package exceptions;

public class NoProjectVacancyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -237730754696648194L;

	public NoProjectVacancyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoProjectVacancyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		System.out.println(message);
	}

}
