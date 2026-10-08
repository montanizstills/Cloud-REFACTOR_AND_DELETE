package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class AdminUndoAdjustmentFile {

	@FindBy(xpath = "//span[contains(text(),'Manage Customers')]")
	public WebElement manageSMBCustomers;

	@FindBy(xpath = "//label[contains(text(),'Cloud Customer Name')]/../input")
	public WebElement smbCustomerName;

	@FindBy(xpath = "//a[contains(text(),'Customer Reports')]")
	public WebElement smbCustomerReports;

	@FindBy(xpath = "//select[@ng-model='data.report.company']")
	public WebElement companyName;

	@FindBy(xpath = "//select[@ng-model='rptValueSelected']")
	public WebElement REPORT;

	@FindBy(id = "rootFolder")
	public WebElement Rootfolder;

	@FindBy(id = "folder2")
	public WebElement Internalyearfolder;

	@FindBy(id = "folder3")
	public WebElement InternalMonthfolder;

	@FindBys({@FindBy(xpath = "//div[2]/div/div/div/div/ul/li"), })
	public List<WebElement> undoAdjustmentFilestatustable;

	@FindBy(xpath = "//a[text()='Yes, Delete Adjustment File']")
	public WebElement deleteAdjustmentFile;

	@FindBy(xpath = "//pagination-extension/span/select")
	public WebElement paginationDD;

	@FindBy(xpath = "//a[contains(text(),'Admin Details')]")
	public WebElement ADMINDETAILSLNK;

	@FindBy(xpath = "//div[3]/div[2]/span/..")
	public WebElement apiKeyblank;

	@FindBy(xpath = "//div[3]/div[3]/span/..")
	public WebElement apiPasswordblank;

	@FindBy(xpath = "//company-overview/div/div[3]/div[2]/span/..")
	public WebElement apiKeyadmindetailsblank;

	@FindBy(xpath = "//company-overview/div/div[3]/div[3]/span/..")
	public WebElement apiPasswordadmindetailsblank;

	@FindBy(xpath = "//div[4]/div[1]/div/div[1]/span/..")
	public WebElement apiKeyPublicAPISection;

	@FindBy(xpath = "//div[4]/div[2]/div/div[1]/span/..")
	public WebElement apiPasswordPublicAPISection;

	@FindBy(linkText = "Generate API Credentials")
	public WebElement generateAPIcredentials;

	@FindBy(xpath = "//div[contains(text(),'This action will update the customer')]")
	public WebElement updateAPICredentialsSection;

	@FindBy(xpath = "//a[text()='Generate API Credentials']")
	public WebElement generateAPICredentialslnk;

	@FindBy(linkText = "Cancel")
	public WebElement cancellnk;

	@FindBy(xpath = "//h2[contains(.,'Public Api Credentials')]/../div/div/div/span[contains(.,'API Key:')]/../../div[2]")
	public WebElement apiKey;

	@FindBy(xpath = "//h2[contains(.,'Public Api Credentials')]/../div/div/div/span[contains(.,'API Password:')]/../../div[2]")
	public WebElement apiPassword;

	@FindBy(xpath = "//a[contains(.,'Reset API Credential')]")
	public WebElement resetAPIcredentials;

	@FindBy(name = "startDate")
	public WebElement StartDate;

	@FindBy(name = "endDate")
	public WebElement Enddate;

	@FindBy(xpath = "//input[@value='AdminTransactionSummary']")
	public WebElement AdminTransactionSummary;

	@FindBy(xpath = "//input[@value='AdminTransactionDetails']")
	public WebElement AdminTransactionDetails;

	@FindBy(xpath = "//input[@value='InternationalAdminTransactionDetails']")
	public WebElement AdminIntTransactionDetails;

	@FindBy(xpath = "//a[contains(text(),'AdminIntlTransactionDetails-All')]")
	public WebElement AdminIntlTransactionDetailReport;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement Submit;

	@FindBy(xpath = "//a[contains(text(),'TransactionSummary')]")
	public WebElement AdminTransactionSummaryReport;

	@FindBy(xpath = "//a[contains(.,'RTEBracketScheduleReportAdmin')]")
	public WebElement AdminRTEBracketScheduleReport;

	@FindBy(xpath = "//li[1]/div/span/a[contains(.,'transSummary')]")
	public WebElement HVRSummaryReport;

	@FindBy(xpath = "//li[1]/div/span/a[contains(.,'transDetail')]")
	public WebElement HVRDetailReport;

	@FindBy(xpath = "//a[contains(text(),'TransactionDetails')]")
	public WebElement AdminTransactionDetailsReport;

	@FindBy(xpath = "//a[contains(.,'_transDetail')][1]")
	public WebElement HVRTransDetail;

	@FindBy(xpath = "//span[contains(text(),'Cloud Customer Name')]")
	public WebElement smbcustomerColumn;

	@FindBy(xpath = "//span[contains(text(),'Environment')]")
	public WebElement environment;

	@FindBy(xpath = "//span[contains(.,'Account Type')]")
	public WebElement accountType;

	@FindBy(xpath = "//a[contains(.,'Push configuration')]")
	public WebElement PUSHCONFIGURATION;

	@FindBy(xpath = "//a[contains(.,'Yes continue to save change')]")
	public WebElement SAVECHANGE;

	@FindBys({@FindBy(xpath = "//table/tbody/tr[@class='ng-scope']"), })
	public List<WebElement> ManageCloudTablerows;

	@FindBy(xpath = "//a[contains(text(),'Connectors')]")
	public WebElement CONNECTORS;

	@FindBy(xpath = "//button[text()='Generate Report']")
	public WebElement GENERATEREPORT;

	@FindBy(xpath = "//button[text()='Download Report']")
	public WebElement DOWNLOADREPORT;

	@FindBy(xpath = "//span[@class='span6']")
	public WebElement DownloadedInternationalReturns;

	@FindBy(xpath = "//a[contains(.,'InternationalReturns'_)]")
	public WebElement InternationalReturnsDownloadLnk;

	@FindBy(xpath = "//a[@class='ng-binding ng-scope']")
	public WebElement DOWNLOADEDADMINTAXABILITYREPORT;

	@FindBy(xpath = "//ul/li[1]/div/span/a[contains(.,'TaxAssistRuleReport')]")
	public WebElement TAXASSISTRULEREPORTCLIENT;

	@FindBy(xpath = "//input[@id='HasRateFile']")
	public WebElement NEGOCHKBOX;

	@FindBy(xpath = "//input[@id='txtNegotiatedContractNumber']")
	public WebElement NEGOTIATEDCONTRACTNUMBER;

	@FindBy(xpath = "//label[contains(.,'Browse')]")
	public WebElement BROWSE;

	@FindBy(xpath = "//button[@id='btnSave']")
	public WebElement SAVE;

	@FindBy(xpath = "//a[text()='Edit']")
	public WebElement EDIT;

	@FindBy(xpath = "//a[text()='View PDF']")
	public WebElement VIEWPDF;

	@FindBy(xpath = "//button[@id='btnCancel']")
	public WebElement CANCEL;

	@FindBy(xpath = "//button[contains(.,'Undo GoLive')]")
	public WebElement UndoGoLive;

	@FindBy(xpath = "//button[contains(.,'Undo GoLive') and @disabled='disabled']")
	public WebElement UndogoLivedisabled;

	@FindBy(xpath = "//a[contains(.,'Customer Prepayments')]")
	public WebElement CustomerPrepayments;

	@FindBy(xpath = "//a[contains(.,'Yes')]")
	public WebElement YES;

	@FindBy(xpath = "//p/a[@id='modal-error-ok']")
	public WebElement ERRORPOPUPOK;

	@FindBy(xpath = "//a[text()='Modify VAR']")
	public WebElement modifyVars;

	@FindBy(xpath = "//div[2]/div[2]/select")
	public WebElement Varlist;

	@FindBy(id = "save-ok")
	public WebElement Savebtn;
	
	@FindBy(xpath = "//button[contains(.,'Cancel Customer')]")
	public WebElement CANCELBUTTON;

	@FindBy(xpath = "//a[contains(.,'Yes, Complete Cancel')]")
	public WebElement COMPLETECANCEL;

	@FindBy(xpath = "//p[contains(.,'You are about to request approval to cancel a customer.')]")
	public WebElement POPUPTEXT;

	@FindBy(id = "inputCancelRequestReason")
	public WebElement CANCELREASON;

	@FindBy(xpath = "//button[@disabled='disabled']")
	public WebElement CANCELDISABLE;

	@FindBy(xpath = "//div/ul/li[contains(.,'Welcome, James!')]")
	public WebElement Welcome;

	@FindBy(xpath = "//div[@class='row-fluid']/descendant::input")
	public WebElement SEARCHBYMERCHANT;

	@FindBy(xpath = "//h3[contains(.,'CONFIRM CHANGE')]")
	public WebElement CONFIRMCHANGE;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement RESETAPIPASSWORD;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement SUBMITPASSWORD;

	@FindBy(xpath = "//strong[contains(.,'Success!')]")
	public WebElement Success;

	@FindBy(xpath = "//a[text()='Edit Customer Subscription']")
	public WebElement EDITCUSTOMERSUBSCRIPTION;

	@FindBy(xpath = "//button[@disabled='disabled'][contains(.,'Generate Report')]")
	public WebElement GENERATEBUTTONDISABLE;

	@FindBy(xpath = "//ul/li[1]/div/span/a[contains(.,'TaxAssistLookupTable')]")
	public WebElement TAXASSISTLOOKUPTABLECLIENT;

	@FindBy(xpath = "//ul/li[1]/div/span/a[contains(.,'TaxAssistAllocationTable')]")
	public WebElement TAXASSISTALLOCATIONTABLECLIENT;

	@FindBy(xpath = "//a[contains(.,'LegacyDataReport')]")
	public WebElement LEGACYDATAREPORT;

	@FindBy(xpath = "//span[contains(text(),'OAuth Credential Mgmt')]")
	public WebElement OAUTHCREDENTIALMGMT;
	
	@FindBy(xpath="//input[@id='credential-usage-public-api']")
	public WebElement PUBLICAPI;
	
	@FindBy(xpath="//input[@id='name']")
	public WebElement PUBAPIFROM;
	
	@FindBy(xpath="//input[@id='client-id']")
	public WebElement CLIENTID;
	
	@FindBy(xpath="//button[contains(.,'Create')]")
	public WebElement CREATE;
	
	@FindBy(xpath="//strong[contains(.,'Client Secret:')]")
	public WebElement CLIENTSECRET;
	
	@FindBy(xpath="//a[contains(.,'Ok')]")
	public WebElement OK;
}
