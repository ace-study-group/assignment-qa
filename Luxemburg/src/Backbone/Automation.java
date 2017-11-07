package Backbone;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import UserStories.WebTests;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.OperaDriverManager;


public class Automation {

/**
 * @author Jorge Cabral - Developed April-May 2017
 * 		Main section to Open a Main XML
 * 		and run all clients according to the 
 * 		tags inside the XML Main run file
 * 		@throws ErrorFile.log 
 * 		@version 1.0 
 * 		@link MainXML.xml
 */
	static Date masterDate = new Date(0);
	static Date masterTime = new Date(0);
	static Date masterStop = new Date(0);
	//Optional run for CI
	public static String jenkinsRun="No";  
	//*******************
	public static String browsers; 
	public static String type;
    public static String userStory;
    public static String link; 
    public static String dataRequest;
    public static String petData;  
    public static String url; 
    public static String rest; 
    	
	public static void main(String[] args) throws Throwable
	{
		try
		{
			ChromeDriverManager.getInstance().setup();
			FirefoxDriverManager.getInstance().setup();
			OperaDriverManager.getInstance().setup();
			EdgeDriverManager.getInstance().setup();
			InternetExplorerDriverManager.getInstance().setup();	
			Singleton singleton=new Singleton(); 
			MainRun run = new OpenXML<MainRun, MainXML>("C:\\XML\\Main.XML", MainRun.class, MainXML.class).getObject();
		for(MainXML form : run.getMainRun())
		{
			if("YES".equals(form.getRun().toUpperCase()))
			{
				// ***************************************
				// if web we can carry on and start
				// the run
				// if Headless , BE or DB we need to
				// create new runs
				// ***************************************
				if("WEB".equals(form.getType().toUpperCase()))
				{
					url=form.getLink(); 
					petData=form.getDataRequests();
					browsers=form.getBrowsers(); 
					UserStories.userStories(browsers, url);
				}
				else
				if("API".equals(form.getType().toUpperCase()))
				{
					
					url=form.getLink(); 
					petData=form.getDataRequests();
					WebTests webTests=new WebTests(); 
					
					webTests.US04_Post(petData);
					
				}
			else
			{
				
				System.out.println(form.getRun());
				System.out.println(" XML Set to NO.");
			}
			}
		}
		}
		catch(Exception e)
		{
			SimpleDateFormat masterStopFile = new SimpleDateFormat("ddMMyyyyHHmmss");
			PrintWriter writer = new PrintWriter(new File("c://Error//Log" + masterStopFile.format(masterDate) + ".log"));
			
			e.printStackTrace(writer);
			writer.close();
		}
		finally
		{
			/**
			 * @author jcabral Saving this section to DB no matter the result
			 *         This will also save to logfile
			 */
			//sending out emails 
			// if server is down shut down all runs 
			
		}

	}
}
