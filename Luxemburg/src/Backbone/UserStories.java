package Backbone;

import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import UserStories.Tests;

public class UserStories {
	
	public static WebDriver driver; 
	
	public static void runtest(String petName, String petStatus) throws Exception
	{
		Singleton.status="Passed"; 
		String test;
		
		PrintWriter writer = new PrintWriter(new File("c://Tests//TestCases.log"));
		
		Screenshot screenshot=new Screenshot(); 
		Tests tests=new Tests();
		
		tests.US01(driver);
		test="Test US01 - " + Singleton.status;
		test=test + "\n";  
		screenshot.passScreenshot(driver, "Pet", "Test", "US01");
		
		tests.US03(driver, petName, petStatus);
		test=test+ "\n Test US03 - " + Singleton.status;
		test=test + "\n";  
		
		screenshot.passScreenshot(driver, "Pet", "Test", "US03");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		screenshot.passScreenshot(driver, "Pet", "Test", "US03_1");
		
		tests.US04(driver, "PetChange", "Delete");
		test=test + "\n Test US04 - " + Singleton.status;
		test=test + "\n";  
		screenshot.passScreenshot(driver, "Pet", "Test", "US04");
		
		tests.US02(driver);
		test=test + "\n Test US02 - " + Singleton.status;
		test=test + "\n";  
		screenshot.passScreenshot(driver, "Pet", "Test", "US02");

		writer.append(test); 
		writer.close();
		
		
		
	}

	public static void userStories(String browsers, String url)
	{
		
		int counter=0; 
		try 
		{
			//Check XML
			String petName="Test";
			String petStatus="Testing";
			String[] browsersRun = browsers.split(","); 
			System.out.println("Browsers Total :" + browsersRun[counter].length() + browsers);
			if("Chrome".equals(browsers))
			{
				driver=new ChromeDriver();
				driver.navigate().to(url);
				runtest(petName, petStatus);
				driver.quit();
			}
			if("FF".equals(browsers))
			{
				driver=new FirefoxDriver();
				driver.navigate().to(url);
				runtest(petName, petStatus);
				driver.quit();
			}
			if("Edge".equals(browsers))
			{
				driver=new EdgeDriver();
				driver.navigate().to(url);
				runtest(petName, petStatus);
				driver.quit();
				
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
}
