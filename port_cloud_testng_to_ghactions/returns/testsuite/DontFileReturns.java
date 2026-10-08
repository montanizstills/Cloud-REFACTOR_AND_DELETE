/*###################################################################################################
Name: DontFileReturns.java 
Description: Returns that are marked Do Not File should not display on the Premium reconciliation report
	// Generated on the 10th of the month and notification emails sent when a customer uploads a notice to the Document Center be sent to vtmsdev gmail
// Ticket CD-5373 and CD-4132
Author: Venkat Reddy Kandula
Date Created:03/02/2020
Date Modified:04/07/2021
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class DontFileReturns extends CommonFunctions {

	public DontFileReturns() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "DontFile" + RND() + "@simulator.amazonses.com";
	String clientname = "DontFileTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("QA") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// Upload notification with adding comment with 255 characters including special
	// characters
	//https://vertexinc.atlassian.net/browse/CD-18881
	@Test(priority = 0)
	public void returnsonlyaddcomment() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, clientname, username, "United States",
				"36 Railroad Street", "Linfield", "Pennsylvania", "19468", "Premium", "Returns Only", POD);
		userRegistration.AddtionalFeatures("", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("TestProfname", "TestProlname", "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling("United States", "36 Railroad Street", "Linfield", "Pennsylvania", "19468",
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");

		userRegistration.Logout();

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		documentCenter.UploadNotices("Pennsylvania", xGetCustomizeddate(-2, 0), xGetCustomizedDate(0, -2, 0),
				"Accrual_AdjustmentFile-TransBeforeCompanyCreateDate",
				"ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCentebjfbfghshhbjsdh");

		userRegistration.Logout();

	}

	// User gets an error message Please select a file without upload notice file
	//https://vertexinc.atlassian.net/browse/CD-18882
	@Test(priority = 1)
	public void unableaddcommentwithoutuploadfile() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");

		documentCenter.UploadNotices("Pennsylvania", xGetCustomizeddate(-2, 0), xGetCustomizedDate(0, -2, 0), "",
				"Upload a Notice to Document Center");

		userRegistration.Logout();

	}

	// Upload notification without adding comment
	//https://vertexinc.atlassian.net/browse/CD-18883
	@Test(priority = 2)
	public void uploadnoticewithoutaddingComment() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.ChangeSubscription(clientname, "Premium", "Tax Calculations and Returns", "");
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "PQA01", "PRIMEQATest", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "California",
				"CA St/Loc/Dist Sales & Use CDTFA-401-A", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Florida", "FL Sales/Use Tax Return DR-15EZ",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Alaska", "AK-HAINES Borough Sales Tax Report",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "New Mexico",
				"NM Combined Sales/Use Tax Report CRS-1", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.GoLive("PRIMEQATest");

		userRegistration.Logout();

		userRegistration.UserLogin(adminusername, adminpassword);

		premiumCustomers.onboardCustomer(clientname, "PRIMEQATest", xGetCustomizedMon(-5, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		documentCenter.UploadNotices("Pennsylvania", xGetCustomizeddate(-1, 0), xGetCustomizedDate(0, -1, 0),
				"AdjustmentFile_InvoiceTax", "");

		userRegistration.Logout();

	}

	// notification emails sent when a customer uploads a notice to the Document
	// Center be sent to vtmsdev gmail and read attachement
	// https://vertexinc.atlassian.net/browse/CD-8343
	@Test(priority = 3, enabled = false)
	public void getnotificationuploadNoticedocumentTest() throws Exception {

		// NotificationEmail(clientname, "ClientNotices uploaded to Document Center");

	}

	// Upload notification with adding comment and An email should generate
	// successfully
	// https://vertexinc.atlassian.net/browse/CD-7917
	@Test(priority = 4)
	public void uploadnoticewithaddingComment() throws Exception {

		userRegistration.UserLogin(username, "Password!1");

		documentCenter.UploadNotices("Pennsylvania", xGetCustomizeddate(-1, 0), xGetCustomizedDate(0, -1, 0),
				"AdjustmentFile_InvoiceTax_CalcOnly", "Upload Notice to the Document Center");

		userRegistration.Logout();

		// Need to verfiy the email after upload the Notice Document
		// NotificationEmail(clientname, "ClientNotices uploaded to Document Center");

	}

	// Upload notification with adding comment with less than 255 characters
	// including special characters
	//https://vertexinc.atlassian.net/browse/CD-18885
	@Test(priority = 5)
	public void uploadnoticecommentlessthanmaxchaTest() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		documentCenter.UploadNotices("Pennsylvania", xGetCustomizeddate(-1, 0), xGetCustomizedDate(0, -1, 0),
				"AdjustmentFile_InvoiceTax",
				"ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCentebjfbf");

		userRegistration.Logout();
	}

	// Admin Document Center can view the comments add by the Customer Portal
	//https://vertexinc.atlassian.net/browse/CD-18886
	@Test(priority = 6)
	public void adminviewcommentsadd() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);
		documentCenter.ViewDocumentCenterComments(clientname, "Notices", "AdjustmentFile_InvoiceTax",
				"ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCentebjfbf");
		userRegistration.Logout();
	}

	// Upload notification with adding comment with more than 255 characters
	// including special characters
	//https://vertexinc.atlassian.net/browse/CD-18887
	@Test(priority = 7)
	public void uploadnoticecommentmorethanmaxchaTest() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		documentCenter.UploadNotices("Pennsylvania", xGetCustomizeddate(-1, 0), xGetCustomizedDate(0, -1, 0),
				"Accrual_AdjustmentFile-TransBeforeCompanyCreateDate",
				"ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCenterwith898993499990@&*#%^(@&*#&*&@ClientNoticesuploadedtoDocumentCentebjfbfghshhbjsdhjklmsna");

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-8415
	@Test(priority = 8)
	public void dodonotfilereturnsadmin() throws Exception {

		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		manageCustomers.DoNotFileReturns("PA Sales/Use/Hotel Tax PA-3", "PRIMEQATest (PQA01)");
		manageCustomers.DoNotFileReturns("FL Sales/Use Tax Return DR-15EZ", "PRIMEQATest (PQA01)");
		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));
		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		// Returns that are marked Do Not File should not display on the Premium
		// reconciliation report
		premiumCustomers.PremiumReconReport(clientname, "PRIMEQATest (PQA01)");
		// userRegistration.Logout();

		// userRegistration.UserLogin(adminusername, adminpassword);
		// Customer Name, Customer Number, Taxpayer:, Company Name, Filing Period:,
		// Default to current filing period,State,Return Form should display in the
		// PremiumDonotFileReport.csv file
		manageCustomers.DownloadAllReturnfilesadmin();
		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-17258
	@Test(priority = 9)
	public void dozeroReport() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Georgia", "GA Sales/Use Tax Report ST-3",
				"5742", "Mail a paper return", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");

		configuration.AddLocations("PRIMEQATest", "Georgia", "VGAL01", "VGA Location", "", "01/01/2017",
				"8821 Ga 15 Hwy N", "Warthen", "", "31094", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Maryland",
				"MD Sales and Use Tax Return Form 202", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "Yes");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Alabama",
				"AL State Seller's Use Tax Return 2620", "12335678", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");

		configuration.AddZeroReport("PRIMEQATest", "Alabama", "100 per page", "Consumer's Use", "7001", "Non-State");

		configuration.EditZeroReport("PRIMEQATest", "Consumer's Use", "7001", "State");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		reports.configurationReport("PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		reports.adminconfigurationReport(clientname, "PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.UploadAdjustmentFile("CD_17258_CurrentFilingPeriod", "Invoice", "Seller",
				"CD_17258_CurrentFilingPeriod_suc", "Yes", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112014", "", "",
				"United States", "Maryland", "21201", "", "3000", "1", "", "", "", "Seller", "", "", "");

		transactions.syncTime();

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-17386
	@Test(priority = 10)
	public void dosearchbyjurisdiction() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.searchjurisdictionzeroReport("PRIMEQATest", "Alabama", "BULLOCK");

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-17418
	@Test(priority = 11)
	public void dorecordexistaftergolive() throws Exception {

		// need to verify the return audit report page from admin portal

		// need to verify the records manually after go live complete to confirm record
		// not purged in returns application
		// String query="Select * from [dbo].[NonLocUserData]";
		// database.executeQuery(query);

	}

	// https://vertexinc.atlassian.net/browse/CD-17361
	@Test(priority = 12)
	public void doentriesalabamainvitr() throws Exception {

		// Verify all Yes/No entries for Alabama are getting updated in VITR app after
		// entering from cloud UI
		// Server Name: DRETSQL01.vertexsmb.cloud and Database name returns
		String query = "Select * from [dbo].[NonLocUserData]";
		database.executeQuery(query);

	}

	// https://vertexinc.atlassian.net/browse/CD-17362
	@Test(priority = 14)
	public void dotaxcategoriesinreturnsdb() throws Exception {

		// Verify Tax categories for Zero Report(15) and label values are shown in the
		// DB table
		// goto VTMS Portal
		String query = "Select * from [ETL].[ReturnsNonLocSpecificSettings]";
		database.executeQuery(query);

	}

	// https://vertexinc.atlassian.net/browse/CD-17363
	@Test(priority = 14)
	public void doalljurisdictionszeroreport() throws Exception {

		// Verify all 1161 jurisdictions are showing in client portal and matches with
		// DB
		// goto VTMS Portal
		String query = "Select * from [ETL].[ReturnsNonLocation]";
		database.executeQuery(query);

	}

	// https://vertexinc.atlassian.net/browse/CD-18077
	@Test(priority = 15)
	public void dolocseqnumberTest() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois", "IL Sales/Use Tax Return/E911 ST-1",
				"54654", "Mail a paper return", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois", "IL Sales/Use Tax Prepayment Return",
				"58795", "Web File", "Monthly", "", "", "", "", "Flat Amount", "1.00", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Illinois",
				"IL-CHICAGO Titled PP 8400R - Monthly", "88422", "Mail a paper return", "Monthly", "", "", "", "", "",
				"", "", "", "", "");

		configuration.addLocseqnumber("PRIMEQATest", "Illinois", "100 per page", "900");

		configuration.editLocseqnumber("PRIMEQATest", "515");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		reports.configurationReport("PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		reports.adminconfigurationReport(clientname, "PRIMEQATest (PQA01)");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112014", "", "", "", "",
				"", "", "1000", "1", "", "", "140010000", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112015", "", "", "", "",
				"", "", "2000", "1", "", "", "140014352", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112016", "", "", "", "",
				"", "", "3000", "1", "", "", "140014609", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112016", "", "", "", "",
				"", "", "4000", "1", "", "", "140014638", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112017", "", "", "", "",
				"", "", "4000", "1", "", "", "140014490", "", "", "", "");

		transactions.syncTime();

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18108
	@Test(priority = 16)
	public void douploadvalidinvaliddata() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.uploadLocseqNumber("PRIMEQATest", "CUT_Distribute_Credits", ".csv", "Yes", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "GAdjustmentFileHelp", ".pdf", "", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "ReconciliationReport", ".zip", "", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "InternationalReturns", ".txt", "", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "LocationSeqNumberXLSFile", ".xls", "", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "LocationSeqNumberXLSXFile", ".xlsx", "", "", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "ValidNonLocationsSettingsValuesFormat", ".csv", "", "Yes", "");
		configuration.uploadLocseqNumber("PRIMEQATest", "ValidInvalidNonLocationsSettingsValuesFormat", ".csv", "",
				"Yes", "Yes");
		configuration.uploadLocseqNumber("PRIMEQATest", "InvalidNonLocationsSettingsValuesFormat", ".csv", "", "",
				"Yes");

		configuration.viewuploadLocseqNumberHistory("PRIMEQATest", "ValidNonLocationsSettingsValuesFormat", ".csv");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		manageCustomers.ChangeSubscription(clientname, "Professional", "Rate File", "");

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-18133
	@Test(priority = 17, enabled = false)
	public void dolocseqnumberdb() throws Exception {

		// Location Seq Number link with setting id 50 is showing in DB
		String query1 = "Select * from [ETL].[ReturnsNonLocSpecificSettings] where geostate=14";
		database.executeQuery(query1);

		// All 1925 jurisdictions are showing in client portal.( 12 jurisdictions will
		// not have jurisdiction code.)
		String query2 = "Select * from [ETL].[ReturnsNonLocation] where GeoState = 14";
		database.executeQuery(query2);

		// All entries are saved and matches with cloud
		// Use [VertexReturns-4247726874147135]
		String query3 = "Select * from [dbo].[NonLocUserData] where GeoState = 14";
		database.executeQuery(query3);
	}

	// https://vertexinc.atlassian.net/browse/CD-18145
	@Test(priority = 18, enabled = false)
	public void doilrecordexistaftergolive() throws Exception {

		// need to verify the return audit report page from admin portal

		// need to verify the records manually after go live complete to confirm record
		// not purged in returns application
		String query = "Select * from [dbo].[NonLocUserData]";
		database.executeQuery(query);

	}

	// https://vertexinc.atlassian.net/browse/CD-18142
	@Test(priority = 19)
	public void doilsearchbyjurisdiction() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.searchjurisdictionLocseqnumber("PRIMEQATest", "BUREAU");

		userRegistration.Logout();
	}

	// https://vertexinc.atlassian.net/browse/CD-18092
	@Test(priority = 20)
	public void doillocseqnumberforPreTI() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(adminusername, adminpassword);

		changeSubscription.UpgradetoTI(clientname, username, Environment);

		Open(driver, url);
		userRegistration.UserLogin(username + ".ti", "Password!1");
		testInstance.CopyTaxcentralCompanyData("", "PRIMEQATest", "ChangeReportBeforeUpdation.csv", "", "", "",
				"527!@#$%^&*()_',?[{]}-<~>TI");

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username + ".ti", "Password!1");

		configuration.verifyTILocseqnumber("PRIMEQATest", "Illinois");

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-2, 0));

		userRegistration.Logout();

	}

	//https://vertexinc.atlassian.net/browse/CD-19655
	@Test(priority = 21)
	public void generatereturnsafterremovetaxpayer() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.Removetaxpayer("PRIMEQATest", xGetCustomizedDate(0, 0, 0), xGetCustomizedDate(0, 0, 0));

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		userRegistration.Logout();

	}

	// https://vertexinc.atlassian.net/browse/CD-19722
	@Test(priority = 22)
	public void domdreturns() throws Exception {

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		manageTaxreturns.downloadReturns("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "", "");

		userRegistration.Logout();

	}

	@AfterTest
	public void closeBrowser() throws Exception {
		quitDriver(driver);

	}
}
