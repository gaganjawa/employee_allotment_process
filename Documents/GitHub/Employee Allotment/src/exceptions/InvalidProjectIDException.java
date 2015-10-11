package exceptions;

public class InvalidProjectIDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4669233880046728705L;

	public InvalidProjectIDException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidProjectIDException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		System.out.println(message);
	}

}
