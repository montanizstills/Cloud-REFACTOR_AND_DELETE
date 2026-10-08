package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecoverUnknownUser {

	@FindBy(xpath = "//a[contains(text(),'Username')]")

	public WebElement FORGOTUSERNAME;
	@FindBy(xpath = "//a[contains(text(),'Password')]")

	public WebElement FORGOTPASSWORD;
	@FindBy(xpath = "//input[@id='EmailAddress']")

	public WebElement EMAILADDRESS;
	@FindBy(xpath = "//button[contains(text(),'Email Username')]")

	public WebElement EMAILUSERNAME;
	@FindBy(xpath = "//a[contains(text(),'Back to Login ')]")

	public WebElement BACKTOLOGIN;
	@FindBy(xpath = "//input[@id='UserName']")

	public WebElement USERNAME;
	@FindBy(xpath = "//button[contains(text(),'Email Password Reset Link')]")

	public WebElement PASSWORDRESETLINK;
	@FindBy(xpath = "//input[@id='Email']")

	public WebElement EMAILFORGOTPWD;
	@FindBy(xpath = "//form/fieldset/div[1]/ul/li[contains(.,'Invalid Email Address')]")

	public WebElement INVALIDEMAILUSER;

}
