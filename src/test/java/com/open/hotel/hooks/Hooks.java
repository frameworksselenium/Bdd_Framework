package com.open.hotel.hooks;

import com.open.hotel.utils.LoggerClass;
import com.open.hotel.utils.html.HtmlLog;
import com.open.hotel.utils.webDriverFactory.LocalDriverFactory;
import com.open.hotel.utils.webDriverFactory.ManagerDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import com.open.hotel.utils.webDriverFactory.RemoteDriverFactory;
import com.open.hotel.utils.threadLevelVariables.VariableManager;
import com.open.hotel.utils.threadLevelVariables.Variables;
import com.open.hotel.runner.TestNGRunner;

import java.text.ParseException;

public class Hooks{

	@Before()
	public void beforeScenario(Scenario scenario){

	/*	String ExecutionMode = TestNGRunner.properties.getProperty("ExecutionMode");
		WebDriver driver = null;
		if (ExecutionMode.contains("Local")) {
			driver = LocalDriverFactory.getInstance().createNewDriver();
		} else if (ExecutionMode.contains("Remote")) {
			driver = RemoteDriverFactory.getInstance().createNewDriver();
		}
		ManagerDriver.getInstance().setWebDriver(driver);
*/
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