package app.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.model.Transaction;


public interface TransactionRepository extends MongoRepository<Transaction, String>{
	  @Query(value="{'acquirerInfo.orderId':?0 , 'acquirerInfo.timestamp': ?1}")
	  Transaction findByAcquirerOrderIdAndTimestamp(int acquirerOrderId, Date acquirerTimestamp);

	  @Query(value="{'paymentId' : ?0}")
	  Transaction findByPaymentId(Integer paymentId);
	  
	  @Query(value="{ 'paymentId' : ?0}", fields ="{'merchantRequestData.amount':1}")
	  Transaction getAmountToPay(int paymentId);
	 

}
