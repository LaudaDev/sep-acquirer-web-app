package app.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import app.commons.Consts;
import app.commons.CustomIdGenerator;
import app.commons.UrlRegister;
import app.model.Transaction;
import app.model.transferData.AcquirerInfo;
import app.model.transferData.CardInfo;
import app.model.transferData.MerchantPaymentRequest;
import app.model.transferData.PaymentCard;
import app.model.transferData.PaymentInstructions;
import app.model.transferData.SharingAmount;
import app.model.transferData.TransactionAuthenticationRequest;
import app.model.transferData.TransactionResponseForMerchant;
import app.model.transferData.TransactionResponseFromAcquirer;
import app.model.transferData.TransactionStatus;
import app.services.exceptions.BadRequestException;

@Service
public class PaymentService {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private MerchantRegisterService merchantService;

	private static final Logger logger = Logger.getLogger(PaymentService.class);
	final RestTemplate restTemplate = new RestTemplate();

	public PaymentInstructions generatePaymentInstructions(MerchantPaymentRequest request,
			BindingResult bindingResult) {
		PaymentInstructions instructions;
		int paymentId;
		int acquirerOrderId;
		Date acquirerTimestamp = null;
		Transaction transaction;

		if (bindingResult.hasErrors()) {

			logger.info("Payment request was not valid.");

			if (request.getErrorUrl() != null) {

				instructions = new PaymentInstructions();
				instructions.setPaymentURL(request.getErrorUrl());
				logger.info("Returned error url");

			} else {
				throw new BadRequestException("Paymet request - validation errors");
			}
		} else {

			if (!merchantService.isMerchantRegistered(request.getMerchantId(), request.getMerchatPassword())) {

				logger.info("Merchant not registered");
				instructions = new PaymentInstructions();
				instructions.setPaymentURL(request.getErrorUrl());

			} else {

				logger.info("Payment request " + request.toString());

				instructions = new PaymentInstructions();
				paymentId = CustomIdGenerator.generatePaymentId();

				instructions.setPaymentURL(UrlRegister.PAYMENT_URL);
				instructions.setPaymentID(paymentId);

				transaction = new Transaction();
				transaction.setMerchantRequestData(request);
				transaction.setPaymentId(paymentId);
				acquirerOrderId = CustomIdGenerator.generateAcquirerOrderId();
			
				SimpleDateFormat format = new SimpleDateFormat(Consts.datePattern);
				Date unparsedDate = new Date();
				try {
					acquirerTimestamp = format.parse(format.format(unparsedDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				transaction.setAcquirerInfo(new AcquirerInfo(acquirerOrderId, acquirerTimestamp));
				transactionService.save(transaction);

			}
		}

		logger.info("Payment instruction for merchant " + instructions.toString());
		return instructions;
	}

	public URI sendAuthenticationRequest(PaymentCard paymentCardDetails, int paymentID) {

		logger.info("Payment card details " + paymentCardDetails.toString());

		TransactionAuthenticationRequest transactionAuthRequest;
		TransactionResponseFromAcquirer bankResponse;
		TransactionResponseForMerchant responseForMerchant;
		URI retURL = null;

		Transaction transaction = transactionService.findByPaymentId(paymentID);

		transactionAuthRequest = new TransactionAuthenticationRequest();
		transactionAuthRequest.setAcquirerInfo(transaction.getAcquirerInfo());

		CardInfo cardInfo = mapToCardInfo(paymentCardDetails);
		transactionAuthRequest.setCardInfo(cardInfo);

		if (transaction.getMerchantRequestData() != null)
			transactionAuthRequest.setTransactionAmount(transaction.getMerchantRequestData().getAmount());

		logger.info("Request sent to acquirer bank " + transactionAuthRequest.toString());

		bankResponse = postForTransactionResult(transactionAuthRequest);

		if (bankResponse == null) {
		
			logger.error("There was an error communicating with the acquirer bank server.BankResponse is null.");
			responseForMerchant = createTransactionResponseWithServerError();
		
		} else {

			logger.info("Bank response - transaction result: " + bankResponse.toString());

			transaction = saveTransactionResult(bankResponse);
			if (transaction == null) {
				responseForMerchant = createTransactionResponseWithServerError();

			} else {
				responseForMerchant = createTransactionResponseForMerchant(transaction);
			}
		}

		retURL = sendTransactionResults(responseForMerchant);

		if (retURL == null) {
			try {
				retURL = transaction.getMerchantRequestData().getErrorUrl().toURI();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}

		return retURL;
	}

	public Transaction saveTransactionResult(TransactionResponseFromAcquirer bankResponse) {
		Transaction transaction = null;

		if (bankResponse.getAcquirerInfo() != null) {
			transaction = transactionService.findByOrderIdAndTimestamp(bankResponse.getAcquirerInfo().getOrderId(),
					bankResponse.getAcquirerInfo().getTimestamp());
			transaction.setTransactionStatus(bankResponse.getTransactionStatus());
			transaction.setIssuerInfo(bankResponse.getIssuerInfo());
			transaction = transactionService.update(transaction);
		}
		return transaction;
	}

	public TransactionResponseFromAcquirer postForTransactionResult(
			TransactionAuthenticationRequest transactionAuthRequest) {
		TransactionResponseFromAcquirer bankResponse = null;

		try {
			bankResponse = restTemplate.postForObject(UrlRegister.ACQUIRER_BANK_URL.toString(), transactionAuthRequest,
					TransactionResponseFromAcquirer.class);
		} catch (RestClientException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return bankResponse;
	}

	public URI sendTransactionResults(TransactionResponseForMerchant transactionResults) {

		URI merchantResponse = restTemplate.postForLocation(UrlRegister.MERCHANT_URL.toString(), transactionResults);
		if (merchantResponse == null) {
			logger.info("There was an error communicating with the merchant server.URL is null.");
		}

		return merchantResponse;

	}

	public SharingAmount getAmountToPay(int paymentId) {
		SharingAmount amount = new SharingAmount(transactionService.getAmountToPay(paymentId));
		return amount;
	}

	private TransactionResponseForMerchant createTransactionResponseWithServerError() {

		TransactionStatus transactionStatus = new TransactionStatus("05", "SERVER_ERROR");
		TransactionResponseForMerchant transactionResponse = new TransactionResponseForMerchant();
		transactionResponse.setTransactionStatus(transactionStatus);

		return transactionResponse;
	}

	private TransactionResponseForMerchant createTransactionResponseForMerchant(Transaction transaction) {

		TransactionResponseForMerchant transactionResults = new TransactionResponseForMerchant();
		transactionResults.setTransactionStatus(transaction.getTransactionStatus());
		if (transaction.getMerchantRequestData() != null) {
			if (transaction.getMerchantRequestData().getMerchantInfo() != null) {
				transactionResults.setMerchantOrderId(
						transaction.getMerchantRequestData().getMerchantInfo().getMerchantOrderId());
			} else {
				logger.warn("Merchant info is null, transaction id: " + transaction.getId());
			}
		} else {
			logger.warn("Merchant request data is null, transaction id: " + transaction.getId());
		}

		transactionResults.setAcquirerInfo(transaction.getAcquirerInfo());
		transactionResults.setPaymentId(transaction.getPaymentId());

		logger.info("Transaction result for merchant " + transactionResults.toString());
		return transactionResults;

	}

	private CardInfo mapToCardInfo(PaymentCard paymentCardInfo) {
		CardInfo cardInfo = new CardInfo();

		String expirationDateString = paymentCardInfo.getExpirationMonth().toString() + "/"
				+ paymentCardInfo.getExpirationYear().toString();
		String cardholder = paymentCardInfo.getCardholderName() + paymentCardInfo.getCardholderName();

		cardInfo.setExpirationDate(expirationDateString);
		cardInfo.setHolderName(cardholder);
		cardInfo.setPan(paymentCardInfo.getCardNumber());
		cardInfo.setSecurityCode(paymentCardInfo.getSecurityCode());

		logger.info(cardInfo.toString());
		return cardInfo;
	}

}
