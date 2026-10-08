package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UnpublishedConfigurationChanges {

	@FindBy(xpath = "//span[contains(text(),'Calculate Tax')]")

	public WebElement CALTAX;
	@FindBy(xpath = "//a[@title='Manage Adjustment File']")

	public WebElement MANAGEADJUSTMENTFILELINK;
	@FindBy(xpath = "//div[contains(text(),'You have unpublished configuration changes.')]")

	public WebElement UNPLUBISHEDCHANGES;
	@FindBy(xpath = "//a[contains(text(),'Upload New File')]")

	public WebElement UPLOADLINK;
	@FindBy(xpath = "//a[@title='Calculate Taxes']")

	public WebElement CALTAXES;
	@FindBy(xpath = "//a[@title='Distribute Tax']")

	public WebElement DISTRIBUTETAX;
	@FindBy(xpath = "//div[contains(text(),'You have unpublished configuration changes. A Distribute Tax Transaction can not be submitted. Please try again later.')]")

	public WebElement DISTRIBUTEDUNPLUNISHEDCHANGES;
	@FindBy(xpath = "//span[contains(text(),'Configure')]")

	public WebElement CONFIGURELINK;
	@FindBy(xpath = "//a[contains(text(),'Save Configuration')]")

	public WebElement SAVECONFIGURATION;
	@FindBy(xpath = "//button[contains(text(),'Yes to complete configuration')]")

	public WebElement COMPLETECONFIGURATION;
	@FindBy(xpath = "//div[@class='ng-hide'][2]")

	public WebElement INPROCESS;
	@FindBy(xpath = "//a[contains(text(),'Quick Reference Guide')]")

	public WebElement DOWNLOADEXTENDHELP;
	@FindBy(xpath = "//button[contains(.,'Download File Format')]")

	public WebElement DOWNLOADFILEFORMAT;
	@FindBy(xpath = "//div[3]/input")

	public WebElement PRODUCTPRICE;
	@FindBy(xpath = "//a[text()='Get Jurisdictions']")

	public WebElement GETJURISDICTIONS;

}
