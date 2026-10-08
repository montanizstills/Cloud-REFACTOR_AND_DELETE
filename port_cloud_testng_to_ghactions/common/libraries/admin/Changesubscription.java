package common.libraries.admin;

import java.awt.Robot;
import java.time.Duration;
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
import common.pages.DowngradePremiumtoPro;
import common.pages.FreeUserLinks;
import common.pages.MySubscriptionPage;
import common.pages.ProfessionalUserLinks;
import common.pages.RegistrationPage;
import common.pages.TestInstanceSandbox;
import common.pages.UpgradeProToPremium;
import common.pages.UploadAdjustmentFile;
import common.pages.VertexSMBCustomers;
import common.utilities.CloudData;

public class Changesubscription extends CommonFunctions {

	private WebDriver driver;

	public Changesubscription(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);

	public void ChangeSubscription(String Clientname, String subscription, String firstName, String feature,
			String divale) throws Exception {
		String UserName;
		UpgradeProToPremium UTP = PageFactory.initElements(driver, UpgradeProToPremium.class);
		DowngradePremiumtoPro DPP = PageFactory.initElements(driver, DowngradePremiumtoPro.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		Thread.sleep(2000);
		waitforElement(90, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, Clientname);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + Clientname + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a")).getText();
		System.out.println(CName);
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
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(30, VSC.EXPORT, driver);
		CNTID = driver.findElement(By.xpath("//div[@class='span3 ng-binding'][contains(.,'Cloud Customer Id:')]"))
				.getText().split(":")[1].trim();
		JavaScriptclick(driver, VSC.SMBADMIN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.EDITCUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.EDITCUSTOMERSUBSCRIPTION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.CUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.CUSTOMERSUBSCRIPTION);
		waitforElement(180, MSP.SAVEBUTTON, driver);
		waitforElement(180, DPP.PROFESSIONAL, driver);
		waitforElement(180, MSP.PREMIUMSUB, driver);
		if (subscription.equalsIgnoreCase("Professional")) {
			waitforElement(180, DPP.PROFESSIONAL, driver);
			click(DPP.PROFESSIONAL);
		}
		if (subscription.equalsIgnoreCase("Premium")) {
			waitforElement(180, MSP.PREMIUMSUB, driver);
			click(MSP.PREMIUMSUB);
		}

		String[] features = feature.split(",");
		if (!feature.isEmpty()) {
			for (int i = 0; i < features.length; i++) {
				switch (features[i].toUpperCase()) {

				case "TAXCALCONLY":
					click(MSP.TAXCALCONLY_SUB);
					break;
				case "TAXCALCULATIONSANDRETURNS":
					click(MSP.TAXCALCRETURNS_SUB);
					break;
				case "RETURNSONLY":
					click(MSP.RETURNSONLY_SUB);
					break;
				case "RATEFILE":
					click(MSP.RATEFILECHECKBOX);
					break;
				case "REALTIMERATESERVICE":
					click(MSP.REALTIME);
					break;
				case "PAYROLLTAXCALCULATION":
					waitforElement(180, MSP.payrollcalc, driver);
					JavaScriptclick(driver,MSP.payrollcalc);
					break;
				case "PAYROLLADDRESSCLEANSING":
					waitforElement(180, MSP.payrolladdresscleansing, driver);
					JavaScriptclick(driver,MSP.payrolladdresscleansing);
					break;
				case "TAXABILITYIMPACTREPORT":
					click(MSP.TAXIMPACTREPORT_SUB);
					break;
				case "ACCELERATOR-ORACLE ERP CLOUD":
					click(MSP.ACCELERATORORACLEERPCLOUD);
					break;
				case "ADD_TESTINSTANCE":
					click(MSP.testInstanceradioBtn);
					break;
				case "PAYROLLTEST":
					JavaScriptclick(driver,MSP.PAYROLLTEST);
					break;
				case "DATAINTEGRITY":
					click(RP.ADMINDATAINTEGRITY);
					driver.findElement(
							By.xpath("//select[@id='data-integrity-storage-region']/option[@selected='selected']"))
							.getText();
					click(RP.ADMINDATASTORAGRREGION);
					break;
				default:
				}
			}
		}
		if (!divale.isEmpty()) {
			DIRegion = divale;
			selectByValue(RP.ADMINDATASTORAGRREGION, DIRegion);
		}
		if ((divale.equals("US") || divale.equals("EU")) && (divale.isEmpty())) {
			if (Environment.equalsIgnoreCase("DEV") || Environment.equalsIgnoreCase("DATAINTEGRITY")) {
				driver.findElement(
						By.xpath("//select[@id='data-integrity-storage-region']/option[@selected='selected']"))
						.getText();
			}
		}
		if (feature.equalsIgnoreCase("Remove_DI")) {
			Thread.sleep(2000);
			click(RP.ADMINDATAINTEGRITY);
			Thread.sleep(2000);
		}
		if (feature.equalsIgnoreCase("LegacyReport")) {
			click(MSP.LEGACYTRANSACTIONREPORT);
		}
		scrollDown(driver);
		waitforElement(180, MSP.SAVEBUTTON, driver);
		JavaScriptclick(driver, MSP.SAVEBUTTON);
		waitforElement(180, UTP.UPGRADEBUTTON, driver);
		JavaScriptclick(driver, UTP.UPGRADEBUTTON);
		Thread.sleep(5000);
		if (feature.equalsIgnoreCase("Add_TestInstance")) {
			JavaScriptclick(driver, RP.INVITEUSER);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, RP.SEARCH, driver);
			if (VertexFirstName != null) {
				type(RP.SEARCH, VertexFirstName);
				Thread.sleep(2000);
				UserName = driver
						.findElement(
								By.xpath("//table/tbody/tr/td[contains(text(),'" + VertexFirstName + "')]/../td[6]"))
						.getText();
			} else {
				type(RP.SEARCH, firstName);
				Thread.sleep(2000);
				UserName = driver
						.findElement(By.xpath("//table/tbody/tr/td[contains(text(),'" + firstName + "')]/../td[6]"))
						.getText();
			}
			if (UserName.contains(".TI")) {
				String[] data = UserName.split(".TI");
				UserName = data[0];
			}
			userregistration.Logout();
			Thread.sleep(2000);
			if (Environment.equalsIgnoreCase("DEV") || Environment.equalsIgnoreCase("AWSDEV")) {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			} else if (Environment.equalsIgnoreCase("STAGE") || Environment.equalsIgnoreCase("AWSSTAGE")) {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			} else if (Environment.equalsIgnoreCase("AWSPROD")) {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			} else {
				xGetTestInstanceSetPasswordURL(UserName, Environment);
			}
			Thread.sleep(7000);
			driver.get(TestInstanceSetPwdURL);
			System.out.println(TestInstanceSetPwdURL);
			TestInstanceclientName = VertexClientName + ".TI";
			System.out.println("Test Instance Client name :" + TestInstanceclientName);
			Thread.sleep(2000);
			type(TI.testInstanceUsername, TestInstanceUserName);
			type(TI.testInstancepassword, CloudData.get_data("Password_TI"));
			Thread.sleep(2000);
			type(TI.testInstanceconfirmpassword, CloudData.get_data("Password_TI"));
			Thread.sleep(2000);
			click(TI.setPassword);
		}
		/*
		 * if (window.equals("exists")) { driver.close(); Thread.sleep(5000);
		 * driver.switchTo().window(parentwindow1); }
		 */
		Thread.sleep(2000);

	}

	public void VerifyOracleERPCloudafterDowngrade(String Subscription, String feature) throws Exception {
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
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN, driver);
		assertTrue(PUL.ACCELERATORORACLEERPCLOUD.isDisplayed());
		WebElement downgradetext = driver.findElement(By
				.xpath("//text[contains(.,'your subscription will change to " + Subscription + ": " + feature + "')]"));
		assertTrue(downgradetext.isDisplayed());
	}

	@SuppressWarnings("unused")
	public void UpgradetoTI(String Clientname, String Username, String env) throws Exception {
		UpgradeProToPremium UTP = PageFactory.initElements(driver, UpgradeProToPremium.class);
		DowngradePremiumtoPro DPP = PageFactory.initElements(driver, DowngradePremiumtoPro.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		String parentwindow1 = null, window = null;
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		Thread.sleep(2000);
		waitforElement(90, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, Clientname);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + Clientname + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a")).getText();
		System.out.println(CName);
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
		Thread.sleep(2000);
		waitforElement(90, VSC.EXPORT, driver);
		JavaScriptclick(driver, VSC.SMBADMIN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.EDITCUSTOMERSUBSCRIPTION, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, VSC.EDITCUSTOMERSUBSCRIPTION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.CUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.CUSTOMERSUBSCRIPTION);
		waitforElement(180, MSP.SAVEBUTTON, driver);
		waitforElement(180, DPP.PROFESSIONAL, driver);
		waitforElement(180, MSP.PREMIUMSUB, driver);
		click(MSP.testInstanceradioBtn);
		waitforElement(180, MSP.SAVEBUTTON, driver);
		JavaScriptclick(driver, MSP.SAVEBUTTON);
		waitforElement(180, UTP.UPGRADEBUTTON, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, UTP.UPGRADEBUTTON);
		Thread.sleep(5000);
		JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		Thread.sleep(2000);
		JavaScriptclick(driver, VSC.VTMSACCOUNT);
		JavaScriptclick(driver, VSC.VTMSLOGOUT);
		Thread.sleep(2000);
		System.out.println(env);
		System.out.println(Username);
		/*
		 * xGetTestInstanceSetPasswordURL(Username, env); Thread.sleep(7000);
		 * driver.get(TestInstanceSetPwdURL); System.out.println(TestInstanceSetPwdURL);
		 * TestInstanceclientName = Clientname + ".TI";
		 * System.out.println("Test Instance Client name :" + TestInstanceclientName);
		 * Thread.sleep(2000); type(TI.testInstanceUsername, Username + ".TI");
		 * type(TI.testInstancepassword, "Password!1"); Thread.sleep(2000);
		 * type(TI.testInstanceconfirmpassword, "Password!1"); Thread.sleep(2000);
		 * click(TI.setPassword);
		 * 
		 * if (window == "exists") { driver.close(); Thread.sleep(5000);
		 * driver.switchTo().window(parentwindow1); } Thread.sleep(2000);
		 * 
		 */ }

	public void VerifyOracleERPCloud(String VerifyERPOracle) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(180, PUL.SETTINGSLINK, driver);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		Thread.sleep(5000);
		waitforElement(180, PUL.Subscription, driver);
		if (VerifyERPOracle.equalsIgnoreCase("Yes")) {
			assertTrue(PUL.ACCELERATORORACLEERPCLOUD.isDisplayed());
		}

	}

	public void VerifySubscriptionInBilling(String VerifySubscription, String Subscription) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(180, PUL.SETTINGSLINK, driver);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		Thread.sleep(5000);
		waitforElement(180, PUL.Subscription, driver);
		if (VerifySubscription.equalsIgnoreCase("Standard")) {
			assertTrue(PUL.Subscription.isDisplayed());
			String TxtContent = driver.findElement(By.xpath("//strong[@class='ng-binding'][contains(.,'Standard')]"))
					.getText();
			assertEquals(TxtContent, Subscription);
		} else if (VerifySubscription.equalsIgnoreCase("Professional")) {
			assertTrue(PUL.Subscription.isDisplayed());
			String TxtContent = driver
					.findElement(By.xpath("//strong[@class='ng-binding'][contains(.,'Professional: Returns Only')]"))
					.getText();
			assertEquals(TxtContent, Subscription);
		} else if (VerifySubscription.equalsIgnoreCase("Premium")) {
			assertTrue(PUL.Subscription.isDisplayed());
			String TxtContent = driver
					.findElement(By.xpath("//strong[@class='ng-binding'][contains(.,'Premium: Returns Only')]"))
					.getText();
			assertEquals(TxtContent, Subscription);
		}

	}

	public void VerifyMultipleClients() throws Exception {

		selectByIndex(driver.findElement(By.id("SelectedTenantId")), 0);
		waitforElement(180, driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")), driver);
		click(driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")));
		Thread.sleep(5000);
		selectByIndex(driver.findElement(By.id("SelectedTenantId")), 1);
		waitforElement(180, driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")), driver);
		click(driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")));
		Thread.sleep(5000);
		selectByIndex(driver.findElement(By.id("SelectedTenantId")), 2);
		waitforElement(180, driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")), driver);
		click(driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")));
		Thread.sleep(5000);

	}

	public void VerifyMultipleFranchiseClients() throws Exception {

		selectByIndex(driver.findElement(By.id("SelectedTenantId")), 0);
		waitforElement(180, driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")), driver);
		click(driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")));
		Thread.sleep(5000);
		selectByIndex(driver.findElement(By.id("SelectedTenantId")), 1);
		waitforElement(180, driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")), driver);
		click(driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")));
		Thread.sleep(5000);
	}
	
	public void SwitchToTIClients() throws Exception {
		selectByIndex(driver.findElement(By.id("SelectedTenantId")), 0);
		waitforElement(180, driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")), driver);
		click(driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")));
		Thread.sleep(5000);
		selectByIndex(driver.findElement(By.id("SelectedTenantId")), 1);
		waitforElement(180, driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")), driver);
		click(driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")));
		Thread.sleep(5000);
	}
	
	public void SwitchToClients() throws Exception {
		selectByIndex(driver.findElement(By.id("SelectedTenantId")), 1);
		waitforElement(180, driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")), driver);
		click(driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")));
		Thread.sleep(5000);
		selectByIndex(driver.findElement(By.id("SelectedTenantId")), 0);
		waitforElement(180, driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")), driver);
		click(driver.findElement(By.xpath("//span[@class='nav-text'][contains(.,'Dashboard')]")));
		Thread.sleep(5000);
		
	}

	public void VerifyadminTI(String Username, String Clientname, String env) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		Thread.sleep(2000);
		waitforElement(90, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, Clientname);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + Clientname + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a")).getText();
		System.out.println(CName);
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
		Thread.sleep(2000);
		waitforElement(90, VSC.EXPORT, driver);
		JavaScriptclick(driver, VSC.SMBADMIN);
		waitforElement(80, VSC.Oracle_ERP_Cloud, driver);
		JavaScriptscrolltoView(driver, VSC.Oracle_ERP_Cloud);
		assertTrue(VSC.Oracle_ERP_Cloud.isDisplayed());
	}

	public void VerifyReadOuserinadmin(String Username, String Clientname, String env) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		Thread.sleep(2000);
		waitforElement(90, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, Clientname);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + Clientname + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a")).getText();
		System.out.println(CName);
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
		Thread.sleep(2000);
		waitforElement(90, VSC.EXPORT, driver);
		JavaScriptclick(driver, VSC.SMBADMIN);
		waitforElement(80, VSC.Oracle_ERP_Cloud, driver);
		JavaScriptscrolltoView(driver, VSC.show_password);
		click(VSC.show_password);
		waitforElement(80, VSC.RO_password, driver);
		assertTrue(VSC.RO_password.isDisplayed());
	}

	public void VerifyCancelSubscription(String ClientName, String Dont_Cancel, String Cancel,
			String Initiated_PendingBill_Client, String DashBoard_Title, String EditCompany_Title,
			String UpdateBilling_Title, String UserName) throws Exception {
		String CurrentDate = xGetDate("M/1/yyyy");
		String d = xMonthsFirstDate(CurrentDate, 1, "M/d/yyyy");
		System.out.println("first date of month is " + d);
		new Robot();
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		Thread.sleep(5000);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		assertTrue(VSC.SMBCUSTNAMESB.isDisplayed());
		type(VSC.SMBCUSTNAMESB, ClientName);
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
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.EXPORT, driver);
		if (Dont_Cancel.equalsIgnoreCase("Yes")) {
			waitforElement(180, MSP.CANCELCUSTOMER, driver);
			JavaScriptclick(driver, MSP.CANCELCUSTOMER);
			waitforElement(180, MSP.CANCELREQUESTREASON, driver);
			JavaScriptclick(driver, MSP.NOCANCELREQUEST);
			Thread.sleep(2000);
			// waitUntilElementInvisibe(180,"//strong[text()='No, cancel request']",driver);
			if (!VerifyObject(driver, MSP.NOCANCELREQUEST)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
		if (Cancel.equalsIgnoreCase("Yes")) {
			if (!Initiated_PendingBill_Client.equalsIgnoreCase("Yes")) {
				waitforElement(180, MSP.CANCELCUSTOMER, driver);
				JavaScriptclick(driver, MSP.CANCELCUSTOMER);
				waitforElement(180, MSP.CANCELREQUESTREASON, driver);
				Thread.sleep(2000);
				type(MSP.CANCELREQUESTREASON, "Testing");
				Thread.sleep(2000);
				click(MSP.YESCOMPLETECANCEL);
				Thread.sleep(2000);
				waitUntilElementInvisibe(180, "//a[text()='Yes, Complete Cancel']", driver);
				Thread.sleep(5000);
				if (!VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
					assertTrue(true);
				} else {
					assertFalse(false);
				}

				driver.navigate().refresh();
				waitforElement(180, MSP.CANCELCUSTOMERDISABLED, driver);
				Thread.sleep(5000);
				assertTrue(MSP.CANCELCUSTOMERDISABLED.isDisplayed());
				assertTrue(VerifyObject(driver,
						"//div[3]/div/span[contains(.,'Subscription:')]/../../div[contains(.,'Service Cancellation / End')]"));
			} else {
				Thread.sleep(5000);
				assertTrue(MSP.CANCELCUSTOMERDISABLED.isDisplayed());
			}
		}

		if (!Initiated_PendingBill_Client.equalsIgnoreCase("Yes")) {
			if (!(Environment.equalsIgnoreCase("Platform_DEV") || Environment.equalsIgnoreCase("CONFIG")
					|| Environment.equalsIgnoreCase("DEV") || Environment.equalsIgnoreCase("QA"))) {
				userregistration.Logout();
				driver.get(Environment);
				Thread.sleep(5000);
				userregistration.UserLogin(UserName, "Password!1");
				CancelledUserLinks(DashBoard_Title, EditCompany_Title, UpdateBilling_Title);
			}
		}
	}

	public void CancelledUserLinks(String DashBoard_Title, String EditCompany_Title, String UpdateBilling_Title)
			throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		new Robot();
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(FUL.DASHBOARDLINK);
		waitforElement(180, PUL.UPDATEANALYSIS, driver);
		assertTrue(DashBoard_Title.equalsIgnoreCase(driver.getTitle()));
		// *******Verifying Rate File link not Displaying****//
		if (!VerifyObject(driver, FUL.RATEFILE)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		// *******Verifying Lookup Tax Rate link not Displaying****//
		if (!VerifyObject(driver, FUL.LOOKUPTAXRATESLINK)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		Thread.sleep(2000);
		// **Verifying Settings links
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, FUL.EDITCOMPANYLINK);
		waitforElement(180, PUL.SAVE, driver);
		assertTrue(EditCompany_Title.equalsIgnoreCase(driver.getTitle()));
		// Edit My Company Page Verification
		assertTrue(PUL.NAME.isDisplayed());
		assertTrue(PUL.PHONE.isDisplayed());
		Thread.sleep(5000);
		waitforElement(180, PUL.SettingsHover, driver);
		JavaScriptMousehover(driver, PUL.SettingsHover);
		Thread.sleep(2000);
		JavaScriptclick(driver, FUL.UPDATEBILLINGLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.SUBMITBILINFO, driver);
		assertTrue(UpdateBilling_Title.equalsIgnoreCase(driver.getTitle()));
		if (!VerifyObject(driver, PUL.CONTACTSUPPORTLINK)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		scrollUp(driver);
		click(FUL.DASHBOARDLINK);
		waitforElement(180, PUL.UPDATEANALYSIS, driver);
		userregistration.Logout();
		driver.get(url);
		Thread.sleep(5000);
		userregistration.UserLogin(adminusername, adminpassword);
	}

	public void InitiatedUserCreation(String Role, String Account_Type, String ValueAddReseller, String Clientname,
			String firstname, String lastname, String OraclePartyNumber, String Email, String User_Country,
			String Phone, String Address1, String City, String State, String Zip, String Subscription_Type,
			String Subscription_Options, String Feature, String Additional_Features, String FixedPriceValue,
			String FixedPriceBillingType, String Negocontract_File, String Negotiatednum, String Password,
			String Username) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		UserEmailLink(Role, Account_Type, ValueAddReseller, Clientname, firstname, lastname, OraclePartyNumber, Email,
				User_Country, Phone, Address1, City, State, Zip, Subscription_Type, Subscription_Options, Feature,
				Additional_Features, FixedPriceValue, FixedPriceBillingType, Negocontract_File, Negotiatednum, Password,
				Username);
		driver.navigate().back();
		JavaScriptclick(driver, PUL.DASHBOARDLINK);

	}

	public void PendinBillingUsercreation(String Role, String Account_Type, String ValueAddReseller, String Clientname,
			String firstname, String lastname, String OraclePartyNumber, String Email, String User_Country,
			String Phone, String Address1, String City, String State, String Zip, String Subscription_Type,
			String Subscription_Options, String Feature, String Additional_Features, String FixedPriceValue,
			String FixedPriceBillingType, String Negocontract_File, String Negotiatednum, String Password,
			String Username) throws Exception {
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		UserEmailLink(Role, Account_Type, ValueAddReseller, Clientname, firstname, lastname, OraclePartyNumber, Email,
				User_Country, Phone, Address1, City, State, Zip, Subscription_Type, Subscription_Options, Feature,
				Additional_Features, FixedPriceValue, FixedPriceBillingType, Negocontract_File, Negotiatednum, Password,
				Username);
		if (VerifyObject(driver, RP.IAGREE_BASIC)) {
			click(RP.IAGREE_BASIC);
		}
		if (VerifyObject(driver, RP.IAGREETC2)) {
			click(RP.IAGREETC2);
		}
		if (VerifyObject(driver, RP.NEXTSTEP2)) {
			click(RP.NEXTSTEP2);
		}
		Thread.sleep(7000);
		driver.manage().deleteAllCookies();
		driver.get(Environment);
		Thread.sleep(2000);
		driver.get(Environment + "/Admin");
		if (driver.getTitle().equalsIgnoreCase("Forbidden | Vertex Cloud")) {
			driver.findElement(By.xpath("//a[contains(.,'please signout and login again')]")).click();
		}
		if (VerifyObject(driver, PUL.DASHBOARDLINK)) {
			userRegistration.Logout();
		}
	}

	public void UserEmailLink(String Role, String Account_Type, String ValueAddReseller, String Clientname,
			String firstname, String lastname, String OraclePartyNumber, String Email, String User_Country,
			String Phone, String Address1, String City, String State, String Zip, String Subscription_Type,
			String Subscription_Options, String Feature, String Additional_Features, String FixedPriceValue,
			String FixedPriceBillingType, String Negocontract_File, String Negotiatednum, String Password,
			String Username) throws Exception {
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		UploadAdjustmentFile UAF = PageFactory.initElements(driver, UploadAdjustmentFile.class);
		String AdjustmentFilePath = xUploadAdjustmentFile("NegotiatedCont");
		String AutoITpath = xAutoIT();
		waitforElement(180, RP.INVITEUSER, driver);
		JavaScriptclick(driver, RP.INVITEUSER);
		waitforElement(180, RP.ADMININVITE, driver);
		waitforElement(180, RP.EXPORT, driver);
		click(RP.ADMININVITE);
		waitforElement(180, RP.ROLE, driver);
		selectByValue(RP.ROLE, Role);
		Thread.sleep(5000);
		sendkeysTab(RP.ROLE);
		Thread.sleep(5000);
		selectByValue(RP.ACCOUNTTYPE, Account_Type);
		sendkeysTab(RP.ACCOUNTTYPE);
		selectByValue(RP.VARNAMES, ValueAddReseller);
		VertexClientName = Clientname;
		waitforElement(180, RP.CLIENT, driver);
		type(RP.CLIENT, firstname);
		type(RP.FIRSTNAME, firstname);
		type(RP.LASTNAME, lastname);
		type(RP.OraclePartyNumber, OraclePartyNumber);
		VertexEmail = Email;

		type(RP.EMAIL, VertexEmail);
		if (User_Country != "") {
			new Select(driver.findElement(By.xpath(RP.NEWUSERCOUNTRY_XPATH)));
			selectByValue(RP.NEWUSERCOUNTRY, User_Country);
		}
		if (User_Country.equalsIgnoreCase("UNITED STATES") || User_Country.equalsIgnoreCase("CANADA")) {
			click(RP.PHONE);
			Thread.sleep(1000);
			JavaScripttype(driver, RP.PHONE, Phone);
			sendkeysTab(RP.PHONE);
		} else {
			click(RP.INTERNAIONAL_PHONENUMBER);
			Thread.sleep(1000);
			JavaScripttype(driver, RP.INTERNAIONAL_PHONENUMBER, Phone);
			sendkeysTab(RP.INTERNAIONAL_PHONENUMBER);
		}
		String User_Address1 = Address1;
		if (Address1 != "") {
			JavaScriptclick(driver, RP.NEWUSERSTREETADDRESS1);
			type(RP.NEWUSERSTREETADDRESS1, User_Address1);
		}
		String User_City1 = City;
		if (City != "") {
			click(RP.NEWUSERCITY);
			type(RP.NEWUSERCITY, User_City1);
		}
		if (User_Country.equalsIgnoreCase("UNITED STATES") || User_Country.equalsIgnoreCase("CANADA")) {
			if (State != "") {
				selectByValue(RP.NEWUSERSTATE, State);
			}
		}
		if (!(User_Country.equalsIgnoreCase("UNITED STATES") || User_Country.equalsIgnoreCase("CANADA"))) {
			type(RP.InternationalState, State);
		}

		if (Zip != "") {
			if (!User_Country.equalsIgnoreCase("UNITED STATES") || User_Country.equalsIgnoreCase("CANADA")) {
				type(RP.NEWUSERZIP, Zip);
			} else {
				click(RP.NEWUSERZIP);
				JavaScripttype(driver, RP.NEWUSERZIP, Zip);
				sendkeysTab(RP.NEWUSERZIP);
			}
		}

		selectByValue(RP.VARSUBSCRIPTION1, Subscription_Type);
		Thread.sleep(3000);
		if (Subscription_Type.equalsIgnoreCase("PROFESSIONAL")) {
			if (Subscription_Options != "") {
				selectByValue(RP.VARSUBSCRIPTION, Subscription_Options);
			}
		}
		if (Subscription_Type.equalsIgnoreCase("PREMIUM")) {
			if (Subscription_Options != "") {
				selectByValue(RP.PREMIUMSUBSCRIPTIONSELECT, Subscription_Options);
			}
		}
		if (Feature.equalsIgnoreCase("RateFile")) {
			click(RP.VARRATEFILE);
		}
		if (Feature.equalsIgnoreCase("TaxabilityImpactReport")) {
			click(RP.VARTAXIMPACTREPORT);
		}
		if (Additional_Features != "") {
			if (Additional_Features.equalsIgnoreCase("RealTimeRateService")) {
				click(RP.RTRS);
			} else if (Additional_Features.equalsIgnoreCase("RateFile")) {
				click(RP.VARRATEFILE);
			} else if (Additional_Features.equalsIgnoreCase("RateFile+RealTimeRateService")) {
				click(RP.VARRATEFILE);
				click(RP.RTRS);
			}
		}
		if (Feature.equalsIgnoreCase("Fixed_Price")) {
			if (VerifyObject(driver, RP.FIXEDPRICE)) {
				JavaScriptclick(driver, RP.FIXEDPRICE);
				waitforElement(30, RP.FIXEDPRICEVALUE, driver);
				type(RP.FIXEDPRICEVALUE, FixedPriceValue);
				selectByValue(RP.FIXEDPRICESCHEDULETYPE, FixedPriceBillingType);
			}
		}
		if (Feature.equalsIgnoreCase("NegotiatedContract")) {
			if (VerifyObject(driver, RP.NEGOCONTRACTNUM)) {
				String negofile = Negocontract_File;
				waitforElement(90, RP.NEGOCONTRACTNUM, driver);
				if ((Browser.equalsIgnoreCase("Chrome")) || (Browser.equalsIgnoreCase("IE"))) {
					click(UAF.BROWSE);
				} else {
					JavaScriptclick(driver, UAF.BROWSE);
				}
				Thread.sleep(2000);
				Browser(driver, Browser, AutoITpath, AdjustmentFilePath, negofile, ".pdf");
				Thread.sleep(5000);
				type(RP.NEGOCONTRACTNUM, Negotiatednum);
				Thread.sleep(3000);
			}
		}
		JavaScriptclick(driver, RP.SNDIVTE);
		Thread.sleep(3000);
		if (VerifyObject(driver, RP.PhoneErrorText) || VerifyObject(driver, RP.ZipCodeErrorText)
				|| VerifyObject(driver, RP.INTERNATIONAL_PHONE_ERROR)) {
			boolean report = false;
			do {
				if (User_Country.equalsIgnoreCase("UNITED STATES") || User_Country.equalsIgnoreCase("CANADA")) {
					click(RP.PHONE);
					RP.PHONE.sendKeys(Keys.CONTROL + "a");
					RP.PHONE.sendKeys(Keys.CONTROL + "x");
					Thread.sleep(2000);
					sendkeysTab(RP.PHONE);
					Thread.sleep(2000);
					click(RP.PHONE);
					RP.PHONE.sendKeys(Keys.CONTROL + "v");
					Thread.sleep(2000);
				} else {
					click(RP.INTERNAIONAL_PHONENUMBER);
					RP.INTERNAIONAL_PHONENUMBER.sendKeys(Keys.CONTROL + "a");
					RP.INTERNAIONAL_PHONENUMBER.sendKeys(Keys.CONTROL + "x");
					Thread.sleep(2000);
					RP.INTERNAIONAL_PHONENUMBER.sendKeys(Keys.CONTROL + "v");
					Thread.sleep(2000);
				}
				click(RP.NEWUSERZIP);
				RP.NEWUSERZIP.sendKeys(Keys.CONTROL + "a");
				RP.NEWUSERZIP.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				sendkeysTab(RP.NEWUSERZIP);
				Thread.sleep(2000);
				click(RP.NEWUSERZIP);
				RP.NEWUSERZIP.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				sendkeysTab(RP.NEWUSERZIP);
				Thread.sleep(2000);
				if (!VerifyObject(driver, RP.PhoneErrorText) || VerifyObject(driver, RP.ZipCodeErrorText)) {
					click(RP.SNDIVTE);
					report = true;
				}
			} while (report == false);
		}
		Thread.sleep(3000);
		waitforElement(180, RP.USERLINK, driver);
		JavaScriptclick(driver, RP.USERLINK);
		waitUntilElementInvisibe(180, RP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(5000);
		if (!VerifyObject(driver, RP.USERNAME)) {
			driver.navigate().refresh();
		}
		waitforElement(180, RP.USERNAME, driver);
		String Password1 = Password;
		String PasswordConfirm1 = Password;

		if ((Browser.equalsIgnoreCase("Chrome")) || (Browser.equalsIgnoreCase("IE"))) {
			Thread.sleep(5000);
		}
		waitforElement(180, RP.USERNAME, driver);
		type(RP.USERNAME, Username);

		click(RP.PASSWORD);
		type(RP.PASSWORD, Password1);
		click(RP.PASSWORDCONFIRM);
		type(RP.PASSWORDCONFIRM, PasswordConfirm1);
		if (VerifyObject(driver, RP.BillingZipError)) {
			boolean report = false;
			do {
				click(RP.BILLINGZIP);
				RP.BILLINGZIP.sendKeys(Keys.CONTROL + "a");
				RP.BILLINGZIP.sendKeys(Keys.CONTROL + "x");
				Thread.sleep(2000);
				RP.BILLINGZIP.sendKeys(Keys.CONTROL + "v");
				Thread.sleep(2000);
				sendkeysTab(RP.BILLINGZIP);
				Thread.sleep(2000);
				if (!VerifyObject(driver, RP.BillingZipError)) {
					click(RP.SUBMIT);
					report = true;
				}
			} while (report == false);
		}
		if (Username.contains("PendingBilling")) {
			Thread.sleep(2000);
			JavaScriptclick(driver, RP.CONTINUETOBILLINGINFORMATION);
			Thread.sleep(3000);

		}
		MultipleVertexUsers = MultipleVertexUsers + Username + ";";
		System.out.println("Multple Users:" + MultipleVertexUsers);
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

	@SuppressWarnings("unused")
	public void ChangeSubscription(String Clientname, String UserName, String feature) throws Exception {
		UpgradeProToPremium UTP = PageFactory.initElements(driver, UpgradeProToPremium.class);
		DowngradePremiumtoPro DPP = PageFactory.initElements(driver, DowngradePremiumtoPro.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		String parentwindow1 = null, window = null;
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		Thread.sleep(2000);
		waitforElement(90, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, Clientname);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + Clientname + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a")).getText();
		System.out.println(CName);
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
		Thread.sleep(2000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(30, VSC.EXPORT, driver);
		CNTID = driver.findElement(By.xpath("//div[@class='span3 ng-binding'][contains(.,'Cloud Customer Id:')]"))
				.getText().split(":")[1].trim();
		JavaScriptclick(driver, VSC.SMBADMIN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.EDITCUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.EDITCUSTOMERSUBSCRIPTION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.CUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.CUSTOMERSUBSCRIPTION);
		waitforElement(180, MSP.SAVEBUTTON, driver);
		waitforElement(180, DPP.PROFESSIONAL, driver);
		waitforElement(180, MSP.PREMIUMSUB, driver);
		if (feature.equalsIgnoreCase("Add_TestInstance")) {
			click(MSP.testInstanceradioBtn);
		}
		waitforElement(180, MSP.SAVEBUTTON, driver);
		JavaScriptclick(driver, MSP.SAVEBUTTON);
		waitforElement(180, UTP.UPGRADEBUTTON, driver);
		JavaScriptclick(driver, UTP.UPGRADEBUTTON);
		Thread.sleep(5000);
		if (feature.equalsIgnoreCase("Add_TestInstance")) {
			Thread.sleep(2000);
			/*
			 * if (Environment.equalsIgnoreCase("DEV") ||
			 * Environment.equalsIgnoreCase("AWSDEV")) {
			 * xGetTestInstanceSetPasswordURL(UserName, Environment); } else if
			 * (Environment.equalsIgnoreCase("STAGE") ||
			 * Environment.equalsIgnoreCase("AWSSTAGE")) {
			 * xGetTestInstanceSetPasswordURL(UserName, Environment); } else if
			 * (Environment.equalsIgnoreCase("AWSPROD")) {
			 * xGetTestInstanceSetPasswordURL(UserName, Environment); } else {
			 * xGetTestInstanceSetPasswordURL(UserName, Environment); }
			 */

			xGetTestInstanceSetPasswordURL(UserName, Environment);

			Thread.sleep(7000);
			driver.get(TestInstanceSetPwdURL);
			System.out.println(TestInstanceSetPwdURL);
			TestInstanceclientName = Clientname + ".TI";
			TestInstanceUserName = UserName + ".TI";
			System.out.println("Test Instance Client name :" + TestInstanceclientName);
			Thread.sleep(2000);
			type(TI.testInstanceUsername, TestInstanceUserName);
			type(TI.testInstancepassword, "Password!1");
			Thread.sleep(2000);
			type(TI.testInstanceconfirmpassword, "Password!1");
			Thread.sleep(2000);
			JavaScriptclick(driver, TI.setPassword);
		}
		if (window == "exists") {
			driver.close();
			Thread.sleep(5000);
			driver.switchTo().window(parentwindow1);
		}
		Thread.sleep(2000);

	}

	public void DowngradetoTI(String Clientname, String Username, String env) throws Exception {
		UpgradeProToPremium UTP = PageFactory.initElements(driver, UpgradeProToPremium.class);
		DowngradePremiumtoPro DPP = PageFactory.initElements(driver, DowngradePremiumtoPro.class);
		MySubscriptionPage MSP = PageFactory.initElements(driver, MySubscriptionPage.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		PageFactory.initElements(driver, RegistrationPage.class);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		Thread.sleep(2000);
		waitforElement(90, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, Clientname);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr[2]/td[1]/div/a[contains(.,'" + Clientname + "')]", driver);
		String CName = driver.findElement(By.xpath("//table/tbody/tr[2]/td[1]/div/a")).getText();
		System.out.println(CName);
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
		Thread.sleep(2000);
		waitforElement(90, VSC.EXPORT, driver);
		JavaScriptclick(driver, VSC.SMBADMIN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, VSC.EDITCUSTOMERSUBSCRIPTION, driver);
		Thread.sleep(2000);
		JavaScriptclick(driver, VSC.EDITCUSTOMERSUBSCRIPTION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, VSC.CUSTOMERSUBSCRIPTION, driver);
		JavaScriptclick(driver, VSC.CUSTOMERSUBSCRIPTION);
		waitforElement(180, MSP.SAVEBUTTON, driver);
		waitforElement(180, DPP.PROFESSIONAL, driver);
		waitforElement(180, MSP.PREMIUMSUB, driver);
		click(MSP.testInstanceradioBtn);
		waitforElement(180, MSP.SAVEBUTTON, driver);
		JavaScriptclick(driver, MSP.SAVEBUTTON);
		waitforElement(180, UTP.UPGRADEBUTTON, driver);
		JavaScriptclick(driver, UTP.UPGRADEBUTTON);
		Thread.sleep(5000);
		click(VSC.MANAGESMBCUSTLINK);
		JavaScriptclick(driver, VSC.VTMSACCOUNT);
		JavaScriptclick(driver, VSC.VTMSLOGOUT);
		Thread.sleep(2000);
		System.out.println(env);
		System.out.println(Username);
	}

	public void DeleteCustomer(String Clientname) throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, Clientname);
		type(VSC.Varname, VARName);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//table/tbody/tr[2]/td/div/a[text()='" + Clientname + "']/../../../td/div/a")) {
			WebElement clientclick = driver.findElement(
					By.xpath("//table/tbody/tr[2]/td/div/a[text()='" + Clientname + "']/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", clientclick);
			Thread.sleep(2000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(30, VSC.EXPORT, driver);
			Thread.sleep(1000);
			driver.navigate().refresh();
			Thread.sleep(5000);
			waitforElement(180, VSC.DELETELINK, driver);
			JavaScriptclick(driver, VSC.DELETELINK);
			waitforElement(180, VSC.YES, driver);
			click(VSC.YES);
			Thread.sleep(1000);
			driver.navigate().refresh();
			waitforText(180, VSC.ManageCloudCustomerheader, driver);
		}
	}

	public void cancelCustomer(String Clientname, String CancelReason) throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		PageFactory.initElements(driver, ConfigurationReportPage.class);
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, Clientname);
		type(VSC.Varname, VARName);
		Thread.sleep(5000);
		if (VerifyObject(driver, "//table/tbody/tr[2]/td/div/a[text()='" + Clientname + "']/../../../td/div/a")) {
			WebElement clientclick = driver.findElement(
					By.xpath("//table/tbody/tr[2]/td/div/a[text()='" + Clientname + "']/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", clientclick);
			Thread.sleep(2000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(30, VSC.EXPORT, driver);
			Thread.sleep(1000);
			driver.navigate().refresh();
			waitforElement(60, AUA.CANCELBUTTON, driver);
			JavaScriptclick(driver, AUA.CANCELBUTTON);
			waitforText(180, AUA.POPUPTEXT, driver);
			type(AUA.CANCELREASON, CancelReason);
			JavaScriptclick(driver, AUA.COMPLETECANCEL);
			waitforElement(120, AUA.CANCELDISABLE, driver);
			Thread.sleep(2000);
			assertTrue(VSC.COMPANYDETAILSTABLE.isDisplayed());

		}

	}

}
