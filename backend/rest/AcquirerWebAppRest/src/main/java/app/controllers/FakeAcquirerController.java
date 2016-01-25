package app.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.model.transferData.TransactionAuthenticationRequest;
import app.model.transferData.TransactionResponseFromAcquirer;

@Controller
@RequestMapping("/acquirerResult")
public class FakeAcquirerController {

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public TransactionResponseFromAcquirer  fakeResponse(@RequestBody TransactionAuthenticationRequest request)
	{
		TransactionResponseFromAcquirer response = new TransactionResponseFromAcquirer();
		response.setCardAuthenticated(true);
		response.setCardAuthorized(true);
		response.setTransactionSucceded(true);
		response.setAcquirerTimestamp(request.getAcquirerTimestamp());
		System.out.println("acqu timestamp in fake controller "+request.getAcquirerTimestamp());
		response.setAcquirerOrderId(request.getAcquirerOrderId());
		response.setIssuerTimestamp(new Date());
		response.setIssuerOrderId(1);
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String  fake(@ModelAttribute("paymentID") int paymentID)
	{
		return String.valueOf(paymentID);
	}
	/*
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String  fake()
	{
		return "jjjj";
	}
	*/
}
