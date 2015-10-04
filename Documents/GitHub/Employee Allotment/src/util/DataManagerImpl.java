/**
 * 
 */
package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import exceptions.*;
import bean.Address;
import bean.Designation;
import bean.Employee;
import bean.Gender;

/**
 * @author Administrator
 *
 */
public class DataManagerImpl implements DataManager
{
	private Employee user;
	private List<Employee> employeeList;
	
	@Override
	public Designation logIn(String employeeID, String password) throws InvalidCredentialsException
	{
		Designation designation=null;
		
		DBConnectionImpl datacon=new DBConnectionImpl();
		try(Connection connection=datacon.getConnection();)
		{
			//Fetch record of the employee with current employeeID from Employee table
			PreparedStatement extractCredentials=connection.prepareStatement
					("SELECT designation FROM credentials WHERE employee_id=? AND password=?");
			extractCredentials.setString(1,employeeID);
			extractCredentials.setString(2,password);
			
			ResultSet resultSet=extractCredentials.executeQuery();
			
			if(!resultSet.next())
			{
				throw new InvalidCredentialsException();
			}
			
			designation=Designation.valueOf(resultSet.getString(1).toUpperCase());
			
			PreparedStatement extractEmployee=connection.prepareStatement
					("SELECT * FROM employee WHERE employee_id=?");
			extractEmployee.setString(1,employeeID);
			resultSet=extractEmployee.executeQuery();
			resultSet.next();
			String firstName=resultSet.getString(2);
			String lastName=resultSet.getString(3);
			Gender gender=Gender.valueOf(resultSet.getString(4));
			Date dateOfBirth=resultSet.getDate(5);
			String contactNo=resultSet.getString(6);
			String emailID=resultSet.getString(7);
			double salary=resultSet.getDouble(8);
			String systemID=resultSet.getString(9);
			String unitID=resultSet.getString(10);
			String projectID=resultSet.getString(11);
			
			PreparedStatement extractAddress=connection.prepareStatement
					("SELECT * FROM address WHERE employee_id=?");
			extractAddress.setString(1,employeeID);
			resultSet=extractAddress.executeQuery();
			resultSet.next();
			String street=resultSet.getString(2);
			String city=resultSet.getString(3);
			String state=resultSet.getString(4);
			String country=resultSet.getString(5);
			String pincode=resultSet.getString(6);
			String landmark=resultSet.getString(7);
			Address address=new Address(street,city,state,country,pincode,landmark);
			
			user=new Employee(firstName,lastName,contactNo,emailID,dateOfBirth,gender,
					address,employeeID,salary,systemID,unitID,projectID,designation);
			
			employeeList=new ArrayList<Employee>();
			
			if(designation==Designation.HR)
			{
				populateEmployeeData();
			}
			else if(designation==Designation.UNITHEAD)
			{
				populateEmployeeData(unitID);
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		System.out.println(user);
		
		System.out.println();
		
		for(Employee e : employeeList)
		{
			System.out.println(e);
		}
		
		return designation;
	}

	@Override
	public void logOut()
	{
		// TODO Auto-generated method stub
		//Set variable user to null
		
	}
	
	public void populateEmployeeData()
	{
		DBConnectionImpl dbc=new DBConnectionImpl();
		try(Connection connection=dbc.getConnection();)
		{
			PreparedStatement extractEmployees=connection.prepareStatement
					("SELECT * FROM employee WHERE NOT employee_id=?");
			extractEmployees.setString(1,user.getEmployeeID());
			ResultSet resultSet=extractEmployees.executeQuery();
			while(resultSet.next())
			{
				String employeeID=resultSet.getString(1);
				String firstName=resultSet.getString(2);
				String lastName=resultSet.getString(3);
				Gender gender=Gender.valueOf(resultSet.getString(4));
				Date dateOfBirth=resultSet.getDate(5);
				String contactNo=resultSet.getString(6);
				String emailID=resultSet.getString(7);
				double salary=resultSet.getDouble(8);
				String systemID=resultSet.getString(9);
				String unitID=resultSet.getString(10);
				String projectID=resultSet.getString(11);
			
				PreparedStatement extractAddress=connection.prepareStatement
						("SELECT * FROM address WHERE employee_id=?");
				extractAddress.setString(1,employeeID);
				ResultSet resultSet2=extractAddress.executeQuery();
				resultSet2.next();
				String street=resultSet2.getString(2);
				String city=resultSet2.getString(3);
				String state=resultSet2.getString(4);
				String country=resultSet2.getString(5);
				String pincode=resultSet2.getString(6);
				String landmark=resultSet2.getString(7);
				Address address=new Address(street,city,state,country,pincode,landmark);
				
				PreparedStatement extractDesignation=connection.prepareStatement
						("SELECT designation FROM credentials WHERE employee_id=?");
				extractDesignation.setString(1,employeeID);
				ResultSet resultSet3=extractDesignation.executeQuery();
				resultSet3.next();
				Designation designation=Designation.valueOf
						(resultSet3.getString(1).toUpperCase());
				
				Employee employee=new Employee(firstName,lastName,contactNo,emailID,dateOfBirth,gender,
						address,employeeID,salary,systemID,unitID,projectID,designation);
				
				employeeList.add(employee);
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void populateEmployeeData(String userUnitID)
	{
		DBConnectionImpl dbc=new DBConnectionImpl();
		try(Connection connection=dbc.getConnection();)
		{
			PreparedStatement extractEmployees=connection.prepareStatement
					("SELECT * FROM employee WHERE (NOT employee_id=?) AND unit_id=?");
			extractEmployees.setString(1,user.getEmployeeID());
			extractEmployees.setString(2,userUnitID);
			ResultSet resultSet=extractEmployees.executeQuery();
			while(resultSet.next())
			{
				String employeeID=resultSet.getString(1);
				String firstName=resultSet.getString(2);
				String lastName=resultSet.getString(3);
				Gender gender=Gender.valueOf(resultSet.getString(4));
				Date dateOfBirth=resultSet.getDate(5);
				String contactNo=resultSet.getString(6);
				String emailID=resultSet.getString(7);
				double salary=resultSet.getDouble(8);
				String systemID=resultSet.getString(9);
				String unitID=resultSet.getString(10);
				String projectID=resultSet.getString(11);
			
				PreparedStatement extractAddress=connection.prepareStatement
						("SELECT * FROM address WHERE employee_id=?");
				extractAddress.setString(1,employeeID);
				ResultSet resultSet2=extractAddress.executeQuery();
				resultSet2.next();
				String street=resultSet.getString(2);
				String city=resultSet.getString(3);
				String state=resultSet.getString(4);
				String country=resultSet.getString(5);
				String pincode=resultSet.getString(6);
				String landmark=resultSet.getString(7);
				Address address=new Address(street,city,state,country,pincode,landmark);
				
				PreparedStatement extractDesignation=connection.prepareStatement
						("SELECT designation FROM credentials WHERE employee_id=?");
				extractDesignation.setString(1,employeeID);
				ResultSet resultSet3=extractDesignation.executeQuery();
				resultSet3.next();
				Designation designation=Designation.valueOf
						(resultSet3.getString(1).toUpperCase());
				
				Employee employee=new Employee(firstName,lastName,contactNo,emailID,dateOfBirth,gender,
						address,employeeID,salary,systemID,unitID,projectID,designation);
				
				employeeList.add(employee);
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void populatePojectData()
	{
		
	}
	
	public void populateSystemData()
	{
		
	}
	
	public void populateSoftwareData()
	{
		
	}
	
	public void populateBusinessUnitData()
	{
		
	}

}
