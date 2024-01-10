package com.jktech.TestCases;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.jktech.BaseClass.BaseClass;
import com.jktech.pages.LoginPage;

import Utility.ReadingFromExcelUtility;


public class LoginTest extends BaseClass {

	LoginPage login;

	@Test(dataProvider = "data")
	public void validLoginTest(String UN, String PW) throws Exception {
		login = new LoginPage(driver);
		String currentTitle= "https://www.saucedemo.com/inventory.html";
		log.info("Created the object for login page under login test case");
		login.setUserName(UN);
		log.info("entered the user name in login test case");
		login.setPassword(PW);
		log.info("entered the password in login test case");
		login.clickLogin();
		log.info("clicked the login button in login test case");
		Thread.sleep(3000);
		if(login.title().equalsIgnoreCase(currentTitle)) {
			System.out.println("Credentials are validated and navigated to home page");
			log.info("Tets case is passed for : "+ UN + " and "+ PW);
		}
		else {
			captureScreenShot(driver, "validLoginTest");
			log.info("Screen shot captured");
			Assert.assertEquals(login.title(), currentTitle);
			log.info("Tets case is failed for : "+ UN + " and "+ PW);
		}

	}
	

	@DataProvider(name = "data")
	public String[][] datafromexternalmethod() throws Exception {

		/*
		 * Object[][] obj = { { "standard_user", "secret_sauce" }, { "locked_out_user",
		 * "secret_sauce" }, { "problem_user", "secret_sauce" } }; return obj;
		 */
				
		String path= System.getProperty("user.dir") + "\\TestData\\DataForPOM.xlsx";
		int rownum=ReadingFromExcelUtility.getRowCount(path, "Sheet1");		// row count
		int colnum=ReadingFromExcelUtility.getCellCount(path,"Sheet1",1);	// cell count
		
		String registerdata[][]=new String[rownum][colnum];
		
		for(int i=1;i<=rownum;i++)			// to iterate the row
		{
			for(int j=0;j<colnum;j++)	//	to iterate the column
			{
				registerdata[i-1][j]=ReadingFromExcelUtility.getCellData(path,"Sheet1", i,j);//0 0
			}
				
		}
	return registerdata;

	}

}
