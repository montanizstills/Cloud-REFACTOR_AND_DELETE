/*###################################################################################################
Name: BillingPage.java 
Description: Configuration Report page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/28/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Metrics {

	@FindBy(xpath = "//a/span[text()='Metrics']")
	public WebElement METRICSLINK;

	@FindBy(xpath = "//a[@id='AuditMetricData']")
	public WebElement AMDDWNLDCSV;

	@FindBy(xpath = "	//a[@id='RateFileMetricData']")
	public WebElement RFDWNLDCSV;

	@FindBy(xpath = "	//a[@id='RateFileMetricData']")
	public WebElement TMDDWNLDCSV;

	@FindBy(xpath = "//a[@id='ProductMetricData']")
	public WebElement PMDDWNLDCSV;

	@FindBy(xpath = "//a[@id='ReturnMetricData']")
	public WebElement RMDDWNLDCSV;

	@FindBy(xpath = "//a[@id='MaintenancesMetricData']")
	public WebElement MMDDWNLDCSV;

	@FindBy(xpath = "//div[2]/div/div/div[1]/h1[contains(.,'Metrics')]")
	public WebElement METRICSLABEL;

	@FindBy(xpath = "//a[@id='AuditMetricData']")
	public WebElement AUDITMETRICDOWNLOAD;

	@FindBy(xpath = "//a[@id='ProductMetricData']")
	public WebElement PRODUCTMETRICDOWNLOAD;

	@FindBy(xpath = "//a[@id='ClientDeleteReport']")
	public WebElement CLIENTDLETEREPORT;

	@FindBy(xpath = "//a[@id='ClientChangeSubscriptionReport']")
	public WebElement CLIENTCHANGESUBREPORT;

	@FindBy(xpath = "//a[@id='customerTaxpayerPodMetrics']")
	public WebElement CLIENTPODDREPORT;

	@FindBy(xpath = "//a[@id='ExceptionMetricData']")
	public WebElement EXCEPTIONMETRICDATA;

	@FindBy(xpath = "//a[@id='ClientLoginReport']")
	public WebElement CLIENTLOGINREPORT;

	@FindBy(xpath = "//a[@id='MaintenancesMetricData']")
	public WebElement MAINTENANCEMETRICDATA;

	@FindBy(xpath = "//a[@id='RateFileMetricData']")
	public WebElement RATEFILEMETRICDATA;

	@FindBy(xpath = "//h1[text()='Partitions Metric Data']/../../div[@class='span4']/p/button[contains(text(),'View Metrics')]")
	public WebElement PMDVIEWMETRICS;

	@FindBy(xpath = "//th[contains(text(),'Pod Id')]")
	public WebElement PODID;

	@FindBy(xpath = "//th[contains(text(),'Assigned')]")
	public WebElement ASSIGNEDHEADER;

	@FindBy(xpath = "//th[contains(text(),'Available')]")
	public WebElement AVAILABLEHEADER;

	@FindBy(xpath = "//th[contains(text(),'Total')]")
	public WebElement TOTALEHEADER;

	@FindBy(xpath = "//a[@id='TransactionMetricData']")
	public WebElement TMDDWNLD;

	@FindBy(xpath = "//a[@id='clientReturnsCount']")
	public WebElement RGDDWNLD;

	@FindBy(xpath = "//a[@id='customerConfigObjectsMetrics']")
	public WebElement CCDDWNLD;

	@FindBy(xpath = "//a[@id='returnsFilingFrequencyMetrics']")
	public WebElement RFDDWNLD;

	@FindBy(xpath = "//a[@id='customerTaxpayerPodMetrics']")
	public WebElement CTDDWNLD;

	@FindBy(xpath = "//a[@id='taxabilityCategoryMetrics']")
	public WebElement TCDNLD;

	@FindBy(xpath = "//a[@id='validationErrorDetails']")
	public WebElement VEDNLD;

	@FindBy(xpath = "//a[@id='ClientDeleteReport']")
	public WebElement ClientDelete;

	@FindBy(xpath = "//a[@id='ClientChangeSubscriptionReport']")
	public WebElement ClientChangesubscription;

	@FindBy(xpath = "//a[@id='SocReportRequestData']")
	public WebElement DOWNLDCSV;

	@FindBy(xpath = "//a[@id='btnOk']")
	public WebElement OK;

	@FindBy(xpath = "//span[contains(.,'Visit Help Center')]")
	public WebElement VISITHELPCENTER;

}
