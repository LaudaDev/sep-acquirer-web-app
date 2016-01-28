package app.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Transaction;
import app.repository.TransactionRepository;
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

		return transactionRepository.findOne(id);
	}

	public Transaction findByOrderIdAndTimestamp(int acquirerOrderId, Date acquirerTimestamp) {

		return transactionRepository.findByAcquirerOrderIdAndTimestamp(acquirerOrderId, acquirerTimestamp);
	}

	public Transaction findByPaymentId(int paymentId) {

		return transactionRepository.findByPaymentId(paymentId);
	}

	public List<Transaction> findByTimestamp(Date d) {
		return transactionRepository.findByTimestamp(d);
	}

	public Double getAmountToPay(int paymentId) {
		Transaction t = transactionRepository.findByPaymentId(paymentId);
		if (t == null)
			throw new NotFoundException("");
		else
			return t.getMerchantRequestData().getAmount().doubleValue();

	}

	public void remove(String id) {

		transactionRepository.delete(id);
	}

	public Transaction update(Transaction transaction) {

		return transactionRepository.save(transaction);
	}
}
