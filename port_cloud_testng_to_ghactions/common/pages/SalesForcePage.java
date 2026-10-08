package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesForcePage {

	public final String CASEORIGIN_XPATH = "//select[@id='cas11']";
	@FindBy(xpath = "//input[@id='username']")

	public WebElement USERNAME;
	@FindBy(xpath = "//input[@id='password']")

	public WebElement PASSWORD;
	@FindBy(xpath = "//input[@id='Login']")

	public WebElement LOGIN;
	@FindBy(xpath = "//li/a[contains(text(),'Contacts')]")

	public WebElement CONTACTS;
	@FindBy(xpath = "//li/a[contains(text(),'Cases')]")

	public WebElement CASES;
	@FindBy(xpath = "//input[@name='go']")

	public WebElement GO;
	@FindBy(xpath = "//input[@id='phSearchInput']")

	public WebElement SEARCHINPUT;
	@FindBy(xpath = "//input[@id='phSearchButton']")

	public WebElement SEARCHBUTTON;
	@FindBy(xpath = "//td[text()='CPA BPO Referred']")

	public WebElement BPOREFERALCHKBOX;
	@FindBy(xpath = "//td[text()='CPA BPO Enrolled']/../td/div/img[@title='Checked']")

	public WebElement BPOENROLLCHKED;

	@FindBy(xpath = "//div[@id='searchResultsWarningMessageBox']")

	public WebElement NOMATCH;
	@FindBy(xpath = "//tbody/tr/td[@class='actionColumn']/a")

	public WebElement EDIT;
	@FindBy(xpath = "//tbody/tr[2]/th/a")

	public WebElement FIRSTNAME;
	@FindBy(xpath = "//input[@id='name_lastcon2']")

	public WebElement LASTNAME;
	@FindBy(xpath = "//select[@title='Agreements Signed - Chosen']/option[text()='Vertex SMB Agreement']")

	public WebElement AGREESIGNED;
	@FindBy(xpath = "//input[@name='save']")

	public WebElement SAVE;
	@FindBy(xpath = "//table/tbody/tr/td/a[contains(text(),'Requests Cancellation')]")

	public WebElement REQUESTSCANCEL;
	@FindBy(xpath = "//a[@title='Cases Tab']")

	public WebElement CASESTAB;
	@FindBy(xpath = "//select[@title='View:']")

	public WebElement VIEWSELECT;
	@FindBy(xpath = "//input[@title='Go!']")

	public WebElement GOBTN;
	@FindBy(xpath = "//a[contains(text(),'Create New View')]")

	public WebElement CREATENEW;
	@FindBy(xpath = "//li[@id='Account_Tab']/a[text()='Accounts']")

	public WebElement ACCOUNTS;
	
	@FindBy(xpath= "//div/one-app-nav-bar-item-root/a[contains(.,'Accounts')]")
	public WebElement SFACCOUNTS;
	
	@FindBy(xpath="//button[@aria-label='Search']")
	public WebElement SFSEARCH;
	
	@FindBy(xpath="//div/input[@placeholder='Search Accounts and more...']")
	public WebElement SFSEARCHFIELD;
	
	@FindBy(xpath="//a[contains(.,'Show All (20)')]")
	public WebElement SHOWALL;
	
	@FindBy(xpath="//a[contains(.,'TaxPayer Company Information')]")
	public WebElement TAXPAYERCOMPANYINFORMATION;
	
	@FindBy(xpath = "//td/input[@name='new']")

	public WebElement NEW;
	@FindBy(xpath = "//select[@id='p3']")

	public WebElement RECORDTYPE;
	@FindBy(xpath = "//select[@id='cas11']")

	public WebElement CASEORIGIN;
	@FindBy(xpath = "//select[@id='cas5']")

	public WebElement CASETYPE;
	@FindBy(xpath = "//select[@id='00N700000031cpm']")

	public WebElement CASESUBTYPE;
	@FindBy(xpath = "//td[@id='bottomButtonRow']/input[@title='Continue']")

	public WebElement CONTINUE;
	@FindBy(xpath = "//span[@class='lookupInput']/a/img[@alt='Contact Name Lookup (New Window)']")

	public WebElement CONTACTNAME_SEARCH;
	@FindBy(xpath = "//span[@class='lookupInput']/a/img[@alt='Asset Lookup (New Window)']")

	public WebElement ASSET_SEARCH;
	@FindBy(xpath = "//select[@name='col0']")

	public WebElement FILTERBY;
	@FindBy(xpath = "//select[@id='oper0']")

	public WebElement SELECT;
	@FindBy(xpath = "//input[@id='fval0']")

	public WebElement ACCOUNTNAME;
	@FindBy(xpath = "//span/label[contains(.,'Support Call Billable?')]/../../../td/input")

	public WebElement SUPPCALBILLABLE;
	@FindBy(xpath = "//table[@class='detailList']/tbody/tr/td[@class='dataCol']/input[@tabindex='7']")

	public WebElement NSFFEEBILLABLE;
	@FindBy(xpath = "//table[@class='detailList']/tbody/tr/td[@class='last dataCol']/input[@tabindex='8']")

	public WebElement WIREFEEBILLABLE;
	@FindBy(xpath = "//table[@class='detailList']/tbody/tr/td/span/label[contains(.,'Rollback Billable?')]/../../../td/input")

	public WebElement ROLLBACK_BILL;
	@FindBy(xpath = "//table/tbody/tr/td/span/label[contains(.,'Amended Return?')]/../../../td[@class='dataCol col02']/input")

	public WebElement AMENDEDRETURNBILL;
	@FindBy(xpath = "//table[@class='detailList']/tbody/tr/td/span/label[contains(.,'Amended Return Billable?')]/../../../td/input")

	public WebElement RETURNBILLABE;
	@FindBy(xpath = "//input[@id='00Nn0000000HGXN']")

	public WebElement SPECIALITYCONFIG_BILL;
	@FindBy(xpath = "//span[@class='dateFormat']")

	public WebElement UPLOADDATE;
	@FindBy(xpath = "//table/tbody/tr/td/span/label[contains(.,'Specialty Config Billable?')]/../../../td/input")

	public WebElement SPECIALITYCONFIGFEE;
	@FindBy(xpath = "//table[@class='detailList']/tbody/tr/td[@class='last dataCol']/input[@tabindex='7']")

	public WebElement NOTICEHANDLINGBILL;
	@FindBy(xpath = "//table[@class='detailList']/tbody/tr[1]/td/label[contains(.,'Subject')]/../../td[2]/div/input")

	public WebElement SUBJECT;
	@FindBy(xpath = "//table[@class='detailList']/tbody/tr/td/label[contains(.,'Description')]/../../td/textarea[@id='cas15']")

	public WebElement DESC;
	@FindBy(xpath = "//table[@class='detailList']/tbody/tr/td/label[contains(.,'Description')]/../../td/div/textarea[@id='cas15']")

	public WebElement DESCSTG;
	@FindBy(xpath = "//div[@class='mainContent']/div/ul/li/a/span[text()='Details']")

	public WebElement DETAILSTAB;
	@FindBy(xpath = "//div[@class='pbHeader']/table/tbody/tr/td/input[@value='Close Case']")

	public WebElement CLOSECASE;
	@FindBy(xpath = "//select[@id='cas7']")

	public WebElement STATUS;
	@FindBy(xpath = "//select[@id='cas6']")

	public WebElement CASEREASON;
	@FindBy(xpath = "//select[@id='00N700000031cph']")

	public WebElement CASERESOLUTION;
	@FindBy(xpath = "//table[@class='multiSelectPicklistTable']/tbody/tr/td/span/select/option[text()='NSF']")

	public WebElement NSF;
	@FindBy(xpath = "//table[@class='multiSelectPicklistTable']/tbody/tr/td/span/select/option[text()='Wire Transfer']")

	public WebElement WIRETRANSFER;
	@FindBy(xpath = "//div[@class='rightArrowIcon']")

	public WebElement SELECTCLICK;
	@FindBy(xpath = "//a[@title='Add']")

	public WebElement ADD;
	@FindBy(xpath = "//table/tbody/tr/td/span/label[contains(.,'Onboarding Docs Uploaded Date')]/../../../td/div/span/span/a")

	public WebElement ONBOARD;
	@FindBy(xpath = "//table[@class='detailList']/tbody/tr/td/label[contains(.,'Resolution Summary')]/../../td/textarea[@id='00Nn0000000HGWx']")

	public WebElement RESOLUTIONSUMM;
	@FindBy(xpath = "//input[@id='lksrch']")

	public WebElement FIRSTNAME_SEARCH;
	@FindBy(xpath = "//table[@class='list']/tbody/tr[2]/th/a")

	public WebElement FIRSTROW;
	@FindBy(xpath = "//input[@id='acc2']")

	public WebElement ACCNAME;
	@FindBy(xpath = "//select[@id='acc6']")

	public WebElement TYPE;
	@FindBy(xpath = "//select[@id='00N700000031X5m']")

	public WebElement ACCSTATUS;
	@FindBy(xpath = "//select[@id='AccountSource']")

	public WebElement ACCSOURCE;
	@FindBy(xpath = "//select[@id='00N700000031X5r']")

	public WebElement INACTIVERESON;
	@FindBy(xpath = "//select[@id='00N700000031XTA']")

	public WebElement CONTRACTTYPE;
	@FindBy(xpath = "//select[@id='fcf']")

	public WebElement VIEW;
	@FindBy(xpath = "//select[@id='00N700000031X5r']")

	public WebElement INACTIVEREASON;
	@FindBy(xpath = "//td[@id='bottomButtonRow']/input[@name='save']")

	public WebElement CLOSESAVE;
	@FindBy(xpath = "//input[@value='Search Again']")

	public WebElement SEARCHAGAIN;
	@FindBy(xpath = "//input[@value='Log Support Call']")

	public WebElement LOGSUPPORTCALL;
	@FindBy(xpath = "//div/a/img[@title='Subject Combo (New Window)']/../../a[@value='Enter Activity Subject Here']")

	public WebElement SUBJECTCOMBO;
	@FindBy(xpath = "//ul/li/a[text()='Call']")

	public WebElement FIRSTLINK;
	@FindBy(xpath = "//div[12]/table/tbody/tr[2]/td[2]/div/a/img")

	public WebElement SUBJECTCOMBO2;
	@FindBy(xpath = "//select[@id='tsk10_fu']")

	public WebElement BILLTYPE;
	@FindBy(xpath = "//select[@id='tsk12_fu']")

	public WebElement STATUSTYPE;
	@FindBy(xpath = "//td[@id='bottomButtonRow']/input[@name='save']")

	public WebElement SAVELOG;
	@FindBy(xpath = "//img[@title='Name Lookup (New Window)']")

	public WebElement NAMEWINDOW;
	@FindBy(xpath = "//input[@id='mw_picker_search_prefix']")

	public WebElement SEARCHBOX;
	@FindBy(xpath = "//input[@id='mw_picker_search_btn']")

	public WebElement FINDBTN;
	@FindBy(xpath = "//div[@class='found_results']/ul/li")

	public WebElement AVLABL_LIST;
	@FindBy(xpath = "//input[@name='saveBtn']")

	public WebElement DONEBTN;
	@FindBy(xpath = "//input[@id='cas4']")

	public WebElement ACCOUNNAME;
	@FindBy(xpath = "//input[@name='edit']")

	public WebElement CASEEDIT;
	@FindBy(xpath = "//p[contains(.,'New Tickets')]/..//div")

	public WebElement NEWTICKETS;
	@FindBy(xpath = "//p[contains(.,'In Progress Tickets')]/..//div")

	public WebElement INPROGRESSTICKETS;
	@FindBy(xpath = "//p[contains(.,'Closed Tickets')]/..//div")

	public WebElement CLOSEDTICKETS;

	@FindBy(xpath = "//div/span[@id='userNavLabel']") // div/span[@id='userNavLabel']

	public WebElement SALESFORCEUSERICON;

	@FindBy(xpath = "//div/a[@title='Logout']")

	public WebElement SALESFORCELOGOUT;

	@FindBy(xpath = "//select[@id='fcf']")

	public WebElement VIEWDD;

	@FindBy(xpath = "//div[@id='Case_body']/div/div/div/span/a")

	public WebElement CLIENTINFO;

	@FindBy(xpath = "//div[@id='logo_wrapper']")

	public WebElement SALESFORCEIMAGE;

	@FindBy(xpath = "//input[@title='Go!']")

	public WebElement GO_MYOPENCASES;

	@FindBy(xpath = "//table/thead/tr/td/div[@title='Date/Time Opened']")

	public WebElement DATEOPENED;

	@FindBy(xpath = "//div[@id='errorDiv_ep']")

	public WebElement ERROR_INVALID;

	@FindBy(xpath = "//h2[contains(.,'Edit Records Per Page Setting')]/../a[@title='Close']")

	public WebElement EDITRECORDPAGE;

	@FindBy(xpath = "//span[contains(.,'Case Number')]")

	public WebElement CASECREATED;

	@FindBy(xpath = "//input[@name='save_close']")

	public WebElement SAVEANDCLOSE;

	@FindBy(xpath = "//select[@id='00B70000007Lv5R_listSelect']")

	public WebElement MYOPENCASESDD;

	@FindBy(xpath = "//select[@id='fcf']")

	public WebElement MYOPENSDD;

	@FindBy(xpath = "//select[@title='View:']")

	public WebElement MYOPENVIEWDD;

	@FindBy(xpath = "//h2[contains(.,'Case Detail')]/../../td/input[@title='Edit']")

	public WebElement CLOSEEDIT;

	@FindBy(xpath = "//span[contains(.,'Details')]")

	public WebElement DETAILS;

	@FindBy(xpath = "//h2[contains(.,'Case Detail')]/../../td/input[@title='Close Case']")

	public WebElement CLOSECASEE;

	@FindBy(xpath = "//h2[contains(.,'Case Edit')]/../../td/input[@name='save']")

	public WebElement SAVECLOSE;

	@FindBy(xpath = "//div[3]/table/tbody/tr[4]/td[2]/input")

	public WebElement CompanyCode;

	@FindBy(xpath = "//div[3]/table/tbody/tr[6]/td[4]/input")

	public WebElement FilingPeriod;

	@FindBy(xpath = "//div[9]/table/tbody/tr[4]/td[4]/input")

	public WebElement QTYReturnsComplete;

	@FindBy(xpath = "//td/label[contains(.,'Referral No Console')]/../../td[2]/input")

	public WebElement RefNoConsole;

	@FindBy(xpath = "//td/label[contains(.,'BPO with Console')]/../../td[4]/input")

	public WebElement BPOConsole;

	@FindBy(xpath = "//td/label[contains(.,'Referral with Console')]/../../td/input")

	public WebElement RefConsole;

	@FindBy(xpath = "//td/label[contains(.,'Type of Partner')]/../../td/span/select")

	public WebElement Typeofpartner;

	@FindBy(xpath = "	//td/label[contains(.,'Master Partner')]/../../td/span/select")

	public WebElement Masterpartner;

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@title='Save']")

	public WebElement TOPROWNEW;

	@FindBy(xpath = "//td/label[contains(.,'US or Canadian Payment?')]/../../td/span/select")

	public WebElement PaymentCountry;

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@value='Delete']")

	public WebElement DELETE;

	@FindBy(xpath = "//input[@id='reminder_select_check']")

	public WebElement REMINDER;

	@FindBy(xpath = "//input[@id='dismiss_all']")

	public WebElement DISMISS_ALL;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Invoice']/../td/div/img")

	public WebElement InvoiceStatus;

	@FindBy(linkText = "Reports")

	public WebElement SFDCReport;

	@FindBy(xpath = "//span[text()='Cloud Customer Billing Invoice Report']")

	public WebElement CCBIReport;

	@FindBy(id = "runMuttonButton")

	public WebElement RunMenuBtn;

	@FindBy(name = "csvsetup")

	public WebElement exportDetails;

	@FindBy(id = "xf")

	public WebElement Exportfilefromat;

	@FindBy(name = "export")

	public WebElement ExportBtn;

	@FindBy(xpath = "//div[@id='runMuttonMenu']/a[text()='Run Report Now']")

	public WebElement RunReportNow;

	@FindBy(xpath = "//label[contains(.,'Master Partner')]/../../td/span/select")

	public WebElement masterPartner;
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement SFUSERNAME;
	
	@FindBy(xpath="//input[@id='password']")
	public WebElement SFPASSWORD;
	
	@FindBy(xpath="//input[@id='Login']")
	public WebElement SFLOGIN;
	
	@FindBy(xpath="//div[@class='pbSubsection']/table/tbody/tr/td[contains(.,'Master Partner')]/../td/div[@id='00N1N00000Q2emp_ileinner']")
	public WebElement MASTERPARTNER;
	
	@FindBy(xpath="//div[@class='pbSubsection']/table/tbody/tr/td[contains(.,'VAR')]/../td/div[@id='CF00N1N00000Q2enS_ileinner']")
	public WebElement VAR;
	
	@FindBy(xpath="//div[@id='userNav-arrow']")
	public WebElement LOGOUTDROPDOWN;
	
	@FindBy(xpath="//a[contains(.,'Logout')]")
	public WebElement LOGOUT;

}
