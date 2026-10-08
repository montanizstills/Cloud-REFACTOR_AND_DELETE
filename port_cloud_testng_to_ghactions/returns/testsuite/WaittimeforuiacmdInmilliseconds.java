/*###################################################################################################
Name: WaittimeforuiacmdInmilliseconds.java 
Description: Increased wait time (D:\Program Files (x86)\Vertex Returns SQL\VTMS.Windows.ReturnsServicingAgent.exe.config), look for <add key="WaitTimeForUIACmdInMilliseconds" value="100" />Change value from 100 (.1s) to 3000 (3s)
and Returns should be generated succssfully
//Ticket Number CD-7419
Author: Venkat Reddy Kandula
Date Created:04/03/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class WaittimeforuiacmdInmilliseconds extends CommonFunctions {

	public WaittimeforuiacmdInmilliseconds() throws Exception {
		super();
	}

	private WebDriver driver;

	String BPOclientname = "ReturnsAutomationWaitTimeTest" + RND()+ "@simulator.amazonses.com";

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("STAGE") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-7684
	@Test(priority = 0)
	public void doreturnsautomationwaittime() throws Exception {

		Open(driver, cpaurl);
		userRegistration.UserLogin("CPACPABPOAutomation@simulator.amazonses.com@simulator.amazonses.com", "Password!1");
		cpaPortal.CPA_EnrollNewClient("GAdjustmentFileHelp.pdf", "", BPOclientname, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "555 555 5552", "Premium",
				"Tax Calculations and Returns", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123", "BPOFN" + RND(), "BPO" + RND(), "Yes",
				"Yes", "Yes", "Yes");
		System.out.println(BPOclientname);
		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");
		Open(driver, url);

		configuration.AddTaxPayer("United States", "PQA01", "PRIMEQATest", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("PRIMEQATest", "Pennsylvania", "PA", "PA", "", "01/01/2017", "36 Railroad Street",
				"Linfield", "", "19468", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Maryland",
				"MD Sales and Use Tax Return Form 202", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		userRegistration.Logout();
		Open(driver, cpaurl);
		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");
		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");
		Open(driver, url);
		configuration.GoLive("PRIMEQATest");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		premiumCustomers.onboardCustomer(BPOclientname, "PRIMEQATest", xGetCustomizedMon(-10, 0));

		userRegistration.Logout();

		Open(driver, cpaurl);

		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");
		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");
		Open(driver, url);

		transactions.UploadAdjustmentFile("CD_6004_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_6004_CurrentFilingPeriod_suc", "Yes", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112014", "", "",
				"United States", "Maryland", "21201", "", "5000", "1", "", "", "", "Seller", "", "", "");

		transactions.syncTime();

		userRegistration.Logout();

		Open(driver, cpaurl);

		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");
		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");
		Open(driver, url);

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0));

		userRegistration.Logout();

		Open(driver, cpaurl);

		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");

		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");

		Open(driver, url);

		transactions.UploadAdjustmentFile("CD_17258_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_17258_CurrentFilingPeriod_suc", "Yes", "");

		transactions.syncTime();

		userRegistration.Logout();

		Open(driver, cpaurl);

		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");
		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");
		Open(driver, url);

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-17260
	@Test(priority = 1)
	public void dozeroReport() throws Exception {

		Open(driver, cpaurl);
		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");
		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");
		Open(driver, url);

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"5742", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");

		configuration.AddLocations("PRIMEQATest", "Georgia", "VGAL01", "VGA Location", "", "01/01/2017",
				"8821 Ga 15 Hwy N", "Warthen", "", "31094", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Alabama",
				"AL State Seller's Use Tax Return 2620", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");

		configuration.AddZeroReport("PRIMEQATest", "Alabama", "100 per page", "Manufacturing CU", "7001", "State");

		configuration.EditZeroReport("PRIMEQATest", "Manufacturing CU", "7001", "Non-State");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		userRegistration.Logout();

		Open(driver, cpaurl);

		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");

		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");

		Open(driver, url);

		reports.configurationReport("PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		reports.adminconfigurationReport(BPOclientname, "PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, cpaurl);

		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");

		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");

		Open(driver, url);

		transactions.UploadAdjustmentFile("CD_17258_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_17258_CurrentFilingPeriod_suc", "Yes", "");

		transactions.syncTime();

		userRegistration.Logout();

		Open(driver, cpaurl);

		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");
		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");
		Open(driver, url);

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18089
	@Test(priority = 2)
	public void dolocseqnumberTest() throws Exception {

		Open(driver, cpaurl);
		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");
		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");
		Open(driver, url);
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois", "IL Sales/Use Tax Return/E911 ST-1",
				"54654", "Mail a paper return", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois", "IL Sales/Use Tax Prepayment Return",
				"58795", "Web File", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois",
				"IL-CHICAGO Titled PP 8400R - Monthly", "88422", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");

		configuration.addLocseqnumber("PRIMEQATest", "Illinois", "100 per page", "900");

		configuration.editLocseqnumber("PRIMEQATest", "000");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		userRegistration.Logout();

		Open(driver, cpaurl);
		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");

		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");

		Open(driver, url);

		reports.configurationReport("PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		reports.adminconfigurationReport(BPOclientname, "PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, cpaurl);

		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");

		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");

		Open(driver, url);

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112014", "", "", "", "",
				"", "", "1000", "1", "", "", "140010000", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112015", "", "", "", "",
				"", "", "2000", "1", "", "", "140014352", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112016", "", "", "", "",
				"", "", "3000", "1", "", "", "140014609", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112016", "", "", "", "",
				"", "", "4000", "1", "", "", "140014638", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112017", "", "", "", "",
				"", "", "4000", "1", "", "", "140014490", "", "", "", "");

		transactions.syncTime();

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18118
	@Test(priority = 3)
	public void douploadvalidinvaliddata() throws Exception {

		Open(driver, cpaurl);
		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");

		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");

		Open(driver, url);

		configuration.uploadLocseqNumber("PRIMEQATest", "CUT_Distribute_Credits", ".csv", "Yes", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "GAdjustmentFileHelp", ".pdf", "", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "ReconciliationReport", ".zip", "", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "InternationalReturns", ".txt", "", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "LocationSeqNumberXLSFile", ".xls", "", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "LocationSeqNumberXLSXFile", ".xlsx", "", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "ValidNonLocationsSettingsValuesFormat", ".csv", "", "Yes", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "ValidInvalidNonLocationsSettingsValuesFormat", ".csv", "",
				"Yes", "Yes");
		configuration.uploadLocseqNumber("PRIMEQATest", "InvalidNonLocationsSettingsValuesFormat", ".csv", "", "",
				"Yes");

		configuration.viewuploadLocseqNumberHistory("PRIMEQATest", "ValidNonLocationsSettingsValuesFormat", ".csv");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-19656
	@Test(priority = 4)
	public void generatereturnsafterremovetaxpayer() throws Exception {

		Open(driver, cpaurl);
		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");

		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");

		Open(driver, url);

		configuration.Removetaxpayer("PRIMEQATest", xGetCustomizedDate(0, 1, 0), xGetCustomizedDate(0, 1, 0));

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-19723
	@Test(priority = 5)
	public void domdreturns() throws Exception {

		Open(driver, cpaurl);
		userRegistration.UserLogin("CPABPOAutomation@simulator.amazonses.com", "Password!1");

		cpaPortal.CPA_VerifyManageClients(BPOclientname, "ENROLLED CLIENTS");

		Open(driver, url);

		manageTaxreturns.downloadReturns("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "", "");

		userRegistration.Logout();

	}

	@AfterTest
	public void closeBrowser() throws Exception {
		quitDriver(driver);

	}

}
