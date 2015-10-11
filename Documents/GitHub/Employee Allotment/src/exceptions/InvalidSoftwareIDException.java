package exceptions;

public class InvalidSoftwareIDException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6977143753116280565L;

	public InvalidSoftwareIDException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidSoftwareIDException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		System.out.println(message);
	}

}
