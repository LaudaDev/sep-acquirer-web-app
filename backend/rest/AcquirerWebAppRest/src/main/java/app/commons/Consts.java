package app.commons;

public class Consts {

	public static final String datePattern = "dd.MM.yyyy HH:mm:ss";

	private Consts() {
		// this prevents even the native class from
		// calling this ctor as well :
		throw new AssertionError();
	}
}
