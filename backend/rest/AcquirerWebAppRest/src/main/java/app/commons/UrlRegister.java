package app.commons;

import java.net.URL;

public final class UrlRegister {
	 

	public static final URL PAYMENT_URL;
	public static final URL ACQUIRER_BANK_URL;
	public static final URL MERCHANT_URL;
	
	static {
        URL paymentURL;
        try {
            paymentURL = new URL("https://localhost:8083/#/payApp");
        } catch (java.net.MalformedURLException e) {
            paymentURL = null;
        }
        PAYMENT_URL = paymentURL;
    }
	
	
	static {
        URL acquirerURL;
        try {
        	acquirerURL = new URL("https://localhost:8082/api/payment/acquirerResult");
        } catch (java.net.MalformedURLException e) {
        	acquirerURL = null;
        }
        ACQUIRER_BANK_URL = acquirerURL;
    }
	
	static {
        URL merchantURL;
        try {
        	merchantURL = new URL("https://localhost:8080//api/merchant/payment/transactionResults");
        } catch (java.net.MalformedURLException e) {
        	merchantURL = null;
        }
        MERCHANT_URL = merchantURL;
    }
	
	
	private UrlRegister(){
		    //this prevents even the native class from 
		    //calling this ctor as well :
		    throw new AssertionError();
		  }
}
