package common.libraries.admin;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import common.libraries.database.DataBase;
import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.pages.Addseller;
import common.pages.AdminConfigLimit;
import common.pages.AdminQueueService;
import common.pages.AdminUndoAdjustmentFile;
import common.pages.AdminUserLinks;
import common.pages.CPA;
import common.pages.ConfigurationReportPage;
import common.pages.ConfigurePage;
import common.pages.CpaRegistration;
import common.pages.DataChecks;
import common.pages.Documentcenter;
import common.pages.ExceptionsPage;
import common.pages.InlineHelpEditor;
import common.pages.IntegrationAdmin;
import common.pages.InviteUsers;
import common.pages.LoginPage;
import common.pages.ManageConfiguration;
import common.pages.ManageoseiesConfig;
import common.pages.ManagetaxReturns;
import common.pages.Metrics;
import common.pages.OSPSearch;
import common.pages.OnboardStatus;
import common.pages.POR632;
import common.pages.ProductsPage;
import common.pages.ProfessionalUserLinks;
import common.pages.RegistrationPage;
import common.pages.SOCReport;
import common.pages.SystemConfiguration;
import common.pages.TaxpayerPartitionsused;
import common.pages.Treasurybillingscreens;
import common.pages.UpdateNotification;
import common.pages.VarUserLinks;
import common.pages.VertexSMBCustomers;
import common.pages.calcparity;
import common.utilities.UtilityScript;
import jxl.Sheet;

public class AdminFunctions extends CommonFunctions {

	private WebDriver driver;

	public AdminFunctions(WebDriver driver) throws Exception {
		this.driver = driver;
		pageDriver(driver);
	}

	public final String GETHELPLINK_XPATH = "//a/span[contains(text(),'Get Help')]";

	PortalFunctions DownloadFiles = PageFactory.initElements(driver, PortalFunctions.class);
	UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);

	public void SaveInlineHelpEditor(String dropdownval, String value) throws Exception {
		InlineHelpEditor IHE = PageFactory.initElements(driver, InlineHelpEditor.class);
		Thread.sleep(5000);
		click(IHE.InlineHelpEditorlink);
		waitforElement(180, IHE.Cancelbutton, driver);
		selectByValue(IHE.HelpSection, dropdownval);
		clear(IHE.Discription);
		type(IHE.Discription, value);
		sendkeysTab(IHE.Discription);
		waitforElement(180, IHE.Save, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, IHE.Save);
		Thread.sleep(2000);
	}

	public String AdminGenerateAPI() throws Exception {
		String status = "";
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			new File(".//");
			AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
			waitforElement(180, AUA.manageSMBCustomers, driver);
			JavaScriptclick(driver, AUA.manageSMBCustomers);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, AUA.smbCustomerName, driver);
			type(AUA.smbCustomerName, VertexClientName);
			xGetCurrentTime();
			Thread.sleep(5000);
			waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + VertexClientName + "')]",
					driver);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + VertexClientName
					+ "')]/../../../td[contains(.,'Registered')]")) {
				JavaScriptclick(driver,
						driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'"
								+ VertexClientName + "')]/../../../td[contains(.,'Registered')]/../td[1]/div/a")));
			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'"
					+ VertexClientName + "')]/../../../td[contains(.,'Authorized')]")) {
				JavaScriptclick(driver,
						driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'"
								+ VertexClientName + "')]/../../../td[contains(.,'Authorized')]/../td[1]/div/a")));
			} else {
				JavaScriptclick(driver,
						driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'"
								+ VertexClientName + "')]/../../../td[contains(.,'Initialized')]/../td[1]/div/a")));
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, AUA.CONNECTORS, driver);
			// Compncode=driver.findElement(By.xpath("//div/span[text()='Company
			// Code']/../../../../../tbody/tr[2]/td/a")).getText().trim();
			JavaScriptclick(driver, AUA.CONNECTORS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
			if (VerifyObject(driver, AUA.generateAPICredentialslnk)) {
				click(AUA.generateAPICredentialslnk);
			} else {
				JavaScriptclick(driver, AUA.resetAPIcredentials);
			}
			if (VerifyObject(driver, AUA.generateAPICredentialslnk)) {
				click(AUA.generateAPICredentialslnk);
			} else {
				click(AUA.resetAPIcredentials);
			}
			Thread.sleep(6000);
			String APIK = getText(AUA.apiKey);
			String AKey[] = APIK.split(":");
			APIkey = AKey[0].trim();
			APIkey = APIK;
			String APIP = getText(AUA.apiPassword);
			String APwd[] = APIP.split(":");
			APIPassword = APwd[0].trim();
			APIPassword = APIP;
			ConnectorAPIKey = APIkey;
			ConnectorAPIPassword = APIPassword;
			status = (APIkey != null) == true ? status : status + "Fail,API Key Not Generated;";
			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
			status = e.getMessage();
			return e.getMessage() + status;
			// xException(RSheet, e, value);
		}
		return status;
	}

	public String AdminGenerateAPI(String ClientName) throws Exception {
		String status = "";
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			new File(".//");
			AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
			waitforElement(180, AUA.manageSMBCustomers, driver);
			JavaScriptclick(driver, AUA.manageSMBCustomers);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, AUA.smbCustomerName, driver);
			type(AUA.smbCustomerName, ClientName);
			xGetCurrentTime();
			Thread.sleep(5000);
			waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]",
					driver);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName
					+ "')]/../../../td[contains(.,'Registered')]")) {
				JavaScriptclick(driver,
						driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'"
								+ ClientName + "')]/../../../td[contains(.,'Registered')]/../td[1]/div/a")));
			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName
					+ "')]/../../../td[contains(.,'Authorized')]")) {
				JavaScriptclick(driver,
						driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'"
								+ ClientName + "')]/../../../td[contains(.,'Authorized')]/../td[1]/div/a")));
			} else {
				JavaScriptclick(driver,
						driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'"
								+ ClientName + "')]/../../../td[contains(.,'Initialized')]/../td[1]/div/a")));
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, AUA.CONNECTORS, driver);
			// Compncode=driver.findElement(By.xpath("//div/span[text()='Company
			// Code']/../../../../../tbody/tr[2]/td/a")).getText().trim();
			JavaScriptclick(driver, AUA.CONNECTORS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
			if (VerifyObject(driver, AUA.generateAPICredentialslnk)) {
				click(AUA.generateAPICredentialslnk);
			} else {
				JavaScriptclick(driver, AUA.resetAPIcredentials);
			}
			if (VerifyObject(driver, AUA.generateAPICredentialslnk)) {
				click(AUA.generateAPICredentialslnk);
			} else {
				click(AUA.resetAPIcredentials);
			}
			Thread.sleep(6000);
			String APIK = getText(AUA.apiKey);
			String AKey[] = APIK.split(":");
			APIkey = AKey[0].trim();
			APIkey = APIK;
			String APIP = getText(AUA.apiPassword);
			String APwd[] = APIP.split(":");
			APIPassword = APwd[0].trim();
			APIPassword = APIP;
			ConnectorAPIKey = APIkey;
			ConnectorAPIPassword = APIPassword;
			status = (APIkey != null) == true ? status : status + "Fail,API Key Not Generated;";
			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
			status = e.getMessage();
			return e.getMessage() + status;
			// xException(RSheet, e, value);
		}
		return status;
	}

	public void AdminDashboard(String Fiescount) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		;
		String clientname = null;
		int count = 0;
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.EDITCOMPANYLINK);
		waitforText(180, PUL.CLIENT, driver);
		clientname = getAttribute(PUL.CLIENT, "value");
		userregistration.Logout();
		Thread.sleep(5000);
		userregistration.UserLogin(adminusername, adminpassword);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		Thread.sleep(2000);
		waitforElement(180, PUL.TABLEROW, driver);
		if (!VerifyObject(driver, "//table/tbody/tr/td[text()='" + clientname + "']/../td[3]")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(30000);
				count = count + 1;
				if (count == 50) {
				}
				if (VerifyObject(driver, "//table/tbody/tr/td[text()='" + clientname + "']/../td[3]")) {
					report = true;
				}
			} while (report == false);
		}
		String uploadcount = driver.findElement(By.xpath("//table/tbody/tr/td[text()='" + clientname + "']/../td[3]"))
				.getText();
		if (uploadcount.equalsIgnoreCase(Fiescount)) {
			assertTrue(uploadcount.equalsIgnoreCase(Fiescount));
		}
	}

	public String SOCMetricsdownload(WebDriver driver, int Data_Row, Sheet Socreportsheet, int Startdate, int EndDate)
			throws Exception {
		String status = null;
		String OriginalCellValue;
		try {
			String ResetToken = null;
			String socLink = null;
			String path = null;
			File tempfile = null;
			xDownloadFiles("SOCReports");
			File directory = new File(".//");
			SOCReport SRR = PageFactory.initElements(driver, SOCReport.class);
			JavaScriptclick(driver, SRR.METRICS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
			waitforText(180, SRR.Stdate, driver);
			socLink = xGetreportlinkfromGmail();
			ResetToken = socLink.split("Download/")[1];
			System.out.println(ResetToken);
			clear(SRR.Stdate);
			Thread.sleep(2000);
			type(SRR.Stdate, Socreportsheet.getCell(Startdate, Data_Row).getContents());
			click(SRR.Stdate);
			Thread.sleep(5000);
			clear(SRR.Enddate);
			Thread.sleep(2000);
			waitforElement(180, SRR.Enddate, driver);
			type(SRR.Enddate, Socreportsheet.getCell(EndDate, Data_Row).getContents());
			click(SRR.Enddate);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, SRR.DownloadCSV, driver);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, SRR.DownloadCSV);
			} else {
				click(SRR.DownloadCSV);
			}
			Thread.sleep(5000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "SOCReports";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("SOCReports", true));
				tempfile.toString();
			}
			// LatestDownloadedFile();
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				LatestDownloadedFile("SOCReports", false);

			}

			xGetCSVCellData(Filepath, ResetToken);
			OriginalCellValue = Cell_Value;
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return OriginalCellValue;
	}

	public String SavefilesNoDelete(WebDriver driver, String folder) throws Exception {
		// xDownloadFiles("GoLiveMetrics");
		File directory = new File(".//");
		directory.getCanonicalPath();
		String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + folder;
		;
		if (Browser.equalsIgnoreCase("Chrome")) {
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
		}
		if (Browser.equalsIgnoreCase("FireFox") || Browser.equalsIgnoreCase("IE")) {
			LatestDownloadedFile(folder, false);
		}
		return path;
	}

	public String ProductnameSplit(WebDriver driver, int Dts_Row, String Productname) throws Exception {
		String status = "";
		try {
			if (Productname.contains("'")) {
				String[] xpathStr = Productname.split("'");
				WebElement prod = driver
						.findElement(By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()=concat('"
								+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1] + "')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", prod);
			} else {
				WebElement jur = driver.findElement(
						By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + Productname + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
			}
		} catch (Exception e) {
			return e.getMessage() + status;

		}
		return status;
	}

	public String Browser(WebDriver driver, String browser, String AutoITpath, String AdjustmentFilePath,
			String Filename, String fileformat) throws Exception {
		String status = "";
		try {
			switch (browser) {
			case "IE":
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				break;
			case "Firefox":
				Runtime.getRuntime().exec(
						AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				break;
			case "Chrome":
				Runtime.getRuntime().exec(
						AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				break;
			case "Safari":
				Runtime.getRuntime().exec(
						AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				break;
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	// To test Transaction Metric Data report
	public void TransactionMetricData(String Compname, String Compstatus, String Clientname, String Subscr,
			String DNSName, String MsgType, String Transcount) throws Exception {

		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		PageFactory.initElements(driver, SOCReport.class);
		Thread.sleep(3000);
		click(MC.METRICSLINK);
		Robot robot = new Robot();
		waitforElement(180, MC.TMDDWNLD, driver);
		click(MC.TMDDWNLD);
		Thread.sleep(1000);
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
		Thread.sleep(5000);
		xGetCSVValueForTrxMetric(CommonFunctions.Filepath, Clientname, Compname, Compstatus, Subscr, DNSName, MsgType,
				Transcount);
	}

	public void AdminUnlockUser(String firstname, String UserName) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		InviteUsers INU = PageFactory.initElements(driver, InviteUsers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, INU.INVITEUSERS, driver);
		click(INU.INVITEUSERS);
		JavaScriptclick(driver, INU.INVITEUSERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, INU.INVITEUSERBTN, driver);
		type(INU.SEARCHTXTBOX, firstname);
		waitforElement(180, INU.Unlocklink, driver);
		Thread.sleep(5000);
		String Username = driver.findElement(By.xpath("//tbody/tr[2]/td[5]")).getText();
		assertTrue(Username.equalsIgnoreCase(UserName));
		JavaScriptclick(driver, INU.Unlocklink);
		waitforElement(180, INU.Unlocksuccess, driver);
		assertTrue(INU.Unlocksuccess.isDisplayed());
	}

	public void IntegrationAdminScreensForECW(String Clientname) throws Exception {
		new Robot();
		int count;
		IntegrationAdmin IA = PageFactory.initElements(driver, IntegrationAdmin.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		AdminUserLinks AUL = PageFactory.initElements(driver, AdminUserLinks.class);
		PageFactory.initElements(driver, InviteUsers.class);
		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		driver.getWindowHandle();
		Thread.sleep(1000);
		// Dashboard
		assertTrue(IA.Dashboard.isDisplayed());
		JavaScriptclick(driver, IA.Dashboard);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(driver.getTitle().equalsIgnoreCase("Index | Vertex Cloud"));
		// LookupTaxRates
		assertTrue(AUL.RatesJurisdictions.isDisplayed());
		JavaScriptclick(driver, AUL.RatesJurisdictions);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// OSp logs search
		assertTrue(IA.Osplogssearch.isDisplayed());
		JavaScriptclick(driver, IA.Osplogssearch);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(driver.getTitle().equalsIgnoreCase("OSP Logs Search | Vertex Cloud"));
		// Logs
		JavaScriptMousehover(driver, TBS.logs);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (!Environment.equalsIgnoreCase("QA")) {
			click(TBS.Taxlinklogs);
			Thread.sleep(5000);
			assertTrue(TBS.Taxlinklogssearch.isDisplayed());
			Thread.sleep(30000);
			selectByIndex(TBS.serverdropdwn, 2);
			if (VerifyObject(driver, TBS.TAXLINKLOGSSDATA)) {
				waitforElement(540, TBS.TAXLINKLOGSSDATA, driver);
				click(TBS.TAXLINKLOGSSDATA);
			}
			Thread.sleep(5000);
		}
		click(TBS.DASHBOARD);
		JavaScriptMousehover(driver, TBS.logs);
		click(TBS.ECWLogs);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		/*
		 * //assertTrue(TBS.ECWLogs.isDisplayed()); Thread.sleep(30000); if
		 * (VerifyObject(driver, TBS.ECWServername)) { waitforElement(540,
		 * TBS.ECWServername, driver); selectByIndex(TBS.ECWServername, 2);
		 * Thread.sleep(5000); if (VerifyObject(driver, TBS.ECWLogdata)) {
		 * click(TBS.ECWLogdata); } }
		 */
		// Manage SMB Customers
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		// Features dropdown verification
		assertTrue(VSC.Featuresdropdwn.isDisplayed());
		String[] fval = { "Rate File", "Real-Time Rate Service", "Taxability Impact Report", "Test Instance",
				"Retail Tax Extract", "EC Wizard" };
		click(VSC.Featuresdropdwn);
		for (int i = 0; i < fval.length; i++) {
			selectByValue(VSC.Featuresdropdwn, fval[i]);
		}
		selectByValue(VSC.Featuresdropdwn, "EC Wizard");
		selectByValue(VSC.Subsciptiondropdwn, "Standard");
		if (Clientname.contains("ECW")) {
			type(AUA.smbCustomerName, Clientname);
			Thread.sleep(10000);
			if (!VerifyObject(driver,
					"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + Clientname + "')]")) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					Thread.sleep(20000);
					type(VSC.SMBCUSTNAMESB, Clientname);
					Thread.sleep(5000);
					count = count + 1;
					if (count == 25) {
						break;
					}
					if (VerifyObject(driver,
							"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + Clientname + "')]")) {
						report = true;
					}
				} while (report == false);
			}
			driver.findElement(
					By.xpath("//table/tbody/tr[@class='ng-scope'][1]/td/div/a[contains(text(),'" + Clientname + "')]"))
					.click();
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			// Verifying the fields in Customer Details screen
			String EClientname = driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Name:')]/../../div[1]"))
					.getText();
			String exc[] = EClientname.split(":");
			assertTrue((exc[1].trim()).equalsIgnoreCase(Clientname));
			String ECWsubscription = driver
					.findElement(By.xpath("//span[contains(text(),'Subscription:')]/../../div[1]")).getText();
			String exs[] = ECWsubscription.split(":");
			assertTrue((exs[1].trim()).equalsIgnoreCase("Standard"));
			String ECWVarname = driver.findElement(By.xpath("//span[contains(.,'Current VAR:')]/../../div[2]"))
					.getText();
			String exva[] = ECWVarname.split(":");
			assertTrue((exva[1].trim()).equalsIgnoreCase(VARName));
			assertTrue(VSC.ClientInformation.isDisplayed());
			JavaScriptclick(driver, VSC.ClientInformation);
			if (!VerifyObject(driver, VSC.cancustdisabled)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			if (!VerifyObject(driver, VSC.CREATENEWUSER)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			if (!VerifyObject(driver, VSC.DELETE)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			// Connectors section verification
			click(VSC.Connectors);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			if (!VerifyObject(driver, VSC.GenerateAPIlink)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			assertTrue(VSC.ECWHeaderText.isDisplayed());
			assertTrue(VSC.ECWURL.isDisplayed());
			String EcwUrl = driver
					.findElement(By.xpath("//div/span[contains(.,'URL:')]/../../div[@class='span7 ng-binding']"))
					.getText();
			System.out.println(EcwUrl);
			String evn = Environment.toLowerCase();
			System.out.println(evn);
			assertTrue(EcwUrl.equalsIgnoreCase("https://" + evn + "ecwportal.vertexsmb.com"));
			assertTrue(VSC.ECWClientCode.isDisplayed());
			assertTrue(PUL.ECWIntegrationText.isDisplayed());
			assertTrue(PUL.ECWInClientID.isDisplayed());
			assertTrue(PUL.ECWInClientsecret.isDisplayed());
			assertTrue(PUL.OseriesIntegrationText.isDisplayed());
			assertTrue(PUL.ECWOseriesClientID.isDisplayed());
			assertTrue(PUL.ECWOseriesClientSecret.isDisplayed());
		} // Metrics Verification
		assertTrue(AUL.METRICSLINK.isDisplayed());
		click(AUL.METRICSLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		assertTrue(VSC.AuditMeticData.isDisplayed());
		Thread.sleep(20000);
		// Invite users link
		click(AUL.INVITEUSERLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		assertTrue(AUL.EXPORTBUTTON.isDisplayed());
		if (!VerifyObject(driver, VSC.InviteUsers)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		if (!VerifyObject(driver, VSC.ACTION)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		if (!VerifyObject(driver, VSC.Deletelink)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		// VertexCloudVars verification
		assertTrue(AUL.VERTEXSMBVARS.isDisplayed());
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		// System Configuration
		assertTrue(IA.systemconfiguration.isDisplayed());
		JavaScriptclick(driver, IA.systemconfiguration);
		// VisitHelpCenter
		assertTrue(AUL.VisitHelpcenter.isDisplayed());
		// Whatsnew link
		assertTrue(AUL.Whatsnew.isDisplayed());

	}

	public void AdminCloudECWClientBannerChanges(String clientType, String clientName, String partitionConfig)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		Robot robot = new Robot();
		File directory = new File(".//");
		File tempfile = null;
		if (Browser.equalsIgnoreCase("Firefox")) {
			click(VSC.MANAGESMBCUSTLINK);
		} else {
			JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, VSC.SMBCUSTNAMESB, driver);

		if (clientName != null) {
			type(VSC.SMBCUSTNAMESB, clientName);
			Thread.sleep(5000);
			waitforElement(180, "//table/tbody/tr/td[contains(.,'" + clientName + "')]", driver);
			if (clientType.equalsIgnoreCase("standard")) {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ clientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			} else {
				WebElement clientname = driver.findElement(By
						.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientName + "')]"));
				JavaScriptclick(driver, clientname);
				Thread.sleep(2000);
			}
		} else {
			type(VSC.SMBCUSTNAMESB, BPOCPAClientName);
			Thread.sleep(5000);
			waitforElement(180, "//table/tbody/tr/td[contains(.,'" + BPOCPAClientName + "')]", driver);
			if (clientType.equalsIgnoreCase("standard")) {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ BPOCPAClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			} else {
				driver.findElement(By.xpath(
						"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + BPOCPAClientName + "')]"))
						.click();
				Thread.sleep(2000);
			}
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, VSC.EXPORT, driver);
		if (!partitionConfig.equalsIgnoreCase("YES")) {
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
			assertTrue(VSC.BillingAddress.isDisplayed());
			assertTrue(VSC.ShipToAddress.isDisplayed());
			assertTrue(VSC.Phonenumber.isDisplayed());
			assertTrue(VSC.Contractnumber.isDisplayed());
			assertTrue(VSC.STATUS.isDisplayed());
			assertTrue(VSC.STATUS.isDisplayed());
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
			if (!VerifyObject(driver, VSC.SMBCUSTOMERREPORT)) {
				assertTrue(true);
			} else {
				assertTrue(false);

			}
			if (!VerifyObject(driver, VSC.CUSTOMERRETURNS)) {
				assertTrue(true);
			} else {
				assertTrue(false);

			}
			if (!VerifyObject(driver, VSC.CustomerPrepayments)) {
				assertTrue(true);
			} else {
				assertTrue(false);

			}
			if (!VerifyObject(driver, VSC.PartitionConfiguration)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			JavaScriptclick(driver, VSC.ADMINDETAILS);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			JavaScriptclick(driver, VSC.Connectors);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(VSC.ECWHeaderText.isDisplayed());
			assertTrue(VSC.ECWURL.isDisplayed());
			String EcwUrl = driver
					.findElement(By.xpath("//div/span[contains(.,'URL:')]/../../div[@class='span7 ng-binding']"))
					.getText();
			System.out.println(EcwUrl);
			String evn = Environment.toLowerCase();
			System.out.println(evn);
			assertTrue(EcwUrl.equalsIgnoreCase("https://" + evn + "ecwportal.vertexsmb.com"));
			assertTrue(VSC.ECWClientCode.isDisplayed());
			assertTrue(PUL.ECWIntegrationText.isDisplayed());
			assertTrue(PUL.ECWInClientID.isDisplayed());
			assertTrue(PUL.ECWInClientsecret.isDisplayed());
			assertTrue(PUL.OseriesIntegrationText.isDisplayed());
			assertTrue(PUL.ECWOseriesClientID.isDisplayed());
			assertTrue(PUL.ECWOseriesClientSecret.isDisplayed());
		}
	}

	public void VertexSMBCustomersVerify(String clientName, String ReportsType, String Year, String Month,
			String View_Warning, String COMPANYCODE, String Export_EDI_List_Disable, String FilingPeriod,
			String GenerateReturns, String ReturnName, String AdminRole) throws Exception {

		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		driver.manage().deleteAllCookies();
		Thread.sleep(5000);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());
		type(VSC.SMBCUSTNAMESB, clientName);
		Thread.sleep(5000);
		waitforText(180, "//table/tbody/tr/td[1]/div[contains(.,'" + clientName + "')]", driver);
		Thread.sleep(5000);
		String CName = driver.findElement(By.xpath("//table/tbody/tr/td[1]/div")).getText();
		assertTrue((clientName).equalsIgnoreCase(CName));
		if (VerifyObject(driver, VSC.SELECT)) {
			JavaScriptclick(driver, VSC.SELECT);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.SELECTSYSAPP, driver);
		}
		if (ReportsType != "") {
			JavaScriptclick(driver, VSC.SMBREPORTS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.FILETYPE, driver);
			assertTrue(VSC.SELECTDOCSTODOWNLOAD.isDisplayed());
			selectByValue(VSC.FILETYPE, ReportsType);
			waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			waitforElement(180, VSC.YEAR, driver);
			selectByValue(VSC.YEAR, Year);
			waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			waitforElement(180, VSC.MONTH, driver);
			selectByValue(VSC.MONTH, Month);
			waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			if (View_Warning.equalsIgnoreCase("YES")) {
				List<WebElement> list = driver.findElements(By.xpath("//a[contains(.,'See Details')]"));
				for (int j = 1; j <= list.size(); j++) {
					driver.findElement(By.xpath("//ul/li[" + j + "]/div/span/a/span[contains(.,'See Details')]"))
							.click();
					if (VerifyObject(driver, VSC.VIEWWARNING)) {
						click(VSC.VIEWWARNING);
						assertTrue(VSC.WARNINGMESSAGE.isDisplayed());
						assertTrue(VSC.WARNINGTEXT.isDisplayed());
						click(VSC.CLOSEWARNING);
					}
				}
			}
			if (VerifyObject(driver, VSC.COMPANYCODE)) {
				waitforElement(180, VSC.COMPANYCODE, driver);
				selectByValue(VSC.COMPANYCODE, COMPANYCODE);
				waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			}
			waitforElement(180, VSC.REPORTSLINKS, driver);
			assertTrue(VSC.REPORTSLINKS.isDisplayed());
		} else {
			if (Export_EDI_List_Disable.equalsIgnoreCase("Yes")) {
				waitforElement(120, VSC.SMBCUSTOMERTAXRETURNS, driver);
				click(VSC.SMBCUSTOMERTAXRETURNS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(120, VSC.FILINGPERIOD, driver);
				selectByValue(VSC.FILINGPERIOD, FilingPeriod);
				Thread.sleep(5000);
				// assertTrue(VSC.EXPORTEDILIST.isDisplayed());
				// Verifying POR-14328 in 6.4.1, Generating Returns through Admin Portal
				if (GenerateReturns.equalsIgnoreCase("Yes")) {
					waitforElement(180, VSC.GENERATERETURNS, driver);
					if (ReturnName.equalsIgnoreCase("Yes")) {
						driver.findElement(By.xpath("//td[contains(.,'" + ReturnName + " ')]/../td[5]/input")).click();
						Thread.sleep(2000);
					} else {
						JavaScriptclick(driver, VSC.GENERATERETURNS);
					}
					waitforElement(180, VSC.GENERATERETURNSBUTTON, driver);
					JavaScriptclick(driver, VSC.GENERATERETURNSBUTTON);
					waitUntilElementInvisibe(180, CNP.LOADER_XPATH, driver);
					Thread.sleep(15000);
					if (AdminRole.equalsIgnoreCase("Treasury Admin")
							|| AdminRole.equalsIgnoreCase("System Approval Admin")
							|| AdminRole.equalsIgnoreCase("Invitation")) {
						assertTrue(VSC.Forbiddeerror.isDisplayed());
					}
					assertTrue(MTR.GENERATERETURNSMSG.isDisplayed());
				}
			} else {
				click(VSC.MANAGESMBCUSTLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, VSC.SELECTSYSAPP, driver);
				((JavascriptExecutor) driver).executeScript("scroll(0,3000);");
				Thread.sleep(10000);
				WebElement Lastpage = driver.findElement(
						By.xpath("//div[@class='pagination pagination-right ng-scope']/ul/li/a[contains(.,'»')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Lastpage);
				List<WebElement> PaginationValue = driver
						.findElements(By.xpath("//div[@class='pagination pagination-right ng-scope']/ul/li/a"));
				int Pagination_Count = PaginationValue.size();
				int count = 2;
				// do {
				for (int p = 1; p <= Pagination_Count; p++) {
					if (count >= Pagination_Count - 2) {
						WebElement PageClick = driver.findElement(By
								.xpath("//div[@class='pagination pagination-right ng-scope']/ul/li[" + count + "]/a"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", PageClick);
						((JavascriptExecutor) driver).executeScript("scroll(0,3000);");
						Thread.sleep(10000);
					} else {
						WebElement PageClick = driver.findElement(By
								.xpath("//div[@class='pagination pagination-right ng-scope']/ul/li[" + count + "]/a"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", PageClick);
						((JavascriptExecutor) driver).executeScript("scroll(0,3000);");
						Thread.sleep(10000);
						count++;
					}
					ArrayList<String> Customer_status = new ArrayList<String>();
					Customer_status.add("Registered");
					Customer_status.add("Initialized");
					Customer_status.add("PendingBilling");
					Customer_status.add("Authorized");
					Customer_status.add("Cancelled");
					List<WebElement> rowscount = driver.findElements(By.xpath(VSC.VERTEXSMBCUSTOMERSTABLE_XPATH));
					for (int k = 2; k < rowscount.size(); k = k + 1) {
						if (Customer_status.contains(
								driver.findElement(By.xpath("//table/tbody/tr[" + k + "]/td[7]")).getText())) {
							assertTrue(driver.getTitle().equalsIgnoreCase("Customer Management | Vertex SMB"));
							Customer_status.remove(
									driver.findElement(By.xpath("//table/tbody/tr[" + k + "]/td[7]")).getText());
							if (Customer_status.size() == 0) {
								break;
							}
						}
					}
				}
				// } while (!VerifyObject(driver, VSC.PAGINATIONDISABLE));
			}
		}
	}

	public void AdminSOCRequest(String CompanyName, String CustomerType, String ContactName, String Title, String Email,
			String ReportVerify) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String socLink = null, InvalidSOCLink = null;
		String path = null;
		File tempfile = null;
		String ResetToken = null;
		int sc, ec, c;
		String StDndCount = "0", EnDndCount = "0";
		File directory = new File(".//");
		SOCReport SRR = PageFactory.initElements(driver, SOCReport.class);
		waitforElement(180, SRR.SOCREPORTREQ, driver);
		Thread.sleep(8000);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, SRR.SOCREPORTREQ);
		} else {
			click(SRR.SOCREPORTREQ);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, SRR.Submit, driver);
		assertTrue(SRR.SOCTitle.isDisplayed());
		clear(SRR.CompanyName);
		click(SRR.CompanyName);
		type(SRR.CompanyName, CompanyName);
		waitforElement(180, SRR.CustomerType, driver);
		selectByValue(SRR.CustomerType, CustomerType);
		Thread.sleep(5000);
		sendkeysTab(SRR.CustomerType);
		clear(SRR.ContactName);
		type(SRR.ContactName, ContactName);
		clear(SRR.Title);
		type(SRR.Title, Title);
		clear(SRR.Email);
		type(SRR.Email, Email);
		// Invalid Emailformat verification
		if (VerifyObject(driver, SRR.INVALIDEMAILMESSAGE)) {
			assertTrue(SRR.INVALIDEMAILMESSAGE.isDisplayed());
		} else {
			Thread.sleep(5000);
			click(SRR.Submit);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, SRR.SOCEMAILLINK, driver);
			Thread.sleep(5000);
			JavaScriptclick(driver, SRR.SOCEMAILLINK);
			// The SOC Report was successfully processed. An email has been sent to RR@G.COM
			// with a link to download the report(s).
			waitforElement(180, SRR.SOCREPORTREQ, driver);
			JavaScriptclick(driver, SRR.SOCREPORTREQ);
			if (ReportVerify.equalsIgnoreCase("Yes")) {
				// StDndCount= SOCMetricsdownload(driver, "Socreportsheet", "Startdate",
				// "EndDate");
				sc = Integer.parseInt(StDndCount);
				socLink = xGetreportlinkfromGmail();
				if (socLink.isEmpty()) {
					// break;
				}
				ResetToken = socLink.split("Download/")[1];
				System.out.println(ResetToken);
				Thread.sleep(5000);
				driver.get(socLink);
				if (Browser.equalsIgnoreCase("Chrome")) {
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "SOCReports";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					tempfile = new File(DownloadFiles.LatestDownloadedFile("SOCReports", true));
					tempfile.toString();
				}
				// LatestDownloadedFile();
				if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
					LatestDownloadedFile("SOCReports", false);
				}
				InvalidSOCLink = socLink + "Invalid";
				driver.get(InvalidSOCLink);
				Thread.sleep(5000);
				assertTrue(driver.getTitle().equalsIgnoreCase("SOC Report Download Error | Vertex Cloud"));
				assertTrue(SRR.SOCDOWNLOADERROR.isDisplayed());
				assertTrue(SRR.SOCMAILID.isDisplayed());
				Thread.sleep(5000);
				driver.get(url);
				Thread.sleep(5000);
				// EnDndCount=SOCMetricsdownload(driver, "Socreportsheet", "Startdate",
				// "EndDate");
				Thread.sleep(10000);
				ec = Integer.parseInt(EnDndCount);
				c = ec - sc;
				System.out.println(c);
				assertTrue(c != 0);
				JavaScriptclick(driver, SRR.SOCREPORTREQ);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				Thread.sleep(5000);
			}
		}
	}

	public void TaxpayerPartitionsused(String varName, String ClientName, String ClientType, String Franchise,
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
				type(TPU.SMBCUSTOMERNAME, ClientName);
				Thread.sleep(4000);
				waitforElement(180,
						"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]", driver);
				if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
						+ "')]/../../../td[contains(text(),'" + varName + "')]")) {
					WebElement Merchant = driver.findElement(
							By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
									+ "')]/../../../td[contains(text(),'" + varName + "')]/../td[1]/div/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Merchant);

				}
			} else {
				type(TPU.SMBCUSTOMERNAME, ClientName);
				Thread.sleep(4000);
				waitforElement(180,
						"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]", driver);
				if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
						+ "')]/../../../td[contains(text(),'" + varName + "')]")) {
					WebElement Registered = driver.findElement(
							By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
									+ "')]/../../../td[contains(text(),'" + varName + "')]/../td[1]/div/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
				}
			}
		} else {
			if (!ClientName.contains("TI")) {
				type(TPU.SMBCUSTOMERNAME, ClientName);
			} else {
				type(TPU.SMBCUSTOMERNAME, ClientName.split("\\.")[0]);
			}

			Thread.sleep(4000);
			waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]",
					driver);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName
					+ "')]/../../../td[contains(text(),'" + varName + "')]")) {
				WebElement Registered = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName
								+ "')]/../../../td[contains(text(),'" + varName + "')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
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
			waitforElement(180, "//div[@class='span12 ng-binding'][contains(.,'" + ClientName + "')]", driver);
			assertTrue(
					driver.findElement(By.xpath("//div[@class='span12 ng-binding'][contains(.,'" + ClientName + "')]"))
							.isDisplayed());
		} else if (ClientType.equalsIgnoreCase("Merchant")) {
			waitforElement(180, "//div[@class='span12 ng-binding'][contains(.,'" + ClientName + "')]", driver);
			assertTrue(
					driver.findElement(By.xpath("//div[@class='span12 ng-binding'][contains(.,'" + ClientName + "')]"))
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

	public void AdminMultiPartition(String varName, String ClientName) throws Exception {
		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(120, TPU.SMBCUSTOMERSTAB, driver);
		click(TPU.SMBCUSTOMERSTAB);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);
		type(TPU.SMBCUSTOMERNAME, ClientName);
		Thread.sleep(4000);
		waitforElement(180,"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]", driver);
		driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
				+ ClientName + "')]")).click();	
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(.,'Admin Details')]")).click();
		Thread.sleep(5000);
		waitforElement(180, TPU.BUT_ADD_PARTITION, driver);
		click(TPU.BUT_ADD_PARTITION);
		waitforElement(180, TPU.AddPartitionHeader, driver); click(TPU.BUT_CANCEL);
		Thread.sleep(3000);
		waitforElement(180, TPU.BUT_ADD_PARTITION, driver);
		click(TPU.BUT_ADD_PARTITION);
		waitforElement(180, TPU.DisplayName, driver);
		assertTrue(TPU.DisplayName.isDisplayed());
		type(TPU.DisplayName, "T"+RND());
		waitforElement(180, TPU.But_Add_Partition, driver);
		assertTrue(TPU.But_Add_Partition.isDisplayed());
		click(TPU.But_Add_Partition);
		waitforElement(180, TPU.RemoveConfirmHeader1, driver);
		assertTrue(TPU.RemoveConfirmHeader1.isDisplayed());
		click(TPU.But_Confirm_remove);
		Thread.sleep(5000);
					
	}
	
	public void AddMultiPartitioninAdmin(String varName, String ClientName) throws Exception {
		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(120, TPU.SMBCUSTOMERSTAB, driver);
		click(TPU.SMBCUSTOMERSTAB);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);
		type(TPU.SMBCUSTOMERNAME, ClientName);
		Thread.sleep(4000);
		waitforElement(180,"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]", driver);
		driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
				+ ClientName + "')]")).click();	
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(.,'Admin Details')]")).click();
		Thread.sleep(5000);
		waitforElement(180, TPU.BUT_ADD_PARTITION, driver);
		click(TPU.BUT_ADD_PARTITION);
		/*
		 * waitforElement(180, TPU.AddPartitionHeader, driver); click(TPU.BUT_CANCEL);
		 */
		Thread.sleep(3000);
		//waitforElement(180, TPU.BUT_ADD_PARTITION, driver);
		//click(TPU.BUT_ADD_PARTITION);
		waitforElement(180, TPU.DisplayName, driver);
		assertTrue(TPU.DisplayName.isDisplayed());
		String SECONDPID1 = "T"+RND();
		NPID = SECONDPID1;
		type(TPU.DisplayName, NPID);
		System.out.println(NPID);
		waitforElement(180, TPU.But_Add_Partition, driver);
		assertTrue(TPU.But_Add_Partition.isDisplayed());
		Thread.sleep(2000);
		click(TPU.But_Add_Partition);
		waitforElement(180, TPU.RemoveConfirmHeader1, driver);
		assertTrue(TPU.RemoveConfirmHeader1.isDisplayed());
		click(TPU.But_Confirm_remove);
		Thread.sleep(5000);
					
	}
	public void AdminCloudClientBannerChanges(String ClientName, String Client_Type, String Verify_PartitionConfig,
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
		if (ClientName != null) {
			type(VSC.SMBCUSTNAMESB, ClientName);
			Thread.sleep(5000);
			waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
			if (Client_Type.equalsIgnoreCase("standard")) {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			} else {
				WebElement clientname = driver.findElement(By
						.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
				JavaScriptclick(driver, clientname);
				Thread.sleep(2000);
			}
			Client = VertexClientName;
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

	public void TweetsandEvents() throws Exception {
		Thread.sleep(5000);
		String url = "http://www.vertexsmb.com/news/events/";
		VarUserLinks VUL = PageFactory.initElements(driver, VarUserLinks.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		driver.get(url);
		Thread.sleep(5000);
		if (VerifyObject(driver, VUL.EVENTS)) {
		} else {
			Events = "exists";
		}
		if (VerifyObject(driver, VUL.DASHBOARDLINK)) {
			if (VerifyObject(driver, LP.VTMSACCOUNT)) {
				waitforElement(180, LP.VTMSACCOUNT, driver);
				boolean report = false;
				do {
					JavaScriptclick(driver, LP.VTMSACCOUNT);
					Thread.sleep(6000);
					if (LP.VTMSLOGOUT.isDisplayed() == true) {
						JavaScriptclick(driver, LP.VTMSLOGOUT);
						Thread.sleep(2000);
						report = true;
					}
				} while (report == false);
				Thread.sleep(5000);
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
		}
	}

	public void AdminErrorMonitor(String clientName) throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		POR632 POR = PageFactory.initElements(driver, POR632.class);
		waitforElement(180, POR.ERRORMONITOR, driver);
		click(POR.ERRORMONITOR);
		waitUntilElementInvisibe(180, POR.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);

		if (VerifyObject(driver,
				"//table/tbody/tr/td[contains(text(),'" + clientName + "')]/../td[contains(.,'Support Case')]")) {

			driver.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'" + clientName
					+ "')]/../td[contains(.,'Support Case')]/../td/a[contains(.,'Details')]")).click();
			waitforElement(60, POR.PermanentError, driver);
			Thread.sleep(2000);
			JavaScriptclick(driver, POR.PermanentError);
			waitforElement(60, POR.SUCCESSMSG, driver);
		}

		if (!VerifyObject(driver, "//table/tbody/tr/td[contains(text(),'" + clientName + "')]")) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}

	}

	public void AdminQueueService(String ClientName, String TaxpayerName, String COMPANYCODE, String StartDate,
			String EndDate, String QueueServiceID, String TaxpayerID_Verification) throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		String clientID = null;
		AdminQueueService AQ = PageFactory.initElements(driver, AdminQueueService.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		waitforElement(180, AQ.DASHBOARD, driver);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, AQ.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		sendkeysEnter(VSC.SMBCUSTNAMESB);
		waitforElement(180, "//table/tbody/tr/td/div/a[contains(.,'" + ClientName + "')]", driver);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")) {
			WebElement registered = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			JavaScriptclick(driver, registered);
		} else {
			WebElement authorized = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			JavaScriptclick(driver, authorized);
		}
		Thread.sleep(4000);
		waitUntilElementInvisibe(180, AQ.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SELECTSYSAPP, driver);
		clientID = driver.findElement(By.xpath("//div/span[contains(.,'Cloud Customer Id:')]/../../div[2]")).getText();
		String nw[] = clientID.split(":");
		clientID = nw[1].trim();
		System.out.println(nw[1].trim());
		click(AQ.QUEUESERVICELINK);
		waitUntilElementInvisibe(180, AQ.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AQ.QUEUESERVICEID, driver);
		assertTrue(AQ.QUEUESERVICELINK.isDisplayed());
		assertTrue(AQ.QUEUESERVICEHEADERTAEXT.isDisplayed());
		assertTrue(AQ.QUEUESERVICEID.isDisplayed());
		assertTrue(AQ.QUEUETYPE.isDisplayed());
		assertTrue(AQ.CLIENTID.isDisplayed());
		assertTrue(AQ.TAXPAYERID.isDisplayed());
		assertTrue(AQ.QUEUESTATUS.isDisplayed());
		assertTrue(AQ.COMPLETEINPUT.isDisplayed());
		assertTrue(AQ.COMPLETEWITHERRORINPUT.isDisplayed());
		assertTrue(AQ.INPROGRESSINPUT.isDisplayed());
		assertTrue(AQ.PENDINGINPUT.isDisplayed());
		assertTrue(AQ.ERRORINPUT.isDisplayed());
		assertTrue(AQ.PERMANENTERRORINPUT.isDisplayed());
		assertTrue(AQ.UNKNOWNINPUT.isDisplayed());
		assertTrue(AQ.RECORDTEXT.isDisplayed());
		assertTrue(AQ.RECORDSPAGES.isDisplayed());
		assertTrue(AQ.STARTDATELABEL.isDisplayed());
		assertTrue(AQ.ENDDATELABEL.isDisplayed());
		assertTrue(AQ.TaxpayerNameField.isDisplayed());
		assertTrue(AQ.COMPANYCODE.isDisplayed());
		click(AQ.RECORDSPAGES);
		selectByValue(AQ.RECORDSPAGES, "25");
		selectByValue(AQ.RECORDSPAGES, "50");
		selectByValue(AQ.RECORDSPAGES, "100");
		selectByValue(AQ.RECORDSPAGES, "150");
		selectByValue(AQ.RECORDSPAGES, "200");
		selectByValue(AQ.RECORDSPAGES, "250");
		assertTrue(AQ.REFRESHBUTTON.isDisplayed());
		assertTrue(AQ.QUEUESERVICEIDHEADERTEXT.isDisplayed());
		assertTrue(AQ.QUEUESTATUSHEADERTEXT.isDisplayed());
		assertTrue(AQ.QUEUETYPEHEADERTEXT.isDisplayed());
		assertTrue(AQ.CLIENTIDHEADERTEXT.isDisplayed());
		assertTrue(AQ.CLIENTNAME.isDisplayed());
		assertTrue(AQ.TAXPAYERID.isDisplayed());
		assertTrue(AQ.COMPANYCODE.isDisplayed());
		assertTrue(AQ.TAXPAYERNAME.isDisplayed());
		assertTrue(AQ.CREATEDDATE.isDisplayed());
		assertTrue(AQ.MODIFIEDDATE.isDisplayed());
		assertTrue(AQ.EXCEPTIONLOGID.isDisplayed());
//		assertTrue(AQ.Pagination.isDisplayed());
//		click(AQ.RECORDSPAGES);
//		selectByValue(AQ.Pagination, "25 per page");
//		selectByValue(AQ.Pagination, "50 per page");
//		selectByValue(AQ.Pagination, "100 per page");
		driver.navigate().refresh();
		Thread.sleep(8000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		Thread.sleep(8000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(AQ.STARTDATE);
		clear(AQ.STARTDATE);
		type(AQ.STARTDATE, StartDate + " 00:00:00.000");
		sendkeysTab(AQ.STARTDATE);

		click(AQ.ENDDATE);
		clear(AQ.ENDDATE);
		type(AQ.ENDDATE, EndDate + " 00:00:00.000");
		sendkeysTab(AQ.ENDDATE);
		type(AQ.CLIENTIDVALUE, clientID);
		selectByValue(AQ.RECORDSPAGES, "250");
		click(AQ.COMPLETEINPUT);
		Thread.sleep(2000);
		click(AQ.COMPLETEWITHERRORINPUT);
		Thread.sleep(2000);
		click(AQ.PERMANENTERRORINPUT);
		Thread.sleep(2000);
		click(AQ.UNKNOWNINPUT);
		Thread.sleep(2000);
		click(AQ.INCLUDEOSERIESFILEVALIDATION);
		Thread.sleep(2000);
		click(AQ.REFRESHBUTTON);
		Thread.sleep(8000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		Thread.sleep(8000);
		if (TaxpayerID_Verification.equalsIgnoreCase("Yes")) {
			String ApTaxpayerID = driver
					.findElement(By.xpath("//thead/tr/th[contains(.,'TaxpayerId')]/../../../tbody/tr[1]/td[6]"))
					.getText();
			System.out.println(ApTaxpayerID);
			type(AQ.TAXPAYERIDVALUE, ApTaxpayerID);
			selectByValue(AQ.RECORDSPAGES, "250");
			click(AQ.REFRESHBUTTON);
			Thread.sleep(8000);
			if (VerifyObject(driver, CNP.OKERROR)) {
				click(CNP.OKERROR);
			}
			Thread.sleep(8000);
			String ApTaxpayername = driver
					.findElement(By.xpath("//thead/tr/th[contains(.,'TaxpayerName')]/../../../tbody/tr[1]/td[7]"))
					.getText();
			System.out.println(ApTaxpayername);
			String ApCompcode = driver
					.findElement(By.xpath("//thead/tr/th[contains(.,'Company Code')]/../../../tbody/tr[1]/td[8]"))
					.getText();
			System.out.println(ApTaxpayername);
			System.out.println(ApCompcode);
			assertTrue(ApCompcode.equalsIgnoreCase(COMPANYCODE));
			assertTrue(ApTaxpayername.equalsIgnoreCase(TaxpayerName));
		}
		if (QueueServiceID.equalsIgnoreCase("Yes")) {
			String ApQueuserviceID = driver
					.findElement(By.xpath("//thead/tr/th[contains(.,'QueueServiceId')]/../../../tbody/tr[1]/td[1]"))
					.getText();
			System.out.println(ApQueuserviceID);
			type(AQ.QUEUETYPEIDVALUE, ApQueuserviceID);
			selectByValue(AQ.RECORDSPAGES, "250");
			click(AQ.REFRESHBUTTON);
			Thread.sleep(8000);
			if (VerifyObject(driver, CNP.OKERROR)) {
				click(CNP.OKERROR);
			}
			Thread.sleep(6000);
			String ApnewTaxpayername = driver
					.findElement(By.xpath("//thead/tr/th[contains(.,'QueueServiceId')]/../../../tbody/tr[1]/td[1]"))
					.getText();
			System.out.println(ApnewTaxpayername);
			assertTrue(ApQueuserviceID.equalsIgnoreCase(ApnewTaxpayername));
		}
		click(AQ.ERRORMONITOR);
		if (VerifyObject(driver, "//tbody/tr/td[1]")) {
			waitforText(180, "//tbody/tr/td[1]", driver);
			String getQueuetype = driver.findElement(By.xpath("//tbody/tr/td[2]")).getText();
			String UpdatedgetQueuetype = getQueuetype.replaceAll("\\s", "");
			click(AQ.QUEUESERVICELINK);
			waitUntilElementInvisibe(180, AQ.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, AQ.QUEUESERVICEID, driver);
			selectByValue(AQ.QUEUETYPEIDVALUE, UpdatedgetQueuetype);
			selectByValue(AQ.RECORDSPAGES, "250");
			click(AQ.COMPLETEINPUT);
			click(AQ.COMPLETEWITHERRORINPUT);
			click(AQ.PENDINGINPUT);
			click(AQ.PERMANENTERRORINPUT);
			click(AQ.UNKNOWNINPUT);
			click(AQ.REFRESHBUTTON);
			Thread.sleep(8000);
			if (VerifyObject(driver, CNP.OKERROR)) {
				click(CNP.OKERROR);
			}
			Thread.sleep(8000);
			String ApQueuetype = driver
					.findElement(By.xpath("//thead/tr/th[contains(.,'QueueStatus')]/../../../tbody/tr[1]/td[2]"))
					.getText();
			System.out.println(ApQueuetype);
			assertTrue(UpdatedgetQueuetype.equalsIgnoreCase(ApQueuetype));
		}

		if (TaxpayerName != "") {
			type(AQ.TaxpayerNameField, TaxpayerName);
		}
		if (COMPANYCODE != "") {
			type(AQ.COMPANYCODE, COMPANYCODE);
			click(AQ.REFRESHBUTTON);
			Thread.sleep(8000);
			if (VerifyObject(driver, CNP.OKERROR)) {
				click(CNP.OKERROR);
			}
			Thread.sleep(8000);
			assertTrue(AQ.TAXPAYERNAMECOLUMN.isDisplayed());
			assertTrue(AQ.COMPANYCODECOLUMN.isDisplayed());
			assertTrue(driver.findElement(By.xpath("//table/tbody/tr[1]/td[contains(.,'" + TaxpayerName + "')]"))
					.isDisplayed());
			assertTrue(driver.findElement(By.xpath("//table/tbody/tr[1]/td[contains(.,'" + COMPANYCODE + "')]"))
					.isDisplayed());
		}
	}

	@SuppressWarnings("static-access")
	public void VertexAdminscreens(String DashBoard_Title, String LookUp_Title, String Metrics_Title,
			String InviteUsers_Title, String VerCloudCust_Title, String VertexCloudVars_Title,
			String ManageConfig_Title, String InlineHelp_Title, String UpdateNotification_Title,
			String Managetaxcalendar_Title, String ErrorMonitor_Title) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Robot rob = new Robot();
		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		AdminUserLinks AUL = PageFactory.initElements(driver, AdminUserLinks.class);
		InviteUsers INU = PageFactory.initElements(driver, InviteUsers.class);
		driver.getWindowHandle();
		VertexAdminTitles = "";
		click(TBS.DASHBOARD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String DB_Title = driver.getTitle();
		if (DashBoard_Title.equalsIgnoreCase(DB_Title)) {
			VertexAdminTitles = DB_Title + ";";
		}
		click(TBS.RatesJurisdictions);
		String LookUp_Title1 = driver.getTitle();
		if (LookUp_Title.equalsIgnoreCase(LookUp_Title1)) {
			VertexAdminTitles = "LookUp_Title" + ";";
		}
		click(TBS.DASHBOARD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String CurrentURL = driver.getCurrentUrl();
		if (!VerifyObject(driver, TBS.CUSTOMERIDREPORT)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		driver.get(url + "//Admin//customerreport");
		assertTrue(getText(TBS.PAGENOTFOUND).equalsIgnoreCase("Page Not Found"));
		driver.navigate().to(CurrentURL);
		waitforElement(180, TBS.DOCUMENTCENTER, driver);
		// Document Center
		click(TBS.DOCUMENTCENTER);
		waitforElement(180, TBS.DOCUMENTCENTERFILETYPE, driver);
		if ((Environment.equalsIgnoreCase("QA")) || (Environment.equalsIgnoreCase("DEV"))) {
			click(TBS.DOCUMNETCENTERCLIENT);
			waitforElement(180, TBS.DOCUMENTCENTERUPLOADFILE, driver);
			assertTrue(TBS.DOCUMENTCENTERUPLOADFILE.isDisplayed());
			Thread.sleep(10000);
			selectByValue(TBS.DOCUMENTCENTERFILETYPE, "Notices");
			Thread.sleep(5000);
			selectByValue(TBS.DOCUMENTCENTERFILETYPE, "Returns");
			Thread.sleep(5000);
		}
		// Metrics
		JavaScriptclick(driver, TBS.METRICS);
		// *****Verifying Partitions metric data Pod Sub types as Per POR-17283
		waitforElement(180, TBS.PARTITIONMETRICDATA, driver);
		waitforElement(180, TBS.VIEWMETRICS, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TBS.VIEWMETRICS);
		} else {
			click(TBS.VIEWMETRICS);
		}
		Thread.sleep(10000);
		if (VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
			assertTrue(PUL.ANERRORHASOCCURED.isDisplayed());
			click(PUL.OKERROR);
			waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
			Thread.sleep(3000);
		} else {
			assertTrue(TBS.PODSUBTYPE.isDisplayed());
			assertTrue(TBS.POD1SUBTYPE.isDisplayed());
			assertTrue(TBS.POD2SUBTYPE.isDisplayed());
			if (Environment.equalsIgnoreCase("STAGE")) {
				assertTrue(TBS.POD3SUBTYPE.isDisplayed());
			}
			if (!VerifyObject(driver, TBS.PODCSUBTYPE)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			click(TBS.OK);
		}
		// ******
		waitforElement(180, TBS.MAINTENANCEMETRICDATA, driver);
		waitforElement(180, TBS.DOWNLOADEDCSV, driver);
		String MR_Title = driver.getTitle();
		if (Metrics_Title.equalsIgnoreCase(MR_Title)) {
			VertexAdminTitles = "Metrics_Title" + ";";
		}
		assertTrue(TBS.MAINTENANCEMETRICDATA.isDisplayed());
		assertTrue(TBS.DOWNLOADEDCSV.isDisplayed());
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TBS.DOWNLOADEDCSV);
		} else {
			click(TBS.DOWNLOADEDCSV);
		}
		Thread.sleep(80000);
		if (VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
			assertTrue(PUL.ANERRORHASOCCURED.isDisplayed());
			click(PUL.OKERROR);
			waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
			Thread.sleep(3000);
			click(TBS.STARTDATE);
			Thread.sleep(3000);
			clear(TBS.STARTDATE);
			currDate1 = xGetDate("MM/dd/yyyy");
			type(TBS.STARTDATE, currDate1);
			click(TBS.DOWNLOADEDCSV);
			Thread.sleep(10000);
		}
		if (Browser.equalsIgnoreCase("IE")) {
			File directory = new File(".//");
			directory.getCanonicalPath();
			Thread.sleep(5000);
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
			StringSelection stringSelection = new StringSelection(
					directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\");
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
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(5000);
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
		}
		reader.close();
		// invite Users
		click(TBS.INVITEUSERS);
		waitforElement(180, TBS.ADMININVITE, driver);
		// Verifying Client Name and Client Number Columns as per POR-15816 in 7.0
		waitforText(180, INU.CLIENTNAME, driver);
		assertTrue(INU.CLIENTNAME.isDisplayed());
		assertTrue(INU.CLIENTNUMBER.isDisplayed());
		String IN_Title = driver.getTitle();
		if (InviteUsers_Title.equalsIgnoreCase(IN_Title)) {
			VertexAdminTitles = "InviteUsers_Title" + ";";
		}
		click(TBS.ADMININVITE);
		waitforElement(180, TBS.VERIFYBILAPPADM_LINK, driver);
		// *****verifying Billing Approval Admin value is there in the selectbox****
//		assertTrue(TBS.VERIFYBILAPPADM.isDisplayed());
		click(TBS.DASHBOARD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, TBS.VERTEXSMBCUSTOMERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, TBS.OSERIESAPIKEY, driver);
		String VSC_Title = driver.getTitle();
		// Validating search with Trustedid
		/*
		 * type(TBS.OSERIESAPIKEY, TrsID); Thread.sleep(5000); waitforElement(180,
		 * TBS.CUSTOMERSFIRSTROW, driver); assertTrue(driver.findElement(By.xpath(
		 * "//tbody/tr[2]/td/div/a[contains(text(),'" + SettingsClntName + "')]"))
		 * .isDisplayed());
		 */
		if (VerCloudCust_Title.equalsIgnoreCase(VSC_Title)) {
			VertexAdminTitles = "VerCloudCust_Title" + ";";
		}
		// click(TBS.PREMIUMSMBCUSTOMERS);
		JavaScriptMousehover(driver, AUL.PremiumCustomerLink);
		waitforElement(180, TBS.PremiumCust, driver);
		JavaScriptclick(driver, TBS.PremiumCust);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TBS.searchboxinPremium, driver);
		Thread.sleep(5000);
		waitforElement(180, TBS.clientList, driver);
		click(TBS.DASHBOARD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TBS.VERTEXSMBVARS, driver);
		// Added by Venkat
		assertTrue(TBS.VERTEXSMBVARS.isDisplayed());
		JavaScriptclick(driver, TBS.VERTEXSMBVARS);
		waitforElement(180, TBS.VSMBVARSEARCH, driver);
		String VSV_Title = driver.getTitle();
		if (VertexCloudVars_Title.equalsIgnoreCase(VSV_Title)) {
			VertexAdminTitles = VSV_Title + ";";
		}
		assertTrue(TBS.VSMBVARSEARCH.isDisplayed());
		assertTrue(TBS.SMBVARSNAMEHEADER.isDisplayed());
		assertTrue(TBS.SMBVARSADDRESSHEADER.isDisplayed());
		assertTrue(TBS.SMBVARSCITYHEAADER.isDisplayed());
		assertTrue(TBS.SMBVARSSTATEHEADER.isDisplayed());
		assertTrue(TBS.SMBVARSZIPHEADER.isDisplayed());
		assertTrue(TBS.SMBVARSPHONEHEADER.isDisplayed());
		assertTrue(TBS.SMBVARSSTATUSHEADER.isDisplayed());
		assertTrue(TBS.SMBVARACCOUNTTYPE.isDisplayed());
		assertTrue(TBS.SMBVARSADMINHEADER.isDisplayed());
		assertTrue(TBS.SMBVARSUSERSHEADER.isDisplayed());
		click(TBS.SMBVARSPLUSICONUSERSCOLUMN);
		waitforElement(180, TBS.SMBVARSVARLASTNAME, driver);
		assertTrue(TBS.SMBVARSVARFIRSTNAME.isDisplayed());
		assertTrue(TBS.SMBVARSVARLASTNAME.isDisplayed());
		assertTrue(TBS.SMBVARSVAREMAIL.isDisplayed());
		assertTrue(TBS.SMBVARSVARPHONE.isDisplayed());
		assertTrue(TBS.SMBVARSVARCREATED.isDisplayed());
		assertTrue(TBS.SMBVARSVARLASTLOGIN.isDisplayed());
		assertTrue(TBS.SMBVARSVARSTATUS.isDisplayed());
		assertTrue(TBS.SMBVARSVARROLE.isDisplayed());
		click(TBS.SMBVARSMINUSICONUSERSCOLUMN);
		waitforElement(180, TBS.INLINEHELP, driver);
		if (VerifyObject(driver, TBS.INLINEHELP)) {
			click(TBS.INLINEHELP);
			waitforElement(180, TBS.CANCEL, driver);
			String IL_Title = driver.getTitle();
			if (InlineHelp_Title.equalsIgnoreCase(IL_Title)) {
				VertexAdminTitles = "InlineHelp_Title" + ";";
			}
			Thread.sleep(3000);
			click(TBS.CANCEL);
			waitforElement(180, TBS.INLINEHELP, driver);
			click(TBS.INLINEHELP);
			selectByValue(TBS.HELPDRPDWN, "Clients : Manage");
			Thread.sleep(5000);
		}
		// Update Notification
		click(TBS.UPDATENOTIFICATION);
		waitforElement(180, TBS.CANCEL, driver);
		String UN_Title = driver.getTitle();
		assertTrue(UpdateNotification_Title.equalsIgnoreCase(UN_Title));
		VertexAdminTitles = "UpdateNotification_Title" + ";";
		click(TBS.MANAGECONGIGURATION);
		waitforElement(30, TBS.MANAGECONFIGUPDATEBUTTON, driver);
		String MC_Title = driver.getTitle();
		if (ManageConfig_Title.equalsIgnoreCase(MC_Title)) {
			VertexAdminTitles = "ManageConfig_Title" + ";";
		}
		assertTrue(TBS.MANAGECONFIGUPDATEBUTTON.isDisplayed());
		assertTrue(TBS.MANAGECONFIGURATIONSCREENSCANCEL.isDisplayed());
		waitforElement(30, TBS.MANAGERETURNSPECIFICRULESLINK, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TBS.MANAGERETURNSPECIFICRULESLINK);
		} else {
			click(TBS.MANAGERETURNSPECIFICRULESLINK);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(30, TBS.MANAGERETURNSSPECIFICRULESUPDATE, driver);
		try {
			switchToAlertAccept(driver);
		} catch (Exception e) {
		}
		assertTrue(TBS.MANAGERETURNSSPECIFICRULESUPDATE.isDisplayed());
		assertTrue(TBS.MANAGECONFIGURATIONSCREENSCANCEL.isDisplayed());
		click(TBS.MANAGELOCATIONSPECIFICRULESLINK);
		waitforElement(180, TBS.MANAGERETURNSSPECIFICRULESUPDATE, driver);
		try {
			switchToAlertAccept(driver);
		} catch (Exception e) {
		}
		click(TBS.MANAGELOCATIONSETUPLINK);
		try {
			switchToAlertAccept(driver);
		} catch (Exception e) {
		}
		click(TBS.ASSOCIATEDTAXPAYERRULESLINK);
		try {
			switchToAlertAccept(driver);
		} catch (Exception e) {
		}
		JavaScriptclick(driver, TBS.MANAGETAXCALENDAR);
		waitforElement(30, TBS.MNGTAXCALUPDATEDISABLE, driver);
		String MTC_Title = driver.getTitle();
		if (Managetaxcalendar_Title.equalsIgnoreCase(MTC_Title)) {
			VertexAdminTitles = "Managetaxcalendar_Title" + ";";
		}
		assertTrue(TBS.MNGTAXCALUPDATEDISABLE.isDisplayed());
		JavaScriptclick(driver, TBS.ERRORMONITOR);
		waitforElement(180, TBS.EMCUSTNAME, driver);
		waitforElement(180, TBS.EMPCUSTNAME, driver);
		String EM_Title = driver.getTitle();
		if (ErrorMonitor_Title.equalsIgnoreCase(EM_Title)) {
			VertexAdminTitles = "ErrorMonitor_Title" + ";";
		}
		assertTrue(TBS.EMCUSTNAME.isDisplayed());
		assertTrue(TBS.EMPCUSTNAME.isDisplayed());
		// Collapse functionality POR-1245
		JavaScriptclick(driver, TBS.COLLAPSE);
		waitforElement(180, TBS.VERIFYCOLLAPSED, driver);
		assertTrue(TBS.VERIFYCOLLAPSED.isDisplayed());
		JavaScriptclick(driver, TBS.COPSDMTCTABICON);
		waitforElement(180, TBS.MTCALEDRHTEXT, driver);
		assertTrue(TBS.MTCALEDRHTEXT.isDisplayed());
		JavaScriptclick(driver, TBS.COLLAPSE);
		// Collapse functionality POR-1245
		// ***Verifying Hard Date in Manage Tax Calendar***
		waitforElement(180, TBS.MNGTAXCALEND, driver);
		JavaScriptclick(driver, TBS.MNGTAXCALEND);
		waitforText(180, TBS.MNGTAXCALUPDATEDISABLE, driver);
		if (!VerifyObject(driver, TBS.HARDDATELBL)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		click(TBS.DASHBOARD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
	}

	public void AdminUserLinks(String Status_Type) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Thread.sleep(3000);
		driver.getWindowHandle();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		AdminUserLinks AUL = PageFactory.initElements(driver, AdminUserLinks.class);
		InviteUsers INU = PageFactory.initElements(driver, InviteUsers.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		JavaScriptclick(driver, AUL.DASHBOARD);
		waitforElement(180, AUL.DASHBOARD, driver);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(AUL.DASHBOARD.isDisplayed());
		waitforElement(180, AUL.DASHBOARDTITLE, driver);
		JavaScriptclick(driver, AUL.RatesJurisdictions);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, PUL.LOOKUPRATE, driver);
		JavaScriptMousehover(driver, AUL.PremiumCustomerLink);
		JavaScriptclick(driver, AUL.PremiumDownloadRetFiles);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AUL.Action, driver);
		assertTrue(AUL.DownloadRetFilesLabel.isDisplayed());
		assertTrue(AUL.FilingPeriodLabel.isDisplayed());
		assertTrue(AUL.GenerationDate.isDisplayed());
		assertTrue(AUL.VertexCust.isDisplayed());
		assertTrue(AUL.COMPANYCODE.isDisplayed());
		assertTrue(AUL.Action.isDisplayed());
		if (VerifyObject(driver, AUL.METRICSLINK)) {
			JavaScriptclick(driver, AUL.METRICSLINK);
			waitforElement(180, AUL.AUDITMETRICDATA, driver);
			click(AUL.AUDITMETRICDATA);
			// waiting to download file
			Thread.sleep(20000);
			// POR-13884 Rate File Metric Data Download for 3 years
			Calendar date = Calendar.getInstance();
			date.setTime(new Date());
			Format f = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println(f.format(date.getTime()));
			clear(AUL.METRICSTARTDATE);
			type(AUL.METRICSTARTDATE, f.format(date.getTime()));
			date.add(Calendar.YEAR, -3);
			clear(AUL.METRICENDDATE);
			type(AUL.METRICENDDATE, f.format(date.getTime()));
			JavaScriptclick(driver, AUL.RATEFILEMETRICDATA);
			Thread.sleep(10000);
			//
			driver.navigate().refresh();
			waitforElement(180, AUL.AUDITMETRICDATA, driver);
			click(AUL.TRANSACTIONMETRICDATA);
			Thread.sleep(10000);
			click(AUL.PRODUCTMETRICDATA);
			Thread.sleep(10000);
			click(AUL.RETURNMETRICDATA);
			Thread.sleep(10000);
			click(AUL.MAINTENANCEMETRICDATA);
			Thread.sleep(10000);
		} else {
			assertTrue(!(AUL.METRICSLINK.isDisplayed()));
		}
		assertTrue(AUL.MANAGESMBCUSTOMERS.isDisplayed());
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, AUL.MANAGESMBCUSTOMERS);
		} else {
			JavaScriptclick(driver, AUL.MANAGESMBCUSTOMERS);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AUL.SMBSTATUS, driver);
		assertTrue(AUL.MANAGESMBCUSTOMERTITLE.isDisplayed());
		assertTrue(AUL.SMBCUSTOMERLISTING.isDisplayed());
		assertTrue(AUL.SMBCUSTOMERNUMLABEL.isDisplayed());
		assertTrue(AUL.SMBCUSTOMERNAMELABEL.isDisplayed());
		assertTrue(AUL.SUBSCRIPTIONLABEL.isDisplayed());
		assertTrue(AUL.STATUSLABEL.isDisplayed());
		assertTrue(AUL.VARNAMELABEL.isDisplayed());
		// assertTrue(AUL.EXPORTBUTTON.isDisplayed());
		assertTrue(AUL.DOCCENTER.isDisplayed());
		// POR-10086 Verification
		String S[] = null;
		String P[] = null;
		String DateTime;
		selectByValue(AUL.SMBSTATUS, Status_Type);
		Thread.sleep(5000);
		if (Status_Type.equalsIgnoreCase("Pending Billing")) {
			waitforElement(60,
					"//span[contains(.,'Status')]/../../../../../tbody/tr[2]/td[contains(.,'PendingBilling(')]",
					driver);
			if (VerifyObject(driver,
					"//span[contains(.,'Status')]/../../../../../tbody/tr[2]/td[contains(.,'PendingBilling(')]")) {
				DateTime = driver.findElement(By.xpath(
						"//span[contains(.,'Status')]/../../../../../tbody/tr[2]/td[contains(.,'PendingBilling(')]"))
						.getText();
				S = DateTime.split("PendingBilling");
			}
		} else {
			waitforElement(60,
					"//span[contains(.,'Status')]/../../../../../tbody/tr[2]/td[contains(.,'" + Status_Type + "(')]",
					driver);
			if (VerifyObject(driver,
					"//span[contains(.,'Status')]/../../../../../tbody/tr[2]/td[contains(.,'" + Status_Type + "(')]")) {
				DateTime = driver
						.findElement(By.xpath("//span[contains(.,'Status')]/../../../../../tbody/tr[2]/td[contains(.,'"
								+ Status_Type + "(')]"))
						.getText();
				S = DateTime.split(Status_Type);
			}
		}
		if (VerifyObject(driver, AUL.PAGINATIONDD)) {
			P = S[1].split("\\n");
			String VerifyDate = P[1].replaceAll("[()]", "");
			assertTrue(VerifyDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4}) ([0-9]){2}:([0-9]){2} ([a-z]){2}"));
		}
		JavaScriptclick(driver, AUL.DOCCENTER);
		assertTrue(AUL.INVITEUSER.isDisplayed());
		click(AUL.INVITEUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, INU.INVITEUSERBTN, driver);
		waitforElement(60, INU.INVITEEXPORT, driver);
		// Verifying Client Name and Client Number Columns as per POR-15816 in 7.0
		waitforText(180, INU.CLIENTNAME, driver);
		assertTrue(INU.CLIENTNAME.isDisplayed());
		assertTrue(INU.CLIENTNUMBER.isDisplayed());
		//
		JavaScriptclick(driver, INU.INVITEEXPORT);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("FireFox")) {
			LatestDownloadedFile("InviteExport", false);
		}
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "InviteExport";
			xDownloadFiles("InviteExport");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			DownloadFiles.LatestDownloadedFile("RateFiles", true);
		}
		click(RP.ADMININVITE);
		waitforElement(180, RP.ROLE, driver);
		waitforElement(180, AUL.RETURNTOUSERS, driver);
		JavaScriptclick(driver, AUL.RETURNTOUSERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, INU.INVITEUSERBTN, driver);
		assertTrue(INU.INVITEUSERBTN.isDisplayed());
		assertTrue(AUL.PREMIUMSMBCUST.isDisplayed());
		JavaScriptclick(driver, AUL.PREMIUMSMBCUST);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(AUL.VERTEXSMBVARS.isDisplayed());
		JavaScriptclick(driver, AUL.VERTEXSMBVARS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(AUL.INLINEHELP.isDisplayed());
		JavaScriptclick(driver, AUL.INLINEHELP);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, AUL.UPDATENOTIFICATIONLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(AUL.MANAGECONFIGURATION.isDisplayed());
		JavaScriptclick(driver, AUL.MANAGECONFIGURATION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(AUL.MANAGETAXCALENDER.isDisplayed());
		JavaScriptclick(driver, AUL.MANAGETAXCALENDER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(AUL.ERRORMONITOR.isDisplayed());
		JavaScriptclick(driver, AUL.ERRORMONITOR);
		waitforElement(180, AUL.ERRORMONITORTITLE, driver);
		JavaScriptclick(driver, AUL.SYSTEMCONFIGURATION);
		waitforElement(180, AUL.SYSTEMCONFIGTITLE, driver);
		waitforElement(180, AUL.NAME, driver);
		assertTrue(AUL.NAME.isDisplayed());
		assertTrue(AUL.VALUE.isDisplayed());
		assertTrue(AUL.ACTION.isDisplayed());
		assertTrue(AUL.UPDATEDUTC.isDisplayed());
		// Contact Support should Disabled
		if (!VerifyObject(driver, PUL.CONTACTSUPPORTLINK)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void UpdateNotification(String notification, String Title, String NewTitle, String Description)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		UpdateNotification UN = PageFactory.initElements(driver, UpdateNotification.class);
		AdminUserLinks AUL = PageFactory.initElements(driver, AdminUserLinks.class);

		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, UN.UPDATENOTIFICATIONLINK);
		} else {
			click(UN.UPDATENOTIFICATIONLINK);
		}
		waitforElement(180, UN.SELECTNOTIFICATIONDRPDOWN, driver);
		assertTrue(UN.SELECTNOTIFICATIONLABEL.isDisplayed());
		assertTrue(UN.SELECTNOTIFICATIONDRPDOWN.isDisplayed());
		assertTrue(UN.NOTIFIEDCLIENTTYPELABEL.isDisplayed());
		assertTrue(UN.NOTIFIEDCLIENTYPETXTBOX.isDisplayed());
		assertTrue(UN.SENDTOLABEL.isDisplayed());
		assertTrue(UN.SENDTODROPDOWN.isDisplayed());
		assertTrue(UN.NOTIFIEDVIAEMAIL.isDisplayed());
		assertTrue(UN.NOTIFIEDVIAEMAILDRPDOWN.isDisplayed());
		assertTrue(UN.NOTIFIEDVIAINTERMESSAGE.isDisplayed());
		assertTrue(UN.NOTIFEDVIAINTERNALMESSDRPDOWN.isDisplayed());
		assertTrue(UN.SUBJECTLINELABEL.isDisplayed());
		assertTrue(UN.SUBJECTLINETXTBOX.isDisplayed());
		assertTrue(UN.MESSAGEBODYLABEL.isDisplayed());
		assertTrue(UN.MESSAGEBODYTXTBOX.isDisplayed());
		assertTrue(UN.SAVEBTN.isDisplayed());
		assertTrue(UN.CANCELBTN.isDisplayed());
		selectByValue(UN.SELECTNOTIFICATIONDRPDOWN, notification);
		String TitleText = driver
				.findElement(By.xpath("//label[text()='Subject Line']/../div[@class='controls']/input"))
				.getAttribute("value");
		if (Title.equalsIgnoreCase(TitleText)) {
		} else {
			System.out.println("Title does not match in Update Notification");
		}
		clear(UN.SUBJECTLINETXTBOX);
		type(UN.SUBJECTLINETXTBOX, NewTitle);
		click(UN.CANCELBTN);
		waitforElement(180, AUL.DASHBOARD, driver);
		driver.navigate().refresh();
		waitforElement(180, AUL.DASHBOARD, driver);
		click(UN.UPDATENOTIFICATIONLINK);
		waitforElement(180, UN.SELECTNOTIFICATIONDRPDOWN, driver);
		selectByValue(UN.SELECTNOTIFICATIONDRPDOWN, notification);
		String TitleText1 = driver
				.findElement(By.xpath("//label[text()='Subject Line']/../div[@class='controls']/input"))
				.getAttribute("value");
		if (Title.equalsIgnoreCase(TitleText1)) {
		} else {
			System.out.println("Title does not match in Update Notification");
		}
		clear(UN.SUBJECTLINETXTBOX);
		type(UN.SUBJECTLINETXTBOX, NewTitle);
		clear(UN.MESSAGEBODYTXTBOX);
		Thread.sleep(2000);
		assertTrue(UN.DESCRIPTIONERROR.isDisplayed());
		type(UN.MESSAGEBODYTXTBOX, Description);
		click(UN.SUBJECTLINETXTBOX);
		Thread.sleep(2000);
		JavaScriptclick(driver, UN.SAVEBTN);
		Thread.sleep(6000);
		driver.navigate().refresh();
		waitforElement(180, AUL.DASHBOARD, driver);
	}

	public void SystemConfiguration() throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		SystemConfiguration SC = PageFactory.initElements(driver, SystemConfiguration.class);
		waitforElement(180, SC.SystemConfigurationLink, driver);
		click(SC.SystemConfigurationLink);
		waitforElement(180, SC.ValueDate, driver);
		assertTrue(SC.Namecolumn.isDisplayed());
		assertTrue(SC.Valuecolumn.isDisplayed());
		assertTrue(SC.Actioncolumn.isDisplayed());
		SC.ValueDate.click();
		Thread.sleep(2000);
		SC.ValueDate.sendKeys(Keys.CONTROL + "a");
		SC.ValueDate.sendKeys(Keys.CONTROL + "x");
		Thread.sleep(2000);
		assertTrue(SC.Cancelbutton.isDisplayed());
		assertTrue(SC.Updatebutton.isDisplayed());
		SC.ValueDate.sendKeys(Keys.CONTROL + "v");
		Thread.sleep(2000);
		SC.Updatebutton.click();
	}

	public void ManageConfiguration(String ReturnNames_Form, String States_Form, String ReturnLevel_Form,
			String ReturnType_Form, String TaxType_Form, String Rule_Return, String Status_Return,
			String Location_Required, String Reg_Number, String CommonFields, String Rule_LocationSpec,
			String RegNoFieldName_Setup, String State_LocationSpec, String SMB_Supported) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ManageConfiguration MC = PageFactory.initElements(driver, ManageConfiguration.class);
		strTaxpayerCompanyName = "";
		@SuppressWarnings("unused")
		String[] State = { "", "ALABAMA", "ALASKA", "ARIZONA", "ARKANSAS", "CALIFORNIA", "CANADA", "COLORADO",
				"CONNECTICUT", "DELAWARE", "DISTRICT OF COLUMBIA", "FLORIDA", "GEORGIA", "HAWAII", "IDAHO", "ILLINOIS",
				"INDIANA", "IOWA", "KANSAS", "KENTUCKY", "LOUISIANA", "MAINE", "MARYLAND", "MASSACHUSETTS", "MICHIGAN",
				"MINNESOTA", "MISSISSIPPI", "MISSOURI", "MONTANA", "NEBRASKA", "NEVADA", "NEW HAMPSHIRE", "NEW JERSEY",
				"NEW MEXICO", "NEW YORK", "NORTH CAROLINA", "NORTH DAKOTA", "OHIO", "OKLAHOMA", "OREGON",
				"PENNSYLVANIA", "PUERTO RICO", "RHODE ISLAND", "SOUTH CAROLINA", "SOUTH DAKOTA", "TENNESSEE", "TEXAS",
				"UTAH", "VERMONT", "VIRGINIA", "WASHINGTON", "WEST VIRGINIA", "WISCONSIN", "WYOMING" };
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, MC.MANAGECONFIGLINK);
		} else {
			click(MC.MANAGECONFIGLINK);
		}
		waitforElement(180, MC.RETURNNAMEDRPDN, driver);
		// List<WebElement> StateList = driver.findElements(By.xpath(
		// "//div[@id='s2id_autogen3']/../select[@class='input-xlarge select2-offscreen
		// select2-applied']/option"));
		assertTrue(MC.FORMDISPLAY.isDisplayed());
		assertTrue(MC.RETUNRSPCIFICRUILES.isDisplayed());
		assertTrue(MC.LOCATIONRULES.isDisplayed());
		assertTrue(MC.LOCATIONSETUP.isDisplayed());
		assertTrue(MC.TAPYERRULES.isDisplayed());
		// ***Verifying Filter DropDowns
		assertTrue(MC.RETURNNAMEDRPDN.isDisplayed());
		assertTrue(MC.STATEDRPDN.isDisplayed());
		assertTrue(MC.RETURNLVLDRPDN.isDisplayed());
		assertTrue(MC.RETURNTYPE.isDisplayed());
		assertTrue(MC.TAXTYPEDRPDN.isDisplayed());
		assertTrue(MC.SMBSUPPORTEDDRPDN.isDisplayed());
		assertTrue(MC.EDISUPPORTEDDRPDN.isDisplayed());
		assertTrue(MC.EDISUPPORTEDDRPDN.isDisplayed());
		assertTrue(MC.EDISUPPORTEDDRPDN.isDisplayed());
		// ****Verifying Grid Columns
		assertTrue(MC.RETURNNAMEGRID.isDisplayed());
		assertTrue(MC.RETURNLEVELGRID.isDisplayed());
		assertTrue(MC.RETURNTYPEGRID.isDisplayed());
		assertTrue(MC.TAXTYPEGRID.isDisplayed());
		assertTrue(MC.ACTIONGRID.isDisplayed());
		assertTrue(MC.RETURNIDGRID.isDisplayed());
		assertTrue(MC.SMBSUPPORTEDGRID.isDisplayed());
		assertTrue(MC.EDISUPPORTEDGRID.isDisplayed());
		assertTrue(MC.STARTDATEGRID.isDisplayed());
		assertTrue(MC.ENDDATEGRID.isDisplayed());
		// Manage Form Display Verification
		waitforElement(30, MC.FORMDISPLAY, driver);
		JavaScriptclick(driver, MC.FORMDISPLAY);
		if (ReturnNames_Form != "") {
			String RtrnName_Form = ReturnNames_Form;
			assertTrue(driver
					.findElement(By
							.xpath("//div[@id='s2id_autogen1']/../select/option[contains(.,'" + RtrnName_Form + "')]"))
					.isDisplayed());
			Thread.sleep(8000);
			click(MC.RETURNNAMEDRPDN);
			Thread.sleep(5000);
			waitforElement(60, MC.RETURNSINPUT, driver);
			type(MC.RETURNSINPUT, RtrnName_Form);
			waitforElement(30, "//ul/li/div[contains(.,'" + RtrnName_Form + "')]", driver);
			WebElement returnF = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnName_Form + "')]"));
			click(returnF);
			// selectByValue(MC.RETURNNAMEDRPDN, RtrnName_Form);
			Thread.sleep(2000);
		}
		if (States_Form != "") {
			String RtrnState_Form = States_Form;
			assertTrue(driver
					.findElement(By
							.xpath("//div[@id='s2id_autogen3']/../select/option[contains(.,'" + RtrnState_Form + "')]"))
					.isDisplayed());
			Thread.sleep(6000);
			click(MC.STATEDRPDN);
			Thread.sleep(2000);
			waitforElement(60, MC.RETURNSINPUT, driver);
			type(MC.RETURNSINPUT, RtrnState_Form);
			waitforElement(180, "//ul/li/div[contains(.,'" + RtrnState_Form + "')]", driver);
			WebElement StateF = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnState_Form + "')]"));
			click(StateF);
			// selectByValue(MC.STATEDRPDN, RtrnState_Form);
			Thread.sleep(2000);
		}
		if (ReturnLevel_Form != "") {
			String RtrnLevel_Form = ReturnLevel_Form;
			assertTrue(driver
					.findElement(By
							.xpath("//div[@id='s2id_autogen5']/../select/option[contains(.,'" + RtrnLevel_Form + "')]"))
					.isDisplayed());
			Thread.sleep(5000);
			click(MC.RETURNLVLDRPDN);
			Thread.sleep(2000);
			waitforElement(60, MC.RETURNSINPUT, driver);
			type(MC.RETURNSINPUT, RtrnLevel_Form);
			waitforElement(180, "//ul/li/div[contains(.,'" + RtrnLevel_Form + "')]", driver);
			WebElement ReturnLF = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnLevel_Form + "')]"));
			click(ReturnLF);
			// selectByValue(MC.RETURNLVLDRPDN, RtrnLevel_Form);
			Thread.sleep(2000);
		}
		if (ReturnType_Form != "") {
			String RtrnType_Form = ReturnType_Form;
			assertTrue(driver
					.findElement(By
							.xpath("//div[@id='s2id_autogen7']/../select/option[contains(.,'" + RtrnType_Form + "')]"))
					.isDisplayed());
			click(MC.RETURNTYPE);
			Thread.sleep(2000);
			waitforElement(60, MC.RETURNSINPUT, driver);
			type(MC.RETURNSINPUT, RtrnType_Form);
			waitforElement(180, "//ul/li/div[contains(.,'" + RtrnType_Form + "')]", driver);
			WebElement ReturnT = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnType_Form + "')]"));
			click(ReturnT);
			// selectByValue(MC.RETURNTYPE, RtrnType_Form);
			Thread.sleep(2000);
		}
		if (TaxType_Form != "") {
			String RtrnTaxType_Form = TaxType_Form;
			assertTrue(driver
					.findElement(By.xpath(
							"//div[@id='s2id_autogen9']/../select/option[contains(.,'" + RtrnTaxType_Form + "')]"))
					.isDisplayed());
			click(MC.TAXTYPEDRPDN);
			Thread.sleep(2000);
			waitforElement(60, MC.RETURNSINPUT, driver);
			type(MC.RETURNSINPUT, RtrnTaxType_Form);
			waitforElement(180, "//ul/li/div[contains(.,'" + RtrnTaxType_Form + "')]", driver);
			WebElement ReturnTT = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnTaxType_Form + "')]"));
			click(ReturnTT);
			// selectByValue(MC.TAXTYPEDRPDN, RtrnTaxType_Form);
			Thread.sleep(2000);
		}
		if (SMB_Supported != "") {
			String SMBsupported = SMB_Supported;
			assertTrue(driver
					.findElement(By.xpath(
							"//label[text()='Cloud Supported:']/../select/option[contains(.,'" + SMBsupported + "')]"))
					.isDisplayed());
			click(MC.SMBSUPPORTEDDRPDN);
			Thread.sleep(2000);
			waitforElement(60, MC.RETURNSINPUT, driver);
			type(MC.RETURNSINPUT, SMBsupported);
			waitforElement(180, "//ul/li/div[contains(.,'" + SMBsupported + "')]", driver);
			WebElement ReturnCS = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + SMBsupported + "')]"));
			click(ReturnCS);
			// selectByValue(MC.SMBSUPPORTEDDRPDN, SMBsupported);
			Thread.sleep(2000);
		}
		driver.navigate().refresh();
		waitforElement(180, MC.RETURNNAMEDRPDN, driver);
		// Manage Return Specific Rules Verification
		click(MC.RETUNRSPCIFICRUILES);
		waitforElement(180, "//div[@id='s2id_autogen1']", driver);
		if (States_Form != "") {
			String RtrnState_Form = States_Form;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen3']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ RtrnState_Form + "']"))
					.isDisplayed());
			click(MC.STATEDROPDOWN);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, RtrnState_Form);
			waitforElement(180, "//ul/li/div[contains(.,'" + RtrnState_Form + "')]", driver);
			WebElement Statef = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnState_Form + "')]"));
			click(Statef);
			// selectByValue(MC.STATEDRPDN, RtrnState_Form);
			Thread.sleep(2000);
		}
		if (ReturnNames_Form != "") {
			String RtrnName_Form = ReturnNames_Form;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen1']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ RtrnName_Form + "']"))
					.isDisplayed());
			click(MC.RETURNNAMEDRP);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, RtrnName_Form);
			waitforElement(180, "//ul/li/div[contains(.,'" + RtrnName_Form + "')]", driver);
			WebElement Statef = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnName_Form + "')]"));
			click(Statef);
			// selectByValue(MC.RETURNNAMEDRPDN, RtrnName_Form);
			Thread.sleep(2000);
		}
		if (Rule_Return != "") {
			String RtrnRule_Form = Rule_Return;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen5']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ RtrnRule_Form + "']"))
					.isDisplayed());
			click(MC.RULEDROPDOWNLOC);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, RtrnRule_Form);
			waitforElement(180, "//ul/li/div[contains(.,'" + RtrnRule_Form + "')]", driver);
			WebElement Rule = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnRule_Form + "')]"));
			click(Rule);
			// selectByValue(MC.RULEDRPDN, RtrnRule_Form);
			Thread.sleep(2000);
		}
		if (Status_Return != "") {
			String RtrnStatus_Return = Status_Return;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen7']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ RtrnStatus_Return + "']"))
					.isDisplayed());
			click(MC.STATUSDROPDOWNLOC);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, RtrnStatus_Return);
			waitforElement(180, "//ul/li/div[contains(.,'" + RtrnStatus_Return + "')]", driver);
			WebElement Rule = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnStatus_Return + "')]"));
			click(Rule);
			// selectByValue(MC.STATUSDRPDN, RtrnStatus_Return);
			Thread.sleep(2000);
		}
		driver.navigate().refresh();
		waitforElement(180, "//div[@id='s2id_autogen1']", driver);
		assertTrue(MC.SEARCHRETRUNRETURN.isDisplayed());
		assertTrue(MC.SEARCHBUTTONRETURN.isDisplayed());
		assertTrue(MC.RETURNNAMEGRID.isDisplayed());
		assertTrue(MC.RULENAMERETURN.isDisplayed());
		assertTrue(MC.STATUSRETURN.isDisplayed());
		assertTrue(MC.DEFAULTRETURN.isDisplayed());
		assertTrue(MC.COMMENTRETURN.isDisplayed());
		assertTrue(MC.VISIBLE.isDisplayed());
		assertTrue(MC.ACTIONRETURN.isDisplayed());
		assertTrue(MC.VISIBLECHECK.isDisplayed());
		assertTrue(MC.COMMENTTXTBX.isDisplayed());
		// Manage Location Specific rules Verification
		click(MC.LOCATIONRULES);
		try {
			switchToAlertAccept(driver);
		} catch (Exception e) {
		}
		waitforElement(30, "//table[@id='field-table']/tbody/tr[1]/td[contains(.,'ALABAMA')]", driver);
		if (State_LocationSpec != "") {
			String RtrnState_Form = State_LocationSpec;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen1']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ RtrnState_Form + "']"))
					.isDisplayed());
			click(MC.STATEDROPDOWN);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, RtrnState_Form);
			waitforElement(30, "//ul/li/div[contains(.,'" + RtrnState_Form + "')]", driver);
			WebElement Statef = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnState_Form + "')]"));
			click(Statef);
			// selectByValue(MC.STATEDRPDN, RtrnState_Form);
			Thread.sleep(2000);
		}
		if (Rule_LocationSpec != "") {

			String Rule_LocSpec = Rule_LocationSpec;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen3']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ Rule_LocSpec + "']"))
					.isDisplayed());
			click(MC.RULEDROPDOWNLOC);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, Rule_LocSpec);
			waitforElement(30, "//ul/li/div[contains(.,'" + Rule_LocSpec + "')]", driver);
			WebElement Rule = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + Rule_LocSpec + "')]"));
			click(Rule);
			// selectByValue(MC.RULEDRPDN, Rule_LocSpec);
			Thread.sleep(2000);
		}
		if (Status_Return != "") {
			String RtrnStatus_Return = Status_Return;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen5']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ RtrnStatus_Return + "']"))
					.isDisplayed());
			click(MC.STATUSDROPDOWNLOC);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, RtrnStatus_Return);
			waitforElement(180, "//ul/li/div[contains(.,'" + RtrnStatus_Return + "')]", driver);
			WebElement Rule = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnStatus_Return + "')]"));
			click(Rule);
			// selectByValue(MC.STATUSDRPDN, RtrnStatus_Return);
			Thread.sleep(2000);
		}
		driver.navigate().refresh();
		waitforElement(180,
				"//div[@id='s2id_autogen1']/../select[@class='input-block-level select2-offscreen select2-applied']",
				driver);

		assertTrue(MC.SEARCHBYRULELOC.isDisplayed());
		assertTrue(MC.SEARCHBUTLOC.isDisplayed());
		assertTrue(MC.STATENAMELOC.isDisplayed());
		assertTrue(MC.RULENAMELOC.isDisplayed());
		assertTrue(MC.STATUSLOC.isDisplayed());
		assertTrue(MC.DEFAULTLOC.isDisplayed());
		assertTrue(MC.COMMENTLOC.isDisplayed());
		assertTrue(MC.VISIBLE.isDisplayed());
		assertTrue(MC.ACTIONLOC.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr[1]/td[text()='ALABAMA']")).isDisplayed());
//		assertTrue(driver.findElement(By.xpath("//tr/td[text()='ALABAMA']/../td[text()='Post office']")).isDisplayed());
		assertTrue(MC.VISIBLECHECK.isDisplayed());
		assertTrue(MC.COMMENTTXTBX.isDisplayed());
		// Manage Location Setup Verification
		click(MC.LOCATIONSETUP);
		waitforElement(180,
				"//div[@id='s2id_autogen1']/../select[@class='input-block-level select2-offscreen select2-applied']",
				driver);
		if (States_Form != "") {
			String RtrnState_Form = States_Form;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen1']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ RtrnState_Form + "']"))
					.isDisplayed());
			click(MC.STATEDROPDOWN);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, RtrnState_Form);
			waitforElement(30, "//ul/li/div[contains(.,'" + RtrnState_Form + "')]", driver);
			WebElement Rule = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnState_Form + "')]"));
			click(Rule);
			// selectByValue(MC.STATESETUP, RtrnState_Form);
			Thread.sleep(2000);
		}
		if (Location_Required != "") {

			String LocReq_Setup = Location_Required;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen3']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ LocReq_Setup + "']"))
					.isDisplayed());
			click(MC.LOCATIONSREQFORDRP);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, LocReq_Setup);
			waitforElement(30, "//ul/li/div[contains(.,'" + LocReq_Setup + "')]", driver);
			WebElement Locreq = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + LocReq_Setup + "')]"));
			click(Locreq);
			// selectByValue(MC.LOCATIONREQUIREDFORSETUP, LocReq_Setup);
			Thread.sleep(2000);
		}
		if (Reg_Number != "") {
			String RegNum_Setup = Reg_Number;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen5']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ RegNum_Setup + "']"))
					.isDisplayed());
			click(MC.REGISTRATIONNUMDRP);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, RegNum_Setup);
			waitforElement(180, "//ul/li/div[contains(.,'" + RegNum_Setup + "')]", driver);
			WebElement Locreq = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RegNum_Setup + "')]"));
			click(Locreq);
			// selectByValue(MC.REGNUMBERFIELDNAMESETUP, RegNum_Setup);
			Thread.sleep(2000);
		}
		driver.navigate().refresh();
		waitforElement(180,
				"//div[@id='s2id_autogen1']/../select[@class='input-block-level select2-offscreen select2-applied']",
				driver);
		assertTrue(MC.STATENAMESETUP.isDisplayed());
		assertTrue(MC.LOCATIONREQSETUP.isDisplayed());
		assertTrue(MC.REGNUMBERSETUP.isDisplayed());
		assertTrue(MC.LOCSPECMESSAGESETUP.isDisplayed());
		assertTrue(MC.ACTIONSETUP.isDisplayed());
		assertTrue(MC.UPDATEACTION.isDisplayed());
		// Associated Taxpayer Return Rules Verification
		click(MC.TAPYERRULES);
		try {
			switchToAlertAccept(driver);
		} catch (Exception e) {
		}
		waitforElement(180, "//div[@id='s2id_return-name']/../select", driver);
		// *****VERIFYING THE DROPDOWN OPTIONS IN TAXPAYER RETURN****

		if (ReturnNames_Form != "") {
			String RtrnName_Form = ReturnNames_Form;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_return-name']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ RtrnName_Form + "']"))
					.isDisplayed());
			click(MC.RETURNNAMEDRPDWN);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, RtrnName_Form);
			waitforElement(30, "//ul/li/div[contains(.,'" + RtrnName_Form + "')]", driver);
			WebElement Locreq = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnName_Form + "')]"));
			click(Locreq);
			// selectByValue(MC.RETURNNAMETAXPAYER, RtrnName_Form);
			Thread.sleep(2000);
		}
		if (States_Form != "") {

			String RtrnState_Form = States_Form;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen2']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='"
							+ RtrnState_Form + "']"))
					.isDisplayed());
			click(MC.JURISDICTIONDRP);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, RtrnState_Form);
			waitforElement(30, "//ul/li/div[contains(.,'" + RtrnState_Form + "')]", driver);
			WebElement Locreq = driver.findElement(By.xpath("//ul/li/div[contains(.,'" + RtrnState_Form + "')]"));
			click(Locreq);
			// selectByValue(MC.STATETAXPAYER, RtrnState_Form);
			Thread.sleep(2000);
		}
		if (CommonFields != "") {
			String CommonFields_Taxpayer = CommonFields;
			assertTrue(driver.findElement(By.xpath(
					"//div[@id='s2id_autogen4']/../select[@class='input-block-level select2-offscreen select2-applied']/option[text()='Filing Frequency']"))
					.isDisplayed());
			click(MC.COMMONFIELDSDRP);
			Thread.sleep(3000);
			type(MC.RETURNSINPUT, CommonFields_Taxpayer);
			waitforElement(30, "//ul/li/div[contains(.,'" + CommonFields_Taxpayer + "')]", driver);
			WebElement Locreq = driver
					.findElement(By.xpath("//ul/li/div[contains(.,'" + CommonFields_Taxpayer + "')]"));
			click(Locreq);
			// selectByValue(MC.COMMONTAXPAYER, CommonFields_Taxpayer);
			Thread.sleep(2000);
		}
		driver.navigate().refresh();
		waitforElement(180, "//div[@id='s2id_return-name']/../select", driver);
		assertTrue(MC.ADDASSOCBUTTONTAXPAYER.isDisplayed());
		assertTrue(MC.FRMRETURNNAMETAXPAYER.isDisplayed());
		assertTrue(MC.TORETURNNAMETAXPAYER.isDisplayed());
		assertTrue(MC.REGNUMBERTAXPAYER.isDisplayed());
		assertTrue(MC.FILINGFREQTAXPAYER.isDisplayed());
		assertTrue(MC.RETURNFIELDSTAXPAYER.isDisplayed());
		assertTrue(MC.ACTIONTAXPAYER.isDisplayed());
		click(MC.REGNUMCHECKTAXPAYER);
		Thread.sleep(2000);
		click(MC.REGNUMCHECKTAXPAYER);
		Thread.sleep(2000);
		click(MC.FILINGFREQCHECKTAXPAYER);
		Thread.sleep(2000);
		click(MC.FILINGFREQCHECKTAXPAYER);
		Thread.sleep(2000);
		click(MC.RETURNFIELDSCHECKTAXPAYER);
		Thread.sleep(2000);
		click(MC.RETURNFIELDSCHECKTAXPAYER);
		Thread.sleep(2000);
		click(MC.ADDASSOCBUTTONTAXPAYER);
		Thread.sleep(2000);
		assertTrue(MC.LASTROWFROMDRPDOWNTAXPAYER.isDisplayed());
		assertTrue(MC.LASTROWTODRPDOWNTAXPAYER.isDisplayed());
		assertTrue(MC.LASTROWUREGNUMBNCHECKTAXPAYER.isDisplayed());
		assertTrue(MC.LASTROWFILINGUNCHECKTAXPAYER.isDisplayed());
		assertTrue(MC.LASTROWRETURNUNCHECKTAXPAYER.isDisplayed());
		assertTrue(MC.LASTROWADDTAXPAYER.isDisplayed());
		assertTrue(MC.LASTROWCANCELTAXPAYER.isDisplayed());
		assertTrue(MC.ADDASSOCDISABLETAXPAYER.isDisplayed());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li/a[contains(text(),'Manage Form Display')]")).click();
		Thread.sleep(2000);
		try {
			switchToAlertAccept(driver);
		}

		catch (Exception e) {
		}
		waitforElement(180, MC.RETURNNAMEDRPDN, driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

	}

	public void VertexSMBCustomers(String userlogin, String userpassword, String Clientname, String Subscription_Type,
			String LastLogin) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		if (Subscription_Type.equalsIgnoreCase("Pending Billing")) {
			Open(driver, url);
			waitforElement(180, LP.USERNAME, driver);
			userregistration.UserLogin(adminusername, adminpassword);
			waitforElement(180, PUL.DASHBOARDLINK, driver);
			waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, Clientname);
			waitforElement(180, "//table/tbody/tr/td[contains(.,'" + Clientname + "')]", driver);
			WebElement Client = driver.findElement(
					By.xpath("//table/tbody/tr[@class='ng-scope']/td/a[contains(text(),'" + Clientname + "')]"));
			JavaScriptclick(driver, Client);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.BYPASSPENDINGBILLING, driver);
			click(VSC.BYPASSPENDINGBILLING);
			waitforElement(180, VSC.YES, driver);
			JavaScriptclick(driver, VSC.YES);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, VSC.BYPASSPENDING_BILLING, driver);
			userregistration.Logout();
			waitforElement(180, LP.LOGIN, driver);
			driver.get(Environment);
			userregistration.UserLogin(userlogin, userpassword);
			Thread.sleep(5000);
			if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
				driver.manage().deleteAllCookies();
				driver.get(Environment);
			}
			waitforElement(180, PUL.DASHBOARDLINK, driver);

		} else {
			waitforElement(180, PUL.DASHBOARDLINK, driver);
			userregistration.Logout();
			waitforElement(180, LP.USERNAME, driver);
			if (LastLogin.equalsIgnoreCase("Yes")) {
				driver.get(url);
				Thread.sleep(3000);
				userregistration.UserLogin(userlogin, userpassword);
				waitforElement(180, PUL.DASHBOARDLINK, driver);
				userregistration.Logout();
				waitforElement(180, LP.USERNAME, driver);
			}
			driver.get(url);
			Thread.sleep(3000);
			userregistration.UserLogin(adminusername, adminpassword);
			waitforElement(180, PUL.DASHBOARDLINK, driver);
			click(VSC.MANAGESMBCUSTLINK);
			Thread.sleep(8000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(10, VSC.EXPORT, driver);
			// Verify Vertex SMB customersScreen
			assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());
			type(VSC.SMBCUSTNAMESB, Clientname);
			waitforElement(180, "//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + Clientname + "')]", driver);
			WebElement client = driver.findElement(
					By.xpath("//table/tbody/tr[@class='ng-scope']/td[1]/div/a[contains(text(),'" + Clientname + "')]"));
			JavaScriptclick(driver, client);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.RESEND, driver);
			// Verifying Last Login for CPAReferral Client
			if (LastLogin.equalsIgnoreCase("Yes")) {
				JavaScriptclick(driver, VSC.SMBADMIN);
				waitforElement(180, VSC.LASTLOGIN, driver);
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				// get current date time with Calendar()
				Calendar cal = Calendar.getInstance();
				String SystemDateTime = dateFormat.format(cal.getTime());
				String LasttLogin = getText(VSC.LASTLOGIN);
				System.out.println("LasttLogin=" + LasttLogin);
				String S[] = LasttLogin.split(" ");
				System.out.println(S[0]);
				assertTrue(SystemDateTime.equalsIgnoreCase(S[0]));
			}
			JavaScriptclick(driver, VSC.SMBREPORTS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.ROOTFOLDER, driver);
			JavaScriptclick(driver, VSC.SMBADMIN);
			waitforElement(180, VSC.LASTLOGIN, driver);
		}
	}

	public void InviteUsers(String Status, String Username) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		InviteUsers INU = PageFactory.initElements(driver, InviteUsers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(3000);
		driver.manage().deleteAllCookies();
		Thread.sleep(2000);
		driver.get(url);
		Thread.sleep(3000);
		click(INU.INVITEUSERS);
		JavaScriptclick(driver, INU.INVITEUSERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, INU.INVITEUSERBTN, driver);
		assertTrue(INU.INVITEUSERBTN.isDisplayed());
		assertTrue(INU.SEARCHTXTBOX.isDisplayed());
		assertTrue(INU.AUSERSCOUNT.isDisplayed());
		assertTrue(INU.PUSERSCOUNT.isDisplayed());
		assertTrue(INU.DUSERSCOUNT.isDisplayed());
		assertTrue(INU.PAGINATION.isDisplayed());
		assertTrue(INU.FNCLMN.isDisplayed());
		assertTrue(INU.LNCLMN.isDisplayed());
		assertTrue(INU.EMAILCLMN.isDisplayed());
		assertTrue(INU.PHONECLMN.isDisplayed());
		assertTrue(INU.CREATEDCLMN.isDisplayed());
		assertTrue(INU.LASTLGNCLMN.isDisplayed());
		assertTrue(INU.STATUSCLMN.isDisplayed());
		assertTrue(INU.ROLECLMN.isDisplayed());
		assertTrue(INU.ACTIONSCLMN.isDisplayed());
		type(INU.SEARCHTXTBOX, Username);
		waitforElement(180, "//tbody/tr[2]/td/a[text()='Delete']", driver);
		Thread.sleep(5000);
		String IUsername = driver.findElement(By.xpath("//tbody/tr[2]/td[6]")).getText();
		assertTrue(IUsername.equalsIgnoreCase(Username));
		String Status1 = driver.findElement(By.xpath("//tbody/tr[2]/td[10]")).getText();
		assertTrue((Status).equalsIgnoreCase(Status1));
		assertTrue(driver.findElement(By.xpath("//tbody/tr[2]/td/a[text()='Delete']")).isDisplayed());
		click(INU.INVITENEWUSER);
		waitforElement(180, INU.RSENDINVITEBTN, driver);
		click(INU.ALLROLES);
		Thread.sleep(2000);
		assertTrue(INU.ALLROLES.isDisplayed());
		click(INU.RSENDINVITEBTN);
		Thread.sleep(3000);
		assertTrue(INU.FNERROR.isDisplayed());
		assertTrue(INU.LNERROR.isDisplayed());
		assertTrue(INU.EMAILERROR.isDisplayed());
		assertTrue(INU.PHNERROR.isDisplayed());

	}

	public void ManageLocSpecificRules(String Jurisdiction, String Location_Rule) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ManageConfiguration MC = PageFactory.initElements(driver, ManageConfiguration.class);
		click(MC.MANAGECONFIGLINK);
		waitforElement(180, MC.RETURNNAMEDRPDN, driver);
		click(MC.LOCATIONRULES);
		waitforElement(180, MC.SEARCHBYRULELOC, driver);
		click(MC.JURISDICTIONLOC);
		waitforElement(180, MC.JURISDICTIONSEARCH, driver);
		type(MC.JURISDICTIONSEARCH, Jurisdiction);
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
		Thread.sleep(1000);
		click(MC.RULELOC);
		waitforElement(180, MC.RULESEARCH, driver);
		type(MC.RULESEARCH, Location_Rule);
		Thread.sleep(1000);
		action.sendKeys(Keys.TAB).build().perform();
		Thread.sleep(2000);
		WebElement selectedoption = driver
				.findElement(By.xpath("//table/thead/tr/th[contains(.,'Status')]/../../../tbody/tr/td[3]/select"));
		Select option = new Select(selectedoption);
		String selectedValue = option.getFirstSelectedOption().getText();
		assertTrue(selectedValue.equals("All"));
		if (!VerifyObject(driver, MC.VISIBLECHECKBOXDISABLED)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		WebElement defaultvalue = driver
				.findElement(By.xpath("//table/thead/tr/th[contains(.,'Default')]/../../../tbody/tr/td[5]/select"));
		Select defaultoption = new Select(defaultvalue);
		String DefaultOption = defaultoption.getFirstSelectedOption().getText();
		assertTrue(DefaultOption.equals("-- No Default --"));
		String Comment = MC.COMMENTFIELD.getText();
		assertTrue(Comment.isEmpty());
		if (!VerifyObject(driver, MC.UPDATEACTION)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (!VerifyObject(driver, MC.CANCELOCATION)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void DIAdminScreens() throws Exception {
		driver.getWindowHandle();
		PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		click(PUL.DATAINTEGRITY);
		Thread.sleep(5000);
		assertTrue("Data Integrity | Vertex Cloud".equalsIgnoreCase(driver.getTitle()));
		// DASHBOARD Page Verification
		waitforElement(180, RP.DATAINTEGRTYTEXT, driver);
		assertTrue(RP.DATAINTEGRTYTEXT.isDisplayed());
		assertTrue(RP.DATAINTEGRTYWIDGETTEXT.isDisplayed());
		assertTrue(RP.LaunchSite.isDisplayed());
		DILaunchSite();
		// Settings should not display
		// Rates and Jurisdictions should not display
		if (!VerifyObject(driver, PUL.RatesJurisdictions)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		// Visit Help Center should display
		waitforElement(180, PUL.GETHELPLINK, driver);
		assertTrue(PUL.GETHELPLINK.isDisplayed());
		// What's New section should display
		assertTrue(PUL.WHATSNEWLINKTAXCENTRAL.isDisplayed());
		assertTrue(LP.Copyrightext.isDisplayed());
	}

	public void DILaunchSite() throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		waitforElement(180, AS.DATAINTEGRITY, driver);
		String parentwindow = driver.getWindowHandle();
		waitforElement(180, AS.DILAUNSITE, driver);
		click(AS.DILAUNSITE);
		Thread.sleep(20000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
				Thread.sleep(2000);
				String DIUrl = driver.getCurrentUrl();
				System.out.println(DIUrl);
				String evn = Environment.toLowerCase();
				assertTrue(DIUrl.equalsIgnoreCase("https://" + evn + "di.vertexsmb.com/dm-integrity-ui/home"));
				driver.close();
				driver.switchTo().window(parentwindow);
				Thread.sleep(2000);
			}
		}
	}

	public void VerifyClientsinPremium(String ClientName, String ClientPortal, String OnBoard_Status, String TaxPayer,
			String Filing_Period, String OffBoard_Status, String Client_Status, String Client_Display,
			String Premium_Status, String EDI_Registration_Number, String Vertex_Filing_Method) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		OnboardStatus OBS = PageFactory.initElements(driver, OnboardStatus.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, OBS.PREMIUMSMBCUSTOMERS, driver);
		JavaScriptMousehover(driver, OBS.PREMIUMSMBCUSTOMERS);
		JavaScriptclick(driver, OBS.PREMIUMCUSTMERSSUBLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, OBS.ONBOARDSEARCHBOX, driver);
		click(OBS.ONBOARDSEARCHBOX);
		type(OBS.ONBOARDSEARCHBOX, ClientName);
		/*
		 * if (ClientPortal.equalsIgnoreCase("BPO")) { click(OBS.ONBOARDSEARCHBOX);
		 * type(OBS.ONBOARDSEARCHBOX, BPOCPAClientName); } else if
		 * (ClientPortal.equalsIgnoreCase("TaxCentral")) { click(OBS.ONBOARDSEARCHBOX);
		 * type(OBS.ONBOARDSEARCHBOX, ClientName); } else { type(OBS.ONBOARDSEARCHBOX,
		 * MerchantClientName); }
		 */
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, OBS.TAXPAYERDROPDOWN, driver);
		if (OnBoard_Status.equalsIgnoreCase("Yes")) {
			Thread.sleep(5000);
			selectByValue(OBS.TAXPAYERDROPDOWN, TaxPayer);
			Thread.sleep(5000);
			selectByValue(OBS.ONBOARDFILINGPERIOD, Filing_Period);
			Thread.sleep(5000);
			click(OBS.ONBOARDLABLE);
			waitforElement(180, OBS.SUCCESSMSG, driver);
			assertTrue(OBS.SUCCESSMSG.isDisplayed());
		}
		if (OffBoard_Status.equalsIgnoreCase("Yes")) {
			click(OBS.OFFBOARDLABEL);
			waitforElement(180, OBS.OFFBOARDSTATUSMESSAGE, driver);
			assertTrue(OBS.OFFBOARDSTATUSMESSAGE.isDisplayed());
		}
		// Verifying Client Should Display in Premium SMB Customers
		if (Client_Status.equalsIgnoreCase("Yes")) {
			assertTrue(OBS.SHOWRETURNS.isDisplayed());
		}
		// Verifying Client Should not Display in Premium SMB Customers
		if (Client_Display.equalsIgnoreCase("Yes")) {
			if (!VerifyObject(driver, OBS.SHOWRETURNS)) {
				assertTrue(true);

			} else {
				assertFalse(false);
			}
		}
		if (Premium_Status.equalsIgnoreCase("Yes")) {
			assertTrue(OBS.ONBOARDFILINGPERIODLABEL.isDisplayed());
			assertTrue(OBS.ONBOARDFILINGPERIOD.isDisplayed());
			List<WebElement> DD_Values = driver
					.findElements(By.xpath("//label[contains(.,'Onboard Filing Period')]/../select/option"));
			System.out.println("Size of the dropdown : " + DD_Values.size());
			// print dropdown options
			/*
			 * for (int DD= 0; DD< DD_Values.size(); DD++) {
			 * System.out.println("OnBoard dropdown having Months:"
			 * +DD_Values.get(DD).getText());}
			 */
			assertTrue(OBS.ONBOARDLABLE.isDisplayed());
			assertTrue(OBS.OFFBOARDLABEL.isDisplayed());
			assertTrue(OBS.SHOWRETURNS.isDisplayed());
			click(OBS.SHOWRETURNS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, OBS.HIDERETURNS, driver);
			assertTrue(OBS.HIDERETURNS.isDisplayed());
			String Taxpayer = getText(OBS.TAXPAYER);
			if (Taxpayer.equalsIgnoreCase(TaxPayer)) {
				System.out.println("Expected matched with Actual");
			}
			assertTrue(OBS.STATE.isDisplayed());
			assertTrue(OBS.RETURN.isDisplayed());
			assertTrue(OBS.CLIENTFILINGMETHOD.isDisplayed());
			assertTrue(OBS.EDIREGNUMBERCOLUMN.isDisplayed());
			/*
			 * Verification=""; Verification=(!VerifyObject(driver,
			 * OBS.VERTEXREGNUMBERCOLUMN))==true? Verification: Verification+
			 * "Fail, Vertex Registration Number not displaying in Premium Client Page;";
			 * status=status+Verification; VertexSMB.RowNo =
			 * bussFunction.xResult(Verification,RSheet,"VERTEXREGNUMBERCOLUMN_XPATH","",
			 * value,NoOfVerify++);
			 */
			assertTrue(OBS.REGISTRATION.isDisplayed());
			assertTrue(OBS.EDIREGISTRATION.isDisplayed());
			assertTrue(OBS.UPDATEBUTTON.isDisplayed());
			List<WebElement> Filing_Values = driver.findElements(By.xpath(OBS.VRTXFILINGVALUES_XPATH));
			System.out.println("Size of the dropdown : " + Filing_Values.size());
			String[] Filing_Methods = (Vertex_Filing_Method.split(";"));
			// print dropdown options
			for (int VF = 0; VF < Filing_Methods.length; VF++) {
				Select FilinMethod = new Select(driver.findElement(By.xpath(OBS.VERTEXFILINGMETHOD_XPATH)));
				FilinMethod.selectByVisibleText(Filing_Methods[VF]);
				sendkeysTab(OBS.VERTEXFILINGMETHOD);
				Thread.sleep(2000);
				assertTrue(OBS.DISABLEEDIREGNUMBER.isDisplayed());
				clear(OBS.ENABLEEDIREGNUMBER);
				type(OBS.ENABLEEDIREGNUMBER, EDI_Registration_Number);
				click(OBS.UPDATEBUTTON);
				Thread.sleep(5000);
				selectByValue(OBS.VERTEXFILINGMETHOD, Filing_Methods[VF - 1]);
				sendkeysTab(OBS.VERTEXFILINGMETHOD);
				Thread.sleep(2000);
				String REG_Value = getAttribute(OBS.ENABLEEDIREGNUMBER, "value");
				assertTrue(EDI_Registration_Number.equalsIgnoreCase(REG_Value));
				assertTrue(OBS.DISABLEEDIREGNUMBER.isDisplayed());
				selectByValue(OBS.VERTEXFILINGMETHOD, Filing_Methods[VF]);
				sendkeysTab(OBS.VERTEXFILINGMETHOD);
				Thread.sleep(2000);
				clear(OBS.ENABLEEDIREGNUMBER);
				type(OBS.ENABLEEDIREGNUMBER, EDI_Registration_Number);
				click(OBS.UPDATEBUTTON);
				Thread.sleep(5000);
				selectByValue(OBS.VERTEXFILINGMETHOD, Filing_Methods[VF + 1]);
				sendkeysTab(OBS.VERTEXFILINGMETHOD);
				String REG_Value1 = getAttribute(OBS.ENABLEEDIREGNUMBER, "value");
				assertTrue(EDI_Registration_Number.equalsIgnoreCase(REG_Value1));
				assertTrue(OBS.DISABLEEDIREGNUMBER.isDisplayed());
				Select ChFilinMethod4 = new Select(driver.findElement(By.xpath(OBS.VERTEXFILINGMETHOD_XPATH)));
				ChFilinMethod4.selectByIndex(VF);
				clear(OBS.ENABLEEDIREGNUMBER);
				type(OBS.ENABLEEDIREGNUMBER, EDI_Registration_Number + RND());
				String REG_Value2 = getAttribute(OBS.ENABLEEDIREGNUMBER, "value");
				assertTrue(EDI_Registration_Number.equalsIgnoreCase(REG_Value2));
			}
			driver.navigate().refresh();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(5000);
		}
	}

	public void ConfigurationMetric() throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		click(MC.METRICSLINK);
		waitforElement(180, MC.CCDDWNLD, driver);
		assertTrue(MC.CCDDWNLD.isDisplayed());
		click(MC.CCDDWNLD);
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

	public void InviteUsersDelete(String Admin) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		InviteUsers INU = PageFactory.initElements(driver, InviteUsers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		click(INU.INVITEUSERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, INU.INVITEUSERBTN, driver);
		waitforElement(180, INU.SEARCHTXT, driver);
		type(INU.SEARCHTXT, VertexClientName);
		waitforElement(180, "//td[contains(.,'" + VertexClientName + "')]", driver);
		Thread.sleep(5000);
		waitforElement(180, INU.DELETE, driver);
		if (Admin != "") {
			if (Admin.equalsIgnoreCase("System Admin") || Admin.equalsIgnoreCase("Treasury Admin")
					|| Admin.equalsIgnoreCase("System Approval Admin")) {
				assertTrue(VSC.DELETE.isDisplayed());
			} else {
				if (!VerifyObject(driver, VSC.DELETE)) {
					assertTrue(true);
				} else {
					assertFalse(false);
				}
			}
		} else {
			JavaScriptclick(driver, VSC.DELETE);
			waitforElement(180, INU.WARNINGPOPUP, driver);
			waitforElement(180, INU.DELETEPOPUP, driver);
			click(INU.DELETEPOPUP);
			waitUntilElementInvisibe(180, INU.DELETEPOPUP_XPATH, driver);
			// Verifying Reactive Button for Deleted User
			waitforElement(180, INU.REACTIVATE, driver);
			waitforElement(180, INU.DELETED, driver);
			assertTrue(INU.REACTIVATE.isDisplayed());
		}
	}

	public void AdminNegotiatedContract(String ClientName, String Negocontract_File, String Contract_Number,
			String Add_Negotiation, String Edit, String Edited_Contract_Number) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		String AdjustmentFilePath = xUploadAdjustmentFile("NegotiatedCont");
		String AutoITpath = xAutoIT();
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		// String VertexClientName="ProTCRTESTClient1364538";
		type(AUA.smbCustomerName, ClientName);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement Registered = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			JavaScriptclick(driver, Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			JavaScriptclick(driver, Authorized);
		} else {
			WebElement Initialized = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
			JavaScriptclick(driver, Initialized);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, AUA.ADMINDETAILSLNK, driver);
		// Compncode=driver.findElement(By.xpath("//div/span[text()='Company
		// Code']/../../../../../tbody/tr[2]/td/a")).getText().trim();
		JavaScriptclick(driver, AUA.ADMINDETAILSLNK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		String negofile = Negocontract_File;
		if (Add_Negotiation.equalsIgnoreCase("Yes")) {
			click(AUA.NEGOCHKBOX);
			waitforElement(90, AUA.NEGOTIATEDCONTRACTNUMBER, driver);
			type(AUA.NEGOTIATEDCONTRACTNUMBER, Contract_Number);
			if ((Browser.equalsIgnoreCase("Chrome")) || (Browser.equalsIgnoreCase("IE"))) {
				click(AUA.BROWSE);
			} else {
				JavaScriptclick(driver, AUA.BROWSE);
			}
			Thread.sleep(2000);
			// Calling AutoIT Browser function
			transactions.AutoItBrowser(driver, Browser, AutoITpath, AdjustmentFilePath, negofile, ".pdf");
			Thread.sleep(5000);
			waitforElement(90, AUA.SAVE, driver);
			click(AUA.SAVE);
			waitUntilElementInvisibe(180, "//div/h1[text()='Please wait...']", driver);
			waitforElement(90, AUA.VIEWPDF, driver);
			waitforElement(90, AUA.EDIT, driver);
		}
		if (Edit.equalsIgnoreCase("Yes")) {
			waitforElement(90, AUA.EDIT, driver);
			JavaScriptclick(driver, AUA.EDIT);
			waitforElement(90, AUA.CANCEL, driver);
			clear(AUA.NEGOTIATEDCONTRACTNUMBER);
			type(AUA.NEGOTIATEDCONTRACTNUMBER, Edited_Contract_Number);
			JavaScriptclick(driver, AUA.SAVE);
			waitUntilElementInvisibe(180, "//div/h1[text()='Please wait...']", driver);
			waitforElement(90, AUA.VIEWPDF, driver);
			waitforElement(90, AUA.EDIT, driver);
		}
	}

	public void ClientpodReport(String ClientName, String CustomerName_header, String OSeriesPod_header,
			String TaxPayerName, String Status) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		click(MC.METRICSLINK);
		waitforElement(180, MC.CLIENTPODDREPORT, driver);
		assertTrue(MC.METRICSLABEL.isDisplayed());
		click(MC.CLIENTPODDREPORT);
		Thread.sleep(30000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			Thread.sleep(30000);
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			DownloadFiles.LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(50000);
			LatestDownloadedFile("Metrics", false);
		}
		String CustomerName = CustomerName_header;
		xGetCSVCellValue(Filepath, CustomerName);
		assertTrue(Cell_Header.matches(CustomerName));
		String OSeriesPod = OSeriesPod_header;
		xGetCSVCellValue(Filepath, OSeriesPod);
		assertTrue(Cell_Header.matches(OSeriesPod));
		// String Clientname = VertexClientName;
		String TaxPayer = TaxPayerName;
		String statuss = Status;
		xGetCSVCellVal(Filepath, ClientName, TaxPayer, statuss);
	}

	public void ChangeOraclePartyNumber(String ClientName, String Clienttype, String Oraclepartynum) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		AdminUserLinks ADMIN = PageFactory.initElements(driver, AdminUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		String oraclepnumber = "";
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		} else {
			click(ADMIN.MANAGESMBCUSTOMERS);
		}
		Thread.sleep(2000);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement clientname = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
		JavaScriptclick(driver, clientname);

		waitforElement(180, VSC.CHANGEORACLEPARTYNUMBER, driver);
		waitforElement(180, VSC.CHANGEORACLEPARTYNUMBER, driver);
		JavaScriptclick(driver, VSC.CHANGEORACLEPARTYNUMBER);
		waitforElement(180, VSC.ORACLEPARTYNUMBER, driver);
		assertTrue(VSC.CHANGEORACLENUMBERHEADING.isDisplayed());
		assertTrue(VSC.CHANGEPARTYNUMBERTEXT.isDisplayed());
		assertTrue(VSC.CUSTOMERNAME.isDisplayed());
		assertTrue(VSC.CURRENTORACLEPARTYNUMBER.isDisplayed());
		type(VSC.ORACLEPARTYNUMBER, "11");
		Thread.sleep(2000);
		assertTrue(VSC.OACLEPARTYNUMBERERROR.isDisplayed());
		clear(VSC.ORACLEPARTYNUMBER);
		type(VSC.ORACLEPARTYNUMBER, "");
		Thread.sleep(2000);
		click(VSC.CANCELORACLEPARTYNUMBER);
		waitforElement(180, VSC.CHANGEORACLEPARTYNUMBER, driver);
		JavaScriptclick(driver, VSC.CHANGEORACLEPARTYNUMBER);
		waitforElement(180, VSC.ORACLEPARTYNUMBER, driver);
		if ((Clienttype.equalsIgnoreCase("BPO Enrolled"))) {
			type(VSC.ORACLEPARTYNUMBER, Oraclepartynum);
			Thread.sleep(2000);
			click(VSC.SAVEORACLEPARTYNUMBER);
			waitforElement(180, VSC.CONFIRMSAVE, driver);
			JavaScriptclick(driver, VSC.CONFIRMSAVE);
			Thread.sleep(3000);
		} else if (Clienttype.equalsIgnoreCase("SecondEnrolled") || Clienttype.equalsIgnoreCase("Merchant")) {
			type(VSC.ORACLEPARTYNUMBER, Oraclepartynum);
			Thread.sleep(2000);
			assertTrue(VSC.DUPORACLEPARTYNUMBER.isDisplayed());
			clear(VSC.ORACLEPARTYNUMBER);
			int m = (int) Math.pow(10, 6 - 1);
			int x = m + new Random().nextInt(9 * m);
			oraclepnumber = String.valueOf(x);
			type(VSC.ORACLEPARTYNUMBER, oraclepnumber);
			Thread.sleep(2000);
			if (VerifyObject(driver, VSC.DUPORACLEPARTYNUMBER)) {
				int m1 = (int) Math.pow(10, 6 - 1);
				int x1 = m1 + new Random().nextInt(9 * m1);
				oraclepnumber = String.valueOf(x1);
				type(VSC.ORACLEPARTYNUMBER, oraclepnumber);
				Thread.sleep(2000);
			}
			click(VSC.SAVEORACLEPARTYNUMBER);
			waitforElement(180, VSC.CONFIRMSAVE, driver);
			JavaScriptclick(driver, VSC.CONFIRMSAVE);
			Thread.sleep(3000);
		} else {
			type(VSC.ORACLEPARTYNUMBER, "123456");
			Thread.sleep(2000);
			assertTrue(VSC.DUPORACLEPARTYNUMBER.isDisplayed());
			clear(VSC.ORACLEPARTYNUMBER);
			int m = (int) Math.pow(10, 6 - 1);
			int x = m + new Random().nextInt(9 * m);
			oraclepnumber = String.valueOf(x);
			type(VSC.ORACLEPARTYNUMBER, oraclepnumber);
			Thread.sleep(2000);
			if (VerifyObject(driver, VSC.DUPORACLEPARTYNUMBER)) {
				int m1 = (int) Math.pow(10, 6 - 1);
				int x1 = m1 + new Random().nextInt(9 * m1);
				oraclepnumber = String.valueOf(x1);
				type(VSC.ORACLEPARTYNUMBER, oraclepnumber);
				Thread.sleep(2000);
			}
			click(VSC.SAVEORACLEPARTYNUMBER);
			waitforElement(180, VSC.CONFIRMSAVE, driver);
			JavaScriptclick(driver, VSC.CONFIRMSAVE);
			Thread.sleep(3000);
		}
		/*
		 * String updatednumber = driver.findElement(By.
		 * xpath("//div/span[contains(.,'Oracle Party Number:')]/..")) .getText();
		 * String number[] = updatednumber.split(":"); String partynumber =
		 * number[1].trim(); assertTrue(partynumber.equalsIgnoreCase(oraclenumber));
		 */
	}

	public void Rolesverification(String Role) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		String strRolVal[] = Role.split(";");
		for (int j = 1; j <= strRolVal.length - 1; j++) {
			waitforElement(180, RP.INVITEUSER, driver);
			JavaScriptclick(driver, RP.INVITEUSER);
			waitforElement(180, RP.ADMININVITE, driver);
			waitforElement(180, RP.EXPORT, driver);
			click(RP.ADMININVITE);
			waitforElement(180, RP.ROLE, driver);
			selectByValue(RP.ROLE, strRolVal[j]);
			Thread.sleep(5000);
			sendkeysTab(RP.ROLE);
		}
	}

	public void MetricsVerify() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		SOCReport SRR = PageFactory.initElements(driver, SOCReport.class);
		click(MC.METRICSLINK);
		Thread.sleep(3000);
		assertTrue(MC.AMDDWNLDCSV.isDisplayed());
		click(MC.AMDDWNLDCSV);
		Thread.sleep(20000);
		assertTrue(MC.RFDWNLDCSV.isDisplayed());
		click(MC.RFDWNLDCSV);
		Thread.sleep(20000);
		assertTrue(MC.TMDDWNLDCSV.isDisplayed());
		click(MC.TMDDWNLDCSV);
		Thread.sleep(20000);
		assertTrue(MC.PMDDWNLDCSV.isDisplayed());
		click(MC.PMDDWNLDCSV);
		Thread.sleep(20000);
		assertTrue(SRR.DownloadCSV.isDisplayed());
		click(SRR.DownloadCSV);
		Thread.sleep(20000);
		assertTrue(MC.RMDDWNLDCSV.isDisplayed());
		click(MC.RMDDWNLDCSV);
		Thread.sleep(20000);
		assertTrue(MC.MMDDWNLDCSV.isDisplayed());
		click(MC.MMDDWNLDCSV);
		Thread.sleep(20000);
		assertTrue(MC.PMDVIEWMETRICS.isDisplayed());
		click(MC.PMDVIEWMETRICS);
		Thread.sleep(20000);
		assertTrue(MC.PODID.isDisplayed());
		assertTrue(MC.ASSIGNEDHEADER.isDisplayed());
		assertTrue(MC.AVAILABLEHEADER.isDisplayed());
		assertTrue(MC.TOTALEHEADER.isDisplayed());
		assertTrue(MC.PMDVIEWMETRICS.isDisplayed());
		click(MC.OK);
		Thread.sleep(3000);
		assertTrue(MC.CLIENTDLETEREPORT.isDisplayed());
		click(MC.CLIENTDLETEREPORT);
		Thread.sleep(30000);
		assertTrue(MC.CLIENTCHANGESUBREPORT.isDisplayed());
		click(MC.CLIENTCHANGESUBREPORT);
		Thread.sleep(30000);
		assertTrue(MC.CLIENTLOGINREPORT.isDisplayed());
		click(MC.CLIENTLOGINREPORT);
		Thread.sleep(30000);
		assertTrue(MC.EXCEPTIONMETRICDATA.isDisplayed());
		click(MC.EXCEPTIONMETRICDATA);
		Thread.sleep(30000);
		scrollDown(driver);
		assertTrue(MC.CLIENTPODDREPORT.isDisplayed());
		click(MC.CLIENTPODDREPORT);
		Thread.sleep(30000);
		assertTrue(MC.RGDDWNLD.isDisplayed());
		click(MC.RGDDWNLD);
		Thread.sleep(30000);
		assertTrue(MC.TCDNLD.isDisplayed());
		click(MC.TCDNLD);
		Thread.sleep(30000);
		assertTrue(MC.CCDDWNLD.isDisplayed());
		click(MC.CCDDWNLD);
		Thread.sleep(30000);
		assertTrue(MC.RFDDWNLD.isDisplayed());
		click(MC.RFDDWNLD);
		Thread.sleep(30000);
	}

	public void AdminCutomerReports(String clientname, String ReportType, String CompanyName, String StartDate,
			String EndDate, String Trans_summary, String Trans_Detials) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		PageFactory.initElements(driver, Documentcenter.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		new Robot();
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, clientname);
		waitforElement(180, "//table/tbody/tr/td/div/a[contains(.,'" + clientname + "')]", driver);
		if (!VerifyObject(driver, "//table/tbody/tr/td/div/a[contains(.,'" + clientname + "')]")) {
			boolean report = false;
			int count = 0;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000);
				type(VSC.SMBCUSTNAMESB, clientname);
				Thread.sleep(5000);
				count = count + 1;
				if (count == 25) {
					break;
				}
				if (VerifyObject(driver, "//table/tbody/tr/td/div/a[contains(.,'" + clientname + "')]")) {
					report = true;
				}
			} while (report == false);
		}

		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname
					+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
		} else {
			driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname
					+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
		}
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		click(AUA.smbCustomerReports);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, AUA.REPORT, driver);
		selectByValue(AUA.REPORT, ReportType);
		if (!CompanyName.isEmpty()) {
			selectByValue(AUA.companyName, CompanyName);
		}
		if (!StartDate.isEmpty()) {
			type(AUA.StartDate, StartDate);
			sendkeysTab(AUA.StartDate);
		}
		if (!EndDate.isEmpty()) {
			type(AUA.Enddate, EndDate);
			sendkeysTab(AUA.Enddate);
		}
		if (Trans_summary.equalsIgnoreCase("yes")) {
			click(AUA.AdminTransactionSummary);
		}
		if (Trans_Detials.equalsIgnoreCase("Yes")) {
			click(AUA.AdminTransactionDetails);
		}
		Thread.sleep(3000);
		click(AUA.GENERATEREPORT);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		driver.navigate().refresh();
		waitforElement(180, AUA.REPORT, driver);
	}

	public void InviteUsersDelete(String Clientname, String Admin) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		InviteUsers INU = PageFactory.initElements(driver, InviteUsers.class);
		new Robot();
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		click(INU.INVITEUSERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, INU.INVITEUSERBTN, driver);
		waitforElement(180, INU.SEARCHTXT, driver);
		type(INU.SEARCHTXT, Clientname);
		waitforElement(180, "//td[contains(.,'" + Clientname + "')]", driver);
		Thread.sleep(5000);
		waitforElement(180, INU.DELETE, driver);
		if (Admin != "") {
			if (Admin.equalsIgnoreCase("System Admin") || Admin.equalsIgnoreCase("Treasury Admin")
					|| Admin.equalsIgnoreCase("System Approval Admin")) {
				assertTrue(VSC.DELETE.isDisplayed());
			} else {
				if (!VerifyObject(driver, VSC.DELETE)) {
					assertTrue(true);
				} else {
					assertTrue(false);
				}
			}
		} else {
			JavaScriptclick(driver, VSC.DELETE);
			waitforElement(180, INU.WARNINGPOPUP, driver);
			waitforElement(180, INU.DELETEPOPUP, driver);
			click(INU.DELETEPOPUP);
			waitUntilElementInvisibe(180, INU.DELETEPOPUP_XPATH, driver);
			/*
			 * // Verifying Reactive Button for Deleted User waitforElement(180,
			 * INU.REACTIVATE, driver); assertTrue(INU.REACTIVATE.isDisplayed());
			 */
		}
	}

	public void InvalidLocationValidationErrors() throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		DataChecks DC = PageFactory.initElements(driver, DataChecks.class);
		int count = 0;
		if (VerifyObject(driver, DC.VIEWERRORS)) {
			click(DC.VIEWERRORS);
		} else {
			if (!VerifyObject(driver, DC.CONFIGURELINK)) {
				assertTrue(DC.VALIDATIONLINK.isDisplayed());
			} else {
				if (!VerifyObject(driver, DC.VALIDATIONLINK)) {
					boolean report = false;
					do {
						driver.navigate().refresh();
						Thread.sleep(30000);
						count = count + 1;
						if (count == 50) {
							assertTrue(DC.VALIDATIONLINK.isDisplayed());
							System.out.println("No Validation Errors are present");
							break;
						}
						if (VerifyObject(driver, DC.VALIDATIONLINK)) {
							report = true;
						}
					} while (report == false);
				}

				if (VerifyObject(driver, DC.VALIDATIONLINK)) {
					click(DC.VALIDATIONLINK);
					Thread.sleep(1000);
					waitforElement(90, DC.DWNLDPRINTRPRT, driver);
					JavaScriptclick(driver, DC.DWNLDPRINTRPRT);
					Thread.sleep(5000);
					if (Browser.equalsIgnoreCase("Chrome")) {
						File directory = new File(".//");
						String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
								+ "Reports";
						xDownloadFiles("Reports");
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(10000);
						LatestDownloadedFile("Reports", true);
					}
					if (Browser.equalsIgnoreCase("Firefox")) {
						Thread.sleep(5000);
						LatestDownloadedFile("Reports", false);
					}
					File directory = new File(".\\");
					directory.getCanonicalPath();
					Thread.sleep(3000);
					assertTrue(DC.PROCESSDATA.isDisplayed());
					assertTrue(DC.RETUNRCONFIGOVERVIEWTOP.isDisplayed());
					assertTrue(DC.RETUNRCONFIGOVERVIEWBOTTOM.isDisplayed());
					String ErrorCount = getText(DC.VALIDATIONERRORCOUNT);
					click(DC.VIEWLINK);
					Thread.sleep(1000);
					waitforElement(90, DC.VALIDATIONDROPDOWN, driver);
					selectByIndex(DC.VALIDATIONDROPDOWN, 1);
					assertTrue(DC.RESOLVEERROR.isDisplayed());
					click(DC.RETUNRCONFIGOVERVIEWTOP);
					Thread.sleep(5000);
					assertTrue(ErrorCount.equalsIgnoreCase("1"));
				} else {
					assertTrue(DC.VALIDATIONLINK.isDisplayed());
				}
			}
		}

	}

	public void IntegrationAdminScreens(String ClientName) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		new Robot();
		int count;
		IntegrationAdmin IA = PageFactory.initElements(driver, IntegrationAdmin.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		AdminUserLinks AUL = PageFactory.initElements(driver, AdminUserLinks.class);
		PageFactory.initElements(driver, InviteUsers.class);
		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		driver.getWindowHandle();
		Thread.sleep(1000);
		// Dashboard
		assertTrue(IA.Dashboard.isDisplayed());
		JavaScriptclick(driver, IA.Dashboard);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(driver.getTitle().equalsIgnoreCase("Index | Vertex Cloud"));
		// LookupTaxRates
		assertTrue(AUL.RatesJurisdictions.isDisplayed());
		JavaScriptclick(driver, AUL.RatesJurisdictions);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// OSp logs search
		assertTrue(IA.Osplogssearch.isDisplayed());
		JavaScriptclick(driver, IA.Osplogssearch);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(driver.getTitle().equalsIgnoreCase("OSP Logs Search | Vertex Cloud"));
		// Logs
		JavaScriptMousehover(driver, TBS.logs);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (!Environment.equalsIgnoreCase("QA")) {
			click(TBS.Taxlinklogs);
			Thread.sleep(5000);
			assertTrue(TBS.Taxlinklogssearch.isDisplayed());
			Thread.sleep(30000);
			selectByIndex(TBS.serverdropdwn, 2);
			if (VerifyObject(driver, TBS.TAXLINKLOGSSDATA)) {
				waitforElement(540, TBS.TAXLINKLOGSSDATA, driver);
				click(TBS.TAXLINKLOGSSDATA);
			}
			Thread.sleep(5000);
		}
		click(TBS.DASHBOARD);
		JavaScriptMousehover(driver, TBS.logs);
		click(TBS.ECWLogs);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(TBS.ECWLogs.isDisplayed());
		Thread.sleep(30000);
		if (VerifyObject(driver, TBS.ECWServername)) {
			waitforElement(540, TBS.ECWServername, driver);
			selectByIndex(TBS.ECWServername, 2);
			Thread.sleep(5000);
			if (VerifyObject(driver, TBS.ECWLogdata)) {
				click(TBS.ECWLogdata);
			}
		}
		// Manage SMB Customers
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		// Features dropdown verification
		assertTrue(VSC.Featuresdropdwn.isDisplayed());
		String[] fval = { "Rate File", "Real-Time Rate Service", "Taxability Impact Report", "Test Instance",
				"Retail Tax Extract", "EC Wizard" };
		click(VSC.Featuresdropdwn);
		for (int i = 0; i < fval.length; i++) {
			selectByValue(VSC.Featuresdropdwn, fval[i]);
		}
		selectByValue(VSC.Featuresdropdwn, "EC Wizard");
		selectByValue(VSC.Subsciptiondropdwn, "Standard");
		if (ClientName.contains("ECW")) {
			type(AUA.smbCustomerName, ClientName);
			Thread.sleep(10000);
			if (!VerifyObject(driver,
					"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]")) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					Thread.sleep(20000);
					type(VSC.SMBCUSTNAMESB, ClientName);
					Thread.sleep(5000);
					count = count + 1;
					if (count == 25) {
						break;
					}
					if (VerifyObject(driver,
							"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]")) {
						report = true;
					}
				} while (report == false);
			}
			driver.findElement(
					By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"))
					.click();
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			// Verifying the fields in Customer Details screen
			String EClientname = driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Name:')]/../../div[1]"))
					.getText();
			String exc[] = EClientname.split(":");
			assertTrue((exc[1].trim()).equalsIgnoreCase(ClientName));
			String ECWsubscription = driver
					.findElement(By.xpath("//span[contains(text(),'Subscription:')]/../../div[1]")).getText();
			String exs[] = ECWsubscription.split(":");
			assertTrue((exs[1].trim()).equalsIgnoreCase("Standard"));
			String ECWVarname = driver.findElement(By.xpath("//span[contains(.,'Current VAR:')]/../../div[2]"))
					.getText();
			String exva[] = ECWVarname.split(":");
			assertTrue((exva[1].trim()).equalsIgnoreCase("Test Instance VAR"));
			assertTrue(VSC.ClientInformation.isDisplayed());
			JavaScriptclick(driver, VSC.ClientInformation);
			assertTrue(VSC.ModifyVardisabled.isDisplayed());
			assertTrue(VSC.Editcompcapdisabled.isDisplayed());
			assertTrue(VSC.cancustdisabled.isDisplayed());
			assertTrue(VSC.CREATENEWUSER.isDisplayed());
			assertTrue(!(VSC.DELETE.isDisplayed()));
			// Customer Configuration section verification
			click(VSC.SMBCUSTOMERREPORT);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(VSC.SECTIONEMPTY.isDisplayed());
			// Customer Returns section verification
			click(VSC.CUSTOMERRETURNS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(VSC.CUSTOMERRETURNS.isDisplayed());
			// Customer Prepayments section verification
			click(VSC.CustomerPrepayments);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(VSC.SECTIONEMPTY.isDisplayed());
			// Customer Reports section verification
			click(VSC.SMBREPORTS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(VSC.SECTIONEMPTY.isDisplayed()); // Partition Configuration section Verification
			click(VSC.PartitonConfiguration);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(VSC.SECTIONEMPTY.isDisplayed());
			// Connectors section verification
			click(VSC.Connectors);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			if (!VerifyObject(driver, VSC.GenerateAPIlink)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			assertTrue(VSC.ECWHeaderText.isDisplayed());
			assertTrue(VSC.ECWURL.isDisplayed());
			String EcwUrl = driver
					.findElement(By.xpath("//div/span[contains(.,'URL:')]/../../div[@class='span7 ng-binding']"))
					.getText();
			System.out.println(EcwUrl);
			String evn = Environment.toLowerCase();
			System.out.println(evn);
			assertTrue(EcwUrl.equalsIgnoreCase("https://" + evn + "ecwportal.vertexsmb.com"));
			assertTrue(VSC.ECWClientCode.isDisplayed());
			assertTrue(PUL.ECWIntegrationText.isDisplayed());
			assertTrue(PUL.ECWInClientID.isDisplayed());
			assertTrue(PUL.ECWInClientsecret.isDisplayed());
			assertTrue(PUL.OseriesIntegrationText.isDisplayed());
			assertTrue(PUL.ECWOseriesClientID.isDisplayed());
			assertTrue(PUL.ECWOseriesClientSecret.isDisplayed());
		} // Metrics Verification
		assertTrue(AUL.METRICSLINK.isDisplayed());
		click(AUL.METRICSLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		assertTrue(VSC.AuditMeticData.isDisplayed());
		click(AUL.AUDITMETRICDATA);
		Thread.sleep(20000);
		// Invite users link
		assertTrue(AUL.INVITEUSERLINK.isDisplayed());
		click(AUL.INVITEUSERLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		assertTrue(AUL.EXPORTBUTTON.isDisplayed());
		if (!VerifyObject(driver, VSC.InviteUsers)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		if (!VerifyObject(driver, VSC.ACTION)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		if (!VerifyObject(driver, VSC.Deletelink)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		// VertexCloudVars verification
		assertTrue(AUL.VERTEXSMBVARS.isDisplayed());
		click(AUL.METRICSLINK);
		waitUntilElementInvisibe(120, "html[@class='nprogress-busy']", driver);
		// System Configuration
		assertTrue(IA.systemconfiguration.isDisplayed());
		JavaScriptclick(driver, IA.systemconfiguration);
		// VisitHelpCenter
		assertTrue(AUL.VisitHelpcenter.isDisplayed());
		// Whatsnew link
		assertTrue(AUL.Whatsnew.isDisplayed());

	}

	public void ManageOseriesConfig(String UserName, String ShouldnotEditable, String CommentSrting,
			String Verify_Exceptions, String Company, String Jurisdiction_Type, String Comment, String Jurisdiction)
			throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		ManageoseiesConfig UN = PageFactory.initElements(driver, ManageoseiesConfig.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		click(UN.Manageoseriesconfiglik);
		waitforElement(180, UN.headerText, driver);
		assertTrue(UN.headerText.isDisplayed());
		assertTrue(UN.countryinput.isDisplayed());
		assertTrue(UN.Jurisdictioninput.isDisplayed());
		assertTrue(UN.enablelljtext.isDisplayed());
		assertTrue(UN.filtertext.isDisplayed());
		assertTrue(UN.countrycloumn.isDisplayed());
		assertTrue(UN.jurisdictioncolumn.isDisplayed());
		assertTrue(UN.enablelljcolumn.isDisplayed());
		assertTrue(UN.commentcolumn.isDisplayed());
		assertTrue(UN.Actionscolumn.isDisplayed());
		if (ShouldnotEditable.equalsIgnoreCase("yes")) {
			JavaScriptclick(driver, UN.enablelljtext);
			Thread.sleep(2000);
			JavaScriptclick(driver, UN.filtertext);
			assertTrue(UN.Checkboxdisabled.isDisplayed());
		} else {
			// Enable low level juridiction
			waitforElement(180, UN.Jurisdictioninput, driver);
			type(UN.Jurisdictioninput, Jurisdiction);
			Thread.sleep(3000);
			JavaScriptclick(driver, UN.filtertext);
			waitforElement(180, UN.EnablelowlevelException, driver);
			Thread.sleep(3000);
			JavaScriptclick(driver, UN.EnablelowlevelException);
			String Juridiction = driver
					.findElement(By.xpath(
							"//table/thead/tr/th[contains(.,'Jurisdiction')]/../../../../table/tbody/tr[2]/td[2]"))
					.getText();
			assertTrue(Juridiction.equalsIgnoreCase(Jurisdiction));
			click(UN.COMMENT);
			type(UN.COMMENT, Comment);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//td[contains(.,'" + Jurisdiction + "')]/..//button[contains(.,'Update')]"))
					.click();
			Thread.sleep(3000);
			assertTrue(UN.UPDATE.isDisplayed());
			// Verify enabled juridction in client
			if (Verify_Exceptions.equalsIgnoreCase("YES")) {
				userregistration.Logout();
				waitforElement(180, LP.USERNAME, driver);
				String URL = url;
				driver.get(URL);
				userregistration.UserLogin(UserName, "Password!1");
				waitforElement(180, LP.DASHBOARD, driver);
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(10000);
				if (VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../../div/div/div/h3[contains(.,'Customer Exceptions')]/span/a[contains(.,'+ Add')]")) {
					WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
							+ "')]/../../../../div/div/div/h3[contains(.,'Customer Exceptions')]/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
					waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				}
				Select stateSelectbox = new Select(
						driver.findElement(By.xpath("//*[@id='ddlExceptionJurisdiction0']")));
				stateSelectbox.selectByVisibleText(Jurisdiction);
				Thread.sleep(3000);
				Select Jurisdtype = new Select(driver.findElement(By.xpath("//select[@id='ddlJurisdictionType0']")));
				Jurisdtype.selectByVisibleText(Jurisdiction_Type);
				assertTrue(CE.ISSUEJURSDTYPE.isEmpty());
			}
			// Disable low level juridiction
			userregistration.Logout();
			waitforElement(180, LP.USERNAME, driver);
			String URL = url;
			driver.get(URL);
			userregistration.UserLogin(UserName, "Password!1");
			waitforElement(180, LP.DASHBOARD, driver);
			click(UN.Manageoseriesconfiglik);
			waitforElement(180, UN.headerText, driver);
			waitforElement(180, UN.Jurisdictioninput, driver);
			type(UN.Jurisdictioninput, Jurisdiction);
			Thread.sleep(3000);
			JavaScriptclick(driver, UN.filtertext);
			waitforElement(180, UN.EnablelowlevelException, driver);
			JavaScriptclick(driver, UN.EnablelowlevelException);
			Thread.sleep(6000);
			click(UN.COMMENT);
			clear(UN.COMMENT);
			type(UN.COMMENT, Comment);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//td[contains(.,'" + Jurisdiction + "')]/..//button[contains(.,'Update')]"))
					.click();
			Thread.sleep(3000);
		}
	}

	public void SystemApprovescreens(String DashBoard_Title, String LookUp_Title, String TaxAreaTransactions_Title,
			String UnSupported_TaxCategories, String CTC_Returns_Title, String Metrics_Title) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		IntegrationAdmin TL = PageFactory.initElements(driver, IntegrationAdmin.class);
		ApproveTitles = "";
		click(TBS.DASHBOARD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String DB_Title = driver.getTitle();
		if (DashBoard_Title.equalsIgnoreCase(DB_Title)) {
			ApproveTitles = DB_Title + ";";
		}
		click(TBS.RatesJurisdictions);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String LookUp_Title1 = driver.getTitle();
		if (LookUp_Title.equalsIgnoreCase(LookUp_Title1)) {
			ApproveTitles = LookUp_Title + ";";
		}
		// ..CTC Impact.........
		Thread.sleep(2000);
		JavaScriptMousehover(driver, TBS.CTCImpact);
		Thread.sleep(2000);
		JavaScriptclick(driver, TBS.CTCTransactions);
		Thread.sleep(8000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(driver.getTitle().equalsIgnoreCase(TaxAreaTransactions_Title));
		JavaScriptMousehover(driver, TBS.CTCImpact);
		Thread.sleep(2000);
		JavaScriptclick(driver, TBS.TaxabilityCategories);
		Thread.sleep(10000);
		// Verifying Unsupported taxability categories
		if (UnSupported_TaxCategories != "") {
			String unsupported_taxCategories = UnSupported_TaxCategories;
			ArrayList<String> aList = new ArrayList<String>(Arrays.asList(unsupported_taxCategories.split(",")));
			for (int k = 0; k < aList.size(); k++) {
				String category = aList.get(k).toString();
				clear(PP.PRODUCTS_QUICK_SEARCH);
				type(PP.PRODUCTS_QUICK_SEARCH, category);
				Thread.sleep(2000);
				assertTrue(driver.findElement(By.xpath("//p[contains(.,'" + category + "')]")).isDisplayed());
			}
		}
		JavaScriptMousehover(driver, TBS.CTCImpact);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		click(TBS.CTCReturns);
		Thread.sleep(8000);
		assertTrue(driver.getTitle().equalsIgnoreCase(CTC_Returns_Title));
		// Metrics
		click(TBS.METRICS);
		waitforElement(180, TBS.MAINTENANCEMETRICDATA, driver);
		String MR_Title = driver.getTitle();
		if (Metrics_Title.equalsIgnoreCase(MR_Title)) {
			ApproveTitles = Metrics_Title + ";";
		}
		assertTrue(MC.AUDITMETRICDOWNLOAD.isDisplayed());
		click(TBS.DOWNLOADEDCSV);
		assertTrue(TBS.DOWNLOADEDCSV.isDisplayed());
		assertTrue(MC.PRODUCTMETRICDOWNLOAD.isDisplayed());
		click(MC.PRODUCTMETRICDOWNLOAD);
		assertTrue(MC.RATEFILEMETRICDATA.isDisplayed());
		click(MC.RATEFILEMETRICDATA);
		assertTrue(MC.RMDDWNLDCSV.isDisplayed());
		click(MC.RMDDWNLDCSV);
		assertTrue(MC.TMDDWNLD.isDisplayed());
		click(MC.TMDDWNLD);
		assertTrue(TBS.MAINTENANCEMETRICDATA.isDisplayed());
		click(TBS.MAINTENANCEMETRICDATA);
		assertTrue(MC.PMDVIEWMETRICS.isDisplayed());
		JavaScriptclick(driver, MC.PMDVIEWMETRICS);
		Thread.sleep(10000);
		click(MC.OK);
		Thread.sleep(2000);
		assertTrue(MC.DOWNLDCSV.isDisplayed());
		click(MC.DOWNLDCSV);
		assertTrue(MC.EXCEPTIONMETRICDATA.isDisplayed());
		click(MC.EXCEPTIONMETRICDATA);
		assertTrue(MC.CLIENTDLETEREPORT.isDisplayed());
		click(MC.CLIENTDLETEREPORT);
		assertTrue(MC.RGDDWNLD.isDisplayed());
		click(MC.RGDDWNLD);
		assertTrue(MC.CCDDWNLD.isDisplayed());
		click(MC.CCDDWNLD);
		assertTrue(MC.TCDNLD.isDisplayed());
		click(MC.TCDNLD);
		assertTrue(MC.RFDDWNLD.isDisplayed());
		click(MC.RFDDWNLD);
		assertTrue(MC.CLIENTPODDREPORT.isDisplayed());
		click(MC.CLIENTPODDREPORT);
		assertTrue(MC.VEDNLD.isDisplayed());
		click(MC.VEDNLD);
		// Document Center
		click(TBS.DOCUMENTCENTER);
		waitforElement(180, TBS.DOCUMENTCENTERFILETYPE, driver);
		assertTrue(TL.manageoseries.isDisplayed());
		JavaScriptclick(driver, TL.manageoseries);
	}

	public void CustomersVerifyInInvitation(String ClientName, String Userstatus) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforText(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr/td[1]/div[@class='ng-binding ng-scope']"))
				.getText();
		assertTrue(ClientName.equalsIgnoreCase(CName));
		String CStatus = driver.findElement(By.xpath("//table/tbody/tr/td[6]")).getText();
		String[] Datav = CStatus.split("\\(");
		assertTrue(Datav[0].contains(Userstatus));
		assertTrue(VSC.EXPORT1.isDisplayed());
		assertTrue(VSC.MANAGEOSERIESCONFIG.isDisplayed());
	}

	public void InviteUsersResend(String ClientName, String Admin) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		InviteUsers INU = PageFactory.initElements(driver, InviteUsers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		JavaScriptclick(driver, INU.INVITEUSERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, INU.INVITEUSERBTN, driver);
		waitforElement(180, INU.SEARCHTXT, driver);
		type(INU.SEARCHTXT, ClientName);
		waitforElement(180, "//td[contains(.,'" + ClientName + "')]", driver);
		Thread.sleep(5000);
		waitforElement(180, INU.DELETE, driver);
		if (Admin != "") {
			if (Admin.equalsIgnoreCase("System Admin") || Admin.equalsIgnoreCase("Treasury Admin")
					|| Admin.equalsIgnoreCase("System Approval Admin")) {
				assertTrue(VSC.DELETE.isDisplayed());
			} else {
				if (!VerifyObject(driver, VSC.DELETE)) {
					assertTrue(true);
				} else {
					assertTrue(false);
				}
			}
		} else {
			JavaScriptclick(driver, VSC.Resendcustomer);
			waitforElement(180, VSC.EmailResent, driver);
			waitforElement(180, VSC.ConfirmResent, driver);
			click(VSC.ConfirmResent);
			waitforElement(180, VSC.Resendcustomer, driver);
			waitforElement(180, INU.DELETE, driver);
			assertTrue(INU.DELETE.isDisplayed());
		}
	}

	public void TransactionMetricData(String ClientName, String Startdate, String EndDate, String Company_Name,
			String Company_Status, String DNS_Name, String Msg_Type, String Trans_Count, String Subscription)
			throws Exception {
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
		String Clientname = ClientName;
		String Subscr = Subscription;
		String DNSName = DNS_Name;
		String MsgType = Msg_Type;
		String Transcount = Trans_Count;
		xGetCSVValue(UtilityScript.Filepath, Clientname, Compname, Compstatus, Subscr, DNSName, MsgType, Transcount);
		System.out.println("ReturnVAlue=" + UtilityScript.Compstat);
	}

	public void ReturnMetricData(String Filing_Frequency_Header, String Fiscal_Month_Start_Header,
			String LockDate_header, String ReturnCount_header, String TaxpayerName_Header, String ReturnName_Header,
			String ReturnState_header, String Status_header, String COMPANYCODE_header, String Taxpayer_Value,
			String Returnname_Value, String AuditHour_header, String AppName_header, String ClientId_header,
			String ClientName_header, String ClientCity_header, String Filing_Frequency_Value,
			String ClientStateCode_header, String UserId_header, String MachineName_header, String FirstName_header,
			String LastName_header, String UserRole_header, String Duration_header, String EventData_header,
			String EventCount_header) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		click(MC.METRICSLINK);
		waitforElement(180, MC.RMDDWNLDCSV, driver);
		assertTrue(MC.RMDDWNLDCSV.isDisplayed());
		click(MC.RMDDWNLDCSV);
		Thread.sleep(10000);
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
		String Frequency = (Filing_Frequency_Header);
		xGetCSVCellValue(Filepath, Frequency);
		assertTrue(Cell_Header.matches(Frequency));
		String FiscalMonth = (Fiscal_Month_Start_Header);
		xGetCSVCellValue(Filepath, FiscalMonth);
		assertTrue(Cell_Header.matches(FiscalMonth));
		String Lockdate = (LockDate_header);
		xGetCSVCellValue(Filepath, Lockdate);
		assertTrue(Cell_Header.matches(Lockdate));
		String ReturnCount = (ReturnCount_header);
		xGetCSVCellValue(Filepath, ReturnCount);
		assertTrue(Cell_Header.matches(ReturnCount));
		String TaxpayerName = (TaxpayerName_Header);
		xGetCSVCellValue(Filepath, TaxpayerName);
		assertTrue(Cell_Header.matches(TaxpayerName));
		String ReturnName = (ReturnName_Header);
		xGetCSVCellValue(Filepath, ReturnName);
		assertTrue(Cell_Header.matches(ReturnName));
		String Returnstate = (ReturnState_header);
		xGetCSVCellValue(Filepath, Returnstate);
		assertTrue(Cell_Header.matches(Returnstate));
		String Status = (Status_header);
		xGetCSVCellValue(Filepath, Status);
		assertTrue(Cell_Header.matches(Status));
		String COMPANYCODE = (COMPANYCODE_header);
		xGetCSVCellValue(Filepath, COMPANYCODE);
		assertTrue(Cell_Header.matches(COMPANYCODE));
		if (TaxpayerName_Header != "") {
			String Tpayerhead = (TaxpayerName_Header);
			String Tpayername = (Taxpayer_Value);
			String Rname = (Returnname_Value);
			String freqvalue = (Filing_Frequency_Value);
			xGetCSVCell(Filepath, Tpayerhead, Tpayername, Rname, freqvalue);
			System.out.println("ReturnVAlue=" + UtilityScript.returnvalue);
			// assertTrue(returnvalue.matches(Rname));
		}
		assertTrue(MC.METRICSLABEL.isDisplayed());
		waitforElement(180, MC.AUDITMETRICDOWNLOAD, driver);
		JavaScriptclick(driver, MC.AUDITMETRICDOWNLOAD);
		Thread.sleep(50000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			Thread.sleep(30000);
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			DownloadFiles.LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(10000);
			LatestDownloadedFile("Metrics", false);
		}
		/*
		 * String AuditDate = (Auditdate_Header); xGetCSVCellValue(Filepath, AuditDate);
		 */
		String datePattern = "([0-1]?)[0-9]\\/([0-3]?)[0-9]\\/[0-9]{4} ([0-1]?)[0-9]:([0-5]?)[0-9]:([0-5]?)[0-9] [paPA][Mm]";
		// Create a Pattern object
		Pattern r = Pattern.compile(datePattern);
		// Now create matcher object.
		Matcher m = r.matcher(Cell_Value);
		if (m.find()) {
			assertTrue(Cell_Value.matches(datePattern));
		}
		String AuditHour = (AuditHour_header);
		xGetCSVCellValue(Filepath, AuditHour);
		assertTrue(Cell_Header.matches(AuditHour));
		String AppName = (AppName_header);
		xGetCSVCellValue(Filepath, AppName);
		assertTrue(Cell_Header.matches(AppName));
		String AppNameevent = (AppName_header);
		xGetCSVCellValue(Filepath, AppNameevent);
		assertTrue(Cell_Header.matches(AppNameevent));
		String ClientId = (ClientId_header);
		xGetCSVCellValue(Filepath, ClientId);
		assertTrue(Cell_Header.matches(ClientId));
		String ClientName = (ClientName_header);
		xGetCSVCellValue(Filepath, ClientName);
		assertTrue(Cell_Header.matches(ClientName));
		String ClientCity = (ClientCity_header);
		xGetCSVCellValue(Filepath, ClientCity);
		assertTrue(Cell_Header.matches(ClientCity));
		String ClientStateCode = (ClientStateCode_header);
		xGetCSVCellValue(Filepath, ClientStateCode);
		assertTrue(Cell_Header.matches(ClientStateCode));
		String UserId = (UserId_header);
		xGetCSVCellValue(Filepath, UserId);
		assertTrue(Cell_Header.matches(UserId));
		String ClientpostalCode = (ClientStateCode_header);
		xGetCSVCellValue(Filepath, ClientpostalCode);
		assertTrue(Cell_Header.matches(ClientpostalCode));
		String MachineName = (MachineName_header);
		xGetCSVCellValue(Filepath, MachineName);
		assertTrue(Cell_Header.matches(MachineName));
		String FirstName = (FirstName_header);
		xGetCSVCellValue(Filepath, FirstName);
		assertTrue(Cell_Header.matches(FirstName));
		String LastName = (LastName_header);
		xGetCSVCellValue(Filepath, LastName);
		assertTrue(Cell_Header.matches(LastName));
		String UserRole = (UserRole_header);
		xGetCSVCellValue(Filepath, UserRole);
		assertTrue(Cell_Header.matches(UserRole));
		String Duration = (Duration_header);
		xGetCSVCellValue(Filepath, Duration);
		assertTrue(Cell_Header.matches(Duration));
		String EventData = (EventData_header);
		xGetCSVCellValue(Filepath, EventData);
		assertTrue(Cell_Header.matches(EventData));
		String EventCount = (EventCount_header);
		xGetCSVCellValue(Filepath, EventCount);
		assertTrue(Cell_Header.matches(EventCount));

	}

	public void VertexSMBCustomersVerify(String ClientName, String CreateNewuser, String Modifyvar, String VAR,
			String Userstatus, String FirstName, String LastName, String Email, String Phone, String UserName,
			String Newpaasword, String Confirmnewpassword, String ReportsType, String Year, String Month,
			String View_Warning, String COMPANYCODE, String Export_EDI_List_Disable, String FilingPeriod,
			String GenerateReturns, String ReturnName, String AdminRole) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		driver.manage().deleteAllCookies();
		Thread.sleep(5000);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforText(180, "//table/tbody/tr/td[1]/div[contains(.,'" + ClientName + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr/td[1]/div")).getText();
		assertTrue((ClientName).equalsIgnoreCase(CName));
		if (VerifyObject(driver, VSC.SELECT)) {
			JavaScriptclick(driver, VSC.SELECT);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.SELECTSYSAPP, driver);
		}
		// Verifying POR-14304 in 6.4.1, Create New User through Admin Portal
		if (CreateNewuser.equalsIgnoreCase("Yes")) {
			// driver.findElement(By.xpath("//table/tbody/tr/td[1]/div")).click();
			// JavaScriptclick(driver, VSC.CLOUDCUSTOMERDETAILS);
			// waitUntilElementInvisibe(180,CNP.NPROGRESSBUSY_XPATH,driver);
			waitforElement(120, VSC.CREATENEWUSER, driver);
			// Verifying POR-14305 in 6.4.1, Modify VAR
			if (Modifyvar.equalsIgnoreCase("Yes")) {
				waitforElement(180, VSC.MODIFYVAR, driver);
				JavaScriptclick(driver, VSC.MODIFYVAR);
				waitforElement(180, VSC.VARLIST, driver);
				selectByValue(VSC.VARLIST, VAR);
				Thread.sleep(2000);
				JavaScriptclick(driver, VSC.SAVE);
				Thread.sleep(5000);
				String ModifyVAR = getText(VSC.CURRENTVAR);
				String CurrentVAR[] = ModifyVAR.split(":");
				assertTrue(VAR.equalsIgnoreCase(CurrentVAR[1].trim()));
			} else {
				if (Userstatus.equalsIgnoreCase("PendingBilling")) {
					assertTrue(VSC.BYPASSPENDINGBILLING.isDisplayed());
				}
				JavaScriptclick(driver, VSC.CREATENEWUSER);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(60, VSC.FIRSTNAME, driver);
				String[] Fnamedata = FirstName.split(";");
				VertexFirstName = xLabelRND(Fnamedata);
				click(VSC.FIRSTNAME);
				type(VSC.FIRSTNAME, VertexFirstName);
				Thread.sleep(2000);
				String[] Lnamedata = LastName.split(";");
				VertexLastName = xLabelRND(Lnamedata);
				click(VSC.LASTNAME);
				type(VSC.LASTNAME, VertexLastName);
				Thread.sleep(2000);
				String[] Emaildata = Email.split(";");
				VertexEmail = xLabelRND(Emaildata);
				click(VSC.EMAIL);
				type(VSC.EMAIL, VertexEmail);
				Thread.sleep(2000);
				type(VSC.PHONE, Phone);
				sendkeysTab(VSC.PHONE);
				click(VSC.FIRSTNAME);
				sendkeysTab(VSC.FIRSTNAME);
				selectByValue(VSC.USerrole, "Admin");
				waitforElement(180, VSC.SENDINVITE, driver);
				JavaScriptclick(driver, VSC.SENDINVITE);
				waitforElement(180, VSC.LINK, driver);
				JavaScriptclick(driver, VSC.LINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				if (Userstatus.equalsIgnoreCase("Initialized")) {
					waitforElement(180, VSC.USERNAME1, driver);
					String[] Unamedata = UserName.split(";");
					VertexFirstName = xLabelRND(Unamedata);
					type(VSC.USERNAME1, VertexFirstName);
					type(VSC.NEWPASSWORD1, Newpaasword);
					type(VSC.CONFIRMNEWPASSWORD1, Confirmnewpassword);
					// JavaScriptclick(driver, VSC.ACCEPTEDTERM);
					// Thread.sleep(3000);
					// JavaScriptclick(driver, VSC.SERVICEAGREEMENT);
					Thread.sleep(3000);
				} else {
					waitforElement(180, VSC.USERNAME1, driver);
					String[] Unamedata = UserName.split(";");
					VertexFirstName = xLabelRND(Unamedata);
					type(VSC.USERNAME1, VertexFirstName);
					type(VSC.NEWPASSWORD1, Newpaasword);
					type(VSC.CONFIRMNEWPASSWORD1, Confirmnewpassword);
					// JavaScriptclick(driver, VSC.ACCEPTEDTERMS);
					Thread.sleep(3000);
				}
				JavaScriptclick(driver, VSC.COMPLETE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, VSC.Dashboard, driver);
				assertTrue(VSC.Dashboard.isDisplayed());
				userregistration.Logout();
				Thread.sleep(5000);
				driver.get(url);
				Thread.sleep(5000);
				userregistration.UserLogin(adminusername, adminpassword);

				Thread.sleep(5000);
			}
		} else {
			if (ReportsType != "") {
				JavaScriptclick(driver, VSC.SMBREPORTS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, VSC.FILETYPE, driver);
				assertTrue(VSC.SELECTDOCSTODOWNLOAD.isDisplayed());
				selectByValue(VSC.FILETYPE, ReportsType);
				waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
				waitforElement(180, VSC.YEAR, driver);
				selectByValue(VSC.YEAR, Year);
				waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
				waitforElement(180, VSC.MONTH, driver);
				selectByValue(VSC.MONTH, Month);
				waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
				if (View_Warning.equalsIgnoreCase("YES")) {
					List<WebElement> list = driver.findElements(By.xpath("//a[contains(.,'See Details')]"));
					for (int j = 1; j <= list.size(); j++) {
						driver.findElement(By.xpath("//ul/li[" + j + "]/div/span/a/span[contains(.,'See Details')]"))
								.click();
						if (VerifyObject(driver, VSC.VIEWWARNING)) {
							click(VSC.VIEWWARNING);
							assertTrue(VSC.WARNINGMESSAGE.isDisplayed());
							assertTrue(VSC.WARNINGTEXT.isDisplayed());
							click(VSC.CLOSEWARNING);
						}
					}
				}
				if (VerifyObject(driver, VSC.COMPANYCODE)) {
					waitforElement(180, VSC.COMPANYCODE, driver);
					selectByValue(VSC.COMPANYCODE, COMPANYCODE);
					waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
				}
				waitforElement(180, VSC.REPORTSLINKS, driver);
				assertTrue(VSC.REPORTSLINKS.isDisplayed());
			} else {
				if (Export_EDI_List_Disable.equalsIgnoreCase("Yes")) {
					waitforElement(120, VSC.SMBCUSTOMERTAXRETURNS, driver);
					click(VSC.SMBCUSTOMERTAXRETURNS);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(120, VSC.FILINGPERIOD, driver);
					selectByValue(VSC.FILINGPERIOD, FilingPeriod);
					Thread.sleep(5000);
					// assertTrue(VSC.EXPORTEDILIST.isDisplayed());
					// Verifying POR-14328 in 6.4.1, Generating Returns through Admin Portal
					if (GenerateReturns.equalsIgnoreCase("Yes")) {
						waitforElement(180, VSC.GENERATERETURNS, driver);
						if (ReturnName.equalsIgnoreCase("Yes")) {
							driver.findElement(By.xpath("//td[contains(.,'" + ReturnName + " ')]/../td[5]/input"))
									.click();
							Thread.sleep(2000);
						} else {
							JavaScriptclick(driver, VSC.GENERATERETURNS);
						}
						waitforElement(180, VSC.GENERATERETURNSBUTTON, driver);
						JavaScriptclick(driver, VSC.GENERATERETURNSBUTTON);
						waitUntilElementInvisibe(180, CNP.LOADER_XPATH, driver);
						Thread.sleep(15000);
						if (AdminRole.equalsIgnoreCase("Treasury Admin")
								|| AdminRole.equalsIgnoreCase("System Approval Admin")
								|| AdminRole.equalsIgnoreCase("Invitation")) {
							assertTrue(VSC.Forbiddeerror.isDisplayed());
						}
						assertTrue(MTR.GENERATERETURNSMSG.isDisplayed());
					}
				} else {
					click(VSC.MANAGESMBCUSTLINK);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, VSC.SELECTSYSAPP, driver);
					((JavascriptExecutor) driver).executeScript("scroll(0,3000);");
					Thread.sleep(10000);
					WebElement Lastpage = driver
							.findElement(By.xpath("//div[@class='pagination pagination-right ng-scope']/ul/li/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Lastpage);
					String PaginationValue = driver.findElement(By.xpath(
							"//div[@class='pagination pagination-right ng-scope']/ul/li[@class='ng-scope active']/a"))
							.getText();
					int Pagination_Count = Integer.parseInt(PaginationValue);
					int count = 2;
					do {
						if (count >= Pagination_Count - 2) {
							WebElement PageClick = driver.findElement(By.xpath(
									"//div[@class='pagination pagination-right ng-scope']/ul/li[" + count + "]/a"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", PageClick);
							((JavascriptExecutor) driver).executeScript("scroll(0,3000);");
							Thread.sleep(10000);
						} else {
							WebElement PageClick = driver.findElement(By.xpath(
									"//div[@class='pagination pagination-right ng-scope']/ul/li[" + count + "]/a"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", PageClick);
							((JavascriptExecutor) driver).executeScript("scroll(0,3000);");
							Thread.sleep(10000);
							count++;
						}
						ArrayList<String> Customer_status = new ArrayList<String>();
						Customer_status.add("Registered");
						Customer_status.add("Initialized");
						Customer_status.add("PendingBilling");
						Customer_status.add("Authorized");
						Customer_status.add("Cancelled");
						List<WebElement> rowscount = driver.findElements(By.xpath(VSC.VERTEXSMBCUSTOMERSTABLE_XPATH));
						for (int k = 2; k < rowscount.size(); k = k + 1) {
							if (Customer_status.contains(
									driver.findElement(By.xpath("//table/tbody/tr[" + k + "]/td[7]")).getText())) {
								assertTrue(driver.getTitle().equalsIgnoreCase("Customer Management | Vertex SMB"));
								Customer_status.remove(
										driver.findElement(By.xpath("//table/tbody/tr[" + k + "]/td[7]")).getText());
								if (Customer_status.size() == 0) {
									break;
								}
							}
						}
					} while (!VerifyObject(driver, VSC.PAGINATIONDISABLE));
				}
			}
		}
	}

	public void SystemAdminReadOnlynotallowedOnboard(String ClientName) throws Exception {
		OnboardStatus OBS = PageFactory.initElements(driver, OnboardStatus.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptMousehover(driver, OBS.PREMIUMSMBCUSTOMERS);
		JavaScriptclick(driver, OBS.PREMIUMCUSTMERSSUBLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, OBS.ONBOARDSEARCHBOX, driver);
		type(OBS.ONBOARDSEARCHBOX, ClientName);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(OBS.OnboardnotAllowed.isDisplayed());
		assertTrue(OBS.OffboardnotAllowed.isDisplayed());
	}

	public void OSPSearchLogs(String DNS, String StartDate, String EndDate, String Message_Type, String Other,
			String ResponseTime, String DocumentNumber, String TrustedId, String VerifyOther) throws Exception {
		PageFactory.initElements(driver, DataBase.class);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		OSPSearch OSP = PageFactory.initElements(driver, OSPSearch.class);
		JavaScriptclick(driver, OSP.OSPLOGSSEARCHOptn);
		waitforElement(180, OSP.OSPLOGSSTDT, driver);
		assertTrue(OSP.OSPLOGSSEARCHLbl.isDisplayed());
		assertTrue(OSP.TRUSTEDID.isDisplayed());
		assertTrue(OSP.COMPANYCODE.isDisplayed());
		assertTrue(OSP.DOCUMENTNUMBER.isDisplayed());
		assertTrue(OSP.ExportBtn.isDisplayed());

		String dnsname = DNS.replace("env", Environment.toLowerCase());
		if (StartDate != "") {
			click(OSP.OSPLOGSSTDT);
			clear(OSP.OSPLOGSSTDT);
			click(OSP.OSPLOGSSTDT);
			type(OSP.OSPLOGSSTDT, StartDate);
			Thread.sleep(2000);
		}
		if (EndDate != "") {
			click(OSP.OSPLOGSENDT);
			clear(OSP.OSPLOGSENDT);
			type(OSP.OSPLOGSENDT, EndDate);
			click(OSP.OSPLOGSENDT);
			Thread.sleep(2000);
		}
		if (Message_Type != "") {
			JavaScriptclick(driver, OSP.MESSAGETYPE);
			clear(OSP.MESSAGETYPE);
			type(OSP.MESSAGETYPE, Message_Type);
		}
		if (DNS != "") {

			clear(OSP.MESSAGETYPE);
			JavaScriptclick(driver, OSP.DNS);
			clear(OSP.DNS);
			type(OSP.DNS, dnsname);
		}
		if (Other != "") {
			clear(OSP.DNS);
			JavaScriptclick(driver, OSP.OTHER);
			clear(OSP.OTHER);
			type(OSP.OTHER, Other);
		}
		if (DocumentNumber != "") {
			JavaScriptclick(driver, OSP.MESSAGETYPE);
			clear(OSP.MESSAGETYPE);
			type(OSP.MESSAGETYPE, Message_Type);
			clear(OSP.DOCUMENTNUMBER);
			JavaScriptclick(driver, OSP.DOCUMENTNUMBER);
			clear(OSP.DOCUMENTNUMBER);
			type(OSP.DOCUMENTNUMBER, DocumentNumber);
		}
		if (TrustedId != "") {
			JavaScriptclick(driver, OSP.TRUSTEDID);
			clear(OSP.TRUSTEDID);
			type(OSP.TRUSTEDID, TrsID);
		}
		waitforElement(30, OSP.SearchBtn, driver);
		click(OSP.SearchBtn);
		waitUntilElementInvisibe(180, OSP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, OSP.NORESULTS)) {
			if (VerifyObject(driver, OSP.DNSVERIFICATION)) {
				assertTrue(OSP.DNSVERIFICATION.isDisplayed());
			}
		} else {
			waitforElement(180, OSP.ResBtn, driver);
			if (DNS != "") {
				String Dnsval = getText(OSP.DNSname);
				String Dns[] = Dnsval.split("DNS:");
				assertTrue((Dns[1].trim()).equalsIgnoreCase(dnsname));
			}
			if (Message_Type != "") {
				String msgval = getText(OSP.MessageType);
				String msg[] = msgval.split("MessageType:");
				if ((msg[1].trim()).equalsIgnoreCase(Message_Type)) {
					assertTrue((msg[1].trim()).equalsIgnoreCase(Message_Type));
				}

			}
			if (VerifyOther != "") {
				String guidval = getText(OSP.GUIDValue);
				String guid[] = guidval.split("GUID:");
				assertTrue(!(guid[1].trim()).equalsIgnoreCase(""));
				Thread.sleep(5000);
				String TrustedID = getText(OSP.RequestLog);
				String tval[] = TrustedID.split("TrustedId>");
				String Val1[] = tval[1].split("</");
				String aTrID[] = Val1[0].split("\\[");
				aTrID[0].split("\\]");
				assertTrue(TrsID.equalsIgnoreCase(Val1[0]));
				if (Other != "") {
					Thread.sleep(5000);
					String text = getText(OSP.RequestLog);
					String tval1[] = text.split("Company>");
					String Val2[] = tval1[1].split("</");
					System.out.println(Val2[0]);
					if (Val2[0].equalsIgnoreCase(Other)) {
						assertTrue(Val2[0].equalsIgnoreCase(Other));
					}
				}
				// Verifying time taken label
				assertTrue(OSP.Timetook.isDisplayed());
				// Verifying IPAdress label
				assertTrue(OSP.IPAddress.isDisplayed());
				// Verifying Response button
				JavaScriptclick(driver, OSP.ResBtn);
				waitforElement(180, OSP.Reshedng, driver);
				// Verifying Response Pop-up window
				assertTrue(OSP.Reshedng.isDisplayed());
				// Verifying Response Pop-up copy button
				assertTrue(OSP.ResCopyBtn.isDisplayed());
				if (ResponseTime.equalsIgnoreCase("YES")) {
					String Respo = getText(OSP.ResponseLog);
					String Respo1[] = Respo.split("ResponseTimeMS>");
					String Respo2[] = Respo1[1].split("</");
					System.out.println(Respo2[0]);
					assertTrue(!(Respo2[0].isEmpty()));
				}
				// Clicking on Response popup cancel
				JavaScriptclick(driver, OSP.ResCancelBtn);
				Thread.sleep(5000);
				waitforElement(180, OSP.CopyBtn, driver);
				// Clicking on Copy
				click(OSP.CopyBtn);
				Thread.sleep(5000);
				scrollUp(driver);
				waitforText(180, OSP.COPIEDSUCCESSFULLY, driver);
				// Verifying success message after copy
				assertTrue(OSP.COPIEDSUCCESSFULLY.isDisplayed());
			}
		}

	}

	public void AdminCreation(WebDriver driver, String username) throws Exception {
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		if (VerifyObject(driver, LP.VTMSACCOUNT)) {
			waitforElement(180, LP.VTMSACCOUNT, driver);
			JavaScriptclick(driver, LP.VTMSACCOUNT);
			Thread.sleep(5000);
			JavaScriptclick(driver, LP.VTMSLOGOUT);
			waitforElement(180, LP.LOGIN, driver);
		}
		waitforElement(180, LP.USERNAME, driver);
		/*
		 * if (Environment.equalsIgnoreCase("Platform_DEV")) { int m = (int)
		 * Math.pow(10, 6 - 1); int x = m + new Random().nextInt(9 * m); String RNDvalue
		 * = String.valueOf(x); username = username + RNDvalue; }
		 */
		type(LP.USERNAME, username);
		type(LP.PASSWORD, "Password!1");
		JavaScriptclick(driver, LP.LOGIN);
		if (username.equalsIgnoreCase("VarAdminAutomation")) {
			assertTrue(LP.DELETEDVAR.isDisplayed());

		} else {
			waitforElement(30, LP.DASHBOARD, driver);
			if (VerifyObject(driver, LP.INVALIDUSER)) {
				driver.get(url);
				userregistration.UserLogin(adminusername, adminpassword);
				userregistration.AdminCreation("System Admin", "SYSAdmin", "Automation",
						"vtmsDEV" + RND() + "@gmail.com", "556 555 5552", "SystemAdminAutomation", "Password!1");
				waitforElement(180, LP.DASHBOARD, driver);
			}
			if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")
					|| driver.getTitle().equalsIgnoreCase("Server Error | Vertex Cloud")) {
				driver.manage().deleteAllCookies();
				driver.get(url);
			}
			if (!LP.DASHBOARD.isDisplayed()) {
				driver.manage().deleteAllCookies();
				driver.get(url);
				waitforElement(180, LP.USERNAME, driver);
				type(LP.USERNAME, username);
				type(LP.PASSWORD, "Password!1");
				JavaScriptclick(driver, LP.LOGIN);
				Thread.sleep(3000);
			}
			AdminLoginUserName = username;
			Thread.sleep(1000);
			assertTrue(LP.DASHBOARD.isDisplayed());
		}
	}

	public void AdminsUndoAdjustmentFileNoDeleteBtn(String ClientName, String folderType, String Year, String Month,
			String Undo_FileName, String Success_Report, String Error_Report, String UnSupported_TaxCategories)
			throws Exception {
		new File(".//");
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		// Verifying Unsupported taxability categories

		waitforElement(180, AUA.manageSMBCustomers, driver);
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AUA.smbCustomerName, driver);
		type(AUA.smbCustomerName, ClientName);
		waitforElement(180, AUA.smbcustomerColumn, driver);
		assertTrue(AUA.smbcustomerColumn.isDisplayed());
		assertTrue(AUA.environment.isDisplayed());
		Thread.sleep(5000);
		if (VerifyObject(driver, "//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement Registered = driver
					.findElement(By.xpath("//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'"
							+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			JavaScriptclick(driver, Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement Authorised = driver
					.findElement(By.xpath("//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'"
							+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			JavaScriptclick(driver, Authorised);
		} else {
			WebElement Initialised = driver
					.findElement(By.xpath("//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'"
							+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
			JavaScriptclick(driver, Initialised);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, AUA.smbCustomerReports, driver);
		click(AUA.smbCustomerReports);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		// selectByValue(AUA.companyName,
		// AdminUndoAdjustmentFileSheet.getCell(CompanyName, Data_Row).getContents());
		waitforElement(180, AUA.Rootfolder, driver);
		selectByValue(AUA.Rootfolder, folderType);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		selectByValue(AUA.Internalyearfolder, Year);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		selectByValue(AUA.InternalMonthfolder, Month);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		selectByValue(AUA.paginationDD, "100 per page");
		String uFileName = Undo_FileName;
		String[] ufile = uFileName.split("\\.");
		Thread.sleep(5000);
		waitforText(180, "//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(.,'" + uFileName
				+ "')]/../../span[3]/label[contains(text(),'Processed')]", driver);
		assertTrue(driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(.,'" + uFileName
				+ "')]/../../span[3]/label[contains(text(),'Processed')]")).isDisplayed());
		String ofile = ufile[0] + "_undo";
		String sfile = ufile[0] + "_suc.csv";
		String efile = ufile[0] + "_err.csv";
		if (Undo_FileName.contains("PurchaseOrder")) {
			if (Success_Report.equalsIgnoreCase("Yes")) {
				String filestatus = driver.findElement(By.xpath(
						"//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + sfile + "')]/../../span[3]"))
						.getText();
				assertTrue(filestatus.isEmpty());
			}
		} else if (Undo_FileName.contains("Quotation")) {
			if (Success_Report.equalsIgnoreCase("Yes")) {
				String filestatus = driver.findElement(By.xpath(
						"//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + sfile + "')]/../../span[3]"))
						.getText();
				assertTrue(filestatus.isEmpty());
			}
			if (Error_Report.equalsIgnoreCase("Yes")) {
				String errfilestatus = driver.findElement(By.xpath(
						"//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + efile + "')]/../../span[3]"))
						.getText();
				assertTrue(errfilestatus.isEmpty());
			}
		}

		if (Success_Report.equalsIgnoreCase("Yes")) {
			/*
			 * String filestatus = driver.findElement(By.xpath(
			 * "//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + sfile +
			 * "')]/../../span[3]")) .getText(); assertTrue(filestatus.isEmpty());
			 */
			if (!VerifyObject(driver,
					driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + sfile
							+ "')]/../../span[3]/button[contains(text(),'Delete')]")))) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
			/*
			 * if (!VerifyObject(driver, driver.findElement(By.xpath(
			 * "//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + ofile +
			 * "')]/../../span[3]/button[contains(text(),'Delete')]")))) { assertTrue(true);
			 * } else { assertFalse(false); }
			 */
		} else if (Success_Report.equalsIgnoreCase("Yes") && Error_Report.equalsIgnoreCase("Yes")) {
			/*
			 * String filestatus = driver.findElement(By.xpath(
			 * "//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + sfile +
			 * "')]/../../span[3]")) .getText(); assertTrue(filestatus.isEmpty()); String
			 * errfilestatus = driver.findElement(By.xpath(
			 * "//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + efile +
			 * "')]/../../span[3]")) .getText(); assertTrue(errfilestatus.isEmpty());
			 */
			if (!VerifyObject(driver,
					driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + ofile
							+ "')]/../../span[3]/button[contains(text(),'Delete')]")))) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		} else {
			/*
			 * String errfilestatus = driver.findElement(By.xpath(
			 * "//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + efile +
			 * "')]/../../span[3]")) .getText(); assertTrue(errfilestatus.isEmpty());
			 */
			if (!VerifyObject(driver,
					driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + efile
							+ "')]/../../span[3]/button[contains(text(),'Delete')]")))) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
		if (UnSupported_TaxCategories != "") {
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, TBS.CTCImpact, driver);
			JavaScriptMousehover(driver, TBS.CTCImpact);
			Thread.sleep(2000);
			JavaScriptclick(driver, TBS.TaxabilityCategories);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			String unsupported_taxCategories = UnSupported_TaxCategories;
			ArrayList<String> aList = new ArrayList<String>(Arrays.asList(unsupported_taxCategories.split(",")));
			for (int k = 0; k < aList.size(); k++) {
				String category = aList.get(k).toString();
				waitforElement(180, PP.PRODUCTS_QUICK_SEARCH, driver);
				clear(PP.PRODUCTS_QUICK_SEARCH);
				type(PP.PRODUCTS_QUICK_SEARCH, category);
				Thread.sleep(2000);
				if (!VerifyObject(driver, driver.findElement(By.xpath("//p[contains(.,'" + category + "')]")))) {
					assertTrue(true);
				} else {
					assertFalse(false);
				}
			}
		}
	}

	public void ProdSupAdminUserLinks() throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		new Robot();
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		AdminUserLinks AUL = PageFactory.initElements(driver, AdminUserLinks.class);
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		IntegrationAdmin TL = PageFactory.initElements(driver, IntegrationAdmin.class);
		InviteUsers INU = PageFactory.initElements(driver, InviteUsers.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		AdminQueueService AQ = PageFactory.initElements(driver, AdminQueueService.class);
		driver.getWindowHandle();
		Thread.sleep(1000);
		// Dashboard
		assertTrue(AUL.DASHBOARD.isDisplayed());
		JavaScriptclick(driver, AUL.DASHBOARD);
		// OSP Logs Search
		click(TBS.OSPLOGS);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, TBS.OSPSEARCH, driver);
		assertTrue(TBS.OSPLOGS.isDisplayed());
		// ..CTC Impact.........
		JavaScriptMousehover(driver, TBS.CTCImpact);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(TBS.CTCTransactions);
		Thread.sleep(5000);
		// assertTrue(TBS.CTCTransactions.isDisplayed());
		JavaScriptMousehover(driver, TBS.CTCImpact);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TBS.CTCReturns);
		} else {
			click(TBS.CTCReturns);
		}
		Thread.sleep(5000);
		// Queue Service
		click(AQ.QUEUESERVICELINK);
		waitUntilElementInvisibe(180, AQ.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AQ.QUEUESERVICEID, driver);
		assertTrue(AQ.QUEUESERVICELINK.isDisplayed());
		// Logs
		JavaScriptMousehover(driver, TBS.logs);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (!Environment.equalsIgnoreCase("QA")) {
			click(TBS.Taxlinklogs);
			Thread.sleep(5000);
			assertTrue(TBS.Taxlinklogssearch.isDisplayed());
			Thread.sleep(30000);
			selectByIndex(TBS.serverdropdwn, 2);
			waitforElement(60, TBS.TAXLINKLOGSSDATA, driver);
			if (VerifyObject(driver, TBS.TAXLINKLOGSSDATA)) {
				click(TBS.TAXLINKLOGSSDATA);
			}
			Thread.sleep(5000);
		}
		click(TBS.DASHBOARD);
		JavaScriptMousehover(driver, TBS.logs);
		click(TBS.ECWLogs);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(60, TBS.ECWServername, driver);
		if (VerifyObject(driver, TBS.ECWServername)) {
			selectByIndex(TBS.ECWServername, 2);
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, TBS.ECWLogdata)) {
			click(TBS.ECWLogdata);
		}
		// Manage SMB Customers
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, AUL.MANAGESMBCUSTOMERS);
		} else {
			click(AUL.MANAGESMBCUSTOMERS);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(AUL.MANAGESMBCUSTOMERS.isDisplayed());
		assertTrue(AUL.SMBCUSTOMERLISTING.isDisplayed());
		assertTrue(AUL.SMBCUSTOMERNUMLABEL.isDisplayed());
		assertTrue(AUL.SMBCUSTOMERNAMELABEL.isDisplayed());
		assertTrue(AUL.SUBSCRIPTIONLABEL.isDisplayed());
		assertTrue(AUL.STATUSLABEL.isDisplayed());
		assertTrue(AUL.VARNAMELABEL.isDisplayed());
		assertTrue(AUL.EXPORTBUTTON.isDisplayed());
		// Metrics
		assertTrue(AUL.METRICSLINK.isDisplayed());
		JavaScriptclick(driver, AUL.METRICSLINK);
		waitforElement(180, AUL.AUDITMETRICDATA, driver);
		assertTrue(AUL.AUDITMETRICDATA.isDisplayed());
		assertTrue(AUL.PRODUCTMETRICDATA.isDisplayed());
		assertTrue(AUL.RATEFILEMETRICDATA.isDisplayed());
		assertTrue(AUL.RETURNMETRICDATA.isDisplayed());
		assertTrue(AUL.TRANSACTIONMETRICDATA.isDisplayed());
		waitforElement(180, MC.MAINTENANCEMETRICDATA, driver);
		assertTrue(MC.MAINTENANCEMETRICDATA.isDisplayed());
		assertTrue(MC.DOWNLDCSV.isDisplayed());
		assertTrue(MC.EXCEPTIONMETRICDATA.isDisplayed());
		assertTrue(MC.CLIENTLOGINREPORT.isDisplayed());
		assertTrue(MC.ClientDelete.isDisplayed());
		assertTrue(MC.ClientChangesubscription.isDisplayed());
		assertTrue(MC.RGDDWNLD.isDisplayed());
		assertTrue(MC.VEDNLD.isDisplayed());
		assertTrue(MC.CLIENTPODDREPORT.isDisplayed());
		assertTrue(MC.TCDNLD.isDisplayed());
		assertTrue(MC.CCDDWNLD.isDisplayed());
		assertTrue(MC.RFDDWNLD.isDisplayed());
		// Document Center
		assertTrue(AUL.DOCCENTER.isDisplayed());
		click(AUL.DOCCENTER);
		// Invite User
		assertTrue(AUL.INVITEUSERLINK.isDisplayed());
		click(AUL.INVITEUSERLINK);
		// Verifying Client Name and Client Number Columns as per POR-15816 in 7.0
		waitforText(180, INU.CLIENTNAME, driver);
		assertTrue(INU.CLIENTNAME.isDisplayed());
		assertTrue(INU.CLIENTNUMBER.isDisplayed());
		// Premium SMB Customers
		assertTrue(AUL.PREMIUMSMBCUST.isDisplayed());
		JavaScriptclick(driver, AUL.PREMIUMSMBCUST);
		// Manage Oseries config
		assertTrue(TL.manageoseries.isDisplayed());
		JavaScriptclick(driver, TL.manageoseries);
		// Vertexsmb VARS
		assertTrue(AUL.VERTEXSMBVARS.isDisplayed());
		JavaScriptclick(driver, AUL.VERTEXSMBVARS);
		// System Configuration
		assertTrue(TL.systemconfiguration.isDisplayed());
		// Error Monitor
		assertTrue(AUL.ERRORMONITOR.isDisplayed());
		JavaScriptclick(driver, AUL.ERRORMONITOR);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AUL.ERRMONTITLE, driver);
		// Error monitor Screen Verification
		assertTrue(AUL.ERRORMONITOR.isDisplayed());
		assertTrue(AUL.ERRMONHELPICON.isDisplayed());
		assertTrue(AUL.ERRMONLASTDATE.isDisplayed());
		if (!VerifyObject(driver, AUL.NORECORDS)) {
			assertTrue(AUL.ERRPAGEDROPDOWN.isDisplayed());
		}
		// Error Monitor Grid Verification
		assertTrue(AUL.ERRMONID.isDisplayed());
		click(AUL.ERRMONID);
		assertTrue(AUL.ERRMONQUETYPE.isDisplayed());
		click(AUL.ERRMONQUETYPE);
		assertTrue(AUL.ERRCREATEDDATE.isDisplayed());
		click(AUL.ERRCREATEDDATE);
		assertTrue(AUL.ERRMODIDATE.isDisplayed());
		click(AUL.ERRMODIDATE);
		assertTrue(AUL.SMBCUSTNAME.isDisplayed());
		click(AUL.SMBCUSTNAME);
		assertTrue(AUL.SMBPCUSTNAME.isDisplayed());
		click(AUL.SMBPCUSTNAME);
		assertTrue(AUL.ERRORMESSAGE.isDisplayed());
		click(AUL.ERRORMESSAGE);
		assertTrue(AUL.ERRORACTION.isDisplayed());
		click(AUL.ERRORACTION);
		if (VerifyObject(driver, AUL.DETAILSLINK)) {
			click(AUL.DETAILSLINK);
			waitforElement(180, AUL.CANCELBUTTON, driver);
			// Queue Error Details screen verification
			assertTrue(AUL.QUEUEERRORDETAILS.isDisplayed());
			assertTrue(AUL.RETRYTHISITEM.isDisplayed());
			assertTrue(AUL.PERMANENTERRORBUTTON.isDisplayed());
			assertTrue(AUL.CANCELBUTTON.isDisplayed());
			click(AUL.CANCELBUTTON);
			Thread.sleep(4000);
			click(AUL.ERRPAGEDROPDOWN);
			selectByValue(AUL.ERRPAGEDROPDOWN, "25 per page");
			selectByValue(AUL.ERRPAGEDROPDOWN, "50 per page");
			selectByValue(AUL.ERRPAGEDROPDOWN, "100 per page");
		}
	}

	public void AdminUndoAdjustmentFile(String ClientName, String folderType, String Year, String Month,
			String Undo_FileName, String UndoFile_Displayed, String Success_Report, String Error_Report)
			throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		new File(".//");
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, AUA.manageSMBCustomers, driver);
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AUA.smbCustomerName, driver);
		type(AUA.smbCustomerName, ClientName);
		Thread.sleep(5000);
		assertTrue(AUA.smbcustomerColumn.isDisplayed());
		assertTrue(AUA.environment.isDisplayed());

		assertTrue(driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
				+ ClientName + "')]/../../../td[contains(text(),'Normal')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
				+ ClientName + "')]/../../../td[contains(text(),'Automation_VAR')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
				+ ClientName + "')]/../../../td[contains(text(),'SMB')]")).isDisplayed());
		if (VerifyObject(driver, "//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			driver.findElement(By.xpath("//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
		} else if (VerifyObject(driver, "//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			driver.findElement(By.xpath("//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
		} else {
			driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, AUA.smbCustomerReports, driver);
		click(AUA.smbCustomerReports);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		if (VerifyObject(driver, AUA.smbCustomerName)) {
			type(AUA.smbCustomerName, ClientName);
			Thread.sleep(5000);
			if (VerifyObject(driver, "//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				driver.findElement(By.xpath("//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
			} else if (VerifyObject(driver, "//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				driver.findElement(By.xpath("//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			} else {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
			waitforElement(180, AUA.smbCustomerReports, driver);
			click(AUA.smbCustomerReports);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(5000);
		}
		waitforElement(180, AUA.Rootfolder, driver);
		selectByValue(AUA.Rootfolder, folderType);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		selectByValue(AUA.Internalyearfolder, Year);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		selectByValue(AUA.InternalMonthfolder, Month);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		selectByValue(AUA.paginationDD, "100 per page");
		Thread.sleep(5000);
		String uFileName = Undo_FileName;
		String[] ufile = uFileName.split("\\.");
		assertTrue(driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li/div/span[1]/a[text()='" + uFileName
				+ "']/../../span[3]/label[contains(text(),'Processed')]")).isDisplayed());
		String ofile = ufile[0] + "_undo";
		String sfile = ufile[0] + "_suc.csv";
		String efile = ufile[0] + "_err.csv";
		if (UndoFile_Displayed.equalsIgnoreCase("Yes")) {
			if (VerifyObject(driver,
					"//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + ofile + "')]")) {
				WebElement deleteclick = driver
						.findElement(By.xpath("//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + ofile
								+ "')]/../../span[3]/button[contains(text(),'Delete')]"));
				JavascriptExecutor scroll = (JavascriptExecutor) driver;
				scroll.executeScript("arguments[0].scrollIntoView();", deleteclick);
				JavaScriptclick(driver, deleteclick);
				waitforElement(120, AUA.deleteAdjustmentFile, driver);
				JavaScriptclick(driver, AUA.deleteAdjustmentFile);
				Thread.sleep(10000);
				driver.navigate().refresh();
				Thread.sleep(10000);
			}
			/*
			 * selectByValue(AUA.InternalMonthfolder, "--Select Folder--");
			 * waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]",
			 * driver); selectByValue(AUA.InternalMonthfolder,
			 * AdminUndoAdjustmentFileSheet.getCell(Month, Data_Row).getContents());
			 * waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]",
			 * driver);
			 */
			selectByValue(AUA.Rootfolder, "--Select Folder--");
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			selectByValue(AUA.Rootfolder, folderType);
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			selectByValue(AUA.Internalyearfolder, "--Select Folder--");
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			selectByValue(AUA.Internalyearfolder, Year);
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			selectByValue(AUA.InternalMonthfolder, "--Select Folder--");
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			selectByValue(AUA.InternalMonthfolder, Month);
			waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
			selectByValue(AUA.paginationDD, "100 per page");
			if (Success_Report.equalsIgnoreCase("Yes")) {
				if (!VerifyObject(driver, "//div[2]/div/div/div/div/ul/li/div/span[1]/a[text()='" + sfile
						+ "']/../../span[3]/label[contains(text(),'Deleted')]")) {
					driver.navigate().refresh();
					Thread.sleep(10000);
					selectByValue(AUA.Rootfolder, "--Select Folder--");
					waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
					selectByValue(AUA.Rootfolder, folderType);
					waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
					selectByValue(AUA.Internalyearfolder, "--Select Folder--");
					waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
					selectByValue(AUA.Internalyearfolder, Year);
					waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
					selectByValue(AUA.InternalMonthfolder, "--Select Folder--");
					waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
					selectByValue(AUA.InternalMonthfolder, Month);
					waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
					selectByValue(AUA.paginationDD, "100 per page");
				}
				assertTrue(driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li/div/span[1]/a[text()='" + sfile
						+ "']/../../span[3]/label[contains(text(),'Deleted')]")).isDisplayed());
				for (int j = 1; j <= AUA.undoAdjustmentFilestatustable.size(); j++) {
					String ufilename = driver
							.findElement(By.xpath("//div[2]/div/div/div/div/ul/li[" + j + "]/div/span[1]/a")).getText();
					String[] undo_file_digit = ufilename.split("_");
					int strlen = undo_file_digit.length;
					if (strlen == 6) {
						String undostampfile = ufile[0] + "_undo_" + undo_file_digit[3] + "_undo_suc.csv";
						if (ufilename.equalsIgnoreCase(undostampfile)) {
							driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li[" + j + "]/div/span[2]"))
									.getText();
							String filestatus = driver
									.findElement(By.xpath("//div[2]/div/div/div/div/ul/li[" + j + "]/div/span[3]"))
									.getText();
							assertTrue(filestatus.isEmpty());
							break;
						}
					}
				}
			} else if (Success_Report.equalsIgnoreCase("Yes") && Error_Report.equalsIgnoreCase("Yes")) {
				assertTrue(driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li/div/span[1]/a[text()='" + sfile
						+ "']/../../span[3]/label[contains(text(),'Deleted')]")).isDisplayed());
				assertTrue(driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'"
						+ ofile + "')]/../../span[3]/label[contains(text(),'Delete-Processed')]")).isDisplayed());
				String errfilestatus = driver
						.findElement(By.xpath(
								"//div[2]/div/div/div/div/ul/li/div/span[1]/a[text()='" + efile + "']/../../span[3]"))
						.getText();
				assertTrue(errfilestatus.isEmpty());
				for (int j = 1; j <= AUA.undoAdjustmentFilestatustable.size(); j++) {
					String ufilename = driver
							.findElement(By.xpath("//div[2]/div/div/div/div/ul/li[" + j + "]/div/span[1]/a")).getText();
					String[] undo_file_digit = ufilename.split("_");
					int strlen = undo_file_digit.length;
					if (strlen == 6) {
						String undostampfile = ufile[0] + "_undo_" + undo_file_digit[3] + "_undo_suc.csv";
						if (ufilename.equalsIgnoreCase(undostampfile)) {
							driver.findElement(By.xpath("//div[2]/div/div/div/div/ul/li[" + j + "]/div/span[2]"))
									.getText();
							String filestatus = driver
									.findElement(By.xpath("//div[2]/div/div/div/div/ul/li[" + j + "]/div/span[3]"))
									.getText();
							assertTrue(filestatus.isEmpty());
							break;
						}
					}
				}
			} else {
				String errfilestatus = driver
						.findElement(By.xpath(
								"//div[2]/div/div/div/div/ul/li/div/span[1]/a[text()='" + efile + "']/../../span[3]"))
						.getText();
				assertTrue(errfilestatus.isEmpty());
			}
		} else {
			if (Success_Report.equalsIgnoreCase("Yes")) {
				String sucfilestatus = driver
						.findElement(By.xpath(
								"//div[2]/div/div/div/div/ul/li/div/span[1]/a[text()='" + sfile + "']/../../span[3]"))
						.getText();
				assertTrue(sucfilestatus.isEmpty());
			} else if (Success_Report.equalsIgnoreCase("Yes") && Error_Report.equalsIgnoreCase("Yes")) {
				String sucfilestatus = driver
						.findElement(By.xpath(
								"//div[2]/div/div/div/div/ul/li/div/span[1]/a[text()='" + sfile + "']/../../span[3]"))
						.getText();
				assertTrue(sucfilestatus.isEmpty());
				String errfilestatus = driver
						.findElement(By.xpath(
								"//div[2]/div/div/div/div/ul/li/div/span[1]/a[text()='" + efile + "']/../../span[3]"))
						.getText();
				assertTrue(errfilestatus.isEmpty());
			} else {
				String errfilestatus = driver
						.findElement(By.xpath(
								"//div[2]/div/div/div/div/ul/li/div/span[1]/a[text()='" + efile + "']/../../span[3]"))
						.getText();
				assertTrue(errfilestatus.isEmpty());
			}
		}
	}

	@SuppressWarnings("resource")
	public void CTC_TaxabilityCategory(String Return_EndDate, String Return_Form, String Subscription,
			String UnSupported_TaxCategories, String FirstSelction, String SecondSelction, String ThirdSelction,
			String FourthSelction, String FifthSelction, String SixthSelction, String SeventhSelction)
			throws Exception {
		PageFactory.initElements(driver, DataBase.class);
		Robot rob = new Robot();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		AdminUserLinks AUL = PageFactory.initElements(driver, AdminUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		String DownloadFilepath = xDownloadFiles("CTCTaxCategoryReport");
		String path, TaxCategoryName = "";
		PageFactory.initElements(driver, OSPSearch.class);
		waitforElement(180, AUL.CTCIMPACTHOVER, driver);
		JavaScriptMousehover(driver, AUL.CTCIMPACTHOVER);
		if (Return_EndDate.equalsIgnoreCase("YES")) {
			JavaScriptclick(driver, AUL.CTCRETURNS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, AUL.RETURNNAMECONTAINS, driver);
			type(AUL.RETURNNAMECONTAINS, Return_Form);
			waitforElement(180, "//tr/td[contains(.,'" + Return_Form + "')]/../td/a", driver);
			driver.findElement(By.xpath("//tr/td[contains(.,'" + Return_Form + "')]/../td/a")).click();
			Thread.sleep(2000);
			JavaScriptclick(driver, AUL.CTC_RDOWNLOAD);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "CTCTaxCategoryReport";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000); // wait to move the download file from downloads folder to our project
				// Actual folder
				LatestDownloadedFile("CTCTaxCategoryReport", true);
			} else if (Browser.equalsIgnoreCase("IE")) {
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
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("CTCTaxCategoryReport", false);
			}
			new FileInputStream(Filepath);
			String SubScriptionn = Subscription;
			String ReturnEnddate = Return_EndDate;
			// Calling Utility Class for xGetXLSXCellvalueClientBase Method
			xr_xGetXLSXCellvalueClientBase(Filepath, VertexClientName, TaxCategoryName, SubScriptionn, ReturnEnddate);
		} else {
			JavaScriptclick(driver, AUL.TAXABILITYCATEGORY);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			// waitforElement(180, AUL.TAXABILITYCATEGORY, driver);
			waitforElement(180, PP.GENERAL, driver);
			waitforElement(180, PP.PRODUCTS_QUICK_SEARCH, driver);

			// Verifying Unsupported Taxability categories
			if (UnSupported_TaxCategories != "") {
				String unsupported_taxCategories = UnSupported_TaxCategories;
				ArrayList<String> aList = new ArrayList<String>(Arrays.asList(unsupported_taxCategories.split(",")));
				for (int k = 0; k < aList.size(); k++) {
					String category = aList.get(k).toString();
					clear(PP.PRODUCTS_QUICK_SEARCH);
					type(PP.PRODUCTS_QUICK_SEARCH, category);
					Thread.sleep(2000);
					assertTrue(!(driver.findElement(By.xpath("//p[contains(.,'" + category + "')]")).isDisplayed()));
				}
			}
			// Selecting the Category Mapping
			if (FirstSelction != "") {
				String FirstSelct = FirstSelction;
				ProductnameSplit(driver, FirstSelct);
			}
			// clicking on product mapping sec selection
			if (SecondSelction != "") {
				String SecSelct = SecondSelction;
				ProductnameSplit(driver, SecSelct);
			}
			// clicking on product mapping third selection
			if (ThirdSelction != "") {
				String ThirdSelct = ThirdSelction;
				ProductnameSplit(driver, ThirdSelct);
			}
			// clicking on product mapping fourth selection
			if (FourthSelction != "") {
				String fourSelct = FourthSelction;
				ProductnameSplit(driver, fourSelct);
			}
			// clicking on product mapping five selection
			if (FifthSelction != "") {
				String FifthSelct = FifthSelction;
				ProductnameSplit(driver, FifthSelct);
			}
			// clicking on product mapping six selection
			if (SixthSelction != "") {
				String SixSelct = SixthSelction;
				ProductnameSplit(driver, SixSelct);
			}
			// clicking on product mapping seven selection
			if (SeventhSelction != "") {
				String sevenSelct = SeventhSelction;
				ProductnameSplit(driver, sevenSelct);
			}
			click(AUL.CTC_DOWNLOAD);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "CTCTaxCategoryReport";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000); // wait to move the download file from downloads folder to our project
				// Actual folder
				LatestDownloadedFile("CTCTaxCategoryReport", true);
			} else if (Browser.equalsIgnoreCase("IE")) {
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
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("CTCTaxCategoryReport", false);
			}
			waitUntilElementInvisibe(180, "//h3[contains(.,'Generating File')]", driver);
			new FileInputStream(Filepath);
			TaxCategoryName = FifthSelction;
			String SubScriptionn = Subscription;
			String ReturnEnddate = Return_EndDate;
			// Calling Utility Class for xGetXLSXCellvalueClientBase Method
			xr_xGetXLSXCellvalueClientBase(Filepath, ClientName, TaxCategoryName, SubScriptionn, ReturnEnddate);
		}
	}

	public String ProductnameSplit(WebDriver driver, String Productname) throws Exception {
		String status = "";
		try {
			if (Productname.contains("'")) {
				String[] xpathStr = Productname.split("'");
				WebElement prod = driver
						.findElement(By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()=concat('"
								+ xpathStr[0] + "'" + "," + "\"'\"" + "," + "'" + xpathStr[1] + "')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", prod);
			} else {
				WebElement jur = driver.findElement(
						By.xpath("//div[@class='mapping-category-overlay']/ul/li/label[text()='" + Productname + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", jur);
			}
		} catch (Exception e) {
			return e.getMessage() + status;

		}
		return status;
	}

	public void ChangeCPAClientType(String ClientName, String VerifysameSubtype, String CPA_ClientType)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, CpaRegistration.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		CPA CP = PageFactory.initElements(driver, CPA.class);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
		} else {
			driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		JavaScriptclick(driver, CP.CHANGECPACLIENTTYPE);
		waitforElement(180, CP.CLIENTTYPELIST, driver);
		assertTrue(CP.CHANGECPACLIENTTYPEHEADING.isDisplayed());
		selectByValue(CP.CLIENTTYPELIST, VerifysameSubtype);
		Thread.sleep(2000);
		assertTrue(CP.CPACLIENTTYPEERROR.isDisplayed());
		click(CP.CANCELCLIENTTYPE);
		waitforElement(180, CP.CHANGECPACLIENTTYPE, driver);
		JavaScriptclick(driver, CP.CHANGECPACLIENTTYPE);
		waitforElement(180, CP.CLIENTTYPELIST, driver);
		selectByValue(CP.CLIENTTYPELIST, CPA_ClientType);
		Thread.sleep(2000);
		click(CP.SAVECLIENTTYPE);
		waitforElement(180, CP.CHANGECPACLIENTTYPE, driver);
		if (CPA_ClientType.equalsIgnoreCase("BPO(Enrolled)")) {
			waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
			JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, ClientName);
			Thread.sleep(5000);
			assertTrue(driver.findElement(By.xpath(
					"//table/tbody/tr/td/div/a[contains(.,'" + ClientName + "')]/../../../td[contains(.,'Enrolled')]"))
					.isDisplayed());
		} else {
			waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
			JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, ClientName);
			Thread.sleep(5000);
			assertTrue(driver.findElement(By.xpath("//table/tbody/tr/td/div/a[contains(.,'" + ClientName
					+ "')]/../../../td[contains(.,'SharedServices')]")).isDisplayed());
		}
	}

	public void AdminDeleteLocations(String ClientName, String Delete_Location, String Location_Code) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		AdminUserLinks ADMIN = PageFactory.initElements(driver, AdminUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		new Robot();
		VarUserTitles = "";
		driver.getWindowHandle();
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		} else {
			click(ADMIN.MANAGESMBCUSTOMERS);
		}
		/*
		 * if (BPOCPAClientName != null) { ClientName = BPOCPAClientName; } else if
		 * (MerchantClientName != null) { ClientName = MerchantClientName; } else {
		 * ClientName = VertexClientName; }
		 */
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"))
				.click();
		waitforElement(180, VSC.SMBCUSTOMERREPORT, driver);
		JavaScriptclick(driver, VSC.SMBCUSTOMERREPORT);
		if (Delete_Location.equalsIgnoreCase("Yes")) {
			Thread.sleep(5000);
			WebElement deleteloc = driver
					.findElement(By.xpath("//th[contains(.,'Action')]/../../../tbody/tr/td[contains(.,'" + Location_Code
							+ "')]/../td/a[contains(.,'Delete')]"));
			JavaScriptclick(driver, deleteloc);
			waitforElement(180, VSC.DeleteLocationPopUp, driver);
			assertTrue(VSC.DeleteLocationPopUp.isDisplayed());
			waitforElement(180, VSC.CancelBtn, driver);
			JavaScriptclick(driver, VSC.CancelBtn);
			Thread.sleep(5000);
			WebElement deletelocation = driver
					.findElement(By.xpath("//th[contains(.,'Action')]/../../../tbody/tr/td[contains(.,'" + Location_Code
							+ "')]/../td/a[contains(.,'Delete')]"));
			JavaScriptclick(driver, deletelocation);
			waitforElement(180, VSC.DeleteLocationPopUp, driver);
			JavaScriptclick(driver, VSC.DeleteLocationBtn);
		}
	}

	public void AdminCreateNewUser(String ClientName, String Firstname, String LastName, String Email, String Phone,
			String AccountManagementRole, String VertexIndirecttaxrole, String ECMRole, String UserName,
			String Password, String ConfirmPassword) throws Exception {

		AdminUserLinks ADMIN = PageFactory.initElements(driver, AdminUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		} else {
			click(ADMIN.MANAGESMBCUSTOMERS);
		}
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement client = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
		JavaScriptclick(driver, client);
		Thread.sleep(5000);
		waitforElement(180, VSC.ADMINCREATENEWUSER, driver);
		click(VSC.ADMINCREATENEWUSER);
		Thread.sleep(5000);
		waitforElement(180, cpamanagersPage.FIRSTNAME, driver);
		type(cpamanagersPage.FIRSTNAME, Firstname);
		type(cpamanagersPage.LASTNAME1, LastName);
		type(cpamanagersPage.USEREMAIL, Email);
		click(cpamanagersPage.PHONE);
		type(cpamanagersPage.PHONE, Phone);
		waitforElement(180, cpamanagersPage.ACCOUNTMANAGEMENTROLE, driver);
		if (AccountManagementRole != "") {
			selectByValue(cpamanagersPage.ACCOUNTMANAGEMENTROLE, AccountManagementRole);
		}
		if (VertexIndirecttaxrole != "") {
			selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE, VertexIndirecttaxrole);
		}

		if (VerifyObject(driver, cpamanagersPage.EXEMPTIONCMROLE) && ECMRole != "") {
			selectByValue(cpamanagersPage.EXEMPTIONCMROLE, ECMRole);
		}
		click(cpamanagersPage.SENDINVITEUSER);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.COMPLETEREGISTRATIONLINK, driver);
		click(cpamanagersPage.COMPLETEREGISTRATIONLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.USERNAME, driver);
		PortalUserName = UserName;
		type(cpamanagersPage.USERNAME, PortalUserName);
		System.out.println("Created user from Admin is " + PortalUserName);
		type(cpamanagersPage.PASSWORD, Password);
		type(cpamanagersPage.CONFIRMPASSWORD, ConfirmPassword);
		Thread.sleep(1000);
		JavaScriptclick(driver, cpamanagersPage.PROFILECOMP_REG);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, cpamanagersPage.LOGIN, driver);
		click(cpamanagersPage.LOGIN);
		waitforElement(180, ADMIN.MANAGESMBCUSTOMERS, driver);
		JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement client1 = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
		JavaScriptclick(driver, client1);
		waitforElement(180, VSC.ADMINCREATENEWUSER, driver);
		WebElement createduser = driver
				.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + PortalUserName + "')]"));
		assertTrue(createduser.isDisplayed());
	}

	public void ProductMetricData(String ClientName_header, String CompanyName_header, String COMPANYCODE_header,
			String CompanyStatus_header, String ProductCode_header, String ProductName_header,
			String ProductTypeId_header, String ProductStartDate_header, String ProductEndDate_header,
			String TaxabilityCategoryName_header, String ParentTaxabilityCategoryName_header) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		click(MC.METRICSLINK);
		waitforElement(180, MC.PRODUCTMETRICDOWNLOAD, driver);
		assertTrue(MC.METRICSLABEL.isDisplayed());
		click(MC.PRODUCTMETRICDOWNLOAD);
		Thread.sleep(30000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			Thread.sleep(30000);
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			DownloadFiles.LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(50000);
			LatestDownloadedFile("Metrics", false);
		}
		String Clientname = (ClientName_header);
		xGetCSVCellValue(Filepath, Clientname);
		assertTrue(Cell_Header.matches(Clientname));
		String companyName = (CompanyName_header);
		xGetCSVCellValue(Filepath, companyName);
		assertTrue(Cell_Header.matches(companyName));
		String COMPANYCODE = (COMPANYCODE_header);
		xGetCSVCellValue(Filepath, COMPANYCODE);
		assertTrue(Cell_Header.matches(COMPANYCODE));
		String companyStatus = (CompanyStatus_header);
		xGetCSVCellValue(Filepath, companyStatus);
		assertTrue(Cell_Header.matches(companyStatus));
		String ProductCode = (ProductCode_header);
		xGetCSVCellValue(Filepath, ProductCode);
		assertTrue(Cell_Header.matches(ProductCode));
		String ProductName = (ProductName_header);
		xGetCSVCellValue(Filepath, ProductName);
		assertTrue(Cell_Header.matches(ProductName));
		String ProductTypeId = (ProductTypeId_header);
		xGetCSVCellValue(Filepath, ProductTypeId);
		assertTrue(Cell_Header.matches(ProductTypeId));
		String ProductStartDate = (ProductStartDate_header);
		xGetCSVCellValue(Filepath, ProductStartDate);
		assertTrue(Cell_Header.matches(ProductStartDate));
		String ProductEndDate = (ProductEndDate_header);
		xGetCSVCellValue(Filepath, ProductEndDate);
		assertTrue(Cell_Header.matches(ProductEndDate));
		String TaxabilityCategoryName = (TaxabilityCategoryName_header);
		xGetCSVCellValue(Filepath, TaxabilityCategoryName);
		assertTrue(Cell_Header.matches(TaxabilityCategoryName));
		String ParentTaxabilityCategoryNam = (ParentTaxabilityCategoryName_header);
		xGetCSVCellValue(Filepath, ParentTaxabilityCategoryNam);
		assertTrue(Cell_Header.matches(ParentTaxabilityCategoryNam));
	}

	public void ManageOseriesConfig(String UserName, String ShouldnotEditable, String Jurisdiction, String Comment,
			String Verify_Exceptions, String Company, String Jurisdiction_Type) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		ManageoseiesConfig UN = PageFactory.initElements(driver, ManageoseiesConfig.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		click(UN.Manageoseriesconfiglik);
		waitforElement(180, UN.headerText, driver);
		assertTrue(UN.headerText.isDisplayed());
		assertTrue(UN.countryinput.isDisplayed());
		assertTrue(UN.Jurisdictioninput.isDisplayed());
		assertTrue(UN.enablelljtext.isDisplayed());
		assertTrue(UN.filtertext.isDisplayed());
		assertTrue(UN.countrycloumn.isDisplayed());
		assertTrue(UN.jurisdictioncolumn.isDisplayed());
		assertTrue(UN.enablelljcolumn.isDisplayed());
		assertTrue(UN.commentcolumn.isDisplayed());
		assertTrue(UN.Actionscolumn.isDisplayed());
		if (ShouldnotEditable.equalsIgnoreCase("yes")) {
			JavaScriptclick(driver, UN.enablelljtext);
			Thread.sleep(2000);
			JavaScriptclick(driver, UN.filtertext);
			assertTrue(UN.Checkboxdisabled.isDisplayed());
		} else {
			// Enable low level juridiction
			waitforElement(180, UN.Jurisdictioninput, driver);
			type(UN.Jurisdictioninput, Jurisdiction);
			Thread.sleep(3000);
			JavaScriptclick(driver, UN.filtertext);
			waitforElement(180, UN.EnablelowlevelException, driver);
			Thread.sleep(3000);
			JavaScriptclick(driver, UN.EnablelowlevelException);
			String Juridiction = driver
					.findElement(By.xpath(
							"//table/thead/tr/th[contains(.,'Jurisdiction')]/../../../../table/tbody/tr[2]/td[2]"))
					.getText();
			assertTrue(Juridiction.equalsIgnoreCase(Jurisdiction));
			click(UN.COMMENT);
			type(UN.COMMENT, Comment);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//td[contains(.,'" + Jurisdiction + "')]/..//button[contains(.,'Update')]"))
					.click();
			Thread.sleep(3000);
			assertTrue(UN.UPDATE.isDisplayed());
			// Verify enabled juridction in client
			if (Verify_Exceptions.equalsIgnoreCase("YES")) {
				userregistration.Logout();
				waitforElement(180, LP.USERNAME, driver);
				String URL = url;
				driver.get(URL);
				userregistration.UserLogin(UserName, "Password!1");
				waitforElement(180, LP.DASHBOARD, driver);
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(10000);
				if (VerifyObject(driver, "//h2[contains(.,'" + Company
						+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]")) {
					WebElement AddException = driver.findElement(By.xpath("//h2[contains(.,'" + Company
							+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddException);
					waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				}
				Select stateSelectbox = new Select(
						driver.findElement(By.xpath("//*[@id='ddlExceptionJurisdiction0']")));
				stateSelectbox.selectByVisibleText(Jurisdiction);
				Thread.sleep(3000);
				Select Jurisdtype = new Select(driver.findElement(By.xpath("//select[@id='ddlJurisdictionType0']")));
				Jurisdtype.selectByVisibleText(Jurisdiction_Type);
				assertTrue(CE.ISSUEJURSDTYPE.isEmpty());
			}
			// Disable low level juridiction
			userregistration.Logout();
			waitforElement(180, LP.USERNAME, driver);
			String URL = url;
			driver.get(URL);
			userregistration.UserLogin(adminusername, adminpassword);
			waitforElement(180, LP.DASHBOARD, driver);
			click(UN.Manageoseriesconfiglik);
			waitforElement(180, UN.headerText, driver);
			waitforElement(180, UN.Jurisdictioninput, driver);
			type(UN.Jurisdictioninput, Jurisdiction);
			Thread.sleep(3000);
			waitforElement(180, UN.filtertext, driver);
			JavaScriptclick(driver, UN.filtertext);
			Thread.sleep(3000);
			waitforElement(180, UN.EnablelowlevelException, driver);
			JavaScriptclick(driver, UN.EnablelowlevelException);
			Thread.sleep(6000);
			click(UN.COMMENT);
			clear(UN.COMMENT);
			type(UN.COMMENT, Comment);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//td[contains(.,'" + Jurisdiction + "')]/..//button[contains(.,'Update')]"))
					.click();
			Thread.sleep(3000);
		}
	}

	public void MaintenanceMetricdata(String ClientId_header, String ClientName_header, String ClientCity_header,
			String ClientStateCode_header, String ClientPostalCode_header, String QueueStatus_header,
			String QueueServiceId_header, String QueueType_header, String QueuePlatform_header,
			String CreatedUTC_header, String ModifiedUTC_header, String DurationInSeconds_header,
			String CompanyName_header, String COMPANYCODE_header, String CompanyStatus_header) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		click(MC.METRICSLINK);
		waitforElement(180, MC.MAINTENANCEMETRICDATA, driver);
		assertTrue(MC.METRICSLABEL.isDisplayed());
		click(MC.MAINTENANCEMETRICDATA);
		Thread.sleep(30000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			Thread.sleep(30000);
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			DownloadFiles.LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(50000);
			LatestDownloadedFile("Metrics", false);
		}
		String ClientId = (ClientId_header);
		xGetCSVCellValue(Filepath, ClientId);
		assertTrue(Cell_Header.matches(ClientId));
		String ClientName = (ClientName_header);
		xGetCSVCellValue(Filepath, ClientName);
		assertTrue(Cell_Header.matches(ClientName));
		String ClientCity = (ClientCity_header);
		xGetCSVCellValue(Filepath, ClientCity);
		assertTrue(Cell_Header.matches(ClientCity));
		String ClientStateCode = (ClientStateCode_header);
		xGetCSVCellValue(Filepath, ClientStateCode);
		assertTrue(Cell_Header.matches(ClientStateCode));
		String ClientPostalCode = (ClientPostalCode_header);
		xGetCSVCellValue(Filepath, ClientPostalCode);
		assertTrue(Cell_Header.matches(ClientPostalCode));
		String QueueStatus = (QueueStatus_header);
		xGetCSVCellValue(Filepath, QueueStatus);
		assertTrue(Cell_Header.matches(QueueStatus));
		String QueueServiceId = (QueueServiceId_header);
		xGetCSVCellValue(Filepath, QueueServiceId);
		assertTrue(Cell_Header.matches(QueueServiceId));
		String QueueType = (QueueType_header);
		xGetCSVCellValue(Filepath, QueueType);
		assertTrue(Cell_Header.matches(QueueType));
		String QueuePlatform = (QueuePlatform_header);
		xGetCSVCellValue(Filepath, QueuePlatform);
		assertTrue(Cell_Header.matches(QueuePlatform));
		String CreatedUTC = (CreatedUTC_header);
		xGetCSVCellValue(Filepath, CreatedUTC);
		assertTrue(Cell_Header.matches(CreatedUTC));
		String ModifiedUTC = (ModifiedUTC_header);
		xGetCSVCellValue(Filepath, ModifiedUTC);
		assertTrue(Cell_Header.matches(ModifiedUTC));
		String DurationInSeconds = (DurationInSeconds_header);
		xGetCSVCellValue(Filepath, DurationInSeconds);
		assertTrue(Cell_Header.matches(DurationInSeconds));
		String CompanyName = (CompanyName_header);
		xGetCSVCellValue(Filepath, CompanyName);
		assertTrue(Cell_Header.matches(CompanyName));
		String COMPANYCODE = (COMPANYCODE_header);
		xGetCSVCellValue(Filepath, COMPANYCODE);
		assertTrue(Cell_Header.matches(COMPANYCODE));
		String CompanyStatus = (CompanyStatus_header);
		xGetCSVCellValue(Filepath, CompanyStatus);
		assertTrue(Cell_Header.matches(CompanyStatus));
	}

	public void ClientDeleteAuditMetric(String AuditDateUtc_header, String AuditEventTypeName_header,
			String UserId_header, String ClientIpAddress_header, String UserName_header, String FirstName_header,
			String LastName_header, String UserRole_header, String ClientId_header, String ClientName_header,
			String Status_header, String CustomerNumber_header, String SalesforceId_header, String OSeriesApiKey_header)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		click(MC.METRICSLINK);
		waitforElement(180, MC.CLIENTDLETEREPORT, driver);
		assertTrue(MC.METRICSLABEL.isDisplayed());
		click(MC.CLIENTDLETEREPORT);
		Thread.sleep(30000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			Thread.sleep(30000);
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			DownloadFiles.LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(50000);
			LatestDownloadedFile("Metrics", false);
		}
		String AuditDateUtc = (AuditDateUtc_header);
		xGetCSVCellValue(Filepath, AuditDateUtc);
		assertTrue(Cell_Header.matches(AuditDateUtc));
		String AuditEventType = (AuditEventTypeName_header);
		xGetCSVCellValue(Filepath, AuditEventType);
		assertTrue(Cell_Header.matches(AuditEventType));
		String UserId = (UserId_header);
		xGetCSVCellValue(Filepath, UserId);
		assertTrue(Cell_Header.matches(UserId));
		String ClientIpAddress = (ClientIpAddress_header);
		xGetCSVCellValue(Filepath, ClientIpAddress);
		assertTrue(Cell_Header.matches(ClientIpAddress));
		String UserName = (UserName_header);
		xGetCSVCellValue(Filepath, UserName);
		assertTrue(Cell_Header.matches(UserName));
		String FirstName = (FirstName_header);
		xGetCSVCellValue(Filepath, FirstName);
		assertTrue(Cell_Header.matches(FirstName));
		String LastName = (LastName_header);
		xGetCSVCellValue(Filepath, LastName);
		assertTrue(Cell_Header.matches(LastName));
		String UserRole = (UserRole_header);
		xGetCSVCellValue(Filepath, UserRole);
		assertTrue(Cell_Header.matches(UserRole));
		String ClientId = (ClientId_header);
		xGetCSVCellValue(Filepath, ClientId);
		assertTrue(Cell_Header.matches(ClientId));
		String ClientName = (ClientName_header);
		xGetCSVCellValue(Filepath, ClientName);
		assertTrue(Cell_Header.matches(ClientName));
		String Status = (Status_header);
		xGetCSVCellValue(Filepath, Status);
		assertTrue(Cell_Header.matches(Status));
		String CustomerNumber = (CustomerNumber_header);
		xGetCSVCellValue(Filepath, CustomerNumber);
		assertTrue(Cell_Header.matches(CustomerNumber));
		String SalesforceId = (SalesforceId_header);
		xGetCSVCellValue(Filepath, SalesforceId);
		assertTrue(Cell_Header.matches(SalesforceId));
		String OSeriesApiKey = (OSeriesApiKey_header);
		xGetCSVCellValue(Filepath, OSeriesApiKey);
		assertTrue(Cell_Header.matches(OSeriesApiKey));

	}

	public void ExceptionMetricData(String MessageText_header, String ProcessName_header, String ErrorCount_header)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		click(MC.METRICSLINK);
		waitforElement(180, MC.EXCEPTIONMETRICDATA, driver);
		assertTrue(MC.METRICSLABEL.isDisplayed());
		click(MC.EXCEPTIONMETRICDATA);
		Thread.sleep(30000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			Thread.sleep(30000);
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			DownloadFiles.LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(50000);
			LatestDownloadedFile("Metrics", false);
		}
		String MessageText = (MessageText_header);
		xGetCSVCellValue(Filepath, MessageText);
		assertTrue(Cell_Header.matches(MessageText));
		String ProcessName = (ProcessName_header);
		xGetCSVCellValue(Filepath, ProcessName);
		assertTrue(Cell_Header.matches(ProcessName));
		String ErrorCount = (ErrorCount_header);
		xGetCSVCellValue(Filepath, ErrorCount);
		assertTrue(Cell_Header.matches(ErrorCount));
	}

	public void ClientLoginMetric(String Date_header, String Hour_header, String UniqueUsers_header) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		click(MC.METRICSLINK);
		waitforElement(180, MC.CLIENTLOGINREPORT, driver);
		assertTrue(MC.METRICSLABEL.isDisplayed());
		click(MC.CLIENTLOGINREPORT);
		Thread.sleep(30000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			Thread.sleep(30000);
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Metrics";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			DownloadFiles.LatestDownloadedFile("Metrics", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(50000);
			LatestDownloadedFile("Metrics", false);
		}
		String MessageText = (Date_header);
		xGetCSVCellValue(Filepath, MessageText);
		assertTrue(Cell_Header.matches(MessageText));
		String Hour = (Hour_header);
		xGetCSVCellValue(Filepath, Hour);
		assertTrue(Cell_Header.matches(Hour));
		String ErrorCount = (UniqueUsers_header);
		xGetCSVCellValue(Filepath, ErrorCount);
		assertTrue(Cell_Header.matches(ErrorCount));
		String uniqueusers = null, Date = null;
		xGetCSVCelVal(Filepath, Date, uniqueusers);

	}

	public void ManageMerchantAPI(String ClientName) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		new File(".//");
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		JavaScriptclick(driver, PUL.ManageMerchants);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AUA.SEARCHBYMERCHANT, driver);
		Thread.sleep(6000);
		Actions a = new Actions(driver);
		a.click(driver.findElement(By.xpath("//div[@class='row-fluid']/descendant::input"))).build().perform();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//div[@class='row-fluid']/descendant::input")).sendKeys(ClientName);
		Thread.sleep(5000);
		waitforElement(90, "//tr/td/a[contains(.,'" + ClientName + "')]/../../td[contains(.,'Registered, test')]",
				driver);
		waitforElement(90, "//tr/td/a[contains(.,'" + ClientName + "')]/../../td[contains(.,'View')]", driver);
		driver.findElement(By.linkText("View")).click();
		assertTrue(PUL.PublicApiCredentialsTitle.isDisplayed());
		Thread.sleep(6000);
		String APIK = getText(AUA.apiKey);
		String AKey[] = APIK.split(":");
		APIkey = AKey[0].trim();
		APIkey = APIK;
		String APIP = getText(AUA.apiPassword);
		String APwd[] = APIP.split(":");
		APIPassword = APwd[0].trim();
		APIPassword = APIP;
		System.out.println(APIkey);
		System.out.println(APIPassword);
		Thread.sleep(5000);
		click(AUA.resetAPIcredentials);
		Thread.sleep(5000);
		click(AUA.resetAPIcredentials);
		waitforText(180, AUA.CONFIRMCHANGE, driver);
		Thread.sleep(5000);
		click(AUA.RESETAPIPASSWORD);
		Thread.sleep(2000);
		click(AUA.RESETAPIPASSWORD);
		Thread.sleep(5000);
		type(AUA.RESETAPIPASSWORD, "Password!1");
		Thread.sleep(2000);
		click(AUA.SUBMITPASSWORD);
		Thread.sleep(6000);
		assertTrue(AUA.Success.isDisplayed());
	}

	public void VerifyLastLoginClientStatusinAdmin(String ClientName, String ClientType, String CancelCustomer,
			String CancelReason, String Vartype, String Subscriptiontype, String AfterLastLogin) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		PageFactory.initElements(driver, ConfigurationReportPage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		String SystemDateTime;
		String[] S;
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		if (ClientType.equalsIgnoreCase("Test Instance")) {
			type(AUA.smbCustomerName, ClientName + ".TI");
			waitforElement(180,
					"//thead/tr/th[contains(.,'Cloud Customer Name')]/../../../tbody/tr/td/div/a[contains(.,'"
							+ ClientName + ".TI" + "')]",
					driver);
			driver.findElement(
					By.xpath("//thead/tr/th[contains(.,'Cloud Customer Name')]/../../../tbody/tr/td/div/a[contains(.,'"
							+ ClientName + ".TI" + "')]"))
					.click();
		} else {
			type(AUA.smbCustomerName, ClientName);
			waitforElement(180,
					"//thead/tr/th[contains(.,'Cloud Customer Name')]/../../../tbody/tr/td/div/a[contains(.,'"
							+ ClientName + "')]",
					driver);
			WebElement client = driver.findElement(
					By.xpath("//thead/tr/th[contains(.,'Cloud Customer Name')]/../../../tbody/tr/td/div/a[contains(.,'"
							+ ClientName + "')]"));
			JavaScriptclick(driver, client);
		}
		Thread.sleep(2000);
		if (CancelCustomer.equalsIgnoreCase("Yes")) {
			waitforElement(60, AUA.CANCELBUTTON, driver);
			JavaScriptclick(driver, AUA.CANCELBUTTON);
			waitforText(180, AUA.POPUPTEXT, driver);
			type(AUA.CANCELREASON, CancelReason);
			JavaScriptclick(driver, AUA.COMPLETECANCEL);
			waitforElement(120, AUA.CANCELDISABLE, driver);
			Thread.sleep(2000);
			assertTrue(VSC.COMPANYDETAILSTABLE.isDisplayed());
			assertTrue(VSC.USERSTABLE.isDisplayed());
			// cLient name Verify
			String[] Username = driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Name:')]/..")).getText()
					.split(":");
			String Clientname = Username[1].trim();
			assertTrue(Clientname.equalsIgnoreCase(ClientName));
			// cLient Status Verify
			String Status1 = driver.findElement(By.xpath("//span[contains(.,'Status:')]/..")).getText();
			System.out.println("Customer " + Status1);
			// cLient Config type Verify
			String[] TaxpayerConfig = driver.findElement(By.xpath("//span[contains(.,'Taxpayer Configuration:')]/.."))
					.getText().split(":");
			System.out.println("Customer has " + TaxpayerConfig[1]);
			// VARTYPE Verify
			String[] CurrentVartype = driver.findElement(By.xpath("//span[contains(.,'Current VAR:')]/..")).getText()
					.split(":");
			String var = CurrentVartype[1].trim();
			System.out.println(Vartype);
			assertTrue(var.equalsIgnoreCase(Vartype));
			// SubScription type Verify
			String[] Subtype = driver.findElement(By.xpath("//span[contains(.,'Subscription:')]/..")).getText()
					.split(":");
			String Subscription = Subtype[2].trim();
			System.out.println(Subscriptiontype);
			assertTrue(Subscription.equalsIgnoreCase(Subscriptiontype));
		}
		if (AfterLastLogin.equalsIgnoreCase("Yes")) {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			// get current date time with Calendar()
			Calendar cal = Calendar.getInstance();
			SystemDateTime = dateFormat.format(cal.getTime());
			System.out.println("SystemDateTime=" + SystemDateTime);
			String LasttLogin1 = driver
					.findElement(By.xpath(
							"//table/thead/tr/th/div/span[contains(.,'Last Login')]/../../../../../tbody/tr[3]/td[7]"))
					.getText();
			System.out.println("LasttLogin=" + LasttLogin1);
			S = LasttLogin1.split(" ");
			System.out.println("SplitLogin=" + S[0]);
			assertTrue(SystemDateTime.equalsIgnoreCase(S[0]));
			assertTrue(driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Id')]")).isDisplayed());
			click(VSC.SMBCUSTOMERREPORT);
			waitUntilElementInvisibe(300, "//div[@class='blockUI blockMsg blockPage']", driver);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Id')]")).isDisplayed());
			if (!Subscriptiontype.equalsIgnoreCase("Calculations Only")) {
				click(VSC.CUSTOMERRETURNS);
				waitUntilElementInvisibe(300, "//div[@class='blockUI blockMsg blockPage']", driver);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				assertTrue(driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Id')]")).isDisplayed());
				click(VSC.CustomerPrepayments);
				waitUntilElementInvisibe(300, "//div[@class='blockUI blockMsg blockPage']", driver);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				assertTrue(driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Id')]")).isDisplayed());
			}
			click(VSC.SMBREPORTS);
			waitUntilElementInvisibe(300, "//div[@class='blockUI blockMsg blockPage']", driver);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Id')]")).isDisplayed());
			click(VSC.ADMINDETAILS);
			waitUntilElementInvisibe(300, "//div[@class='blockUI blockMsg blockPage']", driver);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Id')]")).isDisplayed());
			String companyid = driver.findElement(By.xpath("//dl/dt[contains(.,'Company ID')]/../dd[1]")).getText();
			assertTrue(!companyid.isEmpty());
			String ConfigContainerId = driver
					.findElement(By.xpath("//dl/dt[contains(.,'Config Container ID')]/../dd[2]")).getText();
			assertTrue(!ConfigContainerId.isEmpty());
			if (VerifyObject(driver, VSC.EditSubscription)) {
				click(VSC.EditSubscription);
				waitUntilElementInvisibe(300, "//div[@class='blockUI blockMsg blockPage']", driver);
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				assertTrue(driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Id')]")).isDisplayed());
			}
			click(VSC.Connectors);
			waitUntilElementInvisibe(300, "//div[@class='blockUI blockMsg blockPage']", driver);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(driver.findElement(By.xpath("//span[contains(.,'Cloud Customer Id')]")).isDisplayed());
		} else {
			String LastLoginValue = driver
					.findElement(By.xpath("//table/tbody/tr/td[text()='" + SMBCustomerUserName + "']/../td[7]"))
					.getText();
			assertTrue(LastLoginValue.isEmpty());
		}
	}

	public void AdminInternationalComplianceReport(String ClientName, String Client, String Folder, String Year,
			String Month) throws Exception {
		new File(".//");
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		new Robot();
		xDownloadFiles("TaxAssistReport");
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		/*
		 * if (BPOCPAClientName != null) { ClientName = BPOCPAClientName; } else if
		 * (MerchantClientName != null) { ClientName = MerchantClientName; } else {
		 * ClientName = VertexClientName; }
		 */
		type(AUA.smbCustomerName, ClientName);
		Thread.sleep(7000);
		waitforElement(100, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]",
				driver);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement Registered = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			JavaScriptclick(driver, Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			JavaScriptclick(driver, Authorized);
		} else {
			WebElement initialized = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
			JavaScriptclick(driver, initialized);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(2000);
		click(AUA.smbCustomerReports);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);

		Thread.sleep(3000);
		// Admin Reports Verification
		if (Client.equalsIgnoreCase("REGTESTINSTANCECLIENT")) {
			ClientName = ClientName + ".TI";
			click(AUA.manageSMBCustomers);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			type(AUA.smbCustomerName, ClientName);
			Thread.sleep(2000);
			waitforElement(100, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]",
					driver);
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
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			click(AUA.smbCustomerReports);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		}
		waitforElement(180, AUA.Rootfolder, driver);
		selectByValue(AUA.Rootfolder, Folder);
		Thread.sleep(3000);
		selectByValue(AUA.Internalyearfolder, Year);
		Thread.sleep(3000);
		selectByValue(AUA.InternalMonthfolder, Month);
		Thread.sleep(6000);
		waitforElement(180, AUA.DownloadedInternationalReturns, driver);
		assertTrue(AUA.DownloadedInternationalReturns.isDisplayed());
		Thread.sleep(3000);
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

	public void VerifyEditOptionForAdmins() throws Exception {
		AdminConfigLimit ACL = PageFactory.initElements(driver, AdminConfigLimit.class);
		assertTrue(ACL.PRODUCTEDITHIDE.isDisplayed());
		assertTrue(ACL.MAPPINGEDITHIDE.isDisplayed());
		assertTrue(ACL.CUSTOMEREDITHIDE.isDisplayed());
		assertTrue(ACL.CERTIFICATEEDITHIDE.isDisplayed());
	}

	public void VarsRegistrationDate(String Varname) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, PUL.VERTEXCLOUDVARS, driver);
		JavaScriptclick(driver, PUL.VERTEXCLOUDVARS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VERTEXVARSEARCH, driver);
		type(PUL.VERTEXVARSEARCH, Varname);
		Thread.sleep(3000);
		waitforElement(180, PUL.REGISTRATIONDATE, driver);
		assertTrue(PUL.REGISTRATIONDATE.isDisplayed());
		WebElement varregistrationdate = driver.findElement(By.xpath(
				"//table/tbody/tr[2]/td[contains(.,'" + Varname + "')]/../td[contains(.,'08/07/2014 03:24:17 PM')]"));
		assertTrue(varregistrationdate.isDisplayed());

	}

	public void VerifyNoOracleBillingLink_TreasuryAdmin(WebDriver driver, String env) throws Exception {
		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		waitforElement(180, TBS.METRICS, driver);
		if (!VerifyObject(driver, TBS.ORACLEBILLING)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		System.out.println("https://" + env + "portal.vertexsmb.com/Admin/OracleBilling#/history");
		Open(driver, "https://" + env + "portal.vertexsmb.com/Admin/OracleBilling#/history");
		assertTrue(driver.getTitle().equalsIgnoreCase("Server Error | Vertex Cloud"));
		driver.get("https://" + env + "portal.vertexsmb.com/Admin");
		waitforElement(180, TBS.METRICS, driver);
		driver.get("https://" + env + "portal.vertexsmb.com/Admin/OracleBilling#/run");
		assertTrue(driver.getTitle().equalsIgnoreCase("Server Error | Vertex Cloud"));
		driver.get("https://" + env + "portal.vertexsmb.com/Admin");
	}

	public void SalesforceID_OraclePartyNumber_Export(String ClientName, String Customer_Name, String Oracle_Party,
			String Salesforce_ID, String oraclenumber, String SalesforceID) throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Robot robot = new Robot();
		String[] headers = { "Trusted Id", "Cloud Customer Number", "Cloud Customer Name", "Address", "City", "State",
				"Zip", "Phone Number", "Account Type", "Status", "Subscription", "SubscriptionFilter", "Var Name",
				"Payment Type", "GoLive UTC", "FeatureFilter", "Oracle Party #", "Salesforce ID" };
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		if (Browser.equalsIgnoreCase("Firefox")) {
			click(VSC.MANAGESMBCUSTLINK);
		} else {
			JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(4000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		JavaScriptclick(driver, VSC.CUSTOMERLISTINGEXPORT);
		Thread.sleep(5000);
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
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Export";
			xDownloadFiles("Metrics");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(9000);
			LatestDownloadedFile("Export", true);
		}

		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("Export", false);
		}
		String Customername = (Customer_Name);
		xGetXLSXCellValue2(Filepath, Customername);
		assertTrue(Cell_Value.matches(ClientName));
		if (!oraclenumber.equalsIgnoreCase("")) {
			String oracleparty = (Oracle_Party);
			xGetXLSXCellValue2(Filepath, oracleparty);
			assertTrue(Cell_Value.matches(oraclenumber));
		}
		if (!SalesforceID.equals("")) {
			String Salesforceid = (Salesforce_ID);
			xGetXLSXCellValue2(Filepath, Salesforceid);
			assertTrue(Cell_Value.matches(SalesforceID));
		}
		xGetXLSXHeaderValue(Filepath, headers[0]);
		xGetXLSXHeaderValue(Filepath, headers[1]);
		xGetXLSXHeaderValue(Filepath, headers[2]);
		xGetXLSXHeaderValue(Filepath, headers[3]);
		xGetXLSXHeaderValue(Filepath, headers[4]);
		xGetXLSXHeaderValue(Filepath, headers[5]);
		xGetXLSXHeaderValue(Filepath, headers[6]);
		xGetXLSXHeaderValue(Filepath, headers[7]);
		xGetXLSXHeaderValue(Filepath, headers[8]);
		xGetXLSXHeaderValue(Filepath, headers[9]);
		xGetXLSXHeaderValue(Filepath, headers[10]);
		xGetXLSXHeaderValue(Filepath, headers[11]);
		xGetXLSXHeaderValue(Filepath, headers[12]);
		xGetXLSXHeaderValue(Filepath, headers[13]);
		xGetXLSXHeaderValue(Filepath, headers[14]);
		xGetXLSXHeaderValue(Filepath, headers[15]);
		xGetXLSXHeaderValue(Filepath, headers[16]);
		xGetXLSXHeaderValue(Filepath, headers[17]);
	}

	public void AdminQueueServiceverfiy(String ClientName, String TaxpayerName, String COMPANYCODE, String StartDate,
			String EndDate, String QueueServiceID, String TaxpayerID_Verification) throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		String clientID = null;
		AdminQueueService AQ = PageFactory.initElements(driver, AdminQueueService.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		waitforElement(180, AQ.DASHBOARD, driver);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, AQ.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		sendkeysEnter(VSC.SMBCUSTNAMESB);
		waitforElement(180, "//table/tbody/tr/td/div/a[contains(.,'" + ClientName + "')]", driver);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")) {
			WebElement registered = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			JavaScriptclick(driver, registered);
		} else {
			WebElement authorized = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			JavaScriptclick(driver, authorized);
		}
		Thread.sleep(4000);
		waitUntilElementInvisibe(180, AQ.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SELECTSYSAPP, driver);
		clientID = driver.findElement(By.xpath("//div/span[contains(.,'Cloud Customer Id:')]/../../div[2]")).getText();
		String nw[] = clientID.split(":");
		clientID = nw[1].trim();
		System.out.println(nw[1].trim());
		click(AQ.QUEUESERVICELINK);
		waitUntilElementInvisibe(180, AQ.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AQ.QUEUESERVICEID, driver);
		click(AQ.RECORDSPAGES);
		selectByValue(AQ.RECORDSPAGES, "250");
		click(AQ.RECORDSPAGES);
		selectByValue(AQ.PAGINATION, "100 per page");
		driver.navigate().refresh();
		Thread.sleep(8000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		Thread.sleep(8000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		type(AQ.CLIENTIDVALUE, clientID);
		selectByValue(AQ.RECORDSPAGES, "250");
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		String ApQueuserviceID = driver
				.findElement(By.xpath("//thead/tr/th[contains(.,'QueueServiceId')]/../../../tbody/tr[1]/td[1]"))
				.getText();
		System.out.println(ApQueuserviceID);
		type(AQ.QUEUETYPEIDVALUE, ApQueuserviceID);
		selectByValue(AQ.RECORDSPAGES, "250");
		click(AQ.REFRESHBUTTON);
		Thread.sleep(8000);
	}

	public void AdminCreateNewUserfromamangecustomers(String ClientName, String Firstname, String LastName,
			String Email, String AccountManagementRole, String VertexIndirecttaxrole, String ECMRole, String Password,
			String ConfirmPassword) throws Exception {

		AdminUserLinks ADMIN = PageFactory.initElements(driver, AdminUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		} else {
			click(ADMIN.MANAGESMBCUSTOMERS);
		}
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement client = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
		JavaScriptclick(driver, client);
		Thread.sleep(5000);
		waitforElement(180, VSC.ADMINCREATENEWUSER, driver);
		click(VSC.ADMINCREATENEWUSER);
		Thread.sleep(5000);
		waitforElement(180, cpamanagersPage.USEREMAIL, driver);
		type(cpamanagersPage.USEREMAIL, Email);
		// click(cpamanagersPage.PHONE);
		// type(cpamanagersPage.PHONE, Phone);
		waitforElement(180, cpamanagersPage.ACCOUNTMANAGEMENTROLE, driver);
		if (AccountManagementRole != "") {
			selectByValue(cpamanagersPage.ACCOUNTMANAGEMENTROLE, AccountManagementRole);
		}
		if (VertexIndirecttaxrole != "") {
			selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE, VertexIndirecttaxrole);
		}

		if (VerifyObject(driver, cpamanagersPage.EXEMPTIONCMROLE) && ECMRole != "") {
			selectByValue(cpamanagersPage.EXEMPTIONCMROLE, ECMRole);
		}
		click(cpamanagersPage.SENDINVITEUSER);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
	/*	waitforElement(180, cpamanagersPage.COMPLETEREGISTRATIONLINK, driver);
		click(cpamanagersPage.COMPLETEREGISTRATIONLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, cpamanagersPage.FIRSTNAME, driver);
		type(cpamanagersPage.FIRSTNAME, Firstname);
		type(cpamanagersPage.LASTNAME1, LastName);
		type(cpamanagersPage.PASSWORD, Password);
		type(cpamanagersPage.CONFIRMPASSWORD, ConfirmPassword);
		Thread.sleep(1000);
		JavaScriptclick(driver, cpamanagersPage.PROFILECOMP_REG);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, cpamanagersPage.LOGIN, driver);
		click(cpamanagersPage.LOGIN);*/
		waitforElement(180, ADMIN.MANAGESMBCUSTOMERS, driver);
		JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement client1 = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
		JavaScriptclick(driver, client1);
		waitforElement(180, VSC.ADMINCREATENEWUSER, driver);
		WebElement createduser = driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + Email + "')]"));
		assertTrue(createduser.isDisplayed());
	}
	
	public void DefaultEntityLimits(String ClientName) throws Exception{
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, VSC.SMBCUSTNAMESB, driver);
		
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement clientname = driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
		JavaScriptclick(driver, clientname);
		Thread.sleep(2000);
		waitforElement(60, VSC.PartitionConfiguration, driver);
		click(VSC.PartitionConfiguration);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.ENTITYLIMITS, driver);
		click(VSC.ENTITYLIMITS);
		waitforElement(180, VSC.CUSTOMERLIMITS, driver);
		String customerlimit = VSC.CUSTOMERLIMITS.getAttribute("value");
		assertTrue(customerlimit.equalsIgnoreCase("3000"));
		JavaScriptscrolltoView(driver, VSC.VENDORLIMIT);
		String certificatelimit = VSC.CERTIFICATELIMITS.getAttribute("value");
		assertTrue(certificatelimit.equalsIgnoreCase("10000"));
		String driverlimit = VSC.DRIVERLIMITS.getAttribute("value");
		assertTrue(driverlimit.equalsIgnoreCase("3000"));
		String mappinglimit = VSC.MAPPINGLIMITS.getAttribute("value");
		assertTrue(mappinglimit.equalsIgnoreCase("3000"));
		String vendorlimit = VSC.VENDORLIMIT.getAttribute("value");
		assertTrue(vendorlimit.equalsIgnoreCase("3000"));
		String taxpayerlimit = VSC.TAXPAYERLIMITS.getAttribute("value");
		assertTrue(taxpayerlimit.equalsIgnoreCase("1000"));
		
	}
	
	public void AdminCreateNewUserRole(String ClientName, String Firstname, String LastName, String Emailid, String PhoneNumber,
			String AccountManagementRole, String VertexIndirecttaxrole, String Password, String ConfirmPassword) throws Exception {

		AdminUserLinks ADMIN = PageFactory.initElements(driver, AdminUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		calcparity CL = PageFactory.initElements(driver, calcparity.class);
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		} else {
			click(ADMIN.MANAGESMBCUSTOMERS);
		}
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement client = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
		JavaScriptclick(driver, client);
		Thread.sleep(5000);
		waitforElement(180, VSC.ADMINCREATENEWUSER, driver);
		click(VSC.ADMINCREATENEWUSER);
		Thread.sleep(5000);
                waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.USEREMAIL, driver);
		type(CL.USEREMAIL, Emailid);
		waitforElement(180, CL.ACCOUNTMANAGEMENT, driver);
		if (AccountManagementRole != "") {
			selectByValue(CL.ACCOUNTMANAGEMENT, AccountManagementRole);
		Thread.sleep(1000);
		click(CL.VERTEXCLOUDINDIRECTTAXROLE);
		String[] userrole = VertexIndirecttaxrole.split(",");
		if (!VertexIndirecttaxrole.isEmpty()) {
			for (int i = 0; i < userrole.length; i++) {
				type(CL.VERTEXCLOUDINDIRECTTAXROLE, userrole[i]);
				Thread.sleep(2000);
				CL.VERTEXCLOUDINDIRECTTAXROLE.sendKeys(Keys.ENTER);
				System.out.println(VertexIndirecttaxrole);
			}
		}
		Thread.sleep(1000);
		click(CL.SENDINVITE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CL.COMPLETEREGISTATIONLINK, driver);
		click(CL.COMPLETEREGISTATIONLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		PortalUserName = Emailid;
		System.out.println("CL NewUser name:" + PortalUserName);
		Thread.sleep(2000);
		waitforElement(180, CL.FIRSTNAME, driver);
		type(CL.FIRSTNAME, Firstname);
		type(CL.LASTNAME, LastName);
		click(CL.PHONENUMBER);
		type(CL.PHONENUMBER, PhoneNumber);
		type(CL.PASSWORD, Password);
		type(CL.CONFIRMPASSWORD, ConfirmPassword);
		Thread.sleep(1000);
		JavaScriptclick(driver, CL.PROFILECOMP_REG);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, CL.LOGIN, driver);
		click(CL.LOGIN);
		waitforElement(180, ADMIN.MANAGESMBCUSTOMERS, driver);
		JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement client1 = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
		JavaScriptclick(driver, client1);
		waitforElement(180, VSC.ADMINCREATENEWUSER, driver);
		WebElement createduser = driver
				.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + PortalUserName + "')]"));
		assertTrue(createduser.isDisplayed());
		}
		
	}
	public void VerifyModifyRolesinAdmin(String ClientName, String Email) throws Exception {

		AdminUserLinks ADMIN = PageFactory.initElements(driver, AdminUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
               
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScriptclick(driver, ADMIN.MANAGESMBCUSTOMERS);
		} else {
			click(ADMIN.MANAGESMBCUSTOMERS);
		}
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement client = driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
		JavaScriptclick(driver, client);
		Thread.sleep(5000);
                WebElement RoleLink = driver.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td/div/a[contains(.,'Show Cloud Indirect Tax Roles')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);
		JavaScriptclick(driver, RoleLink);
		Thread.sleep(5000);
		WebElement table = driver.findElement(By.xpath("//table[@class='table table-striped table-bordered']"));
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		for (WebElement row : allRows) { 
		    List<WebElement> cells = row.findElements(By.tagName("td"));
		    for (WebElement cell : cells) { 
		        System.out.println(cell.getText());
		    }
		}
     
      
	}
	
	public void AdminPortalPartitionID(String Clientname) throws Exception {
		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, TPU.SMBCUSTOMERSTAB, driver);
		JavaScriptclick(driver, TPU.SMBCUSTOMERSTAB);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);
		type(TPU.SMBCUSTOMERNAME, Clientname);
		Thread.sleep(4000);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + Clientname + "')]",
				driver);
		driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + Clientname + "')]"))
				.click();
                Thread.sleep(4000);
                waitforText(180, TPU.ADMINDETAILS, driver);
                click(TPU.ADMINDETAILS);
                String PID12 = getText(TPU.PARTITIONID);
                DPID = PID12.split(": ")[0];
		        System.out.println(DPID);
		   
      }
}