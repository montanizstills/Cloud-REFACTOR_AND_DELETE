/*###################################################################################################
Name: 
Description: 
Author: 
Date Created:
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class VertexSMBCustomers {

	public final String VERTEXSMBCUSTOMERSTABLE_XPATH = "//table/tbody/tr";
	public final String DELET_XPATH = "//p/a[contains(.,'Delete')]";
	public final String DELETREQUEST_XPATH = "//button[contains(text(),'YES, CANCEL THIS CUSTOMER')]";
	public final String BYPASSPENDING_BILLING = "//div/a[contains(.,'By Pass Pending Billing')]";

	@FindBy(xpath = "//span[text()='Vertex Cloud Customers']")
	public WebElement VERTEXSMBLINK;

	@FindBy(xpath = "//div[@class='span4']/select")
	public WebElement VARDROPDOWN;

	@FindBy(xpath = "//input[@id='txt']")
	public WebElement SEARCHTEXTBOX;

	// Columns xpaths
	@FindBy(xpath = "//th/a[text()='Name']")
	public WebElement NAMECOL;

	@FindBy(xpath = "//th/a[text()='Address']")
	public WebElement ADDRESSCOL;

	@FindBy(xpath = "//th/a[text()='City']")
	public WebElement CITYCOL;

	@FindBy(xpath = "//th/a[text()='State']")
	public WebElement STATECOL;

	@FindBy(xpath = "//th/a[text()='Zip']")
	public WebElement ZIPCOL;

	@FindBy(xpath = "//th/a[text()='Phone']")
	public WebElement PHONECOL;

	@FindBy(xpath = "//th/a[text()='Status']")
	public WebElement STATUSCOL;

	@FindBy(xpath = "//th/a[contains(text(),'Subscription')]")
	public WebElement SUBSCRIPTIONCOL;

	@FindBy(xpath = "//th[text()='Reports']")
	public WebElement REPORTSCOL;

	@FindBy(xpath = "//th/a[text()='Returns']")
	public WebElement RETURNSCOL;

	@FindBy(xpath = "//th/a[text()='Transactions']")
	public WebElement TRANSACTIONSCOL;

	@FindBy(xpath = "//th[text()='Admin']")
	public WebElement ADMINCOL;

	@FindBy(xpath = "//th/a[text()='User(s)']")
	public WebElement USERSCOL;

	@FindBy(xpath = "//a[@id='lnkGolive']")
	public WebElement GOLIVE;

	@FindBy(xpath = "//a[contains(text(),'Reports')]")
	public WebElement REPORTS;

	@FindBy(xpath = "//a[text()='All Reports']")
	public WebElement ALLREPORTS;

	@FindBy(xpath = "//a[text()='Client Data Report']")
	public WebElement CDATAREPORT;

	@FindBy(xpath = "//div[@class='pull-right']/a[@class='btn btn-primary']")
	public WebElement BACKBTN;

	@FindBy(xpath = "//a[@class='btn btn-primary pull-right']")
	public WebElement ADBACKBTN;

	@FindBy(xpath = "//h2[contains(text(),' - All Reports')]")
	public WebElement ALLREPORTSTITLE;

	@FindBy(xpath = "//h2[contains(text(),' - Client Data Reports ')]")
	public WebElement CDREPORTTITLE;

	@FindBy(xpath = "//a[contains(.,'Admin Details')]")
	public WebElement ADMINDETAILS;

	@FindBy(xpath = "//a[text()='Admin Details']")
	public WebElement ADDETAILSTITLE;

	@FindBy(xpath = "//a[contains(.,'Push configuration')]")
	public WebElement ADMINPUSHCONFIG;

	@FindBy(xpath = "//h4[contains(.,'Configuration Status')]/../ul")
	public WebElement CONFIGURATIONSTATUSSECTION;

	@FindBy(xpath = "//a[contains(.,'Yes continue to save change')]")
	public WebElement CONFIGPOPUP;

	@FindBy(xpath = "//i[@class='icon icon-add icon-blue']")
	public WebElement PLUS;

	@FindBy(xpath = "//tr[@style='display: table-row)']")
	public WebElement USERSLIST;

	@FindBy(xpath = "//td[@style='text-align: center)']/a")
	public WebElement TRANSLINK;

	@FindBy(xpath = "//select[@id='rootFolder']")
	public WebElement FILETYPE;

	@FindBy(xpath = "//select[@id='folder2']")
	public WebElement YEAR;

	@FindBy(xpath = "//select[@id='folder3']")
	public WebElement MONTH;

	@FindBy(xpath = "//div[@class='span3']/select[@id='folder4']")
	public WebElement COMPANYCODE;

	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr/td[11]/a")
	public WebElement TRANSCOUNT;

	@FindBy(xpath = "//a[contains(.,'Customer Request Cancel')]")
	public WebElement REQUESTDELETE;

	@FindBy(xpath = "//h3[@id='removeConfirmHeader1']")
	public WebElement WARNING;

	@FindBy(xpath = "//textarea[@id='inputDeleteRequestReason']")
	public WebElement TEXTAREA;

	@FindBy(xpath = "//a[contains(.,'Yes, Complete delete request')]")
	public WebElement DELETEREQUEST;

	@FindBy(xpath = "//div/strong[contains(text(),'Delete Requested')]")
	public WebElement DELETED;

	@FindBy(xpath = "//span[contains(text(),'Manage Customer Cancel')]")
	public WebElement MANAGECUSTOMERDELETE;

	@FindBy(xpath = "//button[contains(text(),'Approve')]")
	public WebElement APPROVE;

	@FindBy(xpath = "//button[contains(text(),'YES, CANCEL THIS CUSTOMER')]")
	public WebElement DELETREQUEST;

	@FindBy(xpath = "//div/strong[contains(text(),'Delete Approved')]")
	public WebElement DELETEDAPP;

	@FindBy(xpath = "//h1[contains(.,'Vertex Cloud Customers Cancel Requests ')]")
	public WebElement SYSAPPADMIN;

	@FindBy(xpath = "//div[@class='pagination pagination-right ng-scope']/ul/li")
	public WebElement PAGINATIONCOUNT;

	@FindBy(xpath = "//div[@class='pagination pagination-right ng-scope']/ul/li[@class='disabled']/a[contains(.,'�')]")
	public WebElement PAGINATIONDISABLE;

	@FindBy(xpath = "//table/tbody/tr")
	public WebElement VERTEXSMBCUSTOMERSTABLE;

	@FindBy(xpath = "//a[@class='btn btn-primary pull-right']")
	public WebElement BACKBUTTON;

	@FindBy(xpath = "//tr/td/a[contains(.,'Select')]")
	public WebElement ACTION;

	@FindBy(xpath = "//a[contains(.,'Customer Configuration')]")
	public WebElement SMBCUSTOMERREPORT;

	@FindBy(xpath = "//a[contains(.,'Create New User')]")
	public WebElement ADMINCREATENEWUSER;

	@FindBy(xpath = "//a[contains(.,'Admin Details')]")
	public WebElement SMBADMIN;

	@FindBy(xpath = "//div/dt[contains(text(),'Company')]/../div/div/dd[contains(.,'Green')]")
	public WebElement SMBADMINCOMPANYDETAILS;

	@FindBy(xpath = "//dl/dt[contains(text(),'Products')]/../div/div/dd[contains(.,'Green')]")
	public WebElement SMBADMINPRODUCTDETAILS;

	@FindBy(xpath = "//dl/dt[contains(text(),'Exceptions')]/../div/div/dd[contains(.,'Green')]")
	public WebElement SMBADMINEXCEPTIONDETAILS;

	// 4.5 Changes
	@FindBy(xpath = "//h2[contains(.,'Select Documents To Download')]")
	public WebElement SELECTDOCSTODOWNLOAD;

	@FindBy(xpath = "//a[contains(.,'Customer Tax Returns')]")
	public WebElement CUSTOMERRETURNS;

	@FindBy(xpath = "//select[@id='tax-payer']")
	public WebElement CUSTOMERRETURNSTAXPAYER;

	// @FindBy(xpath = "//select[@id='filing-period']")
	// public WebElement FILINGPERIOD;

	@FindBy(xpath = "//button[@id='btnGenerateReturns']")
	public WebElement GenerateReturns;

	@FindBy(xpath = "//th[contains(.,'Generate Returns')]/input[@type='checkbox']")
	public WebElement GenerateReturnsChkBox;

	@FindBy(xpath = "//pagination-extension/span/select")
	public WebElement paginationSelect;

	@FindBy(xpath = "//table/thead/tr/th[8]/input")
	public WebElement GenerateReturnsgridchkbox;

	@FindBy(xpath = "//button[contains(.,'Reconciliation Report')]")
	public WebElement ReconReportAdmin;

	@FindBy(xpath = "//button[contains(.,'Credit Report')]")
	public WebElement CreditReportAdmin;

	@FindBy(xpath = "//table[@id='tblManageTaxReturns']")
	public WebElement RETURNSTABLE;

	@FindBy(xpath = "//button[contains(.,'Credit Report')]")
	public WebElement CREDITREPORT;

	@FindBy(xpath = "//button[contains(.,'Download All Returns')]")

	public WebElement DOWNLOADALLRETURNS;
	@FindBy(xpath = "//ul[@class='unstyled list-downloads']/li")
	public WebElement REPORTSLINKS;

	@FindBy(xpath = "//button[@id='btnGenerateReturns' and @disabled='disabled']")
	public WebElement GenerateReturnsDisabled;

	@FindBy(xpath = "//div[@class='alert alert-info ng-scope']")
	public WebElement ReturnsgenerationMessage;

	// 4.5.1 Changes for Delete Customer

	@FindBy(xpath = "//td/a[contains(.,'Delete')]")

	public WebElement DELETE;
	@FindBy(xpath = "//h3[contains(.,'Are you sure, you want to delete this user?')]")

	public WebElement DELETEPOPUP;
	@FindBy(xpath = "//p/a[contains(.,'Delete')]")

	public WebElement DELETECONFIRM;
	@FindBy(xpath = "//table/thead/tr/th/span/span[contains(.,'Status')]/../../../../../tbody/tr/td[contains(.,'Deleted')]")

	public WebElement DELETESTATUS;
	@FindBy(xpath = "//div[@class='alert alert-danger ng-binding'][contains(.,'User has been deleted.')]")

	public WebElement INCORRECTLOGIN;
	@FindBy(xpath = "//a[contains(.,'Reactivate')]")

	public WebElement REACTIVATE;

	// 4.7.2 Changes for Manage SMB Customers Page
	@FindBy(xpath = "//a[contains(text(),'Export')]")

	public WebElement EXPORT;

	@FindBy(xpath = "//a[contains(text(),'EXPORT')]")

	public WebElement EXPORTC;

	@FindBy(xpath = "//a[@class='btn pull-right btn-primary']/../../a")

	public WebElement EXPORT1;
	@FindBy(xpath = "//select[@class='ng-pristine ng-untouched ng-valid']")

	public WebElement RESEND;
	@FindBy(xpath = "//select[@id='rootFolder']")

	public WebElement ROOTFOLDER;
	@FindBy(xpath = "//a[contains(.,'Push configuration')]")

	public WebElement PUSHCONFIGURATION;
	@FindBy(xpath = "//select[@class='ng-pristine ng-untouched ng-valid']")

	public WebElement SELECTDD;
	@FindBy(xpath = "//h3[contains(.,'Warning!')]")

	public WebElement CUSTWARNING;
	@FindBy(xpath = "//textarea[@id='inputCancelRequestReason']")

	public WebElement TEXTAREAA;
	@FindBy(xpath = "//a[contains(.,'Yes, Complete cancel request')]")

	public WebElement YESCONFIRM;
	@FindBy(xpath = "//p/a[contains(.,'Delete')]")

	public WebElement DELET;
	@FindBy(xpath = "//select[@class='ng-pristine ng-untouched ng-valid']")

	public WebElement SELECTSYSAPP;

	@FindBy(xpath = "//h4[contains(.,'Last Login')]/../dl/dd")

	public WebElement LASTLOGIN;

	@FindBy(xpath = "//a[contains(.,'Customer Tax Returns')]")

	public WebElement SMRRETURNS;

	@FindBy(xpath = "//select[@id='tax-payer']")

	public WebElement ADMINTAXPAYER;
	@FindBy(xpath = "//select[@id='filing-period']")

	public WebElement ADMINFILLINGPERIOD;
	@FindBy(xpath = "//button[contains(.,'Credit Report')]")

	public WebElement ADMINCREDITREPORT;
	@FindBy(xpath = "//h2[contains(.,'Generate Report')]")

	public WebElement ADMINTRANSACTIONREPORT;
	@FindBy(xpath = "//select[@name='company']")

	public WebElement ADMINTAXPAYERNAME;
	@FindBy(xpath = "//label[contains(.,'Start Date')]/../input")

	public WebElement ADMINSTRATDATE;
	@FindBy(xpath = "//label[contains(.,'End Date')]/../input")

	public WebElement ADMINENDDATE;
	@FindBy(xpath = "//button[contains(.,'Generate Report')]")

	public WebElement ADMINSUBMIT;
	@FindBy(xpath = "//div[@class='span12']/div[contains(.,'Your report request has been submitted successfully.')]")

	public WebElement ADMINSUCCESSREPORT;
	@FindBy(xpath = "//div/input[@value='AdminTransactionDetails']")

	public WebElement ADMINTRANSACTIONDETAILS;
	@FindBy(xpath = "//div/label[contains(.,'Select folder:')]/../select")

	public WebElement ADMINSELECTFOLDER;
	@FindBy(xpath = "//div/label[contains(.,'Select Internal folder:')]/../select[@id='folder2']")

	public WebElement ADMININTERNALSELECTFOLDER;
	@FindBy(xpath = "//div/label[contains(.,'Select Internal folder:')]/../select[@id='folder3']")

	public WebElement ADMININTERNALSELECTFOLDER2;
	@FindBy(xpath = "//div/span/a[contains(.,'TransactionDetails-All-')]")

	public WebElement PORTALTRANSDETAILSREPORT;
	@FindBy(xpath = "//div/span/a[contains(.,'TransactionSummary-')]")

	public WebElement PORTALTRANSSUMMARYREPORT;
	@FindBy(xpath = "//div//span/a[contains(.,'AdminTransactionDetails-')]")

	public WebElement ADMINTRANSDETAILS;
	@FindBy(xpath = "//li//span/a")

	public WebElement NEGODETAILS;
	@FindBy(xpath = "//input[@value='Export']")

	public WebElement ADMINEXPORT;
	@FindBy(xpath = "//span[text()='My Customers']")

	public WebElement MYCUSTOMERS;
	@FindBy(xpath = "//input[@class='search input-append ng-pristine ng-valid ng-touched']")

	public WebElement QUICKSEARCH;
	@FindBy(xpath = "//input[@class='search input-append ng-pristine ng-untouched ng-valid']")

	public WebElement QUICKSEARCHADMIN;
	@FindBy(xpath = "//button[text()='Cancel Customer'][@disabled='disabled']")

	public WebElement CANCELCUSTDISABLE;
	@FindBy(xpath = "//label[contains(.,'State/Territory')]/../select")

	public WebElement ADMINSTATE;

	@FindBy(xpath = "//div/span/a[contains(.,'AdminTransactionSummary-')]")

	public WebElement ADMINTRANSSUMMARY;

	/*
	 * @FindBy(xpath="//a[contains(.,'Cloud Customer Tax Returns')]")
	 * 
	 * public WebElement SMBCUSTOMERTAXRETURNS;
	 */

	@FindBy(id = "filing-period")
	public WebElement FILINGPERIOD;

	@FindBy(xpath = "//button[contains(.,'Download All Returns')]")

	public WebElement DOWNLOADALLRETURNSADMIN;

	@FindBy(xpath = "//button[contains(.,'Export Edi List')]")

	public WebElement EXPORTEDILIST;

	@FindBy(xpath = "//table[@id='tblManageTaxReturns']/thead/tr/th[7]/div/span[contains(.,'File & Pay')]")

	public WebElement ADMINFILEANDPAY;

	@FindBy(id = "//a[@id='confirm-remove-certificates']")

	public WebElement ADMINSAVECHANGES;

	@FindBy(xpath = "//ul/li[1]/span/a")

	public WebElement REPORTFROMFOLDER;

	@FindBys({ @FindBy(xpath = "//ul/li/span/a"), })
	public List<WebElement> REPORTLISTFROMFOLDER;

	@FindBy(xpath = "//span[contains(.,'Customer Date Added')]")

	public WebElement CUSTOMERDATEADDED;
	@FindBy(xpath = "//span[text()='Date Added']")

	public WebElement DATEADDED;

	@FindBy(xpath = "//span[text()='Manage Customers']")

	public WebElement MANAGESMBCUSTLINK;

	@FindBy(xpath = "//span[contains(.,'Manage O-Series Config')]")

	public WebElement MANAGEOSERIESCONFIG;

	@FindBy(xpath = "//label[contains(.,'VAR Name')]/../input[@type='text']")

	public WebElement Varname;

	@FindBy(xpath = "//label[contains(text(),'Cloud Customer Name')]/../input")

	public WebElement SMBCUSTNAMESB;

	@FindBy(xpath = "//table/tbody/tr[2]/td/div/a[@class='ng-binding']")

	public WebElement SELECT;

	@FindBy(xpath = "//a[contains(.,'Customer Reports')]")

	public WebElement SMBREPORTS;

	@FindBy(xpath = "//a[contains(.,'Customer Tax Returns')]")

	public WebElement SMBCUSTOMERTAXRETURNS;

	@FindBy(xpath = "//a[contains(.,'Customer Detail')]")

	public WebElement CLOUDCUSTOMERDETAILS;

	@FindBy(xpath = "//div/div/a[contains(.,'Create New User')]")

	public WebElement CREATENEWUSER;

	@FindBy(xpath = "//input[@id='firstName']")

	public WebElement FIRSTNAME;

	@FindBy(xpath = "//input[@id='LastName']")

	public WebElement LASTNAME;

	@FindBy(xpath = "//input[@id='Email']")

	public WebElement EMAIL;

	@FindBy(xpath = "//input[@id='phone']")

	public WebElement PHONE;
	@FindBy(xpath = "//button[contains(.,'Send Invite')]")

	public WebElement SENDINVITE;
	@FindBy(xpath = "//p[3]/a")

	public WebElement LINK;
	@FindBy(xpath = "//input[@id='user-name']")

	public WebElement USERNAME;
	@FindBy(xpath = "//input[@id='UserName']")

	public WebElement USERNAME1;
	@FindBy(xpath = " //input[@id='Password']")

	public WebElement NEWPASSWORD1;
	@FindBy(xpath = "//input[@id='password']")

	public WebElement NEWPASSWORD;
	@FindBy(xpath = "//input[@id='PasswordConfirm']")

	public WebElement CONFIRMNEWPASSWORD1;
	@FindBy(xpath = "//input[@id='confirm-password']")

	public WebElement CONFIRMNEWPASSWORD;
	@FindBy(xpath = "//input[@id='agreedToToS']")

	public WebElement ACCEPTEDTERM;
	@FindBy(xpath = "//input[@id='AcceptedTerms']")

	public WebElement ACCEPTEDTERMS;
	@FindBy(xpath = "//div[@class='alert alert-warning']")

	public WebElement COMPLETEALERT;
	@FindBy(xpath = "//a[@class='text-right pull-right btn btn-primary']")

	public WebElement BILLINGUPDATE;

	@FindBy(xpath = "//input[@id='agreedToSA']")

	public WebElement SERVICEAGREEMENT;

	@FindBy(xpath = "//button[@type='submit']")

	public WebElement COMPLETE;

	@FindBy(xpath = "//span[contains(.,'Dashboard')]")

	public WebElement Dashboard;

	@FindBy(xpath = "//div/a[contains(.,'Modify VAR')]")

	public WebElement MODIFYVAR;
	@FindBy(xpath = "//div/select")

	public WebElement VARLIST;
	@FindBy(xpath = "//button[contains(.,' Save')]")

	public WebElement SAVE;

	@FindBy(xpath = "//th/input")

	public WebElement GENERATERETURNS;

	@FindBy(xpath = "//button[@id='btnGenerateReturns']")

	public WebElement GENERATERETURNSBUTTON;

	@FindBy(xpath = "//button[contains(.,'Generating Returns')]")

	public WebElement GENERATEINGRETURNS;

	@FindBy(xpath = "//div/span[contains(.,'Current VAR:')]/../span/..")

	public WebElement CURRENTVAR;

	@FindBy(xpath = " //select[@ng-model='rptValueSelected']")

	public WebElement ReportDropdown;

	@FindBy(xpath = "//pagination-extension/span/select")

	public WebElement paginationDD;

	@FindBy(xpath = "//table[@sort-table='data.jurisdictionTable.column']/../div/div[@class='span5 pull-left']/pagination-extension/span/select")

	public WebElement PaginationCC;

	@FindBy(xpath = "//select[@id='folder4']")

	public WebElement internalFolder4;

	@FindBy(xpath = "//div/a[contains(.,'By Pass Pending Billing')]")

	public WebElement BYPASSPENDINGBILLING;

	@FindBy(xpath = "//a[@id='confirm-remove']")

	public WebElement YES;

	@FindBy(xpath = " //select[@id='taxpayer']")

	public WebElement ADMINPREPAYMENTTAXPAYER;

	@FindBy(xpath = "//h4[contains(.,'OSeries Information')]")

	public WebElement OSERIESINFO;

	@FindBy(xpath = "//dt[contains(.,'Trusted ID:')]")

	public WebElement TRUSTEDID;

	@FindBy(xpath = "//dt[contains(.,'Config Pod ID:')]")

	public WebElement CONFIGPODID;

	@FindBy(xpath = "//dt[contains(.,'Config Partition Name:')]")

	public WebElement CONFIGPARTITIONNAME;

	@FindBy(xpath = "//dt[contains(.,'Calc Pod ID:')]")

	public WebElement CALCPODID;

	@FindBy(xpath = "//dt[contains(.,'Calc Partition Name:')]")

	public WebElement CALCPPARTITIONNAME;
	@FindBy(xpath = "//dt[contains(.,'Trusted ID:')]/following::dd[1]")

	public WebElement TRUSTEDID_VALUE;

	@FindBy(xpath = "//dt[contains(.,'Config Pod ID:')]/following::dd[1]")

	public WebElement CONFIGPODID_VALUE;

	@FindBy(xpath = "//dt[contains(.,'Config Partition Name:')]/following::dd[1]")

	public WebElement CONFIGPARTITIONNAME_VALUE;

	@FindBy(xpath = "//dt[contains(.,'Calc Pod ID:')]/following::dd[1]")

	public WebElement CALCPODID_VALUE;

	@FindBy(xpath = "//dt[contains(.,'Calc Partition Name:')]/following::dd[1]")

	public WebElement CALCPPARTITIONNAME_VALUE;

	@FindBy(xpath = "//div/div/span[contains(.,'Payment Type:')]/..")

	public WebElement PaymentType;

	@FindBy(xpath = "//div/div/span[contains(.,'Payment Type:')]/../../div[2]")

	public WebElement Paymentvalue;

	@FindBy(xpath = "//div/span[contains(.,'Billing Address:')]/..")

	public WebElement BillingAddress;

	@FindBy(xpath = "//div/span[contains(.,'Ship To Address:')]/..")

	public WebElement ShipToAddress;

	@FindBy(xpath = "//div[3]/div/div[contains(.,'Address:')]/..")

	public WebElement Address;

	@FindBy(xpath = "//a[contains(.,'Customer Prepayments')]")

	public WebElement CustomerPrepayments;

	@FindBy(xpath = "//a[contains(.,'Connectors')]")

	public WebElement Connectors;

	@FindBy(xpath = "//span[contains(.,'ID Number')]/../../../../../tbody/tr[@class='ng-scope']/td[4]")

	public WebElement ID_Number_CustomerDetails;

	@FindBy(xpath = "//span[contains(.,'ID Number')]/../span[2]")

	public WebElement ID_Number_CustomerConfig;

	@FindBy(xpath = "//span[contains(.,'Phone Number:')]/..")

	public WebElement Phonenumber;

	@FindBy(xpath = "//span[contains(.,'Contract Number:')]/..")

	public WebElement Contractnumber;

	@FindBy(xpath = "//th/div/span[contains(.,'Company Code')]/../../../th/div/span[contains(.,'Company Name')]/../../../th/div/span[contains(.,'Company Status')]/../../../th/div/span[contains(.,'ID Number')]/../../../th/div/span[contains(.,'Go Live Date')]/../../../th/div/span[contains(.,'Start Date')]/../../../th/div/span[contains(.,'End Date')]/../../../th/div/span[contains(.,'Is Filing Entity')]")

	public WebElement COMPANYDETAILSTABLE;

	@FindBy(xpath = "//th/div/span[contains(.,'First Name')]/../../../th/div/span[contains(.,'Last Name')]/../../../th/div/span[contains(.,'Email')]/../../../th/div/span[contains(.,'User Name')]/../../../th/div/span[contains(.,'Phone')]/../../../th/div/span[contains(.,'Created')]/../../../th/div/span[contains(.,'Last Login')]/../../../th/div/span[contains(.,'Status')]/../../../th/div/span[contains(.,'Role')]/../../../th[contains(.,'Designated Role')]/../th[contains(.,'Action')]")

	public WebElement USERSTABLE;

	@FindBy(xpath = "//a[text()='Edit Customer Subscription']")

	public WebElement EDITCUSTOMERSUBSCRIPTION;

	@FindBy(xpath = "//a[contains(.,'Customer Subscription')]")

	public WebElement CUSTOMERSUBSCRIPTION;

	@FindBy(xpath = "//a[contains(.,'Edit Customer Subscription')]")

	public WebElement EditSubscription;

	@FindBy(xpath = "//a/span[contains(.,'See Details')]")

	public WebElement SEEDETAILS;

	@FindBy(xpath = "//a[contains(.,'+ View Warning')]")

	public WebElement VIEWWARNING;

	@FindBy(xpath = "//strong[contains(.,'WARNING: Currently Expired Tax Areas:')]")

	public WebElement WARNINGMESSAGE;

	@FindBy(xpath = "//p[contains(.,'The following business locations have tax areas with expiration dates within the range of this Retail Extract.')]")

	public WebElement WARNINGTEXT;

	@FindBy(xpath = "//a[contains(.,'- Close Warning')]")

	public WebElement CLOSEWARNING;

	@FindBy(xpath = "//label[contains(.,'Features')]/../Select")

	public WebElement Featuresdropdwn;

	@FindBy(xpath = "//label[contains(.,'Subscription')]/../Select")

	public WebElement Subsciptiondropdwn;

	@FindBy(xpath = "//b[contains(.,'CLIENT INFORMATION')]")

	public WebElement ClientInformation;

	@FindBy(xpath = "//span[contains(text(),'This is a Standalone ECW client, this section will be empty.')]")

	public WebElement SECTIONEMPTY;

	@FindBy(xpath = "//li/a[contains(text(),'Partition Configuration')]")

	public WebElement PartitonConfiguration;

	@FindBy(xpath = "//h2[contains(.,'Exemption Certificate Wizard')]")

	public WebElement ECWHeaderText;

	@FindBy(xpath = "//span[contains(.,'URL:')]")

	public WebElement ECWURL;

	@FindBy(xpath = "//span[contains(.,'Client Code:')]")

	public WebElement ECWClientCode;

	@FindBy(xpath = "//a[contains(.,'Modify VAR')]/../a[@disabled='disabled']")

	public WebElement ModifyVardisabled;

	@FindBy(xpath = "//button[contains(.,'Edit Company Configuration')]/../button[@disabled='disabled']")

	public WebElement Editcompcapdisabled;

	@FindBy(xpath = "//button[contains(.,'Cancel Customer')]/../button[@disabled='disabled']")

	public WebElement cancustdisabled;

	@FindBy(xpath = "//a[contains(.,'Generate API Credentials')]")

	public WebElement GenerateAPIlink;

	@FindBy(xpath = "//h1[contains(.,'Audit Metric Data')]")

	public WebElement AuditMeticData;

	@FindBy(xpath = "//input[@id='startdate']")

	public WebElement StartDate;

	@FindBy(xpath = "//input[@id='Enddate']")

	public WebElement EndDate;

	@FindBy(xpath = "//a[@class='btn btn-primary pull-right ng-scope'][contains(.,'Invite User')]")

	public WebElement InviteUsers;

	@FindBy(xpath = "//tr[2]/td/a[contains(.,'Delete')]")

	public WebElement Deletelink;

	@FindBy(xpath = "//a[contains(.,'Partition Configuration')]")

	public WebElement PartitionConfiguration;

	@FindBy(xpath = "//span[contains(.,'Cloud Customer Name:')]")

	public WebElement CustomerName;

	@FindBy(xpath = "//span[contains(.,'Trusted Id:')]")

	public WebElement TrustedId;

	@FindBy(xpath = "//span[contains(.,'Status:')]")

	public WebElement Status;

	@FindBy(xpath = "//span[contains(.,'Cloud Customer Number:')]")

	public WebElement CustomerNumber;

	@FindBy(xpath = "//span[contains(.,'Current VAR:')]")

	public WebElement CurrentVAR;

	@FindBy(xpath = "//span[contains(.,'Account Type:')]")

	public WebElement AccountType;

	@FindBy(xpath = "//span[contains(.,'Subscription:')]")

	public WebElement Subscription;

	@FindBy(xpath = "//span[contains(.,'API Key:')]")

	public WebElement APIKey;

	@FindBy(xpath = "//span[contains(.,'Taxpayer Partition Configuration:')]")

	public WebElement Taxpartconfig;

	@FindBy(xpath = "//span[contains(.,'Taxpayer Partitions Used:')]")

	public WebElement Taxpartused;

	@FindBy(xpath = "//span[contains(.,'API Password:')]")

	public WebElement APIPassword;

	@FindBy(xpath = "//span[contains(.,'Partition Configuration Override:')]")

	public WebElement PartitionConfigOverride;

	@FindBy(xpath = "//a[contains(.,'Calculation Precedence')]/i")

	public WebElement CalculationPrecedence;
	
	@FindBy(xpath = "//a[contains(.,'Entity Limits')]/i")
	public WebElement ENTITYLIMITS;

	@FindBy(xpath = "//span[contains(.,'Setting Name')]")

	public WebElement SETTINGNAME;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Setting Value')]")

	public WebElement SettingValue;

	@FindBy(xpath = "//span[contains(.,'Source')]")

	public WebElement Source;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Actions')]")

	public WebElement Actions;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Child Settings')]")

	public WebElement Childsettings;

	@FindBy(xpath = "//a[contains(.,'Calculation Precedence')]/../../../div/table/tbody/tr[2]/td[contains(.,'Procurement - Commodity')]/../td/input")

	public WebElement ParentCateValue;

	@FindBy(xpath = "//a[contains(.,'Calculation Precedence')]/../../../div/table/tbody/tr[2]/td[contains(.,'Procurement - Commodity')]/../td[contains(.,'Vertex Defined')]")

	public WebElement DefaultSourceName;

	@FindBy(xpath = "//input[@value='Save']")

	public WebElement SAVECATEGORY;

	@FindBy(xpath = "//div/strong[contains(.,'Success!')]/../../div[contains(.,'Config partition settings overridden successfully.')]")

	public WebElement SUCCESSMESSAGE;

	@FindBy(xpath = "//a[contains(.,'Address Cleansing')]/../../../div/table/tbody/tr[4]/td[contains(.,'Continue If Address Cleansing Unavailable')]/../td/input")

	public WebElement ADDRESSCLEANSINGVALUE;

	@FindBy(xpath = "//a[contains(.,'Address Cleansing')]")

	public WebElement AddressCleansing;

	@FindBy(xpath = "//a[contains(.,'TaxGIS')]")

	public WebElement TAXGIS;

	@FindBy(xpath = "//a[contains(.,'TaxGIS')]/../../../div/table/tbody/tr[2]/td[contains(.,'Alternative Region Type Lists')]/../td/a[contains(.,'Add')]")

	public WebElement AddLink;

	@FindBy(xpath = "//h3[contains(.,'Override Information')]")

	public WebElement OVERRIDEiNFO;

	@FindBy(id = "childName")

	public WebElement CHILDNAME;

	@FindBy(id = "save-ok")

	public WebElement SAVECHILDNAME;

	@FindBy(xpath = "//button[contains(.,'�')]")

	public WebElement POPUPCLOSE;

	@FindBy(xpath = "//a[contains(.,'Cancel')]")

	public WebElement CANCEL;

	@FindBy(xpath = "//a[contains(.,'TaxGIS')]/../../../div/table/tbody/tr[2]/td[contains(.,'Alternative Region Type Lists')]/../td/a[contains(.,'Add')]/../../td/a[contains(.,'See Details')]")

	public WebElement SEEDETAILSLINK;

	@FindBy(xpath = "//a[contains(.,'TaxGIS')]/../../../div/table/tbody/tr[2]/td[contains(.,'Alternative Region Type Lists')]/../td/a[contains(.,'Add')]/../../td/a[contains(.,'Close')]")

	public WebElement CLOSE;

	@FindBy(xpath = "//a[contains(.,'Calculation Precedence')]/../../../div/table/tbody/tr[2]/td[contains(.,'Procurement - Commodity')]/../td/a[contains(.,'Delete')]")

	public WebElement DELETEPARENTCATEGORY;

	@FindBy(xpath = "//button[contains(.,'EXPORT')]")

	public WebElement EXPORTBUTTON;

	@FindBy(xpath = "//span[contains(.,'This is a Standalone ECW client, this section will be empty.')]")

	public WebElement ECWTEXT;

	@FindBy(xpath = "//input[@value='Save'][@disabled='disabled']")

	public WebElement SAVEDISABLE;

	@FindBy(xpath = "//p/b[contains(.,'CLIENT INFORMATION')]")

	public WebElement CLIENTINFORMATION;

	@FindBy(xpath = "//div[4][contains(.,'Authorized')]/span[contains(.,'Status:')]")

	public WebElement STATUS;
	@FindBy(xpath = "//div[@class='span4 control-group']/select[@name='company']")

	public WebElement CompanyName;

	@FindBy(xpath = "//strong[contains(.,'Are you sure you want to delete this location?')]")

	public WebElement DeleteLocationPopUp;

	@FindBy(xpath = "//a/span[contains(.,'Delete location')]")

	public WebElement DeleteLocationBtn;

	@FindBy(xpath = "//strong[contains(.,'Cancel')]")

	public WebElement CancelBtn;

	@FindBy(xpath = "//strong[contains(text(),'Forbidden')]")

	public WebElement Forbiddeerror;

	@FindBy(xpath = "//a[contains(text(),'Ok')]")

	public WebElement okbutton;

	@FindBy(xpath = "//button[contains(.,'Payment Request Export')]")

	public WebElement PremiumrequestExport;

	@FindBy(xpath = "//h3[contains(.,'This Customer has no configured Companies')]")

	public WebElement NoConfiguredCompaniesText;

	@FindBy(xpath = "//h2[contains(.,'Returns Integration Instances')]")

	public WebElement RETURNSONLYINTEGRATIONINSTANCE;

	@FindBy(xpath = "//table/thead/tr/th/div[contains(.,'Date Added')]")

	public WebElement RODATEADDED;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'Returns Instance Name')]")

	public WebElement RETURNSONLYINSTANCENAMECOL;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td/a[text()='Resend']")

	public WebElement Resendcustomer;

	@FindBy(xpath = "//h3[@id='removeConfirmHeader1']")
	public WebElement EmailResent;
	@FindBy(xpath = "//a[@id='confirm-remove']")
	public WebElement ConfirmResent;

	@FindBy(xpath = "//strong[contains(.,'In order for you to properly configure your Quickbooks Enterprise API, you will need to install the Ground2Cloud connector.')]/a[contains(.,'Click here')]/../../strong[contains(.,' to download, or contact customer support at')]/a[contains(.,'Cloudsupport@vertexinc.com')]")
	public WebElement QBENTERPRISETEXT;

	@FindBy(xpath = "//div[@class='span4 ng-scope']/input")
	public WebElement QBECLIENTID;

	@FindBy(xpath = "//select[@id='roles1']")
	public WebElement USerrole;

	@FindBy(xpath = "//a[contains(.,'Permanently Delete Customer')]")
	public WebElement PermanentDelete;

	@FindBy(xpath = "//a[contains(.,'Yes')]")
	public WebElement PermanentDeleteYes;

	@FindBy(xpath = "//h1[contains(.,'Manage Cloud Customer')]")
	public WebElement ManageCloudCustomerheader;

	@FindBy(xpath = "//a[contains(.,'Change Oracle Party Number')]")
	public WebElement CHANGEORACLEPARTYNUMBER;

	@FindBy(xpath = "//h3[contains(.,'Change Oracle Party Number')]")
	public WebElement CHANGEORACLENUMBERHEADING;

	@FindBy(xpath = "//h5[contains(.,'Change this customer')]")
	public WebElement CHANGEPARTYNUMBERTEXT;

	@FindBy(xpath = "//company-overview/div/div/div/span[contains(.,'Customer Name:')]")
	public WebElement CUSTOMERNAME;

	@FindBy(xpath = "//div/div/div/span[contains(.,'Current OraclePartyNumber:')]")
	public WebElement CURRENTORACLEPARTYNUMBER;

	@FindBy(id = "oraclePartyNumber")
	public WebElement ORACLEPARTYNUMBER;

	@FindBy(xpath = "//div[contains(.,'Oracle party number already exists.')]")
	public WebElement DUPORACLEPARTYNUMBER;

	@FindBy(xpath = "//button[@id='save-ok']")
	public WebElement SAVEORACLEPARTYNUMBER;

	@FindBy(xpath = "//a[@id='save-cancel']")
	public WebElement CANCELORACLEPARTYNUMBER;

	@FindBy(xpath = "//form/div/div/div[contains(.,'Invalid Oracle Party Number, must be numeric at 6 digits.')]")
	public WebElement OACLEPARTYNUMBERERROR;

	@FindBy(xpath = "//a[@id='confirm-remove']")
	public WebElement CONFIRMSAVE;

	@FindBy(xpath = "//span[contains(.,'Premium Customers')]")
	public WebElement PremiumCustomers;

	@FindBy(xpath = "//a[contains(.,'Download Return Files')]")
	public WebElement DownloadRetunrFiles;

	@FindBy(xpath = "//h1[contains(.,'Download Return Files')]")
	public WebElement DownloadRetunrFilesLabel;

	@FindBy(xpath = "//table/tbody/tr/td/a[contains(.,'Download')]")
	public WebElement Downloadlnk;

	@FindBy(xpath = "//div/label[contains(.,'Status')]/../select")
	public WebElement clientStatus;

	@FindBy(xpath = "//a[contains(.,'Customer Listing')]")
	public WebElement CustomerListinglink;

	@FindBys({

			@FindBy(xpath = "//table/tbody/tr"), })
	public List<WebElement> noofClients;

	@FindBy(xpath = "//button[contains(.,'Payment Request Export')]")
	public WebElement paymentrequestExport;

	@FindBy(xpath = "//a[@id='logout']")
	public WebElement VTMSLOGOUT;

	@FindBy(xpath = "//a[@href='#account']/i")
	public WebElement VTMSACCOUNT;

	@FindBy(xpath = "//a[contains(.,'EXPORT')]")
	public WebElement CUSTOMERLISTINGEXPORT;

	@FindBy(xpath = "//span[contains(.,'Return Generation for selected filing period has been requested.')]")
	public WebElement returngenerationrequested;

	@FindBy(xpath = "//label[text()='Cloud Customer Name']/following-sibling::input")
	public WebElement cloudCustomerNameSearchBox;

	@FindBy(xpath = "//a[contains(.,'Customer Configuration')]")
	public WebElement customerConfigurationTab;

	@FindBy(xpath = "//span[text()='Cloud Customer Name:']/parent::div[@class='span4 ng-binding']")
	public WebElement cloudCustomerNameValue;

	@FindBy(xpath = "//span[text()='Cloud Customer Id:']/parent::div[@class='span3 ng-binding']")
	public WebElement cloudCustomerIDValue;

	@FindBy(xpath = "//span[text()='Trusted Id:']/parent::div[@class='span3 ng-binding']")
	public WebElement trustedIDValue;

	@FindBy(xpath = "//span[text()='Status:']/parent::div[@class='span2 ng-binding']")
	public WebElement statusValue;

	@FindBy(xpath = "//span[text()='Cloud Customer Number:']/parent::div[@class='span4 ng-binding']")
	public WebElement cloudCustomerNumberValue;

	@FindBy(xpath = "//span[text()='Current VAR:']/parent::div[@class='span3 ng-binding']")
	public WebElement currentVARValue;

	@FindBy(xpath = "//span[text()='Account Type:']/parent::div[@class='span2 ng-binding']")
	public WebElement accountTypeValue;

	@FindBy(xpath = "//span[text()='Subscription:']/parent::div[@class='span4 ng-binding']")
	public WebElement subscriptionValue;

	@FindBy(xpath = "//span[text()='API Key:']/parent::div[@class='span2 ng-binding']")
	public WebElement apiKeyValue;

	@FindBy(xpath = "//span[text()='Taxpayer Partition Configuration: ']/parent::div[@class='ng-scope']")
	public WebElement taxpayerPartitionConfigurationValue;

	@FindBy(xpath = "//span[text()='Taxpayer Partitions Used:']/parent::div[@class='span3 ng-binding']")
	public WebElement taxpayerPartitionsUsedValue;

	@FindBy(xpath = "//span[text()='API Password:']/parent::div[@class='span2 ng-binding']")
	public WebElement apiPasswordValue;

	@FindBy(xpath = "//span[text()='Partition Configuration Override:']/parent::div[@class='span4 ng-binding']")
	public WebElement partitionConfigurationOverrideValue;

	@FindBy(xpath = "//span[text()='Oracle Account Number:']/parent::div[@class='span4 ng-binding']")
	public WebElement oracleAccountNumberValue;

	@FindBy(xpath = "//span[text()='Oracle Party Number:']/parent::div[@class='span4 ng-binding']")
	public WebElement oraclePartyNumberValue;

	@FindBy(xpath = "//select[@id='taxpayersList']")
	public WebElement companyDropDown;

	@FindBy(xpath = "//span[text()='Company Name:']/following-sibling::span")
	public WebElement companyNameValue;

	@FindBy(xpath = "//span[text()='Start Date:']/following-sibling::span")
	public WebElement startDateValue;

	@FindBy(xpath = "//span[text()='ID Number:']/following-sibling::span")
	public WebElement idNumberValue;

	@FindBy(xpath = "//span[text()='Company Code:']/following-sibling::span")
	public WebElement companyCodeValue;

	@FindBy(xpath = "//span[text()='End Date:']/following-sibling::span")
	public WebElement endDateValue;

	@FindBy(xpath = "//span[text()='Filing Entity:']/following-sibling::span")
	public WebElement filingEntityValue;

	@FindBy(xpath = "//div[text()='Address:']/following-sibling::div")
	public WebElement addressValue;

	@FindBy(xpath = "//span[text()='Company Status:']/following-sibling::span")
	public WebElement companyStatusValue;

	@FindBy(xpath = "//span[text()='Go Live Date:']/following-sibling::span")
	public WebElement goLiveDateValue;

	@FindBy(xpath = "//label[contains(.,'Cloud O Series')]")
	public WebElement cloudoserieslabel;

	@FindBy(xpath = "//label[contains(.,'Cloud O Series')]/../select")
	public WebElement cloudoseriesselect;

	@FindBy(xpath = "//th[@sort-header='cloudOseries']//div[@class='tablesorter-header-inner']")
	public WebElement cloudOseriescolumnheader;

	@FindBy(xpath = "//div[normalize-space()='Professional: Cloud Oseries - Tax Calculations']")
	public WebElement cloudoseriessubscrition;

	@FindBy(xpath = "//td[normalize-space()='No records found.']")
	public WebElement norecordsfound;

	//@FindBy(xpath = "//span[@ng-show='vm.data.userDetailId == company.companyId']") -- Changed as of R8.15 sprint 6.4 code
	//public WebElement oSeriesPassword;

	//@FindBy(xpath = "//h4[contains(text(),'OSeries Information')]/following-sibling::dl[@class='dl-horizontal']/dd[4]") -- -- Changed as of R8.15 sprint 6.4 code
	//public WebElement oSeriesUserNameValue;
	
	@FindBy(xpath = "//div[@ng-show='vm.data.userDetailId == company.companyId']/span[1]")
	public WebElement oSeriesUserNameValue;
		
	@FindBy(xpath = "//div[@ng-show='vm.data.userDetailId == company.companyId']/span[2]")
	public WebElement oSeriesPassword;	

	@FindBy(xpath = "//button[@aria-label='Lanch Site']")
	public WebElement launchSiteButton;

	@FindBy(xpath = "//input[@id='username']")
	public WebElement oSeriesLoginUserName;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement oSeriesLoginPassword;

	@FindBy(xpath = "//button[@id='Login_button']")
	public WebElement oSeriesLoginButton;

	@FindBy(xpath = "//button[contains(.,'Generate')]")
	public WebElement generateButton;

	@FindBy(xpath = "//div[contains(text(),'Accelerator - Oracle ERP Cloud')]")
	public WebElement Oracle_ERP_Cloud;

	@FindBy(xpath = "//button[contains(.,'Show Password')]")
	public WebElement show_password;

	@FindBy(xpath = "//span[contains(.,'T@xes1!')]")
	public WebElement RO_password;

	@FindBy(xpath = "//a[contains(.,'Delete')]")
	public WebElement DELETELINK;
	
	@FindBy(xpath = "//button[@data-testid='my-enterprise']")
	public WebElement oSeriesMyEnterprise;
	
	@FindBy(xpath = "//a[contains(text(), 'Taxpayers')]")
	public WebElement oSeriesTaxpayers;
	
	@FindBy(xpath = "//input[@id='lastPassProcessId']")
	public WebElement lastPassPassword;
	
	@FindBy(xpath = "//button[@id='save-ok']")
	public WebElement seeReadWriteUserButton;

	@FindBy(xpath = "//div[@ng-if='data.showReadWritePassword']")
	public WebElement oSeriesReadWriteUser;
	
	@FindBy(xpath = "//button[contains(text(), 'x')]")
	public WebElement closeButton;
	
	//@FindBy(xpath = "//div[@class='perspective']//span[@class='ant-select-selection-item']")
	@FindBy(xpath = "//div[@data-testid='bus-event-select']/div[@class='ant-select-selector']")
	public WebElement selectingOSeriesServices;
	
	@FindBy(xpath="//table/tbody/tr/td[contains(.,'Customer Limit')]/../td/input")
	public WebElement CUSTOMERLIMITS;
	
	@FindBy(xpath="//table/tbody/tr/td[contains(.,'Exemption Certificate Limit')]/../td/input")
	public WebElement CERTIFICATELIMITS;
	
	@FindBy(xpath="//table/tbody/tr/td[contains(.,'Taxability Driver Limit')]/../td/input")
	public WebElement DRIVERLIMITS;
	
	@FindBy(xpath="//table/tbody/tr/td[contains(.,'Taxability Mapping Limit')]/../td/input")
	public WebElement MAPPINGLIMITS;
	
	@FindBy(xpath="//table/tbody/tr/td[contains(.,'Taxpayer Limit')]/../td/input")
	public WebElement TAXPAYERLIMITS;
	
	@FindBy(xpath="//table/tbody/tr/td[contains(.,'Vendor Limit')]/../td/input")
	public WebElement VENDORLIMIT;
	

	
}