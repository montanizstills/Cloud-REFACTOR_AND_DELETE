package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminGoLiveTaxpayer {

	@FindBy(xpath = "//table/tbody/tr[@class='ng-scope]/td/../td[contains(text(),'Live')]")
	public WebElement ADMINSMBCUSTOMERDETAILSLIVE;

	@FindBy(xpath = "//table/tbody/tr[@class='ng-scope]/td/../td[contains(text(),'Code')]'")
	public WebElement ADMINCODELINK;

	@FindBy(xpath = "//label[contains(.,'Company Status')]/../input")
	public WebElement SMBCUSTOMERTAXRETURNSLIVECHECK;

	@FindBy(xpath = "//a[contains(text(),'Admin Details')]")
	public WebElement ADMINDETAILS;

	@FindBy(xpath = "//a[contains(text(),'Customer Reports')]")
	public WebElement SMBCUSTOMERREPORTS;

	@FindBy(className = "h1 ng-binding")
	public WebElement ADMINDETAILCOMPANYNAME;

	@FindBy(xpath = "//div[@class='h1 ng-binding']/h[2][contains(text(),'Live')]")
	public WebElement ADMINDETAILCOMPANYSTATUS;

	// Transaction Metrics report
	@FindBy(xpath = "//a[@id='TransactionMetricData' and contains(.,'Download CSV')]")
	public WebElement TRANSACTIONMETRIC;

	// Product Metric Data report
	@FindBy(xpath = "//a[@id='ProductMetricData' and contains(.,'Download CSV')]")
	public WebElement PRODUCTMETRIC;

	// ReturnMetricData
	@FindBy(xpath = "//a[@id='ReturnMetricData' and contains(.,'Download CSV')]")
	public WebElement RETURNMETRICDATA;

	// MaintenancesMetricData
	@FindBy(xpath = "//a[@id='MaintenancesMetricData' and contains(.,'Download CSV')]")
	public WebElement MAINTENANCESMETRICDATA;

	@FindBy(xpath = "//span[contains(text(),'Metrics')]")
	public WebElement METRICS;

	@FindBy(xpath = "//span[contains(.,'Status:')]/../../div[contains(.,'Authorized')]")
	public WebElement DETAILSTATUS;

	@FindBy(xpath = "//a[contains(text(),' Copy Config')]")
	public WebElement SMBCOPYCONFIG;

	@FindBy(xpath = "//a[contains(.,'Export')]")
	public WebElement EXPORT;

	@FindBy(xpath = "//span[contains(text(),'Manage Customers')]")
	public WebElement MANAGESMBCUSTOMERS;

	@FindBy(xpath = "//label[contains(text(),'Cloud Customer Name')]/../input")
	public WebElement SMBCUSTOMERNAME;

	@FindBy(xpath = "//a[contains(text(),'Customer Configuration')]")
	public WebElement SMBCUSTOMERCONFIGS;

	@FindBy(xpath = "//span[text()='Live']")
	public WebElement SMBCUSTOMERCONFIGURATIONLIVECHECK;

	@FindBy(xpath = "//a[contains(text(),'Customer Tax Returns')]")
	public WebElement SMBCUSTOMERTAXRETURNS;

	@FindBy(xpath = "//a[contains(text(),'Customer Reports')]")
	public WebElement SMBCUSTOMERREPORT;

	@FindBy(xpath = "//select[@id='taxpayersList']")
	public WebElement TAXPAYERSLIST;

	@FindBy(xpath = "//select[@id='tax-payer']")
	public WebElement COMPANY;

}