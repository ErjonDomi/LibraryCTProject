package com.LibraryCT.step_definitions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				"json:target/cucumber.json",
				"rerun:target/rerun.txt",
				"html:target/cucumber-report.html"
				}, 
		monochrome = true,
		glue = { "com/LibraryCT/step_definitions" },
		features = { "src/test/resources/features" }
)

public class ParallelRun extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}