package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerCertificatesPage {

	@FindBy(xpath = "//a[@title='Customers']")
	public WebElement CUSTOMERSLINK;
	
	@FindBy(xpath = "//a[@title='Add New Customer']")
	public WebElement ADDNEWCUSTOMERLINK;
	
	@FindBy(xpath = "//a/span[contains(text(),'New Customer')]")
	public WebElement NEWCUSTOMER;
	
	@FindBy(xpath = "//a[contains(text(),'Customer Certificates')]")
	public WebElement CUSTOMERCERTIFICATES;
	
	@FindBy(xpath = "//a[@title='Add New Certificate']")
	public WebElement ADDNEWCERTIFICATE;
	
	@FindBy(xpath = "//a[@data-bind='click: $parent.selectCertificate, title: label']")
	public WebElement NEWCERTIFICATE;
	
	@FindBy(xpath = "//li[@class='col-row-static']/select")
	public WebElement CLASS;
	
	@FindBy(xpath = "//input[@id='certificate-start-date']")
	public WebElement STARTDATE;
	
	@FindBy(xpath = "//input[@id='certificate-end-date']")
	public WebElement ENDDATE;
	
	@FindBy(xpath = "//li[@data-bind='validationElement: jurisdictionId']/select")
	public WebElement STATE;
	
	@FindBy(xpath = "//input[@data-bind='value: certificateNumber']")
	public WebElement CERTIFICATENUMBER;
	
	@FindBy(xpath = "//li[@data-bind='validationElement: certificateReasonId']/select")
	public WebElement CERTIFICATEREASON;
	
	@FindBy(xpath = "//button[contains(text(),'Save Changes')]")
	public WebElement SAVECHANGES;

}
