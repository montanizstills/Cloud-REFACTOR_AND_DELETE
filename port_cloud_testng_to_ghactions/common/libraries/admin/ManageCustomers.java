package common.libraries.admin;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import au.com.bytecode.opencsv.CSVReader;
import common.libraries.customer.PortalFunctions;
import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.pages.AdminConfigLimit;
import common.pages.AdminUndoAdjustmentFile;
import common.pages.AdminUserLinks;
import common.pages.CPA;
import common.pages.ConfigurationReportPage;
import common.pages.ConfigurePage;
import common.pages.DashboardPage;
import common.pages.DowngradePremiumtoPro;
import common.pages.FreeUserLinks;
import common.pages.InviteUsers;
import common.pages.LoginPage;
import common.pages.ManageConfiguration;
import common.pages.ManagetaxReturns;
import common.pages.Metrics;
import common.pages.MySubscriptionPage;
import common.pages.ProfessionalUserLinks;
import common.pages.RegistrationPage;
import common.pages.SOCReport;
import common.pages.TaxpayerPartitionsused;
import common.pages.TestInstanceSandbox;
import common.pages.Treasurybillingscreens;
import common.pages.UpgradeProToPremium;
import common.pages.VertexSMBCustomers;
import common.utilities.CloudData;
import common.utilities.UtilityScript;

public class ManageCustomers extends CommonFunctions {

	private WebDriver driver;
	PortalFunctions DownloadFiles = PageFactory.initElements(driver, PortalFunctions.class);
	UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);

	public ManageCustomers(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void GenerateAPIKey() throws Exception {
		if (configstatus == "ConfigFail") {
			// status="ConfigFail";
		} else {
			xGetCurrentTime();
			AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
			waitforElement(60, AUA.manageSMBCustomers, driver);
			click(AUA.manageSMBCustomers);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, AUA.smbCustomerName, driver);
			type(AUA.smbCustomerName, VertexClientName);
			Thread.sleep(5000);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + VertexClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ VertexClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ VertexClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ VertexClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			} else {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ VertexClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, AUA.CONNECTORS, driver);
			JavaScriptclick(driver, AUA.CONNECTORS);
			waitUntilElementInvisibe(360, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
			if (VerifyObject(driver, AUA.generateAPICredentialslnk)) {
				JavaScriptclick(driver, AUA.generateAPICredentialslnk);
			} else {
				JavaScriptclick(driver, AUA.resetAPIcredentials);
			}
			if (VerifyObject(driver, AUA.generateAPICredentialslnk)) {
				JavaScriptclick(driver, AUA.generateAPICredentialslnk);
			} else {
				JavaScriptclick(driver, AUA.resetAPIcredentials);
			}
			Thread.sleep(10000);
			String APIK = getText(AUA.apiKey);
			APIkey = APIK;
			String APIP = getText(AUA.apiPassword);
			APIPassword = APIP;
			System.out.println(APIkey);
			System.out.println(APIPassword);
			assertTrue(APIkey != null);
		}
	}

	public void TaxpayerPartitionsused(String Varname, String Customername, String PartitionValue) throws Exception {
		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String customername;
		String varName;
		waitforElement(120, TPU.SMBCUSTOMERSTAB, driver);
		click(TPU.SMBCUSTOMERSTAB);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);
		if (Varname != "") {
			varName = Varname;
		} else {
			varName = VertexClientName;
		}
		customername = Customername;

		if (customername != "") {
			if (!VertexClientName.contains("TI")) {
				customername = VertexClientName;
				type(TPU.SMBCUSTOMERNAME, customername);
			} else {
				customername = VertexClientName.split("\\.")[0];
				type(TPU.SMBCUSTOMERNAME, customername);
			}
		}
		Thread.sleep(4000);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + customername + "')]", driver);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + customername
				+ "')]/../../../td[contains(text(),'" + varName + "')]")) {
			WebElement Registered = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + customername
							+ "')]/../../../td[contains(text(),'" + varName + "')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		}

		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERUSERSDELETE, driver);
		click(TPU.EDITTAXPAYERCAP);
		waitforElement(180, TPU.PARTITIONSPOPUPSELECT, driver);
		click(TPU.EDITTAXPAYERCAP);
		waitforElement(180, TPU.PARTITIONSPOPUPSELECT, driver);
		selectByValue(TPU.PARTITIONSPOPUPSELECT, PartitionValue);
		click(TPU.SAVE);
		Thread.sleep(4000);
		waitforElement(60, TPU.TaxpayerPartitionConfigurationStatus, driver);

	}

	public void VerifyNocreatenewuser(String ClientName) throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]",
				driver);
		Thread.sleep(2000);
		String CName = driver.findElement(By.xpath("//table/tbody/tr/td[1]/div/a")).getText();
		System.out.println(CName);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement Registered = driver.findElement(
					By.xpath("//table/tbody/tr[2]/td/div/a[contains(text(),'" + ClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement Authorized = driver.findElement(
					By.xpath("//table/tbody/tr[2]/td/div/a[contains(text(),'" + ClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else {
			WebElement Intilized = driver.findElement(
					By.xpath("//table/tbody/tr[2]/td/div/a[contains(text(),'" + ClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Intilized);
		}
		String Clientstatus = driver.findElement(By.xpath("//table/tbody/tr/td[6]")).getText();
		if (Clientstatus.equalsIgnoreCase("Initialized") || Clientstatus.equalsIgnoreCase("PendingBilling")) {
			Boolean va = !(VerifyObject(driver, VSC.CREATENEWUSER));
			System.out.println("Fail" + va);
		}

	}

	public void GenerateAPIKey(String ClientName) throws Exception {
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		waitforElement(60, AUA.manageSMBCustomers, driver);
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, AUA.smbCustomerName, driver);
		type(AUA.smbCustomerName, ClientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]", driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
			// ClientName +
			// "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
			// ClientName +
			// "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
		} else {
			// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
			// ClientName +
			// "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
		}
		waitforElement(180, AUA.CONNECTORS, driver);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, AUA.CONNECTORS, driver);
		JavaScriptclick(driver, AUA.CONNECTORS);
		waitUntilElementInvisibe(360, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, AUA.generateAPICredentialslnk)) {
			JavaScriptclick(driver, AUA.generateAPICredentialslnk);
		} else {
			JavaScriptclick(driver, AUA.resetAPIcredentials);
		}
		if (VerifyObject(driver, AUA.generateAPICredentialslnk)) {
			JavaScriptclick(driver, AUA.generateAPICredentialslnk);
		} else {
			JavaScriptclick(driver, AUA.resetAPIcredentials);
		}
		Thread.sleep(10000);
		String APIK = getText(AUA.apiKey);
		APIkey = APIK;
		String APIP = getText(AUA.apiPassword);
		APIPassword = APIP;
		System.out.println("APIkey :" + APIkey);
		System.out.println("API Password :" + APIPassword);
		assertTrue(APIkey != null);
	}

	public void oauthCredentials(String ClientName, String Clientid) throws Exception {
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		waitforElement(60, AUA.OAUTHCREDENTIALMGMT, driver);
		click(AUA.OAUTHCREDENTIALMGMT);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, AUA.PUBLICAPI, driver);
		click(AUA.PUBLICAPI);
		Thread.sleep(10000);
		type(AUA.PUBAPIFROM, ClientName);
		type(AUA.CLIENTID, Clientid);
		Thread.sleep(2000);
		click(AUA.CREATE);
		waitforElement(180, AUA.CLIENTSECRET, driver);
		String clientsecret = getText(AUA.CLIENTSECRET);
		String secret[] = clientsecret.split(":");
		ClientSecret = secret[1].trim();
		ClientID = Clientid;
		click(AUA.OK);
	}

	public void IncreasePartitions(String ClientName, String PartitionValue) throws Exception {

		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(120, TPU.SMBCUSTOMERSTAB, driver);
		click(TPU.SMBCUSTOMERSTAB);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);
		type(TPU.SMBCUSTOMERNAME, ClientName);
		Thread.sleep(5000);
		waitforElement(60, "//a[contains(.,'" + ClientName + "')]", driver);
		WebElement element = driver.findElement(By.xpath("//a[contains(.,'" + ClientName + "')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
		waitUntilElementInvisibe(80, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(80, TPU.SMBCUSTOMERUSERSDELETE, driver);
		click(TPU.EDITTAXPAYERCAP);
		waitforElement(180, TPU.PARTITIONSPOPUPSELECT, driver);
		selectByValue(TPU.PARTITIONSPOPUPSELECT, PartitionValue);
		Thread.sleep(2000);
		click(TPU.SAVE);
		Thread.sleep(4000);
	}

	public void ChangeSubscription(String clientname, String subscription, String AddtionalFeature, String DataRegion)
			throws Exception {

		DowngradePremiumtoPro DPP = PageFactory.initElements(driver, DowngradePremiumtoPro.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);

		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		Thread.sleep(2000);
		waitforElement(90, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, clientname);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a")).getText();
		assertTrue((clientname).equalsIgnoreCase(CName));
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
				+ "')]/../../../td[contains(.,'Registered')]")) {
			WebElement Registered = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
				+ "')]/../../../td[contains(.,'Authorized')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
				+ "')]/../../../td[contains(.,'Cancelled')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		}
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.EXPORTC, driver);
		CNTID = driver.findElement(By.xpath("//div[@class='span3 ng-binding'][contains(.,'Cloud Customer Id:')]"))
				.getText().split(":")[1].trim();
		System.out.println(CNTID);
		JavaScriptclick(driver, VSC.SMBADMIN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		waitforElement(90, VSC.EDITCUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.EDITCUSTOMERSUBSCRIPTION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.CUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.CUSTOMERSUBSCRIPTION);
		waitforElement(90, MSP.SAVEBUTTON, driver);
		waitforElement(90, DPP.PROFESSIONAL, driver);
		waitforElement(90, MSP.PREMIUMSUB, driver);

		if (subscription != "") {
			if (subscription.equalsIgnoreCase("Professional")) {
				waitforElement(180, DPP.PROFESSIONAL, driver);
				click(DPP.PROFESSIONAL);
			}
		}
		if (subscription != "") {
			if (subscription.equalsIgnoreCase("Premium")) {
				waitforElement(180, MSP.PREMIUMSUB, driver);
				click(MSP.PREMIUMSUB);
			}
		}

		String[] feature = AddtionalFeature.split(",");
		if (!AddtionalFeature.isEmpty()) {
			for (int i = 0; i < feature.length; i++) {
				switch (feature[i].toUpperCase()) {
				case "TAX CALCULATIONS":
					click(MSP.TAXCALCONLY_SUB);
					break;
				case "TAX CALCULATIONS AND RETURNS":
					click(MSP.TAXCALCRETURNS_SUB);
					break;
				case "RETURNS ONLY":
					click(MSP.RETURNSONLY_SUB);
					break;
				case "REAL-TIME RATE SERVICE":
					click(MSP.REALTIME);
					break;
				case "RATE FILE":
					click(MSP.RATEFILECHECKBOX);
					break;
				case "TAXABILITY IMPACT REPORT":
					click(MSP.TAXIMPACTREPORT_SUB);
					break;
				case "RETAIL TAX EXTRACT":
					click(MSP.RETAILTAXEXTRACT);
					break;
				case "GLOBAL COMPLIANCE":
					click(MSP.GLOBALCOMPLIANCE);
					break;
				case "TEST INSTANCE":
					click(MSP.testInstanceradioBtn);
					break;
				case "DATA INTEGRITY":
					click(MSP.DATAINTEGRITY);
					Thread.sleep(1000);
					selectByValue(MSP.DATAINTEGRITYREGION, DataRegion);
					break;
				case "LEGACY TRANSACTION REPORT":
					click(RP.LEGACYTRANSACTIONREPORT);
					break;
				}
			}
		}
		Thread.sleep(4000);
		click(MSP.SAVEBUTTON);
		waitforElement(60, MSP.ACCEPT, driver);
		Thread.sleep(2000);
		click(MSP.ACCEPT);
		Thread.sleep(2000);
	}

	public void AdminManageOutofSalesReturnsUpdate(String ReturnNames_Form) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ManageConfiguration MC = PageFactory.initElements(driver, ManageConfiguration.class);
		click(MC.MANAGECONFIGLINK);
		waitforElement(180, MC.RETURNNAMEDRPDN, driver);
		// Manage Form Display Verification
		JavaScriptclick(driver, MC.FORMDISPLAY);
		if (ReturnNames_Form != "") {
			String RtrnName_Form = ReturnNames_Form;
			JavaScriptclick(driver, MC.RETURNNAMEDRPDN);
			waitforElement(180, MC.ReturnNameInput, driver);
			type(MC.ReturnNameInput, RtrnName_Form);
			Thread.sleep(1000);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
			if (!VerifyObject(driver, MC.Billingapprovetreasurywelcome)) {
				if (!MC.OutofSaleschkbox.isSelected()) {
					click(MC.OutofSaleschkbox);
					Thread.sleep(3000);
					assertTrue(!MC.OutofSaleschkbox.isSelected());
					JavaScriptclick(driver, MC.UPDATEACTION);
					Thread.sleep(3000);
				}
			} else {
				assertTrue(MC.OutofSaleschkbox.isSelected());
			}
		}
	}

	public void TaxpayerPartitionsused(String clientname, String VarType, String ClientType, String Franchise,
			String No_Taxpayer, String Single_TaxPayer, String Double_TaxPayer, String Three_TaxPayer,
			String PartitionSelect, String PartitionValue) throws Exception {
		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(120, TPU.SMBCUSTOMERSTAB, driver);
		click(TPU.SMBCUSTOMERSTAB);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);

		if (ClientType != "") {
			if (Franchise.equalsIgnoreCase("Yes")) {
				type(TPU.SMBCUSTOMERNAME, clientname);
				Thread.sleep(4000);
				waitforElement(180,
						"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname + "')]", driver);
				if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname
						+ "')]/../../../td[contains(text(),'" + VarType + "')]")) {
					WebElement Merchant = driver.findElement(
							By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname
									+ "')]/../../../td[contains(text(),'" + VarType + "')]/../td[1]/div/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Merchant);

				}
			} else {
				type(TPU.SMBCUSTOMERNAME, clientname);
				Thread.sleep(4000);
				waitforElement(180,
						"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname + "')]", driver);
				if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname
						+ "')]/../../../td[contains(text(),'" + VarType + "')]")) {
					WebElement Registered = driver.findElement(
							By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname
									+ "')]/../../../td[contains(text(),'" + VarType + "')]/../td[1]/div/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
				}
			}
		} else {
			if (!clientname.contains("TI")) {
				type(TPU.SMBCUSTOMERNAME, clientname);
			} else {
				clientname = clientname.split("\\.")[0];
				type(TPU.SMBCUSTOMERNAME, clientname);
			}

			Thread.sleep(4000);
			waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname + "')]",
					driver);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
					+ "')]/../../../td[contains(text(),'" + VarType + "')]")) {
				WebElement authorized = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
								+ "')]/../../../td[contains(text(),'" + VarType + "')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", authorized);
			}
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.PARTITIONSUSEDCOLUMN, driver);
		assertTrue(TPU.PARTITIONSUSEDCOLUMN.isDisplayed());
		if (No_Taxpayer != "") {
			assertTrue(
					driver.findElement(By.xpath("//div[@class='span3 ng-binding'][contains(.,'" + No_Taxpayer + "')]"))
							.getText().split(":")[1].trim().equalsIgnoreCase(No_Taxpayer));
		}
		if (Single_TaxPayer != "") {
			assertTrue(driver
					.findElement(By.xpath("//div[@class='span3 ng-binding'][contains(.,'" + Single_TaxPayer + "')]"))
					.getText().split(":")[1].trim().equalsIgnoreCase(Single_TaxPayer));
		}
		if (Double_TaxPayer != "") {
			assertTrue(driver
					.findElement(By.xpath("//div[@class='span3 ng-binding'][contains(.,'" + Double_TaxPayer + "')]"))
					.getText().split(":")[1].trim().equalsIgnoreCase(Double_TaxPayer));
		}
		assertTrue(TPU.EDITTAXPAYERCAP.isDisplayed());
		click(TPU.EDITTAXPAYERCAP);
		waitforElement(180, TPU.PARTITIONSPOPUPSELECT, driver);
		if (ClientType.equalsIgnoreCase("CPAEnrolled")) {
			waitforElement(180, "//div[@class='span12 ng-binding'][contains(.,'" + BPOCPAClientName + "')]", driver);
			assertTrue(driver
					.findElement(By.xpath("//div[@class='span12 ng-binding'][contains(.,'" + BPOCPAClientName + "')]"))
					.isDisplayed());
		} else if (ClientType.equalsIgnoreCase("Merchant")) {
			waitforElement(180, "//div[@class='span12 ng-binding'][contains(.,'" + MerchantClientName + "')]", driver);
			assertTrue(driver
					.findElement(
							By.xpath("//div[@class='span12 ng-binding'][contains(.,'" + MerchantClientName + "')]"))
					.isDisplayed());
		} else {
			assertTrue(
					driver.findElement(By.xpath("//div[@class='span12 ng-binding'][contains(.,'" + clientname + "')]"))
							.isDisplayed());
		}
		if (No_Taxpayer != "") {
			assertTrue(
					driver.findElement(By.xpath("//div[@class='span12 ng-binding'][contains(.,'" + No_Taxpayer + "')]"))
							.isDisplayed());
		}
		if (Single_TaxPayer != "") {
			assertTrue(driver
					.findElement(By.xpath("//div[@class='span12 ng-binding'][contains(.,'" + Single_TaxPayer + "')]"))
					.isDisplayed());
		}
		if (Double_TaxPayer != "") {
			assertTrue(driver
					.findElement(By.xpath("//div[@class='span12 ng-binding'][contains(.,'" + Double_TaxPayer + "')]"))
					.isDisplayed());
		}
		if (Three_TaxPayer != "") {
			assertTrue(driver
					.findElement(By.xpath("//div[@class='span12 ng-binding'][contains(.,'" + Three_TaxPayer + "')]"))
					.isDisplayed());
		}
		assertTrue(TPU.SAVE.isDisplayed());
		assertTrue(TPU.CANCEL.isDisplayed());
		click(TPU.CANCEL);
		if (PartitionSelect.equalsIgnoreCase("Yes")) {
			click(TPU.EDITTAXPAYERCAP);
			waitforElement(180, TPU.PARTITIONSPOPUPSELECT, driver);
			selectByValue(TPU.PARTITIONSPOPUPSELECT, PartitionValue);
			if (Franchise.equalsIgnoreCase("Yes")) {
				assertTrue(TPU.EDITTAXPAYERMODELPOPUPTEXT.isDisplayed());
			} else {
				if (!VerifyObject(driver, TPU.TaxpayerperPartition)) {
					assertTrue(true);
				} else {
					assertFalse(false);
				}

			}
			click(TPU.SAVE);
		}
		Thread.sleep(4000);
		waitforElement(60, TPU.TaxpayerPartitionConfigurationStatus, driver);
	}

	public void VerifyCancelSubscription(String ClientName, String Dont_Cancel, String Cancel,
			String Initiated_PendingBill_Client, String DashBoard_Title, String EditCompany_Title,
			String UpdateBilling_Title) throws Exception {
		String CurrentDate = xGetDate("M/1/yyyy");
		String d = xMonthsFirstDate(CurrentDate, 1, "M/d/yyyy");
		System.out.println("first date of month is " + d);
		new Robot();
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		Thread.sleep(5000);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());
		type(VSC.SMBCUSTNAMESB, ClientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]",
				driver);
		Thread.sleep(2000);
		String CName = driver.findElement(By.xpath("//table/tbody/tr/td[1]/div/a")).getText();
		System.out.println(CName);
		assertTrue((ClientName).equalsIgnoreCase(CName));
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement Registered = driver.findElement(
					By.xpath("//table/tbody/tr[2]/td/div/a[contains(text(),'" + ClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement Authorized = driver.findElement(
					By.xpath("//table/tbody/tr[2]/td/div/a[contains(text(),'" + ClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else {
			WebElement Intilized = driver.findElement(
					By.xpath("//table/tbody/tr[2]/td/div/a[contains(text(),'" + ClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Intilized);
		}
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.EXPORT, driver);
		if (Dont_Cancel.equalsIgnoreCase("Yes")) {
			waitforElement(180, MSP.CANCELCUSTOMER, driver);
			JavaScriptclick(driver, MSP.CANCELCUSTOMER);
			waitforElement(180, MSP.CANCELREQUESTREASON, driver);
			JavaScriptclick(driver, MSP.NOCANCELREQUEST);
			Thread.sleep(2000);
			if (!VerifyObject(driver, MSP.NOCANCELREQUEST)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
		if (Cancel.equalsIgnoreCase("Yes")) {
			if (!Initiated_PendingBill_Client.equalsIgnoreCase("Yes")) {
				waitforElement(180, MSP.CANCELCUSTOMER, driver);
				JavaScriptclick(driver, MSP.CANCELCUSTOMER);
				waitforElement(180, MSP.CANCELREQUESTREASON, driver);
				Thread.sleep(2000);
				type(MSP.CANCELREQUESTREASON, "Testing");
				Thread.sleep(2000);
				click(MSP.YESCOMPLETECANCEL);
				Thread.sleep(2000);
				waitUntilElementInvisibe(180, "//a[text()='Yes, Complete Cancel']", driver);
				Thread.sleep(5000);
				if (!VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
					assertTrue(true);
				} else {
					assertFalse(false);
				}

				driver.navigate().refresh();
				waitforElement(180, MSP.CANCELCUSTOMERDISABLED, driver);
				Thread.sleep(5000);
				assertTrue(MSP.CANCELCUSTOMERDISABLED.isDisplayed());
				assertTrue(VerifyObject(driver,
						"//div[3]/div/span[contains(.,'Subscription:')]/../../div[contains(.,'Service Cancellation / End')]"));
			} else {
				Thread.sleep(5000);
				assertTrue(MSP.CANCELCUSTOMERDISABLED.isDisplayed());
			}
		}
		if (!Initiated_PendingBill_Client.equalsIgnoreCase("Yes")) {
			if (!(Environment.equalsIgnoreCase("Platform_DEV") || Environment.equalsIgnoreCase("CONFIG")
					|| Environment.equalsIgnoreCase("DEV") || Environment.equalsIgnoreCase("QA"))) {
				userregistration.Logout();
				driver.get(Environment);
				Thread.sleep(5000);
				userregistration.UserLogin(VertexUserName, "Password!1");
				CancelledUserLinks(DashBoard_Title, EditCompany_Title, UpdateBilling_Title);
			}
		}
	}

	public void VerifyNocreatenewuser() throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, MySubscriptionPage.class);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());
		String ClientName = VertexClientName;
		type(VSC.SMBCUSTNAMESB, ClientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + VertexClientName + "')]",
				driver);
		Thread.sleep(2000);
		String CName = driver.findElement(By.xpath("//table/tbody/tr/td[1]/div/a")).getText();
		System.out.println(CName);
		assertTrue((ClientName).equalsIgnoreCase(CName));
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + VertexClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement Registered = driver.findElement(By.xpath(
					"//table/tbody/tr[2]/td/div/a[contains(text(),'" + VertexClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr/td/div/a[contains(text(),'" + VertexClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement Authorized = driver.findElement(By.xpath(
					"//table/tbody/tr[2]/td/div/a[contains(text(),'" + VertexClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else {
			WebElement Intilized = driver.findElement(By.xpath(
					"//table/tbody/tr[2]/td/div/a[contains(text(),'" + VertexClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Intilized);
		}
		String Clientstatus = driver.findElement(By.xpath("//table/tbody/tr/td[8]")).getText();
		if (Clientstatus.equalsIgnoreCase("Initialized") || Clientstatus.equalsIgnoreCase("Pending")) {
			if (!VerifyObject(driver, VSC.CREATENEWUSER)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
	}

	public void CancelledUserLinks(String DashBoard_Title, String EditCompany_Title, String UpdateBilling_Title)
			throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		new Robot();
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		click(FUL.DASHBOARDLINK);
		waitforElement(180, PUL.UPDATEANALYSIS, driver);
		assertTrue(DashBoard_Title.equalsIgnoreCase(driver.getTitle()));
		// *******Verifying Lookup Tax Rate link not Displaying****//
		if (!VerifyObject(driver, FUL.LOOKUPTAXRATESLINK)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		Thread.sleep(2000);
		// **Verifying Settings links
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, FUL.EDITCOMPANYLINK);
		waitforElement(180, PUL.SAVE, driver);
		assertTrue(EditCompany_Title.equalsIgnoreCase(driver.getTitle()));
		// Edit My Company Page Verification
		assertTrue(PUL.NAME.isDisplayed());
		assertTrue(PUL.PHONE.isDisplayed());
		Thread.sleep(5000);
		waitforElement(180, PUL.SettingsHover, driver);
		JavaScriptMousehover(driver, PUL.SettingsHover);
		Thread.sleep(2000);
		JavaScriptclick(driver, FUL.UPDATEBILLINGLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.SUBMITBILINFO, driver);
		assertTrue(UpdateBilling_Title.equalsIgnoreCase(driver.getTitle()));
		if (!VerifyObject(driver, PUL.CONTACTSUPPORTLINK)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		scrollUp(driver);
		click(FUL.DASHBOARDLINK);
		waitforElement(180, PUL.UPDATEANALYSIS, driver);
		userregistration.Logout();
		driver.get(url);
		Thread.sleep(5000);
		userregistration.UserLogin(adminusername, adminpassword);
	}

	public void ChangeSubscription(String clientname, String firstName, String To_Professional, String To_Premium,
			String TaxCalcOnly, String TaxCalculationsandReturns, String ReturnsOnly, String RateFile,
			String RealTimeRateService, String TaxabilityImpactReport, String Add_TestInstance, String DataIntegrity,
			String DataIntegrity_Value, String LegacyReport, String NegoContract, String UpgradeFromStndrd,
			String ClientVerify, String Password_TI, String DashboardStandTransactionUser,
			String Upgrade_Downgrade_feature, String Premium_Professional_Downgrade_Feature,
			String Subscription_Options, String TaxCalcToReturns, String DowngradePremiumtoPro) throws Exception {
		String UserName;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		UpgradeProToPremium UTP = PageFactory.initElements(driver, UpgradeProToPremium.class);
		DowngradePremiumtoPro DPP = PageFactory.initElements(driver, DowngradePremiumtoPro.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		DashboardPage DB = PageFactory.initElements(driver, DashboardPage.class);
		TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		new File(".//").getCanonicalPath();
		String parentwindow1 = null, window = null;
		new Robot();
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());
		type(VSC.SMBCUSTNAMESB, clientname);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a")).getText();
		System.out.println(CName);
		assertTrue((clientname).equalsIgnoreCase(CName));
		// JavaScriptclick(driver, VSC.ACTION);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
				+ "')]/../../../td[contains(.,'Registered')]")) {
			WebElement Registered = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
				+ "')]/../../../td[contains(.,'Authorized')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
				+ "')]/../../../td[contains(.,'Cancelled')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		}
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.EXPORT, driver);
		CNTID = driver.findElement(By.xpath("//div[@class='span3 ng-binding'][contains(.,'Cloud Customer Id:')]"))
				.getText().split(":")[1].trim();
		JavaScriptclick(driver, VSC.SMBADMIN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		String CurrentVar = driver.findElement(By.xpath("//div[@class='span3 ng-binding'][contains(.,'Current VAR:')]"))
				.getText().split(":")[1].trim();
		waitforElement(90, VSC.EDITCUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.EDITCUSTOMERSUBSCRIPTION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.CUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.CUSTOMERSUBSCRIPTION);
		waitforElement(90, MSP.SAVEBUTTON, driver);
		waitforElement(90, DPP.PROFESSIONAL, driver);
		waitforElement(90, MSP.PREMIUMSUB, driver);

		if (To_Professional.equalsIgnoreCase("Yes")) {
			waitforElement(180, DPP.PROFESSIONAL, driver);
			click(DPP.PROFESSIONAL);
		}
		if (To_Premium.equalsIgnoreCase("Yes")) {
			waitforElement(180, MSP.PREMIUMSUB, driver);
			click(MSP.PREMIUMSUB);
		}
		// Changing SubscriptionOptions/AdditionalFeatures
		if (TaxCalcOnly.equalsIgnoreCase("Yes")) {
			click(MSP.TAXCALCONLY_SUB);
		}
		if (TaxCalculationsandReturns.equalsIgnoreCase("Yes")) {
			click(MSP.TAXCALCRETURNS_SUB);
		}
		if (ReturnsOnly.equalsIgnoreCase("Yes")) {
			click(MSP.RETURNSONLY_SUB);
		}
		if (RateFile.equalsIgnoreCase("Yes")) {
			click(MSP.RATEFILECHECKBOX);
		}
		if (RealTimeRateService.equalsIgnoreCase("Yes")) {
			click(MSP.REALTIME);
		}
		if (TaxabilityImpactReport.equalsIgnoreCase("Yes")) {
			click(MSP.TAXIMPACTREPORT_SUB);
		}
		if (Add_TestInstance.equalsIgnoreCase("Yes")) {
			click(MSP.testInstanceradioBtn);
		}
		if (CurrentVar.equalsIgnoreCase("CPABPOAutomationFirm") || CurrentVar.equalsIgnoreCase("CPARefAutomationFirm")
				|| CurrentVar.equalsIgnoreCase("CPARefActive_Test_Firm")
				|| CurrentVar.equalsIgnoreCase("CPABPOActive_Test_Firm")) {
			if (!VerifyObject(driver, RP.DATAINTEGRITY)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		}
		if (DataIntegrity.equalsIgnoreCase("Yes")) {
			click(RP.ADMINDATAINTEGRITY);
			String DR = driver
					.findElement(By.xpath("//select[@id='data-integrity-storage-region']/option[@selected='selected']"))
					.getText();
			assertTrue(DR.equals("US"));
			click(RP.ADMINDATASTORAGRREGION);
			DIRegion = DataIntegrity_Value;
			selectByValue(RP.ADMINDATASTORAGRREGION, DIRegion);
		}
		if ((DataIntegrity_Value.equals("US") || DataIntegrity_Value.equals("EU")) && (DataIntegrity_Value == "")) {
			if (Environment.equalsIgnoreCase("DEV") || Environment.equalsIgnoreCase("DATAINTEGRITY")) {
				String DR = driver
						.findElement(
								By.xpath("//select[@id='data-integrity-storage-region']/option[@selected='selected']"))
						.getText();
				assertTrue(DR.equals(DataIntegrity_Value));
			}
		}

		if (LegacyReport.equalsIgnoreCase("Yes")) {
			click(MSP.LEGACYTRANSACTIONREPORT);
		}

		if (NegoContract == "") {
			JavaScriptclick(driver, MSP.SAVEBUTTON);
			Thread.sleep(1000);
			waitforElement(180, UTP.ATTENTIONALERT, driver);
			Thread.sleep(1000);
			assertTrue(UTP.ATTENTIONALERT.isDisplayed());
			if (UpgradeFromStndrd.equalsIgnoreCase("Yes")) {
				assertTrue(UTP.STNDRDSUBSCRIPTIONCHANGEMSG.isDisplayed());
			} else {
				assertTrue(UTP.SUBSCRIPTIONCHANGEMES.isDisplayed());
			}
			waitforElement(180, UTP.UPGRADEBUTTON, driver);
			JavaScriptclick(driver, UTP.UPGRADEBUTTON);
			Thread.sleep(5000);
			// waitforElement(180, PUL.PASSWORDCONFIRM, driver);
		}

		if (ClientVerify.equalsIgnoreCase("Yes")) {
			userregistration.Logout();
			Open(driver, cpaurl);
			userregistration.UserLogin("CPABPOAutomation", "Password!1");
			waitforElement(60, CP.MANAGECLIENTSMENU, driver);
			click(CP.MANAGECLIENTSMENU);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CP.SEARCHBYCLIENT, driver);
			waitforElement(90, "//tr/td/span/a", driver);
			type(CP.SEARCHBYCLIENT, clientname);
			Thread.sleep(4000);
			waitforElement(90,
					"//tr/td/span/a[contains(.,'" + clientname + "')]/../../../td/a[text()='Edit User Access']",
					driver);
			Thread.sleep(4000);
			WebElement element = driver.findElement(By.xpath("//tr/td/span/a[contains(.,'" + clientname + "')]"));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(3000);
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
			// window1.
			window = "exists";
			if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
				driver.manage().deleteAllCookies();
				driver.get(url);
				// executor.executeScript("arguments[0].click();", element);
			}
			Thread.sleep(10000);
			if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
				driver.manage().deleteAllCookies();
				driver.get(url);
				waitforElement(120, LP.VTMSACCOUNT, driver);
			}
			waitforElement(180, DB.DASHBOARD, driver);
			if (!DashboardStandTransactionUser.isEmpty()) {
				click(DB.DASHBOARD);
				Thread.sleep(5000);
				waitforElement(180, DB.SELECTCOMPANY, driver);
				assertTrue(DB.SELECTCOMPANY.isDisplayed());
				assertTrue(DB.FILLINGPERIOD.isDisplayed());
				assertTrue(DB.UPDATERESULTS.isDisplayed());
				assertTrue(DB.ZERORETURNS.isDisplayed());
				assertTrue(DB.ZEROSALES.isDisplayed());
				assertTrue(DB.ZEROTAX.isDisplayed());
				// DashboardStandTransactionUser*******************
			}
			if (To_Professional.equalsIgnoreCase("Yes")) {
				waitforElement(180, CNP.CONFIGURE, driver);
				assertTrue(CNP.CONFIGURE.isDisplayed());
			}
			if (To_Premium.equalsIgnoreCase("Yes")) {
				waitforElement(180, MSP.DOCUMENTCENTRE, driver);
				assertTrue(MSP.DOCUMENTCENTRE.isDisplayed());
			}
			// UpgradeRateandProToPremium
			JavaScriptclick(driver, UTP.SETTINGSLINK);
			waitforElement(180, UTP.EDITMYCOMPNAYLINK, driver);
			if (Upgrade_Downgrade_feature != "") {
				if (Upgrade_Downgrade_feature.equalsIgnoreCase("Returns Only")) {
					if (VerifyObject(driver, UTP.ONBOARDPENDING)) {
						assertTrue(UTP.ONBOARDPENDING.isDisplayed());
					} else {
						if (!VerifyObject(driver, UTP.ONBOARDPENDING)) {
							assertTrue(true);
						} else {
							assertTrue(false);
						}
					}
					if (Premium_Professional_Downgrade_Feature.equalsIgnoreCase("Yes")) {
						assertTrue(UTP.UPGRADEPREROnly.isDisplayed());
					} else {
						assertTrue(UTP.UPGRADEPRETCR.isDisplayed());
					}
				} else if (Upgrade_Downgrade_feature.equalsIgnoreCase("Rate File")) {
					assertTrue(UTP.UPGRADEPRETCR.isDisplayed());
					assertTrue(UTP.ONBOARDPENDING.isDisplayed());
					assertTrue(UTP.SUBSCRIPTIONCHANGEDOWNGRADERTRSMSG.isDisplayed());
				} else if (Upgrade_Downgrade_feature.equalsIgnoreCase("Tax Calculations and Returns")) {
					assertTrue(UTP.UPGRADEPRETCR.isDisplayed());
					assertTrue(UTP.ONBOARDPENDING.isDisplayed());
				}
				Thread.sleep(2000);
			}
			// TrialToPremium
			if (Subscription_Options.equalsIgnoreCase("Tax Calculations and Returns")) {
				assertTrue(UTP.ONBOARDPENDING.isDisplayed());
				assertTrue(UTP.UPGRADEPRETCR.isDisplayed());
			}
			if (Subscription_Options.equalsIgnoreCase("Returns Only")) {
				assertTrue(UTP.ONBOARDPENDING.isDisplayed());
				assertTrue(UTP.UPGRADEPREROnly.isDisplayed());
			}
			// DowngradePremiumtoPro
			if (DowngradePremiumtoPro.equalsIgnoreCase("Tax Calculations and Returns")) {
				assertTrue(MSP.downgradesubfrompremiumtcrstoProtcrs.isDisplayed());

			}
			if (DowngradePremiumtoPro.equalsIgnoreCase("Returns Only")) {
				assertTrue(UTP.UPGRADEPRETCR.isDisplayed());
				assertTrue(MSP.downgradesubfrompremiumtoProReturnsonly.isDisplayed());
			}
			if (TaxCalcToReturns.equalsIgnoreCase("Yes")) {
				assertTrue(PUL.MANAGETAXRETURNS.isDisplayed());
				if (VerifyObject(driver, PUL.MANAGETAXRETURNS)) {
					JavaScriptMousehover(driver, PUL.ReturnsHover);
					JavaScriptclick(driver, PUL.MANAGETAXRETURNS);
					waitforElement(180, PUL.MOSTRECENT, driver);
				}
			}
		}
		if (Add_TestInstance.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, RP.INVITEUSER);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, RP.SEARCH, driver);
			type(RP.SEARCH, firstName);
			Thread.sleep(2000);
			UserName = driver
					.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'" + firstName + "')]/../td[6]"))
					.getText();
			// if(VertexClientName.contains(".TI")){
			if (UserName.contains(".TI")) {
				String[] data = UserName.split(".TI");
				UserName = data[0];
			}

			userregistration.Logout();
			Thread.sleep(2000);
			if (Environment.equalsIgnoreCase("DEV")) {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			} else {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			}
			Thread.sleep(7000);
			driver.get(TestInstanceSetPwdURL);
			System.out.println(TestInstanceSetPwdURL);
			assertTrue(driver.getTitle().equalsIgnoreCase("Test Instance Password | Vertex Cloud"));
			assertTrue(TI.testInstancePwdlabel.isDisplayed());
			TestInstanceclientName = ClientName + ".TI";
			System.out.println("Test Instance Client name :" + TestInstanceclientName);
			Thread.sleep(2000);
			type(TI.testInstanceUsername, TestInstanceUserName);
			type(TI.testInstancepassword, Password_TI);
			Thread.sleep(2000);
			type(TI.testInstanceconfirmpassword, Password_TI);
			Thread.sleep(2000);
			click(TI.setPassword);
		}
		if (window == "exists") {
			driver.close();
			Thread.sleep(5000);
			driver.switchTo().window(parentwindow1);
		}
		Thread.sleep(2000);

	}

	public void AdminFlexFields(String ClientName, String FileName, String DownloadReports, String Taxpayer)
			throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			File directory = new File(".//");
			AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
			PageFactory.initElements(driver, LoginPage.class);
			Robot rob = new Robot();
			String DownloadFilepath = xDownloadFiles("TransactionReport\\HVR");
			click(AUA.manageSMBCustomers);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			/*
			 * if (BPOCPAClientName != null) { ClientName = BPOCPAClientName; } else if
			 * (MerchantClientName != null) { ClientName = MerchantClientName; } else {
			 * ClientName = VertexClientName; }
			 */
			type(AUA.smbCustomerName, ClientName);
			Thread.sleep(7000);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				WebElement registered = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
				JavaScriptclick(driver, registered);
			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				WebElement authorized = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
				JavaScriptclick(driver, authorized);
			} else {
				WebElement initialized = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
				JavaScriptclick(driver, initialized);
			}
			// }
			Thread.sleep(3000);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			click(AUA.smbCustomerReports);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			if (FileName != "") {

				if (VerifyObject(driver, AUA.smbCustomerName)) {
					type(AUA.smbCustomerName, ClientName);
					Thread.sleep(7000);
					if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
							+ ClientName + "')]/../../../td[contains(text(),'Registered')]")) {
						driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"))
								.click();
					} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
							+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
						driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"))
								.click();
					} else {
						driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"))
								.click();
					}
					Thread.sleep(3000);
					waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
					click(AUA.smbCustomerReports);
					waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				}
			}
			waitforElement(180, AUA.REPORT, driver);
			if (DownloadReports != "") {
				selectByValue(AUA.REPORT, DownloadReports);
				Thread.sleep(1000);
				selectByValue(AUA.companyName, Taxpayer);
				click(AUA.DOWNLOADREPORT);
				Thread.sleep(3000);
				driver.navigate().refresh();
				File tempfile = null;
				File tempfile2 = null;
				if (Browser.equalsIgnoreCase("IE")) {
					Thread.sleep(10000);
					rob.keyPress(KeyEvent.VK_CONTROL);
					rob.keyPress(KeyEvent.VK_L);
					rob.keyRelease(KeyEvent.VK_L);
					rob.keyRelease(KeyEvent.VK_CONTROL);
					rob.keyPress(KeyEvent.VK_ENTER);
					rob.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(10000);
					rob.keyPress(KeyEvent.VK_TAB);
					rob.keyRelease(KeyEvent.VK_TAB);
					rob.keyPress(KeyEvent.VK_TAB);
					rob.keyRelease(KeyEvent.VK_TAB);
					rob.keyPress(KeyEvent.VK_DOWN);
					rob.keyRelease(KeyEvent.VK_DOWN);
					rob.keyPress(KeyEvent.VK_DOWN);
					rob.keyRelease(KeyEvent.VK_DOWN);
					rob.keyPress(KeyEvent.VK_ENTER);
					rob.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					rob.keyPress(KeyEvent.VK_HOME);
					rob.keyRelease(KeyEvent.VK_HOME);
					Thread.sleep(5000);
					StringSelection stringSelection = new StringSelection(DownloadFilepath + "\\");
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
					Thread.sleep(5000);
					rob.keyPress(KeyEvent.VK_CONTROL);
					rob.keyPress(KeyEvent.VK_V);
					rob.keyRelease(KeyEvent.VK_V);
					rob.keyRelease(KeyEvent.VK_CONTROL);
					rob.delay(5000);
					rob.keyPress(KeyEvent.VK_ENTER);
					rob.keyRelease(KeyEvent.VK_ENTER);
					rob.delay(1000);
					Thread.sleep(5000);
				}
				if (Browser.equalsIgnoreCase("Chrome")) {
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "TransactionReport\\HVR";
					System.out.println("Path" + path);
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					tempfile = new File(LatestDownloadedFile("TransactionReport\\HVR", true));
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					Thread.sleep(8000);
					tempfile = new File(LatestDownloadedFile("TransactionReport\\HVR", false));
				}
				if (FileName != "") {
					String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
							+ "FlexFields";
					String name = FileName;
					tempfile2 = new File(DirName + "\\" + name + ".csv");
					Thread.sleep(5000);
					Comparecsvfiles(tempfile, tempfile2);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void AdminCloudClientBannerChanges(String clientName, String Client_Type, String Verify_PartitionConfig,
			String Invoice_Client, String Payment_Type, String ClientIsECW, String VerifyCSV,
			String ParentCategory_Value, String ChildName, String Save, String ChildCat_Value,
			String Delete_ParentCategory) throws Exception {
		String Client;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Robot robot = new Robot();
		File directory = new File(".//");
		File tempfile = null;
		String Clienttype = Client_Type;
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, VSC.SMBCUSTNAMESB, driver);
		if (clientName != null) {
			type(VSC.SMBCUSTNAMESB, clientName);
			Thread.sleep(5000);
			waitforElement(180, "//table/tbody/tr/td[contains(.,'" + clientName + "')]", driver);
			if (Client_Type.equalsIgnoreCase("standard")) {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ clientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			} else {
				WebElement clientname = driver.findElement(By
						.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientName + "')]"));
				JavaScriptclick(driver, clientname);
				Thread.sleep(2000);
			}
			Client = clientName;
		} else {
			type(VSC.SMBCUSTNAMESB, BPOCPAClientName);
			Thread.sleep(5000);
			waitforElement(180, "//table/tbody/tr/td[contains(.,'" + BPOCPAClientName + "')]", driver);
			if (Client_Type.equalsIgnoreCase("standard")) {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ BPOCPAClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			} else {
				driver.findElement(By.xpath(
						"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + BPOCPAClientName + "')]"))
						.click();
				Thread.sleep(2000);
			}
			Client = BPOCPAClientName;
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, VSC.EXPORT, driver);
		if (!Verify_PartitionConfig.equalsIgnoreCase("YES")) {
			if (VerifyObject(driver, VSC.EXPORT)) {
				if (Browser.equalsIgnoreCase("IE")) {
					Thread.sleep(5000);
					JavaScriptclick(driver, VSC.EXPORT);
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_ALT);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_S);
				} else {
					click(VSC.EXPORT);
				}
				Thread.sleep(3000);
				if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "Reports";
					xDownloadFiles("Reports");
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(3000);
					tempfile = new File(LatestDownloadedFile("Reports", true));
					tempfile.toString();
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					tempfile = new File(LatestDownloadedFile("Reports", false));
					tempfile.toString();
				}
				FileInputStream fis = new FileInputStream(Filepath);
				org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
				Thread.sleep(7000);
				int Num_Sheets = workbook.getNumberOfSheets();
				File file = new File(".//");
				String expectedfile = file.getCanonicalPath() + "\\InputTestData\\Reports\\customerDetail.xlsx";
				for (int j = 0; j < Num_Sheets; j++) {
					String sheetname = workbook.getSheetName(j);
					String stsc = XLSXtoXLSXFilesColCompare(expectedfile, Filepath, sheetname);
					if (stsc.isEmpty()) {
						assertTrue(true);
					}
				}
			}
			waitforElement(180, VSC.SMRRETURNS, driver);
			assertTrue(VSC.CLIENTINFORMATION.isDisplayed());
			assertTrue(VSC.PaymentType.isDisplayed());
			if (Invoice_Client != "") {
				String[] payment_type = VSC.Paymentvalue.getText().split(":");
				String value_payment = payment_type[1].trim();
				assertTrue(value_payment.equalsIgnoreCase(Invoice_Client));
			}
			if (Payment_Type != "") {
				String[] payment_type = VSC.Paymentvalue.getText().split(":");
				String value_payment = payment_type[1].trim();
				assertTrue(value_payment.equalsIgnoreCase(Payment_Type));
			}
			assertTrue(VSC.BillingAddress.isDisplayed());
			assertTrue(VSC.ShipToAddress.isDisplayed());
			assertTrue(VSC.Phonenumber.isDisplayed());
			assertTrue(VSC.Contractnumber.isDisplayed());
			assertTrue(VSC.STATUS.isDisplayed());
			if (Client_Type.equalsIgnoreCase("standard")) {
				assertTrue(VSC.STATUS.isDisplayed());
			}
			if (!ClientIsECW.equalsIgnoreCase("Yes") && !Client_Type.equalsIgnoreCase("standard")) {
				JavaScriptclick(driver, VSC.SMBCUSTOMERREPORT);
				waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
				if (!Client_Type.equalsIgnoreCase("ECW")) {
					assertTrue(VSC.Address.isDisplayed());
					assertTrue(VSC.ID_Number_CustomerConfig.isDisplayed());
				}
				if (Client_Type.equalsIgnoreCase("standard")) {
					JavaScriptclick(driver, VSC.SMBCUSTOMERREPORT);
					waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
					assertTrue(VSC.STATUS.isDisplayed());
					JavaScriptclick(driver, VSC.CUSTOMERRETURNS);
					waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
					assertTrue(VSC.STATUS.isDisplayed());
					JavaScriptclick(driver, VSC.CustomerPrepayments);
					waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
					assertTrue(VSC.STATUS.isDisplayed());
					JavaScriptclick(driver, VSC.SMBREPORTS);
					waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
					assertTrue(VSC.STATUS.isDisplayed());
					JavaScriptclick(driver, VSC.ADMINDETAILS);
					waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
					assertTrue(VSC.STATUS.isDisplayed());
					JavaScriptclick(driver, VSC.PartitonConfiguration);
					waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
					assertTrue(VSC.STATUS.isDisplayed());
					JavaScriptclick(driver, VSC.Connectors);
					waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(3000);
					assertTrue(VSC.STATUS.isDisplayed());
				}
			} else if (Client_Type.equalsIgnoreCase("ECW") || Client.contains("ECW")) {
				waitforElement(60, VSC.PartitionConfiguration, driver);
				click(VSC.PartitionConfiguration);
				waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
				assertTrue(VSC.ECWTEXT.isDisplayed());
			} else if (Clienttype.equalsIgnoreCase("SysROnly") || Clienttype.equalsIgnoreCase("ProdSupport")
					|| Clienttype.equalsIgnoreCase("Integration") || Clienttype.equalsIgnoreCase("TreasuryAdmin")
					|| Clienttype.equalsIgnoreCase("BillingApprove")) {
				waitforElement(60, VSC.PartitionConfiguration, driver);
				click(VSC.PartitionConfiguration);
				waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
				assertTrue(VSC.SAVEDISABLE.isDisplayed());
			} else {
				if (Invoice_Client != "") {
					String[] payment_type = VSC.Paymentvalue.getText().split(":");
					String value_payment = payment_type[1].trim();
					assertTrue(value_payment.equalsIgnoreCase(Invoice_Client));
				}
				waitforElement(60, VSC.PartitionConfiguration, driver);
				click(VSC.PartitionConfiguration);
				waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
				assertTrue(VSC.CustomerName.isDisplayed());
				assertTrue(VSC.TrustedId.isDisplayed());
				assertTrue(VSC.CustomerName.isDisplayed());
				assertTrue(VSC.CustomerNumber.isDisplayed());
				assertTrue(VSC.CurrentVAR.isDisplayed());
				assertTrue(VSC.AccountType.isDisplayed());
				assertTrue(VSC.Subscription.isDisplayed());
				assertTrue(VSC.APIKey.isDisplayed());
				assertTrue(VSC.Taxpartconfig.isDisplayed());
				assertTrue(VSC.Taxpartused.isDisplayed());
				assertTrue(VSC.APIPassword.isDisplayed());
				assertTrue(VSC.PartitionConfigOverride.isDisplayed());
				if (Client_Type.equalsIgnoreCase("MTPP")) {
					Select companydropdown = new Select(
							driver.findElement(By.xpath("//div/select[@disabled='disabled']")));
					String companyVal = companydropdown.getFirstSelectedOption().getText();
					assertTrue(companyVal.equalsIgnoreCase("--All--"));
					waitforElement(180, VSC.CalculationPrecedence, driver);
					click(VSC.CalculationPrecedence);
				} else {
					waitforElement(180, VSC.CalculationPrecedence, driver);
					click(VSC.CalculationPrecedence);
					assertTrue(VSC.SETTINGNAME.isDisplayed());
					assertTrue(VSC.SettingValue.isDisplayed());
					assertTrue(VSC.Source.isDisplayed());
					assertTrue(VSC.Childsettings.isDisplayed());
				}
				waitforElement(180, VSC.EXPORTBUTTON, driver);
				JavaScriptclick(driver, VSC.EXPORTBUTTON);
				Thread.sleep(3000);
				if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "Reports";
					xDownloadFiles("Reports");
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(3000);
					tempfile = new File(LatestDownloadedFile("Reports", false));
					tempfile.toString();
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					tempfile = new File(LatestDownloadedFile("Reports", false));
					tempfile.toString();
				}
				if (VerifyCSV.equalsIgnoreCase("Yes")) {
					File file = new File(".//");
					String expectedfile = file.getCanonicalPath()
							+ "//InputTestData//Reports//ConfigPartitionExport_template.csv";
					File fileexpected = new File(expectedfile);
					Comparecsvfiles(tempfile, fileexpected);
				}
				if (ParentCategory_Value != "") {
					waitforElement(180, VSC.CalculationPrecedence, driver);
					click(VSC.ParentCateValue);
					String initialValue = getAttribute(VSC.ParentCateValue, "value");// VSC.ParentCateValue.getText().trim();
					clear(VSC.ParentCateValue);
					type(VSC.ParentCateValue, ParentCategory_Value);
					sendkeysTab(VSC.ParentCateValue);
					driver.navigate().refresh();
					waitforElement(180, VSC.CalculationPrecedence, driver);
					click(VSC.CalculationPrecedence);
					waitforElement(180, VSC.ParentCateValue, driver);
					click(VSC.ParentCateValue);
					assertTrue((getAttribute(VSC.ParentCateValue, "value")).equalsIgnoreCase(initialValue));
					String Source_BefUpdated = VSC.DefaultSourceName.getText().trim();
					assertTrue(Source_BefUpdated.equalsIgnoreCase("Vertex Defined"));
					clear(VSC.ParentCateValue);
					type(VSC.ParentCateValue, ParentCategory_Value);
					sendkeysTab(VSC.ParentCateValue);
				}
				JavaScriptclick(driver, VSC.TAXGIS);
				waitforElement(180, VSC.AddLink, driver);
				JavaScriptclick(driver, VSC.AddLink);
				Thread.sleep(2000);
				assertTrue(VSC.OVERRIDEiNFO.isDisplayed());
				assertTrue(VSC.POPUPCLOSE.isDisplayed());
				JavaScriptclick(driver, VSC.POPUPCLOSE);
				Thread.sleep(1000);
				JavaScriptclick(driver, VSC.AddLink);
				waitforElement(180, VSC.CHILDNAME, driver);
				JavaScriptclick(driver, VSC.CANCEL);
				waitforElement(180, VSC.AddLink, driver);
				JavaScriptclick(driver, VSC.AddLink);
				waitforElement(180, VSC.CHILDNAME, driver);
				type(VSC.CHILDNAME, ChildName);
				Thread.sleep(2000);
				JavaScriptclick(driver, VSC.SAVECHILDNAME);
				Thread.sleep(1000);
				if (VerifyObject(driver, VSC.CLOSE)) {
					JavaScriptclick(driver, VSC.CLOSE);
				}
				if (Save.equalsIgnoreCase("YES")) {
					click(VSC.SAVECATEGORY);
					waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(3000);
					assertTrue(VSC.SUCCESSMESSAGE.isDisplayed());
				}
				if (!VSC.SEEDETAILSLINK.isDisplayed()) {
					JavaScriptclick(driver, VSC.TAXGIS);
				}
				waitforElement(180, VSC.SEEDETAILSLINK, driver);
				click(VSC.SEEDETAILSLINK);
				waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ChildName + "')]/../td/input", driver);
				WebElement childcat = driver
						.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + ChildName + "')]/../td/input"));
				click(childcat);
				type(childcat, ChildCat_Value);
				waitforElement(180, VSC.CLOSE, driver);
				click(VSC.CLOSE);
				waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
				click(VSC.SAVECATEGORY);
				waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
				if (!VSC.SEEDETAILSLINK.isDisplayed()) {
					JavaScriptclick(driver, VSC.TAXGIS);
				}
				waitforElement(180, VSC.AddLink, driver);
				click(VSC.SEEDETAILSLINK);
				waitforElement(180,
						"//table/tbody/tr/td[contains(.,'" + ChildName + "')]/../td/a[contains(.,'Delete')]", driver);
				WebElement delete = driver.findElement(
						By.xpath("//table/tbody/tr/td[contains(.,'" + ChildName + "')]/../td/a[contains(.,'Delete')]"));
				JavaScriptclick(driver, delete);
				Thread.sleep(4000);
				JavaScriptclick(driver, VSC.SAVECATEGORY);
				waitforElement(180, VSC.SUCCESSMESSAGE, driver);
				Thread.sleep(3000);
				if (!VSC.SEEDETAILSLINK.isDisplayed()) {
					JavaScriptclick(driver, VSC.TAXGIS);
				}
				waitforElement(180, VSC.AddLink, driver);
				click(VSC.SEEDETAILSLINK);
				assertTrue(!(driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + ChildName + "')]"))
						.isDisplayed()));
				if (Delete_ParentCategory.equalsIgnoreCase("YES")) {
					waitforElement(180, VSC.CalculationPrecedence, driver);
					click(VSC.CalculationPrecedence);
					assertTrue(driver.findElement(By.xpath(
							"//a[contains(.,'Calculation Precedence')]/../../../div/table/tbody/tr[2]/td[contains(.,'Procurement - Commodity')]/../td[contains(.,'User Defined')]"))
							.isDisplayed());
					waitforElement(180, VSC.DELETEPARENTCATEGORY, driver);
					click(VSC.DELETEPARENTCATEGORY);
					waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
					click(VSC.CalculationPrecedence);
					assertTrue(driver.findElement(By.xpath(
							"//a[contains(.,'Calculation Precedence')]/../../../div/table/tbody/tr[2]/td[contains(.,'Procurement - Commodity')]/../td[contains(.,'Vertex Defined')]"))
							.isDisplayed());
				}
			}
		}
	}

	public void AdminConfiglimts(String Select_Test, String Products, String Mappings, String Customer,
			String Certificates, String Client) throws Exception {
		AdminConfigLimit ACL = PageFactory.initElements(driver, AdminConfigLimit.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, Client);
		Thread.sleep(5000);
		waitforElement(180, VSC.SELECT, driver);
		waitforText(180, "//table/tbody/tr[2]/td/div/a[contains(.,'" + Client + "')]", driver);
		JavaScriptclick(driver, driver.findElement(By.xpath("//td/div/a[contains(.,'" + Client + "')]")));
		waitforElement(180, VSC.ADMINDETAILS, driver);
		click(VSC.ADMINDETAILS);
		waitforElement(180, ACL.VIEWLIMITS, driver);
		click(ACL.VIEWLIMITS);
		waitforElement(60, ACL.CONFIGURATIONLIMITSHEADER, driver);
		switch (Select_Test) {
		case "VerifyDefaultLimits":
			VerifyDefaultConfigLimits(Products, Mappings, Customer, Certificates, "Default");
			break;
		case "Edit Limits System Admin":
			SaveNewConfigLimits(Products, Mappings, Customer, Certificates, "Postive");
			break;
		case "Verify Edited Limits ":
			VerifyDefaultConfigLimits(Products, Mappings, Customer, Certificates, "Edited");
			break;
		case "Verify Edit Limits Option for Other Admins ":
			VerifyEditOptionForAdmins();
			break;
		case "Verify Negative Range Limits":
			SaveNewConfigLimits(Products, Mappings, Customer, Certificates, "Negative");
			break;
		}
		click(ACL.CLOSEBUTTON);
	}

	public void VerifyDefaultConfigLimits(String Products, String Mappings, String Customer, String Cwertificates,
			String Default) throws Exception {
		AdminConfigLimit ACL = PageFactory.initElements(driver, AdminConfigLimit.class);
		waitforElement(60, ACL.PRODUCTSLIMIT, driver);
		// Verify Default Limits
		String Productsdefaullimits = getText(ACL.PRODUCTSLIMIT);
		assertTrue(Productsdefaullimits.equalsIgnoreCase(Products));
		String mappingssdefaullimits = getText(ACL.MAPPINGSLIMIT);
		assertTrue(mappingssdefaullimits.equalsIgnoreCase(Mappings));
		String customerdefaullimits = getText(ACL.CUSTOMERLIMIT);
		assertTrue(customerdefaullimits.equalsIgnoreCase(Customer));
		String certificatesdefaullimits = getText(ACL.CERTIFICATELIMIT);
		assertTrue(certificatesdefaullimits.equalsIgnoreCase(Cwertificates));
	}

	public void VerifyEditOptionForAdmins() throws Exception {
		AdminConfigLimit ACL = PageFactory.initElements(driver, AdminConfigLimit.class);
		assertTrue(ACL.PRODUCTEDITHIDE.isDisplayed());
		assertTrue(ACL.MAPPINGEDITHIDE.isDisplayed());
		assertTrue(ACL.CUSTOMEREDITHIDE.isDisplayed());
		assertTrue(ACL.CERTIFICATEEDITHIDE.isDisplayed());
	}

	public void SaveNewConfigLimits(String Products, String Mappings, String Customer, String Certificates,
			String LimitsRange) throws Exception {
		AdminConfigLimit ACL = PageFactory.initElements(driver, AdminConfigLimit.class);
		if (!Products.isEmpty()) {
			waitforElement(60, ACL.PRODUCTEDIT, driver);
			click(ACL.PRODUCTEDIT);
			waitforElement(60, ACL.PRODUCTTEXT, driver);
			clear(ACL.PRODUCTTEXT);
			type(ACL.PRODUCTTEXT, Products);
			click(ACL.SVAEBUTTON);
			if (LimitsRange.equalsIgnoreCase("Negative")) {
				assertTrue(ACL.VALIDATIONMESSAGE.isDisplayed());
				click(ACL.CANCELBUTTON);
			}
		}
		if (!Mappings.isEmpty()) {
			waitforElement(60, ACL.MAPPINGSEDIT, driver);
			click(ACL.MAPPINGSEDIT);
			waitforElement(60, ACL.MAPPINGSTEXT, driver);
			clear(ACL.MAPPINGSTEXT);
			type(ACL.MAPPINGSTEXT, Mappings);
			click(ACL.SVAEBUTTON);
			if (LimitsRange.equalsIgnoreCase("Negative")) {
				assertTrue(ACL.VALIDATIONMESSAGE.isDisplayed());
				click(ACL.CANCELBUTTON);
			}
		}
		if (!Customer.isEmpty()) {
			waitforElement(60, ACL.CUSTOMEREDIT, driver);
			click(ACL.CUSTOMEREDIT);
			waitforElement(60, ACL.CUSTOMERTEXT, driver);
			clear(ACL.CUSTOMERTEXT);
			type(ACL.CUSTOMERTEXT, Customer);
			click(ACL.SVAEBUTTON);
			if (LimitsRange.equalsIgnoreCase("Negative")) {
				assertTrue(ACL.VALIDATIONMESSAGE.isDisplayed());
				click(ACL.CANCELBUTTON);
			}
		}
		if (!Certificates.isEmpty()) {
			waitforElement(60, ACL.CERTIFICATEEDIT, driver);
			click(ACL.CERTIFICATEEDIT);
			waitforElement(60, ACL.CERTIFICATETEXT, driver);
			clear(ACL.CERTIFICATETEXT);
			type(ACL.CERTIFICATETEXT, Certificates);
			click(ACL.SVAEBUTTON);
			if (LimitsRange.equalsIgnoreCase("Negative")) {
				assertTrue(ACL.VALIDATIONMESSAGE.isDisplayed());
				click(ACL.CANCELBUTTON);
			}
		}
	}

	@SuppressWarnings({ "static-access", "unused" })
	public void SystemAdminReadOnlyRole(String DashBoard_Title, String Metrics_Title, String InviteUsers_Title,
			String OSPLogs_Title, String TaxAreaTransactions_Title, String CTC_Returns_Title,
			String PremiumCustomers_Title, String DocumentCenter_Title, String VertexCloudVars_Title,
			String SystemConfg_Title) throws Exception {

		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		AdminUserLinks AUL = PageFactory.initElements(driver, AdminUserLinks.class);
		InviteUsers INU = PageFactory.initElements(driver, InviteUsers.class);
		waitforElement(180, TBS.DASHBOARD, driver);
		Thread.sleep(2000);
		click(TBS.DASHBOARD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		String DB_Title = driver.getTitle();
		assertEquals(DashBoard_Title, DB_Title);
		waitforElement(180, TBS.METRICS, driver);
		// Metrics
		click(TBS.METRICS);
		waitforElement(180, TBS.MAINTENANCEMETRICDATA, driver);
		String MR_Title = driver.getTitle();
		assertEquals(Metrics_Title, MR_Title);
		assertTrue(TBS.MAINTENANCEMETRICDATA.isDisplayed());
		assertTrue(TBS.DOWNLOADEDCSV.isDisplayed());
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TBS.DOWNLOADEDCSV);
		} else {
			click(TBS.DOWNLOADEDCSV);
		}
		// taking time to download
		Thread.sleep(50000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(15000);
			LatestDownloadedFile("Metrics", false);
		}
		CSVReader reader = new CSVReader(new FileReader(DownloadFiles.Filepath));
		List<String[]> contents = reader.readAll();

		String ContentsStrArr[] = null;

		for (int RowsInt = 0; RowsInt < contents.size(); RowsInt++) {
			ContentsStrArr = (String[]) contents.get(RowsInt);
			for (int ColsInt = 0; ColsInt < ContentsStrArr.length; ColsInt++) {
				if (RowsInt == 0) {
					System.out.println(ContentsStrArr[ColsInt]);
				}
			}
			break;
		}
		reader.close();
		Thread.sleep(2000);
		waitforElement(180, TBS.INVITEUSERS, driver);
		// invite Users
		click(TBS.INVITEUSERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TBS.EXPORTBUTTON, driver);
		// Verifying Client Name and Client Number Columns as per POR-15816 in 7.0
		waitforText(180, INU.CLIENTNAME, driver);
		assertTrue(INU.CLIENTNAME.isDisplayed());
		assertTrue(INU.CLIENTNUMBER.isDisplayed());
		String IN_Title = driver.getTitle();
		assertEquals(InviteUsers_Title, IN_Title);
		click(TBS.PREMIUMSMBCUSTOMERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// ..OSP report.........
		click(TBS.OSPLOGS);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, TBS.OSPSEARCH, driver);
		assertTrue(driver.getTitle().equalsIgnoreCase(OSPLogs_Title));
		// ..CTC Impact.........
		JavaScriptMousehover(driver, TBS.CTCImpact);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(TBS.CTCTransactions);
		Thread.sleep(5000);
		assertTrue(driver.getTitle().equalsIgnoreCase(TaxAreaTransactions_Title));
		JavaScriptMousehover(driver, TBS.CTCImpact);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(TBS.CTCReturns);
		Thread.sleep(5000);
		assertTrue(driver.getTitle().equalsIgnoreCase(CTC_Returns_Title));
		// ..Premium Customers.........
		JavaScriptMousehover(driver, AUL.PremiumCustomerLink);
		JavaScriptclick(driver, TBS.PremiumCust);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(driver.getTitle().equalsIgnoreCase(PremiumCustomers_Title));
		// ...Document Center..................//
		click(TBS.DOCUMENTCENTER);
		waitforElement(180, TBS.DOCUMENTCENTERFILETYPE, driver);
		assertTrue(driver.getTitle().equalsIgnoreCase(DocumentCenter_Title));
		// ..Vertex Vars.........
		click(TBS.VERTEXSMBVARS);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		assertTrue(driver.getTitle().equalsIgnoreCase(VertexCloudVars_Title));
		// ...System configuration.......
		click(TBS.SystemConfiguration);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		assertTrue(driver.getTitle().equalsIgnoreCase(SystemConfg_Title));
		// POR-3824 Error monitor
		JavaScriptclick(driver, TBS.ERRORMONITOR);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TBS.ERRORID, driver);
		assertTrue(TBS.ERRORMONITOR.isDisplayed());
		assertTrue(TBS.ERRORID.isDisplayed());
		assertTrue(TBS.ERRORQUEUETYPE.isDisplayed());
		assertTrue(TBS.ERRORCREATEDQUEUE.isDisplayed());
		assertTrue(TBS.ERRORMODIFIEDDATE.isDisplayed());
		assertTrue(TBS.ERRORSMBCUSTOMER.isDisplayed());
		assertTrue(TBS.ERRORPARENTSMBCUSTOMERNAME.isDisplayed());
		assertTrue(TBS.ERRORMESSAGE.isDisplayed());
		assertTrue(TBS.ERRORACTION.isDisplayed());
		click(TBS.ERRORDETAILS);
		waitforElement(180, TBS.ERRORCANCELBUTTON, driver);
		driver.findElement(By.xpath(TBS.ERRORCANCELBUTTON_XPATH)).sendKeys(Keys.PAGE_DOWN);
		JavaScriptclick(driver, TBS.ERRORCANCELBUTTON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, TBS.DASHBOARD, driver);
		JavaScriptclick(driver, TBS.DASHBOARD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TBS.COLLAPSE, driver);
		scrollDown(driver);
		// Collapse functionality POR-1245
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TBS.COLLAPSE);
		} else {
			click(TBS.COLLAPSE);
		}
		waitforText(180, TBS.VERIFYCOLLAPSED, driver);
		assertTrue(TBS.VERIFYCOLLAPSED.isDisplayed());
		assertTrue(TBS.VERTEXVARSHTEXT.isDisplayed());
		JavaScriptclick(driver, TBS.COPSDVERTEXVARSTABICON);
		waitforElement(180, TBS.VERTEXVARSHTEXT, driver);
		JavaScriptclick(driver, TBS.COLLAPSE);
		Thread.sleep(5000);
	}

	public void TransactionMetricData(String Startdate, String EndDate, String Company_Name, String Company_Status,
			String DNS_Name, String Msg_Type, String Trans_Count, String Subscription) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		SOCReport SRR = PageFactory.initElements(driver, SOCReport.class);
		Robot robot = new Robot();
		click(MC.METRICSLINK);
		waitforElement(180, MC.TMDDWNLD, driver);
		assertTrue(MC.TMDDWNLD.isDisplayed());
		clear(SRR.Stdate);
		type(SRR.Stdate, Startdate);
		click(SRR.Stdate);
		Thread.sleep(5000);
		clear(SRR.Enddate);
		waitforElement(180, SRR.Enddate, driver);
		type(SRR.Enddate, EndDate);
		click(SRR.Enddate);
		Thread.sleep(5000);
		click(MC.TMDDWNLD);
		Thread.sleep(30000);
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("Metrics", false);
		}
		String Compname = Company_Name;
		String Compstatus = Company_Status;
		String Clientname = VertexClientName;
		String Subscr = Subscription;
		String DNSName = DNS_Name;
		String MsgType = Msg_Type;
		String Transcount = Trans_Count;
		xGetCSVValue(UtilityScript.Filepath, Clientname, Compname, Compstatus, Subscr, DNSName, MsgType, Transcount);
		System.out.println("ReturnVAlue=" + UtilityScript.Compstat);
	}

	public void SMBInvitationAdminRoleunableInviteusers() throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		InviteUsers INU = PageFactory.initElements(driver, InviteUsers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(INU.INVITEUSERS);
		JavaScriptclick(driver, INU.INVITEUSERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(driver.findElement(By.xpath("//div[1]/div[2]/div[2]/div/div/div[1]/h1")).isDisplayed());
	}

	@SuppressWarnings("resource")
	public void PerformanceMetricsETEReport(String clientname, String CompanyName, String StartDate, String EndDate,
			String Report, String Year, String Month, String FileName, String Folder_Name, String Validate_StartDate,
			String Validate_Report, String VerifyWithoutTaxpayer, String TestInstance_Client,
			String Verify_NoblankReport) throws Exception {
		try {
			AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
			VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
			File directory = new File(".//");
			Robot rob = new Robot();
			String DownloadFilepath = xDownloadFiles("TransactionReport");
			click(AUA.manageSMBCustomers);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			String ClientName;
			if (BPOCPAClientName != null) {
				ClientName = BPOCPAClientName;
			} else if (MerchantClientName != null) {
				ClientName = MerchantClientName;
			} else {
				ClientName = clientname;
			}
			if (TestInstance_Client.equalsIgnoreCase("YES")) {
				ClientName = clientname + ".TI";
			}
			type(AUA.smbCustomerName, ClientName);
			Thread.sleep(5000);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			} else {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
			}
			// }
			Thread.sleep(3000);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			click(AUA.smbCustomerReports);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);

			waitforElement(180, AUA.REPORT, driver);
			selectByValue(AUA.REPORT, Report);
			Thread.sleep(2000);
			Select select = new Select(VSC.CompanyName);
			String DefaultValue = select.getFirstSelectedOption().getText();
			assertTrue(DefaultValue.equalsIgnoreCase("All"));
			if (CompanyName != "") {
				selectByValue(VSC.CompanyName, CompanyName);
			}
			if (Validate_StartDate != "") {
				type(AUA.StartDate, Validate_StartDate);
				Thread.sleep(2000);
				sendkeysTab(AUA.StartDate);
				click(AUA.Enddate);
				Thread.sleep(2000);
				String defaultdate = AUA.StartDate.getAttribute("value");
				assertTrue(defaultdate.equalsIgnoreCase("01/01/2017"));
			}
			if (StartDate != "") {
				clear(AUA.StartDate);
				type(AUA.StartDate, StartDate);
			}

			if (EndDate != "") {
				type(AUA.Enddate, EndDate);
			}

			if (VerifyWithoutTaxpayer.equalsIgnoreCase("YES")) {
				assertTrue(AUA.GENERATEBUTTONDISABLE.isDisplayed());
			} else {
				click(AUA.GENERATEREPORT);
				waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
				assertTrue(VSC.ADMINSUCCESSREPORT.isDisplayed());
				driver.navigate().refresh();
				waitforElement(240, AUA.companyName, driver);
				if (Folder_Name.equalsIgnoreCase("TransactionsMetricReport")) {
					boolean report = false;
					int count = 0;
					do {
						driver.navigate().refresh();
						Thread.sleep(10000);
						count = count + 1;
						if (count == 35) {
							assertTrue(false);
							break;
						}
						if (VerifyObject(driver, AUA.smbCustomerName)) {
							type(AUA.smbCustomerName, ClientName);
							Thread.sleep(5000);
							if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
									+ ClientName + "')]/../../../td[contains(text(),'Registered')]")) {
								driver.findElement(By.xpath(
										"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
												+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"))
										.click();
							} else if (VerifyObject(driver,
									"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
											+ "')]/../../../td[contains(text(),'Authorized')]")) {
								driver.findElement(By.xpath(
										"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
												+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"))
										.click();
							} else {
								driver.findElement(By.xpath(
										"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
												+ "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"))
										.click();
							}
							// }
							Thread.sleep(3000);
							waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
							click(AUA.smbCustomerReports);
							waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
							waitforElement(180, AUA.REPORT, driver);
						}
						if (VerifyObject(driver,
								"//select[@id='rootFolder']/option[text()='TransactionsMetricReport']")) {
							report = true;
						}
					} while (report == false);
				}

				selectByValue(AUA.Rootfolder, Folder_Name);
				waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
				selectByValue(AUA.Internalyearfolder, Year);
				waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
				selectByValue(AUA.InternalMonthfolder, Month);
				waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
				assertTrue(driver.findElement(By.xpath("//a[contains(.,'TransactionMetric')]")).isDisplayed());
			}

			if (Validate_Report.equalsIgnoreCase("YES")) {
				driver.findElement(By.xpath("//a[contains(.,'TransactionMetric')]")).click();
				Thread.sleep(5000);
				File tempfile = null;
				File tempfile2 = null;
				if (Browser.equalsIgnoreCase("IE")) {
					Thread.sleep(10000);
					rob.keyPress(KeyEvent.VK_CONTROL);
					rob.keyPress(KeyEvent.VK_L);
					rob.keyRelease(KeyEvent.VK_L);
					rob.keyRelease(KeyEvent.VK_CONTROL);
					rob.keyPress(KeyEvent.VK_ENTER);
					rob.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(10000);
					rob.keyPress(KeyEvent.VK_TAB);
					rob.keyRelease(KeyEvent.VK_TAB);
					rob.keyPress(KeyEvent.VK_TAB);
					rob.keyRelease(KeyEvent.VK_TAB);
					rob.keyPress(KeyEvent.VK_DOWN);
					rob.keyRelease(KeyEvent.VK_DOWN);
					rob.keyPress(KeyEvent.VK_DOWN);
					rob.keyRelease(KeyEvent.VK_DOWN);
					rob.keyPress(KeyEvent.VK_ENTER);
					rob.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					rob.keyPress(KeyEvent.VK_HOME);
					rob.keyRelease(KeyEvent.VK_HOME);
					Thread.sleep(5000);
					StringSelection stringSelection = new StringSelection(DownloadFilepath + "\\");
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
					Thread.sleep(5000);
					rob.keyPress(KeyEvent.VK_CONTROL);
					rob.keyPress(KeyEvent.VK_V);
					rob.keyRelease(KeyEvent.VK_V);
					rob.keyRelease(KeyEvent.VK_CONTROL);
					rob.delay(5000);
					rob.keyPress(KeyEvent.VK_ENTER);
					rob.keyRelease(KeyEvent.VK_ENTER);
					rob.delay(1000);
					Thread.sleep(5000);
				}
				if (Browser.equalsIgnoreCase("Chrome")) {
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "TransactionReport";
					System.out.println("Path" + path);
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					tempfile = new File(LatestDownloadedFile("TransactionReport", true));
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					Thread.sleep(8000);
					tempfile = new File(LatestDownloadedFile("TransactionReport", false));
				}
				if (FileName != "") {
					String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
							+ "Reports";
					String name = FileName;
					tempfile2 = new File(DirName + "\\" + name + ".csv");
					Thread.sleep(5000);
					Comparecsvfiles(tempfile, tempfile2);
					BufferedReader reader = new BufferedReader(new FileReader(tempfile));
					int row_count = 0;
					while (reader.readLine() != null) {
						row_count++;
					}
					if (!(row_count > 1)) {
						assertTrue(false);
					}
				}
				if (Verify_NoblankReport != "") {
					BufferedReader reader = new BufferedReader(new FileReader(tempfile));

					int row_count = 0;
					while (reader.readLine() != null) {
						row_count++;
					}
					if (!(row_count <= 1)) {
						assertTrue(false);
					}
				}
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void ChangeSubscription(String ClientName, String UserName, String firstName, String Users,
			String To_Professional, String To_Premium, String TaxCalcOnly, String TaxCalculationsandReturns,
			String ReturnsOnly, String RateFile, String RealTimeRateService, String TaxabilityImpactReport,
			String Add_TestInstance, String DataIntegrity, String DataIntegrity_Value, String LegacyReport,
			String NegoContract, String UpgradeFromStndrd, String ClientVerify, String Password_TI,
			String DashboardStandTransactionUser, String Upgrade_Downgrade_feature,
			String Premium_Professional_Downgrade_Feature, String Subscription_Options, String TaxCalcToReturns,
			String DowngradePremiumtoPro) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		UpgradeProToPremium UTP = PageFactory.initElements(driver, UpgradeProToPremium.class);
		DowngradePremiumtoPro DPP = PageFactory.initElements(driver, DowngradePremiumtoPro.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		DashboardPage DB = PageFactory.initElements(driver, DashboardPage.class);
		TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		new File(".//").getCanonicalPath();
		String clientname = null;
		String parentwindow1 = null, window = null;
		new Robot();
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());
		if (Users.equalsIgnoreCase("BPOCPAClientName")) {
			clientname = BPOCPAClientName;
		} else {
			clientname = ClientName;
		}
		Thread.sleep(2000);
		waitforElement(90, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, clientname);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a")).getText();
		System.out.println(CName);
		assertTrue((clientname).equalsIgnoreCase(CName));
		// JavaScriptclick(driver, VSC.ACTION);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
				+ "')]/../../../td[contains(.,'Registered')]")) {
			WebElement Registered = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
				+ "')]/../../../td[contains(.,'Authorized')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientname
				+ "')]/../../../td[contains(.,'Cancelled')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		}
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.EXPORT, driver);
		CNTID = driver.findElement(By.xpath("//div[@class='span3 ng-binding'][contains(.,'Cloud Customer Id:')]"))
				.getText().split(":")[1].trim();
		JavaScriptclick(driver, VSC.SMBADMIN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		String CurrentVar = driver.findElement(By.xpath("//div[@class='span3 ng-binding'][contains(.,'Current VAR:')]"))
				.getText().split(":")[1].trim();
		waitforElement(90, VSC.EDITCUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.EDITCUSTOMERSUBSCRIPTION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.CUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.CUSTOMERSUBSCRIPTION);
		// Changing Subscription
		// JavaScriptMousehover(driver, PUL.SettingsHover);
		// JavaScriptclick(driver,PUL.EDITSUBSCRIPTIONLINK);
		// waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, MSP.SAVEBUTTON, driver);
		waitforElement(90, DPP.PROFESSIONAL, driver);
		waitforElement(90, MSP.PREMIUMSUB, driver);

		if (To_Professional.equalsIgnoreCase("Yes")) {
			waitforElement(180, DPP.PROFESSIONAL, driver);
			click(DPP.PROFESSIONAL);
		}
		if (To_Premium.equalsIgnoreCase("Yes")) {
			waitforElement(180, MSP.PREMIUMSUB, driver);
			click(MSP.PREMIUMSUB);
		}
		// Changing SubscriptionOptions/AdditionalFeatures
		if (TaxCalcOnly.equalsIgnoreCase("Yes")) {
			click(MSP.TAXCALCONLY_SUB);
		}
		if (TaxCalculationsandReturns.equalsIgnoreCase("Yes")) {
			click(MSP.TAXCALCRETURNS_SUB);
		}
		if (ReturnsOnly.equalsIgnoreCase("Yes")) {
			click(MSP.RETURNSONLY_SUB);
		}
		if (RateFile.equalsIgnoreCase("Yes")) {
			click(MSP.RATEFILECHECKBOX);
		}
		if (RealTimeRateService.equalsIgnoreCase("Yes")) {
			click(MSP.REALTIME);
		}
		if (TaxabilityImpactReport.equalsIgnoreCase("Yes")) {
			click(MSP.TAXIMPACTREPORT_SUB);
		}
		if (Add_TestInstance.equalsIgnoreCase("Yes")) {
			click(MSP.testInstanceradioBtn);
		}
		if (CurrentVar.equalsIgnoreCase("CPABPOAutomationFirm") || CurrentVar.equalsIgnoreCase("CPARefAutomationFirm")
				|| CurrentVar.equalsIgnoreCase("CPARefActive_Test_Firm")
				|| CurrentVar.equalsIgnoreCase("CPABPOActive_Test_Firm")) {
			if (!VerifyObject(driver, RP.DATAINTEGRITY)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		}
		if (DataIntegrity.equalsIgnoreCase("Yes")) {
			click(RP.ADMINDATAINTEGRITY);
			String DR = driver
					.findElement(By.xpath("//select[@id='data-integrity-storage-region']/option[@selected='selected']"))
					.getText();
			assertTrue(DR.equals("US"));
			click(RP.ADMINDATASTORAGRREGION);
			DIRegion = DataIntegrity_Value;
			selectByValue(RP.ADMINDATASTORAGRREGION, DIRegion);
		}
		if ((DataIntegrity_Value.equals("US") || DataIntegrity_Value.equals("EU")) && (DataIntegrity_Value == "")) {
			if (Environment.equalsIgnoreCase("DEV") || Environment.equalsIgnoreCase("DATAINTEGRITY")) {
				String DR = driver
						.findElement(
								By.xpath("//select[@id='data-integrity-storage-region']/option[@selected='selected']"))
						.getText();
				assertTrue(DR.equals(DataIntegrity_Value));
			}
		}

		if (LegacyReport.equalsIgnoreCase("Yes")) {
			click(MSP.LEGACYTRANSACTIONREPORT);
		}

		if (NegoContract == "") {
			JavaScriptclick(driver, MSP.SAVEBUTTON);
			Thread.sleep(1000);
			waitforElement(180, UTP.ATTENTIONALERT, driver);
			Thread.sleep(1000);
			assertTrue(UTP.ATTENTIONALERT.isDisplayed());
			if (UpgradeFromStndrd.equalsIgnoreCase("Yes")) {
				assertTrue(UTP.STNDRDSUBSCRIPTIONCHANGEMSG.isDisplayed());
			} else {
				assertTrue(UTP.SUBSCRIPTIONCHANGEMES.isDisplayed());
			}
			waitforElement(180, UTP.UPGRADEBUTTON, driver);
			JavaScriptclick(driver, UTP.UPGRADEBUTTON);
			Thread.sleep(5000);
			// waitforElement(180, PUL.PASSWORDCONFIRM, driver);
		}
		// *******************DashboardStandTransactionUser
		if (ClientVerify.equalsIgnoreCase("Yes")) {
			userregistration.Logout();
			driver.get(url);
			Thread.sleep(5000);
			if (ClientName != null) {
				userregistration.UserLogin(UserName, "Password!1");
			} else if (BPOCPAClientName != null) {
				Thread.sleep(5000);
				Open(driver, cpaurl);
				userregistration.UserLogin(UserName, "Password!1");
				waitforElement(60, CP.MANAGECLIENTSMENU, driver);
				click(CP.MANAGECLIENTSMENU);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, CP.SEARCHBYCLIENT, driver);
				waitforElement(90, "//tr/td/span/a", driver);
				type(CP.SEARCHBYCLIENT, clientname);
				Thread.sleep(4000);
				waitforElement(90,
						"//tr/td/span/a[contains(.,'" + clientname + "')]/../../../td/a[text()='Edit User Access']",
						driver);
				Thread.sleep(4000);
				WebElement element = driver.findElement(By.xpath("//tr/td/span/a[contains(.,'" + clientname + "')]"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				Thread.sleep(3000);
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
				// window1.
				window = "exists";
				if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
					driver.manage().deleteAllCookies();
					driver.get(url);
					// executor.executeScript("arguments[0].click();", element);
				}
				Thread.sleep(10000);
			} else {
				waitforElement(180, LP.USERNAME, driver);
				type(LP.USERNAME, UserN);
				clear(LP.PASSWORD);
				type(LP.PASSWORD, Password_TI);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptclick(driver, LP.LOGIN);
				} else {
					click(LP.LOGIN);
				}
			}
			if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
				driver.manage().deleteAllCookies();
				driver.get(url);
				waitforElement(120, LP.VTMSACCOUNT, driver);
			}
			waitforElement(180, DB.DASHBOARD, driver);
			if (!DashboardStandTransactionUser.isEmpty()) {
				click(DB.DASHBOARD);
				Thread.sleep(5000);
				waitforElement(180, DB.SELECTCOMPANY, driver);
				assertTrue(DB.SELECTCOMPANY.isDisplayed());
				assertTrue(DB.FILLINGPERIOD.isDisplayed());
				assertTrue(DB.UPDATERESULTS.isDisplayed());
				assertTrue(DB.ZERORETURNS.isDisplayed());
				assertTrue(DB.ZEROSALES.isDisplayed());
				assertTrue(DB.ZEROTAX.isDisplayed());
				// DashboardStandTransactionUser*******************
			}
			if (To_Professional.equalsIgnoreCase("Yes")) {
				waitforElement(180, CNP.CONFIGURE, driver);
				assertTrue(CNP.CONFIGURE.isDisplayed());
			}
			if (To_Premium.equalsIgnoreCase("Yes")) {
				waitforElement(180, MSP.DOCUMENTCENTRE, driver);
				assertTrue(MSP.DOCUMENTCENTRE.isDisplayed());
			}
			// UpgradeRateandProToPremium
			JavaScriptclick(driver, UTP.SETTINGSLINK);
			waitforElement(180, UTP.EDITMYCOMPNAYLINK, driver);
			if (Upgrade_Downgrade_feature != "") {
				if (Upgrade_Downgrade_feature.equalsIgnoreCase("Returns Only")) {
					if (VerifyObject(driver, UTP.ONBOARDPENDING)) {
						assertTrue(UTP.ONBOARDPENDING.isDisplayed());
					} else {
						if (!VerifyObject(driver, UTP.ONBOARDPENDING)) {
							assertTrue(true);
						} else {
							assertTrue(false);
						}
					}
					if (Premium_Professional_Downgrade_Feature.equalsIgnoreCase("Yes")) {
						assertTrue(UTP.UPGRADEPREROnly.isDisplayed());
					} else {
						assertTrue(UTP.UPGRADEPRETCR.isDisplayed());
					}
				} else if (Upgrade_Downgrade_feature.equalsIgnoreCase("Rate File")) {
					assertTrue(UTP.UPGRADEPRETCR.isDisplayed());
					assertTrue(UTP.ONBOARDPENDING.isDisplayed());
					assertTrue(UTP.SUBSCRIPTIONCHANGEDOWNGRADERTRSMSG.isDisplayed());
				} else if (Upgrade_Downgrade_feature.equalsIgnoreCase("Tax Calculations and Returns")) {
					assertTrue(UTP.UPGRADEPRETCR.isDisplayed());
					assertTrue(UTP.ONBOARDPENDING.isDisplayed());
				}
				Thread.sleep(2000);
			}
			// TrialToPremium
			if (Subscription_Options.equalsIgnoreCase("Tax Calculations and Returns")) {
				assertTrue(UTP.ONBOARDPENDING.isDisplayed());
				assertTrue(UTP.UPGRADEPRETCR.isDisplayed());
			}
			if (Subscription_Options.equalsIgnoreCase("Returns Only")) {
				assertTrue(UTP.ONBOARDPENDING.isDisplayed());
				assertTrue(UTP.UPGRADEPREROnly.isDisplayed());
			}
			// DowngradePremiumtoPro
			if (DowngradePremiumtoPro.equalsIgnoreCase("Tax Calculations and Returns")) {
				assertTrue(MSP.downgradesubfrompremiumtcrstoProtcrs.isDisplayed());
				/*
				 * Verification="";
				 * Verification=(VerifyObject(driver,MSP.ONBOARDPENDING))==true?Verification:
				 * Verification+
				 * "Fail, Onboard status not showing Pending in the Settings Page;"; status =
				 * status + Verification; VertexSMB.RowNo =
				 * bussFunction.xResult(Verification,RSheet,"ONBOARDPENDING","",value,NoOfVerify
				 * ++);
				 */

			}
			if (DowngradePremiumtoPro.equalsIgnoreCase("Returns Only")) {
				assertTrue(UTP.UPGRADEPRETCR.isDisplayed());
				assertTrue(MSP.downgradesubfrompremiumtoProReturnsonly.isDisplayed());
			}
			if (TaxCalcToReturns.equalsIgnoreCase("Yes")) {
				assertTrue(PUL.MANAGETAXRETURNS.isDisplayed());
				if (VerifyObject(driver, PUL.MANAGETAXRETURNS)) {
					JavaScriptMousehover(driver, PUL.ReturnsHover);
					JavaScriptclick(driver, PUL.MANAGETAXRETURNS);
					waitforElement(180, PUL.MOSTRECENT, driver);
				}
			}
			// bussFunction.xr_Logout();
		}
		if (Add_TestInstance.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, RP.INVITEUSER);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, RP.SEARCH, driver);
			type(RP.SEARCH, firstName);
			Thread.sleep(2000);
			UserName = driver
					.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'" + firstName + "')]/../td[6]"))
					.getText();

			// if(VertexClientName.contains(".TI")){
			if (UserName.contains(".TI")) {
				String[] data = UserName.split(".TI");
				UserName = data[0];
			}
			// }else{
			// UserName =
			// driver.findElement(By.xpath("//table/tbody/tr[3]/td[contains(text(),'"+VertexClientName+"')]/../td[6]")).getText();
			userregistration.Logout();
			Thread.sleep(2000);
			if (Environment.equalsIgnoreCase("DEV") || Environment.equalsIgnoreCase("AWSDEV")) {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			} else if (Environment.equalsIgnoreCase("STAGE") || Environment.equalsIgnoreCase("AWSSTAGE")) {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			} else if (Environment.equalsIgnoreCase("AWSPROD")) {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			} else {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			}
			Thread.sleep(7000);
			driver.get(TestInstanceSetPwdURL);
			System.out.println(TestInstanceSetPwdURL);
			assertTrue(driver.getTitle().equalsIgnoreCase("Test Instance Password | Vertex Cloud"));
			assertTrue(TI.testInstancePwdlabel.isDisplayed());
			TestInstanceclientName = VertexClientName + ".TI";
			System.out.println("Test Instance Client name :" + TestInstanceclientName);
			Thread.sleep(2000);
			type(TI.testInstanceUsername, TestInstanceUserName);
			type(TI.testInstancepassword, Password_TI);
			Thread.sleep(2000);
			type(TI.testInstanceconfirmpassword, Password_TI);
			Thread.sleep(2000);
			click(TI.setPassword);
		}
		if (window == "exists") {
			driver.close();
			Thread.sleep(5000);
			driver.switchTo().window(parentwindow1);
		}
		Thread.sleep(2000);

	}

	public void AdminConfigurationReport(String ClientName, String TaxpayerName, String ConfigReport_Types,
			String Admin_Push, String Verify_ConfigReport, String Compare_Xlsx) throws Exception {

		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		xUploadAdjustmentFile("Reports");
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(2000);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement Registered = driver.findElement(
					By.xpath("//table/tbody/tr/td/div/a[contains(text(),'" + ClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement Authorized = driver.findElement(
					By.xpath("//table/tbody/tr/td/div/a[contains(text(),'" + ClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else {
			WebElement Intilized = driver.findElement(
					By.xpath("//table/tbody/tr/td/div/a[contains(text(),'" + ClientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Intilized);
		}
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (Admin_Push.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, VSC.SMBADMIN);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(60, "//h2[contains(.,'" + TaxpayerName + "')]", driver);
			waitforElement(120, "//h2[contains(.,'" + TaxpayerName
					+ "')]/../div/div[3]/div[2]/div/div/div/a[contains(.,'Push configuration')]", driver);
			// *****Verifying Oseries Config information in Admin details tab as per
			// POR-17283
			assertTrue(VSC.OSERIESINFO.isDisplayed());
			assertTrue(VSC.TRUSTEDID.isDisplayed());
			assertTrue(VSC.CONFIGPODID.isDisplayed());
			assertTrue(VSC.CONFIGPARTITIONNAME.isDisplayed());
			assertTrue(VSC.CALCPODID.isDisplayed());
			assertTrue(VSC.CALCPPARTITIONNAME.isDisplayed());
			assertTrue((VSC.TRUSTEDID_VALUE.getText()) != "");
			assertTrue((VSC.CALCPODID_VALUE.getText()) != "");
			assertTrue((VSC.CALCPPARTITIONNAME_VALUE.getText()) != "");
			// ******
			driver.findElement(By.xpath("//h2[contains(.,'" + TaxpayerName
					+ "')]/../div/div[3]/div[2]/div/div/div/a[contains(.,'Push configuration')]")).click();
			waitforElement(180, VSC.ADMINSAVECHANGES, driver);
			click(VSC.ADMINSAVECHANGES);
			Thread.sleep(10000);
		} else {
			JavaScriptclick(driver, VSC.SMBCUSTOMERREPORT);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(90, CR.ADMINEXPORT, driver);
			selectByValue(VSC.PaginationCC, "10 per page");
			selectByValue(VSC.PaginationCC, "15 per page");
			// Verifying Customer Date Added Filed as per POR-11567 in 6.0
			Thread.sleep(1000);
			assertTrue(VSC.CUSTOMERDATEADDED.isDisplayed());
			assertTrue(VSC.DATEADDED.isDisplayed());
			assertTrue(CR.DATEADDED_PRODUCT.isDisplayed());
			assertTrue(CR.TAXPRODUCTINFO_PRODUCTCODE.isDisplayed());
			assertTrue(CR.TAXPRODUCTINFO_PRODUCTNAME.isDisplayed());
			assertTrue(CR.TAXPRODUCTINFO_PRODUCTTYPE.isDisplayed());
			assertTrue(CR.TAXPRODUCTINFO_STARTDATE.isDisplayed());
			assertTrue(CR.TAXPRODUCTINFO_ENDDATE.isDisplayed());
			assertTrue(CR.TAXPRODUCTINFO_TAXABILITYCATEGEORY.isDisplayed());
			assertTrue(CR.INCITYLIMITS.isDisplayed());
			assertTrue(CR.PHYSICALLOCATION.isDisplayed());
			scrollUp(driver);
			selectByValue(CR.ADMINTAXPAYER, TaxpayerName);
			Thread.sleep(5000);
			waitforElement(180, CR.ADMINTAXPAYER, driver);
			click(CR.ADMINEXPORT);
			Thread.sleep(5000);
			if (!VerifyObject(driver, PUL.OKERROR_XPATH)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				LatestDownloadedFile("Reports", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(5000);
				LatestDownloadedFile("Reports", false);
			}
			if (Compare_Xlsx != "") {
				// status =
				// XLSXtoXLSXFilesCompare(Filepath,DirName+"\\"+ConfigurationReportPageSheet.getCell(Compare_Xlsx,
				// Data_Row).getContents());
				// xCompareExcelFiles(Filepath, DirName + "\\" + Compare_Xlsx, bussFunction);
			}
			if (Verify_ConfigReport.equalsIgnoreCase("YES")) {
				// xCompareExcelFiles(Filepath,DirName+"\\"+ConfigurationReportPageSheet.getCell(Compare_Xlsx,
				// Data_Row).getContents(),RSheet,bussFunction);
				String s = xGetXLSXCellValue1(Filepath, "TaxArea ID", "Taxpayer Locations");
				assertTrue(!s.isEmpty());
			}
			if (ConfigReport_Types != "") {
				File file = new File(".//");
				file.getCanonicalPath();
			}
		}
	}

	public void ReturnsGenerationreport(String ClientName, String ClientStatus, String TaxpayerName,
			String TaxpayerStatus, String ReturnCount) throws Exception {

		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		click(MC.METRICSLINK);
		waitforElement(180, MC.RGDDWNLD, driver);
		assertTrue(MC.RGDDWNLD.isDisplayed());
		click(MC.RGDDWNLD);
		Thread.sleep(30000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("Metrics", false);
		}
	}

	public void ReturnFillingFrequencyReport(String Status, String TaxpayerEntity, String Subscription,
			String Registartions, String Monthly, String BiMonthly, String Quarterly, String SemiAnnually,
			String Annually, String FirstTwoMonthsOfQuarter, String Occasional, String OccasionalQuarterly,
			String OccasionalSemiAnnually, String OccasionalAnnually) throws Exception {

		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		waitforElement(180, MC.METRICSLINK, driver);
		click(MC.METRICSLINK);
		waitforElement(180, MC.RFDDWNLD, driver);
		assertTrue(MC.RFDDWNLD.isDisplayed());
		click(MC.RFDDWNLD);
		Thread.sleep(30000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("Metrics", false);
		}
	}

	public void AdminPrepayments(String ClientName, String TaxPayer, String Fillingperiod, String DoNotPay,
			String Returns, String State) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		int count = 0;
		new Robot();
		File directory = new File(".//");
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		String DownloadFilepath = xDownloadFiles("AdminPrepaymentsPDFs");
		String nPath = DownloadFilepath;
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(AUA.smbCustomerName, ClientName);

		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]", driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
		} else {
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		click(AUA.CustomerPrepayments);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, VSC.ADMINPREPAYMENTTAXPAYER, driver);
		selectByValue(VSC.ADMINPREPAYMENTTAXPAYER, TaxPayer);
		selectByValue(VSC.FILINGPERIOD, Fillingperiod);
		if (DoNotPay.equalsIgnoreCase("Yes")) {
			Admin_DoNotPay(DoNotPay, State, Returns, "Premium: Tax Calculations and Returns");
		}
		if (VerifyObject(driver, MTR.NOPrepaymentRegistrations)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				count = count + 1;
				selectByValue(VSC.ADMINPREPAYMENTTAXPAYER, TaxPayer);
				Thread.sleep(10000);
				if (count == 5) {
					assertTrue(count == 5);
					break;
				}
				if (!VerifyObject(driver, MTR.NOPrepaymentRegistrations)) {
					report = true;
				}
			} while (report == false);
		} else if (VerifyObject(driver, MTR.Warningmsg)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				count = count + 1;
				selectByValue(MTR.PREPAYMENTSTAXPAYER, TaxPayer);
				Thread.sleep(10000);
				if (count == 20) {
					assertTrue(count == 20);
					break;
				}
				if (!VerifyObject(driver, MTR.Warningmsg)) {
					report = true;
				}
			} while (report == false);
		} else {
			if (!(VerifyObject(driver, MTR.DWNDALL))) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(20000);
					selectByValue(VSC.ADMINPREPAYMENTTAXPAYER, TaxPayer);
					Thread.sleep(5000);
					selectByValue(MTR.FILLINGPERID, Fillingperiod);
					Thread.sleep(5000);
					selectByValue(MTR.PAGESIZE, "100 per page");
					Thread.sleep(5000);
					if ((VerifyObject(driver, MTR.DWNDALL))) {
						report = true;
					}
				} while (report == false);
			}
			assertTrue(MTR.DWNDALL.isDisplayed());
		}
		waitforElement(180, VSC.ADMINPREPAYMENTTAXPAYER, driver);
		selectByValue(VSC.ADMINPREPAYMENTTAXPAYER, TaxPayer);
		waitforElement(180, VSC.ADMINFILLINGPERIOD, driver);
		selectByValue(VSC.ADMINFILLINGPERIOD, Fillingperiod);
		assertTrue(MTR.Jurisdiction.isDisplayed());
		assertTrue(MTR.Prepayments.isDisplayed());
		assertTrue(MTR.ADMINMSTRCTPREPAY.isDisplayed());
		if (VerifyObject(driver, MTR.FileandPay)) {
			assertTrue(MTR.FileandPay.isDisplayed());
		}
		assertTrue(MTR.GENRATE.isDisplayed());
		assertTrue(MTR.DWNDALL.isDisplayed());
		List<WebElement> rowscount = driver.findElements(By.xpath("//table[@id='tblJurisdictions']/tbody"));
		Integer.parseInt(CloudData.get_data("DwdState"));
		for (int k = 1; k <= rowscount.size(); k++) {
			if (driver.findElement(By.xpath("//table[@id='tblJurisdictions']/tbody[" + k + "]/tr/td/strong")).getText()
					.equalsIgnoreCase(State)) {
				if (!(VerifyObject(driver, MTR.DWNDALL))) {
					boolean report = false;
					do {
						driver.navigate().refresh();
						Thread.sleep(20000);
						selectByValue(VSC.ADMINPREPAYMENTTAXPAYER, TaxPayer);
						selectByValue(MTR.FILLINGPERID, Fillingperiod);
						Thread.sleep(10000);
						selectByValue(MTR.PAGESIZE, "100 per page");
						Thread.sleep(5000);
						if ((VerifyObject(driver, MTR.DWNDALL))) {
							report = true;
						}
					} while (report == false);
				}
				assertTrue(MTR.DWNDALL.isDisplayed());
				WebElement dwnldclick = driver.findElement(By.xpath("//table[@id='tblJurisdictions']/tbody[" + k
						+ "]/tr/td/strong[contains(.,'" + State + "')]/../../td/div/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", dwnldclick);
				Thread.sleep(15000);
				if (Browser.equalsIgnoreCase("Chrome")) {
					directory = new File(".//");
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					LatestDownloadedFile("Returns", true);
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					LatestDownloadedFile("Returns", false);
				}
				Thread.sleep(5000);
				DownloadFilepath = xExtractZiReadContents(Filepath, DownloadFilepath + "\\");
				if (State.equalsIgnoreCase("Oklahoma")) {
					DownloadFilepath = xExtractZiReadContents(Filepath, DownloadFilepath + "\\");
					String oknames[] = { "OK Consumers Use Prepayment Return", "OK Sales Tax Prepayment Return",
							"OK Vendor's Use Prepayment Return" };
					for (int df = 0; df <= 2; df++) {
						Filepath = nPath + "\\" + oknames[df];
						DownloadedFileName = oknames[df];
						// DownloadPDFVerification(CloudData.Data_Row);
					}
				} else {
					// DownloadPDFVerification(CloudData.Data_Row);
				}
				k = k + 1;
			}
		}
	}

	public void GeneratePrepaymentsfromAdmin(String ClientName, String TaxPayer, String Fillingperiod)
			throws Exception {

		new Robot();
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(AUA.smbCustomerName, ClientName);

		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]", driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
		} else {
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		click(AUA.CustomerPrepayments);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, VSC.ADMINPREPAYMENTTAXPAYER, driver);
		selectByValue(VSC.ADMINPREPAYMENTTAXPAYER, TaxPayer);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		selectByValue(VSC.FILINGPERIOD, Fillingperiod);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		click(VSC.generateButton);
		Thread.sleep(5000);
	}

	public void Admin_DoNotPay(String DoNotPay, String StateName, String return_name, String Subscription)
			throws Exception {

		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		if (!DoNotPay.isEmpty()) {
			if (DoNotPay.equalsIgnoreCase("Yes")) {
				switch (Subscription) {
				case "Premium: Tax Calculations and Returns( onboarding pending )":
				case "Professional: Tax Calculations and Returns":
				case "Professional: Returns Only":
				case "Premium: Returns Only( onboarding pending )":
					if (VerifyObject(driver, MTR.DoNotPayAll) == false) {
						assertTrue(MTR.DoNotPayAll.isDisplayed());
					} else {
						if (!VerifyObject(driver, MTR.DoNotPayAll)) {
							assertTrue(true);
						} else {
							assertTrue(false);
						}
					}
					break;
				case "Premium: Tax Calculations and Returns":
				case "Premium: Returns Only":
					switch (UtilityScript.xInBetweenDates("01", "19")) {
					case "True":
						if (DoNotPay.equalsIgnoreCase("ALL")) {
							click(MTR.DoNotPayAll);
						} else {
							Thread.sleep(5000);
							waitforElement(180, "//table/tbody/tr/td[contains(.,'" + StateName
									+ "')]/../td/a[contains(.,'Manage Prepayments')]", driver);
							WebElement manageprepayments = driver
									.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + StateName
											+ "')]/../td/a[contains(.,'Manage Prepayments')]"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageprepayments);
							driver.findElement(By.xpath("//table/tbody/tr/td/table/tbody/tr/td[contains(.,\""
									+ return_name + "\")]/../td/input"));
							assertTrue(driver.findElement(By.xpath("//table/tbody/tr/td/table/tbody/tr/td[contains(.,\""
									+ return_name + "\")]/../td/input")).isDisplayed());
						}
						break;
					case "False":
						List<WebElement> Donotfilelist1 = driver.findElements(By.xpath("//table/descendant::input"));
						for (int i = 1; i < Donotfilelist1.size(); i++) {
							if (VerifyObject(driver, driver.findElement(
									By.xpath("//table/descendant::input[@disabled='disabled'][" + i + "]"))) == false) {
								driver.findElement(By.xpath("//table[@id='tblJurisdictions']/descendant::tbody["
										+ (i + 1) + "]/descendant::td[" + (i + 1) + "]")).getText().trim();
								assertTrue(driver
										.findElement(
												By.xpath("//table/descendant::input[@disabled='disabled'][" + i + "]"))
										.isDisplayed());
							} else {
								driver.findElement(By.xpath("//table[@id='tblJurisdictions']/descendant::tbody["
										+ (i + 1) + "]/descendant::td[" + (i + 1) + "]")).getText().trim();
								assertTrue(!(driver
										.findElement(
												By.xpath("//table/descendant::input[@disabled='disabled'][" + i + "]"))
										.isDisplayed()));
							}
						}
						break;
					}
				}
			}
		}

	}

	public void GenerateReturnsfromAdmin(String ClientName, String CompanyName, String FilingPeriod, String Returnform)
			throws Exception {

		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		if (VerifyObject(driver, VSC.GenerateReturns)) {
			Thread.sleep(2000);
			click(VSC.GenerateReturns);
			Thread.sleep(2000);
		} else {
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(360, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, ClientName);
			waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]",
					driver);
			Thread.sleep(2000);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			} else {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			}
			waitforElement(180, VSC.CUSTOMERRETURNS, driver);
			Thread.sleep(2000);
			click(VSC.CUSTOMERRETURNS);
			waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
			Thread.sleep(5000);
			selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, CompanyName);
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			Thread.sleep(2000);
			selectByValue(VSC.FILINGPERIOD, FilingPeriod);
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			Thread.sleep(2000);
			if (Returnform != "") {
				if (VerifyObject(driver, "//td[contains(.,'" + Returnform + "')]/../td[8]/input")) {
					driver.findElement(By.xpath("//td[contains(.,'" + Returnform + "')]/../td[8]/input")).click();
				} else {
					driver.findElement(By.xpath("//td[contains(.,'" + Returnform + "')]/../td[5]/input")).click();
				}
			} else {
				click(VSC.GenerateReturnsChkBox);
			}
			Thread.sleep(3000);
			click(VSC.GenerateReturns);
			Thread.sleep(3000);
			assertTrue(VSC.returngenerationrequested.isDisplayed());
		}
	}

	public void verifyreturnsGeneratedfromAdmin(String ClientName, String TaxPayer, String Fillingperiod)
			throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		int count = 0;
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]", driver);

		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
			// ClientName +
			// "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
			// ClientName +
			// "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
		} else {
			// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
			// ClientName +
			// "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
			WebElement client = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
		}
		waitforElement(180, VSC.CUSTOMERRETURNS, driver);
		Thread.sleep(2000);
		click(VSC.CUSTOMERRETURNS);
		waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
		Thread.sleep(5000);
		selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, TaxPayer);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		selectByValue(VSC.FILINGPERIOD, Fillingperiod);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);

		if (VerifyObject(driver, VSC.GenerateReturnsDisabled)) {
			boolean report = false;
			do {
				count = count + 1;
				if (count == 60) {
					waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
					selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, TaxPayer);
					waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
					Thread.sleep(2000);
					waitforElement(180, VSC.FILINGPERIOD, driver);
					selectByValue(VSC.FILINGPERIOD, Fillingperiod);
					waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
					Thread.sleep(2000);
					break;
				}
				driver.navigate().refresh();
				Thread.sleep(5000);
				waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
				selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, TaxPayer);
				waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
				Thread.sleep(4000);
				waitforElement(120, VSC.FILINGPERIOD, driver);
				selectByValue(VSC.FILINGPERIOD, Fillingperiod);
				waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
				Thread.sleep(2000);
				if (!VerifyObject(driver, VSC.GenerateReturnsDisabled)) {
					Thread.sleep(5000);
					report = true;
				}
			} while (report == false);
		}

		assertTrue(VSC.DOWNLOADALLRETURNSADMIN.isDisplayed());
	}

	public void paymentrequestexportlabel(String ClientName, String CompanyName, String FilingPeriod) throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		if (VerifyObject(driver, VSC.GenerateReturns)) {
			Thread.sleep(2000);
			click(VSC.GenerateReturns);
			Thread.sleep(2000);
		} else {
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(360, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, ClientName);
			waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]",
					driver);
			Thread.sleep(2000);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			} else {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			}
			waitforElement(180, VSC.CUSTOMERRETURNS, driver);
			Thread.sleep(2000);
			click(VSC.CUSTOMERRETURNS);
			waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
			Thread.sleep(5000);
			selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, CompanyName);
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			Thread.sleep(2000);
			selectByValue(VSC.FILINGPERIOD, FilingPeriod);
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			Thread.sleep(2000);

			assertTrue(VSC.paymentrequestExport.isDisplayed());
		}

	}

	@SuppressWarnings("unchecked")
	public void paymentrequestexport(String ClientName, String CompanyName, String FilingPeriod, String fileName)
			throws Exception {
		String path;
		File tempfile = null;
		File tempfile2 = null;
		File directory = new File(".//");
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		if (VerifyObject(driver, VSC.GenerateReturns)) {
			Thread.sleep(2000);
			click(VSC.GenerateReturns);
			Thread.sleep(2000);
		} else {
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(360, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, ClientName);
			waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]",
					driver);
			Thread.sleep(2000);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			} else {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			}

			waitforElement(180, VSC.CUSTOMERRETURNS, driver);
			Thread.sleep(2000);
			click(VSC.CUSTOMERRETURNS);
			waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
			Thread.sleep(5000);
			selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, CompanyName);
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			Thread.sleep(2000);
			selectByValue(VSC.FILINGPERIOD, FilingPeriod);
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			Thread.sleep(2000);

			click(VSC.paymentrequestExport);
			Thread.sleep(10000);
			Robot rob = new Robot();
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(5000);
				rob.keyPress(KeyEvent.VK_ALT);
				Thread.sleep(1000);
				rob.keyPress(KeyEvent.VK_S);
				rob.keyRelease(KeyEvent.VK_ALT);
				rob.keyRelease(KeyEvent.VK_S);
			}
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Returns";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				LatestDownloadedFile("Returns", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("Returns", false);
			}

			if (fileName != "") {
				assertEquals(DownloadedFileName, fileName);
				xGetCSVCellValue(Filepath, "Check Due Date");
				xGetCSVCellValue(Filepath, "Payment Request Category");
				xGetCSVCellValue(Filepath, "Detail Information Flag (Y)");
				String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "Reports";
				String FileName = DirName + "\\" + fileName;
				// FileName = xCSVUDateUpdate(FileName);
				tempfile2 = new File(FileName);
				Thread.sleep(5000);
				ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
				System.out.println(f1);
				ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				System.out.println(f2);
				f1.removeAll(f2);
				assertTrue(f1.size() == 0);
			}

		}
	}

	@SuppressWarnings("unchecked")
	public void ReconReportfromAdmin(String ClientName, String CompanyName, String FilingPeriod, String InputFile)
			throws Exception {

		String file2 = null;
		File tempfile = null;
		File tempfile2 = null;
		String path;
		File directory = new File(".//");
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		if (VerifyObject(driver, VSC.ReconReportAdmin)) {
		} else {
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(360, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, ClientName);
			waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]",
					driver);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			} else {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			}
			waitforElement(180, VSC.CUSTOMERRETURNS, driver);
			click(VSC.CUSTOMERRETURNS);
			waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
			Thread.sleep(5000);
		}
		selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, CompanyName);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		selectByValue(VSC.FILINGPERIOD, FilingPeriod);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		assertTrue(VSC.ReconReportAdmin.isDisplayed());
		click(VSC.ReconReportAdmin);
		Thread.sleep(10000);
		Robot rob = new Robot();
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(5000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdminPortal";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			file2 = LatestDownloadedFile("AdminPortal", true);
			tempfile = new File(file2);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			file2 = LatestDownloadedFile("AdminPortal", false);
			tempfile = new File(file2);
		}

		if (InputFile != "") {
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
			String FileName = DirName + "\\" + InputFile;
			// FileName = xCSVUDateUpdate(FileName);
			tempfile2 = new File(FileName);
			Thread.sleep(5000);
			ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
			System.out.println(f1);
			ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
			System.out.println(f2);
			f1.removeAll(f2);
			assertTrue(f1.size() == 0);
		}

	}

	@SuppressWarnings("unchecked")
	public void CreditReportfromAdmin(String ClientName, String CompanyName, String FilingPeriod, String InputFile)
			throws Exception {

		String file2 = null;
		File tempfile = null;
		File tempfile2 = null;
		String path;
		File directory = new File(".//");
		Robot rob = new Robot();
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		if (VerifyObject(driver, VSC.CreditReportAdmin)) {
		} else {
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(360, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, ClientName);
			waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]",
					driver);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			} else {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			}
			waitforElement(180, VSC.CUSTOMERRETURNS, driver);
			click(VSC.CUSTOMERRETURNS);
			waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
			Thread.sleep(5000);
		}
		selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, CompanyName);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		selectByValue(VSC.FILINGPERIOD, FilingPeriod);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		assertTrue(VSC.CreditReportAdmin.isDisplayed());
		click(VSC.CreditReportAdmin);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(5000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdminPortal";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			file2 = LatestDownloadedFile("AdminPortal", true);
			tempfile = new File(file2);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			file2 = LatestDownloadedFile("AdminPortal", false);
			tempfile = new File(file2);
		}

		if (InputFile != "") {
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
			String FileName = DirName + "\\" + InputFile;
			// FileName = xCSVUDateUpdate(FileName);
			tempfile2 = new File(FileName);
			Thread.sleep(5000);
			ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
			System.out.println(f1);
			ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
			System.out.println(f2);
			f1.removeAll(f2);
			assertTrue(f1.size() == 0);
		}
	}

	// Download return files for the Premium Customer from Admin (Auto generate the
	// returns on 10th day of each month)
	public void DownloadAllReturnfilesadmin() throws Exception {

		String path;
		File directory = new File(".//");
		Robot rob = new Robot();
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);

		click(VSC.PremiumCustomers);
		waitforElement(60, VSC.DownloadRetunrFiles, driver);
		Thread.sleep(1000);
		click(VSC.DownloadRetunrFiles);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, VSC.DownloadRetunrFilesLabel, driver);
		assertTrue(VSC.DownloadRetunrFilesLabel.isDisplayed());
		click(VSC.Downloadlnk);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(5000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Returns";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Returns", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("Returns", false);
		}
		// after downloading the returns file verify that dont file returns selected

		// returns should not display in the return pdf

	}

	public void DownloadReturnsfromAdmin(String ClientName, String CompanyName, String FilingPeriod) throws Exception {
		String path;
		File directory = new File(".//");
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		if (VerifyObject(driver, VSC.DOWNLOADALLRETURNS)) {
		} else {
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(360, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, ClientName);
			waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]",
					driver);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			} else {
				// driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+
				// ClientName +
				// "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			}
			waitforElement(180, VSC.CUSTOMERRETURNS, driver);
			click(VSC.CUSTOMERRETURNS);
			waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
			Thread.sleep(5000);
		}
		selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, CompanyName);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		selectByValue(VSC.FILINGPERIOD, FilingPeriod);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		assertTrue(VSC.DOWNLOADALLRETURNS.isDisplayed());
		click(VSC.DOWNLOADALLRETURNS);
		Thread.sleep(15000);
		Robot rob = new Robot();
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(5000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdminPortal";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("AdminPortal", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("AdminPortal", false);
		}

	}

	public void DoNotFileReturns(String returnForm, String TaxPayer) throws Exception {

		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		UtilityScript.xInBetweenDates("01", "09");
		waitforElement(60, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
		waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, MTR.TAXPAYER, driver);
		waitforElement(60, MTR.MANAGETAXRETURNSLINK, driver);
		Thread.sleep(2000);
		selectByValue(MTR.TAXPAYER, TaxPayer);
		Thread.sleep(5000);

		if (returnForm.equalsIgnoreCase("ALL")) {
			click(MTR.DoNotFileAll);
		} else {
			driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody/descendant::td[contains(.,'"
					+ returnForm + "')]/../td/input")).click();
		}
		Thread.sleep(2000);
		click(MTR.Save);
	}

	public void navigateToManageCustomersTab(String clientName) throws Exception {
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		click(manageCustomersPage.MANAGESMBCUSTLINK);
		click(manageCustomersPage.cloudCustomerNameSearchBox);
		type(manageCustomersPage.cloudCustomerNameSearchBox, clientName);
		driver.findElement(By.xpath("//a[contains(text(),'" + clientName + "')]")).click();
		Thread.sleep(2000);
		click(manageCustomersPage.ADMINDETAILS);
	}

	public void CloudOseriesAdminDetailsTab(String clientName, String VARName) throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		AdminConfigLimit ACL = PageFactory.initElements(driver, AdminConfigLimit.class);
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, clientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientName + "')]", driver);
		WebElement Client = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientName + "')]"));
		JavaScriptclick(driver, Client);
		waitforElement(180, VSC.ADMINDETAILS, driver);
		click(VSC.ADMINDETAILS);
		waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.cloudCustomerIDValue, driver);
		String Customername = getText(VSC.cloudCustomerNameValue).split(":")[1].trim();
		assertTrue(clientName.equalsIgnoreCase(Customername));
		String Status = getText(VSC.statusValue).split(":")[1].trim();
		assertTrue(Status.equalsIgnoreCase("Registered"));
		String Var = getText(VSC.currentVARValue).split(":")[1].trim();
		assertTrue(Var.equalsIgnoreCase(VARName));
		String accounttype = getText(VSC.accountTypeValue).split(":")[1].trim();
		assertTrue(accounttype.equalsIgnoreCase("SMB"));
		String Subscription = getText(VSC.subscriptionValue).split("Subscription:")[1].trim();
		assertTrue(Subscription.equalsIgnoreCase("Professional: Cloud OSeries - Tax Calculations"));
		String TPPC = getText(VSC.taxpayerPartitionConfigurationValue).split(":")[1].trim();
		assertTrue(TPPC.equalsIgnoreCase("Multiple taxpayers per partition"));
		// assertTrue(getText(VSC.oraclePartyNumberValue).equalsIgnoreCase(oraclenumber));

		if (!VerifyObject(driver, ACL.VIEWLIMITS)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}

		if (!VerifyObject(driver, AUA.UndoGoLive)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}

		if (!VerifyObject(driver, VSC.ADMINPUSHCONFIG)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}

		if (!VerifyObject(driver, VSC.CONFIGURATIONSTATUSSECTION)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void verifyManageCustomersCommonTab(String eCloudCustomerNameValue) throws Exception {
		Thread.sleep(2000);
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);

		String aCloudCustomerNameValue = getText(manageCustomersPage.cloudCustomerNameValue);
		eCloudCustomerNameValue = "Cloud Customer Name: " + eCloudCustomerNameValue;
		if (aCloudCustomerNameValue.equals(eCloudCustomerNameValue)) {
			System.out.println("verifyManageCustomersCommonTab - PASS");
		}

		String aCloudCustomerIDValue = getText(manageCustomersPage.cloudCustomerIDValue);
		String aTrustedIDValue = getText(manageCustomersPage.trustedIDValue);
		String aStatusValue = getText(manageCustomersPage.statusValue);
		String aCloudCustomerNumberValue = getText(manageCustomersPage.cloudCustomerNumberValue);
		String aCurrentVARValue = getText(manageCustomersPage.currentVARValue);
		String aAccountTypeValue = getText(manageCustomersPage.accountTypeValue);
		String aSubscriptionValue = getText(manageCustomersPage.subscriptionValue);
		String aApiKeyValue = getText(manageCustomersPage.apiKeyValue);
		String aTaxpayerPartitionConfigurationValue = getText(manageCustomersPage.taxpayerPartitionConfigurationValue);
		String aTaxpayerPartitionsUsedValue = getText(manageCustomersPage.taxpayerPartitionsUsedValue);
		String aApiPasswordValue = getText(manageCustomersPage.apiPasswordValue);
		String aPartitionConfigurationOverrideValue = getText(manageCustomersPage.partitionConfigurationOverrideValue);
		String aOracleAccountNumberValue = getText(manageCustomersPage.oracleAccountNumberValue);
		String aOraclePartyNumberValue = getText(manageCustomersPage.oraclePartyNumberValue);

		System.out.println(aCloudCustomerIDValue);
		System.out.println(aTrustedIDValue);
		System.out.println(aStatusValue);
		System.out.println(aCloudCustomerNumberValue);
		System.out.println(aCurrentVARValue);
		System.out.println(aAccountTypeValue);
		System.out.println(aSubscriptionValue);
		System.out.println(aApiKeyValue);
		System.out.println(aTaxpayerPartitionConfigurationValue);
		System.out.println(aTaxpayerPartitionsUsedValue);
		System.out.println(aApiPasswordValue);
		System.out.println(aPartitionConfigurationOverrideValue);
		System.out.println(aOracleAccountNumberValue);
		System.out.println(aOraclePartyNumberValue);
	}

	public void verifyCompanyInfo(String companyNameNcode) throws Exception {
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		Thread.sleep(5000);
		selectByValue(manageCustomersPage.companyDropDown, companyNameNcode);
		String aCompanyNameValue = getText(manageCustomersPage.companyNameValue);
		String aStartDateValue = getText(manageCustomersPage.startDateValue);
		String aIdNumberValue = getText(manageCustomersPage.idNumberValue);
		String aCompanyCodeValue = getText(manageCustomersPage.companyCodeValue);
		String aEndDateValue = getText(manageCustomersPage.endDateValue);
		String aFilingEntityValue = getText(manageCustomersPage.filingEntityValue);
		String aAddressValue = getText(manageCustomersPage.addressValue);
		String aCompanyStatusValue = getText(manageCustomersPage.companyStatusValue);
		String aGoLiveDateValue = getText(manageCustomersPage.goLiveDateValue);

		System.out.println("acompanyNameValue: " + aCompanyNameValue);
		System.out.println("aStartDateValue: " + aStartDateValue);
		System.out.println("aIdNumberValue: " + aIdNumberValue);
		System.out.println("aCompanyCodeValue: " + aCompanyCodeValue);
		System.out.println("aEndDateValue: " + aEndDateValue);
		System.out.println("aFilingEntityValue: " + aFilingEntityValue);
		System.out.println("aAddressValue: " + aAddressValue);
		System.out.println("aCompanyStatusValue: " + aCompanyStatusValue);
		System.out.println("aGoLiveDateValue: " + aGoLiveDateValue);
	}

	public void locationTable(String[][] eLocationValues) throws Exception {
		PageFactory.initElements(driver, VertexSMBCustomers.class);
		// click(manageCustomersPage.MANAGESMBCUSTLINK);
		Thread.sleep(5000);
		String[][] aLocationValues = new String[2][11];

		int actualLocationLength = aLocationValues.length;
		int expectedLocationLength = eLocationValues.length;
		System.out.println("actualLocationLength:" + actualLocationLength);
		System.out.println("expectedLocationLength:" + expectedLocationLength);

		for (int lrow = 0; lrow <= 1; lrow++) {
			for (int lCol = 0; lCol <= 10; lCol++) {
				int locTableRow = lrow + 2;
				int locTableColumn = lCol + 1;
				aLocationValues[lrow][lCol] = driver
						.findElement(By.xpath("//table[@sort-table='data.taxpayerLocationTable.column']/tbody/tr["
								+ locTableRow + "]/td[" + locTableColumn + "]"))
						.getText();
			}
		}

		// Comparing the expected and actual values.
		if (actualLocationLength == expectedLocationLength) {
			for (int i = 0; i <= 1; i++) {
				for (int j = 0; j <= 10; j++) {
					String aLocation = aLocationValues[i][j];
					String eLocation = eLocationValues[i][j];
					if (eLocation.equals(aLocation)) {
						System.out.println("PASS:");
					} else {
						System.out.println(
								"FAIL: Row: " + i + ",Column: " + j + "Expected-" + eLocation + ",Actual-" + aLocation);
					}
				}
			}
		} else {
			System.out.println("FAIL: Expected and actual length is not matching. Expected array length-"
					+ expectedLocationLength + ",actual array length-" + actualLocationLength);
		}
	}

	public void verifyCustomerConfigurationTabisNotDisplayed(String clientName) throws Exception {
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		CommonFunctions commonFunctions = PageFactory.initElements(driver, CommonFunctions.class);
		click(manageCustomersPage.MANAGESMBCUSTLINK);
		Thread.sleep(2000);
		click(manageCustomersPage.cloudCustomerNameSearchBox);
		type(manageCustomersPage.cloudCustomerNameSearchBox, clientName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'" + clientName + "')]")).click();
		Thread.sleep(5000);
		String objectName = "//a[contains(.,'Customer Configuration')]";
		commonFunctions.VerifyObjectIsNotDisplayed(objectName);
		// click(manageCustomersPage.customerConfigurationTab);
	}

	public void navigateToCustomerDetailsTab(String clientName) throws Exception {
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		click(manageCustomersPage.MANAGESMBCUSTLINK);
		Thread.sleep(2000);
		click(manageCustomersPage.cloudCustomerNameSearchBox);
		type(manageCustomersPage.cloudCustomerNameSearchBox, clientName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'" + clientName + "')]")).click();
		Thread.sleep(2000);
		click(manageCustomersPage.CLOUDCUSTOMERDETAILS);
	}

	// Verify the company table information in Customer Detail tab.
	public void verifyCompanyTableInformation(String[][] eCompanyValues) throws Exception {
		PageFactory.initElements(driver, VertexSMBCustomers.class);
		// click(manageCustomersPage.MANAGESMBCUSTLINK);
		Thread.sleep(5000);
		String[][] aCompanyValues = new String[2][8];

		int actualCompanyLength = aCompanyValues.length;
		int expectedCompanyLength = eCompanyValues.length;
		int Row_count = driver.findElements(By.xpath("//table[2]/tbody/tr")).size();
		int Col_count = driver.findElements(By.xpath("//table[2]/tbody/tr[1]/td")).size();

		for (int cRow = 0; cRow < Row_count; cRow++) {
			for (int cCol = 0; cCol < Col_count; cCol++) {
				int compTableRow = cRow + 1; //// table[@sort-table='data.taxpayerTable.column']/tbody/tr[2]/td[3]
				int compTableColumn = cCol + 1;
				aCompanyValues[cRow][cCol] = driver
						.findElement(By.xpath("//table[@sort-table='data.taxpayerTable.column']/tbody/tr["
								+ compTableRow + "]/td[" + compTableColumn + "]"))
						.getText();
			}
		}

		// Comparing the expected and actual values.
		if (actualCompanyLength == expectedCompanyLength) {
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 1; j++) {
					String aCompany = aCompanyValues[i][j];
					String eCompany = eCompanyValues[i][j];
					if (eCompany.equals(aCompany)) {
						System.out.println("PASS");
					} else {
						System.out.println(
								"FAIL: Row: " + i + ",Column: " + j + "Expected-" + eCompany + ",Actual-" + aCompany);
					}
				}
			}
		} else {
			System.out.println("FAIL: Expected and actual length is not matching. Expected array length-"
					+ expectedCompanyLength + ",actual array length-" + actualCompanyLength);
		}
	}

	public void verifyCalcparityinadmin(String clientName, String export) throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, AdminConfigLimit.class);
		PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		String path;
		File directory = new File(".//");
		Robot rob = new Robot();

		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.cloudoserieslabel.isDisplayed());
		assertTrue(VSC.cloudoseriesselect.isDisplayed());
		assertTrue(VSC.cloudOseriescolumnheader.isDisplayed());
		click(VSC.EXPORTC);
		Thread.sleep(20000);
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(5000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "cloudoseries";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("cloudoseries", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("cloudoseries", false);
		}
		Thread.sleep(40000);
		FileInputStream fis2 = new FileInputStream(Filepath);
		XSSFWorkbook wb2 = new XSSFWorkbook(fis2);
		XSSFSheet sheet2 = wb2.getSheetAt(0); // creating a Sheet object to retrieve object
		Iterator<Row> itr2 = sheet2.iterator(); // iterating over excel file
		while (itr2.hasNext()) {
			Row row = itr2.next();
			Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				int s = cell.getColumnIndex();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					if (s == 11 && row.getRowNum() == 0) {
						// assertEquals(cell.getStringCellValue(), "No");
						System.out.println(cell.getStringCellValue());
					}
					if (s == 14 && row.getRowNum() == 0) {
						System.out.println(cell.getStringCellValue());
						// assertEquals(cell.getStringCellValue(), "100, 120");
					}
					break;
				case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
					System.out.print(cell.getNumericCellValue() + "\t\t\n");
					break;
				}
			}
		}
		selectByValue(VSC.cloudoseriesselect, "Yes");
		type(VSC.SMBCUSTNAMESB, clientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientName + "')]", driver);
		Thread.sleep(2000);
		click(VSC.EXPORTC);
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(5000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "cloudoseries";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("cloudoseries", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("cloudoseries", false);
		}
		Thread.sleep(50000);
		FileInputStream fis = new FileInputStream(Filepath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
		Iterator<Row> itr = sheet.iterator(); // iterating over excel file
		while (itr.hasNext()) {
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				int s = cell.getColumnIndex();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					if (s == 11 && row.getRowNum() == 1) {
						assertEquals(cell.getStringCellValue(), "Yes");
						System.out.println(cell.getStringCellValue());
					}
					if (s == 14 && row.getRowNum() == 1) {
						System.out.println(cell.getStringCellValue());
						assertEquals(cell.getStringCellValue(), "100, 120");
					}
					break;
				case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
					System.out.print(cell.getNumericCellValue() + "\t\t\n");
					break;
				}
			}
		}
		selectByValue(VSC.cloudoseriesselect, "No");
		Thread.sleep(2000);
		click(VSC.EXPORTC);
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(5000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "cloudoseries";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("cloudoseries", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("cloudoseries", false);
		}
		Thread.sleep(50000);
		FileInputStream fis1 = new FileInputStream(Filepath);
		XSSFWorkbook wb1 = new XSSFWorkbook(fis1);
		XSSFSheet sheet1 = wb1.getSheetAt(0); // creating a Sheet object to retrieve object
		Iterator<Row> itr1 = sheet1.iterator(); // iterating over excel file
		while (itr1.hasNext()) {
			Row row = itr1.next();
			Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				int s = cell.getColumnIndex();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					if (s == 11 && row.getRowNum() == 1) {
						assertEquals(cell.getStringCellValue(), "No");
						System.out.println(cell.getStringCellValue());
					}
					if (s == 14 && row.getRowNum() == 1) {
						System.out.println(cell.getStringCellValue());
						// assertEquals(cell.getStringCellValue(), "100, 120");
					}
					break;
				case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
					System.out.print(cell.getNumericCellValue() + "\t\t\n");
					break;
				}
			}
		}
		scrollUp(driver);
		driver.navigate().refresh();
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		clear(VSC.SMBCUSTNAMESB);
		type(VSC.SMBCUSTNAMESB, clientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + clientName + "')]", driver);
		JavaScriptclick(driver, driver.findElement(By.xpath("//td/div/a[contains(.,'" + clientName + "')]")));
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, VSC.ADMINDETAILS);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		String Subscription = getText(VSC.cloudoseriessubscrition);
		System.out.println(Subscription);
		assertTrue(Subscription.equalsIgnoreCase("Professional: Cloud OSeries - Tax Calculations"));
		// assertFalse(VSC.EditSubscription.isDisplayed());
		click(VSC.CustomerListinglink);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		clear(VSC.SMBCUSTNAMESB);
		selectByValue(VSC.Subsciptiondropdwn, "Premium: Returns Only");
		selectByValue(VSC.cloudoseriesselect, "No");
		Thread.sleep(5000);
		String subtext = getText(driver.findElement(By.xpath("//tbody/tr[2]/td[5]"))).trim();
		assertTrue(subtext.equalsIgnoreCase("Premium: Returns Only"));
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[6]"))).trim().equalsIgnoreCase("No"));
		selectByValue(VSC.Subsciptiondropdwn, "Premium: Tax Calculations and Returns");
		selectByValue(VSC.cloudoseriesselect, "No");
		Thread.sleep(5000);
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[5]"))).trim()
				.equalsIgnoreCase("Premium: Tax Calculations and Returns"));
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[6]"))).trim().equalsIgnoreCase("No"));
		selectByValue(VSC.Subsciptiondropdwn, "Professional: Returns Only");
		selectByValue(VSC.cloudoseriesselect, "No");
		Thread.sleep(5000);
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[5]"))).trim()
				.equalsIgnoreCase("Professional: Returns Only"));
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[6]"))).trim().equalsIgnoreCase("No"));
		selectByValue(VSC.Subsciptiondropdwn, "Professional: Tax Calculations");
		selectByValue(VSC.cloudoseriesselect, "No");
		Thread.sleep(5000);
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[5]"))).trim()
				.equalsIgnoreCase("Professional: Tax Calculations"));
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[6]"))).trim().equalsIgnoreCase("No"));
		selectByValue(VSC.Subsciptiondropdwn, "Professional: Tax Calculations and Returns");
		selectByValue(VSC.cloudoseriesselect, "No");
		Thread.sleep(5000);
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[5]"))).trim()
				.equalsIgnoreCase("Professional: Tax Calculations and Returns"));
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[6]"))).trim().equalsIgnoreCase("No"));
		selectByValue(VSC.Subsciptiondropdwn, "Standard");
		selectByValue(VSC.cloudoseriesselect, "No");
		Thread.sleep(5000);
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[5]"))).trim().equalsIgnoreCase("Standard"));
		assertTrue(getText(driver.findElement(By.xpath("//tbody/tr[2]/td[6]"))).trim().equalsIgnoreCase("No"));
		selectByValue(VSC.Subsciptiondropdwn, "Premium: Tax Calculations and Returns");
		selectByValue(VSC.cloudoseriesselect, "Yes");
		Thread.sleep(5000);
		assertTrue(VSC.norecordsfound.isDisplayed());

	}

	public void navigateToAdminDetailsTab(String clientName) throws Exception {
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		click(manageCustomersPage.MANAGESMBCUSTLINK);
		Thread.sleep(2000);
		click(manageCustomersPage.cloudCustomerNameSearchBox);
		type(manageCustomersPage.cloudCustomerNameSearchBox, clientName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'" + clientName + "')]")).click();
		Thread.sleep(2000);
		click(manageCustomersPage.ADMINDETAILS);

	}

	public void logIntoOSeriesFromAdminDetailsTab() throws Exception {
		String parentwindow = null;
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		CommonFunctions commonFunctionsPage = PageFactory.initElements(driver, CommonFunctions.class);

		Thread.sleep(2000);
		List<WebElement> li = driver.findElements(By.xpath("//button[contains(text(),'Show Password')]"));
		Thread.sleep(2000);
		li.get(0).click();
		Thread.sleep(2000);
		String oSeriesPassword = getText(manageCustomersPage.oSeriesPassword);
		String oSeriesUserName = getText(manageCustomersPage.oSeriesUserNameValue);
		click(manageCustomersPage.launchSiteButton);
		Thread.sleep(10000);
		parentwindow = driver.getWindowHandle();
		Thread.sleep(2000);
		Set<String> window = driver.getWindowHandles();
		window.remove(parentwindow);
		driver.switchTo().window(window.iterator().next());
		Thread.sleep(3000);
		type(manageCustomersPage.oSeriesLoginUserName, oSeriesUserName);
		type(manageCustomersPage.oSeriesLoginPassword, oSeriesPassword);
		Thread.sleep(1000);
		click(manageCustomersPage.oSeriesLoginButton);
		Thread.sleep(25000);
		commonFunctionsPage.VerifyObjectIsDisplayed("//span[contains(text(),'My Enterprise')]");

	}

	public void navigateToEditSubscriptionPage() throws Exception {
		Thread.sleep(2000);
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		click(manageCustomersPage.EDITCUSTOMERSUBSCRIPTION);

	}

	// Verify EditCompanyConfiguration button is not displayed
	public void verifyEditCompanyConfigurationisNotDisplayed(String clientName) throws Exception {
		CommonFunctions commonFunctions = PageFactory.initElements(driver, CommonFunctions.class);
		String objectName = "//a[contains(.,'Edit Company Configuration')]";
		commonFunctions.VerifyObjectIsNotDisplayed(objectName);
	}

	public void verifyExportFileHasNoCompanyInformation(String expectedFile, String actualSheet, String companyCode)
			throws Exception {
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		click(manageCustomersPage.EXPORTC);
		UtilityScript utilityScripts = PageFactory.initElements(driver, UtilityScript.class);
		utilityScripts.compareCustomerDetails(expectedFile, actualSheet, companyCode);

	}

	public void verifyAdminDetailsTabForCloudOSeries() throws Exception {
		CommonFunctions commonFunctions = PageFactory.initElements(driver, CommonFunctions.class);

		commonFunctions.VerifyObjectIsNotDisplayed("//button[contains(text(),'View Config Limits')]");
		commonFunctions.VerifyObjectIsNotDisplayed("//button[contains(text(),'Undo GoLive')]");
		commonFunctions.VerifyObjectIsNotDisplayed("//a[contains(text(),'Push configuration')]");
		commonFunctions.VerifyObjectIsNotDisplayed("//div[@class='config-company-container ng-scope']");

	}

	public void logIntoOSeriesReadOnlyFromAdminDetailsTab() throws Exception {
		String parentwindow = null;
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		Thread.sleep(2000);
		List<WebElement> li = driver
				.findElements(By.xpath("//a[contains(text(), 'Show Read-Only UserID & Password')]"));
		Thread.sleep(2000);
		li.get(0).click();
		Thread.sleep(2000);
		String oSeriesPassword = getText(manageCustomersPage.oSeriesPassword);
		String oSeriesUserName = getText(manageCustomersPage.oSeriesUserNameValue);
		int a = oSeriesUserName.indexOf("Copy");
		String aOSeriesUserName = oSeriesUserName.substring(9, a);

		int b = oSeriesPassword.indexOf("Copy");
		String aOSeriesPassword = oSeriesPassword.substring(10, b);
		click(manageCustomersPage.launchSiteButton);
		Thread.sleep(20000);
		parentwindow = driver.getWindowHandle();
		Thread.sleep(2000);
		Set<String> window = driver.getWindowHandles();
		window.remove(parentwindow);
		driver.switchTo().window(window.iterator().next());
		Thread.sleep(3000);
		type(manageCustomersPage.oSeriesLoginUserName, aOSeriesUserName);
		type(manageCustomersPage.oSeriesLoginPassword, aOSeriesPassword);
		Thread.sleep(1000);
		click(manageCustomersPage.oSeriesLoginButton);
	}

	public void logIntoOSeriesReadWriteFromAdminDetailsTab() throws Exception {
		String parentwindow = null;
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		Thread.sleep(2000);
		List<WebElement> li = driver
				.findElements(By.xpath("//a[contains(text(), 'Show Read/Write UserID & Password')]"));
		Thread.sleep(2000);
		li.get(0).click();
		Thread.sleep(2000);
		type(manageCustomersPage.lastPassPassword, "X%N697Zovk^e");
		click(manageCustomersPage.seeReadWriteUserButton);
		String oSeriesUserName = manageCustomersPage.oSeriesReadWriteUser.getText();
		System.out.println("kn text: " + oSeriesUserName);
		int a = oSeriesUserName.indexOf("Copy");
		String aOSeriesUserName = oSeriesUserName.substring(9, a);
		System.out.println("aOSeriesUserName: " + aOSeriesUserName);

		int b = oSeriesUserName.indexOf("Password: ");
		int c = oSeriesUserName.length();
		String aOSeriesPassword = oSeriesUserName.substring(b + 10, c - 4);
		System.out.println("aOSeriesPassword: " + aOSeriesPassword);
		click(manageCustomersPage.closeButton);
		click(manageCustomersPage.launchSiteButton);
		Thread.sleep(20000);
		parentwindow = driver.getWindowHandle();
		Thread.sleep(2000);
		Set<String> window = driver.getWindowHandles();
		window.remove(parentwindow);
		driver.switchTo().window(window.iterator().next());
		Thread.sleep(3000);
		type(manageCustomersPage.oSeriesLoginUserName, aOSeriesUserName);
		type(manageCustomersPage.oSeriesLoginPassword, aOSeriesPassword);
		Thread.sleep(1000);
		click(manageCustomersPage.oSeriesLoginButton);
	}

	public void verifyOseriesReadOnlyUserAccess() throws Exception {
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		CommonFunctions commonFunctionsPage = PageFactory.initElements(driver, CommonFunctions.class);
		Thread.sleep(50000);
		click(manageCustomersPage.oSeriesMyEnterprise);
		click(manageCustomersPage.oSeriesTaxpayers);
		commonFunctionsPage.VerifyObjectIsNotDisplayed("//a[contains(text(), 'Add')]");
	}

	public void verifyOseriesReadWriteUserAccess() throws Exception {
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		CommonFunctions commonFunctionsPage = PageFactory.initElements(driver, CommonFunctions.class);
		Thread.sleep(50000);
		click(manageCustomersPage.oSeriesMyEnterprise);
		click(manageCustomersPage.oSeriesTaxpayers);
		commonFunctionsPage.VerifyObjectIsDisplayed("//a[contains(text(), 'Add')]");
	}

	public void selectOSeriesServices(String oSeriesServices) throws Exception {
		@SuppressWarnings("unused")
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		// selectByValue(manageCustomersPage.selectingOSeriesServices, oSeriesServices);

		Select selectingOSeriesServices = new Select(driver
				.findElement(By.xpath("//div[@data-testid='bus-event-select']/div[@class='ant-select-selector']")));
		selectingOSeriesServices.selectByVisibleText(oSeriesServices);

	}
	
	// Navigate to Partition Configuration tab from Manage Customers.
	public void navigateToPartitionConfigurationTab(String clientName) throws Exception {
		VertexSMBCustomers manageCustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		click(manageCustomersPage.MANAGESMBCUSTLINK);
		click(manageCustomersPage.cloudCustomerNameSearchBox);
		type(manageCustomersPage.cloudCustomerNameSearchBox, clientName);
		driver.findElement(By.xpath("//a[contains(text(),'" + clientName + "')]")).click();
		Thread.sleep(2000);
		click(manageCustomersPage.PartitonConfiguration);
	}
	
	//Select Partition value from Partition dropdown in Partition Configuration page
	public void verifyPartitionConfiguration() throws Exception {
		CommonFunctions CF = new CommonFunctions();
		Select selectingPartiton = new Select(driver.findElement(By.xpath("//select[@id='partitionList']")));
		selectingPartiton.selectByVisibleText("knTest1 (-)");
		CF.VerifyObjectIsDisplayed("//a[contains(text(),'Calculation Precedence')]");
		CF.VerifyObjectIsDisplayed("//a[contains(text(),'Address Cleansing')]");
		CF.VerifyObjectIsDisplayed("//a[contains(text(),'TaxGIS')]");
		CF.VerifyObjectIsDisplayed("//a[contains(text(),'Calculation')]");
		CF.VerifyObjectIsDisplayed("//a[contains(text(),'Certificate Center')]");
		CF.VerifyObjectIsDisplayed("//a[contains(text(),'Entity Limits')]");
		CF.VerifyObjectIsDisplayed("//a[contains(text(),'Certificates')]");
		CF.VerifyObjectIsDisplayed("//a[contains(text(),'General')]");
	}
	

}
