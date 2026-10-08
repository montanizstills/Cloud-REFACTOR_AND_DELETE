/*###################################################################################################
Name: CalculateTaxesPage.java 
Description: Calculate Taxes page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class DistributeTaxes {
	@FindBy(xpath = "//span[contains(text(),'Calculate Tax')]")
	public WebElement CALTAX;

	@FindBy(xpath = "//div/a[contains(.,'Distribute Tax')]")
	public WebElement DISTRIBUTETAX;

	@FindBy(id = "form-date")
	public WebElement TRANSDATE;

	@FindBy(id = "transaction-number")
	public WebElement TRANSNO;

	@FindBy(xpath = "//div[contains(text(),'You have unpublished configuration changes.')]")
	public WebElement UNPUBLISHED_CONFIGURATION;

	@FindBy(xpath = "//label[contains(.,'Company')]/../select")
	public WebElement TAXPAYER;

	@FindBy(id = "total-tax")
	public WebElement TOTALTAX;

	@FindBy(id = "customer")
	public WebElement CUSTOMER;

	@FindBy(id = "location")
	public WebElement LOCATION;

	@FindBy(id = "product")
	public WebElement PRODUCT;

	@FindBy(xpath = "//a[contains(.,'Get Jurisdictions')]")
	public WebElement GETJURISDICTIONS;

	@FindBy(xpath = "//a[text()='Distribute Tax']")
	public WebElement DISTRIBUTETEAX;

	@FindBy(xpath = "//button[contains(.,'Close & Return to Top')]")
	public WebElement RETUTNTOTOP;

	@FindBy(id = "contact-1-country")
	public WebElement SHIPCOUNTRY;

	@FindBy(id = "address-3-address1")
	public WebElement SHIPSTREETADDRESS;

	@FindBy(id = "address-4-county")
	public WebElement SHIPCOUNTY;

	@FindBy(id = "address-5-city")
	public WebElement SHIPCITY;

	@FindBy(id = "contact-1-state")
	public WebElement SHIPSTATE;

	@FindBy(id = "contact-1-zip")
	public WebElement SHIPZIP;

	@FindBy(xpath = "//div[@class='span6 shipping-options']/div/ul/li[1]/a")
	public WebElement PHYSICALORIGINLINK;

	@FindBy(id = "contact-2-country")
	public WebElement PHYSICALORIGINCOUNTRY;

	@FindBy(id = "contact-2-state")
	public WebElement PHYSICALORIGINSTATE;

	@FindBy(xpath = "//input[@id='address-8-address1']")
	public WebElement PHYSICALORIGINSTREETADDRESS;

	@FindBy(xpath = "//input[@id='contact-2-zip']")
	public WebElement PHYSICALORIGINZIP;

	@FindBy(xpath = "//input[@id='address-9-county']")
	public WebElement PHYSICALORIGINSHIPCOUNTY;

	@FindBy(xpath = "//input[@id='address-10-city']")
	public WebElement PHYSICALORIGINSHIPCITY;

	@FindBy(id = "address-11-state")
	public WebElement PHYSICALORIGINSHIPSTATE;

	@FindBy(id = "address-12-zip")
	public WebElement PHYSICALORIGINSHIPZIP;

	@FindBy(xpath = "//div[@class='span6 shipping-options']/ul/li[2]/a")
	public WebElement ADLINK;

	@FindBy(id = "country-13-country")
	public WebElement ADCOUNTRY;

	@FindBy(xpath = "//input[@id='address-14-address1']")
	public WebElement ADSTREETADDRESS;

	@FindBy(xpath = "//input[@id='address-15-county']")
	public WebElement ADCOUNTY;

	@FindBy(xpath = "//input[@id='address-16-city']")
	public WebElement ADCITY;

	@FindBy(xpath = "//select[@id='address-17-state']")
	public WebElement ADSTATE;

	@FindBy(id = "address-18-zip")
	public WebElement ADZIP;

	@FindBy(xpath = "//div[@class='span6 shipping-options']/ul/li[3]/a")
	public WebElement AOLINK;

	@FindBy(id = "country-19-country")
	public WebElement AOCOUNTRY;

	@FindBy(xpath = "//input[@id='address-20-address1']")
	public WebElement AOSTREETADDRESS;

	@FindBy(xpath = "//input[@id='address-21-county']")
	public WebElement AOCOUNTY;

	@FindBy(xpath = "//input[@id='address-22-city']")
	public WebElement AOCITY;

	@FindBy(id = "address-23-state")
	public WebElement AOSTATE;

	@FindBy(id = "address-24-zip")
	public WebElement AOZIP;

	@FindBy(xpath = "//div/input[@id='form-date']/../div/span[contains(.,'This field is required')]")
	public WebElement TRANSACTIONDATEERROR;

	@FindBy(xpath = "//span[text()='Total Tax is required and cannot be blank.']")
	public WebElement TOTALTAXERROR;

	@FindBy(xpath = "//span[text()='Taxpayer is required and cannot be blank.']")
	public WebElement TAXPAYEREERROR;

	@FindBy(xpath = "//span[text()='State/Province is required and cannot be blank.']")
	public WebElement STATEEERROR;

	@FindBy(xpath = "//span[text()='Zip/Postal Code is required and cannot be blank.']")
	public WebElement ZIPEERROR;

	@FindBy(xpath = "//span[text()='Transaction Number cannot exceed 40 characters in length.']")
	public WebElement MAXTAXPAYERLENGTH;

	@FindBy(xpath = "//div[@class='alert alert-error']/span")
	public WebElement ERROR_DISTRIBUTETAX;

	@FindBy(xpath = "//div[@id='transaction-preview-container']/div/div/table[2]/tbody/tr/td[2]/span")
	public WebElement JURISDICTION;

	@FindBy(xpath = "//input[@data-bind='value: $parent.request().postingDate']")
	public WebElement INVOICEDATE;

	@FindBy(xpath = "//button[@data-bind='click: $parent.submitInvoice']")
	public WebElement POSTTRANSACTION;

	@FindBy(xpath = "//a[@title='Calculate Taxes']")
	public WebElement CALTAXES;

	@FindBy(xpath = "//div[@class='blockUI blockMsg blockPage']")
	public WebElement PLEASEWAIT;

	@FindBy(xpath = "//div[@class='alert alert-error']")
	public WebElement ERROR_CALCULATE;

	@FindBy(xpath = "//td[@class='big' and @data-bind='text: accounting.formatMoney(responseTotal)']")
	public WebElement TOTALAMOUNT;

	@FindBy(xpath = "//td[@class='big' and @data-bind='text: accounting.formatMoney(responseTotalTax)']")
	public WebElement TOTALTAXAMOUNT;

	@FindBy(xpath = "//div[@class='alert alert-error ng-binding ng-scope']")
	public WebElement ERRORDISTRIBUTE;

	@FindBy(xpath = "//span[text()='Administrative Origin (order received or order taken) Postal Code is required when other address elements have been provided (Street Address, City, County, State)']")
	public WebElement ERRORAOZIP;

	@FindBy(xpath = "//div[@class='alert alert-success ng-scope']")
	public WebElement SUCESSALERT;

	@FindBy(xpath = "//table[@class='pull-right taxes-total']/tbody/tr[1]/td")
	public WebElement DTOTALTAX;

	@FindBy(xpath = "//div[@class='right-col dashboard-col']/div/div[contains(.,'Your transaction has been posted successfully.')]")
	public WebElement SUCCESS;

	@FindBy(id = "address-26-state")
	public WebElement PROVINCE;

	@FindBy(xpath = "//select[@id='address-27-state']")
	public WebElement CANPROVINCE;

	@FindBy(xpath = "//select[@id='shippingTerms']")
	public WebElement SHIPPINGTERM;

	@FindBy(xpath = "//span[contains(text(),'Transaction Posting Rejected: You are not registered to file in this jurisdiction')]")
	public WebElement ERRORMSGCANADA;

	@FindBy(xpath = "//select[@id='contact-1-country']")
	public WebElement SHIPPEDCOUNTRY;

	@FindBy(xpath = "//label[contains(.,'Province / Territory')]")
	public WebElement CANADAPOSTALCODE;

	@FindBy(xpath = "//label[contains(.,'City')]")
	public WebElement USZIPCODE;

	@FindBy(xpath = "//h1/i[@class='icon-section icon-calculate']")
	public WebElement DISTRIBUTETAXICON;

	@FindBy(xpath = "//select[@id='product']")
	public WebElement CodeClass;

	@FindBy(xpath = "//tr/td[2][@class='text-center ng-binding']")
	public WebElement CodeClassvalue;

	@FindBys({ @FindBy(xpath = "//table[@class='table table-striped table-bordered']/tbody/tr"), })
	public List<WebElement> NoofTaxRows;
	
	@FindBy(xpath = "//label[@id='shipToStateLabel']/../select")
	public WebElement SELECTID;
	
	public final String TOTALTAX_XPATH = "//input[@id='total-tax']";
	@FindBy(xpath = "//div[contains(.,'Zip Code is not valid. Please enter a valid Zip Code')]")
	public WebElement ERRORZIP;
	
	@FindBy(xpath = "//label[contains(.,'Tax Type')]/../select")
	public WebElement TAXTYPE;
	
	@FindBy(xpath = "//a[@id='distribute-taxes'][@disabled='disabled']")
	public WebElement GETJURISDICTIONDISABLE;
	
	@FindBy(xpath = "//label[@class='radio' and @for='lbl-1-Addr']/input")
	public WebElement ADDRADIO1;
	
	@FindBy(xpath = "//label[@class='radio' and @for='lbl-1-Taid']/input")
	public WebElement TAIDRADIO1;
	
	@FindBy(xpath = "//label[@class='radio' and @for='lbl-2-Addr']/input")
	public WebElement ADDRADIO2;
	
	@FindBy(xpath = "//label[@class='radio' and @for='lbl-2-Taid']/input")
	public WebElement TAIDRADIO2;
	
	@FindBy(xpath = "//p[contains(.,'Enter Physical Destination By')]/../contact-address/div/label[contains(.,'Address')]/input")
	public WebElement PDADDRADIO;
	
	@FindBy(xpath = "//p[contains(.,'Enter Physical Destination By')]/../contact-address/div/label[contains(.,'TaxAreaId')]/input")
	public WebElement PDTAID;
	
	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../contact-address/div/label[contains(.,'Address')]/input")
	public WebElement POADDRADIO;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../contact-address/div/label[contains(.,'TaxAreaId')]/input")
	public WebElement ADTAID;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../contact-address/div/label[contains(.,'Address')]/input")
	public WebElement ADADDRADIO;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../contact-address/div/label[contains(.,'TaxAreaId')]/input")
	public WebElement AOTAID;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../contact-address/div/label[contains(.,'Address')]/input")
	public WebElement AOADDRADIO;
	
	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../contact-address/div/label[contains(.,'TaxAreaId')]/input")
	public WebElement POTAID;
	
	@FindBy(xpath = "//a[@id='distribute-taxes' and @disabled='disabled']")
	public WebElement GETJURISDICTIONSDISABLED;
	
	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../contact-address[1]/div[2]/div[1]/div[3]/div[2]/div[1]/select[1]")
	public WebElement PHYSICALORIGINSTATECANADA;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../contact-address/div/div/div/div/select")
	public WebElement AD_COUNTRY;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/select[1]")
	public WebElement AD_STATE;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../contact-address[1]/div[2]/div[1]/div[3]/div[4]/div[1]/div[1]/input[1]")
	public WebElement AD_ZIP;
	
	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/div[1]/input[1]")
	public WebElement PHYSICALORIGINZIPCANADA;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../contact-address/div/div/div/div/select")
	public WebElement AO_COUNTRY;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/select[1]")
	public WebElement AO_STATE;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../contact-address[1]/div[2]/div[1]/div[3]/div[4]/div[1]/div[1]/input[1]")
	public WebElement AO_ZIP;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../contact-address[1]/div[2]/div[1]/div[3]/div[2]/div[1]/select[1]")
	public WebElement AO_STATECANADA;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/div[1]/input[1]")
	public WebElement AO_ZIPCANADA;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../contact-address[1]/div[2]/div[1]/div[3]/div[2]/div[1]/select[1]")
	public WebElement AD_STATECANADA;
	
	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/div[1]/input[1]")
	public WebElement AD_ZIPCANADA;
	
	@FindBy(xpath = "//div/input[@id='contact-1-zip']/../div/span[contains(.,'This field is required.')]")
	public WebElement ZIPFEILDERROR;
	
	@FindBy(xpath = "//p[contains(.,'An error has occurred')]/../p/a[contains(.,'Ok')]")
	public WebElement ANERRORHASOCCURED;
	
	@FindBy(xpath = "//input[@id='company-status']")
	public WebElement COMPANYSTATUS;
}
