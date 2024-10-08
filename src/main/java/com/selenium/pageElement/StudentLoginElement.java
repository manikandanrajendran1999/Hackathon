package com.selenium.pageElement;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentLoginElement {
	
	WebDriver driver;
	
	public StudentLoginElement(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Internet Check
	@FindBy (xpath = "(//div[normalize-space()='CHECK'])[1]")
	private WebElement internetCheck;
	
	//Camera Check
	@FindBy (xpath = "(//div[normalize-space()='CHECK'])[2]")
	private WebElement cameraCheck;
	
	//Audio Check
	@FindBy (xpath = "(//div[normalize-space()='CHECK'])[3]")
	private WebElement audioCheck;
	
	//Student Signin Button
	@FindBy (xpath = "//span[normalize-space()='STUDENT SIGN IN']")
	private WebElement studentSignInBtn;
	
	//Select session time
	@FindBy (xpath = "//div[@id='mat-select-value-3']")
	private WebElement selectSession;
	
	//Select your session
	@FindBy (xpath = "//div/mat-select[@role='combobox']")
	private WebElement selectYourSession;
	
	@FindBy (xpath = "//div/mat-option[@role='option']")
	private List<WebElement> sessionTimeList;
	
	//Session proceed button
	@FindBy (xpath = "//button/span[normalize-space()='PROCEED']")
	private WebElement sessionProceedBtn;
	
	//Select academic number
	@FindBy (xpath = "//div/mat-select[@role='combobox']")
	private WebElement selectAcademicNum;
	
	//Academic search
	@FindBy (xpath = "//input[@id='mat-input-0']")
	private WebElement academicSearch;
	
	//Select student
	@FindBy (xpath = "//mat-option[@role='option']")
	private WebElement selectStd;
	
	//Staff Sigin Button
	@FindBy (xpath = "//div/span[text()=' Staff Sign-In ']")
	private WebElement staffSignInBtn;
	
	//Network Admin
	@FindBy (xpath = "//span[normalize-space(text())='Network Admin']")
	private WebElement netWorkAdmin; 
	
	//Network Admin Email
	@FindBy (xpath = "//div/input[@type='email']")
	private WebElement emailField;
	
	//Network Admin Password
	@FindBy (xpath = "//div/input[@type='password']")
	private WebElement passField;
	
	//Network Admin Signin
	@FindBy (xpath = "//button/span[normalize-space(text())='SIGN IN']")
	private WebElement signInBtn;
	
	//Network Admin otp confirm
	@FindBy (xpath = "//button[@type='submit']")
	private WebElement submitBtn;
	
	//Self extracted mac address of this pc
	@FindBy (xpath = "//div/input[@formcontrolname='macAddress']")
	private WebElement macAddress;
	
	//Test center of this pc
	@FindBy (xpath = "//div[@class='mat-form-field-infix ng-tns-c57-7']")
	private WebElement testCenter;
	
	//Test center - list
	@FindBy (xpath = "//mat-option[@role='option']")
	private List<WebElement> testCenterList;
	
	//PC Number
	@FindBy (xpath = "//div/input[@formcontrolname='systemNo']")
	private WebElement sysInfo;
	
	//Save and Logout
	@FindBy (xpath = "//button/span[normalize-space()='SAVE & LOG OUT']")
	private WebElement saveAndLogout;
	
	//Identity Confirm
	@FindBy (xpath = "//div[@class='m-t-16']")
	private WebElement identityConfm;
	
	//Late Comers alert (Already session started)
	@FindBy (xpath = "//p[@class='digi-not-allow-late-comers ng-star-inserted']")
	private WebElement lateComersAlert;
	
	//Proceed button
	@FindBy (xpath = "//button/span[normalize-space()='PROCEED']")
	private WebElement confirmProceedBtn;
	
	//Accept popup
	@FindBy (xpath = "//span[normalize-space()='Yes']")
	private WebElement acceptPop;
	
	//Student Info Button
	@FindBy (xpath = "(//button[@type='submit'])[1]")
	private WebElement studentInfoNextBtn;
	
	//Student Authentication
	@FindBy (xpath = "(//mat-select[@role='combobox'])[2]")
	private WebElement stdAuthentication;
	
	//Face Atuthentication
	@FindBy (xpath = "(//mat-option[@role='option'])[1]")
	private WebElement faceAuthentication;
	
	//Password type Authendication
	@FindBy (xpath = "(//mat-option[@role='option'])[2]")
	private WebElement passwordAuthentication;
	
	//Password of authentication
	@FindBy (xpath = "//input[@id='auth-password']")
	private WebElement authenticationPass;
	
	//Authentication signin button
	@FindBy (xpath = "(//button[@type='submit'])[3]")
	private WebElement authenticationSigninBtn;
	
	//Next button after authentication
	@FindBy (xpath = "(//button[@type='submit'])[2]")
	private WebElement nextBtnAfterAuthentication;
	
	//Start exam button
	@FindBy (xpath = "//span[normalize-space()='START EXAM']")
	private WebElement startExamBtn;
	
	//Info button for students
	@FindBy (xpath = "//span/div[normalize-space()='info']")
	private WebElement infoBtnForStd;
	
	//Toaster message
	@FindBy (xpath = "//div[@id='toast-container']")
	private WebElement toasterMsg;
	
	//Pause exam
	@FindBy (xpath = "//mat-dialog-container/ng-component/div[@class='ng-star-inserted']")
	private WebElement pauseExamMessage;
	
	//Reset exam message
	@FindBy (xpath = "//mat-dialog-container/ng-component")
	private WebElement examResetMessage;
	
	//Reset exam start button 
	@FindBy (xpath = "//button/span[normalize-space()='START']")
	private WebElement resetExamStartBtn;
	
	//Student remining time
	@FindBy (css = "span.digi-time-text")
	private WebElement studentRemainingExamTime;
	
	//Re authentication message
	@FindBy (xpath = "//mat-dialog-container[@role='dialog']")
	private WebElement reAuthenticationMessage;
	
	//Re authentication logout
	@FindBy (xpath = "//button[@class='mat-focus-indicator mat-stroked-button mat-button-base mat-primary']")
	private WebElement reAuthenticationLogout;
	
	//Student terminate alert
	@FindBy (xpath = "//ng-component/div[@class='ng-star-inserted']")
	private WebElement studentTerminatedAlert;
	
	//Close exam and logout button for students
	@FindBy (xpath = "//button/span[normalize-space()='CLOSE EXAM & LOGOUT']")
	private WebElement closeExamAndLogoutBtn;
	
	//Terminate alert
	@FindBy (xpath = "//span[@class='p-l-8 p-t-16 ng-star-inserted']")
	private WebElement terminatedSigninAlert;
	
	//Terminate alert ok button
	@FindBy (xpath = "//span[normalize-space()='OK']")
	private WebElement terminatedAlertOkBtn;
	
	
	//Getters to access above webelements

	public WebElement getInternetCheck() {
		return internetCheck;
	}

	public WebElement getCameraCheck() {
		return cameraCheck;
	}

	public WebElement getAudioCheck() {
		return audioCheck;
	}

	public WebElement getStudentSignInBtn() {
		return studentSignInBtn;
	}

	public WebElement getSelectSession() {
		return selectSession;
	}

	public WebElement getSelectYourSession() {
		return selectYourSession;
	}

	public List<WebElement> getSessionTimeList() {
		return sessionTimeList;
	}

	public WebElement getSessionProceedBtn() {
		return sessionProceedBtn;
	}

	public WebElement getSelectAcademicNum() {
		return selectAcademicNum;
	}

	public WebElement getAcademicSearch() {
		return academicSearch;
	}

	public WebElement getSelectStd() {
		return selectStd;
	}

	public WebElement getStaffSignInBtn() {
		return staffSignInBtn;
	}

	public WebElement getNetWorkAdmin() {
		return netWorkAdmin;
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
	
	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getMacAddress() {
		return macAddress;
	}

	public WebElement getInfoBtnForStd() {
		return infoBtnForStd;
	}

	public WebElement getTestCenter() {
		return testCenter;
	}

	public List<WebElement> getTestCenterList() {
		return testCenterList;
	}

	public WebElement getSysInfo() {
		return sysInfo;
	}

	public WebElement getSaveAndLogout() {
		return saveAndLogout;
	}

	public WebElement getIdentityConfm() {
		return identityConfm;
	}

	public WebElement getLateComersAlert() {
		return lateComersAlert;
	}

	public WebElement getConfirmProceedBtn() {
		return confirmProceedBtn;
	}

	public WebElement getAcceptPop() {
		return acceptPop;
	}

	public WebElement getStudentInfoNextBtn() {
		return studentInfoNextBtn;
	}

	public WebElement getStdAuthentication() {
		return stdAuthentication;
	}

	public WebElement getFaceAuthentication() {
		return faceAuthentication;
	}

	public WebElement getPasswordAuthentication() {
		return passwordAuthentication;
	}

	public WebElement getAuthenticationPass() {
		return authenticationPass;
	}

	public WebElement getAuthenticationSigninBtn() {
		return authenticationSigninBtn;
	}

	public WebElement getNextBtnAfterAuthentication() {
		return nextBtnAfterAuthentication;
	}

	public WebElement getStartExamBtn() {
		return startExamBtn;
	}

	public WebElement getToasterMsg() {
		return toasterMsg;
	}

	public WebElement getPauseExamMessage() {
		return pauseExamMessage;
	}

	public WebElement getExamResetMessage() {
		return examResetMessage;
	}

	public WebElement getResetExamStartBtn() {
		return resetExamStartBtn;
	}

	public WebElement getStudentRemainingExamTime() {
		return studentRemainingExamTime;
	}

	public WebElement getReAuthenticationMessage() {
		return reAuthenticationMessage;
	}

	public WebElement getReAuthenticationLogout() {
		return reAuthenticationLogout;
	}

	public WebElement getStudentTerminatedAlert() {
		return studentTerminatedAlert;
	}

	public WebElement getCloseExamAndLogoutBtn() {
		return closeExamAndLogoutBtn;
	}

	public WebElement getTerminatedSigninAlert() {
		return terminatedSigninAlert;
	}

	public WebElement getTerminatedAlertOkBtn() {
		return terminatedAlertOkBtn;
	}

}
