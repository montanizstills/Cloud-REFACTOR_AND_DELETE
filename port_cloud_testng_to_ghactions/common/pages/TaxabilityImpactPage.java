/*###################################################################################################
Name: BillingPage.java 
Description: Billing page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaxabilityImpactPage {

	@FindBy(xpath = "//a/span[text()='Taxability Impact Report']")

	public WebElement TAXABILITYIMPACTREPORT;
	@FindBy(xpath = "//button[text()='Configure New Report']")

	public WebElement CONFIGURENEWREPORT;
	@FindBy(xpath = "//a[contains(text(),'Add a New Product')]")

	public WebElement ADDANEWPRODUCT;
	@FindBy(xpath = "//input[@id='companyReportName']")

	public WebElement COMPANYREPORTNAME;
	@FindBy(xpath = "//input[@id='tbProductIdentifier']")

	public WebElement TBPRODUCTIDENTIFIER;
	@FindBy(xpath = "//select[@id='product-handling']")

	public WebElement TAXTYPE;
	@FindBy(xpath = "//button[contains(text(),'Save & Close')]")

	public WebElement SAVEANDCLOSE;
	@FindBy(xpath = "//a[@id='returnToReportListing']")

	public WebElement RETURNTOREPORTLISTING;
	@FindBy(xpath = "//a[contains(text(),'Generate Report')]")

	public WebElement GENERATEREPORT;
	@FindBy(xpath = "//a[@disabled='disabled']")

	public WebElement DISABLED;
	@FindBy(xpath = "//button[contains(text(),'Save & Add Another')]")

	public WebElement SAVEADDANOTHER;
	@FindBy(xpath = "//span[contains(text(),'Successfully created product')]")

	public WebElement SAVEANOTHERSUCS;
	@FindBy(xpath = "//tbody/tr[3]/td/span/span/a[text()='Edit']")

	public WebElement REPORTROW1EDIT;
	@FindBy(xpath = "//tbody/tr[2]/td[4]/a[text()='Edit']")

	public WebElement PRODROW1EDIT;
	@FindBy(xpath = "//tbody/tr[3]/td[4]/a[text()='Delete']")

	public WebElement PRODROW2DELETE;
	@FindBy(xpath = "//tbody/tr[3]/td/span/a[text()='Delete']")

	public WebElement REPORTDELETE;
	@FindBy(xpath = "//div/span[contains(text(),'already exists for this client')]")

	public WebElement DUPREPORTVERFY;
	@FindBy(xpath = "//div/span[contains(text(),'already exists for taxability impact report')]")

	public WebElement DUPPRODVERFY;
	public final String PRODROW2DELETE_XPATH = "//tbody/tr[3]/td[4]/a[text()='Delete']";
	public final String DELETEREPORTCONFIRM_XPATH = "//a[contains(text(),'Delete report')]";

	@FindBy(xpath = "//a/span[contains(text(),'Delete product')]")

	public WebElement DELETEPRODUCT;
	@FindBy(xpath = "//a/span[contains(text(),'Delete report')]")

	public WebElement DELETEREPORTCONFIRM;
	@FindBy(xpath = "//tbody/tr[3]/td/span/span/a[text()='Download']")

	public WebElement REPORTDWNLD;
	@FindBy(xpath = "//a[contains(text(),'Return To Report Configuration')]")

	public WebElement RETURNTORPRTCONFIG;
	@FindBy(xpath = "//form/button")

	public WebElement SAVE;
	@FindBy(xpath = "//span[@ng-show='report.isGenerating']/i/b[contains(.,'Generation in progress...')]")

	public WebElement REINPORGRESS;

	@FindBy(xpath = "//div/span[text()='Generation Date']")

	public WebElement GENERATIONDATESORT;

}
