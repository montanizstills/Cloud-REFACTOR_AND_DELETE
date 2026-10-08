/*###################################################################################################
Name: Configuration.java 
Description: Having all Configuration related functions 
Author: Venkat Reddy Kandula
Date Created:02/06/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.libraries.customer;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import au.com.bytecode.opencsv.CSVReader;
import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.pages.Addseller;
import common.pages.AdminUndoAdjustmentFile;
import common.pages.AdminUserLinks;
import common.pages.ConfigurePage;
import common.pages.Documentcenter;
import common.pages.ExceptionsPage;
import common.pages.FlexField;
import common.pages.GoLiveVerify;
import common.pages.ManageConfiguration;
import common.pages.ManagetaxReturns;
import common.pages.ProductsPage;
import common.pages.ProfessionalUserLinks;
import common.pages.StatesRegistrationPage;
import common.pages.TaxAssistRuleReportPage;
import common.pages.TaxassistantRule;
import common.pages.TestInstanceSandbox;
import common.pages.VertexSMBCustomers;
import common.utilities.CloudData;

public class Configuration extends CommonFunctions {

	private WebDriver driver;
	public static String goliveEndTime;

	public Configuration(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);

	// Taxpayer
	public void EditTaxPayerName(String TaxPayer, String RenameTaxPayer, String FederalId) throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(10000);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + TaxPayer + "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(2000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + TaxPayer
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + TaxPayer + "')]/../div/div/h3/span/a[contains(.,'Edit')]")) {
			WebElement EditCompany = driver.findElement(
					By.xpath("//h2[contains(.,'" + TaxPayer + "')]/../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCompany);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + TaxPayer
				+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]")) {
			WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + TaxPayer
					+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		} else {
			driver.findElement(By.xpath("//h2[contains(.,'" + TaxPayer
					+ "')]/../../../../..//div/div/div/div/h3/span/a[contains(text(),'Edit')]")).click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		}
		waitforElement(180, "//table[@class='table table-bordered state-registration ng-scope']/tbody/tr/td", driver);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.EDITCOMPANYINFOLINK);
		waitforElement(180, CNP.UPDATECOMPANYADDRESS, driver);
		Thread.sleep(2000);
		if (!RenameTaxPayer.isEmpty()) {
			clear(CNP.COMAPNYNAME);
			Thread.sleep(2000);
			type(CNP.COMAPNYNAME, RenameTaxPayer);
			sendkeysTab(CNP.COMAPNYNAME);
			Thread.sleep(2000);
		}
		clear(CNP.FEDERALCODE);
		Thread.sleep(2000);
		type(CNP.FEDERALCODE, FederalId);
		sendkeysTab(CNP.FEDERALCODE);
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.UPDATEBUTTON);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.SPINNER_XPATH, driver);
		waitforElement(180, CNP.CONTACTCONFIRMATION, driver);
		click(CNP.CONTACTCONFIRMATION);
		Thread.sleep(2000);
		waitforElement(180, CNP.RETURNTOHEAD_CONFIGOVERVIEW, driver);
		JavaScriptclick(driver, CNP.ReadtSave);
		// JavaScriptclick(driver, CNP.RETURNTOHEAD_CONFIGOVERVIEW);
		// waitforElement(180, CNP.BEGINREMOVAL, driver);
		Thread.sleep(5000);
		waitforElement(60, "//h2[contains(.,'" + RenameTaxPayer + "')]", driver);
		assertTrue(driver.findElement(By.xpath("//h2[contains(.,'" + RenameTaxPayer + "')]")).isDisplayed());
	}

	public void NewCompanyValidations(String Company_Code, String Fereral_ID, String Company_Name, String Address1,
			String City, String Zip, String State) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.COMPANYICON, driver);
		waitforElement(180, CNP.COMPANYHEADER, driver);
		String TaxpayerCompanyName1 = "";
		String TaxpayerCode1 = "";
		String Address, City1, Zip1, Address2, City2, Zip2;
		if (VerifyObject(driver, CNP.ADDANOTHERCOMPANYLINK)) {
			JavaScriptclick(driver, CNP.ADDANOTHERCOMPANYLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CNP.COMPANYCODE, driver);
		}
		TaxpayerCode1 = Company_Code;
		type(CNP.COMPANYCODE, TaxpayerCode1);
		sendkeysTab(CNP.COMPANYCODE);
		click(CNP.FEDERALCODE);
		JavaScripttype(driver, CNP.FEDERALCODE, Fereral_ID);
		sendkeysTab(CNP.FEDERALCODE);
		TaxpayerCompanyName1 = Company_Name;
		clear(CNP.COMAPNYNAME);
		type(CNP.COMAPNYNAME, TaxpayerCompanyName1);
		Address = "Test Data Address";
		click(CNP.ADDRESS1);
		clear(CNP.ADDRESS1);
		type(CNP.ADDRESS1, Address);
		sendkeysTab(CNP.ADDRESS1);
		City1 = "Test City";
		clear(CNP.CITY);
		type(CNP.CITY, City1);
		sendkeysTab(CNP.CITY);
		selectByValue(CNP.STATE, "Massachusetts");
		Thread.sleep(2000);
		Zip1 = "00000";
		clear(CNP.ZIP);
		click(CNP.ZIP);
		Thread.sleep(2000);
		JavaScripttype(driver, CNP.ZIP, Zip1);
		CNP.ZIP.sendKeys(Keys.CONTROL + "a");
		CNP.ZIP.sendKeys(Keys.CONTROL + "x");
		Thread.sleep(2000);
		CNP.ZIP.sendKeys(Keys.CONTROL + "v");
		Thread.sleep(2000);
		sendkeysTab(CNP.ZIP);
		Thread.sleep(3000);
		click(CNP.CITY);
		click(CNP.ADDCOMPANYBUTTON);
		Thread.sleep(3000);
		waitforElement(180, CNP.INVALIDADDRESSPOPUPEDITBUTTON, driver);
		waitforElement(180, CNP.INVALIDADDRESSPOPUPEDITBUTTON, driver);
		click(CNP.INVALIDADDRESSPOPUPEDITBUTTON);
		Address2 = Address1;
		clear(CNP.ADDRESS1);
		type(CNP.ADDRESS1, Address2);
		sendkeysTab(CNP.ADDRESS1);
		City2 = City;
		clear(CNP.CITY);
		type(CNP.CITY, City2);
		sendkeysTab(CNP.CITY);
		selectByValue(CNP.STATE, State);
		clear(CNP.ZIP);
		click(CNP.ZIP);
		Thread.sleep(2000);
		Zip2 = Zip;
		JavaScripttype(driver, CNP.ZIP, Zip2);
		sendkeysTab(CNP.ZIP);
		Thread.sleep(3000);
		waitforElement(60, CNP.ADDCOMPANYBUTTON, driver);
		if (VerifyObject(driver, CNP.ADDCOMPANYDISABLEBUTTON)) {
			boolean report = false;
			do {
				click(CNP.FEDERALCODE);
				driver.findElement(By.xpath(CNP.FEDCODE)).sendKeys(Keys.CONTROL + "a");
				driver.findElement(By.xpath(CNP.FEDCODE)).sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				driver.findElement(By.xpath(CNP.FEDCODE)).sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				click(CNP.ZIP);
				driver.findElement(By.xpath(CNP.COMPZIP)).sendKeys(Keys.CONTROL + "a");
				driver.findElement(By.xpath(CNP.COMPZIP)).sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				driver.findElement(By.xpath(CNP.COMPZIP)).sendKeys(Keys.CONTROL + "v");
				// Thread.sleep(1000);
				// JavaScripttype(driver,CNP.ZIP, Zip1);
				sendkeysTab(CNP.ZIP);
				Thread.sleep(2000);
				if (!VerifyObject(driver, CNP.ADDCOMPANYDISABLEBUTTON)) {
					waitforElement(60, CNP.ADDCOMPANYBUTTON, driver);
					click(CNP.ADDCOMPANYBUTTON);
					report = true;
				}
			} while (report == false);
		} else {
			// waitforElement(60,CNP.ADDCOMPANYBUTTON,driver);
			click(CNP.ADDCOMPANYBUTTON);
		}
		waitUntilElementInvisibe(180, CNP.SPINNER_XPATH, driver);
		waitforElement(180, CNP.CONTACTCONFIRMATION, driver);
		click(CNP.CONTACTCONFIRMATION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.EDITCOMPANYADDRESS, driver);
		click(CNP.EDITCOMPANYADDRESS);
		waitforElement(180, CNP.UPDATECOMPANYADDRESS, driver);
		JavaScriptclick(driver, CNP.UPDATECOMPANYADDRESS);
		waitforElement(180, CNP.ADDCOMPANYVALIDATIONMSG, driver);
		if (VerifyObject(driver, CNP.ADDCOMPANYVALIDATIONMSG)) {
			System.out.println("Validation message is True");
		}
		click(CNP.CONTACTCONFIRMATION);
		Thread.sleep(3000);
		waitforElement(180, CNP.EDITCOMPANYADDRESS, driver);
	}

	public void NewCompanyVerifyStates(String CompanyName) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
			WebElement EditClick = driver.findElement(
					By.xpath("//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		} else {
			WebElement EditClick = driver.findElement(By.xpath(
					"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, CNP.EDITCOMPANYINFOLINK, driver);
		assertTrue(CNP.EDITCOMPANYINFOLINK.isDisplayed());
		assertTrue(CNP.PUERTORICOSTATE.isDisplayed());
		assertTrue(CNP.DELAWARESTATE.isDisplayed());
		assertTrue(CNP.MONTANASTATE.isDisplayed());
		assertTrue(CNP.NEWHAMPSHIRESTATE.isDisplayed());
		assertTrue(CNP.OREGONSTATE.isDisplayed());
	}

	public void AddTaxPayer(String Country, String companyCode, String companyName, String fereralID, String Address,
			String City, String State, String Zip, String firstName, String lastName) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		// click(CNP.CONFIGURE);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, CNP.COMPANYHEADER, driver);
		if (VerifyObject(driver, CNP.ADDANOTHERCOMPANYLINK)) {
			// scrollDown(driver);
			JavaScriptclick(driver, CNP.ADDANOTHERCOMPANYLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(2000);
		}
		waitforElement(180, CNP.COUNTRY, driver);
		selectByValue(CNP.COUNTRY, Country);
		waitforElement(180, CNP.COMPANYCODE, driver);
		type(CNP.COMPANYCODE, companyCode);
		type(CNP.COMAPNYNAME, companyName);
		Thread.sleep(3000);
		clear(CNP.FEDERALCODE);
		click(CNP.FEDERALCODE);
		Thread.sleep(2000);
		JavaScripttype(driver, CNP.FEDERALCODE, fereralID);
		sendkeysTab(CNP.FEDERALCODE);
		if (VerifyObject(driver, CNP.FEDERALIDERROR)) {
			boolean report = false;
			do {
				CNP.FEDERALCODE.sendKeys(Keys.CONTROL + "a");
				CNP.FEDERALCODE.sendKeys(Keys.CONTROL + "x");
				CNP.FEDERALCODE.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				click(CNP.FEDERALCODE);
				CNP.FEDERALCODE.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				if (!VerifyObject(driver, CNP.FEDERALIDERROR)) {
					report = true;
				}
			} while (report == false);
		}
		Thread.sleep(3000);
		if (firstName != "") {
			type(CNP.firstname, firstName);
			if (lastName != "") {
				type(CNP.lastname, lastName);
			}
			if (VerifyObject(driver, CNP.lastnameValidation)) {
				assertTrue(CNP.firstlastnamevalidationmsg.size() == 2);
				Thread.sleep(1000);
				clear(CNP.firstname);
				type(CNP.firstname, "Prepayment@2");
				Thread.sleep(1000);
				clear(CNP.lastname);
				type(CNP.lastname, "QueueItem");
			}
		}

		clear(CNP.ADDRESS1);
		type(CNP.ADDRESS1, Address);
		sendkeysTab(CNP.ADDRESS1);
		if (VerifyObject(driver, CNP.ADDRESS2)) {
			clear(CNP.ADDRESS2);
			type(CNP.ADDRESS2, City);
			sendkeysTab(CNP.ADDRESS2);
		}
		if (!(Country.equalsIgnoreCase("International"))) {
			clear(CNP.CITY);
			type(CNP.CITY, City);
			sendkeysTab(CNP.CITY);
			waitforElement(180, "//select[@id='state']/option[contains(.,'" + State + "')]", driver);
			selectByValue(CNP.STATE, State);
			if (Browser.equalsIgnoreCase("Chrome")) {
				clear(CNP.ZIP);
				Thread.sleep(2000);
				type(CNP.ZIP, Zip);
				click(CNP.CITY);
			} else {
				click(CNP.ZIP);
				JavaScripttype(driver, CNP.ZIP, Zip);
				sendkeysTab(CNP.ZIP);
			}
		}
		waitforElement(60, CNP.ADDCOMPANYBUTTON, driver);
		if (VerifyObject(driver, CNP.ADDCOMPANYDISABLEBUTTON)) {
			boolean report = false;
			do {
				click(CNP.FEDERALCODE);
				driver.findElement(By.xpath(CNP.FEDCODE)).sendKeys(Keys.CONTROL + "a");
				driver.findElement(By.xpath(CNP.FEDCODE)).sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				driver.findElement(By.xpath(CNP.FEDCODE)).sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				click(CNP.ZIP);
				driver.findElement(By.xpath(CNP.COMPZIP)).sendKeys(Keys.CONTROL + "a");
				driver.findElement(By.xpath(CNP.COMPZIP)).sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				driver.findElement(By.xpath(CNP.COMPZIP)).sendKeys(Keys.CONTROL + "v");
				sendkeysTab(CNP.ZIP);
				Thread.sleep(2000);
				if (!VerifyObject(driver, CNP.ADDCOMPANYDISABLEBUTTON)) {
					waitforElement(60, CNP.ADDCOMPANYBUTTON, driver);
					click(CNP.ADDCOMPANYBUTTON);
					report = true;
				}
			} while (report == false);
		} else {
			click(CNP.ADDCOMPANYBUTTON);
		}
		waitUntilElementInvisibe(180, "//spin-icon[@class='ng-scope']/div[@class='spinner']", driver);
		if (!(Country.equalsIgnoreCase("International"))) {
			waitforElement(180, CNP.CONTACTCONFIRMATION, driver);
			click(CNP.CONTACTCONFIRMATION);
			waitUntilElementInvisibe(180, "//spin-icon[@class='ng-scope']/div[@class='spinner']", driver);
		}
		strTaxpayerCompanyName = strTaxpayerCompanyName + companyName + ";";
		waitforElement(180, CNP.RETURNTOHEAD_CONFIGOVERVIEW, driver);
		Thread.sleep(5000);
		click(CNP.RETURNTOHEAD_CONFIGOVERVIEW);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		// waitforElement(180, CNP.BEGINREMOVAL, driver);
		assertTrue(driver.findElement(By.xpath("//h2[contains(.,'" + companyName + "')]")).isDisplayed());
	}

	public void AddinteranationalTaxPayer(String Country, String companyCode, String companyName, String fereralID,
			String Address, String City) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, CNP.COMPANYHEADER, driver);
		if (VerifyObject(driver, CNP.ADDANOTHERCOMPANYLINK)) {
			JavaScriptclick(driver, CNP.ADDANOTHERCOMPANYLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(2000);
		}
		waitforElement(180, CNP.COUNTRY, driver);
		selectByValue(CNP.COUNTRY, Country);
		waitforElement(180, CNP.COMPANYCODE, driver);
		type(CNP.COMPANYCODE, companyCode);
		type(CNP.COMAPNYNAME, companyName);
		Thread.sleep(3000);
		clear(CNP.FEDERALCODE);
		click(CNP.FEDERALCODE);
		Thread.sleep(2000);
		JavaScripttype(driver, CNP.FEDERALCODE, fereralID);
		sendkeysTab(CNP.FEDERALCODE);
		if (VerifyObject(driver, CNP.FEDERALIDERROR)) {
			boolean report = false;
			do {
				CNP.FEDERALCODE.sendKeys(Keys.CONTROL + "a");
				CNP.FEDERALCODE.sendKeys(Keys.CONTROL + "x");
				CNP.FEDERALCODE.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				click(CNP.FEDERALCODE);
				CNP.FEDERALCODE.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				if (!VerifyObject(driver, CNP.FEDERALIDERROR)) {
					report = true;
				}
			} while (report == false);
		}
		Thread.sleep(3000);
		clear(CNP.ADDRESS1);
		type(CNP.ADDRESS1, Address);
		sendkeysTab(CNP.ADDRESS1);
		if (VerifyObject(driver, CNP.ADDRESS2)) {
			clear(CNP.ADDRESS2);
			type(CNP.ADDRESS2, City);
			sendkeysTab(CNP.ADDRESS2);
		}
		waitforElement(60, CNP.ADDCOMPANYBUTTON, driver);
		if (VerifyObject(driver, CNP.ADDCOMPANYDISABLEBUTTON)) {
			boolean report = false;
			do {
				click(CNP.FEDERALCODE);
				driver.findElement(By.xpath(CNP.FEDCODE)).sendKeys(Keys.CONTROL + "a");
				driver.findElement(By.xpath(CNP.FEDCODE)).sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				driver.findElement(By.xpath(CNP.FEDCODE)).sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				click(CNP.ZIP);
				driver.findElement(By.xpath(CNP.COMPZIP)).sendKeys(Keys.CONTROL + "a");
				driver.findElement(By.xpath(CNP.COMPZIP)).sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				driver.findElement(By.xpath(CNP.COMPZIP)).sendKeys(Keys.CONTROL + "v");
				sendkeysTab(CNP.ZIP);
				Thread.sleep(2000);
				if (!VerifyObject(driver, CNP.ADDCOMPANYDISABLEBUTTON)) {
					waitforElement(60, CNP.ADDCOMPANYBUTTON, driver);
					click(CNP.ADDCOMPANYBUTTON);
					report = true;
				}
			} while (report == false);
		} else {
			click(CNP.ADDCOMPANYBUTTON);
		}
		waitUntilElementInvisibe(180, "//spin-icon[@class='ng-scope']/div[@class='spinner']", driver);
		strTaxpayerCompanyName = strTaxpayerCompanyName + companyName + ";";
		waitforElement(180, CNP.RETURNTOHEAD_CONFIGOVERVIEW, driver);
		Thread.sleep(2000);
		click(CNP.RETURNTOHEAD_CONFIGOVERVIEW);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		// waitforElement(180, CNP.BEGINREMOVAL, driver);
	}

	public void AddDiscounts(String Company_Name, String General_Discount, String CashDiscount,
			String Manufacturers_Coupons, String Retailers_Coupon, String Trade_Discount, String QuantityDiscount,
			String EarlyPayment_Discount, String Flexible_Discount) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(CNP.Dashboard);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(CNP.Configurecompany);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//h2[contains(.,'" + Company_Name
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(2000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company_Name
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company_Name + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
			WebElement EditClick = driver.findElement(By
					.xpath("//h2[contains(.,'" + Company_Name + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		} else {
			WebElement EditClick = driver.findElement(By.xpath(
					"//h2[contains(.,'" + Company_Name + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		}
		waitforElement(180, "//table[@class='table table-bordered state-registration ng-scope']/tbody/tr/td", driver);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.EDITCOMPANYINFOLINK);
		waitforElement(180, CNP.UPDATECOMPANYADDRESS, driver);
		JavaScriptclick(driver, CNP.ADDDISCOUNTCODESLINK);
		waitforElement(60, CNP.GENERALDISCOUNT, driver);
		type(CNP.GENERALDISCOUNT, General_Discount);
		type(CNP.CASHDISCOUNT, CashDiscount);
		type(CNP.MANUFACTURESDISCOUNT, Manufacturers_Coupons);
		type(CNP.RETAILERDISCOUNT, Retailers_Coupon);
		type(CNP.TRADEDISCOUNT, Trade_Discount);
		type(CNP.QUALITYDISCOUNT, QuantityDiscount);
		type(CNP.EARLYPAYMENTDISCOUNT, EarlyPayment_Discount);
		type(CNP.FLEXIBLEDISCOUNT, Flexible_Discount);
		assertTrue(CNP.CLOSEDISCOUNTCODES.isDisplayed());
		scrollDown(driver);
		JavaScriptclick(driver, CNP.CLOSEDISCOUNTCODES);
	}

	public void RemoveAnItemCompany(String Company_Name, String Edit_StartDate, String RemoveCompany) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		String Currentdate = xGetDate("MM/dd/yyyy");
		String FutureDate = xAddDaysToTheDateTime(Currentdate, 1, "MM/dd/yyyy");
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SR.BEGINREMOVAL, driver);
		if (VerifyObject(driver, "//h2[contains(.,'" + Company_Name
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company_Name
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		JavaScriptclick(driver, SR.BEGINREMOVAL);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + Company_Name + "')]/../td/a[contains(.,'Remove')]",
				driver);
		WebElement compremove = driver.findElement(
				By.xpath("//table/tbody/tr/td[contains(.,'" + Company_Name + "')]/../td/a[contains(.,'Remove')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", compremove);
		Thread.sleep(5000);
		if (VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
			click(PUL.OKERROR);
			waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
			WebElement compremove1 = driver.findElement(
					By.xpath("//table/tbody/tr/td[contains(.,'" + Company_Name + "')]/../td/a[contains(.,'Remove')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", compremove1);
			Thread.sleep(2000);
		}

		driver.findElement(By.xpath("//input[@id='remove-customer-end-date']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='remove-customer-end-date']")).sendKeys(FutureDate);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='confirm-remove-customer']")).click();
		Thread.sleep(5000);
		waitforElement(180, SR.RETURNTOCONFIGOVERVIEW, driver);
		JavaScriptclick(driver, SR.RETURNTOCONFIGOVERVIEW);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SR.BEGINREMOVAL, driver);
		if (Edit_StartDate.equalsIgnoreCase("YES")) {
			waitforElement(180, SR.SAVECONFIGURATION, driver);
			assertTrue(SR.SAVECONFIGURATION.isDisplayed());
		} else {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + Company_Name + "')]/../../div/a[contains(text(),'Save Configuration')]")) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
		if (!RemoveCompany.equalsIgnoreCase("YES")) {
			WebElement saveconfig = driver.findElement(By.xpath(
					"//h2[contains(.,'" + Company_Name + "')]/../../div/a[contains(text(),'Save Configuration')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveconfig);
			waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
			if (VerifyObject(driver, CNP.COMPLETECONFIGPOPUP)) {
				click(CNP.COMPLETECONFIGPOPUP);
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(2000);
			waitforElement(180, "//h2[contains(.,'" + Company_Name
					+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']", driver);
			if (VerifyObject(driver, "//h2[contains(.,'" + Company_Name
					+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(10000);
					if (!VerifyObject(driver, "//h2[contains(.,'" + Company_Name
							+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
						report = true;
					}
				} while (report == false);
			}
		}
	}

	public void Removetaxpayer(String Company_Name, String FutureDate, String Customerenddate) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SR.BEGINREMOVAL, driver);
		if (VerifyObject(driver, "//h2[contains(.,'" + Company_Name
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company_Name
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		JavaScriptclick(driver, SR.BEGINREMOVAL);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + Company_Name + "')]/../td/a[contains(.,'Remove')]",
				driver);
		WebElement compremove = driver.findElement(
				By.xpath("//table/tbody/tr/td[contains(.,'" + Company_Name + "')]/../td/a[contains(.,'Remove')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", compremove);
		Thread.sleep(5000);
		if (VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
			click(PUL.OKERROR);
			waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
			WebElement compremove1 = driver.findElement(
					By.xpath("//table/tbody/tr/td[contains(.,'" + Company_Name + "')]/../td/a[contains(.,'Remove')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", compremove1);
			Thread.sleep(2000);
		}

		driver.findElement(By.xpath("//input[@id='remove-customer-end-date']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='remove-customer-end-date']")).sendKeys(FutureDate);
		Thread.sleep(2000);
		if (Customerenddate.equalsIgnoreCase(FutureDate)) {
			assertTrue(CNP.removefuturedatetaxpayer.isDisplayed());
		}
		driver.findElement(By.xpath("//a[@id='confirm-remove-customer']")).click();
		Thread.sleep(5000);
		waitforElement(180, SR.RETURNTOCONFIGOVERVIEW, driver);
		JavaScriptclick(driver, SR.RETURNTOCONFIGOVERVIEW);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SR.BEGINREMOVAL, driver);
		WebElement saveconfig = driver.findElement(By
				.xpath("//h2[contains(.,'" + Company_Name + "')]/../../div/a[contains(text(),'Save Configuration')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveconfig);
		waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
		if (VerifyObject(driver, CNP.COMPLETECONFIGPOPUP)) {
			click(CNP.COMPLETECONFIGPOPUP);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		waitforElement(180,
				"//h2[contains(.,'" + Company_Name + "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']",
				driver);
		if (VerifyObject(driver, "//h2[contains(.,'" + Company_Name
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			int count = 0;
			// Refreshing Page for 5 minutes
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				count++;
				if (count == 60) {
					assertTrue(report, "Configuration not turned to Green with in sync");
					break;
				}
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company_Name
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
	}

	// Custom Categories
	public void AddCustomCategories(String categoryName, String firstSelction, String secondSelction,
			String thirdSelction, String fourthSelction, String fifthSelection, String sixthSelection, String state,
			String country, String category, String exemption) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, "//button[contains(.,'Begin Removal')]", driver);
		click(PP.AddNewCategory);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		assertTrue(VerifyObject(driver, "//a[contains(.,'View All Custom Categories')]/../a[@class='btn pull-right']"));
		type(PP.CATERGORY_NAME, categoryName);
		if (firstSelction != null) {
			ProductnameSplit(driver, firstSelction);
		}
		if (secondSelction != null) {
			ProductnameSplit(driver, secondSelction);
		}
		if (thirdSelction != null) {
			ProductnameSplit(driver, thirdSelction);
		}
		if (fourthSelction != null) {
			ProductnameSplit(driver, fourthSelction);
		}
		if (fifthSelection != null) {
			ProductnameSplit(driver, fifthSelection);
		}
		if (sixthSelection != null) {
			ProductnameSplit(driver, sixthSelection);
		}

		assertTrue(VerifyObject(driver, CNP.saveandclosebtndisabled));
		String[] states = state.split(";");
		String[] categories = category.split(";");

		for (int i = 0; i < states.length; i++) {
			if (country.equals("USA")) {
				JavaScriptclick(driver, PP.USAlabel);
			}
			if (country.equals("Canada")) {
				JavaScriptclick(driver, PP.Canadalabel);
			}
			WebElement jurisdiction = driver.findElement(By.xpath("//li/a[1][contains(text(),'" + states[i] + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", jurisdiction);
			Thread.sleep(2000);
			waitforElement(180, PP.STATE_MINUS, driver);
			new Select(driver
					.findElement(By.xpath("//div/tax-rules/div/div[2]/div[2]/div/div/ng-form/div[1]/a[contains(text(),'"
							+ states[i] + "')]/../../div[2]/div/div[1]/select"))).selectByVisibleText(categories[i]);
			Thread.sleep(2500);
			if (category.equals("Exempt")) {
				if (categoryName.equals("ALExemptCredit1")) {
					type(PP.EXEMPTION, "Credit Sales");
					sendkeysTab(PP.EXEMPTION);
				} else {
					new Select(driver.findElement(
							By.xpath("//div/tax-rules/div/div[2]/div[2]/div/div/ng-form/div[1]/a[contains(text(),'"
									+ states[i] + "')]/../../div[2]/div/div[2]/select")))
											.selectByVisibleText(exemption);
				}
			}
			Thread.sleep(1000);
		}
		click(PP.ADD_CUSTOMTAX_CATEGORY);
		waitforText(180, PP.AddCategory, driver);
		assertTrue(VerifyObject(driver, "//table/tbody/tr/td[contains(text(),'" + categoryName
				+ "')]/../td[4]/span/a[contains(text(),'Remove')]"));
		click(PP.AddCategory);
		Thread.sleep(3000);

		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
	}

	public void RemoveCustomCategories(String Category_Name) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, "//button[contains(.,'Begin Removal')]", driver);
		click(PP.EditCustomCategories);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'" + Category_Name
				+ "')]/../td[4]/span/a[contains(text(),'Remove')]")).click();
		waitforElement(60, PP.removeCategory, driver);
		assertTrue(
				driver.findElement(By.xpath("//p/strong[contains(.,'Are you sure you want to remove this category?')]"))
						.isDisplayed());
		click(PP.removeCategory);
		waitforText(180, PP.AddCategory, driver);
		Thread.sleep(4000);
	}

	public void CustomCatProducts(String Company, String Category_Name, String FirstSelct, String SecSelct,
			String ThirdSelct, String fourSelct, String FifthSelct, String SixSelct, String State, String Category,
			String Subcategory, String Country) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		strCatgName = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		driver.navigate().refresh();
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		// waitforElement(180, "//button[contains(.,'Begin Removal')]", driver);
		driver.findElement(By.xpath("//a[contains(.,'Custom Categories')]")).click();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// selectByValue(PP.paginationExtention, "100 per page");
		Thread.sleep(5000);
		JavaScriptclick(driver, PP.AddCategory);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		type(PP.CATERGORY_NAME, Category_Name);
		Thread.sleep(5000);
		// clicking on product mapping first selection
		ProductSplit(driver, FirstSelct);
		// clicking on product mapping sec selection
		ProductSplit(driver, SecSelct);
		// clicking on product mapping third selection
		ProductSplit(driver, ThirdSelct);
		// clicking on product mapping fourth selection
		ProductSplit(driver, fourSelct);
		// clicking on product mapping fifth selection
		ProductSplit(driver, FifthSelct);
		// clicking on product mapping sixth selection
		// ProductSplit(driver, SixSelct);

		if (Country.equalsIgnoreCase("USA")) {
			JavaScriptclick(driver, PP.USAlabel);
		}
		if (Country.equalsIgnoreCase("Canada")) {
			JavaScriptclick(driver, PP.Canadalabel);
		}
		if (!VerifyObject(driver, CNP.saveandclosebtndisabled)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		Thread.sleep(2000);
		String[] s1 = State.split(",");
		if (s1.length > 1) {
			for (int i = 0; i < s1.length; i++) {
				if (State.equalsIgnoreCase("Canada")) {
					JavaScriptclick(driver, PP.Canadalabel);
				}
				WebElement state = driver.findElement(By.xpath("//li/a[1][contains(text(),'" + s1[i] + "')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", state);
				Thread.sleep(2000);
				waitforElement(180, PP.STATE_MINUS, driver);
				new Select(driver.findElement(By.xpath("//div[@class='accordion-heading']/a[contains(text(),'" + s1[i]
						+ "')]/../../div/div/div/select[@id='-category']"))).selectByVisibleText(Category);
				Thread.sleep(1000);
				if (Subcategory != "") {
					new Select(driver.findElement(By.xpath("//div[@class='accordion-heading']/a[contains(text(),'"
							+ s1[i] + "')]/../../div/div/div/select[@id='-reason']"))).selectByVisibleText(Subcategory);
				}
				Thread.sleep(2500);
				if (Country.equalsIgnoreCase("USA")) {
					JavaScriptclick(driver, PP.USAlabel);
				}
			}
		} else {
			if (State.equalsIgnoreCase("Canada")) {
				JavaScriptclick(driver, PP.Canadalabel);
			}
			WebElement state = driver.findElement(By.xpath("//li/a[1][contains(text(),'" + State + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", state);
			Thread.sleep(2000);
			waitforElement(180, PP.STATE_MINUS, driver);
			new Select(driver.findElement(By.xpath("//div[@class='accordion-heading']/a[contains(text(),'" + State
					+ "')]/../../div/div/div/select[@id='-category']"))).selectByVisibleText(Category);
			Thread.sleep(1000);
			if (Subcategory != "") {
				new Select(driver.findElement(By.xpath("//div[@class='accordion-heading']/a[contains(text(),'" + State
						+ "')]/../../div/div/div/select[@id='-reason']"))).selectByVisibleText(Subcategory);
			}
			Thread.sleep(2500);
		}
		click(PP.ADD_CUSTOMTAX_CATEGORY);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(30, PP.AddCategory, driver);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
	}

	public void UnSupportedCustomCategories(String unsupported_taxCategories) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, "//button[contains(.,'Begin Removal')]", driver);
		JavaScriptclick(driver, PP.AddCategory);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		String uncater = unsupported_taxCategories;
		ArrayList<String> aList = new ArrayList<String>(Arrays.asList(uncater.split(",")));
		for (int k = 0; k < aList.size(); k++) {
			String category = aList.get(k).toString();
			clear(PP.PRODUCTS_QUICK_SEARCH);
			type(PP.PRODUCTS_QUICK_SEARCH, category);
			Thread.sleep(2000);
			if (!VerifyObject(driver, "//p[contains(.,'" + category + "')]")) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
			scrollUp(driver);
			JavaScriptclick(driver, CNP.CONFIGURE);
		}

	}

	public void EditCustomCategories(String Category_Name, String State, String Categorytype, String Subcategory)
			throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, "//button[contains(.,'Begin Removal')]", driver);
		click(PP.EditCustomCategories);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		selectByValue(PP.paginationExtention, "100 per page");
		Thread.sleep(5000);
		WebElement EditCategory = driver.findElement(By.xpath("//td[text()='" + Category_Name + "']/../td[4]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCategory);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (State.equalsIgnoreCase("Canada")) {
			JavaScriptclick(driver, PP.Canadalabel);
		}
		scrollDown(driver);
		driver.findElement(By.xpath("//li/a[1][contains(text(),'" + State + "')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='icon-minus-large icon-plus-large']")).click();
		Thread.sleep(2000);
		JavaScriptscrolltoView(driver,
				driver.findElement(By.xpath("//div[@class='accordion-heading']/a[contains(text(),'" + State
						+ "')]/../../div/div/div/select[@id='-category']")));
		;
		new Select(driver.findElement(By.xpath("//div[@class='accordion-heading']/a[contains(text(),'" + State
				+ "')]/../../div/div/div/select[@id='-category']"))).selectByVisibleText(Categorytype);
		Thread.sleep(1000);
		if (Subcategory != "") {
			new Select(driver.findElement(By.xpath("//div[@class='accordion-heading']/a[contains(text(),'" + State
					+ "')]/../../div/div/div/select[@id='-reason']"))).selectByVisibleText(Subcategory);
		}

		click(PP.ADD_CUSTOMTAX_CATEGORY);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, PP.AddCategory, driver);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);

	}

	public void EditCustomCategoryBeforeConfigPush(String Category_Name, String State, String Categorytype,
			String Subcategory, String Hierarchy) throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, "//button[contains(.,'Begin Removal')]", driver);
		click(PP.EditCustomCategories);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		selectByValue(PP.paginationExtention, "100 per page");
		Thread.sleep(5000);
		WebElement EditCategory = driver.findElement(By.xpath("//td[text()='" + Category_Name + "']/../td[4]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCategory);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (State.equalsIgnoreCase("Canada")) {
			JavaScriptclick(driver, PP.Canadalabel);
		}
		JavaScriptclick(driver, PP.PRODUCTS_QUICK_SEARCH);
		clear(PP.PRODUCTS_QUICK_SEARCH);
		Thread.sleep(2000);
		type(PP.PRODUCTS_QUICK_SEARCH, Hierarchy);
		Thread.sleep(5000);
		waitforElement(180, "//a/p[text()='" + Hierarchy + "']", driver);
		assertTrue(VerifyObject(driver, "//p[text()='" + Hierarchy + "']"));
		driver.findElement(By.xpath("//a/p[text()='" + Hierarchy + "']")).click();
		sendkeybackSpace(PP.PRODUCTS_QUICK_SEARCH);
		scrollDown(driver);
		driver.findElement(By.xpath("//li/a[1][contains(text(),'" + State + "')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='icon-minus-large icon-plus-large']")).click();
		Thread.sleep(2000);
		JavaScriptscrolltoView(driver,
				driver.findElement(By.xpath("//div[@class='accordion-heading']/a[contains(text(),'" + State
						+ "')]/../../div/div/div/select[@id='-category']")));
		;
		new Select(driver.findElement(By.xpath("//div[@class='accordion-heading']/a[contains(text(),'" + State
				+ "')]/../../div/div/div/select[@id='-category']"))).selectByVisibleText(Categorytype);
		Thread.sleep(1000);
		if (Subcategory != "") {
			new Select(driver.findElement(By.xpath("//div[@class='accordion-heading']/a[contains(text(),'" + State
					+ "')]/../../div/div/div/select[@id='-reason']"))).selectByVisibleText(Subcategory);
		}

		click(PP.ADD_CUSTOMTAX_CATEGORY);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, PP.AddCategory, driver);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);

	}

	public void CustomCategoriesbeforeOseriesPush(String Category_Name, String Company, String CategorySelection)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// End is writing to break the loop if there is a error while uploading the
		// invalid product sample
		// Verifying the Product state, and refreshing if it is in Yellow
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(10000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div[contains(@id,'mappings')]/h3/span/a[contains(.,'+ Add')]")) {
			WebElement AddProducts = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div[contains(@id,'mappings')]/h3/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProducts);
		} else {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
				WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
				WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
			}
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// waitforElement(180,PP.UPLOADFILE,driver);
		waitforElement(180, PP.BUYSELLPRODT, driver);
		waitforElement(180, PP.GENERAL, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, PP.PRODUCTS_QUICK_SEARCH);
		clear(PP.PRODUCTS_QUICK_SEARCH);
		Thread.sleep(2000);
		type(PP.PRODUCTS_QUICK_SEARCH, Category_Name);
		Thread.sleep(5000);
		waitforElement(180, "//a/p[text()='" + Category_Name + "']", driver);
		assertTrue(VerifyObject(driver, "//p[text()='" + Category_Name + "']"));
		sendkeybackSpace(PP.PRODUCTS_QUICK_SEARCH);
		waitforElement(180, "//p[text()='" + CategorySelection + "']", driver);
		/*
		 * assertTrue( VerifyObject(driver, "//p[text()='" + Category_Name +
		 * "']/../p[text()='" + CategorySelection + "']"));
		 */JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
	}

	public void CustomCategoriesAfterOseriesPush(String Category_Name, String Company, String CategorySelection)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// Verifying the Product state, and refreshing if it is in Yellow
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(10000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div[contains(@id,'mappings')]/h3/span/a[contains(.,'+ Add')]")) {
			WebElement AddProducts = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div[contains(@id,'mappings')]/h3/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProducts);
		} else {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
				WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
				WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
			}
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.BUYSELLPRODT, driver);
		waitforElement(180, PP.GENERAL, driver);
		Thread.sleep(5000);
		clear(PP.PRODUCTS_QUICK_SEARCH);
		Thread.sleep(3000);
		type(PP.PRODUCTS_QUICK_SEARCH, Category_Name);
		Thread.sleep(5000);
		waitforElement(180, "//a/p[text()='" + Category_Name + "']", driver);
		assertTrue(VerifyObject(driver, "//a/p[text()='" + Category_Name + "']"));
		sendkeybackSpace(PP.PRODUCTS_QUICK_SEARCH);
		waitforElement(180, "//p[text()='" + CategorySelection + "']", driver);
		/*
		 * assertTrue( VerifyObject(driver, "//a/p[text()='" + Category_Name +
		 * "']/../p[text()='" + CategorySelection + "']"));
		 */JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
	}

	// Jurisdictions
	public void AddJurisdictions(String CompanyName, String Country, String State, String Jurisdiction,
			String RegNumber, String FilingMethod, String FilingFrequency, String ThreePosition, String PaymentEFT,
			String BusinessNo, String QuebecNo, String PrepaymentType, String Prepaymentamount, String PerceantageRule,
			String Physicalpresence, String Importreversecharge, String claimingreliefact) throws Exception {
		String USreg, CANreg;
		WebElement jur = null;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);

		if (!VerifyObject(driver, CNP.EDITCOMPANYINFOLINK)) {
			waitforElement(180, CNP.CONFIGURE, driver);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, CNP.CONFIGURE);
			} else {
				JavaScriptclick(driver, CNP.CONFIGURE);
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
			// After progress busy complete, its takes 5 secs to enable the objects in the
			// page
			// waitforElement(180, CNP.BEGINREMOVAL, driver);
			if (!VerifyObject(driver, SR.canadaLabel)) {
				if (VerifyObject(driver,
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
					WebElement EditClick = driver.findElement(By.xpath(
							"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				} else {
					WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
							+ "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				}
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, SR.canadaLabel, driver);
			waitforElement(60, SR.INTERNATIONAL, driver);
		}

		if (Country.equalsIgnoreCase("CANADA")) {
			JavaScriptclick(driver, SR.canadaLabel);
			Thread.sleep(3000);
		}
		if (Country.equalsIgnoreCase("INTERNATIONAL")) {
			JavaScriptclick(driver, SR.INTERNATIONAL);
			Thread.sleep(3000);
		}
		JurLink1 = Jurisdiction;
		USreg = "//td/div/strong[text()='" + State
				+ "']/../../../td[@class='text-center align-middle ng-hide']/div/a[contains(text(),'Return(s)')]";
		CANreg = "//strong[text()='" + State
				+ "']/../../th[@class='text-center align-middle ng-hide']/div/a[contains(text(),'Return(s)')]";
		if (!VerifyObject(driver, CNP.RETURNTOCOMPOVERVIEWHEAD)) {
			if (VerifyObject(driver, CANreg)) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			}
			if (VerifyObject(driver, USreg)) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			}
		}
		if (VerifyObject(driver,
				"//td/div/strong[text()='" + State + "']/../../../td/div/a[contains(text(),'Return(s)')]")) {
			WebElement Regs = driver.findElement(By
					.xpath("//td/div/strong[text()='" + State + "']/../../../td/div/a[contains(text(),'Return(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Regs);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(2000);
		}
		if (VerifyObject(driver, "//strong[text()='" + State + "']/../../th/div/a[contains(text(),'Return(s)')]")) {
			WebElement CanRegs = driver.findElement(
					By.xpath("//strong[text()='" + State + "']/../../th/div/a[contains(text(),'Return(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", CanRegs);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(2000);
		}
		if (Country.equalsIgnoreCase("INTERNATIONAL")) {
			clear(SR.STQUICKSRCH);
			Thread.sleep(1000);
			type(SR.STQUICKSRCH, State);
			waitforElement(180, "//strong[text()='" + State + "']", driver);
			WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../div/div/input"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			waitforElement(60, SR.VATNUMBER, driver);
			type(SR.VATNUMBER, RegNumber);
			if (Physicalpresence.equalsIgnoreCase("Yes")) {
				click(SR.PhysicalPresenceCheckbox);
			}
			if (Importreversecharge.equalsIgnoreCase("Yes")) {
				click(SR.ImportReverseChargeCheckbox);
			}
			WebElement Save = driver.findElement(By.xpath(
					"//strong[text()='" + State + "']/../../../td/ng-form/descendant::button[contains(.,'Save')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Save);
			Thread.sleep(3000);
			assertEquals(false, Save.isDisplayed());
		} else {
			if (JurLink1 != "") {
				Thread.sleep(5000);
				if (JurLink1.contains("'")) {
					JurLink1.split("'");

					waitforElement(60, "//li/a/span[contains(.,\"" + JurLink1 + "\")]", driver);
					if (Browser.equalsIgnoreCase("Safari")) {
						driver.findElement(By.xpath("//li/a/span[contains(.,\"" + JurLink1 + "\")]")).click();
					} else {
						jur = driver.findElement(By.xpath("//li/a/span[contains(.,\"" + JurLink1 + "\")]"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
					}
				} else {
					waitforElement(60, "//li/a/span[text()='" + JurLink1 + "']", driver);
					Thread.sleep(5000);
					if (Browser.equalsIgnoreCase("Safari")) {
						driver.findElement(By.xpath("//li/a/span[text()='" + JurLink1 + "']")).click();
					} else {
						jur = driver.findElement(By.xpath("//li/a/span[text()='" + JurLink1 + "']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
					}
				}
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, SR.REGISTRATIONNUMBER, driver);
			clear(SR.REGISTRATIONNUMBER);
			if (RegNumber.contains("RND")) {
				RegNumber = RND();
			}

			if (VerifyObject(driver, SR.multiplestateForm)) {
				if (!SR.multiplestateForm.isSelected()) {
					SR.multiplestateForm.click();
				}
			}

			type(SR.REGISTRATIONNUMBER, RegNumber);
			sendkeysTab(SR.REGISTRATIONNUMBER);
			selectByValue(SR.FILINGMETHOD, FilingMethod);
			selectByValue(SR.FILINGFREQUENCY, FilingFrequency);
			Thread.sleep(2000);
			if (VerifyObject(driver, SR.Continuebutton)) {
				JavaScriptclick(driver, SR.Continuebutton);
				Thread.sleep(2000);
			} else {
				if (FilingFrequency.equalsIgnoreCase("Occasional")
						|| (FilingFrequency.equalsIgnoreCase("Occasional/Annually"))) {
					waitforElement(180, SR.Continuebutton, driver);
					JavaScriptclick(driver, SR.Continuebutton);
					Thread.sleep(2000);
				}
			}
			sendkeysTab(SR.FILINGFREQUENCY);
			if (ThreePosition != "") {
				type(SR.THREEPOSITION, ThreePosition);
			}
			if (PaymentEFT != "") {
				click(SR.PAYMENTVIAEFT);
			}
			if ((RegNumber == "") && ((PaymentEFT != ""))) {
				waitforElement(120, SR.RETURNTOREG, driver);
				click(SR.RETURNTOREG);
				waitforElement(120, SR.EXITREG, driver);
				click(SR.EXITREG);
			}
			if (BusinessNo != "") {
				type(SR.BUSINESSNO, BusinessNo);
			}
			if (QuebecNo != "") {
				type(SR.QUEBECNO, QuebecNo);
			}

			if (claimingreliefact.equalsIgnoreCase("Yes")) {
				click(SR.ClaimingReliefActTaxCredit);
				Thread.sleep(2000);
			}

			// prepayment value
			if (VerifyObject(driver, SR.Prepaymentdetails)) {
				waitforElement(120, SR.Prepaymentamountlabel, driver);
				if ((PrepaymentType != "")) {
					waitforElement(120, SR.Prepaymentamountlabel, driver);
					selectByValue(SR.Prepaymenttype, PrepaymentType);
					if (Prepaymentamount != "") {
						waitforElement(120, SR.Prepaymentvalue, driver);
						clear(SR.Prepaymentvalue);
						type(SR.Prepaymentvalue, Prepaymentamount);
						if (VerifyObject(driver, SR.Percetagebasis)) {
							type(SR.Percetagebasis, PerceantageRule);
						}
					}
				}
			}
			click(SR.SAVEBUTTON);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(120, SR.returnToCompanyOverView, driver);
			assertTrue(
					driver.findElement(By.xpath("//li/a/span[contains(.,\"" + JurLink1 + "\")]/../i")).isDisplayed());
			JavaScriptclick(driver, SR.returnToCompanyOverView);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(120, SR.RETURNTOCONFIGOVERVIEW, driver);
		}
	}

	public void AddJurisdictions(String CompanyName, String Country, String State, String Jurisdiction,
			String RegNumber, String FilingMethod, String FilingFrequency, String ThreePosition, String PaymentEFT,
			String BusinessNo, String QuebecNo, String PrepaymentType, String Prepaymentamount, String PerceantageRule,
			String Physicalpresence, String Importreversecharge, String deductions, String FilingMethodType,
			String scheduleoptions, String otherdeductions) throws Exception {
		String USreg, CANreg;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		if (!VerifyObject(driver, CNP.EDITCOMPANYINFOLINK)) {
			waitforElement(180, CNP.CONFIGURE, driver);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, CNP.CONFIGURE);
			} else {
				click(CNP.CONFIGURE);
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
			// page
			waitforElement(180, CNP.BEGINREMOVAL, driver);
			if (!VerifyObject(driver, SR.canadaLabel)) {
				if (VerifyObject(driver,
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
					WebElement EditClick = driver.findElement(By.xpath(
							"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				} else {
					WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
							+ "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				}
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, SR.canadaLabel, driver);
			waitforElement(60, SR.INTERNATIONAL, driver);
		}
		if (Country.equalsIgnoreCase("CANADA")) {
			JavaScriptclick(driver, SR.canadaLabel);
			Thread.sleep(5000);
		}
		if (Country.equalsIgnoreCase("INTERNATIONAL")) {
			JavaScriptclick(driver, SR.INTERNATIONAL);
			Thread.sleep(5000);
		}
		JurLink1 = Jurisdiction;
		USreg = "//td/div/strong[text()='" + State
				+ "']/../../../td[@class='text-center align-middle ng-hide']/div/a[contains(text(),'Return(s)')]";
		CANreg = "//strong[text()='" + State
				+ "']/../../th[@class='text-center align-middle ng-hide']/div/a[contains(text(),'Return(s)')]";
		if (!VerifyObject(driver, CNP.RETURNTOCOMPOVERVIEWHEAD)) {
			if (VerifyObject(driver, CANreg)) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			}
			if (VerifyObject(driver, USreg)) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			}
		}
		if (VerifyObject(driver,
				"//td/div/strong[text()='" + State + "']/../../../td/div/a[contains(text(),'Return(s)')]")) {
			WebElement Regs = driver.findElement(By
					.xpath("//td/div/strong[text()='" + State + "']/../../../td/div/a[contains(text(),'Return(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Regs);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(3000);
		}
		if (VerifyObject(driver, "//strong[text()='" + State + "']/../../th/div/a[contains(text(),'Return(s)')]")) {
			WebElement CanRegs = driver.findElement(
					By.xpath("//strong[text()='" + State + "']/../../th/div/a[contains(text(),'Return(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", CanRegs);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(3000);
		}
		if (Country.equalsIgnoreCase("US_Territories")) {
			WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		}
		if (State.equalsIgnoreCase("INTERNATIONAL")) {
			WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../div/div/input"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			waitforElement(60, SR.VATNUMBER, driver);
			type(SR.VATNUMBER, "11132");
			if (Physicalpresence.equalsIgnoreCase("Yes")) {
				click(SR.PhysicalPresenceCheckbox);
			}
			if (Importreversecharge.equalsIgnoreCase("Yes")) {
				click(SR.ImportReverseChargeCheckbox);
			}
			WebElement Save = driver.findElement(By.xpath(
					"//strong[text()='" + State + "']/../../../td/ng-form/descendant::button[contains(.,'Save')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Save);
			Thread.sleep(3000);
		} else {
			if (JurLink1 != "") {
				Thread.sleep(5000);
				if (JurLink1.contains("'")) {
					JurLink1.split("'");
					waitforElement(60, "//li/a/span[contains(.,\"" + JurLink1 + "\")]", driver);
					if (Browser.equalsIgnoreCase("Safari")) {
						driver.findElement(By.xpath("//li/a/span[contains(.,\"" + JurLink1 + "\")]")).click();
					} else {
						WebElement jur = driver.findElement(By.xpath("//li/a/span[contains(.,\"" + JurLink1 + "\")]"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
					}
				} else {
					waitforElement(60, "//li/a/span[text()='" + JurLink1 + "']", driver);
					Thread.sleep(5000);
					if (Browser.equalsIgnoreCase("Safari")) {
						driver.findElement(By.xpath("//li/a/span[text()='" + JurLink1 + "']")).click();
					} else {
						WebElement jur = driver.findElement(By.xpath("//li/a/span[text()='" + JurLink1 + "']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
					}
				}
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				waitforElement(180, SR.REGISTRATIONNUMBER, driver);
				if (scheduleoptions.equalsIgnoreCase("Yes")) {

					click(SR.CHKBOX1);
					click(SR.CHKBOX2);
					if (VerifyObject(driver, SR.CHKBOX3)) {
						click(SR.CHKBOX3);
					}
					click(SR.CHKBOX4);
					if (VerifyObject(driver, SR.CHKBOX5)) {
						click(SR.CHKBOX5);
					}

				}
				clear(SR.REGISTRATIONNUMBER);
				if (RegNumber.contains("RND")) {
					RegNumber = RND();
				}
				type(SR.REGISTRATIONNUMBER, RegNumber);
				sendkeysTab(SR.REGISTRATIONNUMBER);
				selectByValue(SR.FILINGMETHOD, FilingMethod);
				selectByValue(SR.FILINGFREQUENCY, FilingFrequency);
				if (deductions != "") {
					type(SR.FIELD1, deductions);
					Thread.sleep(2000);
					selectByValue(SR.FIELDFILING, FilingMethodType);
				}
				if (FilingFrequency.equalsIgnoreCase("Occasional")) {
					Thread.sleep(2000);
					click(SR.Continuebutton);
					Thread.sleep(2000);
				}
				sendkeysTab(SR.FILINGFREQUENCY);
				if (ThreePosition != "") {
					type(SR.THREEPOSITION, ThreePosition);
				}
				if (PaymentEFT != "") {
					click(SR.PAYMENTVIAEFT);
				}
				if ((RegNumber == "") && ((PaymentEFT != ""))) {
					waitforElement(120, SR.RETURNTOREG, driver);
					click(SR.RETURNTOREG);
					waitforElement(120, SR.EXITREG, driver);
					click(SR.EXITREG);
				}
				if (BusinessNo != "") {
					type(SR.BUSINESSNO, BusinessNo);
				}
				if (QuebecNo != "") {
					type(SR.QUEBECNO, QuebecNo);
				}
				// prepayment value
				if (VerifyObject(driver, SR.Prepaymentdetails)) {
					waitforElement(120, SR.Prepaymentamountlabel, driver);
					if ((PrepaymentType != "")) {
						waitforElement(120, SR.Prepaymentamountlabel, driver);
						selectByValue(SR.Prepaymenttype, PrepaymentType);
						if (Prepaymentamount != "") {
							waitforElement(120, SR.Prepaymentvalue, driver);
							clear(SR.Prepaymentvalue);
							type(SR.Prepaymentvalue, Prepaymentamount);
							if (VerifyObject(driver, SR.Percetagebasis)) {
								type(SR.Percetagebasis, PerceantageRule);
							}
						}
					}
				}

				if (otherdeductions != "") {

					type(SR.deductions1, "75");
					type(SR.deductions2, "I am Venkat");

				}

				click(SR.SAVEBUTTON);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				Thread.sleep(3000);
				waitforElement(120, SR.returnToCompanyOverView, driver);
				JavaScriptclick(driver, SR.returnToCompanyOverView);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				Thread.sleep(3000);
				waitforElement(120, SR.RETURNTOCONFIGOVERVIEW, driver);

			}
		}
	}

	public void AddCoBranchNumbers(String CompanyName, String Country, String State, String Jurisdiction,
			String BranchNumber, String Jurisdictioncode, String UtilUse207) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);

		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		Thread.sleep(5000);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		assertTrue(CNP.COBRANCHIDS.isDisplayed());

		JavaScriptclick(driver, CNP.COBRANCHNUMBERS);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);

		click(CNP.SITECODESSEARCH);
		clear(CNP.SITECODESSEARCH);
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.JURISDICTIONRADIO);
		Thread.sleep(5000);
		type(CNP.SITECODESSEARCH, Jurisdiction);
		Thread.sleep(7000);
		if (VerifyObject(driver, "//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'" + Jurisdictioncode
				+ "')]/../td/div/a[contains(.,'Add')]")) {
			WebElement d = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
					+ Jurisdictioncode + "')]/../td/div/a[contains(.,'Add')]"));
			JavaScriptclick(driver, d);
		}
		if (VerifyObject(driver, "//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'" + Jurisdictioncode
				+ "')]/../td/div/a[contains(.,'Edit')]")) {
			WebElement d1 = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
					+ Jurisdictioncode + "')]/../td/div/a[contains(.,'Edit')]"));
			JavaScriptclick(driver, d1);
		}
		Thread.sleep(6000);
		click(CNP.BRANCHIDVALUE);
		Thread.sleep(2000);
		type(CNP.BRANCHIDVALUE, BranchNumber);
		Thread.sleep(3000);
		if (UtilUse207.equalsIgnoreCase("Yes")) {
			click(CNP.Utilities207Checkbox);
		}
		JavaScriptclick(driver, CNP.BRANCHIDSAVE);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.WARNINGPOPUP)) {
			JavaScriptclick(driver, CNP.NOACCEPT);
			JavaScriptclick(driver, CNP.BRANCHIDCANCEL);
			Thread.sleep(5000);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.RETURNTOCOMPOVERVIEW);
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.READYTOSAVE);
	}

	public void SiteCodesEditAfterOSPush(String CompanyName, String Jurisdiction1, String State, String Sitecode_Edit)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		Companynamesplit = CompanyName;
		CompnameSplit(driver, Companynamesplit, "statereg");
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, "//table[@class='table table-bordered state-registration ng-scope']/tbody/tr/td", driver);
		if (VerifyObject(driver, "//h2[contains(.,'" + Companynamesplit
				+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")) {
			driver.findElement(By.xpath("//h2[contains(.,'" + Companynamesplit
					+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")).click();
		}
		waitforElement(60, CNP.MOSITECODES, driver);
		JavaScriptclick(driver, CNP.MOSITECODES);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(CNP.SITECODESSEARCH);
		Thread.sleep(3000);
		clear(CNP.SITECODESSEARCH);
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.JURISDICTIONCODERADIO);
		Thread.sleep(2000);
		click(CNP.SITECODESSEARCH);
		type(CNP.SITECODESSEARCH, Jurisdiction1);
		Thread.sleep(5000);
		WebElement sidecode = driver.findElement(By.xpath("//tr/td[contains(.,'" + State + "')]/../td[contains(.,'"
				+ Jurisdiction1 + "')]/../td/div/a[contains(.,'Edit')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", sidecode);
		Thread.sleep(6000);
		clear(CNP.ADDSITECODEVALUE);
		Thread.sleep(2000);
		type(CNP.ADDSITECODEVALUE, Sitecode_Edit);
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.BRANCHIDSAVE);
		Thread.sleep(5000);
		assertTrue(CNP.READYTOSAVE.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[contains(.,'" + State + "')]/../td[contains(.,'" + Jurisdiction1
				+ "')]/../td[contains(.,'" + Sitecode_Edit + "')]")).isDisplayed());
	}

	public void DeleteCoBranchNumbers(String CompanyName, String Country, String State, String Jurisdiction,
			String Jurisdictioncode, String UtilUse207) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.COBRANCHNUMBERS);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGUREBRANCHNOSTEXT, driver);
		waitforElement(180, CNP.SITECODESSEARCH, driver);
		click(CNP.SITECODESSEARCH);
		clear(CNP.SITECODESSEARCH);
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.JURISDICTIONCODERADIO);
		Thread.sleep(5000);
		type(CNP.SITECODESSEARCH, Jurisdictioncode);
		Thread.sleep(7000);
		if (VerifyObject(driver, "//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'" + Jurisdictioncode
				+ "')]/../td/div/a[contains(.,'Add')]")) {
			WebElement d = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
					+ Jurisdictioncode + "')]/../td/div/a[contains(.,'Add')]"));
			d.click();
		}
		if (VerifyObject(driver, "//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'" + Jurisdictioncode
				+ "')]/../td/div/a[contains(.,'Edit')]")) {
			WebElement d1 = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
					+ Jurisdictioncode + "')]/../td/div/a[contains(.,'Edit')]"));
			d1.click();
		}
		Thread.sleep(6000);
		clear(CNP.BRANCHIDVALUE);
		Thread.sleep(3000);
		if (CNP.Utilities207Checkbox.isSelected()) {
			click(CNP.Utilities207Checkbox);
		}
		JavaScriptclick(driver, CNP.BRANCHIDSAVE);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.WARNINGPOPUP)) {
			JavaScriptclick(driver, CNP.NOACCEPT);
			Thread.sleep(5000);
		}

	}

	public void DeleteMOSiteCodes(String CompanyName, String Country, String State, String Jurisdiction,
			String Jurisdictioncode) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		Thread.sleep(6000);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.MOSITECODES);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.SITECODESSEARCH, driver);
		click(CNP.SITECODESSEARCH);
		clear(CNP.SITECODESSEARCH);
		JavaScriptclick(driver, CNP.JURISDICTIONCODERADIO);
		Thread.sleep(5000);
		click(CNP.SITECODESSEARCH);
		type(CNP.SITECODESSEARCH, Jurisdictioncode);
		Thread.sleep(7000);
		if (VerifyObject(driver, "//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'" + Jurisdictioncode
				+ "')]/../td/div/a[contains(.,'Add')]")) {
			WebElement d = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
					+ Jurisdictioncode + "')]/../td/div/a[contains(.,'Add')]"));
			JavaScriptclick(driver, d);
		}
		if (VerifyObject(driver, "//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'" + Jurisdictioncode
				+ "')]/../td/div/a[contains(.,'Edit')]")) {
			WebElement d1 = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
					+ Jurisdictioncode + "')]/../td/div/a[contains(.,'Edit')]"));
			JavaScriptclick(driver, d1);
		}
		Thread.sleep(6000);
		clear(CNP.ADDSITECODEVALUE);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.BRANCHIDSAVE);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.WARNINGPOPUP)) {
			JavaScriptclick(driver, CNP.NOACCEPT);
			Thread.sleep(5000);
		}
	}

	public void VerifyUtilUse207(String CompanyName, String Country, String State, String Jurisdiction,
			String Jurisdictioncode) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.COBRANCHNUMBERS);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.SITECODESSEARCH, driver);
		JavaScriptclick(driver, CNP.JURISDICTIONCODERADIO);
		Thread.sleep(2000);
		click(CNP.SITECODESSEARCH);
		clear(CNP.SITECODESSEARCH);
		Thread.sleep(5000);
		type(CNP.SITECODESSEARCH, Jurisdictioncode);
		Thread.sleep(5000);
		String S = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[4]/div")).getText();
		if (S.isEmpty()) {
			assertTrue(true);

		}
	}

	public void JuridictionONOFF(String Company_Name, String Canada_State, String State) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(CNP.Dashboard);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(CNP.Configurecompany);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(500);
		if (VerifyObject(driver, "//h2[contains(.,'" + Company_Name
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(2000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company_Name
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company_Name + "')]/../div/div/h3/span/a[contains(.,'Edit')]")) {
			WebElement EditCompany = driver.findElement(
					By.xpath("//h2[contains(.,'" + Company_Name + "')]/../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCompany);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company_Name
				+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]")) {
			WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + Company_Name
					+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		} else {
			driver.findElement(By.xpath("//h2[contains(.,'" + Company_Name
					+ "')]/../../../../..//div/div/div/div/h3/span/a[contains(text(),'Edit')]")).click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		}
		waitforElement(180, "//table[@class='table table-bordered state-registration ng-scope']/tbody/tr/td", driver);
		Thread.sleep(5000);
		if (Canada_State.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, SR.canadaLabel);
			waitforElement(180, SR.FIRSTROWCANADA, driver);
		} else {
			JavaScriptclick(driver, SR.USLabel);
		}
		if (Canada_State.equalsIgnoreCase("Yes")) {
			WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../div/div/label"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		} else {
			WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/label"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		}
		System.out.println(State);
		waitforElement(180, SR.RemoveJuridiction, driver);
		if (VerifyObject(driver, SR.RemoveJuridiction)) {
			Thread.sleep(2000);
			SR.RemoveJuridiction.click();
			Thread.sleep(3000);
		}
		scrollUp(driver);
		click(CNP.Dashboard);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);

	}

	public void InternationalRegistrations(String CompanyName, String State, String EffectiveDate, String Reg_Number,
			String PhysicalPresence, String ImportReverceCharge) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		if (VerifyObject(driver, CNP.RETURNTOCOMPOVERVIEWHEAD)) {
			JavaScriptclick(driver, CNP.RETURNTOCOMPOVERVIEWHEAD);
			waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		}
		Companynamesplit = CompanyName;
		CompnameSplit(driver, Companynamesplit, "statereg");
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, "//table[@class='table table-bordered state-registration ng-scope']/tbody/tr/td", driver);
		if (VerifyObject(driver, "//h2[contains(.,'" + Companynamesplit
				+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")) {
			driver.findElement(By.xpath("//h2[contains(.,'" + Companynamesplit
					+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")).click();
		}
		waitforElement(180, CNP.EDITCOMPANYADDRESS, driver);
		JavaScriptclick(driver, SR.INTERNATIONAL);
		Thread.sleep(5000);

		if (VerifyObject(driver, SR.INTERNATIONALPAGINATION)) {
			waitforElement(180, SR.INTERNATIONALPAGINATION, driver);
			selectByValue(SR.INTERNATIONALPAGINATION, "100 per page");
			click(SR.PAGE3);
			Thread.sleep(5000);
		}
		waitforElement(90, SR.STQUICKSRCH, driver);
		type(SR.STQUICKSRCH, State);
		waitforElement(180, "//strong[text()='" + State + "']", driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../div/div/label"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		Thread.sleep(2000);
		if (EffectiveDate != "") {
			driver.findElement(By.xpath("//strong[text()='" + State + "']/../../../td/div/span/p")).getText();
			driver.findElement(By.xpath("//strong[text()='" + State + "']/../../../td/div/span/a[contains(.,'Edit')]"))
					.click();
			waitforElement(180, SR.EFFDATESAVE, driver);
			driver.findElement(By.xpath("//input[@id='enable-today-future']")).clear();
			driver.findElement(By.xpath("//input[@id='enable-today-future']")).sendKeys(EffectiveDate);
			Thread.sleep(2000);
			JavaScriptclick(driver, SR.EFFDATESAVE);
			Thread.sleep(2000);
		}
		if ((Reg_Number) != "") {
			waitforElement(60, SR.VATNUMBER, driver);
			type(SR.VATNUMBER, Reg_Number);
			if (PhysicalPresence.equalsIgnoreCase("Yes")) {
				click(SR.PhysicalPresenceCheckbox);
				Thread.sleep(3000);
			}
			if (ImportReverceCharge.equalsIgnoreCase("Yes")) {
				click(SR.ImportReverseChargeCheckbox);
				Thread.sleep(3000);
			}
			WebElement Save = driver.findElement(By.xpath(
					"//strong[text()='" + State + "']/../../../td/ng-form/descendant::button[contains(.,'Save')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Save);
			Thread.sleep(3000);
		}
		/*
		 * // Turning Off the Country After Save if
		 * (CloudData.get_data("Country_OFF").equalsIgnoreCase("Yes")) { String intjur =
		 * CloudData.get_data("State"); driver.findElement(By.xpath("//strong[text()='"
		 * + intjur + "']/../div/div/label")).click(); // WebElement OFF = //
		 * driver.findElement(By.xpath("//strong[text()='"+CloudData.get_data("State")+
		 * "']/../div/div/input")); // ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", OFF); waitforElement(30,
		 * SR.RMVEJUR, driver); click(SR.POPUPCANCEL); waitforElement(90,
		 * "//strong[text()='" + CloudData.get_data("State") +
		 * "']/../../td/ng-form/div/div/div/a[contains(.,'Edit')]", driver); if
		 * (VerifyObject(driver, "//strong[text()='" + CloudData.get_data("State") +
		 * "']/../div/div/input")) { WebElement OFFAgain = driver.findElement(
		 * By.xpath("//strong[text()='" + CloudData.get_data("State") +
		 * "']/../div/div/input")); ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", OFFAgain); } else { WebElement
		 * OFFAgain = driver.findElement( By.xpath("//strong[text()='" +
		 * CloudData.get_data("State") + "']/../../div/div/input"));
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 * OFFAgain); } waitforElement(90, SR.RMVEJUR, driver); click(SR.RMVEJUR); } //
		 * Enable jurisdiction After Save Configuration if
		 * (CloudData.get_data("Enable_Juridiction").equalsIgnoreCase("Yes")) {
		 * waitforElement(90, "//strong[text()='" + CloudData.get_data("State") +
		 * "']/../../td/ng-form/div/div/input[@name='vat']", driver);
		 * Enablestate[rowval] = CloudData.get_data("State"); if (VerifyObject(driver,
		 * "//strong[text()='" + CloudData.get_data("State") + "']/../div/div/input")) {
		 * WebElement OFFagain = driver.findElement( By.xpath("//strong[text()='" +
		 * CloudData.get_data("State") + "']/../div/div/input")); ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", OFFagain); } else { WebElement
		 * OFFagain = driver.findElement( By.xpath("//strong[text()='" +
		 * CloudData.get_data("State") + "']/../../div/div/input"));
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 * OFFagain); } waitforElement(90, SR.RMVEJUR, driver); click(SR.POPUPCANCEL);
		 * waitforElement(90, "//strong[text()='" + CloudData.get_data("State") +
		 * "']/../../td/ng-form/div/div/input[@name='vat']", driver);
		 * Thread.sleep(2000); waitforElement(60, SR.VATNUMBER, driver);
		 * type(SR.VATNUMBER, "1234"); WebElement Save =
		 * driver.findElement(By.xpath("//strong[text()='" + CloudData.get_data("State")
		 * + "']/../../td/div/button[contains(.,'Save')]")); ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", Save); rowval++; } // Turning
		 * Off the Country Before Save if
		 * (CloudData.get_data("Juroff_Beforesave").equalsIgnoreCase("Yes")) { if
		 * (VerifyObject(driver, "//strong[text()='" + CloudData.get_data("State") +
		 * "']/../div/div/input")) { String intjur = CloudData.get_data("State");
		 * driver.findElement(By.xpath("//strong[text()='" + intjur +
		 * "']/../div/div/label")).click(); // WebElement OFF = //
		 * driver.findElement(By.xpath("//strong[text()='"+CloudData.get_data("State")+
		 * "']/../div/div/input")); // ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", OFF); } else { String intjur =
		 * CloudData.get_data("State"); driver.findElement(By.xpath("//strong[text()='"
		 * + intjur + "']/../div/div/label")).click(); // WebElement OFF = //
		 * driver.findElement(By.xpath("//strong[text()='"+CloudData.get_data("State")+
		 * "']/../../div/div/input")); // ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", OFF); } waitforElement(30,
		 * SR.RMVEJUR, driver); click(SR.POPUPCANCEL); waitforElement(90,
		 * "//strong[text()='" + CloudData.get_data("State") +
		 * "']/../../td/ng-form/div/div/input[@name='vat']", driver); if
		 * (VerifyObject(driver, "//strong[text()='" + CloudData.get_data("State") +
		 * "']/../div/div/input")) { WebElement OFFAgain = driver.findElement(
		 * By.xpath("//strong[text()='" + CloudData.get_data("State") +
		 * "']/../div/div/input")); ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", OFFAgain); } else { WebElement
		 * OFFAgain = driver.findElement( By.xpath("//strong[text()='" +
		 * CloudData.get_data("State") + "']/../../div/div/input"));
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 * OFFAgain); } waitforElement(90, SR.RMVEJUR, driver); click(SR.RMVEJUR); }
		 */
		clear(SR.STQUICKSRCH);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, CNP.COMPANYHEADER, driver);

	}

	public void RemoveReturnForm(String CompanyName, String state, String Returnname, String EndDate) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + CompanyName
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}

		if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
				+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")) {
			WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
					+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		} else {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditCompany = driver.findElement(
						By.xpath("//h2[contains(.,'" + CompanyName + "')]/../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCompany);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
					+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
						+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
						+ "')]/../../../../..//div/div/div/div/h3/span/a[contains(text(),'Edit')]")).click();
			}
			if (VerifyObject(driver,
					"//h3/span[text()='" + CompanyName + "']/../../div/div/a[text()='Continue Configuration']")) {
				driver.findElement(By.xpath(
						"//h3/span[text()='" + CompanyName + "']/../../div/div/a[text()='Continue Configuration']"))
						.click();
			}
		}
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (state.equalsIgnoreCase("CANADA")) {
			JavaScriptclick(driver, SR.canadaLabel);
			Thread.sleep(5000);
		}
		if (VerifyObject(driver,
				"//td/div/strong[text()='" + state + "']/../../../td/div/a[contains(text(),'Return(s)')]")) {
			WebElement Regs = driver.findElement(By
					.xpath("//td/div/strong[text()='" + state + "']/../../../td/div/a[contains(text(),'Return(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Regs);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(3000);
		}
		if (VerifyObject(driver, "//strong[text()='" + state + "']/../../th/div/a[contains(text(),'Return(s)')]")) {
			WebElement CanRegs = driver.findElement(
					By.xpath("//strong[text()='" + state + "']/../../th/div/a[contains(text(),'Return(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", CanRegs);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(3000);
		}
		/*
		 * WebElement Editstate = driver.findElement(
		 * By.xpath("//td/div/strong[text()='" + state +
		 * "']/../../../td/div/a[contains(text(),'Return(s)')]")); ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", Editstate);
		 * Thread.sleep(5000);
		 */
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);

		if (state != "") {

			JurLink1 = Returnname;
			Thread.sleep(2000);
			if (JurLink1 != "") {
				if (JurLink1.contains("'")) {
					String[] xpathStr = JurLink1.split("'");
					WebElement jur = driver.findElement(By.xpath("//li/a/span[text()=concat('" + xpathStr[0] + "'" + ","
							+ "\"'\"" + "," + "'" + xpathStr[1] + "')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
				} else {
					WebElement jur = driver.findElement(By.xpath("//li/a/span[text()='" + JurLink1 + "']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
				}
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(90, CNP.REMOVE, driver);
			Thread.sleep(2000);
			// assertTrue(CNP.RemoveBlockMessage.isDisplayed());
			JavaScriptclick(driver, CNP.REMOVE);
			Thread.sleep(2000);
			waitforElement(90, CNP.REMOVEITEMBUTTON, driver);
			assertTrue(CNP.Cancel.isDisplayed());
			assertTrue(CNP.removeItemPopupmsg.isDisplayed());
			assertTrue(CNP.removeItemNotePopupmsg.isDisplayed());
			assertTrue(CNP.ENDDATE.isDisplayed());
			clear(CNP.CERTIFCATEENDDATE);
			Thread.sleep(1000);
			type(CNP.CERTIFCATEENDDATE, EndDate);
			endDate = CNP.CERTIFCATEENDDATE.getAttribute("value");
			System.out.println("Return end date is:" + endDate);
			JavaScriptclick(driver, CNP.REMOVEITEMBUTTON);
			Thread.sleep(5000);
			if (JurLink1.contains("'")) {
				String[] xpathStr = JurLink1.split("'");
				assertTrue(driver.findElement(By.xpath("//li/a/span[text()=concat('" + xpathStr[0] + "'" + "," + "\"'\""
						+ "," + "'" + xpathStr[1] + "')]/../i[@class='icon icon-ok icon-gray']")).isDisplayed());
			}
			JavaScriptclick(driver, CNP.RETURNTOCOMPOVERVIEWHEAD);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		}
	}

	public void activatereturnform(String CompanyName, String state, String Returnname) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + CompanyName
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}

		if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
				+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")) {
			WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
					+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		} else {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditCompany = driver.findElement(
						By.xpath("//h2[contains(.,'" + CompanyName + "')]/../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCompany);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
					+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
						+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
						+ "')]/../../../../..//div/div/div/div/h3/span/a[contains(text(),'Edit')]")).click();
			}
			if (VerifyObject(driver,
					"//h3/span[text()='" + CompanyName + "']/../../div/div/a[text()='Continue Configuration']")) {
				driver.findElement(By.xpath(
						"//h3/span[text()='" + CompanyName + "']/../../div/div/a[text()='Continue Configuration']"))
						.click();
			}
		}
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (state.equalsIgnoreCase("CANADA")) {
			JavaScriptclick(driver, SR.canadaLabel);
			Thread.sleep(5000);
		}
		if (VerifyObject(driver,
				"//td/div/strong[text()='" + state + "']/../../../td/div/a[contains(text(),'Return(s)')]")) {
			WebElement Regs = driver.findElement(By
					.xpath("//td/div/strong[text()='" + state + "']/../../../td/div/a[contains(text(),'Return(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Regs);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(3000);
		}
		if (VerifyObject(driver, "//strong[text()='" + state + "']/../../th/div/a[contains(text(),'Return(s)')]")) {
			WebElement CanRegs = driver.findElement(
					By.xpath("//strong[text()='" + state + "']/../../th/div/a[contains(text(),'Return(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", CanRegs);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(3000);
		}
		/*
		 * WebElement Editstate = driver.findElement(
		 * By.xpath("//td/div/strong[text()='" + state +
		 * "']/../../../td/div/a[contains(text(),'Return(s)')]")); ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", Editstate);
		 * Thread.sleep(5000);
		 */
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);

		if (state != "") {

			JurLink1 = Returnname;
			Thread.sleep(2000);
			if (JurLink1 != "") {
				if (JurLink1.contains("'")) {
					String[] xpathStr = JurLink1.split("'");
					WebElement jur = driver.findElement(By.xpath("//li/a/span[text()=concat('" + xpathStr[0] + "'" + ","
							+ "\"'\"" + "," + "'" + xpathStr[1] + "')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
				} else {
					WebElement jur = driver.findElement(By.xpath("//li/a/span[text()='" + JurLink1 + "']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
				}
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(90, CNP.Activate, driver);
			Thread.sleep(2000);
			JavaScriptclick(driver, CNP.Activate);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		}
	}

	public void VerifyUserCancelEndDateChange(String CompanyName, String state, String Returnname, String EndDate)
			throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + CompanyName
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}

		if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
				+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")) {
			WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
					+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		} else {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditCompany = driver.findElement(
						By.xpath("//h2[contains(.,'" + CompanyName + "')]/../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCompany);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
					+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
						+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
						+ "')]/../../../../..//div/div/div/div/h3/span/a[contains(text(),'Edit')]")).click();
			}
			if (VerifyObject(driver,
					"//h3/span[text()='" + CompanyName + "']/../../div/div/a[text()='Continue Configuration']")) {
				driver.findElement(By.xpath(
						"//h3/span[text()='" + CompanyName + "']/../../div/div/a[text()='Continue Configuration']"))
						.click();
			}
		}
		Thread.sleep(5000);
		WebElement Editstate = driver.findElement(
				By.xpath("//td/div/strong[text()='" + state + "']/../../../td/div/a[contains(text(),'Return(s)')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Editstate);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		if (state != "") {
			Thread.sleep(5000);

			Thread.sleep(5000);
			JurLink1 = Returnname;
			Thread.sleep(2000);
			if (JurLink1 != "") {
				if (JurLink1.contains("'")) {
					String[] xpathStr = JurLink1.split("'");
					WebElement jur = driver.findElement(By.xpath("//li/a/span[text()=concat('" + xpathStr[0] + "'" + ","
							+ "\"'\"" + "," + "'" + xpathStr[1] + "')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
				} else {
					WebElement jur = driver.findElement(By.xpath("//li/a/span[text()='" + JurLink1 + "']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
				}
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(90, CNP.REMOVE, driver);
			Thread.sleep(2000);
			assertTrue(CNP.RemoveBlockMessage.isDisplayed());
			JavaScriptclick(driver, CNP.REMOVE);
			Thread.sleep(2000);
			waitforElement(90, CNP.REMOVEITEMBUTTON, driver);
			assertTrue(CNP.Cancel.isDisplayed());
			assertTrue(CNP.removeItemPopupmsg.isDisplayed());
			click(CNP.Cancel);
			Thread.sleep(3000);
			JavaScriptclick(driver, CNP.RETURNTOCOMPOVERVIEWHEAD);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		}
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(driver
				.findElement(By.xpath("//h2[contains(.,'" + CompanyName
						+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]"))
				.isDisplayed());
	}

	public void VerifyCOPhysicalPresenceQuestion() throws Exception {
		ManageConfiguration MC = PageFactory.initElements(driver, ManageConfiguration.class);

		Thread.sleep(8000);
		click(MC.MANAGECONFIGLINK);
		Thread.sleep(2000);
		click(MC.LOCATIONRULES);
		driver.findElement(By.cssSelector("#s2id_autogen1 > a")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#select2-drop > ul > li:nth-child(6) > div")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#field-table > tbody > tr:nth-child(2) > td:nth-child(3) > select")).click();
		assertTrue(driver
				.findElement(By.cssSelector(
						"#field-table > tbody > tr:nth-child(2) > td:nth-child(3) > select > option:nth-child(1)"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By.cssSelector(
						"#field-table > tbody > tr:nth-child(2) > td:nth-child(3) > select > option:nth-child(2)"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By.cssSelector(
						"#field-table > tbody > tr:nth-child(2) > td:nth-child(3) > select > option:nth-child(3)"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By.cssSelector(
						"#field-table > tbody > tr:nth-child(2) > td:nth-child(3) > select > option:nth-child(4)"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By.cssSelector(
						"#field-table > tbody > tr:nth-child(2) > td:nth-child(3) > select > option:nth-child(5)"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By.cssSelector(
						"#field-table > tbody > tr:nth-child(2) > td:nth-child(3) > select > option:nth-child(6)"))
				.isDisplayed());
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#field-table > tbody > tr:nth-child(2) > td:nth-child(5) > select")).click();
		assertTrue(driver
				.findElement(By.cssSelector(
						"#field-table > tbody > tr:nth-child(2) > td:nth-child(5) > select > option:nth-child(1)"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By.cssSelector(
						"#field-table > tbody > tr:nth-child(2) > td:nth-child(5) > select > option:nth-child(2)"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By.cssSelector(
						"#field-table > tbody > tr:nth-child(2) > td:nth-child(5) > select > option:nth-child(3)"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By.cssSelector(
						"#field-table > tbody > tr:nth-child(2) > td:nth-child(5) > select > option:nth-child(4)"))
				.isDisplayed());
		Thread.sleep(2000);
		assertTrue(driver
				.findElement(By
						.cssSelector("#field-table > tbody > tr:nth-child(2) > td:nth-child(7) > button:nth-child(1)"))
				.isDisplayed());
		assertTrue(driver
				.findElement(By
						.cssSelector("#field-table > tbody > tr:nth-child(2) > td:nth-child(7) > button:nth-child(2)"))
				.isDisplayed());
		Thread.sleep(2000);

	}

	public void UploadMOSiteCodes(String CompanyName, String Country, String State, String File, String Succ_File,
			String Err_File) throws Exception {
		String browser = Browser;
		String AutoITpath = xAutoIT();
		int count = 0;
		String AdjustmentFilePath = xUploadAdjustmentFile("SiteCodes");
		String Filename, path;
		File tempfile = null, tempfile2 = null;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		Thread.sleep(6000);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.MOSITECODES);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGURESITECODDESTEXT, driver);
		waitforElement(180, CNP.SITECODESSEARCH, driver);
		waitforElement(180, CNP.ViewUploadHistorysitecodes, driver);
		JavaScriptclick(driver, CNP.UploadMultiplesitecodes);
		waitforElement(180, CNP.uploadmultipleSitecodestext, driver);
		waitforElement(180, CNP.HelpCentersitecodes, driver);
		click(CNP.Fileformat);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "SiteCodes";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(5000);
			tempfile = new File(LatestDownloadedFile("Sitecodes", true));

		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			tempfile = new File(LatestDownloadedFile("Sitecodes", false));
		}
		waitforElement(180, CNP.Browse, driver);
		waitforElement(180, CNP.Uploadfilebtn, driver);
		waitforElement(180, CNP.Cancelbtn, driver);
		Thread.sleep(1000);
		if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
			click(CNP.BROWSE);
		} else {
			JavaScriptclick(driver, CNP.BROWSE);
		}
		Thread.sleep(3000);
		Filename = File;
		fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, Filename, ".csv");
		Thread.sleep(4000);
		click(CNP.Uploadfilebtn);
		Thread.sleep(1000);
		if (VerifyObject(driver, CNP.UploadnoBranchID)) {
			assertTrue(CNP.UploadnoBranchID.isDisplayed());
			click(CNP.Cancelbtn);
		} else {
			waitforElement(180, CNP.manageMOheader, driver);
			assertTrue(CNP.UploadMultiplelink.isDisplayed());
			assertTrue(CNP.ViewMOSitecodeslink.isDisplayed());
			assertTrue(CNP.DateAddedColumn.isDisplayed());
			assertTrue(CNP.SitecodesFilename.isDisplayed());
			assertTrue(CNP.Orgfile.isDisplayed());
			assertTrue(CNP.Successfile.isDisplayed());
			assertTrue(CNP.Errorfile.isDisplayed());
			assertTrue(CNP.pagination.isDisplayed());
			assertTrue(CNP.paginationoptions.isDisplayed());
			Thread.sleep(4000);
			waitforElement(180, CNP.UploadMultiplelink, driver);
			waitforElement(180, "//tbody/tr[1]/td[text()='" + File + "']/../td/a[text()='Original File']", driver);
			String SuccessFile = "//table/tbody/tr/td[contains(.,'" + File
					+ "')]/../td/a[not(@class='ng-hide') and contains(.,'Success File')]";
			String ErrorFile = "//table/tbody/tr/td[contains(.,'" + File + "')]/../td/a[contains(.,'Error File')]";
			if (Succ_File != "") {
				FIRST: if (!VerifyObject(driver, SuccessFile)) {
					boolean report = false;
					count = 0;
					do {
						Thread.sleep(30000);
						if (VerifyObject(driver, ErrorFile)) {
							break FIRST;
						}
						count = count + 1;
						if (count == 8) {
							break;
						}
						if (VerifyObject(driver, SuccessFile)) {
							report = true;
						}
					} while (report == false);
				}
				WebElement Sucfile = driver
						.findElement(By.xpath("//td[contains(.,'" + File + "')]/../td/a[text()='Success File']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Sucfile);
				Thread.sleep(20000);

				if (Browser.equalsIgnoreCase("Chrome")) {
					File directory = new File(".//");
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "SiteCodes";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(5000);
					tempfile = new File(LatestDownloadedFile("Sitecodes", true));

				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					tempfile = new File(LatestDownloadedFile("Sitecodes", false));
				}
				File directory = new File(".//");
				String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "SiteCodes";
				String name1 = Succ_File;
				tempfile2 = new File(DirName1 + "\\" + name1 + "_success.csv");
				Thread.sleep(5000);
				Comparecsvfiles(tempfile, tempfile2);

			}
			if (Err_File != "") {
				FIRST: if (!VerifyObject(driver, ErrorFile)) {
					boolean report = false;
					count = 0;
					do {
						Thread.sleep(30000);
						if (VerifyObject(driver, SuccessFile)) {
							break FIRST;
						}
						count = count + 1;
						if (count == 8) {
							break;
						}
						if (VerifyObject(driver, ErrorFile)) {
							report = true;
						}
					} while (report == false);
				}
				WebElement Errfile = driver
						.findElement(By.xpath("//td[contains(.,'" + File + "')]/../td/a[text()='Error File']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Errfile);
				Thread.sleep(20000);

				if (Browser.equalsIgnoreCase("Chrome")) {
					File directory = new File(".//");
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Sitecodes";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(5000);
					tempfile = new File(LatestDownloadedFile("Sitecodes", true));

				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					tempfile = new File(LatestDownloadedFile("Sitecodes", false));
				}
				File directory = new File(".//");
				String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "SiteCodes";
				String name1 = Err_File;
				tempfile2 = new File(DirName1 + "\\" + name1 + "_error.csv");
				Thread.sleep(5000);
				Comparecsvfiles(tempfile, tempfile2);
			}
			Thread.sleep(5000);
			waitforElement(180, CNP.ViewMOSitecodeslink, driver);
			JavaScriptclick(driver, CNP.ViewMOSitecodeslink);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(5000);
			// verifying the View upload History
			JavaScriptclick(driver, CNP.ViewUploadHistorysitecodes);
			waitforElement(180, CNP.manageMOheader, driver);
			assertTrue(CNP.UploadMultiplelink.isDisplayed());
			assertTrue(CNP.ViewMOSitecodeslink.isDisplayed());
			// Verifying the View Sitecodes links
			JavaScriptclick(driver, CNP.ViewMOSitecodeslink);
			Thread.sleep(2000);
			waitforElement(180, CNP.ViewUploadHistorysitecodes, driver);
		}
	}

	public void UploadCoBranchIDS(String CompanyName, String Country, String State, String File, String Succ_File,
			String Err_File) throws Exception {
		String browser = Browser;
		String AutoITpath = xAutoIT();
		int count = 0;
		String AdjustmentFilePath = xUploadAdjustmentFile("BranchID");
		String path;
		File tempfile = null, tempfile2 = null;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		// resultwriteScenario("Add Colorado BranchNumbers", "Verifying Add Colorado
		// BranchNumbers Functionality");
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		assertTrue(CNP.COBRANCHIDS.isDisplayed());
		JavaScriptclick(driver, CNP.COBRANCHNUMBERS);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.SITECODESSEARCH, driver);
		assertTrue(CNP.ViewUploadHistorysitecodes.isDisplayed());
		JavaScriptclick(driver, CNP.UploadMultiplesitecodes);
		waitforElement(180, CNP.uploadmultiplebranchIDtext, driver);
		assertTrue(CNP.HelpCentersitecodes.isDisplayed());
		assertTrue(CNP.Fileformat.isDisplayed());
		Thread.sleep(3000);
		click(CNP.Fileformat);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Products";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(5000);
			tempfile = new File(LatestDownloadedFile("BranchID", true));

		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			tempfile = new File(LatestDownloadedFile("BranchID", false));
		}
		assertTrue(CNP.Browse.isDisplayed());
		assertTrue(CNP.Uploadfilebtn.isDisplayed());
		assertTrue(CNP.Cancelbtn.isDisplayed());
		Thread.sleep(1000);
		if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
			click(CNP.BROWSE);
		} else {
			JavaScriptclick(driver, CNP.BROWSE);
		}
		Thread.sleep(3000);
		fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, File, ".csv");
		Thread.sleep(4000);
		click(CNP.Uploadfilebtn);
		if (VerifyObject(driver, CNP.UploadnoBranchID)) {
			assertTrue(CNP.UploadnoBranchID.isDisplayed());
			click(CNP.Cancelbtn);
		} else {
			waitforElement(180, CNP.managecoheader, driver);
			assertTrue(CNP.UploadMultiplelink.isDisplayed());
			assertTrue(CNP.ViewCOBranchIDslink.isDisplayed());
			assertTrue(CNP.DateAddedColumn.isDisplayed());
			assertTrue(CNP.BranchIDsFilename.isDisplayed());
			assertTrue(CNP.Orgfile.isDisplayed());
			assertTrue(CNP.Successfile.isDisplayed());
			assertTrue(CNP.Errorfile.isDisplayed());
			// assertTrue(CNP.uploadinprogess.isDisplayed()); "Upload in progress..." is not
			// displayed
			assertTrue(CNP.pagination.isDisplayed());
			assertTrue(CNP.paginationoptions.isDisplayed());
			Thread.sleep(4000);
			waitforElement(180, CNP.UploadMultiplelink, driver);
			waitforElement(180, "//tbody/tr[1]/td[text()='" + File + "']/../td/a[text()='Original File']", driver);
			String SuccessFile = "//table/tbody/tr/td[contains(.,'" + File
					+ "')]/../td/a[not(@class='ng-hide') and contains(.,'Success File')]";
			String ErrorFile = "//table/tbody/tr/td[contains(.,'" + File + "')]/../td/a[contains(.,'Error File')]";
			if (Succ_File != "") {
				FIRST: if (!VerifyObject(driver, SuccessFile)) {
					boolean report = false;
					count = 0;
					do {
						Thread.sleep(30000);
						if (VerifyObject(driver, ErrorFile)) {
							break FIRST;
						}
						count = count + 1;
						if (count == 8) {
							break;
						}
						if (VerifyObject(driver, SuccessFile)) {
							report = true;
						}
					} while (report == false);
				}
				WebElement Sucfile = driver
						.findElement(By.xpath("//td[contains(.,'" + File + "')]/../td/a[text()='Success File']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Sucfile);
				Thread.sleep(20000);

				if (Browser.equalsIgnoreCase("Chrome")) {
					File directory = new File(".//");
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "BranchID";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(5000);
					tempfile = new File(LatestDownloadedFile("BranchID", true));

				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					tempfile = new File(LatestDownloadedFile("BranchID", false));
				}
				File directory = new File(".//");
				String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "BranchID";
				String name1 = Succ_File;
				tempfile2 = new File(DirName1 + "\\" + name1 + "_success.csv");
				Thread.sleep(5000);
				Comparecsvfiles(tempfile, tempfile2);
			}
			if (Err_File != "") {
				FIRST: if (!VerifyObject(driver, ErrorFile)) {
					boolean report = false;
					count = 0;
					do {
						Thread.sleep(30000);
						if (VerifyObject(driver, SuccessFile)) {
							break FIRST;
						}
						count = count + 1;
						if (count == 8) {
							break;
						}
						if (VerifyObject(driver, ErrorFile)) {
							report = true;
						}
					} while (report == false);
				}
				WebElement Errfile = driver
						.findElement(By.xpath("//td[contains(.,'" + File + "')]/../td/a[text()='Error File']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Errfile);
				Thread.sleep(20000);

				if (Browser.equalsIgnoreCase("Chrome")) {
					File directory = new File(".//");
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "BranchID";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(5000);
					tempfile = new File(LatestDownloadedFile("BranchID", true));

				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					tempfile = new File(LatestDownloadedFile("BranchID", false));
				}
				File directory = new File(".//");
				String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "BranchID";
				String name1 = Err_File;
				tempfile2 = new File(DirName1 + "\\" + name1 + "_error.csv");
				Thread.sleep(5000);
				Comparecsvfiles(tempfile, tempfile2);

			}
			JavaScriptclick(driver, CNP.ViewCOBranchIDslink);
			Thread.sleep(1000);
			// verifying the View upload History
			JavaScriptclick(driver, CNP.ViewUploadHistorysitecodes);
			waitforElement(180, CNP.managecoheader, driver);
			assertTrue(CNP.UploadMultiplelink.isDisplayed());
			assertTrue(CNP.ViewCOBranchIDslink.isDisplayed());
			// Verifying the View Sitecodes links
			JavaScriptclick(driver, CNP.ViewCOBranchIDslink);
			Thread.sleep(2000);
			waitforElement(180, CNP.ViewUploadHistorysitecodes, driver);
		}
	}

	public void EditCOBranchNumbers(String CompanyName, String Country, String State, String Jurisdiction,
			String Jurisdictioncode, String NewBranchNumber, String BranchNumber, String UtilUse207) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		// resultwriteScenario("Edit Colorado BranchNumbers", "Verifying Edit Colorado
		// BranchNumbers Functionality");
		Thread.sleep(8000);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.COBRANCHNUMBERS);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(CNP.CONFIGUREBRANCHNOSTEXT.isDisplayed());
		waitforElement(180, CNP.SITECODESSEARCH, driver);
		click(CNP.SITECODESSEARCH);
		clear(CNP.SITECODESSEARCH);
		Thread.sleep(3000);
		type(CNP.SITECODESSEARCH, BranchNumber);
		Thread.sleep(3000);
		clear(CNP.SITECODESSEARCH);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.JURISDICTIONCODERADIO);
		Thread.sleep(5000);
		click(CNP.SITECODESSEARCH);
		type(CNP.SITECODESSEARCH, Jurisdictioncode);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'" + Jurisdictioncode
				+ "')]/../td/div/a[contains(.,'Add')]")) {
			WebElement d = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
					+ Jurisdictioncode + "')]/../td/div/a[contains(.,'Add')]"));
			d.click();
		}
		if (VerifyObject(driver, "//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'" + Jurisdictioncode
				+ "')]/../td/div/a[contains(.,'Edit')]")) {
			WebElement d1 = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
					+ Jurisdictioncode + "')]/../td/div/a[contains(.,'Edit')]"));
			d1.click();
		}
		Thread.sleep(6000);
		clear(CNP.BRANCHIDVALUE);
		Thread.sleep(2000);
		type(CNP.BRANCHIDVALUE, NewBranchNumber);
		if (!CNP.Utilities207Checkbox.isSelected() && UtilUse207.equalsIgnoreCase("Yes")) {
			click(CNP.Utilities207Checkbox);
		} else if (CNP.Utilities207Checkbox.isSelected() && UtilUse207.equalsIgnoreCase("No")) {
			click(CNP.Utilities207Checkbox);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.BRANCHIDSAVE);
		Thread.sleep(2000);
	}

	public void SiteCodes(String CompanyName, String Jurisdiction1, String State, String Reg_Number) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		Companynamesplit = CompanyName;
		CompnameSplit(driver, Companynamesplit, "statereg");
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, "//table[@class='table table-bordered state-registration ng-scope']/tbody/tr/td", driver);
		if (VerifyObject(driver, "//h2[contains(.,'" + Companynamesplit
				+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")) {
			driver.findElement(By.xpath("//h2[contains(.,'" + Companynamesplit
					+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")).click();
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='Missouri']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.MOSITECODES);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(CNP.CONFIGURESITECODDESTEXT.isDisplayed());
		waitforElement(180, CNP.SITECODESSEARCH, driver);
		if (VerifyObject(driver, "//bootstrap-pagination/div/ul")) {
			Thread.sleep(5000);
			click(CNP.PAGESIZEDROPDOWN);
			Thread.sleep(5000);
			selectByValue(CNP.PAGESIZEDROPDOWN, "100 per page");
			// tableRowCount(driver,Data_Row);
			int sitecodecount = 0;
			sitecodecount = sitecodecount + rowcount2;
			Thread.sleep(5000);
		} else {
			List<WebElement> rowCollection = driver.findElements(
					By.xpath("//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr"));
			System.out.println("count of exceptions are = " + rowCollection.size());
			rowCollection.size();
		}
		click(CNP.SITECODESSEARCH);
		clear(CNP.SITECODESSEARCH);
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.JURISDICTIONCODERADIO);
		Thread.sleep(5000);
		type(CNP.SITECODESSEARCH, Jurisdiction1);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//tr/td[contains(.,'" + State + "')]/../td[contains(.,'" + Jurisdiction1
				+ "')]/../td/div/a[contains(.,'Add')]")).click();
		Thread.sleep(6000);
		type(CNP.ADDSITECODEVALUE, Reg_Number);
		Thread.sleep(2000);
		click(CNP.BRANCHIDCANCEL);
		Thread.sleep(2000);
		click(CNP.SITECODESSEARCH);
		clear(CNP.SITECODESSEARCH);
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.JURISDICTIONRADIO);
		Thread.sleep(5000);
		type(CNP.SITECODESSEARCH, State);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//tr/td[contains(.,'" + State + "')]/../td[contains(.,'" + Jurisdiction1
				+ "')]/../td/div/a[contains(.,'Add')]")).click();
		if (VerifyObject(driver, "//tr/td[contains(.,'" + State + "')]/../td[contains(.,'" + Jurisdiction1
				+ "')]/../td/div/a[contains(.,'Add')]")) {
			WebElement d = driver.findElement(By.xpath("//tr/td[contains(.,'" + State + "')]/../td[contains(.,'"
					+ Jurisdiction1 + "')]/../td/div/a[contains(.,'Add')]"));
			d.click();
		}
		if (VerifyObject(driver, "//tr/td[contains(.,'" + State + "')]/../td[contains(.,'" + Jurisdiction1
				+ "')]/../td/div/a[contains(.,'Edit')]")) {
			WebElement d1 = driver.findElement(By.xpath("//tr/td[contains(.,'" + State + "')]/../td[contains(.,'"
					+ Jurisdiction1 + "')]/../td/div/a[contains(.,'Edit')]"));
			d1.click();
		}
		Thread.sleep(6000);
		type(CNP.ADDSITECODEVALUE, Reg_Number);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.BRANCHIDSAVE);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.WARNINGPOPUP)) {
			JavaScriptclick(driver, CNP.NOACCEPT);
			Thread.sleep(5000);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		assertTrue(CNP.READYTOSAVE.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[contains(.,'" + State + "')]/../td[contains(.,'" + Jurisdiction1
				+ "')]/../td[contains(.,'" + Reg_Number + "')]")).isDisplayed());

	}

	public void AddMOSiteCodes(String CompanyName, String Country, String State, String Jurisdiction, String SiteCode,
			String Jurisdictioncode) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);

		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		Thread.sleep(6000);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.MOSITECODES);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGURESITECODDESTEXT, driver);
		waitforElement(180, CNP.SITECODESSEARCH, driver);

		click(CNP.SITECODESSEARCH);
		clear(CNP.SITECODESSEARCH);
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.JURISDICTIONRADIO);
		Thread.sleep(5000);
		type(CNP.SITECODESSEARCH, Jurisdiction);
		Thread.sleep(2000);
		if (VerifyObject(driver, "//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'" + Jurisdictioncode
				+ "')]/../td/div/a[contains(.,'Add')]")) {
			WebElement d = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
					+ Jurisdictioncode + "')]/../td/div/a[contains(.,'Add')]"));
			d.click();
		}
		if (VerifyObject(driver, "//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'" + Jurisdictioncode
				+ "')]/../td/div/a[contains(.,'Edit')]")) {
			WebElement d1 = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
					+ Jurisdictioncode + "')]/../td/div/a[contains(.,'Edit')]"));
			d1.click();
		}
		Thread.sleep(6000);
		type(CNP.ADDSITECODEVALUE, SiteCode);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.BRANCHIDSAVE);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.WARNINGPOPUP)) {
			JavaScriptclick(driver, CNP.NOACCEPT);
			Thread.sleep(5000);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		assertTrue(CNP.READYTOSAVE.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
				+ Jurisdictioncode + "')]/../td[contains(.,'" + SiteCode + "')]")).isDisplayed());
	}

	public void addLocseqnumber(String CompanyName, String State, String paginationdropdownvalue, String locSeqnumber)
			throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		Thread.sleep(6000);
		JavaScriptclick(driver, CNP.LOCATIONSEQNUMBER);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGURELOCATIONSEQNUMBERTEXT, driver);
		waitforElement(180, CNP.JURISDICTIONSEARCH, driver);

		selectByValue(CNP.PAGINATIONDROPDOWN, paginationdropdownvalue);

		Thread.sleep(4000);

		assertTrue(CNP.PAGINATIONVALUE.isDisplayed());

		List<WebElement> elements = driver
				.findElements(By.xpath("//div[@class='pagination pagination-right ng-scope']/ul/li/a"));

		System.out.println("Paginazation size is:" + elements.size());

		// for (int i = 2; i < elements.size() - 1; i++) {

		// driver.findElement(By.xpath("//div[@class='pagination pagination-right
		// ng-scope']/ul/li[" + i + "]/a")).click();

		List<WebElement> rows = driver.findElements(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr"));

		System.out.println(rows.size());

		for (int j = 1; j <= 15; j++) {
			driver.findElement(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[" + j + "]/td/div/a")).click();
			waitforElement(100, "//p[contains(.,'Add Location Seq Number')]", driver);
			assertTrue(CNP.JURISDICTIONNAMEDISABLED.isDisplayed());
			if (j == 1) {
				CNP.Cancelbtn.click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[" + j + "]/td/div/a")).click();
				waitforElement(100, "//p[contains(.,'Add Location Seq Number')]", driver);
			}
			type(CNP.LocationSeqNumberTextBox, "abc");
			type(CNP.LocationSeqNumberTextBox, "1xxx");
			clear(CNP.LocationSeqNumberTextBox);
			type(CNP.LocationSeqNumberTextBox, locSeqnumber);
			CNP.SAVEBTN.click();
			Thread.sleep(2000);
		}
		// }
		SR.returnToCompanyOverView.click();
		waitforElement(120, CNP.LOCATIONSEQNUMBER, driver);
	}

	public void editLocseqnumber(String CompanyName, String locSeqnumber) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		Thread.sleep(6000);
		JavaScriptclick(driver, CNP.LOCATIONSEQNUMBER);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGURELOCATIONSEQNUMBERTEXT, driver);
		waitforElement(180, CNP.JURISDICTIONSEARCH, driver);

		List<WebElement> rows = driver.findElements(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr"));

		System.out.println(rows.size());

		// for (int j = 1; j <= rows.size(); j++) {
		for (int j = 1; j <= 5; j++) {
			driver.findElement(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[" + j + "]/td/div/a")).click();
			waitforElement(100, "//p[contains(.,'Edit Location Seq Number')]", driver);
			assertTrue(CNP.JURISDICTIONNAMEDISABLED.isDisplayed());
			clear(CNP.LocationSeqNumberTextBox);
			type(CNP.LocationSeqNumberTextBox, "abc");
			type(CNP.LocationSeqNumberTextBox, "1xxx");
			clear(CNP.LocationSeqNumberTextBox);
			type(CNP.LocationSeqNumberTextBox, locSeqnumber);
			CNP.SAVEBTN.click();
			Thread.sleep(2000);
		}
		SR.returnToCompanyOverView.click();
		waitforElement(120, CNP.LOCATIONSEQNUMBER, driver);
	}

	public void verifyTILocseqnumber(String CompanyName, String State) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		Thread.sleep(6000);
		assertEquals(CNP.LOCATIONSEQNUMBER.isDisplayed(), false);
	}

	public void searchjurisdictionLocseqnumber(String CompanyName, String jurisdiction) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		Thread.sleep(6000);
		JavaScriptclick(driver, CNP.LOCATIONSEQNUMBER);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGURELOCATIONSEQNUMBERTEXT, driver);
		waitforElement(180, CNP.JURISDICTIONSEARCH, driver);

		type(CNP.JURISDICTIONSEARCH, jurisdiction);
		Thread.sleep(5000);
		WebElement element = driver
				.findElement(By.xpath("//tbody/tr[1]/td[1]/div[contains(.,'" + jurisdiction + "')]"));
		assertTrue(element.isDisplayed());
		clear(CNP.JURISDICTIONSEARCH);
		type(CNP.JURISDICTIONSEARCH, "BLOUNT25544545");
		WebElement element1 = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]"));
		assertEquals(element1.isDisplayed(), false);
		clear(CNP.JURISDICTIONSEARCH);
		type(CNP.JURISDICTIONSEARCH, "125478*%^&#$%548");
		assertEquals(element1.isDisplayed(), false);
		clear(CNP.JURISDICTIONSEARCH);
		type(CNP.JURISDICTIONSEARCH, "   ");
		assertEquals(element1.isDisplayed(), false);

		selectByValue(CNP.PAGINATIONDROPDOWN, "50 per page");
		waitforElement(60, CNP.FIFTYRECORDS, driver);
		Thread.sleep(2000);
		assertTrue(CNP.FIFTYRECORDS.isDisplayed());

		selectByValue(CNP.PAGINATIONDROPDOWN, "100 per page");
		waitforElement(60, CNP.HUNDREDRECORDS, driver);
		Thread.sleep(2000);
		assertTrue(CNP.HUNDREDRECORDS.isDisplayed());

		JavaScriptclick(driver, CNP.JURISDICTION);
		Thread.sleep(5000);
		SR.returnToCompanyOverView.click();
		waitforElement(120, CNP.LOCATIONSEQNUMBER, driver);
	}

	public void uploadLocseqNumber(String CompanyName, String fileName, String fileformat, String invalidcsvfile,
			String verifySuccessfile, String verifyErrorfile) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		String AdjustmentFilePath = xUploadAdjustmentFile("LocationSeqNumber");
		String AutoITpath = xAutoIT();
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		Thread.sleep(6000);
		JavaScriptclick(driver, CNP.LOCATIONSEQNUMBER);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGURELOCATIONSEQNUMBERTEXT, driver);
		waitforElement(180, CNP.JURISDICTIONSEARCH, driver);

		JavaScriptclick(driver, CNP.UPLOADMULTIPLEREPORTSBTN);
		waitforElement(120, CNP.UPLOADMULTIPLELOCATIONSEQNUMBER, driver);
		if (verifySuccessfile.equalsIgnoreCase("Yes")) {
			assertTrue(CNP.UPLOADFILEMESSAGE.isDisplayed());
			assertTrue(CNP.UPLOADHELPCENTERLINK.isDisplayed());
		}
		JavaScriptclick(driver, CNP.FILEFORMATTEMPLATE);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.BROWSEBTN);
		Thread.sleep(10000);
		switch (Browser) {
		case "IE":
			Runtime.getRuntime()
					.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + fileName + fileformat);
			break;
		case "Firefox":

			Runtime.getRuntime()
					.exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + fileName + fileformat);
			break;
		case "Chrome":
			Runtime.getRuntime()
					.exec(AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + fileName + fileformat);
			break;
		case "Safari":
			Runtime.getRuntime()
					.exec(AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + fileName + fileformat);
			break;
		}
		Thread.sleep(4000);
		JavaScriptclick(driver, CNP.UPLOADMULTIPLELOCSEQNUMBER);
		Thread.sleep(5000);

		if (fileformat.equalsIgnoreCase(".xls") || fileformat.equalsIgnoreCase(".xlsx")
				|| fileformat.equalsIgnoreCase(".pdf") || fileformat.equalsIgnoreCase(".rar")
				|| fileformat.equalsIgnoreCase(".txt") || fileformat.equalsIgnoreCase(".zip")) {
			assertTrue(CNP.UNSUPPORTEDUPLOADFILES.isDisplayed());
			click(CNP.Cancelbtn);
			waitforElement(180, SR.returnToCompanyOverView, driver);
		} else {
			int count;
			Robot robot = new Robot();
			File directory = new File(".//");
			File tempfile = null;
			File tempfile2 = null;
			if (invalidcsvfile.equalsIgnoreCase("Yes")) {
				assertTrue(CNP.FILECONTAINNODATAMSG.isDisplayed());
				click(CNP.Cancelbtn);
				waitforElement(180, SR.returnToCompanyOverView, driver);
			} else {
				Thread.sleep(6000);
				assertTrue(CNP.MANAGEILLINOISFILES.isDisplayed());
				String OriginalFile = "//table/tbody/tr/td[contains(.,'" + fileName
						+ "')]/../td/a[contains(.,'Original File')]";
				if (!VerifyObject(driver, OriginalFile)) {
					boolean report = false;
					count = 0;
					do {
						driver.navigate().refresh();
						waitforElement(180, CNP.MANAGEILLINOISFILES, driver);
						count = count + 1;
						if (count == 10) {
							assertTrue(false);
							break;
						}
						if (VerifyObject(driver, OriginalFile)) {
							report = true;
						}
					} while (report == false);
				}
				WebElement OrgFile = driver.findElement(By.xpath(OriginalFile));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OrgFile);
				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("IE")) {
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_ALT);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_S);
				}
				if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
					File directory1 = new File(".//");
					String path = directory1.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "LocationSeqNumber";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					LatestDownloadedFile("LocationSeqNumber", true);
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					LatestDownloadedFile("LocationSeqNumber", false);
				}
				Thread.sleep(5000);
				String SuccessFile = "//table/tbody/tr/td[contains(.,'" + fileName
						+ "')]/../td/a[contains(.,'Success File')]";
				String ErrorFile = "//table/tbody/tr/td[contains(.,'" + fileName
						+ "')]/../td/a[contains(.,'Error File')]";

				if (verifySuccessfile.equalsIgnoreCase("Yes")) {
					WebElement SucsFile = driver.findElement(By.xpath(SuccessFile));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", SucsFile);
					Thread.sleep(10000);
					if (Browser.equalsIgnoreCase("IE")) {
						Thread.sleep(10000);
						robot.keyPress(KeyEvent.VK_ALT);
						Thread.sleep(1000);
						robot.keyPress(KeyEvent.VK_S);
						robot.keyRelease(KeyEvent.VK_ALT);
						robot.keyRelease(KeyEvent.VK_S);
					}
					if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
						String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
								+ "LocationSeqNumber";
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(10000);
						tempfile = new File(LatestDownloadedFile("LocationSeqNumber", true));
						tempfile.toString();
					}
					if (Browser.equalsIgnoreCase("Firefox")) {
						Thread.sleep(10000);
						tempfile = new File(LatestDownloadedFile("LocationSeqNumber", false));
						tempfile.toString();
					}
					String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
							+ "LocationSeqNumber";
					String[] name = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
					System.out.println(name);
					tempfile2 = new File(DirName + "\\" + name[0] + "_suc.csv");
					System.out.println(tempfile2);
					Thread.sleep(5000);
					@SuppressWarnings("unchecked")
					ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
					@SuppressWarnings("unchecked")
					ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
					f1.removeAll(f2);
					assertTrue(f1.size() == 0);
				}
				if (verifyErrorfile.equalsIgnoreCase("Yes")) {
					WebElement ErrFile = driver.findElement(By.xpath(ErrorFile));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", ErrFile);
					Thread.sleep(10000);
					if (Browser.equalsIgnoreCase("IE")) {
						Thread.sleep(5000);
						robot.keyPress(KeyEvent.VK_ALT);
						Thread.sleep(1000);
						robot.keyPress(KeyEvent.VK_S);
						robot.keyRelease(KeyEvent.VK_ALT);
						robot.keyRelease(KeyEvent.VK_S);
					}
					if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
						File directory1 = new File(".//");
						String path = directory1.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
								+ "LocationSeqNumber";
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(10000);
						tempfile = new File(LatestDownloadedFile("LocationSeqNumber", true));
					}
					if (Browser.equalsIgnoreCase("Firefox")) {
						tempfile = new File(LatestDownloadedFile("LocationSeqNumber", false));
					}

					String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
							+ "LocationSeqNumber";
					String[] name = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
					tempfile2 = new File(DirName + "\\" + name[0] + "_err.csv");
					Thread.sleep(10000);
					@SuppressWarnings("unchecked")
					ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
					@SuppressWarnings("unchecked")
					ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
					f1.removeAll(f2);
					assertTrue(f1.size() == 0);
				}
			}
		}
		if (VerifyObject(driver, CNP.ViewIllinoisLocationSeqNumber)) {
			JavaScriptclick(driver, CNP.ViewIllinoisLocationSeqNumber);
			waitforElement(120, CNP.CONFIGURELOCATIONSEQNUMBERTEXT, driver);
		} else {
			SR.returnToCompanyOverView.click();
			waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(2000);
		}
	}

	public void viewuploadLocseqNumberHistory(String CompanyName, String fileName, String fileformat) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		Thread.sleep(6000);
		JavaScriptclick(driver, CNP.LOCATIONSEQNUMBER);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGURELOCATIONSEQNUMBERTEXT, driver);
		waitforElement(180, CNP.JURISDICTIONSEARCH, driver);

		JavaScriptclick(driver, CNP.VIEWUPLOADHISTORY);
		waitforElement(120, CNP.MANAGEILLINOISFILES, driver);
		assertTrue(CNP.MANAGEILLINOISFILES.isDisplayed());
		assertTrue(CNP.DateAdded.isDisplayed());
		assertTrue(CNP.SeqFilename.isDisplayed());
		assertTrue(CNP.OriginalFile.isDisplayed());
		assertTrue(CNP.SuccessReport.isDisplayed());
		assertTrue(CNP.ErrorReport.isDisplayed());
		JavaScriptclick(driver, CNP.ViewIllinoisLocationSeqNumber);
		waitforElement(120, CNP.CONFIGURELOCATIONSEQNUMBERTEXT, driver);
		Thread.sleep(2000);
	}

	public void AddZeroReport(String CompanyName, String State, String paginationdropdownvalue, String taxcategories,
			String sac, String reportOn) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		Thread.sleep(6000);
		JavaScriptclick(driver, CNP.ZEROREPORT);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGUREZEROREPORTTEXT, driver);
		waitforElement(180, CNP.JURISDICTIONSEARCH, driver);

		selectByValue(CNP.PAGINATIONDROPDOWN, paginationdropdownvalue);

		Thread.sleep(4000);

		assertTrue(CNP.PAGINATIONVALUE.isDisplayed());

		List<WebElement> elements = driver
				.findElements(By.xpath("//div[@class='pagination pagination-right ng-scope']/ul/li/a"));

		System.out.println("Paginazation size is:" + elements.size());

		// for (int i = 2; i < elements.size() - 1; i++) {

		// driver.findElement(By.xpath("//div[@class='pagination pagination-right
		// ng-scope']/ul/li[" + i + "]/a")).click();

		List<WebElement> rows = driver.findElements(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr"));

		System.out.println(rows.size());

		selectByValue(CNP.taxTypes, taxcategories);

		for (int j = 1; j <= 12; j++) {
			driver.findElement(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[" + j + "]/td/div/a")).click();
			waitforElement(100, "//p[contains(.,'Add Zero Report')]", driver);
			assertTrue(CNP.JURISDICTIONNAMEDISABLED.isDisplayed());
			if (j == 1) {
				CNP.Cancelbtn.click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[" + j + "]/td/div/a")).click();
				waitforElement(60, "//p[contains(.,'Add Zero Report')]", driver);
			}
			// AddZeroReportTaxCategories();
			int k = j - 1;
			CNP.ZeroReportchkbox.click();
			int a = Integer.parseInt(sac) + k;
			String sacno = String.valueOf(a);

			CNP.SACSettings.sendKeys(sacno);
			selectByValue(CNP.ReportonSettings, reportOn);
			CNP.SAVEBTN.click();
			Thread.sleep(2000);
		}
		// }
		SR.returnToCompanyOverView.click();
		waitforElement(120, CNP.ZEROREPORT, driver);
	}

	public void EditZeroReport(String CompanyName, String taxcategories, String sac, String reportOn) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		Thread.sleep(6000);
		JavaScriptclick(driver, CNP.ZEROREPORT);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGUREZEROREPORTTEXT, driver);
		waitforElement(180, CNP.JURISDICTIONSEARCH, driver);

		List<WebElement> rows = driver.findElements(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr"));

		System.out.println(rows.size());

		// for (int j = 1; j <= rows.size(); j++) {
		for (int j = 1; j <= 6; j++) {
			driver.findElement(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[" + j + "]/td/div/a")).click();
			waitforElement(100, "//p[contains(.,'Edit Zero Report')]", driver);
			assertTrue(CNP.JURISDICTIONNAMEDISABLED.isDisplayed());
			// EditZeroReportTaxCategories();
			if (j == 3 || j == 6) {
			} else {
				CNP.ZeroReportchkbox.click();
			}
			CNP.SACSettings.clear();
			int k = j - 1;
			int a = Integer.parseInt(sac) + k;
			String sacno = String.valueOf(a);
			CNP.SACSettings.sendKeys(sacno);
			if (j == 6) {
				selectByValue(CNP.ReportonSettings, "Non-State");
			} else {
				selectByValue(CNP.ReportonSettings, reportOn);
			}
			CNP.SAVEBTN.click();
			Thread.sleep(2000);
		}
		SR.returnToCompanyOverView.click();
		waitforElement(120, CNP.ZEROREPORT, driver);
	}

	public void AddZeroReportTaxCategories() throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		CNP.ZeroReportchkbox.click();
		CNP.SACSettings.sendKeys("123");
		selectByValue(CNP.ReportonSettings, "State");
		CNP.SALES.click();
		CNP.SELLER.click();
		CNP.CONSUMER.click();
		CNP.RENTALLEASE.click();
		CNP.AUTOCU.click();
		CNP.AUTOSU.click();
		CNP.EQUIPMENTCU.click();
		CNP.EQUIPMENTSU.click();
		CNP.MANUFACTURINGRENTALS.click();
		CNP.AUTORENTALS.click();
		CNP.MANUFACTURINGCU.click();
		CNP.MANUFACTURINGSU.click();

	}

	public void EditZeroReportTaxCategories() throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		CNP.ZEROREPORTAUTO.click();
		CNP.ZEROREPORTFARMEQUIPMENT.click();
		CNP.ZEROREPORTMANUFACTURING.click();
		CNP.MANUFACTURINGRENTALS.click();
		CNP.MANUFACTURINGSU.click();

	}

	public void verifyTIZeroReport(String CompanyName, String State) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		Thread.sleep(6000);
		assertEquals(CNP.ZEROREPORT.isDisplayed(), false);
	}

	public void searchjurisdictionzeroReport(String CompanyName, String State, String jurisdiction) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		Thread.sleep(6000);
		JavaScriptclick(driver, CNP.ZEROREPORT);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGUREZEROREPORTTEXT, driver);
		waitforElement(180, CNP.JURISDICTIONSEARCH, driver);

		type(CNP.JURISDICTIONSEARCH, jurisdiction);
		Thread.sleep(5000);
		WebElement element = driver
				.findElement(By.xpath("//tbody/tr[1]/td[1]/div[contains(.,'" + jurisdiction + "')]"));
		assertTrue(element.isDisplayed());
		clear(CNP.JURISDICTIONSEARCH);
		type(CNP.JURISDICTIONSEARCH, "BLOUNT25544545");
		WebElement element1 = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]"));
		assertEquals(element1.isDisplayed(), false);
		clear(CNP.JURISDICTIONSEARCH);
		type(CNP.JURISDICTIONSEARCH, "125478*%^&#$%548");
		assertEquals(element1.isDisplayed(), false);
		clear(CNP.JURISDICTIONSEARCH);
		type(CNP.JURISDICTIONSEARCH, "   ");
		assertEquals(element1.isDisplayed(), false);

		selectByValue(CNP.PAGINATIONDROPDOWN, "50 per page");
		waitforElement(60, CNP.FIFTYRECORDS, driver);
		Thread.sleep(2000);
		assertTrue(CNP.FIFTYRECORDS.isDisplayed());

		selectByValue(CNP.PAGINATIONDROPDOWN, "100 per page");
		waitforElement(60, CNP.HUNDREDRECORDS, driver);
		Thread.sleep(2000);
		assertTrue(CNP.HUNDREDRECORDS.isDisplayed());

		JavaScriptclick(driver, CNP.JURISDICTION);
		Thread.sleep(5000);
		SR.returnToCompanyOverView.click();
		waitforElement(120, CNP.ZEROREPORT, driver);
	}

	public void AddzeroReportbysearch(String CompanyName, String jurisdiction, String Sac, String Reporton)
			throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		if (!VerifyObject(driver, CNP.JURISDICTIONSEARCH)) {

			waitforElement(180, CNP.CONFIGURE, driver);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, CNP.CONFIGURE);
			} else {
				click(CNP.CONFIGURE);
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
			waitforElement(180, CNP.BEGINREMOVAL, driver);
			if (!VerifyObject(driver, SR.canadaLabel)) {
				if (VerifyObject(driver,
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
					WebElement EditClick = driver.findElement(By.xpath(
							"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				} else {
					WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
							+ "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				}
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, SR.canadaLabel, driver);
			waitforElement(60, SR.INTERNATIONAL, driver);
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.ZEROREPORT);
			waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(5000);
			waitforElement(180, CNP.CONFIGUREZEROREPORTTEXT, driver);
			waitforElement(180, CNP.JURISDICTIONSEARCH, driver);

		}
		clear(CNP.JURISDICTIONSEARCH);
		type(CNP.JURISDICTIONSEARCH, jurisdiction);
		Thread.sleep(5000);

		driver.findElement(By.xpath("//div[4]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td/div/a")).click();
		waitforElement(100, "//p[contains(.,'Add Zero Report')]", driver);

		CNP.ZeroReportchkbox.click();
		CNP.SACSettings.sendKeys(Sac);
		selectByValue(CNP.ReportonSettings, Reporton);
		CNP.SAVEBTN.click();
		Thread.sleep(2000);
		// SR.returnToCompanyOverView.click();
		// waitforElement(120, CNP.ZEROREPORT, driver);
	}

	public void EditMOSiteCodes(String CompanyName, String Country, String State, String Jurisdiction,
			String Jurisdictioncode, String NewSiteCode) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		// resultwriteScenario("Edit Missouri Sitecodes", "Verifying Site code
		// Functionality");
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		Thread.sleep(6000);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.MOSITECODES);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(CNP.CONFIGURESITECODDESTEXT.isDisplayed());
		waitforElement(180, CNP.SITECODESSEARCH, driver);
		click(CNP.SITECODESSEARCH);
		clear(CNP.SITECODESSEARCH);
		Thread.sleep(3000);
		type(CNP.SITECODESSEARCH, Jurisdiction);
		Thread.sleep(5000);
		WebElement sitedit = driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction
				+ "')]/../td[contains(.,'" + Jurisdictioncode + "')]/../td/div/a[contains(.,'Edit')]"));
		JavaScriptclick(driver, sitedit);
		Thread.sleep(6000);
		clear(CNP.ADDSITECODEVALUE);
		Thread.sleep(2000);
		type(CNP.ADDSITECODEVALUE, NewSiteCode);
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.BRANCHIDSAVE);
		Thread.sleep(5000);
		assertTrue(CNP.READYTOSAVE.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[contains(.,'" + Jurisdiction + "')]/../td[contains(.,'"
				+ Jurisdictioncode + "')]/../td[contains(.,'" + NewSiteCode + "')]")).isDisplayed());
	}

	public void Jurisdictionson(String CompanyName, String Country, String State, String EffectiveDate)
			throws Exception {
		String USreg, CANreg;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
			WebElement EditClick = driver.findElement(
					By.xpath("//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		} else {
			WebElement EditClick = driver.findElement(By.xpath(
					"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		if (Country.equalsIgnoreCase("CANADA")) {
			JavaScriptclick(driver, SR.canadaLabel);
			Thread.sleep(5000);
		}
		if (Country.equalsIgnoreCase("INTERNATIONAL")) {
			JavaScriptclick(driver, SR.INTERNATIONAL);
			Thread.sleep(5000);
		}
		USreg = "//td/div/strong[text()='" + State
				+ "']/../../../td[@class='text-center align-middle ng-hide']/div/a[contains(text(),'Return(s)')]";
		CANreg = "//strong[text()='Canada']/../../th[@class='text-center align-middle ng-hide']/div/a[contains(text(),'Return(s)')]";
		if (!VerifyObject(driver, CNP.RETURNTOCOMPOVERVIEWHEAD)) {
			if (VerifyObject(driver, CANreg)) {
				if (State != "Canada") {
					WebElement OnOFf1 = driver.findElement(By.xpath("//strong[text()='Canada']/../div/div/input"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf1);
					WebElement OnOFf = driver
							.findElement(By.xpath("//strong[text()='" + State + "']/../div/div/input"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
				} else {
					WebElement OnOFf = driver
							.findElement(By.xpath("//strong[text()='" + State + "']/../div/div/input"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);

				}
			}

			if (VerifyObject(driver, USreg)) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			}

			if (!EffectiveDate.isEmpty()) {
				currDate1 = xGetDate("MM/dd/yyyy");
				scrollDown(driver);
				USreg = "//div/strong[text()='" + State + "']/../../../td/div/span/a[contains(.,'Edit')]";
				CANreg = "//strong[contains(.,'" + State + "')]/../../th/div/span/a[contains(.,'Edit')]";

				if (VerifyObject(driver, USreg)) {
					driver.findElement(
							By.xpath("//div/strong[text()='" + State + "']/../../../td/div/span/a[contains(.,'Edit')]"))
							.click();
				}
				if (VerifyObject(driver, CANreg)) {
					driver.findElement(
							By.xpath("//strong[contains(.,'" + State + "')]/../../th/div/span/a[contains(.,'Edit')]"))
							.click();
				}
				waitforElement(180, SR.EFFDATESAVE, driver);
				clear(SR.TODAYORFUTUREDATE);
				type(SR.TODAYORFUTUREDATE, currDate1);
				sendkeysTab(SR.TODAYORFUTUREDATE);
				Thread.sleep(2000);
				JavaScriptclick(driver, SR.EFFDATESAVE);
				Thread.sleep(2000);
			}
		}
		scrollUp(driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);

	}

	public void USterritorities(String CompanyName, String State) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
			WebElement EditClick = driver.findElement(
					By.xpath("//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		} else {
			WebElement EditClick = driver.findElement(By.xpath(
					"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		if (!VerifyObject(driver,
				"//td/div/strong[text()='" + State + "']/../../../td/div/a[contains(text(),'Return(s)')]")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	public void CanadastatesON(String CompanyName, String State) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
			WebElement EditClick = driver.findElement(
					By.xpath("//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		} else {
			WebElement EditClick = driver.findElement(By.xpath(
					"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		click(SR.canadaLabel);
		if (State.contains(",")) {
			String s[] = State.split(",");
			for (int i = 0; i < s.length; i++) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + s[i] + "']/../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			}
		}
		scrollUp(driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		Thread.sleep(5000);
	}

	public void UploadMOSiteCodeerrorverify(String CompanyName, String State, String File) throws Exception {
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("SiteCodes");
		String Filename;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		Thread.sleep(6000);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.MOSITECODES);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.CONFIGURESITECODDESTEXT, driver);
		waitforElement(180, CNP.SITECODESSEARCH, driver);
		waitforElement(180, CNP.ViewUploadHistorysitecodes, driver);
		JavaScriptclick(driver, CNP.UploadMultiplesitecodes);
		waitforElement(180, CNP.uploadmultipleSitecodestext, driver);
		Thread.sleep(3000);
		if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
			click(CNP.BROWSE);
		} else {
			JavaScriptclick(driver, CNP.BROWSE);
		}
		Thread.sleep(3000);
		Filename = File;
		fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, Filename, ".csv");
		Thread.sleep(4000);
		click(CNP.Uploadfilebtn);
		Thread.sleep(1000);
		assertTrue(CNP.UploadnoBranchID.isDisplayed());
		click(CNP.Cancelbtn);
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.CONFIGURE);

	}

	public void UploadCoBranchIDerrorverify(String CompanyName, String State, String File) throws Exception {
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("BranchID");
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
		if (VerifyObject(driver, CNP.REMOVEJURISDICTION)) {
			Thread.sleep(6000);
			JavaScriptclick(driver, CNP.CANCELREMOVEJURISDICTION);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.COBRANCHNUMBERS);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CNP.SITECODESSEARCH, driver);
		JavaScriptclick(driver, CNP.UploadMultiplesitecodes);
		waitforElement(180, CNP.uploadmultiplebranchIDtext, driver);
		Thread.sleep(3000);
		if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
			click(CNP.BROWSE);
		} else {
			JavaScriptclick(driver, CNP.BROWSE);
		}
		Thread.sleep(3000);
		fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, File, ".csv");
		Thread.sleep(4000);
		click(CNP.Uploadfilebtn);
		if (VerifyObject(driver, CNP.UploadnoBranchID)) {
			assertTrue(CNP.UploadnoBranchID.isDisplayed());
			click(CNP.Cancelbtn);
		}
		JavaScriptclick(driver, CNP.CONFIGURE);
	}

	// Locations
	public void AddLocations(String CompanyName, String State, String LocationCode, String LocationName,
			String RegistrationCode, String StartDate, String Address, String City, String Province, String Zip,
			String SubRoutineStatus, String SubRoutineValue) throws Exception {

		String USloc, CANloc, LocRegCode, LocZip, SubRoutine_Value;
		String[] locCData, locNData;

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		int SubRoutine_Size;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		if (VerifyObject(driver, SR.STQUICKSRCH)) {
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, CNP.CONFIGURE);
			} else {
				click(CNP.CONFIGURE);
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (!VerifyObject(driver, CNP.EDITCOMPANYINFOLINK)) {
			waitforElement(180, CNP.CONFIGURE, driver);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, CNP.CONFIGURE);
			} else {
				click(CNP.CONFIGURE);
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
			// page
			// waitforElement(180, CNP.BEGINREMOVAL, driver);
			if (!VerifyObject(driver, SR.canadaLabel)) {
				if (VerifyObject(driver,
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
					WebElement EditClick = driver.findElement(By.xpath(
							"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				} else {
					WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
							+ "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				}
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, SR.canadaLabel, driver);
			waitforElement(60, SR.INTERNATIONAL, driver);
		}
		if (State.equalsIgnoreCase("CANADA")) {
			JavaScriptclick(driver, SR.canadaLabel);
			Thread.sleep(3000);
		}
		if (State.equalsIgnoreCase("INTERNATIONAL")) {
			JavaScriptclick(driver, SR.INTERNATIONAL);
			Thread.sleep(3000);
		}
		strLocationName = "";
		USloc = "//td/div/strong[text()='" + State
				+ "']/../../../td[@class='text-center align-middle ng-hide']/div/a[contains(text(),'Location(s)')]";
		CANloc = "//strong[text()='" + State
				+ "']/../../th[@class='text-center align-middle ng-hide']/div/a[contains(text(),'Location(s)')]";
		if (!VerifyObject(driver, CNP.RETURNTOCOMPOVERVIEWHEAD)) {
			if (VerifyObject(driver, USloc)) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
				Thread.sleep(3000);
			}
			if (VerifyObject(driver, CANloc)) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
				Thread.sleep(3000);
			}
		}
		if (State.equalsIgnoreCase("CANADA")) {
			waitforElement(30, "//strong[text()='" + State + "']/../../th/div/a[contains(text(),'Location(s)')]",
					driver);
		} else {
			waitforElement(30,
					"//td/div/strong[text()='" + State + "']/../../../td/div/a[contains(text(),'Location(s)')]",
					driver);
		}
		if (VerifyObject(driver,
				"//td/div/strong[text()='" + State + "']/../../../td/div/a[contains(text(),'Location(s)')]")) {
			WebElement Regloc = driver.findElement(By.xpath(
					"//td/div/strong[text()='" + State + "']/../../../td/div/a[contains(text(),'Location(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Regloc);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, "//strong[text()='" + State + "']/../../th/div/a[contains(text(),'Location(s)')]")) {
			WebElement Canloc = driver.findElement(
					By.xpath("//strong[text()='" + State + "']/../../th/div/a[contains(text(),'Location(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Canloc);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, SR.ADDNEWLOCATION, driver);
		JavaScriptclick(driver, SR.ADDNEWLOCATION);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		assertTrue(VerifyObject(driver, SR.ADDNEWLOCDISABLEBTN));
		locCData = LocationCode.split(";");
		LocationCode = xLabelRND(locCData);
		waitforElement(180, SR.LOCATIONCODE, driver);
		type(SR.LOCATIONCODE, LocationCode);
		locNData = LocationName.split(";");
		LocationName = xLabelRND(locNData);
		type(SR.LOCATIONNAME, LocationName);
		LocRegCode = RegistrationCode;
		if (LocRegCode != "") {
			type(SR.REGISTRATIONCODE, LocRegCode);
		}
		clear(SR.LOCATIONSTARTDATE);
		type(SR.LOCATIONSTARTDATE, StartDate);
		sendkeysTab(SR.LOCATIONSTARTDATE);
		String LocAddress = Address;
		type(SR.LOCATIONADDRESS, LocAddress);
		String LocCity = City;
		clear(SR.LOCATIONCITY);
		type(SR.LOCATIONCITY, LocCity);
		sendkeysTab(SR.LOCATIONCITY);
		if (Province != "") {
			selectByValue(SR.PROVINCE, Province);
		}
		LocZip = Zip;
		click(SR.LOCATIONZIP);
		JavaScripttype(driver, SR.LOCATIONZIP, LocZip);
		sendkeysTab(SR.LOCATIONZIP);
		Thread.sleep(2000);
		if (State.equalsIgnoreCase("Colorado")) {
			driver.findElement(By.cssSelector("select[id='locationfield1']")).click();
			assertTrue(SR.PHYSICALLOCOPTION.isDisplayed());
			assertTrue(SR.NONPHYSICALOPTION1.isDisplayed());
			assertTrue(SR.NONPHYSICALOPTION2.isDisplayed());
		}
		click(SR.LOCATIONCITY);
		Thread.sleep(2000);
		if (SubRoutineStatus.equalsIgnoreCase("Yes")) {
			type(SR.SUBROUTINE, SubRoutineValue);
			SubRoutine_Value = getAttribute(SR.SUBROUTINE, "value");
			SubRoutine_Size = SubRoutine_Value.length();
			assertTrue(SubRoutine_Size <= 4);
		}
		if (VerifyObject(driver, SR.ADDLOCATIONBUTTONDISABLE)) {
			boolean report = false;
			do {
				click(SR.LOCATIONZIP);
				driver.findElement(By.xpath(SR.LOCZIP)).sendKeys(Keys.CONTROL + "a");
				driver.findElement(By.xpath(SR.LOCZIP)).sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				driver.findElement(By.xpath(SR.LOCZIP)).sendKeys(Keys.CONTROL + "v");
				sendkeysTab(SR.LOCATIONZIP);
				Thread.sleep(5000);
				if (!VerifyObject(driver, SR.ADDLOCATIONBUTTONDISABLE)) {
					click(SR.ADDLOCATIONBUTTON);
					report = true;
				}
			} while (report == false);
		} else {
			JavaScriptclick(driver, SR.ADDLOCATIONBUTTON);
		}
		waitUntilElementInvisibe(180, "//spin-icon[@class='ng-scope']/div[@class='spinner']", driver);
		waitforElement(180, SR.CONFIRMATIONPOPUP, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, SR.CONFIRMATIONPOPUP);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, SR.returnToCompanyOverView, driver);
		assertTrue(driver.findElement(By.xpath("//a[contains(.,'" + LocationCode + "')]/i")).isDisplayed());
		JavaScriptclick(driver, SR.returnToCompanyOverView);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, SR.RETURNTOCONFIGOVERVIEW, driver);
	}

	public void AdminDeleteLocations(String ClientName, String Location_Code) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		AdminUserLinks ADMIN = PageFactory.initElements(driver, AdminUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		VarUserTitles = "";
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		} else {
			click(ADMIN.MANAGESMBCUSTOMERS);
		}
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforText(180, "//table/tbody/tr[2]/td/div/a[contains(.,'" + ClientName + "')]", driver);
		JavaScriptclick(driver, driver.findElement(By.xpath("//td/div/a[contains(.,'" + ClientName + "')]")));
		waitforElement(180, VSC.SMBCUSTOMERREPORT, driver);
		JavaScriptclick(driver, VSC.SMBCUSTOMERREPORT);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//th[contains(.,'Action')]/../../../tbody/tr/td[contains(.,'" + Location_Code
				+ "')]/../td/a[contains(.,'Delete')]")).click();
		waitforElement(180, VSC.DeleteLocationPopUp, driver);
		assertTrue(VSC.DeleteLocationPopUp.isDisplayed());
		waitforElement(180, VSC.CancelBtn, driver);
		JavaScriptclick(driver, VSC.CancelBtn);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//th[contains(.,'Action')]/../../../tbody/tr/td[contains(.,'" + Location_Code
				+ "')]/../td/a[contains(.,'Delete')]")).click();
		waitforElement(180, VSC.DeleteLocationPopUp, driver);
		JavaScriptclick(driver, VSC.DeleteLocationBtn);
	}

	public void city(String CompanyName, String State, String cityoff, String citiname, String City_EffectDate,
			String VerifyAdvancedSettings) throws Exception {
		String USads = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		Thread.sleep(5000);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		if (State.equalsIgnoreCase("CANADA")) {
			JavaScriptclick(driver, SR.canadaLabel);
			Thread.sleep(5000);
		}
		if (State.equalsIgnoreCase("INTERNATIONAL")) {
			JavaScriptclick(driver, SR.INTERNATIONAL);
			Thread.sleep(5000);
		}
		USads = "//td/div/strong[text()='" + State
				+ "']/../../../td[@class='text-center align-middle ng-scope ng-hide']/a[contains(text(),'Advanced Settings')]";
		if (!VerifyObject(driver, CNP.RETURNTOCOMPOVERVIEWHEAD)) {
			if (VerifyObject(driver, USads)) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			}
		}

		if (VerifyObject(driver,
				"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]")) {
			WebElement Usads = driver.findElement(By.xpath(
					"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Usads);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (cityoff.equalsIgnoreCase("cityoff")) {
			click(SR.ALLCITI);
			if (VerifyObject(driver, SR.CITYEDITALL)) {
				click(SR.ALLCITI);
			}
			click(SR.SVEJURS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CNP.RETURNTOCONFIGOVERVIEW, driver);
		} else {
			waitforElement(180, SR.CITYEDIT, driver);
			JavaScriptclick(driver, SR.CITYEDIT);
			// Thread.sleep(5000);
			waitforElement(180, SR.JURSR1, driver);
			String citieslist[] = citiname.split(",");
			for (int i = 0; i < citieslist.length; i++) {
				WebElement citi = driver.findElement(By.xpath(
						"//tr[@ng-repeat='city in vm.lowers.cities.items track by city.id']/td/label[contains(.,'"
								+ citieslist[i] + "')]/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", citi);
			}
			JavaScriptclick(driver, SR.SAVECITI);
			waitforElement(180, SR.COMPSTRTDTE, driver);
			if ((City_EffectDate.equalsIgnoreCase("cstrtdate")) || (cityoff.equalsIgnoreCase("Off-Comp"))) {
				click(SR.COMPSTRTDTE);
			}
			click(SR.SVEJURS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CNP.RETURNTOCONFIGOVERVIEW, driver);
			if (VerifyAdvancedSettings.equalsIgnoreCase("YES")) {
				WebElement Usads = driver.findElement(By.xpath(
						"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Usads);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				waitforElement(180, SR.CITYEDIT, driver);
				JavaScriptclick(driver, SR.CITYEDIT);
				// Thread.sleep(5000);
				waitforElement(180, SR.JURSR1, driver);
				// taking city values
				WebElement citi1 = driver.findElement(By.xpath(
						"//tr[@ng-repeat='city in vm.lowers.cities.items track by city.id']/td/label[contains(.,'"
								+ citiname + "')]/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", citi1);

				JavaScriptclick(driver, SR.SAVECITI);
				waitforElement(180, SR.COMPSTRTDTE, driver);
				assertTrue(SR.CITYCHECKBOX.isSelected());
				click(SR.SVEJURS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, CNP.RETURNTOCONFIGOVERVIEW, driver);
			}
		}
	}

	public void county(String CompanyName, String State, String County, String County_EffectDate) throws Exception {
		String USads = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		if (State.equalsIgnoreCase("CANADA")) {
			JavaScriptclick(driver, SR.canadaLabel);
			Thread.sleep(5000);
		}
		if (State.equalsIgnoreCase("INTERNATIONAL")) {
			JavaScriptclick(driver, SR.INTERNATIONAL);
			Thread.sleep(5000);
		}
		USads = "//td/div/strong[text()='" + State
				+ "']/../../../td[@class='text-center align-middle ng-scope ng-hide']/a[contains(text(),'Advanced Settings')]";
		if (!VerifyObject(driver, CNP.RETURNTOCOMPOVERVIEWHEAD)) {
			if (VerifyObject(driver, USads)) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			}
		}

		if (VerifyObject(driver,
				"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]")) {
			WebElement Usads = driver.findElement(By.xpath(
					"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Usads);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);

		if (VerifyObject(driver,
				"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]")) {
			WebElement Usads = driver.findElement(By.xpath(
					"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Usads);
		}
		waitforElement(180, SR.COUNTIESEDIT, driver);
		JavaScriptclick(driver, SR.COUNTIESEDIT);
		Thread.sleep(5000);
		String Countylist[] = County.split(",");
		for (int i = 0; i < Countylist.length; i++) {
			WebElement counti = driver.findElement(By.xpath(
					"//tr[@ng-repeat='county in vm.lowers.counties.items track by county.id']/td/label[contains(.,'"
							+ Countylist[i] + "')]/input"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", counti);
		}
		JavaScriptclick(driver, SR.SAVECOUNTI);
		waitforElement(180, SR.COMPSTRTDTE, driver);
		if (County_EffectDate.equalsIgnoreCase("cstrtdate")) {
			click(SR.COMPSTRTDTE);
		}
		click(SR.SVEJURS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.RETURNTOCONFIGOVERVIEW, driver);

	}

	public void districts(String CompanyName, String State, String District, String District_EffectDate)
			throws Exception {
		String USads = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			click(CNP.CONFIGURE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000); // After progress busy complete, its takes 5 secs to enable the objects in the
		// page
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, SR.canadaLabel)) {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			} else {
				WebElement EditClick = driver.findElement(By.xpath(
						"//h2[contains(.,'" + CompanyName + "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, SR.canadaLabel, driver);
		waitforElement(60, SR.INTERNATIONAL, driver);
		if (State.equalsIgnoreCase("CANADA")) {
			JavaScriptclick(driver, SR.canadaLabel);
			Thread.sleep(5000);
		}
		if (State.equalsIgnoreCase("INTERNATIONAL")) {
			JavaScriptclick(driver, SR.INTERNATIONAL);
			Thread.sleep(5000);
		}
		USads = "//td/div/strong[text()='" + State
				+ "']/../../../td[@class='text-center align-middle ng-scope ng-hide']/a[contains(text(),'Advanced Settings')]";
		if (!VerifyObject(driver, CNP.RETURNTOCOMPOVERVIEWHEAD)) {
			if (VerifyObject(driver, USads)) {
				WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../../div/div/input"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			}
		}

		if (VerifyObject(driver,
				"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]")) {
			WebElement Usads = driver.findElement(By.xpath(
					"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Usads);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);

		if (VerifyObject(driver,
				"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]")) {
			WebElement Usads = driver.findElement(By.xpath(
					"//td/div/strong[text()='" + State + "']/../../../td/a[contains(text(),'Advanced Settings')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Usads);
		}
		waitforElement(180, SR.DISTRICTSEDIT, driver);
		JavaScriptclick(driver, SR.DISTRICTSEDIT);
		Thread.sleep(5000);
		String Districtlist[] = District.split(",");
		for (int i = 0; i < Districtlist.length; i++) {
			WebElement dist = driver.findElement(By.xpath(
					"//tr[@ng-repeat='district in vm.lowers.districts.items track by district.id']/td/label[contains(.,'"
							+ Districtlist[i] + "')]/input"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", dist);
		}
		JavaScriptclick(driver, SR.SAVEDIST);
		waitforElement(180, SR.COMPSTRTDTE, driver);
		if (District_EffectDate.equalsIgnoreCase("cstrtdate")) {
			click(SR.COMPSTRTDTE);
		}
		click(SR.SVEJURS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.RETURNTOCONFIGOVERVIEW, driver);

	}

	// Products
	public void CreateProduct(String TaxpayerName, String BuysellProduct, String ProductCode, String ProductType,
			String ProductName, String StartDate, String EndDate, String FirstSelction, String SecondSelction,
			String ThirdSelction, String FourthSelction, String FifthSelction, String SixthSelction,
			String SeventhSelction, String VerifyingDuplicateProdcode, String Verificatons) throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + TaxpayerName
				+ "')]/../../../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + TaxpayerName
					+ "')]/../../../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + TaxpayerName
				+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + TaxpayerName
					+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.BUYSELLPRODT, driver);
		waitforElement(180, PP.GENERAL, driver);
		if (VerifyObject(driver, PP.ADDMAPPING)) {
			click(PP.ADDMAPPING);
			waitforElement(180, PP.ADD_A_NEW_SINGLPRODUCT, driver);
			JavaScriptclick(driver, PP.ADD_A_NEW_SINGLPRODUCT);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(30, PP.BUYSELLPRODT, driver);
		}
		selectByValue(PP.BUYSELLPRODT, BuysellProduct);
		Thread.sleep(2000);
		selectByValue(PP.PRODUCTTYPE, ProductType);
		Thread.sleep(2000);
		if (!ProductType.equalsIgnoreCase("Taxpayer")) {
			click(PP.PRODUCTCODE);
			Thread.sleep(1000);
			type(PP.PRODUCTCODE, ProductCode);
			// sendkeysTab(PP.PRODUCTCODE);
			if (!VerifyingDuplicateProdcode.isEmpty()) {
				waitforElement(60, CNP.CODECLASSALREADYEXISTS, driver);
				assertTrue(CNP.CODECLASSALREADYEXISTS.isDisplayed());
				if (VerifyObject(driver, CNP.CODECLASSALREADYEXISTS)) {
					selectByValue(PP.PRODUCTTYPE, VerifyingDuplicateProdcode);
				}
				type(PP.PRODUCTCODE, ProductCode);
			}
			if (!Verificatons.isEmpty()) {
				// Verifying i icon and text of Type
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				String TypeIcon = driver.findElement(By.xpath("//inline-help[@code='configprdtype']/a"))
						.getAttribute("id");
				jse.executeScript("$('[id=" + TypeIcon + "]').trigger('mouseover')");
				Thread.sleep(2000);
				if (VerifyObject(driver, PP.POPUPTYPE)) {
				} else {
				}
				// Verifying i icon and text of Code/Class
				String ClassIcon = driver.findElement(By.xpath("//inline-help[@code='configprdcd']/a"))
						.getAttribute("id");
				jse.executeScript("$('[id=" + ClassIcon + "]').trigger('mouseover')");
				Thread.sleep(2000);
				if (VerifyObject(driver, PP.POPUPCODECLASS)) {
				} else {
				}
				// Verifying i icon and text of Startdate
				ClassIcon = driver.findElement(By.xpath("//inline-help[@code='configprdstdt']/a")).getAttribute("id");
				jse.executeScript("$('[id=" + ClassIcon + "]').trigger('mouseover')");
				Thread.sleep(2000);
				if (VerifyObject(driver, PP.startdatetext)) {
				} else {
				}

			}
		}
		if (!ProductType.equalsIgnoreCase("Taxpayer")) {
			type(PP.PRODNAME, ProductName);
			sendkeysTab(PP.PRODNAME);
		}
		clear(PP.STARTDATE);
		JavaScriptclick(driver, PP.STARTDATE);
		type(PP.STARTDATE, StartDate);
		sendkeysTab(PP.STARTDATE);
		if (EndDate != "") {
			clear(PP.ENDDATE);
			type(PP.ENDDATE, EndDate);
			sendkeysTab(PP.ENDDATE);
		}
		Thread.sleep(5000);
		if (FirstSelction != "") {
			WebElement First = driver.findElement(
					By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + FirstSelction + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", First);
		}
		if (SecondSelction != "") {
			WebElement sec = driver.findElement(
					By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + SecondSelction + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sec);
		}
		if (ThirdSelction != "") {
			WebElement third = driver.findElement(
					By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + ThirdSelction + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", third);
		}
		if (FourthSelction != "") {
			WebElement fourth = driver.findElement(
					By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + FourthSelction + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", fourth);
		}
		if (FifthSelction != "") {
			WebElement fifth = driver.findElement(
					By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + FifthSelction + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", fifth);
		}
		if (SixthSelction != "") {
			WebElement sixth = driver.findElement(
					By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + SixthSelction + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", sixth);
		}
		if (SeventhSelction != "") {
			WebElement seventh = driver.findElement(
					By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + SeventhSelction + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", seventh);
		}
		waitforElement(180, PP.SAVEANDCLOSE, driver);
		click(PP.SAVEANDCLOSE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (VerifyObject(driver, PP.READYTOSAVE)) {
			JavaScriptclick(driver, PP.READYTOSAVE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
	}

	public void VerifyHelplink()throws Exception
	{
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.Visit_Help_Center, driver);
		JavaScriptscrolltoView(driver, CNP.Visit_Help_Center);
		JavaScriptclick(driver, CNP.Visit_Help_Center);
		Thread.sleep(5000);
		String Childwindow = driver.getWindowHandle();
		Set<String> Window1 = driver.getWindowHandles();
//		Window1.remove(Childwindow);
		driver.switchTo().window(Window1.iterator().next());
		for (String winHandle : Window1) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(10000);
		assertTrue(CNP.Vertex_Community.isDisplayed());
		JavaScriptscrolltoView(driver, CNP.Log_in_toVertex_Community);
		assertTrue(CNP.Log_in_toVertex_Community.isDisplayed());
		assertTrue(CNP.Help_Email.isDisplayed());
		assertTrue(CNP.Help_Password.isDisplayed());
		Thread.sleep(1000);
		driver.switchTo().window(Childwindow);
		
		
	}
	
	public void UpdateSecondProduct(String CompanyName, String ProductName) throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		resultwriteScenario("Updated Second Product", "Verifying Updated Second Product Functionality");
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
				+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ViewAllProducts, driver);
		JavaScriptclick(driver, PP.ViewAllProducts);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.EDITSECPROD, driver);
		JavaScriptclick(driver, PP.EDITSECPROD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, PP.PRODUCTS_QUICK_SEARCH);
		type(PP.PRODUCTS_QUICK_SEARCH, ProductName);
		driver.findElement(By.xpath("//a[@class='tt-suggestion type-ahead-hover ng-scope']")).click();
		Thread.sleep(5000);
		JavaScriptclick(driver, PP.SAVEANDCLOSE);
		assertTrue(PP.DUPLICATE.isDisplayed());
		assertTrue(driver
				.findElement(By
						.xpath("//p/strong[contains(.,'" + CompanyName + " is already mapped to Appointment Books.']"))
				.isDisplayed());
		JavaScriptclick(driver, PP.DUPLICATECATNAME);
		JavaScriptclick(driver, PP.ViewAllProducts);
	}

	public void UploadMappings(String Taxpayername, String Filename, String Fileformat) throws Exception {
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("Products");
		Robot robot = new Robot();
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		// waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Taxpayername
				+ "')]/../../../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + Taxpayername
					+ "')]/../../../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Taxpayername
				+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsGreen = driver.findElement(By.xpath(
					"//h2[contains(.,'Taxpayername')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.BUYSELLPRODT, driver);
		waitforElement(180, PP.GENERAL, driver);
		JavaScriptclick(driver, PP.UploadMulProducts);
		// For keyboard event required wait time to enter and tab
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			driver.navigate();
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_S);
			Thread.sleep(5000);
		}
		waitforElement(180, PP.BROWSE, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, PP.BROWSE);
		} else {
			click(PP.BROWSE);
		}
		Thread.sleep(7000); // to wait for the file system window attched browser (AutoIt)
		fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, Filename, Fileformat);
		Thread.sleep(8000);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, PP.UPLOADFILE);
		} else {
			click(PP.UPLOADFILE);
		}
		Thread.sleep(5000);
		if (!(Filename.equalsIgnoreCase("3000Products") || Filename.equalsIgnoreCase("VertexTaxCodesMaxlength"))) {
			waitforElement(360, "//tbody/tr/td[text()='" + Filename + "']/../td/a[text()='Success File']", driver);
			WebElement successfile = driver
					.findElement(By.xpath("//tbody/tr/td[text()='" + Filename + "']/../td/a[text()='Success File']"));
			if (VerifyObject(driver, successfile)) {
				JavaScriptclick(driver, successfile);
			}
		}
		if (VerifyObject(driver, PP.READYTOSAVE)) {
			JavaScriptclick(driver, PP.READYTOSAVE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
	}

	public void BulkUploadProducts(String Company, String Upload_Product_Code, String Download_Template,
			String File_Name, String Verify_Successfile, String Validate, String Compare_Filename,
			String Verify_Errorfile, String Verify_Count, String ExpiredCount, String MappedCount, String UnmappedCount,
			String SaveConfiguration) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		strProdName = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		String DownloadFilepath = xDownloadFiles("Products");
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("Products");
		PortalFunctions DownloadFiles = new PortalFunctions(driver);
		String Filename;
		Robot robot = new Robot();
		int count;
		File tempfile = null;
		File tempfile2 = null;
		String[] nextLine;
		String path, Cell_Name;
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// Verifying the Product state, and refreshing if it is in Yellow
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}

		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);

		// ************* Upload Product ***************
		if (Upload_Product_Code != "") {
			click(PP.UploadMulProducts);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.UPLOADFILE, driver);
			assertTrue(VerifyObject(driver, PP.ViewAllProducts));
			if (Upload_Product_Code.equalsIgnoreCase("YES")) {
				if (Download_Template.equalsIgnoreCase("YES")) {
					if (Browser.equalsIgnoreCase("IE")) {
						JavaScriptclick(driver, PP.DWNLD_FILE_FRMT_TEMP);
					} else {
						click(PP.DWNLD_FILE_FRMT_TEMP);
						// Verifying the downloaded template
						Thread.sleep(5000);
						if (Browser.equalsIgnoreCase("Chrome")) {
							File directory = new File(".//");
							path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
									+ "Products";
							System.out.println("Path" + path);
							MoveLatestDownloadFileFromOnedirtoAnother(path);
							Thread.sleep(10000);
							LatestDownloadedFile("Products", true);
						}
						if (Browser.equalsIgnoreCase("Firefox")) {
							LatestDownloadedFile("Products", false);
						}
						CSVReader reader = new CSVReader(new FileReader(Filepath));
						ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
						while ((nextLine = reader.readNext()) != null) {
							ArrayList<String> list = new ArrayList<String>();
							for (int t = 0; t < nextLine.length; t++) {
								list.add(nextLine[t]);
							}
							array.add(list);
						}
						Thread.sleep(5000);
						Cell_Name = array.get(0).get(0).trim();
						assertTrue(Cell_Name.equalsIgnoreCase("Results"));
						Cell_Name = array.get(0).get(1).trim();
						assertTrue(Cell_Name.equalsIgnoreCase("Code Or Class"));
						Cell_Name = array.get(0).get(2).trim();
						assertTrue(Cell_Name.equalsIgnoreCase("Name"));
						Cell_Name = array.get(0).get(3).trim();
						assertTrue(Cell_Name.equalsIgnoreCase("Tax Type"));
						Cell_Name = array.get(0).get(4).trim();
						assertTrue(Cell_Name.equalsIgnoreCase("Code Or Class Type"));
						Cell_Name = array.get(0).get(5).trim();
						assertTrue(Cell_Name.equalsIgnoreCase("Start Date"));
						Cell_Name = array.get(0).get(6).trim();
						assertTrue(Cell_Name.equalsIgnoreCase("End Date"));
						Cell_Name = array.get(0).get(7).trim();
						assertTrue(Cell_Name.equalsIgnoreCase("Vertex Tax Code"));
					}
					if (Browser.equalsIgnoreCase("IE")) {
						Thread.sleep(10000);
						robot.keyPress(KeyEvent.VK_TAB);
						robot.keyRelease(KeyEvent.VK_TAB);
						robot.keyPress(KeyEvent.VK_TAB);
						robot.keyRelease(KeyEvent.VK_TAB);
						robot.keyPress(KeyEvent.VK_DOWN);
						robot.keyRelease(KeyEvent.VK_DOWN);
						robot.keyPress(KeyEvent.VK_DOWN);
						robot.keyRelease(KeyEvent.VK_DOWN);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						Thread.sleep(2000);
						robot.keyPress(KeyEvent.VK_HOME);
						robot.keyRelease(KeyEvent.VK_HOME);
						Thread.sleep(5000);
						StringSelection stringSelection = new StringSelection(DownloadFilepath + "\\");
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
						Thread.sleep(5000);
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.delay(5000);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.delay(1000);
						Thread.sleep(5000);
					}
					if (Browser.equalsIgnoreCase("Chrome")) {
						File directory = new File(".//");
						path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Products";
						System.out.println("Path" + path);
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(10000);
						DownloadFiles.LatestDownloadedFile("Products", false);

					}
					if (Browser.equalsIgnoreCase("Firefox")) {
						Thread.sleep(5000);
						LatestDownloadedFile("Products", false);
					}
				}

				Thread.sleep(1000);
				if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
					click(PP.BROWSE);
				} else {
					JavaScriptclick(driver, PP.BROWSE);
				}
				Thread.sleep(3000);
				Filename = File_Name;
				fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, Filename, ".csv");
				Thread.sleep(4000);
				click(PP.UPLOADFILE);
				Thread.sleep(4000);
				waitforElement(180, PP.UPLOADADDPRODUCT, driver);
				waitforElement(180, "//tbody/tr[1]/td[text()='" + File_Name + "']/../td/a[text()='Original File']",
						driver);

				String SuccessFile = "//table/tbody/tr/td[contains(.,'" + File_Name
						+ "')]/../td/a[not(@class='ng-hide') and contains(.,'Success File')]";
				String ErrorFile = "//table/tbody/tr/td[contains(.,'" + File_Name
						+ "')]/../td/a[contains(.,'Error File')]";

				if ((Verify_Successfile.equalsIgnoreCase("YES"))) {

					FIRST: if (!VerifyObject(driver, SuccessFile)) {
						boolean report = false;
						count = 0;
						do {
							Thread.sleep(5000);
							if (VerifyObject(driver, ErrorFile)) {

								break FIRST;
							}
							count = count + 1;
							if (count == 20) {
								break;
							}
							if (VerifyObject(driver, SuccessFile)) {
								report = true;
							}
						} while (report == false);
					}

					driver.findElement(By.xpath("//td[contains(.,'" + File_Name + "')]/../td/a[text()='Success File']"))
							.click();
					Thread.sleep(15000);
					if (Browser.equalsIgnoreCase("Chrome")) {
						File directory = new File(".//");
						path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Products";
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(10000);
						tempfile = new File(LatestDownloadedFile("Products", true));

					}
					if (Browser.equalsIgnoreCase("Firefox")) {
						tempfile = new File(LatestDownloadedFile("Products", false));
					}

					if (Validate != "") {

						File directory = new File(".//");
						String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
								+ "Products";
						String name1 = Compare_Filename;
						tempfile2 = new File(DirName1 + "\\" + name1 + "_suc.csv");
						Thread.sleep(5000);
						Comparecsvfiles(tempfile, tempfile2);
					}
				}

				if (Verify_Errorfile.equalsIgnoreCase("YES")) {
					FIRST: if (!VerifyObject(driver, ErrorFile)) {
						boolean report = false;
						count = 0;
						do {
							Thread.sleep(30000);
							if (VerifyObject(driver, SuccessFile)) {
								break FIRST;
							}
							count = count + 1;
							if (count == 8) {
								break;
							}
							if (VerifyObject(driver, ErrorFile)) {
								report = true;
							}
						} while (report == false);
					}

					WebElement Errfile = driver.findElement(
							By.xpath("//td[contains(.,'" + File_Name + "')]/../td/a[text()='Error File']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Errfile);
					Thread.sleep(20000);

					if (Browser.equalsIgnoreCase("Chrome")) {
						File directory = new File(".//");
						path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Products";
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(5000);
						tempfile = new File(LatestDownloadedFile("Products", true));

					}
					if (Browser.equalsIgnoreCase("Firefox")) {
						tempfile = new File(LatestDownloadedFile("Products", false));
					}

					if (Validate != "") {

						File directory = new File(".//");
						String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
								+ "Products";
						String name1 = Compare_Filename;
						tempfile2 = new File(DirName1 + "\\" + name1 + "_err.csv");
						Thread.sleep(5000);
						Comparecsvfiles(tempfile, tempfile2);
					}

				}
			}
			if (VerifyObject(driver, PP.ADDPRODINPRODLIST)) {
				if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
					click(PP.ADDPRODINPRODLIST);
				} else {
					JavaScriptclick(driver, PP.ADDPRODINPRODLIST);
				}
				waitforElement(180, PP.UploadMulProducts, driver);
			}
			if (Verify_Count != "") {
				if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
					click(PP.ViewAllProducts);
				} else {
					JavaScriptclick(driver, PP.ViewAllProducts);
				}

				waitforElement(180, PP.ADDPRODMAPPING, driver);
				waitforText(180, PP.FILTERSELECT, driver);
				if (ExpiredCount != "") {
					waitforElement(180, PP.EXPIRED, driver);

					if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
						click(PP.EXPIRED);
					} else {
						JavaScriptclick(driver, PP.EXPIRED);
					}
					List<WebElement> tablerows = driver.findElements(By.xpath("//table[@id='tblProducts']/tbody/tr"));
					int Appcount = tablerows.size();
					String Excount = ExpiredCount;
					int Ecount = Integer.parseInt(Excount);
					if (Ecount == Appcount) {
						assertTrue(Ecount == Appcount);
					}
				}
				if (MappedCount != "") {
					selectByValue(PP.FILTERSELECT, "Mapping Ready to Save");
					List<WebElement> tablerows = driver.findElements(By.xpath("//table[@id='tblProducts']/tbody/tr"));
					int Appcount = tablerows.size();
					String Excount = MappedCount;
					int Ecount = Integer.parseInt(Excount);
					if (Ecount == Appcount) {
						assertTrue(Ecount == Appcount);
					}
				}
				if (UnmappedCount != "") {
					selectByValue(PP.FILTERSELECT, "Unmapped Products");
					List<WebElement> tablerows = driver.findElements(By.xpath("//table[@id='tblProducts']/tbody/tr"));
					int Appcount = tablerows.size();
					String Excount = UnmappedCount;
					int Ecount = Integer.parseInt(Excount);
					if (Ecount == Appcount) {
						assertTrue(Ecount == Appcount);
					}
				}
				waitforElement(180, PP.ADDPRODMAPPING, driver);
				if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
					click(PP.ADDPRODMAPPING);
				} else {
					JavaScriptclick(driver, PP.ADDPRODMAPPING);
				}

				waitforElement(180, PP.UploadMulProducts, driver);
			}
		}
		if (VerifyObject(driver, CNP.SAVECONFIGURATION)) {
			if (!(SaveConfiguration.equalsIgnoreCase("NO"))) {
				waitforElement(180, CNP.SAVECONFIGURATION, driver);
				if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
					click(CNP.SAVECONFIGURATION);
				} else {
					JavaScriptclick(driver, CNP.SAVECONFIGURATION);
				}
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
				click(CNP.COMPLETECONFIGPOPUP);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, CNP.STATEINPROCESS, driver);
			}
		}

		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
	}

	public void EditQualifyingConditions(String Company, String Product_Name, String Edit_category, String State)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		strProdName = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.BEGINREMOVAL)) {
			Companynamesplit = Company;
			CompnameSplit(driver, Companynamesplit, "");
			CompnameSplit(driver, Companynamesplit, "Mapped");
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.FILTERSELECT, driver);
			selectByValue(PP.FILTERSELECT, "All");
			Thread.sleep(2000);
		}
		selectByValue(PP.FILTERSELECT, "All");
		waitforElement(120, "//table[@id='tblMappings']/tbody/tr/td[contains(.,'" + Product_Name
				+ "')]/../td/a[2][contains(.,'Edit')]", driver);
		WebElement MappedProductEDIT = driver.findElement(By.xpath("//table[@id='tblMappings']/tbody/tr/td[contains(.,'"
				+ Product_Name + "')]/../td/a[2][contains(.,'Edit')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductEDIT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.CategoryDesc, driver);
		waitforElement(180, PP.ADDQUALICOND, driver);
		// Editing the tax payer category after save
		JavaScriptclick(driver, PP.PRODUCTS_QUICK_SEARCH);
		type(PP.PRODUCTS_QUICK_SEARCH, Edit_category);
		Thread.sleep(1000);
		PP.PRODUCTS_QUICK_SEARCH.sendKeys(Keys.SPACE);
		Thread.sleep(2000);
		PP.PRODUCTS_QUICK_SEARCH.sendKeys(Keys.TAB);
		if (State.equalsIgnoreCase("Connecticut") || State.equalsIgnoreCase("Iowa")) {
			JavaScriptclick(driver, PP.ADDQUALICOND);
			Thread.sleep(1000);
			waitforElement(180, PP.STATE1, driver);
			selectByValue(PP.STATE1, State);
			Thread.sleep(2000);
			Thread.sleep(2000);
			JavaScriptclick(driver, PP.TODAYRADIO);
			Thread.sleep(3000);
			Thread.sleep(2000);
			JavaScriptclick(driver, PP.RULEDESCRIPTIONRADIO);
			Thread.sleep(3000);
			click(PP.IMPOSITIONTYPE);
			Thread.sleep(2000);
			click(PP.SAVEANDCLOSE);
			Thread.sleep(1000);
		}
	}

	public void BulkUploadProducts(String Company, String File_Name) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		String browser = Browser;
		String AutoITpath = xAutoIT();
		int count = 0;
		String AdjustmentFilePath = xUploadAdjustmentFile("Products");
		String Filename, path;
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);

		// ************* Upload Product ***************
		click(PP.UploadMulProducts);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.UPLOADFILE, driver);
		Thread.sleep(1000);
		if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
			click(PP.BROWSE);
		} else {
			JavaScriptclick(driver, PP.BROWSE);
		}
		Thread.sleep(3000);
		Filename = File_Name;
		fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, Filename, ".csv");
		Thread.sleep(4000);
		click(PP.UPLOADFILE);
		Thread.sleep(4000);
		waitforElement(180, PP.UPLOADADDPRODUCT, driver);
		waitforElement(180, "//tbody/tr[1]/td[text()='" + File_Name + "']/../td/a[text()='Original File']", driver);
		String SuccessFile = "//table/tbody/tr/td[contains(.,'" + File_Name
				+ "')]/../td/a[not(@class='ng-hide') and contains(.,'Success File')]";
		String ErrorFile = "//table/tbody/tr/td[contains(.,'" + File_Name + "')]/../td/a[contains(.,'Error File')]";
		if (!VerifyObject(driver, ErrorFile)) {
			FIRST: if (!VerifyObject(driver, ErrorFile)) {
				boolean report = false;
				count = 0;
				do {
					Thread.sleep(30000);
					if (VerifyObject(driver, SuccessFile)) {
						break FIRST;
					}
					count = count + 1;
					if (count == 8) {
						break;
					}
					if (VerifyObject(driver, ErrorFile)) {
						report = true;
					}
				} while (report == false);
			}
			WebElement Errfile = driver
					.findElement(By.xpath("//td[contains(.,'" + File_Name + "')]/../td/a[text()='Error File']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Errfile);
			Thread.sleep(20000);

			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Products";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(5000);
				new File(LatestDownloadedFile("Products", true));

			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				new File(LatestDownloadedFile("Products", false));
			}
		}

		if (VerifyObject(driver, PP.ADDPRODINPRODLIST)) {
			if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
				click(PP.ADDPRODINPRODLIST);
			} else {
				JavaScriptclick(driver, PP.ADDPRODINPRODLIST);
			}
			waitforElement(180, PP.UploadMulProducts, driver);
		}

		if (VerifyObject(driver, CNP.SAVECONFIGURATION)) {
			waitforElement(180, CNP.SAVECONFIGURATION, driver);
			if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
				click(CNP.SAVECONFIGURATION);
			} else {
				JavaScriptclick(driver, CNP.SAVECONFIGURATION);
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
			click(CNP.COMPLETECONFIGPOPUP);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CNP.STATEINPROCESS, driver);
		}
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
	}

	public void QualifyingConditions(String Company, String State, String JurisdictionType, String LowerJurisdiction,
			String Product_Name, String impositionType) throws Exception {
		strProdName = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.BEGINREMOVAL)) {
			Companynamesplit = Company;
			CompnameSplit(driver, Companynamesplit, "");
			CompnameSplit(driver, Companynamesplit, "Mapped");
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.FILTERSELECT, driver);
			Thread.sleep(1000);
			selectByValue(PP.FILTERSELECT, "All");
			Thread.sleep(10000);
		}
		Thread.sleep(10000);
		waitforElement(180, PP.paginationExtention, driver);
		Thread.sleep(10000);
		selectByValue(PP.paginationExtention, "100 per page");
		Thread.sleep(10000);
		WebElement MappedProductEDIT = driver.findElement(By.xpath("//table[@id='tblMappings']/tbody/tr/td[contains(.,'"
				+ Product_Name + "')]/../td/a[2][contains(.,'Edit')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductEDIT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, PP.CategoryDesc, driver);
		waitforElement(40, PP.GENERAL, driver);
		waitforElement(30, PP.ADDQUALICOND, driver);
		JavaScriptclick(driver, PP.ADDQUALICOND);
		Thread.sleep(1000);
		waitforElement(180, PP.STATE, driver);
		selectByValue(PP.STATE, State);
		Thread.sleep(1000);
		if (!JurisdictionType.equalsIgnoreCase("State")) {
			selectByValue(PP.JURISDICTIONTYPE, JurisdictionType);
			Thread.sleep(1000);
			if (!LowerJurisdiction.isEmpty()) {
				selectByValue(PP.LOWERJURISDICTION, LowerJurisdiction);
			}
			Thread.sleep(1000);
		}

		if (!impositionType.isEmpty()) {
			click(PP.ImpositionType);
			Thread.sleep(1000);
			selectByValue(PP.QualcondImp, impositionType);
			Thread.sleep(1000);
		}

		if (State.equalsIgnoreCase("Connecticut") || State.equalsIgnoreCase("Iowa") || State.equalsIgnoreCase("Arizona")
				|| State.equalsIgnoreCase("Alabama") || State.equalsIgnoreCase("Arkansas")
				|| State.equalsIgnoreCase("Georgia")) {
			Thread.sleep(2000);
			JavaScriptclick(driver, PP.TODAYRADIO);
			Thread.sleep(6000);
			if (VerifyObject(driver, PP.RULE1)) {
				assertTrue(PP.RULE1.isDisplayed());
			}
			Thread.sleep(5000);
			if (VerifyObject(driver, PP.RULE2)) {
				assertTrue(PP.RULE2.isDisplayed());
			}
			Thread.sleep(5000);
			JavaScriptclick(driver, PP.RULEDESCRIPTIONRADIO);
			Thread.sleep(3000);
		}
		click(PP.IMPOSITIONTYPE);
		JavaScriptclick(driver, PP.SAVECLOSEBUTTON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.FILTERSELECT, driver);

	}

	public void RemoveQCafterSaveconfig(String Company, String Product_Name) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		strProdName = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.BEGINREMOVAL)) {
			Companynamesplit = Company;
			CompnameSplit(driver, Companynamesplit, "");
			CompnameSplit(driver, Companynamesplit, "Mapped");
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.FILTERSELECT, driver);
			selectByValue(PP.FILTERSELECT, "All");
			Thread.sleep(2000);
		}

		WebElement MappedProductRemove = driver
				.findElement(By.xpath("//table[@id='tblMappings']/tbody/tr/td[contains(.,'" + Product_Name
						+ "')]/../td/a[contains(.,'Remove')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductRemove);
		waitforElement(180, PP.REMOVEPRODUCT, driver);
		click(PP.REMOVEPRODUCT);
		Thread.sleep(2000);

	}

	public void CopyConfigProducts(String Source_Company, String Destination_Company, String File_Name)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		PortalFunctions DownloadFiles = new PortalFunctions(driver);
		File tempfile = null;
		File tempfile2 = null;
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, "//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Source_Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		WebElement MappedProductsGREEN = driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div[contains(.,'Mappings')]/div/a[contains(.,'Mapped')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductsGREEN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.FILTERSELECT, driver);
		click(PP.DownloadProducts);
		Thread.sleep(10000);
		if (File_Name != "") {
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String dpath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Products";
				System.out.println("Path" + dpath);
				MoveLatestDownloadFileFromOnedirtoAnother(dpath);
				Thread.sleep(15000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("Products", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				tempfile = new File(LatestDownloadedFile("Products", false));
			}
			File directory = new File(".//");
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Products";
			if (File_Name != "") {
				FileName = File_Name;
			} else {
				if (FileName != null && FileName.length() > 0 && FileName.charAt(FileName.length() - 1) == ']') {
					FileName = FileName.substring(0, FileName.length() - 1);
				}
			}
			tempfile2 = new File(DirName + "\\" + FileName);
			Thread.sleep(5000);
			@SuppressWarnings("unchecked")
			ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
			@SuppressWarnings("unchecked")
			ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
			f1.removeAll(f2);
		}
		// click(PP.CopyProducts);
		JavaScriptclick(driver, PP.CopyProducts);
		waitforElement(60, PP.CopyDatalabel, driver);
		selectByValue(PP.DestinationCompany, Destination_Company);
		click(PP.Cancellnk);
		waitforElement(180, PP.FILTERSELECT, driver);
		click(PP.CopyProducts);
		waitforElement(60, PP.CopyDatalabel, driver);
		selectByValue(PP.DestinationCompany, Destination_Company);
		click(PP.CopyDataBtn);
		waitforText(60, PP.RequestProductCopyProcess, driver);
		if (VerifyObject(driver, PP.RequestProductCopyProcess)) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				Thread.sleep(1000);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, CNP.BEGINREMOVAL, driver);
				WebElement MappedProducts = driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
						+ "')]/../../../div/div[contains(.,'Mappings')]/div/a[contains(.,'Mapped')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProducts);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PP.FILTERSELECT, driver);
				if (!VerifyObject(driver, PP.RequestProductCopyProcess)) {
					report = true;
				}
			} while (report == false);
		}
		JavaScriptclick(driver, CNP.CONFIGURE);
		Thread.sleep(1000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		waitforElement(180,
				"//h2[contains(.,'" + Destination_Company + "')]/../../div/a[contains(text(),'Save Configuration')]",
				driver);
		WebElement Productscopyed = driver.findElement(By.xpath("//h2[contains(.,'" + Destination_Company
				+ "')]/../../../div/div[contains(.,'Mappings')]/h3/span/a[contains(.,'+ Add')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Productscopyed);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ViewAllProducts, driver);
		click(PP.UploadMulProducts);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ManageFiles, driver);
		click(PP.ManageFiles);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ManageOriginalFiles, driver);

	}

	public void CreateProductthroughQuicksearch(String TaxpayerName, String BuysellProduct, String ProductCode,
			String ProductType, String ProductName, String StartDate, String EndDate, String Quick_Search)
			throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + TaxpayerName
				+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + TaxpayerName
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + TaxpayerName
				+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + TaxpayerName
					+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.BUYSELLPRODT, driver);
		waitforElement(180, PP.GENERAL, driver);
		if (VerifyObject(driver, PP.ADDMAPPING)) {
			click(PP.ADDMAPPING);
			waitforElement(180, PP.ADD_A_NEW_SINGLPRODUCT, driver);
			JavaScriptclick(driver, PP.ADD_A_NEW_SINGLPRODUCT);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(30, PP.BUYSELLPRODT, driver);
		}
		selectByValue(PP.BUYSELLPRODT, BuysellProduct);
		Thread.sleep(2000);
		selectByValue(PP.PRODUCTTYPE, ProductType);
		Thread.sleep(2000);
		if (!ProductType.equalsIgnoreCase("Taxpayer")) {
			type(PP.PRODUCTCODE, ProductCode);
			Thread.sleep(3000);
			type(PP.PRODNAME, ProductName);
		}

		clear(PP.STARTDATE);
		type(PP.STARTDATE, StartDate);
		sendkeysTab(PP.STARTDATE);
		if (EndDate != "") {
			clear(PP.ENDDATE);
			type(PP.ENDDATE, EndDate);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, PP.PRODUCTS_QUICK_SEARCH);
		clear(PP.PRODUCTS_QUICK_SEARCH);
		type(PP.PRODUCTS_QUICK_SEARCH, Quick_Search);
		waitforElement(180, "//p[contains(.,'" + Quick_Search + "')]", driver);
		JavaScriptclick(driver, driver.findElement(
				By.xpath("//p[contains(.,'" + Quick_Search + "') and @class='category-name ng-binding']")));
		waitforElement(180, PP.SAVEANDCLOSE, driver);
		click(PP.SAVEANDCLOSE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
	}

	public void RemoveProduct(String Company, String Product_Code) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, CNP.COMPANYHEADER, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		String s[] = Company.split(" ");
		@SuppressWarnings("unused")
		String sval = s[0];
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);

		}
		scrollDown(driver);
		WebElement cname = driver.findElement(By.xpath("//h2[contains(.,'" + Company + "')]"));
		JavaScriptscrolltoView(driver, cname);
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ViewAllProducts, driver);
		JavaScriptclick(driver, PP.ViewAllProducts);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		// Searching the customer using Customer Code
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		clear(CE.SEARCH);
		type(CE.SEARCH, Product_Code);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(2000);
		JavaScriptclick(driver, PP.RMVEFIRSTPROD);
		waitforElement(180, PP.REMOVECANCEL, driver);
		Thread.sleep(2000);
		waitforElement(180, PP.REMOVESECONDPRODUCTPOPUP, driver);
		// type(PP.PRODENDDATE,Currentdate);
		JavaScriptclick(driver, PP.REMOVESECONDPRODUCTPOPUP);
		waitUntilElementInvisibe(180, PP.REMOVESECONDPRODUCTPOPUP_XPATH, driver);
		waitforElement(180, PP.READYTOSAVEEDITPRODS, driver);
		Thread.sleep(5000);
	}

	public void CopyConfigNoProductsSingleTaxpayer(String Source_Company) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// End is writing to break the loop if there is a error while uploading the
		// invalid product sample
		if (VerifyObject(driver, "//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Source_Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		WebElement NoProducts = driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", NoProducts);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ViewAllProducts, driver);
		click(PP.ViewAllProducts);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (!VerifyObject(driver, PP.DownloadProducts)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (!VerifyObject(driver, PP.CopyProducts)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}

	}

	public void EditFirstProductandRemoveSecondProduct(String Total_Products, String Company, String Submit,
			String Product_Type, String Product_Code) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, CNP.COMPANYHEADER, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (Total_Products.equalsIgnoreCase("Yes")) {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
				WebElement AddProducts = driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProducts);
			} else {
				if (VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
					WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + Company
							+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
				} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
					WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
							+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
				}
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.ViewAllProducts, driver);
			JavaScriptclick(driver, PP.ViewAllProducts);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.EDITFIRSTPROD, driver);
			JavaScriptclick(driver, PP.EDITFIRSTPROD);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.ENDDATE, driver);
			clear(PP.ENDDATE);
			String CurrentDate = xGetDate("MM/dd/yyyy");
			type(PP.ENDDATE, CurrentDate);
			Thread.sleep(2000);
			sendkeysTab(PP.ENDDATE);
		}

		if (Submit.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, PP.SAVEANDCLOSE);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			if (PP.READYTOSAVEEDITPRODS.isDisplayed()) {
				waitforElement(180, PP.READYTOSAVEEDITPRODS, driver);
				click(PP.READYTOSAVEEDITPRODS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, CNP.BEGINREMOVAL, driver);
			}
		}
		if (Total_Products.equalsIgnoreCase("Yes")) {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
				WebElement AddProducts = driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProducts);
			} else {
				if (VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
					WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + Company
							+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
				} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
					WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
							+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
				}
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			/*
			 * waitforElement(180, PP.ViewAllProducts, driver); click(PP.ViewAllProducts);
			 * waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			 */
			waitforElement(180, PP.REMOVESECONDPRODUCT, driver);
			if (VerifyObject(driver, PP.REMOVESECONDPRODUCT)) {
				JavaScriptclick(driver, PP.REMOVESECONDPRODUCT);
				waitforElement(180, PP.REMOVECANCEL, driver);
				JavaScriptclick(driver, PP.REMOVECANCEL);
				waitforElement(180, PP.REMOVESECONDPRODUCT, driver);
				JavaScriptclick(driver, PP.REMOVESECONDPRODUCT);
				Thread.sleep(2000);
				waitforElement(180, PP.REMOVESECONDPRODUCTPOPUP, driver);
				// type(PP.PRODENDDATE,Currentdate);
				JavaScriptclick(driver, PP.REMOVESECONDPRODUCTPOPUP);
				waitUntilElementInvisibe(180, PP.REMOVESECONDPRODUCTPOPUP_XPATH, driver);
				waitforElement(180, PP.READYTOSAVEEDITPRODS, driver);
				JavaScriptclick(driver, PP.READYTOSAVEEDITPRODS);
				waitUntilElementInvisibe(180, PP.READYTOSAVEEDITPRODS_XPATH, driver);
				Thread.sleep(5000);
				if (VerifyObject(driver, CNP.SAVECONFIGURATION)) {
					assertTrue(CNP.SAVECONFIGURATION.isDisplayed());
					JavaScriptclick(driver, CNP.SAVECONFIGURATION);
					waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
					click(CNP.COMPLETECONFIGPOPUP);
					waitUntilElementInvisibe(180, CNP.COMPLETECONFIGPOPUP_XPATH, driver);
					Thread.sleep(5000);
				}
				if (VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					boolean report = false;
					do {
						driver.navigate().refresh();
						waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
						Thread.sleep(10000);
						if (!VerifyObject(driver, "//h2[contains(.,'" + Company
								+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
							report = true;
						}
					} while (report == false);
				}

			}
		}
		if ((Product_Type.equalsIgnoreCase("Usage Code")) || Product_Type.equalsIgnoreCase("Usage Class")) {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
				WebElement AddProducts = driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProducts);
			} else {
				if (VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
					WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + Company
							+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
				} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
					WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
							+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
				}
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PP.UPLOADFILE, driver);
				waitforElement(180, PP.ADDNEWPRODUCT, driver);
				click(PP.ADDNEWPRODUCT);
				waitforElement(180, PP.ViewAllProducts, driver);
				click(PP.ViewAllProducts);
				waitforElement(180, "//table[@id='tblMappings']/tbody/tr", driver);
				assertTrue(VerifyObject(driver, "//table[@id='tblMappings']/tbody/tr/td[3][text()='" + Product_Code
						+ "']/../td[2][text()='" + Product_Type + "']"));
			}
		}
	}

	public void canadaQualifyingConditions(String Company, String State, String JurisdictionType,
			String LowerJurisdiction, String Product_Name, String impositionType) throws Exception {
		strProdName = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.BEGINREMOVAL)) {
			Companynamesplit = Company;
			CompnameSplit(driver, Companynamesplit, "");
			CompnameSplit(driver, Companynamesplit, "Mapped");
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.FILTERSELECT, driver);
			Thread.sleep(1000);
			selectByValue(PP.FILTERSELECT, "All");
			Thread.sleep(20000);
		}
		WebElement MappedProductEDIT = driver.findElement(By.xpath("//table[@id='tblMappings']/tbody/tr/td[contains(.,'"
				+ Product_Name + "')]/../td/a[2][contains(.,'Edit')]"));
		waitforElement(360, MappedProductEDIT, driver);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductEDIT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, PP.CategoryDesc, driver);
		waitforElement(40, PP.GENERAL, driver);
		waitforElement(30, PP.ADDQUALICOND, driver);
		JavaScriptclick(driver, PP.ADDQUALICOND);
		Thread.sleep(1000);
		waitforElement(180, PP.STATE, driver);
		selectByValue(PP.STATE, State);
		Thread.sleep(1000);
		if (!JurisdictionType.equalsIgnoreCase("State")) {
			selectByValue(PP.JURISDICTIONTYPE, JurisdictionType);
			Thread.sleep(1000);
			if (!LowerJurisdiction.isEmpty()) {
				selectByValue(PP.LOWERJURISDICTION, LowerJurisdiction);
			}
			Thread.sleep(1000);
		}

		if (!impositionType.isEmpty()) {
			click(PP.ImpositionType);
			Thread.sleep(1000);
			selectByValue(PP.QualcondImp, impositionType);
			sendkeysTab(PP.QualcondImp);
			Thread.sleep(10000);

		}
		click(PP.IMPOSITIONTYPE);
		if (VerifyObject(driver, PP.erroroccurredpopup)) {
			assertTrue(false);
		}
		WebElement c = driver.findElement(By.xpath("//div[2]/div/label/input"));
		waitforElement(180, c, driver);
		c.click();
		JavaScriptclick(driver, PP.SAVECLOSEBUTTON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.FILTERSELECT, driver);
	}

	public void UpdateEnddateofProduct(String CompanyName, String ProductName) throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + CompanyName
				+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ViewAllProducts, driver);
		JavaScriptclick(driver, PP.ViewAllProducts);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.EDITSECPROD, driver);
		JavaScriptclick(driver, PP.EDITSECPROD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, PP.PRODUCTS_QUICK_SEARCH);
		type(PP.PRODUCTS_QUICK_SEARCH, ProductName);
		driver.findElement(By.xpath("//a[@class='tt-suggestion type-ahead-hover ng-scope']")).click();
		Thread.sleep(5000);
		JavaScriptclick(driver, PP.SAVEANDCLOSE);
		assertTrue(PP.DUPLICATE.isDisplayed());
		assertTrue(driver
				.findElement(By
						.xpath("//p/strong[contains(.,'" + CompanyName + " is already mapped to Appointment Books.']"))
				.isDisplayed());
		JavaScriptclick(driver, PP.DUPLICATECATNAME);
		JavaScriptclick(driver, PP.ViewAllProducts);
	}

	// Exemptions
	public void TaxableCustomer(String taxpayerName, String customerType, String customerCode, String customerName,
			String customerstartDate, String customerendDate, String taxResult) throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		String[] custData, custnData;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);

		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + taxpayerName
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + taxpayerName
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + taxpayerName
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + taxpayerName
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (!VerifyObject(driver, CE.CUSTOMERTYPE)) {
			JavaScriptclick(driver, CE.ADDCUSTOMERLINK);
		}
		selectByValue(CE.CUSTOMERTYPE, customerType);
		custData = customerCode.split(";");
		CustomerCode = xLabelRND(custData);
		type(CE.CUSTOMERCODE, CustomerCode);
		custnData = customerName.split(";");
		CustomerName = xLabelRND(custnData);
		type(CE.CUSTOMERNAME, CustomerName);
		clear(CE.STARTDATE_CUST);
		type(CE.STARTDATE_CUST, customerstartDate);
		sendkeysTab(CE.STARTDATE_CUST);
		sendkeysTab(CE.ENDDATE_CUST);
		if ((customerstartDate != "")) {
			clear(CE.ENDDATE_CUST);
			type(CE.ENDDATE_CUST, customerendDate);
			sendkeysTab(CE.ENDDATE_CUST);
			sendkeysTab(CE.ENDDATE_CUST);
		}
		selectByValue(CE.TAXRESULTS, taxResult);
		JavaScriptclick(driver, CE.SAVEANDCLOSE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (VerifyObject(driver, PP.CONFIGLIMITPOPUP)) {
			waitforElement(180, PP.CONFIGLIMITPOPUP, driver);
			assertTrue(PP.CONFIGLIMITPOPUP.isDisplayed());
			JavaScriptclick(driver, PP.CONFIGLIMITOKBUTTON);
			waitforElement(180, CE.CANCELBUTTON, driver);
			JavaScriptclick(driver, CE.CANCELBUTTON);
		}
		Thread.sleep(5000);
	}

	public void UploadCertificate(String Company_Name, String Filename) throws Exception {
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String Filename1;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// waitforElement(180, CE.BEGINREMOVAL, driver);
		Thread.sleep(5000);
		Companynamesplit = Company_Name;
		CompnameSplit(driver, Companynamesplit, "AddException");
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		waitforElement(180, CE.UPLOADEXCEPTION, driver);
		JavaScriptclick(driver, CE.UPLOADEXCEPTION);
		Thread.sleep(5000);
		waitforElement(180, CE.BROWSE, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			click(CE.BROWSE);
		} else {
			JavaScriptclick(driver, CE.BROWSE);
		}
		Thread.sleep(4000);
		Path RSource = Paths.get(AdjustmentFilePath + "\\" + Filename + ".csv");
		Filename1 = Filename + RND();
		Path RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + ".csv");
		Files.copy(RSource, RTarget);
		Filename = Filename1;
		Thread.sleep(2000);
		fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, Filename, ".csv");
		Thread.sleep(5000);
		click(CE.EXCEPTIONUPLOAD);
		Thread.sleep(2000);
		waitforElement(180, CE.VIEWALLEXCEPTIONS, driver);
		JavaScriptclick(driver, CE.VIEWALLEXCEPTIONS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		JavaScriptclick(driver, CE.RETRURNTOCONFIGUREOVERVIEW);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		waitforElement(180, CE.BEGINREMOVAL, driver);
		driver.navigate().refresh();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.BEGINREMOVAL, driver);
	}

	public void RemoveAnItemExemption(String taxpayerName, String Customer_Code, String Cus_End_Date,
			String Cert_Remove, String customerRemove) throws Exception {
		strCustName = "";
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		// refreshing configuration to Green
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + taxpayerName
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + taxpayerName
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + taxpayerName
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + taxpayerName
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		waitforElement(180, CE.PAGINATION, driver);
		Thread.sleep(5000);
		click(CE.SearchByCodeRadioBtn);
		Thread.sleep(2000);
		click(CE.SEARCH);
		Thread.sleep(2000);
		JavaScripttype(driver, CE.SEARCH, Customer_Code);
		Thread.sleep(2000);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(2000);
		sendkeybackSpace(CE.SEARCH);
		clear(CE.SEARCH);
		JavaScripttype(driver, CE.SEARCH, Customer_Code);
		Thread.sleep(5000);
		waitforElement(180, "//td[contains(.,'" + Customer_Code + "')]/../td/div/a", driver);
		if (customerRemove.equalsIgnoreCase("YES")) {
			WebElement customerclick = driver
					.findElement(By.xpath("//td[contains(.,'" + Customer_Code + "')]/../td[9]/div/a"));
			JavaScriptscrolltoView(driver, customerclick);
			Thread.sleep(2000);
			JavaScriptclick(driver, customerclick);
			Thread.sleep(7000);
			driver.findElement(By.xpath("//tbody/tr[1]/td/div/ul/li/a[contains(.,'Remove')]")).click();
			Thread.sleep(5000);
			waitforElement(60, CE.REMOVEBTN, driver);
			click(CE.REMOVEBTN);
			Thread.sleep(1000);
		}
		if (Cus_End_Date.equalsIgnoreCase("YES")) {
			driver.findElement((By.xpath("//td[contains(.,'" + Customer_Code + "')]/../td/div/a"))).click();
			driver.findElement(
					By.xpath("//td[contains(.,'" + Customer_Code + "')]/../td/div/ul/li/a[contains(.,'Edit')]"))
					.click();
			waitforElement(60, CE.CUSTENDDATE, driver);
			type(CE.CUSTENDDATE, xGetTomorrowDate("MM/DD/YYYY"));
			waitforElement(60, CE.ADDEXCEPTION, driver);
			click(CE.ADDEXCEPTION);
			waitforElement(60, CE.RETRNCONFIGOVER, driver);
			Thread.sleep(5000);
		}
		// Removing Customer certificates with and without save
		if (Cert_Remove.equalsIgnoreCase("YES")) {
			waitforElement(180, "//td[contains(.,'" + Customer_Code + "')]/../td/div/a", driver);
			driver.findElement((By.xpath("//td[contains(.,'" + Customer_Code + "')]/../td/div/a"))).click();
			waitforElement(180, "//td[contains(.,'" + Customer_Code + "')]/../td/span/a[contains(.,'View')]", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + Customer_Code + "')]/../td/span/a[contains(.,'View')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CE.ACTIONSEDIT, driver);
			JavaScriptclick(driver, CE.ACTIONSEDIT);
			waitforElement(180, CE.CERTEDIT, driver);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(0,150)");
			Thread.sleep(2000);
			JavaScriptclick(driver, CE.CERTEDIT);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CE.EXCEPENDDATE, driver);
			// type(CE.EXCEPENDDATE, xGetCurrentDate("MM/DD/YYYY"));
			JavaScriptclick(driver, CE.SAVECERTIFICATE);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CE.CUSTOMERLISTING, driver);
			Thread.sleep(2000);
			JavaScriptclick(driver, CE.ACTIONSEDIT);
			waitforElement(180, CE.CERTREMOV, driver);
			Thread.sleep(5000);
			JavaScriptclick(driver, CE.CERTREMOV);
			// click(CE.CERT2REMOV);
			waitforElement(180, CE.REMOVEBTN, driver);
			click(CE.CERT2ENDDATE);
			clear(CE.CERT2ENDDATE);
			Thread.sleep(2000);
			type(CE.CERT2ENDDATE, xGetTomorrowDate("MM/dd/YYYY"));
			JavaScriptclick(driver, CE.REMOVEBTN);
			Thread.sleep(5000);
			// VerifyObject(driver, "//tr[2]/td[text()='"+Currentdate+"']");
			waitforElement(180, CE.CUSTOMERLISTING, driver);
			JavaScriptclick(driver, CE.CUSTOMERLISTING);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		}
		waitforElement(180, CE.RETRNCONFIGOVER, driver);
		JavaScriptclick(driver, CE.RETRNCONFIGOVER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
	}

	public void AddCertificate(String Company, String code, String Customer_Name, String Cert_Exception_StartDate,
			String Cert_Exception_EndDate, String Certificate_Scope, String Product_Code, String Document_Number,
			String Add_States, String Cert_Jurisdiction, String Cert_Reason, String Cert_Exception_No,
			String Addanother_State, String Cert_Upload_Image, String Imagename, String Cert_Exception_Type,
			String Taxability_Category) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		strCustName = "";
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String Uploadimg;
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(10000);
		if (VerifyObject(driver, "//button[@class='close drawer__btn-close']")) {
			WebElement closeaddcustomer = driver.findElement(By.xpath("//button[@class='close drawer__btn-close']"));
			JavaScriptclick(driver, closeaddcustomer);
			Thread.sleep(2000);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CE.SearchBynameRadioBtn);
		click(CE.SEARCH);
		type(CE.SEARCH, Customer_Name);
		Thread.sleep(2000);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(3000);
		WebElement customerclick = driver
				.findElement(By.xpath("//td[contains(.,'" + Customer_Name + "')]/../td[9]/div/a"));
		JavaScriptscrolltoView(driver, customerclick);
		Thread.sleep(2000);
		JavaScriptclick(driver, customerclick);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'View')]",
				driver);
		driver.findElement(By.xpath("//td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'(View)')]"))
				.click();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, CE.ADDCERTIFICATE, driver);
		JavaScriptclick(driver, CE.ADDCERTIFICATE);
		waitforElement(180, CE.CUSTOMERINFOLINK, driver);
		if (!VerifyObject(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN)) {
			JavaScriptclick(driver, CE.CUSTOMERINFOLINK);
		}
		waitforElement(180, CE.SEARCHCUSTOMERADDCERTFSCREEN, driver);
		click(CE.SEARCHCUSTOMERADDCERTFSCREEN);
		type(CE.SEARCHCUSTOMERADDCERTFSCREEN, Customer_Name);
		Thread.sleep(3000);
		waitforElement(180, "//a/span[contains(.,'" + Customer_Name + "')]", driver);
		Thread.sleep(2000);
		WebElement customer = driver.findElement(By.xpath("//a/span[contains(.,'" + Customer_Name + "')]"));
		JavaScriptclick(driver, customer);
		waitforElement(180, CE.CertificateInfoLink, driver);
		JavaScriptclick(driver, CE.CertificateInfoLink);
		waitforElement(180, CE.EXCEPTYPE, driver);

		if (Cert_Exception_Type != "") {
			selectByValue(CE.EXCEPTYPE, Cert_Exception_Type);
			if (Cert_Exception_Type.equalsIgnoreCase("Special Condition")) {
				click(CE.SPTaxabilityCategory);
				type(CE.SPTaxabilityCategory, Taxability_Category);
				Thread.sleep(3000);
				CE.SPTaxabilityCategory.sendKeys(Keys.BACK_SPACE);
				Thread.sleep(2000);
				CE.SPTaxabilityCategory.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				// driver.findElement(By.xpath("//p[contains(.,'" + Taxability_Category +
				// "')]")).click();
			}
		}

		if (Cert_Exception_StartDate != "") {
			scrollDown(driver);
			click(CE.EXCEPSTRATDATE);
			waitforElement(180, CE.EXCEPSTRATDATE, driver);
			clear(CE.EXCEPSTRATDATE);
			type(CE.EXCEPSTRATDATE, Cert_Exception_StartDate);
			click(CE.EXCEPSTRATDATE);
			sendkeysTab(CE.EXCEPSTRATDATE);
			sendkeysTab(CE.EXCEPENDDATE);
		}
		if (Cert_Exception_EndDate != "") {
			clear(CE.EXCEPENDDATE);
			type(CE.EXCEPENDDATE, Cert_Exception_EndDate);
			sendkeysTab(CE.EXCEPENDDATE);
		}
		// Adding the Certificate scope
		if (Certificate_Scope != "") {
			String Cerscope = Certificate_Scope;
			switch (Cerscope) {
			case "Limited":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				String ProdVal = Product_Code;
				waitforElement(180, CE.ProductCodeVal, driver);
				click(CE.ProductCodeVal);
				type(CE.ProductCodeVal, ProdVal);
				Thread.sleep(5000);
				WebElement selectprod = driver
						.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[contains(.,'" + ProdVal + "')]"));
				click(selectprod);
				break;
			case "Single Use":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				Thread.sleep(2000);
				String DocumentValue = Document_Number;
				waitforElement(180, CE.InvoiceNumber, driver);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptclick(driver, CE.InvoiceNumber);
				} else {
					click(CE.InvoiceNumber);
				}
				type(CE.InvoiceNumber, DocumentValue);
				sendkeysTab(CE.InvoiceNumber);
				break;
			case "Blanket":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				Thread.sleep(2000);
				break;
			}
		}
		if (Add_States.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, CE.JURISDICTIONLINK);
			waitforElement(180, CE.ADDJURISDICTION, driver);
			JavaScriptclick(driver, CE.ADDJURISDICTION);
			waitforElement(180, CE.BackToAddCertificate, driver);
			Select stateSelectbox = new Select(
					driver.findElement(By.xpath("//select[@id='ddlExceptionJurisdiction']")));
			stateSelectbox.selectByVisibleText(Cert_Jurisdiction);
			Thread.sleep(5000);
			Select ExceptionReason = new Select(driver.findElement(By.xpath("//select[@id='ddlExceptionReason']")));
			ExceptionReason.selectByVisibleText(Cert_Reason);
			driver.findElement(By.xpath("//select[@id='ddlExceptionReason']")).sendKeys(Keys.TAB);
			if (Cert_Exception_No != "") {
				clear(CE.EXCEPNUM);
				String CerNumber = Cert_Exception_No;
				type(CE.EXCEPNUM, CerNumber);
				sendkeysTab(CE.EXCEPNUM);
				if (Addanother_State != "") {
					Select Anotherstatebox = new Select(driver
							.findElement(By.xpath("//div/ng-form/div/div/div/select[@id='certificate-state-select']")));
					Anotherstatebox.selectByVisibleText(Addanother_State);
					(driver.findElement(By.xpath("//select[@id='cert-reason1']"))).getText();
					assertTrue(new Select(driver.findElement(By.xpath("//select[@id='cert-reason1']")))
							.getFirstSelectedOption().getText().equalsIgnoreCase("None"));
					JavaScriptclick(driver, CE.SAVEJURISDICTION);
				} else {
					JavaScriptclick(driver, CE.SAVEJURISDICTION);
				}
			}

			if (Cert_Upload_Image.equalsIgnoreCase("Yes")) {
				Thread.sleep(5000);
				// JavaScriptclick(driver,CE.CertificateImageLink);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", CE.CertificateImageLink);
				Uploadimg = Imagename;
				if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
					JavaScriptclick(driver, CE.CERTIFICATEIMAGEBROWSE);
				} else {
					JavaScriptclick(driver, CE.CERTIFICATEIMAGEBROWSE);
				}
				Thread.sleep(5000); // to wait for the file system window attached browser (AutoIt)
				fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Uploadimg, "");
				Thread.sleep(5000);
			}
			if (VerifyObject(driver, CE.ADDEXCEPTION)) {
				JavaScriptclick(driver, CE.ADDEXCEPTION);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			}
			Thread.sleep(2000); // after progress busy complete wait for visibility of objects in the page

			if (VerifyObject(driver, CE.SAVECERTIFICATE)) {
				JavaScriptclick(driver, CE.SAVECERTIFICATE);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			}

			if (VerifyObject(driver, CE.EXPANDADDCERT)) {
				JavaScriptclick(driver, CE.EXPANDADDCERT);
			}
			if (VerifyObject(driver, CE.NEWCERTIBUTTON)) {
				JavaScriptclick(driver, CE.NEWCERTIBUTTON);
			}
		}
		if (VerifyObject(driver, CE.SAVECERTIFICATE)) {
			JavaScriptclick(driver, CE.SAVECERTIFICATE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.RETURNTOCERTIFICATELIST)) {
			JavaScriptclick(driver, CE.RETURNTOCERTIFICATELIST);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, CE.ADDEXCEPTION)) {
			JavaScriptclick(driver, CE.ADDEXCEPTION);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.CUSTOMERLISTING)) {
			JavaScriptclick(driver, CE.CUSTOMERLISTING);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.VIEWALLEXCEPTIONS)) {
			JavaScriptclick(driver, CE.VIEWALLEXCEPTIONS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, CE.READYTOSAVE, driver);
		}
	}

	public void AddCertificatewithLowerlevel(String Company, String code, String Customer_Name,
			String Cert_Exception_StartDate, String Cert_Exception_EndDate, String Certificate_Scope,
			String Product_Code, String Document_Number, String Add_States, String Cert_Jurisdiction,
			String Cert_Reason, String Cert_Exception_No, String Addanother_State, String Cert_Upload_Image,
			String Imagename, String State_Shortform, String Jurisdiction_Type, String Issuing_Jurisdiction,
			String ChangeException_State, String IssueDate, String ReviewDate, String ExpirationDate) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		strCustName = "";

		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String Uploadimg;
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		Thread.sleep(3000);
		driver.navigate().refresh();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//button[@class='close drawer__btn-close']")) {
			WebElement closeaddcustomer = driver.findElement(By.xpath("//button[@class='close drawer__btn-close']"));
			JavaScriptclick(driver, closeaddcustomer);
			Thread.sleep(2000);
		}
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'View')]",
				driver);
		driver.findElement(By.xpath("//td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'(View)')]"))
				.click();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, CE.ADDCERTIFICATE, driver);
		JavaScriptclick(driver, CE.ADDCERTIFICATE);
		waitforElement(180, CE.CUSTOMERINFOLINK, driver);
		if (!VerifyObject(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN)) {
			JavaScriptclick(driver, CE.CUSTOMERINFOLINK);
		}
		waitforElement(180, CE.SEARCHCUSTOMERADDCERTFSCREEN, driver);
		JavaScriptclick(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN);
		type(CE.SEARCHCUSTOMERADDCERTFSCREEN, Customer_Name);
		waitforElement(180, "//a/span[contains(.,'" + Customer_Name + "')]", driver);
		Thread.sleep(2000);
		WebElement customer = driver.findElement(By.xpath("//a/span[contains(.,'" + Customer_Name + "')]"));
		JavaScriptclick(driver, customer);
		waitforElement(180, CE.CertificateInfoLink, driver);
		JavaScriptclick(driver, CE.CertificateInfoLink);
		waitforElement(180, CE.EXCEPTYPE, driver);
		/*
		 * if (Cert_Exception_Type != "") { selectByValue(CE.EXCEPTYPE,
		 * Cert_Exception_Type); }
		 */
		if (Cert_Exception_StartDate != "") {
			waitforElement(180, CE.EXCEPSTRATDATE, driver);
			clear(CE.EXCEPSTRATDATE);
			type(CE.EXCEPSTRATDATE, Cert_Exception_StartDate);
			click(CE.EXCEPSTRATDATE);
			sendkeysTab(CE.EXCEPSTRATDATE);
			sendkeysTab(CE.EXCEPENDDATE);
		}
		if (Cert_Exception_EndDate != "") {
			clear(CE.EXCEPENDDATE);
			type(CE.EXCEPENDDATE, Cert_Exception_EndDate);
			sendkeysTab(CE.EXCEPENDDATE);
		}
		// Adding the Certificate scope
		if (Certificate_Scope != "") {
			String Cerscope = Certificate_Scope;
			switch (Cerscope) {
			case "Limited":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				String ProdVal = Product_Code;
				waitforElement(180, CE.ProductCodeVal, driver);
				click(CE.ProductCodeVal);
				type(CE.ProductCodeVal, ProdVal);
				Thread.sleep(5000);
				WebElement selectprod = driver
						.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[contains(.,'" + ProdVal + "')]"));
				click(selectprod);
				break;
			case "Single Use":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				Thread.sleep(2000);
				String DocumentValue = Document_Number;
				waitforElement(180, CE.InvoiceNumber, driver);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptclick(driver, CE.InvoiceNumber);
				} else {
					click(CE.InvoiceNumber);
				}
				type(CE.InvoiceNumber, DocumentValue);
				sendkeysTab(CE.InvoiceNumber);
				break;
			case "Blanket":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				Thread.sleep(2000);
				break;
			}
		}
		if (Add_States.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, CE.JURISDICTIONLINK);
			waitforElement(180, CE.ADDJURISDICTION, driver);
			JavaScriptclick(driver, CE.ADDJURISDICTION);
			waitforElement(180, CE.BackToAddCertificate, driver);
			Select stateSelectbox = new Select(
					driver.findElement(By.xpath("//select[@id='ddlExceptionJurisdiction']")));
			stateSelectbox.selectByVisibleText(Cert_Jurisdiction);
			if (State_Shortform.equalsIgnoreCase("AK") || State_Shortform.equalsIgnoreCase("AZ")
					|| State_Shortform.equalsIgnoreCase("CO") || State_Shortform.equalsIgnoreCase("LA")
					|| State_Shortform.equalsIgnoreCase("SC")) {
				Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
				Jurisdtype.selectByVisibleText(Jurisdiction_Type);
				if (!(Jurisdiction_Type.equalsIgnoreCase("State"))) {
					Thread.sleep(8000);
					Select IssuingJuris = new Select(driver.findElement(By.xpath(CE.ISSUEJURSDTYPE)));
					assertTrue(Issuing_Jurisdiction != "");
					IssuingJuris.selectByVisibleText(Issuing_Jurisdiction);
					click(CE.ExceptionType);
					selectByValue(CE.ExceptionType, ChangeException_State);
					Thread.sleep(3000);
				}
			}

			Select ExceptionReason = new Select(driver.findElement(By.xpath("//select[@id='ddlExceptionReason']")));
			ExceptionReason.selectByVisibleText(Cert_Reason);
			driver.findElement(By.xpath("//select[@id='ddlExceptionReason']")).sendKeys(Keys.TAB);
			if (Cert_Exception_No != "") {
				clear(CE.EXCEPNUM);
				String CerNumber = Cert_Exception_No;
				type(CE.EXCEPNUM, CerNumber);
				sendkeysTab(CE.EXCEPNUM);
				if (IssueDate != "") {
					assertTrue(CE.ISSUEDATE.isDisplayed());
					clear(CE.ISSUEDATE);
					click(CE.ISSUEDATE);
					type(CE.ISSUEDATE, IssueDate);
					sendkeysTab(CE.ISSUEDATE);
				}
				if (ReviewDate != "") {
					assertTrue(CE.REVIEWDATE.isDisplayed());
					clear(CE.REVIEWDATE);
					click(CE.REVIEWDATE);
					type(CE.REVIEWDATE, ReviewDate);
					sendkeysTab(CE.REVIEWDATE);
				}
				if (ExpirationDate != "") {
					assertTrue(CE.EXPIRATIONDATE.isDisplayed());
					clear(CE.EXPIRATIONDATE);
					click(CE.EXPIRATIONDATE);
					type(CE.EXPIRATIONDATE, ExpirationDate);
					sendkeysTab(CE.EXPIRATIONDATE);
				}
				if (Addanother_State != "") {
					Select Anotherstatebox = new Select(driver
							.findElement(By.xpath("//div/ng-form/div/div/div/select[@id='certificate-state-select']")));
					Anotherstatebox.selectByVisibleText(Addanother_State);
					(driver.findElement(By.xpath("//select[@id='cert-reason1']"))).getText();
					assertTrue(new Select(driver.findElement(By.xpath("//select[@id='cert-reason1']")))
							.getFirstSelectedOption().getText().equalsIgnoreCase("None"));
					JavaScriptclick(driver, CE.SAVEJURISDICTION);
				} else {
					JavaScriptclick(driver, CE.SAVEJURISDICTION);
				}
			}
			/*
			 * if (!(Jurisdiction_Type.equalsIgnoreCase("State"))) { Thread.sleep(2000);
			 * driver.findElement(By.xpath("//tr/td[5]/div/a[@class='dropdown-toggle']")).
			 * click(); waitforElement(180,
			 * "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]"
			 * , driver); driver.findElement(By.xpath(
			 * "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]"
			 * )) .click(); Thread.sleep(2000); JavaScriptclick(driver,
			 * CE.SAVEJURISDICTION); }
			 */

			if (Cert_Upload_Image.equalsIgnoreCase("Yes")) {
				Thread.sleep(5000);
				// JavaScriptclick(driver,CE.CertificateImageLink);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", CE.CertificateImageLink);
				Uploadimg = Imagename;
				if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
					JavaScriptclick(driver, CE.CERTIFICATEIMAGEBROWSE);
				} else {
					JavaScriptclick(driver, CE.CERTIFICATEIMAGEBROWSE);
				}
				Thread.sleep(5000); // to wait for the file system window attched browser (AutoIt)
				switch (browser) {
				case "IE":
					Runtime.getRuntime()
							.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
					break;
				case "Firefox":
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
					break;
				case "Chrome":
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
					break;
				case "Safari":
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
					break;
				}
				Thread.sleep(5000);
			}
			if (VerifyObject(driver, CE.ADDEXCEPTION)) {
				JavaScriptclick(driver, CE.ADDEXCEPTION);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			}
			Thread.sleep(2000); // after progress busy complete wait for visibility of objects in the page

			if (VerifyObject(driver, CE.SAVECERTIFICATE)) {
				JavaScriptclick(driver, CE.SAVECERTIFICATE);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			}

			if (VerifyObject(driver, CE.EXPANDADDCERT)) {
				JavaScriptclick(driver, CE.EXPANDADDCERT);
			}
			if (VerifyObject(driver, CE.NEWCERTIBUTTON)) {
				JavaScriptclick(driver, CE.NEWCERTIBUTTON);
			}
		}
		if (VerifyObject(driver, CE.SAVECERTIFICATE)) {
			JavaScriptclick(driver, CE.SAVECERTIFICATE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.RETURNTOCERTIFICATELIST)) {
			JavaScriptclick(driver, CE.RETURNTOCERTIFICATELIST);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, CE.ADDEXCEPTION)) {
			JavaScriptclick(driver, CE.ADDEXCEPTION);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.CUSTOMERLISTING)) {
			JavaScriptclick(driver, CE.CUSTOMERLISTING);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.VIEWALLEXCEPTIONS)) {
			JavaScriptclick(driver, CE.VIEWALLEXCEPTIONS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, CE.READYTOSAVE, driver);
		}
	}

	public void ExemptCustomer(String Company, String Upload_Image, String Exempt_CertInfo, String Tax_Result,
			String Customer_Type, String Customer_Code, String Customer_Name, String Cus_Start_Date,
			String Cus_End_Date) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String Uploadimg;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (!VerifyObject(driver, CE.CUSTOMERTYPE)) {
			JavaScriptclick(driver, CE.ADDCUSTOMERLINK);
		}
		selectByValue(CE.CUSTOMERTYPE, Customer_Type);
		type(CE.CUSTOMERCODE, Customer_Code);
		type(CE.CUSTOMERNAME, Customer_Name);
		clear(CE.STARTDATE_CUST);
		type(CE.STARTDATE_CUST, Cus_Start_Date);
		sendkeysTab(CE.STARTDATE_CUST);
		sendkeysTab(CE.ENDDATE_CUST);
		if (Cus_End_Date != "") {
			clear(CE.ENDDATE_CUST);
			type(CE.ENDDATE_CUST, Cus_End_Date);
			sendkeysTab(CE.ENDDATE_CUST);
			sendkeysTab(CE.ENDDATE_CUST);
		}
		selectByValue(CE.TAXRESULTS, Tax_Result);

		if (Exempt_CertInfo.equalsIgnoreCase("Resale")) {
			JavaScriptclick(driver, CE.RESALE);
		} else if (Exempt_CertInfo.equalsIgnoreCase("Government")) {
			JavaScriptclick(driver, CE.GOVERNMENT);
		} else if (Exempt_CertInfo.equalsIgnoreCase("Exempt Organization")) {
			JavaScriptclick(driver, CE.EXEMPT);
		}

		if (Upload_Image != "") {
			Uploadimg = Upload_Image;
			if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
				click(CE.CERTIFICATEIMAGEBROWSE);
			} else {
				JavaScriptclick(driver, CE.CERTIFICATEIMAGEBROWSE);
			}
			Thread.sleep(5000); // to wait for the file system window attached browser (AutoIt)
			switch (browser) {
			case "IE":
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Uploadimg);
				break;
			case "Firefox":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Uploadimg);
				break;
			case "Chrome":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Uploadimg);
				break;
			case "Safari":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Uploadimg);
				break;
			}
			Thread.sleep(5000);
		}
		if (Customer_Name.equalsIgnoreCase("VATCustomer")) {
			VatRegForCustomer("Croatia", "testcode", "Yes", "United Kingdom", "");
			VatRegForCustomer("Sweden", "5270527@$test", "Yes", "", "Yes");

		}
		JavaScriptclick(driver, CE.SAVEANDCLOSE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);

		if (VerifyObject(driver, PP.READYTOSAVE)) {
			JavaScriptclick(driver, PP.READYTOSAVE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
	}

	public void CertificateUploadforCustomers(String Company, String Customer_Name, String Cert_Upload_Image,
			String CustomerType) throws Exception {
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String Filename, cname;
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(10000);
		WebElement Certificate;
		String comname = Company;
		if (VerifyObject(driver,
				"//h2[contains(.,'" + comname + "')]/../../../div/div/div/a[contains(.,'Customers')]")) {
			Certificate = driver.findElement(
					By.xpath("//h2[contains(.,'" + comname + "')]/../../../div/div/div/a[contains(.,'Customers')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Certificate);
		} else {
			if (VerifyObject(driver,
					"//h2[contains(.,'" + comname + "')]/../../../../div/div/div/div/a[contains(.,'Customers')]")) {
				Certificate = driver.findElement(By.xpath(
						"//h2[contains(.,'" + comname + "')]/../../../../div/div/div/div/a[contains(.,'Customers')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Certificate);
			}
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		cname = Customer_Name;
		Filename = Cert_Upload_Image;
		switch (CustomerType) {
		case "Exempt":
			driver.findElement(By.xpath("//td[contains(.,'" + cname + "')]/../td/div/a")).click();
			waitforElement(30, "//td[contains(.,'" + cname + "')]/../td/div/ul/li/a[contains(.,'Upload Image')]",
					driver);
			driver.findElement(
					By.xpath("//td[contains(.,'" + cname + "')]/../td/div/ul/li/a[contains(.,'Upload Image')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(4000);
			JavaScriptscrolltoView(driver, CE.BROWSEBTN);
			Thread.sleep(3000);
			if (Browser.equalsIgnoreCase("chrome")) {
				click(CE.BROWSEBTN);
			} else {
				JavaScriptclick(driver, CE.BROWSEBTN);
			}

			Thread.sleep(5000);
			if (Cert_Upload_Image.contains("jpg")) {
				fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, ".jpg");
			} else {
				fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, ".pdf");
			}
			Thread.sleep(5000);
			driver.findElement(By.xpath(CE.SAVEANDCLOSE_XPATH)).click();
			Thread.sleep(3000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[contains(.,'" + cname + "')]/../td/div/a")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//td[contains(.,//td[contains(.,'" + cname + "')]/../td/div/ul/li/a[contains(.,'View Image')]"))
					.click();
			assertTrue(driver
					.findElement(
							By.xpath("//td[contains(.,'" + cname + "')]/../td/div/ul/li/a[contains(.,'View Image')]"))
					.isDisplayed());

			Thread.sleep(5000);
			break;
		case "Taxable":
			WebElement view = driver
					.findElement(By.xpath("//td[contains(.,'" + cname + "')]/../td/span/a[contains(.,'View')]"));
			JavaScriptclick(driver, view);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(3000);
			waitforElement(180, "//td[contains(.,'" + cname + "')]/../td/div/a", driver);
			/*
			 * WebElement actionslink = driver.findElement(By.xpath("//td[contains(.,'" +
			 * cname + "')]/../td/div/a")); JavaScriptclick(driver, actionslink);
			 */
			driver.findElement(By.xpath("//td[contains(.,'" + cname + "')]/../td/div/a")).click();
			scrollDown(driver);
			waitforElement(180, "//td[contains(.,'" + cname + "')]/../td/div/ul/li/a[contains(.,'Upload Image')]",
					driver);
			WebElement uploadimage = driver.findElement(
					By.xpath("//td[contains(.,'" + cname + "')]/../td/div/ul/li/a[contains(.,'Upload Image')]"));
			JavaScriptclick(driver, uploadimage);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			JavaScriptscrolltoView(driver, CE.BROWSEBTNTaxable);
			Thread.sleep(4000);
			if (Browser.equalsIgnoreCase("chrome")) {
				click(CE.BROWSEBTNTaxable);
			} else {
				JavaScriptclick(driver, CE.BROWSEBTNTaxable);
			}
			Thread.sleep(5000);
			fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, "");
			Thread.sleep(5000);
			click(CE.SAVECERTIFICATE);
			Thread.sleep(3000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[contains(.,'" + cname + "')]/../td/div/a")).click();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//td[contains(.,'" + cname + "')]/../td/div/ul/li/a[contains(.,'View Image')]")).click();
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//td[contains(.,'" + cname + "')]/../td/div/ul/li/a[contains(.,'Delete Image')]"))
					.click();
			assertTrue(driver
					.findElement(
							By.xpath("//td[contains(.,'" + cname + "')]/../td/div/ul/li/a[contains(.,'View Image')]"))
					.isDisplayed());

			Thread.sleep(6000);
			JavaScriptclick(driver, CE.CUSTOMERLISTING);
			waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
			break;
		}

		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(10000);
		if (!VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/./../../div/a[contains(text(),'Save Configuration')]")) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void Taxable_Customer(String Company, String customerType, String customerCode, String customerName,
			String customerstartDate, String customerendDate, String taxResult, String Cerscope, String ProdVal,
			String TaxabilityCategory, String ExceptionState, String ExceptionReason) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		String[] custData, custnData;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (!VerifyObject(driver, CE.CUSTOMERTYPE)) {
			JavaScriptclick(driver, CE.ADDCUSTOMERLINK);
		}
		selectByValue(CE.CUSTOMERTYPE, customerType);
		custData = customerCode.split(";");
		CustomerCode = xLabelRND(custData);
		type(CE.CUSTOMERCODE, CustomerCode);
		custnData = customerName.split(";");
		CustomerName = xLabelRND(custnData);
		type(CE.CUSTOMERNAME, CustomerName);
		clear(CE.STARTDATE_CUST);
		type(CE.STARTDATE_CUST, customerstartDate);
		sendkeysTab(CE.STARTDATE_CUST);
		sendkeysTab(CE.ENDDATE_CUST);
		if ((customerendDate != "")) {
			clear(CE.ENDDATE_CUST);
			type(CE.ENDDATE_CUST, customerendDate);
			sendkeysTab(CE.ENDDATE_CUST);
		}
		selectByValue(CE.TAXRESULTS, taxResult);
		JavaScriptclick(driver, CE.SAVEANDADDCERTFICATE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, CE.VERIFYPLUSICONCERTIFICATEINFO)) {
			JavaScriptclick(driver, CE.VERIFYPLUSICONCERTIFICATEINFO);
		}
		Thread.sleep(5000);
		selectByValue(CE.CertificateScopeValue, Cerscope);
		Thread.sleep(5000);
		if (Cerscope.equalsIgnoreCase("Limited")) {
			click(CE.ProductCodeVal);
			type(CE.ProductCodeVal, ProdVal);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[contains(.,'" + ProdVal + "')]")).click();
		}
		if (Cerscope.equalsIgnoreCase("Single Use")) {
			click(CE.InvoiceNumber);
			type(CE.InvoiceNumber, "14523698");
			sendkeysTab(CE.InvoiceNumber);
		}
		Thread.sleep(5000);
		selectByValue(CE.EXCEPTYPE, "Special Condition");
		click(CE.SPTaxabilityCategory);
		type(CE.SPTaxabilityCategory, TaxabilityCategory);
		Thread.sleep(3000);
		CE.SPTaxabilityCategory.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(2000);
		CE.SPTaxabilityCategory.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		if (VerifyObject(driver, CE.VERIFYPLUSICONJURISINFO)) {
			click(CE.VERIFYPLUSICONJURISINFO);
		}
		click(CE.ADDJURISDICTION);
		waitforElement(180, CE.ExceptionType, driver);
		click(CE.ExceptionType);
		selectByValue(CE.ExceptionType, ExceptionState);
		Thread.sleep(3000);
		click(CE.ExceptionReason);
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			type(CE.ExceptionReason, ExceptionReason);
		} else {
			selectByValue(CE.ExceptionReason, ExceptionReason);
		}
		sendkeysTab(CE.ExceptionReason);
		click(CE.EXCEPNUM);
		clear(CE.EXCEPNUM);
		type(CE.EXCEPNUM, "H2705#2705");
		Thread.sleep(2000);
		if (VerifyObject(driver, CE.SAVEJURISDICTION)) {
			click(CE.SAVEJURISDICTION);
		}
		Thread.sleep(2000);
		click(CE.SAVECERTIFICATE);
		Thread.sleep(7000);
		if (VerifyObject(driver, PP.READYTOSAVE)) {
			JavaScriptclick(driver, PP.READYTOSAVE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
	}

	public void EditExemptCustomer(String Company, String CustomerCode, String newCustomerCode, String Upload_Image)
			throws Exception {
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String Uploadimg;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		PageFactory.initElements(driver, ProductsPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		Thread.sleep(2000);
		Companynamesplit = Company;
		CompnameSplit(driver, Companynamesplit, "AddException");
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		// Searching the customer using Customer Code
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, CustomerCode);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='dropdown']/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//td[contains(.,'" + CustomerCode + "')]/../td/div/ul/li/a[contains(.,'Edit')]"))
				.click();
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(10000);
		waitforElement(180, CE.CLOSEICON, driver);
		waitforElement(90, CE.ADDCERTIFICATEBUTTON, driver);
		click(CE.CUSTOMERCODE);
		Thread.sleep(5000);
		clear(CE.CUSTOMERCODE);
		type(CE.CUSTOMERCODE, newCustomerCode);
		if (Upload_Image != "") {
			Uploadimg = Upload_Image;
			if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
				click(CE.CERTIFICATEIMAGEBROWSE);
			} else {
				JavaScriptclick(driver, CE.CERTIFICATEIMAGEBROWSE);
			}
			Thread.sleep(5000); // to wait for the file system window attached browser (AutoIt)
			switch (browser) {
			case "IE":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
				break;
			case "Firefox":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
				break;
			case "Chrome":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
				break;
			case "Safari":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
				break;
			}
			Thread.sleep(5000);
		}
		Thread.sleep(2000);
		click(CE.SAVEANDCLOSE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
	}

	public void DeleteExemptCustomer(String Company, String CustomerCode) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		Thread.sleep(2000);
		Companynamesplit = Company;
		CompnameSplit(driver, Companynamesplit, "AddException");
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		// Searching the customer using Customer Code
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, CustomerCode);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='dropdown']/a")).click();
		Thread.sleep(3000);
		scrollDown(driver);
		driver.findElement(By.xpath("//td[contains(.,'" + CustomerCode + "')]/../td/div/ul/li/a[contains(.,'Remove')]"))
				.click();
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		click(CE.REMOVEITEM);
	}

	public void UploadCertificate(String Filename) throws Exception {
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String Filename1;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.BEGINREMOVAL, driver);
		Thread.sleep(2000);
		CompnameSplit(driver, Companynamesplit, "AddException");
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (VerifyObject(driver, CE.CANCELBUTTON)) {
			JavaScriptclick(driver, CE.CANCELBUTTON);
		}
		waitforElement(180, CE.UPLOADEXCEPTION, driver);
		JavaScriptclick(driver, CE.UPLOADEXCEPTION);
		Thread.sleep(5000);
		if (VerifyObject(driver, CE.CANCELVAT)) {
			JavaScriptclick(driver, CE.CANCELVAT);
		}
		waitforElement(180, CE.UPLOADEXCEPTION, driver);
		Thread.sleep(5000);
		waitforElement(180, CE.BROWSE, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			click(CE.BROWSE);
		} else {
			JavaScriptclick(driver, CE.BROWSE);
		}
		Thread.sleep(4000);
		Path RSource = Paths.get(AdjustmentFilePath + "\\" + Filename + ".csv");
		Filename1 = Filename + RND();
		Path RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + ".csv");
		Files.copy(RSource, RTarget);
		Filename = Filename1;
		Thread.sleep(2000);
		fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, Filename, ".csv");
		Thread.sleep(5000);
		click(CE.EXCEPTIONUPLOAD);
		Thread.sleep(2000);
		waitforElement(180, CE.VIEWALLEXCEPTIONS, driver);
		JavaScriptclick(driver, CE.VIEWALLEXCEPTIONS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		JavaScriptclick(driver, CE.RETRURNTOCONFIGUREOVERVIEW);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		waitforElement(180, CE.BEGINREMOVAL, driver);
		driver.navigate().refresh();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.BEGINREMOVAL, driver);
	}

	public void DeleteImage(String Company, String Customer_type, String CustomerCode) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		Thread.sleep(2000);
		Companynamesplit = Company;
		CompnameSplit(driver, Companynamesplit, "AddException");
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		// Searching the customer using Customer Code
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, CustomerCode);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(2000);
		if (Customer_type.equalsIgnoreCase("Taxable")) {
			driver.findElement(By.xpath("//td[contains(.,'" + CustomerCode + "')]/../td/span/a[contains(.,'View')]"))
					.click();
			waitforElement(180, "//tr[1]/td[12]", driver);
			driver.findElement(By.xpath("//tr[1]/td[12]")).click();
			waitforElement(180, "//a[contains(.,'Delete Image')]", driver);
			WebElement delete = driver.findElement(By.xpath("//a[contains(.,'Delete Image')]"));
			JavaScriptclick(driver, delete);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[1]/td[12]")).click();
			Thread.sleep(2000);
			WebElement uploadimage = driver.findElement(By.xpath("//a[contains(.,'Upload Image')]"));
			assertTrue(uploadimage.isDisplayed());
		}
		if (Customer_type.equalsIgnoreCase("Exempt")) {
			driver.findElement(By.xpath("//div[@class='dropdown']/a")).click();
			Thread.sleep(3000);
			driver.findElement(
					By.xpath("//td[contains(.,'" + CustomerCode + "')]/../td/div/ul/li/a[contains(.,'Remove')]"))
					.click();
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
			click(CE.REMOVEITEM);
		}
	}

	public void AddImage(String Company, String Customer_type, String CustomerCode, String CustomerName,
			String Filename) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		Thread.sleep(2000);
		Companynamesplit = Company;
		CompnameSplit(driver, Companynamesplit, "AddException");
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		// Searching the customer using Customer Code
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, CustomerCode);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(2000);
		if (Customer_type.equalsIgnoreCase("Taxable")) {
			driver.findElement(By.xpath("//td[contains(.,'" + Customer_type + "')]/../td/span/a[contains(.,'View')]"))
					.click();
			waitforElement(180, "//tr[1]/td[12]", driver);
			driver.findElement(By.xpath("//tr[1]/td[12]")).click();
			waitforElement(180,
					"//td[contains(.,'" + CustomerName + "')]/../td/div/ul/li/a[contains(.,'Upload Image')]", driver);
			driver.findElement(
					By.xpath("//td[contains(.,'" + CustomerName + "')]/../td/div/ul/li/a[contains(.,'Upload Image')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			JavaScriptscrolltoView(driver, CE.BROWSEBTNTaxable);
			Thread.sleep(2000);
			if (Browser.equalsIgnoreCase("chrome")) {
				click(CE.BROWSEBTNTaxable);
			} else {
				JavaScriptclick(driver, CE.BROWSEBTNTaxable);
			}
			Thread.sleep(2000);
			if (Filename.contains("jpg")) {
				fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, ".jpg");
			} else {
				fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, ".pdf");
			}
			Thread.sleep(2000);
			click(CE.SAVECERTIFICATE);
			Thread.sleep(3000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[contains(.,'" + CustomerName + "')]/../td/div/a")).click();
			assertTrue(driver
					.findElement(By.xpath(
							"//td[contains(.,'" + CustomerName + "')]/../td/div/ul/li/a[contains(.,'View Image')]"))
					.isDisplayed());
			Thread.sleep(6000);
			JavaScriptclick(driver, CE.CUSTOMERLISTING);
			waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		}
		if (Customer_type.equalsIgnoreCase("Exempt")) {
			driver.findElement(By.xpath("//td[contains(.,'" + CustomerCode + "')]/../td/div/a")).click();
			driver.findElement(
					By.xpath("//td[contains(.,'" + CustomerCode + "')]/../td/div/ul/li/a[contains(.,'Upload Image')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(4000);
			JavaScriptscrolltoView(driver, CE.BROWSEBTN);
			Thread.sleep(3000);
			if (Browser.equalsIgnoreCase("chrome")) {
				click(CE.BROWSEBTN);
			} else {
				JavaScriptclick(driver, CE.BROWSEBTN);
			}

			Thread.sleep(5000);
			if (Filename.contains("jpg")) {
				fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, ".jpg");
			} else {
				fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, ".pdf");
			}
			Thread.sleep(5000);
			driver.findElement(By.xpath(CE.SAVEANDCLOSE_XPATH)).click();
			Thread.sleep(3000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[contains(.,'" + CustomerCode + "')]/../td/div/a")).click();
			assertTrue(driver
					.findElement(By.xpath(
							"//td[contains(.,'" + CustomerCode + "')]/../td/div/ul/li/a[contains(.,'View Image')]"))
					.isDisplayed());

			Thread.sleep(5000);
		}
	}

	public void CopyConfigNoCustomersSingleTaxpayer(String Source_Company) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		xDownloadFiles("ExceptionFiles");
		xAutoIT();
		xUploadAdjustmentFile("ExceptionFiles");
		new PortalFunctions(driver);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// End is writing to break the loop if there is a error while uploading the
		// invalid product sample
		WebElement NoCustomers = driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", NoCustomers);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		/*
		 * waitforElement(180, CE.VIEWALLEXCEPTIONS, driver);
		 * click(CE.VIEWALLEXCEPTIONS);
		 */
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (!VerifyObject(driver, PP.DownloadProducts)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (!VerifyObject(driver, PP.CopyProducts)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void ECMUpload(String Company, String File_name, String Image_Filenames, String Delete_File,
			String Successfile, String Errorfile, String Validate, String Dwnld_Fileformat) throws Exception {
		strCustName = "";
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		PortalFunctions DownloadFiles = new PortalFunctions(driver);
		Robot robot = new Robot();
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String Filename, Excepfile = null;
		int count = 0;
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.HELPCENTERLINK, driver);
		Thread.sleep(2000);
		String[] name;
		String path, Filename1, SuccessFile, ErrorFile, DirName;
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionRed = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionRed);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../../div/div/div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, CE.CLOSEICON)) {
			JavaScriptclick(driver, CE.CLOSEICON);
		}
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, CE.UPLOADEXCEPTION, driver);
		JavaScriptclick(driver, CE.UPLOADEXCEPTION);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		scrollUp(driver);
		Thread.sleep(5000);
		waitforElement(180, CE.BROWSE, driver);
		// Verifying the ECM file format
		if (Dwnld_Fileformat != "") {
			File Appdownload = null;
			File Inputtestdatafile = null;
			JavaScriptclick(driver, CE.DNLDFILEFRMT);
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				driver.navigate();
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_S);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("CHROME")) {
				File directory = new File(".//");
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "ExceptionFiles";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				Appdownload = new File(LatestDownloadedFile("ExceptionFiles", true));
			}
			if (Browser.equalsIgnoreCase("FIREFOX")) {
				Thread.sleep(5000);
				Appdownload = new File(LatestDownloadedFile("ExceptionFiles", false));
			}
			// Validating the Downloaded file Format
			waitforElement(180, CE.BROWSE, driver);
			File directory = new File(".//");
			DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "ExceptionFiles";
			Inputtestdatafile = new File(DirName + "\\" + "ExceptionFileFormat.csv");
			Comparecsvfiles(Appdownload, Inputtestdatafile);
		}
		if (Browser.equalsIgnoreCase("CHROME")) {
			click(CE.BROWSE);
		} else {
			JavaScriptclick(driver, CE.BROWSE);
		}
		Thread.sleep(4000);
		Filename = File_name;
		Path RSource = Paths.get(AdjustmentFilePath + "\\" + Filename + ".csv");
		Filename1 = Filename + RND();
		Path RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + ".csv");
		Files.copy(RSource, RTarget);
		Filename = Filename1;
		Thread.sleep(2000);
		fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, ".csv");
		Thread.sleep(10000);
		click(CE.EXCEPTIONUPLOAD);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.MANAGECERTLABEL, driver);
		Thread.sleep(10000);
		Excepfile = Filename;
		// Verifying Ecm Bulk upload Action required functionality
		if (Image_Filenames != "") {
			waitforElement(180, "//table/tbody/tr/td[text()='" + Filename + "']/../td/div/a[text()='Action Required']",
					driver);
			driver.findElement(
					By.xpath("//table/tbody/tr/td[text()='" + Filename + "']/../td/div/a[text()='Action Required']"))
					.click();
			// if(!(ExcepuploadSheet.getCell(Image_Filenames,Data_Row).getContents().equalsIgnoreCase("Yes"))){
			String imagefilenames[] = Image_Filenames.split(",");
			for (int j = 0; j < imagefilenames.length; j++) {
				waitforElement(180, CE.ADDIMAGES, driver);
				click(CE.ADDIMAGES);
				Thread.sleep(3000);
				fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, imagefilenames[j], "");
				waitforElement(180, CE.UPLOAD, driver);
				click(CE.UPLOAD);
				waitforElement(180, CE.DONE, driver);
				click(CE.DONE);
				Thread.sleep(3000);
			}
			// String Imagesuploadcount=driver.findElement(By.xpath("//a[text()='Images
			// Uploaded']/../span")).getText();
			// Validating Success Row link
			assertTrue(CE.SUCCESSROWSLINK.isDisplayed());
			// Validating Error Row link
			assertTrue(CE.ERRORROWSLINK.isDisplayed());
			// Validating Images Expected link
			assertTrue(CE.IMAGESEXPECTED.isDisplayed());
			// Validating Images Missing link
			assertTrue(CE.IMAGESMISSING.isDisplayed());
			// Validating Images Upload link
			assertTrue(CE.IMAGESUPLOADED.isDisplayed());
			// Verifying the Delete Functionality
			if (Delete_File.equalsIgnoreCase("Yes")) {
				waitforElement(180, CE.DELETEFILE, driver);
				click(CE.DELETEFILE);
				waitforElement(180, CE.CANCEL, driver);
				click(CE.CANCEL);
				waitUntilElementInvisibe(90, "//a/strong[text()='Cancel']", driver);
				Thread.sleep(1000);
				// clicking on Return to File Listing
				waitforElement(180, CE.RETURNTOFILELISTING, driver);
				click(CE.RETURNTOFILELISTING);
				waitforElement(180, CE.ADDEXCEPT, driver);
				waitforElement(180, "//td[contains(.,'" + Excepfile + "')]/../td/a[text()='Delete']", driver);
				driver.findElement(By.xpath("//td[contains(.,'" + Excepfile + "')]/../td/a[text()='Delete']")).click();
				waitforElement(180, CE.REMOVEITEM, driver);
				click(CE.REMOVEITEM);
				Thread.sleep(5000);
			} else {
				waitforElement(180, CE.COMPLETEUPLOAD, driver);
				click(CE.COMPLETEUPLOAD);
				waitforElement(180, CE.VIEWALLEXCEPTIONS, driver);
			}
		}
		if (!Delete_File.equalsIgnoreCase("Yes")) {
			if (VerifyObject(driver,
					"//td[contains(.,'" + Excepfile + "')]/../td/div/i[contains(.,'Processing ...')]")) {
				boolean report = false;
				count = 0;
				do {
					Thread.sleep(5000);
					count = count + 1;
					if (count == 50) {
						assertTrue(false);
						break;
					}
					if (VerifyObject(driver, "//td[contains(.,'" + Excepfile
							+ "')]/../td/div[@class='ng-hide']/i[contains(.,'Processing ...')]")) {
						report = true;
					}
				} while (report == false);
			}
			Thread.sleep(5000);
			WebElement Orgfile = driver
					.findElement(By.xpath("//td[contains(.,'" + Excepfile + "')]/../td/a[text()='Original File']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Orgfile);
		}
		// driver.findElement(By.xpath("//td[contains(.,'"+Excepfile+"')]/../td/a[text()='Original
		// File']")).click();
		Thread.sleep(5000);
		// assertTrue(CE.STATUS.isDisplayed());
		assertTrue(CE.DATEADDED.isDisplayed());
		assertTrue(CE.UPLOADFILENAME.isDisplayed());
		assertTrue(CE.OrgFile.isDisplayed());
		assertTrue(CE.SucReport.isDisplayed());
		assertTrue(CE.ErrReport.isDisplayed());
		SuccessFile = "//table/tbody/tr/td[contains(.,'" + Excepfile
				+ "')]/../td/a[not(@class='ng-hide') and contains(.,'Success File')]";
		ErrorFile = "//table/tbody/tr/td[contains(.,'" + Excepfile
				+ "')]/../td/a[@class='ng-hide'][contains(.,'Error File')]";

		if (Successfile.equalsIgnoreCase("YES")) {
			FIRSTS: if (!VerifyObject(driver, SuccessFile)) {
				boolean report = false;
				count = 0;
				do {
					Thread.sleep(5000);
					if (!VerifyObject(driver, ErrorFile)) {
						assertTrue(true);
						break FIRSTS;
					}
					count = count + 1;
					if (count == 20) {
						assertTrue(false);
						break;
					}
					if (VerifyObject(driver, SuccessFile)) {
						report = true;
					}
				} while (report == false);
			}
			Thread.sleep(5000);
			WebElement Succsfile = driver
					.findElement(By.xpath("//td[contains(.,'" + Excepfile + "')]/../td/a[text()='Success File']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Succsfile);
			// driver.findElement(By.xpath("//td[contains(.,'"+Excepfile+"')]/../td/a[text()='Success
			// File']")).click();
			Thread.sleep(5000);
			File tempfile = null;
			File tempfile2 = null;
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "ExceptionFiles";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(5000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("ExceptionFiles", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				tempfile = new File(LatestDownloadedFile("ExceptionFiles", false));
			}

			if (Validate != "") {
				File directory = new File(".//");
				DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "ExceptionFiles";
				name = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
				tempfile2 = new File(DirName + "\\" + name[0] + "_success.csv");
				Thread.sleep(5000);
				Comparecsvfiles(tempfile, tempfile2);
			}
		}
		if (Errorfile != "") {
			FIRST: if (VerifyObject(driver, ErrorFile)) {
				boolean report = false;
				count = 0;
				do {
					Thread.sleep(30000);
					if (VerifyObject(driver, SuccessFile)) {
						assertTrue(true);
						break FIRST;
					}
					count = count + 1;
					if (count == 8) {
						assertTrue(false);
						break;
					}
					if (!VerifyObject(driver, ErrorFile)) {
						report = true;
					}
				} while (report == false);
			}
			Thread.sleep(5000);
			WebElement Errfile = driver
					.findElement(By.xpath("//td[contains(.,'" + Excepfile + "')]/../td/a[text()='Error File']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Errfile);
			Thread.sleep(5000);
			File tempfile = null;
			File tempfile2 = null;
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "ExceptionFiles";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(5000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("ExceptionFiles", true));

			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				tempfile = new File(LatestDownloadedFile("ExceptionFiles", false));
			}
			if (Validate != "") {
				File directory = new File(".//");
				DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "ExceptionFiles";
				// Regular expression for spliting the file name between in digits and
				// characters
				name = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
				tempfile2 = new File(DirName + "\\" + name[0] + "_error.csv");
				Comparecsvfiles(tempfile, tempfile2);
			}
		}
		assertTrue(CE.VIEWALLEXCEPTIONS.isDisplayed());
		waitforElement(180, CE.VIEWALLEXCEPTIONS, driver);
		JavaScriptclick(driver, CE.VIEWALLEXCEPTIONS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		JavaScriptclick(driver, CE.RETRURNTOCONFIGUREOVERVIEW);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(1000);
	}

	public void UpdateCertificateDates(String Company, String Update_Issuedate, String CodeValue,
			String Cert_Exception_No, String Exception_State) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		strCustName = "";
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]")) {
			driver.findElement(
					By.xpath("//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
		} else {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]")) {
				driver.findElement(
						By.xpath("//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]")) {
				driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			}
		}
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, CodeValue);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(5000);
		if (Update_Issuedate != "") {
			driver.findElement(By.xpath("//td[contains(.,'" + CodeValue + "')]/../td/span/a")).click();
			waitforElement(120, "//tbody/tr/td[contains(.,'" + CodeValue + "')]/../td/div/a[@class='dropdown-toggle']",
					driver);
			Thread.sleep(5000);
			driver.findElement(By.xpath(
					"//tbody/tr/td[contains(.,'" + Cert_Exception_No + "')]/../td/div/a[@class='dropdown-toggle']"))
					.click();
			Thread.sleep(8000);
			JavaScriptclick(driver, CE.EDIT);
			Thread.sleep(5000);
			driver.findElement(By.xpath(
					"//tbody/tr/td[contains(.,'" + Exception_State + "')]/../td/div/a[@class='dropdown-toggle']"))
					.click();
			Thread.sleep(8000);
			driver.findElement(By
					.xpath("//tr/td[contains(.,'" + Cert_Exception_No + "')]/../td[5]/div/ul/li/a[contains(.,'Edit')]"))
					.click();
			// JavaScriptclick(driver,CE.EDIT);
			Thread.sleep(5000);
			clear(CE.ISSUEDATE);
			click(CE.ISSUEDATE);
			type(CE.ISSUEDATE, Update_Issuedate);
			sendkeysTab(CE.ISSUEDATE);
			clear(CE.REVIEWDATE);
			click(CE.REVIEWDATE);
			type(CE.REVIEWDATE, Update_Issuedate);
			sendkeysTab(CE.REVIEWDATE);
			clear(CE.EXPIRATIONDATE);
			click(CE.EXPIRATIONDATE);
			type(CE.EXPIRATIONDATE, Update_Issuedate);
			sendkeysTab(CE.EXPIRATIONDATE);
			Thread.sleep(3000);
			if (VerifyObject(driver, CE.SAVEJURISDICTION)) {
				JavaScriptclick(driver, CE.SAVEJURISDICTION);
			}
			Thread.sleep(5000);
			JavaScriptclick(driver, CE.SAVECERTIFICATE);
			Thread.sleep(7000);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, CE.CUSTOMERLISTING);
		Thread.sleep(2000);
		JavaScriptclick(driver, CE.READYTOSAVE);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
	}

	public void EditCustExAfterPushOS(String Company, String Customer_Name, String Expcode,
			String Cert_Exception_EndDate, String ViewCustomer, String Add_Certificates, String Exception_Reason,
			String Cert_Exception_No, String IssueDate, String ReviewDate, String ExpirationDate) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		strCustName = "";
		String Customername, Excelcustname;
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]")) {
			driver.findElement(
					By.xpath("//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
		} else {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]")) {
				driver.findElement(
						By.xpath("//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]")) {
				driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			}
		}
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, Expcode);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//td[contains(.,'" + Expcode + "')]/../td/span/a[contains(.,'View')]")).click();
		waitforElement(180, CE.ADDCERTIFICATE, driver);
		JavaScriptclick(driver, CE.Actionitem);
		waitforElement(180, CE.Actionitemlink, driver);
		scrollDown(driver);
		Thread.sleep(10000);
		waitforElement(180,
				"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]",
				driver);
		driver.findElement(By.xpath(
				"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]"))
				.click();
		waitforText(180, CE.EDITCERTIFICATETEXT, driver);
		Thread.sleep(5000);
		waitforElement(180, CE.EXCEPENDDATE, driver);
		click(CE.EXCEPENDDATE);
		type(CE.EXCEPENDDATE, Cert_Exception_EndDate);
		sendkeysTab(CE.EXCEPENDDATE);
		if ((ViewCustomer != "") && (Add_Certificates != "")) {
			waitforElement(90, CE.ADDCERTIFICATE, driver);
			// JavaScriptclick(driver, CE.ADDCERTIFICATE);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//td[5]/div/a[@class='dropdown-toggle']")).click();
			waitforElement(180, CE.Actionitemlink, driver);
			scrollDown(driver);
			Thread.sleep(8000);
			JavaScriptclick(driver, CE.Editcertificatelink);
			waitforText(180, CE.EDITJURISDICTIONTEXT, driver);
			JavaScriptclick(driver, CE.ExceptionReason);
			Thread.sleep(2000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				type(CE.ExceptionReason, Exception_Reason);
			} else {
				selectByValue(CE.ExceptionReason, Exception_Reason);
			}
			sendkeysTab(CE.ExceptionReason);
			if ((Cert_Exception_No != "") && (VerifyObject(driver, CE.EXCEPNUM))) {
				clear(CE.EXCEPNUM);
				click(CE.EXCEPNUM);
				type(CE.EXCEPNUM, Cert_Exception_No);
			}
			if (IssueDate != "") {
				assertTrue(CE.ISSUEDATE.isDisplayed());
				clear(CE.ISSUEDATE);
				click(CE.ISSUEDATE);
				type(CE.ISSUEDATE, IssueDate);
				sendkeysTab(CE.ISSUEDATE);
			}
			if (ReviewDate != "") {
				assertTrue(CE.REVIEWDATE.isDisplayed());
				clear(CE.REVIEWDATE);
				click(CE.REVIEWDATE);
				type(CE.REVIEWDATE, ReviewDate);
				sendkeysTab(CE.REVIEWDATE);
			}
			if (ExpirationDate != "") {
				assertTrue(CE.EXPIRATIONDATE.isDisplayed());
				clear(CE.EXPIRATIONDATE);
				click(CE.EXPIRATIONDATE);
				type(CE.EXPIRATIONDATE, ExpirationDate);
				sendkeysTab(CE.EXPIRATIONDATE);
			}
			waitforElement(90, CE.SAVEJURISDICTION, driver);
			JavaScriptclick(driver, CE.SAVEJURISDICTION);
			waitforElement(90, CE.SAVECERTIFICATE, driver);
			JavaScriptclick(driver, CE.SAVECERTIFICATE);
			Thread.sleep(5000);
			scrollUp(driver);
			JavaScriptclick(driver, CNP.CONFIGURE);
		} else {
			waitforElement(180,
					"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/a",
					driver);
			driver.findElement(By.xpath(
					"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/a"))
					.click();
			waitforElement(180,
					"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]",
					driver);
			driver.findElement(By.xpath(
					"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CE.CUSTOMERNAME, driver);
			clear(CE.CUSTOMERNAME);
			waitforElement(180, CE.THISFIELDISREQ, driver);
			type(CE.CUSTOMERNAME, Customer_Name);
			waitUntilElementInvisibe(180, CE.THISFIELDISREQ_XPATH, driver);
			// Giving end date for the customer
			click(CE.ENDDATE_CUST);
			type(CE.ENDDATE_CUST, Currentdate);
			click(CE.SAVEANDCLOSE);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			Customername = driver
					.findElement(By.xpath(
							"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr[1]/td[2]"))
					.getText();
			Excelcustname = Customer_Name;
			assertTrue(Customername.equalsIgnoreCase(Excelcustname));
			click(CE.RETRNCONFIGOVER);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		}
	}

	public void EditCustomer(String Company, String Customer_Name, String Expcode, String Cert_Exception_EndDate,
			String Exception_Reason, String Cert_Exception_No, String IssueDate, String ReviewDate,
			String ExpirationDate, String Add_States, String Exception_State, String Certificate_Scope,
			String Product_Code, String Prodcut_Category, String Product_Type) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		strCustName = "";
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]")) {
			driver.findElement(
					By.xpath("//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
		} else {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]")) {
				driver.findElement(
						By.xpath("//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]")) {
				driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			}
		}
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, Expcode);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//td[contains(.,'" + Expcode + "')]/../td/span/a[contains(.,'View')]")).click();
		waitforElement(180, CE.ADDCERTIFICATE, driver);
		JavaScriptclick(driver, CE.Actionitem);
		waitforElement(180, CE.Actionitemlink, driver);
		scrollDown(driver);
		Thread.sleep(10000);
		waitforElement(180,
				"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]",
				driver);
		driver.findElement(By.xpath(
				"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]"))
				.click();
		waitforText(180, CE.EDITCERTIFICATETEXT, driver);
		Thread.sleep(5000);
		waitforElement(180, CE.EXCEPENDDATE, driver);
		click(CE.EXCEPENDDATE);
		type(CE.EXCEPENDDATE, Cert_Exception_EndDate);
		sendkeysTab(CE.EXCEPENDDATE);
		Thread.sleep(5000);
		JavaScriptclick(driver, CE.SAVECERTIFICATE);
		Thread.sleep(2000);
		// JavaScriptclick(driver,CE.RETRURNTOCUSTOMERS);
		waitforElement(900, CE.ADDCERTIFICATE, driver);
		JavaScriptclick(driver, CE.ADDCERTIFICATE);
		Thread.sleep(5000);
		waitforElement(180, CE.CUSTOMERINFOLINK, driver);
		if (!VerifyObject(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN)) {
			JavaScriptclick(driver, CE.CUSTOMERINFOLINK);
		}
		waitforElement(180, CE.SEARCHCUSTOMERADDCERTFSCREEN, driver);
		JavaScriptclick(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN);
		type(CE.SEARCHCUSTOMERADDCERTFSCREEN, Customer_Name);
		waitforElement(180, "//a/span[contains(.,'" + Customer_Name + "')]", driver);
		Thread.sleep(2000);
		WebElement customer = driver.findElement(By.xpath("//a/span[contains(.,'" + Customer_Name + "')]"));
		JavaScriptclick(driver, customer);
		JavaScriptclick(driver, CE.CERTIINFO);

		if (Certificate_Scope != "") {
			assertTrue(CE.CertificateScopelabelText.isDisplayed());
			Thread.sleep(5000);
			switch (Certificate_Scope) {
			case "Limited":
				sendkeysTab(CE.EXCEPENDDATE);
				selectByValue(CE.CertificateScopeValue, Certificate_Scope);
				assertTrue(CE.Code_classText.isDisplayed());
				String ProdVal = Product_Code;
				String ProdCategoryVal = Prodcut_Category;
				String ProdCodeType = Product_Type;
				Thread.sleep(2000);
				click(CE.ProductCodeVal);
				type(CE.ProductCodeVal, ProdVal);
				Thread.sleep(4000);
				assertTrue(
						driver.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[contains(.,'" + ProdVal + "')]"))
								.isDisplayed());

				if ((ProdCategoryVal != "") || (ProdCodeType != "")) {
					assertTrue(driver
							.findElement(By
									.xpath("//div[@class='chosen-drop']/ul/li[contains(.,'" + ProdCategoryVal + "')]"))
							.isDisplayed());
				}
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[contains(.,'" + ProdVal + "')]"))
						.click();
			}
		}
		if (Add_States != "") {
			// Edited with Cert_Row
			Thread.sleep(3000);
			if (VerifyObject(driver, CE.VERIFYPLUSICONJURISINFO)) {
				click(CE.VERIFYPLUSICONJURISINFO);
			}
			click(CE.ADDJURISDICTION);
			waitforElement(180, CE.ExceptionType, driver);
			click(CE.ExceptionType);
			selectByValue(CE.ExceptionType, Exception_State);
			Thread.sleep(3000);
			// ctr++;
			click(CE.ExceptionReason);
			Thread.sleep(2000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				type(CE.ExceptionReason, Exception_Reason);
			} else {
				selectByValue(CE.ExceptionReason, Exception_Reason);
			}
			if (Cert_Exception_No != "") {
				clear(CE.EXCEPNUM);
				click(CE.EXCEPNUM);
				type(CE.EXCEPNUM, Cert_Exception_No);
			}
			if (IssueDate != "") {
				assertTrue(CE.ISSUEDATE.isDisplayed());
				clear(CE.ISSUEDATE);
				click(CE.ISSUEDATE);
				type(CE.ISSUEDATE, IssueDate);
				sendkeysTab(CE.ISSUEDATE);
			}
			if (ReviewDate != "") {
				assertTrue(CE.REVIEWDATE.isDisplayed());
				clear(CE.REVIEWDATE);
				click(CE.REVIEWDATE);
				type(CE.REVIEWDATE, ReviewDate);
				sendkeysTab(CE.REVIEWDATE);
			}
			if (ExpirationDate != "") {
				assertTrue(CE.EXPIRATIONDATE.isDisplayed());
				clear(CE.EXPIRATIONDATE);
				click(CE.EXPIRATIONDATE);
				type(CE.EXPIRATIONDATE, ExpirationDate);
				sendkeysTab(CE.EXPIRATIONDATE);
			}

			waitforElement(180, CE.SAVEJURISDICTION, driver);
			if (VerifyObject(driver, CE.SAVEJURISDICTION)) {
				click(CE.SAVEJURISDICTION);
			}
		}
		Thread.sleep(2000);
		click(CE.SAVECERTIFICATE);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);

		}
	}

	public void editCustomerstartDate(String Company, String Customer_Name, String CustomerStartDate) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		strCustName = "";
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		Thread.sleep(3000);
		JavaScriptclick(driver, CE.SearchBynameRadioBtn);
		click(CE.SEARCH);
		type(CE.SEARCH, Customer_Name);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(3000);
		WebElement customerclick = driver
				.findElement(By.xpath("//td[contains(.,'" + Customer_Name + "')]/../td[9]/div/a"));
		JavaScriptscrolltoView(driver, customerclick);
		Thread.sleep(2000);
		JavaScriptclick(driver, customerclick);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//tbody/tr[1]/td/div/ul/li/a[contains(.,'Edit')]")).click();
		waitforElement(90, CE.ENDDATE_CUST, driver);
		JavaScriptclick(driver, CE.STARTDATE_CUST);
		clear(CE.STARTDATE_CUST);
		type(CE.STARTDATE_CUST, CustomerStartDate);
		// sendkeysTab(CE.STARTDATE_CUST);
		Thread.sleep(3000);
		click(CE.SAVEANDCLOSE);
		Thread.sleep(5000);
		if (VerifyObject(driver, CE.CERTIFICATESUPDATEDtext)) {
			assertTrue(CE.warningtext.isDisplayed());
			assertTrue(CE.startdatetex.isDisplayed());
			assertTrue(CE.gotittext.isDisplayed());
			click(CE.gotittext);
			JavaScriptclick(driver, CE.SearchBynameRadioBtn);
			clear(CE.SEARCH);
			click(CE.SEARCH);
			type(CE.SEARCH, Customer_Name);
			sendkeysEnter(CE.SEARCH);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'View')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(5000);
			String date = driver
					.findElement(By.xpath(
							"//td[contains(.,'" + Customer_Name + "')]/../td[contains(.,'" + CustomerStartDate + "')]"))
					.getAttribute("value");
			System.out.println(date);
			assertTrue(driver
					.findElement(By.xpath(
							"//td[contains(.,'" + Customer_Name + "')]/../td[contains(.,'" + CustomerStartDate + "')]"))
					.isDisplayed());
		}
	}

	public void AddCertificatewithissuedate(String Company, String code, String Customer_Name,
			String Cert_Exception_StartDate, String Cert_Exception_EndDate, String Certificate_Scope,
			String Product_Code, String Document_Number, String Add_States, String Cert_Jurisdiction,
			String Cert_Reason, String Cert_Exception_No, String Addanother_State, String Cert_Upload_Image,
			String Imagename, String Cert_Exception_Type, String Taxability_Category, String Valid_ExcepNumber,
			String ExpirationType, String ExpirationRule, String NewExpDate, String NoIssue_Date, String Format,
			String Exception_Number_Format, String Sample, String IssueDate, String ReviewDate, String ExpirationDate)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		strCustName = "";
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String Uploadimg;
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		Thread.sleep(3000);
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		waitforElement(180, CE.PAGINATION, driver);
		Thread.sleep(5000);
		click(CE.SearchByName);
		type(CE.SEARCH, Customer_Name);
		CE.SEARCH.sendKeys(Keys.ENTER);
		waitforElement(180, "//td[contains(.,'" + Customer_Name + "')]/../td/div/a", driver);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'View')]",
				driver);
		WebElement view = driver.findElement(
				By.xpath("//table/tbody/tr/td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'View')]"));
		JavaScriptclick(driver, view);
		if (VerifyObject(driver, "//button[@class='close drawer__btn-close']")) {
			WebElement closeaddcustomer = driver.findElement(By.xpath("//button[@class='close drawer__btn-close']"));
			JavaScriptclick(driver, closeaddcustomer);
			Thread.sleep(2000);
		}
		waitforElement(180, CE.ADDCERTIFICATE, driver);
		JavaScriptclick(driver, CE.ADDCERTIFICATE);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		// CE.ADDCERTIFICATE);
		waitforElement(180, CE.CUSTOMERINFOLINK, driver);
		if (!VerifyObject(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN)) {
			JavaScriptclick(driver, CE.CUSTOMERINFOLINK);
		}
		waitforElement(180, CE.SEARCHCUSTOMERADDCERTFSCREEN, driver);
		JavaScriptclick(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN);
		type(CE.SEARCHCUSTOMERADDCERTFSCREEN, Customer_Name);
		waitforElement(180, "//a/span[contains(.,'" + Customer_Name + "')]", driver);
		Thread.sleep(2000);
		WebElement customer = driver.findElement(By.xpath("//a/span[contains(.,'" + Customer_Name + "')]"));
		JavaScriptclick(driver, customer);
		waitforElement(180, CE.CertificateInfoLink, driver);
		JavaScriptclick(driver, CE.CertificateInfoLink);
		waitforElement(180, CE.EXCEPTYPE, driver);

		if (Cert_Exception_Type != "") {
			selectByValue(CE.EXCEPTYPE, Cert_Exception_Type);
			if (Cert_Exception_Type.equalsIgnoreCase("Special Condition")) {
				type(CE.SPTaxabilityCategory, Taxability_Category);
				waitforElement(60, "//p[contains(.,'" + Taxability_Category + "')]", driver);
				CE.SPTaxabilityCategory.sendKeys(Keys.BACK_SPACE);
				CE.SPTaxabilityCategory.sendKeys(Keys.TAB);
			}
		}

		if (Cert_Exception_StartDate != "") {
			waitforElement(180, CE.EXCEPSTRATDATE, driver);
			clear(CE.EXCEPSTRATDATE);
			type(CE.EXCEPSTRATDATE, Cert_Exception_StartDate);
			click(CE.EXCEPSTRATDATE);
			sendkeysTab(CE.EXCEPSTRATDATE);
			sendkeysTab(CE.EXCEPENDDATE);
		}
		if (Cert_Exception_EndDate != "") {
			clear(CE.EXCEPENDDATE);
			type(CE.EXCEPENDDATE, Cert_Exception_EndDate);
			sendkeysTab(CE.EXCEPENDDATE);
		}
		// Adding the Certificate scope
		if (Certificate_Scope != "") {
			String Cerscope = Certificate_Scope;
			switch (Cerscope) {
			case "Limited":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				String ProdVal = Product_Code;
				waitforElement(180, CE.ProductCodeVal, driver);
				click(CE.ProductCodeVal);
				type(CE.ProductCodeVal, ProdVal);
				Thread.sleep(5000);
				WebElement selectprod = driver
						.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[contains(.,'" + ProdVal + "')]"));
				click(selectprod);
				break;
			case "Single Use":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				Thread.sleep(2000);
				String DocumentValue = Document_Number;
				waitforElement(180, CE.InvoiceNumber, driver);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptclick(driver, CE.InvoiceNumber);
				} else {
					click(CE.InvoiceNumber);
				}
				type(CE.InvoiceNumber, DocumentValue);
				sendkeysTab(CE.InvoiceNumber);
				break;
			case "Blanket":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				Thread.sleep(2000);
				break;
			}
		}
		if (Add_States.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, CE.JURISDICTIONLINK);
			waitforElement(180, CE.ADDJURISDICTION, driver);
			JavaScriptclick(driver, CE.ADDJURISDICTION);
			waitforElement(180, CE.BackToAddCertificate, driver);
			Select stateSelectbox = new Select(
					driver.findElement(By.xpath("//select[@id='ddlExceptionJurisdiction']")));
			stateSelectbox.selectByVisibleText(Cert_Jurisdiction);
			Select ExceptionReason = new Select(driver.findElement(By.xpath("//select[@id='ddlExceptionReason']")));
			ExceptionReason.selectByVisibleText(Cert_Reason);
			driver.findElement(By.xpath("//select[@id='ddlExceptionReason']")).sendKeys(Keys.TAB);
			if (Cert_Exception_No != "") {
				clear(CE.EXCEPNUM);
				String CerNumber = Cert_Exception_No;
				type(CE.EXCEPNUM, CerNumber);
				sendkeysTab(CE.EXCEPNUM);
				Thread.sleep(3000);
				if (IssueDate != "") {
					assertTrue(CE.ISSUEDATE.isDisplayed());
					clear(CE.ISSUEDATE);
					click(CE.ISSUEDATE);
					type(CE.ISSUEDATE, IssueDate);
					sendkeysTab(CE.ISSUEDATE);
				}
				if (ReviewDate != "") {
					assertTrue(CE.REVIEWDATE.isDisplayed());
					clear(CE.REVIEWDATE);
					click(CE.REVIEWDATE);
					type(CE.REVIEWDATE, ReviewDate);
					sendkeysTab(CE.REVIEWDATE);
				}
				if (ExpirationDate != "") {
					assertTrue(CE.EXPIRATIONDATE.isDisplayed());
					clear(CE.EXPIRATIONDATE);
					click(CE.EXPIRATIONDATE);
					type(CE.EXPIRATIONDATE, ExpirationDate);
					sendkeysTab(CE.EXPIRATIONDATE);
				}
				if (Valid_ExcepNumber != "") {
					JavaScriptclick(driver, CE.ValidateBtn);
					Thread.sleep(5000);
					if ((Valid_ExcepNumber.equalsIgnoreCase("Valid"))
							|| (Valid_ExcepNumber.equalsIgnoreCase("Invalid"))) {
						if ((Add_States) != "") {
							sendkeysTab(CE.ExceptionReason);
							scrollDown(driver);
							if (ExpirationType != "" && ExpirationRule.equalsIgnoreCase("Yes")) {
								Thread.sleep(8000);
								assertTrue(driver.findElement(By.xpath(
										"//div[contains(text(),'Expiration date has been calculated')]/b[contains(text(),'"
												+ NewExpDate + "')]/.."))
										.isDisplayed());
								Thread.sleep(5000);
							}
							if (ExpirationRule.equalsIgnoreCase("Yes")
									&& Valid_ExcepNumber.equalsIgnoreCase("Invalid")) {
								assertTrue(CE.NoissueDate.isDisplayed());
							}

							if (ExpirationRule.equalsIgnoreCase("No")) {
								assertTrue(CE.NoExpirationdate.isDisplayed());
							}
							assertTrue(driver
									.findElement(By.xpath("//div[contains(text(),'Validation:')]/b[contains(text(),'"
											+ Valid_ExcepNumber + "')]"))
									.isDisplayed());
							if (Valid_ExcepNumber.equalsIgnoreCase("Valid")) {
								assertTrue(CE.ExRsnJuriVldLabel.isDisplayed());
							}
							assertTrue(driver.findElement(By.xpath(
									"//div[contains(text(),'The following exception number formats are valid for')]/b[contains(text(),'"
											+ Cert_Reason + "')]"))
									.isDisplayed());
							assertTrue(
									driver.findElement(By.xpath("//div[contains(.,'exceptions in')]/b[contains(text(),'"
											+ Cert_Jurisdiction + "')]")).isDisplayed());
							if (NoIssue_Date != "") {
								assertTrue(CE.No_Issue_Date.isDisplayed());
							}
							if ((Format != "") && (!Valid_ExcepNumber.equalsIgnoreCase("NA"))) {

								assertTrue(driver
										.findElement(By.xpath("//tbody[@class='popover-results']/tr/td[contains(.,'"
												+ Exception_Number_Format + "')]"))
										.isDisplayed());
								assertTrue(driver.findElement(By
										.xpath("//tbody[@class='popover-results']/tr/td[contains(.,'" + Sample + "')]"))
										.isDisplayed());

							}
						}
					} else if (Valid_ExcepNumber.equalsIgnoreCase("NA")) {
						if (Add_States != "") {
							scrollDown(driver);
							scrollDown(driver);
							assertTrue(CE.ExNumVldLabel.isDisplayed());
							assertTrue(driver.findElement(By.xpath(
									"//div[contains(text(),'There are no standard exception number formats defined for')]/b[contains(.,'"
											+ Cert_Reason + "')]"))
									.isDisplayed());
							if (ExpirationType != "") {
								assertTrue(driver.findElement(By.xpath(
										"//div[contains(text(),'Expiration date has been calculated')]/b[contains(text(),'"
												+ NewExpDate + "')]/../../div[contains(text(),'" + ExpirationType
												+ "')]"))
										.isDisplayed());
							}
							if (ExpirationRule.equalsIgnoreCase("No")) {
								assertTrue(CE.NoExpirationdate.isDisplayed());
							}
							assertTrue(driver.findElement(By.xpath(
									"//div[contains(.,'exceptions in')]/b[contains(.,'" + Cert_Jurisdiction + "')]"))
									.isDisplayed());
						}
					}
				}
			}

			if (Addanother_State != "") {
				Select Anotherstatebox = new Select(driver
						.findElement(By.xpath("//div/ng-form/div/div/div/select[@id='certificate-state-select']")));
				Anotherstatebox.selectByVisibleText(Addanother_State);
				(driver.findElement(By.xpath("//select[@id='cert-reason1']"))).getText();
				assertTrue(new Select(driver.findElement(By.xpath("//select[@id='cert-reason1']")))
						.getFirstSelectedOption().getText().equalsIgnoreCase("None"));
				JavaScriptclick(driver, CE.SAVEJURISDICTION);
			} else {
				JavaScriptclick(driver, CE.SAVEJURISDICTION);
			}
		}

		if (Cert_Upload_Image.equalsIgnoreCase("Yes")) {
			Thread.sleep(5000);
			// JavaScriptclick(driver,CE.CertificateImageLink);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", CE.CertificateImageLink);
			Uploadimg = Imagename;
			if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
				JavaScriptclick(driver, CE.CERTIFICATEIMAGEBROWSE);
			} else {
				JavaScriptclick(driver, CE.CERTIFICATEIMAGEBROWSE);
			}
			Thread.sleep(5000); // to wait for the file system window attched browser (AutoIt)
			switch (browser) {
			case "IE":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
				break;
			case "Firefox":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
				break;
			case "Chrome":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
				break;
			case "Safari":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Uploadimg + ".pdf");
				break;
			}
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, CE.ADDEXCEPTION)) {
			JavaScriptclick(driver, CE.ADDEXCEPTION);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		Thread.sleep(2000); // after progress busy complete wait for visibility of objects in the page

		if (VerifyObject(driver, CE.SAVECERTIFICATE)) {
			JavaScriptclick(driver, CE.SAVECERTIFICATE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}

		if (VerifyObject(driver, CE.EXPANDADDCERT)) {
			JavaScriptclick(driver, CE.EXPANDADDCERT);
		}
		if (VerifyObject(driver, CE.NEWCERTIBUTTON)) {
			JavaScriptclick(driver, CE.NEWCERTIBUTTON);
		}

		if (VerifyObject(driver, CE.SAVECERTIFICATE)) {
			JavaScriptclick(driver, CE.SAVECERTIFICATE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.RETURNTOCERTIFICATELIST)) {
			JavaScriptclick(driver, CE.RETURNTOCERTIFICATELIST);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, CE.ADDEXCEPTION)) {
			JavaScriptclick(driver, CE.ADDEXCEPTION);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.CUSTOMERLISTING)) {
			JavaScriptclick(driver, CE.CUSTOMERLISTING);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.VIEWALLEXCEPTIONS)) {
			JavaScriptclick(driver, CE.VIEWALLEXCEPTIONS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, CE.READYTOSAVE, driver);
		}
	}

	public void VatRegForCustomer(String VAT_Country, String VAT_Regcode, String Physical_Presence,
			String VAT_Country_Edit, String VAT_Country_Remove) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		assertTrue(CE.VATREGISTRATIONTABLE.isDisplayed());
		JavaScriptclick(driver, CE.VATREGISTRATION);
		Thread.sleep(6000);
		waitforElement(60, CE.VATCOUNTRIES, driver);
		// CD-3934 In Jurisdiction drop down, one option is displayed as 'User Defined'
		// while adding VAT registrations for customers
		String valueBeingChecked = "User Defined";
		WebElement drpdwn = driver.findElement(By.id("ddlVATCountry"));
		Select DrpDwnSel = new Select(drpdwn);
		List<WebElement> DrpDwnList = DrpDwnSel.getOptions();
		for (WebElement indElem : DrpDwnList) {
			if (indElem.getText().equalsIgnoreCase(valueBeingChecked)) {
				assertFalse((indElem.getText().equalsIgnoreCase(valueBeingChecked)));
				break;
			}
		}
		selectByValue(CE.VATCOUNTRIES, VAT_Country);
		click(CE.VATREGISTRATIONCODE);
		type(CE.VATREGISTRATIONCODE, VAT_Regcode);
		if (Physical_Presence.equalsIgnoreCase("Yes")) {
			click(CE.PHYSICALPRESENCEINDICATOR);
		}
		JavaScriptclick(driver, CE.CANCELVAT);
		Thread.sleep(3000);
		JavaScriptclick(driver, CE.VATREGISTRATION);
		Thread.sleep(3000);
		waitforElement(180, CE.VATCOUNTRIES, driver);
		selectByValue(CE.VATCOUNTRIES, VAT_Country);
		click(CE.VATREGISTRATIONCODE);
		type(CE.VATREGISTRATIONCODE, VAT_Regcode);
		if (Physical_Presence.equalsIgnoreCase("Yes")) {
			click(CE.PHYSICALPRESENCEINDICATOR);
		}
		JavaScriptclick(driver, CE.SAVEVAT);
		Thread.sleep(3000);
		if (Physical_Presence.equalsIgnoreCase("Yes")) {
			String PP = driver.findElement(By.xpath("//td[contains(.,'" + VAT_Country + "')]/../td[3]")).getText();
			assertTrue(PP.equalsIgnoreCase("true"));
		} else {
			driver.findElement(By.xpath("//td[contains(.,'" + VAT_Country + "')]/../td[3]")).getText();
		}

		driver.findElement(By.xpath("//td[contains(.,'" + VAT_Country + "')]/../td[4]/div/a")).click();
		Thread.sleep(3000);
		if (VAT_Country_Edit != "") {
			driver.findElement(
					By.xpath("//td[contains(.,'" + VAT_Country + "')]/../td[4]/div/ul/li/a[contains(.,'Edit')]"))
					.click();
			Thread.sleep(3000);
			waitforElement(180, CE.EDITJURISDICTION, driver);
			selectByValue(CE.VATCOUNTRIES, VAT_Country_Edit);
			JavaScriptclick(driver, CE.SAVEVAT);
			Thread.sleep(3000);
		}
		if (VAT_Country_Remove != "") {
			driver.findElement(
					By.xpath("//td[contains(.,'" + VAT_Country + "')]/../td[4]/div/ul/li/a[contains(.,'Remove')]"))
					.click();
			Thread.sleep(3000);
			// assertTrue(CE.VATREGISTRATIONTEXT.isDisplayed());
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
	}

	public void VerifyTaxibitycategory(String Company, String Customer_Name, String Expcode, String Categoryvalue)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]")) {
			driver.findElement(
					By.xpath("//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
		} else {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]")) {
				driver.findElement(
						By.xpath("//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]")) {
				driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			}
		}
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, Expcode);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//td[contains(.,'" + Expcode + "')]/../td/span/a[contains(.,'View')]")).click();
		waitforElement(180, CE.ADDCERTIFICATE, driver);
		JavaScriptclick(driver, CE.Actionitem);
		waitforElement(180, CE.Actionitemlink, driver);
		scrollDown(driver);
		Thread.sleep(10000);
		waitforElement(180,
				"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]",
				driver);
		driver.findElement(By.xpath(
				"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]"))
				.click();
		waitforText(180, CE.EDITCERTIFICATETEXT, driver);
		waitforText(180, CE.SPTaxabilityCategory, driver);
		// Thread.sleep(5000);
		// String Certcategory = getText(CE.SPTaxabilityCategory);
		// System.out.println(Certcategory);
		assertTrue(CE.SPTaxabilityCategory.isDisplayed());
		Thread.sleep(2000);
	}

	public void UpdateCustomerEndDate(String Company, String CodeValue, String EditCustomer, String Cus_End_Date)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		strCustName = "";
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		Thread.sleep(3000);
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, CodeValue);
		Thread.sleep(2000);
		CE.SEARCH.sendKeys(Keys.CONTROL + "a");
		CE.SEARCH.sendKeys(Keys.CONTROL + "x");
		Thread.sleep(2000);
		CE.SEARCH.sendKeys(Keys.CONTROL + "v");
		Thread.sleep(2000);
		if (EditCustomer != "") {
			WebElement customerclick = driver
					.findElement(By.xpath("//td[contains(.,'" + CodeValue + "')]/../td[9]/div/a"));
			JavaScriptscrolltoView(driver, customerclick);
			Thread.sleep(2000);
			JavaScriptclick(driver, customerclick);
			Thread.sleep(5000);
			driver.findElement(By.xpath("//tbody/tr[1]/td/div/ul/li/a[contains(.,'Edit')]")).click();
			waitforElement(90, CE.ENDDATE_CUST, driver);
			JavaScriptclick(driver, CE.ENDDATE_CUST);
			clear(CE.ENDDATE_CUST);
			type(CE.ENDDATE_CUST, Cus_End_Date);
			sendkeysTab(CE.ENDDATE_CUST);
			Thread.sleep(3000);
			click(CE.SAVEANDCLOSE);
			Thread.sleep(5000);
			if (VerifyObject(driver, CE.ERRORMESSAGE)) {
				assertTrue(true);
				JavaScriptclick(driver, CE.OKREMOVECUSTOMER);
				Thread.sleep(2000);
				click(CE.ENDDATE_CUST);
				type(CE.ENDDATE_CUST, xGetDate("MM/dd/yyyy"));
				Thread.sleep(2000);
				sendkeysTab(CE.ENDDATE_CUST);
				Thread.sleep(3000);
				click(CE.SAVEANDCLOSE);
			}
		}
		Thread.sleep(2000);
		click(CE.READYTOSAVE);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}

	}

	@SuppressWarnings("null")
	public void Certificatecitydropdown(String Company, String Customer_Name, String cityvalues,
			String Cert_Exception_StartDate, String Cert_Exception_EndDate, String Certificate_Scope,
			String Product_Code, String Document_Number, String Add_States) throws Exception {
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		Thread.sleep(3000);
		driver.navigate().refresh();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//button[@class='close drawer__btn-close']")) {
			WebElement closeaddcustomer = driver.findElement(By.xpath("//button[@class='close drawer__btn-close']"));
			JavaScriptclick(driver, closeaddcustomer);
			Thread.sleep(2000);
		}
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'View')]",
				driver);
		driver.findElement(By.xpath("//td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'(View)')]"))
				.click();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, CE.ADDCERTIFICATE, driver);
		JavaScriptclick(driver, CE.ADDCERTIFICATE);
		waitforElement(180, CE.CUSTOMERINFOLINK, driver);
		if (!VerifyObject(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN)) {
			JavaScriptclick(driver, CE.CUSTOMERINFOLINK);
		}
		waitforElement(180, CE.SEARCHCUSTOMERADDCERTFSCREEN, driver);
		JavaScriptclick(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN);
		type(CE.SEARCHCUSTOMERADDCERTFSCREEN, Customer_Name);
		waitforElement(180, "//a/span[contains(.,'" + Customer_Name + "')]", driver);
		Thread.sleep(2000);
		WebElement customer = driver.findElement(By.xpath("//a/span[contains(.,'" + Customer_Name + "')]"));
		JavaScriptclick(driver, customer);
		waitforElement(180, CE.CertificateInfoLink, driver);
		JavaScriptclick(driver, CE.CertificateInfoLink);
		waitforElement(180, CE.EXCEPTYPE, driver);
		if (Cert_Exception_StartDate != "") {
			waitforElement(180, CE.EXCEPSTRATDATE, driver);
			clear(CE.EXCEPSTRATDATE);
			type(CE.EXCEPSTRATDATE, Cert_Exception_StartDate);
			click(CE.EXCEPSTRATDATE);
			sendkeysTab(CE.EXCEPSTRATDATE);
			sendkeysTab(CE.EXCEPENDDATE);
		}
		if (Cert_Exception_EndDate != "") {
			clear(CE.EXCEPENDDATE);
			type(CE.EXCEPENDDATE, Cert_Exception_EndDate);
			sendkeysTab(CE.EXCEPENDDATE);
		}
		// Adding the Certificate scope
		if (Certificate_Scope != "") {
			String Cerscope = Certificate_Scope;
			switch (Cerscope) {
			case "Limited":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				String ProdVal = Product_Code;
				waitforElement(180, CE.ProductCodeVal, driver);
				click(CE.ProductCodeVal);
				type(CE.ProductCodeVal, ProdVal);
				Thread.sleep(5000);
				WebElement selectprod = driver
						.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[contains(.,'" + ProdVal + "')]"));
				click(selectprod);
				break;
			case "Single Use":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				Thread.sleep(2000);
				String DocumentValue = Document_Number;
				waitforElement(180, CE.InvoiceNumber, driver);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptclick(driver, CE.InvoiceNumber);
				} else {
					click(CE.InvoiceNumber);
				}
				type(CE.InvoiceNumber, DocumentValue);
				sendkeysTab(CE.InvoiceNumber);
				break;
			case "Blanket":
				selectByValue(CE.CertificateScopeValue, Cerscope);
				Thread.sleep(2000);
				break;
			}
		}
		if (Add_States.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, CE.JURISDICTIONLINK);
			waitforElement(180, CE.ADDJURISDICTION, driver);
			JavaScriptclick(driver, CE.ADDJURISDICTION);
			waitforElement(180, CE.BackToAddCertificate, driver);
			String[] Stateslist = { "Arizona", "Alabama", "Alaska", "Colorado", "Louisiana", "South Carolina",
					"Texas" };
			for (int s = 0; s < Stateslist.length; s++) {
				Select stateSelectbox = new Select(
						driver.findElement(By.xpath("//select[@id='ddlExceptionJurisdiction']")));
				stateSelectbox.selectByVisibleText(Stateslist[s]);
				String state = Stateslist[s];
				switch (state) {
				case "Alaska":
					String[] issuinglist = { "City", "District", "County" };
					for (int l = 0; l < issuinglist.length; l++) {
						String issuelist = issuinglist[l];
						if (issuelist.equalsIgnoreCase("City")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Adak", "Akhiok", "Akiak", "Akutan", "Alakanuk", "Aleknagik", "Allakaket",
									"Ambler", "Anaktuvuk Pass", "Anchorage", "Anderson", "Angoon", "Aniak", "Anvik",
									"Atka", "Atqasuk", "Bethel", "Bettles", "Brevig Mission", "Buckland", "Chefornak",
									"Chevak", "Chignik", "Chuathbaluk", "Clarks Point", "Coffman Cove", "Cold Bay",
									"Cordova", "Craig", "Deering", "Delta Junction", "Dillingham", "Diomede", "Eagle",
									"Eek", "Egegik", "Ekwok", "Elim", "Emmonak", "Fairbanks", "False Pass",
									"Fort Yukon", "Galena", "Gambell", "Golovin", "Goodnews Bay", "Grayling",
									"Gustavus", "Haines", "Holy Cross", "Homer", "Hoonah", "Hooper Bay", "Houston",
									"Hughes", "Huslia", "Hydaburg", "Juneau", "Kachemak", "Kake", "Kaktovik", "Kaltag",
									"Kasaan", "Kenai", "Ketchikan", "Kiana", "King Cove", "Kivalina", "Klawock",
									"Kobuk", "Kodiak", "Kotlik", "Kotzebue", "Koyuk", "Koyukuk", "Kupreanof",
									"Kwethluk", "Larsen Bay", "Lower Kalskag", "Manokotak", "Marshall", "Mcgrath",
									"Mekoryuk", "Mountain Village", "Napakiak", "Napaskiak", "Nenana", "New Stuyahok",
									"Newhalen", "Newtok", "Nightmute", "Nikolai", "Nome", "Nondalton", "Noorvik",
									"North Pole", "Nuiqsut", "Nulato", "Nunam Iqua", "Nunapitchuk", "Old Harbor",
									"Ouzinkie", "Palmer", "Pelican", "Pilot Point", "Pilot Station", "Platinum",
									"Point Hope", "Port Alexander", "Port Heiden", "Port Lions", "Quinhagak", "Ruby",
									"Russian Mission", "Saint George", "Saint Marys", "Saint Michael", "Saint Paul",
									"Sand Point", "Savoonga", "Saxman", "Scammon Bay", "Selawik", "Seldovia", "Seward",
									"Shageluk", "Shaktoolik", "Shishmaref", "Shungnak", "Sitka", "Skagway", "Soldotna",
									"Stebbins", "Tanana", "Teller", "Tenakee Springs", "Thorne Bay", "Togiak",
									"Toksook Bay", "Unalakleet", "Unalaska", "Upper Kalskag", "Utqiagvik", "Valdez",
									"Wainwright", "Wales", "Wasilla", "Whale Pass", "White Mountain", "Whittier",
									"Wrangell", "Yakutat" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
						if (issuelist.equalsIgnoreCase("District")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Native Village Of Goodnews Bay", "Talkeetna Sewer And Water Service Area" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
						if (issuelist.equalsIgnoreCase("County")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Aleutians East Borough", "Aleutians West", "Anchorage Borough", "Bethel",
									"Bristol Bay Borough", "Denali Borough", "Dillingham",
									"Fairbanks North Star Borough", "Haines Borough", "Hoonah-Angoon", "Juneau Borough",
									"Kenai Peninsula Borough", "Ketchikan Gateway Borough", "Kodiak Island Borough",
									"Kusilvak", "Lake And Peninsula Borough", "Matanuska-Susitna Borough", "Nome",
									"North Slope Borough", "Northwest Arctic Borough", "Petersburg",
									"Prince Of Wales-Hyder", "Sitka Borough", "Skagway", "Southeast Fairbanks",
									"Valdez-Cordova", "Wrangell Borough", "Yakutat Borough", "Yukon-Koyukuk" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
					}

					break;
				case "Arizona":
					String[] issuinglist2 = { "City", "District", "County" };
					for (int l = 0; l < issuinglist2.length; l++) {
						String issuelist = issuinglist2[l];
						if (issuelist.equalsIgnoreCase("City")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Apache Junction", " Avondale", " Benson", " Bisbee", " Buckeye",
									" Bullhead City", " Camp Verde", " Carefree", " Casa Grande", " Cave Creek",
									" Chandler", " Chino Valley", " Clarkdale", " Clifton", " Colorado City",
									" Coolidge", " Cottonwood", " Dewey-Humboldt", " Douglas", " Duncan", " Eagar",
									" El Mirage", " Eloy", " Flagstaff", " Florence", " Fountain Hills", " Fredonia",
									" Gila Bend", " Gilbert", " Glendale", " Globe", " Goodyear", " Guadalupe",
									" Hayden", " Holbrook", " Huachuca City", " Jerome", " Kayenta", " Kearny",
									" Kingman", " Lake Havasu City", " Litchfield Park", " Mammoth", " Marana",
									" Maricopa", " Mesa", " Miami", " Nogales", " Oro Valley", " Page",
									" Paradise Valley", " Parker", " Patagonia", " Payson", " Peoria", " Phoenix",
									" Pima", " Pinetop-Lakeside", " Prescott", " Prescott Valley", " Quartzsite",
									" Queen Creek", " Safford", " Sahuarita", " Saint Johns", " San Luis",
									" Scottsdale", " Sedona", " Show Low", " Sierra Vista", " Snowflake", " Somerton",
									" South Tucson", " Springerville", " Star Valley", " Superior", " Surprise",
									" Taylor", " Tempe", " Thatcher", " Tolleson", " Tombstone", " Tuba City",
									" Tucson", " Tusayan", " Wellton", " Wickenburg", " Willcox", " Williams",
									" Winkelman", " Winslow", " Youngtown", " YumaApache Junction", " Avondale",
									" Benson", " Bisbee", " Buckeye", " Bullhead City", " Camp Verde", " Carefree",
									" Casa Grande", " Cave Creek", " Chandler", " Chino Valley", " Clarkdale",
									" Clifton", " Colorado City", " Coolidge", " Cottonwood", " Dewey-Humboldt",
									" Douglas", " Duncan", " Eagar", " El Mirage", " Eloy", " Flagstaff", " Florence",
									" Fountain Hills", " Fredonia", " Gila Bend", " Gilbert", " Glendale", " Globe",
									" Goodyear", " Guadalupe", " Hayden", " Holbrook", " Huachuca City", " Jerome",
									" Kayenta", " Kearny", " Kingman", " Lake Havasu City", " Litchfield Park",
									" Mammoth", " Marana", " Maricopa", " Mesa", " Miami", " Nogales", " Oro Valley",
									" Page", " Paradise Valley", " Parker", " Patagonia", " Payson", " Peoria",
									" Phoenix", " Pima", " Pinetop-Lakeside", " Prescott", " Prescott Valley",
									" Quartzsite", " Queen Creek", " Safford", " Sahuarita", " Saint Johns",
									" San Luis", " Scottsdale", " Sedona", " Show Low", " Sierra Vista", " Snowflake",
									" Somerton", " South Tucson", " Springerville", " Star Valley", " Superior",
									" Surprise", " Taylor", " Tempe", " Thatcher", " Tolleson", " Tombstone",
									" Tuba City", " Tucson", " Tusayan", " Wellton", " Wickenburg", " Willcox",
									" Williams", " Winkelman", " Winslow", " Youngtown", " Yuma" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
						if (issuelist.equalsIgnoreCase("District")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Ak-Chin", "ArizonaSportsAndTourismAuthority", "CocopahIndianReservation",
									"ColoradoRiverIndianReservation", "CornerstoneAndQueenCreekDistrict",
									"FortMcdowell-YavapaiIndianReservation", "FortMojaveIndianReservation",
									"FortYuma-QuechanIndianRes", "GilaRiverIndianReservation", "HavasupaiIndianRes",
									"HopiIndianReservation", "HualapaiIndianReservation",
									"Kaibab-PaiuteIndianReservation", "KayentaTownship", "NavajoIndianReservation",
									"Pascua-YaquiIndianRes", "QueenCreekMarketplaceDistrict", "RioNuevoDistrict",
									"SaltRiverPima-MaricopaIndianReservation", "SanCarlosApacheIndianReservation",
									"SanJuanSouthPauiteIndianReservation", "To'nanees'diziLocalGovernmentDistrict",
									"TohonoO'odhamIndianReservation", "TontoApacheIndianRes",
									"UniversityOfPhoenixStadium", "WhiteMountainApacheIndianReservation",
									"YavapaiApacheIndianReservation", "Yavapai-PrescottIndianReservation" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
						if (issuelist.equalsIgnoreCase("County")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Apache", "Cochise", "Coconino", "Gila", "Graham", "Greenlee", "La Paz",
									"Maricopa", "Mohave", "Navajo", "Pima", "Pinal", "Santa Cruz", "Yavapai", "Yuma" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
					}
					break;
				/*
				 * case "Alabama": String[] issuinglist3 = { "City", "District", "County" }; for
				 * (int l = 0; l < issuinglist3.length; l++) { String issuelist =
				 * issuinglist3[l]; if (issuelist.equalsIgnoreCase("City")) { Select Jurisdtype
				 * = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
				 * Jurisdtype.selectByVisibleText(issuelist); Thread.sleep(5000);
				 * List<WebElement> length = driver
				 * .findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
				 * List<String> all_elements_text = new ArrayList<>();
				 * System.out.println(state); System.out.println(issuelist); for (int i = 0; i <
				 * length.size(); i++) { if (!length.get(i).getText().isEmpty()) {
				 * all_elements_text.add(length.get(i).getText()); } }
				 * System.out.println(all_elements_text); List<String> all_elements_text1 = new
				 * ArrayList<>(); String C[] = { "Abbeville", "Adamsville", "Addison", "Akron",
				 * "Alabaster", "Albertville", "Alexander City", "Aliceville", "Allgood",
				 * "Altoona", "Andalusia", "Anderson", "Anniston", "Arab", "Ardmore", "Argo",
				 * "Ariton", "Arley", "Ashford", "Ashland", "Ashville", "Athens", "Atmore",
				 * "Attalla", "Auburn", "Autaugaville", "Avon", "Babbie", "Baileyton",
				 * "Bakerhill", "Banks", "Bay Minette", "Bayou La Batre", "Bear Creek",
				 * "Beatrice", "Beaverton", "Belk", "Benton", "Berry", "Bessemer",
				 * "Billingsley", "Birmingham", "Black", "Blountsville", "Blue Springs", "Boaz",
				 * "Boligee", "Bon Air", "Brantley", "Brent", "Brewton", "Bridgeport",
				 * "Brighton", "Brilliant", "Brookside", "Brookwood", "Brundidge", "Butler",
				 * "Calera", "Camden", "Camp Hill", "Carbon Hill", "Cardiff", "Carolina",
				 * "Carrollton", "Castleberry", "Cedar Bluff", "Center Point", "Centre",
				 * "Centreville", "Chatom", "Chelsea", "Cherokee", "Chickasaw", "Childersburg",
				 * "Citronelle", "Clanton", "Clay", "Clayhatchee", "Clayton", "Cleveland",
				 * "Clio", "Coaling", "Coffee Springs", "Coffeeville", "Coker", "Collinsville",
				 * "Colony", "Columbia", "Columbiana", "Coosada", "Cordova", "Cottonwood",
				 * "County Line", "Courtland", "Cowarts", "Creola", "Crossville", "Cuba",
				 * "Cullman", "Cusseta", "Dadeville", "Daleville", "Daphne", "Dauphin Island",
				 * "Daviston", "Dayton", "Deatsville", "Decatur", "Demopolis", "Detroit",
				 * "Dodge City", "Dora", "Dothan", "Double Springs", "Douglas", "Dozier",
				 * "Dutton", "East Brewton", "Eclectic", "Edwardsville", "Elba", "Elberta",
				 * "Eldridge", "Elkmont", "Elmore", "Emelle", "Enterprise", "Epes",
				 * "Ethelsville", "Eufaula", "Eutaw", "Eva", "Evergreen", "Excel", "Fairfield",
				 * "Fairhope", "Fairview", "Falkville", "Faunsdale", "Fayette", "Five Points",
				 * "Flomaton", "Florala", "Florence", "Foley", "Forkland", "Fort Deposit",
				 * "Fort Payne", "Franklin", "Frisco City", "Fruithurst", "Fulton",
				 * "Fultondale", "Fyffe", "Gadsden", "Gainesville", "Gantt", "Garden City",
				 * "Gardendale", "Gaylesville", "Geiger", "Geneva", "Georgiana", "Geraldine",
				 * "Gilbertown", "Glen Allen", "Glencoe", "Glenwood", "Goldville", "Good Hope",
				 * "Goodwater", "Gordo", "Gordon", "Gordonville", "Goshen", "Grant",
				 * "Graysville", "Greensboro", "Greenville", "Grimes", "Grove Hill", "Gu-Win",
				 * "Guin", "Gulf Shores", "Guntersville", "Gurley", "Hackleburg", "Haleburg",
				 * "Haleyville", "Hamilton", "Hammondville", "Hanceville", "Harpersville",
				 * "Hartford", "Hartselle", "Hayden", "Hayneville", "Headland", "Heath",
				 * "Heflin", "Helena", "Henagar", "Highland Lake", "Hillsboro", "Hobson City",
				 * "Hodges", "Hokes Bluff", "Holly Pond", "Hollywood", "Homewood", "Hoover",
				 * "Horn Hill", "Hueytown", "Huntsville", "Hurtsboro", "Hytop", "Ider",
				 * "Indian Springs Village", "Irondale", "Jackson", "Jacksons' Gap",
				 * "Jacksonville", "Jasper", "Jemison", "Kansas", "Kellyton", "Kennedy",
				 * "Killen", "Kimberly", "Kinsey", "Kinston", "Lafayette", "Lake View",
				 * "Lakeview", "Lanett", "Langston", "Leeds", "Leesburg", "Leighton", "Lester",
				 * "Level Plains", "Lexington", "Libertyville", "Lincoln", "Linden",
				 * "Lineville", "Lipscomb", "Lisman", "Littleville", "Livingston", "Loachapoka",
				 * "Lockhart", "Locust Fork", "Louisville", "Lowndesboro", "Loxley", "Luverne",
				 * "Lynn", "Madison", "Madrid", "Magnolia Springs", "Malvern", "Maplesville",
				 * "Margaret", "Marion", "Maytown", "Mcintosh", "Mckenzie", "Mcmullen",
				 * "Memphis", "Mentone", "Midfield", "Midland City", "Midway", "Millbrook",
				 * "Millport", "Millry", "Mobile", "Monroeville", "Montevallo", "Montgomery",
				 * "Moody", "Mooresville", "Morris", "Mosses", "Moulton", "Moundville",
				 * "Mount Vernon", "Mountain Brook", "Mulga", "Munford", "Muscle Shoals",
				 * "Myrtlewood", "Napier Field", "Natural Bridge", "Nauvoo", "Nectar",
				 * "Needham", "New Brockton", "New Hope", "New Site", "Newbern", "Newton",
				 * "Newville", "North Courtland", "North Johns", "Northport", "Notasulga",
				 * "Oak Grove", "Oak Hill", "Oakman", "Odenville", "Ohatchee", "Oneonta",
				 * "Onycha", "Opelika", "Opp", "Orange Beach", "Orrville", "Owens Cross Roads",
				 * "Oxford", "Ozark", "Paint Rock", "Parrish", "Pelham", "Pell City",
				 * "Pennington", "Perdido Beach", "Petrey", "Phenix City", "Phil Campbell",
				 * "Pickensville", "Piedmont", "Pike Road", "Pinckard", "Pine Apple",
				 * "Pine Hill", "Pine Ridge", "Pinson", "Pisgah", "Pleasant Grove",
				 * "Pleasant Groves", "Pollard", "Powell", "Prattville", "Priceville",
				 * "Prichard", "Providence", "Ragland", "Rainbow City", "Rainsville",
				 * "Ranburne", "Red Bay", "Red Level", "Reece City", "Reform", "Rehobeth",
				 * "Repton", "Ridgeville", "River Falls", "Riverside", "Riverview", "Roanoke",
				 * "Robertsdale", "Rockford", "Rogersville", "Rosa", "Russellville", "Rutledge",
				 * "Saint Florian", "Samson", "Sand Rock", "Sanford", "Saraland", "Sardis City",
				 * "Satsuma", "Scottsboro", "Section", "Selma", "Semmes", "Sheffield", "Shiloh",
				 * "Shorter", "Silas", "Silverhill", "Sipsey", "Skyline", "Slocomb",
				 * "Smiths Station", "Snead", "Somerville", "South Vinemont", "Southside",
				 * "Spanish Fort", "Springville", "Steele", "Stevenson", "Sulligent", "Sumiton",
				 * "Summerdale", "Susan Moore", "Sweet Water", "Sylacauga", "Sylvan Springs",
				 * "Sylvania", "Talladega", "Talladega Springs", "Tallassee", "Tarrant",
				 * "Taylor", "Thomaston", "Thomasville", "Thorsby", "Town Creek", "Toxey",
				 * "Trafford", "Triana", "Trinity", "Troy", "Trussville", "Tuscaloosa",
				 * "Tuscumbia", "Tuskegee", "Twin", "Union", "Union Grove", "Union Springs",
				 * "Uniontown", "Valley", "Valley Grande", "Valley Head", "Vance", "Vernon",
				 * "Vestavia Hills", "Vina", "Vincent", "Vredenburgh", "Wadley", "Waldo",
				 * "Walnut Grove", "Warrior", "Waterloo", "Waverly", "Weaver", "Webb",
				 * "Wedowee", "West Blocton", "West Jefferson", "West Point", "Westover",
				 * "Wetumpka", "White Hall", "Wilsonville", "Wilton", "Winfield", "Woodland",
				 * "Woodstock", "Woodville", "Yellow Bluff", "York" }; for (String text : C) {
				 * all_elements_text1.add(text); } all_elements_text1.equals(all_elements_text);
				 * } if (issuelist.equalsIgnoreCase("District")) { Select Jurisdtype = new
				 * Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
				 * Jurisdtype.selectByVisibleText(issuelist); Thread.sleep(5000);
				 * List<WebElement> length = driver
				 * .findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
				 * List<String> all_elements_text = new ArrayList<>();
				 * System.out.println(state); System.out.println(issuelist); for (int i = 0; i <
				 * length.size(); i++) { if (!length.get(i).getText().isEmpty()) {
				 * all_elements_text.add(length.get(i).getText()); } }
				 * System.out.println(all_elements_text); List<String> all_elements_text1 = new
				 * ArrayList<>(); String C[] = { "Abbeville Police Jurisdiction",
				 * "Adamsville Police Jurisdiction", "Addison Police Jurisdiction",
				 * "Akron Police Jurisdiction", "Albertville Police Jurisdiction",
				 * "Alexander City Police Jurisdiction", "Aliceville Police Jurisdiction",
				 * "Allgood Police Jurisdiction", "Altoona Police Jurisdiction",
				 * "Anderson Police Jurisdiction", "Anniston Police Jurisdiction",
				 * "Arab Police Jurisdiction", "Ariton Police Jurisdiction",
				 * "Arley Police Jurisdiction", "Ashford Police Jurisdiction",
				 * "Ashland Police Jurisdiction", "Ashville Police Jurisdiction",
				 * "Atmore Police Jurisdiction", "Bakerhill Police Jurisdiction",
				 * "Bay Minette Police Jurisdiction", "Bayou La Batre Police Jurisdiction",
				 * "Bear Creek Police Jurisdiction", "Beatrice Police Jurisdiction",
				 * "Beaverton Police Jurisdiction", "Berry Police Jurisdiction",
				 * "Black Police Jurisdiction", "Blountsville Police Jurisdiction",
				 * "Boligee Police Jurisdiction", "Brantley Police Jurisdiction",
				 * "Brewton Police Jurisdiction", "Bridgeport Police Jurisdiction",
				 * "Brighton Police Jurisdiction", "Brilliant Police Jurisdiction",
				 * "Brookside Police Jurisdiction", "Brookwood Police Jurisdiction",
				 * "Brundidge Police Jurisdiction", "Butler Police Jurisdiction",
				 * "Calera Police Jurisdiction", "Camden Police Jurisdiction",
				 * "Camp Hill Police Jurisdiction", "Carbon Hill Police Jurisdiction",
				 * "Carrollton Police Jurisdiction", "Castleberry Police Jurisdiction",
				 * "Centreville Police Jurisdiction", "Chatom Police Jurisdiction",
				 * "Cherokee Police Jurisdiction", "Chickasaw Police Jurisdiction",
				 * "Childersburg Police Jurisdiction", "Citronelle Police Jurisdiction",
				 * "Clayhatchee Police Jurisdiction", "Clayton Police Jurisdiction",
				 * "Cleveland Police Jurisdiction", "Clio Police Jurisdiction",
				 * "Coaling Police Jurisdiction", "Coffeeville Police Jurisdiction",
				 * "Coker Police Jurisdiction", "Collinsville Police Jurisdiction",
				 * "Columbia Police Jurisdiction", "Cooperative District",
				 * "Coosada Police Jurisdiction", "Cordova Police Jurisdiction",
				 * "Cottonwood Police Jurisdiction", "Courtland Police Jurisdiction",
				 * "Creola Police Jurisdiction", "Crossville Police Jurisdiction",
				 * "Cuba Police Jurisdiction", "Cusseta Police Jurisdiction",
				 * "Daleville Police Jurisdiction", "Dauphin Island Police Jurisdiction",
				 * "Daviston Police Jurisdiction", "Decatur Police Jurisdiction",
				 * "Demopolis Police Jurisdiction", "Detroit Police Jurisdiction",
				 * "Double Springs Police Jurisdiction", "Douglas Police Jurisdiction",
				 * "Dozier Police Jurisdiction", "East Brewton Police Jurisdiction",
				 * "Eastern Shore Centre", "Eclectic Police Jurisdiction",
				 * "Edwardsville Police Jurisdiction", "Elba Police Jurisdiction",
				 * "Elberta Police Jurisdiction", "Eldridge Police Jurisdiction",
				 * "Elmore Police Jurisdiction", "Enterprise Police Jurisdiction",
				 * "Eutaw Police Jurisdiction", "Evergreen Police Jurisdiction",
				 * "Excel Police Jurisdiction", "Fairfield Police Jurisdiction",
				 * "Fairhope Police Jurisdiction", "Falkville Police Jurisdiction",
				 * "Faunsdale Police Jurisdiction", "Fayette Police Jurisdiction",
				 * "Five Points Police Jurisdiction", "Flomaton Police Jurisdiction",
				 * "Florala Police Jurisdiction",
				 * "Foley Holdings Public Facilities Cooperative District",
				 * "Foley Police Jurisdiction",
				 * "Foley Square Ii Public Facilities Cooperative District",
				 * "Foley Square Public Facilities Cooperative District",
				 * "Forkland Police Jurisdiction", "Fort Deposit Police Jurisdiction",
				 * "Franklin Police Jurisdiction", "Frisco City Police Jurisdiction",
				 * "Fulton Police Jurisdiction", "Fultondale Police Jurisdiction",
				 * "Fyffe Police Jurisdiction", "Gainesville Police Jurisdiction",
				 * "Gantt Police Jurisdiction", "Gateway Cooperative District",
				 * "Gaylesville Police Jurisdiction", "Geiger Police Jurisdiction",
				 * "Geneva Police Jurisdiction", "Georgiana Police Jurisdiction",
				 * "Geraldine Police Jurisdiction", "Gilbertown Police Jurisdiction",
				 * "Glencoe Police Jurisdiction", "Glenwood Police Jurisdiction",
				 * "Goldville Police Jurisdiction", "Goodwater Police Jurisdiction",
				 * "Gordo Police Jurisdiction", "Gordon Police Jurisdiction",
				 * "Goshen Police Jurisdiction", "Grant Police Jurisdiction",
				 * "Greensboro Police Jurisdiction", "Grimes Police Jurisdiction",
				 * "Grove Hill Police Jurisdiction", "Guin Police Jurisdiction",
				 * "Gulf Shores Police Jurisdiction", "Guntersville Police Jurisdiction",
				 * "Hackleburg Police Jurisdiction", "Haleyville Police Jurisdiction",
				 * "Hamilton Police Jurisdiction", "Hammondville Police Jurisdiction",
				 * "Hanceville Police Jurisdiction", "Harpersville Police Jurisdiction",
				 * "Hartford Police Jurisdiction", "Hayden Police Jurisdiction",
				 * "Hayneville Police Jurisdiction", "Headland Police Jurisdiction",
				 * "Heflin Police Jurisdiction", "Helena Police Jurisdiction",
				 * "Henagar Police Jurisdiction", "Hillsboro Police Jurisdiction",
				 * "Hobson City Police Jurisdiction", "Hodges Police Jurisdiction",
				 * "Hokes Bluff Police Jurisdiction", "Hollywood Police Jurisdiction",
				 * "Hoover Police Jurisdiction", "Hurtsboro Police Jurisdiction",
				 * "Ider Police Jurisdiction", "Irondale Police Jurisdiction",
				 * "Jackson Police Jurisdiction", "Jacksons' Gap Police Jurisdiction",
				 * "Jacksonville Police Jurisdiction", "Jasper Police Jurisdiction",
				 * "Jemison Police Jurisdiction", "Kansas Police Jurisdiction",
				 * "Kennedy Police Jurisdiction", "Killen Police Jurisdiction",
				 * "Kimberly Police Jurisdiction", "Kinsey Police Jurisdiction",
				 * "Kinston Police Jurisdiction", "Lafayette Police Jurisdiction",
				 * "Lake View Police Jurisdiction", "Lanett Police Jurisdiction",
				 * "Leesburg Police Jurisdiction", "Leighton Police Jurisdiction",
				 * "Level Plains Police Jurisdiction", "Lexington Police Jurisdiction",
				 * "Lincoln Police Jurisdiction", "Linden Police Jurisdiction",
				 * "Lineville Police Jurisdiction", "Lipscomb Police Jurisdiction",
				 * "Livingston Police Jurisdiction", "Lockhart Police Jurisdiction",
				 * "Locust Fork Police Jurisdiction", "Louisville Police Jurisdiction",
				 * "Loxley Police Jurisdiction", "Luverne Police Jurisdiction",
				 * "Lynn Police Jurisdiction", "Madison Police Jurisdiction",
				 * "Magnolia Springs Police Jurisdiction", "Malvern Police Jurisdiction",
				 * "Maplesville Police Jurisdiction", "Margaret Police Jurisdiction",
				 * "Marion Police Jurisdiction", "Mcintosh Police Jurisdiction",
				 * "Mckenzie Police Jurisdiction",
				 * "Mckenzie Village Public Facilities Cooperative District",
				 * "Mentone Police Jurisdiction", "Midland City Police Jurisdiction",
				 * "Midway Police Jurisdiction", "Millbrook Police Jurisdiction",
				 * "Millport Police Jurisdiction", "Millry Police Jurisdiction",
				 * "Mobile Police Jurisdiction", "Monroeville Police Jurisdiction",
				 * "Montgomery Police Jurisdiction", "Morris Police Jurisdiction",
				 * "Mosses Police Jurisdiction", "Moulton Police Jurisdiction",
				 * "Moundville Police Jurisdiction", "Mount Vernon Police Jurisdiction",
				 * "Munford Police Jurisdiction", "Muscle Shoals Police Jurisdiction",
				 * "Myrtlewood Police Jurisdiction", "Napier Field Police Jurisdiction",
				 * "Nauvoo Police Jurisdiction", "Needham Police Jurisdiction",
				 * "New Brockton Police Jurisdiction", "New Site Police Jurisdiction",
				 * "Newbern Police Jurisdiction", "Newton Police Jurisdiction",
				 * "Newville Police Jurisdiction", "North Courtland Police Jurisdiction",
				 * "Northport Police Jurisdiction", "Notasulga Police Jurisdiction",
				 * "Oak Grove Police Jurisdiction", "Oakman Police Jurisdiction",
				 * "Odenville Police Jurisdiction", "Ohatchee Police Jurisdiction",
				 * "Oneonta Police Jurisdiction", "Opelika Police Jurisdiction",
				 * "Opp Police Jurisdiction", "Orange Beach Police Jurisdiction",
				 * "Orrville Police Jurisdiction", "Oxford Police Jurisdiction",
				 * "Ozark Police Jurisdiction", "Parrish Police Jurisdiction",
				 * "Pennington Police Jurisdiction", "Phenix City Police Jurisdiction",
				 * "Pickensville Police Jurisdiction", "Piedmont Police Jurisdiction",
				 * "Pike Road Police Jurisdiction", "Pinckard Police Jurisdiction",
				 * "Pine Hill Police Jurisdiction", "Pisgah Police Jurisdiction",
				 * "Powell Police Jurisdiction", "Priceville Police Jurisdiction",
				 * "Prichard Police Jurisdiction", "Ragland Police Jurisdiction",
				 * "Rainbow City Police Jurisdiction", "Rainsville Police Jurisdiction",
				 * "Ranburne Police Jurisdiction", "Red Bay Police Jurisdiction",
				 * "Red Level Police Jurisdiction", "Reece City Police Jurisdiction",
				 * "Reform Police Jurisdiction", "Rehobeth Police Jurisdiction",
				 * "Repton Police Jurisdiction", "Ridgeville Police Jurisdiction",
				 * "Riverside Police Jurisdiction", "Riverview Police Jurisdiction",
				 * "Robertsdale Police Jurisdiction", "Rockford Police Jurisdiction",
				 * "Rogersville Police Jurisdiction", "Rutledge Police Jurisdiction",
				 * "Saint Florian Police Jurisdiction", "Samson Police Jurisdiction",
				 * "Sand Rock Police Jurisdiction", "Sanford Police Jurisdiction",
				 * "Saraland Police Jurisdiction", "Sardis City Police Jurisdiction",
				 * "Satsuma Police Jurisdiction", "Scottsboro Police Jurisdiction",
				 * "Section Police Jurisdiction", "Selma Police Jurisdiction",
				 * "Semmes Police Jurisdiction",
				 * "Shoe Station Public Facilities Cooperative District",
				 * "Shorter Police Jurisdiction", "Silas Police Jurisdiction",
				 * "Silverhill Police Jurisdiction", "Sipsey Police Jurisdiction",
				 * "Skyline Police Jurisdiction", "Slocomb Police Jurisdiction",
				 * "Snead Police Jurisdiction", "Southside Police Jurisdiction",
				 * "Spanish Fort Police Jurisdiction", "Steele Police Jurisdiction",
				 * "Stevenson Police Jurisdiction", "Sulligent Police Jurisdiction",
				 * "Sumiton Police Jurisdiction", "Summerdale Police Jurisdiction",
				 * "Susan Moore Police Jurisdiction", "Sweet Water Police Jurisdiction",
				 * "Sylvan Springs Police Jurisdiction", "Sylvania Police Jurisdiction",
				 * "Talladega Police Jurisdiction", "Tallassee Police Jurisdiction",
				 * "Tanger Outlet Public Facilities Cooperative District",
				 * "Taylor Police Jurisdiction", "Thomaston Police Jurisdiction",
				 * "Thomasville Police Jurisdiction", "Thorsby Police Jurisdiction",
				 * "Town Center", "Town Creek Police Jurisdiction", "Toxey Police Jurisdiction",
				 * "Trafford Police Jurisdiction", "Triana Police Jurisdiction",
				 * "Troy Police Jurisdiction", "Tuscaloosa Police Jurisdiction",
				 * "Tuscumbia Police Jurisdiction", "Tuskegee Police Jurisdiction",
				 * "Union Grove Police Jurisdiction", "Union Police Jurisdiction",
				 * "Union Springs Police Jurisdiction", "Uniontown Police Jurisdiction",
				 * "Valley Grande Police Jurisdiction", "Valley Head Police Jurisdiction",
				 * "Valley Police Jurisdiction", "Vance Police Jurisdiction",
				 * "Vernon Police Jurisdiction", "Vina Police Jurisdiction",
				 * "Vincent Police Jurisdiction", "Wadley Police Jurisdiction",
				 * "Waldo Police Jurisdiction", "Warrior Police Jurisdiction",
				 * "Waterloo Police Jurisdiction", "Waverly Police Jurisdiction",
				 * "Weaver Police Jurisdiction", "Webb Police Jurisdiction",
				 * "Wedowee Police Jurisdiction", "West Blocton Police Jurisdiction",
				 * "Westover Police Jurisdiction", "Wetumpka Police Jurisdiction",
				 * "White Hall Police Jurisdiction", "Wilsonville Police Jurisdiction",
				 * "Wilton Police Jurisdiction", "Winfield Police Jurisdiction",
				 * "Wolf Bay Lodge Public Facilities Cooperative District",
				 * "Woodstock Police Jurisdiction", "Woodville Police Jurisdiction",
				 * "Yellow Bluff Police Jurisdiction", "York Police Jurisdiction" }; for (String
				 * text : C) { all_elements_text1.add(text); }
				 * all_elements_text1.equals(all_elements_text); } if
				 * (issuelist.equalsIgnoreCase("County")) { Select Jurisdtype = new
				 * Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
				 * Jurisdtype.selectByVisibleText(issuelist); Thread.sleep(5000);
				 * List<WebElement> length = driver
				 * .findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
				 * List<String> all_elements_text = new ArrayList<>();
				 * System.out.println(state); System.out.println(issuelist); for (int i = 0; i <
				 * length.size(); i++) { if (!length.get(i).getText().isEmpty()) {
				 * all_elements_text.add(length.get(i).getText()); } }
				 * System.out.println(all_elements_text); List<String> all_elements_text1 = new
				 * ArrayList<>(); String C[] = { "Autauga", "Baldwin", "Barbour", "Bibb",
				 * "Blount", "Bullock", "Butler", "Calhoun", "Chambers", "Cherokee", "Chilton",
				 * "Choctaw", "Clarke", "Clay", "Cleburne", "Coffee", "Colbert", "Conecuh",
				 * "Coosa", "Covington", "Crenshaw", "Cullman", "Dale", "Dallas", "Dekalb",
				 * "Elmore", "Escambia", "Etowah", "Fayette", "Franklin", "Geneva", "Greene",
				 * "Hale", "Henry", "Houston", "Jackson", "Jefferson", "Lamar", "Lauderdale",
				 * "Lawrence", "Lee", "Limestone", "Lowndes", "Macon", "Madison", "Marengo",
				 * "Marion", "Marshall", "Mobile", "Monroe", "Montgomery", "Morgan", "Perry",
				 * "Pickens", "Pike", "Randolph", "Russell", "Shelby", "St Clair", "Sumter",
				 * "Talladega", "Tallapoosa", "Tuscaloosa", "Walker", "Washington", "Wilcox",
				 * "Winston" }; for (String text : C) { all_elements_text1.add(text); }
				 * all_elements_text1.equals(all_elements_text); } } break;
				 */case "Colorado":
					String[] issuinglist4 = { "City", "District", "County" };
					for (int l = 0; l < issuinglist4.length; l++) {
						String issuelist = issuinglist4[l];
						if (issuelist.equalsIgnoreCase("City")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Agate", "Aguilar", "Akron", "Alamosa", "Allenspark", "Allison", "Alma",
									"Almont", "Amherst", "Anton", "Antonito", "Arapahoe", "Arboles", "Arlington",
									"Arriba", "Arvada", "Aspen", "Aspen Park", "Association Camp", "Atwood", "Ault",
									"Aurora", "Avon", "Avondale", "Bailey", "Barr Lake", "Basalt", "Battlement Mesa",
									"Bayfield", "Bedrock", "Bellvue", "Bennett", "Bergen Park", "Berthoud", "Bethune",
									"Beulah", "Black Forest", "Black Hawk", "Blanca", "Blende", "Blue River",
									"Bonanza City", "Boncarbo", "Bond", "Bondad", "Boone", "Boulder", "Bow Mar",
									"Bowie", "Boyero", "Brandon", "Branson", "Breckenridge", "Briggsdale", "Brighton",
									"Bristol", "Broadmoor", "Brookside", "Broomfield", "Brush", "Buckingham",
									"Buena Vista", "Buffalo Creek", "Buford", "Burlington", "Burns", "Byers", "Cahone",
									"Calhan", "Cameo", "Campion", "Campo", "Canon City", "Capulin", "Carbonate",
									"Carbondale", "Carr", "Cascade", "Castle Pines", "Castle Rock", "Cedaredge",
									"Centennial", "Center", "Central City", "Chama", "Cheraw", "Cherry Hills Village",
									"Cheyenne Wells", "Chimney Rock", "Chipita Park", "Chivington", "Chromo",
									"Cimarron", "Clark", "Clifton", "Climax", "Coal Creek", "Coaldale", "Coalmont",
									"Cokedale", "Collbran", "Colorado City", "Colorado Springs", "Columbine",
									"Columbine Valley", "Commerce City", "Como", "Conejos", "Conifer", "Cope",
									"Copper Mountain", "Cordillera", "Cortez", "Cotopaxi", "Cowdrey", "Craig",
									"Crawford", "Creede", "Crested Butte", "Crestone", "Cripple Creek", "Critchell",
									"Crook", "Crowley", "Cuchara", "Dacono", "Dailey", "De Beque", "Deer Trail",
									"Del Norte", "Delhi", "Delta", "Denver", "Deora", "Dillon", "Dinosaur", "Divide",
									"Dolores", "Dotsero", "Dove Creek", "Downieville", "Drake", "Dumont", "Dupont",
									"Durango", "Eads", "Eagle", "East Portal", "Eastlake", "Eaton", "Eckley",
									"Edgewater", "Edwards", "Egnar", "El Jebel", "El Moro", "Elba", "Elbert", "Eldora",
									"Eldorado Springs", "Elizabeth", "Elk Springs", "Ellicott", "Empire", "Englewood",
									"Ent Afb", "Erie", "Estes Park", "Evans", "Evergreen", "Fairplay", "Falcon",
									"Farisita", "Federal Heights", "Firestone", "Flagler", "Fleming", "Florence",
									"Florissant", "Fort Carson", "Fort Collins", "Fort Garland", "Fort Logan",
									"Fort Lupton", "Fort Lyon", "Fort Morgan", "Fountain", "Fowler", "Foxfield",
									"Foxton", "Franktown", "Fraser", "Frederick", "Frisco", "Fruita", "Galeton",
									"Garcia", "Garden City", "Gardner", "Garfield", "Gateway", "Genoa", "Georgetown",
									"Gilcrest", "Gill", "Gilman", "Gilsonite", "Glade Park", "Glen Haven", "Glendale",
									"Glendevey", "Glenwood Springs", "Gold Hill", "Golden", "Goodrich", "Gould",
									"Granada", "Granby", "Grand Junction", "Grand Lake", "Granite", "Grant", "Greeley",
									"Green Mountain", "Green Mountain Falls", "Greenwood Village", "Greystone",
									"Grover", "Guffey", "Gulnare", "Gunnison", "Gypsum", "Hale", "Hamilton", "Hartman",
									"Hartsel", "Hasty", "Haswell", "Haxtun", "Hayden", "Heeney", "Henderson",
									"Hereford", "Hermosa", "Hesperus", "Highlands Ranch", "Hillrose", "Hillside",
									"Hoehne", "Holly", "Holyoke", "Homelake", "Hooper", "Hot Sulphur Springs",
									"Hotchkiss", "Howard", "Hoyt", "Hudson", "Hugo", "Hygiene", "Idaho Springs",
									"Idalia", "Idledale", "Ignacio", "Iliff", "Indian Hills", "Ione", "Irondale",
									"Ivywild", "Jamestown", "Jansen", "Jaroso", "Jefferson", "Joes", "Johnstown",
									"Julesburg", "Karval", "Keenesburg", "Keota", "Kersey", "Keystone", "Kim", "Kiowa",
									"Kirk", "Kit Carson", "Kittredge", "Kremmling", "Kutch", "La Garita", "La Jara",
									"La Junta", "La Salle", "La Veta", "Lafayette", "Laird", "Lake City", "Lake George",
									"Lakeside", "Lakewood", "Lamar", "Laporte", "Larkspur", "Las Animas", "Last Chance",
									"Lawson", "Lay", "Lazear", "Leadville", "Lewis", "Leyden", "Limon", "Lindon",
									"Littleton", "Livermore", "Lochbuie", "Log Lane Village", "Loma", "Lone Tree",
									"Longmont", "Lookout Mountain", "Louisville", "Louviers", "Loveland", "Lucerne",
									"Lycan", "Lyons", "Mack", "Maher", "Malta", "Manassa", "Mancos", "Manitou Springs",
									"Manzanola", "Marble", "Marvel", "Masonville", "Masters", "Matheson", "Maybell",
									"Mcclave", "Mccoy", "Mead", "Meeker", "Meredith", "Merino", "Mesa", "Mesa Verde",
									"Mesita", "Midway", "Milliken", "Milner", "Minturn", "Model", "Moffat", "Molina",
									"Monte Vista", "Montezuma", "Montrose", "Monument", "Morrison", "Mosca",
									"Mount Crested Butte", "Mountain View", "Mountain Village", "Nathrop", "Naturita",
									"Nederland", "New Castle", "Niwot", "North Avondale", "North Pole", "Northglenn",
									"Norwood", "Nucla", "Nunn", "Oak Creek", "Ohio", "Olathe", "Olney Springs", "Ophir",
									"Orchard", "Orchard City", "Ordway", "Otis", "Ouray", "Ovid", "Padroni",
									"Pagosa Springs", "Palisade", "Palmer Lake", "Paoli", "Paonia", "Parachute",
									"Paradox", "Parkdale", "Parker", "Parlin", "Parshall", "Peaceful Valley", "Peetz",
									"Penrose", "Peyton", "Phippsburg", "Pierce", "Pine", "Pinecliffe",
									"Pinewood Springs", "Pitkin", "Placerville", "Platner", "Platoro", "Platteville",
									"Pleasant View", "Poncha Springs", "Portland", "Powderhorn", "Pritchett", "Proctor",
									"Prospect Valley", "Pryor", "Pueblo", "Pueblo West", "Radium", "Ramah", "Rand",
									"Rangely", "Raymer", "Red Cliff", "Red Feather Lakes", "Red Wing", "Redstone",
									"Redvale", "Rico", "Ridgway", "Rifle", "Rio Blanco", "Rockvale", "Rocky Ford",
									"Roggen", "Rollinsville", "Romeo", "Rosita", "Rush", "Rye", "Saguache", "Salida",
									"San Acacio", "San Luis", "San Pablo", "Sanford", "Sapinero", "Sargents", "Sawpit",
									"Schriever Afb", "Security-Widefield", "Sedalia", "Sedgwick", "Segundo", "Seibert",
									"Severance", "Shawnee", "Sheridan", "Sheridan Lake", "Silt", "Silver Cliff",
									"Silver Plume", "Silverthorne", "Silverton", "Simla", "Slater", "Slick Rock",
									"Snowmass", "Snowmass Village", "Snyder", "Somerset", "South Fork", "Springfield",
									"Starkville", "Steamboat Springs", "Sterling", "Stone City", "Stoneham",
									"Stonewall", "Stonington", "Strasburg", "Stratton", "Sugar City", "Superior",
									"Swink", "Tabernash", "Telluride", "Texas Creek", "Thatcher", "Thomasville",
									"Thornton", "Timnath", "Timpas", "Tincup", "Tiny Town", "Toponas", "Towaoc",
									"Towner", "Trinchera", "Trinidad", "Twin Lakes", "Two Buttes", "Uravan",
									"Utleyville", "Vail", "Valdez", "Vernon", "Victor", "Vilas", "Villa Grove",
									"Villegreen", "Vineland", "Virginia Dale", "Vona", "Wagon Wheel Gap", "Walden",
									"Walsenburg", "Walsh", "Ward", "Watkins", "Wattenburg", "Weldona", "Wellington",
									"Wellsville", "West Village", "Westcliffe", "Westminster", "Weston", "Wetmore",
									"Wheat Ridge", "Whitewater", "Wiggins", "Wild Horse", "Wiley", "Willard",
									"Williamsburg", "Windsor", "Winter Park", "Wolcott", "Woodland Park", "Woodrow",
									"Woody Creek", "Wray", "Yampa", "Yellow Jacket", "Yoder", "Yuma" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
						if (issuelist.equalsIgnoreCase("District")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Alamosa County Local Marketing District", "Arista Improvement District",
									"Arrowhead Metropolitan District", "Aspen Park Metropolitan District",
									"Bachelors Gulch Metro District", "Baptist Road Rural Transportation District",
									"Beaver Creek Resort District", "Belmar-Plaza Metropolitan District No. 1",
									"Boulder County Local Improvement District",
									"Cafarelli Center At Denver West Pid 1", "Cafarelli Center At Denver West Pid 2",
									"Centerra Public Improvement District", "Centerra Retail Sales District",
									"Cityset I", "Colorado Mills-Public Improvement District",
									"Conifer Metropolitan District", "Copper Mountain Resort Association",
									"Copper Ridge Metropolitan District", "Creekside-Public Improvement District",
									"Deer Trail Reporting Area", "Delta County Public Safety Improvement District",
									"Denver Reporting Area", "Denver Tourism Improvement District",
									"Dolores Canyon Metropolitan District No. 1", "Eagle-Vail Metropolitan District",
									"East Canon Reporting Area", "Edwards Metropolitan District",
									"Elbert And Highway 86 Commercial Metropolitan District Pif",
									"Estes Park Local Marketing District",
									"Firestone City Centre 2 Public Improvement District",
									"First And Main Business Improvement District No. 2",
									"Fitzsimons Village Public Improvement District", "Flatiron Improvement District",
									"Florissant Reporting Area", "Football Stadium District",
									"Foothills Public Improvement District",
									"Front Range Village Public Improvement Fee",
									"Gardens On Havana Metropolitan District No. 1",
									"Glenwood Meadows Metropolitan District",
									"Gunnison County Local Marketing District",
									"Gunnison Valley Rural Transportation Authority", "Gunnison-Hinsdale Combined Etsa",
									"Harmony Commons Retail Sales District",
									"Interquest Marketplace Public Improvement Fee",
									"Johnstown Plaza Public Improvement District", "La Junta Health Service District",
									"Lakeside Center Public Improvement Fee",
									"Lamar Station Plaza Public Improvement District",
									"Lincoln Station Local Improvement District",
									"Littleton Village Metropolitan District No. 3", "Manzanola Reporting Area",
									"Merino Reporting Area", "Mesa County Local Improvement District",
									"Mesa County Public Safety Improvement District",
									"Moffat County Local Marketing District", "Montezuma County Hospital District",
									"Mt Crested Butte Town Center Lodging District",
									"Mt Vernon Country Club Metropolitan District",
									"North Gateway Number 1 Public Improvement District",
									"Oxbow Crossing And River Landing Phase I",
									"Oxbow Crossing And River Landing Phase Ii",
									"Pikes Peak Rural Transportation Authority", "Pinecliffe Reporting Area",
									"Pnq Llc Public Improvement District", "Powderhorn Reporting Area",
									"Powers Pointe Public Improvement District",
									"Prairie Center Metropolitan District No. 1",
									"Promenade At Castle Rock Metropolitan District No. 1",
									"Promenade At Castle Rock Metropolitan District No. 2",
									"Promenade At Castle Rock Metropolitan District No. 3",
									"Promenade Denver West Public Improvement Fee", "Promenade Retail Sales District",
									"Public Safety Improvement District", "Pueblo West Metropolitan District",
									"Ramah Reporting Area", "Rampart Range Metropolitan District No. 1",
									"Red Sky Ranch Metropolitan District", "Regional Transportation District",
									"Rimrock Crossing Public Improvement District", "River Point At Sheridan",
									"Roaring Fork Rural Transportation Authority",
									"Roaring Fork Rural Transportation Authority", "Royal Gorge Reporting Area",
									"Rye Reporting Area", "San Luis Valley E-911 Telephone Authority",
									"San Miguel Regional Transportation Authority",
									"Scientific And Cultural Facilities District", "Sedgwick County Lodging District",
									"South Academy Highlands Public Improvement District", "South Fork Reporting Area",
									"South Park Health Service District", "South Platte Valley Rta",
									"Southeast Jefferson County Local Improvement District",
									"Southwest Plaza Metropolitan District", "Spicer Reporting Area",
									"Spivak Reporting Area", "Steamboat Springs Local Marketing District",
									"Summit County Housing Authority", "Traer Creek Metropolitan District",
									"Two Rivers Metropolitan District", "Ute Pass Regional Ambulance District",
									"Vail Local Marketing District", "Valley Club Pointe Metropolitan District",
									"Village At Dry Creek Public Improvement District No. 1", "Webster Lake Promenade",
									"West Of Highway 85 Reporting District", "Woodland Park Reporting Area" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
						if (issuelist.equalsIgnoreCase("County")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Adams", "Alamosa", "Arapahoe", "Archuleta", "Baca", "Bent", "Boulder",
									"Broomfield", "Chaffee", "Cheyenne", "Clear Creek", "Conejos", "Costilla",
									"Crowley", "Custer", "Delta", "Denver", "Dolores", "Douglas", "Eagle", "El Paso",
									"Elbert", "Fremont", "Garfield", "Gilpin", "Grand", "Gunnison", "Hinsdale",
									"Huerfano", "Jackson", "Jefferson", "Kiowa", "Kit Carson", "La Plata", "Lake",
									"Larimer", "Las Animas", "Lincoln", "Logan", "Mesa", "Mineral", "Moffat",
									"Montezuma", "Montrose", "Morgan", "Otero", "Ouray", "Park", "Phillips", "Pitkin",
									"Prowers", "Pueblo", "Rio Blanco", "Rio Grande", "Routt", "Saguache", "San Juan",
									"San Miguel", "Sedgwick", "Summit", "Teller", "Washington", "Weld", "Yuma" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
					}
					break;
				case "Louisiana":
					String[] issuinglist5 = { "City", "District", "County" };
					for (int l = 0; l < issuinglist5.length; l++) {
						String issuelist = issuinglist5[l];
						if (issuelist.equalsIgnoreCase("City")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Abbeville", "Abita Springs", "Addis", "Albany", "Alexandria", "Amite",
									"Anacoco", "Angie", "Arcadia", "Arnaudville", "Ashland", "Athens", "Atlanta",
									"Baker", "Baldwin", "Ball", "Basile", "Baskin", "Bastrop", "Baton Rouge", "Belcher",
									"Benton", "Bernice", "Berwick", "Bienville", "Blanchard", "Bogalusa", "Bonita",
									"Bossier City", "Boyce", "Breaux Bridge", "Broussard", "Brusly", "Bryceland",
									"Bunkie", "Calvin", "Campti", "Cankton", "Carencro", "Castor", "Central",
									"Chataignier", "Chatham", "Cheneyville", "Choudrant", "Church Point", "Clarence",
									"Clarks", "Clayton", "Clinton", "Colfax", "Collinston", "Columbia", "Converse",
									"Cotton Valley", "Cottonport", "Coushatta", "Covington", "Creola", "Crowley",
									"Cullen", "Delcambre", "Delhi", "Delta", "Denham Springs", "Dequincy", "Deridder",
									"Dixie Inn", "Dodson", "Donaldsonville", "Downsville", "Doyline", "Dry Prong",
									"Dubach", "Dubberly", "Duson", "East Hodge", "Edgefield", "Elizabeth", "Elton",
									"Epps", "Erath", "Eros", "Estherwood", "Eunice", "Evergreen", "Farmerville",
									"Fenton", "Ferriday", "Fisher", "Florien", "Folsom", "Fordoche", "Forest",
									"Forest Hill", "Franklin", "Franklinton", "French Settlement", "Georgetown",
									"Gibsland", "Gilbert", "Gilliam", "Glenmora", "Golden Meadow", "Goldonna",
									"Gonzales", "Grambling", "Gramercy", "Grand Cane", "Grand Coteau", "Grand Isle",
									"Grayson", "Greensburg", "Greenwood", "Gretna", "Grosse Tete", "Gueydan",
									"Hahnville", "Hall Summit", "Hammond", "Harahan", "Harrisonburg", "Haughton",
									"Haynesville", "Heflin", "Henderson", "Hessmer", "Hodge", "Homer", "Hornbeck",
									"Hosston", "Houma", "Ida", "Independence", "Iota", "Iowa", "Jackson", "Jamestown",
									"Jean Lafitte", "Jeanerette", "Jena", "Jennings", "Jonesboro", "Jonesville",
									"Junction City", "Kaplan", "Keachi", "Kenner", "Kentwood", "Kilbourne", "Killian",
									"Kinder", "Krotz Springs", "Lafayette", "Lake Arthur", "Lake Charles",
									"Lake Providence", "Lecompte", "Leesville", "Leonville", "Lillie", "Lisbon",
									"Livingston", "Livonia", "Lockport", "Logansport", "Longstreet", "Loreauville",
									"Lucky", "Lutcher", "Madisonville", "Mamou", "Mandeville", "Mangham", "Mansfield",
									"Mansura", "Many", "Maringouin", "Marion", "Marksville", "Martin", "Maurice",
									"Mcnary", "Melville", "Mer Rouge", "Mermentau", "Merryville", "Minden", "Monroe",
									"Montgomery", "Montpelier", "Mooringsport", "Moreauville", "Morgan City",
									"Morganza", "Morse", "Mound", "Mount Lebanon", "Napoleonville", "Natchez",
									"Natchitoches", "New Iberia", "New Llano", "New Orleans", "New Roads", "Newellton",
									"Noble", "North Hodge", "Norwood", "Oak Grove", "Oak Ridge", "Oakdale", "Oberlin",
									"Oil City", "Olla", "Opelousas", "Palmetto", "Parks", "Patterson", "Pearl River",
									"Pine Prairie", "Pineville", "Pioneer", "Plain Dealing", "Plaquemine",
									"Plaucheville", "Pleasant Hill", "Pollock", "Ponchatoula", "Port Allen",
									"Port Barre", "Port Vincent", "Powhatan", "Provencal", "Quitman", "Rayne",
									"Rayville", "Reeves", "Richmond", "Richwood", "Ridgecrest", "Ringgold", "Robeline",
									"Rodessa", "Rosedale", "Roseland", "Rosepine", "Ruston", "Saint Francisville",
									"Saint Gabriel", "Saint Joseph", "Saint Martinville", "Saline", "Sarepta", "Scott",
									"Shongaloo", "Shreveport", "Sibley", "Sicily Island", "Sikes", "Simmesport",
									"Simpson", "Simsboro", "Slaughter", "Slidell", "Sorrento", "South Mansfield",
									"Spearsville", "Springfield", "Springhill", "Stanley", "Sterlington", "Stonewall",
									"Sulphur", "Sun", "Sunset", "Tallulah", "Tangipahoa", "Thibodaux", "Tickfaw",
									"Tullos", "Turkey Creek", "Urania", "Varnado", "Vidalia", "Vienna", "Ville Platte",
									"Vinton", "Vivian", "Walker", "Washington", "Waterproof", "Welsh", "West Monroe",
									"Westlake", "Westwego", "White Castle", "Wilson", "Winnfield", "Winnsboro",
									"Wisner", "Woodworth", "Youngsville", "Zachary", "Zwolle" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
						if (issuelist.equalsIgnoreCase("District")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "4Th Ward", "Abbeville Hospital Service District #2",
									"Alexandria Downtown District", "Americana Economic Development District",
									"Apollo Edd", "Ascension Parish Drainage District",
									"Ascension Parish Hospital District", "Ascension Parish Sales Tax District",
									"Assumption Parish Drainage District", "Assumption Parish Library District",
									"Assumption Parish Road District", "Baker School District",
									"Baton Rouge North Economic Development District",
									"Bluebonnet Convention Hotel Taxing District",
									"Breaux Bridge Economic Development District #1",
									"Breaux Bridge Northern Annexation",
									"Broussard Ambassador Caffery Extension Sub-District No. 1",
									"Caddo Parish Fire District No 1", "Caddo Parish Fire District No. 3",
									"Caddo Parish Sales Tax District", "Caldwell Parish Hospital District",
									"Camellia Square Economic Development District", "Central School District",
									"Central St Landry Economic Development District",
									"Claiborne Parish Water Shed District", "Concordia Parish Hospital District",
									"Conway Economic Development District", "Cooktown Economic Development District",
									"Coushatta Tribe", "Cyntreniks Group/King Hotel Special Taxing District",
									"Denham Springs Economic Development District", "Destination Pointe Edd",
									"Donaldsonville Annexation Area",
									"Downtown Lafayette Economic Development District", "East Ascension",
									"East Baton Rouge School District", "East Ouachita", "East School District",
									"Executive Economic Development District", "Fire District 17", "Fire District 18",
									"Franklin Economic Development District No. 1",
									"Fremaux Town Center Economic Development District",
									"Grambling Economic Development District",
									"Grand Coteau Economic Development District No. 1",
									"Greater Alexandria Economic Development Authority District",
									"Harveston Economic Development District", "Henderson North I-10 Annex",
									"Holy Rosary Institute Economic Development District",
									"Hope Economic Development District", "Hwy 21 Economic Development District",
									"I-10 Corridor @ Mile Marker 103", "I-49 Corridor Economic Development District",
									"Iberia Parish Economic Development District No. 1",
									"Jefferson Davis Parish Road District",
									"Jefferson Davis Parish Sales Tax District 1",
									"Jefferson Parish East Bank District", "Jefferson Parish West Bank District",
									"Juban Crossing Economic Development District",
									"Lafourche Parish Consolidated District A", "Lafourche Parish Road Tax District 2",
									"Lakefront Economic Development District", "Lincoln Parish Fire District",
									"Livingston Parish Drainage District #1", "Livingston Parish Drainage District #2",
									"Livingston Parish Drainage District #5",
									"Livingston Parish School Tax District No. 22",
									"Livingston Parish School Tax District No. 33",
									"Louisiana Stadium And Exposition District",
									"Magnolia Economic Development District", "Mall Economic Development District",
									"Memorial Boulevard Development District",
									"Minden Economic Development District #1",
									"Minden Economic Development District #2",
									"Minden Economic Development District #3",
									"Minden Economic Development District #4",
									"Morehouse Parish Police Jury District #1", "Morehouse Parish Subdistrict #1",
									"Morehouse Parish Tax District #1", "Morganfield Economic Development District",
									"Natchitoches Economic Development District A",
									"Natchitoches Economic Development District B",
									"Natchitoches Economic Development District C",
									"Natchitoches Economic Development District D", "New Iberia Annexed Sub District",
									"New Iberia Highway 14 Economic Development District #1",
									"New Iberia Highway 182 Economic Development District #1",
									"New Orleans International", "North Caddo Hospital District",
									"North Lafourche Levee District", "Northshore Harbor Center District",
									"Northshore Square Economic Development District",
									"Northway Economic Development District", "Old Lnb Building Redevelopment District",
									"Opelousas Ddd Edd", "Opelram Economic Development District",
									"Rapides Parish Sales Tax District", "River Express Development Zone 1",
									"Riverview Economic Development District", "Rooms To Go",
									"Ruston Economic Development District #1", "Sabine Parish School District 2",
									"Sabine Parish School District 34", "Saint Martinville North Annex",
									"South Lafourche Levee District", "Southside Economic Development District",
									"St Martin Parish Sales Tax District #1", "St Martin Parish Sales Tax District #2",
									"Sterlington Economic Development District No. 1",
									"Tanger Mall Development District", "Tangipahoa Parish Fire Protection District #1",
									"Third-Florida Economic Development District",
									"Trappey Economic Development District",
									"University Club Center Economic Development District",
									"University Gateway Economic Development District",
									"Vermilion Parish Hospital District #1",
									"Vidalia Riverfront Economic Development District", "Walker Annexed Sub District",
									"Ward 1", "Ward 1 (Tax District No 5)", "Ward 1", "Tax District #8", "Ward 2",
									"Ward 5 & 8", "Wards 1", "2", "3", "4", "7 & 10", "Wards 6 & 9",
									"Webster Parish School District 6", "West Ascension",
									"West Baton Rouge Parish Fire District",
									"West Baton Rouge Parish Sales Tax District",
									"West Monroe Economic Development District", "West Ouachita",
									"Youngsville Sales Tax District No. 1", "Zachary School District" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
						if (issuelist.equalsIgnoreCase("County")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Acadia", "Allen", "Ascension", "Assumption", "Avoyelles", "Beauregard",
									"Bienville", "Bossier", "Caddo", "Calcasieu", "Caldwell", "Cameron", "Catahoula",
									"Claiborne", "Concordia", "De Soto", "East Baton Rouge", "East Carroll",
									"East Feliciana", "Evangeline", "Franklin", "Grant", "Iberia", "Iberville",
									"Jackson", "Jefferson", "Jefferson Davis", "Lafayette", "Lafourche", "Lasalle",
									"Lincoln", "Livingston", "Madison", "Morehouse", "Natchitoches", "Orleans",
									"Ouachita", "Plaquemines", "Pointe Coupee", "Rapides", "Red River", "Richland",
									"Sabine", "St Bernard", "St Charles", "St Helena", "St James",
									"St John The Baptist", "St Landry", "St Martin", "St Mary", "St Tammany",
									"Tangipahoa", "Tensas", "Terrebonne", "Union", "Vermilion", "Vernon", "Washington",
									"Webster", "West Baton Rouge", "West Carroll", "West Feliciana", "Winn" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
					}
					break;
				case "South Carolina":
					String[] issuinglist6 = { "City", "District", "County" };
					for (int l = 0; l < issuinglist6.length; l++) {
						String issuelist = issuinglist6[l];
						if (issuelist.equalsIgnoreCase("City")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Abbeville", "Aiken", "Allendale", "Anderson", "Andrews", "Arcadia Lakes",
									"Atlantic Beach", "Awendaw", "Aynor", "Bamberg", "Barnwell", "Batesburg-Leesville",
									"Beaufort", "Belton", "Bennettsville", "Bethune", "Bishopville", "Blacksburg",
									"Blackville", "Blenheim", "Bluffton", "Blythewood", "Bonneau", "Bowman",
									"Branchville", "Briarcliffe Acres", "Brunson", "Burnettown", "Calhoun Falls",
									"Camden", "Cameron", "Campobello", "Carlisle", "Cayce", "Central",
									"Central Pacolet", "Chapin", "Charleston", "Cheraw", "Chesnee", "Chester",
									"Chesterfield", "Clemson", "Clinton", "Clio", "Clover", "Columbia", "Conway",
									"Cope", "Cordova", "Cottageville", "Coward", "Cowpens", "Cross Hill", "Darlington",
									"Denmark", "Dillon", "Donalds", "Due West", "Duncan", "Easley", "Eastover",
									"Edgefield", "Edisto Beach", "Ehrhardt", "Elgin", "Elko", "Elloree", "Estill",
									"Eutawville", "Fairfax", "Florence", "Folly Beach", "Forest Acres", "Fort Lawn",
									"Fort Mill", "Fountain Inn", "Furman", "Gaffney", "Gaston", "Georgetown", "Gifford",
									"Gilbert", "Goose Creek", "Govan", "Gray Court", "Great Falls", "Greeleyville",
									"Greenville", "Greenwood", "Greer", "Hampton", "Hanahan", "Hardeeville",
									"Harleyville", "Hartsville", "Heath Springs", "Hemingway", "Hickory Grove", "Hilda",
									"Hilton Head Island", "Hodges", "Holly Hill", "Hollywood", "Honea Path", "Inman",
									"Irmo", "Isle Of Palms", "Iva", "Jackson", "James Island", "Jamestown", "Jefferson",
									"Jenkinsville", "Johnsonville", "Johnston", "Jonesville", "Kershaw",
									"Kiawah Island", "Kingstree", "Kline", "Lake City", "Lake View", "Lamar",
									"Lancaster", "Landrum", "Lane", "Latta", "Laurens", "Lexington", "Liberty",
									"Lincolnville", "Little Mountain", "Livingston", "Lockhart", "Lodge", "Loris",
									"Lowndesville", "Lowrys", "Luray", "Lyman", "Lynchburg", "Manning", "Marion",
									"Mauldin", "Mayesville", "Mcbee", "Mcclellanville", "Mccoll", "Mcconnells",
									"Mccormick", "Meggett", "Moncks Corner", "Monetta", "Mount Croghan",
									"Mount Pleasant", "Mullins", "Myrtle Beach", "Neeses", "New Ellenton", "Newberry",
									"Nichols", "Ninety Six", "Norris", "North", "North Augusta", "North Charleston",
									"North Myrtle Beach", "Norway", "Olanta", "Olar", "Orangeburg", "Pacolet",
									"Pageland", "Pamplico", "Parksville", "Patrick", "Pawleys Island", "Paxville",
									"Peak", "Pelion", "Pelzer", "Pendleton", "Perry", "Pickens", "Pine Ridge",
									"Pinewood", "Plum Branch", "Pomaria", "Port Royal", "Prosperity", "Quinby",
									"Ravenel", "Reevesville", "Reidville", "Richburg", "Ridge Spring", "Ridgeland",
									"Ridgeville", "Ridgeway", "Rock Hill", "Rockville", "Rowesville", "Ruby",
									"Saint George", "Saint Matthews", "Saint Stephen", "Salem", "Salley", "Saluda",
									"Santee", "Scotia", "Scranton", "Seabrook Island", "Sellers", "Seneca", "Sharon",
									"Silverstreet", "Simpsonville", "Six Mile", "Smoaks", "Smyrna", "Snelling",
									"Society Hill", "South Congaree", "Spartanburg", "Springdale", "Springfield",
									"Starr", "Stuckey", "Sullivans Island", "Summerton", "Summerville", "Summit",
									"Sumter", "Surfside Beach", "Swansea", "Sycamore", "Tatum", "Tega Cay",
									"Timmonsville", "Travelers Rest", "Trenton", "Troy", "Turbeville", "Ulmer", "Union",
									"Van Wyck", "Vance", "Varnville", "Wagener", "Walhalla", "Walterboro", "Ward",
									"Ware Shoals", "Waterloo", "Wellford", "West Columbia", "West Pelzer", "West Union",
									"Westminster", "Whitmire", "Williams", "Williamston", "Williston", "Windsor",
									"Winnsboro", "Woodford", "Woodruff", "Yemassee", "York" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
						if (issuelist.equalsIgnoreCase("District")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Catawba Indian Reservation" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
						if (issuelist.equalsIgnoreCase("County")) {
							Select Jurisdtype = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
							Jurisdtype.selectByVisibleText(issuelist);
							Thread.sleep(5000);
							List<WebElement> length = driver
									.findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
							List<String> all_elements_text = new ArrayList<>();
							System.out.println(state);
							System.out.println(issuelist);
							for (int i = 0; i < length.size(); i++) {
								if (!length.get(i).getText().isEmpty()) {
									all_elements_text.add(length.get(i).getText());
								}
							}
							System.out.println(all_elements_text);
							List<String> all_elements_text1 = new ArrayList<>();
							String C[] = { "Abbeville", "Aiken", "Allendale", "Anderson", "Bamberg", "Barnwell",
									"Beaufort", "Berkeley", "Calhoun", "Charleston", "Cherokee", "Chester",
									"Chesterfield", "Clarendon", "Colleton", "Darlington", "Dillon", "Dorchester",
									"Edgefield", "Fairfield", "Florence", "Georgetown", "Greenville", "Greenwood",
									"Hampton", "Horry", "Jasper", "Kershaw", "Lancaster", "Laurens", "Lee", "Lexington",
									"Marion", "Marlboro", "Mccormick", "Newberry", "Oconee", "Orangeburg", "Pickens",
									"Richland", "Saluda", "Spartanburg", "Sumter", "Union", "Williamsburg", "York" };
							for (String text : C) {
								all_elements_text1.add(text);
							}
							all_elements_text1.equals(all_elements_text);
						}
					}
					break;

				/*
				 * case "Texas": String[] issuinglist7 = { "City", "District", "County" }; for
				 * (int l = 0; l < issuinglist7.length; l++) { String issuelist =
				 * issuinglist7[l]; if (issuelist.equalsIgnoreCase("City")) { Select Jurisdtype
				 * = new Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
				 * Jurisdtype.selectByVisibleText(issuelist); Thread.sleep(5000);
				 * List<WebElement> length = driver
				 * .findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
				 * List<String> all_elements_text = new ArrayList<>();
				 * System.out.println(state); System.out.println(issuelist); for (int i = 0; i <
				 * length.size(); i++) { if (!length.get(i).getText().isEmpty()) {
				 * all_elements_text.add(length.get(i).getText()); } }
				 * System.out.println(all_elements_text); List<String> all_elements_text1 = new
				 * ArrayList<>(); String C[] = { "Abbott", "Abernathy", "Abilene", "Ackerly",
				 * "Acton", "Addison", "Adkins", "Adrian", "Agua Dulce", "Alamo",
				 * "Alamo Heights", "Alba", "Albany", "Aledo", "Alice", "Allen", "Alma",
				 * "Alpine", "Altair", "Alto", "Alton", "Alvarado", "Alvin", "Alvord",
				 * "Amarillo", "Ames", "Amherst", "Anahuac", "Anderson", "Andrews", "Angleton",
				 * "Angus", "Anna", "Annetta", "Annetta North", "Annetta South", "Annona",
				 * "Anson", "Anthony", "Anton", "Appleby", "Aquilla", "Aransas Pass",
				 * "Archer City", "Arcola", "Argyle", "Arlington", "Arp", "Asherton",
				 * "Aspermont", "Atascosa", "Athens", "Atlanta", "Aubrey", "Aurora", "Austin",
				 * "Austwell", "Avery", "Avinger", "Azle", "Bailey", "Baileys Prairie", "Baird",
				 * "Balch Springs", "Balcones Heights", "Ballinger", "Balmorhea", "Bammel",
				 * "Bandera", "Bangs", "Banquete", "Bardwell", "Barry", "Barstow", "Bartlett",
				 * "Bartonville", "Bastrop", "Bay City", "Bayou Vista", "Bayside", "Baytown",
				 * "Bayview", "Beach City", "Bear Creek", "Beasley", "Beaumont", "Beckville",
				 * "Bedford", "Bedias", "Bee Cave", "Beeville", "Bellaire", "Bellevue",
				 * "Bellmead", "Bells", "Bellville", "Belton", "Benavides", "Benbrook",
				 * "Benjamin", "Berryville", "Bertram", "Beverly Hills", "Bevil Oaks",
				 * "Big Lake", "Big Sandy", "Big Spring", "Big Wells", "Bishop", "Bishop Hills",
				 * "Blackwell", "Blanco", "Blanket", "Bloomburg", "Blooming Grove", "Blossom",
				 * "Blue Mound", "Blue Ridge", "Blum", "Boerne", "Bogata", "Bonham", "Bonney",
				 * "Booker", "Borger", "Bovina", "Bowie", "Boyd", "Brackettville", "Brady",
				 * "Brazoria", "Brazos Country", "Breckenridge", "Bremond", "Brenham",
				 * "Briarcliff", "Briaroaks", "Bridge City", "Bridgeport", "Broaddus", "Brock",
				 * "Bronte", "Brookshire", "Brookside Village", "Browndell", "Brownfield",
				 * "Brownsboro", "Brownsville", "Brownwood", "Bruceville Eddy", "Bryan",
				 * "Bryson", "Buckholts", "Buda", "Buena Vista", "Buffalo", "Buffalo Gap",
				 * "Buffalo Springs", "Bullard", "Bulverde", "Bunker Hill Village",
				 * "Burkburnett", "Burke", "Burleson", "Burnet", "Burton", "Byers", "Bynum",
				 * "Cactus", "Caddo Mills", "Caldwell", "Callisburg", "Calvert", "Cameron",
				 * "Camp Wood", "Campbell", "Canadian", "Caney City", "Canton", "Canyon",
				 * "Carbon", "Carls Corner", "Carmine", "Carrizo Springs", "Carrollton",
				 * "Carthage", "Cashion Community", "Castle Hills", "Castroville", "Cedar Hill",
				 * "Cedar Park", "Celeste", "Celina", "Center", "Centerville", "Chandler",
				 * "Channing", "Chapman Ranch", "Charlotte", "Chester", "Chico", "Childress",
				 * "Chillicothe", "China", "China Grove", "Chireno", "Christine", "Cibolo",
				 * "Cisco", "Clarendon", "Clarksville", "Clarksville City", "Claude",
				 * "Clear Lake Shores", "Cleburne", "Cleveland", "Clifton", "Clint", "Clute",
				 * "Clyde", "Coahoma", "Cockrell Hill", "Coffee City", "Coldspring", "Coleman",
				 * "College Station", "Colleyville", "Collinsville", "Colmesneil",
				 * "Colorado City", "Columbus", "Comanche", "Combes", "Combine", "Commerce",
				 * "Como", "Conroe", "Converse", "Cool", "Coolidge", "Cooper", "Coppell",
				 * "Copper Canyon", "Copperas Cove", "Corinth", "Corpus Christi", "Corrigan",
				 * "Corsicana", "Cottonwood", "Cottonwood Shores", "Cotulla", "Coupland",
				 * "Cove", "Covington", "Coyote Flats", "Crandall", "Crane", "Cranfills Gap",
				 * "Crawford", "Creedmoor", "Cresson", "Crockett", "Crosbyton", "Cross Plains",
				 * "Cross Roads", "Cross Timber", "Crowell", "Crowley", "Crystal City", "Cuero",
				 * "Cumby", "Cuney", "Cushing", "Cut And Shoot", "Cypress", "Daingerfield",
				 * "Daisetta", "Dalhart", "Dallas", "Dalworthington Gardens", "Danbury",
				 * "Darrouzett", "Dawson", "Dayton", "Dayton Lakes", "De Kalb", "De Leon",
				 * "Dean", "Decatur", "Decordova", "Deer Park", "Del Rio", "Dell City",
				 * "Denison", "Dennis", "Denton", "Denver City", "Deport", "Desoto", "Detroit",
				 * "Devers", "Devine", "Diboll", "Dickens", "Dickinson", "Dilley", "Dimmitt",
				 * "Dish", "Dodd City", "Dodson", "Domino", "Donna", "Dorchester", "Double Oak",
				 * "Douglassville", "Draper", "Dripping Springs", "Driscoll", "Dublin", "Dumas",
				 * "Duncanville", "Eagle Lake", "Eagle Pass", "Early", "Earth", "East Bernard",
				 * "East Mountain", "East Tawakoni", "Eastland", "Easton", "Ector", "Edcouch",
				 * "Eden", "Edgecliff Village", "Edgewood", "Edinburg", "Edmonson", "Edna",
				 * "Edom", "El Campo", "El Cenizo", "El Lago", "El Paso", "Eldorado", "Electra",
				 * "Elgin", "Elkhart", "Elmendorf", "Elsa", "Emhouse", "Emory",
				 * "Enchanted Oaks", "Encinal", "Ennis", "Escobares", "Estelline", "Euless",
				 * "Eureka", "Eustace", "Evant", "Everman", "Fair Oaks Ranch", "Fairchilds",
				 * "Fairfield", "Fairview", "Falfurrias", "Falls City", "Farmers Branch",
				 * "Farmersville", "Farwell", "Fate", "Fayetteville", "Ferris", "Fischer",
				 * "Flatonia", "Flatwood", "Florence", "Floresville", "Flower Mound",
				 * "Floydada", "Follett", "Forest Hill", "Forney", "Forsan", "Fort Stockton",
				 * "Fort Worth", "Four Corners", "Franklin", "Frankston", "Fredericksburg",
				 * "Freeport", "Freer", "Friendswood", "Friona", "Frisco", "Fritch", "Frost",
				 * "Fruitvale", "Fulshear", "Fulton", "Gainesville", "Galena Park", "Gallatin",
				 * "Galveston", "Ganado", "Garden Ridge", "Garland", "Garrett", "Garrison",
				 * "Garwood", "Gary", "Gatesville", "George West", "Georgetown", "Gholson",
				 * "Giddings", "Gilmer", "Gladewater", "Glen Rose", "Glenn Heights", "Godley",
				 * "Goldsmith", "Goldthwaite", "Goliad", "Golinda", "Gonzales", "Goodlow",
				 * "Goodrich", "Gordon", "Goree", "Gorman", "Graford", "Graham", "Granbury",
				 * "Grand Prairie", "Grand Saline", "Grandfalls", "Grandview", "Granger",
				 * "Grangerland", "Granite Shoals", "Granjeno", "Grapeland", "Grapevine",
				 * "Grays Prairie", "Greenville", "Gregory", "Grey Forest", "Groesbeck",
				 * "Groom", "Groves", "Groveton", "Gruver", "Gun Barrel City", "Gunter",
				 * "Gustine", "Hackberry", "Hale Center", "Hallettsville", "Hallsburg",
				 * "Hallsville", "Haltom City", "Hamilton", "Hamlin", "Happy", "Hardin",
				 * "Harker Heights", "Harlingen", "Hart", "Haskell", "Haslet", "Hawk Cove",
				 * "Hawkins", "Hawley", "Hays", "Hearne", "Heath", "Hebron", "Hedley",
				 * "Hedwig Village", "Helotes", "Hemphill", "Hempstead", "Henderson",
				 * "Henrietta", "Hereford", "Hewitt", "Hickory Creek", "Hico", "Hidalgo",
				 * "Hideaway", "Higgins", "Highland Haven", "Highland Park", "Highland Village",
				 * "Highlands", "Hill Country Village", "Hillcrest", "Hillsboro",
				 * "Hilshire Village", "Hitchcock", "Hockley", "Holiday Lakes", "Holland",
				 * "Holliday", "Hollywood Park", "Hondo", "Honey Grove", "Hooks",
				 * "Horizon City", "Horseshoe Bay", "Houston", "Howardwick", "Howe", "Hubbard",
				 * "Hudson", "Hudson Oaks", "Hufsmith", "Hughes Springs", "Humble",
				 * "Hunters Creek Village", "Huntington", "Huntsville", "Hurst", "Hutchins",
				 * "Hutto", "Huxley", "Hye", "Idalou", "Impact", "Indian Lake", "Industry",
				 * "Ingleside", "Ingleside On The Bay", "Ingram", "Iola", "Iowa Colony",
				 * "Iowa Park", "Iraan", "Iredell", "Irving", "Italy", "Itasca", "Ivanhoe",
				 * "Jacinto City", "Jacksboro", "Jacksonville", "Jamaica Beach", "Jarrell",
				 * "Jasper", "Jayton", "Jefferson", "Jersey Village", "Jewett", "Joaquin",
				 * "Johnson City", "Jolly", "Jones Creek", "Jonestown", "Josephine", "Joshua",
				 * "Jourdanton", "Junction", "Justin", "Karnes City", "Katy", "Kaufman",
				 * "Keene", "Keller", "Kemah", "Kemp", "Kempner", "Kendleton", "Kenedy",
				 * "Kenefick", "Kennard", "Kennedale", "Kerens", "Kermit", "Kerrville",
				 * "Kilgore", "Killeen", "Kingsbury", "Kingsville", "Kirby", "Kirbyville",
				 * "Kirvin", "Klein", "Knollwood", "Knox City", "Kosse", "Kountze", "Kress",
				 * "Krugerville", "Krum", "Kurten", "Kyle", "La Coste", "La Feria", "La Grange",
				 * "La Grulla", "La Joya", "La Marque", "La Porte", "La Vernia", "La Villa",
				 * "La Ward", "Lacy Lakeview", "Ladonia", "Lago Vista", "Laguna Vista",
				 * "Lajitas", "Lake Bridgeport", "Lake City", "Lake Dallas", "Lake Jackson",
				 * "Lake Tanglewood", "Lake Worth", "Lakeport", "Lakeside", "Lakeside",
				 * "Lakeside City", "Lakeview", "Lakeway", "Lakewood Village", "Lamesa",
				 * "Lampasas", "Lancaster", "Laredo", "Latexo", "Lavon", "Lawn", "League City",
				 * "Leakey", "Leander", "Leary", "Lefors", "Leon Springs", "Leon Valley",
				 * "Leona", "Leonard", "Leroy", "Levelland", "Lewisville", "Lexington",
				 * "Liberty", "Liberty Hill", "Lincoln Park", "Lindale", "Linden", "Lindsay",
				 * "Lipan", "Little Elm", "Little River Academy", "Littlefield", "Live Oak",
				 * "Liverpool", "Livingston", "Llano", "Lockhart", "Lockney", "Log Cabin",
				 * "Lometa", "Lone Oak", "Lone Oak", "Lone Star", "Longview", "Loraine",
				 * "Lorena", "Lorenzo", "Los Fresnos", "Los Indios", "Los Ybanez", "Losoya",
				 * "Lott", "Lovelady", "Lowry Crossing", "Lubbock", "Lucas", "Lueders",
				 * "Lufkin", "Luling", "Lumberton", "Lyford", "Lytle", "Mabank", "Macdona",
				 * "Madisonville", "Magnolia", "Malakoff", "Malone", "Manor", "Mansfield",
				 * "Manvel", "Marble Falls", "Marfa", "Marietta", "Marion", "Marlin", "Marquez",
				 * "Marshall", "Mart", "Martindale", "Mason", "Matador", "Mathis", "Matthews",
				 * "Maud", "Maypearl", "Mcallen", "Mccamey", "Mcgregor", "Mckinney", "Mclean",
				 * "Mclendon Chisholm", "Mcneil", "Meadow", "Meadowlakes", "Meadows Place",
				 * "Megargel", "Melissa", "Melvin", "Memphis", "Menard", "Mercedes", "Meridian",
				 * "Merkel", "Mertens", "Mertzon", "Mesquite", "Mexia", "Miami", "Midland",
				 * "Midlothian", "Midway", "Milano", "Mildred", "Miles", "Milford",
				 * "Millers Cove", "Millican", "Millsap", "Mineola", "Mineral Wells", "Mingus",
				 * "Mission", "Missouri City", "Mobeetie", "Mobile City", "Monahans",
				 * "Mont Belvieu", "Montgomery", "Moody", "Moore Station", "Moran", "Morgan",
				 * "Morgan's Point", "Morgan's Point Resort", "Morton", "Moulton", "Mount Calm",
				 * "Mount Enterprise", "Mount Pleasant", "Mount Vernon", "Mountain City",
				 * "Muenster", "Muleshoe", "Mullin", "Munday", "Murchison", "Murphy", "Mustang",
				 * "Mustang Ridge", "Nacogdoches", "Nada", "Naples", "Nash", "Nassau Bay",
				 * "Natalia", "Navarro", "Navasota", "Nazareth", "Nederland", "Needville",
				 * "Nesbitt", "Nevada", "New Berlin", "New Boston", "New Braunfels",
				 * "New Caney", "New Chapel Hill", "New Deal", "New Fairview", "New Home",
				 * "New Hope", "New London", "New Summerfield", "New Waverly", "Newark",
				 * "Newcastle", "Newton", "Neylandville", "Niederwald", "Nixon", "Nocona",
				 * "Nolanville", "Nome", "Noonday", "Nordheim", "Normangee", "North Cleveland",
				 * "North Richland Hills", "Northlake", "Novice", "O'brien", "O'donnell",
				 * "Oak Grove", "Oak Leaf", "Oak Point", "Oak Ridge", "Oak Ridge",
				 * "Oak Ridge North", "Oak Valley", "Oakwood", "Odem", "Odessa", "Oglesby",
				 * "Old River Winfree", "Olmos Park", "Olney", "Olton", "Omaha", "Onalaska",
				 * "Opdyke West", "Orange", "Orange Grove", "Orchard", "Ore City", "Overton",
				 * "Ovilla", "Oyster Creek", "Paducah", "Paint Rock", "Palacios", "Palestine",
				 * "Palisades", "Palm Valley", "Palmer", "Palmhurst", "Palmview", "Pampa",
				 * "Panhandle", "Panorama Village", "Pantego", "Paradise", "Paris", "Parker",
				 * "Pasadena", "Pattison", "Patton Village", "Payne Springs", "Pearland",
				 * "Pearsall", "Peaster", "Pecan Gap", "Pecan Hill", "Pecos", "Pelican Bay",
				 * "Penelope", "Penitas", "Perryton", "Petersburg", "Petrolia", "Petronila",
				 * "Pflugerville", "Pharr", "Pilot Point", "Pine Forest", "Pine Island",
				 * "Pinehurst", "Pineland", "Piney Point Village", "Pittsburg", "Plains",
				 * "Plainview", "Plano", "Plantersville", "Pleak", "Pleasant Valley",
				 * "Pleasanton", "Plum Grove", "Point", "Point Blank", "Point Comfort",
				 * "Point Venture", "Pond Springs", "Ponder", "Port Aransas", "Port Arthur",
				 * "Port Isabel", "Port Lavaca", "Port Neches", "Porter", "Portland", "Post",
				 * "Post Oak Bend", "Poteet", "Poth", "Pottsboro", "Powell", "Poynor",
				 * "Prairie View", "Premont", "Presidio", "Primera", "Princeton", "Progreso",
				 * "Progreso Lakes", "Prosper", "Providence Village", "Putnam", "Pyote",
				 * "Quanah", "Queen City", "Quinlan", "Quintana", "Quitaque", "Quitman",
				 * "Ralls", "Rancho Viejo", "Ranger", "Rangerville", "Rankin", "Ransom Canyon",
				 * "Ravenna", "Raymondville", "Red Oak", "Redlick", "Redwater", "Refugio",
				 * "Reklaw", "Reno", "Reno", "Retreat", "Rhome", "Rice", "Richardson",
				 * "Richland", "Richland Hills", "Richland Springs", "Richmond", "Richwood",
				 * "Riesel", "Rio Bravo", "Rio Grande City", "Rio Hondo", "Rio Vista",
				 * "Rising Star", "River Oaks", "Riverside", "Roanoke", "Roaring Springs",
				 * "Robert Lee", "Robinson", "Robstown", "Roby", "Rochester", "Rockdale",
				 * "Rockport", "Rocksprings", "Rockwall", "Rocky Mound", "Rogers",
				 * "Rollingwood", "Roma", "Roman Forest", "Ropesville", "Roscoe", "Rose City",
				 * "Rose Hill Acres", "Rosebud", "Rosenberg", "Ross", "Rosser", "Rotan",
				 * "Round Mountain", "Round Rock", "Round Top", "Rowlett", "Roxton",
				 * "Royse City", "Rule", "Runaway Bay", "Runge", "Rusk", "Sabinal", "Sachse",
				 * "Sadler", "Saginaw", "Saint Hedwig", "Saint Jo", "Saint Paul", "Salado",
				 * "San Angelo", "San Antonio", "San Augustine", "San Benito", "San Diego",
				 * "San Elizario", "San Felipe", "San Juan", "San Leanna", "San Marcos",
				 * "San Patricio", "San Pedro", "San Perlita", "San Saba", "Sanctuary", "Sandy",
				 * "Sandy Oaks", "Sandy Point", "Sanford", "Sanger", "Sansom Park",
				 * "Santa Anna", "Santa Clara", "Santa Fe", "Santa Rosa", "Satsuma", "Sattler",
				 * "Savoy", "Schertz", "Schulenburg", "Scotland", "Scottsville", "Scurry",
				 * "Seabrook", "Seadrift", "Seagoville", "Seagraves", "Sealy", "Seguin",
				 * "Selma", "Seminole", "Seven Oaks", "Seven Points", "Seymour", "Shady Shores",
				 * "Shallowater", "Shamrock", "Shavano Park", "Shenandoah", "Shepherd",
				 * "Sheridan", "Sherman", "Shiner", "Shoreacres", "Silsbee", "Silverton",
				 * "Simonton", "Sinton", "Skellytown", "Slaton", "Smiley", "Smithville",
				 * "Smyer", "Snook", "Snyder", "Socorro", "Somerset", "Somerville", "Sonora",
				 * "Sour Lake", "South Frydek", "South Houston", "South Mountain",
				 * "South Padre Island", "South San Pedro", "Southlake", "Southmayd",
				 * "Southside Place", "Spearman", "Splendora", "Spofford", "Spring Branch",
				 * "Spring Valley", "Springlake", "Springtown", "Spur", "Stafford",
				 * "Stagecoach", "Stamford", "Stanton", "Staples", "Star Harbor",
				 * "Stephenville", "Sterling City", "Stinnett", "Stockdale", "Stockton Bend",
				 * "Stratford", "Strawn", "Streetman", "Sudan", "Sugar Land", "Sullivan City",
				 * "Sulphur Springs", "Sun Valley", "Sundown", "Sunnyvale", "Sunray",
				 * "Sunrise Beach Village", "Sunset Valley", "Surfside Beach", "Sweeny",
				 * "Sweetwater", "Taft", "Tahoka", "Talco", "Talty", "Tatum", "Taylor",
				 * "Taylor Lake Village", "Taylor Landing", "Teague", "Tehuacana", "Temple",
				 * "Tenaha", "Terrell", "Terrell Hills", "Texarkana", "Texas City", "Texhoma",
				 * "Texline", "The Colony", "The Woodlands", "Thompsons", "Thorndale",
				 * "Thornton", "Thorntonville", "Thrall", "Three Rivers", "Throckmorton",
				 * "Tiki Island", "Timpson", "Tioga", "Tira", "Toco", "Todd Mission", "Tolar",
				 * "Tom Bean", "Tomball", "Tool", "Toyah", "Trent", "Trenton", "Trinidad",
				 * "Trinity", "Trophy Club", "Troup", "Troy", "Tulia", "Turkey", "Tuscola",
				 * "Tye", "Tyler", "Uhland", "Uncertain", "Union Grove", "Union Valley",
				 * "Universal City", "University Park", "Uvalde", "Valentine", "Valley Mills",
				 * "Valley View", "Van", "Van Alstyne", "Van Horn", "Van Raub", "Vega", "Venus",
				 * "Vernon", "Vertel", "Victoria", "Vidor", "Village Of The Hills",
				 * "Village Of Timbercreek Canyon", "Vinton", "Volente", "Von Ormy", "Waco",
				 * "Waelder", "Wake Village", "Waller", "Wallis", "Walnut Springs",
				 * "Warren City", "Waskom", "Watauga", "Waukegan", "Waxahachie", "Weatherford",
				 * "Webberville", "Webster", "Weimar", "Weinert", "Weir", "Wellington",
				 * "Wellman", "Wells", "Weslaco", "West", "West Columbia", "West Lake Hills",
				 * "West Orange", "West Tawakoni", "West University Place", "Westbrook",
				 * "Westfield", "Westlake", "Weston", "Weston Lakes", "Westover Hills",
				 * "Westworth Village", "Wharton", "Wheeler", "White Deer", "White Oak",
				 * "White Settlement", "Whiteface", "Whitehouse", "Whitesboro", "Whitewright",
				 * "Whitney", "Wichita Falls", "Wickett", "Wigginsville", "Willis",
				 * "Willow Park", "Wills Point", "Wilmer", "Wilson", "Wimberley", "Windcrest",
				 * "Windom", "Windthorst", "Winfield", "Wink", "Winnsboro", "Winona", "Winters",
				 * "Wixon Valley", "Wolfe City", "Wolfforth", "Woodbranch", "Woodcreek",
				 * "Woodloch", "Woodsboro", "Woodson", "Woodville", "Woodway", "Wortham",
				 * "Wylie", "Yantis", "Yoakum", "Yorktown", "Zavalla" }; for (String text : C) {
				 * all_elements_text1.add(text); } all_elements_text1.equals(all_elements_text);
				 * } if (issuelist.equalsIgnoreCase("District")) { Select Jurisdtype = new
				 * Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
				 * Jurisdtype.selectByVisibleText(issuelist); Thread.sleep(5000);
				 * List<WebElement> length = driver
				 * .findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
				 * List<String> all_elements_text = new ArrayList<>();
				 * System.out.println(state); System.out.println(issuelist); for (int i = 0; i <
				 * length.size(); i++) { if (!length.get(i).getText().isEmpty()) {
				 * all_elements_text.add(length.get(i).getText()); } }
				 * System.out.println(all_elements_text); List<String> all_elements_text1 = new
				 * ArrayList<>(); String C[] = { "Abilene-Taylor County Events Venue District",
				 * "Airline Improvement District", "Aliana Management District",
				 * "Amarillo-Potter Events Venue District",
				 * "Anahuac Municipal Development District",
				 * "Annetta Crime Control And Prevention District",
				 * "Aransas County Health Services", "Aransas Pass Crime Control District",
				 * "Aransas Pass Municipal Development District",
				 * "Argyle Crime Control And Prevention District",
				 * "Arlington Entertainment Area Management District",
				 * "Arlington Tourism Public Improvement District",
				 * "Aubrey Municipal Development District",
				 * "Austin Metropolitan Transit Authority",
				 * "Azle Crime Control And Prevention District",
				 * "Azle Municipal Development District",
				 * "Balcones Heights Crime Control District",
				 * "Bangs Municipal Development District",
				 * "Bartlett Municipal Development District",
				 * "Bartonville Crime Control District", "Baylor County Hospital District",
				 * "Baytown Crime Control And Prevention District", "Baytown Fire Control",
				 * "Prevention And Emergency Medical", "Baytown Municipal Development District",
				 * "Benbrook Library District", "Bertram Municipal Development District",
				 * "Bexar County Emergency Services District No. 10",
				 * "Bexar County Emergency Services District No. 11",
				 * "Bexar County Emergency Services District No. 12",
				 * "Bexar County Emergency Services District No. 2",
				 * "Bexar County Emergency Services District No. 3",
				 * "Bexar County Emergency Services District No. 4",
				 * "Bexar County Emergency Services District No. 5",
				 * "Bexar County Emergency Services District No. 6",
				 * "Bexar County Emergency Services District No. 7",
				 * "Bexar County Emergency Services District No. 8",
				 * "Big Sandy Municipal Development District",
				 * "Blanco County Emergency Services District No. 1",
				 * "Blanco County Emergency Services District No. 2",
				 * "Blanco County North Library District",
				 * "Blanco County South Library District", "Blue Mound Crime Control District",
				 * "Bogata Crime Control And Prevention District",
				 * "Boyd Municipal Development District",
				 * "Brewster County Emergency Services District No. 1",
				 * "Bridgeland Management District", "Brooks County Health Services",
				 * "Brownwood Municipal Development District",
				 * "Bulverde Area Rural Library District",
				 * "Burnet County Emergency Services District No. 4",
				 * "Burnet County Emergency Services District No. 6",
				 * "Burnet County Emergency Services District No. 8",
				 * "Burnet County Emergency Services District No. 9",
				 * "Caldwell County Emergency Services District No. 2",
				 * "Caldwell County Emergency Services District No. 3",
				 * "Caldwell County Emergency Services District No. 3-A",
				 * "Caldwell County Emergency Services District No. 4",
				 * "Caldwell Hays Emergency Services District No. 1",
				 * "Caldwell Hays Emergency Services District No. 1-A",
				 * "Canyon Lake Community Library District",
				 * "Cass County Emergency Services District No. 2",
				 * "Castle Hills Crime Control And Prevention District",
				 * "Cedar Hill Crime Control And Prevention District",
				 * "Chambers County Emergency Services District No. 1",
				 * "Chambers County Health Services",
				 * "Childress Municipal Development District",
				 * "Cibolo Canyons Special Improvement District",
				 * "Colleyville Crime Control District",
				 * "Comal County Emergency Services District No. 3",
				 * "Comal County Emergency Services District No. 4",
				 * "Comal County Emergency Services District No. 5",
				 * "Comal County Emergency Services District No. 5-A",
				 * "Comal County Emergency Services District No. 6",
				 * "Combes Municipal Development District", "Coppell Crime Control District",
				 * "Copper Canyon Crime Control And Prevention District",
				 * "Corinth Crime Control And Prevention District",
				 * "Corpus Christi Crime Control District",
				 * "Corpus Christi Metropolitan Transit Authority",
				 * "Cottonwood Shores Crime Control And Prevention District",
				 * "Crane County Assistance District",
				 * "Cross Roads Municipal Development District",
				 * "Crosswinds At South Lake Special Improvement District",
				 * "Crowley Crime Control And Prevention District",
				 * "Dallas Metropolitan Transit Authority",
				 * "Dallas Tourism Public Improvement District",
				 * "Dalworthington Gardens Crime Control District",
				 * "Deer Park Crime Control District", "Deer Park Fire Control",
				 * "Prevention And Ems District", "Delta County Emergency Services District",
				 * "Denton County Development District No. 4",
				 * "Denton County Transit Authority", "Dickinson Management District No. 1",
				 * "Draper Municipal Development District",
				 * "Driftwood Economic Development Municipal Mgt. District",
				 * "Dripping Springs Community Library District",
				 * "Duval County Emergency Services District No. 1",
				 * "Early Municipal Development District", "East Aldine Management District",
				 * "East Aldine Management District Special Zone",
				 * "East Montgomery County Improvement District",
				 * "East Montgomery County Improvement District A",
				 * "East Montgomery County Improvement District Edz No. 1",
				 * "East Montgomery County Improvement District Edz No. 2",
				 * "East Montgomery County Improvement District Edz No. 3",
				 * "East Montgomery County Improvement District Edz No. 4",
				 * "East Travis Gateway Library District",
				 * "Eastland Memorial Hospital District", "Ector County Assistance District",
				 * "Ector County Hospital District", "El Paso City Transit Department",
				 * "El Paso County Emergency Services District No. 2",
				 * "Elsa Municipal Development District",
				 * "Ennis Crime Control And Prevention District",
				 * "Erath County Development District No. 1", "Euless Crime Control District",
				 * "Evant Municipal Development District",
				 * "Everman Crime Control And Prevention District",
				 * "Fair Oaks Ranch Municipal Development District",
				 * "Fate Municipal Development District", "Flower Mound Crime Control District",
				 * "Flower Mound Fire Protection District", "Forest Hill Library District",
				 * "Fort Bend County Assistance District No. 1",
				 * "Fort Bend County Assistance District No. 10",
				 * "Fort Bend County Assistance District No. 11",
				 * "Fort Bend County Assistance District No. 16",
				 * "Fort Bend County Assistance District No. 19",
				 * "Fort Bend County Assistance District No. 2",
				 * "Fort Bend County Assistance District No. 20",
				 * "Fort Bend County Assistance District No. 22",
				 * "Fort Bend County Assistance District No. 4",
				 * "Fort Bend County Assistance District No. 5",
				 * "Fort Bend County Assistance District No. 6",
				 * "Fort Bend County Assistance District No. 7",
				 * "Fort Bend County Assistance District No. 8",
				 * "Fort Bend County Assistance District No. 9",
				 * "Fort Bend County Emergency Services District No. 7",
				 * "Fort Bend County Improvement District No. 24",
				 * "Fort Bend County Municipal Management District No. 1",
				 * "Fort Worth Crime Control District",
				 * "Fort Worth Metropolitan Transit Authority",
				 * "Fort Worth Public Improvement No. 18", "Fritch Crime Control District",
				 * "Galveston County Emergency Services District No.2",
				 * "Generation Park Management District",
				 * "Grand Prairie Crime Control And Prevention District",
				 * "Granger Municipal Development District",
				 * "Grapeland Municipal Development District",
				 * "Grapevine Crime Control District",
				 * "Gregg County Emergency Services District No. 2",
				 * "Haltom City Crime Control District",
				 * "Hamlin Municipal Development District",
				 * "Hardin County Emergency Services District No. 2",
				 * "Hardin County Emergency Services District No. 4",
				 * "Hardin County Emergency Services District No. 6",
				 * "Harris County Emergency Services District No. 10",
				 * "Harris County Emergency Services District No. 12",
				 * "Harris County Emergency Services District No. 13",
				 * "Harris County Emergency Services District No. 14",
				 * "Harris County Emergency Services District No. 15",
				 * "Harris County Emergency Services District No. 16",
				 * "Harris County Emergency Services District No. 17",
				 * "Harris County Emergency Services District No. 2",
				 * "Harris County Emergency Services District No. 20",
				 * "Harris County Emergency Services District No. 21",
				 * "Harris County Emergency Services District No. 24",
				 * "Harris County Emergency Services District No. 28",
				 * "Harris County Emergency Services District No. 29",
				 * "Harris County Emergency Services District No. 4",
				 * "Harris County Emergency Services District No. 4-A",
				 * "Harris County Emergency Services District No. 46",
				 * "Harris County Emergency Services District No. 47",
				 * "Harris County Emergency Services District No. 48",
				 * "Harris County Emergency Services District No. 5",
				 * "Harris County Emergency Services District No. 50",
				 * "Harris County Emergency Services District No. 6",
				 * "Harris County Emergency Services District No. 60",
				 * "Harris County Emergency Services District No. 7",
				 * "Harris County Emergency Services District No. 80",
				 * "Harris County Emergency Services District No. 9",
				 * "Harris County Improvement District No. 18",
				 * "Harris County-Houston Sports Authority",
				 * "Harris-Fort Bend Emergency Services District No.100",
				 * "Hawk Cove Municipal Development District",
				 * "Hays County Emergency Services District No. 3",
				 * "Hays County Emergency Services District No. 3 - A",
				 * "Hays County Emergency Services District No. 4",
				 * "Hays County Emergency Services District No. 5",
				 * "Hays County Emergency Services District No. 5-A",
				 * "Hays County Emergency Services District No. 6",
				 * "Hays County Emergency Services District No. 6-A",
				 * "Hays County Emergency Services District No. 8",
				 * "Hays County Emergency Services District No. 8 - A",
				 * "Henderson County Emergency Services District No. 6",
				 * "Hill County Emergency Services District No. 2",
				 * "Hill County Emergency Services District No. 2-A",
				 * "Hood County Development District No. 1",
				 * "Houston Metropolitan Transit Authority",
				 * "Hughes Springs Municipal Development District",
				 * "Hurst Crime Control District",
				 * "Iowa Colony Crime Control And Prevention District",
				 * "Jasper County Development District No. 1",
				 * "Jasper County Emergency Services District No. 1",
				 * "Jeff Davis County Emergency Services District No. 1",
				 * "Jeff Davis County Health Services",
				 * "Jefferson County Assistance District No. 4",
				 * "Jersey Village Crime Control District",
				 * "Jim Hogg County Assistance District", "Jim Hogg County Health Services",
				 * "Kaufman County Assistance District No. 1",
				 * "Kaufman County Assistance District No. 2", "Keller Crime Control District",
				 * "Kemp Municipal Development District",
				 * "Kimble County Emergency Services District", "Kinney County Health Services",
				 * "Kirby Crime Control And Prevention District",
				 * "Krum Crime Control And Prevention District", "La Porte Fire Control",
				 * "Prevention And Ems District", "La Vernia Municipal Development District",
				 * "Lake Travis Community Library District",
				 * "Lake Worth Crime Control District",
				 * "Lakewood Village Municipal Development District",
				 * "Laredo City Transit Department", "Las Damas Management District",
				 * "Leon Valley Crime Control And Prevention District",
				 * "Lewisville Crime Control District", "Lewisville Fire Control District",
				 * "Liberty County Emergency Services District No. 3",
				 * "Liberty County Emergency Services District No. 7",
				 * "Liberty County Emergency Services District No. 7-A",
				 * "Liberty County Municipal Management District No. 1",
				 * "Liberty Hill Public Library District",
				 * "Lower Kirby Pearland Management District",
				 * "Lowry Crossing Municipal Development District", "Lucas Fire Control",
				 * "Prevention And Emsd", "Malakoff Crime Control And Prevention District",
				 * "Malone Municipal Development District", "Mason County Health Services",
				 * "Maverick County Lfw & Cdc District", "Mcculloch County Hospital District",
				 * "Medina County Emergency Services District No. 1",
				 * "Medina County Emergency Services District No. 6",
				 * "Midland County Hospital District",
				 * "Montgomery County Emergency Services District No. 1",
				 * "Montgomery County Emergency Services District No. 1-A",
				 * "Montgomery County Emergency Services District No. 1-B",
				 * "Montgomery County Emergency Services District No. 10",
				 * "Montgomery County Emergency Services District No. 14",
				 * "Montgomery County Emergency Services District No. 14-A",
				 * "Montgomery County Emergency Services District No. 2",
				 * "Montgomery County Emergency Services District No. 3",
				 * "Montgomery County Emergency Services District No. 4",
				 * "Montgomery County Emergency Services District No. 6",
				 * "Montgomery County Emergency Services District No. 7",
				 * "Montgomery County Emergency Services District No. 7-A",
				 * "Montgomery County Emergency Services District No. 8",
				 * "Montgomery County Emergency Services District No. 9",
				 * "Montgomery County Management District No. 1",
				 * "Morgan's Point Municipal Development District",
				 * "Murphy Municipal Development District",
				 * "Nacogdoches County Hospital District", "Nasa Area Management District",
				 * "Natalia Municipal Development District",
				 * "Newark Municipal Development District",
				 * "North Hays County Emergency Services District No. 1",
				 * "North Richland Hills Crime Control District",
				 * "Oak Point Municipal Development District",
				 * "Old Town Spring Improvement District",
				 * "Orange County Emergency Services District No. 3",
				 * "Overton Municipal Development District",
				 * "Ovilla Municipal Development District",
				 * "Paducah Municipal Development District",
				 * "Palmhurst Crime Control And Prevention District",
				 * "Palmview Crime Control And Prevention District",
				 * "Palmview Municipal Development District",
				 * "Palo Pinto County Emergency Services District No. 1",
				 * "Palo Pinto County Emergency Services District No. 1-A",
				 * "Palo Pinto County Emergency Services District No. 2",
				 * "Parker County Emergency Services District No. 1",
				 * "Parker County Emergency Services District No. 8",
				 * "Pasadena Crime Control District",
				 * "Pearland Municipal Management District No. 1",
				 * "Penitas Crime Control And Prevention District",
				 * "Point Comfort Municipal Development District",
				 * "Port O'connor Improvement District",
				 * "Potter County Assistance District No. 1",
				 * "Prairie View Crime Control District",
				 * "Presidio Municipal Development District",
				 * "Prosper Crime Control And Prevention District",
				 * "Prosper Fire Control Prevention And Ems District",
				 * "Randall County Assistance District No. 1",
				 * "Reeves County Assistance District No. 1", "Reeves County Hospital District",
				 * "Reno Crime Control And Prevention District", "Rice Hospital District",
				 * "Richland Hills Crime Control And Prevention District",
				 * "Richwood Crime Control And Prevention District",
				 * "Rio Hondo Municipal Development District",
				 * "Rising Star Municipal Development District",
				 * "River Oaks Crime Control District",
				 * "Rockdale Municipal Development District",
				 * "Rockwall County Public Safety And Fire Assistance District",
				 * "Rogers Municipal Development District",
				 * "Sachse Municipal Development District", "Saginaw Crime Control District",
				 * "Salado Public Library District",
				 * "San Antonio Advanced Transportation District",
				 * "San Antonio Metropolitan Transit Authority",
				 * "San Antonio Tourism Public Improvement District",
				 * "San Diego Municipal Development District",
				 * "San Jacinto County Emergency Services District",
				 * "Sansom Park Crime Control District", "Schleicher County Health Services",
				 * "Seabrook Crime Control And Prevention District",
				 * "Selma Municipal Development District",
				 * "Shavano Park Crime Control District",
				 * "Shenandoah Municipal Development District",
				 * "Smith County Emergency Services District No. 2",
				 * "Smith County Emergency Services District No. 2-A",
				 * "Smith County Emergency Services District No. 2-B",
				 * "South Houston Crime Control And Prevention District",
				 * "Southlake Crime Control District", "Staples Municipal Development District",
				 * "Sullivan City Crime Control And Prevention District",
				 * "Sullivan City Municipal Development District",
				 * "Sunset Valley Crime Control And Prevention District",
				 * "Sweetwater Municipal Development District", "The Woodlands Township",
				 * "The Woodlands Township Economical Development Zone",
				 * "Timpson Public Library District",
				 * "Town Center Economic Development Zone No. 2",
				 * "Town Center Economic Development Zone No. 3",
				 * "Town Center Economic Development Zone No. 4",
				 * "Travis County Emergency Service District No. 2 - A",
				 * "Travis County Emergency Services District No. 10",
				 * "Travis County Emergency Services District No. 11",
				 * "Travis County Emergency Services District No. 11-A",
				 * "Travis County Emergency Services District No. 12",
				 * "Travis County Emergency Services District No. 12 - A",
				 * "Travis County Emergency Services District No. 12-A (1)",
				 * "Travis County Emergency Services District No. 14",
				 * "Travis County Emergency Services District No. 2",
				 * "Travis County Emergency Services District No. 3",
				 * "Travis County Emergency Services District No. 4",
				 * "Travis County Emergency Services District No. 4-A",
				 * "Travis County Emergency Services District No. 5",
				 * "Travis County Emergency Services District No. 5-A",
				 * "Travis County Emergency Services District No. 6",
				 * "Travis County Emergency Services District No. 6-A",
				 * "Travis County Emergency Services District No. 8",
				 * "Travis County Emergency Services District No. 8 - A",
				 * "Travis County Emergency Services District No. 9",
				 * "Trophy Crime Control And Prevention District",
				 * "Tuscola Crime Control And Prevention District",
				 * "Tuscola Municipal Development District",
				 * "Tyler County Emergency Services District No. 2",
				 * "Uvalde County Health Services", "Waco Tourism Public Improvement District",
				 * "Walker County Emergency Services District No. 1",
				 * "Walker County Emergency Services District No. 2",
				 * "Watauga Crime Control District", "Water-Oriented Recreation District",
				 * "Wells Branch Community Library District",
				 * "Westbank Community Library District",
				 * "Westside 211 Special Improvement District",
				 * "Westwood Magnolia Parkway Improvement District",
				 * "Westwood Magnolia Parkway Improvement District A",
				 * "Westworth Village Crime Control District",
				 * "White Settlement Crime Control District",
				 * "Williamson County Emergency Services District No. 1",
				 * "Williamson County Emergency Services District No. 3",
				 * "Williamson County Emergency Services District No. 4",
				 * "Williamson County Emergency Services District No. 5",
				 * "Williamson County Emergency Services District No. 7",
				 * "Williamson County Esd No 3 Combined Area-1",
				 * "Wilson County Emergency Services District No. 1",
				 * "Wilson County Emergency Services District No. 2",
				 * "Wimberley Village Library District",
				 * "Windcrest Crime Control And Prevention District",
				 * "Winkler County Health Services", "Winnie-Stowell Hospital District",
				 * "Wood Trace Management District", "Zapata County Assistance District",
				 * "Zavala County Assistance District" }; for (String text : C) {
				 * all_elements_text1.add(text); } all_elements_text1.equals(all_elements_text);
				 * } if (issuelist.equalsIgnoreCase("County")) { Select Jurisdtype = new
				 * Select(driver.findElement(By.xpath(CE.JURSDTYPE)));
				 * Jurisdtype.selectByVisibleText(issuelist); Thread.sleep(5000);
				 * List<WebElement> length = driver
				 * .findElements(By.xpath("//select[@id='ddlIssuingJurisdiction']/option"));
				 * List<String> all_elements_text = new ArrayList<>();
				 * System.out.println(state); System.out.println(issuelist); for (int i = 0; i <
				 * length.size(); i++) { if (!length.get(i).getText().isEmpty()) {
				 * all_elements_text.add(length.get(i).getText()); } }
				 * System.out.println(all_elements_text); List<String> all_elements_text1 = new
				 * ArrayList<>(); String C[] = { "Anderson", "Andrews", "Angelina", "Aransas",
				 * "Archer", "Armstrong", "Atascosa", "Austin", "Bailey", "Bandera", "Bastrop",
				 * "Baylor", "Bee", "Bell", "Bexar", "Blanco", "Borden", "Bosque", "Bowie",
				 * "Brazoria", "Brazos", "Brewster", "Briscoe", "Brooks", "Brown", "Burleson",
				 * "Burnet", "Caldwell", "Calhoun", "Callahan", "Cameron", "Camp", "Carson",
				 * "Cass", "Castro", "Chambers", "Cherokee", "Childress", "Clay", "Cochran",
				 * "Coke", "Coleman", "Collin", "Collingsworth", "Colorado", "Comal",
				 * "Comanche", "Concho", "Cooke", "Coryell", "Cottle", "Crane", "Crockett",
				 * "Crosby", "Culberson", "Dallam", "Dallas", "Dawson", "Deaf Smith", "Delta",
				 * "Denton", "Dewitt", "Dickens", "Dimmit", "Donley", "Duval", "Eastland",
				 * "Ector", "Edwards", "El Paso", "Ellis", "Erath", "Falls", "Fannin",
				 * "Fayette", "Fisher", "Floyd", "Foard", "Fort Bend", "Franklin", "Freestone",
				 * "Frio", "Gaines", "Galveston", "Garza", "Gillespie", "Glasscock", "Goliad",
				 * "Gonzales", "Gray", "Grayson", "Gregg", "Grimes", "Guadalupe", "Hale",
				 * "Hall", "Hamilton", "Hansford", "Hardeman", "Hardin", "Harris", "Harrison",
				 * "Hartley", "Haskell", "Hays", "Hemphill", "Henderson", "Hidalgo", "Hill",
				 * "Hockley", "Hood", "Hopkins", "Houston", "Howard", "Hudspeth", "Hunt",
				 * "Hutchinson", "Irion", "Jack", "Jackson", "Jasper", "Jeff Davis",
				 * "Jefferson", "Jim Hogg", "Jim Wells", "Johnson", "Jones", "Karnes",
				 * "Kaufman", "Kendall", "Kenedy", "Kent", "Kerr", "Kimble", "King", "Kinney",
				 * "Kleberg", "Knox", "La Salle", "Lamar", "Lamb", "Lampasas", "Lavaca", "Lee",
				 * "Leon", "Liberty", "Limestone", "Lipscomb", "Live Oak", "Llano", "Loving",
				 * "Lubbock", "Lynn", "Madison", "Marion", "Martin", "Mason", "Matagorda",
				 * "Maverick", "Mcculloch", "Mclennan", "Mcmullen", "Medina", "Menard",
				 * "Midland", "Milam", "Mills", "Mitchell", "Montague", "Montgomery", "Moore",
				 * "Morris", "Motley", "Nacogdoches", "Navarro", "Newton", "Nolan", "Nueces",
				 * "Ochiltree", "Oldham", "Orange", "Palo Pinto", "Panola", "Parker", "Parmer",
				 * "Pecos", "Polk", "Potter", "Presidio", "Rains", "Randall", "Reagan", "Real",
				 * "Red River", "Reeves", "Refugio", "Roberts", "Robertson", "Rockwall",
				 * "Runnels", "Rusk", "Sabine", "San Augustine", "San Jacinto", "San Patricio",
				 * "San Saba", "Schleicher", "Scurry", "Shackelford", "Shelby", "Sherman",
				 * "Smith", "Somervell", "Starr", "Stephens", "Sterling", "Stonewall", "Sutton",
				 * "Swisher", "Tarrant", "Taylor", "Terrell", "Terry", "Throckmorton", "Titus",
				 * "Tom Green", "Travis", "Trinity", "Tyler", "Upshur", "Upton", "Uvalde",
				 * "Val Verde", "Van Zandt", "Victoria", "Walker", "Waller", "Ward",
				 * "Washington", "Webb", "Wharton", "Wheeler", "Wichita", "Wilbarger",
				 * "Willacy", "Williamson", "Wilson", "Winkler", "Wise", "Wood", "Yoakum",
				 * "Young", "Zapata", "Zavala" }; for (String text : C) {
				 * all_elements_text1.add(text); } all_elements_text1.equals(all_elements_text);
				 * } } break;
				 */
				}
			}
		}
	}

	public void certificateEndDate(String Company, String Customer_Name, String Cert_Exception_EndDate)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		strCustName = "";
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		Thread.sleep(3000);
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		waitforElement(180, CE.PAGINATION, driver);
		Thread.sleep(5000);
		click(CE.SearchByName);
		type(CE.SEARCH, Customer_Name);
		CE.SEARCH.sendKeys(Keys.ENTER);
		waitforElement(180, "//td[contains(.,'" + Customer_Name + "')]/../td/div/a", driver);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'View')]",
				driver);
		WebElement view = driver.findElement(
				By.xpath("//table/tbody/tr/td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'View')]"));
		JavaScriptclick(driver, view);
		if (VerifyObject(driver, "//button[@class='close drawer__btn-close']")) {
			WebElement closeaddcustomer = driver.findElement(By.xpath("//button[@class='close drawer__btn-close']"));
			JavaScriptclick(driver, closeaddcustomer);
			Thread.sleep(2000);
		}
		waitforElement(180, CE.ADDCERTIFICATE, driver);
		JavaScriptclick(driver, CE.ADDCERTIFICATE);
		waitforElement(180, CE.CUSTOMERINFOLINK, driver);
		if (!VerifyObject(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN)) {
			JavaScriptclick(driver, CE.CUSTOMERINFOLINK);
		}
		waitforElement(180, CE.SEARCHCUSTOMERADDCERTFSCREEN, driver);
		JavaScriptclick(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN);
		type(CE.SEARCHCUSTOMERADDCERTFSCREEN, Customer_Name);
		waitforElement(180, "//a/span[contains(.,'" + Customer_Name + "')]", driver);
		Thread.sleep(2000);
		WebElement customer = driver.findElement(By.xpath("//a/span[contains(.,'" + Customer_Name + "')]"));
		JavaScriptclick(driver, customer);
		waitforElement(180, CE.CertificateInfoLink, driver);
		JavaScriptclick(driver, CE.CertificateInfoLink);
		waitforElement(180, CE.EXCEPTYPE, driver);
		if (Cert_Exception_EndDate != "") {
			clear(CE.EXCEPENDDATE);
			type(CE.EXCEPENDDATE, Cert_Exception_EndDate);
			sendkeysTab(CE.EXCEPENDDATE);
		}
		if (VerifyObject(driver, CE.SAVECERTIFICATE)) {
			JavaScriptclick(driver, CE.SAVECERTIFICATE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}

		if (VerifyObject(driver, CE.RETURNTOCERTIFICATELIST)) {
			JavaScriptclick(driver, CE.RETURNTOCERTIFICATELIST);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, CE.ADDEXCEPTION)) {
			JavaScriptclick(driver, CE.ADDEXCEPTION);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.CUSTOMERLISTING)) {
			JavaScriptclick(driver, CE.CUSTOMERLISTING);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.VIEWALLEXCEPTIONS)) {
			JavaScriptclick(driver, CE.VIEWALLEXCEPTIONS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, CE.READYTOSAVE, driver);
		}
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);

	}

	public void removeCustomerEndDate(String Company, String CodeValue, String Cus_End_Date) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		strCustName = "";
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		Thread.sleep(3000);
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		JavaScripttype(driver, CE.SEARCH, CodeValue);
		Thread.sleep(2000);
		CE.SEARCH.sendKeys(Keys.CONTROL + "a");
		CE.SEARCH.sendKeys(Keys.CONTROL + "x");
		Thread.sleep(2000);
		CE.SEARCH.sendKeys(Keys.CONTROL + "v");
		Thread.sleep(5000);
		WebElement customerclick = driver.findElement(By.xpath("//td[contains(.,'" + CodeValue + "')]/../td[9]/div/a"));
		JavaScriptscrolltoView(driver, customerclick);
		Thread.sleep(2000);
		JavaScriptclick(driver, customerclick);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//tbody/tr[1]/td/div/ul/li/a[contains(.,'Edit')]")).click();
		waitforElement(90, CE.ENDDATE_CUST, driver);
		JavaScriptclick(driver, CE.ENDDATE_CUST);
		clear(CE.ENDDATE_CUST);
		type(CE.ENDDATE_CUST, Cus_End_Date);
		sendkeysTab(CE.ENDDATE_CUST);
		Thread.sleep(3000);
		click(CE.SAVEANDCLOSE);
		Thread.sleep(5000);
		if (VerifyObject(driver, CE.READYTOSAVE)) {
			click(CE.READYTOSAVE);
		}
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}

	}

	public void removecertificateEndDate(String Company, String Customer_Name, String Cert_Exception_EndDate)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		strCustName = "";
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver, CNP.STATEINPROCESS)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000); // For O Series sync refreshing the page
				if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
		} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
			WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Company
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
		}
		Thread.sleep(3000);
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		waitforElement(180, CE.PAGINATION, driver);
		Thread.sleep(5000);
		click(CE.SearchByName);
		type(CE.SEARCH, Customer_Name);
		CE.SEARCH.sendKeys(Keys.ENTER);
		waitforElement(180, "//td[contains(.,'" + Customer_Name + "')]/../td/div/a", driver);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'View')]",
				driver);
		WebElement view = driver.findElement(
				By.xpath("//table/tbody/tr/td[contains(.,'" + Customer_Name + "')]/../td/span/a[contains(.,'View')]"));
		JavaScriptclick(driver, view);
		if (VerifyObject(driver, "//button[@class='close drawer__btn-close']")) {
			WebElement closeaddcustomer = driver.findElement(By.xpath("//button[@class='close drawer__btn-close']"));
			JavaScriptclick(driver, closeaddcustomer);
			Thread.sleep(2000);
		}
		waitforElement(180, CE.ADDCERTIFICATE, driver);
		JavaScriptclick(driver, CE.ADDCERTIFICATE);
		waitforElement(180, CE.CUSTOMERINFOLINK, driver);
		if (!VerifyObject(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN)) {
			JavaScriptclick(driver, CE.CUSTOMERINFOLINK);
		}
		waitforElement(180, CE.SEARCHCUSTOMERADDCERTFSCREEN, driver);
		JavaScriptclick(driver, CE.SEARCHCUSTOMERADDCERTFSCREEN);
		type(CE.SEARCHCUSTOMERADDCERTFSCREEN, Customer_Name);
		waitforElement(180, "//a/span[contains(.,'" + Customer_Name + "')]", driver);
		Thread.sleep(2000);
		WebElement customer = driver.findElement(By.xpath("//a/span[contains(.,'" + Customer_Name + "')]"));
		JavaScriptclick(driver, customer);
		waitforElement(180, CE.CertificateInfoLink, driver);
		JavaScriptclick(driver, CE.CertificateInfoLink);
		waitforElement(180, CE.EXCEPTYPE, driver);
		if (Cert_Exception_EndDate != "") {
			clear(CE.EXCEPENDDATE);
			// type(CE.EXCEPENDDATE, Cert_Exception_EndDate);
			sendkeysTab(CE.EXCEPENDDATE);
		}
		if (VerifyObject(driver, CE.SAVECERTIFICATE)) {
			JavaScriptclick(driver, CE.SAVECERTIFICATE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}

		if (VerifyObject(driver, CE.RETURNTOCERTIFICATELIST)) {
			JavaScriptclick(driver, CE.RETURNTOCERTIFICATELIST);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, CE.ADDEXCEPTION)) {
			JavaScriptclick(driver, CE.ADDEXCEPTION);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.CUSTOMERLISTING)) {
			JavaScriptclick(driver, CE.CUSTOMERLISTING);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		if (VerifyObject(driver, CE.VIEWALLEXCEPTIONS)) {
			JavaScriptclick(driver, CE.VIEWALLEXCEPTIONS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, CE.READYTOSAVE, driver);
		}
	}

	// Tax assist Rules
	public void AddTaxAssistRule(String Company, String RuleName, String FieldsVal, String ConclusionSetVal,
			String ConclusionToVal) throws Exception {
		String java_script = "";
		File directory = new File(".//");
		TaxassistantRule TAR = PageFactory.initElements(driver, TaxassistantRule.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, TAR.CONFIGURELINK, driver);
		JavaScriptclick(driver, TAR.CONFIGURELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		waitforText(180, TAR.TaxassistantHeader, driver);
		waitforElement(180, "//h2[contains(text(),'" + Company
				+ "')]/../../../../div/div/table/tbody/tr/td/a[contains(.,'All Tax Assist Rules')]", driver);
		driver.findElement(By.xpath("//h2[contains(text(),'" + Company
				+ "')]/../../../../div/div/table/tbody/tr/td/a[contains(.,'All Tax Assist Rules')]")).click();
		waitforElement(180, TAR.AddRule, driver);
		JavaScriptclick(driver, TAR.Tax_Assist_Rules_Link);
		waitforElement(180, TAR.AddRule, driver);
		JavaScriptclick(driver, TAR.AddRule);
		waitforElement(180, TAR.AddTaxAssistant, driver);
		waitforElement(180, TAR.nameInput, driver);
		type(TAR.nameInput, RuleName);
		String jquerySimulator = new String(Files.readAllBytes(
				Paths.get(directory.getCanonicalPath() + "\\" + "resources\\config" + "\\" + "JQUeryEvent.js")));
		java_script = jquerySimulator;
		java_script = java_script + "simulateHTML5DragAndDrop(arguments[0], arguments[1])";
		JavaScriptclick(driver, TAR.ConclusionLink);
		Thread.sleep(5000);
		WebElement SETOP1 = driver.findElement(By.xpath("//div/span[contains(.,'" + FieldsVal + "')]"));
		WebElement SETcon1 = driver
				.findElement(By.xpath("//div[1][@class='conclusion']/div[contains(.,'SET')]/../div[2]/div"));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript(java_script, SETOP1, SETcon1);
		waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
		waitforText(180, TAR.ADDCONTEXTlink, driver);
		driver.findElement(By.xpath("//label[contains(.,'Filter:')]/../input")).sendKeys(ConclusionSetVal);
		waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
		WebElement Vl1 = driver
				.findElement(By.xpath("//div[@class='span12 options']/ul/li[@data-value='" + ConclusionSetVal + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Vl1);
		JavaScriptclick(driver, TAR.ADDcontext_CLoselink);
		Thread.sleep(2000);
		WebElement ToVal1 = driver.findElement(By.xpath("//div/h2[contains(text(),'Values')]/../div[3]/div[1]"));
		WebElement ToCon1 = driver
				.findElement(By.xpath("//div[1][@class='conclusion']/div[contains(.,'TO')]/../div[4]/div"));
		((JavascriptExecutor) driver).executeScript(java_script, ToVal1, ToCon1);
		Thread.sleep(5000);
		driver.findElement(
				By.xpath("//div[@class='conclusion']/div[contains(.,'TO')]/../div[4]/div/div/div/div/div/input"))
				.sendKeys(ConclusionToVal);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(.,'Scratch')]/../div[@class='scratch-pad']/div/span")).click();
		Thread.sleep(2000);
		JavaScriptclick(driver, TAR.SAVERULE);
		waitforText(180, "//td[contains(.,'" + RuleName + "')]", driver);
		String TaxRulueName = driver.findElement(By.xpath("//tr[2]/td[1]")).getText();
		assertTrue(RuleName.equalsIgnoreCase(TaxRulueName));

	}

	public void UIverifyTaxassistRule(String Lookuptables_Verify, String AllocationTables_Verify) throws Exception {
		TaxassistantRule PUL = PageFactory.initElements(driver, TaxassistantRule.class);
		JavaScriptclick(driver, PUL.Tax_Assist_Rules_Link);
		waitforElement(180, PUL.AddRule, driver);
		assertTrue(PUL.AddRule.isDisplayed());
		assertTrue(PUL.TaxAssistHeaderText.isDisplayed());
		assertTrue(PUL.Tax_Assist_Rules_Link.isDisplayed());
		assertTrue(PUL.LookupTableslink.isDisplayed());
		assertTrue(PUL.Allocationtablelink.isDisplayed());
		assertTrue(PUL.AsofDatelabel.isDisplayed());
		assertTrue(xGetDate("MM/dd/yyyy").equalsIgnoreCase(getAttribute(PUL.AsofDate, "value")));
		assertTrue(PUL.BusinessEvent.isDisplayed());
		assertTrue(PUL.BusinessEventDropdown.isDisplayed());
		String sup = driver
				.findElement(By.xpath("//div/label[contains(.,'Business Event')]/../../div/select/option[1]"))
				.getText();
		String Pro = driver
				.findElement(By.xpath("//div/label[contains(.,'Business Event')]/../../div/select/option[2]"))
				.getText();
		assertTrue(sup.equalsIgnoreCase("Supplies"));
		assertTrue(Pro.equalsIgnoreCase("Procurement"));
		assertTrue(PUL.Tax_Assist_Rule_Search_Results.isDisplayed());
		assertTrue(PUL.Phaselabel.isDisplayed());
		assertTrue(PUL.Filterlabel.isDisplayed());
		assertTrue(PUL.FilterInput.isDisplayed());
		assertTrue(PUL.Phasedropdown.isDisplayed());
		String PreCal = driver.findElement(By.xpath("//div/label[contains(.,'Phase')]/../../div/select/option[1]"))
				.getText();
		String ProCal = driver.findElement(By.xpath("//div/label[contains(.,'Phase')]/../../div/select/option[2]"))
				.getText();
		assertTrue(PreCal.equalsIgnoreCase("Pre-Calculation"));
		assertTrue(ProCal.equalsIgnoreCase("Post-Calculation"));
		assertTrue(PUL.Nameheader.isDisplayed());
		assertTrue(PUL.Phaseheader.isDisplayed());
		assertTrue(PUL.Start_Date_Column.isDisplayed());
		/*
		 * assertTrue(PUL.End_Date_Column.isDisplayed());
		 * assertTrue(PUL.View.isDisplayed()); assertTrue(PUL.Edit.isDisplayed());
		 * assertTrue(PUL.Clone.isDisplayed());
		 */
		if (Lookuptables_Verify.equalsIgnoreCase("YES")) {
			waitforElement(180, PUL.Lookuptables, driver);
			JavaScriptclick(driver, PUL.Lookuptables);
			waitforElement(180, PUL.AddLookuptable, driver);
			assertTrue(PUL.lookuptablesearchresults.isDisplayed());
			assertTrue(PUL.AddLookuptable.isDisplayed());
			assertTrue(PUL.NameHeader.isDisplayed());
			assertTrue(PUL.ResultNameHeader.isDisplayed());
			assertTrue(PUL.Datatype.isDisplayed());
			assertTrue(PUL.Startdate.isDisplayed());
			assertTrue(PUL.Enddate.isDisplayed());
			assertTrue(xGetDate("MM/dd/yyyy").equalsIgnoreCase(getAttribute(PUL.AsofDate, "value")));
			JavaScriptclick(driver, PUL.AddLookuptable);
			waitforElement(180, PUL.PROPERTIES, driver);
			assertTrue(PUL.LookupNameinput.isDisplayed());
			assertTrue(PUL.LookupDescription.isDisplayed());
			assertTrue(PUL.LookupResultname.isDisplayed());
			assertTrue(PUL.Lookupdatatype.isDisplayed());
			assertTrue(PUL.LookupStartdate.isDisplayed());
			assertTrue(PUL.LookupEnddate.isDisplayed());
			assertTrue(PUL.Parameter1.isDisplayed());
			assertTrue(PUL.Parameter2.isDisplayed());
			assertTrue(PUL.Parameter3.isDisplayed());
			assertTrue(PUL.Parameter4.isDisplayed());
			assertTrue(PUL.Parameter5.isDisplayed());
			assertTrue(PUL.Savelookuptable.isDisplayed());
			assertTrue(PUL.Cancel.isDisplayed());
			waitforElement(180, PUL.Records, driver);
			JavaScriptclick(driver, PUL.Records);
			assertTrue(PUL.Import.isDisplayed());
			assertTrue(PUL.AddRecord.isDisplayed());
			assertTrue(PUL.Recordsstartdate.isDisplayed());
			assertTrue(PUL.RecordsEnddate.isDisplayed());
			assertTrue(PUL.Savelookuptable.isDisplayed());
			assertTrue(PUL.Cancel.isDisplayed());
		}
		if (AllocationTables_Verify.equalsIgnoreCase("YES")) {
			waitforElement(180, PUL.ALLOCATIONTABLES, driver);
			JavaScriptclick(driver, PUL.ALLOCATIONTABLES);
			waitforElement(180, PUL.ADDALLOCATIONTABLE, driver);
			assertTrue(PUL.ADDALLOCATIONTABLE.isDisplayed());
			assertTrue(PUL.ALLOCATIONTABLESEARCHRESULTS.isDisplayed());
			assertTrue(PUL.AllocationTableName.isDisplayed());
			assertTrue(PUL.AllocationStartDate.isDisplayed());
			assertTrue(PUL.AllocationEndDate.isDisplayed());
			JavaScriptclick(driver, PUL.ADDALLOCATIONTABLE);
			waitforElement(180, PUL.AllocationTablename, driver);
			assertTrue(PUL.AllocationTablename.isDisplayed());
			assertTrue(PUL.Allocationdescription.isDisplayed());
			assertTrue(PUL.LookupStartdate.isDisplayed());
			assertTrue(PUL.LookupEnddate.isDisplayed());
			assertTrue(PUL.Column1.isDisplayed());
			assertTrue(PUL.Saveallocationtable.isDisplayed());
			assertTrue(PUL.Cancel.isDisplayed());
			waitforElement(180, PUL.Records, driver);
			JavaScriptclick(driver, PUL.Records);
			assertTrue(PUL.Import.isDisplayed());
			assertTrue(PUL.ImportUpdate.isDisplayed());
			assertTrue(PUL.AddRecord.isDisplayed());
			assertTrue(PUL.AllocationCode.isDisplayed());
			assertTrue(PUL.Recordsstartdate.isDisplayed());
			assertTrue(PUL.RecordsEnddate.isDisplayed());
			assertTrue(PUL.Saveallocationtable.isDisplayed());
			assertTrue(PUL.Cancel.isDisplayed());
		}

	}

	public void AddTaxAssistRule(String RuleName, String PhaseType, String Operator1, String Operator2, String Val1,
			String FieldsVal, String Val2, String Operator3, String Val3, String Add_lookup, String LookupField,
			String LookupTableName, String lookupValue, String Val4, String ConclusionSetVal, String ConclusionSetType,
			String ConclusionToVal, String ConclusionToType, String Add_Allocation_Conclusion) throws Exception {
		String java_script = "";
		File directory = new File(".//");
		TaxassistantRule PUL = PageFactory.initElements(driver, TaxassistantRule.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String jquerySimulator = new String(Files.readAllBytes(
				Paths.get(directory.getCanonicalPath() + "\\" + "resources\\config" + "\\" + "JQUeryEvent.js")));
		java_script = jquerySimulator;
		java_script = java_script + "simulateHTML5DragAndDrop(arguments[0], arguments[1])";
		waitforElement(180, PUL.CONFIGURELINK, driver);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.TaxassistantHeader, driver);
		waitforElement(180, PUL.ViewLink, driver);
		click(PUL.ViewLink);
		waitforElement(180, PUL.AddRule, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			click(PUL.Tax_Assist_Rules_Link);
			waitforElement(180, PUL.AddRule, driver);
			click(PUL.AddRule);
		} else {
			JavaScriptclick(driver, PUL.Tax_Assist_Rules_Link);
			waitforElement(180, PUL.AddRule, driver);
			JavaScriptclick(driver, PUL.AddRule);
		}

		waitforElement(180, PUL.AddTaxAssistant, driver);
		type(PUL.nameInput, RuleName);
		selectByValue(PUL.phaseinput, PhaseType);
		JavaScriptclick(driver, PUL.ConditionLink);
		assertTrue(PUL.ScratchPad.isDisplayed());
		WebElement OP1 = driver.findElement(By.xpath("//div/span[contains(.,'" + Operator1 + "')]"));
		WebElement Con1 = driver
				.findElement(By.xpath("//div[@class='condition-container']/div[contains(.,'IF')]/../div[2]/div"));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript(java_script, OP1, Con1);
		Thread.sleep(2000);
		if (Operator2 != "") {
			WebElement Op2 = driver.findElement(By.xpath("//div/span[contains(.,'" + Operator2 + "')]"));
			String Opval1 = Operator1;
			if (Opval1.equalsIgnoreCase("AND") || Opval1.equalsIgnoreCase("OR")) {
				WebElement Con2 = driver.findElement(By.xpath("//div[@class='block-children']/div[1]"));
				WebElement Con3 = driver.findElement(By.xpath("//div[@class='block-children']/div[2]"));
				Thread.sleep(5000);
				((JavascriptExecutor) driver).executeScript(java_script, Op2, Con2);
				Thread.sleep(5000);
				if (Val1 != "") {
					WebElement Con2Val = driver.findElement(By.xpath("//div/span[contains(.,'" + FieldsVal + "')]"));
					WebElement Con2valCel = driver.findElement(
							By.xpath("//div[@class='block-children']/div[@class='expression-block'][1]/div[1]"));
					((JavascriptExecutor) driver).executeScript(java_script, Con2Val, Con2valCel);
					Thread.sleep(3000);
					waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
					driver.findElement(By.xpath("//label[contains(.,'Filter:')]/../input")).sendKeys(Val1);
					waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
					WebElement Vl1 = driver
							.findElement(By.xpath("//div[@class='span12 options']/ul/li[@data-value='" + Val1 + "']"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Vl1);

					// driver.findElement(By.xpath("//div[@class='span12
					// options']/ul/li[contains(.,'"+Rulessheet.getCell(Val1,
					// j).getContents()+"')]")).click();
				}
				if (Val2 != "") {
					WebElement Con2Val = driver
							.findElement(By.xpath("//h2[contains(text(),'Values')]/../div/div[1]/img[1]"));
					WebElement Con2valCel = driver.findElement(
							By.xpath("//div[@class='block-children']/div[@class='expression-block'][1]/div[3]"));
					((JavascriptExecutor) driver).executeScript(java_script, Con2Val, Con2valCel);
					Thread.sleep(3000);
					driver.findElement(By
							.xpath("//div[@class='block-children']/div[@class='expression-block'][1]/div[3]/div/input"))
							.click();
					driver.findElement(By
							.xpath("//div[@class='block-children']/div[@class='expression-block'][1]/div[3]/div/input"))
							.sendKeys(Val2);
				}
				if (Operator3.equalsIgnoreCase("=") || Operator3.equalsIgnoreCase("<>")) {
					WebElement Op3 = driver.findElement(By.xpath("//div/span[contains(.,'" + Operator3 + "')]"));
					((JavascriptExecutor) driver).executeScript(java_script, Op3, Con3);
					Thread.sleep(5000);
					if (Val3 != "") {
						WebElement Con2Val = driver
								.findElement(By.xpath("//div/span[contains(.,'" + FieldsVal + "')]"));
						WebElement Con2valCel = driver.findElement(
								By.xpath("//div[@class='block-children']/div[@class='expression-block'][2]/div[1]"));
						((JavascriptExecutor) driver).executeScript(java_script, Con2Val, Con2valCel);
						Thread.sleep(3000);
						waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
						driver.findElement(By.xpath("//label[contains(.,'Filter:')]/../input")).sendKeys(Val3);
						waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
						WebElement Vl3 = driver.findElement(
								By.xpath("//div[@class='span12 options']/ul/li[@data-value='" + Val3 + "']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", Vl3);
						// driver.findElement(By.xpath("//div[@class='span12
						// options']/ul/li[contains(.,'"+Rulessheet.getCell(Val3,
						// j).getContents()+"')]")).click();
					}
					if (Add_lookup.equalsIgnoreCase("Yes")) {
						click(PUL.Advancedlink);
						WebElement Con2Val = driver.findElement(By.xpath(
								"//div/h2[contains(text(),'Lookups')]/../div[3]/div/span['" + LookupField + "']"));
						WebElement Con2valCel = driver.findElement(
								By.xpath("//div[@class='block-children']/div[@class='expression-block'][2]/div[1]"));
						((JavascriptExecutor) driver).executeScript(java_script, Con2Val, Con2valCel);
						Thread.sleep(5000);
						click(PUL.BASIClink);
						// driver.findElement(By.xpath("//div[@class='block-children']/div[@class='expression-block']/div/div/div/div[@class='string-expression']")).sendKeys(Rulessheet.getCell(LookupTableName,
						// j).getContents());
						WebElement lookuptable = driver.findElement(By.xpath(
								"//div[@class='block-children']/div[@class='expression-block']/div/div/div/div[@class='string-expression']"));
						JavaScriptclick(driver, lookuptable);
						Thread.sleep(2000);
						JavaScripttype(driver, lookuptable, LookupTableName);
						WebElement Val = driver
								.findElement(By.xpath("//h2[contains(text(),'Values')]/../div/div[1]/img[1]"));
						WebElement Target = driver.findElement(By.xpath(
								"//div[@class='block-children']/div[@class='expression-block'][2]/div[1]/div/div/div/span"));
						((JavascriptExecutor) driver).executeScript(java_script, Val, Target);
						Thread.sleep(2000);
						driver.findElement(By.xpath(
								"//div[@class='block-children']/div[@class='expression-block'][2]/div[1]/div/div/div/div/input"))
								.click();
						driver.findElement(By.xpath(
								"//div[@class='block-children']/div[@class='expression-block'][2]/div[1]/div/div/div/div/input"))
								.sendKeys(lookupValue);
					}
					if (Val4 != "") {
						WebElement Con2Val = driver
								.findElement(By.xpath("//h2[contains(text(),'Values')]/../div/div[1]/img[1]"));
						WebElement Con2valCel = driver.findElement(
								By.xpath("//div[@class='block-children']/div[@class='expression-block'][2]/div[3]"));
						((JavascriptExecutor) driver).executeScript(java_script, Con2Val, Con2valCel);
						Thread.sleep(2000);
						driver.findElement(By.xpath(
								"//div[@class='block-children']/div[@class='expression-block'][2]/div[3]/div/input"))
								.click();
						driver.findElement(By.xpath(
								"//div[@class='block-children']/div[@class='expression-block'][2]/div[3]/div/input"))
								.sendKeys(Val4);
						driver.findElement(By.xpath("//div[@class='scratch-pad']/div/span")).click();
						Thread.sleep(2000);
					}
				}
			}
			if (Opval1.equalsIgnoreCase("<") || Opval1.equalsIgnoreCase(">") || Opval1.equalsIgnoreCase("<>")
					|| Opval1.equalsIgnoreCase("=") || Opval1.equalsIgnoreCase("<=") || Opval1.equalsIgnoreCase(">=")) {
				WebElement Con2 = driver.findElement(By.xpath("//div[@class='block-children']/div[1]"));
				WebElement Con3 = driver.findElement(By.xpath("//div[@class='block-children']/div[2]"));
				Thread.sleep(5000);
				((JavascriptExecutor) driver).executeScript(java_script, Op2, Con2);
				Thread.sleep(5000);
				((JavascriptExecutor) driver).executeScript(java_script, Op2, Con3);
				Thread.sleep(2000);

			}
			if (Operator3 != "") {
				if (Operator2.equalsIgnoreCase("OR") || Operator2.equalsIgnoreCase("AND")) {
					WebElement Op4 = driver.findElement(By.xpath("//div/span[contains(.,'" + Operator3 + "')]"));
					String Opval2 = Operator2;
					// to drag the third operator
					if (Opval2.equalsIgnoreCase("AND") || Opval2.equalsIgnoreCase("OR")) {
						WebElement Con2 = driver.findElement(
								By.xpath("//div[@class='block-children']/div[1]/div[@class='block-children']/div[1]"));
						WebElement Con3 = driver.findElement(
								By.xpath("//div[@class='block-children']/div[1]/div[@class='block-children']/div[2]"));
						Thread.sleep(5000);
						((JavascriptExecutor) driver).executeScript(java_script, Op4, Con2);
						Thread.sleep(5000);
						((JavascriptExecutor) driver).executeScript(java_script, Op4, Con3);
						Thread.sleep(2000);

						// to send the Field for first operator in first value
						WebElement SETOP1 = driver.findElement(By.xpath("//div/span[contains(.,'" + FieldsVal + "')]"));
						WebElement Value11 = driver.findElement(By.xpath(
								"//div[@class='block-children']/div[1]/div[@class='block-children']/div[1]/div[1]"));
						((JavascriptExecutor) driver).executeScript(java_script, SETOP1, Value11);
						Thread.sleep(5000);
						waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
						driver.findElement(By.xpath("//label[contains(.,'Filter:')]/../input")).sendKeys("billingType");
						waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
						driver.findElement(By.xpath("//div[@class='span12 options']/ul/li[1]")).click();

						driver.findElement(By.xpath("html/body/div[2]/div[2]/div/div[1]/div/div")).click();
						;
						Thread.sleep(5000);
						// to send the value for first operator in Second value
						WebElement ToVal1 = driver
								.findElement(By.xpath("//div/h2[contains(text(),'Values')]/../div[3]/div[1]"));
						WebElement ToCon11 = driver.findElement(By.xpath(
								"//div[@class='block-children']/div[1]/div[@class='block-children']/div[1]/div[3]"));
						((JavascriptExecutor) driver).executeScript(java_script, ToVal1, ToCon11);
						driver.findElement(By.xpath(
								"//div[@class='block-children']/div[1]/div[@class='block-children']/div[1]/div[3]/div/input"))
								.sendKeys("Test");
						Thread.sleep(5000);
						// to send the Field for Second operator in first value

						WebElement Value21 = driver.findElement(By.xpath(
								"//div[@class='block-children']/div[1]/div[@class='block-children']/div[2]/div[1]"));
						((JavascriptExecutor) driver).executeScript(java_script, SETOP1, Value21);
						Thread.sleep(5000);
						waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
						driver.findElement(By.xpath("//label[contains(.,'Filter:')]/../input")).sendKeys("costCenter");
						waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
						driver.findElement(By.xpath("//div[@class='span12 options']/ul/li[1]")).click();
						driver.findElement(By.xpath("html/body/div[2]/div[2]/div/div[1]/div/div")).click();

						// to send the value for Second operator in Second value
						Thread.sleep(5000);
						WebElement ToCon22 = driver.findElement(By.xpath(
								"//div[@class='block-children']/div[1]/div[@class='block-children']/div[2]/div[3]"));
						((JavascriptExecutor) driver).executeScript(java_script, ToVal1, ToCon22);
						driver.findElement(By.xpath(
								"//div[@class='block-children']/div[1]/div[@class='block-children']/div[2]/div[3]/div/input"))
								.sendKeys("Test");

						// Third Variable
						Thread.sleep(5000);
						WebElement Con4 = driver.findElement(
								By.xpath("//div[@class='block-children']/div[2]/div[@class='block-children']/div[1]"));
						WebElement Con5 = driver.findElement(
								By.xpath("//div[@class='block-children']/div[2]/div[@class='block-children']/div[2]"));
						Thread.sleep(5000);
						((JavascriptExecutor) driver).executeScript(java_script, Op4, Con4);
						Thread.sleep(5000);
						((JavascriptExecutor) driver).executeScript(java_script, Op4, Con5);
						Thread.sleep(2000);
						// to send the Field for THIRD operator in first value

						WebElement Value31 = driver.findElement(By.xpath(
								"//div[@class='block-children']/div[2]/div[@class='block-children']/div[1]/div[1]"));
						((JavascriptExecutor) driver).executeScript(java_script, SETOP1, Value31);
						Thread.sleep(5000);
						driver.findElement(By.xpath("//label[contains(.,'Filter:')]/../input")).sendKeys("billingType");
						waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
						driver.findElement(By.xpath("//div[@class='span12 options']/ul/li[1]")).click();
						driver.findElement(By.xpath("html/body/div[2]/div[2]/div/div[1]/div/div")).click();
						Thread.sleep(5000);
						// to send the value for Third operator in Second value
						WebElement ToCon32 = driver.findElement(By.xpath(
								"//div[@class='block-children']/div[2]/div[@class='block-children']/div[1]/div[3]"));
						((JavascriptExecutor) driver).executeScript(java_script, ToVal1, ToCon32);
						driver.findElement(By.xpath(
								"//div[@class='block-children']/div[2]/div[@class='block-children']/div[1]/div[3]/div/input"))
								.sendKeys("Test");
						// to send the Field for Fourth operator in first value

						WebElement Value41 = driver.findElement(By.xpath(
								"//div[@class='block-children']/div[2]/div[@class='block-children']/div[2]/div[1]"));
						((JavascriptExecutor) driver).executeScript(java_script, SETOP1, Value41);
						Thread.sleep(5000);
						waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
						driver.findElement(By.xpath("//label[contains(.,'Filter:')]/../input")).sendKeys("usage");
						waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
						driver.findElement(By.xpath("//div[@class='span12 options']/ul/li[1]")).click();
						driver.findElement(By.xpath("html/body/div[2]/div[2]/div/div[1]/div/div")).click();
						Thread.sleep(5000);
						// to send the value for Fourth operator in Second value
						driver.findElement(By.xpath(
								"//div[@class='block-children']/div[2]/div[@class='block-children']/div[2]/div[3]"))
								.click();
						WebElement ToCon42 = driver.findElement(By.xpath(
								"//div[@class='block-children']/div[2]/div[@class='block-children']/div[2]/div[3]"));
						((JavascriptExecutor) driver).executeScript(java_script, ToVal1, ToCon42);
						Thread.sleep(2000);
						driver.findElement(By.xpath(
								"//div[@class='block-children']/div[2]/div[@class='block-children']/div[2]/div[3]/div/input"))
								.sendKeys("Test");

					}
					if (Opval2.equalsIgnoreCase("<") || Opval2.equalsIgnoreCase(">") || Opval2.equalsIgnoreCase("<>")
							|| Opval2.equalsIgnoreCase("=") || Opval2.equalsIgnoreCase("<=")
							|| Opval2.equalsIgnoreCase(">=")) {
						WebElement Con2 = driver.findElement(
								By.xpath("//div[@class='block-children']/div[1]/div[@class='block-children']/div[1]"));
						WebElement Con3 = driver.findElement(
								By.xpath("//div[@class='block-children']/div[1]/div[@class='block-children']/div[2]"));
						Thread.sleep(5000);
						((JavascriptExecutor) driver).executeScript(java_script, Op4, Con2);
						Thread.sleep(5000);
						((JavascriptExecutor) driver).executeScript(java_script, Op4, Con3);
						Thread.sleep(5000);
						WebElement Con4 = driver.findElement(
								By.xpath("//div[@class='block-children']/div[2]/div[@class='block-children']/div[1]"));
						WebElement Con5 = driver.findElement(
								By.xpath("//div[@class='block-children']/div[2]/div[@class='block-children']/div[2]"));
						Thread.sleep(5000);
						((JavascriptExecutor) driver).executeScript(java_script, Op4, Con4);
						Thread.sleep(5000);
						((JavascriptExecutor) driver).executeScript(java_script, Op4, Con5);
						Thread.sleep(2000);
					}
				}
			}
		}
		JavaScriptclick(driver, PUL.ConclusionLink);
		Thread.sleep(2000);
		assertTrue(PUL.ScratchPad.isDisplayed());
		assertTrue(PUL.ANDOp.isDisplayed());
		assertTrue(PUL.OrOp.isDisplayed());
		assertTrue(PUL.isNullOp.isDisplayed());
		assertTrue(PUL.isnotNullOP.isDisplayed());
		assertTrue(PUL.equaltoOP.isDisplayed());
		assertTrue(PUL.NOtEqualOp.isDisplayed());
		assertTrue(PUL.LessthanOP.isDisplayed());
		assertTrue(PUL.GreaterOp.isDisplayed());
		assertTrue(PUL.LessthanEqOp.isDisplayed());
		assertTrue(PUL.GreaterthanEqOp.isDisplayed());
		assertTrue(PUL.PlusOp.isDisplayed());
		assertTrue(PUL.SUbOP.isDisplayed());
		assertTrue(PUL.MUlOp.isDisplayed());
		assertTrue(PUL.SlashOp.isDisplayed());
		assertTrue(PUL.TaxabilityFields.isDisplayed());
		assertTrue(PUL.NumbersFeild.isDisplayed());
		assertTrue(PUL.BooleanField.isDisplayed());
		assertTrue(PUL.DateField.isDisplayed());
		assertTrue(PUL.StringButton.isDisplayed());
		assertTrue(PUL.Numericbutton.isDisplayed());
		assertTrue(PUL.DateButton.isDisplayed());
		click(PUL.Advancedlink);
		Thread.sleep(2000);
		assertTrue(PUL.Addresses.isDisplayed());
		assertTrue(PUL.Overrides.isDisplayed());
		assertTrue(PUL.OutputNotice.isDisplayed());
		assertTrue(PUL.Arrybutton.isDisplayed());
		assertTrue(PUL.IncludedImp.isDisplayed());
		assertTrue(PUL.RemovedImp.isDisplayed());
		assertTrue(PUL.ReturnField.isDisplayed());
		assertTrue(PUL.SUBStrFunc.isDisplayed());
		assertTrue(PUL.JOinFucn.isDisplayed());
		assertTrue(PUL.DaysBWFunc.isDisplayed());
		assertTrue(PUL.ROUNDFunc.isDisplayed());
		assertTrue(PUL.OverrideFunc.isDisplayed());
		assertTrue(PUL.TaxRegFunc.isDisplayed());
		assertTrue(PUL.RemndrFunc.isDisplayed());
		assertTrue(PUL.ContainsFunc.isDisplayed());
		assertTrue(PUL.UpperFunc.isDisplayed());
		assertTrue(PUL.MonthFunc.isDisplayed());
		assertTrue(PUL.DayFunc.isDisplayed());
		assertTrue(PUL.YearFucnt.isDisplayed());
		assertTrue(PUL.INclImpFunct.isDisplayed());
		assertTrue(PUL.RemImpFucn.isDisplayed());
		assertTrue(PUL.Addreturnsfunc.isDisplayed());
		assertTrue(PUL.isMappedFunc.isDisplayed());
		assertTrue(PUL.LOWERFunc.isDisplayed());
		assertTrue(PUL.LookupString.isDisplayed());
		assertTrue(PUL.LookupNumeric.isDisplayed());
		assertTrue(PUL.LookupArray.isDisplayed());
		assertTrue(PUL.LookupAllocation.isDisplayed());
		click(PUL.BASIClink);
		Thread.sleep(3000);
		if (ConclusionSetVal != "") {
			WebElement Con2Val = driver.findElement(By.xpath("//div/span[contains(.,'" + ConclusionSetType + "')]"));
			WebElement Con2valCel = driver
					.findElement(By.xpath("//div[@class='conclusion']/div[contains(.,'SET')]/../div[2]/div"));
			((JavascriptExecutor) driver).executeScript(java_script, Con2Val, Con2valCel);
			Thread.sleep(3000);
			waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
			driver.findElement(By.xpath("//label[contains(.,'Filter:')]/../input")).sendKeys(ConclusionSetVal);
			waitforText(180, "//div[@class='span12 options']/ul/li[1]", driver);
			WebElement Vl1 = driver.findElement(
					By.xpath("//div[@class='span12 options']/ul/li[@data-value='" + ConclusionSetVal + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Vl1);

			// driver.findElement(By.xpath("//div[@class='span12
			// options']/ul/li[contains(.,'"+Rulessheet.getCell(Val1,
			// j).getContents()+"')]")).click();
		}
		if (ConclusionToVal != "") {
			String totype = ConclusionToType;
			WebElement Con2Val;
			switch (totype) {
			case "Date":
				Con2Val = driver.findElement(By.xpath("//h2[contains(text(),'Values')]/../div/div[4]/img"));
				break;
			case "number":
				Con2Val = driver.findElement(By.xpath("//h2[contains(text(),'Values')]/../div/div[2]/img"));
				break;
			case "Boolean":
				Con2Val = driver.findElement(By.xpath("//h2[contains(text(),'Values')]/../div/div[3]/img"));
				break;
			default:
				Con2Val = driver.findElement(By.xpath("//h2[contains(text(),'Values')]/../div/div[1]/img"));
				break;
			}

			WebElement Con2valCel = driver
					.findElement(By.xpath("//div[@class='conclusion']/div[contains(.,'TO')]/../div[4]/div"));
			((JavascriptExecutor) driver).executeScript(java_script, Con2Val, Con2valCel);
			Thread.sleep(3000);
			// driver.findElement(By.xpath("//div[@class='conclusion']/div[contains(.,'TO')]/../div[4]/div/div/div/div/div/div/div")).sendKeys(Rulessheet.getCell(ConclusionToVal,
			// j).getContents());
		}
		if (Add_Allocation_Conclusion != "") {
			click(PUL.Addalloctionconclusion);
			String Text = driver
					.findElement(By.xpath("//div[2][@class='conclusion']/div[contains(.,'SET')]/../div[2]/div"))
					.getText();
			assertTrue(Text.equalsIgnoreCase("ALLOCATED_CHILD_LINE_ITEMS"));
			JavaScriptclick(driver, PUL.Advancedlink);
			Thread.sleep(2000);
			WebElement Allocationtable = driver
					.findElement(By.xpath("//div/h2[contains(text(),'Lookups')]/../div[3]/div[4]"));
			WebElement Toallocatincond = driver
					.findElement(By.xpath("//div[2][@class='conclusion']/div[contains(.,'TO')]/../div[4]/div"));
			((JavascriptExecutor) driver).executeScript(java_script, Allocationtable, Toallocatincond);
			Thread.sleep(3000);
		}
		driver.findElement(By.xpath("//button[contains(.,'Save Rule')]")).click();
		waitforElement(180, PUL.AddRule, driver);

	}

	public void AddLookuptables(String Lookup_tablename, String Businessevent, String Lookupdescription,
			String LookupResultname, String Datatype, String LookupParameter1, String LookupParameter2,
			String LookupParameter3, String LookupParameter4, String LookupParameter5, String Addrecor,
			String Resultname_value, String Parameter1_value, String Parameter2_value, String Parameter3_value,
			String Parameter4_value, String Parameter5_value, String UploadRecord, String lookupFileName,
			String lookuptable_Edit) throws Exception {
		String java_script = "", Tablename = "";
		File directory = new File(".//");
		String AutoITpath = xAutoIT();
		String TaxassistFilepath = xUploadAdjustmentFile("TaxAssistRuleReportFiles");
		TaxassistantRule PUL = PageFactory.initElements(driver, TaxassistantRule.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String jquerySimulator = new String(Files.readAllBytes(
				Paths.get(directory.getCanonicalPath() + "\\" + "resources\\config" + "\\" + "JQUeryEvent.js")));
		java_script = jquerySimulator;
		java_script = java_script + "simulateHTML5DragAndDrop(arguments[0], arguments[1])";
		waitforElement(180, PUL.CONFIGURELINK, driver);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.TaxassistantHeader, driver);
		waitforElement(180, PUL.ViewLink, driver);
		click(PUL.ViewLink);
		waitforElement(180, PUL.AddRule, driver);
		// Add Lookup Table
		String bussinessevn = Businessevent;
		switch (bussinessevn) {
		case "Procurement":
			waitforElement(180, PUL.BusinessEventDropdown, driver);
			selectByValue(PUL.BusinessEventDropdown, bussinessevn);
			break;
		case "Supplies":
			waitforElement(180, PUL.BusinessEventDropdown, driver);
			selectByValue(PUL.BusinessEventDropdown, bussinessevn);
			break;
		}
		waitforElement(180, PUL.Lookuptables, driver);
		JavaScriptclick(driver, PUL.Lookuptables);
		waitforElement(180, PUL.AddLookuptable, driver);
		if (!VerifyObject(driver, PUL.AddLookuptable)) {
			JavaScriptclick(driver, PUL.CONFIGURELINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.Lookuptables, driver);
			JavaScriptclick(driver, PUL.Lookuptables);
			waitforElement(180, PUL.AddLookuptable, driver);
		}
		JavaScriptclick(driver, PUL.AddLookuptable);
		waitforElement(180, PUL.LookupNameinput, driver);
		Thread.sleep(2000);
		if (Lookup_tablename.contains(";")) {
			String[] DataValue = Lookup_tablename.split(";");
			Tablename = xLabelRND(DataValue);
		} else {
			Tablename = Lookup_tablename;
		}
		type(PUL.LookupNameinput, Tablename);
		type(PUL.LookupDescription, Lookupdescription);
		type(PUL.LookupResultname, LookupResultname);
		String Datatyp = Datatype;
		selectByValue(PUL.DATATYPE, Datatyp);
		Thread.sleep(2000);
		if ((LookupParameter1) != "") {
			type(PUL.Parameter1, LookupParameter1);
		}
		if ((LookupParameter2) != "") {
			type(PUL.Parameter2, LookupParameter2);
		}
		if ((LookupParameter3) != "") {
			type(PUL.Parameter3, LookupParameter3);
		}
		if ((LookupParameter4) != "") {
			type(PUL.Parameter4, LookupParameter4);
		}
		if ((LookupParameter5) != "") {
			type(PUL.Parameter5, LookupParameter5);
		}
		// Add lookup Records
		if (Addrecor.equalsIgnoreCase("YES")) {
			assertTrue(xGetDate("MM/dd/yyyy").equalsIgnoreCase(getAttribute(PUL.AsofDate, "value")));
			waitforElement(180, PUL.Records, driver);
			JavaScriptclick(driver, PUL.Records);
			waitforElement(180, PUL.AddRecord, driver);
			JavaScriptclick(driver, PUL.AddRecord);
			String Resultname = driver.findElement(By.xpath("//table/thead/tr/th[1]/span/span")).getText();
			assertTrue(Resultname.equalsIgnoreCase(LookupResultname));
			type(PUL.Resultname, Resultname_value);
			if ((LookupParameter1) != "") {
				click(PUL.Parameter1value);
				type(PUL.Parameter1value, Parameter1_value);
			}
			if ((LookupParameter2) != "") {
				click(PUL.Parameter2value);
				type(PUL.Parameter2value, Parameter2_value);
			}
			if ((LookupParameter3) != "") {
				click(PUL.Parameter3value);
				type(PUL.Parameter3value, Parameter3_value);
			}
			if ((LookupParameter4) != "") {
				click(PUL.Parameter4value);
				type(PUL.Parameter4value, Parameter4_value);
			}
			if ((LookupParameter5) != "") {
				click(PUL.Parameter5value);
				type(PUL.Parameter5value, Parameter5_value);
			}
		}
		// Uploading lookup Records
		if ((UploadRecord).equalsIgnoreCase("YES")) {
			assertTrue(xGetDate("MM/dd/yyyy").equalsIgnoreCase(getAttribute(PUL.AsofDate, "value")));
			assertTrue(xGetDate("MM/dd/yyyy").equalsIgnoreCase(getAttribute(PUL.PropertiesStartDate, "value")));
			waitforElement(180, PUL.Records, driver);
			JavaScriptclick(driver, PUL.Records);
			waitforElement(180, PUL.AddRecord, driver);
			JavaScriptclick(driver, PUL.LookupImport);
			fileUploadDialog(driver, Browser, AutoITpath, TaxassistFilepath, lookupFileName, ".csv");
			Thread.sleep(5000);
		}
		JavaScriptclick(driver, PUL.Savelookuptable);
		waitforElement(180, PUL.AsofDate, driver);
		assertTrue(xGetDate("MM/dd/yyyy").equalsIgnoreCase(getAttribute(PUL.AsofDate, "value")));
		// assertTrue(xGetDate("MM/dd/yyyy").equalsIgnoreCase(getAttribute(PUL.TableStartDate,
		// "value")));
		Thread.sleep(5000);
		waitforElement(180, PUL.AddLookuptable, driver);
		// Edit lookup Table and Records
		if ((lookuptable_Edit).equalsIgnoreCase("YES")) {
			driver.findElement(
					By.xpath("//td[contains(.,'" + Tablename + "')]/..//a/span[@data-original-title='Edit']")).click();
			waitforElement(180, PUL.Records, driver);
			JavaScriptclick(driver, PUL.Records);
			waitforElement(180, PUL.AddRecord, driver);
			JavaScriptclick(driver, PUL.AddRecord);
			type(PUL.Resultname, Resultname_value);
			if ((LookupParameter1) != "") {
				click(PUL.Parameter1value);
				type(PUL.Parameter1value, Parameter1_value);
			}
			if ((LookupParameter2) != "") {
				click(PUL.Parameter2value);
				type(PUL.Parameter2value, Parameter2_value);
			}
			JavaScriptclick(driver, PUL.Savelookuptable);
			Thread.sleep(5000);
			waitforElement(180, PUL.AddLookuptable, driver);
		}

	}

	public void AddAllocationTables(String BusinessEvent, String AllocationTable_Name, String Allocationdescription,
			String Column1, String Column2, String Column3, String Column4, String Addrecord, String Code,
			String Column1_value, String Column2_value, String Column3_value, String Column4_value,
			String Allocationtable_edit, String Column2_value1) throws Exception {
		String java_script = "", Tablename = "";
		File directory = new File(".//");
		TaxassistantRule PUL = PageFactory.initElements(driver, TaxassistantRule.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String jquerySimulator = new String(Files.readAllBytes(
				Paths.get(directory.getCanonicalPath() + "\\" + "resources\\config" + "\\" + "JQUeryEvent.js")));
		java_script = jquerySimulator;
		java_script = java_script + "simulateHTML5DragAndDrop(arguments[0], arguments[1])";
		waitforElement(180, PUL.CONFIGURELINK, driver);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.TaxassistantHeader, driver);
		waitforElement(180, PUL.ViewLink, driver);
		click(PUL.ViewLink);
		waitforElement(180, PUL.AddRule, driver);
		// Add Allocation Table
		String bussinessevn = BusinessEvent;
		switch (bussinessevn) {
		case "Procurement":
			waitforElement(180, PUL.BusinessEventDropdown, driver);
			selectByValue(PUL.BusinessEventDropdown, bussinessevn);
			break;
		case "Supplies":
			waitforElement(180, PUL.BusinessEventDropdown, driver);
			selectByValue(PUL.BusinessEventDropdown, bussinessevn);
			break;
		}
		waitforElement(180, PUL.ALLOCATIONTABLES, driver);
		JavaScriptclick(driver, PUL.ALLOCATIONTABLES);
		waitforElement(180, PUL.ADDALLOCATIONTABLE, driver);
		Thread.sleep(3000);
		JavaScriptclick(driver, PUL.ADDALLOCATIONTABLE);
		waitforElement(180, PUL.AllocationTablename, driver);
		String[] DataValue = AllocationTable_Name.split(";");
		Tablename = xLabelRND(DataValue);
		type(PUL.AllocationTablename, Tablename);
		type(PUL.Allocationdescription, Allocationdescription);
		if ((Column1) != "") {
			selectByValue(PUL.COLUMN1, Column1);
			Thread.sleep(3000);
		}
		if ((Column2) != "") {
			click(PUL.INSERTBELOW1);
			selectByValue(PUL.COLUMN2, Column2);
			Thread.sleep(3000);
		}
		if ((Column3) != "") {
			click(PUL.INSERTBELOW2);
			selectByValue(PUL.COLUMN3, Column3);
			Thread.sleep(3000);

		}
		if ((Column4) != "") {
			click(PUL.INSERTBELOW3);
			selectByValue(PUL.COLUMN4, Column4);
			Thread.sleep(3000);
		}
		// Add Allocation Records
		if ((Addrecord).equalsIgnoreCase("YES")) {
			waitforElement(180, PUL.Records, driver);
			JavaScriptclick(driver, PUL.Records);
			waitforElement(180, PUL.AddRecord, driver);
			click(PUL.AddRecord);
			if ((Column1) != "") {
				String Cloum = driver.findElement(By.xpath("//table/thead/tr/th[2]/span/span")).getText();
				assertTrue(Cloum.equalsIgnoreCase(Column1));
			}
			if ((Column2) != "") {
				String Cloum = driver.findElement(By.xpath("//table/thead/tr/th[3]/span/span")).getText();
				assertTrue(Cloum.equalsIgnoreCase(Column2));
			}
			if ((Column3) != "") {
				String Cloum = driver.findElement(By.xpath("//table/thead/tr/th[4]/span/span")).getText();
				assertTrue(Cloum.equalsIgnoreCase(Column3));
			}
			if ((Column4) != "") {
				String Cloum = driver.findElement(By.xpath("//table/thead/tr/th[5]/span/span")).getText();
				assertTrue(Cloum.equalsIgnoreCase(Column4));
			}
			type(PUL.Code, Code);
			if ((Column1) != "") {
				type(PUL.CITY, Column1_value);
			}
			if ((Column2) != "") {
				selectByValue(PUL.Conversationrate, Column2_value);
			}
			if ((Column3) != "") {
				type(PUL.Country, Column3_value);
			}
			if ((Column4) != "") {
				type(PUL.Jurisdictioncode, Column4_value);
			}
		}
		JavaScriptclick(driver, PUL.Saveallocationtable);
		Thread.sleep(3000);
		waitforElement(180, PUL.ADDALLOCATIONTABLE, driver);
		// Click on edit and Add Records
		if ((Allocationtable_edit).equalsIgnoreCase("YES")) {
			driver.findElement(
					By.xpath("//td[contains(.,'" + Tablename + "')]/..//a/span[@data-original-title='Edit']")).click();
			waitforElement(180, PUL.Records, driver);
			JavaScriptclick(driver, PUL.Records);
			waitforElement(180, PUL.AddRecord, driver);
			click(PUL.AddRecord);
			type(PUL.Code, Code);
			if ((Column1) != "") {
				type(PUL.CITY, Column1_value);
			}
			if ((Column2) != "") {
				clear(PUL.COLUMN2_Value);
				type(PUL.COLUMN2_Value, Column2_value1);
				selectByValue(PUL.Conversationrate, Column2_value);
			}
			if ((Column3) != "") {
				type(PUL.Country, Column3_value);
			}
			if ((Column4) != "") {
				type(PUL.Jurisdictioncode, Column4_value);
			}
			JavaScriptclick(driver, PUL.Saveallocationtable);
			waitforElement(180, PUL.ADDALLOCATIONTABLE, driver);
		}
	}

	public void EditTaxassistRule(String RuleName, String Add_lookup, String Add_Allocation_Conclusion)
			throws Exception {
		String java_script = "";
		File directory = new File(".//");
		TaxassistantRule PUL = PageFactory.initElements(driver, TaxassistantRule.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String jquerySimulator = new String(Files.readAllBytes(
				Paths.get(directory.getCanonicalPath() + "\\" + "resources\\config" + "\\" + "JQUeryEvent.js")));
		java_script = jquerySimulator;
		java_script = java_script + "simulateHTML5DragAndDrop(arguments[0], arguments[1])";
		waitforElement(180, PUL.CONFIGURELINK, driver);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.TaxassistantHeader, driver);
		waitforElement(180, PUL.ViewLink, driver);
		click(PUL.ViewLink);
		waitforElement(180, PUL.AddRule, driver);
		if (VerifyObject(driver, "//table/tbody/tr/td[contains(.,'" + RuleName + "')]")) {
			driver.findElement(By.xpath(
					"//tbody/tr/td[contains(.,'" + RuleName + "')]/../td/div/a/span[@data-original-title='Edit']"))
					.click();
			waitforElement(180, PUL.Mainlink, driver);
			JavaScriptclick(driver, PUL.RULEENDDATE);
			type(PUL.RULEENDDATE, Currentdate);
			JavaScriptclick(driver, PUL.SAVERULE);
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//tbody/tr/td[contains(.,'" + RuleName + "')]/../td/div/a/span[@data-original-title='Move Up']"))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//tbody/tr/td[contains(.,'" + RuleName + "')]/../td/div/a/span[@data-original-title='Move Down']"))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"//tbody/tr/td[contains(.,'" + RuleName + "')]/../td/div/a/span[@data-original-title='Edit']"))
					.click();
			Thread.sleep(2000);
			JavaScriptclick(driver, PUL.ConclusionLink);
			Thread.sleep(2000);
			JavaScriptclick(driver, PUL.Advancedlink);
			Thread.sleep(2000);
			if (Add_lookup.equalsIgnoreCase("YES")) {
				WebElement lookuptable = driver.findElement(By.xpath(
						"//div/h2[contains(text(),'Lookups')]/../div[3]/div/span[contains(.,'LOOKUP() string')]"));
				WebElement Tolookupcond = driver
						.findElement(By.xpath("//div[1][@class='conclusion']/div[contains(.,'TO')]/../div[4]/div"));
				((JavascriptExecutor) driver).executeScript(java_script, lookuptable, Tolookupcond);
				Thread.sleep(5000);
				WebElement lookupvalue = driver
						.findElement(By.xpath("//div/img[@src='/Content/vendor/oseries/array_button_icon2.gif']"));
				WebElement lookupcond = driver.findElement(By.xpath("//div[2]/span/span[@class='placeholder']"));
				((JavascriptExecutor) driver).executeScript(java_script, lookupvalue, lookupcond);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[4]/div/div/div/div/div/div/div[2]/div[1]/input")).sendKeys("Value1");
				Thread.sleep(2000);
				WebElement lookupvalue1 = driver
						.findElement(By.xpath("//div/img[@src='/Content/vendor/oseries/array_button_icon2.gif']"));
				WebElement lookupcond1 = driver.findElement(By.xpath("//div[3]/span/span[@class='placeholder']"));
				((JavascriptExecutor) driver).executeScript(java_script, lookupvalue1, lookupcond1);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[4]/div/div/div/div/div/div/div[3]/div[1]/input")).sendKeys("Value2");
				Thread.sleep(2000);
				WebElement lookupvalue2 = driver
						.findElement(By.xpath("//div/img[@src='/Content/vendor/oseries/array_button_icon2.gif']"));
				WebElement lookupcond2 = driver.findElement(By.xpath("//div[4]/span/span[@class='placeholder']"));
				((JavascriptExecutor) driver).executeScript(java_script, lookupvalue2, lookupcond2);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[4]/div/div/div/div/div/div/div[4]/div[1]/input")).sendKeys("Value3");
				Thread.sleep(2000);
				WebElement lookupvalue3 = driver
						.findElement(By.xpath("//div/img[@src='/Content/vendor/oseries/array_button_icon2.gif']"));
				WebElement lookupcond3 = driver.findElement(By.xpath("//div[5]/span/span[@class='placeholder']"));
				((JavascriptExecutor) driver).executeScript(java_script, lookupvalue3, lookupcond3);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[4]/div/div/div/div/div/div/div[5]/div[1]/input")).sendKeys("Value4");
				Thread.sleep(2000);
			}

			if (Add_Allocation_Conclusion.equalsIgnoreCase("YES")) {
				click(PUL.Addalloctionconclusion);
				String Text = driver
						.findElement(By.xpath("//div[2][@class='conclusion']/div[contains(.,'SET')]/../div[2]/div"))
						.getText();
				assertTrue(Text.equalsIgnoreCase("ALLOCATED_CHILD_LINE_ITEMS"));
				JavaScriptclick(driver, PUL.Advancedlink);
				Thread.sleep(2000);
				WebElement Allocationtable = driver.findElement(By
						.xpath("//div//h2[contains(.,'Lookups')]/../div/div/span[contains(.,'LOOKUP() allocation')]"));
				WebElement Toallocatincond = driver
						.findElement(By.xpath("//div[2][@class='conclusion']/div[contains(.,'TO')]/../div[4]/div"));
				((JavascriptExecutor) driver).executeScript(java_script, Allocationtable, Toallocatincond);
				Thread.sleep(5000);
			}
		}
		driver.findElement(By.xpath("//button[contains(.,'Save Rule')]")).click();
		waitforElement(180, PUL.AddRule, driver);

	}

	public void AddLookuptaxvalue(String Businessevent, String Lookup_tablename, String Lookupdescription,
			String LookupResultname, String Datatype, String LookupParameter1, String LookupParameter2,
			String LookupParameter3, String LookupParameter4, String LookupParameter5, String UploadRecord,
			String FileName) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		new Robot();
		String java_script = "";
		File directory = new File(".//");
		TaxassistantRule PUL = PageFactory.initElements(driver, TaxassistantRule.class);
		PageFactory.initElements(driver, TaxAssistRuleReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		xDownloadFiles("TaxAssistReport");
		String browserName = Browser;
		String AutoITpath = xAutoIT();
		String TaxassistFilepath = xUploadAdjustmentFile("TaxAssistRuleReportFiles");
		Thread.sleep(5000);
		String jquerySimulator = new String(Files.readAllBytes(
				Paths.get(directory.getCanonicalPath() + "\\" + "resources\\config" + "\\" + "JQUeryEvent.js")));
		java_script = jquerySimulator;
		java_script = java_script + "simulateHTML5DragAndDrop(arguments[0], arguments[1])";
		waitforElement(180, PUL.CONFIGURELINK, driver);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.TaxassistantHeader, driver);
		waitforElement(180, PUL.ViewLink, driver);
		click(PUL.ViewLink);
		waitforElement(180, PUL.AddRule, driver);
		String bussinessevn = Businessevent;
		switch (bussinessevn) {
		case "Procurement":
			waitforElement(180, PUL.BusinessEventDropdown, driver);
			selectByValue(PUL.BusinessEventDropdown, bussinessevn);
			break;
		case "Supplies":
			waitforElement(180, PUL.BusinessEventDropdown, driver);
			selectByValue(PUL.BusinessEventDropdown, bussinessevn);
			break;
		}
		waitforElement(180, PUL.Lookuptables, driver);
		JavaScriptclick(driver, PUL.Lookuptables);
		waitforElement(180, PUL.AddLookuptable, driver);
		if (!VerifyObject(driver, PUL.AddLookuptable)) {
			JavaScriptclick(driver, PUL.CONFIGURELINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.Lookuptables, driver);
			JavaScriptclick(driver, PUL.Lookuptables);
			waitforElement(180, PUL.AddLookuptable, driver);
		}
		assertTrue(xGetDate("MM/dd/yyyy").equalsIgnoreCase(getAttribute(PUL.AsofDate, "value")));
		JavaScriptclick(driver, PUL.AddLookuptable);
		waitforElement(180, PUL.LookupNameinput, driver);
		Thread.sleep(2000);
		type(PUL.LookupNameinput, Lookup_tablename);
		type(PUL.LookupDescription, Lookupdescription);
		type(PUL.LookupResultname, LookupResultname);
		selectByValue(PUL.DATATYPE, Datatype);
		Thread.sleep(2000);
		if (LookupParameter1 != "") {
			type(PUL.Parameter1, LookupParameter1);
		}
		if (LookupParameter2 != "") {
			type(PUL.Parameter2, LookupParameter2);
		}
		if (LookupParameter3 != "") {
			type(PUL.Parameter3, LookupParameter3);
		}
		if (LookupParameter4 != "") {
			type(PUL.Parameter4, LookupParameter4);
		}
		if (LookupParameter5 != "") {
			type(PUL.Parameter5, LookupParameter5);
		}
		// Uploading lookup Records
		if (UploadRecord != "") {
			waitforElement(180, PUL.Records, driver);
			JavaScriptclick(driver, PUL.Records);
			waitforElement(180, PUL.AddRecord, driver);
			JavaScriptclick(driver, PUL.LookupImport);
			fileUploadDialog(driver, browserName, AutoITpath, TaxassistFilepath, FileName, ".csv");
			Thread.sleep(5000);
		}
		assertTrue(xGetDate("MM/dd/yyyy").equalsIgnoreCase(getAttribute(PUL.AsofDate, "value")));
		assertTrue(xGetDate("MM/dd/yyyy").equalsIgnoreCase(getText(PUL.startdate)));
		JavaScriptclick(driver, PUL.Savelookuptable);
		Thread.sleep(5000);
		waitforElement(180, PUL.AddLookuptable, driver);
	}

	public void GoLive(String TaxPayer, String downloadLink) throws Exception {

		GoLiveVerify GL = PageFactory.initElements(driver, GoLiveVerify.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		// int count = 0;
		String MsgCount;
		waitforElement(120, MTR.MANAGETAXRETURNSLINK, driver);
		JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, MTR.TAXPAYER, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(120, "html[@class='nprogress-busy']", driver);
		waitforText(60, CNP.BEGINREMOVAL, driver);
		/*
		 * WebElement TaxPayerGoLive = driver.findElement(By.xpath( "//h2[contains(.,'"
		 * + TaxPayer +
		 * "')]/../../../../../div/div/div/button[contains(.,'Go Live')]"));
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 * TaxPayerGoLive);
		 */
		if (!VerifyObject(driver, "//button[contains(.,' Go Live')]")) {

			driver.navigate().refresh();
			Thread.sleep(5000);
		}
		driver.findElement(By.xpath("//button[contains(.,' Go Live')]")).click();
		waitforElement(180, GL.SMOKE_YESGOLIVEBUTTON, driver);
		click(GL.SMOKE_YESGOLIVEBUTTON);
		Thread.sleep(5000);
		if (VerifyObject(driver, GL.GOLIVEERRORPROCESS)) {
			JavaScriptclick(driver, GL.GOLIVEOK);
			Thread.sleep(3000);
		}
		waitforElement(60, GL.CHECKSTATUS, driver);
		if (VerifyObject(driver, GL.CHECKSTATUS)) {
			click(GL.CHECKSTATUS);
			Thread.sleep(3000);
			waitforElement(180, GL.OKICON_INPROGRESS, driver);
		}
		if (!VerifyObject(driver, GL.OKICON_INPROGRESS)) {
			boolean okIcon = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(8000);
				waitforElement(180, GL.DASHBOARD, driver);
				if (VerifyObject(driver, GL.CHECKSTATUS)) {
					JavaScriptclick(driver, GL.CHECKSTATUS);
					waitforElement(180, GL.OKICON_INPROGRESS, driver);
				}
				if (VerifyObject(driver, GL.OKICON_INPROGRESS)) {
					okIcon = true;
				}
			} while (okIcon == false);
		}
		JavaScriptclick(driver, GL.CLOSE);
		Thread.sleep(3000);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		click(PUL.DASHBOARDLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, CNP.BEGINREMOVAL, driver);
		waitforElement(180, "//h2[contains(.,'" + TaxPayer
				+ "')]/../../../../../div/div[1]/div/table/tbody/tr/td/h2[contains(.,'Live')]", driver);
		assertTrue(VerifyObject(driver, "//h2[contains(.,'" + TaxPayer
				+ "')]/../../../../../div/div[1]/div/table/tbody/tr/td/h2[contains(.,'Live')]"));
		// My RateFile should display
		waitforElement(180, PUL.RateFileHover, driver);
		JavaScriptMousehover(driver, PUL.RateFileHover);
		JavaScriptclick(driver, PUL.MYRATEFILE_LINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, "//td[contains(.,'Success File')]", driver);
		assertTrue(VerifyObject(driver, "//td[contains(.,'Success File')]"));
		// TaxabilityReport Should display
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TAXABILITYREPORTSLINK, driver);
		JavaScriptclick(driver, PUL.TAXABILITYREPORTSLINK);
		waitforElement(180, PUL.TAXABILITYCOMPDD, driver);
		selectByValue(PUL.TAXABILITYCOMPDD, downloadLink);
		waitforElement(180, "//tbody/tr/td/a[contains(.,'Report')]", driver);
		if (VerifyObject(driver, PUL.TAXABILITYREPORTPLUS)) {
			JavaScriptclick(driver, PUL.TAXABILITYREPORTPLUS);
			waitforElement(180, PUL.TAXABILITYREPORTMINUS, driver);
		}
		assertTrue(VerifyObject(driver, GL.TAXABILITYLINK));
		MsgCount = getText(GL.MESSAGEICON);
		if (Integer.parseInt(MsgCount) == 0) {
		}
		// TransactionReport Should not display
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitforElement(180, GL.NOTRANSACTIONMSG, driver);
		// assertTrue(GL.NOTRANSACTIONMSG.isDisplayed()==false);
		assertTrue(GL.NOTRANSACTIONMSG.isDisplayed());
		// Returns Should not display
		click(PUL.MANAGETAXRETURNSLINK);
		waitforElement(180, MTR.TAXPAYER, driver);
		// assertTrue(GL.DownloadAllReturns.isDisplayed()==false);
		assertEquals(false, VerifyObject(driver, GL.DownloadAllReturns));

		// Adjustment Files Should display as per 6.1
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
		waitforElement(180, GL.UPLADJROW, driver);
		assertTrue(GL.UPLADJROW.isDisplayed());
	}

	public void GoLive(String TaxPayer) throws Exception {

		GoLiveVerify GL = PageFactory.initElements(driver, GoLiveVerify.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.CONFIGURE, driver);
		if (VerifyObject(driver, MTR.MANAGETAXRETURNSLINK)) {
			waitforElement(180, MTR.MANAGETAXRETURNSLINK, driver);
			JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, MTR.TAXPAYER, driver);
		}
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, "//button[contains(.,' Go Live')]")) {

			driver.navigate().refresh();
			Thread.sleep(5000);
		}
		driver.findElement(By.xpath("//button[contains(.,' Go Live')]")).click();
		waitforElement(180, GL.SMOKE_YESGOLIVEBUTTON, driver);
		click(GL.SMOKE_YESGOLIVEBUTTON);
		Thread.sleep(5000);
		if (VerifyObject(driver, GL.GOLIVEERRORPROCESS)) {
			JavaScriptclick(driver, GL.GOLIVEOK);
			Thread.sleep(3000);
		}
		waitforElement(60, GL.CHECKSTATUS, driver);
		if (VerifyObject(driver, GL.CHECKSTATUS)) {
			click(GL.CHECKSTATUS);
			Thread.sleep(3000);
			waitforElement(180, GL.OKICON_INPROGRESS, driver);
		}
		if (!VerifyObject(driver, GL.OKICON_INPROGRESS)) {
			boolean okIcon = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(8000);
				waitforElement(180, GL.DASHBOARD, driver);
				if (VerifyObject(driver, GL.CHECKSTATUS)) {
					JavaScriptclick(driver, GL.CHECKSTATUS);
					waitforElement(180, GL.OKICON_INPROGRESS, driver);
				}
				if (VerifyObject(driver, GL.OKICON_INPROGRESS)) {
					okIcon = true;
				}
			} while (okIcon == false);
		}

		JavaScriptclick(driver, GL.CLOSE);
		Thread.sleep(3000);
		waitforElement(100, PUL.DASHBOARDLINK, driver);
		click(PUL.DASHBOARDLINK);
		Thread.sleep(5000);
		waitforElement(100, CNP.CONFIGURE, driver);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(100, "html[@class='nprogress-busy']", driver);
		waitforText(100, CNP.BEGINREMOVAL, driver);
		assertTrue(driver.findElement(By.xpath("//h2[contains(.,'" + TaxPayer
				+ "')]/../../../../../div/div[1]/div/table/tbody/tr/td/h2[contains(.,'Live')]")).isDisplayed());
		goliveEndTime = xGetCurrentTime();
	}

	public void syncTime() throws Exception {

		String CurrentTime = xGetCurrentTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date1 = sdf.parse(CurrentTime);
		Date date2 = sdf.parse(goliveEndTime);
		long DifferenceTime = date1.getTime() - date2.getTime();
		long min = DifferenceTime / (60 * 1000) % 60;
		System.out.println(min * 60 * 1000);
		if (min < 10) {
			Thread.sleep(600000 - (min * 60 * 1000));
		}
	}

	public void AdminUndoGoLive(String TaxPayer_Code, String Client) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		type(AUA.smbCustomerName, Client);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Client + "')]", driver);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + Client
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement Registered = driver.findElement(
					By.xpath("//table/tbody/tr/td/div/a[contains(text(),'" + Client + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr/td/div/a[contains(text(),'" + Client
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement Authorized = driver.findElement(
					By.xpath("//table/tbody/tr/td/div/a[contains(text(),'" + Client + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else {
			WebElement Intilized = driver.findElement(
					By.xpath("//table/tbody/tr/td/div/a[contains(text(),'" + Client + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Intilized);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(60, AUA.ADMINDETAILSLNK, driver);
		JavaScriptclick(driver, AUA.ADMINDETAILSLNK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, AUA.EDITCUSTOMERSUBSCRIPTION, driver);
		WebElement UndoGolive = driver.findElement(By.xpath("//div/div/button[contains(.,'Undo GoLive')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", UndoGolive);
		// driver.findElement(By.xpath("//div/div/h2[contains(.,'"+CloudData.get_data(TaxPayer_Code,Data_Row).getContents()+"')]/../../div/div/button")).click()
		waitforElement(60, AUA.YES, driver);
		JavaScriptclick(driver, AUA.YES);
		if (VerifyObject(driver, AUA.ERRORPOPUPOK)) {
			JavaScriptclick(driver, AUA.ERRORPOPUPOK);
			WebElement UndoGoliveaftererror = driver
					.findElement(By.xpath("//div/div/button[contains(.,'Undo GoLive')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", UndoGoliveaftererror);
			waitforElement(60, AUA.YES, driver);
			JavaScriptclick(driver, AUA.YES);
			Thread.sleep(2000);
		}
		assertTrue(driver
				.findElement(By.xpath(
						"//div/div/h2[contains(.,'" + TaxPayer_Code + "')]/../../div/div/button[@disabled='disabled']"))
				.isDisplayed());
	}

	// Test Instance
	public void CopyTaxcentralCompanyData(String Source_Company, String TestInstance_CompanyCode) throws Exception {
		TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
		waitforElement(180, TI.Configurelnk, driver);
		JavaScriptclick(driver, TI.Configurelnk);
		waitUntilElementInvisibe(180, TI.NPROGRESSBUSY_XPATH, driver);
		waitforText(100, TI.ConfigureCompanyHeading, driver);
		Thread.sleep(6000);
		waitforElement(180, TI.sourcCompany, driver);
		selectByValue(TI.sourcCompany, Source_Company);
		clear(TI.newCompanycode);
		type(TI.newCompanycode, TestInstance_CompanyCode);
		Thread.sleep(5000);
		JavaScriptclick(driver, TI.copyDataBtn);
		Thread.sleep(1000);
		waitforElement(180, TI.changesprocessing, driver);
		if (!VerifyObject(driver, TI.viewChangeReport)) {
			boolean report = false;
			do {
				JavaScriptclick(driver, TI.Configurelnk);
				waitUntilElementInvisibe(180, TI.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(10000);
				if (VerifyObject(driver, TI.viewChangeReport)) {
					report = true;
				}
			} while (report == false);
		}
	}

	// Config Push
	public void MTPPConfigVerification(String CompanyName) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(60, CNP.BEGINREMOVAL, driver);
		Companynamesplit = CompanyName;
		if (Companynamesplit.contains("'")) {
			waitforElement(180,
					"//h2[contains(.,\"" + Companynamesplit + "\")]/../../div/a[contains(text(),'Save Configuration')]",
					driver);
			assertTrue(driver.findElement(By.xpath("//h2[contains(.,\"" + Companynamesplit
					+ "\")]/../../div/a[contains(text(),'Save Configuration')]")).isDisplayed());
		} else {
			waitforElement(180,
					"//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]",
					driver);
			assertTrue(driver.findElement(By.xpath(
					"//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]"))
					.isDisplayed());
		}
	}

	public void SaveConfig(String CompanyName) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		int count = 0;
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]")) {
			waitforElement(180,
					"//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]",
					driver);
			WebElement saveconfig = driver.findElement(By.xpath(
					"//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveconfig);
			waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
			if (VerifyObject(driver, CNP.COMPLETECONFIGPOPUP)) {
				JavaScriptclick(driver, CNP.COMPLETECONFIGPOPUP);
			}
		}
		waitforText(180, CNP.BEGINREMOVAL, driver);
		if (!VerifyObject(driver, MTR.GOLIVEBUTTON)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitforElement(180, MTR.TAXPAYER, driver);
				count = count + 1;
				if (count == 15) {
					break;
				}
				if (VerifyObject(driver, MTR.GOLIVEBUTTON)) {
					report = true;
				}
			} while (report == false);
		}
	}

	public void ConfigPush(String CompanyName) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(1000);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		// waitforText(180, CNP.BEGINREMOVAL, driver);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]")) {
			waitforElement(180,
					"//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]",
					driver);
			WebElement saveconfig = driver.findElement(By.xpath(
					"//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveconfig);
			waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
			if (VerifyObject(driver, CNP.COMPLETECONFIGPOPUP)) {
				JavaScriptclick(driver, CNP.COMPLETECONFIGPOPUP);
			}
		}
		// waitforText(180, CNP.BEGINREMOVAL, driver);
		waitforText(180, "//h2[contains(.,'" + CompanyName + "')]", driver);
		Thread.sleep(5000);
		// assertTrue(VerifyObject(driver,"//h2[contains(.,'" + CompanyName +
		// "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']"));
	}

	public void VerifyConfigPush(String TaxPayer) throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitforText(180, CNP.BEGINREMOVAL, driver);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (VerifyObject(driver, "//h2[contains(.,'" + TaxPayer
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning' and contains(.,'Registrations')]")
				&& VerifyObject(driver, "//h2[contains(.,'" + TaxPayer
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning' and contains(.,'Mappings')]")
				&& VerifyObject(driver, "//h2[contains(.,'" + TaxPayer
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning' and contains(.,'Customer Exemptions')]")) {
			boolean reportt = false;
			int count = 0;
			// Refreshing Page for 5 minutes
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitforText(180, CNP.BEGINREMOVAL, driver);
				Thread.sleep(10000);
				count++;
				if (count == 100) {
					assertTrue(reportt, "Configuration not turned to Green with in sync");
					break;
				}
				if (VerifyObject(driver, "//h2[contains(.,'" + TaxPayer
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-success' and contains(.,'Registrations')]")
						&& VerifyObject(driver, "//h2[contains(.,'" + TaxPayer
								+ "')]/../../../div/div/h3[@class='h5 heading-accent has-success' and contains(.,'Mappings')]")
						&& VerifyObject(driver, "//h2[contains(.,'" + TaxPayer
								+ "')]/../../../div/div/h3[@class='h5 heading-accent has-success' and contains(.,'Customer Exemptions')]")) {
					reportt = true;
				}
			} while (reportt == false);
		}
	}

	public static String fileUploadDialog(WebDriver driver, String browser, String AutoITpath,
			String AdjustmentFilePath, String Filename, String fileformat) throws Exception {
		String status = "";
		try {
			switch (browser) {
			case "IE":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			case "Firefox":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\"
							+ Filename + fileformat);
				}
				break;
			case "Chrome":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			case "Safari":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public String CompnameSplit(WebDriver driver, String Compname, String Configtype) throws Exception {
		String status = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		// StatesRegistrationPage SR=PageFactory.initElements(driver,
		// StatesRegistrationPage.class);
		try {
			if (Compname.contains("'")) {
				String[] configred = Compname.split("'");
				// String test ="+xpathStr[0]+"'"+","+"\"'\""+","+"'"+xpathStr[1]";
				// String test=configred[0]+ "'"+configred[1];
				waitforText(180, "//h2[contains(.,concat('" + configred[0] + "'" + "," + "\"'\"" + "," + "'"
						+ configred[1] + "'))]", driver);
				if (VerifyObject(driver, "//h2[contains(.,concat('" + configred[0] + "'" + "," + "\"'\"" + "," + "'"
						+ configred[1] + "'))]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					boolean report = false;
					do {
						driver.navigate().refresh();
						Thread.sleep(15000);
						if (!VerifyObject(driver,
								"//h2[contains(.,concat('" + configred[0] + "'" + "," + "\"'\"" + "," + "'"
										+ configred[1]
										+ "'))]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
							report = true;
						}
					} while (report == false);
				}
			} else {
				// waitforText(180,"//h2[contains(.,'"+Compname+"')]",driver);
				if (VerifyObject(driver, "//h2[contains(.,'" + Compname
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					boolean report = false;
					do {
						driver.navigate().refresh();
						Thread.sleep(15000);
						if (!VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
							report = true;
						}
					} while (report == false);
				}
			}
			switch (Configtype) {
			case "statereg":
				if (VerifyObject(driver, "//h2[contains(.,'" + Compname
						+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")) {
					WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
							+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				} else {
					if (Compname.contains("'")) {
						String[] xpathStr = Compname.split("'");
						if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + ","
								+ "'" + xpathStr[1] + "'))]/../div/div/h3/span/a[contains(.,'Edit')]")) {
							WebElement EditCompany = driver.findElement(
									By.xpath("//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + "," + "'"
											+ xpathStr[1] + "'))]/../div/div/h3/span/a[contains(.,'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCompany);
						} else if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\""
								+ "," + "'" + xpathStr[1]
								+ "'))]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]")) {
							WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,concat('" + xpathStr[0]
									+ "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
						} else if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\""
								+ "," + "'" + xpathStr[1] + "'))]/../../div/div/div/h3/span/a[contains(.,'Edit')]")) {
							WebElement EditComp = driver.findElement(
									By.xpath("//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + "," + "'"
											+ xpathStr[1] + "'))]/../../div/div/div/h3/span/a[contains(.,'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditComp);
						} else {
							WebElement Edit = driver.findElement(By.xpath("//h2[contains(.,concat('" + xpathStr[0] + "'"
									+ "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../../../../../div/div/div/div/h3/span/a[contains(text(),'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", Edit);
						}
					} else {
						if (VerifyObject(driver,
								"//h2[contains(.,'" + Compname + "')]/../div/div/h3/span/a[contains(.,'Edit')]")) {
							WebElement EditCompany = driver.findElement(By.xpath(
									"//h2[contains(.,'" + Compname + "')]/../div/div/h3/span/a[contains(.,'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCompany);
						} else if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]")) {
							WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
						} else if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../../div/div/div/h3/span/a[contains(.,'Edit')]")) {
							WebElement EditComp = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../div/div/div/h3/span/a[contains(.,'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditComp);
						} else {
							WebElement Edit = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../../../../div/div/div/div/h3/span/a[contains(text(),'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", Edit);
						}
					}
				}
				break;
			case "Editcompany":

				if (VerifyObject(driver, "//h2[contains(.,'" + Compname
						+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]")) {
					WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
							+ "')]/../../../div/div[contains(@id,'registrations')]/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				} else {
					if (Compname.contains("'")) {
						String[] xpathStr = Compname.split("'");
						waitforElement(180, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + "," + "'"
								+ xpathStr[1] + "'))]", driver);
						if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + ","
								+ "'" + xpathStr[1]
								+ "'))]/../div/div/div/div[@class='ng-hide']/h3[@class='h5 heading-accent']/span/a[contains(text(),'Edit')]")) {
							WebElement EditCompany1 = driver.findElement(By.xpath("//h2[contains(.,concat('"
									+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../div/div/div/div[not(@class)]/h3[@class='h5 heading-accent has-error']/span/a[contains(text(),'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCompany1);
						} else if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\""
								+ "," + "'" + xpathStr[1]
								+ "'))]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]")) {
							WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,concat('" + xpathStr[0]
									+ "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
						} else {
							driver.findElement(By.xpath("//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\""
									+ "," + "'" + xpathStr[1]
									+ "'))]/../div/div/div/div/h3[@class='h5 heading-accent']/span/a[contains(text(),'Edit')]"))
									.click();
						}
					} else {
						waitforElement(180, "//h2[contains(.,'" + Compname + "')]", driver);
						if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../div/div/div/div[@class='ng-hide']/h3[@class='h5 heading-accent']/span/a[contains(text(),'Edit')]")) {
							WebElement EditCompany1 = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../div/div/div/div[not(@class)]/h3[@class='h5 heading-accent has-error']/span/a[contains(text(),'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditCompany1);
						} else if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]")) {
							WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../../../div/div/div[contains(@id,'registrations')]/h3/span/a[contains(text(),'Edit')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
						} else {
							driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../div/div/div/div/h3[@class='h5 heading-accent']/span/a[contains(text(),'Edit')]"))
									.click();
						}
					}
				}
				break;
			case "Mapped":

				if (VerifyObject(driver,
						"//h2[contains(.,'" + Compname + "')]/../../../div/div/div/a[contains(.,'Mapped')]")) {
					WebElement MappedProducts = driver.findElement(By.xpath(
							"//h2[contains(.,'" + Compname + "')]/../../../div/div/div/a[contains(.,'Mapped')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProducts);
				} else {
					if (Compname.contains("'")) {
						String[] xpathStr = Compname.split("'");
						if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + ","
								+ "'" + xpathStr[1]
								+ "'))]/../../../../../../div[1]/div/div/div/div/div[2]/div/div/a[contains(.,'Mapped')]")) {
							WebElement MappedProductsGREEN = driver.findElement(By.xpath("//h2[contains(.,concat('"
									+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../../../../../../div[1]/div/div/div/div/div[2]/div/div/a[contains(.,'Mapped')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductsGREEN);
						} else if (VerifyObject(driver,
								"//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
										+ "'))]/../../../../div/div/div/div/a[contains(.,'Mapped')]")) {
							WebElement MappedProductsRED = driver.findElement(By.xpath("//h2[contains(.,concat('"
									+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../../../../div/div/div/div/a[contains(.,'Mapped')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductsRED);
						}
					} else {
						if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../../../../../../div[1]/div/div/div/div/div[2]/div/div/a[contains(.,'Mapped')]")) {
							WebElement MappedProductsGREEN = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../../../../../div[1]/div/div/div/div/div[2]/div/div/a[contains(.,'Mapped')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductsGREEN);
						} else if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../../../../div/div/div/div/a[contains(.,'Mapped')]")) {
							WebElement MappedProductsRED = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../../../div/div/div/div/a[contains(.,'Mapped')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductsRED);
						}
					}
				}
				break;
			case "Product":
				if (VerifyObject(driver,
						"//h2[contains(.,'" + Compname + "')]/../../../div/div/h3/span/a[contains(.,'+ Add')]")) {
					WebElement AddProducts = driver.findElement(By.xpath(
							"//h2[contains(.,'" + Compname + "')]/../../../div/div/h3/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProducts);
				} else {
					if (Compname.contains("'")) {
						String[] xpathStr = Compname.split("'");
						if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + ","
								+ "'" + xpathStr[1]
								+ "'))]/../../../../div/div/div/h3[contains(.,' Mappings')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,concat('"
									+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../../../../div/div/div/h3[contains(.,' Mappings')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
						} else if (VerifyObject(driver,
								"//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
										+ "'))]/../div/div/h3[contains(.,' Mappings')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,concat('"
									+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../div/div/h3[contains(.,' Mappings')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
						}
					} else {
						if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../../../div/div/div/h3[contains(.,' Mappings')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
						} else if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../div/div/h3[contains(.,' Mappings')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddProductsGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
						}
					}
				}

				break;
			case "AddCustomer":
				if (VerifyObject(driver, "//h2[contains(.,'" + Compname
						+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
					WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
							+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
				} else {
					if (Compname.contains("'")) {
						String[] xpathStr = Compname.split("'");
						/*
						 * if(VerifyObject(driver,"//h2[contains(.,\""+
						 * Compname+"\")]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"
						 * )) {WebElement AddException =
						 * driver.findElement(By.xpath("//h2[contains(.,\""+
						 * Compname+"\")]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"
						 * )); ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
						 * AddException);} else if(VerifyObject(driver,"//h2[contains(.,\""+
						 * Compname+"\")]/../../../div/div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"
						 * )) {WebElement AddExceptionGreen =
						 * driver.findElement(By.xpath("//h2[contains(.,\""+
						 * Compname+"\")]/../../../div/div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"
						 * )); ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
						 * AddExceptionGreen);}}
						 */

						if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + ","
								+ "'" + xpathStr[1]
								+ "'))]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,concat('"
									+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
						} else if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\""
								+ "," + "'" + xpathStr[1]
								+ "'))]/../../../div/div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,concat('"
									+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../../../div/div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
						}
					} else {
						if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
						} else if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../../../div/div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../../div/div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
						}
					}
				}
				break;
			case "AddException":

				if (VerifyObject(driver, "//h2[contains(.,'" + Compname
						+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
					WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
							+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
				} else {
					if (Compname.contains("'")) {
						String[] xpathStr = Compname.split("'");
						if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\"" + ","
								+ "'" + xpathStr[1]
								+ "'))]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddExceptionRed = driver.findElement(By.xpath("//h2[contains(.,concat('"
									+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionRed);
						} else if (VerifyObject(driver, "//h2[contains(.,concat('" + xpathStr[0] + "'" + "," + "\"'\""
								+ "," + "'" + xpathStr[1]
								+ "'))]/../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,concat('"
									+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1]
									+ "'))]/../../../../div/div/div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
						}
					} else {
						if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddExceptionRed = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../../../div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionRed);
						} else if (VerifyObject(driver, "//h2[contains(.,'" + Compname
								+ "')]/../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
							WebElement AddExceptionGreen = driver.findElement(By.xpath("//h2[contains(.,'" + Compname
									+ "')]/../../../../div/div/div/div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddExceptionGreen);
						}
					}
				}
				break;
			case "Configure":
				if (Compname.contains("'")) {
					if (VerifyObject(driver, "//h2[contains(.,\"" + Companynamesplit
							+ "\")]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
						boolean report = false;
						do {
							JavaScriptclick(driver, CNP.CONFIGURE);
							waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
							Thread.sleep(8000);
							if (!VerifyObject(driver, "//h2[contains(.,\"" + Companynamesplit
									+ "\")]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
								report = true;
							}
						} while (report == false);
					}
				} else {
					if (VerifyObject(driver, "//h2[contains(.,'" + Compname
							+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
						boolean report = false;
						do {
							JavaScriptclick(driver, CNP.CONFIGURE);
							waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
							Thread.sleep(8000);
							if (!VerifyObject(driver, "//h2[contains(.,'" + Compname
									+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
								report = true;
							}
						} while (report == false);
					}
				}
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public String ProductnameSplit(WebDriver driver, String Productname) throws Exception {
		String status = "";
		try {
			if (Productname.contains("'")) {
				String[] xpathStr = Productname.split("'");
				WebElement prod = driver
						.findElement(By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()=concat('"
								+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1] + "')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", prod);
			} else {
				WebElement jur = driver.findElement(
						By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + Productname + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
			}
		} catch (Exception e) {
			return e.getMessage() + status;

		}
		return status;
	}

	public String ProductnameSplit(WebDriver driver, int i, String Productname) throws Exception {
		String status = "";
		try {
			if (Productname.contains("'")) {
				String[] xpathStr = Productname.split("'");
				WebElement prod = driver
						.findElement(By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()=concat('"
								+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1] + "')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", prod);
			} else {
				WebElement jur = driver.findElement(
						By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + Productname + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
			}
		} catch (Exception e) {
			return e.getMessage() + status;

		}
		return status;
	}

	public String ProductSplit(WebDriver driver, String Productname) throws Exception {
		String status = "";
		try {
			if (Productname.contains("'")) {
				String[] xpathStr = Productname.split("'");
				WebElement prod = driver
						.findElement(By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()=concat('"
								+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1] + "')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", prod);
			} else {
				WebElement jur = driver.findElement(
						By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + Productname + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
			}
		} catch (Exception e) {
			return e.getMessage() + status;

		}
		return status;
	}

	// flex fields
	public void FlexFields(String Data_Type, String Name, String Taxability_Category, String File) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		TaxassistantRule PUL = PageFactory.initElements(driver, TaxassistantRule.class);
		FlexField FFP = PageFactory.initElements(driver, FlexField.class);
		String DownloadFilepath = xDownloadFiles("FlexFields");
		waitforElement(180, PUL.CONFIGURELINK, driver);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, FFP.AllFlexFields, driver);
		JavaScriptclick(driver, FFP.AllFlexFields);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, FFP.ConfigureFlexFieldsLabel, driver);
		assertTrue(FFP.ConfigureFlexFieldsLabel.isDisplayed());
		assertTrue(FFP.TableColHeaders.isDisplayed());
		assertTrue(FFP.ReturnConfigRight.isDisplayed());
		assertTrue(FFP.ReturnConfigLeft.isDisplayed());
		if (File != "") {
			click(FFP.DownloadBtn);
			Thread.sleep(6000);
			File tempfile = null;
			File tempfile2 = null;
			Robot rob = new Robot();
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(10000);
				rob.keyPress(KeyEvent.VK_CONTROL);
				rob.keyPress(KeyEvent.VK_L);
				rob.keyRelease(KeyEvent.VK_L);
				rob.keyRelease(KeyEvent.VK_CONTROL);
				rob.keyPress(KeyEvent.VK_ENTER);
				rob.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(10000);
				rob.keyPress(KeyEvent.VK_TAB);
				rob.keyRelease(KeyEvent.VK_TAB);
				rob.keyPress(KeyEvent.VK_TAB);
				rob.keyRelease(KeyEvent.VK_TAB);
				rob.keyPress(KeyEvent.VK_DOWN);
				rob.keyRelease(KeyEvent.VK_DOWN);
				rob.keyPress(KeyEvent.VK_DOWN);
				rob.keyRelease(KeyEvent.VK_DOWN);
				rob.keyPress(KeyEvent.VK_ENTER);
				rob.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				rob.keyPress(KeyEvent.VK_HOME);
				rob.keyRelease(KeyEvent.VK_HOME);
				Thread.sleep(5000);
				StringSelection stringSelection = new StringSelection(DownloadFilepath + "\\");
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
				Thread.sleep(5000);
				rob.keyPress(KeyEvent.VK_CONTROL);
				rob.keyPress(KeyEvent.VK_V);
				rob.keyRelease(KeyEvent.VK_V);
				rob.keyRelease(KeyEvent.VK_CONTROL);
				rob.delay(5000);
				rob.keyPress(KeyEvent.VK_ENTER);
				rob.keyRelease(KeyEvent.VK_ENTER);
				rob.delay(1000);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "FlexFields";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				tempfile = new File(LatestDownloadedFile("FlexFields", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(8000);
				tempfile = new File(LatestDownloadedFile("FlexFields", false));
			}
			File directory = new File(".//");
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
					+ "FlexFields";
			String name = File;
			tempfile2 = new File(DirName + "\\" + name + ".csv");
			Thread.sleep(5000);
			Comparecsvfiles(tempfile, tempfile2);
			click(FFP.ActionViewBox);
			Thread.sleep(2000);
			assertTrue(FFP.EditLnk.isDisplayed());
			click(FFP.EditLnk);
			waitforElement(30, FFP.EditFlexFieldLabels, driver);
			assertTrue(FFP.EditFlexFieldLabels.isDisplayed());
			assertTrue(FFP.CloseIcon.isDisplayed());
			assertTrue(FFP.FlexDataType.isDisplayed());
			assertTrue(FFP.FlexName.isDisplayed());
			click(FFP.CancelBtn);
		}
		waitforElement(30, FFP.EditLnk, driver);
		driver.findElement(By.xpath(
				"//table/tbody/tr/td[contains(.,'" + Data_Type + "')]/../td[text()='" + Name + "']/../td[4]/div/a"))
				.click();
		Thread.sleep(5000);
		WebElement key = driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + Data_Type
				+ "')]/../td[text()='" + Name + "']/../td[4]/div[1]/ul[1]/li[1]/a[1]"));
		// JavascriptExecutor jse = ((JavascriptExecutor) driver);
		// jse.executeScript("arguments[0].scrollIntoView();", key);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", key);
		Thread.sleep(3000);
		// driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'"+CloudData.get_data("Data_Type")+"')]/../td[text()='"+CloudData.get_data("Name")+"']/../td[4]/div[1]/ul[1]/li[1]/a[1]")).click();
		waitforElement(30, FFP.EditFlexFieldLabels, driver);
		clear(FFP.FlexDescription);
		type(FFP.FlexDescription, Name);
		if (Data_Type.equalsIgnoreCase("Code")) {
			if (!VerifyObject(driver, FFP.QuickSearchTaxabilityCategory)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		} else {
			type(FFP.QuickSearchTaxabilityCategory, "Date");
			List<WebElement> list = driver.findElements(By.xpath("//ul/li"));
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getText().equals(Taxability_Category)) {
					list.get(i).click();
					break;
				}
			}
			Thread.sleep(3000);
			sendkeysTab(FFP.QuickSearchTaxabilityCategory);
		}
		click(FFP.SaveBtn);
		Thread.sleep(2000);
	}

	// Oseries
	public void VerifyTaxpayerinOseries(String companyCode, String companyName) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.OMyenterprise, driver);
		click(CNP.OMyenterprise);
		waitforText(180, CNP.OTaxpayers, driver);
		JavaScriptclick(driver, CNP.OTaxpayers);
		Thread.sleep(2000);
		waitforElement(180, CNP.taxpayerCode_input, driver);
		type(CNP.taxpayerCode_input, companyCode);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.search_button);
		Thread.sleep(5000);
		click(CNP.OTaxpayername);
		String ocname = getText(CNP.OTaxpayername);
		String ocode = getText(CNP.OTaxpayerCode);
		assertTrue(ocname.equalsIgnoreCase(companyName));
		assertTrue(ocode.equalsIgnoreCase(companyCode));
		click(driver.findElement(By.xpath("//*[@id=\"site-header\"]/div/div[1]/div[1]/button/img")));

	}

	public void VerifyRegistrationsinOseries(String companyCode, String companyName, String Statename)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.OMyenterprise, driver);
		JavaScriptclick(driver, CNP.OMyenterprise);
		waitforText(180, CNP.OTaxpayers, driver);
		JavaScriptclick(driver, CNP.OTaxpayers);
		Thread.sleep(2000);
		waitforElement(180, CNP.taxpayerCode_input, driver);
		type(CNP.taxpayerCode_input, companyCode);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.search_button);
		waitforElement(180, CNP.OTaxpayername, driver);
		Thread.sleep(3000);
		click(CNP.OTaxpayername);
		Thread.sleep(3000);
		click(CNP.oTaxpayeractions);
		click(CNP.Viewlink);
		Thread.sleep(2000);
		waitforElement(180, CNP.oregistrations, driver);
		JavaScriptclick(driver, CNP.oregistrations);
		Actions actions = new Actions(driver);
		WebElement elementLocator = CNP.usalink;
		actions.doubleClick(elementLocator).perform();
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.oregistrationsplus);
		String SN[] = Statename.split(",");
		WebElement s1;
		for (int i = 0; i < SN.length; i++) {
			s1 = driver.findElement(
					By.xpath("//span[text()='UNITED STATES, " + SN[i].toUpperCase() + " (All Jurisdictions)']"));
			Thread.sleep(500);
			if (VerifyObject(driver, s1)) {
				s1.click();
				assertTrue(true);
			}
		}
		click(CNP.oLocations);

	}

	public void VerifyCanadaRegistrationsinOseries(String companyCode, String companyName, String ProvinceName)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.OMyenterprise, driver);
		JavaScriptclick(driver, CNP.OMyenterprise);
		waitforText(180, CNP.OTaxpayers, driver);
		JavaScriptclick(driver, CNP.OTaxpayers);
		Thread.sleep(2000);
		waitforElement(180, CNP.taxpayerCode_input, driver);
		type(CNP.taxpayerCode_input, companyCode);
		Thread.sleep(3000);
		JavaScriptclick(driver, CNP.search_button);
		waitforElement(180, CNP.OTaxpayername, driver);
		Thread.sleep(3000);
		click(CNP.OTaxpayername);
		Thread.sleep(3000);
		click(CNP.oTaxpayeractions);
		click(CNP.Viewlink);
		Thread.sleep(2000);
		waitforElement(180, CNP.oregistrations, driver);
		JavaScriptclick(driver, CNP.oregistrations);
		Actions actions = new Actions(driver);
		WebElement elementLocator = CNP.canadalink;
		actions.doubleClick(elementLocator).perform();
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.oregistrationsplus);
		String SN[] = ProvinceName.split(",");
		WebElement s1;
		for (int i = 0; i < SN.length; i++) {
			s1 = driver
					.findElement(By.xpath("//span[text()='CANADA, " + SN[i].toUpperCase() + " (All Jurisdictions)']"));
			Thread.sleep(500);
			if (VerifyObject(driver, s1)) {
				s1.click();
				assertTrue(true);
			}
		}
		click(CNP.oLocations);

	}

	public void VerifyLocationsinOseries(String companyCode, String companyName, String locationstate)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(CNP.oLocations);
		Thread.sleep(2000);
		WebElement s1 = driver.findElement(By.xpath(
				"//div[@id='registrationLocations_table']/div/div/div/div/div[3]/div[2]/div/div/div/div/div/span[contains(.,'1')]/../../../../div[contains(.,'"
						+ locationstate.toUpperCase() + "')]"));
		assertTrue(s1.isDisplayed());
		// scrollDown(driver);
		// driver.findElement(By.xpath("//div[@id='BusinessLocationsTable_table']/div/div/div/div/div[3]/div[2]/div/div/div/div[4]")).click();
		Thread.sleep(2000);
	}

	public void VerifyTaxassistruleinOseries(String Rulename) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		scrollDown(driver);
		JavaScriptclick(driver, CNP.taxsetup);
		click(CNP.taxassist);
		click(CNP.Precalculation);
		WebElement s1 = driver.findElement(
				By.xpath("//div[@id='taxAssistRules_table']/div/div/div/div/div[3]/div[2]/div/div/div/div[contains(.,'"
						+ Rulename + "')]"));
		assertTrue(s1.isDisplayed());
		Thread.sleep(1000);
		scrollUp(driver);
		JavaScriptclick(driver, CNP.taxsetup);
	}

	public void VerifyMappinginOseries(String ProductType, String ProductName, String ProductCode, String Taxpayer,
			String StartDate) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.OMyenterprise, driver);
		clear(CNP.OAsofDate);
		click(CNP.OAsofDate);
		type(CNP.OAsofDate, StartDate);
		sendkeysTab(CNP.OAsofDate);
		click(CNP.OMyenterprise);
		waitforText(180, CNP.OTaxbilityDriver, driver);
		JavaScriptclick(driver, CNP.OTaxbilityDriver);
		Thread.sleep(2000);
		waitforElement(180, CNP.OTaxbilityDriverslink, driver);
		click(CNP.OTaxbilityDriverslink);
		waitforElement(180, CNP.OTaxbilityDriversheader, driver);
		waitforElement(180, CNP.Prodnameinput, driver);
		click(driver.findElement(By.xpath("//div[@id='inputParameterId_select']/div/div[2]/div")));
		driver.findElement(
				By.xpath("//div[@id='inputParameterId_select']/div/div/div[contains(.,'" + ProductType + "')]"))
				.click();
		JavaScriptclick(driver, CNP.search_button);
		assertTrue(driver.findElement(By.xpath("//div[text()='" + ProductCode + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[text()='" + ProductName + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[text()='" + Taxpayer + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[text()='" + StartDate + "']")).isDisplayed());
		click(driver.findElement(By.xpath("//*[@id='site-header']/div/div[1]/div[1]/button/img")));
		Thread.sleep(2000);
	}

	public void VerifyFlexfieldsinOseries(String Flexfieldvalue, String Datatype, String taxabilityCategoryName,
			String StartDate) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.OMyenterprise, driver);
		clear(CNP.OAsofDate);
		click(CNP.OAsofDate);
		type(CNP.OAsofDate, StartDate);
		sendkeysTab(CNP.OAsofDate);
		scrollDown(driver);
		JavaScriptclick(driver, CNP.TaxSetup);
		waitforText(180, CNP.Flexiblefields, driver);
		JavaScriptclick(driver, CNP.Flexiblefields);
		Thread.sleep(2000);
		waitforElement(180, CNP.OFFHeader, driver);
		if (Datatype.equalsIgnoreCase("Numeric") || Datatype.equalsIgnoreCase("Date")) {
			scrollDown(driver);
			JavaScriptclick(driver, CNP.Nextpage);
			Thread.sleep(2000);
			assertTrue(driver.findElement(By.xpath("//div[@col-id='shortName' and text()='" + Flexfieldvalue + "']"))
					.isDisplayed());
			assertTrue(driver.findElement(By.xpath("//div[contains(.,'" + Flexfieldvalue
					+ "')]/../div[@col-id='dataType'and text()='" + Datatype + "']")).isDisplayed());
			if (taxabilityCategoryName != "") {
				System.out.println(taxabilityCategoryName);
				assertTrue(driver.findElement(By.xpath("//div[contains(.,'" + Flexfieldvalue
						+ "')]/../div[@col-id='taxabilityCategoryName' and text()='" + taxabilityCategoryName + "']"))
						.isDisplayed());
			}
		} else {
			assertTrue(driver.findElement(By.xpath("//div[@col-id='shortName' and text()='" + Flexfieldvalue + "']"))
					.isDisplayed());
			assertTrue(driver.findElement(By.xpath("//div[contains(.,'" + Flexfieldvalue
					+ "')]/../div[@col-id='dataType'and text()='" + Datatype + "']")).isDisplayed());
			if (taxabilityCategoryName != "") {
				assertTrue(driver.findElement(By.xpath("//div[contains(.,'" + Flexfieldvalue
						+ "')]/../div[@col-id='taxabilityCategoryName' and text()='" + taxabilityCategoryName + "']"))
						.isDisplayed());
			}
		}
		scrollUp(driver);
		JavaScriptclick(driver, CNP.taxsetup);
	}

	public void VerifycategoreisinOseries(String categoryname, String StartDate) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.OMyenterprise, driver);
		clear(CNP.OAsofDate);
		click(CNP.OAsofDate);
		type(CNP.OAsofDate, StartDate);
		sendkeysTab(CNP.OAsofDate);
		scrollDown(driver);
		JavaScriptclick(driver, CNP.TaxSetup);
		waitforText(180, CNP.TaxabilityCategories, driver);
		JavaScriptclick(driver, CNP.TaxabilityCategories);
		Thread.sleep(2000);
		waitforElement(180, CNP.categoryname, driver);
		click(CNP.categoryname);
		type(CNP.categoryname, categoryname);
		sendkeysTab(CNP.categoryname);
		Thread.sleep(2000);
		// click(CNP.search_button);
		assertTrue(driver.findElement(By.xpath("//span[contains(.,'" + categoryname + "')]")).isDisplayed());
		scrollUp(driver);
		JavaScriptclick(driver, CNP.taxsetup);

	}

	public void VerifyCustomerInOseries(String customerType, String code, String customerName, String taxpayer,
			String startDate, String endDate) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.OMyenterprise, driver);
		clear(CNP.OAsofDate);
		click(CNP.OAsofDate);
		type(CNP.OAsofDate, startDate);
		sendkeysTab(CNP.OAsofDate);
		Thread.sleep(5000);
		JavaScriptclick(driver, CNP.OMyenterprise);
		waitforElement(180, CNP.OCustomers, driver);
		click(CNP.OCustomers);
		if (customerType.equalsIgnoreCase("code")) {
			waitforElement(180, CNP.search_button, driver);
			click(CNP.search_button);
		} else {
			waitforElement(180, CNP.search_button, driver);
			click(driver.findElement(By.xpath("//*[@id=\"customerSelectorType_select\"]/div")));
			Thread.sleep(2000);
			click(driver.findElement(By.xpath("//div[text()='Customer Class']")));
			click(CNP.search_button);
		}
		assertTrue(driver.findElement(By.xpath("//span[text()='" + code + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[text()='" + customerName + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[text()='" + taxpayer + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[text()='" + startDate + "']")).isDisplayed());
		if (endDate.isEmpty()) {
			assertTrue(driver.findElement(By.xpath("//div[text()='No End Date']")).isDisplayed());
		} else {
			assertTrue(driver.findElement(By.xpath("//div[text()='" + endDate + "']")).isDisplayed());
		}
		Thread.sleep(2000);
		click(driver.findElement(By.xpath("//*[@id=\"site-header\"]/div/div[1]/div[1]/button/img")));
		Thread.sleep(2000);
		scrollUp(driver);
	}

	public void VerifyCertificatesinOseries(String CustomerCode, String Certificatescope, String Statename,
			String Certificatetype, String certstatrtdate, String Cetnumber, String Reason, String Customername,
			String taxresult, String Enddate) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.OMyenterprise, driver);
		Thread.sleep(2000);
		clear(CNP.OAsofDate);
		click(CNP.OAsofDate);
		type(CNP.OAsofDate, certstatrtdate);
		sendkeysEnter(CNP.OAsofDate);
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.OMyenterprise);
		waitforText(180, CNP.Certificates, driver);
		JavaScriptclick(driver, CNP.Certificates);
		Thread.sleep(2000);
		waitforElement(180, CNP.OcertiHeader, driver);
		waitforElement(180, CNP.Certificateinput, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, CNP.search_button);
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.xpath("//div[text()='" + Customername + "']")).isDisplayed());
		int numOfCerts = driver.findElements(By.xpath("//div[text()='" + Customername + "']")).size();
		assertTrue(driver.findElement(By.xpath("//div[text()='" + CustomerCode + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[text()='" + Certificatescope + "']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//div[text()='" + certstatrtdate + "']")).isDisplayed());
		if (Enddate.isEmpty()) {
			assertTrue(driver.findElement(By.xpath("//div[text()='No End Date']")).isDisplayed());
		} else {
			assertTrue(driver.findElement(By.xpath("//div[text()='" + Enddate + "']")).isDisplayed());
			assertTrue(driver.findElements(By.xpath("//div[text()='No date']")).size() <= numOfCerts);
		}
		JavaScriptclick(driver, CNP.OMyenterprise);
	}

	@SuppressWarnings("resource")
	public String ReadTextFile() throws IOException {
		try {
			File file = new File(".//");
			String mainfolder = file.getCanonicalPath();
			String staterow = mainfolder + "\\resources\\qboimporter\\States.txt";
			File f = new File(staterow);
			String readLine = "";
			BufferedReader b = new BufferedReader(new FileReader(f));
			System.out.println("Reading file using Buffered Reader");
			while ((readLine = b.readLine()) != null) {
				Staterow = readLine;
				System.out.println(readLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Staterow;
	}

	public String WriteStates() throws IOException {
		try {
			File file = new File(".//");
			String mainfolder = file.getCanonicalPath();
			String newvalue = mainfolder + "\\resources\\qboimporter\\States.txt";
			File f = new File(newvalue);
			BufferedWriter bw = null;
			FileWriter fw = null;
			Integer Appendedvalue = Integer.parseInt(Staterow);
			Appendedvalue++;
			int data = Appendedvalue;

			// File file = new File(FILENAME);

			// if file doesnt exists, then create it
			f.delete();
			if (!f.exists()) {
				f.createNewFile();
			}

			// true = append file
			fw = new FileWriter(f.getAbsoluteFile(), true);

			bw = new BufferedWriter(fw);

			bw.write(String.valueOf(data));

			System.out.println("Done");
			if (bw != null)
				bw.close();

			if (fw != null)
				fw.close();
		} catch (Exception e) {
			e.getMessage();

		}
		return NewStatecount;
	}

	public String tableRowCount(WebDriver driver, int data_Row) throws Exception {
		String status = "";
		try {
			Documentcenter DC = PageFactory.initElements(driver, Documentcenter.class);
			int rowcount = (DC.ROWCOLLECTION).size();
			rowcount2 = rowcount + rowcount2;
			if (!VerifyObject(driver, DC.PAGINATION)) {
				if (VerifyObject(driver, DC.PAGINATIONCOUNT)) {
					String page = getText(DC.PAGINATIONCOUNT);
					int page1 = Integer.parseInt(page) + 1;
					Thread.sleep(2000);
					driver.findElement(By.xpath(
							"//div[@class='pagination pagination-right ng-scope']/ul/li[@class='ng-scope']/a[contains(.,"
									+ page1 + ")]"))
							.click();
					tableRowCount(driver, data_Row);
				}
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public void VerifyPublicAPICredentials(String Public_API_Generate_Status) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.ADAPTERSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.TRUSTEDIDLABEL, driver);
		if (Public_API_Generate_Status.equalsIgnoreCase("Yes")) {
			assertTrue(getText(PUL.ConnectorsAPIKeyblank).trim().isEmpty());
			assertTrue(getText(PUL.ConnectorsAPIPasswordblank).trim().isEmpty());
		} else {
			ConnectorAPIKey = getText(PUL.ConnectorsAPIKeyblank).trim();
			assertTrue(ConnectorAPIKey.equalsIgnoreCase(APIkey));
			ConnectorAPIPassword = getText(PUL.ConnectorsAPIPasswordblank).trim();
			assertTrue(ConnectorAPIPassword.equalsIgnoreCase(APIPassword));
			assertTrue(getText(PUL.ConnectorsAPIPasswordblank).trim().equalsIgnoreCase(APIPassword));
		}

	}

	public void EditProduct(String TaxPayer, String ProductCode, String EndDate) throws Exception {

		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//h2[contains(.,'" + TaxPayer
				+ "')]/../../../div/div[contains(@id,'mappings')]/h3/span/a[contains(.,'+ Add')]")) {
			WebElement AddProducts = driver.findElement(By.xpath("//h2[contains(.,'" + TaxPayer
					+ "')]/../../../div/div[contains(@id,'mappings')]/h3/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProducts);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		click(AS.Viewallmappings);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);

		driver.findElement(
				By.xpath("//table/tbody/tr/td[contains(.,'" + ProductCode + "')]/.././td/a[contains(.,'Edit')][2]"))
				.click();

		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ENDDATE, driver);
		clear(PP.ENDDATE);
		type(PP.ENDDATE, EndDate);
		Thread.sleep(2000);
		sendkeysTab(PP.ENDDATE);
		Thread.sleep(2000);
		click(AS.SAVEPRODUCT);
		Thread.sleep(2000);
	}

	public void EditCustomer(String Company, String Customer_Name, String Expcode, String Cert_Exception_EndDate)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]")) {
			driver.findElement(
					By.xpath("//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
		} else {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]")) {
				driver.findElement(
						By.xpath("//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]")) {
				driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			}
		}
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, Expcode);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(5000);
		JavaScriptclick(driver, CE.Actionitem);
		waitforElement(180, CE.Actionitemlink, driver);
		Thread.sleep(5000);
		waitforElement(180,
				"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]",
				driver);
		driver.findElement(By.xpath(
				"//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody[1]/tr[1]/td/div/ul/li[contains(.,'Edit')]"))
				.click();
		waitforText(180, CE.EDITCUSTOMERTEXT, driver);
		clear(CE.CUSTENDDATE);
		click(CE.CUSTENDDATE);
		type(CE.CUSTENDDATE, Cert_Exception_EndDate);
		sendkeysTab(CE.CUSTENDDATE);
		Thread.sleep(5000);
		JavaScriptclick(driver, CE.ADDEXCEPTION);
		Thread.sleep(5000);
	}

	public void VatRegForCustomer(String value) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		assertTrue(CE.VATREGISTRATIONTABLE.isDisplayed());
		JavaScriptclick(driver, CE.VATREGISTRATION);
		Thread.sleep(6000);
		waitforElement(60, CE.VATCOUNTRIES, driver);
		// CD-3934 In Jurisdiction drop down, one option is displayed as 'User Defined'
		// while adding VAT registrations for customers
		String valueBeingChecked = "User Defined";
		WebElement drpdwn = driver.findElement(By.id("ddlVATCountry"));
		Select DrpDwnSel = new Select(drpdwn);
		List<WebElement> DrpDwnList = DrpDwnSel.getOptions();
		for (WebElement indElem : DrpDwnList) {
			if (indElem.getText().equalsIgnoreCase(valueBeingChecked)) {
				assertFalse((indElem.getText().equalsIgnoreCase(valueBeingChecked)));
				break;
			}
		}
		selectByValue(CE.VATCOUNTRIES, CloudData.get_data("VAT_Country"));
		click(CE.VATREGISTRATIONCODE);
		type(CE.VATREGISTRATIONCODE, CloudData.get_data("VAT_Regcode"));
		if (CloudData.get_data("Physical_Presence").equalsIgnoreCase("Yes")) {
			click(CE.PHYSICALPRESENCEINDICATOR);
		}
		JavaScriptclick(driver, CE.CANCELVAT);
		Thread.sleep(3000);
		JavaScriptclick(driver, CE.VATREGISTRATION);
		Thread.sleep(3000);
		waitforElement(180, CE.VATCOUNTRIES, driver);
		selectByValue(CE.VATCOUNTRIES, CloudData.get_data("VAT_Country"));
		click(CE.VATREGISTRATIONCODE);
		type(CE.VATREGISTRATIONCODE, CloudData.get_data("VAT_Regcode"));
		if (CloudData.get_data("Physical_Presence").equalsIgnoreCase("Yes")) {
			click(CE.PHYSICALPRESENCEINDICATOR);
		}
		JavaScriptclick(driver, CE.SAVEVAT);
		Thread.sleep(3000);
		if (CloudData.get_data("Physical_Presence").equalsIgnoreCase("Yes")) {
			String PP = driver
					.findElement(By.xpath("//td[contains(.,'" + CloudData.get_data("VAT_Country") + "')]/../td[3]"))
					.getText();
			assertTrue(PP.equalsIgnoreCase("true"));
		} else {
			driver.findElement(By.xpath("//td[contains(.,'" + CloudData.get_data("VAT_Country") + "')]/../td[3]"))
					.getText();
		}

		driver.findElement(By.xpath("//td[contains(.,'" + CloudData.get_data("VAT_Country") + "')]/../td[4]/div/a"))
				.click();
		Thread.sleep(3000);
		if (CloudData.get_data("VAT_Country_Edit") != "") {
			driver.findElement(By.xpath("//td[contains(.,'" + CloudData.get_data("VAT_Country")
					+ "')]/../td[4]/div/ul/li/a[contains(.,'Edit')]")).click();
			Thread.sleep(3000);
			waitforElement(180, CE.EDITJURISDICTION, driver);
			selectByValue(CE.VATCOUNTRIES, CloudData.get_data("VAT_Country_Edit"));
			JavaScriptclick(driver, CE.SAVEVAT);
			Thread.sleep(3000);
		}
		if (CloudData.get_data("VAT_Country_Remove") != "") {
			driver.findElement(By.xpath("//td[contains(.,'" + CloudData.get_data("VAT_Country")
					+ "')]/../td[4]/div/ul/li/a[contains(.,'Remove')]")).click();
			Thread.sleep(3000);
			// assertTrue(CE.VATREGISTRATIONTEXT.isDisplayed());
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
	}

	public void EditJurisdictions(String CompanyName, String Country, String State, String Jurisdiction,
			String RegNumber, String FilingMethod, String FilingFrequency, String ThreePosition, String PaymentEFT,
			String BusinessNo, String QuebecNo, String PrepaymentType, String Prepaymentamount, String PerceantageRule,
			String Physicalpresence, String Importreversecharge) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);

		if (!VerifyObject(driver, CNP.EDITCOMPANYINFOLINK)) {
			waitforElement(180, CNP.CONFIGURE, driver);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, CNP.CONFIGURE);
			} else {
				JavaScriptclick(driver, CNP.CONFIGURE);
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(3000);
			// After progress busy complete, its takes 5 secs to enable the objects in the
			// page
			// waitforElement(180, CNP.BEGINREMOVAL, driver);
			if (!VerifyObject(driver, SR.canadaLabel)) {
				if (VerifyObject(driver,
						"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]")) {
					WebElement EditClick = driver.findElement(By.xpath(
							"//h2[contains(.,'" + CompanyName + "')]/../../../div/div/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				} else {
					WebElement EditClick = driver.findElement(By.xpath("//h2[contains(.,'" + CompanyName
							+ "')]/../../../../div/div/h3/span/a[contains(.,'Edit')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", EditClick);
				}
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, SR.canadaLabel, driver);
			waitforElement(60, SR.INTERNATIONAL, driver);
		}

		if (Country.equalsIgnoreCase("CANADA")) {
			JavaScriptclick(driver, SR.canadaLabel);
			Thread.sleep(3000);
		}
		if (Country.equalsIgnoreCase("INTERNATIONAL")) {
			JavaScriptclick(driver, SR.INTERNATIONAL);
			Thread.sleep(3000);
		}
		JurLink1 = Jurisdiction;
		/*
		 * if (!VerifyObject(driver, CNP.RETURNTOCOMPOVERVIEWHEAD)) { if
		 * (VerifyObject(driver, CANreg)) { WebElement OnOFf =
		 * driver.findElement(By.xpath("//strong[text()='" + State +
		 * "']/../div/div/input")); ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", OnOFf); } if
		 * (VerifyObject(driver, USreg)) { WebElement OnOFf =
		 * driver.findElement(By.xpath("//strong[text()='" + State +
		 * "']/../../div/div/input")); ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", OnOFf); } }
		 */
		if (VerifyObject(driver,
				"//td/div/strong[text()='" + State + "']/../../../td/div/a[contains(text(),'Return(s)')]")) {
			WebElement Regs = driver.findElement(By
					.xpath("//td/div/strong[text()='" + State + "']/../../../td/div/a[contains(text(),'Return(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Regs);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(2000);
		}
		if (VerifyObject(driver, "//strong[text()='" + State + "']/../../th/div/a[contains(text(),'Return(s)')]")) {
			WebElement CanRegs = driver.findElement(
					By.xpath("//strong[text()='" + State + "']/../../th/div/a[contains(text(),'Return(s)')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", CanRegs);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(2000);
		}
		if (Country.equalsIgnoreCase("INTERNATIONAL")) {
			clear(SR.STQUICKSRCH);
			Thread.sleep(1000);
			type(SR.STQUICKSRCH, State);
			waitforElement(180, "//strong[text()='" + State + "']", driver);
			WebElement OnOFf = driver.findElement(By.xpath("//strong[text()='" + State + "']/../div/div/input"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", OnOFf);
			waitforElement(60, SR.VATNUMBER, driver);
			type(SR.VATNUMBER, RegNumber);
			if (Physicalpresence.equalsIgnoreCase("Yes")) {
				click(SR.PhysicalPresenceCheckbox);
			}
			if (Importreversecharge.equalsIgnoreCase("Yes")) {
				click(SR.ImportReverseChargeCheckbox);
			}
			WebElement Save = driver.findElement(By.xpath(
					"//strong[text()='" + State + "']/../../../td/ng-form/descendant::button[contains(.,'Save')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Save);
			Thread.sleep(2000);
		} else {
			if (JurLink1 != "") {
				Thread.sleep(5000);
				if (JurLink1.contains("'")) {
					JurLink1.split("'");
					waitforElement(60, "//li/a/span[contains(.,\"" + JurLink1 + "\")]", driver);
					if (Browser.equalsIgnoreCase("Safari")) {
						driver.findElement(By.xpath("//li/a/span[contains(.,\"" + JurLink1 + "\")]")).click();
					} else {
						WebElement jur = driver.findElement(By.xpath("//li/a/span[contains(.,\"" + JurLink1 + "\")]"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
					}
				} else {
					waitforElement(60, "//li/a/span[text()='" + JurLink1 + "']", driver);
					if (Browser.equalsIgnoreCase("Safari")) {
						driver.findElement(By.xpath("//li/a/span[text()='" + JurLink1 + "']")).click();
					} else {
						WebElement jur = driver.findElement(By.xpath("//li/a/span[text()='" + JurLink1 + "']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
					}
				}
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, SR.REGISTRATIONNUMBER, driver);
			if (RegNumber != "") {
				clear(SR.REGISTRATIONNUMBER);
				if (RegNumber.contains("RND")) {
					RegNumber = RND();
				}
				type(SR.REGISTRATIONNUMBER, RegNumber);
				sendkeysTab(SR.REGISTRATIONNUMBER);
			}
			if (FilingMethod != "") {
				selectByValue(SR.FILINGMETHOD, FilingMethod);
			}
			if (FilingFrequency != "") {
				selectByValue(SR.FILINGFREQUENCY, FilingFrequency);
			}
			Thread.sleep(2000);
			if (FilingFrequency.equalsIgnoreCase("Occasional")
					|| (FilingFrequency.equalsIgnoreCase("Occasional/Annually"))) {
				waitforElement(180, SR.Continuebutton, driver);
				JavaScriptclick(driver, SR.Continuebutton);
				Thread.sleep(2000);
			}
			sendkeysTab(SR.FILINGFREQUENCY);
			if (ThreePosition != "") {
				type(SR.THREEPOSITION, ThreePosition);
			}
			if (PaymentEFT != "") {
				click(SR.PAYMENTVIAEFT);
			}
			if ((RegNumber == "") && ((PaymentEFT != ""))) {
				waitforElement(120, SR.RETURNTOREG, driver);
				click(SR.RETURNTOREG);
				waitforElement(120, SR.EXITREG, driver);
				click(SR.EXITREG);
			}
			if (BusinessNo != "") {
				type(SR.BUSINESSNO, BusinessNo);
			}
			if (QuebecNo != "") {
				type(SR.QUEBECNO, QuebecNo);
			}
			// prepayment value
			if (VerifyObject(driver, SR.Prepaymentdetails)) {
				waitforElement(120, SR.Prepaymentamountlabel, driver);
				if ((PrepaymentType != "")) {
					waitforElement(120, SR.Prepaymentamountlabel, driver);
					selectByValue(SR.Prepaymenttype, PrepaymentType);
					if (Prepaymentamount != "") {
						waitforElement(120, SR.Prepaymentvalue, driver);
						clear(SR.Prepaymentvalue);
						type(SR.Prepaymentvalue, Prepaymentamount);
						if (VerifyObject(driver, SR.Percetagebasis)) {
							type(SR.Percetagebasis, PerceantageRule);
						}
					}
				}
			}
			click(SR.SAVEBUTTON);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(120, SR.returnToCompanyOverView, driver);
		}

	}

	public void AdminConfigPush(String ClientName, String taxpayernamecode) throws Exception {

		AdminUserLinks ADMIN = PageFactory.initElements(driver, AdminUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		VarUserTitles = "";
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		} else {
			click(ADMIN.MANAGESMBCUSTOMERS);
		}
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement client = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
		JavaScriptclick(driver, client);
		waitforElement(180, VSC.SMBCUSTOMERREPORT, driver);
		JavaScriptclick(driver, VSC.ADMINDETAILS);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (taxpayernamecode != "") {
			driver.findElement(
					By.xpath("//h2[text()='" + taxpayernamecode + "']/..//a[contains(.,'Push configuration')]"))
					.click();
		} else {
			waitforElement(60, VSC.ADMINPUSHCONFIG, driver);
			JavaScriptclick(driver, VSC.ADMINPUSHCONFIG);

		}
		waitforElement(180, VSC.CONFIGPOPUP, driver);
		JavaScriptclick(driver, VSC.CONFIGPOPUP);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
	}

	public void VerifydeletedMappinginOseries(String ProductType, String ProductName, String ProductCode,
			String StartDate) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, CNP.OMyenterprise, driver);
		clear(CNP.OAsofDate);
		click(CNP.OAsofDate);
		type(CNP.OAsofDate, StartDate);
		sendkeysTab(CNP.OAsofDate);
		click(CNP.OMyenterprise);
		waitforText(180, CNP.OTaxbilityDriver, driver);
		JavaScriptclick(driver, CNP.OTaxbilityDriver);
		Thread.sleep(2000);
		waitforElement(180, CNP.OTaxbilityDriverslink, driver);
		click(CNP.OTaxbilityDriverslink);
		waitforElement(180, CNP.OTaxbilityDriversheader, driver);
		waitforElement(180, CNP.Prodnameinput, driver);
		click(driver.findElement(By.xpath("//div[@id='inputParameterId_select']/div/div[2]/div")));
		driver.findElement(
				By.xpath("//div[@id='inputParameterId_select']/div/div/div[contains(.,'" + ProductType + "')]"))
				.click();
		JavaScriptclick(driver, CNP.search_button);
		assertTrue(!driver.findElement(By.xpath("//div[text()='" + ProductCode + "']")).isDisplayed());
		assertTrue(!driver.findElement(By.xpath("//div[text()='" + ProductName + "']")).isDisplayed());
		click(driver.findElement(By.xpath("//*[@id='site-header']/div/div[1]/div[1]/button/img")));
		Thread.sleep(2000);
	}

	public void SearchCertificateByJurisdiction(String Company, String customerCodeValue, String Cert_Exception_No,
			String jurisdictionName) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		strCustName = "";
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]")) {
			driver.findElement(
					By.xpath("//h2[contains(.,'" + Company + "')]/../../../div/div/div/a[contains(.,'Customers')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
		} else {
			if (VerifyObject(driver, "//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]")) {
				driver.findElement(
						By.xpath("//h2[contains(.,'" + Company + "')]/../div/div/div/a[contains(.,'Customers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + Company
					+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]")) {
				driver.findElement(By.xpath("//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div[contains(@id,'exceptions')]/div/a[contains(.,'Cusotmers')]"))
						.click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, "//table[@class='table table-striped table-bordered ng-isolate-scope']", driver);
			}
		}
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CE.Codeclassradiobtn);
		click(CE.SEARCH);
		type(CE.SEARCH, customerCodeValue);
		sendkeysEnter(CE.SEARCH);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//td[contains(.,'" + customerCodeValue + "')]/../td/span/a")).click();
		waitforElement(120,
				"//tbody/tr/td[contains(.,'" + customerCodeValue + "')]/../td/div/a[@class='dropdown-toggle']", driver);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(10000);
		JavaScriptclick(driver, CE.JurisdictionNameRadiobtn);
		type(CE.SEARCH, jurisdictionName);
		Thread.sleep(5000);
		waitforElement(180, "//tbody/tr/td[contains(.,'" + jurisdictionName + "')", driver);
		click(CNP.CONFIGURE);
	}

	public void Upload10kCertificates(String Company_Name, String Filename) throws Exception {
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String Filename1;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// waitforElement(180, CE.BEGINREMOVAL, driver);
		Thread.sleep(5000);
		Companynamesplit = Company_Name;
		CompnameSplit(driver, Companynamesplit, "AddException");
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		waitforElement(180, CE.UPLOADEXCEPTION, driver);
		JavaScriptclick(driver, CE.UPLOADEXCEPTION);
		Thread.sleep(5000);
		waitforElement(180, CE.BROWSE, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			click(CE.BROWSE);
		} else {
			JavaScriptclick(driver, CE.BROWSE);
		}
		Thread.sleep(4000);
		Path RSource = Paths.get(AdjustmentFilePath + "\\" + Filename + ".csv");
		Filename1 = Filename + RND();
		Path RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + ".csv");
		Files.copy(RSource, RTarget);
		Filename = Filename1;
		Thread.sleep(2000);
		fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, Filename, ".csv");
		Thread.sleep(5000);
		click(CE.EXCEPTIONUPLOAD);
		click(CE.OrgFile);
		Thread.sleep(2000);
		waitforElement(180, CE.VIEWALLEXCEPTIONS, driver);
		int count = 0;
		boolean reportt = false;
		WebElement Succsfile = driver
				.findElement(By.xpath("//td[contains(.,'" + Filename + "')]/../td/a[text()='Success File']"));
		WebElement Errorfile = driver
				.findElement(By.xpath("//td[contains(.,'" + Filename + "')]/../td/a[text()='Error File']"));
		// refreshes continually for 5 minutes to check for success/error file
		do {
			driver.navigate().refresh();
			JavaScriptclick(driver, CE.CANCEL);
			Thread.sleep(10000);
			count++;
			if (count == 90) {
				break;
			}
			if (Succsfile.isDisplayed() || Errorfile.isDisplayed()) {
				reportt = true;
			}
		} while (reportt == false);
		JavaScriptclick(driver, CE.VIEWALLEXCEPTIONS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		JavaScriptclick(driver, CE.RETRURNTOCONFIGUREOVERVIEW);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		waitforElement(180, CE.BEGINREMOVAL, driver);
		driver.navigate().refresh();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.BEGINREMOVAL, driver);
	}

	public void Upload20kCertificates(String Company_Name, String Filename) throws Exception {
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String Filename1;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// waitforElement(180, CE.BEGINREMOVAL, driver);
		Thread.sleep(5000);
		Companynamesplit = Company_Name;
		CompnameSplit(driver, Companynamesplit, "AddException");
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		waitforElement(180, CE.UPLOADEXCEPTION, driver);
		JavaScriptclick(driver, CE.UPLOADEXCEPTION);
		Thread.sleep(5000);
		waitforElement(180, CE.BROWSE, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			click(CE.BROWSE);
		} else {
			JavaScriptclick(driver, CE.BROWSE);
		}
		Thread.sleep(4000);
		Path RSource = Paths.get(AdjustmentFilePath + "\\" + Filename + ".csv");
		Filename1 = Filename + RND();
		Path RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + ".csv");
		Files.copy(RSource, RTarget);
		Filename = Filename1;
		Thread.sleep(2000);
		fileUploadDialog(driver, browser, AutoITpath, AdjustmentFilePath, Filename, ".csv");
		Thread.sleep(5000);
		click(CE.EXCEPTIONUPLOAD);
		Thread.sleep(2000);
		waitforElement(180, CE.VIEWALLEXCEPTIONS, driver);
		int count = 0;
		boolean reportt = false;
		WebElement Succsfile = driver
				.findElement(By.xpath("//td[contains(.,'" + Filename + "')]/../td/a[text()='Success File']"));
		WebElement Errorfile = driver
				.findElement(By.xpath("//td[contains(.,'" + Filename + "')]/../td/a[text()='Error File']"));
		// refreshes continually for 20 minutes to check for success/error file
		do {
			driver.navigate().refresh();
			waitforElement(180, CE.CANCEL, driver);
			JavaScriptclick(driver, CE.CANCEL);
			Thread.sleep(10000);
			count++;
			if (count == 90) {
				break;
			}
			if (Succsfile.isDisplayed() || Errorfile.isDisplayed()) {
				reportt = true;
			}
		} while (reportt == false);
		JavaScriptclick(driver, CE.VIEWALLEXCEPTIONS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.RETRURNTOCONFIGUREOVERVIEW, driver);
		JavaScriptclick(driver, CE.RETRURNTOCONFIGUREOVERVIEW);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		waitforElement(180, CE.BEGINREMOVAL, driver);
		driver.navigate().refresh();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.BEGINREMOVAL, driver);
	}

	public void multiConfigPush(String CompanyName) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		driver.navigate().refresh();
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		scrollDown(driver);
		WebElement saveconfig = driver.findElement(
				By.xpath("//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]"));
		JavaScriptscrolltoView(driver, saveconfig);
		if (VerifyObject(driver,
				"//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]")) {
			waitforElement(180,
					"//h2[contains(.,'" + CompanyName + "')]/../../div/a[contains(text(),'Save Configuration')]",
					driver);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveconfig);
			waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
			if (VerifyObject(driver, CNP.COMPLETECONFIGPOPUP)) {
				JavaScriptclick(driver, CNP.COMPLETECONFIGPOPUP);
			}
		}
		// waitforText(180, CNP.BEGINREMOVAL, driver);
		waitforText(180, "//h2[contains(.,'" + CompanyName + "')]", driver);
		Thread.sleep(5000);
		// assertTrue(VerifyObject(driver,"//h2[contains(.,'" + CompanyName +
		// "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']"));
	}
	/*
	 * This method will add new company for the current login user
	 */

    public void AddNewCompany(String Company_Code, String Fereral_ID, String Company_Name, String Address1,
                              String City, String Zip, String State) throws Exception {
        ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
        click(CNP.CONFIGURE);
        waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
        waitforElement(180, CNP.COMPANYICON, driver);
        waitforElement(180, CNP.COMPANYHEADER, driver);
        String TaxpayerCompanyName1 = "";
        String TaxpayerCode1 = "";
        TaxpayerCode1 = Company_Code;
        type(CNP.COMPANYCODE, TaxpayerCode1);
        sendkeysTab(CNP.COMPANYCODE);
        click(CNP.FEDERALCODE);
        JavaScripttype(driver, CNP.FEDERALCODE, Fereral_ID);
        sendkeysTab(CNP.FEDERALCODE);
        TaxpayerCompanyName1 = Company_Name;
        clear(CNP.COMAPNYNAME);
        type(CNP.COMAPNYNAME, TaxpayerCompanyName1);
        click(CNP.ADDRESS1);
        clear(CNP.ADDRESS1);
        type(CNP.ADDRESS1, Address1);
        sendkeysTab(CNP.ADDRESS1);
        clear(CNP.CITY);
        type(CNP.CITY, City);
        sendkeysTab(CNP.CITY);
        selectByValue(CNP.STATE, State);
        Thread.sleep(2000);
        clear(CNP.ZIP);
        click(CNP.ZIP);
        Thread.sleep(2000);
        JavaScripttype(driver, CNP.ZIP, Zip);
        Thread.sleep(2000);
        sendkeysTab(CNP.ZIP);
        Thread.sleep(3000);
        click(CNP.CITY);
        click(CNP.ADDCOMPANYBUTTON);
        Thread.sleep(3000);
        waitUntilElementInvisibe(180, CNP.SPINNER_XPATH, driver);
        waitforElement(180, CNP.CONTACTCONFIRMATION, driver);
        click(CNP.CONTACTCONFIRMATION);
        waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
        click(CNP.READYTOSAVE);
    }

}
