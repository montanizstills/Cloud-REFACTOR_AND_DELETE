/*###################################################################################################
Name: RegistrationPage.java 
Description: VertexSMB Add user registration Page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CPA {

	@FindBy(xpath = "//a[@title='Manage Clients']")
	public WebElement MANAGECLIENTSMENU;

	@FindBy(xpath = "//a[contains(.,'Enroll New Client')]")
	public WebElement ENROLLNEWCLIENT;

	@FindBy(xpath = "//label[contains(.,'Company Name')]/../input")
	public WebElement BASICCOMPANYNAME;

	@FindBy(xpath = "//select[@id='country']")
	public WebElement BASICCOUNTRY;
	
	@FindBy(xpath = "//input[@id='address']")
	public WebElement BASISTREETADD;
	
	@FindBy(xpath = "//input[@id='city']")
	public WebElement BASICCITY;
	
	@FindBy(xpath = "//select[@id='state']")
	public WebElement BASICSTATE;
	
	@FindBy(xpath = "//input[@id='postal']")
	public WebElement BASICZIP;
	
	@FindBy(xpath = "//input[@id='clientPhone']")
	public WebElement BASICPHONE;
	
	@FindBy(xpath = "//input[@id='phoneNumber']")
	public WebElement MerchantPHONE;
	
	@FindBy(xpath = "//input[@id='user-name']")
	public WebElement MERCHANTUSERNAME;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement PASSWORD;

	@FindBy(xpath = "//input[@id='confirm-password']")
	public WebElement CONFIRMPASSWORD;

	@FindBy(xpath = "//button[contains(.,'Finish and Save')]")
	public WebElement FINISHANDSAVE;
	
	@FindBy(xpath = "//h4[contains(.,'Professional')]")
	public WebElement PROFESSIONALSUBSCRIPTION;
	
	@FindBy(xpath = "//h4[contains(.,'Premium')]")
	public WebElement PREMIUMSUBSCRIPTION;
	
	@FindBy(xpath = "//h4[contains(.,'Standard')]")
	public WebElement STANDARD;
	
	@FindBy(xpath = "//strong[text()='Tax Calculations and Returns.']/../input")
	public WebElement TAXCALRETRADIO;
	
	@FindBy(xpath = "//strong[text()='Returns Only.']/../input")
	public WebElement RETONLYRADIO;
	
	@FindBy(xpath = "//input[@id='calc-only']")
	public WebElement CALCONLY;
	
	@FindBy(xpath = "//strong[text()='Rate File.']/../input")
	public WebElement RATEFILECHECK;
	
	@FindBy(xpath = "//strong[text()='Real-Time Rate Service.']/../input")
	public WebElement REALTIMERATESERV;
	
	@FindBy(xpath = "//strong[contains(.,'Taxability Impact Report.')]/../input")
	public WebElement TAXIMPACTREPORT;
	
	@FindBy(xpath = "//button[contains(.,'Save & Continue')]")
	public WebElement SAVEANDCONTINUE;
	
	@FindBy(xpath = "//div/a[contains(.,'Agreement')]")
	public WebElement AGREEMENTLINK;
	
	@FindBy(xpath = "//button[contains(.,'I Agree')]")
	public WebElement IAGREELINK;
	
	@FindBy(xpath = "//select[@id='bill_to_address_country']")
	public WebElement BILLCOUNTRY;
	
	@FindBy(xpath = "//input[@name='bill_to_address_line1']")
	public WebElement BILLADDRESS1;
	
	@FindBy(xpath = "//input[@name='bill_to_address_city']")
	public WebElement BILLCITY;
	
	@FindBy(xpath = "//select[@id='bill_to_address_state']")
	public WebElement BILLSTATE;
	
	@FindBy(xpath = "//input[@name='bill_to_address_postal_code']")
	public WebElement BILLZIP;
	
	@FindBy(xpath = "//ul[@id='billing-tabs']/li[2]/a")
	public WebElement ACHPAYMENT;
	
	@FindBy(xpath = "//ul[@id='billing-tabs']/li[1]/a")
	public WebElement CREDITCARDPAYMENT;
	
	@FindBy(xpath = "//input[@name='card_number']")
	public WebElement CREDITCARD;
	
	@FindBy(xpath = "//select[@id='expiry_month']")
	public WebElement EXPMONTH;
	
	@FindBy(xpath = "//select[@id='expiry_month']/option")
	public WebElement EXPMONTH_VALUES;
	
	@FindBy(xpath = "//select[@id='expire-year']")
	public WebElement EXPYEAR;
	
	@FindBy(xpath = "//input[@id='firstName']")
	public WebElement MerchantFIRSTNAME;
	
	@FindBy(xpath = "//input[@id='bill_to_forename']")
	public WebElement BILLFIRSTNAME;
	
	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement MerchantLastNAME;
	
	@FindBy(xpath = "//input[@id='email']")
	public WebElement MerchantMail;
	
	@FindBy(xpath = "//h1[contains(.,'Complete Your Registration')]")
	public WebElement CompleteYourRegistrationTitle;
	
	@FindBy(xpath = "//select/option[contains(.,'Admin')]")
	public WebElement DefaultRoleAsAdmin;
	
	@FindBy(xpath = "//input[@id='bill_to_surname']")
	public WebElement BILLLASTNAME;

	@FindBy(xpath = "//input[@id='card_cvn']")
	public WebElement CVN;

	@FindBy(xpath = "//input[@id='clientPhone']/../div/span[text()='This field is required.']")
	public WebElement ClientPhoneErrorText;

	@FindBy(xpath = "//input[@id='postal']/../div/span[text()='This field is required.']")
	public WebElement ZipErrorText;

	@FindBy(xpath = "//span/strong[text()='ACH']")
	public WebElement ACH;
	
	@FindBy(xpath = "//input[@name='echeck_account_number']")
	public WebElement BANKNUMBER;
	
	@FindBy(xpath = "//input[@name='echeck_routing_number']")
	public WebElement ROUTINGNUMBER;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement SUBMIT;

	// Verify Manage Clients
	@FindBy(xpath = "//a[contains(.,'Enrolled Clients')]")
	public WebElement ENROLLED_CLIENTS;
	
	@FindBy(xpath = "//a[contains(.,'Referred Clients')]")
	public WebElement REFERRED_CLIENTS;
	
	@FindBy(xpath = "//a[contains(.,'Shared Services Clients')]")
	public WebElement SHARED_SERVICES_CLIENTS;
	
	@FindBy(xpath = "//label[text()='Search by Client Name']/../input")
	public WebElement SEARCHBYCLIENT;
	
	@FindBy(xpath = "//input[@ng-model='enrolled.searchFields.textSearch.value']")
	public WebElement ENROLLEDSEARCHBYCLIENT;
	
	@FindBy(xpath = "//input[contains(@ng-model,'referred.searchFields.textSearch.value')]")
	public WebElement REFERREDSEARCHBYCLIENT;
	
	@FindBy(xpath = "//input[@ng-model='shared.searchFields.textSearch.value']")
	public WebElement SHAREDSEARCHBYCLIENT;
	
	@FindBy(xpath = "//label[text()='Filter by Status']/../select")
	public WebElement FILTERBYSTATUS;
	
	@FindBy(xpath = "//label[text()='Filter by Subscription']/../select")
	public WebElement FILTERBYSUBSC;

	@FindBy(xpath = "//input[@id='SupportRequest_SalesforceSupportDetails_CompanyName']")
	public WebElement COMPANYNAME;

	@FindBy(xpath = "//a[text()='Referred Clients']")
	public WebElement REFERREDCLIENTS;

	@FindBy(xpath = "//div[2]/div[1]/div[1]/input")
	public WebElement SEARCHCLIENTNAME;

	@FindBy(xpath = "//a[@title='Document Center']")
	public WebElement DOCUMENTCENTER;

	@FindBy(id = "account-type")
	public WebElement ACCOUNTTYPE;

	@FindBy(id = "routing-number")
	public WebElement AROUTINGNUMBER;

	@FindBy(id = "account-number")
	public WebElement ACCOUNTNUMBER;

	@FindBy(xpath = "//button[contains(text(),'Save Banking Information')]")
	public WebElement SAVEBANKINFORMATION;

	@FindBy(id = "billing-information-password")
	public WebElement BILLINFOPASSWORD;

	@FindBy(id = "billing-password-ok")
	public WebElement BILLINGPASSWORDOK;

	@FindBy(xpath = "//select[@id='account-type' and @readonly='readonly']")
	public WebElement ACCOUNTTYPEAUPDATION;

	@FindBy(xpath = "//div[2]/div[2]/div/table/tbody/tr[2]/td[5]")
	public WebElement TAXCENTRALACCESSLEVEL;

	@FindBy(xpath = "//h3[contains(.,'No Access Granted')]")
	public WebElement NOACCESSGRANTED;

	@FindBy(xpath = "//h2[contains(.,'Configuration Report')]")
	public WebElement CONFIGURATIONREPORT;

	@FindBy(xpath = "//h2[contains(.,'Taxability Report')]")
	public WebElement TAXABILITYREPORT;

	@FindBy(xpath = "//h2[contains(.,'Transaction Detail Report')]")
	public WebElement TRANSACTIONDETAILREPORT;
	
	@FindBy(xpath = "//h2[contains(.,'Transaction Summary Report')]")
	public WebElement TRANSACTIONSUMMARYREPORT;
	
	@FindBy(xpath = "//h2[contains(.,'Unassigned Data Report')]")
	public WebElement UNASSIGNEDDATAREPORT;

	@FindBy(xpath = "//strong[contains(.,'Name:')]/..")
	public WebElement NAME;

	@FindBy(xpath = "//h2[contains(.,'Configuration Report')]/../p/a[contains(.,'Download Report')]")
	public WebElement CONFIGURATIONREPORT_DOWNLOAD;

	@FindBy(xpath = "//input[@id='phoneNumber']/../div/span[text()='This field is required.']")
	public WebElement PhoneErrorText;

	@FindBy(xpath = "//input[@id='postal']/../div/span[text()='This field is required.']")
	public WebElement ZipCodeErrorText;

	@FindBy(xpath = "//input[@id='email']/../div/span[text()='This field is required.']")
	public WebElement EmailText;

	@FindBy(xpath = "//input[@id='password']/../div/span[text()='This field is required.']")
	public WebElement passwordText;

	@FindBy(xpath = "//input[@id='confirm-password']/../div[2]/span[text()='This field is required.']")
	public WebElement ConfirmpasswordText;

	@FindBy(xpath = "//h1[contains(.,'Manage Sales Forms')]")
	public WebElement Managesalesforms;

	@FindBy(xpath = "//input[@id='tbPhone']/../div/span[contains(.,'This field is required.')]")
	public WebElement VarPhoneErrorText;

	@FindBy(xpath = "//input[@id='tbZipCpde']/../div/span[contains(.,'This field is required.')]")
	public WebElement VarZipCodeErrorText;

	@FindBy(xpath = "//input[@name='bill_to_address_postal_code']/../div/span[contains(.,'This field is required.')]")
	public WebElement BillingZipError;

	// Manage Merchants
	@FindBy(xpath = "//a[@title='Manage Merchants']")
	public WebElement MANAGEMERCHANTSMENU;

	@FindBy(xpath = "//div[@class='row-fluid']/descendant::input")
	public WebElement SEARCHBYMERCHANT;

	@FindBy(xpath = "//a[contains(.,'Terms of service')]")
	public WebElement TERMSOFSERVICE;

	@FindBy(xpath = "//a[contains(.,'Services Agreement')]")
	public WebElement SERVICESAGREEMENT;

	@FindBy(xpath = "//a[contains(.,'Shared Services Clients')]")
	public WebElement SHAREDSERVICESLINK;

	@FindBy(xpath = "//label[text()='Search by Client Name']/../input[@ng-model='shared.searchFields.textSearch.value']")
	public WebElement SEARCHSSCLIENT;
	
	@FindBy(xpath = "//a[contains(.,'Enroll Shared Services')]")
	public WebElement ENROLLSHAREDSERVICES;
	
	@FindBy(xpath = "//input[@id='password']")
	public WebElement MerchantPASSWORD;
	
	@FindBy(xpath = "//input[@id='confirm-password']")
	public WebElement MerchantCONFIRMPASSWORD;

	@FindBy(xpath = "//input[@id='Username']")
	public WebElement MERCHANTRESENDUSERNAME;

	@FindBy(xpath = "//button[contains(.,'Complete Registration')]")
	public WebElement MERCHANTRESENDFINISH;

	@FindBy(xpath = "//a[contains(.,'Login')]")
	public WebElement afterLogin;

	@FindBy(xpath = "//a[contains(.,'Change CPA Client Type')]")
	public WebElement CHANGECPACLIENTTYPE;

	@FindBy(xpath = "//h3[contains(.,'Change CPA client type')]")
	public WebElement CHANGECPACLIENTTYPEHEADING;

	@FindBy(xpath = "//div/div[contains(.,'CPA client type List')]/../div/select")
	public WebElement CLIENTTYPELIST;

	@FindBy(xpath = "//form/div/div[contains(.,'You have Selected a CPA client type that is the same as their current type. Please select another CPA client type.')]")
	public WebElement CPACLIENTTYPEERROR;

	@FindBy(id = "save-cancel")
	public WebElement CANCELCLIENTTYPE;

	@FindBy(id = "save-ok")
	public WebElement SAVECLIENTTYPE;

	@FindBy(xpath = "//a[contains(.,'Enrolled Clients')]")
	public WebElement ENROLLEDCLIENTS;

	@FindBy(xpath = "//company-overview/div/div/div[contains(.,'SharedService')]")
	public WebElement SHAREDSERVICETEXT;

	@FindBy(xpath = "//company-overview/div/div/div[contains(.,'Enrolled')]")
	public WebElement ENROLLEDTEXT;

	@FindBy(xpath = "//label[contains(.,' Please upload client order form*')]/../label[contains(.,'Upload')]")
	public WebElement CLIENTFORMUPLOAD;

	@FindBy(xpath = "//fileupload/div[contains(.,'Please select a client order form.')]")
	public WebElement ORDERFORMERROR;

	@FindBy(xpath = "//fileupload/div[contains(.,'Only pdf, jpg, png, bmp, doc, docx files are allowed')]")
	public WebElement FILEFORMATERROR;

	@FindBy(xpath = "//button[contains(.,'Upload File')]")
	public WebElement UPLOADFILE;

	@FindBy(xpath = "//fileupload/div[contains(.,'Your file has been successfully uploaded!')]")
	public WebElement UPLOADSUCCESSMESSAGE;

	@FindBy(xpath = "//label[contains(.,'Search by Client Name')]/../input[@ng-model='referred.searchFields.textSearch.value']")
	public WebElement SEARCHREFCLIENT;

	@FindBy(xpath = "//span[contains(.,'Customer Number')]")
	public WebElement CUSTOMERNUMBER;
	
	@FindBy(xpath="//table[@sort-table='referred.table.columns']/thead/tr/th/div/span[contains(.,'Customer Number')]")
	public WebElement REFERREDCUSTOMERNUMBER;
	
	@FindBy(xpath="//table[@sort-table='shared.table.columns']/thead/tr/th/div/span[contains(.,'Customer Number')]")
	public WebElement SHAREDCUSTOMERNUMBER;

    @FindBy(xpath = "//p[contains(.,'Client Order form')]")
	public WebElement CLIENTORDERFORMERROR;
    
    @FindBy(xpath = "//fileupload/div[contains(.,'Please upload a client order form.')]")
    public WebElement ORDER_FORMERROR;
    
    @FindBy(name="includeRateFile")
    public WebElement RATEFILE;
    
    @FindBy(name="includeRealTimeRateService")
    public WebElement RTRS;
    
    @FindBy(name="includeTaxabilityImpactReport")
    public WebElement TIR;
    
    @FindBy(xpath="//div/div/h2[contains(.,'Enroll new client')]/../button[contains(.,'Order Form and Pricing Info')]")
    public WebElement ENROLLEDPRICINGINFO;
    
    @FindBy(xpath="//div/div/h2[contains(.,'Shared Services')]/../button[contains(.,'Order Form and Pricing Info')]")
    public WebElement SHAREDPRICINGINFO;
    
}
