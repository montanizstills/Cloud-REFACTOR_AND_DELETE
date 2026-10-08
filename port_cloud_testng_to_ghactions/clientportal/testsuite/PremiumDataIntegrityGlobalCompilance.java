package clientportal.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class PremiumDataIntegrityGlobalCompilance extends CommonFunctions {

	public PremiumDataIntegrityGlobalCompilance() throws Exception {
		super();
	}

	private WebDriver driver;
	String UserName = "ACH" + RND() + "@simulator.amazonses.com";
	String ClientName = "PreROnlyRTRSGL" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-15821
	@Test(priority = 0)
	public void taxabilityimpactreportTest() throws Exception {

		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, ClientName, UserName, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Premium", "Returns Only", POD);
		userRegistration.AddtionalFeatures(
				"REAL - TIME RATE SERVICE,TAXABILITY IMPACT REPORT,DATA INTEGRITY,GLOBAL COMPLIANCE", "US", "", "", "",
				"", "", "", "");
		userRegistration.CompleteRegistration("PreRTSTESTFN" + RND(), "PreRTSTESTLN" + RND(), "556 555 5552",
				"Password!1", "Password!1");
		userRegistration.UserLogin(UserName, "Password!1");
		userRegistration.ACHBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"12345678", "112200439");
		userRegistration.Logout();
		userRegistration.UserLogin(adminusername, adminpassword);
		adminPortal.TaxpayerPartitionsused(VARName, ClientName, "", "", "0/1", "", "", "", "Yes", "2");
		adminPortal.ChangeOraclePartyNumber(ClientName, "", "");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(UserName, "Password!1");
		reports.TaxabilityImpactReport("Report" + RND(), "Canned Software", "", "Canned Software", "Yes", "Yes", "Yes");
		reports.TaxabilityImpactReport("Report" + RND(), "Film Developing", "", "Film Developing", "Yes", "Yes", "Yes");
		reports.TaxabilityImpactReport("Report" + RND(), "Repair of Clothing", "", "Repair of Clothing", "Yes", "Yes",
				"");
		reports.TaxabilityImpactReport("Report" + RND(), "Canned Software", "", "Canned Software", "Yes", "Yes", "");
	}

	// https://vertexinc.atlassian.net/browse/CD-15822
	@Test(priority = 1)
	public void professionaluserlinksTest() throws Exception {
		userRegistration.ProfessionalUserLinks("Dashboard | Vertex Cloud", "Configure | Vertex Cloud",
				"Calculate Taxes | Vertex Cloud", "Distribute Taxes | Vertex Cloud", "Adjustment Files | Vertex Cloud",
				"Lookup Tax Rates | Vertex Cloud", "Manage Tax Returns | Vertex Cloud",
				"Configuration Report | Vertex Cloud", "Filing Calendar Report | Vertex Cloud",
				"Taxability Reports | Vertex Cloud", "Transaction Report | Vertex Cloud",
				"Customer Exemption Report | Vertex Cloud", "Validation Error Reports | Vertex Cloud",
				"Edit My Company | Vertex Cloud", "My Users | Vertex Cloud", "Billing | Vertex Cloud",
				"Billing Reports | Vertex Cloud", "View All Connectors | Vertex Cloud",
				"Document Center | Vertex Cloud", "", "", "", "", "Tax Assist Rule Report | Vertex Cloud",
				"Data Integrity | Vertex Cloud", "Global Compliance | Vertex Cloud");
	}

	// https://vertexinc.atlassian.net/browse/CD-15823
	@Test(priority = 2)
	public void removeanitemexceptionTest() throws Exception {
		userRegistration.UserLogin(UserName, "Password!1");
		configuration.AddTaxPayer("United States", "GS01", "German Shepherd, Inc", "345678912", "306 Main St",
				"Sturbridge", "Massachusetts", "01566", "", "");
		configuration.AddLocations("German Shepherd, Inc", "Virginia", "1928374650", "VALoc001", "", "01/01/2017",
				"7625 Porters Rd", "Esmont", "", "22937", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Virginia",
				"VA Dealer's Sales/Use Tax ST-9", "23456789112", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");
		configuration.AddLocations("German Shepherd, Inc", "Pennsylvania", "14", "PA Kitkat", "", "01/01/2017",
				"1550 Liberty Ridge", "Chesterbrook", "", "19087-5565", "", "");
		configuration.AddJurisdictions("German Shepherd, Inc", "United States", "Pennsylvania",
				"PA Sales/Use/Hotel Tax PA-3", "4567", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.ExemptCustomer("German Shepherd, Inc", "", "Resale", "Exempt", "Code", "E001",
				"Exempt Customer RMV", "01/01/2017", "");
		configuration.ExemptCustomer("German Shepherd, Inc", "", "Resale", "Exempt", "Class", "C02", "Customer2",
				"01/01/2017", "");
		configuration.TaxableCustomer("German Shepherd, Inc", "Code", "T001", "Tax Customer RMV", "01/01/2017",
				"08/13/2022", "Taxable");
		configuration.AddCertificate("German Shepherd, Inc", "T001", "Tax Customer RMV", "01/01/2017", "08/13/2022",
				"Blanket", "", "", "New Mexico", "", "Resale", "456321", "", "", "", "Exemption Certificate", "");
		configuration.AddCertificate("German Shepherd, Inc", "T001", "Tax Customer RMV", "01/01/2017", "08/13/2022",
				"Blanket", "", "", "Texas", "", "Resale", "5745584", "", "", "", "Exemption Certificate", "");
		configuration.TaxableCustomer("German Shepherd, Inc", "Class", "CertificateAL", "CertificateAL", "01/01/2017",
				"08/13/2022", "Taxable");
		configuration.ConfigPush("German Shepherd, Inc");
		configuration.VerifyConfigPush("German Shepherd, Inc");
	}

	// https://vertexinc.atlassian.net/browse/CD-15824
	@Test(priority = 3)
	public void bulkuploadproductsTest() throws Exception {
		configuration.BulkUploadProducts("German Shepherd, Inc", "Yes", "", "ProductsTemplate_invalidEnddateandTaxcode",
				"", "Yes", "", "Yes", "", "", "", "", "");
		configuration.BulkUploadProducts("German Shepherd, Inc", "Yes", "", "ProductsTemplate_InValidTaxcode", "",
				"Yes", "", "Yes", "", "", "", "", "");
		configuration.BulkUploadProducts("German Shepherd, Inc", "Yes", "",
				"ProductsTemplate_startdatelessthancompanydate", "", "Yes", "", "Yes", "", "", "", "", "");
		configuration.BulkUploadProducts("German Shepherd, Inc", "Yes", "", "ProductsTemplate_WithEnddatedonly", "Yes",
				"", "", "", "", "", "", "", "");
		configuration.BulkUploadProducts("German Shepherd, Inc", "Yes", "",
				"ProductsTemplate_Withenddatelessthanstartate", "", "", "", "Yes", "", "", "", "", "");
		configuration.BulkUploadProducts("German Shepherd, Inc", "Yes", "", "ProductsTemplate_WithoutstartDate", "Yes",
				"", "", "", "", "", "", "", "");
		configuration.BulkUploadProducts("German Shepherd, Inc", "Yes", "", "ProductsTemplate_WithVertexandEnddate",
				"Yes", "", "", "", "", "", "", "", "");
		configuration.BulkUploadProducts("German Shepherd, Inc", "Yes", "", "Products_OldTemplate", "Yes", "", "", "",
				"", "", "", "10", "");
		configuration.BulkUploadProducts("German Shepherd, Inc", "Yes", "", "ProductsWithAllType_Mapped", "Yes", "",
				"Bulkupload", "Yes", "", "", "", "", "");
		configuration.ConfigPush("German Shepherd, Inc");
		configuration.VerifyConfigPush("German Shepherd, Inc");
	}

	// https://vertexinc.atlassian.net/browse/CD-15826
	@Test(priority = 4)
	public void customerexceptionreportTest() throws Exception {
		userRegistration.EditUserSettings("", "", "Bill", "Tom", "187449684545454", "Password!1");
		reports.CustomerExceptionReport("Tax Customer RMV", "Yes", "T001", "Texas", "German Shepherd, Inc (GS01)", "TX",
				"");
		reports.CustomerExceptionReport("", "", "", "Alabama", "German Shepherd, Inc (GS01)", "AL", "");
		reports.CustomerExceptionReport("", "", "", "Alberta", "German Shepherd, Inc (GS01)", "AB", "");
		reports.CustomerExceptionReport("", "", "", "Canada", "German Shepherd, Inc (GS01)", "CN", "");
		reports.CustomerExceptionReport("", "", "", "Puerto Rico", "German Shepherd, Inc (GS01)", "PR", "");

	}

	// https://vertexinc.atlassian.net/browse/CD-15827
	@Test(priority = 5)
	public void editcompanyupdationTest() throws Exception {
		userRegistration.EditCompanyUpdation("United States", "", "", "66655558754558", "Test", "", "Chesterbrook",
				"Pennsylvania", "19087-5565");
	}

	// https://vertexinc.atlassian.net/browse/CD-15828
	@Test(priority = 6)
	public void golivetaxpayerTest() throws Exception {
		configuration.GoLive("German Shepherd, Inc");
	}

	// https://vertexinc.atlassian.net/browse/CD-15829
	@Test(priority = 7)
	public void configurecompanyTest() throws Exception {
		configuration.AddTaxPayer("United States", "S01\\", "Snickers, Inc", "345678912", "306 Main St", "Sturbridge",
				"Massachusetts", "01566", "", "");
	}

	// https://vertexinc.atlassian.net/browse/CD-15830
	@Test(priority = 8)
	public void execmptondisenableTest() throws Exception {
		configuration.ConfigPush("Snickers, Inc");
		configuration.VerifyConfigPush("Snickers, Inc");
	}

	// https://vertexinc.atlassian.net/browse/CD-15831
	@Test(priority = 9)
	public void filingcalendarreportTest() throws Exception {
		reports.FilingCalendarReport("", "NM Combined Sales/Use Tax Report CRS-1", "");
		reports.FilingCalendarReport("Snickers, Inc (S01\\)", "", "");
		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-15832
	@Test(priority = 10)
	public void configurationmetricTest() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		adminPortal.VerifyClientsinPremium(ClientName, "TaxCentral", "", "", "", "", "", "Yes", "", "", "");
		adminPortal.ConfigurationMetric();
	}

	// https://vertexinc.atlassian.net/browse/CD-15833
	@Test(priority = 11)
	public void changepasswordTest() throws Exception {
		adminPortal.VertexSMBCustomersVerify(ClientName, "", "", "", "", "", "yes", xGetCustomizedMon(-1, 0), "",
				"PA Sales/Use/Hotel Tax PA-3", "");
		String DNSname = ("addrenvconnect.vertexsmb.com").replace("env", Environment);
		// need to update DNS name
		adminPortal.TransactionMetricData("", "", "", "Professional: Tax Calculations and Returns", DNSname,
				"Address Transaction", "Address Transaction");
		adminPortal.AdminErrorMonitor(ClientName);
		userRegistration.Logout();
		userRegistration.UserLogin(UserName, "Password!1");
		userRegistration.ChangePassword("Password!1",
				"P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd",
				"P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd@123P@ssw0rd");
	}

	// https://vertexinc.atlassian.net/browse/CD-15834
	@Test(priority = 12)
	public void configurationreportTest() throws Exception {
		reports.ConfigurationReport("German Shepherd, Inc (GS01)", "", "", "", "", "", "");
		userRegistration.Logout();
	}

	@AfterTest
	public void closeBrowser() throws Exception {
		quitDriver(driver);

	}

}
