package app.controllers;



import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.model.transferData.IssuerInfo;
import app.model.transferData.TransactionAuthenticationRequest;
import app.model.transferData.TransactionResponseFromAcquirer;
import app.model.transferData.TransactionStatus;

@Controller
@RequestMapping("/acquirerResult")
public class FakeAcquirerController {

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public TransactionResponseFromAcquirer  fakeResponse(@RequestBody TransactionAuthenticationRequest request)
	{
		System.out.println("fakeeeee");
		System.out.println("fake request "+request);
		System.out.println("date in format json"+request.getAcquirerInfo().getTimestamp());
	
		TransactionResponseFromAcquirer response = new TransactionResponseFromAcquirer();
		response.setTransactionStatus(new TransactionStatus("00", "success"));
		//response.setTransactionStatus(new TransactionStatus("05", "failed"));
		response.setAcquirerInfo(request.getAcquirerInfo());
		response.setIssuerInfo(new IssuerInfo(1,new Date()));
		System.out.println("fake response : "+response.toString());

		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String  fake(@ModelAttribute("paymentID") int paymentID)
	{
		return String.valueOf(paymentID);
	}

}
