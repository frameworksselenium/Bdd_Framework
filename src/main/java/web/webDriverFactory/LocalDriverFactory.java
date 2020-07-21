package web.webDriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LocalDriverFactory {

	private static LocalDriverFactory instance = new LocalDriverFactory();

	public static LocalDriverFactory getInstance() {
		return instance;
	}

	public WebDriver createNewDriver() {
		WebDriver driver = null;
		String browser = "CH";
			String driverPath = System.getProperty("user.dir");
			if (browser.toUpperCase().contains("CH")) {
				System.setProperty("webdriver.chrome.driver", driverPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				options.setExperimentalOption("useAutomationExtension", false);
				options.addArguments("no-sandbox");
				options.addArguments("start-maximized");
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				driver = new ChromeDriver(options);
			}
		return driver;
	}
}