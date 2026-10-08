/*###################################################################################################
Name: Reports.java 
Description: Having all types of reports related methods
Author: Venkat Reddy Kandula
Date Created:02/06/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.libraries.customer;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.pages.AdminUndoAdjustmentFile;
import common.pages.ConfigurationReportPage;
import common.pages.ConfigurePage;
import common.pages.CustomerExceptionReport;
import common.pages.FilingCalendarReportPage;
import common.pages.GoLiveVerify;
import common.pages.LoginPage;
import common.pages.ManagetaxReturns;
import common.pages.Metrics;
import common.pages.ProductsPage;
import common.pages.ProfessionalUserLinks;
import common.pages.RegistrationPage;
import common.pages.TaxAssistRuleReportPage;
import common.pages.TaxabilityImpactPage;
import common.pages.TransactionReportPage;
import common.pages.UploadAdjustmentFile;
import common.pages.VertexSMBCustomers;
import common.utilities.CloudData;

public class Reports extends CommonFunctions {

	private WebDriver driver;

	public Reports(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void DownloadTaxabilityReports(String downloadlink, String CopyRight_Section, String worksheetNames,
			String sheetName, String stateName, String categoryType) throws Exception {

		Robot rob = new Robot();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		int count = 0;
		int TaxLength, j;
		WebElement TaxabilityReport, TaxReportLink;
		File directory = new File(".//");
		xDownloadFiles("TaxabilityReports");
		String path, Column_Value, strvertexinc, sheetvalue, statevalue, categoryvalue;
		waitforText(180, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TAXABILITYREPORTSLINK, driver);
		JavaScriptclick(driver, PUL.TAXABILITYREPORTSLINK);
		waitforText(180, PUL.TAXABILITYCOMPDD, driver);
		selectByValue(PUL.TAXABILITYCOMPDD, downloadlink);
		String Date = GetCurrentMonthYear();
		waitforElement(60, "//tbody/tr/td[contains(.,'" + Date + "')]/../../../tbody/tr/td/a[contains(.,'Report')]",
				driver);
		if (VerifyObject(driver, "//div/p/strong[text()='no taxability reports']")) {
			boolean report = false;
			do {
				Thread.sleep(30000); // wait for O Series sync to get taxability download link
				count = count + 1;
				if (count == 15) {
					break;
				}
				if (!VerifyObject(driver, "//div/p/strong[text()='no taxability reports']")) {
					report = true;
				}
			} while (report == false);
		}

		TaxabilityReport = driver
				.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/a[contains(.,'Report')]"));
		if (VerifyObject(driver, TaxabilityReport)) {
			click(TaxabilityReport);
			waitforElement(180, PUL.TAXABILITYREPORTMINUS, driver);
		}
		List<WebElement> TaxabilityReportsLength = driver
				.findElements(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul/li/a"));
		TaxLength = TaxabilityReportsLength.size();
		if (TaxLength == 1) {
			TaxReportLink = driver.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[1]/li/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", TaxReportLink);
			TaxabilityDownloadlink = driver
					.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[1]/li/a")).getText();
		} else {
			TaxReportLink = driver.findElement(
					By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[" + TaxLength + "]/li/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", TaxReportLink);
			TaxabilityDownloadlink = driver
					.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[" + TaxLength + "]/li/a"))
					.getText();
		}
		Thread.sleep(7000); // wait to download the file to the file system folder

		// For keyboard event required wait time to enter and tab
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(10000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}

		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "TaxabilityReports";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000); // wait to move the download file from downloads folder to our project
			// Actual folder
			LatestDownloadedFile("TaxabilityReports", true);
		}

		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("TaxabilityReports", false);
		}
		FileInputStream fis = new FileInputStream(Filepath);
		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
		int Num_Sheets = workbook.getNumberOfSheets();
		for (j = 0; j < Num_Sheets; j++) {
			String sheetname = workbook.getSheetName(j);
			String[] SheetNames = worksheetNames.split(";");
			assertTrue(sheetname.equalsIgnoreCase(SheetNames[j]));
		}
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
		int Num_rows = sheet.getLastRowNum() - 3;
		org.apache.poi.ss.usermodel.Row Row = sheet.getRow(Num_rows);
		Cell atColumn = Row.getCell(0);
		Column_Value = atColumn.getStringCellValue();
		strvertexinc = Column_Value.split("\\.")[0];
		boolean hasState = strvertexinc.contains(CopyRight_Section);
		assertTrue(hasState);
		fis.close();
		sheetvalue = sheetName;
		statevalue = stateName;
		categoryvalue = categoryType;
		xGetCustomizedXLSXCellValue(Filepath, sheetvalue, statevalue, categoryvalue);
	}

	public void COBranchNumbersinConfigReport(String Companyname, String Report_path) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, CR.Reports_Overview, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CR.ConfigurationLink);
		Thread.sleep(8000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		selectByValue(CR.TAXDROPDOWN, Companyname);
		Thread.sleep(8000);
		// downloading Export Button
		JavaScriptclick(driver, CR.EXPORTBUTON);
		Thread.sleep(8000);
		// Comparing Downloaded File
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
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
		// File directory = new File(".\\");
		// String DirName = directory.getCanonicalPath() + "\\" +
		// "resources\\testdata\\expected" + "\\" + "Reports";
		// XLSXtoXLSXFilesCompare(Filepath,DirName+"\\"+Report_path);
		String s = xGetXLSXCellValue1(Filepath, "Branch ID", "Reporting Locations");
		assertTrue(!s.isEmpty());
	}

	public void COBranchNumbersinAdminConfigReport(String Companyname, String ClientName, String Report_path)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]", driver);
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
		Thread.sleep(5000);
		JavaScriptclick(driver, VSC.SMBCUSTOMERREPORT);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(8000);
		selectByValue(CR.TAXDROPDOWN, Companyname);
		Thread.sleep(8000);	
		// downloading Export Button
		JavaScriptclick(driver, CR.EXPORTBUTON);
		Thread.sleep(8000);
		// Comparing Downloaded File
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
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
		File directory = new File(".\\");
		String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
		XLSXtoXLSXFilesCompare(Filepath,DirName+"\\"+Report_path);
		//xr_xCompareExcelFiles(Filepath, DirName + "\\" + Report_path);
	}

	public void InternationalTransactionReport(String Taxpayer, String Country, String Start_Date, String End_Date,
			String Successful_Message) throws Exception {

		new Robot();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		Thread.sleep(5000);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		// waitforText(90,CR.Reports_Overview_Text, driver);
		waitforText(90, TR.InternationalTransactionText, driver);
		JavaScriptclick(driver, TR.InternationalTransactionLink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		waitforElement(180, TR.TAXPAYERSELECT, driver);
		selectByValue(TR.TAXPAYERSELECT, Taxpayer);
		waitforElement(180, TR.CREATEBUTTON, driver);
		click(TR.CREATEBUTTON);
		waitforElement(180, TR.SELECTDD, driver);
		selectByValue(TR.SELECTDD, Taxpayer);
		Thread.sleep(5000);
		selectByValue(TR.COUNTRYDD, Country);
		if (!VerifyObject(driver, TR.SUBMITBTNDISABLE)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		assertTrue(TR.CANCELBUTTON.isDisplayed());
		type(TR.STARTDATE, Start_Date);
		sendkeysTab(TR.STARTDATE);
		type(TR.ENDDATE, End_Date);
		sendkeysTab(TR.ENDDATE);
		click(TR.TRANSACTIONDETAILS);
		JavaScriptclick(driver, TR.SUBMITBUTTON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TR.TAXPAYERSELECT, driver);
		if (Successful_Message.equalsIgnoreCase(driver.findElement(By.xpath(TR.SUCCESSFULMESSAGE_XPATH)).getText())) {
			TransactionSuccess = getText(TR.SUCCESSFULMESSAGE);
		}

	}

	public void VerifyInternationalTransactionReport(String Taxpayer, String Start_Date, String End_Date,
			String File_Name) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Robot rob = new Robot();
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String DownloadFilepath = xDownloadFiles("TransactionReport\\Oseries");
		int count = 0;
		Thread.sleep(5000);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, CR.Reports_Overview, driver);
		waitforText(90, TR.InternationalTransactionText, driver);
		JavaScriptclick(driver, TR.InternationalTransactionLink);
		Thread.sleep(2000);
		waitforElement(180, TR.TAXPAYERSELECT, driver);
		selectByValue(TR.TAXPAYERSELECT, Taxpayer);
		waitforElement(180, TR.CREATEBUTTON, driver);
		waitforElement(180, "//tr/td[text()='" + Start_Date + "']/../td[text()='" + End_Date + "']/../td/a", driver);
		String downloadLink = "//tr/td[text()='" + Start_Date + "']/../td[text()='" + End_Date + "']/../td/a";
		if (!VerifyObject(driver, downloadLink)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TR.TAXPAYERSELECT, driver);
				selectByValue(TR.TAXPAYERSELECT, Taxpayer);
				count = count + 1;
				if (VerifyObject(driver, downloadLink)) {
					report = true;
				}
				Thread.sleep(5000);
				JavaScriptclick(driver, PUL.ReportsHover);
				waitforText(90, TR.InternationalTransactionText, driver);
				JavaScriptclick(driver, TR.InternationalTransactionLink);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TR.TAXPAYERSELECT, driver);
				selectByValue(TR.TAXPAYERSELECT, Taxpayer);
			} while (report == false);
		}
		TransactionFile = Start_Date + "--" + End_Date;
		driver.findElement(By.xpath(downloadLink)).click();
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
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
					+ "TransactionReport\\Reports";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			tempfile = new File(LatestDownloadedFile("TransactionReport\\Reports", true));
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(8000);
			tempfile = new File(LatestDownloadedFile("TransactionReport\\Reports", false));
		}
		Thread.sleep(5000);

		if (File_Name != "") {
			File directory = new File(".//");
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
			// String[] name =FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")
			String name = File_Name;
			// String Suc_File=DirName+"\\"+name+".csv";
			tempfile2 = new File(DirName + "\\" + name + ".csv");
			Thread.sleep(5000);
			Comparecsvfiles(tempfile, tempfile2);
		}

	}

	@SuppressWarnings("null")
	public void TransactionReport(String Taxpayer, String Start_Date, String End_Date, String State,
			String Transaction_Details_Summary, String Successful_Message) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		UserRegistration userrgistration = null;
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		// Wait 15 minutes for transaction data when HVR on
		// Thread.sleep(900000);
		driver.navigate().refresh();
		if (VerifyObject(driver, LP.USERNAME)) {
			userrgistration.UserLogin(VertexUserName, "Password!1");
			waitforElement(360, LP.VTMSACCOUNT, driver);
		}
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		waitforElement(180, TR.TransactionLink, driver);
		JavaScriptclick(driver, TR.TransactionLink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		waitforElement(180, TR.TAXPAYERSELECT, driver);
		selectByValue(TR.TAXPAYERSELECT, Taxpayer);
		waitforElement(180, TR.CREATEBUTTON, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, TR.CREATEBUTTON);
		} else {
			click(TR.CREATEBUTTON);
		}
		Thread.sleep(5000);
		waitforElement(180, TR.CANCELBUTTON, driver);
		waitforElement(180, TR.SELECTDD, driver);
		selectByValue(TR.SELECTDD, Taxpayer);
		// verifying Label text with State/Territory
		assertTrue(TR.STATE.isDisplayed());
		assertFalse(VerifyObject(driver, TR.SUBMITBTNDISABLE));
		assertTrue(TR.CANCELBUTTON.isDisplayed());
		type(TR.STARTDATE, Start_Date);
		sendkeysTab(TR.STARTDATE);
		type(TR.ENDDATE, End_Date);
		sendkeysTab(TR.ENDDATE);
		Thread.sleep(2000);
		if (State != "") {
			selectByValue(TR.STATESELECTIONID, State);
			Thread.sleep(2000);
		}
		if (Transaction_Details_Summary.equalsIgnoreCase("TransactionDetails")) {
			click(TR.TRANSACTIONDETAILS);
		} else if ((Transaction_Details_Summary.equalsIgnoreCase("TransactionDetailsAbbreviated"))) {
			click(TR.TRANSACTIONABBREVIATEDREPORT);
		}
		// Jurisdiction Summary as per 6.5 Changes
		else if ((Transaction_Details_Summary.equalsIgnoreCase("Jurisdiction Summary"))) {
			click(TR.JURISDICTIONSUMMARY);
		}
		// Document Summary Jurisdiction as per 6.5 Changes
		else if ((Transaction_Details_Summary.equalsIgnoreCase("Document Summary by Jurisdiction"))) {
			click(TR.DOCUMENTSUMMARYBYJURISDICTION);
		} else {
			click(TR.TRANSACTIONSUMMARY);
		}
		JavaScriptclick(driver, TR.SUBMITBUTTON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TR.TAXPAYERSELECT, driver);
		if (Successful_Message.equalsIgnoreCase(driver.findElement(By.xpath(TR.SUCCESSFULMESSAGE_XPATH)).getText())) {
			TransactionSuccess = getText(TR.SUCCESSFULMESSAGE);
		}

	}

	public void ComplianceFileReport(String InternationalCompliance, String TaxpayerCode, String Fillingperiod,
			String Startdate, String EndDate) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		// Thread.sleep(900000);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		// waitforText(90,CR.Reports_Overview_Text, driver);
		Thread.sleep(5000);
		if (InternationalCompliance.equals("Yes")) {
			click(PUL.ComplianceInternationalLink);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, TR.CREATENEWCOMPLIANCEFILE, driver);
			Thread.sleep(5000);
			// if(ComplianceFileReport.getCell(BeforeReturns,
			// Data_Row).getContents().equalsIgnoreCase("Yes")){
			assertTrue(TR.Tablecolumns.isDisplayed());
			assertTrue(TR.BeforeRegistrations.isDisplayed());
		} else {
			click(PUL.ComplianceUSCanadaLink);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, TR.CREATENEWCOMPLIANCEFILE, driver);
			waitforElement(180, TR.TAXPAYERSELECT, driver);
			selectByValue(TR.TAXPAYERSELECT, TaxpayerCode);
		}
		waitforElement(180, TR.CREATENEWCOMPLIANCEFILE, driver);
		click(TR.CREATENEWCOMPLIANCEFILE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TR.CANCELBUTTON, driver);
		waitforElement(180, TR.COMPANYLISTDD, driver);
		selectByValue(TR.COMPANYLISTDD, TaxpayerCode);
		Thread.sleep(5000);
		if (Fillingperiod != "") {
			waitforElement(30, TR.FILINGPERIOD, driver);
			selectByValue(TR.FILINGPERIOD, Fillingperiod);
		} else {
			click(TR.ExportDataPostedFrom);
			Thread.sleep(2000);
			type(TR.StartDate, Startdate);
			Thread.sleep(1000);
			type(TR.EndDate, EndDate);
		}

		if (VerifyObject(driver, TR.GenerateFileDisabled)) {
			assertTrue(TR.DaterangeValidationMsg.isDisplayed());
		} else {

			click(TR.COMPLIANCESUBMITBUTTON);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(60, TR.SUCCESSFULMESSAGE_XPATH, driver);
			// assertTrue(TR.SUCCESSFULMESSAGE_XPATH.isEmpty());
			assertTrue(driver.findElement(By.xpath(TR.SUCCESSFULMESSAGE_XPATH)).isDisplayed());
		}
	}

	public void VerifyTransactionReport(String Taxpayer, String Start_Date, String End_Date, String Report_type,
			String Verify_Download_Status, String Columns_Verify, String TransactionProcessDate, String CompanyCode,
			String File_Name) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Robot rob = new Robot();
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String DownloadFilepath = xDownloadFiles("TransactionReport\\Oseries");
		int count = 0;
		Thread.sleep(5000);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, CR.Reports_Overview, driver);
		// waitforText(90,CR.Reports_Overview_Text, driver);
		JavaScriptclick(driver, TR.TransactionLink);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		waitforElement(180, TR.TAXPAYERSELECT, driver);
		selectByValue(TR.TAXPAYERSELECT, Taxpayer);
		waitforElement(180, TR.CREATEBUTTON, driver);
		waitforElement(180, "//tr/td[text()='" + Start_Date + "']/../td[text()='" + End_Date + "']/../td[contains(.,'"
				+ Report_type + "')]/../td/a", driver);
		String downloadLink = "//tr/td[text()='" + Start_Date + "']/../td[text()='" + End_Date + "']/../td[contains(.,'"
				+ Report_type + "')]/../td/a";
		if (!VerifyObject(driver, downloadLink)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TR.TAXPAYERSELECT, driver);
				selectByValue(TR.TAXPAYERSELECT, Taxpayer);
				count = count + 1;
				if (count == 60) {
					break;
				}
				if (VerifyObject(driver, downloadLink)) {
					report = true;
				}
				Thread.sleep(5000);
				JavaScriptclick(driver, PUL.ReportsHover);
				waitforElement(90, PUL.TRANSACTIONREPORTLINK, driver);
				JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TR.TAXPAYERSELECT, driver);
				selectByValue(TR.TAXPAYERSELECT, Taxpayer);
			} while (report == false);
		}
		assertTrue(TR.COLUMN1.isDisplayed());
		assertTrue(TR.COLUMN2.isDisplayed());
		assertTrue(TR.COLUMN3.isDisplayed());
		assertTrue(TR.COLUMN4.isDisplayed());
		assertTrue(TR.COLUMN5.isDisplayed());
		TransactionFile = Start_Date + "--" + End_Date;
		driver.findElement(By.xpath(downloadLink)).click();
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
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
					+ "TransactionReport\\Oseries";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			tempfile = new File(LatestDownloadedFile("TransactionReport\\Oseries", true));
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(8000);
			tempfile = new File(LatestDownloadedFile("TransactionReport\\Oseries", false));
		}
		Thread.sleep(5000);
		if (Verify_Download_Status.equalsIgnoreCase("Yes")) {

			String[] feature = Columns_Verify.split(",");
			if (!Columns_Verify.contains("")) {
				for (int i = 0; i < feature.length; i++) {

					Thread.sleep(5000);

					xGetCSVCellValue(Filepath, feature[i]);
					assertTrue(Cell_Header.equalsIgnoreCase(feature[i]));
					if (Columns_Verify != "") {
						assertTrue(Cell_Value.equalsIgnoreCase(feature[i]));
					}

					if (feature[i].equalsIgnoreCase("Shipping Terms")) {
						assertTrue(Cell_Value.equals(""));
					}
				}
			}

			xGetCSVCellValue(Filepath, "Comment");
			if (TransactionProcessDate.equalsIgnoreCase("Yes")) {
				xGetCSVCellValue(Filepath, "Transaction Process Date");
				String TransProcessDate = Cell_Value;
				System.out.println("TransactionProcessDate=" + TransProcessDate);
				assertTrue(
						TransProcessDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4}) ([0-9]){1}:([0-9]){2} ([A-Z]){2}"));
			}
			if (CompanyCode.equalsIgnoreCase("Yes")) {
				xGetCSVCellValue(Filepath, "Taxpayer Company Code");
				String TaxpayerCompanyCode = Cell_Value;
				System.out.println("TaxpayerCompanyCode=" + TaxpayerCompanyCode);
				assertTrue(TaxpayerCompanyCode.contains(","));
			}
			if (File_Name != "") {
				File directory = new File(".//");
				String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "Reports";
				// String[] name =FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")
				String name = File_Name;
				// String Suc_File=DirName+"\\"+name+".csv";
				tempfile2 = new File(DirName + "\\" + name + ".csv");
				Thread.sleep(5000);
				Comparecsvfiles(tempfile, tempfile2);
			}

		}
	}

	public void VerifyComplianceFileReport(String InternationalCompliance, String TaxpayerCode, String Taxpayer)
			throws Exception {
		Robot rob = new Robot();
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		xDownloadFiles("ComplianceFileReport");
		int count = 0;
		File directory = new File(".//");
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		// waitforText(90,CR.Reports_Overview_Text, driver);

		if (InternationalCompliance.equals("Yes")) {
			click(PUL.ComplianceInternationalLink);
		} else {
			click(PUL.ComplianceUSCanadaLink);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TR.CREATENEWCOMPLIANCEFILE, driver);
		waitforElement(180, TR.TAXPAYERSELECT, driver);
		// selectByValue(TR.TAXPAYERSELECT, TaxpayerCode);
		Thread.sleep(3000);
		String downloadLink = "//tr[2]/td[contains(.,'" + Taxpayer + "')]/../td/a";
		// String Reportgen="//tbody/tr/td/span/i[text()='Report generation is in
		// progress ...']";
		if (!VerifyObject(driver, downloadLink)) {
			boolean report = false;
			do {
				Thread.sleep(30000);
				count = count + 1;
				if (count == 80) {
					assertFalse(false);
					break;
				}
				if (VerifyObject(driver, downloadLink)) {
					report = true;
				}
				JavaScriptclick(driver, PUL.ReportsHover);
				waitforElement(180, CR.Reports_Overview, driver);
				if (InternationalCompliance.equals("Yes")) {
					click(PUL.ComplianceInternationalLink);
				} else {
					click(PUL.ComplianceUSCanadaLink);
				}
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TR.CREATENEWCOMPLIANCEFILE, driver);
				selectByValue(TR.TAXPAYERSELECT, TaxpayerCode);
				waitforElement(180, TR.TAXPAYERSELECT, driver);
			} while (report == false);
		}
		assertTrue(TR.GENERATIONDATE.isDisplayed());
		assertTrue(TR.COMPANYNAME.isDisplayed());
		assertTrue(TR.FILINGPERIODCOLUMN.isDisplayed());
		if (TR.ACTIONCOLUMN.isDisplayed()) {
			assertTrue(TR.ACTIONCOLUMN.isDisplayed());
		}
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
		} else {
			driver.findElement(By.xpath(downloadLink)).click();
		}
		if (Browser.equalsIgnoreCase("Chrome")) {
			Thread.sleep(5000);
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
					+ "ComplianceFileReport";
			System.out.println("Path" + path);
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			new File(LatestDownloadedFile("ComplianceFileReport", true));
		}
		if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(8000);
			new File(LatestDownloadedFile("ComplianceFileReport", false));
		}
		Thread.sleep(5000);

	}

	public void SummaryChangesReport(String Subscription, String Verify_PDF, String File_Name) throws Exception {
		String CompareFile;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		File tempfile = null;
		File tempfile2 = null;
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		if (!Subscription.equalsIgnoreCase("Standard")) {
			JavaScriptclick(driver, PUL.ReportsHover);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CR.SUMMARYOFCHANGESREPORT, driver);
			assertTrue(CR.SUMMARYCHANGESHEADING.isDisplayed());
			assertTrue(CR.SUMMARYCHANGESTEXT.isDisplayed());
			click(CR.SUMMARYOFCHANGESREPORT);
			Thread.sleep(20000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Reports";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				tempfile = new File(LatestDownloadedFile("Reports", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(5000);
				tempfile = new File(LatestDownloadedFile("Reports", false));
			}
			if (Verify_PDF.equalsIgnoreCase("YES")) {
				CompareFile = File_Name;
				File directory = new File(".\\");
				String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "Reports";
				tempfile2 = new File(DirName + "\\" + CompareFile + ".pdf");
				String File1 = tempfile.toString();
				String File2 = tempfile2.toString();
				PDFComparison(File1, File2);
			}
		} else {
			if (!VerifyObject(driver, PUL.ReportsHover)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}

	}

	public void AdminTransactionReport(String ClientName, String folderType, String Report, String CompanyName,
			String StartDate, String EndDate, String Transaction_Details_Summary, String DownloadReports, String Year,
			String Month, String CompareRTE_BracketSchedule) throws Exception {
		try {
			File directory = new File(".//");
			AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
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
				WebElement registered = driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
				JavaScriptclick(driver, registered);
			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				WebElement authorized = driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
				JavaScriptclick(driver, authorized);
			} else {
				WebElement initialized = driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
				JavaScriptclick(driver, initialized);
			}
			// }
			Thread.sleep(3000);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			click(AUA.smbCustomerReports);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			if (folderType != "") {
				selectByValue(AUA.REPORT, Report);
				if (CompanyName != "") {
					selectByValue(AUA.companyName, CompanyName);
				}
				if (StartDate != "") {
					type(AUA.StartDate, StartDate);
				}
				if (EndDate != "") {
					type(AUA.Enddate, EndDate);
				}
				if (Transaction_Details_Summary.equalsIgnoreCase("TransactionDetails")) {
					click(AUA.AdminTransactionDetails);
				} else if (Transaction_Details_Summary.equalsIgnoreCase("TransactionSummary")) {
					click(AUA.AdminTransactionSummary);
				} else {
					if (Report.equalsIgnoreCase("International Transaction Report")) {
						// click(AUA.AdminIntTransactionDetails);
						Thread.sleep(3000);
					}
				}
				Thread.sleep(3000);
				click(AUA.GENERATEREPORT);
				waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
				driver.navigate().refresh();
				waitforElement(180, AUA.REPORT, driver);
				int count;

				if (folderType != "") {
					if (folderType.equalsIgnoreCase("IntlOseriesReconCSVAdmin")) {
						boolean report = false;
						count = 0;
						do {
							driver.navigate().refresh();
							Thread.sleep(10000);
							count = count + 1;
							if (count == 25) {
								assertTrue(false);
								break;
							}
							if (VerifyObject(driver,
									"//select[@id='rootFolder']/option[text()='IntlOseriesReconCSVAdmin']")) {
								report = true;
							}
						} while (report == false);
					} else {
						if (folderType.equalsIgnoreCase("OSeriesReconCsvAdmin")) {
							boolean report = false;
							count = 0;
							do {
								driver.navigate().refresh();
								Thread.sleep(10000);
								count = count + 1;
								if (count == 25) {
									assertTrue(false);
									break;
								}
								if (VerifyObject(driver,
										"//select[@id='rootFolder']/option[text()='OSeriesReconCsvAdmin']")) {
									report = true;
								}
							} while (report == false);
						}
					}
				}
			}
			if (DownloadReports != "") {
				selectByValue(AUA.Rootfolder, DownloadReports);
				waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
				selectByValue(AUA.Internalyearfolder, Year);
				waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
				selectByValue(AUA.InternalMonthfolder, Month);
				waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
				if (Transaction_Details_Summary.equalsIgnoreCase("TransactionDetails")) {
					Thread.sleep(2000);
					JavaScriptclick(driver, AUA.AdminTransactionDetailsReport);
				} else if (Transaction_Details_Summary.equalsIgnoreCase("TransactionSummary")) {
					Thread.sleep(2000);
					JavaScriptclick(driver, AUA.AdminTransactionSummaryReport);
				} else if (Transaction_Details_Summary.equalsIgnoreCase("HVRTransactionDetails")) {
					Thread.sleep(3000);
					JavaScriptclick(driver, AUA.HVRDetailReport);
				} else {
					Thread.sleep(2000);
					JavaScriptclick(driver, AUA.AdminRTEBracketScheduleReport);
				}

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
							+ "Reports";
					String name = FileName;
					tempfile2 = new File(DirName + "\\" + name + ".csv");
					Thread.sleep(5000);
					Comparecsvfiles(tempfile, tempfile2);
				}
				if (CompareRTE_BracketSchedule.equalsIgnoreCase("YES")) {
					String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
							+ "RTE";
					String name = folderType;
					tempfile2 = new File(DirName + "\\" + name + ".csv");
					Thread.sleep(5000);
					Comparecsvfiles(tempfile, tempfile2);

				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@SuppressWarnings("resource")
	public void PerformanceMetricsETEReport(String ClientName, String TestInstance_Client, String Report,
			String CompanyName, String Validate_StartDate, String StartDate, String EndDate,
			String VerifyWithoutTaxpayer, String Folder_Name, String Year, String Month, String Validate_Report,
			String Verify_NoblankReport) throws Exception {
		try {
			AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
			VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
			File directory = new File(".//");
			Robot rob = new Robot();
			String DownloadFilepath = xDownloadFiles("TransactionReport");
			click(AUA.manageSMBCustomers);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			/*
			 * if (BPOCPAClientName != null) { ClientName = BPOCPAClientName; } else if
			 * (MerchantClientName != null) { ClientName = MerchantClientName; } else {
			 * ClientName = VertexClientName; }
			 */
			if (TestInstance_Client.equalsIgnoreCase("YES")) {
				ClientName = ClientName + ".TI";
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

	public void AdminConfigurationReport(String ClientName, String Admin_Push, String TaxpayerName, String Compare_Xlsx,
			String ConfigReport_Types) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
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
		/*
		 * if (BPOCPAClientName != null) { ClientName = BPOCPAClientName; } else if
		 * (MerchantClientName != null) { ClientName = MerchantClientName; } else {
		 * ClientName = VertexClientName; }
		 */
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
		waitforElement(90, VSC.EXPORT, driver);
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
			click(CR.ADMINEXPORT);
			Thread.sleep(5000);
			if (!VerifyObject(driver, PUL.OKERROR_XPATH)) {
				assertTrue(true);
			} else {
				assertFalse(false);
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
			File directory = new File(".\\");
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
			if (Compare_Xlsx != "") {
				// status =
				// XLSXtoXLSXFilesCompare(Filepath,DirName+"\\"+ConfigurationReportPageSheet.getCell(Compare_Xlsx,
				// Data_Row).getContents());
				xr_xCompareExcelFiles(Filepath, DirName + "\\" + Compare_Xlsx);
			}
			if (CloudData.get_data("Verify_ConfigReport").equalsIgnoreCase("YES")) {
				// xCompareExcelFiles(Filepath,DirName+"\\"+ConfigurationReportPageSheet.getCell(Compare_Xlsx,
				// Data_Row).getContents(),RSheet,bussFunction);
				String s = xGetXLSXCellValue1(Filepath, "TaxArea ID", "Taxpayer Locations");
				assertTrue(!s.isEmpty());
			}
			if (ConfigReport_Types != "") {
				String wbookname = ConfigReport_Types;
				File file = new File(".//");
				String custworkbookname = file.getCanonicalPath() + "\\InputTestData\\Reports\\" + wbookname + ".xls";
				xr_xCompareExcelFiles(Filepath, custworkbookname);
			}
		}

	}

	public void AdminConfigurationReport(String ClientName, String Admin_Push, String TaxpayerName, String Compare_Xlsx,
			String Verify_ConfigReport, String ConfigReport_Types) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
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
		waitforElement(90, VSC.EXPORT, driver);
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
			selectByValue(CR.ADMINTAXPAYER, TaxpayerName);
			Thread.sleep(5000);
			// Verifying Customer Date Added Filed as per POR-11567 in 6.0
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
			click(CR.ADMINEXPORT);
			Thread.sleep(5000);
			if (!VerifyObject(driver, PUL.OKERROR_XPATH)) {
				assertTrue(true);
			} else {
				assertFalse(false);
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
			File directory = new File(".\\");
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
			if (Compare_Xlsx != "") {
				xr_xCompareExcelFiles(Filepath, DirName + "\\" + Compare_Xlsx);
			}
			if (Verify_ConfigReport.equalsIgnoreCase("YES")) {
				Thread.sleep(5000);
				String s = xGetXLSXCellValue1(Filepath, "TaxArea ID", "Taxpayer Locations");
				assertTrue(!s.isEmpty());
			}
			if (ConfigReport_Types != "") {
				String wbookname = ConfigReport_Types;
				File file = new File(".//");
				String custworkbookname = file.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "Reports" + wbookname + ".xls";
				xr_xCompareExcelFiles(Filepath, custworkbookname);
			}
		}

	}

	public void ConfigurationReportWithoutProd(String TaxpayerName, String Client_Name, String CompanyCode,
			String Federal_ID_Number, String StartDate, String FilingQuantity, String Verify_Discount)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CR.Reports_Overview, driver);
		waitforElement(180, CR.ConfigurationLink, driver);
		JavaScriptclick(driver, CR.ConfigurationLink);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.CLIENTNAME_XPATH, driver);
		WebElement CReport_Client = driver.findElement(By.xpath(CR.CLIENTNAME_XPATH));
		List<WebElement> Client_Names = CReport_Client.findElements(By.xpath("//optgroup/option"));
		System.out.println("Size of the dropdown : " + Client_Names.size());
		// print dropdown options
		for (int DD = 0; DD < Client_Names.size(); DD++) {
			CReport_Client.sendKeys(Client_Names.get(DD).getText());
			System.out.println(DD);
		}
		if (TaxpayerName.equalsIgnoreCase("REGCOMP")) {
			selectByValue(CR.TAXDROPDOWN, "//a[@title='" + TaxpayerName + "']");
		} else {
			System.out.println("seelcting tax payer");
			selectByValue(CR.TAXDROPDOWN, TaxpayerName);
			Thread.sleep(8000);
		}
		sendkeysTab(CR.TAXDROPDOWN);
		Thread.sleep(5000);
		assertTrue(CR.TAXINFO_COMPANYCODE.isDisplayed());
		assertTrue(CR.TAXINFO_TAXNAME.isDisplayed());
		if (Client_Name.equalsIgnoreCase("USA")) {
			assertTrue(CR.TAXINFO_FEDERALID.isDisplayed());
			assertTrue(CR.TAXADDRESS_STREETADDRESS.isDisplayed());
			assertTrue(CR.TAXADDRESS_CITY.isDisplayed());
			assertTrue(CR.TAXADDRESS_STATE.isDisplayed());
			assertTrue(CR.TAXADDRESS_ZIP.isDisplayed());
		}
		if (Client_Name.equalsIgnoreCase("CAN")) {
			assertTrue(CR.TAXINFO_BUSSINESSNUMBER.isDisplayed());
			assertTrue(CR.TAXADDRESS_STREETADDRESS.isDisplayed());
			assertTrue(CR.TAXADDRESS_MUNICIPALITY.isDisplayed());
			assertTrue(CR.TAXADDRESS_PROVINCETERRITORY.isDisplayed());
			assertTrue(CR.TAXADDRESS_ZIP.isDisplayed());
		}
		assertTrue(CR.TAXINFO_STARTDATE.isDisplayed());
		assertTrue(CR.TAXINFO_ENDDATE.isDisplayed());
		assertTrue(CR.TAXINFO_FILINGENTITY.isDisplayed());
		if (Client_Name != "") {
			assertTrue(Client_Name.equalsIgnoreCase(getText(CR.TAXINFO_TAXPAYERNAME)));
		}
		if (CompanyCode != "") {
			assertTrue(CompanyCode.equalsIgnoreCase(getText(CR.TAXINFO_CODE)));
		}
		if (Federal_ID_Number != "") {
			if (Client_Name.equalsIgnoreCase("CAN")) {
			} else {
				assertTrue(Federal_ID_Number.equalsIgnoreCase(getText(CR.TAXINFO_FEDERALIDVALUE)));
			}
		}
		assertTrue(StartDate.equalsIgnoreCase(getText(CR.TAXINFO_DATE)));
		assertTrue(FilingQuantity.equalsIgnoreCase(getText(CR.TAXINFO_FILINGQUANTITY)));
		assertTrue(CR.TAXLOC_LOCATIONCODE.isDisplayed());
		assertTrue(CR.TAXLOC_LOCATIONNAME.isDisplayed());
		assertTrue(CR.TAXLOC_STREETADDRESS.isDisplayed());
		assertTrue(CR.TAXLOC_CITY.isDisplayed());
		assertTrue(CR.TAXLOC_STATE.isDisplayed());
		assertTrue(CR.TAXLOC_ZIP.isDisplayed());
		if (VerifyObject(driver, CR.TAXLOC_REGCODE)) {
			assertTrue(true);
		}
		assertTrue(CR.TAXRETURNS_REGNUMBER.isDisplayed());
		assertTrue(CR.TAXRETURNS_RETURNNAME.isDisplayed());
		assertTrue(CR.TAXRETURNS_JURINAME.isDisplayed());
		assertTrue(CR.TAXRETURNS_JURITYPE.isDisplayed());
		assertTrue(CR.TAXRETURNS_FILINGMETHOD.isDisplayed());
		assertTrue(CR.TAXRETURNS_FILINGFREQ.isDisplayed());
		assertTrue(CR.RETURNLEVEL.isDisplayed());
		assertTrue(CR.RETURNTYPE.isDisplayed());
		// to scroll window down
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0, 800)");
		Thread.sleep(2000);
		assertTrue(CR.TAXPAYER_JURINAME.isDisplayed());
		assertTrue(CR.TAXPAYER_JURITYPE.isDisplayed());
		if (Verify_Discount != "") {
			assertTrue(CR.TAXCUSTOMERINFO_CUSTOMERCODE.isDisplayed());
			assertTrue(CR.TAXCUSTOMERINFO_CUSTOMERNAME.isDisplayed());
			assertTrue(CR.TAXCUSTOMERINFO_CUSTOMERTYPE.isDisplayed());
			assertTrue(CR.TAXCUSTOMERINFO_TAXRESULT.isDisplayed());
			assertTrue(CR.TAXCUSTOMERINFO_DEDUCTIONCATEGORY.isDisplayed());
			assertTrue(CR.TAXCUSTOMERINFO_STARTDATE.isDisplayed());
			assertTrue(CR.TAXCUSTOMERINFO_ENDDATE.isDisplayed());
			scrollDown(driver);
			click(CR.SELECTDISOCUNTCODE);
			selectByValue(CR.SELECTDISOCUNTCODE, "15 per page");
			Thread.sleep(5000);
			assertTrue(CR.DISCOUNTCODESTYPE.isDisplayed());
			assertTrue(CR.DISCOUNTCODESCODE.isDisplayed());
			assertTrue(CR.GENERALDISCOUNT.isDisplayed());
			assertTrue(CR.GENERALDISCOUNTVALUE.isDisplayed());
			assertTrue(CR.CASHDISCOUNT.isDisplayed());
			assertTrue(CR.CASHDISCOUNTVALUE.isDisplayed());
			assertTrue(CR.MANUFACTURERCOUPONS.isDisplayed());
			assertTrue(CR.MANUFACTURERCOUPONSVALUE.isDisplayed());
			assertTrue(CR.RETAILERCOUPONS.isDisplayed());
			assertTrue(CR.RETAILERVALUE.isDisplayed());
			assertTrue(CR.TRADEDISCOUNT.isDisplayed());
			assertTrue(CR.TRADEDISCOUNTVALUE.isDisplayed());
			assertTrue(CR.QUANTITYDISCOUNT.isDisplayed());
			assertTrue(CR.QUANTITYDISCOUNTVALUE.isDisplayed());
			assertTrue(CR.EARLYPAYMENTDISCOUNT.isDisplayed());
			assertTrue(CR.EARLYPAYMENTDISCOUNTVALUE.isDisplayed());
			assertTrue(CR.FLEXIBLEDISCOUNT.isDisplayed());
			assertTrue(CR.FLEXIBLEDISCOUNTVALUE.isDisplayed());
		}
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("scroll(0, 0)");
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
	}

	public void MOSitecodesinConfigReport(String Companyname, String Report_path) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, CR.Reports_Overview, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CR.ConfigurationLink);
		Thread.sleep(8000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		selectByValue(CR.TAXDROPDOWN, Companyname);
		Thread.sleep(8000);
		// downloading Export Button
		JavaScriptclick(driver, CR.EXPORTBUTON);
		Thread.sleep(8000);
		// Comparing Downloaded File
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
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
		File directory = new File(".\\");
		@SuppressWarnings("unused")
		String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
		// XLSXtoXLSXFilesCompare(Filepath,DirName+"\\"+Report_path);
		// xr_xCompareExcelFiles(Filepath, DirName + "\\" + Report_path);
		String s = xGetXLSXCellValue1(Filepath, "Site Code", "Reporting Locations");
		assertTrue(!s.isEmpty());
	}

	public void MOSitecodesinAdminConfigReport(String Companyname, String ClientName, String Report_path)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]", driver);
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
		Thread.sleep(5000);
		JavaScriptclick(driver, VSC.SMBCUSTOMERREPORT);
		Thread.sleep(8000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		selectByValue(CR.TAXDROPDOWN, Companyname);
		Thread.sleep(8000);
		assertTrue(CR.TAXLOC_HEADING.isDisplayed());
		if (!VerifyObject(driver, CR.TAXLOC_REGCODE)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		// downloading Export Button
		JavaScriptclick(driver, CR.ADMINEXPORTBUTON);
		Thread.sleep(8000);
		// Comparing Downloaded File
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
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
		File directory = new File(".\\");
		String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
		// XLSXtoXLSXFilesCompare(Filepath,DirName+"\\"+Report_path);
		xr_xCompareExcelFiles(Filepath, DirName + "\\" + Report_path);
		String s = xGetXLSXCellValue1(Filepath, "Site Code", "Reporting Locations");
		assertTrue(!s.isEmpty());

	}

	public void AdminTaxabilityReport(String ClientName, String folderType, String Year, String Month, String FileName)
			throws Exception {
		try {
			File directory = new File(".//");
			AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
			Robot rob = new Robot();
			String DownloadFilepath = xDownloadFiles("TaxAssistReport");
			click(AUA.manageSMBCustomers);
			// String VertexClientName="ProTCRTESTClient152162642";
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			/*
			 * if (BPOCPAClientName != null) { ClientName = BPOCPAClientName; } else if
			 * (MerchantClientName != null) { ClientName = MerchantClientName; } else {
			 * ClientName = VertexClientName; }
			 */
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
				WebElement authorized = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
				JavaScriptclick(driver, authorized);
			} else {
				driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
						+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
			}
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			click(AUA.smbCustomerReports);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			Thread.sleep(3000);
			// Admin Reports Verification
			waitforElement(180, AUA.Rootfolder, driver);
			selectByValue(AUA.Rootfolder, folderType);
			Thread.sleep(3000);
			selectByValue(AUA.Internalyearfolder, Year);
			Thread.sleep(3000);
			selectByValue(AUA.InternalMonthfolder, Month);
			Thread.sleep(6000);
			waitforElement(180, AUA.DOWNLOADEDADMINTAXABILITYREPORT, driver);
			assertTrue(AUA.DOWNLOADEDADMINTAXABILITYREPORT.isDisplayed());
			JavaScriptclick(driver, AUA.DOWNLOADEDADMINTAXABILITYREPORT);
			Thread.sleep(3000);
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
						+ "Reports";
				String name = FileName;
				tempfile2 = new File(DirName + "\\" + name + ".csv");
				Thread.sleep(5000);
				Comparecsvfiles(tempfile, tempfile2);
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void AdminInternationalComplianceReport(String Clientname, String Folder) throws Exception {
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);		
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(AUA.smbCustomerName, Clientname);
		Thread.sleep(7000);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]",
				driver);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname
				+ "')]/../../../td[contains(.,'Registered')]")) {
			WebElement Registered = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + Clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname
				+ "')]/../../../td[contains(.,'Authorized')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + Clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + Clientname
				+ "')]/../../../td[contains(.,'Cancelled')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + Clientname + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(2000);
		click(AUA.smbCustomerReports);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(3000);
		// Admin Reports Verification
		waitforElement(180, AUA.Rootfolder, driver);
		selectByValue(AUA.Rootfolder, Folder);
		Thread.sleep(3000);
		selectByValue(AUA.Internalyearfolder, xGetCustomizedYear(0));
		Thread.sleep(3000);
		selectByValue(AUA.InternalMonthfolder, xGetCustomizedMonNumber(0));
		Thread.sleep(6000);
		waitforElement(180, AUA.DownloadedInternationalReturns, driver);
		assertTrue(AUA.DownloadedInternationalReturns.isDisplayed());
		Thread.sleep(3000);
	}

	public void TaxabilityImpactReport(String Loop_Billing, String Report_Name, String Product_Name, String Tax_Type,
			String TIRGenerate, String TIRDeleteProd, String TIREdit, String Unsupported_TaxCategories,
			String Save_Close, String DupReportVerify, String TIRDownload, String TIRDeleteReport) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		TaxabilityImpactPage TIMP = PageFactory.initElements(driver, TaxabilityImpactPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		String[] repName;
		String TIRrepname = "", loop;
		if (Loop_Billing != "") {
			loop = Loop_Billing;
			int tirloop = Integer.parseInt(loop);
			for (int j = 0; j <= tirloop; j++) {
				waitforElement(180, TIMP.TAXABILITYIMPACTREPORT, driver);
				JavaScriptclick(driver, TIMP.TAXABILITYIMPACTREPORT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(5000);
				waitforElement(180, TIMP.CONFIGURENEWREPORT, driver);
				JavaScriptclick(driver, TIMP.CONFIGURENEWREPORT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TIMP.ADDANEWPRODUCT, driver);
				if (Report_Name != "") {
					Report_Name = "Report" + RND();
					repName = Report_Name.split(";");
					// verifying Duplicate Product Report message
					if (j == 2) {
						// TIRrepname=xLabelRND(repName);
						type(TIMP.COMPANYREPORTNAME, TIRrepname);
						click(TIMP.ADDANEWPRODUCT);
						waitforElement(180, TIMP.DUPREPORTVERFY, driver);
						assertTrue(TIMP.DUPREPORTVERFY.isDisplayed());
						clear(TIMP.COMPANYREPORTNAME);
					}
					TIRrepname = xLabelRND(repName);
					type(TIMP.COMPANYREPORTNAME, TIRrepname);
				}
				click(TIMP.ADDANEWPRODUCT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TIMP.TBPRODUCTIDENTIFIER, driver);
				type(TIMP.TBPRODUCTIDENTIFIER, Product_Name);
				if (Tax_Type.equalsIgnoreCase("Buyer")) {
					selectByValue(TIMP.TAXTYPE, "Buyer");
				} else {
					selectByValue(TIMP.TAXTYPE, "Seller");
				}
				// Adding Products with Products Quick Search
				click(PP.PRODUCTS_QUICK_SEARCH);
				Thread.sleep(1000);
				type(PP.PRODUCTS_QUICK_SEARCH, Product_Name);
				Thread.sleep(3000);
				sendkeysTab(PP.PRODUCTS_QUICK_SEARCH);
				Thread.sleep(3000);
				click(TIMP.SAVEANDCLOSE);
				Thread.sleep(3000);
				// An erro has Occurred Pop Up Handling
				if (VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
					click(PUL.OKERROR);
					waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
					assertTrue(PUL.ANERRORHASOCCURED.isDisplayed());

				}
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TIMP.RETURNTOREPORTLISTING, driver);
				if (TIRGenerate.equalsIgnoreCase("Yes")) {
					click(TIMP.GENERATEREPORT);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, TIMP.CONFIGURENEWREPORT, driver);
				}
			}
		} else {
			// Deleting second Product
			if (TIRDeleteProd.equalsIgnoreCase("Yes")) {
				JavaScriptclick(driver, TIMP.RETURNTORPRTCONFIG);
				waitUntilElementInvisibe(30, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TIMP.RETURNTOREPORTLISTING, driver);
				JavaScriptclick(driver, TIMP.PRODROW2DELETE);
				waitforElement(180, TIMP.DELETEPRODUCT, driver);
				JavaScriptclick(driver, TIMP.DELETEPRODUCT);
				Thread.sleep(3000);
			}
			// Editing the first Product
			if (TIREdit.equalsIgnoreCase("Yes")) {
				waitforElement(180, TIMP.RETURNTOREPORTLISTING, driver);
				JavaScriptclick(driver, TIMP.RETURNTOREPORTLISTING);
				waitUntilElementInvisibe(30, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TIMP.REPORTROW1EDIT, driver);
				JavaScriptclick(driver, TIMP.REPORTROW1EDIT);
				waitUntilElementInvisibe(30, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TIMP.PRODROW1EDIT, driver);
				JavaScriptclick(driver, TIMP.PRODROW1EDIT);
				waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
			}
			if (Report_Name != "") {
				JavaScriptclick(driver, TIMP.TAXABILITYIMPACTREPORT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(3000);
				waitforElement(180, TIMP.CONFIGURENEWREPORT, driver);
				Thread.sleep(3000);
				JavaScriptclick(driver, TIMP.CONFIGURENEWREPORT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TIMP.ADDANEWPRODUCT, driver);
				repName = Report_Name.split(";");
				TIRrepname = xLabelRND(repName);
				type(TIMP.COMPANYREPORTNAME, TIRrepname);
			}
			if (VerifyObject(driver, TIMP.ADDANEWPRODUCT)) {
				click(TIMP.ADDANEWPRODUCT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			}
			// Verifying Unsupported taxability categories
			if (Unsupported_TaxCategories != "") {
				String unsupported_taxCategories = Unsupported_TaxCategories;
				ArrayList<String> aList = new ArrayList<String>(Arrays.asList(unsupported_taxCategories.split(",")));
				for (int k = 0; k < aList.size(); k++) {
					String category = aList.get(k).toString();
					waitforElement(180, PP.PRODUCTS_QUICK_SEARCH, driver);
					clear(PP.PRODUCTS_QUICK_SEARCH);
					type(PP.PRODUCTS_QUICK_SEARCH, category);
					Thread.sleep(2000);
					if (!VerifyObject(driver, "//p[contains(.,'" + category + "')]")) {
						assertTrue(true);
					} else {
						assertFalse(false);
					}

				}
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, TIMP.TBPRODUCTIDENTIFIER, driver);
			clear(TIMP.TBPRODUCTIDENTIFIER);
			type(TIMP.TBPRODUCTIDENTIFIER, Product_Name);
			selectByValue(TIMP.TAXTYPE, "Seller");
			// Adding Products with Products Quick Search
			click(PP.PRODUCTS_QUICK_SEARCH);
			Thread.sleep(1000);
			clear(PP.PRODUCTS_QUICK_SEARCH);
			type(PP.PRODUCTS_QUICK_SEARCH, Product_Name);
			Thread.sleep(3000);
			sendkeysTab(PP.PRODUCTS_QUICK_SEARCH);
			Thread.sleep(3000);
			// Saving Edited Product
			if (TIREdit.equalsIgnoreCase("Yes")) {
				JavaScriptclick(driver, TIMP.SAVE);
			} else if ((Save_Close != "")) {
				click(TIMP.SAVEANDCLOSE);
			} else {
				click(TIMP.SAVEADDANOTHER);
				waitforElement(180, TIMP.SAVEANOTHERSUCS, driver);
			}
			Thread.sleep(3000);
			// An erro has Occurred Pop Up Handling
			if (VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
				click(PUL.OKERROR);
				waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
				assertTrue(PUL.ANERRORHASOCCURED.isDisplayed());
			}
			// verifying Duplicate Product Error message
			if (DupReportVerify != "") {
				type(TIMP.TBPRODUCTIDENTIFIER, Product_Name);
				selectByValue(TIMP.TAXTYPE, "Seller");
				// Adding Products with Products Quick Search
				click(PP.PRODUCTS_QUICK_SEARCH);
				Thread.sleep(1000);
				clear(PP.PRODUCTS_QUICK_SEARCH);
				type(PP.PRODUCTS_QUICK_SEARCH, Product_Name);
				Thread.sleep(3000);
				sendkeysTab(PP.PRODUCTS_QUICK_SEARCH);
				Thread.sleep(3000);
				click(TIMP.SAVEANDCLOSE);
				Thread.sleep(3000);
				// An erro has Occurred Pop Up Handling
				if (VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
					click(PUL.OKERROR);
					waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
					assertTrue(PUL.ANERRORHASOCCURED.isDisplayed());
				}
				assertTrue(TIMP.DUPPRODVERFY.isDisplayed());

				clear(TIMP.TBPRODUCTIDENTIFIER);
			}
			if (TIRGenerate.equalsIgnoreCase("Yes")) {
				click(TIMP.GENERATEREPORT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TIMP.CONFIGURENEWREPORT, driver);
			}
		}
		// Downloading Report
		if (TIRDownload.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, TIMP.GENERATIONDATESORT);
			Thread.sleep(5000);
			waitforElement(180, TIMP.REPORTDWNLD, driver);
			JavaScriptclick(driver, TIMP.REPORTDWNLD);
			// file download
			Thread.sleep(5000);
		}
		// Deleting Report
		if (TIRDeleteReport.equalsIgnoreCase("Yes")) {
			waitforElement(180, TIMP.REPORTDELETE, driver);
			JavaScriptclick(driver, TIMP.REPORTDELETE);
			Thread.sleep(2000);
			waitforElement(180, TIMP.DELETEREPORTCONFIRM, driver);
			JavaScriptclick(driver, TIMP.DELETEREPORTCONFIRM);
			waitUntilElementInvisibe(30, TIMP.DELETEREPORTCONFIRM_XPATH, driver);
		}
		JavaScriptclick(driver, TIMP.TAXABILITYIMPACTREPORT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TIMP.CONFIGURENEWREPORT, driver);
	}

	public void AdminCustomersReport(String ClientName, String TaxpayerCode, String PortalReport, String Year,
			String Month) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]",
				driver);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement registered = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			JavaScriptclick(driver, registered);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement authorized = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			JavaScriptclick(driver, authorized);
		} else {
			WebElement initialized = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
			JavaScriptclick(driver, initialized);
		}
		waitforElement(180, VSC.SMRRETURNS, driver);
		JavaScriptclick(driver, VSC.SMBREPORTS);
		waitforElement(180, VSC.ADMINSELECTFOLDER, driver);
		Thread.sleep(5000);
		selectByValue(VSC.ADMINSELECTFOLDER, PortalReport);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		waitforElement(60, VSC.ADMININTERNALSELECTFOLDER, driver);
		selectByValue(VSC.ADMININTERNALSELECTFOLDER, Year);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		waitforElement(60, VSC.ADMININTERNALSELECTFOLDER2, driver);
		selectByValue(VSC.ADMININTERNALSELECTFOLDER2, Month);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		if (PortalReport.equalsIgnoreCase("Return")) {
			selectByValue(VSC.internalFolder4, TaxpayerCode);
		}
		Thread.sleep(3000);
		selectByValue(VSC.paginationDD, "100 per page");
		Thread.sleep(3000);
		waitforElement(60, VSC.REPORTFROMFOLDER, driver);
		ArrayList<String> list = new ArrayList<String>();
		for (int j = 1; j <= VSC.REPORTLISTFROMFOLDER.size(); j++) {
			String datetimstamp = driver.findElement(By.xpath("//ul/li[" + j + "]/span/a/../../span[2]")).getText();
			list.add(datetimstamp);
		}
		ArrayList<String> list1 = list;
		/* Sorting in decreasing order */
		Collections.sort(list1, Collections.reverseOrder());
		list.size();
		for (int k = 0; k < list.size(); k++) {
			assertTrue(list.get(k).equals(list1.get(k)));
		}
	}

	@SuppressWarnings("unchecked")
	public void AdminCreditReport(String ClientName, String TaxPayer, String Fillingperiod, String CreditAmountNegative,
			String Validate, String Validate_Positive, String StartDate, String PortalReport, String Year, String Month,
			String AdminReport) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		Robot rob = new Robot();
		int count = 0;
		xDownloadFiles("Returns");
		File tempfile = null, tempfile2 = null;
		click(VSC.MANAGESMBCUSTLINK);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]",
				driver);
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
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(.,'Registered')]")) {
			WebElement registered = driver.findElement(
					By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
			JavaScriptclick(driver, registered);
		} else {
			WebElement authorized = driver.findElement(
					By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]"));
			JavaScriptclick(driver, authorized);
		}
		waitforElement(180, VSC.SMRRETURNS, driver);
		JavaScriptclick(driver, VSC.SMRRETURNS);
		if (VerifyObject(driver, MTR.MTRALERT1)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				count = count + 1;
				selectByValue(MTR.TAXPAYER, TaxPayer);
				Thread.sleep(10000);
				if (count == 20) {
					break;
				}
				if (!VerifyObject(driver, MTR.MTRALERT1)) {
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180, VSC.ADMINTAXPAYER, driver);
		selectByValue(VSC.ADMINTAXPAYER, TaxPayer);
		waitforElement(180, VSC.ADMINFILLINGPERIOD, driver);
		selectByValue(VSC.ADMINFILLINGPERIOD, Fillingperiod);
		waitforElement(180, VSC.ADMINCREDITREPORT, driver);
		JavaScriptclick(driver, VSC.ADMINCREDITREPORT);
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
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Returns";
			xDownloadFiles("Returns");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			tempfile = new File(LatestDownloadedFile("Returns", true));
			System.out.println("Path" + path);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(5000);
			String dwlfile = LatestDownloadedFile("Returns", false);
			tempfile = new File(dwlfile);
			System.out.println(tempfile);
		}
		if (CreditAmountNegative != "") {
			// assertTrue(VSC.ADMINFILEANDPAY.isDisplayed());
			File directory = new File(".//");
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Returns";
			FileName = DirName + "\\" + FileName.replace("]", "");
			FileName = FileName.replace(".csv", "_Negative.csv");
			System.out.println("Printed");
			tempfile2 = new File(FileName);
			if (Validate != "") {
				ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
				ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				System.out.println(f1);
				System.out.println(f2);
				f1.removeAll(f2);
				System.out.println(f1.size());
				assertTrue((f1.size() == 0));
			}
		} else {
			File directory = new File(".//");
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Returns";
			FileName = DirName + "\\" + FileName.replace("]", "");
			if (Validate_Positive != "") {
				FileName = FileName.replace(".csv", "_Positive.csv");
				tempfile2 = new File(FileName);
			}
			tempfile2 = new File(FileName);
			if (Validate != "") {
				ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
				ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				f1.removeAll(f2);
			}
			// Verifying the POR_4450 in 5.1
			click(VSC.SMBREPORTS);
			// Verifying label state/Territory
			waitforElement(180, VSC.ADMINTRANSACTIONREPORT, driver);
			selectByValue(VSC.ReportDropdown, "Transaction Report");
			assertTrue(VSC.ADMINSTATE.isDisplayed());
			selectByValue(VSC.ADMINTAXPAYERNAME, TaxPayer);
			waitforElement(180, VSC.ADMINSTRATDATE, driver);
			type(VSC.ADMINSTRATDATE, StartDate);
			waitforElement(180, VSC.ADMINENDDATE, driver);
			type(VSC.ADMINENDDATE, xGetDate("MM/dd/yyyy"));
			waitforElement(180, VSC.ADMINSUBMIT, driver);
			click(VSC.ADMINSUBMIT);
			waitforElement(180, VSC.ADMINSUCCESSREPORT, driver);
			driver.navigate().refresh();
			// Generating TransactionDetails
			waitforElement(180, VSC.ADMINTRANSACTIONREPORT, driver);
			selectByValue(VSC.ReportDropdown, "Transaction Report");
			assertTrue(VSC.ADMINSTATE.isDisplayed());
			selectByValue(VSC.ADMINTAXPAYERNAME, TaxPayer);
			waitforElement(180, VSC.ADMINSTRATDATE, driver);
			type(VSC.ADMINSTRATDATE, StartDate);
			waitforElement(180, VSC.ADMINENDDATE, driver);
			type(VSC.ADMINENDDATE, xGetDate("MM/dd/yyyy"));
			waitforElement(180, VSC.ADMINSUBMIT, driver);
			click(VSC.ADMINTRANSACTIONDETAILS);
			click(VSC.ADMINSUBMIT);
			Thread.sleep(2000);
			waitforElement(180, VSC.ADMINSUCCESSREPORT, driver);
			boolean report = false;
			do {
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				count = count + 1;
				Thread.sleep(10000);
				if (count == 20) {
					report = true;
					break;
				}
			} while (report == false);

			// Verification of Transaction Details report of Portal in admin
			if (!PortalReport.equalsIgnoreCase("NegotiatedContract")) {
				selectByValue(VSC.ADMINSELECTFOLDER, PortalReport);
				sendkeysTab(VSC.ADMINSELECTFOLDER);
				Thread.sleep(10000);
				waitforElement(360, VSC.ADMININTERNALSELECTFOLDER, driver);
				selectByValue(VSC.ADMININTERNALSELECTFOLDER, Year);
				sendkeysTab(VSC.ADMININTERNALSELECTFOLDER);
				Thread.sleep(10000);
				waitforElement(360, VSC.ADMININTERNALSELECTFOLDER2, driver);
				selectByValue(VSC.ADMININTERNALSELECTFOLDER2, Month);
				sendkeysTab(VSC.ADMININTERNALSELECTFOLDER2);
				Thread.sleep(10000);
				if (!VerifyObject(driver, VSC.PORTALTRANSDETAILSREPORT)) {
					boolean report2 = false;
					do {
						Thread.sleep(30000); // For O Series sync refreshing the page to get download link
						count = count + 1;
						if (count == 60) {
							break;
						}
						if (VerifyObject(driver, VSC.PORTALTRANSDETAILSREPORT)) {
							report2 = true;
						}
					} while (report2 == false);
				}
				assertTrue(VSC.PORTALTRANSDETAILSREPORT.isDisplayed());
				waitforElement(180, VSC.PORTALTRANSDETAILSREPORT, driver);
				click(VSC.PORTALTRANSDETAILSREPORT);
				if (Browser.equalsIgnoreCase("IE")) {
					Thread.sleep(5000);
					rob.keyPress(KeyEvent.VK_ALT);
					Thread.sleep(1000);
					rob.keyPress(KeyEvent.VK_S);
					rob.keyRelease(KeyEvent.VK_ALT);
					rob.keyRelease(KeyEvent.VK_S);
				}
				if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
					File directory1 = new File(".//");
					String path = directory1.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "Returns";
					xDownloadFiles("Returns");
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					tempfile = new File(LatestDownloadedFile("Returns", true));
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					Thread.sleep(5000);
					tempfile = new File(LatestDownloadedFile("Returns", false));
				}
				File directory1 = new File(".//");
				String DirName2 = directory1.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "Returns";
				FileName = DirName2 + "\\" + FileName.replace("]", "");
				String[] name = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
				tempfile2 = new File(name[0] + ".csv");
				if (Validate != "") {
					ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
					ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
					f1.removeAll(f2);
				}
				// Verification of Transaction Summary report of Portal in admin
				assertTrue(VSC.PORTALTRANSSUMMARYREPORT.isDisplayed());
				waitforElement(180, VSC.PORTALTRANSSUMMARYREPORT, driver);
				click(VSC.PORTALTRANSSUMMARYREPORT);
				if (Browser.equalsIgnoreCase("Chrome")) {
					File CDirectory = new File(".//");
					String path = CDirectory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "Returns";
					xDownloadFiles("Returns");
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					tempfile = new File(LatestDownloadedFile("Returns", true));
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					Thread.sleep(5000);
					tempfile = new File(LatestDownloadedFile("Returns", false));
				}
				File Cdirectory2 = new File(".//");
				String CDirName = Cdirectory2.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "Returns";
				FileName = CDirName + "\\" + FileName.replace("]", "");
				String[] name1 = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
				tempfile2 = new File(name1[0] + ".csv");
				if (Validate != "") {
					ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
					ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
					f1.removeAll(f2);
				}
				// Verification of Transaction Summary report in admin
				if (VerifyObject(driver, AdminReport)) {
					boolean report3 = false;
					do {
						driver.navigate().refresh();
						Thread.sleep(20000);
						count = count + 1;
						if (count == 10) {
						}
						selectByValue(VSC.ADMINSELECTFOLDER, AdminReport);
						Thread.sleep(2000);
						if (!VerifyObject(driver, AdminReport)) {
							report3 = true;
						}
					} while (report3 == false);
				}
				selectByValue(VSC.ADMINSELECTFOLDER, AdminReport);
				sendkeysTab(VSC.ADMINSELECTFOLDER);
				Thread.sleep(10000);
				waitforElement(180, VSC.ADMININTERNALSELECTFOLDER, driver);
				selectByValue(VSC.ADMININTERNALSELECTFOLDER, Year);
				sendkeysTab(VSC.ADMININTERNALSELECTFOLDER);
				Thread.sleep(10000);
				waitforElement(180, VSC.ADMININTERNALSELECTFOLDER2, driver);
				selectByValue(VSC.ADMININTERNALSELECTFOLDER2, Month);
				sendkeysTab(VSC.ADMININTERNALSELECTFOLDER2);
				Thread.sleep(10000);
				if (!VerifyObject(driver, VSC.ADMINTRANSSUMMARY)) {
					boolean report2 = false;
					do {
						Thread.sleep(30000); // For O Series sync refreshing the page to get download link
						count = count + 1;
						if (count == 60) {
							break;
						}
						if (VerifyObject(driver, VSC.ADMINTRANSSUMMARY)) {
							report2 = true;
						}
					} while (report2 == false);
				}
				assertTrue(VSC.ADMINTRANSDETAILS.isDisplayed());
				waitforElement(180, VSC.ADMINTRANSDETAILS, driver);
			}
			if (PortalReport.equalsIgnoreCase("NegotiatedContract")) {
				selectByValue(VSC.ADMINSELECTFOLDER, PortalReport);
				sendkeysTab(VSC.ADMINSELECTFOLDER);
				Thread.sleep(10000);
				waitforElement(360, VSC.ADMININTERNALSELECTFOLDER, driver);
				selectByValue(VSC.ADMININTERNALSELECTFOLDER, Year);
				sendkeysTab(VSC.ADMININTERNALSELECTFOLDER);
				Thread.sleep(10000);
				waitforElement(360, VSC.ADMININTERNALSELECTFOLDER2, driver);
				selectByValue(VSC.ADMININTERNALSELECTFOLDER2, Month);
				sendkeysTab(VSC.ADMININTERNALSELECTFOLDER2);
				Thread.sleep(10000);
				click(VSC.NEGODETAILS);
			} else {
				click(VSC.ADMINTRANSDETAILS);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory2 = new File(".//");
				String path = directory2.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Returns";
				xDownloadFiles("Returns");
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				tempfile = new File(LatestDownloadedFile("Returns", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(5000);
				tempfile = new File(LatestDownloadedFile("Returns", false));
			}
			File directory2 = new File(".//");
			String DirName3 = directory2.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Returns";
			FileName = DirName3 + "\\" + FileName.replace("]", "");
			String[] name2 = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
			tempfile2 = new File(name2[0] + ".csv");
			if (Validate != "") {
				ArrayList<String> f3 = new ArrayList<String>(FileUtils.readLines(tempfile));
				ArrayList<String> f4 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				f3.removeAll(f4);
			}
			// Verification of Transaction Details report in admin
			if (!PortalReport.equalsIgnoreCase("NegotiatedContract")) {
				assertTrue(VSC.ADMINTRANSSUMMARY.isDisplayed());
				waitforElement(180, VSC.ADMINTRANSSUMMARY, driver);
				click(VSC.ADMINTRANSSUMMARY);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory3 = new File(".//");
				String path = directory3.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Returns";
				xDownloadFiles("Returns");
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				tempfile = new File(LatestDownloadedFile("Returns", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(5000);
				tempfile = new File(LatestDownloadedFile("Returns", false));
			}
			File directory3 = new File(".//");
			String DirName4 = directory3.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Returns";
			FileName = DirName4 + "\\" + FileName.replace("]", "");
			String[] name3 = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
			tempfile2 = new File(name3[0] + ".csv");
			if (Validate != "") {
				ArrayList<String> f5 = new ArrayList<String>(FileUtils.readLines(tempfile));
				ArrayList<String> f6 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				f5.removeAll(f6);
			}
		}
		driver.navigate().refresh();

	}

	public void TaxabilityReports(String Verify_No_TaxabilityReport, String Download_Link, String Date,
			String WorkSheet_Names, CharSequence CopyRight_Section, CharSequence Qualifying_Condition, String SheetName,
			String StateName, String Category_type, String Sub_Category, String QC_Status) throws Exception {
		Robot rob = new Robot();
		int TaxLength;
		String path;
		WebElement TaxabilityReport, TaxReportLink;
		String DownloadFilepath = xDownloadFiles("TaxabilityReports");
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		int count = 0;
		rob.mouseMove(0, 0);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		waitforElement(90, CR.TaxabilityReportText, driver);
		JavaScriptclick(driver, PUL.TaxabilityReport);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.TAXABILITYCOMPDD, driver);
		if (Verify_No_TaxabilityReport != "") {
			if (Verify_No_TaxabilityReport.equalsIgnoreCase("Yes")) {
				assertTrue(PUL.NOTAXABILITYREPORTS.isDisplayed());
			}
		} else {
			selectByValue(PUL.TAXABILITYCOMPDD, Download_Link);
			Thread.sleep(5000);
			// Verifying for Taxability Report Link
			if (VerifyObject(driver, PUL.NOTAXABILITYREPORT)) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(30000);
					count = count + 1;
					if (count == 15) {
						break;
					}
					if (!VerifyObject(driver, PUL.NOTAXABILITYREPORT)) {
						report = true;
					}
				} while (report == false);
			}
			Thread.sleep(5000);
			TaxabilityReport = driver
					.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/a[contains(.,'Report')]"));
			if (VerifyObject(driver, TaxabilityReport)) {
				click(TaxabilityReport);
				waitforElement(180, PUL.TAXABILITYREPORTMINUS, driver);
			}
			Thread.sleep(7000);
			List<WebElement> TaxabilityReportsLength = driver
					.findElements(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul/li/a"));
			TaxLength = TaxabilityReportsLength.size();
			if (TaxLength == 1) {
				TaxReportLink = driver
						.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[1]/li/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", TaxReportLink);
				TaxabilityDownloadlink = driver
						.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[1]/li/a")).getText();
			} else {
				TaxReportLink = driver.findElement(
						By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[" + TaxLength + "]/li/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", TaxReportLink);
				TaxabilityDownloadlink = driver
						.findElement(
								By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[" + TaxLength + "]/li/a"))
						.getText();
			}
			Thread.sleep(7000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "TaxabilityReports";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000); // wait to move the download file from downloads folder to our project
				// Actual folder
				LatestDownloadedFile("TaxabilityReports", true);
			}
			// wait to download the file to the file system folder
			else if (Browser.equalsIgnoreCase("IE")) {
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
				LatestDownloadedFile("TaxabilityReports", false);
			}
			FileInputStream fis = new FileInputStream(Filepath);
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
			Thread.sleep(7000);
			int Num_Sheets = workbook.getNumberOfSheets();
			for (int j = 0; j < Num_Sheets; j++) {
				String sheetname = workbook.getSheetName(j);
				System.out.println(sheetname);
				(WorkSheet_Names).split(";");
			}
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			int Num_rows = sheet.getLastRowNum() - 3;
			int qc = sheet.getLastRowNum() - 8;
			org.apache.poi.ss.usermodel.Row Row = sheet.getRow(Num_rows);
			org.apache.poi.ss.usermodel.Row Row_qc = sheet.getRow(qc);
			Cell atColumn = Row.getCell(0);
			Cell atColumn_QC = Row_qc.getCell(0);
			String Column_Value = atColumn.getStringCellValue();
			String Column_Value_QC = atColumn_QC.getStringCellValue();
			String strvertexinc = Column_Value.split("\\.")[0];
			String strvertexinc1 = Column_Value_QC.split("\\.")[0];
			CharSequence cs1 = CopyRight_Section;
			CharSequence cs2 = Qualifying_Condition;
			System.out.println(cs2);
			boolean hasState = strvertexinc.contains(cs1);
			boolean hasQC = strvertexinc1.contains(cs2);
			assertTrue(hasQC);
			assertTrue(hasState);
			fis.close();
			FileInputStream fis1 = new FileInputStream(Filepath);
			String[] Country = { "Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California", "Colorado",
					"Connecticut", "Delaware", "District of Columbia", "F.S. of Micronesia", "Florida", "Georgia",
					"Guam", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
					"Maine", "Marshall Islands", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
					"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
					"New York", "North Carolina", "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma",
					"Oregon", "Palau", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota",
					"Tennessee", "Texas", "United States Minor Outlying Islands", "Utah", "Vermont", "Virgin Islands",
					"Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "Canada" };
			XSSFWorkbook Juris_workbook = new XSSFWorkbook(fis1);
			XSSFSheet Juris_sheet = Juris_workbook.getSheetAt(3);
			System.out.println(Juris_workbook.getSheetAt(2).getSheetName());
			int Juris_rows = Juris_sheet.getLastRowNum();
			XSSFRow HeaderRow = Juris_sheet.getRow(1);
			int columnsCount = HeaderRow.getLastCellNum();
			for (int k = 2; k <= Juris_rows; k++) {
				XSSFRow Rows = Juris_sheet.getRow(k);
				for (int col = 0; col < columnsCount; col++) {
					XSSFCell atColumns = HeaderRow.getCell(col);
					String Columns_Value = atColumns.getStringCellValue();
					if (Columns_Value.equalsIgnoreCase("Jurisdiction Name")) {
						switch (Rows.getCell(col).getCellType()) {
						case Cell.CELL_TYPE_STRING:
							String Str_Value = Rows.getCell(col).getStringCellValue();
							assertTrue(Str_Value.equalsIgnoreCase(Country[k - 2]));
							break;
						}
					}
				}
			}
			fis1.close();
			if (Qualifying_Condition != null) {
				String qcsts = xGetCustomizedXLSXCellValue(Filepath, SheetName, StateName, Category_type, Sub_Category,
						QC_Status);
				assertTrue(qcsts.equalsIgnoreCase("Yes"));
			} else {
				xGetCustomizedXLSXCellValue(Filepath, SheetName, StateName, Category_type);
			}
		}
	}

	public void TaxAssistRuleReport(String Reports, String Taxpayer, String Generate, String Delete, String Download,
			String FileColumn_Values) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Robot rob = new Robot();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TaxAssistRuleReportPage TAR = PageFactory.initElements(driver, TaxAssistRuleReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		String Reportgen = "//tbody/tr/td/span/i[text()='Report generation is in progress ...']";
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		waitforText(90, TAR.TaxAssistRuleReportText, driver);
		scrollDown(driver);
		// switch cases added for two new reports Tax assist allocation and Lookup table
		// reports
		String TaxassistReports = Reports;
		switch (TaxassistReports) {
		case "TaxAssistRuleReport":
			JavaScriptclick(driver, TAR.TaxAssistRuleReport);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.GENERATEREPORT, driver);
			break;
		case "Taxassistallocationtablereport":
			JavaScriptclick(driver, TAR.Taxassistallocationtable);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.GENERATEREPORT, driver);
			break;
		case "Taxassistlookuptablereport":
			JavaScriptclick(driver, TAR.Taxassistlookuptable);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.GENERATEREPORT, driver);
			break;
		}
		if (Generate.equalsIgnoreCase("Yes")) {
			selectByValue(TAR.TAXPAYERSELECT, Taxpayer);
			JavaScriptclick(driver, PUL.GENERATEREPORT);
			waitforElement(180, TAR.SUCCESSMSG, driver);
			assertTrue(TAR.SUCCESSMSG.isDisplayed());
		}
		// Verifying Delete report
		if (Delete.equalsIgnoreCase("Yes")) {
			if (VerifyObject(driver, Reportgen)) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(5000);
					if (VerifyObject(driver, TAR.DOWNLOADLINK)) {
						report = true;
					}
				} while (report == false);
			}
			waitforElement(180, TAR.DELETE, driver);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, TAR.DELETE);
			} else {
				click(TAR.DELETE);
			}
			waitforElement(180, TAR.CANCEL, driver);
			JavaScriptclick(driver, TAR.CANCEL);
			waitUntilElementInvisibe(180, "//a/strong[text()='Cancel']", driver);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, TAR.DELETE);
			} else {
				click(TAR.DELETE);
			}
			waitforElement(180, TAR.POPUPDELETE, driver);
			JavaScriptclick(driver, TAR.POPUPDELETE);
			waitUntilElementInvisibe(180, "//a/strong[text()='Cancel']", driver);
			assertTrue(TAR.DELETESUCCESS.isDisplayed());
		}
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, PUL.GENERATEREPORT);
		} else {
			click(PUL.GENERATEREPORT);
		}
		waitforElement(180, TAR.SUCCESSMSG, driver);
		if (Download.equalsIgnoreCase("Yes")) {
			waitforElement(180, TAR.DOWNLOADLINK, driver);
			if (VerifyObject(driver, Reportgen)) {
				boolean report = false;
				do {
					Thread.sleep(5000);
					if (VerifyObject(driver, TAR.DOWNLOADLINK)) {
						report = true;
					}
				} while (report == false);
			}
			waitforElement(180, TAR.DOWNLOADLINK, driver);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, TAR.DOWNLOADLINK);
			} else {
				click(TAR.DOWNLOADLINK);
			}
			Thread.sleep(5000);
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(5000);
				rob.keyPress(KeyEvent.VK_ALT);
				Thread.sleep(1000);
				rob.keyPress(KeyEvent.VK_S);
				rob.keyRelease(KeyEvent.VK_ALT);
				rob.keyRelease(KeyEvent.VK_S);
			}
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				String TaxassistReports1 = Reports;
				switch (TaxassistReports1) {
				case "TaxAssistRuleReport":
					File directory = new File(".//");
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "TaxAssistRuleReport";
					System.out.println("Path" + path);
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(1000);
					new File(LatestDownloadedFile("TaxAssistRuleReport", true));
					break;
				case "Taxassistallocationtable":
					File directory1 = new File(".//");
					String path1 = directory1.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "Taxassistallocationtable";
					System.out.println("Path" + path1);
					MoveLatestDownloadFileFromOnedirtoAnother(path1);
					Thread.sleep(1000);
					new File(LatestDownloadedFile("Taxassistallocationtable", true));
					break;
				case "Taxassistlookuptable":
					File directory2 = new File(".//");
					String path2 = directory2.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "Taxassistlookuptable";
					System.out.println("Path" + path2);
					MoveLatestDownloadFileFromOnedirtoAnother(path2);
					Thread.sleep(1000);
					new File(LatestDownloadedFile("Taxassistlookuptable", true));
					break;
				}
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				String TaxassistReports2 = Reports;
				switch (TaxassistReports2) {
				case "TaxAssistRuleReport":
					new File(LatestDownloadedFile("TaxAssistRuleReport", false));
					break;
				case "Taxassistallocationtablereport":
					new File(LatestDownloadedFile("Taxassistallocationtablereport", false));
					break;
				case "Taxassistlookuptablereport":
					new File(LatestDownloadedFile("Taxassistlookuptablereport", false));
					break;
				}
			}

			String dwnldfileclmnvalues[] = FileColumn_Values.split(",");
			for (int j = 0; j < dwnldfileclmnvalues.length; j++) {
				xGetCSVCellValue(Filepath, dwnldfileclmnvalues[j]);
				assertTrue(Cell_Header.trim().equalsIgnoreCase(dwnldfileclmnvalues[j].trim()));
			}
		}

	}

	public void configurationReport(String taxpayerName) throws Exception {

		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		waitforElement(180, CR.ConfigurationLink, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CR.ConfigurationLink);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		selectByValue(CR.TAXDROPDOWN, taxpayerName);
		Thread.sleep(5000);
		click(CR.EXPORT);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "ZeroReport";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
		}

	}

	public void adminconfigurationReport(String clientName, String taxpayerName) throws Exception {

		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		xUploadAdjustmentFile("Reports");
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());

		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(2000);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement Registered = driver.findElement(
					By.xpath("//table/tbody/tr/td/div/a[contains(text(),'" + clientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr/td/div/a[contains(text(),'" + clientName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement Authorized = driver.findElement(
					By.xpath("//table/tbody/tr/td/div/a[contains(text(),'" + clientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else {
			WebElement Intilized = driver.findElement(
					By.xpath("//table/tbody/tr/td/div/a[contains(text(),'" + clientName + "')]/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Intilized);
		}
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.EXPORT, driver);
		JavaScriptclick(driver, VSC.SMBCUSTOMERREPORT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, CR.ADMINEXPORT, driver);
		click(CR.ADMINEXPORT);
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
	}

	public void ConfigurationReport(String TaxpayerName, String State_Effectivedate, String State, String City,
			String citinamevalues, String countinamesvalues, String districtnamesvalues) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		WebElement configtablelength = null;
		List<WebElement> values = null;
		String citiname, countiname, distname;
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		waitforElement(180, CR.ConfigurationLink, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CR.ConfigurationLink);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		selectByValue(CR.TAXDROPDOWN, TaxpayerName);
		Thread.sleep(5000);

		if (State_Effectivedate != "") {
			selectByValue(CR.JURINFODROPDOWN, "15 per page");
			Thread.sleep(3000);
			// Verifying effective start date
			assertTrue(driver
					.findElement(By.xpath("//div/p[text()='Jurisdiction Information']/../../table/tbody/tr/td[text()='"
							+ State.toUpperCase() + "']/../td[text()='" + State_Effectivedate + "']"))
					.isDisplayed());
		}
		if (((State) != "") && ((State_Effectivedate) == "")) {
			// Verifying Jurisdiction Info Table
			scrollDown(driver);
			JavaScriptscrolltoView(driver, CR.JURINFODROPDOWN);
			selectByValue(CR.JURINFODROPDOWN, "15 per page");
			Thread.sleep(3000);
			if (City != "") {
				if (VerifyObject(driver, "//div/p[text()='Jurisdiction Information']/../../table/tbody/tr/td[text()='"
						+ State.toUpperCase() + "']/../td/a")) {
					WebElement expand = driver.findElement(
							By.xpath("//div/p[text()='Jurisdiction Information']/../../table/tbody/tr/td[text()='"
									+ State.toUpperCase() + "']/../td/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", expand);
					Thread.sleep(3000);
					configtablelength = driver.findElement(
							By.xpath("//div/p[text()='Jurisdiction Information']/../../table/tbody/tr/td/table/tbody"));
					values = configtablelength.findElements(By.tagName("tr"));
					System.out.println("Table size is" + values.size());
					// xpand = "clicked";
				}
				if (!City.equalsIgnoreCase("Off")) {
					/*
					 * if (VerifyObject(driver,
					 * "//div/p[text()='Jurisdiction Information']/../../table/tbody/tr/td[text()='"
					 * + State.toUpperCase() + "']/../td/a")) { WebElement expand =
					 * driver.findElement(By.xpath(
					 * "//div/p[text()='Jurisdiction Information']/../../table/tbody/tr/td[text()='"
					 * + State.toUpperCase() + "']/../td/a")); ((JavascriptExecutor)
					 * driver).executeScript("arguments[0].click();", expand); Thread.sleep(3000);
					 * assertTrue(driver .findElement(By.xpath("//table/tbody/tr/td[text()='" +
					 * State.toUpperCase() +
					 * "']/../td[5][text()='Y']/../td[6][text()='Y']/../td[7][text()='Y']"))
					 * .isDisplayed()); }
					 */
					if (citinamevalues != null) {
						String citinames[] = citinamevalues.split(",");
						for (int z = 0; z < citinames.length; z++) {
							citiname = "//tbody/tr/td/table/tbody/tr/td[text()='" + citinames[z].toUpperCase()
									+ "']/../td[text()='CITY']/../td[text()='Exclude']";
							System.out.println(citiname);
							WebElement cname = driver.findElement(By.xpath(citiname));
							JavaScriptscrolltoView(driver, cname);
							assertTrue(cname.isDisplayed());
						}
					}
				}
				if (!(countinamesvalues == "")) {
					String countinames[] = countinamesvalues.split(",");
					for (int z = 0; z < countinames.length; z++) {
						countiname = "//tbody/tr/td/table/tbody/tr/td[text()='" + countinames[z].toUpperCase()
								+ "']/../td[text()='COUNTY']/../td[text()='Exclude']";
						assertTrue(driver.findElement(By.xpath(countiname)).isDisplayed());
					}
				}
				if (!(districtnamesvalues == "")) {
					String districtnames[] = districtnamesvalues.split(",");
					for (int z = 0; z < districtnames.length; z++) {
						distname = "//tbody/tr/td/table/tbody/tr/td[text()='" + districtnames[z].toUpperCase()
								+ "']/../td[text()='SALES TAX DISTRICT']/../td[text()='Exclude']";
						assertTrue(driver.findElement(By.xpath(distname)).isDisplayed());
					}
				}
			}
			if (City.equalsIgnoreCase("Off")) {
				JavaScriptscrolltoView(driver, driver.findElement(By.xpath(
						"//div/p[contains(.,'Jurisdiction Information')]/../../table/tbody/descendant::td[text()='"
								+ State.toUpperCase()
								+ "']/../td[5][text()='N']/../td[6][text()='Y']/../td[7][text()='Y']")));

				assertTrue(driver.findElement(By.xpath(
						"//div/p[contains(.,'Jurisdiction Information')]/../../table/tbody/descendant::td[text()='"
								+ State.toUpperCase()
								+ "']/../td[5][text()='N']/../td[6][text()='Y']/../td[7][text()='Y']"))
						.isDisplayed());

			}
			if (City == "") {
				JavaScriptscrolltoView(driver, driver.findElement(By.xpath(
						"//div/p[contains(.,'Jurisdiction Information')]/../../table/tbody/descendant::td[text()='"
								+ State.toUpperCase()
								+ "']/../td[5][text()='Y']/../td[6][text()='Y']/../td[7][text()='Y']")));
				assertTrue(driver.findElement(By.xpath(
						"//div/p[contains(.,'Jurisdiction Information')]/../../table/tbody/descendant::td[text()='"
								+ State.toUpperCase()
								+ "']/../td[5][text()='Y']/../td[6][text()='Y']/../td[7][text()='Y']"))
						.isDisplayed());
			}
		}
	}

	public void ConfigurationReportwithcertificates(String TaxpayerName, String execmptstate, String issjuris,
			String Jurtype) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		waitforElement(180, CR.ConfigurationLink, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CR.ConfigurationLink);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.CLIENTNAME, driver);
		selectByValue(CR.TAXDROPDOWN, TaxpayerName);
		Thread.sleep(5000);
		scrollDown(driver);
		scrollDown(driver);
		JavaScriptscrolltoView(driver, CR.Customerinfo);
		waitforElement(90, CR.CertificatesPAGINATION, driver);
		JavaScriptclick(driver, CR.CertificatesPAGINATION);
		Thread.sleep(2000);
		selectByValue(CR.CertificatesPAGINATION, "15 per page");
		Thread.sleep(2000);
		List<WebElement> list = driver
				.findElements(By.xpath("//div[5]/div/p[contains(.,'Customer Information')]/../../table/tbody/tr"));
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getText());
			String a = list.get(i).getText();
			if (a.contains("Taxable Customer")) {
				int j = i + 2;
				;
				driver.findElement(By.xpath("//tr[" + j + "]/td/div/div/a[text()='Certificates ']")).click();
				waitforElement(90, CR.CERTIFICATESTABLE, driver);
				assertTrue(issjuris.equalsIgnoreCase("Bethel"));
				assertTrue(
						driver.findElement(By.xpath("//table/tbody/tr/td[text()='" + execmptstate + "']/../td[text()='"
								+ Jurtype + "']/../td[text()='" + issjuris.toUpperCase() + "']")).isDisplayed());
				break;
			}
		}
	}

	@SuppressWarnings("unused")
	public void CustomerExceptionReport(String CustomerName, String DateAdded, String CustomerCode, String State,
			String Taxpayer_Name, String Dwnld_statename, String Reason_Type) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		CustomerExceptionReport CER = PageFactory.initElements(driver, CustomerExceptionReport.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		JavaScriptclick(driver, PUL.CustomerExceptionLink);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(2000);
		waitforElement(180, CER.TAXPAYERNAME, driver);
		waitforElement(180, CER.GENERATEBUTTON, driver);

		// verifying STATE/PROVINCE label
		JavaScriptclick(driver, CER.GENERATEBUTTON);
		waitforElement(180, CER.SUCCESS_ALERT, driver);
		waitforElement(180, CER.CUSTEXCEPTIONREPORTDWNLD, driver);
		JavaScriptclick(driver, CER.CUSTEXCEPTIONREPORTDWNLD);
		Thread.sleep(5000);
		waitforElement(180, CER.TAXPAYERNAME, driver);
		waitforElement(180, CER.GENERATEBUTTON, driver);
		// Generate the Report using Filter Customer Name
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (VerifyObject(driver, CER.INPROCESSCOMPANYCONFIG)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver, CER.INPROCESSCOMPANYCONFIG)) {
					report = true;
				}
			} while (report == false);
		}
		// Generate the Report Without Using Filter option
		if (CustomerName != "") {
			waitforElement(180, CER.CUSTOMERNAME, driver);
			selectByValue(CER.CUSTOMERNAME, CustomerName);
			click(CER.GENERATEBUTTON);
			waitforElement(180, "//div[@class='alert alert-success ng-binding ng-scope']", driver);
			waitforElement(180, CER.CUSTEXCEPTIONREPORTDWNLD, driver);
			Thread.sleep(3000);
			JavaScriptclick(driver, CER.CUSTEXCEPTIONREPORTDWNLD);
			Thread.sleep(3000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "CustomerExceptionReport";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(3000);
				LatestDownloadedFile("CustomerExceptionReport", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("CustomerExceptionReport", false);
			}
			System.out.println(Filepath);
			FileInputStream fis = new FileInputStream(Filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (int drow = 3; drow <= 6; drow++) {
				XSSFRow Rows = sheet.getRow(drow);
				XSSFCell Cell = Rows.getCell(2);
				String Customer_Names = "" + Cell;
				System.out.println(Customer_Names);
				if (DateAdded.equalsIgnoreCase("Yes")) {
					Rows = sheet.getRow(2);
					Cell = Rows.getCell(0);
					String CustomerDateAdd = "" + Cell;
					XSSFCell Cell1 = Rows.getCell(1);
					String Certificatescope = "" + Cell1;
					assertTrue(Certificatescope.equalsIgnoreCase("Certificate Scope"));
					assertTrue(CustomerDateAdd.equalsIgnoreCase("Customer Date Added"));
				}
				assertTrue(CustomerName.equalsIgnoreCase(Customer_Names));
				break;
			}
			fis.close();
		}
		// Generate the Report using Filter Customer Code
		// Thread.sleep(5000);
		waitforElement(180, CER.TAXPAYERNAME, driver);
		if (CustomerCode != "") {
			JavaScriptclick(driver, PUL.ReportsHover);
			waitforElement(90, PUL.CUSTOMEREXEMPTIONREPORTLINK, driver);
			JavaScriptclick(driver, PUL.CUSTOMEREXEMPTIONREPORTLINK);
			waitforElement(180, CER.TAXPAYERNAME, driver);
			Thread.sleep(10000);
			waitforElement(180, CER.GENERATEBUTTON, driver);
			selectByValue(CER.CUSTOMERCODE, CustomerCode);
			JavaScriptclick(driver, CER.GENERATEBUTTON);
			waitforElement(180, "//div[@class='alert alert-success ng-binding ng-scope']", driver);
			waitforElement(180, CER.CUSTEXCEPTIONREPORTDWNLD, driver);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tbody/tr[1]/td/a[contains(text(),'Download')]")).click();
			Thread.sleep(3000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "CustomerExceptionReport";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(3000);
				LatestDownloadedFile("CustomerExceptionReport", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("CustomerExceptionReport", false);
			}
			System.out.println(Filepath);
			FileInputStream fis1 = new FileInputStream(Filepath);
			XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
			XSSFSheet sheet1 = workbook1.getSheetAt(0);
			for (int drow = 3; drow <= 6; drow++) {
				XSSFRow Rows1 = sheet1.getRow(drow);
				XSSFCell Cell1 = Rows1.getCell(3);
				String Customer_Codes = "" + Cell1;
				System.out.println(Customer_Codes);
				assertTrue(CustomerCode.equalsIgnoreCase(Customer_Codes));
				break;
			}
			fis1.close();
		}
		// Thread.sleep(5000);
		waitforElement(180, CER.TAXPAYERNAME, driver);
		// Generate the Report using Filter State
		if (State != "") {
			JavaScriptclick(driver, PUL.ReportsHover);
			waitforElement(90, PUL.CUSTOMEREXEMPTIONREPORTLINK, driver);
			JavaScriptclick(driver, PUL.CUSTOMEREXEMPTIONREPORTLINK);
			Thread.sleep(10000);
			waitforElement(180, CER.TAXPAYERNAME, driver);
			waitforElement(180, CER.GENERATEBUTTON, driver);
			selectByValue(CER.STATE, State);
			JavaScriptclick(driver, CER.GENERATEBUTTON);
			waitforElement(180, "//div[@class='alert alert-success ng-binding ng-scope']", driver);
			waitforElement(180, CER.CUSTEXCEPTIONREPORTDWNLD, driver);
			Thread.sleep(3000);
			click(CER.CUSTEXCEPTIONREPORTDWNLD);
			Thread.sleep(5000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "CustomerExceptionReport";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(3000);
				LatestDownloadedFile("CustomerExceptionReport", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("CustomerExceptionReport", false);
			}
			System.out.println(Filepath);
			FileInputStream fis2 = new FileInputStream(Filepath);
			XSSFWorkbook workbook2 = new XSSFWorkbook(fis2);
			XSSFSheet sheet2 = workbook2.getSheetAt(0);
			XSSFRow Rows4 = sheet2.getRow(3);
			// Need to verify
			if (Rows4 != null) {
				XSSFCell Cell3 = Rows4.getCell(5);
				String State_Name = "" + Cell3;
				System.out.println(State_Name);
				String stateexcel = Dwnld_statename;
				System.out.println("stateexcel value is " + stateexcel);
				assertTrue(Dwnld_statename.equalsIgnoreCase(State_Name));
				fis2.close();
			}
		}
		// Thread.sleep(5000);
		waitforElement(180, CER.TAXPAYERNAME, driver);
		// Generate the Reports using filter TaxPayer Name
		if (Taxpayer_Name != "") {
			JavaScriptclick(driver, PUL.ReportsHover);
			waitforElement(90, PUL.CUSTOMEREXEMPTIONREPORTLINK, driver);
			JavaScriptclick(driver, PUL.CUSTOMEREXEMPTIONREPORTLINK);
			Thread.sleep(3000);
			waitforElement(180, CER.TAXPAYERNAME, driver);
			waitforElement(180, CER.GENERATEBUTTON, driver);
			selectByValue(CER.TAXPAYERNAME, Taxpayer_Name);
			JavaScriptclick(driver, CER.GENERATEBUTTON);
			waitforElement(180, "//div[@class='alert alert-success ng-binding ng-scope']", driver);
			waitforElement(180, CER.CUSTEXCEPTIONREPORTDWNLD, driver);
			Thread.sleep(3000);
			// String Download3 = CustomerExemptionReportsheet.getCell(Download_Link,
			// Data_Row).getContents();
			driver.findElement(By.xpath("//tbody/tr[1]/td/a[contains(text(),'Download')]")).click();
			Thread.sleep(5000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "CustomerExceptionReport";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				LatestDownloadedFile("CustomerExceptionReport", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("CustomerExceptionReport", false);
			}
			System.out.println(Filepath);
			FileInputStream fis3 = new FileInputStream(Filepath);
			XSSFWorkbook workbook5 = new XSSFWorkbook(fis3);
			XSSFSheet sheet5 = workbook5.getSheetAt(0);
			for (int drow = 2; drow <= 7; drow++) {
				XSSFRow Rows4 = sheet5.getRow(drow);
				XSSFCell Cell3 = Rows4.getCell(6);
				XSSFCell Cell4 = Rows4.getCell(7);
				XSSFCell Cell5 = Rows4.getCell(23);
				XSSFCell Cell6 = Rows4.getCell(24);
				XSSFCell Cell7 = Rows4.getCell(25);
				XSSFCell Cell8 = Rows4.getCell(26);
				String NewJurisdictionType = "" + Cell3;
				String NewIssuingJurisdiction = "" + Cell4;
				String codeclassname = "" + Cell5;
				String codeclass = "" + Cell6;
				String codeclasstype = "" + Cell7;
				String documentnumber = "" + Cell8;
				assertTrue(NewJurisdictionType.equalsIgnoreCase("Jurisdiction Type"));
				assertTrue(NewIssuingJurisdiction.equalsIgnoreCase("Issuing Jurisdiction"));
				assertTrue(codeclassname.equalsIgnoreCase("Code/Class Name"));
				assertTrue(codeclass.equalsIgnoreCase("Code/Class"));
				assertTrue(codeclasstype.equalsIgnoreCase("Code/Class Type"));
				assertTrue(documentnumber.equalsIgnoreCase("Document Number"));
				drow++;
				XSSFRow Rows5 = sheet5.getRow(drow);
				XSSFCell Cell11 = Rows5.getCell(16);
				String Taxpayer_name = "" + Cell11;
				System.out.println(Taxpayer_name);
				String TaxPName = Taxpayer_Name;
				String TP[] = TaxPName.split(" [(]");
				System.out.println(TP[0]);
				assertTrue(TP[0].equalsIgnoreCase(Taxpayer_name));
				break;
			}
			fis3.close();
		}
		waitforElement(180, CER.TAXPAYERNAME, driver);
		if (Reason_Type != "") {
			JavaScriptclick(driver, PUL.ReportsHover);
			waitforElement(90, PUL.CUSTOMEREXEMPTIONREPORTLINK, driver);
			JavaScriptclick(driver, PUL.CUSTOMEREXEMPTIONREPORTLINK);
			Thread.sleep(10000);
			waitforElement(180, CER.TAXPAYERNAME, driver);
			waitforElement(180, CER.GENERATEBUTTON, driver);
			selectByValue(CER.REASONTYPE, Reason_Type);
			JavaScriptclick(driver, CER.GENERATEBUTTON);
			waitforElement(180, CER.SUCCESS_ALERT, driver);
			waitforElement(180, CER.CUSTEXCEPTIONREPORTDWNLD, driver);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tbody/tr[1]/td/a[contains(text(),'Download')]")).click();
			Thread.sleep(5000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "CustomerExceptionReport";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(3000);
				LatestDownloadedFile("CustomerExceptionReport", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("CustomerExceptionReport", false);
			}
			System.out.println(Filepath);
			FileInputStream fis4 = new FileInputStream(Filepath);
			XSSFWorkbook workbook6 = new XSSFWorkbook(fis4);
			XSSFSheet sheet6 = workbook6.getSheetAt(0);
			for (int drow = 3; drow <= 7; drow++) {
				XSSFRow Rows6 = sheet6.getRow(drow);
				XSSFCell Cell12 = Rows6.getCell(19);
				String Reasoncode = "" + Cell12;
				System.out.println(Reasoncode);
				assertTrue(Reason_Type.equalsIgnoreCase(Reasoncode));
				break;
			}
			fis4.close();
		}
	}

	public void VerifyCustomTaxabilityReports(String Download_Link, String Date) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Robot rob = new Robot();
		int TaxLength;
		WebElement TaxabilityReport, TaxReportLink;
		String DownloadFilepath = xDownloadFiles("TaxabilityReports");
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		int count = 0;
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.TaxabilityReport, driver);
		JavaScriptclick(driver, PUL.TaxabilityReport);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		// rob.mouseMove(0,0);
		waitforElement(180, PUL.TAXABILITYCOMPDD, driver);
		selectByValue(PUL.TAXABILITYCOMPDD, Download_Link);
		Thread.sleep(5000);
		// Verifying for Taxability Report Link
		if (VerifyObject(driver, PUL.NOTAXABILITYREPORT)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(30000);
				count = count + 1;
				if (count == 15) {
					break;
				}
				if (!VerifyObject(driver, PUL.NOTAXABILITYREPORT)) {
					report = true;
				}
			} while (report == false);
		}
		TaxabilityReport = driver
				.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/a[contains(.,'Report')]"));
		if (VerifyObject(driver, TaxabilityReport)) {
			click(TaxabilityReport);
			waitforElement(180, PUL.TAXABILITYREPORTMINUS, driver);
		}
		Thread.sleep(7000);
		List<WebElement> TaxabilityReportsLength = driver
				.findElements(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul/li/a"));
		TaxLength = TaxabilityReportsLength.size();
		if (TaxLength == 1) {
			TaxReportLink = driver.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[1]/li/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", TaxReportLink);
			TaxabilityDownloadlink = driver
					.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[1]/li/a")).getText();
		} else {
			TaxReportLink = driver.findElement(
					By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[" + TaxLength + "]/li/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", TaxReportLink);
			TaxabilityDownloadlink = driver
					.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[" + TaxLength + "]/li/a"))
					.getText();
		}
		Thread.sleep(7000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
					+ "TaxabilityReports";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("TaxabilityReports", true);
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
			LatestDownloadedFile("TaxabilityReports", false);
		}
		FileInputStream fis = new FileInputStream(Filepath);
		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
		Thread.sleep(7000);
		int Num_Sheets = workbook.getNumberOfSheets();

		File file = new File(".//");
		String expectedfile = file.getCanonicalPath() + "\\InputTestData\\Reports\\ProductTaxabilityReport_Custom.xlsx";
		for (int j = 0; j < Num_Sheets; j++) {
			String sheetname = workbook.getSheetName(j);
			XLSXtoXLSXFilesCompare(expectedfile, Filepath, sheetname);
		}
	}

	public void downloadconfigreport(String TaxpayerName, String comparefiles, String Verify_ConfigReport,
			String Is_Location) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		// waitforText(90,CR.Reports_Overview_Text, driver);
		JavaScriptclick(driver, CR.ConfigurationLink);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		selectByValue(CR.TAXDROPDOWN, TaxpayerName);
		Thread.sleep(5000);
		JavaScriptclick(driver, CR.EXPORTBUTON);
		Thread.sleep(8000);
		// assertTrue(driver.findElement(By.xpath(PUL.OKERROR_XPATH)).isDisplayed());
		// Comparing Downloaded File
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
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
		File directory = new File(".\\");
		String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
		if (comparefiles != "") {
			xr_xCompareExcelFiles(Filepath, DirName + "\\" + comparefiles);
		}
		Thread.sleep(5000);
		String s = xGetXLSXCellValue1(Filepath, "TaxArea ID", "Taxpayer Locations");
		if (Verify_ConfigReport.equalsIgnoreCase("YES")) {
			if (Is_Location.equalsIgnoreCase("YES")) {
				assertTrue(!s.isEmpty());
			} else {
				assertTrue(s.isEmpty());
			}
		}
	}

	public void TaxibilitycategorymappingReport(String TaxabilityHierarchy, String TotalCustomersMapped,
			String TestCustomers, String LiveCustomers, String VertexProvided) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Metrics MC = PageFactory.initElements(driver, Metrics.class);
		click(MC.METRICSLINK);
		waitforElement(180, MC.TCDNLD, driver);
		assertTrue(MC.TCDNLD.isDisplayed());
		Thread.sleep(5000);
		click(MC.TCDNLD);
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

	public void VerifyReportsafterUndoGoLive(String TaxPayer, String MTPP, String TaxPayer_Code) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		PageFactory.initElements(driver, GoLiveVerify.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		String LiveTaxPayer;
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforText(180, CNP.BEGINREMOVAL, driver);
		LiveTaxPayer = TaxPayer;
		if (MTPP.equalsIgnoreCase("Yes")) {
			assertTrue(driver.findElement(By.xpath("//button[contains(.,' Go Live')]")).isDisplayed());
		} else {
			assertTrue(driver.findElement(By.xpath("//h2[contains(.,'" + LiveTaxPayer
					+ "')]/../../../../../div/div/div/button[contains(.,' Go Live')]")).isDisplayed());
		}
		JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
		waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, MTR.LTAXPAYER, driver);
		selectByValue(MTR.LTAXPAYER, TaxPayer_Code);
		Thread.sleep(5000);
		if (!VerifyObject(driver, MTR.DownlaodbuttonUndoGoLive)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(90, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitforElement(180, TR.TAXPAYERSELECT, driver);
		selectByValue(TR.TAXPAYERSELECT, TaxPayer_Code);
		waitforElement(180, PUL.createNewReport, driver);
		if (!VerifyObject(driver, PUL.TransactionsDownload)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		JavaScriptclick(driver, PUL.SettingsHover);
		waitforElement(180, PUL.subscriptionType, driver);
		if (!VerifyObject(driver, PUL.TransactionsCountUndoGoLive)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	public void FilingCalendarReport(String TaxpayerName, String Return_Pre_Payment, String Download_PrintRprt)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		new Robot();
		FilingCalendarReportPage FCR = PageFactory.initElements(driver, FilingCalendarReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		xDownloadFiles("Reports");
		PrepaymentReturn = "";
		waitforElement(30, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		// waitforText(90,CR.Reports_Overview_Text, driver);
		waitforElement(180, FCR.FilingCalendarLink, driver);
		JavaScriptclick(driver, FCR.FilingCalendarLink);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, FCR.TAXPAYERDROPDOWN, driver);
		/*
		 * Select Company = new Select(FCR.TAXPAYERDROPDOWN);
		 * Company.selectByVisibleText(TaxpayerName);
		 */
		if (!TaxpayerName.isEmpty()) {
			selectByValue(FCR.TAXPAYERDROPDOWN, TaxpayerName);
		}
		Thread.sleep(2000);
		if (Return_Pre_Payment != "") {
			if (!VerifyObject(driver, FCR.RETURNPREPAYMENT)) {
				boolean con = false;
				int count = 0;
				do {
					Thread.sleep(30000);
					driver.navigate().refresh();
					Select Company1 = new Select(FCR.TAXPAYERDROPDOWN);
					Company1.selectByVisibleText(TaxpayerName);
					count = count + 1;
					// if(count==15){
					// status="Fail;There is no retuurn forms are not displaying";
					// break;
					// }
					if (!VerifyObject(driver, FCR.RETURNPREPAYMENT)) {
						con = true;
					}
				} while (con == false);
			}

			String Return_PrePayment = getText(FCR.RETURNPREPAYMENT);
			if (Return_Pre_Payment.equalsIgnoreCase(Return_PrePayment)) {
				PrepaymentReturn = Return_PrePayment + ";";
				assertTrue(FCR.RPREPAYMENTLBL.isDisplayed());
				assertTrue(FCR.DUEDATELBL.isDisplayed());
			}
		} else {
			assertTrue(FCR.MESSAGENOUPCOMING.isDisplayed());
		}
		if (Download_PrintRprt.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, FCR.DWNLDPRINTREPRT);
			// Comparing Downloaded File
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
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
			File directory = new File(".\\");
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
			PDFComparison(Filepath, DirName + "\\" + FileName.replace("]", ""));
			Thread.sleep(5000);
		}
	}

	public void TaxabilityImpactReport(String reportName, String productName, String taxType, String productMapping,
			String generateReport, String downloadReport, String deleteReport) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		TaxabilityImpactPage TIMP = PageFactory.initElements(driver, TaxabilityImpactPage.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		String reportNameRND = reportName + RND();

		waitforElement(180, TIMP.TAXABILITYIMPACTREPORT, driver);
		JavaScriptclick(driver, TIMP.TAXABILITYIMPACTREPORT);

		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TIMP.CONFIGURENEWREPORT, driver);
		JavaScriptclick(driver, TIMP.CONFIGURENEWREPORT);

		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TIMP.ADDANEWPRODUCT, driver);
		type(TIMP.COMPANYREPORTNAME, reportNameRND);
		click(TIMP.ADDANEWPRODUCT);

		waitforElement(180, TIMP.TBPRODUCTIDENTIFIER, driver);
		type(TIMP.TBPRODUCTIDENTIFIER, productName);

		if (taxType.equalsIgnoreCase("Buyer")) {
			selectByValue(TIMP.TAXTYPE, "Buyer");
		} else {
			selectByValue(TIMP.TAXTYPE, "Seller");
		}

		click(PP.PRODUCTS_QUICK_SEARCH);
		Thread.sleep(500);
		type(PP.PRODUCTS_QUICK_SEARCH, productMapping);
		Thread.sleep(500);
		sendkeysTab(PP.PRODUCTS_QUICK_SEARCH);

		click(TIMP.SAVEANDCLOSE);
		waitforElement(180, TIMP.RETURNTOREPORTLISTING, driver);

		if (generateReport.equalsIgnoreCase("generatereport")) {
			click(TIMP.GENERATEREPORT);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, TIMP.CONFIGURENEWREPORT, driver);
		}

		if (downloadReport.equalsIgnoreCase("downloadreport")) {
			String downloadReportLink = "//td[text()= '" + reportNameRND
					+ "']/..//a[text()='Download'  and not(@class='ng-scope')]";
			WebElement downloadReportLinkElement = null;
			boolean report = false;
			int count = 0;
			do {
				driver.navigate().refresh(); // For O Series sync refreshing the page to get download link
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				count = count + 1;
				if (count == 10) {
					break;
				}
				if (VerifyObject(driver, downloadReportLink)) {
					report = true;
					downloadReportLinkElement = driver.findElement(By.xpath(downloadReportLink));
				}

			} while (report == false);

			waitforElement(180, downloadReportLinkElement, driver);
			JavaScriptclick(driver, downloadReportLinkElement);

		}
		// Deleting Report
		if (deleteReport.equalsIgnoreCase("deletereport")) {
			Thread.sleep(1000);
			WebElement deleteReportLinkElement = driver
					.findElement(By.xpath("//td[text()= '" + reportNameRND + "']/..//a[text()='Delete']"));
			waitforElement(180, deleteReportLinkElement, driver);
			JavaScriptclick(driver, deleteReportLinkElement);
			Thread.sleep(2000);
			waitforElement(180, TIMP.DELETEREPORTCONFIRM, driver);
			JavaScriptclick(driver, TIMP.DELETEREPORTCONFIRM);
			waitUntilElementInvisibe(30, TIMP.DELETEREPORTCONFIRM_XPATH, driver);
		}

	}

	public void VerifyTaxabilityReports(String Verify_No_TaxabilityReport, String Download_Link, String Date,
			String WorkSheet_Names, String CopyRight_Section, String Qualifying_Condition, String SheetName,
			String StateName, String Category_type, String Sub_Category, String QC_Status) throws Exception {
		Robot rob = new Robot();
		int TaxLength, count = 0;
		String path;
		WebElement TaxabilityReport, TaxReportLink;
		String DownloadFilepath = xDownloadFiles("TaxabilityReports");
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		rob.mouseMove(0, 0);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		waitforElement(90, CR.TaxabilityReportText, driver);
		JavaScriptclick(driver, PUL.TaxabilityReport);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.TAXABILITYCOMPDD, driver);
		if (Verify_No_TaxabilityReport != "") {
			if (Verify_No_TaxabilityReport.equalsIgnoreCase("Yes")) {
				assertTrue(PUL.NOTAXABILITYREPORTS.isDisplayed());
			}
		} else {
			selectByValue(PUL.TAXABILITYCOMPDD, Download_Link);
			Thread.sleep(5000);
			// Verifying for Taxability Report Link
			if (VerifyObject(driver, PUL.NOTAXABILITYREPORT)) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(30000);
					count = count + 1;
					if (count == 15) {
						break;
					}
					if (!VerifyObject(driver, PUL.NOTAXABILITYREPORT)) {
						report = true;
					}
				} while (report == false);
			}
			Thread.sleep(5000);
			TaxabilityReport = driver
					.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/a[contains(.,'Report')]"));
			if (VerifyObject(driver, TaxabilityReport)) {
				click(TaxabilityReport);
				waitforElement(180, PUL.TAXABILITYREPORTMINUS, driver);
			}
			Thread.sleep(7000);
			List<WebElement> TaxabilityReportsLength = driver
					.findElements(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul/li/a"));
			TaxLength = TaxabilityReportsLength.size();
			if (TaxLength == 1) {
				TaxReportLink = driver
						.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[1]/li/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", TaxReportLink);
				TaxabilityDownloadlink = driver
						.findElement(By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[1]/li/a")).getText();
			} else {
				TaxReportLink = driver.findElement(
						By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[" + TaxLength + "]/li/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", TaxReportLink);

				TaxabilityDownloadlink = driver
						.findElement(
								By.xpath("//tbody/tr/td[contains(.,'" + Date + "')]/../td/ul[" + TaxLength + "]/li/a"))
						.getText();
			}
			Thread.sleep(7000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "TaxabilityReports";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				LatestDownloadedFile("TaxabilityReports", true);
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
				LatestDownloadedFile("TaxabilityReports", false);
			}
			FileInputStream fis = new FileInputStream(Filepath);
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
			Thread.sleep(7000);
			int Num_Sheets = workbook.getNumberOfSheets();
			for (int j = 0; j < Num_Sheets; j++) {
				String sheetname = workbook.getSheetName(j);
				System.out.println(sheetname);
				(WorkSheet_Names).split(";");
			}
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			int Num_rows = sheet.getLastRowNum() - 3;
			int qc = sheet.getLastRowNum() - 8;
			org.apache.poi.ss.usermodel.Row Row = sheet.getRow(Num_rows);
			org.apache.poi.ss.usermodel.Row Row_qc = sheet.getRow(qc);
			Cell atColumn = Row.getCell(0);
			Cell atColumn_QC = Row_qc.getCell(0);
			String Column_Value = atColumn.getStringCellValue();
			String Column_Value_QC = atColumn_QC.getStringCellValue();
			String strvertexinc = Column_Value.split("\\.")[0];
			String strvertexinc1 = Column_Value_QC.split("\\.")[0];
			CharSequence cs1 = CopyRight_Section;
			CharSequence cs2 = Qualifying_Condition;
			System.out.println(cs2);
			boolean hasState = strvertexinc.contains(cs1);
			boolean hasQC = strvertexinc1.contains(cs2);
			assertTrue(hasQC);
			assertTrue(hasState);
			fis.close();
			FileInputStream fis1 = new FileInputStream(Filepath);
			String[] Country = { "Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California", "Colorado",
					"Connecticut", "Delaware", "District of Columbia", "F.S. of Micronesia", "Florida", "Georgia",
					"Guam", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
					"Maine", "Marshall Islands", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
					"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
					"New York", "North Carolina", "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma",
					"Oregon", "Palau", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota",
					"Tennessee", "Texas", "United States Minor Outlying Islands", "Utah", "Vermont", "Virgin Islands",
					"Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "Canada" };
			XSSFWorkbook Juris_workbook = new XSSFWorkbook(fis1);
			XSSFSheet Juris_sheet = Juris_workbook.getSheetAt(3);
			System.out.println(Juris_workbook.getSheetAt(2).getSheetName());
			int Juris_rows = Juris_sheet.getLastRowNum();
			XSSFRow HeaderRow = Juris_sheet.getRow(1);
			int columnsCount = HeaderRow.getLastCellNum();
			for (int k = 2; k <= Juris_rows; k++) {
				XSSFRow Rows = Juris_sheet.getRow(k);
				for (int col = 0; col < columnsCount; col++) {
					XSSFCell atColumns = HeaderRow.getCell(col);
					String Columns_Value = atColumns.getStringCellValue();
					if (Columns_Value.equalsIgnoreCase("Jurisdiction Name")) {
						switch (Rows.getCell(col).getCellType()) {
						case Cell.CELL_TYPE_STRING:
							String Str_Value = Rows.getCell(col).getStringCellValue();
							assertTrue(Str_Value.equalsIgnoreCase(Country[k - 2]));
							break;
						}
					}
				}
			}
			fis1.close();
			if (Qualifying_Condition != null) {
				String sheetvalue = SheetName;
				String statevalue = StateName;
				String categoryvalue = Category_type;
				String SubCategory = Sub_Category;
				String QCStatus = QC_Status;
				String qcsts = xGetCustomizedXLSXCellValue(Filepath, sheetvalue, statevalue, categoryvalue, SubCategory,
						QCStatus);
				assertTrue(qcsts.equalsIgnoreCase("Yes"));
			} else {
				String sheetvalue = SheetName;
				String statevalue = StateName;
				String categoryvalue = Category_type;
				xGetCustomizedXLSXCellValue(Filepath, sheetvalue, statevalue, categoryvalue);
			}
		}
	}

	public void DiscrepancyReport() throws IOException, InterruptedException {

		try {
			File directory = new File(".//");
			String Actualpath = null;
			String Expectedpath = null;
			XSSFWorkbook wb;
			wb = new XSSFWorkbook();
			String DataTime = xGetDateTime();
			XSSFSheet sheet = wb.createSheet();
			String line = null;
			int row = -1;
			File file = new File(".//");
			Actualpath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual\\AdjustmentFiles\\";
			Expectedpath = directory.getCanonicalPath() + "\\"
					+ "InputTestData\\AdjustmentFiles\\FedTaxAdjustmentFiles\\";
			// status=discrepancy(Actualpath,Expectedpath,status);
			discrepancy(Actualpath, Expectedpath);
			String nm = file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy Report.csv";
			String TempExcel = file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy_Report.xlsx";
			File RDCSV = new File(
					file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy Report.csv");
			if (RDCSV.exists()) {
				Path RSource = Paths
						.get(file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy Report.csv");
				Path RTarget = Paths.get(file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\"
						+ "Discrepancy Report" + DataTime + ".csv");
				Files.copy(RSource, RTarget);
			}
			File RDExcel = new File(
					file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy_Report.xlsx");
			if (RDExcel.exists()) {
				Path RSource = Paths
						.get(file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy_Report.xlsx");
				Path RTarget = Paths.get(file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\"
						+ "Discrepancy_Report" + DataTime + ".xlsx");
				Files.copy(RSource, RTarget);
			}
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(nm));
			while ((line = reader.readLine()) != null) {
				String str[] = line.split(",");
				row++;
				XSSFRow currentRow = sheet.createRow(row);
				for (int i = 0; i < str.length; i++) {
					currentRow.createCell(i).setCellValue(str[i]);
				}
			}
			FileOutputStream fileOutputStream = new FileOutputStream(TempExcel);
			wb.write(fileOutputStream);
			fileOutputStream.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

	}


	public void AdjustmentFileComparision(String Actualpath,String Expectedpath) throws IOException, InterruptedException {

		try {
			File directory = new File(".//");
			//String Actualpath = null;
			//String Expectedpath = null;
			XSSFWorkbook wb;
			wb = new XSSFWorkbook();
			String DataTime = xGetDateTime();
			XSSFSheet sheet = wb.createSheet();
			String line = null;
			int row = -1;
			File file = new File(".//");
			Actualpath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual\\AdjustmentFiles\\";
			Expectedpath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected\\AdjustmentFiles\\";
			// status=discrepancy(Actualpath,Expectedpath,status);
			discrepancy(Actualpath, Expectedpath);
			String nm = file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy Report.csv";
			String TempExcel = file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy_Report.xlsx";
			File RDCSV = new File(
					file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy Report.csv");
			if (RDCSV.exists()) {
				Path RSource = Paths
						.get(file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy Report.csv");
				Path RTarget = Paths.get(file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\"
						+ "Discrepancy Report" + DataTime + ".csv");
				Files.copy(RSource, RTarget);
			}
			File RDExcel = new File(
					file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy_Report.xlsx");
			if (RDExcel.exists()) {
				Path RSource = Paths
						.get(file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\" + "Discrepancy_Report.xlsx");
				Path RTarget = Paths.get(file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\"
						+ "Discrepancy_Report" + DataTime + ".xlsx");
				Files.copy(RSource, RTarget);
			}
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(nm));
			while ((line = reader.readLine()) != null) {
				String str[] = line.split(",");
				row++;
				XSSFRow currentRow = sheet.createRow(row);
				for (int i = 0; i < str.length; i++) {
					currentRow.createCell(i).setCellValue(str[i]);
				}
			}
			FileOutputStream fileOutputStream = new FileOutputStream(TempExcel);
			wb.write(fileOutputStream);
			fileOutputStream.close();

		} catch (Exception ex) {
			ex.getMessage();
		}

	}
	
	
	public void returnsenddateinReports(String taxpayerName, String returnName) throws Exception {

		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(60, CR.Reports_Overview, driver);
		waitforElement(60, CR.ConfigurationLink, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CR.ConfigurationLink);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		selectByValue(CR.TAXDROPDOWN, taxpayerName);
		Thread.sleep(5000);

		String returnendDate = driver.findElement(By.xpath("//td[contains(.,'" + returnName + "')]/../td[9]"))
				.getText();
		System.out.println("Return End Date in Configuration Report is:" + returnendDate);

		assertEquals(returnendDate, endDate);

	}

	public void returnsenddatenullinReports(String taxpayerName, String returnName) throws Exception {

		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(60, CR.Reports_Overview, driver);
		waitforElement(60, CR.ConfigurationLink, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, CR.ConfigurationLink);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		selectByValue(CR.TAXDROPDOWN, taxpayerName);
		Thread.sleep(5000);

		String returnendDate = driver.findElement(By.xpath("//td[contains(.,'" + returnName + "')]/../td[9]"))
				.getText();
		System.out.println("Return End Date in Configuration Report is:" + returnendDate);

		assertEquals(returnendDate, "");

	}

}