package com.open.hotel.hooks;

import com.open.abddf.Logger.LoggerClass;
import com.open.abddf.html.HtmlLog;
import com.open.abddf.webDriverFactory.ManagerDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import com.open.abddf.threadLevelVariables.VariableManager;
import com.open.abddf.threadLevelVariables.Variables;

import java.text.ParseException;

public class Hooks{

	@Before()
	public void beforeScenario(Scenario scenario){
		String testCaseName = scenario.getName().split(":")[1];
		String testCaseID = scenario.getName().split(":")[0];

		Variables variables = new Variables();
		VariableManager.getInstance().setVariablesManager(variables);

		VariableManager.getInstance().getVariablesManager().setObject("testCaseID",testCaseID);
		VariableManager.getInstance().getVariablesManager().setObject("testCaseName",testCaseName);
		org.apache.log4j.Logger log = LoggerClass.getThreadLogger("Thread" + Thread.currentThread().getName(), VariableManager.getInstance().getVariablesManager().getObject("testCaseID"));
		log.info("Thread ID:'" + Thread.currentThread().getId() + "' 'PASS'");

		HtmlLog.initilization(testCaseName);
	}
			
	@After()
	public void afterScenario(Scenario scenario) throws ParseException {
		HtmlLog.summaryInsertTestCase();
		WebDriver driver = ManagerDriver.getInstance().getWebDriver();
		if(driver != null){
			driver.close();
			driver.quit();
		}
	}

}