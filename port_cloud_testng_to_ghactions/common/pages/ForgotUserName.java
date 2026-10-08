package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotUserName {

	@FindBy(xpath = "//a[contains(.,'Username')]")
	public WebElement USERNAMELINK;

	@FindBy(xpath = "//input[@id='EmailAddress']")
	public WebElement EMAILADDRESS;

	@FindBy(xpath = "//button[contains(.,'Email Username')]")
	public WebElement EMAILUSERNAMEBTN;

	@FindBy(xpath = "//h1[contains(.,'Success!')]")
	public WebElement SUCCESS;

	@FindBy(xpath = "//p[contains(.,'Your Username has been emailed to the supplied email address.')]")
	public WebElement USERNAMEEMAILEDTOTHESUPPLIEDEMAIL;

	@FindBy(xpath = "//a[contains(.,'Go to Dashboard')]")
	public WebElement GOTODASHBOARDLINK;

}
