package com.event.base;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.pageElement.LoginPageEement;


public class LibraryClass {
	
private Hooks base;
	WebDriver driver;
	LoginPageEement login;
	
	public LibraryClass(Hooks base) {
		this.base = base;
		PageFactory.initElements(driver, this);
	}
	
	public By loader_icon = By.xpath("//div[@class='spinner-frame']//div[@class='lds-roller']");
	public By loader_wait = By.xpath("//div/p[text()='Loading offline facial data, please wait...']");
	
	public void loaderWaitUntilDisappear(WebDriver driver, int timeInSec) throws IOException, InterruptedException {
		login = new LoginPageEement(driver);
 		WebElement loader_DE = login.getLoaderInDE();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSec));
		Thread.sleep(2000);
		try {
		if (loader_DE.isDisplayed()) {
		wait.until(ExpectedConditions.invisibilityOf(loader_DE));
		System.out.println("Loader Disappeared");
		}}catch(Exception e) {
			System.out.println("Loader is not present");
		}
	}
	
	

	public void waitForClickableElement(WebDriver driver, WebElement element, int secondsToWait) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void waitForVisibilityOfElement(WebDriver driver, WebElement visbleElement, int secondsToWait) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
		wait.until(ExpectedConditions.visibilityOf(visbleElement));
	}


	public void scrollToView(By element) throws Exception {
		// Find element by link text and store in variable "Element"
		boolean stat = base.getDriver().findElements(element).size() > 0;
		if (stat == true) {
			WebElement Element = base.getDriver().findElement(element);
			JavascriptExecutor js = (JavascriptExecutor) base.getDriver();
			// This will scroll the page till the element is found
			js.executeScript("arguments[0].scrollIntoView(true);", Element);
			Thread.sleep(500);
		}
	}



	public void Clear(By Element) throws Exception {
		base.getDriver().findElement(Element).clear();
	}

	public void HorizontalscrollToView(WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) base.getDriver();
		js.executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth", element);

	}
	public void Clicks(By Element) throws Exception {
		base.getDriver().findElement(Element).click();
	
	}
	
	public void waitUntilLoaderDisappears() throws Exception {
		WebDriverWait wait = new WebDriverWait(base.getDriver(), Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader_wait));
		}

	public void waitUntilLoaderAppears() throws IOException {
		Wait<WebDriver> wait = new FluentWait<>(base.getDriver()).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(loader_icon));
	}
	
	public static void movetoelement(WebElement ref, WebDriver driver) {
		int i = 0;
		try {
			Actions acc = new Actions(driver);
			acc.moveToElement(ref).build().perform();
		} catch (MoveTargetOutOfBoundsException e) {
			if(i < 5) {
				movetoelement(ref, driver);
				i++;
			} else {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void implicitWait(int seconds) throws IOException {
		base.getDriver().manage().timeouts().implicitlyWait(seconds,TimeUnit.SECONDS);

	}
	public void waitTime(int seconds) {
		seconds = seconds * 1000;
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
