package com.jktech.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	
	
	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);		// it is going instantiate all the element in one go
	}
	
	
	//WebElement drag = driver.findElement(By.id("draggable"));
	@FindBy(id="user-name")
	WebElement userName;
	
	@FindBy(name="password")
	WebElement password;

	
	@FindBy(how=How.ID, using="login-button")		//@FindBy(id="login-button")
	WebElement loginButton;
	
	@FindBy(xpath="//div[contains(text(),'Swag Labs')]")
	WebElement homePage;
	
	
	
	public void setUserName(String name) {
		userName.sendKeys(name);
	}
	
	public void setPassword(String PWname) {
		password.sendKeys(PWname);
	}
	
	public void clickLogin() {
		loginButton.click();
	}
	
	public String title() {
		String title = ldriver.getCurrentUrl();
		return title;
	}
	
	
	

}
