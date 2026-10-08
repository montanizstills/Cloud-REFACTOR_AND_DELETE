package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class ManagetaxReturns {
	@FindBy(xpath = "//span[contains(text(),'Manage Tax Returns')]")
	public WebElement MANAGETAXRETURNSLINK;

	@FindBy(xpath = "//li/a[contains(.,'Manage Prepayments')]")
	public WebElement MANAGEPREPAYMENTSLINK;

	@FindBy(xpath = "//h1")
	public WebElement MANAGETAXRETURNSLABEL;

	@FindBy(xpath = "//h1[contains(.,'Manage Prepayments')]")
	public WebElement PREPAYMENT;

	@FindBy(id = "tax-payer")
	public WebElement TAXPAYER;

	@FindBy(id = "filing-period")
	public WebElement FILLINGPERID;

	@FindBy(id = "btnGenerateReturns")
	public WebElement GENERATRETURN;

	@FindBy(xpath = "//button[contains(text(),'Generating Returns ...')]")
	public WebElement GENERATINGRETURN;

	@FindBy(xpath = "//button[contains(text(),'Generating Returns…') and @disabled='']")
	public WebElement GENERATERETURNSDISABLE;

	@FindBy(xpath = "//table/tbody//tr[1]/td[1]")
	public WebElement RETURN;

	@FindBy(xpath = "//table/tbody//tr[1]/td[3]")
	public WebElement STATUS;

	@FindBy(xpath = "//table/tbody//tr[1]/td[4]")
	public WebElement UPCOMING;

	@FindBy(xpath = "//button[contains(text(),'Reconciliation Report')]")
	public WebElement RECONCILIATION;

	@FindBy(xpath = "//button[contains(text(),'Unassigned Data Report')]")
	public WebElement UNASSIGNREPORT;

	@FindBy(xpath = "//div[@class='alert ng-scope']/../div[contains(.,'Hey')]")
	public WebElement MTRALERT;

	@FindBy(xpath = "//a[@id='btnDownload']")
	public WebElement RULEDEFINITION;

	@FindBy(xpath = "//a[contains(text(),'Go Live')]")
	public WebElement GOLIVEBUTTON;

	@FindBy(xpath = "//div[@class='alert alert-warning ng-scope']/../div[contains(.,'One or more of your companies have not completed configuration. Ensure all companies have been committed to continue.')]")
	public WebElement MTRALERT1;

	@FindBy(xpath = "//span[contains(.,'Return Generation for selected filing period has been requested.')]")
	public WebElement GENERATERETURNSMSG;

	@FindBy(xpath = "//button[@class='btn btn-primary ng-binding'][contains(text(),'Generate Post Filing Transactions Report')]")
	public WebElement POSTFILINGTRANS;

	@FindBy(xpath = "//button[@class='btn btn-primary'][contains(text(),'Download Post Filing Transaction Report')]")
	public WebElement DWNLD_POSTFILINGTRANS;

	@FindBy(xpath = "//span[contains(text(),'File & Pay')]")
	public WebElement FILEANDPAY;

	@FindBy(xpath = "//table[@id='tblManageTaxReturns']")
	public WebElement GRIDMANAGERETURNS;

	@FindBys({ @FindBy(xpath = "//table[@id='tblManageTaxReturns']/tbody"), })
	public List<WebElement> ReturnsCount;

	@FindBy(xpath = "//span/select[@ng-model='data.pageSize']")
	public WebElement PAGESIZE;

	@FindBys({ @FindBy(xpath = "//table[@id='tblManageTaxReturns']/tbody/tr[1]"), })
	public List<WebElement> rowscount;

	@FindBys({ @FindBy(xpath = "//table[@id='tblJurisdictions']/tbody/tr[1]"), })
	public List<WebElement> prepaymentrowscount;

	@FindBy(xpath = "//button[contains(.,'Import Error Report')]")
	public WebElement IMPORTERRORREPORT;

	@FindBy(xpath = "//button[contains(.,'Credit Report')]")
	public WebElement CREDITERRORREPORT;

	@FindBy(xpath = "//button[contains(.,'Reconciliation Report')]")
	public WebElement RECONCILATIONERRORREPORT;

	@FindBy(xpath = "//span[contains(.,'Return Generation for selected filing period has been requested.')]")
	public WebElement RETURNGENERATESUCCESSMSG;

	@FindBy(linkText = "Go Live")
	public WebElement GOLIVELINK;

	@FindBy(xpath = "//select[@id='filing-period']/option[@selected='selected']")
	public WebElement DEFAULTSELECETDDFILINGPERIOD;

	@FindBy(xpath = "//select[@id='filing-period']")
	public WebElement FILINGPERIOD;

	@FindBy(xpath = "//span[contains(text(),'Additional Returns Data')]")
	public WebElement ADDITIONALRETURNSDATA;

	@FindBy(xpath = "//button[contains(.,'Generating Returns ...')]")
	public WebElement GENERATINGRETURNS;

	@FindBy(xpath = "//button[contains(.,'Generate Returns')]")
	public WebElement GENERATERETURNS;

	@FindBy(xpath = "//button[contains(.,'Download All Returns')]")
	public WebElement DOWNLOADALLRTRNS;

	@FindBy(xpath = "//label[contains(.,'Company')]/../select")
	public WebElement LTAXPAYER;

	@FindBy(xpath = "//div[@class='alert alert-warning ng-binding ng-scope']/../div[contains(.,'Company  has not completed configuration. Ensure its configuration is up to date.')]")
	public WebElement ALERTSFTERGOLIVE;

	@FindBy(xpath = "//div/strong[contains(.,'Hey!')]/../../div[contains(.,'You currently have not registered for prepayments.')]")
	public WebElement PREPAYMENTSSYNCMSG;

	@FindBy(xpath = "//select[@id='taxpayer']")
	public WebElement PREPAYMENTSTAXPAYER;

	@FindBy(xpath = "//a[contains(.,'download prepayment rule definitions')]")
	public WebElement DOWNLOADPDF;

	@FindBy(xpath = "//div[contains(.,'Ensure its configuration is up to date.')]")
	public WebElement Warningmsg;

	@FindBy(xpath = "//strong[contains(.,'Changing this prepayment will overwrite the previous value.')]")
	public WebElement Changevaluwarning;

	@FindBy(xpath = "//a[contains(.,'Yes use the new value')]")
	public WebElement YEStoNewVal;

	@FindBy(xpath = "//a/strong[contains(.,'No, go back to old value')]")
	public WebElement Backtooldval;

	@FindBy(xpath = "//tr/td/a[contains(.,'Close Prepayments')]")
	public WebElement closeprepayments;

	@FindBy(xpath = "//span[text()='Dashboard']")
	public WebElement DASHBOARD;

	@FindBy(xpath = "//span[text()='Jurisdiction']")
	public WebElement Jurisdiction;

	@FindBy(xpath = "//th[contains(text(),'Prepayments')]")
	public WebElement Prepayments;

	@FindBy(xpath = "//th[3][contains(text(),'Most Recent Prepayments')]")
	public WebElement MSTRCTPREPAY;

	@FindBy(xpath = "//th[contains(text(),'Most Recent Prepayment Forms')]")
	public WebElement ADMINMSTRCTPREPAY;

	@FindBy(xpath = "//button[contains(.,'Generate')]")
	public WebElement GENRATE;

	@FindBy(xpath = "//button[contains(.,'Download All Most Recent')]")
	public WebElement DWNDALL;

	@FindBy(xpath = "//table[@id='tblManageTaxReturns']/tbody[2]/tr[1]/td[4]/div/div/a[contains(.,'Download')]")
	public WebElement DownlaodbuttonUndoGoLive;

	@FindBy(xpath = "//div/strong[contains(.,'Processing!')]/../../div[contains(.,'Your latest documents are being generated for this filing period. You will receive an email when they are complete.')]")
	public WebElement Prepaymentreturnsprocessing;

	@FindBy(xpath = "//div[contains(.,'Prepayment document generation for selected filing period has been requested.')]")
	public WebElement PrepaymentProcessing;

	@FindBy(xpath = "//span[contains(.,'Document Center')]")
	public WebElement DocumentCenter;

	@FindBy(xpath = "//th[4][contains(text(),'File & Pay')]")
	public WebElement FileandPay;

	@FindBy(xpath = "//div[contains(text(),'There are no companies registered for prepayments.  This section will be empty until it')]")
	public WebElement NOPrepaymentRegistrations;

	@FindBy(xpath = "//div[contains(.,'You currently have not registered for prepayments. This section will be empty until you save your configuration after registering for prepayments.')]/span")
	public WebElement Prepaymentsyncmessage;

	@FindBy(xpath = "//table[@id='tblManageTaxReturns']/thead/descendant::input")
	public WebElement DoNotFileAll;

	@FindBy(xpath = "//table[@id='tblJurisdictions']/thead/descendant::input")
	public WebElement DoNotPayAll;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement Save;

	@FindBy(xpath = "//button[contains(.,'Premium Reconciliation Report')]")
	public WebElement PremiumReconReportBtn;

	@FindBy(id = "doNotPayCheckBox")
	public WebElement DoNotPay;

	@FindBy(xpath = "//input[@id='company-status']")
	public WebElement TAXPAYERSTATUS;

	@FindBy(xpath = "//button[@id='btnGenerateReturns' and @disabled='disabled']")
	public WebElement GenerateReturnsDisabled;

	@FindBy(xpath = "//span[contains(.,'Applied Out-of-State Sales of')]")
	public WebElement appliedoutofsalesmsg;
}
