package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyCompanyPage {

	@FindBy(xpath = "//a[@title='My Company']")
	public WebElement MYCOMPANYPAGE;

	@FindBy(xpath = "//h1")
	public WebElement MYCOMPNAYLABEL;

	@FindBy(xpath = "//a[contains(text(),'Edit My Company')]")
	public WebElement EDITLINK;

	@FindBy(xpath = "//input[@id='ClientName']")
	public WebElement CLIENTNAME;

	@FindBy(xpath = "//input[@id='ClientPhone']")
	public WebElement CLIENTPHONE;

	@FindBy(xpath = "//input[@id='Address1']")
	public WebElement ADDRESS1;

	@FindBy(xpath = "//input[@id='Address2']")
	public WebElement ADDRESS2;

	@FindBy(xpath = "//input[@id='City']")
	public WebElement CITY;

	@FindBy(xpath = "//select[@id='StateCode']")
	public WebElement STATE;

	@FindBy(xpath = "//input[@id='PostalCode']")
	public WebElement ZIPCODE;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement SAVEBUTTON;
}
