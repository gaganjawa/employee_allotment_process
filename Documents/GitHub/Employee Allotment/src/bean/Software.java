/**
 * 
 */
package bean;

/**
 * @author Administrator
 *
 */
public class Software
{
	private String softwareID;
	private String softwareName;
	
	public Software(String softwareID, String softwareName)
	{
		this.softwareID = softwareID;
		this.softwareName = softwareName;
	}

	public String getSoftwareID()
	{
		return softwareID;
	}
	
	public void setSoftwareID(String softwareID)
	{
		this.softwareID = softwareID;
	}
	
	public String getSoftwareName()
	{
		return softwareName;
	}
	
	public void setSoftwareName(String softwareName)
	{
		this.softwareName = softwareName;
	}
	
	public String toString()
	{
		return softwareID+" "+ softwareName ;
	}
}
