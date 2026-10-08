package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class ProfessionalUserLinks {
	public final String CASETYPE_XPATH = "//select[@id='caseTypes']";

	public final String OKERROR_XPATH = "//p[contains(.,'An error has occurred.')]";

	public final String RETURNSONLYUPLOAD_XPATH = "//a[contains(text(),'Returns Only  Adjustment Files')]";

	public final String RETRUNADJUSTFILEFORMAT_XPATH = "//select[@id='AdjustmentFileFormat']/option";
	// public final String
	// VISITHELPCENTERWhatsnew_XPATH="//a[contains(text(),'Whatï¿½s New')]";

	public final String FAQS_XPATH = "//h1[contains(.,'Frequently Asked Questions')]";

	public final String TRANSACTIONAFTERDD_XPATH = "//select[@ng-model='data.pageSize']";

	@FindBy(xpath = "//i[@class='icon-info icon-small section-heading']")
	public WebElement ICONDASHBOARD;

	@FindBy(xpath = "//i[@class='icon-info icon-small']")
	public WebElement ICON;

	@FindBy(xpath = "//inline-help[@id='calculateTaxesHelp']/a/i[@class='icon-info icon-small section-heading']")
	public WebElement ICONCALC;

	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	public WebElement DASHBOARDLINK;

	@FindBy(xpath = "//span[text()='Inline Help']")
	public WebElement INLINEHELP;

	@FindBy(xpath = "//span[contains(text(),'Configure')]")
	public WebElement CONFIGURELINK;

	@FindBy(xpath = "//span[contains(text(),'Calculate Tax')]")
	public WebElement CALTAX;

	@FindBy(xpath = "//a[@title='Calculate Taxes']")
	public WebElement CALCULATETAXES;

	@FindBy(xpath = "//a[@title='Distribute Tax']")
	public WebElement DISTRIBUTETAX;

	@FindBy(xpath = "//a[@title='Manage Adjustment File']")
	public WebElement MANAGEADJUSTMENTFILELINK;

	@FindBy(xpath = "//span[contains(text(),'Rates & Jurisdictions')]")
	public WebElement RatesJurisdictions;

	@FindBy(xpath = "//span[contains(text(),'Manage Tax Returns')]")
	public WebElement MANAGETAXRETURNSLINK;

	@FindBy(xpath = "//span[contains(text(),'Customer ID Report')]")
	public WebElement CUSTOMERIDREPORT;

	@FindBy(xpath = "//span[text()='Reports']")
	public WebElement REPORTSLINK;

	@FindBy(xpath = "//a[contains(.,'Configuration')]")
	public WebElement CONFIGURATIONREPORTLINK;

	@FindBy(xpath = "//a[contains(.,'Filing Calendar')]")
	public WebElement FILLINGCALENDERREPORTLINK;

	@FindBy(xpath = "//a[@title='Tax Assist Rule Report']")
	public WebElement TAXASSISTRULEREPORTLINK;

	@FindBy(xpath = "//a[@href='/taxabilityreport']")
	public WebElement TAXABILITYREPORTSLINK;

	@FindBy(xpath = "//a[contains(.,'Transaction - US and Canada')]")
	public WebElement TRANSACTIONREPORTLINK;

	@FindBy(xpath = "//a[contains(text(),'Customer Exemption')]")
	public WebElement CUSTOMEREXEMPTIONREPORTLINK;

	@FindBy(xpath = "//a[contains(.,'Validation Errors')]")
	public WebElement VALIDATIONERRORSREPORTLINK;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement SETTINGSLINK;

	@FindBy(xpath = "//p/a[contains(text(),'Edit My Subscription')]")
	public WebElement SETTINGSEDITSUBSCRIPTIONLINK;

	@FindBy(xpath = "//div[@class='section header-block ng-scope']/h1/a")
	public WebElement BILLRETURNTOSET;

	@FindBy(xpath = "//div/span[text()='#']")
	public WebElement BILLREPCOLUMN1;

	@FindBy(xpath = "//div/span[text()='Billing Period']")
	public WebElement BILLREPCOLUMN2;

	@FindBy(xpath = "//div/span[text()='Billing Reports']")
	public WebElement BILLREPCOLUMN3;

	@FindBy(xpath = "//p[3]/a[text()='Edit My Subscription']")
	public WebElement EDITMYSUBSCRIPTION;

	@FindBy(xpath = "//a[contains(text(),'Edit My Company')]")
	public WebElement EDITCOMPANYLINK;

	@FindBy(xpath = "//a[contains(text(),'View All Users')]")
	public WebElement VIEWALLUSERSLINK;

	@FindBy(xpath = "//li/a[contains(text(),'Update My Billing')]")
	public WebElement UPDATEBILLINGLINK;

	@FindBy(xpath = "//a[contains(text(),'Edit My Subscription')]")
	public WebElement EDITSUBSCRIPTIONLINK;

	@FindBy(xpath = "//a[contains(text(),'Billing Reports')]")
	public WebElement BILLINGREPORTSLINK;

	@FindBy(xpath = "//a[contains(text(),'View All Connectors')]")
	public WebElement ADAPTERSLINK;

	@FindBy(xpath = "//a/span[contains(text(),'Document Center')]")
	public WebElement DOCUMENTCENTRE;

	@FindBy(xpath = "//div/a[contains(.,'View All Billing Reports')]")
	public WebElement VIEWALLBILLREPORTS;

	@FindBy(xpath = "//table/tbody/tr/td[3]/a[contains(text(),'Quote')]")
	public WebElement QUOTEBILLING;

	@FindBy(xpath = "//span[contains(text(),'Billing Reports')]")
	public WebElement BILLINGREPORTS_RECPT_QUOTES_LINK;

	@FindBy(xpath = "//a[contains(text(),'Receipts and Quotes')]")
	public WebElement BILLINGRECEIPTSLINK;

	@FindBy(xpath = "//a/span[contains(.,'Visit Help Center')]")
	public WebElement GETHELPLINK;

	@FindBy(xpath = "//div[@id='docExtraTabs']/ul/li/a[@id='Informationlink']")
	public WebElement GETHELPINFORMATIONLINK;

	@FindBy(xpath = "//a/span[contains(text(),'Contact Support')]")
	public WebElement CONTACTSUPPORTLINK;

	@FindBy(xpath = "//span[contains(.,'Frequently Asked Questions')]")
	public WebElement VISITHELPCENTERFAQS;

	@FindBy(xpath = "//li/a[contains(@href,'https://helpcenter.vertexsmb.com/whats-new/')]")
	public WebElement VISITHELPCENTERWHATSNEW;

	@FindBy(xpath = "//div[@class='alert alert-error']")
	public WebElement ERRORDISTRIBUTE;

	@FindBy(xpath = "//h1[contains(.,'Distribute Taxes')]")
	public WebElement DISTRIBUTETAXESTEXT;

	@FindBy(xpath = "//h1[contains(.,'Distribute Taxes')]/../../div[contains(.,'Enter a Total Tax amount to be distributed to the applicable jurisdictions based on the provided State and Zip Code combination.')]")
	public WebElement DISTRIBUTETAXTEXT;

	@FindBy(xpath = "//h2[text()='Transaction Details']")
	public WebElement TRANSDISTRIBUTE;

	@FindBy(xpath = "//div[@class='left-col nav-col view-collapsed']/div/ul/li/a/span[contains(text(),'Configure')]")
	public WebElement COPSDMTCTABICON;

	@FindBys({ @FindBy(xpath = "//select[@id='caseTypes']/option"), })
	public WebElement CASETYPE;

	@FindBy(xpath = "//a[contains(.,'Download Rate Files')]")
	public WebElement DWLDRATEFILE_LINK;

	@FindBy(xpath = "//a[contains(.,'My Rate Files')]")
	public WebElement MYRATEFILE_LINK;

	@FindBy(xpath = "//input[@id='include-rate-file']")
	public WebElement RATEFILE;

	@FindBy(xpath = "//h3[contains(text(),'Confirm Subscription Changes')]")
	public WebElement PASSWORDCONFIRM;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement PASSWORD;

	@FindBy(xpath = "//button[contains(.,'Save Changes')]")
	public WebElement SAVECHANGES;

	@FindBy(xpath = "//div[@class='alert alert-success ng-binding ng-scope']")
	public WebElement SUCCESS;

	@FindBy(xpath = "//a[contains(text(),'Manage Tax Returns')]")
	public WebElement MANAGETAXRETURNS;

	@FindBy(xpath = "//a[contains(text(),'Returns Only  Adjustment Files')]")
	public WebElement RETURNSONLYUPLOAD;

	@FindBy(xpath = "//h1[contains(.,'Returns Only Adjustment Files')]")
	public WebElement RETURNSONLYTITLE;

	@FindBy(xpath = "//h1[contains(text(),'Returns Only Adjustment Files')]")
	public WebElement RETURNONLYADJUSTMENTFILESHEADER;

	@FindBy(xpath = "//p[contains(.,'Upload your address to get tax rates on tangible personal property where you do business.') and contains(.,'for information on the Rate file layout.')]")
	public WebElement MYRATEFILESDESC;

	@FindBy(xpath = "//div[contains(text(),' Returns Only adjustment files to upload your transactions for returns processing. Need more information? Please refer to the following article in the')]")
	public WebElement RETURNADJUSTMENTFILETEXT;

	@FindBy(xpath = "//a[contains(.,'Vertex Help Center')]")
	public WebElement RETURNEXTENDEDHELP;

	@FindBy(xpath = "//a[contains(text(),'Upload New File')]")
	public WebElement UPLOADNEWFILELINK;

	@FindBy(xpath = "//h1[contains(.,'Returns Only Adjustment File Upload')]")
	public WebElement RETURNSUPLOADADJUSTMENTFILEHEADER;

	@FindBy(xpath = "//a[contains(text(),'Return To Returns Only Adjustment Files')]")
	public WebElement RETURNSRETURNTOADJUSTMENTFILES;

	@FindBy(xpath = "//table[@id='returnsAdjustmentFilesTable']/thead/tr/th/div/span[contains(text(),'File Upload Date')]")
	public WebElement RETURNTABLEADJUSTMENTFILEUPLOAD;

	@FindBy(xpath = "//table[@id='returnsAdjustmentFilesTable']/thead/tr/th/div/span[contains(text(),'File Name')]")
	public WebElement RETURNTABLEADJUSTMENTFILENAME;

	@FindBy(xpath = "//table[@id='returnsAdjustmentFilesTable']/thead/tr/th[contains(text(),'Original File')]")
	public WebElement RETURNTABLEORIGINALFILE;

	@FindBy(xpath = "//table[@id='returnsAdjustmentFilesTable']/thead/tr/th[contains(text(),'Success Report')]")
	public WebElement RETURNTABLESUCCESSREPORT;

	@FindBy(xpath = "//table[@id='returnsAdjustmentFilesTable']/thead/tr/th[contains(text(),'Error Report')]")
	public WebElement RETURNTABLEERRORREPORT;

	@FindBy(xpath = "//a[contains(text(),'Return To Returns Only Adjustment Files')]")
	public WebElement RETURNTORETURNADJUSTMENTFILES;

	@FindBy(xpath = "//select[@id='AdjustmentFileFormat']/option")
	public WebElement RETRUNADJUSTFILEFORMAT;

	@FindBy(xpath = "//button[@id='downloadTemplateButton']")
	public WebElement RETURNADJUSTMENTFILEFORMAT;

	@FindBy(xpath = "//a[contains(.,'Create New User')]")
	public WebElement CREATENEWUSER;

	@FindBy(xpath = "//span[contains(.,'Enter a Total Tax amount to be distributed to the applicable jurisdictions based on the provided State and Zip Code combination.')]")
	public WebElement DISTRIBUTEHEADING;

	@FindBy(xpath = "//span[contains(.,'This report displays due dates for your currently configured registrations.')]")
	public WebElement FILINGCALENDARHEADING;

	@FindBy(xpath = "//h1[contains(.,'Taxability Reports')]/../../div[contains(.,'This report displays taxability for your configured products and registrations.')]")
	public WebElement TAXABILITYREPORTHEADING;

	@FindBy(xpath = "//h1[contains(.,'Validation Error Reports')]/../../div[contains(.,'This report will only be displayed if a validation error has occurred. A validation error occurs when a code is passed that is not currently configured in Vertex Cloud')]")
	public WebElement VALIDATIONREPOTHEADING;

	@FindBy(xpath = "//div[2]/p[contains(.,'Billing reports are displayed after billing occurs on the second business day of each month when you are in')]")
	public WebElement BILLINGREPORTHEADING;

	@FindBy(xpath = "//div[@class='section'][contains(.,'Complete this form to send a notification to Vertex SMB support. Include as much information as possible so that your request can be directed to the appropriate support area.')]")
	public WebElement CONTACTSUPPORTHEADING;

	@FindBy(xpath = "//div[@class='alert alert-info']/strong[contains(.,'Important!')]")
	public WebElement DOCUMENTCENTERHEADING;

	@FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr/td[contains(.,'Real-Time Rate Service Transaction Count')]/../td[2][contains(.,'0')]")
	public WebElement CURRMONTH;

	@FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr/td[contains(.,'Real-Time Rate Service Transaction Count')]/../td[3][contains(.,'0')]")
	public WebElement PREVMONTH;

	@FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr/td[contains(.,'Real-Time Rate Service Transaction Count')]/../td[4][contains(.,'0')]")
	public WebElement PREVMONTH1;

	@FindBy(xpath = "//span[contains(.,'Program Resources')]")
	public WebElement PROGRAMRESOURCES;

	@FindBy(xpath = "//span[contains(text(),'Manage Clients')]")
	public WebElement MANAGECLIENTS;

	@FindBy(xpath = "//h2[contains(.,'Sales Tax Automation Program')]")
	public WebElement SALESTAXAUTOPROGRAM;

	@FindBy(xpath = "//p[contains(.,'The Sales Tax Automation Program provides the educational resources, industry updates,')]/../p[contains(.,'and news required to manage sales tax compliance.')]/../p[contains(.,'To access these materials, please click Read More below to launch this program.')]")
	public WebElement STAPHELPTEXT;

	@FindBy(xpath = "//a[contains(.,'Read More')]")
	public WebElement READMOREBUTTON;

	@FindBy(xpath = "//h2[contains(.,'Enroll new client')]")
	public WebElement ENROLLNEWCLIENT;

	@FindBy(xpath = "//p[contains(.,'Enrolled clients are registered for and managed in TaxCentral interally by your firm. Click Enroll New Client to complete the registration process and begin TaxCentral configuration.')]")
	public WebElement ENROLLNEWCLIENTHELPTEXT;

	@FindBy(xpath = "//h2[contains(.,'Refer new client')]")
	public WebElement REFERNEWCLIENT;

	@FindBy(xpath = "//p[contains(.,'Referral clients register for and manage their TaxCentral accounts under their own managment.')]")
	public WebElement REFERNEWCLIENTHELPTEXT;

	@FindBy(xpath = "//div/h2[contains(.,'Enroll new client')]/../button[contains(.,'Order Form and Pricing Info')]")
	public WebElement ENROLLDOWNLOADPRICINGFINFO;

	@FindBy(xpath = "//div/button[contains(.,'Enroll Shared Services')]/../button[contains(.,'Order Form and Pricing Info')]")
	public WebElement ENROLLSHAREDSERVICESINFO;

	@FindBy(xpath = "//div/h2[contains(.,'Refer new client')]/../a[contains(.,'Download pricing info')]")
	public WebElement REFERDOWNLOADPRICINGINFO;

	@FindBy(xpath = "//strong[text()='Enrolled Clients']")
	public WebElement ENROLLEDCLIENTS;

	@FindBy(xpath = "//h2[contains(.,'This is an example positioning statement encouraging the user to sign up')]")
	public WebElement READMOREHELPTEXT;

	@FindBy(xpath = "//h1[contains(.,'Client Registration')]")
	public WebElement CLIENTREGISTRATION;

	@FindBy(xpath = "//button[contains(.,'Enroll New Client')]")
	public WebElement ENROLLNEWCLIENTBUTTON;

	@FindBy(xpath = "//a[contains(text(),'Refer new')]")
	public WebElement REFERNEWCLIENTBUTTON;

	@FindBy(xpath = "//h1[contains(.,'Refer a Client')]")
	public WebElement REFERCLIENTPAGEHEADER;

	@FindBy(xpath = "//p[contains(.,'Vertex Cloud')]/../p[contains(.,'s sales and use tax automation solution empowers accounting professionals with the tools they need to solidify their position as a trusted advisor to their clients. Let Vertex Cloud help your clients calculate taxes, manage exemption certificates, generate signature-ready PDF returns, and remain up-to-date with changing tax laws and rate updates. Please complete the form below to tell us about your client.')]")
	public WebElement REFERCLIENTHELPETEXT;

	@FindBy(xpath = "//a[contains(.,'Refer new client')]")
	public WebElement REFERALNEWCLIENTBUTTON;

	@FindBy(xpath = "//li/a[contains(.,'News')]")
	public WebElement NEWS;

	@FindBy(xpath = "//li/a[contains(.,'Events')]")
	public WebElement EVENTS;

	@FindBy(xpath = "//li/a[contains(.,'About')]")
	public WebElement ABOUT;

	@FindBy(xpath = "//li/a[contains(.,'Login')]")
	public WebElement LOGIN;

	@FindBy(xpath = "//li/a[contains(.,'Enroll Now')]")
	public WebElement ENROLLNOW;

	@FindBy(xpath = "//h1[contains(.,'Manage Clients')]")
	public WebElement MANAGECLIENTTITLE;

	@FindBy(xpath = "//h1[contains(.,'Manage Clients')]/../../div[contains(.,'Select a client name in order to manage the client')]")
	public WebElement MANAGECLIENTHELPTEXT;

	@FindBy(xpath = "//a[contains(.,'Enroll New Client')]")
	public WebElement ENROLLNCLIENT;

	@FindBy(xpath = "//h1[contains(.,'Lookup Tax Rates')]")
	public WebElement LOOKUPTITLE;

	@FindBy(xpath = "//p[contains(.,'Rate Lookup provides the ability to search for tax rates for states or provinces, counties, cities, and district based on address information provided.')]")
	public WebElement LOOKUPHELPTEXT;

	@FindBy(xpath = "//h1[contains(.,'Settings')]")
	public WebElement SETTINGSTITLE;

	@FindBy(xpath = "//h2[contains(.,'Company Information')]")
	public WebElement COMPNAYINFORMATIN;

	@FindBy(xpath = "//small[contains(.,'© 2021 Vertex, Inc. All rights reserved.')]")
	public WebElement ALLRIGHTSRESERVED;

	@FindBy(xpath = "//h2[contains(.,'Manage Users')]")
	public WebElement MANAGEUSER;

	@FindBy(xpath = "//a[contains(.,'View All Users')]")
	public WebElement VIEWALLUSERS;

	@FindBy(xpath = "//a[contains(.,'Create New User')]")
	public WebElement CREATENUSER;

	@FindBy(xpath = "//div[@class='row-fluid']/div/div/h2[contains(.,'Subscription And Payment')]")
	public WebElement SUBCRIPTIONPAYMENT;

	@FindBy(xpath = "//a[contains(.,'Manage Subscription Settings')]")
	public WebElement MANAGESUBCRIPTIONSETTINGS;

	@FindBy(xpath = "//h2/i[@class='icon-section icon-my-subscriptions']")
	public WebElement SUBSCANDPAY;

	@FindBy(xpath = "//h1[contains(.,'Contact Support')]")
	public WebElement CONTACTSUPPORTTITLE;

	@FindBy(xpath = "//h1[contains(.,'Product & Technical Support')]")
	public WebElement PRODTECHSUPPORT;

	@FindBy(xpath = "//h1[contains(.,'Console Billing Support')]")
	public WebElement CONSOLEBILLSUPPORT;

	@FindBy(xpath = "//h1[contains(.,'Program Information')]")
	public WebElement PROGRAMINFORMATION;

	@FindBy(xpath = "//span[contains(.,'Rate File')]")
	public WebElement RATEFFILEMENLINK;

	@FindBy(xpath = "//h1[contains(.,'Download Rate Files')]")
	public WebElement RATEFILEHEADERTEXT;

	@FindBy(xpath = "//div[@class='control-group']/select")
	public WebElement MONTHYEAR;

	@FindBy(xpath = "//a[contains(.,'Rate File Format')]")
	public WebElement RATEFILETEMPLATELINK;

	@FindBy(xpath = "//p/a[contains(.,'Help Center')]")
	public WebElement EXTENDEDHELPRATEFILE;

	@FindBy(xpath = "//a[contains(.,'Upload New File')]")
	public WebElement UPDNEWFILELINK;

	@FindBy(xpath = "//select[@id='tax-payer']")
	public WebElement COMPANYDD;

	@FindBy(xpath = "//select[@id='tax-payer-filing-period']")
	public WebElement FILINGPERIODDD;

	@FindBy(xpath = "//button[contains(.,'Update Results')]")
	public WebElement UPDATERESULTS;

	@FindBy(xpath = "//label[contains(.,'United States')]")
	public WebElement UNITEDSTATES;

	@FindBy(xpath = "//label[contains(.,'Canada')]")
	public WebElement CANADA;

	@FindBy(xpath = "//div[@id='svgMap']")
	public WebElement MAP;

	@FindBy(xpath = "//h1[contains(.,'Dashboard')]")
	public WebElement DASHBOARDHEADER;

	@FindBy(xpath = "//h2[contains(.,'Overall Numbers')]")
	public WebElement OVERALLNUMBERSSECTION;

	@FindBy(xpath = "//h2[contains(.,'State Statistics')]")
	public WebElement STATESTATSTICS;

	@FindBy(xpath = "//h2[contains(.,'Comparative Analysis')]")
	public WebElement COMPARATIVEANALYSIS;

	@FindBy(xpath = "//select[@id='chart-currently-showing']")
	public WebElement CURRENTLYSHOWINGDD;

	@FindBy(xpath = "//select[@id='chart-sort-by']")
	public WebElement SORTBYDD;

	@FindBy(xpath = "//a[contains(.,'Update Analysis')]")
	public WebElement UPDATEANALYSIS;

	@FindBy(xpath = "//h2[contains(.,'Transaction Details')]")
	public WebElement INVOICEDEATAILS;

	@FindBy(xpath = "//h2[contains(.,'Shipping Information')]")
	public WebElement SHIPPINGINFORMATION;

	@FindBy(xpath = "//h2[contains(.,'Line Items')]")
	public WebElement LINEITEMS;

	@FindBy(xpath = "//a[contains(.,'+ Upload New File')]")
	public WebElement UPLOADNEWFILE;

	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/thead/tr/th/div[contains(.,'File Upload Date')]")
	public WebElement FILEUPLOADDATE;

	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/thead/tr/th/div[contains(.,'Adjustment File Name')]")
	public WebElement ADJUSTMENTFILENAME;

	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/thead/tr/th[contains(.,'Original File')]")
	public WebElement ORIGINALFILE;

	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/thead/tr/th[contains(.,'Success Report')]")
	public WebElement SUCCESSREPORT;

	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/thead/tr/th[contains(.,'Error Report')]")
	public WebElement ERRORREPORT;

	@FindBy(xpath = "//select[@id='filing-period']")
	public WebElement FILINGPERIOD;

	@FindBy(xpath = "//table[@id='tblManageTaxReturns']/thead/tr/th/div[contains(.,'Return')]")
	public WebElement RETURN;

	@FindBy(xpath = "//table[@id='tblManageTaxReturns']/thead/tr/th/div[contains(.,'Due Date')]")
	public WebElement DUEDATE;

	@FindBy(xpath = "//table[@id='tblManageTaxReturns']/thead/tr/th/div[contains(.,'Additional Returns Data')]")
	public WebElement PREPAYMENTS;

	@FindBy(xpath = "//table[@id='tblManageTaxReturns']/thead/tr/th/div[contains(.,'Most Recent')]")
	public WebElement MOSTRECENT;

	@FindBy(xpath = "//label[contains(.,'Name*')]")
	public WebElement NAME;

	@FindBy(xpath = "//label[contains(.,'Phone*')]")
	public WebElement PHONE;

	@FindBy(xpath = "//label[contains(.,'Country*')]")
	public WebElement COUNTRY;

	@FindBy(xpath = "//label[contains(.,'Address 1*')]")
	public WebElement ADD1;

	@FindBy(xpath = "//label[contains(.,'Address 2')]")
	public WebElement ADD2;

	@FindBy(xpath = "//label[contains(.,'City*')]")
	public WebElement CITY;

	@FindBy(xpath = "//label[@id='stateLabel']")
	public WebElement STATE;

	@FindBy(xpath = "//label[@id='zipLabel']")
	public WebElement ZIP;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement SAVE;

	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement CANCEL;

	@FindBy(xpath = "//span[contains(.,'Trusted ID:')]")
	public WebElement TRUSTEDIDLABEL;

	@FindBy(xpath = "//th[contains(.,'Connector Name')]")
	public WebElement CONNECTORNAME;

	@FindBy(xpath = "//th[contains(.,'ERP Connector URL')]")
	public WebElement ERPURL;

	@FindBy(xpath = "//th[contains(.,'Address Lookup URL')]")
	public WebElement LOOKUPURL;

	@FindBy(xpath = "//label[contains(.,'Contact Name')]")
	public WebElement CONTACTNAME;

	@FindBy(xpath = "//label[contains(.,'Contact Email')]")
	public WebElement CONTACTEMAIL;

	@FindBy(xpath = "//label[contains(.,'Contact Phone')]")
	public WebElement CONTACTPHONE;

	@FindBy(xpath = "//label[contains(.,'Company Name')]")
	public WebElement COMPANYNAME;

	@FindBy(xpath = "//label[contains(.,'Case Type')]")
	public WebElement CASETYP;

	@FindBy(xpath = "//label[contains(.,'Case Sub Type')]")
	public WebElement CASESUBTYPE;

	@FindBy(xpath = "//label[contains(.,'Subject')]")
	public WebElement SUBJECT;

	@FindBy(xpath = "//label[contains(.,'Description')]")
	public WebElement DESCRIPTION;

	@FindBy(xpath = "//button[contains(.,'Submit Ticket')]")
	public WebElement SUBMITTICKET;

	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement CANCELBUTTON;

	@FindBy(xpath = "//h4[contains(.,'Standard')]")
	public WebElement STANDARD;

	@FindBy(xpath = "//h4[contains(.,'Professional')]")
	public WebElement PROFESSIONAL;

	@FindBy(xpath = "//h4[contains(.,'Premium')]")
	public WebElement PREMIUM;

	@FindBy(xpath = "//a[contains(.,'Cancel Subscription')]")
	public WebElement CANCELSUBSCRIPTION;

	@FindBy(xpath = "//a[contains(.,'Partner Topics')]")
	public WebElement PARTNERTOPICS;

	@FindBy(xpath = "//a[contains(.,'Customer Topics')]")
	public WebElement CUSTOMERTOPICS;

	@FindBy(xpath = "//button[text()='Lookup Rates']")
	public WebElement LOOKUPRATE;

	@FindBy(xpath = "//button[contains(.,'Submit Billing Information')]")
	public WebElement SUBMITBILINFO;

	@FindBy(xpath = "//a[text()='Return to Settings']")
	public WebElement RETURNTOSETTINGS;

	@FindBy(xpath = "//button[contains(.,'ADD COMPANY')]")
	public WebElement ADDCOMPANY;

	@FindBy(xpath = "//h1[text()='Configure Company']")
	public WebElement CONFIGUREOVERVIEW;

	@FindBy(xpath = "//inline-help[@id='configmain-help']/span/a/strong[text()='Help Center']")
	public WebElement HELPCENTER;

	@FindBy(xpath = "//h1[contains(.,'Frequently Asked Questions')]")
	public WebElement FAQS;

	@FindBy(xpath = "//a[contains(.,'Whatï¿½s New)]")
	public WebElement WHATSNEW;

	@FindBy(xpath = "//a[contains(.,'DOWNLOAD PRINTABLE REPORT')]")
	public WebElement CONFIGREPORT;

	@FindBy(xpath = "//h1[contains(.,'Configuration Report')]")
	public WebElement CONFIGREPORTTXT;

	@FindBy(xpath = "//div[@class='pull-right']/button[@class='btn btn-primary']")
	public WebElement CREATE;

	@FindBy(xpath = "//a[contains(.,'Generate')]")
	public WebElement GENERATE;

	@FindBy(xpath = "//select[@id='company']")
	public WebElement TAXABILITYCOMPDD;

	@FindBy(xpath = "//select[@id='companyList']")
	public WebElement TRANSACTIONREPORTDD;

	@FindBy(xpath = "//tbody/tr/td/a/i[@class='icon icon-blue icon-add']")
	public WebElement TAXABILITYREPORTPLUS;

	@FindBy(xpath = "//tbody/tr/td/a/i[@class='icon icon-blue icon-minus']")
	public WebElement TAXABILITYREPORTMINUS;

	@FindBy(xpath = "//select[@class='pagesize']")
	public WebElement TRANSACTIONBEFOREDD;

	@FindBy(xpath = "//select[@ng-model='data.pageSize']")
	public WebElement TRANSACTIONAFTERDD;

	@FindBy(xpath = "//div[@class='alert alert-success'][contains(.,'Successfully Generated')]")
	public WebElement CUSTOMEREXCEPTIONSUCCESS;

	@FindBy(xpath = "//a[contains(.,'Delete')]")
	public WebElement DELETE;

	@FindBy(xpath = "//h1[contains(.,'Dashboard')]")
	public WebElement DashBoardText;

	@FindBy(xpath = "//tbody/tr/td/span/a[contains(.,'View')]")
	public WebElement VIEW;

	@FindBy(xpath = "//h2[contains(.,'Accountant Console Billing & Subscription')]")
	public WebElement ACCOUNTCONSOLE;

	@FindBy(xpath = "//h2[contains(.,'Billing AND Subscription')]")
	public WebElement BillingAndSubScription;

	@FindBy(xpath = "//p/a[contains(.,'Update My Billing')]")
	public WebElement UpdateBillingLink;

	@FindBy(xpath = "//a[contains(.,'Users by Client')]")
	public WebElement USERSBYCLIENT;

	@FindBy(xpath = "//a[contains(.,'support@hq.cpa.com')]")
	public WebElement SUPPORTHQ;

	@FindBy(xpath = "//h1[contains(.,'Manage Users')]/../../div[contains(.,'Select a user')][contains(.,'s name from the list below to view and edit user details, resend the user')][contains(.,'s email invitation, and delete or reactivate the user.')]")
	public WebElement MANAGEUSERSTITTLE;

	@FindBy(xpath = "//select[@class='ng-pristine ng-untouched ng-valid ng-not-empty']")
	public WebElement USERSBYCLIENTPAGINATION;

	@FindBy(xpath = "//label[contains(.,'Search By Client Name')]")
	public WebElement SEARCHBYCLIENTNAME;

	@FindBy(xpath = "//label[contains(.,'Filter by Status')]")
	public WebElement FILTERBYSTATUS;

	@FindBy(xpath = "//label[contains(.,'Filter by Subscription')]")
	public WebElement FILTERBYSUBSCRIPTION;

	@FindBy(xpath = "//span[contains(.,'Client Name')]")
	public WebElement CLIENTNAME;

	@FindBy(xpath = "//span[contains(.,'Client Type')]")
	public WebElement CLIENTTYPE;

	@FindBy(xpath = "//span[contains(.,'Client Status')]")
	public WebElement CLIENTSTATUS;

	@FindBy(xpath = "//span[contains(.,'Client Subscription')]")
	public WebElement CLIENTSUBSCRIPTION;

	@FindBy(xpath = "//span[contains(.,'Vertex Cloud Access Level')]")
	public WebElement TAXCENTRATACCESSLEVEL;

	@FindBy(xpath = "//h2[contains(.,'Enrolled Client Service Request Queue')]")
	public WebElement ENROLLEDCLIENTSERREQQUE;

	@FindBy(xpath = "//input[@id='SupportRequest_SalesforceSupportDetails_CompanyName']")
	public WebElement SUPPORTREQUEST;

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]")
	public WebElement TABLEROW;

	@FindBy(xpath = "//span[contains(text(),'Calculate Taxes')]")
	public WebElement taxesHover;

	@FindBy(xpath = "//span[contains(text(),'Manage Tax Returns')]")
	public WebElement ReturnsHover;

	@FindBy(xpath = "//span[contains(text(),'Retail Tax Extract')]")
	public WebElement RTEHover;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement SettingsHover;

	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	public WebElement ReportsHover;

	@FindBy(xpath = "//span[contains(text(),'Rate File')]")
	public WebElement RateFileHover;

	@FindBy(xpath = "//h3[contains(.,'ATTENTION')]")
	public WebElement ATTENTION;

	@FindBy(xpath = "//button[contains(.,'Accept')]")
	public WebElement ACCEPT;

	@FindBy(xpath = "//div/p/strong[text()='no taxability reports']")
	public WebElement NOTAXABILITYREPORT;

	@FindBy(xpath = "//select[@id='AdjustmentFileFormat']")
	public WebElement FILEFORMAT;

	@FindBy(xpath = "//a/span[contains(text(),'Reports')]")
	public WebElement REPORTSTAB;

	@FindBy(xpath = "//a[@title='Billing Report']")
	public WebElement BILLINGREPORT;

	@FindBy(xpath = "//td[text()='https://FullqaConnect.vertexsmb.com/vertex-ws/services/CalculateTax70']")
	public WebElement FULLERPCONNECTORURLQA;

	@FindBy(xpath = "//td[text()='https://StdqaConnect.vertexsmb.com/vertex-ws/services/CalculateTax70']")
	public WebElement STANDARDERPCONNECTORURLQA;

	@FindBy(xpath = "//td[text()='https://FullqaConnect.vertexsmb.com/vertex-ws/services/LookupTaxAreas70']")
	public WebElement FULLADDRESSLOOKUPURLQA;

	@FindBy(xpath = "//td[text()='N/A']")
	public WebElement STANDARDADDRESSLOOKUPURLQA;

	@FindBy(xpath = "//a[contains(.,'Export')]")
	public WebElement EXPORT;

	@FindBy(xpath = "//a[contains(.,'EXPORT')]")
	public WebElement CONFIGURATIONEXPORT;

	@FindBy(xpath = "//td[text()='https://FullstageConnect.vertexsmb.com/vertex-ws/services/CalculateTax70']")
	public WebElement FULLERPCONNECTORURLSTAGE;

	@FindBy(xpath = "//td[text()='https://StdstageConnect.vertexsmb.com/vertex-ws/services/CalculateTax70']")
	public WebElement STANDARDERPCONNECTORURLSTAGE;

	@FindBy(xpath = "//td[text()='https://FullstageConnect.vertexsmb.com/vertex-ws/services/LookupTaxAreas70']")
	public WebElement FULLADDRESSLOOKUPURLSTAGE;

	@FindBy(xpath = "//td[text()='N/A']")
	public WebElement STANDARDADDRESSLOOKUPURLSTAGE;

	@FindBy(xpath = "//p[contains(.,'An error has occurred.')]")
	public WebElement ANERRORHASOCCURED;

	@FindBy(xpath = "//a[contains(.,'Ok')]")
	public WebElement OKERROR;

	@FindBy(xpath = "//h1[contains(.,'My Users')]")
	public WebElement MYUSERSTITLE;

	@FindBy(xpath = "//dl/dt[contains(.,'Services Agreement')]/../dd[1]/a[not(@class='ng-hide')]")
	public WebElement SERVICEAGREEMENTVIEWPDFPRO;

	@FindBy(xpath = "//dl/dt[contains(.,'Services Agreement')]/../dd[1]/a[not(@class='ng-hide')]")
	public WebElement SERVICEAGREEMENTVIEWPDFPRE;

	@FindBy(xpath = "//dl/dt[contains(.,'Services Agreement')]/../dd[2]/a")
	public WebElement TERMSSERVICEVIEWPDF;

	@FindBy(xpath = "//a[contains(text(),'View All Connectors')]")
	public WebElement VIEWALLCONNECTORSBTN;

	@FindBy(xpath = "//div/a[contains(text(),'View All Connectors')]")
	public WebElement VIEWALLCONNECTORSBTN1;

	@FindBy(xpath = "(//h4[contains(.,'Subscription')])[2]")
	public WebElement Subscription;

	@FindBy(xpath = "//h2[contains(.,'My Connectors')]")
	public WebElement MyConnectors;

	@FindBy(xpath = "//strong[contains(.,'Accelerator - Oracle ERP Cloud')]")
	public WebElement ACCELERATORORACLEERPCLOUD;

	@FindBy(xpath = "//a[@id='launchOracleErpCloudSite']")
	public WebElement LAUNCHVERTEXACCELERATORBTN;

	@FindBy(xpath = "//span[contains(text(),'Trusted ID:')]")
	public WebElement TRUSTEDID;

	@FindBy(xpath = "//strong[contains(.,'no taxability reports')]")
	public WebElement NOTAXABILITYREPORTS;

	@FindBy(xpath = "//h3[contains(text(),'Confirm Password to Save')]")
	public WebElement USERPASSWORDCONFIRM;

	@FindBy(xpath = "//a[contains(.,'Help Center')]")
	public WebElement CPAHELPCENTER;

	@FindBy(xpath = "//button[contains(.,'Search')]")
	public WebElement SEARCH;

	@FindBy(xpath = "//a[@href='/support/whatsnew']")
	public WebElement WHATSNEWLINKCPA;

	@FindBy(xpath = "//a[@href='/Admin/VisitHelpCenter/WhatsNew']")
	public WebElement WHATSNEWLINKTAXCENTRAL;

	@FindBy(xpath = "//a[@href='/visithelpcenter/whatsnew']")
	public WebElement WHATSNEWLINKTC;

	@FindBy(xpath = "//h1[contains(.,'What')][contains(.,'s New')]")
	public WebElement WHATSNEWTITLE;

	@FindBy(xpath = "//input[@id='search-input']")
	public WebElement SEARCHWHATSNEW;

	@FindBy(xpath = "//span[contains(text(),'Address Cleansing')]")
	public WebElement addressCleansing;

	@FindBy(xpath = "//div[1]/div[3]/div[1]/div[1]/ul/li[6]/a/i")
	public WebElement addressCleansingIcon;

	@FindBy(xpath = "//h1[contains(text(),'Address Cleansing')]")
	public WebElement addressCleansingheader;

	@FindBy(xpath = "//h1/i")
	public WebElement addressCleansingheaderIcon;

	@FindBy(xpath = "//div[contains(text(),'Address Cleansing allows you to upload and validate addresses in order to ensure accurate sales and use tax calculations.  This option is subject to an additional fee based upon consumption.')]")
	public WebElement addressCleansingtext;

	@FindBy(linkText = "+ Upload New File")
	public WebElement addressCleansinguploadNewfilelnk;

	@FindBy(xpath = "//table[@id='addressValidationFilesTable']/thead/tr/th[@sort-header='date']/div/span[contains(text(),'File Upload Date')]")
	public WebElement addressCleansinggridfileuploadDate;

	@FindBy(xpath = "//table[@id='addressValidationFilesTable']/thead/tr/th[@sort-header='originalFile.displayName']/div/span[contains(text(),'File Name')]")
	public WebElement addressCleansinggridfileName;

	@FindBy(xpath = "//th[contains(text(),'Original File ')]")
	public WebElement originalFIle;

	@FindBy(xpath = "//th[contains(text(),'Success Report')]")
	public WebElement successReport;

	@FindBy(xpath = "//th[contains(text(),'Error Report')]")
	public WebElement errorReport;

	@FindBy(xpath = "//h3[contains(text(),'ADD-ON OPTION')]")
	public WebElement addOnOption;

	@FindBy(xpath = "//strong[contains(text(),'Address Cleansing is an add-on option.')]")
	public WebElement addOnOptiontext;

	@FindBy(id = "cancel-address-validation-upload")
	public WebElement cancel;

	@FindBy(id = "address-validation-upload")
	public WebElement Continue;

	@FindBy(xpath = "//div[1]/h1")
	public WebElement uploadaddressCleansingfileheader;

	@FindBy(linkText = "Download File Format Template")
	public WebElement downlaodfileformatlnk;

	@FindBy(xpath = "//span[contains(text(),'Update Notification')]")
	public WebElement updateNotificationLink;

	@FindBy(xpath = "//h1[contains(text(),'Edit Notifications (System Generated Messages)')]")
	public WebElement editNotification;

	@FindBy(xpath = "//form/fieldset/div/div[1]/div/select")
	public WebElement selectNotificationmsg;

	@FindBy(id = "title")
	public WebElement subjectLine;

	@FindBy(name = "description")
	public WebElement messageBody;

	@FindBys({ @FindBy(xpath = "//form/fieldset/div/div[1]/div/select/option") })
	public List<WebElement> notificationsCount;

	@FindBys({ @FindBy(xpath = "//table/tbody/tr[@class='ng-scope']") })
	public List<WebElement> adjustmentFilestable;

	@FindBy(xpath = "//a[contains(text(),'Transaction - US and Canada')]")
	public WebElement COMPLIANCEFILELINK;

	@FindBy(xpath = "//h1[contains(.,'Compliance File')]")
	public WebElement COMPLIANCEFILEPAGETITLE;

	@FindBy(xpath = "//a[contains(text(),'International Compliance File')]")
	public WebElement INTERNATIONALCOMPLIANCEFILELINK;

	@FindBy(xpath = "//div[2]/span[2]")
	public WebElement ConnectorsAPIKeyblank;

	@FindBy(xpath = "//div[3]/span[2]")
	public WebElement ConnectorsAPIPasswordblank;

	@FindBy(id = "emailNotification")
	public WebElement emailNotification;

	@FindBy(xpath = "//a[@href='/Admin/VisitHelpCenter/WhatsNew']/span")
	public WebElement ADMINWHATSNEWLINKTAXCENTRAL;

	@FindBy(xpath = "//h3[contains(text(),'This Customer has no committed Companies')]")
	public WebElement NOTRANSACTION;

	@FindBy(xpath = "//h1[contains(.,'Transaction Report')]")
	public WebElement TRANSACTIONREPORTTXT;

	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[text()='Go Live Date']")
	public WebElement GOLIVEDTCOLUMN;

	@FindBy(xpath = "//label[contains(.,'Company Status')]/../input")
	public WebElement DSBCompanystatus;

	@FindBy(xpath = "//a[@href='mailto:CloudSupport@Vertexinc.com']")
	public WebElement CONTACTSUPPORTHREF;

	@FindBy(xpath = "//input[@id='name']")
	public WebElement CLIENT;

	@FindBy(xpath = "//strong[contains(.,'Name:')]/../../div[2]")
	public WebElement MerchantName;

	@FindBy(xpath = "//label[contains(.,'Zip/Postal Code*')]")
	public WebElement LOOKUPZIP;

	@FindBy(xpath = "//div/div[2]/p[1]/strong[1]")
	public WebElement subscriptionType;

	@FindBy(xpath = "//h2[contains(.,'Test Instance Environment')]")
	public WebElement testInstanceEnvLabel;

	@FindBy(xpath = "//h1[contains(.,'Configure Overview')]")
	public WebElement ConfigureOverViewlabel;

	@FindBy(xpath = "//a[contains(.,'Delete Test Company')]")
	public WebElement deleteTestCompanylabel;

	@FindBy(xpath = "//li/a[contains(text(),'Billing Reports')]")
	public WebElement billingReports;

	@FindBy(xpath = "//span[contains(text(),'Taxability Impact Report')]")
	public WebElement TaxabilityImpactReport;

	@FindBy(xpath = "//table/tbody/tr[2]/td[7]/a[contains(.,'Download')]")
	public WebElement transactionReportDownloadReport;

	@FindBy(xpath = "//div[2]/table/thead/tr/th[contains(.,'Transactions')]/../../../tbody/tr")
	public WebElement settingstransactionCount;

	@FindBy(xpath = "//h2[contains(.,'My Usage')]/../../div")
	public WebElement Myusagesection;

	@FindBy(xpath = "//p[contains(.,' The Vertex Cloud integration to Oracle ERP Cloud provides sales and consumer use tax calculations. Click the Integration Setup button below to continue.')]")
	public WebElement ERPVERBAGE;

	@FindBy(xpath = "//button[@ng-if='canSetupTaxLink']")
	public WebElement INTEGRATIONSETUP;

	@FindBy(xpath = "//div[@ng-if='canSetupTaxLink']/button[contains(.,'INTEGRATION SETUP')]")
	public WebElement ORACLECLOUDINTEGRATION;

	@FindBy(xpath = "//h1[contains(.,'Oracle ERP Cloud Integration')]")
	public WebElement ORACLECLOUDINTEGRATIONTITLE;

	@FindBy(xpath = "//a[@class='btn pull-right'][contains(.,'RETURN TO CONNECTORS')]")
	public WebElement RETURNTOCONNECTORS;

	@FindBy(xpath = "//h2[contains(.,'Oracle Configuration')]")
	public WebElement ORACLECONFIGURATION;

	@FindBy(xpath = "//label[contains(.,'Customer Name')]")
	public WebElement CUSTOMERNAME;

	@FindBy(xpath = "//input[@id='instance-id']")
	public WebElement CLOUDINSTANCENAME;

	@FindBy(xpath = "//select[@id='instance-type']")
	public WebElement INSTANCETYPE;

	@FindBy(xpath = "//label[contains(.,'Oracle Data Center')]")
	public WebElement ORACLEDATACENTERLABEl;

	@FindBy(xpath = "//label[contains(.,'ERP Cloud Username')]")
	public WebElement ERPCLOUDUSERNAMELABEL;

	@FindBy(xpath = "//label[contains(.,'ERP Cloud Password')]")
	public WebElement ERPCLOUDPASSWORDLABEL;

	@FindBy(xpath = "//label[contains(.,'Scheduler Enabled')]")
	public WebElement SCHEDULERENABLEDLABEL;

	@FindBy(xpath = "//div[contains(text(),'Service Subscriptions?')]")
	public WebElement SERVICESUBSCRIPTIONS;

	@FindBy(xpath = "//h2[contains(.,'Business Units')]")
	public WebElement BUSINESSUNITS;

	@FindBy(xpath = "//a[contains(.,'Add Business Unit')]")
	public WebElement ADDBUSINESSUNIT;

	@FindBy(xpath = "//button[contains(.,'Submit')]")
	public WebElement SUBMIT;

	@FindBy(xpath = "//select[@id='data-center']")
	public WebElement ORACLEDATACENTERSELECT;

	@FindBy(xpath = "//input[@id='cloud-username']")
	public WebElement CLOUDUSERNAMEINPUT;

	@FindBy(xpath = "//input[@id='cloud-password']")
	public WebElement CLOUDPASSWORDINPUT;

	@FindBy(xpath = "//input[@value='true']")
	public WebElement YES;

	@FindBy(xpath = "//div/i[@class='icon-remove icon-blue']")
	public WebElement Remove;

	@FindBy(xpath = "//p[contains(.,'You are about to remove an item.')]")
	public WebElement WarningPopup;

	@FindBy(xpath = "//input[@id='form-date']")
	public WebElement ENDDATE;

	@FindBy(xpath = "//a[contains(.,'Remove Item')]")
	public WebElement RemoveItem;

	@FindBy(xpath = "//input[@value='false']")
	public WebElement NO;

	@FindBy(xpath = "//a[contains(.,'Expired')]")
	public WebElement Expired;

	@FindBy(xpath = "//a[contains(.,'Active')]")
	public WebElement Active;

	@FindBy(xpath = "//label[contains(.,'Accounts Receivable')]/input")
	public WebElement ACCOUNTSRECEIVABLE;

	@FindBy(xpath = "//label[contains(.,'Accounts Payable')]/input")
	public WebElement ACCOUNTSPAYABLE;

	@FindBy(xpath = "//label[contains(.,'Source of Product Codes')]/../select")
	public WebElement SOURCEOFPRODUCTCODES;

	@FindBy(xpath = "//label[contains(.,'Source of Product Classes')]/../select")
	public WebElement SOURCEOFPRODUCTCLASSES;

	@FindBy(xpath = "//label[contains(.,'Source of Purchase Codes')]/../select")
	public WebElement SOURCEOFPURCHASECODES;

	@FindBy(xpath = "//label[contains(.,'Source of Purchase Classes')]/../select")
	public WebElement SOURCEOFPURCHASECLASSES;

	@FindBy(xpath = "//input[@id='business-name-1']")
	public WebElement BUSINESSUNITNAME1;

	@FindBy(xpath = "//input[@id='business-id-1']")
	public WebElement BUSINESSUNITID1;

	@FindBy(xpath = "//input[@id='business-name-2']")
	public WebElement BUSINESSUNITNAME2;

	@FindBy(xpath = "//input[@id='business-id-2']")
	public WebElement BUSINESSUNITID2;

	@FindBy(xpath = "//label[contains(.,'United States')]/input")
	public WebElement UNITEDSTATESINPUT;

	@FindBy(xpath = "//label[contains(.,'United States')]/input")
	public WebElement CANADAINPUT;

	@FindBy(xpath = "//h3[contains(.,'Reminder!')]")
	public WebElement REMINDER;

	@FindBy(xpath = "//p/a[contains(.,'Submit')]")
	public WebElement REMINDERSUBMIT;

	@FindBy(xpath = "//div[contains(.,'Your TaxLink username and password have been generated successfully and are listed below.')]")
	public WebElement SUCCESSERP;

	@FindBy(xpath = "//a[contains(.,'Vertex_ERP_Cloud_Taxlink_US_R2R_Configuration')]")
	public WebElement DOWNLOADLINK;

	@FindBy(xpath = "//span/strong[contains(.,'Active')]")
	public WebElement ACTIVE;

	@FindBy(xpath = "//ul/li/a[contains(.,'Active')]")
	public WebElement ACTIVEUSERLINK;

	@FindBy(xpath = "//ul/li/a[contains(.,'Pending')]")
	public WebElement PENDINGUSERLINK;

	@FindBy(xpath = "//div/strong[contains(.,'Card:')]")
	public WebElement CardNumber;

	@FindBy(xpath = "//div/strong[contains(.,'Exp. Date:')]")
	public WebElement CardExDate;

	@FindBy(xpath = "//div/strong[contains(.,'Address:')]")
	public WebElement BillingAddress;

	@FindBy(xpath = "//span/a[contains(.,'Expired')]")
	public WebElement EXPIRED;

	@FindBy(xpath = "//a[contains(.,'Export')]")
	public WebElement EXPORTBUTTON;

	@FindBy(xpath = "//button[contains(text(),'Generate Report')]")
	public WebElement GENERATEREPORT;

	@FindBy(xpath = " //h1[contains(.,'Tax Assist Rule Report')]")
	public WebElement TAXASSISTRULEREPORTHEADING;

	public final String SVECHANGES = "//button[contains(.,'Save Changes')]";

	@FindBy(xpath = "//a[@title='Quote and Purchase Order Report']")
	public WebElement QuotePurchaseOrderReport;

	@FindBy(xpath = "//strong[contains(.,'no Quote and PO reports')]")
	public WebElement NoQuotePurchaseOrder;

	@FindBy(xpath = "//a[contains(.,'What’s New')]")
	public WebElement Whatsnew;

	@FindBy(xpath = "//a/strong[text()='Cancel']")
	public WebElement SUBSCRIPTIONCANCEL;

	@FindBy(xpath = "//h2[contains(.,'Oracle cloud / Taxlink Integration')]")
	public WebElement ORACLECLOUD;

	@FindBy(xpath = "//label[contains(.,'TaxLink Username')]")
	public WebElement TAXLINKUSERNAME;

	@FindBy(xpath = "//label[contains(.,'TaxLink Password')]")
	public WebElement TAXLINKPASSWORD;

	@FindBy(xpath = "//table/tbody/tr[2]/td[2]/a[contains(.,'TaxAssistRuleReport')]")
	public WebElement TaxAssitRuleLinkUndoGoLive;

	@FindBy(xpath = "//table[@id='customerExceptionsTable']/tbody/tr[1]/td[4]/a[contains(.,'Download')]")
	public WebElement CustomerExceptionDownloadUndoGoLive;

	@FindBy(xpath = "//button[contains(.,'Create new transaction report')]")
	public WebElement createNewReport;

	@FindBy(xpath = "//table/tbody/tr[2]/td[7]/a")
	public WebElement TransactionsDownload;

	@FindBy(xpath = "//div[4]/div/div/div/p/strong[contains(.,'no taxability reports')]")
	public WebElement TaxabilityReportUndoGoLive;

	@FindBy(xpath = "//div[2]/table/thead/tr/th[2]/../../../tbody")
	public WebElement TransactionsCountUndoGoLive;

	@FindBy(xpath = "//a/span[contains(.,'What') and contains(.,'s New')]")
	public WebElement SYSADMINWHATSNEWLINKTAXCENTRAL;

	@FindBy(xpath = "//h2[contains(.,'My Billing & Subscription')]")
	public WebElement MyBillingSubscriptionsection;

	@FindBy(xpath = "//strong[contains(.,'Card:')]")
	public WebElement CardDetails;

	@FindBy(xpath = "//div/h4[contains(.,'Subscription')]/../p/strong[1]")
	public WebElement MerchantSubscription;

	@FindBy(xpath = "//h1[contains(.,'Contact Support')]")
	public WebElement Contactsupportlabel;

	@FindBy(xpath = "//p[contains(.,'Please contact your franchise representative.')]")
	public WebElement Franchsemessage;

	@FindBy(xpath = "//h2[contains(.,'My Billing & Subscription')]")
	public WebElement MyBillingSubscription;

	@FindBy(xpath = "//strong[contains(.,'Cancelled')]")
	public WebElement Cancelledtext;

	// FranchiseAdmin links
	@FindBy(xpath = "//h2[contains(.,'Enroll new Merchant')]")
	public WebElement ENROLLNEWCLIENTTITLE;

	@FindBy(xpath = "//h2[contains(.,'Enroll new Merchant')]/../p")
	public WebElement ENROLLNEWCLIENTTEXT;

	@FindBy(xpath = "//a[contains(.,' Enroll New Merchant')]")
	public WebElement ENROLLBUTTON;

	@FindBy(xpath = "//h2[contains(.,'Merchant Overview')]")
	public WebElement MerchantOverview;

	@FindBy(xpath = "//div[@class='well']/../..")
	public WebElement MerchantOverviewSection;

	@FindBy(xpath = "//a/span[contains(.,'Manage Merchants')]")
	public WebElement ManageMerchants;

	@FindBy(xpath = "//a/span[contains(.,'Collapse')]")
	public WebElement Collapse;

	@FindBy(linkText = "Services Agreement")
	public WebElement ServicesAgreementink;

	@FindBy(xpath = "//h1[contains(.,'Merchant Registration')]")
	public WebElement MerchantRegistrationTitle;

	@FindBy(xpath = "/h1[contains(.,'Manage Merchants')]")
	public WebElement ManageMerchantTitle;

	@FindBy(xpath = "//div[@class='span10']/../div")
	public WebElement MerchantsTEXT;

	@FindBy(xpath = "//div/label[contains(.,'Search by Merchant Name')]/../input[@type='text']")
	public WebElement MerchantSearchBox;

	@FindBy(xpath = "//div/label[contains(.,'Filter by Status')]/../select")
	public WebElement FilterbyStatus;

	@FindBy(xpath = "//div/span[contains(.,'Merchant Name')]")
	public WebElement MerchantNameColumn;

	@FindBy(xpath = "//div/span[contains(.,'Phone')]")
	public WebElement PhoneColumn;

	@FindBy(xpath = "//div/span[contains(.,'Registration Status')]")
	public WebElement RegistrationStatusColumn;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Manage API Credentials')]")
	public WebElement ManageAPICredentialsColumn;

	@FindBy(xpath = "//h2[contains(.,'Enroll new Merchant')]/../p")
	public WebElement EnrollNewMerchantInfo;

	@FindBy(xpath = "//h1[contains(.,'Configuration Report')]")
	public WebElement CONFIGURATIONREPORTWITHOUTCOMPANY;

	@FindBy(xpath = "//h1[contains(.,'Taxability Reports')]")
	public WebElement TAXABILITYREPORTHEADER;

	@FindBy(xpath = "//h1[contains(.,'Transaction Report')]")
	public WebElement TRANSACTIONREPORTHEADER;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Actions')]")
	public WebElement ActionsColumn;

	@FindBy(xpath = "//div/h2[contains(.,'Public Api Credentials')]")
	public WebElement PublicApiCredentialsTitle;

	@FindBy(xpath = "//span[contains(.,'Settings')]")
	public WebElement ECWSettingslink;

	@FindBy(xpath = " //h2[contains(.,'My Company')]")
	public WebElement ECWmycompany;

	@FindBy(xpath = "//strong[contains(.,'Name:')]")
	public WebElement ECWmyComName;

	@FindBy(xpath = "//strong[contains(.,'Address:')]")
	public WebElement ECWmyComaddress;

	@FindBy(xpath = "//strong[contains(.,'Phone:')]")
	public WebElement ECWmyComPhone;

	@FindBy(xpath = "//a[contains(.,'Edit My Company')]")
	public WebElement EditofMycompany;

	@FindBy(xpath = "//h2[contains(.,'Exemption certificate wizard')]")
	public WebElement ECWHeaderText;

	@FindBy(xpath = "//div[@class='eqheight-well-col has-btn-block ng-scope']//div[2]//div[1]//strong[contains(.,'URL:')]")
	public WebElement ECWURL;

	@FindBy(xpath = "//strong[contains(text(),'Authorization URL:')]")
	public WebElement AUTHORIZATIONURL;

	@FindBy(xpath = "//strong[contains(.,'Client Code:')]")
	public WebElement ECWClientCode;

	@FindBy(xpath = "//h3[contains(.,'Exemption Certificate Wizard Integration')]")
	public WebElement ECWIntegrationText;

	@FindBy(xpath = "//h3[contains(text(),'Exemption Certificate Wizard Integration')]/../div/div/strong[contains(.,'Client ID:')]")
	public WebElement ECWInClientID;

	@FindBy(xpath = "//h3[contains(text(),'Exemption Certificate Wizard Integration')]/../div/div/strong[contains(.,'Client Secret:')]")
	public WebElement ECWInClientsecret;

	@FindBy(xpath = "//h3[contains(text(),'O Series Integration')]")
	public WebElement OseriesIntegrationText;

	@FindBy(xpath = "//h3[contains(text(),'O Series Integration')]/../div/div/strong[contains(.,'Client ID:')]")
	public WebElement ECWOseriesClientID;

	@FindBy(xpath = " //h3[contains(text(),'O Series Integration')]/../div/div/strong[contains(.,'Client Secret:')]")
	public WebElement ECWOseriesClientSecret;

	@FindBy(xpath = "//span[contains(.,'Contact Support')]")
	public WebElement contactsupport;

	@FindBy(xpath = "//a[contains(.,'Manage Retail Tax Extract')]")
	public WebElement ManageRetailTaxExtract;

	@FindBy(xpath = "//button[text()='Create Extract']")
	public WebElement CREATEEXTRACT;

	@FindBy(xpath = "//h1[contains(.,'Manage Retail Tax Extract')]")
	public WebElement MANAGERETAILTAXEXTRACTTEXT;

	@FindBy(xpath = "//a[contains(.,' Manage Bracket Schedule')]")
	public WebElement MANAGEBRACKETSCHEDULE;

	@FindBy(xpath = "//h1[contains(.,'Manage Bracket Schedule')]")
	public WebElement MANAGEBRACKETTEXT;

	@FindBy(xpath = "//div/p/strong[contains(.,'Trusted ID:')]/../../../div[@class='span10']")
	public WebElement TrustedID;

	@FindBy(xpath = "//div[@class='modal-backdrop fade in']")
	public WebElement BACKDROPFADE;

	@FindBy(xpath = "//a/strong[text()='Exit']")
	public WebElement EXIT;

	// 20189 Capability to on-board more than one Oracle ERP cloud instance on a
	// single Vertex cloud instance
	@FindBy(xpath = "//h1[contains(.,'Oracle ERP Cloud Integration')]")
	public WebElement ORACLEERPCLOUDINTEGRATIONTESXT;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Date Added')]/../th[contains(.,'Oracle Instance Name / Data Center')]/../th[contains(.,'Oracle Instance Type')]")
	public WebElement ORACLEINSTANCESTABLE;

	@FindBy(xpath = "//a[contains(.,'RETURN TO CONNECTORS')]")
	public WebElement RETURNTOCONNECTORSBUTTON;

	@FindBy(xpath = "//a[contains(.,'ADD CONNECTION')]")
	public WebElement ADDCONNECTIONSBUTTON;

	@FindBy(xpath = "//a[@title='International Transaction Report']")
	public WebElement InternationalTransactionReport;

	@FindBy(xpath = "//a[contains(.,'Return to Oracle Instances')]")
	public WebElement RETURNTOORACLEINSTANCES;

	@FindBy(xpath = "//input[@id='customer-name']")
	public WebElement ORACLECLIENTNAME;

	@FindBy(xpath = "//button[contains(.,'Show')]")
	public WebElement Show;

	@FindBy(xpath = "//button[contains(.,'Copy')]")
	public WebElement Copy;

	@FindBy(xpath = "//a[contains(text(),'View Errors')]")
	public WebElement ViewErrors;

	@FindBy(xpath = "//h2[contains(.,'Returns Integration')]")
	public WebElement ReturnsOnlyIntegration;
	
	@FindBy(xpath = "//h1[contains(.,'View All Connectors')]")
	public WebElement VIEW_ALL_CONNECTORS;
	
	@FindBy(xpath = "//p[@class='lead'][contains(.,'OSeries SOAP')]")
	public WebElement OSERIES_SOAP;
	
	@FindBy(xpath = "//p[@class='lead'][contains(.,'Public REST API Connectors')]")
	public WebElement PUBLIC_REST_API_CONNECTORS;
	
	@FindBy(xpath = "//h2[contains(.,'Download Connector')]")
	public WebElement DOWNLOAD_CONNECTOR;
	
	@FindBy(xpath = "//a[contains(@id,'btnConnector')]")
	public WebElement BTNCONNECTOR;
	
	@FindBy(xpath = "//h2[contains(.,'Big Commerce Integration')]")
	public WebElement BIG_COMMERCE_INTEGRATION;
	
	@FindBy(xpath = "//button[@type='button'][contains(.,'INTEGRATION SETUP')]")
	public WebElement BTNINTEGRATION_SETUP;
	
	@FindBy(xpath = "//h2[contains(.,'QuickBooks Online Integration')]")
	public WebElement QUICKBOOKS_ONLINE_INTEGRATION;
	
	@FindBy(xpath = "//button[@type='button'][contains(.,'Launch Site')]")
	public WebElement BTNLAUNCH_SITE;

	@FindBy(xpath = "//h1[contains(.,'Returns Integration')]")
	public WebElement ReturnsOIntegration;

	@FindBy(xpath = "//p[contains(.,'The Vertex Cloud Returns Integration to a supported ERP provides sales')]")
	public WebElement ReturnsOnlyText;

	@FindBy(xpath = "//button[contains(.,'Returns Setup')]")
	public WebElement ReturnsOnlySetup;

	@FindBy(xpath = "//h4[contains(.,'Returns Integration Instances')]")
	public WebElement ROIntegrationInstances;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'Date Added')]")
	public WebElement DateAdded;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'Returns Only Instance Type')]")
	public WebElement ROInstanceType;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Action')]")
	public WebElement Action;

	@FindBy(xpath = "//div[@class='table-responsive']/table/thead/tr/th[contains(.,'Action')]")
	public WebElement ACTION;

	@FindBy(xpath = "//div[@class='table-responsive']/table/thead/tr/th[contains(.,'Action')]")
	public WebElement RETURNSINTEGRATIONACTION;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'Status')]")
	public WebElement Statuscolumn;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'Returns Instance Name')]")
	public WebElement RETURNSINSTANCENAME;

	@FindBy(xpath = "//a[contains(.,'ADD CONNECTION')]")
	public WebElement ADDCONNECTION;

	@FindBy(xpath = "//h2[contains(.,'API Configuration')]")
	public WebElement ERPCONFIGURATION;

	@FindBy(xpath = "//select[@id='select-erp']")
	public WebElement AVAILABLEERPS;

	@FindBy(xpath = "//input[@id='erp-description']")
	public WebElement ERPDESCRIPTION;

	@FindBy(xpath = "//button[contains(.,'Submit')]")
	public WebElement SUBMITERP;

	public final String REQUESTPROCESSING_XPATH = "//h3[@id='removeConfirmHeader1']";

	@FindBy(xpath = "//a[contains(.,'Return to Oracle Instances')]")
	public WebElement ReturntoOracleinstances;

	@FindBy(xpath = "//div[@class='right-col dashboard-col']/div/div/div/div/a[contains(.,'Returns Only API Instances')]")
	public WebElement ReturnsOnlyAPIInstances;

	@FindBy(xpath = "//a[contains(.,'RETURN TO CONNECTORS')]")
	public WebElement ReturnToConnectors;

	@FindBy(xpath = "//a[contains(.,'Compliance - International')]")
	public WebElement ComplianceInternationalLink;

	@FindBy(xpath = "//a[contains(.,'Customer Exemption')]")
	public WebElement CustomerExceptionLink;

	@FindBy(xpath = "//a[contains(.,'Validation Errors')]")
	public WebElement ValidationErrors;

	@FindBy(xpath = "//span[contains(.,'View and correct validation errors.')]")
	public WebElement ValidationErrorsText;

	@FindBy(xpath = "//h4/a[contains(.,'Taxability')]")
	public WebElement TaxabilityReport;

	@FindBy(xpath = "//span[contains(.,'Identify tax exceptions that are relevant to your business. View the taxability of product categories in the configured jurisdictions.')]")
	public WebElement TaxabilityReportText;

	@FindBy(xpath = "//span[contains(.,'View details about configured exceptions for companies, jurisdictions, customer names/codes, reason types, or date ranges.')]")
	public WebElement CustomerExceptionText;

	@FindBy(xpath = "//span[contains(.,'Data Integrity')]")
	public WebElement DATAINTEGRITY;

	@FindBy(xpath = "//span[contains(.,'Global Compliance')]")
	public WebElement GLOBALCOMPLIANCE;

	@FindBy(xpath = "//p[contains(.,'The Global Compliance portal allows you to prepare and submit indirect tax reports ensuring the transparency and auditability of the filing process, click the Launch Site button below to continue.')]")
	public WebElement GLOBALCOMPLIANCETEXT;

	@FindBy(xpath = "//h2[contains(.,'My OAuth Credentials')]")
	public WebElement MYOAUTHCREDENTIALSHEADERTEXT;

	@FindBy(xpath = "//p[contains(.,'Using Vertex Cloud credentials, you can manage needed credentials and passwords within your respective systems.')]")
	public WebElement MYOAUTHCREDENTIALSTEXT;

	@FindBy(xpath = "//p/strong[contains(.,'Total Credentials:')]")
	public WebElement TOTALCREDENTIALS;

	@FindBy(xpath = "//a[contains(@class,'btn btn-primary')][contains(text(),'View All Credentials')]")
	public WebElement VIEWALLCREDENTIALS;

	@FindBy(xpath = "//strong[contains(text(),'Services Agreement')]")
	public WebElement SERVICESAGREEMENT;

	@FindBy(xpath = "//a[contains(@ng-click,'vm.downloadFile(vm.data.clientSettings.company.negotiatedContractPublicFileId)')]")
	public WebElement VIEWPDFLINK;

	@FindBy(xpath = "//h2[contains(.,'My Users')]")
	public WebElement MYUSERS;

	@FindBy(xpath = "//li/strong[contains(.,'Active')]/..")
	public WebElement ACTIVEUSER;

	@FindBy(xpath = "//ul[@class='inline']//li[2]")
	public WebElement PENDINGUSERS;

	@FindBy(xpath = "//ul[contains(@class,'inline')]//li[3]")
	public WebElement DELETEDUSERS;

	@FindBy(xpath = "//a[@class='btn btn-primary'][contains(text(),'View All Users')]")
	public WebElement VIEWALLUSERSBUTTON;

	@FindBy(xpath = "//a[@class='btn btn-primary ng-scope'][contains(text(),'Add New User')]")
	public WebElement ADDNEWUSERBUTTON;

	@FindBy(xpath = "//a[contains(text(),'Launch Site')]")
	public WebElement LAUNCHSITE;

	@FindBy(xpath = "//button[@id='log-out']")
	public WebElement ECWLOGOUT;

	@FindBy(xpath = "//a/span[contains(.,'EC Wizard')]")
	public WebElement ECWIZARD;

	@FindBy(xpath = "//thead/tr/th[3]/../../../tbody/tr/td/a[contains(.,'All Flex Fields')]")
	public WebElement AllFlexFields;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Tax Assist')]")
	public WebElement TaxassistantHeader;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Custom Categories')]/span/a[contains(.,'+ Add')]")
	public WebElement AddCustomCategory;

	@FindBy(xpath = "//h3[contains(.,'This Customer has no Companies')]")
	public WebElement NOTAXPAYERTEXT;

	@FindBy(xpath = "//h1[contains(.,'Configure Overview')]")
	public WebElement CONFIGOVERVIEWHEADING;

	@FindBy(xpath = "//p[contains(.,'The Vertex Cloud Returns Integration to a supported ERP provides sales and consumer use tax calculations. Click the Returns Setup button below to continue.')]")
	public WebElement RETURNSONLYVERBAGE;

	@FindBy(xpath = "//a[@id='confirm-remove-customer']")
	public WebElement DELETERETURNSINTEGRATION;

	@FindBy(xpath = "//a/strong[contains(.,'cancel')]")
	public WebElement CANCELLINK;

	@FindBy(xpath = "//label[contains(.,'Available APIs')]")
	public WebElement AVAILABLEAPITEXT;

	@FindBy(xpath = "//label[contains(.,'API Description')]")
	public WebElement APIDESCRIPTION;

	@FindBy(xpath = "//div[@class='right-col dashboard-col']/div/div/div/div/a[contains(.,'Return To Returns Instances')]")
	public WebElement RETURNTORETURNSINSTACES;

	@FindBy(xpath = "//div[@class='right-col dashboard-col']/descendant::div[@class='span12']/a[contains(.,'Return To Returns Instances')]")
	public WebElement RETURNTORETURNSINSTACESLEFT;

	@FindBy(xpath = "//span[contains(.,'- No transactions were found within the selected date range.')]")
	public WebElement NOTRANSACTIONSERROR;

	@FindBy(xpath = "//p[contains(.,'Need more information? Please refer to the following article in the')]")
	public WebElement DOWNLOADRATEFILETEXT;

	@FindBy(xpath = "//p[contains(.,'OSeries SOAP')]")
	public WebElement OSERIESSOAPTEXT;

	@FindBy(xpath = "//p[contains(.,'Public REST API Connectors')]")
	public WebElement RESTAPICONNECTORS;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Public Interface Name')]")
	public WebElement PUBLICINTERFACENAME;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Endpoint URL')]")
	public WebElement ENDPOINTURL;

	@FindBy(xpath = "//div/h2[contains(.,'Current Pipeline')]/../p/span[1]/span/a[contains(.,' Shared Services Clients')]")
	public WebElement SHAREDSERVICESDASHBOARDLINK;

	@FindBy(xpath = "//a[contains(.,'Login')]")
	public WebElement LOGINLINK;

	@FindBy(xpath = "//small[contains(.,'© 2020 Vertex, Inc. All rights reserved.')]")
	public WebElement ALLRIGHTSHELPCENTER;

	@FindBy(xpath = "//footer/div/div/div[contains(.,'© 2020 Vertex, Inc. All rights reserved.')]")
	public WebElement ALLRIGHTSRESERVEDHELPCENTER;

	@FindBy(xpath = "//div[@class='site-header__left']/div/div[contains(.,'Vertex® Connector for SAP® Concur®')]")
	public WebElement VERTEXSAPHEADER;

	@FindBy(xpath = "//h1[contains(.,'Setup Vertex® Indirect Tax for Concur® Invoice')]")
	public WebElement SAPCONCURHEADING;

	@FindBy(xpath = "//p[contains(.,'New to Vertex and need to talk to us? –')]/span/a[contains(.,'click here')]")
	public WebElement CONCURCLICKHERE;

	@FindBy(xpath = "//p[contains(.,'Already enrolled with Vertex and ready to get connected? Enter your Vertex issues Trusted ID and Connector URL, then click Connect')]")
	public WebElement ALREADYENROLLED;

	@FindBy(xpath = "//label[contains(.,'Connector URL *')]")
	public WebElement CONCURCONNECTORURL;

	@FindBy(xpath = "//h5[contains(.,'Vertex provides you with your Connector URL once enrollment is complete.')]")
	public WebElement CONNECTORURLTEXT;

	@FindBy(xpath = "//input[@id='connectorUrl']")
	public WebElement CONNECTORURLINPUT;

	@FindBy(xpath = "//label[contains(.,'Trusted ID *')]")
	public WebElement CONCURTRUSTEDID;

	@FindBy(xpath = "//h5[contains(.,'Vertex provides you with your Trusted ID once enrollment is complete.')]")
	public WebElement TRUSTEDIDTEXT;

	@FindBy(xpath = "//input[@id='trustedId']")
	public WebElement TRUSTEDIDINPUT;

	@FindBy(xpath = "//form/button[@id='connect']")
	public WebElement CONCURCONNECTBUTTON;

	@FindBy(xpath = "//h4[contains(.,'Vertex® Indirect Tax for Concur® Invoice validates consumer use for invoices. Ensure every transaction applies the most accurate tax rules, rates, and exceptions automatically with Vertex® Indirect Tax for Concur® Invoice.')]")
	public WebElement VERTEXSAPCONCURTEXT;

	@FindBy(xpath = "//h2[contains(.,'Advanced')]")
	public WebElement ORACLEERPADVANCEDHEADING;

	@FindBy(xpath = "//a[contains(.,'Advanced Configuration')]")
	public WebElement ADVANCEDCONFIGURATION;

	@FindBy(xpath = "//div/img[@alt='Vertex Logo']")
	public WebElement VERTEXLOGO;

	@FindBy(xpath = "//div/div/div[contains(.,'TaxLink')]")
	public WebElement TAXLINKLOGO;

	@FindBy(xpath = "//p[contains(.,'You currently have')]/strong[contains(.,'no transaction reports')]")
	public WebElement NOTRANSACTIONSTOVIEW;

	@FindBy(xpath = "//a[contains(.,'Compliance - US and Canada')]")
	public WebElement ComplianceUSCanadaLink;

	@FindBy(xpath = "//div[contains(.,'Invalid username or password.')]/../div/strong[contains(.,'Error:')]")
	public WebElement InvalidUsernamepassword;

	@FindBy(xpath = "//a[contains(.,'D365 for Operations')]")
	public WebElement D365CONNECTOR;

	@FindBy(xpath = "//div/ul/li/span/a[contains(.,'D365 Retail')]")
	public WebElement D365RETAILCONNECTOR;

	@FindBy(xpath = "//a[contains(.,'Dynamics AX')]")
	public WebElement DYNAMICSAX;

	@FindBy(xpath = "//div/ul/li/span/a[contains(.,'Dynamics GP')]")
	public WebElement DYNAMICSGP;

	@FindBy(xpath = "//div/ul/li/span/a[contains(.,'QuickBooks')]")
	public WebElement QUICKBOOKS;

	@FindBy(xpath = "//a[contains(.,'Sage 100')]")
	public WebElement SAGE100;

	@FindBy(xpath = "//a[contains(.,'Sage 300')]")
	public WebElement SAGE300;

	@FindBy(xpath = "//a[contains(.,'SAP Hybris')]")
	public WebElement SAPHYBRIS;

	@FindBy(xpath = "//a[contains(.,'Vertex Tax Links for Episerver')]")
	public WebElement VERTEXTAXLINKSFOREPISERVER;

	@FindBy(xpath = "//h2[contains(.,'Download Connector')]")
	public WebElement DOWNLOADCONNECTORHEADING;

	@FindBy(xpath = "//p[contains(.,'The Vertex Cloud connectors provides direct integration between Vertex Cloud and multiple ERP systems and eCommerce platforms.')]")
	public WebElement DOWNLOADCONNECTORTEXT;

	@FindBy(id = "btnConnector")
	public WebElement CONNECTORSBUTTON;

	@FindBy(xpath = "//h1[contains(.,'Login to myVertex Account')]")
	public WebElement MYVERTEXACCOUNTHEADING;

	@FindBy(xpath = "//div[@class='span12']/div[contains(.,'Your configuration is not available for posting transactions to your journal.')]")
	public WebElement NOCONFIGWARNING;

	@FindBy(xpath = "//a[@class='ng-binding']")
	public List<WebElement> allLinksFromReportsPage;

	@FindBy(xpath = "//a[contains(.,'CPAsupport@VertexInc.com')]")
	public WebElement CONTACTSUPPORTMAIL;

	@FindBy(xpath = "//a/span[contains(.,'Vertex Cloud VARS')]")
	public WebElement VERTEXCLOUDVARS;

	@FindBy(xpath = "//div/span[contains(.,'Registration Date')]")
	public WebElement REGISTRATIONDATE;

	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement VERTEXVARSEARCH;

	@FindBy(xpath = "//strong[contains(.,'In order for you to properly configure your Quickbooks Enterprise API, you will need to install the Ground2Cloud connector.')]")
	public WebElement QBDTEXT1;

	@FindBy(xpath = "//a[contains(.,'CloudSupport@vertexinc.com')]")
	public WebElement QBDCONTACTSUPPORTLINK;

	@FindBy(xpath = "//strong[contains(.,'to navigate to the MyVertex website where you can log in with your Vertex client account information and access connector documentation and product downloads.')]")
	public WebElement QBDTEXT2;

	@FindBy(xpath = "//a[contains(.,'Click here')]")
	public WebElement QBDCLICKHERE;

	@FindBy(xpath = "//input[@class='input sfdc_usernameinput input']")
	public WebElement VERTEXACCOUNTUSERNAME;

	@FindBy(xpath = "//label[contains(.,'QBE Client Id')]/../input[@ng-change='checkReadyForSumbit()']")
	public WebElement QBECLIENTID;

	@FindBy(xpath = "//h1[contains(.,'OAuth Credentials')]")
	public WebElement OAuthCredentialsHEADER;

	@FindBy(xpath = "//span[contains(.,'Credential Usage')]")
	public WebElement CredentialUsage;

	@FindBy(xpath = "//span[contains(.,'Client Id')]")
	public WebElement ClientId;

	@FindBy(xpath = "//span[contains(.,'Description')]")
	public WebElement Description;

	@FindBy(xpath = "//span[contains(.,'Created By')]")
	public WebElement CreatedBy;

	@FindBy(xpath = "//th[contains(.,'Actions')]")
	public WebElement Actions;

	@FindBy(xpath = "//a[contains(.,'Return to Settings')]/../a[@class='btn pull-right']")
	public WebElement RIGHTReturntoSettings;

	@FindBy(xpath = "	//a[contains(.,'Return to Settings')]/../a[@class='btn pull-left']")
	public WebElement leftReturntoSettings;

	@FindBy(xpath = "//a[contains(.,'Create New Credential')]")
	public WebElement CreatNewCredential;

	@FindBy(xpath = "//p[contains(.,'Add Credential')]")
	public WebElement AddCredential;

	@FindBy(xpath = "//span[contains(.,'Credential Details')]")
	public WebElement CredentialDetails;

	@FindBy(xpath = "//label[contains(.,'Credential Usage')]/../select[@id='credential-usage']")
	public WebElement selectcredentialusage;

	@FindBy(xpath = "//label[contains(.,'Client Id')]/../input[@id='oauth-client-id']")
	public WebElement ClientIdinput;

	@FindBy(xpath = "//div/label[contains(.,'Credential Description')]/../input[@id='credential-description']")
	public WebElement CredentialDescription;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement savebutton;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	public WebElement cancelbtn;

	@FindBy(xpath = "//div[contains(.,'A new secret was created.  Please keep this secret for your records as it will no longer be accessible after closing this notification.')]/strong[contains(.,'Success!')]/../strong[contains(.,'New Secret:')]")
	public WebElement Successouthcreation;

	@FindBy(xpath = "//td[5]/div/a")
	public WebElement actionauth;

	@FindBy(xpath = "//a[contains(.,'Edit')]")
	public WebElement actionauthEdit;

	@FindBy(xpath = "//div/ul/li/a[contains(.,'Delete')]")
	public WebElement actionauthDelete;

	@FindBy(xpath = "//div/ul/li/a[contains(.,'Manage Secrets')]")
	public WebElement actionauthManageSecrets;

	@FindBy(xpath = "//h3[contains(.,'Warning!')]")
	public WebElement deletewarning;

	@FindBy(xpath = "//p[contains(.,'You are about to delete a Credential and its Secrets.')]")
	public WebElement deleteouthtext;

	@FindBy(xpath = "//a[@id='confirm-remove-customer']/span[contains(.,'Delete Credential and its Secrets')]")
	public WebElement deleteouthlink;

	@FindBy(xpath = "//a/strong[contains(.,'Cancel')]")
	public WebElement cancellink;

	@FindBy(xpath = "//p[contains(.,'Manage Secrets')]")
	public WebElement managesecretsheader;

	@FindBy(xpath = "//i[@class='icon-minus icon-blue']")
	public WebElement minusicon;

	@FindBy(xpath = "//strong[contains(.,'Secret Created:')]")
	public WebElement secretcreatedtext;

	@FindBy(xpath = "//div/strong[contains(.,'Secret Created:')]/../../div/i")
	public WebElement deletesecreticon;

	@FindBy(xpath = "//button[contains(.,'Add New Secret')]")
	public WebElement AddNewSecrettext;

	@FindBy(xpath = "//button[contains(.,'Close')]")
	public WebElement Closebuttn;

	@FindBy(xpath = "//ul[1]/li/div[contains(.,'You are about to delete a secret.  This secret will no longer be valid for use.  Are you sure you want to delete?')]/strong[contains(.,'Warning:')]")
	public WebElement warningtextmanagesecrets;

	@FindBy(xpath = "//ul[1]/li/div/p/button[contains(.,'Delete')]")
	public WebElement deletemanagesecret;

	@FindBy(xpath = "//ul[1]/li/div/p/button[contains(.,'Cancel')]")
	public WebElement closemanagesecret;

	@FindBy(xpath = "//ul[1]/li/div/strong[contains(.,'Secret Created:')]/../../div/i")
	public WebElement deletefirstsecret;

	@FindBy(xpath = "//p[contains(.,'Using Vertex Cloud credentials, you can manage needed credentials and passwords within your respective systems.')]")
	public WebElement outhcredentialstext;

	@FindBy(xpath = "//strong[contains(.,'Total Credentials:')]")
	public WebElement TotalCredentialstext;

	@FindBy(xpath = "//span[contains(.,'INDIRECT TAX INTELLIGENCE')]")
	public WebElement IndirectTaxIntelligence;

	@FindBy(css = "div.container-dashboard:nth-child(2) div.wrapper div.right-col.dashboard-col div.ng-scope div.ng-scope div.iti.ng-scope div.section div.row-fluid div.eqheight-well div.iti-tile.pr-15:nth-child(1) div.eqheight-well-col.has-btn-block > div.tile-heading")
	public WebElement IndirectTaxIntelligenceLabel;

	@FindBy(css = "div.container-dashboard:nth-child(2) div.wrapper div.right-col.dashboard-col div.ng-scope div.ng-scope div.iti.ng-scope div.section div.row-fluid div.eqheight-well div.iti-tile:nth-child(2) div.eqheight-well-col.has-btn-block.text-center > div.tile-heading:nth-child(2)")
	public WebElement RegisterforAccess;

	@FindBy(xpath = "//span[contains(.,'Log into Indirect Tax Intelligence ')]")
	public WebElement LoginITI;

	@FindBy(xpath = "//span[contains(.,'Discover Indirect Tax Intelligence')]")
	public WebElement DiscoverITI;

	@FindBy(xpath = "//div/a[contains(text(),'View All Users')]")
	public WebElement VIEWALLUSERSLINKFROMCLIENT;

}
