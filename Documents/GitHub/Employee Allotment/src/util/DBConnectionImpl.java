package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionImpl implements DBConnectionUtil
{
	private Connection connection;
	
	@Override
	public Connection getConnection() throws ClassNotFoundException,
			SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection
        		("jdbc:mysql://localhost:3306/employee_allotment","root","root");
		return connection;
	}

	@Override
	public void closeConnection() throws SQLException
	{
		connection.close();
	}
}
