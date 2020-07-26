package com.open.hotel.runner;

import com.open.hotel.utils.html.HtmlLog;
import com.open.hotel.utils.html.HtmlManager;
import com.open.hotel.utils.loadConfig.Config;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
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
	public static HtmlLog htmLog = null;
	@Override
	@DataProvider (parallel = true)
	public Object[][] scenarios() {

		return super.scenarios();
	}

	@BeforeSuite()
	public void setup(){
		properties = Config.init();
		htmLog = HtmlManager.init();
		Config.createFolder(properties.getProperty("resultFolder"));
		Config.createFolder(properties.getProperty("resultFolderName"));
		htmLog.summaryInitialization("ExecutionSummaryReport");

	}

	@AfterSuite()
	public void tearDown(){
		htmLog.summaryCloseHtml(properties.getProperty("Release"));
	}

}
