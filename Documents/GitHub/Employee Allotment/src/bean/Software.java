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
	private String softwareName;
	
	public Software(String softwareName)
	{
		this.softwareName = softwareName;
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
		java.lang.System.out.println("SOFTWARE INFORMATION:");
		java.lang.System.out.println("---------------------");
		java.lang.System.out.println("\t\t SOFTWARE NAME: "+softwareName);
		
		return "";
	}
}
