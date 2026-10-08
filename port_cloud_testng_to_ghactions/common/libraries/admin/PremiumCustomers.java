package common.libraries.admin;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.OnboardStatus;
import common.pages.VertexSMBCustomers;
import common.utilities.UtilityScript;

public class PremiumCustomers extends CommonFunctions {

	private WebDriver driver;

	public PremiumCustomers(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void onboardCustomer(String CustomerName, String TaxPayer, String FilingPeriod) throws Exception {

		OnboardStatus OBS = PageFactory.initElements(driver, OnboardStatus.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, OBS.PREMIUMSMBCUSTOMERS, driver);
		JavaScriptMousehover(driver, OBS.PREMIUMSMBCUSTOMERS);
		Thread.sleep(2000);
		JavaScriptclick(driver, OBS.PREMIUMCUSTMERSSUBLINK);
		// click(OBS.PREMIUMCUSTMERSSUBLINK);
		// String Url="https://devportal.vertexsmb.com/Admin/Premium";
		// Open(url+"/Admin/Premium");
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, OBS.ONBOARDSEARCHBOX, driver);
		click(OBS.ONBOARDSEARCHBOX);
		Thread.sleep(2000);
		type(OBS.ONBOARDSEARCHBOX, CustomerName);
		waitUntilElementInvisibe(60, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		selectByValue(OBS.TAXPAYERDROPDOWN, TaxPayer);
		Thread.sleep(2000);
		selectByValue(OBS.ONBOARDFILINGPERIOD, FilingPeriod);
		Thread.sleep(2000);
		click(OBS.ONBOARDLABLE);
		Thread.sleep(1000);
		waitforElement(180, OBS.SUCCESSMSG, driver);
		assertTrue(OBS.SUCCESSMSG.isDisplayed());
	}

	public void PremiumReconReport(String ClientName, String TaxPayer) throws Exception {

		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitUntilElementInvisibe(40, CNP.NPROGRESSBUSY_XPATH, driver);
		Robot rob = new Robot();
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		waitforElement(60, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(.,'" + ClientName + "')]", driver);
		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			//driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a")).click();
			WebElement client = driver.findElement(
					By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+ ClientName + "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
			
		} else if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"
				+ ClientName + "')]/../../../td[contains(text(),'Authorized')]")) {
			//driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a")).click();
			WebElement client = driver.findElement(
					By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+ ClientName + "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
		} else {
			//driver.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a")).click();
			WebElement client = driver.findElement(
					By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'"+ ClientName + "')]/../../../td[contains(text(),'Initialized')]/../td[1]/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", client);
		}
		waitforElement(180, VSC.CUSTOMERRETURNS, driver);
		click(VSC.CUSTOMERRETURNS);
		waitforElement(180, VSC.CUSTOMERRETURNSTAXPAYER, driver);
		Thread.sleep(5000);
		selectByValue(VSC.CUSTOMERRETURNSTAXPAYER, TaxPayer);
		waitUntilElementInvisibe(180, "//h1[contains(text(),'Please wait...')]", driver);
		selectByValue(VSC.paginationDD, "100 per page");
		Thread.sleep(3000);
		if (UtilityScript.xInBetweenDates("01", "09") == "True") {
			JavaScriptclick(driver, VSC.PremiumrequestExport);
		} else {
			JavaScriptclick(driver, VSC.PremiumrequestExport);
		}
			Thread.sleep(7000);
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(5000);
				rob.keyPress(KeyEvent.VK_ALT);
				Thread.sleep(1000);
				rob.keyPress(KeyEvent.VK_S);
				rob.keyRelease(KeyEvent.VK_ALT);
				rob.keyRelease(KeyEvent.VK_S);
			}
			rob.keyPress(KeyEvent.VK_ESCAPE);
			if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Returns";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				LatestDownloadedFile("Returns", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("Returns", false);
			}
	}

}
