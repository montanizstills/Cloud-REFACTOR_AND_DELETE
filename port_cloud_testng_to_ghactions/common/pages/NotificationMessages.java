package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationMessages {

	@FindBy(id = "messageLink")
	public WebElement MESSAGEICON;

	@FindBy(xpath = "//div[@class='pull-right']/i[@class='glyphicon-remove message-icon']")
	public WebElement DELETEICON;

	@FindBy(xpath = "//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Configuration Update'][1]")
	public WebElement SMBCONFIGURATION;

	@FindBy(xpath = "//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Taxability Report'][1]")
	public WebElement SMBTAXABILITYREPORT;

	@FindBy(xpath = "//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Rate File Available'][1]")
	public WebElement SMBRATEFILE;

	@FindBy(xpath = "//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Report Generated'][1]")
	public WebElement SMBREPORTS;

	@FindBy(linkText = "Vertex Cloud Returns Generated")
	public WebElement RETURNS;

	@FindBy(xpath = "//div[@class='col-content']/ul/li[text()='Your requested configuration changes are now active.']")
	public WebElement CONFIGURATIONTEXT;

	@FindBy(xpath = "//div[@class='col-content']/ul/li[text()='Your custom taxability report(s) are available.']")
	public WebElement TAXABILITYREPORTTEXT;

	@FindBy(xpath = "//div[@class='col-content']/ul/li[text()='Your requested Rate Files have been generated.']")
	public WebElement RATEFILETEXT;

	@FindBy(xpath = "//div[@class='col-content']/ul/li[text()='Your requested report has been generated.']")
	public WebElement REPORTSTEXT;

	@FindBy(xpath = "//div[@class='col-content']/ul/li[text()='Your requested returns have been generated.']")
	public WebElement RETURNSTEXT;

	@FindBy(linkText = "Vertex Cloud Configuration Update")
	public WebElement CONFIGURATIONHEADING;

	@FindBy(linkText = "Vertex Cloud Taxability Report")
	public WebElement TAXABILITYREPORTHEADING;

	@FindBy(linkText = "Vertex Cloud Rate File Available")
	public WebElement RATEFILEHEADING;

	@FindBy(linkText = "Vertex Cloud Report Generated")
	public WebElement REPORTSHEADING;

	@FindBy(linkText = "Vertex Cloud Returns Generated")
	public WebElement RETURNSHEADING;

	@FindBy(xpath = "Your requested configuration changes are now active.")
	public WebElement CONFIGURATIONTEXT1;

	@FindBy(xpath = "Your custom taxability report(s) are available.")
	public WebElement TAXABILITYREPORTTEXT1;

	@FindBy(xpath = "Your requested Rate Files have been generated.")
	public WebElement RATEFILETEXT1;

	@FindBy(xpath = "Your requested report has been generated.")
	public WebElement REPORTSTEXT1;

	@FindBy(xpath = "Your requested returns have been generated.")
	public WebElement RETURNSTEXT1;

	@FindBy(xpath = "//h1[contains(.,'Messages')]")
	public WebElement MESSAGESTEXT;

	@FindBy(xpath = "//span[@class='badge badge-info pull-right ng-binding']")
	public WebElement MESSAGESCOUNT;

	@FindBy(xpath = "//span[@class='badge badge-info badge-large']")
	public WebElement NOTIFICATIONMESSAGECOUNT;

	@FindBy(xpath = "//a[contains(.,'Delete All Messages')]")
	public WebElement DELETEALLMESSAGES;

	@FindBy(xpath = "//a[contains(.,'Mark All as Read')]")
	public WebElement MARKALLASREAD;

	@FindBy(xpath = "//i[@title='Mark Unread']")
	public WebElement MARKASUNREAD;

	@FindBy(xpath = "//div[@class='config-col message-list']/div[@class='col-content']/ul/li")
	public WebElement LISTMESSAGES;

	@FindBy(xpath = "//div[@ng-app='messages']/descendant::div[@class='alert alert-success ng-binding']")
	public WebElement ALLREADSUCCESSMESSAGE;

	@FindBy(xpath = "//span[contains(.,'Delete')]")
	public WebElement DELETEBUTTON;

	@FindBy(xpath = "//a[contains(.,'Ok')]")
	public WebElement OKBUTTON;

	@FindBy(xpath = "//p[contains(.,'(No message selected)')]")
	public WebElement NOMESSAGESELECTED;

	public final String SMBCONFIGURATION_XPATH = "//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Configuration Update']";

	public final String SMBTAXABILITYREPORT_XPATH = "//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Taxability Report']";

	public final String SMBRATEFILE_XPATH = "//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Rate File Available']";

	public final String SMBREPORTS_XPATH = "//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Report Generated']";

	public final String RETURNS_XPATH = "//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Returns Generated']";
}
