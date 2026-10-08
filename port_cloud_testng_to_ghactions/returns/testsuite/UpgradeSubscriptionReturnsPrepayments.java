package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.enums.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class UpgradeSubscriptionReturnsPrepayments extends CommonFunctions {

	public UpgradeSubscriptionReturnsPrepayments() throws Exception {
		super();
	}

	private WebDriver driver;
	String username = "UpgradeSubscriptionPrepayments" + RND() + "@simulator.amazonses.com";
	String clientname = "UpgradeSubscriptionPrepaymentsTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-20478
	@Test(priority = 0)
	public void dostandardregistrationTest() throws Exception {

		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, clientname, username, "Canada",
				"60 Carlton St", "Toronto", "Ontario", "M5B 1J1", "Standard", "", POD);
		userRegistration.AddtionalFeatures("Real - Time Rate Service,Taxability Impact Report", "", "", "", "", "", "",
				"", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling("Canada", "60 Carlton St", "Toronto", "Ontario", "M5B 1J1", "4111111111111111",
				"Dec (12)", xGetCustomizedYear(2), "123");
		System.out.println("User Name is:" + username);

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactionReport.IndirectTaxIntelligencenotDisplayed();
		userRegistration.VerifyNoITIUserStandard();

		userRegistration.Logout();
		userRegistration.UserLogin(adminusername, adminpassword);
		cpaPortal.AdminVerifyNOITIUserStandard(clientname);
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-9668
	@Test(priority = 1)
	public void dochangesubscription() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.ChangeSubscription(clientname, "Professional", "Rate File", "");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-9671
	@Test(dependsOnMethods = { "dochangesubscription" }, priority = 2)
	public void doadjustmentfile() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		restAPI.RESTconfigureCompany("HI01", "KitKat, Inc", "123456789", "01/01/2017",
				enumAddress.Linfield.addressLine1, enumAddress.Linfield.city, enumState.PA.fullName,
				enumAddress.Linfield.zip5);

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Texas", "TX Sales/Use Tax Short Form 01-117",
				"SU1234567890", "Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddLocations("KitKat, Inc", "Texas", "12", "TX manufact", "", "01/01/2017", "4799 Airport Pkwy",
				"Addison", "", "75001-3321", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "California", "CA Sales/Use Prepayment Form",
				"1234567", "Web File", "First Two Months of Quarter", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "California",
				"CA St/Loc/Dist Sales & Use CDTFA-401-A", "ABC123765", "Web File", "Quarterly", "", "", "", "", "", "",
				"", "", "", "");

		configuration.AddLocations("KitKat, Inc", "California", "15", "CA", "", "01/01/2017", "1750 Lundy Ave",
				"San Jose", "", "95101", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Florida",
				"FL Sales/Use Tax DR-15 (Per Location)", "123", "Web File", "Monthly", "", "", "", "", "Flat Amount",
				"30", "", "", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Florida", "FL Sales/Use Tax Return DR-15",
				"123456789123415", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");

		configuration.AddLocations("KitKat, Inc", "Florida", "FL1", "FL1", "", "01/01/2017",
				"5050 West Tennessee Street", "Tallahassee", "", "32399", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"12312123451", "Web File", "Monthly", "", "", "", "", "Flat Amount", "500", "", "", "", "");

		configuration.AddLocations("KitKat, Inc", "Georgia", "GA1", "GA1", "", "01/01/2017", "943 Aycock Mill Rd",
				"Shellman", "", "39886", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Arizona",
				"AZ Annual Est. Payment Trans. Priv. Tax", "56789", "Web File", "Annually", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Arizona", "AZ-YAVAPAI PRESCOTT Indian Tribe",
				"9876", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Arizona", "Azloc", "AZ Cake", "", "01/01/2017", "1600 West Monroe",
				"Phoenix", "", "85007", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Colorado", "CO Retail Sales Tax Return DR0100",
				"371456789012345", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Colorado", "CO-WINDSOR Sales Tax Return",
				"1234567", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddLocations("KitKat, Inc", "Colorado", "Coloc1", "Coloc1", "", "01/01/2017",
				"825 E. Fillmore Street", "Colorado Colorado", "", "80907", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Pennsylvania", "PA Prepayment Form for PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"123456", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Pennsylvania", "14", "PA Chocolate", "", "01/01/2017",
				"1550 Liberty Ridge", "Chesterbrook", "", "19087-5565", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Illinois", "IL Sales/Use Tax Prepayment Return",
				"123456789123435", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Illinois", "IL Sales/Use Tax Return/E911 ST-1",
				"123456789123435", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Illinois", "Illoc1", "Illoc", "", "01/01/2017",
				"100 N University St", "Normal", "", "61761", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Arkansas", "AR Excise Sales/Use Tax Report",
				"123456789", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Arkansas", "AR Sales Tax - Direct Pay Filers",
				"123456987", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Arkansas", "AR Use Tax - Direct Pay Filers",
				"123654", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "United States", "Wisconsin",
				"WI State/County Sales/Use Tax ST-12", "123451234512345", "Web File", "Monthly", "", "", "", "", "", "",
				"", "", "", "");

		configuration.AddJurisdictions("KitKat, Inc", "CANADA", "Canada", "CN-BRITISH COLUMBIA Provincial Sales Tax",
				"123456789", "Web File", "Monthly", "", "", "5555785", "", "", "", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Canada", "CA01", "Canada!#", "", "01/01/2017", "800 Griffiths Way",
				"Vancouver!@#", "British Columbia", "V6B 6G1", "", "");

		configuration.AddLocations("KitKat, Inc", enumState.AL.fullName, "4", "AL Tandy", "", "01/01/2017",
				"2810 35th St", "Tuscaloosa", "", " 35401-6429", "", "");
		configuration.AddLocations("KitKat, Inc", enumState.OR.fullName, "ORLoc001", "University of Oregon", "121231",
				"01/01/2017", "1585 E 13th Ave", "Eugene", "", "97403", "", "");
		configuration.AddLocations("KitKat, Inc", enumState.NH.fullName, "NHLoc001", "University of New Hampshire",
				"121231", "01/01/2017", "105 Main St", "Durham", "", "03824", "", "");

		configuration.AddLocations("KitKat, Inc", "Delaware", "DELoc001", "Delaware State University", "111521",
				"01/01/2017", "1200 N Dupont Hwy", "Dover", "", "19901", "", "");

		configuration.AddLocations("KitKat, Inc", "Montana", "MOLoc002", "University of Montana", "121231",
				"01/01/2017", "32 Campus Dr", "Missoula", "", "59812", "", "");

		reports.TaxabilityReports("Yes", "", "", "", "", "", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "VSProduct", "Product Code",
				"Home or Non-Immediate Consumption", "01/01/2017", "", "General", "TPP/Goods", "Food & Beverages",
				"For Home or Non-Immediate Consumption", "", "", "", "", "");
		configuration.TaxableCustomer("KitKat, Inc", "Code", "T001", "Taxable Customer", "01/01/2017", "", "Taxable");
		// configuration.UploadCertificate("KitKat, Inc", "MultipleCerti");
		configuration.ConfigPush("KitKat, Inc");
		configuration.VerifyConfigPush("KitKat, Inc");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.GoLive("KitKat, Inc");

		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CUT_Accrual_Val", "Accrual", "Buyer", "");
		transactions.UploadAdjustmentFile("CUT_Asset-GoodsMovement_Val", "Asset/Goods Movement", "Buyer", "");
		transactions.UploadAdjustmentFile("CUT_DistributeTaxProcurement_Val", "Distribute Tax", "Buyer", "");
		transactions.UploadAdjustmentFile("CUT_InventoryRemoval_Val", "Inventory Removal", "Buyer", "");
		transactions.UploadAdjustmentFile("CUT_PurchaseOrder_Val", "Purchase Order", "Buyer", "");
		transactions.UploadAdjustmentFile("Sales_DistributedTax_Val", "Distribute Tax", "Seller", "");
		transactions.UploadAdjustmentFile("Sales_InvoiceTax_Val", "Invoice", "Seller", "");
		transactions.UploadAdjustmentFile("Sales_QuotationTax_Val", "Quotation", "Seller", "");

		transactions.UploadAdjustmentFile("CUT_Accural_Flexcode", "Accural", "Buyer", "");
		transactions.UploadAdjustmentFile("CUT_Asset-GoodsMovement_Flexcode", "Asset/Goods Movement", "Buyer", "");
		transactions.UploadAdjustmentFile("CUT_DistributeTaxProcurement_Flexcode", "Distribute Tax", "Buyer", "");
		transactions.UploadAdjustmentFile("CUT_InventoryRemoval_Flexcode", "Inventory Removal", "Buyer", "");
		transactions.UploadAdjustmentFile("CUT_PurchaseOrder_Flexcode", "Purchase Order", "Buyer", "");
		transactions.UploadAdjustmentFile("Sales_DistributedTax_Flexcode", "Distribute Tax", "Seller", "");
		transactions.UploadAdjustmentFile("Sales_InvoiceTax_Flexcode", "Invoice", "Seller", "");
		transactions.UploadAdjustmentFile("Sales_QuotationTax_Flexcode", "Quotation", "Seller", "");

		transactions.UploadAdjustmentFile("AdjustmentFileFormat_1steachworduppercase", "Distribute Tax", "Seller", "");
		transactions.UploadAdjustmentFile("AdjustmentFileFormat_1stletterUppercase", "Distribute Tax", "Seller", "");
		transactions.UploadAdjustmentFile("AdjustmentFileFormat_1stWordAllLower2ndwordAllUpper", "Distribute Tax",
				"Seller", "");
		transactions.UploadAdjustmentFile("AdjustmentFileFormat_1stWordAllUpper2ndwordAllLower", "Distribute Tax",
				"Seller", "");
		transactions.UploadAdjustmentFile("AdjustmentFileFormat_1stWordlowercaseNextworduppercase", "Distribute Tax",
				"Seller", "");
		transactions.UploadAdjustmentFile("AdjustmentFileFormat_AllColumnsinLowercase", "Distribute Tax", "Seller", "");
		transactions.UploadAdjustmentFile("AdjustmentFileFormat_AllColumnsinUppercase", "Distribute Tax", "Seller", "");
		transactions.UploadAdjustmentFile("AdjustmentFileFormat_MixofUpperLower", "Distribute Tax", "Seller", "");
		transactions.syncTime();
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18902
	@Test(dependsOnMethods = { "doadjustmentfile" }, priority = 3, enabled = true)
	public void doprepayments() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		prepayments.EnterPrepayments("KitKat, Inc (HI01)", xGetCustomizedMon(0, 0), "Arkansas",
				"AR Use Tax - Direct Pay Filers", "1800", "Use Prepayment");
		prepayments.EnterPrepayments("KitKat, Inc (HI01)", xGetCustomizedMon(0, 0), "Florida",
				"FL Sales/Use Tax DR-15 (Per Location)", "5000", "");
		prepayments.EnterPrepayments("KitKat, Inc (HI01)", xGetCustomizedMon(0, 0), "Georgia",
				"GA Sales/Use Tax Report ST-3", "1000", "");

		prepayments.GeneratePrepayment("KitKat, Inc (HI01)", xGetCustomizedMon(0, 0));

		manageTaxreturns.Returnsgenerate("KitKat, Inc (HI01)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("KitKat, Inc (HI01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18903
	@Test(dependsOnMethods = { "doprepayments" }, priority = 4, enabled = true)
	public void dodownloadpayments() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		prepayments.DownloadPrepayment("KitKat, Inc (HI01)", xGetCustomizedMon(0, 0));
		manageTaxreturns.downloadReturns("KitKat, Inc (HI01)", xGetCustomizedMon(-1, 0), "", "");
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-9672
	@Test(dependsOnMethods = { "dodownloadpayments" }, priority = 5)
	public void doapplyprepaymentgeneratereturns() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.ChangeSubscription(clientname, "Premium", "", "");
		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "KitKat, Inc", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		prepayments.EnterPrepayments("KitKat, Inc (HI01)", xGetCustomizedMon(-1, 0), "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "1.00", "");
		manageTaxreturns.Returnsgenerate("KitKat, Inc (HI01)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("KitKat, Inc (HI01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-9675
	@Test(dependsOnMethods = { "doapplyprepaymentgeneratereturns" }, priority = 6)
	public void doadmincreditreport() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.CreditReportfromAdmin(clientname, "KitKat, Inc (HI01)", xGetCustomizedMon(-1, 0), "");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-9679
	@Test(dependsOnMethods = { "doadmincreditreport" }, priority = 7)
	public void downloadreturns() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		manageTaxreturns.downloadReturns("KitKat, Inc (HI01)", xGetCustomizedMon(-1, 0), "", "");
		userRegistration.Logout();
	}

	@AfterTest
	public void closeBrowser() throws Exception {
		quitDriver(driver);
	}

}
