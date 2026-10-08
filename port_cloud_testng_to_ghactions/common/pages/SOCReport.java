package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SOCReport {
	@FindBy(xpath = "//span[contains(text(),'SOC Report Request')]")

	public WebElement SOCREPORTREQ;
	@FindBy(css = "//h1[class='icon-section icon-transactions']")

	public WebElement SOCPAGETITLE;
	@FindBy(xpath = "//*[@id='SocReportRequestData']")

	public WebElement DOWNLDCSV;
	@FindBy(xpath = "//*[@id='company-name']")

	public WebElement CompanyName;
	@FindBy(xpath = "//*[@id='prospect-customer']")

	public WebElement CustomerType;
	@FindBy(xpath = "//*[@id='contact-name']")

	public WebElement ContactName;
	@FindBy(xpath = "//*[@id='title']")

	public WebElement Title;
	@FindBy(xpath = "//h1[contains(.,'SOC Report Request')]")

	public WebElement SOCTitle;
	@FindBy(xpath = "//*[@id='delivery-email']")

	public WebElement Email;
	@FindBy(xpath = "//*[@id='newAddLocation']")

	public WebElement Submit;
	// ***************metrics xpath***********************************************
	@FindBy(xpath = "//h1[class='icon-sectionadmin icon-metrics']")

	public WebElement MetricsPAGETITLE;
	@FindBy(xpath = "//span[contains(text(),'Metrics')]")

	public WebElement METRICS;
	@FindBy(xpath = "//div[@class='row-fluid']/div/div/h1[contains(.,'SOC Document Request Log')]")

	public WebElement SOCDOCREQLOG;
	@FindBy(xpath = "//*[@id='SocReportRequestData']")

	public WebElement DownloadCSV;
	@FindBy(xpath = "//input[@id='startdate']")

	public WebElement Stdate;
	@FindBy(xpath = "//input[@id='Enddate']")

	public WebElement Enddate;
	// **************************dashboard***************
	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")

	public WebElement DASHBOARD;
	@FindBy(xpath = "//span[contains(.,'Please enter a proper email address.')]")

	public WebElement INVALIDEMAILMESSAGE;
	@FindBy(xpath = "//h1[contains(.,'SOC Report Download Error')]")

	public WebElement SOCDOWNLOADERROR;
	@FindBy(xpath = "//p[contains(.,'The requested report')]/../p/a[contains(.,'CloudSupport@Vertexinc.com')]")

	public WebElement SOCMAILID;
	@FindBy(xpath = "//p[3]/a")

	public WebElement SOCEMAILLINK;
	@FindBy(xpath = "//a[contains(text(),'Request SOC Report')]")

	public WebElement SOCReportRequest;
	@FindBy(xpath = "//h1[contains(.,'SOC Report')]")

	public WebElement SOCheadertext;
	@FindBy(xpath = "//button[contains(.,'Download Report')]")

	public WebElement SOCreportdownload;

}