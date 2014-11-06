package com.wu.TestScripts;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import bsh.Parser;

import com.wu.utility.Utils;

public class Registration {
	static String winHandle;

	public static void generateRandomNumber(String rndNumber) throws IOException, ConfigurationException{
	    String randomNum = Long.toString( System.currentTimeMillis());
	    String value = rndNumber + randomNum + "@gmail.com";
	    Utils.writeConfig("ValidEmail", value);	    
	}
	
	public static Boolean IsRegistrationSuccessful() throws IOException, InterruptedException{
		Boolean result = false;
		
		String successText = Utils.getBodyText(By.xpath(Utils.readObjRep("Register_Success")));
		
		if(successText.equalsIgnoreCase("Thank you for registering with Western Union")){
			Thread.sleep(5000);
			try{
			winHandle = Utils.getCurrWinhandle();
			}catch(Exception e){
				System.out.println("Unable to get handler");	
			}
			result = true;
		}
		return result;
	}
	
	public static void registerWithValidCredentials() throws IOException, ConfigurationException, InterruptedException{
		// Click on Register Page from Home Page
		Utils.clickRegiter();
		
		// Generate random email
		generateRandomNumber("dummy_");
		
		/*Account information*/
		Utils.SetValue( By.xpath(Utils.readObjRep("Register_Email")), Utils.readConfig("ValidEmail"));
		Utils.Wait(3);
		Utils.Click( By.id(Utils.readObjRep("Register_FirstName")));
		Utils.SetValue( By.id(Utils.readObjRep("Register_FirstName")), Utils.readConfig("ValidFirstName"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_Surname")), Utils.readConfig("ValidSurname"));
		/*Account information*/
		
		/*Password*/
		Utils.SetValue( By.id(Utils.readObjRep("Register_Password")), Utils.readConfig("ValidPassword"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_RePassword")), Utils.readConfig("ValidRePassword"));
		/*Password*/
		
		/*Contact information*/
		Utils.SetValue( By.id(Utils.readObjRep("Register_AddressLine1")), Utils.readConfig("ValidAddressLine1"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_AddressLine2")), Utils.readConfig("ValidAddressLine2"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_City")), Utils.readConfig("ValidCity"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_PostalCode")), Utils.readConfig("ValidPostalCode"));
		Utils.SetSelectBoxValue( By.id(Utils.readObjRep("Register_PhoneCC")), Utils.readConfig("ValidPhoneCC"));
		Utils.Click(By.id(Utils.readObjRep("Register_PhoneNumber")));
		Utils.SetValue( By.id(Utils.readObjRep("Register_PhoneNumber")), Utils.readConfig("ValidPhoneNumber"));
		/*Contact information*/
		
		/*Other information*/
		Utils.SetRadioButtonValue( By.id(Utils.readObjRep("Register_GenderMale")), Utils.readConfig("ValidGenderMale"));
		Utils.SetSelectBoxValue( By.id(Utils.readObjRep("Register_Nationality")), Utils.readConfig("ValidNationality"));
		/*Other information*/
		
		/*Security information*/
		Utils.SetSelectBoxValue( By.id(Utils.readObjRep("Register_SecurityQuestion")), Utils.readConfig("ValidSecurityQuestion"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_SecurityAnswer")), Utils.readConfig("ValidSecurityAnswer"));
		Utils.SetSelectBoxValue( By.id(Utils.readObjRep("Register_DateOfBirth_day")), Utils.readConfig("ValidDateOfBirth_day"));
		Utils.SetSelectBoxValue( By.id(Utils.readObjRep("Register_DateOfBirth_month")), Utils.readConfig("ValidDateOfBirth_month"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_DateOfBirth_year")), Utils.readConfig("ValidDateOfBirth_year"));
		Utils.Click( By.id(Utils.readObjRep("Register_Terms")));
		Utils.Click( By.id(Utils.readObjRep("Register_MarketingOptions")));
		Utils.SetValue( By.id(Utils.readObjRep("Register_Captcha")), Utils.readConfig("ValidCaptcha"));
		/*Security information*/
		Utils.Click(By.id(Utils.readObjRep("Register_Submit")));
		
		if(IsRegistrationSuccessful()){
			Utils.getOTP("Firefox", "OTP_SelectMobiliser", "ValidMobiliser", "OTP_Email", "ValidEmail");
		}else {
			System.out.println("Registration was not Successful");
			return;
		}
		Utils.windowSwitch(winHandle);
		Utils.Click(By.id(Utils.readObjRep("Register_OTP")));
		Utils.SetValue(By.id(Utils.readObjRep("Register_OTP")), Utils.readConfig("ValidOTP"));
		Utils.Click(By.id(Utils.readObjRep("Register_Complete_Verification")));
	}
	
	public static void registerWithExistingUser() throws IOException {
		Utils.clickRegiter();
		Utils.Click(By.xpath(Utils.readObjRep("Register_Email")));
		Utils.SetValue( By.xpath(Utils.readObjRep("Register_Email")), Utils.readConfig("ValidEmail"));
		Utils.Click( By.id(Utils.readObjRep("Register_FirstName")));
		System.out.println(Utils.getBodyText(By.id(Utils.readObjRep("Register_Email_Error"))));
//		if(Utils.getBodyText(By.id(Utils.readObjRep("Register_Email_Error"))).equalsIgnoreCase("User/email already registered.")){
//			System.out.println("Pass: Exiting User cant register!!");
//		}else{
//			System.out.println(Utils.getBodyText(By.id(Utils.readObjRep("Register_Email_Error"))));
//		}
	}
}
