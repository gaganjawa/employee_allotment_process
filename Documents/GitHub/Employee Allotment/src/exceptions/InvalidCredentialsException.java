package exceptions;

public class InvalidCredentialsException extends Exception 
{
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "Incorrect Employee ID or Password";
	}
}

