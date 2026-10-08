package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePassword {

	@FindBy(xpath = "//input[@id='username']")
	public WebElement USERNAME;

	@FindBy(xpath = "//input[@id='Password']")
	public WebElement PASSWORD;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement LOGIN;

	@FindBy(xpath = "//a[contains(@href, '#account')]")
	public WebElement ACCOUNTOPEN;

	@FindBy(xpath = "//a[contains(text(),'Change Password')]")
	public WebElement CHANGEPASSWORDLINK;

	@FindBy(xpath = "//input[@id='currentPassword']")
	public WebElement CURRENTPASSWORD;

	@FindBy(xpath = "//input[@id='newPassword']")
	public WebElement NEWPASSWORD;

	@FindBy(xpath = "//div[contains(.,'Your password must be 8-128 characters in length and must contain at least one uppercase and lowercase letter, number, and special character (!@#$%^&*<>?).')]")
	public WebElement PWDSTRENGTH;

	@FindBy(xpath = "//input[@id='confirmNewPassword']")
	public WebElement CONFIRMPASSWORD;

	@FindBy(xpath = "//button[contains(text(),'Change Password')]")
	public WebElement CHANGEPASSWORDBUTTON;

	@FindBy(xpath = "//h1")
	public WebElement DASHBOARDLABEL;

	@FindBy(xpath = "//p[contains(.,'Your Password has been changed.')]")
	public WebElement SUCCESMESSAGE;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	public WebElement SUCCESSMESSAGE;

	@FindBy(xpath = "//div/input[@id='new-password']/../div[contains(.,'Your password must be 8-40 characters in length and must contain at least one uppercase letter, number, and special character (!@#$%^&*<>?).')]")
	public WebElement CPA_PWDSTRENGTH;

	@FindBy(xpath = "//input[@id='current-password']")
	public WebElement CPA_CURRENTPASSWORD;

	@FindBy(xpath = "//input[@id='new-password']")
	public WebElement CPA_NEWPASSWORD;

	@FindBy(xpath = "//input[@id='confirm-password']")
	public WebElement CPA_CONFIRMPASSWORD;

	@FindBy(xpath = "//a[text()='Cancel']")
	public WebElement CPA_CANCEL;

	@FindBy(xpath = "//h1[text()='Dashboard']")
	public WebElement CPA_DASHBOARD;

	@FindBy(xpath = "//span[text()='The confirm password should be the same as the password.']")
	public WebElement CPA_CONFIRMPSWDERR;

	@FindBy(xpath = "//a[contains(text(),'User Settings')]")
	public WebElement CPA_USERSETTINGS;

	@FindBy(xpath = "//input[@id='first-name']")
	public WebElement CPA_FIRSTNAME;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement CPA_SAVE;

	@FindBy(xpath = "//li[contains(.,'Invalid password.')]")
	public WebElement ERRORMESSAGEPWD;

	@FindBy(xpath = "//div[@class='alert alert-danger ng-binding']/strong")
	public WebElement ACCOUNTLOCKED;

	@FindBy(id = "username")
	public WebElement VTMSUSERNAME;

	@FindBy(id = "password")
	public WebElement VTMSPASSWORD;

	@FindBy(xpath = "//a[@id='login-page-link']")
	public WebElement CONTINUETOLOGIN;

	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	public WebElement DASHBOARDLINK;
}
