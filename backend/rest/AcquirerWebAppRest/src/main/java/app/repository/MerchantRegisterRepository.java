package app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.model.Merchant;

public interface MerchantRegisterRepository extends MongoRepository<Merchant, String>{
	 @Query(value="{'merchantId':?0 , 'merchantPassword': ?1}")
	 Merchant findByIdAndPassword(String merchantId, String merchantPassword);

}
