package common.libraries.customer;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.pages.AdminUndoAdjustmentFile;
import common.pages.ConfigurationReportPage;
import common.pages.ConfigurePage;
import common.pages.LoginPage;
import common.pages.LookupTaxRates;
import common.pages.ProfessionalUserLinks;
import common.pages.RegistrationPage;
import common.pages.TransactionReportPage;
import common.pages.VertexSMBCustomers;

public class TransactionReport extends CommonFunctions {

	private WebDriver driver;

	public TransactionReport(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);

	public void TransactionDetailReport(String Taxpayer, String Start_Date, String End_Date) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		waitforElement(60, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, TR.SELECTDD, driver);
		waitforElement(180, TR.CANCELBUTTON, driver);
		selectByValue(TR.SELECTDD, Taxpayer);
		waitforElement(180, TR.CANCELBUTTON, driver);
		if (Start_Date != "") {
			type(TR.STARTDATE, Start_Date);
			sendkeysTab(TR.STARTDATE);
		}
		if (End_Date != "") {
			type(TR.ENDDATE, End_Date);
			sendkeysTab(TR.ENDDATE);
		}
		JavaScriptclick(driver, TR.TRANSACTIONDETAILS);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.SUBMITBUTTON);

		Thread.sleep(10000);
		if (VerifyObject(driver, TR.lineitemexceedtransactiondetail)) {

			assertTrue(TR.lineitemexceedtransactiondetail.isDisplayed());
			click(TR.OkBtn);

		} else if (VerifyObject(driver, TR.SUCCESSFULMESSAGE)) {
			waitforElement(360, TR.SUCCESSFULMESSAGE, driver);
			assertTrue(TR.SUCCESSFULMESSAGE.isDisplayed());
			assertTrue(TR.ReportProgress.isDisplayed());
		} else {
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='startDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='endDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
		}
	}

	public void TransactionAbbrevitedReport(String Taxpayer, String Start_Date, String End_Date) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		waitforElement(60, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, TR.SELECTDD, driver);
		waitforElement(180, TR.CANCELBUTTON, driver);
		selectByValue(TR.SELECTDD, Taxpayer);
		waitforElement(180, TR.CANCELBUTTON, driver);
		if (Start_Date != "") {
			type(TR.STARTDATE, Start_Date);
			sendkeysTab(TR.STARTDATE);
		}
		if (End_Date != "") {
			type(TR.ENDDATE, End_Date);
			sendkeysTab(TR.ENDDATE);
		}
		JavaScriptclick(driver, TR.TRANSACTIONDETAILSABBREVIATED);
		JavaScriptclick(driver, TR.SUBMITBUTTON);

		if (VerifyObject(driver, TR.SUCCESSFULMESSAGE)) {
			waitforElement(360, TR.SUCCESSFULMESSAGE, driver);
			assertTrue(TR.SUCCESSFULMESSAGE.isDisplayed());
			// assertTrue(TR.ReportProgress.isDisplayed());
		} else {
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='startDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='endDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
		}
	}

	public void JurisdictionSummaryReport(String Taxpayer, String Start_Date, String End_Date) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		waitforElement(60, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, TR.SELECTDD, driver);
		waitforElement(180, TR.CANCELBUTTON, driver);
		selectByValue(TR.SELECTDD, Taxpayer);
		waitforElement(180, TR.CANCELBUTTON, driver);
		if (Start_Date != "") {
			type(TR.STARTDATE, Start_Date);
			sendkeysTab(TR.STARTDATE);
		}
		if (End_Date != "") {
			type(TR.ENDDATE, End_Date);
			sendkeysTab(TR.ENDDATE);
		}
		JavaScriptclick(driver, TR.JURISDICTIONSUMMARYREPORT);
		JavaScriptclick(driver, TR.SUBMITBUTTON);

		if (VerifyObject(driver, TR.SUCCESSFULMESSAGE)) {
			waitforElement(360, TR.SUCCESSFULMESSAGE, driver);
			assertTrue(TR.SUCCESSFULMESSAGE.isDisplayed());
			assertTrue(TR.ReportProgress.isDisplayed());
		} else {
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='startDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='endDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
		}
	}

	public void DocumentSummaryByJurisdiction(String Taxpayer, String Start_Date, String End_Date) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		waitforElement(60, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, TR.SELECTDD, driver);
		waitforElement(180, TR.CANCELBUTTON, driver);
		selectByValue(TR.SELECTDD, Taxpayer);
		waitforElement(180, TR.CANCELBUTTON, driver);
		if (Start_Date != "") {
			type(TR.STARTDATE, Start_Date);
			sendkeysTab(TR.STARTDATE);
		}
		if (End_Date != "") {
			type(TR.ENDDATE, End_Date);
			sendkeysTab(TR.ENDDATE);
		}
		JavaScriptclick(driver, TR.DOCUMENTSUMMARYJURISDICTION);
		JavaScriptclick(driver, TR.SUBMITBUTTON);

		if (VerifyObject(driver, TR.SUCCESSFULMESSAGE)) {
			waitforElement(360, TR.SUCCESSFULMESSAGE, driver);
			assertTrue(TR.SUCCESSFULMESSAGE.isDisplayed());
			assertTrue(TR.ReportProgress.isDisplayed());
		} else {
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='startDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='endDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
		}
	}

	public void TransactionSummaryReport(String Taxpayer, String Start_Date, String End_Date) throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		waitforElement(60, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, TR.SELECTDD, driver);
		waitforElement(180, TR.CANCELBUTTON, driver);
		selectByValue(TR.SELECTDD, Taxpayer);
		waitforElement(180, TR.CANCELBUTTON, driver);
		assertTrue(TR.CustomReportlabel.isDisplayed());
		assertTrue(TR.StandardReportlabel.isDisplayed());
		if (Start_Date != "") {
			type(TR.STARTDATE, Start_Date);
			sendkeysTab(TR.STARTDATE);
		}
		if (End_Date != "") {
			type(TR.ENDDATE, End_Date);
			sendkeysTab(TR.ENDDATE);
		}
		JavaScriptclick(driver, TR.TRANSACTIONSUMMARY);
		JavaScriptclick(driver, TR.SUBMITBUTTON);
		Thread.sleep(5000);

		if (VerifyObject(driver, TR.SUCCESSFULMESSAGE)) {
			waitforElement(360, TR.SUCCESSFULMESSAGE, driver);
			assertTrue(TR.SUCCESSFULMESSAGE.isDisplayed());
			assertTrue(TR.ReportProgress.isDisplayed());
		} else {
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='startDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='endDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
		}
	}

	public void CustomTransactionReport(String Taxpayer, String Start_Date, String End_Date, String TemplateName)
			throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		waitforElement(60, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(60, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(60, "html[@class='nprogress-busy']", driver);
		waitforElement(60, TR.CREATEBUTTON, driver);
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitUntilElementInvisibe(60, "html[@class='nprogress-busy']", driver);
		waitforElement(60, TR.SELECTDD, driver);
		waitforElement(60, TR.CANCELBUTTON, driver);
		selectByValue(TR.SELECTDD, Taxpayer);
		waitforElement(60, TR.CANCELBUTTON, driver);
		assertTrue(TR.CustomReportlabel.isDisplayed());
		assertTrue(TR.StandardReportlabel.isDisplayed());
		if (Start_Date != "") {
			type(TR.STARTDATE, Start_Date);
			sendkeysTab(TR.STARTDATE);
		}
		if (End_Date != "") {
			type(TR.ENDDATE, End_Date);
			sendkeysTab(TR.ENDDATE);
		}
		driver.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']/../input"))
				.click();
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.SUBMITBUTTON);
		Thread.sleep(5000);
		if (VerifyObject(driver, TR.SUCCESSFULMESSAGE)) {
			waitforElement(360, TR.SUCCESSFULMESSAGE, driver);
			assertTrue(TR.SUCCESSFULMESSAGE.isDisplayed());
			assertTrue(TR.ReportProgress.isDisplayed());
		} else {
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='startDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='endDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
		}
	}

	public void VerifyCustomReportGrid(String Taxpayer) throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		waitforElement(60, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(60, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(60, "html[@class='nprogress-busy']", driver);
		waitforElement(60, TR.CREATEBUTTON, driver);
		selectByValue(TR.TAXPAYERSELECT, Taxpayer);
		assertTrue(TR.generationdate.isDisplayed());
		assertTrue(TR.companyname.isDisplayed());
		assertTrue(TR.reporttype.isDisplayed());
		assertTrue(TR.reportingstartdate.isDisplayed());
		assertTrue(TR.reportingenddate.isDisplayed());
		assertTrue(TR.stateteritory.isDisplayed());
	}

	public void VerifyTransReport(String Taxpayer, String Period_StartDate, String Period_EndDate, String Report_type)
			throws Exception {
		Robot rob = new Robot();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		int count = 0;
		String downloadLink, Reportgen;
		File directory = new File(".//");
		WebElement TranLink;
		String path;
		waitforElement(180, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		waitforElement(360, TR.TAXPAYERSELECT, driver);
		waitforElement(180, "//select[@id='companyList']/optgroup/option[contains(.,'" + Taxpayer + "')]", driver);
		selectByValue(TR.TAXPAYERSELECT, Taxpayer);
		waitforElement(180, TR.DOWNLOAD, driver);
		Thread.sleep(5000);
		downloadLink = "//tr/td[text()='" + Period_StartDate + "']/../td[text()='" + Period_EndDate
				+ "']/../td[contains(.,'" + Report_type + "')]/../td/a";
		Reportgen = "//tbody/tr[@class='ng-hide']/td/span/i[text()='Report generation is in progress ...']";
		if (!VerifyObject(driver, Reportgen)) {
			boolean report = false;
			do {
				Thread.sleep(30000);
				count = count + 1;
				if (count == 50) {
					break;
				}
				if (VerifyObject(driver, Reportgen)) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, Reportgen)) {
			if (Browser.equalsIgnoreCase("IE")) {
				TranLink = driver.findElement(By.xpath(downloadLink));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", TranLink);
				rob.keyPress(KeyEvent.VK_ALT);
				Thread.sleep(1000);
				rob.keyPress(KeyEvent.VK_S);
				rob.keyRelease(KeyEvent.VK_ALT);
				rob.keyRelease(KeyEvent.VK_S);
			} else {
				driver.findElement(By.xpath(downloadLink)).click();
			}
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "TransactionReport";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				new File(LatestDownloadedFile("TransactionReport", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				new File(LatestDownloadedFile("TransactionReport", false));
			}

		}
	}

	public void CustomTransactionReportBeforeAddComany() throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		assertTrue(TR.CustomTransactionsDetialLabel.isDisplayed());
		JavaScriptclick(driver, TR.CustomTransactionLink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		Thread.sleep(5000);
		assertTrue(TR.CustomerhasnocommittedCompanies.isDisplayed());
	}

	// Verify Custom Transaction Detail Report link not displaying under Reports tab

	public void customreportlinknotdisplay() throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);

		if (!VerifyObject(driver, TR.CustomTransactionsDetialLabel)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}

		if (!VerifyObject(driver, TR.CustomTransactionLink)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	public void CustomTransactionReport(String Taxpayer, String State, String StartDate, String EndDate,
			String TemplateName, String FieldItem, String ColumnName, String ColumnName_abovemaxlength,
			String Column_Maxlength) throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);

		// Wait 15 minutes for transaction data when HVR on
		/*
		 * Thread.sleep(900000); driver.navigate().refresh(); if(VerifyObject(driver,
		 * LP.USERNAME)){ bussFunction.Login("Login;Login;3"); waitforElement(360,
		 * LP.VTMSACCOUNT, driver); }
		 */
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(60, "html[@class='nprogress-busy']", driver);
		waitforElement(60, TR.CREATEBUTTON, driver);
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		Thread.sleep(2000);
		assertTrue(TR.CustomTransactionDetailReportRequestlabel.isDisplayed());
		waitforElement(180, TR.TAXPAYERSELECT, driver);
		assertTrue(TR.SelectCompanyLabelinLandingPage.isDisplayed());
		selectByValue(TR.TAXPAYERSELECT, Taxpayer);
		assertTrue(TR.CompanyStatuslabel.isDisplayed());
		JavaScriptclick(driver, TR.Createnewcustomtransactionreport);
		waitforElement(180, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(5000);
		assertTrue(TR.CustomTransactionDetailReportRequestlabel.isDisplayed());
		selectByValue(TR.CompanyDropdown, Taxpayer);
		assertTrue(TR.CompanyDropdown.isDisplayed());
		selectByValue(TR.StateDropdown, State);
		assertTrue(TR.StateDropdown.isDisplayed());
		type(TR.StartDateTextBox, "invalidstartdate");
		Thread.sleep(1000);
		sendkeysTab(TR.StartDateTextBox);
		Thread.sleep(1000);
		assertTrue(TR.StartDateTextBox.isDisplayed());
		clear(TR.StartDateTextBox);
		Thread.sleep(1000);
		type(TR.StartDateTextBox, StartDate);
		assertTrue(TR.StartDateTextBox.isDisplayed());
		type(TR.EndDateTextBox, "invalidenddate");
		Thread.sleep(1000);
		sendkeysTab(TR.EndDateTextBox);
		Thread.sleep(1000);
		clear(TR.EndDateTextBox);
		Thread.sleep(1000);
		type(TR.EndDateTextBox, EndDate);
		assertTrue(TR.EndDateTextBox.isDisplayed());
		JavaScriptclick(driver, TR.NewCustomTransactionDetailReportTemplatelnk);
		waitforElement(180, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(3000);
		assertTrue(TR.NEWCUSTOMTRANSACTIONDETAILREPORTTEMPLATE.isDisplayed());
		assertTrue(TR.Backbutton.isDisplayed());
		type(TR.TemplatenameInputBox, TemplateName);

		assertTrue(TR.Field.isDisplayed());
		assertTrue(TR.Column.isDisplayed());
		assertTrue(TR.UpArrow.isDisplayed());
		assertTrue(TR.DownArrow.isDisplayed());
		assertTrue(TR.RemoveIcon.isDisplayed());
		assertTrue(TR.DefaultRowOne.isDisplayed());
		assertTrue(TR.DefaultRowOne.isDisplayed());
		assertTrue(TR.AddField.isDisplayed());
		assertTrue(TR.RemoveAllFields.isDisplayed());
		assertTrue(TR.Save.isDisplayed());
		assertTrue(TR.Cancel.isDisplayed());

		JavaScriptclick(driver, TR.FirstRow);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.RemoveIcon);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.AddField);
		Thread.sleep(5000);
		assertTrue(TR.VisibleRows.size() == 1);
		JavaScriptclick(driver, TR.AddAllFields);
		Thread.sleep(5000);
		assertTrue(TR.VisibleRows.size() >= 3);
		JavaScriptclick(driver, TR.Cancel);
		waitforElement(60, TR.ConfirmationMessage, driver);
		Thread.sleep(2000);
		assertTrue(TR.ConfirmationMessage.isDisplayed());
		click(TR.NoButton);
		Thread.sleep(3000);
		assertTrue(TR.NEWCUSTOMTRANSACTIONDETAILREPORTTEMPLATE.isDisplayed());
		JavaScriptclick(driver, TR.Cancel);
		waitforElement(60, TR.ConfirmationMessage, driver);
		Thread.sleep(2000);
		click(TR.YesButton);
		Thread.sleep(3000);
		waitforElement(180, TR.CustomTransactionDetailReportRequestlabel, driver);
		assertTrue(TR.CustomTransactionDetailReportRequestlabel.isDisplayed());
		JavaScriptclick(driver, TR.NewCustomTransactionDetailReportTemplatelnk);
		Thread.sleep(2000);
		assertTrue(TR.RemoveAllFieldsDisabledByDefault.isDisplayed());
		JavaScriptclick(driver, TR.AddAllFields);
		Thread.sleep(3000);
		JavaScriptclick(driver, TR.RemoveAllFields);
		waitforElement(180, TR.RemoveallFieldsConfirmationMessage, driver);
		Thread.sleep(2000);
		assertTrue(TR.RemoveallFieldsConfirmationMessage.isDisplayed());
		click(TR.NoButton);
		Thread.sleep(3000);
		assertTrue(TR.VisibleRows.size() >= 2);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.RemoveAllFields);
		waitforElement(180, TR.RemoveallFieldsConfirmationMessage, driver);
		Thread.sleep(2000);
		click(TR.YesButton);
		Thread.sleep(2000);
		assertTrue(TR.VisibleRows.size() == 1);
		JavaScriptclick(driver, TR.UpArrow);
		Thread.sleep(2000);
		assertTrue(TR.VisibleRows.size() == 2);
		JavaScriptclick(driver, TR.DownArrow);
		Thread.sleep(2000);
		assertTrue(TR.VisibleRows.size() == 3);
		JavaScriptclick(driver, TR.UpArrow);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.DownArrow);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.UpArrow);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.DownArrow);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.UpArrow);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.DownArrow);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.UpArrow);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.DownArrow);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.UpArrow);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.DownArrow);
		Thread.sleep(1000);
		assertTrue(TR.VisibleRows.size() == 13);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.FirstRow);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[1]")).sendKeys(FieldItem);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[1]")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		Robot rob = new Robot();
		rob.keyPress(KeyEvent.VK_BACK_SPACE);
		rob.keyRelease(KeyEvent.VK_BACK_SPACE);
		Thread.sleep(2000);
		assertTrue(TR.FieldsCannotemptyalert.isDisplayed());
		type(TR.ColumnName1, ColumnName_abovemaxlength);
		Thread.sleep(2000);
		int maxlen = ColumnName_abovemaxlength.length();
		int allowlen = Column_Maxlength.length();
		Thread.sleep(1000);
		assertTrue(maxlen > allowlen);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.RemoveAllFields);
		waitforElement(180, TR.RemoveallFieldsConfirmationMessage, driver);
		Thread.sleep(2000);
		click(TR.YesButton);
		Thread.sleep(2000);
		assertTrue(TR.Save.isDisplayed());
		JavaScriptclick(driver, TR.AddField);
		Thread.sleep(3000);
		JavaScriptclick(driver, TR.FirstRow);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[1]")).sendKeys("Admin Destination City");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[1]")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		rob.keyPress(KeyEvent.VK_BACK_SPACE);
		rob.keyRelease(KeyEvent.VK_BACK_SPACE);
		Thread.sleep(2000);
		type(TR.ColumnName1, ColumnName);
		Thread.sleep(2000);
		assertTrue(TR.FieldsCannotemptyalert.isDisplayed());
		assertTrue(TR.Save.isDisplayed());
		type(TR.TemplatenameInputBox, TemplateName);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.AddAllFields);
		Thread.sleep(5000);
		click(TR.SaveBtn);
		waitforElement(60, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']"))
				.isDisplayed());
	}

	public void CreateNewCustomTransactionDeatilReportTemplate(String FieldItem, String ColumnName, String TemplateName)
			throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}

		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitforElement(180, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, TR.NewCustomTransactionDetailReportTemplatelnk);
		waitforElement(180, TR.NEWCUSTOMTRANSACTIONDETAILREPORTTEMPLATE, driver);
		Thread.sleep(3000);
		type(TR.TemplatenameInputBox, TemplateName);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.FirstRow);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[1]")).sendKeys(FieldItem);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[1]")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		if (ColumnName != "") {
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_BACK_SPACE);
			rob.keyRelease(KeyEvent.VK_BACK_SPACE);
			Thread.sleep(2000);
			type(TR.ColumnName1, ColumnName);
			Thread.sleep(2000);
		}

		Thread.sleep(2000);
		JavaScriptclick(driver, TR.AddAllFields);
		Thread.sleep(5000);
		click(TR.SaveBtn);
		Thread.sleep(2000);
		if (VerifyObject(driver,
				"//label[contains(.,'Template name already exists. Please choose a unique name for your template.')]")) {
			assertTrue(driver.findElement(By.xpath(
					"//label[contains(.,'Template name already exists. Please choose a unique name for your template.')]"))
					.isDisplayed());
			JavascriptExecutor jse = ((JavascriptExecutor) driver);
			jse.executeScript("arguments[0].scrollIntoView();", TR.Backbutton);
			Thread.sleep(3000);
			JavaScriptclick(driver, TR.Backbutton);
			waitforElement(60, TR.CustomTransactionDetailReportRequestlabel, driver);
			Thread.sleep(2000);
		} else {
			waitforElement(60, TR.CustomTransactionDetailReportRequestlabel, driver);
			Thread.sleep(2000);
			assertTrue(driver
					.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']"))
					.isDisplayed());
		}
	}

	public void CustomtemplatescreatedOrder(String TemplateName) throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(60, "html[@class='nprogress-busy']", driver);
		waitforElement(60, TR.CREATEBUTTON, driver);
		waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitforElement(180, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(300);
		List<WebElement> templatelists = driver
				.findElements(By.xpath("//h2[contains(.,'" + TemplateName + "')]/../../div/label"));

		for (int i = 1; i <= templatelists.size(); i++) {

			assertTrue(driver.findElement(By.xpath(
					"//h2[contains(.,'" + (TemplateName + i) + "')]/../../div/label[contains(.,'Edit Template')]"))
					.isDisplayed());
		}

	}

	public void EditTemplate(String TemplateName, String Rename_Template, String ColumnName, String Update_FieldItem)
			throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(60, "html[@class='nprogress-busy']", driver);
		waitforElement(60, TR.CREATEBUTTON, driver);
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']")).click();
		waitforElement(100, TR.EditCustomTransactionDetailReportTemplate, driver);
		Thread.sleep(2000);
		assertTrue(TR.EditCustomTransactionDetailReportTemplate.isDisplayed());
		clear(TR.TemplatenameInputBox);
		Thread.sleep(1000);
		type(TR.TemplatenameInputBox, Rename_Template);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.FirstRow);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[1]")).sendKeys(Update_FieldItem);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//select[1]")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		if (ColumnName != "") {
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_BACK_SPACE);
			rob.keyRelease(KeyEvent.VK_BACK_SPACE);
			Thread.sleep(2000);
			type(TR.ColumnName1, ColumnName);
			Thread.sleep(2000);
			click(TR.SaveBtn);
			waitforElement(60, TR.CustomTransactionDetailReportRequestlabel, driver);
			Thread.sleep(2000);
			assertTrue(driver
					.findElement(By.xpath("//label[contains(.,'" + Rename_Template + "')]/a[text()='Edit Template']"))
					.isDisplayed());
		} else {
			Thread.sleep(2000);
			click(TR.SaveBtn);
			waitforElement(60, TR.CustomTransactionDetailReportRequestlabel, driver);
			Thread.sleep(2000);
			assertTrue(driver
					.findElement(By.xpath("//label[contains(.,'" + Rename_Template + "')]/a[text()='Edit Template']"))
					.isDisplayed());
		}
	}

	public void DeleteTemplateNo(String FieldItem, String ColumnName, String TemplateName) throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		PageFactory.initElements(driver, ConfigurationReportPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}

		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitforElement(180, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(5000);
		assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']"))
				.isDisplayed());
		driver.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']")).click();
		waitforElement(100, TR.EditCustomTransactionDetailReportTemplate, driver);
		Thread.sleep(2000);
		assertTrue(TR.EditCustomTransactionDetailReportTemplate.isDisplayed());
		JavaScriptclick(driver, TR.DeleteTemplateButton);
		waitforElement(180, TR.DeleteTemplateLabel, driver);
		Thread.sleep(5000);
		assertTrue(TR.DeleteTemplateLabel.isDisplayed());
		JavaScriptclick(driver, TR.DeleteTemplateNoConfirm);
		waitforElement(100, TR.EditCustomTransactionDetailReportTemplate, driver);
		Thread.sleep(2000);
		assertTrue(TR.EditCustomTransactionDetailReportTemplate.isDisplayed());
	}

	public void DeleteTemplateYes(String FieldItem, String ColumnName, String TemplateName) throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		PageFactory.initElements(driver, ConfigurationReportPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}

		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitforElement(180, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(5000);
		assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']"))
				.isDisplayed());

		driver.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']")).click();
		waitforElement(100, TR.EditCustomTransactionDetailReportTemplate, driver);
		Thread.sleep(2000);
		assertTrue(TR.EditCustomTransactionDetailReportTemplate.isDisplayed());
		JavaScriptclick(driver, TR.DeleteTemplateButton);
		waitforElement(180, TR.DeleteTemplateLabel, driver);
		Thread.sleep(5000);
		assertTrue(TR.DeleteTemplateLabel.isDisplayed());
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.DeleteTemplateYesConfirm);
		waitforElement(60, TR.CustomTransactionDetailReportRequestlabel, driver);
		assertTrue(TR.CustomTransactionDetailReportRequestlabel.isDisplayed());
		Thread.sleep(5000);
		// assertFalse(driver.findElement(By.xpath("//label[contains(.,'"+TemplateName+"')]/a[text()='Edit
		// Template']")).isDisplayed());
	}

	public void allReportLinkNamesInReportsPage() throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(60, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);

		List<WebElement> allActualLinksInReportsPage = PUL.allLinksFromReportsPage;
		String[] allExpectedLinksInReportsPage = { "Configuration", "Customer Exemption", "Tax Assist Rule",
				"Tax Assist Allocation Table", "Tax Assist Lookup Table", "Taxability", "Validation Errors",
				"Filing Calendar", "Compliance - International", "Transaction - International",
				"Transaction - US and Canada", "Summary of Changes" };

		for (int i = 0; i < allActualLinksInReportsPage.size(); i++) {
			String actualLinkName = allActualLinksInReportsPage.get(i).getText();
			String expactedLinkName = allExpectedLinksInReportsPage[i];
			assertTrue(expactedLinkName.equals(actualLinkName));
		}
	}

	public void CreateNewTemplate(String TemplateName, String FieldItem1, String ColumnName1, String FieldItem2,
			String ColumnName2, String FieldItem3, String FieldItem4, String FieldItem5) throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(60, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(90, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitforElement(180, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(5000);
		JavaScriptclick(driver, TR.NewCustomTransactionDetailReportTemplatelnk);
		waitforElement(180, TR.NEWCUSTOMTRANSACTIONDETAILREPORTTEMPLATE, driver);
		Thread.sleep(3000);
		type(TR.TemplatenameInputBox, TemplateName);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.FirstRow);
		Thread.sleep(2000);
		selectByValue(TR.firstdropdown, FieldItem1);
		// driver.findElement(By.xpath("//select[1]")).sendKeys(FieldItem1);
		Thread.sleep(2000);
		// sendkeysTab(TR.firstdropdown);
		// driver.findElement(By.xpath("//select[1]")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		if (ColumnName1 != "") {
			click(TR.firstrowtextbox);
			Thread.sleep(1000);
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_BACK_SPACE);
			rob.keyRelease(KeyEvent.VK_BACK_SPACE);
			Thread.sleep(2000);
			type(TR.ColumnName1, ColumnName1);
			Thread.sleep(2000);
		}

		JavaScriptclick(driver, TR.AddField);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.SecondRow);
		Thread.sleep(2000);
		selectByValue(TR.seconddropdown, FieldItem2);
		// driver.findElement(By.xpath("//select[2]")).sendKeys(FieldItem2);
		Thread.sleep(2000);
		// sendkeysTab(TR.seconddropdown);
		// driver.findElement(By.xpath("//select[2]")).sendKeys(Keys.TAB);
		// Thread.sleep(1000);
		if (ColumnName2 != "") {
			click(TR.secondrowtextbox);
			Thread.sleep(1000);
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_BACK_SPACE);
			rob.keyRelease(KeyEvent.VK_BACK_SPACE);
			Thread.sleep(2000);
			type(TR.ColumnName1, ColumnName2);
			Thread.sleep(2000);
		}
		JavaScriptclick(driver, TR.AddField);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.ThirdRow);
		Thread.sleep(2000);
		selectByValue(TR.thirddropdown, FieldItem3);
		// driver.findElement(By.xpath("//select[3]")).sendKeys(FieldItem3);
		Thread.sleep(2000);
		// sendkeysTab(TR.thirddropdown);
		// driver.findElement(By.xpath("//select[3]")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.AddField);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.FourthRow);
		Thread.sleep(2000);
		selectByValue(TR.fourthdropdown, FieldItem4);
		// driver.findElement(By.xpath("//select[4]")).sendKeys(FieldItem4);
		Thread.sleep(2000);
		// sendkeysTab(TR.fourthdropdown);
		// driver.findElement(By.xpath("//select[4]")).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		JavaScriptclick(driver, TR.AddField);
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.FifthRow);
		Thread.sleep(2000);
		selectByValue(TR.fifthdropdown, FieldItem5);
		// driver.findElement(By.xpath("//select[5]")).sendKeys(FieldItem5);
		Thread.sleep(2000);
		// sendkeysTab(TR.fifthdropdown);
		// driver.findElement(By.xpath("//select[5]")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		click(TR.SaveBtn);
		Thread.sleep(2000);
		waitforElement(60, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']"))
				.isDisplayed());
	}

	public void removefieldfromTemplate(String TemplateName) throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);

		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(60, "html[@class='nprogress-busy']", driver);
		waitforElement(180, TR.CREATEBUTTON, driver);
		waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitforElement(180, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']")).click();
		waitforElement(100, TR.EditCustomTransactionDetailReportTemplate, driver);
		Thread.sleep(2000);
		assertTrue(TR.EditCustomTransactionDetailReportTemplate.isDisplayed());
		JavaScriptclick(driver, TR.RemoveIcon);
		Thread.sleep(2000);
		click(TR.SaveBtn);
		waitforElement(60, TR.CustomTransactionDetailReportRequestlabel, driver);
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + TemplateName + "')]/a[text()='Edit Template']"))
				.isDisplayed());
	}

	public void AdminCustomReport(String ClientName, String folderType, String Year, String Month, String ReportType,
			String Filename) throws Exception {

		File directory = new File(".//");
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		Robot rob = new Robot();
		String DownloadFilepath = xDownloadFiles("TransactionReport\\HVR");
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		type(AUA.smbCustomerName, ClientName);
		Thread.sleep(7000);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName
				+ "')]/../../../td[contains(.,'Registered')]")) {
			WebElement Registered = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + ClientName + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName
				+ "')]/../../../td[contains(.,'Authorized')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + ClientName + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName
				+ "')]/../../../td[contains(.,'Cancelled')]")) {
			WebElement Authorized = driver
					.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + ClientName + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Authorized);
		}

		Thread.sleep(3000);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		click(AUA.smbCustomerReports);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		selectByValue(AUA.Rootfolder, folderType);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		selectByValue(AUA.Internalyearfolder, Year);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		selectByValue(AUA.InternalMonthfolder, Month);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(.,'" + ReportType + "')]")).click();
		Thread.sleep(10000);
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
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			tempfile = new File(LatestDownloadedFile("TransactionReport", true));
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(8000);
			tempfile = new File(LatestDownloadedFile("TransactionReport", false));
		}
		if (Filename != "") {
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
			String name = Filename;
			tempfile2 = new File(DirName + "\\" + name + ".csv");
			Thread.sleep(5000);
			Comparecsvfiles(tempfile, tempfile2);
		}
	}

	public void LargeVolumeTransactionDetailReport(String Taxpayer, String Start_Date, String End_Date)
			throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		waitforElement(60, PUL.ReportsHover, driver);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, TR.CREATEBUTTON, driver);
		JavaScriptclick(driver, TR.CREATEBUTTON);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, TR.SELECTDD, driver);
		waitforElement(180, TR.CANCELBUTTON, driver);
		selectByValue(TR.SELECTDD, Taxpayer);
		waitforElement(180, TR.CANCELBUTTON, driver);
		if (Start_Date != "") {
			type(TR.STARTDATE, Start_Date);
			sendkeysTab(TR.STARTDATE);
		}
		if (End_Date != "") {
			type(TR.ENDDATE, End_Date);
			sendkeysTab(TR.ENDDATE);
		}
		JavaScriptclick(driver, TR.LARGEVOLUMETRANSACTIONDETAILS);

		JavaScriptclick(driver, TR.SUBMITBUTTON);

		Thread.sleep(5000);

		if (VerifyObject(driver, TR.SUCCESSFULMESSAGE)) {
			waitforElement(360, TR.SUCCESSFULMESSAGE, driver);
			assertTrue(TR.SUCCESSFULMESSAGE.isDisplayed());
			assertTrue(TR.ReportProgress.isDisplayed());
		} else {
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='startDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
			assertTrue(driver
					.findElement(
							By.xpath("//div/input[@id='endDate']/../div/span[contains(.,'This field is required.')]"))
					.isDisplayed());
		}

	}

	@SuppressWarnings("null")
	public void transactionreports(String Taxpayer, String Start_Date, String End_Date, String State,
			String Transaction_Details_Summary, String Successful_Message) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		new Robot();
		UserRegistration userrgistration = null;
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		// Wait 15 minutes for transaction data when HVR on
		// Thread.sleep(900000); Use syncTime() method
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
		if (State != null) {
			selectByValue(TR.STATESELECTIONID, State);
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

	public void VerifyTransactionReport(String Start_Date, String End_Date, String Verify_Download_Status,
			String Report_type, String Verify_Transaction, String Taxpayer, String State, String File_Name,
			String TransactionProcessDate, String CompanyCode, String Columns_Verify) throws Exception {

		Robot rob = new Robot();
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);

		String DownloadFilepath = xDownloadFiles("TransactionReport\\Oseries");
		int count = 0;
		int stRow1, endRow1;
		// Sheet TransactionReportPageSheet = xGetdtSheet(value);
		String[] ExData;
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
				// if(count==80){
				// status="Fail;There is no download click";
				// break;
				// }
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
			// System.out.println("Path" + path);
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

			if (Verify_Transaction != "") {
				if ((Verify_Transaction.contains(";"))) {
					ExData = Verify_Transaction.split(";");
					stRow1 = 0;
					endRow1 = ExData.length;
				} else if ((Verify_Transaction.contains(":"))) {
					ExData = Verify_Transaction.split(":");
					stRow1 = Integer.parseInt(ExData[0]);
					endRow1 = Integer.parseInt(ExData[1]) + 1;
				} else {
					String SrData = Verify_Transaction;
					stRow1 = Integer.parseInt(SrData);
					endRow1 = Integer.parseInt(SrData) + 1;
				}

				Thread.sleep(5000);
				for (int j = stRow1; j < endRow1; j++) {
					if ((Verify_Transaction.contains(";"))) {
						ExData = (Verify_Transaction).split(";");
						Integer.parseInt(ExData[j]);
					}
				}
			}
			// xGetCSVCellValue(Filepath, "Comment");
			if (TransactionProcessDate.equalsIgnoreCase("Yes")) {
				xGetCSVCellValue(Filepath, "Transaction Process Date");
				String TransProcessDate = Cell_Value;
				//System.out.println("TransactionProcessDate=" + TransProcessDate);
				switch (Report_type) {
				case "Transaction Details Report":
					assertTrue(TransProcessDate
							.matches("([0-9]{2})/([0-9]{2})/([0-9]{4}) ([0-9]){1,2}:([0-9]){2} ([A-Z]){2}"));
					break;
				case "Transaction Summary Report":
					assertTrue(TransProcessDate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"));
					break;
				}

			}
			if (CompanyCode != "") {
				xGetCSVCellValue(Filepath, "Taxpayer Company Code");
				String TaxpayerCompanyCode = Cell_Value;
				// System.out.println("TaxpayerCompanyCode=" + TaxpayerCompanyCode);
				assertTrue(TaxpayerCompanyCode.equals(CompanyCode));
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
				/*
				 * boolean dataavailable=false; for(int x=1;x<array.size();x++) { for(int
				 * y=1;y<array.get(x).size();y++) { String TransactionData=array.get(x).get(y);
				 * int Datalen=TransactionData.length(); if(Datalen>0){
				 * dataavailable=dataavailable|true; if (dataavailable) { break; }else {
				 * continue; } } } }
				 */
			}
		}
	}

	public void VerifyLookupTaxRatesPage(String State, String ZIP, String JurisdictionType, String TaxImpositionType,
			String StreetAddress, String County, String City, String Country, String VerifyError, String State1,
			String TAID, String RenatlRate) throws Exception {
		LookupTaxRates LTR = PageFactory.initElements(driver, LookupTaxRates.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		int k;
		MultipleStates = "";
		String SalesReplace = null, UseReplace = null, RentalReplace = null, SalesTaxRate, UseTaxRate,
				RentalTaxRate = null;
		driver.navigate().refresh();
		waitforElement(180, LTR.LOOKUPTAXRATESLINK, driver);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, LTR.LOOKUPTAXRATESLINK);
		} else {
			click(LTR.LOOKUPTAXRATESLINK);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, LTR.COUNTRYDRPDWN, driver);
		if (Country != "") {
			selectByValue(LTR.COUNTRYDRPDWN, Country);
			Thread.sleep(2000);
			if (StreetAddress != "") {
				clear(LTR.STREETADDRESS);
				type(LTR.STREETADDRESS, StreetAddress);
			}
			if (County != "") {
				clear(LTR.COUNTY);
				type(LTR.COUNTY, County);
			}
			if (City != "") {
				clear(LTR.CITY);
				type(LTR.CITY, City);
			}
			if (State != "") {
				waitforText(180, "//select[@id='requestState']/option[contains(.,'" + State + "')]", driver);
				selectByValue(LTR.STATE, State);
			}
			if (ZIP != "") {
				JavaScriptclick(driver, LTR.ZIP);
				JavaScripttype(driver, LTR.ZIP, "-");
				String PostalCode = getAttribute(LTR.ZIP, ZIP);
				assertTrue(PostalCode == null);
				JavaScripttype(driver, LTR.ZIP, ZIP);
				if (TAID == "") {
					sendkeysTab(LTR.ZIP);
					Thread.sleep(1000);
				}
				waitforElement(180, LTR.ADDLOOKUPRATEBUTTON, driver);
				JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
				Thread.sleep(2000);
				if (!VerifyObject(driver, LTR.CLEARRESULTS)) {
					JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
					Thread.sleep(2000);
				}
			}
		}
		if (TAID != "") {
			JavaScriptclick(driver, LTR.TAIDRadio);
			Thread.sleep(2000);
			click(LTR.TAXAREAID);
			JavaScripttype(driver, LTR.TAXAREAID, TAID);
			Thread.sleep(2000);
			if (ZIP == "") {
				sendkeysTab(LTR.TAXAREAID);
				Thread.sleep(1000);
			}
			waitforElement(180, LTR.TAIDLOOKUPRATEBUTTON, driver);
			JavaScriptclick(driver, LTR.TAIDLOOKUPRATEBUTTON);
			Thread.sleep(2000);
			if (!VerifyObject(driver, LTR.CLEARRESULTS)) {
				JavaScriptclick(driver, LTR.TAIDLOOKUPRATEBUTTON);
				Thread.sleep(2000);
			}
		}
		if (TAID != "") {
			if (VerifyObject(driver, LTR.FieldrequiredTaid)) {
				boolean report = false;
				do {
					click(LTR.TAXAREAID);
					driver.findElement(By.id("requestTAID")).sendKeys(Keys.CONTROL + "a");
					driver.findElement(By.id("requestTAID")).sendKeys(Keys.CONTROL + "x");
					Thread.sleep(2000);
					driver.findElement(By.id("requestTAID")).sendKeys(Keys.CONTROL + "v");
					Thread.sleep(2000);
					if (!VerifyObject(driver, LTR.FieldrequiredTaid)) {
						JavaScriptclick(driver, LTR.TAIDLOOKUPRATEBUTTON);
						report = true;
					}
				} while (report == false);
				JavaScriptclick(driver, LTR.TAIDLOOKUPRATEBUTTON);
			}
		}
		if (ZIP != "") {
			if (VerifyObject(driver, LTR.Fieldrequired)) {
				boolean report = false;
				do {
					click(LTR.ZIP);
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
				JavaScriptclick(driver, LTR.ADDLOOKUPRATEBUTTON);
			}
		}
		if (VerifyError != "") {
			Thread.sleep(5000);
			if (VerifyObject(driver, LTR.INVALIDADDRESSERRMSG)) {
				waitforElement(180, LTR.INVALIDADDRESSERRMSG, driver);
				assertTrue(LTR.INVALIDADDRESSERRMSG.isDisplayed());

			} else {
				waitforElement(180, LTR.InvalidTAIDmeg, driver);
				assertTrue(LTR.InvalidTAIDmeg.isDisplayed());
			}
		} else {
			waitforElement(180, LTR.CLEARRESULTS, driver);
			JavaScriptclick(driver, LTR.EXPANDALL);
			Thread.sleep(5000);
			float TotalSalesTax = 0.00f;
			float TotalUseTax = 0.00f;
			float TotalRentalTax = 0.00f;
			List<WebElement> Rows = driver.findElements(
					By.xpath("//div/table/tbody/tr/td/table[@class='table table-minimal table-bordered']"));
			k = 2;
			for (int j = 1; j <= Rows.size() - 2; j++) {
				if ((Country.equalsIgnoreCase("United States")) || (State1.equalsIgnoreCase("Colorado"))) {
					// POR-24918 Combined Sales Tax Rate does not match the break down on the Sales
					// Tax Rate in the Look Up Tax Rate Feature
					if (RenatlRate != "") {
						String TAID1 = TAID;
						SalesTaxRate = driver
								.findElement(By.xpath("//tbody/tr[" + (k - 1)
										+ "]/td/span[starts-with(@id,'rate-combined-sales-tax-rate-" + TAID1 + "')]"))
								.getText();
						UseTaxRate = driver.findElement(By.xpath("//tbody/tr[" + (k - 1)
								+ "]/td/span[starts-with(@id,'rate-combined-consumer-use-tax-rate-" + TAID1 + "')]"))
								.getText();
						RentalTaxRate = driver
								.findElement(By.xpath("//tbody/tr[" + (k - 1)
										+ "]/td/span[starts-with(@id,'rate-combined-rental-rate-" + TAID1 + "')]"))
								.getText();
					} else {
						SalesTaxRate = driver
								.findElement(By.xpath("//div/table/tbody/tr[" + k
										+ "]/td/table/tbody/tr/td[4]/span[starts-with(@id,'rate-detail-tax-rate')]"))
								.getText();
						UseTaxRate = driver.findElement(By.xpath("//div/table/tbody/tr[" + k
								+ "]/td/table/tbody/tr/td[5]/span[starts-with(@id,'rate-detail-cut-tax-rate')]"))
								.getText();
						RentalTaxRate = driver
								.findElement(By.xpath("//div/table/tbody/tr[" + k
										+ "]/td/table/tbody/tr/td[6]/span[starts-with(@id,'rate-detail-rental-rate')]"))
								.getText();
					}
				} else {
					SalesTaxRate = driver.findElement(By.xpath("//div/table/tbody/tr/td/table/tbody/tr[" + j
							+ "]/td[4]/span[starts-with(@id,'rate-detail-tax-rate')]")).getText();
					UseTaxRate = driver.findElement(By.xpath("//div/table/tbody/tr/td/table/tbody/tr[" + j
							+ "]/td[5]/span[starts-with(@id,'rate-detail-cut-tax-rate')]")).getText();
					RentalTaxRate = driver.findElement(By.xpath("//div/table/tbody/tr/td/table/tbody/tr[" + j
							+ "]/td[6]/span[starts-with(@id,'rate-detail-rental-rate')]")).getText();
				}
				// Sales tax is N/A
				if (SalesTaxRate.equalsIgnoreCase("N/A")) {
					TotalSalesTax = TotalSalesTax + 0;
				} else {
					SalesReplace = SalesTaxRate.replaceAll("%", "");
					TotalSalesTax = TotalSalesTax + Float.parseFloat(SalesReplace);
				}

				// Use tax is N/A
				if (UseTaxRate.equalsIgnoreCase("N/A")) {
					TotalUseTax = TotalUseTax + 0;
				} else {
					UseReplace = SalesTaxRate.replaceAll("%", "");
					TotalUseTax = TotalUseTax + Float.parseFloat(UseReplace);
				}
				// Rental rate is N/A
				if (RentalTaxRate.equalsIgnoreCase("N/A")) {
					TotalRentalTax = TotalRentalTax + 0;
				} else {
					RentalReplace = RentalTaxRate.replaceAll("%", "");
					TotalRentalTax = TotalRentalTax + Float.parseFloat(RentalReplace);
				}
				// Verifying Rental Rate as per POR-18540 in 7.1
				if (RenatlRate != "") {
					String Rental = String.valueOf(TotalRentalTax);
					String RentalExcelValue = RenatlRate;
					assertTrue(Rental.equalsIgnoreCase(RentalExcelValue));
				}
				if (Country.equalsIgnoreCase("United States")) {
					if (State != "") {
						String strJurisdiction = driver
								.findElement(By.xpath("//div/table/tbody/tr[" + k + "]/td/table/tbody/tr/td[1]/span"))
								.getText();
						String ExcelJurisdiction = State;
						assertTrue(strJurisdiction.equalsIgnoreCase(ExcelJurisdiction));
					}
				} else {
					if (State != "") {
						String strJurisdiction1 = driver
								.findElement(By.xpath("//div/table/tbody/tr/td/table/tbody/tr[2]/td[1]/span"))
								.getText();
						String ExcelJurisdiction1 = State;
						assertTrue(strJurisdiction1.equalsIgnoreCase(ExcelJurisdiction1));
					}
				}
				String strJurisdictionType = driver
						.findElement(By.xpath("//div/table/tbody/tr[" + k + "]/td/table/tbody/tr/td[2]/span"))
						.getText();
				assertTrue(JurisdictionType.equalsIgnoreCase(strJurisdictionType));

				String strImpositionType = driver
						.findElement(By.xpath("//div/table/tbody/tr[" + k + "]/td/table/tbody/tr/td[3]/span"))
						.getText();

				assertTrue(TaxImpositionType.equalsIgnoreCase(strImpositionType));
				// TAID Verification
				scrollDown(driver);
				if (TAID != "") {
					assertTrue(TAID.equalsIgnoreCase(getText(LTR.TAID)));
				}
				k = k + 2;
			}
			waitforElement(180, LTR.CLEARRESULTS, driver);
			if (VerifyObject(driver, LTR.CLEARRESULTS)) {
				click(LTR.CLEARRESULTS);
				driver.navigate().refresh();
				Thread.sleep(5000);
				// Verifying Lookup Rates when the Clear Results button is clicked, Country is
				// changed in POR-21168
				List<WebElement> CountryList = driver.findElements(By.xpath("//select[@id='requestCountry']/option"));
				assertTrue((CountryList.get(0).getText()).equalsIgnoreCase("United States"));

			} else {
			}
			driver.navigate().refresh();
			waitforElement(180, LTR.ZIP, driver);
		}
		MultipleStates = MultipleStates + State + ";";

	}

	public void AdminTransactionReport(String ClientName, String CompanyName, String StartDate, String EndDate,
			String Transaction_Details_Summary, String folderType, String Year, String Month, String Report,
			String CompareRTE_BracketSchedule, String DownloadReports) throws Exception {
		try {
			File directory = new File(".//");
			AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
			PageFactory.initElements(driver, LoginPage.class);
			Robot rob = new Robot();
			String DownloadFilepath = xDownloadFiles("TransactionReport\\HVR");
			click(AUA.manageSMBCustomers);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);

			type(AUA.smbCustomerName, ClientName);
			Thread.sleep(7000);
			if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
					+ "')]/../../../td[contains(text(),'Registered')]")) {
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);

			} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
					+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			} else {
				WebElement client = driver
						.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
								+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			}
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
						click(AUA.AdminIntTransactionDetails);
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
				} else if (Transaction_Details_Summary.equalsIgnoreCase("International Transaction Report")) {
					Thread.sleep(3000);
					JavaScriptclick(driver, AUA.AdminIntlTransactionDetailReport);
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

	public void IndirectTaxIntelligence() throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		JavaScriptclick(driver, PUL.IndirectTaxIntelligence);
		String parentwindow = driver.getWindowHandle();
		waitforElement(60, PUL.IndirectTaxIntelligenceLabel, driver);
		waitforElement(60, PUL.RegisterforAccess, driver);
		assertTrue(PUL.IndirectTaxIntelligenceLabel.isDisplayed());
		assertTrue(PUL.RegisterforAccess.isDisplayed());

		JavaScriptclick(driver, PUL.LoginITI);
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		driver.manage().window().maximize();
		Thread.sleep(10000);
		driver.close();
		driver.switchTo().window(parentwindow);
		Thread.sleep(5000);
		JavaScriptclick(driver, PUL.DiscoverITI);
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		driver.manage().window().maximize();
		Thread.sleep(10000);
		driver.close();
		driver.switchTo().window(parentwindow);
	}

	public void IndirectTaxIntelligencenotDisplayed() throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		if (VerifyObject(driver, PUL.IndirectTaxIntelligence)) {
			assertTrue(true);
			System.out.println("ITI Left Navnavigation : Indirect Tax Intelligence Displayed");
		} else {
			assertFalse(false);
			System.out.println("ITI Left Navnavigation : Indirect Tax Intelligence Not Displayed");
		}
	}

	@SuppressWarnings("resource")
	public void PerformanceMetricsETEReport(String CompanyName, String StartDate, String EndDate, String Report,
			String Year, String Month, String FileName, String Folder_Name, String Validate_StartDate,
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
				ClientName = VertexClientName;
			}
			if (TestInstance_Client.equalsIgnoreCase("YES")) {
				ClientName = VertexClientName + ".TI";
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

	public void ComplianceFileReport(String InternationalCompliance, String Fillingperiod, String TaxpayerCode,
			String Startdate, String EndDate) throws Exception {
		new Robot();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		// waitforText(90,CR.Reports_Overview_Text, driver);
		JavaScriptscrolltoView(driver, PUL.ComplianceInternationalLink);
		Thread.sleep(5000);
		if (InternationalCompliance.equals("Yes")) {
			click(PUL.ComplianceInternationalLink);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, TR.CREATENEWCOMPLIANCEFILE, driver);
			Thread.sleep(5000);
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
		selectByValue(TR.TAXPAYERSELECT, TaxpayerCode);
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
					assertTrue(false);
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

	public void AdminInternationalComplianceReport(String ClientName, String Folder, String Year, String Month)
			throws Exception {
		new File(".//");
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		new Robot();
		xDownloadFiles("TaxAssistReport");
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		type(AUA.smbCustomerName, ClientName);
		Thread.sleep(7000);
		waitforElement(100, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName + "')]",
				driver);
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

		waitforElement(180, AUA.smbCustomerReports, driver);
		Thread.sleep(2000);
		click(AUA.smbCustomerReports);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);

		Thread.sleep(3000);
		waitforElement(180, AUA.Rootfolder, driver);
		selectByValue(AUA.Rootfolder, Folder);
		Thread.sleep(3000);
		selectByValue(AUA.Internalyearfolder, Year);
		Thread.sleep(3000);
		selectByValue(AUA.InternalMonthfolder, Month);
		Thread.sleep(6000);
		waitforElement(180, AUA.DownloadedInternationalReturns, driver);
		assertTrue(AUA.InternationalReturnsDownloadLnk.isDisplayed());
		click(AUA.InternationalReturnsDownloadLnk);
		Thread.sleep(6000);
	}

	public void InternationalTransactionReport(String Start_Date, String End_Date, String Transaction_Details_Summary,
			String Successful_Message, String Taxpayer, String Country) throws Exception {

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
			assertTrue(false);
		}
		assertTrue(TR.CANCELBUTTON.isDisplayed());
		type(TR.STARTDATE, Start_Date);
		sendkeysTab(TR.STARTDATE);
		type(TR.ENDDATE, End_Date);
		sendkeysTab(TR.ENDDATE);
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

	public void VerifyInternationalTransactionReport(String Start_Date, String End_Date, String Verify_Download_Status,
			String Taxpayer, String File_Name, String CountryCode) throws Exception {
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
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, CR.Reports_Overview, driver);
		// waitforText(90,CR.Reports_Overview_Text, driver);
		JavaScriptclick(driver, TR.InternationalTransactionLink);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		waitforElement(180, TR.TAXPAYERSELECT, driver);
		selectByValue(TR.TAXPAYERSELECT, Taxpayer);
		waitforElement(180, TR.CREATEBUTTON, driver);
		waitforElement(180, "//tr/td[text()='" + Start_Date + "']/../td[text()='" + End_Date + "']/../td[contains(.,'"
				+ CountryCode + "')]/../td/a", driver);
		String downloadLink = "//tr/td[text()='" + Start_Date + "']/../td[text()='" + End_Date + "']/../td[contains(.,'"
				+ CountryCode + "')]/../td/a";
		if (!VerifyObject(driver, downloadLink)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TR.TAXPAYERSELECT, driver);
				selectByValue(TR.TAXPAYERSELECT, Taxpayer);
				count = count + 1;
				// if(count==80){
				// status="Fail;There is no download click";
				// break;
				// }
				if (VerifyObject(driver, downloadLink)) {
					report = true;
				}
				Thread.sleep(5000);
				JavaScriptclick(driver, PUL.ReportsHover);
				waitforElement(90, TR.InternationalTransactionLink, driver);
				JavaScriptclick(driver, TR.InternationalTransactionLink);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, TR.TAXPAYERSELECT, driver);
				selectByValue(TR.TAXPAYERSELECT, Taxpayer);
			} while (report == false);
		}
		assertTrue(TR.COLUMN1.isDisplayed());
		assertTrue(TR.COUNTRYCOLUMN.isDisplayed());
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
		Thread.sleep(15000);
		if (Verify_Download_Status.equalsIgnoreCase("Yes")) {
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

}
