package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditCPAFirmAccess {

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement SettingsHover;

	@FindBy(xpath = "//a[contains(text(),'Edit CPA Firm Access')]")
	public WebElement EDITCPAFIRMLINK;

	@FindBy(xpath = "//input[@name='reportType' and @value='3']")
	public WebElement READWRITEACCESSREPORT;

	@FindBy(id = "agreedToSA")
	public WebElement SERVICEAGG;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement SAVEBTN;

	@FindBy(id = "password")
	public WebElement CPAFIRMACCESSPASSWORD;

	@FindBy(id = "authorize-ok")
	public WebElement AUTHORIZEBTN;

	@FindBy(xpath = "//strong[contains(text(),'Success')]")
	public WebElement PASSWORDUPDATED;

	@FindBy(xpath = "//input[@name='reportType' and @value='1']")
	public WebElement NOACCESSREPORT;

	@FindBy(xpath = "//input[@name='reportType' and @value='2']")
	public WebElement REPORTONLYACCESS;

	@FindBy(xpath = "//h1[contains(.,'Client Details & Account Access')]")
	public WebElement CLIENTDETAILSACCOUNTACCESS;

	@FindBy(xpath = "//h2[contains(.,'Client Order Forms')]")
	public WebElement CLIENTORDERFORMHEADING;
}
