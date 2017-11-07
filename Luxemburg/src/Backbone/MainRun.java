package Backbone;

import java.util.List;

import javax.xml.bind.annotation.*;

/**
 * 
 * @author jcabral on 12 . 04 . 2017 
 *
 */

@XmlRootElement(name="AutomationProjects")
@XmlAccessorType(XmlAccessType.FIELD)

public class MainRun {
	@XmlElement(name="ProjectDetails")
	private List<MainXML> mainRun; 
	
	public List<MainXML> getMainRun()
	{
		return mainRun; 
	}
	
	public void setMainRun(List<MainXML> mainRun)
	{
		this.mainRun = mainRun; 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
