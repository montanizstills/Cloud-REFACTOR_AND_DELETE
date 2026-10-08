/*###################################################################################################
Name: queueItemnotdisplayforReports.java 
Description: Verify prepayments are generated  successfully and has no queue item failure.
//CD-8453, 
Author: Venkat Reddy Kandula
Date Created:04/22/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class QueueItems extends CommonFunctions {

	public QueueItems() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "QueueItems" + RND() + "@simulator.amazonses.com";
	String clientname = "QueueItemsTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// Verify prepayments are generated successfully and has no queue item failure
	// https://vertexinc.atlassian.net/browse/CD-8761
	@Test(priority = 0)
	public void doprepaymentqueueitemTest() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, clientname, username, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Professional", "", POD);
		userRegistration.AddtionalFeatures("Rate File,Retail Tax Extract,Invoice", "", "ACH Credit", "Yes", "", "", "",
				"", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "PQA01", "PRIMEQATest", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "VertexPrepayments@2020", "lastname");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Prepayment Form for PA-3",
				"12335679", "Web File", "Monthly", "", "", "", "", "Flat Amount", "5", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Maryland",
				"MD Sales and Use Tax Return Form 202", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "Yes");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.GoLive("PRIMEQATest");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.syncTime();

		prepayments.EnterPrepayments("PRIMEQATest (PQA01)", xGetCustomizedMon(0, 0), "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "100", "");
		prepayments.GeneratePrepayment("PRIMEQATest (PQA01)", xGetCustomizedMon(0, 0));
		prepayments.DownloadPrepayment("PRIMEQATest (PQA01)", xGetCustomizedMon(0, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		adminPortal.AdminErrorMonitor(clientname);
		userRegistration.Logout();
	}

	// Verify returns are generated successfully and has no queue item failure
	// https://vertexinc.atlassian.net/browse/CD-8764
	@Test(priority = 1)
	public void doreturnsqueueitemTest() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Florida", "FL Sales/Use Tax Return DR-15",
				"12335679", "Web File", "Monthly", "", "", "", "", "Flat Amount", "15", "", "", "", "");
		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_8453_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_8453_CurrentFilingPeriod_suc");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112014", "", "",
				"United States", "Maryland", "21201", "", "8000", "1", "", "", "", "Seller", "", "", "");

		transactions.syncTime();

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		adminPortal.AdminErrorMonitor(clientname);

		userRegistration.Logout();

	}

	// Generate different transactions reports and verify no queue item failure
	// https://vertexinc.atlassian.net/browse/CD-8771
	@Test(priority = 2)
	public void dotransactionsreportqueueitemTest() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactionReport.TransactionDetailReport("PRIMEQATest (PQA01)", xGetCustomizedPreviousMon(-1, 0),
				xGetCustomizedDate(0, 0, 0));

		transactionReport.TransactionSummaryReport("PRIMEQATest (PQA01)", xGetCustomizedPreviousMon(-1, 0),
				xGetCustomizedDate(0, 0, 0));

		transactionReport.CreateNewCustomTransactionDeatilReportTemplate("Admin Destination City", "QueueItemReport",
				"Template01");

		transactionReport.CustomTransactionReport("PRIMEQATest (PQA01)", xGetCustomizedPreviousMon(-1, 0),
				xGetCustomizedDate(0, 0, 0), "Template01");

		transactionReport.VerifyTransReport("PRIMEQATest (PQA01)", xGetCustomizedPreviousMon(-1, 0),
				xGetCustomizedDate(0, 0, 0), "Transaction Details Report");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		adminPortal.AdminErrorMonitor(clientname);

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-17259
	@Test(priority = 3)
	public void dozeroReport() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"5742", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");

		configuration.AddLocations("PRIMEQATest", "Georgia", "VGAL01", "VGA Location", "", "01/01/2017",
				"8821 Ga 15 Hwy N", "Warthen", "", "31094", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Alabama",
				"AL State Seller's Use Tax Return 2620", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");

		configuration.AddZeroReport("PRIMEQATest", "Alabama", "100 per page", "Farm Equipment CU", "7001", "State");

		configuration.EditZeroReport("PRIMEQATest", "Farm Equipment CU", "7001", "Non-State");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		reports.configurationReport("PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		reports.adminconfigurationReport(clientname, "PRIMEQATest (PQA01)");

		changeSubscription.UpgradetoTI(clientname, username, Environment);

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_6004_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_6004_CurrentFilingPeriod_suc", "Yes", "");

		transactions.syncTime();

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(username + ".ti", "Password!1");
		testInstance.CopyTaxcentralCompanyData("", "PRIMEQATest", "ChangeReportBeforeUpdation.csv", "", "", "",
				"527!@#$%^&*()_',?[{]}-<~>TI");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username + ".ti", "Password!1");

		configuration.verifyTIZeroReport("PRIMEQATest", "Alabama");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18894
	@Test(priority = 4)
	public void dozeroreportmulttaxpayer() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.IncreasePartitions(clientname, "2");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "USA", "USA Company", "456789012", "6310 Clift Street", "Shreveport",
				"Louisiana", "71105", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"5742", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");
		configuration.AddLocations("USA Company", "Georgia", "VGAL01", "VGA Location", "", "01/01/2017",
				"8821 Ga 15 Hwy N", "Warthen", "", "31094", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "North Dakota",
				"ND ST - Sales and Use Tax Return", "52120", "Mail a paper return", "Semi-annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("USA Company", "North Dakota", "VNDL01", "VND Location", "", "01/01/2017",
				"4262 110th Ave SW", "Dickinson", "", "58601-9280", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"54654", "Mail a paper return", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Pennsylvania", "PA Prepayment Form for PA-3",
				"54654", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");
		configuration.AddLocations("USA Company", "Pennsylvania", "VPAL01", "VPAL01", "", "01/01/2017",
				"1006 W 9th Ave", "King Of Prussia", "", "19406-1206", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Alabama",
				"AL State Seller's Use Tax Return 2620", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");

		configuration.AddZeroReport("USA Company", "Alabama", "100 per page", "Auto SU", "7001", "State");

		configuration.EditZeroReport("USA Company", "Auto SU", "1458", "State");

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		reports.configurationReport("USA Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		reports.adminconfigurationReport(clientname, "USA Company (USA)");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_17258_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_17258_CurrentFilingPeriod_suc", "Yes", "");

		transactions.syncTime();

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18088
	@Test(priority = 5)
	public void dolocseqnumberTest() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois", "IL Sales/Use Tax Return/E911 ST-1",
				"54654", "Mail a paper return", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois", "IL Sales/Use Tax Prepayment Return",
				"58795", "Web File", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois",
				"IL-CHICAGO Titled PP 8400R - Monthly", "88422", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");

		configuration.addLocseqnumber("PRIMEQATest", "Illinois", "100 per page", "000");

		configuration.editLocseqnumber("PRIMEQATest", "900");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		reports.configurationReport("PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		reports.adminconfigurationReport(clientname, "PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

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

	// https://vertexinc.atlassian.net/browse/CD-18117
	@Test(priority = 6)
	public void douploadvalidinvaliddata() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

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

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.ChangeSubscription(clientname, "Premium", "Rate File", "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-19722
	@Test(priority = 7)
	public void domdreturns() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.downloadReturns("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "", "");

		userRegistration.Logout();

	}

	@AfterTest
	public void closeBrowser() throws Exception {
		quitDriver(driver);

	}
}
