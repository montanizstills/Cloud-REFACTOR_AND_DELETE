package common.libraries.ecw;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.LoginPage;
import common.pages.ProfessionalUserLinks;
import common.pages.RegistrationPage;
import common.pages.WizardSetting;

public class ExemptionCertificateWizard extends CommonFunctions {

	private WebDriver driver;

	public ExemptionCertificateWizard(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void ECWClientUserLinks() throws Exception {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		WizardSetting WS = PageFactory.initElements(driver, WizardSetting.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		waitforElement(180, PUL.ECWIZARD, driver);
		assertTrue(driver.getTitle().equalsIgnoreCase("Exemption Certificate Wizard | Vertex Cloud"));
		// Settings Verification
		JavaScriptclick(driver, PUL.ECWSettingslink);
		Thread.sleep(5000);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		// DASHBOARD Page Verification
		assertTrue(VerifyObject(driver, PUL.ECWURL));
		String EcwUrl = driver
				.findElement(By.xpath("//div/strong[contains(.,'URL:')]/../../div[@class='span9 ng-binding']"))
				.getText();
		System.out.println(EcwUrl);
		if (Environment.equalsIgnoreCase("PROD")) {
			assertTrue(EcwUrl.equalsIgnoreCase("https://ecwportal.vertexsmb.com"));

		} else {
			String evn = Environment.toLowerCase();
			assertTrue(EcwUrl.equalsIgnoreCase("https://" + evn + "ecwportal.vertexsmb.com"));
		}
		assertTrue(VerifyObject(driver, PUL.ECWClientCode));
		assertTrue(VerifyObject(driver, PUL.contactsupport));
		JavaScriptclick(driver, PUL.ECWIZARD);
		Thread.sleep(1000);
		JavaScriptclick(driver, RP.LaunchSite);
		Thread.sleep(7000);
		String parentwindow = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		waitforElement(180, RP.ECWLogout, driver);
		if (!VerifyObject(driver, WS.CERTIFICATESEARCHL) || !VerifyObject(driver, WS.SEARCHBUTTON)) {
			driver.close();
			driver.switchTo().window(parentwindow);
			driver.get(url);
			Thread.sleep(5000);
			JavaScriptclick(driver, LP.VTMSACCOUNT);
			Thread.sleep(3000);
			JavaScriptclick(driver, LP.VTMSLOGOUT);
			Thread.sleep(2000);
		}
		assertTrue(VerifyObject(driver, WS.CERTIFICATESEARCHL));
		assertTrue(VerifyObject(driver, WS.SEARCHBUTTON));
		// JavaScriptclick(driver, RP.ECWLogout);
		driver.close();
		driver.switchTo().window(parentwindow);
		driver.get(url);
		Thread.sleep(5000);
		JavaScriptclick(driver, LP.VTMSACCOUNT);
		Thread.sleep(3000);
		JavaScriptclick(driver, LP.VTMSLOGOUT);
		Thread.sleep(2000);
	}

	public void ECWClientUserLinks(String UserName) throws Exception {

		String parentwindow = driver.getWindowHandle();
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		WizardSetting WS = PageFactory.initElements(driver, WizardSetting.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		JavaScriptclick(driver, PUL.ECWIZARD);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, RP.ECWheaderText, driver);
		assertTrue(driver.getTitle().equalsIgnoreCase("Exemption Certificate Wizard | Vertex Cloud"));

		// DASHBOARD Page Verification
		assertTrue(VerifyObject(driver, RP.ECWheaderText));
		assertTrue(VerifyObject(driver, RP.ECWWidgetText));
		assertTrue(VerifyObject(driver, RP.LaunchSite));
		waitforElement(180, RP.LaunchSite, driver);
		JavaScriptclick(driver, RP.LaunchSite);
		Thread.sleep(7000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		waitforElement(180, RP.ECWLogout, driver);
		assertTrue(VerifyObject(driver, WS.CERTIFICATESEARCHL));
		assertTrue(VerifyObject(driver, WS.SEARCHBUTTON));
		JavaScriptclick(driver, RP.ECWLogout);
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window(parentwindow);
		// driver.get(xEnvironment());
		driver.navigate().refresh();
		Thread.sleep(5000);
		if (VerifyObject(driver, LP.USERNAME)) {
			waitforElement(180, LP.USERNAME, driver);
			if (Environment.equalsIgnoreCase("PROD")) {
				type(LP.USERNAME, UserName);
				type(LP.PASSWORD, prodsmokepassword);
			} else if (Environment.equalsIgnoreCase("CS")) {
				type(LP.USERNAME, UserName);
				type(LP.PASSWORD, cssmokepassword);
			} else {
				type(LP.USERNAME, UserName);
				type(LP.PASSWORD, "Password!1");
			}
			JavaScriptclick(driver, LP.LOGIN);
		}
		waitforElement(180, PUL.ECWIZARD, driver);
		// Settings Verification
		JavaScriptclick(driver, PUL.ECWSettingslink);
		waitUntilElementInvisibe(120, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.ECWHeaderText, driver);
		assertTrue(VerifyObject(driver, PUL.ECWHeaderText));
		assertTrue(VerifyObject(driver, PUL.ECWURL));

		String EcwUrl = driver
				.findElement(By.xpath("//div/strong[contains(.,'URL:')]/../../div[@class='span9 ng-binding']"))
				.getText();
		System.out.println(EcwUrl);
		if (Environment.equalsIgnoreCase("PROD")) {
			assertTrue(EcwUrl.equalsIgnoreCase("https://ecwportal.vertexsmb.com"));

		} else {
			String evn = Environment.toLowerCase();
			assertTrue(EcwUrl.equalsIgnoreCase("https://" + evn + "ecwportal.vertexsmb.com"));
		}
		assertTrue(VerifyObject(driver, PUL.ECWClientCode));
		assertTrue(VerifyObject(driver, PUL.contactsupport));
		Thread.sleep(5000);
	}

}
