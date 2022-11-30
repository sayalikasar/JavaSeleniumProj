package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class RegPageTest extends BaseTest{
	
	@BeforeClass
	public void regPageSetup() throws InterruptedException
	{
		regPage=loginpage.clickOnRegLink();
		Thread.sleep(2000);
	}
	
	
	@DataProvider(name="getUserData")
	public Object[][] getUserData()
	{
		return new Object[][]
				{
					{"firstname","lastname","email","password","Yes"}
				};
	}
	
	
	@Test
	@Description("testing title of logn page")
	@Severity(SeverityLevel.NORMAL)
	public void getTitle()
	{
		String title=regPage.getRegPageTitle();
		Assert.assertTrue(title.contains(Constants.REG_PAGE_TITLE));
	}
	
	
	@Test(dataProvider="getUserData")
	@Description("testing registration page")
	@Severity(SeverityLevel.CRITICAL)
	public void doRegisteredData(String Fname, String Lname, String eml, String pwd, String subV)
	{
		regPage.registerData(Fname, Lname, eml, pwd, subV);
		System.out.println("registered successfully......");
		
	}
	
	

}
