/**
 * 
 */
package bean;

/**
 * @author Administrator
 *
 */

public class Address
{

	private String street;
	private String country;	
	private String city;
	private String state;
	private String pincode;
	private String landmark;
	
	public Address(String street, String country, String city, String state,
			String pincode, String landmark)
	{
		this.street = street;
		this.country = country;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.landmark = landmark;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public void setStreet_name(String street)
	{
		this.street = street;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public String getPincode()
	{
		return pincode;
	}
	
	public void setPincode(String pincode)
	{
		this.pincode = pincode;
	}
	
	public String getLandmark()
	{
		return landmark;
	}
	
	public void setLandmark(String landmark)
	{
		this.landmark = landmark;
	}
	
	@Override
	public String toString()
	{
		java.lang.System.out.println("ADDRESS INFORMATION");
		java.lang.System.out.println("---------------------");
		java.lang.System.out.println("\t STREET: "+street);
		java.lang.System.out.println("\t LANDMARK:"+landmark);
		java.lang.System.out.println("\t CITY:"+city);
		java.lang.System.out.println("\t STATE:"+state);
		java.lang.System.out.println("\t COUNTRY:"+country);
		java.lang.System.out.println("\t PINCODE:"+pincode);
		return "";
	}
}
