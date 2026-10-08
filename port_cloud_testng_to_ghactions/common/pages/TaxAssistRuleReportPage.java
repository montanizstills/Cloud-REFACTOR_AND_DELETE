/*###################################################################################################
Name: BillingPage.java 
Description: Transaction Report page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/28/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaxAssistRuleReportPage {

	@FindBy(xpath = "//a[@title='Transaction Report']")

	public WebElement TRANSACTIONREPORTLINK;
	@FindBy(id = "companyList")

	public WebElement TAXPAYERSELECT;
	@FindBy(xpath = "//div[@class='alert alert-success ng-scope']")

	public WebElement SUCCESSMSG;
	@FindBy(xpath = "//tr[2]/td/a[text()='Delete']")

	public WebElement DELETE;
	@FindBy(xpath = "//tr[@class='ng-hide']/td/span/i[text()='Report generation is in progress ...']")

	public WebElement INPROGRESS;
	@FindBy(xpath = "//a/strong[text()='Cancel']")

	public WebElement CANCEL;
	@FindBy(xpath = "//p/a[text()='Delete']")

	public WebElement POPUPDELETE;
	@FindBy(xpath = "//tr[2]/td[2]/a")

	public WebElement DOWNLOADLINK;
	@FindBy(xpath = "//a[contains(.,'Tax Assist Rule')]")

	public WebElement TaxAssistRuleReport;
	@FindBy(xpath = "//span[contains(.,'Change or view data that is passed from your host system before tax is calculated or add data to tax calculation results.')]")

	public WebElement TaxAssistRuleReportText;
	@FindBy(xpath = "//div[@class='alert alert-success ng-binding'][contains(.,'Deleted')]")

	public WebElement DELETESUCCESS;
	@FindBy(xpath = "//a[contains(.,'Tax Assist Allocation Table')]")

	public WebElement Taxassistallocationtable;
	@FindBy(xpath = "//a[contains(.,'Tax Assist Lookup Table')]")

	public WebElement Taxassistlookuptable;

}
