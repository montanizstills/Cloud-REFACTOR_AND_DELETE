/*###################################################################################################
Name: MDUForms.java 
Description: VITR Monthly Testing 9 (Build 292)
//Ticket Number CD-16305
Author: Venkat Reddy Kandula
Date Created:04/29/2021
Date Modified:
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class MDUForms extends CommonFunctions {

	public MDUForms() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "MDUForms" + RND() + "@simulator.amazonses.com";
	String clientname = "MDUFormsTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {

		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-20505
	@Test(priority = 0)
	public void doprepaymentforTest() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, clientname, username, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Premium", "", POD);
		userRegistration.AddtionalFeatures("", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "PQA01", "PRIMEQATest", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Maryland",
				"MD Sales and Use Tax Return Form 202", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "Yes");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Prepayment Form for PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "Flat Amount", "5.00", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Massachusetts", "MA Accelerated Payment Return",
				"12335678", "Web File", "Monthly", "", "", "", "", "Flat Amount", "5.00", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Massachusetts", "MA Sales/Use Tax Return ST-9",
				"45874596287", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		/*
		 * configuration.AddJurisdictions("PRIMEQATest", "United States","Washington",
		 * "WA-SHELTON B & O Return", "12335678", "Web File","Monthly", "", "", "", "",
		 * "", "", "", "", "","");
		 * 
		 * configuration.AddJurisdictions("PRIMEQATest", "United States",
		 * "West Virginia", "WV-South Charleston District Return", "85251", "Web File",
		 * "Monthly", "", "", "", "", "", "", "", "", "","");
		 */

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_MA_PreviousFilingMonthAdjustementFile", "Invoice", "Seller",
				"CD_MA_PreviousFilingMonthAdjustementFile_suc", "Yes", "");

		transactions.syncTime();

		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		prepayments.EnterPrepayments("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "Massachusetts",
				"MA Sales/Use Tax Return ST-9", "100", "Prepayment Rule");

		prepayments.GeneratePrepayment("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));
		prepayments.DownloadPrepayment("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.CreditReport("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-19719
	@Test(priority = 1)
	public void doMduTest() throws Exception {

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

		transactions.UploadAdjustmentFile("CD_MDU_CurrentFilingMonthAdjustementFile", "Invoice", "Seller",
				"CD_MDU_CurrentFilingMonthAdjustementFile_suc", "Yes", "");

		transactions.syncTime();

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.GenerateReturnsfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0),
				"MA Sales/Use Tax Return ST-9");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0));
		manageCustomers.CreditReportfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0), "");
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.CreditReport("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-20504
	@Test(priority = 2)
	public void doprepaymentforLive() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_MA_CurrentFilingMonthAdjustementFile", "Invoice", "Seller",
				"CD_MA_CurrentFilingMonthAdjustementFile_suc", "Yes", "");

		transactions.syncTime();

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		prepayments.EnterPrepayments("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "Massachusetts",
				"MA Sales/Use Tax Return ST-9", "100", "Prepayment Rule");

		userRegistration.Logout();

		// prepayments.GeneratePrepayment("PRIMEQATest (PQA01)", xGetCustomizedMon(0,
		// 0));
		// prepayments.DownloadPrepayment("PRIMEQATest (PQA01)", xGetCustomizedMon(0,
		// 0));

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.GeneratePrepaymentsfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.downloadReturns("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "", "");

		manageTaxreturns.CreditReport("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	@AfterTest
	public void closebrowser() throws Exception {
		quitDriver(driver);

	}

}