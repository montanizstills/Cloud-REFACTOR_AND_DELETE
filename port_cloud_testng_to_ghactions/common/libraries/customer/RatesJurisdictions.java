/*###################################################################################################
Name: RatesJurisdictions.java 
Description: Verifying Rates and Jurisdictions functionality
Author: Venkat Reddy Kandula
Date Created:02/06/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.libraries.customer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.LookupTaxRates;

public class RatesJurisdictions extends CommonFunctions {

	private WebDriver driver;

	public RatesJurisdictions(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void VerifyLookupTaxRatesPage(String Country, String StreetAddress, String County, String City, String State,
			String ZIP, String TAID, String VerifyError, String State1, String RenatlRate, String TaxImpositionType)
			throws Exception {
		LookupTaxRates LTR = PageFactory.initElements(driver, LookupTaxRates.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		int k;
		MultipleStates = "";
		String SalesReplace = null, UseReplace = null, RentalReplace = null, SalesTaxRate, UseTaxRate,
				RentalTaxRate = null;
		driver.navigate().refresh();
		waitforElement(180, LTR.LOOKUPTAXRATESLINK, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, LTR.LOOKUPTAXRATESLINK);
		} else {
			click(LTR.LOOKUPTAXRATESLINK);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, LTR.COUNTRYDRPDWN, driver);
		if (Country != "") {
			selectByValue(LTR.COUNTRYDRPDWN, Country);
			Thread.sleep(2000);
			if (StreetAddress != "") {
				clear(LTR.STREETADDRESS);
				type(LTR.STREETADDRESS, StreetAddress);
			}
			if (County != "") {
				clear(LTR.COUNTY);
				type(LTR.COUNTY, County);
			}
			if (City != "") {
				clear(LTR.CITY);
				type(LTR.CITY, City);
			}
			if (State != "") {
				waitforText(180, "//select[@id='requestState']/option[contains(.,'" + State + "')]", driver);
				selectByValue(LTR.STATE, State);
			}
			if (ZIP != "") {
				JavaScriptclick(driver, LTR.ZIP);
				JavaScripttype(driver, LTR.ZIP, "-");
				String PostalCode = LTR.ZIP.getText();
				assertTrue(PostalCode.isEmpty());
				JavaScripttype(driver, LTR.ZIP, ZIP);
				if (TAID.isEmpty()) {
					sendkeysTab(LTR.ZIP);
					Thread.sleep(1000);
				}
				waitforElement(180, LTR.ADDLOOKUPRATEBUTTON, driver);
				JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
				Thread.sleep(2000);
				if (!VerifyObject(driver, LTR.CLEARRESULTS)) {
					JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
					Thread.sleep(2000);
				}
			}
		}
		if (TAID != "") {
			JavaScriptclick(driver, LTR.TAIDRadio);
			Thread.sleep(2000);
			click(LTR.TAXAREAID);
			JavaScripttype(driver, LTR.TAXAREAID, TAID);
			Thread.sleep(2000);
			if (ZIP == "") {
				sendkeysTab(LTR.TAXAREAID);
				Thread.sleep(1000);
			}
			waitforElement(180, LTR.TAIDLOOKUPRATEBUTTON, driver);
			JavaScriptclick(driver, LTR.TAIDLOOKUPRATEBUTTON);
			Thread.sleep(2000);
			if (!VerifyObject(driver, LTR.CLEARRESULTS)) {
				JavaScriptclick(driver, LTR.TAIDLOOKUPRATEBUTTON);
				Thread.sleep(2000);
			}
		}
		if (TAID != "") {
			if (VerifyObject(driver, LTR.FieldrequiredTaid)) {
				boolean report = false;
				do {
					click(LTR.TAXAREAID);
					driver.findElement(By.id("requestTAID")).sendKeys(Keys.CONTROL + "a");
					driver.findElement(By.id("requestTAID")).sendKeys(Keys.CONTROL + "x");
					Thread.sleep(2000);
					driver.findElement(By.id("requestTAID")).sendKeys(Keys.CONTROL + "v");
					Thread.sleep(2000);
					if (!VerifyObject(driver, LTR.FieldrequiredTaid)) {
						JavaScriptclick(driver, LTR.TAIDLOOKUPRATEBUTTON);
						report = true;
					}
				} while (report == false);
				JavaScriptclick(driver, LTR.TAIDLOOKUPRATEBUTTON);
			}
		}
		if (ZIP != "") {
			if (VerifyObject(driver, LTR.Fieldrequired)) {
				boolean report = false;
				do {
					click(LTR.ZIP);
					driver.findElement(By.id("requestZip")).sendKeys(Keys.CONTROL + "a");
					driver.findElement(By.id("requestZip")).sendKeys(Keys.CONTROL + "x");
					Thread.sleep(2000);
					driver.findElement(By.id("requestZip")).sendKeys(Keys.CONTROL + "v");
					Thread.sleep(2000);
					if (!VerifyObject(driver, LTR.Fieldrequired)) {
						JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
						report = true;
					}
				} while (report == false);
				JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
			}
		}
		if (VerifyError != "") {
			Thread.sleep(5000);
			if (VerifyObject(driver, LTR.INVALIDADDRESSERRMSG)) {
				waitforElement(180, LTR.INVALIDADDRESSERRMSG, driver);
				assertTrue(LTR.INVALIDADDRESSERRMSG.isDisplayed());

			} else {
				waitforElement(180, LTR.InvalidTAIDmeg, driver);
				assertTrue(LTR.InvalidTAIDmeg.isDisplayed());
			}
		} else {
			waitforElement(180, LTR.CLEARRESULTS, driver);
			JavaScriptclick(driver, LTR.EXPANDALL);
			Thread.sleep(5000);
			float TotalSalesTax = 0.00f;
			float TotalUseTax = 0.00f;
			float TotalRentalTax = 0.00f;
			List<WebElement> Rows = driver.findElements(
					By.xpath("//div/table/tbody/tr/td/table[@class='table table-minimal table-bordered']"));
			k = 2;
			for (int j = 1; j <= Rows.size() - 2; j++) {
				if ((Country.equalsIgnoreCase("United States")) || (State1.equalsIgnoreCase("Colorado"))) {
					// POR-24918 Combined Sales Tax Rate does not match the break down on the Sales
					// Tax Rate in the Look Up Tax Rate Feature
					if (RenatlRate != null) {
						String TAID1 = TAID;
						SalesTaxRate = driver
								.findElement(By.xpath("//tbody/tr[" + (k - 1)
										+ "]/td/span[starts-with(@id,'rate-combined-sales-tax-rate-" + TAID1 + "')]"))
								.getText();
						UseTaxRate = driver.findElement(By.xpath("//tbody/tr[" + (k - 1)
								+ "]/td/span[starts-with(@id,'rate-combined-consumer-use-tax-rate-" + TAID1 + "')]"))
								.getText();
						RentalTaxRate = driver
								.findElement(By.xpath("//tbody/tr[" + (k - 1)
										+ "]/td/span[starts-with(@id,'rate-combined-rental-rate-" + TAID1 + "')]"))
								.getText();
					} else {
						SalesTaxRate = driver
								.findElement(By.xpath("//div/table/tbody/tr[" + k
										+ "]/td/table/tbody/tr/td[4]/span[starts-with(@id,'rate-detail-tax-rate')]"))
								.getText();
						UseTaxRate = driver.findElement(By.xpath("//div/table/tbody/tr[" + k
								+ "]/td/table/tbody/tr/td[5]/span[starts-with(@id,'rate-detail-cut-tax-rate')]"))
								.getText();
						RentalTaxRate = driver
								.findElement(By.xpath("//div/table/tbody/tr[" + k
										+ "]/td/table/tbody/tr/td[6]/span[starts-with(@id,'rate-detail-rental-rate')]"))
								.getText();
					}
				} else {
					SalesTaxRate = driver.findElement(By.xpath("//div/table/tbody/tr/td/table/tbody/tr[" + j
							+ "]/td[4]/span[starts-with(@id,'rate-detail-tax-rate')]")).getText();
					UseTaxRate = driver.findElement(By.xpath("//div/table/tbody/tr/td/table/tbody/tr[" + j
							+ "]/td[5]/span[starts-with(@id,'rate-detail-cut-tax-rate')]")).getText();
					RentalTaxRate = driver.findElement(By.xpath("//div/table/tbody/tr/td/table/tbody/tr[" + j
							+ "]/td[6]/span[starts-with(@id,'rate-detail-rental-rate')]")).getText();
				}
				// Sales tax is N/A
				if (SalesTaxRate.equalsIgnoreCase("N/A")) {
					TotalSalesTax = TotalSalesTax + 0;
				} else {
					SalesReplace = SalesTaxRate.replaceAll("%", "");
					TotalSalesTax = TotalSalesTax + Float.parseFloat(SalesReplace);
				}

				// Use tax is N/A
				if (UseTaxRate.equalsIgnoreCase("N/A")) {
					TotalUseTax = TotalUseTax + 0;
				} else {
					UseReplace = SalesTaxRate.replaceAll("%", "");
					TotalUseTax = TotalUseTax + Float.parseFloat(UseReplace);
				}
				// Rental rate is N/A
				if (RentalTaxRate.equalsIgnoreCase("N/A")) {
					TotalRentalTax = TotalRentalTax + 0;
				} else {
					RentalReplace = RentalTaxRate.replaceAll("%", "");
					TotalRentalTax = TotalRentalTax + Float.parseFloat(RentalReplace);
				}
				// Verifying Rental Rate as per POR-18540 in 7.1
				if (RenatlRate != "") {
					String Rental = String.valueOf(TotalRentalTax);
					String RentalExcelValue = RenatlRate;
					assertTrue(Rental.equalsIgnoreCase(RentalExcelValue));
				}
				if (Country.equalsIgnoreCase("United States")) {
					if (State != "") {
						String strJurisdiction = driver
								.findElement(By.xpath("//div/table/tbody/tr[" + k + "]/td/table/tbody/tr/td[1]/span"))
								.getText();
						String ExcelJurisdiction = State;
						assertTrue(strJurisdiction.equalsIgnoreCase(ExcelJurisdiction));
					}
				} else {
					if (State != "") {
						String strJurisdiction1 = driver
								.findElement(By.xpath("//div/table/tbody/tr/td/table/tbody/tr[2]/td[1]/span"))
								.getText();
						String ExcelJurisdiction1 = State;
						assertTrue(strJurisdiction1.equalsIgnoreCase(ExcelJurisdiction1));
					}
				}
				String strImpositionType = driver
						.findElement(By.xpath("//div/table/tbody/tr[" + k + "]/td/table/tbody/tr/td[3]/span"))
						.getText();

				assertTrue(TaxImpositionType.equalsIgnoreCase(strImpositionType));
				// TAID Verification
				scrollDown(driver);
				if (TAID != "") {
					assertTrue(TAID.equalsIgnoreCase(getText(LTR.TAID)));
				}
				k = k + 2;
			}
			waitforElement(180, LTR.CLEARRESULTS, driver);
			if (VerifyObject(driver, LTR.CLEARRESULTS)) {
				click(LTR.CLEARRESULTS);
				driver.navigate().refresh();
				Thread.sleep(5000);
				// Verifying Lookup Rates when the Clear Results button is clicked, Country is
				// changed in POR-21168
				List<WebElement> CountryList = driver.findElements(By.xpath("//select[@id='requestCountry']/option"));
				assertTrue((CountryList.get(0).getText()).equalsIgnoreCase("United States"));

			} else {
			}
			driver.navigate().refresh();
			waitforElement(180, LTR.ZIP, driver);
		}
		stateName = State;
		MultipleStates = MultipleStates + stateName + ";";

	}

	public void RatesandJurisdictions(String country, String state, String county, String streetaddress, String city,
			String zip, String JurisdictionType, String TaxImpositionType, String verifyError) throws Exception {

		LookupTaxRates LTR = PageFactory.initElements(driver, LookupTaxRates.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String SalesReplace = null, UseReplace = null, SalesTaxRate, UseTaxRate, strJurisdiction, ExcelJurisdiction,
				strJurisdiction1, ExcelJurisdiction1, strJurisdictionType, strImpositionType;
		driver.navigate().refresh();
		waitforElement(180, LTR.LOOKUPTAXRATESLINK, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, LTR.LOOKUPTAXRATESLINK);
		} else {
			click(LTR.LOOKUPTAXRATESLINK);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, LTR.COUNTRYDRPDWN, driver);
		waitforElement(180, LTR.RatesJurisdictions, driver);
		// JavaScriptclick(driver, LTR.RatesJurisdictions);
		waitforElement(180, LTR.ZIP, driver);
		waitforElement(180, "//select[@id='requestState']/option[contains(.,'" + state + "')]", driver);
		if (!country.isEmpty()) {
			selectByValue(LTR.COUNTRYDRPDWN, country);
		}
		if (!streetaddress.isEmpty()) {
			clear(LTR.STREETADDRESS);
			type(LTR.STREETADDRESS, streetaddress);
		}
		if (!county.isEmpty()) {
			clear(LTR.COUNTY);
			type(LTR.COUNTY, county);
		}
		if (!city.isEmpty()) {
			clear(LTR.CITY);
			type(LTR.CITY, city);
		}
		if (!state.isEmpty()) {
			selectByValue(LTR.STATE, state);
			sendkeysTab(LTR.STATE);
			Thread.sleep(2000);
		}
		JavaScriptclick(driver, LTR.ZIP);
		Thread.sleep(2000);
		JavaScripttype(driver, LTR.ZIP, zip);
		sendkeysTab(LTR.ZIP);
		JavaScriptclick(driver, LTR.STREETADDRESS);
		Thread.sleep(2000);
		waitforElement(180, LTR.ADDLOOKUPRATEBUTTON, driver);
		if (!verifyError.isEmpty()) {
			JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
			waitforElement(180, LTR.INVALIDADDRESSERRMSG, driver);
			assertTrue(VerifyObject(driver, LTR.INVALIDADDRESSERRMSG));
		} else {
			JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
			waitforElement(180, LTR.CLEARRESULTS, driver);
			waitforElement(60, LTR.EXPANDALL, driver);
			if (VerifyObject(driver, LTR.EXPANDALL)) {
				JavaScriptclick(driver, LTR.EXPANDALL);
			}
			Thread.sleep(2000);
			float TotalSalesTax = 0.00f;
			float TotalUseTax = 0.00f;
			int k = 2;
			for (int j = 1; j <= LTR.Rows.size(); j++) {
				SalesTaxRate = driver.findElement(By.xpath("//div/table/tbody/tr[" + k
						+ "]/td/table/tbody/tr/td/span[starts-with(@id,'rate-detail-tax-rate')]")).getText();
				UseTaxRate = driver
						.findElement(By.xpath("//div/table/tbody/tr[" + k
								+ "]/td/table/tbody/tr/td/span[starts-with(@id,'rate-detail-cut-tax-rate')]"))
						.getText();
				if (SalesTaxRate.equalsIgnoreCase("N/A")) {
					TotalSalesTax = TotalSalesTax + 0;
				} else {
					SalesReplace = SalesTaxRate.replaceAll("%", "");
					TotalSalesTax = TotalSalesTax + Float.parseFloat(SalesReplace);
				}
				if (UseTaxRate.equalsIgnoreCase("N/A")) {
					TotalUseTax = TotalUseTax + 0;
				} else {
					UseReplace = SalesTaxRate.replaceAll("%", "");
					TotalUseTax = TotalUseTax + Float.parseFloat(UseReplace);
				}
				if (country.equals("United States")) {
					strJurisdiction = driver
							.findElement(By.xpath("//div/table/tbody/tr[" + k
									+ "]/td/table/tbody/tr/td/span[starts-with(@id,'rate-detail-jurisdiction-name')]"))
							.getText();
					ExcelJurisdiction = state;
					assertTrue(ExcelJurisdiction.equalsIgnoreCase(strJurisdiction));
				} else {
					strJurisdiction1 = driver
							.findElement(By.xpath("//div/table/tbody/tr[" + k
									+ "]/td/table/tbody/tr/td/span[starts-with(@id,'rate-detail-jurisdiction-name')]"))
							.getText();
					ExcelJurisdiction1 = state;
					assertTrue(ExcelJurisdiction1.equalsIgnoreCase(strJurisdiction1));
				}
				strJurisdictionType = driver
						.findElement(By.xpath("//div/table/tbody/tr[" + k
								+ "]/td/table/tbody/tr/td/span[starts-with(@id,'rate-detail-jurisdiction-type-name')]"))
						.getText();
				assertTrue(JurisdictionType.equalsIgnoreCase(strJurisdictionType));
				strImpositionType = driver
						.findElement(By.xpath("//div/table/tbody/tr[" + k
								+ "]/td/table/tbody/tr/td/span[starts-with(@id,'rate-detail-imposition-value')]"))
						.getText();
				assertTrue(TaxImpositionType.equalsIgnoreCase(strImpositionType));
				k = k + 2;
			}
			if (VerifyObject(driver, LTR.CLEARRESULTS)) {
				click(LTR.CLEARRESULTS);
			}
			driver.navigate().refresh();
			waitforElement(180, LTR.ZIP, driver);
		}
	}

}
