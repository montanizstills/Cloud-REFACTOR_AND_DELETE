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

public class Documentcenter {
	public final String DOWNLOADCHECK_XPATH = "//input[@id='downloadOnly']";

	@FindBy(xpath = "//ul[@class='nav nav-list']/li/a/span[contains(text(),'Document Center')]")
	public WebElement DOCUMENTCENTER;

	@FindBy(xpath = "//div[@class='span4']/select")
	public WebElement CLIENTSELECT;

	@FindBy(xpath = "//div[@class='span2']/select")
	public WebElement FILETYPESELECT;

	@FindBy(xpath = "//div[@class='row-fluid']/div/label[contains(.,'Period')]/../select")
	public WebElement PERIODSELECT;

	@FindBy(xpath = "//div[@class='ng-scope']/div[3]/div/div/div/fileupload[@class='ng-isolate-scope']/div[@class='row-fluid']/div/label[contains(.,'Browse')]")
	public WebElement ADMINBROWSE;

	@FindBy(xpath = "//div[3]/div/div/fileupload/div[4]/div/label[contains(.,'Browse')]")
	public WebElement CLIENTBROWSE;

	@FindBy(xpath = "//div[@id='notices']/div/fileupload/div[@class='row-fluid']/div[1]/label[contains(text(),'Browse')]")
	public WebElement NOTICESBROWSE;

	@FindBy(xpath = "//div[@class='ng-scope']/div/div/div/div/fileupload[@class='ng-isolate-scope']/div[@ng-hide='hideUploadButton']/button[contains(text(),'Upload File')]")
	public WebElement ADMINUPLOAD;

	@FindBy(xpath = "//a[@id='confirm-upload']")
	public WebElement UPLOADOK;

	@FindBy(xpath = "//div[@class='ng-scope']/div[3]/div/div/div/fileupload[@class='ng-isolate-scope']/div[@class='alert alert-success']")
	public WebElement ADMINUPLOADSUCCESS;

	@FindBy(xpath = "//div[@id='notices']/div/fileupload[@class='ng-isolate-scope']/div/button[contains(text(),'Upload File')]")
	public WebElement NOTICESUPLOAD;

	@FindBy(xpath = "//div[4]/div/div/div/fileupload/div/button[contains(text(),'Upload File')]")
	public WebElement CLIENTUPLOAD;

	@FindBy(xpath = "//div[4]/div/div/div/fileupload/div[@class='alert alert-success']")
	public WebElement CLIENTUPLOADSUCCESS;

	@FindBy(xpath = "//div[@class='ng-scope']/div[3]/div/div/table/tbody/tr[1]/td/a[contains(text(),'Download')]")
	public WebElement ADMINDOWNLOAD;

	@FindBy(xpath = "//div[@class='ng-scope']/div[4]/div/div/table/tbody/tr[1]/td/a[contains(text(),'Download')]")
	public WebElement CLIENTDOWNLOAD;

	@FindBy(xpath = "//div[@class='ng-scope']/div[3]/div/div/table/tbody/tr[1]/td[1]")
	public WebElement ADMINFILENAME;

	@FindBy(xpath = "//div[@class='ng-scope']/div[3]/div/div/table/tbody/tr[1]/td[2]")
	public WebElement ADMINJURISNAME;

	@FindBy(xpath = "//div[@class='ng-scope']/div[3]/div/div/table/tbody/tr[1]/td[3]")
	public WebElement ADMINFILINGPERIDNAME;

	@FindBy(xpath = "//div[@id='notices']/div/table/tbody/tr[1]/td[1]")
	public WebElement NOTICESFILENAME;

	@FindBy(xpath = "//div[@class='ng-scope']/div[3]/div/div/table/tbody/tr[1]/td[2]")
	public WebElement ADMINDATETIME;

	@FindBy(xpath = "//div[@class='alert alert-error ng-binding']")
	public WebElement UPLOADERROR;
	
	@FindBy(xpath="//div[@class='alert alert-error ng-binding']")
	public WebElement AlertMessage;

	@FindBy(xpath = "//input[@id='downloadOnly']")
	public WebElement DOWNLOADCHECK;

	@FindBy(xpath = "//div[@class='section header-block ng-scope']/h1[text()='Document Management']")
	public WebElement DOCUMENTMNGMTTXT;

	@FindBy(xpath = "//div[3]/div/div/div/fileupload/div/button[contains(text(),'Upload File')]")
	public WebElement CLIENTNOTICEUPLOAD;

	@FindBy(xpath = "//div[@class='section']/ul/li/a[text()='Monthly']")
	public WebElement CLIENTMONTHLY;

	@FindBy(xpath = "//div[@class='section']/ul/li/a[text()='Onboarding']")
	public WebElement CLIENTONBOARDING;

	@FindBy(xpath = "//div[@class='section']/ul/li/a[text()='Notices']")
	public WebElement CLIENTNOTICES;

	@FindBy(xpath = "//div[@id='notices']/div[@class='well']/fileupload/div/button[contains(text(),'Upload File')]")
	public WebElement NoticeUpload;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	public WebElement CLIENTNOTICESALERT;

	@FindBy(xpath = "//div[@class='accordion-heading']/a[contains(.,'ONBOARDING')]/i")
	public WebElement ONBOARDINGDOCUMENTATION;

	@FindBy(xpath = "//div[@class='accordion-heading']/a[contains(.,'ONBOARDING')]/i[@class='icon-plus-large']")
	public WebElement ONBOARDDOCPLUS;

	@FindBy(xpath = "//div[@class='accordion-heading']/a[contains(text(),'POA')]/i")
	public WebElement POA;

	@FindBy(xpath = "//div[@class='accordion-heading']/a[contains(text(),'POA')]/i[@class='icon-plus-large']")
	public WebElement POAPLUS;

	@FindBy(xpath = "//div[@class='ng-scope']/div/form/div/div[3]/div/div/table/tbody/tr[1]/td[1]")
	public WebElement CLIENTFILENAME;

	@FindBy(xpath = "//div[@class='ng-scope']/div[4]/div/div/table/tbody/tr[1]/td[2]")
	public WebElement CLIENTDATETIME;

	@FindBy(xpath = "//div[@class='row-fluid']/div/form/select")
	public WebElement PARENTFILESELECT;

	@FindBy(xpath = "//div[@id='onboarding']/p[contains(.,'The onboarding section provides details regarding current tax return')]/../p[contains(.,'filings, prepayments and power of attorney documents.')]")
	public WebElement ONBOARDTXT;

	@FindBy(xpath = "//div[@id='Div1']/p/inline-help/span[text()='Returns that have been filed on your behalf will be displayed in this section by filing period.  You can review, download and print for your records.']")
	public WebElement MONTHTXT;

	@FindBy(xpath = "//div[@class='well']/inline-help/span[contains(text(),'Notices that you have sent to TaxCentral from a jurisdiction will be on this page.  You can review, download and print for your records.')]")
	public WebElement NOTICETXT;

	@FindBy(xpath = "//fileupload/div[@class='row-fluid']/div/label[@for='jurisdiction']/../select")
	public WebElement JURISDICTIONSELECT;

	@FindBy(xpath = "//fileupload/div[@class='row-fluid']/div/label[@for='filing-period']/../select")
	public WebElement FILING_PERIOD;

	@FindBy(xpath = "//fileupload/div[@class='row-fluid']/div/label[@for='due-date']/../input")
	public WebElement DUEDATE;

	@FindBy(xpath = "//h1")
	public WebElement DOCUMENTCENTERLABEL;

	@FindBy(xpath = "//ul[@id='documentTabs']/li[@class='active']/a")
	public WebElement ONBOARDINGLABEL;

	@FindBy(xpath = "//ul[@id='documentTabs']/li[2][@class='']/a")
	public WebElement MONTHLYLABEL;

	@FindBy(xpath = "//ul[@id='documentTabs']/li[3][@class='']/a")
	public WebElement NOTICESLABEL;

	@FindBy(xpath = "//form/div/div[1]/div/inline-help[1]/span")
	public WebElement INLINEHELPLABEL1;

	@FindBy(xpath = "//form/div/div[1]/div/div/div[1]/div/div[1]/a")
	public WebElement ONBOARDINGDOCLABEL;

	@FindBy(xpath = "//form/div/div[1]/div/div/div[2]/div/div[1]/a")
	public WebElement POALABEL;

	@FindBy(xpath = "//form/div/div[1]/div/div/div[3]/div/div[1]/a")
	public WebElement RETURNSLABEL;

	@FindBy(xpath = "//div/div[2]/div/div/fileupload/div[4]/div[1]/a")
	public WebElement FILEUPLOAD;

	@FindBy(xpath = "//form/div/div[1]/div/h2")
	public WebElement FILESUPLOADLABEL;

	@FindBy(xpath = "//form/div/div[1]/div/inline-help[2]/span")
	public WebElement INLINEHELPLABEL2;

	@FindBy(xpath = "//form/div/div[1]/div/table")
	public WebElement UPLOADEDFILESTABLE;

	@FindBy(xpath = "//form/div/div[1]/div/div/div[3]/div/div[1]/a/i")
	public WebElement RETURNSPLUS;

	@FindBy(xpath = "//div[@class='well']")
	public WebElement MONTHLYTABLE;

	@FindBy(xpath = "//form/div/div[3]/div/div/h2")
	public WebElement NOTICEUPLOAD;

	@FindBy(xpath = "//form/div/div[3]/div/div/inline-help/span")
	public WebElement INLINEHELP3;

	@FindBy(xpath = "//fileupload/div[@class='row-fluid']/div/label[@for='jurisdiction']/../select/option")
	public WebElement JURISDICTIONITEMS;

	@FindBys({

			@FindBy(xpath = "//fileupload/div[@class='row-fluid']/div/label[@for='jurisdiction']/../select/option"), })
	public List<WebElement> JURISDICTIONITEM;

	@FindBy(xpath = "//div[@class='accordion-heading']/a[contains(.,'ONBOARDING')]/i")
	public WebElement BANKING;

	@FindBy(xpath = "//div[@class='accordion-heading']/a[contains(.,'ONBOARDING')]/i[@class='icon-plus-large']")
	public WebElement BANKINGPLUS;

	@FindBys({ @FindBy(xpath = "//table/tbody/tr[@class='ng-scope']"),
	})
	public List<WebElement> ROWCOLLECTION;

	@FindBy(xpath = "//div[@class='pagination pagination-right ng-scope']/ul/li[@class='disabled']/a[contains(.,'»')]")
	public WebElement PAGINATION;

	@FindBy(xpath = "//div[@class='pagination pagination-right ng-scope']/ul/li[@class='ng-scope active']/a")
	public WebElement PAGINATIONCOUNT;

	// div[@class='pagination pagination-right ng-scope']/ul/li[@class='ng-scope
	// active']/a
	@FindBy(xpath = "//div[@class='accordion-heading']/a[contains(text(),'Onboarding Documentation')]/../../div/div/table/thead/tr/th[@sort-header='fileName']")
	public WebElement ONBOARDINGDOC;

	@FindBy(xpath = "//div[@class='accordion-heading']/a[contains(.,'ONBOARDING')]/../../div/div/table/thead/tr/th/span[2]")
	public WebElement ONBOARDING;

	@FindBy(xpath = "//div[@class='accordion-heading']/a[contains(text(),'POA')]/../../div/div/table/thead/tr/th[1]")
	public WebElement POASORT;

	@FindBy(xpath = "//div/div[@id='onboarding']/div/h2[text()='Uploaded Files']/../../div/table/thead/tr/th[1]")
	public WebElement ONBAORDINGUPLOAD;

	@FindBy(xpath = "//form/div/div[2]/div/div/h2[text()='Uploaded Files']/../../div/table/thead/tr/th[1]")
	public WebElement MONTHLYUPLOAD;

	@FindBy(xpath = "//form/div/div[3]/div/div/h2[text()='Uploaded Files']/../../div/table/thead/tr/th[1]")
	public WebElement NOTICEFILESUPLOAD;

	@FindBy(xpath = "//div/div/div/pagination-extension/span/select")
	public WebElement PAGINATIONTXT;

	@FindBy(xpath = "//div[@id='notices']/div/fileupload/div[contains(.,'Your file has been successfully uploaded!')]")
	public WebElement NOTICESUPLOADSUC;

	@FindBy(xpath = "//div[@id='notices']//label[contains(text(),'Browse')]")
	public WebElement NoticeBrowseButton;

	@FindBy(xpath = "//a[contains(.,'Banking')]")
	public WebElement BANKINGLINK;

	@FindBy(xpath = "//div[contains(.,'Account Type*')]/select")
	public WebElement ACCOUNTTYPE;

	@FindBy(xpath = "//div/label[contains(.,'Routing Number*')]/../input")
	public WebElement ROUTINGNUM;

	@FindBy(xpath = "//div/label[contains(.,'Account Number*')]/../input")
	public WebElement ACCOUNTNUM;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement SAVEBANKBUTTON;

	@FindBy(xpath = "//h3[contains(.,'Confirm Password')]")
	public WebElement CHNAGEPWD;

	@FindBy(xpath = "//input[@id='billing-information-password']")
	public WebElement PASSWORDINPUT;

	@FindBy(xpath = "//button[contains(.,'Add Banking Information')]")
	public WebElement EDITBANKBUTTON;

	@FindBy(xpath = "//h2[contains(.,'Premium Banking Information')]")
	public WebElement PremiumBanking;

	@FindBy(xpath = "//div/strong[contains(.,'Country:')]/../../div[3]")
	public WebElement Countryval;

	@FindBy(xpath = "//div/strong[contains(.,'Account Type:')]/../../div[3]")
	public WebElement AdminActype;

	@FindBy(xpath = "//div/strong[contains(.,'Routing Number:')]/../../div[3]")
	public WebElement Adminrnum;

	@FindBy(xpath = "//span/strong[contains(.,'Account Number:')]/../../div[2]")
	public WebElement AdminAccnum;

	@FindBy(xpath = "//select[@id='country']")
	public WebElement Country;

	@FindBy(xpath = "//button[contains(.,'Premium Payment Info')]")
	public WebElement PREMIUMBANKINGINFO;

	@FindBy(xpath = "//h2[contains(.,'Premium Payment Information')]")
	public WebElement PAYMENTINFOHEADER;

	@FindBy(xpath = "//button[contains(.,'Add Bank Account')]")
	public WebElement ADDBANKButton;

	@FindBy(xpath = "//a[contains(.,'Save Bank Account Information')]")
	public WebElement SAVEBANKBUTTON2;

	@FindBy(xpath = "//h3[contains(.,'Add New  Account')]")
	public WebElement ADDNEWACCOUNT;

	@FindBy(xpath = "//a[contains(.,'Ok')]")
	public WebElement OKbutton;

	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement CancelLink;

	@FindBy(xpath = "//button[contains(.,'Delete')]")
	public WebElement Delete;

	@FindBy(xpath = "//button[contains(.,'Delete Banking Information')]")
	public WebElement DeleteBankinginfo;

	@FindBy(xpath = "//div/h3[contains(.,'Edit')]")
	public WebElement Editaccouttitle;

	@FindBy(xpath = "//button[contains(.,'Edit Banking Information')]")
	public WebElement Editbankinginfo;

	@FindBy(xpath = "//div/h3[contains(.,'Are you sure you want to permanently delete this file?')]/../../div/p/a[contains(text(),'Delete')]")
	public WebElement DELETE;

	@FindBy(xpath = "//span[@class='pagination-index ng-scope']/select")
	public WebElement PAGINATIOS;

	@FindBy(xpath = "//fileupload/div[contains(.,'Your file has been successfully uploaded!')]")
	public WebElement FILEUPLOADSUCCESS;

	@FindBy(xpath = "//div[@id='LoadScreenDiv2']/spin-icon")
	public WebElement SPINICON;
	
	@FindBy(xpath="//div[@id='notices']//div[contains(@class,'well')]//div[1]//select")
	public WebElement Jurisdictions;
	
	@FindBy(xpath="//div[@id='notices']//select[@id='filing-period']")
	public WebElement FilingPeriod;
	
	@FindBy(xpath="//div[@id='notices']//input[@id='due-date']")
	public WebElement DueDate;
	
	@FindBy(xpath="//div[@id='notices']//div[contains(@class,'well')]//fileupload[contains(@class,'ng-isolate-scope')]//div[contains(@class,'row-fluid')]//div//input[@id='comments']")
	public WebElement Comments;
	
	@FindBy(xpath="//span[contains(.,'Document Center')]")
	public WebElement AdminDocumentCenter;
	
	@FindBy(xpath="//label[contains(.,'Client')]")
	public WebElement ClientLabel;
	
	@FindBy(xpath="//label[contains(.,'Client')]/../select")
	public WebElement ClientDD;
	
	@FindBy(xpath="//label[contains(.,'File Type')]/../select")
	public WebElement FileTypeDD;

}
