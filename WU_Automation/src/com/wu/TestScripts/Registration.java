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

	public static void generateRandomNumber(String rndNumber) throws IOException, ConfigurationException{
	    String randomNum = Long.toString( System.currentTimeMillis());
	    String value = rndNumber + randomNum + "@gmail.com";
	    Utils.writeConfig(value);	    
	}
	
	public static void registerWithValidCredentials() throws IOException, ConfigurationException{
		Utils.Launch(Utils.readConfig("Browser"));
		//Utils.Click( By.xpath(Utils.readObjRep("HP_RegisterLink")));
		Utils.Click(By.xpath("//li[text()='Register']"));
		generateRandomNumber("dummy_");
		//Utils.Click( By.xpath(Utils.readObjRep("Register_Email")));
		Utils.SetValue( By.xpath(Utils.readObjRep("Register_Email")), Utils.readConfig("ValidEmail"));
		Utils.Wait(3);
		Utils.Click( By.id(Utils.readObjRep("Register_FirstName")));
		Utils.SetValue( By.id(Utils.readObjRep("Register_FirstName")), Utils.readConfig("ValidFirstName"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_Surname")), Utils.readConfig("ValidSurname"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_Password")), Utils.readConfig("ValidPassword"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_RePassword")), Utils.readConfig("ValidRePassword"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_AddressLine1")), Utils.readConfig("ValidAddressLine1"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_AddressLine2")), Utils.readConfig("ValidAddressLine2"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_City")), Utils.readConfig("ValidCity"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_PostalCode")), Utils.readConfig("ValidPostalCode"));
		Utils.SetSelectBoxValue( By.id(Utils.readObjRep("Register_PhoneCC")), Utils.readConfig("ValidPhoneCC"));
		Utils.Click(By.id(Utils.readObjRep("Register_PhoneNumber")));
		Utils.SetValue( By.id(Utils.readObjRep("Register_PhoneNumber")), Utils.readConfig("ValidPhoneNumber"));
		Utils.SetRadioButtonValue( By.id(Utils.readObjRep("Register_GenderMale")), Utils.readConfig("ValidGenderMale"));
		Utils.SetSelectBoxValue( By.id(Utils.readObjRep("Register_Nationality")), Utils.readConfig("ValidNationality"));
		Utils.SetSelectBoxValue( By.id(Utils.readObjRep("Register_SecurityQuestion")), Utils.readConfig("ValidSecurityQuestion"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_SecurityAnswer")), Utils.readConfig("ValidSecurityAnswer"));
		Utils.SetSelectBoxValue( By.id(Utils.readObjRep("Register_DateOfBirth_day")), Utils.readConfig("ValidDateOfBirth_day"));
		Utils.SetSelectBoxValue( By.id(Utils.readObjRep("Register_DateOfBirth_month")), Utils.readConfig("ValidDateOfBirth_month"));
		Utils.SetValue( By.id(Utils.readObjRep("Register_DateOfBirth_year")), Utils.readConfig("ValidDateOfBirth_year"));
		Utils.Click( By.id(Utils.readObjRep("Register_Terms")));
		Utils.Click( By.id(Utils.readObjRep("Register_MarketingOptions")));
//		Utils.SetValue( By.xpath(Utils.readObjRep("Register_Captcha")), Utils.readConfig("ValidCaptchaValid"));
	}
}
