package com.wu.TestScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.wu.utility.Utils;

public class LoginScript {
	public static WebDriver driver; 
	
	public static void loginWithValidCredentials() throws IOException, InterruptedException
	{	
		Utils.Launch(Utils.readConfig("Browser"));
		Utils.Click( By.xpath(Utils.readObjRep("HP_LoginLink")));
		Utils.SetValue( By.xpath(Utils.readObjRep("Login_Username")), Utils.readConfig("ValidUsername"));
		Utils.SetValue( By.xpath(Utils.readObjRep("Login_Password")), Utils.readConfig("ValidPassword"));
		Utils.Click( By.xpath(Utils.readObjRep("Login_Submit")));
		Utils.Wait(20);
		Utils.Click(By.xpath(Utils.readObjRep("SignOut_DrpDwn")));
		Utils.Wait(5);
		//Utils.clickElement(By.xpath(Utils.readObjRep("SignOut_Button")));
		boolean iselementPresent = Utils.VerifyExist(By.xpath(Utils.readObjRep("SignOut_Button")));
		Utils.Click(By.xpath(Utils.readObjRep("SignOut_Button")));
		Utils.Close();
	
	}
	

}
