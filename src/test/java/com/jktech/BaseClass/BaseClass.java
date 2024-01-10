package com.jktech.BaseClass;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;


public class BaseClass {
	
	public static WebDriver driver;
	public static Logger log;
	
	
	@BeforeMethod
	@Parameters("br")
	public void setUp(String br) {
		log = org.apache.logging.log4j.LogManager.getLogger();
		if(br.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");		
			log.info("set the path of the chrome driver");
			driver = new ChromeDriver();
		}
		else if(br.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\Drivers\\geckodriver.exe");
			log.info("set the path of the firefox driver");
			driver = new FirefoxDriver();
			log.info("set the path of the driver");
		}
		else if(br.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", ".\\Drivers\\msedgedriver.exe");
			log.info("set the path of the edge driver");
			driver = new EdgeDriver();
			log.info("set the path of the driver");
		}		
	   driver.manage().window().maximize();
	   log.info("maximized the browser");
	   driver.get("https://www.saucedemo.com/");
	   log.info("opened the url");
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	
	public static void captureScreenShot(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"//Screenshots//"+tname+".png");
		//org.apache.commons.io.FileUtils.copyFile(source, target);
		FileUtils.copyFile(source, target);
		System.out.println("screenShot Taken");
		
		
	}
	
	

}
