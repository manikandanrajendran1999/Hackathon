package com.StepDefination;

import com.event.base.Hooks;
import com.event.base.LibraryClass;
import com.selenium.pageElement.LoginPageEement;

import io.cucumber.java.en.Given;

public class LoginStep {
	
	private Hooks base;
	private LoginPageEement login;
	private LibraryClass library;

	public LoginStep(Hooks base, LibraryClass library) {
		this.base = base;
		this.library = library;
	}

	@Given("Login as a Admin")
	public void login_as_a_admin() throws Exception {
		base.launchBrowser();
		login = new LoginPageEement(base.getDriver());
		Thread.sleep(2000);
		library.loaderWaitUntilDisappear(base.getDriver(), 40);
		login.getStaffSignInBtn().click();
		Thread.sleep(2000);
		login.getCourseCoordinator().click();
		login.getEmailField().sendKeys(base.getConfigData("coordinatorEmail"));
		login.getPassField().sendKeys(base.getConfigData("coordinatorPassword"));
		login.getSignInBtn().click();
		library.waitForClickableElement(base.getDriver(), login.getSubmitBtn(), 20);
		login.getSubmitBtn().click();
	}
	
}
