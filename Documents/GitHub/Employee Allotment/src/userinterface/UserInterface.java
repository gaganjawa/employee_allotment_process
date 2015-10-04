package userinterface;

import util.DataManagerImpl;

public class UserInterface {

	public static void main(String[] args) throws Exception
	{
		DataManagerImpl dbimpl=new DataManagerImpl();
		dbimpl.logIn("E00006","1234");
	}

}
