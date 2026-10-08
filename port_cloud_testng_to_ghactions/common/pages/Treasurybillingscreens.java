/*###################################################################################################
Name: BillingReport.java 
Description: Billing Report page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/28/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class Treasurybillingscreens {

	@FindBy(xpath = "//span[text()='Dashboard']")
	public WebElement DASHBOARD;

	@FindBy(xpath = "//span[text()='Lookup Tax Rates']")
	public WebElement LOOKUPTAXRATES;

	@FindBy(xpath = "//span[contains(text(),'Rates & Jurisdictions')]")
	public WebElement RatesJurisdictions;

	@FindBy(xpath = "//input[@id='requestZip']")
	public WebElement ZIP;

	@FindBy(xpath = "//input[@id='startdate']")
	public WebElement STARTDATE;

	@FindBy(xpath = "//button[@class='btn btn-disabled']")
	public WebElement DISABLELOOKUPTAXRATESBUTTON;

	@FindBy(xpath = "//button[@class='btn btn-disabled btn-disable']")
	public WebElement DISLOOKUP_FIN_STF;

	@FindBy(xpath = "//span[text()='Metrics']")
	public WebElement METRICS;
	
	@FindBy(xpath = "//h1[contains(text(),'Maintenance Metric Data')]")
	public WebElement MAINTENANCEMETRICDATA;
	
	@FindBy(xpath = "//a[@id='MaintenancesMetricData']/strong[contains(.,'Download CSV')]")
	public WebElement DOWNLOADEDCSV;
	
	@FindBy(xpath = "//span[text()='Billing Reports']")
	public WebElement BILLINGREPORTS;

	@FindBy(xpath = "//a[contains(text(),'Invite User')]")
	public WebElement ADMININVITE;
	
	@FindBy(xpath = "//div[@class='controls']/select/option[text()='Billing Approval Admin']")
	public WebElement VERIFYBILAPPADM;
	
	@FindBy(xpath = "//table[@class='table table-striped grid-table']/tbody/tr[1]")
	public WebElement TBLFRSTROW;

	@FindBy(xpath = "//span[text()='Manage Billing']")
	public WebElement MANAGEBILLING;
	
	@FindBy(xpath = "//a[@title='Discount Maintenance']")
	public WebElement DISCOUNTMAINTENANCE;
	
	@FindBy(xpath = "//a[@title='Manual Transaction']")
	public WebElement MANUALTRANSACTION;
	
	@FindBy(xpath = "//a[@title='Billing Administration']")
	public WebElement BILLINGADMINISTRATION;
	
	@FindBy(xpath = "//a[@id='liBillingRun']")

	public WebElement BILLINGDASHBOARD;
	@FindBy(xpath = "//a[text()='Failed Transactions ']")

	public WebElement FAILEDBILLING;
	@FindBy(xpath = "//a[@title='Cloud Customer Lookup']")

	public WebElement CLIENTLOOUP;
	@FindBy(xpath = "//a[@title='Billing Run History']")

	public WebElement TRANSACTIONHISTORY;
	@FindBy(xpath = "//a[@title='Billing Summary Report']")

	public WebElement BSUMMARYREPORT;
	@FindBy(xpath = "//a[@title='Billing Detail Report']")

	public WebElement BDETAILREPORT;
	@FindBy(xpath = "//a[@title='Billing Error Report']")

	public WebElement BERRRORREPORT;
	@FindBy(xpath = "//a[@title='VAR Commission Report']")

	public WebElement COMMSSIONREPORT;
	@FindBy(xpath = "//a[@title='VAR Customer Report']")

	public WebElement CUSTOMERREPORT;
	@FindBy(xpath = "//span[text()='Invite Users']")

	public WebElement INVITEUSERS;
	@FindBy(xpath = "//input[@class='search input-append ng-pristine ng-untouched ng-valid']")

	public WebElement SEARCHINPUT;
	@FindBy(xpath = "//div/p/a[contains(text(),'Delete')]")

	public WebElement DELETEBUTTON;
	@FindBy(xpath = "//tr/td[contains(text(),'Deleted')]")

	public WebElement USERDELETECONFIRM;
	@FindBy(xpath = "//span[text()='Manage Customers']")

	public WebElement VERTEXSMBCUSTOMERS;
	@FindBy(xpath = "//table/tbody/tr[2]/td[1]/div/a")

	public WebElement CUSTOMERSFIRSTROW;
	@FindBy(xpath = "//label[contains(.,'Trusted ID')]/../input")

	public WebElement OSERIESAPIKEY;

	@FindBy(xpath = "//span[text()='Premium Customers']")

	public WebElement PREMIUMSMBCUSTOMERS;
	@FindBy(xpath = "//div[@id='client-filter']/input")

	public WebElement searchboxinPremium;
	@FindBy(xpath = "//div[@id='client-list']")

	public WebElement clientList;
	@FindBy(xpath = "//div/div/div[2]/div[1]/div/a")

	public WebElement PREMIUMFIRSTCLIENT;
	@FindBy(xpath = "//a[contains(text(),'Show Returns')]")

	public WebElement SHOWRETURNS;
	@FindBy(xpath = "//table/tbody/tr[1]/td[5]/select")

	public WebElement VERTEXFILINGMETHOD;
	@FindBy(xpath = "//table/tbody/tr[1]/td[7]/input")

	public WebElement EDIREGISTRATIONNUMBER;
	@FindBy(xpath = "//table/tbody/tr[1]/td[8]/button")

	public WebElement UPDATEBUTTON;
	@FindBy(xpath = "//table/tbody/tr[1]/td/button[@ng-disabled='isReturnSaving']")

	public WebElement DISABLEUPDATEBUTTON;
	// @FindBy(xpath= "//span[text()='Help Center']")
	//
	@FindBy(xpath = "//span[text()='Inline Help Editor']")

	public WebElement INLINEHELP;
	@FindBy(xpath = "//div[@class='controls-inline']/select")

	public WebElement HELPDRPDWN;
	@FindBy(xpath = "//a[contains(text(),'Cancel')]")

	public WebElement CANCEL;
	@FindBy(xpath = "//span[text()='Update Notification']")

	public WebElement UPDATENOTIFICATION;
	@FindBy(xpath = "//span[text()='Manage Returns Config']")

	public WebElement MANAGECONGIGURATION;
	@FindBy(xpath = "//table/tbody/tr[1]/td/button[contains(text(),'Update')]")

	public WebElement MANAGECONFIGUPDATEBUTTON;
	@FindBy(xpath = "//a[contains(text(),'Manage Return Specific Rules')]")

	public WebElement MANAGERETURNSPECIFICRULESLINK;
	@FindBy(xpath = "//table/tbody/tr[1]/td/button[contains(.,'Update')]")

	public WebElement MANAGERETURNSSPECIFICRULESUPDATE;
	@FindBy(xpath = "//table/tbody/tr[1]/td/button[contains(.,'Cancel')]")

	public WebElement MANAGECONFIGURATIONSCREENSCANCEL;
	@FindBy(xpath = "//a[contains(text(),'Manage Location Specific Rules')]")

	public WebElement MANAGELOCATIONSPECIFICRULESLINK;
	@FindBy(xpath = "//a[contains(text(),'Manage Location Setup')]")

	public WebElement MANAGELOCATIONSETUPLINK;
	@FindBy(xpath = "//a[contains(text(),'Associated Taxpayer Return Rules')]")

	public WebElement ASSOCIATEDTAXPAYERRULESLINK;
	@FindBy(xpath = "//span[text()='Manage Tax Calendar']")

	public WebElement MANAGETAXCALENDAR;
	@FindBy(xpath = "//table/tbody/tr[1]/td/button[contains(.,'Update')]")

	public WebElement MNGTAXCALUPDATEDISABLE;
	@FindBy(xpath = "//span[text()='Error Monitor']")

	public WebElement ERRORMONITOR;
	@FindBy(xpath = "//span[text()='Get Help']")

	public WebElement GETHELP;
	@FindBy(xpath = "//span[contains(text(),'Vertex Cloud VARS')]")

	public WebElement VERTEXSMBVARS;
	@FindBy(xpath = "//input[@type='text']")

	public WebElement VSMBVARSEARCH;
	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(text(),'Name')]")

	public WebElement SMBVARSNAMEHEADER;
	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(text(),'Address')]")

	public WebElement SMBVARSADDRESSHEADER;
	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(text(),'City')]")

	public WebElement SMBVARSCITYHEAADER;
	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(text(),'State')]")

	public WebElement SMBVARSSTATEHEADER;
	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(text(),'Zip')]")

	public WebElement SMBVARSZIPHEADER;
	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(text(),'Phone')]")

	public WebElement SMBVARSPHONEHEADER;
	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(text(),'Status')]")

	public WebElement SMBVARSSTATUSHEADER;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Reports')]")

	public WebElement SMBVARSREPORTSHEADER;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Admin')]")

	public WebElement SMBVARSADMINHEADER;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'User(s)')]")

	public WebElement SMBVARSUSERSHEADER;
	@FindBy(xpath = "//table/tbody/tr[2]/td/a/i[@class='icon icon-add icon-blue']")

	public WebElement SMBVARSPLUSICONUSERSCOLUMN;
	@FindBy(xpath = "//table/tbody/tr[3]/td/table/thead/tr/th[1][contains(text(),'First Name')]")

	public WebElement SMBVARSVARFIRSTNAME;
	@FindBy(xpath = "//table/tbody/tr[3]/td/table/thead/tr/th[2][contains(text(),'Last Name')]")

	public WebElement SMBVARSVARLASTNAME;
	@FindBy(xpath = "//table/tbody/tr[3]/td/table/thead/tr/th[3][contains(text(),'Email')]")

	public WebElement SMBVARSVAREMAIL;
	@FindBy(xpath = "//table/tbody/tr[3]/td/table/thead/tr/th[4][contains(text(),'User Name')]")

	public WebElement SMBVARSVARUSERNAME;
	@FindBy(xpath = "//table/tbody/tr[3]/td/table/thead/tr/th[4][contains(text(),'Phone')]")

	public WebElement SMBVARSVARPHONE;
	@FindBy(xpath = "//table/tbody/tr[3]/td/table/thead/tr/th[5][contains(text(),'Created')]")

	public WebElement SMBVARSVARCREATED;
	@FindBy(xpath = "//table/tbody/tr[3]/td/table/thead/tr/th[6][contains(text(),'Last Login')]")

	public WebElement SMBVARSVARLASTLOGIN;
	@FindBy(xpath = "//table/tbody/tr[3]/td/table/thead/tr/th[7][contains(text(),'Status')]")

	public WebElement SMBVARSVARSTATUS;
	@FindBy(xpath = "//table/tbody/tr[3]/td/table/thead/tr/th[8][contains(text(),'Role')]")

	public WebElement SMBVARSVARROLE;
	@FindBy(xpath = "//table/tbody/tr/td/a/i[@class='icon icon-blue icon-minus']")

	public WebElement SMBVARSMINUSICONUSERSCOLUMN;

	// Approve Screns

	@FindBy(xpath = "//a[@title='Approval Discount']")

	public WebElement APPROVEDISCOUNT;
	@FindBy(xpath = "//a[@title='Approval Price Override']")

	public WebElement APPPRICEOVER;
	@FindBy(xpath = "//a[@title='Approval Rate Structure']")

	public WebElement APPSTRUCTURE;
	@FindBy(xpath = "//a[@title='Approval Billing Run']")

	public WebElement APPBILLINGRUN;
	@FindBy(xpath = "//a[@title='Approval VAR Set Up']")

	public WebElement APPVARSETUP;

	@FindBy(xpath = "//a[text()='Treasury Admin Name']")

	public WebElement TADMINNAME;
	@FindBy(xpath = "//a[text()='Treasury Admin Name']")

	public WebElement TADMINNAMEPO;
	@FindBy(xpath = "//a[text()='Request Date']")

	public WebElement REQDATE;
	@FindBy(xpath = "//a[text()='Requested Discount']")

	public WebElement REQDIS;
	@FindBy(xpath = "//a[text()='Request Type']")

	public WebElement REQTYPE;
	@FindBy(xpath = "//a[text()='Request Id']")

	public WebElement REQID;
	@FindBy(xpath = "//strong[text()='There is no pending request for Rate Structure Approval.']")

	public WebElement RSMESSAGE;
	@FindBy(xpath = "//textarea[@id='txtNoteseSetStrtDate']")

	public WebElement APPROVALADMINNOTES;
	@FindBy(xpath = "//button[contains(text(),'Approve')]")

	public WebElement APPROVEBUTTON;
	@FindBy(xpath = "//div[contains(text(),'Request for Update Billing Run Start Date is Approved.')]")

	public WebElement UPDATEBILLNGSTARTDATEAPPROVED;

	// Discount Maintenance

	@FindBy(xpath = "//h1[contains(.,'Cloud Customer Discount Maintenance')]")

	public WebElement HEADDISMAIN;
	@FindBy(xpath = "//a[text()='Cloud Customer Number']")

	public WebElement CLIENTNO;
	@FindBy(xpath = "//a[text()='Cloud Customer Name']")

	public WebElement CLIENTNAME;
	@FindBy(xpath = "//a[text()='Discount']")

	public WebElement DISCOUNT;
	@FindBy(xpath = "//a[text()='Effective Date']")

	public WebElement EFFECTIVEDATE;
	@FindBy(xpath = "//a[text()='End Date']")

	public WebElement ENDDATE;
	@FindBy(xpath = "//a[text()='Status']")

	public WebElement STATUS;
	@FindBy(xpath = "//span[text()='History']")

	public WebElement HISTORY;

	// Manual Transaction
	@FindBy(xpath = "//a[text()='Transaction Date']")

	public WebElement TRANSACTIONDATE;
	@FindBy(xpath = "//a[text()='Payment Type']")

	public WebElement PAYMENTTYPE;
	@FindBy(xpath = "//a[text()='Payment Type']")

	public WebElement TOTALAMOUNT;

	// Billing Administration

	@FindBy(xpath = "//td[contains(.,'Cloud Customer Name :')]")

	public WebElement CUSTDRPDN;
	@FindBy(xpath = "//th[text()='Transactions Month ']")

	public WebElement MONTH;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Total Billing Transactions ')]")

	public WebElement TOTALBILLING;
	@FindBy(xpath = "//th[text()='Successful Transactions ']")

	public WebElement SUCCESSFULLTRANS;
	@FindBy(xpath = "//th[text()='Failed Transactions ']")

	public WebElement FAILEDTRANS;
	@FindBy(xpath = "//th[text()='Successful Total Amount ']")

	public WebElement STTOTALAMOUNT;
	@FindBy(xpath = "//th[text()='Failed Total Amount ']")

	public WebElement FTOTALAMOUNT;
	@FindBy(xpath = "//th[text()='Billing Run Start Date ']")

	public WebElement BILLINGRUNSTARTDATE;
	@FindBy(xpath = "//strong[contains(text(),'Billing Run Status: ')]/../../span[contains(text(),'Inprogress')]")

	public WebElement BILLINGRUNSTATUSLABEL;
	@FindBy(xpath = "//div[@class='datepicker-days']/table/thead/tr/th[contains(text(),'«')]")

	public WebElement CALANDERPREVIUOS;
	@FindBy(xpath = "//div[contains(text(),'Request has been sent to Approval Admin.')]")

	public WebElement APPROVALADMINREQUEST;
	// @FindBy(xpath="//button[@id='btnClose']")
	//
	@FindBy(xpath = "//button[@class='btn btn-primary']")

	public WebElement CLOSE;

	@FindBy(xpath = "//a[text()='SMB Customer ID']")

	public WebElement CLIENTID;
	@FindBy(xpath = "//a[text()='Transaction ID']")

	public WebElement TRANSACTIONID;
	@FindBy(xpath = "//a[text()='Reason Code']")

	public WebElement REASONCODE;
	@FindBy(xpath = "//a[text()='Transaction Status']")

	public WebElement TRANSACTIONSTATUS;

	// Client Look up

	@FindBy(xpath = "//span[text()='Quote/Invoice']")

	public WebElement QUOTEINVOICE;
	// @FindBy(xpath= "//input[@id='btnReset']")

	public WebElement RESETFILTERSCT;
	@FindBy(xpath = "//input[@value='Reset Filters']")

	public WebElement RESETFILTERS;
	@FindBy(xpath = "//input[@id='btnExport']")

	public WebElement EXPORT;
	@FindBy(xpath = "//a[text()='Subscription Level']")

	public WebElement SUBSCRIPTIONLEVEL;

	// Rate Structure

	@FindBy(xpath = "//a[@title='Rate Structure']")

	public WebElement RATESTRUCTURE;
	@FindBy(xpath = "//div[@class='section']/strong[text()='Service Level Fees']")

	public WebElement RSTRUCTUREFLOOR;
	@FindBy(xpath = "//div[@class='section']/strong[text()='Transaction Fees (Scaling)']")

	public WebElement TRANSCATIONFEE;
	@FindBy(xpath = "//div[@class='section']/strong[text()='Rate File Fees (Scaling)']")

	public WebElement RATEFILEFEES;
	@FindBy(xpath = "//div[@class='section']/strong[text()='Ancillary Fees ']")

	public WebElement ANCILLARYFEES;
	@FindBy(xpath = "//input[@id='btnReset']")

	public WebElement RESET;
	@FindBy(xpath = "//input[@id='btnUpdateRate']")

	public WebElement UPDATE;
	@FindBy(xpath = "//input[@id='Ancillary_Fee_TransactionMinimumAmount']")

	public WebElement MONTHLYMINFEE;

	// Transcation History

	@FindBy(xpath = "//a[text()='Created Date']")

	public WebElement CREATEDDATE;
	@FindBy(xpath = "//a[text()='Billing Period']")

	public WebElement BILLINGPERIOD;
	@FindBy(xpath = "//a[text()='Sub Total Amount']")

	public WebElement SUBTOTAL;
	@FindBy(xpath = "//a[text()='Discount Amount']")

	public WebElement DISCOUNTAMOUNT;
	@FindBy(xpath = "//a[text()='Tax']")

	public WebElement TAX;
	@FindBy(xpath = "//a[text()='Grand Total Amount']")

	public WebElement GRANDTAMOUNT;
	@FindBy(xpath = "//a[text()='Paid Amount']")

	public WebElement PAIDAMOUNT;
	@FindBy(xpath = "//a[text()='Paid Date']")

	public WebElement PAIDADATE;

	// Billing Reports

	@FindBy(xpath = "//a[text()='Transaction Amount']")

	public WebElement TRANSACTIONAMOUNT;
	@FindBy(xpath = "//a[text()='Billing Date']")

	public WebElement BILLLINGDATE;
	@FindBy(xpath = "//select[@id='ClientID']")

	public WebElement SELECT;

	// Billing Detail Report

	@FindBy(xpath = "//a[text()='Line Number']")

	public WebElement LINENUMBER;
	@FindBy(xpath = "//a[text()='Line Item Total Amount']")

	public WebElement LITOTAMOUNT;
	@FindBy(xpath = "//a[text()='Line Item Product Code']")

	public WebElement LIPRODUCTCODE;
	@FindBy(xpath = "//a[text()='Line Item Description']")

	public WebElement LIDESCRIPTION;

	// Billing Error report

	@FindBy(xpath = "//a[text()='Item Description']")

	public WebElement ITEMDES;
	@FindBy(xpath = "//a[text()='Payment Token']")

	public WebElement PAYTOKEN;

	// Var commission

	@FindBy(xpath = "//a[text()='VAR ID']")

	public WebElement VARID;
	@FindBy(xpath = "//a[text()='VAR Name']")

	public WebElement VARNAME;
	@FindBy(xpath = "//a[text()='Commission Percentage']")

	public WebElement COMMISSIONPER;
	@FindBy(xpath = "//a[text()='Updated Date']")

	public WebElement UPDATEDDATE;
	@FindBy(xpath = "//a[text()='SMB Customer Name']")

	public WebElement CUSTOMERNAME;
	@FindBy(xpath = "//a[text()='Billed Amount']")

	public WebElement BILLEDAMOUNT;
	@FindBy(xpath = "//a[text()='Billing Date']")

	public WebElement BILLINGDATE;
	@FindBy(xpath = "//a[text()='Commission Amount']")

	public WebElement COMAMOUNT;
	@FindBy(xpath = "//input[@id='btnReset']")

	public WebElement RESETFILTER;
	@FindBy(xpath = "//input[@id='btnAssignRate']")

	public WebElement ASSIGNCOM;
	@FindBy(xpath = "//a/span[contains(text(),'Get Help')]")

	public WebElement GETHELPLINK;

	// Client Look Up
	@FindBy(xpath = "//div/a[text()='Cloud Customer Name']/../../div[@class='grid-filter']/span")

	public WebElement CLIENTNAMEFILTER;
	@FindBy(xpath = "//input[@class='grid-filter-input form-control']")

	public WebElement VALUE;
	@FindBy(xpath = "//button[text()='Apply']")

	public WebElement APPLY;
	@FindBy(xpath = "//div[@data-name='ClientNumber']/span")

	public WebElement CLIENTNOFILTER;
	@FindBy(xpath = "//div[@data-name='SubscriptionLevel']/span")

	public WebElement SUBLEVELFILTER;

	@FindBy(xpath = "//a[text()='Requested Discount']")

	public WebElement REQDATEAPP;
	@FindBy(xpath = "//a[text()='Requested Date']")

	public WebElement REQDATEVAR;

	// Manage Tax Calendar

	@FindBy(xpath = "//span[text()='Manage Tax Calendar']")

	public WebElement MNGTAXCALEND;
	@FindBy(xpath = "//th[text()='Lock Date Offset']")

	public WebElement HARDDATELBL;

	// Collapse

	@FindBy(xpath = "//a/span[contains(.,'Collapse')]")

	public WebElement COLLAPSE;
	@FindBy(xpath = "//div[@class='left-col nav-col view-collapsed']")

	public WebElement VERIFYCOLLAPSED;
	@FindBy(xpath = "//a/span[text()='Marketing ToolKit']")

	public WebElement COPSDMTKTABICON;
	@FindBy(xpath = "//h1[contains(.,'Marketing Toolkit')]")

	public WebElement MTKITHTEXT;
	@FindBy(xpath = "//div[@class='left-col nav-col view-collapsed']/div/ul/li/a/span[text()='Manage Tax Calendar']")

	public WebElement COPSDMTCTABICON;
	@FindBy(xpath = "//h1[contains(.,'Tax Calendar')]")

	public WebElement MTCALEDRHTEXT;
	@FindBy(xpath = "//div[@class='left-col nav-col view-collapsed']/div/ul/li/a/span[text()='Vertex Cloud VARS']")

	public WebElement COPSDVERTEXVARSTABICON;
	@FindBy(xpath = "//h1[contains(.,'Vertex Cloud VARS')]")

	public WebElement VERTEXVARSHTEXT;
	@FindBy(xpath = "//span[contains(text(),'Settings')]")

	public WebElement SETTINGS;
	@FindBy(xpath = "//div/a[contains(text(),'View All Users')]")

	public WebElement SETTINGSVIEWALLUSERS;
	@FindBy(xpath = "//h1/a[contains(text(),'Return to Settings')]")

	public WebElement RETURNTOSETTINGS;

	// Error Monitor

	@FindBy(xpath = "//div/span[text()='Cloud Customer Name']")

	public WebElement EMCUSTNAME;
	@FindBy(xpath = "//div/span[text()='Parent Cloud Customer Name']")

	public WebElement EMPCUSTNAME;

	// System Admin Document Center

	@FindBy(xpath = "//span[contains(text(),'Document Center')]")

	public WebElement DOCUMENTCENTER;
	@FindBy(xpath = "//div[@class='span4']/select/option[2]")

	public WebElement DOCUMNETCENTERCLIENT;
	@FindBy(xpath = "//div[@class='span2']/select")

	public WebElement DOCUMENTCENTERFILETYPE;
	@FindBy(xpath = "//button[contains(.,'Upload File')]")

	public WebElement DOCUMENTCENTERUPLOADFILE;
	@FindBy(xpath = "//button[contains(.,'Upload File')]")

	public WebElement NOTICEANDRETURNSFILEUPDATE;

	// Customer Id Report
	@FindBy(xpath = "//span[contains(text(),'Customer ID Report')]")

	public WebElement CUSTOMERIDREPORT;
	@FindBy(xpath = "//span[@data-original-title='Customer ID Report']/span[contains(text(),'i')]")

	public WebElement INLINEHELPICON;
	@FindBy(xpath = "//select[@class='input-xlarge']")

	public WebElement VARDROPDOWN;
	@FindBy(xpath = "//a[contains(text(),'Export')]")

	public WebElement EXPORTBTN;
	@FindBy(xpath = "//div[contains(text(),'Customer Name')]")

	public WebElement CUSTOMERNAMECOLUMN;
	@FindBy(xpath = "//div[@class='tablesorter-header-inner'][text()='Customer Name']")

	public WebElement CUSTOMERNAMESORTINGICON;
	@FindBy(xpath = "//div[@class='tablesorter-header-inner'][text()='Customer Account Number']")

	public WebElement CUSTOMERACCNUMBERSORTINGICON;
	@FindBy(xpath = "//div[@class='tablesorter-header-inner'][text()='O Series API Key (Public Trusted Id)']")

	public WebElement TRUSTEDIDSORTINGICON;
	@FindBy(xpath = "//div[@class='tablesorter-header-inner'][text()='Customer Account Number']")

	public WebElement CUSTOMERACNUMBER;
	@FindBy(xpath = "//div[@class='tablesorter-header-inner'][text()='O Series API Key (Public Trusted Id)']")

	public WebElement TRUSTID;
	@FindBy(xpath = "//div[contains(text(),'Report of all SMB Customers and their associated TrustedIDs')]")

	public WebElement CUSTOMERIDICONHEADERDESCRIPTION;
	@FindBy(xpath = "//div/span[@class='span6 disablePagination']/ul/li/a")

	public WebElement CUSTIDREPORTPAGINATION;
	@FindBy(xpath = "//div/span[@class='span6 disablePagination']/ul/li/a[@class='last hand disabled']")

	public WebElement CUSTIDREPORTPAGINATIONNEXTDISABLE;
	@FindBy(xpath = "//span[contains(text(),'Configuration Report')]")

	public WebElement CONFIGURATIONREPORT;
	@FindBy(xpath = "//table/tbody/tr[1]/td[1]")

	public WebElement CUSTOMERNAMEFIRSTROW;

	// System Admin Read only error monitor table

	@FindBy(xpath = "//h1/span/span[contains(text(),i)]")

	public WebElement CUSTOMERIDREPORTICON;
	@FindBy(xpath = "//thead/tr/th/div/span[contains(text(),'ID')]")

	public WebElement ERRORID;
	@FindBy(xpath = "//thead/tr/th/div/span[contains(text(),'Queue Type')]")

	public WebElement ERRORQUEUETYPE;
	@FindBy(xpath = "//thead/tr/th/div/span[contains(text(),'Created Date')]")

	public WebElement ERRORCREATEDQUEUE;
	@FindBy(xpath = "//thead/tr/th/div/span[contains(text(),'Modified Date')]")

	public WebElement ERRORMODIFIEDDATE;
	@FindBy(xpath = "//thead/tr/th/div/span[(text()='Cloud Customer Name')]")

	public WebElement ERRORSMBCUSTOMER;
	@FindBy(xpath = "//thead/tr/th/div/span[contains(text(),'Parent Cloud Customer Name')]")

	public WebElement ERRORPARENTSMBCUSTOMERNAME;
	@FindBy(xpath = "//thead/tr/th/div/span[contains(text(),'Error Message')]")

	public WebElement ERRORMESSAGE;
	@FindBy(xpath = "//thead/tr/th[contains(text(),'Action')]")

	public WebElement ERRORACTION;
	@FindBy(xpath = "//table[@id='errortable']/../table/tbody/tr/td/a[contains(.,'Details')]")

	public WebElement ERRORDETAILS;
	@FindBy(xpath = "//div/a[contains(text(),'Cancel')]")

	public WebElement ERRORCANCELBUTTON;

	// Billing

	@FindBy(xpath = "//ul/li/a[@title='Billing Run History']")

	public WebElement RUNHISTORY;
	@FindBy(id = "billingPeriodYear")

	public WebElement RHYEAR;
	@FindBy(id = "billingPeriodYear")

	public WebElement RUNYEAR;
	@FindBy(id = "billingPeriodMonth")

	public WebElement RUNPERIOD;
	@FindBy(id = "runType")

	public WebElement RUNTYPEID;
	@FindBy(id = "billingPeriodMonth")

	public WebElement RHYEARMONTH;
	@FindBy(xpath = "//a[contains(.,'Run Billing')]")

	public WebElement RUNBILLINGBTN;
	@FindBy(xpath = "//div[2]/div[@class='row-fluid']/div/label[contains(.,'Cloud Customer Name')]/../input")

	public WebElement RHCUSTOMERNAME;
	@FindBy(xpath = "//div[2]/div[@class='row-fluid']/div/label[contains(.,'Cloud Customer Number')]/../input")

	public WebElement RHCUSTOMERNUMBER;
	@FindBy(xpath = "//input[@class='ng-pristine ng-valid ng-touched']")

	public WebElement RHCKCKBOX;
	@FindBy(xpath = "//a[contains(.,'Initiate Billing')]")

	public WebElement INITIATEBILLPOPUPID;
	@FindBy(xpath = "//button[contains(.,'Initiate Billing Run')]")

	public WebElement INITIATEBILLRUN;
	@FindBy(xpath = "//a[@class='btn btn-primary ng-binding']")

	public WebElement INITIATEBILLPOPUP;
	@FindBy(xpath = "//span[@class='pagination-index ng-scope']/select")

	public WebElement PAGINATION;
	@FindBy(xpath = ".//*[@id='billingRunsTable']/tbody/tr/td[2]")

	public WebElement SMCUSTOMERNAME;

	// 4.4.1 Changes CAD Conversion Page Objects

	@FindBy(xpath = "//h3[contains(.,'Please enter in today’s CAD conversion rate.')]")

	public WebElement CADCONVERSIONPOPUP;
	@FindBy(xpath = "//a[contains(.,'here')]")

	public WebElement HERELINK;
	@FindBy(id = "canConversion")

	public WebElement CADCONVERSIONRATE;
	@FindBy(id = "confirm-remove-customer")

	public WebElement UPDATERATEBUTTON;
	@FindBy(xpath = "//h3[contains(.,'Are you sure you want to set the CAD conversion rate of 1.22697 for today?')]")

	public WebElement CADCONVERSIONCONFIRM;
	@FindBy(id = "confirm-remove")

	public WebElement SAVECADCONVERSION;
	@FindBy(id = "base_currency_caret")

	public WebElement CURRENCYDROPDOWN;
	@FindBy(id = "base_currency_input")

	public WebElement CURRENCYINPUT;
	@FindBy(id = "base_amount_input")

	public WebElement CURRENCYVALUE;
	@FindBy(id = "customerName")

	public WebElement RHCUSTOMERNAME2;

	@FindBy(xpath = "//span[text()='Manage Billing']")

	public WebElement ManageBillingHover;

	@FindBy(xpath = "//a[contains(.,'Export')]")

	public WebElement EXPORTBUTTON;

	@FindBy(xpath = "//span[contains(text(),'Manage Billing')]")

	public WebElement MANAGEBILLINGHOVER;

	@FindBy(id = "billingPeriodMonth")

	public WebElement RHYEAR_MONTH_ID;

	@FindBy(xpath = "//div//h1[contains(.,'Page Not Found')]")

	public WebElement PAGENOTFOUND;

	@FindBy(xpath = "//select[@class='ng-pristine ng-valid ng-touched']")

	public WebElement TRANSACTIONHISTORYLISTBOX;

	@FindBy(xpath = "//a[contains(text(),'Manage Discounts')]")

	public WebElement MANAGEDISCOUNTSLINK;

	@FindBy(xpath = "//a[contains(text(),'+ Add Discount')]")

	public WebElement ADDISCOUNT;

	@FindBy(xpath = "//select[@id='type']")

	public WebElement BILLINGTYPE;

	@FindBy(xpath = "//input[@name='amount']")

	public WebElement AMOUNT;

	@FindBy(xpath = "//textarea[@id='notes']")

	public WebElement NOTES;

	@FindBy(xpath = "//button[1]")

	public WebElement SUBMIT;

	@FindBy(xpath = "//select[@name='lineItemProduct']")

	public WebElement LINEITEM;

	@FindBy(xpath = "//input[@name='lineItemAmount']")

	public WebElement LINEITEMAMOUNT;

	@FindBy(xpath = "//a[text()='+ Add Line Item']")

	public WebElement ADDLINEITEM;

	@FindBy(xpath = "//div[2]/div/div/select[1]")

	public WebElement LINEITEM1;

	@FindBy(xpath = "//div[5]/div[2]/div/div/input")

	public WebElement LINEITEMAMOUNT1;

	@FindBy(xpath = "//div[5]/div[2]/div/div/select[2]")

	public WebElement LINEITEMTYPE;

	@FindBy(xpath = "//a[contains(text(),'Discount Requests')]")

	public WebElement DISCOUNTREQUESTS;

	@FindBys({

			@FindBy(xpath = "//table/tbody/tr"), })

	public List<WebElement> TABLEROWS;

	@FindBy(xpath = "//div[2]/div/button[1]")

	public WebElement APPROVE;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Header']/../td[text()='Approved']")

	public WebElement VERIFYAPPROVESTATUSHeader;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Line Level']/../td[text()='Supersceded']")

	public WebElement VERIFYREJECTSTATUSHeader;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Line Level']/../td[text()='Approved']")

	public WebElement VERIFYAPPROVESTATUSLineLevel;

	@FindBy(xpath = "//li/a[contains(.,'FAQs')]")

	public WebElement FAQS;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(text(),'Account Type')]")

	public WebElement SMBVARACCOUNTTYPE;
	@FindBy(xpath = "//a[contains(text(),'+ Show Current Discounts')]")

	public WebElement SHOWCURRENTDISCOUNTS;

	@FindBy(xpath = "//div[contains(text(),'There are currently no discounts for the selected period.')]")

	public WebElement NODISCOUNTSTEXT;

	@FindBy(xpath = "//a[contains(text(),'- Hide Current Discounts')]")

	public WebElement HIDEDISCOUNTS;

	@FindBy(xpath = "//a[contains(text(),'+ New Manual Debit ')]")

	public WebElement NEWMANUALDEBIT;

	@FindBy(id = "ddlBillingLine")

	public WebElement BILLINGLINE;

	@FindBy(id = "quanitiy")

	public WebElement QUANTITY;

	@FindBy(id = "txtLineLevelAmount")

	public WebElement LINELEVELAMOUNT;

	@FindBy(id = "txtSubTotal")

	public WebElement TAXSUBTOTAL;

	@FindBy(id = "ddlExcludeDiscounts")

	public WebElement EXCLUDEDISCOUNTS;

	@FindBy(id = "txtNotes")

	public WebElement TRANSACTIONNOTES;

	@FindBy(xpath = "//a[contains(text(),'Submit')]")

	public WebElement MANUALDEBITSUBMIT;

	@FindBy(xpath = "//td[text()='Debit Request Run']/../td[8]")

	public WebElement DEBITREQUESTRUNSTATUS;

	@FindBy(xpath = "//a[contains(text(),'Manual Transaction Requests')]")

	public WebElement MANUALTRANSREQUEST;

	@FindBy(xpath = "//span[@class='pagination-index ng-scope']/select")

	public WebElement PAGINATIONDROPDOWN;

	@FindBy(xpath = "//a[text()='3']")

	public WebElement THIRDPAGENAVIGATION;

	@FindBy(xpath = "//li[@class='ng-scope active']/a")

	public WebElement THIRDPAGENAVIGATEDTEXT;

	@FindBy(xpath = "//a[text()='1']")

	public WebElement FIRSTPAGENAVIGATION;

	@FindBy(id = "confirm-remove-customer")

	public WebElement CONFIRMREMOVECUSTOMER;

	@FindBy(id = "confirm-remove")

	public WebElement CONFIRMREMOVE;

	@FindBys({

			@FindBy(xpath = "//table/tbody/tr")

	})
	public List<WebElement> MANUALTRANSACTIONREQUESTSTABLE;

	@FindBy(id = "txtApproverNotes")

	public WebElement APPROVERNOTES;

	@FindBy(xpath = "//a[text()='Approve Debit']")

	public WebElement APPROVEDEBIT;

	@FindBy(xpath = "//a[text()='Approve Credit']")

	public WebElement APPROVECREDIT;

	@FindBys({

			@FindBy(xpath = "//table[@id='billingHistoryDetailTable']/tbody/tr") })
	public List<WebElement> TRANSACTIONHISTROYDEBITMEMORUN;

	@FindBy(xpath = "//a[text()='Reject Debit']")

	public WebElement REJECTDEBIT;

	@FindBy(xpath = "//a[text()='Reject Credit']")

	public WebElement REJECTCREDIT;
	@FindBy(id = "amount")

	public WebElement REFENDAMOUNT;

	@FindBy(name = "notes")

	public WebElement NOTESFORAPPROVER;

	@FindBy(xpath = "//a[text()='Submit']")

	public WebElement CREDITSUBMIT;

	@FindBy(name = "reviewerNotes")

	public WebElement REVIEWERNOTES;

	@FindBy(xpath = "//a[contains(.,'Customer Reports')]")

	public WebElement SMBREPORTS;
	@FindBy(xpath = "//div/label[contains(.,'State/Territory')]/../select")

	public WebElement ADMINSTATE;
	@FindBy(xpath = "//h2[contains(.,'Generate Report')]")

	public WebElement ADMINTRANSACTIONREPORT;

	public final String HARDDATELBL_XPATH = "//th[text()='Lock Date Offset']";
	public final String VERIFYCOLLAPSED_XPATH = "//div[@class='left-col nav-col view-collapsed']";
	public final String ERRORCANCELBUTTON_XPATH = "//div/a[contains(text(),'Cancel')]";
	public final String VERIFYBILAPPADM_LINK = "//div[@class='controls']/select/option[text()='Billing Approval Admin']";

	@FindBy(xpath = "//a[contains(text(),'Edit Filter Settings')]")

	public WebElement EDITUSERSETTINGS;

	@FindBys({ @FindBy(xpath = "//div[2]/div/div[2]/div[1]/div[2]/table/tbody/tr"), })
	public List<WebElement> ACTIVEACCOUNTSTABLE;

	@FindBys({ @FindBy(xpath = "//div[2]/div/div[2]/div[3]/div[2]/table/tbody/tr"), })
	public List<WebElement> TESTACCOUNTSTABLE;

	@FindBy(xpath = "//button[contains(text(),'SAVE CHANGES')]")

	public WebElement SAVECHANGES;

	@FindBy(xpath = "//a[contains(.,'Test Accounts')]")

	public WebElement TESTACCOUNTS;

	@FindBy(xpath = "//a[contains(.,'Active Accounts')]")

	public WebElement ACTIVEACCOUNTS;

	@FindBy(xpath = "//div[4]/div[1]/select")

	public WebElement SECONDLINEITEM;

	@FindBy(xpath = "//div[4]/div[2]/input")

	public WebElement SECONDLINEQUANTITY;

	@FindBy(xpath = "//div[4]/div[3]/input")

	public WebElement SECONDLINELEVELAMOUNT;

	@FindBy(xpath = "//a[contains(.,'+ Add Another Billing Line')]")
	public WebElement ADDANOTHERLINEITEM;

	@FindBy(xpath = "//a[contains(text(),'Export Data')]")
	public WebElement exportData;

	@FindBy(xpath = "//div/div/h1")

	public WebElement DASHBOARDTEXT;

	@FindBy(xpath = "//a[@href='/Admin/OspLogsSearch']")

	public WebElement OSPLOGS;

	@FindBy(xpath = "//input[@value='Search']")

	public WebElement OSPSEARCH;

	@FindBy(xpath = "//span[contains(.,'CTC Impact')]")

	public WebElement CTCImpact;

	@FindBy(xpath = "//a[contains(.,'Tax Area Transactions')]")

	public WebElement CTCTransactions;

	@FindBy(xpath = "//a[contains(.,' Returns')]")

	public WebElement CTCReturns;

	@FindBy(xpath = "//a[contains(.,'Taxability Categories')]")

	public WebElement TaxabilityCategories;

	@FindBy(xpath = "//li/a[@href='/Admin/Premium']/../a[contains(.,'Premium Customers')]")

	public WebElement PremiumCust;

	@FindBy(xpath = "//a[@href='/Admin/Premium/DownloadReturnFiles']")

	public WebElement DNDallreturns;

	@FindBy(xpath = "//a[@href='/Admin/DocumentCenter']")

	public WebElement DocCenter;

	@FindBy(xpath = "//a[@href='/Admin/Clients/Vars']")

	public WebElement VertexVars;

	@FindBy(xpath = "//a[@href='/Admin/SystemConfiguration']")

	public WebElement SystemConfiguration;

	@FindBy(xpath = "//table[@id='billingRunsTable']/thead/tr/th[1]/input")

	public WebElement InvoiceCustomerChkbox;

	@FindBy(xpath = "//table[@id='billingRunsTable']/tbody/tr[2]/td/div[contains(.,'Invoice Customer')]")

	public WebElement CustomerType;

	@FindBy(xpath = "//button[contains(.,'Initiate Billing Run') and @disabled='disabled']")

	public WebElement InitiateBillingBtnInvoice;

	@FindBy(xpath = "//h1[contains(text(),'Partitions Metric Data')]")

	public WebElement PARTITIONMETRICDATA;

	@FindBy(xpath = "//button[contains(.,'View Metrics')]")

	public WebElement VIEWMETRICS;

	@FindBy(xpath = "//h2[contains(.,'PARTITIONS METRIC DATA')]")

	public WebElement METRICDATA;

	@FindBy(xpath = "//th[contains(.,'Pod Sub Type')]")

	public WebElement PODSUBTYPE;

	@FindBy(xpath = "//tr/td[1][contains(text(),'1')]/../td[contains(.,'Calc')]")

	public WebElement POD1SUBTYPE;

	@FindBy(xpath = "//tr/td[1][contains(text(),'2')]/../td[contains(.,'Calc')]")

	public WebElement POD2SUBTYPE;

	@FindBy(xpath = "//tr/td[contains(.,'1000')]/../td[contains(.,'Config')]")

	public WebElement PODCSUBTYPE;

	@FindBy(xpath = "//a[contains(.,'Ok')]")

	public WebElement OK;

	@FindBy(xpath = "//tr/td[1][contains(text(),'3')]/../td[contains(.,'Calc')]")

	public WebElement POD3SUBTYPE;

	@FindBy(xpath = "//table[@id='billingRunsTable']/tbody/tr[2]/td/div[contains(.,'Merchant')]")

	public WebElement FranchiseCustomerType;

	@FindBy(xpath = "//li[@class='nav-has-child']/a/span[contains(text(),'Logs')]")

	public WebElement logs;

	@FindBy(xpath = "//a[contains(text(),'Tax Link Logs')]")

	public WebElement Taxlinklogs;

	@FindBy(xpath = "//h1[contains(.,'TaxLink Logs Search')]")

	public WebElement Taxlinklogssearch;

	@FindBy(xpath = "//a[contains(text(),'ECW Logs')]")

	public WebElement ECWLogs;

	@FindBy(xpath = "//div/select")

	public WebElement serverdropdwn;

	@FindBy(xpath = "//a[contains(text(),'VertexTL-Rest-Controller.log')]")

	public WebElement TAXLINKLOGSSDATA;

	@FindBy(xpath = "//label[contains(.,'Server Names')]/../select")

	public WebElement ECWServername;

	@FindBy(xpath = "//a/img[@src='/Content/img/folder.jpg']/../../a[contains(.,'ecw')]")

	public WebElement ECWLogdata;

	@FindBy(xpath = "//a/i[@class='icon-section icon-collapse']")

	public WebElement UnCollapsed;

	@FindBy(xpath = "//span[contains(.,'Oracle Account Number')]")

	public WebElement OracleAccountNumber;

	@FindBy(xpath = "//span[contains(.,'Oracle Party Number')]")

	public WebElement OraclePartyNumber;

	@FindBy(xpath = "//span[contains(.,'Oracle Status')]")

	public WebElement OracleStatus;

	@FindBy(xpath = "//span[contains(.,'Payment Status')]")

	public WebElement PaymentStatus;

	@FindBy(xpath = "//span[contains(.,'Transaction Details')]")

	public WebElement TransactionDetails;

	@FindBy(xpath = "//a/span[contains(.,'Oracle Billing')]")
	public WebElement ORACLEBILLING;

	@FindBy(xpath = "//h1[contains(.,'Oracle Billing History')]")
	public WebElement ORACLEBILLINGHEADING;

	@FindBy(xpath = "//select[@id='billingPeriodYear']")
	public WebElement YEAR;

	@FindBy(xpath = "//select[@id='billingPeriodMonth']")
	public WebElement BILLINGMONTH;

	@FindBy(xpath = "//div/label[contains(.,'Cloud Customer Name:')]/../input[@id='customerName']")
	public WebElement CLOUDCUSTOMERNAME;

	@FindBy(xpath = "//div/label[contains(.,'Cloud Customer Number:')]/../input[@id='customerNumber']")
	public WebElement CLOUDCUSTOMERNUMBER;

	@FindBy(xpath = "//a[contains(.,'Export Data')]")
	public WebElement EXPORTDATA;

	@FindBy(xpath = "//div[@class='span6 pull-right text-right']/button[contains(.,'Return to Oracle Billing History')]")
	public WebElement RETURNTOORACLEBILLING;

	@FindBy(xpath = "//div/span[contains(.,'Cloud Customer Name')]")
	public WebElement CLOUDCUSTOMERNAMECOL;

	@FindBy(xpath = "//div/span[contains(.,'Oracle Account Number')]")
	public WebElement ORACLEACCOUNTNUMBERCOL;

	@FindBy(xpath = "//div/span[contains(.,'Oracle Party Number')]")
	public WebElement ORACLEPARTYNUMBERCOL;

	@FindBy(xpath = "//div/span[contains(.,'Oracle Status')]")
	public WebElement ORACLESTATUS;

	@FindBy(xpath = "//div/span[contains(.,'Cloud Customer Number')]")
	public WebElement CLOUDCUSTOMERNUMBERCOL;

	@FindBy(xpath = "//div/span[contains(.,'Subscription')]")
	public WebElement SUBSCRIPTIONCOL;

	@FindBy(xpath = "//div/span[contains(.,'Cloud Customer Status')]")
	public WebElement CLOUDCUSTOMERSTATUS;

	@FindBy(xpath = "//div/span[contains(.,'VAR')]")
	public WebElement VARCOL;

	@FindBy(xpath = "//th[contains(.,'Documents')]")
	public WebElement DOCUMENTSCOL;

	@FindBy(xpath = "//div/span[contains(.,'Payment Status')]")
	public WebElement PAYMENTSTATUSCOL;

	@FindBy(xpath = "//div/span[contains(.,'Transaction Details')]")
	public WebElement TRANSACTIONDETAILSCOL;

	@FindBy(xpath = "//h5[contains(.,'Transaction Details')]")
	public WebElement TRANSACTIONDETAILSHEADING;
	@FindBy(xpath = "//strong[contains(.,'Transaction Type')]")
	public WebElement TRANSACTIONTYPECOL;
	@FindBy(xpath = "//strong[contains(.,'Transaction Date')]")
	public WebElement TRANSACTIONDATECOL;
	@FindBy(xpath = "//strong[contains(.,'Receipt Number')]")
	public WebElement RECEIPTNUMBERCOL;
	@FindBy(xpath = "//strong[contains(.,'Subtotal')]")
	public WebElement SUBTOTALCOL;
	@FindBy(xpath = "//strong[contains(.,'Tax')]")
	public WebElement TAXCOL;
	@FindBy(xpath = "//strong[contains(.,'Grand Total')]")
	public WebElement GRANDTOTALCOL;
	@FindBy(xpath = "//strong[contains(.,'Currency Code')]")
	public WebElement CURRENCYCODECOL;
	@FindBy(xpath = "//a[contains(.,'Hide Details')]")
	public WebElement HIDEDETAILS;
	@FindBy(xpath = "//h1[contains(.,'Initiate Oracle Billing Run')]")
	public WebElement INITIATEORACLEHEADING;
	@FindBy(xpath = "//div/label[contains(.,'Cloud Customer Name:')]/../input")
	public WebElement CUSTOMERNAMERB;
	@FindBy(xpath = "//div/label[contains(.,'Cloud Customer Number:')]/../input")
	public WebElement CUSTOMERNUMBER;
	@FindBy(xpath = "//div[@class='span6 pull-right text-right']/a[contains(.,'Oracle Billing History')]")
	public WebElement ORACLEBILLINGHISTORY;

	@FindBy(xpath = "//td[contains(.,'No records found.')]")
	public WebElement NORECORDS;

	@FindBy(xpath = "//div[@class='span6 pull-right text-right']/a[contains(.,'Return to Oracle Billing History')]")
	public WebElement RETURNORACLEBILLINGHISTORY;

}
