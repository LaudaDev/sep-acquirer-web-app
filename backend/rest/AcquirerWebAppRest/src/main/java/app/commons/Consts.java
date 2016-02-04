package app.commons;

public class Consts {

	public static final String datePattern = "dd.MM.yyyy HH:mm:ss";
	public static final int LENGTH_PAN = 16;
	public static final int MIN_SECURITY_CODE = 100;
	public static final int MAX_SECURITY_CODE = 999;
	public static final int MIN_HOLDER_NAME = 2; 
	public static final int MAX_HOLDER_NAME = 100; 

	private Consts() {
		// this prevents even the native class from
		// calling this ctor as well :
		throw new AssertionError();
	}
}
