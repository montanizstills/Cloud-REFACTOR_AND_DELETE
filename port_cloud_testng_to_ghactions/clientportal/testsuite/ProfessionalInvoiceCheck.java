package clientportal.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class ProfessionalInvoiceCheck extends CommonFunctions {

	String taxcode1 = "GS,";
	String taxcode2 = "01#/";
	String taxpayercode = taxcode1.concat(taxcode2);

	public ProfessionalInvoiceCheck() throws Exception {
		super();
	}

	private WebDriver driver;
	String UserName = "ProTCRSInvoiceDIGC" + RND() + "@simulator.amazonses.com";
	String ClientName = "ProTCRSInvoiceDIGCClient" + RND();
	String UserName1 = "PremiumWB" + RND() + "@simulator.amazonses.com";
	

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// userRegistration
	// 114308;114309;114310;114311;114324;114335;114248;1146807;1146813
	// https://vertexinc.atlassian.net/browse/CD-11738
	/***************** Invoice user registration ************************/
	@Test(priority = 0)
	public void userRegistrationTest() throws Exception {
		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, ClientName, UserName, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Professional",
				"Tax Calculations and Returns", POD);
		userRegistration.AddtionalFeatures("INVOICE,GLOBAL COMPLIANCE,DATA INTEGRITY", "US", "Check", "", "India",
				"KRISHE SAPPHIRE, 4th and 5th Floor", "Hyderabad", "Telangana", "500081");
		userRegistration.CompleteRegistration("FN" + RND(), "LN" + RND(), "555 555 5552", "Password!1", "Password!1");
		System.out.println("User name is " + UserName);
	}

	// SMBCustomerCreateNewUser
	// 303948;228047;228048;228049;228051;228056;228059;228060;228063;228065;228066
	// https://vertexinc.atlassian.net/browse/CD-11739
	/************
	 * change oracle party number, create new user from settings and Admin
	 ************/
	@Test(priority = 1)
	public void SMBCustomerCreateNewUserTest() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		adminPortal.ChangeOraclePartyNumber(ClientName, "", "");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(UserName, "Password!1");
		userRegistration.CreateNewUser("TESTFN" + RND(), "TESTLN" + RND(), "vtmsU" + RND() + "@simulator.amazonses.com",
				"111111111111111", "Admin", "", "", "", "", "", "Password!1", "Password!1");
		userRegistration.CreateNewUser("TESTFN" + RND(), "TESTLN" + RND(), "vtmsU" + RND() + "@simulator.amazonses.com",
				"111111111111111", "", "Read Only", "", "", "", "", "Password!1", "Password!1");
		userRegistration.Logout();
	}

	// ConfigureCompany 1146870
	// https://vertexinc.atlassian.net/browse/CD-11740
	/*************** Configure Taxpayer ********/
	@Test(priority = 2)
	public void ConfigureCompanyTest() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		cpaPortal.AdminCreateNewUserNew(ClientName, "TESTFN" + RND(), "TESTLN" + RND(), "vtmsAu" + RND() + "@simulator.amazonses.com",
				"111111111111111", "Admin", "", "", "", "", "Password!1");
		cpaPortal.AdminCreateNewUserNew(ClientName, "TESTFN" + RND(), "TESTLN" + RND(), "vtmsAu" + RND() + "@simulator.amazonses.com",
				"111111111111111", "Read Only", "", "", "", "", "Password!1");
		cpaPortal.AdminCreateNewUserNew(ClientName, "TESTFN" + RND(), "TESTLN" + RND(), "vtmsAu" + RND() + "@simulator.amazonses.com",
				"111111111111111", "ECM Only", "", "", "", "", "Password!1");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(UserName, "Password!1");
		configuration.AddTaxPayer("United States", taxpayercode, "German Shepherd, Inc", "345678912", "306 Main St",
				"Sturbridge", "Massachusetts", "01566", "", "");
	}

	// ChangeSubscription 114343;114271;114272
	// https://vertexinc.atlassian.net/browse/CD-11741
	/******************
	 * Configuration,Golive and upload adjustment files
	 *****************/
	@Test(priority = 3)
	public void ChangeSubscriptionTest() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(UserName, "Password!1");
		configuration.AddLocations("German Shepherd, Inc", "Pennsylvania", "14", "PA Kitkat", "", "01/01/2017",
				"1550 Liberty Ridge", "Chesterbrook", "", "19087-5565", "", "12345");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Pennsylvania",
				"PA Prepayment Form for PA-3", "12335678", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30",
				"", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "123356789", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddLocations("German Shepherd, Inc", "Colorado", "Colorado5", "Colorado5", "", "01/01/2017",
				"825 E. Fillmore Street", "Colorado Springs", "", "81247", "", "12345");
		configuration.AddLocations("German Shepherd, Inc", "Colorado", "Colorado6", "Colorado6", "", "01/01/2017",
				"825 E. Fillmore Street", "Colorado Springs", "", "80907", "", "12345");
		configuration.AddLocations("German Shepherd, Inc", "Pennsylvania", "PA4821", "PA4821", "", "01/01/2017",
				"36 Railroad St", "Linfield", "", "19468", "", "12345");
		configuration.UploadMappings("German Shepherd, Inc", "ProductsSample", ".csv");
		configuration.TaxableCustomer("German Shepherd, Inc", "Code", "T001", "Taxable Customer", "01/01/2017",
				"01/01/2025", "Taxable");
		configuration.AddCertificate("German Shepherd, Inc", "T001", "Taxable Customer", "01/01/2017", "01/01/2025",
				"Blanket", "", "", "Yes", "New Jersey", "Resale", "1234567899", "", "", "", "Exemption Certificate",
				"");
		configuration.CreateProduct("German Shepherd, Inc", "Seller", "Atv01", "Product Code", "All Terrain Vehicles",
				"01/01/2017", "01/01/2025", "General", "TPP/Goods", "Motor Vehicles and Transportation Equipment",
				"Automobiles", "All Terrain Vehicles", "", "", "", "");
		configuration.CreateProduct("German Shepherd, Inc", "Buyer", "Bb01", "Usage Class", "Books and Booklets",
				"01/01/2017", "01/01/2025", "General", "TPP/Goods", "Printed Matter", "Books and Booklets", "", "", "",
				"", "");
		configuration.CreateProduct("German Shepherd, Inc", "Buyer", "GS01", "Taxpayer", "German Shepherd, Inc",
				"01/01/2017", "01/01/2025", "General", "TPP/Goods", "Computer Software", "Canned Software", "", "", "",
				"", "");
		configuration.UpdateCustomerEndDate("German Shepherd, Inc", "T001", "Yes", "");
		configuration.ConfigPush("German Shepherd, Inc");
		configuration.VerifyConfigPush("German Shepherd, Inc");
		configuration.GoLive("German Shepherd, Inc");
		transactions.CalculateTaxes("German Shepherd, Inc (GS,01#/)", xGetCustomizedDate(-29, -1, 0), "120112014", "",
				"PA Kitkat", "United States", "Pennsylvania", "19101", "", "1000", "1", "$80.00", "$1,080.00", "",
				"Seller", "", "", "");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		adminPortal.AdminGenerateAPI(ClientName);
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(UserName, "Password!1");
		transactions.AdjustmentFileAPI("https://" + Environment.toLowerCase() + "auth.vertexsmb.com",
				"https://" + Environment.toLowerCase() + "api.vertexsmb.com",
				"pub-api-" + Environment.toLowerCase() + "", "upload", "e9507951e7294026a66c6455c876203c",
				"1BDB923ED7494E10BC85AFEB279A0F58", "Gs8_5D%f", "Accrual", "SDKAdjustmentFiles", "", "");
		transactions.AdjustmentFileAPI("https://" + Environment.toLowerCase() + "auth.vertexsmb.com",
				"https://" + Environment.toLowerCase() + "api.vertexsmb.com",
				"pub-api-" + Environment.toLowerCase() + "", "upload", "e9507951e7294026a66c6455c876203c",
				"1BDB923ED7494E10BC85AFEB279A0F58", "Gs8_5D%f", "AssetMovement", "SDKAdjustmentFiles", "", "");
		transactions.AdjustmentFileAPI("https://" + Environment.toLowerCase() + "auth.vertexsmb.com",
				"https://" + Environment.toLowerCase() + "api.vertexsmb.com",
				"pub-api-" + Environment.toLowerCase() + "", "upload", "e9507951e7294026a66c6455c876203c",
				"1BDB923ED7494E10BC85AFEB279A0F58", "Gs8_5D%f", "DistributeTax", "SDKAdjustmentFiles", "", "");
		transactions.AdjustmentFileAPI("https://" + Environment.toLowerCase() + "auth.vertexsmb.com",
				"https://" + Environment.toLowerCase() + "api.vertexsmb.com",
				"pub-api-" + Environment.toLowerCase() + "", "upload", "e9507951e7294026a66c6455c876203c",
				"1BDB923ED7494E10BC85AFEB279A0F58", "Gs8_5D%f", "InventoryRemoval", "SDKAdjustmentFiles", "", "");
		transactions.AdjustmentFileAPI("https://" + Environment.toLowerCase() + "auth.vertexsmb.com",
				"https://" + Environment.toLowerCase() + "api.vertexsmb.com",
				"pub-api-" + Environment.toLowerCase() + "", "upload", "e9507951e7294026a66c6455c876203c",
				"1BDB923ED7494E10BC85AFEB279A0F58", "Gs8_5D%f", "InvoiceVerification", "SDKAdjustmentFiles", "", "");
		transactions.AdjustmentFileAPI("https://" + Environment.toLowerCase() + "auth.vertexsmb.com",
				"https://" + Environment.toLowerCase() + "api.vertexsmb.com",
				"pub-api-" + Environment.toLowerCase() + "", "upload", "e9507951e7294026a66c6455c876203c",
				"1BDB923ED7494E10BC85AFEB279A0F58", "Gs8_5D%f", "PurchaseOrder", "SDKAdjustmentFiles", "", "");
		transactions.AdjustmentFileAPI("https://" + Environment.toLowerCase() + "auth.vertexsmb.com",
				"https://" + Environment.toLowerCase() + "api.vertexsmb.com",
				"pub-api-" + Environment.toLowerCase() + "", "upload", "e9507951e7294026a66c6455c876203c",
				"1BDB923ED7494E10BC85AFEB279A0F58", "Gs8_5D%f", "DistributeTax", "SDKAdjustmentFiles", "", "");
		transactions.AdjustmentFileAPI("https://" + Environment.toLowerCase() + "auth.vertexsmb.com",
				"https://" + Environment.toLowerCase() + "api.vertexsmb.com",
				"pub-api-" + Environment.toLowerCase() + "", "upload", "e9507951e7294026a66c6455c876203c",
				"1BDB923ED7494E10BC85AFEB279A0F58", "Gs8_5D%f", "Invoice", "SDKAdjustmentFiles", "", "");
		transactions.AdjustmentFileAPI("https://" + Environment.toLowerCase() + "auth.vertexsmb.com",
				"https://" + Environment.toLowerCase() + "api.vertexsmb.com",
				"pub-api-" + Environment.toLowerCase() + "", "upload", "e9507951e7294026a66c6455c876203c",
				"1BDB923ED7494E10BC85AFEB279A0F58", "Gs8_5D%f", "Quotation", "SDKAdjustmentFiles", "", "");
		userRegistration.Logout();
	}

	// AdminCloudClientBannerChanges 114281;114282
	// https://vertexinc.atlassian.net/browse/CD-11742
	/*************** Update billing information *******************/
	@Test(priority = 4)
	public void AdminCloudClientBannerChangesTest() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(UserName, "Password!1");
		userRegistration.UpdateBilling("yes", "", "United States", "36 Railroad Street", "", "Linfield", "Pennsylvania",
				"19468", "CC","4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123","", "");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		adminPortal.AdminCloudClientBannerChanges(ClientName, "", "NO", "Invoice - Check", "", "", "", "", "", "", "",
				"");
		userRegistration.Logout();
	}

	// EditCompanyUpdation 114266
	// https://vertexinc.atlassian.net/browse/CD-11743
	/********************** Edit Company Updation *****************/
	@Test(priority = 5)
	public void EditCompanyUpdationTest() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(UserName, "Password!1");
		userRegistration.EditCompanyUpdation("India", "", "InvoiceCustomerNF", "75865498755",
				"KRISHE SAPPHIRE, 4th and 5th Floor", "Survey No.88, Madhapur", "Hyderabad", "Telangana", "500081");
		userRegistration.Logout();
	}

	// SAPConcur 1709936
	// https://vertexinc.atlassian.net/browse/CD-11744
	/*********** Verify SAP concur url ********************/
	@Test(priority = 6)
	public void SAPConcurTest() throws Exception {
		Open(driver, sapurl);
		features.SAPConcur();
	}

	// userRegistration 77997
	// https://vertexinc.atlassian.net/browse/CD-11745
	/*****************
	 * Verify CPA referral user creation from CPA Ref with no console VAR
	 ************/
	@Test(priority = 7)
	public void userRegistrationTest1() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "CPA Referral no Console", "CPAAutomation",
				"CPARefNoCon" + RND(), UserName1, "United States", "36 Railroad Street", "Linfield", "Pennsylvania",
				"19468", "Premium", "Returns Only", POD);
		userRegistration.AddtionalFeaturesInvPre("RATE FILE,ACCELERATOR ORACLE ERP CLOUD", "", "", "", "", "", "", "",
				"");
		userRegistration.CompleteRegistration("CPARefNoCon" + RND(), "CPARefNoCon" + RND(), "555 555 5552",
				"Password!1", "Password!1");
		userRegistration.UserLogin(UserName1, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");
		userRegistration.VerifyOracleERPCloudInSettings();
		userRegistration.Logout();
	}

	@AfterTest
	public void closeBrowser() throws Exception {
		quitDriver(driver);

	}

}
