package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.qa.opencart.util.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public optionsManager opManager;
	public FileInputStream io;
	public static ThreadLocal<WebDriver> tldriver=new ThreadLocal<>();
	public static final Logger log=LogManager.getLogger("DriverFactory");
	
	/**
	 * This method used to initialize on basis of browser name.
	 * This will use for remote + local execution
	 * @param prop
	 * @return
	 */
	public WebDriver init_driver(Properties prop)
	{
		String browser=prop.getProperty("Browser").trim();
		highlight=prop.getProperty("highlight").trim();
		opManager=new optionsManager(prop);
		log.info("Browser name:"+browser);
		
		if(browser.equalsIgnoreCase(Browser.CHROME_BROWSER_VAULE))
		{
			log.info("running test cases on chrome...");
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(opManager.getChromeOptions());
			tldriver.set(new ChromeDriver(opManager.getChromeOptions()));
		}
		else if(browser.equalsIgnoreCase(Browser.GECKO_BROWSER_VAULE))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver= new FirefoxDriver();
			tldriver.set(new FirefoxDriver());
		}
		else if(browser.equalsIgnoreCase(Browser.IE_BROWSER_VAULE))
		{
			WebDriverManager.iedriver().setup();
			//driver= new InternetExplorerDriver();
			tldriver.set(new InternetExplorerDriver());
		}
		else {
			System.out.println("enter valid browser name");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
		
	}
	
	
	public static WebDriver getDriver()
	{
		return tldriver.get();
	}
	
	/**
	 * This method initialize the property on the basis of given environment
	 * i.e. QA/DEV/STAGE/PROD
	 * @return
	 */
	public Properties init_Prop()
	{
		prop=new Properties();
		try {
			//FileInputStream io= new FileInputStream("D:\\Sayali\\IPRU\\ECLIPSE_WORKSPACE\\FinalProj\\resources\\Config\\config.properties");
			//prop.load(io);
			
			//add logic for multiple env
			String env=System.getProperty("env");
			if (env==null)
			{
				//System.out.println("no environment is provided..hence running on QA env..");
				log.info("no environment is provided..hence running on QA env..");
				 io= new FileInputStream("D:\\Sayali\\IPRU\\ECLIPSE_WORKSPACE\\FinalProj\\resources\\Config\\config.properties");
				prop.load(io);
			}
			else
			{
				switch(env.toLowerCase()) {
				case "qa" :
					io= new FileInputStream("D:\\Sayali\\IPRU\\ECLIPSE_WORKSPACE\\FinalProj\\resources\\Config\\qa.config.properties");
					prop.load(io);
					break;
				case "stage" :
					io= new FileInputStream("D:\\Sayali\\IPRU\\ECLIPSE_WORKSPACE\\FinalProj\\resources\\Config\\stage.config.properties");
					prop.load(io);
					break;
				case "prod" :
					io= new FileInputStream("D:\\Sayali\\IPRU\\ECLIPSE_WORKSPACE\\FinalProj\\resources\\Config\\prod.config.properties");
					prop.load(io);
					break;
				case "dev" :
					io= new FileInputStream("D:\\Sayali\\IPRU\\ECLIPSE_WORKSPACE\\FinalProj\\resources\\Config\\dev.config.properties");
					prop.load(io);
					break;
				default:
					//System.out.println("Please provide valid Environment!!!");
					log.error("Please provide valid Environment!!!");
				}
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("exception in config file accessing");
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	/**
	 * take screenshot
	 * @return 
	 */
	public static String getScreenshot()
	{
		File src=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"screenshot/"+System.currentTimeMillis()+".png";
		File des= new File(path);
		
		try {
			FileUtils.copyFile(src, des);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	
}
