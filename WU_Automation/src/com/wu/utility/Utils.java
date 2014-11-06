package com.wu.utility;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import driver.test1;

public class Utils {

	private static Logger log = Logger.getLogger(Utils.class);

	public static WebDriver driver;
	
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: readObjRep
	//Description: A method to collect element locators from 'OR' file. 
	//Created Date :                       Create By: 
	//Last Modified Date:                  Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	
	public static String readObjRep(String keyWord) throws IOException{
		FileInputStream file = new FileInputStream("ObjectRepository\\OR.properties");
		Properties prop = new Properties();
		prop.load(file);
		String key = prop.getProperty(keyWord);
		return key;
	}
	
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: readConfig
	//Description:  A method to collect test data and configurations settings from 'Config' file
	//Created Date :                      Create By: 
	//Last Modified Date:                 Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	
	public static String readConfig(String value) throws IOException{
		FileInputStream file = new FileInputStream("Configuration\\config.properties");
		Properties prop = new Properties();
		prop.load(file);
		String val = prop.getProperty(value);
		return val;
	}
	//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: writeConfig
	//Description:  A method to write test 
	//Created Date :                      Create By: 
	//Last Modified Date:                 Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	
	public static void writeConfig(String key, String value) throws IOException, ConfigurationException{
		try
		{
			PropertiesConfiguration config = new PropertiesConfiguration("Configuration\\config.properties");
			config.setProperty(key, value);
			config.save();
		}catch(Exception e){

			System.out.println("Unable tp update config file");	
		}
}
	
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: Launch
	//Description: A method to open browser and navigate to given environment.
	//Created Date :                       Create By:
	//Last Modified Date:                  Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	
	
	public static void Launch(String browserName) throws IOException{
		if(browserName.equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
			log.info("Browser Launched Succesfully");
			driver.get(readConfig("Url"));
			driver.manage().window().maximize();
			log.info("Browser Maximised Succesfully");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			log.info("Wait for navigate to test environment");
		}
	}

//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: Launch
	//Description: A method to open browser and navigate to given environment.
	//Created Date :                       Create By:
	//Last Modified Date:                  Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	
	
	public static void clickRegiter() throws IOException{
		try{
			Utils.Launch(Utils.readConfig("Browser"));
			Utils.Click(By.xpath("//li[text()='Register']"));
		}catch(Exception e){
			System.out.println("Unable to click on Register");
		}
	}
	
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: getOTP
	//Description: A method to get OTP
	//Created Date :                       Create By:
	//Last Modified Date:                  Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	
	public static void getOTP(String browserName, String mobiliserObject, String validMobiliser, String emailObject, String validEmail) throws IOException{
		
		WebDriver originalDriver = Utils.driver; 
		
		if(browserName.equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			log.info("Browser Launched Succesfully");
			log.info("Wait for navigate to test environment");
			driver.get(Utils.readConfig("OTP"));
			SetSelectBoxValue(By.id(readObjRep(mobiliserObject)), Utils.readConfig(validMobiliser));
			SetValue(By.id(Utils.readObjRep(emailObject)), Utils.readConfig(validEmail));
			Click(By.className(Utils.readObjRep("OTP_GetOTP")));
			try
			{
				String OTP = driver.findElement(By.xpath(readObjRep("OTP_Number"))).getText();
				writeConfig("ValidOTP", OTP);
				driver.quit();
			}catch(Exception e){
				System.out.println("Unable to get text");
			}
		}
		
		Utils.driver = originalDriver;
	}
	
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: SetValue
	//Description: A method to enter Value in text box fields
	//Created Date :                         Create By: 
	//Last Modified Date:                    Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static void SetValue (By by, String value){
		try
		{
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);

		}catch(Exception e){

			System.out.println("Unable to enter value in textbox field");	
		}
	}

//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: SetSelectBoxValue
	//Description: A method to enter Value in select box fields
	//Created Date :                         Create By: 
	//Last Modified Date:                    Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static void SetSelectBoxValue (By by, String value){
		try
		{
			new Select(driver.findElement(by)).selectByVisibleText(value);

			}catch(Exception e){

			System.out.println("Unable to enter value in select box field");	
		}
	}
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: SetRadioButtonValue
	//Description: A method to enter Value in select box fields
	//Created Date :                         Create By: 
	//Last Modified Date:                    Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static void SetRadioButtonValue (By by, String value){
		try
		{
			
			if(value == "Male") {
				WebElement genderMale = driver.findElement(by);
				genderMale.click();
				}
			else{
				WebElement genderFemale = driver.findElement(by);
				genderFemale.click();
			}
		}catch(Exception e){

			System.out.println("Unable to enter value in select box field");	
		}
	}

	
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: Click
	//Description: A Method to perform click action  on elements(buttons,link etc..)
	//Created Date :                         Create By:
	//Last Modified Date:                    Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static void Click(By by){
		try
		{
			driver.findElement(by).click();
			System.out.println("Clicked on Object");
			log.info("Clicked on Element - " + by);
		}catch(Exception e){
			System.out.println("Error on Click");
		}
	}
	
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: getTitle
	//Description: A Method to get title of the page
	//Created Date :                         Create By:
	//Last Modified Date:                    Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static String getPageTitle(){
		String pageTitle = "";
		try
		{
			pageTitle = driver.getTitle();
		}catch(Exception e){
			System.out.println("Unable to get title");
		}
		return pageTitle;
	}
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: getCurrWinhandle
	//Description: A Method to get title of the page
	//Created Date :                         Create By:
	//Last Modified Date:                    Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static String getCurrWinhandle(){
		String currWindowHandle = "";
		try
		{
			currWindowHandle = driver.getWindowHandle();
		}catch(Exception e){
			System.out.println("Unable to get window handle");
		}
		return currWindowHandle;
	}
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: windowSwitch
	//Description: A Method to  switch between windows
	//Created Date :                         Create By:
	//Last Modified Date:                    Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static void windowSwitch(String winHandler){
		try
		{
			System.out.println(winHandler);
			System.out.println(driver.getWindowHandle());
			driver.switchTo().window(winHandler);
		}catch(Exception e){
			System.out.println("Unable to switch between windows");
		}
	}

//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: getBodyText
	//Description: A Method to get text
	//Created Date :                         Create By:
	//Last Modified Date:                    Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static String getBodyText(By by){
		String  bodyText= "";
		try
		{
			bodyText = driver.findElement(by).getText();
		}catch(Exception e){
			System.out.println("Unable to get text");
		}
		return bodyText;
	}

//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: VerifyText
	//Description: A method to verify whether a string text is present or not on page
	//Created Date :                          Create By:
	//Last Modified Date:                     Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	
	
	public static boolean VerifyText(By by, String ExepectedString){
		String actualSTring = null;
		try
		{
			actualSTring = driver.findElement(by).getText();
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting the innertext");
			log.info(ExepectedString + "  Text is Present");
		}
		if(actualSTring.equalsIgnoreCase(ExepectedString))
		{
			System.out.println(ExepectedString + "  Text is Present");
			
			return true;
		}
		else
		{
			System.out.println(ExepectedString + "  Text is Not Present");
			log.info(ExepectedString + "  Text is Not Present");
			return false;
		}

	}
	
//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: VerifyExist
	//Description: A method to verify whether an element is present or not on page.
	//Created Date :                           Create By: 
	//Last Modified Date:                      Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static boolean VerifyExist(By by){
		boolean f = false;
		try{
			 f = driver.findElement(by).isDisplayed();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		if(f)
		{
			System.out.println(by + "  Element is Present");
			log.info(by + "  Element is Present");
			return true;
		}
		else
		{
			System.out.println(by + "  Element is Not Present");
			log.info(by + "  Element is Not Present");
			return false;
		}

	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: Close
	//Description: A method to close web driver instance.
	//Created Date :                           Create By: 
	//Last Modified Date:                      Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static void Close()
	{
		try
		{
			driver.close();
			System.out.println("Driver closed successfully");
		}
		catch(Exception e)
		{
			System.out.println("Exception in closing the web driver");
		}
		
	}
	//----------------------------------------------------------------------------------------------------------------------------
    //Method Name: Wait
	//Description: A method to wait.
	//Created Date :                           Create By: 
	//Last Modified Date:                      Last Modified By:
//-------------------------------------------------------------------------------------------------------------------------------	

	public static void Wait(long sec)
	{
		try
		{
		  Thread.sleep(sec*1000);
		  System.out.println("Successfully waited for" + sec + "seconds");
	    }
		catch(Exception e)
		{
			
		}
	}	

}
