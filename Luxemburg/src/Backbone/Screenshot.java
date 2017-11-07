package Backbone;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot
{
	Date temporaryDate; 
	
	public void passScreenshot(WebDriver driver, String client, String project, String testCase) throws IOException, ParseException
	{
		
		int count=1;
		SimpleDateFormat ndate=new SimpleDateFormat("ddMMyyyyHHmmss");
		Date newDate=Calendar.getInstance().getTime(); 
		temporaryDate=newDate; 
		String sNewDate=ndate.format(newDate);
		System.out.println(sNewDate);
		System.out.println(client + testCase + project + sNewDate);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("/Screenshots/"+client+"/"+project+"("+testCase+")"+".png"));
		count ++;
	}
	
	public String failScreenshot(WebDriver driver, String client, String project,  String testCase) throws IOException, ParseException
	{
		
		int count=1;
		SimpleDateFormat ndate=new SimpleDateFormat("ddMMyyyyHHmmss");
		Date newDate=Calendar.getInstance().getTime(); 
		temporaryDate=newDate; 
		String sNewDate=ndate.format(newDate);
		System.out.println(sNewDate);	
		System.out.println(client + project + sNewDate);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("/Screenshots/Fail/"+client+"/"+project+"("+count+")"+ ".png"));
		String location=("/Screenshots/Fail/"+client+"/"+project+"("+testCase+")"+ ".png");
		count ++;
		return location; 
	}
}