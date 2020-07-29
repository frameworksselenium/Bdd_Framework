package com.open.hotel.runner;

import com.open.abddf.html.HtmlLog;
import com.open.abddf.loadConfig.Config;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;

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

	//public static Properties properties = null;
	@Override
	@DataProvider (parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@BeforeSuite()
	public void setup(){
		//properties = Config.init();
		Config.createFolder(Config.properties.getProperty("resultFolder"));
		Config.createFolder(Config.properties.getProperty("resultFolderName"));
		HtmlLog.summaryInitialization("ExecutionSummaryReport");
	}

	@AfterSuite()
	public void tearDown(){
		HtmlLog.summaryCloseHtml(Config.properties.getProperty("Release"));
	}

}
