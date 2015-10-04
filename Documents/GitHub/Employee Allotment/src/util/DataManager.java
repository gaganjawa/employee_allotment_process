package util;

import bean.Designation;
import exceptions.InvalidCredentialsException;

public interface DataManager
{
	public Designation logIn(String employeeID,String password) throws InvalidCredentialsException;

	public void logOut();
}
