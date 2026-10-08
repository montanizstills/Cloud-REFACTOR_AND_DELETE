package common.libraries.cpa;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import common.libraries.customer.PortalFunctions;
import common.libraries.customer.Transactions;
import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.pages.*;

public class CPAPortal extends CommonFunctions {

	private WebDriver driver;

	public CPAPortal(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public static int NewC, Inc, Closec;
	public static String NewUpdatedCount, InProgressUpdatedCount, Initiated, Live;
	PortalFunctions prtlfunction = PageFactory.initElements(driver, PortalFunctions.class);
	UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);

	// R8.14 method for CPA Enrolled New Client
	public void CPA_EnrollNewClient(String ClientOrderForm, String Role, String Clientname, String User_Country,
			String Address1, String City, String State, String zip, String Phone, String Subscription,
			String Subscription_Options, String BillingAddress1, String BillingCity, String BillingState,
			String Billingzip, String CreditCard, String ExpMonth, String ExpYear, String CVN, String FirstName,
			String LastName, String RateFile, String RTRS, String TIR, String VerifyInvalidFromat) throws Exception {
		String Filename;
		CPA CP = PageFactory.initElements(driver, CPA.class);
		CpaRegistration CPAR = PageFactory.initElements(driver, CpaRegistration.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String Billing, BillingZip1, CCND, BillingCity1, parentwindow = null;
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String AutoITpath = xAutoIT();
		// String browser = xBrowser();
		File directory = new File(".//");
		MultipleBPOClients = "";
		Filename = ClientOrderForm;
		if (VerifyObject(driver, CP.MANAGECLIENTSMENU)) {
			click(CP.MANAGECLIENTSMENU);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CP.ENROLLNEWCLIENT, driver);
			click(CP.ENROLLNEWCLIENT);
			Thread.sleep(10000);
			parentwindow = driver.getWindowHandle();
			Thread.sleep(2000);
			Set<String> window = driver.getWindowHandles();
			window.remove(parentwindow);
			driver.switchTo().window(window.iterator().next());
			Thread.sleep(3000);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		BPOCPAClientName = Clientname;
		waitforElement(90, CP.BASICCOMPANYNAME, driver);
		type(CP.BASICCOMPANYNAME, BPOCPAClientName);
		System.out.println("BPOClientName:" + BPOCPAClientName);
		Reporter.log(BPOCPAClientName);
		waitforElement(90, CP.BASICCOUNTRY, driver);
		selectByValue(CP.BASICCOUNTRY, User_Country);
		type(CP.BASISTREETADD, Address1);
		type(CP.BASICCITY, City);
		selectByValue(CP.BASICSTATE, State);
		click(CP.BASICZIP);
		JavaScripttype(driver, CP.BASICZIP, zip);
		// sendkeysTab(CP.BASICZIP);
		Thread.sleep(2000);
		click(CP.BASICPHONE);
		JavaScripttype(driver, CP.BASICPHONE, Phone);
		Thread.sleep(2000);
		// sendkeysTab(CP.BASICPHONE);
		Thread.sleep(2000);
		if ((Subscription).equalsIgnoreCase("PROFESSIONAL")) {
			click(CP.PROFESSIONALSUBSCRIPTION);
		}
		if ((Subscription).equalsIgnoreCase("PREMIUM")) {
			click(CP.PREMIUMSUBSCRIPTION);
		}
		// clicking statndard subscription
		if ((Subscription).equalsIgnoreCase("Standard")) {
			click(CP.STANDARD);
		}
		Thread.sleep(2000);
		if ((Subscription_Options).equalsIgnoreCase("RETURNS ONLY")) {
			click(CP.RETONLYRADIO);
		}
		if ((Subscription_Options).equalsIgnoreCase("Tax Calculations")) {
			click(CP.CALCONLY);
		}
		Thread.sleep(3000);
		if (RateFile.equalsIgnoreCase("Yes")) {
			click(CP.RATEFILE);
		}
		if (RTRS.equalsIgnoreCase("Yes")) {
			click(CP.RTRS);
		}
		if (TIR.equalsIgnoreCase("Yes")) {
			click(CP.TIR);
		}
		if (!VerifyObject(driver, CPAR.TERMSOFSERVICE)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		JavaScriptclick(driver, CPAR.AGREEMENTLINK);
		Thread.sleep(5000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "CPAPDFReports";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000); // to get the file from dafault download path
									// to specified path
			LatestDownloadedFile("CPAPDFReports", true);
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			LatestDownloadedFile("CPAPDFReports", false);
		}

		if (VerifyInvalidFromat.equalsIgnoreCase("Yes")) {
			click(CPAR.IAGREE);
			Thread.sleep(2000);
			assertTrue(CP.CLIENTORDERFORMERROR.isDisplayed());
			JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
			Thread.sleep(2000);
			// transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath,
			// "Invalid.xlsx", "");
			fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, "Invalid.xlsx", "");
			Thread.sleep(2000);
			assertTrue(CP.FILEFORMATERROR.isDisplayed());
			JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
			Thread.sleep(4000);
			// transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath,
			// Filename, "");
			fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, "");
			Thread.sleep(4000);
			click(CPAR.IAGREE);
			Thread.sleep(2000);
		} else {
			JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
			Thread.sleep(4000);
			// transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath,
			// Filename, "");
			fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, "");
			Thread.sleep(4000);
			click(CPAR.IAGREE);
			Thread.sleep(2000);
		}
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex")) {
			driver.get(Environment + "/registration/billing");
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, CP.ClientPhoneErrorText) || VerifyObject(driver, CP.ZipErrorText)) {
			boolean report = false;
			do {
				Thread.sleep(5000);
				CP.BASICPHONE.sendKeys(Keys.CONTROL + "a");
				CP.BASICPHONE.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				CP.BASICPHONE.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				click(CP.BASICZIP);
				CP.BASICZIP.sendKeys(Keys.CONTROL + "a");
				CP.BASICZIP.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				CP.BASICZIP.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				sendkeysTab(CP.BASICZIP);
				Thread.sleep(2000);
				if (!VerifyObject(driver, CP.ClientPhoneErrorText) || VerifyObject(driver, CP.ZipErrorText)) {
					click(RP.SNDIVTE);
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180, CP.BILLADDRESS1, driver);
		// closing window with out user registration
		Billing = BillingAddress1;
		type(CP.BILLADDRESS1, Billing);
		BillingCity1 = BillingCity;
		type(CP.BILLCITY, BillingCity1);
		Thread.sleep(2000);
		selectByValue(CP.BILLSTATE, BillingState);
		BillingZip1 = Billingzip;
		click(CP.BILLZIP);
		JavaScripttype(driver, CP.BILLZIP, BillingZip1);
		sendkeysTab(CP.BILLZIP);
		Thread.sleep(2000);

		// to scroll window down
		scrollDown(driver);
		Thread.sleep(2000);
		click(CP.CREDITCARDPAYMENT);
		click(CP.CREDITCARD);
		clear(CP.CREDITCARD);
		CCND = CreditCard;
		type(CP.CREDITCARD, CreditCard);
		JavaScripttypespace(driver, CP.CREDITCARD, CCND);
		Thread.sleep(3000);
		sendkeybackSpace(CP.CREDITCARD);
		Thread.sleep(3000);
		sendkeysTab(CP.CREDITCARD);
		selectByValue(CP.EXPMONTH, ExpMonth);
		selectByValue(CP.EXPYEAR, ExpYear);
		Thread.sleep(1000);
		type(CP.CVN, CVN);
		clear(CP.BILLFIRSTNAME);
		JavaScripttypespace(driver, CP.BILLFIRSTNAME, FirstName);
		Thread.sleep(2000);
		sendkeybackSpace(CP.BILLFIRSTNAME);

		clear(CP.BILLLASTNAME);
		JavaScripttypespace(driver, CP.BILLLASTNAME, LastName);
		Thread.sleep(2000);
		sendkeybackSpace(CP.BILLLASTNAME);
		sendkeysTab(CP.BILLLASTNAME);
		Thread.sleep(2000);
		click(CP.SUBMIT);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		if (parentwindow != null) {
//			driver.close();
			Thread.sleep(5000);
			driver.switchTo().window(parentwindow);
			click(CP.MANAGECLIENTSMENU);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CP.CUSTOMERNUMBER, driver);
			assertTrue(CP.CUSTOMERNUMBER.isDisplayed());
			type(CP.SEARCHBYCLIENT, BPOCPAClientName);
			Thread.sleep(3000);
			String customernumber = driver.findElement(By
					.xpath("//table/thead/tr/th[@sort-header='customerNumber']/../../../tbody/tr/td/span/a[contains(.,'"
							+ BPOCPAClientName + "')]/../../../td[2]"))
					.getText();
			System.out.println(customernumber);
			assertTrue(!(customernumber.isEmpty()));
		}
	}

	public void CPA_EnrollSharedServices(String ClientOrderForm, String Client, String User_Country, String Address1,
			String City, String State, String zip, String Phone, String Subscription, String Subscription_Options,
			String BillingAddress1, String BillingCity, String BillingState, String Billingzip, String CreditCard,
			String ExpMonth, String ExpYear, String CVN, String FirstName, String LastName, String RateFile,
			String RTRS, String TIR) throws Exception {
		String Filename;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		CpaRegistration CPAR = PageFactory.initElements(driver, CpaRegistration.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		Transactions transactions = PageFactory.initElements(driver, Transactions.class);
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String AutoITpath = xAutoIT();
		File directory = new File(".//");
		String Billing, BillingZip1, CCND, BillingCity1, parentwindow;
		Filename = ClientOrderForm;
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		click(PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(CPAR.SHAREDSERVICESHEADING.isDisplayed());
		assertTrue(CPAR.SHAREDSERVICESTEXT.isDisplayed());
		parentwindow = driver.getWindowHandle();
		click(CPAR.SHAREDSERVICESBUTTON);
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		BPOCPASClientName = Client;
		System.out.println(BPOCPASClientName);
		Reporter.log(BPOCPASClientName);
		waitforElement(90, CP.BASICCOMPANYNAME, driver);
		type(CP.BASICCOMPANYNAME, BPOCPASClientName);
		System.out.println("BPOClientName:" + BPOCPASClientName);
		waitforElement(90, CP.BASICCOUNTRY, driver);
		selectByValue(CP.BASICCOUNTRY, User_Country);
		type(CP.BASISTREETADD, Address1);
		type(CP.BASICCITY, City);
		selectByValue(CP.BASICSTATE, State);
		click(CP.BASICZIP);
		JavaScripttype(driver, CP.BASICZIP, zip);
		sendkeysTab(CP.BASICZIP);
		Thread.sleep(2000);
		click(CP.BASICPHONE);
		JavaScripttype(driver, CP.BASICPHONE, Phone);
		Thread.sleep(2000);
		sendkeysTab(CP.BASICPHONE);
		Thread.sleep(2000);
		if ((Subscription).equalsIgnoreCase("PROFESSIONAL")) {
			click(CP.PROFESSIONALSUBSCRIPTION);
		}
		if ((Subscription).equalsIgnoreCase("PREMIUM")) {
			click(CP.PREMIUMSUBSCRIPTION);
		}
		// clicking statndard subscription
		if ((Subscription).equalsIgnoreCase("Standard")) {
			click(CP.STANDARD);
		}
		Thread.sleep(2000);
		if ((Subscription_Options).equalsIgnoreCase("RETURNS ONLY")) {
			click(CP.RETONLYRADIO);
		}
		if ((Subscription_Options).equalsIgnoreCase("Tax Calculations")) {
			click(CP.CALCONLY);
		}
		if (RateFile.equalsIgnoreCase("Yes")) {
			click(CP.RATEFILE);
		}
		if (RTRS.equalsIgnoreCase("Yes")) {
			click(CP.RTRS);
		}
		if (TIR.equalsIgnoreCase("Yes")) {
			click(CP.TIR);
		}

		if (!VerifyObject(driver, CPAR.TERMSOFSERVICE)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		JavaScriptclick(driver, CPAR.AGREEMENTLINK);
		Thread.sleep(5000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "CPAPDFReports";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000); // to get the file from dafault download path
									// to specified path
			LatestDownloadedFile("CPAPDFReports", true);
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			LatestDownloadedFile("CPAPDFReports", false);
		}
		click(CPAR.IAGREE);
		Thread.sleep(2000);
		if (VerifyObject(driver, CP.CLIENTORDERFORMERROR)) {
			assertTrue(CP.CLIENTORDERFORMERROR.isDisplayed());
			JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
			Thread.sleep(2000);
			transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, "Invalid.xlsx", "");
			Thread.sleep(2000);
			assertTrue(CP.FILEFORMATERROR.isDisplayed());
			JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
			Thread.sleep(2000);
			transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, "");
			Thread.sleep(2000);
			click(CPAR.IAGREE);
			Thread.sleep(2000);
		}
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex")) {
			driver.get(Environment + "/registration/billing");
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, CP.ClientPhoneErrorText) || VerifyObject(driver, CP.ZipErrorText)) {
			boolean report = false;
			do {
				Thread.sleep(5000);
				CP.BASICPHONE.sendKeys(Keys.CONTROL + "a");
				CP.BASICPHONE.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				CP.BASICPHONE.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				click(CP.BASICZIP);
				CP.BASICZIP.sendKeys(Keys.CONTROL + "a");
				CP.BASICZIP.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				CP.BASICZIP.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				sendkeysTab(CP.BASICZIP);
				Thread.sleep(2000);
				if (!VerifyObject(driver, CP.ClientPhoneErrorText) || VerifyObject(driver, CP.ZipErrorText)) {
					click(RP.SNDIVTE);
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180, CP.BILLADDRESS1, driver);
		// closing window with out user registration

		Billing = BillingAddress1;
		type(CP.BILLADDRESS1, Billing);
		BillingCity1 = BillingCity;
		type(CP.BILLCITY, BillingCity1);
		Thread.sleep(2000);
		selectByValue(CP.BILLSTATE, BillingState);
		BillingZip1 = Billingzip;
		click(CP.BILLZIP);
		JavaScripttype(driver, CP.BILLZIP, BillingZip1);
		sendkeysTab(CP.BILLZIP);
		Thread.sleep(2000);

		// to scroll window down
		scrollDown(driver);
		Thread.sleep(2000);
		click(CP.CREDITCARDPAYMENT);
		click(CP.CREDITCARD);
		clear(CP.CREDITCARD);
		CCND = CreditCard;
		type(CP.CREDITCARD, CreditCard);
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScripttypespace(driver, CP.CREDITCARD, CCND);
			Thread.sleep(3000);
			sendkeybackSpace(CP.CREDITCARD);
		}
		Thread.sleep(3000);
		sendkeysTab(CP.CREDITCARD);
		selectByValue(CP.EXPMONTH, ExpMonth);
		selectByValue(CP.EXPYEAR, ExpYear);
		type(CP.CVN, CVN);

		if ((FirstName) != "") {
			clear(CP.BILLFIRSTNAME);
			JavaScripttypespace(driver, CP.BILLFIRSTNAME, FirstName);
			Thread.sleep(2000);
			sendkeybackSpace(CP.BILLFIRSTNAME);
		}
		if ((LastName) != "") {
			clear(CP.BILLLASTNAME);
			JavaScripttypespace(driver, CP.BILLLASTNAME, LastName);
			Thread.sleep(2000);
			sendkeybackSpace(CP.BILLLASTNAME);
			sendkeysTab(CP.BILLLASTNAME);
			Thread.sleep(2000);
		}
		click(CP.SUBMIT);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		driver.close();
		driver.switchTo().window(parentwindow);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		click(CP.MANAGECLIENTSMENU);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CP.SHAREDSERVICESLINK, driver);
		JavaScriptclick(driver, CP.SHAREDSERVICESLINK);
		Thread.sleep(2000);
		waitforElement(180, CP.SEARCHSSCLIENT, driver);
		assertTrue(CP.SHAREDCUSTOMERNUMBER.isDisplayed());
		type(CP.SEARCHSSCLIENT, BPOCPASClientName);
		Thread.sleep(3000);
		String customernumber = driver.findElement(
				By.xpath("//table/thead/tr/th[@sort-header='customerNumber']/../../../tbody/tr/td/span/a[contains(.,'"
						+ BPOCPASClientName + "')]/../../../td[2]"))
				.getText();
		System.out.println(customernumber);
		assertTrue(!(customernumber.isEmpty()));
	}

	public void CPA_Registration(String Environment, String CPAFirstname, String LastName, String Email, String Phone,
			String UserName, String Password, String PasswordConfirm, String Client, String User_Country,
			String Address1, String City, String State, String zip) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		File directory = new File(".//");
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		CpaRegistration CPAR = PageFactory.initElements(driver, CpaRegistration.class);
		String url = "";
		String[] CparegcData, CparegLData, CparegfData, CparegUData;
		MultipleCPAAdmins = "";
		switch (Environment) {
		case "QA":
			url = "https://qacpaportal.vertexsmb.com/registration";
			break;
		case "DEV":
			url = "https://devcpaportal.vertexsmb.com/registration";
			break;
		case "STAGE":
			url = "https://stagecpaportal.vertexsmb.com/registration";
			break;
		case "CLOUDELEMENTS_QA":
			url = "https://qacpaportal.vertexsmb.com/registration";
		}
		driver.get(url);
		waitforElement(180, CPAR.CPA_FIRSTNAME, driver);
		CparegfData = CPAFirstname.split(";");
		if (CPAFirstname != "") {
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CPAR.CPA_FIRSTNAME, driver);
			CPAFirstName = xLabelRND(CparegfData);
			type(CPAR.CPA_FIRSTNAME, CPAFirstName);
		}
		CparegLData = LastName.split(";");
		if (LastName != "") {
			CPALastName = xLabelRND(CparegLData);
			type(CPAR.CPA_LASTNAME, CPALastName);
		}

		if (Email != "") {
			type(CPAR.CPA_EMAIL, Email);
		}
		click(CPAR.CPA_PHONE);
		type(CPAR.CPA_PHONE, Phone);
		CparegUData = UserName.split(";");
		if (UserName != "") {
			CPAAdminName = xLabelRND(CparegUData);
			type(CPAR.CPA_USERNAME, CPAAdminName);
			System.out.println(CPAAdminName);
		}
		assertTrue(CPAR.PSWDVERIFYTXT_XPATH.isDisplayed());
		type(CPAR.CPA_PASSWORD, Password);
		type(CPAR.CPA_CONFIRMPASSWORD, PasswordConfirm);
		// click(CPAR.CPA_AGREEDTOSA);
		CparegcData = Client.split(";");
		if (Client != "") {
			CPAClientName = xLabelRND(CparegcData);
			type(CPAR.CPA_ACCOUNTFIRMNAME, CPAClientName);
		}
		selectByValue(CPAR.CPA_COUNTRY, User_Country);
		type(CPAR.CPA_ADDRESS1, Address1);
		type(CPAR.CPA_CITY, City);
		selectByValue(CPAR.CPA_STATE, State);
		click(CPAR.CPA_ZIP);
		JavaScripttype(driver, CPAR.CPA_ZIP, zip);
		sendkeysTab(CPAR.CPA_ZIP);
		Thread.sleep(2000);
		if (!VerifyObject(driver, CPAR.TERMSOFSERVICE)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		JavaScriptclick(driver, CPAR.AGREEMENTLINK);
		Thread.sleep(5000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "CPAPDFReports";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000); // to get the file from dafault download path
									// to specified path
			LatestDownloadedFile("CPAPDFReports", true);
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			LatestDownloadedFile("CPAPDFReports", false);
		}

		// assertTrue(CPAR.CPA_AGREEDTOS.isDisplayed());
		// assertTrue(CPAR.AGREETOTERMSTEXT.isDisplayed());
		// assertTrue(CPAR.TERMSOFSERVICE.isDisplayed());
		click(CPAR.IAGREE);
		Thread.sleep(2000);
	}

	public void CPA_AdminLinksVerify(String Environment, String Browser, String BPOAdmin, String REFAdmin,
			String PRODReferNewClient, String ViewAllUser, String CPA_Topics, String file_name1, String file_name3,
			String file_name4) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, LookupTaxRates.class);
		PageFactory.initElements(driver, CpaRegistration.class);
		ProUserTitles = "";
		File directory = new File(".//");
		String parentwindow = driver.getWindowHandle();
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		// Dashboard Verification
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.REFERDOWNLOADPRICINGINFO, driver);
		assertTrue(PUL.SALESTAXAUTOPROGRAM.isDisplayed());
		assertTrue(PUL.STAPHELPTEXT.isDisplayed());
		assertTrue(PUL.READMOREBUTTON.isDisplayed());
		assertTrue(PUL.ENROLLNEWCLIENT.isDisplayed());
		assertTrue(PUL.ENROLLDOWNLOADPRICINGFINFO.isDisplayed());
		// ENROLL DOWNLOA DPRICINGF INFO
		JavaScriptclick(driver, PUL.ENROLLDOWNLOADPRICINGFINFO);
		Thread.sleep(10000);
		// download PDF and verify
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Reports";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Reports", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(5000);
			LatestDownloadedFile("Reports", false);
		}
		String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
		PDFComparison(Filepath, DirName1 + "\\" + file_name1.replace("]", ""));
		Thread.sleep(10000);
		assertTrue(PUL.REFERNEWCLIENT.isDisplayed());
		// REFER DOWNLOAD PRICINGINFO
		assertTrue(PUL.REFERDOWNLOADPRICINGINFO.isDisplayed());
		JavaScriptclick(driver, PUL.REFERDOWNLOADPRICINGINFO);
		Thread.sleep(5000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Reports";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Reports", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(5000);
			LatestDownloadedFile("Reports", false);
		}
		String DirName3 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
		PDFComparison(Filepath, DirName3 + "\\" + file_name3.replace("]", ""));
		Thread.sleep(10000);
		// ENROLL SHARED SERVICES INFO
		assertTrue(PUL.ENROLLSHAREDSERVICESINFO.isDisplayed());
		JavaScriptclick(driver, PUL.ENROLLSHAREDSERVICESINFO);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Reports";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Reports", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(5000);
			LatestDownloadedFile("Reports", false);
		}
		String DirName4 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
		PDFComparison(Filepath, DirName4 + "\\" + file_name4.replace("]", ""));
		// Read More
		JavaScriptclick(driver, PUL.READMOREBUTTON);
		Thread.sleep(10000);
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
		waitforElement(180, PUL.NEWS, driver);
		Thread.sleep(5000);
		assertTrue((gettitle(driver)).equalsIgnoreCase("Vertex | CPA.com - Sales Tax Automation Program"));
		assertTrue(PUL.NEWS.isDisplayed());
		assertTrue(PUL.EVENTS.isDisplayed());
		assertTrue(PUL.ABOUT.isDisplayed());
		driver.close();
		driver.switchTo().window(parentwindow);
		waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
		// Enroll New Client
		if (BPOAdmin.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, PUL.ENROLLNEWCLIENTBUTTON);
			Thread.sleep(3000);
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(parentwindow)) {
					driver.switchTo().window(winHandle);
				}
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.CLIENTREGISTRATION, driver);
			assertTrue((gettitle(driver)).equalsIgnoreCase("Registration | Vertex Cloud"));
			assertTrue(PUL.CLIENTREGISTRATION.isDisplayed());
			driver.close();
			driver.switchTo().window(parentwindow);
			waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
		}
		if (REFAdmin.equalsIgnoreCase("Yes")) {
			assertTrue(PUL.ENROLLEDCLIENTSERREQQUE.isDisplayed());
		}
		// Refer New Client
		JavaScriptclick(driver, PUL.REFERNEWCLIENTBUTTON);
		Thread.sleep(5000);
		driver.switchTo().window(parentwindow);
		if (PRODReferNewClient.equalsIgnoreCase("No")) {
			if (Browser.equalsIgnoreCase("FireFox")) {
				LatestDownloadedFile("ReferClientPDF", false);
			}
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				for (String winHandle : driver.getWindowHandles()) {
					if (!winHandle.equals(parentwindow)) {
						driver.switchTo().window(winHandle);
					}
				}
				driver.close();
				driver.switchTo().window(parentwindow);
				waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
			}
		}
		if (PRODReferNewClient.equalsIgnoreCase("Yes")) {
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(parentwindow)) {
					driver.switchTo().window(winHandle);
				}
			}
			waitforElement(180, PUL.REFERCLIENTHELPETEXT, driver);
			assertTrue((gettitle(driver)).equalsIgnoreCase("Refer a Client | Vertex, Inc."));
			assertTrue(PUL.REFERCLIENTHELPETEXT.isDisplayed());
			driver.close();
			driver.switchTo().window(parentwindow);
			waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
		}
		// Program Resources Verification
		JavaScriptclick(driver, PUL.PROGRAMRESOURCES);
		Thread.sleep(3000);
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
		// driver.switchTo().window(parentwindow);
		Thread.sleep(5000);
		waitforElement(180, PUL.NEWS, driver);
		assertTrue((gettitle(driver)).equalsIgnoreCase("Vertex | CPA.com - Sales Tax Automation Program"));
		assertTrue(PUL.NEWS.isDisplayed());
		assertTrue(PUL.EVENTS.isDisplayed());
		assertTrue(PUL.ABOUT.isDisplayed());
		driver.close();
		driver.switchTo().window(parentwindow);
		waitforElement(180, PUL.PROGRAMRESOURCES, driver);
		// Manage Clients Verification
		JavaScriptclick(driver, PUL.MANAGECLIENTS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.MANAGECLIENTTITLE, driver);
		assertTrue(PUL.MANAGECLIENTTITLE.isDisplayed());
		assertTrue(PUL.MANAGECLIENTHELPTEXT.isDisplayed());
		if (BPOAdmin.equalsIgnoreCase("Yes")) {
			waitforElement(180, PUL.ENROLLNCLIENT, driver);
			assertTrue(PUL.ENROLLNCLIENT.isDisplayed());
		}
	}

	public void CPA_AdminLinks(String Environment, String Browser, String BPOAdmin, String REFAdmin,
			String PRODReferNewClient, String ViewAllUser, String CPA_Topics) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LookupTaxRates LTR = PageFactory.initElements(driver, LookupTaxRates.class);
		CpaRegistration CPAR = PageFactory.initElements(driver, CpaRegistration.class);
		ProUserTitles = "";
		File directory = new File(".//");
		String parentwindow = driver.getWindowHandle();
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		// Dashboard Verification
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.REFERDOWNLOADPRICINGINFO, driver);
		assertTrue(PUL.SALESTAXAUTOPROGRAM.isDisplayed());
		assertTrue(PUL.STAPHELPTEXT.isDisplayed());
		assertTrue(PUL.READMOREBUTTON.isDisplayed());
		assertTrue(PUL.ENROLLNEWCLIENT.isDisplayed());
		assertTrue(PUL.ENROLLDOWNLOADPRICINGFINFO.isDisplayed());
		// ENROLL DOWNLOA DPRICINGF INFO
		JavaScriptclick(driver, PUL.ENROLLDOWNLOADPRICINGFINFO);
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
		Thread.sleep(5000);
		assertTrue(PUL.REFERNEWCLIENT.isDisplayed());
		// REFER DOWNLOAD PRICINGINFO
		JavaScriptclick(driver, PUL.REFERNEWCLIENT);
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
		Thread.sleep(5000);
		assertTrue(PUL.REFERNEWCLIENT.isDisplayed());
		// REFER DOWNLOAD PRICINGINFO
		assertTrue(PUL.REFERDOWNLOADPRICINGINFO.isDisplayed());
		JavaScriptclick(driver, PUL.REFERDOWNLOADPRICINGINFO);
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
		Thread.sleep(5000);
		// ENROLL SHARED SERVICES INFO
		assertTrue(PUL.ENROLLSHAREDSERVICESINFO.isDisplayed());
		JavaScriptclick(driver, PUL.ENROLLSHAREDSERVICESINFO);
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
		// Read More
		JavaScriptclick(driver, PUL.READMOREBUTTON);
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
		waitforElement(180, PUL.NEWS, driver);
		Thread.sleep(5000);
		assertTrue((gettitle(driver)).equalsIgnoreCase("Vertex | CPA.com - Sales Tax Automation Program"));
		assertTrue(PUL.NEWS.isDisplayed());
		assertTrue(PUL.EVENTS.isDisplayed());
		assertTrue(PUL.ABOUT.isDisplayed());
		//driver.close();
		driver.switchTo().window(parentwindow);
		waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
		// Enroll New Client
		if (BPOAdmin.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, PUL.ENROLLNEWCLIENTBUTTON);
			Thread.sleep(3000);
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(parentwindow)) {
					driver.switchTo().window(winHandle);
				}
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.CLIENTREGISTRATION, driver);
			assertTrue((gettitle(driver)).equalsIgnoreCase("Registration | Vertex Cloud"));
			assertTrue(PUL.CLIENTREGISTRATION.isDisplayed());
			//driver.close();
			driver.switchTo().window(parentwindow);
			waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
		}
		/*
		 * if (REFAdmin.equalsIgnoreCase("Yes")) {
		 * assertTrue(PUL.ENROLLEDCLIENTSERREQQUE.isDisplayed()); }
		 */
		// Refer New Client
		JavaScriptclick(driver, PUL.REFERNEWCLIENTBUTTON);
		Thread.sleep(5000);
		driver.switchTo().window(parentwindow);
		if (PRODReferNewClient.equalsIgnoreCase("No")) {
			if (Browser.equalsIgnoreCase("FireFox")) {
				LatestDownloadedFile("ReferClientPDF", false);
			}
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				for (String winHandle : driver.getWindowHandles()) {
					if (!winHandle.equals(parentwindow)) {
						driver.switchTo().window(winHandle);
					}
				}
				driver.close();
				driver.switchTo().window(parentwindow);
				waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
			}
		}
		if (PRODReferNewClient.equalsIgnoreCase("Yes")) {
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(parentwindow)) {
					driver.switchTo().window(winHandle);
				}
			}
			waitforElement(180, PUL.REFERCLIENTHELPETEXT, driver);
			assertTrue((gettitle(driver)).equalsIgnoreCase("Refer a Client | Vertex, Inc."));
			assertTrue(PUL.REFERCLIENTHELPETEXT.isDisplayed());
			//driver.close();
			driver.switchTo().window(parentwindow);
			waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
		}
		// Program Resources Verification
		JavaScriptclick(driver, PUL.PROGRAMRESOURCES);
		Thread.sleep(3000);
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
		// driver.switchTo().window(parentwindow);
		Thread.sleep(5000);
		waitforElement(180, PUL.NEWS, driver);
		assertTrue((gettitle(driver)).equalsIgnoreCase("Vertex | CPA.com - Sales Tax Automation Program"));
		assertTrue(PUL.NEWS.isDisplayed());
		assertTrue(PUL.EVENTS.isDisplayed());
		assertTrue(PUL.ABOUT.isDisplayed());
		driver.close();
		driver.switchTo().window(parentwindow);
		waitforElement(180, PUL.PROGRAMRESOURCES, driver);
		// Manage Clients Verification
		JavaScriptclick(driver, PUL.MANAGECLIENTS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.MANAGECLIENTTITLE, driver);
		assertTrue(PUL.MANAGECLIENTTITLE.isDisplayed());
		assertTrue(PUL.MANAGECLIENTHELPTEXT.isDisplayed());
		if (BPOAdmin.equalsIgnoreCase("Yes")) {
			waitforElement(180, PUL.ENROLLNCLIENT, driver);
			assertTrue(PUL.ENROLLNCLIENT.isDisplayed());
			if (REFAdmin.equalsIgnoreCase("Yes")) {
				scrollDown(driver);
				waitforElement(180, PUL.REFERALNEWCLIENTBUTTON, driver);
				assertTrue(PUL.REFERALNEWCLIENTBUTTON.isDisplayed());
				// LookUpTaxRates Verification
				JavaScriptclick(driver, PUL.RatesJurisdictions);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.LOOKUPHELPTEXT, driver);
				assertTrue(PUL.LOOKUPTITLE.isDisplayed());
				assertTrue(PUL.LOOKUPHELPTEXT.isDisplayed());
				assertTrue(LTR.TAXAREAID.isDisplayed());
				// Settings Verification
				JavaScriptclick(driver, PUL.SETTINGSLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.COMPNAYINFORMATIN, driver);
				assertTrue(PUL.SETTINGSTITLE.isDisplayed());
				assertTrue(PUL.COMPNAYINFORMATIN.isDisplayed());
				assertTrue(PUL.MANAGEUSER.isDisplayed());
				assertTrue(PUL.VIEWALLUSERS.isDisplayed());
				assertTrue(PUL.CREATENUSER.isDisplayed());
				assertTrue(PUL.ACCOUNTCONSOLE.isDisplayed());
				assertTrue(PUL.SUPPORTHQ.isDisplayed());
				if (!VerifyObject(driver, CPAR.TERMSOFSERVICE)) {
					assertTrue(true);
				} else {
					assertFalse(false);
				}
				JavaScriptclick(driver, CPAR.AGREEMENTLINK);
				Thread.sleep(5000);
				if (Browser.equalsIgnoreCase("Chrome")) {
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "CPAPDFReports";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000); // to get the file from dafault download path
											// to specified path
					LatestDownloadedFile("CPAPDFReports", true);
				}
				if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
					LatestDownloadedFile("CPAPDFReports", false);
				}
				// status=VerifyObject(driver, CP.SERVICESAGREEMENT)==false? status: status+
				// "Fail, Services Agreement should not display;";
				// status=VerifyObject(driver, CP.TERMSOFSERVICE)==false? status: status+ "Fail,
				// Terms of Service should not display;";
				if (ViewAllUser.equalsIgnoreCase("Yes")) {
					JavaScriptclick(driver, PUL.VIEWALLUSERS);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, PUL.MANAGEUSERSTITTLE, driver);
					assertTrue(PUL.USERSBYCLIENT.isDisplayed());
					assertTrue(PUL.MANAGEUSERSTITTLE.isDisplayed());
					// Verifying Users By Client Tab as per POR-7216
					JavaScriptclick(driver, PUL.USERSBYCLIENT);
					// waitforElement(30, PUL.USERSBYCLIENTPAGINATION, driver);
					Thread.sleep(2000);
					waitforElement(180, PUL.FILTERBYSTATUS, driver);
					assertTrue(PUL.USERSBYCLIENTPAGINATION.isDisplayed());
					assertTrue(PUL.SEARCHBYCLIENTNAME.isDisplayed());
					assertTrue(PUL.FILTERBYSTATUS.isDisplayed());
					assertTrue(PUL.FILTERBYSUBSCRIPTION.isDisplayed());
					assertTrue(PUL.CLIENTNAME.isDisplayed());
					assertTrue(PUL.CLIENTTYPE.isDisplayed());
					assertTrue(PUL.CLIENTSTATUS.isDisplayed());
					assertTrue(PUL.CLIENTSUBSCRIPTION.isDisplayed());
					assertTrue(PUL.TAXCENTRATACCESSLEVEL.isDisplayed());
					click(PUL.CLIENTNAME);
					Thread.sleep(2000);
					click(PUL.CLIENTNAME);
					Thread.sleep(2000);
					click(PUL.CLIENTTYPE);
					Thread.sleep(2000);
					click(PUL.CLIENTTYPE);
					Thread.sleep(2000);
					click(PUL.CLIENTSTATUS);
					Thread.sleep(2000);
					click(PUL.CLIENTSTATUS);
					Thread.sleep(2000);
					click(PUL.CLIENTSUBSCRIPTION);
					Thread.sleep(2000);
					click(PUL.CLIENTSUBSCRIPTION);
					Thread.sleep(2000);
					click(PUL.TAXCENTRATACCESSLEVEL);
					Thread.sleep(2000);
					click(PUL.TAXCENTRATACCESSLEVEL);
					Thread.sleep(2000);
				}
				JavaScriptclick(driver, PUL.DASHBOARDLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.REFERDOWNLOADPRICINGINFO, driver);
				// Contact Support Verification for CPA Referral Firm Only
				if (!VerifyObject(driver, PUL.ENROLLEDCLIENTSERREQQUE)) {
					JavaScriptclick(driver, PUL.CONTACTSUPPORTLINK);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, PUL.CONTACTSUPPORTTITLE, driver);
					assertTrue(PUL.CONTACTSUPPORTMAIL.isDisplayed());
					assertTrue(PUL.CONTACTSUPPORTTITLE.isDisplayed());
					assertTrue(PUL.PRODTECHSUPPORT.isDisplayed());
					assertTrue(PUL.CONSOLEBILLSUPPORT.isDisplayed());
					assertTrue(PUL.PROGRAMINFORMATION.isDisplayed());
				}

				// Help Center
				if (Browser.equalsIgnoreCase("IE")) {
					click(PUL.GETHELPLINK);
				} else {
					JavaScriptclick(driver, PUL.GETHELPLINK);
				}
				Thread.sleep(3000);
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
				waitforElement(180, PUL.Whatsnew, driver);
				// waitforElement(180, PUL.SEARCH, driver);
				if (CPA_Topics.equalsIgnoreCase("YES")) {
					// Verifying Visit Help Center Page as per POR-6267
					assertTrue(PUL.PARTNERTOPICS.isDisplayed());
					assertTrue(PUL.CUSTOMERTOPICS.isDisplayed());
				}
				assertTrue(PUL.ALLRIGHTSRESERVEDHELPCENTER.isDisplayed());
				//driver.close();
				driver.switchTo().window(parentwindow);
				waitforElement(180, PUL.DASHBOARDLINK, driver);
				click(PUL.DASHBOARDLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.REFERDOWNLOADPRICINGINFO, driver);
				assertTrue(PUL.REFERDOWNLOADPRICINGINFO.isDisplayed());

			}
			if (BPOAdmin.equalsIgnoreCase("Yes") || REFAdmin.equalsIgnoreCase("Yes")) {
				JavaScriptclick(driver, PUL.CONTACTSUPPORTLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.CONTACTSUPPORTTITLE, driver);
				assertTrue(PUL.CONTACTSUPPORTMAIL.isDisplayed());
				assertTrue(PUL.CONTACTSUPPORTTITLE.isDisplayed());
			}
		}
	}

	public void CPA_VerifyManageClients(String ClientName, String ClientType) throws Exception {
		CPA CP = PageFactory.initElements(driver, CPA.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String parentwindow1;
		click(CP.MANAGECLIENTSMENU);
		waitforElement(180, CP.ENROLLEDSEARCHBYCLIENT, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(Environment);
		}
		if (ClientType.equalsIgnoreCase("ENROLLED CLIENTS")) {
			click(CP.ENROLLED_CLIENTS);
			Thread.sleep(2000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CP.ENROLLEDSEARCHBYCLIENT, driver);
			type(CP.ENROLLEDSEARCHBYCLIENT, ClientName);
		} else if (ClientType.equalsIgnoreCase("REFERRED CLIENTS")) {
			click(CP.REFERRED_CLIENTS);
			Thread.sleep(2000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CP.REFERREDSEARCHBYCLIENT, driver);
			type(CP.REFERREDSEARCHBYCLIENT, ClientName);
		} else if (ClientType.equalsIgnoreCase("SHARED SERVICES CLIENTS")) {
			click(CP.SHARED_SERVICES_CLIENTS);
			Thread.sleep(2000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CP.SHAREDSEARCHBYCLIENT, driver);
			type(CP.SHAREDSEARCHBYCLIENT, ClientName);
		}

		Thread.sleep(2000);
		waitforElement(90, "//tr/td/span/a[contains(.,'" + ClientName + "')]/../../../td/a[text()='Edit User Access']",
				driver);
		WebElement element = driver.findElement(By.xpath("//tr/td/span/a[contains(.,'" + ClientName + "')]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(Environment);
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
			driver.get(Environment);
		}
//		Thread.sleep(30000);
//		driver.close();
//		Thread.sleep(5000);
//		driver.switchTo().window(parentwindow1);
		Thread.sleep(5000);

	}

	public void CPA_VerifyViewAllConnectors() throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(60, PUL.SETTINGSLINK, driver);
		click(PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN1, driver);
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		assertTrue(PUL.VIEW_ALL_CONNECTORS.isDisplayed());
		assertTrue(PUL.OSERIES_SOAP.isDisplayed());
		assertTrue(PUL.PUBLIC_REST_API_CONNECTORS.isDisplayed());
		assertTrue(PUL.DOWNLOAD_CONNECTOR.isDisplayed());
		assertTrue(PUL.BTNCONNECTOR.isDisplayed());
		assertTrue(PUL.BIG_COMMERCE_INTEGRATION.isDisplayed());
		assertTrue(PUL.BTNINTEGRATION_SETUP.isDisplayed());
		assertTrue(PUL.QUICKBOOKS_ONLINE_INTEGRATION.isDisplayed());
		assertTrue(PUL.BTNLAUNCH_SITE.isDisplayed());
	}

	public void CPA_BPO_RefClientRegistration(String role, String accountType, String varType, String clientName,
			String firstName, String lastName, String email, String country, String Phone, String Address, String City,
			String State, String Zip, String Subscription, String userName, String passWord, String passwordConfirm,
			String billingAddress, String billingCity, String billingState, String billingZip, String creditcardNumber,
			String expirationMonth, String expirationYear, String CSV) throws Exception {
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		click(RP.INVITEUSER);
		waitforElement(180, RP.ADMININVITE, driver);
		click(RP.ADMININVITE);
		waitforElement(180, RP.ROLE, driver);
		selectByValue(RP.ROLE, role);
		waitforElement(60, RP.VARNAMES, driver);
		selectByValue(RP.ACCOUNTTYPE, accountType);
		sendkeysTab(RP.ACCOUNTTYPE);
		waitforText(60,
				"//label[contains(.,'VAR/CPA/Franchise Name* :')]/../div/select/option[text()='" + varType + "']",
				driver);
		selectByValue(RP.VARNAMES, varType);

		int m = (int) Math.pow(10, 6 - 1);
		int x = m + new Random().nextInt(9 * m);
		String oraclenumber = String.valueOf(x);
		type(RP.OraclePartyNumber, oraclenumber);
		type(RP.CLIENT, clientName);
		type(RP.FIRSTNAME, firstName);
		type(RP.LASTNAME, lastName);
		type(RP.EMAIL, email);
		selectByValue(RP.NEWUSERCOUNTRY, country);
		click(RP.PHONE);
		type(RP.PHONE, Phone);
		sendkeysTab(RP.PHONE);
		JavaScriptclick(driver, RP.NEWUSERSTREETADDRESS1);
		type(RP.NEWUSERSTREETADDRESS1, Address);
		click(RP.NEWUSERCITY);
		type(RP.NEWUSERCITY, City);
		selectByValue(RP.NEWUSERSTATE, State);
		click(RP.NEWUSERZIP);
		JavaScripttype(driver, RP.NEWUSERZIP, Zip);
		sendkeysTab(RP.NEWUSERZIP);

		selectByValue(RP.NEWUSERSUBSCRIPTION, Subscription);
		click(RP.VARRATEFILE);
		click(RP.RETAILTAXEXTRACT);
		click(RP.TAXIMPACTREPORT);
		click(RP.LEGACYTRANSACTIONREPORT);

		JavaScriptclick(driver, RP.SNDIVTE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, RP.USERLINK, driver);
		JavaScriptclick(driver, RP.USERLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
		waitforElement(180, RP.USERNAME, driver);
		type(RP.USERNAME, userName);
		System.out.println(userName);
		Reporter.log(userName);
		click(RP.PASSWORD);
		type(RP.PASSWORD, passWord);
		click(RP.PASSWORDCONFIRM);
		type(RP.PASSWORDCONFIRM, passwordConfirm);
		Thread.sleep(5000);
		click(RP.NEXTSTEP2);
		waitforElement(360, RP.BILLINGADDRESS1, driver);
		type(RP.BILLINGADDRESS1, billingAddress);
		type(RP.BILLINGCITY, billingCity);
		Thread.sleep(10000);
		selectByValue(RP.BILLINGSTATE, billingState);
		click(RP.BILLINGZIP);
		JavaScripttype(driver, RP.BILLINGZIP, billingZip);
		sendkeysTab(RP.BILLINGZIP);
		click(RP.BILLINGCITY);
		Thread.sleep(3000);
		click(RP.CREDITCARDPAYMENT);
		click(RP.CREDITCARD);
		clear(RP.CREDITCARD);
		click(RP.CREDITCARD);
		type(RP.CREDITCARD, creditcardNumber);
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScripttypespace(driver, RP.CREDITCARD, creditcardNumber);
			Thread.sleep(3000);
			sendkeybackSpace(RP.CREDITCARD);
		}
		selectByValue(RP.EXPMONTH, expirationMonth);
		selectByValue(RP.EXPYEAR, expirationYear);
		type(RP.CVN, CSV);

		if (VerifyObject(driver, RP.SUBMITBILLINGINFOBUTTON)) {
			click(RP.SUBMITBILLINGINFOBUTTON);
		} else {
			click(RP.SUBMIT);
		}
		Thread.sleep(5000);
		waitforElement(60, RP.DASHBOARDLINK, driver);
		userregistration.Logout();
		driver.get("https://devcpaportal.vertexsmb.com");
		userregistration.UserLogin("CPABPOAutomation", "Password!1");
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		click(CP.MANAGECLIENTSMENU);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, CP.REFERREDCLIENTS);
		Thread.sleep(2000);
		waitforElement(180, CP.SEARCHREFCLIENT, driver);
		assertTrue(CP.REFERREDCUSTOMERNUMBER.isDisplayed());
		type(CP.SEARCHREFCLIENT, userName);
		Thread.sleep(3000);
		String customernumber = driver.findElement(By.xpath(
				"//table[@sort-table='referred.table.columns']/thead/tr/th[@sort-header='customerNumber']/../../../tbody/tr/td/a[contains(.,'"
						+ userName + "')]/../../td[2]"))
				.getText();
		System.out.println(customernumber);
		assertTrue(!(customernumber.isEmpty()));
	}

	public void CPA_UserAccount(String Email, String Password) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		UserAccount UA = PageFactory.initElements(driver, UserAccount.class);
		click(UA.ACCOUNTOPEN);
		waitforElement(180, UA.USERSETTINGSLINK, driver);
		click(UA.USERSETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, UA.EMAIL, driver);
		Thread.sleep(1000);
		clear(UA.EMAIL);
		Thread.sleep(1000);
		type(UA.EMAIL, Email);
		Thread.sleep(3000);
		click(UA.SAVE);
		waitforElement(60, UA.PASSWORD, driver);
		clear(UA.PASSWORD);
		type(UA.PASSWORD, Password);
		Thread.sleep(1000);
		click(UA.SAVECHANGES);
		Thread.sleep(3000);
		click(UA.GotoDashboard);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			Thread.sleep(3000);
			driver.get(url);
			Thread.sleep(5000);
			if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
				click(LP.FORBIDDEN_LOGOUT);
				waitforElement(60, LP.LOGIN, driver);
			}
		}
	}

	public void CPADashboardCount() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		SalesForcePage SF = PageFactory.initElements(driver, SalesForcePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		click(PUL.DASHBOARDLINK);
		waitforElement(180, PUL.REFERDOWNLOADPRICINGINFO, driver);
		waitforElement(180, SF.NEWTICKETS, driver);
		// Getting New Ticket Value
		String New = getText(SF.NEWTICKETS);
		System.out.println("New=" + New);
		// Getting Inprogress Ticket Value
		String InProgress = getText(SF.INPROGRESSTICKETS);
		System.out.println("InProgress=" + InProgress);
		// Getting Closed Ticket Values
		String Closed = getText(SF.CLOSEDTICKETS);
		System.out.println("Closed=" + Closed);
		NewC = Integer.parseInt(New);
		Inc = Integer.parseInt(InProgress);
		Closec = Integer.parseInt(Closed);
	}

	public void CPA_CreateNewUser(String FirstName, String LastName, String Email, String Phone, String UserRole,
			String ClientAccess1, String ClientAccess2, String PendingStatus, String Resend, String UserName,
			String Password, String ConfirmPassword, String Edit_LastName, String Delete, String Login)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Cpa_ManageUsers NU = PageFactory.initElements(driver, Cpa_ManageUsers.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		String Username;
		String[] regcData;
		MultipleStaffClients = "";
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.REFERDOWNLOADPRICINGINFO, driver);
		JavaScriptclick(driver, NU.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, NU.CREATENEWUSER, driver);
		click(NU.CREATENEWUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, NU.USERROLE, driver);
		type(NU.FIRSTNAME, FirstName);
		type(NU.LASTNAME, LastName);
		type(NU.EMAIL, Email);
		click(NU.PHONENUMBER);
		type(NU.PHONENUMBER, Phone);
		selectByValue(NU.USERROLE, UserRole);
		Thread.sleep(1000);
		// Verifying POR-7219 for Staff Role
		assertTrue(NU.CLIENTACCOUNTACCESS.isDisplayed());
		if (UserRole.equalsIgnoreCase("CPA Staff")) {
			assertTrue(NU.CLIENTAATITLESTAFF.isDisplayed());
			assertTrue(NU.GRANTACCESS.isDisplayed());
			assertTrue(NU.SELECTEDCLIENTS.isDisplayed());
			assertTrue(NU.SELECTALL.isDisplayed());
			assertTrue(NU.REMVOEALL.isDisplayed());
			// Before Selecting the Clients count
			WebElement SelectAll = driver.findElement(By.xpath(NU.SELECTALLCLINETS_XPATH));
			List<WebElement> SAll = SelectAll.findElements(By.tagName("tr"));
			System.out.println(SAll.size());
			click(NU.SELECTALL);
			Thread.sleep(5000);
			// After Selecting the Clients Count
			WebElement RemoveAll = driver.findElement(By.xpath(NU.REMOVEALLCLINETS_XPATH));
			List<WebElement> RAll = RemoveAll.findElements(By.tagName("tr"));
			System.out.println(RAll.size());
			assertTrue((SAll.size() == RAll.size()));
			// Before Removing the Clients Count
			WebElement RemoveAl = driver.findElement(By.xpath(NU.REMOVEALLCLINETS_XPATH));
			List<WebElement> RAl = RemoveAl.findElements(By.tagName("tr"));
			System.out.println(RAl.size());
			click(NU.REMVOEALL);
			Thread.sleep(5000);
			// After Removing the Clients Count
			WebElement SelectAl = driver.findElement(By.xpath(NU.SELECTALLCLINETS_XPATH));
			List<WebElement> SAl = SelectAl.findElements(By.tagName("tr"));
			System.out.println(SAl.size());
			assertTrue((SAl.size() == RAl.size()));
			// Selecting and Verifying BPOClient Name in Client Access
			if (ClientAccess1 != "") {
				driver.findElement(By.xpath("//div/a[contains(.,'Select All')]/../../div/table/tbody/tr/td[contains(.,'"
						+ ClientAccess1 + "')]")).click();
				waitforElement(180, "//div/a[contains(.,'Remove All')]/../../div/table/tbody/tr/td[contains(.,'"
						+ ClientAccess1 + "')]", driver);
				driver.findElement(By.xpath("//div/a[contains(.,'Select All')]/../../div/table/tbody/tr/td[contains(.,'"
						+ ClientAccess2 + "')]")).click();
				waitforElement(180, "//div/a[contains(.,'Remove All')]/../../div/table/tbody/tr/td[contains(.,'"
						+ ClientAccess2 + "')]", driver);
			}
		}
		if (UserRole.equalsIgnoreCase("CPA Admin")) {
			assertTrue(NU.CLIENTAATITLEADMIN.isDisplayed());
		}
		click(NU.SENDINVITE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, NU.COMPLETEREGISTRATION, driver);
		if (PendingStatus.equalsIgnoreCase("yes")) {
			click(NU.CONRETURNTOSETTINGS);
			waitforElement(180, NU.SETTINGSHEADER, driver);
			click(NU.VIEWALLUSERS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, NU.DROPDOWN, driver);
			Username = driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + LastName
					+ "')]/../td[contains(.,'" + LastName + "')]/../td[3]")).getText();
			assertTrue((Username.isEmpty()));
			if (Resend.equalsIgnoreCase("yes")) {
				String name = LastName;
				type(NU.Search, name);
				driver.findElement(By.xpath("//a[contains(.,'" + name + "')]")).click();
				waitforText(180, NU.UserDetails, driver);
				click(NU.Resendlink);
				waitforText(180, NU.Successresend, driver);
				assertTrue(NU.Successresend.isDisplayed());
				click(NU.ReturntoUserListing);
				waitforElement(180, NU.Returntosettings, driver);
				click(NU.Returntosettings);
				waitforElement(180, NU.SETTINGSHEADER, driver);
			}
		} else {
			click(NU.COMPLETEREGISTRATION);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, NU.USERNAME, driver);
			regcData = UserName.split(";");
			if (UserRole.equalsIgnoreCase("CPA Admin")) {
				CPAAdminName = xLabelRND(regcData);
				type(NU.USERNAME, CPAAdminName);
				System.out.println("CPA Admin name " + CPAAdminName);
				loginlockedusername = CPAAdminName;
			} else {
				CPAStaffName = xLabelRND(regcData);
				type(NU.USERNAME, CPAStaffName);
				System.out.println("CPA Staff name=" + CPAStaffName);
				loginlockedusername = CPAStaffName;
			}
			type(NU.PASSWORD, Password);
			type(NU.CONFIRMPASSWORD, ConfirmPassword);
			Thread.sleep(1000);
			JavaScriptclick(driver, NU.PROFILECOMP_REG);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(60, NU.LOGIN, driver);
			click(NU.LOGIN);
			waitforElement(60, NU.SETTINGS, driver);
			JavaScriptclick(driver, NU.SETTINGS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			click(NU.VIEWALLUSERS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			if (UserRole.equalsIgnoreCase("CPA Admin")) {
				// Verifying created User
				click(NU.SORT_CREATED);
				Thread.sleep(3000);
				click(NU.SORT_CREATED);
				Thread.sleep(3000);
				assertTrue(driver.findElement(By.xpath("//table/tbody/tr/td[3][text()='" + CPAAdminName + "']"))
						.isDisplayed());
				// Editing the user and verifying the edited user
				if (Delete.equalsIgnoreCase("yes")) {
					String name = LastName;
					type(NU.Search, name);
					driver.findElement(By.xpath("//a[contains(.,'" + name + "')]")).click();
					waitforText(180, NU.UserDetails, driver);
					click(NU.Deleteuser);
					waitforText(180, NU.Deleteuserheaderpopup, driver);
					assertTrue(NU.Deleteuserheaderpopup.isDisplayed());
					click(NU.Deleteuserbutton);
					waitforText(180, NU.reactivateuser, driver);
					assertTrue(NU.reactivateuser.isDisplayed());
					click(NU.ReturntoUserListing);
					waitforElement(180, NU.Returntosettings, driver);
				}
				if (Edit_LastName != "") {
					driver.findElement(By.xpath("//table/tbody/tr/td[3][text()='" + CPAAdminName + "']/../td[1]/a"))
							.click();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(3000);
					waitforElement(180, NU.LASTNAME_EDIT, driver);
					clear(NU.LASTNAME_EDIT);
					type(NU.LASTNAME_EDIT, Edit_LastName);
					click(NU.SAVE);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, NU.QUICKSEARCH, driver);
					type(NU.QUICKSEARCH, CPAAdminName);
					waitforElement(180, "//table/tbody/tr/td[3][text()='" + CPAAdminName
							+ "']/../td[1]/a/u[contains(text(),'Edited')]", driver);
					assertTrue(driver.findElement(By.xpath("//table/tbody/tr/td[3][text()='" + CPAAdminName
							+ "']/../td[1]/a/u[contains(text(),'Edited')]")).isDisplayed());
				}
			}
			click(NU.RETURNTOSETTINGS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, NU.VIEWALLUSERS, driver);
			// Verifying Client Access POR-7219
			if (ClientAccess1 != "") {
				click(NU.VIEWALLUSERS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, NU.DROPDOWN, driver);
				type(NU.QUICKSEARCH, CPAStaffName);
				waitforElement(180, "//table/tbody/tr/td[contains(.,'" + CPAStaffName + "')]/../td/a", driver);
				driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + CPAStaffName + "')]/../td/a")).click();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, NU.CLIENTACCOUNTACCESS, driver);
				assertTrue(driver.findElement(
						By.xpath("//div/a[contains(.,'Remove All')]/../../div/table/tbody/tr/td[contains(.,'"
								+ ClientAccess1 + "')]"))
						.isDisplayed());
				assertTrue(driver.findElement(
						By.xpath("//div/a[contains(.,'Remove All')]/../../div/table/tbody/tr/td[contains(.,'"
								+ ClientAccess2 + "')]"))
						.isDisplayed());
				click(NU.RETURNTOUSERLISTING);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, NU.RETURNTOSETTINGS, driver);
				click(NU.RETURNTOSETTINGS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, NU.CREATENEWUSER, driver);

			}
			if (Login != "") {
				userregistration.Logout();
				Thread.sleep(5000);
				String URL = driver.getCurrentUrl();
				System.out.println(URL);
				driver.get(URL);
				type(LP.USERNAME, CPAStaffName);
				type(LP.PASSWORD, ConfirmPassword);
				click(LP.LOGIN);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(3000);
				// Dash board
				waitforElement(180, PUL.DASHBOARDLINK, driver);
				JavaScriptclick(driver, PUL.DASHBOARDLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.REFERDOWNLOADPRICINGINFO, driver);
				assertTrue(PUL.SALESTAXAUTOPROGRAM.isDisplayed());
				// MANAGECLIENTS
				JavaScriptclick(driver, PUL.MANAGECLIENTS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.MANAGECLIENTTITLE, driver);
				assertTrue(PUL.MANAGECLIENTTITLE.isDisplayed());
				// LOOKUPTAXRATESLINK
				JavaScriptclick(driver, PUL.RatesJurisdictions);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.LOOKUPHELPTEXT, driver);
				assertTrue(PUL.LOOKUPTITLE.isDisplayed());
				assertTrue(PUL.LOOKUPHELPTEXT.isDisplayed());
				// SETTINGSLINK
				JavaScriptclick(driver, PUL.SETTINGSLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.COMPNAYINFORMATIN, driver);
				assertTrue(PUL.SETTINGSTITLE.isDisplayed());
				assertTrue(PUL.COMPNAYINFORMATIN.isDisplayed());
			}
			MultipleStaffClients = MultipleStaffClients + CPAStaffName + CPAAdminName + ";";
		}
	}

	public void CPALastLogin(String ClientName, String LastLogin) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		String SystemDateTime, LasttLogin;
		String[] S;
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex")) {
			driver.manage().deleteAllCookies();
			if (Environment.equalsIgnoreCase("STAGEC")) {
				driver.get("https://stage.vertexsmb.com/");
			}
			if (Environment.equalsIgnoreCase("QAC")) {
				driver.get("https://qaportal.vertexsmb.com/");
			}
			if (Environment.equalsIgnoreCase("DEVC")) {
				driver.get("https://devportal.vertexsmb.com/");
			}
		}
		userregistration.UserLogin(adminusername, adminpassword);
		Thread.sleep(5000);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement Client = driver.findElement(By.xpath("//table/tbody/tr/td/div/a[contains(.,'" + ClientName + "')]"));
		JavaScriptclick(driver, Client);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.RESEND, driver);
		// Verifying Last Login as per POR-7217 in 5.1
		if (LastLogin.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, VSC.SMBADMIN);
			Thread.sleep(3000);
			waitforElement(180, VSC.LASTLOGIN, driver);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			// get current date time with Calendar()
			Calendar cal = Calendar.getInstance();
			SystemDateTime = dateFormat.format(cal.getTime());
			System.out.println("SystemDateTime=" + SystemDateTime);
			LasttLogin = getText(VSC.LASTLOGIN);
			System.out.println("LasttLogin=" + LasttLogin);
			S = LasttLogin.split(" ");
			System.out.println("SplitLogin=" + S[0]);
			assertTrue(SystemDateTime.equalsIgnoreCase(S[0]));
		}
	}

	// R8.14
	public void AdminCreateNewUser(String Clientname, String FirstName, String LastName, String Email, String Phone,
			String VertexCloudIndirectTax, String ECManager, String GlobalCompliance, String DataIntegrity,
			String IndirectTaxIntelligence, String UserName, String ConfirmPassword) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		PageFactory.initElements(driver, Cpa_ManageUsers.class);
		SmbCustomerUserPage SUP = PageFactory.initElements(driver, SmbCustomerUserPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		PageFactory.initElements(driver, RtePage.class);
		PageFactory.initElements(driver, ManageBrackets.class);
		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String[] regcData;
		waitforElement(180, TPU.SMBCUSTOMERSTAB, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TPU.SMBCUSTOMERSTAB);
		} else {
			click(TPU.SMBCUSTOMERSTAB);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);
		type(TPU.SMBCUSTOMERNAME, Clientname);
		Thread.sleep(4000);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname + "')]", driver);
		WebElement Registered = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname + "')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SUP.ADMINCREATENEWUSER, driver);
		JavaScriptclick(driver, SUP.ADMINCREATENEWUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, SUP.RETURNTOCUSTOMERDETAIL, driver);
		regcData = FirstName.split(";");
		FranchiseFN = xLabelRND(regcData);
		regcData = LastName.split(";");
		FranchiseLN = xLabelRND(regcData);
		type(SUP.FRANCHISEFIRSTNAME, FranchiseFN);
		type(SUP.LASTNAME1, FranchiseLN);
		type(SUP.EMAIL, Email);
		click(SUP.PHONE);
		type(SUP.PHONE, Phone);
		SUP.PHONE.sendKeys(Keys.CONTROL + "a");
		SUP.PHONE.sendKeys(Keys.CONTROL + "x");
		Thread.sleep(2000);
		SUP.PHONE.sendKeys(Keys.CONTROL + "v");
		sendkeysTab(SUP.PHONE);
		Thread.sleep(3000);
		// CD-2797 Update Client User Add process to allow for assignment of role(s) per
		// product active in the user's subscription.
		if (VerifyObject(driver, SUP.ACCOUNTMANAGEMENT)) {
			JavaScriptclick(driver, SUP.ACCOUNTMANAGEMENT);
			selectByValue(SUP.ACCOUNTMANAGEMENT, "Admin");
			Thread.sleep(3000);
			if (VertexCloudIndirectTax.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "Admin");
			}

			if (VertexCloudIndirectTax.equalsIgnoreCase("Read Only")) {
				selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "Read Only");
			}

			if (VertexCloudIndirectTax.equalsIgnoreCase("ECM Only")) {
				selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "ECM Only");
			}

			if (ECManager.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.ECMANAGER, "Admin");
			}

			if (ECManager.equalsIgnoreCase("User")) {
				selectByValue(SUP.ECMANAGER, "User");
			}

			if (GlobalCompliance.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.GLOBALCOMPLIANCE, "Admin");
			}

			if (GlobalCompliance.equalsIgnoreCase("User")) {
				selectByValue(SUP.GLOBALCOMPLIANCE, "User");
			}

			if (GlobalCompliance.equalsIgnoreCase("Read Only")) {
				selectByValue(SUP.GLOBALCOMPLIANCE, "Read Only");
			}

			if (DataIntegrity.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.DATAINTEGRITY, "Admin");
			}
			if (IndirectTaxIntelligence.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.INDIRECTTAXINTELLIGENCE, "Admin");
			}
			if (IndirectTaxIntelligence.equalsIgnoreCase("User")) {
				selectByValue(SUP.INDIRECTTAXINTELLIGENCE, "User");
			}
			Thread.sleep(3000);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, SUP.SENDINVITE);
			} else {
				click(SUP.SENDINVITE);
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, SUP.CONFIRMREGLINK, driver);
			JavaScriptclick(driver, SUP.CONFIRMREGLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(3000);
			// System.out.println(SUP.VertexTitle.getAttribute("alt"));
			// assertTrue(SUP.VertexTitle.getAttribute("alt").equalsIgnoreCase("Vertex
			// Cloud"));
			waitforElement(180, SUP.FRANCHISEUSERNAME, driver);
			regcData = UserName.split(";");
			SMBCustomerUserName = xLabelRND(regcData);
			type(SUP.FRANCHISEUSERNAME, SMBCustomerUserName);
			type(SUP.password, ConfirmPassword);
			type(SUP.CONFIRMPASSWORDNEW, ConfirmPassword);
			Thread.sleep(4000);
			click(SUP.COMPLETE);
			Thread.sleep(4000);
			click(PUL.LOGINLINK);
		}
		/*
		 * bussFunction.xr_Logout(); userregistration.UserLogin(adminusername,
		 * adminpassword);
		 */
		waitforElement(180, LP.DASHBOARD, driver);
		if (SMBCustomerUserName == null) {
			loginlockedusername = FranchiseUser;
		} else {
			loginlockedusername = SMBCustomerUserName;
		}
	}

	// R8.15
	public void AdminCreateNewUser(String Clientname, String FirstName, String LastName, String Email, String Phone,
			String VertexCloudIndirectTax, String ECManager, String GlobalCompliance, String DataIntegrity,
			String IndirectTaxIntelligence, String ConfirmPassword) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		PageFactory.initElements(driver, Cpa_ManageUsers.class);
		SmbCustomerUserPage SUP = PageFactory.initElements(driver, SmbCustomerUserPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		PageFactory.initElements(driver, RtePage.class);
		PageFactory.initElements(driver, ManageBrackets.class);
		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String[] regcData;
		waitforElement(180, TPU.SMBCUSTOMERSTAB, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TPU.SMBCUSTOMERSTAB);
		} else {
			click(TPU.SMBCUSTOMERSTAB);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);
		type(TPU.SMBCUSTOMERNAME, Clientname);
		Thread.sleep(4000);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname + "')]", driver);
		WebElement Registered = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname + "')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SUP.ADMINCREATENEWUSER, driver);
		JavaScriptclick(driver, SUP.ADMINCREATENEWUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, SUP.RETURNTOCUSTOMERDETAIL, driver);
		regcData = FirstName.split(";");
		FranchiseFN = xLabelRND(regcData);
		regcData = LastName.split(";");
		FranchiseLN = xLabelRND(regcData);
		type(SUP.FRANCHISEFIRSTNAME, FranchiseFN);
		type(SUP.LASTNAME1, FranchiseLN);
		type(SUP.EMAIL, Email);
		click(SUP.PHONE);
		type(SUP.PHONE, Phone);
		SUP.PHONE.sendKeys(Keys.CONTROL + "a");
		SUP.PHONE.sendKeys(Keys.CONTROL + "x");
		Thread.sleep(2000);
		SUP.PHONE.sendKeys(Keys.CONTROL + "v");
		sendkeysTab(SUP.PHONE);
		Thread.sleep(3000);
		// CD-2797 Update Client User Add process to allow for assignment of role(s) per
		// product active in the user's subscription.
		if (VerifyObject(driver, SUP.ACCOUNTMANAGEMENT)) {
			JavaScriptclick(driver, SUP.ACCOUNTMANAGEMENT);
			selectByValue(SUP.ACCOUNTMANAGEMENT, "Admin");
			Thread.sleep(3000);
			if (VertexCloudIndirectTax.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "Admin");
			}

			if (VertexCloudIndirectTax.equalsIgnoreCase("Read Only")) {
				selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "Read Only");
			}

			if (VertexCloudIndirectTax.equalsIgnoreCase("ECM Only")) {
				selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "ECM Only");
			}

			if (ECManager.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.ECMANAGER, "Admin");
			}

			if (ECManager.equalsIgnoreCase("User")) {
				selectByValue(SUP.ECMANAGER, "User");
			}

			if (GlobalCompliance.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.GLOBALCOMPLIANCE, "Admin");
			}

			if (GlobalCompliance.equalsIgnoreCase("User")) {
				selectByValue(SUP.GLOBALCOMPLIANCE, "User");
			}

			if (GlobalCompliance.equalsIgnoreCase("Read Only")) {
				selectByValue(SUP.GLOBALCOMPLIANCE, "Read Only");
			}

			if (DataIntegrity.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.DATAINTEGRITY, "Admin");
			}
			if (IndirectTaxIntelligence.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.INDIRECTTAXINTELLIGENCE, "Admin");
			}
			if (IndirectTaxIntelligence.equalsIgnoreCase("User")) {
				selectByValue(SUP.INDIRECTTAXINTELLIGENCE, "User");
			}
			Thread.sleep(3000);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, SUP.SENDINVITE);
			} else {
				click(SUP.SENDINVITE);
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, SUP.CONFIRMREGLINK, driver);
			JavaScriptclick(driver, SUP.CONFIRMREGLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(3000);
			// System.out.println(SUP.VertexTitle.getAttribute("alt"));
			// assertTrue(SUP.VertexTitle.getAttribute("alt").equalsIgnoreCase("Vertex
			// Cloud"));
			waitforElement(180, SUP.FRANCHISEUSERNAME, driver);
			regcData = Email.split(";");
			SMBCustomerUserName = xLabelRND(regcData);
			type(SUP.FRANCHISEUSERNAME, SMBCustomerUserName);
			type(SUP.password, ConfirmPassword);
			type(SUP.CONFIRMPASSWORDNEW, ConfirmPassword);
			Thread.sleep(4000);
			click(SUP.COMPLETE);
			Thread.sleep(4000);
			click(PUL.LOGINLINK);
		}
		/*
		 * bussFunction.xr_Logout(); userregistration.UserLogin(adminusername,
		 * adminpassword);
		 */
		waitforElement(180, LP.DASHBOARD, driver);
		if (SMBCustomerUserName == null) {
			loginlockedusername = FranchiseUser;
		} else {
			loginlockedusername = SMBCustomerUserName;
		}
	}

	// New R8.15 method for Admin create user
	public void AdminCreateNewUserNew(String Clientname, String FirstName, String LastName, String Email, String Phone,
			String VertexCloudIndirectTax, String ECManager, String GlobalCompliance, String DataIntegrity,
			String IndirectTaxIntelligence, String ConfirmPassword) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		PageFactory.initElements(driver, Cpa_ManageUsers.class);
		SmbCustomerUserPage SUP = PageFactory.initElements(driver, SmbCustomerUserPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		PageFactory.initElements(driver, RtePage.class);
		PageFactory.initElements(driver, ManageBrackets.class);
		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(180, TPU.SMBCUSTOMERSTAB, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TPU.SMBCUSTOMERSTAB);
		} else {
			click(TPU.SMBCUSTOMERSTAB);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);
		type(TPU.SMBCUSTOMERNAME, Clientname);
		Thread.sleep(4000);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname + "')]", driver);
		WebElement Registered = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname + "')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SUP.ADMINCREATENEWUSER, driver);
		JavaScriptclick(driver, SUP.ADMINCREATENEWUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, SUP.RETURNTOCUSTOMERDETAIL, driver);
		type(SUP.EMAIL, Email);

		// CD-2797 Update Client User Add process to allow for assignment of role(s) per
		// product active in the user's subscription.
		if (VerifyObject(driver, SUP.ACCOUNTMANAGEMENT)) {
			JavaScriptclick(driver, SUP.ACCOUNTMANAGEMENT);
			selectByValue(SUP.ACCOUNTMANAGEMENT, "Admin");
			Thread.sleep(3000);
			if (VertexCloudIndirectTax.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "Admin");
			}

			if (VertexCloudIndirectTax.equalsIgnoreCase("Read Only")) {
				selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "Read Only");
			}

			if (VertexCloudIndirectTax.equalsIgnoreCase("ECM Only")) {
				selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "ECM Only");
			}

			if (ECManager.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.ECMANAGER, "Admin");
			}

			if (ECManager.equalsIgnoreCase("User")) {
				selectByValue(SUP.ECMANAGER, "User");
			}

			if (GlobalCompliance.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.GLOBALCOMPLIANCE, "Admin");
			}

			if (GlobalCompliance.equalsIgnoreCase("User")) {
				selectByValue(SUP.GLOBALCOMPLIANCE, "User");
			}

			if (GlobalCompliance.equalsIgnoreCase("Read Only")) {
				selectByValue(SUP.GLOBALCOMPLIANCE, "Read Only");
			}

			if (DataIntegrity.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.DATAINTEGRITY, "Admin");
			}
			if (IndirectTaxIntelligence.equalsIgnoreCase("Admin")) {
				selectByValue(SUP.INDIRECTTAXINTELLIGENCE, "Admin");
			}
			if (IndirectTaxIntelligence.equalsIgnoreCase("User")) {
				selectByValue(SUP.INDIRECTTAXINTELLIGENCE, "User");
			}
			Thread.sleep(3000);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, SUP.SENDINVITE);
			} else {
				click(SUP.SENDINVITE);
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, SUP.CONFIRMREGLINK, driver);
			JavaScriptclick(driver, SUP.CONFIRMREGLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(3000);
			type(SUP.ADFIRSTNAME, FirstName);
			Thread.sleep(1000);
			type(SUP.ADLASTNAME, LastName);
			Thread.sleep(1000);
			type(SUP.ADPHONE, Phone);
			Thread.sleep(3000);
			type(SUP.ADPASSWORD, ConfirmPassword);
			Thread.sleep(1000);
			type(SUP.ADCONFORMPASSWORD, ConfirmPassword);
			Thread.sleep(4000);
			click(SUP.Complete_Registration);
			Thread.sleep(4000);
			click(PUL.LOGINLINK);
		}
		/*
		 * bussFunction.xr_Logout(); userregistration.UserLogin(adminusername,
		 * adminpassword);
		 */
		waitforElement(180, LP.DASHBOARD, driver);
		if (SMBCustomerUserName == null) {
			loginlockedusername = FranchiseUser;
		} else {
			loginlockedusername = SMBCustomerUserName;
		}
	}

	public void AdminVerifyNOITIUserStandard(String Clientname) throws Exception {
		PageFactory.initElements(driver, Cpa_ManageUsers.class);
		SmbCustomerUserPage SUP = PageFactory.initElements(driver, SmbCustomerUserPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, RtePage.class);
		PageFactory.initElements(driver, ManageBrackets.class);
		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		waitforElement(180, TPU.SMBCUSTOMERSTAB, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TPU.SMBCUSTOMERSTAB);
		} else {
			click(TPU.SMBCUSTOMERSTAB);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);
		type(TPU.SMBCUSTOMERNAME, Clientname);
		Thread.sleep(4000);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname + "')]", driver);
		WebElement Registered = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname + "')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SUP.ADMINCREATENEWUSER, driver);
		JavaScriptclick(driver, SUP.ADMINCREATENEWUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, SUP.RETURNTOCUSTOMERDETAIL, driver);
		if (!VerifyObject(driver, SUP.INDIRECTTAXINTELLIGENCE)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void CPAReferalfirm_Documentcenter(String ClientName, String Account_type, String Routing_No,
			String Account_No, String Password, String TaxCentral_Access_Level) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		CPA CP = PageFactory.initElements(driver, CPA.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		EditCPAFirmAccess EPA = PageFactory.initElements(driver, EditCPAFirmAccess.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		@SuppressWarnings("unused")
		String Access_Level, parentwindow;
		waitforElement(180, CP.MANAGECLIENTSMENU, driver);
		click(CP.MANAGECLIENTSMENU);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CP.REFERREDCLIENTS, driver);
		click(CP.REFERREDCLIENTS);
		Thread.sleep(5000);
		type(CP.SEARCHCLIENTNAME, ClientName);
		waitforElement(120, "//table/tbody/tr[2]/td/a[contains(text(),'" + ClientName + "')]", driver);
		// TaxCentral_Access_Level = getText(CP.TAXCENTRALACCESSLEVEL);
		driver.findElement(By.xpath("//table/tbody/tr[2]/td/a[contains(text(),'" + ClientName + "')]")).click();
		Thread.sleep(5000);
		if (TaxCentral_Access_Level.equalsIgnoreCase("No Access")) {
			waitforElement(180, CP.NOACCESSGRANTED, driver);
			assertTrue(CP.NOACCESSGRANTED.isDisplayed());
		} else if (TaxCentral_Access_Level.equalsIgnoreCase("Reports Only")) {
			waitforElement(180, CP.CONFIGURATIONREPORT, driver);
			assertTrue(CP.CONFIGURATIONREPORT.isDisplayed());
			assertTrue(CP.TAXABILITYREPORT.isDisplayed());
			assertTrue(CP.TRANSACTIONDETAILREPORT.isDisplayed());
			assertTrue(CP.TRANSACTIONSUMMARYREPORT.isDisplayed());
			assertTrue(CP.UNASSIGNEDDATAREPORT.isDisplayed());
			// Verifying Configuration Report Should Download for Referred Client in CPA
			// Portal as per POR-16880 in 7.0
			JavaScriptclick(driver, CP.CONFIGURATIONREPORT_DOWNLOAD);
			Thread.sleep(5000);
			if (VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
				assertTrue(PUL.ANERRORHASOCCURED.isDisplayed());
				click(PUL.OKERROR);
				waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
				Thread.sleep(3000);
			} else {
				// Comparing the Downloaded file
				if (Browser.equalsIgnoreCase("Chrome")) {
					File directory = new File(".//");
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "ConfigurationReport";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(5000);
					LatestDownloadedFile("ConfigurationReport", true);
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					LatestDownloadedFile("ConfigurationReport", false);
				}
				File file = new File(".//");
				String ExpectedFile = file.getCanonicalPath() + "\\InputTestData\\Reports\\CPAConfigurationReport.xlsx";
				XLSXtoXLSXFilesCompare(ExpectedFile, Filepath);
			}
		} else if (TaxCentral_Access_Level.equalsIgnoreCase("Read / Write")) {
			parentwindow = driver.getWindowHandle();
			Set<String> window = driver.getWindowHandles();
			window.remove(parentwindow);
			driver.switchTo().window(window.iterator().next());
			Thread.sleep(5000);
			waitforElement(180, EPA.SettingsHover, driver);
			JavaScriptMousehover(driver, EPA.SettingsHover);
			Thread.sleep(5000);
			JavaScriptclick(driver, EPA.EDITCPAFIRMLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(5000);
			waitforElement(180, EPA.SAVEBTN, driver);
			assertTrue((driver.findElement(By.xpath("//input[@name='reportType' and @value='3']")).isSelected()));
			click(EPA.NOACCESSREPORT);
			click(EPA.SERVICEAGG);
			click(EPA.SAVEBTN);
			waitforElement(180, EPA.CPAFIRMACCESSPASSWORD, driver);
			type(EPA.CPAFIRMACCESSPASSWORD, Password);
			click(EPA.AUTHORIZEBTN);
			waitforElement(180, EPA.PASSWORDUPDATED, driver);
			assertTrue(EPA.PASSWORDUPDATED.isDisplayed());
			driver.close();
			Thread.sleep(5000);
			driver.switchTo().window(parentwindow);
			waitforElement(180, CP.MANAGECLIENTSMENU, driver);
			click(CP.MANAGECLIENTSMENU);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CP.REFERREDCLIENTS, driver);
			click(CP.REFERREDCLIENTS);
			Thread.sleep(5000);
			type(CP.SEARCHCLIENTNAME, ClientName);
			waitforElement(120, "//table/tbody/tr[2]/td/a[contains(text(),'" + ClientName + "')]", driver);
			Access_Level = getText(CP.TAXCENTRALACCESSLEVEL);
			// assertTrue((Access_Level.equalsIgnoreCase("No Access")));
		} else {
			parentwindow = driver.getWindowHandle();
			Set<String> window = driver.getWindowHandles();
			window.remove(parentwindow);
			driver.switchTo().window(window.iterator().next());
			waitforElement(180, CP.DOCUMENTCENTER, driver);
			click(CP.DOCUMENTCENTER);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CP.ACCOUNTTYPE, driver);
			selectByValue(CP.ACCOUNTTYPE, Account_type);
			type(CP.AROUTINGNUMBER, Routing_No);
			type(CP.ACCOUNTNUMBER, Account_No);
			click(CP.SAVEBANKINFORMATION);
			Thread.sleep(5000);
			type(CP.BILLINFOPASSWORD, Password);
			click(CP.BILLINGPASSWORDOK);
			Thread.sleep(30000);
			assertTrue(CP.ACCOUNTTYPEAUPDATION.isDisplayed());
			driver.close();
			Thread.sleep(5000);
			driver.switchTo().window(parentwindow);
		}
	}

	public void CPA_AdminLinks(String REFAdmin, String BPOAdmin, String PRODReferNewClient, String ViewAllUser,
			String CPA_Topics) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LookupTaxRates LTR = PageFactory.initElements(driver, LookupTaxRates.class);
		File directory = new File(".//");
		ProUserTitles = "";
		String parentwindow = driver.getWindowHandle();
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		// Dashboard Verification
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.REFERDOWNLOADPRICINGINFO, driver);
		assertTrue(PUL.SALESTAXAUTOPROGRAM.isDisplayed());
		assertTrue(PUL.STAPHELPTEXT.isDisplayed());
		assertTrue(PUL.READMOREBUTTON.isDisplayed());
		assertTrue(PUL.ENROLLNEWCLIENT.isDisplayed());
		assertTrue(PUL.ENROLLDOWNLOADPRICINGFINFO.isDisplayed());
		Thread.sleep(10000);
		// download PDF and verify
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Reports";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Reports", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(5000);
			LatestDownloadedFile("Reports", false);
		}
		directory.getCanonicalPath();
		// PDFComparison(Filepath, DirName1 + "\\" + "CPABPO_Pricing".replace("]", ""));
		Thread.sleep(10000);
		assertTrue(PUL.REFERNEWCLIENT.isDisplayed());
		assertTrue(PUL.REFERDOWNLOADPRICINGINFO.isDisplayed());
		// REFER DOWNLOAD PRICINGINFO
		JavaScriptclick(driver, PUL.REFERDOWNLOADPRICINGINFO);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Reports";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Reports", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(5000);
			LatestDownloadedFile("Reports", false);
		}
		directory.getCanonicalPath();
		// PDFComparison(Filepath, DirName3 + "\\" + "CPAReferral_Pricing".replace("]",
		// ""));
		Thread.sleep(10000);
		// ENROLL SHARED SERVICES INFO
		assertTrue(PUL.ENROLLSHAREDSERVICESINFO.isDisplayed());
		JavaScriptclick(driver, PUL.ENROLLSHAREDSERVICESINFO);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Reports";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Reports", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(5000);
			LatestDownloadedFile("Reports", false);
		}
		directory.getCanonicalPath();
		// PDFComparison(Filepath, DirName4 + "\\" + "CPABPO_Sharedservice".replace("]",
		// ""));
		Thread.sleep(10000);
		// Read More
		JavaScriptclick(driver, PUL.READMOREBUTTON);
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		// To Handle Additional encryption popup displaying
		if (Environment.equalsIgnoreCase("QA") || Environment.equalsIgnoreCase("DEV")
				|| Environment.equalsIgnoreCase("AWSQA") || Environment.equalsIgnoreCase("AWSDEV")) {
			if (Browser.equalsIgnoreCase("Firefox")) {
				try {
					Alert javascriptAlert = driver.switchTo().alert();
					javascriptAlert.accept();
				} catch (Exception e) {
				}
			}
		}
		waitforElement(180, PUL.NEWS, driver);
		Thread.sleep(5000);
		assertTrue((gettitle(driver)).equalsIgnoreCase("Vertex | CPA.com - Sales Tax Automation Program"));
		assertTrue(PUL.NEWS.isDisplayed());
		assertTrue(PUL.EVENTS.isDisplayed());
		assertTrue(PUL.ABOUT.isDisplayed());
		driver.close();
		driver.switchTo().window(parentwindow);
		waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
		// Enroll New Client
		if (BPOAdmin.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, PUL.ENROLLNEWCLIENTBUTTON);
			Thread.sleep(3000);
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(parentwindow)) {
					driver.switchTo().window(winHandle);
				}
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.CLIENTREGISTRATION, driver);
			assertTrue((gettitle(driver)).equalsIgnoreCase("Registration | Vertex Cloud"));
			assertTrue(PUL.CLIENTREGISTRATION.isDisplayed());
			driver.close();
			driver.switchTo().window(parentwindow);
			waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
		}
		if (REFAdmin.equalsIgnoreCase("Yes")) {
			if (!VerifyObject(driver, PUL.ENROLLEDCLIENTSERREQQUE)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
		// Refer New Client
		JavaScriptclick(driver, PUL.REFERNEWCLIENTBUTTON);
		Thread.sleep(5000);
		/*
		 * for (String winHandle : driver.getWindowHandles()) { if
		 * (!winHandle.equals(parentwindow)) { driver.switchTo().window(winHandle); } }
		 */
		// driver.close();
		driver.switchTo().window(parentwindow);
		if (PRODReferNewClient.equalsIgnoreCase("No")) {
			if (Browser.equalsIgnoreCase("FireFox")) {
				LatestDownloadedFile("ReferClientPDF", false);
			}
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				for (String winHandle : driver.getWindowHandles()) {
					if (!winHandle.equals(parentwindow)) {
						driver.switchTo().window(winHandle);
					}
				}
				driver.close();
				driver.switchTo().window(parentwindow);
				waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
			}
		}
		if (PRODReferNewClient.equalsIgnoreCase("Yes")) {
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(parentwindow)) {
					driver.switchTo().window(winHandle);
				}
			}
			waitforElement(180, PUL.REFERCLIENTHELPETEXT, driver);
			assertTrue((gettitle(driver)).equalsIgnoreCase("Refer a Client | Vertex, Inc."));
			assertTrue(PUL.REFERCLIENTHELPETEXT.isDisplayed());
			driver.close();
			driver.switchTo().window(parentwindow);
			waitforElement(180, PUL.REFERNEWCLIENTBUTTON, driver);
		}
		// Program Resources Verification
		JavaScriptclick(driver, PUL.PROGRAMRESOURCES);
		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		// To Handle Additional encryption popup displaying
		if (Environment.equalsIgnoreCase("QA") || Environment.equalsIgnoreCase("DEV")
				|| Environment.equalsIgnoreCase("AWSQA") || Environment.equalsIgnoreCase("AWSDEV")) {
			if (Browser.equalsIgnoreCase("Firefox")) {
				try {
					Alert javascriptAlert = driver.switchTo().alert();
					javascriptAlert.accept();
				} catch (Exception e) {
				}
			}
		}
		// driver.switchTo().window(parentwindow);
		Thread.sleep(5000);
		waitforElement(180, PUL.NEWS, driver);
		assertTrue((gettitle(driver)).equalsIgnoreCase("Vertex | CPA.com - Sales Tax Automation Program"));
		assertTrue(PUL.NEWS.isDisplayed());
		assertTrue(PUL.EVENTS.isDisplayed());
		assertTrue(PUL.ABOUT.isDisplayed());
		driver.close();
		driver.switchTo().window(parentwindow);
		waitforElement(180, PUL.PROGRAMRESOURCES, driver);
		// Manage Clients Verification
		JavaScriptclick(driver, PUL.MANAGECLIENTS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.MANAGECLIENTTITLE, driver);
		assertTrue(PUL.MANAGECLIENTTITLE.isDisplayed());
		assertTrue(PUL.MANAGECLIENTHELPTEXT.isDisplayed());
		if (BPOAdmin.equalsIgnoreCase("Yes")) {
			waitforElement(180, PUL.ENROLLNCLIENT, driver);
			assertTrue(PUL.ENROLLNCLIENT.isDisplayed());
			if (REFAdmin.equalsIgnoreCase("Yes")) {
				scrollDown(driver);
				waitforElement(180, PUL.REFERALNEWCLIENTBUTTON, driver);
				assertTrue(PUL.REFERALNEWCLIENTBUTTON.isDisplayed());
				// LookUpTaxRates Verification
				JavaScriptclick(driver, PUL.RatesJurisdictions);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.LOOKUPHELPTEXT, driver);
				assertTrue(PUL.LOOKUPTITLE.isDisplayed());
				assertTrue(PUL.LOOKUPHELPTEXT.isDisplayed());
				assertTrue(LTR.TAXAREAID.isDisplayed());
				// Settings Verification
				JavaScriptclick(driver, PUL.SETTINGSLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.COMPNAYINFORMATIN, driver);
				assertTrue(PUL.SETTINGSTITLE.isDisplayed());
				assertTrue(PUL.COMPNAYINFORMATIN.isDisplayed());
				assertTrue(PUL.MANAGEUSER.isDisplayed());
				assertTrue(PUL.VIEWALLUSERS.isDisplayed());
				assertTrue(PUL.CREATENUSER.isDisplayed());
				assertTrue(PUL.ACCOUNTCONSOLE.isDisplayed());
				assertTrue(PUL.SUPPORTHQ.isDisplayed());
				// status=VerifyObject(driver, CP.SERVICESAGREEMENT)==false? status: status+
				// "Fail, Services Agreement should not display;";
				// status=VerifyObject(driver, CP.TERMSOFSERVICE)==false? status: status+ "Fail,
				// Terms of Service should not display;";
				if (ViewAllUser.equalsIgnoreCase("Yes")) {
					JavaScriptclick(driver, PUL.VIEWALLUSERS);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, PUL.MANAGEUSERSTITTLE, driver);
					assertTrue(PUL.USERSBYCLIENT.isDisplayed());
					assertTrue(PUL.MANAGEUSERSTITTLE.isDisplayed());
					// Verifying Users By Client Tab as per POR-7216
					JavaScriptclick(driver, PUL.USERSBYCLIENT);
					// waitforElement(30, PUL.USERSBYCLIENTPAGINATION, driver);
					Thread.sleep(2000);
					waitforElement(180, PUL.FILTERBYSTATUS, driver);
					assertTrue(PUL.USERSBYCLIENTPAGINATION.isDisplayed());
					assertTrue(PUL.SEARCHBYCLIENTNAME.isDisplayed());
					assertTrue(PUL.FILTERBYSTATUS.isDisplayed());
					assertTrue(PUL.FILTERBYSUBSCRIPTION.isDisplayed());
					assertTrue(PUL.CLIENTNAME.isDisplayed());
					assertTrue(PUL.CLIENTTYPE.isDisplayed());
					assertTrue(PUL.CLIENTSTATUS.isDisplayed());
					assertTrue(PUL.CLIENTSUBSCRIPTION.isDisplayed());
					assertTrue(PUL.TAXCENTRATACCESSLEVEL.isDisplayed());
					click(PUL.CLIENTNAME);
					Thread.sleep(2000);
					click(PUL.CLIENTNAME);
					Thread.sleep(2000);
					click(PUL.CLIENTTYPE);
					Thread.sleep(2000);
					click(PUL.CLIENTTYPE);
					Thread.sleep(2000);
					click(PUL.CLIENTSTATUS);
					Thread.sleep(2000);
					click(PUL.CLIENTSTATUS);
					Thread.sleep(2000);
					click(PUL.CLIENTSUBSCRIPTION);
					Thread.sleep(2000);
					click(PUL.CLIENTSUBSCRIPTION);
					Thread.sleep(2000);
					click(PUL.TAXCENTRATACCESSLEVEL);
					Thread.sleep(2000);
					click(PUL.TAXCENTRATACCESSLEVEL);
					Thread.sleep(2000);
				}
				JavaScriptclick(driver, PUL.DASHBOARDLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.REFERDOWNLOADPRICINGINFO, driver);
				// Contact Support Verification for CPA Referral Firm Only
				if (!VerifyObject(driver, PUL.ENROLLEDCLIENTSERREQQUE)) {
					JavaScriptclick(driver, PUL.CONTACTSUPPORTLINK);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, PUL.CONTACTSUPPORTTITLE, driver);
					assertTrue(PUL.CONTACTSUPPORTTITLE.isDisplayed());
					assertTrue(PUL.PRODTECHSUPPORT.isDisplayed());
					assertTrue(PUL.CONSOLEBILLSUPPORT.isDisplayed());
					assertTrue(PUL.PROGRAMINFORMATION.isDisplayed());
				}
				// Help Center
				if (Browser.equalsIgnoreCase("IE")) {
					click(PUL.GETHELPLINK);
				} else {
					JavaScriptclick(driver, PUL.GETHELPLINK);
				}
				Thread.sleep(3000);
				for (String winHandle : driver.getWindowHandles()) {
					if (!winHandle.equals(parentwindow)) {
						driver.switchTo().window(winHandle);
					}
				}
				// To Handle Additional encryption popup displaying
				if (Environment.equalsIgnoreCase("QA") || Environment.equalsIgnoreCase("DEV")
						|| Environment.equalsIgnoreCase("AWSQA") || Environment.equalsIgnoreCase("AWSDEV")) {
					if (Browser.equalsIgnoreCase("Firefox")) {
						try {
							Alert javascriptAlert = driver.switchTo().alert();
							javascriptAlert.accept();
						} catch (Exception e) {
						}
					}
				}
				waitforElement(180, PUL.Whatsnew, driver);
				// waitforElement(180, PUL.SEARCH, driver);
				if (CPA_Topics.equalsIgnoreCase("YES")) {
					// Verifying Visit Help Center Page as per POR-6267
					assertTrue(PUL.PARTNERTOPICS.isDisplayed());
					assertTrue(PUL.CUSTOMERTOPICS.isDisplayed());
				}
				assertTrue(PUL.ALLRIGHTSRESERVEDHELPCENTER.isDisplayed());
				driver.close();
				driver.switchTo().window(parentwindow);
				waitforElement(180, PUL.DASHBOARDLINK, driver);
				click(PUL.DASHBOARDLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.REFERDOWNLOADPRICINGINFO, driver);
				assertTrue(PUL.REFERDOWNLOADPRICINGINFO.isDisplayed());
			}
		}
	}

	public void CPAPipelineCount(String Verify_SSClientcount, String Reg_Incomplete) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		DashboardPage DB = PageFactory.initElements(driver, DashboardPage.class);
		click(PUL.DASHBOARDLINK);
		waitforElement(180, PUL.ENROLLEDCLIENTS, driver);
		scrollDown(driver);
		Thread.sleep(3000);
		// Getting Initiated Value
		Initiated = getText(DB.INITCOUNT);
		// Getting Live Value
		Live = getText(DB.LIVECOUNT);
		if (Verify_SSClientcount.equalsIgnoreCase("yes")) {
			JavaScriptclick(driver, PUL.SHAREDSERVICESDASHBOARDLINK);
			Thread.sleep(3000);
		}
		if (Reg_Incomplete.equalsIgnoreCase("yes")) {
			Initiatedi = Integer.parseInt(Initiated);
			Livei = Integer.parseInt(Live);
		}
		if (Reg_Incomplete.equalsIgnoreCase("No")) {
			int Initiatedr = Integer.parseInt(Initiated);
			int Liver = Integer.parseInt(Live);
			if (Initiatedr == (Initiatedi - 1)) {
			} else {
			}
			if (Liver == (Livei + 1)) {
			} else {
			}
		}
	}

	public void EnrolledFirmAccess(String ClientName, String ClientOrderForm) throws Exception {
		String Filename;
		Robot rob = new Robot();
		EditCPAFirmAccess EPA = PageFactory.initElements(driver, EditCPAFirmAccess.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Transactions transactions = PageFactory.initElements(driver, Transactions.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		File directory = new File(".//");
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String AutoITpath = xAutoIT();
		String browser = Browser;
		Filename = ClientOrderForm;
		click(CP.MANAGECLIENTSMENU);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CP.SEARCHBYCLIENT, driver);
		type(CP.SEARCHBYCLIENT, ClientName);
		Thread.sleep(5000);
		waitforElement(90, "//tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement edituseraccess = driver.findElement(By.xpath("//tr/td[contains(.,'" + ClientName + "')]/../td/a"));
		JavaScriptclick(driver, edituseraccess);
		waitforElement(180, EPA.CLIENTDETAILSACCOUNTACCESS, driver);
		assertTrue(EPA.CLIENTORDERFORMHEADING.isDisplayed());
//		click(CP.UPLOADFILE);
//		Thread.sleep(2000);
//		assertTrue(CP.ORDER_FORMERROR.isDisplayed());
		JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
		Thread.sleep(2000);
		transactions.AutoItBrowser(driver, browser, AutoITpath, AdjustmentFilePath, "Invalid.xlsx", "");
		Thread.sleep(2000);
		assertTrue(CP.FILEFORMATERROR.isDisplayed());
		JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
		Thread.sleep(2000);
		transactions.AutoItBrowser(driver, browser, AutoITpath, AdjustmentFilePath, Filename, "");
		Thread.sleep(5000);
		JavaScriptclick(driver, CP.UPLOADFILE);
		Thread.sleep(8000);
//		assertTrue(CP.UPLOADSUCCESSMESSAGE.isDisplayed());
//		WebElement filename = driver.findElement(By.xpath("//a[contains(.,'" + Filename + "')]"));
//		JavaScriptclick(driver, filename);
		Thread.sleep(6000);
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(10000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "RateFiles";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(6000);
			new File(LatestDownloadedFile("RateFiles", true));
		}
	}

	public void CPAClientBillingReport(String ClientName, String BillingStatus, String BillingYear,
			String BillingPeriod, String BillingReport) throws Exception {
		CPAClientBillingReport CBR = PageFactory.initElements(driver, CPAClientBillingReport.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		click(CBR.SETTINGS);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		if (BillingStatus.equalsIgnoreCase("Yes")) {
			waitforElement(180, CBR.VIEWALLBILLINGREPORTS, driver);
			click(CBR.VIEWALLBILLINGREPORTS);
			selectByValue(CBR.CLIENT, ClientName);
			selectByValue(CBR.BILLINGYEAR, BillingYear);
			selectByValue(CBR.BILLINGPERIOD, BillingPeriod);
			Thread.sleep(2000);
			selectByValue(CBR.BILLINGTYPE, BillingReport);

			if (VerifyObject(driver, CBR.DOWNLOADBILLINGREPORT)) {
				click(CBR.DOWNLOADBILLINGREPORT);
				assertTrue(CBR.DOWNLOADBILLINGREPORT.isDisplayed());
			}
		} else {
			assertTrue(!(CBR.VIEWALLBILLINGREPORTS).isDisplayed());
		}
	}

	public void SharedServicesFirmAccess(String ClientName, String ClientOrderForm) throws Exception {
		String Filename;
		Robot rob = new Robot();
		EditCPAFirmAccess EPA = PageFactory.initElements(driver, EditCPAFirmAccess.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		Transactions transactions = PageFactory.initElements(driver, Transactions.class);
		File directory = new File(".//");
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String AutoITpath = xAutoIT();
		String browser = Browser;
		Filename = ClientOrderForm;
		click(CP.MANAGECLIENTSMENU);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, CP.SHAREDSERVICESLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CP.SEARCHSSCLIENT, driver);
		type(CP.SEARCHSSCLIENT, ClientName);
		Thread.sleep(5000);
		waitforElement(90, "//tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement edituseraccess = driver.findElement(By.xpath("//tr/td[contains(.,'" + ClientName + "')]/../td/a"));
		JavaScriptclick(driver, edituseraccess);
		waitforElement(180, EPA.CLIENTDETAILSACCOUNTACCESS, driver);
		assertTrue(EPA.CLIENTORDERFORMHEADING.isDisplayed());
		click(CP.UPLOADFILE);
		Thread.sleep(3000);
		assertTrue(CP.ORDER_FORMERROR.isDisplayed());
		JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
		Thread.sleep(2000);
		transactions.AutoItBrowser(driver, browser, AutoITpath, AdjustmentFilePath, "Invalid.xlsx", "");
		Thread.sleep(2000);
		assertTrue(CP.FILEFORMATERROR.isDisplayed());
		JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
		Thread.sleep(2000);
		transactions.AutoItBrowser(driver, browser, AutoITpath, AdjustmentFilePath, Filename, "");
		Thread.sleep(5000);
		JavaScriptclick(driver, CP.UPLOADFILE);
		Thread.sleep(8000);
		assertTrue(CP.UPLOADSUCCESSMESSAGE.isDisplayed());
		WebElement filename = driver.findElement(By.xpath("//a[contains(.,'" + Filename + "')]"));
		JavaScriptclick(driver, filename);
		Thread.sleep(6000);
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(10000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "RateFiles";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(6000);
			new File(LatestDownloadedFile("RateFiles", true));
		}
	}

	public void CPA_EnrollSharedServices(String ClientOrderForm, String Client, String User_Country, String Address1,
			String City, String State, String zip, String Phone, String Subscription, String Subscription_Options,
			String BillingAddress1, String BillingCity, String BillingState, String Billingzip, String CreditCard,
			String ExpMonth, String ExpYear, String CVN, String FirstName, String LastName) throws Exception {
		String Filename;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		CpaRegistration CPAR = PageFactory.initElements(driver, CpaRegistration.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String AutoITpath = xAutoIT();
		String Billing, BillingZip1, CCND, BillingCity1, parentwindow;
		Filename = ClientOrderForm;
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		click(PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(CPAR.SHAREDSERVICESHEADING.isDisplayed());
		assertTrue(CPAR.SHAREDSERVICESTEXT.isDisplayed());
		parentwindow = driver.getWindowHandle();
		click(CPAR.SHAREDSERVICESBUTTON);
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		BPOCPAClientName = Client;
		System.out.println(BPOCPAClientName);
		Reporter.log(BPOCPAClientName);
		waitforElement(90, CP.BASICCOMPANYNAME, driver);
		type(CP.BASICCOMPANYNAME, BPOCPAClientName);
		System.out.println("BPOClientName:" + BPOCPAClientName);
		waitforElement(90, CP.BASICCOUNTRY, driver);
		selectByValue(CP.BASICCOUNTRY, User_Country);
		type(CP.BASISTREETADD, Address1);
		type(CP.BASICCITY, City);
		selectByValue(CP.BASICSTATE, State);
		click(CP.BASICZIP);
		JavaScripttype(driver, CP.BASICZIP, zip);
		sendkeysTab(CP.BASICZIP);
		Thread.sleep(2000);
		click(CP.BASICPHONE);
		JavaScripttype(driver, CP.BASICPHONE, Phone);
		Thread.sleep(2000);
		sendkeysTab(CP.BASICPHONE);
		Thread.sleep(2000);
		if ((Subscription).equalsIgnoreCase("PROFESSIONAL")) {
			click(CP.PROFESSIONALSUBSCRIPTION);
		}
		if ((Subscription).equalsIgnoreCase("PREMIUM")) {
			click(CP.PREMIUMSUBSCRIPTION);
		}
		// clicking statndard subscription
		if ((Subscription).equalsIgnoreCase("Standard")) {
			click(CP.STANDARD);
		}
		Thread.sleep(2000);
		if ((Subscription_Options).equalsIgnoreCase("RETURNS ONLY")) {
			click(CP.RETONLYRADIO);
		}
		if ((Subscription_Options).equalsIgnoreCase("Tax Calculations")) {
			click(CP.CALCONLY);
		}
		assertTrue(CP.TERMSOFSERVICE.isDisplayed());
		click(CP.SAVEANDCONTINUE);
		Thread.sleep(2000);
		assertTrue(CP.CLIENTORDERFORMERROR.isDisplayed());
		JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
		Thread.sleep(2000);
		transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, Filename, "");
		Thread.sleep(2000);
		click(CP.SAVEANDCONTINUE);
		Thread.sleep(2000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex")) {
			driver.get(Environment + "/registration/billing");
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, CP.ClientPhoneErrorText) || VerifyObject(driver, CP.ZipErrorText)) {
			boolean report = false;
			do {
				Thread.sleep(5000);
				CP.BASICPHONE.sendKeys(Keys.CONTROL + "a");
				CP.BASICPHONE.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				CP.BASICPHONE.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				click(CP.BASICZIP);
				CP.BASICZIP.sendKeys(Keys.CONTROL + "a");
				CP.BASICZIP.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				CP.BASICZIP.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				sendkeysTab(CP.BASICZIP);
				Thread.sleep(2000);
				if (!VerifyObject(driver, CP.ClientPhoneErrorText) || VerifyObject(driver, CP.ZipErrorText)) {
					click(RP.SNDIVTE);
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180, CP.BILLADDRESS1, driver);
		// closing window with out user registration

		Billing = BillingAddress1;
		type(CP.BILLADDRESS1, Billing);
		BillingCity1 = BillingCity;
		type(CP.BILLCITY, BillingCity1);
		Thread.sleep(2000);
		selectByValue(CP.BILLSTATE, BillingState);
		BillingZip1 = Billingzip;
		click(CP.BILLZIP);
		JavaScripttype(driver, CP.BILLZIP, BillingZip1);
		sendkeysTab(CP.BILLZIP);
		Thread.sleep(2000);

		// to scroll window down
		scrollDown(driver);
		Thread.sleep(2000);
		click(CP.CREDITCARDPAYMENT);
		click(CP.CREDITCARD);
		clear(CP.CREDITCARD);
		CCND = CreditCard;
		type(CP.CREDITCARD, CreditCard);
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScripttypespace(driver, CP.CREDITCARD, CCND);
			Thread.sleep(3000);
			sendkeybackSpace(CP.CREDITCARD);
		}
		Thread.sleep(3000);
		sendkeysTab(CP.CREDITCARD);
		selectByValue(CP.EXPMONTH, ExpMonth);
		selectByValue(CP.EXPYEAR, ExpYear);
		type(CP.CVN, CVN);

		if ((FirstName) != "") {
			clear(CP.BILLFIRSTNAME);
			JavaScripttypespace(driver, CP.BILLFIRSTNAME, FirstName);
			Thread.sleep(2000);
			sendkeybackSpace(CP.BILLFIRSTNAME);
		}
		if ((LastName) != "") {
			clear(CP.BILLLASTNAME);
			JavaScripttypespace(driver, CP.BILLLASTNAME, LastName);
			Thread.sleep(2000);
			sendkeybackSpace(CP.BILLLASTNAME);
			sendkeysTab(CP.BILLLASTNAME);
			Thread.sleep(2000);
		}
		click(CP.SUBMIT);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		driver.close();
		driver.switchTo().window(parentwindow);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		click(CP.MANAGECLIENTSMENU);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(CP.SHAREDSERVICESLINK);
		waitforElement(180, CP.SEARCHSSCLIENT, driver);
		type(CP.SEARCHSSCLIENT, BPOCPAClientName);
		Thread.sleep(3000);
		String customernumber = driver.findElement(
				By.xpath("//table/thead/tr/th[@sort-header='customerNumber']/../../../tbody/tr/td/span/a[contains(.,'"
						+ BPOCPAClientName + "')]/../../../td[2]"))
				.getText();
		System.out.println(customernumber);
	}

	public void OrderFormPricingInfo(String ExpectedFile) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		File directory = new File(".//");
		File tempfile = null;
		File tempfile2 = null;
		String DirName, File1, File2;
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		click(PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CP.ENROLLEDPRICINGINFO, driver);
		JavaScriptclick(driver, CP.ENROLLEDPRICINGINFO);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Enrolled";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000); // to get the file from dafault download path
									// to specified path
			tempfile = new File(LatestDownloadedFile("Enrolled", true));
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			tempfile = new File(LatestDownloadedFile("Enrolled", false));
		}

		DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
		tempfile2 = new File(DirName + "\\" + ExpectedFile + ".pdf");
		File1 = tempfile.toString();
		File2 = tempfile2.toString();
		PDFComparison(File1, File2);

		waitforElement(180, CP.SHAREDPRICINGINFO, driver);
		JavaScriptclick(driver, CP.SHAREDPRICINGINFO);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Shared";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000); // to get the file from dafault download path
									// to specified path
			tempfile = new File(LatestDownloadedFile("Shared", true));
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			tempfile = new File(LatestDownloadedFile("Shared", false));
		}
		DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
		tempfile2 = new File(DirName + "\\" + ExpectedFile + ".pdf");
		File1 = tempfile.toString();
		File2 = tempfile2.toString();
		PDFComparison(File1, File2);

	}

	public void CPA_LookUpTaxRates(String varCountry, String varAddress, String varCounty, String varCity,
			String varState, String varZip, String VerifyError, String varRst1JuriType, String varRst1Impo,
			String varRst1Sales, String varRst1Cut, String varRst2Juri, String varRst2JuriType, String varRst2Impo,
			String varRst2Sales, String varRst2Cut, String varRst3Juri, String varRst3JuriType, String varRst3Impo,
			String varRst3Sales, String varRst3Cut, String varRst4Juri, String varRst4JuriType, String varRst4Impo,
			String varRst4Sales, String varRst4Cut) throws Exception {
		LookupTaxRates LTR = PageFactory.initElements(driver, LookupTaxRates.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String SalesReplace = null, UseReplace = null;
		String Zip, strJurisdictionType, strImpositionType, SalesTaxRate, SheetSalesTax, UseTaxRate, SheetUseTax,
				strJurisdiction, ExcelJurisdiction;
		MultipleStates = "";
		click(LTR.RatesJurisdictions);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, LTR.CPA_ZIP, driver);
		waitforElement(60, LTR.CPA_COUNTRYDRPDWN, driver);
		Thread.sleep(5000);
		selectByValue(LTR.CPA_COUNTRYDRPDWN, varCountry);
		Thread.sleep(5000);
		if (varAddress != "") {
			clear(LTR.CPA_STREETADDRESS);
			type(LTR.CPA_STREETADDRESS, varAddress);
		}

		if (varCounty != "") {
			clear(LTR.CPA_COUNTY);
			type(LTR.CPA_COUNTY, varCounty);
		}

		if (varCity != "") {
			clear(LTR.CPA_CITY);
			type(LTR.CPA_CITY, varCity);
		}
		if (varState != "") {
			selectByValue(LTR.CPA_STATE, varState);
		}
		clear(LTR.CPA_ZIP);
		click(LTR.CPA_ZIP);
		Zip = varZip;
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, LTR.CPA_ZIP, Zip);
			Thread.sleep(3000);
		} else {
			JavaScripttype(driver, LTR.CPA_ZIP, varZip);
		}
		// click(LTR.CPA_STREETADDRESS);
		JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
		Thread.sleep(2000);
		if (VerifyObject(driver, LTR.Fieldrequired)) {
			boolean report = false;
			do {
				click(LTR.CPA_ZIP);
				driver.findElement(By.id("requestZip")).sendKeys(Keys.CONTROL + "a");
				driver.findElement(By.id("requestZip")).sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				driver.findElement(By.id("requestZip")).sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				if (!VerifyObject(driver, LTR.Fieldrequired)) {
					JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
					report = true;
				}
			} while (report == false);
		}
		/*
		 * else{ JavaScriptclick(driver, LTR.LOOKUPRATEBUTTON); Thread.sleep(2000); }
		 */
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// Verifying Error Message for Invalid Address
		if (VerifyError != "") {
			assertTrue(VerifyObject(driver, LTR.CPA_INVALIDADDRESSERRMSG));
		} else {
			waitforElement(60, LTR.LOOKUPTABLE, driver);
		}
		// Verifying Jurisdicton Type
		if (varRst1JuriType != "") {
			strJurisdictionType = driver.findElement(By.xpath("//div/table/tbody/tr[2]/td/table/tbody/tr/td[2]/span"))
					.getText();
			assertTrue(varRst1JuriType.equalsIgnoreCase(strJurisdictionType));
		}
		// Verifying Imposition Type
		if (varRst1Impo != "") {
			strImpositionType = driver.findElement(By.xpath("//div/table/tbody/tr[2]/td/table/tbody/tr/td[3]/span"))
					.getText();
			assertTrue(varRst1Impo.equalsIgnoreCase(strImpositionType));
		}
		// Verifying Sales Tax Rate and Buyer Rate
		if ((varRst1Sales != "") && (varRst1Cut != "")) {
			SalesTaxRate = driver.findElement(By.xpath(
					"//div/table/tbody/tr[2]/td/table/tbody/tr/td[4]/span[starts-with(@id,'rate-detail-tax-rate')]"))
					.getText();
			SalesReplace = SalesTaxRate.replaceAll("%", "");
			System.out.println("SalesTax Replace=" + SalesReplace);
			SheetSalesTax = varRst1Sales.replaceAll("%", "").trim();
			System.out.println("Sheet Sales Tax=" + SheetSalesTax);
			assertTrue(SheetSalesTax.equalsIgnoreCase(SalesReplace));

			UseTaxRate = driver.findElement(By.xpath(
					"//div/table/tbody/tr[2]/td/table/tbody/tr/td[5]/span[starts-with(@id,'rate-detail-cut-tax-rate')]"))
					.getText();
			UseReplace = UseTaxRate.replaceAll("%", "");
			System.out.println("UseTax Replace=" + UseReplace);
			SheetUseTax = varRst1Cut.replaceAll("%", "").trim();
			System.out.println("SheetUseTax=" + SheetUseTax);
			assertTrue(SheetUseTax.equalsIgnoreCase(UseReplace));

		}
		// Verifying 2nd Jurisdiction State
		if (varRst2Juri != "") {
			strJurisdiction = driver.findElement(By.xpath("//div/table/tbody/tr[4]/td/table/tbody/tr/td[1]/span"))
					.getText();
			ExcelJurisdiction = varRst2Juri;
			System.out.println(" app jur " + strJurisdiction);
			System.out.println(" excl jur " + ExcelJurisdiction);
			assertTrue(strJurisdiction.equalsIgnoreCase(ExcelJurisdiction));

			if (varRst2JuriType != "") {
				strJurisdictionType = driver
						.findElement(By.xpath("//div/table/tbody/tr[4]/td/table/tbody/tr/td[2]/span")).getText();
				assertTrue(varRst2JuriType.equalsIgnoreCase(strJurisdictionType));
			}

			if (varRst2Impo != "") {
				strImpositionType = driver.findElement(By.xpath("//div/table/tbody/tr[4]/td/table/tbody/tr/td[3]/span"))
						.getText();
				assertTrue(varRst2Impo.equalsIgnoreCase(strImpositionType));
			}

			if ((varRst2Sales != "")) {
				SalesTaxRate = driver.findElement(By.xpath(
						"//div/table/tbody/tr[4]/td/table/tbody/tr/td[4]/span[starts-with(@id,'rate-detail-tax-rate')]"))
						.getText();
				SalesReplace = SalesTaxRate.replaceAll("%", "");
				System.out.println("SalesTax Replace=" + SalesReplace);
				SheetSalesTax = varRst2Sales.replaceAll("%", "").trim();
				System.out.println("Sheet Sales Tax=" + SheetSalesTax);
				assertTrue(SheetSalesTax.equalsIgnoreCase(SalesReplace));
			}

			if ((varRst2Cut != "")) {
				UseTaxRate = driver.findElement(By.xpath(
						"//div/table/tbody/tr[4]/td/table/tbody/tr/td[5]/span[starts-with(@id,'rate-detail-cut-tax-rate')]"))
						.getText();
				UseReplace = UseTaxRate.replaceAll("%", "");
				System.out.println("UseTax Replace=" + UseReplace);
				SheetUseTax = varRst2Cut.replaceAll("%", "").trim();
				System.out.println("SheetUseTax=" + SheetUseTax);
				assertTrue(SheetUseTax.equalsIgnoreCase(UseReplace));

			}

			// Verifying 3rd Jurisdiction
			if (varRst3Juri != "") {
				strJurisdiction = driver.findElement(By.xpath("//div/table/tbody/tr[6]/td/table/tbody/tr/td[1]/span"))
						.getText();
				ExcelJurisdiction = varRst3Juri;
				System.out.println(" app jur " + strJurisdiction);
				System.out.println(" excl jur " + ExcelJurisdiction);
				assertTrue(strJurisdiction.equalsIgnoreCase(ExcelJurisdiction));
			}

			if (varRst3JuriType != "") {
				strJurisdictionType = driver
						.findElement(By.xpath("//div/table/tbody/tr[6]/td/table/tbody/tr/td[2]/span")).getText();
				assertTrue(varRst3JuriType.equalsIgnoreCase(strJurisdictionType));
			}

			if (varRst3Impo != "") {
				strImpositionType = driver.findElement(By.xpath("//div/table/tbody/tr[4]/td/table/tbody/tr/td[3]/span"))
						.getText();
				assertTrue(varRst3Impo.equalsIgnoreCase(strImpositionType));
			}

			if ((varRst3Sales != "")) {
				SalesTaxRate = driver.findElement(By.xpath(
						"//div/table/tbody/tr[6]/td/table/tbody/tr/td[4]/span[starts-with(@id,'rate-detail-tax-rate')]"))
						.getText();
				SalesReplace = SalesTaxRate.replaceAll("%", "");
				System.out.println("SalesTax Replace=" + SalesReplace);
				SheetSalesTax = varRst3Sales.replaceAll("%", "").trim();
				System.out.println("Sheet Sales Tax=" + SheetSalesTax);
				assertTrue(SheetSalesTax.equalsIgnoreCase(SalesReplace));
			}

			if ((varRst3Cut != "")) {
				UseTaxRate = driver.findElement(By.xpath(
						"//div/table/tbody/tr[6]/td/table/tbody/tr/td[5]/span[starts-with(@id,'rate-detail-cut-tax-rate')]"))
						.getText();
				UseReplace = UseTaxRate.replaceAll("%", "");
				System.out.println("UseTax Replace=" + UseReplace);
				SheetUseTax = varRst3Cut.replaceAll("%", "").trim();
				System.out.println("SheetUseTax=" + SheetUseTax);
				assertTrue(SheetUseTax.equalsIgnoreCase(UseReplace));
			}

			// Verifying 4th Jurisdiction State
			if (varRst4Juri != "") {
				strJurisdiction = driver.findElement(By.xpath("//div/table/tbody/tr[8]/td/table/tbody/tr/td[1]/span"))
						.getText();
				ExcelJurisdiction = varRst4Juri;
				System.out.println(" app jur " + strJurisdiction);
				System.out.println(" excl jur " + ExcelJurisdiction);
				assertTrue(strJurisdiction.equalsIgnoreCase(ExcelJurisdiction));
			}
			if (varRst4JuriType != "") {
				strJurisdictionType = driver
						.findElement(By.xpath("//div/table/tbody/tr[8]/td/table/tbody/tr/td[2]/span")).getText();
				assertTrue(varRst4JuriType.equalsIgnoreCase(strJurisdictionType));
			}

			if (varRst4Impo != "") {
				strImpositionType = driver.findElement(By.xpath("//div/table/tbody/tr[8]/td/table/tbody/tr/td[3]/span"))
						.getText();
				assertTrue(varRst4Impo.equalsIgnoreCase(strImpositionType));
			}

			if ((varRst4Sales != "")) {
				SalesTaxRate = driver.findElement(By.xpath(
						"//div/table/tbody/tr[8]/td/table/tbody/tr/td[4]/span[starts-with(@id,'rate-detail-tax-rate')]"))
						.getText();
				SalesReplace = SalesTaxRate.replaceAll("%", "");
				System.out.println("SalesTax Replace=" + SalesReplace);
				SheetSalesTax = varRst4Sales.replaceAll("%", "").trim();
				System.out.println("SheetSalesTax=" + SheetSalesTax);
				assertTrue(SheetSalesTax.equalsIgnoreCase(SalesReplace));
			}

			if ((varRst4Cut != "")) {
				UseTaxRate = driver.findElement(By.xpath(
						"//div/table/tbody/tr[8]/td/table/tbody/tr/td[5]/span[starts-with(@id,'rate-detail-cut-tax-rate')]"))
						.getText();
				UseReplace = UseTaxRate.replaceAll("%", "");
				System.out.println("UseTax Replace=" + UseReplace);
				SheetUseTax = varRst4Cut.replaceAll("%", "").trim();
				System.out.println("SheetUseTax=" + SheetUseTax);
				assertTrue(SheetUseTax.equalsIgnoreCase(UseReplace));
			}

			// *************************************
			if (VerifyObject(driver, LTR.CLEARRESULTS)) {
				click(LTR.CLEARRESULTS);
			}

		}
		// stateName = CloudData.get_data("varState");
		// MultipleStates = MultipleStates + stateName + ";";
	}

	public static String fileUploadDialog(WebDriver driver, String browser, String AutoITpath,
			String AdjustmentFilePath, String Filename, String fileformat) throws Exception {
		String status = "";
		try {
			switch (browser) {
			case "IE":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			case "Firefox":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\"
							+ Filename + fileformat);
				}
				break;
			case "Chrome":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			case "Safari":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

}
