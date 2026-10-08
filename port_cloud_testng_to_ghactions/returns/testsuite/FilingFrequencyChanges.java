/*###################################################################################################
Name: paymentrequestForm.java 
Description: Return generation queue items(18) failed in Month-End return generation
//Ticket Number CD-16843
Author: Venkat Reddy Kandula
Date Created:02/20/2021
Date Modified:
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class FilingFrequencyChanges extends CommonFunctions {

	public FilingFrequencyChanges() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "PaymentRequestForm" + RND() + "@simulator.amazonses.com";
	String clientname = "PaymentRequestFormTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("STAGE") String env, @Optional("Firefox") String Browser) throws Exception {

		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-16928
	@Test(priority = 0)
	public void dochangemonthlytoquarterly() throws Exception {

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

		configuration.AddJurisdictions("USA Company", "United States", "Illinois", "IL Sales/Use Tax Return/E911 ST-1",
				"1113", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "West Virginia",
				"WV Sales and Use Tax Return CST-200CU", "1122", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("USA Company", "United States", "Florida", "FL Sales/Use Tax Return DR-15EZ",
				"1114", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Missouri", "MO Vendor's Use Return 53-V",
				"52120", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Maryland",
				"MD Sales and Use Tax Return Form 202", "1113", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("USA Company", "United States", "South Carolina",
				"SC Sales and Use Tax Return ST-3", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("USA Company", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"1114", "Web File", "Monthly", "", "", "", "", "Flat Amount", "1.0", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Michigan",
				"MI Sales/Use/Withholding 5080 (the 20th)", "12335678", "Web File", "Quarterly", "", "", "", "", "", "",
				"", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Arkansas", "AR Excise Sales/Use Tax Report",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.GoLive("USA Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		premiumCustomers.onboardCustomer(clientname, "USA Company", "December 2019");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_14106_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_6004_CurrentFilingPeriod_suc");

		transactions.UploadAdjustmentFile("OccasionalReturnPurchaseOrder", "Purchase Order", "Buyer",
				"OccasionalReturnPurchaseOrder_suc");

		transactions.syncTime();

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("USA Company", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"12335678", "Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Illinois", "IL Sales/Use Tax Return/E911 ST-1",
				"1113", "Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"1114", "Web File", "Quarterly", "", "", "", "", "Flat Amount", "1.0", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "South Carolina",
				"SC Sales and Use Tax Return ST-3", "12335678", "Web File", "Quarterly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-16929
	@Test(dependsOnMethods = { "dochangemonthlytoquarterly" }, priority = 1)
	public void dochangemonthlytosemiannually() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("USA Company", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"12335678", "Web File", "Semi-annually", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Maryland",
				"MD Sales and Use Tax Return Form 202", "1113", "Web File", "Semi-annually", "", "", "", "", "", "", "",
				"", "", "");

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-16930
	@Test(dependsOnMethods = { "dochangemonthlytosemiannually" }, priority = 2)
	public void dochangemonthlytoannually() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("USA Company", "United States", "South Carolina",
				"SC Sales and Use Tax Return ST-3", "12335678", "Web File", "Annually", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("USA Company", "United States", "Arkansas", "AR Excise Sales/Use Tax Report",
				"12335678", "Web File", "Annually", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Missouri", "MO Vendor's Use Return 53-V",
				"52120", "Web File", "Annually", "", "", "", "", "", "", "", "", "", "");

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-16931
	@Test(dependsOnMethods = { "dochangemonthlytoannually" }, priority = 3)
	public void dochangequarterlytoannually() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("USA Company", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"1114", "Web File", "Annually", "", "", "", "", "Flat Amount", "1.0", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "West Virginia",
				"WV Sales and Use Tax Return CST-200CU", "1122", "Web File", "Annually", "", "", "", "", "", "", "", "",
				"", "");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-16932
	@Test(dependsOnMethods = { "dochangequarterlytoannually" }, priority = 4)
	public void dochangequarterlytomonthly() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("USA Company", "United States", "Michigan",
				"MI Sales/Use/Withholding 5080 (the 20th)", "12335678", "Web File", "Monthly", "", "", "", "", "", "",
				"", "", "", "");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company (USA)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	@AfterTest
	public void closebrowser() throws Exception {
		quitDriver(driver);

	}

}
