package app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.model.Transaction;


public interface TransactionRepository extends MongoRepository<Transaction, String>{
	  @Query(value="{'acquirerOrderId':?0 , 'acquirerTimestamp': ?1}")
	  Transaction findByAcquirerOrderIdAndTimestamp(int acquirerOrderId, Date acquirerTimestamp);

	  @Query(value="{ 'paymentId' : ?0}")
	  Transaction findByPaymentId(int paymentId);
	  
	  @Query(value="{'acquirerTimestamp': ?0 }")
	  List<Transaction> findByTimestamp(Date acquirerTimestamp);
	  
	  @Query(value="{ 'paymentId' : ?0}", fields ="{'merchantRequestData.amount':1}")
	  Transaction getAmountToPay(int paymentId);
	 

}
