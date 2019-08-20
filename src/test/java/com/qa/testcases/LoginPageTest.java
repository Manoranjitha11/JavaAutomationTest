package com.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.page.HomePage;
import com.qa.page.LoginPage;
import com.qa.page.SignUpPage;
import com.qa.utils.ExcelUtils;

public class LoginPageTest extends TestBase{

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
		loginPage = new LoginPage(driver, wait);
		homePage.clickSignIn();
		excelUtils = new ExcelUtils();
	}
		
	/**
	 *  this method verify the title of the page
	 */
	@Test(priority=1)
	public void validateLoginPageTitle(){
	    String title = loginPage.validateLoginPageTitle();
	    Assert.assertEquals(title, "Login - My Store");
	}
	
	/**
	 *  this method verify the product logo is displayed
	 */
	@Test(priority=2)
	public void logoImageTest(){
	    boolean flag = loginPage.verifyLogo();
	    Assert.assertTrue(flag);
	}


	/**
	* this method takes below param and checks for valid credentials
	* @param username - valid and registered username
	* @param password - valid password
	 * @throws IOException 
	*/
	@Test(priority = 3)
	public void validCredentials() throws IOException {
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		loginPage.login(testcaseName);
		String expectedUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, "http://automationpractice.com/index.php?controller=my-account");
	}

	/**
	* this method takes below param and checks for empty credentials
	* @param username - empty username
	* @param password - empty password
	 * @throws IOException 
	*/
	@Test(priority=4)
	public void emptyCredentials() throws IOException{
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		loginPage.login(testcaseName);
	    String errorText = loginPage.getErrorText();
	    Assert.assertEquals(errorText, "An email address required.");
	}

	/**
	* this method takes below param and checks for empty username
	* @param username - empty username
	* @param password - some password
	 * @throws IOException 
	*/
	@Test(priority=5)
	public void emptyUsername() throws IOException{
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		loginPage.login(testcaseName);
	    String errorText = loginPage.getErrorText();
	    Assert.assertEquals(errorText, "An email address required.");
	}

	/**
	* this method takes below param and checks for empty password
	* @param username - registered username
	* @param password - empty password
	 * @throws IOException 
	*/
	@Test(priority=6)
	public void emptyPassword() throws IOException{
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		loginPage.login(testcaseName);
	    String errorText = loginPage.getErrorText();
	    Assert.assertEquals(errorText, "Password is required.");
	}

	/**
	* this method takes below param and checks for unregistered username
	* @param username - unregistered username
	* @param password - some password
	 * @throws IOException 
	*/
	@Test(priority=7)
	public void unregisteredUsername() throws IOException{
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		loginPage.login(testcaseName);
	    String errorText = loginPage.getErrorText();
	    Assert.assertEquals(errorText, "Authentication failed.");
	}

	/**
	* this method takes below param and checks for invalid password
	* @param username - registered username
	* @param password - invalid password
	 * @throws IOException 
	*/
	@Test(priority=8)
	public void invalidPassword() throws IOException{
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		loginPage.login(testcaseName);
	    String errorText = loginPage.getErrorText();
	    Assert.assertEquals(errorText, "Authentication failed.");
	}

	/**
	* this method takes below param and checks for valid username(email) format
	* @param username - invalid username (ie incorrect email format)
	* @param password - some password
	 * @throws IOException 
	*/
	@Test(priority=9)
	public void invalidUsername() throws IOException{
		String testcaseName = new Throwable().getStackTrace()[0].getMethodName(); 
		loginPage.login(testcaseName);
	    String errorText = loginPage.getErrorText();
	    Assert.assertEquals(errorText, "Invalid email address.");
	}

	/**
	* this method takes below param and checks whether password is either visible as asterisk or bullet signs.
	*/
	@Test(priority=10)
	public void isPasswordVisible(){
		String type =  loginPage.verifyPasswordVisibility(); 
		 Assert.assertEquals(type, "password");
	}
	
	/**
	* this method takes below param and checks for exceeding the character limit of the Password fields
	* @param username - valid username
	* @param password - very long password
	*/
	@Test(enabled=false)
	public void passwordLength(String username, String password){
	    loginPage.login(username, password);
	    String errorText = loginPage.getErrorText();
	    Assert.assertEquals(errorText, "Invalid password.");
	}

	/**
	* this method takes below param and checks for exceeding the character limit of the Username fields
	* @param username - very long username but proper email format
	* @param password - some password
	*/
	@Test(enabled=false)
	public void usernameLength(String username, String password){
	    loginPage.login(username, password);
	    String errorText = loginPage.getErrorText();
	    Assert.assertEquals(errorText, "Authentication failed.");
	}

	/**
	* this method takes below param and checks for exceeding the character limit of the Username fields
	* @param username - very long username and improper email format
	* @param password - some password
	*/
	@Test(enabled=false)
	public void usernameLength2(String username, String password){
	    loginPage.login(username, password);
	    String errorText = loginPage.getErrorText();
	    Assert.assertEquals(errorText, "Invalid email address.");
	}


	/**
	* this method checks for login in different tabs of the same browser window
	*/
	@Test(enabled=false)
	public void differentTabLogin(String username, String password){
	    
	}

	/**
	* this method checks for login in different windows of the same browser
	*/
	@Test(enabled=false)
	public void differentWindowLogin(String username, String password){
	    
	}

	/**
	* this method checks for login in different browsers
	*/
	@Test(enabled=false)
	public void differentBrowserLogin(String username, String password){
	    
	}

	/**
	* this method is to verify that user is not able to Login with inactive credentials
	* @param username - inactive username (ie deactivated account credentials)
	* @param password - some password
	*/
	@Test(enabled=false)
	public void inActiveCredentials(String username, String password){
	    
	}

	/**
	 * this method is to verify that encrypted characters in “Password” field
	 * should not allow deciphering if copied
	 */
	@Test(enabled = false)
	public void decipheringPassword() {

	}

	/**
	 * this method is to verify that whether user is still logged in after
	 * series of actions such as sign in, close browser and reopen the
	 * application.
	 */
	@Test(enabled = false)
	public void reopenApplicationWindow() {

	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
