/**
 * 
 */
package bean;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class Employee extends Person
{

	private String employeeID;
	private double salary;
	private String systemID;
	private String unitID;
	private String projectID;
	private Designation designation;

	public Employee(String firstName, String lastName, String contactNo,
			String emailID, Date dateOfBirth, Gender gender, Address address,
			String employeeID, double salary, String systemID, String unitID,
			String projectID, Designation designation)
	{
		super(firstName, lastName, contactNo, emailID, dateOfBirth, gender,
				address);
		this.employeeID = employeeID;
		this.salary = salary;
		this.systemID = systemID;
		this.unitID = unitID;
		this.projectID = projectID;
		this.designation = designation;
	}

	public String getEmployeeID()
	{
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID)
	{
		this.employeeID = employeeID;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	
	public String getSystemID()
	{
		return systemID;
	}
	
	public void setSystemID(String systemID)
	{
		this.systemID = systemID;
	}
	
	public String getUnitID()
	{
		return unitID;
	}
	
	public void setUnitID(String unitID)
	{
		this.unitID = unitID;
	}
	
	public String getProjectID()
	{
		return projectID;
	}
	
	public void setProjectID(String projectID)
	{
		this.projectID = projectID;
	}
	
	public Designation getDesgnation()
	{
		return designation;
	}
	
	public void setDesgnation(Designation designation)
	{
		this.designation = designation;
	}
	
	@Override
	public String toString()
	{
		return firstName+" "+lastName+" "+ contactNo+" "+ emailID+" "+ dateOfBirth+" "+gender+" "+address+" "+employeeID+" "+ salary+" "+systemID+" "+ unitID+" "+ projectID+" " +designation;
	}
}
