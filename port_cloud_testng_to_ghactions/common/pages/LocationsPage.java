/*###################################################################################################
Name: LocationsPage.java 
Description: Professional User - Configure Location Page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/20/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LocationsPage {

	@FindBy(xpath = "//a[@title='Locations']")
	public WebElement LOCATIONLINK;

	@FindBy(xpath = "//button[@class='close']")
	public WebElement LOCATIONCLOSEICON;

	@FindBy(xpath = "//a[@title='Add New Location']")
	public WebElement ADDNEWLOCATION;

	@FindBy(xpath = "//input[@type='checkbox']")
	public WebElement NOLOCATIONCHECKBOX;

	@FindBy(xpath = "//a[@title='New Location']")
	public WebElement NEWLOCATION;

	@FindBy(xpath = "//input[@data-bind='enable: !id, value: locationCode']")
	public WebElement NEWLOCATIONCODE;

	@FindBy(xpath = "//input[@data-bind='value: name']")
	public WebElement NEWLOCATIONNAME;

	@FindBy(xpath = "//input[@id='location-start-date']")
	public WebElement LOCATIONSTARTDATE;

	@FindBy(xpath = "//input[@id='location-end-date']")
	public WebElement LOCATIONENDDATE;

	@FindBy(xpath = "//input[@data-bind='value: registrationCode']")
	public WebElement REGISTRATIONCODE;

	@FindBy(xpath = "//input[@data-bind='value: address']")
	public WebElement LOCATIONADDRESS;

	@FindBy(xpath = "//input[@data-bind='value: city']")
	public WebElement LOCATIONCITY;

	@FindBy(xpath = "//li[8]/input")
	public WebElement LOCATIONZIP;

	@FindBy(xpath = "//select[@id='location-state-select']")
	public WebElement LOCATIONSTATE;

	@FindBy(xpath = "//button[@data-bind='enable: !id, click: $root.config().selectedTaxpayer().removeLocation']")
	public WebElement REMOVEBUTTON;

	@FindBy(xpath = "//button[contains(text(),'Save Changes')]")
	public WebElement SAVEBUTTON;

	@FindBy(xpath = "//div/li/input")
	public WebElement AL_TRADENAME;

	@FindBy(xpath = "//div/li[2]/input")
	public WebElement AL_REGISTRATIONNUMBER;

	@FindBy(xpath = "//div/li[3]/input")
	public WebElement AL_POSTOFFICE;

	@FindBy(xpath = "//div/li/input")
	public WebElement IN_REGISTRATIONNUMBER;

	@FindBy(xpath = "//div/li[2]/input")
	public WebElement IN_THREEPOSITIONUMBER;

	@FindBy(xpath = "//div[@class='alert alert-error']/span[text()='Configuration save failed']")
	public WebElement ERRORTEXT;

}
