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
public class BusinessUnit
{
	//private String unitID;
	private String unitName;
	private String unitHeadID;
	private Map<String,Project> projectMap;
	
	public BusinessUnit(/*String unitID,*/ String unitName, String unitHeadID,
			Map<String, Project> projectMap)
	{
		//this.unitID = unitID;
		this.unitName = unitName;
		this.unitHeadID = unitHeadID;
		this.projectMap = projectMap;
	}
	/*
	public String getUnitID()
	{
		return unitID;
	}
	
	public void setUnitID(String unitID)
	{
		this.unitID = unitID;
	}
	*/
	public String getUnitName()
	{
		return unitName;
	}
	
	public void setUnitName(String unitName) 
	{
		this.unitName = unitName;
	}
	
	public Map<String,Project> getSoftwareMap()
	{
		return projectMap;
	}
	
	public void setSoftwareMap(Map<String,Project> projectMap)
	{
		this.projectMap = projectMap;
	}

	public String getUnitHeadID()
	{
		return unitHeadID;
	}
	
	public void setUnitHeadID(String unitHeadID)
	{
		this.unitHeadID = unitHeadID;
	}
	
	@Override
	public String toString()
	{
		java.lang.System.out.println("BUSSINESS UNIT INFORMATION");
		java.lang.System.out.println("---------------------");
		java.lang.System.out.println("\t UNIT NAME: "+unitName);
		java.lang.System.out.println("\t UNIT HEAD ID: "+unitHeadID);
		java.lang.System.out.println("\t LIST OF PROJECTS:");
		java.lang.System.out.println("---------------------");
		Set<Entry<String,Project>> projectSet=projectMap.entrySet();
		for(Entry<String,Project> entry : projectSet)
		{
			Project project=entry.getValue();
			String projectName=project.getProjectName();
			java.lang.System.out.println("\t\t "+projectName);
		}
		return "";
	}
}