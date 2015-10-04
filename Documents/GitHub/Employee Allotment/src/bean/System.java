/**
 * 
 */
package bean;

import java.util.Map;

/**
 * @author Administrator
 *
 */
public class System
{
	private String systemID;
	private boolean isAvailable;
	private String operatingSystem;
	private String ipAddress;
	private Map<String,String> softwareMap;
	
	public System(String systemID, boolean isAvailable, String operatingSystem,
			String ipAddress, Map<String, String> softwareMap)
	{
		this.systemID = systemID;
		this.isAvailable = isAvailable;
		this.operatingSystem = operatingSystem;
		this.ipAddress = ipAddress;
		this.softwareMap = softwareMap;
	}

	public String getSystemID()
	{
		return systemID;
	}
	
	public void setSystemID(String systemID)
	{
		this.systemID = systemID;
	}
	
	public boolean isAvailable()
	{
		return isAvailable;
	}
	
	public void setAvailable(boolean isAvailable)
	{
		this.isAvailable = isAvailable;
	}
	
	public String getOperatingSystem()
	{
		return operatingSystem;
	}
	
	public void setOperatingSystem(String operatingSystem)
	{
		this.operatingSystem = operatingSystem;
	}
	
	public String getIpAddress()
	{
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	
	public Map<String,String> getSoftwareMap()
	{
		return softwareMap;
	}
	
	public void setSoftwareMap(Map<String,String> softwareMap)
	{
		this.softwareMap = softwareMap;
	}
	
	@Override
	public String toString()
	{
		return systemID+" "+ isAvailable+" "+ operatingSystem+" "+ ipAddress+" "+ softwareMap;	
	}
}