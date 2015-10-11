package userinterface;

import util.DataManagerImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

import exceptions.InvalidBusinessUnitIDException;
import exceptions.InvalidCredentialsException;
import exceptions.InvalidEmployeeIDException;
import exceptions.InvalidProjectIDException;
import exceptions.InvalidSoftwareIDException;
import exceptions.InvalidSystemIDException;
import exceptions.NoProjectVacancyException;
import exceptions.SystemNotAvailableException;
import bean.BusinessUnit;
import bean.Designation;
import bean.Employee;
import bean.Project;
import bean.Skill;
import bean.Software;

public class UserInterface
{
	private DataManagerImpl data;
	private BufferedReader in;
	private Designation designation;
	private JFrame frame,passwordChangeFrame;
	private int attempts;
	private String employeeID;
	private boolean loginSuccess;
	private int passwordChangeStatus;
	
	UserInterface()
	{		
		loginSuccess=false;
		attempts=0;
		data=new DataManagerImpl();
		in=new BufferedReader(new InputStreamReader(System.in));
		passwordChangeStatus=0;
		
		frame = new JFrame("LOGIN");
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		passwordChangeFrame = new JFrame("LOGIN");
		passwordChangeFrame.setSize(300, 150);
		passwordChangeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		
		JPanel panel2 = new JPanel();
		passwordChangeFrame.add(panel2);

		panel.setLayout(null);
		
		panel2.setLayout(null);

		JLabel userLabel = new JLabel("Employee ID");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		panel.add(loginButton);
		
		loginButton.addActionListener(new ActionListener()
		{
			 
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				try
				{
					employeeID=userText.getText();
					String password=getMD5EncryptedPassword
							(new String(passwordText.getPassword()));
					
					designation=data.logIn(employeeID,password);
					
					userText.setText("");
					passwordText.setText("");
					
					loginSuccess=true;
				}
				catch(InvalidCredentialsException e)
				{
					userText.setText("");
					passwordText.setText("");
					System.out.println("Invalid LogIn Credentials.");
					attempts++;
				}
				catch(NoSuchAlgorithmException e){}
			
				frame.dispose();
			}
		});
		
		
		JLabel password1Label = new JLabel("Enter Password");
		password1Label.setBounds(10, 10, 80, 25);
		panel2.add(password1Label);

		JPasswordField passwordText1 = new JPasswordField(20);
		passwordText1.setBounds(100, 10, 160, 25);
		panel2.add(passwordText1);

		JLabel password2Label = new JLabel("Re-Enter Password");
		password2Label.setBounds(10, 40, 80, 25);
		panel2.add(password2Label);

		JPasswordField passwordText2 = new JPasswordField(20);
		passwordText2.setBounds(100, 40, 160, 25);
		panel2.add(passwordText2);

		JButton changeButton = new JButton("Change Password");
		changeButton.setBounds(10, 80, 80, 25);
		panel2.add(changeButton);
		
		changeButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					String password1=getMD5EncryptedPassword
							(new String(passwordText1.getPassword()));
					String password2=getMD5EncryptedPassword
							(new String(passwordText2.getPassword()));
					
					if(password1.equals(password2))
					{
						data.changePassword(password1);
						passwordChangeStatus=1;
					}
					else
					{
						passwordChangeStatus=2;
					}
				}
				catch(NoSuchAlgorithmException e)
				{}
				
				passwordChangeFrame.dispose();
			}
			
		});
	}
	
	private String getMD5EncryptedPassword(String passwordToHash)
			throws NoSuchAlgorithmException
	{
		// Create MessageDigest instance for MD5
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //Add password bytes to digest
        messageDigest.update(passwordToHash.getBytes());
        //Get the hash's bytes
        byte[] bytes = messageDigest.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
        	stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //Return complete hashed password in hex format
        return stringBuilder.toString();
	}
	
	public static void main(String[] args) throws Exception
	{	//*
		UserInterface ui=new UserInterface();
		ui.display();
		//*/
	}
	
	public void display() throws IOException, InvalidEmployeeIDException, InvalidBusinessUnitIDException, InvalidSystemIDException, InvalidProjectIDException
	{
		while(true)
		{
			System.out.println("1. Log In"
							+ "\n2. Exit");
			if(readInt()==2) break;
			
			do
			{
				frame.setVisible(true);
			}
			while(attempts<3 && loginSuccess==false);
			
			if(attempts==3)
			{
				System.out.println("Log In attempt failed 3 times. Please try again later.");
				continue;
			}
			
			if(designation==Designation.EMPLOYEE)
			{
				int ch=0;
				Employee cur=null;
				try
				{
					cur=data.searchEmployeeByID(employeeID);
				}
				catch(InvalidEmployeeIDException e)
				{
					e.printStackTrace();
				}
				System.out.println("Welcome "+cur.getFirstName()+" "+cur.getLastName());
				do
				{
					System.out.println("1. Display my data"
									+ "\n2. Change password"
									+ "\n3. Log out");
					ch=readInt();
					if(ch==1)
					{
						displayEmployee(employeeID);
					}
					else if(ch==2)
					{
						passwordChangeFrame.setVisible(true);
						System.out.println();
						while(passwordChangeStatus==0)
						{
						}
						if(passwordChangeStatus==1)
						{
							System.out.println("Password changed successfully.");
						}
						else
						{
							System.out.println("Passwords do not match. "
									+ "Please try again later.");
						}
						passwordChangeStatus=0;
					}
					else if(ch==3)
					{
						attempts=0;
						loginSuccess=false;
						designation=null;
						employeeID=null;
						data.logOut();
					}
				}while(ch!=3);
			}
			else if(designation==Designation.HR)
			{	
				int ch = 0;
				Employee cur=null;
				try
				{
					cur=data.searchEmployeeByID(employeeID);
				}
				catch(InvalidEmployeeIDException e)
				{
					e.printStackTrace();
				}
				System.out.println("Welcome "+cur.getFirstName()+" "+cur.getLastName());
				do 
				{
					System.out.println("1. Display my data"
									+ "\n2. Change password"
									+ "\n3. Display all employees"
									+ "\n4. Forward to MBU"
									+ "\n5. Log out");
					ch = readInt(); 
					
					if(ch==1)
					{
						displayEmployee(employeeID);
					}
					else if(ch==2)
					{
						passwordChangeFrame.setVisible(true);
						while(passwordChangeStatus==0);
						if(passwordChangeStatus==1)
						{
							System.out.println("Password changed successfully.");
						}
						else
						{
							System.out.println("Passwords do not match. "
									+ "Please try again later.");
						}
						passwordChangeStatus=0;
					}
					else if(ch == 3)
					{
						displayAllEmployees();
					}
					if(ch == 4)		//forward to MBU
					{
						data.sendToUnitHead();
					}
					else if(ch==5)
					{
						data.logOut();
						attempts=0;
						designation=null;
						employeeID=null;
						loginSuccess=false;
					}
				} while (ch != 5);
			}
			else
			{
				int ch = 0;
				Employee cur=null;
				try 
				{
					cur = data.searchEmployeeByID(employeeID);
				}
				catch (InvalidEmployeeIDException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Welcome "+cur.getFirstName()+" "+cur.getLastName());
				do 
				{	
					System.out.println("1. Display my data"
									+ "\n2. Change password"
									+ "\n3. Display all employees"
									+ "\n4. Assign Employee to project"
									+ "\n5. Log out");
					ch = readInt();
					
					if(ch==1)
					{
						displayEmployee(employeeID);
					}
					else if(ch==2)
					{
						passwordChangeFrame.setVisible(true);
						while(passwordChangeStatus==0);
						if(passwordChangeStatus==1)
						{
							System.out.println("Password changed successfully.");
						}
						else
						{
							System.out.println("Passwords do not match. "
									+ "Please try again later.");
						}
						passwordChangeStatus=0;
					}
					else if(ch == 3)
					{
						displayAllEmployees();
					}
					if(ch == 4)
					{
						displayAvailableProjects();
						System.out.println("Please enter the project id:");
						String projectID=readString();
						Project project;
						String empID=null;
						try 
						{
							project = data.searchProjectByID(projectID);
							Skill skill=project.getSkill();
							Set<Entry<String,Employee>> filteredSet=
									data.searchEmployeesBySkill(skill);
							for(Entry<String,Employee> e : filteredSet)
							{
								empID=e.getKey();
								displayEmployee(empID);
								System.out.println("1. Accept"
												+ "\n2. Reject");
								int choice=readInt();
								if(choice==1)
								{
									data.assignToProject(empID,projectID);
									System.out.println("Employee is successfully alloted to the "
											+ "specified project.");
									System.out.println("Updated Employee Data : ");
									displayEmployee(empID);
								}
								else
								{
									data.assignUnit(empID,"U00000");
									System.out.println("Employee Rejected.");
								}
							}
						} 
						catch (InvalidProjectIDException e) 
						{
							System.out.println("Invalid Project ID.");
						}
						catch (NoProjectVacancyException e)
						{
							System.out.println("The selected project has no vacancies. "
									+ "Unable to assign employee to this project.");
						} 
						catch(SystemNotAvailableException e) 
						{
							System.out.println("Employee is successfully alloted to the "
									+ "specified project.");
							System.out.println("Updated Employee Data : ");
							displayEmployee(empID);
							System.out.println("NOTE : No system is available at the moment. "
									+ "Hence the employee is not assigned any system yet.");
						}
						
					}
					else if(ch==5)
					{
						attempts=0;
						loginSuccess=false;
						data.logOut();
					}
				}while(ch != 5);
			}
		}
	}
	
	private void displayAvailableSystems() throws InvalidSystemIDException
	{
		for(Map.Entry<String, bean.System> m : data.getSystemMap().entrySet())
		{
			bean.System system=m.getValue();
			if(system.isAvailable())
			{
				displaySystem(m.getKey());
			}
		}
	}

	public void displayEmployee(String employeeID) throws InvalidEmployeeIDException, InvalidBusinessUnitIDException, InvalidProjectIDException, InvalidSystemIDException
	{ 
		System.out.println("Employee ID: "+employeeID);
		Employee employee=data.searchEmployeeByID(employeeID);
		System.out.println(employee);
		
		System.out.println("ALLOTTED RESOURCES AND UNITS");
		java.lang.System.out.println("---------------------");
		
		String projectID=employee.getProjectID();
		String unitID=employee.getUnitID();
		String systemID=employee.getSystemID();
		
		if(unitID!=null)
		{
			BusinessUnit unit=data.searchUnitByID(unitID);
			String unitName=unit.getUnitName();
			System.out.println("\t BUSINESS UNIT: "+unitName);
		}
		else
		{
			System.out.println("\t BUSINESS UNIT: Not Allocated");
		}
		if(projectID!=null)
		{
			Project project=data.searchProjectByID(projectID);
			String projectName=project.getProjectName();
			System.out.println("\t PROJECT: "+projectName);
		}
		else
		{
			System.out.println("\t PROJECT: Not Allocated");
		}
		if(systemID!=null)
		{
		
			bean.System system=data.searchSystemByID(systemID);
			String ipAddress=system.getIpAddress();
			System.out.println("\t SYSTEM IP ADDRESS: "+ipAddress);
		}
		else
		{
			System.out.println("\t SYSTEM IP ADDRESS: Not Allocated");
		}
		System.out.println("------------------------------------------");
	}
	
	public void displayBusinessUnit(String unitID) throws InvalidBusinessUnitIDException
	{
		java.lang.System.out.println("BUSSINESS UNIT ID: "+unitID);
		BusinessUnit businessUnit=data.searchUnitByID(unitID);
		System.out.println(businessUnit);
		System.out.println("------------------------------------------");
	}
	
	public void displayProject(String projectID) throws InvalidProjectIDException
	{	
		
		System.out.println("PROJECT ID: "+projectID);
		Project project=data.searchProjectByID(projectID);
		System.out.println(project);
		System.out.println("------------------------------------------");
	}
	
	public void displayAllEmployees() throws InvalidEmployeeIDException, InvalidBusinessUnitIDException, InvalidProjectIDException, InvalidSystemIDException
	{
		for(Map.Entry<String, Employee> m : data.getEmployeeMap().entrySet())
		{
			displayEmployee(m.getKey());
		}
	}
	
	public void displayAllProjects() throws InvalidProjectIDException
	{
		for(Map.Entry<String, Project> m : data.getProjectMap().entrySet())
		{
			displayProject(m.getKey());
		}
	}
	
	public void displayAvailableProjects() throws InvalidProjectIDException
	{
		for(Map.Entry<String, Project> m : data.getProjectMap().entrySet())
		{
			Project project=m.getValue();
			if(project.getVacancies()>0)
			{
				displayProject(m.getKey());
			}
		}
	}
	
	public void displayAllSystems() throws InvalidSystemIDException
	{
		for(Map.Entry<String, bean.System> m : data.getSystemMap().entrySet())
		{
			displaySystem(m.getKey());
		}
	}
	
	public void displayAllSoftwares() throws InvalidSoftwareIDException
	{
		for(Map.Entry<String, Software> m : data.getSoftwareMap().entrySet())
		{
			displaySoftware(m.getKey());
		}
	}
	
	public void displayAllUnits() throws InvalidBusinessUnitIDException
	{
		for(Map.Entry<String, Employee> m : data.getEmployeeMap().entrySet())
		{
			displayBusinessUnit(m.getKey());
		}
	}
	
	public void displaySystem(String systemID) throws InvalidSystemIDException
	{ 
		System.out.println("SYSTEM ID: "+systemID);
		bean.System system=data.searchSystemByID(systemID);
		System.out.println(system);
		System.out.println("------------------------------------------");
	}
	
	public void displaySoftware(String softwareID) throws InvalidSoftwareIDException
	{
		System.out.println("SOFTWARE ID: "+softwareID);
		Software software=data.searchSoftwareByID(softwareID);
		System.out.println(software);
		System.out.println("------------------------------------------");
	}
	
	public int readInt() throws IOException
	{
		return Integer.parseInt(in.readLine());
	}
	
	public String readString() throws IOException
	{
		return in.readLine();
	}
}
