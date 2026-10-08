package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerPortalAddNewUser {

	@FindBy(xpath = "//span[contains(.,'Settings')]")
	public WebElement ClickSettings;

	@FindBy(xpath = "//a[contains(.,'Add New User')]")
	public WebElement AddNewUser;

	@FindBy(xpath = "//input[@id='firstName']")
	public WebElement Firstname;

	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement Lastname;

	@FindBy(xpath = "//input[@id='emailId']")
	public WebElement Emailid;

	@FindBy(xpath = "//input[@id='NewUser_Phone']")
	public WebElement PhoneNumber;

	@FindBy(xpath = "//select[@id='NewUser_Role']")
	public WebElement Role;

	@FindBy(xpath = "//a[@class='btn btn-primary'][contains(.,'View All Users')]")
	public WebElement ViewallUsers;

	@FindBy(xpath = "//h1//a[contains(.,'Return to Settings')]//..[contains(.,'My Users')]")
	public WebElement Myuserspage;

	@FindBy(xpath = "//button[contains(.,'Send Invite')]")
	public WebElement SendInvite;

	@FindBy(xpath = "//a[contains(.,'https:')]")
	public WebElement confirmlink;

	@FindBy(xpath = "//input[@id='UserName']")
	public WebElement Username;

	@FindBy(xpath = "//input[@id='Password']")
	public WebElement Npassword;

	@FindBy(xpath = "//input[@id='PasswordConfirm']")
	public WebElement Confirmpassword;

	@FindBy(xpath = "//input[@id='AcceptedTerms']")
	public WebElement terms;

	@FindBy(xpath = "//button[contains(.,'Complete')]")
	public WebElement complete;

//	standard/professiona;/premium
	@FindBy(xpath = "//span[contains(.,'Dashboard')]")
	public WebElement Vdashboard;

	@FindBy(xpath = "//p[contains(.,'You can')]//..//a//..//..//a[contains(.,'tax rates')]")
	public WebElement Vdashboardlink;

	@FindBy(xpath = "//span[contains(text(),'Rates & Jurisdictions')]")
	public WebElement RatesJurisdictions;

	@FindBy(xpath = "//span[contains(text(),'Rate File')]")
	public WebElement RateHover;

	@FindBy(xpath = "//span[contains(.,'Lookup Tax Rates')]")
	public WebElement Vlookuptaxrate;

	@FindBy(xpath = "//span[contains(.,'Taxability Impact Report')]")
	public WebElement VtaximpReport;

	@FindBy(xpath = "//span[contains(.,'Reports')]")
	public WebElement VReports;

	@FindBy(xpath = "//span[contains(.,'Settings')]")
	public WebElement VSettings;

	@FindBy(xpath = "//a[@class='btn btn-primary'][contains(.,'View All Users')]")
	public WebElement SettingsViewallusers;

	@FindBy(xpath = "//body//div//div//div//div//div//div//div//div/table//thead//tr//th[contains(.,'Action')]")
	public WebElement ActionViewallusers;

	@FindBy(xpath = "//a[@class='btn btn-primary'][contains(.,'View All Connectors')]")
	public WebElement ViewallConnectors;

	@FindBy(xpath = "//button[@ng-if='data.launchSiteDisable'][contains(.,'Launch Site')]")
	public WebElement Viewlaunchsite;

	@FindBy(xpath = "//span[contains(.,'Reports')]//..//..//ul//a[contains(.,'Validation Errors')]")
	public WebElement ValidationError;

	@FindBy(xpath = "//span[contains(.,'Reports')]")
	public WebElement reports;

	@FindBy(xpath = "//span[contains(.,'Rate File')]")
	public WebElement ratefile;

	@FindBy(xpath = "//a[contains(.,'My Rate Files')]")
	public WebElement myratefile;

	@FindBy(xpath = "//body//div//div//div//div//div//div//div//div//a[contains(.,'Upload New File')]")
	public WebElement uploadlink;

	@FindBy(xpath = "//button[contains(.,'Generate Returns')]")
	public WebElement GenReturns;

	@FindBy(xpath = "//a[contains(.,'Manage Tax Returns')]")
	public WebElement ManTaxRet;

	@FindBy(xpath = "//a[contains(.,'Manage Out-of-State Sales')]")
	public WebElement ManOutst;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement Savebutton;

	@FindBy(xpath = "//tbody//tr//td[contains(.,'Current Value')]//table//tr//td//input[@ng-disabled='isOutOfStateSalesLocked() || !hasWriteAccess']")
	public WebElement Currentvalue;

	@FindBy(xpath = "//span[contains(.,'Manage Tax Returns')]")
	public WebElement Managetax;

	@FindBy(xpath = "//a[contains(.,'Manage Prepayments')]")
	public WebElement ManagePrepayments;

	@FindBy(xpath = "//body//tr//td//a[contains(.,'Manage Prepayments')]")
	public WebElement ManagePre;

	@FindBy(xpath = "//button[@id='prepayment-amount0'][contains(.,'Pay')]")
	public WebElement PayButton;

	@FindBy(xpath = "//input[@id='prepayment-amount0'][@ng-disabled='!prepayment.isEditable || !vm.hasWriteAccess']")
	public WebElement AmountpaidButton;

	@FindBy(xpath = "//td//input")
	public WebElement checkbox;

	@FindBy(xpath = "//th//input")
	public WebElement applycheckbox;

	@FindBy(xpath = "//th[contains(.,'Prepayments')]")
	public WebElement Prepaytext;

	@FindBy(xpath = "//p/a[contains(.,'Extended Help')]")
	public WebElement EXTENDHELP;

	@FindBy(xpath = "//div/a[Contains(.,'Edit My Company')]")
	public WebElement EDITMYCMPANY;

	@FindBy(xpath = "//h1/a[contains(.,'Return to Settings')]")
	public WebElement RETURNTOSETTINGS;

	@FindBy(xpath = "//a[contains(.,'Export')]")
	public WebElement EXPORT;

	//// edit user role
	@FindBy(xpath = "a[contains(.,'Add New User')]")
	public WebElement Addnewuser;
}
