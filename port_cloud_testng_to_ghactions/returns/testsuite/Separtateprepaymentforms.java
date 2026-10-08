package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class Separtateprepaymentforms extends CommonFunctions {

	public Separtateprepaymentforms() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "SeparatePrepayments" + RND() + "@simulator.amazonses.com";
	String clientname = "SeparatePrepaymentsTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {

		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-10944
	@Test(priority = 0)
	public void doconfigureoccasionalreturns() throws Exception {

		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, clientname, username, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Premium", "", POD);
		userRegistration.AddtionalFeatures("ECW", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "555 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");

		userRegistration.Logout();
		System.out.println("User Name is:" + username);

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		configuration.AddTaxPayer("United States", "OC01", "Occasional Company", "345678912", "306 Main St",
				"Sturbridge", "Massachusetts", "01566", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Hawaii",
				"HI General Excise/Use Tax Return G-45", "23456789112", "Web File", "Occasional", "", "", "", "", "",
				"", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Hawaii",
				"HI General Excise/Use Tax Return G-45", "W23456789112", "Web File", "Semi-annually", "", "", "", "",
				"", "", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Hawaii",
				"HI Annual Return/Reconciliation G-49", "6789456", "Web File", "Annually", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Kentucky",
				"KY Sales/Use Tax Return 51A102", "041123457", "Web File", "Occasional", "", "", "", "", "", "", "", "",
				"", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "North Dakota",
				"ND ST - Sales and Use Tax Return", "121212", "Web File", "Semi-annually", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Rhode Island",
				"RI Consumer's Use Tax Return T-205", "12345678911", "Web File", "Occasional", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "South Dakota", "SD Sales/Use Tax Return",
				"ST12345678", "Web File", "Occasional/Annually", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "South Dakota", "SD Sales/Use Tax Return",
				"ST12345678", "Web File", "Bi-monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("Occasional Company", "United States", "Idaho",
				"ID-STANLEY Non-Property Sales Tax Return", "1234568", "Web File", "Occasional", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Idaho", "ID Sales/Use Tax Return 850",
				"1234568", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "1234567", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");
		configuration.AddJurisdictions("Occasional Company", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"12312123451", "Web File", "Quarterly", "", "", "", "", "Flat Amount", "500", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "New Jersey",
				"NJ Monthly Sales/Use Tax Return ST-51", "123456789", "Web File", "First Two Months of Quarter", "", "",
				"", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("Occasional Company", "United States", "Connecticut",
				"CT Sales/Use Tax Return OS-114", "1234568", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");
		configuration.AddLocations("Occasional Company", "Connecticut", "VCT1", "CT Location", "", "01/01/2017",
				"1803 Cheshire Road", "STAMFORD", "", "06901", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Tennessee",
				"TN Sales and Use Tax Return SLS 450", "987654321", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddLocations("Occasional Company", "Tennessee", "VTN1", "TN Location", "", "01/01/2017",
				"1482 Frum Street", "NASHVILLE-DAVIDSON", "", "37201", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "South Carolina",
				"SC Sales and Use Tax Return ST-3", "40451234512345", "Web File", "Annually", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddLocations("Occasional Company", "South Carolina", "VSC1", "SC Location", "", "01/01/2017",
				"University Of South Carolina", "COLUMBIA", "", "29208-0001", "", "");

		configuration.AddJurisdictions("Occasional Company", "CANADA", "Canada", "CN GST/HST Return (Non personalized)",
				"123456789", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("Occasional Company", "Canada", "VCN1", "CN Location", "", "01/01/2017",
				"27 King's College Circle", "TORONTO", "Ontario", "M5S 1A1", "", "");
		configuration.AddJurisdictions("Occasional Company", "CANADA", "Canada", "CN-SASKATCHEWAN Provincial Sales Tax",
				"123456789", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "CANADA", "Canada", "CN-QUEBEC QST Return", "123456789",
				"Web File", "Annually", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "CANADA", "Canada", "CN-MANITOBA Vendor's Return",
				"123456789", "Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");

		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("Occasional Company", "United States", "Florida",
				"FL Sales/Use Tax DR-15 (Per Location)", "123", "Web File", "Monthly", "", "", "", "", "Flat Amount",
				"200", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Florida",
				"FL Sales/Use Tax Return DR-15", "123", "Web File", "Monthly", "", "", "", "", "Flat Amount", "40", "",
				"", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Kansas",
				"KS Retailer's Sales Tax Return ST-36", "123456789123456", "Web File", "Annually", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Kansas",
				"KS Retailer's Tax Return CT-9U", "123456789123456", "Web File", "Monthly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Kansas",
				"KS Consumer's Use Tax Return CT-10U", "1234567891234560", "Web File", "Monthly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Kentucky",
				"KY Sales/Use Tax Return 51A103", "1234", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30",
				"", "", "", "");

		configuration.AddJurisdictions("Occasional Company", "United States", "North Carolina",
				"NC Sales and Use Tax Return E-500", "S123456789", "Web File", "Quarterly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "North Carolina",
				"NC-Eastern Band of Cherokee Tribal Ret", "S123456789", "Web File", "Monthly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Ohio",
				"OH UST-1 Seller's Use and Rental/Lease", "993456790", "Web File", "Monthly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Ohio", "OH UST-1 Seller's Use",
				"993456789", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Ohio", "OH UST-1 Sales", "013456790",
				"Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Ohio", "OH UST-1 Sales and Rental/Lease",
				"48011234567", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Oklahoma",
				"OK Sales Tax Prepayment Return", "7584211", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30",
				"", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Oklahoma",
				"OK Vendor's Use Prepayment Return", "7584211", "Web File", "Monthly", "", "", "", "", "Flat Amount",
				"30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Oklahoma",
				"OK Consumer's Use Tax Report SCU20004", "7584211", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Oklahoma",
				"OK Sales Tax Report STS20002", "7584211", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");
		configuration.AddJurisdictions("Occasional Company", "United States", "Oklahoma",
				"OK Vendor's Use Tax Report SVU20005", "7584211", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Oklahoma",
				"OK Consumers Use Prepayment Return", "7584211", "Web File", "Monthly", "", "", "", "", "Flat Amount",
				"30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "New York",
				"NY Part-Qrtly Sales/Use (Qrtly) ST-810", "123654", "Web File", "Quarterly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "New York",
				"NY Part-Qrtly Sales/Use (Mthly) ST-809", "557777777", "Web File", "First Two Months of Quarter", "",
				"", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Illinois",
				"IL Sales/Use Tax Return/E911 ST-1", "123456789123435", "Web File", "Monthly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Illinois",
				"IL Sales/Use Tax Prepayment Return", "123456789123435", "Web File", "Monthly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Pennsylvania",
				"PA Prepayment Form for PA-3", "1234567", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30",
				"", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "1234567", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");
		configuration.AddLocations("Occasional Company", "Pennsylvania", "VPA1", "PA Location", "", "01/01/2017",
				"1006 W 9th Ave ", "King Of Prussia", "", "19406-1206", "", "");

		configuration.AddJurisdictions("Occasional Company", "United States", "Alaska",
				"AK-SITKA City/Borough Mthly Prepayment", "12345679", "Web File", "First Two Months of Quarter", "", "",
				"", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Alaska",
				"AK-JUNEAU Monthly Deposit Slip", "12345679", "Web File", "Monthly", "", "", "", "", "Flat Amount",
				"30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Mississippi", "MS Use Tax Return 72-110",
				"12345678901234500", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Mississippi",
				"MS Unrounded Use Tax Report", "12345678901234500", "Web File", "Monthly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Mississippi",
				"MS Sales Tax Return 72-010", "557777777", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30",
				"", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Mississippi",
				"MS Sales Tax Return 72-001", "121212", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "",
				"", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Mississippi",
				"MS June Accelerated Payment Return", "12345679", "Web File", "Annually", "", "", "", "", "Flat Amount",
				"30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "West Virginia",
				"WV Sales and Use Tax Return CST-200CU", "123456123456", "Web File", "Annually", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Arkansas",
				"AR Sales Tax - Direct Pay Filers", "123456987", "Web File", "Monthly", "", "", "", "", "Flat Amount",
				"30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Arkansas",
				"AR Use Tax - Direct Pay Filers", "123456987", "Web File", "Monthly", "", "", "", "", "Flat Amount",
				"30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Arkansas",
				"AR Excise Sales/Use Tax Report", "123456987", "Web File", "Monthly", "", "", "", "", "Flat Amount",
				"30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Virginia",
				"VA Dealer's Sales/Use Tax ST-9", "123452345612345", "Mail a paper return", "Monthly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Virginia",
				"VA Out-of-State Dealer w/Rent & CU ST-8", "123452345612345", "Mail a paper return", "Monthly", "", "",
				"", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Virginia",
				"VA Dealer's Sales/Use Tax w/CU ST-9", "123452345612345", "Mail a paper return", "Monthly", "", "", "",
				"", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Virginia",
				"VA Dealer's Sales/Use Tax w/Rental ST-9", "123452345612345", "Mail a paper return", "Monthly", "", "",
				"", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Virginia",
				"VA Dealer's Sales/Use w/Rent & CU ST-9", "123452345612345", "Mail a paper return", "Monthly", "", "",
				"", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Virginia",
				"VA Out-of-State Dealer Use ST-8", "123452345612345", "Mail a paper return", "Monthly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Virginia",
				"VA Out-of-State Dealer Use w/CU ST-8", "123452345612345", "Mail a paper return", "Monthly", "", "", "",
				"", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Virginia",
				"VA Out-of-State Dealer Use w/Rental ST-8", "123452345612345", "Mail a paper return", "Monthly", "", "",
				"", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Texas", "TX Sales/Use Tax Return 01-114",
				"12345678911", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Missouri", "MO Sales Tax Return 53-1",
				"12345679", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("Occasional Company", "United States", "Missouri", "MO Vendor's Use Return 53-V",
				"12345679", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.CreateProduct("Occasional Company", "Seller", "CS01", "Product Code",
				"Cleaning/Janitorial Services", "01/01/2017", "", "General", "Services", "Facility Maintenance",
				"Cleaning/Janitorial Services", "", "", "", "", "");
		configuration.ExemptCustomer("Occasional Company", "", "Resale", "Exempt", "Code", "C01", "Customer1",
				"01/01/2017", "");
		configuration.ExemptCustomer("Occasional Company", "", "Resale", "Exempt", "Code", "C02", "Customer2",
				"01/01/2017", "");
		configuration.ConfigPush("Occasional Company");
		configuration.VerifyConfigPush("Occasional Company");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.GoLive("Occasional Company");

		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		premiumCustomers.onboardCustomer(clientname, "Occasional Company", xGetCustomizedMon(-10, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.CalculateTaxes("Occasional Company (OC01)", xGetCustomizedDate(0, -1, 0), "5520112014", "", "",
				"United States", "Hawaii", "96722", "", "3000", "1", "", "", "", "", "", "", "");

		transactions.CalculateTaxes("Occasional Company (OC01)", xGetCustomizedDate(0, -1, 0), "5520112014", "", "",
				"United States", "Texas", "75244-4902", "", "1500", "1", "", "", "", "", "", "", "");
		transactions.UploadAdjustmentFile("Sales_Invoice_Credits", "Invoice", "Seller", "Sales_Invoice_Credits_suc",
				"Yes", "");
		transactions.UploadAdjustmentFile("Canadians_invoice_Credits", "Invoice", "Canadian Transactions",
				"Canadians_invoice_Credits_suc", "Yes", "");
		transactions.UploadAdjustmentFile("CUT-Distribute_Credits", "Distribute Tax", "Buyer",
				"CUT-Distribute_Credits_suc", "Yes", "");
		transactions.syncTime();
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-9986
	@Test(dependsOnMethods = { "doconfigureoccasionalreturns" }, priority = 1)
	public void doprepayments() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Florida",
				"FL Sales/Use Tax DR-15", "100", "");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Florida",
				"FL Sales/Use Tax Return DR-15", "100", "");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Kentucky",
				"KY Sales/Use Tax Return 51A103", "100", "Prepayment Rule");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Missouri",
				"MO Sales Tax Return 53-1", "100", "Prepayment Week 1");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "New York",
				"NY Part-Qrtly Sales/Use (Qrtly) ST-810", "100", "PrompTax");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Ohio",
				"OH UST-1 Sales and Rental/Lease", "100", "");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Ohio", "OH UST-1 Sales",
				"100", "");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Arkansas",
				"AR Use Tax - Direct Pay Filers", "100", "Use Prepayment");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Oklahoma",
				"OK Sales Tax Report STS20002", "100", "Prepayment Rule");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "100", "");
		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Illinois",
				"IL Sales/Use Tax Return/E911 ST-1", "100", "Prepayment Rule");

		prepayments.EnterPrepayments("Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Texas",
				"TX Sales/Use Tax Return 01-114", "100", "");

	}

	// https://vertexinc.atlassian.net/browse/CD-9987
	@Test(dependsOnMethods = { "doprepayments" }, priority = 2)
	public void dogeneratereturns() throws Exception {

		manageTaxreturns.Returnsgenerate("Occasional Company (OC01)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("Occasional Company (OC01)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-9989
	@Test(dependsOnMethods = { "dogeneratereturns" }, priority = 3)
	public void dodownloadpayments() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		prepayments.GeneratePrepayment("Occasional Company (OC01)", xGetCustomizedMon(0, 0));
		prepayments.DownloadPrepayment("Occasional Company (OC01)", xGetCustomizedMon(0, 0));

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-9990
	@Test(dependsOnMethods = { "dodownloadpayments" }, priority = 4)
	public void doadminreports() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.CreditReportfromAdmin(clientname, "Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "");

		manageCustomers.ReturnsGenerationreport(clientname, "Authorized", "Occasional Company", "Live", "51");
		manageCustomers.ReturnFillingFrequencyReport("Live", "OC01", "Premium", "63", "44", "1", "4", "2", "6", "3",
				"3", "0", "0", "0");

		manageCustomers.AdminPrepayments(clientname, "Occasional Company (OC01)", xGetCustomizedMon(-1, 0), "Yes",
				"PA Sales/Use/Hotel Tax PA-3", "Pennsylvania");

		userRegistration.Logout();
	}

	@AfterClass
	public void closeBrowser() throws Exception {
		quitDriver(driver);

	}

}
