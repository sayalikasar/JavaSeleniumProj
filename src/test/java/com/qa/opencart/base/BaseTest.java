package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;

public class BaseTest {
	
	public DriverFactory df;
	public LoginPage loginpage;
	public RegisterPage regPage;
	public WebDriver driver;
	public Properties prop;
	
	@BeforeTest
	public void setup()
	{
		df= new DriverFactory();
		prop=df.init_Prop();
		driver=df.init_driver(prop);
		loginpage= new LoginPage(driver);
		regPage=new RegisterPage(driver);
	}
	
	@AfterTest
	public void tearDown()
	{
		//driver.quit();
	}

}
