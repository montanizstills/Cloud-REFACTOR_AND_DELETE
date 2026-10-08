package common.libraries.customer;

import java.awt.Robot;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.pages.ConfigurePage;
import common.pages.ForgotPassword;
import common.pages.ProfessionalUserLinks;
import common.pages.SOCReport;
import common.pages.VarAdminScreens;

public class PortalFunctions extends CommonFunctions {

	private WebDriver driver;

	public PortalFunctions(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);

	public void Myusage(String value) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		click(PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue((PUL.CURRMONTH).isDisplayed() && (PUL.PREVMONTH).isDisplayed() && (PUL.PREVMONTH1).isDisplayed());
		System.out.println("My usage values displayed with Zero's");
	}

	public String Commit(String tpName) throws Exception {
		String status = "";
		try {
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			click(CNP.CONFIGURE);
			waitforElement(180, "//h2/span[text()='" + tpName + "']/../../a[@class='btn btn-primary']", driver);
			driver.findElement(By.xpath("//h2/span[text()='" + tpName + "']/../../a[@class='btn btn-primary']"))
					.click();
			waitforElement(180, CNP.COMMIT, driver);
			click(CNP.COMMIT);
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {
			return e.getMessage() + status;
		}
		return "Pass";
	}

	public boolean isTextNotPresent(String text) throws Exception {
		try {
			boolean b = driver.getPageSource().contains(text);
			return b;
		} catch (Exception e) {
			return true;
		}
	}

	public String SOCFromClientSettings(String value) throws Exception {
		String status = "";
		try {
			if (Browser.equalsIgnoreCase("Firefox")) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			} else {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			}
			new Robot();
			SOCReport SC = PageFactory.initElements(driver, SOCReport.class);
			VarAdminScreens VSS = PageFactory.initElements(driver, VarAdminScreens.class);
			PageFactory.initElements(driver, ConfigurePage.class);
			ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
			scrollDown(driver);
			waitforElement(120, VSS.SETTINGSLINK, driver);
			JavaScriptMousehover(driver, PUL.SettingsHover);
			waitforElement(180, SC.SOCReportRequest, driver);
			click(SC.SOCReportRequest);
			waitforText(180, SC.SOCheadertext, driver);
			Thread.sleep(2000);
			click(SC.SOCreportdownload);
			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public void ForgotPassword(String userName, String emailAddress, String newPassword, String confirmPassword)
			throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		ForgotPassword FP = PageFactory.initElements(driver, ForgotPassword.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, FP.PASSWORDLINK, driver);
		JavaScriptclick(driver, FP.PASSWORDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		type(FP.USERNAME, userName);
		type(FP.EMAILADDRESS, emailAddress);
		JavaScriptclick(driver, FP.EMAILPASSWORDRESETBTN);
		waitforElement(180, FP.SUCCESS, driver);
		assertTrue(VerifyObject(driver, FP.SUCCESS));
		click(FP.RESETPASSWORDLINK);
		type(FP.USERNAME, userName);
		type(FP.NEWPASSWORD, newPassword);
		type(FP.CONFIRMNEWPASSWORD, confirmPassword);
		click(FP.RESETPASSWORDBUTTON);
		click(FP.LOGINBUTTON);
	}

}