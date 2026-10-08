package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VertexCloudVars {

	@FindBy(xpath = "//a/span[contains(.,'Vertex Cloud VARS')]")

	public WebElement vertexCloudvarsMenu;

	@FindBy(tagName = "input")

	public WebElement searchBox;

	@FindBy(xpath = "//div[@id='createForm']/h3[contains(.,'Admin Details')]")

	public WebElement Admindetails;

	@FindBy(xpath = "//tr/th[contains(.,'Name')]/../th[contains(.,'Address')]/../th[contains(.,'City')]/../th[contains(.,'State')]/../th[contains(.,'Zip')]/../th[contains(.,'Phone')]/../th[contains(.,'Status')]/../th[contains(.,'Account Type')]/../th[contains(.,'Admin')]/../th[contains(.,'User(s)')]")

	public WebElement VertexCloudVARSColumns;

	@FindBy(xpath = "//tr/th[contains(.,'Name')]/../th[contains(.,'Address')]/../th[contains(.,'City')]/../th[contains(.,'State')]/../th[contains(.,'Zip')]/../th[contains(.,'Phone')]/../th[contains(.,'Status')]/../th[contains(.,'Account Type')]/../th[contains(.,'User(s)')]")

	public WebElement VertexCloudVARSColumns1;

	@FindBy(xpath = "//tr/td/table/thead/tr/th[contains(.,'First Name')]/../th[contains(.,'Last Name')]/../th[contains(.,'Email')]/../th[contains(.,'Phone')]/../th[contains(.,'Created')]/../th[contains(.,'Last Login')]/../th[contains(.,'Status')]/../th[contains(.,'Role')]")

	public WebElement VertexCloudVARSColumnsAfterExpand;

	@FindBy(xpath = "//p[contains(.,'Client Name')]")

	public WebElement ClientName;

	@FindBy(xpath = "//label/strong[contains(.,'Client Artifact Repository')]")

	public WebElement ClientArtifactRepository;

	@FindBy(xpath = "//label/strong[contains(.,'Last Log In')]")

	public WebElement LastLogIn;

	@FindBy(xpath = "//*[@id='createForm']/div/select")

	public WebElement MerchantSubscriptionValue;

	@FindBy(xpath = "//h3[contains(.,'Merchant Subscription')]")

	public WebElement MerchantSubscription;

	@FindBy(xpath = "//input[@id='HasRateFile']")

	public WebElement RateFile;

	@FindBy(xpath = "//input[@id='TaxabilityImpactReport']")

	public WebElement TaxabilityImpactReport;

	@FindBy(xpath = "//input[@id='RetailTaxExtract']")

	public WebElement RetailTaxExtract;

	@FindBy(xpath = "//input[@id='HasRateFileCalc']")

	public WebElement RTRS;

	@FindBy(xpath = "//input[@id='TestInstance']")

	public WebElement TestInstance;

	@FindBy(xpath = "//table/tbody/tr[2]/td/a[contains(.,'Reports')]")

	public WebElement Reports;

	@FindBy(xpath = "//table/tbody/tr[2]/td/a[contains(.,'Admin Details')]")

	public WebElement AdminDetailsLink;

	@FindBy(xpath = "//tr/td/table/thead/tr/th[contains(.,'Status')]/../../../tbody/tr/td[8]")

	public WebElement STATUS;

	@FindBy(xpath = "//div/a[contains(.,'Return to Var Listing')]")

	public WebElement RETURNTOVARLISTING;

	@FindBy(xpath = "//a[contains(.,'Expand')]")

	public WebElement EXPAND;
}
