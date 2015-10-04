/**
 * 
 */
package bean;

import java.util.Map;

/**
 * @author Administrator
 *
 */
public class Project
{
	private String projectID;
	private String projectName;
	private String managerID;
	private Map<String, String> softwareMap;
	
	public Project(String projectID, String projectName, String managerID,
			Map<String, String> softwareMap)
	{
		this.projectID = projectID;
		this.projectName = projectName;
		this.managerID = managerID;
		this.setSoftwareMap(softwareMap);
	}

	public String getProjectID()
	{
		return projectID;
	}
	
	public void setProjectID(String projectID)
	{
		this.projectID = projectID;
	}
	
	public String getProjectName()
	{
		return projectName;
	}
	
	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}
	
	public String getManagerID()
	{
		return managerID;
	}
	
	public void setManagerID(String managerID)
	{
		this.managerID = managerID;
	}

	public Map<String, String> getSoftwareMap()
	{
		return softwareMap;
	}

	public void setSoftwareMap(Map<String, String> softwareMap)
	{
		this.softwareMap = softwareMap;
	}
	
	@Override
	public String toString()
	{
		return projectID+" "+projectName+" "+ managerID+" "+softwareMap;
	}
}
