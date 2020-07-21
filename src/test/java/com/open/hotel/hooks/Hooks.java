package com.open.hotel.hooks;

import com.open.hotel.web.UiUtils;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks{

	UiUtils uIUtil = new UiUtils();

	@Before(order = 1)
	public void beforeScenario(Scenario scenario){
		uIUtil.createNewDriver();
	}
			
	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		WebDriver driver = uIUtil.driver;
		if(driver != null){
			driver.close();
			driver.quit();
		}
	}

}