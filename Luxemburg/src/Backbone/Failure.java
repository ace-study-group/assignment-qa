package Backbone;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;

import org.openqa.selenium.WebDriver;

public class Failure
{
	public Failure()
	{
	}
	public void testFail(Exception ex) throws IOException, ParseException, SQLException
	{
	//	final String SMTP_IP_ADDRESS = "192.168.111.12";
		Screenshot failScreenshot = new Screenshot();
		ex.printStackTrace();
		
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("Test Version:   Test \n");
		buffer.append("Site:  web app pets \n");
		buffer.append("Steps: get from dataBase \n");
		buffer.append("Location of Screenshot: Location \n");
	
	}
}
