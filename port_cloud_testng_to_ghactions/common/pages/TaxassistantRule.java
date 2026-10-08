package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaxassistantRule {
	@FindBy(xpath = "//span[contains(text(),'Configure')]")
	public WebElement CONFIGURELINK;
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Tax Assist')]")
	public WebElement TaxassistantHeader;
	@FindBy(xpath = "//table/thead/tr/th[3]/../../../tbody/tr/td/a[contains(.,'All Tax Assist Rules')]")
	public WebElement ViewLink;
	@FindBy(xpath = "//span/a[contains(.,'Add Rule')]")
	public WebElement AddRule;
	@FindBy(xpath = "//h1[contains(.,'Tax Assist')]")
	public WebElement TaxAssistHeaderText;
	@FindBy(xpath = "//li/a[contains(.,'Tax Assist Rules')]")
	public WebElement Tax_Assist_Rules_Link;
	@FindBy(xpath = "//li/a[contains(.,'Lookup Tables')]")
	public WebElement LookupTableslink;
	@FindBy(xpath = "//li/a[contains(.,'Allocation Tables')]")
	public WebElement Allocationtablelink;
	@FindBy(xpath = "//label[contains(.,'As of Date')]")
	public WebElement AsofDatelabel;
	@FindBy(xpath = "//label[contains(.,'Business Event')]")
	public WebElement BusinessEvent;
	@FindBy(xpath = "//div/label[contains(.,'Business Event')]/../../div/select")
	public WebElement BusinessEventDropdown;
	@FindBy(xpath = "//h2[contains(.,'Tax Assist Rule Search Results')]")
	public WebElement Tax_Assist_Rule_Search_Results;
	@FindBy(xpath = "//label[contains(.,'Phase')]")
	public WebElement Phaselabel;
	@FindBy(xpath = "//label[contains(.,'Filter')]")
	public WebElement Filterlabel;
	@FindBy(xpath = "//div/label[contains(.,'Filter')]/../../div/input")
	public WebElement FilterInput;
	@FindBy(xpath = "//div/label[contains(.,'Phase')]/../../div/select")
	public WebElement Phasedropdown;
	@FindBy(xpath = "//th/span/span[contains(.,'Name')]")
	public WebElement Nameheader;
	@FindBy(xpath = "//th/span/span[contains(.,'Phase')]")
	public WebElement Phaseheader;
	@FindBy(xpath = "//th/span/span[contains(.,'Start Date')]")
	public WebElement Start_Date_Column;
	@FindBy(xpath = "//th/span/span[contains(.,'End Date')]")
	public WebElement End_Date_Column;
	@FindBy(xpath = "//div/a/span[@data-original-title='View']")
	public WebElement View;
	@FindBy(xpath = "//div/a/span[@data-original-title='Edit']")
	public WebElement Edit;
	@FindBy(xpath = "//div/a/span[@data-original-title='Clone']")
	public WebElement Clone;
	@FindBy(xpath = "//div/label[contains(.,'As of Date')]/../../div/input")
	public WebElement AsofDate;
	@FindBy(xpath = "//h2[contains(.,'Add Tax Assist Rule')]")
	public WebElement AddTaxAssistant;
	@FindBy(xpath = "//li/a[contains(.,'Main')]")
	public WebElement Mainlink;
	@FindBy(xpath = "//li/a[contains(.,'Condition')]")
	public WebElement ConditionLink;
	@FindBy(xpath = "//li/a[contains(.,'Conclusions')]")
	public WebElement ConclusionLink;
	@FindBy(xpath = "	//div/label[contains(.,'Name')]/../div/input")
	public WebElement nameInput;
	@FindBy(xpath = "//div/label[contains(.,'Phase')]/../div/select")
	public WebElement phaseinput;
	@FindBy(xpath = "//div/label[contains(.,'Description')]/../div/textarea")
	public WebElement DESCRIPTAREA;
	@FindBy(xpath = "//div/label[contains(.,'Start Date')]/../div/input")
	public WebElement STARTDATEINPUT;
	@FindBy(xpath = "//div/label[contains(.,'End Date')]/../div/input")
	public WebElement ENDDATEINPUT;
	@FindBy(xpath = "//button[contains(.,'Add Context')]")
	public WebElement ADDCONTEXTlink;
	@FindBy(xpath = "//div/a")
	public WebElement CLoselink;
	@FindBy(xpath = "//button[contains(.,'Add Context')]/../../div/a")
	public WebElement ADDcontext_CLoselink;
	@FindBy(xpath = "//a[contains(.,'Lookup Tables')]")
	public WebElement Lookuptables;
	@FindBy(xpath = "//a[contains(.,'Add Lookup Table')]")
	public WebElement AddLookuptable;
	@FindBy(xpath = "//div/h2[contains(.,'Lookup Table Search Results')]")
	public WebElement lookuptablesearchresults;
	@FindBy(xpath = "//th[1]/span/span[contains(.,'Name')]")
	public WebElement NameHeader;
	@FindBy(xpath = "//th/span/span[contains(.,'Result Name')]")
	public WebElement ResultNameHeader;
	@FindBy(xpath = "//span/span[contains(.,'Data Type')]")
	public WebElement Datatype;
	@FindBy(xpath = "//span/span[contains(.,'Start Date')]")
	public WebElement Startdate;
	@FindBy(xpath = "//span/span[contains(.,'End Date')]")
	public WebElement Enddate;
	@FindBy(xpath = "//a[contains(.,'Properties')]")
	public WebElement PROPERTIES;
	@FindBy(xpath = "//div/label[contains(.,'Name')]/../div/input[@id='forms.addLookup.code']")
	public WebElement LookupNameinput;
	@FindBy(xpath = "//div/label[contains(.,'Description')]/../div/textarea[@id='forms.addLookup.description']")
	public WebElement LookupDescription;
	@FindBy(xpath = "//div/label[contains(.,'Result Name')]/../div/input[@id='forms.addLookup.resultName']")
	public WebElement LookupResultname;
	@FindBy(xpath = "//div/label[contains(.,'Data Type')]/../select")
	public WebElement Lookupdatatype;
	@FindBy(xpath = "//div/label[contains(.,'Start Date')]/../div/input")
	public WebElement LookupStartdate;
	@FindBy(xpath = "//div/label[contains(.,'End Date')]/../div/input")
	public WebElement LookupEnddate;
	@FindBy(xpath = "//div/label[contains(.,'Parameter 1')]/../div/input[@id='forms.addLookup.parameterNames[0]']")
	public WebElement Parameter1;
	@FindBy(xpath = "//div/label[contains(.,'Parameter 2')]/../div/input[@id='forms.addLookup.parameterNames[1]']")
	public WebElement Parameter2;
	@FindBy(xpath = "//div/label[contains(.,'Parameter 3')]/../div/input[@id='forms.addLookup.parameterNames[2]']")
	public WebElement Parameter3;
	@FindBy(xpath = "//div/label[contains(.,'Parameter 4')]/../div/input[@id='forms.addLookup.parameterNames[3]']")
	public WebElement Parameter4;
	@FindBy(xpath = "//div/label[contains(.,'Parameter 5')]/../div/input[@id='forms.addLookup.parameterNames[4]']")
	public WebElement Parameter5;
	@FindBy(xpath = "//button[contains(.,'Save Lookup Table')]")
	public WebElement Savelookuptable;
	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement Cancel;
	@FindBy(xpath = "//a[contains(.,'Allocation Tables')]")
	public WebElement ALLOCATIONTABLES;
	@FindBy(xpath = "//a[contains(.,'Add Allocation Table')]")
	public WebElement ADDALLOCATIONTABLE;
	@FindBy(xpath = "//h2[contains(.,'Allocation Table Search Results')]")
	public WebElement ALLOCATIONTABLESEARCHRESULTS;
	@FindBy(xpath = "//th/span[contains(.,'Name')]")
	public WebElement AllocationTableName;
	@FindBy(xpath = "//th/span[contains(.,'Start Date')]")
	public WebElement AllocationStartDate;
	@FindBy(xpath = "//th/span[contains(.,'End Date')]")
	public WebElement AllocationEndDate;
	@FindBy(xpath = "//a[contains(.,'Records')]")
	public WebElement Records;
	@FindBy(xpath = "//label[contains(.,'Import (Replace)...')]")
	public WebElement Import;
	@FindBy(xpath = "//button[contains(.,'Add Record')]")
	public WebElement AddRecord;
	@FindBy(xpath = "//span[contains(.,'Start Date')]")
	public WebElement Recordsstartdate;
	@FindBy(xpath="//input[@id='forms.addLookup.effectiveDate']")
	public WebElement PropertiesStartDate;
	@FindBy(xpath="//div[@class='span12']/div/table/tbody/tr/td[4]")
	public WebElement TableStartDate;
	@FindBy(xpath = "//span[contains(.,'End Date')]")
	public WebElement RecordsEnddate;
	@FindBy(xpath = "//label[contains(.,'Allocation Table Name')]/../div/input[@id='forms.addAllocation.code']")
	public WebElement AllocationTablename;
	@FindBy(xpath = "//label[contains(.,'Description')]/../div/textarea[@id='forms.addAllocation.description']")
	public WebElement Allocationdescription;
	@FindBy(xpath = "//label[contains(.,'Column 1')]/../select")
	public WebElement Column1;
	@FindBy(xpath = "//button[contains(.,'Save Allocation Table')]")
	public WebElement Saveallocationtable;
	@FindBy(xpath = "//label[contains(.,'Import (Update)...')]")
	public WebElement ImportUpdate;
	@FindBy(xpath = "//th[contains(.,'Code')]")
	public WebElement AllocationCode;
	@FindBy(xpath = "//span/span[contains(.,'administrativeDestination.city')]")
	public WebElement Administrativedestination;
	@FindBy(xpath = "//button[contains(.,'Add Allocation Conclusion')]")
	public WebElement Addalloctionconclusion;
	@FindBy(xpath = "//a[contains(.,'Advanced')]")
	public WebElement Advancedlink;
	@FindBy(xpath = "//a[contains(.,'Basic')]")
	public WebElement BASIClink;
	@FindBy(xpath = "//div/h2[contains(text(),'Lookups')]/../div[3]/div[4]")
	public WebElement Allocationlookup;
	@FindBy(xpath = "//table/tbody/tr[1]/td/div/div/input")
	public WebElement Code;
	@FindBy(xpath = "//label[contains(.,'Column 1')]/../../div/div/a/span[@data-original-title='Insert Below']")
	public WebElement INSERTBELOW1;
	@FindBy(xpath = "//label[contains(.,'Column 2')]/../select")
	public WebElement COLUMN2;
	@FindBy(xpath = "//label[contains(.,'Column 3')]/../select")
	public WebElement COLUMN3;
	@FindBy(xpath = "//label[contains(.,'Column 4')]/../select")
	public WebElement COLUMN4;
	@FindBy(xpath = "//label[contains(.,'Column 2')]/../../div/div/a/span[@data-original-title='Insert Below']")
	public WebElement INSERTBELOW2;
	@FindBy(xpath = "//label[contains(.,'Column 3')]/../../div/div/a/span[@data-original-title='Insert Below']")
	public WebElement INSERTBELOW3;
	@FindBy(xpath = "//table/tbody/tr[1]/td[3]/span/span/select")
	public WebElement Conversationrate;
	@FindBy(xpath = "//table/tbody/tr/td[4]/span/input")
	public WebElement Country;
	@FindBy(xpath = "//table/tbody/tr/td[2]/span/input")
	public WebElement CITY;
	@FindBy(xpath = "//table/tbody/tr/td[5]/span/input")
	public WebElement Jurisdictioncode;
	@FindBy(xpath = "//label[contains(.,'Column 1')]/../select")
	public WebElement COLUMN1;
	@FindBy(xpath = "//table/tbody/tr/td[3]/span/span/div/div/input")
	public WebElement COLUMN2_Value;
	@FindBy(xpath = "//table/tbody/tr[1]/td/div/div/input")
	public WebElement Resultname;
	@FindBy(xpath = "//table/tbody/tr[1]/td[2]/input")
	public WebElement Parameter1value;
	@FindBy(xpath = "//table/tbody/tr[1]/td[3]/input")
	public WebElement Parameter2value;
	@FindBy(xpath = "//table/tbody/tr[1]/td[4]/input")
	public WebElement Parameter3value;
	@FindBy(xpath = "//table/tbody/tr[1]/td[5]/input")
	public WebElement Parameter4value;
	@FindBy(xpath = "//table/tbody/tr[1]/td[6]/input")
	public WebElement Parameter5value;
	@FindBy(xpath = "//div/p/button[contains(.,'Save Rule')]")
	public WebElement SAVERULE;
	@FindBy(xpath = "//div/input[@id='.endDate']")
	public WebElement RULEENDDATE;
	@FindBy(xpath = "//div/div/label[contains(.,'Data Type')]/../select")
	public WebElement DATATYPE;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div/span[contains(text(),'<=')]")
	public WebElement LessthanEqOp;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div/span[contains(text(),'>=')]")
	public WebElement GreaterthanEqOp;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div/span[contains(text(),'+')]")
	public WebElement PlusOp;
	@FindBy(xpath = "	//div//h2[contains(.,'Operators')]/../div/div/span[contains(text(),'-')]")
	public WebElement SUbOP;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div/span[contains(text(),'*')]")
	public WebElement MUlOp;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div/span[contains(.,'AND')]")
	public WebElement ANDOp;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div/span[contains(.,'OR')]")
	public WebElement OrOp;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div/span[contains(.,'isNull')]")
	public WebElement isNullOp;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div/span[contains(.,'isNotNull')]")
	public WebElement isnotNullOP;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div[5]/span[contains(text(),'=')]")
	public WebElement equaltoOP;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div/span[contains(text(),'<>')]")
	public WebElement NOtEqualOp;
	@FindBy(xpath = "	//div//h2[contains(.,'Operators')]/../div/div[7]/span[contains(text(),'<')]")
	public WebElement LessthanOP;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div[8]/span[contains(text(),'>')]")
	public WebElement GreaterOp;
	@FindBy(xpath = "//div//h2[contains(.,'Operators')]/../div/div/span[contains(text(),'/')]")
	public WebElement SlashOp;
	@FindBy(xpath = "//div//h2[contains(.,'Fields')]/../div/div/span[contains(.,'Taxibility Drivers, Codes, Names, Types')]")
	public WebElement TaxabilityFields;
	@FindBy(xpath = "//div//h2[contains(.,'Fields')]/../div/div/span[contains(.,'Numbers, Amounts, Rates, Tax Area IDs')]")
	public WebElement NumbersFeild;
	@FindBy(xpath = "//div//h2[contains(.,'Fields')]/../div/div/span[contains(.,'True or False')]")
	public WebElement BooleanField;
	@FindBy(xpath = "//div//h2[contains(.,'Fields')]/../div/div/span[contains(.,'Dates')]")
	public WebElement DateField;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'SUBSTR')]")
	public WebElement SUBStrFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Fields/Values')]/../div/div/span[contains(.,'Returns Field')]")
	public WebElement ReturnField;
	@FindBy(xpath = "//div//h2[contains(.,'Fields/Values')]/../div/div/span[contains(.,'Removed Impositions')]")
	public WebElement RemovedImp;
	@FindBy(xpath = "//div//h2[contains(.,'Fields/Values')]/../div/div/span[contains(.,'Included Impositions')]")
	public WebElement IncludedImp;
	@FindBy(xpath = "//div//h2[contains(.,'Fields/Values')]/../div/div/img[@src='/Content/vendor/oseries/array_button_icon2.gif']")
	public WebElement Arrybutton;
	@FindBy(xpath = "//div//h2[contains(.,'Fields/Values')]/../div/div/span[contains(.,'Output Notice ID Field')]")
	public WebElement OutputNotice;
	@FindBy(xpath = "//div//h2[contains(.,'Fields/Values')]/../div/div/span[contains(.,'overrides')]")
	public WebElement Overrides;
	@FindBy(xpath = "//div//h2[contains(.,'Fields/Values')]/../div/div/span[contains(.,'addresses')]")
	public WebElement Addresses;
	@FindBy(xpath = "//div//h2[contains(.,'Values')]/../div/div/img[@src='/Content/vendor/oseries/date_button_icon6.gif']")
	public WebElement DateButton;
	@FindBy(xpath = "//div//h2[contains(.,'Values')]/../div/div/img[@src='/Content/vendor/oseries/numeric_button_icon.gif']")
	public WebElement Numericbutton;
	@FindBy(xpath = "//div//h2[contains(.,'Values')]/../div/div/img[@src='/Content/vendor/oseries/string_button_icon.gif']")
	public WebElement StringButton;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'JOIN()')]")
	public WebElement JOinFucn;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'DAYS BTW()')]")
	public WebElement DaysBWFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'ROUND()')]")
	public WebElement ROUNDFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'OVERRIDE()')]")
	public WebElement OverrideFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'TAX REG()')]")
	public WebElement TaxRegFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'REMNDR()')]")
	public WebElement RemndrFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'CONTAINS()')]")
	public WebElement ContainsFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'UPPER()')]")
	public WebElement UpperFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'MONTH()')]")
	public WebElement MonthFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'DAY()')]")
	public WebElement DayFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'YEAR()')]")
	public WebElement YearFucnt;
	@FindBy(xpath = "	//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'INCL IMP()')]")
	public WebElement INclImpFunct;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'REM IMP()')]")
	public WebElement RemImpFucn;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'ADD RETURNS FIELD()')]")
	public WebElement Addreturnsfunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'IS MAPPED()')]")
	public WebElement isMappedFunc;
	@FindBy(xpath = "//div//h2[contains(.,'Functions')]/../div/div/span[contains(.,'LOWER()')]")
	public WebElement LOWERFunc;
	@FindBy(xpath = "	//div//h2[contains(.,'Lookups')]/../div/div/span[contains(.,'LOOKUP() string')]")
	public WebElement LookupString;
	@FindBy(xpath = "//div//h2[contains(.,'Lookups')]/../div/div/span[contains(.,'LOOKUP() numeric')]")
	public WebElement LookupNumeric;
	@FindBy(xpath = "//div//h2[contains(.,'Lookups')]/../div/div/span[contains(.,'LOOKUP() array')]")
	public WebElement LookupArray;
	@FindBy(xpath = "//div//h2[contains(.,'Lookups')]/../div/div/span[contains(.,'LOOKUP() allocation')]")
	public WebElement LookupAllocation;
	@FindBy(xpath = "//div[contains(.,'Scratch')]/../div[@class='scratch-pad']")
	public WebElement ScratchPad;
	@FindBy(xpath = "//label[contains(.,'Import (Replace)...')]")
	public WebElement LookupImport;
	@FindBy(xpath = "//tr[1]/td[contains(.,'No End Date')]/../td[7]")
	public WebElement startdate;
}
