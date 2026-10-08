package common.libraries.cpa;

import java.awt.Robot;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.CPA;
import common.pages.ConfigurePage;
import common.pages.Cpa_ManageUsers;
import common.pages.EditCPAFirmAccess;
import common.pages.ProfessionalUserLinks;
import common.pages.RegistrationPage;

public class CPAManaged extends CommonFunctions {

	private WebDriver driver;

	public CPAManaged(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void CPA_EnrollNewClient(String Role, String clienttype, String clientname, String country, String Address1,
			String state, String city, String zipcode, String Phone, String Subscription, String feature,
			String addintionalfeature, String Filename, String BillingAddress1, String BillingCity, String BillingState,
			String Billingzip, String PaymentMethod, String CreditCard, String ExpMonth, String ExpYear, String CVN,
			String BankNumber, String RoutingNumber, String Firstname, String Lastname) throws Exception {
		CPA CP = PageFactory.initElements(driver, CPA.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String CCND, parentwindow;
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String AutoITpath = xAutoIT();
		MultipleBPOClients = "";
		click(CP.MANAGECLIENTSMENU);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CP.ENROLLNEWCLIENT, driver);
		if (clienttype != "") {
			click(CP.SHAREDSERVICESLINK);
			waitforElement(180, CP.ENROLLSHAREDSERVICES, driver);
			click(CP.ENROLLSHAREDSERVICES);
		} else {
			click(CP.ENROLLNEWCLIENT);
		}
		Thread.sleep(10000);
		parentwindow = driver.getWindowHandle();
		Thread.sleep(2000);
		Set<String> window = driver.getWindowHandles();
		window.remove(parentwindow);
		driver.switchTo().window(window.iterator().next());
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (clientname != "") {
			BPOCPAClientName = clientname;
			waitforElement(90, CP.BASICCOMPANYNAME, driver);
			type(CP.BASICCOMPANYNAME, BPOCPAClientName);
			System.out.println("BPOClientName:" + BPOCPAClientName);
		}
		waitforElement(90, CP.BASICCOUNTRY, driver);
		selectByValue(CP.BASICCOUNTRY, country);
		type(CP.BASISTREETADD, Address1);
		type(CP.BASICCITY, city);
		selectByValue(CP.BASICSTATE, state);
		click(CP.BASICZIP);
		JavaScripttype(driver, CP.BASICZIP, zipcode);
		sendkeysTab(CP.BASICZIP);
		Thread.sleep(2000);
		click(CP.BASICPHONE);
		JavaScripttype(driver, CP.BASICPHONE, Phone);
		Thread.sleep(2000);
		sendkeysTab(CP.BASICPHONE);
		Thread.sleep(2000);
		if (Subscription.equalsIgnoreCase("Professional")) {
			click(CP.PROFESSIONALSUBSCRIPTION);
		}
		if (Subscription.equalsIgnoreCase("Premium")) {
			click(CP.PREMIUMSUBSCRIPTION);
		}
		if (Subscription.equalsIgnoreCase("Standard")) {
			click(CP.STANDARD);
		}
		Thread.sleep(2000);
		if (feature.equalsIgnoreCase("RETURNS ONLY")) {
			click(CP.RETONLYRADIO);
		}
		if (feature.equalsIgnoreCase("Tax Calculations")) {
			click(CP.CALCONLY);
		}
		if (feature.equalsIgnoreCase("TaxabilityImpactReport")) {
			click(CP.TAXIMPACTREPORT);
		}
		{
			if (addintionalfeature.equalsIgnoreCase("RealTimeRateService")) {
				click(CP.REALTIMERATESERV);
			} else if (addintionalfeature.equalsIgnoreCase("RateFile+RealTimeRateService")) {
				click(CP.RATEFILECHECK);
				Thread.sleep(1000);
				JavaScriptclick(driver, CP.REALTIMERATESERV);
			}
			click(CP.SAVEANDCONTINUE);
			Thread.sleep(2000);
			JavaScriptclick(driver, CP.CLIENTFORMUPLOAD);
			Thread.sleep(2000);
			transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, "Invalid", ".xlsx");
			Thread.sleep(2000);
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
			type(CP.BILLADDRESS1, BillingAddress1);
			type(CP.BILLCITY, BillingCity);
			Thread.sleep(2000);
			selectByValue(CP.BILLSTATE, BillingState);
			click(CP.BILLZIP);
			JavaScripttype(driver, CP.BILLZIP, Billingzip);
			sendkeysTab(CP.BILLZIP);
			Thread.sleep(2000);
			if (PaymentMethod.equalsIgnoreCase("Creditcard")) {
				// to scroll window down
				scrollDown(driver);
				Thread.sleep(2000);
				click(CP.CREDITCARDPAYMENT);
				click(CP.CREDITCARD);
				clear(CP.CREDITCARD);
				CCND = CreditCard;
				type(CP.CREDITCARD, CCND);
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
			} else {
				// ********* Registering Billing ACH user *********************
				click(CP.ACH);
				Thread.sleep(5000);
				clear(CP.BANKNUMBER);
				clear(CP.ROUTINGNUMBER);
				type(CP.BANKNUMBER, BankNumber);
				type(CP.ROUTINGNUMBER, RoutingNumber);
			}
			clear(CP.BILLFIRSTNAME);
			JavaScripttypespace(driver, CP.BILLFIRSTNAME, Firstname);
			Thread.sleep(2000);
			sendkeybackSpace(CP.BILLFIRSTNAME);
			clear(CP.BILLLASTNAME);
			JavaScripttypespace(driver, CP.BILLLASTNAME, Lastname);
			Thread.sleep(2000);
			sendkeybackSpace(CP.BILLLASTNAME);
			sendkeysTab(CP.BILLLASTNAME);
			Thread.sleep(2000);
			click(CP.SUBMIT);
			waitforElement(180, PUL.DASHBOARDLINK, driver);
			driver.close();
			Thread.sleep(5000);
			driver.switchTo().window(parentwindow);
			Thread.sleep(5000);
		}
		MultipleBPOClients = MultipleBPOClients + BPOCPAClientName + ";";
	}

	public void CPA_VerifyManageClients(String CPAClientname, String clienttype, String ClientType) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		String parentwindow1;
		click(CP.MANAGECLIENTSMENU);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(Environment);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CP.SEARCHBYCLIENT, driver);
		BPOCPAClientName = CPAClientname;
		if (ClientType.equalsIgnoreCase("Enrolled")) {
			click(CP.ENROLLEDCLIENTS);
			waitforElement(180, CP.SEARCHBYCLIENT, driver);
			type(CP.SEARCHBYCLIENT, BPOCPAClientName);
			Thread.sleep(2000);
		} else {
			click(CP.SHAREDSERVICESLINK);
			Thread.sleep(2000);
			type(CP.SEARCHSSCLIENT, BPOCPAClientName);
			Thread.sleep(2000);
		}
		Thread.sleep(5000);
		waitforElement(90,
				"//tr/td/span/a[contains(.,'" + BPOCPAClientName + "')]/../../../td/a[text()='Edit User Access']",
				driver);
		WebElement element = driver.findElement(By.xpath("//tr/td/span/a[contains(.,'" + BPOCPAClientName + "')]"));
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
		Thread.sleep(30000);
		driver.close();
		Thread.sleep(5000);
		driver.switchTo().window(parentwindow1);
		Thread.sleep(5000);
	}

	public void MerchantRegistration(String Role, String clientname, String Country, String Address1, String City,
			String State, String zip, String Firstname, String LastName, String Email, String Phone) throws Exception {
		CPA CP = PageFactory.initElements(driver, CPA.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Cpa_ManageUsers NU = PageFactory.initElements(driver, Cpa_ManageUsers.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String parentwindow = driver.getWindowHandle();
		MultipleMerchantClients = "";
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		click(PUL.ManageMerchants);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(PUL.ENROLLBUTTON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		MerchantClientName = clientname;
		waitforElement(180, CP.BASICCOMPANYNAME, driver);
		Thread.sleep(5000);
		Actions a = new Actions(driver);
		a.click(CP.BASICCOMPANYNAME).build().perform();
		JavaScriptclick(driver, CP.BASICCOMPANYNAME);
		type(CP.BASICCOMPANYNAME, MerchantClientName);
		selectByValue(CP.BASICCOUNTRY, Country);
		type(CP.BASISTREETADD, Address1);
		type(CP.BASICCITY, City);
		selectByValue(CP.BASICSTATE, State);
		click(CP.BASICZIP);
		JavaScripttype(driver, CP.BASICZIP, zip);
		selectByValue(CP.BASICCOUNTRY, Country);
		sendkeysTab(CP.BASICZIP);
		Thread.sleep(5000);
		clear(CP.MerchantFIRSTNAME);
		JavaScripttypespace(driver, CP.MerchantFIRSTNAME, Firstname);
		Thread.sleep(2000);
		sendkeybackSpace(CP.MerchantFIRSTNAME);
		clear(CP.MerchantLastNAME);
		JavaScripttypespace(driver, CP.MerchantLastNAME, LastName);
		Thread.sleep(2000);
		sendkeybackSpace(CP.MerchantLastNAME);
		sendkeysTab(CP.MerchantLastNAME);
		Thread.sleep(2000);
		click(CP.MerchantMail);
		type(CP.MerchantMail, Email);
		sendkeysTab(CP.MerchantMail);
		Thread.sleep(5000);
		click(CP.MerchantPHONE);
		JavaScripttype(driver, CP.MerchantPHONE, Phone);
		sendkeysTab(CP.MerchantPHONE);
		Thread.sleep(5000);
		JavaScriptclick(driver, CP.SUBMIT);
		if (VerifyObject(driver, CP.PhoneErrorText) || VerifyObject(driver, CP.ZipCodeErrorText)
				|| VerifyObject(driver, CP.EmailText)) {
			boolean report = false;
			do {
				if (VerifyObject(driver, CP.PhoneErrorText)) {
					Thread.sleep(5000);
					click(CP.MerchantPHONE);
					CP.MerchantPHONE.sendKeys(Keys.CONTROL + "a");
					CP.MerchantPHONE.sendKeys(Keys.CONTROL + "x");
					Thread.sleep(3000);
					sendkeysTab(CP.MerchantPHONE);
					Thread.sleep(2000);
					click(CP.MerchantPHONE);
					CP.MerchantPHONE.sendKeys(Keys.CONTROL + "v");
					Thread.sleep(2000);
					sendkeysTab(CP.MerchantPHONE);
					Thread.sleep(2000);
				}
				if (VerifyObject(driver, CP.ZipCodeErrorText)) {
					Thread.sleep(5000);
					click(CP.BASICZIP);
					CP.BASICZIP.sendKeys(Keys.CONTROL + "a");
					CP.BASICZIP.sendKeys(Keys.CONTROL + "x");
					Thread.sleep(3000);
					sendkeysTab(CP.BASICZIP);
					Thread.sleep(2000);
					click(CP.BASICZIP);
					CP.BASICZIP.sendKeys(Keys.CONTROL + "v");
					Thread.sleep(2000);
					sendkeysTab(CP.BASICZIP);
					Thread.sleep(2000);
				}
				if (VerifyObject(driver, CP.EmailText)) {
					Thread.sleep(5000);
					click(CP.MerchantMail);
					CP.MerchantMail.sendKeys(Keys.CONTROL + "a");
					CP.MerchantMail.sendKeys(Keys.CONTROL + "x");
					Thread.sleep(3000);
					sendkeysTab(CP.MerchantMail);
					Thread.sleep(2000);
					click(CP.MerchantMail);
					CP.MerchantMail.sendKeys(Keys.CONTROL + "v");
					Thread.sleep(2000);
					sendkeysTab(CP.MerchantMail);
					Thread.sleep(3000);
				}
				if (!VerifyObject(driver, CP.PhoneErrorText) || VerifyObject(driver, CP.ZipCodeErrorText)
						|| VerifyObject(driver, CP.EmailText)) {
					click(CP.SUBMIT);
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180, NU.COMPLETEREGISTRATION, driver);
		MultipleMerchantClients = MultipleMerchantClients + MerchantClientName + ";";
		System.out.println("Multple Merchant clients:" + MultipleMerchantClients);
	}

	public void EditCPAFirmAccess(String No_Access, String Reports_Access, String Read_Write, String Password)
			throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		new Robot();
		EditCPAFirmAccess EPA = PageFactory.initElements(driver, EditCPAFirmAccess.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, EPA.SettingsHover, driver);
		JavaScriptMousehover(driver, EPA.SettingsHover);
		JavaScriptclick(driver, EPA.EDITCPAFIRMLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (No_Access.equalsIgnoreCase("Yes")) {
			//assertTrue(driver.findElement(By.xpath("//input[@name='reportType' and @value='1']")).isSelected());
			click(EPA.NOACCESSREPORT);
		} else if (Reports_Access.equalsIgnoreCase("Yes")) {
			click(EPA.REPORTONLYACCESS);
		} else if (Read_Write.equalsIgnoreCase("Yes")) {
			click(EPA.READWRITEACCESSREPORT);
		}
		click(EPA.SERVICEAGG);
		Thread.sleep(1000);
		click(EPA.SAVEBTN);
		waitforElement(60, EPA.CPAFIRMACCESSPASSWORD, driver);
		clear(EPA.CPAFIRMACCESSPASSWORD);
		type(EPA.CPAFIRMACCESSPASSWORD, Password);
		Thread.sleep(2000);
		click(EPA.AUTHORIZEBTN);
		waitforText(180, EPA.PASSWORDUPDATED, driver);
		Thread.sleep(5000);
		assertTrue(EPA.PASSWORDUPDATED.isDisplayed());
		click(CNP.Dashboard);
		Thread.sleep(5000);
	}

}
