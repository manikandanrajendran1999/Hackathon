package com.selenium.pageElement;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExamScreenElement {
	
WebDriver driver;
	
	public ExamScreenElement(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//View session
	@FindBy (xpath = "//button/span[normalize-space()='VIEW SESSION']")
	private List<WebElement> viewSessionBtn;
	
	//couse list
	@FindBy (xpath = "//tr/td[1]/div[@class='ng-star-inserted']")
	private List<WebElement> courseList;
	
	//Session status
	@FindBy (xpath = "//div/p[@class='m-0 f-s-14 f-w-500 digi-status-bg p-8']")
	private List<WebElement> sessionStatus;
	
	//Students tab
	@FindBy (xpath = "//a/span[normalize-space()='Students']")
	private WebElement studentsTab;
	
	//Data per page
	@FindBy (xpath = "//span[normalize-space()='30']")
	private WebElement dataPerPage;
	
	//All center filter
	@FindBy (xpath = "(//div/mat-select/div)[2]")
	private WebElement allCenterFilter;
	
	//Exam Center's list
	@FindBy (xpath = "(//div/mat-option[@role='option'])[2]")
	private List<WebElement> firstCenter;
	
	//Student Academin Number in table
	@FindBy (xpath = "//td[1]")
	private List<WebElement> academicNumber;
	
	//Test Center in table
	@FindBy (xpath = "//td[3]")
	private List<WebElement> testCenterName;
	
	//Course Status in table
	@FindBy (xpath = "//td[6]")
	private List<WebElement> currentStatus;
	
	//Extra Time in table
	@FindBy (xpath = "//td[7]")
	private List<WebElement> extraTime;
	
	//More options in table
	@FindBy (xpath = "//div[@class='digi-circle-icon-container p-4 cursor-pointer']")
	private List<WebElement> moreOption;
	
	//Menu items
	@FindBy (xpath = "//button[@role='menuitem']")
	private List<WebElement> moreItems;
	
	//Extra Time and Reason
	@FindBy (xpath = "//div/mat-chip[contains(@class,'mat-chip mat-focus-indicator')]")
	private List<WebElement> extraTimeAndReason;
	
	//Refresh Button
	@FindBy (xpath = "//div/mat-icon[normalize-space()='refresh']")
	private WebElement refreshBtn;
	
	//Extra Time Submit Button
	@FindBy (xpath = "//button/span[text()=' SUBMIT ']")
	private WebElement extraTimeSubmitBtn;
	
	//Student Search Box
	@FindBy (xpath = "//input[@placeholder='Search']")
	private WebElement studentSearchBox;
	
	
	//Getters to access above webelements

	public List<WebElement> getViewSessionBtn() {
		return viewSessionBtn;
	}

	public List<WebElement> getSessionStatus() {
		return sessionStatus;
	}

	public List<WebElement> getCourseList() {
		return courseList;
	}

	public List<WebElement> getExtraTimeAndReason() {
		return extraTimeAndReason;
	}

	public WebElement getStudentsTab() {
		return studentsTab;
	}

	public WebElement getDataPerPage() {
		return dataPerPage;
	}

	public WebElement getAllCenterFilter() {
		return allCenterFilter;
	}

	public List<WebElement> getFirstCenter() {
		return firstCenter;
	}

	public List<WebElement> getAcademicNumber() {
		return academicNumber;
	}

	public List<WebElement> getTestCenterName() {
		return testCenterName;
	}

	public List<WebElement> getCurrentStatus() {
		return currentStatus;
	}

	public List<WebElement> getExtraTime() {
		return extraTime;
	}

	public List<WebElement> getMoreOption() {
		return moreOption;
	}

	public List<WebElement> getMoreItems() {
		return moreItems;
	}

	public List<WebElement> getExtraTimeAndreason() {
		return extraTimeAndReason;
	}

	public WebElement getRefreshBtn() {
		return refreshBtn;
	}

	public WebElement getExtraTimeSubmitBtn() {
		return extraTimeSubmitBtn;
	}

	public WebElement getStudentSearchBox() {
		return studentSearchBox;
	}
	

}
