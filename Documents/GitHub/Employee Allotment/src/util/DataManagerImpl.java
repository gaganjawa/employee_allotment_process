/**
 * 
 */
package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import exceptions.*;
import bean.Address;
import bean.BusinessUnit;
import bean.Designation;
import bean.Employee;
import bean.Gender;
import bean.Project;
import bean.Skill;
import bean.Software;

/**
 * @author Administrator
 *
 */
public class DataManagerImpl implements DataManager
{
	private Entry<String,Employee> user;
	private Entry<String,BusinessUnit> userUnit;
	private Entry<String,bean.System> userSystem;
	private Entry<String,Project> userProject;
	
	private Map<String,Employee> employeeMap;
	private Map<String,Software> softwareMap;
	private Map<String,bean.System> systemMap;
	private Map<String,Project> projectMap;
	private Map<String,BusinessUnit> unitMap;
	private DBConnectionImpl datacon;
	
	public DataManagerImpl()
	{
		datacon=new DBConnectionImpl();
	}
	
	@Override
	public Designation logIn(String employeeID, String password) throws InvalidCredentialsException
	{
		Designation designation=null;
		
		try(Connection connection=datacon.getConnection();)
		{
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
			Skill skill=Skill.valueOf(resultSet.getString(12).toUpperCase());
			String interviewFeedback=resultSet.getString(13);
			Date dateOfJoining=resultSet.getDate(14);
			
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
			Address address=new Address(street, country, city, state, pincode, landmark);
			
			Employee employee=new Employee
					(firstName,lastName,contactNo,emailID,dateOfBirth,gender,
					address,salary,systemID,unitID,projectID,designation
					,skill,interviewFeedback,dateOfJoining);
			user=new AbstractMap.SimpleEntry<String, Employee>(employeeID,employee);
			
			employeeMap=new HashMap<String,Employee>();
			
			if(designation==Designation.HR)
			{
				populateEmployeeData();
			}
			else if(designation==Designation.UNITHEAD)
			{
				populateEmployeeData(unitID);
			}
			
			populateSoftwareData();
			populateSystemData();
			populatePojectData();
			populateBusinessUnitData();
			
			populateUserData();
			
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return designation;
	}

	@Override
	public void logOut()
	{
		user=null;
		userUnit=null;
		userProject=null;
		userSystem=null;
		
		employeeMap=null;
		projectMap=null;
		softwareMap=null;
		systemMap=null;
		unitMap=null;
	}
	
	public void populateEmployeeData()
	{
		try(Connection connection=datacon.getConnection();)
		{
			PreparedStatement extractEmployees=connection.prepareStatement
					("SELECT * FROM employee WHERE NOT employee_id=?");
			
			String string = user.getKey();
			extractEmployees.setString(1,string);
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
				Skill skill=Skill.valueOf(resultSet.getString(12).toUpperCase());
				String interviewFeedback=resultSet.getString(13);
				Date dateOfJoining=resultSet.getDate(14);
			
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
				Address address=new Address(street, country, city, state, pincode, landmark);
				
				PreparedStatement extractDesignation=connection.prepareStatement
						("SELECT designation FROM credentials WHERE employee_id=?");
				extractDesignation.setString(1,employeeID);
				ResultSet resultSet3=extractDesignation.executeQuery();
				resultSet3.next();
				Designation designation=Designation.valueOf
						(resultSet3.getString(1).toUpperCase());
				
				Employee employee=new Employee
						(firstName,lastName,contactNo,emailID,dateOfBirth,gender,
						address,salary,systemID,unitID,projectID,designation
						,skill,interviewFeedback,dateOfJoining);
				
				employeeMap.put(employeeID,employee);
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void populateEmployeeData(String userUnitID)
	{
		try(Connection connection=datacon.getConnection();)
		{
			PreparedStatement extractEmployees=connection.prepareStatement
					("SELECT * FROM employee WHERE (NOT employee_id=?) AND unit_id=?");
			extractEmployees.setString(1,user.getKey());
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
				Skill skill=Skill.valueOf(resultSet.getString(12).toUpperCase());
				String interviewFeedback=resultSet.getString(13);
				Date dateOfJoining=resultSet.getDate(14);
			
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
				Address address=new Address(street, country, city, state, pincode, landmark);
				
				PreparedStatement extractDesignation=connection.prepareStatement
						("SELECT designation FROM credentials WHERE employee_id=?");
				extractDesignation.setString(1,employeeID);
				ResultSet resultSet3=extractDesignation.executeQuery();
				resultSet3.next();
				Designation designation=Designation.valueOf
						(resultSet3.getString(1).toUpperCase());
				
				Employee employee=new Employee
						(firstName,lastName,contactNo,emailID,dateOfBirth,gender,
						address,salary,systemID,unitID,projectID,designation
						,skill,interviewFeedback,dateOfJoining);
				
				employeeMap.put(employeeID,employee);
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public Map<String, Software> getSoftwareMap() {
		return softwareMap;
	}

	public void setSoftwareMap(Map<String, Software> softwareMap) {
		this.softwareMap = softwareMap;
	}

	public Map<String, bean.System> getSystemMap() {
		return systemMap;
	}

	public void setSystemMap(Map<String, bean.System> systemMap) {
		this.systemMap = systemMap;
	}

	public Map<String, Project> getProjectMap() {
		return projectMap;
	}

	public void setProjectMap(Map<String, Project> projectMap) {
		this.projectMap = projectMap;
	}

	public Map<String, BusinessUnit> getUnitMap() {
		return unitMap;
	}

	public void setUnitMap(Map<String, BusinessUnit> unitMap) {
		this.unitMap = unitMap;
	}

	public Map<String, Employee> getEmployeeMap() {
		
		return employeeMap;
		
	}
	
	public void populatePojectData()
	{
		projectMap=new HashMap<String, Project>();
		
		try(Connection connection=datacon.getConnection();)
		{
			Statement extractProjects=connection.createStatement();
			ResultSet resultSet=extractProjects.executeQuery
					("SELECT * FROM project");
			while(resultSet.next())
			{
				Map<String,Software> requiredSoftwaresMap=new HashMap<String,Software>();
				String projectID=resultSet.getString(1);
				String projectName=resultSet.getString(2);
				String managerID=resultSet.getString(3);
				String unitID=resultSet.getString(4);
				int vacancies=resultSet.getInt(5);
				Skill skill=Skill.valueOf(resultSet.getString(6).toUpperCase());
				
				PreparedStatement extractRequiredSoftwares=connection.prepareStatement
						("SELECT software_id FROM project_software_requirement"
								+ " WHERE project_id=?");
				extractRequiredSoftwares.setString(1,projectID);
				ResultSet resultSet2=extractRequiredSoftwares.executeQuery();
				while(resultSet2.next())
				{
					String softwareID=resultSet2.getString(1);
					Software software=softwareMap.get(softwareID);
					requiredSoftwaresMap.put(softwareID,software);
				}
				
				Project project=new Project(projectName,managerID,
						requiredSoftwaresMap,vacancies,skill);
				projectMap.put(projectID,project);
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void populateSystemData()
	{
		systemMap=new HashMap<String,bean.System>();
		
		try(Connection connection=datacon.getConnection();)
		{
			Statement extractSystems=connection.createStatement();
			ResultSet resultSet=extractSystems.executeQuery
					("SELECT * FROM system");
			while(resultSet.next())
			{
				String systemID=resultSet.getString(1);
				String systemStatus=resultSet.getString(2);
				boolean isAvailable=false;
				if(systemStatus.equals("Available")) isAvailable=true; 
				String operatingSystem=resultSet.getString(3);
				String ipAddress=resultSet.getString(4);
				
				Map<String,Software> curSoftwareMap=new HashMap<String,Software>();
				PreparedStatement extractInstalledSoftwares=connection.prepareStatement
						("SELECT software_id FROM system_software_installation"
								+ " WHERE system_id=?");
				extractInstalledSoftwares.setString(1,systemID);
				ResultSet resultSet2=extractInstalledSoftwares.executeQuery();
				while(resultSet2.next())
				{
					String softwareID=resultSet2.getString(1);
					Software software=softwareMap.get(softwareID);
					curSoftwareMap.put(softwareID,software);
				}
				
				bean.System system=new bean.System(isAvailable,operatingSystem, 
						ipAddress,curSoftwareMap);
				systemMap.put(systemID,system);
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void populateSoftwareData()
	{
		softwareMap = new HashMap<String,Software>();
		Software s;
		try(Connection connection=datacon.getConnection();)
		{
			PreparedStatement populateSoftware = connection.prepareStatement(
					"SELECT * FROM software");
			ResultSet softwares = populateSoftware.executeQuery();
			while(softwares.next()){
				String softwareID = softwares.getString(1);
				String softwareName = softwares.getString(2);
				s= new Software(softwareName);
				softwareMap.put(softwareID, s);
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void populateBusinessUnitData()
	{
		unitMap = new HashMap<String, BusinessUnit>();
		
		try(Connection connection=datacon.getConnection();)
		{
			Statement extractUnitID = connection.createStatement();
			ResultSet resultSet = extractUnitID.executeQuery(
					"SELECT * FROM business_unit");
			while(resultSet.next())
			{
				String unit_id = resultSet.getString(1);
				String unit_name = resultSet.getString(2);
				String unitHeadID = resultSet.getString(3);
			
				PreparedStatement extractProjectDetails = 
					connection.prepareStatement("SELECT project_id FROM project WHERE unit_id=?");
				extractProjectDetails.setString(1, unit_id);
				ResultSet projectDetails = extractProjectDetails.executeQuery();
			
				Map<String, Project> projectData = new HashMap<String, Project>();
				
				while(projectDetails.next())
				{
					String projectID = projectDetails.getString(1);
					Project project = projectMap.get(projectID);
					projectData.put(projectID, project);
				}
				BusinessUnit businessUnit = new BusinessUnit
						(unit_name, unitHeadID, projectData);
				unitMap.put(unit_id, businessUnit);
			}
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void populateUserData()
	{
		Employee employee=user.getValue();
		
		String projectID=employee.getProjectID();
		String unitID=employee.getUnitID();
		String systemID=employee.getSystemID();
		
		Project project=projectMap.get(projectID);
		BusinessUnit unit=unitMap.get(unitID);
		bean.System system=systemMap.get(systemID);
		
		userProject=new AbstractMap.SimpleEntry<String,Project>(projectID,project);
		userUnit=new AbstractMap.SimpleEntry<String,BusinessUnit>(unitID,unit);
		userSystem=new AbstractMap.SimpleEntry<String,bean.System>(systemID,system);
	}

	public List<Entry<String,Employee>> filterEmployeesBySkill(Skill skill) throws Exception
	{
		Set<Entry<String, Employee>> filteredSet = employeeMap.entrySet();
		List<Entry<String,Employee>> filteredList=new ArrayList<Entry<String,Employee>>(); 
		for (Iterator<Entry<String,Employee>> it = filteredSet.iterator(); it.hasNext();)
		{
			Entry<String,Employee> entry = it.next();
			Employee employee=entry.getValue();
			if(employee.getSkill()==skill) filteredList.add(entry);
		}
		
		return filteredList;
	}
	
	public void sendToUnitHead()
	{
		Calendar today=Calendar.getInstance();
		java.util.Date todayDate=today.getTime();
		
		Set<Map.Entry<String,Employee>> set=employeeMap.entrySet();
		
		for (Entry<String, Employee> entry : set)
		{	
			Employee employee=entry.getValue();
			String employeeID=entry.getKey();
			if(todayDate.before((java.util.Date)employee.getDateOfJoining()))
			{
				assignUnit(employeeID,"U00001");
			}
		}
	}
	
	public void assignUnit(String employeeID,String unitID)
	{
		try(Connection connection=datacon.getConnection();)
		{
			PreparedStatement updateEmployeeUnit = connection.prepareStatement
					("UPDATE employee_allotment.employee"
							+ " SET unit_id=? where employee_id=?");
			updateEmployeeUnit.setString(1,unitID);
			updateEmployeeUnit.setString(2,employeeID);
			updateEmployeeUnit.executeUpdate();
			
			Employee employee=employeeMap.get(employeeID);
			employee.setUnitID(unitID);
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void assignToProject(String employeeID, String projectID)
			throws NoProjectVacancyException, SystemNotAvailableException
	{
		Project project = projectMap.get(projectID);
		if(project.getVacancies()<1) throw new NoProjectVacancyException();
		project.decrementVacancies(1);
		
		Employee employee = employeeMap.get(employeeID);
		employee.setProjectID(projectID);
		
		try(Connection connection = datacon.getConnection())
		{
			PreparedStatement updateEmployee = 
					connection.prepareStatement
					("UPDATE employee SET project_id=? WHERE employee_id=?");
			updateEmployee.setString(1, projectID);
			updateEmployee.setString(2, employeeID);
			updateEmployee.executeUpdate();
			
			PreparedStatement updateProject = connection.prepareStatement
					("UPDATE project SET vacancies=?"
							+ " WHERE project_id=?");
			updateProject.setString(1, String.valueOf(project.getVacancies()));
			updateProject.setString(2, projectID);
			updateProject.executeUpdate();
			
			Set<Entry<String,bean.System>> systemEntrySet=systemMap.entrySet();
			int required=softwareMap.size();
			String assignedSystemID=null;
			for(Entry<String,bean.System> entry : systemEntrySet)
			{
				bean.System system=entry.getValue();
				if(!system.isAvailable()) continue;
				String systemID=entry.getKey();
				int curRequired=system.getRequiredSoftwareNumber(project);
				if(curRequired<required)
				{
					required=curRequired;
					assignedSystemID=systemID;
				}
			}
			if(assignedSystemID==null) throw new SystemNotAvailableException();
			assignToSystem(employeeID, assignedSystemID);
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	

	public Employee searchEmployeeByID(String employeeID) throws InvalidEmployeeIDException
	{
		Employee employee = employeeMap.get(employeeID);
		
		if (employee==null)
		{
			
			if(employeeID.equals(user.getKey()))
			{
				employee = user.getValue();
			}
			else
			{
				throw new InvalidEmployeeIDException();
			}
		}
		return employee;
	}
	
	public Project searchProjectByID(String projectID) throws InvalidProjectIDException
	{
		Project project = projectMap.get(projectID);
		return project;
	}
	
	public Software searchSoftwareByID(String softwareID) throws InvalidSoftwareIDException
	{
		Software software = softwareMap.get(softwareID);
		if(software==null) throw new InvalidSoftwareIDException();
		return software;
	}
	
	public bean.System searchSystemByID(String systemID) throws InvalidSystemIDException
	{
		bean.System system = systemMap.get(systemID);
		if(system==null) throw new InvalidSystemIDException();
		return system;
	}
	
	public BusinessUnit searchUnitByID(String unitID) throws InvalidBusinessUnitIDException
	{
		BusinessUnit unit = unitMap.get(unitID);
		if(unit==null) throw new InvalidBusinessUnitIDException();
		return unit;
	}
	
	public Set<Entry<String,Employee>> searchEmployeesBySkill(Skill skill)
	{
		Set<Entry<String,Employee>> filteredSet=new HashSet<Entry<String,Employee>>();
		
		Set<Entry<String,Employee>> set=employeeMap.entrySet();
		for(Entry<String,Employee> e : set)
		{
			Employee employee=e.getValue();
			Skill employeeSkill=employee.getSkill();
			if(skill==employeeSkill)
			{
				filteredSet.add(e);
			}
		}
		
		return filteredSet;
	}

	public void assignToSystem(String employeeID, String systemID)
	{
		Employee employee=employeeMap.get(employeeID);
		
		bean.System system=systemMap.get(systemID);
		
		employee.setSystemID(systemID);
	
		system.setAvailable(false);
		
		try(Connection connection = datacon.getConnection()){
			PreparedStatement updateEmployee = 
					connection.prepareStatement
					("UPDATE employee SET system_id=? WHERE employee_id=?");
			updateEmployee.setString(1, systemID);
			updateEmployee.setString(2, employeeID);
			updateEmployee.executeUpdate();
			
			PreparedStatement updateSystem = connection.prepareStatement
					("UPDATE system SET system_status=?"
							+ " WHERE system_id=?");
			updateSystem.setString(1, "NotAvailable");
			updateSystem.setString(2, systemID);
			updateSystem.executeUpdate();
		}
		catch (SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void changePassword(String password1)
	{
		try(Connection connection=datacon.getConnection())
		{
			PreparedStatement updatePassword=connection.prepareStatement
					("UPDATE credentials SET password=? WHERE employee_id=?");
			updatePassword.setString(1,password1);
			updatePassword.setString(2,user.getKey());
			updatePassword.executeUpdate();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}
