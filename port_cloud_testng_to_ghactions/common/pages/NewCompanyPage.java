package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewCompanyPage {

	@FindBy(xpath = "//a[@data-bind='click: config().addTaxpayer']")
	public WebElement ADDNEWCOMPANY;

	@FindBy(xpath = "//a[@title='New Company']")
	public WebElement NEWCOMPANY;

	@FindBy(xpath = "//a[@title='Company']")
	public WebElement COMPANY;

	@FindBy(xpath = "//input[@id='company-code']")
	public WebElement COMPANYCODE;

	@FindBy(xpath = "//input[@id='company-name']")
	public WebElement COMAPNYNAME;

	@FindBy(xpath = "//input[@id='company-start-date']")
	public WebElement STARTDATE;

	@FindBy(xpath = "//input[@id='federal-id']")
	public WebElement FEDERALCODE;

	@FindBy(xpath = "//button[@data-bind='click: $root.save']")
	public WebElement SAVECHANGES;

	@FindBy(xpath = "//input[@id='address1']")
	public WebElement ADDRESS1;

	@FindBy(xpath = "//input[@id='city']")
	public WebElement CITY;

	@FindBy(xpath = "//select[@name='state']")
	public WebElement STATE;

	@FindBy(xpath = "//input[@id='zip']")
	public WebElement ZIP;

	@FindBy(xpath = "//button[contains(text(),'ADD COMPANY')]")
	public WebElement ADDCOMPANYBUTTON;

}
