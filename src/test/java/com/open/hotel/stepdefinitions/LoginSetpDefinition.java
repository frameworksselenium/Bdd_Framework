package com.open.hotel.stepdefinitions;

import com.open.hotel.pages.Login;
import com.open.hotel.runner.TestNGRunner;
import com.open.hotel.utils.loadConfig.Config;
import com.open.hotel.utils.webDriverFactory.LocalDriverFactory;
import com.open.hotel.utils.webDriverFactory.ManagerDriver;
import com.open.hotel.utils.webDriverFactory.RemoteDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class LoginSetpDefinition {
	
	public Login login = null;//new Login();

	@Given("Open Browser")
	public void OpenBrowser() {
		String ExecutionMode = Config.properties.getProperty("ExecutionMode");
		WebDriver driver = null;
		if (ExecutionMode.contains("Local")) {
			driver = LocalDriverFactory.getInstance().createNewDriver();
		} else if (ExecutionMode.contains("Remote")) {
			driver = RemoteDriverFactory.getInstance().createNewDriver();
		}
		ManagerDriver.getInstance().setWebDriver(driver);
		login = new Login();
	}

	@Given("User is able Launch the hotel application using {string}")
	public void user_is_able_Launch_the_hotel_application_using(String arg1) throws InterruptedException {
		login.lauchApplication(arg1);
	}

	@When("User enters the {string} and {string} and click on login button")
	public void user_enters_the_and(String arg1, String arg2) throws Exception {
		login.login(arg1, arg2);
	}

	@Then("LogOut application")
	public void logout_application() throws Exception {
		login.LogOut();
	}

}