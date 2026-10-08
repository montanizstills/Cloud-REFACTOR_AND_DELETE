package common.libraries.qbo;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.QBOManageForms;

public class QuickBooksUI extends CommonFunctions {

	private WebDriver driver;

	public QuickBooksUI(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public String QBOLogout(String value) throws Exception {
		String status = "";
		try {
			QBOManageForms QBO = PageFactory.initElements(driver, QBOManageForms.class);
			waitforElement(180, QBO.SETTINGS, driver);
			JavaScriptclick(driver, QBO.SETTINGS);
			waitforElement(180, QBO.QBOSIGNOUT, driver);
			JavaScriptclick(driver, QBO.QBOSIGNOUT);
			waitforElement(180, QBO.QBOSignIn, driver);

			if (status == "") {
				status = "Pass";
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public void CreateNewInvoicesFile() {
		try {
			File file = new File(".//");
			String mainfolder = file.getCanonicalPath();
			String invoiceIds = mainfolder + "\\resources\\qboimporter\\bin\\Release\\TransInvoiceNumbers.txt";
			File invoiceIdsfile = new File(invoiceIds);
			String rndfile = "TransInvoiceNumbers" + RND() + ".txt";
			File rnmaefile = new File(mainfolder + "\\resources\\qboimporter\\bin\\Release\\" + rndfile);
			if (invoiceIdsfile.exists()) {
				invoiceIdsfile.renameTo(rnmaefile);
				invoiceIdsfile.delete();
				invoiceIdsfile.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void QBOSandboxCompany(String QBOSandboxCompany, String Subscription) throws Exception {

		QBOManageForms QBO = PageFactory.initElements(driver, QBOManageForms.class);

		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='qbo-auth-login-handler-iframe-view']"));
		driver.switchTo().frame(iframe);
		waitforElement(180, QBO.QBOHOMEPAGE, driver);
		Thread.sleep(5000);
		String Sub = Subscription;
		System.out.println(Sub);
		switch (Sub) {
		case "Standard":
			WebElement Cclick = driver
					.findElement(By.xpath("//ul[@id='company-list1']/li/a[contains(.,'" + QBOSandboxCompany + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Cclick);
			break;
		case "Professional":
			WebElement Cclick1 = driver
					.findElement(By.xpath("//ul[@id='company-list1']/li/a[contains(.,'" + QBOSandboxCompany + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Cclick1);
			break;
		case "Premium":
			WebElement Cclick2 = driver
					.findElement(By.xpath("//ul[@id='company-list1']/li/a[contains(.,'" + QBOSandboxCompany + "')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Cclick2);
			break;
		}
		Thread.sleep(15000);
		if (VerifyObject(driver, "//button[contains(text(),'Check it out')]")) {
			driver.findElement(By.xpath("//button[contains(text(),'Check it out')]")).click();
		}
		waitforElement(180, "//div[contains(.,'" + QBOSandboxCompany + "')]", driver);
		driver.switchTo().defaultContent();
	}

	public void QBOLogout() throws Exception {

		QBOManageForms QBO = PageFactory.initElements(driver, QBOManageForms.class);
		waitforElement(180, QBO.QICON, driver);
		JavaScriptclick(driver, QBO.QICON);
		waitforElement(180, QBO.SIGNOUT, driver);
		JavaScriptclick(driver, QBO.SIGNOUT);
		waitforElement(180, QBO.QBOSignIn, driver);
	}

	public void QBOLogin(String UserName, String Password) throws Exception {

		QBOManageForms QBO = PageFactory.initElements(driver, QBOManageForms.class);
		if (VerifyObject(driver, QBO.SETTINGS)) {
			QBOLogout();
		}
		waitforElement(60, QBO.QBOUserid, driver);
		type(QBO.QBOUserid, UserName);
		type(QBO.QBOPassword, Password);
		click(QBO.QBOSignIn);
		waitforElement(180, QBO.QBOLOGO1, driver);
	}

}
