package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class QBOFirstTimeMap {

	@FindBy(xpath = "//h2[contains(text(),'QuickBooks Online Integration')]")

	public WebElement QuickBookOnline;

	@FindBy(xpath = "//p[contains(text(),'The Vertex Cloud integration to QuickBooks Online provides Web-based access for self-service setup and configuration for sales tax calculations.Just click the Launch Site button below to continue.')]")

	public WebElement QuickBookOnlineSection;

	@FindBy(id = "launchSite")
	
	public WebElement LaunchSite;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")

	public WebElement SettingsHover;

	@FindBy(xpath = "//div/a[contains(text(),'View All Connectors')]")

	public WebElement ViewAllConnectors;

	@FindBy(xpath = "//div/label[contains(.,'API Key')]/../../div/input")	

	public WebElement ApiKey;

	@FindBy(xpath = "//div/label[contains(.,'API Password')]/../../div/input")

	public WebElement ApiPassword;
	
	@FindBy(xpath = "//input[@id='KeySecret_Secret']")

	public WebElement ApiPassword1;

	@FindBy(xpath = "//button[contains(text(),'SAVE')]")

	public WebElement Continue;
	
	@FindBy(xpath = "//button[contains(text(),'CONTINUE')]")

	public WebElement Continue1;

	@FindBy(xpath = "//div[1]/img[@alt='Tax Central by Vertex SMB']")

	public WebElement VertexLogo;

	@FindBy(xpath = "//a/img[@alt='Tax Central by Vertex SMB']")

	public WebElement VertexLogolefttop;

	@FindBy(xpath = "//h1[contains(text(),'AUTHORIZE QUICKBOOKS ONLINE INTEGRATION')]")

	public WebElement QuickbookOnlineIntlabel;

	@FindBy(xpath = "//img[@alt='QuicksBooks Online']")

	public WebElement QuickBooksOnlineLogo;

	@FindBy(xpath = "//div[4]/img")

	public WebElement ConnectQuickBooks;

	@FindBy(xpath = "//input[@id='ius-userid']")
	public WebElement QBOUserid;

	@FindBy(xpath = "//input[@id='ius-password']")
	public WebElement QBOPassword;

	@FindBy(xpath = "//button[@id='ius-sign-in-submit-btn']")
	public WebElement QBOSignIn;

	@FindBy(id = "authorizeBtn")

	public WebElement authorizeBtn;

	@FindBy(xpath = "//button[contains(.,'Connect')]")

	public WebElement CONNECTAUTHORIZE;

	@FindBy(name = "TCName")

	public WebElement taxCentralName;

	@FindBy(xpath = "//button[contains(text(),'SUBMIT')]")

	public WebElement submit;

	@FindBy(xpath = "//a[contains(text(),'Add Company Mapping')]")

	public WebElement addCompanyMap;

	@FindBy(xpath = "//span[contains(text(),'Manage Sales Forms')]")

	public WebElement manageSalesforms;

	@FindBy(xpath = "//span[contains(text(),'Address Validation')]")
	
	public WebElement addressValidation;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	
	public WebElement settings;

	@FindBy(xpath = "//span[contains(text(),'Visit Help Center')]")

	public WebElement helpCenter;

	@FindBy(xpath = "//span[contains(text(),'Contact Support')]")

	public WebElement contactSupport;

	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")

	public WebElement dashBoard;

	@FindBys({@FindBy(xpath = "//select[@id='tax-payer']/optgroup"), })
	
	public List<WebElement> ConfiguredCompanies;

	@FindBy(id = "ius-mfa-confirm-code")

	public WebElement confirmationCode;

	@FindBy(xpath = "//input[@value='Continue']")

	public WebElement continuebtn;

	@FindBy(xpath = "//button[@id='launchSite' and @class='btn btn-primary disabled ng-scope']")

	public WebElement launchsitestandarddisable;

	@FindBy(xpath = "//a[@id='launchSite' and @class='btn btn-primary ng-scope']")

	public WebElement launchsiteenable;

	@FindBy(xpath = "//input[@id='startDate']")

	public WebElement STARTDATE;

	@FindBy(xpath = "//button[contains(text(),'Sync Sales Forms')]")

	public WebElement syncsalesForms;

	@FindBy(xpath = "//table/tbody/tr[3]/td[1]/input")

	public WebElement formDatecheck;

	@FindBy(xpath = "//div[@class='btn-group pull-right']/a[contains(text(),'Select Action')]")

	public WebElement selectAction;

	@FindBy(xpath = "//div[@class='btn-group pull-right ng-hide']/a[contains(text(),'Select Action')]")

	public WebElement selectActionhid;

	@FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[contains(text(),'ESTIMATE TAX')]")

	public WebElement estimateTax;

	@FindBy(xpath = "//div/button[contains(.,'ESTIMATE TAX')]")

	public WebElement STNDESTIMATETAX;

	@FindBy(xpath = "//button[@id='btn-view-reports' and @disabled='disabled']")

	public WebElement selectActiondisable;

	@FindBy(xpath = "//table/tbody/tr[3]/td[8]/a")

	public WebElement formstatusError;

	@FindBy(xpath = "//button[contains(text(),'Sync Customer Records')]")
	
	public WebElement synccustomerRecords;

	@FindBy(xpath = "//table/tbody/tr[3]/td[8]/a")
	public WebElement validate;

	@FindBy(name = "suggestedAddress")
	
	public WebElement suggestaddress;

	@FindBy(xpath = "//a[contains(.,'Save')]")
	
	public WebElement save;

	@FindBy(xpath = "//table/tbody/tr[3]/td[contains(text(),'Complete')]")

	public WebElement validationStatus;

	@FindBy(xpath = "//table/thead/tr/th[1]/input")

	public WebElement fromDatecheckall;

	@FindBy(xpath = "//pagination-extension/span/select")

	public WebElement pageExtention;

	@FindBys({@FindBy(xpath = "//table/tbody/tr[@class='ng-scope']"), })
	
	public List<WebElement> manageSalesformsrowscount;

	@FindBy(xpath = "//a[contains(text(),'POST TO JOURNAL')]")

	public WebElement postjournal;

	@FindBy(id = "btn-tax-estimate")

	public WebElement btnestimateTax;

	@FindBy(id = "tbQuickSearch")

	public WebElement quickSearch;

	@FindBys({@FindBy(xpath = "//table/tbody/tr[@class='ng-scope'][1]") })
	
	public List<WebElement> ttablesearchitemsize;

	@FindBy(xpath = "//table/tbody/tr[@class='ng-scope'][1]/td/div/a")

	public WebElement totaltaxAmountlnk;

	@FindBy(xpath = "//a[contains(text(),'Hide Details')]")

	public WebElement HIDEDETAILS;

	@FindBy(xpath = "//table/tbody/tr[5]/td/div/table/tbody/tr[@class='ng-scope']")
	
	public List<WebElement> transtaxdetailstable;

	@FindBy(xpath = "//table/tbody/tr[5]/td/div/table/tbody/tr[@class='ng-scope'][1]/td")
	
	public List<WebElement> transtaxdetailstablecolumn;

	@FindBys({@FindBy(xpath = "//div[7]/div/div[2]/div[1]/div[2]/div[2]/div/div") })
	
	public List<WebElement> QBOtaxamttable;

	@FindBy(xpath = "//i[@class='tableCell hi hi-close']")

	public WebElement invoiceClose;

	@FindBy(xpath = "//table/tbody/tr[@class='ng-scope'][1]")

	public WebElement FIRSTROW;

	public final String NPROGRESSBUSY_XPATH = "html[@class='nprogress-busy']";

	@FindBy(xpath = "//h3[contains(text(),'Repost Transaction(s)')]")

	public WebElement repostTransactions;

	@FindBy(xpath = "//button[contains(text(),'Continue')]")

	public WebElement Continuebtn;

	@FindBys({ @FindBy(xpath = "//div[@class='company-picker-list']/div/a"), })
	
	public List<WebElement> quickbookcompanies;

	@FindBy(xpath = "//h3[contains(text(),'EDIT SECURITY CREDENTIALS')]")

	public WebElement editSecuritycredentials;

	@FindBy(xpath = "//p[contains(text(),'Enter your updated API Key and API password below, and click the Save button to save the API key and API password in your settings.')]")

	public WebElement editSecuritycredentialsdescription;

	@FindBy(name = "Secretkey")

	public WebElement EditApikeypwd;

	@FindBy(xpath = "//button[contains(text(),'SAVE')]")

	public WebElement SaveBtn;

	@FindBy(xpath = "//button[@class='btn btn-primary disabled ng-scope' and contains(.,'Launch Site')]")

	public WebElement LAUNCHSITEDISABLE;

	@FindBy(xpath = "//div/span[contains(.,'API Password:')]/../span[2]")

	public WebElement APIPASSWORD;

	@FindBy(xpath = "//div/span[contains(.,'API Key:')]/../span[2]")

	public WebElement APIKEY;

	@FindBy(xpath = "//i[@class='icon-standalone-light icon-section icon-transfer']")

	public WebElement APITEXT;

	@FindBy(xpath = "//div[@class='company-picker-list']")

	public WebElement COMPlIST;

	@FindBy(xpath = "//span[@class='global-header-item settings']/button")

	public WebElement SETTINGS;

	@FindBy(xpath = "//img[@class='qblogo']")

	public WebElement QBOLOGO1;

	@FindBy(xpath = "//span[@class='global-header-item search']/button[@class='global-header-item-button']")

	public WebElement QBOINVOCIESEARCH;

	@FindBy(xpath = "//input[@id='dijit_form_TextBox_0']")

	public WebElement SEARCHINPUT;

	@FindBy(xpath = "//i[@class='tableCell hi hi-close']")

	public WebElement CLOSE;

	@FindBy(xpath = "//h3[contains(.,'Repost Transaction(s)')]")

	public WebElement REPOST;

	@FindBy(xpath = "//button[contains(.,'Continue')]")

	public WebElement CONTINUE;

	@FindBy(xpath = "//i[contains(.,'Runtime Error')]")

	public WebElement Runtimeerror;

	@FindBy(id = "startDate")

	public WebElement STARRTDATE;

	@FindBy(xpath = "//button[contains(.,'Connect')]")

	public WebElement CONNECT;

	@FindBy(xpath = "//input[@placeholder='Search for a company']")
	public WebElement ROQBOCOMPLIST;

	@FindBy(xpath = "//button[@class='action-button']")

	public WebElement ROQBOCOMPLISTDROPDOWN;

	@FindBys({ @FindBy(xpath = "//div[@class='company-list-item']/div"), })
	
	public List<WebElement> ROCOMPLIST;

	@FindBy(xpath = "//button[contains(.,'Next')]")

	public WebElement NEXT;

	@FindBy(partialLinkText = "Deleted")

	public WebElement DELETEDLINK;

	@FindBy(xpath = "//div[@class='help-block help-block-error ng-scope']/div[contains(.,'That description has already been used.')]")

	public WebElement ERPDESCRIPTIONERROR;

	public final String REQUESTPROCESSING = "//h3[contains(.,'Request Processing')]";

	@FindBy(xpath = "//p[contains(.,'No sales forms to display')]")

	public WebElement NOSALESFORMS;

	@FindBy(xpath = "//select[@id='ddlStatus']")

	public WebElement STATUS;

	@FindBy(xpath = "//td[contains(.,'Status:')]")
	public WebElement STATUSLABEL;

	@FindBy(xpath = "//div[@class='appcard connected-appcard ']/div/div[@class='appcard-details']/div[contains(.,'Vertex Cloud Returns Connector')]")
	public WebElement QBOVCRETURNSONLYCONNECTOR;

	@FindBy(xpath = "//a[contains(.,'Disconnect')]")
	public WebElement DISCONNECT;

	@FindBy(id = "cancel-ok")
	public WebElement DISCONNECTCONFIRM;

	@FindBy(xpath = "//form/ul[@id='ius-mfa-choices']")
	public WebElement QBOCONFIRMMYACCOUNT;

	@FindBy(xpath = "//a[text() = 'ADD CONNECTION']")
    public WebElement ADDCONNECTION;
    
    @FindBy(xpath = "//select[@id='select-erp']")
    public WebElement AVAILABLEAPIS;
    
    @FindBy(xpath = "//input[@id='erp-description']")
    public WebElement APIDESCRIPTION;
    
    @FindBy(xpath = "//label[contains(text(),'Consumer Key')]/following-sibling::input")
    public WebElement CONSUMERKEY;
    
    @FindBy(xpath = "//label[contains(text(),'Consumer Secret')]/following-sibling::input")
    public WebElement CONSUMERSECRET;
    
    @FindBy(xpath = "//label[contains(text(),'Access Token ID')]/following-sibling::input")
    public WebElement ACCESSTOKENID;
    
    @FindBy(xpath = "//label[contains(text(),'Access Token Secret')]/following-sibling::input")
    public WebElement ACCESSTOKENSECRET;
    
    @FindBy(xpath = "//label[contains(text(),'Account ID')]/following-sibling::input")
    public WebElement ACCOUNTID;
    
    @FindBy(xpath = "//button[text() = 'Submit']")
    public WebElement SUBMIT;
    
    @FindBy(xpath="//span[contains(.,'Connection to NetSuite was invalid, please verify the information entered and try again')]")
    public WebElement NETSUITEERROR;
    
    //New xpats created by manikanta
    
    @FindBy(xpath = "//h1[@class='text-center'][contains(.,'CONFIGURE COMPANY MAPPING')]")
    public WebElement CONFIGURE_COMPANY_MAPPING;
    
    //Intuit xpath
    
    @FindBy(xpath = "//a[@data-name='Sign In'][contains(.,'Sign In')]")
    public WebElement INTUITSIGNIN;
    
    @FindBy(xpath = "//input[@id='ius-userid']")
    public WebElement INTUITUSERNAME;
    
    @FindBy(xpath = "//input[@id='ius-password']")
    public WebElement INTUITPASSWORD;
    
    @FindBy(xpath = "//span[@id='ius-sign-in-submit-btn-text']")
    public WebElement INTUITSIGNINBTN;
    
    @FindBy(xpath = "(//a[@data-noshellroute='true'][contains(.,'Get started')])[1]")
    public WebElement GET_STARTED;
    
    @FindBy(xpath = "//a[@data-name='QuickBooks sandbox'][contains(.,'QuickBooks sandbox')]")
    public WebElement QUICKBOOKS_SANDBOX;
    
    @FindBy(xpath = "//a[contains(.,'Sandbox Company_US_1')]")
    public WebElement INTUITCOMPANY;
    
    @FindBy(xpath = "//svg[contains(@class,'qbo-logo-wordmark')]")
    public WebElement QBO_LOGO;
    
    @FindBy(xpath = "//div[@class='left-nav-default-create'][contains(.,'New')]")
    public WebElement INTUITBUTNEW;
    
    @FindBy(xpath = "//a[@id='invoice']")
    public WebElement INTUITINVOICE;
    
    @FindBy(xpath = "(//div[contains(@class,'comboboxItemName')])")
    public WebElement INVOICECUSTMER;
    
    @FindBy(xpath = "//input[@id='uniqName_9_0']")
    public WebElement INVOICECUSTMER1;
    
    @FindBy(xpath = "(//div[contains(.,'product/service')])[20]")
    public WebElement Product;
    
    @FindBy(xpath = "//input[@id='improved-inventory/js/extension-providers/ItemComboBox_0']")
    public WebElement INVOICEPRODUST;
    
    @FindBy(xpath = "//input[@id='uniqName_27_23']")
    public WebElement INVOICERATE;
    
    @FindBy(xpath = "//button[@data-automation-id='btn-footer-save']")
    public WebElement SAVEBUT;
    
    @FindBy(xpath = "//div[@class='message']")
    public WebElement MESSAGENODE;
    
    @FindBy(xpath = "//a[@id='recvpmt']")
    public WebElement RECEIVEPAYMENT;
    
    @FindBy(xpath = "//button[@id='ius-mfa-email-otp-card-challenge']")
    public WebElement CODETOEMAIL;
    
    @FindBy(xpath = "//input[@id='ius-mfa-confirm-code']")
    public WebElement CONFIRM_CODE;
    

}
