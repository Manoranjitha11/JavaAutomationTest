package com.qa.base;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.testcases.LoginPageTest;
import com.qa.testcases.SignupPageTest;
import com.qa.utils.Constants;

public class TestBase {

	WebDriver driver;
	WebDriverWait wait;
	SignupPageTest signupPageTest;
	LoginPageTest loginPageTest;


	public  void initialization(String browser) throws Exception {
		if (browser.equalsIgnoreCase("chrome")) {
			// create chrome instance
			System.setProperty(Constants.DRIVER_CHROME, Constants.DRIVER_LAUNCHER_PATH_CHROME);
			driver = new ChromeDriver();
		}
		// Check if parameter passed as 'firefox'
		else if (browser.equalsIgnoreCase("firefox")) {
			// set path to chromedriver.exe
			System.setProperty(Constants.DRIVER_FIREFOX, Constants.DRIVER_LAUNCHER_PATH_FIREFOX);
			// create chrome instance
			driver = new FirefoxDriver();
		}
		// Check if parameter passed as 'Edge'
		else if (browser.equalsIgnoreCase("Edge")) {
			// set path to Edge.exe
			System.setProperty(Constants.DRIVER_EDGE, Constants.DRIVER_LAUNCHER_PATH_EDGE);
			// create Edge instance
			driver = new EdgeDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(Constants.URL);
		wait = new WebDriverWait(driver, 30);
		HashMap<WebDriver, WebDriverWait> driverInstance= new HashMap<WebDriver, WebDriverWait>();
		driverInstance.put(driver, wait);
		
	}
	
	public WebDriver getDriver(){
		
		return this.driver;
	
	}
public WebDriverWait getWait(){
		
		return this.wait;
	
	}
}
