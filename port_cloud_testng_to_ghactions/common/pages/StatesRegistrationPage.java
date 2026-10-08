/*###################################################################################################
Name: StatesRegistrationPage.java 
Description: Professional User - Configure Registration Page objects with all xpaths for PENNSYLVANIA
Author: Subrahmanyam L
Date Created:01/21/2014
Date Modified:
Updated Comments:
#####################################################################################################*/
package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StatesRegistrationPage {
	@FindBy(xpath = "//*[@id='remove-jurisdiction']")
	public WebElement RemoveJuridiction;
	@FindBy(xpath = "//label[contains(.,'Both Seller's Use  & sales tax')]")
	public WebElement BOTH;
	@FindBy(xpath = "//label[contains(.,'Sales tax')]")
	public WebElement SALES;
	@FindBy(xpath = "//label[contains(.,'Use tax')]")
	public WebElement USE;
	@FindBy(xpath = "//a[contains(., 'Add a New Location')]")
	public WebElement ADDNEWLOCATION;
	@FindBy(xpath = "//button[contains(text(),'Add Location') and @disabled='disabled']")
	public WebElement ADDNEWLOCDISABLEBTN;
	@FindBy(id = "new-location-code")
	public WebElement LOCATIONCODE;
	@FindBy(id = "new-location-name")
	public WebElement LOCATIONNAME;
	@FindBy(id = "new-location-start-date")
	public WebElement LOCATIONSTARTDATE;
	@FindBy(id = "new-location-registration-code")
	public WebElement REGISTRATIONCODE;
	@FindBy(id = "new-location-address")
	public WebElement LOCATIONADDRESS;
	@FindBy(id = "new-location-city")
	public WebElement LOCATIONCITY;
	@FindBy(id = "new-location-zip")
	public WebElement LOCATIONZIP;
	public final String LOCZIP = "//input[@id='new-location-zip']";
	@FindBy(xpath = "//select[@id='new-location-state-select']")
	public WebElement LOCATIONSTATE;
	@FindBy(xpath = "//button[@id='newAddLocation']")
	public WebElement ADDLOCATIONBUTTON;
	@FindBy(xpath = "//button[@id='newAddLocation'][@disabled='disabled']")
	public WebElement ADDLOCATIONBUTTONDISABLE;
	@FindBy(xpath = "//a[contains(text(),'Yes, confirm address information')]")
	public WebElement CONFIRMATIONPOPUP;
	@FindBy(xpath = "//a[contains(text(), 'No, edit location information')]")
	public WebElement EDITINGLOCPOPUP;
	@FindBy(xpath = "//div[@class='alert alert-error']/span[text()='Configuration save failed']")
	public WebElement ERRORTEXT;
	@FindBy(id = "locationfield0")
	public WebElement SUBROUTINE;
	@FindBy(xpath = "//input[@id='locationfield1']")
	public WebElement LOC_OUTLETNUM;
	@FindBy(xpath = "//input[@id='locationfield0']")
	public WebElement LOC_SPEC1;
	@FindBy(xpath = "//input[@id='locationfield1']")
	public WebElement LOC_SPEC2;
	@FindBy(xpath = "//input[@id='locationfield2']")
	public WebElement LOC_SPEC3;
	@FindBy(xpath = "//input[@id='locationfield3']")
	public WebElement LOC_SPEC4;
	// @FindBy(xpath="//label[contains(text(),'Three position #')]/../input")
	//
	@FindBy(xpath = "//input[@id='locationfield4']")
	public WebElement LOC_SPEC5;
	// Registration
	@FindBy(xpath = "//input[@id='registration-number']")
	public WebElement REGISTRATIONNUMBER;
	@FindBy(xpath = "//select[@id='filing-method']")
	public WebElement FILINGMETHOD;
	@FindBy(xpath = "//select[@id='filing-frequency']")
	public WebElement FILINGFREQUENCY;
	@FindBy(xpath = "//label[contains(text(),'Three position')]/../input")
	public WebElement THREEPOSITION;
	@FindBy(xpath = "//input[@id='locationfield2']")
	public WebElement THREEPOSITIONTWO;
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement SAVEBUTTON;
	// @FindBy(xpath= "//a[contains(text(),'Ready to Save')]")
	//
	@FindBy(xpath = "//div[3]/div/div/a[contains(text(),'Ready to Save')]")
	public WebElement READYTOSAVE;
	@FindBy(xpath = "//button[contains(text(),'YES TO Exit Exception')]")
	public WebElement YESTOEXITEXCEPTION;
	@FindBy(xpath = "//a[contains(text(),'Save Configuration')]")
	public WebElement SAVECONFIGURATION;
	@FindBy(xpath = "//button[contains(text(),'Yes to complete configuration')]")
	public WebElement COMPLETECONFIGPOPUP;
	@FindBy(xpath = "//a[@id='ALABAMA' and contains(text(), 'Edit')]")
	public WebElement EDITREGISTRATIONS;
	@FindBy(xpath = "//div[@class='col'and contains(.,'Registered Jurisdictions')]/span[@class='badge badge-success badge-large ng-binding']")
	public WebElement STATEREGISTRATIONCOUNT;
	@FindBy(xpath = "//div[@class='span4']/label/input")
	public WebElement AUTOMATICFILING;
	@FindBy(xpath = "//div/button[@id='continue-stateOverview2']")
	public WebElement AUTOMATICFILINGSAVE;
	// **********Required for StateJurisdictionsFunctions function********
	@FindBy(xpath = "//a[@data-bind='click: selectRegistrations']")
	public WebElement REGISTRATIONLINK;
	@FindBy(xpath = "//div[@class='col-row-static alt col-callout']/select")
	public WebElement ADDSTATES;
	@FindBy(xpath = "//button[@data-bind='click: addJurisdiction']")
	public WebElement STATESADDBUTTON;
	@FindBy(xpath = "//li/a[@title='PENNSYLVANIA']")
	public WebElement ADDEDSTATELINK;
	@FindBy(xpath = "//li/a[@title='PA Sales/Use/Hotel Tax PA-3']")
	public WebElement JURIDICTIONSPAYMENTLINK;
	@FindBy(xpath = "//div/button[@data-bind='click: $root.config().selectedTaxpayer().addRegistration']")
	public WebElement REGISTERBUTTON;
	@FindBy(xpath = "//div/ul/li[4]/select")
	public WebElement CREDITTYPE;
	@FindBy(xpath = "//div/ul/li[5]/input")
	public WebElement EDIACCESSKEY;
	@FindBy(xpath = "//div/ul/li[6]/input")
	public WebElement PAYMENTFUNDSTRANSFER;
	@FindBy(xpath = "//div/ul/li[7]/input")
	public WebElement AMENDEDRETURN;
	@FindBy(xpath = "//div/ul/li[8]/select")
	public WebElement EDIENTITYTYPE;
	// Albama
	@FindBy(xpath = "//li/a[@title='ALBAMA']")
	public WebElement AL_ADDEDSTATELINK;
	@FindBy(xpath = "//li/a[@title='AL State City & County Tax Return']")
	public WebElement AL_JURIDICTIONSPAYMENTLINK;
	@FindBy(xpath = "//div/ul/li[4]/input")
	public WebElement AL_EDIACCESSKEY;
	@FindBy(xpath = "//div/ul/li[5]/input")
	public WebElement AL_CHAINNUMBER;
	@FindBy(xpath = "//div/ul/li[6]/input")
	public WebElement AL_PAYMENTFUNDSTRANSFER;
	@FindBy(xpath = "//button[@data-bind='click: $root.commit']")
	public WebElement COMMIT;
	// Colorado
	@FindBy(xpath = "//li/a[@title='COLORADO']")
	public WebElement CO_ADDEDSTATELINK;
	@FindBy(xpath = "//div/ul/li[4]/input")
	public WebElement COM_EDIACCESSKEY;
	@FindBy(xpath = "//div/ul/li[5]/input")
	public WebElement CO_PREPARERFIRMBUSINESSADDRESSLINE2;
	@FindBy(xpath = "//div/ul/li[6]/input")
	public WebElement CO_PREPARERFIRMBUSINESSCITY;
	@FindBy(xpath = "//div/ul/li[7]/input")
	public WebElement CO_PREPARER_SSN;
	@FindBy(xpath = "//div/ul/li[8]/input")
	public WebElement CO_AMENDED_RETURN;
	@FindBy(xpath = "//div/ul/li[9]/input")
	public WebElement CO_PAYMENTVIAELECTRONICFUNDSTRANSFER;
	@FindBy(xpath = "//div/ul/li[10]/input")
	public WebElement CO_PREPARERFIRMBUSINESSZIPCODE;
	@FindBy(xpath = "//div/ul/li[11]/input")
	public WebElement CO_EFT_ACCOUNTNUMBER;
	@FindBy(xpath = "//div/ul/li[12]/input")
	public WebElement CO_PREPARERFIRMBUSINESSADDRESSLINE1;
	@FindBy(xpath = "//div/ul/li[13]/input")
	public WebElement CO_ACKNOWLEDGEMENT_EMAIL;
	@FindBy(xpath = "//div/ul/li[14]/input")
	public WebElement CO_OTHERDEDUCTION_EXPLANATION;
	@FindBy(xpath = "//div/ul/li[15]/input")
	public WebElement CO_PREPARERFIRMBUSINESSNAME;
	@FindBy(xpath = "//div/ul/li[16]/input")
	public WebElement CO_CONTACT_PHONE;
	@FindBy(xpath = "//div/ul/li[17]/input")
	public WebElement CO_PREPARERFIRMBUSINESSSTATE;
	@FindBy(xpath = "//div/ul/li[18]/input")
	public WebElement CO_CONTACT_NAME;
	@FindBy(xpath = "//div/ul/li[19]/input")
	public WebElement CO_OTHEREXEMPTION_EXPLANATION;
	@FindBy(xpath = "//div/ul/li[20]/input")
	public WebElement CO_CONTACT_EMAIL;
	// Arkansas
	@FindBy(xpath = "//div/ul/li[5]/select")
	public WebElement AR_PaymentbyEFT;
	@FindBy(xpath = "//div/ul/li[6]/input")
	public WebElement AR_Amended_Return;
	// CALIFORNIA
	@FindBy(xpath = "//div/ul/li[4]/select")
	public WebElement CAL_FISCALYEARSTART;
	@FindBy(xpath = "//div/ul/li[5]/select")
	public WebElement CAL_TAXABLEACTIVITYTYPE;
	@FindBy(xpath = "//div/ul/li[6]/select")
	public WebElement CAL_TAXABLEACTIVITYINDICATOR;
	@FindBy(xpath = "//div/ul/li[7]/input")
	public WebElement CAL_OPTION2;
	@FindBy(xpath = "//div/ul/li[8]/input")
	public WebElement CAL_BUSINESSADDRESS;
	@FindBy(xpath = "//div/ul/li[9]/input")
	public WebElement CAL_OPTION3PREPAY;
	@FindBy(xpath = "//div/ul/li[10]/input")
	public WebElement CAL_OPTION1;
	@FindBy(xpath = "//div/ul/li[11]/input")
	public WebElement CAL_PAYBYCREDITCARD;
	@FindBy(xpath = "//div/ul/li[12]/input")
	public WebElement CAL_BOARDOFFICEINDI;
	@FindBy(xpath = "//div/ul/li[13]/input")
	public WebElement CAL_FOID;
	// Sec Jurisdiction
	@FindBy(xpath = "//div/ul/li[7]/select")
	public WebElement CAL_J2_RETURNBATCHCODE;
	@FindBy(xpath = "//div/ul/li[11]/input")
	public WebElement CAL_J2_BUSINESSADDRESS;
	@FindBy(xpath = "//div/ul/li[8]/select")
	public WebElement CAL_J2_PAYMENTBYEFT;
	@FindBy(xpath = "//div/ul/li[9]/input")
	public WebElement CAL_J2_OTHERDEDUCTIONS;
	@FindBy(xpath = "//div/ul/li[10]/input")
	public WebElement CAL_J2_FOID;
	@FindBy(xpath = "//div/ul/li[13]/input")
	public WebElement CAL_J2_BUSINESSCODE;
	@FindBy(xpath = "//div/ul/li[14]/input")
	public WebElement CAL_J2_LOCATIONCODE;
	@FindBy(xpath = "//div/ul/li[15]/input")
	public WebElement CAL_J2_INDUSTRYCODE;
	// Third Jurisdiction
	@FindBy(xpath = "//div/ul/li[9]/input")
	public WebElement CAL_J3_BUSINESSADDRESS;
	@FindBy(xpath = "//div/ul/li[7]/select")
	public WebElement CAL_J3_PAYMENTBYEFT;
	@FindBy(xpath = "//div/ul/li[8]/input")
	public WebElement CAL_J3_OTHERDEDUCTIONS;
	@FindBy(xpath = "//div/ul/li[11]/input")
	public WebElement CAL_J3_BOARDOFFICEINDI;
	@FindBy(xpath = "//div/ul/li[12]/input")
	public WebElement CAL_J3_BUSINESSCODE;
	@FindBy(xpath = "//div/ul/li[13]/input")
	public WebElement CAL_J3_LOCATIONCODE;
	@FindBy(xpath = "//div/ul/li[14]/input")
	public WebElement CAL_J3_INDUSTRYCODE;
	// DISTRICT OF COLUMBIA
	// *********************************************VENKAT ADDED
	// RHODE ISLAND and South Dakota and Tennessee
	@FindBy(xpath = "//li[4]/input")
	public WebElement RI_EMAILADDRESS;
	@FindBy(xpath = "//li[5]/input")
	public WebElement RI_NAICSCODE;
	@FindBy(xpath = "//li[4]/select")
	public WebElement DATALOCATED;
	@FindBy(xpath = "//li[5]/select")
	public WebElement NONDATALOCATED;
	@FindBy(xpath = "//li[6]/input")
	public WebElement BUSINESSCLASS;
	@FindBy(xpath = "//li[7]/input")
	public WebElement FILECODE;
	@FindBy(xpath = "//li[8]/input")
	public WebElement ACCESSKEY;
	@FindBy(xpath = "//li[6]/input")
	public WebElement TE_ACCESSKEY;
	@FindBy(xpath = "//li[7]/input")
	public WebElement RATEFILTER;
	@FindBy(xpath = "//li[8]/input")
	public WebElement AUTHORIZATIONNO;
	@FindBy(xpath = "//li[9]/input")
	public WebElement AMENDEDNONRETURN;
	@FindBy(xpath = "//li[10]/input")
	public WebElement TE_AMENDEDRETURN;
	// SOUTH CAROLINA
	@FindBy(xpath = "//li[4]/select")
	public WebElement SC_FISCALYEAR;
	@FindBy(xpath = "//li[5]/select")
	public WebElement SC_DATALOCATED;
	@FindBy(xpath = "//li[6]/select")
	public WebElement SC_NONDATALOCATED;
	@FindBy(xpath = "//li[7]/input")
	public WebElement SC_EDIACCESSKEY;
	@FindBy(xpath = "//li[8]/select")
	public WebElement SC_ROUNDING;
	@FindBy(xpath = "//input[@type='checkbox']")
	public WebElement ACHTRANSACTION;
	@FindBy(xpath = "//li[10]/input")
	public WebElement EMAILADDRESS;
	@FindBy(xpath = "//li[11]/input")
	public WebElement DATEBUSINESSOPENED;
	@FindBy(xpath = "//li[12]/input")
	public WebElement SID;
	@FindBy(xpath = "//li[9]/input")
	public WebElement SC_EMAILADDRESS;
	@FindBy(xpath = "//li[10]/input")
	public WebElement SC_DATEBUSINESSOPENED;
	@FindBy(xpath = "//li[11]/input")
	public WebElement SC_SID;
	// TEXAS and IOWA
	@FindBy(xpath = "//li[4]/input")
	public WebElement TI_ACCESSKEY;
	@FindBy(xpath = "//li[5]/input")
	public WebElement REDUCEDTAXES;
	@FindBy(xpath = "//li[6]/input")
	public WebElement EXPORTEDOUTSIDE;
	@FindBy(xpath = "//li[7]/input")
	public WebElement T_AMENDEDRETURN;
	@FindBy(xpath = "//li[8]/input")
	public WebElement LOCATIONCLOSED;
	@FindBy(xpath = "//li[9]/input")
	public WebElement ALWAYSSHOWSLOCATION;
	@FindBy(xpath = "//li[10]/input")
	public WebElement OUTOFBUSINESS;
	@FindBy(xpath = "//li[11]/input")
	public WebElement PURCHASEDATEA;
	@FindBy(xpath = "//li[12]/input")
	public WebElement FUNDSTRANSFER;
	@FindBy(xpath = "//li[13]/input")
	public WebElement MAILINGADDRESS;
	@FindBy(xpath = "//li[14]/input")
	public WebElement BUSINESSDATE;
	@FindBy(xpath = "//li[15]/input")
	public WebElement EDIMAILINGADDRESS;
	@FindBy(xpath = "//li[16]/input")
	public WebElement EDITAXPAYER;
	@FindBy(xpath = "//li[8]/input")
	public WebElement T_PURCHASEDATEA;
	@FindBy(xpath = "//li[9]/input")
	public WebElement T_BUSINESSDATE;
	@FindBy(xpath = "//li[10]/input")
	public WebElement T_ALWAYSSHOWSLOCATION;
	@FindBy(xpath = "//li[11]/input")
	public WebElement T_OUTOFBUSINESS;
	@FindBy(xpath = "//li[12]/input")
	public WebElement T_EDIMAILINGADDRESS;
	@FindBy(xpath = "//li[13]/input")
	public WebElement T_EDITAXPAYER;
	@FindBy(xpath = "//li[14]/input")
	public WebElement T_LOCATIONCLOSED;
	@FindBy(xpath = "//li[15]/input")
	public WebElement T_MAILINGADDRESS;
	@FindBy(xpath = "//li[16]/input")
	public WebElement T_FUNDSTRANSFER;
	@FindBy(xpath = "//li[4]/select")
	public WebElement T_DATALOCATED;
	@FindBy(xpath = "//li[5]/select")
	public WebElement T_NONDATALOCATED;
	@FindBy(xpath = "//li[6]/input")
	public WebElement OUTLETNUMBER;
	// IOWA
	@FindBy(xpath = "//li[5]/select")
	public WebElement PAYMENTMETHOD;
	@FindBy(xpath = "//li[6]/input")
	public WebElement BENNUMBER;
	@FindBy(xpath = "//li[5]/input")
	public WebElement I_AMENDEDRETURN;
	@FindBy(xpath = "//li[6]/select")
	public WebElement I_PAYMENTMETHOD;
	@FindBy(xpath = "//li[7]/select")
	public WebElement TAXCREDITMETHOD;
	@FindBy(xpath = "//li[8]/select")
	public WebElement PREPAIDE911;
	@FindBy(xpath = "//li[9]/input")
	public WebElement I_BENNUMBER;
	@FindBy(xpath = "//li[4]/select")
	public WebElement NONLOCATEDDATA;
	@FindBy(xpath = "//li[5]/input")
	public WebElement I_ACCESSKEY;
	@FindBy(xpath = "//li[6]/input")
	public WebElement I1AMENDEDRETURN;
	@FindBy(xpath = "//li[7]/select")
	public WebElement I1_PAYMENTMETHOD;
	@FindBy(xpath = "//li[8]/select")
	public WebElement I_TAXCREDITMETHOD;
	@FindBy(xpath = "//li[9]/select")
	public WebElement I_PREPAIDE911;
	@FindBy(xpath = "//li[10]/select")
	public WebElement LOCATIONPERMIT;
	@FindBy(xpath = "//li[11]/input")
	public WebElement I1_BENNUMBER;
	// **********EPIC4-Edit Company After Push OS**********
	@FindBy(xpath = "//h3/span/a")
	public WebElement EDITSTATELINK;
	@FindBy(xpath = "//div/ul/li[@ng-repeat='location in data.locations']/a/i[@class='icon icon-ok']")
	public WebElement EDITLOCATION;
	@FindBy(xpath = "//button[contains(text(),'Save Location')]")
	public WebElement SAVELOCATIONBUTTON;
	@FindBy(xpath = "//div/ul/li[@ng-repeat='return in data.filteredreturns | filter:{returnLevelId: 1, returnTypeId: 1}']/a/i[@class='icon icon-ok']")
	public WebElement EDITSTATEFORM;
	@FindBy(xpath = "//a[@class='btn pull-right' and contains(text(),'Return to company overview')]")
	public WebElement RETURNTOCOMPOVERVIEW;
	@FindBy(xpath = "//a[@class='btn' and contains(text(),'Return to Company Overview')]")
	public WebElement RETURNTOCOMPOVERVIEWDOWN;
	@FindBy(xpath = "//a[@class='btn pull-right' and contains(text(),'Return to Configuration Overview')]")
	public WebElement RETURNTOCONFIGOVERVIEW;
	@FindBy(id = "locationfield0")
	public WebElement PAYMENTVIAEFT;
	@FindBy(xpath = "//button[@disabled='disabled']")
	public WebElement SAVEDISABLED;
	@FindBy(xpath = "//a[@class='btn pull-right']")
	public WebElement RETURNTOREG;
	@FindBy(xpath = "//a[@class='btn pull-right']")
	public WebElement EXITREG;
	// Canada Locations
	@FindBy(xpath = "//select[@id='Select1']")
	public WebElement PROVINCE;
	// Canada Jurisdictions
	@FindBy(xpath = "//label[contains(.,' Business Number')]/../input")
	public WebElement BUSINESSNO;
	@FindBy(xpath = "//input[@id='locationfield1']")
	public WebElement FILE;
	@FindBy(xpath = "//label[contains(.,'Quebec Enterprise Number (NEQ)')]/../input")
	public WebElement QUEBECNO;
	@FindBy(xpath = "//select[@id='locationfield0']")
	public WebElement LOCDATA;
	@FindBy(xpath = "//select[@id='locationfield1']")
	public WebElement NONLOCDATA;
	@FindBy(xpath = "//select[@id='locationfield2']")
	public WebElement STATEEFILE;
	@FindBy(xpath = "//select[@id='locationfield2']")
	public WebElement ALDOR;
	@FindBy(xpath = "//input[@id='locationfield0']")
	public WebElement FIELD1;
	@FindBy(xpath = "//input[@id='locationfield1']")
	public WebElement FIELD2;
	
	@FindBy(xpath = "//select[@id='locationfield1']")
	public WebElement FIELDFILING;
	
	
	@FindBy(xpath = "//input[@id='locationfield2']")
	public WebElement FIELD3;
	@FindBy(xpath = "//input[@id='locationfield3']")
	public WebElement FIELD4;
	@FindBy(xpath = "//input[@id='locationfield4']")
	public WebElement FIELD5;
	@FindBy(xpath = "//input[@id='locationfield5']")
	public WebElement FIELD6;
	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	public WebElement CONTINUE;
	@FindBy(xpath = "//button[contains(text(),'Begin Removal')]")
	public WebElement BEGINREMOVAL;
	@FindBy(xpath = "//a[@id='confirm-remove-customer']")
	public WebElement CONFIRMREMOVECUSTOMER;
	@FindBy(xpath = "//input[@id='remove-customer-end-date']")
	public WebElement CUSTOMERENDDATE;
	@FindBy(xpath = "//button[text()='Remove']")
	public WebElement REMOVE;
	@FindBy(xpath = "//label[contains(.,'Automate Retail B&O')]/../select")
	public WebElement AUTOMATIVERETAIL;
	@FindBy(xpath = "//label[contains(.,'Automate Services B&O')]/../select")
	public WebElement AUTOMATIVESERVICE;
	@FindBy(xpath = "//label[contains(.,'Automate Wholesaling B&O')]/../select")
	public WebElement AUTOMATIVEWHOLESELLING;
	@FindBy(xpath = "//div/a[@class='btn pull-right'][contains(text(),'Return to company overview')]")
	public WebElement returnToCompanyOverView;
	@FindBy(xpath = "//label[2]")
	public WebElement canadaLabel;
	@FindBy(xpath = "//label[@for='canada-checkbox']")
	public WebElement FIRSTROWCANADA;
	@FindBy(xpath = "//strong[contains(.,'Canada')]/../div/div/input")
	public WebElement CANADAON;
	@FindBy(xpath = "//input[@id='united-states-switch']")
	public WebElement USLabel;
	@FindBy(xpath = "//label[@for='alabama-checkbox']")
	public WebElement FIRSTROWUS;
	@FindBy(xpath = "//label[contains(.,'United States & Territories')]")
	public WebElement USterritoriesLabel;
	// Advanced Settings
	@FindBy(xpath = "//label[contains(.,'Cities')]/../../div/a")
	public WebElement CITYEDIT;
	@FindBy(xpath = "//label[contains(.,'Cities')]/../../div[@class='span3 min-span ng-hide']/a")
	public WebElement CITYNOEDIT;
	@FindBy(xpath = "//label[contains(.,'Cities')]/../../div[@class='span3 min-span']/a")
	public WebElement CITYEDITALL;
	@FindBy(xpath = "//label[contains(.,'Counties')]/../../div/a")
	public WebElement COUNTIESEDIT;
	@FindBy(xpath = "//label[contains(.,'Districts')]/../../div/a")
	public WebElement DISTRICTSEDIT;
	@FindBy(xpath = "//button[@id='save-cities']")
	public WebElement SAVECITI;
	@FindBy(xpath = "//label[contains(.,'Cities')]/input")
	public WebElement ALLCITI;
	@FindBy(xpath = "//button[@id='save-counties']")
	public WebElement SAVECOUNTI;
	@FindBy(xpath = "//button[@id='save-districts']")
	public WebElement SAVEDIST;
	@FindBy(xpath = "//label[contains(.,'Company Start Date')]/input")
	public WebElement COMPSTRTDTE;
	@FindBy(xpath = "//button[@id='save-jurisdiction']")
	public WebElement SVEJURS;
	@FindBy(xpath = "//table/tbody/tr/td/label/input")
	public WebElement JURSR1;
	@FindBy(xpath = "//h2[contains(text(),'Where would you like to register?')]")
	public WebElement REGTEXT;
	@FindBy(xpath = "//input[@id='quick-search']")
	public WebElement STQUICKSRCH;
	@FindBy(xpath = "//a[@id='remove-jurisdiction']")
	public WebElement RMVEJUR;
	/*
	 * @FindBy(xpath="//a[@id='remove-jurisdiction']") public WebElement REMOVEJUR;
	 */
	@FindBy(xpath = "//div[@class='span11 offset1 ng-binding']/../../../p/a[@id='remove-jurisdiction']")
	public WebElement RMVEJURDATE;
	@FindBy(xpath = "//a[@id='add-jurisdiction']")
	public WebElement ENABLEJUR;
	@FindBy(xpath = "//input[@id='start-date-company-start']")
	public WebElement ENBLEJURCOMPSTRDDTE;
	public final String FILING_METHOD = "//select[@id='filing-method']/option";
	public final String REMOVEJUR_XPATH = "//a[@id='remove-jurisdiction']";
	// occasional filling changes--6.3
	@FindBy(xpath = "//h5[contains(.,' When selecting an occasional filing frequency, your return will be generated when your tax liability is greater than $.01.')]")
	public WebElement OccasioalWarning;
	@FindBy(xpath = "//button[contains(.,'Continue')]")
	public WebElement Continuebutton;
	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement Cancelbutton;
	@FindBy(xpath = "//label[contains(.,'International')]")
	public WebElement INTERNATIONAL;
	@FindBy(xpath = "//input[@name='vat']")
	public WebElement VATNUMBER;
	@FindBy(xpath = "//select[@class='ng-pristine ng-untouched ng-valid']")
	public WebElement INTERNATIONALPAGINATION;
	@FindBy(xpath = "//table/tbody/tr")
	public WebElement ROWSVERIFICATION;
	@FindBy(xpath = "//a[text()='3']")
	public WebElement PAGE3;
	@FindBy(xpath = "//label[2]/input[@ng-model='country.isImportReverseCharge'][1]")
	public WebElement ImportReverseChargeCheckbox;
	@FindBy(xpath = "//label/input[@ng-model='country.isPhysicalPresence'][1]")
	public WebElement PhysicalPresenceCheckbox;
	// ----Prepayment-----//
	@FindBy(xpath = "//h5[contains(.,'Prepayment Details')]")
	public WebElement Prepaymentdetails;
	@FindBy(xpath = "//label[contains(.,'Prepayment Amount')]")
	public WebElement Prepaymentamountlabel;
	@FindBy(xpath = "//select[@id='prepayment-type']")
	public WebElement Prepaymenttype;
	@FindBy(xpath = "//input[@id='prepayment-amount']")
	public WebElement Prepaymentvalue;
	@FindBy(xpath = "//select[@id='prepayment-percent-basis']")
	public WebElement Percetagebasis;
	@FindBy(xpath = "//div[3]/div[2]/div/div/div[3]/div/div/div[2]")
	public WebElement STTPContainer;
	@FindBy(xpath = "//a[@id='cancel-remove-jurisdiction']")
	public WebElement POPUPCANCEL;
	@FindBy(xpath = "//div/div/a[contains(text(),'Edit')]")
	public WebElement EDIT;
	@FindBy(xpath = "//span[contains(.,'Special characters are not allowed.')]")
	public WebElement SPECIAL_CHAR;
	@FindBy(xpath = "//a[@id='add-jurisdiction']")
	public WebElement ENABLEJURISDICTION;
	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	public WebElement CANCEL;
	@FindBy(xpath = "//input[@id='cities-checkbox'][@type='checkbox']")
	public WebElement CITYCHECKBOX;
	@FindBy(xpath = "//div[@class='datepicker-days']/table//th[@class='today']")
	public WebElement CALENDERTODAY;
	@FindBy(xpath = "//a[@id='add-jurisdiction']")
	public WebElement EFFDATESAVE;
	@FindBy(xpath = "//div[@class='pull-left']/button[contains(.,'Save')]")
	public WebElement EFFDATESAVEINTERNATIONAL;
	@FindBy(xpath = "//a[@id='cancel-add-jurisdiction']")
	public WebElement EFFDATECANCEL;
	@FindBy(xpath = "//select[@id='fiscal-year-override']")
	public WebElement FiscalStartYear;
	@FindBy(xpath = "//h3[contains(.,'Edit new location information')]")
	public WebElement EDITLOCATIONINFO;
	@FindBy(xpath = "//input[@id='new-location-taxareaId']")
	public WebElement EDITTAID;
	@FindBy(xpath = "//h3[contains(.,'Validation Message!')]")
	public WebElement VALIDATIONMESSAGE;
	@FindBy(xpath = "//input[@id='enable-today-future']")
	public WebElement TODAYORFUTUREDATE;
	@FindBy(xpath = "//p[contains(.,'If you are finished adding, editing or removing registrations, please click')]/strong[contains(.,'Ready to Save')]")
	public WebElement READYTOSAVETEXT1;
	@FindBy(xpath = "//p[contains(.,'If you are finished adding, editing or removing registrations, click')]/strong[contains(.,'Ready to Save')]")
	public WebElement READYTOSAVETEXT2;
	@FindBy(xpath = "//input[@id='new-location-withincitylimits']")
	public WebElement LOCWITHINCITYLIMITS;
	@FindBy(xpath = "//div[@class='well']/div/div/label[1][contains(.,'Physical Location')]")
	public WebElement PHYSICALLOCATION;
	@FindBy(xpath = "//div[@class='well']/div/div/label[2][contains(.,'Non-Physical Location')]")
	public WebElement NONPHYSICALLOCATION;
	@FindBy(xpath = "//select[@id='locationfield1']/option[contains(.,'Yes')]")
	public WebElement PHYSICALLOCOPTION;
	@FindBy(xpath = "//select[@id='locationfield1']/option[contains(.,'No')][1]")
	public WebElement NONPHYSICALOPTION1;
	@FindBy(xpath = "//select[@id='locationfield1']/option[contains(.,'No - Utilities Location (207)')]")
	public WebElement NONPHYSICALOPTION2;
	
	@FindBy(xpath="//span[contains(.,'CA Schedule B CDTFA-531')]/../input")
	public WebElement CHKBOX1;
	
	@FindBy(xpath="//span[contains(.,'CA Schedule C CDTFA-530')]/../input")
	public WebElement CHKBOX2;
	
	@FindBy(xpath="//span[contains(.,'CA Lumber Products Assessment')]/../input")
	public WebElement CHKBOX3;
	
	@FindBy(xpath="//span[contains(.,'CA Schedule A CDTFA-531-A')]/../input")
	public WebElement CHKBOX4;
	
	@FindBy(xpath="//span[contains(.,'CA Schedule G CDTFA-531-G')]/../input")
	public WebElement CHKBOX5;
	
	@FindBy(xpath = "//input[@id='locationfield0']")
	public WebElement deductions1;
	@FindBy(xpath = "//input[@id='locationfield1']")
	public WebElement deductions2;
	
	@FindBy(xpath="//span[contains(.,'IL Multiple Site Form ST-2')]/../input[@type='checkbox']")
	public WebElement multiplestateForm;
	
	@FindBy(xpath="//li/label[contains(.,'Claiming Relief Act Tax Credit?')]/../input[@type='checkbox']")
	public WebElement ClaimingReliefActTaxCredit;
	
}