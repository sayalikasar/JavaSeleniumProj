package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageNegativeTest extends BaseTest{
	
	
	
	@DataProvider(name="getUserData")
	public Object[][] getUserData()
	{
		return new Object[][]
				{
					{"firstname","lastname"},
					{"firstname","lastname"},
					{"firstname","lastname"},
				};
	}
	
	@Test(dataProvider="getUserData")
	@Description("testing LOGIN WITH VALID USER")
	@Severity(SeverityLevel.BLOCKER)
	public void doLoginTest(String usr,String pwd)
	{
		loginpage.doLogin(usr, pwd);
		//Assert.assertTrue(URL.contains(Constants.LOGIN_PAGE_FRACTION_URL));
		
	}

}
