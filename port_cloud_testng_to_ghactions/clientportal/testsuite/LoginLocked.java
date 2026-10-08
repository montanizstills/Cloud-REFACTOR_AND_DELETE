package clientportal.testsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import common.enums.*;
import common.libraries.generic.CommonFunctions;
import common.listener.TestNGCustom;

@Listeners(TestNGCustom.class)
public class LoginLocked extends CommonFunctions {

	public LoginLocked() throws Exception {
		super();
	}

	private WebDriver driver;
	String ClientName = "LoginLockedClient" + RND();
	String UserName = "LoginLocked" + RND() + "@simulator.amazonses.com";
	String EmailId1 = "vtmsdev@gmail.com";
	
		
	@BeforeTest
	@Parameters({ "env", "Browser" })
	public void openBrowser(@Optional("DEV") String env, @Optional("Firefox") String Browser) throws Exception {
		driver = getDriver(env, Browser);
		setDriver(driver);
		Open(driver, url);
	}
     //https://vertexinc.atlassian.net/browse/CD-9198
	@Test(priority = 0)
	public void UserRegUIVerification() throws Exception {
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, ClientName, UserName, enumCountry.USA.fullName,
				enumAddress.VertexKOPAddress.addressLine1, enumAddress.VertexKOPAddress.city, enumState.PA.fullName,
				enumAddress.VertexKOPAddress.zip5, "Professional", "Tax Calculations and Returns", POD);
		userRegistration.AddtionalFeatures("RATE FILE", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("Login", "Locked", "555 555 5552", "Password!1", "Password!1");
		userRegistration.UserLogin(UserName, "Password!1");
		userRegistration.NewUserBilling(enumCountry.USA.fullName, enumAddress.Linfield.addressLine1,
				enumAddress.Linfield.city, enumState.PA.fullName, enumAddress.Linfield.zip5, enumCreditCard.NUMBER.text,
				enumCreditCard.EXPIRY_MONTH.text, enumCreditCard.EXPIRY_YEAR.text, enumCreditCard.CODE.text);
		ratefiles.UploadRateFile("RateFileTemplate", "", "");
		database.InterfacesTrustedID();
		userRegistration.Logout();
		
	}
	//https://vertexinc.atlassian.net/browse/CD-15816
	@Test(priority = 1)
	public void VertexAdminscreens() throws Exception {
		userRegistration.UserLogin(adminusername, adminpassword);
		if (userRegistration.Userexists("SYSAdmin", "SystemAdminAutomation@simulator.amazonses.com")) {
			userRegistration.Logout();
			Open(driver, url);
			userRegistration.UserLogin("SystemAdminAutomation@simulator.amazonses.com", "Password!1");
		} else {
			userRegistration.AdminsCreation("System Admin", "SYSAdmin", "Automation",
					"SystemAdminAutomation@simulator.amazonses.com", "556 555 5552", "Password!1");
		}
		adminPortal.VertexAdminscreens("Index | Vertex Cloud", "Lookup Tax Rates | Vertex Cloud",
				"Metrics | Vertex Cloud", "Invite Users | Vertex Cloud", "Customer Management | Vertex Cloud",
				"Vertex Cloud VARS | Vertex Cloud", "Manage Configuration | Vertex Cloud", "Inline Help | Vertex Cloud",
				"Update Notifications | Vertex Cloud", "Manage Tax Calendar | Vertex Cloud",
				"Error Monitor | Vertex Cloud");
		userRegistration.Logout();
	}
	//https://vertexinc.atlassian.net/browse/CD-15817
	@Test(priority = 2)
	public void VerifyLookupTaxRatesPage() throws Exception {
		userRegistration.UserLogin("SystemAdminAutomation@simulator.amazonses.com", "Password!1");
		ratesJurisdictions.VerifyLookupTaxRatesPage("Canada", "Canada!#$", "", "Canada!#$", "Ontario", "M5B 1J1", "",
				"", "CANADA,Ontario", "", "GST/HST");
		ratesJurisdictions.VerifyLookupTaxRatesPage("United States", "", "", "", "Delaware", "19901", "", "", "", "",
				"Occupation Use Tax / No Sales or Use Tax Imposed");
		ratesJurisdictions.VerifyLookupTaxRatesPage("United States", "", "", "", "Alabama", "36104", "", "", "", "",
				"Sales and Use Tax");
		ratesJurisdictions.VerifyLookupTaxRatesPage("United States", "", "", "", "Arizona", "85004", "", "", "", "",
				"Transaction Privilege and Use Tax");
		ratesJurisdictions.VerifyLookupTaxRatesPage("", "", "", "", "", "", "390913340", "", "", "",
				"Sales and Use Tax");
		ratesJurisdictions.VerifyLookupTaxRatesPage("", "", "", "", "", "", "390910000", "", "", "",
				"Sales and Use Tax");
		ratesJurisdictions.VerifyLookupTaxRatesPage("", "", "", "", "", "", "700151420", "", "", "", "GST/HST");
		ratesJurisdictions.VerifyLookupTaxRatesPage("", "", "", "", "", "", "700150960", "", "", "", "GST/HST");
		ratesJurisdictions.VerifyLookupTaxRatesPage("", "", "", "", "", "", "200310000", "", "", "",
				"Sales and Use Tax");
		ratesJurisdictions.VerifyLookupTaxRatesPage("United States", "1675 Rocky Mountain Ave", "Larimer", "Loveland",
				"Colorado", "80538-8705", "", "", "Colorado", "6.7", "Sales and Use Tax");
		ratesJurisdictions.VerifyLookupTaxRatesPage("United States", "11901 E Canal DR", "", "Aurora", "Colorado",
				"80011", "", "", "Colorado", "8.0", "Sales and Use Tax");
		ratesJurisdictions.VerifyLookupTaxRatesPage("United States", "8686 E Phillips AVE", "", "Englewood", "Colorado",
				"80112", "", "", "Colorado", "6.75", "Sales and Use Tax");
		ratesJurisdictions.VerifyLookupTaxRatesPage("", "1675 Rocky Mountain Ave", "Larimer", "Loveland", "", "",
				"60695743", "", "Colorado", "6.7", "Sales and Use Tax");
		ratesJurisdictions.VerifyLookupTaxRatesPage("", "11901 E Canal DR", "", "Aurora", "", "", "60050030", "",
				"Colorado", "8.0", "");
		ratesJurisdictions.VerifyLookupTaxRatesPage("", "8686 E Phillips AVE", "", "Englewood", "", "", "60050024", "",
				"Colorado", "6.75", "Sales and Use Tax");
		ratesJurisdictions.VerifyLookupTaxRatesPage("", "", "", "", "", "", "20031000", "Yes", "", "", "");
		userRegistration.Logout();
	}
	//https://vertexinc.atlassian.net/browse/CD-15818
	@Test(priority = 3)
	public void AdminUserLinks() throws Exception {
		// adminPortal.AdminUserLinks("Registered");
	}
	//https://vertexinc.atlassian.net/browse/CD-15819
	@Test(priority = 4)
	public void UpdateNotification() throws Exception {
		userRegistration.UserLogin("SystemAdminAutomation@simulator.amazonses.com", "Password!1");
		adminPortal.UpdateNotification("Manual OneTime Request", "Manual OneTime Request", "Manual OneTime Request1",
				"Changed");
		adminPortal.SystemConfiguration();
		userRegistration.Logout();
	}
	//https://vertexinc.atlassian.net/browse/CD-9207
	@Test(priority = 5)
	public void ManageConfiguration() throws Exception {
		userRegistration.UserLogin("SystemAdminAutomation@simulator.amazonses.com", "Password!1");
		adminPortal.ManageConfiguration("AK-ADAK Sales Tax Return", "ALASKA", "City", "Additional Forms", "Both",
				"Additional Tax", "None", "Optional", "Account Number", "Filing Frequency", "Account ID",
				"Account Number", "ALASKA", "Yes");
		adminPortal.ManageConfiguration("MA Sales/Use Tax Return ST-9", "MASSACHUSETTS", "County", "Common Forms",
				"None", "Location State", "Premium/Premium Plus", "Required for Calculation", "Outlet Number",
				"Registration Number", "Final Return", "Outlet Number", "MAINE", "No");
		adminPortal.ManageConfiguration("WY Sales & Use Tax Return Form 41", "WYOMING", "Unassigned", "", "Use Tax", "",
				"Removed", "Required for Filing and Calculation", "Registration Number", "", "Tax Area",
				"Registration Number", "WYOMING", "");
		adminPortal.ManageConfiguration("", "", "", "", "", "", "Unassigned", "", "Registraton Number", "",
				"Trade name (valid until 12/31/06)", "Registraton Number", "", "");
		adminPortal.ManageConfiguration("", "", "", "", "", "", "Unassigned", "", "Registraton Number", "",
				"Trade name (valid until 12/31/06)", "Registraton Number", "", "");
		adminPortal.ManageConfiguration("", "", "", "", "", "", "All", "", "State Location Code", "", "",
				"State Location Code", "", "");
		adminPortal.ManageConfiguration("", "", "", "", "", "", "", "", "Suboutlet No", "", "", "Suboutlet No", "", "");
		adminPortal.ManageConfiguration("SC Sales and Use Tax Return ST-3", "SOUTH CAROLINA", "", "", "",
				"Type of electronic payment for EDI", "Unassigned", "", "", "", "", "", "", "");
		adminPortal.ManageConfiguration("SC Sales and Use Tax Return ST-3", "SOUTH CAROLINA", "", "", "",
				"Name of foreign-owned bank account", "Unassigned", "", "", "", "", "", "", "");
		adminPortal.VertexSMBCustomers(UserName, "Password!1", ClientName, "Professional: Tax Calculations and Returns",
				"Yes");
		ClientName = "ClientBillpending" + RND();
		UserName = "Pending Bill" + RND() + "@simulator.amazonses.com";
		userRegistration.InviteNewUser("Customer Admin", "VAR", VARName, ClientName, UserName, enumCountry.USA.fullName,
				enumAddress.VertexKOPAddress.addressLine1, enumAddress.VertexKOPAddress.city, enumState.PA.fullName,
				enumAddress.VertexKOPAddress.zip5, "Professional", "Tax Calculations and Returns", "");
		userRegistration.AddtionalFeatures("", "", "", "", "", "", "", "", "");
		userRegistration.CompleteRegistration("Login", "Locked", "555 555 5552", "Password!1", "Password!1");
		userRegistration.UserLogin(UserName, "Password!1");
		userRegistration.NewUserBilling(enumCountry.USA.fullName, enumAddress.Linfield.addressLine1,
				enumAddress.Linfield.city, enumState.PA.fullName, enumAddress.Linfield.zip5, enumCreditCard.NUMBER.text,
				enumCreditCard.EXPIRY_MONTH.text, enumCreditCard.EXPIRY_YEAR.text, enumCreditCard.CODE.text);
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin("SystemAdminAutomation@simulator.amazonses.com", "Password!1");
		adminPortal.InviteUsers("Active", UserName);
		adminPortal.ManageLocSpecificRules("COLORADO", "Is this a Physical Location?");
		userRegistration.Logout();
		Open(driver, url);
		userRegistration.UserLogin(adminusername, adminpassword);
		if (userRegistration.Userexists("DataIntegrity", "DataIntegrityAutomation@simulator.amazonses.com")) {
			userRegistration.Logout();
			Open(driver, url);
			userRegistration.UserLogin("DataIntegrityAutomation@simulator.amazonses.com", "Password!1");
		} else {
			userRegistration.AdminsCreation("Vertex Data Integrity Admin", "DataIntegrity", "Automation",
					"DataIntegrityAutomation@simulator.amazonses.com", "555 555 5552", "Password!1");
			userRegistration.Logout();
		}
		Open(driver, url);
		userRegistration.UserLogin("DataIntegrityAutomation@simulator.amazonses.com", "Password!1");
		adminPortal.DIAdminScreens();
		userRegistration.Logout();
		adminPortal.TweetsandEvents();
	}

	@AfterTest
	public void closeBrowser() throws Exception {
		quitDriver(driver);

	}

}
