package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminUserLinks {

	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	public WebElement DASHBOARD;

	@FindBy(xpath = "//a/span[contains(text(),'Lookup Tax Rates')]")
	public WebElement LOOKUPTAXRATESLINK;

	@FindBy(xpath = "//span[contains(text(),'Rates & Jurisdictions')]")
	public WebElement RatesJurisdictions;

	@FindBy(xpath = "//a/span[contains(text(),'Manage Billing')]")
	public WebElement MANAGEBILLING;

	@FindBy(xpath = "//li/a[contains(text(),'Cloud Customer Lookup')]")
	public WebElement CLIENTLOOKUP;

	@FindBy(xpath = "//a/span[text()='Metrics']")
	public WebElement METRICSLINK;

	@FindBy(xpath = "//a/span[text()='Customer ID Report']")
	public WebElement CUSTIDREPORT;

	@FindBy(xpath = "//a/span[text()='Document Center']")
	public WebElement DOCCENTER;

	@FindBy(xpath = "//a[@title='Invite Users']")
	public WebElement INVITEUSER;

	@FindBy(xpath = "//a/span[contains(.,'Invite Users')]")
	public WebElement INVITEUSERLINK;

	@FindBy(xpath = "//a/span[contains(text(),'Premium Customers')]")
	public WebElement PREMSMBCUST;

	@FindBy(xpath = "//a/span[contains(text(),'Vertex Cloud VARS')]")
	public WebElement VERTEXSMBVARS;

	@FindBy(xpath = "//a/span[contains(text(),'Inline Help Editor')]")
	public WebElement INLINEHELP;

	@FindBy(xpath = "//span[text()='Update Notification']")
	public WebElement UPDATENOTIFICATIONLINK;

	@FindBy(xpath = "//a/span[contains(text(),'Manage Returns Config')]")
	public WebElement MANAGECONFIGURATION;

	@FindBy(xpath = "//a/span[contains(text(),'Manage Tax Calendar')]")
	public WebElement MANAGETAXCALENDER;

	@FindBy(xpath = "//a/span[contains(text(),'Error Monitor')]")
	public WebElement ERRORMONITOR;

	@FindBy(xpath = "//a[@id='AuditMetricData']")
	public WebElement AUDITMETRICDATA;

	@FindBy(xpath = "//a[@id='RateFileMetricData']")
	public WebElement RATEFILEMETRICDATA;

	@FindBy(xpath = "//a[@id='TransactionMetricData']")
	public WebElement TRANSACTIONMETRICDATA;

	@FindBy(xpath = "//a[@id='ProductMetricData']")
	public WebElement PRODUCTMETRICDATA;

	@FindBy(xpath = "//a[@id='ReturnMetricData']")
	public WebElement RETURNMETRICDATA;

	@FindBy(xpath = "//a[@id='MaintenancesMetricData']")
	public WebElement MAINTENANCEMETRICDATA;

	// ****Added as per POR-4423 in 4.2.4
	@FindBy(xpath = "//span[contains(.,'Manage Customers')]")
	public WebElement MANAGESMBCUSTOMERS;

	@FindBy(xpath = "//h1[contains(.,'Manage Cloud Customer')]")
	public WebElement MANAGESMBCUSTOMERTITLE;

	@FindBy(xpath = "//a[contains(.,'Customer Listing')]")
	public WebElement SMBCUSTOMERLISTING;

	@FindBy(xpath = "//label[contains(.,'Cloud Customer Number')]")
	public WebElement SMBCUSTOMERNUMLABEL;

	@FindBy(xpath = "//label[contains(.,'Cloud Customer Name')]")
	public WebElement SMBCUSTOMERNAMELABEL;

	@FindBy(xpath = "//label[contains(.,'Subscription')]")
	public WebElement SUBSCRIPTIONLABEL;

	@FindBy(xpath = "//label[contains(.,'Status')]")
	public WebElement STATUSLABEL;

	@FindBy(xpath = "//label[contains(.,'Var Name')]")
	public WebElement VARNAMELABEL;

	@FindBy(xpath = "//a[contains(.,'Export')]")
	public WebElement EXPORTBUTTON;

	@FindBy(xpath = "//a/span[contains(text(),'Premium Customers')]")
	public WebElement PREMIUMSMBCUST;

	@FindBy(xpath = "//a[contains(text(),'Return to Users')]")
	public WebElement RETURNTOUSERS;

	// Verrification of Error Monitor Table as per POR-9502 for new Role-Production
	// Support

	@FindBy(xpath = "//div/h1[contains(.,'Error Monitor')]")
	public WebElement ERRMONTITLE;

	@FindBy(xpath = "	//div/h1[contains(.,'Error Monitor')]/inline-help/a/i")
	public WebElement ERRMONHELPICON;

	public final String ERRMONHELPICON_XPTATH = "//div/h1[contains(.,'Error Monitor')]/inline-help/a";

	@FindBy(xpath = "//p[@id='currDate']")
	public WebElement ERRMONLASTDATE;

	@FindBy(xpath = "//table[@id='errortable']/tbody/tr/td[contains(.,'No records found.')]")
	public WebElement NORECORDS;

	@FindBy(xpath = "//pagination-extension/span/select")
	public WebElement ERRPAGEDROPDOWN;

	@FindBy(xpath = "//table[@id='errortable']/thead/tr/th/div/span[contains(.,'ID')]")
	public WebElement ERRMONID;

	@FindBy(xpath = "	//table[@id='errortable']/thead/tr/th/div/span[contains(.,'Queue Type')]")
	public WebElement ERRMONQUETYPE;

	@FindBy(xpath = "//table[@id='errortable']/thead/tr/th/div/span[contains(.,'Created Date')]")
	public WebElement ERRCREATEDDATE;

	@FindBy(xpath = "//table[@id='errortable']/thead/tr/th/div/span[contains(.,'Modified Date')]")
	public WebElement ERRMODIDATE;

	@FindBy(xpath = "//table[@id='errortable']/thead/tr/th/div/span[contains(.,'Cloud Customer Name')]")
	public WebElement SMBCUSTNAME;

	@FindBy(xpath = "//table[@id='errortable']/thead/tr/th/div/span[contains(.,'Parent Cloud Customer Name')]")
	public WebElement SMBPCUSTNAME;

	@FindBy(xpath = "//table[@id='errortable']/thead/tr/th/div/span[contains(.,'Error Message')]")
	public WebElement ERRORMESSAGE;

	@FindBy(xpath = "//table[@id='errortable']/thead/tr/th[contains(.,'Action')]")
	public WebElement ERRORACTION;

	@FindBy(xpath = "//table[@id='errortable']/tbody/tr[1]/td/a[contains(.,'Details')]")
	public WebElement DETAILSLINK;

	@FindBy(xpath = "//div/h1[contains(.,'Queue Error Details')]")
	public WebElement QUEUEERRORDETAILS;

	@FindBy(xpath = "//a[contains(.,'Retry This Item')]")
	public WebElement RETRYTHISITEM;

	@FindBy(xpath = "//a[contains(.,'Permanent Error')]")
	public WebElement PERMANENTERRORBUTTON;

	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement CANCELBUTTON;

	@FindBy(xpath = "//h1[contains(.,'Error Monitor')]")
	public WebElement ERRORMONITORTITLE;

	@FindBy(xpath = "//span[contains(.,'System Configuration')]")
	public WebElement SYSTEMCONFIGURATION;

	@FindBy(xpath = "//h1[contains(.,'System Configuration')]")
	public WebElement SYSTEMCONFIGTITLE;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'Name')]")
	public WebElement NAME;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'Value')]")
	public WebElement VALUE;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Action')]")
	public WebElement ACTION;

	@FindBy(xpath = "//td[contains(.,'WHATS_NEW_UPDATED_UTC')]")
	public WebElement UPDATEDUTC;

	@FindBy(xpath = "//small[contains(.,'© 2020 Vertex, Inc. All rights reserved.')]")
	public WebElement ALLRIGHTSRESERVED;

	@FindBy(xpath = "//label[contains(.,'Status')]/../select")
	public WebElement SMBSTATUS;

	@FindBy(xpath = "//select[@class='ng-pristine ng-untouched ng-valid']")
	public WebElement PAGINATIONDD;

	@FindBy(xpath = "//h1[contains(.,'DASHBOARD')]")
	public WebElement DASHBOARDTITLE;

	@FindBy(xpath = "//input[@id='startdate']")
	public WebElement METRICSTARTDATE;

	@FindBy(xpath = "//input[@id='Enddate']")
	public WebElement METRICENDDATE;

	///// 6.7
	@FindBy(xpath = "//a/span[contains(text(),'Premium Customers')]")
	public WebElement PremiumCustomerLink;

	@FindBy(xpath = "//a[contains(text(),'Download Return Files')]")
	public WebElement PremiumDownloadRetFiles;

	@FindBy(xpath = "//h1[contains(.,' Download Return Files')]")
	public WebElement DownloadRetFilesLabel;

	@FindBy(xpath = "//label[contains(.,'Filing Period')]")
	public WebElement FilingPeriodLabel;

	@FindBy(xpath = "//th[contains(.,'Generation Date')]")
	public WebElement GenerationDate;

	@FindBy(xpath = "//th[contains(.,'Vertex Customer')]")
	public WebElement VertexCust;

	@FindBy(xpath = "//th[text()='Company (Code)']")
	public WebElement COMPANYCODE;

	@FindBy(xpath = "//th[text()='Action']")
	public WebElement Action;

	@FindBy(xpath = "//span[contains(.,'CTC Impact')]")
	public WebElement CTCIMPACTHOVER;

	@FindBy(xpath = "//a[contains(.,'Taxability Categories')]")
	public WebElement TAXABILITYCATEGORY;

	@FindBy(xpath = "//a[contains(.,'Returns')]")
	public WebElement CTCRETURNS;

	@FindBy(xpath = "//input[@name='Download']")
	public WebElement CTC_DOWNLOAD;

	@FindBy(xpath = "//a[contains(.,'Remove All')]/../../div/input[@id='Submit']")
	public WebElement CTC_RDOWNLOAD;

	@FindBy(id = "txtNameContains")
	public WebElement RETURNNAMECONTAINS;

	@FindBy(xpath = "//span[contains(.,'Visit Help Center')]")
	public WebElement VisitHelpcenter;

	@FindBy(xpath = "//a[@href='/Admin/VisitHelpCenter/WhatsNew']")
	public WebElement Whatsnew;

	@FindBy(xpath = "//h1[contains(.,'Tax Calendar')]")
	public WebElement TAXCALENDAR;

	@FindBy(xpath = "//a/span[contains(.,'--Any--')]")
	public WebElement RETURN;

	@FindBy(xpath = "//div[@class='select2-search']/input")
	public WebElement SEARCHRETURN;

	@FindBy(xpath = "//input[@data-bind='checked: applyBusinessDayAdjustment']")
	public WebElement APPLYBUSDAYRULE;

	@FindBy(xpath = "//button[contains(.,'Update')]")
	public WebElement UPDATE;

	@FindBy(xpath = "//div[@class='popover fade right in']/div[contains(.,'Help Text')]")
	public WebElement TAXCALPOPUP;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Return/Prepayment Name')]")
	public WebElement RETURNPREPAYMENTFORM;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Default Due Date')]")
	public WebElement DEFAULTDUEDATE;

	@FindBy(xpath = "//table/thead/tr/th[starts-with(.,'Due Date')]")
	public WebElement DUEDATE;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Lock Date Offset')]")
	public WebElement LOCKDATEOFFSET;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Apply Business Day Rules')]")
	public WebElement APPLYBUSINESSDAYRULES;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Action')]")
	public WebElement ACTIONCOLUMN;

	@FindBy(xpath = "//span[@class='help-popover']/span")
	public WebElement TAXCALICON;

	@FindBy(id = "add-jurisdiction")
	public WebElement ENABLEJURISDICTION;

	@FindBy(xpath = "//button[contains(.,'Update')][@disabled='disabled']")
	public WebElement UPDATEDISABLED;

	@FindBy(xpath = "//inline-help[@id='taxCalindex']/a/i")
	public WebElement TAXCALENDERICON;

	@FindBy(xpath = "//a[contains(text(),'State Registrations')]")
	public WebElement STATEREGISTRATIONS;

	@FindBy(xpath = "//h1[contains(.,'CTC Impact: State Registrations')]")
	public WebElement CTCSTATEREGISTRATIONTEXT;

	@FindBy(xpath = "//select[@id='ddState']")
	public WebElement STATE;

	@FindBy(id = "Submit")
	public WebElement DOWNLOADCTCSTATEREGISTRATIONS;

	@FindBy(xpath = "//li/a[contains(.,'Partition Configuration')]")
	public WebElement PARTITIONCONFIGURATIONTAB;

	@FindBy(xpath = "//div/select[@ng-disabled='true']/option")
	public WebElement COMPANYDROPDOWNINPARTITIONCONFIGURATION;

	@FindBy(xpath = "//div/a[contains(.,'Certificates')]/i")
	public WebElement CERTIFICATESINPARTITIONCONFIGURATION;

	@FindBy(xpath = "//tr/td[contains(.,'ECW Rejection Letter Subject')]/../td[2]/input[@disabled='disabled']")
	public WebElement REJECTIONLETTERSUBJECT;

	@FindBy(xpath = "//div/a[contains(.,'TaxGIS')]/i")
	public WebElement TAXGISINPARTITIONCONFIGURATION;

	@FindBy(xpath = "//tr/td[contains(.,'Mimimum Aggregate Confidence')]/../td[2]/input")
	public WebElement MINAGGREGATECONFIDENCE;

	@FindBy(xpath = "//input[@value='Save']")
	public WebElement SAVE;

	@FindBy(xpath = "//div/strong[contains(.,'Success!')]/..[contains(.,'Config partition settings overridden successfully.')]")
	public WebElement Success;

	@FindBy(xpath = "//a[contains(.,'Back To Customer Details')]/i")
	public WebElement BACKTOCUSTOMERDETAILS;
}
