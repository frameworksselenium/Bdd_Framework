package com.open.hotel.pages;

import com.open.hotel.web.UiUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Login  extends UiUtils {

	WebDriver driver1 = null;
	String page = "Login";

	public Login(){
		this.driver1 = driver;

		PageFactory.initElements(driver1, this);
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
		driver1.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
		driver1.get(url);
	}

	public void login(String userName, String password) throws Exception {
		UserName.sendKeys(userName);
		Password.sendKeys( password);
		Login.click();
	}

	public void LogOut() throws Exception {
		LogOut.click();
	}

}