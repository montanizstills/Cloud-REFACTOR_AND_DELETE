/*###################################################################################################
Name: LookupTaxRates.java 
Description: Lookup Tax Rates page objects with all xpaths
Author: Venkat Reddy Kandula
Date Created:01/20/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class LookupTaxRates {

	@FindBy(xpath = "//a/span[contains(text(),'Rates & Jurisdictions')]")
	public WebElement LOOKUPTAXRATESLINK;

	@FindBy(id = "requestAddress1")
	public WebElement STREETADDRESS;

	@FindBy(id = "requestCounty")
	public WebElement COUNTY;

	@FindBy(id = "requestCity")
	public WebElement CITY;

	@FindBy(id = "requestState")
	public WebElement STATE;

	@FindBy(id = "requestZip")
	public WebElement ZIP;

	@FindBy(id = "requestTAID")
	public WebElement TAXAREAID;

//	@FindBy(id="lookup-rates")
//	public WebElement LOOKUPRATEBUTTON1;

	@FindBy(id = "requestCountry")
	public WebElement COUNTRYDRPDWN;

	@FindBy(xpath = "//button[@id='lookup-rate'][@class='btn btn-disabled btn-primary']")
	public WebElement LOOKUPRATEBUTTONENABLE;

	@FindBy(xpath = "//span[contains(text(),'Rates & Jurisdictions')]")
	public WebElement RatesJurisdictions;

	// For LookUpTaxRates verification
	@FindBy(xpath = "//div[@class='styled-percent large tax-rate']/span[@id='tax-rate-primary']")
	public WebElement TAXRATE;

	@FindBy(xpath = "//a[contains(text(),'Clear Results')]")
	public WebElement CLEARRESULTS;

	@FindBy(xpath = "//div[@class='validation-summary-errors alert alert-error']/ul/li[contains(text(),'The Zip Code field is required')]")
	public WebElement ZIPREQUIREDERRMSG;

	@FindBy(xpath = "//span[text()='Invalid address. A valid address is required for a tax rate lookup.']")
	public WebElement INVALIDADDRESSERRMSG;

	@FindBy(xpath = "//button[@class='btn btn-disabled']")
	public WebElement LOOKUPRATEDISABLE;

	@FindBy(xpath = "//select[@id='statesDropDown']/option[text()='Alberta']/../option[text()='Manitoba']/../option[text()='New Brunswick']/../option[text()='Quebec']/../option[text()='Yukon Territory']")
	public WebElement CANADASTATESDRPDN;

	@FindBy(xpath = "//input[@id='requestAddress1']")
	public WebElement CPA_STREETADDRESS;

	@FindBy(xpath = "//input[@id='requestCounty']")
	public WebElement CPA_COUNTY;

	@FindBy(xpath = "//input[@id='requestCity']")
	public WebElement CPA_CITY;

	@FindBy(xpath = "//select[@id='requestState']")
	public WebElement CPA_STATE;

	@FindBy(xpath = "//input[@id='requestZip']")
	public WebElement CPA_ZIP;

	@FindBy(xpath = "//select[@id='requestCountry']")
	public WebElement CPA_COUNTRYDRPDWN;

	@FindBy(xpath = "//div[@class='alert alert-error']/span[contains(.,'Invalid address. A valid address is required for a tax rate lookup.')]")
	public WebElement CPA_INVALIDADDRESSERRMSG;

	@FindBy(xpath = "//div[@class='help-block help-block-error ng-scope ng-active']/span[contains(.,'This field is required.')]")
	public WebElement CPA_ZIPREQUIREDERRMSG;

	@FindBys({ @FindBy(xpath = "//select[@id='requestState']/option"), })
	public List<WebElement> CPA_STATEDD;

	@FindBys({ @FindBy(xpath = "//div/table/tbody/tr/td/table[@class='table table-minimal table-bordered']"), })
	public List<WebElement> Rows;

	@FindBy(xpath = "//tr[1]/td/div/div[@class='percent-wrapper']/span")
	public WebElement BADGE;

	@FindBy(xpath = "//tr[2]/td/div/div[@class='percent-wrapper']/span")
	public WebElement BADGE2;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[1]/td[2]/span")
	public WebElement JurisdictionType;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[1]/td[3]/span")
	public WebElement ImpositionType;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[1]/td/span[starts-with(@id,'tax-rate')]")
	public WebElement SalestaxRate;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[1]/td/span[starts-with(@id,'cut-tax-rate')]")
	public WebElement UsetaxRate;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[2]/td[1]/span")
	public WebElement sJurisdictiontype;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[2]/td[2]/span")
	public WebElement strJurisdictiontype;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[2]/td[3]/span")
	public WebElement sImpositionType;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[2]/td/span[starts-with(@id,'tax-rate')]")
	public WebElement sSalestaxRate;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[2]/td/span[starts-with(@id,'cut-tax-rate')]")
	public WebElement sUsetaxRate;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[3]/td[1]/span")
	public WebElement tJurisdictionType;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[3]/td[2]/span")
	public WebElement t2JurisdictionType;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[3]/td[3]/span")
	public WebElement timpositionType;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[3]/td/span[starts-with(@id,'tax-rate')]")
	public WebElement tSalestaxRate;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[3]/td/span[starts-with(@id,'cut-tax-rate')]")
	public WebElement tUsetaxRate;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[4]/td[1]/span")
	public WebElement fJurisdiction;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[4]/td[2]/span")
	public WebElement fJurisdictionType;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[4]/td[3]/span")
	public WebElement fimpositionType;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[4]/td/span[starts-with(@id,'tax-rate')]")
	public WebElement fSalestaxRate;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr[4]/td/span[starts-with(@id,'cut-tax-rate')]")
	public WebElement fUsetaxRate;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered lookup-result']/tbody/tr/td/span[starts-with(@id,'tax-rate')]")
	public WebElement Table;

	@FindBy(xpath = "//select[@id='requestState']")
	public WebElement CPA_STATEDDP;

	public final String COUNTRYDRPDWN_XPATH = "//select[@id='countryDropDown']";

	public final String CLEARRESULTS_XPATH = "//a[contains(text(),'Clear Results')]";

	@FindBy(xpath = "//strong[contains(.,'Tax Area Id (TAID)')]/../../../../tbody/tr[1]/td[1]/span")
	public WebElement TAID;

	@FindBy(xpath = "//a[contains(.,'Expand All')]")
	public WebElement EXPANDALL;

	@FindBy(xpath = "//span[text()='Invalid tax area id.  A valid tax area id is required for a tax rate lookup.']")
	public WebElement InvalidTAIDmeg;

	@FindBy(xpath = "//input[@id='requestZip']/../div/span[contains(.,'This field is required.')]")
	public WebElement Fieldrequired;

	@FindBy(xpath = "//input[@id='requestTAID']/../div/span[contains(.,'This field is required.')]")
	public WebElement FieldrequiredTaid;

	@FindBy(xpath = "//div/table[@class='table table-bordered']")
	public WebElement LOOKUPTABLE;

	// CD-780
	@FindBy(xpath = "//label[@class='radio'][contains(.,'Address')]/input")
	public WebElement AddRadio;

	@FindBy(xpath = "//label[@class='radio'][contains(.,'Tax Area ID')]/input")
	public WebElement TAIDRadio;

	@FindBy(xpath = "//div[@ng-show='lookupBy == 2']//div//button[@id='lookup-rates']")
	public WebElement TAIDLOOKUPRATEBUTTON;

	@FindBy(xpath = "//div[@ng-show='lookupBy == 1']//div//button[@id='lookup-rates']")
	public WebElement ADDLOOKUPRATEBUTTON;

}
