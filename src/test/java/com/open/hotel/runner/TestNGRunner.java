package com.open.hotel.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;
import java.util.Properties;

@CucumberOptions(
		plugin={
				"pretty",
				"html:target/cucumberReport",
				"json:target/cucumberReport/cucumber.json",
		},
		tags={"@SmokeTest"},
		features = {"src/test/java/com/open/hotel/features"},
		glue={"com.open.hotel.hooks", "com.open.hotel.stepdefinitions"},
		strict = true,
		dryRun = false
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

	public static Properties properties = null;

	@Override
	@DataProvider (parallel = true)
	public Object[][] scenarios() {

		return super.scenarios();
	}

}
