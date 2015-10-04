/**
 * 
 */
package bean;

import java.util.Map;

/**
 * @author Administrator
 *
 */
public class BusinessUnit
{
	private String unitID;
	private String unitName;
	private String unitHeadID;
	private Map<String,String> projectMap;
	
	public BusinessUnit(String unitID, String unitName, String unitHeadID,
			Map<String, String> projectMap)
	{
		this.unitID = unitID;
		this.unitName = unitName;
		this.unitHeadID = unitHeadID;
		this.projectMap = projectMap;
	}
	
	public String getUnitID()
	{
		return unitID;
	}
	
	public void setUnitID(String unitID)
	{
		this.unitID = unitID;
	}
	
	public String getUnitName()
	{
		return unitName;
	}
	
	public void setUnitName(String unitName) 
	{
		this.unitName = unitName;
	}
	
	public Map<String,String> getSoftwareMap()
	{
		return projectMap;
	}
	
	public void setSoftwareMap(Map<String,String> projectMap)
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
		return unitID+" "+ unitName+" "+ unitHeadID+ " "+ projectMap;
		
	}
}