package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoardVarRoles {

	@FindBy(xpath = "//h2[contains(text(),'Commission Report')]")
	public WebElement COMMISSIONREPORT;
	
	@FindBy(xpath = "//h2[contains(text(),'Current Pipeline')]")
	public WebElement CURRENTPIPELINE;
	
	@FindBy(xpath = "//h2[contains(text(),'Retention Report')]")
	public WebElement RETENTIONREPORT;
	
	@FindBy(xpath = "//h2[contains(text(),'Customer Service Queue')]")
	public WebElement CUSTOMERSERVICEQUEUE;
	
	@FindBy(xpath = "//h2[contains(text(),'Latest Tweets')]")
	public WebElement LATESTTWEETS;
	
	@FindBy(xpath = "//h2[contains(text(),'Events')]")
	public WebElement EVENTS;
	
	@FindBy(xpath = "//h2[contains(text(),'News')]")
	public WebElement NEWS;
	
	@FindBy(xpath = "//h2[contains(text(),'Pricing Calculator')]")
	public WebElement PRICINGCALCULATOR;
	
	@FindBy(xpath = "//h2[contains(text(),'Send an Invite')]")
	public WebElement SENDINVITE;
	
	@FindBy(xpath = ".//*[@id='CommissionReport']/div/p")
	public WebElement QUARTELYCOMMISSIONS;
	
	@FindBy(xpath = ".//*[@id='CommissionReport']/div/div[1]/div[1]/div/div[1]/div[1]")
	public WebElement QUARTELYCOMMISSIONSVERTICALGRAPHS;
	
	@FindBy(xpath = ".//*[@id='CommissionReport']/div/div[1]/div[1]/div/div[1]/div[2]")
	public WebElement QUARTELYCOMMISSIONSHORIZONTALGRAPHS;
	
	@FindBy(xpath = "//h2[contains(text(),'Lifetime Billing Totals')]")
	public WebElement LIFETIMEBILLINGTOTAL;
	
	@FindBy(xpath = ".//*[@id='CommissionReport']/div/div[1]/div[2]/div/div/div[1]/div/div/p")
	public WebElement TOTALCOMMISSION;
	
	@FindBy(xpath = ".//*[@id='CommissionReport']/div/div[1]/div[2]/div/div/div[1]/div/div/div/span")
	public WebElement TOTALCOMMISSIONDOLLARS;
	
	@FindBy(xpath = "//p[contains(text(),'Total Billed Subject to Commission')]")
	public WebElement BILLEDSUBJECTTOCOMMISSION;
	
	@FindBy(xpath = ".//*[@id='CommissionReport']/div/div[1]/div[2]/div/div/div[2]/div/div/div/span")
	public WebElement BILLEDSUBJECTTOCOMMISSIONDOLLARS;
	
	@FindBy(xpath = ".//*[@id='CommissionReport']/div/div[1]/div[1]/div/div[2]/div/div/select")
	public WebElement CURRENTSHOWINGDROPDOWN;
	
	@FindBy(xpath = "//div[3]/div[2]/div[2]/div[2]/div/div/div[1]/div[1]/div")
	public WebElement INITIATEDCOUNT;
	
	@FindBy(xpath = "//div[3]/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div")
	public WebElement TRIALUSERSDCOUNT;
	
	@FindBy(xpath = "//div[3]/div[2]/div[2]/div[2]/div/div/div[3]/div[1]/div")
	public WebElement REGISTEREDCOUNT;
	
	@FindBy(xpath = "//div[3]/div[2]/div[2]/div[2]/div/div/div[4]/div[1]/div")
	public WebElement LIVECOUNT;
	
	@FindBy(xpath = "//div[4]/div/div/div[1]/div[1]/div/div[1]")
	public WebElement ALLOPENTICKETS;
	
	@FindBy(xpath = "//div[4]/div/div/div[1]/div[2]/div/div[1]")
	public WebElement CLOSEDLAST30DAYS;
	
	@FindBy(xpath = "//div[4]/div/div/div[1]/div[3]/div/div[1]")
	public WebElement TOTALTICKETSLAST30DAYS;
	
	@FindBy(xpath = "//div[4]/div/div/div[1]/div[4]/div/div[1]")
	public WebElement TOTALTICKETS;
	
	@FindBy(xpath = "//div[4]/div/div/div[1]/div[1]/div/div[1]/div/a[contains(text(),'View')]")
	public WebElement ALLOPENTICKETSVIEW;
	
	@FindBy(xpath = "//table/thead/tr/th[1]/div/a[contains(text(),'Client')]")
	public WebElement CLIENT;
	
	@FindBy(xpath = "//table/thead/tr/th[2]/div/a[contains(text(),'Type')]")
	public WebElement TYPE;
	
	@FindBy(xpath = "//table/thead/tr/th[3]/div/a[contains(text(),'Ticket Title')]")
	public WebElement TICKETTITLE;
	
	@FindBy(xpath = "//div[4]/div/div/div[1]/div[1]/div/div[1]/div/a")
	public WebElement CLOSE;
	
	@FindBy(xpath = "//div[4]/div/div/div[1]/div[2]/div/div[1]/div/a[contains(text(),'View')]")
	public WebElement CLOSEDLAST30DATSVIEW;
	
	@FindBy(xpath = "//div[4]/div/div/div[3]/div/div/div/table/thead/tr/th[1]/div")
	public WebElement CLIENT1;
	
	@FindBy(xpath = "//div[4]/div/div/div[3]/div/div/div/table/thead/tr/th[2]/div/a[contains(text(),'Type')]")
	public WebElement TYPE1;
	
	@FindBy(xpath = "//div[4]/div/div/div[3]/div/div/div/table/thead/tr/th[3]/div/a[contains(text(),'Ticket Title')]")
	public WebElement TICKETTITLE1;
	
	@FindBy(xpath = "//a[contains(text(),'Go To Pricing Calculator')]")
	public WebElement GOTOPRICINGCALCULATOR;
	
	@FindBy(xpath = "//a[contains(text(),'INVITE CUSTOMER')]")
	public WebElement INVITECUSTOMER;
	
	@FindBy(xpath = "//div[6]/div[1]/div/div/div[1]/div[2]/h2/a")
	public WebElement EVENTTITLE;
	
	@FindBy(xpath = "//div[@data-url='Dashboard/Events']/div/div/div/div[@class='circle-date']/div")
	public WebElement EVENTDATE;
	
	@FindBy(xpath = "//div[6]/div[1]/div/div/div[1]")
	public WebElement EVENTDESCRIPTION;
	
	@FindBy(xpath = "//div[6]/div[1]/div/div/div[1]/div[3]/a[contains(text(),'Read More')]")
	public WebElement EVENTSREADMORE;
	
	@FindBy(xpath = "//div[6]/div[1]/div/div/div[5]/a[contains(text(),'More')]")
	public WebElement EVENTSMORELINK;
	
	@FindBy(xpath = "//div[6]/div[2]/div/div/div[1]/div[2]/h2")
	public WebElement NEWTITLE;
	
	@FindBy(xpath = "//div[@data-url='Dashboard/EventsAndNews']/div/div/div/div[@class='circle-date']")
	public WebElement NEWSDATE;
	
	@FindBy(xpath = "//div[6]/div[2]/div/div/div[1]")
	public WebElement NEWSDESCRIPTION;
	
	@FindBy(xpath = "//div[6]/div[2]/div/div/div[1]/div[3]/a[contains(text(),'Read More')]")
	public WebElement NEWSSREADMORE;
	
	@FindBy(xpath = "//div[6]/div[2]/div/div/div[3]/a[contains(text(),'More')]")
	public WebElement NEWSMORELINK;
	
	@FindBy(xpath = "//a[contains(text(),'Return to Dashboard')]")
	public WebElement RETURNTODASHBOARD;

}
