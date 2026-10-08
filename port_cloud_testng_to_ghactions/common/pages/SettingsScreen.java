package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsScreen {

	@FindBy(xpath = "//span[contains(text(),'Settings')]")

	public WebElement SETTINGS;
	@FindBy(xpath = "//h1")

	public WebElement SETTINGSLABEL;
	@FindBy(xpath = "//h2[contains(text(),'My Company')]")

	public WebElement MYCOMPANYLABEL;
	@FindBy(xpath = "//div[@class='pull-right baseline-h1']/a[contains(text(),'Edit My Company')]")

	public WebElement EDITMYCOMPANY;
	@FindBy(xpath = "//h2[contains(text(),'My Users')]")

	public WebElement MYUSERSLABEL;
	@FindBy(xpath = "//table/tbody/tr/td[3]/a")

	public WebElement USERACTIONEDIT;
	@FindBy(xpath = "//a[contains(text(),'View All Users')]")

	public WebElement VIEWALLUSERS;
	@FindBy(xpath = "//a[contains(text(),'Add New User')]")

	public WebElement ADDNEWUSER;
	@FindBy(xpath = "//h2[contains(text(),'My Billing & Subscription')]")

	public WebElement MYBILLINGANDSUBLABEL;
	@FindBy(xpath = "//div[@class='span6']/h4[contains(text(),'Billing')]")

	public WebElement BILLINGLABEL;
	@FindBy(xpath = "//div[@class='span6']/h4[contains(text(),'Subscription')]")

	public WebElement SUBSCRIPTIONLABEL;
	@FindBy(xpath = "//img[contains(@src,'/content/images/icon-product-intermediate.png')]")

	public WebElement BILLINGANDSUBIMAGE;
	@FindBy(xpath = "//div[1]/div/div/div/div[1]/p[2]/a")

	public WebElement UPDATEMYBILLING;
	@FindBy(xpath = "//div[1]/div/div/div/div[2]/p[2]/a")

	public WebElement EDITMYSUB;
	@FindBy(xpath = "//dl[@class='dl-horizontal section-action']/dt[contains(text(),'Services Agreement')]")

	public WebElement SERVICEAGGREMENTLABEL;
	@FindBy(xpath = "//dl[@class='dl-horizontal section-action']/dt[contains(text(),'Terms of Service')]")

	public WebElement TERMSOFSERVICELABEL;
	@FindBy(xpath = "//h2[contains(text(),'My Connectors')]")

	public WebElement MYCONNECTORSLABEL;
	@FindBy(xpath = "//p[contains(text(),'CalculateTax')]")

	public WebElement ERPCONNECTORSURL;
	@FindBy(xpath = "//p[contains(text(),'LookupTax')]")

	public WebElement ADDLOOKURL;
	@FindBy(xpath = "//a[contains(text(),'View All Connectors')]")

	public WebElement VIEWALLCONNECTOR;
	@FindBy(xpath = "//dt[contains(text(),'Services Agreement')]/../dd[1]/a[contains(text(),'View PDF')]")

	public WebElement SERVICEAGGREMENTVIEWPDF;
	@FindBy(xpath = "//dt[contains(text(),'Terms of Service')]/../dd[2]/a[contains(text(),'View PDF')]")

	public WebElement TERMSOFSERVICEVIEWPDF;
	@FindBy(xpath = "//table[@class='table table-bordered table-striped']/tbody/tr/td/strong/a")

	public WebElement BILLINGREPORTSPDFLINK;

	@FindBy(xpath = "//label[contains(.,'Admin')]")

	public WebElement ADMINCHECKBOX;

	@FindBy(xpath = "//label[contains(.,'Read Only')]")

	public WebElement READONLYCHECKBOX;

	@FindBy(xpath = "//label[contains(.,'ECW Portal Admin')]")

	public WebElement ECWPORTALADMINCHECKBOX;

	@FindBy(xpath = "//label[contains(.,'ECW Portal User')]")

	public WebElement ECWPORTALUSERCHECKBOX;

	@FindBy(xpath = "//label[contains(.,'ECM Only')]")

	public WebElement ECMONLYROLE;

	@FindBy(xpath = "//input[@id='name'][@readonly='']")
	public WebElement Name;
	
	@FindBy(xpath = "//a[@href='/myclient/edit']")
	public WebElement editCPACompany;
	
	@FindBy(xpath = "//input[@id='firm-name']")
	public WebElement cpaFirmName;
	
	
}
