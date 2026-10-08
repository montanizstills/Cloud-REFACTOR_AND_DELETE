/*###################################################################################################
Name: LinksNavigation.java 
Description: Having all Portals Links Navigationrelated functions 
Author: Venkat Reddy Kandula
Date Created:02/10/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.libraries.miscellaneous;

import java.awt.Robot;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurationReportPage;
import common.pages.ConfigurePage;
import common.pages.CustomerExceptionReport;
import common.pages.FreeUserLinks;
import common.pages.LookupTaxRates;
import common.pages.ProfessionalUserLinks;
import common.pages.TransactionReportPage;
import common.properties.CommonDataProperties;
import common.properties.ReadProperties;

public class LinksNavigation extends CommonFunctions {

	private WebDriver driver;
	ReadProperties CONNECTOR_PROPERTIES = new ReadProperties(CommonDataProperties.CONNECTORS_FILE_PATH);

	public LinksNavigation(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void StandardUserLinks(String DashBoard_Title, String Lookuprates_Title, String Data_Integrity,
			String Global_Compliance, String EditMyCompany_Title, String MyUsers_Title, String UpdateBilling_Title,
			String BillingReports_Title) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LookupTaxRates LTR = PageFactory.initElements(driver, LookupTaxRates.class);
		String parentwindow = driver.getWindowHandle();
		scrollUp(driver);
		click(FUL.DASHBOARDLINK);
		waitforElement(180, PUL.UPDATEANALYSIS, driver);
		assertTrue(DashBoard_Title.equalsIgnoreCase(driver.getTitle()));
		// DASHBOARD Page Verification
		assertTrue(PUL.COMPANYDD.isDisplayed());

		assertTrue(PUL.FILINGPERIODDD.isDisplayed());
		assertTrue(PUL.UPDATERESULTS.isDisplayed());
		assertTrue(PUL.UNITEDSTATES.isDisplayed());
		assertTrue(PUL.CANADA.isDisplayed());
		assertTrue(PUL.OVERALLNUMBERSSECTION.isDisplayed());
		assertTrue(PUL.STATESTATSTICS.isDisplayed());
		assertTrue(PUL.CURRENTLYSHOWINGDD.isDisplayed());
		assertTrue(PUL.SORTBYDD.isDisplayed());

		click(FUL.LOOKUPTAXRATESLINK);
		waitforText(180, PUL.LOOKUPRATE, driver);
		assertTrue(Lookuprates_Title.equalsIgnoreCase(driver.getTitle()));
		driver.findElement(By.xpath("//input[@name='lookupBy' and @value='2']")).click();
		Thread.sleep(2000);
		assertTrue(LTR.TAXAREAID.isDisplayed());
		if ((Data_Integrity != "") && (Environment.equalsIgnoreCase("DEV"))) {
			click(PUL.DATAINTEGRITY);
			Thread.sleep(3000);
			assertTrue(Data_Integrity.equalsIgnoreCase(driver.getTitle()));
		}
		if ((Global_Compliance != "") && (Environment.equalsIgnoreCase("DEV"))) {
			click(PUL.GLOBALCOMPLIANCE);
			assertTrue(Global_Compliance.equalsIgnoreCase(driver.getTitle()));
		}
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, FUL.EDITCOMPANYLINK);
		waitforElement(180, PUL.SAVE, driver);
		assertTrue(EditMyCompany_Title.equalsIgnoreCase(driver.getTitle()));

		// Edit My Company Page Verification
		assertTrue(PUL.NAME.isDisplayed());
		assertTrue(PUL.PHONE.isDisplayed());
		assertTrue(PUL.COUNTRY.isDisplayed());
		assertTrue(PUL.ADD1.isDisplayed());
		assertTrue(PUL.ADD2.isDisplayed());
		assertTrue(PUL.CITY.isDisplayed());
		assertTrue(PUL.STATE.isDisplayed());
		assertTrue(PUL.ZIP.isDisplayed());
		assertTrue(PUL.SAVE.isDisplayed());
		assertTrue(PUL.CANCEL.isDisplayed());
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, FUL.VIEWALLUSERSLINK);
		waitforElement(180, PUL.EXPORT, driver);
		assertTrue(MyUsers_Title.equalsIgnoreCase(driver.getTitle()));

		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, FUL.UPDATEBILLINGLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.SUBMITBILINFO, driver);
		assertTrue(UpdateBilling_Title.equalsIgnoreCase(driver.getTitle()));

		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, FUL.BILLINGREPORTSLINK);
		waitforElement(180, FUL.BILLINGREPORTTEXT, driver);
		assertTrue(BillingReports_Title.equalsIgnoreCase(driver.getTitle()));

		if (!VerifyObject(driver, FUL.DOCUMENTCENTRE)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}

		if (Browser.equalsIgnoreCase("IE")) {
			scrollDown(driver);
			click(FUL.GETHELPLINK);
		} else {
			JavaScriptclick(driver, FUL.GETHELPLINK);
		}
		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		// To Handle Additional encryption popup displaying
		if (Browser.equalsIgnoreCase("Firefox")) {
			try {
				Alert javascriptAlert = driver.switchTo().alert();
				javascriptAlert.accept();
			} catch (Exception e) {
			}
		}
		waitforText(180, PUL.VISITHELPCENTERWHATSNEW, driver);
		assertTrue(PUL.VISITHELPCENTERWHATSNEW.isDisplayed());
		driver.close();
		driver.switchTo().window(parentwindow);
		waitforElement(180, PUL.GETHELPLINK, driver);
	}

	public void PortalLinks(String DashBoard_Title, String Configure_Title, String CalculateTaxes_Title,
			String DistributeTaxes_Title, String ManageAdjust_Title, String Lookuprates_Title, String RTE_Title,
			String RTEManagebracket_Title, String Managetaxreturns_Title, String ConfigReport_Title,
			String FillingReport_Title, String TaxabilityReport_Title, String TransactionReport_Title,
			String LegacyTransReport_Title, String CustomerExemption_Title, String ValidationErrors_Title,
			Object ViewAllUsers_Verify, String EditCompany_Title, String ViewAllUsers_Title, String UpdateBilling_Title,
			String BillingReports_Title, String Adapters_Title, String ConnectorsVerify, String Help_Center_Url,
			String Legacyflag, String LegacyReport_Title) throws Exception {
		Robot rob = new Robot();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		CustomerExceptionReport CER = PageFactory.initElements(driver, CustomerExceptionReport.class);
		String parentwindow = driver.getWindowHandle();

		click(PUL.DASHBOARDLINK);
		Thread.sleep(5000);
		waitforText(180, PUL.UPDATEANALYSIS, driver);
		assertTrue(!VerifyObject(driver, PUL.INLINEHELP));
		assertTrue(DashBoard_Title.equalsIgnoreCase(gettitle(driver)));
		click(PUL.CONFIGURELINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (Environment.equalsIgnoreCase("CS") || Environment.equalsIgnoreCase("PROD")) {
			waitforElement(180, CNP.BEGINREMOVAL, driver);
		} else {
			waitforElement(60, CNP.ConfigureCompanyheader, driver);
		}
		Thread.sleep(5000);
		assertTrue(Configure_Title.equalsIgnoreCase(gettitle(driver)));
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, PUL.CALCULATETAXES);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, PUL.INVOICEDEATAILS, driver);
		assertTrue(CalculateTaxes_Title.equalsIgnoreCase(gettitle(driver)));
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, PUL.DISTRIBUTETAX);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, PUL.DISTRIBUTETAXTEXT, driver);
		assertTrue(DistributeTaxes_Title.equalsIgnoreCase(gettitle(driver)));
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, PUL.ERRORREPORT, driver);
		assertTrue(ManageAdjust_Title.equalsIgnoreCase(gettitle(driver)));
		waitforElement(180, PUL.RatesJurisdictions, driver);
		JavaScriptclick(driver, PUL.RatesJurisdictions);
		waitforText(180, PUL.LOOKUPZIP, driver);
		assertTrue(Lookuprates_Title.equalsIgnoreCase(gettitle(driver)));
		if (!RTE_Title.isEmpty()) {
			JavaScriptMousehover(driver, PUL.RTEHover);
			JavaScriptclick(driver, PUL.ManageRetailTaxExtract);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, PUL.MANAGERETAILTAXEXTRACTTEXT, driver);
			assertTrue(RTE_Title.equalsIgnoreCase(gettitle(driver)));
			JavaScriptMousehover(driver, PUL.RTEHover);
			JavaScriptclick(driver, PUL.MANAGEBRACKETSCHEDULE);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforText(180, PUL.MANAGEBRACKETTEXT, driver);
			assertTrue(RTEManagebracket_Title.equalsIgnoreCase(gettitle(driver)));
		}
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		JavaScriptclick(driver, PUL.MANAGETAXRETURNS);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, PUL.FILINGPERIOD, driver);
		assertTrue(Managetaxreturns_Title.equalsIgnoreCase(gettitle(driver)));
		rob.mouseMove(0, 0);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.CONFIGURATIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.CONFIGURATIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (Environment.equalsIgnoreCase("CS") || Environment.equalsIgnoreCase("PROD")) {
			waitforElement(180, CR.CLIENTNAME, driver);
		} else {
			waitforElement(180, PUL.CONFIGURATIONREPORTWITHOUTCOMPANY, driver);
		}
		assertTrue(ConfigReport_Title.equalsIgnoreCase(gettitle(driver)));
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.FILLINGCALENDERREPORTLINK, driver);
		JavaScriptclick(driver, PUL.FILLINGCALENDERREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, CR.CLIENTNAME, driver);
		assertTrue(FillingReport_Title.equalsIgnoreCase(gettitle(driver)));
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TAXABILITYREPORTSLINK, driver);
		JavaScriptclick(driver, PUL.TAXABILITYREPORTSLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (Environment.equalsIgnoreCase("CS") || Environment.equalsIgnoreCase("PROD")) {
			waitforText(180, PUL.TAXABILITYCOMPDD, driver);
		} else {
			waitforText(180, PUL.TAXABILITYREPORTHEADER, driver);
		}
		assertTrue(TaxabilityReport_Title.equalsIgnoreCase(gettitle(driver)));
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (Environment.equalsIgnoreCase("CS") || Environment.equalsIgnoreCase("PROD")) {
			waitforElement(180, TR.TAXPAYERSELECT, driver);
		} else {
			waitforElement(180, PUL.TRANSACTIONREPORTHEADER, driver);
		}
		assertTrue(TransactionReport_Title.equalsIgnoreCase(gettitle(driver)));
		if (Legacyflag.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, PUL.ReportsHover);
			waitforElement(180, TR.LegacyTransactionReportLink, driver);
			JavaScriptclick(driver, TR.LegacyTransactionReportLink);
			waitforElement(180, TR.LEGACYTRANSACTIONREPORTHEADER, driver);
			assertTrue(LegacyReport_Title.equalsIgnoreCase(gettitle(driver)));
		}
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.CUSTOMEREXEMPTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.CUSTOMEREXEMPTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, CER.GENERATEBUTTON, driver);
		assertTrue(CustomerExemption_Title.equalsIgnoreCase(gettitle(driver)));
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.VALIDATIONERRORSREPORTLINK, driver);
		JavaScriptclick(driver, PUL.VALIDATIONERRORSREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, PUL.TAXABILITYCOMPDD, driver);
		assertTrue(ValidationErrors_Title.equalsIgnoreCase(gettitle(driver)));
		waitforElement(180, PUL.SettingsHover, driver);
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.EDITCOMPANYLINK);
		waitforText(180, PUL.NAME, driver);
		Thread.sleep(3000);
		assertTrue(EditCompany_Title.equalsIgnoreCase(gettitle(driver)));
		if (ViewAllUsers_Verify.equals("Yes")) {
			JavaScriptMousehover(driver, PUL.SettingsHover);
			JavaScriptclick(driver, PUL.VIEWALLUSERSLINK);
			waitforElement(180, PUL.CREATENEWUSER, driver);
			assertTrue(ViewAllUsers_Title.equalsIgnoreCase(gettitle(driver)));
			assertTrue(VerifyObject(driver, PUL.MYUSERSTITLE));
			assertTrue(VerifyObject(driver, PUL.CREATENEWUSER));
		}
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.UPDATEBILLINGLINK);
		waitforText(180, PUL.SUBMITBILINFO, driver);
		assertTrue(UpdateBilling_Title.equalsIgnoreCase(gettitle(driver)));
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.BILLINGREPORTSLINK);
		waitforText(180, PUL.BILLINGREPORTHEADING, driver);
		assertTrue(BillingReports_Title.equalsIgnoreCase(gettitle(driver)));
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.ADAPTERSLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, PUL.RETURNTOSETTINGS, driver);
		assertTrue(Adapters_Title.equalsIgnoreCase(gettitle(driver)));
		waitforText(180, "//tr/td[contains(.,'SAP ByDesign *')]", driver);
		if (EnvironmentType.equalsIgnoreCase("QA")) {
			verifyQAConnectors();
		}
		if (EnvironmentType.equalsIgnoreCase("STAGE")) {
			verifySTAGEConnectors();
		}
		if (VerifyObject(driver, PUL.DOCUMENTCENTRE)) {
			JavaScriptclick(driver, PUL.DOCUMENTCENTRE);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if ((Browser.equalsIgnoreCase("Firefox")) || (Browser.equalsIgnoreCase("Chrome"))) {
			JavaScriptclick(driver, PUL.GETHELPLINK);
		} else {
			scrollDown(driver);
			click(PUL.GETHELPLINK);
		}
		Thread.sleep(5000);

		// To Handle Additional encryption popup displaying
		if (Browser.equalsIgnoreCase("Firefox")) {
			try {
				Alert javascriptAlert = driver.switchTo().alert();
				javascriptAlert.accept();
			} catch (Exception e) {
			}
		}
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		if (!Help_Center_Url.isEmpty()) {
			Thread.sleep(5000);
			System.out.println(gettitle(driver));
			/*assertTrue(Help_Center_Url.equalsIgnoreCase(gettitle(driver)));
			//if (VerifyObject(driver, PUL.CUSTOMERTOPICS)) {
				click(PUL.CUSTOMERTOPICS);
			}

			waitforElement(180, PUL.VISITHELPCENTERFAQS, driver);
			click(PUL.VISITHELPCENTERFAQS);
			Thread.sleep(2000);
			waitforElement(180, PUL.VISITHELPCENTERWHATSNEW, driver);
			click(PUL.VISITHELPCENTERWHATSNEW);
			Thread.sleep(5000);*/
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
			driver.close();
			Thread.sleep(5000);
			driver.switchTo().window(parentwindow);
			Thread.sleep(5000);
		} else {
			driver.close();
			Thread.sleep(2000);
			driver.switchTo().window(parentwindow);
			if (Browser.equalsIgnoreCase("IE")) {
				scrollUp(driver);
			}
		}
	}

	public void verifyQAConnectors() throws Exception {
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'SAP ByDesign *')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QASAPByDesign") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPSAPByDesign") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Microsoft Dynamics GP')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QAMicrosoftDynamicsGP") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPMicrosoftDynamicsGP") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Microsoft Dynamics AX')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QAMicrosoftDynamicsAX") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPMicrosoftDynamicsAX") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Sage 100')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QASage100")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QALPSage100") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Sage 300')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QASage300")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QALPSage300") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Netsuite')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QANetsuite")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QALPNetsuite") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Magento')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QAMagento")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QALPMagento") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'None(online rate file) (BYOC) *')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QANone(onlineratefile)(BYOC)") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPNone(onlineratefile)(BYOC)") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'None (online calculation) (BYOC)')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QANone(onlinecalculation)(BYOC)") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPNone(onlinecalculation)(BYOC)") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'QuickBooks')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QAQuickBooks") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPQuickBooks") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Standard QuickBooks')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QAStandardQuickBooks") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPStandardQuickBooks") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Microsoft Dynamics NAV')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QAMicrosoftDynamicsNAV") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPMicrosoftDynamicsNAV") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'None (Standard online calculation) (BYOC) *')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QANone(Standardonlinecalculation)(BYOC)")
						+ "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPNone(Standardonlinecalculation)(BYOC)") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'S4HANA')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QAS4HANA")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QALPS4HANA") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Oracle TaxLink')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QAOracleTaxLink") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPOracleTaxLink") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'JDEdwards')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QAJDEdwards")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QALPJDEdwards") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Full')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QAFull")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QALPFull") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Standard *')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QAStandard")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QALPStandard") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Acumatica')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QAAcumatica")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("QALPAcumatica") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Salesforce Commerce Cloud')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QASalesforceCommerceCloud") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("QALPSalesforceCommerceCloud") + "')]"));

	}

	public void verifySTAGEConnectors() throws Exception {
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'SAP ByDesign *')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGESAPByDesign") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPSAPByDesign") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Microsoft Dynamics GP')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGEMicrosoftDynamicsGP") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPMicrosoftDynamicsGP") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Microsoft Dynamics AX')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGEMicrosoftDynamicsAX") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPMicrosoftDynamicsAX") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Sage 100')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGESage100")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGELPSage100") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Sage 300')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGESage300")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGELPSage300") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Netsuite')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGENetsuite")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGELPNetsuite") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Magento')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGEMagento")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGELPMagento") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'None(online rate file) (BYOC) *')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGENone(onlineratefile)(BYOC)") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPNone(onlineratefile)(BYOC)") + "')]"));
		assertTrue(VerifyObject(driver, "//tr/td[contains(.,'None (online calculation) (BYOC)')]/../td[contains(.,'"
				+ CONNECTOR_PROPERTIES.getProperty("STAGENone(onlinecalculation)(BYOC)") + "')]/../td[contains(.,'"
				+ CONNECTOR_PROPERTIES.getProperty("STAGELPNone(onlinecalculation)(BYOC)") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'QuickBooks')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGEQuickBooks") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPQuickBooks") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Standard QuickBooks')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGEStandardQuickBooks") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPStandardQuickBooks") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Microsoft Dynamics NAV')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGEMicrosoftDynamicsNAV") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPMicrosoftDynamicsNAV") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'None (Standard online calculation) (BYOC) *')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGENone(Standardonlinecalculation)(BYOC)")
						+ "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPNone(Standardonlinecalculation)(BYOC)") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'S4HANA')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGES4HANA")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGELPS4HANA") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Oracle TaxLink')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGEOracleTaxLink") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPOracleTaxLink") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'JDEdwards')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGEJDEdwards") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPJDEdwards") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Full')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGEFull")
						+ "')]/../td[contains(.,'" + CONNECTOR_PROPERTIES.getProperty("STAGELPFull") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Standard *')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGEStandard") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPStandard") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Acumatica')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGEAcumatica") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPAcumatica") + "')]"));
		assertTrue(VerifyObject(driver,
				"//tr/td[contains(.,'Salesforce Commerce Cloud')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGESalesforceCommerceCloud") + "')]/../td[contains(.,'"
						+ CONNECTOR_PROPERTIES.getProperty("STAGELPSalesforceCommerceCloud") + "')]"));

	}

}
