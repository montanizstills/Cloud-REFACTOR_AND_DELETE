package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminQueueService {

	@FindBy(xpath = "//span[contains(.,'Dashboard')]")
	public WebElement DASHBOARD;

	@FindBy(xpath = "//span[contains(text(),'Queue Service')]")
	public WebElement QUEUESERVICELINK;

	@FindBy(xpath = "//h1[contains(.,'Queue Service Summary Details')]")
	public WebElement QUEUESERVICEHEADERTAEXT;

	@FindBy(xpath = "//label[contains(.,'Queue Service Id:')]")
	public WebElement QUEUESERVICEID;

	@FindBy(xpath = "//span[contains(.,'Queue Type:')]")
	public WebElement QUEUETYPE;

	@FindBy(xpath = "//span[contains(.,'Client Id:')]")
	public WebElement CLIENTID;

	@FindBy(xpath = "//span[contains(.,'Taxpayer Id:')]")
	public WebElement TAXPAYERID;

	@FindBy(xpath = "//span[contains(.,'Queue Status:')]")
	public WebElement QUEUESTATUS;

	@FindBy(xpath = "//html[@class='nprogress-busy']")
	public String NPROGRESSBUSY_XPATH;

	@FindBy(xpath = "//span[1]/label[contains(.,'Complete')]/../label/input")
	public WebElement COMPLETEINPUT;

	@FindBy(xpath = "//label[contains(.,'CompleteWithErrors')]/../label/input")
	public WebElement COMPLETEWITHERRORINPUT;

	@FindBy(xpath = "//label[contains(.,'In_Progress')]/../label/input")
	public WebElement INPROGRESSINPUT;

	@FindBy(xpath = "//label[contains(.,'Pending')]/../label/input")
	public WebElement PENDINGINPUT;

	@FindBy(xpath = "//span[3]/label[contains(.,'Error')]/../label/input")
	public WebElement ERRORINPUT;

	@FindBy(xpath = "//span[6]/label[contains(.,'PermanentError')]/../label/input")
	public WebElement PERMANENTERRORINPUT;

	@FindBy(xpath = "//label[contains(.,'Unknown')]/../label/input")
	public WebElement UNKNOWNINPUT;

	@FindBy(xpath = "//label[contains(.,'Record Count:')]")
	public WebElement RECORDTEXT;

	@FindBy(xpath = "//label[contains(.,'Record Count:')]/../select")
	public WebElement RECORDSPAGES;

	@FindBy(xpath = "//input[@value='Refresh']")
	public WebElement REFRESHBUTTON;

	@FindBy(xpath = "//th/div/span[contains(.,'QueueServiceId')]")
	public WebElement QUEUESERVICEIDHEADERTEXT;

	@FindBy(xpath = "//th/div/span[contains(.,'QueueStatus')]")
	public WebElement QUEUESTATUSHEADERTEXT;

	@FindBy(xpath = "//th/div/span[contains(.,'Queue Type')]")
	public WebElement QUEUETYPEHEADERTEXT;

	@FindBy(xpath = "//th/div/span[contains(.,'ClientId')]")
	public WebElement CLIENTIDHEADERTEXT;

	@FindBy(xpath = "//th/div/span[contains(.,'ClientName')]")
	public WebElement CLIENTNAME;

	@FindBy(xpath = "//th/div/span[contains(.,'TaxpayerName')]")
	public WebElement TAXPAYERNAME;

	@FindBy(xpath = "//th/div/span[contains(.,'Created Date')]")
	public WebElement CREATEDDATE;

	@FindBy(xpath = "//th/div/span[contains(.,'Modified Date')]")
	public WebElement MODIFIEDDATE;

	@FindBy(xpath = "//th/div/span[contains(.,'ExceptionLogId')]")
	public WebElement EXCEPTIONLOGID;

	@FindBy(xpath = "//span[contains(.,'Client Id:')]/input")
	public WebElement CLIENTIDVALUE;

	@FindBy(xpath = "//span/label[contains(.,'Queue Type:')]/../../span/select")
	public WebElement QUEUETYPEIDVALUE;

	@FindBy(xpath = "//span[contains(.,'Taxpayer Id:')]/input")
	public WebElement TAXPAYERIDVALUE;

	@FindBy(xpath = "//span[contains(.,'Error Monitor')]")
	public WebElement ERRORMONITOR;

	@FindBy(xpath = "//span[@class='pagination-index ng-scope']/select")
	public WebElement PAGINATION;

	@FindBy(xpath = "//label[contains(.,'Start Date:')]")
	public WebElement STARTDATELABEL;

	@FindBy(xpath = "//label[contains(.,'End Date:')]")
	public WebElement ENDDATELABEL;

	@FindBy(xpath = "//input[@id='startdate']")
	public WebElement STARTDATE;

	@FindBy(xpath = "//input[@id='Enddate']")
	public WebElement ENDDATE;

	@FindBy(xpath = "//span/label[contains(.,'Tax Payer Name:')]/../input")
	public WebElement TaxpayerNameField;

	@FindBy(xpath = "//span/label[contains(.,'Company Code:')]/../input")
	public WebElement COMPANYCODE;

	@FindBy(xpath = "//label[contains(.,'Include OSeries File Validation')]/input")
	public WebElement INCLUDEOSERIESFILEVALIDATION;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'TaxpayerName')]")
	public WebElement TAXPAYERNAMECOLUMN;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'Company Code')]")
	public WebElement COMPANYCODECOLUMN;

}
