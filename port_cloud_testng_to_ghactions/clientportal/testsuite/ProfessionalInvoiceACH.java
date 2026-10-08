package clientportal.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class ProfessionalInvoiceACH extends CommonFunctions {

	public ProfessionalInvoiceACH() throws Exception {
		super();
	}

	private WebDriver driver;
	String UserName = "ProTCRSRFRTEInvoice" + RND() + "@simulator.amazonses.com";
	String ClientName = "ProTCRSRFRTEInvoiceClient" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// ConfigureProducts-#566670
	// https://vertexinc.atlassian.net/browse/CD-11727
	/********************** User Registration, Configuration **********************/
	@Test(priority = 0, enabled = true)
	public void userRegistration_Tests() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, ClientName, UserName, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Professional",
				"Tax Calculations and Returns", POD);
		userRegistration.AddtionalFeatures("RATE FILE,RETAIL TAX EXTRACT,INVOICE", "", "ACH Credit", "Yes", "", "", "",
				"", "19468");
		userRegistration.CompleteRegistration("ProRTSTESTFN", "ProRTSTESTLN", "555 555 5552", "Password!1",
				"Password!1");
		System.out.println("Username is " + UserName);
		userRegistration.UserLogin(UserName, "Password!1");
		configuration.AddTaxPayer("United States", "HI01", "KitKat, Inc", "123456789", "36 Railroad Street", "Linfield",
				"Pennsylvania", "19468", "", "");
		configuration.AddLocations("KitKat, Inc", "Alabama", "4", "AL Tandy", "", "01/01/2017", "2810 35th St",
				"Tuscaloosa", "", " 35401-6429", "", "12345");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Alabama",
				"AL State Sales Tax Return w/Est Pay 2100", "SU1234567890", "Web File", "Monthly", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Texas", "12", "TX manufact", "", "01/01/2017", "4799 Airport Pkwy",
				"Addison", "", "75001-3321", "", "12345");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Texas", "TX Sales/Use Tax Short Form 01-117",
				"12345678911", "Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "California", "15", "CA", "", "01/01/2017", "1750 Lundy Ave",
				"San Jose", "", "95101", "", "12345");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "California",
				"CA St/Loc/Dist Sales & Use CDTFA-401-A", "ABC123765", "Web File", "Quarterly", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "California", "CA Sales/Use Prepayment Form",
				"1234567", "Web File", "First Two Months of Quarter", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Florida", "FL1", "FL1", "", "01/01/2017",
				"5050 West Tennessee Street", "Tallahassee", "", "32399", "", "12345");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Florida",
				"FL Sales/Use Tax DR-15 (Per Location)", "123", "Web File", "Monthly", "", "", "", "", "Flat Amount",
				"30", "", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Florida", "FL Sales/Use Tax Return DR-15",
				"123456789123415", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Georgia", "GA1", "GA1", "", "01/01/2017", "943 Aycock Mill Rd",
				"Shellman", "", "39886", "", "12345");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"12312123451", "Web File", "Monthly", "", "", "", "", "Flat Amount", "500", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Arizona", "Azloc", "AZ Cake", "", "01/01/2017", "1600 West Monroe",
				"Phoenix", "", "85007", "", "12345");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Arizona",
				"AZ Annual Est. Payment Trans. Priv. Tax", "56789", "Web File", "Annually", "", "", "", "",
				"Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Arizona", "AZ-YAVAPAI PRESCOTT Indian Tribe",
				"9876", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Colorado", "Coloc1", "ColLoc", "", "01/01/2017",
				"825 E. Fillmore Street", "Colorado Springs", "", "80907", "", "12345");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Colorado", "CO Retail Sales Tax Return DR0100",
				"371456789012345", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Colorado", "CO-WINDSOR Sales Tax Return",
				"1234567", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Pennsylvania", "14", "PA Chocolate", "", "01/01/2017",
				"1550 Liberty Ridge", "Chesterbrook", "", "19087-5565", "", "12345");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Pennsylvania", "PA Prepayment Form for PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"123456", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Illinois", "Illoc1", "Illoc", "", "01/01/2017",
				"100 N University St", "Normal", "", "61761", "", "12345");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Illinois", "IL Sales/Use Tax Prepayment Return",
				"123456789123435", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Illinois", "IL Sales/Use Tax Return/E911 ST-1",
				"123456789123435", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Arkansas", "AR Excise Sales/Use Tax Report",
				"123456789", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Arkansas", "AR Sales Tax - Direct Pay Filers",
				"123456987", "Web File", "Monthly", "", "", "", "", "Flat Amount", "30", "", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Arkansas", "AR Use Tax - Direct Pay Filers",
				"123654", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("KitKat, Inc", "United States", "Wisconsin",
				"WI State/County Sales/Use Tax ST-12", "123451234512345", "Web File", "Monthly", "", "", "", "", "", "",
				"", "", "", "");
		configuration.AddLocations("KitKat, Inc", "Canada", "CA01", "Canada!#", "", "01/01/2017", "800 Griffiths Way!",
				"Vancouver!@#", "British Columbia", "V6B 6G1", "", "12345");
		configuration.AddJurisdictions("KitKat, Inc", "Canada", "Canada", "CN-BRITISH COLUMBIA Provincial Sales Tax",
				"123456789", "Web File", "Monthly", "", "", "CNQB00001", "", "", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Atv01", "Product Code", "All Terrain Vehicles",
				"01/01/2017", "01/01/2025", "General", "TPP/Goods", "Motor Vehicles and Transportation Equipment",
				"Automobiles", "All Terrain Vehicles", "", "", "", "Yes");
		configuration.CreateProduct("KitKat, Inc", "Buyer", "Bb01", "Usage Class", "Books and Booklets", "01/01/2017",
				xGetCurrentDate("MM/dd/yyyy"), "General", "TPP/Goods", "Printed Matter", "Books and Booklets", "", "",
				"", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Cbs01", "Product Code", "Comic Books- Serial",
				"01/01/2017", xGetCurrentDate("MM/dd/yyyy"), "General", "TPP/Goods", "Printed Matter",
				"Comic Books- Serial", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Buyer", "Sl01", "Usage Code", "Shipping Labels", "01/01/2017",
				xGetCurrentDate("MM/dd/yyyy"), "General", "TPP/Goods", "Packaging", "Retail Packaging",
				"Shipping Labels", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Hc01", "Product Class", "Handling Charge", "01/01/2017",
				"01/01/2025", "General", "TPP/Goods", "Delivery Charges", "Handling Charge", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Dp01", "Product Code", "Direct Production", "01/01/2017",
				"01/01/2025", "Manufacturing", "Direct Production", "", "", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Icq01", "Product Code", "Ice Cream Equal to Pint",
				"01/01/2017", "01/01/2025", "General", "TPP/Goods", "Food & Beverages", "For Immediate Consumption",
				"Cold Prepared Foods for Off-Premises", "Single Size Ice Cream and Frozen Novelties",
				"Ice Cream Equal to Pint", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Pc01", "Product Code", "Pollution Control", "01/01/2017",
				"01/01/2025", "Manufacturing", "Pollution Control", "", "", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Ncsd01", "Product Code", "Non-Carbonated Soft Drinks",
				"01/01/2017", "01/01/2025", "General", "TPP/Goods", "Food & Beverages",
				"For Home or Non-Immediate Consumption", "Non-Carbonated Soft Drinks", "", "", "", "Yes");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Ssic01", "Product Code",
				"Single Size Ice Cream and Frozen Novelties", "01/01/2017", "01/01/2025", "General", "TPP/Goods",
				"Food & Beverages", "For Immediate Consumption", "Cold Prepared Foods for Off-Premises",
				"Single Size Ice Cream and Frozen Novelties", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Pw01", "Product Code", "Press Wood", "01/01/2017",
				"01/01/2025", "General", "TPP/Goods", "Home Fuel", "Press Wood", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Ldt01", "Product Code", "Labels, Decals, and Tags",
				"01/01/2017", "01/01/2025", "Manufacturing", "Packaging Materials", "Labels, Decals, and Tags", "", "",
				"", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Unhf01", "Product Code",
				"Unheated Food Sold by Weight or Volume", "01/01/2017", "01/01/2025", "General", "TPP/Goods",
				"Food & Beverages", "For Immediate Consumption", "Cold Prepared Foods for Off-Premises",
				"Unheated Food Sold by Weight or Volume", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Gw01", "Product Code", "Gift Wrapping", "01/01/2017",
				"01/01/2025", "General", "Services", "Retail Services", "Gift Wrapping", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Cjs01", "Product Code", "Cleaning/Janitorial Services",
				"01/01/2017", "01/01/2025", "General", "Services", "Facility Maintenance",
				"Cleaning/Janitorial Services", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Seller", "Fd01", "Taxpayer", "Film Developing", "01/01/2017",
				"01/01/2025", "General", "Services", "Retail Services", "Film Developing", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Buyer", "AMS01", "Taxpayer", "Alarm Monitoring Services",
				"01/01/2017", "01/01/2025", "General", "Services", "Facility Maintenance", "Alarm Monitoring Services",
				"", "", "", "", "");
		userRegistration.Logout();
	}

	// QualifyingConditions-#566343;566347
	// https://vertexinc.atlassian.net/browse/CD-11728
	/******************** Add Qualifying Conditions ******************/
	@Test(priority = 1, enabled = true)
	public void QualifyingConditions_Test() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(UserName, "Password!1");
		configuration.ConfigPush("KitKat, Inc");
		configuration.VerifyConfigPush("KitKat, Inc");
		configuration.QualifyingConditions("KitKat, Inc", "Arizona", "State", "", "Shipping Labels", "");
		configuration.QualifyingConditions("KitKat, Inc", "Arizona", "City", "Bisbee", "Direct Production", "");
		configuration.QualifyingConditions("KitKat, Inc", "Arkansas", "County", "Clark", "Non-Carbonated Soft Drinks",
				"");
		configuration.QualifyingConditions("KitKat, Inc", "Georgia", "County", "Banks",
				"Single Size Ice Cream and Frozen Novelties", "");
		configuration.QualifyingConditions("KitKat, Inc", "Arkansas", "State", "",
				"Unheated Food Sold by Weight or Volume", "");
		configuration.ConfigPush("KitKat, Inc");
		configuration.VerifyConfigPush("KitKat, Inc");
	}

	// ConfigureProducts-#978358;978360
	// https://vertexinc.atlassian.net/browse/CD-11729
	/*************** Configure Mappings *********************/
	@Test(priority = 2, enabled = true)
	public void ConfigureProducts_FlexCode8n11_Test() throws Exception {
		configuration.CreateProduct("KitKat, Inc", "Seller", "F8S", "FlexCode08", "FlexCode8Sell", "01/01/2017",
				"01/01/2025", "General", "TPP/Goods", "Clothing", "Scarves", "", "", "", "", "");
		configuration.CreateProduct("KitKat, Inc", "Buyer", "FlexCode11Buy", "FlexCode11", "FlexCode8Sell",
				"01/01/2017", "01/01/2025", "General", "TPP/Goods", "Digital Goods",
				"Greeting Card Delivered Electronically", "", "", "", "", "");
	}

	// ConfigureProducts-#978382
	// https://vertexinc.atlassian.net/browse/CD-11730
	/********************* Upload Mappings **************************/
	@Test(priority = 3, enabled = true)
	public void ConfigureProducts_FlexType_Test() throws Exception {
		configuration.UploadMappings("KitKat, Inc", "ProductsUISupportAll25FlexFields", ".csv");
		configuration.ConfigPush("KitKat, Inc");
		configuration.VerifyConfigPush("KitKat, Inc");
	}

	// Exceptions-#566568
	// https://vertexinc.atlassian.net/browse/CD-11731
	/******************** Configure Customer Exemptions *************************/
	@Test(priority = 4, enabled = true)
	public void Exceptions_Test() throws Exception {
		configuration.ExemptCustomer("KitKat, Inc", "", "Resale", "Exempt", "Code", "E001", "Exempt Customer",
				"01/01/2017", "06/01/2025");
		configuration.TaxableCustomer("KitKat, Inc", "Code", "T001", "Taxable Customer", "01/01/2017", "06/01/2025",
				"Taxable");
		configuration.AddCertificate("KitKat, Inc", "T001", "Taxable Customer", "01/01/2017", "06/01/2025", "Blanket",
				"", "", "Yes", "New Mexico", "Resale", "12345678", "", "", "", "Exemption Certificate", "");
		configuration.AddCertificate("KitKat, Inc", "T001", "Taxable Customer", "01/01/2017", "06/01/2025", "Blanket",
				"", "", "Yes", "Texas", "Resale", "123456555", "", "", "", "Exemption Certificate", "");
		configuration.AddCertificate("KitKat, Inc", "T001", "Taxable Customer", "01/01/2017", "06/01/2025", "Blanket",
				"", "", "Yes", "New Jersey", "Resale", "1234567899", "", "", "", "Exemption Certificate", "");
		configuration.AddCertificate("KitKat, Inc", "T001", "Taxable Customer", "01/01/2017", "06/01/2025", "Blanket",
				"", "", "Yes", "Pennsylvania", "Resale", "1234567890", "", "", "", "Exemption Certificate", "");
		configuration.TaxableCustomer("KitKat, Inc", "Code", "T002", "LimitedQC", "01/01/2017", "06/01/2025",
				"Taxable");
		configuration.AddCertificate("KitKat, Inc", "T002", "LimitedQC", "01/01/2017", "06/01/2025", "Limited",
				"Unhf01", "", "Yes", "Pennsylvania", "Resale", "456321", "", "", "", "Exemption Certificate", "");
	}

	// CalculateTaxesProUser-#566570
	// https://vertexinc.atlassian.net/browse/CD-11732
	/*********************** Upload Address Cleansing Files ******************/
	@Test(priority = 5, enabled = true)
	public void CalculateTaxes_Test() throws Exception {
		configuration.ConfigPush("KitKat, Inc");
		configuration.VerifyConfigPush("KitKat, Inc");
		rte.createRTE("", "", "TestRteStart2", "Test start with sendsales", "Start", xGetCustomizedDate(-29, 0, 0),
				xGetCustomizedDate(-29, 0, 0), "", "", "", "", "KitKat, Inc (HI01)", "PA Chocolate,Canada!#,AL Tandy",
				"Yes", "Hc01", "", "Pennsylvania", "", "Yes", "", "Codes", "", "", "", "", "", "", "", "", "", "", "",
				"Yes", "", "", "", "", "", "", "", "");
		reports.ConfigurationReport("KitKat, Inc (HI01)", "", "", "", "", "", "");
		transactions.AddressCleansing("AddressCleansingXLSFile", ".xls", "Yes", "", "", "");
		transactions.AddressCleansing("AddressCleansingXLXSFile", ".xlsx", "Yes", "", "", "");
		transactions.AddressCleansing("AddressClansingPDFFile", ".pdf", "Yes", "", "", "");
		transactions.AddressCleansing("AddressCleansingRARFile", ".rar", "Yes", "", "", "");
		transactions.AddressCleansing("AddressCleansingValidFile", ".csv", "", "", "Yes", "");
		transactions.AddressCleansing("AddressCleansingCSVFile_InvalidCommentsColumn", ".csv", "Yes", "", "", "");
		transactions.AddressCleansing("AddressCleansingCSVFile_InvalidRecordIdColumn", ".csv", "Yes", "", "", "");
		transactions.AddressCleansing("AddressCleansingCSVFile_InvalidAddresLine1Column", ".csv", "Yes", "", "", "");
		transactions.AddressCleansing("AddressCleansingCSVFile_InvalidAddressLine2Column", ".csv", "Yes", "", "", "");
		transactions.AddressCleansing("AddressCleansingCSVFile_InvalidCityColumn", ".csv", "Yes", "", "", "");
		transactions.AddressCleansing("AddressCleansingCSVFile_InvalidStateColumn", ".csv", "Yes", "", "", "");
		transactions.AddressCleansing("AddressCleansingCSVFile_InvalidZip", ".csv", "Yes", "", "", "");
		transactions.AddressCleansing("AddressCleansingCSVFile_InvalidCommentsandAddressLine1Columns", ".csv", "Yes",
				"", "", "");
		transactions.AddressCleansing("AddressCleansingCSVFile_ValidandInvalidData", ".csv", "", "", "Yes", "Yes");
		transactions.AddressCleansing("AddressCleansingInValidFile", ".csv", "", "", "", "Yes");
		transactions.AddressCleansing("AddressCleansingCanadaAddress", ".csv", "", "", "", "Yes");
		transactions.AddressCleansing("AddressCleansingSpecialCharacters", ".csv", "", "", "", "Yes");
		transactions.AddressCleansing("AddressCleansingHeaderColumnsChanged", ".csv", "", "", "Yes", "");
		transactions.AddressCleansing("Address_Cleansing_firsteachworduppercase", ".csv", "", "", "Yes", "");
		transactions.AddressCleansing("Address_Cleansing_firststletterUppercase", ".csv", "", "", "Yes", "");
		transactions.AddressCleansing("Address_Cleansing_firstWordAllLowersecondwordAllUpper", ".csv", "", "", "Yes",
				"");
		transactions.AddressCleansing("Address_Cleansing_firstWordAllUppersecondwordAllLower", ".csv", "", "", "Yes",
				"");
		transactions.AddressCleansing("Address_Cleansing_firstWordlowercaseNextworduppercase", ".csv", "", "", "Yes",
				"");
		transactions.AddressCleansing("Address_Cleansing_AllColumnsinLowercase", ".csv", "", "", "Yes", "");
		transactions.AddressCleansing("Address_Cleansing_AllColumnsinUppercase", ".csv", "", "", "Yes", "");
		transactions.AddressCleansing("Address_Cleansing_MixofUpperLower", ".csv", "", "", "Yes", "");
		transactions.AddressCleansing("Address_Cleansing_WithAllCombinations", ".csv", "", "", "Yes", "");
		reports.TaxabilityReports("", "KitKat, Inc (HI01)", xGetCurrentDate("MM/yyyy"),
				"Overview_Kitkat;Sales Taxability_Kitkat;Usage Taxability_Kitkat;Jurisdiction Codes_Kitkat",
				"Vertex, Inc", "C = Indicator for a selected qualifying condition", "Sales Taxability", "AR", "General",
				"Unheated Food Sold by Weight or Volume", "C");
		transactions.CalculateTaxes("KitKat, Inc (HI01)", "Test", "120112014", "", "", "United States", "Georgia",
				"30223", "Single Size Ice Cream and Frozen Novelties", "1000", "1", "$0.00", "$1,000.00", "", "Seller",
				"", "", "");
		transactions.CalculateTaxes("KitKat, Inc (HI01)", "Test", "120112014", "LimitedQC", "", "United States",
				"Pennsylvania", "19101", "Unheated Food Sold by Weight or Volume", "1000", "1", "$0.00", "$1,000.00",
				"", "Seller", "", "", "");
		transactions.VerifyAddressCleansingNotifications("Address Cleansing used by", Environment, ClientName);
	}

	// UploadAdjustmentFile-#566706;566704
	// https://vertexinc.atlassian.net/browse/CD-11733
	@Test(priority = 6, enabled = true)
	public void UploadAdjustmentFiles_Test() throws Exception {
		transactions.CalculateTaxes("KitKat, Inc (HI01)", "Test", "120112017", "", "", "United States", "Pennsylvania",
				"19101", "FlexCode11Sell", "1000", "1", "$0.00", "$1,000.00", "", "Seller", "", "", "");
		transactions.UploadAdjustmentFile("CUT_Accural_Flexcode", "Accrual", "Buyer", "");
		transactions.UploadAdjustmentFile("Sales_DistributedTax_Flexcode", "Distribute Tax", "Seller", "");
	}

	// EditFirstProductandRemoveSecondProduct-#978370;978368
	// https://vertexinc.atlassian.net/browse/CD-11734
	@Test(priority = 7, enabled = true)
	public void EditandRemove_Products_Test() throws Exception {
		userRegistration.UserLogin(UserName, "Password!1");
		manageTaxreturns.Returnsgenerate("KitKat, Inc (HI01)", xGetCustomizedMon(-2, 0));
		configuration.JuridictionONOFF("KitKat, Inc", "", "Pennsylvania");
		configuration.EditFirstProductandRemoveSecondProduct("Yes", "KitKat, Inc", "Yes", "", "");
		configuration.GoLive("KitKat, Inc");
		userRegistration.Logout();
	}

	// AdminNegotiatedContract-#78223
	// https://vertexinc.atlassian.net/browse/CD-11735
	@Test(priority = 8, enabled = true)
	public void AdminNegotiatedContract_Test() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		reports.AdminTaxabilityReport(ClientName, "TaxabilityReport", xGetCurrentDate("yyyy"), xGetCurrentDate("M"),
				"AdminProductTaxabilityReport");
		transactions.AddressCleansingPresenceAdminNotification();
	}

	// AdminConfigurationReport-#78222;566847
	// https://vertexinc.atlassian.net/browse/CD-11736
	@Test(priority = 9, enabled = true)
	public void AdminConfigurationReport_Test() throws Exception {
		reports.AdminConfigurationReport(ClientName, "", "KitKat, Inc (HI01)", "", "", "");
		adminPortal.ClientpodReport(ClientName, "CustomerName", "OSeriesPod", "KitKat, Inc", "Authorized");
		userRegistration.Logout();
	}

	// RTE-#114417;114532;114534;114421;227863;227865;227869;227871
	// https://vertexinc.atlassian.net/browse/CD-11737
	@Test(priority = 10, enabled = true)
	public void RTE_Test() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(UserName, "Password!1");
		// manageTaxreturns.downloadReturns("KitKat, Inc (HI01)", xGetCustomizedMon(-1,
		// 0), "", "");
		rte.downloadRTE("TestRteStart2");
		unZipRTE(RTEFilepath, RTEPath + "_A");
		compareCSVRTE("D:\\Projects\\Develop\\vc-testing-automation\\resources\\testdata\\expected\\RTE\\ExpectedFiles",
				RTEPath + "_A", RTEPath + "_Results");
	}

	// RTE-#114459;114479;227931;373666
	// https://vertexinc.atlassian.net/browse/CD-15837
	@Test(priority = 11, enabled = true)
	public void RTE_GenerateBrackest_Test() throws Exception {
		rte.createRTE("", "", "TestRteStartGB", "Test start with Generate Brackets", "Start",
				xGetCustomizedDate(-29, 0, 0), xGetCustomizedDate(-29, 0, 0), "", "", "", "", "KitKat, Inc (HI01)",
				"PA Chocolate,Canada!#", "Yes", "Hc01", "", "", "", "Yes", "911 Fee", "Codes", "", "", "", "", "", "",
				"", "", "", "", "", "Yes", "Yes", "", "", "", "", "", "", "");
	}

	// ManageBracket-#175311;175326;175330;175344;175362;175384;227582;227583;227585;227587;227594;227595
	// https://vertexinc.atlassian.net/browse/CD-15838
	@Test(priority = 12, enabled = true)
	public void ManageBracket_Test() throws Exception {
		rte.ManageBracket("", "Alabama", "", "", "2", "50", "General Sales and Use Tax");

	}

	@AfterTest
	public void closeBrowser() throws Exception {
		quitDriver(driver);

	}

}
