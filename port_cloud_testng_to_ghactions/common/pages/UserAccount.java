package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserAccount {

	@FindBy(xpath = "//a[contains(@href, '#account')]")

	public WebElement ACCOUNTOPEN;
	@FindBy(xpath = "//a[contains(text(),'User Settings')]")

	public WebElement USERSETTINGSLINK;
	@FindBy(xpath = "//input[@id='firstName']")

	public WebElement FIRSTNAME;
	@FindBy(xpath = "//input[@id='lastName']")

	public WebElement LASTNAME;
	@FindBy(xpath = "//input[@id='phoneNumber']")

	public WebElement PHONE;
	@FindBy(xpath = "//button[contains(text(),'Save')]")

	public WebElement SAVE;
	@FindBy(xpath = "//input[@id='emailAddress']")

	public WebElement EMAIL;
	@FindBy(xpath = "//input[@id='password']")

	public WebElement PASSWORD;
	@FindBy(xpath = "//button[contains(.,'Save Changes')]")

	public WebElement SAVECHANGES;
	@FindBy(xpath = "//input[@id='password']")

	public WebElement ConfirmPwdSave;

	@FindBy(xpath = "//button[contains(.,'Save Changes')]")

	public WebElement SaveChanges;

	@FindBy(xpath = "//h1[contains(.,'Success!')]")

	public WebElement Success;

	@FindBy(xpath = "//p[contains(.,'Your Account has been updated.')]")

	public WebElement Accountupdated;

	@FindBy(xpath = "//a[contains(.,'Go to Dashboard')]")

	public WebElement GotoDashboard;

}
