package driver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.wu.TestScripts.LoginScript;
import com.wu.utility.Utils;


public class test1 {

	private static Logger logg = Logger.getLogger(test1.class);


	public static void main(String[] args) throws IOException, InterruptedException {
		LoginScript.loginWithValidCredentials();
		
				
	}

}
