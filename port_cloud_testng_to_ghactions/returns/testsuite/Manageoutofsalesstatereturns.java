/*###################################################################################################
Name: Manageoutofsalesstatereturns.java 
Description:  Add new test scenarios based on missing code path for regression
Author:Venkat Reddy Kandula
Ticket Number: CD-10868
Date Created:06/01/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class Manageoutofsalesstatereturns extends CommonFunctions {

	public Manageoutofsalesstatereturns() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "CodeTouchPoints" + RND() + "@simulator.amazonses.com";
	String clientname = "CodeTouchPointsTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("STAGE") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-11451
	@Test(priority = 0)
	public void doreturnsformlabelEmpty() throws Exception {

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

		configuration.AddTaxPayer("United States", "GS01", "German Shepherd, Inc", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		userRegistration.Logout();

		/*
		 * Open(driver, url); userRegistration.UserLogin(adminusername, adminpassword);
		 * manageTaxreturns.ManageReturnSpecificRule("COLORADO",
		 * "CO-WESTMINSTER Sales and Use Tax Return"); userRegistration.Logout();
		 */
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Colorado",
				"CO-WESTMINSTER Sales and Use Tax Return", "123", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "", "", "", "");

	}

	// https://vertexinc.atlassian.net/browse/CD-11449
	@Test(dependsOnMethods = { "doreturnsformlabelEmpty" }, priority = 1)
	public void doprepaymentbyjurisdictiontaxdataenterbyjurisdiction() throws Exception {

		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Pennsylvania",
				"PA Prepayment Form for PA-3", "54654", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount",
				"5.00", "", "", "", "");

	}

	// https://vertexinc.atlassian.net/browse/CD-11422
	@Test(dependsOnMethods = { "doprepaymentbyjurisdictiontaxdataenterbyjurisdiction" }, priority = 2)
	public void domanageoutofsalestouchpoint() throws Exception {

		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Arkansas",
				"AR Excise Sales/Use Tax Report", "82551", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Arkansas",
				"AR Sales Tax - Direct Pay Filers", "123456987", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Arkansas",
				"AR Sales Tax - Direct Pay Filers", "123456987", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Connecticut",
				"CT Sales/Use Tax Return OS-114", "12345678911", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "District Of Columbia",
				"DC Sales/Use Tax Return FR-800M/Q/A", "12345785", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Hawaii",
				"HI General Excise/Use Tax Return G-45", "23456789112", "Web File", "Monthly", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Idaho", "ID Sales/Use Tax Return 850",
				"123456789", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Indiana",
				"IN Sales and Use Tax Voucher ST-103", "1234567891234", "Web File", "Quarterly", "123", "", "", "", "",
				"", "", "", "", "");

		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Illinois",
				"IL Sales/Use Tax Return/E911 ST-1", "82551", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");

		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Iowa",
				"IA Sales/Retailer's Use Tax 32-022", "000000123456794", "Web File", "Monthly", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Kentucky",
				"KY Sales/Use Tax Return 51A102", "041123457", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Kentucky",
				"KY Sales/Use Tax Return 51A103", "041123458", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Louisiana",
				"LA Monthly Sales Tax Return R-1029", "1245789151", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Minnesota",
				"MN Sales/Use Tax Return ST-1", "111111445", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Minnesota",
				"MN Consolidated Sales/Use Tax ST-1V CON", "777777770", "Web File", "Monthly", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Mississippi",
				"MS Sales Tax Return 72-010", "557777777", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Mississippi",
				"MS Sales Tax Return 72-001", "121212", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "North Carolina",
				"NC Sales and Use Tax Return E-500", "S123456789", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "North Dakota",
				"ND ST - Sales and Use Tax Return", "121212", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "South Dakota",
				"SD Sales/Use Tax Return", "ST12345678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Tennessee",
				"TN Sales and Use Tax Return SLS 450", "987654321", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Wisconsin",
				"WI State/County Sales/Use Tax ST-12", "123451234512345", "Web File", "Monthly", "", "", "", "", "", "",
				"", "", "", "");

		configuration.ConfigPush("German Shepherd, Inc");
		configuration.VerifyConfigPush("German Shepherd, Inc");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-11452
	@Test(dependsOnMethods = { "domanageoutofsalestouchpoint" }, priority = 3)
	public void doimportformimportrejectedgreaterthanzeroacceptwitherror() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.GoLive("German Shepherd, Inc");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("Outof100First25badforthreshholdwithnewtaxareaid", "Invoice", "Seller", "");

		transactions.syncTime();

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-11450
	@Test(dependsOnMethods = { "domanageoutofsalestouchpoint" }, priority = 4)
	public void doprepaymentbyjurisdictiontaxdataenterbyjurisdictioncreditamountfound() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Texas",
				"TX S/U Tax 01-114 (Direct Pay filers) ", "123", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "", "", "", "");

		configuration.ConfigPush("German Shepherd, Inc");
		configuration.VerifyConfigPush("German Shepherd, Inc");

		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "AR Excise Sales/Use Tax Report",
				"AR Excise Sales/Use Tax Report", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "AR Sales Tax - Direct Pay Filers",
				"AR Sales Tax - Direct Pay Filers", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "CT Sales/Use Tax Return OS-114",
				"CT Sales/Use Tax Return OS-114", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "DC Sales/Use Tax Return FR-800M/Q/A",
				"DC Sales/Use Tax Return FR-800M/Q/A", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "HI General Excise/Use Tax Return G-45",
				"HI General Excise/Use Tax Return G-45", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "ID Sales/Use Tax Return 850",
				"ID Sales/Use Tax Return 850", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "IL Sales/Use Tax Return/E911 ST-1",
				"IL Sales/Use Tax Return/E911 ST-1", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "IN Sales and Use Tax Voucher ST-103",
				"IN Sales and Use Tax Voucher ST-103", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "IA Sales/Retailer's Use Tax 32-022",
				"IA Sales/Retailer's Use Tax 32-022", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "KY Sales/Use Tax Return 51A102",
				"KY Sales/Use Tax Return 51A102", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "KY Sales/Use Tax Return 51A103",
				"KY Sales/Use Tax Return 51A103", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "LA Monthly Sales Tax Return R-1029",
				"LA Monthly Sales Tax Return R-1029", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "MN Sales/Use Tax Return ST-1",
				"MN Sales/Use Tax Return ST-1", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes",
				"MN Consolidated Sales/Use Tax ST-1V CON", "MN Consolidated Sales/Use Tax ST-1V CON", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "MS Sales Tax Return 72-010",
				"MS Sales Tax Return 72-010", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "MS Sales Tax Return 72-001",
				"MS Sales Tax Return 72-001", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "NC Sales and Use Tax Return E-500",
				"NC Sales and Use Tax Return E-500", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "ND ST - Sales and Use Tax Return",
				"ND ST - Sales and Use Tax Return", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "SD Sales/Use Tax Return",
				"SD Sales/Use Tax Return", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "TN Sales/Use Tax Return RV-R0000201",
				"TN Sales/Use Tax Return RV-R0000201", "", "");
		manageTaxreturns.ManageOutofSales("German Shepherd, Inc (GS01)", "Yes", "WI State/County Sales/Use Tax ST-12",
				"WI State/County Sales/Use Tax ST-12", "", "");

		manageTaxreturns.manageoutofstateSales("German Shepherd, Inc (GS01)", xGetCustomizedMon(-1, 0),
				"AR Excise Sales/Use Tax Report", "10");
		manageTaxreturns.manageoutofstateSales("German Shepherd, Inc (GS01)", xGetCustomizedMon(-1, 0),
				"IL Sales/Use Tax Return/E911 ST-1", "100");
		prepayments.EnterPrepayments("German Shepherd, Inc (GS01)", xGetCustomizedMon(-1, 0), "Texas",
				"TX S/U Tax 01-114 (Direct Pay filers)", "100", "Prepayment State");

		prepayments.EnterPrepayments("German Shepherd, Inc (GS01)", xGetCustomizedMon(-1, 0), "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "100", "");

		manageTaxreturns.Returnsgenerate("German Shepherd, Inc (GS01)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("German Shepherd, Inc (GS01)", xGetCustomizedMon(-1, 0));

		prepayments.EnterPrepayments("German Shepherd, Inc (GS01)", xGetCustomizedMon(-1, 0), "Texas",
				"TX S/U Tax 01-114 (Direct Pay filers)", "33", "");

		manageTaxreturns.Returnsgenerate("German Shepherd, Inc (GS01)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("German Shepherd, Inc (GS01)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-10006
	@Test( priority = 5)
	public void dotaxpayerpartition() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.IncreasePartitions(clientname, "2");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-12733
	@Test( priority = 6)
	public void domanageoutofsales() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "HI01", "KitKat, Inc", "345678912", "306 Main St", "Sturbridge",
				"Massachusetts", "01566", "", "");

		configuration.AddLocations("KitKat, Inc", "Delaware", "DL01", "Delaware loc", "123345", "01/01/2017",
				"1200 N Dupont Hwy", "Dover", "", "19901", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"12312123451", "Web File", "Monthly", "", "", "", "", "Flat Amount", "500", "", "", "", "");

		configuration.AddLocations("KitKat, Inc", "Georgia", "GA3", "GA3", "", "01/01/2017", "943 Aycock Mill Rd",
				"Shellman", "", "39886", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"1234567", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "California", "CA Sales/Use Prepayment Form",
				"1234567", "Web File", "First Two Months of Quarter", "", "", "", "", "", "", "", "", "", "");

		configuration.ConfigPush("KitKat, Inc");
		configuration.VerifyConfigPush("KitKat, Inc");

		manageTaxreturns.manageoutofstateSales("KitKat, Inc (HI01)", xGetCustomizedMon(-1, 0),
				"AR Excise Sales/Use Tax Report", "10");

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-12734
	@Test(priority = 7)
	public void doadminmanageoutofsales() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.AdminManageOutofSalesReturnsUpdate("AR Excise Sales/Use Tax Report");

		userRegistration.Logout();
	}

	@AfterTest
	public void closebrowser() throws Exception {
		quitDriver(driver);

	}

}
