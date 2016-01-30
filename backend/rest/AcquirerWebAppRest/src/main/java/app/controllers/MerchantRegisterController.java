package app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.model.Merchant;
import app.services.MerchantRegisterService;

@Controller
@RequestMapping("/merchantRegister")
public class MerchantRegisterController {
	

	@Autowired
	private MerchantRegisterService merchantService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(@Validated @RequestBody Merchant merchant) {
		return merchantService.save(merchant);	
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Merchant> getAll() {
		return merchantService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{merchantId}")
	@ResponseBody
	public Merchant getById(@PathVariable("merchantId") String merchantId) {
		return merchantService.findById(merchantId);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{merchantId}")
	@ResponseBody
	public String remove(@PathVariable("merchantId") String merchantId) {
		return merchantService.remove(merchantId);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(@Validated @RequestBody Merchant merchant) {
		return merchantService.update(merchant);	
	}

}
