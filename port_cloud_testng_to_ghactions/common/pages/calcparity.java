/*###################################################################################################
Name: calcparity.java 
Description: calcparity page objects with all xpaths
Author: Sivanagamani	
Date Created:27/08/2021
Date Modified:
Updated Comments:
#####################################################################################################*/
package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class calcparity {

	@FindBy(xpath = "//div[@class='ant-select-selector']/span[contains(text(),'Select your application...')]")
	public WebElement SELECTAPPLICATION;

	@FindBy(xpath = "//div[@class='ant-select-item-option-content']/div/span[contains(.,'Indirect Tax O Series')]")
	public WebElement SELECTINDIRECTTAXOSERIES;

	@FindBy(xpath = "//div[contains(.,'Indirect Tax O Series') and @class='product-name']")
	public WebElement INDIRECTTAXOSERIESLINK;

	@FindBy(xpath = "//a[contains(.,'Indirect Tax O Series')]")
	public WebElement INDIRECTTAXOSERIESCLOUD;

	//@FindBy(xpath = "//div[contains(.,'Indirect Tax O Series�') and @class='product-name']")
	@FindBy(xpath = "//div[contains(.,'Indirect Tax O Series') and @class='product-name']")
	public WebElement ADDRESSCLEANSINGLINK;

	@FindBy(xpath = "//img[@alt='Vertex']")
	public WebElement VERTEXLOGO;

	@FindBy(xpath = "//span[@class='ant-select-selection-item']//span[contains(text(),'Indirect Tax O Series')]")
	public WebElement INDIRECTTAXOSERIESTXT;

	@FindBy(xpath = "//button[contains(.,'MAIN MENU')]")
	public WebElement MAINMENU;
    
	@FindBy(xpath = "//div[contains(@aria-label,'User Info')]")
	public WebElement USERINFO;
	
	@FindBy(xpath = "//span[@class='user-info-log-out'][contains(.,'Log Out')]")
	public WebElement LOGOUT;
	
	@FindBy(xpath = "//button[contains(.,'My Enterprise')]")
	public WebElement MYENTERRISELINK;

	@FindBy(xpath = "//a[contains(.,'Taxpayers')]")
	public WebElement TAXAYERSLINK;

	@FindBy(xpath = "//a[contains(.,'Customers')]")
	public WebElement CUSTOMERSLINK;

	@FindBy(xpath = "//button[contains(.,'Taxability Driver Management')]")
	public WebElement TAXABILITYDRIVERMANAGEMENT;

	@FindBy(xpath = "//a[contains(.,'Taxability Drivers')]")
	public WebElement TAXABILITYDRIVERSLINK;

	@FindBy(xpath = "//a[contains(.,'Commodity Codes')]")
	public WebElement COMMODITYCODES;

	@FindBy(xpath = "//a[contains(.,'Communications Service Codes')]")
	public WebElement COMMSERVICECODESLINK;

	@FindBy(xpath = "//a[contains(.,'Certificates')]")
	public WebElement CERTIFICATESLINK;

	@FindBy(xpath = "//button[contains(.,'Certificate Management')]")
	public WebElement CERTIFICATESMGT;

	@FindBy(xpath = "//button[contains(.,'Certificate Portal')]")
	public WebElement CERTIFICATESPORTAL;

	@FindBy(xpath = "//span[@title='Supplies']")
	public WebElement SUPPLIES;

	@FindBy(xpath = "//div[contains(text(),'Procurement')]")
	public WebElement PROCUREMENT;

	// creating the taxpayer
	@FindBy(xpath = "//a[contains(text(),'Add')]")
	public WebElement ADDLINK;

	@FindBy(xpath = "//h2[contains(text(),'Add Taxpayer')]")
	public WebElement ADDTAXAYER;

	@FindBy(xpath = "//input[@id='taxpayerKey.taxpayerCode_input']")
	public WebElement TAXPAYERCODE;

	@FindBy(xpath = "//input[@name='taxpayerCode']")
	public WebElement TAXPAYERCODESEARCH;
	
	@FindBy(xpath = "//button[@id='taxpayerCode_button']")
	public WebElement TAXPAYERCODESEARCHBUTTON;
	
	@FindBy(xpath = "//button[@id='filterEntities_button']")
	public WebElement TAXPAYERFILTER;
	
	@FindBy(xpath = "//button[@id='selectEntity_button']")
	public WebElement SELECTTAXPAYERBUTTON;
	
	@FindBy(xpath = "//button[@id='search_button']")
	public WebElement TAXPAYERSEARCH;
	
	@FindBy(xpath = "//a[@data-id='edit']")
	public WebElement TAXPAYEREDIT;
	
	@FindBy(xpath = "//input[@name='taxpayerName']")
	public WebElement TAXPAYERNAME;

	@FindBy(xpath = "//button[@id='taxpayerParentName_button']")
	public WebElement PARENTTAXPAYERSEARCH;

	@FindBy(xpath = "//input[@id='taxpayerParentName_input']")
	public WebElement PARENTTAXPAYERCODE;

	@FindBy(xpath = "//button[@id='selectEntity_button']")
	public WebElement TAXPAYERSELECT;

	// Registrations
	// @FindBy(xpath =
	// "//li[contains(@class,'tabs-plus__tab-list-item')]/button[@id='registrationsTab']")
	@FindBy(xpath = "//span[contains(.,'Registrations')]")
	public WebElement REGISTRATIONSTAB;

	@FindBy(xpath = "//button[@id='viewJurisdictions_button']")
	public WebElement REGISTRATIONSSEARCH;

	@FindBy(xpath = "//span[contains(text(),'UNITED STATES')]")
	public WebElement UNITEDSTATESTEXT;

	@FindBy(xpath = "//button[contains(text(),'Select All')]")
	public WebElement SELECTALLBTN;

	@FindBy(xpath = "//button[@id='selectJurisdiction_button']")
	public WebElement SELECT;

	// Taxability Driver
	@FindBy(xpath = "//input[@id='taxpayerKey_input']")
	public WebElement DRIVERTAXPAYERCODE;
	
	@FindBy(xpath = "//div[@id='inputParameterTypeId_select']")
	public WebElement TAXDRIVERTYPE;

	// @FindBy(xpath = "//button[@id='taxpayerKey_button']//*[local-name()='svg']")
	@FindBy(xpath = "//button[@id='taxpayerKey_button']")
	public WebElement DRIVERTAXPAYERSEARCH;

	@FindBy(xpath = "//input[@id='taxpayerCode_input']")
	public WebElement DRIVERSECLECTTAXPAYERCODE;

	@FindBy(xpath = "//input[@id='taxabilityDriverCode_input']")
	public WebElement TAXABILITYCODE;

	@FindBy(xpath = "//input[@id='name_input']")
	public WebElement TAXABILITYNAME;

	@FindBy(xpath = "//div[@id='inputParameterTypeId_select']")
	public WebElement TAXABILITYTYPE;

	@FindBy(xpath = "//div[@id='taxability_select']")
	public WebElement TAXABILITY;

	@FindBy(xpath = "//div[@id='discountCategoryId_select']")
	public WebElement TAXABILITYDISCOUNT;

	@FindBy(xpath = "//input[@id='effectiveDate_datepicker']")
	public WebElement TAXABILITYSTARTDATE;

	@FindBy(xpath = "//input[@id='endDate_datepicker']")
	public WebElement TAXABILITYENDDATE;

	@FindBy(xpath = "//button[@id='save_button']")
	public WebElement SAVETAXPAYERDRIVER;
	
	@FindBy(xpath = "//a[contains(.,'Flexible Fields')]")
	public WebElement FLEXFIELDS;
	
	@FindBy(xpath = "//a[@data-id='edit']")
	public WebElement FLEXFIELDEDIT;
	
	@FindBy(xpath = "//input[@id='shortName_input']")
	public WebElement FLEXFIELDSHORTNAME;
	
	@FindBy(xpath = "//input[@id='longName_input']")
	public WebElement FLEXFIELDLONGNAME;
	
	@FindBy(xpath = "//button[@id='Save_button']")
	public WebElement FLEXFIELDSAVE;

	// TaxSetup

	@FindBy(xpath = "//button[contains(.,'Tax Setup')]")
	public WebElement TAXASETUP;

	@FindBy(xpath = "//a[contains(.,'Taxability Mapping')]")
	public WebElement TAXABILITYMAPPING;

	@FindBy(xpath = "//a[@id='add_link']")
	public WebElement TAXABILITYMAPPINGADD;

	// @FindBy(xpath =
	// "//button[@id='entitiesDisplay_button']//*[local-name()='svg']")
	@FindBy(xpath = "//button[@id='entitiesDisplay_button']")
	public WebElement TAXABILITYENTITIES;

	@FindBy(xpath = "//div[@id='Taxpayer_select']")
	public WebElement TAXABILITYTAXPAYER;

	@FindBy(xpath = "//button[@id='Search_button']")
	public WebElement TAXABILITYSEARCH;

	@FindBy(xpath = "//button[@class='btn btn--tertiary btn--sm']")
	public WebElement TAXABILITYADD;

	@FindBy(xpath = "//button[@id='selectEntity_button']")
	public WebElement TAXABILITYSELECT;

	// @FindBy(xpath =
	// "//button[@id='taxabilityCategoryKey_button']//*[local-name()='svg']")
	@FindBy(xpath = "//button[@id='taxabilityCategoryKey_button']")
	public WebElement TAXABILITYCATEGORY;
	
	@FindBy(xpath = "//input[@id='Name_input']")
	public WebElement TAXABILITYCATEGORYNAME;

	@FindBy(xpath = "//span[contains(.,'General [Code]')]/../../../../div/button")
	public WebElement GENTAXABILITYCATEGORY;

	@FindBy(xpath = "//span[contains(.,'TPP/Goods [Code]')]")
	public WebElement TPPCATEGORY;

	@FindBy(xpath = "//button[@id='selectTaxabilityCategory_button']")
	public WebElement TPPCATEGORYSELECT;

	@FindBy(xpath = "//button[@id='Save_button']")
	public WebElement TAXABILITYMAPPINGSAVE;

	@FindBy(xpath = "//button[@id='search_button']")
	public WebElement TAXMAPPINGSEARCH;

	// Locations
	// @FindBy(xpath = "//button[@id='locationsTab']")
	@FindBy(xpath = "//span[contains(.,'Locations')]")
	public WebElement LOCATIONSTAB;

	@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']")
	public WebElement ANIMATIONlINK;

	@FindBy(xpath = "//div[contains(text(),'ALABAMA')]")
	public WebElement PENNSYLVANIASTATE;

	@FindBy(xpath = "//input[@id='registrationNumber_input']")
	public WebElement REGNUMBER;

	@FindBy(xpath = "//input[@id='locationCode_input']")
	public WebElement LOCATIONCODE;

	@FindBy(xpath = "//input[@id='locationName_input']")
	public WebElement LOCATIONNAME;

	@FindBy(xpath = "//input[@id='streetAddress_input']")
	public WebElement STREETADDRESS;

	@FindBy(xpath = "//input[@id='city_input']")
	public WebElement CITYIN;

	@FindBy(xpath = "//input[@id='postalCode_input']")
	public WebElement POSTALCODE;

	@FindBy(xpath = "//button[contains(.,'Add Location')]")
	public WebElement ADDLOCATION;

	@FindBy(xpath = "//h3[contains(.,'Select Tax Area ID')]")
	public WebElement SELECTTAID;

	@FindBy(xpath = "//div/span[@class='ag-selection-checkbox']")
	public WebElement CHECKBOX;

	@FindBy(xpath = "//button[@id='selectEntity_button']")
	public WebElement SELECTLOCATION;

	@FindBy(xpath = "//button[@id='saveTaxpayerSubmitButton_button']")
	public WebElement SAVETAXPAYER;

	// Customers

	@FindBy(xpath = "//a[@id='addCustomer_link']")
	public WebElement ADDUSTOMERS;

	@FindBy(xpath = "//h2[contains(.,'Add Customer')]")
	public WebElement ADDCUSTOMERSTEXT;

	@FindBy(xpath = "//div[@id='type_select']/div/div/div[contains(.,'Customer')]")
	public WebElement CUSTOMERTYE;

	@FindBy(xpath = "//input[@id='customerKey.customerCode_input']")
	public WebElement CUSTOMERCODE;

	@FindBy(xpath = "//input[@id='customerName_input']")
	public WebElement CUSTOMERNAME;

	// @FindBy(xpath =
	// "//button[@id='customerKey.taxpayerKey_button']//*[local-name()='svg']")
	@FindBy(xpath = "//button[@id='customerKey.taxpayerKey_button']")
	public WebElement CUSTOMERTAXPAYERSERCH;

	@FindBy(xpath = "//h3[contains(.,'Select Taxpayer')]")
	public WebElement SELECTTAXPAYERTEXT;

	@FindBy(xpath = "//input[@id='taxpayerName_input']")
	public WebElement TAXPAYERNAMEIN;

	@FindBy(xpath = "//button[@id='filterEntities_button']")
	public WebElement TAXPAYERNAMEFILTER;

	@FindBy(xpath = "//div[@row-index=0]/div[@col-id='taxpayerName']/div/span[@class='ag-selection-checkbox']/span/../../../div")
	public WebElement TAXPAYERNAMECHKBOX;

	@FindBy(xpath = "//button[@id='saveCustomer_button']")
	public WebElement SELECTCUSTOMER;

	// Certificates

	@FindBy(xpath = "//a[@id='add_link']")
	public WebElement ADDCERTIFICATES;

	@FindBy(xpath = "//h2[contains(.,'Add Certificate')]")
	public WebElement ADDCERTIFICATETEXT;

	// @FindBy(xpath = "//div[@id='certificateClass_select']/div/div/div")
	@FindBy(xpath = "//div[@id='certificateClass_select']")
	public WebElement CERTIFICATECLASS;

	@FindBy(xpath = "//div[@id='certificateTaxResult_select']/div/div/div")
	public WebElement CERTIFICATERESULTCLASS;

	@FindBy(xpath = "//div[@id='certificateType_select']/div/div/div")
	public WebElement CERTIFICATETYPECLASS;

	// @FindBy(xpath =
	// "//button[@id='customerKey.customerCode_button']//*[local-name()='svg']")
	@FindBy(xpath = "//button[@id='customerKey.customerCode_button']")
	public WebElement CERTIFICATECUSTOMERSEARCH;

	@FindBy(xpath = "//h3[contains(.,'Select Customer')]")
	public WebElement SELECTCUSTOMERTEXT;

	@FindBy(xpath = "//input[@id='customerName_input']")
	public WebElement CUSTOMERNAMEIN;

	@FindBy(xpath = "//div[@row-index=0]/div[@col-id='customerKey.customerCode']/div/span[@class='ag-selection-checkbox']/span/../../../div")
	public WebElement CUSTOMERCODECHKBOX;

	@FindBy(xpath = "//button[@id='saveCertificateSubmitButton_button']")
	public WebElement SAVECERTIFICATE;

	@FindBy(xpath = "//div[@id='transactionTypes_select']/div/div/div/div/input")
	public WebElement TRANSACTIONTYPE;

	// @FindBy(xpath =
	// "//li[contains(@class,'tabs-plus__tab-list-item')]/button[@id='jurisdictionsTab']")
	@FindBy(xpath = "//span[contains(.,'Jurisdictions')]")
	public WebElement JURISDICTIONSTAB;

	@FindBy(xpath = "//button[@id='viewJurisdictions_button']")
	public WebElement JURISDICTIONSSEARCH;

	@FindBy(xpath = "//input[@id='customerKey.customerCode_input']")
	public WebElement CUSTOMERCODEIN;

	@FindBy(xpath = "//input[@id='endDate_datepicker']")
	public WebElement CERTIFICATEENDDATE;

	@FindBy(xpath = "//button[@id='search_button']")
	public WebElement CERTIFICATESEARCH;

	@FindBy(xpath = "//div[@id='transactionTypes_select']/div/div/div/div/input")
	public WebElement CERTTRANSACTIONTYPE;

	@FindBy(xpath = "//a[contains(.,'Reports')]")
	public WebElement REPORTS;

	@FindBy(xpath = "//span[contains(.,'Upload')]")
	public WebElement UPLOADDRIVERS;

	@FindBy(xpath = "//button/span[contains(.,'Download Template')]")
	public WebElement DOWNLOADTEMPLATE;

	@FindBy(xpath = "//div[@class='ant-form-item-control-input-content']/div[@class='ant-select vtx-select ant-select-single ant-select-show-arrow']/div[@class='ant-select-selector']")
	public WebElement SELECTTAXPAYER;

	@FindBy(xpath="//div[@class='ant-form-item-control-input-content']/div[@class='ant-select vtx-select ant-select-single ant-select-allow-clear ant-select-show-arrow']/div/span[@class='ant-select-selection-search']")
	public WebElement SELECTTAXPAYERTMR;

	@FindBy(xpath = "//div[@class='ant-upload-drag-container']/p/span")
	public WebElement BROWSETAXABILITYDRIVERS;

	@FindBy(xpath = "//button/span[contains(.,'Upload Drivers')]")
	public WebElement UPLOADDRIVERSBTN;

	@FindBy(xpath = "//button[contains(.,'Configuration')]")
	public WebElement CONFIGURATIONREPORTS;

	@FindBy(xpath = "//button[contains(.,'Generate Report')]")
	public WebElement GENERATEREPORT;

	@FindBy(xpath = "//span[@title='All Configuration Reports']")
	public WebElement CONFIGURATIONREPORTSDROPDOWN;

	@FindBy(xpath = "//button/span[contains(.,'Upload Mappings')]")
	public WebElement UPLOADMAPPINGSBTN;

	@FindBy(xpath = "//button[contains(.,'Tax Setup')]")
	public WebElement TAXSETUP;

	@FindBy(xpath = "//button[contains(.,'Tools')]")
	public WebElement TOOLS;

	@FindBy(xpath = "//button[contains(.,'Transaction Tester')]")
	public WebElement TRANSACTIONTESTER;

	@FindBy(xpath = "//a[contains(.,'Single Transaction')]")
	public WebElement SINGLETRANSACTION;

	@FindBy(xpath = "//div[@id='financialEvent_select']")
	public WebElement FIEVENTSELECT;

	@FindBy(xpath = "//div[@id='financialEvent_select']/div/div/div[contains(.,'Invoice')]")
	public WebElement INVOICE;

	@FindBy(xpath = "//input[@id='inheritedPropValues.sellerParticipant.companyTaxpayerCode_input']")
	public WebElement CMPTAXPAYERCODE;

	@FindBy(xpath = "//input[@id='inheritedPropValues.customerParticipant.customerCode_input']")
	public WebElement CMPCUSTOMERCODE;

	@FindBy(xpath = "//div[@id='inheritedPropValues.destination.country_select']")
	public WebElement COUNTRY;

	@FindBy(xpath = "//input[@id='inheritedPropValues.destination.state_input']")
	public WebElement STATE;

	@FindBy(xpath = "//input[@id='inheritedPropValues.destination.postalCode_input']")
	public WebElement CMPPOSTALCODE;

	@FindBy(xpath = "//div[@col-id='amounts.extendedPrice_1']/div[@class='ag-react-container']")
	public WebElement EXTENDEDPRICE;

	@FindBy(xpath = "//div[@col-id='amounts.extendedPrice_1']/div[@class='ag-react-container']/div[@class='vx-ag-cell']/input")
	public WebElement EXTENDEDPRICEINPUT;

	@FindBy(xpath = "//button[@id='calculate_button']")
	public WebElement CALCULATEBTN;

	@FindBy(xpath = "//button[@id='saveToFile_button']")
	public WebElement SAVETOFILE;

	@FindBy(xpath = "//button[contains(.,'Post to Journal')]")
	public WebElement POSTTOJOURNAL;

	@FindBy(xpath = "//span[contains(.,'Taxability Matrix')]")
	public WebElement TAXABILITYMATRIX;

	@FindBy(xpath = "//span/input[@name='country']/../../span[contains(.,'USA')]")
	public WebElement USA;

	@FindBy(xpath = "//div[@class='ant-select-selector']/div[@class='ant-select-selection-overflow']/div/div[@class='ant-select-selection-search']")
	public WebElement SELECTSTATE;

	@FindBy(xpath = "//div[@class='ant-select-selector']/div[@class='ant-select-selection-overflow']/div[@class='ant-select-selection-overflow-item ant-select-selection-overflow-item-suffix']/div/input")
	public WebElement SELECTSTATEINPUT;
	@FindBy(xpath="//span[@class='anticon anticon-caret-down ant-select-tree-switcher-icon']")
	public WebElement CARETDOWN;						 

	@FindBy(xpath = "//input[@id='startDate']")
	public WebElement SELECTDATE;

	@FindBy(xpath="//span[contains(.,'Union OSS Scheme Enabled')]")
	public WebElement UNIONOSSCHKBOX;
	
	@FindBy(xpath="//span[contains(.,'Non-Union OSS Scheme Enabled')]")
	public WebElement NONUNIONOSSCHKBOX;
	
	@FindBy(xpath = "//input[@id='euOneStopShopObjects[0].registrationCodeId_input']")
	public WebElement UNIONREGNUMBER;
	
	@FindBy(xpath="//span[contains(.,'Import OSS Scheme Enabled')]")
	public WebElement IMPORTOSSCHKBOX;
	
	@FindBy(xpath = "//input[@id='euOneStopShopObjects[2].registrationCodeId_input']")
	public WebElement IMPORTREGNUMBER;
	
	@FindBy(xpath = "//input[@id='euOneStopShopObjects[1].registrationCodeId_input']")
	public WebElement NONUNIONREGNUMBER;

	@FindBy(xpath = "//*[@id='euOneStopShopObjects[0].memberStateId_select']")
	public WebElement UNIONSTATESELECT;

	@FindBy(xpath = "//*[@id=\"euOneStopShopObjects[0].filingCurrencyCode_select\"]")
	public WebElement UNIONCURRENCYSELECT;
	
	@FindBy(xpath="//div[@class='ant-form-item-control-input-content']/div[@class='ant-select vtx-select ant-select-single ant-select-allow-clear ant-select-show-arrow']/div[@class='ant-select-selector']")
	public WebElement TMRSELECTTAXPAYER;
	
	@FindBy(xpath="//button/span[contains(.,'Generate Report')]")
	public WebElement GENERATETMREPORT;
	
	@FindBy(xpath="//button/span[contains(.,'Transaction & Compliance')]")
	public WebElement TRANSACTIONREPORT;
	
	@FindBy(xpath="//span[contains(.,'All Transaction & Compliance Reports')]")
	public WebElement ALLTRANSCOMPREPORTS;
	
	@FindBy(xpath="//div[@class='ant-select-item ant-select-item-option ant-select-item-option-grouped']/div[contains(.,'Transaction Details')]")
	public WebElement TRANSACTIONDETAILSOPTION;
	
	@FindBy(xpath="//div[@class='ant-form-item-control-input-content']/div[@data-testid='taxpayer-select']/div[@class='ant-select-selector']/span[@class='ant-select-selection-search']")
	public WebElement SELECTTPTDR;
	
	@FindBy(xpath="//div[@class='ant-form-item-control-input-content']/div[@data-automation-id='jurisdiction-selector-select']/div/span[@class='ant-select-selection-search']")
	public WebElement SELECTJURISDICTION;
	
	@FindBy(xpath="//input[@id='dayRange']")
	public WebElement STARTDATE;
	
	@FindBy(xpath="//input[@placeholder='End date']")
	public WebElement ENDDATE;
	
	@FindBy(xpath = "//a[contains(.,'Download')]")
	public WebElement REGREPORTDOWNLOAD;
	
	@FindBy(xpath="//a[contains(.,'Adjustment File')]")
	public WebElement ADJUSTMENTFILE;
	
	@FindBy(xpath="//span[contains(.,'Seller')]/../span[@class='ant-radio']")
	public WebElement SELLER;
	
	@FindBy(xpath="//span[contains(.,'Buyer')]/../span[@class='ant-radio']")
	public WebElement BUYER;
	
	@FindBy(xpath="//span[contains(.,'Canadian Transactions')]/../span[@class='ant-radio']")
	public WebElement CANADIANTRANSACTIONS;
	
	@FindBy(xpath="//div[@class='ant-form-item-control-input']/div/div[@data-automation-id='message-type-selector']")
	public WebElement MESSAGETYPE;
	
	@FindBy(xpath="//button/span[contains(.,'Upload Adjustment File')]")
	public WebElement UPLOADADJUSTMENTFILE;
	
	@FindBy(xpath="//div[@class='ant-select-item ant-select-item-option']/div[contains(.,'Invoice')]")
	public WebElement INVOICETYPE;
	
	@FindBy(xpath="//div[@title='Quotation']/div[contains(.,'Quotation')]")
	public WebElement QUOTATIONTYPE;
	
	@FindBy(xpath="//div[@title='Distribute Tax']/div")
	public WebElement DISTRIBUTETAXTYPE;
	
	@FindBy(xpath="//div[@title='Accrual']/div")
	public WebElement ACCRUALTYPE;
	
	@FindBy(xpath="//div[@title='Asset/Goods Movement']/div")
	public WebElement ASSETSGOODS;
	
	@FindBy(xpath="//div[@title='Inventory Removal']/div")
	public WebElement INVENTORYREMOVAL;
	
	@FindBy(xpath="//div[@title='Purchase Order']/div")
	public WebElement PURCHASEORDER;
	
	@FindBy(xpath="//div[@title='Distribute Tax Procurement']/div")
	public WebElement DISTRIBUTETAXPROCUREMENT;
	
	@FindBy(xpath="//div[@title='Invoice Verification']/div")
	public WebElement INVOICEVERIFICATION;
	
	@FindBy(xpath = "//button[contains(.,'Transaction & Compliance')]")
	public WebElement TRANSACTIONCOMPLIANCEREPORTS;

  @FindBy(xpath="//div[@title='Customer Exemptions']/div")
	public WebElement CUSTOMEREXEMPTIONREPORT;
  
  @FindBy(xpath="//span[@title='All States']/../span[@class='ant-select-tree-checkbox']")
  public WebElement ALLSTATES;
  
  
  //
  @FindBy(xpath = "(//span[contains(@role,'img')])[2]")
  public WebElement SETTINGSICON;
  
  @FindBy(xpath = "//a[@href='/ui/settings/users'][contains(.,'User Management')]")
  public WebElement USER_MANAGEMENT;
  
  // New UI User Management screen & Change role functionality  from client
  
  @FindBy(xpath="//div[@class='container-nav']/ul/li/a/span[text()='Settings']")
  public WebElement SETTINGS;

  @FindBy(xpath="//span[@class='nav-text'][contains(.,'Indirect Tax O Series')]")
  public WebElement INDIRECTTAXOSERIES;

  @FindBy(xpath="//div[contains(@class,'vtx-dropdown-button')]")
  public WebElement VTXDROPDOWN;

  @FindBy(xpath="//a[contains(.,'User Management')]")
  public WebElement USERMANGEMENT;

  @FindBy(xpath="//a[contains(.,'Add New User')]")
  public WebElement ADDNEWUSER;
   
  @FindBy(xpath="//a[contains(.,'Connectors')]")
  public WebElement CONNECTORS;
  
  @FindBy(xpath="//div/a[contains(text(),'View All Users')]")
  public WebElement VIEWALLUSERSLINKFROMCLIENT;
  
  @FindBy(xpath="//input[@id='Email']")
  public WebElement USEREMAIL;

  @FindBy(xpath="//select[@id='roles0']")
  public WebElement ACCOUNTMANAGEMENT;
  
  @FindBy(xpath="//select[@disabled='disabled']")
  public WebElement DISABLE_ACCOUNTMANAGEMENT;

  @FindBy(xpath="//input[@value='Select User Roles']")
  public WebElement VERTEXCLOUDINDIRECTTAXROLE;
  
  @FindBy(xpath="(//input[@value='Select User Roles'])[1]")
  public WebElement VERTEXCLOUDINDIRECTTAXROLEPART1;
  
  @FindBy(xpath="(//input[@value='Select User Roles'])[2]")
  public WebElement VERTEXCLOUDINDIRECTTAXROLEPART2;

  @FindBy(xpath = "//select[contains(@name,'roles-5')]")
  public WebElement INDIRECTTAXINTELLIGENCE;

  @FindBy(xpath="//input[@value='Send Invite']")
  public WebElement SENDINVITE;
  
  @FindBy(xpath="//button[contains(.,'Save')]")
  public WebElement CHANGEROLESAVEBUTTON;
  
  @FindBy(xpath="//a[contains(.,'Cancel')]")
  public WebElement CHANGEROLECANCELBUTTON;
  
  @FindBy(xpath="//a[contains(.,'Return to Users')]")
  public WebElement RETURNTOUSERS;
  
  @FindBy(xpath="//div/p[contains(.,'a direct link to continue registration')]/a")
  public WebElement COMPLETEREGISTATIONLINK;

  @FindBy(xpath="//input[@id='FirstName']")
  public WebElement FIRSTNAME;

  @FindBy(xpath="//input[@id='LastName']")
  public WebElement LASTNAME;

  @FindBy(xpath="//input[@id='UserPhone']")
  public WebElement PHONENUMBER;

  @FindBy(xpath="//input[@id='Password']")
  public WebElement PASSWORD;

  @FindBy(xpath="//input[@id='ConfirmPassword']")
  public WebElement CONFIRMPASSWORD;

  @FindBy(xpath="//button[contains(.,'Complete Registration')]")
  public WebElement PROFILECOMP_REG;

  @FindBy(xpath="//a[contains(.,'Login')]")
  public WebElement LOGIN;
  
  @FindBy(xpath="//h3[contains(.,'Are you sure you want to delete this user?')]")
  public WebElement DELETEHEADERTEXT;
  
  @FindBy(xpath="//strong[contains(.,'Cancel')]")
  public WebElement CANCELLINK;
  
  @FindBy(xpath="//a[contains(@id,'confirm-remove-customer')]")
  public WebElement REMOVECUSTOMERLINK;
  
  @FindBy(xpath="//a[contains(.,'Create New User')]")
  public WebElement CREATENEWUSERLINK;
  
  //Delete user functionality from AdminPortal
  
  @FindBy(xpath="//span[contains(text(),'Manage Customers')]")
  public WebElement SMBCUSTOMERSTAB;
  
  @FindBy(xpath="//label[contains(text(),'Cloud Customer Name')]/../input")
  public WebElement SMBCUSTOMERNAME;
  
  @FindBy(xpath="//a[contains(@id,'confirm-remove')]")
  public WebElement REMOVEUSER;  

  //EUOSS report
  @FindBy(xpath="//input[@id='filingCurrency']")
  public WebElement OSSFILINGCURRENCY;
  
  @FindBy(xpath="//input[@placeholder='End date']")
  public WebElement OSSENDDATE;
  
  @FindBy(xpath="//input[@placeholder='Start date']")
	public WebElement OSSSTARTDATE;
	
  //verify Partitions 
  
  @FindBy(xpath="//div[@class='ant-modal-title'][contains(.,'Select a Partition')]")
  public WebElement SELECTPARTITION;
  
  @FindBy(xpath="//span[contains(@data-testid,'partition-search')]")
  public WebElement SEARCHPARTITION;
  
  @FindBy(xpath="//div[@class='partitions-list']/div[1]")
  public WebElement PARTITION1;
 
  @FindBy(xpath="//div[@class='partitions-list']/div[2]")
  public WebElement PARTITION2;
  
}
