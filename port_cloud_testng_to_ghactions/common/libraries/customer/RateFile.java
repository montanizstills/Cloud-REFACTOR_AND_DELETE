/*###################################################################################################
Name: RateFile.java 
Description: Having all Rate file related functions 
Author: Venkat Reddy Kandula
Date Created:02/13/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.libraries.customer;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.MySubscriptionPage;
import common.pages.ProfessionalUserLinks;
import common.pages.UploadAdjustmentFile;

public class RateFile extends CommonFunctions {

	private WebDriver driver;
	String FillingperiodMonth = xGetCustomizedMon(0, 0);

	public RateFile(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public static String VerifyRateFile;
	public static String RateFileStateFileName;

	// Upload RateFile using Parameters.
	public void UploadRateFile(String fileName, String succFileVLD, String errFileVLD) throws Exception {
		ProfessionalUserLinks profUserPage = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		MySubscriptionPage mySubscPage = PageFactory.initElements(driver, MySubscriptionPage.class);
		ConfigurePage configPage = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptMousehover(driver, profUserPage.RateFileHover);
		JavaScriptclick(driver, profUserPage.MYRATEFILE_LINK);
		waitUntilElementInvisibe(180, configPage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, mySubscPage.UPDNEWFILELINK, driver);

		JavaScriptclick(driver, mySubscPage.UPDNEWFILELINK);
		waitforText(180, mySubscPage.BROWSE, driver);
		JavaScriptclick(driver, mySubscPage.BROWSE);
		Thread.sleep(6000);
		new UploadAdjustmentFile();
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("RateFiles");
		int count;

		Robot rob = new Robot();
		File directory = new File(".//");
		String browser = Browser;
		String path;
		switch (browser) {
		case "IE":
			Runtime.getRuntime()
					.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + fileName + ".csv");
			break;
		case "Firefox":
			Runtime.getRuntime()
					.exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + fileName + ".csv");
			break;
		case "Chrome":
			Runtime.getRuntime()
					.exec(AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + fileName + ".csv");
			break;
		case "Safari":
			Runtime.getRuntime()
					.exec(AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + fileName + ".csv");
			break;
		}
		Thread.sleep(6000);
		JavaScriptclick(driver, mySubscPage.UPLOAD);

		String successFile = "//td[contains(.,'" + fileName
				+ "')]/../td/a[text()='Success File'  and not(@class='ng-hide')]";
		String errorFile = "//td[contains(.,'" + fileName
				+ "')]/../td/a[text()='Error File'  and not(@class='ng-hide')]";

		// Downloading the success file
		if (succFileVLD.equalsIgnoreCase("yes")) {
			if (!VerifyObject(driver, successFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh(); // For O Series sync refreshing the page to get download link
					waitUntilElementInvisibe(180, configPage.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, mySubscPage.UPDNEWFILELINK, driver);
					count = count + 1;
					if (count == 10) {
						break;
					}
					if (VerifyObject(driver, successFile)) {
						report = true;
						// driver.findElement(By.xpath(successFile)).click();
						WebElement successFileLink = driver.findElement(By.xpath(successFile));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", successFileLink);
						Thread.sleep(5000); // wait for the file download to file system object
						if (Browser.equalsIgnoreCase("IE")) {
							rob.keyPress(KeyEvent.VK_ALT);
							Thread.sleep(1000);
							rob.keyPress(KeyEvent.VK_S);
							rob.keyRelease(KeyEvent.VK_ALT);
							rob.keyRelease(KeyEvent.VK_S);

						}

						if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
							path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
									+ "RateFiles";
							MoveLatestDownloadFileFromOnedirtoAnother(path);
							Thread.sleep(10000);
							LatestDownloadedFile("RateFiles", true);
						}
						if (Browser.equalsIgnoreCase("FireFox")) {
							LatestDownloadedFile("RateFiles", false);
						}

					}
				} while (report == false);
			}
			assertTrue(VerifyObject(driver, successFile));
		}

		// Downloading the error file
		if (errFileVLD.equalsIgnoreCase("yes")) {
			if (!VerifyObject(driver, errorFile)) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh(); // For O Series sync refreshing the page to get download link
					waitUntilElementInvisibe(180, configPage.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, mySubscPage.UPDNEWFILELINK, driver);
					count = count + 1;
					if (count == 10) {
						break;
					}
					if (VerifyObject(driver, errorFile)) {
						report = true;
						WebElement ErrorFileLink = driver.findElement(By.xpath(errorFile));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", ErrorFileLink);
						//driver.findElement(By.xpath(errorFile)).click();
					}
				} while (report == false);
			}
			assertTrue(VerifyObject(driver, errorFile));
		}

	}

	public void downloadRateFile(String ratefileMonth, String reatefileState, String downloadOption) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		new UploadAdjustmentFile();
		xDownloadFiles("AdjustmentFiles");
		String path, StateName = null;
		StringSelection stringSelection;
		Robot rob = new Robot();
		File directory = new File(".//");
		waitforElement(180, PUL.RateFileHover, driver);
		JavaScriptMousehover(driver, PUL.RateFileHover);
		JavaScriptclick(driver, PUL.DWLDRATEFILE_LINK);
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(240, MSP.MONTHYEAR, driver);
		if (!ratefileMonth.equalsIgnoreCase("")) {
			selectByValue(MSP.MONTHYEAR, ratefileMonth);
		}
		Thread.sleep(500); // wait to load the states

		String[] reatefileStates = reatefileState.split(";");

		for (int i = 0; i < reatefileStates.length; i++) {
			StateName = reatefileStates[i].toUpperCase();

			if (StateName.equalsIgnoreCase("Select ALL")) {
				click(MSP.SELECTAll);
			} else {

				waitforElement(180,
						"//div[@class='jurisdiction-container']/table[@class='table table-hover']/tbody/tr/td[contains(.,'"
								+ StateName + "')]",
						driver);
				WebElement StClick = driver.findElement(By.xpath(
						"//div[@class='jurisdiction-container']/table[@class='table table-hover']/tbody/tr/td[contains(.,'"
								+ StateName + "')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", StClick);
				waitforElement(180,
						"//a[contains(.,'Remove All')]/../../div[@class='jurisdiction-container']/table[@class='table table-hover']/tbody/tr/td[contains(.,'"
								+ StateName + "')]",
						driver);
				assertTrue(VerifyObject(driver,
						"//a[contains(.,'Remove All')]/../../div[@class='jurisdiction-container']/table[@class='table table-hover']/tbody/tr/td[contains(.,'"
								+ StateName + "')]"));
			}
		}
		Thread.sleep(5000);
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, MSP.DWNLDBTN);
		} else {
			JavaScriptclick(driver, MSP.DWNLDBTN);
		}

		Thread.sleep(5000);
		if (StateName.equalsIgnoreCase("Select ALL")) {
			if (downloadOption.equalsIgnoreCase("One Consolidated File for each country")) {
				click(MSP.ONECONSOLIDATEDFILE);
			}

			if (downloadOption.equalsIgnoreCase("Individual File for each jurisdiction")) {
				click(MSP.INDIVIDUALFILE);
			}

			click(MSP.CONFIRMDOWNLOAD);
		}

		Thread.sleep(5000);// wait for the file download to file system object}

		if (Browser.equalsIgnoreCase("IE")) {
			Thread.sleep(10000);
			driver.navigate().refresh();
			Thread.sleep(10000);
			rob.keyPress(KeyEvent.VK_TAB);
			rob.keyRelease(KeyEvent.VK_TAB);
			// Thread.sleep(3000);
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
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "RateFiles";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("RateFiles", true);
		}
		if (Browser.equalsIgnoreCase("FireFox") || Browser.equalsIgnoreCase("IE")) {
			LatestDownloadedFile("RateFiles", false);
			Thread.sleep(40000);
		}

	}

}
