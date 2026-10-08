package common.libraries.frachise;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import common.libraries.generic.CommonFunctions;
import common.pages.CPA;
import common.pages.ConfigurePage;
import common.pages.Cpa_ManageUsers;
import common.pages.LoginPage;
import common.pages.LookupTaxRates;
import common.pages.ProfessionalUserLinks;
import common.pages.RegistrationPage;

public class Franchise extends CommonFunctions {

	private WebDriver driver;
	String parentwindow;

	public Franchise(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void MerchantRegistration(String clientname, String country, String streetaddress, String city, String state,
			String zip, String firstName, String lastName, String email, String phone) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		CPA CP = PageFactory.initElements(driver, CPA.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Cpa_ManageUsers NU = PageFactory.initElements(driver, Cpa_ManageUsers.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		parentwindow = driver.getWindowHandle();
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		driver.get(franchiseurl);
		Thread.sleep(5000);
		click(PUL.ManageMerchants);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.ENROLLBUTTON, driver);
		click(PUL.ENROLLBUTTON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}

		waitforElement(180, CP.BASICCOMPANYNAME, driver);
		Thread.sleep(5000);
		Actions a = new Actions(driver);
		a.click(CP.BASICCOMPANYNAME).build().perform();
		JavaScriptclick(driver, CP.BASICCOMPANYNAME);
		type(CP.BASICCOMPANYNAME, clientname);
		selectByValue(CP.BASICCOUNTRY, country);
		type(CP.BASISTREETADD, streetaddress);
		type(CP.BASICCITY, city);
		selectByValue(CP.BASICSTATE, state);
		click(CP.BASICZIP);
		JavaScripttype(driver, CP.BASICZIP, zip);
		sendkeysTab(CP.BASICZIP);
		Thread.sleep(5000);

		clear(CP.MerchantFIRSTNAME);
		JavaScripttypespace(driver, CP.MerchantFIRSTNAME, firstName);
		Thread.sleep(2000);
		sendkeybackSpace(CP.MerchantFIRSTNAME);

		clear(CP.MerchantLastNAME);
		JavaScripttypespace(driver, CP.MerchantLastNAME, lastName);
		Thread.sleep(2000);
		sendkeybackSpace(CP.MerchantLastNAME);
		sendkeysTab(CP.MerchantLastNAME);
		Thread.sleep(2000);

		click(CP.MerchantMail);
		type(CP.MerchantMail, email);
		sendkeysTab(CP.MerchantMail);
		Thread.sleep(5000);
		click(CP.MerchantPHONE);
		JavaScripttype(driver, CP.MerchantPHONE, phone);
		sendkeysTab(CP.MerchantPHONE);
		Thread.sleep(5000);
		assertTrue(VerifyObject(driver, CP.DefaultRoleAsAdmin));
		JavaScriptclick(driver, CP.SUBMIT);
		waitforElement(180, NU.COMPLETEREGISTRATION, driver);
		click(NU.COMPLETEREGISTRATION);
		Thread.sleep(5000);
		waitforElement(180, CP.CompleteYourRegistrationTitle, driver);
		assertTrue(VerifyObject(driver, CP.CompleteYourRegistrationTitle));
	}

	public void MerchantCompleteRegistration(String UserName, String Password, String PasswordConfirm)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		CPA CP = PageFactory.initElements(driver, CPA.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		// String parentwindow=driver.getWindowHandle();
		/*
		 * click(CP.MERCHANTUSERNAME); clear(CP.MERCHANTUSERNAME);
		 * 
		 * String[] regcData = UserName.split(";"); String MerchantUserName =
		 * xLabelRND(regcData); JavaScripttypespace(driver, CP.MERCHANTUSERNAME,
		 * MerchantUserName); Thread.sleep(2000); sendkeybackSpace(CP.MERCHANTUSERNAME);
		 * sendkeysTab(CP.MERCHANTUSERNAME);
		 */
		Thread.sleep(2000);
		type(CP.MerchantPASSWORD, Password);
		sendkeysTab(CP.MerchantPASSWORD);
		Thread.sleep(2000);
		type(CP.MerchantCONFIRMPASSWORD, PasswordConfirm);
		sendkeysTab(CP.MerchantCONFIRMPASSWORD);
		Thread.sleep(2000);
		waitforElement(180, CP.FINISHANDSAVE, driver);
		JavaScriptclick(driver, CP.FINISHANDSAVE);
		Thread.sleep(20000);
		/*
		 * if (Environment.equalsIgnoreCase("QA") ||
		 * Environment.equalsIgnoreCase("Stage")) { waitforElement(180,
		 * PUL.DASHBOARDLINK, driver); } else { waitforElement(180, loginPage.LOGIN,
		 * driver); }
		 */
		//driver.close();
		driver.switchTo().window(parentwindow);
		click(CP.MANAGEMERCHANTSMENU);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CP.SEARCHBYMERCHANT, driver);
		type(CP.SEARCHBYMERCHANT, UserName);
		Thread.sleep(5000);
		waitforElement(90, "//tr/td/a[contains(.,'" + UserName + "')]/../../td[contains(.,'Registered, test')]",
				driver);
		WebElement element = driver.findElement(By.xpath("//tr/td/a[contains(.,'" + UserName + "')]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(6000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
			executor.executeScript("arguments[0].click();", element);
		}
		parentwindow = driver.getWindowHandle();
		Thread.sleep(5000);
		Set<String> window1 = driver.getWindowHandles();
		window1.remove(parentwindow);
		driver.switchTo().window(window1.iterator().next());
		Thread.sleep(10000);
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, CNP.COMPANYHEADER, driver);
		System.out.println("Merchant UserName:" + UserName);
		Reporter.log(UserName);
		//driver.close();
		driver.switchTo().window(parentwindow);
		driver.get(url);
		Thread.sleep(2000);
		waitforElement(180, LP.DASHBOARD, driver);
	}

	public void FranchiseAdminLinks() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LookupTaxRates LTR = PageFactory.initElements(driver, LookupTaxRates.class);
		String parentwindow = driver.getWindowHandle();
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		// Dashboard Verification
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.ENROLLBUTTON, driver);
		assertTrue(PUL.ENROLLNEWCLIENTTITLE.isDisplayed());
		String EnrollNewMerchantText = "Enrolled merchants are registered for and managed in Vertex Cloud internally by your firm. Click Enroll New Merchant to complete the registration process and begin Vertex Cloud configuration. Specific pricing information for enrolled merchant is available below.";
		assertTrue((EnrollNewMerchantText.equalsIgnoreCase(getText(PUL.EnrollNewMerchantInfo))));
		assertTrue(PUL.ENROLLBUTTON.isDisplayed());
		assertTrue(PUL.MerchantOverviewSection.isDisplayed());
		String Initiatedcount1 = driver.findElement(By.xpath("//p[contains(.,'Initiated')]/..//div")).getText();
		String Testcount1 = driver.findElement(By.xpath("//p[contains(.,'Test Mode')]/..//div")).getText();
		String Livecount1 = driver.findElement(By.xpath("//p[contains(.,'Live')]/..//div")).getText();
		System.out.println("Initiated count:" + Initiatedcount1);
		System.out.println("Test count:" + Testcount1);
		System.out.println("Live count:" + Livecount1);
		// ManageMerchants Verification
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		JavaScriptclick(driver, PUL.ManageMerchants);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		String MerchantDescription = "Select a merchant name in order to manage the merchant's Vertex Cloud account. For management of merchants, access to Vertex Cloud is dependent upon authorization from the merchant.";
		assertTrue((MerchantDescription.equalsIgnoreCase(getText(PUL.MerchantsTEXT))));
		assertTrue((PUL.MerchantSearchBox).isDisplayed());
		assertTrue((PUL.FilterbyStatus).isDisplayed());
		assertTrue((PUL.ENROLLBUTTON).isDisplayed());
		assertTrue((PUL.MerchantNameColumn).isDisplayed());
		assertTrue((PUL.PhoneColumn).isDisplayed());
		assertTrue((PUL.RegistrationStatusColumn).isDisplayed());
		assertTrue((PUL.ManageAPICredentialsColumn).isDisplayed());
		assertTrue((PUL.ActionsColumn).isDisplayed());
		// LookUpTaxRates Verification
		JavaScriptclick(driver, PUL.RatesJurisdictions);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, PUL.LOOKUPHELPTEXT, driver);
		assertTrue((PUL.LOOKUPTITLE).isDisplayed());
		assertTrue((PUL.LOOKUPHELPTEXT).isDisplayed());
		JavaScriptclick(driver, LTR.TAIDRadio);
		Thread.sleep(5000);
		assertTrue((LTR.TAXAREAID).isDisplayed());
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue((PUL.Collapse).isDisplayed());
		// Settings Verification
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		waitforElement(180, PUL.COMPNAYINFORMATIN, driver);
		assertTrue((PUL.SETTINGSTITLE).isDisplayed());
		assertTrue((PUL.COMPNAYINFORMATIN).isDisplayed());
		assertTrue((PUL.MANAGEUSER).isDisplayed());
		assertTrue((PUL.CREATENUSER).isDisplayed());
		assertTrue((PUL.BillingAndSubScription).isDisplayed());
		assertTrue((PUL.UpdateBillingLink).isDisplayed());
		assertTrue((PUL.MANAGEUSER).isDisplayed());
		assertTrue((PUL.CREATENUSER).isDisplayed());
		assertTrue((PUL.ACTIVEUSERLINK).isDisplayed());
		assertTrue((PUL.PENDINGUSERLINK).isDisplayed());
		/*
		 * if (InvoiceCustomer == null) { assertTrue((PUL.CardNumber).isDisplayed());
		 * assertTrue((PUL.CardExDate).isDisplayed());
		 * assertTrue((PUL.BillingAddress).isDisplayed()); }
		 */
		assertTrue((PUL.ALLRIGHTSRESERVED).isDisplayed());
		assertTrue((PUL.CONTACTSUPPORTLINK).isDisplayed());
		// Help Center
		if (Browser.equalsIgnoreCase("IE")) {
			click(PUL.GETHELPLINK);
		} else {
			JavaScriptclick(driver, PUL.GETHELPLINK);
		}
		Thread.sleep(3000);
		if ((driver.getWindowHandles().size()) > 1) {
			Thread.sleep(5000);
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(parentwindow)) {
					driver.switchTo().window(winHandle);
				}
			}
			// To Handle Additional encryption popup displaying
			if (Environment.equalsIgnoreCase("QA") || Environment.equalsIgnoreCase("DEV")) {
				if (Browser.equalsIgnoreCase("Firefox")) {
					try {
						Alert javascriptAlert = driver.switchTo().alert();
						javascriptAlert.accept();
					} catch (Exception e) {
					}
				}
			}
			waitforText(180, PUL.SEARCHWHATSNEW, driver);
			assertTrue(PUL.PARTNERTOPICS.isDisplayed());
			assertTrue(PUL.CUSTOMERTOPICS.isDisplayed());
			driver.close();
			driver.switchTo().window(parentwindow);
			waitforElement(180, PUL.DASHBOARDLINK, driver);
			click(PUL.DASHBOARDLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		}
	}

	public void FranchiseSecurityTest(String URL) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		waitforElement(180, PUL.COMPNAYINFORMATIN, driver);
		Thread.sleep(5000);
		String url = driver.getCurrentUrl();
		String modifiedurl = url.replace("franchise", "");
		driver.get(modifiedurl + URL);
		Thread.sleep(6000);
		assertTrue((driver.getTitle().equalsIgnoreCase("Page Not Found | Vertex Cloud")));
		Thread.sleep(6000);
		driver.get(franchiseurl);
	}

	public void Franchise_VerifyManageMerchants(String ClientName, String Reg_Incomplete, String BillingAddress,
			String BillingCity, String BillingState, String BillingZip, String CreditCard, String ExpMonth,
			String ExpYear, String CVV) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		String parentwindow1;
		click(CP.MANAGEMERCHANTSMENU);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CP.SEARCHBYMERCHANT, driver);
		type(CP.SEARCHBYMERCHANT, ClientName);
		Thread.sleep(5000);
		waitforElement(90, "//tr/td/a[contains(.,'" + ClientName + "')]/../../td[contains(.,'Registered, test')]",
				driver);
		WebElement element = driver.findElement(By.xpath("//tr/td/a[contains(.,'" + ClientName + "')]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(6000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
			executor.executeScript("arguments[0].click();", element);
		}
		parentwindow1 = driver.getWindowHandle();
		Thread.sleep(5000);
		Set<String> window1 = driver.getWindowHandles();
		window1.remove(parentwindow1);
		driver.switchTo().window(window1.iterator().next());
		Thread.sleep(5000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
		}
		Thread.sleep(30000);
		if (Reg_Incomplete != "") {
			waitforElement(180, RP.UPDATEBILLING, driver);
			JavaScriptclick(driver, RP.UPDATEBILLING);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, RP.SUBMIT, driver);
			type(CP.BILLADDRESS1, BillingAddress);
			type(CP.BILLCITY, BillingCity);
			Thread.sleep(2000);
			selectByValue(CP.BILLSTATE, BillingState);
			click(CP.BILLZIP);
			JavaScripttype(driver, CP.BILLZIP, BillingZip);
			sendkeysTab(CP.BILLZIP);
			Thread.sleep(2000);
			click(CP.CREDITCARDPAYMENT);
			click(CP.CREDITCARD);
			clear(CP.CREDITCARD);
			if (Browser.equalsIgnoreCase("Firefox")) {
				JavaScripttypespace(driver, CP.CREDITCARD, CreditCard);
				Thread.sleep(3000);
				sendkeybackSpace(CP.CREDITCARD);
			} else {
				type(CP.CREDITCARD, CreditCard);
			}
			Thread.sleep(3000);
			sendkeysTab(CP.CREDITCARD);
			selectByValue(CP.EXPMONTH, ExpMonth);
			selectByValue(CP.EXPYEAR, ExpYear);
			type(CP.CVN, CVV);
			click(CP.SUBMIT);
			waitforElement(180, PUL.DASHBOARDLINK, driver);
		}
		driver.close();
		Thread.sleep(5000);
		driver.switchTo().window(parentwindow1);
		Thread.sleep(5000);
	}

}
