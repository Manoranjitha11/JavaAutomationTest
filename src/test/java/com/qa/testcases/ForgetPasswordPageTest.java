package com.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.page.HomePage;
import com.qa.page.LoginPage;
import com.qa.page.SignUpPage;
import com.qa.utils.ExcelUtils;

public class ForgetPasswordPageTest extends TestBase{

	
	LoginPage loginPage;
	HomePage homePage;
	SignUpPage signupPage;
	ExcelUtils excelUtils;
	WebDriver driver;
	WebDriverWait wait;
		
	@Parameters({"browser"})
	@BeforeMethod
	public void setUp(String browser) throws Exception{
		initialization(browser);
		this.driver = getDriver();
		this.wait = getWait();	
		homePage = new HomePage(driver, wait);
		homePage.clickSignIn();
	}

	/**
	 * Verify that user is redirected to Forgot password page when clicking on Forgot Password link
	*/
	@Test(priority=0)
	public void redirectedToForgotPasswordPage(){

	}

	/**
	* this method takes below param and checks for valid username(email) format
	* @param username - unregistered username
	*/
	@Test(priority=1)
	public void unregisteredUsername(String username){

	    String errorText = forgotPassword.getErrorText();
	    Assert.assertEquals(errorText, "There is no account registered for this email address.");
	}

	/**
	* this method takes below param and checks for valid username(email) format
	* @param username - invalid username (ie incorrect email format)
	*/
	@Test(priority=2)
	public void invalidUsername(String username){

	    String errorText = forgotPassword.getErrorText();
	    Assert.assertEquals(errorText, "Invalid email address.");
	}

	/**
	* this method takes below param and checks for valid username(email) format
	* @param username - valid username
	*/
	@Test(priority=3)
	public void validUsername(String username){

	    Assert.assertEquals(successText, "A confirmation email has been sent to your address: ${username}");
	}

	/**
	* this method takes below param and checks for empty username
	* @param username - empty username
	*/
	@Test(priority=4)
	public void emptyUsername(String username){

	    String errorText = loginPage.getErrorText();
	    Assert.assertEquals(errorText, "Invalid email address.");
	}

	/**
	* After changing the password, this method takes below param and checks for login with old password
	* @param username - valid username
	* @param username - old password
	*/
	@Test(priority=5)
	public void oldPasswordLogin(String username, String password){

	}


	/**
	* After changing the password, this method takes below param and checks for login with new password
	* @param username - valid username
	* @param username - new valid password
	*/
	@Test(priority=6)
	public void newPasswordLogin(String username, String password){

	}
}
