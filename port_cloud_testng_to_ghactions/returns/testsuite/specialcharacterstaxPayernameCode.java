/*###################################################################################################
Name: specialcharacterstaxPayernameCode.java 
Description: Added Tax Payer Name and Company Code with Special Characters
//Ticket Number CD-16692
Author: Venkat Reddy Kandula
Date Created:01/27/2021
Date Modified:
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class specialcharacterstaxPayernameCode extends CommonFunctions {

	public specialcharacterstaxPayernameCode() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "SpecialCharactersTaxpayer" + RND() + "@simulator.amazonses.com";
	String clientname = "SpecialCharactersTaxpayerTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {

		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-20476
	@Test(priority = 0)
	public void doprofessionalregistration() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, clientname, username, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Professional", "", POD);
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

	// https://vertexinc.atlassian.net/browse/CD-20869
	@Test(priority = 1)
	public void doprofessionalregistrationreadonly() throws Exception {

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

	// https://vertexinc.atlassian.net/browse/CD-16745
	@Test(priority = 2)
	public void professionalspecialcharacters() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "USA\"/\\|*?<>:", "USA Company\"/\\\\|*?<>:", "456789012",
				"6310 Clift Street", "Shreveport", "Louisiana", "71105", "", "");

		configuration.AddJurisdictions("USA Company\"/\\\\|*?<>:", "United States", "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");

		configuration.AddJurisdictions("USA Company\"/\\\\|*?<>:", "United States", "Alabama",
				"AL-ST CLAIR COUNTY Tax Return", "12335678", "Web File", "Occasional", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("USA Company\"/\\\\|*?<>:", "United States", "California",
				"CA Prepaid MTS Report", "1122", "Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company\"/\\\\|*?<>:", "United States", "Florida",
				"FL Prepaid Wireless E911 Fee Return", "1113", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("USA Company\"/\\\\|*?<>:", "United States", "Georgia",
				"GA Prepaid Wireless Fee Return", "1114", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");

		configuration.AddJurisdictions("USA Company\"/\\\\|*?<>:", "United States", "North Dakota",
				"ND ST - Sales and Use Tax Return", "52120", "Mail a paper return", "Semi-annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("USA Company\"/\\\\|*?<>:", "United States", "Pennsylvania",
				"PA Prepayment Form for PA-3", "54654", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount",
				"1.00", "", "", "", "");
		configuration.AddJurisdictions("USA Company\"/\\\\|*?<>:", "United States", "Vermont",
				"VT Sales/Use Tax Return (SU-451)", "12345678901", "Mail a paper return", "Quarterly", "", "", "", "",
				"", "", "", "", "", "");
		configuration.AddJurisdictions("USA Company\"/\\\\|*?<>:", "United States", "West Virginia",
				"WV Sales and Use Tax Return CST-200CU", "85251", "Mail a paper return", "Annually", "", "", "", "", "",
				"", "", "", "", "");

		configuration.ConfigPush("USA Company\"/\\\\|*?<>:");
		configuration.VerifyConfigPush("USA Company\"/\\\\|*?<>:");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.GoLive("USA Company\"/\\\\|*?<>:");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.CalculateTaxes("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedDate(0, -2, 0), "212011",
				"", "", "United States", "Georgia", "30022", "", "-1254.87", "1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedDate(0, -1, 0), "212012",
				"", "", "United States", "Georgia", "30022", "", "1936.293", "1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedDate(0, -2, 0), "212013",
				"", "", "United States", "Pennsylvania", "19406", "", "-101.459", "1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedDate(0, -1, 0), "212014",
				"", "", "United States", "Pennsylvania", "19406", "", "-50.2178", "1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedDate(0, -2, 0), "212015",
				"", "", "United States", "Tennessee", "37101", "", "-3345.98", "1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedDate(0, -1, 0), "212016",
				"", "", "United States", "Tennessee", "37101", "", "2986.36", "1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedDate(0, -2, 0), "212017",
				"", "", "United States", "Wisconsin", "54409", "", "1000", "1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedDate(0, -1, 0), "212016",
				"", "", "United States", "Wisconsin", "54409", "", "2000", "1", "", "", "", "", "", "", "");

		transactions.syncTime();

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.GenerateReturnsfromAdmin(clientname, "USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)",
				xGetCustomizedMon(-2, 0), "PA Sales/Use/Hotel Tax PA-3");

		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)",
				xGetCustomizedMon(-2, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-16692
	@Test(dependsOnMethods = { "professionalspecialcharacters" }, priority = 3)
	public void docheckDueDateusaspecialcharacters() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.ChangeSubscription(clientname, "Premium", "", "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-16318
	@Test(dependsOnMethods = { "docheckDueDateusaspecialcharacters" }, priority = 4)
	public void docheckDueDateusa() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "USA Company\"/\\\\|*?<>:", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.GenerateReturnsfromAdmin(clientname, "USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)",
				xGetCustomizedMon(-2, 0), "PA Sales/Use/Hotel Tax PA-3");

		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname, "USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)",
				xGetCustomizedMon(-2, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.Returnsgenerate("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)",
				xGetCustomizedMon(-1, 0), "");

	}

	// https://vertexinc.atlassian.net/browse/CD-16320
	@Test(priority = 5)
	public void docheckDueDatecanada() throws Exception {

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.IncreasePartitions(clientname, "3");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		configuration.AddTaxPayer("Canada", "CAN\\\"/\\\\|*?<>:", "Canada<:\"\"? >\\/Company/*\\\\|", "515316542313243",
				"800 Griffiths Way!", "Vancouver!#@#$%^&*", "British Columbia", "V6B 6G1", "", "");
		configuration.AddJurisdictions("Canada<:\"\"? >\\/Company/*\\\\|", "CANADA", "Canada",
				"CN GST/HST Return (Non personalized)", "557777778", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddLocations("Canada<:\"\"? >\\/Company/*\\\\|", "Canada", "CA", "Canada Location", "",
				"01/01/2017", "800 Griffiths Way", "Vancouver", "British Columbia", "V6B 6G1", "", "");
		configuration.ConfigPush("Canada<:\"\"? >\\/Company/*\\\\|");
		configuration.VerifyConfigPush("Canada<:\"\"? >\\/Company/*\\\\|");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "Canada<:\"\"? >\\/Company/*\\\\|", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		transactions.CalculateTaxes("Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)", xGetCustomizedDate(0, -2, 0),
				"212011", "", "", "Canada", "Ontario", "M5S 1A1", "", "-1200", "1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)", xGetCustomizedDate(0, -1, 0),
				"212011", "", "", "Canada", "Ontario", "M5S 1A1", "", "-1500", "1", "", "", "", "", "", "", "");
		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -2 with negative transactions

		manageCustomers.GenerateReturnsfromAdmin(clientname, "Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-2, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname,
				"Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)", xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		// Generate Returns for Current Month -1 with negative transactions

		manageTaxreturns.Returnsgenerate("Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-1, 0));

		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		transactions.CalculateTaxes("Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)", xGetCustomizedDate(0, -1, 0),
				"212011", "", "", "Canada", "Ontario", "M5S 1A1", "", "3000", "1", "", "", "", "", "", "", "");

		transactions.syncTime();
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		// Generate Returns for Current Month -1 with positive transactions

		manageTaxreturns.Returnsgenerate("Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-1, 0));
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-1, 0), "");

	}

	// https://vertexinc.atlassian.net/browse/CD-16321
	@Test(priority = 6)
	public void docheckDueDateuscan() throws Exception {

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "USCAN\\\"/\\\\|*?<>:", "Combined<:\"\"? >\\/Company/*\\\\|",
				"456789012", "6310 Clift Street", "Shreveport", "Louisiana", "71105", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "United States", "Georgia",
				"GA Sales/Use Tax Report ST-3", "5742", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount",
				"1.00", "", "", "", "");
		configuration.AddLocations("Combined<:\"\"? >\\/Company/*\\\\|", "Georgia", "VGAL01", "VGA Location", "",
				"01/01/2017", "8821 Ga 15 Hwy N", "Warthen", "", "31094", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "United States", "North Dakota",
				"ND ST - Sales and Use Tax Return", "52120", "Mail a paper return", "Semi-annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("Combined<:\"\"? >\\/Company/*\\\\|", "North Dakota", "VNDL01", "VND Location", "",
				"01/01/2017", "4262 110th Ave SW", "Dickinson", "", "58601-9280", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "United States", "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "54654", "Mail a paper return", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "United States", "Pennsylvania",
				"PA Prepayment Form for PA-3", "54654", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount",
				"1.00", "", "", "", "");
		configuration.AddLocations("Combined<:\"\"? >\\/Company/*\\\\|", "Pennsylvania", "VPAL01", "VPAL01", "",
				"01/01/2017", "1006 W 9th Ave", "King Of Prussia", "", "19406-1206", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "United States", "South Carolina",
				"SC Sales and Use Tax Return ST-3", "52205", "Mail a paper return", "Annually", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddLocations("Combined<:\"\"? >\\/Company/*\\\\|", "South Carolina", "VSCL01", "VSC Location", "",
				"01/01/2017", "1013 Mount Vernon Dr", "North Myrtle Beach", "", "29582", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "United States", "South Dakota",
				"SD Sales/Use Tax Return", "55214", "Mail a paper return", "Semi-annually", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddLocations("Combined<:\"\"? >\\/Company/*\\\\|", "South Dakota", "VSD01", "VSD Location", "",
				"01/01/2017", "431 N Perry Ln", "Harrisburg", "", "57032-2355", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "United States", "Tennessee",
				"TN Sales and Use Tax Return SLS 450", "85120", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("Combined<:\"\"? >\\/Company/*\\\\|", "Tennessee", "VTNL01", "VTN Location", "",
				"01/01/2017", "8818 S Poplar Lake Dr", "Germantown", "", "38138-7727", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "United States", "Vermont",
				"VT Sales/Use Tax Return (SU-451)", "12345678901", "Mail a paper return", "Quarterly", "", "", "", "",
				"", "", "", "", "", "");
		configuration.AddLocations("Combined<:\"\"? >\\/Company/*\\\\|", "Vermont", "VVTL01", "VVT Location", "",
				"01/01/2017", "176 Center Rd", "Morrisville", "", "05661-8577", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "United States", "West Virginia",
				"WV Sales and Use Tax Return CST-200CU", "85251", "Mail a paper return", "Annually", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("Combined<:\"\"? >\\/Company/*\\\\|", "West Virginia", "VWVL01", "VWV Location", "",
				"01/01/2017", "84 Central Ave", "Romney", "", "26757-1202", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "United States", "Wisconsin",
				"WI State/County Sales/Use Tax ST-12", "82551", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddLocations("Combined<:\"\"? >\\/Company/*\\\\|", "Wisconsin", "VWIL01", "VWI Location", "",
				"01/01/2017", "8818 Greenview Ln", "Greendale", "", "53129-1551", "", "");
		configuration.AddJurisdictions("Combined<:\"\"? >\\/Company/*\\\\|", "CANADA", "Canada",
				"CN GST/HST Return (Non personalized)", "557777778", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddLocations("Combined<:\"\"? >\\/Company/*\\\\|", "Canada", "CA", "Canada Location", "",
				"01/01/2017", "800 Griffiths Way", "Vancouver", "British Columbia", "V6B 6G1", "", "");

		configuration.ConfigPush("Combined<:\"\"? >\\/Company/*\\\\|");
		configuration.VerifyConfigPush("Combined<:\"\"? >\\/Company/*\\\\|");
		// *************************************************************************************************************
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "Combined<:\"\"? >\\/Company/*\\\\|", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		transactions.CalculateTaxes("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedDate(0, -2, 0), "212011", "", "", "United States", "Georgia", "30022", "", "-1254.87",
				"1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedDate(0, -1, 0), "212012", "", "", "United States", "Georgia", "30022", "", "1936.293",
				"1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedDate(0, -2, 0), "212013", "", "", "United States", "Pennsylvania", "19406", "",
				"-101.459", "1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedDate(0, -1, 0), "212014", "", "", "United States", "Pennsylvania", "19406", "",
				"-50.2178", "1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedDate(0, -2, 0), "212015", "", "", "United States", "Tennessee", "37101", "", "-3345.98",
				"1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedDate(0, -1, 0), "212016", "", "", "United States", "Tennessee", "37101", "", "2986.36",
				"1", "", "", "", "", "", "", "");
		transactions.CalculateTaxes("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedDate(0, -2, 0), "212017", "", "", "United States", "Wisconsin", "54409", "", "1000", "1",
				"", "", "", "", "", "", "");
		transactions.CalculateTaxes("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedDate(0, -1, 0), "212016", "", "", "United States", "Wisconsin", "54409", "", "2000", "1",
				"", "", "", "", "", "", "");

		transactions.CalculateTaxes("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedDate(0, -2, 0), "212011", "", "", "Canada", "Ontario", "M5S 1A1", "", "-1200", "1", "",
				"", "", "", "", "", "");
		transactions.CalculateTaxes("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedDate(0, -1, 0), "212011", "", "", "Canada", "Ontario", "M5S 1A1", "", "-1500", "1", "",
				"", "", "", "", "", "");

		transactions.syncTime();
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		// Generate Returns for Current Month -2

		manageCustomers.GenerateReturnsfromAdmin(clientname, "Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-2, 0), "");
		manageCustomers.verifyreturnsGeneratedfromAdmin(clientname,
				"Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)", xGetCustomizedMon(-2, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		// Generate Returns for Current Month -1

		manageTaxreturns.Returnsgenerate("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-1, 0));
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "Combined<:\"\"? >\\/Company/*\\\\| (USCAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-1, 0), "");

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-16322
	@Test(dependsOnMethods = { "docheckDueDateuscan" }, priority = 7)
	public void doDiscountcolumnforuscanada() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)",
				xGetCustomizedMon(-1, 0), "USA_PremiumReturns_Reconciliation");
		manageCustomers.paymentrequestexport(clientname, "Canada<:\"\"? >\\/Company/*\\\\| (CAN\\/\\\\|*?<>:)",
				xGetCustomizedMon(-1, 0), "USA_PremiumReturns_Reconciliation");

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-16323
	@Test(dependsOnMethods = { "docheckDueDateusa" }, priority = 8)
	public void dototalamountmatcheswithPDF() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.paymentrequestexport(clientname, "USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)",
				xGetCustomizedMon(-1, 0), "USA_PremiumReturns_Reconciliation");

		manageTaxreturns.downloadReturns("USA Company\"/\\\\|*?<>: (USA/\\|*?<>:)", xGetCustomizedMon(-1, 0), "", "");

		userRegistration.Logout();

	}

	@AfterTest
	public void closebrowser() throws Exception {
		quitDriver(driver);

	}

}