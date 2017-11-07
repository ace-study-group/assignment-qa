package Backbone;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.google.common.base.Function;

import io.selendroid.server.common.exceptions.NoSuchElementException;
import io.selendroid.server.common.exceptions.TimeoutException;

public abstract class Page
{
	protected WebDriver driver;
	protected Map<String, By> listOfByElements;
//	protected CRUD temporaryRun;
//	protected DBTables mainRun;
//	protected DBTables tempRun;
	public abstract void loadDefinitions();

	public Page(WebDriver driver)
	{
		this.driver = driver;
		this.listOfByElements = new HashMap<String, By>() ;
		loadDefinitions();
	}
	
	public void actionClick(String elementName) throws NoSuchElementException, InterruptedException, SQLException{
		boolean findResult = false;
		try{
			WebElement ele = driver.findElement(listOfByElements.get(elementName));
			ele.click();
			System.out.println(elementName + " on xpath:" + listOfByElements.get(elementName) + "driver click()");
			findResult = true;
		}
		catch(java.util.NoSuchElementException e){
			findResult = false;
			System.out.println("NoSuchElementException:" + elementName + " on xpath:" + listOfByElements.get(elementName));
			//e.printStackTrace();
			Thread.sleep(2000);
		}
		catch(org.openqa.selenium.TimeoutException e){
			findResult = false;
			System.out.println("TimeoutException:" + elementName + " on xpath:" + listOfByElements.get(elementName));
			//e.printStackTrace();
			Thread.sleep(2000);
    	}
		catch(WebDriverException e){
			findResult = false;
			System.out.println("WebDriverException:" + elementName + " on xpath:" + listOfByElements.get(elementName));
			//e.printStackTrace();
			Thread.sleep(2000);
		}
		catch(Exception e){
			findResult = false;
			System.out.println(e.getClass().getSimpleName() + ": " + elementName + " on xpath:" + listOfByElements.get(elementName));
			e.printStackTrace();
			Thread.sleep(2000);
		}		finally{
			if(!findResult){
				List<WebElement> element = driver.findElements(listOfByElements.get(elementName));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element.get(0));
				System.out.println(elementName + " on xpath:" + listOfByElements.get(elementName) + "script click()");
			}
		}
	}
	
	
		 
	public String actionRead(String elementName){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(listOfByElements.get(elementName)));
        String value = driver.findElement(listOfByElements.get(elementName)).getText().toString();
		return value;
	}
	
	
	public void actionSendKey(String elementName, String value) throws org.openqa.selenium.NoSuchElementException, InterruptedException, SQLException{
		boolean findResult = false;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		waitForLoad(driver);
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(listOfByElements.get(elementName)));
			WebElement element = driver.findElement(listOfByElements.get(elementName));
			element.click();
			element.sendKeys(value);
			findResult = true;
			System.out.println(elementName + " on xpath:" + listOfByElements.get(elementName) + "driver sendKey()");
		}
		catch(WebDriverException e){
			findResult = false;
			System.err.println("WebDriverException:" + elementName + " on xpath:" + listOfByElements.get(elementName) + "FAILED");
			Thread.sleep(2000);
		}
		catch(java.util.NoSuchElementException e){
			findResult = false;
			System.err.println("NoSuchElementException:" + elementName + " on xpath:" + listOfByElements.get(elementName) + "FAILED");
			Thread.sleep(2000);
		}
		catch(TimeoutException e){
			findResult = false;
			System.err.println("TimeoutException:" + elementName + " on xpath:" + listOfByElements.get(elementName) + "FAILED");
			Thread.sleep(2000);
    	 }
		finally{
			if(!findResult){
				WebElement element = driver.findElement(listOfByElements.get(elementName));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].sendKeys(arguments[1]);", element,value);
				System.out.println(elementName + " on xpath:" + listOfByElements.get(elementName) + "script sendKey()");
			}
		}
	}
	
	public void actionClear(String elementName){
		try{
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(listOfByElements.get(elementName)));
			driver.findElement(listOfByElements.get(elementName)).clear();
		}
		catch(Exception e){
			System.out.println("FIND ELEMENT ERROR: " + elementName + " , " + listOfByElements.get(elementName) + "\nUSING JAVASCRIPT TO FIND");
			WebElement element = driver.findElement(listOfByElements.get(elementName));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].clear();", element);
			
		}
	}
	
	public WebElement getRandomElementFromContainer(String containerName){
		List<WebElement> elements = driver.findElements(listOfByElements.get(containerName));    

		Random rand = new Random();
		int indexOfRandomElement = rand.nextInt(elements.size());
		
		return elements.get(indexOfRandomElement);
	}
	
	public void actionClickAll(String containerOfElements) throws java.util.NoSuchElementException, InterruptedException{
		List<WebElement> elements = driver.findElements(listOfByElements.get(containerOfElements));    

		WebDriverWait wait = new WebDriverWait(driver, 5);
        for(WebElement ele : elements){
        	try{
    			wait.until(ExpectedConditions.elementToBeClickable(ele));
    			ele.click();
    			Thread.sleep(1000);
    			System.out.println("Click on " + ele.getText());
        	}
        	catch(Exception e){
        		System.out.println("FIND ELEMENT ERROR: " + ele + " , " + (By)ele + "\nUSING JAVASCRIPT TO FIND");
        		WebElement element = driver.findElement((By) ele);
        		JavascriptExecutor executor = (JavascriptExecutor)driver;
        		executor.executeScript("arguments[0].click();", element);
        	}
		}
	}
	
	public void setDriver(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void waitForLoad(WebDriver driver) {
	    new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
	
	public WebElement fluentWait(final By locator) throws java.util.NoSuchElementException{
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(30, TimeUnit.SECONDS)
	            .pollingEvery(5, TimeUnit.SECONDS);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo;
	};	
}
	

