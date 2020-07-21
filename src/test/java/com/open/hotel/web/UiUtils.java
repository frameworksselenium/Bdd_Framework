package com.open.hotel.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class UiUtils {

    public WebDriver driver;

    public UiUtils(){
        this.driver = null;
    }

    public WebDriver createNewDriver() {

        String browser = "CH";
        String driverPath = System.getProperty("user.dir");
        if (browser.toUpperCase().contains("CH")) {
            System.setProperty("webdriver.chrome.driver", driverPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        if (browser.toUpperCase().contains("FF")) {
            driver = new FirefoxDriver();
        }
        if (browser.toUpperCase().contains("IE")) {
            System.setProperty("webdriver.ie.driver", driverPath + "\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }

}