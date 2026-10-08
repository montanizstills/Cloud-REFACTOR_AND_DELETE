package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPassword {

	@FindBy(xpath = "//a[contains(.,'Password')]")
	public WebElement PASSWORDLINK;

	@FindBy(xpath = "//input[@id='Username']")
	public WebElement USERNAME;

	@FindBy(xpath = "//input[@id='EmailAddress']")
	public WebElement EMAILADDRESS;

	@FindBy(xpath = "//button[contains(.,'Email Password Reset Link')]")
	public WebElement EMAILPASSWORDRESETBTN;

	@FindBy(xpath = "//h1[contains(.,'Success!')]")
	public WebElement SUCCESS;

	@FindBy(xpath = "//p[contains(text(),'We're testing! So here's a direct link to reset yo')]")
	public WebElement PASSWORDEMAILEDTOTHESUPPLIEDEMAIL;

	@FindBy(xpath = "//a[contains(.,'Go to Dashboard')]")
	public WebElement GOTODASHBOARDLINK;
	
	@FindBy(xpath = "//a[contains(text(), 'https://devauth.vertexsmb.com')]")
	public WebElement RESETPASSWORDLINK;
	
	@FindBy(xpath = "//input[@id='NewPassword']")
	public WebElement NEWPASSWORD;
	
	@FindBy(xpath = "//input[@id='ConfirmNewPassword']")
	public WebElement CONFIRMNEWPASSWORD;

	@FindBy(xpath = "//button[text()='Reset Password']")
	public WebElement RESETPASSWORDBUTTON;
	
	@FindBy(xpath = "//a[text()='Login']")
	public WebElement LOGINBUTTON;

}
