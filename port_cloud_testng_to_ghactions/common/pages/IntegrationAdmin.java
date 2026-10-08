package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IntegrationAdmin {

	@FindBy(xpath = "//a[@title='Dashboard']")
	public WebElement Dashboard;

	@FindBy(xpath = "//a[@title='OSP Logs Search']")
	public WebElement Osplogssearch;

	@FindBy(xpath = "//a[@title='Tax Link Logs']")
	public WebElement Taxlinklogs;

	@FindBy(xpath = "//a[@title='Manage Customers']")
	public WebElement ManageCustomers;

	@FindBy(xpath = "//a/span[contains(.,'Premium Customers')]")
	public WebElement Premiumcustomers;

	@FindBy(xpath = "//a[@title='Premium Customers']")
	public WebElement Premiumcustmentlink;

	@FindBy(xpath = "//a[@title='Manage O-Series Config']")
	public WebElement manageoseries;

	@FindBy(xpath = "//a[@title='System Configuration']")
	public WebElement systemconfiguration;

	@FindBy(xpath = "//a[@title='Visit Help Center']")
	public WebElement visithelpcenter;

	@FindBy(xpath = "//a[@href='/Admin/VisitHelpCenter/WhatsNew']")
	public WebElement whatsnew;

	@FindBy(xpath = "//small[contains(.,'2018 Vertex Global Tax Solutions')]")
	public WebElement footertext;

	@FindBy(xpath = "//a/span[contains(.,'Collapse')]")
	public WebElement collapse;

	@FindBy(xpath = "//h1[contains(.,'Manage Cloud Customer')]")
	public WebElement Managecustomerhead;
	// h1[contains(.,'Manage Cloud Customer')]
}
