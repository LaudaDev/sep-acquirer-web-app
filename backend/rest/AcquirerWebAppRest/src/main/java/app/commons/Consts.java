package app.commons;

import java.net.URL;

public final class Consts {
	 

	public static final URL PAYMENT_URL;
	public static final URL ACQUIRER_URL;
	public static final URL MERCHANT_URL;
	
	static {
        URL paymentURL;
        try {
            paymentURL = new URL("http://localhost:8083/#/payApp");
        } catch (java.net.MalformedURLException e) {
            paymentURL = null;
        }
        PAYMENT_URL = paymentURL;
    }
	
	
	static {
        URL acquirerURL;
        try {
        	acquirerURL = new URL("http://localhost:8081/api/payment/acquirerResult");
        } catch (java.net.MalformedURLException e) {
        	acquirerURL = null;
        }
        ACQUIRER_URL = acquirerURL;
    }
	
	static {
        URL merchantURL;
        try {
        	merchantURL = new URL("http://localhost:8080//api/merchant/payment/transactionResults");
        } catch (java.net.MalformedURLException e) {
        	merchantURL = null;
        }
        MERCHANT_URL = merchantURL;
    }
	
	
	private Consts(){
		    //this prevents even the native class from 
		    //calling this ctor as well :
		    throw new AssertionError();
		  }
}
