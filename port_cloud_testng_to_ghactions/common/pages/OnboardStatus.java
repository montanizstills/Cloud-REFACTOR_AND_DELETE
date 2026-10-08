package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OnboardStatus {

	public final String VERTEXFILINGMETHOD_XPATH = "//table/tbody/tr[1]/td[5]/select";

	public final String VRTXFILINGVALUES_XPATH = "//table/tbody/tr[1]/td[5]/select/option";

	@FindBy(xpath = "//span[contains(text(),'Premium Customers')]")
	public WebElement PREMIUMSMBCUSTOMERS;

	@FindBy(xpath = "//input[@placeholder='Filter Cloud Customers']")
	public WebElement ONBOARDSEARCHBOX;

	@FindBy(xpath = "//div[3]/div[1]/div[1]/label[1]")
	public WebElement ONBOARDLABLE;

	@FindBy(xpath = "//div[3]/div[1]/div[1]/label[2]")
	public WebElement OFFBOARDLABEL;

	@FindBy(xpath = "//div[2]/div/div[2]/div/div/div[1]/div")
	public WebElement OFFBOARDSTATUSMESSAGE;

	@FindBy(xpath = "//label[contains(.,'Onboard Filing Period')]/../select")
	public WebElement ONBOARDFILINGPERIOD;

	@FindBy(xpath = "//a[contains(text(),'Show Returns')]")
	public WebElement SHOWRETURNS;

	@FindBy(xpath = "//label[contains(text(),'Onboard Filing Period')]")
	public WebElement ONBOARDFILINGPERIODLABEL;

	@FindBy(xpath = "//a[contains(text(),'Hide Returns')]")
	public WebElement HIDERETURNS;

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]")
	public WebElement TAXPAYER;

	@FindBy(xpath = "//table/tbody/tr[1]/td[2]")
	public WebElement STATE;

	@FindBy(xpath = "//table/tbody/tr[1]/td[3]")
	public WebElement RETURN;

	@FindBy(xpath = "//table/tbody/tr[1]/td[4]")
	public WebElement CLIENTFILINGMETHOD;

	@FindBy(xpath = "//table/tbody/tr[1]/td[5]/select")
	public WebElement VERTEXFILINGMETHOD;

	@FindBy(xpath = "//table/tbody/tr[1]/td[5]/select/option")
	public WebElement VRTXFILINGVALUES;

	@FindBy(xpath = "//table/tbody/tr/td[6]")
	public WebElement REGISTRATION;

	@FindBy(xpath = "//table/tbody/tr[1]/td[7]/input")
	public WebElement EDIREGISTRATION;

	@FindBy(xpath = "//table/tbody/tr[1]/td[8]/button[contains(text(),'Update')]")
	public WebElement UPDATEBUTTON;

	@FindBy(xpath = "//th[contains(text(),'EDI Registration Number')]")
	public WebElement EDIREGNUMBERCOLUMN;

	@FindBy(xpath = "//th[contains(text(),'Vertex Registration Number')]")
	public WebElement VERTEXREGNUMBERCOLUMN;

	@FindBy(xpath = "//table/tbody/tr[1]/td[5]/select")
	public WebElement FILINGMETHOD;

	@FindBy(xpath = "//tr[1]/td[7]/input")
	public WebElement DISABLEEDIREGNUMBER;

	@FindBy(xpath = "//tr[1]/td[7]/input")
	public WebElement ENABLEEDIREGNUMBER;

	@FindBy(xpath = "//div[@class='alert alert-success ng-binding']")
	public WebElement SUCCESSMSG;

	@FindBy(xpath = "//label[@class='ng-scope selected not-allowed']")
	public WebElement OnboardnotAllowed;

	@FindBy(xpath = "//label[@class='ng-scope not-allowed']")
	public WebElement OffboardnotAllowed;

	@FindBy(xpath = "//a[contains(text(),'Premium Customers')]")
	public WebElement PREMIUMCUSTMERSSUBLINK;

	@FindBy(xpath = "//label[contains(.,'Taxpayer')]/../select")
	public WebElement TAXPAYERDROPDOWN;

}
