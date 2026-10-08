package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckDates {

	@FindBy(xpath = "//h3/span[@class='pull-right']/a[contains(text(),'Edit')]")
	public WebElement EDITCOMPANY;

	@FindBy(xpath = "//div[@class='registration-block has-registration ng-scope']/h3/span[@class='pull-right']/a[contains(text(),'Edit')]")
	public WebElement EDITREGISTRATION;

	@FindBy(xpath = "//a[contains(text(),'Add a New Location')]")
	public WebElement ADDNEWLOCATION;

	@FindBy(xpath = "//input[@id='new-location-start-date']")
	public WebElement LOCATIONSTARTDATE;

	@FindBy(xpath = "//input[@id='new-location-code']")
	public WebElement LOCATIONCODE;

}
