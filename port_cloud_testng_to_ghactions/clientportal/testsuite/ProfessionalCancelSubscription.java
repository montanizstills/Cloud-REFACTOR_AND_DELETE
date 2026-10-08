package clientportal.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class ProfessionalCancelSubscription extends CommonFunctions {

	public ProfessionalCancelSubscription() throws Exception {
		super();
	}

	private WebDriver driver;
	String UserName = "ProTCRSDIGC" + RND() + "@simulator.amazonses.com";
	String ClientName = "ProTCRSDIGCClient" + RND();
	String InitializedEmail = "Initializeduser" + RND() + "@simulator.amazonses.com";
	String InitializedClient = "Initialized" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// CancelSubscription 631764
	// https://vertexinc.atlassian.net/browse/CD-9899
	/***************** Initialized user registration *******************/
	@Test(priority = 0)
	public void InitiatedUserCreationTest() throws Exception {
		userRegistration.Auth0UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, InitializedClient, InitializedEmail,
				"United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Standard", "", POD);
		userRegistration.AddtionalFeatures("Rate File", "", "", "", "", "", "", "", "");
		Open(driver, url);
		manageCustomers.VerifyCancelSubscription(InitializedClient, "", "Yes", "Yes", "Dashboard | Vertex Cloud",
				"Edit My Company | Vertex Cloud", "Billing | Vertex Cloud");
		userRegistration.Logout();
		userRegistration.Auth0UserLogin(adminusername, adminpassword);
		manageCustomers.VerifyNocreatenewuser(InitializedClient);
		userRegistration.Logout();
	}

	// userRegistration 114323;114333;114338
	// https://vertexinc.atlassian.net/browse/CD-9900
	/*****************
	 * Professional user registration with Global Complaince and Data Integrity
	 * features
	 *******/
	@Test(priority = 1)
	public void userRegistrationTest() throws Exception {
		Open(driver, url);
		userRegistration.Auth0UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, ClientName, UserName, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Professional",
				"Tax Calculations and Returns", POD);
		userRegistration.AddtionalFeatures("GLOBAL COMPLIANCE,DATA INTEGRITY", "EU", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("FN" + RND(), "LN" + RND(), "555 555 5552", "Password!1", "Password!1");
		System.out.println("User name is " + UserName);
		userRegistration.Auth0UserLogin(UserName, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");
		userRegistration.Logout();
	}

	// StateRegistration 1403270
	// https://vertexinc.atlassian.net/browse/CD-9901
	/****************** Configurae TaxPayer and Registrations *****************/
	@Test(priority = 2)
	public void VerifyAccountTypeofVertexCloudVarsTest() throws Exception {
		userRegistration.Auth0UserLogin(adminusername, adminpassword);
		adminPortal.TaxpayerPartitionsused(VARName, ClientName, "", "", "0/1", "", "", "", "Yes", "2");
		userRegistration.VerifyAccountTypeofVertexCloudVars("Customer Admin", "Active", "Professional", "Yes",
				"RealTimeRateService", "", "", VARName);
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.Auth0UserLogin(UserName, "Password!1");
		configuration.AddTaxPayer("United States", "GS01", "German Shepherd, Inc", "345678912", "306 Main St",
				"Sturbridge", "Massachusetts", "01566", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Missouri", "MO Sales Tax Return 53-1",
				"12345679", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Ohio", "OH UST-1 Sales", "013456790",
				"Web File", "Monthly", "", "", "", "", "Flat Amount", "200", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Oklahoma",
				"OK Sales Tax Prepayment Return", "STS0123456789", "Web File", "Monthly", "", "", "", "", "Flat Amount",
				"200", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Oklahoma",
				"OK Sales Tax Report STS20002", "STS0123456789", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");
		configuration.AddLocations("German Shepherd, Inc", "Pennsylvania", "14", "PA Kitkat", "", "01/01/2017",
				"1550 Liberty Ridge", "Chesterbrook", "", "19087-5565", "", "12345");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Pennsylvania",
				"PA Prepayment Form for PA-3", "12335678", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30",
				"", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "123356789", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");
		configuration.SiteCodes("German Shepherd, Inc", "00000-035-000", "CARTER", "9705");
		configuration.SiteCodes("German Shepherd, Inc", "00000-083-000", "HENRY", "9756");

	}

	// CopyConfig 1170
	// https://vertexinc.atlassian.net/browse/CD-9902
	/******************** Configure locations and Return forms ************/
	@Test(priority = 3)
	public void ConfigureProductsTest() throws Exception {
		configuration.AddLocations("German Shepherd, Inc", "Delaware", "USALoc001", "University of Delaware",
				"DE123456", "01/01/2017", "257 Academy St", "Newark", "", "19716", "", "12345");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Wisconsin",
				"WI State/County Sales/Use Tax ST-12", "123451234512345", "Web File", "Monthly", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "American Samoa", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "F.S. Of Micronesia", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Guam", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Marshall Islands", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Northern Mariana Islands", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Palau", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "United States Minor Outlying Islands",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Virgin Islands", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "");
		configuration.CopyConfigNoProductsSingleTaxpayer("German Shepherd, Inc");
	}

	// Jurisdiction 1403273;1403245;1403275
	// https://vertexinc.atlassian.net/browse/CD-9903
	/****************** Add and Upload multiple mappings **************/
	@Test(priority = 4)
	public void ExceptionsTest() throws Exception {
		configuration.CreateProduct("German Shepherd, Inc", "Seller", "TPP01", "Product Class", "Gen TPP", "01/01/2017",
				"06/13/2025", "General", "TPP/Goods", "School Supplies", "Calculators", "", "", "", "", "");
		configuration.CreateProduct("German Shepherd, Inc", "Seller", "SV01", "Product Code", "Service", "01/01/2017",
				"06/13/2025", "General", "Services", "Repair of Tangible Personal Property", "Repair of Clothing", "",
				"", "", "", "");
		configuration.CreateProduct("German Shepherd, Inc", "Seller", "CL01", "Product Class", "Clothing", "01/01/2017",
				"06/13/2025", "General", "TPP/Goods", "Clothing", "Scarves", "", "", "", "", "");
		configuration.CreateProduct("German Shepherd, Inc", "Seller", "CS01", "Product Code", "Canned Software",
				"01/01/2017", "06/13/2025", "General", "TPP/Goods", "Computer Software", "Canned Software", "", "", "",
				"", "");
		configuration.CopyConfigNoCustomersSingleTaxpayer("German Shepherd, Inc");
		configuration.BulkUploadProducts("German Shepherd, Inc", "Yes", "", "VertexTaxCodesMaxlength", "", "Yes",
				"VertexTaxCodesMaxlength_err", "Yes", "", "", "", "", "");
		configuration.UploadMappings("German Shepherd, Inc", "ProductsSamplewithProductcodecomma", ".csv");
		configuration.CreateProduct("German Shepherd, Inc", "Seller", "CS,,01", "Product Code", "Canned Software",
				"01/01/2017", "", "General", "TPP/Goods", "Computer Software", "Canned Software", "", "", "", "", "");
		configuration.CreateProduct("German Shepherd, Inc", "Seller", "CS$^%&*(_@!01,", "Product Code",
				"Canned Software", "01/01/2017", "", "General", "TPP/Goods", "Computer Software", "Canned Software", "",
				"", "", "", "");
		configuration.ConfigPush("German Shepherd, Inc");
		configuration.VerifyConfigPush("German Shepherd, Inc");
		configuration.ExemptCustomer("German Shepherd, Inc", "", "Resale", "Exempt", "Code", "E001", "Exempt Customer",
				"01/01/2017", "06/13/2022");
		configuration.TaxableCustomer("German Shepherd, Inc", "Code", "CertificateProdctCode1", "Taxable Customer",
				"01/01/2017", "06/01/2022", "Taxable");
		configuration.AddCertificate("German Shepherd, Inc", "CertificateProdctCode1", "Taxable Customer", "01/01/2017",
				"06/01/2022", "Limited", "CS01", "", "Yes", "Georgia", "Resale", "128", "", "", "",
				"Exemption Certificate", "");
		configuration.TaxableCustomer("German Shepherd, Inc", "Code", "CertificateProdctClass1", "Taxable Customer",
				"01/01/2017", "06/01/2022", "Taxable");
		configuration.TaxableCustomer("German Shepherd, Inc", "Code", "CertificateFelxicode3", "Taxable Customer",
				"01/01/2017", "06/01/2022", "Taxable");
		configuration.AddCertificate("German Shepherd, Inc", "CertificateFelxicode3", "Taxable Customer", "01/01/2017",
				"06/01/2022", "Limited", "TPP01", "", "Yes", "California", "Other", "130", "", "", "",
				"Exemption Certificate", "");
		configuration.TaxableCustomer("German Shepherd, Inc", "Code", "CertificateFelxicode4", "Taxable Customer",
				"01/01/2017", "06/01/2022", "Taxable");
		configuration.TaxableCustomer("German Shepherd, Inc", "Code", "CertificateFelxicode5", "Taxable Customer",
				"01/01/2017", "06/01/2022", "Taxable");
		configuration.AddCertificate("German Shepherd, Inc", "CertificateFelxicode5", "Taxable Customer", "01/01/2017",
				"06/01/2022", "Limited", "SV01", "", "Yes", "New Mexico", "Other", "132", "", "", "",
				"Exemption Certificate", "");
		configuration.TaxableCustomer("German Shepherd, Inc", "Code", "CertificateFelxicode6", "Taxable Customer",
				"01/01/2017", "06/01/2022", "Taxable");
		configuration.TaxableCustomer("German Shepherd, Inc", "Code", "CertificateFelxicode7", "Taxable Customer",
				"01/01/2017", "06/01/2022", "Taxable");
		configuration.ConfigPush("German Shepherd, Inc");
		configuration.VerifyConfigPush("German Shepherd, Inc");
		configuration.CertificateUploadforCustomers("German Shepherd, Inc", "Exempt Customer", "GAdjustmentFileHelp",
				"Exempt");
		configuration.SiteCodesEditAfterOSPush("German Shepherd, Inc", "00000-083-000", "HENRY", "9027");
		configuration.ConfigPush("German Shepherd, Inc");
		configuration.VerifyConfigPush("German Shepherd, Inc");
	}

	// CopyConfig 1159;1168
	// https://vertexinc.atlassian.net/browse/CD-9904
	/******************
	 * Add second taxpayer, configure jurisdictions and copy products
	 *********/
	@Test(priority = 5)
	public void CopyConfigProductsTest() throws Exception {
		configuration.AddTaxPayer("Canada", "1029384001", "CAN", "515316542313243", "60 Carlton St", "Toronto",
				"Ontario", "M5B 1J1", "Nick", "Sakiewicz");
		configuration.AddLocations("CAN", "Canada", "CANLoc001", "University of Toronto", "", "01/01/2017",
				"153 Main Street", "Mount Stewart", "Prince Edward Island", "C0A 1T0", "", "12345");
		configuration.AddJurisdictions("CAN", "United States", "Iowa", "IA Sales/Retailer's Use Tax 32-022",
				"000000123456794", "Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("CAN", "Canada", "Canada", "CN-BRITISH COLUMBIA Provincial Sales Tax",
				"CAN00002", "Web File", "Quarterly", "", "", "12332", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("CAN", "Canada", "Canada", "CN GST/HST Return (Non personalized)", "CAN00003",
				"Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");
		configuration.ConfigPush("CAN");
		configuration.VerifyConfigPush("CAN");
		configuration.CopyConfigProducts("German Shepherd, Inc", "CAN", "");
	}

	// ConfigurationReport 1403276
	// https://vertexinc.atlassian.net/browse/CD-9905
	/*********** Add Customer Exemptions for second taxpayer ********************/
	@Test(priority = 6)
	public void ConfigurationReportTest() throws Exception {
		configuration.TaxableCustomer("CAN", "Code", "CANCC001", "CAN CustEX 001", "01/01/2017", "06/13/2022",
				"Taxable");
		configuration.AddCertificate("CAN", "CANCC001", "CAN CustEX 001", "01/01/2017", "06/13/2022", "Blanket", "", "",
				"Yes", "Canada", "Resale", "CANEx001", "", "", "", "Exemption Certificate", "");
		configuration.TaxableCustomer("CAN", "Class", "CANCC002", "CAN CustEX 002", "01/01/2017", "06/13/2022",
				"Taxable");
		configuration.AddCertificate("CAN", "CANCC002", "CAN CustEX 002", "01/01/2017", "06/13/2022", "Blanket", "", "",
				"Yes", "British Columbia", "Resale", "CANEx002", "", "", "", "Exemption Certificate", "");
		configuration.TaxableCustomer("CAN", "Class", "USACC001", "USA CustEX 001", "01/01/2017", "06/13/2022",
				"Taxable");
		configuration.AddCertificate("CAN", "USACC001", "USA CustEX 001", "01/01/2017", "06/13/2022", "Blanket", "", "",
				"Yes", "Georgia", "Resale", "USAEX 001", "", "Yes", "GAdjustmentFileHelp", "Exemption Certificate", "");
		configuration.ConfigPush("CAN");
		configuration.VerifyConfigPush("CAN");
	}

	// ConfigurationReport 1403386
	// https://vertexinc.atlassian.net/browse/CD-9906
	/********************* Golive taxpayer ***************************/
	@Test(priority = 7)
	public void GoLiveByTaxPayerTest() throws Exception {
		configuration.GoLive("German Shepherd, Inc");
		reports.ConfigurationReportWithoutProd("German Shepherd, Inc (GS01)", "German Shepherd, Inc", "GS01",
				"34-5678912", "01/01/2017", "Yes", "");
		reports.ConfigurationReportWithoutProd("CAN (1029384001)", "CAN", "1029384001", "515316542 31 3243",
				"01/01/2017", "Yes", "");
		configuration.AddLocations("German Shepherd, Inc", "Canada", "CAN00001", "Canada Location", "", "01/01/2017",
				"153 Main Street", "Mount Stewart", "Prince Edward Island", "C0A 1T0", "", "12345");
		configuration.AddJurisdictions("German Shepherd, Inc", "Canada", "Canada",
				"CN-SASKATCHEWAN Provincial Sales Tax", "CNSA101234", "Web File", "Quarterly", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddLocations("German Shepherd, Inc", "Canada", "CA01", "Vancover", "", "01/01/2017",
				"800 Griffiths Way!", "Vancouver", "British Columbia", "V6B 6G1", "", "12345");
		configuration.AddJurisdictions("German Shepherd, Inc", "Canada", "Canada", "CN-MANITOBA Vendor's Return",
				"123456789", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.ConfigPush("German Shepherd, Inc");
		configuration.VerifyConfigPush("German Shepherd, Inc");
		configuration.CreateProductthroughQuicksearch("CAN", "Seller", "TESTPDR001", "Product Code",
				"Swim Suit Cover-ups", "01/01/2017", "", "Swim Suit Cover-ups");
		configuration.CreateProductthroughQuicksearch("CAN", "Buyer", "TESTPDR005", "Usage Code", "Bathing Suits",
				"01/01/2017", "", "Bathing Suits");
		configuration.CreateProductthroughQuicksearch("CAN", "Seller", "TESTPDR002", "Product Class", "Costumes",
				"01/01/2017", "", "Costumes");
		configuration.CreateProductthroughQuicksearch("CAN", "Buyer", "TESTPDR003", "Usage Class",
				"Carbonated Soft Drinks", "01/01/2017", "", "Carbonated Soft Drinks");
		configuration.ConfigPush("CAN");
		configuration.VerifyConfigPush("CAN");
		configuration.EditFirstProductandRemoveSecondProduct("Yes", "German Shepherd, Inc", "Yes", "", "");
		configuration.FlexFields("Code", "FlexCode1", "", "");
		userRegistration.Logout();
	}

	// CalculateTaxesProUser 373618;373619
	// https://vertexinc.atlassian.net/browse/CD-9907
	/************
	 * Post transactions through calculate taxes and upload adjustment files
	 **********/
	@Test(priority = 8)
	public void PostingTransactionsTest() throws Exception {
		Open(driver, url);
		userRegistration.Auth0UserLogin(UserName, "Password!1");
		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "Canned Software", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "",
				"");
		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "Gen TPP", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "Service", "1000", "1", "$0.00", "$1,000.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "Clothing", "1000", "1", "$0.00", "$1,000.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "PROD1", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "PROD2", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "PROD3", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "PROD4", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "PROD5", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "PROD6", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "PROD7", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "PROD8", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "", "");

		transactions.CalculateTaxes("German Shepherd, Inc (GS01)", "Live", "120112014", "", "", "United States",
				"Pennsylvania", "19101", "PROD9", "1000", "1", "$80.00", "$1,080.00", "", "Seller", "", "", "");

		transactions.UploadAdjustmentFile("Accrual_AdjustmentFile-OseriesError", "Accrual", "Buyer", "");
		transactions.UploadAdjustmentFile("Accrual_AdjustmentFile-StateMissing", "Accrual", "Buyer", "");
		transactions.UploadAdjustmentFile("Accrual_AdjustmentFile-TransBeforeCompanyCreateDate", "Accrual", "Buyer",
				"");
		transactions.UploadAdjustmentFile("AssetMovement_AdjustmentFile_NoTaxAmount", "Asset/Goods Movement", "Buyer",
				"");
		transactions.UploadAdjustmentFile("DistTaxPro_AdjustmentFile_ZipMissing", "Distribute Tax", "Buyer", "");
		transactions.UploadAdjustmentFile("InventoryRemoval_AdjustmentFile_InvalidCompCode", "Inventory Removal",
				"Buyer", "");
		transactions.UploadAdjustmentFile("PurchaseOrder_AdjustmentFile_DateError", "Purchase Order", "Buyer", "");
		transactions.UploadAdjustmentFile("Cut_Accural_Territories_AdjustmentFile", "Accrual", "Buyer", "");
		transactions.UploadAdjustmentFile("Cut_Asset_Territories_AdjustmentFile", "Asset/Goods Movement", "Buyer", "");
		transactions.UploadAdjustmentFile("Cut_DistributeTax_Territories_AdjustmentFile", "Distribute Tax", "Buyer",
				"");
		transactions.UploadAdjustmentFile("Cut_Invenotry_Territories_AdjustmentFile", "Inventory Removal", "Buyer", "");
		transactions.UploadAdjustmentFile("Cut_Invoice_Territories_AdjustmentFile", "Invoice Verification", "Buyer",
				"");
		transactions.UploadAdjustmentFile("Cut_PurchaseOrderr_Territories_AdjustmentFile", "Purchase Order", "Buyer",
				"");
		transactions.UploadAdjustmentFile("Sales_Invoice_Territories_AdjustmentFile", "Invoice", "Seller", "");
		transactions.UploadAdjustmentFile("Sales_DistributeTax_Territories_AdjustmentFile", "Distribute Tax", "Seller",
				"");
		transactions.UploadAdjustmentFile("Sales_Quote_Territories_AdjustmentFile", "Quotation", "Seller", "");
		userRegistration.Logout();
		transactions.syncTime();
	}

	// ConfigurationReport 506033
	// https://vertexinc.atlassian.net/browse/CD-9908
	/******************
	 * Verify Admin Configuration Report
	 ***************************/
	@Test(priority = 9)
	public void AdminConfigurationReportTest() throws Exception {
		userRegistration.Auth0UserLogin(adminusername, adminpassword);
		adminPortal.AdminErrorMonitor(ClientName);
		reports.AdminConfigurationReport(ClientName, "", "German Shepherd, Inc (GS01)", "", "Yes", "");
		userRegistration.Logout();
	}

	// TransactionReport 506141
	// https://vertexinc.atlassian.net/browse/CD-9910
	/************ Generate Transation reports and Verify **********************/
	@Test(priority = 10)
	public void TransactionReportTest() throws Exception {
		Open(driver, url);
		userRegistration.Auth0UserLogin(UserName, "Password!1");
		reports.TransactionReport("German Shepherd, Inc (GS01)", xGetCustomizedDate(-29, -1, 0),
				xGetCurrentDate("MM/dd/yyyy"), "All", "TransactionDetails", "Success!");
		reports.TransactionReport("German Shepherd, Inc (GS01)", xGetCustomizedDate(-29, -1, 0),
				xGetCurrentDate("MM/dd/yyyy"), "All", "", "Success!");
		reports.TransactionReport("German Shepherd, Inc (GS01)", xGetCustomizedDate(-29, -1, 0),
				xGetCurrentDate("MM/dd/yyyy"), "Pennsylvania", "TransactionDetailsAbbreviated", "Success!");
		reports.TransactionReport("German Shepherd, Inc (GS01)", xGetCustomizedDate(-29, -1, 0),
				xGetCurrentDate("MM/dd/yyyy"), "All", "Jurisdiction Summary", "Success!");
		reports.TransactionReport("German Shepherd, Inc (GS01)", xGetCustomizedDate(-29, -1, 0),
				xGetCurrentDate("MM/dd/yyyy"), "Pennsylvania", "Document Summary By Jurisdiction", "Success!");
		reports.VerifyTransactionReport("German Shepherd, Inc (GS01)", xGetCustomizedDate(-29, -1, 0),
				xGetCurrentDate("MM/dd/yyyy"), "Transaction Details Report", "Yes", "", "", "", "");
		reports.VerifyTransactionReport("German Shepherd, Inc (GS01)", xGetCustomizedDate(-29, -1, 0),
				xGetCurrentDate("MM/dd/yyyy"), "Transaction Summary Report", "Yes", "", "", "", "");
		reports.VerifyTransactionReport("German Shepherd, Inc (GS01)", xGetCustomizedDate(-29, -1, 0),
				xGetCurrentDate("MM/dd/yyyy"), "Transaction Details Abbreviated Report", "Yes", "", "Yes", "", "");
		reports.VerifyTransactionReport("German Shepherd, Inc (GS01)", xGetCustomizedDate(-29, -1, 0),
				xGetCurrentDate("MM/dd/yyyy"), "Jurisdiction Summary", "Yes", "", "", "", "");
		reports.VerifyTransactionReport("German Shepherd, Inc (GS01)", xGetCustomizedDate(-29, -1, 0),
				xGetCurrentDate("MM/dd/yyyy"), "Document Summary By Jurisdiction", "Yes", "", "", "", "");
		userRegistration.Logout();
	}

	// PartitionConfiguration
	// 326869;326870;326878;326879;326882;326883;326884;326899;326910;326925;326945;114283;114284
	// https://vertexinc.atlassian.net/browse/CD-9911
	/************** Verify Admin Banner changes *********************/
	@Test(priority = 11)
	public void AdminCloudClientBannerChangesTest() throws Exception {
		userRegistration.Auth0UserLogin(adminusername, adminpassword);
		adminPortal.AdminCloudClientBannerChanges(ClientName, "MTPP", "Yes", "", "", "", "Yes", "600", "test", "Yes",
				"", "");
		adminPortal.AdminCloudClientBannerChanges(ClientName, "", "NO", "", "Credit Card", "", "", "", "", "", "", "");
	}

	// CustomersDelete 77865
	// https://vertexinc.atlassian.net/browse/CD-9912
	/****************
	 * Delete client from invite users and cancel subscription from DB
	 ***************/
	@Test(priority = 12)
	public void UserDeleteTest() throws Exception {
		adminPortal.InviteUsersDelete(ClientName, "");
		userRegistration.Logout();
		// database.VerifyDIStatus("");
	}

	@AfterTest
	public void closeBrowser() throws Exception {
		quitDriver(driver);

	}

}
