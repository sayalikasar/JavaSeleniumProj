package com.qa.opencart.pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil elutil;
	
	//1. all locators
	private By Fname= By.name("firstname");
	private By Lname= By.name("lastname");
	private By email= By.name("email");
	private By pwd=By.name("password");
	private By subYes=By.xpath("//label[@class='form-check-label']/../child::input[@value=1 and @type='radio']");
	private By subNo=By.xpath("//input[@id='input-newsletter-no']");
	private By termsCheckBox= By.cssSelector(".form-check-input[name='agree']");
	private By ContinueBtn= By.xpath("//button[@type='submit']");
	
	//2.Constructor
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		elutil=new ElementUtil(driver);
	}
	
	//3.Methods
	public String getRegPageTitle()
	{
		return driver.getTitle();
	}
	
	public String getRegPageUrl()
	{
		return driver.getCurrentUrl();
	}
	
	public void registerData(String firstname, String lastname, String emailvalue, String password, String subValue)
	{
		elutil.doSendKeys(Fname, firstname);
		elutil.doSendKeys(Lname, lastname);
		elutil.doSendKeys(email, emailvalue);
		elutil.doSendKeys(pwd, password);
		if(subValue.equals("Yes"))
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(subYes));
			ele.click();
			System.out.println(ele.getText());
			//driver.findElement(subYes).click();
		}
		else
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement No=wait.until(ExpectedConditions.elementToBeClickable(subNo));
			No.click();
			System.out.println(No.getText());
			//driver.findElement(subNo).click();
		}
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement chkbox=wait.until(ExpectedConditions.elementToBeClickable(termsCheckBox));
		chkbox.click();
		
		elutil.doClick(ContinueBtn);
	}
	
	
	
	
	
	
	
	

}
