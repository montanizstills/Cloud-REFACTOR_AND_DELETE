package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class DashboardPage {

	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	public WebElement DASHBOARD;
	
	@FindBy(xpath = "//select[@id='tax-payer']")
	public WebElement SELECTCOMPANY;
	
	@FindBy(xpath = "//select[@id='tax-payer-filing-period']")
	public WebElement FILLINGPERIOD;
	
	@FindBy(xpath = "//button[contains(text(),'Update Results')]")
	public WebElement UPDATERESULTS;
	
	@FindBy(xpath = "//b[@id='number_of_trans_main']")
	public WebElement NUMBEROFTRANSACTIONS;
	
	@FindBy(xpath = "//b[@id='number_of_returns_main']")
	public WebElement NUMBEROFRETURNS;
	
	@FindBy(xpath = "//b[@id='total_sales_main']")
	public WebElement TOTALOFSALES;
	
	@FindBy(xpath = "//b[@id='total_tax_main']")
	public WebElement TOTALOFTAX;
	
	@FindBy(xpath = "//div[@class='banner-content']/p[1][contains(text(),'You are currently using our free trial. Some features are hidden or not accessible to free trial users')]")
	public WebElement BANNERCONTENT1;
	
	@FindBy(xpath = "//div[@class='banner-content']/p[2][contains(text(),'and harness the superior quality of our sales tax software calculation system')]")
	public WebElement BANNERCONTENT2;
	
	@FindBy(xpath = "//div/p[2]/a[text()='Upgrade your plan']")
	public WebElement UPGRADEYOURPLAN;
	
	@FindBy(xpath = "//select[@id='tax-payer' and @disabled='disabled']")
	public WebElement DISABLESELECTCOMPANY;
	
	@FindBy(xpath = "//select[@id='tax-payer-filing-period' and @disabled='disabled']")
	public WebElement DISABLEDFILLINGPERIOD;
	
	@FindBy(xpath = "//b[@id='number_of_trans_main' and text()='0']")
	public WebElement ZEROTRANSACTIONS;
	
	@FindBy(xpath = "//b[@id='number_of_returns_main' and text()='0']")
	public WebElement ZERORETURNS;
	
	@FindBy(xpath = "//b[@id='total_sales_main' and contains(.,'0')]")
	public WebElement ZEROSALES;
	
	@FindBy(xpath = "//b[@id='total_tax_main' and contains(.,'0')]")
	public WebElement ZEROTAX;
	
	@FindBy(xpath = "//div/dl/dd[contains(text(),'Free Trial')]")
	public WebElement SUBSPLAN;
	
	@FindBy(xpath = "//div/dl/dd[contains(text(),'Not Subscribed')]")
	public WebElement SUBSRATEFILE;
	
	@FindBy(xpath = "//div/dl/dd/a[@href='/tos/client.pdf']")
	public WebElement SUBSSERVAGREEMENT;
	
	@FindBy(xpath = "//div[contains(text(),'Your free trial will expire in 29 days')]")
	public WebElement SUBSEXPIREMSG;
	
	@FindBy(xpath = "//div[contains(text(),'Update your billing information to modify your subscription')]")
	public WebElement SUBSUPDATEYOURBILLINGMSG;
	
	@FindBy(xpath = "//div/p[contains(text(),'No billing information provided')]")
	public WebElement SUBSBILLINGINFO;
	
	@FindBy(xpath = "//label[@id='usRadioButtonLabel']")
	public WebElement USTOGGLE;
	
	@FindBy(xpath = "//label[@id='canadaRadioButtonLabel']")
	public WebElement CANADATOGGLE;
	
	@FindBy(xpath = "//div[contains( @style,'display: block)')]/h2[contains(text(),'Comparative Analysis ')]")
	public WebElement COMPARATIVEANALYSIS;
	
	@FindBy(xpath = "//h3[contains(text(),'Canada')]")
	public WebElement CANADAMAPTEXT;
	
	@FindBy(xpath = "//h2[contains(text(),'Canada Statistics')]")
	public WebElement CANADASTATESTATITICS;
	
	@FindBy(xpath = "//p[@id='regLineCAN']")
	public WebElement REGISTRATIONSCANADA;
	
	@FindBy(xpath = "//p[contains(text(),'Select a state to view state sales and taxes.')]")
	public WebElement STATEVIEWSALESTAX;
	
	@FindBy(xpath = "//ul[contains( @style,'display: block)')]/li[1]/span[@class='map-legend-registered']")
	public WebElement STATESURREGISTEDIN;
	
	@FindBy(xpath = "//ul[contains( @style,'display: block)')]/li[2]/span[@class='map-legend-selected']")
	public WebElement CURRENTLYSELECTEDSTATE;
	
	@FindBy(xpath = "//p[@id='regLineUS']")
	public WebElement REGISTRATIONSUS;
	
	@FindBy(xpath = "//h3[contains(text(),'Alabama')]")
	public WebElement USMAPTEXT;
	
	@FindBy(xpath = "//dl[@class='total-tax']")
	public WebElement TOTALTAXSTAT;
	
	@FindBy(xpath = "//dl[@class='total-state-tax']")
	public WebElement TOTALSTATETAXSTAT;
	
	@FindBy(xpath = "//dl[@class='precentage-liability']")
	public WebElement PERCNTTAXLIABLTY;

	// Pipeling count in dashboard CPA
	@FindBy(xpath = "//p[text()='Initiated']/../div[@class='total h1 ng-binding ng-scope']")
	public WebElement INITCOUNT;
	
	@FindBy(xpath = "//p[text()='Live']/../div[@class='total h1 ng-binding ng-scope']")
	public WebElement LIVECOUNT;
	
	@FindBy(xpath = "//p[text()='Registered']/../div[@class='total h1']")
	public WebElement REGCOUNTPORTAL;
	
	@FindBy(xpath = "//p[text()='Live']/../div[@class='total h1']")
	public WebElement LIVECOUNTPORTAL;
	
	@FindBys({ @FindBy(xpath = "//select[@id='chart-currently-showing']/option"), })
	public List<WebElement> CURRENTLYSHOWING;
	
	@FindBys({ @FindBy(xpath = "//select[@id='chart-sort-by']/option"), })
	public List<WebElement> SORTBY;
	
	@FindBy(xpath = "//a[contains(.,'Update Analysis')]")
	public WebElement UPDATEANALYSIS;
	
	@FindBy(xpath = "//div/h2[contains(.,'Current Pipeline')]")
	public WebElement CURRENTPIPELINE;
	
	@FindBy(xpath = "//div/h2[contains(.,'Retention Report')]")
	public WebElement RETENTIONREPORT;
	
	@FindBy(xpath = "//select[@class='input-block-level']")
	public WebElement VARCURRENTLYSHOWING;
	
	@FindBy(xpath = "//div/h2[contains(.,'Customer Service Queue')]")
	public WebElement CUSTOMERSERVICEQUEUE;
	
	@FindBy(xpath = "//a[contains(.,'Go To Pricing Calculator')]")
	public WebElement PRICINGCALCULATORLINK;
	
	@FindBy(xpath = "//a[contains(.,'INVITE CUSTOMER')]")
	public WebElement INVITECUSTOMERLINK;

}
