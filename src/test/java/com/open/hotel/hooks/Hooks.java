package com.open.hotel.hooks;

import com.open.hotel.web.UiUtils;
import com.open.hotel.web.webDriverFactory.ManagerDriver;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks{

	@Before()
	public void beforeScenario(Scenario scenario){
		UiUtils uIUtil= new UiUtils();
		uIUtil.createNewDriver();
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