/**
 * 
 */
package bean;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class Person
{
	protected String firstName,lastName;
	protected String contactNo;
	protected String emailID;
	protected Date dateOfBirth;
	protected Gender gender;
	protected Address address;
	
	public Person(String firstName, String lastName, String contactNo,
			String emailID, Date dateOfBirth, Gender gender, Address address)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.emailID = emailID;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
	}

	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public String getContactNo()
	{
		return contactNo;
	}
	
	public void setContactNo(String contactNo)
	{
		this.contactNo = contactNo;
	}
	
	public String getEmailID()
	{
		return emailID;
	}
	
	public void setEmailID(String emailID)
	{
		this.emailID = emailID;
	}
	
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	
	public Gender getGender()
	{
		return gender;
	}
	
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	/*@Override
	public String toString()
	{
		return firstName+" "+lastName+" "+ contactNo+" "+ emailID+" "+ dateOfBirth+" "+gender+" "+address;
	}*/
}