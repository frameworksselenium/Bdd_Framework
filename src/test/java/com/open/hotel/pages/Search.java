package com.open.hotel.pages;

import com.open.hotel.web.UiUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

public class Search extends UiUtils {

	String page = "Search";
	public Search(){
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.NAME, using = "location")
	WebElement Location;
	@FindBy(how =How.NAME, using = "room_nos")
	WebElement NoOfRoom;
	@FindBy(how =How.NAME, using = "datepick_in")
	WebElement DatepickIn;
	@FindBy(how =How.NAME, using = "datepick_out")
	WebElement DatepickOut;
	@FindBy(how =How.NAME, using = "adult_room")
	WebElement AdultRoom;
	@FindBy(how =How.NAME, using = "Submit")
	WebElement Submit;
	@FindBy(how =How.NAME, using = "//*[contains(text(),'Select Hotel')]")
	WebElement SelectHotelText;


	public void clickOnSearch() throws Exception {
		Submit.click();
	}
	
	public void enterRoomSearchInfo(HashMap<String, String> values)throws Exception {
		Location.sendKeys( values.get("Location"));
		NoOfRoom.sendKeys( values.get("Number of Rooms"));
		DatepickIn.sendKeys(values.get("Check In Date"));
		DatepickOut.sendKeys(values.get("Check Out Date"));
		AdultRoom.sendKeys(values.get("Adults per Room"));
	}

}