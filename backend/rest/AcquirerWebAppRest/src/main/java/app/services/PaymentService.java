package app.services;

import java.net.URI;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import app.commons.Consts;
import app.commons.RandomGenerator;
import app.model.Transaction;
import app.model.transferData.MerchantPaymentRequest;
import app.model.transferData.PaymentCardDetails;
import app.model.transferData.PaymentInstructions;
import app.model.transferData.TransactionAuthenticationRequest;
import app.model.transferData.TransactionResponseForMerchant;
import app.model.transferData.TransactionResponseFromAcquirer;
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

		if (!merchantService.isMerchantRegistered(request.getMerchantId(), request.getMerchatPassword())) {
			// redirekcija ->error url
		}

		logger.info("Merchant payment request");
		logger.info(request.toString());
		
		instructions = new PaymentInstructions();
		paymentId = RandomGenerator.generatePaymentId();

		instructions.setPaymentURL(Consts.PAYMENT_URL);
		instructions.setPaymentID(paymentId);

		Transaction transaction = new Transaction();
		transaction.setMerchantRequestData(request);
		transaction.setPaymentId(paymentId);
		acquirerOrderId = RandomGenerator.generateAcquirerOrderId();
		acquirerTimestamp = new Date();
		transaction.setAcquirerOrderId(acquirerOrderId);
		transaction.setAcquirerTimestamp(acquirerTimestamp);
		transactionService.save(transaction);
		logger.info("payment instruction for merchant");
		logger.info(instructions.toString());
		return instructions;
	}

	public URI sendAuthenticationRequest(PaymentCardDetails paymentCardDetails, int paymentID) {
		logger.info("Payment card details");
		logger.info(paymentCardDetails.toString());
		logger.info("Creating authentication request");
		TransactionAuthenticationRequest transactionAuthRequest;
		TransactionResponseFromAcquirer bankResponse;
		Transaction transaction = transactionService.findByPaymentId(paymentID);
		transactionAuthRequest = new TransactionAuthenticationRequest();
		transactionAuthRequest.setAcquirerOrderId(transaction.getAcquirerOrderId());
		transactionAuthRequest.setAcquirerTimestamp(transaction.getAcquirerTimestamp());
		transactionAuthRequest.setPaymentCardDetails(paymentCardDetails);

		logger.info("Request sent to acquirer bank");
		logger.info(transactionAuthRequest.toString());
		bankResponse = postForTransactionResult(transactionAuthRequest);

		if (bankResponse == null) {
			throw new CustomRestClientException(
					"There was an error communicating with the acquirer bank server.Bank response is null.");
		}

		logger.info("Bank response - transaction result");
		logger.info(bankResponse.toString());
		
		transaction = saveTransactionResult(bankResponse);
		
		return sendTransactionResults(transaction);
	}

	public Transaction saveTransactionResult(TransactionResponseFromAcquirer bankResponse)
	{
		Transaction transaction = transactionService.findByOrderIdAndTimestamp(bankResponse.getAcquirerOrderId(),
				bankResponse.getAcquirerTimestamp());
		transaction.setCardAuthorized(bankResponse.isCardAuthorized());
		transaction.setCardAuthenticated(bankResponse.isCardAuthenticated());
		transaction.setTransactionSucceded(bankResponse.isTransactionSucceded());
		transaction.setIssuerOrderId(bankResponse.getIssuerOrderId());
		transaction.setIssuerTimestamp(bankResponse.getIssuerTimestamp());
		return transactionService.update(transaction);
	}
	
	
	public TransactionResponseFromAcquirer postForTransactionResult(
			TransactionAuthenticationRequest transactionAuthRequest) {
		TransactionResponseFromAcquirer bankResponse = null;
		try {
			bankResponse = restTemplate.postForObject(Consts.ACQUIRER_URL.toString(), transactionAuthRequest,
					TransactionResponseFromAcquirer.class);
		} catch (RestClientException e) {
			logger.error(e.getStackTrace().toString());
		}

		return bankResponse;
	}

	public URI sendTransactionResults(Transaction transaction) {
		
		TransactionResponseForMerchant transactionResults = new TransactionResponseForMerchant();
		transactionResults.setCardAuthorized(transaction.isCardAuthorized());
		transactionResults.setCardAuthenticated(transaction.isCardAuthenticated());
		transactionResults.setTransactionSucceded(transaction.isTransactionSucceded());
		transactionResults.setMerchantOrderId(transaction.getMerchantRequestData().getMerchantOrderId());
		transactionResults.setAcquirerOrderId(transaction.getAcquirerOrderId());
		transactionResults.setAcquirerTimestamp(transaction.getAcquirerTimestamp());
		transactionResults.setPaymentId(transaction.getPaymentId());

		logger.info("Transaction result for merchant");
		logger.info(transactionResults.toString());
		
		URI merchantResponse = restTemplate.postForLocation(Consts.MERCHANT_URL.toString(), transactionResults);
		if(merchantResponse == null)
		{
			throw new CustomRestClientException(
					"There was an error communicating with the merchant server.URL is null.");

		}
		logger.info("Merchant response - URL");
		logger.info(merchantResponse.toString());
		return merchantResponse;

	}
}
