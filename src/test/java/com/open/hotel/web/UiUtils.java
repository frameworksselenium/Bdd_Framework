package com.open.hotel.web;

import com.open.hotel.runner.TestNGRunner;
import com.open.hotel.web.webDriverFactory.LocalDriverFactory;
import com.open.hotel.web.webDriverFactory.ManagerDriver;
import com.open.hotel.web.webDriverFactory.RemoteDriverFactory;
import org.openqa.selenium.WebDriver;

public class UiUtils {

    public WebDriver driver;

    public UiUtils(){
        this.driver = null;
    }

    public void createNewDriver() {
        String ExecutionMode = "Local";
        WebDriver driver = null;
        if (ExecutionMode.contains("Local")) {
            driver = LocalDriverFactory.getInstance().createNewDriver();
        } else if (ExecutionMode.contains("Remote")) {
            driver = RemoteDriverFactory.getInstance().createNewDriver();
        }
        ManagerDriver.getInstance().setWebDriver(driver);
    }

}