package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerExceptionReport {

	public final String STATES_XPATH = "//div[@class='controls']/select[@id='ddlExceptionJurisdiction']/option";
	
	@FindBy(xpath = "//span[text()='Reports']")
	public WebElement REPORTSLINK;
	
	@FindBy(xpath = "//a[contains(.,'Customer Exemption')]")
	public WebElement CUSTOMEREXEMPTIONREPORTLINK;
	
	@FindBy(xpath = "//div[@class='controls']/select[@id='SelectedState']/option")
	public WebElement STATES;
	
	@FindBy(xpath = "//label[text()='State/Province']")
	public WebElement STATEPROVINCELABEL;
	
	@FindBy(xpath = "//a[contains(.,'Generate Report')]")
	public WebElement GENERATEBUTTON;
	
	@FindBy(xpath = "//input[@id='cert-start-date-start']")
	public WebElement STARTFROM;
	
	@FindBy(xpath = "//input[@id='cert-start-date-end']")
	public WebElement STARTTO;
	
	@FindBy(xpath = "//input[@id='cert-end-date-start']")
	public WebElement ENDFROM;
	
	@FindBy(xpath = "//input[@id='cert-end-date-end']")
	public WebElement ENDTO;
	
	@FindBy(xpath = "//input[@id='cert-exp-date-start']")
	public WebElement CERTFICEXPFROM;
	
	@FindBy(xpath = "//input[@id='cert-exp-date-end']")
	public WebElement CERTFICEXPTO;
	
	@FindBy(xpath = "//table/tbody/tr/td[4]/a[contains(text(),'Delete')]")
	public WebElement DELETELINK;
	
	@FindBy(xpath = "//select[@id='ddlCustomerName']")
	public WebElement CUSTOMERNAME;
	
	@FindBy(xpath = "//select[@id='ddlCustomerCode']")
	public WebElement CUSTOMERCODE;
	
	@FindBy(xpath = "//select[@id='ddlExceptionJurisdiction']")
	public WebElement STATE;
	
	@FindBy(xpath = "//div/input[@id='cert-start-date-start']")
	public WebElement STARTDATEFROM;
	
	@FindBy(xpath = "//div/input[@id='cert-start-date-end']")
	public WebElement STARTDATETO;
	
	@FindBy(xpath = "//div/input[@id='cert-end-date-start']")
	public WebElement ENDDATEFROM;
	
	@FindBy(xpath = "//div/input[@id='cert-end-date-end']")
	public WebElement ENDDATETO;
	
	@FindBy(xpath = "//select[@id='ddlCompanyName']")
	public WebElement TAXPAYERNAME;
	
	@FindBy(xpath = "//select[@id='ddlCompanyCode']")
	public WebElement TAXPAYERCODE;
	
	@FindBy(xpath = "//p/a[contains(.,'Delete')]")
	public WebElement DELETEBUTTON;
	
	@FindBy(xpath = "//div[@class='registration-block ng-scope' and @ng-repeat='company in data.companies']/div/h3[@class='h5 heading-accent has-warning ng-binding']")
	public WebElement INPROCESSCOMPANYCONFIG;
	
	@FindBy(xpath = "html[@class='nprogress-busy']")
	public WebElement NPROGRESSBUSY;
	
	@FindBy(xpath = "//a[@id='generateButton']")
	public WebElement GENERATEBTN;
	
	@FindBy(xpath = "//div[text()='Date']")
	public WebElement DATECOLUMN;
	
	@FindBy(xpath = "//tbody/tr[1]/td/a[contains(text(),'Download')]")
	public WebElement CUSTEXCEPTIONREPORTDWNLD;
	
	@FindBy(xpath = "//div[@class='alert alert-success ng-binding ng-scope']")
	public WebElement SUCCESS_ALERT;
	
	@FindBy(xpath = "//tbody/tr[1]/td/a[contains(text(),'CustomerExceptionReport')]")
	public WebElement CUSTEXREPORT1;
	
	@FindBy(xpath = "//select[@id='ddlReasonType']")
	public WebElement REASONTYPE;
	
	@FindBy(xpath = "//table[@id='customerExceptionsTable']/tbody[1]/tr[1]/td/a[contains(text(),'Download')]")
	public WebElement DOWNLOAD;
	
	@FindBy(xpath = "//table[@id='customerExceptionsTable']/tbody[1]/tr[1]/td/a[contains(text(),'See Details')]")
	public WebElement SEEDETAILS;
	
	@FindBy(xpath = "//table[@id='customerExceptionsTable']/tbody[1]/tr[1]/td/a[contains(text(),'Close')]")
	public WebElement CLOSE;
	
	@FindBy(xpath = "//tr[1]/td/div/a[text()='Designated ECM Contact']")
	public WebElement DESIGNATEDECMCONTACT;
	
	@FindBy(xpath = "//tr[@class='inner-table']/td/div/a[text()='Update']")
	public WebElement UPDATE;
	
	@FindBy(xpath = "//input[@value='true']")
	public WebElement ENABLEREPORTNOTIFICATIONS;
	
	@FindBy(xpath = "//p/a[text()='Update']")
	public WebElement UPDATECONFORMATION;

}
