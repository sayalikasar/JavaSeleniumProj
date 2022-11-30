package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Epic 100")
@Story("userstory 01: design login page features")
public class LoginPageTest extends BaseTest{
	
	
	
	@Test
	@Description("testing title of logn page")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest()
	{
		String title=loginpage.getLoginPageTitle();
		System.out.println("login page title"+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	@Description("testing URL of logn page")
	@Severity(SeverityLevel.CRITICAL)
	public void loginPageGetUrl()
	{
		String URL=loginpage.getLoginPageUrl();
		Assert.assertTrue(URL.contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	@Description("testing LOGIN WITH VALID USER")
	@Severity(SeverityLevel.BLOCKER)
	public void doLoginTest()
	{
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	@Description("testing reg page")
	@Severity(SeverityLevel.NORMAL)
	public void doClickOnRegPage()
	{
		if(loginpage.isRegLinkVisible())
		{
			loginpage.clickOnRegLink();
		}
		else
		{
			System.out.println("Not visible");
		}
		
	}
	
	

}
