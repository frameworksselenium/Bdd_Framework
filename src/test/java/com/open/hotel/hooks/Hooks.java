package com.open.hotel.hooks;

import web.webDriverFactory.LocalDriverFactory;
import web.webDriverFactory.ManagerDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import web.webDriverFactory.RemoteDriverFactory;

public class Hooks{

	@Before()
	public void beforeScenario(Scenario scenario){
		String ExecutionMode = "Local";
		WebDriver driver = null;
		if (ExecutionMode.contains("Local")) {
			driver = LocalDriverFactory.getInstance().createNewDriver();
		} else if (ExecutionMode.contains("Remote")) {
			driver = RemoteDriverFactory.getInstance().createNewDriver();
		}
		ManagerDriver.getInstance().setWebDriver(driver);
	}
			
	@After()
	public void afterScenario(Scenario scenario) {
		WebDriver driver = ManagerDriver.getInstance().getWebDriver();
		if(driver != null){
			driver.close();
			driver.quit();
		}
	}

}