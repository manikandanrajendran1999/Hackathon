package com.selenium.pageElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.event.base.Hooks;


public class LoginPageEement extends Hooks {

WebDriver driver;
	
	public LoginPageEement(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Staff Signin Button
	@FindBy (xpath = "//div/span[normalize-space()='Staff Sign-In']")
	private WebElement staffSignInBtn;
	
	//Network Admin radio button
	@FindBy (xpath = "//span[normalize-space(text())='Network Admin']")
	private WebElement networkAdmin;
	
	//Co-ordinator radio button
	@FindBy (xpath = "//span[normalize-space(text())='Exam Coordinator']")
	private WebElement courseCoordinator; 
	
	//Staff Email
	@FindBy (xpath = "//div/input[@type='email']")
	private WebElement emailField;
	
	//Staff Password
	@FindBy (xpath = "//div/input[@type='password']")
	private WebElement passField;
	
	//Signin button
	@FindBy (xpath = "//button/span[normalize-space()='SIGN IN']")
	private WebElement signInBtn;
	
	//Submit button
	@FindBy (xpath = "//button[@type='submit']")
	private WebElement submitBtn;
	
	//Loader
	@FindBy (xpath = "//div/img[@alt='loader']")
	private WebElement loaderInDE;
	
	
	//Getters to access above webelements

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getStaffSignInBtn() {
		return staffSignInBtn;
	}

	public WebElement getNetworkAdmin() {
		return networkAdmin;
	}

	public WebElement getCourseCoordinator() {
		return courseCoordinator;
	}

	public WebElement getEmailField() {
		return emailField;
	}

	public WebElement getPassField() {
		return passField;
	}

	public WebElement getSignInBtn() {
		return signInBtn;
	}

	public WebElement getLoaderInDE() {
		return loaderInDE;
	}
	
}
