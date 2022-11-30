package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ElementUtil {
	
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}


	/**
	 * This method will return by as per locator type
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */
	public By getBy(String locatorType, String locatorValue)
	{
		By locator=null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator=By.id(locatorValue);
			break;
		case "linktext":
			locator=By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator=By.partialLinkText(locatorValue);
			break;
		case "name":
			locator=By.name(locatorValue);
			break;
		case "cssselector":
			locator=By.cssSelector(locatorValue);
			break;
		case "xpath":
			locator=By.xpath(locatorValue);
			break;
		case "tagname":
			locator=By.tagName(locatorValue);
			break;
			
		default:
			break;
			
		}
		return locator;
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public List<String> getLinkTextList(By locator) {
		List<WebElement> ls=getElements(locator);
		List<String> eletextlist=new ArrayList<String>();
		
		for(WebElement e: ls) {
			String str=e.getText();
			if(!str.isEmpty())
			{
				eletextlist.add(str);
			}
		}
		return eletextlist;
	}
	
	public void doSendKeys(By locator,String value)
	{
		getElement(locator).sendKeys(value);
	}
	
	public String dogetText(WebElement el) {
		return el.getText();
	}
	
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	public List<String> getAttributeList(By locator,String value)
	{
		List<WebElement> list=getElements(locator);
		List<String> ls=new ArrayList<String>();
		for(WebElement e: list)
		{
			String text=e.getAttribute(value);
			ls.add(text);
		}
		return ls;
	}
	
	/***********************************Drop down util*************************************************/
	
	public  void doSelectByVisibleText(By locator, String value)
	{
		WebElement e=getElement(locator);
		Select sel=new Select(e);
		sel.selectByVisibleText(value);
	}
	
	public void doSelectByIndex(By locator, int num)
	{
		WebElement e=getElement(locator);
		Select sel=new Select(e);
		sel.selectByIndex(num);
	}
	
	public void doSelectByValue(By locator, String value)
	{
		WebElement e=getElement(locator);
		Select sel=new Select(e);
		sel.selectByValue(value);
	}
	
	public List<String> doGetDropDownOption(By locator)
	{
		WebElement el=driver.findElement(locator);
		Select sel=new Select(el);
		List<WebElement> lst=sel.getOptions();
		List<String> optionList=new ArrayList<>();
		for(WebElement e1: lst)
		{
			optionList.add(e1.getText());
		}
		return optionList;
	
	}
	
	public List<String> doSelectDropDownValue(By locator,String val)
	{
		WebElement el=driver.findElement(locator);
		Select sel=new Select(el);
		List<WebElement> lst=sel.getOptions();
		List<String> optionList=new ArrayList<>();
		for(WebElement e1: lst)
		{
			if(el.getText().equals(val));
				el.click();
				break;
			
		}
		return optionList;
	
	}
	
	public List<String> getSuggestionList(By loc)
	{
		List<WebElement> lst=getElements(loc);
		List<String> suggestLstText= new ArrayList<String>();
		
		for(WebElement e: lst)
		{
			suggestLstText.add(e.getText());
		}
		
		return suggestLstText;
		
	}

	//*****************Actions class*******************************//
	
	public void selectSubMenu2(By parent, By child, By child2) throws InterruptedException
	{
		Actions act=new Actions(driver);
		act.moveToElement(getElement(parent)).perform();
		Thread.sleep(3000);
		act.moveToElement(getElement(child)).perform();
		Thread.sleep(3000);
		driver.findElement(child2).click();
	}
}
