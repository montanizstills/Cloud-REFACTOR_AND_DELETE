package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageAdjustmentFile {

	@FindBy(xpath = "//span[contains(text(),'Calculate Tax')]")
	public WebElement CALTAX;

	@FindBy(xpath = "//a[@title='Manage Adjustment File']")
	public WebElement MANAGEADJUSTMENTFILELINK;

	@FindBy(xpath = "//h1")
	public WebElement MANAGEADJUSTMNETFILELABEL;

	@FindBy(xpath = "//a[contains(text(),'Download Extended Help')]")
	public WebElement DOWNLOADEXTENDHELP;

	@FindBy(xpath = "//a[contains(text(),'Download Format')]")
	public WebElement DOWNLOADFORMATLINK;

	@FindBy(xpath = "//a[contains(text(),'Upload New File')]")
	public WebElement UPLOADFILELINK;

	@FindBy(xpath = "//legend")
	public WebElement UPLOADADJUSTMENTFILElABEL;

	@FindBy(xpath = "//select[@id='RequestedMessageType']")
	public WebElement MESSAGETYPE;

	@FindBy(xpath = "//input[@id='txtadjFile']")
	public WebElement UPLOADFILETEXT;

	@FindBy(xpath = "//a[contains(text(),'Browse')]")
	public WebElement BROWSEBUTTON;

	@FindBy(xpath = "//button[contains(text(),'Upload')]")
	public WebElement UPLOADBUTTON;
}
