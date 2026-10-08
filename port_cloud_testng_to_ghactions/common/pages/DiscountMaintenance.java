/*###################################################################################################
Name: ConfigurePage.java 
Description: Professional User - Configure Page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiscountMaintenance {

	@FindBy(xpath = "//a[@title='Discount Maintenance']")
	public WebElement DISCOUNTMAINTENANCE;
	
	@FindBy(xpath = "//span[@id='lblClientName' and @class='control-label']")
	public WebElement CLIENTNAME;
	
	@FindBy(xpath = "//span[@id='lblClientNumber' and @class='control-label']")
	public WebElement CLIENTPHONE;
	
	@FindBy(xpath = "//span[@id='lblClientAddress' and @class='control-label']")
	public WebElement ADDRESS;
	
	@FindBy(xpath = "//button[@id='btnUpdateDiscount']")
	public WebElement UPDATE;
	
	@FindBy(xpath = "//tr[1][@class='grid-row ']/td[@data-name='ClientNumber']")
	public WebElement ROW;
	
	@FindBy(xpath = "//li[text()=' Please Modify Client Discount Information.']")
	public WebElement ERRORMODIFYINFO;
	
	@FindBy(xpath = "//li[text()=' Discount percentage Should be between 1 to 100 ']")
	public WebElement ERRORDISCOUNTPER;
	
	@FindBy(xpath = "//input[@id='txtDisEffectiveDate']")
	public WebElement EFFECTIVEDATETEXT;
	
	@FindBy(xpath = "//input[@id='txtDisEndDate']")
	public WebElement ENDDATETEXT;
	
	@FindBy(xpath = "//input[@id='txtDisPercentage']")
	public WebElement DISPERTEXT;
	
	@FindBy(xpath = "//div[@id='divModelStatus']/div[text()='Request has been sent to approval admin']")
	public WebElement TREASCONFIRMSG;
	
	@FindBy(xpath = "//button[@id='btnClose']")
	public WebElement CLOSE;
	
	@FindBy(xpath = "//tr[@class='grid-row ']/td[text()='Pending Approval']")
	public WebElement PENDINGAPPROVAL;
	
	@FindBy(xpath = "//label[text()='History']")
	public WebElement HISTORYLINK;
	
	@FindBy(xpath = "//tr[@class='grid-row ']/td[text()='Pending']")
	public WebElement PENDINGSTATUS;
	
	@FindBy(xpath = "//button[@id='btnClose']")
	public WebElement DISCOUNTCLOSE;
	
	@FindBy(xpath = "//strong[text()='Already One Request is Pending for Approval for this Client. Do you want to override it? ']")
	public WebElement OVERRIDEMESSAGE;
	
	@FindBy(xpath = "//button[@id='btnIsReqExsitsProceed']")
	public WebElement PROCEEDBTN;
	
	@FindBy(xpath = "//button[@id='btnIsReqExsitsClose']")
	public WebElement CLOSEBTN;
	
	@FindBy(xpath = "//a[@title='Approval Discount']")
	public WebElement APPROVALDISCOUNT;
	
	@FindBy(xpath = "//button[@id='btnApprove']")
	public WebElement APPROVEBTN;
	
	@FindBy(xpath = "//div[@id='divModelStatus']/div[text()='Request has been approved']")
	public WebElement APPROVECONFIRMSG;
	
	@FindBy(xpath = "//tr[@class='grid-row grid-row-selected']/td[2]")
	public WebElement CLINUMBGRID;
	
	@FindBy(xpath = "//tr[@class='grid-row grid-row-selected']/td[3]")
	public WebElement CLINAMEGRID;
	
	@FindBy(xpath = "//tr[@class='grid-row grid-row-selected']/td[4]")
	public WebElement DISCOUNTGRID;
	
	@FindBy(xpath = "//tr[@class='grid-row grid-row-selected']/td[5]")
	public WebElement EFFDATEGRID;
	
	@FindBy(xpath = "//tr[@class='grid-row grid-row-selected']/td[6s]")
	public WebElement ENDDATEGRID;
	
	@FindBy(xpath = "//strong[text()='SMB Customer Name: ']")
	public WebElement CUSTNAMETEXT;
	
	@FindBy(xpath = "//strong[text()='SMB Customer Name: ']")
	public WebElement CUSTPHONE;
	
	@FindBy(xpath = "//h2[text()=' SMB Customer Discount History']")
	public WebElement HISTORYLINHEADING;
	
	@FindBy(xpath = "//button[@id='btnClse']")
	public WebElement HISCLOSEBTN;

}
