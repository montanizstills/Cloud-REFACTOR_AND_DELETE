/*###################################################################################################
Name: Custompremiumreconreport.java 
Description: PROD OS8 to OS9 COnfig push test 
//CD-17695
Author: Venkat Reddy Kandula
Date Created:10/22/2020
Date Modified:11/05/2020
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class Custompremiumreconreport extends CommonFunctions {

	public Custompremiumreconreport() throws Exception {
		super();
	}

	private WebDriver driver;

	String ususername = "USCredits" + RND() + "@simulator.amazonses.com";
	String usclientname = "USCreditsTest" + RND();

	String cnusername = "CNCredits" + RND() + "@simulator.amazonses.com";
	String cnclientname = "CNCreditsTest" + RND();

	String uscnusername = "USCNCredits" + RND() + "@simulator.amazonses.com";
	String uscnclientname = "USCNCreditsTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);

	}

	// https://vertexinc.atlassian.net/browse/CD-18182
	@Test(priority = 0)
	public void doproreconreportusa() throws Exception {

		userRegistration.UserLogin(adminusername, adminpassword);

		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, usclientname, ususername, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Professional", "", POD);
		userRegistration.AddtionalFeatures("", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(ususername, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");
		System.out.println("US User Name is:" + ususername);

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(ususername, "Password!1");

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
		// *************************************************************************************************************
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(ususername, "Password!1");
		configuration.GoLive("USA Company");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(ususername, "Password!1");
		transactions.UploadAdjustmentFile("CD-7542_CurrentFiling_Inv", "Invoice", "Seller",
				"CD-7542_CurrentFiling_Inv_suc", "Yes", "");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -1

		manageCustomers.GenerateReturnsfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0));
		manageCustomers.ReconReportfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-13273
	@Test(dependsOnMethods = { "doproreconreportusa" }, priority = 1)
	public void docreditamountusa() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.ChangeSubscription(usclientname, "Premium", "", "");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(usclientname, "USA Company", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -2

		manageCustomers.GenerateReturnsfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-2, 0));
		manageCustomers.CreditReportfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.ReconReportfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.DownloadReturnsfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-13269
	@Test(dependsOnMethods = { "docreditamountusa" }, priority = 2)
	public void docreditamountusacurrentfiling() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(ususername, "Password!1");

		// Generate Returns for Current Month -1

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("USA Company (USA)", xGetCustomizedMon(-1, 0), "", "");
		manageTaxreturns.CreditReport("USA Company (USA)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18184
	@Test(dependsOnMethods = { "docreditamountusacurrentfiling" }, priority = 3)
	public void doreconreportusa() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.ReconReportfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18212
	@Test(dependsOnMethods = { "docreditamountusacurrentfiling" }, priority = 4)
	public void doreconreportustaxpayercustomerportal() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(ususername, "Password!1");

		// Generate Returns for Current Month -1
		manageTaxreturns.ReconciliationReport("USA Company (USA)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18181
	@Test(priority = 5)
	public void doproreconreportcanada() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, cnclientname, cnusername, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Professional", "", POD);
		userRegistration.AddtionalFeatures("", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(cnusername, "Password!1");
		userRegistration.NewUserBilling("Canada", "800 Griffiths Way", "Vancouver", "British Columbia", "V6B 6G1",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");
		System.out.println("CN User Name is:" + cnusername);

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(cnusername, "Password!1");

		configuration.AddTaxPayer("Canada", "CAN", "Canada Company", "515316542313243", "800 Griffiths Way!",
				"Vancouver!#@#$%^&*", "British Columbia", "V6B 6G1", "", "");
		configuration.AddJurisdictions("Canada Company", "CANADA", "Canada", "CN GST/HST Return (Non personalized)",
				"557777778", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("Canada Company", "Canada", "CA", "Canada Location", "", "01/01/2017",
				"800 Griffiths Way", "Vancouver", "British Columbia", "V6B 6G1", "", "");
		configuration.ConfigPush("Canada Company");
		configuration.VerifyConfigPush("Canada Company");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(cnusername, "Password!1");
		configuration.GoLive("Canada Company");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(cnusername, "Password!1");
		transactions.UploadAdjustmentFile("Canada_Transactions_Negative", "Invoice", "Canadian Transactions",
				"Canada_Transactions_Negative_suc", "Yes", "");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -2 with negative transactions

		manageCustomers.GenerateReturnsfromAdmin(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0));
		manageCustomers.CreditReportfromAdmin(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.ReconReportfromAdmin(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.DownloadReturnsfromAdmin(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-13271
	@Test(dependsOnMethods = { "doproreconreportcanada" }, priority = 6)
	public void docreditamountcanada() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.ChangeSubscription(cnclientname, "Premium", "", "");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(cnclientname, "Canada Company", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(cnusername, "Password!1");
		transactions.UploadAdjustmentFile("Canada_Transactions_Positive", "Invoice", "Canadian Transactions",
				"Canada_Transactions_Positive_suc", "Yes", "");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.GenerateReturnsfromAdmin(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0));
		manageCustomers.CreditReportfromAdmin(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.ReconReportfromAdmin(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.DownloadReturnsfromAdmin(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(cnusername, "Password!1");

		// Generate Returns for Current Month -1 with negative transactions

		manageTaxreturns.Returnsgenerate("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("Canada Company (CAN)", xGetCustomizedMon(-1, 0), "", "");
		manageTaxreturns.CreditReport("Canada Company (CAN)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18183
	@Test(dependsOnMethods = { "docreditamountcanada" }, priority = 7)
	public void doreconreportcanada() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(cnusername, "Password!1");

		// Generate Returns for Current Month -1
		manageTaxreturns.ReconciliationReport("Canada Company (CAN)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-18185
	@Test(priority = 8)
	public void doproreconreportuscan() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, uscnclientname, uscnusername, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Professional", "", POD);
		userRegistration.AddtionalFeatures("", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(uscnusername, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");
		System.out.println("US CN User Name is:" + uscnusername);

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(uscnusername, "Password!1");

		configuration.AddTaxPayer("United States", "USCAN", "Combined Company", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		configuration.Jurisdictionson("Combined Company", "United States", "Georgia", "");
		configuration.Jurisdictionson("Combined Company", "Canada", "Canada", "");

		configuration.ConfigPush("Combined Company");
		configuration.VerifyConfigPush("Combined Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(uscnusername, "Password!1");
		configuration.GoLive("Combined Company");
		configuration.syncTime();
		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(uscnusername, "Password!1");

		manageTaxreturns.Returnsgenerate("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.Creditreportgenerated("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.CreditReport("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");
		manageTaxreturns.ReconciliationReport("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(uscnusername, "Password!1");

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
		Open(driver, url);
		userRegistration.UserLogin(uscnusername, "Password!1");
		transactions.UploadAdjustmentFile("CD-7542_CurrentFiling_Invoice_US", "Invoice", "Seller",
				"CD-7542_CurrentFiling_Invoice_US_suc", "Yes", "");
		transactions.UploadAdjustmentFile("Canada_Transactions_Negative_Combined", "Invoice", "Canadian Transactions",
				"Canada_Transactions_Negative_Combined_suc", "Yes", "");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(uscnusername, "Password!1");

		// Generate Returns for Current Month -1

		manageTaxreturns.Returnsgenerate("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "", "");
		manageTaxreturns.CreditReport("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");
		manageTaxreturns.ReconciliationReport("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-13268
	@Test(dependsOnMethods = { "doproreconreportuscan" }, priority = 9)
	public void docreditamountuscan() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.ChangeSubscription(uscnclientname, "Premium", "", "");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(uscnclientname, "Combined Company", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -2

		manageCustomers.GenerateReturnsfromAdmin(uscnclientname, "Combined Company (USCAN)", xGetCustomizedMon(-2, 0),
				"");
		manageCustomers.verifyreturnsGeneratedfromAdmin(uscnclientname, "Combined Company (USCAN)",
				xGetCustomizedMon(-2, 0));
		manageCustomers.CreditReportfromAdmin(uscnclientname, "Combined Company (USCAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.ReconReportfromAdmin(uscnclientname, "Combined Company (USCAN)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.DownloadReturnsfromAdmin(uscnclientname, "Combined Company (USCAN)", xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-12651
	@Test(dependsOnMethods = { "docreditamountuscan" }, priority = 10)
	public void docreditamountuscancustomerportal() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(uscnusername, "Password!1");

		// Generate Returns for Current Month -1

		manageTaxreturns.Returnsgenerate("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "", "");
		manageTaxreturns.CreditReport("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");
		manageTaxreturns.ReconciliationReport("Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18448
	@Test(dependsOnMethods = { "doreconreportusa" }, priority = 11)
	public void dopaymentrequestreportusa() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(ususername, "Password!1");
		manageCustomers.DoNotFileReturns("GA Sales/Use Tax Report ST-3", "USA Company (USA)");

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		// The returns marked as ‘Do Not File’ will not be in Payment Request export
		manageCustomers.paymentrequestexport(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		manageCustomers.GenerateReturnsfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0),
				"TN Sales and Use Tax Return SLS 450");
		manageCustomers.verifyreturnsGeneratedfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0));

		// All 9 returns should show in payment export report
		manageCustomers.paymentrequestexport(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		manageCustomers.GenerateReturnsfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageCustomers.paymentrequestexport(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		Open(driver, url);
		userRegistration.UserLogin(ususername, "Password!1");

		manageTaxreturns.downloadReturns("USA Company (USA)", "xGetCustomizedMon(-1, 0)", "", "");

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-18452
	@Test(dependsOnMethods = { "doreconreportcanada" }, priority = 12)
	public void dopaymentrequestreportcanada() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(cnusername, "Password!1");

		manageCustomers.DoNotFileReturns("CN-SASKATCHEWAN Provincial Sales Tax", "Canada Company (CAN)");

		// Generate Returns for Current Month -1 with negative transactions

		manageTaxreturns.Returnsgenerate("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// The returns marked as ‘Do Not File’ will not be in Payment Request export
		manageCustomers.paymentrequestexport(cnusername, "Canada Company (CAN)", xGetCustomizedMon(-1, 0), "");

		manageCustomers.GenerateReturnsfromAdmin(cnusername, "Canada Company (CAN)", xGetCustomizedMon(-1, 0),
				"CN GST/HST Return (Non personalized)");
		manageCustomers.verifyreturnsGeneratedfromAdmin(cnusername, "Canada Company (CAN)", xGetCustomizedMon(-1, 0));

		// All remaining returns should show in payment export report
		manageCustomers.paymentrequestexport(cnusername, "Canada Company (CAN)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(cnusername, "Password!1");
		transactions.UploadAdjustmentFile("Canada_Transactions_Positive", "Invoice", "Canadian Transactions",
				"Canada_Transactions_Positive_suc", "Yes", "");
		transactions.syncTime();
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(cnusername, "Password!1");

		// Generate Returns for Current Month -1 with positive transactions

		manageTaxreturns.Returnsgenerate("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada Company (CAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("Canada Company (CAN)", xGetCustomizedMon(-1, 0), "", "");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(cnclientname, "Canada Company (CAN)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18453
	@Test(dependsOnMethods = { "docreditamountuscancustomerportal" }, priority = 13)
	public void dopaymentrequestreportuscan() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(uscnclientname, "Password!1");
		manageCustomers.DoNotFileReturns("GA Sales/Use Tax Report ST-3", "Combined Company (USCAN)");

		manageCustomers.DoNotFileReturns("CN-SASKATCHEWAN Provincial Sales Tax", "Combined Company (USCAN)");

		manageTaxreturns.Returnsgenerate("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Combined Company (USCAN)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		// The returns marked as ‘Do Not File’ will not be in Payment Request export
		manageCustomers.paymentrequestexport(uscnclientname, "Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");

		manageCustomers.GenerateReturnsfromAdmin(uscnclientname, "Combined Company (USCAN)", xGetCustomizedMon(-1, 0),
				"TN Sales and Use Tax Return SLS 450");
		manageCustomers.verifyreturnsGeneratedfromAdmin(uscnclientname, "Combined Company (USCAN)",
				xGetCustomizedMon(-1, 0));

		// All 9 returns should show in payment export report
		manageCustomers.paymentrequestexport(uscnclientname, "Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");

		manageCustomers.GenerateReturnsfromAdmin(uscnclientname, "Combined Company (USCAN)", xGetCustomizedMon(-1, 0),
				"");
		manageCustomers.verifyreturnsGeneratedfromAdmin(uscnclientname, "Combined Company (USCAN)",
				xGetCustomizedMon(-1, 0));

		manageCustomers.paymentrequestexport(uscnclientname, "Combined Company (USCAN)", xGetCustomizedMon(-1, 0), "");

		Open(driver, url);
		userRegistration.UserLogin(uscnusername, "Password!1");

		manageTaxreturns.downloadReturns("Combined Company (USCAN)", "xGetCustomizedMon(-1, 0)", "", "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18454
	@Test(dependsOnMethods = { "dopaymentrequestreportusa" }, priority = 14)
	public void doreturnsnotdueatreturnsgeneration() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(ususername, "Password!1");

		configuration.AddJurisdictions("USA Company", "United States", "Michigan",
				"MI Sales/Use/Withholding 5080 (the 20th)", "12335678", "Web File", "Monthly", "", "", "", "", "", "",
				"", "", "", "");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(ususername, "Password!1");

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(usclientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	@AfterTest
	public void closebrowser() throws Exception {
		quitDriver(driver);

	}
}
