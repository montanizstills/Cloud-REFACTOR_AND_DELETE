package common.libraries.customer;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import common.libraries.generic.CommonFunctions;
import common.pages.*;
import common.utilities.CloudData;

public class Transactions extends CommonFunctions {

	private WebDriver driver;

	public Transactions(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public static String TransactionPostEndTime;

	public void CalculateTaxes(String Taxpayers, String Trans_Date, String Trans_No, String Customers, String Locations,
			String To_Country, String S_State, String S_ZIP, String Products, String Prod_Price, String Prod_Qty,
			String Total_Tax, String total, String TaxAreaID, String TaxType, String MessageType, String ChargedTax,
			String multiplelineItems) throws Exception {

		CalculateTaxesPage CT = PageFactory.initElements(driver, CalculateTaxesPage.class);
		DistributeTaxes DT = PageFactory.initElements(driver, DistributeTaxes.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		currDate1 = xGetDate("MM/dd/yyyy");
		String Zip1, Qty, TotalTaxAmount, TotalAmount;
		int count = 0;
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		waitforElement(180, PUL.taxesHover, driver);
		JavaScriptMousehover(driver, PUL.taxesHover);
		if (Browser.equalsIgnoreCase("Chrome")) {
			click(PUL.CALCULATETAXES);
		} else {
			JavaScriptclick(driver, PUL.CALCULATETAXES);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, CT.PRODUCTPRICE, driver);
		selectByValue(DT.TAXPAYER, Taxpayers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);

		if (VerifyObject(driver, CT.UNPUBLISHEDPUSH_CONFIGURATION)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000);
				count = count + 1;
				if (count == 250) {
					break;
				}
				selectByValue(DT.TAXPAYER, Taxpayers);
				if (!VerifyObject(driver, CT.UNPUBLISHEDPUSH_CONFIGURATION)) {
					waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
					report = true;
				}
			} while (report == false);
		}

		waitforElement(180, CT.TRANSDATE, driver);
		clear(CT.TRANSDATE);
		if (Trans_Date.equalsIgnoreCase("Live")) {
			type(CT.TRANSDATE, xGetCustomizedDate(-29, -1, 0));
		} else if (Trans_Date.equalsIgnoreCase("Test")) {
			type(CT.TRANSDATE, xGetCustomizedDate(-29, -2, 0));
		} else {
			type(CT.TRANSDATE, Trans_Date);
		}
		sendkeysTab(CT.TRANSDATE);
		clear(CT.TRANSNO);
		type(CT.TRANSNO, Trans_No);
		if ((Browser.equalsIgnoreCase("Firefox") && !Taxpayers.isEmpty())) {
			waitforElement(180, DT.TAXPAYER, driver);
			selectByValue(DT.TAXPAYER, Taxpayers);
		} else if (!Taxpayers.isEmpty()) {
			waitforElement(180, DT.TAXPAYER, driver);
			type(DT.TAXPAYER, Taxpayers);
		}
		if (!TaxType.isEmpty()) {
			selectByValue(DT.TAXTYPE, TaxType);
		}
		if (!MessageType.isEmpty()) {
			selectByValue(CT.MessageType, MessageType);
		}
		if (!ChargedTax.isEmpty()) {
			type(CT.chargedtaxfield, ChargedTax);
		}

		if (!Customers.isEmpty()) {
			waitforText(60, "//select[@id='customer']/option[contains(.,'" + Customers + "')]", driver);
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				selectByValue(CT.CUSTOMER, Customers);
			} else {
				type(CT.CUSTOMER, Customers);
			}
		}
		if (!Locations.isEmpty()) {
			Thread.sleep(5000);
			waitforText(60, "//select[@id='location']/option[contains(.,'" + Locations + "')]", driver);
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				selectByValue(CT.LOCATION, Locations);
			} else {
				type(CT.LOCATION, Locations);
			}
		}

		if (!TaxAreaID.isEmpty()) {
			JavaScriptclick(driver, CT.TAIDRADIO1);
			Thread.sleep(3000);
			click(CT.TOtaxareaid);
			type(CT.TOtaxareaid, TaxAreaID);
			Thread.sleep(2000);
			sendkeysTab(CT.TOtaxareaid);
		} else {
			Thread.sleep(3000);
			if (TaxType.equalsIgnoreCase("Buyer")) {
				if (VerifyObject(driver, CT.SHIPCOUNTRYBUYER)) {
					selectByValue(CT.SHIPCOUNTRYBUYER, To_Country);
					sendkeysTab(CT.SHIPCOUNTRYBUYER);
				}
				if (VerifyObject(driver, CT.SHIPCOUNTRY)) {
					selectByValue(CT.SHIPCOUNTRY, To_Country);
					sendkeysTab(CT.SHIPCOUNTRY);
				}

				if (S_State != "") {
					if (VerifyObject(driver, CT.SHIPSTATEBUYER)) {
						selectByValue(CT.SHIPSTATEBUYER, S_State);
					}
					if (VerifyObject(driver, CT.SHIPSTATE)) {
						selectByValue(CT.SHIPSTATE, S_State);
					}
				}
				Zip1 = S_ZIP;
				if (VerifyObject(driver, CT.SHIPZIPBUYER)) {
					click(CT.SHIPZIPBUYER);
					JavaScripttype(driver, CT.SHIPZIPBUYER, Zip1);
					sendkeysTab(CT.SHIPZIPBUYER);
				}
				if (VerifyObject(driver, CT.SHIPZIP)) {
					click(CT.SHIPZIP);
					JavaScripttype(driver, CT.SHIPZIP, Zip1);
					sendkeysTab(CT.SHIPZIP);
				}
			} else {
				selectByValue(CT.SHIPCOUNTRY, To_Country);
				sendkeysTab(CT.SHIPCOUNTRY);
				if (S_State != "") {
					selectByValue(CT.SHIPSTATE, S_State);
				}
				Zip1 = S_ZIP;
				click(CT.SHIPZIP);
				JavaScripttype(driver, CT.SHIPZIP, Zip1);
				sendkeysTab(CT.SHIPZIP);
			}
		}

		if (multiplelineItems.equalsIgnoreCase("Yes")) {

			String[] products = Products.split(",");
			String[] price = Prod_Price.split(",");
			String[] quantity = Prod_Qty.split(",");

			for (int p = 1; p <= price.length; p++) {
				int q = p - 1;
				if (!Products.isEmpty()) {

					new Select(driver.findElement(By.xpath("//div[" + p + "]/div[1]/div[2]/select[1]")))
							.selectByVisibleText(products[q]);
				}

				if (!Prod_Price.isEmpty()) {
					driver.findElement(By.xpath("//input[@id='line-item-" + q + "-price']")).clear();
					driver.findElement(By.xpath("//input[@id='line-item-" + q + "-price']")).sendKeys(price[q]);
					Thread.sleep(1000);
				}

				if (!Prod_Qty.isEmpty()) {
					driver.findElement(By.xpath("//input[@id='line-item-" + q + "-quantity']")).clear();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//input[@id='line-item-" + q + "-quantity']")).sendKeys(quantity[q]);
					Thread.sleep(1000);
					driver.findElement(By.xpath("//input[@id='line-item-" + q + "-quantity']")).sendKeys(Keys.RETURN);
					Thread.sleep(1000);
					driver.findElement(By.xpath("//input[@id='line-item-" + q + "-quantity']")).sendKeys(Keys.TAB);
					Thread.sleep(2000);
				}
				if (p != price.length) {
					click(CT.AddAnotherLineItem);
					Thread.sleep(2000);
				}
			}
		} else {

			if (!Products.isEmpty()) {
				waitforText(180, "//label[text()='Code/Class']/../select/option[contains(.,'" + Products + "')]",
						driver);
				if (Browser.equalsIgnoreCase("Firefox")) {
					selectByValue(CT.PRODUCT, Products);
				} else {
					type(CT.PRODUCT, Products);
				}
			}
			if (!Prod_Price.isEmpty()) {
				clear(CT.PRODUCTPRICE);
				Prod_Price.split("\\.");
				type(CT.PRODUCTPRICE, Prod_Price);
				Thread.sleep(1000);
			}

			if (!Prod_Qty.isEmpty()) {
				Qty = Prod_Qty;
				clear(CT.PRODUCTQTY);
				Thread.sleep(1000);
				type(CT.PRODUCTQTY, Qty);
				Thread.sleep(1000);
				sendkeysReturn(CT.PRODUCTQTY);
				Thread.sleep(1000);
				sendkeysTab(CT.PRODUCTQTY);
				Thread.sleep(2000);

			}
		}
		if (VerifyObject(driver, CT.DiableCalButton)) {
			boolean report = false;
			do {
				if (!VerifyObject(driver, CT.SHIPZIP)) {

					clear(CT.PRODUCTQTY);
					Thread.sleep(1000);
					type(CT.PRODUCTQTY, Prod_Qty);
					Thread.sleep(1000);
					sendkeysTab(CT.PRODUCTQTY);
					click(CT.SHIPZIP);
					driver.findElement(By.id("contact-1-zip")).sendKeys(Keys.CONTROL + "a");
					driver.findElement(By.id("contact-1-zip")).sendKeys(Keys.CONTROL + "x");
					Thread.sleep(2000);
					driver.findElement(By.id("contact-1-zip")).sendKeys(Keys.CONTROL + "v");
					Thread.sleep(2000);
					if (VerifyObject(driver, "//input[@id='contact-2-zip']")) {
						driver.findElement(By.id("contact-2-zip")).sendKeys(Keys.CONTROL + "a");
						driver.findElement(By.id("contact-2-zip")).sendKeys(Keys.CONTROL + "x");
						Thread.sleep(2000);
						driver.findElement(By.id("contact-2-zip")).sendKeys(Keys.CONTROL + "v");
						Thread.sleep(2000);
					}

				} else {
					click(CT.SHIPZIP);
					driver.findElement(By.id("contact-1-zip")).sendKeys(Keys.CONTROL + "a");
					driver.findElement(By.id("contact-1-zip")).sendKeys(Keys.CONTROL + "x");
					Thread.sleep(2000);
					driver.findElement(By.id("contact-1-zip")).sendKeys(Keys.CONTROL + "v");
					Thread.sleep(2000);
					if (VerifyObject(driver, "//input[@id='contact-2-zip']")) {
						driver.findElement(By.id("contact-2-zip")).sendKeys(Keys.CONTROL + "a");
						driver.findElement(By.id("contact-2-zip")).sendKeys(Keys.CONTROL + "x");
						Thread.sleep(2000);
						driver.findElement(By.id("contact-2-zip")).sendKeys(Keys.CONTROL + "v");
						Thread.sleep(2000);
					}
				}
				if (!VerifyObject(driver, CT.DiableCalButton)) {
					waitforElement(60, CT.CALCULATETAXES, driver);
					if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
						JavaScriptclick(driver, CT.CALCULATETAXES);
					} else {
						click(CT.CALCULATETAXES);
					}
					report = true;
				}
			} while (report == false);
		} else {
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				waitforElement(180, CT.CALCULATETAXES, driver);
				JavaScriptclick(driver, CT.CALCULATETAXES);
			} else {
				click(CT.CALCULATETAXES);
			}
			Thread.sleep(2000);
		}
		waitUntilElementInvisibe(60, "//div[@class='blockUI blockMsg blockPage']", driver);
		waitforElement(60, CT.POSTTRANSACTION, driver);
		if (VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
			click(PUL.OKERROR);
			waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
			Thread.sleep(3000);
		}
		if (!Total_Tax.isEmpty()) {
			TotalTaxAmount = getText(CT.TOTALTAXAMOUNT);
			if (!Total_Tax.isEmpty()) {
				Assert.assertEquals(TotalTaxAmount, Total_Tax);
			}
		}
		if (!total.isEmpty()) {
			TotalAmount = getText(CT.TOTALAMOUNT);
			if (!total.isEmpty()) {
				Assert.assertEquals(TotalAmount, total);
			}
		}
		if (VerifyObject(driver, CT.POSTTRANSACTION)) {
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, CT.POSTTRANSACTION);
			} else {
				click(CT.POSTTRANSACTION);
			}
			waitUntilElementInvisibe(60, "//div[@class='blockUI blockMsg blockPage']", driver);
			waitforElement(180, CT.CLOSEANDRETURN, driver);
			transactioncount++;
		}

		if (VerifyObject(driver, CT.POSTING_REJECTED) || VerifyObject(driver, CT.ERROR_CALCULATE)
				|| VerifyObject(driver, CT.CANCELTRANSACTION)) {
			driver.navigate().refresh();
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, CT.PRODUCTPRICE, driver);
		} else {
			click(CT.CLOSEANDRETURN);
			Thread.sleep(2000);
			driver.navigate().refresh();
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, CT.PRODUCTPRICE, driver);
		}
		TransactionPostEndTime = xGetCurrentTime();
	}

	public void DistributeTaxes(String Taxpayers, String Trans_Date, String Trans_No, String Totaltax,
			String PD_Country, String PD_State, String PD_ZIP, String Taxtype, String TotalTax) throws Exception {
		DistributeTaxes DT = PageFactory.initElements(driver, DistributeTaxes.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		int TaxRows, j, count = 0;
		String TaxType, Taxxpath, splitdollar, TotalSum, TotalSumsplit, DTotaltax;
		waitforElement(60, PUL.taxesHover, driver);
		JavaScriptMousehover(driver, PUL.taxesHover);
		if (Browser.equalsIgnoreCase("Chrome")) {
			click(PUL.DISTRIBUTETAX);
		} else {
			JavaScriptclick(driver, PUL.DISTRIBUTETAX);
		}
		waitforElement(180, DT.GETJURISDICTIONS, driver);
		if ((Browser.equalsIgnoreCase("Firefox") && !Taxpayers.isEmpty())) {
			waitforElement(180, DT.TAXPAYER, driver);
			selectByValue(DT.TAXPAYER, Taxpayers);
		} else if (!Taxpayers.isEmpty()) {
			waitforElement(180, DT.TAXPAYER, driver);
			type(DT.TAXPAYER, Taxpayers);
		}
		waitUntilElementInvisibe(30, "//div[@class='blockUI blockMsg blockPage']", driver);
		if (VerifyObject(driver, DT.ERRORDISTRIBUTE)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitforElement(180, DT.GETJURISDICTIONS, driver);
				// Wait for the Distribute Tax page with fields after config push to O Series
				count = count + 1;
				if (count == 60) {
					break;
				}
				if ((Browser.equalsIgnoreCase("Firefox") && !Taxpayers.isEmpty())) {
					waitforElement(180, DT.TAXPAYER, driver);
					selectByValue(DT.TAXPAYER, Taxpayers);
				} else if (!Taxpayers.isEmpty()) {
					type(DT.TAXPAYER, Taxpayers);
				}
				waitUntilElementInvisibe(30, "//div[@class='blockUI blockMsg blockPage']", driver);
				if (!VerifyObject(driver, DT.ERRORDISTRIBUTE)) {
					report = true;
					driver.navigate().refresh();
					waitforElement(180, DT.GETJURISDICTIONS, driver);
				}
			} while (!report);
		}
		// driver.navigate().refresh();
		waitforElement(180, DT.GETJURISDICTIONS, driver);
		clear(DT.TRANSDATE);
		if (Trans_Date.equalsIgnoreCase("Live")) {
			type(DT.TRANSDATE, xGetCustomizedDate(-29, -1, 0));
		} else if (Trans_Date.equalsIgnoreCase("Test")) {
			type(DT.TRANSDATE, xGetCustomizedDate(-29, -2, 0));
		} else {
			type(DT.TRANSDATE, Trans_Date);
		}
		sendkeysReturn(DT.TRANSDATE);
		type(DT.TRANSNO, Trans_No);
		waitforElement(180, DT.TAXPAYER, driver);
		/*
		 * if ((Browser.equalsIgnoreCase("Firefox") && !Taxpayers.isEmpty())) {
		 * selectByValue(DT.TAXPAYER, Taxpayers); } else if (!Taxpayers.isEmpty()) {
		 * type(DT.TAXPAYER, Taxpayers); }
		 */
		waitUntilElementInvisibe(30, "//div[@class='blockUI blockMsg blockPage']", driver);
		if (!Totaltax.isEmpty()) {
			type(DT.TOTALTAX, Totaltax);
		}
		if (!PD_Country.isEmpty()) {
			selectByValue(DT.SHIPCOUNTRY, PD_Country);
		}
		if (!PD_State.isEmpty()) {
			selectByValue(DT.SHIPSTATE, PD_State);
		}
		String Zip1 = PD_ZIP;
		if (!PD_ZIP.isEmpty()) {
			click(DT.SHIPZIP);
			JavaScripttype(driver, DT.SHIPZIP, Zip1);
			sendkeysTab(DT.SHIPZIP);
			Thread.sleep(2000);
			DT.SHIPZIP.sendKeys(Keys.CONTROL + "a");
			DT.SHIPZIP.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			DT.SHIPZIP.sendKeys(Keys.CONTROL + "v");
			if (Browser.equalsIgnoreCase("Chrome")) {
				JavaScriptclick(driver, DT.TRANSNO);
			} else {
				click(DT.TRANSNO);
			}
			Thread.sleep(2000);
		}
		waitforElement(180, DT.GETJURISDICTIONS, driver);
		click(DT.GETJURISDICTIONS);
		waitUntilElementInvisibe(30, "//div[@class='blockUI blockMsg blockPage']", driver);
		waitforElement(180, DT.DISTRIBUTETAX, driver);
		if (VerifyObject(driver, PUL.ANERRORHASOCCURED) || VerifyObject(driver, DT.ERROR_DISTRIBUTETAX)) {
			if (VerifyObject(driver, PUL.OKERROR)) {
				click(PUL.OKERROR);
				waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
			}
			Thread.sleep(3000);
		}
		if (VerifyObject(driver, DT.DISTRIBUTETEAX)) {
			TaxType = Taxtype;
			switch (TaxType) {
			case "ALL":
				int Rows = driver.findElements(By.xpath("//tbody/tr")).size();
				for (j = 1; j <= Rows; j++) {
					driver.findElement(By.xpath("//tbody/tr[" + j + "]/td/input")).click();
				}
				break;
			case "STATE":
				driver.findElement(By.xpath("//td[contains(.,'STATE')]/../td/input[@type='checkbox']")).click();
				break;
			case "COUNTY":
				driver.findElement(By.xpath("//td/span[text()='COUNTY']/../../td/input[@type='checkbox']")).click();
				break;
			case "COUNTRY":
				driver.findElement(By.xpath("//td/span[text()='COUNTRY']/../../td/input[@type='checkbox']")).click();
				break;
			case "CITY":
				driver.findElement(By.xpath("//td/span[text()='CITY']/../../td/input[@type='checkbox']")).click();
				break;
			case "DISTRICT":
				driver.findElement(By.xpath("//td/span[text()='DISTRICT']/../../td/input[@type='checkbox']")).click();
				break;
			case "STATE COUNTY":
				driver.findElement(By.xpath("//td/span[text()='STATE']/../../td/input[@type='checkbox']")).click();
				driver.findElement(By.xpath("//td/span[text()='COUNTY']/../../td/input[@type='checkbox']")).click();
				break;
			case "STATE CITY":
				driver.findElement(By.xpath("//td/span[text()='STATE']/../../td/input[@type='checkbox']")).click();
				driver.findElement(By.xpath("//td/span[text()='CITY']/../../td/input[@type='checkbox']")).click();
				break;
			case "STATE DISTRICT":
				driver.findElement(By.xpath("//td/span[text()='STATE']/../../td/input[@type='checkbox']")).click();
				driver.findElement(By.xpath("//td/span[text()='DISTRICT']/../../td/input[@type='checkbox']")).click();
				break;
			case "STATE COUNTY DISTRICT":
				driver.findElement(By.xpath("//td/span[text()='STATE']/../../td/input[@type='checkbox']")).click();
				driver.findElement(By.xpath("//td/span[text()='DISTRICT']/../../td/input[@type='checkbox']")).click();
				driver.findElement(By.xpath("//td/span[text()='COUNTY']/../../td/input[@type='checkbox']")).click();
				break;
			default:
			}
			click(DT.DISTRIBUTETEAX);
			waitUntilElementInvisibe(30, "//div[@class='blockUI blockMsg blockPage']", driver);
			waitforElement(180, DT.SUCESSALERT, driver);
			if (VerifyObject(driver, PUL.ANERRORHASOCCURED) || VerifyObject(driver, DT.ERROR_DISTRIBUTETAX)) {
				if (VerifyObject(driver, PUL.OKERROR)) {
					click(PUL.OKERROR);
					waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
				}
				Thread.sleep(3000);
			}
			if (VerifyObject(driver, DT.RETUTNTOTOP)) {
				TaxRows = DT.NoofTaxRows.size();
				float sum = 0.00f;
				for (int k = 1; k < TaxRows; k++) {
					Taxxpath = driver
							.findElement(By.xpath(
									"//table[@class='table table-striped table-bordered']/tbody/tr[" + k + "]/td[8]"))
							.getText();
					splitdollar = Taxxpath.substring(1);
					Float taxAmount = Float.parseFloat(splitdollar.replace(",", ""));
					sum = sum + taxAmount;
				}
				TotalSum = driver
						.findElement(By.xpath(
								"//table[@class='table table-striped table-bordered']/tbody/tr[" + TaxRows + "]/td[8]"))
						.getText();
				TotalSumsplit = TotalSum.substring(1);
				Float.parseFloat(TotalSumsplit.replace(",", ""));
				if (!TotalTax.isEmpty()) {
					DTotaltax = getText(DT.DTOTALTAX);
					Assert.assertEquals(DTotaltax, TotalTax);
					click(DT.RETUTNTOTOP);
					waitforElement(180, DT.TRANSDATE, driver);
				}
			}
		}
		TransactionPostEndTime = xGetCurrentTime();
	}

	public void UploadAdjustmentFile(String Filename, String Messagetype, String File_Format, String Validate,
			String sucflag, String errflag) throws Exception {

		String path = null;
		String DirName = null;
		Robot robot = new Robot();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		UploadAdjustmentFile UAF = PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PortalFunctions DownloadFiles = PageFactory.initElements(driver, PortalFunctions.class);
		// String DownloadFilepath = xDownloadFiles("AdjustmentFiles");
		String AdjustFile;
		String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
		String AutoITpath = xAutoIT();
		String LatestSucc = null;
		int count = 0, AdjustFilesCount;
		Path RSource, RTarget;
		WebElement Original, Sucess, Error;
		StringSelection stringSelection;
		File directory = new File(".//");
		String Filename1, Condition, browser, OriginalFile, SuccessFile_Hide, ErrorFile, SuccessFile, ErrorFile_Hide,
				ProcessedFile, AppTXTFile, name, Suc_File;
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		waitforElement(180, PUL.taxesHover, driver);
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (VerifyObject(driver, UAF.UNPUBLISHED)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000);
				count = count + 1;
				if (count == 250) {
					break;
				}
				if (!VerifyObject(driver, UAF.UNPUBLISHED)) {
					report = true;
				}
			} while (report == false);
		}
		waitforElement(120, UAF.UPLOADNEWFILE, driver);
		RSource = Paths.get(AdjustmentFilePath + "\\" + Filename + ".csv");
		Filename1 = Filename + RND();
		RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + ".csv");
		Files.copy(RSource, RTarget);
		Filename = Filename1;
		AdjustFilesCount = driver.findElements(By.xpath("//table/tbody/tr/td[contains(.,'" + Filename + "')]")).size();
		Filename = xCSVUDateUpdation(AdjustmentFilePath + "\\" + Filename + ".csv");
		JavaScriptclick(driver, UAF.UPLOADNEWFILE);
		Condition = Messagetype;
		browser = Browser;
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UAF.ADJUSTMENTFILEFORMAT, driver);
		switch (Condition) {
		case "Distribute Tax":
		case "Invoice":
		case "Quotation":
		case "Accrual":
		case "Asset/Goods Movement":
		case "Inventory Removal":
		case "Purchase Order":
		case "Invoice Verification":
			selectByValue(UAF.ADJUSTMENTFILEFORMAT, File_Format);
			Thread.sleep(5000);
			selectByValue(UAF.MESSAGETYPE, Messagetype);
			if ((Browser.equalsIgnoreCase("Chrome")) || (Browser.equalsIgnoreCase("IE"))) {
				click(UAF.BROWSE);
				Thread.sleep(5000);
			} else {
				JavaScriptclick(driver, UAF.BROWSE);
			}
			switch (browser) {
			case "IE":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
				} else {
					Runtime.getRuntime()
							.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			case "Firefox":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			case "Chrome":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			case "Safari":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			default:
			}
			Thread.sleep(3000);
			if ((Browser.equalsIgnoreCase("IE"))) {
				click(UAF.UPLOADBUTTON);
			} else {
				JavaScriptclick(driver, UAF.UPLOADBUTTON);
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(120, UAF.UPLOADNEWFILE, driver);
			if (AdjustFilesCount > 0) {
				AdjustFile = Filename1 + " " + "(" + (AdjustFilesCount + 1) + ")";
			} else {
				AdjustFile = Filename1;
			}
			OriginalFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Original File')]";
			Original = driver.findElement(By.xpath(OriginalFile));
			OriginalFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Original File')]";
			Original = driver.findElement(By.xpath(OriginalFile));
			if (!VerifyObject(driver, OriginalFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, UAF.UPLOADNEWFILE, driver);
					count = count + 1;
					if (count == 18) {
						break;
					}
					if (VerifyObject(driver, OriginalFile)) {
						report = true;
					}
				} while (report == false);
			}

			// Original File Download
			assertTrue(VerifyObject(driver, OriginalFile));
			JavaScriptclick(driver, Original);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptMousehover(driver, PUL.taxesHover);
				JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_HOME);
				robot.keyRelease(KeyEvent.VK_HOME);
				Thread.sleep(5000);
				stringSelection = new StringSelection(
						directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\");
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.delay(5000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.delay(1000);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdjustmentFiles";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(5000);
				new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
			}
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
				Thread.sleep(5000);
			}
			assertTrue(VerifyObject(driver, OriginalFile));
			SuccessFile_Hide = "//table/tbody/tr/td[contains(.,'" + AdjustFile
					+ "')]/../td/a[contains(.,'Success File') and @class='ng-hide']";
			ErrorFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Error File')]";
			SuccessFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Success File')]";
			ErrorFile_Hide = "//table/tbody/tr/td[contains(.,'" + AdjustFile
					+ "')]/../td/a[contains(.,'Error File') and @class='ng-hide']";
			// waiting for Processed status
			ProcessedFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td[contains(.,'Processed')]";
			PROCESSFILE: if (!VerifyObject(driver, ProcessedFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, UAF.UPLOADNEWFILE, driver);
					Thread.sleep(5000);
					if (!VerifyObject(driver, ErrorFile_Hide) && VerifyObject(driver, SuccessFile_Hide)) {
						break PROCESSFILE;
					}
					count = count + 1;
					if (count == 90) {
						break;
					}
					if (VerifyObject(driver, ProcessedFile)) {
						report = true;
					}
				} while (report == false);

				assertTrue(VerifyObject(driver, ProcessedFile));
			}

			if (sucflag.equalsIgnoreCase("Yes") && errflag.equalsIgnoreCase("Yes")) {

				assertTrue(driver.findElement(By.xpath(SuccessFile)).isDisplayed());
				assertTrue(driver.findElement(By.xpath(ErrorFile)).isDisplayed());
				File tempfile = null;
				File tempfile2 = null;
				Sucess = driver.findElement(By.xpath(SuccessFile));
				JavaScriptclick(driver, Sucess);
				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("Chrome")) {
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "AdjustmentFiles";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(5000);
					tempfile = new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
					LatestSucc = tempfile.toString();
				}
				// LatestDownloadedFile();
				if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
					LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
				}
				LatestSucc = xCSVUDateInputUpdation(LatestSucc);
				AppTXTFile = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "AdjustmentFiles" + "\\" + "App_Suc_File" + RND() + ".txt";
				FileWriter writer1 = new FileWriter(AppTXTFile, true);
				writer1.write(LatestSucc);
				writer1.close();
				tempfile = new File(AppTXTFile);
				DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "AdjustmentFiles";
				name = Validate;
				Suc_File = DirName + "\\" + name + ".csv";
				Suc_File = xCSVUDateInputUpdation(Suc_File);
				FileWriter writer = new FileWriter(DirName + "Suc_File" + RND() + ".txt", true);
				writer.write(Suc_File);
				writer.close();
				tempfile2 = new File(DirName + "Suc_File" + RND() + ".txt");
				Thread.sleep(5000);
				if (Validate != "") {
					@SuppressWarnings("unchecked")
					ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
					@SuppressWarnings("unchecked")
					ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
					f1.removeAll(f2);
					// status=(f1.size()==0)==true? status: status+ "Fail,CSV files are not equal
					// for the file"+ActualFilename+" ;";
				}

			} else if (sucflag.equalsIgnoreCase("Yes")) {
				assertTrue(driver.findElement(By.xpath(SuccessFile)).isDisplayed());
				File tempfile = null;
				File tempfile2 = null;
				Sucess = driver.findElement(By.xpath(SuccessFile));
				JavaScriptclick(driver, Sucess);
				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("Chrome")) {
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "AdjustmentFiles";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(5000);
					tempfile = new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
					LatestSucc = tempfile.toString();
				}
				// LatestDownloadedFile();
				if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
					LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
				}
				LatestSucc = xCSVUDateInputUpdation(LatestSucc);
				AppTXTFile = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "AdjustmentFiles" + "\\" + "App_Suc_File" + RND() + ".txt";
				FileWriter writer1 = new FileWriter(AppTXTFile, true);
				writer1.write(LatestSucc);
				writer1.close();
				tempfile = new File(AppTXTFile);
				DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "AdjustmentFiles";
				name = Validate;
				Suc_File = DirName + "\\" + name + ".csv";
				Suc_File = xCSVUDateInputUpdation(Suc_File);
				FileWriter writer = new FileWriter(DirName + "Suc_File" + RND() + ".txt", true);
				writer.write(Suc_File);
				writer.close();
				tempfile2 = new File(DirName + "Suc_File" + RND() + ".txt");
				Thread.sleep(5000);
				if (Validate != "") {
					@SuppressWarnings("unchecked")
					ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
					@SuppressWarnings("unchecked")
					ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
					f1.removeAll(f2);
					// status=(f1.size()==0)==true? status: status+ "Fail,CSV files are not equal
					// for the file"+ActualFilename+" ;";
				}
			} else {
				// Only Error File Download
				if (errflag.equalsIgnoreCase("Yes")) {
					assertTrue(driver.findElement(By.xpath(ErrorFile)).isDisplayed());
					Error = driver.findElement(By.xpath(ErrorFile));
					File tempfile = null;
					JavaScriptclick(driver, Error);
					Thread.sleep(10000);
					if (Browser.equalsIgnoreCase("Chrome")) {
						path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
								+ "AdjustmentFiles";
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(5000);
						tempfile = new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
						LatestSucc = tempfile.toString();
					}
					// LatestDownloadedFile();
					if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
						LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
					}
				}
			}
		}
		TransactionPostEndTime = xGetCurrentTime();
	}

	public void UploadAdjustmentFile(String Filename, String Messagetype, String File_Format, String Validate)
			throws Exception {

		String path = null;
		String DirName = null;
		Robot robot = new Robot();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		UploadAdjustmentFile UAF = PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PortalFunctions DownloadFiles = PageFactory.initElements(driver, PortalFunctions.class);
		String DownloadFilepath = xDownloadFiles("AdjustmentFiles");
		String AdjustFile;
		String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
		String AutoITpath = xAutoIT();
		String LatestSucc = null;
		int count = 0, AdjustFilesCount;
		Path RSource, RTarget;
		WebElement Original, Sucess, Error;
		StringSelection stringSelection;
		File directory = new File(".//");
		String Filename1, Condition, browser, OriginalFile, SuccessFile_Hide, ErrorFile, SuccessFile, ErrorFile_Hide,
				ProcessedFile, AppTXTFile, name, Suc_File;
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		waitforElement(180, PUL.taxesHover, driver);
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (VerifyObject(driver, UAF.UNPUBLISHED)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000);
				count = count + 1;
				if (count == 180) {
					break;
				}
				if (!VerifyObject(driver, UAF.UNPUBLISHED)) {
					report = true;
				}
			} while (report == false);
		}
		waitforElement(120, UAF.UPLOADNEWFILE, driver);
		RSource = Paths.get(AdjustmentFilePath + "\\" + Filename + ".csv");
		Filename1 = Filename + RND();
		RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + ".csv");
		Files.copy(RSource, RTarget);
		Filename = Filename1;
		AdjustFilesCount = driver.findElements(By.xpath("//table/tbody/tr/td[contains(.,'" + Filename + "')]")).size();
		Filename = xCSVUDateUpdation(AdjustmentFilePath + "\\" + Filename + ".csv");
		JavaScriptclick(driver, UAF.UPLOADNEWFILE);
		Condition = Messagetype;
		browser = Browser;
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UAF.ADJUSTMENTFILEFORMAT, driver);
		switch (Condition) {
		case "Distribute Tax":
		case "Invoice":
		case "Quotation":
		case "Accrual":
		case "Asset/Goods Movement":
		case "Inventory Removal":
		case "Purchase Order":
		case "Invoice Verification":
			selectByValue(UAF.ADJUSTMENTFILEFORMAT, File_Format);
			Thread.sleep(5000);
			selectByValue(UAF.MESSAGETYPE, Messagetype);
			if ((Browser.equalsIgnoreCase("Chrome")) || (Browser.equalsIgnoreCase("IE"))) {
				click(UAF.BROWSE);
				Thread.sleep(5000);
			} else {
				JavaScriptclick(driver, UAF.BROWSE);
			}
			switch (browser) {
			case "IE":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
				} else {
					Runtime.getRuntime()
							.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			case "Firefox":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			case "Chrome":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			case "Safari":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			default:
			}
			Thread.sleep(3000);
			if ((Browser.equalsIgnoreCase("IE"))) {
				click(UAF.UPLOADBUTTON);
			} else {
				JavaScriptclick(driver, UAF.UPLOADBUTTON);
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(120, UAF.UPLOADNEWFILE, driver);
			if (AdjustFilesCount > 0) {
				AdjustFile = Filename1 + " " + "(" + (AdjustFilesCount + 1) + ")";
			} else {
				AdjustFile = Filename1;
			}
			OriginalFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Original File')]";
			Original = driver.findElement(By.xpath(OriginalFile));
			OriginalFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Original File')]";
			Original = driver.findElement(By.xpath(OriginalFile));
			if (!VerifyObject(driver, OriginalFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, UAF.UPLOADNEWFILE, driver);
					count = count + 1;
					if (count == 18) {
						break;
					}
					if (VerifyObject(driver, OriginalFile)) {
						report = true;
					}
				} while (report == false);
			}

			// Original File Download
			assertTrue(VerifyObject(driver, OriginalFile));
			JavaScriptclick(driver, Original);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptMousehover(driver, PUL.taxesHover);
				JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_HOME);
				robot.keyRelease(KeyEvent.VK_HOME);
				Thread.sleep(5000);
				stringSelection = new StringSelection(
						directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\");
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.delay(5000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.delay(1000);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdjustmentFiles";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(5000);
				new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
			}
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
				Thread.sleep(5000);
			}
			SuccessFile_Hide = "//table/tbody/tr/td[contains(.,'" + AdjustFile
					+ "')]/../td/a[contains(.,'Success File') and @class='ng-hide']";
			ErrorFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Error File')]";
			SuccessFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Success File')]";
			ErrorFile_Hide = "//table/tbody/tr/td[contains(.,'" + AdjustFile
					+ "')]/../td/a[contains(.,'Error File') and @class='ng-hide']";
			// waiting for Processed status
			ProcessedFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td[contains(.,'Processed')]";
			PROCESSFILE: if (!VerifyObject(driver, ProcessedFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, UAF.UPLOADNEWFILE, driver);
					Thread.sleep(5000);
					if (!VerifyObject(driver, ErrorFile_Hide) && VerifyObject(driver, SuccessFile_Hide)) {
						break PROCESSFILE;
					}
					count = count + 1;
					if (count == 90) {
						break;
					}
					if (VerifyObject(driver, ProcessedFile)) {
						report = true;
					}
				} while (report == false);
			}

			assertTrue(VerifyObject(driver, ProcessedFile));

			if (!VerifyObject(driver, SuccessFile_Hide) && VerifyObject(driver, ErrorFile_Hide)) {
				File tempfile = null;
				File tempfile2 = null;
				Sucess = driver.findElement(By.xpath(SuccessFile));
				JavaScriptclick(driver, Sucess);
				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptMousehover(driver, PUL.taxesHover);
					JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_HOME);
					robot.keyRelease(KeyEvent.VK_HOME);
					Thread.sleep(5000);
					stringSelection = new StringSelection(
							directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\");
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.delay(5000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(1000);
					Thread.sleep(5000);
				}
				if (Browser.equalsIgnoreCase("Chrome")) {
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "AdjustmentFiles";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(5000);
					tempfile = new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
					LatestSucc = tempfile.toString();
				}
				// LatestDownloadedFile();
				if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
					LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
				}
				LatestSucc = xCSVUDateInputUpdation(LatestSucc);
				AppTXTFile = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "AdjustmentFiles" + "\\" + "App_Suc_File" + RND() + ".txt";
				FileWriter writer1 = new FileWriter(AppTXTFile, true);
				writer1.write(LatestSucc);
				writer1.close();
				tempfile = new File(AppTXTFile);
				DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "AdjustmentFiles";
				name = Validate;
				Suc_File = DirName + "\\" + name + ".csv";
				Suc_File = xCSVUDateInputUpdation(Suc_File);
				FileWriter writer = new FileWriter(DirName + "Suc_File" + RND() + ".txt", true);
				writer.write(Suc_File);
				writer.close();
				tempfile2 = new File(DirName + "Suc_File" + RND() + ".txt");
				Thread.sleep(5000);
				if (Validate != "") {
					@SuppressWarnings("unchecked")
					ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
					@SuppressWarnings("unchecked")
					ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
					f1.removeAll(f2);
					// status=(f1.size()==0)==true? status: status+ "Fail,CSV files are not equal
					// for the file"+ActualFilename+" ;";
				}

				assertTrue(VerifyObject(driver, SuccessFile));
			}
			// Only Error File Download
			if ((!VerifyObject(driver, ErrorFile_Hide)) && (VerifyObject(driver, SuccessFile_Hide))) {
				Error = driver.findElement(By.xpath(ErrorFile));
				File tempfile = null;
				JavaScriptclick(driver, Error);
				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptMousehover(driver, PUL.taxesHover);
					JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_HOME);
					robot.keyRelease(KeyEvent.VK_HOME);
					Thread.sleep(5000);
					stringSelection = new StringSelection(DownloadFilepath + "\\");
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.delay(5000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(1000);
					Thread.sleep(5000);
				}

				if (Browser.equalsIgnoreCase("Chrome")) {
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "AdjustmentFiles";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(5000);
					tempfile = new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
					LatestSucc = tempfile.toString();
				}
				// LatestDownloadedFile();
				if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
					LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
				}
				assertTrue(VerifyObject(driver, ErrorFile));
			}
		}
		TransactionPostEndTime = xGetCurrentTime();
	}

	public void ReturnsOnlyAdjustment(String Filename, String File_Format, String Validate) throws Exception {

		String path = null;
		String DirName = null;
		Robot robot = new Robot();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		UploadAdjustmentFile UAF = PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PortalFunctions DownloadFiles = PageFactory.initElements(driver, PortalFunctions.class);
		String DownloadFilepath = xDownloadFiles("AdjustmentFiles");
		String AdjustFile;
		String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
		String AutoITpath = xAutoIT();
		String LatestSucc = null;
		int count = 0, AdjustFilesCount;
		Path RSource, RTarget;
		WebElement Original, Sucess, Error;
		StringSelection stringSelection;
		File directory = new File(".//");
		String Filename1, browser, OriginalFile, SuccessFile_Hide, ErrorFile, SuccessFile, ErrorFile_Hide,
				ProcessedFile, AppTXTFile, name, Suc_File;
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		waitforElement(90, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, UAF.EXTENDEDHELP, driver);
		if (VerifyObject(driver, UAF.UNPUBLISHED)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000);
				count = count + 1;
				if (count == 150) {
					break;
				}
				if (!VerifyObject(driver, UAF.UNPUBLISHED)) {
					report = true;
				}
			} while (report == false);
		}
		waitforElement(120, UAF.RETURNSONLYUPLOADNEWFILE, driver);
		RSource = Paths.get(AdjustmentFilePath + "\\" + Filename + ".csv");
		Filename1 = Filename + RND();
		RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + ".csv");
		Files.copy(RSource, RTarget);
		Filename = Filename1;
		AdjustFilesCount = driver.findElements(By.xpath("//table/tbody/tr/td[contains(.,'" + Filename + "')]")).size();
		Filename = xCSVUDateUpdation(AdjustmentFilePath + "\\" + Filename + ".csv");
		JavaScriptclick(driver, UAF.RETURNSONLYUPLOADNEWFILE);
		// Condition = Messagetype;
		browser = Browser;
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UAF.ADJUSTMENTFILEFORMAT, driver);
		// switch(Condition)
		selectByValue(UAF.FILEFORMAT, File_Format);
		Thread.sleep(3000);
		if ((Browser.equalsIgnoreCase("Chrome")) || (Browser.equalsIgnoreCase("IE"))) {
			click(UAF.BROWSE);
			Thread.sleep(5000);
		} else {
			JavaScriptclick(driver, UAF.BROWSE);
		}
		switch (browser) {
		case "IE":
			if (AdjustmentFilePath.isEmpty() == true) {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
			} else {
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
			}
			break;
		case "Firefox":
			if (AdjustmentFilePath.isEmpty() == true) {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
			} else {
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
			}
			break;
		case "Chrome":
			if (AdjustmentFilePath.isEmpty() == true) {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
			} else {
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
			}
			break;
		case "Safari":
			if (AdjustmentFilePath.isEmpty() == true) {
				Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
			} else {
				Runtime.getRuntime()
						.exec(AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
			}
			break;
		}
		Thread.sleep(3000);
		if ((Browser.equalsIgnoreCase("IE"))) {
			click(UAF.UPLOADBUTTON);
		} else {
			JavaScriptclick(driver, UAF.UPLOADBUTTON);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, UAF.UPLOADNEWFILE, driver);
		if (AdjustFilesCount > 0) {
			AdjustFile = Filename1 + " " + "(" + (AdjustFilesCount + 1) + ")";
		} else {
			AdjustFile = Filename1;
		}
		OriginalFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Original File')]";
		Original = driver.findElement(By.xpath(OriginalFile));
		OriginalFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Original File')]";
		Original = driver.findElement(By.xpath(OriginalFile));
		if (!VerifyObject(driver, OriginalFile)) {
			boolean report = false;
			count = 0;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, UAF.RETURNSONLYUPLOADNEWFILE, driver);
				count = count + 1;
				if (count == 18) {
					break;
				}
				if (VerifyObject(driver, OriginalFile)) {
					report = true;
				}
			} while (report == false);
		}
		// Original File Download
		if (VerifyObject(driver, OriginalFile)) {
			JavaScriptclick(driver, Original);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptMousehover(driver, PUL.ReturnsHover);
				JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_HOME);
				robot.keyRelease(KeyEvent.VK_HOME);
				Thread.sleep(5000);
				stringSelection = new StringSelection(
						directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\");
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.delay(5000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.delay(1000);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdjustmentFiles";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(5000);
				new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
			}
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
				Thread.sleep(5000);
			}
		}
		SuccessFile_Hide = "//table/tbody/tr/td[contains(.,'" + AdjustFile
				+ "')]/../td/a[contains(.,'Success File') and @class='ng-hide']";
		ErrorFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Error File')]";
		SuccessFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Success File')]";
		ErrorFile_Hide = "//table/tbody/tr/td[contains(.,'" + AdjustFile
				+ "')]/../td/a[contains(.,'Error File') and @class='ng-hide']";
		// waiting for Processed status
		ProcessedFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td[contains(.,'Processed')]";
		PROCESSFILE: if (!VerifyObject(driver, ProcessedFile)) {
			boolean report = false;
			count = 0;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, UAF.RETURNSONLYUPLOADNEWFILE, driver);
				Thread.sleep(5000);
				if (!VerifyObject(driver, ErrorFile_Hide) && VerifyObject(driver, SuccessFile_Hide)) {
					break PROCESSFILE;
				}
				count = count + 1;
				if (count == 90) {
					break;
				}
				if (VerifyObject(driver, ProcessedFile)) {
					report = true;
				}
			} while (report == false);
		}
		if (!VerifyObject(driver, SuccessFile_Hide) && VerifyObject(driver, ErrorFile_Hide)) {
			File tempfile = null;
			File tempfile2 = null;
			Sucess = driver.findElement(By.xpath(SuccessFile));
			JavaScriptclick(driver, Sucess);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptMousehover(driver, PUL.ReturnsHover);
				JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_HOME);
				robot.keyRelease(KeyEvent.VK_HOME);
				Thread.sleep(5000);
				stringSelection = new StringSelection(
						directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\");
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.delay(5000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.delay(1000);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdjustmentFiles";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(5000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
				LatestSucc = tempfile.toString();
			}
			// LatestDownloadedFile();
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
			}
			LatestSucc = xCSVUDateInputUpdation(LatestSucc);
			AppTXTFile = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdjustmentFiles"
					+ "\\" + "App_Suc_File" + RND() + ".txt";
			FileWriter writer1 = new FileWriter(AppTXTFile, true);
			writer1.write(LatestSucc);
			writer1.close();
			tempfile = new File(AppTXTFile);
			DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "AdjustmentFiles";
			name = Validate;
			Suc_File = DirName + "\\" + name + ".csv";
			Suc_File = xCSVUDateInputUpdation(Suc_File);
			FileWriter writer = new FileWriter(DirName + "Suc_File" + RND() + ".txt", true);
			writer.write(Suc_File);
			writer.close();
			tempfile2 = new File(DirName + "Suc_File" + RND() + ".txt");
			Thread.sleep(5000);
			if (Validate != "") {
				@SuppressWarnings("unchecked")
				ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
				@SuppressWarnings("unchecked")
				ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				f1.removeAll(f2);
				// status=(f1.size()==0)==true? status: status+ "Fail,CSV files are not equal
				// for the file"+ActualFilename+" ;";
			}
		}
		// Only Error File Download
		if ((!VerifyObject(driver, ErrorFile_Hide)) && (VerifyObject(driver, SuccessFile_Hide))) {
			Error = driver.findElement(By.xpath(ErrorFile));
			File tempfile = null;
			JavaScriptclick(driver, Error);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptMousehover(driver, PUL.ReturnsHover);
				JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_HOME);
				robot.keyRelease(KeyEvent.VK_HOME);
				Thread.sleep(5000);
				stringSelection = new StringSelection(DownloadFilepath + "\\");
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.delay(5000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.delay(1000);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "AdjustmentFiles";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(5000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
				LatestSucc = tempfile.toString();
			}
			// LatestDownloadedFile();
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
			}
		}

		TransactionPostEndTime = xGetCurrentTime();
	}

	public void syncTime() throws Exception {

		String CurrentTime = xGetCurrentTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date1 = sdf.parse(CurrentTime);
		Date date2 = sdf.parse(TransactionPostEndTime);
		long DifferenceTime = date1.getTime() - date2.getTime();
		long min = DifferenceTime / (60 * 1000) % 60;
		System.out.println(min * 60 * 1000);
		if (min < 15) {
			Thread.sleep(900000 - (min * 60 * 1000));
		}
	}

	public void UploadAdjustment(String Messagetype, String Filename, String File_Format, String NotCSVFile,
			String RetunsOnly, String Foldername) throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		UploadAdjustmentFile UAF = PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		int count = 0;
		xDownloadFiles("AdjustmentFiles");
		PageFactory.initElements(driver, PortalFunctions.class);
		PageFactory.initElements(driver, ExceptionsPage.class);
		new Robot();
		String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
		String AutoITpath = xAutoIT();
		String Filename1, Condition, browser, Folderpath, AppFName, OriginalFile;
		if (VerifyObject(driver, UAF.UNPUBLISHED)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000);
				count = count + 1;
				if (count == 25) {
					break;
				}
				if (!VerifyObject(driver, UAF.UNPUBLISHED)) {
					report = true;
				}
			} while (report == false);
		}
		// waitforElement(180,UAF.UPLOADNEWFILE,driver);
		Filename1 = Filename;
		Condition = Messagetype;
		browser = Browser;
		if (RetunsOnly.equalsIgnoreCase("Yes")) {
			JavaScriptMousehover(driver, PUL.ReturnsHover);
			JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, UAF.RETURNSONLYUPLOADNEWFILE, driver);
			Thread.sleep(5000);
			JavaScriptclick(driver, UAF.RETURNSONLYUPLOADNEWFILE);
		} else {
			JavaScriptMousehover(driver, PUL.taxesHover);
			JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, UAF.UPLOADNEWFILE, driver);
			Thread.sleep(5000);
			JavaScriptclick(driver, UAF.UPLOADNEWFILE);
		}
	
		Folderpath = AdjustmentFilePath + "\\" + Foldername;
		;
		if (!NotCSVFile.equalsIgnoreCase("Yes")) {
			Filename = Folderpath + "\\" + Filename;
			Filename = xCSVUDateUpdation(Filename);
			Thread.sleep(2000);

			switch (Condition) {
			case "Distribute Tax":
			case "Invoice":
			case "Quotation":
			case "Accrual":
			case "Asset/Goods Movement":
			case "Inventory Removal":
			case "Purchase Order":
			case "ReturnsOnly":
				selectByValue(UAF.ADJUSTMENTFILEFORMAT, File_Format);
				Thread.sleep(10000);

				if (!RetunsOnly.equalsIgnoreCase("Yes")) {
					waitforElement(180, UAF.MESSAGETYPE, driver);
					selectByValue(UAF.MESSAGETYPE, Messagetype);
					Thread.sleep(5000);
				}
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptclick(driver, UAF.BROWSE);
				} else {
					click(UAF.BROWSE);
				}
				Thread.sleep(1000);// to wait for the file system window attched browser (AutoIt)
				switch (browser) {
				case "IE":
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
					break;
				case "Firefox":
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
					break;
				case "Chrome":
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
					break;
				case "Safari":
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
					break;
				}
				Thread.sleep(5000);
				JavaScriptclick(driver, UAF.UPLOADBUTTON);
				Thread.sleep(5000);
			}
			AppFName = Filename1.split("\\.")[0];
			OriginalFile = "//table/tbody/tr/td[contains(.,'" + AppFName + "')]/../td/a[contains(.,'Original File')]";
			if (!VerifyObject(driver, OriginalFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitforElement(180, UAF.UPLOADNEWFILE, driver);
					count = count + 1;
					if (count == 18) {
						break;
					}
					if (VerifyObject(driver, OriginalFile)) {
						report = true;
					}
				} while (report == false);
			}
		} else {

			Thread.sleep(5000);
			switch (Condition) {
			case "Distribute Tax":
			case "Invoice":
			case "Quotation":
			case "Accrual":
			case "Asset/Goods Movement":
			case "Inventory Removal":
			case "Purchase Order":
			case "ReturnsOnly":
				selectByValue(UAF.ADJUSTMENTFILEFORMAT, CloudData.get_data("File_Format"));
				if (!RetunsOnly.equalsIgnoreCase("Yes")) {
					waitforElement(180, UAF.MESSAGETYPE, driver);
					selectByValue(UAF.MESSAGETYPE, Messagetype);
				}
				Thread.sleep(1000);
				JavaScriptclick(driver, UAF.BROWSE);
				Thread.sleep(1000);// to wait for the file system window attched browser (AutoIt)
				switch (Browser) {
				case "IE":
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Folderpath + "\\" + Filename1);
					break;
				case "Firefox":
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Folderpath + "\\" + Filename1);
					break;
				case "Chrome":
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Folderpath + "\\" + Filename1);
					break;
				case "Safari":
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Folderpath + "\\" + Filename1);
					break;
				}
				Thread.sleep(5000);
				click(UAF.UPLOADBUTTON);
				Thread.sleep(2000);
				waitforElement(360, "//td[contains(.,'" + Filename1 + "')]", driver);
				assertTrue(driver.findElement(By.xpath("//td[contains(.,'" + Filename1 + "')]")).isDisplayed());
			}
		}
		TransactionPostEndTime = xGetCurrentTime();
	}

	public void DownloadAdjustmentFolders(String Filename, String NotCSVFile, String RetunsOnly, String Foldername)
			throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String Filename1, AppFName, OriginalFile, SuccessFile_Hide, ErrorFile, SuccessFile, ErrorFile_Hide,
				Success_Error;

		if (!NotCSVFile.equalsIgnoreCase("Yes")) {
			if (RetunsOnly.equalsIgnoreCase("Yes")) {
				JavaScriptMousehover(driver, PUL.ReturnsHover);
				JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
			} else {
				JavaScriptMousehover(driver, PUL.taxesHover);
				JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
			}

			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(5000);
			waitforElement(180, CNP.PAGESIZE, driver);
			Filename1 = Filename;
			AppFName = Filename1.split("\\.csv")[0];
			OriginalFile = "//table/tbody/tr/td[contains(.,'" + AppFName + "')]/../td/a[contains(.,'Original File')]";
			selectByValue(CNP.PAGESIZE, "100 per page");
			Thread.sleep(5000);
			if (VerifyObject(driver, OriginalFile)) {
				WebElement Original = driver.findElement(By.xpath(OriginalFile));
				JavaScriptclick(driver, Original);
				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("Firefox")) {
					LatestDownloadedFile("AdjustmentFiles" + "\\" + Foldername + "\\" + "OriginalFiles", false);
				}
			}
			SuccessFile_Hide = "//table/tbody/tr/td[contains(.,'" + AppFName
					+ "')]/../td/a[@class='ng-binding ng-hide']";
			ErrorFile = "//table/tbody/tr/td[contains(.,'" + AppFName + "')]/../td/a[contains(.,'Error File')]";
			SuccessFile = "//table/tbody/tr/td[contains(.,'" + AppFName + "')]/../td/a[contains(.,'Success File')]";
			ErrorFile_Hide = "//table/tbody/tr/td[contains(.,'" + AppFName
					+ "')]/../td/a[contains(.,'Error File') and @class='ng-hide']";
			Success_Error = "//table/tbody/tr/td[contains(.,'" + AppFName + "')]/..//td/a[@class='ng-binding']";

			if (VerifyObject(driver, Success_Error) && (!VerifyObject(driver, ErrorFile_Hide))) {
				WebElement Sucess = driver.findElement(By.xpath(SuccessFile));
				JavaScriptclick(driver, Sucess);
				Thread.sleep(2000);
				if (Browser.equalsIgnoreCase("Firefox")) {
					LatestDownloadedFile("AdjustmentFiles" + "\\" + Foldername + "\\" + "SuccessFiles", false);
				}
				WebElement Error = driver.findElement(By.xpath(ErrorFile));
				JavaScriptclick(driver, Error);
				Thread.sleep(2000);
				if (Browser.equalsIgnoreCase("Firefox")) {
					LatestDownloadedFile("AdjustmentFiles" + "\\" + Foldername + "\\" + "ErrorFiles", false);
				}
			}

			if (VerifyObject(driver, SuccessFile) && VerifyObject(driver, ErrorFile_Hide)) {
				WebElement Sucess = driver.findElement(By.xpath(SuccessFile));
				JavaScriptclick(driver, Sucess);
				Thread.sleep(2000);
				if (Browser.equalsIgnoreCase("Firefox")) {
					LatestDownloadedFile("AdjustmentFiles" + "\\" + Foldername + "\\" + "SuccessFiles", false);
				}
			}

			if (VerifyObject(driver, ErrorFile) && VerifyObject(driver, SuccessFile_Hide)) {
				WebElement Error = driver.findElement(By.xpath(ErrorFile));
				JavaScriptclick(driver, Error);
				Thread.sleep(2000);
				if (Browser.equalsIgnoreCase("Firefox")) {
					LatestDownloadedFile("AdjustmentFiles" + "\\" + Foldername + "\\" + "ErrorFiles", false);
				}
			}

		}
	}

	public void AdminUndoAdjustmentFile(String clientname, String folderType, String Year, String Month,
			String Undo_FileName, String UndoFile_Displayed, String Success_Report, String Error_Report)
			throws Exception {

		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, AUA.manageSMBCustomers, driver);
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AUA.smbCustomerName, driver);
		type(AUA.smbCustomerName, clientname);
		Thread.sleep(7000);
		assertTrue(AUA.smbcustomerColumn.isDisplayed());

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

		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, AUA.smbCustomerReports, driver);
		click(AUA.smbCustomerReports);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
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

	public void PortalUndoAdjustmentFileStatus(String Undo_FileName, String AdjustmentFileType,
			String UndoFile_Displayed, String Success_Report, String Error_Report) throws Exception {
		PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String Filename = Undo_FileName;
		String[] ofiles = Filename.split("\\.");
		if (AdjustmentFileType.equalsIgnoreCase("ManageAdjustmentFile")) {
			waitforElement(180, PUL.taxesHover, driver);
			JavaScriptMousehover(driver, PUL.taxesHover);
			JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.UPLOADNEWFILELINK, driver);
			if (UndoFile_Displayed.equalsIgnoreCase("Yes")) {
				if (Success_Report.equalsIgnoreCase("Yes")
						|| Success_Report.equalsIgnoreCase("Yes") && Error_Report.equalsIgnoreCase("Yes")) {
					String successReportChange = driver
							.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'" + ofiles[0]
									+ "')]/../td[5]/a[@class='ng-binding']/../../td[7]"))
							.getText().trim();
					if (successReportChange.equalsIgnoreCase("Deleted Success File")) {
						assertTrue(
								(driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'"
										+ ofiles[0] + "')]/../td[7]")).getText().trim()).equalsIgnoreCase("Deleted"));
					}
					if (Error_Report.equalsIgnoreCase("Yes")) {
						String errorReportChange = driver
								.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'"
										+ ofiles[0] + "')]/../td[6]/a[not (@class='ng-hide')]"))
								.getText().trim();
						assertTrue(errorReportChange.equalsIgnoreCase("Error File"));
					}
				}
			} else {
				if (Success_Report.equalsIgnoreCase("Yes") || Error_Report.equalsIgnoreCase("Yes")
						|| Success_Report.equalsIgnoreCase("Yes") && Error_Report.equalsIgnoreCase("Yes")) {
					String filestatus = driver.findElement(By.xpath(
							"//table/tbody/tr[@class='ng-scope']/td[contains(text(),'" + ofiles[0] + "')]/../td[7]"))
							.getText().trim();
					assertTrue(filestatus.equalsIgnoreCase("Processed"));
				}
			}
		} else {
			waitforElement(180, PUL.ReturnsHover, driver);
			JavaScriptMousehover(driver, PUL.ReturnsHover);
			JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.UPLOADNEWFILELINK, driver);
			if (AdjustmentFileType.equalsIgnoreCase("ReturnsOnly")) {
				if (UndoFile_Displayed.equalsIgnoreCase("Yes")) {
					if (Success_Report.equalsIgnoreCase("Yes")
							|| Success_Report.equalsIgnoreCase("Yes") && Error_Report.equalsIgnoreCase("Yes")) {
						String successReportChange = driver
								.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'"
										+ ofiles[0] + "')]/../td[4]/a"))
								.getText().trim();
						if (successReportChange.equalsIgnoreCase("Deleted Success File")) {
							assertTrue(driver
									.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'"
											+ ofiles[0] + "')]/../td[6]"))
									.getText().trim().equalsIgnoreCase("Deleted"));
						}
						if (Error_Report.equalsIgnoreCase("Yes")) {
							String errorReportChange = driver
									.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'"
											+ ofiles[0] + "')]/../td[5]/a"))
									.getText().trim();
							assertTrue(errorReportChange.equalsIgnoreCase("Error File"));
						}
					}
				} else {
					if (Error_Report.equalsIgnoreCase("Yes")) {
						String filestatus = driver
								.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'"
										+ ofiles[0] + "')]/../td[6]"))
								.getText().trim();
						assertTrue(filestatus.equalsIgnoreCase("Processed"));
					}
				}
			}
		}
	}

	public void AdminsUndoAdjustmentFileNoDeleteBtn(String clietName, String folderType, String Year, String Month,
			String Undo_FileName, String Success_Report, String Error_Report) throws Exception {
		new File(".//");
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, AUA.manageSMBCustomers, driver);
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, AUA.smbCustomerName, driver);
		type(AUA.smbCustomerName, clietName);
		waitforElement(180, AUA.smbcustomerColumn, driver);
		assertTrue(AUA.smbcustomerColumn.isDisplayed());
		assertTrue(AUA.environment.isDisplayed());
		Thread.sleep(5000);
		if (VerifyObject(driver, "//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'" + clietName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement Registered = driver
					.findElement(By.xpath("//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'"
							+ clietName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			JavaScriptclick(driver, Registered);
		} else if (VerifyObject(driver, "//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'" + clietName
				+ "')]/../../../td[contains(text(),'Authorized')]")) {
			WebElement Authorised = driver
					.findElement(By.xpath("//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'"
							+ clietName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			JavaScriptclick(driver, Authorised);
		} else {
			WebElement Initialised = driver
					.findElement(By.xpath("//table/tbody/tr[2][@class='ng-scope']/td/div/a[contains(text(),'"
							+ clietName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
			JavaScriptclick(driver, Initialised);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(5000);
		waitforElement(180, AUA.smbCustomerReports, driver);
		click(AUA.smbCustomerReports);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, AUA.Rootfolder, driver);
		selectByValue(AUA.Rootfolder, folderType);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(4000);
		selectByValue(AUA.Internalyearfolder, Year);
		Thread.sleep(4000);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(4000);
		selectByValue(AUA.InternalMonthfolder, Month);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(5000);
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
			String filestatus = driver.findElement(By.xpath(
					"//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + sfile + "')]/../../span[3]"))
					.getText();
			assertTrue(filestatus.isEmpty());

			if (!VerifyObject(driver, "//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + ofile
					+ "')]/../../span[3]/button[contains(text(),'Delete')]")) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}

		} else if (Success_Report.equalsIgnoreCase("Yes") && Error_Report.equalsIgnoreCase("Yes")) {
			String filestatus = driver.findElement(By.xpath(
					"//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + sfile + "')]/../../span[3]"))
					.getText();
			assertTrue(filestatus.isEmpty());
			String errfilestatus = driver.findElement(By.xpath(
					"//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + efile + "')]/../../span[3]"))
					.getText();
			assertTrue(errfilestatus.isEmpty());
			if (!VerifyObject(driver, "//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + ofile
					+ "')]/../../span[3]/button[contains(text(),'Delete')]")) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		} else {
			String errfilestatus = driver.findElement(By.xpath(
					"//div[2]/div/div/div/div/ul/li/div/span[1]/a[contains(text(),'" + efile + "')]/../../span[3]"))
					.getText();
			assertTrue(errfilestatus.isEmpty());
		}
	}

	public void unsupportedtaxCategories(String TaxCategories) throws Exception {

		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TBS.CTCImpact, driver);
		JavaScriptMousehover(driver, TBS.CTCImpact);
		Thread.sleep(2000);
		JavaScriptclick(driver, TBS.TaxabilityCategories);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String unsupported_taxCategories = TaxCategories;
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

	public void Myusage() throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		click(PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue((PUL.CURRMONTH).isDisplayed() && (PUL.PREVMONTH).isDisplayed() && (PUL.PREVMONTH1).isDisplayed());
	}

	@SuppressWarnings("unchecked")
	public void AddressCleansing(String fileName, String fileExt, String invdFileFormtVld, String fileTemplateVLD,
			String verifySuccessfile, String verifyErrorfile) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Robot robot = new Robot();
		File directory = new File(".//");
		ConfigurePage ConfigPage = PageFactory.initElements(driver, ConfigurePage.class);
		UploadAdjustmentFile UploadPage = PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ProfessionalUserLinks ProfUserLinks = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String DownloadFilepath = xDownloadFiles("AddressCleansing");
		String Filename;
		String AdjustmentFilePath = xUploadAdjustmentFile("AddressCleansing");
		String AutoITpath = xAutoIT();
		File tempfile = null;
		File tempfile2 = null;
		String Filename1 = null;
		int count = 0;
		waitforElement(180, ProfUserLinks.addressCleansing, driver);
		JavaScriptclick(driver, ProfUserLinks.addressCleansing);
		waitUntilElementInvisibe(180, ConfigPage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, ProfUserLinks.addressCleansingheader, driver);

		assertTrue(ProfUserLinks.addressCleansingheader.isDisplayed());
		assertTrue(ProfUserLinks.addressCleansingheaderIcon.isDisplayed());
		assertTrue(ProfUserLinks.addressCleansingtext.isDisplayed());
		assertTrue(ProfUserLinks.addressCleansinguploadNewfilelnk.isDisplayed());
		assertTrue(ProfUserLinks.addressCleansinggridfileuploadDate.isDisplayed());
		assertTrue(ProfUserLinks.addressCleansinggridfileName.isDisplayed());
		assertTrue(ProfUserLinks.originalFIle.isDisplayed());
		assertTrue(ProfUserLinks.successReport.isDisplayed());
		assertTrue(ProfUserLinks.errorReport.isDisplayed());
		waitforElement(180, UploadPage.ADDRESSCLEANSING_UPLOADNEWFILE, driver);
		click(UploadPage.ADDRESSCLEANSING_UPLOADNEWFILE);
		Thread.sleep(5000);
		assertTrue(ProfUserLinks.addOnOption.isDisplayed());
		assertTrue(ProfUserLinks.addOnOptiontext.isDisplayed());
		// click(ProfUserLinks.cancel);
		// Thread.sleep(2000);

		if (fileExt.equalsIgnoreCase(".xls") || fileExt.equalsIgnoreCase(".xlsx") || fileExt.equalsIgnoreCase(".pdf")
				|| fileExt.equalsIgnoreCase(".rar") || fileExt.equalsIgnoreCase(".csv")) {
			Path RSource = Paths.get(AdjustmentFilePath + "\\" + fileName + fileExt);
			Filename1 = fileName + RND();
			Path RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + fileExt);
			Files.copy(RSource, RTarget);
			Filename = Filename1;
			Thread.sleep(6000);
			Filename = xCSVUDateUpdation(AdjustmentFilePath + "\\" + Filename + fileExt);
		}

		/*
		 * JavaScriptclick(driver, ProfUserLinks.addressCleansing);
		 * waitUntilElementInvisibe(180, ConfigPage.NPROGRESSBUSY_XPATH, driver);
		 * waitforElement(180, UploadPage.UPLOADNEWFILE, driver); Thread.sleep(6000); if
		 * (Browser.equalsIgnoreCase("IE")) { JavaScriptclick(driver,
		 * UploadPage.UPLOADNEWFILE); } else { click(UploadPage.UPLOADNEWFILE); }
		 */
		// Thread.sleep(2000);
		waitforElement(180, ProfUserLinks.Continue, driver);
		click(ProfUserLinks.Continue);
		waitUntilElementInvisibe(180, ConfigPage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UploadPage.BROWSE, driver);
		assertTrue(ProfUserLinks.addressCleansingheaderIcon.isDisplayed());
		assertTrue(ProfUserLinks.uploadaddressCleansingfileheader.isDisplayed());

		if (fileTemplateVLD.equalsIgnoreCase("Yes")) {
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(5000);
				JavaScriptclick(driver, ProfUserLinks.downlaodfileformatlnk);
				Thread.sleep(10000);
				robot.keyPress(KeyEvent.VK_ALT);
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_S);
				;
				Runtime.getRuntime().exec(AutoITpath + "\\FileDownloadIE.exe " + DownloadFilepath);
			} else {
				click(ProfUserLinks.downlaodfileformatlnk);
			}
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "AddressCleansing";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				tempfile = new File(LatestDownloadedFile("AddressCleansing", true));

			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				tempfile = new File(LatestDownloadedFile("AddressCleansing", false));
				Thread.sleep(20000);
			}
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
					+ "AddressCleansing";
			if (FileName.contains("]")) {
				tempfile2 = new File(DirName + "\\" + FileName.replace("]", ""));
			} else {
				tempfile2 = new File(DirName + "\\" + FileName);
			}
			Thread.sleep(5000);
			ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
			ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
			f1.removeAll(f2);
			// assertTrue(f1.size() == 0);
		}
		if ((Browser.equalsIgnoreCase("Firefox")) || (Browser.equalsIgnoreCase("IE"))) {
			JavaScriptclick(driver, UploadPage.BROWSE);
		} else {
			click(UploadPage.BROWSE);
		}
		Thread.sleep(10000);
		// Calling AutoIT Browser function
		fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename1, fileExt);
		Thread.sleep(6000);
		if (fileExt.equalsIgnoreCase(".xls") || fileExt.equalsIgnoreCase(".xlsx") || fileExt.equalsIgnoreCase(".pdf")
				|| fileExt.equalsIgnoreCase(".rar")) {
			assertTrue(UploadPage.uplaodbtndisabledinvalifile.isDisplayed());
		} else {
			click(UploadPage.UPLOADBUTTON);
			Thread.sleep(300000);
			// waitforElement(180, UAF.UPLOADNEWFILE, driver);
		}
		if (invdFileFormtVld.equalsIgnoreCase("Yes")) {
			assertTrue(UploadPage.ERRORINVALIDUPLOAD.isDisplayed());
			click(UploadPage.RETURNTOADDRESSCLEANSINGFILES);
			waitforElement(180, UploadPage.UPLOADNEWFILE, driver);
		} else {
			assertTrue(driver
					.findElement(By.xpath(
							"//table/tbody/tr[@class='ng-scope']/td[contains(text(),'" + Filename1 + "')]/../td[6]"))
					.getText().trim().equalsIgnoreCase("Processing"));
			JavaScriptclick(driver, ProfUserLinks.addressCleansing);
			waitforElement(180, UploadPage.UPLOADNEWFILE, driver);
			Thread.sleep(6000);
			String OriginalFile = "//table/tbody/tr/td[contains(.,'" + Filename1
					+ "')]/../td/a[contains(.,'Original File')]";
			if (!VerifyObject(driver, OriginalFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitforElement(180, UploadPage.UPLOADNEWFILE, driver);
					count = count + 1;
					if (count == 10) {
						assertTrue(false);
						break;
					}
					if (VerifyObject(driver, OriginalFile)) {
						report = true;
					}
				} while (report == false);
			}
			WebElement OrgFile = driver.findElement(By.xpath(OriginalFile));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", OrgFile);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_ALT);
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_S);
			}
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				File directory1 = new File(".//");
				String path = directory1.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "AddressCleansing";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				LatestDownloadedFile("AddressCleansing", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("AddressCleansing", false);
			}
			Thread.sleep(5000);
			String SuccessFile1 = "//table/tbody/tr/td[contains(.,'" + Filename1
					+ "')]/../td/a[contains(.,'Success File') and @class='ng-hide']";
			String ErrorFile1 = "//table/tbody/tr/td[contains(.,'" + Filename1
					+ "')]/../td/a[contains(.,'Error File') and @class='ng-hide']";
			String SuccessFile = "//table/tbody/tr/td[contains(.,'" + Filename1
					+ "')]/../td/a[contains(.,'Success File')]";
			String ErrorFile = "//table/tbody/tr/td[contains(.,'" + Filename1 + "')]/../td/a[contains(.,'Error File')]";
			String processed = "//table/tbody/tr/td[contains(.,'" + Filename1
					+ "')]/../td[contains(text(),'Processed')]";
			if (!VerifyObject(driver, processed)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitforElement(180, UploadPage.UPLOADNEWFILE, driver);
					Thread.sleep(15000);
					count = count + 1;
					if (count == 30) {
						assertTrue(false);
						break;
					}
					if (VerifyObject(driver, processed)) {
						report = true;
					}
				} while (report == false);
			}
			if (verifySuccessfile.equalsIgnoreCase("YES")) {
				WebElement SucsFile = driver.findElement(By.xpath(SuccessFile));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", SucsFile);
				Thread.sleep(10000);
				assertTrue(driver.findElement(By
						.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'" + Filename1 + "')]/../td[6]"))
						.getText().trim().equalsIgnoreCase("Processed"));
				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("IE")) {
					Thread.sleep(10000);
					robot.keyPress(KeyEvent.VK_ALT);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_S);
				}
				if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "AddressCleansing";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					tempfile = new File(LatestDownloadedFile("AddressCleansing", true));
					tempfile.toString();
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					Thread.sleep(10000);
					tempfile = new File(LatestDownloadedFile("AddressCleansing", false));
					tempfile.toString();
				}
				String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "AddressCleansing";
				String[] name = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
				tempfile2 = new File(DirName + "\\" + name[0] + "_suc.csv");
				Thread.sleep(5000);
				ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
				ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				f1.removeAll(f2);
				// assertTrue(f1.size() == 0);
				if (verifyErrorfile == "") {
					assertTrue(VerifyObject(driver, ErrorFile1));
				}
			}
			if (verifyErrorfile.equalsIgnoreCase("Yes")) {
				WebElement ErrFile = driver.findElement(By.xpath(ErrorFile));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", ErrFile);
				Thread.sleep(10000);
				assertTrue(driver.findElement(By
						.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'" + Filename1 + "')]/../td[6]"))
						.getText().trim().equalsIgnoreCase("Processed"));

				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("IE")) {
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_ALT);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_S);
				}
				if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
					File directory1 = new File(".//");
					String path = directory1.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "AddressCleansing";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					tempfile = new File(LatestDownloadedFile("AddressCleansing", true));
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					tempfile = new File(LatestDownloadedFile("AddressCleansing", false));
				}

				String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "AddressCleansing";
				String[] name = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
				tempfile2 = new File(DirName + "\\" + name[0] + "_err.csv");
				Thread.sleep(10000);
				ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
				ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				f1.removeAll(f2);
				assertTrue(f1.size() == 0);
				if (verifySuccessfile == "") {
					assertTrue(VerifyObject(driver, SuccessFile1));
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	public void AddressCleansingMFE(String fileName, String fileExt, String invdFileFormtVld, String fileTemplateVLD,
			String verifySuccessfile, String verifyErrorfile) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Robot robot = new Robot();
		File directory = new File(".//");
		UploadAdjustmentFile UploadPage = PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ProfessionalUserLinks ProfUserLinks = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String DownloadFilepath = xDownloadFiles("AddressCleansing");
		String Filename;
		String AdjustmentFilePath = xUploadAdjustmentFile("AddressCleansing");
		String AutoITpath = xAutoIT();
		File tempfile = null;
		File tempfile2 = null;
		String Filename1 = null;
		int count = 0;
		JavaScriptclick(driver, UploadPage.ADDRESSCLEANSINGLINK);
		waitforElement(180, UploadPage.ADDRESSCLEANSINGFILESHEADING, driver);
		assertTrue(UploadPage.ADDRESSCLEANSINGFILESHEADING.isDisplayed());
		if (!VerifyObject(driver, UploadPage.ADDRESSCLEANSINGICON)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (fileExt.equalsIgnoreCase(".xls") || fileExt.equalsIgnoreCase(".xlsx") || fileExt.equalsIgnoreCase(".pdf")
				|| fileExt.equalsIgnoreCase(".rar") || fileExt.equalsIgnoreCase(".csv")) {
			Path RSource = Paths.get(AdjustmentFilePath + "\\" + fileName + fileExt);
			Filename1 = fileName + RND();
			Path RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + fileExt);
			Files.copy(RSource, RTarget);
			Filename = Filename1;
			Thread.sleep(6000);
			Filename = xCSVUDateUpdation(AdjustmentFilePath + "\\" + Filename + fileExt);
		}
		assertTrue(UploadPage.HELPINGTEXT.isDisplayed());
		assertTrue(UploadPage.FILEUPLOADDATECOL.isDisplayed());
		assertTrue(UploadPage.FILENAMECOL.isDisplayed());
		assertTrue(UploadPage.ORIGINALFILECOL.isDisplayed());
		assertTrue(UploadPage.SUCCESSREPORTCOL.isDisplayed());
		assertTrue(UploadPage.ERRORREPORTCOL.isDisplayed());
		assertTrue(UploadPage.STATUSCOL.isDisplayed());
		click(UploadPage.UPLOADNEWFILE);
		Thread.sleep(5000);
		assertTrue(UploadPage.ADDONOPTIONHEADING.isDisplayed());
		assertTrue(UploadPage.POPUPTEXT1.isDisplayed());
		assertTrue(UploadPage.POPUPTEXT2.isDisplayed());
		assertTrue(UploadPage.CANCELBUTTON.isDisplayed());
		click(UploadPage.CONTINUEBUTTON);
		waitforElement(180, UploadPage.UPLOADADDRESSCLEANSINGFILEHEADING, driver);
		assertTrue(UploadPage.UPLOADADDRESSCLEANSINGFILEHEADING.isDisplayed());
		if (!VerifyObject(driver, UploadPage.RETURNTOADDRESSCLEANSINGFILES)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (fileTemplateVLD.equalsIgnoreCase("Yes")) {
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(5000);
				JavaScriptclick(driver, ProfUserLinks.downlaodfileformatlnk);
				Thread.sleep(10000);
				robot.keyPress(KeyEvent.VK_ALT);
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_S);
				Runtime.getRuntime().exec(AutoITpath + "\\FileDownloadIE.exe " + DownloadFilepath);
			} else {
				click(ProfUserLinks.downlaodfileformatlnk);
			}
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "AddressCleansing";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				tempfile = new File(LatestDownloadedFile("AddressCleansing", true));

			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				tempfile = new File(LatestDownloadedFile("AddressCleansing", false));
				Thread.sleep(20000);
			}
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
					+ "AddressCleansing";
			if (FileName.contains("]")) {
				tempfile2 = new File(DirName + "\\" + FileName.replace("]", ""));
			} else {
				tempfile2 = new File(DirName + "\\" + FileName);
			}
			Thread.sleep(5000);
			ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
			ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
			f1.removeAll(f2);
			// assertTrue(f1.size() == 0);
		}
		click(UploadPage.UPLOADBUTTON);
		Thread.sleep(2000);
		assertTrue(UploadPage.UPLOADERROR.isDisplayed());
		assertTrue(UploadPage.CANCELBUTTON.isDisplayed());
		if ((Browser.equalsIgnoreCase("Firefox")) || (Browser.equalsIgnoreCase("IE"))) {
			JavaScriptclick(driver, UploadPage.BROWSE);
		} else {
			click(UploadPage.BROWSE);
		}
		Thread.sleep(10000);
		// Calling AutoIT Browser function
		fileUploadDialog(driver, Browser, AutoITpath, AdjustmentFilePath, Filename1, fileExt);
		Thread.sleep(6000);
		if (fileExt.equalsIgnoreCase(".xls") || fileExt.equalsIgnoreCase(".xlsx") || fileExt.equalsIgnoreCase(".pdf")
				|| fileExt.equalsIgnoreCase(".rar")) {
			assertTrue(UploadPage.uplaodbtndisabledinvalifile.isDisplayed());
		} else {
			click(UploadPage.UPLOADBUTTON);
			Thread.sleep(8000);
			// waitforElement(180, UAF.UPLOADNEWFILE, driver);
		}
		if (invdFileFormtVld.equalsIgnoreCase("Yes")) {
			assertTrue(UploadPage.ERRORINVALIDUPLOAD.isDisplayed());
			click(UploadPage.CANCELBUTTON);
		} else {
			assertTrue(driver
					.findElement(By.xpath(
							"//table/tbody/tr[@class='ng-scope']/td[contains(text(),'" + Filename1 + "')]/../td[6]"))
					.getText().trim().equalsIgnoreCase("Processing"));
			JavaScriptclick(driver, UploadPage.ADDRESSCLEANSINGLINK);
			waitforElement(180, UploadPage.UPLOADNEWFILE, driver);
			Thread.sleep(6000);
			String OriginalFile = "//table/tbody/tr/td[contains(.,'" + Filename1
					+ "')]/../td/a[contains(.,'Original File')]";
			if (!VerifyObject(driver, OriginalFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitforElement(180, UploadPage.UPLOADNEWFILE, driver);
					count = count + 1;
					if (count == 10) {
						assertTrue(false);
						break;
					}
					if (VerifyObject(driver, OriginalFile)) {
						report = true;
					}
				} while (report == false);
			}
			WebElement OrgFile = driver.findElement(By.xpath(OriginalFile));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", OrgFile);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(5000);
				robot.keyPress(KeyEvent.VK_ALT);
				Thread.sleep(1000);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_S);
			}
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				File directory1 = new File(".//");
				String path = directory1.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "AddressCleansing";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				LatestDownloadedFile("AddressCleansing", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("AddressCleansing", false);
			}
			Thread.sleep(5000);
			String SuccessFile1 = "//table/tbody/tr/td[contains(.,'" + Filename1
					+ "')]/../td/a[contains(.,'Success File') and @class='ng-hide']";
			String ErrorFile1 = "//table/tbody/tr/td[contains(.,'" + Filename1
					+ "')]/../td/a[contains(.,'Error File') and @class='ng-hide']";
			String SuccessFile = "//table/tbody/tr/td[contains(.,'" + Filename1
					+ "')]/../td/a[contains(.,'Success File')]";
			String ErrorFile = "//table/tbody/tr/td[contains(.,'" + Filename1 + "')]/../td/a[contains(.,'Error File')]";
			String processed = "//table/tbody/tr/td[contains(.,'" + Filename1
					+ "')]/../td[contains(text(),'Processed')]";
			if (!VerifyObject(driver, processed)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitforElement(180, UploadPage.UPLOADNEWFILE, driver);
					Thread.sleep(15000);
					count = count + 1;
					if (count == 30) {
						assertTrue(false);
						break;
					}
					if (VerifyObject(driver, processed)) {
						report = true;
					}
				} while (report == false);
			}
			if (verifySuccessfile.equalsIgnoreCase("YES")) {
				WebElement SucsFile = driver.findElement(By.xpath(SuccessFile));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", SucsFile);
				Thread.sleep(10000);
				assertTrue(driver.findElement(By
						.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'" + Filename1 + "')]/../td[6]"))
						.getText().trim().equalsIgnoreCase("Processed"));
				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("IE")) {
					Thread.sleep(10000);
					robot.keyPress(KeyEvent.VK_ALT);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_S);
				}
				if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "AddressCleansing";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					tempfile = new File(LatestDownloadedFile("AddressCleansing", true));
					tempfile.toString();
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					Thread.sleep(10000);
					tempfile = new File(LatestDownloadedFile("AddressCleansing", false));
					tempfile.toString();
				}
				String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "AddressCleansing";
				String[] name = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
				tempfile2 = new File(DirName1 + "\\" + name[0] + "_suc.csv");
				Thread.sleep(5000);
				ArrayList<String> f11 = new ArrayList<String>(FileUtils.readLines(tempfile));
				ArrayList<String> f21 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				f11.removeAll(f21);
				// assertTrue(f1.size() == 0);
				if (verifyErrorfile == "") {
					assertTrue(VerifyObject(driver, ErrorFile1));
				}
			}
			if (verifyErrorfile.equalsIgnoreCase("Yes")) {
				WebElement ErrFile = driver.findElement(By.xpath(ErrorFile));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", ErrFile);
				Thread.sleep(10000);
				assertTrue(driver.findElement(By
						.xpath("//table/tbody/tr[@class='ng-scope']/td[contains(text(),'" + Filename1 + "')]/../td[6]"))
						.getText().trim().equalsIgnoreCase("Processed"));

				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("IE")) {
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_ALT);
					Thread.sleep(1000);
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_ALT);
					robot.keyRelease(KeyEvent.VK_S);
				}
				if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
					File directory1 = new File(".//");
					String path = directory1.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "AddressCleansing";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					tempfile = new File(LatestDownloadedFile("AddressCleansing", true));
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					tempfile = new File(LatestDownloadedFile("AddressCleansing", false));
				}

				String DirName2 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
						+ "AddressCleansing";
				String[] name = FileName.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
				tempfile2 = new File(DirName2 + "\\" + name[0] + "_err.csv");
				Thread.sleep(10000);
				ArrayList<String> f12 = new ArrayList<String>(FileUtils.readLines(tempfile));
				ArrayList<String> f22 = new ArrayList<String>(FileUtils.readLines(tempfile2));
				f12.removeAll(f22);
				assertTrue(f12.size() == 0);
				if (verifySuccessfile == "") {
					assertTrue(VerifyObject(driver, SuccessFile1));
				}
			}
		}
	}

	public void VerifyAddressCleansingNotifications(String Subject, String Environment, String Username)
			throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String status = GetAddressCleansingNotification(Subject, Environment, Username);
		assertTrue(status.equalsIgnoreCase("Pass"));
	}

	public void AddressCleansingPresenceAdminNotification() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		click(PUL.updateNotificationLink);
		waitforElement(60, PUL.editNotification, driver);
		int notificationsSize = PUL.notificationsCount.size();
		for (int i = 0; i < notificationsSize; i++) {
			String addressnote = PUL.notificationsCount.get(i).getText();
			if (addressnote.equalsIgnoreCase("Address Cleanse File Processed")) {
				selectByValue(PUL.selectNotificationmsg, "Address Cleanse File Processed");
				break;
			}
		}
		assertTrue(getAttribute(PUL.subjectLine, "value").trim()
				.equalsIgnoreCase("Vertex Cloud Address Cleansing File Processed"));
		assertTrue(getAttribute(PUL.messageBody, "value").trim()
				.equalsIgnoreCase("Your address cleansing file has been processed."));
		Select select = new Select(PUL.emailNotification);
		String notificationemailStatus = select.getFirstSelectedOption().getText();
		assertTrue(notificationemailStatus.trim().equalsIgnoreCase("No"));
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

	public void AdjustmentFileAPI(String auth_uri, String root_uri, String Client_Id, String cmd, String Secret,
			String APIUserName, String Password, String Type, String Foldername, String Filestatus, String Statusmsg)
			throws Exception {
		String Invalidfile = "";
		String ConmmandType = null, StrCmd1 = null, StrCmd2 = null, JSONfilepath = null, downloadPath = null,
				StatusMsg = null, InputfilePath = null, FileID = null;
		// Run DB Query DBQuery(Username);
		// Get API Username GetAPIUsername();
		String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
		xDownloadFiles("AdjustmentFiles");
		File directory = new File(".//");
		String rootpath = directory.getCanonicalPath() + "\\resources\\adjfilesdk\\AdjFileMgr.exe";
		JSONfilepath = directory.getCanonicalPath() + "\\adjfilemgr_response.json";
		downloadPath = directory.getCanonicalPath() + "\\resources\\testdata\\actual\\AdjustmentFiles";
		InputfilePath = directory.getCanonicalPath() + "\\InputTestData\\AdjustmentFiles";
		String AuthURI = auth_uri;
		String RootURI = root_uri;
		String ClientID = Client_Id;
		String command = cmd;
		String Sec = Secret;
		String Usr = APIUserName;
		String Pwd = Password;
		// String Usr = APIkey;
		// String Pwd = APIPassword;
		String Typ = Type;
		String AdjFileName = FileName;
		String Folderpath = Foldername;
		AdjFileName = AdjustmentFilePath + "\\" + Folderpath + "\\" + AdjFileName;
		AdjFileName = xCSVUDateUpdation(AdjFileName);
		String dwnldfileid = Filestatus;
		Update_AdjAPIConfigXML(AuthURI, RootURI, ClientID);
		ConmmandType = command.toUpperCase();
		switch (ConmmandType) {
		case "UPLOAD":
			StrCmd1 = "upload -sec=" + Sec + " -usr=" + Usr + " -pwd=" + Pwd + " -typ=" + Typ + " -path=";
			StrCmd2 = "\"" + AdjFileName + "\"";
			String[] UploadCommands = { "cmd", "/c", rootpath + " " + StrCmd1 + StrCmd2 };
			System.out.println(rootpath + " " + StrCmd1 + StrCmd2);
			Process UploadProcess = Runtime.getRuntime().exec(UploadCommands);
			UploadProcess.waitFor();
			break;
		case "STATUS":
			FileID = xReadJSON(JSONfilepath, "id", "");
			if (FileID == null) {
				Invalidfile = xReadJSON(JSONfilepath, "message", "");
			} else {
				StrCmd1 = "status -sec=" + Sec + " -usr=" + Usr + " -pwd=" + Pwd + " -fid=" + FileID + "";
				String[] StatusCommand = { "cmd", "/c", rootpath + " " + StrCmd1 };
				boolean flag = false;
				do {
					Process StatusProcess = Runtime.getRuntime().exec(StatusCommand);
					StatusProcess.waitFor();
					StatusMsg = xReadJSON(JSONfilepath, "message", "");
					if (StatusMsg.equalsIgnoreCase("Complete") || StatusMsg.equalsIgnoreCase("Complete with Error")
							|| StatusMsg.equalsIgnoreCase("Error")) {
						flag = true;
					}
				} while (flag == false);
			}
			break;

		case "DOWNLOAD":
			if (Invalidfile != "") {
				break;
			} else {
				FileID = xReadJSON(JSONfilepath, "reports", dwnldfileid);
				if (FileID == null) {
					FileID = xReadJSON(JSONfilepath, "id", "");
					StrCmd1 = "status -sec=" + Sec + " -usr=" + Usr + " -pwd=" + Pwd + " -fid=" + FileID + "";
					String[] StatusCommand1 = { "cmd", "/c", rootpath + " " + StrCmd1 };
					boolean flag1 = false;
					do {
						Process StatusProcess1 = Runtime.getRuntime().exec(StatusCommand1);
						StatusProcess1.waitFor();
						StatusMsg = xReadJSON(JSONfilepath, "status", "");
						if (StatusMsg.equalsIgnoreCase("Complete") || StatusMsg.equalsIgnoreCase("Complete with Error")
								|| StatusMsg.equalsIgnoreCase("Error")) {
							flag1 = true;
						}
					} while (flag1 == false);
					FileID = xReadJSON(JSONfilepath, "reports", dwnldfileid);
				}
				StrCmd1 = "download -sec=" + Sec + " -usr=" + Usr + " -pwd=" + Pwd + " -fid=" + FileID + " -path=";
				if (dwnldfileid.equalsIgnoreCase("yes")) {
					dwnldfileid = "_suc";
					InputfilePath = InputfilePath + "\\" + Folderpath;
				} else if (dwnldfileid.equalsIgnoreCase("No")) {
					dwnldfileid = ("_err");
					// InputfilePath="\\Err_"+AdjFileName+".csv";
					// InputfilePath=InputfilePath+"\\Err_"+AdjFileName[0]+".csv";
					InputfilePath = InputfilePath + "\\" + Folderpath + "_err" + ".csv";
				} else {
					dwnldfileid = "_org";
					// InputfilePath="InputfilePath+\\Org_"+AdjFileName[0]+".csv";
					InputfilePath = InputfilePath + "\\" + Folderpath + "_org" + ".csv";
				}
				StrCmd2 = "\"" + downloadPath + "\\" + Folderpath;
				String[] DownloadCommand = { "cmd", "/c", rootpath + " " + StrCmd1 + StrCmd2 };
				Process DownloadProcess = Runtime.getRuntime().exec(DownloadCommand);
				DownloadProcess.waitFor();
				downloadPath = downloadPath + "\\" + Folderpath;
				System.out.println(downloadPath);
				System.out.println(InputfilePath);
				Thread.sleep(5000);
				csvFilesCompare(downloadPath, InputfilePath);
				break;
			}
		}

	}

	@SuppressWarnings("null")
	public void UAT_ReturnOnlyUpload(String Messagetype, String File_Name, String File_Format, String Validate)
			throws Exception {
		Robot robot = new Robot();
		UploadAdjustmentFile UAF = PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String Filename, Filename1, AdjustFile, OriginalFile;
		Path RTarget, RSource;
		;
		String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
		String AutoITpath = xAutoIT();
		String Condition, browser, FileFormat;
		waitforElement(360, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
		waitforElement(360, UAF.RETURNSONLYUPLOADNEWFILE, driver);
		Filename = File_Name;
		RSource = Paths.get(AdjustmentFilePath + "\\" + Filename + ".csv");
		Filename1 = Filename + RND();
		RTarget = Paths.get(AdjustmentFilePath + "\\" + Filename1 + ".csv");
		Files.copy(RSource, RTarget);
		Filename = Filename1;
		Filename = xCSVUDateUpdation(AdjustmentFilePath + "\\" + Filename + ".csv");
		Filename = File_Name;
		click(UAF.RETURNSONLYUPLOADNEWFILE);
		FileFormat = File_Format;
		selectByValue(UAF.ADJUSTMENTFILEFORMAT, FileFormat);
		Condition = Messagetype;
		browser = Browser;
		String path, LatestSucc = null, SuccessFile_Hide, SuccessFile, ErrorFile_Hide, ProcessedFile;
		WebElement Original, Sucess;
		int count;
		StringSelection stringSelection;
		File directory = null;
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PortalFunctions DownloadFiles = PageFactory.initElements(driver, PortalFunctions.class);
		xDownloadFiles("AdjustmentFiles");
		switch (Condition) {
		case "Buyer":
		case "Seller":
			Select filefrmt = new Select(driver.findElement(By.xpath("//select[@id='AdjustmentFileFormat']")));
			filefrmt.selectByVisibleText(File_Format);
			if ((Browser.equalsIgnoreCase("Chrome")) || (Browser.equalsIgnoreCase("IE"))) {
				click(UAF.BROWSE);
			} else {
				JavaScriptclick(driver, UAF.BROWSE);
			}
			switch (browser) {
			case "IE":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
				} else {
					Runtime.getRuntime()
							.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			case "Firefox":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			case "Chrome":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			case "Safari":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Filename1 + ".csv");
				}
				break;
			}
			Thread.sleep(10000);
			waitforElement(180, UAF.UPLOADBUTTON, driver);
			JavaScriptclick(driver, UAF.UPLOADBUTTON);
			waitforElement(120, UAF.RETURNSONLYUPLOADNEWFILE, driver);
			Thread.sleep(5000);
			AdjustFile = Filename1;
			OriginalFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Original File')]";
			Original = driver.findElement(By.xpath(OriginalFile));
			if (!VerifyObject(driver, OriginalFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, UAF.RETURNSONLYUPLOADNEWFILE, driver);
					count = count + 1;
					if (count == 18) {
						break;
					}
					if (VerifyObject(driver, OriginalFile)) {
						report = true;
					}
				} while (report == false);
			}
			// Original File Download
			if (VerifyObject(driver, OriginalFile)) {
				JavaScriptclick(driver, Original);
				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptMousehover(driver, PUL.taxesHover);
					JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_HOME);
					robot.keyRelease(KeyEvent.VK_HOME);
					Thread.sleep(5000);
					stringSelection = new StringSelection(
							directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\");
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.delay(5000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(1000);
					Thread.sleep(5000);
				}
				if (Browser.equalsIgnoreCase("Chrome")) {
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "AdjustmentFiles";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
				}
				if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
					LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
					Thread.sleep(5000);
				}
			}
			SuccessFile_Hide = "//table/tbody/tr/td[contains(.,'" + AdjustFile
					+ "')]/../td/a[contains(.,'Success File') and @class='ng-hide']";
			SuccessFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td/a[contains(.,'Success File')]";
			ErrorFile_Hide = "//table/tbody/tr/td[contains(.,'" + AdjustFile
					+ "')]/../td/a[contains(.,'Error File') and @class='ng-hide']";
			// waiting for Processed status
			ProcessedFile = "//table/tbody/tr/td[contains(.,'" + AdjustFile + "')]/../td[contains(.,'Processed')]";
			PROCESSFILE: if (!VerifyObject(driver, ProcessedFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, UAF.RETURNSONLYUPLOADNEWFILE, driver);
					Thread.sleep(5000);
					if (!VerifyObject(driver, ErrorFile_Hide) && VerifyObject(driver, SuccessFile_Hide)) {
						break PROCESSFILE;
					}
					count = count + 1;
					if (count == 90) {
						break;
					}
					if (VerifyObject(driver, ProcessedFile)) {
						report = true;
					}
				} while (report == false);
			}
			// Only Success File Download
			if (!VerifyObject(driver, SuccessFile_Hide) && VerifyObject(driver, ErrorFile_Hide)) {
				File tempfile = null;
				Sucess = driver.findElement(By.xpath(SuccessFile));
				JavaScriptclick(driver, Sucess);
				Thread.sleep(10000);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptMousehover(driver, PUL.taxesHover);
					JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyPress(KeyEvent.VK_TAB);
					robot.keyRelease(KeyEvent.VK_TAB);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_HOME);
					robot.keyRelease(KeyEvent.VK_HOME);
					Thread.sleep(5000);
					stringSelection = new StringSelection(
							directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\");
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
					Thread.sleep(5000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.delay(5000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(1000);
					Thread.sleep(5000);
				}
				if (Browser.equalsIgnoreCase("Chrome")) {
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "AdjustmentFiles";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					tempfile = new File(DownloadFiles.LatestDownloadedFile("AdjustmentFiles", true));
					LatestSucc = tempfile.toString();
				}
				// LatestDownloadedFile();
				if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
					LatestSucc = LatestDownloadedFile("AdjustmentFiles", false);
				}
				LatestSucc = xCSVUDateInputUpdation(LatestSucc);
			}
		}
	}
	// Verifying Address cleansing UI for new React MFE

	@SuppressWarnings("unused")
	public void AddressCleansingReactMFE_UIvalidation() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		AddressCleansingPage addressCleansing = PageFactory.initElements(driver, AddressCleansingPage.class);
		JavaScriptclick(driver, addressCleansing.ADDRESSCLEANSINGLINKFROMLEFTNAV);
		waitforElement(180, addressCleansing.ADDRESSCLEANSINGPAGETITLE, driver);

		String actualHelpContent = addressCleansing.ADDRESSCLEANSINGPAGEHELPCONTENTTEXT.getText();
		String expectedHeloContent = "Address Cleansing allows you to upload and validate addresses in order to ensure accurate sales and use tax calculations. This option is subject to an additional fee based upon consumption.";

		String actualTableTitle = addressCleansing.CLEANSEDFILESTABLETITLE.getText();
		String ExpectedTableTitle = "Cleansed Files";

		String[] expectedTableColumns = { "File Upload Date", "File Name", "Original File", "Success Report",
				"Error Report", "Status" };

		assertTrue(addressCleansing.ADDRESSCLEANSINGPAGETITLE.isDisplayed()); //
		// Verifying the page title.
		// Assert.assertEquals(expectedHeloContent,actualHelpContent); // Verifying the
		// help content.
		Assert.assertEquals(ExpectedTableTitle, actualTableTitle); // Verifying the table title.

		// Verifying the table column names.
		for (int i = 0; i < expectedTableColumns.length; i++) {
			int actualCoumnNumber = i + 1;
			String actualTableColumnname = driver
					.findElement(By.xpath("//table/thead/tr/th[" + actualCoumnNumber + "]")).getText();
			Assert.assertEquals(expectedTableColumns[i], actualTableColumnname);
		}

		String actualUploadFileButton = addressCleansing.UPLOADFILEBUTTONINADDRESSCLEANSINGPAGE.getText();
		String expectedUploadFileButton = "Upload File";
		Assert.assertEquals(expectedUploadFileButton, actualUploadFileButton);
	}

}
