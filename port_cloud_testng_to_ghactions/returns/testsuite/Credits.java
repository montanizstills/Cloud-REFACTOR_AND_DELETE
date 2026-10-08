/*###################################################################################################
Name: Credits.java 
Description: Verify the credit in returns pdf, credit report and reconciliation report with different combinations and validations and Always Generate Reconciliation Report for US and Canada
//Ticket Number CD-7542
//CD-17695 
Author: Venkat Reddy Kandula
Date Created:04/27/2020
Date Modified:04/28/2020
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class Credits extends CommonFunctions {

	public Credits() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "Credits" + RND() + "@simulator.amazonses.com";
	String clientname = "CreditsTest" + RND();
	
	

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("STAGE") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-20477
	@Test(priority = 0)
	public void doPremiumregistrationTest() throws Exception {

		userRegistration.UserLogin(adminusername, adminpassword);

		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, clientname, username, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Premium", "", POD);
		userRegistration.AddtionalFeatures("INDIRECTTAXINTELLIGENCE", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");
		System.out.println("User Name is:" + username);

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactionReport.IndirectTaxIntelligence();

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-20870
	@Test(priority = 1)
	public void doPremiumregistrationTestAdmin() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		String usernameronly = "ITIUser" + RND() + "@simulator.amazonses.com";

		userRegistration.CreateNewUser("TESTFN" + RND(), "TESTLN" + RND(), usernameronly, "5555555555555", "Admin",
				"Read Only", "", "", "", "Admin", "Password!1", "Password!1");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(usernameronly, "Password!1");

		transactionReport.IndirectTaxIntelligence();

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		String usernameradminonly = "ITIAdminUser" + RND() + "@simulator.amazonses.com";

		userRegistration.CreateNewUser("ITIFN" + RND(), "ITILN" + RND(), usernameradminonly, "5555555555555", "Admin",
				"Admin", "", "", "", "User", "Password!1", "Password!1");
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(usernameradminonly, "Password!1");

		transactionReport.IndirectTaxIntelligence();

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-21063
	@Test(priority = 2)
	public void AdminCreateNewUserITI() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		String usernameradmin = "ITIAdmin" + RND() + "@simulator.amazonses.com";
		cpaPortal.AdminCreateNewUserNew(clientname, usernameradmin,"TESTLN" + RND(),usernameradmin , "111111111111111",
				"Admin", "", "", "", "Admin", "Password!1");
		String usernamerreadonly = "ITIAdminuser" + RND() + "@simulator.amazonses.com";
		cpaPortal.AdminCreateNewUserNew(clientname,usernamerreadonly, "TESTLN" + RND(), usernamerreadonly,
				"111111111111111", "Read Only", "", "", "", "User", "Password!1");
		
	}

	// https://vertexinc.atlassian.net/browse/CD-18516
	@Test(priority = 3)
	public void dosinglereturnformgeneration() throws Exception {
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
		configuration.AddJurisdictions("USA Company", "United States", "South Carolina",
				"SC Sales and Use Tax Return ST-3", "52205", "Mail a paper return", "Annually", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddLocations("USA Company", "South Carolina", "VSCL01", "VSC Location", "", "01/01/2017",
				"1013 Mount Vernon Dr", "North Myrtle Beach", "", "29582", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "South Dakota", "SD Sales/Use Tax Return",
				"55214", "Mail a paper return", "Semi-annually", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("USA Company", "South Dakota", "VSD01", "VSD Location", "", "01/01/2017",
				"431 N Perry Ln", "Harrisburg", "", "57032-2355", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Tennessee",
				"TN Sales and Use Tax Return SLS 450", "85120", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("USA Company", "Tennessee", "VTNL01", "VTN Location", "", "01/01/2017",
				"8818 S Poplar Lake Dr", "Germantown", "", "38138-7727", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Vermont", "VT Sales/Use Tax Return (SU-451)",
				"12345678901", "Mail a paper return", "Quarterly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("USA Company", "Vermont", "VVTL01", "VVT Location", "", "01/01/2017",
				"176 Center Rd", "Morrisville", "", "05661-8577", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "West Virginia",
				"WV Sales and Use Tax Return CST-200CU", "85251", "Mail a paper return", "Annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("USA Company", "West Virginia", "VWVL01", "VWV Location", "", "01/01/2017",
				"84 Central Ave", "Romney", "", "26757-1202", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Wisconsin",
				"WI State/County Sales/Use Tax ST-12", "82551", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");

		configuration.AddLocations("USA Company", "Wisconsin", "VWIL01", "VWI Location", "", "01/01/2017",
				"8818 Greenview Ln", "Greendale", "", "53129-1551", "", "");

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");

		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		configuration.GoLive("USA Company");
		userRegistration.Logout();

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
		configuration.AddLocations("Canada Company", "Canada", "CA", "Canada Location", "", "01/01/2017",
				"800 Griffiths Way", "Vancouver", "British Columbia", "V6B 6G1", "", "");

		configuration.AddJurisdictions("Canada Company", "CANADA", "Canada", "CN-BRITISH COLUMBIA Provincial Sales Tax",
				"55777778", "Web File", "Quarterly", "", "", "4545544554", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("Canada Company", "CANADA", "Canada", "CN-QUEBEC QST Return", "5577778",
				"Web File", "Annually", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("Canada Company", "CANADA", "Canada", "CN-MANITOBA Vendor's Return", "5577778",
				"Web File", "Semi-annually", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("Canada Company", "CANADA", "Canada", "CN-SASKATCHEWAN Provincial Sales Tax",
				"5577744", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.ConfigPush("Canada Company");
		configuration.VerifyConfigPush("Canada Company");
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "USCAN", "Combined Company", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"5742", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");
		configuration.AddLocations("Combined Company", "Georgia", "VGAL01", "VGA Location", "", "01/01/2017",
				"8821 Ga 15 Hwy N", "Warthen", "", "31094", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "North Dakota",
				"ND ST - Sales and Use Tax Return", "52120", "Mail a paper return", "Semi-annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("Combined Company", "North Dakota", "VNDL01", "VND Location", "", "01/01/2017",
				"4262 110th Ave SW", "Dickinson", "", "58601-9280", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "54654", "Mail a paper return", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Pennsylvania",
				"PA Prepayment Form for PA-3", "54654", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount",
				"1.00", "", "", "", "");
		configuration.AddLocations("Combined Company", "Pennsylvania", "VPAL01", "VPAL01", "", "01/01/2017",
				"1006 W 9th Ave", "King Of Prussia", "", "19406-1206", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "South Carolina",
				"SC Sales and Use Tax Return ST-3", "52205", "Mail a paper return", "Annually", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddLocations("Combined Company", "South Carolina", "VSCL01", "VSC Location", "", "01/01/2017",
				"1013 Mount Vernon Dr", "North Myrtle Beach", "", "29582", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "South Dakota", "SD Sales/Use Tax Return",
				"55214", "Mail a paper return", "Semi-annually", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("Combined Company", "South Dakota", "VSD01", "VSD Location", "", "01/01/2017",
				"431 N Perry Ln", "Harrisburg", "", "57032-2355", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Tennessee",
				"TN Sales and Use Tax Return SLS 450", "85120", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("Combined Company", "Tennessee", "VTNL01", "VTN Location", "", "01/01/2017",
				"8818 S Poplar Lake Dr", "Germantown", "", "38138-7727", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Vermont",
				"VT Sales/Use Tax Return (SU-451)", "12345678901", "Mail a paper return", "Quarterly", "", "", "", "",
				"", "", "", "", "", "");
		configuration.AddLocations("Combined Company", "Vermont", "VVTL01", "VVT Location", "", "01/01/2017",
				"176 Center Rd", "Morrisville", "", "05661-8577", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "West Virginia",
				"WV Sales and Use Tax Return CST-200CU", "85251", "Mail a paper return", "Annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("Combined Company", "West Virginia", "VWVL01", "VWV Location", "", "01/01/2017",
				"84 Central Ave", "Romney", "", "26757-1202", "", "");
		configuration.AddJurisdictions("Combined Company", "United States", "Wisconsin",
				"WI State/County Sales/Use Tax ST-12", "82551", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("Combined Company", "Wisconsin", "VWIL01", "VWI Location", "", "01/01/2017",
				"8818 Greenview Ln", "Greendale", "", "53129-1551", "", "");
		configuration.AddJurisdictions("Combined Company", "CANADA", "Canada", "CN GST/HST Return (Non personalized)",
				"557777778", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("Combined Company", "Canada", "CA", "Canada Location", "", "01/01/2017",
				"800 Griffiths Way", "Vancouver", "British Columbia", "V6B 6G1", "", "");

		configuration.ConfigPush("Combined Company");
		configuration.VerifyConfigPush("Combined Company");
		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "All Taxpayers", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.GenerateReturnsfromAdmin(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0),
				"TN Sales and Use Tax Return SLS 450");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0));
		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");
		manageCustomers.GenerateReturnsfromAdmin(clientname, "Canada Company (CAN)", xGetCustomizedMon(-1, 0),
				"CN-SASKATCHEWAN Provincial Sales Tax");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageCustomers.paymentrequestexport(clientname, "Canada Company (CAN)", xGetCustomizedMon(-1, 0), "");
		manageCustomers.GenerateReturnsfromAdmin(clientname, "Combined Company (USCAN)", xGetCustomizedMon(-1, 0),
				"GA Sales/Use Tax Report ST-3");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "Combined Company (USCAN)",
				xGetCustomizedMon(-1, 0));
		manageCustomers.paymentrequestexport(clientname, "Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.	net/browse/CD-8883
	@Test(dependsOnMethods = { "dosinglereturnformgeneration" }, priority = 4)
	public void docreditamountusa() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		transactions.UploadAdjustmentFile("CD-7542_CurrentFiling_Inv", "Invoice", "Seller",
				"CD-7542_CurrentFiling_Inv_suc", "Yes", "");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -2

		manageCustomers.GenerateReturnsfromAdmin(clientname, "USA Company (USA)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "USA Company (USA)", xGetCustomizedMon(-2, 0));
		manageCustomers.CreditReportfromAdmin(clientname, "USA Company (USA)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.ReconReportfromAdmin(clientname, "USA Company (USA)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.DownloadReturnsfromAdmin(clientname, "USA Company (USA)", xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		// Generate Returns for Current Month -1

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("USA Company (USA)", xGetCustomizedMon(-1, 0), "", "");
		manageTaxreturns.CreditReport("USA Company (USA)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-20035
	@Test(dependsOnMethods = { "docreditamountusa" }, priority = 5)
	public void doPaymentRequestReportforus() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0),
				"USA_PremiumReturns_Reconciliation");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-10093
	@Test(dependsOnMethods = { "dosinglereturnformgeneration" }, priority = 6)
	public void docreditamountcanada() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		transactions.UploadAdjustmentFile("Canada_Transactions_Negative", "Invoice", "Canadian Transactions",
				"Canada_Transactions_Negative_suc", "Yes", "");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -2 with negative transactions

		manageCustomers.GenerateReturnsfromAdmin(clientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0));
		manageCustomers.CreditReportfromAdmin(clientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.ReconReportfromAdmin(clientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.DownloadReturnsfromAdmin(clientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		// Generate Returns for Current Month -1 with negative transactions

		manageTaxreturns.Returnsgenerate("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("Canada Company (CAN)", xGetCustomizedMon(-1, 0), "", "");
		manageTaxreturns.CreditReport("Canada Company (CAN)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		transactions.UploadAdjustmentFile("Canada_Transactions_Positive", "Invoice", "Canadian Transactions",
				"Canada_Transactions_Positive_suc", "Yes", "");
		transactions.syncTime();
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		// Generate Returns for Current Month -1 with positive transactions

		manageTaxreturns.Returnsgenerate("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("Canada Company (CAN)", xGetCustomizedMon(-1, 0), "", "");
		manageTaxreturns.CreditReport("Canada Company (CAN)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-20137
	@Test(dependsOnMethods = { "docreditamountcanada" }, priority = 7)
	public void doPaymentRequestReportforcanada() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "Canada Company (CAN)", xGetCustomizedMon(-1, 0),
				"USA_PremiumReturns_Reconciliation");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-10094
	@Test(dependsOnMethods = { "dosinglereturnformgeneration" }, priority = 8)
	public void docreditamountuscan() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		transactions.UploadAdjustmentFile("CD-7542_CurrentFiling_Invoice_US", "Invoice", "Seller",
				"CD-7542_CurrentFiling_Invoice_US_suc", "Yes", "");
		transactions.UploadAdjustmentFile("Canada_Transactions_Negative_Combined", "Invoice", "Canadian Transactions",
				"Canada_Transactions_Negative_Combined_suc", "Yes", "");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -2

		manageCustomers.GenerateReturnsfromAdmin(clientname, "Combined Company (USCAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "Combined Company (USCAN)",
				xGetCustomizedMon(-2, 0));
		manageCustomers.CreditReportfromAdmin(clientname, "Combined Company (USCAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.DownloadReturnsfromAdmin(clientname, "Combined Company (USCAN)", xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		// Generate Returns for Current Month -1

		manageTaxreturns.Returnsgenerate("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "", "");
		manageTaxreturns.CreditReport("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-20138
	@Test(dependsOnMethods = { "docreditamountuscan" }, priority = 9)
	public void doPaymentRequestReportforuscan() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "Canada Company (CAN)", xGetCustomizedMon(-1, 0),
				"USA_PremiumReturns_Reconciliation");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18186
	@Test(dependsOnMethods = { "docreditamountuscan" }, priority = 10)
	public void doreconmtpp() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.ReconciliationReport("USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		manageTaxreturns.ReconciliationReport("Canada Company (CAN)", xGetCustomizedMon(-1, 0), "");

		manageTaxreturns.ReconciliationReport("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-8886
	@Test(dependsOnMethods = { "docreditamountusa" }, priority = 11)
	public void doprepayment() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		configuration.AddJurisdictions("USA Company", "United States", "New York",
				"NY Part-Qrtly Sales/Use (Mthly) ST-809", "557777777", "Web File", "First Two Months of Quarter", "",
				"", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Texas", "TX Sales/Use Tax Return 01-114",
				"557777778", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		// Apply Prepayment amount to USA Company and Generate payments

		prepayments.EnterPrepayments("USA Company (USA)", xGetCustomizedMon(0, 0), "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "1.00", "");
		prepayments.EnterPrepayments("USA Company (USA)", xGetCustomizedMon(0, 0), "Georgia",
				"GA Sales/Use Tax Report ST-3", "1.00", "");
		/*
		 * prepayments.EnterPrepayments("USA Company (USA)", xGetCustomizedMon(0, 0),
		 * "New York", "NY Part-Qrtly Sales/Use (Mthly) ST-809", "1.00","");
		 */
		prepayments.EnterPrepayments("USA Company (USA)", xGetCustomizedMon(0, 0), "Texas",
				"TX Sales/Use Tax Return 01-114", "1.00", "Prepayment State");
		prepayments.GeneratePrepayment("USA Company (USA)", xGetCustomizedMon(0, 0));
		prepayments.DownloadPrepayment("USA Company (USA)", xGetCustomizedMon(0, 0));
		userRegistration.Logout();

	}

	@AfterTest
	public void closebrowser() throws Exception {
		quitDriver(driver);

	}
}
