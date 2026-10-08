/*###################################################################################################
Name: UserRegistration.java 
Description: Having Login, Logout and User Creation related functions 
Author: Venkat Reddy Kandula
Date Created:02/06/2020
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.libraries.usercreation;

import java.awt.Robot;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import common.libraries.generic.CommonFunctions;
import common.pages.*;

public class UserRegistration extends CommonFunctions {

	private WebDriver driver;

	public UserRegistration(WebDriver driver) throws Exception {
		this.driver = driver;
		pageDriver(driver);
	}

	private static Logger logger = Logger.getLogger("UserRegistration");

	public void Logout() throws Exception {

		if (VerifyObject(driver, loginPage.VTMSACCOUNT)) {
			waitforElement(180, loginPage.VTMSACCOUNT, driver);
			boolean report = false;
			do {
				JavaScriptclick(driver, loginPage.VTMSACCOUNT);
				Thread.sleep(6000);
				if (loginPage.VTMSLOGOUT.isDisplayed() == true) {
					JavaScriptclick(driver, loginPage.VTMSLOGOUT);
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
					click(loginPage.FORBIDDEN_LOGOUT);
					waitforElement(60, loginPage.LOGIN, driver);
				}
			}
			// assertTrue(VerifyObject(driver, LP.LOGIN));
		} else if (VerifyObject(driver, loginPage.ECLogout)) {
			click(loginPage.ECLogout);
			waitforText(180, loginPage.ECPortalText, driver);
		} else {
			waitforElement(180, salesforcePage.SALESFORCEUSERICON, driver);
			click(salesforcePage.SALESFORCEUSERICON);
			Thread.sleep(2000);
			click(salesforcePage.SALESFORCELOGOUT);
			waitforElement(180, salesforcePage.LOGIN, driver);
		}
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

	// R8.14 method for create Admin
	public void AdminCreation(String role, String firstName, String lastName, String email, String Phone,
			String userName, String passWord) throws Exception {
		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		type(registrationPage.FIRSTNAME, firstName);
		type(registrationPage.LASTNAME, lastName);
		type(registrationPage.EMAIL, email);
		click(registrationPage.PHONE);
		type(registrationPage.PHONE, Phone);
		sendkeysTab(registrationPage.PHONE);
		JavaScriptclick(driver, registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
		waitforElement(180, registrationPage.UserName, driver);
		click(registrationPage.UserName);
		type(registrationPage.UserName, userName);
		click(registrationPage.PASWORD);
		type(registrationPage.PASWORD, passWord);
		click(registrationPage.PASSWORDCONF);
		type(registrationPage.PASSWORDCONF, passWord);
		Thread.sleep(2000);
		click(registrationPage.BUTTON);
		waitforElement(180, registrationPage.Loginbutton, driver);
		click(registrationPage.Loginbutton);
		waitforElement(180, registrationPage.DASHBOARDLINK, driver);
	}

	// R8.15 method for Admin creation
	public void AdminCreation(String role, String email) throws Exception {
		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		type(registrationPage.EMAIL, email);
		JavaScriptclick(driver, registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
		waitforElement(180, registrationPage.UserName, driver);
		click(registrationPage.PASSWORDCONF);
		Thread.sleep(2000);
		click(registrationPage.BUTTON);
		waitforElement(180, registrationPage.Loginbutton, driver);
		click(registrationPage.Loginbutton);
		waitforElement(180, registrationPage.DASHBOARDLINK, driver);
	}

	public void UserLogin_Old(String user, String pwd) throws Exception {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(30, loginPage.USERNAME, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
		}
		if (VerifyObject(driver, loginPage.DASHBOARD) || VerifyObject(driver, qbo.manageSalesforms)) {
			Logout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user);
		} else {
			type(loginPage.USERNAME, user);
		}
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		Thread.sleep(3000);
		// loginPage.LOGIN.click();
		JavaScriptclick(driver, loginPage.LOGIN);
		Thread.sleep(2000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				loginPage.USERNAME.sendKeys(user);
				Thread.sleep(2000);
				loginPage.PASSWORD.sendKeys(pwd);
				Thread.sleep(2000);
				loginPage.LOGIN.click();
				Thread.sleep(2000);
			}
		}
		if (VerifyObject(driver, loginPage.DASHBOARD)) {
			waitforElement(180, loginPage.DASHBOARD, driver);
		}
		LoginName = user;
		Reporter.log("Customer Portal User Name :" + LoginName);

		if (VerifyObject(driver, loginPage.ACCOUNTICON)) {
			waitforElement(60, loginPage.ACCOUNTICON, driver);
			// loginPage.SELECTAVAILABLECLIENTS.sendKeys(user);
		}
		Thread.sleep(2000);
	}

	public void Auth0UserLogin(String user, String pwd) throws Exception {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(30, loginPage.USERNAME, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user);
		} else {
			type(loginPage.USERNAME, user);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		Thread.sleep(3000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(10000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				loginPage.USERNAME.sendKeys(user);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(2000);
				loginPage.PASSWORD.sendKeys(pwd);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(2000);
			}
		}
		if (VerifyObject(driver, loginPage.DASHBOARD)) {
			waitforElement(180, loginPage.DASHBOARD, driver);
		}
		LoginName = user;
		Reporter.log("Customer Portal User Name :" + LoginName);

		if (VerifyObject(driver, loginPage.ACCOUNTICON)) {
			waitforElement(60, loginPage.ACCOUNTICON, driver);
		}
		Thread.sleep(2000);
	}

	public void Auth0TIUserLogin(String user, String pwd) throws Exception {
		waitforElement(30, loginPage.USERNAME, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
		}
		if (VerifyObject(driver, loginPage.DASHBOARD) || VerifyObject(driver, qbo.manageSalesforms)) {
			Logout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user);
		} else {
			type(loginPage.USERNAME, user);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);

		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		Thread.sleep(3000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		// Thread.sleep(2000);
		// loginPage.LOGIN.click();
		Thread.sleep(10000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				loginPage.USERNAME.sendKeys(user);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(2000);
				loginPage.PASSWORD.sendKeys(pwd);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(2000);
			}
		}
		if (VerifyObject(driver, loginPage.DASHBOARD)) {
			waitforElement(180, loginPage.DASHBOARD, driver);
		}
		LoginName = user;
		Reporter.log("Customer Portal User Name :" + LoginName);
		waitforElement(60, loginPage.ACCOUNTICON, driver);
		selectByValue(loginPage.SELECTAVAILABLECLIENTS, user + ".TI");
		// loginPage.SELECTAVAILABLECLIENTS.sendKeys(user + ".TI");
		Thread.sleep(2000);
		if (VerifyObject(driver, loginPage.TESTINSTANEENVTEXT)) {
			waitforElement(180, loginPage.TESTINSTANEENVTEXT, driver);
		}

	}

	public void Auth0FranchiseUserLogin(String user, String pwd) throws Exception {
		waitforElement(30, loginPage.USERNAME, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
		}
		if (VerifyObject(driver, loginPage.DASHBOARD) || VerifyObject(driver, qbo.manageSalesforms)) {
			Logout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user);
		} else {
			type(loginPage.USERNAME, user);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		Thread.sleep(3000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		// loginPage.LOGIN.click();
		Thread.sleep(10000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(franchiseurl);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				loginPage.USERNAME.sendKeys(user);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(2000);
				loginPage.PASSWORD.sendKeys(pwd);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(2000);
			}
		}
		if (VerifyObject(driver, loginPage.DASHBOARD)) {
			waitforElement(180, loginPage.DASHBOARD, driver);
		}
		LoginName = user;
		Reporter.log("Customer Portal User Name :" + LoginName);
		waitforElement(60, loginPage.ACCOUNTICON, driver);
		selectByValue(loginPage.SELECTAVAILABLECLIENTS, user);
		// loginPage.SELECTAVAILABLECLIENTS.sendKeys(user);
		Thread.sleep(2000);
		// System.out.println(xRayAccessToken());

	}

	public void UserLogin(String user, String pwd) throws Exception {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(30, loginPage.USERNAME, driver);
		assertTrue(loginPage.USERNAME.isDisplayed());
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user);
		} else {
			type(loginPage.USERNAME, user);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		Thread.sleep(3000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(10000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				loginPage.USERNAME.sendKeys(user);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(2000);
				loginPage.PASSWORD.sendKeys(pwd);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(5000);
			}
		}
		if (VerifyObject(driver, loginPage.DASHBOARD)) {
			waitforElement(180, loginPage.DASHBOARD, driver);
		}
		LoginName = user;
		Reporter.log("Customer Portal User Name :" + LoginName);

		if (VerifyObject(driver, loginPage.ACCOUNTICON)) {
			waitforElement(60, loginPage.ACCOUNTICON, driver);
		}
		Thread.sleep(2000);
	}

	public void userloginSwitch(String clientname, String user, String pwd) throws Exception {
		waitforElement(30, loginPage.USERNAME, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
		}
		if (VerifyObject(driver, loginPage.DASHBOARD) || VerifyObject(driver, qbo.manageSalesforms)) {
			Logout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user);
		} else {
			type(loginPage.USERNAME, user);
		}
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(10000);
		// loginPage.LOGIN.click();
		// Thread.sleep(2000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				JavaScripttype(driver, loginPage.USERNAME, user);
				JavaScriptclick(driver, loginPage.CONTINUE);
				Thread.sleep(2000);
				JavaScripttype(driver, loginPage.PASSWORD, pwd);
				JavaScriptclick(driver, loginPage.CONTINUE);
				Thread.sleep(2000);
			}
		}
		if (VerifyObject(driver, loginPage.DASHBOARD)) {
			waitforElement(180, loginPage.DASHBOARD, driver);
		}
		LoginName = user;
		Reporter.log("Customer Portal User Name :" + LoginName);

		if (VerifyObject(driver, loginPage.ACCOUNTICON)) {
			waitforElement(60, loginPage.ACCOUNTICON, driver);
			selectByValue(loginPage.SELECTAVAILABLECLIENTS, clientname);
			Thread.sleep(7000);
		}

	}

	public void nonauth0userloginSwitch(String clientname, String user, String pwd) throws Exception {
		waitforElement(30, loginPage.USERNAME, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
		}
		if (VerifyObject(driver, loginPage.DASHBOARD) || VerifyObject(driver, qbo.manageSalesforms)) {
			Logout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user);
		} else {
			type(loginPage.USERNAME, user);
		}
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		Thread.sleep(3000);
		// loginPage.LOGIN.click();
		JavaScriptclick(driver, loginPage.LOGIN);
		Thread.sleep(2000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				loginPage.USERNAME.sendKeys(user);
				Thread.sleep(2000);
				loginPage.PASSWORD.sendKeys(pwd);
				Thread.sleep(2000);
				loginPage.LOGIN.click();
				Thread.sleep(2000);
			}
		}
		if (VerifyObject(driver, loginPage.DASHBOARD)) {
			waitforElement(180, loginPage.DASHBOARD, driver);
		}
		LoginName = user;
		Reporter.log("Customer Portal User Name :" + LoginName);

		if (VerifyObject(driver, loginPage.ACCOUNTICON)) {
			waitforElement(60, loginPage.ACCOUNTICON, driver);
			selectByValue(loginPage.SELECTAVAILABLECLIENTS, clientname);
			Thread.sleep(7000);
		}

	}

	public void FranchiseUserLogin(String user, String pwd) throws Exception {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(30, loginPage.USERNAME, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(franchiseurl);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user);
		} else {
			type(loginPage.USERNAME, user);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		Thread.sleep(3000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(10000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(franchiseurl);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				loginPage.USERNAME.sendKeys(user);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(2000);
				loginPage.PASSWORD.sendKeys(pwd);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(5000);
			}
		}
		if (VerifyObject(driver, loginPage.DASHBOARD)) {
			waitforElement(180, loginPage.DASHBOARD, driver);
		}
		LoginName = user;
		Reporter.log("Customer Portal User Name :" + LoginName);
		waitforElement(60, loginPage.ACCOUNTICON, driver);
		selectByValue(loginPage.SELECTAVAILABLECLIENTS, user);
		// loginPage.SELECTAVAILABLECLIENTS.sendKeys(user);
		Thread.sleep(2000);
		// System.out.println(xRayAccessToken());

	}

	public void TIUserLogin(String user, String pwd) throws Exception {
		waitforElement(30, loginPage.USERNAME, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
		}
		if (VerifyObject(driver, loginPage.DASHBOARD) || VerifyObject(driver, qbo.manageSalesforms)) {
			Logout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user + "@simulator.amazonses.com");
		} else {
			type(loginPage.USERNAME, user + "@simulator.amazonses.com");
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		Thread.sleep(3000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(10000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				loginPage.USERNAME.sendKeys(user + "@simulator.amazonses.com");
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(2000);
				loginPage.PASSWORD.sendKeys(pwd);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(5000);
			}
		}
		if (VerifyObject(driver, loginPage.DASHBOARD)) {
			waitforElement(180, loginPage.DASHBOARD, driver);
		}
		LoginName = user;
		Reporter.log("Customer Portal User Name :" + LoginName);
		waitforElement(60, loginPage.ACCOUNTICON, driver);
		selectByValue(loginPage.SELECTAVAILABLECLIENTS, user + ".TI");
		// loginPage.SELECTAVAILABLECLIENTS.sendKeys(user + ".TI");
		Thread.sleep(2000);
		if (VerifyObject(driver, loginPage.TESTINSTANEENVTEXT)) {
			waitforElement(180, loginPage.TESTINSTANEENVTEXT, driver);
		}

	}

	public void ECWUserLogin(String user, String pwd) throws Exception {
		// loginPage.USERNAME.sendKeys(user);
		JavaScripttype(driver, loginPage.USERNAME, user);
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		// loginPage.PASSWORD.sendKeys(pwd);
		Thread.sleep(2000);
		JavaScripttype(driver, loginPage.PASSWORD, pwd);
		// loginPage.ECLogin.click();
		JavaScriptclick(driver, loginPage.CONTINUE);
		LoginName = user;
		Reporter.log("ECW Portal User Name :" + LoginName);
	}

	public void SalesforceLogin(String user, String pwd) throws Exception {

		waitforElement(180, loginPage.SALESFORCEUSERNAME, driver);
		Thread.sleep(5000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			type(loginPage.SALESFORCEUSERNAME, user);
		} else {
			type(loginPage.SALESFORCEUSERNAME, user);
		}
		Thread.sleep(3000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.SALESFORCEPASSWORD, pwd);
		} else {
			type(loginPage.SALESFORCEPASSWORD, pwd);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.SALESFORCELOGIN);
		waitforElement(180, loginPage.SALESFORCECONTACTS, driver);
	}

	public void CPAAdminLogin(String user, String pwd) throws Exception {
		// loginPage.USERNAME.sendKeys(user);
		JavaScripttype(driver, loginPage.USERNAME, user);
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		// loginPage.PASSWORD.sendKeys(pwd);
		Thread.sleep(2000);
		JavaScripttype(driver, loginPage.PASSWORD, pwd);
		// loginPage.ECLogin.click();
		JavaScriptclick(driver, loginPage.CONTINUE);
		LoginName = user;
		Reporter.log("CPA Portal User Name :" + LoginName);
	}

	public void IntegrationConnectorAdminCreation(String role, String firstName, String lastName, String email,
			String Phone, String userName, String passWord, String passwordConfirm) throws Exception {

		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		Thread.sleep(5000);
		type(registrationPage.FIRSTNAME, firstName);
		type(registrationPage.LASTNAME, lastName);
		type(registrationPage.EMAIL, email);
		click(registrationPage.PHONE);
		type(registrationPage.PHONE, Phone);
		sendkeysTab(registrationPage.PHONE);
		JavaScriptclick(driver, registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
		waitforElement(180, registrationPage.USERNAME, driver);
		type(registrationPage.UserName, userName);
		click(registrationPage.PASWORD);
		type(registrationPage.PASWORD, passWord);
		click(registrationPage.PASSWORDCONF);
		type(registrationPage.PASSWORDCONF, passwordConfirm);
		Thread.sleep(5000);
		click(registrationPage.SUBMIT);
		waitUntilElementInvisibe(120, "html[@class='nprogressbusy']", driver);
		assertTrue(registrationPage.Success.isDisplayed());
		Thread.sleep(3000);
		click(registrationPage.Loginbutton);
	}

	public void IntegrationConnectorReadAccess(String Clientname) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		int count;
		Thread.sleep(1000);
		assertTrue(integrationadminPage.Dashboard.isDisplayed());
		JavaScriptclick(driver, integrationadminPage.Dashboard);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		click(adminundoadjustmentfilePage.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, vertexsmbcustomersPage.SMBCUSTNAMESB, driver);
		if (!VerifyObject(driver,
				"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + Clientname + "')]")) {
			boolean report = false;
			count = 0;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000);
				type(vertexsmbcustomersPage.SMBCUSTNAMESB, Clientname);
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
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + Clientname + "')]"))
				.click();
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		JavaScriptclick(driver, adminlinksPage.PARTITIONCONFIGURATIONTAB);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		assertTrue(adminlinksPage.COMPANYDROPDOWNINPARTITIONCONFIGURATION.isDisplayed());
		JavaScriptclick(driver, adminlinksPage.CERTIFICATESINPARTITIONCONFIGURATION);
		Thread.sleep(5000);
		assertTrue(adminlinksPage.REJECTIONLETTERSUBJECT.isDisplayed());
		Thread.sleep(3000);
	}

	public void SystemAdminReadWriteAccess(String Clientname, String aggvalue) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		int count;
		Thread.sleep(1000);
		// Dashboard
		assertTrue(integrationadminPage.Dashboard.isDisplayed());
		JavaScriptclick(driver, integrationadminPage.Dashboard);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		click(adminundoadjustmentfilePage.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, vertexsmbcustomersPage.SMBCUSTNAMESB, driver);
		if (!VerifyObject(driver,
				"//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + Clientname + "')]")) {
			boolean report = false;
			count = 0;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000);
				type(vertexsmbcustomersPage.SMBCUSTNAMESB, Clientname);
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
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + Clientname + "')]"))
				.click();
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		JavaScriptclick(driver, adminlinksPage.PARTITIONCONFIGURATIONTAB);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		JavaScriptclick(driver, adminlinksPage.TAXGISINPARTITIONCONFIGURATION);
		Thread.sleep(5000);
		JavaScriptclick(driver, adminlinksPage.MINAGGREGATECONFIDENCE);
		Thread.sleep(3000);
		type(adminlinksPage.MINAGGREGATECONFIDENCE, aggvalue);
		Thread.sleep(3000);
		JavaScriptclick(driver, adminlinksPage.SAVE);
		Thread.sleep(9000);
		assertTrue(adminlinksPage.Success.isDisplayed());
		Thread.sleep(3000);
	}

	public void InviteMerchantUser(String MerchantClientName, String MerchantFirstName, String MerchantLastName,
			String Password, String email, String country, String Phone, String Address, String City, String State,
			String Zip) throws Exception {
		String parentwindow = driver.getWindowHandle();
		click(professionalLinks.ManageMerchants);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		click(professionalLinks.ENROLLBUTTON);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		type(cpaPage.BASICCOMPANYNAME, MerchantClientName);
		Thread.sleep(5000);
		selectByValue(cpaPage.BASICCOUNTRY, country);
		Thread.sleep(5000);
		type(cpaPage.BASISTREETADD, Address);
		Thread.sleep(5000);
		type(cpaPage.BASICCITY, City);
		Thread.sleep(5000);
		selectByValue(cpaPage.BASICSTATE, State);
		Thread.sleep(5000);
		click(cpaPage.BASICZIP);
		Thread.sleep(5000);
		JavaScripttype(driver, cpaPage.BASICZIP, Zip);
		sendkeysTab(cpaPage.BASICZIP);
		Thread.sleep(5000);
		JavaScripttype(driver, cpaPage.MerchantFIRSTNAME, MerchantFirstName);
		Thread.sleep(2000);
		sendkeybackSpace(cpaPage.MerchantFIRSTNAME);
		JavaScripttype(driver, cpaPage.MerchantLastNAME, MerchantLastName);
		Thread.sleep(2000);
		sendkeybackSpace(cpaPage.MerchantLastNAME);
		sendkeysTab(cpaPage.MerchantLastNAME);
		Thread.sleep(2000);
		click(cpaPage.MerchantMail);
		Thread.sleep(5000);
		type(cpaPage.MerchantMail, email);
		sendkeysTab(cpaPage.MerchantMail);
		Thread.sleep(5000);
		click(cpaPage.MerchantPHONE);
		Thread.sleep(5000);
		JavaScripttype(driver, cpaPage.MerchantPHONE, Phone);
		sendkeysTab(cpaPage.MerchantPHONE);
		Thread.sleep(5000);
		JavaScriptclick(driver, cpaPage.SUBMIT);
		Thread.sleep(9000);
		waitforElement(180, cpamanagersPage.COMPLETEREGISTRATION, driver);
		click(cpamanagersPage.COMPLETEREGISTRATION);
		Thread.sleep(5000);
		assertTrue(cpaPage.CompleteYourRegistrationTitle.isDisplayed());
		Thread.sleep(5000);
		click(cpaPage.MERCHANTUSERNAME);
		clear(cpaPage.MERCHANTUSERNAME);
		JavaScripttype(driver, cpaPage.MERCHANTUSERNAME, MerchantClientName);
		Thread.sleep(2000);
		sendkeybackSpace(cpaPage.MERCHANTUSERNAME);
		sendkeysTab(cpaPage.MERCHANTUSERNAME);
		Thread.sleep(2000);
		type(cpaPage.MerchantPASSWORD, Password);
		sendkeysTab(cpaPage.MerchantPASSWORD);
		Thread.sleep(2000);
		type(cpaPage.MerchantCONFIRMPASSWORD, Password);
		sendkeysTab(cpaPage.MerchantCONFIRMPASSWORD);
		Thread.sleep(2000);
		waitforElement(180, cpaPage.FINISHANDSAVE, driver);
		JavaScriptclick(driver, cpaPage.FINISHANDSAVE);
		Thread.sleep(6000);
		assertTrue(professionalLinks.DASHBOARDLINK.isDisplayed());
	}

	public void InviteCPAManagedUser(String BPOClientName, String BPOFirstName, String BPOLastName, String Password,
			String email, String CreditCard, String ExpMonth, String ExpYear, String CVN, String country,
			String Subscrip, String AdditionalFeature1, String AdditionalFeature2, String Phone, String Address,
			String City, String State, String Zip, String file_name, String orderfile) throws Exception {
		File directory = new File(".//");
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String AutoITpath = xAutoIT();
		String parentwindow = driver.getWindowHandle();
		click(cpaPage.MANAGECLIENTSMENU);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, cpaPage.ENROLLNEWCLIENT, driver);
		click(cpaPage.ENROLLNEWCLIENT);
		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		Thread.sleep(3000);
		type(cpaPage.BASICCOMPANYNAME, BPOClientName);
		selectByValue(cpaPage.BASICCOUNTRY, country);
		type(cpaPage.BASISTREETADD, Address);
		Thread.sleep(3000);
		type(cpaPage.BASICCITY, City);
		Thread.sleep(3000);
		selectByValue(cpaPage.BASICSTATE, State);
		Thread.sleep(3000);
		click(cpaPage.BASICZIP);
		JavaScripttype(driver, cpaPage.BASICZIP, Zip);
		Thread.sleep(2000);
		sendkeysTab(cpaPage.BASICZIP);
		click(cpaPage.BASICPHONE);
		JavaScripttype(driver, cpaPage.BASICPHONE, Phone);
		Thread.sleep(2000);
		sendkeysTab(cpaPage.BASICPHONE);
		Thread.sleep(2000);
		if (Subscription.equalsIgnoreCase("PROFESSIONAL")) {
			click(cpaPage.PROFESSIONALSUBSCRIPTION);
		}
		if (Subscription.equalsIgnoreCase("PREMIUM")) {
			click(cpaPage.PREMIUMSUBSCRIPTION);
		}
		if (Subscription.equalsIgnoreCase("Standard")) {
			click(cpaPage.STANDARD);
		}
		Thread.sleep(2000);
		/*
		 * if(Subscription_Options.equalsIgnoreCase("RETURNS ONLY"))
		 * {click(CP.RETONLYRADIO);} if
		 * (Subscription_Options.equalsIgnoreCase("Tax Calculations"))
		 * {click(CP.CALCONLY);}
		 */
		if (AdditionalFeature1.equalsIgnoreCase("TaxabilityImpactReport")) {
			click(cpaPage.TAXIMPACTREPORT);
		}
		if (AdditionalFeature2.equalsIgnoreCase("RateFile")) {
			click(cpaPage.RATEFILECHECK);
		}
		Thread.sleep(2000);
		if (VerifyObject(driver, cpaPage.CLIENTORDERFORMERROR)) {
			assertTrue(cpaPage.CLIENTORDERFORMERROR.isDisplayed());
			JavaScriptclick(driver, cpaPage.CLIENTFORMUPLOAD);
			Thread.sleep(2000);
			transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, orderfile, "");
			Thread.sleep(2000);
		}
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex")) {
			driver.get(Environment + "/registration/billing");
			Thread.sleep(5000);
		}
		Thread.sleep(3000);
		assertTrue(cpaPage.AGREEMENTLINK.isDisplayed());
		click(cpaPage.AGREEMENTLINK);
		Thread.sleep(5000);
		if (Browser.equalsIgnoreCase("Chrome")) {
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
		String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Reports";
		PDFComparison(Filepath, DirName + "\\" + file_name.replace("]", ""));
		Thread.sleep(5000);
		click(cpaPage.IAGREELINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(13000);
		type(cpaPage.BILLADDRESS1, Address);
		Thread.sleep(5000);
		type(cpaPage.BILLCITY, City);
		Thread.sleep(2000);
		selectByValue(cpaPage.BILLSTATE, State);
		click(cpaPage.BILLZIP);
		Thread.sleep(2000);
		JavaScripttype(driver, cpaPage.BILLZIP, Zip);
		Thread.sleep(2000);
		sendkeysTab(cpaPage.BILLZIP);
		Thread.sleep(3000);
		type(cpaPage.CREDITCARD, CreditCard);
		sendkeysTab(cpaPage.CREDITCARD);
		Thread.sleep(3000);
		selectByValue(cpaPage.EXPMONTH, ExpMonth);
		Thread.sleep(3000);
		selectByValue(cpaPage.EXPYEAR, ExpYear);
		type(cpaPage.CVN, CVN);
		Thread.sleep(3000);
		JavaScripttype(driver, cpaPage.BILLFIRSTNAME, BPOFirstName);
		Thread.sleep(2000);
		sendkeybackSpace(cpaPage.BILLFIRSTNAME);
		JavaScripttype(driver, cpaPage.BILLLASTNAME, BPOLastName);
		Thread.sleep(2000);
		sendkeysTab(cpaPage.BILLLASTNAME);
		Thread.sleep(2000);
		click(cpaPage.SUBMIT);
		Thread.sleep(6000);
	}

	public void CPA_VerifyManageClients(String clientname) throws Exception {

		String parentwindow1;
		click(cpaPage.MANAGECLIENTSMENU);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
		}
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpaPage.SEARCHBYCLIENT, driver);
		Thread.sleep(5000);
		type(cpaPage.SEARCHBYCLIENT, clientname);
		Thread.sleep(5000);
		waitforElement(90, "//tr/td/span[contains(.,'" + clientname + "')]/../../td[6][contains(.,'Edit User Access')]",
				driver);
		driver.findElement(By.xpath("//tr/td/span/a[contains(.,'" + clientname + "')]")).click();
		/*
		 * WebElement element =
		 * driver.findElement(By.xpath("//tr/td/span/a[contains(.,'" + clientname +
		 * "')]")); JavascriptExecutor executor = (JavascriptExecutor) driver;
		 * executor.executeScript("arguments[0].click();", element);
		 */
		Thread.sleep(3000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
			driver.findElement(By.xpath("//tr/td/span/a[contains(.,'" + clientname + "')]")).click();
			// executor.executeScript("arguments[0].click();", element);
		}
		parentwindow1 = driver.getWindowHandle();
		Thread.sleep(5000);
		Set<String> window1 = driver.getWindowHandles();
		window1.remove(parentwindow1);
		driver.switchTo().window(window1.iterator().next());
		Thread.sleep(5000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
		}
		driver.close();
		Thread.sleep(5000);
		driver.switchTo().window(parentwindow1);
		Thread.sleep(5000);
	}

	public void Franchise_VerifyManageMerchants(String clientname) throws Exception {

		String parentwindow1;
		click(cpaPage.MANAGEMERCHANTSMENU);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
		}
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpaPage.SEARCHBYMERCHANT, driver);
		Thread.sleep(5000);
		type(cpaPage.SEARCHBYMERCHANT, clientname);
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//tr/td/a[contains(.,'" + clientname + "')]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(5000);
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
		Thread.sleep(5000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
		}
		driver.close();
		Thread.sleep(5000);
		driver.switchTo().window(parentwindow1);
		Thread.sleep(5000);
	}

	// R8.14 invite user
	public void InviteNewUser(String role, String accountType, String varType, String clientName, String firstName,
			String lastName, String email, String country, String Phone, String Address, String City, String State,
			String Zip, String Subscription, String SubscriptionOption, String Reservepod) throws Exception {
		waitUntilElementInvisibe(60, "html[@class='nprogressbusy']", driver);
		waitforElement(60, registrationPage.INVITEUSER, driver);
		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		switch (role) {

		case "Customer Admin":

			CustomerAdmin(role, accountType, varType, clientName, firstName, lastName, email, country, Phone, Address,
					City, State, Zip, Subscription, SubscriptionOption, Reservepod);
			break;

		case "Franchise":

			Franchise(role, accountType, varType, clientName, firstName, lastName, email, country, Phone, Address, City,
					State, Zip, Subscription, SubscriptionOption);
			break;
		}
	}

	// R8.15 invite user
	public void InviteNewUser(String role, String accountType, String varType, String clientName, String email,
			String country, String Address, String City, String State, String Zip, String Subscription,
			String SubscriptionOption, String Reservepod) throws Exception {
		waitUntilElementInvisibe(60, "html[@class='nprogressbusy']", driver);
		waitforElement(60, registrationPage.INVITEUSER, driver);
		JavaScriptclick(driver, registrationPage.INVITEUSER);
		Thread.sleep(3000);
		int count = 0;
		waitforElement(180, registrationPage.ADMININVITE, driver);
		JavaScriptclick(driver, registrationPage.ADMININVITE);
		Thread.sleep(2000);
		if (!VerifyObject(driver, registrationPage.ROLE)) {
			boolean report = false;
			do {
				click(registrationPage.ADMININVITE);
				Thread.sleep(5000);
				count = count + 1;
				if (count == 10) {
					break;
				}
				if (VerifyObject(driver, registrationPage.ROLE)) {
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		switch (role) {

		case "Customer Admin":

			CustomerAdmin(role, accountType, varType, clientName, email, country, Address, City, State, Zip,
					Subscription, SubscriptionOption, Reservepod);
			Reporter.log(email);
			logger.debug(email);
			System.out.println("UserName:" + email);
			break;

		case "Franchise":

			Franchise(role, accountType, varType, clientName, email, country, Address, City, State, Zip, Subscription,
					SubscriptionOption);
			Reporter.log(email);
			logger.debug(email);
			System.out.println("UserName:" + email);
			break;
		}
	}

	// R8.15 method for Admin Creation
	public void AdminsCreation(String role, String firstName, String lastName, String email, String Phone,
			String password) throws Exception {
		Thread.sleep(5000);
		// click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		click(registrationPage.EMAIL);
		type(registrationPage.EMAIL, email);
		Thread.sleep(3000);
		click(registrationPage.SNDIVTE);
		// JavaScriptclick(driver, registrationPage.SNDIVTE);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(3000);
		waitforElement(180, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		waitforElement(180, registrationPage.ADMINFIRSTNAME, driver);
		type(registrationPage.ADMINFIRSTNAME, firstName);
		type(registrationPage.ADMINLASTNAME, lastName);
		Thread.sleep(3000);
		click(registrationPage.ADMINPHONE);
		type(registrationPage.ADMINPHONE, Phone);
		sendkeysTab(registrationPage.ADMINPHONE);

		// JavaScriptclick(driver, registrationPage.USERLINK);
		// Thread.sleep(5000);
		// waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
		Reporter.log(email);
		logger.debug(email);
		// type(registrationPage.ADMINSUSERNAME, UserName);
		JavaScripttype(driver, registrationPage.VARPASSWORD, password);
		Thread.sleep(3000);
		JavaScripttype(driver, registrationPage.Adminpasswordconfirm, password);
		click(registrationPage.VARCOMPLETE);
		if (VerifyObject(driver, registrationPage.LOGINBUTTON)) {
			JavaScriptclick(driver, registrationPage.LOGINBUTTON);
			Thread.sleep(3000);
		}
	}

	// New R8.15 method for Admin Creation
	public void AdminsCreationNew(String role, String firstName, String lastName, String email, String Phone,
			String password) throws Exception {
		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		waitforElement(60, registrationPage.EMAIL, driver);
		type(registrationPage.EMAIL, email);
		registrationPage.EMAIL.sendKeys(Keys.CONTROL + "a");
		registrationPage.EMAIL.sendKeys(Keys.CONTROL + "x");
		Thread.sleep(2000);
		registrationPage.EMAIL.sendKeys(Keys.CONTROL + "v");
		Thread.sleep(2000);
		click(registrationPage.SNDIVTE);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		Thread.sleep(5000);
		waitforElement(60, registrationPage.FIRSTNAME, driver);
		type(registrationPage.FIRSTNAME, firstName);
		waitforElement(60, registrationPage.LASTNAME, driver);
		type(registrationPage.LASTNAME, lastName);
		waitforElement(60, registrationPage.PHONE, driver);
		click(registrationPage.PHONE);
		type(registrationPage.PHONE, Phone);
		sendkeysTab(registrationPage.PHONE);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
		JavaScripttype(driver, registrationPage.VARPASSWORD, password);
		Thread.sleep(3000);
		JavaScripttype(driver, registrationPage.Adminpasswordconfirm, password);
		click(registrationPage.VARCOMPLETE);
		if (VerifyObject(driver, registrationPage.LOGINBUTTON)) {
			JavaScriptclick(driver, registrationPage.LOGINBUTTON);
			Thread.sleep(3000);
		}
	}

	// R8.14 method for creation Admin
	public void AdminsCreation(String role, String firstName, String lastName, String email, String Phone,
			String UserName, String password) throws Exception {

		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		waitforElement(60, registrationPage.FIRSTNAME, driver);
		type(registrationPage.FIRSTNAME, firstName);
		type(registrationPage.LASTNAME, lastName);
		type(registrationPage.EMAIL, email);
		Thread.sleep(3000);
		click(registrationPage.PHONE);
		type(registrationPage.PHONE, Phone);
		sendkeysTab(registrationPage.PHONE);
		JavaScriptclick(driver, registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
		Reporter.log(UserName);
		logger.debug(UserName);
		type(registrationPage.ADMINSUSERNAME, UserName);
		JavaScripttype(driver, registrationPage.VARPASSWORD, password);
		Thread.sleep(3000);
		JavaScripttype(driver, registrationPage.Adminpasswordconfirm, password);
		click(registrationPage.VARCOMPLETE);
		if (VerifyObject(driver, registrationPage.LOGINBUTTON)) {
			JavaScriptclick(driver, registrationPage.LOGINBUTTON);
			Thread.sleep(3000);
		}
	}

	public void Franchise(String role, String accountType, String varType, String clientName, String firstName,
			String lastName, String email, String country, String Phone, String Address, String City, String State,
			String Zip, String Subscription, String SubscriptionOption) throws Exception {

		waitforElement(60, registrationPage.CLIENT, driver);
		type(registrationPage.CLIENT, clientName);
		type(registrationPage.FIRSTNAME, firstName);
		type(registrationPage.LASTNAME, lastName);
		type(registrationPage.EMAIL, email);
		selectByValue(registrationPage.NEWUSERCOUNTRY, country);
		Thread.sleep(3000);
		click(registrationPage.PHONE);
		type(registrationPage.PHONE, Phone);
		sendkeysTab(registrationPage.PHONE);
		JavaScriptclick(driver, registrationPage.NEWUSERSTREETADDRESS1);
		type(registrationPage.NEWUSERSTREETADDRESS1, Address);
		click(registrationPage.NEWUSERCITY);
		type(registrationPage.NEWUSERCITY, City);
		if (VerifyObject(driver, registrationPage.NEWUSERSTATE)) {
			selectByValue(registrationPage.NEWUSERSTATE, State);
		} else {
			type(registrationPage.FOREIGNSTATE, State);
		}
		click(registrationPage.NEWUSERZIP);
		JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
		sendkeysTab(registrationPage.NEWUSERZIP);

		selectByValue(registrationPage.NEWUSERSUBSCRIPTION, Subscription);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.PROFESSIONALSUBSCRIPTIONSELECT)) {
			if (SubscriptionOption != "") {
				selectByValue(registrationPage.PROFESSIONALSUBSCRIPTIONSELECT, SubscriptionOption);
			}
		}
		if (VerifyObject(driver, registrationPage.PREMIUMSUBSCRIPTIONSELECT)) {
			if (SubscriptionOption != "") {
				selectByValue(registrationPage.PREMIUMSUBSCRIPTIONSELECT, SubscriptionOption);
			}
		}
	}

	public void Franchise(String role, String accountType, String varType, String clientName, String email,
			String country, String Address, String City, String State, String Zip, String Subscription,
			String SubscriptionOption) throws Exception {

		waitforElement(60, registrationPage.CLIENT, driver);
		type(registrationPage.CLIENT, clientName);

		type(registrationPage.EMAIL, email);
		selectByValue(registrationPage.NEWUSERCOUNTRY, country);
		Thread.sleep(3000);
		JavaScriptclick(driver, registrationPage.NEWUSERSTREETADDRESS1);
		type(registrationPage.NEWUSERSTREETADDRESS1, Address);
		click(registrationPage.NEWUSERCITY);
		type(registrationPage.NEWUSERCITY, City);
		if (VerifyObject(driver, registrationPage.NEWUSERSTATE)) {
			selectByValue(registrationPage.NEWUSERSTATE, State);
		} else {
			type(registrationPage.FOREIGNSTATE, State);
		}
		click(registrationPage.NEWUSERZIP);
		JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
		sendkeysTab(registrationPage.NEWUSERZIP);

		selectByValue(registrationPage.NEWUSERSUBSCRIPTION, Subscription);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.PROFESSIONALSUBSCRIPTIONSELECT)) {
			if (SubscriptionOption != "") {
				selectByValue(registrationPage.PROFESSIONALSUBSCRIPTIONSELECT, SubscriptionOption);
			}
		}
		if (VerifyObject(driver, registrationPage.PREMIUMSUBSCRIPTIONSELECT)) {
			if (SubscriptionOption != "") {
				selectByValue(registrationPage.PREMIUMSUBSCRIPTIONSELECT, SubscriptionOption);
			}
		}
	}

	public void CustomerAdmin(String role, String accountType, String varType, String clientName, String firstName,
			String lastName, String email, String country, String Phone, String Address, String City, String State,
			String Zip, String Subscription, String SubscriptionOption, String Reservepod) throws Exception {

		waitforElement(60, registrationPage.VARNAMES, driver);
		selectByValue(registrationPage.ACCOUNTTYPE, accountType);
		sendkeysTab(registrationPage.ACCOUNTTYPE);
		waitforText(60,
				"//label[contains(.,'VAR/CPA/Franchise Name* :')]/../div/select/option[text()='" + varType + "']",
				driver);
		selectByValue(registrationPage.VARNAMES, varType);

		int m = (int) Math.pow(10, 6 - 1);
		int x = m + new Random().nextInt(9 * m);
		oraclenumber = String.valueOf(x);
		type(registrationPage.OraclePartyNumber, oraclenumber);
		type(registrationPage.CLIENT, clientName);
		// type(registrationPage.FIRSTNAME, firstName);
		// type(registrationPage.LASTNAME, lastName);
		type(registrationPage.EMAIL, email);
		selectByValue(registrationPage.NEWUSERCOUNTRY, country);
		Thread.sleep(3000);
		// click(registrationPage.PHONE);
		// type(registrationPage.PHONE, Phone);
		// sendkeysTab(registrationPage.PHONE);
		JavaScriptclick(driver, registrationPage.NEWUSERSTREETADDRESS1);
		type(registrationPage.NEWUSERSTREETADDRESS1, Address);
		click(registrationPage.NEWUSERCITY);
		type(registrationPage.NEWUSERCITY, City);
		Thread.sleep(2000);
		if (VerifyObject(driver, registrationPage.NEWUSERSTATE)) {
			selectByValue(registrationPage.NEWUSERSTATE, State);
		} else {
			type(registrationPage.FOREIGNSTATE, State);
		}
		click(registrationPage.NEWUSERZIP);
		JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
		sendkeysTab(registrationPage.NEWUSERZIP);
		selectByValue(registrationPage.NEWUSERSUBSCRIPTION, Subscription);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.PROFESSIONALSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			selectByValue(registrationPage.PROFESSIONALSUBSCRIPTIONSELECT, SubscriptionOption);
		}
		if (VerifyObject(driver, registrationPage.PREMIUMSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			selectByValue(registrationPage.PREMIUMSUBSCRIPTIONSELECT, SubscriptionOption);
		}
		if (VerifyObject(driver, registrationPage.ReservedPodText) && !Reservepod.isEmpty()) {
			click(registrationPage.ReservedPodinput);
			if (Environment.equalsIgnoreCase("DEV") || (Environment.equalsIgnoreCase("QA"))) {
				switch (Reservepod) {
				case "Pod 3":
					selectByValue(registrationPage.ReservedPodselection, "Pod 3");
					break;
				case "Pod 42":
					selectByValue(registrationPage.ReservedPodselection, "Pod 42");
					break;
				case "Pod 43":
					selectByValue(registrationPage.ReservedPodselection, "Pod 43");
					break;

				}

			}

			if (Environment.equalsIgnoreCase("STAGE")) {
				selectByValue(registrationPage.ReservedPodselection, "Pod 4");
			}
		}
	}

	public void CustomerAdmin(String role, String accountType, String varType, String clientName, String email,
			String country, String Address, String City, String State, String Zip, String Subscription,
			String SubscriptionOption, String Reservepod) throws Exception {

		waitforElement(60, registrationPage.VARNAMES, driver);
		selectByValue(registrationPage.ACCOUNTTYPE, accountType);
		sendkeysTab(registrationPage.ACCOUNTTYPE);
		waitforText(60,
				"//label[contains(.,'VAR/CPA/Franchise Name* :')]/../div/select/option[text()='" + varType + "']",
				driver);
		selectByValue(registrationPage.VARNAMES, varType);

		int m = (int) Math.pow(10, 6 - 1);
		int x = m + new Random().nextInt(9 * m);
		oraclenumber = String.valueOf(x);
		type(registrationPage.OraclePartyNumber, oraclenumber);
		type(registrationPage.CLIENT, clientName);
		// type(registrationPage.FIRSTNAME, firstName);
		// type(registrationPage.LASTNAME, lastName);
		type(registrationPage.EMAIL, email);
		selectByValue(registrationPage.NEWUSERCOUNTRY, country);
		Thread.sleep(3000);
		// click(registrationPage.PHONE);
		// type(registrationPage.PHONE, Phone);
		// sendkeysTab(registrationPage.PHONE);
		JavaScriptclick(driver, registrationPage.NEWUSERSTREETADDRESS1);
		type(registrationPage.NEWUSERSTREETADDRESS1, Address);
		click(registrationPage.NEWUSERCITY);
		type(registrationPage.NEWUSERCITY, City);
		Thread.sleep(2000);
		if (VerifyObject(driver, registrationPage.NEWUSERSTATE)) {
			selectByValue(registrationPage.NEWUSERSTATE, State);
		} else {
			type(registrationPage.FOREIGNSTATE, State);
		}
		click(registrationPage.NEWUSERZIP);
		JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
		sendkeysTab(registrationPage.NEWUSERZIP);
		selectByValue(registrationPage.NEWUSERSUBSCRIPTION, Subscription);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.PROFESSIONALSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			selectByValue(registrationPage.PROFESSIONALSUBSCRIPTIONSELECT, SubscriptionOption);
		}
		if (VerifyObject(driver, registrationPage.PREMIUMSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			selectByValue(registrationPage.PREMIUMSUBSCRIPTIONSELECT, SubscriptionOption);
		}
		if (VerifyObject(driver, registrationPage.ReservedPodText) && !Reservepod.isEmpty() || !(POD.isEmpty())) {
			click(registrationPage.ReservedPodinput);
			if (!(POD.isEmpty())) {
				selectByValue(registrationPage.ReservedPodselection, POD);
			} else {
				switch (Reservepod) {
				case "3":
					selectByValue(registrationPage.ReservedPodselection, "Pod 3");
					break;
				case "4":
					selectByValue(registrationPage.ReservedPodselection, "Pod 4");
					break;
				case "7":
					selectByValue(registrationPage.ReservedPodselection, "Pod 7");
					break;
				case "11":
					selectByValue(registrationPage.ReservedPodselection, "Pod 11");
					break;
				case "12":
					selectByValue(registrationPage.ReservedPodselection, "Pod 12");
					break;
				case "42":
					selectByValue(registrationPage.ReservedPodselection, "Pod 42");
					break;
				case "43":
					selectByValue(registrationPage.ReservedPodselection, "Pod 43");
					break;
				case "44":
					selectByValue(registrationPage.ReservedPodselection, "Pod 44");
					break;
				case "45":
					selectByValue(registrationPage.ReservedPodselection, "Pod 45");
					break;
				case "90":
					selectByValue(registrationPage.ReservedPodselection, "Pod 90");
					break;
				case "91":
					selectByValue(registrationPage.ReservedPodselection, "Pod 91");
					break;
				case "100":
					selectByValue(registrationPage.ReservedPodselection, "Pod 100");
					break;
				case "101":
					selectByValue(registrationPage.ReservedPodselection, "Pod 101");
					break;
				}
			}
		}
	}

	public void calcparityinvitation(String role, String accountType, String varType, String clientName,
			String firstName, String lastName, String email, String country, String Phone, String Address, String City,
			String State, String Zip, String Subscription, String SubscriptionOption, String Reservepod)
			throws Exception {
		waitUntilElementInvisibe(60, "html[@class='nprogressbusy']", driver);
		waitforElement(60, registrationPage.INVITEUSER, driver);
		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		waitforElement(60, registrationPage.VARNAMES, driver);
		selectByValue(registrationPage.ACCOUNTTYPE, accountType);
		sendkeysTab(registrationPage.ACCOUNTTYPE);
		waitforText(60,
				"//label[contains(.,'VAR/CPA/Franchise Name* :')]/../div/select/option[text()='" + varType + "']",
				driver);
		selectByValue(registrationPage.VARNAMES, varType);

		int m = (int) Math.pow(10, 6 - 1);
		int x = m + new Random().nextInt(9 * m);
		oraclenumber = String.valueOf(x);
		type(registrationPage.OraclePartyNumber, oraclenumber);
		type(registrationPage.CLIENT, clientName);
		type(registrationPage.FIRSTNAME, firstName);
		type(registrationPage.LASTNAME, lastName);
		type(registrationPage.EMAIL, email);
		selectByValue(registrationPage.NEWUSERCOUNTRY, country);
		Thread.sleep(3000);
		click(registrationPage.PHONE);
		type(registrationPage.PHONE, Phone);
		sendkeysTab(registrationPage.PHONE);
		JavaScriptclick(driver, registrationPage.NEWUSERSTREETADDRESS1);
		type(registrationPage.NEWUSERSTREETADDRESS1, Address);
		click(registrationPage.NEWUSERCITY);
		type(registrationPage.NEWUSERCITY, City);
		if (VerifyObject(driver, registrationPage.NEWUSERSTATE)) {
			selectByValue(registrationPage.NEWUSERSTATE, State);
		} else {
			type(registrationPage.FOREIGNSTATE, State);
		}
		click(registrationPage.NEWUSERZIP);
		JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
		sendkeysTab(registrationPage.NEWUSERZIP);
		selectByValue(registrationPage.NEWUSERSUBSCRIPTION, Subscription);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.PROFESSIONALSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			selectByValue(registrationPage.PROFESSIONALSUBSCRIPTIONSELECT, SubscriptionOption);

		}
		if (VerifyObject(driver, registrationPage.PREMIUMSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			// selectByValue(registrationPage.PREMIUMSUBSCRIPTIONSELECT,
			// SubscriptionOption);
			int countriessize = registrationPage.PremiumSubscriptionFeatures.size();
			for (int countries = 0; countries < countriessize; countries++) {
				System.out.println(registrationPage.PremiumSubscriptionFeatures.get(countries).getText());
				assertFalse(registrationPage.PremiumSubscriptionFeatures.get(countries).getText()
						.equals("Cloud Oseries - Tax Calculations"));
			}
		}
		if (VerifyObject(driver, registrationPage.ReservedPodText) && !Reservepod.isEmpty()) {
			click(registrationPage.ReservedPodinput);
			if (Environment.equalsIgnoreCase("DEV") || (Environment.equalsIgnoreCase("QA"))) {
				selectByValue(registrationPage.ReservedPodselection, "Pod 3");
			}

			if (Environment.equalsIgnoreCase("STAGE")) {
				selectByValue(registrationPage.ReservedPodselection, "Pod 4");
			}
		}
	}

	public void AddtionalFeatures(String AddtionalFeature, String DataRegion, String Paymenttype,
			String Billingaddresssameascompanyaddress, String Country, String StreetAddress, String City, String State,
			String Zip) throws Exception {
		String[] feature = AddtionalFeature.split(",");
		if (!AddtionalFeature.isEmpty()) {
			for (int i = 0; i < feature.length; i++) {
				switch (feature[i].toUpperCase()) {
				case "ECW":
					click(registrationPage.ECWFEATURE);
					break;
				case "RATE FILE":
					click(registrationPage.RATEFILE);
					break;
				case "RETAIL TAX EXTRACT":
					click(registrationPage.RETAILTAXEXTRACT);
					break;
				case "EC WIZARD":
					click(registrationPage.ECWIZARDFEATURE);
					break;
				case "PAYROLLTAXCALCULATION":
					click(registrationPage.PayrollCalculation);
					break;
				case "PAYROLLADDRESSCLEANSING":
					click(registrationPage.PayrollAddressCleansing);
					break;
				case "PAYROLLTEST":
					click(registrationPage.PAYROLLTEST);
					break;
				case "REAL - TIME RATE SERVICE":
					click(registrationPage.RTRS);
					break;
				case "TAXABILITY IMPACT REPORT":
					click(registrationPage.TAXIMPACTREPORT);
					break;
				case "GLOBAL COMPLIANCE":
					JavaScriptclick(driver, registrationPage.GLOBALCOMPLIANCE);
					break;
				case "DATA INTEGRITY":
					JavaScriptclick(driver, registrationPage.DATAINTEGRITY);
					Thread.sleep(1000);
					selectByValue(registrationPage.DATASTORAGRREGION, DataRegion);
					break;
				case "LEGACY TRANSACTION REPORT":
					click(registrationPage.LEGACYTRANSACTIONREPORT);
					break;
				case "ACCELERATOR ORACLE ERP CLOUD":
					click(registrationPage.ACCELERATORORACLEERPCLOUD);
					assertTrue(registrationPage.ORACLEERPCLOUD.isDisplayed());
					break;
				case "INDIRECTTAXINTELLIGENCE":
					click(registrationPage.INDIRECTTAXINTELLIGENCE);
					Thread.sleep(2000);
					break;
				case "INVOICE":
					click(registrationPage.InvoiceChk);
					Thread.sleep(1500);
					waitforElement(180, registrationPage.InvoicePaymentMethod, driver);
					selectByValue(registrationPage.InvoicePaymentMethod, Paymenttype);
					if (Billingaddresssameascompanyaddress.equalsIgnoreCase("Yes")) {
						click(registrationPage.InvoiceBillingAddresChkbox);
						Thread.sleep(2000);
						click(registrationPage.InvoiceZip);
						JavaScripttype(driver, registrationPage.InvoiceZip, Zip);
					} else {
						selectByValue(registrationPage.InvoiceCountry, Country);
						type(registrationPage.InvoiceStreet1, StreetAddress);
						type(registrationPage.InvoiceCity, City);
						if (Country.equalsIgnoreCase("UNITED STATES") || Country.equalsIgnoreCase("CANADA")) {
							selectByValue(registrationPage.InvoiceState, State);
						} else {
							type(registrationPage.BillingState, State);
						}
						clear(registrationPage.InvoiceZip);
						JavaScripttype(driver, registrationPage.InvoiceZip, Zip);
						registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "a");
						registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "x");
						Thread.sleep(2000);
						click(registrationPage.InvoiceZip);
						registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "v");
						registrationPage.InvoiceZip.sendKeys(Keys.TAB);
					}
					break;
				default:
				}
			}
		}
		Thread.sleep(4000);
		JavaScriptclick(driver, registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, registrationPage.ZIPERROR)) {
			clear(registrationPage.NEWUSERZIP);
			JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "a");
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			click(registrationPage.NEWUSERZIP);
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "v");
			registrationPage.NEWUSERZIP.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			clear(registrationPage.InvoiceZip);
			JavaScripttype(driver, registrationPage.InvoiceZip, Zip);
			registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "a");
			registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			click(registrationPage.InvoiceZip);
			registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "v");
			registrationPage.InvoiceZip.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			JavaScriptclick(driver, registrationPage.SNDIVTE);
			waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		}
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
		Thread.sleep(10000);

	}

	public void AddtionalFeaturesInvPre(String AddtionalFeature, String DataRegion, String Paymenttype,
			String Billingaddresssameascompanyaddress, String Country, String StreetAddress, String City, String State,
			String Zip) throws Exception {
		String[] feature = AddtionalFeature.split(",");
		if (!AddtionalFeature.isEmpty()) {
			for (int i = 0; i < feature.length; i++) {
				switch (feature[i].toUpperCase()) {
				case "ECW":
					click(registrationPage.ECWFEATURE);
					break;
				case "RATE FILE":
					click(registrationPage.RATEFILE);
					break;
				case "RETAIL TAX EXTRACT":
					click(registrationPage.RETAILTAXEXTRACT);
					break;
				case "EC WIZARD":
					click(registrationPage.ECWIZARDFEATURE);
					break;
				case "PAYROLLTAXCALCULATION":
					click(registrationPage.PayrollCalculation);
					break;
				case "PAYROLLADDRESSCLEANSING":
					click(registrationPage.PayrollAddressCleansing);
					break;
				case "REAL - TIME RATE SERVICE":
					click(registrationPage.RTRS);
					break;
				case "TAXABILITY IMPACT REPORT":
					click(registrationPage.TAXIMPACTREPORT);
					break;
				case "GLOBAL COMPLIANCE":
					JavaScriptclick(driver, registrationPage.GLOBALCOMPLIANCE);
					break;
				case "DATA INTEGRITY":
					JavaScriptclick(driver, registrationPage.DATAINTEGRITY);
					Thread.sleep(1000);
					selectByValue(registrationPage.DATASTORAGRREGION, DataRegion);
					break;
				case "LEGACY TRANSACTION REPORT":
					click(registrationPage.LEGACYTRANSACTIONREPORT);
					break;
				case "ACCELERATOR ORACLE ERP CLOUD":
					click(registrationPage.ACCELERATORORACLEERPCLOUD);
					assertTrue(registrationPage.ORACLEERPCLOUD.isDisplayed());
					break;
				}
			}
		}
		if (!VerifyObject(driver, registrationPage.InvoiceChk)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		Thread.sleep(4000);
		JavaScriptclick(driver, registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, registrationPage.ZIPERROR)) {
			clear(registrationPage.NEWUSERZIP);
			JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "a");
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			click(registrationPage.NEWUSERZIP);
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "v");
			registrationPage.NEWUSERZIP.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			clear(registrationPage.InvoiceZip);
			JavaScripttype(driver, registrationPage.InvoiceZip, Zip);
			registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "a");
			registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			click(registrationPage.InvoiceZip);
			registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "v");
			registrationPage.InvoiceZip.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			JavaScriptclick(driver, registrationPage.SNDIVTE);
			waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		}
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
	}

	public void CompleteRegistrationECWstandard(String userName, String passWord, String passwordConfirm)
			throws Exception {
		waitforElement(180, registrationPage.USERNAME, driver);
		System.out.println(userName);
		type(registrationPage.USERNAME, userName);
		click(registrationPage.PASSWORD);
		type(registrationPage.PASSWORD, passWord);
		click(registrationPage.PASSWORDCONFIRM);
		type(registrationPage.PASSWORDCONFIRM, passwordConfirm);
		Thread.sleep(5000);
		click(registrationPage.ECWFinishandSave);
		Thread.sleep(20000);
		// waitforElement(360, RP.BILLINGADDRESS1, driver);
	}

	// R8.14 method for Invite New User With All Features
	public void InviteNewUserWithAllFeatures(String role, String accountType, String varType, String clientName,
			String firstName, String lastName, String email, String country, String Phone, String Address, String City,
			String State, String Zip, String Subscription, String SubscriptionOption, String RateFile, String RTRS,
			String TIR, String RTE, String GC, String DI, String DI_Value, String ECW, String LegacyTransReport)
			throws Exception {
		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		waitforElement(60, registrationPage.VARNAMES, driver);
		selectByValue(registrationPage.ACCOUNTTYPE, accountType);
		sendkeysTab(registrationPage.ACCOUNTTYPE);
		waitforText(60,
				"//label[contains(.,'VAR/CPA/Franchise Name* :')]/../div/select/option[text()='" + varType + "']",
				driver);
		selectByValue(registrationPage.VARNAMES, varType);

		int m = (int) Math.pow(10, 6 - 1);
		int x = m + new Random().nextInt(9 * m);
		String oraclenumber = String.valueOf(x);
		type(registrationPage.OraclePartyNumber, oraclenumber);
		type(registrationPage.CLIENT, clientName);
		type(registrationPage.FIRSTNAME, firstName);
		type(registrationPage.LASTNAME, lastName);
		type(registrationPage.EMAIL, email);
		selectByValue(registrationPage.NEWUSERCOUNTRY, country);
		click(registrationPage.PHONE);
		type(registrationPage.PHONE, Phone);
		sendkeysTab(registrationPage.PHONE);
		JavaScriptclick(driver, registrationPage.NEWUSERSTREETADDRESS1);
		type(registrationPage.NEWUSERSTREETADDRESS1, Address);
		click(registrationPage.NEWUSERCITY);
		type(registrationPage.NEWUSERCITY, City);
		selectByValue(registrationPage.NEWUSERSTATE, State);
		click(registrationPage.NEWUSERZIP);
		JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
		sendkeysTab(registrationPage.NEWUSERZIP);

		selectByValue(registrationPage.NEWUSERSUBSCRIPTION, Subscription);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.PROFESSIONALSUBSCRIPTIONSELECT)) {
			selectByValue(registrationPage.PROFESSIONALSUBSCRIPTIONSELECT, SubscriptionOption);
		}
		if (VerifyObject(driver, registrationPage.PREMIUMSUBSCRIPTIONSELECT)) {
			selectByValue(registrationPage.PREMIUMSUBSCRIPTIONSELECT, SubscriptionOption);
		}

		if (RateFile.equalsIgnoreCase("Yes")) {
			click(registrationPage.RATEFILE);
		}
		if (RTRS.equalsIgnoreCase("Yes")) {
			click(registrationPage.RTRS);
		}
		if (TIR.equalsIgnoreCase("Yes")) {
			click(registrationPage.TAXIMPACTREPORT);
		}
		if (RTE.equalsIgnoreCase("Yes")) {
			click(registrationPage.RETAILTAXEXTRACT);
		}
		if (GC.equalsIgnoreCase("Yes")) {
			click(registrationPage.GLOBALCOMPLIANCE);
		}
		if (DI.equalsIgnoreCase("Yes")) {
			click(registrationPage.DATAINTEGRITY);
			waitforElement(180, registrationPage.DATASTORAGRREGION, driver);
			selectByValue(registrationPage.DATASTORAGRREGION, DI_Value);
			Thread.sleep(1000);
		}
		if (ECW.equalsIgnoreCase("Yes")) {
			click(registrationPage.ECWFEATURE);
		}
		if (LegacyTransReport.equalsIgnoreCase("Yes")) {
			click(registrationPage.LEGACYTRANSACTIONREPORT);
		}

		JavaScriptclick(driver, registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
	}

	// R8.15 method for Invite New User With All Features
	public void InviteNewUserWithAllFeatures(String role, String accountType, String varType, String clientName,
			String email, String country, String Address, String City, String State, String Zip, String Subscription,
			String SubscriptionOption, String RateFile, String RTRS, String TIR, String RTE, String GC, String DI,
			String DI_Value, String ECW, String LegacyTransReport) throws Exception {
		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		waitforElement(60, registrationPage.VARNAMES, driver);
		selectByValue(registrationPage.ACCOUNTTYPE, accountType);
		sendkeysTab(registrationPage.ACCOUNTTYPE);
		waitforText(60,
				"//label[contains(.,'VAR/CPA/Franchise Name* :')]/../div/select/option[text()='" + varType + "']",
				driver);
		selectByValue(registrationPage.VARNAMES, varType);

		int m = (int) Math.pow(10, 6 - 1);
		int x = m + new Random().nextInt(9 * m);
		String oraclenumber = String.valueOf(x);
		type(registrationPage.OraclePartyNumber, oraclenumber);
		type(registrationPage.CLIENT, clientName);
		type(registrationPage.EMAIL, email);
		selectByValue(registrationPage.NEWUSERCOUNTRY, country);
		click(registrationPage.PHONE);
		sendkeysTab(registrationPage.PHONE);
		JavaScriptclick(driver, registrationPage.NEWUSERSTREETADDRESS1);
		type(registrationPage.NEWUSERSTREETADDRESS1, Address);
		click(registrationPage.NEWUSERCITY);
		type(registrationPage.NEWUSERCITY, City);
		selectByValue(registrationPage.NEWUSERSTATE, State);
		click(registrationPage.NEWUSERZIP);
		JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
		sendkeysTab(registrationPage.NEWUSERZIP);

		selectByValue(registrationPage.NEWUSERSUBSCRIPTION, Subscription);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.PROFESSIONALSUBSCRIPTIONSELECT)) {
			selectByValue(registrationPage.PROFESSIONALSUBSCRIPTIONSELECT, SubscriptionOption);
		}
		if (VerifyObject(driver, registrationPage.PREMIUMSUBSCRIPTIONSELECT)) {
			selectByValue(registrationPage.PREMIUMSUBSCRIPTIONSELECT, SubscriptionOption);
		}

		if (RateFile.equalsIgnoreCase("Yes")) {
			click(registrationPage.RATEFILE);
		}
		if (RTRS.equalsIgnoreCase("Yes")) {
			click(registrationPage.RTRS);
		}
		if (TIR.equalsIgnoreCase("Yes")) {
			click(registrationPage.TAXIMPACTREPORT);
		}
		if (RTE.equalsIgnoreCase("Yes")) {
			click(registrationPage.RETAILTAXEXTRACT);
		}
		if (GC.equalsIgnoreCase("Yes")) {
			click(registrationPage.GLOBALCOMPLIANCE);
		}
		if (DI.equalsIgnoreCase("Yes")) {
			click(registrationPage.DATAINTEGRITY);
			waitforElement(180, registrationPage.DATASTORAGRREGION, driver);
			selectByValue(registrationPage.DATASTORAGRREGION, DI_Value);
			Thread.sleep(1000);
		}
		if (ECW.equalsIgnoreCase("Yes")) {
			click(registrationPage.ECWFEATURE);
		}
		if (LegacyTransReport.equalsIgnoreCase("Yes")) {
			click(registrationPage.LEGACYTRANSACTIONREPORT);
		}

		JavaScriptclick(driver, registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
	}

	// R8.14 user registration
	public void CompleteRegistration(String userName, String passWord, String passwordConfirm) throws Exception {

		waitforElement(360, registrationPage.USERNAME, driver);
		type(registrationPage.USERNAME, userName);
		Reporter.log(userName);
		logger.debug(userName);
		System.out.println("UserName:" + userName);
		click(registrationPage.PASSWORD);
		type(registrationPage.PASSWORD, passWord);
		click(registrationPage.PASSWORDCONFIRM);
		type(registrationPage.PASSWORDCONFIRM, passwordConfirm);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.NEXTSTEP2)) {
			click(registrationPage.NEXTSTEP2);
			Thread.sleep(4000);
			waitforElement(360, registrationPage.BILLINGADDRESS1, driver);
		} else if (VerifyObject(driver, registrationPage.Submit)) {
			click(registrationPage.Submit);
			Thread.sleep(4000);
		} else {
			if (VerifyObject(driver, registrationPage.ECWFinishandSave))
				click(registrationPage.ECWFinishandSave);
		}
		Thread.sleep(20000);
	}

	// R8.15 user registration
	public void CompleteRegistration(String firstName, String lastName, String Phone, String passWord,
			String passwordConfirm) throws Exception {
		Thread.sleep(3000);
		waitforElement(180, registrationPage.FIRSTNAME, driver);
		assertTrue(registrationPage.FIRSTNAME.isDisplayed());
		if (VerifyObject(driver, registrationPage.FIRSTNAME)) {
			type(registrationPage.FIRSTNAME, firstName);
			type(registrationPage.LASTNAME, lastName);
			click(registrationPage.PHONE);
			type(registrationPage.PHONE, Phone);
			sendkeysTab(registrationPage.PHONE);
			click(registrationPage.PASSWORD);
			type(registrationPage.PASSWORD, passWord);
			click(registrationPage.PASSWORDCONFIRM);
			type(registrationPage.PASSWORDCONFIRM, passwordConfirm);
			Thread.sleep(5000);
		}
		if (VerifyObject(driver, registrationPage.NEXTSTEP2)) {
			click(registrationPage.NEXTSTEP2);
			Thread.sleep(4000);
			// waitforElement(360, registrationPage.BILLINGADDRESS1, driver);
		} else if (VerifyObject(driver, registrationPage.Submit)) {
			click(registrationPage.Submit);
			Thread.sleep(4000);
		} else {
			if (VerifyObject(driver, registrationPage.ECWFinishandSave))
				click(registrationPage.ECWFinishandSave);
		}
		Thread.sleep(20000);
	}

	public void CompleteRegistrationforOracleERPCloud(String userName, String passWord, String passwordConfirm)
			throws Exception {

		waitforElement(180, registrationPage.USERNAME, driver);
		type(registrationPage.USERNAME, userName);
		Reporter.log(userName);
		logger.debug(userName);
		System.out.println("UserName:" + userName);
		click(registrationPage.PASSWORD);
		type(registrationPage.PASSWORD, passWord);
		click(registrationPage.PASSWORDCONFIRM);
		type(registrationPage.PASSWORDCONFIRM, passwordConfirm);
		Thread.sleep(5000);
		assertTrue(registrationPage.ACCELERATORDISABLED.isDisplayed());
		assertTrue(registrationPage.ACCELERATORTEXT1.isDisplayed());
		assertTrue(registrationPage.ACCELERATORTEXT2.isDisplayed());
		if (VerifyObject(driver, registrationPage.NEXTSTEP2)) {
			click(registrationPage.NEXTSTEP2);
			Thread.sleep(4000);
			waitforElement(180, registrationPage.BILLINGADDRESS1, driver);
		} else if (VerifyObject(driver, registrationPage.Submit)) {
			click(registrationPage.Submit);
			Thread.sleep(4000);
		} else {
			if (VerifyObject(driver, registrationPage.ECWFinishandSave))
				click(registrationPage.ECWFinishandSave);
		}
		Thread.sleep(20000);
	}

	// R8.14 code
	public void CompleteRegistrationforpyroll(String userName, String passWord, String passwordConfirm)
			throws Exception {

		waitforElement(180, registrationPage.USERNAME, driver);
		type(registrationPage.USERNAME, userName);
		Reporter.log(userName);
		logger.debug(userName);
		click(registrationPage.PASSWORD);
		type(registrationPage.PASSWORD, passWord);
		click(registrationPage.PASSWORDCONFIRM);
		type(registrationPage.PASSWORDCONFIRM, passwordConfirm);
		Thread.sleep(5000);
		assertTrue(registrationPage.payrollcalcdisabledtext.isDisplayed());
		assertTrue(registrationPage.payrolladdresscleansingdisabledtext.isDisplayed());
		if (VerifyObject(driver, registrationPage.NEXTSTEP2)) {
			click(registrationPage.NEXTSTEP2);
			Thread.sleep(4000);
			waitforElement(180, registrationPage.BILLINGADDRESS1, driver);
		} else if (VerifyObject(driver, registrationPage.Submit)) {
			click(registrationPage.Submit);
			Thread.sleep(4000);
		} else {
			if (VerifyObject(driver, registrationPage.ECWFinishandSave))
				click(registrationPage.ECWFinishandSave);
		}
		Thread.sleep(20000);
	}

	// R.15 code
	public void CompleteRegistrationforpyroll(String firstName, String lastName, String Phone, String passWord,
			String passwordConfirm) throws Exception {

		waitforElement(180, registrationPage.FIRSTNAME, driver);
		type(registrationPage.FIRSTNAME, firstName);
		type(registrationPage.LASTNAME, lastName);
		click(registrationPage.PHONE);
		type(registrationPage.PHONE, Phone);
		sendkeysTab(registrationPage.PHONE);
		click(registrationPage.PASSWORD);
		type(registrationPage.PASSWORD, passWord);
		click(registrationPage.PASSWORDCONFIRM);
		type(registrationPage.PASSWORDCONFIRM, passwordConfirm);
		Thread.sleep(5000);
		assertTrue(registrationPage.payrollcalcdisabledtext.isDisplayed());
		assertTrue(registrationPage.payrolladdresscleansingdisabledtext.isDisplayed());
		if (VerifyObject(driver, registrationPage.NEXTSTEP2)) {
			click(registrationPage.NEXTSTEP2);
			Thread.sleep(4000);
			waitforElement(180, registrationPage.BILLINGADDRESS1, driver);
		} else if (VerifyObject(driver, registrationPage.Submit)) {
			click(registrationPage.Submit);
			Thread.sleep(4000);
		} else {
			if (VerifyObject(driver, registrationPage.ECWFinishandSave))
				click(registrationPage.ECWFinishandSave);
		}
		Thread.sleep(20000);
	}

	public void NewUserBilling(String billingCountry, String billingAddress, String billingCity, String billingState,
			String billingZip, String creditcardNumber, String expirationMonth, String expirationYear, String CSV)
			throws Exception {
		Thread.sleep(10000);
		if (VerifyObject(driver, registrationPage.UPDATEBILLING)) {
			click(registrationPage.UPDATEBILLING);
			waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		}
		int count = 0;
		if (!VerifyObject(driver, registrationPage.BILLINGCOUNTRYDDRPDOWN)) {
			boolean report = false;
			do {
				click(registrationPage.UPDATEBILLING);
				waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
				count = count + 1;
				if (count == 2) {
					break;
				}
				if (VerifyObject(driver, registrationPage.BILLINGCOUNTRYDDRPDOWN)) {
					report = true;
				}
			} while (report == false);
		}

		selectByValue(registrationPage.BILLINGCOUNTRYDDRPDOWN, billingCountry);
		type(registrationPage.BILLINGADDRESS1, billingAddress);
		type(registrationPage.BILLINGCITY, billingCity);
		Thread.sleep(10000);
		selectByValue(registrationPage.BILLINGSTATE, billingState);
		click(registrationPage.BILLINGZIP);
		JavaScripttype(driver, registrationPage.BILLINGZIP, billingZip);
		sendkeysTab(registrationPage.BILLINGZIP);
		click(registrationPage.BILLINGCITY);
		Thread.sleep(3000);
		if (registrationPage.CREDITCARDPAYMENT.isDisplayed()) {
			click(registrationPage.CREDITCARDPAYMENT);
		}
		click(registrationPage.CREDITCARD);
		clear(registrationPage.CREDITCARD);
		click(registrationPage.CREDITCARD);
		type(registrationPage.CREDITCARD, creditcardNumber);
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScripttypespace(driver, registrationPage.CREDITCARD, creditcardNumber);
			Thread.sleep(3000);
			sendkeybackSpace(registrationPage.CREDITCARD);
		}
		selectByValue(registrationPage.EXPMONTH, expirationMonth);
		selectByValue(registrationPage.EXPYEAR, expirationYear);
		type(registrationPage.CVN, CSV);
		if (VerifyObject(driver, registrationPage.SUBMITBILLINGINFOBUTTON)) {
			click(registrationPage.SUBMITBILLINGINFOBUTTON);
		} else {
			click(registrationPage.SUBMIT);
		}
		Thread.sleep(10000);
		if (VerifyObject(driver, registrationPage.DASHBOARDLINK)) {
			waitforElement(90, registrationPage.DASHBOARDLINK, driver);
			assertTrue(registrationPage.DASHBOARDLINK.isDisplayed());
		} else {
			waitforElement(90, registrationPage.INDIRECTTAXOSERIESCLOUD, driver);
			assertTrue(registrationPage.INDIRECTTAXOSERIESCLOUD.isDisplayed());
		}
	}

	// R8.15 code
	public void payrollNewUserBilling(String billingCountry, String billingAddress, String billingCity,
			String billingState, String billingZip, String creditcardNumber, String expirationMonth,
			String expirationYear, String CSV) throws Exception {
		Thread.sleep(2000);
		if (VerifyObject(driver, registrationPage.UPDATEBILLING)) {
			click(registrationPage.UPDATEBILLING);
			waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		}
		selectByValue(registrationPage.BILLINGCOUNTRYDDRPDOWN, billingCountry);
		type(registrationPage.BILLINGADDRESS1, billingAddress);
		type(registrationPage.BILLINGCITY, billingCity);
		Thread.sleep(10000);
		selectByValue(registrationPage.BILLINGSTATE, billingState);
		click(registrationPage.BILLINGZIP);
		JavaScripttype(driver, registrationPage.BILLINGZIP, billingZip);
		sendkeysTab(registrationPage.BILLINGZIP);
		click(registrationPage.BILLINGCITY);
		Thread.sleep(3000);
		if (registrationPage.CREDITCARDPAYMENT.isDisplayed()) {
			click(registrationPage.CREDITCARDPAYMENT);
		}
		click(registrationPage.CREDITCARD);
		clear(registrationPage.CREDITCARD);
		click(registrationPage.CREDITCARD);
		type(registrationPage.CREDITCARD, creditcardNumber);
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScripttypespace(driver, registrationPage.CREDITCARD, creditcardNumber);
			Thread.sleep(3000);
			sendkeybackSpace(registrationPage.CREDITCARD);
		}
		selectByValue(registrationPage.EXPMONTH, expirationMonth);
		selectByValue(registrationPage.EXPYEAR, expirationYear);
		type(registrationPage.CVN, CSV);

		if (VerifyObject(driver, registrationPage.SUBMITBILLINGINFOBUTTON)) {
			click(registrationPage.SUBMITBILLINGINFOBUTTON);
		} else {
			click(registrationPage.SUBMIT);
		}
		Thread.sleep(10000);

	}

	// R8.16 code
	public void calcparityNewUserBilling(String billingCountry, String billingAddress, String billingCity,
			String billingState, String billingZip, String creditcardNumber, String expirationMonth,
			String expirationYear, String CSV) throws Exception {
		Thread.sleep(2000);
		if (VerifyObject(driver, registrationPage.UPDATEBILLING)) {
			click(registrationPage.UPDATEBILLING);
			waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		}
		selectByValue(registrationPage.BILLINGCOUNTRYDDRPDOWN, billingCountry);
		type(registrationPage.BILLINGADDRESS1, billingAddress);
		type(registrationPage.BILLINGCITY, billingCity);
		Thread.sleep(10000);
		selectByValue(registrationPage.BILLINGSTATE, billingState);
		click(registrationPage.BILLINGZIP);
		JavaScripttype(driver, registrationPage.BILLINGZIP, billingZip);
		sendkeysTab(registrationPage.BILLINGZIP);
		click(registrationPage.BILLINGCITY);
		Thread.sleep(3000);
		if (registrationPage.CREDITCARDPAYMENT.isDisplayed()) {
			click(registrationPage.CREDITCARDPAYMENT);
		}
		click(registrationPage.CREDITCARD);
		clear(registrationPage.CREDITCARD);
		click(registrationPage.CREDITCARD);
		type(registrationPage.CREDITCARD, creditcardNumber);
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScripttypespace(driver, registrationPage.CREDITCARD, creditcardNumber);
			Thread.sleep(3000);
			sendkeybackSpace(registrationPage.CREDITCARD);
		}
		selectByValue(registrationPage.EXPMONTH, expirationMonth);
		selectByValue(registrationPage.EXPYEAR, expirationYear);
		type(registrationPage.CVN, CSV);

		if (VerifyObject(driver, registrationPage.SUBMITBILLINGINFOBUTTON)) {
			click(registrationPage.SUBMITBILLINGINFOBUTTON);
		} else {
			click(registrationPage.SUBMIT);
		}
		Thread.sleep(10000);

	}

	public void ACHBilling(String billingCountry, String billingAddress, String billingCity, String billingState,
			String billingZip, String BankNumber_ACH, String RoutingNumber_ACH) throws Exception {

		Thread.sleep(2000);
		if (VerifyObject(driver, registrationPage.UPDATEBILLING)) {
			click(registrationPage.UPDATEBILLING);
		}
		waitforElement(180, registrationPage.BILLINGCOUNTRYDDRPDOWN, driver);
		selectByValue(registrationPage.BILLINGCOUNTRYDDRPDOWN, billingCountry);
		type(registrationPage.BILLINGADDRESS1, billingAddress);
		type(registrationPage.BILLINGCITY, billingCity);
		Thread.sleep(10000);
		selectByValue(registrationPage.BILLINGSTATE, billingState);
		click(registrationPage.BILLINGZIP);
		JavaScripttype(driver, registrationPage.BILLINGZIP, billingZip);
		sendkeysTab(registrationPage.BILLINGZIP);
		click(registrationPage.BILLINGCITY);
		Thread.sleep(3000);
		waitforElement(180, registrationPage.ACH, driver);
		click(registrationPage.ACH);
		Thread.sleep(5000);
		clear(registrationPage.BANKNUMBER);
		clear(registrationPage.ROUTINGNUMBER);
		type(registrationPage.BANKNUMBER, BankNumber_ACH);
		type(registrationPage.ROUTINGNUMBER, RoutingNumber_ACH);

		if (VerifyObject(driver, registrationPage.SUBMITBILLINGINFOBUTTON)) {
			click(registrationPage.SUBMITBILLINGINFOBUTTON);
		} else {
			click(registrationPage.SUBMIT);
		}
		waitforElement(180, registrationPage.DASHBOARDLINK, driver);
	}

	public boolean Userexists(String firstname, String Email) throws Exception {

		waitforElement(180, registrationPage.INVITEUSER, driver);
		JavaScriptclick(driver, registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		type(registrationPage.userinput, firstname);
		Thread.sleep(3000);
		if (VerifyObject(driver, "//table/thead/tr/th[contains(.,'Client Name')]/../../../tbody/tr/td[contains(text(),'"
				+ firstname + "')]/../../../tbody/tr/td[contains(text(),'" + Email + "')]")) {
			return true;
		} else {
			return false;
		}
	}

	public void Verifypageresponse() throws Exception {
		driver.findElement(By.xpath("//p[contains(.,'Page Not Found.')]")).isDisplayed();
		driver.getTitle().equalsIgnoreCase("Page Not Found | Vertex Cloud");
	}

	public void FranchiseAdminLogin(String user, String pwd) throws Exception {

		loginPage.USERNAME.sendKeys(user);
		loginPage.PASSWORD.sendKeys(pwd);
		loginPage.ECLogin.click();
		Reporter.log("FranchisePortal User Name :" + user);
	}

	// R8.14 method for create new user from settings page
	/*
	 * public void CreateNewUser(String Firstname, String LastName, String Email,
	 * String Phone, String AccountManagementRole, String VertexIndirecttaxrole,
	 * String ECMRole, String GlobaComplaincerole, String DataIntegrityrole, String
	 * UserName, String Password, String ConfirmPassword) throws Exception { if
	 * (Browser.equalsIgnoreCase("Firefox")) {
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); } else {
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); }
	 * JavaScriptclick(driver, professionalLinks.DASHBOARDLINK);
	 * waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
	 * JavaScriptclick(driver, cpamanagersPage.SETTINGS);
	 * waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
	 * waitforElement(180, cpamanagersPage.ADDNEWUSER, driver);
	 * click(cpamanagersPage.ADDNEWUSER); waitUntilElementInvisibe(180,
	 * configurePage.NPROGRESSBUSY_XPATH, driver); waitforElement(180,
	 * cpamanagersPage.FIRSTNAME, driver); type(cpamanagersPage.FIRSTNAME,
	 * Firstname); // FirstName = Firstname; type(cpamanagersPage.LASTNAME1,
	 * LastName); // lastname = LastName; type(cpamanagersPage.USEREMAIL, Email);
	 * click(cpamanagersPage.PHONE); type(cpamanagersPage.PHONE, Phone);
	 * waitforElement(180, cpamanagersPage.ACCOUNTMANAGEMENTROLE, driver); if
	 * (AccountManagementRole != "") {
	 * selectByValue(cpamanagersPage.ACCOUNTMANAGEMENTROLE, AccountManagementRole);
	 * } if (VertexIndirecttaxrole != "") {
	 * selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE,
	 * VertexIndirecttaxrole); }
	 * 
	 * if (VerifyObject(driver, cpamanagersPage.EXEMPTIONCMROLE) && ECMRole != "") {
	 * selectByValue(cpamanagersPage.EXEMPTIONCMROLE, ECMRole); } if
	 * (VerifyObject(driver, cpamanagersPage.GLOBALCOMPLAINCEROLE) &&
	 * GlobaComplaincerole != "") {
	 * selectByValue(cpamanagersPage.GLOBALCOMPLAINCEROLE, GlobaComplaincerole); }
	 * if (VerifyObject(driver, cpamanagersPage.DATAINTEGRITYROLE) &&
	 * DataIntegrityrole != "") { selectByValue(cpamanagersPage.DATAINTEGRITYROLE,
	 * DataIntegrityrole); } Thread.sleep(1000);
	 * 
	 * // assertTrue(NU.CLIENTACCOUNTACCESS.isDisplayed());
	 * click(cpamanagersPage.SENDINVITEUSER); waitUntilElementInvisibe(180,
	 * configurePage.NPROGRESSBUSY_XPATH, driver); waitforElement(180,
	 * cpamanagersPage.COMPLETEREGISTRATIONLINK, driver);
	 * click(cpamanagersPage.COMPLETEREGISTRATIONLINK);
	 * waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
	 * waitforElement(180, cpamanagersPage.USERNAME, driver); PortalUserName =
	 * UserName; type(cpamanagersPage.USERNAME, PortalUserName);
	 * System.out.println("CPA Admin name " + PortalUserName);
	 * type(cpamanagersPage.PASSWORD, Password);
	 * type(cpamanagersPage.CONFIRMPASSWORD, ConfirmPassword); Thread.sleep(1000);
	 * JavaScriptclick(driver, cpamanagersPage.PROFILECOMP_REG);
	 * waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
	 * waitforElement(60, cpamanagersPage.LOGIN, driver);
	 * click(cpamanagersPage.LOGIN); waitforElement(60, cpamanagersPage.SETTINGS,
	 * driver); JavaScriptclick(driver, cpamanagersPage.SETTINGS);
	 * waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
	 * waitforElement(180, cpamanagersPage.VIEWALLUSERS, driver);
	 * click(cpamanagersPage.VIEWALLUSERS); waitUntilElementInvisibe(180,
	 * configurePage.NPROGRESSBUSY_XPATH, driver); // Verifying Client Access
	 * POR-7219 // MultipleStaffClients = CPAAdminName + ";";
	 * 
	 * }
	 */
	// R8.15 method for create new user from settings page
	public void CreateNewUser(String Firstname, String LastName, String Emailid, String PhoneNumber,
			String AccountManagementRole, String VertexIndirecttaxrole, String ECMRole, String GlobaComplaincerole,
			String DataIntegrityrole, String IndirectTaxIntelligence, String Password, String ConfirmPassword)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, professionalLinks.DASHBOARDLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, cpamanagersPage.SETTINGS);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.ADDNEWUSER, driver);
		click(cpamanagersPage.ADDNEWUSER);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.USEREMAIL, driver);
		type(cpamanagersPage.USEREMAIL, Emailid);
		waitforElement(180, cpamanagersPage.ACCOUNTMANAGEMENTROLE, driver);
		if (AccountManagementRole != "") {
			selectByValue(cpamanagersPage.ACCOUNTMANAGEMENTROLE, AccountManagementRole);
		}
		if (VertexIndirecttaxrole != "") {
			selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE, VertexIndirecttaxrole);
		}

		if (IndirectTaxIntelligence != "") {
			selectByValue(cpamanagersPage.INDIRECTTAXINTELLIGENCE, IndirectTaxIntelligence);
		}

		if (VerifyObject(driver, cpamanagersPage.EXEMPTIONCMROLE) && ECMRole != "") {
			selectByValue(cpamanagersPage.EXEMPTIONCMROLE, ECMRole);
		}
		if (VerifyObject(driver, cpamanagersPage.GLOBALCOMPLAINCEROLE) && GlobaComplaincerole != "") {
			selectByValue(cpamanagersPage.GLOBALCOMPLAINCEROLE, GlobaComplaincerole);
		}
		if (VerifyObject(driver, cpamanagersPage.DATAINTEGRITYROLE) && DataIntegrityrole != "") {
			selectByValue(cpamanagersPage.DATAINTEGRITYROLE, DataIntegrityrole);
		}
		Thread.sleep(1000);

		// assertTrue(NU.CLIENTACCOUNTACCESS.isDisplayed());
		click(cpamanagersPage.SENDINVITEUSER);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.COMPLETEREGISTRATIONLINK, driver);
		click(cpamanagersPage.COMPLETEREGISTRATIONLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		PortalUserName = Emailid;
		System.out.println("CPA Admin name " + PortalUserName);
		waitforElement(180, cpamanagersPage.FIRSTNAME, driver);
		type(cpamanagersPage.FIRSTNAME, Firstname);
		// FirstName = Firstname;
		type(cpamanagersPage.LASTNAME, LastName);
		// lastname = LastName;
		click(cpamanagersPage.PHONENUMBER);
		type(cpamanagersPage.PHONENUMBER, PhoneNumber);
		type(cpamanagersPage.PASSWORD, Password);
		type(cpamanagersPage.CONFIRMPASSWORD, ConfirmPassword);
		Thread.sleep(1000);
		JavaScriptclick(driver, cpamanagersPage.PROFILECOMP_REG);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, cpamanagersPage.LOGIN, driver);
		click(cpamanagersPage.LOGIN);
		/*
		 * waitforElement(60, cpamanagersPage.SETTINGS, driver); JavaScriptclick(driver,
		 * cpamanagersPage.SETTINGS); waitUntilElementInvisibe(180,
		 * configurePage.NPROGRESSBUSY_XPATH, driver); waitforElement(180,
		 * cpamanagersPage.VIEWALLUSERS, driver); click(cpamanagersPage.VIEWALLUSERS);
		 * waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver); //
		 * Verifying Client Access POR-7219 // MultipleStaffClients = CPAAdminName +
		 * ";";
		 */
	}

	public void OseriesLogin(String UserName, String Password) throws Exception {

		waitforElement(180, loginPage.Oseriesusername, driver);
		type(loginPage.Oseriesusername, UserName);
		click(loginPage.Oseriespassword);
		clear(loginPage.Oseriespassword);
		type(loginPage.Oseriespassword, Password);
		JavaScriptclick(driver, loginPage.Oserieslogin);
		waitforElement(60, loginPage.Oseriesloout, driver);
		LoginName = UserName;
		PasswordName = Password;
		Reporter.log(UserName);
		Thread.sleep(5000);

	}

	public void OseriesLogout() throws Exception {

		JavaScriptclick(driver, loginPage.Oseriesloout);
		waitforElement(120, loginPage.Oserieslogin, driver);
		Thread.sleep(5000);

	}

	public void SMBCustomerCreateNewUser(String FirstName, String LastName, String Email, String Phone, String UserRole,
			String UserName, String ConfirmPassword, String AfterLastLogin, String RTERonly, String Pending_User,
			String Merchant_Customer, String Franchise_Customer, String LoginRonly, String View_Warning,
			String RTE_Name, String VertexCloudIndirectTax, String GlobalCompliance, String ECManager,
			String DataIntegrity, String IndirectTaxIntelligence) throws Exception {

		Cpa_ManageUsers NU = PageFactory.initElements(driver, Cpa_ManageUsers.class);
		SmbCustomerUserPage SUP = PageFactory.initElements(driver, SmbCustomerUserPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		RtePage RTE = PageFactory.initElements(driver, RtePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		new Robot();
		String[] regcData;
		String SystemDateTime;
		if (!LoginRonly.equalsIgnoreCase("Yes")) {
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, NU.SETTINGS);
			} else {
				click(NU.SETTINGS);
			}
			String[] regfData = FirstName.split(";");
			firstname = xLabelRND(regfData);
			String[] regfData1 = LastName.split(";");
			lastname = xLabelRND(regfData1);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			// Creating new user from Franchise Portal as per POR-20042
			if (Franchise_Customer.equalsIgnoreCase("Yes")) {
				if (Merchant_Customer.equalsIgnoreCase("Franchise")) {
					Activecount = driver.findElement(By.xpath("//li[1]/a/strong")).getText();
					Pendingcount = driver.findElement(By.xpath("//li[2]/a/strong")).getText();
					waitforElement(180, SUP.FRANCHISECREATENEWUSER, driver);
					click(SUP.DELETEDUSERSLNK);
					waitforElement(80, SUP.DeletedUsersPage1, driver);
					// assertTrue(SUP.DeletedUsersPage.isDisplayed());
					assertTrue(SUP.DeletedUsersPage1.isDisplayed());
					click(SUP.ReturntoSettingsRightBtn);
					waitforElement(180, SUP.FRANCHISECREATENEWUSER, driver);
					click(SUP.FRANCHISECREATENEWUSER);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, SUP.FRANCHISEFIRSTNAME, driver);
					regcData = FirstName.split(";");
					FranchiseFN = xLabelRND(regcData);
					regcData = LastName.split(";");
					FranchiseLN = xLabelRND(regcData);
					type(SUP.FRANCHISEFIRSTNAME, FranchiseFN);
					type(SUP.FRANCHISELASTNAME, FranchiseLN);
					type(SUP.FRANCHISEEMAIL, Email);
					click(SUP.FRANCHISEPHONE);
					type(SUP.FRANCHISEPHONE, Phone);
					SUP.FRANCHISEPHONE.sendKeys(Keys.CONTROL + "a");
					SUP.FRANCHISEPHONE.sendKeys(Keys.CONTROL + "x");
					Thread.sleep(2000);
					SUP.FRANCHISEPHONE.sendKeys(Keys.CONTROL + "v");
					sendkeysTab(SUP.FRANCHISEPHONE);
					Thread.sleep(3000);
					if (Browser.equalsIgnoreCase("IE")) {
						JavaScriptclick(driver, SUP.SEND_INVITE);
					} else {
						click(SUP.SEND_INVITE);
					}
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(120, SUP.CompleteRegistrationLink, driver);
					click(SUP.CompleteRegistrationLink);
					waitforElement(180, SUP.FRANCHISEUSERNAME, driver);
					regcData = UserName.split(";");
					FranchiseUser = xLabelRND(regcData);
					type(SUP.FRANCHISEUSERNAME, FranchiseUser);
					type(SUP.PASSWORD, ConfirmPassword);
					type(SUP.CONFIRMPASSWORD, ConfirmPassword);
					Thread.sleep(3000);
					/*
					 * Verification=""; Verification=(VerifyObject(driver,
					 * SUP.SERVICEAgreement))==true? Verification:
					 * Verification+"Fail; Service Agreement check box not displayed;";
					 * status=status+Verification; VertexSMB.RowNo =
					 * bussFunction.xResult(Verification,
					 * RSheet,"Service Agreement type check box not displayed","",value,NoOfVerify++
					 * ); click(SUP.SERVICEAgreement);
					 */
					click(SUP.COMPLETERegistration);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(5000);
					if (VerifyObject(driver, SUP.SuccessMessage)) {
						Thread.sleep(3000);
						click(SUP.LOGIN);
						Thread.sleep(3000);
						if (Browser.equalsIgnoreCase("IE")) {
							JavaScriptclick(driver, NU.SETTINGS);
						} else {
							click(NU.SETTINGS);
						}
						Thread.sleep(3000);
						driver.findElement(
								By.xpath("//li/a[@href='/users#/all?isActiveUser=true']/strong[contains(.,'Active')]"))
								.click();
						Thread.sleep(3000);
					}
					assertTrue(SUP.Manage_Users_Title.isDisplayed());
					assertTrue(SUP.QuickSearch.isDisplayed());
					assertTrue(SUP.Pagination.isDisplayed());
					click(SUP.QuickSearch);
					type(SUP.QuickSearch, FranchiseUser);
					Thread.sleep(5000);
					waitforElement(180, "//table/tbody/tr/td[3]", driver);
					String UName = driver.findElement(By.xpath("//table/tbody/tr/td[3]")).getText();
					assertTrue((FranchiseUser).equalsIgnoreCase(UName));
					Thread.sleep(5000);
					WebElement Active = driver.findElement(By.xpath("//table/tbody/tr/td[1]/a"));
					click(Active);
					// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Active);
					waitUntilElementInvisibe(240, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(60, SUP.UserActionsTitle, driver);
					assertTrue(SUP.UserActionsTitle.isDisplayed());
					String DeleteDescription = "Click Delete User to revoke this user's account access immediately. All user information will be retained, however, the user will not be able to log into their account. You can reactivate this user at any time.";
					String Delete = driver.findElement(By.xpath("//h4[contains(.,'Delete User')]/../p")).getText();
					assertTrue((DeleteDescription).equalsIgnoreCase(Delete));
					waitforElement(60, SUP.DELETEUSER, driver);
					click(SUP.DELETEUSER);
					Thread.sleep(3000);
					waitforElement(30, SUP.confirmremove, driver);
					click(SUP.confirmremove);
					Thread.sleep(3000);
					waitforElement(30, SUP.Success, driver);
					click(SUP.RETURNTOUSERLISTING);
					waitUntilElementInvisibe(40, CNP.NPROGRESSBUSY_XPATH, driver);
					click(SUP.DeletedUsers);
					Thread.sleep(3000);
					type(SUP.QuickSearch, FranchiseUser);
					waitforElement(40, "//table/tbody/tr/td[contains(.,'" + FranchiseFN + " " + FranchiseLN
							+ " ')]/../td[contains(.,'Deleted')]", driver);
					WebElement Deleted = driver.findElement(
							By.xpath("//table/tbody/tr/td[contains(.,'" + FranchiseFN + " " + FranchiseLN + "')]/a"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Deleted);
					waitUntilElementInvisibe(40, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(60, SUP.Success, driver);
					Thread.sleep(5000);
					assertTrue(SUP.ReactivateDescription.isDisplayed());
					Thread.sleep(5000);
					click(SUP.REACTIVATEUSER);
					Thread.sleep(5000);
					click(SUP.ConfirmReactivate);
					waitforElement(30, SUP.Success, driver);
					click(SUP.RETURNTOUSERLISTING);
					waitUntilElementInvisibe(30, CNP.NPROGRESSBUSY_XPATH, driver);
					assertTrue(SUP.ExportButton.isDisplayed());
					waitforElement(60, NU.SETTINGS, driver);
				}
				if (Pending_User.equalsIgnoreCase("Yes")) {
					click(NU.SETTINGS);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					click(SUP.FRANCHISECREATENEWUSER);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, SUP.FRANCHISEFIRSTNAME, driver);
					regcData = FirstName.split(";");
					PendfingFN = xLabelRND(regcData);
					regcData = LastName.split(";");
					PendfingLN = xLabelRND(regcData);
					type(SUP.FRANCHISEFIRSTNAME, PendfingFN);
					type(SUP.FRANCHISELASTNAME, PendfingLN);
					type(SUP.FRANCHISEEMAIL, Email);
					click(SUP.FRANCHISEPHONE);
					type(SUP.FRANCHISEPHONE, Phone);
					SUP.FRANCHISEPHONE.sendKeys(Keys.CONTROL + "a");
					SUP.FRANCHISEPHONE.sendKeys(Keys.CONTROL + "x");
					Thread.sleep(2000);
					SUP.FRANCHISEPHONE.sendKeys(Keys.CONTROL + "v");
					sendkeysTab(SUP.FRANCHISEPHONE);
					Thread.sleep(3000);
					if (Browser.equalsIgnoreCase("IE")) {
						JavaScriptclick(driver, SUP.SEND_INVITE);
					} else {
						click(SUP.SEND_INVITE);
					}
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(60, SUP.ReturntoSettings, driver);
					if (Browser.equalsIgnoreCase("IE")) {
						JavaScriptclick(driver, SUP.ReturntoSettings);
					} else {
						click(SUP.ReturntoSettings);
					}
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(3000);
					click(SUP.Pending);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(3000);
					click(SUP.QuickSearch);
					type(SUP.QuickSearch, PendfingFN);
					WebElement Pendinguser = driver.findElement(
							By.xpath("//table/tbody/tr/td/a[contains(.,'" + PendfingFN + " " + PendfingLN + "')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", Pendinguser);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(5000);
					assertTrue(SUP.ResendInvitationInfo.isDisplayed());
					click(SUP.RESENDINVITE);
					waitforText(120, SUP.Success, driver);
					click(SUP.RETURNTOUSERLISTING);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				}
			} else {
				waitforElement(180, SUP.CREATENEWUSER, driver);
				// Verify Create new user button functionality from View all users page
				click(SUP.VIEWALLUSERS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, SUP.FRANCHISECREATENEWUSER, driver);
				click(SUP.FRANCHISECREATENEWUSER);
				Thread.sleep(4000);
				assertTrue(SUP.FRANCHISEFIRSTNAME.isDisplayed());
				waitforElement(180, SUP.RETURNTOUSERS, driver);
				click(SUP.RETURNTOUSERS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, SUP.ReturntoSettings, driver);
				click(SUP.ReturntoSettings);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptclick(driver, SUP.CREATENEWUSER);
				} else {
					click(SUP.CREATENEWUSER);
				}
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, SUP.FRANCHISEFIRSTNAME, driver);
				type(SUP.FRANCHISEFIRSTNAME, firstname);
				type(SUP.LASTNAME1, lastname);
				type(SUP.EMAIL, Email);
				click(SUP.PHONE);
				type(SUP.PHONE, Phone);
				// sendkeysTab(SUP.PHONE);
				// CD-2797 Update Client User Add process to allow for assignment of role(s) per
				// product active in the user's subscription.
				if (VerifyObject(driver, SUP.ACCOUNTMANAGEMENT)) {
					JavaScriptclick(driver, SUP.ACCOUNTMANAGEMENT);
					int sl = SUP.accountotions.size();
					System.out.println(sl);
					if (sl > 2) {
						assertTrue(false);
					}
					selectByValue(SUP.ACCOUNTMANAGEMENT, "Admin");
					Thread.sleep(3000);
					if (VertexCloudIndirectTax.equalsIgnoreCase("Admin")) {
						selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "Admin");
					}
					if (VertexCloudIndirectTax.equalsIgnoreCase("Read Only")) {
						selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "Read Only");
					}
					if (VertexCloudIndirectTax.equalsIgnoreCase("ECM Only")) {
						selectByValue(SUP.VERTEXCLOUDINDIRECTTAX, "ECM Only");
					}

					if (ECManager.equalsIgnoreCase("Admin")) {
						selectByValue(SUP.ECMANAGER, "Admin");
					}
					if (ECManager.equalsIgnoreCase("User")) {
						selectByValue(SUP.ECMANAGER, "User");
					}

					if (GlobalCompliance.equalsIgnoreCase("Admin")) {
						selectByValue(SUP.GLOBALCOMPLIANCE, "Admin");
					}
					if (GlobalCompliance.equalsIgnoreCase("User")) {
						selectByValue(SUP.GLOBALCOMPLIANCE, "User");
					}
					if (GlobalCompliance.equalsIgnoreCase("Read Only")) {
						selectByValue(SUP.GLOBALCOMPLIANCE, "Read Only");
					}

					if (DataIntegrity.equalsIgnoreCase("Admin")) {
						selectByValue(SUP.DATAINTEGRITY, "Admin");
					}
					if (IndirectTaxIntelligence.equalsIgnoreCase("Admin")) {
						selectByValue(SUP.INDIRECTTAXINTELLIGENCE, "Admin");
					}
					if (IndirectTaxIntelligence.equalsIgnoreCase("User")) {
						selectByValue(SUP.INDIRECTTAXINTELLIGENCE, "User");
					}

				} else {
					String Role_type = UserRole;
					switch (Role_type) {
					case "Admin":
						click(SUP.ROLE1);
						break;
					case "Read Only":
						click(SUP.ROLE2);
						break;
					case "ECM Only":
						click(SUP.ROLE3);
						break;
					}
					click(SUP.FRANCHISEFIRSTNAME);
					sendkeysTab(SUP.FRANCHISEFIRSTNAME);
					Thread.sleep(2000);
				}
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptclick(driver, SUP.SENDINVITE);
				} else {
					click(SUP.SENDINVITE);
				}
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, SUP.CONFIRMREGLINK, driver);
				if (Browser.equalsIgnoreCase("IE")) {
					JavaScriptclick(driver, SUP.CONFIRMREGLINK);
				} else {
					click(SUP.CONFIRMREGLINK);
				}
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(3000);
				// System.out.println(SUP.VertexTitle.getAttribute("alt"));
				// assertTrue(SUP.VertexTitle.getAttribute("alt").equalsIgnoreCase("Vertex
				// Cloud"));
				waitforElement(180, SUP.FRANCHISEUSERNAME, driver);
				regcData = UserName.split(";");
				SMBCustomerUserName = xLabelRND(regcData);
				type(SUP.FRANCHISEUSERNAME, SMBCustomerUserName);
				type(SUP.password, ConfirmPassword);
				type(SUP.CONFIRMPASSWORDNEW, ConfirmPassword);
				/*
				 * if (CreateNewUser.getCell(Merchant_Customer,
				 * Data_Row).getContents().equalsIgnoreCase("Yes")||CreateNewUser.getCell(
				 * ClientType,
				 * Data_Row).getContents().equalsIgnoreCase("Merchant_TestInstance")) {
				 * Verification=""; Verification=(SUP.SERVICETYPE.isDisplayed())==false?
				 * Verification: Verification+"Fail; Service type check box displayed;";
				 * status=status+Verification; VertexSMB.RowNo =
				 * bussFunction.xResult(Verification,RSheet,"Service type check box displayed"
				 * ,"",value,NoOfVerify++); }else{ if(SUP.SERVICETYPE.isDisplayed()){
				 * click(SUP.SERVICETYPE); } }
				 */
				click(SUP.COMPLETE);
				Thread.sleep(4000);
				if (VerifyObject(driver, PUL.LOGINLINK)) {
					click(PUL.LOGINLINK);
					waitforElement(180, LP.DASHBOARD, driver);
				}

			}
			if (RTERonly.equalsIgnoreCase("Yes")) {
				Logout();
				Thread.sleep(5000);
				if (Merchant_Customer.equalsIgnoreCase("Franchise")) {
					Thread.sleep(3000);
					driver.get(url);
					System.out.println(url);
					Thread.sleep(3000);
					type(LP.USERNAME, FranchiseUser);
				} else {
					String URL = url;
					driver.get(URL);
					Thread.sleep(3000);
					type(LP.USERNAME, SMBCustomerUserName);
				}
				type(LP.PASSWORD, ConfirmPassword);
				click(LP.LOGIN);
				waitforElement(120, NU.SETTINGS, driver);
				JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
				JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				assertTrue(RTE.CREATEEXTRACT.isDisplayed());
			}
			if (!UserRole.equalsIgnoreCase("ECM Only")) {
				JavaScriptclick(driver, NU.SETTINGS);
				Thread.sleep(2000);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(3000);
			}
			if (AfterLastLogin.equalsIgnoreCase("Yes")) {
				Logout();
				Thread.sleep(5000);
				if (Merchant_Customer.equalsIgnoreCase("Franchise")) {
					Thread.sleep(3000);
					driver.get(franchiseurl);
					System.out.println(franchiseurl);
					Thread.sleep(3000);
					type(LP.USERNAME, FranchiseUser);
					Thread.sleep(3000);
					type(LP.PASSWORD, ConfirmPassword);
					click(LP.LOGIN);
					waitforElement(90, NU.SETTINGS, driver);
					JavaScriptclick(driver, NU.SETTINGS);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				} else {
					String URL = url;
					driver.get(URL);
					Thread.sleep(3000);
					type(LP.USERNAME, SMBCustomerUserName);
					type(LP.PASSWORD, ConfirmPassword);
					click(LP.LOGIN);
					waitforElement(120, NU.SETTINGS, driver);
					JavaScriptclick(driver, NU.SETTINGS);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(3000);
					JavaScriptclick(driver, NU.VIEWALLUSERS);
					waitUntilElementInvisibe(90, CNP.NPROGRESSBUSY_XPATH, driver);
					if (SMBCustomerUserName.contains(".TI")) {
						assertTrue(SUP.DESIGNATEDBILLINGCONTACT.isDisplayed());
						assertTrue(SUP.DESIGNATEDECMCONTACT.isDisplayed());
					}
				}
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				// get current date time with Calendar()
				Calendar cal = Calendar.getInstance();
				SystemDateTime = dateFormat.format(cal.getTime());
				System.out.println("SystemDateTime=" + SystemDateTime);
				if (Merchant_Customer.equalsIgnoreCase("Franchise")) {
					waitforElement(120, SUP.ActiveUsers, driver);
					click(SUP.ActiveUsers);
					type(SUP.QuickSearch, FranchiseUser);
					Thread.sleep(5000);
				}
			}
		}
	}

	public void VerifyNoITIUserStandard() throws Exception {
		Cpa_ManageUsers NU = PageFactory.initElements(driver, Cpa_ManageUsers.class);
		SmbCustomerUserPage SUP = PageFactory.initElements(driver, SmbCustomerUserPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		new Robot();
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, NU.SETTINGS);
		} else {
			click(NU.SETTINGS);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SUP.CREATENEWUSER, driver);
		click(SUP.CREATENEWUSER);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SUP.ACCOUNTMANAGEMENT, driver);
		if (!VerifyObject(driver, SUP.INDIRECTTAXINTELLIGENCE)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void LoginLocked(String username, String password) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		JavaScripttype(driver, LP.USERNAME, username);
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(1000);
		for (int i = 1; i < 3; i++) {
			Thread.sleep(1000);
			type(LP.PASSWORD, "Wrongpwd!");
			Thread.sleep(2000);
			JavaScriptclick(driver, loginPage.CONTINUE);
			Thread.sleep(3000);
			waitforElement(180, LP.LOGINLOCKERROR, driver);
			clear(LP.PASSWORD);
		}
		// Login With Correct Password for 3rd time
		Thread.sleep(3000);
		driver.get(url);
		waitforElement(180, LP.LOGIN, driver);
		clear(LP.USERNAME);
		type(LP.USERNAME, username);
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(2000);
		type(LP.PASSWORD, password);
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		waitforElement(60, LP.DASHBOARD, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.manage().deleteAllCookies();
			driver.get(url);
			waitforElement(120, LP.VTMSACCOUNT, driver);
		}
		if (VerifyObject(driver, LP.DASHBOARD)) {
			assertTrue(LP.DASHBOARD.isDisplayed());
		} else {
			assertTrue(LP.SETTINGS.isDisplayed());
		}
		userregistration.Logout();
		driver.get(url);
		waitforElement(180, LP.LOGIN, driver);
		clear(LP.USERNAME);
		type(LP.USERNAME, username);
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(1000);
		for (int i = 1; i <= 3; i++) {
			Thread.sleep(1000);
			type(LP.PASSWORD, "Wrongpwd!");
			Thread.sleep(2000);
			JavaScriptclick(driver, loginPage.CONTINUE);
			Thread.sleep(3000);
			if (i == 3) {
				waitforElement(180, LP.LOGINLOCKERROR, driver);
			}
			clear(LP.PASSWORD);
		}
		LockedUsername = username;
		// Verifying Lockout Message and Forget Password page
		assertTrue(LP.LOGINLOCKERROR.isDisplayed());
		if (username.contains("LoginLocked")) {
			waitforElement(180, LP.FORGETPWD, driver);
			JavaScriptclick(driver, LP.FORGETPWD);
			Thread.sleep(5000);
			JavaScriptclick(driver, loginPage.CONTINUE);
			waitforElement(180, LP.SUCCESSMSG, driver);
			Thread.sleep(5000);
			assertTrue(LP.SUCCESSMSG.isDisplayed());

		}
	}

	public void EditUserSettings(String Email_Edit, String Role, String FirstName, String LastName, String Phone,
			String Password) throws Exception {
		new Robot();
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		EditUserSettings EUS = PageFactory.initElements(driver, EditUserSettings.class);
		Thread.sleep(5000);
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		Thread.sleep(5000);
		JavaScriptclick(driver, EUS.ACCOUNT);
		Thread.sleep(2000);
		JavaScriptclick(driver, EUS.USERSETTINGS);
		Thread.sleep(5000);
		assertTrue(EUS.SUBMIT.isDisplayed());
		if (Email_Edit != "") {
			String strRole = Role;
			String[] DataValue = Email_Edit.split(";");
			clear(EUS.EMAILADDRESS);
			switch (strRole.toUpperCase()) {
			case "SMB CUSTOMER ADMIN":
				VertexProEmail = xLabelRND(DataValue);
				type(EUS.EMAILADDRESS, VertexProEmail);
				VertexEmail = VertexProEmail;
				break;
			case "VAR ADMIN":
				VertexVarEmail = xLabelRND(DataValue);
				type(EUS.EMAILADDRESS, VertexVarEmail);
				VertexEmail = VertexVarEmail;
				break;
			case "TREASURY ADMIN":
				VertexTreasEmail = xLabelRND(DataValue);
				type(EUS.EMAILADDRESS, VertexTreasEmail);
				VertexEmail = VertexTreasEmail;
				break;
			case "BILLING APPROVAL ADMIN":
				VertexAppEmail = xLabelRND(DataValue);
				type(EUS.EMAILADDRESS, VertexAppEmail);
				VertexEmail = VertexAppEmail;
				break;
			case "VAR FINANCIAL":
				VertexFinanceEmail = xLabelRND(DataValue);
				type(EUS.EMAILADDRESS, VertexFinanceEmail);
				VertexEmail = VertexFinanceEmail;
				break;
			case "VAR STAFF":
				VertexStaffEmail = xLabelRND(DataValue);
				type(EUS.EMAILADDRESS, VertexStaffEmail);
				VertexEmail = VertexStaffEmail;
				break;
			}
			sendkeysTab(EUS.EMAILADDRESS);
		}
		if (FirstName != "") {
			clear(EUS.FIRSTNAME);
			type(EUS.FIRSTNAME, FirstName);
		}
		if (LastName != "") {
			clear(EUS.LASTNAME);
			type(EUS.LASTNAME, LastName);
			sendkeysTab(EUS.LASTNAME);
		}
		Thread.sleep(2000);
		assertTrue(EUS.USERNAME.isDisplayed());
		click(EUS.PHONENUMBER);
		type(EUS.PHONENUMBER, Keys.CONTROL + "a");
		clear(EUS.PHONENUMBER);
		Thread.sleep(2000);
		click(EUS.PHONENUMBER);
		type(EUS.PHONENUMBER, Keys.CONTROL + "a");
		type(EUS.PHONENUMBER, Phone);
		sendkeysTab(EUS.PHONENUMBER);
		click(EUS.FIRSTNAME);
		Thread.sleep(2000);
		click(EUS.SUBMIT);
		Thread.sleep(5000);
		if (VerifyObject(driver, EUS.ErrorPhone)) {
			boolean report = false;
			do {
				click(EUS.PHONENUMBER);
				EUS.PHONENUMBER.sendKeys(Keys.CONTROL + "a");
				EUS.PHONENUMBER.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				EUS.PHONENUMBER.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				if (!VerifyObject(driver, EUS.ErrorPhone)) {
					click(EUS.SUBMIT);
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180, PUL.USERPASSWORDCONFIRM, driver);
		if (VerifyObject(driver, PUL.USERPASSWORDCONFIRM)) {
			click(PUL.PASSWORD);
			type(PUL.PASSWORD, Password);
		}
		Thread.sleep(5000);
		if (VerifyObject(driver, PUL.SAVECHANGES)) {
			click(PUL.SAVECHANGES);
			Thread.sleep(5000);
		}
		// assertTrue(EUS.SUCCESS.isDisplayed());

		Thread.sleep(2000);
		if (VerifyObject(driver, EUS.GOTODASHBOARD)) {
			click(EUS.GOTODASHBOARD);
			Thread.sleep(5000);
		}

	}

	public void EditCompanyUpdation(String User_Country, String Invalid_ClientName, String Name, String Phone,
			String Address1, String Address2, String City, String State, String Zip) throws Exception {
		new Robot();
		VarAdminScreens VSS = PageFactory.initElements(driver, VarAdminScreens.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(120, VSS.SETTINGSLINK, driver);
		click(VSS.SETTINGSLINK);
		waitforElement(180, VSS.EDITMYCOMPNAYLINK, driver);
		click(VSS.EDITMYCOMPNAYLINK);
		waitforElement(180, VSS.NAME, driver);
		selectByValue(VSS.CountryDropDown, User_Country);
		Thread.sleep(2000);
		if (User_Country.equalsIgnoreCase("United States") || User_Country.equalsIgnoreCase("Canada")) {
			WebElement State_EditCompany = VSS.STATE;
			List<WebElement> State_Values = State_EditCompany.findElements(By.xpath("//option"));
			for (int DD = 0; DD < State_Values.size(); DD++) {
				State_EditCompany.sendKeys(State_Values.get(DD).getText());
			}
			assertTrue(VSS.STATE.isDisplayed());
		} else {
			assertTrue(VSS.ForeignStateName.isDisplayed());
		}
		assertTrue(VSS.RETURNTOSETTINGS.isDisplayed());
		click(VSS.CANCEL);
		waitforElement(180, VSS.SETTINGSLINK, driver);
		click(VSS.SETTINGSLINK);
		waitforElement(180, VSS.EDITMYCOMPNAYLINK, driver);
		click(VSS.EDITMYCOMPNAYLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSS.NAME, driver);
		/*
		 * clear(VSS.NAME); if (Invalid_ClientName.equalsIgnoreCase("Yes")) {
		 * type(VSS.NAME, InvalidClinetName);
		 * assertTrue(VSS.InvaidClientName.isDisplayed()); clear(VSS.NAME); }
		 * type(VSS.NAME, Name);
		 */
		selectByValue(VSS.CountryDropDown, User_Country);
		if (User_Country.equalsIgnoreCase("United States") || User_Country.equalsIgnoreCase("Canada")) {
			clear(VSS.COMPANYPHONE);
			type(VSS.COMPANYPHONE, Keys.CONTROL + "a");
			(VSS.COMPANYPHONE).sendKeys(Keys.DELETE);
			sendkeysTab(VSS.COMPANYPHONE);
			click(VSS.COMPANYPHONE);
			Thread.sleep(2000);
			type(VSS.COMPANYPHONE, Keys.CONTROL + "a");
			type(VSS.COMPANYPHONE, Phone);
			sendkeysEnter(VSS.COMPANYPHONE);
		} else {
			clear(VSS.ForiegnPhone);
			JavaScripttype(driver, VSS.ForiegnPhone, Phone);
			Thread.sleep(1000);
			VSS.ForiegnPhone.sendKeys(Keys.CONTROL + "a");
			VSS.ForiegnPhone.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			VSS.ForiegnPhone.sendKeys(Keys.CONTROL + "v");
			VSS.ForiegnPhone.sendKeys(Keys.TAB);
		}
		Thread.sleep(2000);
		clear(VSS.ADDRESS1);
		type(VSS.ADDRESS1, Address1);
		clear(VSS.ADDRESS2);
		if (Address2 != "") {
			type(VSS.ADDRESS2, Address2);
		}
		clear(VSS.CITY);
		type(VSS.CITY, City);
		if (User_Country.equalsIgnoreCase("United States") || User_Country.equalsIgnoreCase("Canada")) {
			selectByValue(VSS.STATE, State);
		} else {
			clear(VSS.ForeignStateName);
			Thread.sleep(1000);
			type(VSS.ForeignStateName, State);
		}
		JavaScripttype(driver, VSS.ZIP_CODE, Zip);
		Thread.sleep(3000);
		sendkeysTab(VSS.ZIP_CODE);
		Thread.sleep(2000);
		// click(VSS.NAME);
		waitforElement(120, VSS.SAVE, driver);
		click(VSS.SAVE);
		if (VerifyObject(driver, VSS.ZIPFIELDISREQUIRED)) {
			Thread.sleep(2000);
			clear(VSS.ZIP_CODE);
			Thread.sleep(2000);
			JavaScripttype(driver, VSS.ZIP_CODE, Zip);
			sendkeysTab(VSS.ZIP_CODE);
			Thread.sleep(2000);
			if (VerifyObject(driver, VSS.ERRORZIPCODE)) {
				VSS.ZIP_CODE.sendKeys(Keys.CONTROL + "a");
				VSS.ZIP_CODE.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				VSS.ZIP_CODE.sendKeys(Keys.CONTROL + "v");
				VSS.ZIP_CODE.sendKeys(Keys.TAB);
				Thread.sleep(1000);
			}
			click(VSS.SAVE);
		}
		Thread.sleep(5000);
		JavaScriptclick(driver, VSS.SETTINGSLINK);
		waitforElement(180, VSS.EDITMYCOMPNAYLINK, driver);
		click(VSS.EDITMYCOMPNAYLINK);
		waitforElement(180, VSS.NAME, driver);
		/*
		 * clear(VSS.NAME); type(VSS.NAME, Name);
		 */
		selectByValue(VSS.CountryDropDown, User_Country);
		if (User_Country.equalsIgnoreCase("United States") || User_Country.equalsIgnoreCase("Canada")) {
			clear(VSS.COMPANYPHONE);
			JavaScripttype(driver, VSS.COMPANYPHONE, Phone);
			Thread.sleep(1000);
			VSS.COMPANYPHONE.sendKeys(Keys.CONTROL + "a");
			VSS.COMPANYPHONE.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			VSS.COMPANYPHONE.sendKeys(Keys.CONTROL + "v");
			VSS.COMPANYPHONE.sendKeys(Keys.TAB);
		} else {
			clear(VSS.ForiegnPhone);
			JavaScripttype(driver, VSS.ForiegnPhone, Phone);
			Thread.sleep(1000);
			VSS.ForiegnPhone.sendKeys(Keys.CONTROL + "a");
			VSS.ForiegnPhone.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			VSS.ForiegnPhone.sendKeys(Keys.CONTROL + "v");
			VSS.ForiegnPhone.sendKeys(Keys.TAB);
		}
		Thread.sleep(2000);
		clear(VSS.ADDRESS1);
		type(VSS.ADDRESS1, Address1);
		if (Address2 != "") {
			clear(VSS.ADDRESS2);
			type(VSS.ADDRESS2, Address2);
		}
		clear(VSS.CITY);
		type(VSS.CITY, City);
		if (User_Country.equalsIgnoreCase("United States") || User_Country.equalsIgnoreCase("Canada")) {
			selectByValue(VSS.STATE, State);
		} else {
			clear(VSS.ForeignStateName);
			type(VSS.ForeignStateName, State);
		}
		JavaScripttype(driver, VSS.ZIP_CODE, Zip);
		Thread.sleep(1000);
		VSS.ZIP_CODE.sendKeys(Keys.CONTROL + "a");
		VSS.ZIP_CODE.sendKeys(Keys.CONTROL + "x");
		Thread.sleep(2000);
		VSS.ZIP_CODE.sendKeys(Keys.CONTROL + "v");
		VSS.ZIP_CODE.sendKeys(Keys.TAB);
		// click(VSS.NAME);
		Thread.sleep(2000);
		waitforElement(180, VSS.SAVE, driver);
		click(VSS.SAVE);
		if (VerifyObject(driver, VSS.ZIPFIELDISREQUIRED)) {
			Thread.sleep(2000);
			VSS.ZIP_CODE.sendKeys(Keys.CONTROL + "a");
			VSS.ZIP_CODE.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			VSS.ZIP_CODE.sendKeys(Keys.CONTROL + "v");
			VSS.ZIP_CODE.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			click(VSS.SAVE);
		}
		waitforElement(180, VSS.EDITCOMPANYUPDATEDMESSAGE, driver);
		assertTrue(VSS.EDITCOMPANYUPDATEDMESSAGE.isDisplayed());
	}

	public void AddDescription(String Description) throws Exception {
		new Robot();
		VarAdminScreens VSS = PageFactory.initElements(driver, VarAdminScreens.class);

		PageFactory.initElements(driver, RegistrationPage.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(120, VSS.SETTINGSLINK, driver);
		click(VSS.SETTINGSLINK);
		waitforElement(180, VSS.EDITMYCOMPNAYLINK, driver);
		click(VSS.EDITMYCOMPNAYLINK);
		Thread.sleep(2000);
		waitforElement(180, VSS.LabelDESCRIPTION, driver);
		waitforElement(180, VSS.DESCRIPTIONTEXTBOX, driver);
		clear(VSS.DESCRIPTIONTEXTBOX);
		type(VSS.DESCRIPTIONTEXTBOX, Description);
		Thread.sleep(2000);
		click(VSS.SAVE);
		waitforElement(120, VSS.SETTINGSLINK, driver);
		click(VSS.SETTINGSLINK);
		waitforElement(180, VSS.EDITMYCOMPNAYLINK, driver);
		String DESCRIPTIONText = VSS.DESCRIPTIONTEXT.getText();
		if (DESCRIPTIONText.equalsIgnoreCase(Description)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		waitforElement(120, VSS.DASHBOARD, driver);
		click(VSS.DASHBOARD);
		waitforElement(120, VSS.Available_Clients, driver);
		click(VSS.Available_Clients);
		String AvailableClients[] = VSS.Available_Clients.getText().split("\\(|\\)");
		for (int i = 0; i < AvailableClients.length; i++) {
			for (int j = i; j <= i; j++) {
				if (AvailableClients[i].equalsIgnoreCase(Description)) {
					assertTrue(true);
					System.out.println("client description available in client dropdown list : " + AvailableClients[i]);
				}
			}

		}

	}

	public void VerifyDescription() throws Exception {
		new Robot();
		VarAdminScreens VSS = PageFactory.initElements(driver, VarAdminScreens.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(120, VSS.SETTINGSLINK, driver);
		click(VSS.SETTINGSLINK);
		waitforElement(180, VSS.DESCRIPTION, driver);
		if (VerifyObject(driver, VSS.DESCRIPTION)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	public void VerifyNoDescription() throws Exception {
		new Robot();
		VarAdminScreens VSS = PageFactory.initElements(driver, VarAdminScreens.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(120, VSS.SETTINGSLINK, driver);
		click(VSS.SETTINGSLINK);
		waitforElement(180, VSS.Company_Information, driver);
		if (!VerifyObject(driver, VSS.DESCRIPTION)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	public void ChangePassword(String currentPassword, String newPassword, String confirmPassword) throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		new Robot();
		ChangePassword CP = PageFactory.initElements(driver, ChangePassword.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		EditUserSettings EUS = PageFactory.initElements(driver, EditUserSettings.class);
		DashBoardLebel = "";
		waitforElement(180, CP.DASHBOARDLINK, driver);
		JavaScriptclick(driver, CP.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, CP.ACCOUNTOPEN, driver);
		JavaScriptclick(driver, CP.ACCOUNTOPEN);
		waitforElement(180, CP.CHANGEPASSWORDLINK, driver);
		click(CP.CHANGEPASSWORDLINK);
		waitforElement(90, CP.PWDSTRENGTH, driver);
		waitforElement(180, CP.CURRENTPASSWORD, driver);
		assertTrue(CP.PWDSTRENGTH.isDisplayed());
		type(CP.CURRENTPASSWORD, currentPassword);
		type(CP.NEWPASSWORD, newPassword);
		type(CP.CONFIRMPASSWORD, confirmPassword);
		click(CP.CURRENTPASSWORD);
		waitforElement(180, CP.CHANGEPASSWORDBUTTON, driver);
		click(CP.CHANGEPASSWORDBUTTON);
		waitforElement(180, CP.SUCCESMESSAGE, driver);
		assertTrue(CP.SUCCESMESSAGE.isDisplayed());
		Thread.sleep(500);
		if (VerifyObject(driver, EUS.GOTODASHBOARD)) {
			click(EUS.GOTODASHBOARD);
			Thread.sleep(5000);
		}
	}

	public void VerifyAccountTypeofVertexCloudVars(String Role, String Status, String Subscription_Type,
			String RateFile, String Additional_Features, String TaxabilityImpact_Report, String TestInstance,
			String ValueAddReseller) throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		VertexCloudVars VCV = PageFactory.initElements(driver, VertexCloudVars.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		click(LP.VTMSACCOUNT);
		waitforElement(180, LP.USERSETTINGS, driver);
		click(LP.USERSETTINGS);
		waitforElement(180, LP.FIRSTNAME, driver);
		UserN = getAttribute(LP.EmailAddress, "value");
		JavaScriptclick(driver, LP.CANCELUSERSETTINGS);
		Thread.sleep(5000);
		JavaScriptclick(driver, VCV.vertexCloudvarsMenu);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (UserN.equalsIgnoreCase("vertuser2@vertex.local") || UserN.equalsIgnoreCase("SystemROnlyAutomation")) {
			assertTrue(VCV.VertexCloudVARSColumns.isDisplayed());
		} else {
			assertTrue(VCV.VertexCloudVARSColumns1.isDisplayed());
		}
		if (Role.equalsIgnoreCase("Franchise")) {
			waitforElement(180, VCV.searchBox, driver);
			type(VCV.searchBox, VertexClientName);
			Thread.sleep(5000);
			WebElement Expand = driver.findElement(By.xpath(
					"//table/tbody/tr/td[contains(.,'" + VertexClientName + "')]/../td/a[contains(.,'Expand')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Expand);
			assertTrue(VCV.VertexCloudVARSColumnsAfterExpand.isDisplayed());
			String Status1 = VCV.STATUS.getText();
			assertTrue(Status1.equalsIgnoreCase(Status));
			waitforElement(60, VCV.AdminDetailsLink, driver);
			click(VCV.AdminDetailsLink);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			// Verify admin details and Subscription details
			assertTrue(VCV.Admindetails.isDisplayed());
			assertTrue(VCV.ClientName.isDisplayed());
			assertTrue(VCV.ClientArtifactRepository.isDisplayed());
			assertTrue(VCV.LastLogIn.isDisplayed());
			assertTrue(VCV.MerchantSubscription.isDisplayed());
			List<WebElement> Subtypes = driver.findElements(By.xpath("//*[@id='createForm']/div/select/option"));
			for (int j = 0; j < Subtypes.size(); j++) {
				assertTrue(Subtypes.get(0).getText().equalsIgnoreCase(Subscription_Type));
			}
			if (RateFile.equalsIgnoreCase("Yes")) {
				assertTrue(VCV.RateFile.isSelected());
			}
			if (Additional_Features.equalsIgnoreCase("RealTimeRateService")) {
				assertTrue(VCV.RTRS.isSelected());
			}
			if (TaxabilityImpact_Report.equalsIgnoreCase("Yes")) {
				assertTrue(VCV.TaxabilityImpactReport.isSelected());
			}
			if (TestInstance.equalsIgnoreCase("Yes")) {
				assertTrue(VCV.TestInstance.isSelected());
			}
			waitforElement(60, VCV.RETURNTOVARLISTING, driver);
			click(VCV.RETURNTOVARLISTING);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, VCV.searchBox, driver);
		} else if ((ValueAddReseller.equalsIgnoreCase("CPARefAutomationFirm"))
				|| (ValueAddReseller.equalsIgnoreCase("CPABPOAutomationFirm"))) {
			if (ValueAddReseller.equalsIgnoreCase("CPARefAutomationFirm")) {
				waitforElement(180, VCV.searchBox, driver);
				type(VCV.searchBox, "CPARefAutomationFirm");
			}
			if (ValueAddReseller.equalsIgnoreCase("CPABPOAutomationFirm")) {
				waitforElement(180, VCV.searchBox, driver);
				type(VCV.searchBox, "CPABPOAutomationFirm");
			}
			Thread.sleep(5000);
			waitforElement(180, VCV.EXPAND, driver);
			click(VCV.EXPAND);
			assertTrue(VCV.VertexCloudVARSColumnsAfterExpand.isDisplayed());
			Thread.sleep(5000);
			click(VCV.AdminDetailsLink);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(VCV.Admindetails.isDisplayed());
			assertTrue(VCV.ClientName.isDisplayed());
			assertTrue(VCV.ClientArtifactRepository.isDisplayed());
			assertTrue(VCV.LastLogIn.isDisplayed());
			waitforElement(60, VCV.RETURNTOVARLISTING, driver);
			click(VCV.RETURNTOVARLISTING);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, VCV.searchBox, driver);
		} else if (ValueAddReseller.equalsIgnoreCase(VARName)) {
			waitforElement(180, VCV.searchBox, driver);
			type(VCV.searchBox, VARName);
			waitforElement(180, VCV.AdminDetailsLink, driver);
			click(VCV.AdminDetailsLink);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, VCV.RETURNTOVARLISTING, driver);
			click(VCV.RETURNTOVARLISTING);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, VCV.searchBox, driver);
			Thread.sleep(5000);
			waitforElement(180, VCV.EXPAND, driver);
			click(VCV.EXPAND);
			assertTrue(VCV.VertexCloudVARSColumnsAfterExpand.isDisplayed());
			Thread.sleep(5000);
			click(VCV.AdminDetailsLink);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			assertTrue(VCV.Admindetails.isDisplayed());
			assertTrue(VCV.ClientName.isDisplayed());
			assertTrue(VCV.ClientArtifactRepository.isDisplayed());
			assertTrue(VCV.LastLogIn.isDisplayed());
			waitforElement(60, VCV.RETURNTOVARLISTING, driver);
			click(VCV.RETURNTOVARLISTING);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, VCV.searchBox, driver);
		} else {
			waitforElement(180, VCV.searchBox, driver);
			type(VCV.searchBox, "Franchise1_VAR");
			Thread.sleep(5000);
			assertTrue(driver
					.findElement(By.xpath(
							"//table/tbody/tr[2]/td[contains(.,'Franchise1_VAR')]/../td[contains(.,'Franchise')]"))
					.isDisplayed());
		}
	}

	public void UpdateBilling(String Billing, String Invoice_Customer, String User_Country, String StreetAddress1,
			String StreetAddress2, String City_Municipality, String State_Province_Territory, String Zip_PostalCode,
			String PaymentType, String CreditCardNumber, String ExpirationMonth, String ExpirationYear, String CSV,
			String BankAccountNumber, String RoutingNumber) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		PageFactory.initElements(driver, UpgradeProToPremium.class);
		PageFactory.initElements(driver, DowngradePremiumtoPro.class);
		PageFactory.initElements(driver, LoginPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, DashboardPage.class);
		PageFactory.initElements(driver, TestInstanceSandbox.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		String[] NonInvoiceBillingAddress = { "United States", "Canada" };
		new Robot();
		// Changing Billing Details
		waitforElement(180, PUL.SettingsHover, driver);
		if (Billing.equalsIgnoreCase("Yes")) {
			JavaScriptclick(driver, PUL.SettingsHover);
			waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(60, PUL.MyBillingSubscriptionsection, driver);

			if (Invoice_Customer.equalsIgnoreCase("Yes")) {
				if (VerifyObject(driver, PUL.CardDetails)) {
					assertTrue(true);
				}
			} else {
				assertTrue(PUL.CardDetails.isDisplayed());
			}

			JavaScriptMousehover(driver, PUL.SettingsHover);
			JavaScriptclick(driver, PUL.UPDATEBILLINGLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, MSP.BILLTOSAVEBUTTON, driver);
			waitforElement(180, MSP.COUNTRY, driver);
			// waitforElement(180, MSP.CREDITCARDNUMBER, driver);
			if (User_Country != "") {
				selectByValue(MSP.COUNTRY, User_Country);
				Thread.sleep(2000);
				if (Invoice_Customer.equalsIgnoreCase("Yes")) {
					int countriessize = RP.NonInvoiceBillingCountries.size();
					String nonInvoiceCountries[] = StoreCountriesinArray();
					for (int countries = 0; countries < countriessize; countries++) {
						System.out.println(RP.NonInvoiceBillingCountries.get(countries).getText());
						System.out.println(nonInvoiceCountries[countries]);
						assertTrue(RP.NonInvoiceBillingCountries.get(countries).getText()
								.equals(nonInvoiceCountries[countries]));
					}
				} else {
					int countriessize = RP.NonInvoiceBillingCountries.size();
					for (int countries = 0; countries < countriessize; countries++) {
						assertTrue(RP.NonInvoiceBillingCountries.get(countries).getText()
								.equals(NonInvoiceBillingAddress[countries]));
					}
				}

			}
			clear(MSP.ADDRESSLINE1);
			click(MSP.ADDRESSLINE1);
			JavaScripttype(driver, MSP.ADDRESSLINE1, StreetAddress1);
			Thread.sleep(1000);
			if (StreetAddress2 != "") {
				clear(MSP.ADDRESSLINE2);
				click(MSP.ADDRESSLINE2);
				type(MSP.ADDRESSLINE2, StreetAddress2);
			}
			clear(MSP.CITY);
			click(MSP.CITY);
			JavaScripttype(driver, MSP.CITY, City_Municipality);
			Thread.sleep(2000);
			if (User_Country.equalsIgnoreCase("United States") || User_Country.equalsIgnoreCase("CANADA")) {
				selectByValue(MSP.BILLTOSTATE, State_Province_Territory);
			} else {
				clear(MSP.BILLTOSTATE);
				type(MSP.BILLTOSTATE, State_Province_Territory);
			}

			Thread.sleep(2000);
			click(MSP.BILLTOZIP);
			// type(MSP.BILLTOZIP, Keys.CONTROL + "a");
			JavaScripttype(driver, MSP.BILLTOZIP, Zip_PostalCode);
			Thread.sleep(1000);
			sendkeysTab(MSP.BILLTOZIP);
			JavaScriptclick(driver, MSP.CITY);
			Thread.sleep(2000);
			if (!Invoice_Customer.equalsIgnoreCase("Yes")) {
				if (PaymentType != "") {
					if (PaymentType.equalsIgnoreCase("CC")) {
						click(MSP.CREDITCARDNUMBER);
						WebElement CCD1 = MSP.CREDITCARDNUMBER;
						String CCND1 = CreditCardNumber;
						if (Browser.equalsIgnoreCase("Firefox")) {
							JavascriptExecutor myExecutor1 = ((JavascriptExecutor) driver);
							myExecutor1.executeScript("arguments[0].value='" + CCND1 + " " + "';", CCD1);
							Thread.sleep(2000);
							sendkeybackSpace(MSP.CREDITCARDNUMBER);
						} else {
							type(MSP.CREDITCARDNUMBER, CreditCardNumber);
						}
						selectByValue(MSP.EXPIRATIONMONTH, ExpirationMonth);
						selectByValue(MSP.EXPIRATIONYEAR, ExpirationYear);
						clear(MSP.SECURITYCODE);
						click(MSP.SECURITYCODE);
						type(MSP.SECURITYCODE, CSV);
					}
					if (PaymentType.equalsIgnoreCase("ACH")) {
						click(RP.ACH);
						waitforElement(180, RP.BANKNUMBER, driver);
						clear(RP.BANKNUMBER);
						type(RP.BANKNUMBER, BankAccountNumber);
						clear(RP.ROUTINGNUMBER);
						type(RP.ROUTINGNUMBER, RoutingNumber);
					}
				}
			}
			JavaScriptclick(driver, MSP.BILLTOSAVEBUTTON);
			if (VerifyObject(driver, MSP.ADDRESSLINE1ERROR)) {
				type(MSP.ADDRESSLINE1, Keys.CONTROL + "a");
				type(MSP.ADDRESSLINE1, Keys.CONTROL + "x");
				Thread.sleep(1000);
				type(MSP.ADDRESSLINE1, Keys.CONTROL + "v");
				sendkeysTab(MSP.ADDRESSLINE1);
			}
			if (VerifyObject(driver, MSP.CITYERROR)) {
				type(MSP.CITY, Keys.CONTROL + "a");
				type(MSP.CITY, Keys.CONTROL + "x");
				Thread.sleep(1000);
				type(MSP.CITY, Keys.CONTROL + "v");
				sendkeysTab(MSP.CITY);
			}
			if (VerifyObject(driver, MSP.CVNError)) {
				type(MSP.SECURITYCODE, Keys.CONTROL + "a");
				type(MSP.SECURITYCODE, Keys.CONTROL + "x");
				Thread.sleep(1000);
				type(MSP.SECURITYCODE, Keys.CONTROL + "v");
				sendkeysTab(MSP.SECURITYCODE);
			}
			JavaScriptclick(driver, MSP.BILLTOSAVEBUTTON);
			waitforElement(180, MSP.SUCCESS, driver);
			assertTrue(MSP.SUCCESS.isDisplayed());
		}
	}

	public void PortalCustomerCreateNewUser(String FirstName, String LastName, String Email, String Phone,
			String UserRole, String UserName, String Password, String ConfirmPassword, String VertexCloudIndirectTax,
			String ECManager, String GlobalCompliance, String DataIntegrity, String Verify_SSCustomerUser)
			throws Exception {
		CustomerPortalAddNewUser SUP = PageFactory.initElements(driver, CustomerPortalAddNewUser.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		SmbCustomerUserPage SCP = PageFactory.initElements(driver, SmbCustomerUserPage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		String[] regcData;
		String SMBCustomerUserName = null;
		JavaScriptclick(driver, SUP.ClickSettings);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SUP.AddNewUser, driver);
		JavaScriptclick(driver, SUP.AddNewUser);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SUP.Firstname, driver);
		click(SUP.Firstname);
		type(SUP.Firstname, FirstName);
		click(SCP.LASTNAME1);
		type(SCP.LASTNAME1, LastName);
		click(SCP.EMAIL);
		type(SCP.EMAIL, Email);
		click(SCP.PHONE);
		type(SCP.PHONE, Phone);
		if (VerifyObject(driver, SCP.ACCOUNTMANAGEMENT)) {
			JavaScriptclick(driver, SCP.ACCOUNTMANAGEMENT);
			selectByValue(SCP.ACCOUNTMANAGEMENT, "Admin");
			Thread.sleep(3000);
			if (VertexCloudIndirectTax.equalsIgnoreCase("Admin")) {
				selectByValue(SCP.VERTEXCLOUDINDIRECTTAX, "Admin");
			}
			if (VertexCloudIndirectTax.equalsIgnoreCase("Read Only")) {
				selectByValue(SCP.VERTEXCLOUDINDIRECTTAX, "Read Only");
			}
			if (VertexCloudIndirectTax.equalsIgnoreCase("ECM Only")) {
				selectByValue(SCP.VERTEXCLOUDINDIRECTTAX, "ECM Only");
			}

			if (ECManager.equalsIgnoreCase("Admin")) {
				selectByValue(SCP.ECMANAGER, "Admin");
			}
			if (ECManager.equalsIgnoreCase("User")) {
				selectByValue(SCP.ECMANAGER, "User");
			}

			if (GlobalCompliance.equalsIgnoreCase("Admin")) {
				selectByValue(SCP.GLOBALCOMPLIANCE, "Admin");
			}
			if (GlobalCompliance.equalsIgnoreCase("User")) {
				selectByValue(SCP.GLOBALCOMPLIANCE, "User");
			}
			if (GlobalCompliance.equalsIgnoreCase("Read Only")) {
				selectByValue(SCP.GLOBALCOMPLIANCE, "Read Only");
			}

			if (DataIntegrity.equalsIgnoreCase("Admin")) {
				selectByValue(SCP.DATAINTEGRITY, "Admin");
			}

		} else {
			String Role_type = UserRole;
			switch (Role_type) {
			case "Admin":
				click(SCP.ROLE1);
				break;
			case "Read Only":
				click(SCP.ROLE2);
				break;
			case "ECM Only":
				click(SCP.ROLE3);
				break;
			}
		}
		Thread.sleep(1000);
		waitforElement(180, SCP.SENDINVITE, driver);
		click(SUP.Firstname);
		JavaScriptclick(driver, SCP.SENDINVITE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SUP.confirmlink, driver);
		Thread.sleep(5000);
		click(SUP.confirmlink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SCP.FRANCHISEUSERNAME, driver);
		regcData = UserName.split(";");
		SMBCustomerUserName = xLabelRND(regcData);
		Thread.sleep(5000);
		type(SCP.FRANCHISEUSERNAME, SMBCustomerUserName);
		type(SUP.Npassword, Password);
		type(SCP.CONFIRMPASSWORDNEW, ConfirmPassword);
		Thread.sleep(2000);
		click(SUP.complete);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		/// verify dashboard
		waitforElement(60, PUL.LOGINLINK, driver);
		JavaScriptclick(driver, PUL.LOGINLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		waitforElement(180, SUP.Vdashboard, driver);
		Logout();
		driver.get(url);
		waitforElement(180, LP.USERNAME, driver);
		type(LP.USERNAME, SMBCustomerUserName);
		type(LP.PASSWORD, Password);
		click(LP.LOGIN);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		click(SUP.Vdashboard);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, SUP.RatesJurisdictions, driver);
		assertTrue(SUP.RatesJurisdictions.isDisplayed());
		if (UserRole.equalsIgnoreCase("Read Only")) {
			// Verify Rate file menu
			waitforElement(180, SUP.RateHover, driver);
			JavaScriptMousehover(driver, SUP.RateHover);
			click(SUP.myratefile);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, SUP.EXTENDHELP, driver);
			assertTrue(SUP.uploadlink.isDisplayed());
			// verify all links visible on Settings for read only user
			waitforElement(180, SUP.ClickSettings, driver);
			click(SUP.ClickSettings);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, SUP.ViewallConnectors, driver);
			int r = 1;
			By mySettings = By.xpath("//body//div//div/div//div//div//div//a");
			List<WebElement> myESettings = driver.findElements(mySettings);
			for (WebElement e : myESettings) {
				System.out.println(e.getText());
			}
			for (WebElement e : myESettings) {
				if ((e.getText().equalsIgnoreCase("VIEW ALL USERS")) || (e.getText().equalsIgnoreCase("View PDF"))
						|| (e.getText().equalsIgnoreCase("VIEW ALL CONNECTORS")) || (e.getText().contains(""))) {
					r = 2;
				}
			}
			if (r == 1) {
				assertTrue(r == 1);
			}
			// view all users page from settings to check Action
			click(SUP.SettingsViewallusers);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, SUP.RETURNTOSETTINGS, driver);
			assertTrue(SUP.EXPORT.isDisplayed());
			assertTrue(SUP.ActionViewallusers.isDisplayed());
			click(SUP.ClickSettings);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, SUP.ViewallConnectors, driver);
			click(SUP.ViewallConnectors);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			assertTrue(SUP.Viewlaunchsite.isDisplayed());
			By mySelector = By.xpath("//div//div//div/ul//li/a//span[@class='nav-text']");
			List<WebElement> myElements = driver.findElements(mySelector);
			for (WebElement e : myElements) {
				if (e.getText().equalsIgnoreCase("Address Cleansing")) {
					assertTrue(e.getText().equalsIgnoreCase("Address Cleansing"));
				}
			}
		}

		if (Subscription.equalsIgnoreCase("Professional") || Subscription.equalsIgnoreCase("Premium")) {
			// checking configure,address cleansing should not display
			By mySelector = By.xpath("//div//div//div/ul//li/a//span[@class='nav-text']");
			List<WebElement> myElements = driver.findElements(mySelector);
			for (WebElement e : myElements) {
				if (e.getText().equalsIgnoreCase("Configure") || e.getText().equalsIgnoreCase("Address Cleansing")) {
					assertTrue(e.getText().equalsIgnoreCase("Configure")
							|| e.getText().equalsIgnoreCase("Address Cleansing"));
				}
				// verify Calculate taxes for Manage Adjustment File page
				By mycalc = By.xpath("//span[contains(.,'Calculate Taxes')]//..//..//ul//a");
				List<WebElement> myEcalc = driver.findElements(mycalc);
				for (WebElement e1 : myEcalc) {
					if (e1.getText().equalsIgnoreCase("Manage Adjustment File")) {
						assertTrue(e1.getText().equalsIgnoreCase("Manage Adjustment File"));
					}

				}
				// Verify Manage Tax Returns page
				JavaScriptclick(driver, SUP.ManTaxRet);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				assertTrue(SUP.GenReturns.isEnabled());
				// manage out of sales
				if (VerifyObject(driver, SUP.ManOutst)) {
					JavaScriptclick(driver, SUP.ManOutst);
					assertTrue(SUP.Savebutton.isEnabled());
					if (SUP.Currentvalue.isEnabled()) {
						assertTrue(SUP.Currentvalue.isEnabled());
					} else {
						assertTrue(!(SUP.Currentvalue.isEnabled()));
					}
				}
				// reports
				By myReports = By.xpath("//span[contains(.,'Reports')]//..//..//ul//a");
				List<WebElement> myEReports = driver.findElements(myReports);
				for (WebElement e1 : myEReports) {
					if (e1.getText().equalsIgnoreCase("Validation Errors")) {
						assertTrue(e1.getText().equalsIgnoreCase("Validation Errors"));
					}
				}
				// manage prepayments
				JavaScriptMousehover(driver, SUP.ManTaxRet);
				click(SUP.ManagePrepayments);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, SUP.ManagePre, driver);
				if (VerifyObject(driver, SUP.ManagePre)) {
					JavaScriptclick(driver, SUP.ManagePre);
					Thread.sleep(2000);
					waitforElement(180, SUP.ManagePre, driver);
					assertTrue(SUP.PayButton.isDisplayed());
					if (SUP.AmountpaidButton.isEnabled()) {
						assertTrue(SUP.AmountpaidButton.isEnabled());
					} else {
						if (!VerifyObject(driver, PUL.billingReports)) {
							assertTrue(true);
						} else {
							assertTrue(false);
						}
					}
				}
			}

			if (Verify_SSCustomerUser.equalsIgnoreCase("YES")) {

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

				waitforElement(180, PUL.SETTINGSLINK, driver);
				JavaScriptMousehover(driver, PUL.SETTINGSLINK);
				assertTrue(PUL.billingReports.isDisplayed());
				assertTrue(PUL.UPDATEBILLINGLINK.isDisplayed());
				JavaScriptclick(driver, PUL.SETTINGSLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				assertTrue(PUL.UpdateBillingLink.isDisplayed());
				assertTrue(PUL.MyBillingSubscriptionsection.isDisplayed());
			}
		}
	}

	public void VerifyPublicAPICredentials(String Public_API_Generate_Status) throws Exception {

		JavaScriptMousehover(driver, professionalLinks.SettingsHover);
		JavaScriptclick(driver, professionalLinks.ADAPTERSLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, professionalLinks.TRUSTEDIDLABEL, driver);
		if (Public_API_Generate_Status.equalsIgnoreCase("Yes")) {
			assertTrue(getText(professionalLinks.ConnectorsAPIKeyblank).trim().isEmpty());
			assertTrue(getText(professionalLinks.ConnectorsAPIPasswordblank).trim().isEmpty());
		} else {
			ConnectorAPIKey = getText(professionalLinks.ConnectorsAPIKeyblank).trim();
			assertTrue(ConnectorAPIKey.equalsIgnoreCase(APIkey));
			ConnectorAPIPassword = getText(professionalLinks.ConnectorsAPIPasswordblank).trim();
			assertTrue(ConnectorAPIPassword.equalsIgnoreCase(APIPassword));
			assertTrue(getText(professionalLinks.ConnectorsAPIPasswordblank).trim().equalsIgnoreCase(APIPassword));
		}

	}

	public void SecurityTest(String URL, String Login_Verify, String Login_User, String InternalError,
			String Error_Message, String Page_NotFound, String Unauthorized) throws Exception {

		String secUrl = URL;
		if (Login_Verify.equalsIgnoreCase("YES")) {
			UserLogin(Login_User, "Password!1");
			waitforElement(180, loginPage.ACCOUNTICON, driver);
			if (InternalError.equalsIgnoreCase("YES")) {
				driver.navigate().to("" + url + "/" + secUrl + "");
				Thread.sleep(5000);
				assertTrue(configurePage.PAGENOTFOUND.isDisplayed());

				driver.navigate().to(url);
				if (VerifyObject(driver, loginPage.ACCOUNTICON)) {
					Logout();
				}
				waitforElement(180, loginPage.USERNAME, driver);
			} else if (Error_Message.equalsIgnoreCase("YES")) {
				driver.navigate().to("" + url + "/" + secUrl + "");
				assertTrue(driver.findElement(By.xpath("/htms")).isDisplayed());
				driver.navigate().to(url);
				if (VerifyObject(driver, loginPage.ACCOUNTICON)) {
					Logout();
				}
				waitforElement(180, loginPage.USERNAME, driver);
			} else if (Page_NotFound.equalsIgnoreCase("YES")) {
				driver.navigate().to("" + url + "/" + secUrl + "");
				Thread.sleep(5000);
				assertTrue(configurePage.PAGENOTFOUND.isDisplayed());
				assertTrue(configurePage.HELP.isDisplayed());
				driver.navigate().to(url);
				if (VerifyObject(driver, loginPage.ACCOUNTICON)) {
					Logout();
				}
				waitforElement(180, loginPage.USERNAME, driver);
			}
		} else if (Unauthorized.equalsIgnoreCase("YES")) {
			driver.navigate().to("" + url + "/" + secUrl + "");
			assertTrue(driver.findElement(By.xpath("//span[contains(.,'Unauthorized')]")).isDisplayed());

		} else {
			driver.navigate().to("" + url + "/" + secUrl + "");
			Thread.sleep(5000);

			assertTrue(configurePage.VERTEXLOGO.isDisplayed());

			driver.navigate().to(url);
		}

	}

	public void ProfessionalUserLinks(String DashBoard_Title, String Configure_Title, String CalculateTaxes_Title,
			String DistributeTaxes_Title, String ManageAdjust_Title, String Lookuprates_Title,
			String Managetaxreturns_Title, String ConfigReport_Title, String FillingReport_Title,
			String TaxabilityReport_Title, String TransactionReport_Title, String CustomerExemption_Title,
			String ValidationErrors_Title, String EditCompany_Title, String ViewAllUsers_Title,
			String UpdateBilling_Title, String BillingReports_Title, String Adapters_Title, String DocumentCenter_Title,
			String Help_Center_Url, String RateFile, String ViewAllUsers_Verify, String CPA_Topics,
			String TaxAssistRuleReport_Title, String Data_Integrity, String Global_Compliance) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		TransactionReportPage TR = PageFactory.initElements(driver, TransactionReportPage.class);
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		PageFactory.initElements(driver, LookupTaxRates.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ConfigurationReportPage CR = PageFactory.initElements(driver, ConfigurationReportPage.class);
		FilingCalendarReportPage FCR = PageFactory.initElements(driver, FilingCalendarReportPage.class);
		TaxAssistRuleReportPage TAR = PageFactory.initElements(driver, TaxAssistRuleReportPage.class);
		Robot rob = new Robot();
		xDownloadFiles("SERAgreementTERMServicePDF");
		String parentwindow = driver.getWindowHandle();
		click(PUL.DASHBOARDLINK);
		waitforElement(180, PUL.UPDATEANALYSIS, driver);
		if (!VerifyObject(driver, PUL.INLINEHELP)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		assertTrue(DashBoard_Title.equalsIgnoreCase(driver.getTitle()));
		// DASHBOARD Page Verification
		assertTrue(PUL.COMPANYDD.isDisplayed());
		assertTrue(PUL.FILINGPERIODDD.isDisplayed());
		assertTrue(PUL.UPDATERESULTS.isDisplayed());
		assertTrue(PUL.UNITEDSTATES.isDisplayed());
		assertTrue(PUL.CANADA.isDisplayed());
		assertTrue(PUL.MAP.isDisplayed());
		assertTrue(PUL.OVERALLNUMBERSSECTION.isDisplayed());
		assertTrue(PUL.STATESTATSTICS.isDisplayed());
		assertTrue(PUL.CURRENTLYSHOWINGDD.isDisplayed());
		assertTrue(PUL.SORTBYDD.isDisplayed());
		click(PUL.CONFIGURELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, PUL.CONFIGUREOVERVIEW, driver);
		assertTrue(Configure_Title.equalsIgnoreCase(driver.getTitle()));
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, PUL.CALCULATETAXES);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, PUL.LINEITEMS, driver);
		assertTrue(CalculateTaxes_Title.equalsIgnoreCase(driver.getTitle()));
		assertTrue(PUL.INVOICEDEATAILS.isDisplayed());
		assertTrue(PUL.SHIPPINGINFORMATION.isDisplayed());
		assertTrue(PUL.SHIPPINGINFORMATION.isDisplayed());
		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, PUL.DISTRIBUTETAX);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.DISTRIBUTETAXESTEXT, driver);
		assertTrue(DistributeTaxes_Title.equalsIgnoreCase(driver.getTitle()));
		assertTrue(PUL.DISTRIBUTEHEADING.isDisplayed());
		click(PUL.RatesJurisdictions);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, PUL.LOOKUPRATE, driver);
		assertTrue(Lookuprates_Title.equalsIgnoreCase(driver.getTitle()));
		if ((Data_Integrity != "") && (Environment.equalsIgnoreCase("DEV"))) {
			click(PUL.DATAINTEGRITY);
			assertTrue(Data_Integrity.equalsIgnoreCase(driver.getTitle()));
		}
		if ((Global_Compliance != "") && (Environment.equalsIgnoreCase("DEV"))) {
			click(PUL.GLOBALCOMPLIANCE);
			assertTrue(Global_Compliance.equalsIgnoreCase(driver.getTitle()));
		}
		JavaScriptclick(driver, PUL.SettingsHover);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.subscriptionType, driver);
		String Subtype = getText(PUL.subscriptionType);
		if (Subtype.contains("Tax Calculations and Returns")) {
			JavaScriptMousehover(driver, PUL.taxesHover);
			JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.ERRORREPORT, driver);
			assertTrue(ManageAdjust_Title.equalsIgnoreCase(driver.getTitle()));
			// Adjsutment File Page Verification
			assertTrue(PUL.UPLOADNEWFILE.isDisplayed());
			assertTrue(PUL.FILEUPLOADDATE.isDisplayed());
			assertTrue(PUL.ADJUSTMENTFILENAME.isDisplayed());
			assertTrue(PUL.ORIGINALFILE.isDisplayed());
			assertTrue(PUL.SUCCESSREPORT.isDisplayed());
			assertTrue(PUL.ERRORREPORT.isDisplayed());
		}
		if (Subtype.contains("Returns Only")) {
			if (!VerifyObject(driver, PUL.MANAGEADJUSTMENTFILELINK)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
		if (Managetaxreturns_Title.equalsIgnoreCase("No")) {
			if (VerifyObject(driver, PUL.ReturnsHover)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		} else {
			JavaScriptMousehover(driver, PUL.ReturnsHover);
			JavaScriptclick(driver, PUL.MANAGETAXRETURNS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.MOSTRECENT, driver);
			assertTrue(Managetaxreturns_Title.equalsIgnoreCase(driver.getTitle()));
			// Manage Tax Returns Page Verification
			assertTrue(PUL.FILINGPERIOD.isDisplayed());
			assertTrue(PUL.RETURN.isDisplayed());
			assertTrue(PUL.DUEDATE.isDisplayed());
			assertTrue(PUL.PREPAYMENTS.isDisplayed());
			assertTrue(PUL.MOSTRECENT.isDisplayed());
			rob.mouseMove(0, 0);
			if (Subtype.contains("Tax Calculations and Returns")) {
				if (!VerifyObject(driver, PUL.RETURNSONLYUPLOAD)) {
					assertTrue(true);
				} else {
					assertFalse(false);
				}
			}
			if (Subtype.contains("Returns Only")) {
				JavaScriptMousehover(driver, PUL.ReturnsHover);
				JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.RETURNSONLYTITLE, driver);
				assertTrue(PUL.RETURNSONLYTITLE.isDisplayed());
			}
		}
		if (VerifyObject(driver, PUL.CUSTOMERIDREPORT)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CR.Reports_Overview, driver);
		// waitforText(90,CR.Reports_Overview_Text, driver);
		assertTrue(CR.ConfigurationText.isDisplayed());
		JavaScriptclick(driver, CR.ConfigurationLink);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(ConfigReport_Title.equalsIgnoreCase(driver.getTitle()));
		if (FillingReport_Title.equalsIgnoreCase("No")) {
			if (VerifyObject(driver, FCR.FilingCalendarLink)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
			// COMPLIANCEFILE
			JavaScriptclick(driver, PUL.ReportsHover);
			waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.COMPLIANCEFILEPAGETITLE, driver);
		} else {
			JavaScriptclick(driver, FUL.SettingsHover);
			// Thread.sleep(5000);
			waitforElement(180, PUL.MyBillingSubscription, driver);
			if ((VerifyObject(driver, PUL.FILLINGCALENDERREPORTLINK)) == true) {
				if (VerifyObject(driver, FCR.FilingCalendarLink)) {
					assertTrue(true);
				} else {
					assertFalse(false);
				}
			} else {
				JavaScriptclick(driver, PUL.ReportsHover);
				waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(5000);
				// Verifying Filing Calendar Report Heading Text
				assertTrue(FCR.FilingCalendarText.isDisplayed());
				JavaScriptclick(driver, FCR.FilingCalendarLink);
				waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(5000);
				assertTrue(FillingReport_Title.equalsIgnoreCase(driver.getTitle()));
			}
		} // }
			// Verifying Tax Assist Rule Report
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		// Verifying Taxability Report Heading Text
		assertTrue(TAR.TaxAssistRuleReportText.isDisplayed());
		JavaScriptclick(driver, TAR.TaxAssistRuleReport);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(TaxAssistRuleReport_Title.equalsIgnoreCase(driver.getTitle()));
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		// Verifying Taxability Report Heading Text
		assertTrue(PUL.TaxabilityReportText.isDisplayed());
		JavaScriptclick(driver, PUL.TaxabilityReport);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(TaxabilityReport_Title.equalsIgnoreCase(driver.getTitle()));
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		// Verifying Transaction Report Heading Text
		assertTrue(TR.TransactionText.isDisplayed());
		JavaScriptclick(driver, TR.TransactionLink);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(TransactionReport_Title.equalsIgnoreCase(driver.getTitle()));
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		// Verifying CustomerException Report Heading Text
		assertTrue(PUL.CustomerExceptionText.isDisplayed());
		JavaScriptclick(driver, PUL.CustomerExceptionLink);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.GENERATE, driver);
		assertTrue(CustomerExemption_Title.equalsIgnoreCase(driver.getTitle()));
		JavaScriptclick(driver, PUL.ReportsHover);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(PUL.ValidationErrorsText.isDisplayed());
		JavaScriptclick(driver, PUL.ValidationErrors);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		assertTrue(ValidationErrors_Title.equalsIgnoreCase(driver.getTitle()));
		if (ECWFlag == true) {
			assertTrue(PUL.ECWIZARD.isDisplayed());
		}
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.EDITCOMPANYLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.CANCEL, driver);
		assertTrue(EditCompany_Title.equalsIgnoreCase(driver.getTitle()));
		// Edit My Company Page Verification
		assertTrue(PUL.NAME.isDisplayed());
		assertTrue(PUL.PHONE.isDisplayed());
		assertTrue(PUL.COUNTRY.isDisplayed());
		assertTrue(PUL.ADD1.isDisplayed());
		assertTrue(PUL.ADD2.isDisplayed());
		Thread.sleep(2000);
		assertTrue(PUL.CITY.isDisplayed());
		assertTrue(PUL.STATE.isDisplayed());
		assertTrue(PUL.ZIP.isDisplayed());
		assertTrue(PUL.SAVE.isDisplayed());
		assertTrue(PUL.CANCEL.isDisplayed());
		// BPO Client View All Users Link not to Verify
		if (ViewAllUsers_Verify.equalsIgnoreCase("YES")) {
			JavaScriptMousehover(driver, PUL.SettingsHover);
			JavaScriptclick(driver, PUL.VIEWALLUSERSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.CREATENEWUSER, driver);
			assertTrue(ViewAllUsers_Title.equalsIgnoreCase(driver.getTitle()));
			// Verifying Create New User Button
			assertTrue(PUL.CREATENEWUSER.isDisplayed());
		}
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.UPDATEBILLINGLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.SUBMITBILINFO, driver);
		assertTrue(UpdateBilling_Title.equalsIgnoreCase(driver.getTitle()));
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.BILLINGREPORTSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, PUL.BILLINGREPORTHEADING, driver);
		assertTrue(BillingReports_Title.equalsIgnoreCase(driver.getTitle()));
		// Verifying Billing Report Heading Text
		assertTrue(PUL.BILLINGREPORTHEADING.isDisplayed());
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.ADAPTERSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, PUL.LOOKUPURL, driver);
		assertTrue(Adapters_Title.equalsIgnoreCase(driver.getTitle()));
		// View All Connectors Page Verificaiton
		assertTrue(PUL.TRUSTEDIDLABEL.isDisplayed());
		assertTrue(PUL.CONNECTORNAME.isDisplayed());
		assertTrue(PUL.ERPURL.isDisplayed());
		assertTrue(PUL.LOOKUPURL.isDisplayed());
		JavaScriptclick(driver, PUL.SettingsHover);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN1, driver);
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.TRUSTEDID, driver);
		Thread.sleep(5000);
		assertTrue(PUL.TRUSTEDID.isDisplayed());
		assertTrue(PUL.CONTACTSUPPORTHREF.isDisplayed());
		// Verifying RateFile Link
		if (RateFile != "") {
			assertTrue(PUL.RATEFFILEMENLINK.isDisplayed());
			JavaScriptMousehover(driver, PUL.RateFileHover);
			JavaScriptclick(driver, PUL.DWLDRATEFILE_LINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.MONTHYEAR, driver);
			assertTrue(PUL.RATEFILETEMPLATELINK.isDisplayed());
			assertTrue(PUL.MONTHYEAR.isDisplayed());
			JavaScriptMousehover(driver, PUL.RateFileHover);
			JavaScriptclick(driver, PUL.MYRATEFILE_LINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.UPDNEWFILELINK, driver);
			assertTrue(PUL.EXTENDEDHELPRATEFILE.isDisplayed());
			assertTrue(PUL.UPDNEWFILELINK.isDisplayed());
		}
		if (DocumentCenter_Title != "") {
			JavaScriptclick(driver, PUL.DOCUMENTCENTRE);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.DOCUMENTCENTERHEADING, driver);
			assertTrue(DocumentCenter_Title.equalsIgnoreCase(driver.getTitle()));
			// Verifying Document Center Heading Text
			assertTrue(PUL.DOCUMENTCENTERHEADING.isDisplayed());
		} else {
			if (!VerifyObject(driver, PUL.DOCUMENTCENTRE)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
		// Help Center Verification
		if (Browser.equalsIgnoreCase("IE")) {
			scrollDown(driver);
			click(PUL.GETHELPLINK);
		} else {
			JavaScriptclick(driver, PUL.GETHELPLINK);
		}
		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		// To Handle Additional encryption popup displaying
		if (Browser.equalsIgnoreCase("Firefox")) {
			try {
				Alert javascriptAlert = driver.switchTo().alert();
				javascriptAlert.accept();
			} catch (Exception e) {
			}
		}
		waitforText(180, PUL.VISITHELPCENTERWHATSNEW, driver);
		if (CPA_Topics.equalsIgnoreCase("YES")) {
			waitforElement(180, PUL.CUSTOMERTOPICS, driver);
			assertTrue(PUL.PARTNERTOPICS.isDisplayed());
			assertTrue(Help_Center_Url.equalsIgnoreCase(driver.getTitle()));
			driver.close();
			driver.switchTo().window(parentwindow);
			waitforElement(180, PUL.GETHELPLINK, driver);
			if (Browser.equalsIgnoreCase("IE")) {
				scrollUp(driver);
			}
			JavaScriptclick(driver, PUL.DASHBOARDLINK);
			waitforElement(180, PUL.UPDATEANALYSIS, driver);
		} else {
			driver.close();
			driver.switchTo().window(parentwindow);
			waitforElement(180, PUL.GETHELPLINK, driver);
			if (Browser.equalsIgnoreCase("IE")) {
				scrollUp(driver);
			}
			JavaScriptclick(driver, PUL.DASHBOARDLINK);
			waitforElement(180, PUL.UPDATEANALYSIS, driver);
		}
		// What's New Verification
		if (Browser.equalsIgnoreCase("IE")) {
			scrollDown(driver);
			click(PUL.WHATSNEWLINKTC);
		} else {
			JavaScriptclick(driver, PUL.WHATSNEWLINKTC);
		}
		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		// To Handle Additional encryption popup displaying
		if (Browser.equalsIgnoreCase("Firefox")) {
			try {
				Alert javascriptAlert = driver.switchTo().alert();
				javascriptAlert.accept();
			} catch (Exception e) {
			}
		}
		Thread.sleep(5000);
		waitforElement(180, PUL.SEARCHWHATSNEW, driver);
		assertTrue(PUL.SEARCHWHATSNEW.isDisplayed());
		driver.close();
		driver.switchTo().window(parentwindow);
		if (Browser.equalsIgnoreCase("IE")) {
			scrollUp(driver);
		}
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		waitforElement(180, PUL.UPDATEANALYSIS, driver);
	}

	public void VerifyOracleERPCloudInSettings() throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, PUL.SETTINGSLINK, driver);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN1, driver);
		assertTrue(PUL.ACCELERATORORACLEERPCLOUD.isDisplayed());
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, PUL.LAUNCHVERTEXACCELERATORBTN, driver);
		assertTrue(PUL.LAUNCHVERTEXACCELERATORBTN.isDisplayed());
		PUL.LAUNCHVERTEXACCELERATORBTN.click();
		String parentwindow = null;
		parentwindow = driver.getWindowHandle();
		Thread.sleep(2000);
		Set<String> window = driver.getWindowHandles();
		window.remove(parentwindow);
		driver.switchTo().window(window.iterator().next());
		Thread.sleep(3000);
		if (Environment.equalsIgnoreCase("DEV")) {
			assertTrue(driver.getTitle().equalsIgnoreCase("Vertex Accelerator for Oracle ERP Cloud"));
		}
		driver.switchTo().window(parentwindow);
	}

	public void payrolluserLinks(String payrollcalc, String Payrolladdcleansing) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		Thread.sleep(3000);
		waitforElement(30, PUL.SettingsHover, driver);
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, FUL.viewallcredentialslink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		// OAuthCredentials Page Verification
		assertTrue(PUL.OAuthCredentialsHEADER.isDisplayed());
		assertTrue(PUL.CredentialUsage.isDisplayed());
		assertTrue(PUL.ClientId.isDisplayed());
		assertTrue(PUL.Description.isDisplayed());
		assertTrue(PUL.CreatedBy.isDisplayed());
		Thread.sleep(5000);
		assertTrue(PUL.Actions.isDisplayed());
		assertTrue(PUL.RIGHTReturntoSettings.isDisplayed());
		assertTrue(PUL.leftReturntoSettings.isDisplayed());
		assertTrue(PUL.CreatNewCredential.isDisplayed());
		click(PUL.CreatNewCredential);
		waitforElement(180, PUL.AddCredential, driver);
		assertTrue(PUL.CredentialDetails.isDisplayed());
		assertTrue(PUL.selectcredentialusage.isDisplayed());
		assertTrue(PUL.ClientIdinput.isDisplayed());
		assertTrue(PUL.CredentialDescription.isDisplayed());
		assertTrue(PUL.savebutton.isDisplayed());
		assertTrue(PUL.cancelbtn.isDisplayed());
		if (!payrollcalc.isEmpty()) {
			selectByValue(PUL.selectcredentialusage, payrollcalc);
		}
		if (!Payrolladdcleansing.isEmpty()) {
			selectByValue(PUL.selectcredentialusage, Payrolladdcleansing);
		}
		click(PUL.cancelbtn);
	}

	public void VerifyNocreatenewuser(String ClientName) throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		waitforElement(180, vertexsmbcustomersPage.MANAGESMBCUSTLINK, driver);
		click(vertexsmbcustomersPage.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, vertexsmbcustomersPage.SMBCUSTNAMESB, driver);
		assertTrue(vertexsmbcustomersPage.SMBCUSTNAMESB.isDisplayed());
		type(vertexsmbcustomersPage.SMBCUSTNAMESB, ClientName);
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
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(4000);
		String Clientstatus = driver.findElement(By.xpath("//table/tbody/tr/td[8]")).getText();
		if (Clientstatus.equalsIgnoreCase("Initialized") || Clientstatus.equalsIgnoreCase("Pending")) {
			if (!VerifyObject(driver, vertexsmbcustomersPage.CREATENEWUSER)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
	}

	public String AutoItBrowser(WebDriver driver, String browser, String AutoITpath, String AdjustmentFilePath,
			String Filename, String fileformat) throws Exception {
		String status = "";
		try {
			switch (browser.toUpperCase()) {
			case "IE":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			case "FIREFOX":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\"
							+ Filename + fileformat);
				}
				break;
			case "CHROME":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
				} else {
					Runtime.getRuntime().exec(
							AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename + fileformat);
				}
				break;
			case "SAFARI":
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

	public String AutoItBrowser(WebDriver driver, String browser, String AutoITpath, String AdjustmentFilePath,
			String Filename) throws Exception {
		String status = "";
		try {
			switch (browser.toUpperCase()) {
			case "IE":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadIE.exe " + Filename);
				} else {
					Runtime.getRuntime()
							.exec(AutoITpath + "\\FileUploadIE.exe " + AdjustmentFilePath + "\\" + Filename);
				}
				break;
			case "FIREFOX":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadFirefox.exe " + Filename);
				} else {
					Runtime.getRuntime()
							.exec(AutoITpath + "\\FileUploadFirefox.exe " + AdjustmentFilePath + "\\" + Filename);
				}
				break;
			case "CHROME":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadChrome.exe " + Filename);
				} else {
					Runtime.getRuntime()
							.exec(AutoITpath + "\\FileUploadChrome.exe " + AdjustmentFilePath + "\\" + Filename);
				}
				break;
			case "SAFARI":
				if (AdjustmentFilePath.isEmpty() == true) {
					Runtime.getRuntime().exec(AutoITpath + "\\FileUploadSafari.exe " + Filename);
				} else {
					Runtime.getRuntime()
							.exec(AutoITpath + "\\FileUploadSafari.exe " + AdjustmentFilePath + "\\" + Filename);
				}
				break;
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public void CustomerCreateNewUser(String FirstNamevalue, String LastName, String Email, String Phone,
			String UserRole, String UserName, String ConfirmPassword, String ECmrole, String GCrole, String DIrole)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		waitforElement(180, UM.CreateNewUserlink, driver);
		JavaScriptclick(driver, UM.CreateNewUserlink);
		waitforText(180, UM.NEWUSERheader, driver);
		assertTrue(UM.ClientNameinputfield.isDisplayed());
		type(UM.FirstNameinputfield, FirstNamevalue);
		type(UM.LastNameinputfield, LastName);
		type(UM.Emailinputfield, Email);
		click(UM.Phoneinputfield);
		type(UM.Phoneinputfield, Phone);
		sendkeysTab(UM.Phoneinputfield);
		assertTrue(UM.RoleAssignmenttext.isDisplayed());
		JavaScriptclick(driver, UM.AccountManagementdropdown);
		selectByValue(UM.AccountManagementdropdown, "Admin");
		Thread.sleep(3000);
		if (!UserRole.isEmpty()) {
			String Role_type = UserRole;
			switch (Role_type) {
			case "Admin":
				selectByValue(UM.VertexCloudIndirectTaxdropdown, "Admin");
				break;
			case "Read Only":
				selectByValue(UM.VertexCloudIndirectTaxdropdown, "Read Only");
				break;
			case "ECM Only":
				selectByValue(UM.VertexCloudIndirectTaxdropdown, "ECM Only");
				break;
			}
		}
		if (!ECmrole.isEmpty()) {
			switch (ECmrole) {
			case "Admin":
				selectByValue(UM.ExemptionCertificateManagerdropdown, "Admin");
				break;
			case "Read Only":
				selectByValue(UM.ExemptionCertificateManagerdropdown, "Read Only");
				break;
			case "User":
				selectByValue(UM.ExemptionCertificateManagerdropdown, "User");
				break;
			}
		}
		if (!GCrole.isEmpty()) {
			switch (GCrole) {
			case "Admin":
				selectByValue(UM.GlobalCompliancedropdown, "Admin");
				break;
			case "User":
				selectByValue(UM.GlobalCompliancedropdown, "User");
				break;
			case "Read Only":
				selectByValue(UM.GlobalCompliancedropdown, "Read Only");
				break;
			}
		}
		if (!DIrole.isEmpty()) {
			switch (DIrole) {
			case "Admin":
				selectByValue(UM.DataIntegritydropdown, "Admin");
				break;
			}
		}
		click(UM.FirstNameinputfield);
		sendkeysTab(UM.FirstNameinputfield);
		Thread.sleep(2000);
		JavaScriptclick(driver, UM.SendInvitelink);
		assertTrue(UM.ReturntoUserslink.isDisplayed());
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UM.Backlink, driver);
		JavaScriptclick(driver, UM.Emaillink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		waitforElement(180, UM.FirstNameinputfield, driver);
		assertTrue(UM.CompleteYourRegistrationtext.isDisplayed());
		type(UM.Usernamefield, UserName);
		type(UM.Passwordfield, ConfirmPassword);
		type(UM.ConfirmPasswordfield, ConfirmPassword);
		JavaScriptclick(driver, UM.CompleteRegistrationbutton);
		Thread.sleep(4000);
		assertTrue(UM.successtext.isDisplayed());
		assertTrue(UM.Successlink.isDisplayed());
		assertTrue(UM.Loginlink.isDisplayed());
		click(UM.Loginlink);
		Thread.sleep(5000);
	}

	public void Customerallusersverify() throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UM.Allusersheader, driver);
		// All users screen
		assertTrue(UM.Allusersheader.isDisplayed());
		assertTrue(UM.FirstNamelabel.isDisplayed());
		assertTrue(UM.LastNamelabel.isDisplayed());
		assertTrue(UM.Emaillabel.isDisplayed());
		assertTrue(UM.UserNamelabel.isDisplayed());
		assertTrue(UM.Phonelabel.isDisplayed());
		assertTrue(UM.Createdlabel.isDisplayed());
		assertTrue(UM.Lastloginlabel.isDisplayed());
		assertTrue(UM.StatusLABEL.isDisplayed());
		assertTrue(UM.Roleslabel.isDisplayed());
		assertTrue(UM.Actionslabel.isDisplayed());
		assertTrue(UM.Exportbutton.isDisplayed());
		assertTrue(UM.pagination.isDisplayed());
		assertTrue(UM.DesignatedECMContactlink.isDisplayed());
		assertTrue(UM.DesignatedBillingContactlink.isDisplayed());
		selectByValue(UM.pagination, "25 per page");
		selectByValue(UM.pagination, "50 per page");
		selectByValue(UM.pagination, "100 per page");

	}

	public void usermanagementLogout() throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		waitforElement(360, UM.logoutlink, driver);
		JavaScriptclick(driver, UM.logoutlink);
		Thread.sleep(5000);
		switch (Environment) {
		case "DEV":
			url = "https://dev-app.vertexcloud.net/ui/loggedout";
			break;
		case "QA":
			url = "https://QA-app.vertexcloud.net/ui/loggedout";
			break;
		case "STAGE":
			url = "https://stage-app.vertexcloud.com/ui/loggedout";
			break;
		}
		driver.get(url);
		waitforElement(540, UM.UMLoginlink, driver);
		Thread.sleep(50000);
		driver.manage().deleteAllCookies();
		Thread.sleep(10000);
		driver.get(url);
	}

	public void UsermanagementLogin(String user, String pwd) throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		Thread.sleep(5000);
		if (VerifyObject(driver, UM.logoutlink)) {
			usermanagementLogout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		if (VerifyObject(driver, loginPage.DASHBOARD) || VerifyObject(driver, qbo.manageSalesforms)) {
			Logout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		if (VerifyObject(driver, UM.UMLoginlink)) {
			usermanagementLogout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		type(loginPage.USERNAME, user);
		Thread.sleep(2000);
		type(loginPage.PASSWORD, pwd);
		Thread.sleep(2000);
		loginPage.LOGIN.click();
		Thread.sleep(10000);
		waitforElement(180, UM.logoutlink, driver);

	}

	public void DeleteuserfromCustomerportal(String UserName) throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(10000);
		WebElement delete = driver
				.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td/a[contains(.,'Delete')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		// JavaScriptscrolltoView(driver, delete);
		Thread.sleep(2000);
		JavaScriptclick(driver, delete);
		waitforElement(180, UM.DeleteheaderText, driver);
		assertTrue(UM.cancellink.isDisplayed());
		Thread.sleep(2000);
		JavaScriptclick(driver, UM.removecustomerlink);
		Thread.sleep(2000);
		waitforElement(180, UM.CreateNewUserlink, driver);
		WebElement deletedstatus = driver
				.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td[contains(.,'Deleted')]"));
		assertTrue(deletedstatus.isDisplayed());
		Thread.sleep(5000);
	}

	public void reactivateuserfromCustomerportal(String UserName) throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(10000);
		WebElement delete = driver
				.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td/a[contains(.,'Delete')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		// JavaScriptscrolltoView(driver, delete);
		Thread.sleep(2000);
		JavaScriptclick(driver, delete);
		waitforElement(180, UM.DeleteheaderText, driver);
		assertTrue(UM.cancellink.isDisplayed());
		Thread.sleep(2000);
		JavaScriptclick(driver, UM.removecustomerlink);
		Thread.sleep(2000);
		waitforElement(180, UM.CreateNewUserlink, driver);
		WebElement deletedstatus = driver
				.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td[contains(.,'Deleted')]"));
		assertTrue(deletedstatus.isDisplayed());
		Thread.sleep(5000);
		WebElement react = driver
				.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td/a[contains(.,'Reactivate')]"));
		js.executeScript("scroll(100,50)");
		// JavaScriptscrolltoView(driver, delete);
		Thread.sleep(2000);
		JavaScriptclick(driver, react);
		Thread.sleep(2000);
		WebElement redelete = driver
				.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td/a[contains(.,'Delete')]"));
		assertTrue(redelete.isDisplayed());
	}

	@SuppressWarnings("unused")
	public void Exportusers() throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		String path;
		waitforElement(180, UM.CreateNewUserlink, driver);
		click(UM.Exportbutton);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\";
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Users", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			LatestDownloadedFile("Users", false);
			Thread.sleep(2000);
		}
		System.out.println(Filepath);
		xGetCSVCell(Filepath, "First  Name", "Last Name", "Email", "User name", "Phone", "Created Date", "Last Login",
				"Status", "Role");
		Thread.sleep(5000);
	}

	public void PendingusercreationfromCustomer(String FirstNamevalue, String LastName, String Email, String Phone,
			String UserRole) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		JavaScriptclick(driver, professionalLinks.DASHBOARDLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, cpamanagersPage.SETTINGS);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.ADDNEWUSER, driver);
		click(cpamanagersPage.ADDNEWUSER);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, UM.NEWUSERheader, driver);
		assertTrue(UM.ClientNameinputfield.isDisplayed());
		type(UM.FirstNameinputfield, FirstNamevalue);
		type(UM.LastNameinputfield, LastName);
		type(UM.Emailinputfield, Email);
		click(UM.Phoneinputfield);
		type(UM.Phoneinputfield, Phone);
		assertTrue(UM.RoleAssignmenttext.isDisplayed());
		JavaScriptclick(driver, UM.AccountManagementdropdown);
		selectByValue(UM.AccountManagementdropdown, "Admin");
		Thread.sleep(3000);
		String Role_type = UserRole;
		switch (Role_type) {
		case "Admin":
			selectByValue(UM.VertexCloudIndirectTaxdropdown, "Admin");
			break;
		case "Read Only":
			selectByValue(UM.VertexCloudIndirectTaxdropdown, "Read Only");
			break;
		case "ECM Only":
			selectByValue(UM.VertexCloudIndirectTaxdropdown, "ECM Only");
			break;
		}
		click(UM.FirstNameinputfield);
		sendkeysTab(UM.FirstNameinputfield);
		Thread.sleep(2000);
		JavaScriptclick(driver, UM.SendInvitelink);
		assertTrue(UM.ReturntoUserslink.isDisplayed());
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UM.Backlink, driver);
		JavaScriptclick(driver, UM.Backlink);
		waitforElement(30, UM.CREATENEWUSER, driver);
		WebElement Pendingstatus = driver
				.findElement(By.xpath("//td[contains(.,'" + FirstNamevalue + "')]/../td[contains(.,'Pending')]"));
		assertTrue(Pendingstatus.isDisplayed());
		Thread.sleep(5000);
	}

	public void changerolefunctionality(String UserName, String UserRole, String ecmrole) throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(10000);
		WebElement chnagerole = driver
				.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td[10]/div[1]/a[contains(.,'Change')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);
		JavaScriptclick(driver, chnagerole);
		waitforText(180, UM.UserSettingstext, driver);
		Thread.sleep(2000);
		assertTrue(UM.FirstNamedisabled.isDisplayed());
		assertTrue(UM.LastNamedisabled.isDisplayed());
		assertTrue(UM.Emaildisabled.isDisplayed());
		assertTrue(UM.Phonedisabled.isDisplayed());
		assertTrue(UM.UserNamedisabled.isDisplayed());
		assertTrue(UM.UserSettingstext.isDisplayed());
		assertTrue(UM.ReturntoUserslink.isDisplayed());
		click(UM.Changerolecancelbutton);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(2000);
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);
		WebElement chnagerole1 = driver
				.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td[10]/div[1]/a[contains(.,'Change')]"));
		JavaScriptclick(driver, chnagerole1);
		waitforText(180, UM.UserSettingstext, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, UM.AccountManagementdropdown);
		selectByValue(UM.AccountManagementdropdown, "Admin");
		Thread.sleep(3000);
		if (!UserRole.isEmpty()) {
			String Role_type = UserRole;
			switch (Role_type) {
			case "Admin":
				selectByValue(UM.VertexCloudIndirectTaxdropdown, "Admin");
				break;
			case "Read Only":
				selectByValue(UM.VertexCloudIndirectTaxdropdown, "Read Only");
				break;
			case "ECM Only":
				selectByValue(UM.VertexCloudIndirectTaxdropdown, "ECM Only");
				break;
			}
		}
		if (!ecmrole.isEmpty()) {
			switch (ecmrole) {
			case "Admin":
				selectByValue(UM.ExemptionCertificateManagerdropdown, "Admin");
				break;
			case "Read Only":
				selectByValue(UM.ExemptionCertificateManagerdropdown, "Read Only");
				break;
			case "User":
				selectByValue(UM.ExemptionCertificateManagerdropdown, "User");
				break;
			}
		}
		assertTrue(UM.Changerolesavebutton.isDisplayed());
		assertTrue(UM.Changerolecancelbutton.isDisplayed());
		click(UM.Changerolesavebutton);
		Thread.sleep(5000);
		scrollUp(driver);
		waitforElement(180, UM.usersettingssuccess, driver);
		click(UM.ReturntoUserslink);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(5000);
		WebElement newrole = driver.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td[9]"));
		System.out.println(newrole.getText());
		if (UserRole.equalsIgnoreCase("Admin") && ecmrole.equalsIgnoreCase("Admin")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager Admin, Vertex Cloud Indirect Tax Admin ");
		}
		if (UserRole.equalsIgnoreCase("Admin") && ecmrole.equalsIgnoreCase("Read Only")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager Read Only, Vertex Cloud Indirect Tax Admin ");
		}
		if (UserRole.equalsIgnoreCase("Admin") && ecmrole.equalsIgnoreCase("User")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager User, Vertex Cloud Indirect Tax Admin ");
		}
		if (UserRole.equalsIgnoreCase("Read Only") && ecmrole.equalsIgnoreCase("Admin")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Vertex Cloud Indirect Tax Read Only, Exemption Certificate Manager Admin");
		}
		if (UserRole.equalsIgnoreCase("Read Only") && ecmrole.equalsIgnoreCase("Read Only")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Vertex Cloud Indirect Tax Read Only, Exemption Certificate Manager Read Only");
		}
		if (UserRole.equalsIgnoreCase("Read Only") && ecmrole.equalsIgnoreCase("User")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Vertex Cloud Indirect Tax Read Only, Exemption Certificate Manager User");
		}
		if (UserRole.equalsIgnoreCase("ECM Only") && ecmrole.equalsIgnoreCase("Admin")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager Admin, Vertex Cloud Indirect Tax ECM Only ");
		}
		if (UserRole.equalsIgnoreCase("ECM Only") && ecmrole.equalsIgnoreCase("Read Only")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager Read Only, Vertex Cloud Indirect Tax ECM Only ");
		}
		if (UserRole.equalsIgnoreCase("ECM Only") && ecmrole.equalsIgnoreCase("User")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager User, Vertex Cloud Indirect Tax ECM Only ");
		}

	}

	public void usercountverification() throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(2000);
		System.out.println(UM.Activecount.getText());
		System.out.println(UM.PendingCount.getText());
		System.out.println(UM.DeletedCount.getText());
		assertTrue(UM.Activecount.getText() != "0");
		assertTrue(UM.PendingCount.getText() != "0");
		assertTrue(UM.DeletedCount.getText() != "0");

	}

	public void designatedbillingcontact(String UserName) throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(10000);
		WebElement dbc = driver.findElement(
				By.xpath("//td[contains(.,'" + UserName + "')]/../td/div/a[contains(.,'Designated Billing Contact')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);
		JavaScriptclick(driver, dbc);
		waitforText(180, UM.designatedbillingtext, driver);
		assertTrue(UM.SelectBillingContacttext.isDisplayed());
		assertTrue(UM.SelectBillingContactdrodown.isDisplayed());
		assertTrue(UM.designatedbillingUpdate.isDisplayed());
		Thread.sleep(2000);
		selectByValue(UM.SelectBillingContactdrodown, UserName);
		click(UM.designatedbillingUpdate);
		waitforElement(180, UM.Updatebutton, driver);
		Thread.sleep(2000);
		assertTrue(UM.Warningtext.isDisplayed());
		assertTrue(UM.designatedbillingcontact.isDisplayed());
		assertTrue(UM.designatedbillingcontactcofirmtet.isDisplayed());
		assertTrue(UM.Cancelbutton.isDisplayed());
		click(UM.Cancelbutton);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(2000);
		waitforText(180, UM.designatedbillingtext, driver);
		Thread.sleep(2000);
		selectByValue(UM.SelectBillingContactdrodown, UserName);
		click(UM.designatedbillingUpdate);
		waitforElement(180, UM.Updatebutton, driver);
		Thread.sleep(2000);
		click(UM.Updatebutton);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(2000);
		WebElement newrole = driver.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td[9]"));
		System.out.println(newrole.getText());
	}

	public void designatedECMcontact(String UserName) throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(10000);
		WebElement dbc = driver.findElement(
				By.xpath("//td[contains(.,'" + UserName + "')]/../td/div/a[contains(.,'Designated ECM Contact')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);
		JavaScriptclick(driver, dbc);
		waitforText(180, UM.designatedECMtext, driver);
		assertTrue(UM.SelectECMContacttext.isDisplayed());
		assertTrue(UM.SelectECMContactdrodown.isDisplayed());
		assertTrue(UM.ExpirationECMdays.isDisplayed());
		assertTrue(UM.EnableReportECM.isDisplayed());
		assertTrue(UM.disableReportECM.isDisplayed());
		assertTrue(UM.designatedECMUpdate.isDisplayed());
		Thread.sleep(2000);
		selectByValue(UM.SelectECMContactdrodown, UserName);
		click(UM.EnableReportECM);
		click(UM.designatedECMUpdate);
		waitforElement(180, UM.Updatebutton, driver);
		Thread.sleep(2000);
		assertTrue(UM.Warningtext.isDisplayed());
		assertTrue(UM.designatedEMcontact.isDisplayed());
		assertTrue(UM.designatedECMcontactcofirmtet.isDisplayed());
		assertTrue(UM.Cancelbutton.isDisplayed());
		click(UM.Cancelbutton);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(2000);
		waitforText(180, UM.designatedECMtext, driver);
		Thread.sleep(2000);
		selectByValue(UM.SelectECMContactdrodown, UserName);
		click(UM.EnableReportECM);
		click(UM.designatedECMUpdate);
		waitforElement(180, UM.Updatebutton, driver);
		Thread.sleep(2000);
		click(UM.Updatebutton);
		waitforElement(180, UM.CreateNewUserlink, driver);
		Thread.sleep(5000);
		WebElement newrole = driver.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td[9]"));
		System.out.println(newrole.getText());
	}

	public void CustomerCreateNewUservalidations() throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		waitforElement(180, UM.CreateNewUserlink, driver);
		JavaScriptclick(driver, UM.CreateNewUserlink);
		waitforText(180, UM.NEWUSERheader, driver);
		Thread.sleep(3000);
		assertTrue(UM.ClientNameinputfield.isDisplayed());
		JavaScriptclick(driver, UM.SendInvitelink);
		assertTrue(UM.FirstNameerrorinputfield.isDisplayed());
		assertTrue(UM.LastNameerrorinputfield.isDisplayed());
		assertTrue(UM.Emailerrorinputfield.isDisplayed());
		assertTrue(UM.Phoneerrorinputfield.isDisplayed());
		assertTrue(UM.roleassignmenterror.isDisplayed());
		JavaScriptclick(driver, UM.ReturntoUserslink);
		waitforElement(180, UM.CreateNewUserlink, driver);
	}

	public void changerolefunctionalityfromclient(String Email, String UserRole, String ecmrole) throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		JavaScriptclick(driver, professionalLinks.DASHBOARDLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, cpamanagersPage.SETTINGS);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, professionalLinks.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(professionalLinks.VIEWALLUSERSLINKFROMCLIENT);
		Thread.sleep(10000);
		WebElement chnagerole = driver.findElement(
				By.xpath("//td[contains(.,'" + Email + "')]/../td[9]/div[1]/a[contains(.,'Change Role')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);
		JavaScriptclick(driver, chnagerole);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.USEREMAIL, driver);
		Thread.sleep(2000);
		waitforElement(180, cpamanagersPage.ACCOUNTMANAGEMENTROLE, driver);
		JavaScriptclick(driver, cpamanagersPage.ACCOUNTMANAGEMENTROLE);
		selectByValue(cpamanagersPage.ACCOUNTMANAGEMENTROLE, "Admin");
		Thread.sleep(3000);
		if (!UserRole.isEmpty()) {
			String Role_type = UserRole;
			switch (Role_type) {
			case "Admin":
				selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE, "Admin");
				break;
			case "Read Only":
				selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE, "Read Only");
				break;
			case "ECM Only":
				selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE, "ECM Only");
				break;
			}
		}
		if (!ecmrole.isEmpty()) {
			switch (ecmrole) {
			case "Admin":
				selectByValue(cpamanagersPage.EXEMPTIONCMROLE, "Admin");
				break;
			case "Read Only":
				selectByValue(cpamanagersPage.EXEMPTIONCMROLE, "Read Only");
				break;
			case "User":
				selectByValue(cpamanagersPage.EXEMPTIONCMROLE, "User");
				break;
			}
		}
		assertTrue(UM.Changerolesavebutton.isDisplayed());
		assertTrue(UM.Changerolecancelbutton.isDisplayed());
		click(UM.Changerolesavebutton);
		Thread.sleep(5000);
		scrollUp(driver);
		waitforElement(180, cpamanagersPage.RETURNTOUSERS, driver);
		click(cpamanagersPage.RETURNTOUSERS);
		Thread.sleep(5000);
		WebElement newrole = driver.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td[7]"));
		System.out.println(newrole.getText());
		if (UserRole.equalsIgnoreCase("Admin") && ecmrole.equalsIgnoreCase("Admin")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager Admin, Vertex Cloud Indirect Tax Admin ");
		}
		if (UserRole.equalsIgnoreCase("Admin") && ecmrole.equalsIgnoreCase("Read Only")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager Read Only, Vertex Cloud Indirect Tax Admin ");
		}
		if (UserRole.equalsIgnoreCase("Admin") && ecmrole.equalsIgnoreCase("User")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager User, Vertex Cloud Indirect Tax Admin ");
		}
		if (UserRole.equalsIgnoreCase("Read Only") && ecmrole.equalsIgnoreCase("Admin")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Vertex Cloud Indirect Tax Read Only, Exemption Certificate Manager Admin");
		}
		if (UserRole.equalsIgnoreCase("Read Only") && ecmrole.equalsIgnoreCase("Read Only")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Vertex Cloud Indirect Tax Read Only, Exemption Certificate Manager Read Only");
		}
		if (UserRole.equalsIgnoreCase("Read Only") && ecmrole.equalsIgnoreCase("User")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Vertex Cloud Indirect Tax Read Only, Exemption Certificate Manager User");
		}
		if (UserRole.equalsIgnoreCase("ECM Only") && ecmrole.equalsIgnoreCase("Admin")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager Admin, Vertex Cloud Indirect Tax ECM Only ");
		}
		if (UserRole.equalsIgnoreCase("ECM Only") && ecmrole.equalsIgnoreCase("Read Only")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager Read Only, Vertex Cloud Indirect Tax ECM Only ");
		}
		if (UserRole.equalsIgnoreCase("ECM Only") && ecmrole.equalsIgnoreCase("User")) {
			newrole.getText().equalsIgnoreCase(
					"Account Management Admin, Exemption Certificate Manager User, Vertex Cloud Indirect Tax ECM Only ");
		}

	}

	public void DeleteuserfromClientportal(String Email) throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		JavaScriptclick(driver, professionalLinks.DASHBOARDLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, cpamanagersPage.SETTINGS);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, professionalLinks.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(professionalLinks.VIEWALLUSERSLINKFROMCLIENT);
		Thread.sleep(10000);
		WebElement delete = driver
				.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td/a[contains(.,'Delete')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		// JavaScriptscrolltoView(driver, delete);
		Thread.sleep(2000);
		JavaScriptclick(driver, delete);
		waitforElement(180, UM.DeleteheaderText, driver);
		assertTrue(UM.cancellink.isDisplayed());
		Thread.sleep(2000);
		JavaScriptclick(driver, UM.removecustomerlink);
		Thread.sleep(2000);
		waitforElement(180, UM.CreateNewUserlink, driver);
		WebElement deletedstatus = driver
				.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td[contains(.,'Deleted')]"));
		assertTrue(deletedstatus.isDisplayed());
		Thread.sleep(5000);
	}

	public void PendinguserfromClient(String FirstNamevalue, String LastName, String Email, String Phone,
			String UserRole) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		waitforElement(180, UM.CreateNewUserlink, driver);
		JavaScriptclick(driver, UM.CreateNewUserlink);
		waitforText(180, UM.NEWUSERheader, driver);
		assertTrue(UM.ClientNameinputfield.isDisplayed());
		type(UM.FirstNameinputfield, FirstNamevalue);
		type(UM.LastNameinputfield, LastName);
		type(UM.Emailinputfield, Email);
		click(UM.Phoneinputfield);
		type(UM.Phoneinputfield, Phone);
		assertTrue(UM.RoleAssignmenttext.isDisplayed());
		JavaScriptclick(driver, UM.AccountManagementdropdown);
		selectByValue(UM.AccountManagementdropdown, "Admin");
		Thread.sleep(3000);
		String Role_type = UserRole;
		switch (Role_type) {
		case "Admin":
			selectByValue(UM.VertexCloudIndirectTaxdropdown, "Admin");
			break;
		case "Read Only":
			selectByValue(UM.VertexCloudIndirectTaxdropdown, "Read Only");
			break;
		case "ECM Only":
			selectByValue(UM.VertexCloudIndirectTaxdropdown, "ECM Only");
			break;
		}
		click(UM.FirstNameinputfield);
		sendkeysTab(UM.FirstNameinputfield);
		Thread.sleep(2000);
		JavaScriptclick(driver, UM.SendInvitelink);
		assertTrue(UM.ReturntoUserslink.isDisplayed());
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UM.Backlink, driver);
		JavaScriptclick(driver, UM.Backlink);
		waitforElement(180, UM.CreateNewUserlink, driver);
		WebElement Pendingstatus = driver
				.findElement(By.xpath("//td[contains(.,'" + FirstNamevalue + "')]/../td[contains(.,'Pending')]"));
		assertTrue(Pendingstatus.isDisplayed());
		Thread.sleep(5000);
	}

	// R.14 registration
	// new Calc parity
	public void calcparityInviteNewUser(String role, String accountType, String varType, String clientName,
			String firstName, String lastName, String email, String country, String Phone, String Address, String City,
			String State, String Zip, String Subscription, String SubscriptionOption, String Reservepod)
			throws Exception {
		waitUntilElementInvisibe(60, "html[@class='nprogressbusy']", driver);
		waitforElement(60, registrationPage.INVITEUSER, driver);
		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		waitforElement(60, registrationPage.VARNAMES, driver);
		selectByValue(registrationPage.ACCOUNTTYPE, accountType);
		sendkeysTab(registrationPage.ACCOUNTTYPE);
		waitforText(60,
				"//label[contains(.,'VAR/CPA/Franchise Name* :')]/../div/select/option[text()='" + varType + "']",
				driver);
		selectByValue(registrationPage.VARNAMES, varType);

		int m = (int) Math.pow(10, 6 - 1);
		int x = m + new Random().nextInt(9 * m);
		oraclenumber = String.valueOf(x);
		type(registrationPage.OraclePartyNumber, oraclenumber);
		type(registrationPage.CLIENT, clientName);
		type(registrationPage.FIRSTNAME, firstName);
		type(registrationPage.LASTNAME, lastName);
		type(registrationPage.EMAIL, email);
		selectByValue(registrationPage.NEWUSERCOUNTRY, country);
		Thread.sleep(3000);
		click(registrationPage.PHONE);
		type(registrationPage.PHONE, Phone);
		sendkeysTab(registrationPage.PHONE);
		JavaScriptclick(driver, registrationPage.NEWUSERSTREETADDRESS1);
		type(registrationPage.NEWUSERSTREETADDRESS1, Address);
		click(registrationPage.NEWUSERCITY);
		type(registrationPage.NEWUSERCITY, City);
		if (VerifyObject(driver, registrationPage.NEWUSERSTATE)) {
			selectByValue(registrationPage.NEWUSERSTATE, State);
		} else {
			type(registrationPage.FOREIGNSTATE, State);
		}
		click(registrationPage.NEWUSERZIP);
		JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
		sendkeysTab(registrationPage.NEWUSERZIP);
		selectByValue(registrationPage.NEWUSERSUBSCRIPTION, Subscription);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.PROFESSIONALSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			selectByValue(registrationPage.PROFESSIONALSUBSCRIPTIONSELECT, SubscriptionOption);

		}
		if (VerifyObject(driver, registrationPage.PREMIUMSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			// selectByValue(registrationPage.PREMIUMSUBSCRIPTIONSELECT,
			// SubscriptionOption);
			/*
			 * int countriessize = registrationPage.PremiumSubscriptionFeatures.size(); for
			 * (int countries = 0; countries < countriessize; countries++) {
			 * System.out.println(registrationPage.PremiumSubscriptionFeatures.get(countries
			 * ).getText());
			 * assertFalse(registrationPage.PremiumSubscriptionFeatures.get(countries).
			 * getText() .equals("Cloud Oseries - Tax Calculations")); }
			 */
		}
		if (VerifyObject(driver, registrationPage.CloudOSeriestext)) {
			click(registrationPage.CloudOSeriestext);
		}

		if (VerifyObject(driver, registrationPage.ReservedPodText) && !Reservepod.isEmpty()) {
			click(registrationPage.ReservedPodinput);
			if (Environment.equalsIgnoreCase("DEV") || (Environment.equalsIgnoreCase("QA"))) {
				selectByValue(registrationPage.ReservedPodselection, "Pod 3");
			}

			if (Environment.equalsIgnoreCase("STAGE")) {
				selectByValue(registrationPage.ReservedPodselection, "Pod 4");
			}
		}
	}

	// R.15 registration
	// new Calc parity
	public void calcparityInviteNewUser(String role, String accountType, String varType, String clientName,
			String email, String country, String Address, String City, String State, String Zip, String Subscription,
			String SubscriptionOption, String Reservepod) throws Exception {
		waitUntilElementInvisibe(60, "html[@class='nprogressbusy']", driver);
		waitforElement(60, registrationPage.INVITEUSER, driver);
		click(registrationPage.INVITEUSER);
		Thread.sleep(3000);
		int count = 0;
		waitforElement(180, registrationPage.ADMININVITE, driver);
		JavaScriptclick(driver, registrationPage.ADMININVITE);
		Thread.sleep(2000);
		if (!VerifyObject(driver, registrationPage.ROLE)) {
			boolean report = false;
			do {
				click(registrationPage.ADMININVITE);
				Thread.sleep(5000);
				count = count + 1;
				if (count == 10) {
					break;
				}
				if (VerifyObject(driver, registrationPage.ROLE)) {
					report = true;
				}
			} while (report == false);
		}

		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		waitforElement(60, registrationPage.VARNAMES, driver);
		selectByValue(registrationPage.ACCOUNTTYPE, accountType);
		sendkeysTab(registrationPage.ACCOUNTTYPE);
		waitforText(60,
				"//label[contains(.,'VAR/CPA/Franchise Name* :')]/../div/select/option[text()='" + varType + "']",
				driver);
		selectByValue(registrationPage.VARNAMES, varType);

		int m = (int) Math.pow(10, 6 - 1);
		int x = m + new Random().nextInt(9 * m);
		oraclenumber = String.valueOf(x);
		type(registrationPage.OraclePartyNumber, oraclenumber);
		type(registrationPage.CLIENT, clientName);
		type(registrationPage.EMAIL, email);
		selectByValue(registrationPage.NEWUSERCOUNTRY, country);
		Thread.sleep(3000);
		JavaScriptclick(driver, registrationPage.NEWUSERSTREETADDRESS1);
		type(registrationPage.NEWUSERSTREETADDRESS1, Address);
		click(registrationPage.NEWUSERCITY);
		type(registrationPage.NEWUSERCITY, City);
		if (VerifyObject(driver, registrationPage.NEWUSERSTATE)) {
			selectByValue(registrationPage.NEWUSERSTATE, State);
		} else {
			type(registrationPage.FOREIGNSTATE, State);
		}
		click(registrationPage.NEWUSERZIP);
		JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
		sendkeysTab(registrationPage.NEWUSERZIP);
		selectByValue(registrationPage.NEWUSERSUBSCRIPTION, Subscription);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.PROFESSIONALSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			selectByValue(registrationPage.PROFESSIONALSUBSCRIPTIONSELECT, SubscriptionOption);

		}
		if (VerifyObject(driver, registrationPage.PREMIUMSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			// selectByValue(registrationPage.PREMIUMSUBSCRIPTIONSELECT,
			// SubscriptionOption);
			/*
			 * int countriessize = registrationPage.PremiumSubscriptionFeatures.size(); for
			 * (int countries = 0; countries < countriessize; countries++) {
			 * System.out.println(registrationPage.PremiumSubscriptionFeatures.get(countries
			 * ).getText());
			 * assertFalse(registrationPage.PremiumSubscriptionFeatures.get(countries).
			 * getText() .equals("Cloud Oseries - Tax Calculations")); }
			 */
		}
		if (VerifyObject(driver, registrationPage.CloudOSeriestext)) {
			click(registrationPage.CloudOSeriestext);
		}
		/*
		 * Thread.sleep(2000); if (VerifyObject(driver,
		 * registrationPage.INDIRECTTAXINTELLIGENCETEXT)) {
		 * click(registrationPage.INDIRECTTAXINTELLIGENCETEXT); }
		 */

		if (VerifyObject(driver, registrationPage.ReservedPodText) && !Reservepod.isEmpty()) {
			click(registrationPage.ReservedPodinput);
			switch (Reservepod) {
			case "3":
				selectByValue(registrationPage.ReservedPodselection, "Pod 3");
				break;
			case "4":
				selectByValue(registrationPage.ReservedPodselection, "Pod 4");
				break;
			case "7":
				selectByValue(registrationPage.ReservedPodselection, "Pod 7");
				break;
			case "11":
				selectByValue(registrationPage.ReservedPodselection, "Pod 11");
				break;
			case "12":
				selectByValue(registrationPage.ReservedPodselection, "Pod 12");
				break;
			case "42":
				selectByValue(registrationPage.ReservedPodselection, "Pod 42");
				break;
			case "43":
				selectByValue(registrationPage.ReservedPodselection, "Pod 43");
				break;
			case "44":
				selectByValue(registrationPage.ReservedPodselection, "Pod 44");
				break;
			case "45":
				selectByValue(registrationPage.ReservedPodselection, "Pod 45");
				break;
			case "90":
				selectByValue(registrationPage.ReservedPodselection, "Pod 90");
				break;
			case "91":
				selectByValue(registrationPage.ReservedPodselection, "Pod 91");
				break;
			case "100":
				selectByValue(registrationPage.ReservedPodselection, "Pod 100");
				break;
			case "101":
				selectByValue(registrationPage.ReservedPodselection, "Pod 101");
				break;
			}
		}
		System.out.println("UserName:" + email);
		Reporter.log(email);
		logger.debug(email);
	}

	public void InviteNewUserForCalcParity(String role, String accountType, String varType, String clientName,
			String email, String country, String Address, String City, String State, String Zip, String Subscription,
			String SubscriptionOption, String OSeriesCloud, String IndirectTaxIntelligence, String OSeriesEdge,
			String Reservepod, String Invoice) throws Exception {
		waitUntilElementInvisibe(60, "html[@class='nprogressbusy']", driver);
		waitforElement(60, registrationPage.INVITEUSER, driver);
		click(registrationPage.INVITEUSER);
		Thread.sleep(3000);
		int count = 0;
		waitforElement(180, registrationPage.ADMININVITE, driver);
		JavaScriptclick(driver, registrationPage.ADMININVITE);
		Thread.sleep(2000);
		if (!VerifyObject(driver, registrationPage.ROLE)) {
			boolean report = false;
			do {
				click(registrationPage.ADMININVITE);
				Thread.sleep(5000);
				count = count + 1;
				if (count == 10) {
					break;
				}
				if (VerifyObject(driver, registrationPage.ROLE)) {
					report = true;
				}
			} while (report == false);
		}

		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		waitforElement(60, registrationPage.VARNAMES, driver);
		selectByValue(registrationPage.ACCOUNTTYPE, accountType);
		sendkeysTab(registrationPage.ACCOUNTTYPE);
		waitforText(60,
				"//label[contains(.,'VAR/CPA/Franchise Name* :')]/../div/select/option[text()='" + varType + "']",
				driver);
		selectByValue(registrationPage.VARNAMES, varType);

		int m = (int) Math.pow(10, 6 - 1);
		int x = m + new Random().nextInt(9 * m);
		oraclenumber = String.valueOf(x);
		type(registrationPage.OraclePartyNumber, oraclenumber);
		type(registrationPage.CLIENT, clientName);
		type(registrationPage.EMAIL, email);
		selectByValue(registrationPage.NEWUSERCOUNTRY, country);
		Thread.sleep(3000);
		JavaScriptclick(driver, registrationPage.NEWUSERSTREETADDRESS1);
		type(registrationPage.NEWUSERSTREETADDRESS1, Address);
		click(registrationPage.NEWUSERCITY);
		type(registrationPage.NEWUSERCITY, City);
		if (VerifyObject(driver, registrationPage.NEWUSERSTATE)) {
			selectByValue(registrationPage.NEWUSERSTATE, State);
		} else {
			type(registrationPage.FOREIGNSTATE, State);
		}
		click(registrationPage.NEWUSERZIP);
		JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
		sendkeysTab(registrationPage.NEWUSERZIP);
		selectByValue(registrationPage.NEWUSERSUBSCRIPTION, Subscription);
		Thread.sleep(5000);
		if (VerifyObject(driver, registrationPage.PROFESSIONALSUBSCRIPTIONSELECT) && !SubscriptionOption.isEmpty()) {
			selectByValue(registrationPage.PROFESSIONALSUBSCRIPTIONSELECT, SubscriptionOption);

		}
		if (OSeriesCloud.equals("Yes")) {
			waitforElement(60, registrationPage.CloudOSeriestext, driver);
			click(registrationPage.CloudOSeriestext);
		}

		if (IndirectTaxIntelligence.equals("Yes")) {
			waitforElement(60, registrationPage.INDIRECTTAXINTELLIGENCETEXT, driver);
			click(registrationPage.INDIRECTTAXINTELLIGENCETEXT);
		}
		if (OSeriesEdge.equals("Yes")) {
			waitforElement(60, registrationPage.OSERIESEDGE, driver);
			click(registrationPage.OSERIESEDGE);
		}

		if (VerifyObject(driver, registrationPage.ReservedPodText) && !Reservepod.isEmpty()) {
			click(registrationPage.ReservedPodinput);
			switch (Reservepod) {
			case "3":
				selectByValue(registrationPage.ReservedPodselection, "Pod 3");
				break;
			case "4":
				selectByValue(registrationPage.ReservedPodselection, "Pod 4");
				break;
			case "7":
				selectByValue(registrationPage.ReservedPodselection, "Pod 7");
				break;
			case "11":
				selectByValue(registrationPage.ReservedPodselection, "Pod 11");
				break;
			case "12":
				selectByValue(registrationPage.ReservedPodselection, "Pod 12");
				break;
			case "42":
				selectByValue(registrationPage.ReservedPodselection, "Pod 42");
				break;
			case "43":
				selectByValue(registrationPage.ReservedPodselection, "Pod 43");
				break;
			case "44":
				selectByValue(registrationPage.ReservedPodselection, "Pod 44");
				break;
			case "45":
				selectByValue(registrationPage.ReservedPodselection, "Pod 45");
				break;
			case "90":
				selectByValue(registrationPage.ReservedPodselection, "Pod 90");
				break;
			case "91":
				selectByValue(registrationPage.ReservedPodselection, "Pod 91");
				break;
			case "100":
				selectByValue(registrationPage.ReservedPodselection, "Pod 100");
				break;
			case "101":
				selectByValue(registrationPage.ReservedPodselection, "Pod 101");
				break;
			}
		}
		if (Invoice.equals("Yes")) {
			calcparityinvoice("Invoice", "ACH Credit", "Yes", "", "", "", "", "19468");
		} else {
			Thread.sleep(4000);
			JavaScriptclick(driver, registrationPage.SNDIVTE);
			waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(2000);
			if (VerifyObject(driver, registrationPage.ZIPERROR)) {
				clear(registrationPage.NEWUSERZIP);
				JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
				registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "a");
				registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				click(registrationPage.NEWUSERZIP);
				registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "v");
				registrationPage.NEWUSERZIP.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				clear(registrationPage.InvoiceZip);
				JavaScripttype(driver, registrationPage.InvoiceZip, Zip);
				registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "a");
				registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				click(registrationPage.InvoiceZip);
				registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "v");
				registrationPage.InvoiceZip.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				JavaScriptclick(driver, registrationPage.SNDIVTE);
				waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
			}
			waitforElement(60, registrationPage.USERLINK, driver);
			JavaScriptclick(driver, registrationPage.USERLINK);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);

		}

		System.out.println("UserName:" + email);
		Reporter.log(email);
		logger.debug(email);
	}

	public void noAddtionalFeatures(String Zip) throws Exception {
		assertTrue(!registrationPage.ECWFEATURE.isDisplayed());
		assertTrue(!registrationPage.RATEFILE.isDisplayed());
		assertTrue(!registrationPage.RETAILTAXEXTRACT.isDisplayed());
		assertTrue(!registrationPage.ECWIZARDFEATURE.isDisplayed());
		assertTrue(!registrationPage.PayrollCalculation.isDisplayed());
		assertTrue(!registrationPage.PayrollAddressCleansing.isDisplayed());
		assertTrue(!registrationPage.RTRS.isDisplayed());
		assertTrue(!registrationPage.TAXIMPACTREPORT.isDisplayed());
		assertTrue(!registrationPage.GLOBALCOMPLIANCE.isDisplayed());
		assertTrue(!registrationPage.DATAINTEGRITY.isDisplayed());
		assertTrue(!registrationPage.LEGACYTRANSACTIONREPORT.isDisplayed());
		assertTrue(!registrationPage.ACCELERATORORACLEERPCLOUD.isDisplayed());
		assertTrue(!registrationPage.RATEFILE.isDisplayed());
		Thread.sleep(4000);
		JavaScriptclick(driver, registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, registrationPage.ZIPERROR)) {
			clear(registrationPage.NEWUSERZIP);
			JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "a");
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			click(registrationPage.NEWUSERZIP);
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "v");
			registrationPage.NEWUSERZIP.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			JavaScriptclick(driver, registrationPage.SNDIVTE);
			waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		}
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
	}

	// R8.14
	public void CompleteRegistrationforcalcparity(String userName, String passWord, String passwordConfirm)
			throws Exception {
		waitforElement(360, registrationPage.USERNAME, driver);
		type(registrationPage.USERNAME, userName);
		Reporter.log(userName);
		logger.debug(userName);
		click(registrationPage.PASSWORD);
		type(registrationPage.PASSWORD, passWord);
		click(registrationPage.PASSWORDCONFIRM);
		type(registrationPage.PASSWORDCONFIRM, passwordConfirm);
		Thread.sleep(5000);
		// assertTrue(registrationPage.calcparitydisabledtext.isDisplayed());
		// assertTrue(registrationPage.calcparitydescrition.isDisplayed());
		if (VerifyObject(driver, registrationPage.NEXTSTEP2)) {
			click(registrationPage.NEXTSTEP2);
			Thread.sleep(4000);
			waitforElement(360, registrationPage.BILLINGADDRESS1, driver);
		} else if (VerifyObject(driver, registrationPage.Submit)) {
			click(registrationPage.Submit);
			Thread.sleep(4000);
		} else {
			if (VerifyObject(driver, registrationPage.ECWFinishandSave))
				click(registrationPage.ECWFinishandSave);
		}
		Thread.sleep(20000);
	}

	// R8.15
	public void CompleteRegistrationforcalcparity(String firstName, String lastName, String Phone, String passWord,
			String passwordConfirm) throws Exception {

		waitforElement(360, registrationPage.FIRSTNAME, driver);
		type(registrationPage.FIRSTNAME, firstName);
		type(registrationPage.LASTNAME, lastName);
		click(registrationPage.PHONE);
		type(registrationPage.PHONE, Phone);
		sendkeysTab(registrationPage.PHONE);
		click(registrationPage.PASSWORD);
		type(registrationPage.PASSWORD, passWord);
		click(registrationPage.PASSWORDCONFIRM);
		type(registrationPage.PASSWORDCONFIRM, passwordConfirm);
		Thread.sleep(5000);
		// assertTrue(registrationPage.calcparitydisabledtext.isDisplayed());
		// assertTrue(registrationPage.calcparitydescrition.isDisplayed());
		if (VerifyObject(driver, registrationPage.NEXTSTEP2)) {
			click(registrationPage.NEXTSTEP2);
			Thread.sleep(4000);
			waitforElement(360, registrationPage.BILLINGADDRESS1, driver);
		} else if (VerifyObject(driver, registrationPage.Submit)) {
			click(registrationPage.Submit);
			Thread.sleep(4000);
		} else {
			if (VerifyObject(driver, registrationPage.ECWFinishandSave))
				click(registrationPage.ECWFinishandSave);
		}
		Thread.sleep(20000);
	}

	public void calcparityinvoice(String AddtionalFeature, String Paymenttype,
			String Billingaddresssameascompanyaddress, String Country, String StreetAddress, String City, String State,
			String Zip) throws Exception {
		String[] feature = AddtionalFeature.split(",");
		if (!AddtionalFeature.isEmpty()) {
			for (int i = 0; i < feature.length; i++) {
				switch (feature[i].toUpperCase()) {
				case "INVOICE":
					click(registrationPage.InvoiceChk);
					Thread.sleep(1500);
					waitforElement(180, registrationPage.InvoicePaymentMethod, driver);
					selectByValue(registrationPage.InvoicePaymentMethod, Paymenttype);
					if (Billingaddresssameascompanyaddress.equalsIgnoreCase("Yes")) {
						click(registrationPage.InvoiceBillingAddresChkbox);
						Thread.sleep(2000);
						click(registrationPage.InvoiceZip);
						JavaScripttype(driver, registrationPage.InvoiceZip, Zip);
					} else {
						selectByValue(registrationPage.InvoiceCountry, Country);
						type(registrationPage.InvoiceStreet1, StreetAddress);
						type(registrationPage.InvoiceCity, City);
						if (Country.equalsIgnoreCase("UNITED STATES") || Country.equalsIgnoreCase("CANADA")) {
							selectByValue(registrationPage.InvoiceState, State);
						} else {
							type(registrationPage.BillingState, State);
						}
						clear(registrationPage.InvoiceZip);
						JavaScripttype(driver, registrationPage.InvoiceZip, Zip);
						registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "a");
						registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "x");
						Thread.sleep(2000);
						click(registrationPage.InvoiceZip);
						registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "v");
						registrationPage.InvoiceZip.sendKeys(Keys.TAB);
					}
					break;
				default:
				}
			}
		}
		Thread.sleep(4000);
		JavaScriptclick(driver, registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, registrationPage.ZIPERROR)) {
			clear(registrationPage.NEWUSERZIP);
			JavaScripttype(driver, registrationPage.NEWUSERZIP, Zip);
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "a");
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			click(registrationPage.NEWUSERZIP);
			registrationPage.NEWUSERZIP.sendKeys(Keys.CONTROL + "v");
			registrationPage.NEWUSERZIP.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			clear(registrationPage.InvoiceZip);
			JavaScripttype(driver, registrationPage.InvoiceZip, Zip);
			registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "a");
			registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "x");
			Thread.sleep(2000);
			click(registrationPage.InvoiceZip);
			registrationPage.InvoiceZip.sendKeys(Keys.CONTROL + "v");
			registrationPage.InvoiceZip.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			JavaScriptclick(driver, registrationPage.SNDIVTE);
			waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		}
		waitforElement(60, registrationPage.USERLINK, driver);
		JavaScriptclick(driver, registrationPage.USERLINK);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, "html[@class='nprogressbusy']", driver);
	}

	public void CreateNewUserForGC(String Firstname, String LastName, String Email, String Phone,
			String AccountManagementRole, String VertexIndirecttaxrole, String ECMRole, String[] GlobaComplaincerole,
			String DataIntegrityrole, String ITIrole, String UserName, String Password, String ConfirmPassword)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		JavaScriptclick(driver, cpamanagersPage.SETTINGS);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.ADDNEWUSER, driver);
		click(cpamanagersPage.ADDNEWUSER);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
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

		// Adding GC Roles
		int gcRoles = GlobaComplaincerole.length;

		if (gcRoles > 0) {
			for (int a = 0; a < gcRoles; a++) {
				click(cpamanagersPage.GLOBALCOMPLIANCE);
				cpamanagersPage.GLOBALCOMPLIANCE.sendKeys(GlobaComplaincerole[a]);
				// System.out.println("GlobaComplaincerole[a]: "+GlobaComplaincerole[a]);
				// Thread.sleep(5000);
				String role = GlobaComplaincerole[a];
				switch (role) {
				case "Admin":
					WebElement selectprod1 = driver.findElement(By.xpath(
							"//div[@class='chosen-drop']/ul/li[contains(.,'" + GlobaComplaincerole[a] + "')][2]"));
					click(selectprod1);
					break;

				case "Access Management Admin":
					WebElement selectprod2 = driver.findElement(By.xpath(
							"//div[@class='chosen-drop']/ul/li[contains(.,'" + GlobaComplaincerole[a] + "')][1]"));
					click(selectprod2);
					break;

				default:
					WebElement selectprod4 = driver.findElement(By
							.xpath("//div[@class='chosen-drop']/ul/li[contains(.,'" + GlobaComplaincerole[a] + "')]"));
					click(selectprod4);
				}

				// WebElement selectprod =
				// driver.findElement(By.xpath("//div[@class='chosen-drop']/ul/li[contains(.,'"
				// + GlobaComplaincerole[a] + "')]"));
				// kk //WebElement selectprod =
				// driver.findElement(By.xpath("//div[@class='chosen-drop']//ul//li[text()='" +
				// GlobaComplaincerole[a] + "']"));
				// click(selectprod);
			}

		}

		if (VerifyObject(driver, cpamanagersPage.DATAINTEGRITYROLE) && DataIntegrityrole != "") {
			selectByValue(cpamanagersPage.DATAINTEGRITYROLE, DataIntegrityrole);
		}

		// Adding ITI role
		if (ITIrole != "") {
			selectByValue(cpamanagersPage.INDIRECTTAXINTELLIGENCE, ITIrole);
		}

		click(cpamanagersPage.SENDINVITEUSER);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.COMPLETEREGISTRATIONLINK, driver);
		click(cpamanagersPage.COMPLETEREGISTRATIONLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.USERNAME, driver);
		PortalUserName = UserName;
		type(cpamanagersPage.USERNAME, PortalUserName);
		type(cpamanagersPage.PASSWORD, Password);
		type(cpamanagersPage.CONFIRMPASSWORD, ConfirmPassword);
		Thread.sleep(1000);
		JavaScriptclick(driver, cpamanagersPage.PROFILECOMP_REG);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, cpamanagersPage.LOGIN, driver);
		click(cpamanagersPage.LOGIN);
		/*
		 * waitforElement(60, cpamanagersPage.SETTINGS, driver); JavaScriptclick(driver,
		 * cpamanagersPage.SETTINGS); waitUntilElementInvisibe(180,
		 * configurePage.NPROGRESSBUSY_XPATH, driver); waitforElement(180,
		 * cpamanagersPage.VIEWALLUSERS, driver); click(cpamanagersPage.VIEWALLUSERS);
		 * waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		 */

	}

	public void verifyUsersInMyUsersPage(String eUserName, String eUserRoles) throws Exception {
		waitforElement(60, cpamanagersPage.SETTINGS, driver);
		JavaScriptclick(driver, cpamanagersPage.SETTINGS);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.VIEWALLUSERS, driver);
		click(cpamanagersPage.VIEWALLUSERS);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		int tRoCount = driver
				.findElements(By.xpath("//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody"))
				.size();

		for (int userRow = 1; userRow <= tRoCount; userRow++) {
			String aUserName = driver
					.findElement(By.xpath("//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody["
							+ userRow + "]/tr/td[4]"))
					.getText();
			if (eUserName.equals(aUserName)) {
				String aUserRoles = driver.findElement(
						By.xpath("//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody["
								+ userRow + "]/tr/td[9]"))
						.getText();
				if (!eUserRoles.equals(aUserRoles)) {
					System.out.println(
							"FAIL: " + "\n" + "ExpectedValue: " + eUserRoles + "\n" + "ActualValue:   " + aUserRoles);
				}
				break;
			}

		}

	}

	public void verifyCloudOSeriesIsNotDisplayed() throws Exception {
		CommonFunctions CF = new CommonFunctions();
		CF.VerifyObjectIsNotDisplayed("//label[contains(.,'Cloud O Series')]");
	}

	public void checkCloudOSeries() throws Exception {
		click(registrationPage.CloudOSeriestext);
	}

	public void verifyObjectIsCheckedAndDisabled() throws Exception {
		Thread.sleep(500);
		CommonFunctions CF = new CommonFunctions();
		CF.verifyObjectIsDisabled("//input[@id='include-cloud-o-series'][@ng-checked='true']");
		/*
		 * String kn =
		 * driver.findElement(By.xpath("//input[@id='include-cloud-o-series']")).getText
		 * (); System.out.println("kn---" + kn); boolean kn2 =
		 * driver.findElement(By.xpath("//input[@id='include-cloud-o-series']")).
		 * isEnabled(); System.out.println("kn2---" + kn2);
		 */

	}

	public void CompleteRegistrationwithoutfirstname() throws Exception {
		waitforElement(180, registrationPage.NEXTSTEP2, driver);
		if (VerifyObject(driver, registrationPage.NEXTSTEP2)) {
			click(registrationPage.NEXTSTEP2);
			Thread.sleep(4000);
		}
		Thread.sleep(20000);
	}

	public void CompleteRegistrationwithinvoice() throws Exception {
		waitforElement(180, registrationPage.ECWFinishandSave, driver);
		click(registrationPage.ECWFinishandSave);
		Thread.sleep(20000);
	}

	public void AdminsCreationwithsameemail(String role, String email) throws Exception {

		click(registrationPage.INVITEUSER);
		waitforElement(180, registrationPage.ADMININVITE, driver);
		click(registrationPage.ADMININVITE);
		waitforElement(180, registrationPage.ROLE, driver);
		selectByValue(registrationPage.ROLE, role);
		click(registrationPage.EMAIL);
		type(registrationPage.EMAIL, email);
		Thread.sleep(3000);
		click(registrationPage.SNDIVTE);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		Thread.sleep(3000);
		waitforElement(180, registrationPage.BACKLINK, driver);
		JavaScriptclick(driver, registrationPage.BACKLINK);
	}

	public void switchtoactivelogin(String user, String pwd, String switchuser) throws Exception {
		waitforElement(30, loginPage.USERNAME, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
		}
		if (VerifyObject(driver, loginPage.DASHBOARD) || VerifyObject(driver, qbo.manageSalesforms)) {
			Logout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user);
		} else {
			type(loginPage.USERNAME, user);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(10000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				loginPage.USERNAME.sendKeys(user);
				Thread.sleep(2000);
				loginPage.PASSWORD.sendKeys(pwd);
				Thread.sleep(2000);
				loginPage.CONTINUE.click();
				Thread.sleep(2000);
			}
		}
		if (VerifyObject(driver, loginPage.DASHBOARD)) {
			waitforElement(180, loginPage.DASHBOARD, driver);
		}
		LoginName = user;
		Reporter.log("Customer Portal User Name :" + LoginName);
		waitforElement(60, loginPage.ACCOUNTICON, driver);
		// loginPage.SELECTAVAILABLECLIENTS.sendKeys(switchuser);
		selectByValue(loginPage.SELECTAVAILABLECLIENTS, switchuser);
		Thread.sleep(2000);
		if (VerifyObject(driver, loginPage.TESTINSTANEENVTEXT)) {
			waitforElement(180, loginPage.TESTINSTANEENVTEXT, driver);
		}

	}

	public void forgotpwd(String email) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		driver.get(url);
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, email);
		} else {
			type(loginPage.USERNAME, email);
		}
		Thread.sleep(2000);
		JavaScriptclick(driver, loginPage.CONTINUE);
		Thread.sleep(2000);
		waitforElement(180, LP.FORGOTPWD, driver);
		JavaScriptclick(driver, LP.FORGOTPWD);
		// waitforElement(180, LP.EMAILLINKINFORGOTWD, driver);
		Thread.sleep(5000);
		waitforText(180, "//h1[contains(text(),'Forgot Your Password')]", driver);
		assertTrue(VerifyObject(driver, "//h1[contains(text(),'Forgot Your Password')]"));
		type(LP.EMAILADDRESSIN, email);
		JavaScriptclick(driver, loginPage.CONTINUE);
		waitforElement(180, LP.SUCCESSMSG, driver);
		Thread.sleep(5000);
		assertTrue(LP.SUCCESSMSG.isDisplayed());
	}

	public void deleteUserLogin(String user, String pwd) throws Exception {
		waitforElement(30, loginPage.USERNAME, driver);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
		}
		if (VerifyObject(driver, loginPage.DASHBOARD) || VerifyObject(driver, qbo.manageSalesforms)) {
			Logout();
			waitforElement(180, loginPage.USERNAME, driver);
		}
		waitforElement(180, loginPage.USERNAME, driver);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.USERNAME, user);
		} else {
			type(loginPage.USERNAME, user);
		}
		Thread.sleep(2000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			JavaScripttype(driver, loginPage.PASSWORD, pwd);
		} else {
			type(loginPage.PASSWORD, pwd);
		}
		Thread.sleep(3000);
		loginPage.LOGIN.click();
		Thread.sleep(2000);
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			JavaScriptclick(driver, loginPage.FORBIDDEN_LOGOUT);
			Thread.sleep(2000);
			driver.manage().deleteAllCookies();
			Thread.sleep(2000);
			driver.get(url);
			if (VerifyObject(driver, loginPage.USERNAME)) {
				loginPage.USERNAME.sendKeys(user);
				Thread.sleep(2000);
				loginPage.PASSWORD.sendKeys(pwd);
				Thread.sleep(2000);
				loginPage.LOGIN.click();
				Thread.sleep(2000);
			}
		}
		assertTrue(loginPage.USERDONTHAVEACCESS.isDisplayed());
	}

	public void CreateNewUserfromcalcParity(String Firstname, String LastName, String Emailid, String PhoneNumber,
			String AccountManagementRole, String VertexIndirecttaxrole, String Password, String ConfirmPassword)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		JavaScriptclick(driver, cpamanagersPage.SETTINGS);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.ADDNEWUSER, driver);
		click(cpamanagersPage.ADDNEWUSER);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.USEREMAIL, driver);
		type(cpamanagersPage.USEREMAIL, Emailid);
		waitforElement(180, cpamanagersPage.ACCOUNTMANAGEMENTROLE, driver);
		if (AccountManagementRole != "") {
			selectByValue(cpamanagersPage.ACCOUNTMANAGEMENTROLE, AccountManagementRole);
		}
		if (VertexIndirecttaxrole != "") {
			selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE, VertexIndirecttaxrole);
		}
		Thread.sleep(1000);
		click(cpamanagersPage.SENDINVITEUSER);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.COMPLETEREGISTRATIONLINK, driver);
		click(cpamanagersPage.COMPLETEREGISTRATIONLINK);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		PortalUserName = Emailid;
		System.out.println("CPA Admin name " + PortalUserName);
		waitforElement(180, cpamanagersPage.FIRSTNAME, driver);
		type(cpamanagersPage.FIRSTNAME, Firstname);
		// FirstName = Firstname;
		type(cpamanagersPage.LASTNAME, LastName);
		// lastname = LastName;
		click(cpamanagersPage.PHONENUMBER);
		type(cpamanagersPage.PHONENUMBER, PhoneNumber);
		type(cpamanagersPage.PASSWORD, Password);
		type(cpamanagersPage.CONFIRMPASSWORD, ConfirmPassword);
		Thread.sleep(1000);
		JavaScriptclick(driver, cpamanagersPage.PROFILECOMP_REG);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, cpamanagersPage.LOGIN, driver);
		click(cpamanagersPage.LOGIN);
	}

	public void changerolefunctionalitycalcParity(String Email, String UserRole) throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		JavaScriptclick(driver, cpamanagersPage.SETTINGS);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, professionalLinks.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(professionalLinks.VIEWALLUSERSLINKFROMCLIENT);
		Thread.sleep(10000);
		WebElement chnagerole = driver.findElement(
				By.xpath("//td[contains(.,'" + Email + "')]/../td[9]/div[1]/a[contains(.,'Change Role')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		Thread.sleep(2000);
		JavaScriptclick(driver, chnagerole);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, cpamanagersPage.USEREMAIL, driver);
		Thread.sleep(2000);
		waitforElement(180, cpamanagersPage.ACCOUNTMANAGEMENTROLE, driver);
		JavaScriptclick(driver, cpamanagersPage.ACCOUNTMANAGEMENTROLE);
		selectByValue(cpamanagersPage.ACCOUNTMANAGEMENTROLE, "Admin");
		Thread.sleep(3000);
		if (!UserRole.isEmpty()) {
			String Role_type = UserRole;
			switch (Role_type) {
			case "Admin":
				selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE, "Admin");
				break;
			case "Read Only":
				selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE, "Read Only");
				break;
			case "ECM Only":
				selectByValue(cpamanagersPage.VERTEXCLOUDINDIRECTTAXROLE, "ECM Only");
				break;
			}
		}
		assertTrue(UM.Changerolesavebutton.isDisplayed());
		assertTrue(UM.Changerolecancelbutton.isDisplayed());
		click(UM.Changerolesavebutton);
		Thread.sleep(5000);
		scrollUp(driver);
		waitforElement(180, cpamanagersPage.RETURNTOUSERS, driver);
		click(cpamanagersPage.RETURNTOUSERS);
		Thread.sleep(5000);
		WebElement newrole = driver.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td[7]"));
		System.out.println(newrole.getText());
	}

	public void DeleteuserforcalcParity(String Email) throws Exception {
		Usermanagentscreen UM = PageFactory.initElements(driver, Usermanagentscreen.class);
		JavaScriptclick(driver, cpamanagersPage.SETTINGS);
		waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, professionalLinks.VIEWALLUSERSLINKFROMCLIENT, driver);
		click(professionalLinks.VIEWALLUSERSLINKFROMCLIENT);
		Thread.sleep(10000);
		WebElement delete = driver
				.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td/a[contains(.,'Delete')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(100,50)");
		// JavaScriptscrolltoView(driver, delete);
		Thread.sleep(2000);
		JavaScriptclick(driver, delete);
		waitforElement(180, UM.DeleteheaderText, driver);
		assertTrue(UM.cancellink.isDisplayed());
		Thread.sleep(2000);
		JavaScriptclick(driver, UM.removecustomerlink);
		Thread.sleep(2000);
		waitforElement(180, UM.CreateNewUserlink, driver);
		WebElement deletedstatus = driver
				.findElement(By.xpath("//td[contains(.,'" + Email + "')]/../td[contains(.,'Deleted')]"));
		assertTrue(deletedstatus.isDisplayed());
		Thread.sleep(5000);
	}

	public void cancelledcalcParity() throws Exception {
		PageFactory.initElements(driver, calcparity.class);
		waitforElement(30, loginPage.DASHBOARD, driver);
		assertTrue(loginPage.DASHBOARD.isDisplayed());
		// assertFalse(!CL.INDIRECTTAXOSERIESLINK.isDisplayed());
	}

	public void AdminLogout() throws Exception {
		waitforElement(180, loginPage.DASHBOARD, driver);
		if (VerifyObject(driver, loginPage.VTMSACCOUNT)) {
			waitforElement(180, loginPage.VTMSACCOUNT, driver);
			boolean report = false;
			do {
				JavaScriptclick(driver, loginPage.ACCOUNTICON);
				Thread.sleep(6000);
				if (loginPage.VTMSLOGOUT.isDisplayed() == true) {
					JavaScriptclick(driver, loginPage.VTMSLOGOUT);
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
					click(loginPage.FORBIDDEN_LOGOUT);
					waitforElement(60, loginPage.LOGIN, driver);
				}
			}
			// assertTrue(VerifyObject(driver, LP.LOGIN));
		} else if (VerifyObject(driver, loginPage.ECLogout)) {
			click(loginPage.ECLogout);
			waitforText(180, loginPage.ECPortalText, driver);
		} else {
			JavaScriptclick(driver, loginPage.ACCOUNTICON);
			Thread.sleep(6000);
			JavaScriptclick(driver, loginPage.VTMSLOGOUT);
			Thread.sleep(2000);
		}
	}

	/*
	 * this method will take all the billing details and fill in their respective
	 * fields
	 */
	public void NewUserBillingWithFirstName(String billingCountry, String billingAddress, String billingCity,
			String billingState, String billingZip, String creditcardNumber, String expirationMonth,
			String expirationYear, String CSV, String firstName) throws Exception {
		RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
		Thread.sleep(10000);
		if (VerifyObject(driver, registrationPage.UPDATEBILLING)) {
			click(registrationPage.UPDATEBILLING);
			waitUntilElementInvisibe(180, configurePage.NPROGRESSBUSY_XPATH, driver);
		}
		selectByValue(registrationPage.BILLINGCOUNTRYDDRPDOWN, billingCountry);
		type(registrationPage.BILLINGADDRESS1, billingAddress);
		type(registrationPage.BILLINGCITY, billingCity);
		Thread.sleep(10000);
		selectByValue(registrationPage.BILLINGSTATE, billingState);
		click(registrationPage.BILLINGZIP);
		JavaScripttype(driver, registrationPage.BILLINGZIP, billingZip);
		sendkeysTab(registrationPage.BILLINGZIP);
		click(registrationPage.BILLINGCITY);
		Thread.sleep(3000);
		if (registrationPage.CREDITCARDPAYMENT.isDisplayed()) {
			click(registrationPage.CREDITCARDPAYMENT);
		}
		click(registrationPage.CREDITCARD);
		clear(registrationPage.CREDITCARD);
		click(registrationPage.CREDITCARD);
		type(registrationPage.CREDITCARD, creditcardNumber);
		if (Browser.equalsIgnoreCase("Firefox")) {
			JavaScripttypespace(driver, registrationPage.CREDITCARD, creditcardNumber);
			Thread.sleep(3000);
			sendkeybackSpace(registrationPage.CREDITCARD);
		}
		selectByValue(registrationPage.EXPMONTH, expirationMonth);
		selectByValue(registrationPage.EXPYEAR, expirationYear);
		type(registrationPage.CVN, CSV);
		click(registrationPage.BILLINGNAME);
		clear(registrationPage.BILLINGNAME);
		type(registrationPage.BILLINGNAME, firstName);
		if (VerifyObject(driver, registrationPage.SUBMITBILLINGINFOBUTTON)) {
			click(registrationPage.SUBMITBILLINGINFOBUTTON);
		} else {
			click(registrationPage.SUBMIT);
		}
		Thread.sleep(7000);
		driver.get(url);
		waitforElement(90, registrationPage.DASHBOARDLINK, driver);
		assertTrue(registrationPage.DASHBOARDLINK.isDisplayed());
	}
}
