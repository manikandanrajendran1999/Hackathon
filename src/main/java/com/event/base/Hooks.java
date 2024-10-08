package com.event.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseUtils{
	
	private static boolean shouldSkip = false;


	public WebDriver getDriver() throws IOException {
		if (getConfigData("browser").equals("chrome")) {
			return driver;
		} else if (getConfigData("browser").equals("edge")) {
			return edgeDriver;
		} else if (getConfigData("browser").equals("ie")) {
			return ieDriver;
		} else {
			return driver2;
		}
	}

	public void launchBrowser() throws IOException {
		String os = System.getProperty("os") != null ? System.getProperty("os") : getConfigData("os");
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: getConfigData("browser");

		// Check if the os is mac or win
		if (os.toLowerCase().contains("mac")) {
			if (browserName.toLowerCase().contains("chrome") && getDriver() == null) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors", "start-maximized",
						"use-fake-ui-for-media-stream"));
				driver = new ChromeDriver(options);
				HashMap<String, Object> chromePrefs = new HashMap<>();
				options.setPageLoadStrategy(PageLoadStrategy.EAGER);
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--test-type");
				options.addArguments("--disable-extensions"); // to disable browser extension popup
				options.addArguments("--disable-notifications");
				options.addArguments("use-fake-ui-for-media-stream");
				options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				driver.get(getConfigData("stagingURL"));
				// Use Actions to simulate Control + Command + F
		        Actions actions = new Actions(driver);
		        actions.keyDown(Keys.CONTROL).keyDown(Keys.COMMAND).sendKeys("f").keyUp(Keys.COMMAND).keyUp(Keys.CONTROL).perform();
			} else if (browserName.toLowerCase().contains("edge") && getDriver() == null) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors", "start-maximized",
						"use-fake-ui-for-media-stream"));
				System.out.println("Launched");
				edgeDriver = new EdgeDriver(options);
				edgeDriver.get(getConfigData("stagingURL"));
			} else if (browserName.toLowerCase().contains("ie") && getDriver() == null) {
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.ignoreZoomSettings();
				System.out.println("Launched");
				ieDriver = new InternetExplorerDriver(options);
				ieDriver.get(getConfigData("stagingURL"));
			}
		} else if (os.toLowerCase().contains("win")) {
			if (browserName.toLowerCase().contains("chrome") && getDriver() == null) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors", "start-maximized",
						"use-fake-ui-for-media-stream"));
				System.out.println("Launched");
				driver = new ChromeDriver(options);
				driver.get(getConfigData("stagingURL"));
			} else if (browserName.toLowerCase().contains("edge") && getDriver() == null) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors", "start-maximized",
						"use-fake-ui-for-media-stream"));
				System.out.println("Launched");
				edgeDriver = new EdgeDriver(options);
				edgeDriver.get(getConfigData("stagingURL"));
			} else if (browserName.toLowerCase().contains("ie") && getDriver() == null) {
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.ignoreZoomSettings();
				System.out.println("Launched");
				ieDriver = new InternetExplorerDriver(options);
				ieDriver.get(getConfigData("stagingURL"));
			}
		}
	}

	// This is for read the config data
	public String getConfigData(String propname) throws IOException {
		String propValue = "";
		Properties p = new Properties();
		File f = new File(System.getProperty("user.dir") + "/config.properties");
		FileInputStream fis = new FileInputStream(f);
		p.load(fis);
		propValue = p.getProperty(propname);
		return propValue;
		
	}
	
	

//    @Before
//    public void beforeScenario(Scenario scenario) {
//        if (shouldSkip) {
//            scenario.log("Skipping scenario due to a previous failure.");
//            throw new RuntimeException("Skipping scenario due to a previous failure.");
//        }
//    }
//
//    @After
//    public void afterScenario(Scenario scenario) {
//        if (scenario.isFailed()) {
//            shouldSkip = true; // Set flag to skip future scenarios
//        }
//    }



}
