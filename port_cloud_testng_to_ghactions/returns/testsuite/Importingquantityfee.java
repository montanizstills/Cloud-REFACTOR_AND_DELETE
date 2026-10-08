/*###################################################################################################
Name: Importingquantityfee.java 
Description: Importing Quantity Based Fee Data and need ability to enter this adjustment 
so the fee flows to the return (Multiple jurisdictions CT, CO, DC, NM)
//Ticket Number CD-14363, CD-5449
Author: Venkat Reddy Kandula
Date Created:11/09/2020
Date Modified: 11/16/2020
Updated Comments:
#####################################################################################################*/

package returns.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class Importingquantityfee extends CommonFunctions {

	public Importingquantityfee() throws Exception {
		super();
	}

	private WebDriver driver;

	String username = "Importquantityfee" + RND() + "@simulator.amazonses.com";
	String clientname = "ImportquantityfeeTest" + RND();

	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {

		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}

	// https://vertexinc.atlassian.net/browse/CD-14753
	@Test(priority = 0)
	public void doimportantquantityctfee() throws Exception {

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

		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		configuration.AddTaxPayer("United States", "PQA01", "PRIMEQATest", "456789012", "6310 Clift Street",
				"Shreveport", "Louisiana", "71105", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Connecticut", "CT Sales/Use Tax Return OS-114",
				"12335678", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Connecticut",
				"CT Prepaid Wireless E911 Return OP-153", "55555578", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");

	}

	// https://vertexinc.atlassian.net/browse/CD-14774
	@Test(dependsOnMethods = { "doimportantquantityctfee" }, priority = 1)
	public void doimportquantitybagfee() throws Exception {

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Colorado",
				"CO-BOULDER Disposable Bag Fee Return", "1233558", "Web File", "Monthly", "", "", "", "", "", "", "",
				"", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Colorado", "CO-FRISCO Bag Fee Return",
				"1233679", "Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "District Of Columbia",
				"DC Sales/Use Tax Return FR-800M/Q/A", "12378", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "New Mexico", "NM-SANTA FE Paper Bag Fee Report",
				"2335611", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

	}

	// https://vertexinc.atlassian.net/browse/CD-14775
	@Test(dependsOnMethods = { "doimportquantitybagfee" }, priority = 2)
	public void doimportquantityprepaid() throws Exception {

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Tennessee",
				"TN Sales and Use Tax Return SLS 450", "23358", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Arkansas", "AR Excise Sales/Use Tax Report",
				"2331", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Arkansas", "AR Sales Tax - Direct Pay Filers",
				"2332", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Arkansas", "AR Use Tax - Direct Pay Filers",
				"2333", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Alabama",
				"AL State Prepaid Wireless 911 Return", "1111", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		/*
		 * configuration.AddJurisdictions("PRIMEQATest", "United States", "Delaware",
		 * "DE Prepaid Wireless Return", "1112", "Web File", "Quarterly", "", "", "",
		 * "", "", "", "", "", "","");
		 */

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Florida", "FL Prepaid Wireless E911 Fee Return",
				"1113", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Georgia", "GA Prepaid Wireless Fee Return",
				"1114", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Iowa", "IA Sales/Retailer's Use Tax 32-022",
				"15505", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Indiana", "IN Prepaid 911 Enhancement Fee",
				"1115", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Kentucky",
				"KY CRMS Prepaid Service Charge Return ", "1116", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Mississippi", "MS Prepaid Wireless E911 Report",
				"1117", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "New York", "NY Prepaid Wireless Worksheet",
				"1118", "Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "New York",
				"NY Part-Qrtly Sales/Use (Qrtly) ST-810", "335874", "Web File", "Quarterly", "", "", "", "", "", "", "",
				"", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "North Carolina",
				"NC 911 Prepaid Wireless Return", "1119", "Web File", "Monthly", "", "", "", "", "", "", "", "", "",
				"");

		/*
		 * configuration.AddJurisdictions("PRIMEQATest", "United States",
		 * "New Hampshire", "NH Prepaid CMRS Surcharge Report", "1289", "Web File",
		 * "Monthly", "", "", "", "", "", "", "", "", "","");
		 */
		configuration.AddJurisdictions("PRIMEQATest", "United States", "Oklahoma", "OK Prepaid Wireless Fee Report",
				"1120", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "South Carolina",
				"SC Wireless 911 Charge Return", "1121", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "California", "CA Prepaid MTS Report", "1122",
				"Web File", "Quarterly", "", "", "", "", "", "", "", "", "", "");

		/*
		 * configuration.AddJurisdictions("PRIMEQATest", "United States", "Iowa",
		 * "IA Semi Monthly Deposits", "1123", "Web File", "Monthly", "", "", "", "",
		 * "", "", "", "", "");
		 */

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Maryland",
				"MD Sales and Use Tax Return Form 202", "1124", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Maine", "ME Sales/Use Tax Return ST-7", "1125",
				"Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Minnesota", "MN Sales/Use Tax Return ST-1M",
				"1126", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Pennsylvania", "PA Sales/Use/Hotel Tax PA-3",
				"1127", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Virginia", "VA Out-of-State Dealer Use ST-8",
				"8542589", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Virginia", "VA Dealer's Sales/Use Tax ST-9",
				"87452189547", "Web File", "Monthly", "", "", "", "", "", "", "", "", "", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Washington",
				"WA Combined Monthly Excise Tax 2406M", "1129", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.AddJurisdictions("PRIMEQATest", "United States", "Wisconsin",
				"WI Police & Fire Protection Fee Wrksht", "1130", "Web File", "Monthly", "", "", "", "", "", "", "", "",
				"", "");

		configuration.CreateProduct("PRIMEQATest", "Seller", "Wireless", "Product Class", "Prepaid Wireless",
				"01/01/2017", "", "General", "TPP/Goods", "Telecommunications - TPP",
				"Prepaid Phone Cards/Plans for Wireless", "", "", "", "", "");

		configuration.CreateProduct("PRIMEQATest", "Seller", "911 Fee", "Product Class", "Prepaid E911", "01/01/2017",
				"", "General", "TPP/Goods", "Telecommunications - TPP", "Prepaid Cell Phones-Preloaded with Airtime",
				"", "", "", "", "");

		configuration.CreateProduct("PRIMEQATest", "Seller", "Bag Fee", "Product Class", "Disposable Bag Fee",
				"01/01/2017", "", "General", "TPP/Goods", "Climate and Environmental",
				"Misc. Environmental Fees/charges", "Disposable Bags", "", "", "", "");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		configuration.QualifyingConditions("PRIMEQATest", "Connecticut", "State", "", "Disposable Bag Fee", "Bag Fee");

		configuration.ConfigPush("PRIMEQATest");
		configuration.VerifyConfigPush("PRIMEQATest");

		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(username, "Password!1");
		configuration.GoLive("PRIMEQATest");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		premiumCustomers.onboardCustomer(clientname, "PRIMEQATest", xGetCustomizedMon(-10, 0));
		userRegistration.Logout();

		Open(driver, url);

		userRegistration.UserLogin(username, "Password!1");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112014", "", "", "", "",
				"", "Prepaid E911,Disposable Bag Fee,Prepaid Wireless", "1000,2000,3000", "1,2,3", "", "", "011150660",
				"", "", "", "Yes");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112015", "", "", "", "",
				"", "Prepaid E911", "2000", "1", "", "", "040000000", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112016", "", "", "", "",
				"", "Prepaid E911", "3000", "1", "", "", "050670077", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112016", "", "", "", "",
				"", "Disposable Bag Fee", "4000", "1", "", "", "060000000", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112017", "", "", "", "",
				"", "Disposable Bag Fee", "4000", "1", "", "", "070000000", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112018", "", "", "", "",
				"", "Disposable Bag Fee", "1200", "1", "", "", "090010010", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112019", "", "", "", "",
				"", "Prepaid E911", "2200", "1", "", "", "080010010", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112020", "", "", "", "",
				"", "Prepaid E911", "2500", "1", "", "", "100090240", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112021", "", "", "", "",
				"", "Prepaid E911", "2800", "1", "", "", "111210080", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112022", "", "", "", "",
				"", "Prepaid E911", "4100", "1", "", "", "161530320", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112023", "", "", "", "",
				"", "Prepaid E911", "4200", "1", "", "", "150970670", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112024", "", "", "", "",
				"", "Prepaid E911", "500", "1", "", "", "180735418", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112025", "", "", "", "",
				"", "Prepaid E911", "600", "1", "", "", "210030020", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112026", "", "", "", "",
				"", "Prepaid E911", "700", "1", "", "", "200110020", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112027", "", "", "", "",
				"", "Prepaid E911", "800", "1", "", "", "241230000", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112028", "", "", "", "",
				"", "Prepaid E911", "900", "1", "", "", "250490340", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112029", "", "", "", "",
				"", "Prepaid E911", "1600", "1", "", "", "341830870", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112030", "", "", "", "",
				"", "Prepaid E911", "1700", "1", "", "", "300130030", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112031", "", "", "", "",
				"", "Disposable Bag Fee", "1800", "1", "", "", "320000000", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112032", "", "", "", "",
				"", "Disposable Bag Fee", "1850", "1", "", "", "330430000", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112033", "", "", "", "",
				"", "Prepaid E911", "1900", "1", "", "", "371090550", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112034", "", "", "", "",
				"", "Prepaid E911", "1900", "1", "", "", "391013000", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112035", "", "", "", "",
				"", "Prepaid E911", "1950", "1", "", "", "410790230", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112036", "", "", "", "",
				"", "Prepaid E911", "2300", "1", "", "", "430372364", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112037", "", "", "", "",
				"", "Prepaid E911", "2400", "1", "", "", "477600550", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112038", "", "", "", "",
				"", "Prepaid E911", "2800", "1", "", "", "480690000", "", "", "", "");

		transactions.CalculateTaxes("PRIMEQATest (PQA01)", xGetCustomizedDate(0, -1, 0), "2120112039", "", "", "", "",
				"", "Prepaid E911", "2900", "1", "", "", "500250580", "", "", "", "");

		transactions.syncTime();

		manageTaxreturns.Returnsgenerate("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.verifyreturnsGenerated("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0));

		manageTaxreturns.downloadReturns("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0),
				"CT Sales/Use Tax Return OS-114", "OnBoarded");

		returnpdflineitem(Filepath, "Plastic Bag Fee");

		manageTaxreturns.downloadReturns("PRIMEQATest (PQA01)", xGetCustomizedMon(-1, 0), "", "");

	}

	@AfterTest
	public void closebrowser() throws Exception {

		quitDriver(driver);

	}

}
