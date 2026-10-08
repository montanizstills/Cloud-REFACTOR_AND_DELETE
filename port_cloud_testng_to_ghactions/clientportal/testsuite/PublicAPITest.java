package clientportal.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.enums.enumAddress;
import common.enums.enumCountry;
import common.enums.enumState;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class PublicAPITest extends CommonFunctions {

	public PublicAPITest() throws Exception {
		super();
	}

	private WebDriver driver;
	String username = "PubAPIuser" + RND() + "@simulator.amazonses.com";
	String clientname = "PubAPIclient" + RND();

	@BeforeTest(enabled = true)
	@Parameters({ "env", "Browser" })
	public void beforesuite(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);

	}

	@Test(priority = 0, enabled = true)
	public void UserCreationForAPItest() throws Exception {
		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, clientname, username, enumCountry.USA.fullName,
				enumAddress.VertexKOPAddress.addressLine1, enumAddress.VertexKOPAddress.city, enumState.PA.fullName,
				enumAddress.VertexKOPAddress.zip5, "Professional", "Tax Calculations and Returns", POD);
		userRegistration.AddtionalFeatures("RATE FILE,RETAIL TAX EXTRACT", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("PDPFN" + RND(), "PDPFN" + RND(), "556 555 5552", "Password!1",
				"Password!1");
		userRegistration.UserLogin(username, "Password!1");
		userRegistration.NewUserBilling(enumCountry.USA.fullName, enumAddress.VertexKOPAddress.addressLine1,
				enumAddress.VertexKOPAddress.city, enumState.PA.fullName, enumAddress.VertexKOPAddress.zip5,
				"4111111111111111", "Dec (12)", xGetCustomizedYear(2), "123");
		configuration.AddTaxPayer("United States", "PQA01", "PRIMEQATest", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		configuration.AddLocations("PRIMEQATest", "Pennsylvania", "PA", "PA", "", "01/01/2017", "36 Railroad Street",
				"Linfield", "", "19468", "", "12345");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.CreateProduct("PRIMEQATest", "Seller", "CS012", "Product Code", "Canned Software", "01/01/2017",
				"", "General", "TPP/Goods", "Computer Software", "Canned Software",
				"Canned Software Delivered Electronically", "", "", "", "");
		configuration.UploadMappings("PRIMEQATest", "Smoke_ProductsSample", ".csv");
		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");
		configuration.GoLive("PRIMEQATest");
		// Added this step to make sure immutalble calc refresh completed
		transactions.CalculateTaxes("PRIMEQATest (PQA01)", "Live", "2120112014", "", "", "United States",
				"Pennsylvania", "19101", "Canned Software", "1000", "1", "$60.00", "$1,060.00", "", "Seller", "", "",
				"");
		userRegistration.Logout();

	}

	@Test(priority = 1, enabled = true)
	public void AdjustmetfileAPItest() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.GenerateAPIKey(clientname);
		manageCustomers.oauthCredentials(clientname, "Pub_API" + RND());
		restAPI.publicAPItoken(ClientID, ClientSecret, APIkey, APIPassword);
		restAPI.APIUploadAdjustmentFile("quotation", "SmokeAdjustmentFile_QuotationTax");
		restAPI.APIUploadAdjustmentFileStatus();
		restAPI.APIDownloadAdjustmentFile();
		restAPI.APIUploadAdjustmentFile("invoice", "SmokeAdjustmentFile_InvoiceTax");
		restAPI.APIUploadAdjustmentFileStatus();
		restAPI.APIDownloadAdjustmentFile();
		restAPI.APIUploadAdjustmentFile("purchaseorder", "SmokeAdjustmentFile_PurchaseOrder");
		restAPI.APIUploadAdjustmentFileStatus();
		restAPI.APIDownloadAdjustmentFile();
		restAPI.APIUploadAdjustmentFile("distributetax", "SmokeAdjustmentFile_DistributedTax");
		restAPI.APIUploadAdjustmentFileStatus();
		restAPI.APIDownloadAdjustmentFile();
		userRegistration.Logout();

	}

	@Test(priority = 2, enabled = true)
	public void RTETest() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		manageCustomers.GenerateAPIKey(clientname);
		manageCustomers.oauthCredentials(clientname, "Pub_API" + RND());
		restAPI.publicAPItoken(ClientID, ClientSecret, APIkey, APIPassword);
		restAPI.APIRTEStatus("TestRteStart2");
		restAPI.APIDownloadRTE(APIRteStatus);
		userRegistration.Logout();
	}

	@Test(priority = 3, enabled = true)
	public void QBOAPITest() throws Exception {
		restAPI.publicAPItoken(ClientID, ClientSecret, APIkey, APIPassword);
		restAPI.QBOAPITogetsubscription("50");
		restAPI.QBOAPIsubscriptionFeatures("");
		restAPI.QBOAPIAddressValidation("3855 Blair Mill Road", "", "Horsham", "Pennsylvania", "19044");
		restAPI.QBOAPIQuotation(ClientID, "PA", "19044", "1000", "1", xGetCustomizedDate(0), RND(), "PQA01");
		restAPI.QBOAPIInvoice(ClientID, "PA", "19044", "1000", "1", xGetCustomizedDate(0), RND(), "PQA01");
	}

	@Test(priority = 4, enabled = true)
	public void APIOracleReports() throws Exception {
		restAPI.publicAPItoken(ClientID, ClientSecret, APIkey, APIPassword);
		restAPI.APIOracleARAll("PQA01");
		restAPI.APIOracleAPAll("PQA01");
		restAPI.APIOracleARDoc("PQA01");
		restAPI.APITogetARStatus("");
		restAPI.APITogetAPStatus("");

	}

	@AfterTest(enabled = true)
	public void closeBrowser() throws Exception {
		quitDriver(driver);
	}

}
