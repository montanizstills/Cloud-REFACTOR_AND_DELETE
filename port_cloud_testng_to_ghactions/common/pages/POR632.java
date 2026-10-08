package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class POR632 {

	@FindBy(xpath = "//span[contains(text(),'Calculate Tax')]")
	public WebElement CALTAX;

	@FindBy(xpath = "//a[@title='Manage Adjustment File']")
	public WebElement MANAGEADJUSTMENTFILELINK;

	@FindBy(xpath = "//input[@name='__RequestVerificationToken'][1]")
	public WebElement REQUESTVERIFICATIONTOKEN;

	@FindBy(xpath = "//a/i[contains(text(),'account')]")
	public WebElement ACCOUNTICON;

	@FindBy(xpath = "//li/a/i[contains(text(),'account')]")
	public WebElement ACCOUNT;

	@FindBy(xpath = "//a[contains(text(),'User Settings')]")
	public WebElement USERSETTINGS;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement SAVEBUTTON;

	@FindBy(xpath = "//strong[text()='Success!']")
	public WebElement SUCCESMSG;

	@FindBy(xpath = "//div[@class='alert alert-success ng-binding ng-scope'][contains(.,'Subscription updated!')]")
	public WebElement SUBSCRIPTIONUPDATED;

	@FindBy(xpath = "//div[@class='modal-body ng-scope']/p[2]/a")
	public WebElement TRANSREPORTBADTOKEN;

	@FindBy(xpath = "//h1[contains(text(),'Internal Server Error')]")
	public WebElement ERRORLABEL;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement SETTINGSLINK;

	@FindBy(xpath = "//a[contains(text(),'Edit My Company')]")
	public WebElement EDITCOMPANYLINK;

	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	public WebElement DASHBOARDLINK;

	@FindBy(xpath = "//a[contains(text(),'Go Live')]")
	public WebElement GOLIVEBUTTON;

	@FindBy(xpath = "//button[contains(text(),'Yes, Go Live')]")
	public WebElement YESGOLIVEBUTTON;

	@FindBy(xpath = "//span[contains(text(),'Manage Tax Returns')]")
	public WebElement MANAGETAXRETURNSLINK;

	@FindBy(xpath = "//select[@id='filing-period']")
	public WebElement FILLINGPERID;

	@FindBy(xpath = "//a[@id='toggle-prepayments-1692']")
	public WebElement PAPREPAYMENTLINK;

	@FindBy(xpath = "//input[@id='amount-prepayment-40']")
	public WebElement PAPREPAYMENTAMT;

	@FindBy(xpath = "//button[@id='pay-prepayment-40']")
	public WebElement PAYMENTBUTTON;

	@FindBy(xpath = "//div[3]/form//input[@name='__RequestVerificationToken']")
	public WebElement BADTOKENPREPAYMENTS;

	@FindBy(xpath = "//input[@id='emailId']")
	public WebElement NEWUSEREMAIL;

	@FindBy(xpath = "//table/tbody/tr[1]/td[10]/a[1]")
	public WebElement VIEWALLUSERSACTION1;

	@FindBy(xpath = "//h4/span")
	public WebElement ANTITOKENMESSAGE;

	@FindBy(xpath = "//h4/strong[contains(text(),'User Deleted!')]")
	public WebElement DELSUCESSMESSAGE;

	@FindBy(xpath = "//h4/strong[contains(text(),'User Reactivated!')]")
	public WebElement REACTVSUCCESSMESSAGE;

	@FindBy(xpath = "//div[2]/a[contains(text(),'Delete')]")
	public WebElement DELETEBUTTON;

	@FindBy(xpath = "//p[1]/button[@class='btn btn-primary']")
	public WebElement NEWVALUEWARNING;

	@FindBy(xpath = "//a[contains(text(),'close')]")
	public WebElement CLOSE_INPROGRESS;

	@FindBy(xpath = "//a[contains(text(),'Check Status')]")
	public WebElement CHECKSTATUS;

	@FindBy(xpath = "//a[contains(text(),'Go to the Dashboard')]")
	public WebElement GOTODASHBOARD;

	@FindBy(xpath = "//li[text()='Update Client Status']/i[@class='icon icon-ok']")
	public WebElement OKICON_INPROGRESS;

	@FindBy(xpath = "//div[2]/div[2]/div[3]/div/div")
	public WebElement HIDDENELEMENT;

	@FindBy(xpath = "//td/a[contains(text(),'Reactivate')]")
	public WebElement REACTIVATIONLINK;

	@FindBy(xpath = "//a[contains(text(),'Delete')]")
	public WebElement DELETELINK;

	@FindBy(xpath = "//button[@class='btn btn-primary']")
	public WebElement SAVE;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement SUBMIT;

	@FindBy(xpath = "//div[@class='pull-right']/button[@class='btn btn-primary']")
	public WebElement CREATEBUTTON;

	@FindBy(xpath = "//input[@id='startDate']")
	public WebElement STARTDATE;

	@FindBy(xpath = "//input[@id='endDate']")
	public WebElement ENDDATE;

	@FindBy(xpath = "//a/span[contains(text(),'Help Center')]")
	public WebElement INLINEHELP;

	@FindBy(xpath = "//button[@id='save-help']")
	public WebElement SAVEHELP;

	@FindBy(xpath = "//form[@id='help-form']/fieldset/div/div[5]/div/span")
	public WebElement SAVEDBUTTON;

	@FindBy(xpath = "//a/span[contains(text(),'Error Monitor')]")
	public WebElement ERRORMONITOR;

	@FindBy(xpath = "//table/tbody/tr[1]/td[8]/a[contains(text(),'Details')]")
	public WebElement DETAILS;

	@FindBy(xpath = "//button[contains(text(),'Retry This Item')]")
	public WebElement RETRYTHISITEM;

	@FindBy(xpath = "//span[@id='MessageCount']")
	public WebElement MESSAGESCOUNT;

	@FindBy(xpath = "(//a[contains(text(),'Customer Registration Complete')])[1]")
	public WebElement CUSTOMERREGISTRATIONCOMPLETE;

	@FindBy(xpath = "//i[@class='glyphicon-envelope message-icon']")
	public WebElement MESSAGEICON;

	@FindBy(xpath = "//a/span[contains(text(),'Manage Tax Calendar')]")
	public WebElement MANAGETAXCALENDER;

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]")
	public WebElement TABLEDATARETURN;

	@FindBy(xpath = "//table/tbody/tr[1]/td[3]/div/input[@class='input-small']")
	public WebElement DUEDATE1;

	@FindBy(xpath = "//table/tbody/tr[1]/td[4]/div/input[@class='input-small']")
	public WebElement LOCKDATEAFFECT;

	@FindBy(xpath = "//table/tbody/tr[2]/td[3]/div/input[@class='input-small']")
	public WebElement DUEDATE2;

	@FindBy(xpath = "//table/tbody/tr[1]/td[6]/button")
	public WebElement UPDATEBUTTON;

	@FindBy(xpath = "//img[@alt='Tax Central by Vertex SMB']")
	public WebElement VERTEXSMBIMG;

	@FindBy(xpath = "//input[@id='subscription-password']")
	public WebElement SUBSCRIPTIONPWD;

	@FindBy(xpath = "//button[@id='subscription-password-ok']")
	public WebElement SUBSCRIPTIONSAVECHANGES;
	
	@FindBy(xpath = "//h1[contains(text(),'Internal Server Error')]")
	public WebElement INTERNALERROR;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement REENTERPSWD;

	@FindBy(xpath = "//button[@id='cancel-ok']")
	public WebElement SAVECHANGES;

	@FindBy(xpath = "//button[@id='save-ok']")
	public WebElement SETTINGSAVECHANGES;

	@FindBy(xpath = "//div[@class='alert alert-success ng-binding ng-scope']")
	public WebElement SUCCESSSUB;

	public final String NPROGRESSBUSY_XPATH = "html[@class='nprogress-busy']";

	@FindBy(xpath = "//a[contains(.,'Retry This Item')]")
	public WebElement RETRY;

	@FindBy(xpath = "//div/strong[contains(.,'Success!')]/../../div[contains(.,'Queue has been updated sucessfully.')]")
	public WebElement SUCCESSMSG;

	@FindBy(xpath = "//a[contains(.,'Permanent Error')]")
	public WebElement PermanentError;

}
