package app.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.model.transferData.MerchantPaymentRequest;
import app.model.transferData.PaymentCardDetails;
import app.model.transferData.PaymentInstructions;
import app.services.PaymentService;

@Controller
@RequestMapping("/paying")
@CrossOrigin(origins = "http://localhost:8083") 
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public PaymentInstructions generatePaymentInstructions(@Validated @RequestBody MerchantPaymentRequest request,  BindingResult bindingResult)
	{
		return paymentService.generatePaymentInstructions(request, bindingResult );
	}
	
	/*
	@RequestMapping(method = RequestMethod.POST, value = "pay")
	@ResponseBody
	public TransactionResultsResponse payInsurance(@ModelAttribute("paymentID") int paymentID ,@RequestBody PaymentCardDetails paymentCardDetails)
	{
		return instructionService.sendAuthenticationRequest(paymentCardDetails, paymentID);
	}
	*/

	@RequestMapping(method = RequestMethod.POST, value = "/{paymentID}")
	@ResponseBody
	public String payInsurance(@PathVariable("paymentID") Integer paymentID ,@Validated @RequestBody PaymentCardDetails paymentCardDetails)
	{
		URI resultURI = paymentService.sendAuthenticationRequest(paymentCardDetails,paymentID);
		return "redirect:"+resultURI;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{paymentID}")
	@ResponseBody
	public Double getAmountToPay(@PathVariable("paymentID") Integer paymentID)
	{
		return paymentService.getAmountToPay(paymentID);
	}
}
