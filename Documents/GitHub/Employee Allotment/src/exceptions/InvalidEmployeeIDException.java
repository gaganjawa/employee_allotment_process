package exceptions;

public class InvalidEmployeeIDException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5226504936886724374L;

	@Override
	public String toString()
	{
		return "Invalid Employee ID";
	}
}
