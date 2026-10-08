package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArtifactRepository {

	@FindBy(xpath = "//a[text()='Artifact Repository']")
	public WebElement ARTIFACTLINK;
	
	@FindBy(xpath = "//a[text()='AdminOnboarding']")
	public WebElement ADMINONBOARDINGLINK;
	
	@FindBy(xpath = "//a[text()='AdminMonthly']")
	public WebElement ADMINMONTHLYLINK;
	
	@FindBy(xpath = "//a[text()='AdminPOA']")
	public WebElement ADMINPOALINK;
	
	@FindBy(xpath = "//a[text()='ClientOnboarding']")
	public WebElement CLIENTONBOARDINGLINK;
	
	@FindBy(xpath = "//a[text()='ClientNotices']")
	public WebElement CLIENTMONTHLYLINK;
	
	@FindBy(xpath = "//a[text()='AdminOnboarding']")
	public WebElement CLIENTNOTICESLINK;
	
	@FindBy(xpath = "//a[text()='ClientPOA']")
	public WebElement CLIENTPOALINK;
	
	@FindBy(xpath = "//a[text()='ClientReturns']")
	public WebElement CLIENTRETURNSLINK;
	
	@FindBy(xpath = "//a[text()='Invoice']")
	public WebElement INVOICELINK;
	
	@FindBy(xpath = "//a[text()='ClientRateFile']")
	public WebElement CLIENTRATEFILELINK;
	
	@FindBy(xpath = "//a[text()='Internal']")
	public WebElement INTERNALLINK;
	
	@FindBy(xpath = "//a[text()='TaxabilityReport']")
	public WebElement TAXABILITYREPORTLINK;
	
	@FindBy(xpath = "//a[text()='NoticeManagement']")
	public WebElement NOTICEMANAGEMENTLINK;
	
	@FindBy(xpath = "//a[text()='CustomerExemptionReport']")
	public WebElement CUSEXEMPREPORTLINK;
	
	@FindBy(xpath = "//a[text()='GoLiveBackup']")
	public WebElement GOLIVEBACKUPLINK;
	
	@FindBy(xpath = "//a[text()='Report']")
	public WebElement REPORTLINK;
	
	@FindBy(xpath = "//a[text()='Return']")
	public WebElement RETURNLINK;
	
	@FindBy(xpath = "//a[text()='OSeriesReconCsv']")
	public WebElement OSERIESRECONCSVLINK;
	
	@FindBy(xpath = "//a[text()='AdjustmentFile']")
	public WebElement ADJUSTMENTFILELINK;
	
	@FindBy(xpath = "//a[text()='MarketingToolkit']")
	public WebElement MARKETINGTOOLKITLINK;

}
