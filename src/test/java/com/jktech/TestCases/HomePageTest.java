package com.jktech.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.jktech.BaseClass.BaseClass;
import com.jktech.pages.HomePage;
import com.jktech.pages.LoginPage;

public class HomePageTest extends BaseClass{
	
	HomePage homePage;
	LoginTest login;
	
	
	@Test(dependsOnMethods = "validLoginTest")
	public void sampleTest() throws Exception {
		login = new LoginTest();
		login.validLoginTest("standard_user", "standard_user");		
		homePage = new HomePage(driver);
		System.out.println("new changes");
		
	}

}
