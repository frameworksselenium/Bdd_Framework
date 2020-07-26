package com.open.hotel.pages;

import com.open.hotel.security.Security;
import com.open.hotel.utils.UIUtils;
import com.open.hotel.utils.webDriverFactory.ManagerDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Login extends UIUtils {

	WebDriver driver = null;
	String page = "Login";

	public Login(){
		this.driver = ManagerDriver.getInstance().getWebDriver();
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(how =How.ID, using = "username")
	WebElement UserName;

	@FindBy(how =How.ID, using = "password")
	WebElement Password;

	@FindBy(how =How.ID, using = "login")
	WebElement Login;

	@FindBy(how =How.XPATH, using = "//*[contains(text(),'Search Hotel')]")
	WebElement SearchHotelText;

	@FindBy(how =How.XPATH, using = "//a[contains(text(),'Logout')]")
	WebElement LogOut;

	public void lauchApplication(String url)throws InterruptedException {
		//this.driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		this.driver.get(url);
	}

	public void login(String userName, String password) {
		type(UserName, userName, "UserName", this.page);
		Security security = new Security();
		String decryptPasswordValue = security.decryptPassword(password);
		type(Password, decryptPasswordValue, "Password", this.page);
		clickElement(Login, "Login", this.page);
	}

	public void LogOut() {
		clickElement(LogOut, "LogOut", this.page);
	}

}