/*###################################################################################################
Name: PaymentRequestReport.java 
Description: Change Label from Premium Reconciliation Report to Payment Request Export
//Ticket Number CD-14106
Author: Venkat Reddy Kandula
Date Created:11/20/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class paymentrequestReport extends CommonFunctions {

	public paymentrequestReport() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "PaymentRequestReport" + RND() + "@simulator.amazonses.com";
	String clientname = "PaymentRequestReportTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {

		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-14974
	@Test(priority = 0)
	public void doPaymentRequestReport() throws Exception {

		userRegistration.UserLogin(adminusername, adminpassword);

		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, clientname, username, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Premium", "", POD);
		userRegistration.AddtionalFeatures("", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");
		System.out.println("User Name is:" + username);

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "USA", "USA Company", "456789012", "6310 Clift Street", "Shreveport",
				"Louisiana", "71105", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Alabama", "AL-ST CLAIR COUNTY Tax Return",
				"12335678", "Web File", "Occasional", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "California", "CA Prepaid MTS Report", "1122",
				"Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Florida", "FL Prepaid Wireless E911 Fee Return",
				"1113", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Georgia", "GA Prepaid Wireless Fee Return",
				"1114", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "North Dakota",
				"ND ST - Sales and Use Tax Return", "52120", "Mail a paper return", "Semi-annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Pennsylvania", "PA Prepayment Form for PA-3",
				"54654", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Vermont", "VT Sales/Use Tax Return (SU-451)",
				"12345678901", "Mail a paper return", "Quarterly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "West Virginia",
				"WV Sales and Use Tax Return CST-200CU", "85251", "Mail a paper return", "Annually", "", "", "", "", "",
				"", "", "", "", "");

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.GoLive("USA Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "USA Company", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_14106_PreviousFilingPeriod", "Invoice", "Seller",
				"CD_14107_PreviousFilingPeriod_suc", "Yes", "");

		transactions.syncTime();

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.GenerateReturnsfromAdmin(clientname, "USA Company (USA)", xGetCustomizedMon(-2, 0),
				"PA Sales/Use/Hotel Tax PA-3");

		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "USA Company (USA)", xGetCustomizedMon(-2, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_14106_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_6004_CurrentFilingPeriod_suc");

		transactions.UploadAdjustmentFile("OccasionalReturnPurchaseOrder", "Purchase Order", "Buyer",
				"OccasionalReturnPurchaseOrder_suc");

		transactions.syncTime();

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexportlabel(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-15138
	@Test(dependsOnMethods = { "doPaymentRequestReport" }, priority = 1)
	public void doPaymentRequestReportforus() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0),
				"USA_PremiumReturns_Reconciliation");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-15139
	@Test(priority = 2)
	public void doPaymentRequestReportforcanada() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.IncreasePartitions(clientname, "3");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		configuration.AddTaxPayer("Canada", "CAN", "Canada Company", "515316542313243", "800 Griffiths Way!",
				"Vancouver!#@#$%^&*", "British Columbia", "V6B 6G1", "", "");
		configuration.AddJurisdictions("Canada Company", "CANADA", "Canada", "CN GST/HST Return (Non personalized)",
				"557777778", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("Canada Company", "CANADA", "Canada", "CN-BRITISH COLUMBIA Provincial Sales Tax",
				"55777778", "Web File", "Quarterly", "", "", "4545544554", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("Canada Company", "CANADA", "Canada", "CN-QUEBEC QST Return", "5577778",
				"Web File", "Annually", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("Canada Company", "CANADA", "Canada", "CN-MANITOBA Vendor's Return", "5577778",
				"Web File", "Semi-annually", "", "", "", "", "", "", "", "", "", "");

		configuration.ConfigPush("Canada Company");
		configuration.VerifyConfigPush("Canada Company");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "Canada Company", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		transactions.UploadAdjustmentFile("Canada_Transactions_Negative", "Invoice", "Canadian Transactions",
				"Canada_Transactions_Negative_suc");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -2 with negative transactions

		manageCustomers.GenerateReturnsfromAdmin(clientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		// Generate Returns for Current Month -1 with negative transactions

		manageTaxreturns.Returnsgenerate("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		transactions.UploadAdjustmentFile("Canada_Transactions_Positive", "Invoice", "Canadian Transactions",
				"Canada_Transactions_Positive_suc");
		transactions.syncTime();
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		// Generate Returns for Current Month -1 with positive transactions

		manageTaxreturns.Returnsgenerate("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "Canada Company (CAN)", xGetCustomizedMon(-1, 0),
				"CAN_PremiumReturns_Reconciliation");

	}

	// https://vertexinc.atlassian.net/browse/CD-15140
	@Test(priority = 3)
	public void doPaymentRequestReportforuscanada() throws Exception {

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "USCAN", "Combined Company", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"5742", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "North Dakota",
				"ND ST - Sales and Use Tax Return", "52120", "Mail a paper return", "Semi-annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "54654", "Mail a paper return", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Pennsylvania",
				"PA Prepayment Form for PA-3", "54654", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount",
				"1.00", "", "", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "South Carolina",
				"SC Sales and Use Tax Return ST-3", "52205", "Mail a paper return", "Annually", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "South Dakota", "SD Sales/Use Tax Return",
				"55214", "Mail a paper return", "Semi-annually", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Tennessee",
				"TN Sales and Use Tax Return SLS 450", "85120", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Vermont",
				"VT Sales/Use Tax Return (SU-451)", "12345678901", "Mail a paper return", "Quarterly", "", "", "", "",
				"", "", "", "", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "West Virginia",
				"WV Sales and Use Tax Return CST-200CU", "85251", "Mail a paper return", "Annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Wisconsin",
				"WI State/County Sales/Use Tax ST-12", "82551", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("Combined Company", "CANADA", "Canada", "CN GST/HST Return (Non personalized)",
				"557777778", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("Combined Company", "CANADA", "Canada",
				"CN-BRITISH COLUMBIA Provincial Sales Tax", "55777778", "Web File", "Quarterly", "", "", "885514587",
				"", "", "", "", "", "", "");

		configuration.AddJurisdictions("Combined Company", "CANADA", "Canada", "CN-QUEBEC QST Return", "5577778",
				"Web File", "Annually", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("Combined Company", "CANADA", "Canada", "CN-MANITOBA Vendor's Return", "5577778",
				"Web File", "Semi-annually", "", "", "", "", "", "", "", "", "", "");

		configuration.ConfigPush("Combined Company");
		configuration.VerifyConfigPush("Combined Company");

		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "Combined Company", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		transactions.UploadAdjustmentFile("CD-7542_CurrentFiling_Invoice_US", "Invoice", "Seller",
				"CD-7542_CurrentFiling_Invoice_US_suc");
		transactions.UploadAdjustmentFile("Canada_Transactions_Negative_Combined", "Invoice", "Canadian Transactions",
				"Canada_Transactions_Negative_Combined_suc");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -2

		manageCustomers.GenerateReturnsfromAdmin(clientname, "Combined Company (USCAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "Combined Company (USCAN)",
				xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		// Generate Returns for Current Month -1

		manageTaxreturns.Returnsgenerate("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company (USCAN)", xGetCustomizedMon(-1, 0),
				"USCAN_PremiumReturns_Reconciliation");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-15141
	@Test(dependsOnMethods = { "doPaymentRequestReportforus" }, priority = 4)
	public void dontfileOn() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		manageCustomers.DoNotFileReturns("PA Sales/Use/Hotel Tax PA-3", "USA Company (USA)");
		manageCustomers.DoNotFileReturns("FL Sales/Use Tax Return DR-15EZ", "USA Company (USA)");
		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		// Returns that are marked Do Not File should not display on the Premium
		// reconciliation report
		premiumCustomers.PremiumReconReport(clientname, "USA Company (USA)");
		// userRegistration.Logout();

		// userRegistration.UserLogin(adminusername, adminpassword);
		// Customer Name, Customer Number, Taxpayer:, Company Name, Filing Period:,
		// Default to current filing period,State,Return Form should display in the
		// PremiumDonotFileReport.csv file
		// manageCustomers.DownloadAllReturnfilesadmin();
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-15142
	@Test(dependsOnMethods = { "dontfileOn" }, priority = 5)
	public void dontfileOff() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		manageCustomers.DoNotFileReturns("PA Sales/Use/Hotel Tax PA-3", "USA Company (USA)");
		manageCustomers.DoNotFileReturns("FL Sales/Use Tax Return DR-15EZ", "USA Company (USA)");
		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		// Returns that are marked Do Not File should not display on the Premium
		// reconciliation report
		premiumCustomers.PremiumReconReport(clientname, "USA Company (USA)");
		// userRegistration.Logout();

		// userRegistration.UserLogin(adminusername, adminpassword);
		// Customer Name, Customer Number, Taxpayer:, Company Name, Filing Period:,
		// Default to current filing period,State,Return Form should display in the
		// PremiumDonotFileReport.csv file
		// manageCustomers.DownloadAllReturnfilesadmin();
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-15143
	@Test(dependsOnMethods = { "dontfileOff" }, priority = 6)
	public void occasionalTaxdue() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0),
				"USA_PremiumReturns_Reconciliation");

	}

	// https://vertexinc.atlassian.net/browse/CD-15144
	@Test(dependsOnMethods = { "occasionalTaxdue" }, priority = 7)
	public void paymentrequestdifferentfilingfrequency() throws Exception {

		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0),
				"USA_PremiumReturns_Reconciliation");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-15145
	@Test(dependsOnMethods = { "paymentrequestdifferentfilingfrequency" }, priority = 8)
	public void paymentrequestreportcomparision() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0),
				"USA_PremiumReturns_Reconciliation");

		manageCustomers.paymentrequestexport(clientname, "Canada Company (CAN)", xGetCustomizedMon(-1, 0),
				"CAN_PremiumReturns_Reconciliation");

		manageCustomers.paymentrequestexport(clientname, "Combined Company (USCAN)", xGetCustomizedMon(-1, 0),
				"USCAN_PremiumReturns_Reconciliation");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-15232
	@Test(dependsOnMethods = { "doPaymentRequestReport" }, priority = 9)
	public void paymentrequestpdfmatch() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.downloadReturns("USA Company (USA)", xGetCustomizedMon(-1, 0), "", "");

		manageTaxreturns.CreditReport("USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		manageTaxreturns.ReconciliationReport("USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-17908
	@Test(dependsOnMethods = { "doPaymentRequestReportforuscanada" }, priority = 10)
	public void combineddontfileOnoff() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		manageCustomers.DoNotFileReturns("GA Sales/Use Tax Report ST-3", "Combined Company (USCAN)");
		manageCustomers.DoNotFileReturns("CN GST/HST Return (Non personalized)", "Combined Company (USCAN)");
		manageTaxreturns.Returnsgenerate("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		// Canada and Georgia Return from should not display in the Payment request
		// export form
		premiumCustomers.PremiumReconReport(clientname, "Combined Company (USCAN)");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		manageCustomers.DoNotFileReturns("GA Sales/Use Tax Report ST-3", "Combined Company (USCAN)");
		manageCustomers.DoNotFileReturns("CN GST/HST Return (Non personalized)", "Combined Company (USCAN)");
		manageTaxreturns.Returnsgenerate("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		// Canada and Georgia Return from should display in the Payment request export
		// form
		premiumCustomers.PremiumReconReport(clientname, "Combined Company (USCAN)");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-17906
	@Test(dependsOnMethods = { "doPaymentRequestReportforcanada" }, priority = 11)
	public void canadadontfileOnoff() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		manageCustomers.DoNotFileReturns("CN GST/HST Return (Non personalized)", "Canada Company (CAN)");
		manageTaxreturns.Returnsgenerate("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		// Canada Return from should display in the Payment request export form when
		// having only one return form
		premiumCustomers.PremiumReconReport(clientname, "Canada Company (CAN)");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		manageCustomers.DoNotFileReturns("CN GST/HST Return (Non personalized)", "Canada Company (CAN)");
		manageTaxreturns.Returnsgenerate("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		// Canada Return from should display in the Payment request export form
		premiumCustomers.PremiumReconReport(clientname, "Canada Company (CAN)");
		userRegistration.Logout();
	}

	@AfterTest
	public void closebrowser() throws Exception {
		quitDriver(driver);

	}

}
