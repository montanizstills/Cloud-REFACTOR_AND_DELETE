package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateNotification {

	@FindBy(xpath = "//span[text()='Update Notification']")

	public WebElement UPDATENOTIFICATIONLINK;
	@FindBy(xpath = "//div[@class='controls']/select")

	public WebElement NOTIFICATIONDRPDN;
	@FindBy(xpath = "//input[@id='title']")

	public WebElement TITLETXTBOX;

	@FindBy(xpath = "//textarea[@class='support-wide-field']")

	public WebElement DESCRIPTINTXTBOX;
	@FindBy(xpath = "//textarea[@name='description']/../div/span")

	public WebElement DESCRIPTIONERROR;

	@FindBy(xpath = "//label[text()='Select  Notification']")

	public WebElement SELECTNOTIFICATIONLABEL;
	@FindBy(xpath = "//label[text()='Select  Notification']/../div[@class='controls']/select")

	public WebElement SELECTNOTIFICATIONDRPDOWN;
	@FindBy(xpath = "//label[text()='Notified Client Type']")

	public WebElement NOTIFIEDCLIENTTYPELABEL;
	@FindBy(xpath = "//input[@name='ClientName']")

	public WebElement NOTIFIEDCLIENTYPETXTBOX;
	@FindBy(xpath = "//label[text()='Send To:']")

	public WebElement SENDTOLABEL;
	@FindBy(xpath = "//select[@id='sendToFlag']")

	public WebElement SENDTODROPDOWN;
	@FindBy(xpath = "//label[text()='Notified via Email']")

	public WebElement NOTIFIEDVIAEMAIL;
	@FindBy(xpath = "//select[@id='emailNotification']")

	public WebElement NOTIFIEDVIAEMAILDRPDOWN;
	@FindBy(xpath = "//label[text()='Notified via Internal Message']")

	public WebElement NOTIFIEDVIAINTERMESSAGE;
	@FindBy(xpath = "//select[@id='internalMessageNotification']")

	public WebElement NOTIFEDVIAINTERNALMESSDRPDOWN;
	@FindBy(xpath = "//label[text()='Subject Line']")

	public WebElement SUBJECTLINELABEL;
	@FindBy(xpath = "//input[@id='title']")

	public WebElement SUBJECTLINETXTBOX;
	@FindBy(xpath = "//label[text()='Message Body']")

	public WebElement MESSAGEBODYLABEL;
	@FindBy(xpath = "//textarea[@name='description']")

	public WebElement MESSAGEBODYTXTBOX;
	@FindBy(xpath = "//button[@id='save-help']")

	public WebElement SAVEBTN;
	@FindBy(xpath = "//a[@class='btn btn-primary']")

	public WebElement CANCELBTN;

	@FindBy(xpath = "//span[text()='This field is required.']")

	public WebElement ERRORMESSAGE;

}
