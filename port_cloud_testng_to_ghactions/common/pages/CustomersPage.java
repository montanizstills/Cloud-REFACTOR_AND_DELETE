
/*###################################################################################################
Name: LocationsPage.java 
Description: Professional User - Configure Location Page objects with all xpaths
Author: Venkat Reddy K
Date Created:01/20/2014
Date Modified:
Updated Comments:
#####################################################################################################*/
package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomersPage {

	@FindBy(xpath = "//a[text()='Add an Exceptions']")
	public WebElement ADDEXCEPTIONSLINK;

	@FindBy(xpath = "//div[@data-bind='validationElement: customerType']/select")
	public WebElement CUSTOMERTYPE;

	@FindBy(xpath = "//input[@data-bind='value: customerCode']")
	public WebElement CUSTOMERCODE;

	@FindBy(xpath = "//input[@data-bind='value: name']")
	public WebElement CUSTOMERNAME;

	@FindBy(xpath = "//input[@id='newcompany-start-date']")
	public WebElement STARTDATE_CUST;

	@FindBy(xpath = "//select[@id='tax-result']")
	public WebElement TAXRESULTS;

	@FindBy(xpath = "//div[@data-bind='validationElement: certificateClassId']/select")
	public WebElement EXCEPTIONTYPE;

	@FindBy(xpath = "//input[@id='cert-start-date']")
	public WebElement STARTDATE_CERT;

	@FindBy(xpath = "//input[@id='certificate-end-date']")
	public WebElement ENDDATE_CERT;

	@FindBy(xpath = "//div[@data-bind='validationElement: jurisdictionId']/select")
	public WebElement STATE;

	@FindBy(xpath = "//input[@data-bind='value: certificateNumber']")
	public WebElement EXCEPTIONNUMBER;

	@FindBy(xpath = "//div[@data-bind='validationElement: certificateReasonId']/select")
	public WebElement EXCEPTIONREASON;

	@FindBy(xpath = "//label[text()='Resale']")
	public WebElement RESALE;

	@FindBy(xpath = "//label[text()='Government']")
	public WebElement GOVERNMENT;

	@FindBy(xpath = "//label[text()='Exempt Organization']")
	public WebElement EXEMPT;

//	@FindBy(xpath="//input[@id='customer-details-end-date']")
	public WebElement ENDDATE;

	@FindBy(xpath = "//button[contains(text(),'Add Exception')]")
	public WebElement ADDEXCEPTION;

	@FindBy(xpath = "//div[@class='alert alert-error']/span[text()='Configuration save failed']")
	public WebElement ERRORTEXT;

}
