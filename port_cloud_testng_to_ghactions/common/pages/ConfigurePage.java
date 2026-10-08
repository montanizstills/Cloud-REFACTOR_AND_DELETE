/*###################################################################################################
Name: ConfigurePage.java 
Description: Professional User - Configure Page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/
package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class ConfigurePage {
	@FindBy(xpath = "//span[contains(.,'Dashboard')]")
	public WebElement Dashboard;
	
	@FindBy(xpath = "//span[contains(.,'Configure')]")
	public WebElement Configurecompany;
	
	@FindBy(xpath = "//div[@class='section ng-scope']/p/inline-help/span[text()='Please configure each company below. You are required, at a minimum, to add basic information and one tax registration in order to complete configuration for each respective company. You may add additional information as needed. Please review all areas of the configuration model to determine which areas best support your business practices. If you have questions about configuration, please see our #Get Help# section.']")
	public WebElement CONFIG_DESCRIPTION;
	
	@FindBy(xpath = "//div/div/h3[contains(.,'Mappings')]/../div[2]/span[contains(.,'Mapped')]/../span[contains(.,'0')]")
	public WebElement PROD_COUNT_ZERO;
	
	// @FindBy(xpath= "//a[contains(text(),'Total
	// Exceptions')]/../span[contains(text(),'0')]")
	@FindBy(xpath = "//div/div/h3[contains(.,'Customer Exceptions')]/../div/span[contains(.,'Customers')]/../span[contains(.,'0')]")
	public WebElement EXCEPTIONS_COUNT_ZERO;
	
	@FindBy(xpath = "//a[contains(text(),'Go to Configuration Overview')]")
	public WebElement GOTOCONFIGOVERVIEW;
	
	@FindBy(xpath = "//div[@class='section ng-scope']/a[contains(text(),'Return to Configuration Overview')]")
	public WebElement RETURNTOCONFIGOVERVIEW;
	
	@FindBy(xpath = "//div[@class='section header-block ng-scope']/a[contains(text(),'Return to Configuration Overview')]")
	public WebElement RETURNTOHEAD_CONFIGOVERVIEW;
	
	@FindBy(xpath = "//div[@class='section ng-scope']/a[contains(text(), 'Return to Company Overview')]")
	public WebElement RETURNTOCOMPOVERVIEW;
	
	@FindBy(xpath = "//div[@class='section header-block ng-scope']/a[contains(text(), 'Return to company overview')]")
	public WebElement RETURNTOCOMPOVERVIEWHEAD;
	
	@FindBy(xpath = "//a[contains(text(),'Edit Company (Filing Entity) Information')]")
	public WebElement EDITCOMPANYENTITY;
	
	@FindBy(xpath = "//button[@data-bind='click: $root.commit']")
	public WebElement COMMIT;
	
	@FindBy(xpath = "//a[@title='Configure']")
	public WebElement CONFIGURE;
	
	@FindBy(xpath = "//div/a[contains(.,'Ready to Save')]")
	public WebElement READYTOSAVE;
	
	@FindBy(xpath = "//button[contains(text(),'ADD COMPANY') and @disabled='disabled']")
	public WebElement ADDCOMPANYDISABLEBTN;
	
	@FindBy(xpath = "//a[contains(text(),'Update Analysis')]")
	public WebElement UPDATEANALYSIS;
	
	@FindBy(xpath = "//div[@class='blockUI blockMsg blockPage']")
	public WebElement PLEASEWAIT;
	
	@FindBy(xpath = "html[@class='nprogress-busy']")
	public WebElement NPROGRESSBUSY;
	
	@FindBy(xpath = "//spin-icon[@class='ng-scope']/div[@class='spinner']")
	public WebElement SPINNER;
	
	@FindBy(xpath = "//span/img")
	public WebElement LOADER;
	
	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement SETTINGSLINK;
	
	@FindBy(xpath = "//img[@alt='Vertex Cloud']")
	public WebElement VERTEXLOGO;
	
	@FindBy(xpath = "//button[contains(text(),'Yes to complete configuration')]")
	public WebElement COMPLETECONFIGPOPUP;
	
	@FindBy(id = "add-company")
	public WebElement ADDANOTHERCOMPANYLINK;

	@FindBy(xpath = "//a[@title='New Company']")
	public WebElement NEWCOMPANY;
	
	@FindBy(xpath = "//a[@title='Company']")
	public WebElement COMPANY;
	
	@FindBy(xpath = "//button[contains(text(),'Begin Removal')]")
	public WebElement BEGINREMOVAL;
	
	@FindBy(xpath = "//a[@class='btn btn-primary disabled ng-scope']")
	public WebElement BEGINREMOVALDISABLE;
	
	@FindBy(id = "country")
	public WebElement COUNTRY;
	
	@FindBy(id = "company-code")
	public WebElement COMPANYCODE;
	
	@FindBy(xpath = "//a[@id='Inline-help2PopupLink01Q']/i")
	public WebElement COMPANYCODEICON;
	
	@FindBy(xpath = "//inline-help[@id='Inline-help2']/div/h3")
	public WebElement COMPANYCODEICONLABEL;
	
	@FindBy(id = "company-name")
	public WebElement COMAPNYNAME;
	
	@FindBy(xpath = "//a[@id='Inline-help1PopupLink01R']/i")
	public WebElement COMPANYNAMEICON;
	
	@FindBy(xpath = "//inline-help[@id='Inline-help1']/div/div[2]")
	public WebElement COMPANYNAMEICONLLABEL;
	
	@FindBy(xpath = "//input[@id='company-start-date']")
	public WebElement STARTDATE;
	
	@FindBy(xpath = "//a[@id='Inline-help3PopupLink01S']/i")
	public WebElement STARTDATEICON;
	
	@FindBy(id = "statetempfirstName")
	public WebElement FIRST_NAME;
	
	@FindBy(id = "statetemplastName")
	public WebElement LAST_NAME;
	
	@FindBy(id = "title")
	public WebElement JOB_TITLE;
	
	@FindBy(id = "email")
	public WebElement EMAIL;
	
	@FindBy(id = "phone")
	public WebElement PHONE;
	
	@FindBy(id = "federal-id")
	public WebElement FEDERALCODE;
	
	public final String FEDCODE = "//input[@id='federal-id']";
	
	@FindBy(xpath = "//button[@data-bind='click: $root.save']")
	public WebElement SAVECHANGES;
	
	@FindBy(id = "address1")
	public WebElement ADDRESS1;
	
	@FindBy(id = "address2")
	public WebElement ADDRESS2;
	
	@FindBy(id = "city")
	public WebElement CITY;
	
	@FindBy(id = "state")
	public WebElement STATE;
	
	@FindBy(id = "city1")
	public WebElement MUNICIPALITY;
	
	@FindBy(xpath = "//p/button[contains(.,'Update')]")
	public WebElement UPDATE;
	
	@FindBys({ @FindBy(xpath = "//select[@id='state']/option"), })
	public List<WebElement> STATESize;
	
	@FindBy(id = "zip123")
	public WebElement ZIP;
	
	@FindBy(id = "zip")
	public WebElement CANADAZIP;
	
	public final String COMPZIP = "//input[@id='zip123']";
	
	@FindBy(xpath = "//button[contains(text(),'ADD COMPANY')]")
	public WebElement ADDCOMPANYBUTTON;
	
	@FindBy(xpath = "//button[contains(text(),'ADD COMPANY')][@disabled='disabled']")
	public WebElement ADDCOMPANYDISABLEBUTTON;
	
	@FindBy(xpath = "//a[contains(text(),'Save Configuration')]")
	public WebElement SAVECONFIGURATION;
	
	@FindBy(xpath = "//a[contains(text(),'Yes, confirm address information')]")
	public WebElement CONTACTCONFIRMATION;
	
	@FindBy(xpath = "//h3[@class='h5 heading-accent heading-accent has-error']")
	public WebElement CONFIGRED;
	
	@FindBy(xpath = "//h3[@class='h5 heading-accent heading-accent has-success']/span[text()='Products']")
	public WebElement CONFIGGREEN;
	
	@FindBy(xpath = "//div[@class='data-text']/div/input[1]")
	public WebElement CONFIRMRADIOPOPUP;
	
	// @FindBy(xpath= "//div[@class='banner-content banner
	// padding-banner']/h3/text()[2]")
	//
	@FindBy(xpath = "//div[@class='banner-content banner padding-banner']/h3")
	public WebElement VERIFYCOMPNAME;
	
	@FindBy(xpath = "//div[@class='span2'][1]/dl/dd")
	public WebElement VERIFYCOMPCODE;
	
	@FindBy(xpath = "//h2/inline-help/a/i")
	public WebElement HELPTEXT;
	
	// @FindBy(xpath= "//div[text()='Add the states in which you are registered to
	// collect tax. You will need a registration number for each return you select.
	// Be sure to use the correct registration number format or it may cause your
	// returns not to generate. Refer to the Help section for a list of formats.']")
	//
	@FindBy(xpath = "//div[text()='Add the states where you are registered to collect tax. You will need a registration number for each return you select. Be sure to use the correct registration number format or it may cause your returns to not generate. Refer to the Help section for a list of formats.']")
	public WebElement HELPTEXTCONTENT;
	
	@FindBy(xpath = "//h2[contains(.,'IN WHICH STATES WOULD YOU LIKE TO REGISTER?')]")
	public WebElement WHEREDOCONDUCTBUSINESS;
	
	@FindBy(xpath = "//h3[contains(.,'Click on the states in which you wish to register')]")
	public WebElement WHATJURISDICTIONSLOCATIONS;
	
	@FindBy(xpath = "//h1/i[@class='icon-section icon-configure']")
	public WebElement COMPANYICON;
	
	@FindBy(xpath = "//h1[contains(text(),'Configure')]")
	public WebElement COMPANYHEADER;
	
	// POR-3714 IN 4.0.3
	@FindBy(xpath = "//a[contains(text(),'Help Center')]")
	public WebElement VISITHELPCENTER;
	
	// **********EPIC5-Adding Another Company**********
	@FindBy(xpath = "//h3/span[2]/a[contains(text(),'Remove')]")
	public WebElement REMOVECOMPANYLINK;
	
	@FindBy(xpath = "//a[contains(text(),'Remove item')]")
	public WebElement REMOVEITEMBUTTON;

	@FindBy(xpath="//strong[contains(.,'Cancel')]")
	public WebElement Cancel;

	// **********EPIC4-Edit Company After Push OS**********
	@FindBy(xpath = "//h3/span[@class='pull-right']/a[contains(text(),'Edit')]")
	public WebElement EDITCOMPANYLINK;
	
	@FindBy(xpath = "//a[contains(text(),'Edit Company (Filing Entity) Information')]")
	public WebElement EDITCOMPANYINFOLINK;
	
	@FindBy(xpath = "//button[contains(text(),'Update')]")
	public WebElement UPDATECOMPANYBUTTON;
	
	@FindBy(xpath = "//div[@class='registration-block ng-scope' and @ng-repeat='company in data.companies']/div/h3[@class='h5 heading-accent has-warning ng-binding']")
	public WebElement INPROCESSCOMPANYCONFIG;
	
	// State,Product,Exception In process
	@FindBy(xpath = "//div[@class='ng-hide'][2]")
	public WebElement STATEINPROCESS;
	
	// @FindBy(xpath=
	// "//div[@ng-if='productIsCommittedInProgress']/h3[contains(text(),'Your
	// configuration is complete and processing')]")
	//
	@FindBy(xpath = "//h3[@class='h5 heading-accent heading-accent has-success has-warning']")
	public WebElement PRODINPROCESS;
	
	@FindBy(xpath = "//div[@ng-if='execptionIsCommittedInProgress']/h3[contains(text(),'Your configuration is complete and processing')]")
	public WebElement EXCEPINPROCESS;
	
	// **********EPIC-1 New Company TCs**********
	@FindBy(xpath = "//a[contains(text(),'Edit address information')]")
	public WebElement INVALIDADDRESSPOPUPEDITBUTTON;
	
	@FindBy(xpath = "//strong[contains(text(),'Puerto Rico')]")
	public WebElement PUERTORICOSTATE;
	
	@FindBy(xpath = "//strong[contains(text(),'Delaware')]")
	public WebElement DELAWARESTATE;
	
	@FindBy(xpath = "//strong[contains(text(),'Montana')]")
	public WebElement MONTANASTATE;
	
	@FindBy(xpath = "//strong[contains(text(),'New Hampshire')]")
	public WebElement NEWHAMPSHIRESTATE;
	
	@FindBy(xpath = "//strong[contains(text(),'Oregon')]")
	public WebElement OREGONSTATE;
	
	// *************Download Returns *************
	@FindBy(xpath = "//button[contains(.,'Download All Returns')]")
	public WebElement DOWNLOADALLRTRNS;
	
	@FindBy(xpath = "//p[contains(.,'visit the')]/a[text()='Help Center']")
	public WebElement HELPCENTERTEXT;
	
	@FindBy(xpath = "//button[contains(text(),'Save') and @disabled='disabled']")
	public WebElement JURFORMSAVEDISABLE;
	
	@FindBy(xpath = "//ul[@class='nav nav-list']/li/a/span[contains(text(),'Document Center')]")
	public WebElement DOCUMENTCENTER;
	
	// *************Discount Codes *************
	/*
	 * @FindBy(xpath= "//a[contains(.,'Add Discount Codes')]") public WebElement
	 * ADDDISCOUNTCODES
	 * 
	 * @FindBy(xpath= "//input[@name='General Discount']") public WebElement
	 * GENERALDISCOUNT;
	 * 
	 * @FindBy(xpath= "//input[@name='Cash Discount']") public WebElement
	 * CASHDISCOUNT;
	 * 
	 * @FindBy(xpath= "//input[contains(@name,'Manufacturer')]") public WebElement
	 * MANUFACTURERCOUPONS;
	 * 
	 * @FindBy(xpath= "//input[contains(@name,'Retailer')]") public WebElement
	 * RETAILERCOUPON;
	 * 
	 * @FindBy(xpath= "//input[@name='Trade Discount']") public WebElement
	 * TRADEDISCOUNT;
	 * 
	 * @FindBy(xpath= "//input[@name='Quantity Discount']") public WebElement
	 * QUANTITYDISCOUNT;
	 * 
	 * @FindBy(xpath= "//input[@name='Early Payment Discount']") public WebElement
	 * EARLYPAYMENTDISCOUNT;
	 * 
	 * @FindBy(xpath= "//input[@name='Flexible Discount']")
	 */
	//
	// ********************Discount Codes******************
	@FindBy(xpath = "//h2[contains(text(),'3. Discount Codes')]")
	public WebElement DISCOUNTCODES;
	
	@FindBy(xpath = "//input[@id='federal-id']/../div/span[text()='This field is required.']")
	public WebElement FEDERALIDERROR;
	
	@FindBy(xpath = "//p[2]/a[1]")
	public WebElement ADDDISCOUNTCODESLINK;
	
	@FindBy(xpath = "//input[@name='General Discount']")
	public WebElement GENERALDISCOUNT;
	
	@FindBy(xpath = " //input[@name='Cash Discount']")
	public WebElement CASHDISCOUNT;
	
	@FindBy(xpath = "//input[@id='discount-1-1'] ")
	public WebElement MANUFACTURESDISCOUNT;
	
	@FindBy(xpath = "//input[@id='discount-1-2'] ")
	public WebElement RETAILERDISCOUNT;
	
	@FindBy(xpath = "//input[@name='Trade Discount']")
	public WebElement TRADEDISCOUNT;
	
	@FindBy(xpath = "//input[@name='Quantity Discount']")
	public WebElement QUALITYDISCOUNT;
	
	@FindBy(xpath = "//input[@name='Early Payment Discount']")
	public WebElement EARLYPAYMENTDISCOUNT;
	
	@FindBy(xpath = "//input[@name='Flexible Discount']")
	public WebElement FLEXIBLEDISCOUNT;
	
	@FindBy(xpath = "//p[2]/a[2]")
	public WebElement CLOSEDISCOUNTCODES;
	
	@FindBy(xpath = "//div[5]/dl/dt[contains(text(),'Discount Codes')]/../dd")
	public WebElement DISCOUNTCODESCCOUNT;
	
	@FindBy(xpath = "//p/a[@class='ng-hide' and contains(.,'Close Discount Codes')]")
	public WebElement EDITCMPNYCLOSEDISCOUNTS;
	
	@FindBy(xpath = "//p/a[contains(.,'Add Discount Codes')]")
	public WebElement EDITCMPNYADDDISCOUNTS;
	
	@FindBy(xpath = "//h1[contains(text(),'Configure Company')]/../h1/i")
	public WebElement CONFIGURECOMPANYICON;
	
	@FindBy(xpath = "//a[@class='btn pull-right']")
	public WebElement RETURNCONFIGOVERVIEWRIGHTCORN;
	
	@FindBy(xpath = "//a[@class='btn'][text()='Return to Configuration Overview']")
	public WebElement RETURNCONFIGOVERVIEWLEFTCORN;
	
	@FindBy(xpath = "//h2[contains(text(),'Where do you conduct business?')]")
	public WebElement WHEREDOYOUCONDUCTBUSINESS;
	
	@FindBy(xpath = "//th[contains(.,'All States')]")
	public WebElement ALLJURISDICTIONS;
	
	@FindBy(xpath = "//h2[contains(text(),'Jurisdictions you are filing in')]")
	public WebElement JURISDICTIONSURFILLING;
	
	@FindBy(xpath = "//tbody/tr/td[contains(.,'California')]/../../tr/td[contains(.,'Colorado')]/../../tr/td[contains(.,'Canada')]")
	public WebElement CANADASTATE;
	
	@FindBy(xpath = "//div[3]/div[3]/div/div/div/div/div/div[3]/div[2]/div/span")
	public WebElement LOCATIONSCOUNTBEFORE;
	
	@FindBy(xpath = "//div[@class='banner warning has-sibling ng-hide']/div/a[contains(text(),'Save Configuration')]")
	public WebElement HIDDENSAVECONFIGURE;
	
	@FindBy(xpath = "//h5[contains(.,'The address you have entered has no match.  Please select �Edit address information�')]")
	public WebElement LOCINVALIDWARNINGMSG;
	
	@FindBy(xpath = "//h5[contains(.,'If the address displayed is correct, please click the button to confirm.')]")
	public WebElement LOCVALIDWARNINGMSG;
	
	@FindBy(xpath = "//h5[contains(.,'The address that you entered returned more than one match. Please select an option')]")
	public WebElement LOCMULTIPLEWARNINGMSG;
	
	@FindBy(xpath = "//a[contains(.,'Edit address information')]")
	public WebElement EDITLOCATIONADDRESS;
	
	@FindBy(xpath = "//p/a[contains(.,'Return to ')]")
	public WebElement RETURNTOREGISTRATIONS;
	
	@FindBy(xpath = "//p/strong[contains(.,'You will lose entered information if you exit this page without saving.')]")
	public WebElement RETURNTOREGWARNINGMSG;
	
	@FindBy(xpath = "//button[contains(.,'Yes to exit Locations')]")
	public WebElement EXITLOCATIONSBTN;

	@FindBy(xpath = "//h3[contains(.,'Validation Message!')]")
	public WebElement ADDCOMPANYVALIDATIONMSG;

	@FindBy(xpath = "//h3[contains(.,'Validation Message!')]")
	public WebElement ADDLOCVALIDATIONMSG;

	@FindBy(xpath = "//strong[contains(.,'The end date of the customer must be greater than all related certificates')]")
	public WebElement ERRORMESSAGE;

	@FindBy(xpath = "//button[contains(.,'Save & Close')]")
	public WebElement SAVEANDCLOSE;

	@FindBy(xpath = "//div/p[contains(.,'We cannot find a match to this address.')]")
	public WebElement INVALIDADDRESS;

	@FindBy(xpath = "//h5[contains(.,'The Zip code you have entered does not match the State selected.')]")
	public WebElement INVALIDZIP;

	@FindBy(xpath = "//h5[contains(.,'The address you have entered has no match.')]")
	public WebElement NOMATCHADDRESS;

	@FindBy(xpath = "//p/a[contains(.,'Edit address information')]")
	public WebElement EDITADDRESS;

	@FindBy(xpath = "//a/strong[contains(.,'No, edit address information')]")
	public WebElement RETURNSTOLOCATION;

	@FindBy(xpath = "//a[contains(.,'Edit Company (Filing Entity) Information')]")
	public WebElement EDITCOMPANYADDRESS;

	@FindBy(xpath = "//button[contains(.,'Update')]")
	public WebElement UPDATECOMPANYADDRESS;
	
	// 4.6 Changes
	@FindBy(xpath = "//inline-help/span[contains(.,'Please configure each company below.')]/b/../../span[contains(.,'Please review all areas of the configuration model to determine which areas best support your business practices and add additional information as needed. If you have questions about configuration, please visit our')]")
	public WebElement CONFIGUREHEADING;
	
	@FindBy(xpath = ".//*[@id='quick-search']")
	public WebElement QUICKESEARCHTEXTBOX;
	
	@FindBy(xpath = "//h1[contains(.,'Internal Server Error')]")
	public WebElement INTERNALSERVERERROR;
	
	@FindBy(xpath = "//a[contains(.,'logging back into TaxCentral.')]")
	public WebElement BACKTOCENTRAL;
	
	@FindBy(xpath = "//input[@id='UserName']")
	public WebElement USER;
	
	@FindBy(xpath = "//h1[text()='Page Not Found']")
	public WebElement PAGENOTFOUND;
	
	@FindBy(xpath = "//p[contains(text(),'If you are having trouble, please visit our Help Center.')]")
	public WebElement HELP;
	
	@FindBy(xpath = "//label/strong[contains(.,'End Date')]")
	public WebElement ENDDATE;
	
	@FindBy(xpath = "//button[text()='Remove']")
	public WebElement REMOVE;

	@FindBy(xpath = "//button[contains(.,'Activate')]")
	public WebElement Activate;

	@FindBy(xpath="//span[contains(.,'By end-dating/removing a registration, the system will no longer generate a return for said jurisdiction. Proceed with caution before end-dating/removal.')]")
	public WebElement RemoveBlockMessage;

	@FindBy(xpath="//strong[contains(.,'Are you sure you want to end date this return? It is important that you ensure that all tax collected for this jurisdiction/return is reported and paid. This includes any outstanding prepayments or credits.')]")
	public WebElement removeItemPopupmsg;

	@FindBy(xpath="//strong[contains(.,'Note: End dating returns will have no impact on returns previously filed or in the process of being filed on or before today')]")
	public WebElement removeItemNotePopupmsg;

	@FindBy(xpath = "//p/a[2]")
	public WebElement ENTITILINK;
	
	@FindBy(xpath = "//li[@id='pagernums']/a[@class='pagecount hand selected']")
	public WebElement PAGECOUNT;
	
	@FindBy(xpath = "//a[@id='confirm-remove-customer']")
	public WebElement CONFIRMREMOVECUSTOMER;
	
	@FindBy(xpath = "//label[contains(.,'2. Upload Your Template')]/../fileupload/div/div/button[contains(.,'Browse')][@disabled='disabled']")
	public WebElement BROWSE_DISABLE;
	
	@FindBy(xpath = "//p[contains(.,'File cannot be uploaded until the taxpayer�s configuration has been committed.')]")
	public WebElement UPLOADTEMPLATETEXT;
	
	@FindBy(xpath = "//label[contains(.,'2. Upload Your Template')]/../fileupload/div/button[contains(.,'Upload File')][@disabled='disabled']")
	public WebElement UPLOADFILE_DISABLE;
	
	@FindBy(xpath = "//label[@class='file-input-wrapper btn btn-default upload-file']")
	public WebElement BROWSE_ENABLE;

	@FindBy(xpath = "//label[@class='file-input-wrapper btn btn-default upload-file']/../../../div/button[@class='btn btn-primary ng-binding'][contains(.,'Upload File')]")
	public WebElement UPLOADFILE_ENABLE;
	
	@FindBy(xpath = "//a[contains(.,'Ok')]")
	public WebElement OKERROR;
	
	// Configuration Message with No location, state
	@FindBy(xpath = "//p/strong[contains(.,' If you are ready to apply the highlighted updates to your configuration,')]")
	public WebElement CONFIGMSGNOLOCSTATE;
	
	// Configuration Message with location, state
	@FindBy(xpath = "//div/p/strong[text()=' If you are ready to apply the highlighted updates to your configuration, click Save Configuration.']")
	public WebElement CONFIGMSGLOCSTATE;
	
	@FindBy(xpath = "//span[text()='Leading, trailing or consecutive spaces are not allowed in the code.']")
	public WebElement COMPCODERRMSG;
	
	@FindBy(xpath = "//span[text()='Leading, trailing or consecutive spaces are not allowed in the code.']")
	public WebElement LOCCODERRMSG;
	
	@FindBy(xpath = "//span[text()='Leading, trailing or consecutive spaces are not allowed in the code.']")
	public WebElement PRODCODERRMSG;
	
	@FindBy(xpath = "//span[text()='Backslash is not allowed in the code.']")
	public WebElement PRODCODBCKSLSHMSG;
	
	@FindBy(xpath = "//span[text()='Leading, trailing or consecutive spaces are not allowed in the code.']")
	public WebElement EXCEPCODERRMSG;
	
	@FindBy(xpath = "//p/strong[contains(.,'This product is associated with a certificate and cannot be deleted.')]")
	public WebElement CERTIFICATEPRODUCTPOPUP;
	
	@FindBy(xpath = "//label[text()='How can we help you?']")
	public WebElement HELPCENTER;
	
	@FindBy(xpath = "//span/select[@ng-model='data.pageSize']")
	public WebElement PAGESIZE;
	
	public final String NPROGRESSBUSY_XPATH = "html[@class='nprogress-busy']";
	
	public final String LOADER_XPATH = "//span/img";
	
	public final String SPINNER_XPATH = "//spin-icon/div[@class='spinner']";
	
	public final String COMPLETECONFIGPOPUP_XPATH = "//button[contains(text(),'Yes to complete configuration')]";
	
	public final String ADDCOMPANYDISABLEBUTTON_XPATH = "//button[contains(text(),'ADD COMPANY')][@disabled='disabled']";
	
	public final String CONFIRMREMOVECUSTOMER_XPATH = "//a[@id='confirm-remove-customer']";
	
	public final String PLEASEWAIT_XPATH = "//div[@class='blockUI blockMsg blockPage']";
	
	public final String SAVECONFIGURATION_XPATH = "//div[@class='banner warning has-sibling']/div/a[contains(text(),'Save Configuration')]";
	
	@FindBy(xpath = "//inline-help/span[contains(.,'Please configure each company below. Please review all areas of the configuration to determine which areas best support your business practices and add additional information as needed. If you have questions about configuration, please visit our Help Center')]")
	public WebElement CONFIGUREVIEWTEXT;
	
	@FindBy(xpath = "//inline-help/span[contains(.,'Please configure each company below. In order to save your changes, you must select at least one jurisdiction and enter one registration for each company added. Please review all areas of the configuration model to determine which areas best support your business practices and add additional information as needed. If you have questions about configuration, please visit our Help Center')]")
	public WebElement CALCANDRETURNSONLYCONFIGUREVIEW;
	
	@FindBy(xpath = "	//button[@disabled='disabled']/../button[contains(.,'Go Live')]")
	public WebElement GoLiveDisable;
	
	@FindBy(xpath = "//h2[contains(.,'Test Mode')]")
	public WebElement Testmode;
	
	@FindBy(xpath = "//h3[contains(.,'Certificate Image')]/../div/div/fileupload/div/div/label[contains(.,'Browse')]")
	public WebElement AFTERCERTIFICATEIMAGEBROWSE;
	
	@FindBy(xpath = "//p[contains(.,'File cannot be uploaded until the taxpayer�s configuration has been committed.')]/../p[@class='strong']")
	public WebElement UPLOADTEMPLATETEXTENABLE;
	
	@FindBy(xpath = "//div/span[text()='Code / Class already exists!']")
	public WebElement CODECLASSALREADYEXISTS;
	
	@FindBy(xpath = "//h1[contains(.,'Configure Overview')]")
	public WebElement ConfigOverView;
	
	@FindBy(xpath = "//a[@class='btn btn-primary disabled pull-left'] [contains(.,'Add Another Company')]")
	public WebElement AddAnotherCompanyDisable;
	
	@FindBy(xpath = "//p[contains(.,'The company limit has been met. Please contact  customer support if additional companies are needed.')]")
	public WebElement COMPANYLIMITTEXT;
	
	@FindBy(xpath = "//a[contains(.,'Continue to Login Page')]")
	public WebElement CONTINUETOLOGINPAGE;
	
	@FindBy(xpath = "//p/button[contains(.,'Update')]")
	public WebElement UPDATEBUTTON;
	
	@FindBy(xpath = "//h1[contains(.,'Configure Company')]")
	public WebElement ConfigureCompanyheader;
	
	@FindBy(xpath = "//h1[contains(.,'Configure Company')]")
	public WebElement CONFIGURECOMPANYHEADING;
	
	@FindBy(xpath = "//p[contains(.,'After your configuration is created or modified, transaction processing from the Cloud and/or host system may not immediately reflect the expected tax results from the most recent configuration changes.')]")
	public WebElement CONFIGURECOMPANYTEXT;
	
	@FindBy(xpath = "//div/label[contains(.,'Please save your taxability mapping and Save Configuration. Edit from the Configuration Mapping table to add a conditional rule.')]")
	public WebElement QCMSG;
	
	@FindBy(xpath = "//a[contains(.,'Pending Certificates')]")
	public WebElement PENDINGCERTIFICATES;
	
	@FindBy(xpath = "//button[contains(.,'Send Rejection')]")
	public WebElement SENDREJECTION;
	
	@FindBy(xpath = "//a[contains(.,'Customers')]")
	public WebElement CUSTOMERS;
	
	@FindBy(xpath = "//div/label[contains(.,'Customer Name')]/../label[@class='ng-binding']")
	public WebElement CUSTOMERNAMEVALUE;
	
	@FindBy(xpath = "//div/label[contains(.,'Customer Type')]/../label[2]")
	public WebElement CUSTOMERTYPE;
	
	@FindBy(xpath = "//div/label[contains(.,'Customer Code/Class')]/../label[2]")
	public WebElement CUSTOMERCODECLASS;
	
	@FindBy(xpath = "//div/label[contains(.,'Certificate Scope')]/../label[2]")
	public WebElement CERTIFICATESCOPE;
	
	@FindBy(xpath = "//div/label[contains(.,'Start Date')]/../label[2]")
	public WebElement CERTIFICATESTARTDATE;
	
	@FindBy(xpath = "//div/label[contains(.,'End Date')]/../label[2]")
	public WebElement CERTIFICATEENDDATE;
	
	@FindBy(xpath = "//div[@class='accordion-unification__panel-content']/div/div/table/tbody/tr/td[1]")
	public WebElement EXEMTIONSTATEVERIFY;
	
	@FindBy(xpath = "//div[@class='accordion-unification__panel-content']/div/div/a")
	public WebElement IMAGEFILE;
	
	@FindBy(xpath = "//a[contains(.,'Active Certificates')]")
	public WebElement ActiveCertificates;
	
	@FindBy(xpath = "//button[contains(.,'Save Jurisdiction')]")
	public WebElement SAVEJRISDICTION;
	
	@FindBy(xpath = "//input[@id='remove-customer-end-date']")
	public WebElement CERTIFCATEENDDATE;
	
	@FindBy(xpath = "//a[contains(.,'Remove item')]")
	public WebElement REMOVEITEM;
	
	@FindBy(xpath = "//label[contains(.,'Browse')]")
	public WebElement BROWSE;
	
	@FindBy(xpath = "//a/strong[contains(.,'Help Center')]")
	public WebElement HELPCENTERLINK;
	
	@FindBy(xpath = "//textarea[@class='input-block-level ng-pristine ng-untouched ng-valid']")
	public WebElement REJECTREASONTEXTBOX;
	
	@FindBy(xpath = "//a[contains(.,'Pending Certificates')]/../span[@class='badge badge-large ng-binding badge-important']")
	public WebElement PENDINGCOUNT;
	
	@FindBy(xpath = "//a[contains(.,'Pending Certificates')]/../span[@class='badge badge-large ng-binding badge-success']")
	public WebElement PENDINGCOUNTGRFEEN;
	
	@FindBy(xpath = "//a[contains(.,'Active Certificates')]/../span[@class='badge badge-large ng-binding badge-important']")
	public WebElement ACTIVECERTIFICATECOUNT;
	
	@FindBy(xpath = "//a[contains(.,'Active Certificates')]/../span[@class='badge badge-large ng-binding badge-success']")
	public WebElement ACTIVECERTIFICATECOUNTGREEN;
	
	@FindBy(xpath = "//button[contains(.,'Revalidate')]")
	public WebElement REVALIDATE;
	
	@FindBy(xpath = "//button[contains(.,'Approve')]")
	public WebElement APPROVEBUTTON;
	
	// SITECODES
	@FindBy(xpath = "//tr/td/div[contains(.,'Missouri')]/../../td[3]/div/a[contains(.,'Site Codes(s)')]")
	public WebElement SITECODES;
	
	@FindBy(xpath = "//h1[contains(.,'Configure Site Codes')]")
	public WebElement CONFIGURESITECODDESTEXT;

	@FindBy(xpath = "//input[@id='find-sites']")
	public WebElement SITECODESSEARCH;

	@FindBy(xpath = "//input[@value='jurisCode']")
	public WebElement JURISDICTIONCODERADIO;

	@FindBy(xpath = "//input[@value='jurisdictionName']")
	public WebElement JURISDICTIONRADIO;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Jurisdiction Code')]/../th[contains(.,'Site Code')]")
	public WebElement SITECODETABLE;
	
	@FindBy(xpath = "//p/a[contains(.,'Save')]")
	public WebElement SAVE;
	
	@FindBy(xpath = "//p/a[contains(.,'Cancel')]")
	public WebElement CANCEL;
	
	@FindBy(xpath = "//input[@ng-model='sc.textValue']")
	public WebElement SITECODEVALUE;
	
	@FindBy(xpath = "//label[contains(.,'Branch ID')]/../input[@type='text']")
	public WebElement BRANCHIDVALUE;
	
	@FindBy(xpath = "//div/h3[contains(.,'Warning!')]")
	public WebElement WARNINGPOPUP;
	
	@FindBy(xpath = "//p/a[contains(.,'Ok')]")
	public WebElement NOACCEPT;
	
	@FindBy(xpath = "//span/select[@ng-model='data.pageSize']")
	public WebElement PAGESIZEDROPDOWN;

	@FindBy(xpath = "//div/h3[contains(.,'Conditional Rules')]")
	public WebElement CONDITIONRULEHEADERTEXT;

	@FindBy(xpath = "//div/h4[contains(.,'Select Conditional Rules')]")
	public WebElement CONDITIONRULESUBHEADERTEXT;

	@FindBy(xpath = "//span/a[@id='colorado-site-codes']")
	public WebElement COBRANCHNUMBERS;

	@FindBy(xpath = "//P[contains(.,'Colorado Branch IDs')]")
	public WebElement COBranchHeaders;	

	@FindBy(xpath = "//button[@ng-click='vm.cancel()']")
	public WebElement BRANCHIDCANCEL;

	@FindBy(xpath = "//button[@ng-click='vm.save()']")
	public WebElement BRANCHIDSAVE;

	@FindBy(xpath = "//h1[contains(.,'Configure Branch IDs')]")
	public WebElement CONFIGUREBRANCHNOSTEXT;

	@FindBy(xpath = "//p/a[@id='remove-jurisdiction']")
	public WebElement REMOVEJURISDICTION;

	@FindBy(xpath = "//p/a[@id='cancel-remove-jurisdiction']")
	public WebElement CANCELREMOVEJURISDICTION;

	// Sitecodes updated
	@FindBy(xpath = "//span/a[@id='missouri-site-codes']")
	public WebElement MOSITECODES;

	@FindBy(xpath = "//a[contains(.,'Branch IDs')]")
	public WebElement COBRANCHIDS;

	@FindBy(xpath = "//label[contains(.,'Site Code')]/../input[@type='text']")
	public WebElement ADDSITECODEVALUE;

	@FindBy(xpath = "//input[@name='firstname']")
	public WebElement firstname;
	
	@FindBy(xpath = "//input[@name='lastname']")
	public WebElement lastname;

	@FindBy(xpath="//div[contains(@class,'config-form')]//div[2]//div[1]//div[1]//span[1]")
	public WebElement lastnameValidation;

	@FindBy(xpath="//input[@type='checkbox']")
	public WebElement Utilities207Checkbox;

	@FindBys({

		@FindBy(xpath="//span[contains(.,'First Name and Last Name combined has exceeded the limit of 24 characters')]")
	})
	public List<WebElement> firstlastnamevalidationmsg;

	@FindBy(xpath = "//a[contains(.,'Ready to Save')]")
	public WebElement ReadtSave;

	@FindBy(xpath = "//a[contains(.,'Upload Multiple')]")
	public WebElement UploadMultiplesitecodes;

	@FindBy(xpath = "//a[contains(.,'View Upload History')]")
	public WebElement ViewUploadHistorysitecodes;

	@FindBy(xpath = "//p[contains(.,'Upload Multiple Branch IDs')]")
	public WebElement uploadmultiplebranchIDtext;

	@FindBy(xpath = "//p[contains(.,'Upload Multiple Site Codes')]")
	public WebElement uploadmultipleSitecodestext;

	@FindBy(xpath = "//strong[contains(.,'Help Center')]")
	public WebElement HelpCentersitecodes;

	@FindBy(xpath = "//strong[contains(.,'File Format Template')]")
	public WebElement Fileformat;

	@FindBy(xpath = "//label[contains(.,'Browse')]")
	public WebElement Browse;

	@FindBy(xpath = "/html/body/div[5]/div/div/button[1]")
	public WebElement Uploadfilebtn;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	public WebElement Cancelbtn;

	@FindBy(xpath = "//h1[contains(.,'Manage Colorado Files')]")
	public WebElement managecoheader;

	@FindBy(xpath = "//h1[contains(.,'Manage Missouri Files')]")
	public WebElement manageMOheader;

	@FindBy(xpath = "//div[@class='alert alert-error ng-binding']")
	public WebElement UploadnoBranchID;

	@FindBy(xpath = "//a[contains(.,'Upload Multiple')]")
	public WebElement UploadMultiplelink;

	@FindBy(xpath = "//a[@class='btn pull-right ng-binding']/../a[contains(.,'View Colorado Branch IDs')]")
	public WebElement ViewCOBranchIDslink;

	@FindBy(xpath = "//a[@class='btn pull-right ng-binding']/../a[contains(.,'View Missouri Site Codes')]")
	public WebElement ViewMOSitecodeslink;	

	@FindBy(xpath = "//span[contains(.,'Date Added')]")
	public WebElement DateAddedColumn;

	@FindBy(xpath = "//span[contains(.,'Branch IDs File Name')]")
	public WebElement BranchIDsFilename;

	@FindBy(xpath = "//span[contains(.,'Site Codes File Name')]")
	public WebElement SitecodesFilename;

	@FindBy(xpath = "//th[contains(.,'Original File')]")
	public WebElement Orgfile;

	@FindBy(xpath = "//th[contains(.,'Success Report')]")
	public WebElement Successfile;

	@FindBy(xpath = "//th[contains(.,'Error Report')]")
	public WebElement Errorfile;

	@FindBy(xpath = "//i[contains(.,' Upload in progress ...')]")
	public WebElement uploadinprogess;

	@FindBy(xpath = "//pagination-extension/span")
	public WebElement pagination;

	@FindBy(xpath = "//pagination-extension/span/select")
	public WebElement paginationoptions;	

	@FindBy(xpath = "//span[contains(.,'My Enterprise')]")
	public WebElement OMyenterprise;

	@FindBy(xpath = "//a[contains(.,'Taxpayers')]")
	public WebElement OTaxpayers;

	@FindBy(xpath = "//input[@id='taxpayerCode_input']")
	public WebElement taxpayerCode_input;

	@FindBy(xpath = "//input[@id='taxpayerName_input']")
	public WebElement taxpayerName_input;

	@FindBy(xpath = "//button[@id='search_button']")
	public WebElement search_button;

	@FindBy(xpath = "//div[@ref='eCenterContainer']/div/div[@col-id='taxpayerName']/span/span[4]")
	public WebElement OTaxpayername;

	@FindBy(xpath = "//div[@ref='eCenterContainer']/div/div[@col-id='taxpayerCode']")
	public WebElement OTaxpayerCode;

	@FindBy(xpath="//button[contains(.,'Show Actions')]")
	public WebElement oTaxpayeractions;

	@FindBy(xpath="//a[contains(text(),'View')]")
	public WebElement Viewlink;

	@FindBy(xpath="//button[@id='registrationsTab']")
	public WebElement oregistrations;

	@FindBy(xpath="//div[@id='jurisdictionSelectorOptions_table']/div/div/div/div/div[3]/div[2]/div/div/div/div/span/span[4]/..//span[contains(.,'UNITED STATES (Selected States, Selected Exclusions)')]")
	public WebElement usalink;

	@FindBy(xpath="//div[@id='jurisdictionSelectorOptions_table']/div/div/div/div/div[3]/div[2]/div/div/div/div/span/span[4]/..//span[contains(.,'CANADA (Country Excluded, Selected States)')]")
	public WebElement canadalink;
	
	@FindBy(xpath="//div[@id='jurisdictionSelectorOptions_table']/div/div/div/div/div[3]/div[2]/div/div/div/div/span/span[contains(.,'UNITED STATES (Selected States, Selected Exclusions)')]/../../span")
	public WebElement oregistrationsplus;

	@FindBy(xpath="//button[@id='locationsTab']")
	public WebElement oLocations;

	@FindBy(xpath="//button[@id='tax-setup']/span")
	public WebElement taxsetup;

	@FindBy(xpath="//button[contains(.,'Tax Assist')]")
	public WebElement taxassist;

	@FindBy(xpath="//a[contains(text(),'Pre-Calculation')]")
	public WebElement Precalculation;

	@FindBy(xpath="//button[@id='my-enterprise/taxability-driver-management']")
	public WebElement OTaxbilityDriver;

	@FindBy(xpath="//a[contains(.,'Taxability Drivers')]")
	public WebElement OTaxbilityDriverslink;

	@FindBy(xpath="//h1[contains(.,'Taxability Drivers')]")
	public WebElement OTaxbilityDriversheader;	

	@FindBy(xpath="//input[@id='name_input']")
	public WebElement Prodnameinput;	

	@FindBy(xpath="//input[@id='global-settings__as-of-date']")
	public WebElement OAsofDate;	

	@FindBy(xpath="//div[@ref='eCenterContainer']/div/div[@col-id='taxabilityDriverCode']")
	public WebElement taxabilityDriverCode;	

	@FindBy(xpath="//div[@ref='eCenterContainer']/div/div[@col-id='name']")
	public WebElement taxabilityDrivername;	

	@FindBy(xpath="//div[@ref='eCenterContainer']/div/div[@col-id='taxpayerName']")
	public WebElement mappingtaxpayerName;	

	@FindBy(xpath="//div[@ref='eCenterContainer']/div/div[@col-id='effectiveDate']")
	public WebElement effectiveDate;	

	@FindBy(xpath="//span[contains(.,'Tax Setup')]")
	public WebElement TaxSetup;

	@FindBy(xpath="	//a[contains(text(),'Flexible Fields')]")
	public WebElement Flexiblefields;

	@FindBy(xpath="//h1[contains(.,'Flexible Fields')]")
	public WebElement OFFHeader;	

	@FindBy(xpath="//button[contains(.,'Next')]")
	public WebElement Nextpage;	

	@FindBy(xpath="	//a[contains(text(),'Taxability Categories')]")
	public WebElement TaxabilityCategories;

	@FindBy(xpath="//input[@id='Name_input']")
	public WebElement categoryname;	

	@FindBy(xpath="//a[contains(text(),'Customers')]")
	public WebElement OCustomers;

	@FindBy(xpath="//a[contains(text(),'Certificates')]")
	public WebElement Certificates;

	@FindBy(xpath="//h1[contains(.,'Certificates')]")
	public WebElement OcertiHeader;	

	@FindBy(xpath="//h1[contains(.,'View Certificate')]")
	public WebElement ViewCertificate;	

	@FindBy(xpath="//th[contains(text(),'Certificate Class')]/../td")
	public WebElement Certificatetype1;

	@FindBy(xpath="//th[contains(text(),'Certificate Tax Result')]/../td")
	public WebElement Taxresult;

	@FindBy(xpath="//th[contains(text(),'Customer')]/../td")
	public WebElement Customername;	

	@FindBy(xpath="//th[contains(text(),'Certificate Scope')]/../td")
	public WebElement Certificatescope1;

	@FindBy(xpath="//th[contains(text(),'Start Date')]/../td")
	public WebElement statedate;	

	@FindBy(xpath="//th[contains(text(),'End Date')]/../td")
	public WebElement CertendDate;

	@FindBy(xpath="//th[contains(text(),'End Date')]/../td[contains(.,'No End Date')]")
	public WebElement CertnoendDate;

	@FindBy(xpath="//th[contains(text(),'Certificate Number')]/../td")
	public WebElement Certnumber;	

	@FindBy(xpath="//th[contains(text(),'Reason Type')]/../td")
	public WebElement Reason;	

	@FindBy(xpath="//th[contains(text(),'Review Date')]/../td")
	public WebElement ReviewDate;	

	@FindBy(xpath="//th[contains(text(),'Expiration Date')]/../td")
	public WebElement ExpirationDate;	

	@FindBy(xpath="//th[contains(text(),'Issue Date')]/../td")
	public WebElement IssueDate;

	@FindBy(xpath="//div[@id='customerKeys_select']/div/div/div/div/input")
	public WebElement Certificateinput;

	@FindBy(xpath="//button[contains(text(),'Save & Close')  and @disabled='disabled']")
	public WebElement saveandclosebtndisabled;

	@FindBy(xpath="//p[contains(.,'Beware: if you remove this taxpayer, we will')]/strong[contains(.,'automatically remove')]/../../p[contains(.,'entities')]")
	public WebElement removefuturedatetaxpayer;

	@FindBy(xpath = "//a[contains(.,'Location Seq Number')]")
	public WebElement LOCATIONSEQNUMBER;
	
	@FindBy(xpath = "//h1[contains(.,'Configure Location Seq Number')]")
	public WebElement CONFIGURELOCATIONSEQNUMBERTEXT;
	
	@FindBy(xpath="//input[@maxlength='3']")
	public WebElement LocationSeqNumberTextBox;
	
	@FindBy(xpath = "//a[contains(.,'Zero Report')]")
	public WebElement ZEROREPORT;
	
	@FindBy(xpath = "//h1[contains(.,'Configure Zero Report')]")
	public WebElement CONFIGUREZEROREPORTTEXT;
	
	@FindBy(xpath = "//input[@id='find-sites']")
	public WebElement JURISDICTIONSEARCH;
	
	@FindBy(xpath = "//pagination-extension/span/select")
	public WebElement PAGINATIONDROPDOWN;
	
	@FindBy(xpath = "//strong[contains(.,'1 to 100 of')]")
	public WebElement PAGINATIONVALUE;
	
	@FindBy(xpath = "//input[@id='jur-name' and @disabled='disabled']")
	public WebElement JURISDICTIONNAMEDISABLED;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Sales')]/input[@type='checkbox']")
	public WebElement SALES;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Seller')]/input[@type='checkbox']")
	public WebElement SELLER;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Consumer')]/input[@type='checkbox']")
	public WebElement CONSUMER;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Rental/Lease')]/input[@type='checkbox']")
	public WebElement RENTALLEASE;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Auto CU')]/input[@type='checkbox']")
	public WebElement AUTOCU;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Auto SU')]/input[@type='checkbox']")
	public WebElement AUTOSU;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Farm Equipment CU')]/input[@type='checkbox']")
	public WebElement EQUIPMENTCU;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Farm Equipment SU')]/input[@type='checkbox']")
	public WebElement EQUIPMENTSU;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Manufacturing Rentals')]/input[@type='checkbox']")
	public WebElement MANUFACTURINGRENTALS;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Auto Rentals')]/input[@type='checkbox']")
	public WebElement AUTORENTALS;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Manufacturing CU')]/input[@type='checkbox']")
	public WebElement MANUFACTURINGCU;
	
	@FindBy(xpath = "//div[contains(.,'Zero Report - Manufacturing SU')]/input[@type='checkbox']")
	public WebElement MANUFACTURINGSU;
	
	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement SAVEBTN;
	
	@FindBy(xpath = "//div[7]/div[contains(.,' Zero Report - Auto')]/input[@type='checkbox']")
	public WebElement ZEROREPORTAUTO;
	
	@FindBy(xpath = "//div[10]/div[contains(.,'Zero Report - Farm Equipment')]/input[@type='checkbox']")
	public WebElement ZEROREPORTFARMEQUIPMENT;
	
	@FindBy(xpath = "//div[15]/div[contains(.,'Zero Report - Manufacturing')]/input[@type='checkbox']")
	public WebElement ZEROREPORTMANUFACTURING;
	
	@FindBy(xpath="//a[contains(.,'Upload Multiple')]")
	public WebElement UPLOADMULTIPLEREPORTSBTN;
	
	@FindBy(xpath="//p[contains(.,'Upload Multiple Zero Report')]")
	public WebElement UPLOADMULTIPLEZEROREPORTS;
	
	@FindBy(xpath="//p[contains(.,'Upload Multiple Location Seq Number')]")
	public WebElement UPLOADMULTIPLELOCATIONSEQNUMBER;
	
	@FindBy(xpath="//p[contains(.,'Download the file format template below to enter your state assigned codes next to the designated jurisdiction code')]")
	public WebElement UPLOADFILEMESSAGE;
	
	@FindBy(xpath="//strong[contains(.,'Help Center')]")
	public WebElement UPLOADHELPCENTERLINK;
	
	@FindBy(xpath="//strong[contains(.,'File Format Template')]")
	public WebElement FILEFORMATTEMPLATE;
	
	@FindBy(xpath="//label[contains(.,'Browse')]")
	public WebElement BROWSEBTN;
	
	@FindBy(xpath="//p[contains(.,'Upload Multiple Zero Report')]/../button[contains(.,'Upload File')]")
	public WebElement UPLOADFILE;
	
	@FindBy(xpath="//p[contains(.,'Upload Multiple Location Seq Number')]/../button[contains(.,'Upload File')]")
	public WebElement UPLOADMULTIPLELOCSEQNUMBER;
	
	@FindBy(xpath="//a[contains(.,'View Upload History')]")
	public WebElement VIEWUPLOADHISTORY;
	
	@FindBy(xpath="//h1[contains(.,'Manage Alabama Files')]")
	public WebElement MANAGEALABAMAFILES;
	
	@FindBy(xpath="//h1[contains(.,'Manage Illinois Files')]")
	public WebElement MANAGEILLINOISFILES;
	
	@FindBy(xpath="//strong[contains(.,'1 to 50 of')]")
	public WebElement FIFTYRECORDS;
	
	@FindBy(xpath="//strong[contains(.,'1 to 100 of')]")
	public WebElement HUNDREDRECORDS;
	
	@FindBy(xpath="//thead/tr[1]/th[1]/div[1]/span[contains(.,'Jurisdiction')]")
	public WebElement JURISDICTION;
	
	@FindBy(xpath="//fileupload[1]/div[contains(.,'are not supported.')]")
	public WebElement UNSUPPORTEDUPLOADFILES;
	
	@FindBy(xpath="//fileupload[1]/div[contains(.,'The selected file does not contain any data. Will not be uploaded.')]")
	public WebElement FILECONTAINNODATAMSG;
	
	@FindBy(xpath="//a[contains(.,'View Illinois Location Seq Number') and @class='btn pull-right ng-binding']")
	public WebElement ViewIllinoisLocationSeqNumber;
	
	@FindBy(xpath="//span[contains(.,'Date Added')]")
	public WebElement DateAdded;
	
	@FindBy(xpath="//span[contains(.,'Location Seq Number File Name')]")
	public WebElement SeqFilename;
	
	@FindBy(xpath="//th[contains(.,'Original File')]")
	public WebElement OriginalFile;
	
	@FindBy(xpath="//th[contains(.,'Success Report')]")
	public WebElement SuccessReport;
	
	@FindBy(xpath="//th[contains(.,'Error Report')]")
	public WebElement ErrorReport;
	
	@FindBy(xpath="//select[@id='taxcategories']")
	public WebElement taxTypes;
	
	@FindBy(xpath="//input[@type='checkbox']")
	public WebElement ZeroReportchkbox;
	
	@FindBy(xpath="//input[@id='text-setting-1']")
	public WebElement SACSettings;
	
	@FindBy(xpath="//select[@id='list-setting-2']")
	public WebElement ReportonSettings;
	
	@FindBy(xpath = "//span[@class='nav-text'][contains(.,'Visit Help Center')]")
	public WebElement Visit_Help_Center;
	
	@FindBy(xpath = "(//span[contains(.,'Vertex Community')])[1]")
	public WebElement Vertex_Community;
	
	@FindBy(xpath = "//p[@class='title'][contains(.,'Log in toVertex Community')]")
	public WebElement Log_in_toVertex_Community;
	
	@FindBy(xpath = "//input[@id='input-1']")
	public WebElement Help_Email;
	
	@FindBy(xpath = "//input[@id='input-2']")
	public WebElement Help_Password;
	
	
	
}
