package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elutil;
	
	//1. all private By locator of login page
	private By email=By.id("input-email");
	private By pwd=By.id("input-password");
	private By lgnButton=By.xpath("//button[@class='btn btn-primary']");
	private By reglink=By.linkText("Register");

	
	//2.constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elutil= new ElementUtil(driver);
	}
	
	//3.public page action
	@Step("Checking title of the page")
	public String getLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	@Step("accessing URL")
	public String getLoginPageUrl()
	{
		return driver.getCurrentUrl();
	}
	
	@Step("Logging in to application with valid username {0} and password {1}")
	public void doLogin(String un, String pwdvalue)
	{
		elutil.doSendKeys(email, un);
		elutil.doSendKeys(pwd, pwdvalue);
		elutil.doClick(lgnButton);
	}

	public boolean isRegLinkVisible()
	{
		return driver.findElement(reglink).isEnabled();
	}
	
	public RegisterPage clickOnRegLink()
	{
		driver.findElement(reglink).click();
		return new RegisterPage(driver);
	}
	
}
