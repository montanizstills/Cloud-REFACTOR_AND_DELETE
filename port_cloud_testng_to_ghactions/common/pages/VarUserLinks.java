package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VarUserLinks {

	@FindBy(xpath = "//span[contains(text(),'My Customers')]")

	public WebElement MYCUSTOMERSLINK;
	@FindBy(xpath = "//span[contains(text(),'Marketing ToolKit')]")

	public WebElement MARKETINGTOOLKITLINK;
	@FindBy(xpath = "//span[contains(text(),'Settings')]")

	public WebElement SETTINGSLINK;
	@FindBy(xpath = "//a[contains(text(),'Edit My Company')]")

	public WebElement EDITCOMPANYLINK;
	@FindBy(xpath = "//a[contains(text(),'View All Users')]")

	public WebElement VIEWALLUSERSLINK;
	@FindBy(xpath = "//a[contains(text(),'Add New User')]")

	public WebElement ADDNEWUSERLINK;
	@FindBy(xpath = "//a[contains(text(),'Invite User')]")

	public WebElement InviteNEWUSERLINK;
	@FindBy(xpath = "//a/span[contains(text(),'Visit Help Center')]")

	public WebElement GETHELPLINK;
	@FindBy(xpath = "//div[@id='docExtraTabs']/ul/li/a[@id='Informationlink']")

	public WebElement GETHELPINFORMATIONLINK;
	@FindBy(xpath = "//a[contains(text(),'What’s New')]")

	public WebElement VISITHELPCENTERWHATSNEW;
	@FindBy(xpath = "//a/span[contains(text(),'Contact Support')]")

	public WebElement CONTACTSUPPORTLINK;
	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")

	public WebElement DASHBOARDLINK;
	@FindBy(xpath = "//div[@class='twitter-feed']/div[@class='tweet']")

	public WebElement TWEETS_NOTNULL;
	@FindBy(xpath = "//div[@class='content-feed']/h2[text()='Events']/../div[@class='teaser-box']")

	public WebElement EVENTS_NOTNULL;
	@FindBy(xpath = "//div[@class='right-col dashboard-col']/div")

	public WebElement DASHBOARDERRORTEXT;
	@FindBy(css = "input.btn.btn-primary")

	public WebElement EXPORTBUTTON;
	@FindBy(id = "lookup-rates")

	public WebElement LOOKUPTRATEBUTTON;
	@FindBy(tagName = "h2")

	public WebElement MARKETINGDOWNLOADT;
	@FindBy(xpath = "//a[contains(.,'INVITE CUSTOMER')]")

	public WebElement INVITECUSTOMER;

	@FindBy(xpath = "//input[@id='NewUser_FirstName']")

	public WebElement NEWUSERFIRSTNAME;
	@FindBy(xpath = "//input[@id='NewUser_LastName']")

	public WebElement NEWUSERLASTNAME;
	@FindBy(xpath = "//input[@id='NewUser_Email']")

	public WebElement NEWUSEREMAIL;
	@FindBy(xpath = "//input[@id='NewUser_Phone']")

	public WebElement NEWUSERPHONE;
	@FindBy(xpath = "//select[@id='NewUser_Role']")

	public WebElement NEWUSERROLE;
	@FindBy(xpath = "//a[contains(text(),'Back  »')]")

	public WebElement NEWUSERBACK;
	@FindBy(xpath = "//p[3]/a")

	public WebElement EMAILVERIFYLINK;
	@FindBy(xpath = "//input[@id='username']")

	public WebElement USERNAME;
	@FindBy(xpath = "//input[@id='password']")

	public WebElement NEWPASSWORD;
	@FindBy(xpath = "//input[@id='PasswordConfirm']")

	public WebElement NEWPASSWORDCONFIRM;
	@FindBy(xpath = "//input[@id='AcceptedTerms']")

	public WebElement ACCEPTEDTERMS;
	@FindBy(xpath = "//button[@type='submit']")

	public WebElement CONTINUE;
	@FindBy(xpath = "//button[contains(text(),Complete)]")

	public WebElement NEWUSERCOMPLETE;

	@FindBy(xpath = "//div[@class='col sixcol post-content']/h2[text()='Sorry, nothing to display.']")

	public WebElement NOTHINGTODISPLAY;
	@FindBy(xpath = "//div[@class='left-col nav-col view-collapsed']/div/ul/li/a/span[contains(text(),'Marketing ToolKit')]")

	public WebElement COPSDMTCTABICON;
	@FindBy(css = "a.btn.pull-right")

	public WebElement RETURNTOSETTINGS;
	@FindBy(xpath = "//h1[contains(.,'Events')]")

	public WebElement EVENTS;

	@FindBy(xpath = "//a[contains(.,'Go To Pricing Calculator')]")

	public WebElement GOTOPRICINGCAL;
	@FindBy(xpath = "//select[@class='input-block-level']")

	public WebElement MARKETINGSELECT;
	@FindBy(xpath = "//input[@value='Send Invite']")

	public WebElement SENDINVITE;
	@FindBy(xpath = "//button[contains(.,'Submit Ticket')]")

	public WebElement SUBMITTIECKET;

	@FindBy(xpath = "//span[contains(text(),'Calculate Taxes')]")

	public WebElement taxesHover;

	@FindBy(xpath = "//span[contains(text(),'Manage Tax Returns')]")

	public WebElement ReturnsHover;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")

	public WebElement SettingsHover;

	@FindBy(xpath = "//span[contains(text(),'Reports')]")

	public WebElement ReportsHover;
	@FindBy(xpath = "//div/p[contains(.,'All Open Tickets')]/../a[contains(.,'View')]")

	public WebElement VIEW;
	@FindBy(xpath = "//div/p[contains(.,'All Open Tickets')]/../a[contains(.,'Close')]")

	public WebElement CLOSE;
	@FindBy(xpath = "//div[@class='span3 pagination-index']/../div/select[@class='pagesize']")

	public WebElement PAGE;
	@FindBy(xpath = "//div/p[contains(.,'Closed Last 30 Days')]/../a[contains(.,'View')]")

	public WebElement VIEWPAGE;
	@FindBy(xpath = "//div/p[contains(.,'Closed Last 30 Days')]/../a[contains(.,'Close')]")

	public WebElement CLOSEPAGE;
	@FindBy(xpath = "//div/p[contains(.,'Closed Last 30 Days')]/../a[contains(.,'Close')]")

	public WebElement CLOSEPAGESIZE;

	@FindBy(xpath = "//h1[contains(.,'My Customers ')]/..//inline-help/a/i")

	public WebElement MYCUSTOMERHEADICON;

	@FindBy(xpath = "//input[@placeholder='Search']")

	public WebElement SEARCHBOX;

	@FindBy(xpath = "//table/tbody/tr[2]/td[1]")

	public WebElement NAME;

	@FindBy(xpath = "//table/thead/tr/th[1]/div/span[contains(.,'Name')]")

	public WebElement NAMEHEADER;
	@FindBy(xpath = "//table/thead/tr/th[2]/div/span[contains(.,'Address')]")

	public WebElement ADDRESSHEADER;
	@FindBy(xpath = "//table/thead/tr/th[3]/div/span[contains(.,'City')]")

	public WebElement CITYHEADER;
	@FindBy(xpath = "//table/thead/tr/th[4]/div/span[contains(.,'State')]")

	public WebElement STATEHEADER;
	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'Phone')]")

	public WebElement PHONEHEADER;
	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(.,'Status')]")

	public WebElement STATUSHEADER;
	@FindBy(xpath = "//table/thead/tr/th[7]/div/span[contains(.,'Subscription')]")

	public WebElement SUBSCRIPTIONHEADER;
	@FindBy(xpath = "//table/thead/tr/th[7]")

	public WebElement SUBSCRIPTION;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Transactions')]")

	public WebElement TRANSACTIONSHEADER;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Actions')]")

	public WebElement ACTIONSHEADER;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Users')]")

	public WebElement USERSHEADER;
	@FindBy(xpath = "//table/tbody/tr[2]/td[10]/a/i[@class='icon icon-add icon-blue']")

	public WebElement PLUSICON;
	@FindBy(xpath = "//table/thead/tr/th[@sort-header='name']")

	public WebElement NAMESORTING;
	@FindBy(xpath = "//table/thead/tr/th[@sort-header='Address']")

	public WebElement ADDRESSSORTING;
	@FindBy(xpath = "//table/thead/tr/th[@sort-header='city']")

	public WebElement CITYSORTING;
	@FindBy(xpath = "//table/thead/tr/th[@sort-header='state']")

	public WebElement STATESORTING;
	@FindBy(xpath = "//table/thead/tr/th[@sort-header='phone']")

	public WebElement PHONESORTING;
	@FindBy(xpath = "//table/thead/tr/th[@sort-header='status']")

	public WebElement STATUSSORTING;
	@FindBy(xpath = "//table/thead/tr/th[@sort-header='subscription']")

	public WebElement SUBSCRIPTIONSORTING;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'First')]")

	public WebElement FIRSTCOLUMNNAME;
	@FindBy(xpath = "//table/thead/tr/th[2][contains(text(),'Last')]")

	public WebElement LASTCOLUMNNAME;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Email')]")

	public WebElement EMAILCOLUMNNAME;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'User Name')]")

	public WebElement USERNAMECOLUMNNAME;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Phone')]")

	public WebElement PHONECOLUMNNAME;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Created')]")

	public WebElement CREATEDCOLUMNNAME;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Last Login')]")

	public WebElement LASTLOGINCOLUMNNAME;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Status')]")

	public WebElement STATUSCOLUMNNAME;
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Role')]")

	public WebElement ROLECOLUMNNAME;
	@FindBy(xpath = "//pagination-extension/span/select")

	public WebElement PAGINATIONCOUNT;
	@FindBy(xpath = "//input[@value='Export']")

	public WebElement MYCUSTOMERSEXPORTBUTTON;
	@FindBy(xpath = "//span[contains(text(),'Rates & Jurisdictions')]")

	public WebElement RatesJurisdictions;
	@FindBy(id = "username")

	public WebElement VARUSERNAME;
	@FindBy(id = "password")

	public WebElement PASSWORD;
	@FindBy(xpath = "//button[@type='submit']")

	public WebElement LOGIN;

	@FindBy(xpath = "//table/tbody/tr[2]/td[7]")

	public WebElement SUBSCRIPTIONNAME;
	@FindBy(xpath = "//a[@class='btn pull-right ng-scope'][contains(.,'Return to Settings')]")

	public WebElement returntoSettingsleftbottomlink;

	@FindBy(xpath = "//h1[contains(.,'Settings')]")

	public WebElement settingslabel;

	@FindBy(xpath = "//a[contains(.,'Ok')]")

	public WebElement OKbutton;

	public final String NPROGRESSBUSY_XPATH = "html[@class='nprogress-busy']";
}
