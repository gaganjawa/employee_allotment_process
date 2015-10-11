package exceptions;

public class SystemNotAvailableException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7154650032818188558L;

	public SystemNotAvailableException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SystemNotAvailableException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		System.out.println(message);
	}

}
