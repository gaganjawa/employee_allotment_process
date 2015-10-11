package exceptions;

public class InvalidSystemIDException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7120542488723481397L;

	public InvalidSystemIDException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidSystemIDException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		System.out.println(message);
	}

}
