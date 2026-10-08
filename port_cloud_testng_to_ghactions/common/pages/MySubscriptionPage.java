package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MySubscriptionPage {
	@FindBy(xpath = "//a[@title='My Subscriptions']")
	public WebElement MYSUBSCRIPTIONLINK;

	@FindBy(xpath = "//h1")
	public WebElement MYSUBSCRIPTIONLABEL;

	@FindBy(xpath = "//a[contains(text(),'Edit')]")
	public WebElement EDITLINK;

	@FindBy(xpath = "//h4[contains(text(),'Professional')]")
	public WebElement PROFESSIONALSUBSCRIPTION;

	@FindBy(xpath = "//label/img[@alt='Rate File Only']")
	public WebElement RATEFILEONLYSUBSCRIPTION;

	@FindBy(xpath = "//input[contains(@data-bind, 'checked: hasRateFile') and contains(@value, 'True')]")
	public WebElement RATEFILEYES;

	@FindBy(xpath = "//input[contains(@data-bind, 'checked: hasRateFile') and contains(@value, 'False')]")
	public WebElement RATEFILENO;

	@FindBy(xpath = "//input[@id='s2id_autogen2']")
	public WebElement STATE;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement SAVEBUTTON;

	@FindBy(xpath = "//button[contains(.,'Save Changes')]")
	public WebElement SAVECHANGES;

	@FindBy(xpath = "//button[text()='Cancel Customer']")
	public WebElement CANCELCUSTOMER;

	@FindBy(xpath = "//form[@action='/subscription/cancel']/button[text()='Continue']")
	public WebElement CONTINUE;

	@FindBy(xpath = "//form[@class='form-inline']/button[text()='Reactivate']")
	public WebElement REACTIVATE;

	@FindBy(xpath = "//a[contains(text(),'Cancel')]")
	public WebElement CANCELLINK;

	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	public WebElement CONTINUEBUTTON;

	@FindBy(xpath = "//a[contains(text(),'Update')]")
	public WebElement UPDATELINK;

	@FindBy(xpath = "//input[@name='bill_to_forename']")
	public WebElement FIRSTNAME;

	@FindBy(xpath = "//input[@name='bill_to_surname']")
	public WebElement LASTNAME;

	@FindBy(xpath = "//input[@name='bill_to_email']")
	public WebElement EMAIL;

	@FindBy(xpath = "//input[@name='bill_to_address_line1']")
	public WebElement ADDRESSLINE1;

	@FindBy(xpath = "//input[@id='bill_to_address_line2']")
	public WebElement ADDRESSLINE2;

	@FindBy(xpath = "//input[@name='bill_to_address_city']")
	public WebElement CITY;

	@FindBy(id = "bill_to_address_state")
	public WebElement BILLTOSTATE;

	@FindBy(xpath = "//input[@name='bill_to_address_postal_code']")
	public WebElement BILLTOZIP;

	@FindBy(xpath = "//input[@name='card_number']")
	public WebElement CREDITCARDNUMBER;

	@FindBy(xpath = "//select[@id='expiry_month']")
	public WebElement EXPIRATIONMONTH;

	@FindBy(xpath = "//select[@id='expire-year']")
	public WebElement EXPIRATIONYEAR;

	@FindBy(xpath = "//input[@name='card_cvn']")
	public WebElement SECURITYCODE;

	@FindBy(xpath = "//button[@class='btn btn-primary' and @disabled='']")
	public WebElement SAVEBILLINGINFODISABLE;

	@FindBy(xpath = "//button[contains(text(),'Submit Billing Information')]")
	public WebElement BILLTOSAVEBUTTON;

	@FindBy(xpath = "//p[2]/a[contains(text(),'Edit Subscription')]")
	public WebElement EDITSUBSCRIPTION;

	@FindBy(xpath = "//a/span[contains(text(),'Document Center')]")
	public WebElement DOCUMENTCENTRE;

	@FindBy(xpath = "//div[@class='alert alert-success ng-binding ng-scope']")
	public WebElement SUCCESS;

	@FindBy(xpath = "//h4[contains(text(),'Premium')]")
	public WebElement PREMIUMSUB;

	public WebElement UPGRADEBUTTON;

	@FindBy(xpath = "//button[contains(text(),'Submit Billing Information')]")
	public WebElement SAVEBILLINGINFO;

	@FindBy(xpath = "//i[@class='icon-info icon-small']")
	public WebElement BILLINGICON;

	@FindBy(xpath = "//h3[contains(text(),'Billing Edit')]")
	public WebElement BILLINGICONTEXT;

	@FindBy(xpath = "//div[@id='payment-card']/div[2]/div[3]/span/i")
	public WebElement CSVICON;

	@FindBy(xpath = "//h3[contains(text(),'CSV')]")
	public WebElement CSVICONTEXT;

	@FindBy(xpath = "//span/i[@class='icon-info icon-small']")
	public WebElement SUBSCRIPTIONICON;

	@FindBy(xpath = "//h3[contains(text(),'My Subscriptions')]")
	public WebElement SUBSCRIPTIONICONTEXT;

	@FindBy(xpath = "//form[@id='createForm']/fieldset/div[2]/div/ul/li/label/strong/span[2]")
	public WebElement PROFESSIONALICON;

	@FindBy(xpath = "//h3[contains(text(),'Professional  Subscription')]")
	public WebElement PROFESSIONALICONTEXT;

	@FindBy(xpath = "//form[@id='createForm']/fieldset/div[2]/div/ul/li[3]/label/strong/span[2]")
	public WebElement RATEFILEONLYICON;

	@FindBy(xpath = "//h3[contains(text(),'Rate File Only Subscription')]")
	public WebElement RATEFILEONLYICONTEXT;

	@FindBy(xpath = "//form[@id='createForm']/fieldset/div[2]/div/ul/li[2]/label/strong/span[2]")
	public WebElement PREMIUMICON;

	@FindBy(xpath = "//h3[contains(text(),'//h3[contains(text(),'Premium Subscription')]")
	public WebElement PREMIUMICONTEXT;

	@FindBy(css = "div.control-group>select")
	public WebElement MONTHYEAR;

	@FindBy(css = "button.btn.btn-primary.pull-right")
	public WebElement DWNLDBTN;

	@FindBy(xpath = "//a[contains(.,'Upload New File')]")
	public WebElement UPDNEWFILELINK;

	@FindBy(css = "label.file-input-wrapper.btn.btn-default.upload-file")
	public WebElement BROWSE;

	@FindBy(css = "button.btn.btn-primary.ng-binding")
	public WebElement UPLOAD;

	@FindBy(xpath = "//div[contains(.,'Your file has been successfully uploaded!')]")
	public WebElement SUCESSMSG;

	@FindBy(xpath = "//input[@id='include-rate-file']")
	public WebElement RATEFILECHECKBOX;

	@FindBy(xpath = "//input[@id='returns-only']")
	public WebElement RETURNSONLY;

	@FindBy(xpath = "//input[@id='include-online-rate-file-calculation']")
	public WebElement REALTIME;

	@FindBy(xpath = "//select[@id='bill_to_address_country']")
	public WebElement COUNTRY;

	@FindBy(xpath = "//div/a[contains(.,'View All Connectors')]")
	public WebElement VIWALLCONNECTORS;

	@FindBy(xpath = "//a[@class='btn pull-right' and text()='Return to Subscription']")
	public WebElement RETURNTOSUB;

	@FindBy(xpath = "//strong[text()='No, cancel request']")
	public WebElement NOCANCELREQUEST;

	@FindBy(xpath = "//span[text()='This field is required.']")
	public WebElement ERRMSG;

	@FindBy(xpath = "//textarea[@id='cancelComments']")
	public WebElement CANCELCOMMENTS;

	@FindBy(xpath = "//a[contains(text(),'Go Back')]")
	public WebElement GOBACK;

	@FindBy(xpath = "//div/strong[text()='Error!']")
	public WebElement INVALIDPSWDMSG;

	@FindBy(xpath = "//div[@class='alert alert-success ng-binding ng-scope']")
	public WebElement SUCCESSSUB;

	@FindBy(xpath = "//div[@class='modal hide fade warning in']")
	public WebElement WARNING;

	@FindBy(xpath = "//td/a[text()='Original File']")
	public WebElement ORIGINALFILE;

	@FindBy(xpath = "//button[contains(.,'Reconciliation Report')]")
	public WebElement RECONCILATIONREPORT;

	@FindBy(xpath = "//textarea[@id='inputCancelRequestReason']")
	public WebElement CANCELREQUESTREASON;

	@FindBy(xpath = "//h3[contains(.,'Confirm Cancellation')]")
	public WebElement CANCELCONFIRM;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement ENTERTAXCENTRAL;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement REACTIVATEPWD;

	@FindBy(xpath = "//button[contains(.,'Cancel Subscription')]")
	public WebElement CANCELSUBB;

	@FindBy(xpath = "//h3[contains(.,'Confirm Cancellation')]/../..//button[contains(.,'Cancel Subscription')]")
	public WebElement CANCELSUB;

	@FindBy(xpath = "//h2[contains(.,'You have successfully requested to cancel your account.')]")
	public WebElement CANCELSUBSUCCESS;

	@FindBy(xpath = "//span[contains(text(),'Rates & Jurisdictions')]")
	public WebElement LOOKUPTAXRATESLINK;

	public final String ENTERTAXCENTRAL_XPATH = "//input[@id='password']";

	public final String GOBACK_XPATH = "//a[contains(text(),'Go Back')]";

	@FindBy(xpath = "//strong[text()='Tax Calculations and Returns.']/../input")
	public WebElement CALRETURNSRADIOBTN;

	@FindBy(xpath = "//strong[text()='Taxability Impact Report.']/../input")
	public WebElement TAXIMPACTREPORT;

	@FindBy(xpath = "//strong[contains(.,'Premium: Tax Calculations and Returns')]")
	public WebElement DOWNGRADEPRETAXCAL;

	@FindBy(xpath = "//strong[contains(.,'Premium: Returns Only')]")
	public WebElement DOWNGRADEPRETCR;

	@FindBy(xpath = "//strong[contains(.,'( onboarding pending )')]")
	public WebElement ONBOARDPENDING;

	@FindBy(xpath = "//div[1]/div[2]/div/div/text[1]")
	public WebElement SUBSCRIPTIONCHANGEDOWNGRADEMSG;

	@FindBy(xpath = "//strong[contains(.,'Returns Only.')]/../input")
	public WebElement RETURNSONLY_SUB;

	@FindBy(xpath = "//strong[contains(.,'Tax Calculations and Returns.')]/../input")
	public WebElement TAXCALCRETURNS_SUB;

	@FindBy(xpath = "//strong[contains(text(),'Tax Calculations.')]/../input")
	public WebElement TAXCALCONLY_SUB;

	@FindBy(xpath = "//input[@id='include-taxability-impact-report']")
	public WebElement TAXIMPACTREPORT_SUB;

	@FindBy(xpath = "//strong[contains(.,'Accelerator - Oracle ERP Cloud.')]/../input")
	public WebElement ACCELERATORORACLEERPCLOUD;

	@FindBy(xpath = "//input[@id='include-retail-tax-extract']")
	public WebElement RETAILTAXEXTRACT;

	@FindBy(xpath = "//input[@id='include-global-compliance']")
	public WebElement GLOBALCOMPLIANCE;

	@FindBy(xpath = "//input[@id='include-data-integrity']")
	public WebElement DATAINTEGRITY;

	@FindBy(xpath = "//select[@id='data-integrity-storage-region']")
	public WebElement DATAINTEGRITYREGION;

	@FindBy(xpath = "//input[@id='include-retail-tax-extract']")
	public WebElement RTE_SUB;

	@FindBy(xpath = "//input[@id='include-global-compliance']")
	public WebElement GLOBACOMPLIANCE_SUB;

	@FindBy(xpath = "//a[@id='login-page-link']")
	public WebElement CONTINUELOGINPAGELINK;

	@FindBy(xpath = "//div[@class='section ng-scope']/a[text()='Return to Subscription']")
	public WebElement RETURNTOSUBSCRIPTIONLNK;

	@FindBy(xpath = "//div[@class='section']/a[text()='Return to Settings']")
	public WebElement RETURNTOSETTINGS;

	@FindBy(name = "Subscription.hasTestInstance")
	public WebElement testInstanceradioBtn;
	
	@FindBy(xpath="//input[@id='include-payroll-test-environment']")
	public WebElement PAYROLLTEST;

	@FindBy(xpath = "//text[contains(.,'your subscription will change to Professional: Tax Calculations and Returns')]")
	public WebElement downgradesubfrompremiumtcrstoProtcrs;

	@FindBy(xpath = "//text[contains(.,'your subscription will change to Professional: Returns Only')]")
	public WebElement downgradesubfrompremiumtoProReturnsonly;

	@FindBy(xpath = "//a[contains(.,'Select All')]")
	public WebElement SELECTAll;

	@FindBy(xpath = "//input[@ng-value='data.rateFileConsumptionTypes.USCADConsolidated.id']")
	public WebElement ONECONSOLIDATEDFILE;

	@FindBy(xpath = "//input[@ng-value='data.rateFileConsumptionTypes.Individual.id']")
	public WebElement INDIVIDUALFILE;

	@FindBy(xpath = "//div[contains(text(),'(60) rate files selected')]")
	public WebElement SELECTEDRATEFILES;

	@FindBy(xpath = "//a[@id='confirm-download']")
	public WebElement CONFIRMDOWNLOAD;

	@FindBy(xpath = "//h3[contains(.,'SELECT DOWNLOAD PREFERENCE')]")
	public WebElement SELECTDOWNLOADPREFERENCE;

	@FindBy(xpath = "//div[@class='alert alert-error ng-scope'][contains(.,'Unable to update your Vertex Cloud subscription.')]")
	public WebElement SUBCANCELERRORMSG;

	@FindBy(xpath = "//a[text()='Yes, Complete Cancel']")
	public WebElement YESCOMPLETECANCEL;

	@FindBy(xpath = "//button[contains(.,'Cancel Customer')][@disabled='disabled']")
	public WebElement CANCELCUSTOMERDISABLED;

	@FindBy(xpath = "//span/input[@id='bill_to_address_city']/../div/span[contains(.,'This field is required')]")
	public WebElement CITYERROR;

	@FindBy(xpath = "//input[@name='card_cvn']/../div/span[contains(.,'This field is required.')]")
	public WebElement CVNError;

	@FindBy(xpath = "//span/input[@id='bill_to_address_line1']/../div/span[contains(.,'This field is required')]")
	public WebElement ADDRESSLINE1ERROR;

	@FindBy(xpath = "//div[contains(.,'User has been deleted.')][@class='alert alert-danger ng-binding']")
	public WebElement DeleteUserloginmessage;

	public final String NPROGRESSBUSY_XPATH = "html[@class='nprogress-busy']";

	@FindBy(xpath = "//input[@name='Subscription.hasLegacyTransactionReport']")
	public WebElement LEGACYTRANSACTIONREPORT;

	@FindBy(xpath = "//button[contains(.,'Accept')]")
	public WebElement ACCEPT;

	@FindBy(xpath = "//input[@id='include-payroll-calculation']")
	public WebElement payrollcalc;

	@FindBy(xpath = "//input[@id='include-payroll-address-cleansing']")
	public WebElement payrolladdresscleansing;

}
