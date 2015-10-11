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
	private double salary;
	private String systemID;
	private String unitID;
	private String projectID;
	private Designation designation;
	private Skill skill;
	private String interviewFeedback;
	private Date dateOfJoining;

	public Employee(String firstName, String lastName, String contactNo,
			String emailID, Date dateOfBirth, Gender gender, Address address,
			double salary, String systemID, String unitID,
			String projectID, Designation designation,
			Skill skill,String interviewFeedback,Date dateOfJoining)
	{
		super(firstName, lastName, contactNo, emailID, dateOfBirth, gender,
				address);
		//this.employeeID = employeeID;
		this.salary = salary;
		this.systemID = systemID;
		this.unitID = unitID;
		this.projectID = projectID;
		this.designation = designation;
		this.skill=skill;
		this.interviewFeedback=interviewFeedback;
		this.dateOfJoining=dateOfJoining;
	}

	/*public String getEmployeeID()
	{
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID)
	{
		this.employeeID = employeeID;
	}
	*/
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
		super.toString();
		
		java.lang.System.out.println("OFFICIAL INFORMATION");
		java.lang.System.out.println("---------------------");
		java.lang.System.out.println("\t DESIGNATION: "+designation);
		java.lang.System.out.println("\t DATE OF JOINING: "+dateOfJoining);
		java.lang.System.out.println("\t SKILL: "+skill);
		return "";
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public String getInterviewFeedback() {
		return interviewFeedback;
	}

	public void setInterviewFeedback(String interviewFeedback) {
		this.interviewFeedback = interviewFeedback;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
}
