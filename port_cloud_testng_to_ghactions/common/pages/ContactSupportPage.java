package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactSupportPage {

	@FindBy(xpath = "//a/span[contains(text(),'Contact Support')]")
	public WebElement CONTACTSUPPORTLINK;
	
	@FindBy(tagName = "h1")
	public WebElement CONTACTSUPPORTLABEL;
	
	@FindBy(id = "caseTypes")
	public WebElement CASETYPE;
	
	@FindBy(id = "caseSubTypes")
	public WebElement CASESUBTYPE;
	
	@FindBy(id = "SupportRequest_SalesforceSupportDetails_Subject")
	public WebElement SUBJECT;
	
	@FindBy(id = "SupportRequest_SalesforceSupportDetails_Description")
	public WebElement DESCRIPTION;
	
	@FindBy(id = "submitTicket")
	public WebElement SUBMITTICKET;
	
	@FindBy(xpath = "//div[@class='alert-success'][contains(.,'Support case successfully submitted!')]")
	public WebElement SUCCESS;
	
	public final String CASETYPE_XPATH = "//select[@id='caseTypes']";
	
	public final String CASESUBTYPE_XPATH = "//select[@id='caseSubTypes']";

	@FindBy(xpath = "//h1[contains(.,'Internal Server Error')]")
	public WebElement INTERNALSERVERERROR;
	
	@FindBy(xpath = "//a[contains(.,'logging back into TaxCentral.')]")
	public WebElement LOGINBACKTOTAXCENTRAL;
	
	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	public WebElement DASHBOARD;
	
}
