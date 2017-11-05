package Backbone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jcabral 12.04.2017
 * main XML class to run automation 
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ProjectDetails")

public class MainXML {
	
	private String Run;
	private String UserStory; 
	private String Type;
	private String Link;
	private String DataRequests; 
	private String Browsers; 
	

	public String getBrowsers() {
		return Browsers;
	}



	public void setBrowsers(String browsers) {
		Browsers = browsers;
	}



	@XmlAttribute 
	private long id;

	public String getRun() {
		return Run;
	}


	
	public String getUserStory() {
		return UserStory;
	}



	public void setUserStory(String userStory) {
		UserStory = userStory;
	}



	public String getType() {
		return Type;
	}



	public void setType(String type) {
		Type = type;
	}



	public String getLink() {
		return Link;
	}



	public void setLink(String link) {
		Link = link;
	}



	public String getDataRequests() {
		return DataRequests;
	}



	public void setDataRequests(String dataRequests) {
		DataRequests = dataRequests;
	}



	public void setRun(String run) {
		Run = run;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getId() {
		return id;
	}



}