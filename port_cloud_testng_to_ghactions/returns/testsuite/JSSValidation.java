package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class JSSValidation extends CommonFunctions {

	public JSSValidation() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "JSSValidation" + RND() + "@simulator.amazonses.com";
	String clientname = "JSSValidationTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("QA") String env, @Optional("Firefox") String Browser) throws Exception {

		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/RETUI-401
	@Test(priority = 0)
	public void doalabamataxtypereturnsTest() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "CPA Referral w/ Console", "CPAAutomation", clientname,
				username, "United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Premium",
				"Tax Calculations and Returns", POD);
		userRegistration.AddtionalFeatures("Rate File,TAXABILITY IMPACT REPORT", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("JSSFN" + RND(), "JSSLN" + RND(), "555 555 5552", "Password!1",
				"Password!1");
		System.out.println("User name is " + username);
		userRegistration.Logout();
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "PQA01", "PRIMEQATest", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Alabama", "AL State City & County Tax Return",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Alabama",
				"AL-AVENU Sales/Use/Rental Tax Return", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Alabama",
				"AL State Seller's Use Tax Return 2620", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");

		configuration.AddZeroReport("PRIMEQATest", "Alabama", "100 per page", "Sales", "7001", "State");

		configuration.EditZeroReport("PRIMEQATest", "Sales", "7001", "State");

		configuration.AddzeroReportbysearch("PRIMEQATest", "CHOCTAW", "785", "State");
		configuration.AddzeroReportbysearch("PRIMEQATest", "CLARKE", "562", "State");
		configuration.AddzeroReportbysearch("PRIMEQATest", "Adamsville (JEFFERSON)", "7001", "Non-State");
		configuration.AddzeroReportbysearch("PRIMEQATest", "Akron (HALE)", "9668", "State");
		configuration.AddzeroReportbysearch("PRIMEQATest", "CONECUH", "7018", "Non-State");
		configuration.AddzeroReportbysearch("PRIMEQATest", "RANDOLPH", "7056", "Non-State");
		configuration.AddzeroReportbysearch("PRIMEQATest", "LOWNDES", "7043", "Non-State");
		configuration.AddzeroReportbysearch("PRIMEQATest", "Brookwood (TUSCALOOSA)", "9697", "Non-State");
		configuration.AddzeroReportbysearch("PRIMEQATest", "Kennedy (LAMAR)", "9332", "Non-State");

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

		transactions.UploadAdjustmentFile("Invoice_AdjustmentFile_LiveTest", "Invoice", "Seller",
				"Invoice_AdjustmentFile_LiveTest_suc", "Yes", "");

		transactions.syncTime();

		userRegistration.Logout();

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.CreditReport("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/RETUI-403
	@Test(priority = 1)
	public void dolocseqnumberTest() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois", "IL Sales/Use Tax Return/E911 ST-1",
				"54654", "Mail a paper return", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.addLocseqnumber("PRIMEQATest", "Illinois", "100 per page", "999");

		configuration.editLocseqnumber("PRIMEQATest", "900");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/RETUI-404
	@Test(priority = 2)
	public void dositecodesTest() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddMOSiteCodes("PRIMEQATest", "United States", "Missouri", "CHARITON", "123", "00000-041-000");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/RETUI-402
	@Test(priority = 3)
	public void dobranchidTest() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddCoBranchNumbers("PRIMEQATest", "United States", "Colorado", "BACA", "1234", "220206", "Yes");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		userRegistration.Logout();

	}

	@AfterTest
	public void closebrowser() throws Exception {
		quitDriver(driver);

	}

}
