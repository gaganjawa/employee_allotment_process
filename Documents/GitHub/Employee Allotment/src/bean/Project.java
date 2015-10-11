/**
 * 
 */
package bean;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author Administrator
 *
 */
public class Project
{
	//private String projectID;
	private String projectName;
	private String managerID;
	private Map<String, Software> softwareMap;
	private int vacancies;
	private Skill skill;
	
	public Project(/*String projectID,*/ String projectName, String managerID,
			Map<String, Software> softwareMap,int vacancies,Skill skill)
	{
		//this.projectID = projectID;
		this.projectName = projectName;
		this.managerID = managerID;
		this.setSoftwareMap(softwareMap);
		this.setVacancies(vacancies);
		this.setSkill(skill);
	}

/*	public String getProjectID()
	{
		return projectID;
	}
	
	public void setProjectID(String projectID)
	{
		this.projectID = projectID;
	}
	*/
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

	public Map<String, Software> getSoftwareMap()
	{
		return softwareMap;
	}

	public void setSoftwareMap(Map<String, Software> softwareMap)
	{
		this.softwareMap = softwareMap;
	}
	
	@Override
	public String toString()
	{
		Project p=null;
		java.lang.System.out.println("PROJECT INFORMATION");
		java.lang.System.out.println("---------------------");
		java.lang.System.out.println("\t PROJECT NAME: "+projectName);
		java.lang.System.out.println("\t MANAGER ID: "+managerID);
		java.lang.System.out.println("\t VACANCIES:"+vacancies);
		java.lang.System.out.println("\t SKILL:"+skill);
		java.lang.System.out.println("\t LIST OF SOFTWARE REQUIRED:");
		java.lang.System.out.println("---------------------");
		Set<Entry<String,Software>> softwareSet=softwareMap.entrySet();
		for(Entry<String,Software> e : softwareSet)
		{
			Software software=e.getValue();
			String softwareName=software.getSoftwareName();
			java.lang.System.out.println("\t\t "+softwareName);
		}
		
		return "";
	}

	public int getVacancies() {
		return vacancies;
	}

	public void setVacancies(int vacancies) {
		this.vacancies = vacancies;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	public void decrementVacancies(int number)
	{
		vacancies-=number;
	}
}
