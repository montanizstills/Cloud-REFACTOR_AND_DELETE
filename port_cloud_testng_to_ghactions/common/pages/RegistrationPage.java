/*###################################################################################################
Name: RegistrationPage.java 
Description: VertexSMB Add user registration Page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/
package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class RegistrationPage {
	public final String R_ACHSTATENAMES_XPATH = "//select[@id='bill_to_address_state']";
	public final String IAGREETC2_CAPTCHA_XPATH = "//fieldset[3]/div/label/input[@type='checkbox']";
	public final String BILLINGZIP_XPATH = "//input[@name='bill_to_address_postal_code']";
	public final String ROUTINGNUMBER_XPATH = "//input[@name='echeck_routing_number']";
	public final String BANKNUMBER_XPATH = "//input[@name='echeck_account_number']";
	public final String ERRORMSGADD1_XPATH = "//label[text()='Please enter no more than 60 characters.']";
	public final String ERRORMSGACCOUNT_XPATH = "//label[text()='Please enter no more than 17 characters.']";
	public final String ERRORMSGROUTING_XPATH = "//label[text()='Please enter no more than 9 characters.']";
	public final String BILLINGADDRESS1_XPATH = "//input[@name='bill_to_address_line1']";
	public final String ERRORMSGCITY_XPATH = "//label[text()='Please enter no more than 50 characters.']";
	public final String BILLINGCITY_XPATH = "//input[@name='bill_to_address_city']";
	public final String ERRORMSGCSV_XPATH = "//label[text()='Please enter no more than 4 characters.']";
	public final String VARCOUNTRY_XPATH = "//select[@id='countryDropDown']";
	public final String NEWUSERCOUNTRY_XPATH = "//select[@name='Country']";
	public final String ERRORMSGCC_XPATH = "//span[@ng-message='creditCard'][contains(.,'Please enter a valid credit card number.')]";
	public final String CVN_XPATH = "//input[@id='card_cvn']";
	
	@FindBy(xpath = "//div[text()='Your password must be 8-128 characters in length and must contain at least one uppercase and lowercase letter, number, and special character (!@#$%^&*<>?).']")
	public WebElement PSWDVERIFYTXT;
	
	@FindBy(xpath = "//a[@title='Invite Users']")
	public WebElement INVITEUSER;
	
	@FindBy(xpath = "//a[contains(text(),'Invite User')]")
	public WebElement ADMININVITE;
	
	@FindBy(xpath = "//input[@type='text']")
	public WebElement INVITEUSERSSEARCH;
	
	@FindBy(xpath = "//a[contains(text(),'Export')]")
	public WebElement EXPORT;
	
	@FindBy(xpath = "//select[@name='Role']")
	public WebElement ROLE;
	
	@FindBy(xpath = "//select[@name='resellerName']")
	public WebElement VARCPA;
	/*
	 * @FindBy(xpath= "//select[@name='ClientId']") public WebElement VARNAMES;
	 */
	
	@FindBy(xpath = "//input[@name='ClientName']")
	public WebElement CLIENT;
	/*
	 * @FindBy(xpath="//input[@id='user-name']") public WebElement USERNAME;
	 */
	
	@FindBy(xpath = "//div[2]/div/div/div[1]/div[2]/div/button[@class='btn btn-primary' and @disabled='']")
	public WebElement NEXTBTNDISABLE;
	
	@FindBy(xpath = "//div/div/div[2]/div[2]/div/button[@class='btn btn-primary' and @disabled='']")
	public WebElement NEXTBTNADDINFODISABLE;
	
	@FindBy(xpath = "//button[contains(text(),'Continue') and @disabled='']")
	public WebElement CONTINUEBTNSUBSCRIPTIONDISABLE;
	
	@FindBy(xpath = "//button[contains(text(),'Submit Billing Information & Finish') and @disabled='']")
	public WebElement SUBMITBILLINGINFOFINISHDISABLE;
	
	@FindBy(xpath = "//button[contains(.,'Submit Billing Information & Finish')]")
	public WebElement SUBMITBILLINGINFOFINISHENABLE;
	
	@FindBy(xpath = "//input[@id='Username']")
	public WebElement ADMINSUSERNAME;
	
	@FindBy(xpath = "//div[@class='accordion-toggle active completed']")
	public WebElement BASICINFORMATION;
	
	@FindBy(xpath = "//input[@id='firstName']")
	public WebElement FIRSTNAME;
	
	@FindBy(xpath = "//input[@id='FirstName']")
	public WebElement ADMINFIRSTNAME;
	
	@FindBy(xpath = "//input[@id='LastName']")
	public WebElement ADMINLASTNAME;
	
	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement LASTNAME;
	
	@FindBy(xpath = "//input[@name='Email']")
	public WebElement EMAIL;
	
	@FindBy(xpath = "//input[@id='emailAddress']")
	public WebElement EMAILEDIT;
	
	@FindBy(xpath = "//input[@id='EmailAddress']")
	public WebElement EMAILEDITADMIN;
	
	@FindBy(xpath = "//input[@id='first-name']")
	public WebElement FIRSTNAMEEDIT;
	
	@FindBy(xpath = "//input[@id='last-name']")
	public WebElement LASTNAMEEDIT;
	
	@FindBy(xpath = "//input[@name='Email']")
	public WebElement INVALIDEMAIL;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement CONTINUE;
	
	@FindBy(id = "HasRateFile")
	public WebElement RATEFILE;
	
	@FindBy(xpath = "//button[@id='btnInvite']")
	public WebElement SNDIVTE;
	
	@FindBy(xpath = "//input[@id='clientAddress1']")
	public WebElement ADDRESS1;
	
	@FindBy(xpath = "//input[@id='clientAddress2']")
	public WebElement ADDRESS2;
	
	@FindBy(xpath = "//input[@id='tbAddress1']")
	public WebElement VARADDRESS1;
	
	@FindBy(xpath = "//input[@id='tbAddress2']")
	public WebElement VARADDRESS2;
	
	@FindBy(xpath = "//select[@id='countryDropDown']")
	public WebElement VARCOUNTRY;
	
	@FindBy(xpath = "//input[@id='clientCity']")
	public WebElement CITY;
	
	@FindBy(xpath = "//select[@id='clientState']")
	public WebElement STATE;
	
	@FindBy(xpath = "//input[@id='clientZip']")
	public WebElement ZIP;
	
	@FindBy(xpath = "//input[@id='tbCity']")
	public WebElement VARCITY;
	
	@FindBy(xpath = "//select[@id='ddlState']")
	public WebElement VARSTATE;
	
	@FindBy(xpath = "//input[@id='tbZipCpde']")
	public WebElement VARZIP;
	
	@FindBy(xpath = "//select[@name='subscriptionId']")
	public WebElement VARSUBSCRIPTION1;
	
	@FindBy(xpath = "//select[@name='ProfessionalSubscriptionFeatures']")
	public WebElement VARSUBSCRIPTION;
	
	@FindBy(xpath = "//label[contains(.,'Additional Features :')]/../div/label/input[@id='HasRateFile']")
	public WebElement VARRATEFILE;
	
	@FindBy(xpath = "//label[contains(.,'Customer Negotiated Contract :')]/../div/input[@id='HasNegotiatedContract']")
	public WebElement NEGOCONTRACT;
	
	@FindBy(xpath = "//label[contains(.,'Customer Negotiated Contract :')]/../div/input[@id='HasNegotiatedContract']")
	public WebElement ECWNEGOCONTRACT;
	
	@FindBy(xpath = "//span[text()='This field is required.']/../../../div/input[@id='txtNegotiatedContractNumber']")
	public WebElement NEGOFLDREQ;
	
	@FindBy(xpath = "//input[@id='txtNegotiatedContractNumber']")
	public WebElement NEGOCONTRACTNUM;
	
	@FindBy(css = "label.file-input-wrapper.btn.btn-primary.upload-file")
	public WebElement BROWSE;
	
	@FindBy(xpath = "//input[@id='TaxabilityImpactReport']")
	public WebElement TAXIMPACTREPORT;
	
	@FindBy(xpath = "//input[@id='HasPayrollCalculation']")
	public WebElement PayrollCalculation;
	
	@FindBy(xpath="//input[@id='HasPayrollProductionEnvironment']")
	public WebElement PAYROLLPRODUCTION;
	
	@FindBy(xpath="//input[@id='HasPayrollTestEnvironment']")
	public WebElement PAYROLLTEST;
	
	@FindBy(xpath = "//input[@id='HasPayrollAddressCleansing']")
	public WebElement PayrollAddressCleansing;
	
	@FindBy(xpath = "//input[@id='RetailTaxExtract']")
	public WebElement RETAILTAXEXTRACT;
	
	@FindBy(xpath = "//input[@id='HasTaxabilityImpactReport']")
	public WebElement VARTAXIMPACTREPORT;
	
	@FindBy(xpath = "//input[@id='password']/../div[contains(text(),'Your password must be 8-40 characters in length and must contain at least one uppercase letter, number, and special character (!@#$%^&*<>?).')]")
	public WebElement PWDSTRENGTH;
	
	@FindBy(xpath = "//input[@id='password']")
	public WebElement PASSWORD;
	/*
	 * @FindBy(xpath= "//input[@id='password-confirm']") public WebElement
	 * PASSWORDCONFIRM;
	 */
	
	@FindBy(xpath = "//input[@id='AcceptedTerms']")
	public WebElement IAGREETC1;
	
	@FindBy(xpath = "//input[@id='Registration_HasAgreedTOS']")
	public WebElement IAGREETC_CAPTCHA;
	
	@FindBy(xpath = "//fieldset[3]/div/label/input[@type='checkbox']")
	public WebElement IAGREETC2_CAPTCHA;
	/*
	 * @FindBy(xpath= "//div[@id='basic-information']/div/fieldset/div/label/input")
	 * public WebElement IAGREE_BASIC;
	 */
	
	@FindBy(xpath = "//button[@type='button']")
	public WebElement NEXTSTEP;
	
	@FindBy(xpath = "//div[@id='additional-information']/div/button")
	public WebElement NEXTSTEP1;
	
	@FindBy(xpath = "//input[@id='transactionsPerMonth']")
	public WebElement TRANSACTIONPERMONTH;
	
	@FindBy(xpath = "//input[@id='transactionsPerMonth']/../span[contains(text(),'This field is required.')]")
	public WebElement TRANSPERMONTHERROR;
	
	@FindBy(xpath = "//div[2]/div/select")
	public WebElement PRIMARYCONNECTOR;
	
	@FindBy(xpath = "//input[@id='add-erp-17']")
	public WebElement ADDITIONALCONNECTOR;
	
	@FindBy(xpath = "//select[@id='user-type']")
	public WebElement USERTYPE;
	
	@FindBy(xpath = "//select[@id='lead-source']")
	public WebElement SOURCELEAD;
	
	@FindBy(xpath = "//select[@id='industry-type']")
	public WebElement PRIMARYINDUSTRY;
	
	@FindBy(xpath = "//div[@class='row-fluid subscription-options']/div/div/label/h4[contains(.,'Standard')]")
	public WebElement STRDSUBSCRIPTION;
	
	@FindBy(xpath = "//input[@id='include-rate-file']")
	public WebElement RATEFILECHECKBOX;
	
	@FindBy(xpath = "//input[@id='include-online-rate-file-calculation']")
	public WebElement REALTIMERATESERVICE;
	/*
	 * @FindBy(xpath=
	 * "//div[@class='row-fluid subscription-options']/div/div/label/h4[contains(.,'Professional']"
	 * ) public WebElement PROFESSIONALSUBSCRIPTION;
	 */
	
	@FindBy(xpath = "//div[@class='row-fluid subscription-options']/div/div/label/h4[contains(.,'Premium')]")
	public WebElement PREMIUMSUBSCRIPTION;
	
	@FindBy(xpath = "//div[@id='subscription']/div/fieldset/div/div/div/label/h4[text()='Rate File']")
	public WebElement RATEFILEONLYSUBSCRIPTION;
	
	@FindBy(xpath = "//input[@id='returns-only']")
	public WebElement RETURNSONLY;
	
	@FindBy(xpath = "//input[@id='calc-and-returns']")
	public WebElement TAXCALANDRETURNS;
	
	@FindBy(xpath = "//span/img[@alt='Guided Service']")
	public WebElement GUIDEDSERVICE;
	
	@FindBy(xpath = "//span/img[@alt='Self Service']")
	public WebElement SELFSERVICE;
	
	@FindBy(xpath = "//div[@id='implementationAssistance']/div/fieldset/div/div[1]/label/span/img")
	public WebElement FULLSERVICE;
	
	@FindBy(xpath = "//select[@id='implementation-assister']")
	public WebElement IMPLEMENTATIONASSISTOR;
	
	@FindBy(xpath = "//input[@name='card_number']")
	public WebElement CREDITCARD;
	
	@FindBy(xpath = "//select[@id='expiry_month']")
	public WebElement EXPMONTH;
	
	@FindBy(xpath = "//button[contains(.,' SUBMIT')]")
	public WebElement Submit;
	
	@FindBys({ @FindBy(xpath = "//select[@id='expiry_month']/option"), })
	public List<WebElement> EXPMONTH_VALUES;
	
	@FindBy(xpath = "//select[@id='expire-year']")
	public WebElement EXPYEAR;
	
	@FindBy(xpath = "//input[@id='card_cvn']")
	public WebElement CVN;
	
	@FindBy(xpath = "//input[@id='bill_to_address_line1']")
	public WebElement BILLINGADDRESS1;
	
	@FindBy(xpath = "//input[@name='bill_to_address_city']")
	public WebElement BILLINGCITY;
	
	@FindBy(xpath = "//select[@id='bill_to_address_state']")
	public WebElement BILLINGSTATE;
	
	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	public WebElement DASHBOARDLINK;
	
	@FindBys({ @FindBy(xpath = "//select[@id='bill_to_address_state']/option") })
	public List<WebElement> BILLINGSTATES;
	
	@FindBy(xpath = "//input[@name='bill_to_address_postal_code']")
	public WebElement BILLINGZIP;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement SAVE;
	
	@FindBy(xpath = "//input[contains(@id, 'Subscription_HasRateFile') and contains(@value, 'True')]")
	public WebElement RATEFILEYES;
	
	@FindBy(xpath = "//input[contains(@id, 'Subscription_HasRateFile') and contains(@value, 'False')]")
	public WebElement RATEFILENO;
	
	@FindBy(xpath = "//input[@id='s2id_autogen1']")
	public WebElement SUBSCRIPTIONSTATE;
	
	@FindBy(xpath = "//ul[@id='billing-tabs']/li[2]/a")
	public WebElement ACHPAYMENT;
	
	@FindBy(xpath = "//ul[@id='billing-tabs']/li[1]/a")
	public WebElement CREDITCARDPAYMENT;
	
	@FindBy(xpath = "//select[@name='echeck_account_type']")
	public WebElement ACHACCOUNTTYEP;
	
	@FindBy(xpath = "//input[@name='echeck_account_number']")
	public WebElement ACHBANKNUMBER;
	
	@FindBy(xpath = "//input[@name='echeck_routing_number']")
	public WebElement ACHROUTINGNUMBER;
	
	@FindBy(xpath = "//input[@name='company_tax_id']")
	public WebElement ACHCOMPANYTAXID;
	
	@FindBy(xpath = "//a[contains(@href, '#account')]")
	public WebElement ACCOUNTICONLINK;
	
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	public WebElement LOGOUT;
	/*
	 * @FindBy(xpath= "//input[@id='txtPhone']") public WebElement PHONE;
	 */
	
	@FindBy(xpath = "//input[@id='userPhone']")
	public WebElement BASICPHONE;
	
	@FindBy(xpath = "//input[@name='account-terms']")
	public WebElement TERMSOFSERVICE;
	
	@FindBy(xpath = "//input[@id='tbPhone']")
	public WebElement VARBASICPHONE;
	
	@FindBy(xpath = "//div/fieldset[3]/div/label/input")
	/*
	 * public WebElement IAGREETC2;
	 * 
	 * @FindBy(xpath=
	 * "//div[@id='implementationAssistance']/div/fieldset[2]/div/label/input")
	 */
	public WebElement IAGREETC3;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement SUBMIT;
	/*
	 * @FindBy(xpath= "//button[contains(.,'Continue to Billing Information')]")
	 * public WebElement NEXTSTEP2;
	 */
	
	@FindBy(xpath = "//div[@id='additional-information']/div/button")
	public WebElement NEXTSTEP3;
	
	@FindBy(xpath = "//div[@id='implementationAssistance']/div/button")
	public WebElement CONTINUE1;
	
	@FindBy(xpath = "//div[@class='content-col']/div[contains(.,' Something went wrong processing your request. Reenter your information and try again.')]")
	public WebElement ERRORMSG;
	
	@FindBy(xpath = "//select[@id='Registration_ParentClientId']")
	public WebElement R_VALUEADDRESELLER;
	
	@FindBys({ @FindBy(xpath = "//select[@id='Registration_ParentClientId']/option"), })
	public List<WebElement> R_VALUEADDRESELLERVALUES;
	
	@FindBy(xpath = "//input[@id='Registration_ClientName']")
	public WebElement R_CLIENT;
	
	@FindBy(xpath = "//input[@id='Registration_FirstName']")
	public WebElement R_FIRSTNAME;
	
	@FindBy(xpath = "//input[@id='Registration_LastName']")
	public WebElement R_LASTNAME;
	
	@FindBy(xpath = "//input[@id='emailId']")
	public WebElement R_EMAIL;
	
	@FindBy(xpath = "//div[@id='implementationAssistance']/div/fieldset/div/div[1]/label/span/img")
	public WebElement R_GUIDEDSERVICE;
	
	@FindBy(xpath = "//div[@id='implementationAssistance']/div/fieldset/div/div[2]/label/span/img")
	public WebElement R_SELFSERVICE;
	
	@FindBy(xpath = "//select[@id='bill_to_address_state']")
	public WebElement R_ACHSTATENAMES;
	
	@FindBy(xpath = "//input[@id='Password']")
	public WebElement VARPASSWORD;
	
	@FindBy(xpath = "//input[@id='PasswordConfirm']")
	public WebElement VARPASSWORDCONFIRM;
	
	@FindBy(xpath = "//button[text()='Complete Registration']")
	public WebElement VARCOMPLETE;
	
	@FindBy(xpath = "//a[contains(.,'INVITE CUSTOMER')]")
	public WebElement VARINVITECUSTOMER;
	
	@FindBy(xpath = "//input[contains(@data-bind, 'checked: includeRateFileRadio') and contains(@value, 'true')]")
	public WebElement VARRATEFILEYES;
	
	@FindBy(xpath = "//span[contains(text(),'My Customers')]")
	public WebElement MYCUSTOMERS;
	
	@FindBy(xpath = "//span[text()='Manage Merchants']")
	public WebElement MANAGEMERCHANTS;
	
	@FindBy(xpath = "//div/label[contains(text(),'How many transactions do you process a month? (Estimate)')]")
	public WebElement ADDINFOHOWMANYTRANSLABEL;
	
	@FindBy(xpath = "//div/label[contains(text(),'Select Primary Connector')]")
	public WebElement PRIMARYCONNECTORLABEL;
	
	@FindBy(xpath = "//div/select[@id='erp-1']")
	public WebElement VARPRIMARYCONNECTOR;
	
	@FindBy(xpath = "//div/input[@name='add-erp']")
	public WebElement VARPRIMARYCONNECTOROTHER;
	
	@FindBy(xpath = "//div/a[@title='Add an Additional Connector']")
	public WebElement ADDITIONALCONNECTORLINK;
	
	@FindBy(xpath = "//div/select[@id='erp-4']")
	public WebElement VARADDITIONALCONNECTOR;
	
	@FindBy(xpath = "//div/label[contains(text(),'What is your role?')]")
	public WebElement WHATROLELABEL;
	
	@FindBy(xpath = "//div/select[@id='user-type']")
	public WebElement VARSELECTROLE;
	
	@FindBy(xpath = "//div/label[contains(text(),'How did you hear about us?')]")
	public WebElement HOWDIDHEARLABEL;
	
	@FindBy(xpath = "//div/select[@id='lead-source']")
	public WebElement VARHEARSOURCE;
	
	@FindBy(xpath = "//div/label[contains(text(),'What is your primary industry?')]")
	public WebElement WHATINDUSTRYLABEL;
	
	@FindBy(xpath = "//div/select[@id='industry-type']")
	public WebElement VARINDUSTRYTYPE;
	
	@FindBy(xpath = "//div[@id='recaptcha_image']")
	public WebElement CAPTCHAIMAGE;
	
	@FindBy(xpath = "//input[@id='recaptcha_response_field']")
	public WebElement CAPTCHATEXTBOX;
	
	@FindBy(xpath = "//a[@id='recaptcha_reload_btn']")
	public WebElement CAPTCHARELOAD;
	
	@FindBy(xpath = "//a[@id='recaptcha_switch_audio_btn']")
	public WebElement CAPTCHAAUDIO;
	
	@FindBy(xpath = "//a[@id='recaptcha_whatsthis_btn']")
	public WebElement CAPTCHAHELP;
	
	@FindBy(xpath = "//img[@id='recaptcha_logo']")
	public WebElement CAPTCHALOGO;
	
	@FindBy(xpath = "//span[@id='recaptcha_privacy']")
	public WebElement CAPTCHAPRIVACY;
	
	@FindBy(xpath = "//div[@class='validation-summary-errors alert alert-error']/ul/li[text()='Incorrect captcha answer.']")
	public WebElement INVALIDCAPTCHA;
	
	@FindBy(xpath = "//span[text()='reCAPTCHA']")
	public WebElement CAPTCHALOGO_HELP;
	
	@FindBy(xpath = "//h1[text()='Complete Your Registration']")
	public WebElement HEADING;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/div[contains(.,'Basic Information')]")
	public WebElement BASICINFO;
	
	@FindBy(xpath = ".//*[@id='accordion-main']/div[2]/div[1]/a")
	public WebElement ADDITIONALINFO;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/div[contains(.,'Subscription')]")
	public WebElement SUBSCRIPTIONINFO;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#implementationAssistance']")
	public WebElement IMPLEMENTINFO;
	
	@FindBy(xpath = "//h5[text()='Basic Information']/../a[text()='Edit']")
	public WebElement BASICEDIT;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#basic-information']/i[@class='icon icon-ok']")
	public WebElement BASIC_OKICON;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#additional-information']/i[@class='icon icon-ok']")
	public WebElement ADDITIONAL_OKICON;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#subscription']/i[@class='icon icon-ok']")
	public WebElement SUBS_OKICON;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#basic-information' and @class='accordion-toggle active']")
	public WebElement BASICINFOOPEN;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#basic-information' and @class='accordion-toggle collapsed']")
	public WebElement BASICINFOCLOSED;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#basic-information' and @class='accordion-toggle completed success']")
	public WebElement BASICINFOCLOSED1;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#additional-information' and @class='accordion-toggle active']")
	public WebElement ADDITIONALOPEN;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#additional-information' and @class='accordion-toggle collapsed']")
	public WebElement ADDITIONALCLOSED;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#additional-information' and @class='accordion-toggle completed success']")
	public WebElement ADDITIONALCLOSED1;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#subscription' and @class='accordion-toggle active']")
	public WebElement SUBSOPEN;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#subscription' and @class='accordion-toggle collapsed']")
	public WebElement SUBSCLOSED;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#subscription' and @class='accordion-toggle completed success']")
	public WebElement SUBSCLOSED1;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#implementationAssistance' and @class='accordion-toggle active']")
	public WebElement IMPLEMENTOPEN;
	
	@FindBy(xpath = "//div[@class='accordion-heading']/a[@href='#implementationAssistance' and @class='accordion-toggle collapsed']")
	public WebElement IMPLEMENTCLOSED;
	
	@FindBy(xpath = "//div/select[@id='erp-2']")
	public WebElement ADDCONNECTORDROP;
	
	@FindBy(xpath = "//label[@class='subscription-option selector-widget control-label selected']/span/strong[Contains(text(),'Professional')]")
	public WebElement PROFSUBS;
	
	@FindBy(xpath = "//label[@class='subscription-option selector-widget control-label selected']/span/strong[Contains(text(),'Free Trial')]")
	public WebElement FREETRIALSUBS;
	
	@FindBy(xpath = "//span[Contains(text(),'Gain limited access to tax calculation features.')]")
	public WebElement FREE_DESC;
	
	@FindBy(xpath = "//span[Contains(text(),'Configure, perform tax calculations and print signature ready returns.')]")
	public WebElement PROF_DESC;
	
	@FindBy(xpath = "//span[Contains(text(),'Download a file containing tax rate information for one or more U.S. states.')]")
	public WebElement RATE_DESC;
	
	@FindBy(xpath = "//label[@class='control-group implementation-assistance-type-tos has-error']")
	public WebElement SERVAGREE_CHECKDEFAULT;
	
	@FindBy(xpath = "//label[@class='subscription-option selector-widget control-label selected']/span/strong[Contains(text(),'Self Service')]")
	public WebElement SELFSERV_SELECT;
	
	@FindBy(xpath = "//img[@alt='Self Service']")
	public WebElement SELFSERV_IMG;
	
	@FindBy(xpath = "//img[@alt='Guided Service']")
	public WebElement GUIDESERV_IMG;
	
	@FindBy(xpath = "//span[@data-bind='text: clientName']")
	public WebElement INSCRCLIENT;
	
	@FindBy(xpath = "//span[@data-bind='text: firstName']")
	public WebElement INSCRFNAME;
	
	@FindBy(xpath = "//span[@data-bind='    text: lastName']")
	public WebElement INSCRLNAME;
	
	@FindBy(xpath = "//span[@data-bind='text: email']")
	public WebElement INSCREMAIL;
	
	@FindBy(xpath = "//span[@data-bind='text: transactionsPerMonth']")
	public WebElement INSCRTRANS;
	
	@FindBy(xpath = "//span[@data-bind='text: name']")
	public WebElement INSCRSUBSCR;
	
	@FindBy(xpath = "//span[@data-bind='text: rateFileStatus']")
	public WebElement INSCRRTFILE;
	
	@FindBy(xpath = "//div[contains(.,'Service: ')]/span")
	public WebElement INSCRSERVICE;
	
	@FindBy(xpath = "//h1[text()='Enter Your Billing Information']")
	public WebElement BILLINGPAGETITLE;
	
	@FindBy(xpath = "//a[@class='btn pull-left']")
	public WebElement BILLINGPAGEBACK;
	
	@FindBy(xpath = "//button[@class='btn btn-primary ng-binding']")
	public WebElement SUBMITBILLINGINFOBUTTON;
	
	@FindBy(xpath = "//h2[text()='Credit Card']")
	public WebElement CREDITCARDTITLE;
	
	@FindBy(xpath = "//label[contains(.,'Credit Card Number:')]")
	public WebElement CREDITCARDNUMBERLABEL;
	
	@FindBy(xpath = "//label[contains(.,'Expiration Date:')]")
	public WebElement EXPIRATIONDATELABEL;
	
	@FindBy(xpath = "//label[contains(.,'First Name:')]")
	public WebElement FIRSTNAMELABEL;
	
	@FindBy(xpath = "//label[contains(.,'Last Name:')]")
	public WebElement LASTNAMELABEL;
	
	@FindBy(xpath = "//label[contains(.,'CSV:')]")
	public WebElement CSVLABEL;
	
	@FindBy(xpath = "//label[contains(.,'Street Address 1:')]")
	public WebElement ADDRESS1LABEL;
	
	@FindBy(xpath = "//label[contains(.,'Street Address 2:')]")
	public WebElement ADDRESS2LABEL;
	
	@FindBy(xpath = "//label[contains(.,'City:')]")
	public WebElement CITYLABEL;
	
	@FindBy(xpath = "//label[contains(.,'State:')]")
	public WebElement STATELABEL;
	
	@FindBy(xpath = "//label[contains(.,'Zip/Postal Code:')]")
	public WebElement ZIPLABEL;
	
	@FindBy(xpath = "//label[contains(.,'Bank Account Number:')]")
	public WebElement BANKNUMBERLABEL;
	
	@FindBy(xpath = "//label[text()='Routing Number:']")
	public WebElement ROUTINGNUMBERLABEL;
	
	@FindBy(xpath = "//label[text()='Account Type:']")
	public WebElement ACCOUNTTYPELABEL;
	
	@FindBy(xpath = "//input[@name='echeck_account_number']")
	public WebElement BANKNUMBER;
	
	@FindBy(xpath = "//input[@name='echeck_routing_number']")
	public WebElement ROUTINGNUMBER;
	
	@FindBy(xpath = "//i[@class='icon-info icon-small section-heading']")
	public WebElement IICONXPATH;
	
	@FindBy(xpath = "//label[text()='This field is required.']")
	public WebElement ERRORMESSAGE;
	
	@FindBy(xpath = "//span/label[@for='echeck_account_number']")
	public WebElement ERRORBANKNUM;
	
	@FindBy(xpath = "//span/label[@for='echeck_routing_number']")
	public WebElement ERRORROUTINGNUM;
	
	@FindBy(xpath = "//div[text()='The three or four digit code on the back of your credit card.']")
	public WebElement IICONTEXT;
	
	@FindBy(xpath = "//span/strong[text()='ACH']")
	public WebElement ACH;
	
	@FindBy(xpath = "//span/strong[text()='Credit Card']")
	public WebElement CREDITCARDBILLINGPAGE;
	
	@FindBy(xpath = "//input[@id='client']")
	public WebElement BASICCOMPANYNAME;
	
	@FindBy(xpath = "//input[@id='first-name']")
	public WebElement BASICFIRSTNAME;
	
	@FindBy(xpath = "//input[@id='last-name']")
	public WebElement BASICLASTNAME;
	
	@FindBy(xpath = "//input[@id='email']")
	public WebElement BASICEMAIL;
	
	@FindBy(xpath = "//a[text()='services agreement']")
	public WebElement SERVICEAGRMNT;
	
	@FindBy(xpath = "//button[text()='Close']")
	public WebElement SERVICECLOSE;
	
	@FindBy(xpath = "//button[@class='close']")
	public WebElement SERVICECLOSEICON;
	
	@FindBy(xpath = "//h3[text()='Services Agreement']")
	public WebElement SERVICEHEADING;
	
	@FindBy(xpath = "//div[@class='registration footer']/p[contains(.,'© 2020 Vertex, Inc. All rights reserved.')]")
	public WebElement COPYRIGHTFOOTER;
	
	@FindBy(xpath = "//div[contains(.,'Your billing information has been updated!')]")
	public WebElement SUCCESS;
	
	@FindBy(xpath = "//h1")
	public WebElement REGISTRATIONPAGEHEADER;
	
	@FindBy(xpath = "//button[@class='btn btn-disable']")
	public WebElement CONTINUEBUTTONDISABLE;
	
	@FindBy(xpath = "//h1[contains(text(),'Complete Your Registration')]")
	public WebElement COMPLETEREGISTRATIONPAGEHEADER;
	
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	public WebElement BUTTON;
	
	@FindBy(xpath = "//legend[contains(text(),'select one:')]")
	public WebElement SELECTONE;
	
	@FindBy(xpath = "//button[@disabled='']")
	public WebElement SUBSCRIPTIONCONTINUE;
	
	@FindBy(xpath = "//span[3]/span")
	public WebElement LONGEMAILID;
	
	@FindBy(xpath = "//input[@name='__RequestVerificationToken']")
	public WebElement REQUESTVERIFICATIONTOKEN;
	
	@FindBy(xpath = "//span")
	public WebElement BADTOKEN_ERROR;
	
	@FindBy(xpath = "//input[@id='client']")
	public WebElement BASICINFOCLIENT;
	
	@FindBy(xpath = "//input[@id='first-name']")
	public WebElement BASICINFOFIRSTNAME;
	
	@FindBy(xpath = "//input[@id='last-name']")
	public WebElement BASICINFOLASTNAME;
	
	@FindBy(xpath = "//div[1]/div[2]/div/button[contains(text(),'Next Step')]")
	public WebElement NEXTSTEPBUTTON;
	
	@FindBy(xpath = "//span[@ng-message='creditCard'][contains(.,'Please enter a valid credit card number.')]")
	public WebElement ERRORMSGCC;
	
	@FindBy(xpath = "//label[text()='Please enter no more than 4 characters.']")
	public WebElement ERRORMSGCSV;
	
	@FindBy(xpath = "//label[text()='Please enter no more than 60 characters.']")
	public WebElement ERRORMSGADD1;
	
	@FindBy(xpath = "//label[text()='Please enter no more than 50 characters.']")
	public WebElement ERRORMSGCITY;
	
	@FindBy(xpath = "//label[text()='Please enter no more than 17 characters.']")
	public WebElement ERRORMSGACCOUNT;
	
	@FindBy(xpath = "//label[text()='Please enter no more than 9 characters.']")
	public WebElement ERRORMSGROUTING;
	
	@FindBy(xpath = "//span[contains(text(),'An error has occurred.')]")
	public WebElement ANTIFOREIGNTOKENERROR;
	
	@FindBy(xpath = "//input[@id='txtStreetAddress1']")
	public WebElement NEWUSERSTREETADDRESS1;
	
	@FindBy(xpath = "//input[@id='txtStreetAddress2']")
	public WebElement NEWUSERSTREETADDRESS2;
	
	@FindBy(xpath = "//input[@id='txtCity']")
	public WebElement NEWUSERCITY;
	
	@FindBy(xpath = "//select[@name='StateCode']")
	public WebElement NEWUSERSTATE;
	
	@FindBy(xpath = "//input[@name='state']")
	public WebElement FOREIGNSTATE;
	
	@FindBy(xpath = "//input[@id='txtZip']")
	public WebElement NEWUSERZIP;
	
	@FindBy(name = "ProfessionalSubscriptionFeatures")
	public WebElement NEWUSERSUBSCRIPTIONOPTION;
	
	@FindBy(xpath = "//select[@id='clientCountry']")
	public WebElement CAPTCHAUSERCOUNTRY;
	
	@FindBy(xpath = "//p[3]/a")
	public WebElement USERLINK;
	
	@FindBy(xpath = "//div[contains(text(),'You must accept the terms of service')]")
	public WebElement ERRORCHECKBOX;
	
	@FindBy(xpath = "//select[@id='bill_to_address_country']")
	public WebElement BILLINGCOUNTRYDDRPDOWN;
	
	@FindBy(xpath = "//label[contains(.,'Username:*')]/../div/span[contains(.,'This field is required.')]")
	public WebElement USERNAMEERROR;
	
	@FindBy(xpath = "//span[contains(.,'This field is required.')]/../../label[contains(.,'Password:*')]")
	public WebElement PASSWORDERROR;
	
	@FindBy(xpath = "//div[contains(.,'You must accept the terms of service')]")
	public WebElement TERMSOFSERVICEERROR;
	
	@FindBy(xpath = "//div[contains(.,'You must accept the terms of service')]")
	public WebElement SERVICEAGRMTERROR;
	
	@FindBy(xpath = "//label[contains(.,'Confirm Password:*')]/../div/span[contains(.,'This field is required.')]")
	public WebElement CONFIRMPASSWORDERROR;
	
	@FindBy(xpath = "//div/h2[contains(.,'Billing')]/../a[contains(.,'Update')]")
	public WebElement UPDATEBILLING;
	
	@FindBy(xpath = ".//*[@id='UserName']")
	public WebElement LOGINUSERNAME;
	
	@FindBy(xpath = "//ul/li[contains(text(),'already exists')]")
	public WebElement USERALREADYEXISTS;
	
	@FindBy(name = "resellerName")
	public WebElement VARNAMES;
	
	@FindBy(name = "Country")
	public WebElement NEWUSERCOUNTRY;
	
	@FindBy(xpath = "//input[@id='userPhone']")
	public WebElement PHONE;
	
	@FindBy(xpath = "//input[@id='UserPhone']")
	public WebElement ADMINPHONE;

	@FindBy(name = "subscriptionId")
	public WebElement NEWUSERSUBSCRIPTION;

	@FindBy(xpath = "//select[contains(@name,'SubscriptionFeatures')]")
	public WebElement SubscriptionFeatures;

	@FindBy(xpath = "//input[@id='user-name']")
	public WebElement USERNAME;
	
	@FindBy(xpath = "//input[@id='confirm-password']")
	public WebElement PASSWORDCONFIRM;
	
	@FindBy(id = "agreedToToS")
	public WebElement IAGREE_BASIC;
	
	@FindBy(xpath = "//button[contains(text(),'Continue to Billing Information')]")
	public WebElement NEXTSTEP2;
	
	@FindBy(id = "agreedToSA")
	public WebElement IAGREETC2;
	
	@FindBy(xpath = "//input[@id='agreedToSA'][@disabled='disabled']")
	public WebElement ServiceAgreeDisabled;
	
	@FindBy(xpath = "//div[@class='row-fluid subscription-options']/div/div/label/h4[contains(.,'Professional')]")
	public WebElement PROFESSIONALSUBSCRIPTION;
	public final String NPROGRESSBUSY_XPATH = "html[@class='nprogress-busy']";
	
	@FindBy(xpath = "//input[contains(@value,'Send Invite')]")
	public WebElement VARSUMBIT;
	
	@FindBy(xpath = "//a[@id='testingCompleteRegistration']")
	public WebElement VAREMAILREGISTRATIONLINK;

	@FindBy(xpath = "//fieldset[2]/div/div/div[contains(.,'Select at least one option')]")
	public WebElement SELECTATLEASTONEOPTION;
	
	@FindBy(xpath = "//label/input[@disabled='disabled']/../span[text()='I have agreed to a negotiated contract.']")
	public WebElement NEGCONTTEXTVERIF;
	
	@FindBy(xpath = "//a[@href='mailto:support@vertexsmb.com']")
	public WebElement CUSTOMERSUPPORTLINK;
	
	@FindBy(name = "clientSubTypeName")
	public WebElement ACCOUNTTYPE;
	
	@FindBy(xpath = "//label[contains(.,'VAR/CPA/Franchise Name* :')]")
	public WebElement VARNAMETEXT;
	
	@FindBy(xpath = "//label[@class='subscription-option control-label disabled']/h4[contains(.,'Standard')]")
	public WebElement SUBSTDRDDISABLE;
	
	@FindBy(xpath = "//label[@class='subscription-option control-label disabled']/h4[contains(.,'Premium')]")
	public WebElement SUBPREMDISABLE;
	
	@FindBy(xpath = "//label[@class='subscription-option control-label disabled']/h4[contains(.,'Professional')]")
	public WebElement SUBPROMDISABLE;
	
	@FindBy(xpath = "//input[@disabled='disabled']/../strong[text()='Rate File.']")
	public WebElement RATEFILEDISABLE;
	
	@FindBy(xpath = "//input[@disabled='disabled']/../strong[text()='Tax Calculations.']")
	public WebElement TAXCALCDISABLE;
	
	@FindBy(xpath = "//input[@disabled='disabled']/../strong[text()='Tax Calculations and Returns.']")
	public WebElement TAXCALRETUCDISABLE;
	
	@FindBy(xpath = "//input[@disabled='disabled']/../strong[text()='Returns Only.']")
	public WebElement RONLYCDISABLE;
	
	@FindBy(xpath = "//input[@disabled='disabled']/../strong[text()='Real-Time Rate Service.']")
	public WebElement RTSDISABLE;
	
	@FindBy(xpath = "//input[@disabled='disabled']/../strong[text()='Taxability Impact Report.']")
	public WebElement TIRDISABLE;
	
	@FindBy(xpath = "//h2[contains(text(),'Please contact')]/a[contains(text(),'sales')]")
	public WebElement NEGOCONTEXT;
	
	@FindBy(xpath = "//a[text()='sales']")
	public WebElement SALES;
	
	@FindBy(xpath = "//button[@id='submitTicket']")
	public WebElement SUBMITTICKET;
	
	@FindBy(xpath = "//input[@disabled='disabled']/../strong[text()='Retail Tax Extract.']")
	public WebElement RTEDISABLE;
	
	@FindBy(xpath = "//label[contains(.,'VAR/CPA Name* :')]/../div/select/option[text()='Automation_VAR']")
	public WebElement AUTOMATIONVAR;
	
	@FindBy(xpath = "//label[contains(.,'VAR/CPA Name* :')]/../div/select/option[text()='Merchant_VAR']")
	public WebElement MERCHANTVAR;
	
	@FindBy(id = "FixedPrice")
	public WebElement FIXEDPRICE;
	
	@FindBy(id = "txtFixedPriceValue")
	public WebElement FIXEDPRICEVALUE;
	
	@FindBy(name = "FixedPriceScheduleType")
	public WebElement FIXEDPRICESCHEDULETYPE;
	
	@FindBy(name = "PremiumSubscriptionFeatures")
	public WebElement NEWUSERSUBSCRIPTIONOPTIONPREMIUM;
	
	@FindBy(xpath = "//div[@class='subscription-option control-label  disabled']/h4[contains(.,'Standard')]")
	public WebElement SUBSTDRDDISABLEEDITMYSUB;
	
	@FindBy(xpath = "//div[@class='subscription-option control-label selected']/h4[contains(.,'Professional')]")
	public WebElement SUBPRODISABLEEDIMYSUB;
	
	@FindBy(xpath = "//div[@class='subscription-option control-label selected']/h4[contains(.,'Premium')]")
	public WebElement SUBPREMDISABLEEDIMYSUB;
	
	@FindBy(xpath = "//div[@class='subscription-option control-label ']/h4[contains(.,'Professional')]")
	public WebElement CANCELSUBPROF;
	
	@FindBy(linkText = "account")
	public WebElement VTMSACCOUNT;
	
	@FindBy(id = "TestInstance")
	public WebElement TestInstance;
	
	@FindBy(id = "HasTestInstance")
	public WebElement varTestInstance;
	
	@FindBy(xpath = "//label[contains(.,'Test Instance is a separate Vertex Cloud environment for safely testing your business changes without impacting your production account')]")
	public WebElement testInstancelabel;
	
	@FindBy(xpath = "//input[@id='txtPhone']/../div/span[text()='This field is required.']")
	public WebElement PhoneErrorText;
	
	@FindBy(xpath = "//input[@id='txtZip']/../div/span[text()='This field is required.']")
	public WebElement ZipCodeErrorText;
	
	@FindBy(xpath = "//h1[contains(.,'Manage Sales Forms')]")
	public WebElement Managesalesforms;
	
	@FindBy(xpath = "//input[@id='tbPhone']/../div/span[contains(.,'This field is required.')]")
	public WebElement VarPhoneErrorText;
	
	@FindBy(xpath = "//input[@id='tbZipCpde']/../div/span[contains(.,'This field is required.')]")
	public WebElement VarZipCodeErrorText;
	
	@FindBy(xpath = "//input[@name='bill_to_address_postal_code']/../div/span[contains(.,'This field is required.')]")
	public WebElement BillingZipError;
	
	@FindBy(name = "state")
	public WebElement InternationalState;
	
	@FindBy(id = "HasInvoiceFile")
	public WebElement InvoiceChk;
	
	@FindBy(id = "manual-invoice-approved-by")
	public WebElement ApprovedBy;
	
	@FindBy(id = "manual-invoice-payment-method")
	public WebElement InvoicePaymentMethod;
	
	@FindBy(id = "manual-invoice-start-date")
	public WebElement InvoiceStartDate;
	
	@FindBy(id = "manual-invoice-billing-address")
	public WebElement InvoiceBillingAddresChkbox;
	
	@FindBy(id = "manual-invoice-country")
	public WebElement InvoiceCountry;
	
	@FindBy(id = "manual-invoice-street1")
	public WebElement InvoiceStreet1;
	
	@FindBy(id = "manual-invoice-street2")
	public WebElement InvoiceStreet2;
	
	@FindBy(id = "manual-invoice-city")
	public WebElement InvoiceCity;
	
	@FindBy(id = "manual-invoice-state")
	public WebElement InvoiceState;
	
	@FindBy(id = "manual-invoice-zip")
	public WebElement InvoiceZip;
	
	@FindBy(xpath = "//button[contains(.,'Finish and Save')]")
	public WebElement FinishSave;
	
	@FindBy(xpath = "//label[@for='manual-invoice-zip']/../div/div/span[contains(.,'This field is required.')]")
	public WebElement ZIPERROR;
	
	@FindBy(xpath = "//button[contains(.,'Continue to Billing Information')]")
	public WebElement CONTINUETOBILLINGINFORMATION;
	
	@FindBy(name = "billingState")
	public WebElement BillingState;
	
	@FindBys({ @FindBy(xpath = "//select[@name='Country']/option"), })
	public List<WebElement> ShippingCountries;
	
	@FindBys({ @FindBy(xpath = "//select[@id='manual-invoice-country']/option"), })
	public List<WebElement> InvoiceBillingCountries;
	
	@FindBys({ @FindBy(xpath = "//select[@id='bill_to_address_country']/option"), })
	public List<WebElement> NonInvoiceBillingCountries;
	
	@FindBy(id = "HasRateFileCalc")
	public WebElement RTRS;
	
	@FindBy(name = "Phone")
	public WebElement INTERNAIONAL_PHONENUMBER;
	
	@FindBy(xpath = "//input[@name='Phone']/../div/span[text()='This field is required.']")
	public WebElement INTERNATIONAL_PHONE_ERROR;
	
	@FindBy(xpath = "//select[@name='PremiumSubscriptionFeatures']")
	public WebElement PREMIUMSUBSCRIPTIONSELECT;
	
	@FindBy(xpath = "//select[@name='ProfessionalSubscriptionFeatures']")
	public WebElement PROFESSIONALSUBSCRIPTIONSELECT;

	@FindBys({ @FindBy(xpath = "//select[@name='PremiumSubscriptionFeatures']/option"), })
	public List<WebElement> PremiumSubscriptionFeatures;
		// POR-19667
	
	@FindBy(xpath = "//label[contains(.,'Account type* :')]/../div/div/span")
	public WebElement Accounttypeerrormessage;
	
	@FindBy(xpath = "//label[contains(.,'VAR/CPA/Franchise Name* :')]/../div/div/span")
	public WebElement VARCPANameerrormessage;
	
	@FindBy(xpath = "//label[contains(.,'Client Name* :')]/../div/div/span")
	public WebElement ClientNameerrormessage;
	
	@FindBy(xpath = "//label[contains(.,'First Name* :')]/../div/div/span")
	public WebElement FirstNameerrormessage;
	
	@FindBy(xpath = "//label[contains(.,'Last Name* :')]/../div/div/span")
	public WebElement LastNameerrormessage;
	
	@FindBy(xpath = "//label[contains(.,'Email* :')]/../div/div/span")
	public WebElement Emailerrormessage;
	
	@FindBy(xpath = "//label[contains(.,'Phone* :')]/../div/div/span")
	public WebElement Phoneerrormessage;
	
	@FindBy(xpath = "//label[contains(.,'Street Address* :')]/../div/div/span")
	public WebElement StreetAdressErrorMessage;
	
	@FindBy(xpath = "//label[contains(.,'City* :')]/../div/div/span")
	public WebElement CityErrorMessage;
	
	@FindBy(xpath = "//label[contains(.,'State* :')]/../div/div/span")
	public WebElement StateErrorMessage;
	
	@FindBy(xpath = "//label[contains(.,'Zip/Postal Code* :')]/../div/div/span")
	public WebElement PostalcodeErrorMessage;
	
	@FindBy(xpath = "//label[contains(.,'Subscription* :')]/../div/div")
	public WebElement SubcriptionErrorMessage;
	
	@FindBy(xpath = "//label[contains(.,'Fixed Price :')]/../div[contains(.,' Please select Fixed Price and/or Negotiated Contract.')]")
	public WebElement FixedpriceErrorMessage;
	
	@FindBy(xpath = "//label[contains(.,'Customer Negotiated Contract :')]/../div[contains(.,' Please select Fixed Price and/or Negotiated Contract.')]")
	public WebElement NegotiatedContractErrorMessage;
	
	@FindBy(xpath = "//button[contains(.,'Finish and Save')]")
	public WebElement ECWFinishandSave;
	
	@FindBy(xpath = "//h2[contains(.,'Exemption Certificate Wizard')]")
	public WebElement ECWheaderText;
	
	@FindBy(xpath = "//p[contains(.,'The Exemption Certificate Wizard portal allows you to define system preferences and configuration, click the Launch Site button below to continue.')]")
	public WebElement ECWWidgetText;
	
	@FindBy(xpath = "//a[contains(.,'Launch Site')]")
	public WebElement LaunchSite;
	
	@FindBy(xpath = "//a[contains(.,'EC Wizard')]")
	public WebElement ECWIZARD;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement SEARCH;
	
	@FindBy(xpath = "//input[@id='username']")
	public WebElement ECWUsername;
	
	@FindBy(xpath = "//input[@id='password']")
	public WebElement ECWPwd;
	
	@FindBy(xpath = "//span[contains(.,'Login')]")
	public WebElement ECWLogin;
	
	@FindBy(xpath = "//button[@id='log-out']")
	public WebElement ECWLogout;
	
	@FindBy(xpath = "//div/input[@name='ClientName']/../div[contains(.,'Special characters are not allowed as a first letter.')]")
	public WebElement InvalidClientName;
	
	@FindBy(xpath = "//input[@name='OraclePartyNumber']")
	public WebElement OraclePartyNumber;
	
	@FindBy(xpath = "//span[contains(.,'Oracle Party Number already exists.')]")
	public WebElement OraclepartyError;
	
	@FindBy(id = "HasDataIntegrity")
	public WebElement DATAINTEGRITY;
	
	@FindBy(id = "include-data-integrity")
	public WebElement ADMINDATAINTEGRITY;
	
	@FindBy(xpath = "//label[contains(.,'Data Integrity')]/select")
	public WebElement DATASTORAGRREGION;
	
	@FindBy(id = "data-integrity-storage-region")
	public WebElement ADMINDATASTORAGRREGION;
	
	@FindBy(id = "HasGlobalCompliance")
	public WebElement GLOBALCOMPLIANCE;
	
	@FindBy(xpath = "//input[@ng-disabled='true']/../strong[contains(.,'Global Compliance')]")
	public WebElement DISABLEGLOBALCOMPLIANCE;
	
	@FindBy(xpath = "//input[@ng-disabled='true']/../strong[contains(.,'Data Integrity')]")
	public WebElement DISABLEDATAINTEGRITY;
	
	@FindBy(xpath = "//input[@id='HasEcw']")
	public WebElement ECWFEATURE;
	
	@FindBy(xpath = "//label[contains(.,'EC Wizard')]")
	public WebElement ECWIZARDFEATURE;
	
	@FindBy(xpath = "//input[@id='HasLegacyTransactionReport']")
	public WebElement LEGACYTRANSACTIONREPORT;
	
	@FindBy(xpath = "//input[@id='HasAcceleratorOracleErpCloud']")
	public WebElement ACCELERATORORACLEERPCLOUD;
	
	@FindBy(xpath = "//input[@id='HasIndirectTaxIntelligence']")
	public WebElement INDIRECTTAXINTELLIGENCE;
	
	@FindBy(xpath = "//label[contains(.,'Accelerator - Oracle ERP Cloud')]")
	public WebElement ORACLEERPCLOUD;
	
	@FindBy(xpath = "//strong[contains(.,'Accelerator - Oracle ERP Cloud.')]")
	public WebElement ACCELERATORTEXT1;
	
	@FindBy(xpath = "//label[contains(.,'Oracle ERP Cloud provides sales and consumer use tax calculations.')]")
	public WebElement ACCELERATORTEXT2;
	
	@FindBy(xpath = "//input[@id='include-accelerator-oracle-erp-cloud']")
	public WebElement ACCELERATORDISABLED;
	
	@FindBy(name = "SalesRepEmails")
	public WebElement SALESREPEMAIL;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	public WebElement Adminpasswordconfirm;
	
	@FindBy(xpath = "//button[contains(.,'Complete Registration')]")
	public WebElement Admincompleteinvite;
	
	@FindBy(xpath = "//input[@id='UserName']")
	public WebElement Varusername;
	
	@FindBy(xpath = "//input[@id='Password']")
	public WebElement Varpassword;
	
	@FindBy(xpath = "//input[@id='PasswordConfirm']")
	public WebElement Varpasswordconfirm;
	
	@FindBy(xpath = "//button[contains(.,'Complete')]")
	public WebElement Varcomplete;
	
	@FindBy(xpath = "//div/a[contains(.,'Login')]")
	public WebElement LOGINBUTTON;
	
	@FindBy(xpath = "//h2[contains(.,'Data Integrity')]")
	public WebElement DATAINTEGRTYTEXT;
	
	@FindBy(xpath = "//p[contains(.,'The Data Integrity portal allows you to setup standardized Vertex rules, lookups, templates, etc. for use by subscribers of the product. Click the Launch Site button below to continue.')]")
	public WebElement DATAINTEGRTYWIDGETTEXT;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement userinput;
	
	@FindBy(xpath = "//input[@id='Password']")
	public WebElement PASWORD;
	
	@FindBy(xpath = "//input[@id='Username']")
	public WebElement UserName;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	public WebElement PASSWORDCONF;
	
	@FindBy(xpath = "//div/h1[contains(.,'Success')]")
	public WebElement Success;
	
	@FindBy(xpath = "//div/a[contains(.,'Login')]")
	public WebElement Loginbutton;
	
	@FindBy(xpath = "//label[contains(.,'Reserved Pod :')]")
	public WebElement ReservedPodText;
	
	@FindBy(xpath = "//div[contains(.,'Reserved Pod ')]/input[@id='HasReservedPod']")
	public WebElement ReservedPodinput;
	
	@FindBy(xpath = "//select[@name='reservedPod']")
	public WebElement ReservedPodselection;

	@FindBy(xpath = "//strong[contains(.,'Payroll Tax Calculation.')]/../../label[contains(.,'Determine applicable taxes and tax amounts related to employee paychecks.')]")
	public WebElement payrollcalcdisabledtext;

	@FindBy(xpath = "//strong[contains(.,'Payroll Address Cleansing.')]/../../label[contains(.,'Receive a USPS cleansed address including the associated geocode, school district, and PSD information.')]")
	public WebElement payrolladdresscleansingdisabledtext;

	@FindBy(xpath = "//label/input[@id='vertex-cloud-oseries' and @disabled='disabled']")
	public WebElement calcparitydisabledtext;

	@FindBy(xpath = "//label/../label/strong[contains(.,'Cloud OSeries - Tax Calculations.')]")
	public WebElement calcparitydescrition;

	@FindBy(xpath = "//label[contains(.,'O Series Cloud')]")
	public WebElement CloudOSeriestext;
	
	@FindBy(xpath = "//a[contains(.,'Back  »')]")
	public WebElement BACKLINK;

	@FindBy(xpath = "//input[@name='bill_to_forename']")
	public WebElement BILLINGNAME;
	
	@FindBy(xpath = "(//label[contains(.,'Indirect Tax Intelligence')])[2]")
	public WebElement INDIRECTTAXINTELLIGENCETEXT;
	
	@FindBy(xpath = "(//input[@id='HasIndirectTaxEdge'])[2]")
	public WebElement OSERIESEDGE;
	
	@FindBy(xpath = "//a[contains(.,'Indirect Tax O Series')]")
	public WebElement INDIRECTTAXOSERIESCLOUD;
	
}
