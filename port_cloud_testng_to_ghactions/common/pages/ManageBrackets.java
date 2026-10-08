package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageBrackets {

	@FindBy(xpath = "//span[contains(text(),'Retail Tax Extract')]")
	public WebElement RETAILTAXEXTRACT;

	@FindBy(xpath = "//a[contains(text(),' Manage Bracket Schedule')]")
	public WebElement MANAGEBRACKETSCHEDULE;

	@FindBy(xpath = "//input[@id='quick-search-products']")
	public WebElement PRODUCTSSEARCH;

	@FindBy(xpath = "//input[@id='quick-search-locations']")
	public WebElement LOCATIONSSEARCH;

	@FindBy(xpath = "//div/label[text()='Company']/../select")
	public WebElement COMPANY;

	@FindBy(xpath = "//div/label[text()='Jurisdiction']/../select")
	public WebElement JURISDICTION;

	@FindBy(xpath = "//a[text()='Show']")
	public WebElement SHOW;

	@FindBy(xpath = "//a[text()='Hide']")
	public WebElement HIDE;

	@FindBy(xpath = "//button[text()='Cancel']")
	public WebElement CANCEL;

	@FindBy(xpath = "//div[3]/div/div[3]/input")
	public WebElement MAXBASISROW2;

	@FindBy(xpath = "//div[2]/div/div[6]/h1/i[1]")
	public WebElement ADDABOVEROW1;

	@FindBy(xpath = "//div[2]/div/div[6]/h1/i[2]")
	public WebElement ADDBELOWROW1;

	@FindBy(xpath = "//div[2]/div/div[5]/input")
	public WebElement REPEATSTARTROW1;

	@FindBy(xpath = "//div[2]/div/div[5]/label/i")
	public WebElement ROW1REPEATSTARTERROR;

	@FindBy(xpath = "//div[@class='popover-content']/p[text()='No repeat start indicator. One required.']")
	public WebElement REPEATSTARTERRORMSG;

	@FindBy(xpath = "//div[@class='popover-content']/p[text()='Tax amount leaves a gap compared to the previous bracket.']")
	public WebElement TAXAMOUNTWARNING;

	@FindBy(xpath = "//button[text()='Save']")
	public WebElement SAVE;

	@FindBy(xpath = "//input[@id='rte-extended-price']")
	public WebElement EXTENDEDPRICE;

	@FindBy(xpath = "//button[text()='Calculate']")
	public WebElement CALCULATE;

	@FindBy(xpath = "//input[@id='rte-extended-price-result']")
	public WebElement EXTENDEDPRICERESULT;

	@FindBy(xpath = "//th[text()='Product Class']/../th[text()='Product Name']/../th[text()='Company']")
	public WebElement PRODUCTCLASSCOLUMNS;

	@FindBy(xpath = "//td[text()='TPP01']/../td[text()='Gen TPP']/../td[text()='German Shepherd, Inc']")
	public WebElement PRODUCTCLASSVALUES;

	@FindBy(xpath = "//div/strong[text()='no bracket schedules']")
	public WebElement NOBRACKETSCHEDULES;

	@FindBy(xpath = "//a[contains(.,'Import Bracket Schedule')]")
	public WebElement IMPORTBRACKETSCHEDULE;

	@FindBy(xpath = "//h3/a[@id='downloadFileFormat']")
	public WebElement DOWNLOADFILEFORMATTEMPLATE;

	@FindBy(xpath = "//input[@type='text']")
	public WebElement BROWSE;

	@FindBy(xpath = "//button[contains(.,' Upload File')]")
	public WebElement UPLOADFILE;

	@FindBy(xpath = "//div[@class='alert alert-error ng-binding ng-scope'][contains(.,'Invalid file format. Please verify that your file is formatted correctly.')]")
	public WebElement FILEFORMATERROR;

	@FindBy(xpath = "//div[@class='alert alert-error ng-binding ng-scope'][contains(.,'Invalid file type. Only .csv files are supported.')]")
	public WebElement FILETYPEERROR;

}
