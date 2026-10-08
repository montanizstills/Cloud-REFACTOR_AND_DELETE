package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QBOManageForms {

	@FindBy(xpath = "//span[contains(.,'Manage Sales Forms')]")
	public WebElement QBOMANAGESALESFORMS;
	
	@FindBy(xpath = "//h1[contains(.,'Manage Sales Forms')]")
	public WebElement QBOMANAGESALESFORMPAGEHEADING;
	
	@FindBy(xpath = "//label[contains(.,'QuickBooks Online Company')]")
	public WebElement QBOONLINECOMPANY;
	
	@FindBy(xpath = "//select[@id='tax-payer']")
	public WebElement QBOOCOMPANYDROPDOWN;
	
	@FindBy(xpath = "//button[contains(.,'Sync Sales Forms')]")
	public WebElement QBOSYNCBUTTON;
	
	@FindBy(xpath = "//div/b[contains(.,'Last Data Sync:')]")
	public WebElement LASTSYNCTEXT;
	
	@FindBy(xpath = "//h2[contains(.,'SALES FORM STATUS')]")
	public WebElement SALESFORMWSTATUS;
	
	@FindBy(xpath = "//a[contains(.,'No Tax Estimated')]")
	public WebElement NOTAXESTIMATED;
	
	@FindBy(xpath = "//div[2]/div/div/div/a[contains(text(),'Tax Estimated')]")
	public WebElement TAXESTIMASTED;
	
	@FindBy(xpath = "//a[contains(text(),'Errors')]")
	public WebElement ERRORS;
	
	@FindBy(xpath = "//a[contains(text(),'Posted')]")
	public WebElement POSTED;
	
	@FindBy(xpath = "//a[contains(.,'No Tax Estimated')]/../div")
	public WebElement NOTAXESTIMATEDCOUNT;
	
	@FindBy(xpath = "//div[2]/div/div/div/a[contains(text(),'Tax Estimated')]/../div")
	public WebElement TAXESTIAMTEDCOUNT;
	
	@FindBy(xpath = "//div/a[contains(text(),'Errors')]/../div")
	public WebElement ERRORCOUNT;
	
	@FindBy(xpath = "//div/a[contains(text(),'Posted')]/../div")
	public WebElement POSTEDCOUNT;
	
	@FindBy(xpath = "//input[@id='tbQuickSearch']")
	public WebElement QUICKSEARCH;
	
	@FindBy(xpath = "//select[@id='transaction-type']")
	public WebElement SELECTFORMTYPE;
	
	@FindBy(xpath = "//select[@id='transaction-status']")
	public WebElement SELECTFORMSTATUS;
	
	@FindBy(xpath = "//button[contains(.,'SELECT ACTION')]")
	public WebElement SELECTACTIONBUTTON;
	
	@FindBy(xpath = "//table/thead/tr/th/input[@type='checkbox']")
	public WebElement CHECKBOX;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Form Date')]")
	public WebElement FORMDATE;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Customer Name')]")
	public WebElement CUSTOMERNAME;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Form Type')]")
	public WebElement FORMTYPE;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Form Number')]")
	public WebElement FORMNUMBER;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Form Total')]")
	public WebElement FORMTOTAL;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Total Tax Amount')]")
	public WebElement TOTALTAXAMOUNT;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Form Status')]")
	public WebElement FORMSTATUS;
	
	@FindBy(xpath = "//div/a[contains(text(),'Select Action')]")
	public WebElement SELECTACTIONAFTERCHECK;
	
	@FindBy(xpath = "//div/ul/li/a[contains(.,'ESTIMATE TAX')]")
	public WebElement ESTIMATETAX;
	
	@FindBy(xpath = "//div/ul/li/a[contains(.,'POST TO JOURNAL')]")
	public WebElement POSTTOJOURNAL;
	
	@FindBy(xpath = "//pagination-extension/span/select")
	public WebElement PAGINATION;
	
	@FindBy(xpath = "//h3[contains(text(),'SYNC IN PROGRESS')]")
	public WebElement SYNCHEADERTITLE;
	
	@FindBy(xpath = "//strong[contains(.,'A user-requested sync is in progress. Please wait while your sales forms are refreshed from your Quickbooks Online account. This may take several minutes")
	public WebElement SYNCBODYTEXT;
	
	@FindBy(xpath = "//p[contains(.,'No sales forms to display.')]")
	public WebElement NOSLAES;
	
	@FindBy(xpath = "//input[@id='ius-userid']")
	public WebElement QBOUserid;
	
	@FindBy(xpath = "//input[@id='ius-password']")
	public WebElement QBOPassword;
	
	@FindBy(xpath = "//button[@id='ius-sign-in-submit-btn']")
	public WebElement QBOSignIn;
	
	@FindBy(xpath = "//div[@class='app-logo']")
	public WebElement QBOLOGO;
	
	@FindBy(xpath = "//div/a[@class='customers']")
	public WebElement QBOCUATOMERS;
	
	@FindBy(xpath = "//div/span[contains(.,'Customers')]")
	public WebElement CUSTOMERSTITLE;
	
	@FindBy(xpath = "//button[contains(.,'New customer')]")
	public WebElement NEWCUSTOMER;
	
	@FindBy(xpath = "//div/button[contains(.,'New customer')]/../button[2]")
	public WebElement NEWCUSTOMERDROPDOWN;
	
	@FindBy(xpath = "//td[contains(.,'Import customers')]")
	public WebElement IMPORTCUSTOMER;
	
	@FindBy(xpath = "//div[contains(.,'Import Customers')]/../div[@class='title inlineBlock']")
	public WebElement IMPORTCUSTITLE;
	
	@FindBy(xpath = "//button[contains(.,'Browse')]")
	public WebElement BROWSE;
	
	@FindBy(xpath = "//img[@class='qblogo']")
	public WebElement QBOLOGO1;
	
	@FindBy(xpath = "//div[text()='Please select the company you want to open']")
	public WebElement QBOHOMEPAGE;
	
	@FindBy(xpath = "//div[contains(text(),'Please select the company you want to open')]")
	public WebElement MAPTEXT;
	
	@FindBy(xpath = "//span[@class='global-header-item settings']/button")
	public WebElement SETTINGS;

	@FindBy(xpath = "//li[@class='logout']/a")
	public WebElement QBOSIGNOUT;

	@FindBy(xpath = "//div/div/div/div[contains(.,'Please select the company you want to open')]")
	public WebElement PLEASESELECTCOMPANYTEXT;

	@FindBy(xpath = "//div/a/span[contains(.,'Apps')]")
	public WebElement APPS;

	@FindBy(xpath = "//div[@class='appcard-inner ']/div[contains(.,'Sales Tax Extension for Vertex SMB')]")
	public WebElement VERTEXCLOUDRETURNSCONNECTOR;

	@FindBy(xpath = "//li[@class='logout-new']/a")
	public WebElement QBOSLOGOUT;

	@FindBy(xpath = "//span[@class='global-header-item settings']/button/i")
	public WebElement QBOSSETTINGS;
	
	@FindBy(xpath="//div[@class='oneIntuitAccountHeaderItem']/div[contains(.,'Q')]")
	public WebElement QICON;
	
	@FindBy(xpath="//button/span/span[contains(.,'Sign out')]")
	public WebElement SIGNOUT;

}
