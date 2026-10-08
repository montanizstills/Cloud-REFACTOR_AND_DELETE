/*###################################################################################################
Name: Netdueamountmatcheswithreturnpdf.java 
Description: Fix Cloud Reconciliation Report to display correct Net Tax Due Amount from VITR
//Ticket Number CD-7542
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
public class Netdueamountmatcheswithreturnpdf extends CommonFunctions {

	public Netdueamountmatcheswithreturnpdf() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "NetDueAmount" + RND() + "@simulator.amazonses.com";
	String clientname = "NetDueAmountTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-7716
	@Test(priority = 0)
	public void dopaymentreturnsnetdueamount() throws Exception {

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
		configuration.AddTaxPayer("United States", "PQA01", "PRIMEQATest", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("PRIMEQATest", "Pennsylvania", "PA", "PA", "", "01/01/2017", "36 Railroad Street",
				"Linfield", "", "19468", "", "");
		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		transactions.UploadAdjustmentFile("CD_7542_Recon_NetDueAmount", "Invoice", "Seller",
				"CD_7542_Recon_NetDueAmount_suc", "", "");
		transactions.syncTime();
		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0));
		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0));
		manageTaxreturns.downloadReturns("PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0), "", "");
		manageTaxreturns.ReconciliationReport("PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0), "");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-7792
	//@Test(dependsOnMethods = { "dopaymentreturnsnetdueamount" }, priority = 1)
	@Test(priority = 1)
	public void doreconreturnsnetdueamount() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Prepayment Form for PA-3",
				"12335679", "Web File", "Monthly", "", "", "", "", "Flat Amount", "5", "", "", "", "");
		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		configuration.GoLive("PRIMEQATest");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "PRIMEQATest", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		transactions.UploadAdjustmentFile("CD_7542_Recon_NetDueAmount_TAID", "Invoice", "Seller",
				"CD_7542_Recon_NetDueAmount_TAID_suc", "", "");
		transactions.syncTime();
		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "", "");
		manageTaxreturns.ReconciliationReport("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-8928
	@Test( priority = 2)
	public void donetduepositivenegativematchreturnpayment() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		transactions.UploadAdjustmentFile("CD_7542_Recon_NetDueAmount_PositiveNegativeTransactions", "Invoice",
				"Seller", "CD_7542_Recon_NetDueAmount_PositiveNegativeTransactions_suc", "", "");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.GenerateReturnsfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0),
				"PA Sales/Use/Hotel Tax PA-3");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0));
		manageCustomers.CreditReportfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.ReconReportfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0), "");
		manageCustomers.DownloadReturnsfromAdmin(clientname, "PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0));

		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.downloadReturns("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "", "");
		manageTaxreturns.ReconciliationReport("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();
	}

	@AfterTest
	public void closebrowser() throws Exception {
		quitDriver(driver);

	}
}
