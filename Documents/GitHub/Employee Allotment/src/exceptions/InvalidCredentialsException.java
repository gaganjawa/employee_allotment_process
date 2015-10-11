package exceptions;

public class InvalidCredentialsException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7415482285426490390L;

	@Override
	public String toString()
	{
		return "Incorrect Employee ID or Password";
	}
}

