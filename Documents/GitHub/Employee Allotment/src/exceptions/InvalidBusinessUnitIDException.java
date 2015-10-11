package exceptions;

public class InvalidBusinessUnitIDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5996401848904720607L;

	public InvalidBusinessUnitIDException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidBusinessUnitIDException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		System.out.println(message);
	}

}
