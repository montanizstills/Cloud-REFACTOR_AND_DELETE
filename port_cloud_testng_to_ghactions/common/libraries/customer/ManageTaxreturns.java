package common.libraries.customer;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import common.libraries.generic.CommonFunctions;
import common.pages.CalculateTaxesPage;
import common.pages.ConfigurePage;
import common.pages.DistributeTaxes;
import common.pages.ManageConfiguration;
import common.pages.ManagetaxReturns;
import common.pages.ProfessionalUserLinks;
import common.pages.UnpublishedConfigurationChanges;
import common.pages.VertexSMBCustomers;

public class ManageTaxreturns extends CommonFunctions {

	private WebDriver driver;

	public ManageTaxreturns(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	// Generate Returns using parameters.
	public void GenerateReturns(String companyName, String companyCode, String filingPeriod) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		ManagetaxReturns ManageTaxRetPage = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks profUserLinksPage = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage configPage = PageFactory.initElements(driver, ConfigurePage.class);
		int count = 0;
		String companyValue = companyName + " " + "(" + companyCode + ")";
		waitforElement(60, profUserLinksPage.ReturnsHover, driver);
		JavaScriptMousehover(driver, profUserLinksPage.ReturnsHover);
		JavaScriptclick(driver, ManageTaxRetPage.MANAGETAXRETURNSLINK);
		waitUntilElementInvisibe(180, configPage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, ManageTaxRetPage.TAXPAYER, driver);
		if (VerifyObject(driver, ManageTaxRetPage.MTRALERT)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, configPage.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, ManageTaxRetPage.TAXPAYER, driver);
				count = count + 1;
				if (count == 5) {
					break;
				}
				if (!VerifyObject(driver, ManageTaxRetPage.MTRALERT)) {
					report = true;
				}
			} while (report == false);
		}
		selectByValue(ManageTaxRetPage.TAXPAYER, companyValue);
		if (VerifyObject(driver, ManageTaxRetPage.MTRALERT1)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, configPage.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, ManageTaxRetPage.TAXPAYER, driver);
				count = count + 1;
				selectByValue(ManageTaxRetPage.TAXPAYER, companyValue);
				// Thread.sleep(20000); // Wait for the page load after selecting the taxpayer
				// name from manage tax
				// returns page
				if (count == 30) {
					break;
				}
				if (!VerifyObject(driver, ManageTaxRetPage.MTRALERT1)) {
					report = true;
				}
			} while (report == false);
		}
		selectByValue(ManageTaxRetPage.TAXPAYER, companyValue);
		Thread.sleep(2000);
		selectByValue(ManageTaxRetPage.FILLINGPERID, filingPeriod);
		waitUntilElementInvisibe(180, configPage.NPROGRESSBUSY_XPATH, driver);
		waitUntilElementInvisibe(360, "//span/img", driver);
		waitforElement(180, ManageTaxRetPage.GENERATRETURN, driver);
		JavaScriptclick(driver, ManageTaxRetPage.GENERATRETURN);
		waitforText(180, ManageTaxRetPage.RETURNGENERATESUCCESSMSG, driver);
		boolean returnsGeneration = false;
		WebElement manageprepayments = driver.findElement(By.xpath("//button[@id='btnGenerateReturns']"));
		Thread.sleep(20000); // Need to wait for returns generation
		do {
			String generateRetrunsButtonText = manageprepayments.getText();
			System.out.println(generateRetrunsButtonText);
			if ((generateRetrunsButtonText).equalsIgnoreCase("Generate Returns")) {
				returnsGeneration = true;
			}
			selectByValue(ManageTaxRetPage.FILLINGPERID, "February 2020");
			selectByValue(ManageTaxRetPage.FILLINGPERID, filingPeriod);
			Thread.sleep(5000); // As returns generating takes time, waiting after every refresh.

		} while (returnsGeneration == false);
	}

	public String returndownload(String TaxPayer, String Fillingperiod, int k, String browser) throws Exception {
		String status = "", path;

		try {
			ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
			Robot rob = new Robot();
			StringSelection stringSelection;
			File directory = new File(".//");
			selectByValue(MTR.TAXPAYER, TaxPayer);
			selectByValue(MTR.FILLINGPERID, Fillingperiod);
			waitforElement(180, "//span/select[@ng-model='data.pageSize']", driver);
			Thread.sleep(5000);
			if ((Browser.equalsIgnoreCase("Firefox")) || (Browser.equalsIgnoreCase("Chrome"))) {
				if (VerifyObject(driver, "//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[4]/div/div/a")) {
					driver.findElement(
							By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[4]/div/div/a"))
							.click();
				} else {
					driver.findElement(
							By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[6]/div/div/a"))
							.click();
				}
				Thread.sleep(6000);
			}
			if (browser.equalsIgnoreCase("IE")) {

				if (VerifyObject(driver, "//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[6]/div/div/a")) {
					WebElement Dwnload = driver.findElement(
							By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[6]/div/div/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Dwnload);
				} else {
					WebElement Dwnload = driver.findElement(
							By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[4]/div/div/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Dwnload);
				}
				Thread.sleep(10000);
				JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
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
				stringSelection = new StringSelection(
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
			if (browser.equalsIgnoreCase("Chrome")) {
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Returns";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000); // to get the file from dafault download path to specified path
				LatestDownloadedFile("Returns", true);
			}
			if (browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("IE")) {
				LatestDownloadedFile("Returns", false);
			}
			System.out.println(FileName);
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public String returndownload(String TaxPayer, String Fillingperiod, int k) throws Exception {
		String status = "", path;
		try {
			ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
			Robot rob = new Robot();
			StringSelection stringSelection;
			File directory = new File(".//");
			selectByValue(MTR.TAXPAYER, TaxPayer);
			selectByValue(MTR.FILLINGPERID, Fillingperiod);
			waitforElement(180, "//span/select[@ng-model='data.pageSize']", driver);
			Thread.sleep(5000);
			if ((Browser.equalsIgnoreCase("Firefox")) || (Browser.equalsIgnoreCase("Chrome"))) {

				if (String.valueOf(k) == "") {
					driver.findElement(By.xpath("//button[contains(.,'Download All Returns')]2")).click();
				} else if (VerifyObject(driver,
						"//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[4]/div/div/a")) {
					driver.findElement(
							By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[4]/div/div/a"))
							.click();
				} else {
					driver.findElement(
							By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[6]/div/div/a"))
							.click();
				}

				Thread.sleep(6000);
			}
			if (Browser.equalsIgnoreCase("IE")) {

				if (String.valueOf(k) == "") {
					driver.findElement(By.xpath("//button[contains(.,'Download All Returns')]2")).click();
				} else if (VerifyObject(driver,
						"//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[6]/div/div/a")) {
					WebElement Dwnload = driver.findElement(
							By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[6]/div/div/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Dwnload);
				} else {
					WebElement Dwnload = driver.findElement(
							By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[4]/div/div/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Dwnload);
				}
				Thread.sleep(10000);
				JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
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
				stringSelection = new StringSelection(
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
				path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "CustomerPortal";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000); // to get the file from dafault download path to specified path
				LatestDownloadedFile("CustomerPortal", true);
			}
			if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("IE")) {
				LatestDownloadedFile("CustomerPortal", false);
			}
			System.out.println(FileName);
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public void Returnsgenerate(String TaxPayer, String Fillingperiod) throws Exception {
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		int count = 0;
		waitforElement(60, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, MTR.TAXPAYER, driver);
		if (VerifyObject(driver, MTR.MTRALERT)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, MTR.TAXPAYER, driver);
				count = count + 1;
				if (count == 5) {
					break;
				}
				if (!VerifyObject(driver, MTR.MTRALERT)) {
					report = true;
				}
			} while (report == false);
		}
		selectByValue(MTR.TAXPAYER, TaxPayer);
		if (VerifyObject(driver, MTR.MTRALERT1)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, MTR.TAXPAYER, driver);
				count = count + 1;
				selectByValue(MTR.TAXPAYER, TaxPayer);
				Thread.sleep(20000); // Wait for the page load after selecting the taxpayer name from manage tax
				// returns page
				if (count == 30) {
					break;
				}
				if (!VerifyObject(driver, MTR.MTRALERT1)) {
					report = true;
				}
			} while (report == false);
		}
		selectByValue(MTR.TAXPAYER, TaxPayer);
		Thread.sleep(4000);
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitUntilElementInvisibe(360, "//span/img", driver);
		waitforElement(180, MTR.GENERATRETURN, driver);
		JavaScriptclick(driver, MTR.GENERATRETURN);
		waitforText(180, MTR.RETURNGENERATESUCCESSMSG, driver);
		Thread.sleep(2000);
		assertTrue(MTR.RETURNGENERATESUCCESSMSG.isDisplayed());

	}

	public void returnexits(String taxPayer, String fillingperiod, String returnName) throws Exception {
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		int count = 0;
		waitforElement(60, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
		waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, MTR.TAXPAYER, driver);
		if (VerifyObject(driver, MTR.MTRALERT)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, MTR.TAXPAYER, driver);
				count = count + 1;
				if (count == 5) {
					break;
				}
				if (!VerifyObject(driver, MTR.MTRALERT)) {
					report = true;
				}
			} while (report == false);
		}
		selectByValue(MTR.TAXPAYER, taxPayer);
		if (VerifyObject(driver, MTR.MTRALERT1)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, MTR.TAXPAYER, driver);
				count = count + 1;
				selectByValue(MTR.TAXPAYER, taxPayer);
				Thread.sleep(20000); // Wait for the page load after selecting the taxpayer name from manage tax
				// returns page
				if (count == 30) {
					break;
				}
				if (!VerifyObject(driver, MTR.MTRALERT1)) {
					report = true;
				}
			} while (report == false);
		}
		selectByValue(MTR.TAXPAYER, taxPayer);
		selectByValue(MTR.FILLINGPERID, fillingperiod);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitUntilElementInvisibe(360, "//span/img", driver);
		waitforElement(180, MTR.GENERATRETURN, driver);
		assertTrue(driver
				.findElement(
						By.xpath("//table[@id='tblManageTaxReturns']/tbody/tr[1]/td[contains(.,'" + returnName + "')]"))
				.isDisplayed());

	}

	public void returndoesnotexits(String taxPayer, String fillingperiod, String returnName) throws Exception {
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		int count = 0;
		waitforElement(60, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
		waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, MTR.TAXPAYER, driver);
		if (VerifyObject(driver, MTR.MTRALERT)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, MTR.TAXPAYER, driver);
				count = count + 1;
				if (count == 5) {
					break;
				}
				if (!VerifyObject(driver, MTR.MTRALERT)) {
					report = true;
				}
			} while (report == false);
		}
		selectByValue(MTR.TAXPAYER, taxPayer);
		if (VerifyObject(driver, MTR.MTRALERT1)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, MTR.TAXPAYER, driver);
				count = count + 1;
				selectByValue(MTR.TAXPAYER, taxPayer);
				Thread.sleep(20000); // Wait for the page load after selecting the taxpayer name from manage tax
				// returns page
				if (count == 30) {
					break;
				}
				if (!VerifyObject(driver, MTR.MTRALERT1)) {
					report = true;
				}
			} while (report == false);
		}
		selectByValue(MTR.TAXPAYER, taxPayer);
		selectByValue(MTR.FILLINGPERID, fillingperiod);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitUntilElementInvisibe(360, "//span/img", driver);
		waitforElement(180, MTR.GENERATRETURN, driver);
		assertTrue(driver
				.findElement(
						By.xpath("//table[@id='tblManageTaxReturns']/tbody/tr[1]/td[contains(.,'" + returnName + "')]"))
				.isDisplayed() == false);

	}

	public void downloadReturns(String taxPayer, String fillingPeriod, String returnName, String premiumType)
			throws Exception {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		String path;
		File directory = new File(".//");
		Robot rob = new Robot();
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(60, MTR.MANAGETAXRETURNSLINK, driver);
		if (!VerifyObject(driver, MTR.DOWNLOADALLRTRNS)) {

			JavaScriptMousehover(driver, PUL.taxesHover);
			JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, MTR.TAXPAYER, driver);
			waitforElement(180, "// select[@id='tax-payer']/optgroup/option[contains(.,'" + taxPayer + "')]", driver);
			waitforElement(180, "//span/select[@ng-model='data.pageSize']", driver);
			new Select(driver.findElement(By.xpath("//span/select[@ng-model='data.pageSize']")))
					.selectByVisibleText("100 per page");
			Thread.sleep(20000);
		}
		selectByValue(MTR.TAXPAYER, taxPayer);
		Thread.sleep(5000);
		selectByValue(MTR.FILLINGPERID, fillingPeriod);
		Thread.sleep(5000);
		if (premiumType.equalsIgnoreCase("OnBoarded")) {
			if (returnName != "") {
				for (int k = 1; k <= MTR.ReturnsCount.size(); k++) {
					if (driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[2]"))
							.getText().equalsIgnoreCase(returnName)) {

						driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k
								+ "]/tr[1]/td//a[contains(.,'Download')]")).click();

						Thread.sleep(6000);
					}
				}
			}
		} else if (premiumType.equalsIgnoreCase("NotOnBoarded")) {

			if (returnName != "") {
				for (int k = 1; k <= MTR.ReturnsCount.size(); k++) {
					if (driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[1]"))
							.getText().equalsIgnoreCase(returnName)) {

						driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k
								+ "]/tr[1]/td//a[contains(.,'Download')]")).click();

						Thread.sleep(6000);
					}
				}
			}

		} else {
			assertTrue(MTR.DOWNLOADALLRTRNS.isDisplayed());
			click(MTR.DOWNLOADALLRTRNS);
			Thread.sleep(10000);

		}

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
	}

	public void Creditreportgenerated(String TaxPayer, String Fillingperiod) throws Exception {

		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		int count = 0;
		waitforElement(60, MTR.MANAGETAXRETURNSLINK, driver);
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, MTR.TAXPAYER, driver);
		waitforElement(180, "// select[@id='tax-payer']/optgroup/option[contains(.,'" + TaxPayer + "')]", driver);
		waitforElement(180, "//span/select[@ng-model='data.pageSize']", driver);
		selectByValue(MTR.TAXPAYER, TaxPayer);
		Thread.sleep(5000);
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		Thread.sleep(7000);
		if (!VerifyObject(driver, MTR.CREDITERRORREPORT)) {
			boolean report = false;
			do {
				count = count + 1;
				if (count == 60) {
					selectByValue(MTR.TAXPAYER, TaxPayer);
					Thread.sleep(5000);
					selectByValue(MTR.FILLINGPERID, Fillingperiod);
					waitUntilElementInvisibe(180, CNP.LOADER_XPATH, driver);
					Thread.sleep(8000);
					break;
				}
				driver.navigate().refresh();
				Thread.sleep(5000);
				selectByValue(MTR.TAXPAYER, TaxPayer);
				Thread.sleep(5000);
				selectByValue(MTR.FILLINGPERID, Fillingperiod);
				waitforElement(100, CNP.LOADER, driver);
				Thread.sleep(8000);
				if (VerifyObject(driver, MTR.CREDITERRORREPORT)) {
					Thread.sleep(5000);
					report = true;
				}
			} while (report == false);
		}

		assertTrue(MTR.CREDITERRORREPORT.isDisplayed());
	}

	public void verifyreturnsGenerated(String TaxPayer, String Fillingperiod) throws Exception {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		int count = 0;
		waitforElement(60, MTR.MANAGETAXRETURNSLINK, driver);
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, MTR.TAXPAYER, driver);
		waitforElement(180, "// select[@id='tax-payer']/optgroup/option[contains(.,'" + TaxPayer + "')]", driver);
		waitforElement(180, "//span/select[@ng-model='data.pageSize']", driver);
		selectByValue(MTR.TAXPAYER, TaxPayer);
		Thread.sleep(5000);
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		Thread.sleep(7000);
		if (VerifyObject(driver, MTR.GenerateReturnsDisabled)) {
			boolean report = false;
			do {
				count = count + 1;
				if (count == 60) {
					selectByValue(MTR.TAXPAYER, TaxPayer);
					Thread.sleep(5000);
					selectByValue(MTR.FILLINGPERID, Fillingperiod);
					waitUntilElementInvisibe(180, CNP.LOADER_XPATH, driver);
					Thread.sleep(8000);
					break;
				}
				driver.navigate().refresh();
				Thread.sleep(5000);
				selectByValue(MTR.TAXPAYER, TaxPayer);
				Thread.sleep(5000);
				selectByValue(MTR.FILLINGPERID, Fillingperiod);
				waitforElement(100, CNP.LOADER, driver);
				Thread.sleep(8000);
				if (!VerifyObject(driver, MTR.GenerateReturnsDisabled)) {
					Thread.sleep(5000);
					report = true;
				}
			} while (report == false);
		}

		assertTrue(MTR.DOWNLOADALLRTRNS.isDisplayed());
	}

	public void uncheckreturnformschkbox(String paginationsize, String Returnforms) throws Exception {

		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		selectByValue(VSC.paginationSelect, paginationsize);
		Thread.sleep(15000);
		String[] rforms = Returnforms.split(";");
		for (int i = 0; i <= rforms.length; i++) {
			if (VerifyObject(driver, "//td[contains(.,'" + rforms[i] + "')]/../td[5]/input")) {
				driver.findElement(By.xpath("//td[contains(.,'" + rforms[i] + "')]/../td[5]/input")).click();
				Thread.sleep(2000);
			}
		}
	}

	public void CheckGenerateReturnschkboxgrid(String ClientName, String CompanyName, String FilingPeriod)
			throws Exception {

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
		waitforElement(180, VSC.CUSTOMERRETURNS, driver);
		click(VSC.CUSTOMERRETURNS);
		waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
		Thread.sleep(5000);
		selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, CompanyName);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		selectByValue(VSC.FILINGPERIOD, FilingPeriod);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		Thread.sleep(2000);
		click(VSC.GenerateReturnsgridchkbox);
		Thread.sleep(2000);
	}

	@SuppressWarnings("unchecked")
	public void ReconciliationReport(String TaxPayer, String Fillingperiod, String InputFile) throws Exception {

		Robot rob = new Robot();
		String file2 = null;
		File tempfile = null;
		File tempfile2 = null;
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String path;
		File directory = new File(".//");
		waitforElement(60, MTR.MANAGETAXRETURNSLINK, driver);
		if (VerifyObject(driver, MTR.RECONCILATIONERRORREPORT)) {
		} else {
			JavaScriptMousehover(driver, PUL.taxesHover);
			JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, MTR.TAXPAYER, driver);
			waitforElement(180, "//select[@id='tax-payer']/optgroup/option[contains(.,'" + TaxPayer + "')]", driver);
			waitforElement(180, "//span/select[@ng-model='data.pageSize']", driver);
		}
		selectByValue(MTR.TAXPAYER, TaxPayer);
		Thread.sleep(5000);
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		Thread.sleep(5000);
		waitforElement(90, MTR.RECONCILATIONERRORREPORT, driver);
		assertTrue(MTR.RECONCILATIONERRORREPORT.isDisplayed());
		JavaScriptclick(driver, MTR.RECONCILATIONERRORREPORT);
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
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "CustomerPortal";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			file2 = LatestDownloadedFile("CustomerPortal", true);
			tempfile = new File(file2);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			file2 = LatestDownloadedFile("CustomerPortal", false);
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
	public void CreditReport(String TaxPayer, String Fillingperiod, String InputFile) throws Exception {

		Robot rob = new Robot();
		String file2 = null;
		File tempfile = null;
		File tempfile2 = null;
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String path;
		File directory = new File(".//");
		waitforElement(60, MTR.MANAGETAXRETURNSLINK, driver);
		if (VerifyObject(driver, MTR.CREDITERRORREPORT)) {
		} else {
			JavaScriptMousehover(driver, PUL.taxesHover);
			JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, MTR.TAXPAYER, driver);
			waitforElement(180, "//select[@id='tax-payer']/optgroup/option[contains(.,'" + TaxPayer + "')]", driver);
			waitforElement(180, "//span/select[@ng-model='data.pageSize']", driver);
		}
		selectByValue(MTR.TAXPAYER, TaxPayer);
		Thread.sleep(5000);
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		Thread.sleep(5000);
		waitforElement(180, MTR.CREDITERRORREPORT, driver);
		assertTrue(MTR.CREDITERRORREPORT.isDisplayed());
		JavaScriptclick(driver, MTR.CREDITERRORREPORT);
		Thread.sleep(35000);
		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(5000);
			rob.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			rob.keyPress(KeyEvent.VK_S);
			rob.keyRelease(KeyEvent.VK_ALT);
			rob.keyRelease(KeyEvent.VK_S);
		}
		if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "CustomerPortal";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			file2 = LatestDownloadedFile("CustomerPortal", true);
			tempfile = new File(file2);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			file2 = LatestDownloadedFile("CustomerPortal", false);
			tempfile = new File(file2);
			System.out.println(tempfile);
		}
		if (InputFile != "") {
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
			String FileName = DirName + "\\" + InputFile;
			// FileName = xCSVUDateUpdate(FileName);
			tempfile2 = new File(FileName);
			ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
			ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
			System.out.println(f1);
			System.out.println(f2);
			f1.removeAll(f2);
			System.out.println(f1.size());
			assertTrue(f1.size() == 0);
		}

	}

	public void manageoutofstateSales(String TaxPayer, String Fillingperiod, String outofform, String Amount)
			throws Exception {
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		int count = 0;
		if (!VerifyObject(driver, MTR.GENERATRETURN)) {
			waitforElement(60, PUL.ReturnsHover, driver);
			JavaScriptMousehover(driver, PUL.ReturnsHover);
			JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
			waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(60, MTR.TAXPAYER, driver);
			if (VerifyObject(driver, MTR.MTRALERT)) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, MTR.TAXPAYER, driver);
					count = count + 1;
					if (count == 5) {
						break;
					}
					if (!VerifyObject(driver, MTR.MTRALERT)) {
						report = true;
					}
				} while (report == false);
			}
			selectByValue(MTR.TAXPAYER, TaxPayer);
			if (VerifyObject(driver, MTR.MTRALERT1)) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, MTR.TAXPAYER, driver);
					count = count + 1;
					selectByValue(MTR.TAXPAYER, TaxPayer);
					Thread.sleep(20000); // Wait for the page load after selecting the taxpayer name from manage tax
					// returns page
					if (count == 30) {
						break;
					}
					if (!VerifyObject(driver, MTR.MTRALERT1)) {
						report = true;
					}
				} while (report == false);
			}
		}
		selectByValue(MTR.TAXPAYER, TaxPayer);
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitUntilElementInvisibe(360, "//span/img", driver);
		waitforElement(180, MTR.GENERATRETURN, driver);

		if (VerifyObject(driver,
				"//table[@id='tblManageTaxReturns']/tbody[contains(.,'" + outofform + "')]/tr[1]/td[1]")) {

			driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[contains(.,'" + outofform
					+ "')]/tr[1]/td[1]/../td/span/a[contains(.,'Manage Out-of-State Sales')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[contains(.,'" + outofform
					+ "')]/tr[2]/td/table/tbody/tr[2]/td[2]/input")).click();
			driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[contains(.,'" + outofform
					+ "')]/tr[2]/td/table/tbody/tr[2]/td[2]/input")).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[contains(.,'" + outofform
					+ "')]/tr[2]/td/table/tbody/tr[2]/td[2]/input")).sendKeys(Amount);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[contains(.,'" + outofform
					+ "')]/tr[2]/td/table/tbody/tr[2]/td[3]/button")).click();
			Thread.sleep(2000);
		}

		waitforText(180, MTR.appliedoutofsalesmsg, driver);

	}

	public void ManageReturnSpecificRule(String Jurisdiction, String ReturnName) throws Exception {

		ManageConfiguration MC = PageFactory.initElements(driver, ManageConfiguration.class);
		waitforElement(60, MC.MANAGECONFIGLINK, driver);
		JavaScriptclick(driver, MC.MANAGECONFIGLINK);
		waitforElement(180, MC.PageLoad, driver);
		Thread.sleep(10000);
		click(MC.RETUNRSPCIFICRUILES);
		waitforElement(180, "//div[@id='s2id_autogen1']", driver);
		Thread.sleep(20000);
		driver.findElement(
				By.xpath("//div[@id='s2id_autogen3']//span[@class='select2-chosen'][contains(text(),'-- Any --')]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-search']/input"))
				.sendKeys(Jurisdiction);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'" + Jurisdiction + "')]")).click();
		Thread.sleep(5000);

		driver.findElement(
				By.xpath("//div[@id='s2id_autogen1']//span[@class='select2-chosen'][contains(text(),'-- Any --')]"))
				.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-search']/input"))
				.sendKeys(ReturnName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(.,'" + ReturnName + "')]")).click();

		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//td[contains(.,'" + ReturnName
				+ "')]/../td[contains(.,'Other Deductions:')]/../td/input[@type='checkbox']"));
		if (element.isSelected()) {
			System.out.println("Checkbox alreday selected");
		} else {
			element.click();
			driver.findElement(By.xpath("//td[contains(.,'" + ReturnName
					+ "')]/../td[contains(.,'Other Deductions:')]/../td/button[contains(.,'Update')]")).click();
			Thread.sleep(5000);
		}
		WebElement element1 = driver
				.findElement(By.xpath("//tr[7]/td[contains(.,'" + ReturnName + "')]/../td/input[@type='checkbox']"));
		if (element1.isSelected()) {
			System.out.println("Checkbox alreday selected");
		} else {
			element1.click();
			driver.findElement(
					By.xpath("//tr[7]/td[contains(.,'" + ReturnName + "')]/../td/button[contains(.,'Update')]"))
					.click();
			Thread.sleep(5000);
		}
	}

	public void ManageOutofSales(String TaxPayer, String Out_Of_Sales, String Out_Of_Sales_returnform,
			String Jurisdictions, String Out_Of_Sales_Value, String Out_Of_SalesLink_NotDisplayed) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		int count = 0;
		JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
		if (VerifyObject(driver, CNP.OKERROR)) {
			click(CNP.OKERROR);
		}
		waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, MTR.LTAXPAYER, driver);
		Thread.sleep(5000);
		if (VerifyObject(driver,
				"//div[@class='ng-scope']/div/div[contains(.,'Ensure its configuration is up to date.')]")) {
			boolean report = false;
			count = 0;
			do {
				driver.navigate().refresh();
				Thread.sleep(5000);
				count = count + 1;
				if (count == 50) {
					break;
				}
				if (VerifyObject(driver, "//pagination-extension/span/select")) {
					report = true;
				}
			} while (report == false);
		}
		selectByValue(MTR.LTAXPAYER, TaxPayer);
		Thread.sleep(10000);
		waitforElement(180, MTR.GENERATERETURNS, driver);
		WebElement returns = driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']"));
		List<WebElement> rowscount = returns.findElements(By.xpath("tbody"));
		rowscount.size();
		if ((Out_Of_Sales).equalsIgnoreCase("Yes")) {
			new Select(driver.findElement(By.xpath("//pagination-extension/span/select")))
					.selectByVisibleText("100 per page");
			Thread.sleep(10000);
			if (rowscount.size() != 0) {
				if (driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[2]/tr[1]/td[1]")).getText()
						.equalsIgnoreCase(Jurisdictions)) {
					if (Jurisdictions.equalsIgnoreCase("CN GST/HST Return (Non personalized)")) {
						assertTrue(VerifyObject(driver,
								"//table[@id='tblManageTaxReturns']/tbody/tr/td[contains(.,'CN GST/HST Return (Non personalized)')]/../td[3]/span/a[contains(.,'View Input Tax Credit')]"));
						driver.findElement(By.xpath(
								"//table[@id='tblManageTaxReturns']/tbody/tr/td[contains(.,'CN GST/HST Return (Non personalized)')]/../td[3]/span/a[contains(.,'View Input Tax Credit')]"))
								.click();
						Thread.sleep(5000);
						driver.findElement(By.xpath(
								"//table[@class='no-border-tax-reports']/..//td/div[contains(.,'CN GST/HST Return (Non personalized)')]/../../td[2]/input"))
								.clear();
						driver.findElement(By.xpath(
								"//table[@class='no-border-tax-reports']/..//td/div[contains(.,'CN GST/HST Return (Non personalized)')]/../../td[2]/input"))
								.sendKeys(Out_Of_Sales_Value);

						driver.findElement(By.xpath(
								"//table[@class='no-border-tax-reports']/..//td/div[contains(.,'CN GST/HST Return (Non personalized)')]/../../td/button[contains(.,'Save')]"))
								.click();
						waitforElement(180, MTR.GENERATERETURNS, driver);
						JavaScriptclick(driver, MTR.GENERATERETURNS);
					} else if (Out_Of_SalesLink_NotDisplayed.equalsIgnoreCase("Yes")) {
						assertTrue(VerifyObject(driver,
								"//table[@id='tblManageTaxReturns']/tbody[2]/tr[1]/td[3]/span/a[text()='Manage Out-of-State Sales']"));
					} else {
						driver.findElement(By.xpath(
								"//table[@id='tblManageTaxReturns']/tbody[2]/tr[1]/td[3]/span/a[text()='Manage Out-of-State Sales']"))
								.click();
						driver.findElement(By.xpath(
								"//table[@id='tblManageTaxReturns']/tbody[2]/tr[2]/td/table/tbody/tr[2]/td[2]/input"))
								.clear();
						driver.findElement(By.xpath(
								"//table[@id='tblManageTaxReturns']/tbody[2]/tr[2]/td/table/tbody/tr[2]/td[2]/input"))
								.sendKeys(Out_Of_Sales_Value);
						if (VerifyObject(driver,
								"//table[@id='tblManageTaxReturns']/tbody[2]/tr[2]/td/table/tbody/tr[2]/td[3]/button[@disabled='disabled']")) {
							assertTrue(VerifyObject(driver,
									"//table[@id='tblManageTaxReturns']/tbody[2]/tr[2]/td/table/tbody/tr[2]/td[3]/button[@disabled='disabled']"));
						} else {
							assertTrue(VerifyObject(driver,
									"//table[@id='tblManageTaxReturns']/tbody[2]/tr[2]/td/table/tbody/tr[2]/td[3]/button"));
							driver.findElement(By.xpath(
									"//table[@id='tblManageTaxReturns']/tbody[2]/tr[2]/td/table/tbody/tr[2]/td[3]/button"))
									.click();
						}
						Thread.sleep(5000);
						driver.findElement(By.xpath(
								"//table[@id='tblManageTaxReturns']/tbody[2]/tr[1]/td[3]/span/a[text()='Close Out-of-State Sales']"))
								.click();
					}
				}

			} else {
				if (VerifyObject(driver,
						"//table[@id='tblManageTaxReturns']/tbody/tr[1]/td[3]/span/a[text()='Manage Out-of-State Sales']")) {
					assertTrue(true);
				} else {
					assertTrue(false);
				}
			}

		}
	}

	public void DownloadOccasional(String TaxPayer, String Fillingperiod, String Return, String OccasionalFilling)
			throws Exception {
		Robot rob = new Robot();
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		xDownloadFiles("Returns");
		int count = 0;
		JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, MTR.FILLINGPERID, driver);
		Thread.sleep(3000);
		selectByValue(MTR.LTAXPAYER, TaxPayer);
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		Thread.sleep(5000);
		WebElement returns = driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']"));
		List<WebElement> rowscount = returns.findElements(By.xpath("tbody"));
		rowscount.size();
		for (int k = 1; k <= rowscount.size(); k++) {
			if (driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[1]"))
					.getText().equalsIgnoreCase(Return)) {
				if (!((VerifyObject(driver, CNP.DOWNLOADALLRTRNS)) && (VerifyObject(driver, MTR.GENERATRETURN)))) {
					boolean report = false;
					do {
						JavaScriptclick(driver, MTR.MANAGETAXRETURNSLINK);
						Thread.sleep(5000);
						waitforElement(180, MTR.FILLINGPERID, driver);
						Thread.sleep(30000);
						count = count + 1;
						if (count == 60) {
							selectByValue(MTR.LTAXPAYER, TaxPayer);
							selectByValue(MTR.FILLINGPERID, Fillingperiod);
							waitforElement(180, CNP.LOADER, driver);
							Thread.sleep(8000);
							break;
							// FIRST;
						}
						selectByValue(MTR.LTAXPAYER, TaxPayer);
						selectByValue(MTR.FILLINGPERID, Fillingperiod);
						waitforElement(180, CNP.LOADER, driver);
						Thread.sleep(8000);
						if ((VerifyObject(driver, CNP.DOWNLOADALLRTRNS)) && (VerifyObject(driver, MTR.GENERATRETURN))) {
							driver.navigate().refresh();
							Thread.sleep(5000);
							report = true;
						}
					} while (report == false);
				}
				selectByValue(MTR.LTAXPAYER, TaxPayer);
				selectByValue(MTR.FILLINGPERID, Fillingperiod);

				if (VerifyObject(driver, CNP.DOWNLOADALLRTRNS)) {
					JavaScriptclick(driver, CNP.DOWNLOADALLRTRNS);
					Thread.sleep(3000);
					if (!OccasionalFilling.equalsIgnoreCase("Yes")) {

						if ((Browser.equalsIgnoreCase("Firefox")) || (Browser.equalsIgnoreCase("Chrome"))) {
							driver.findElement(By
									.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[4]/div/div/a"))
									.click();
						}
						Thread.sleep(3000);
						rob.keyPress(KeyEvent.VK_ESCAPE);
						if (Browser.equalsIgnoreCase("Chrome")) {
							File directory = new File(".//");
							String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
									+ "Returns";
							MoveLatestDownloadFileFromOnedirtoAnother(path);
							Thread.sleep(10000);
							LatestDownloadedFile("Returns", true);
						}
						if (Browser.equalsIgnoreCase("Firefox")) {
							LatestDownloadedFile("Returns", false);
						}
					} else {
						String dlink = Return;
						assertTrue(driver
								.findElement(By.xpath("//tr/td[contains(.,'" + dlink
										+ "')]/../td/div/div[contains(.,'Occasional filing threshold not met')]"))
								.isDisplayed());
					}

				}
			}
		}
	}

	public void UnpublishedConfiguration(String TaxPayer, String TaxPayerName) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		Robot rob = new Robot();
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		UnpublishedConfigurationChanges UPC = PageFactory.initElements(driver, UnpublishedConfigurationChanges.class);
		CalculateTaxesPage CT = PageFactory.initElements(driver, CalculateTaxesPage.class);
		DistributeTaxes DT = PageFactory.initElements(driver, DistributeTaxes.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, UPC.MANAGEADJUSTMENTFILELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UPC.UPLOADLINK, driver);
		JavaScriptclick(driver, UPC.UPLOADLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UPC.DOWNLOADFILEFORMAT, driver);
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, UPC.CALTAXES);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UPC.PRODUCTPRICE, driver);
		rob.mouseMove(0, 0);
		selectByValue(DT.TAXPAYER, TaxPayer);
		waitforElement(180, CT.UNPUBLISHEDPUSH_CONFIGURATION, driver);
		assertTrue(CT.UNPUBLISHEDPUSH_CONFIGURATION.isDisplayed());
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, UPC.DISTRIBUTETAX);
		waitforElement(180, DT.GETJURISDICTIONS, driver);
		selectByValue(DT.TAXPAYER, TaxPayer);
		waitforElement(180, CT.UNPUBLISHEDPUSH_CONFIGURATION, driver);
		assertTrue(CT.UNPUBLISHEDPUSH_CONFIGURATION.isDisplayed());
		JavaScriptclick(driver, CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UPC.SAVECONFIGURATION, driver);
		WebElement saveconfig = driver.findElement(By
				.xpath("//h2[contains(.,'" + TaxPayerName + "')]/../../div/a[contains(text(),'Save Configuration')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveconfig);
		waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
		if (VerifyObject(driver, CNP.COMPLETECONFIGPOPUP)) {
			click(CNP.COMPLETECONFIGPOPUP);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		// waitforElement(180,CNP.ADDANOTHERCOMPANYLINK,driver);
		// waitforElement(180,CNP.BEGINREMOVAL,driver);
		Thread.sleep(5000);
		waitforText(180,
				"//h2[contains(.,'" + TaxPayerName + "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']",
				driver);
		if (VerifyObject(driver, "//h2[contains(.,'" + TaxPayerName
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(10000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + TaxPayerName
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, UPC.MANAGEADJUSTMENTFILELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CT.UNPUBLISHEDPUSH_CONFIGURATION, driver);
		assertTrue(CT.UNPUBLISHEDPUSH_CONFIGURATION.isDisplayed());
		JavaScriptclick(driver, UPC.CALTAX);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UPC.PRODUCTPRICE, driver);
		selectByValue(DT.TAXPAYER, TaxPayer);
		waitforElement(180, CT.UNPUBLISHEDPUSH_CONFIGURATION, driver);
		assertTrue(CT.UNPUBLISHEDPUSH_CONFIGURATION.isDisplayed());
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, UPC.DISTRIBUTETAX);
		waitforElement(180, DT.GETJURISDICTIONS, driver);
		if (Browser.equalsIgnoreCase("Firefox")) {
			selectByValue(DT.TAXPAYER, TaxPayer);
		} else if (TaxPayer != "") {
			type(DT.TAXPAYER, TaxPayer);
		}
		waitforElement(180, CT.UNPUBLISHEDPUSH_CONFIGURATION, driver);
		assertTrue(CT.UNPUBLISHEDPUSH_CONFIGURATION.isDisplayed());
	}

}
