/*###################################################################################################
Name: BillingPage.java 
Description: Transaction Report page objects with all xpaths
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

public class TransactionReportPage {
	@FindBy(xpath = "//a[@title='Transaction Report']")
	public WebElement TRANSACTIONREPORTLINK;
	@FindBy(xpath = "//select[@id='companyList']")
	public WebElement TAXPAYERSELECT;
	@FindBy(xpath = "//div/h1")
	public WebElement TRANSACTIONREPORTLABEL;
	@FindBy(xpath = "//span[@class='help-popover']/span")
	public WebElement HELPICON;
	@FindBy(xpath = "//h3[@class='popover-title']")
	public WebElement HELPTITLE;
	@FindBy(xpath = "//div[@class='popover-content']")
	public WebElement HELPCONTENT;
	@FindBy(xpath = "//div[@class='pull-right']/button[@class='btn btn-primary']")
	public WebElement CREATEBUTTON;
	@FindBy(xpath = "//button[contains(text(),'Submit') and @disabled='']")
	public WebElement SUBMITBTNDISABLE;
	@FindBy(xpath = "//input[@name='startDate']")
	public WebElement STARTDATE;
	@FindBy(xpath = "//input[@name='endDate']")
	public WebElement ENDDATE;
	@FindBy(xpath = "//label[contains(.,'Transaction Summary')]")
	public WebElement TRANSACTIONSUMMARY;
	@FindBy(xpath = "//label[contains(.,'Transaction Details')]")
	public WebElement TRANSACTIONDETAILS;
	
	@FindBy(xpath = "//label[contains(.,'Transaction Details Abbreviated')]")
	public WebElement TRANSACTIONDETAILSABBREVIATED;
	
	@FindBy(xpath = "//label[contains(.,'Jurisdiction Summary')]")
	public WebElement JURISDICTIONSUMMARYREPORT;
	
	@FindBy(xpath = "//label[contains(.,'Document Summary by Jurisdiction')]")
	public WebElement DOCUMENTSUMMARYJURISDICTION;
	
	@FindBy(xpath = "//input[@name='reportType']/../../label[contains(.,'Large Volume Transaction Detail Report')]")
	public WebElement LARGEVOLUMETRANSACTIONDETAILS;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement SUBMITBUTTON;
	@FindBy(xpath = "//a[contains(text(),'Cancel')]")
	public WebElement CANCELBUTTON;
	@FindBy(xpath = "//h6/a")
	public WebElement BACKTOTRANSACTIONREPORTLINK;
	@FindBy(xpath = "//div[@class='alert alert-success ng-scope']/div/strong[contains(.,'Success!')]")
	public WebElement SUCCESSFULMESSAGE;
	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	public WebElement DASHBOARDLINK;
	@FindBy(xpath = "//div[@style='display: block)']/div[@class='tree-folder-header']/i[@class='glyphicon-folder-close']")
	public WebElement DOWNLOADLABELLINK;
	@FindBy(xpath = "//div[@style='display: block)']/div[@class='tree-folder-content']/div[@class='tree-item']/div[@class='tree-item-name']")
	public WebElement DOWNLOADLINK;
	@FindBy(xpath = "//th[@data-column='modifiedSort']")
	public WebElement EXECDATE;
	@FindBy(xpath = "//table[@id='field-table']/tbody/tr[2]/td/a")
	public WebElement GRIDROW;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Generation Date')]")
	public WebElement COLUMN1;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Report Type')]")
	public WebElement COLUMN2;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Message Type')]")
	public WebElement COLUMN2_Message;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Reporting Period Start Date')]")
	public WebElement COLUMN3;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Reporting Period End Date')]")
	public WebElement COLUMN4;
	@FindBy(xpath = "//table/thead/tr[@class='tablesorter-headerRow']/th[contains(.,'Action')]")
	public WebElement COLUMN5;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Country')]")
	public WebElement COUNTRYCOLUMN;
	@FindBy(xpath = "//select[@id='company']")
	public WebElement SELECTDD;
	@FindBy(xpath = "//select[@id='companyList']")
	public WebElement COMPANYLISTDD;
	@FindBy(xpath = " //select[@id='jurisdiction']")
	public WebElement COUNTRYDD;
	@FindBy(xpath = "//table/tbody/tr/td/a[contains(.,'Download')]")
	public WebElement DOWNLOAD;
	@FindBy(xpath = "//label[contains(.,'State/Territory')]")
	public WebElement STATE;
	@FindBy(xpath = "//label[contains(.,'Transaction Details Abbreviated')]/input")
	public WebElement TRANSACTIONABBREVIATEDREPORT;
	@FindBy(id = "jurisdiction")
	public WebElement STATESELECTIONID;
	@FindBy(xpath = "//label[contains(.,'State/Territory')]")
	public WebElement STATELABEL;
	public final String SUCCESSFULMESSAGE_XPATH = "//div[@class='alert alert-success ng-scope']/div/strong[contains(.,'Success!')]";
	@FindBy(xpath = "//button[contains(text(),'Generate File')]")
	public WebElement CREATENEWCOMPLIANCEFILE;
	@FindBy(xpath = "//button[contains(.,'Generate File')]")
	public WebElement COMPLIANCESUBMITBUTTON;
	@FindBy(xpath = "//select[@id='filingPeriod']")
	public WebElement FILINGPERIOD;
	@FindBy(xpath = "//span[contains(.,'Generation Date')]")
	public WebElement GENERATIONDATE;
	@FindBy(xpath = "//span[contains(.,'Company Name')]")
	public WebElement COMPANYNAME;
	@FindBy(xpath = "//span[contains(.,'Filing Period')]")
	public WebElement FILINGPERIODCOLUMN;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Action')]")
	public WebElement ACTIONCOLUMN;
	@FindBy(xpath = "//label[contains(.,'Jurisdiction Summary')]")
	public WebElement JURISDICTIONSUMMARY;
	@FindBy(xpath = "//label[contains(.,'Document Summary by Jurisdiction')]")
	public WebElement DOCUMENTSUMMARYBYJURISDICTION;
	@FindBy(xpath = "//h1[contains(.,'Quote and Purchase order Report')]")
	public WebElement QuotePurchaseOrderlable;
	@FindBy(tagName = "select")
	public WebElement MessageType;
	@FindBy(xpath = "//a[contains(.,'Back')]")
	public WebElement BackBtn;
	// POR--22205 Provide the VAT Returns Export File to All Vertex Cloud Customers
	@FindBy(xpath = "//h3[contains(.,'This Customer has no committed Companies')]")
	public WebElement BEFORETAXPAYER;
	@FindBy(xpath = "//h1[contains(.,'International Compliance File')]")
	public WebElement InternationalComplianceFileheadertext;
	@FindBy(xpath = "//span[contains(.,'Generation Date')]/../../../th[contains(.,'Company Name')]/../th[contains(.,'Filing Period/Date Range')]/../th[contains(.,'Action')]")
	public WebElement Tablecolumns;
	@FindBy(xpath = "//p/strong[contains(.,'no international compliance files')]")
	public WebElement BeforeRegistrations;
	@FindBy(xpath = "//a[contains(.,'Transaction - International')]")
	public WebElement InternationalTransactionLink;
	@FindBy(xpath = "//span[contains(.,'View details about international transactions for a company and date range.')]")
	public WebElement InternationalTransactionText;
	@FindBy(xpath = "//a[contains(.,'Transaction - US and Canada')]")
	public WebElement TransactionLink;

	@FindBy(xpath = "//span[contains(.,'View details about US and Canadian transactions for a company and date range.')]")
	public WebElement TransactionText;
	@FindBy(xpath = "//a[contains(.,'Legacy Transaction Report')]")
	public WebElement LegacyTransactionReportLink;
	@FindBy(xpath = "//h1[contains(.,'Legacy Transaction Report')]")
	public WebElement LEGACYTRANSACTIONREPORTHEADER;
	@FindBy(xpath = "//div[@ng-app='legacy-transaction-report']/div/div/div/div/div/button[contains(.,'GENERATE REPORT')]")
	public WebElement GENERATEREPORT;
	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement LEGACYCANCELBUTTON;
	@FindBy(xpath = "//input[@id='startDate']")
	public WebElement LEGACYSTARTDATE;
	@FindBy(xpath = "//input[@id='endDate']")
	public WebElement LEGACYENDDATE;
	@FindBy(xpath = "//button[contains(.,'Submit')]")
	public WebElement LEGACYSUBMITBTN;
	@FindBy(xpath = "//ul/li/a[contains(.,'Download')]")
	public WebElement LEGACYDOWNLOAD;
	@FindBy(xpath = "//ul/li/a[contains(.,'Delete')]")
	public WebElement LEGACYDELETE;
	@FindBy(xpath = "//h3[contains(text(),'This Customere has no legacy data to generate reports')]")
	public WebElement CUSTOMEREHASNOLEGACYDATA;

	@FindBy(xpath = "//input[@value='dateRange' and @type='radio']")
	public WebElement ExportDataPostedFrom;

	@FindBy(xpath = "//input[@id='startDate']")
	public WebElement StartDate;

	@FindBy(xpath = "//input[@id='endDate']")
	public WebElement EndDate;

	@FindBy(xpath = "//button[contains(.,'Generate File') and @disabled='disabled']")
	public WebElement GenerateFileDisabled;

	@FindBy(xpath = "//div[contains(text(),'Date range must be 45 days or less.')]")
	public WebElement DaterangeValidationMsg;

	@FindBy(xpath = "//a[contains(.,'Custom Transaction Detail Report- US and Canada')]")
	public WebElement CustomTransactionLink;

	@FindBy(xpath = "//p[contains(.,'View details about transactions for a company and date range in a format of your choosing.')]")
	public WebElement CustomTransactionsDetialLabel;

	@FindBy(xpath = "//h1[contains(.,'Custom Transaction Detail Report')]")
	public WebElement CustomTransactionDetailsLandingPageLabel;

	@FindBy(xpath = "//label[contains(.,'Select a Company')]")
	public WebElement SelectCompanyLabelinLandingPage;

	@FindBy(xpath = "//label[contains(.,'Company Status')]")
	public WebElement CompanyStatuslabel;

	@FindBy(xpath = "//button[contains(.,'Create new Transaction Detail report')]")
	public WebElement Createnewcustomtransactionreport;

	@FindBy(xpath = "//h1[contains(.,'Transaction Report Request')]")
	public WebElement CustomTransactionDetailReportRequestlabel;

	@FindBy(xpath = "//h3[contains(.,'This Customer has no committed Companies')]")
	public WebElement CustomerhasnocommittedCompanies;

	@FindBy(xpath = "//select[@id='company']")
	public WebElement CompanyDropdown;

	@FindBy(xpath = "//select[@id='jurisdiction']")
	public WebElement StateDropdown;

	@FindBy(xpath = "//input[@id='startDate']")
	public WebElement StartDateTextBox;

	@FindBy(xpath = "//input[@id='endDate']")
	public WebElement EndDateTextBox;

	@FindBy(xpath = "//a[contains(.,'+ New Custom Transaction Detail Report Template')]")
	public WebElement NewCustomTransactionDetailReportTemplatelnk;

	@FindBy(xpath = "//h1[contains(.,'NEW CUSTOM TRANSACTION DETAIL REPORT TEMPLATE')]")
	public WebElement NEWCUSTOMTRANSACTIONDETAILREPORTTEMPLATE;

	@FindBy(xpath = "//div[contains(.,'Template Name')]/input")
	public WebElement TemplatenameInputBox;

	@FindBy(xpath = "//a[contains(.,'Back')]")
	public WebElement Backbutton;

	@FindBy(xpath = "//button[contains(.,'Add Field')]")
	public WebElement AddField;

	@FindBy(xpath = "//button[contains(.,'Add All Fields')]")
	public WebElement AddAllFields;

	@FindBy(xpath = "//button[contains(.,'Remove All Fields')]")
	public WebElement RemoveAllFields;

	@FindBy(xpath = "//button[contains(.,'Save') and @disabled='disabled']")
	public WebElement Save;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	public WebElement Cancel;

	@FindBy(xpath = "//span[contains(.,'Field')]")
	public WebElement Field;

	@FindBy(xpath = "//span[contains(.,'Column')]")
	public WebElement Column;

	@FindBy(xpath = "//span[contains(.,'Actions')]")
	public WebElement Action;

	@FindBy(xpath = "//div[contains(@class,'ui-grid-row ng-scope')]//div[contains(@class,'ng-isolate-scope')]")
	public WebElement DefaultRowOne;

	@FindBys({

			@FindBy(xpath = "//div[contains(@class,'ui-grid-row ng-scope')]//div[contains(@class,'ng-isolate-scope')]") })
	public List<WebElement> VisibleRows;

	@FindBy(xpath = "//img[@alt='Add ']")
	public WebElement UpArrow;

	@FindBy(xpath = "//img[@alt='Down']")
	public WebElement DownArrow;

	@FindBy(xpath = "//img[@alt='Remove']")
	public WebElement RemoveIcon;

	@FindBy(xpath = "//div[2]/div[1]/div[1]/div[1]/div[2]/div[1]")
	public WebElement FirstRow;

	@FindBy(xpath = "//div[2]/div[1]/div[2]/div[1]/div[2]/div[1]")
	public WebElement SecondRow;

	@FindBy(xpath = "//div[2]/div[1]/div[3]/div[1]/div[2]/div[1]")
	public WebElement ThirdRow;

	@FindBy(xpath = "//div[2]/div[1]/div[4]/div[1]/div[2]/div[1]")
	public WebElement FourthRow;

	@FindBy(xpath = "//div[2]/div[1]/div[5]/div[1]/div[2]/div[1]")
	public WebElement FifthRow;

	@FindBy(xpath = "//div[contains(@class,'ui-grid-cell-contents ng-binding ng-scope ui-grid-cell-focus')]")
	public WebElement FirstRowclick;

	@FindBy(xpath = "//div[contains(.,'Admin Destination City')]")
	public WebElement SelectItemfromFirstRow;

	@FindBy(xpath = "//h3[contains(.,'Are you sure you want to cancel the changes?')]")
	public WebElement ConfirmationMessage;

	@FindBy(xpath = "//strong[contains(.,'No')]")
	public WebElement NoButton;

	@FindBy(xpath = "//a[contains(.,'Yes')]")
	public WebElement YesButton;

	@FindBy(xpath = "//button[contains(.,'Remove All Fields') and @disabled='disabled']")
	public WebElement RemoveAllFieldsDisabledByDefault;

	@FindBy(xpath = "//h3[contains(.,'Are you sure you want to remove all the fields?')]")
	public WebElement RemoveallFieldsConfirmationMessage;

	@FindBy(xpath = "//div[2]/div[1]/div[1]/div[1]/div[3]/div[1]")
	public WebElement ColumnName;

	@FindBy(xpath = "//div[3]/input")
	public WebElement ColumnName1;

	@FindBy(xpath = "//div[contains(.,'Fields can not be empty.') and @class='alert alert-error']")
	public WebElement FieldsCannotemptyalert;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement SaveBtn;

	@FindBy(xpath = "//h1[contains(.,'EDIT CUSTOM TRANSACTION DETAIL REPORT TEMPLATE')]")
	public WebElement EditCustomTransactionDetailReportTemplate;

	@FindBy(xpath = "//button[contains(.,'Delete Template')]")
	public WebElement DeleteTemplateButton;

	@FindBy(xpath = "//h3[contains(.,'Are you sure you want to delete the template?')]")
	public WebElement DeleteTemplateLabel;

	@FindBy(xpath = "//a[contains(.,'Yes')]")
	public WebElement DeleteTemplateYesConfirm;

	@FindBy(xpath = "//strong[contains(.,'No')]")
	public WebElement DeleteTemplateNoConfirm;

	@FindBy(xpath = "//h2[contains(.,'Custom Reports')]")
	public WebElement CustomReportlabel;

	@FindBy(xpath = "//h2[contains(.,'Standard Reports')]")
	public WebElement StandardReportlabel;

	@FindBy(xpath = "//i[contains(.,'Report generation is in progress ...')]")
	public WebElement ReportProgress;

	@FindBy(xpath = "//span[contains(.,'Generation Date')]")
	public WebElement generationdate;

	@FindBy(xpath = "//span[contains(.,'Company Name')]")
	public WebElement companyname;

	@FindBy(xpath = "//span[contains(.,'Report Type')]")
	public WebElement reporttype;

	@FindBy(xpath = "//span[contains(.,'Reporting Period Start Date')]")
	public WebElement reportingstartdate;

	@FindBy(xpath = "//span[contains(.,'Reporting Period End Date')]")
	public WebElement reportingenddate;

	@FindBy(xpath = "//span[contains(.,'State/Territory')]")
	public WebElement stateteritory;

	@FindBy(xpath = "//div[5]/div/div[1]/div[2]/div/div[1]/div/div[2]/div[2]/form/select")
	public WebElement firstdropdown;

	@FindBy(xpath = "//div[5]/div/div[1]/div[2]/div/div[2]/div/div[2]/div[2]/form/select")
	public WebElement seconddropdown;

	@FindBy(xpath = "//div[5]/div/div[1]/div[2]/div/div[3]/div/div[2]/div[2]/form/select")
	public WebElement thirddropdown;

	@FindBy(xpath = "//div[5]/div/div[1]/div[2]/div/div[4]/div/div[2]/div[2]/form/select")
	public WebElement fourthdropdown;

	@FindBy(xpath = "//div[5]/div/div[1]/div[2]/div/div[5]/div/div[2]/div[2]/form/select")
	public WebElement fifthdropdown;

	@FindBy(xpath = "//div[5]/div/div[1]/div[2]/div/div[1]/div/div[3]/div")
	public WebElement firstrowtextbox;

	@FindBy(xpath = "//div[5]/div/div[1]/div[2]/div/div[2]/div/div[3]/div")
	public WebElement secondrowtextbox;

	@FindBy(xpath="//input[@id='company-status']")
	public WebElement TAXPAYERSTATUS;
	
	@FindBy(xpath="//button[contains(.,'Ok')]")
	public WebElement OkBtn;
	
	@FindBy(xpath="//p[contains(.,'The line items returned exceeds 100000 limit.  Please run the Large Volume Transaction Detail Report instead.')]")
	public WebElement lineitemexceedtransactiondetail;
	
}
