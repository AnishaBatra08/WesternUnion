package com.wu.utility;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
