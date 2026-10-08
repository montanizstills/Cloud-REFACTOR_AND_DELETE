package common.libraries.customer;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.pages.*;

public class Calcparity extends CommonFunctions {

	private WebDriver driver;

	public Calcparity(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);

	public void verifylinksforcloudOseries() throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		waitforElement(180, CL.VERTEXLOGO, driver);
		waitforElement(180, CL.INDIRECTTAXOSERIESTXT, driver);
		waitforElement(360, CL.MYENTERRISELINK, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXAYERSLINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.CUSTOMERSLINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXABILITYDRIVERMANAGEMENT);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXABILITYDRIVERSLINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.COMMODITYCODES);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.COMMSERVICECODESLINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.CERTIFICATESLINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.CERTIFICATESMGT);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.CERTIFICATESPORTAL);
		Thread.sleep(2000);
		assertTrue(CL.SUPPLIES.isDisplayed());
	}

	public void CalcParityLinks() throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		Thread.sleep(15000);
		waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
		assertTrue(CL.INDIRECTTAXOSERIESLINK.isDisplayed());
		assertTrue(CL.ADDRESSCLEANSINGLINK.isDisplayed());
		click(CL.INDIRECTTAXOSERIESLINK);
		Thread.sleep(5000);
		waitforText(180, CL.INDIRECTTAXOSERIESTXT, driver);
		assertTrue(CL.INDIRECTTAXOSERIESTXT.isDisplayed());
		Thread.sleep(2000);
		waitforElement(180, CL.MAINMENU, driver);
		JavaScriptclick(driver, CL.MAINMENU);
		waitforElement(180, CL.MYENTERRISELINK, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXAYERSLINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.CUSTOMERSLINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXABILITYDRIVERMANAGEMENT);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXABILITYDRIVERSLINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.COMMODITYCODES);
		Thread.sleep(2000);
		// JavaScriptclick(driver, CL.COMMSERVICECODESLINK);
		// Thread.sleep(2000);
		JavaScriptclick(driver, CL.CERTIFICATESLINK);
		// JavaScriptclick(driver, CL.CERTIFICATESMGT);
		// Thread.sleep(2000);
		// JavaScriptclick(driver, CL.CERTIFICATESPORTAL);
		// Thread.sleep(2000);
		Thread.sleep(5000);
		waitforElement(180, CL.MAINMENU, driver);
		JavaScriptclick(driver, CL.MAINMENU);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.REPORTS);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.TOOLS);
		waitforElement(180, CL.ADJUSTMENTFILE, driver);
		JavaScriptclick(driver, CL.ADJUSTMENTFILE);
		Thread.sleep(5000);
		assertTrue(CL.SUPPLIES.isDisplayed());
	}

	public void createTaxPayer(String TaxPayercode, String TaxPayername, String regnumber, String locationcode,
			String locationname, String streetaddress, String city, String Postalcode) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);

		Thread.sleep(5000);
		waitforElement(30, CL.INDIRECTTAXOSERIESLINK, driver);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		/*
		 * waitforElement(30, CL.MAINMENU, driver); if (VerifyObject(driver,
		 * CL.MAINMENU)) { waitforElement(180, CL.MAINMENU, driver);
		 * JavaScriptclick(driver, CL.MAINMENU); }
		 */
		waitforElement(30, CL.MAINMENU, driver);
		if (VerifyObject(driver, CL.MAINMENU)) {
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.MYENTERRISELINK, driver);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXAYERSLINK);
		Thread.sleep(2000);
		waitforElement(180, CL.ADDLINK, driver);
		click(CL.ADDLINK);
		waitforElement(180, CL.TAXPAYERCODE, driver);
		type(CL.TAXPAYERCODE, TaxPayercode);
		type(CL.TAXPAYERNAME, TaxPayername);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSTAB, driver);
		click(CL.REGISTRATIONSTAB);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSSEARCH, driver);
		JavaScriptscrolltoView(driver, CL.REGISTRATIONSSEARCH);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.REGISTRATIONSSEARCH);
		waitforElement(180, CL.SELECTALLBTN, driver);
		click(CL.SELECTALLBTN);
		Thread.sleep(5000);
		waitforElement(180, CL.SELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.SELECT);
		waitforElement(180, CL.REGISTRATIONSTAB, driver);
		Thread.sleep(3000);
		click(CL.LOCATIONSTAB);
		waitforElement(180, CL.ANIMATIONlINK, driver);
		click(CL.PENNSYLVANIASTATE);
		waitforElement(180, CL.REGNUMBER, driver);
		type(CL.REGNUMBER, regnumber);
		type(CL.LOCATIONCODE, locationcode);
		type(CL.LOCATIONNAME, locationname);
		type(CL.STREETADDRESS, streetaddress);
		type(CL.CITYIN, city);
		type(CL.POSTALCODE, Postalcode);
		Thread.sleep(2000);
		scrollDown(driver);
		click(CL.ADDLOCATION);
		waitforElement(180, CL.SELECTTAID, driver);
		click(CL.CHECKBOX);
		Thread.sleep(2000);
		click(CL.SELECTLOCATION);
		Thread.sleep(2000);
		waitforElement(180, CL.SAVETAXPAYER, driver);
		click(CL.SAVETAXPAYER);
		Thread.sleep(5000);
		waitforElement(180, CL.MYENTERRISELINK, driver);
	}

	public void createChildTaxPayer(String ParentTaxPayercode, String TaxPayercode, String TaxPayername,
			String regnumber, String locationcode, String locationname, String streetaddress, String city,
			String Postalcode) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		/*
		 * waitforElement(180, CL.INDIRECTTAXOSERIESTXT, driver); waitforElement(360,
		 * CL.MAINMENU, driver); JavaScriptclick(driver, CL.MAINMENU);
		 * Thread.sleep(2000); waitforElement(180, CL.MYENTERRISELINK, driver);
		 * Thread.sleep(5000); JavaScriptclick(driver, CL.MYENTERRISELINK);
		 * Thread.sleep(2000);
		 */
		JavaScriptclick(driver, CL.TAXAYERSLINK);
		Thread.sleep(2000);
		waitforElement(180, CL.ADDLINK, driver);
		click(CL.ADDLINK);
		waitforElement(180, CL.PARENTTAXPAYERCODE, driver);
		type(CL.PARENTTAXPAYERCODE, ParentTaxPayercode);
		Thread.sleep(5000);
		click(CL.PARENTTAXPAYERSEARCH);
		Thread.sleep(2000);
		waitforElement(180, CL.TAXPAYERSELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.TAXPAYERSELECT);
		waitforElement(180, CL.TAXPAYERCODE, driver);
		type(CL.TAXPAYERCODE, TaxPayercode);
		type(CL.TAXPAYERNAME, TaxPayername);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSTAB, driver);
		click(CL.REGISTRATIONSTAB);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSSEARCH, driver);
		JavaScriptscrolltoView(driver, CL.REGISTRATIONSSEARCH);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.REGISTRATIONSSEARCH);
		waitforElement(180, CL.SELECTALLBTN, driver);
		click(CL.SELECTALLBTN);
		Thread.sleep(5000);
		waitforElement(180, CL.SELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.SELECT);
		waitforElement(180, CL.SAVETAXPAYER, driver);
		click(CL.SAVETAXPAYER);
		Thread.sleep(5000);
		waitforElement(180, CL.MYENTERRISELINK, driver);
	}

	public void createTaxDriver(String ParentTaxPayercode, String Taxabilitycode, String Taxabilitynamee)
			throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		Thread.sleep(4000);
		waitforElement(30, CL.MAINMENU, driver);
		if (VerifyObject(driver, CL.MAINMENU)) {
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.MYENTERRISELINK, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXABILITYDRIVERMANAGEMENT);
		Thread.sleep(2000);
		// JavaScriptclick(driver, CL.TAXABILITYDRIVERSLINK);
		// Thread.sleep(2000);
		if (!CL.TAXABILITYDRIVERSLINK.isDisplayed()) {
			JavaScriptclick(driver, CL.TAXABILITYDRIVERMANAGEMENT);
			Thread.sleep(2000);
			JavaScriptclick(driver, CL.TAXABILITYDRIVERSLINK);
		} else
			JavaScriptclick(driver, CL.TAXABILITYDRIVERSLINK);
		waitforElement(180, CL.ADDLINK, driver);
		click(CL.ADDLINK);
		Thread.sleep(3000);
		waitforElement(180, CL.DRIVERTAXPAYERCODE, driver);
		type(CL.DRIVERTAXPAYERCODE, ParentTaxPayercode);
		Thread.sleep(5000);
		click(CL.DRIVERTAXPAYERSEARCH);
		Thread.sleep(5000);
		waitforElement(180, CL.TAXPAYERSELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.TAXPAYERSELECT);
		Thread.sleep(5000);
		waitforElement(180, CL.TAXABILITYCODE, driver);
		type(CL.TAXABILITYCODE, Taxabilitycode);
		waitforElement(180, CL.TAXABILITYNAME, driver);
		type(CL.TAXABILITYNAME, Taxabilitynamee);
		Thread.sleep(2000);
		waitforElement(180, CL.SAVETAXPAYERDRIVER, driver);
		click(CL.SAVETAXPAYERDRIVER);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
	}

	public void AddDriverToFlexField(String flexFieldName, String ParentTaxPayercode, String Taxabilitycode,
			String Taxabilitynamee) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		Thread.sleep(4000);
		waitforElement(30, CL.MAINMENU, driver);
		if (VerifyObject(driver, CL.MAINMENU)) {
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.MYENTERRISELINK, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXABILITYDRIVERMANAGEMENT);
		Thread.sleep(2000);
		// JavaScriptclick(driver, CL.TAXABILITYDRIVERSLINK);
		// Thread.sleep(2000);
		if (!CL.TAXABILITYDRIVERSLINK.isDisplayed()) {
			JavaScriptclick(driver, CL.TAXABILITYDRIVERMANAGEMENT);
			Thread.sleep(2000);
			JavaScriptclick(driver, CL.TAXABILITYDRIVERSLINK);
		} else
			JavaScriptclick(driver, CL.TAXABILITYDRIVERSLINK);
		waitforElement(180, CL.ADDLINK, driver);
		click(CL.ADDLINK);
		// Thread.sleep(1000);
		click(CL.TAXDRIVERTYPE);
		Thread.sleep(1000);
		click(driver.findElement(By.xpath(
				"//div[@class='css-1ae1wzb-menu select-control__menu']/*[contains(.,'" + flexFieldName + "')]")));
		Thread.sleep(3000);
		waitforElement(180, CL.DRIVERTAXPAYERCODE, driver);
		type(CL.DRIVERTAXPAYERCODE, ParentTaxPayercode);
		Thread.sleep(5000);
		click(CL.DRIVERTAXPAYERSEARCH);
		Thread.sleep(5000);
		waitforElement(180, CL.TAXPAYERSELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.TAXPAYERSELECT);
		Thread.sleep(5000);
		waitforElement(180, CL.TAXABILITYCODE, driver);
		type(CL.TAXABILITYCODE, Taxabilitycode);
		waitforElement(180, CL.TAXABILITYNAME, driver);
		type(CL.TAXABILITYNAME, Taxabilitynamee);
		Thread.sleep(2000);
		waitforElement(180, CL.SAVETAXPAYERDRIVER, driver);
		click(CL.SAVETAXPAYERDRIVER);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
	}

	public void createDriverMapping(String ParentTaxPayername, String taxCatName) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		Thread.sleep(4000);
		waitforElement(30, CL.MAINMENU, driver);
		if (CL.MAINMENU.isDisplayed()) {
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.TAXASETUP, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXASETUP);
		Thread.sleep(2000);
		waitforElement(180, CL.TAXABILITYMAPPING, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXABILITYMAPPING);
		Thread.sleep(2000);
		waitforElement(180, CL.TAXABILITYMAPPINGADD, driver);
		click(CL.TAXABILITYMAPPINGADD);
		waitforElement(180, CL.TAXABILITYENTITIES, driver);
		click(CL.TAXABILITYENTITIES);
		Thread.sleep(2000);
		// click(CL.DRIVERTAXPAYERSEARCH);
		// Thread.sleep(2000);
		// waitforElement(180, CL.TAXABILITYTAXPAYER, driver);
		// type(CL.TAXABILITYTAXPAYER, ParentTaxPayername);
		// Thread.sleep(2000);
		click(CL.TAXABILITYSEARCH);
		waitforElement(180, CL.TAXABILITYADD, driver);
		JavaScriptclick(driver, CL.TAXABILITYADD);
		Thread.sleep(5000);
		waitforElement(180, CL.TAXABILITYSELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.TAXABILITYSELECT);
		Thread.sleep(2000);
		waitforElement(180, CL.TAXABILITYCATEGORY, driver);
		click(CL.TAXABILITYCATEGORY);
		waitforElement(180, CL.TAXABILITYCATEGORYNAME, driver);
		type(CL.TAXABILITYCATEGORYNAME, taxCatName);
		CL.TAXABILITYCATEGORYNAME.sendKeys(Keys.ENTER);
		// click(driver.findElement(By.xpath("//div/span[contains(.,'"+taxCatName+"')]")));
		/*
		 * waitforElement(180, CL.GENTAXABILITYCATEGORY, driver);
		 * JavaScriptclick(driver, CL.GENTAXABILITYCATEGORY); Thread.sleep(2000);
		 * JavaScriptclick(driver, CL.TPPCATEGORY); Thread.sleep(2000);
		 */
		waitforElement(180, CL.TPPCATEGORYSELECT, driver);
		JavaScriptclick(driver, CL.TPPCATEGORYSELECT);
		waitforElement(180, CL.TAXABILITYMAPPINGSAVE, driver);
		click(CL.TAXABILITYMAPPINGSAVE);
		Thread.sleep(5000);
		waitforElement(180, CL.TAXABILITYMAPPING, driver);
		Thread.sleep(2000);
		// JavaScriptclick(driver, CL.TAXABILITYMAPPING);
		waitforElement(180, CL.TAXMAPPINGSEARCH, driver);
		click(CL.TAXMAPPINGSEARCH);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.MAINMENU);
	}

	public void createCustomers(String Customertype, String customercode, String csutomername, String TaxPayername)
			throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}

		if (VerifyObject(driver, CL.MAINMENU)) {
			waitforElement(180, CL.MAINMENU, driver);
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.MYENTERRISELINK, driver);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		waitforElement(180, CL.CUSTOMERSLINK, driver);
		JavaScriptclick(driver, CL.CUSTOMERSLINK);
		Thread.sleep(2000);
		waitforElement(180, CL.ADDUSTOMERS, driver);
		Thread.sleep(2000);
		click(CL.ADDUSTOMERS);
		waitforElement(180, CL.ADDCUSTOMERSTEXT, driver);
		waitforElement(180, CL.CUSTOMERTYE, driver);
		Thread.sleep(5000);
		type(CL.CUSTOMERCODE, customercode);
		type(CL.CUSTOMERNAME, csutomername);
		click(CL.CUSTOMERTAXPAYERSERCH);
		waitforElement(180, CL.SELECTTAXPAYERTEXT, driver);
		waitforElement(180, CL.TAXPAYERNAMEIN, driver);
		Thread.sleep(2000);
		type(CL.TAXPAYERNAMEIN, TaxPayername);
		waitforElement(180, CL.TAXPAYERNAMEFILTER, driver);
		click(CL.TAXPAYERNAMEFILTER);
		Thread.sleep(2000);
		waitforElement(180, CL.TAXPAYERNAMECHKBOX, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXPAYERNAMECHKBOX);
		waitforElement(180, CL.SELECTLOCATION, driver);
		click(CL.SELECTLOCATION);
		Thread.sleep(5000);
		waitforElement(180, CL.REGISTRATIONSTAB, driver);
		click(CL.REGISTRATIONSTAB);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSSEARCH, driver);
		JavaScriptscrolltoView(driver, CL.REGISTRATIONSSEARCH);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.REGISTRATIONSSEARCH);
		waitforElement(180, CL.SELECTALLBTN, driver);
		click(CL.SELECTALLBTN);
		Thread.sleep(5000);
		waitforElement(180, CL.SELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.SELECT);
		Thread.sleep(2000);
		waitforElement(180, CL.SELECTCUSTOMER, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.SELECTCUSTOMER);
		Thread.sleep(5000);
		// waitforElement(360, CL.MYENTERRISELINK, driver);
	}

	public void createCertificates(String Certificatetype, String certificateTaxResult, String certificateType,
			String Customername, String transactionType) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);

		waitforElement(360, CL.CERTIFICATESLINK, driver);
		// JavaScriptclick(driver, CL.MYENTERRISELINK);
		// waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// Thread.sleep(5000);
		JavaScriptclick(driver, CL.CERTIFICATESLINK);
		Thread.sleep(2000);
		waitforElement(180, CL.ADDCERTIFICATES, driver);
		Thread.sleep(1000);
		click(CL.ADDCERTIFICATES);
		waitforElement(180, CL.ADDCERTIFICATETEXT, driver);
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//div[@id='certificateClass_select']/div/div/div")).sendKeys(Certificatetype);
		// Thread.sleep(2000);

		// waitforElement(180, CL.ADDCERTIFICATETEXT, driver);
		// driver.findElement(By.xpath("//div[@id='certificateTaxResult_select']")).sendKeys(Certificatetype);
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//div[@id='certificateType_select']/div/div/div")).sendKeys(Certificatetype);
		// Thread.sleep(5000);
		click(CL.CUSTOMERCODEIN);
		waitforElement(180, CL.CERTIFICATECUSTOMERSEARCH, driver);
		JavaScriptclick(driver, CL.CERTIFICATECUSTOMERSEARCH);
		waitforElement(180, CL.SELECTCUSTOMERTEXT, driver);
		waitforElement(180, CL.CUSTOMERNAMEIN, driver);
		Thread.sleep(2000);
		type(CL.CUSTOMERNAMEIN, Customername);
		waitforElement(180, CL.TAXPAYERNAMEFILTER, driver);
		click(CL.TAXPAYERNAMEFILTER);
		Thread.sleep(2000);
		waitforElement(180, CL.CUSTOMERCODECHKBOX, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.CUSTOMERCODECHKBOX);
		waitforElement(180, CL.SELECTLOCATION, driver);
		click(CL.SELECTLOCATION);
		Thread.sleep(5000);
		// waitforElement(180, CL.ADDCERTIFICATETEXT, driver);
		// Thread.sleep(2000);
		click(CL.CERTIFICATEENDDATE);
		CL.CERTIFICATEENDDATE.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		// sendkeysTab(CL.CERTTRANSACTIONTYPE);
		type(CL.CERTTRANSACTIONTYPE, "S");
		CL.CERTTRANSACTIONTYPE.sendKeys(Keys.TAB);
		// waitforElement(180, CL.CERTTRANSACTIONTYPE, driver);
		// driver.findElement(By.xpath("//div[@id='transactionTypes_select']")).sendKeys(transactionType);

		// type(CL.CERTTRANSACTIONTYPE,transactionType);
		Thread.sleep(2000);
		waitforElement(180, CL.JURISDICTIONSTAB, driver);
		click(CL.JURISDICTIONSTAB);
		Thread.sleep(2000);
		waitforElement(180, CL.JURISDICTIONSSEARCH, driver);
		JavaScriptscrolltoView(driver, CL.JURISDICTIONSSEARCH);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.JURISDICTIONSSEARCH);
		waitforElement(180, CL.SELECTALLBTN, driver);
		click(CL.SELECTALLBTN);
		Thread.sleep(5000);
		waitforElement(180, CL.SELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.SELECT);
		Thread.sleep(2000);
		waitforElement(180, CL.SAVECERTIFICATE, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.SAVECERTIFICATE);
		Thread.sleep(5000);
		click(CL.CERTIFICATESEARCH);
		Thread.sleep(2000);
		waitforElement(180, CL.MYENTERRISELINK, driver);
	}

	// method to login to Cloud Portal and access IndirectTax O Series
	public void CloudOSeriesTab() throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		Thread.sleep(10000);
		waitforText(30, CL.INDIRECTTAXOSERIESCLOUD, driver);
		assertTrue(VerifyObject(driver, CL.INDIRECTTAXOSERIESCLOUD));
		click(CL.INDIRECTTAXOSERIESCLOUD);
		Thread.sleep(25000);
		waitforText(180, CL.INDIRECTTAXOSERIESTXT, driver);
		assertTrue(CL.INDIRECTTAXOSERIESTXT.isDisplayed());
		waitforElement(180, CL.MAINMENU, driver);
		assertTrue(CL.MAINMENU.isDisplayed());
		System.out.println("Success- Logged into calc config MFE- Main Menu is displayed");
	}

	public void VerifySystemAccessPages_() throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESCLOUD)) {
			waitforText(180, CL.INDIRECTTAXOSERIESCLOUD, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESCLOUD);
			Thread.sleep(80000);
		}
		waitforText(180, CL.SETTINGSICON, driver);
		JavaScriptclick(driver, CL.SETTINGSICON);
//		Thread.sleep(5000);
		waitforText(180, CL.USER_MANAGEMENT, driver);
		JavaScriptclick(driver, CL.SETTINGSICON);
		Thread.sleep(60000);

		System.out.println("Clicked ok");

	}

	public void createDriverMultiMapping(String ParentTaxPayername, int n) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		Thread.sleep(4000);
		waitforElement(30, CL.MAINMENU, driver);
		if (CL.MAINMENU.isDisplayed()) {
			JavaScriptclick(driver, CL.MAINMENU);
		}
		Thread.sleep(2000);
		waitforElement(180, CL.TAXASETUP, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXASETUP);
		Thread.sleep(2000);
		waitforElement(180, CL.TAXABILITYMAPPING, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXABILITYMAPPING);
		Thread.sleep(2000);
		waitforElement(180, CL.TAXABILITYMAPPINGADD, driver);
		click(CL.TAXABILITYMAPPINGADD);
		waitforElement(180, CL.TAXABILITYENTITIES, driver);
		click(CL.TAXABILITYENTITIES);
		Thread.sleep(2000);
		// click(CL.DRIVERTAXPAYERSEARCH);
		// Thread.sleep(2000);
		// waitforElement(180, CL.TAXABILITYTAXPAYER, driver);
		// type(CL.TAXABILITYTAXPAYER, ParentTaxPayername);
		// Thread.sleep(2000);
		click(CL.TAXABILITYSEARCH);
		for (int i = 1; i <= n; i++) {
			waitforElement(180, CL.TAXABILITYADD, driver);
			JavaScriptclick(driver, CL.TAXABILITYADD);
			Thread.sleep(5000);
		}
		waitforElement(180, CL.TAXABILITYSELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.TAXABILITYSELECT);
		Thread.sleep(2000);
		waitforElement(180, CL.TAXABILITYCATEGORY, driver);
		click(CL.TAXABILITYCATEGORY);
		waitforElement(180, CL.GENTAXABILITYCATEGORY, driver);
		JavaScriptclick(driver, CL.GENTAXABILITYCATEGORY);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TPPCATEGORY);
		Thread.sleep(2000);
		waitforElement(180, CL.TPPCATEGORYSELECT, driver);
		JavaScriptclick(driver, CL.TPPCATEGORYSELECT);
		waitforElement(180, CL.TAXABILITYMAPPINGSAVE, driver);
		click(CL.TAXABILITYMAPPINGSAVE);
		Thread.sleep(5000);
		waitforElement(180, CL.TAXABILITYMAPPING, driver);
		Thread.sleep(2000);
		// JavaScriptclick(driver, CL.TAXABILITYMAPPING);
		waitforElement(180, CL.TAXMAPPINGSEARCH, driver);
		click(CL.TAXMAPPINGSEARCH);
		// Thread.sleep(2000);
		// JavaScriptclick(driver, CL.MAINMENU);
	}

	public void UploadTaxabilityDrivers(String Taxpayer, String TPcode, String Filename, String fileExt)
			throws Exception {
		File directory = new File(".//");
		String path = null;
		String Successfile;
		WebElement Success, Errorfile;
		PortalFunctions DownloadFiles = PageFactory.initElements(driver, PortalFunctions.class);
		String Driverpath = xUploadAdjustmentFile("TaxabilityDrivers");
		String AutoITpath = xAutoIT();
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		Thread.sleep(5000);
		waitforElement(30, CL.INDIRECTTAXOSERIESLINK, driver);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		waitforElement(30, CL.MAINMENU, driver);
		if (CL.MAINMENU.isDisplayed()) {
			JavaScriptclick(driver, CL.MAINMENU);
		}
		Thread.sleep(4000);
		waitforElement(180, CL.MYENTERRISELINK, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXABILITYDRIVERMANAGEMENT);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXABILITYDRIVERSLINK);
		Thread.sleep(2000);
		waitforElement(180, CL.UPLOADDRIVERS, driver);
		JavaScriptclick(driver, CL.UPLOADDRIVERS);
		waitforElement(180, CL.DOWNLOADTEMPLATE, driver);
		JavaScriptclick(driver, CL.DOWNLOADTEMPLATE);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "TaxabilityDrivers";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			new File(LatestDownloadedFile("TaxabilityDrivers", true));

		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			new File(LatestDownloadedFile("TaxabilityDrivers", false));
			Thread.sleep(20000);
		}

		click(CL.SELECTTAXPAYER);
		waitforElement(30, "//span[@class='vtx-text-gray-7']", driver);
		driver.findElement(By.xpath("//span[@class='vtx-text-gray-7']"));
		driver.findElement(By.xpath("//div/span[contains(.,'" + TPcode + "')]")).click();
		// JavaScriptclick(driver, taxpayercode);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.BROWSETAXABILITYDRIVERS);
		Thread.sleep(5000);
		Browser(driver, Browser, AutoITpath, Driverpath, Filename, fileExt);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.UPLOADDRIVERSBTN);
		Thread.sleep(60000);
		// Successfile =
		// "//div/h3/span[contains(.,'"+TPcode+"')]/../../../div/div/div/div/div/div/div/div/span[contains(.,'Download
		// success file')]";
		Successfile = "//span[contains(.,'Download success file')]";
		Success = driver.findElement(By.xpath(Successfile));
		waitforElement(180, Success, driver);
		JavaScriptclick(driver, Success);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "TaxabilityDrivers";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(5000);
			new File(DownloadFiles.LatestDownloadedFile("TaxabilityDrivers", true));
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			LatestDownloadedFile("TaxabilityDrivers", false);
			Thread.sleep(5000);
		}

		Errorfile = driver.findElement(By.xpath("//h3[contains(.,'1')]/../span[contains(.,'Download error file')]"));
		JavaScriptclick(driver, Errorfile);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "TaxabilityDrivers";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(5000);
			new File(DownloadFiles.LatestDownloadedFile("TaxabilityDrivers", true));
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			LatestDownloadedFile("TaxabilityDrivers", false);
			Thread.sleep(5000);
		}

	}

	public void CreateRegistrationsReport(String taxpayerCode) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);

		waitforElement(30, CL.INDIRECTTAXOSERIESLINK, driver);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		waitforElement(30, CL.MAINMENU, driver);
		if (CL.MAINMENU.isDisplayed()) {
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(30, CL.REPORTS, driver);
		JavaScriptclick(driver, CL.REPORTS);
		waitforElement(30, CL.CONFIGURATIONREPORTS, driver);
		JavaScriptclick(driver, CL.CONFIGURATIONREPORTS);
		boolean isDropdownThere = false;
		int timeLimit = 0;
		// Checks to see if the dropdown is there for 30 seconds, otherwise it will fail
		// on the next line
		while (isDropdownThere == false && timeLimit < 15) {
			isDropdownThere = VerifyObject(driver, "//span[@title='All Configuration Reports']");
			System.out.println(isDropdownThere + " " + timeLimit);
			timeLimit++;
			Thread.sleep(1000);
		}
		driver.findElement(By.xpath("//span[@title='All Configuration Reports']")).click();
		Thread.sleep(2000);
		// Registrations in the dropdown
		driver.findElement(By.xpath(
				"//*[@id=\"single-spa-application:vtx-ui-mf-calc-config\"]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[5]/div"))
				.click();
		Thread.sleep(2000);
		/*
		 * driver.findElement(By.xpath("//input[@id='taxpayer']")).click();
		 * Thread.sleep(2000); driver.findElement(By.xpath("//span[contains(.,'" +
		 * taxpayerCode + "')]")).click();
		 */
		click(CL.SELECTTAXPAYER);
		waitforElement(30, "//span[@class='vtx-text-gray-7']", driver);
		driver.findElement(By.xpath("//span[@class='vtx-text-gray-7']"));
		driver.findElement(By.xpath("//div/span[contains(.,'" + taxpayerCode + "')]")).click();
		// modified to select the taxpayer.
		// JavaScriptclick(driver, taxpayercode);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.GENERATEREPORT);
		boolean isGenerating = VerifyObject(driver, "//span[contains(.,'Generating')]");
		int timeLimit2 = 0;
		// Checks to see if generating is there for 2 minutes, otherwise it will fail on
		// the next line
		while (isGenerating == true && timeLimit2 < 24) {
			isGenerating = VerifyObject(driver, "//span[contains(.,'Generating')]");
			System.out.println(isGenerating);
			timeLimit2++;
			Thread.sleep(5000);
		}
		boolean isDownloadThere = VerifyObject(driver, "//a[contains(.,'Download')]");
		assertTrue(!isGenerating);
		assertTrue(isDownloadThere);
		JavaScriptclick(driver, CL.REGREPORTDOWNLOAD);
	}

	public void UploadTaxabilityMapping(String Taxpayer, String TPcode, String Filename, String fileExt)
			throws Exception {
		File directory = new File(".//");
		String path = null;
		String Successfile;
		WebElement Success, Errorfile;
		PortalFunctions DownloadFiles = PageFactory.initElements(driver, PortalFunctions.class);
		String Driverpath = xUploadAdjustmentFile("TaxabilityDrivers");
		String AutoITpath = xAutoIT();
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		Thread.sleep(5000);
		waitforElement(30, CL.INDIRECTTAXOSERIESLINK, driver);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		waitforElement(30, CL.MAINMENU, driver);
		if (CL.MAINMENU.isDisplayed()) {
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.TAXSETUP, driver);
		JavaScriptclick(driver, CL.TAXSETUP);
		waitforElement(180, CL.TAXABILITYMAPPING, driver);
		JavaScriptclick(driver, CL.TAXABILITYMAPPING);
		waitforElement(180, CL.UPLOADDRIVERS, driver);
		JavaScriptclick(driver, CL.UPLOADDRIVERS);
		waitforElement(180, CL.DOWNLOADTEMPLATE, driver);
		JavaScriptclick(driver, CL.DOWNLOADTEMPLATE);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "TaxabilityMappings";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			new File(LatestDownloadedFile("TaxabilityMappings", true));

		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			new File(LatestDownloadedFile("TaxabilityMappings", false));
			Thread.sleep(20000);
		}
		click(CL.SELECTTAXPAYER);
		waitforElement(30, "//span[@class='vtx-text-gray-7']", driver);
		driver.findElement(By.xpath("//span[@class='vtx-text-gray-7']"));
		driver.findElement(By.xpath("//div/span[contains(.,'" + TPcode + "')]")).click();
		// JavaScriptclick(driver, taxpayercode);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.BROWSETAXABILITYDRIVERS);
		Thread.sleep(5000);
		Browser(driver, Browser, AutoITpath, Driverpath, Filename, fileExt);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.UPLOADMAPPINGSBTN);
		Thread.sleep(5000);
		// Successfile =
		// "//div/h3/span[contains(.,'"+TPcode+"')]/../../../div/div/div/div/div/div/div/div/span[contains(.,'Download
		// success file')]";
		Successfile = "//span[contains(.,'Download success file')]";
		Success = driver.findElement(By.xpath(Successfile));
		waitforElement(180, Success, driver);
		JavaScriptclick(driver, Success);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "TaxabilityMappings";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(5000);
			new File(DownloadFiles.LatestDownloadedFile("TaxabilityMappings", true));
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			LatestDownloadedFile("TaxabilityMappings", false);
			Thread.sleep(5000);
		}

		Errorfile = driver.findElement(By.xpath("//h3[contains(.,'1')]/../span[contains(.,'Download error file')]"));
		JavaScriptclick(driver, Errorfile);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "TaxabilityMappings";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(5000);
			new File(DownloadFiles.LatestDownloadedFile("TaxabilityMappings", true));
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			LatestDownloadedFile("TaxabilityMappings", false);
			Thread.sleep(5000);
		}

	}

	public void EditFlexField(String codeToEdit, String shortName, String longName) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		Thread.sleep(4000);
		waitforElement(30, CL.MAINMENU, driver);
		if (CL.MAINMENU.isDisplayed()) {
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.TAXASETUP, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXASETUP);
		Thread.sleep(2000);
		waitforElement(180, CL.FLEXFIELDS, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.FLEXFIELDS);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"flexibleFields_table\"]/div/div/div[1]/div/div[3]/div[3]/div["
				+ codeToEdit + "]/div/div/div/div/button")).click();
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.FLEXFIELDEDIT);
		clear(CL.FLEXFIELDSHORTNAME);
		type(CL.FLEXFIELDSHORTNAME, shortName);
		Thread.sleep(1000);
		clear(CL.FLEXFIELDLONGNAME);
		type(CL.FLEXFIELDLONGNAME, longName);
		JavaScriptclick(driver, CL.FLEXFIELDSAVE);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.MAINMENU);
	}

	public void SingleTransaction_calculatetaxes(String FinancialEvent, String Taxpayercode, String CustomerCode,
			String Country, String State, String Zipcode, String Price, String TaxAmount) throws Exception {

		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		waitforElement(30, CL.MAINMENU, driver);
		if (CL.MAINMENU.isDisplayed()) {
			waitforElement(180, CL.MAINMENU, driver);
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.TOOLS, driver);
		JavaScriptclick(driver, CL.TOOLS);
		waitforElement(180, CL.TRANSACTIONTESTER, driver);
		JavaScriptclick(driver, CL.TRANSACTIONTESTER);
		waitforElement(180, CL.SINGLETRANSACTION, driver);
		JavaScriptclick(driver, CL.SINGLETRANSACTION);
		waitforElement(180, CL.FIEVENTSELECT, driver);
		click(CL.FIEVENTSELECT);
		waitforElement(180, CL.INVOICE, driver);
		click(CL.INVOICE);
		Thread.sleep(3000);
		type(CL.CMPTAXPAYERCODE, Taxpayercode);
		if (CustomerCode != "") {
			waitforElement(180, CL.CMPCUSTOMERCODE, driver);
			type(CL.CMPCUSTOMERCODE, CustomerCode);
		}

		Thread.sleep(2000);
		// click(CL.COUNTRY);
		// type(CL.COUNTRY, Country);
		// Thread.sleep(3000);
		click(CL.STATE);
		type(CL.STATE, State);
		Thread.sleep(2000);
		click(CL.CMPPOSTALCODE);
		type(CL.CMPPOSTALCODE, Zipcode);
		/*
		 * driver.findElement(By.xpath("//span[contains(.,'Addresses')]")).click();
		 * //*[@id="inheritedPropValues.destination.taxAreaId_input"]
		 * //waitforElement(180, driver.findElement(By.xpath(
		 * "//*[@id=\"inheritedPropValues.destination.taxAreaId_input\"]")), driver);
		 * type(driver.findElement(By.xpath(
		 * "//*[@id=\"inheritedPropValues.destination.taxAreaId_input\"]")),
		 * "800400000"); Thread.sleep(2000); type(driver.findElement(By.xpath(
		 * "//input[@id='inheritedPropValues.administrativeOrigin.taxAreaId_input']")),
		 * "802500000");
		 */
		waitforElement(180, CL.EXTENDEDPRICE, driver);
		click(CL.EXTENDEDPRICE);
		JavaScripttype(driver, CL.EXTENDEDPRICEINPUT, Price);
		Thread.sleep(2000);
		click(CL.CALCULATEBTN);
		Thread.sleep(5000);
		waitforElement(180, CL.SAVETOFILE, driver);
		if (!VerifyObject(driver, CL.POSTTOJOURNAL)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		String taxamount = driver
				.findElement(By.xpath("//table/thead/tr/th[contains(.,'Tax')]/../../../tbody/tr/td[7]")).getText();
		assertTrue(taxamount.equalsIgnoreCase(TaxAmount));

	}

	public void CreateCustomerExemptionsReport(String taxpayerCode, String customerName, String state, String startDate,
			String endDate) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		File directory = new File(".//");
		String path = null;

		System.out.println("customer exemption report");
		Thread.sleep(10000);
		// System.out.println("Hello");
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		/*
		 * waitforElement(30, CL.MAINMENU, driver); if (VerifyObject(driver,
		 * CL.MAINMENU)) { waitforElement(180, CL.MAINMENU, driver);
		 * JavaScriptclick(driver, CL.MAINMENU); }
		 */
		waitforElement(30, CL.REPORTS, driver);
		JavaScriptclick(driver, CL.REPORTS);
		waitforElement(30, CL.CONFIGURATIONREPORTS, driver);
		JavaScriptclick(driver, CL.CONFIGURATIONREPORTS);
		boolean isDropdownThere = false;
		int timeLimit = 0;
		// Checks to see if the dropdown is there for 15 seconds, otherwise it will fail
		// on the next line
		while (isDropdownThere == false && timeLimit < 15) {
			isDropdownThere = VerifyObject(driver, "//span[@title='All Configuration Reports']");
			System.out.println(isDropdownThere + " " + timeLimit);
			timeLimit++;
			Thread.sleep(1000);
		}
		driver.findElement(By.xpath("//span[@title='All Configuration Reports']")).click();
		Thread.sleep(2000);
		// Customer Exemptions in the dropdown
		click(CL.CUSTOMEREXEMPTIONREPORT);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='taxpayer']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div/span[contains(.,'" + taxpayerCode + "')]")).click();
		if (customerName != "") {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='customer']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@title='" + customerName + "']")).click();
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='jurisdiction']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@title='" + state + "']")).click();
		Thread.sleep(2000);
		click(CL.STARTDATE);
		type(CL.STARTDATE, startDate);
		CL.STARTDATE.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		click(CL.ENDDATE);
		type(CL.ENDDATE, endDate);
		CL.ENDDATE.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		/*
		 * if (startDate != "" && endDate != "") {
		 * driver.findElement(By.xpath("//input[@id='dayRange']")).click();
		 * Thread.sleep(2000); driver.findElement(By.xpath("//td[@title='" + startDate +
		 * "']")).click(); Thread.sleep(2000);
		 * driver.findElement(By.xpath("//input[@placeholder='End date']")).click();
		 * Thread.sleep(2000); driver.findElement(By.xpath("//td[@title='" + endDate +
		 * "']")).click(); }
		 */
		// Thread.sleep(10000);
		JavaScriptclick(driver, CL.GENERATEREPORT);
		boolean isGenerating = VerifyObject(driver, "//span[contains(.,'Generating')]");
		int timeLimit2 = 0;
		// Checks to see if generating is there for 2 minutes, otherwise it will fail on
		// the next line
		while (isGenerating == true && timeLimit2 < 24) {
			isGenerating = VerifyObject(driver, "//span[contains(.,'Generating')]");
			System.out.println(isGenerating);
			timeLimit2++;
			Thread.sleep(5000);
		}
		waitforElement(240, "//table/tbody/tr/td/span/span[contains(.,'" + taxpayerCode
				+ "')]/../../../td/span/a[contains(.,'Download')]", driver);
		WebElement download = driver.findElement(By.xpath("//table/tbody/tr/td/span/span[contains(.,'" + taxpayerCode
				+ "')]/../../../td/span/a[contains(.,'Download')]"));
		click(download);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
					+ "CustomerExemptionsReport";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			new File(LatestDownloadedFile("CustomerExemptionsReport", true));

		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			new File(LatestDownloadedFile("CustomerExemptionsReport", false));
			Thread.sleep(20000);
		}

		// customerReport.length();
	}

	public void TaxabilityMatrixReport(String States, String AllStates, String currentDate, String Taxpayercode)
			throws Exception {
		File directory = new File(".//");
		String path = null;
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		Thread.sleep(5000);
		waitforElement(30, CL.INDIRECTTAXOSERIESLINK, driver);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		waitforElement(30, CL.MAINMENU, driver);
		if (CL.MAINMENU.isDisplayed()) {
			waitforElement(180, CL.MAINMENU, driver);
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.REPORTS, driver);
		JavaScriptclick(driver, CL.REPORTS);
		waitforElement(180, CL.TAXABILITYMATRIX, driver);
		click(CL.TAXABILITYMATRIX);
		waitforElement(180, CL.SELECTSTATE, driver);
		click(CL.SELECTSTATE);
		Thread.sleep(2000);
		if (CL.CARETDOWN.isDisplayed()) {
			JavaScriptclick(driver, CL.CARETDOWN);
		}
		Thread.sleep(2000);
		String[] statevalue = States.split(",");
		if (!States.isEmpty()) {
			for (int i = 0; i < statevalue.length; i++) {
				type(CL.SELECTSTATEINPUT, statevalue[i]);
				Thread.sleep(3000);
				WebElement state = driver.findElement(
						By.xpath("//span[@title='" + statevalue[i] + "']/../span[@class='ant-select-tree-checkbox']"));
				JavaScriptclick(driver, state);
				Thread.sleep(2000);
				// CL.SELECTSTATEINPUT.sendKeys(Keys.ENTER);
				// WebElement state = driver.findElement(By.xpath("//div[@class='ant-select-item
				// ant-select-item-option']/div[contains(.,'"+statevalue[i]+"')]"));
				// click(state);
			}
		}
		if (AllStates.equalsIgnoreCase("Yes")) {
			click(CL.ALLSTATES);
		}
		Thread.sleep(3000);
		type(CL.SELECTDATE, currentDate);
		CL.SELECTDATE.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		click(CL.SELECTTAXPAYERTMR);
		waitforElement(30, "//span[@class='vtx-text-gray-7']", driver);
		driver.findElement(By.xpath("//span[@class='vtx-text-gray-7']"));
		driver.findElement(By.xpath("//div/span[contains(.,'" + Taxpayercode + "')]")).click();
		// JavaScriptclick(driver, taxpayercode);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.GENERATETMREPORT);
		waitforElement(360, "//table/tbody/tr/td/span/span[contains(.,'" + Taxpayercode
				+ "')]/../../../td/span/a[contains(.,'Download')]", driver);
		waitforElement(240, "//table/tbody/tr/td/span/span[contains(.,'" + Taxpayercode
				+ "')]/../../../td/span/a[contains(.,'Download')]", driver);
		WebElement download = driver.findElement(By.xpath("//table/tbody/tr/td/span/span[contains(.,'" + Taxpayercode
				+ "')]/../../../td/span/a[contains(.,'Download')]"));
		click(download);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
					+ "TaxabilityMatrixReport";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			new File(LatestDownloadedFile("TaxabilityMatrixReport", true));

		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			new File(LatestDownloadedFile("TaxabilityMatrixReport", false));
			Thread.sleep(20000);
		}
	}

	public void CreateNonUnionEUOSSTaxPayer(String TaxPayercode, String TaxPayername, String regnumber,
			String memberState, String filingCurrency, String jurisdictions) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		Thread.sleep(10000);
		if (VerifyObject(driver, CL.MAINMENU)) {
			waitforElement(180, CL.MAINMENU, driver);
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.MYENTERRISELINK, driver);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXAYERSLINK);
		Thread.sleep(2000);
		click(CL.ADDLINK);
		waitforElement(180, CL.TAXPAYERCODE, driver);
		type(CL.TAXPAYERCODE, TaxPayercode);
		type(CL.TAXPAYERNAME, TaxPayername);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSTAB, driver);
		click(CL.REGISTRATIONSTAB);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSSEARCH, driver);
		JavaScriptscrolltoView(driver, CL.REGISTRATIONSSEARCH);
		JavaScriptclick(driver, CL.REGISTRATIONSSEARCH);
		Thread.sleep(5000);
		String[] jurisdictionList = jurisdictions.split(",");
		for (int x = 0; x < jurisdictionList.length; x++) {
			driver.findElement(By.xpath("//label/span[contains(.,'" + jurisdictionList[x] + "')]")).click();
			System.out.println(jurisdictionList[x]);
		}
		waitforElement(180, CL.SELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.SELECT);
		driver.findElement(By.xpath("//div/span[contains(.,'Non-Union')]")).click();
		click(CL.NONUNIONOSSCHKBOX);
		waitforElement(180, CL.NONUNIONREGNUMBER, driver);
		type(CL.NONUNIONREGNUMBER, regnumber);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"euOneStopShopObjects[1].memberStateId_select\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@title='" + memberState + "']")).click();
		Thread.sleep(1000);
		WebElement filingCurrencyDropdown = driver
				.findElement(By.xpath("//*[@id=\"euOneStopShopObjects[1].filingCurrencyCode_select\"]"));
		filingCurrencyDropdown.click();
		Thread.sleep(1000);
		// Need to add a way to scroll down for filing currency
		driver.findElement(By.xpath("//div[@title='" + filingCurrency + "']")).click();
		Thread.sleep(1000);
		click(CL.SAVETAXPAYER);
		Thread.sleep(5000);
	}

	public void CreateUnionEUOSSTaxPayer(String TaxPayercode, String TaxPayername, String regnumber, String memberState,
			String filingCurrency, String jurisdictions) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		Thread.sleep(10000);
		if (VerifyObject(driver, CL.MAINMENU)) {
			waitforElement(180, CL.MAINMENU, driver);
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.MYENTERRISELINK, driver);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXAYERSLINK);
		Thread.sleep(2000);
		click(CL.ADDLINK);
		waitforElement(180, CL.TAXPAYERCODE, driver);
		type(CL.TAXPAYERCODE, TaxPayercode);
		type(CL.TAXPAYERNAME, TaxPayername);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSTAB, driver);
		click(CL.REGISTRATIONSTAB);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSSEARCH, driver);
		JavaScriptscrolltoView(driver, CL.REGISTRATIONSSEARCH);
		JavaScriptclick(driver, CL.REGISTRATIONSSEARCH);
		Thread.sleep(5000);
		String[] jurisdictionList = jurisdictions.split(",");
		for (int x = 0; x < jurisdictionList.length; x++) {
			driver.findElement(By.xpath("//label/span[contains(.,'" + jurisdictionList[x] + "')]")).click();
			System.out.println(jurisdictionList[x]);
		}
		waitforElement(180, CL.SELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.SELECT);
		// driver.findElement(By.xpath("//span[contains(.,'FRANCE (All
		// Jurisdictions)')]")).click();
		// Thread.sleep(1000);
		/*
		 * type(driver.findElement(By.xpath(
		 * "//div/input[@id='registrations[2].registrationCodeId_input']")), regnumber);
		 * Thread.sleep(1000); //driver.findElement(By.xpath(
		 * "//div[@id='registrations[2].registrationType_select']")).click();
		 * //Thread.sleep(1000);
		 * //driver.findElement(By.xpath("//div[contains(.,'VAT Registration')]")).click
		 * ();
		 * 
		 * //type(driver.findElement(By.xpath(
		 * "//div[@id='registrations[2].registrationType_select']/div")),
		 * registrationType); //Thread.sleep(1000); //driver.findElement(By.xpath(
		 * "//div[@id='registrations[2].registrationType_select]")).sendKeys(Keys.ENTER)
		 * ; //Thread.sleep(1000);
		 * //*[@id="registrations[2].physicalPresenceIndicator_checkbox"]
		 * JavaScriptscrolltoView(driver, driver.findElement(By.xpath(
		 * "//*[@id=\"registrations[2].physicalPresenceIndicator_checkbox\"]")));
		 * 
		 * driver.findElement(By.xpath(
		 * "//*[@id=\"registrations[2].physicalPresenceIndicator_checkbox\"]")).click();
		 * driver.findElement(By.xpath("//div/span[contains(.,'Union')]")).click();
		 */
		driver.findElement(By.xpath("//div/span[contains(.,'Union')]")).click();
		click(CL.UNIONOSSCHKBOX);
		waitforElement(180, CL.UNIONREGNUMBER, driver);
		type(CL.UNIONREGNUMBER, regnumber);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id=\"euOneStopShopObjects[0].memberStateId_select\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@title='" + memberState + "']")).click();
		Thread.sleep(1000);
		WebElement filingCurrencyDropdown = driver
				.findElement(By.xpath("//*[@id=\"euOneStopShopObjects[0].filingCurrencyCode_select\"]"));
		filingCurrencyDropdown.click();
		Thread.sleep(1000);
		// Need to add a way to scroll down for filing currency
		driver.findElement(By.xpath("//div[@title='" + filingCurrency + "']")).click();
		Thread.sleep(1000);

		click(CL.SAVETAXPAYER);
		Thread.sleep(5000);
	}

	public void CreateImportOSSTaxPayer(String TaxPayercode, String TaxPayername, String regnumber, String memberState,
			String filingCurrency, String jurisdictions) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		Thread.sleep(10000);
		if (VerifyObject(driver, CL.MAINMENU)) {
			waitforElement(180, CL.MAINMENU, driver);
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.MYENTERRISELINK, driver);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXAYERSLINK);
		Thread.sleep(2000);
		click(CL.ADDLINK);
		waitforElement(180, CL.TAXPAYERCODE, driver);
		type(CL.TAXPAYERCODE, TaxPayercode);
		type(CL.TAXPAYERNAME, TaxPayername);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSTAB, driver);
		click(CL.REGISTRATIONSTAB);
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSSEARCH, driver);
		JavaScriptscrolltoView(driver, CL.REGISTRATIONSSEARCH);
		JavaScriptclick(driver, CL.REGISTRATIONSSEARCH);
		Thread.sleep(5000);
		String[] jurisdictionList = jurisdictions.split(",");
		for (int x = 0; x < jurisdictionList.length; x++) {
			driver.findElement(By.xpath("//label/span[contains(.,'" + jurisdictionList[x] + "')]")).click();
			System.out.println(jurisdictionList[x]);
		}
		Thread.sleep(2000);
		waitforElement(180, CL.SELECT, driver);
		scrollDown(driver);
		JavaScriptclick(driver, CL.SELECT);
		driver.findElement(By.xpath("//div/span[contains(.,'Import')]")).click();
		click(CL.IMPORTOSSCHKBOX);
		waitforElement(180, CL.IMPORTREGNUMBER, driver);
		type(CL.IMPORTREGNUMBER, regnumber);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"euOneStopShopObjects[2].memberStateId_select\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@title='" + memberState + "']")).click();
		Thread.sleep(1000);
		WebElement filingCurrencyDropdown = driver
				.findElement(By.xpath("//*[@id=\"euOneStopShopObjects[2].filingCurrencyCode_select\"]"));
		filingCurrencyDropdown.click();
		Thread.sleep(1000);
		// Need to add a way to scroll down for filing currency
		driver.findElement(By.xpath("//div[@title='" + filingCurrency + "']")).click();
		Thread.sleep(1000);
		click(CL.SAVETAXPAYER);
		Thread.sleep(5000);
	}

	public void EditImportOSSTaxPayer(String TaxPayercode, String TaxPayername, String regnumber, String memberState,
			String filingCurrency, String jurisdictions) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		Thread.sleep(10000);
		if (VerifyObject(driver, CL.MAINMENU)) {
			waitforElement(180, CL.MAINMENU, driver);
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.MYENTERRISELINK, driver);
		JavaScriptclick(driver, CL.MYENTERRISELINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXAYERSLINK);
		JavaScriptclick(driver, CL.TAXPAYERCODESEARCHBUTTON);
		Thread.sleep(2000);
		type(CL.TAXPAYERCODESEARCH, TaxPayercode);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.TAXPAYERFILTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//*[@id=\"entitySelectorOptions_table\"]/div/div/div[1]/div/div[3]/div[2]/div/div/div/div[1]/div/span[1]/span[2]"))
				.click();
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.SELECTTAXPAYERBUTTON);
		Thread.sleep(1000);
		JavaScriptclick(driver, CL.TAXPAYERSEARCH);
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("//*[@id=\"taxpayers_table\"]/div/div/div[1]/div/div[3]/div[3]/div/div/div/div/div/button"))
				.click();
		JavaScriptclick(driver, CL.TAXPAYEREDIT);
		// *[@id="taxpayerName_input"]
		if (TaxPayername != "") {
			Thread.sleep(2000);
			clear(CL.TAXPAYERNAME);
			type(CL.TAXPAYERNAME, TaxPayername);
		}
		Thread.sleep(2000);
		waitforElement(180, CL.REGISTRATIONSTAB, driver);
		click(CL.REGISTRATIONSTAB);
		if (jurisdictions != "") {
			Thread.sleep(2000);
			waitforElement(180, CL.REGISTRATIONSSEARCH, driver);
			JavaScriptscrolltoView(driver, CL.REGISTRATIONSSEARCH);
			JavaScriptclick(driver, CL.REGISTRATIONSSEARCH);
			Thread.sleep(5000);
			String[] jurisdictionList = jurisdictions.split(",");
			for (int x = 0; x < jurisdictionList.length; x++) {
				driver.findElement(By.xpath("//label/span[contains(.,'" + jurisdictionList[x] + "')]")).click();
				System.out.println(jurisdictionList[x]);
			}
			Thread.sleep(2000);
			waitforElement(180, CL.SELECT, driver);
			scrollDown(driver);
			JavaScriptclick(driver, CL.SELECT);
		}
		driver.findElement(By.xpath("//div/span[contains(.,'Import')]")).click();
		if (regnumber != "") {
			waitforElement(180, CL.IMPORTREGNUMBER, driver);
			clear(CL.IMPORTREGNUMBER);
			type(CL.IMPORTREGNUMBER, regnumber);
			Thread.sleep(1000);
		}
		if (memberState != "") {
			driver.findElement(
					By.xpath("//*[@id=\"IMPORT_SCHEME_collapse\"]/div[2]/div/div[3]/div[1]/div/div/div/span[2]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@title='" + memberState + "']")).click();
			Thread.sleep(1000);
		}
		if (filingCurrency != "") {
			WebElement filingCurrencyDropdown = driver.findElement(
					By.xpath("//*[@id=\"IMPORT_SCHEME_collapse\"]/div[2]/div/div[3]/div[2]/div/div/div/span[2]"));
			filingCurrencyDropdown.click();
			Thread.sleep(1000);
			// Need to add a way to scroll down for filing currency
			driver.findElement(By.xpath("//div[@title='" + filingCurrency + "']")).click();
		}
		Thread.sleep(1000);
		click(CL.SAVETAXPAYER);
		Thread.sleep(5000);
	}

	public void CreateNewUserRole(String Firstname, String LastName, String Emailid, String PhoneNumber,
			String AccountManagementRole, String VertexIndirecttaxrole, String IndirectTaxIntelligence, String Password,
			String ConfirmPassword) throws Exception {
		// ProfessionalUserLinks PUL = PageFactory.initElements(driver,
		// ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		/*
		 * waitforElement(30, CL.INDIRECTTAXOSERIES, driver); if (VerifyObject(driver,
		 * CL.INDIRECTTAXOSERIES)) { waitforText(180, CL.INDIRECTTAXOSERIES, driver);
		 * JavaScriptclick(driver, CL.INDIRECTTAXOSERIES); } Thread.sleep(3000);
		 * waitforElement(180, CL.VTXDROPDOWN, driver); click(CL.VTXDROPDOWN);
		 * waitforElement(180, CL.USERMANGEMENT, driver); JavaScriptclick(driver,
		 * CL.USERMANGEMENT);
		 */
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, CL.ADDNEWUSER, driver);
		click(CL.ADDNEWUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.USEREMAIL, driver);
		type(CL.USEREMAIL, Emailid);
		waitforElement(180, CL.ACCOUNTMANAGEMENT, driver);
		if (AccountManagementRole != "") {
			selectByValue(CL.ACCOUNTMANAGEMENT, AccountManagementRole);
		}
		/*
		 * click(CL.VERTEXCLOUDINDIRECTTAXROLE); type(CL.VERTEXCLOUDINDIRECTTAXROLE,
		 * VertexIndirecttaxrole); CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
		 * Thread.sleep(1000);
		 */
		Thread.sleep(1000);
		click(CL.VERTEXCLOUDINDIRECTTAXROLE);
		String[] userrole = VertexIndirecttaxrole.split(",");
		if (!VertexIndirecttaxrole.isEmpty()) {
			for (int i = 0; i < userrole.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLE, userrole[i]);
				Thread.sleep(2000);
				String Role_type = userrole[i];
				switch (Role_type) {
				case "Cloud Partition System Administrator":
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
					break;
				case "Admin":
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
					break;
				case "Read Only":
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
					break;

				}
				Thread.sleep(2000);
				// CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
			}
		}
		if (IndirectTaxIntelligence != "") {
			selectByValue(CL.INDIRECTTAXINTELLIGENCE, IndirectTaxIntelligence);
		}
		Thread.sleep(1000);
		click(CL.SENDINVITE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.COMPLETEREGISTATIONLINK, driver);
		click(CL.COMPLETEREGISTATIONLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		PortalUserName = Emailid;
		System.out.println("CL NewUser name:" + PortalUserName);
		Thread.sleep(2000);
		waitforElement(180, CL.FIRSTNAME, driver);
		type(CL.FIRSTNAME, Firstname);
		type(CL.LASTNAME, LastName);
		click(CL.PHONENUMBER);
		type(CL.PHONENUMBER, PhoneNumber);
		type(CL.PASSWORD, Password);
		type(CL.CONFIRMPASSWORD, ConfirmPassword);
		Thread.sleep(1000);
		JavaScriptclick(driver, CL.PROFILECOMP_REG);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, CL.LOGIN, driver);
		click(CL.LOGIN);
	}

	public void CreateNewUserRolewithPartition(String Firstname, String LastName, String Emailid, String PhoneNumber,
			String AccountManagementRole, String VertexIndirecttaxrole1, String VertexIndirecttaxrole2,
			String IndirectTaxIntelligence, String Password, String ConfirmPassword) throws Exception {
		// ProfessionalUserLinks PUL = PageFactory.initElements(driver,
		// ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		/*
		 * waitforElement(30, CL.INDIRECTTAXOSERIES, driver); if (VerifyObject(driver,
		 * CL.INDIRECTTAXOSERIES)) { waitforText(180, CL.INDIRECTTAXOSERIES, driver);
		 * JavaScriptclick(driver, CL.INDIRECTTAXOSERIES); } Thread.sleep(3000);
		 * waitforElement(180, CL.VTXDROPDOWN, driver); click(CL.VTXDROPDOWN);
		 * waitforElement(180, CL.USERMANGEMENT, driver); JavaScriptclick(driver,
		 * CL.USERMANGEMENT);
		 */
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, CL.ADDNEWUSER, driver);
		click(CL.ADDNEWUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.USEREMAIL, driver);
		type(CL.USEREMAIL, Emailid);
		waitforElement(180, CL.ACCOUNTMANAGEMENT, driver);
		if (AccountManagementRole != "") {
			selectByValue(CL.ACCOUNTMANAGEMENT, AccountManagementRole);
		}
		/*
		 * click(CL.VERTEXCLOUDINDIRECTTAXROLE); type(CL.VERTEXCLOUDINDIRECTTAXROLE,
		 * VertexIndirecttaxrole); CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
		 * Thread.sleep(1000);
		 */
		Thread.sleep(1000);
		//click(CL.VERTEXCLOUDINDIRECTTAXROLEPART1);
		String[] userrole1 = VertexIndirecttaxrole1.split(",");
		if (!VertexIndirecttaxrole1.isEmpty()) {
			for (int i = 0; i < userrole1.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLEPART1, userrole1[i]);
				Thread.sleep(2000);
				String Role_type = userrole1[i];
				switch (Role_type) {
				case "Cloud Partition System Administrator":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;
				case "Admin":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;
				case "Read Only":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;
				}
				// CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
			}
		}
		Thread.sleep(2000);
		//click(CL.VERTEXCLOUDINDIRECTTAXROLEPART2);
		String[] userrole2 = VertexIndirecttaxrole2.split(",");
		if (!VertexIndirecttaxrole2.isEmpty()) {
			for (int i = 0; i < userrole2.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLEPART2, userrole2[i]);
				Thread.sleep(2000);
				String Role_type1 = userrole2[i];
				switch (Role_type1) {
				case "Cloud Partition System Administrator":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;
				case "Admin":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;
				case "Read Only":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;

				}
				Thread.sleep(2000);
				// CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
			}
		}
		if (IndirectTaxIntelligence != "") {
			selectByValue(CL.INDIRECTTAXINTELLIGENCE, IndirectTaxIntelligence);
		}
		Thread.sleep(1000);
		click(CL.SENDINVITE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.COMPLETEREGISTATIONLINK, driver);
		click(CL.COMPLETEREGISTATIONLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		PortalUserName = Emailid;
		System.out.println("CL NewUser name:" + PortalUserName);
		Thread.sleep(2000);
		waitforElement(180, CL.FIRSTNAME, driver);
		type(CL.FIRSTNAME, Firstname);
		type(CL.LASTNAME, LastName);
		click(CL.PHONENUMBER);
		type(CL.PHONENUMBER, PhoneNumber);
		type(CL.PASSWORD, Password);
		type(CL.CONFIRMPASSWORD, ConfirmPassword);
		Thread.sleep(1000);
		JavaScriptclick(driver, CL.PROFILECOMP_REG);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, CL.LOGIN, driver);
		click(CL.LOGIN);
	}

	public void ReInviteUserSameEmailIdInClient(String Emailid, String AccountManagementRole,
			String VertexIndirecttaxrole1, String VertexIndirecttaxrole2, String IndirectTaxIntelligence)
			throws Exception {
		// ProfessionalUserLinks PUL = PageFactory.initElements(driver,
		// ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		/*
		 * waitforElement(30, CL.INDIRECTTAXOSERIES, driver); if (VerifyObject(driver,
		 * CL.INDIRECTTAXOSERIES)) { waitforText(180, CL.INDIRECTTAXOSERIES, driver);
		 * JavaScriptclick(driver, CL.INDIRECTTAXOSERIES); } Thread.sleep(3000);
		 * waitforElement(180, CL.VTXDROPDOWN, driver); click(CL.VTXDROPDOWN);
		 * waitforElement(180, CL.USERMANGEMENT, driver); JavaScriptclick(driver,
		 * CL.USERMANGEMENT);
		 */
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, CL.ADDNEWUSER, driver);
		click(CL.ADDNEWUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.USEREMAIL, driver);
		type(CL.USEREMAIL, Emailid);
		waitforElement(180, CL.ACCOUNTMANAGEMENT, driver);
		if (AccountManagementRole != "") {
			selectByValue(CL.ACCOUNTMANAGEMENT, AccountManagementRole);
		}
		/*
		 * click(CL.VERTEXCLOUDINDIRECTTAXROLE); type(CL.VERTEXCLOUDINDIRECTTAXROLE,
		 * VertexIndirecttaxrole); CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
		 * Thread.sleep(1000);
		 */
		Thread.sleep(1000);
		//click(CL.VERTEXCLOUDINDIRECTTAXROLEPART1);
		String[] userrole = VertexIndirecttaxrole1.split(",");
		if (!VertexIndirecttaxrole1.isEmpty()) {
			for (int i = 0; i < userrole.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLEPART1, userrole[i]);
				String Role_type = userrole[i];
				switch (Role_type) {
				case "Cloud Partition System Administrator":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;
				case "Admin":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;
				case "Read Only":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;

				}
				Thread.sleep(2000);
				//CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
			}
		}
		Thread.sleep(1000);
		//click(CL.VERTEXCLOUDINDIRECTTAXROLEPART2);
		String[] userrole1 = VertexIndirecttaxrole2.split(",");
		if (!VertexIndirecttaxrole2.isEmpty()) {
			for (int i = 0; i < userrole1.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLEPART2, userrole1[i]);
				String Role_type1 = userrole1[i];
				switch (Role_type1) {
				case "Cloud Partition System Administrator":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;
				case "Admin":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;
				case "Read Only":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;

				}
				Thread.sleep(2000);
				//CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
			}
		}
		if (IndirectTaxIntelligence != "") {
			selectByValue(CL.INDIRECTTAXINTELLIGENCE, IndirectTaxIntelligence);
		}
		Thread.sleep(1000);
		click(CL.SENDINVITE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(CL.VIEWALLUSERSLINKFROMCLIENT);
		Thread.sleep(3000);
		WebElement ReInviteEmail = driver.findElement(By.xpath("//td[contains(.,'" + Emailid + "')]/../td[7]"));
		System.out.println(ReInviteEmail.getText());
	}

	public void DeleteuserfromClientportal(String Email) throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(CL.VIEWALLUSERSLINKFROMCLIENT);
		Thread.sleep(10000);
		WebElement delete = driver
				.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td/a[contains(.,'Delete')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		// JavaScriptscrolltoView(driver, delete);
		Thread.sleep(2000);
		JavaScriptclick(driver, delete);
		waitforElement(180, CL.DELETEHEADERTEXT, driver);
		assertTrue(CL.CANCELLINK.isDisplayed());
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.REMOVECUSTOMERLINK);
		Thread.sleep(2000);
		if (!VerifyObject(driver, CL.REMOVECUSTOMERLINK)) {
			assertTrue(true);
			System.out.println("UserDeleted");
		} else {
			assertTrue(false);
			System.out.println("UserNotDeleted");
		}
		waitforElement(180, CL.CREATENEWUSERLINK, driver);
		driver.navigate().refresh();
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.SETTINGS);
	}

	public void DeleteuserfromAdminportal(String ClientName, String Email) throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(120, CL.SMBCUSTOMERSTAB, driver);
		click(CL.SMBCUSTOMERSTAB);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.SMBCUSTOMERNAME, driver);
		type(CL.SMBCUSTOMERNAME, ClientName);
		Thread.sleep(4000);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]",
				driver);
		driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"))
				.click();
		Thread.sleep(5000);
		WebElement delete = driver
				.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td/a[contains(.,'Delete')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);
		JavaScriptclick(driver, delete);
		waitforElement(180, CL.DELETEHEADERTEXT, driver);
		assertTrue(CL.CANCELLINK.isDisplayed());
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.REMOVEUSER);
		Thread.sleep(2000);
		if (!VerifyObject(driver, CL.REMOVEUSER)) {
			assertTrue(true);
			System.out.println("UserDeleted:");
		} else {
			assertTrue(false);
			System.out.println("UserNotDeleted:");
		}
		waitforElement(180, CL.CREATENEWUSERLINK, driver);

	}

	public void TransactionReport(String State, String StartDate, String EndDate, String CompanyCode) throws Exception {
		File directory = new File(".//");
		String path = null;
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		Thread.sleep(5000);
		waitforElement(30, CL.INDIRECTTAXOSERIESLINK, driver);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		waitforElement(30, CL.MAINMENU, driver);
		if (CL.MAINMENU.isDisplayed()) {
			waitforElement(180, CL.MAINMENU, driver);
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.REPORTS, driver);
		JavaScriptclick(driver, CL.REPORTS);
		waitforElement(180, CL.TRANSACTIONREPORT, driver);
		JavaScriptclick(driver, CL.TRANSACTIONREPORT);
		waitforElement(180, CL.ALLTRANSCOMPREPORTS, driver);
		click(CL.ALLTRANSCOMPREPORTS);
		waitforElement(180, CL.TRANSACTIONDETAILSOPTION, driver);
		JavaScriptclick(driver, CL.TRANSACTIONDETAILSOPTION);
		Thread.sleep(4000);
		click(CL.SELECTTPTDR);
		waitforElement(30, "//span[@class='vtx-text-gray-7']", driver);
		driver.findElement(By.xpath("//span[@class='vtx-text-gray-7']"));
		driver.findElement(By.xpath("//div/span[contains(.,'" + CompanyCode + "')]")).click();
		// JavaScriptclick(driver, taxpayercode);
		Thread.sleep(2000);
		click(CL.SELECTJURISDICTION);
		Thread.sleep(3000);
		WebElement alabama = driver.findElement(By.xpath("//div[@title='" + State + "']/div"));
		click(alabama);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@data-automation-id='jurisdiction-selector-select']/div")).click();
		Thread.sleep(3000);
		/*
		 * boolean report = false; do { Actions actions = new Actions(driver);
		 * actions.moveToElement(alabama); JavaScriptMousehover(driver, alabama);
		 * actions.sendKeys(Keys.DOWN); Robot robot = new Robot();
		 * robot.keyPress(KeyEvent.VK_DOWN); Thread.sleep(2000);
		 * robot.keyRelease(KeyEvent.VK_DOWN); if (VerifyObject(driver,
		 * "//div[@class='ant-select-item ant-select-item-option']/div[contains(.,'" +
		 * State + "')]")) { report = true; } } while (report == false);
		 */

		/*
		 * WebElement jurisdiction = driver.findElement( By.
		 * xpath("//div[@class='ant-select-item ant-select-item-option']/div[contains(.,'"
		 * + State + "')]")); JavaScriptscrolltoView(driver, jurisdiction);
		 * Thread.sleep(2000); JavaScriptclick(driver, jurisdiction);
		 * Thread.sleep(2000);
		 */
		click(CL.STARTDATE);
		type(CL.STARTDATE, StartDate);
		CL.STARTDATE.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		click(CL.ENDDATE);
		type(CL.ENDDATE, EndDate);
		CL.ENDDATE.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		JavaScriptclick(driver, CL.GENERATETMREPORT);
		waitforElement(360,
				"//table/tbody/tr/td/span[contains(.,'" + State + "')]/../../td/span/a[contains(.,'Download')]",
				driver);
		WebElement download = driver.findElement(By.xpath(
				"//table/tbody/tr/td/span[contains(.,'" + State + "')]/../../td/span/a[contains(.,'Download')]"));
		click(download);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
					+ "TransactionDetailReport";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			new File(LatestDownloadedFile("TransactionDetailReport", true));

		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			new File(LatestDownloadedFile("TransactionDetailReport", false));
			Thread.sleep(20000);
		}

		xExtractZiReadContents(Filepath, xDownloadFiles("TransactionDetailReport"));
		xGetCSVCellValue(actualfile, "Taxpayer_Company_Code");
		String TaxpayerCompanyCode = Cell_Value;
		assertTrue(TaxpayerCompanyCode.equals(CompanyCode));

	}

	public void UploadAdjustmentFiles(String Format, String Messagetype, String filename, String fileExt,
			String VerifySuccessfile, String VerifyErrorfile, String Validate) throws Exception {
		String DirName = null;
		PortalFunctions DownloadFiles = PageFactory.initElements(driver, PortalFunctions.class);
		xUploadAdjustmentFile("AdjustmentFiles");
		String AutoITpath = xAutoIT();
		String LatestSucc = null;
		File directory = new File(".//");
		String Filename, browser, AppTXTFile, name, Suc_File;
		String path = null;
		String adjustmentfilepath = xUploadAdjustmentFile("AdjustmentFiles");
		browser = Browser;
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		waitforElement(30, CL.MAINMENU, driver);
		if (CL.MAINMENU.isDisplayed()) {
			JavaScriptclick(driver, CL.MAINMENU);
		}
		waitforElement(180, CL.TOOLS, driver);
		JavaScriptclick(driver, CL.TOOLS);
		waitforElement(180, CL.ADJUSTMENTFILE, driver);
		JavaScriptclick(driver, CL.ADJUSTMENTFILE);
		Thread.sleep(3000);
		waitforElement(180, CL.MESSAGETYPE, driver);
		switch (Format) {
		case "Seller":
			JavaScriptclick(driver, CL.SELLER);
			break;
		case "Buyer":
			// JavaScriptclick(driver, CL.BUYER);
			break;
		case "Canadian Transactions":
			JavaScriptclick(driver, CL.CANADIANTRANSACTIONS);
			break;
		}

		switch (Messagetype) {
		case "Invoice":
			click(CL.MESSAGETYPE);
			waitforElement(180, CL.INVOICETYPE, driver);
			click(CL.INVOICETYPE);
			break;
		case "Quotation":
			click(CL.MESSAGETYPE);
			waitforElement(180, CL.QUOTATIONTYPE, driver);
			click(CL.QUOTATIONTYPE);
			break;
		case "DistributeTax":
			click(CL.MESSAGETYPE);
			waitforElement(180, CL.DISTRIBUTETAXTYPE, driver);
			click(CL.DISTRIBUTETAXTYPE);
			break;

		case "Accrual":
			click(CL.MESSAGETYPE);
			waitforElement(180, CL.ACCRUALTYPE, driver);
			click(CL.ACCRUALTYPE);
			break;
		case "Asset Goods Movement":
			click(CL.MESSAGETYPE);
			waitforElement(180, CL.ASSETSGOODS, driver);
			click(CL.ASSETSGOODS);
			break;
		case "Inventory Removal":
			click(CL.MESSAGETYPE);
			waitforElement(180, CL.INVENTORYREMOVAL, driver);
			click(CL.INVENTORYREMOVAL);
			break;
		case "Purchase Order":
			click(CL.MESSAGETYPE);
			waitforElement(180, CL.PURCHASEORDER, driver);
			click(CL.PURCHASEORDER);
			break;
		case "Distribute Tax Procurement":
			click(CL.MESSAGETYPE);
			waitforElement(180, CL.DISTRIBUTETAXPROCUREMENT, driver);
			click(CL.DISTRIBUTETAXPROCUREMENT);
			break;
		case "Invoice Verification":
			click(CL.MESSAGETYPE);
			waitforElement(180, CL.INVOICEVERIFICATION, driver);
			click(CL.INVOICEVERIFICATION);
			break;

		}
		Filename = xCSVUDateUpdation(adjustmentfilepath + "\\" + filename + ".csv");
		waitforElement(180, CL.DOWNLOADTEMPLATE, driver);
		JavaScriptclick(driver, CL.DOWNLOADTEMPLATE);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdjustmentFiles";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			new File(LatestDownloadedFile("AdjustmentFiles", true));

		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			new File(LatestDownloadedFile("AdjustmentFiles", false));
			Thread.sleep(20000);
		}

		JavaScriptclick(driver, CL.BROWSETAXABILITYDRIVERS);
		Thread.sleep(5000);
		switch (browser) {
		case "IE":
			if (adjustmentfilepath.isEmpty() == true) {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
			} else {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename + "");
			}
			break;
		case "Firefox":
			if (adjustmentfilepath.isEmpty() == true) {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
			} else {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename + "");
			}
			break;
		case "Chrome":
			if (adjustmentfilepath.isEmpty() == true) {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
			} else {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename + "");
			}
			break;
		case "Safari":
			if (adjustmentfilepath.isEmpty() == true) {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
			} else {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename + "");
			}
			break;
		default:
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.UPLOADADJUSTMENTFILE);
		Thread.sleep(100000);
		if (VerifySuccessfile.equalsIgnoreCase("Yes")) {
			File tempfile = null;
			File tempfile2 = null;
			WebElement successfile = driver.findElement(By.xpath("//div/h3[contains(.,'" + filename
					+ "')]/../../div/div/div/div/div/div/div/div/div/div/div/span[contains(.,'Download success file')]"));
			waitforElement(180, successfile, driver);
			JavaScriptclick(driver, successfile);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdjustmentFiles";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(5000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
				LatestSucc = tempfile.toString();
			}
			// LatestDownloadedFile();
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
			}
			LatestSucc = xCSVUDateInputUpdation(LatestSucc);
			AppTXTFile = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdjustmentFiles"
					+ "\\" + "App_Suc_File" + RND() + ".txt";
			FileWriter writer1 = new FileWriter(AppTXTFile, true);
			writer1.write(LatestSucc);
			writer1.close();
			tempfile = new File(AppTXTFile);
			DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "AdjustmentFiles";
			name = Validate;
			Suc_File = DirName + "\\" + name + ".csv";
			Suc_File = xCSVUDateInputUpdation(Suc_File);
			FileWriter writer = new FileWriter(DirName + "Suc_File" + RND() + ".txt", true);
			writer.write(Suc_File);
			writer.close();
			tempfile2 = new File(DirName + "Suc_File" + RND() + ".txt");
			Thread.sleep(5000);
			if (Validate != "") {
				@SuppressWarnings("unchecked")
				ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
				@SuppressWarnings("unchecked")
				ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				f1.removeAll(f2);
			}
		}
		if (VerifyErrorfile.equalsIgnoreCase("Yes")) {
			File tempfile = null;
			WebElement successfile = driver.findElement(By.xpath("//div/h3[contains(.,'" + Filename
					+ "')]/../../div/div/div/div/div/div/div/div/div/div/div/span[contains(.,'Download success file')]"));
			waitforElement(180, successfile, driver);
			JavaScriptclick(driver, successfile);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdjustmentFiles";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(5000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
				LatestSucc = tempfile.toString();
			}
			// LatestDownloadedFile();
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
			}
		}

	}

	public void CreateEUOSSReport(String taxpayerCode, String registrationScheme, String startDate, String endDate,
			String filingCurrency, String exchangeRateDate, String fileName, String fileExt) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		new File(".//");
		new File(".//");
		PageFactory.initElements(driver, PortalFunctions.class);
		String Driverpath = xUploadAdjustmentFile("EUOSSReports");
		String AutoITpath = xAutoIT();
		String path = null;
		File directory = new File(".//");

		Thread.sleep(5000);
		if (VerifyObject(driver, CL.INDIRECTTAXOSERIESLINK)) {
			waitforText(180, CL.INDIRECTTAXOSERIESLINK, driver);
			JavaScriptclick(driver, CL.INDIRECTTAXOSERIESLINK);
		}
		Thread.sleep(12000);

		if (VerifyObject(driver, CL.MAINMENU)) {
			waitforElement(180, CL.MAINMENU, driver);
			JavaScriptclick(driver, CL.MAINMENU);
		}

		waitforElement(360, CL.REPORTS, driver);
		JavaScriptclick(driver, CL.REPORTS);
		waitforElement(360, CL.TRANSACTIONCOMPLIANCEREPORTS, driver);
		JavaScriptclick(driver, CL.TRANSACTIONCOMPLIANCEREPORTS);
		boolean isDropdownThere = false;
		int timeLimit = 0;
		// Checks to see if the dropdown is there for 15 seconds, otherwise it will fail
		// on the next line
		while (isDropdownThere == false && timeLimit < 15) {
			isDropdownThere = VerifyObject(driver, "//span[@title='All Transaction & Compliance Reports']");
			System.out.println(isDropdownThere + " " + timeLimit);
			timeLimit++;
			Thread.sleep(1000);
		}
		driver.findElement(By.xpath("//span[@title='All Transaction & Compliance Reports']")).click();
		Thread.sleep(2000);
		// EU One Stop Shop Extract in the dropdown
		driver.findElement(By.xpath(
				"//*[@id=\"single-spa-application:vtx-ui-mf-calc-config\"]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[10]/div"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='taxpayer']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div/span[contains(.,'" + taxpayerCode + "')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='registrationScheme']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@title='" + registrationScheme + "']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='dateRange']")).click();
		// driver.findElement(By.xpath("//input[@placeholder='startDate']")).click();
		// Thread.sleep(1000);
		type(CL.OSSSTARTDATE, startDate);
		Thread.sleep(1000);
		CL.OSSSTARTDATE.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@placeholder='End date']")).click();
		type(CL.OSSENDDATE, endDate);
		Thread.sleep(1000);
		CL.OSSENDDATE.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		// filing currency
		click(CL.OSSFILINGCURRENCY);
		Thread.sleep(1000);
		type(CL.OSSFILINGCURRENCY, filingCurrency);
		Thread.sleep(1000);
		CL.OSSFILINGCURRENCY.sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		/*
		 * Thread.sleep(1000); driver.findElement(By.xpath("//td[@title='" + startDate +
		 * "']")).click(); Thread.sleep(1000);
		 * driver.findElement(By.xpath("//input[@placeholder='End date']")).click();
		 * Thread.sleep(1000); driver.findElement(By.xpath("//td[@title='" + endDate +
		 * "']")).click();
		 */

		WebElement rateDate = driver.findElement(By.xpath("//input[@id='exchangeRateDate']"));
		rateDate.click();
		type(rateDate, exchangeRateDate);
		rateDate.sendKeys(Keys.ENTER);
		// driver.findElement(By.xpath("//td[@title='" + exchangeRateDate +
		// "']/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='ant-upload-drag-container']/p")).click();
		Thread.sleep(5000);
		Browser(driver, Browser, AutoITpath, Driverpath, fileName, fileExt);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.GENERATETMREPORT);
		Thread.sleep(50000);
		waitforElement(180, "//table/tbody/tr/td/span/span[contains(.,'" + taxpayerCode
				+ "')]/../../../td/span/a[contains(.,'Download')]", driver);
		WebElement download = driver.findElement(By.xpath("//table/tbody/tr/td/span/span[contains(.,'" + taxpayerCode
				+ "')]/../../../td/span/a[contains(.,'Download')]"));
		click(download);

		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "EUOSSReports";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			new File(LatestDownloadedFile("EUOSSReports", true));

		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			new File(LatestDownloadedFile("EUOSSReports", false));
			Thread.sleep(20000);
		}

		xExtractZiReadContents(Filepath, xDownloadFiles("EUOSSReportTest"));
		xGetCSVCellValue(actualfile, "Company Code");
		String TaxpayerCompanyCode = Cell_Value;
		System.out.println("Companycode is " + TaxpayerCompanyCode);
		assertTrue(TaxpayerCompanyCode.equals(taxpayerCode));

	}

	public void ChangeRoleFunctionalityfromClient(String Email, String VertexIndirecttaxrole,
			String IndirectTaxIntelligence) throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		/*
		 * waitforElement(30, CL.INDIRECTTAXOSERIES, driver); if (VerifyObject(driver,
		 * CL.INDIRECTTAXOSERIES)) { waitforText(180, CL.INDIRECTTAXOSERIES, driver);
		 * JavaScriptclick(driver, CL.INDIRECTTAXOSERIES); } Thread.sleep(3000);
		 * waitforElement(180, CL.VTXDROPDOWN, driver); click(CL.VTXDROPDOWN);
		 * waitforElement(180, CL.USERMANGEMENT, driver); JavaScriptclick(driver,
		 * CL.USERMANGEMENT);
		 */
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(CL.VIEWALLUSERSLINKFROMCLIENT);
		Thread.sleep(10000);
		WebElement chnagerole = driver.findElement(
				By.xpath("//td[contains(.,'" + Email + "')]/../td[9]/div[1]/a[contains(.,'Change Role')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);
		JavaScriptclick(driver, chnagerole);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.USEREMAIL, driver);
		Thread.sleep(1000);
		waitforElement(180, CL.ACCOUNTMANAGEMENT, driver);
		/*
		 * JavaScriptclick(driver, CL.ACCOUNTMANAGEMENT);
		 * selectByValue(CL.ACCOUNTMANAGEMENT, "Admin");
		 */
		Thread.sleep(2000);
		click(CL.VERTEXCLOUDINDIRECTTAXROLE);
		String[] newrole = VertexIndirecttaxrole.split(",");
		if (!VertexIndirecttaxrole.isEmpty()) {
			for (int i = 0; i < newrole.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLE, newrole[i]);
				Thread.sleep(2000);
				String Role_type = newrole[i];
				switch (Role_type) {
				case "Cloud Partition System Administrator":
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
					break;
				case "Admin":
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
					break;
				case "Read Only":
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
					break;

				}
				Thread.sleep(2000);
				//CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
			}
		}
		Thread.sleep(2000);
		/*
		 * if (IndirectTaxIntelligence != "") {
		 * selectByValue(CL.INDIRECTTAXINTELLIGENCE, IndirectTaxIntelligence); }
		 */
		assertTrue(CL.CHANGEROLESAVEBUTTON.isDisplayed());
		assertTrue(CL.CHANGEROLECANCELBUTTON.isDisplayed());
		click(CL.CHANGEROLESAVEBUTTON);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(CL.VIEWALLUSERSLINKFROMCLIENT);
		// scrollUp(driver);
		// waitforElement(180, CL.RETURNTOUSERS, driver);
		// click(CL.RETURNTOUSERS);
		Thread.sleep(5000);
		WebElement ChangeroleEmail = driver.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td[7]"));
		System.out.println(ChangeroleEmail.getText());
	}

	public void VerifyIndirectTaxOseries() throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(CL.INDIRECTTAXOSERIESCLOUD.isDisplayed());
//		JavaScriptclick(driver, CL.INDIRECTTAXOSERIESCLOUD);

	}

	public void VerifyAccountManagementRole(String Email, String AccountManagement, String VertexIndirecttaxrole,
			String IndirectTaxIntelligence) throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(CL.VIEWALLUSERSLINKFROMCLIENT);
		Thread.sleep(3000);
		WebElement chnagerole = driver.findElement(
				By.xpath("//td[contains(.,'" + Email + "')]/../td[9]/div[1]/a[contains(.,'Change Role')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);

		if (AccountManagement == "Yes") {
			JavaScriptclick(driver, chnagerole);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CL.USEREMAIL, driver);
			Thread.sleep(1000);
			waitforElement(180, CL.ACCOUNTMANAGEMENT, driver);
			JavaScriptclick(driver, CL.ACCOUNTMANAGEMENT);
			selectByValue(CL.ACCOUNTMANAGEMENT, "Admin");
			Thread.sleep(2000);
			click(CL.VERTEXCLOUDINDIRECTTAXROLE);
			String[] newrole = VertexIndirecttaxrole.split(",");
			if (!VertexIndirecttaxrole.isEmpty()) {
				for (int i = 0; i < newrole.length; i++) {
					type(CL.VERTEXCLOUDINDIRECTTAXROLE, newrole[i]);
					Thread.sleep(2000);
					CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
				}
			}
			/*
			 * if (IndirectTaxIntelligence != "") {
			 * selectByValue(CL.INDIRECTTAXINTELLIGENCE, IndirectTaxIntelligence); }
			 */
			assertTrue(CL.CHANGEROLESAVEBUTTON.isDisplayed());
			assertTrue(CL.CHANGEROLECANCELBUTTON.isDisplayed());
			click(CL.CHANGEROLESAVEBUTTON);
			Thread.sleep(5000);
			scrollUp(driver);
			waitforElement(180, CL.RETURNTOUSERS, driver);
			click(CL.RETURNTOUSERS);
			Thread.sleep(5000);
			WebElement ChangeroleEmail = driver.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td[7]"));
			System.out.println(ChangeroleEmail.getText());
		} else {
			driver.findElement(
					By.xpath("//td[contains(.,'" + Email + "')]/../td[9]/div[1]/a[contains(.,'Change Role')]")).click();
			Thread.sleep(1000);
//			JavascriptExecutor jss = (JavascriptExecutor) driver;
			js.executeScript("scroll(100,50)");
			waitforElement(180, CL.DISABLE_ACCOUNTMANAGEMENT, driver);
			CL.DISABLE_ACCOUNTMANAGEMENT.isEnabled();
			click(CL.CHANGEROLESAVEBUTTON);

		}

	}

	public void UserCreationwithPending(String Emailid, String AccountManagementRole, String VertexIndirecttaxrole1,
			String VertexIndirecttaxrole2, String IndirectTaxIntelligence) throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		/*
		 * waitforElement(30, CL.INDIRECTTAXOSERIES, driver); if (VerifyObject(driver,
		 * CL.INDIRECTTAXOSERIES)) { waitforText(180, CL.INDIRECTTAXOSERIES, driver);
		 * JavaScriptclick(driver, CL.INDIRECTTAXOSERIES); } Thread.sleep(3000);
		 * waitforElement(180, CL.VTXDROPDOWN, driver); click(CL.VTXDROPDOWN);
		 * waitforElement(180, CL.USERMANGEMENT, driver); JavaScriptclick(driver,
		 * CL.USERMANGEMENT);
		 */
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, CL.ADDNEWUSER, driver);
		click(CL.ADDNEWUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.USEREMAIL, driver);
		type(CL.USEREMAIL, Emailid);
		waitforElement(180, CL.ACCOUNTMANAGEMENT, driver);
		if (AccountManagementRole != "") {
			selectByValue(CL.ACCOUNTMANAGEMENT, AccountManagementRole);
		}
		/*
		 * click(CL.VERTEXCLOUDINDIRECTTAXROLE); type(CL.VERTEXCLOUDINDIRECTTAXROLE,
		 * VertexIndirecttaxrole); CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
		 * Thread.sleep(1000);
		 */
		Thread.sleep(1000);
		//click(CL.VERTEXCLOUDINDIRECTTAXROLEPART1);
		String[] userrole = VertexIndirecttaxrole1.split(",");
		if (!VertexIndirecttaxrole1.isEmpty()) {
			for (int i = 0; i < userrole.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLEPART1, userrole[i]);
				String Role_type = userrole[i];
				switch (Role_type) {
				case "Cloud Partition System Administrator":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;
				case "Admin":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;
				case "Read Only":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;

				}
				Thread.sleep(2000);
				//CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
			}
		}
		Thread.sleep(1000);
		//click(CL.VERTEXCLOUDINDIRECTTAXROLEPART2);
		String[] userrole1 = VertexIndirecttaxrole2.split(",");
		if (!VertexIndirecttaxrole2.isEmpty()) {
			for (int i = 0; i < userrole1.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLEPART2, userrole1[i]);
				String Role_type1 = userrole1[i];
				switch (Role_type1) {
				case "Cloud Partition System Administrator":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;
				case "Admin":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;
				case "Read Only":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;

				}
				Thread.sleep(2000);
				//CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
			}
		}
		if (IndirectTaxIntelligence != "") {
			selectByValue(CL.INDIRECTTAXINTELLIGENCE, IndirectTaxIntelligence);
		}
		Thread.sleep(1000);
		click(CL.SENDINVITE);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, CL.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(CL.VIEWALLUSERSLINKFROMCLIENT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		WebElement ChangeroleEmail = driver.findElement(By.xpath("//td[contains(.,'" + Emailid + "')]/../td[7]"));
		System.out.println(ChangeroleEmail.getText());

	}

	public void VerifyChangeRoleLink(String Email) throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(CL.VIEWALLUSERSLINKFROMCLIENT);
		Thread.sleep(3000);
		WebElement chnagerole = driver.findElement(
				By.xpath("//td[contains(.,'" + Email + "')]/../td[9]/div[1]/a[contains(.,'Change Role')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		assertTrue(chnagerole.isDisplayed());
		Thread.sleep(1000);
	}

	public void ChangeRolesUserSync(String Email, String VertexIndirecttaxrole1, String VertexIndirecttaxrole2)
			throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(CL.VIEWALLUSERSLINKFROMCLIENT);
		Thread.sleep(10000);
		WebElement chnagerole = driver.findElement(
				By.xpath("//td[contains(.,'" + Email + "')]/../td[9]/div[1]/a[contains(.,'Change Role')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);
		JavaScriptclick(driver, chnagerole);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.USEREMAIL, driver);
		Thread.sleep(1000);
		waitforElement(180, CL.ACCOUNTMANAGEMENT, driver);
		Thread.sleep(2000);
		//click(CL.VERTEXCLOUDINDIRECTTAXROLEPART1);
		String[] userrole = VertexIndirecttaxrole1.split(",");
		if (!VertexIndirecttaxrole1.isEmpty()) {
			for (int i = 0; i < userrole.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLEPART1, userrole[i]);
				String Role_type = userrole[i];
				switch (Role_type) {
				case "Cloud Partition System Administrator":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;
				case "Admin":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;
				case "Read Only":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
					break;

				}
				Thread.sleep(2000);
				//CL.VERTEXCLOUDINDIRECTTAXROLEPART1.sendKeys(Keys.ENTER);
			}
		}
		Thread.sleep(1000);
		//click(CL.VERTEXCLOUDINDIRECTTAXROLEPART2);
		String[] userrole1 = VertexIndirecttaxrole2.split(",");
		if (!VertexIndirecttaxrole2.isEmpty()) {
			for (int i = 0; i < userrole1.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLEPART2, userrole1[i]);
				String Role_type1 = userrole1[i];
				switch (Role_type1) {
				case "Cloud Partition System Administrator":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;
				case "Admin":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;
				case "Read Only":
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.DOWN);
					CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
					break;

				}
				Thread.sleep(2000);
				//CL.VERTEXCLOUDINDIRECTTAXROLEPART2.sendKeys(Keys.ENTER);
			}
		}
		assertTrue(CL.CHANGEROLESAVEBUTTON.isDisplayed());
		assertTrue(CL.CHANGEROLECANCELBUTTON.isDisplayed());
		click(CL.CHANGEROLESAVEBUTTON);
		Thread.sleep(5000);
		JavaScriptclick(driver, CL.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(CL.VIEWALLUSERSLINKFROMCLIENT);
		//scrollUp(driver);
		//waitforElement(180, CL.RETURNTOUSERS, driver);
		//click(CL.RETURNTOUSERS);
		Thread.sleep(5000);
		WebElement ChangeroleEmail = driver.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td[7]"));
		System.out.println(ChangeroleEmail.getText());
	}
	
	public void CloudOSeriesVerifyPartition() throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		Thread.sleep(10000);
		waitforText(30, CL.INDIRECTTAXOSERIESCLOUD, driver);
		assertTrue(VerifyObject(driver, CL.INDIRECTTAXOSERIESCLOUD));
		click(CL.INDIRECTTAXOSERIESCLOUD);
		Thread.sleep(15000);
		waitforText(180, CL.SELECTPARTITION, driver);
		Thread.sleep(6000);
        if (VerifyObject(driver, CL.PARTITION1)) {
	       waitforText(180, CL.PARTITION1, driver);
	       String PID123 = getText(CL.PARTITION1);
		   System.out.println(PID123);
		   Thread.sleep(2000);
        }
        if (VerifyObject(driver, CL.PARTITION2)) {
	       waitforText(180, CL.PARTITION2, driver);
	       String PID123 = getText(CL.PARTITION2);
	       System.out.println(PID123);
		   Thread.sleep(2000);
        }
       System.out.println("Success- Logged into calc config MFE- and Verifyed Partitions");
	}
	
	
	public void SelectDefaultPartition() throws Exception {
	    calcparity CL = PageFactory.initElements(driver, calcparity.class);
	    waitforText(180, CL.INDIRECTTAXOSERIESCLOUD, driver);
        assertTrue(VerifyObject(driver, CL.INDIRECTTAXOSERIESCLOUD));
	    click(CL.INDIRECTTAXOSERIESCLOUD);
	    Thread.sleep(10000);
	    waitforText(180, CL.SELECTPARTITION, driver);
	    String DefPID= DPID.substring(0,7);
	    System.out.println(DefPID);
	    driver.findElement(By.xpath("//div[@class='partitions-list']/div[contains(.,"+ DefPID + ")]/span")).click();
	    System.out.println("Select Default Admin Partition");
	    
   }
	
	public void SelectNewPartition() throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		 //waitforText(180, CL.INDIRECTTAXOSERIESCLOUD, driver);
	    //assertTrue(VerifyObject(driver, CL.INDIRECTTAXOSERIESCLOUD));
		//click(CL.INDIRECTTAXOSERIESCLOUD);
		 Thread.sleep(10000);
		 waitforText(180, CL.SELECTPARTITION, driver);
		 driver.findElement(By.xpath("//div[@class='partitions-list']/div[contains(.,"+ NPID +")]/span")).click();
		 System.out.println("Select New Partition");
	}
	
	public void VerifyTaxpayerLandingPage() throws Exception {
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(10000);
		waitforText(30, CL.INDIRECTTAXOSERIESCLOUD, driver);
		assertTrue(VerifyObject(driver, CL.INDIRECTTAXOSERIESCLOUD));
		click(CL.INDIRECTTAXOSERIESCLOUD);
		Thread.sleep(25000);
		waitforText(180, CL.TAXAYERSLINK, driver);
		assertTrue(CL.TAXAYERSLINK.isDisplayed());
		waitforElement(180, CL.MAINMENU, driver);
		assertTrue(CL.MAINMENU.isDisplayed());
		System.out.println("Success- Logged into calc config MFE- TaxPayer Landing Page is displayed");
		Thread.sleep(10000);
		waitforElement(180, CL.VTXDROPDOWN, driver); 
		click(CL.VTXDROPDOWN);
		waitforElement(180, CL.USERMANGEMENT, driver); 
		JavaScriptclick(driver,CL.USERMANGEMENT);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		
	}
	
	public void navigatetoSettings() throws Exception {
	  calcparity CL = PageFactory.initElements(driver, calcparity.class);
	  ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
	  waitforElement(180, CL.VTXDROPDOWN, driver); 
	  click(CL.VTXDROPDOWN);
	  waitforElement(180, CL.USERMANGEMENT, driver); 
	  JavaScriptclick(driver,CL.USERMANGEMENT);
	  Thread.sleep(2000);
	  waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver); 
	  
	}
}