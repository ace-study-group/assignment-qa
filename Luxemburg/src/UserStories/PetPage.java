package UserStories;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Backbone.Page;
import Backbone.Screenshot;


import Backbone.Failure;

public class PetPage extends Backbone.Page
{
	public PetPage(WebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private String textheader				;
	private String textBoxName 				;
	private String textBoxStatus			;
	private String buttonCreate				;
	private String textBoxListName			;
	private String textBoxListStatus		;
	private String buttonDelete				;
	private String petName					; 
	
	
	@Override
	public void loadDefinitions(){
		this.textheader					=  "textheader"				;
		this.textBoxName 				=  "textBoxName" 		    ; 
		this.textBoxStatus				=  "textBoxStatus"		    ; 
		this.buttonCreate				=  "buttonCreate"			; 
		this.textBoxListName			=  "textBoxListName"	    ; 
		this.textBoxListStatus			=  "textBoxListStatus"		; 
		this.buttonDelete				=  "buttonDelete"			;
		
		this.listOfByElements.put(textheader        , By.xpath("//div[@class='assignment-nav-item active']"));
		this.listOfByElements.put(textBoxName 		, By.xpath("//input[@class='form-control pet-name']"));
		this.listOfByElements.put(textBoxStatus		, By.xpath("//input[@class='form-control pet-status']")); 
		this.listOfByElements.put(buttonCreate		, By.xpath("//button[@class='btn btn-primary create']"));
		this.listOfByElements.put(textBoxListName	, By.xpath("//span[contains(.,'" + petName + "')]")); 
		this.listOfByElements.put(textBoxListStatus	, By.xpath("//span[@class='pet lbl pet-status']")); 
		this.listOfByElements.put(buttonDelete		, By.xpath("//button[contains(.,'Delete')]")); 
	}                                      
	
	public void changeName(String petName) throws IOException, ParseException
	{
		try
		{
			this.waitForLoad(driver);
			this.actionSendKey("textBoxListName", petName);
			
		}
		catch(Exception ex)
		{
			//process for handling failures
			//integrate results, or send out email to jira to create ticket
			System.out.println("Error in creating pet"); 
		}
	}
	
	
	public void populateName(String petName) throws IOException, ParseException
	{
		try
		{
			this.waitForLoad(driver);
			
			this.actionSendKey("textBoxName", petName);
			
		}
		catch(Exception ex)
		{
			//process for handling failures
			//integrate results, or send out email to jira to create ticket
			System.out.println("Error in creating pet"); 
		}
	}
	
	public void populateStatus(String petStatus) throws IOException, ParseException
	{
		try
		{
			this.waitForLoad(driver);
			
			this.actionSendKey("textBoxStatus", petStatus);
			
		}
		catch(Exception ex)
		{
			//process for handling failures
			//integrate results, or send out email to jira to create ticket
			System.out.println("Error in creating pet"); 
		}
	}
	
	public void createWithVariousOptions(String options) throws NoSuchElementException
	{
		try
		{
			if ("button".equals(options))
			{
				this.buttonCreate(); 
			}
			else
			if ("keys".equals(options))
			{
				this.actionSendKey("textBoxStatus", "\n");
				
			}
		}
		catch(Exception ex)
		{
			//process for handling failures
			//integrate results, or send out email to jira to create ticket
			System.out.println("Error in creating pet"); 
		}
	}
	
	public String headerDetails()
	{
		System.out.print(driver.findElement(listOfByElements.get(textheader)).getCssValue("background-color"));
		return (driver.findElement(listOfByElements.get(textheader)).getCssValue("background-color")); 
	}
	
	public boolean checkHeader()
	{
		System.out.print(driver.findElement(listOfByElements.get(textheader)).isEnabled());
		return (driver.findElement(listOfByElements.get(textheader)).isEnabled());
	}
	
	public String getTextHeader()
	{
		System.out.print(driver.findElement(listOfByElements.get(textheader)).getText()); 
		return (driver.findElement(listOfByElements.get(textheader)).getText());
	}
	
	public void buttonCreate() throws InterruptedException  
	{
		driver.findElement(listOfByElements.get(buttonCreate)).click(); 
	}
	
	
	
	
}

