package app.services;

import java.net.URI;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import app.commons.CustomIdGenerator;
import app.commons.UrlRegister;
import app.model.Transaction;
import app.model.transferData.AcquirerInfo;
import app.model.transferData.MerchantPaymentRequest;
import app.model.transferData.PaymentCardInfo;
import app.model.transferData.PaymentInstructions;
import app.model.transferData.SharingAmount;
import app.model.transferData.TransactionAuthenticationRequest;
import app.model.transferData.TransactionResponseForMerchant;
import app.model.transferData.TransactionResponseFromAcquirer;
import app.model.transferData.TransactionStatus;
import app.services.exceptions.BadRequestException;
import app.services.exceptions.CustomRestClientException;

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
		Date acquirerTimestamp;

		// if request not valid -> redirect to error URL!
		// check merchant id and password
		if (bindingResult.hasErrors()) {
			if (request.getErrorUrl() != null) {
				// redirekcija na error url
			} else {
				throw new BadRequestException("Merchant paymet request - validation errors");
			}
		}

		if (request.getMerchantInfo() == null)
			throw new BadRequestException("Merchant info is null");

		if (!merchantService.isMerchantRegistered(request.getMerchantId(), request.getMerchatPassword())) {
			// redirekcija ->error url
		}

		logger.info("Merchant payment request");
		logger.info(request.toString());

		instructions = new PaymentInstructions();
		paymentId = CustomIdGenerator.generatePaymentId();

		instructions.setPaymentURL(UrlRegister.PAYMENT_URL);
		instructions.setPaymentID(paymentId);

		Transaction transaction = new Transaction();
		transaction.setMerchantRequestData(request);
		transaction.setPaymentId(paymentId);
		acquirerOrderId = CustomIdGenerator.generateAcquirerOrderId();
		acquirerTimestamp = new Date();
		transaction.setAcquirerInfo(new AcquirerInfo(acquirerOrderId, acquirerTimestamp));
		transactionService.save(transaction);
		logger.info("payment instruction for merchant");
		logger.info(instructions.toString());
		return instructions;
	}

	public URI sendAuthenticationRequest(PaymentCardInfo paymentCardDetails, int paymentID) {

		logger.info("Payment card details");
		logger.info(paymentCardDetails.toString());
		logger.info("Creating authentication request");

		TransactionAuthenticationRequest transactionAuthRequest;
		TransactionResponseFromAcquirer bankResponse;
		TransactionResponseForMerchant responseForMerchant;

		Transaction transaction = transactionService.findByPaymentId(paymentID);

		transactionAuthRequest = new TransactionAuthenticationRequest();
		transactionAuthRequest.setAcquirerInfo(transaction.getAcquirerInfo());
		transactionAuthRequest.setCardInfo(paymentCardDetails);

		if (transaction.getMerchantRequestData() != null)
			transactionAuthRequest.setTransactionAmount(transaction.getMerchantRequestData().getAmount());

		logger.info("Request sent to acquirer bank");
		logger.info(transactionAuthRequest.toString());
		bankResponse = postForTransactionResult(transactionAuthRequest);

		if (bankResponse == null) {
			logger.error("There was an error communicating with the acquirer bank server.Bank response is null.");
			responseForMerchant = createTransactionResponseWithServerError();
		} else {

			logger.info("Bank response - transaction result");
			logger.info(bankResponse.toString());

			transaction = saveTransactionResult(bankResponse);
			responseForMerchant = createTransactionResponseForMerchant(transaction);
		}
		
		return sendTransactionResults(responseForMerchant);
	}

	public Transaction saveTransactionResult(TransactionResponseFromAcquirer bankResponse) {

		Transaction transaction = transactionService.findByOrderIdAndTimestamp(
				bankResponse.getAcquirerInfo().getAcquirerOrderId(),
				bankResponse.getAcquirerInfo().getAcquirerTimestamp());
		transaction.setTransactionStatus(bankResponse.getTransactionStatus());
		transaction.setIssuerInfo(bankResponse.getIssuerInfo());
		return transactionService.update(transaction);
	}

	public TransactionResponseFromAcquirer postForTransactionResult(
			TransactionAuthenticationRequest transactionAuthRequest) {
		TransactionResponseFromAcquirer bankResponse = null;

		try {
			bankResponse = restTemplate.postForObject(UrlRegister.ACQUIRER_BANK_URL.toString(), transactionAuthRequest,
					TransactionResponseFromAcquirer.class);
		} catch (RestClientException e) {

			logger.error(e.getStackTrace().toString());
		}

		return bankResponse;
	}

	public URI sendTransactionResults(TransactionResponseForMerchant transactionResults) {

		URI merchantResponse = restTemplate.postForLocation(UrlRegister.MERCHANT_URL.toString(), transactionResults);
		// promijeniti da u slucaju greske vraca predefinisani error url
		if (merchantResponse == null) {
			throw new CustomRestClientException(
					"There was an error communicating with the merchant server.URL is null.");

		}
		logger.info("Merchant response - URL");
		logger.info(merchantResponse.toString());
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
		transactionResults
				.setMerchantOrderId(transaction.getMerchantRequestData().getMerchantInfo().getMerchantOrderId());
		transactionResults.setAcquirerInfo(transaction.getAcquirerInfo());
		transactionResults.setPaymentId(transaction.getPaymentId());

		logger.info("Transaction result for merchant");
		logger.info(transactionResults.toString());
		return transactionResults;

	}

}
