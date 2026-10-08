package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageConfiguration {

	@FindBy(xpath = "//a[@title='Manage Returns Config']")
	public WebElement MANAGECONFIGLINK;

	@FindBy(xpath = "//li/a[contains(text(),'Manage Form Display')]")
	public WebElement FORMDISPLAY;

	@FindBy(xpath = "//li/a[contains(text(),'Manage Return Specific Rules')]")
	public WebElement RETUNRSPCIFICRUILES;

	@FindBy(xpath = "//li/a[contains(text(),'Manage Location Specific Rules')]")
	public WebElement LOCATIONRULES;

	@FindBy(xpath = "//li/a[contains(text(),'Manage Location Setup')]")
	public WebElement LOCATIONSETUP;

	@FindBy(xpath = "//li/a[contains(text(),'Associated Taxpayer Return Rules')]")
	public WebElement TAPYERRULES;

	@FindBy(xpath = "//label[contains(text(),'Return Name:')]/../div/a")
	public WebElement RETURNNAMEDRPDN;

	@FindBy(xpath = "//div[@id='select2-drop']/div[@class='select2-search']/input[@type='text']")
	public WebElement RETURNSINPUT;

	@FindBy(xpath = "//label[contains(text(),'Jurisdiction:')]/../div/a")
	public WebElement STATEDRPDN;

	@FindBy(xpath = "//label[contains(.,'Location Required For?')]/../div/a")
	public WebElement LOCATIONSREQFORDRP;

	@FindBy(xpath = "//label[contains(.,'Registration Number Field Name')]/../div/a")
	public WebElement REGISTRATIONNUMDRP;

	@FindBy(xpath = "//label[contains(.,'Return Name')]/../div/a")
	public WebElement RETURNNAMEDRPDWN;

	@FindBy(xpath = "//label[contains(.,'Jurisdiction')]/../div/a")
	public WebElement JURISDICTIONDRP;

	@FindBy(xpath = "//label[contains(.,'Common Fields or Return Field Associations')]/../div/a")
	public WebElement COMMONFIELDSDRP;

	@FindBy(xpath = "//label[contains(text(),'Return Level:')]/../div/a")
	public WebElement RETURNLVLDRPDN;

	@FindBy(xpath = "//label[contains(text(),'Return Type:')]/../div/a")
	public WebElement RETURNTYPE;

	@FindBy(xpath = "//label[contains(text(),'Tax Type:')]/../div/a")
	public WebElement TAXTYPEDRPDN;

	@FindBy(xpath = "//label[text()='Cloud Supported:']/../div/a")
	public WebElement SMBSUPPORTEDDRPDN;

	@FindBy(xpath = "//th/div[contains(text(),'Return Name')]")
	public WebElement RETURNNAMEGRID;

	@FindBy(xpath = "//th/div[contains(text(),'Return Level Name')]")
	public WebElement RETURNLEVELGRID;

	@FindBy(xpath = "//th/div[contains(text(),'Return Type')]")
	public WebElement RETURNTYPEGRID;

	@FindBy(xpath = "//th/div[contains(text(),'TaxType')]")
	public WebElement TAXTYPEGRID;

	@FindBy(xpath = "//th/div[contains(text(),'Action')]")
	public WebElement ACTIONGRID;

	@FindBy(xpath = "//th/div[text()='Return Id']")
	public WebElement RETURNIDGRID;

	@FindBy(xpath = "//th/div[text()='Cloud Supported']")
	public WebElement SMBSUPPORTEDGRID;

	@FindBy(xpath = "//th/div[text()='Start Date']")
	public WebElement STARTDATEGRID;

	@FindBy(xpath = "//th/div[text()='End Date']")
	public WebElement ENDDATEGRID;

	@FindBy(xpath = "//label[text()='EDI Supported:']")
	public WebElement EDISUPPORTEDDRPDN;

	@FindBy(xpath = "//th/div[text()='EDI Supported']")
	public WebElement EDISUPPORTEDGRID;

	@FindBy(xpath = "//tr[1]/td/button[contains(.,'Update')]")
	public WebElement UPDATEACTION;

	@FindBy(xpath = "//tr[1]/td/button[contains(.,'Cancel')]")
	public WebElement CANCELOCATION;

	@FindBy(xpath = "//tr[1]/td/button[text()='Update']")
	public WebElement UPDATEDISABLE;

	@FindBy(xpath = "//tr[1]/td/button[text()='Cancel']")
	public WebElement CANCELDISABLE;

	@FindBy(xpath = "//tr[1]/td[5]/input[@type='text']")
	public WebElement DEFAULTLOCATION;

	@FindBy(xpath = "//tr[1]/td")
	public WebElement RTRNNAMEGRDROW;

	@FindBy(xpath = "//label[contains(.,'Search Returns')]/../input")
	public WebElement SEARCHRETRUNRETURN;

	@FindBy(xpath = "//button[contains(.,'Search')]")
	public WebElement SEARCHBUTTONRETURN;

	@FindBy(xpath = "//label[contains(text(),'Rule:')]/../select")
	public WebElement RULEDRPDN;

	@FindBy(xpath = "//label[contains(text(),'Status:')]/../select")
	public WebElement STATUSDRPDN;

	@FindBy(xpath = "//th/div[contains(text(),'Rule Name')]")
	public WebElement RULENAMERETURN;

	@FindBy(xpath = "//th/div[contains(text(),'Status')]")
	public WebElement STATUSRETURN;

	@FindBy(xpath = "//th/div[contains(text(),'Default')]")
	public WebElement DEFAULTRETURN;

	@FindBy(xpath = "//th/div[contains(text(),'Comment')]")
	public WebElement COMMENTRETURN;

	@FindBy(xpath = "//th/div[contains(text(),'Action')]")
	public WebElement ACTIONRETURN;

	@FindBy(xpath = "//tr[1]/td/input[@type='checkbox']")
	public WebElement VISIBLECHECK;

	@FindBy(xpath = "//tr[1]/td/input[@type='text']")
	public WebElement COMMENTTXTBX;

	@FindBy(xpath = "//label[contains(text(),'Jurisdiction:')]/../div/a")
	public WebElement STATEDROPDOWN;

	@FindBy(xpath = "//label[contains(.,'Return Name:')]/../div/a")
	public WebElement RETURNNAMEDRP;

	@FindBy(xpath = "//tr[1]/td[1]")
	public WebElement STATENAME;

	@FindBy(xpath = "//label[contains(.,'Search By Rule')]/../input")
	public WebElement SEARCHBYRULELOC;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	public WebElement SEARCHBUTLOC;

	@FindBy(xpath = "//label[contains(text(),'Jurisdiction:')]/../div/a")
	public WebElement STATEDROPDOWNLOC;

	@FindBy(xpath = "//label[contains(text(),'Rule:')]/../div/a")
	public WebElement RULEDROPDOWNLOC;

	@FindBy(xpath = "//label[contains(text(),'Status:')]/../div/a")
	public WebElement STATUSDROPDOWNLOC;

	@FindBy(xpath = "//th/div[contains(text(),'Jurisdiction Name')]")
	public WebElement STATENAMELOC;

	@FindBy(xpath = "//th/div[contains(text(),'Rule Name')]")
	public WebElement RULENAMELOC;

	@FindBy(xpath = "//th/div[contains(text(),'Status')]")
	public WebElement STATUSLOC;

	@FindBy(xpath = "//th/div[contains(text(),'Visible?')]")
	public WebElement VISIBLE;

	@FindBy(xpath = "//th/div[contains(text(),'Default')]")
	public WebElement DEFAULTLOC;

	@FindBy(xpath = "//th/div[contains(text(),'Comment')]")
	public WebElement COMMENTLOC;

	@FindBy(xpath = "//th/div[contains(text(),'Action')]")
	public WebElement ACTIONLOC;

	@FindBy(xpath = "//label[contains(text(),'Jurisdiction:')]/../select")
	public WebElement STATESETUP;

	@FindBy(xpath = "//label[contains(text(),'Location Required For?')]/../select")
	public WebElement LOCATIONREQUIREDFORSETUP;

	@FindBy(xpath = "//label[contains(text(),'Registration Number Field Name')]/../select")
	public WebElement REGNUMBERFIELDNAMESETUP;

	@FindBy(xpath = "//th/div[contains(text(),'Jurisdiction Name')]")
	public WebElement STATENAMESETUP;

	@FindBy(xpath = "//th/div[contains(text(),'Location Required')]")
	public WebElement LOCATIONREQSETUP;

	@FindBy(xpath = "//th/div[contains(text(),'Registration No. Field Name')]")
	public WebElement REGNUMBERSETUP;

	@FindBy(xpath = "//th/div[contains(text(),'Location Specific Message')]")
	public WebElement LOCSPECMESSAGESETUP;

	@FindBy(xpath = "//th/div[contains(text(),'Action')]")
	public WebElement ACTIONSETUP;

	@FindBy(xpath = "//tr[1]/td/div/select/../select")
	public WebElement REGNUMDROPDOWNSETUP;

	@FindBy(xpath = "//tr[1]/td/div/button[text()='ADD']")
	public WebElement ADDBUTTONSETUP;

	@FindBy(xpath = "//tr[1]/td/div/button[text()='x']")
	public WebElement XBUTTONSETUP;

	@FindBy(xpath = "//tr[1]/td[2]/select")
	public WebElement LOCREQDRPDOWNSETUP;

	@FindBy(xpath = "//tr[1]/td/div/input")
	public WebElement ADDTEXTBOXSETUP;

	@FindBy(xpath = "//span[2]/input[@type='text']")
	public WebElement TAXPAYERSEARCHRETURNS;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	public WebElement SEARCHTAXPAYER;

	@FindBy(xpath = "//label[contains(text(),'Return Name')]/../select")
	public WebElement RETURNNAMETAXPAYER;

	@FindBy(xpath = "//label[contains(text(),'Jurisdiction')]/../select")
	public WebElement STATETAXPAYER;

	@FindBy(xpath = "//label[contains(text(),'Common Fields or Return Field Associations')]/../select")
	public WebElement COMMONTAXPAYER;

	@FindBy(xpath = "//span[2]/input[@type='text']")
	public WebElement SEARCHRETURNSTAXPAYER;

	@FindBy(xpath = "//button[@id='btnAssociation1']")
	public WebElement ADDASSOCTAXPAYER;

	@FindBy(xpath = "//th/div[contains(text(),'From Return Name')]")
	public WebElement FRMRETURNNAMETAXPAYER;

	@FindBy(xpath = "//th/div[contains(text(),'To Return Name')]")
	public WebElement TORETURNNAMETAXPAYER;

	@FindBy(xpath = "//th/div[contains(text(),'Registration Number')]")
	public WebElement REGNUMBERTAXPAYER;

	@FindBy(xpath = "//th/div[contains(text(),'Filing Frequency')]")
	public WebElement FILINGFREQTAXPAYER;

	@FindBy(xpath = "//th/div[contains(text(),'Return Fields')]")
	public WebElement RETURNFIELDSTAXPAYER;

	@FindBy(xpath = "//th/div[contains(text(),'Action')]")
	public WebElement ACTIONTAXPAYER;

	@FindBy(xpath = "//tr[1]/td/input[@data-bind='checked: bRegNum']")
	public WebElement REGNUMCHECKTAXPAYER;

	@FindBy(xpath = "//tr[1]/td/input[@data-bind='checked: bFilingFreq']")
	public WebElement FILINGFREQCHECKTAXPAYER;

	@FindBy(xpath = "//tr[1]/td/input[@data-bind='checked: bReturnFields']")
	public WebElement RETURNFIELDSCHECKTAXPAYER;

	@FindBy(xpath = "//tfoot/tr[1]/td[1]/select")
	public WebElement LASTROWFROMDRPDOWNTAXPAYER;

	@FindBy(xpath = "//tfoot/tr[1]/td[2]/select")
	public WebElement LASTROWTODRPDOWNTAXPAYER;

	@FindBy(xpath = "//tfoot/tr/td/input[@data-bind='checked:testrn']")
	public WebElement LASTROWUREGNUMBNCHECKTAXPAYER;

	@FindBy(xpath = "//tfoot/tr/td/input[@data-bind='checked:testff']")
	public WebElement LASTROWFILINGUNCHECKTAXPAYER;

	@FindBy(xpath = "//tfoot/tr/td/input[@data-bind='checked:testrf']")
	public WebElement LASTROWRETURNUNCHECKTAXPAYER;

	@FindBy(xpath = "//tfoot/tr/td/button[@id='btnAdd']")
	public WebElement LASTROWADDTAXPAYER;

	@FindBy(xpath = "//tfoot/tr/td/button[@id='btnCancel']")
	public WebElement LASTROWCANCELTAXPAYER;

	@FindBy(xpath = "//button[@id='btnAssociation1']")
	public WebElement ADDASSOCBUTTONTAXPAYER;

	@FindBy(xpath = "//button[@id='btnAssociation2']")
	public WebElement ADDASSOCDISABLETAXPAYER;

	@FindBy(xpath = "//th[contains(.,'Out-of-State Sales')]/../../../tbody/tr/td[8]/input[@type='checkbox']")
	public WebElement OutofSaleschkbox;

	@FindBy(xpath = "//button[contains(.,'Update')]")
	public WebElement update;

	@FindBy(xpath = "//li[contains(.,'Automation')]")
	public WebElement Billingapprovetreasurywelcome;

	@FindBy(xpath = "//div/label[contains(text(),'Return Name:')]/../div/a/../input")
	public WebElement ReturnNameInput;

	@FindBy(xpath = "//input[@class='select2-input']")
	public WebElement srh;

	@FindBy(xpath = "//input[@id='search']")
	public WebElement SEARCHRETURNS;

	@FindBy(xpath = "//button[contains(.,'Search')]")
	public WebElement SEARCHBUTTON;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Visible?')]/../../../tbody/tr/td[4]/input")
	public WebElement VISIBLECHECKBOXDISABLED;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Comment')]/../../../tbody/tr/td[6]/input")
	public WebElement COMMENTFIELD;

	@FindBy(xpath = "//label[contains(.,'Jurisdiction:')]/../div/a")
	public WebElement JURISDICTIONLOC;

	@FindBy(xpath = "//div[@id='select2-drop']/div/input")
	public WebElement JURISDICTIONSEARCH;

	@FindBy(xpath = "//label[contains(.,'Rule:')]/../div/a")
	public WebElement RULELOC;

	@FindBy(xpath = "//div[@id='select2-drop']/div/input")
	public WebElement RULESEARCH;

	@FindBy(xpath = "//span/strong[contains(.,'1 to 25 of')]")
	public WebElement PageLoad;

}
