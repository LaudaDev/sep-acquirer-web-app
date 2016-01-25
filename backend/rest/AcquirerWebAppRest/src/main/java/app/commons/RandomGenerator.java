package app.commons;

public final class RandomGenerator {

	private static int acquirerOrderId =0;
	private static int paymentId =0;	
	
	public static int generateAcquirerOrderId()
	{
		return acquirerOrderId++;
	}
	
	public static int generatePaymentId()
	{
		return paymentId++;
	}
	
}
