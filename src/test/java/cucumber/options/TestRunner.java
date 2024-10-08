package cucumber.options;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

import com.event.base.ReportUtils;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
		features = { "src/test/resources/SeleniumFeatures" },
		glue = {"com.StepDefination" },
		tags = "@TC-1",
		dryRun = false,
		monochrome = true,
		plugin = {"pretty","html:target/cucumber-report/html-report.html", "json:target/cucumber-report/json-report.json",
				"junit:target/cucumber-report/junit-report.xml"}
		)

public class TestRunner extends AbstractTestNGCucumberTests{

	@AfterSuite
	public void generateReport() throws Exception {

		ReportUtils reportUtility = new ReportUtils();
		reportUtility.generateJVMReport();
	}
}
