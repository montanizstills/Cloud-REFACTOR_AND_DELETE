/*###################################################################################################
Name: iteration.testsuite.java 
Description: Set Filing Month for End Date Returns and Modify User Message shown when end dating a return
//Ticket Number CD-13363, CD-13898
Author: Venkat Reddy Kandula
Date Created:12/04/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class EndDateRetunforms extends CommonFunctions {

	public EndDateRetunforms() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "EndDatedReturns" + RND() + "@simulator.amazonses.com";
	String clientname = "EndDatedReturnsTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-14023
	@Test(priority = 0)
	public void enddateReturnformTest() throws Exception {

		userRegistration.UserLogin(adminusername, adminpassword);

		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, clientname, username, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Premium", "", POD);
		userRegistration.AddtionalFeatures("", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");
		System.out.println("US User Name is:" + username);

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "USA", "USA Company", "456789012", "6310 Clift Street", "Shreveport",
				"Louisiana", "71105", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Alabama", "AL State Sales Tax Return w/Est Pay 2100",
				"52120", "Web File", "Monthly", "", "", "", "", "Flat Amount", "500", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Utah", "UT Sales and Use Tax Return TC-62S",
				"52120", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Florida",
				"FL Sales/Use Tax DR-15 (Per Location)", "52120", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");
		configuration.AddJurisdictions("USA Company", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"5742", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "North Dakota",
				"ND ST - Sales and Use Tax Return", "52120", "Mail a paper return", "Semi-annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"54654", "Mail a paper return", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Pennsylvania", "PA Prepayment Form for PA-3",
				"54654", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "South Carolina",
				"SC Sales and Use Tax Return ST-3", "52205", "Mail a paper return", "Annually", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "South Dakota", "SD Sales/Use Tax Return",
				"55214", "Mail a paper return", "Semi-annually", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Tennessee",
				"TN Sales and Use Tax Return SLS 450", "85120", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Vermont", "VT Sales/Use Tax Return (SU-451)",
				"12345678901", "Mail a paper return", "Quarterly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "West Virginia",
				"WV Sales and Use Tax Return CST-200CU", "85251", "Mail a paper return", "Annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Wisconsin",
				"WI State/County Sales/Use Tax ST-12", "82551", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "California",
				"CA St/Loc/Dist Sales & Use CDTFA-401-A", "123", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Alabama", "AL-ST CLAIR COUNTY Tax Return",
				"12335678", "Web File", "Occasional", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Florida", "FL Prepaid Wireless E911 Fee Return",
				"1113", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("USA Company", "United States", "Georgia", "GA Prepaid Wireless Fee Return",
				"1114", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company", "United States", "Indiana", "IN Sales and Use Tax Voucher ST-103",
				"225544", "Web File", "Monthly", "123", "", "", "", "", "", "", "", "", "");
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
		transactions.UploadAdjustmentFile("CD-7542_CurrentFiling_Inv", "Invoice", "Seller",
				"CD-7542_CurrentFiling_Inv_suc", "Yes", "");

		configuration.RemoveReturnForm("USA Company", "Tennessee", "TN Sales and Use Tax Return SLS 450",
				xGetCustomizedDate(0, 0, 0));

	}

	// https://vertexinc.atlassian.net/browse/CD-14078
	@Test(dependsOnMethods = { "enddateReturnformTest" }, priority = 1)
	public void verifyusercancelendingdatechange() throws Exception {

		configuration.VerifyUserCancelEndDateChange("USA Company", "Vermont", "VT Sales/Use Tax Return (SU-451)",
				xGetCustomizedDate(0, 0, 0));
	}

	// https://vertexinc.atlassian.net/browse/CD-15528
	@Test(dependsOnMethods = { "enddateReturnformTest" }, priority = 2)
	public void updateverbageonenddatingusermessage() throws Exception {

		configuration.RemoveReturnForm("USA Company", "West Virginia", "WV Sales and Use Tax Return CST-200CU",
				xGetCustomizedDate(0, 0, 0));

	}

	// https://vertexinc.atlassian.net/browse/CD-15618
	@Test(dependsOnMethods = { "enddateReturnformTest" }, priority = 3)
	public void currentfilingmonth() throws Exception {

		configuration.RemoveReturnForm("USA Company", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				xGetCustomizedDate(0, -1, 0));

	}

	// https://vertexinc.atlassian.net/browse/CD-15619
	@Test(dependsOnMethods = { "enddateReturnformTest" }, priority = 4)
	public void futuremonthaheadtocurrentfilingmonth() throws Exception {

		configuration.RemoveReturnForm("USA Company", "Alabama", "AL State Sales Tax Return w/Est Pay 2100",
				xGetCustomizedDate(0, 1, 0));

	}

	// https://vertexinc.atlassian.net/browse/CD-15620
	@Test(dependsOnMethods = { "enddateReturnformTest" }, priority = 5)
	public void todaysdate() throws Exception {

		configuration.RemoveReturnForm("USA Company", "Wisconsin", "WI State/County Sales/Use Tax ST-12",
				xGetCustomizedDate(0, 0, 0));

	}

	// https://vertexinc.atlassian.net/browse/CD-15621
	@Test(dependsOnMethods = { "enddateReturnformTest" }, priority = 6)
	public void activatereturnform() throws Exception {

		configuration.RemoveReturnForm("USA Company", "Florida", "FL Sales/Use Tax DR-15 (Per Location)",
				xGetCustomizedDate(0, -1, 0));

		configuration.ConfigPush("USA Company");

		configuration.VerifyConfigPush("USA Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.activatereturnform("USA Company", "Florida", "FL Sales/Use Tax DR-15 (Per Location)");

	}

	// https://vertexinc.atlassian.net/browse/CD-15623
	@Test(dependsOnMethods = { "enddateReturnformTest" }, priority = 7)
	public void returnonoff() throws Exception {

		configuration.RemoveReturnForm("USA Company", "Georgia", "GA Sales/Use Tax Report ST-3",
				xGetCustomizedDate(0, 0, 0));

		configuration.ConfigPush("USA Company");

		configuration.VerifyConfigPush("USA Company");

		configuration.JuridictionONOFF("USA Company", "", "Georgia");

		configuration.ConfigPush("USA Company");

		configuration.VerifyConfigPush("USA Company");

		manageTaxreturns.Returnsgenerate("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company (USA)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.returnexits("USA Company (USA)", xGetCustomizedMon(-1, 0), "PA Sales/Use/Hotel Tax PA-3");

		manageTaxreturns.returnexits("USA Company (USA)", xGetCustomizedMon(-1, 0), "GA Sales/Use Tax Report ST-3");

		manageTaxreturns.returnexits("USA Company (USA)", xGetCustomizedMon(-1, 0),
				"CA St/Loc/Dist Sales & Use CDTFA-401-A");

		manageTaxreturns.returnexits("USA Company (USA)", xGetCustomizedMon(-1, 0), "AL State Sales Tax Return w/Est Pay 2100");

		manageTaxreturns.returnexits("USA Company (USA)", xGetCustomizedMon(-1, 0),
				"FL Sales/Use Tax DR-15 (Per Location)");

	}

	// https://vertexinc.atlassian.net/browse/CD-15624
	@Test(dependsOnMethods = { "enddateReturnformTest" }, priority = 8)
	public void prepaymentform() throws Exception {

		configuration.RemoveReturnForm("USA Company", "Pennsylvania", "PA Prepayment Form for PA-3",
				xGetCustomizedDate(0, 1, 0));

		configuration.ConfigPush("USA Company");

		configuration.VerifyConfigPush("USA Company");

		prepayments.EnterPrepayments("USA Company (USA)", xGetCustomizedMon(0, 0), "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "1.00", "");

		prepayments.GeneratePrepayment("USA Company (USA)", xGetCustomizedMon(0, 0));
		prepayments.DownloadPrepayment("USA Company (USA)", xGetCustomizedMon(0, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-16325
	@Test(priority = 9)
	public void dofinalfilingmonthmonthforward() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.RemoveReturnForm("USA Company", "Alabama", "AL-ST CLAIR COUNTY Tax Return",
				xGetCustomizedDate(0, 1, 0));

		configuration.ConfigPush("USA Company");

		configuration.VerifyConfigPush("USA Company");

		reports.returnsenddateinReports("USA Company (USA)", "AL-ST CLAIR COUNTY Tax Return");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-16326
	@Test(dependsOnMethods = { "dofinalfilingmonthmonthforward" }, priority = 10)
	public void doenddatecurrentMonth() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.RemoveReturnForm("USA Company", "Indiana", "IN Sales and Use Tax Voucher ST-103",
				xGetCustomizedDate(0, 0, 0));

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");

		reports.returnsenddateinReports("USA Company (USA)", "IN Sales and Use Tax Voucher ST-103");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-16327
	@Test(dependsOnMethods = { "dofinalfilingmonthmonthforward" }, priority = 11)
	public void doenddatepastMonth() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.RemoveReturnForm("USA Company", "Florida", "FL Prepaid Wireless E911 Fee Return",
				xGetCustomizedDate(0, -1, 0));

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");

		reports.returnsenddateinReports("USA Company (USA)", "FL Prepaid Wireless E911 Fee Return");

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-16328
	@Test(dependsOnMethods = { "dofinalfilingmonthmonthforward" }, priority = 12)
	public void doactivateenddateReturn() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.RemoveReturnForm("USA Company", "Georgia", "GA Prepaid Wireless Fee Return",
				xGetCustomizedDate(0, -1, 0));

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");

		reports.returnsenddateinReports("USA Company (USA)", "GA Prepaid Wireless Fee Return");

		configuration.activatereturnform("USA Company", "Georgia", "GA Prepaid Wireless Fee Return");

		configuration.ConfigPush("USA Company");
		configuration.VerifyConfigPush("USA Company");

		reports.returnsenddatenullinReports("USA Company (USA)", "GA Prepaid Wireless Fee Return");

		userRegistration.Logout();

	}

	@AfterTest
	public void closeBrowser() throws Exception {
		quitDriver(driver);

	}
}
