package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RtePage {

	@FindBy(xpath = "//span[contains(text(),'Retail Tax Extract')]")

	public WebElement RETAILTAXEXTRACT;
	@FindBy(xpath = "//li/a[contains(.,'Manage Retail Tax Extract')]")

	public WebElement MANAGERETAILTAXEXTRACT;
	@FindBy(xpath = "//li/a[contains(.,' Manage Bracket Schedule')]")

	public WebElement MANAGEBRACKETSCHEDULE;
	@FindBy(xpath = "//button[text()='Create Extract']")

	public WebElement CREATEEXTRACT;
	@FindBy(xpath = "//input[@id='quick-search']")

	public WebElement QUICKSEARCH;
	@FindBy(xpath = "//input[@id='filter-name']")

	public WebElement NAME;
	@FindBy(xpath = "//div[@class='section ng-scope']/div/h1")

	public WebElement TITLE;
	@FindBy(xpath = "//input[@id='filter-description']")

	public WebElement DESCRIPTION;
	@FindBy(xpath = "//input[@id='filter-start-date']")

	public WebElement STARTDATE;
	@FindBy(xpath = "//input[@id='filter-end-date']")

	public WebElement ENDDATE;
	@FindBy(xpath = "//input[@value='1']")

	public WebElement DAYSPRIOR;
	@FindBy(xpath = "//input[@value='2']")

	public WebElement NEXTMONTH;
	@FindBy(xpath = "//label/strong[text()='Select a Company']/../../select")

	public WebElement SELECTCOMPANY;
	@FindBy(xpath = "//span[text()='Select Locations']")

	public WebElement SELECTLOCATIONS;
	@FindBy(xpath = "//span[text()='Select Locations']/../../../div/div/p/button[text()='Select']")

	public WebElement LOCATIONSSELECTBUTTON;
	@FindBy(xpath = "//span[contains(.,'Select Companies')]/../../../div/div/p/button[text()='Select']")

	public WebElement COMPANYSELECTBUTTON;
	@FindBy(xpath = "//span[text()='Select Product Classes']")

	public WebElement SELECTPRODUCTCLASSES;
	@FindBy(xpath = "//span[text()='Select Product Classes']/../../../div/div/p/button[text()='Select']")

	public WebElement PRODUCTCLASSESSELECTBUTTON;
	@FindBy(xpath = "//input[@id='send-sales']")

	public WebElement SENDSALES;
	@FindBy(xpath = "//input[@id='generate-bracket-schedule']")

	public WebElement GENERATEBRACKETSCHEDULE;
	@FindBy(xpath = "//span[text()='Select Registered Jurisdictions']/../../../div/div/p/button[text()='Select']")

	public WebElement REGISTEREDJURSELECTBUTTON;
	@FindBy(xpath = "//span[text()='Select Registered Jurisdictions']")

	public WebElement SELECTREGISTEREDJURISDICTIONS;
	@FindBy(xpath = "//span[text()='Select Imposition Types']")

	public WebElement SELECTIMPOSITIONTYPES;
	@FindBy(xpath = "//span[text()='Select Imposition Types']/../../../div/div/p/button[text()='Select']")

	public WebElement IMPOSITIONTYPESSELECTBUTTON;
	@FindBy(xpath = "//input[@id='customer-codes']")

	public WebElement CUSTOMERCODES;
	@FindBy(xpath = "//input[@id='customer-classes']")

	public WebElement CUSTOMERCLASSES;
	@FindBy(xpath = "//span[@class='onoffswitch-switch']")

	public WebElement SCHEDULEON;
	@FindBy(xpath = "//h2[contains(.,'Schedule')]/../div/div/div/input[@id='filter-start-date']")

	public WebElement SCHEDULESTARTDATE;
	@FindBy(xpath = "//input[@id='startTime']")

	public WebElement SCHEDULESTARTTIME;
	@FindBy(xpath = "//select[@id='run-frequency']")

	public WebElement FREQUENCY;
	@FindBy(xpath = "//input[@id='filter-days-before-system-date']")

	public WebElement DAYSPRIORCDATE;
	@FindBy(xpath = "//input[@id='filter-days-after-system-date']")

	public WebElement DAYSAFTERCDATE;
	@FindBy(xpath = "//label[contains(.,'Run on')]/../input")

	public WebElement RUNONVALUE;
	@FindBy(xpath = "//label[contains(.,'Export Filename')]/../input")

	public WebElement EXPORTFILENAME;
	@FindBy(xpath = "//button[@ng-click='vm.saveButtonClicked()']")

	public WebElement SAVE;
	@FindBy(xpath = "//button[@ng-click='vm.saveScheduleButtonClicked()']")

	public WebElement SCHEDULESAVE;
	@FindBy(xpath = "//tr/th[1]/div/span[text()='Name']")

	public WebElement COLNAME;
	@FindBy(xpath = "//tr/th[2]/div/span[text()='Frequency']")

	public WebElement COLFREQUENCY;
	@FindBy(xpath = "//tr/th[3]/div/span[text()='Next Generate Time']")

	public WebElement COLNEXTGENERATETIME;
	@FindBy(xpath = "//tr/th[5]/div/span[text()='Status']")

	public WebElement STATUS;
	@FindBy(xpath = "//tr/th[4]/div/span[text()='Last Generated']")

	public WebElement COLLASTGENERATED;
	@FindBy(xpath = "//tr/th[6]/div/span[text()='Action']")

	public WebElement COLACTION;
	@FindBy(xpath = "//tbody/tr/td[text()='Test']/../td/span/a[text()='Manage']")

	public WebElement MANAGE;
	/*
	 * @FindBy(xpath="//a[text()='Delete Test']")
	 * 
	 * public WebElement DELETETEST;
	 */
	@FindBy(xpath = "//a/strong[text()='Cancel']")

	public WebElement CANCEL;
	@FindBy(xpath = "//div[@class='well']/div[@class='row-fluid']/p/button[text()='Cancel']")

	public WebElement RTECANCEL;
	@FindBy(xpath = "//div/strong[text()='Processing!']/../../div[@class='alert alert-warning ng-scope'][contains(.,'An extract is currently running. You will be notified when it is complete.')]")

	public WebElement PROCESSING;
	@FindBy(xpath = "//a/span[text()='DELETE']")

	public WebElement DELETE;
	@FindBy(xpath = "//p/a[text()='Ok']")

	public WebElement OK;
	@FindBy(xpath = "//strong[contains(.,'Failed to save filter:')]/../strong[contains(.,'No duplicate extract names.')]/../strong[contains(.,'Please rename your extract.')]")

	public WebElement DUPLICATEERRORMSG;
	@FindBy(xpath = "//div/span[text()='Select Locations']/../../../div/div/div/input[@id='quick-search']")

	public WebElement LOCATIONSQUICKSEARCH;
	@FindBy(xpath = "//div/span[text()='Select Product Classes']/../../../div/div/div/input[@id='quick-search']")

	public WebElement PRODUCTCLASSESSQUICKSEARCH;
	@FindBy(xpath = "//div/span[text()='Select Registered Jurisdictions']/../../../div/div/div/input[@id='quick-search']")

	public WebElement JURISDICTIONSQUICKSEARCH;
	@FindBy(xpath = "//div/span[text()='Select Imposition Types']/../../../div/div/div/input[@id='quick-search']")

	public WebElement IMPOSITIONQUICKSEARCH;
	@FindBy(xpath = "//div/span[contains(.,'Select Companies')]")

	public WebElement SELECTCOMPLINK;
	@FindBy(xpath = "//label[contains(.,'Include All Tax Areas')]/input")

	public WebElement INCLUDEALLTAXAREAS;
	@FindBy(xpath = "//label[contains(.,'This value cannot exceed 90 days.')]")

	public WebElement DAYSPRIORERROR;
	@FindBy(xpath = "//label[contains(.,'This value cannot exceed 30 days.')]")

	public WebElement DAYSAFTERERROR;
	@FindBy(xpath = "//a[contains(.,'View Warning')]")

	public WebElement VIEWWARNING;
	@FindBy(xpath = "//strong[contains(.,'WARNING: Currently Expired Tax Areas:')]")

	public WebElement WARNINGMESSAGE;
	@FindBy(xpath = "//p[contains(.,'The following business locations have tax areas with expiration dates within the range of this Retail Extract.')]")

	public WebElement WARNINGTEXT;
	@FindBy(xpath = "//div/strong[contains(.,'Success!')]/../../div[contains(.,'Extract Information has been saved successfully.')]")

	public WebElement SUCCESSEXTRACT;
	@FindBy(xpath = "//div/strong[contains(.,'Success!')]/../../div[contains(.,'Schedule has been saved succesfully.')]")

	public WebElement SUCCESSSCHEDULE;
	@FindBy(xpath = "//div[@style='float:right;']/button[text()='RETURN TO ALL EXTRACTS']")

	public WebElement RETURNTOALEXTRACTS;
}
