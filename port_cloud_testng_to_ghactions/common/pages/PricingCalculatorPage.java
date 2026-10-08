/*###################################################################################################
Name: ProductsPage.java 
Description: Professional User - Add Products Page objects with all xpaths
Author: Venkat Reddy K
Date Created:01/20/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PricingCalculatorPage {

	public final String VIDEOCLOSE_XPATH = "//div[@class='modal-content']/a[@id='close']";

	@FindBy(xpath = "//li/a[@title='Marketing ToolKit']")
	public WebElement MARKETINGTOLLKITLINK;

	@FindBy(xpath = "//p/a[contains(text(),'View the Pricing Calculator')]")
	public WebElement VIEW_PRICING_CALCULATORLINK;

	@FindBy(xpath = "//h2[text()='Standard']/../div/a[text()='Calculate My Price']")
	public WebElement STD_CALPRICEBTN;

	@FindBy(xpath = "//h2[text()='Professional']/../div/a[text()='Calculate My Price']")
	public WebElement PROF_CALPRICEBTN;

	@FindBy(xpath = "//h2[text()='Premium']/../div/a[text()='Calculate My Price']")
	public WebElement PREM_CALPRICEBTN;

	@FindBy(xpath = "//button[@class='btn calculate']")
	public WebElement CALCULATEBTN;

	@FindBy(xpath = "//input[@id='average-returns']")
	public WebElement NUMBER_OF_RETURNS;

	@FindBy(xpath = "//input[@id='number-invoices']")
	public WebElement INVOICES_SALESORDERS;

	@FindBy(xpath = "//input[@value='Calculate My Price']")
	public WebElement CALCULATEMYPRICE;

	@FindBy(xpath = "//a[text()='Edit Information']")
	public WebElement EDITINFORMATION;

	@FindBy(xpath = "//input[@id='PCcalcAndReturn1']")
	public WebElement TAXCALCRETURNS_RADIO;

	@FindBy(xpath = "//input[@id='PCcalcAndReturn0']")
	public WebElement RETURNSONLY_RADIO;

	@FindBy(xpath = "//input[@id='PCnumReturns']")
	public WebElement NOOFRETURNS;

	@FindBy(xpath = "//input[@id='PCnumTransactions']")
	public WebElement NOOFTRANS;

	@FindBy(xpath = "//input[@id='PCrateFileStates']")
	public WebElement NOOFRATEFILES;

	@FindBy(xpath = "//input[@id='PConlineTransactions']")
	public WebElement NOOFINVOICES;

	@FindBy(xpath = "//input[@id='PCrateFileEnabled']")
	public WebElement RATEFILE_CHECK;

	@FindBy(xpath = "//input[@id='PConlineEnabled']")
	public WebElement SERVICE_CHECK;

	@FindBy(xpath = "//button[contains(.,'Calculate')]")
	public WebElement CALCULATE;

	@FindBy(xpath = "//div[@class='pricing-tier standard']/div/a[contains(.,'Calculate My Price')]")
	public WebElement STANDCALC;

	@FindBy(xpath = "//div[@class='pricing-tier professional']/div/a[contains(.,'Calculate My Price')]")
	public WebElement PROFDCALC;

	@FindBy(xpath = "//div[@class='pricing-tier premium']/div/a[contains(.,'Calculate My Price')]")
	public WebElement PREMCALC;

	@FindBy(xpath = "//span[@class='ng-binding calculated']")
	public WebElement TOTALTAX;

	@FindBy(xpath = "//li[@ng-if='pricingOutput.subscriptionFee']")
	public WebElement BASEPRICE;

	@FindBy(xpath = "//li[@ng-if='pricingOutput.transactionFee']")
	public WebElement CALCULATIONS;

	@FindBy(xpath = "//li[@ng-if='pricingOutput.returnFee']")
	public WebElement RETURNS;

	@FindBy(xpath = "//li[@ng-if='pricingOutput.rateFileFee']")
	public WebElement RATEFILE;

	@FindBy(xpath = "//li[@ng-if='pricingOutput.onlineFee']")
	public WebElement REALTIME;

	@FindBy(xpath = "//span[@data-bind='text : PROFESSIONAL_calculatedMonthlyCost']")
	public WebElement PROFTOTAL;

	@FindBy(xpath = "//span[@data-bind='text : PROFESSIONAL_monthlyBaseFee']")
	public WebElement PROFBASE;

	@FindBy(xpath = "//span[@data-bind='text : PROFESSIONAL_calculatedTransactionsCost']")
	public WebElement PROFTRANS;

	@FindBy(xpath = "//span[@data-bind='text : PROFESSIONAL_calculatedReturnsCost']")
	public WebElement PROFRETURNS;

	@FindBy(xpath = "//span[@data-bind='text : PROFESSIONAL_monthlyServiceFee']")
	public WebElement PROFSUPPORTVAL;

	@FindBy(xpath = "//dl[@class='heading professional']/../div/div/dl/dt[text()='Support']/../dd")
	public WebElement PROFSUPPORTNA;

	@FindBy(xpath = "//span[@data-bind='text : PREMIUM_calculatedMonthlyCost']")
	public WebElement PREMTOTAL;

	@FindBy(xpath = "//span[@data-bind='text : PREMIUM_monthlyBaseFee']")
	public WebElement PREMBASE;

	@FindBy(xpath = "//span[@data-bind='text : PREMIUM_calculatedTransactionsCost']")
	public WebElement PREMTRANS;

	@FindBy(xpath = "//span[@data-bind='text : PREMIUM_calculatedReturnsCost']")
	public WebElement PREMRETURNS;

	@FindBy(xpath = "//span[@data-bind='text : PREMIUM_monthlyServiceFee']")
	public WebElement PREMSUPPORTVAL;

	@FindBy(xpath = "//dl[@class='heading premium']/../div/div/dl/dt[text()='Support']/../dd")
	public WebElement PREMSUPPORTNA;

	@FindBy(xpath = "//span[@data-bind='text : PREMIUM_PLUS_calculatedMonthlyCost']")
	public WebElement PREMPLUSTOTAL;

	@FindBy(xpath = "//span[@data-bind='text : PREMIUM_PLUS_monthlyBaseFee']")
	public WebElement PREMPLUSBASE;

	@FindBy(xpath = "//span[@data-bind='text : PREMIUM_PLUS_calculatedTransactionsCost']")
	public WebElement PREMPLUSTRANS;

	@FindBy(xpath = "//span[@data-bind='text : PREMIUM_PLUS_calculatedReturnsCost']")
	public WebElement PREMPLUSRETURNS;

	@FindBy(xpath = "//span[@data-bind='text : PREMIUM_PLUS_monthlyServiceFee']")
	public WebElement PREMPLUSSUPPORTVAL;

	@FindBy(xpath = "//dl[@class='heading premium_plus']/../div/div/dl/dt[text()='Support']/../dd")
	public WebElement PREMPLUSSUPPORTNA;

	@FindBy(xpath = "//div/h3[contains(.,'Marketing Materials')]/../ul/li/a[contains(text(),'Demo Movie')]/../i")
	public WebElement VIDEOICON;

	@FindBy(xpath = "//div[@class='well']/ul/li/a[contains(text(),'Demo Movie')]/../a[@class='icon icon-download']")
	public WebElement VIDEODOWNLOAD;

	@FindBy(xpath = "//div[@class='well']/ul/li/a[contains(text(),'Demo Movie')]")
	public WebElement DEMOMOVIELINK;

	@FindBy(xpath = "//div[@class='modal-content']/a[@id='close']")
	public WebElement VIDEOCLOSE;

	@FindBy(xpath = "//div[@class='modal-content']/div[@id='playVideo']")
	public WebElement VIDEOFILE;

	@FindBy(xpath = "//div[@class='well']/ul/li/a[contains(text(),'Vertex SMB Company Intro Video')]")
	public WebElement DEMOSTAGELINK;

	@FindBy(xpath = "//div/h3[contains(.,'Marketing Materials')]/../ul/li/a[contains(text(),'Vertex SMB Company Intro Video')]/../i")
	public WebElement VIDEOICONSTAGE;

	@FindBy(xpath = "//div/h3[contains(.,'Marketing Materials')]/../ul/li/a[contains(text(),'Vertex SMB Company Intro Video')]/../a[@class='icon icon-download']")
	public WebElement VIDEODOWNLOADSTAGE;

	@FindBy(xpath = "//span[@class='ng-binding calculated']")
	public WebElement TOTALVALUE;

	@FindBy(xpath = "//li[@ng-if='pricingOutput.rateFileFee']")
	public WebElement RATE;

	@FindBy(xpath = "//li[@ng-if='pricingOutput.onlineFee']")
	public WebElement INVOICE;

	@FindBy(xpath = "//li[@ng-if='pricingOutput.subscriptionFee']")
	public WebElement SUBSCRIPTIONFEE;

	@FindBy(xpath = "//li[@ng-if='pricingOutput.transactionFee']")
	public WebElement TRANSACTIONSFEE;

	@FindBy(xpath = "//li[@ng-if='pricingOutput.returnFee']")
	public WebElement RETURNFEE;

	@FindBy(xpath = "//select[@class='input-block-level']")
	public WebElement VIDEOFILEDD;

}
