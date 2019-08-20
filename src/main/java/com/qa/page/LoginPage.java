package com.qa.page;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.utils.Constants;
import com.qa.utils.ExcelUtils;

public class LoginPage {
	By email = By.id("email_create");
	By createAccount = By.id("SubmitCreate");
	By registeredEmail = By.id("email");
	By password = By.id("passwd");
	By signInBtn = By.id("SubmitLogin");
	By productLogo = By.className("logo");
	By errorAlert = By.cssSelector(".alert.alert-danger ol li");
	WebDriver driver = null;
	WebDriverWait wait = null;
	ExcelUtils excelUtils;

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		excelUtils = new ExcelUtils();
	}

	public void setEmail(String email) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.email))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.email))).sendKeys(email);
	}

	public void clickCreateAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.createAccount))).click();
	}

	public void setRegisteredEmail(String email) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.registeredEmail))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.registeredEmail))).sendKeys(email);
	}
	public void setPassword(String password) {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.password))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.password))).sendKeys(password);
	}
	public String getErrorText() {
		String ActualData = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.errorAlert))).getText();
		return ActualData;
	}
	public void clickSignIn() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.signInBtn))).click();
	}
	public boolean verifyLogo() {
		return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.productLogo))).isDisplayed();
	
	}
	
	public String verifyPasswordVisibility() {
		return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.password))).getAttribute("type");
	
	}
	
		public String validateLoginPageTitle(){
			return driver.getTitle();
		}
		
		public void login(String testcaseName) throws IOException{
			String username;
			String pasword;
			
			HashMap<String, String> credential = excelUtils.getdata(testcaseName,Constants.SHEETNAME_LOGIN);
			 username = credential.entrySet().iterator().next().getKey();				
			 pasword = credential.entrySet().iterator().next().getValue();				
			 login(username, pasword);

		}
		
	
	public void createAccount(String email) {

		setEmail(email);
		clickCreateAccount();

	}
	public void login(String email, String password) {

		setRegisteredEmail(email);
		setPassword(password);
		clickSignIn();

	}
}
