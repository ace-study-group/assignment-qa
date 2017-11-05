package UserStories;

import static org.junit.Assert.assertEquals;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import UserStories.PetPage;
import junit.framework.Assert;
import Backbone.Page;
import Backbone.Screenshot;
import Backbone.Singleton; 


public class Tests {

	public static WebDriver driver;


	@SuppressWarnings("deprecation")
	public static void US01(WebDriver driver) throws InterruptedException
	{
		try
		{
			String datePattern="\\d{2}-\\d{2}-\\d{4}";
			Boolean isDate2; 
			PetPage petPage=new PetPage(driver);
			Date dateValidate; 
			String UserStoryID="US";
			String colour; 
			// find element WebElement header=element.getCssValue("background-color"); 
			//petPage.checkHeader();
			colour=petPage.headerDetails();
			
			String[] hexValue = colour.replace("rgba(", "").replace(")", "").split(",");
			 
			int hexValue1=Integer.parseInt(hexValue[0]);
			hexValue[1] = hexValue[1].trim();
			int hexValue2=Integer.parseInt(hexValue[1]);
			hexValue[2] = hexValue[2].trim();
			int hexValue3=Integer.parseInt(hexValue[2]);
			 
			String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
			Assert.assertEquals("#000000", actualColor);
			
			UserStoryID=petPage.getTextHeader();
			String[] parts = UserStoryID.split(":"); 
			String newDate=parts[1];  
			
			if (isDate2=newDate.matches(datePattern))
			{
				System.out.println("Passed Date");
			}
			else
			{
				System.out.println("Date Failed");
				Throwable ex; 
			}
			
		}
		catch (Exception ex)
		{
			//Save to error file
			Singleton.status="Failed";
		}
		finally
		{
			//Save to specific tool
			//This can be used with current company tool
			//Integration
		}
		
	}

	public static void US02(WebDriver driver) throws Exception
	{
		String ex="Failure to load!";
		try
		{
			for (int i=0;i<50; i++)
			{
				US03(driver, "automation " + i, "Automated" );
			} 
			Singleton.status="Passed";
			PetPage petPage = new PetPage(driver); 
			long start = System.currentTimeMillis();
			driver.get("https://qa-petstore.herokuapp.com");
			long finish = System.currentTimeMillis();
			long totalTime = finish - start; 
			System.out.println("Total Time for page load - "+totalTime); 
			if (totalTime<2000)
			{
				System.out.println("Total Time Accepted"); 
					
			}
			else
			{
				throw new Exception(ex) ;
			}
		}
		catch (Exception e)
		{
			//Save to error file
			Singleton.status="Failed"; 
		}
		finally
		{
			//Save to specific 
		}
	}
	
	public static void US03(WebDriver driver, String petName, String petStatus) throws Exception
	{
		try
		{
			Singleton.status="Passed"; 
			PetPage petPage = new PetPage(driver); 
			petPage.populateName(petName);
			petPage.populateStatus(petStatus);
			petPage.buttonCreate();
			petPage.populateName("EnterKey");
			petPage.populateStatus(petStatus);
			petPage.populateName("\n");
			petPage.populateName("TabPet \t");
			petPage.populateStatus(petStatus + "tabbed Press");
			petPage.buttonCreate();
		}
		catch (Exception ex)
		{
			//Save to error file
			Singleton.status="Failed"; 
		}
		finally
		{
			//Save to specific 
		}
	}
	
	public static void US04(WebDriver driver, String petName, String petStatus) throws Exception
	{
		try
		{
			int key=0;
			Singleton.status="Passed";
			PetPage petPage = new PetPage(driver); 
			petPage.populateName("ChangePetName");
			petPage.populateStatus(petStatus);
			petPage.buttonCreate();
			key=(KeyEvent.VK_ESCAPE);
			WebElement changeName=driver.findElement(By.xpath("//span[contains(.,'" + petName + "')]")); 
			changeName.clear();
			changeName.sendKeys("NewPet");
			petPage.buttonCreate();
			
		}
		catch (Exception ex)
		{
			//Save to error file
			Singleton.status="Failed"; 
		}
		finally
		{
			//Save to specific 
		}
	}
	
	
}