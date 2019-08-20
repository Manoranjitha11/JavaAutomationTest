package com.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	By signIn = By.xpath("//a[@class='login']");
	WebDriver driver = null;
	WebDriverWait wait = null;

	public HomePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void clickSignIn() {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.signIn))).click();

	}

}
