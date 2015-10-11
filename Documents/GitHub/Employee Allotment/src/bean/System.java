/**
 * 
 */
package bean;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Administrator
 *
 */
public class System
{
	private boolean isAvailable;
	private String operatingSystem;
	private String ipAddress;
	private Map<String,Software> softwareMap;
	
	public System(boolean isAvailable, String operatingSystem,
			String ipAddress, Map<String, Software> softwareMap)
	{
		this.isAvailable = isAvailable;
		this.operatingSystem = operatingSystem;
		this.ipAddress = ipAddress;
		this.softwareMap = softwareMap;
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
	
	public Map<String,Software> getSoftwareMap()
	{
		return softwareMap;
	}
	
	public void setSoftwareMap(Map<String,Software> softwareMap)
	{
		this.softwareMap = softwareMap;
	}
	
	@Override
	public String toString()
	{
		bean.System s=null;
		java.lang.System.out.println("SYSTEM INFORMATION");
		java.lang.System.out.println("---------------------");
		java.lang.System.out.println("\t  IP ADDRESS:"+ipAddress);
		java.lang.System.out.println("\t OPERATING SYSTEM:"+operatingSystem);
		java.lang.System.out.print("\t SYSTEM STATUS: ");
		if(isAvailable) java.lang.System.out.println("Available");
		else java.lang.System.out.println("Not Available");
		java.lang.System.out.println("\t LIST OF INSTALLED SOFTWARES: ");
		java.lang.System.out.println("---------------------");
		Set<Entry<String,Software>> softwareSet=softwareMap.entrySet();
		for(Entry<String,Software> entry : softwareSet)
		{
			Software software=entry.getValue();
			String softwareName=software.getSoftwareName();
			java.lang.System.out.println("\t\t "+softwareName);
		}
		
		return "";
	}
	
	public int getRequiredSoftwareNumber(Project project)
	{
		int required=0;
		Map<String,Software> requiredSoftwareMap=project.getSoftwareMap();
		Set<String> requiredSoftwareIDs=requiredSoftwareMap.keySet();
		for(String softwareID : requiredSoftwareIDs)
		{
			if(!softwareMap.containsKey(softwareID))
			{
				required++;
			}
		}
		return required;
	}
}