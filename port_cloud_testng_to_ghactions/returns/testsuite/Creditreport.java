/*###################################################################################################
Name: Creditreport.java 
Description: Changes to Credit application for relaes 8 10
//Ticket Number CD-6004
Author: Venkat Reddy Kandula
Date Created:03/02/2020
Date Modified:04/03/2020
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class Creditreport extends CommonFunctions {

	public Creditreport() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "CreditReport" + RND() + "@simulator.amazonses.com";
	String clientname = "CreditReportTest" + RND();
	

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {

		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-7240
	@Test(priority = 0)
	public void dogeneratecredtreportadminportal() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "CPA Referral w/ Console", "CPAAutomation", clientname,
				username, "United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Premium",
				"Tax Calculations and Returns", POD);
		userRegistration.AddtionalFeatures("Rate File,TAXABILITY IMPACT REPORT", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("CPARefAutomationRO" + RND(), "CPARefAutomationRO" + RND(),
				"555 555 5552", "Password!1", "Password!1");
		System.out.println("User name is " + username);
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "PQA01", "PRIMEQATest", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("PRIMEQATest", "Pennsylvania", "PA", "PA", "", "01/01/2017", "36 Railroad Street",
				"Linfield", "", "19468", "", "");
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
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "PRIMEQATest", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_6004_PreviousFilingPeriod", "Invoice", "Seller",
				"CD_6004_PreviousFilingPeriod_suc", "Yes", "");

		transactions.UploadAdjustmentFile("CD_7190_PreviousFilingPeriod", "Invoice", "Seller",
				"CD_7190_PreviousFilingPeriod_suc", "Yes", "");

		transactions.syncTime();

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.GenerateReturnsfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0),
				"PA Sales/Use/Hotel Tax PA-3");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0));
		manageCustomers.CreditReportfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0), "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-7246
	@Test(dependsOnMethods = { "dogeneratecredtreportadminportal" }, priority = 1)
	public void dogeneratecreditreportcustomerportal() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_6004_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_6004_CurrentFilingPeriod_suc", "Yes", "");

		transactions.UploadAdjustmentFile("CD_7190_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_7190_CurrentFilingPeriod_suc", "Yes", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112014", "", "",
				"United States", "Maryland", "21201", "", "6000", "1", "", "", "", "Seller", "", "", "");

		transactions.syncTime();

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.CreditReport("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-17261
	@Test(dependsOnMethods = { "dogeneratecredtreportadminportal" }, priority = 2)
	public void dozeroReport() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Alabama",
				"AL State Seller's Use Tax Return 2620", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");

		configuration.AddZeroReport("PRIMEQATest", "Alabama", "100 per page", "Sales", "7001", "State");

		configuration.EditZeroReport("PRIMEQATest", "Sales", "7001", "State");

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

		transactions.UploadAdjustmentFile("CD_17258_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_17258_CurrentFilingPeriod_suc", "Yes", "");

		transactions.syncTime();

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		

	}

	// https://vertexinc.atlassian.net/browse/CD-18090
	@Test(priority = 3)
	public void dolocseqnumberTest() throws Exception {
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois", "IL Sales/Use Tax Return/E911 ST-1",
				"54654", "Mail a paper return", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois", "IL Sales/Use Tax Prepayment Return",
				"58795", "Web File", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois",
				"IL-CHICAGO Titled PP 8400R - Monthly", "88422", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");

		configuration.addLocseqnumber("PRIMEQATest", "Illinois", "100 per page", "999");

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

		

	}

	// https://vertexinc.atlassian.net/browse/CD-18120
	@Test(priority = 4)
	public void douploadvalidinvaliddata() throws Exception {
		userRegistration.Logout();
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

		

	}

	// https://vertexinc.atlassian.net/browse/CD-19721
	@Test(priority = 5)
	public void domdreturns() throws Exception {

		userRegistration.Logout();
		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.downloadReturns("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "", "");

		userRegistration.Logout();

	}

	@AfterTest
	public void closebrowser() throws Exception {
		quitDriver(driver);

	}

}
