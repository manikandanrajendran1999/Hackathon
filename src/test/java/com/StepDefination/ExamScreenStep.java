package com.StepDefination;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.PageFactory;

import com.event.base.Hooks;
import com.event.base.LibraryClass;
import com.event.base.TimeConvertIntoSeconds;
import com.selenium.pageElement.ExamScreenElement;
import com.selenium.pageElement.StudentLoginElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class ExamScreenStep {

	private Hooks base;
	private ExamScreenElement examScreen;
	private StudentLoginElement studenLogin;
	private LibraryClass library;
	private JavascriptExecutor js;
	private TimeConvertIntoSeconds timeClass;

	public WebDriver tempDriver;
	String incognitoWindowHandle = "";
	String mainWindowHandle;

	/*=========================================================================
	 * If you want to run a particular student
	 * 1.please provide the their acc ID and Center name
	 * 2. Command the - And Check the "Not Reported" student - this feature file 
	 ==========================================================================*/
	
	String StudentAcademicId = "23010006";
	String firstCenterName = "COMPUTER LAB";

	public ExamScreenStep(Hooks base, LibraryClass library, TimeConvertIntoSeconds timeClass) throws IOException {
		this.base = base;
		this.library = library;
		this.timeClass = timeClass;
		PageFactory.initElements(base.getDriver(), this);
		PageFactory.initElements(tempDriver, this);

		ChromeOptions chromeOptions = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromeOptions.addArguments("--incognito");
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
		chromeOptions.setExperimentalOption("prefs", chromePrefs);
		chromeOptions.addArguments("--test-type");
		chromeOptions.addArguments("--disable-extensions"); // to disable browser extension popup
		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments("use-fake-ui-for-media-stream");
		chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		tempDriver = new ChromeDriver(chromeOptions);
		tempDriver.manage().window().maximize();
		tempDriver.get(base.getConfigData("stageURLWithMacId"));
	}

	@When("switch to the incognito window")
	public void switchToIncognitoWindow() throws IOException, InterruptedException {
		tempDriver.switchTo().window(incognitoWindowHandle);
		Thread.sleep(3000);
		System.out.println("Switched to the incognito window.");
	}

	@When("switch back to the main window")
	public void switchToMainWindow() throws Exception {
		mainWindowHandle = base.getDriver().getWindowHandle();
		base.getDriver().switchTo().window(mainWindowHandle);
		System.out.println("Switched back to the main window.");
	}

	@And("User click the view session button")
	public void user_click_the_view_session_button() throws IOException, InterruptedException {
		examScreen = new ExamScreenElement(base.getDriver());
		Thread.sleep(2000);
		try {
			List<WebElement> viewSessionBtn = examScreen.getViewSessionBtn();
			
			//Select the ongoing session even closed session is listing the screen
			for (int i = 0; i < viewSessionBtn.size(); i++) {
				String courseName = examScreen.getCourseList().get(i).getText();
				if (courseName.contains("AUTOMATION")) {
					viewSessionBtn.get(i).click();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("No session found");
			e.printStackTrace();
		}
	}

	@And("User select the students tab")
	public void user_select_the_students_tab() {
		try {
			examScreen.getStudentsTab().click();
		} catch (Exception e) {
			System.out.println("No session found");
			e.printStackTrace();
		}
	}

	@And("Check the {string} student")
	public void check_the_student(String status) throws InterruptedException, IOException {
		Thread.sleep(3000);
		examScreen.getAllCenterFilter().click();
		List<WebElement> firstCenter = examScreen.getFirstCenter();
		for (int i = 0; i < firstCenter.size(); i++) {
			firstCenterName = firstCenter.get(i).getText();
			System.out.println("Selected Center Name : " + firstCenterName);
			firstCenter.get(i).click();
			break;
		}
		library.waitForClickableElement(base.getDriver(), examScreen.getDataPerPage(), 20);
		examScreen.getDataPerPage().click();
		List<WebElement> currentStatus = examScreen.getCurrentStatus();
		List<WebElement> academicNumber = examScreen.getAcademicNumber();

		Thread.sleep(2000);
		for (int i = 0; i < currentStatus.size(); i++) {
			if (currentStatus.get(i).getText().contains(status)) {
				System.out.println("Selected student status :" + currentStatus.get(i).getText());
				StudentAcademicId = academicNumber.get(i).getText();
				System.out.println("Not Answered Student academic ID : " + StudentAcademicId);
				Thread.sleep(4000);
				break;
			}
		}
	}

		@And("User login as a student")
	public void user_login_as_a_student() throws Exception {
		studenLogin = new StudentLoginElement(tempDriver);
		library.loaderWaitUntilDisappear(tempDriver, 100);
		library.waitForClickableElement(tempDriver, studenLogin.getStaffSignInBtn(), 20);
		studenLogin.getStaffSignInBtn().click();
		library.waitForClickableElement(tempDriver, studenLogin.getNetWorkAdmin(), 20);
		studenLogin.getNetWorkAdmin().click();
		library.waitForClickableElement(tempDriver, studenLogin.getEmailField(), 20);
		studenLogin.getEmailField().sendKeys(base.getConfigData("coordinatorEmail"));
		library.waitForClickableElement(tempDriver, studenLogin.getPassField(), 20);
		studenLogin.getPassField().sendKeys(base.getConfigData("coordinatorPassword"));
		library.waitForClickableElement(tempDriver, studenLogin.getSignInBtn(), 20);
		studenLogin.getSignInBtn().click();
		library.waitForClickableElement(tempDriver, studenLogin.getSubmitBtn(), 20);
		studenLogin.getSubmitBtn().click();
		library.waitForClickableElement(tempDriver, studenLogin.getMacAddress(), 20);
		studenLogin.getMacAddress().sendKeys(base.getConfigData("macAddress"));
		library.waitForClickableElement(tempDriver, studenLogin.getTestCenter(), 20);
		studenLogin.getTestCenter().click();
		Thread.sleep(1000);
		List<WebElement> testCenterList = studenLogin.getTestCenterList();
		for (int i = 0; i < testCenterList.size(); i++) {
			if (testCenterList.get(i).getText().contains(firstCenterName)) {
				Thread.sleep(2000);
				testCenterList.get(i).click();
				break;
			}
		}
		library.waitForClickableElement(tempDriver, studenLogin.getSysInfo(), 20);
		studenLogin.getSysInfo().sendKeys(base.getConfigData("macAddress"));
		library.waitForClickableElement(tempDriver, studenLogin.getSaveAndLogout(), 20);
		studenLogin.getSaveAndLogout().click();
		library.loaderWaitUntilDisappear(tempDriver, 100);
		library.waitForClickableElement(tempDriver, studenLogin.getInternetCheck(), 20);
		studenLogin.getInternetCheck().click();
		library.waitForClickableElement(tempDriver, studenLogin.getCameraCheck(), 20);
		studenLogin.getCameraCheck().click();
		library.waitForClickableElement(tempDriver, studenLogin.getAudioCheck(), 20);
		studenLogin.getAudioCheck().click();
		Thread.sleep(1000);
		library.waitForClickableElement(tempDriver, studenLogin.getStudentSignInBtn(), 20);
		studenLogin.getStudentSignInBtn().click();
		
		//Select the session while student login
		Thread.sleep(1000);
		library.waitForClickableElement(tempDriver, studenLogin.getSelectYourSession(), 20);
		studenLogin.getSelectYourSession().click();
		List<WebElement> sessionTimeList = studenLogin.getSessionTimeList();
		for (int i = 0; i < sessionTimeList.size(); i++) {
			sessionTimeList.get(i).click();
		}
		
		library.waitForClickableElement(tempDriver, studenLogin.getSessionProceedBtn(), 20);
		studenLogin.getSessionProceedBtn().click();
		Thread.sleep(2000);
		library.waitForClickableElement(tempDriver, studenLogin.getSelectAcademicNum(), 20);
		studenLogin.getSelectAcademicNum().click();
		library.waitForClickableElement(tempDriver, studenLogin.getAcademicSearch(), 20);
		studenLogin.getAcademicSearch().click();
		library.waitForClickableElement(tempDriver, studenLogin.getAcademicSearch(), 20);
		studenLogin.getAcademicSearch().sendKeys(StudentAcademicId);
		library.waitForClickableElement(tempDriver, studenLogin.getSelectStd(), 20);
		studenLogin.getSelectStd().click();
		try {
			library.waitForVisibilityOfElement(tempDriver, studenLogin.getIdentityConfm(), 20);
			String identity = studenLogin.getIdentityConfm().getText();
			System.out.println(identity.replaceAll("\n", " ").replaceAll(" +", " "));
		} catch (Exception e) {
			System.out.println("Plesae check if the student already start the exam or terminated");
			e.printStackTrace();
		}
		library.waitForClickableElement(tempDriver, studenLogin.getSessionProceedBtn(), 20);
		studenLogin.getSessionProceedBtn().click();
		library.waitForClickableElement(tempDriver, studenLogin.getAcceptPop(), 20);
		studenLogin.getAcceptPop().click();
		library.waitForClickableElement(tempDriver, studenLogin.getStudentInfoNextBtn(), 20);
		studenLogin.getStudentInfoNextBtn().click();
	}

	@And("Student done the authentication")
	public void student_done_the_authentication() throws InterruptedException, IOException {
		studenLogin = new StudentLoginElement(tempDriver);
		Thread.sleep(2000);
		library.waitForClickableElement(tempDriver, studenLogin.getStdAuthentication(), 20);
		studenLogin.getStdAuthentication().click();
		library.waitForClickableElement(tempDriver, studenLogin.getPasswordAuthentication(), 20);
		studenLogin.getPasswordAuthentication().click();
		library.waitForClickableElement(tempDriver, studenLogin.getAuthenticationPass(), 20);
		studenLogin.getAuthenticationPass().click();
		studenLogin.getAuthenticationPass().sendKeys(base.getConfigData("studentPassword"));
		library.waitForClickableElement(tempDriver, studenLogin.getAuthenticationSigninBtn(), 20);
		studenLogin.getAuthenticationSigninBtn().click();
		library.waitForClickableElement(tempDriver, studenLogin.getNextBtnAfterAuthentication(), 20);
		studenLogin.getNextBtnAfterAuthentication().click();
		Thread.sleep(2000);
	}

	@And("Student start the exam")
	public void student_start_the_exam() throws InterruptedException {
		studenLogin = new StudentLoginElement(tempDriver);
		try {
			library.waitForClickableElement(tempDriver, studenLogin.getStartExamBtn(), 20);
			studenLogin.getStartExamBtn().click();
		} catch (Exception e) {
			System.out.println("Session not found");
			e.printStackTrace();
		}
	}

	@And("Click more options for the exam {string} student")
	public void click_more_options_for_the_exam_student(String status) throws InterruptedException, IOException {
//		Thread.sleep(2000);
//		examScreen.getDataPerPage().click();
		library.waitForClickableElement(base.getDriver(), examScreen.getStudentSearchBox(), 20);
		examScreen.getStudentSearchBox().clear();
		examScreen.getStudentSearchBox().sendKeys(StudentAcademicId);
		Thread.sleep(2000);

		List<WebElement> academicNumber = examScreen.getAcademicNumber();

		for (int i = 0; i < academicNumber.size(); i++) {

			String academicIdText = examScreen.getAcademicNumber().get(i).getText();

			if (academicIdText.contains(StudentAcademicId)) {
				System.out.println("Selected student Academic ID: " + academicIdText);
				List<WebElement> currentStatus = examScreen.getCurrentStatus();
				String statusText = currentStatus.get(i).getText();
				if (statusText.contains(status)) {
					System.out.println("The Student successfully started the exam");
					List<WebElement> moreOption = examScreen.getMoreOption();
					Thread.sleep(2000);
					moreOption.get(i).click();
				} else {
					System.out.println("Student has not started the exam");
				}
				break;
			}

		}
	}

	@And("Click {string} for that student")
	public void click_for_that_student(String string) throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> moreItems = examScreen.getMoreItems();
		for (int i = 0; i < moreItems.size(); i++) {
			if (moreItems.get(i).getText().contains(string)) {
				moreItems.get(i).click();
				break;
			}
		}

	}

	@And("User give the {string} mins time to that student")
	public void user_give_the_mins_time_to_that_student(String time) throws InterruptedException, IOException {
		try {
		examScreen = new ExamScreenElement(base.getDriver());
		List<WebElement> extraTimeAndreason = examScreen.getExtraTimeAndreason();
		Thread.sleep(2000);
		for (int i = 0; i < extraTimeAndreason.size(); i++) {
			if (extraTimeAndreason.get(i).getText().contains(time)) {
				System.out.println("Given time is :" + extraTimeAndreason.get(i).getText());
				extraTimeAndreason.get(i).click();
				break;
			}
		}
		}catch (Exception e) {
			System.out.println("Please check the given extra time is there in list");
			e.printStackTrace();
		}
	}

	@And("User give {string} reason")
	public void user_give_reason(String reason) throws InterruptedException, IOException {
		js = (JavascriptExecutor)base.getDriver();
		new StudentLoginElement(base.getDriver());

		List<WebElement> extraTimeAndreason = examScreen.getExtraTimeAndreason();
		Thread.sleep(3000);
		for (int i = 0; i < extraTimeAndreason.size(); i++) {
			if (extraTimeAndreason.get(i).getText().contains(reason)) {
				System.out.println("Given reason : " + extraTimeAndreason.get(i).getText());
				extraTimeAndreason.get(i).click();
				break;
			}
		}
		js.executeScript("arguments[0].scrollIntoView(true);", examScreen.getExtraTimeSubmitBtn());
		Thread.sleep(2000);
		examScreen.getExtraTimeSubmitBtn().click();
//		String toastMsg = studenLoginforstaff.getToasterMsg().getText();
//		System.out.println("Successfully : " + toastMsg);
	}

	@And("Check the extra time added to the student")
	public void check_the_extra_time_added_to_the_student() throws InterruptedException {
		library.waitForClickableElement(tempDriver, examScreen.getRefreshBtn(), 20);
		examScreen.getRefreshBtn().click();
		library.waitForClickableElement(tempDriver, examScreen.getStudentSearchBox(), 20);
		examScreen.getStudentSearchBox().clear();
		examScreen.getStudentSearchBox().sendKeys(StudentAcademicId);
		Thread.sleep(2000);

		List<WebElement> academicNumber = examScreen.getAcademicNumber();

		for (int i = 0; i < academicNumber.size(); i++) {

			String academicIdText = examScreen.getAcademicNumber().get(i).getText();

			if (academicIdText.contains(StudentAcademicId)) {
				String extraTime = examScreen.getExtraTime().get(i).getText();
				if (extraTime != null) {
					System.out.println("Given Extra Time is : " + extraTime);
				} else {
					System.out.println("Extra Time is not added");
				}
				break;
			}

		}

	}

	@And("Check the given extra time")
	public void check_the_given_extra_time() throws InterruptedException {
		studenLogin = new StudentLoginElement(tempDriver);
		try {
		Actions a = new Actions(tempDriver);
		a.moveToElement(studenLogin.getInfoBtnForStd()).perform();
		@Nullable
		String toastMsg = studenLogin.getToasterMsg().getText();
		System.out.println("Successfully student get the extra time :" + toastMsg);
		}catch (Exception e) {
			System.out.println("Extra time is not added");
			e.printStackTrace();
		}
	}
	

	@And("Student check the paused exam message and resume time")
	public void student_check_the_paused_exam_message_and_resume_time() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("Paused message and Resume Time : (" + studenLogin.getPauseExamMessage().getText() + ")");
	}

	@And("Check the student remaining time")
	public void check_the_student_remaining_time() throws InterruptedException {
		Thread.sleep(2000);
		try {
		String remainingTime = studenLogin.getStudentRemainingExamTime().getText();
		System.out.println("Remaining Time - (" + remainingTime + ")");
		}catch (Exception e) {
			System.out.println("Check the exam has finished");
			e.printStackTrace();
		}

	}

	@And("Student check if the exam has resume")
	public void student_check_if_the_exam_has_resume() {
		try {

			// Check if the element is null
			if (studenLogin.getStudentRemainingExamTime().isDisplayed()) {
				System.out.println("Successfully exam has been resumed");
			} else {
				System.out.println("Exam has not resumed... Please check");
			}

		} catch (Exception e) {
			System.out.println("Exam has not resumed... Please check");
			e.printStackTrace();
		}
	}

	@And("User re-start the exam as a student")
	public void user_re_start_the_exam_as_a_student() {
		try {
			if (studenLogin.getExamResetMessage().isDisplayed()) {
				System.out.println(studenLogin.getExamResetMessage().getText());
				Thread.sleep(3000);
				studenLogin.getResetExamStartBtn().click();
			}

		} catch (Exception e) {
			System.out.println("Exam not restarted, Please check it");
			e.printStackTrace();
		}
	}

	@And("Click the {string} button as staff")
	public void click_the_button_as_staff(String string) {
		try {
			WebElement element = base.getDriver()
					.findElement(By.xpath("//button//span[normalize-space()='" + string + "']"));
			if (element.isDisplayed()) {
				element.click();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("Click the {string} button as student")
	public void click_the_button_as_student(String string) {
		try {
			WebElement element = tempDriver.findElement(By.xpath("//button//span[normalize-space()='" + string + "']"));
			if (element.isDisplayed()) {
				element.click();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("User check the re authentication as a student")
	public void user_check_the_re_authentication_as_a_student() {
		try {

			if (studenLogin.getReAuthenticationMessage().isDisplayed()) {
				System.out.println("Re-authentication dialog is displayed.");

				String countdownMessage = studenLogin.getReAuthenticationMessage().getText();
				System.out.println("Countdown Message: " + countdownMessage);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("User check the terminated alert and close the exam")
	public void user_check_the_terminated_alert_and_close_the_exam() {
		studenLogin = new StudentLoginElement(tempDriver);
		try {

			if (studenLogin.getStudentTerminatedAlert().isDisplayed()) {
				System.out.println(studenLogin.getStudentTerminatedAlert().getText());
				// click the close exam and logout button
				Thread.sleep(2000);
				studenLogin.getCloseExamAndLogoutBtn().click();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@And("Try to sign as a terminated student")
	public void try_to_sign_as_a_terminated_student() throws InterruptedException, IOException {
		studenLogin = new StudentLoginElement(tempDriver);
		library.loaderWaitUntilDisappear(tempDriver, 100);
		library.waitForClickableElement(tempDriver, studenLogin.getInternetCheck(), 20);
		studenLogin.getInternetCheck().click();
		library.waitForClickableElement(tempDriver, studenLogin.getCameraCheck(), 20);
		studenLogin.getCameraCheck().click();
		library.waitForClickableElement(tempDriver, studenLogin.getAudioCheck(), 20);
		studenLogin.getAudioCheck().click();
		library.waitForClickableElement(tempDriver, studenLogin.getStudentSignInBtn(), 20);
		studenLogin.getStudentSignInBtn().click();
		library.waitForClickableElement(tempDriver, studenLogin.getSessionProceedBtn(), 20);
		studenLogin.getSessionProceedBtn().click();
		Thread.sleep(2000);
		library.waitForClickableElement(tempDriver, studenLogin.getSelectAcademicNum(), 20);
		studenLogin.getSelectAcademicNum().click();
		Thread.sleep(2000);
		library.waitForClickableElement(tempDriver, studenLogin.getAcademicSearch(), 20);
		studenLogin.getAcademicSearch().click();
		library.waitForClickableElement(tempDriver, studenLogin.getAcademicSearch(), 20);
		studenLogin.getAcademicSearch().sendKeys(StudentAcademicId);
		library.waitForClickableElement(tempDriver, studenLogin.getSelectStd(), 20);
		studenLogin.getSelectStd().click();
		Thread.sleep(2000);
		try {
			library.waitForClickableElement(tempDriver, studenLogin.getTerminatedSigninAlert(), 20);
		if (studenLogin.getTerminatedSigninAlert().isDisplayed()) {
			System.out.println(studenLogin.getTerminatedSigninAlert().getText());

			// Click ok in the popup
			studenLogin.getTerminatedAlertOkBtn().click();
		}}catch (Exception e) {
			System.out.println("Student is not terminated. Please check it once");
			e.printStackTrace();
		}

	}

	@And("Check the student status changed as {string} in controller screen")
	public void check_the_student_status_changed_as_in_controller_screen(String status) throws InterruptedException, IOException {

		library.waitForClickableElement(base.getDriver(), examScreen.getRefreshBtn(), 30);
		examScreen.getRefreshBtn().click();
		Thread.sleep(3000);
		examScreen.getStudentSearchBox().clear();
		examScreen.getStudentSearchBox().sendKeys(StudentAcademicId);
		Thread.sleep(3000);

		List<WebElement> academicNumber = examScreen.getAcademicNumber();

		for (int i = 0; i < academicNumber.size(); i++) {

			String academicIdText = examScreen.getAcademicNumber().get(i).getText();

			if (academicIdText.contains(StudentAcademicId)) {
				System.out.println("Selected Academic ID: " + academicIdText);
				List<WebElement> currentStatus = examScreen.getCurrentStatus();
				String statusText = currentStatus.get(i).getText();
				if (statusText.contains(status)) {
					System.out.println("Sucessfully student " + status +"in the exam");
				} else {
					System.out.println("Student not " + status + ". Student status is : " + statusText);
				}
				break;
			}

		}

	}
	
	//===================
	String initialRemainingTime;
    String finalRemainingTime;

    @And("Check the student remaining time before {string}")
    public void check_the_student_remaining_time_before(String string) throws InterruptedException {
        Thread.sleep(2000); // Simulate a delay
        try {
            initialRemainingTime = studenLogin.getStudentRemainingExamTime().getText(); // Fetch remaining time
            System.out.println("Remaining Time Before "+ string + " : " + initialRemainingTime);
        } catch (Exception e) {
            System.out.println("Check if the exam has finished");
            e.printStackTrace();
        }
    }

    @And("Check the student remaining time after {string}")
    public void check_the_student_remaining_time_after(String string) throws InterruptedException {
        Thread.sleep(2000); // Simulate a delay
        try {
            finalRemainingTime = studenLogin.getStudentRemainingExamTime().getText(); // Fetch updated time
            System.out.println("Remaining Time After " + string + " : " + finalRemainingTime);

         // Convert initial and final time into total seconds
            int initialTimeInSeconds = timeClass.extractTotalSeconds(initialRemainingTime);
            int finalTimeInSeconds = timeClass.extractTotalSeconds(finalRemainingTime);
            
            if (finalTimeInSeconds >= initialTimeInSeconds) {
                System.out.println("Time has been increased successfully");
            } else {
                System.out.println("Error: Extra time has not been added successfully");
            }
//            timeClass.verifyExtraTimeAdded(initialRemainingTime, finalRemainingTime);
        } catch (Exception e) {
            System.out.println("Check if the exam has finished");
            e.printStackTrace();
        }
    }



}
