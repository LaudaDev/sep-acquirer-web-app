package app.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Transaction;
import app.repository.TransactionRepository;
import app.services.exceptions.BadRequestException;
import app.services.exceptions.NotFoundException;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction save(Transaction transaction) {

		return transactionRepository.save(transaction);
	}

	public List<Transaction> findAll() {

		return (List<Transaction>) transactionRepository.findAll();
	}

	public Transaction findById(String id) {
		if (id == null) {
			throw new BadRequestException("transaction id is null");
		}
		Transaction transaction = transactionRepository.findOne(id);

		if (transaction == null) {
			throw new NotFoundException("transaction with id " + id + " doesn't exist.");
		}

		return transaction;
	}

	public Transaction findByOrderIdAndTimestamp(int acquirerOrderId, Date acquirerTimestamp) {
		if (acquirerTimestamp == null) {
			throw new BadRequestException("acquirerTimestamp is null");

		}
		Transaction transaction = transactionRepository.findByAcquirerOrderIdAndTimestamp(acquirerOrderId,
				acquirerTimestamp);
		if (transaction == null) {
			throw new NotFoundException("transaction with acquirerOrderId " + acquirerOrderId
					+ " and acquirerTimestamp " + acquirerTimestamp + " doesn't exist.");
		}
		return transaction;
	}

	public Transaction findByPaymentId(int paymentId) {

		Transaction transaction = transactionRepository.findByPaymentId(paymentId);
		if (transaction == null) {
			throw new NotFoundException("transaction with paymentID " + paymentId + " doesn't exist.");
		}
		return transaction;

	}

	public Double getAmountToPay(int paymentId) {
		Transaction t = transactionRepository.getAmountToPay(paymentId);
		if (t == null)
			throw new NotFoundException("Transaction with paymentId " + paymentId + " not found");
		else
			return t.getMerchantRequestData().getAmount().doubleValue();

	}

	public void remove(String id) {

		if (id == null) {
			throw new BadRequestException("Transaction id is null");
		}
		transactionRepository.delete(id);
	}

	public Transaction update(Transaction transaction) {

		if (transaction == null) {
			throw new BadRequestException("Transaction is null");
		}
		return transactionRepository.save(transaction);
	}
}
