/*###################################################################################################
Name: BillingPage.java 
Description: Configuration Report page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/28/2014
Date Modified:
Updated Comments:
#####################################################################################################*/
package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class UploadAdjustmentFile {
	@FindBy(xpath = "//div[not(@class='row-fluid ng-hide')]/div/div[contains(text(),'Your configuration is not available for posting transactions to your journal.')]")
	public WebElement UNPUBLISHED;
	@FindBy(xpath = "//span[contains(text(),'Calculate Tax')]")
	public WebElement CALTAX;
	@FindBy(xpath = "//div[@class='container-nav']/ul/li/ul/li/a[contains(text(),'Manage Adjustment File')]")
	public WebElement MANAGEADJUSTMENTFILELINK;
	@FindBy(id = "RequestedMessageType")
	public WebElement MESSAGETYPE;
	@FindBy(xpath = "//a[@ui-sref='upload'][contains(.,'+ Upload New File')]")
	public WebElement UPLOADNEWFILE;

	@FindBy(xpath = "//a[@ng-click='upload();'][contains(.,'+ Upload New File')]")
	public WebElement ADDRESSCLEANSING_UPLOADNEWFILE;

	@FindBy(xpath = "//a[@ui-sref='returnsAdjustmentFile.upload'][contains(.,'+ Upload New File')]")
	public WebElement RETURNSONLYUPLOADNEWFILE;
	@FindBy(xpath = "//label[contains(.,'Browse')]")
	public WebElement BROWSE;
	@FindBy(id = "AdjustmentFileFormat")
	public WebElement ADJUSTMENTFILEFORMAT;
	@FindBy(xpath = "//button[contains(.,'Upload File')]")
	public WebElement UPLOADBUTTON;
	@FindBy(xpath = "//a[contains(text(),'Download Format')]")
	public WebElement DOWNLOADFORMATLINK;
	/*
	 * @FindBy(linkText="Extended Help") public WebElement DOWNLOADEXTENDHELP
	 */
	@FindBy(xpath = "//div[@class='alert alert-error ng-binding']")
	public WebElement ERRORINVALIDUPLOAD;
	@FindBy(xpath = "//div[@class='alert alert-error ng-binding'][contains(.,'Only csv files are allowed')]")
	public WebElement ERRORNOTCSV;
	@FindBy(xpath = "//div[@class='alert alert-error ng-binding']")
	public WebElement UPLOADERROR;
	@FindBy(xpath = "//div[@class='alert alert-error ng-binding'][contains(.,'The selected file does not contain any transaction data')]")
	public WebElement NOTTRANSACTIONERROR;
	@FindBy(xpath = "//div[@class='alert alert-error ng-binding'][contains(.,'Duplicate column names encountered, Please correct and re-upload.')]")
	public WebElement DUPLICATEERROR;
	@FindBy(xpath = "//a[@class='btn pull-right']")
	public WebElement BACK;
	@FindBy(xpath = "//fileupload/div[contains(.,'Your file has been successfully uploaded!')]")
	public WebElement SUCCESSUPLOAD;
	@FindBy(xpath = "//h1[contains(text(),'Upload Adjustment File')]")
	public WebElement UPLOADADJUSTMENTFILEHEADER;
	@FindBy(xpath = "//a[contains(text(),'Return To Adjustment Files')]")
	public WebElement RETURNTOADJUSTMENTFILES;
	@FindBys({ @FindBy(xpath = "//select[@id='AdjustmentFileFormat']/option"), })
	public List<WebElement> ADJUSTFILEFORMAT;
	@FindBy(xpath = "//button[contains(text(),'Download')]")
	public WebElement DOWNLOADBTN;
	@FindBy(xpath = "//select[@id='AdjustmentFileFormat']")
	public WebElement FILEFORMAT;
	@FindBy(xpath = "//select[@id='RequestedMessageType']/option[text()='Accrual']/../option[text()='Asset/Goods Movement']/../option[text()='Distribute Tax']/../option[text()='Inventory Removal']/../option[text()='Invoice Verification']/../option[text()='Purchase Order']")
	public WebElement MESSAGETYPELISTBOXVALUES;
	@FindBy(xpath = "//select[@id='RequestedMessageType']/option[text()='Distribute Tax']/../option[text()='Invoice']/../option[text()='Quotation']")
	public WebElement SALESMESSAGETYPELIST;
	@FindBy(xpath = "//div[contains(text(),'SALES versus SELLER')]")
	public WebElement SALESWRNGMSG;
	@FindBy(xpath = "//h1[contains(text(),'Adjustment Files')]")
	public WebElement ADJUSTMENTFILESHEADER;
	@FindBy(xpath = "//div[contains(text(),'Adjustment files are used to upload multiple transactions for various types of tax calculations. Need more information? Please refer to the following article in the')]")
	public WebElement ADJUSTMENTFILETEXT;
	@FindBy(xpath = "//a[contains(text(),'Vertex Help Center')]")
	public WebElement EXTENDEDHELP;
	@FindBy(xpath = "//a[contains(text(),'Upload New File')]")
	public WebElement UPLOADFILELINK;
	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/thead/tr/th[contains(.,'Adjustment File Name')]")
	public WebElement TABLEADJUSTMENTFILENAME;
	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/thead/tr/th[contains(text(),'Original File')]")
	public WebElement TABLEORIGINALFILE;
	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/thead/tr/th/div/span[contains(text(),'Message Type')]")
	public WebElement TABLEMESSAGETYPE;
	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/thead/tr/th[contains(text(),'Success Report')]")
	public WebElement TABLESUCCESSREPORT;
	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/thead/tr/th[contains(text(),'Error Report')]")
	public WebElement TABLEERRORREPORT;
	@FindBy(xpath = "//i[contains(text(),'Messages')]")
	public WebElement portalNotificationBox;
	@FindBy(xpath = "//ul/li[1]/a[contains(text(),'Vertex SMB Address Cleansing File Processed')]")
	public WebElement addresscleanfileProcessed;
	@FindBy(xpath = "//div/div/div[2]/div[1]/div/i[2]")
	public WebElement archieveMessage;
	@FindBy(xpath = "//button[contains(.,'Upload File') and @ng-disabled='uploadDisabled']")
	public WebElement uplaodbtndisabledinvalifile;
	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/tbody/tr[1]/td[contains(.,'Processed')]")
	public WebElement AdjustmentfileundoGoLive;
	@FindBy(xpath = "//table[@id='returnsAdjustmentFilesTable']/tbody/tr[1]/td[contains(.,'Processed')]")
	public WebElement ReturnsAdjustmentfileundoGoLive;
	@FindBy(xpath = "//div[@class='alert alert-error ng-binding'][contains(.,'One or more columns in the Adjustment File you have uploaded are not recognized by Vertex Cloud.')]")
	public WebElement FILEUPLOADERROR;
	@FindBy(xpath = "//div[@class='alert alert-error ng-binding']/button")
	public WebElement FILEUPLOADERRORCLOSE;
	@FindBy(xpath = "//div[@class='section header-block ng-scope']/a[contains(text(),'Return To Address Cleansing Files')]")
	public WebElement RETURNTOADDRESSCLEANSINGFILES;
	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement QUICKSEARCH;
	@FindBy(id = "fromDate")
	public WebElement FROMDATE;
	@FindBy(id = "toDate")
	public WebElement TODATE;
	@FindBy(xpath = "//div/div/a[contains(.,'Upload Data')]")
	public WebElement UPLOADDATA;
	@FindBy(xpath = "//a[contains(.,'Sync Connector')]")
	public WebElement SYNCCONNECTOR;
	@FindBy(id = "btnSync")
	public WebElement SYNC;
	@FindBy(xpath = "//h1[contains(.,'SYNC CONNECTOR')]")
	public WebElement SYNCCONNECTORHEADING;
	@FindBy(xpath = "//select[@id='connectorTypes']")
	public WebElement SELECTCLOUDELEMENTS;
	@FindBy(xpath = "//select[@id='taxpayers']")
	public WebElement SELECTTAXPAYERS;
	@FindBy(xpath = "//a[@class='btn pull-right']")
	public WebElement RETURNTORETURNSONLYADJUSTMENT;
	/*
	 * @FindBy(xpath="//i[contains(.,'File Generation is in progress')]") public
	 * WebElement FILEGENERATIONTEXT;
	 */
	@FindBy(xpath = "//a[contains(.,'Upload new file')]")
	public WebElement ROUPLOADNEWFILE;
	@FindBy(xpath = "//h3[contains(.,'This Customer has no committed Companies')]")
	public WebElement NOCONFIGTPTEXT;
	@FindBy(xpath = "//div/label[contains(.,'1.Select Returns Instance')]")
	public WebElement SELECTRETURNSINSTANCE;
	@FindBy(xpath = "//div/label[contains(.,'Select Company')]")
	public WebElement SELECTCOMPANY;
	@FindBy(xpath = "//div/label[contains(.,'3.Select Time Period (Past Dates Only)')]")
	public WebElement SELECTTIMEPERIOD;
	public final String FILEGENERATIONTEXT = "//i[contains(.,'File Generation is in progress')]";
	@FindBy(xpath = "//h1[contains(.,'Adjustment File Upload')]")
	public WebElement ADJUSTMENTFILESHELPCENTER;
	@FindBy(xpath = "//h1[contains(.,'Returns Only Adjustment File Upload')]")
	public WebElement RETURNSONLYHELPCENTER;
	@FindBy(xpath = "//h1[contains(.,'Download a Rate File')]")
	public WebElement DOWNLOADRATEFILEHELPCENTER;

	@FindBy(xpath = "//input[@id='fromDate']")
	public WebElement STARTDATE;

	@FindBy(xpath = "//input[@id='toDate']")
	public WebElement ENDDATE;

	@FindBy(xpath = "//div[@class='alert alert-error']/strong[contains(.,'Errors Found!')]")
	public WebElement ERRORSFOUND;
	
	@FindBy(xpath="//a[contains(.,'Address Cleansing')]")
	public WebElement ADDRESSCLEANSINGLINK;
	
	@FindBy(xpath="//h1[contains(.,'Address Cleansing Files')]")
	public WebElement ADDRESSCLEANSINGFILESHEADING;
	
	@FindBy(xpath="//h1/i[@class='icon-section icon-address-cleansing']")
	public WebElement ADDRESSCLEANSINGICON;
	
	@FindBy(xpath="//div[@id='VTMSMain']/div/div/div/div[contains(.,'Address Cleansing allows you to upload and validate addresses in order to ensure accurate sales and use tax calculations.  This option is subject to an additional fee based upon consumption.')]")
	public WebElement HELPINGTEXT;
	
	@FindBy(xpath="//table/thead/tr/th/div[contains(.,'File Upload Date')]")
	public WebElement FILEUPLOADDATECOL;
	
	@FindBy(xpath="//table/thead/tr/th/div[contains(.,'File Name')]")
	public WebElement FILENAMECOL;
	
	@FindBy(xpath="//table/thead/tr/th[contains(.,'Original File')]")
	public WebElement ORIGINALFILECOL;
	
	@FindBy(xpath="//table/thead/tr/th[contains(.,'Success Report')]")
	public WebElement SUCCESSREPORTCOL;
	
	@FindBy(xpath="//table/thead/tr/th[contains(.,'Error Report')]")
	public WebElement ERRORREPORTCOL;
	
	@FindBy(xpath="//table/thead/tr/th[contains(.,'Status')]")
	public WebElement STATUSCOL;
	@FindBy(xpath="//h3[contains(.,'Add-On Option')]")
	public WebElement ADDONOPTIONHEADING;
	@FindBy(xpath="//p[contains(.,'You can upload multiple addresses at once in a .csv file and receive an output file with the resulting cleansed addresses.  Your account will be charged for each address row processed.')]")
	public WebElement POPUPTEXT1;
	@FindBy(xpath="//strong[contains(.,'Address Cleansing is an add-on option.')]")
	public WebElement POPUPTEXT2;
	@FindBy(xpath="//a[contains(.,'Continue')]")
	public WebElement CONTINUEBUTTON;
	@FindBy(xpath="//a[contains(.,'Cancel')]")
	public WebElement CANCELBUTTON;
	@FindBy(xpath="//h1[contains(.,'Upload Address Cleansing File')]")
	public WebElement UPLOADADDRESSCLEANSINGFILEHEADING;
		
}
