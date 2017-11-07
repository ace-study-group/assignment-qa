package UserStories;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.HttpClient;



import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;

import UserStories.PetPage;
import junit.framework.Assert;
import Backbone.Page;
import Backbone.Singleton; 


public class WebTests {

	//incomplete 
	
		WebDriver driver; 
		Singleton Singleton = new Singleton();
		Date temporarydate = new Date();
		Date temporarytime = new Date();
		int dataCounter=0;
		String typeRest="";
		String dataRest="";
		String sentence="";
		
		public void US04_Post(String httpData) throws IOException 
		{
			try
			{
				String[] restMethod=httpData.split(";");
				
				System.out.print("Length" + restMethod.length);
				for(dataCounter=0; dataCounter<=restMethod.length;dataCounter++)
				{
					try
					{
					sentence=restMethod[dataCounter]; 
					String[] splitMethod=sentence.split(":");
					typeRest=splitMethod[0];
					dataRest=splitMethod[1];
					URL url = new URL("https://qa-petstore.herokuapp.com"+ dataRest);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod(typeRest);
					connection.setRequestProperty("Content-type", "text/json;charset=\"utf-8\"");
					connection.setReadTimeout(60000);
					connection.setDoOutput(true);
					connection.setDoInput(true);
					connection.getOutputStream().write(dataRest.getBytes());
					connection.connect();
					connection.getResponseCode();
					
					
					BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
					byte[] contents = new byte[1024];

					int bytesRead = 0;
					String strFileContents=""; 
					while((bytesRead = in.read(contents)) != -1) { 
					    strFileContents += new String(contents, 0, bytesRead);              
					}
					
					System.out.print("\n Result Passed ["+ typeRest + "]: " + dataRest + ":" + 	connection.getResponseCode());
					}
					catch (Exception ex)
					{
						//Save to error file
						System.out.print("\n Sentence Failed to get API ");	
						Singleton.status="Failed";
					}
				}
			}
					catch (Exception ex)
			{
				//Save to error file
				System.out.print("Failure to get API ");	
				Singleton.status="Failed";
			}
			finally
			{
				//Save to specific 
			}
		}

}
