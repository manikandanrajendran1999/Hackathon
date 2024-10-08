package com.event.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

public class ReportUtils {
	
	public void generateJVMReport() {
	
	File reportOutputDirectory = new File("CCreports");
	List<String> jsonFiles = new ArrayList<>();
	jsonFiles.add("target/cucumber-report/json-report.json");

	String buildNumber = "1";
	String projectName = "cucumberProject";

	Configuration c = new Configuration(reportOutputDirectory, projectName);
	// optional configuration - check javadoc for details
//	configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
	// do not make scenario failed when step has status SKIPPED
	c.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
	c.setBuildNumber(buildNumber);
	c.addClassifications("Platform", "mac");
	c.addClassifications("Browser", "Chrome");
	c.addClassifications("Branch", "release/1.0");
	c.addClassifications("Tool", "Selenium");

	ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, c);
	@SuppressWarnings("unused")
	Reportable result = reportBuilder.generateReports();

	}
	
}
