package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditUserSettings {

	@FindBy(xpath = "//li/a/i[contains(text(),'account')]")
	public WebElement ACCOUNT;

	@FindBy(xpath = "//a[contains(text(),'User Settings')]")
	public WebElement USERSETTINGS;

	@FindBy(id = "firstName")
	public WebElement FIRSTNAME;

	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement LASTNAME;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement SUBMIT;

	@FindBy(xpath = "//p[contains(.,'Your Account has been updated.')]")
	public WebElement SUCCESS;

	@FindBy(xpath = "//input[@id='email']")
	public WebElement EMAILADDRESS;

	@FindBy(xpath = "//input[@id='emailAddress']")
	public WebElement USERNAME;

	@FindBy(xpath = "//input[@id='phoneNumber']")
	public WebElement PHONENUMBER;

	@FindBy(xpath = "//input[@id='SupportRequest_SalesforceSupportDetails_CompanyName']")
	public WebElement BPOClient;

	@FindBy(xpath = "//input[@id='firstName']")
	public WebElement BPOFIRSTNAME;

	@FindBy(xpath = "//div/input[@id='phone']/../div/span[contains(.,'This field is required.')]")
	public WebElement ErrorPhone;

	@FindBy(xpath = "//div/a[contains(.,'Go to Dashboard')]")
	public WebElement GOTODASHBOARD;

}