package app.commons;

public final class RandomGenerator {

	private static int acquirerOrderId =1;
	private static int paymentId =1;	
	
	public static int generateAcquirerOrderId()
	{
		return acquirerOrderId++;
	}
	
	public static int generatePaymentId()
	{
		return paymentId++;
	}
	
}
