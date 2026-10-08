package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OSPSearch {

	@FindBy(xpath = "//span[contains(.,'Osp Logs Search')]")
	public WebElement OSPLOGSSEARCHOptn;

	@FindBy(xpath = "//h1[contains(.,'Osp Logs Search')]")
	public WebElement OSPLOGSSEARCHLbl;

	@FindBy(xpath = "//input[@id='startdate']")
	public WebElement OSPLOGSSTDT;

	@FindBy(xpath = "//input[@id='Enddate']")
	public WebElement OSPLOGSENDT;

	@FindBy(xpath = "//input[@placeholder='Message Type']")
	public WebElement MESSAGETYPE;

	@FindBy(xpath = "//input[@name='trustedId']")
	public WebElement TRUSTEDID;

	@FindBy(xpath = "//input[@name='companyCode']")
	public WebElement COMPANYCODE;

	@FindBy(xpath = "//input[@name='documentNumber']")
	public WebElement DOCUMENTNUMBER;

	@FindBy(xpath = "//input[@placeholder='Dns']")
	public WebElement DNS;

	@FindBy(xpath = "//input[@placeholder='Other']")
	public WebElement OTHER;

	@FindBy(xpath = "//input[@value='Search']")
	public WebElement SearchBtn;

	@FindBy(xpath = "//input[@value='Export']")
	public WebElement ExportBtn;

	@FindBy(xpath = "//span[contains(.,'Took:')]")
	public WebElement Timetook;

	@FindBy(xpath = "//span[contains(.,'IP: ')]")
	public WebElement IPAddress;

	@FindBy(xpath = "//button[text()='Response']")
	public WebElement ResBtn;

	@FindBy(xpath = "//h2[contains(.,'Response')]")
	public WebElement Reshedng;

	@FindBy(xpath = "//span[contains(.,'<?xml version=')]")
	public WebElement Resmessage;

	@FindBy(xpath = "//a[@id='copy-response']")
	public WebElement ResCopyBtn;

	@FindBy(xpath = "//strong[contains(.,'Cancel')]")
	public WebElement ResCancelBtn;

	@FindBy(xpath = "//button[contains(.,'Copy')]")
	public WebElement CopyBtn;

	@FindBy(xpath = "//div[@class='span12']/div[contains(.,'Copied successfully')]")
	public WebElement COPIEDSUCCESSFULLY;

	@FindBy(xpath = "//div[6]/div/div[3]/div/div/div/span[text()='MessageType: InvoiceRequest']")
	public WebElement INVOICEREQ;

	@FindBy(xpath = "//div[6]/div/div[3]/div/div/div/span[text()='MessageType: QuotationRequest']")
	public WebElement QUOTATIONREQ;

	@FindBy(xpath = "//div[6]/div/div[3]/div/div/div/span[text()='MessageType: PurchaseOrderRequest']")
	public WebElement PURCHASEORDERREQ;

	@FindBy(xpath = "//div[6]/div/div[4]/div/div/div/span[text()='DNS: vtmsqaconnect.vertexsmb.com']")
	public WebElement DNSVERIFICATION;

	public final String NPROGRESSBUSY_XPATH = "html[@class='nprogress-busy']";

	@FindBy(xpath = "//div[contains(text(),'No results')]")
	public WebElement NORESULTS;

	@FindBy(xpath = "//div[6]/div/div/div/div/div/pre[@class='ng-isolate-scope']")
	public WebElement RequestLog;

	@FindBy(xpath = "//h2[contains(.,'Response')]/../..//div/div/div/div/pre[@class='ng-isolate-scope']")
	public WebElement ResponseLog;

	@FindBy(xpath = "//span[contains(.,'DNS:')][@class='ng-binding']")
	public WebElement DNSname;

	@FindBy(xpath = "//span[contains(.,'MessageType:')][@class='ng-binding']")
	public WebElement MessageType;

	@FindBy(xpath = "//span[contains(.,'GUID:')][@class='ng-binding']")
	public WebElement GUIDValue;

	@FindBy(xpath = "//span[starts-with(.,'Logs')]")
	public WebElement LOGS;

	@FindBy(xpath = "//li/a[@title='Big Commerce Logs']")
	public WebElement BIGCOMMERCELOGS;

	@FindBy(xpath = "//h1[contains(.,'Big Commerce Logs')]")
	public WebElement BIGCOMMERCELOGSHEADING;

	@FindBy(xpath = "//h2[contains(.,'Log Files')]")
	public WebElement LOGFILESHEADING;

	@FindBy(xpath = "//label[contains(.,'Server Names')]/../select")
	public WebElement SERVERNAMES;

}
