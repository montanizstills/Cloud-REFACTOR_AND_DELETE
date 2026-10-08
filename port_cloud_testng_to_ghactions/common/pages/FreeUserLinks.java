package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FreeUserLinks {

	@FindBy(xpath = "//i[@class='icon-info icon-small section-heading']")
	public WebElement ICONDASHBOARD;
	
	@FindBy(xpath = "//i[@class='icon-info icon-small']")
	public WebElement ICON;
	
	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	public WebElement DASHBOARDLINK;
	
	@FindBy(xpath = "//span[contains(text(),'Calculate Tax')]")
	public WebElement CALTAX;
	
	@FindBy(xpath = "//a[@title='Calculate Taxes']")
	public WebElement CALCULATETAXES;
	
	@FindBy(xpath = "//span[contains(text(),'Rates & Jurisdictions')]")
	public WebElement LOOKUPTAXRATESLINK;
	
	@FindBy(xpath = "//li[4]/a/span[contains(text(),'Reports')]")
	public WebElement REPORTSLINK;
	
	@FindBy(xpath = "//a[@title='Taxability Reports']")
	public WebElement TAXABILITYREPORTSLINK;
	
	@FindBy(xpath = "//a/span[contains(text(),'Billing Reports')]")
	public WebElement MAIN_BILLINGREPORTLINK;
	
	@FindBy(xpath = "//a[@title='Receipts and Quotes']")
	public WebElement RECEIPTSTLINK;
	
	@FindBy(xpath = "//a[contains(text(),'Edit My Company')]")
	public WebElement EDITCOMPANYLINK;
	
	@FindBy(xpath = "//a[contains(text(),'View All Users')]")
	public WebElement VIEWALLUSERSLINK;
	
	@FindBy(xpath = "//a[contains(text(),'Update My Billing')]")
	public WebElement UPDATEBILLINGLINK;
	
	@FindBy(xpath = "//a[contains(text(),'Billing Reports')]")
	public WebElement BILLINGREPORTSLINK;
	
	// @FindBy(xpath="//a[@title='My Users']")
	//
	public WebElement MYUSERSLINK;
	
	@FindBy(xpath = "//a[@title='My Company']")
	public WebElement MYCOMPANYLINK;
	
	@FindBy(xpath = "//a[@title='My Subscriptions']")
	public WebElement MYSUBSCRIPTIONSLINK;
	
	@FindBy(xpath = "//a/span[contains(text(),'Visit Help Center')]")
	public WebElement GETHELPLINK;
	
	@FindBy(xpath = "//div[@id='docExtraTabs']/ul/li/a[@id='Informationlink']")
	public WebElement GETHELPINFORMATIONLINK;
	
	@FindBy(xpath = "//a/span[contains(text(),'Contact Support')]")
	public WebElement CONTACTSUPPORTLINK;
	
	@FindBy(xpath = "//a/span[contains(text(),'Document Center')]")
	public WebElement DOCUMENTCENTRE;
	
	@FindBy(xpath = "//span[contains(.,'Rate File')]")
	public WebElement RATEFILE;

	// 5.0 Changes

	@FindBy(xpath = "//div/h1[contains(.,'Billing Report')]/../../div[contains(.,'Billing reports are displayed after billing occurs on the second business day of each month when you are in ‘Live’ mode.')]")
	public WebElement BILLINGREPORTTEXT;
	
	@FindBy(xpath = "//span[contains(text(),'Calculate Taxes')]")
	public WebElement taxesHover;
	

	@FindBy(xpath = "//span[contains(text(),'Manage Tax Returns')]")
	public WebElement ReturnsHover;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement SettingsHover;

	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	public WebElement ReportsHover;
	
	@FindBy(xpath = "//a[contains(text(),'View All Credentials')]")
	public WebElement viewallcredentialslink;
	
	@FindBy(xpath = "//div/a[contains(text(),'View All Credentials')]")
	public WebElement settingsviewallcredentialslink;
	
	@FindBy(xpath = "//h2[contains(text(),'My OAuth Credentials')]")
	public WebElement MyOAuthCredentials;
	

	
}
