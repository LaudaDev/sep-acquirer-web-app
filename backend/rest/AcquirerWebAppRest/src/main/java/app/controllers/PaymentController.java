package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.model.transferData.MerchantPaymentRequest;
import app.model.transferData.PaymentCard;
import app.model.transferData.PaymentInstructions;
import app.model.transferData.SharingAmount;
import app.model.transferData.UrlWrapper;
import app.services.PaymentService;

@Controller
@RequestMapping("/paying")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public PaymentInstructions generatePaymentInstructions(@Validated @RequestBody MerchantPaymentRequest request,
			BindingResult bindingResult) {
		return paymentService.generatePaymentInstructions(request, bindingResult);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/buy/{paymentID}")
	@ResponseBody
	public UrlWrapper payInsurance(@PathVariable("paymentID") Integer paymentID,
			@Validated @RequestBody PaymentCard paymentCardDetails) {
		System.out.println("paying post");
		
		UrlWrapper resultURI = paymentService.sendAuthenticationRequest(paymentCardDetails, paymentID);
		System.out.println("redirection on :"+resultURI);
		return resultURI;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{paymentID}")
	@ResponseBody
	public SharingAmount getAmountToPay(@PathVariable("paymentID") Integer paymentID) {
		return paymentService.getAmountToPay(paymentID);
	}
}
