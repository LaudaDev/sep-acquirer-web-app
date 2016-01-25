package app.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Merchant;
import app.repository.MerchantRegisterRepository;
import app.services.exceptions.BadRequestException;
import app.services.exceptions.NotFoundException;

@Service
public class MerchantRegisterService {

	@Autowired
	private MerchantRegisterRepository merchantRepository;

	private static final Logger logger = Logger.getLogger(MerchantRegisterService.class);

	public Map<String, Object> save(Merchant merchant) {

		Map<String, Object> response;
		response = new LinkedHashMap<String, Object>();
		if(isMerchantRegistered(merchant.getMerchantId(), merchant.getMerchantPassword()))
		{
			response.put("message", "Merchant already exists");
			return response;
			
		}
		merchantRepository.save(merchant);
		response.put("message", "Merchant created successfully");
		response.put("sport", merchant);
		logger.info("Merchant created successfully");
		return response;
	}

	public List<Merchant> findAll() {

		return (List<Merchant>) merchantRepository.findAll();
	}

	public Merchant findById(String id) {
		
		if(id == null)
		{
			throw new BadRequestException("merchant id is null");
		} 
		logger.info("Find merchant with id: " + id);
		Merchant merchant = merchantRepository.findOne(id);
		
		if (merchant == null) {
			throw new NotFoundException("Merchant with id " + id + " doesn't exist.");
		}
		return merchantRepository.findOne(id);
	}

	public String remove(String id) {
		if(id == null)
		{
			throw new BadRequestException("merchant id is null");
		} 
		logger.info("Removing merchant with id: " + id);		
		merchantRepository.delete(id);
		return "removed";
	
	}

	public Map<String, Object> update(Merchant merchant) {
		
		Map<String, Object> response;
		response = new LinkedHashMap<String, Object>();
		merchantRepository.save(merchant);
		response.put("message", "Merchant updated successfully");
		response.put("insurance", merchant);
		logger.info("Merchant updated successfully");	
		return response;
	}

	boolean isMerchantRegistered(String merchantId, String merchantPassword) {
		
		if (merchantId == null || merchantPassword==null) {
			throw new BadRequestException("merchantId or merchantPassword is null");		
		}
		if(merchantRepository.findByIdAndPassword( merchantId,merchantPassword)!=null)
				return true;
		return false;
	
	}

}
