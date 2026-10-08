package common.libraries.qbo;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.StringReader;
import java.time.Duration;
import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.InputSource;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.MyInterfacePage;
import common.pages.ProfessionalUserLinks;
import common.pages.QBOFirstTimeMap;
import common.pages.QBOManageForms;
import common.pages.QBOSettings;
import common.pages.VertexSMBCustomers;
import common.utilities.CloudData;
import common.utilities.UtilityScript;

public class QuickBooks extends CommonFunctions {

	private WebDriver driver;

	public QuickBooks(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void ConnectToQBO(String Subscription, String QBO_UserName, String QBO_Password, String company,
			String Map_Company, String StartDate, String invoiceno) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		QBOFirstTimeMap QBO = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		QBOSettings QBS = PageFactory.initElements(driver, QBOSettings.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(180, PUL.SETTINGSLINK, driver);
		JavaScriptMousehover(driver, PUL.SETTINGSLINK);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN1, driver);
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, QBO.LaunchSite, driver);
		assertTrue(VerifyObject(driver, QBO.QuickBookOnline));
		assertTrue(VerifyObject(driver, QBO.QuickBookOnlineSection));
		if (Subscription.equalsIgnoreCase("Standard")) {
			assertTrue(VerifyObject(driver, QBO.launchsitestandarddisable));
		}
		if (Subscription.equalsIgnoreCase("Professional") || Subscription.equalsIgnoreCase("Premium")) {
			// Handling View All connectors Window (Parent Window)
			String Parentwindow = driver.getWindowHandle();
			if (VerifyObject(driver, QBO.launchsitestandarddisable)) {
				driver.navigate().refresh();
				waitforElement(60, QBO.launchsiteenable, driver);
			}
			/*
			 * if (Browser.equalsIgnoreCase("IE")) { JavaScriptclick(driver,
			 * QBO.LaunchSite); } else {
			 */
			scrollDown(driver);
			Thread.sleep(3000);
			JavaScriptscrolltoView(driver, QBO.LaunchSite);
			JavaScriptclick(driver, QBO.LaunchSite);
			// }
			Thread.sleep(8000);
			Set<String> Window = driver.getWindowHandles();
			Window.remove(Parentwindow);
			driver.switchTo().window(Window.iterator().next());
			// QBO Page Getting Run time Error after clicking on Launch site buttton
			if (VerifyObject(driver, QBO.Runtimeerror)) {
				assertTrue(VerifyObject(driver, QBO.ApiKey));
				driver.close();
				driver.switchTo().window(Parentwindow);
				waitforElement(60, QBO.LaunchSite, driver);
			}
			// Switching to API Key Password Window(Childwindow1)
			waitforElement(60, QBO.ApiKey, driver);
			if (VerifyObject(driver, QBO.ApiKey)) {
//				type(QBO.ApiKey, APIkey);
//				type(QBO.ApiPassword1, APIPassword);
				type(QBO.ApiKey, "04acd21a24fe445da83b00a8ac10af92");
				type(QBO.ApiPassword1, "Qi3-6R&e");

				JavaScriptclick(driver, QBO.Continue1);
			}
			waitforElement(60, QBO.ConnectQuickBooks, driver);

			if (VerifyObject(driver, QBO.ConnectQuickBooks)) {
				// Handling Connect to Quick Books Window
				click(QBO.ConnectQuickBooks);
				Thread.sleep(5000);
				Set<String> Window1 = driver.getWindowHandles();
				System.out.println("1=" + Window1.size());
//				Window1.remove(Childwindow);
				System.out.println("2=" + Window1.size());
				// Switching to Quick Books Companies Window (Child Window2)
				driver.switchTo().window(Window1.iterator().next());
				for (String winHandle : Window1) {
					driver.switchTo().window(winHandle);
				}
				Thread.sleep(5000);
				waitforElement(180, QBO.QBOUserid, driver);
				if (VerifyObject(driver, QBO.QBOUserid)) {
					type(QBO.QBOUserid, QBO_UserName);
					type(QBO.QBOPassword, QBO_Password);
					JavaScriptclick(driver, QBO.QBOSignIn);
					Thread.sleep(10000);
				}
				Set<String> Window2 = driver.getWindowHandles();
				driver.switchTo().window(Window2.iterator().next());
				for (String winHandle : Window2) {
					driver.switchTo().window(winHandle);
				}
				Thread.sleep(10000);
				waitforElement(60, QBO.CONFIGURE_COMPANY_MAPPING, driver);
				waitforElement(180, QBO.taxCentralName, driver);
				if (VerifyObject(driver, QBO.taxCentralName)) {
					selectByValue(QBO.taxCentralName, company);
					JavaScriptclick(driver, QBO.submit);
					waitforElement(60, QBO.manageSalesforms, driver);
					assertTrue(VerifyObject(driver, QBO.manageSalesforms));
					assertTrue(VerifyObject(driver, QBO.addressValidation));
					assertTrue(VerifyObject(driver, QBO.settings));
					assertTrue(VerifyObject(driver, QBO.helpCenter));
					assertTrue(VerifyObject(driver, QBO.contactSupport));
				}
			}
			waitforElement(60, QBO.manageSalesforms, driver);
			JavaScriptclick(driver, QBO.manageSalesforms);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, QBO.syncsalesForms, driver);
			waitforElement(180, QBO.STARRTDATE, driver);
			click(QBO.STARRTDATE);
			QBO.STARRTDATE.sendKeys(Keys.CONTROL + "a");
			QBO.STARRTDATE.sendKeys(Keys.BACK_SPACE);
			type(QBO.STARRTDATE, StartDate);
			Thread.sleep(2000);
			sendkeysTab(QBO.STARRTDATE);
			assertTrue(gettitle(driver).equalsIgnoreCase("Manage Sales Form | Vertex Cloud"));
			JavaScriptclick(driver, QBO.syncsalesForms);
			Thread.sleep(8000);
			if (VerifyObject(driver, "//h3[contains(text(),'SYNC IN PROGRESS')]")) {
				assertTrue(true);
			}
			waitUntilElementInvisibe(360, "//h3[contains(text(),'SYNC IN PROGRESS')]", driver);
			waitforElement(180, QBO.quickSearch, driver);
			// Searching for New Transaction Number
			JavaScriptclick(driver, QBO.quickSearch);
			clear(QBO.quickSearch);
			Thread.sleep(5000);
			type(QBO.quickSearch, invoiceno);
			Thread.sleep(5000);
			type(QBO.quickSearch, "0");
			Thread.sleep(5000);
			sendkeybackSpace(QBO.quickSearch);
			Thread.sleep(5000);
			if (QBO.NOSALESFORMS.isDisplayed()) {
				clear(QBO.quickSearch);
				Thread.sleep(5000);
				type(QBO.quickSearch, invoiceno);
				Thread.sleep(5000);
				type(QBO.quickSearch, "0");
				Thread.sleep(5000);
				sendkeybackSpace(QBO.quickSearch);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//table/tbody/tr[3]/td[contains(.,'" + invoiceno + "')]/../td/input[2]"))
						.click();
			}
			// JavaScriptclick(driver, QBO.formDatecheck);
			else {
				driver.findElement(By.xpath("//table/tbody/tr[3]/td[contains(.,'" + invoiceno + "')]/../td/input[2]"))
						.click();
			}
			Thread.sleep(5000);
			if (Browser.equalsIgnoreCase("IE")) {
				click(QBO.selectAction);
			} else {
				JavaScriptclick(driver, QBO.selectAction);
			}
			Thread.sleep(2000);
			JavaScriptclick(driver, QBO.estimateTax);
			Thread.sleep(5000);
			waitforText(360, "//table/tbody/tr[3]/td[contains(text(),'Tax Estimated')]", driver);
			JavaScriptclick(driver, QBO.addressValidation);
			waitforElement(180, QBO.synccustomerRecords, driver);
			JavaScriptclick(driver, QBO.synccustomerRecords);
			Thread.sleep(8000);
			waitUntilElementInvisibe(360, "//h3[contains(text(),'SYNC IN PROGRESS')]", driver);
			waitforElement(180, QBO.validate, driver);
			JavaScriptclick(driver, QBO.validate);
			waitforElement(180, QBO.suggestaddress, driver);
			JavaScriptclick(driver, QBO.suggestaddress);
			JavaScriptclick(driver, QBO.save);
			waitforText(120, QBO.validationStatus, driver);
			assertTrue(VerifyObject(driver, QBO.validationStatus));
			JavaScriptclick(driver, QBO.settings);
			waitforElement(120, QBS.addcompanyMapping, driver);
			driver.close();
			driver.switchTo().window(Parentwindow);
			waitforElement(60, QBO.LaunchSite, driver);
		}

	}

	public void PostTransactionInQBOC(String Email, String Password, String Customer, String Product, String Rate)
			throws Exception {
		QBOFirstTimeMap QBO = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		Open(driver, "https://developer.intuit.com/app/developer/homepage");
		Thread.sleep(3000);
		click(QBO.INTUITSIGNIN);
		String Childwindow = driver.getWindowHandle();
		driver.switchTo().window(Childwindow);
		if (VerifyObject(driver, QBO.INTUITUSERNAME)) {
			type(QBO.INTUITUSERNAME, Email);
			waitforElement(180, QBO.INTUITPASSWORD, driver);
			type(QBO.INTUITPASSWORD, Password);
			waitforElement(180, QBO.INTUITSIGNINBTN, driver);
			click(QBO.INTUITSIGNINBTN);
		}
		waitforElement(180, QBO.GET_STARTED, driver);
		click(QBO.GET_STARTED);
		waitforElement(180, QBO.QUICKBOOKS_SANDBOX, driver);
		click(QBO.QUICKBOOKS_SANDBOX);
		waitforElement(180, QBO.INTUITCOMPANY, driver);
		click(QBO.INTUITCOMPANY);
		Thread.sleep(3000);
		Set<String> Window2 = driver.getWindowHandles();
		driver.switchTo().window(Window2.iterator().next());
		for (String winHandle : Window2) {
			driver.switchTo().window(winHandle);
		}
		click(QBO.INTUITBUTNEW);
		waitforElement(180, QBO.INTUITINVOICE, driver);
		click(QBO.INTUITINVOICE);
		Thread.sleep(2000);
		Set<String> Window1 = driver.getWindowHandles();
		driver.switchTo().window(Window1.iterator().next());
		for (String winHandle : Window1) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(3000);
		Robot robot = new Robot();
		if (VerifyObject(driver, QBO.INVOICECUSTMER1)) {
			type(QBO.INVOICECUSTMER1, Customer);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
		}
		waitforElement(180, QBO.Product, driver);
		click(QBO.Product);
		waitforElement(180, QBO.INVOICEPRODUST, driver);
		type(QBO.INVOICEPRODUST, Product);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		waitforElement(180, QBO.INVOICERATE, driver);
		type(QBO.INVOICERATE, Rate);
		Thread.sleep(1000);
		click(QBO.SAVEBUT);
		Thread.sleep(1000);
		String Msg = QBO.MESSAGENODE.getText();
		String[] InvoiceMesage = Msg.split("\\s", 0);
		InvoiceNO = InvoiceMesage[1];

	}

	/*
	 * public void PostToJurnelInMicroSite(String Subscription, String QBO_UserName,
	 * String QBO_Password, String company, String Map_Company, String StartDate,
	 * String invoiceno) { ConfigurePage CNP = PageFactory.initElements(driver,
	 * ConfigurePage.class); QBOFirstTimeMap QBO = PageFactory.initElements(driver,
	 * QBOFirstTimeMap.class); QBOSettings QBS = PageFactory.initElements(driver,
	 * QBOSettings.class); ProfessionalUserLinks PUL =
	 * PageFactory.initElements(driver, ProfessionalUserLinks.class);
	 * waitforElement(60, QBO.manageSalesforms, driver); JavaScriptclick(driver,
	 * QBO.manageSalesforms); Thread.sleep(5000); waitUntilElementInvisibe(180,
	 * CNP.NPROGRESSBUSY_XPATH, driver); waitforElement(180, QBO.syncsalesForms,
	 * driver); waitforElement(180, QBO.STARRTDATE, driver); click(QBO.STARRTDATE);
	 * QBO.STARRTDATE.sendKeys(Keys.CONTROL + "a");
	 * QBO.STARRTDATE.sendKeys(Keys.BACK_SPACE); type(QBO.STARRTDATE, StartDate);
	 * Thread.sleep(2000); sendkeysTab(QBO.STARRTDATE); assertTrue(gettitle(driver).
	 * equalsIgnoreCase("Manage Sales Form | Vertex Cloud"));
	 * JavaScriptclick(driver, QBO.syncsalesForms); Thread.sleep(8000); if
	 * (VerifyObject(driver, "//h3[contains(text(),'SYNC IN PROGRESS')]")) {
	 * assertTrue(true); } waitUntilElementInvisibe(360,
	 * "//h3[contains(text(),'SYNC IN PROGRESS')]", driver); waitforElement(180,
	 * QBO.quickSearch, driver); // Searching for New Transaction Number
	 * JavaScriptclick(driver, QBO.quickSearch); clear(QBO.quickSearch);
	 * Thread.sleep(5000); type(QBO.quickSearch, invoiceno); Thread.sleep(5000);
	 * type(QBO.quickSearch, "0"); Thread.sleep(5000);
	 * sendkeybackSpace(QBO.quickSearch); Thread.sleep(5000); if
	 * (QBO.NOSALESFORMS.isDisplayed()) { clear(QBO.quickSearch);
	 * Thread.sleep(5000); type(QBO.quickSearch, invoiceno); Thread.sleep(5000);
	 * type(QBO.quickSearch, "0"); Thread.sleep(5000);
	 * sendkeybackSpace(QBO.quickSearch); Thread.sleep(5000);
	 * driver.findElement(By.xpath("//table/tbody/tr[3]/td[contains(.,'" + invoiceno
	 * + "')]/../td/input[2]")) .click(); } // JavaScriptclick(driver,
	 * QBO.formDatecheck); else {
	 * driver.findElement(By.xpath("//table/tbody/tr[3]/td[contains(.,'" + invoiceno
	 * + "')]/../td/input[2]")) .click(); } Thread.sleep(5000); if
	 * (Browser.equalsIgnoreCase("IE")) { click(QBO.selectAction); } else {
	 * JavaScriptclick(driver, QBO.selectAction); } Thread.sleep(2000);
	 * JavaScriptclick(driver, QBO.estimateTax); Thread.sleep(5000);
	 * waitforText(360, "//table/tbody/tr[3]/td[contains(text(),'Tax Estimated')]",
	 * driver); JavaScriptclick(driver, QBO.addressValidation); waitforElement(180,
	 * QBO.synccustomerRecords, driver); JavaScriptclick(driver,
	 * QBO.synccustomerRecords); Thread.sleep(8000); waitUntilElementInvisibe(360,
	 * "//h3[contains(text(),'SYNC IN PROGRESS')]", driver); waitforElement(180,
	 * QBO.validate, driver); JavaScriptclick(driver, QBO.validate);
	 * waitforElement(180, QBO.suggestaddress, driver); JavaScriptclick(driver,
	 * QBO.suggestaddress); JavaScriptclick(driver, QBO.save); waitforText(120,
	 * QBO.validationStatus, driver); assertTrue(VerifyObject(driver,
	 * QBO.validationStatus)); JavaScriptclick(driver, QBO.settings);
	 * waitforElement(120, QBS.addcompanyMapping, driver); driver.close();
	 * 
	 * String Parentwindow = driver.getWindowHandle(); waitforElement(60,
	 * QBO.LaunchSite, driver); }
	 */
	public void QuickBooksLaunchSite(String Subscription, String QBO_UserName, String QBO_Password, String company,
			String Map_Company, String StartDate, String invoiceno) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		QBOFirstTimeMap QBO = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		QBOSettings QBS = PageFactory.initElements(driver, QBOSettings.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(180, PUL.SETTINGSLINK, driver);
		JavaScriptMousehover(driver, PUL.SETTINGSLINK);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN1, driver);
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, QBO.LaunchSite, driver);
		assertTrue(VerifyObject(driver, QBO.QuickBookOnline));
		assertTrue(VerifyObject(driver, QBO.QuickBookOnlineSection));
		if (Subscription.equalsIgnoreCase("Standard")) {
			assertTrue(VerifyObject(driver, QBO.launchsitestandarddisable));
		}
		if (Subscription.equalsIgnoreCase("Professional") || Subscription.equalsIgnoreCase("Premium")) {
			// Handling View All connectors Window (Parent Window)
			String Parentwindow = driver.getWindowHandle();
			if (VerifyObject(driver, QBO.launchsitestandarddisable)) {
				driver.navigate().refresh();
				waitforElement(60, QBO.launchsiteenable, driver);
			}
			/*
			 * if (Browser.equalsIgnoreCase("IE")) { JavaScriptclick(driver,
			 * QBO.LaunchSite); } else {
			 */
			scrollDown(driver);
			Thread.sleep(3000);
			JavaScriptscrolltoView(driver, QBO.LaunchSite);
			JavaScriptclick(driver, QBO.LaunchSite);
			// }
			Thread.sleep(8000);
			Set<String> Window = driver.getWindowHandles();
			Window.remove(Parentwindow);
			driver.switchTo().window(Window.iterator().next());
			// QBO Page Getting Run time Error after clicking on Launch site buttton
			if (VerifyObject(driver, QBO.Runtimeerror)) {
				assertTrue(VerifyObject(driver, QBO.ApiKey));
				driver.close();
				driver.switchTo().window(Parentwindow);
				waitforElement(60, QBO.LaunchSite, driver);
			}
			// Switching to API Key Password Window(Childwindow1)
			waitforElement(60, QBO.ApiKey, driver);
			if (VerifyObject(driver, QBO.ApiKey)) {
				type(QBO.ApiKey, APIkey);
				type(QBO.ApiPassword1, APIPassword);
				JavaScriptclick(driver, QBO.Continue1);
			}
			waitforElement(60, QBO.ConnectQuickBooks, driver);
			if (VerifyObject(driver, QBO.ConnectQuickBooks)) {
				// Handling Connect to Quick Books Window
				String Childwindow = driver.getWindowHandle();
				JavaScriptclick(driver, QBO.ConnectQuickBooks);
				Thread.sleep(3000);
				Set<String> Window1 = driver.getWindowHandles();
				System.out.println("1=" + Window1.size());
				Window1.remove(Childwindow);
				System.out.println("2=" + Window1.size());
				// Switching to Quick Books Companies Window (Child Window2)
				driver.switchTo().window(Window1.iterator().next());
				for (String winHandle : Window1) {
					driver.switchTo().window(winHandle);
				}
				Thread.sleep(5000);
				waitforElement(180, QBO.QBOUserid, driver);
				if (VerifyObject(driver, QBO.QBOUserid)) {
					type(QBO.QBOUserid, QBO_UserName);
					type(QBO.QBOPassword, QBO_Password);
					JavaScriptclick(driver, QBO.QBOSignIn);
					Thread.sleep(3000);
					click(QBO.CODETOEMAIL);
					Thread.sleep(10000);
					UtilityScript.xGetQBOVerificationCode(Environment);
					type(QBO.CONFIRM_CODE, TestInstanceSetPwdURL);
					waitforElement(60, QBO.ROQBOCOMPLIST, driver);
					// if Company List Page Not loaded it will close the Childwindow1 and
					// Childwindow2 and Switch to Parent Window
					// if(!(QBO.ROQBOCOMPLIST.isDisplayed())||QBO.QBOCONFIRMMYACCOUNT.isDisplayed())
					if (!(VerifyObject(driver, QBO.ROQBOCOMPLIST)) || VerifyObject(driver, QBO.QBOCONFIRMMYACCOUNT)) {
						driver.close();
						Thread.sleep(3000);
						driver.switchTo().window(Childwindow);
						driver.close();
						Thread.sleep(3000);
						driver.switchTo().window(Parentwindow);
						Thread.sleep(5000);
						// assertTrue(VerifyObject(driver, QBO.ROQBOCOMPLIST));
					}
					click(QBO.ROQBOCOMPLIST);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[@class='company-list-item']/div[contains(.,'" + company + "')]"))
							.click();
					Thread.sleep(10000);
					waitforElement(180, QBO.NEXT, driver);
					JavaScriptclick(driver, QBO.NEXT);
					waitforElement(180, QBO.CONNECT, driver);
					if (VerifyObject(driver, QBO.authorizeBtn)) {
						JavaScriptclick(driver, QBO.authorizeBtn);
					}
					if (VerifyObject(driver, QBO.CONNECTAUTHORIZE)) {
						JavaScriptclick(driver, QBO.CONNECTAUTHORIZE);
					}
					Thread.sleep(10000);
					driver.switchTo().window(Childwindow);
					waitforElement(180, QBO.taxCentralName, driver);
					if (VerifyObject(driver, QBO.taxCentralName)) {
						selectByValue(QBO.taxCentralName, Map_Company);
						JavaScriptclick(driver, QBO.submit);
						waitforElement(60, QBO.manageSalesforms, driver);
						assertTrue(VerifyObject(driver, QBO.manageSalesforms));
						assertTrue(VerifyObject(driver, QBO.addressValidation));
						assertTrue(VerifyObject(driver, QBO.settings));
						assertTrue(VerifyObject(driver, QBO.helpCenter));
						assertTrue(VerifyObject(driver, QBO.contactSupport));
					}
				}
			}
			waitforElement(60, QBO.manageSalesforms, driver);
			JavaScriptclick(driver, QBO.manageSalesforms);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, QBO.syncsalesForms, driver);
			waitforElement(180, QBO.STARRTDATE, driver);
			click(QBO.STARRTDATE);
			QBO.STARRTDATE.sendKeys(Keys.CONTROL + "a");
			QBO.STARRTDATE.sendKeys(Keys.BACK_SPACE);
			type(QBO.STARRTDATE, StartDate);
			Thread.sleep(2000);
			sendkeysTab(QBO.STARRTDATE);
			assertTrue(gettitle(driver).equalsIgnoreCase("Manage Sales Form | Vertex Cloud"));
			JavaScriptclick(driver, QBO.syncsalesForms);
			Thread.sleep(8000);
			if (VerifyObject(driver, "//h3[contains(text(),'SYNC IN PROGRESS')]")) {
				assertTrue(true);
			}
			waitUntilElementInvisibe(360, "//h3[contains(text(),'SYNC IN PROGRESS')]", driver);
			waitforElement(180, QBO.quickSearch, driver);
			// Searching for New Transaction Number
			JavaScriptclick(driver, QBO.quickSearch);
			clear(QBO.quickSearch);
			Thread.sleep(5000);
			type(QBO.quickSearch, invoiceno);
			Thread.sleep(5000);
			type(QBO.quickSearch, "0");
			Thread.sleep(5000);
			sendkeybackSpace(QBO.quickSearch);
			Thread.sleep(5000);
			if (QBO.NOSALESFORMS.isDisplayed()) {
				clear(QBO.quickSearch);
				Thread.sleep(5000);
				type(QBO.quickSearch, invoiceno);
				Thread.sleep(5000);
				type(QBO.quickSearch, "0");
				Thread.sleep(5000);
				sendkeybackSpace(QBO.quickSearch);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//table/tbody/tr[3]/td[contains(.,'" + invoiceno + "')]/../td/input[2]"))
						.click();
			}
			// JavaScriptclick(driver, QBO.formDatecheck);
			else {
				driver.findElement(By.xpath("//table/tbody/tr[3]/td[contains(.,'" + invoiceno + "')]/../td/input[2]"))
						.click();
			}
			Thread.sleep(5000);
			if (Browser.equalsIgnoreCase("IE")) {
				click(QBO.selectAction);
			} else {
				JavaScriptclick(driver, QBO.selectAction);
			}
			Thread.sleep(2000);
			JavaScriptclick(driver, QBO.estimateTax);
			Thread.sleep(5000);
			waitforText(360, "//table/tbody/tr[3]/td[contains(text(),'Tax Estimated')]", driver);
			JavaScriptclick(driver, QBO.addressValidation);
			waitforElement(180, QBO.synccustomerRecords, driver);
			JavaScriptclick(driver, QBO.synccustomerRecords);
			Thread.sleep(8000);
			waitUntilElementInvisibe(360, "//h3[contains(text(),'SYNC IN PROGRESS')]", driver);
			waitforElement(180, QBO.validate, driver);
			JavaScriptclick(driver, QBO.validate);
			waitforElement(180, QBO.suggestaddress, driver);
			JavaScriptclick(driver, QBO.suggestaddress);
			JavaScriptclick(driver, QBO.save);
			waitforText(120, QBO.validationStatus, driver);
			assertTrue(VerifyObject(driver, QBO.validationStatus));
			JavaScriptclick(driver, QBO.settings);
			waitforElement(120, QBS.addcompanyMapping, driver);
			driver.close();
			driver.switchTo().window(Parentwindow);
			waitforElement(60, QBO.LaunchSite, driver);
		}
	}

	public void QBOTaxEstimate(String transno) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String parentwindow = driver.getWindowHandle();
		QBOFirstTimeMap QBO = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		QBOSettings QBS = PageFactory.initElements(driver, QBOSettings.class);
		// Reading Invoice No
		// Smoke_ReadNewInvoiceIds();
		JavaScriptMousehover(driver, QBO.SettingsHover);
		JavaScriptclick(driver, QBO.ViewAllConnectors);
		waitforElement(60, QBO.LaunchSite, driver);
		assertTrue(VerifyObject(driver, QBO.QuickBookOnline));
		assertTrue(VerifyObject(driver, QBO.QuickBookOnlineSection));
		JavaScriptclick(driver, QBO.LaunchSite);
		Thread.sleep(3000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		waitforElement(120, QBO.manageSalesforms, driver);
		// Verifying Tax Estimate
		JavaScriptclick(driver, QBO.manageSalesforms);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, QBO.syncsalesForms, driver);
		assertTrue(gettitle(driver).equalsIgnoreCase("Manage Sales Form | Vertex Cloud"));
		// Searching for New Transaction Number
		waitforElement(180, QBO.quickSearch, driver);
		JavaScriptclick(driver, QBO.quickSearch);
		clear(QBO.quickSearch);
		Thread.sleep(5000);
		System.out.println("InvocieNumber=" + transno);
		type(QBO.quickSearch, transno);
		Thread.sleep(5000);
		type(QBO.quickSearch, "0");
		Thread.sleep(5000);
		sendkeybackSpace(QBO.quickSearch);
		Thread.sleep(5000);

		// JavaScriptclick(driver, QBO.formDatecheck);

		driver.findElement(By.xpath("//table/tbody/tr[3]/td[contains(.,'" + transno + "')]/../td/input[2]")).click();

		Thread.sleep(4000);
		JavaScriptclick(driver, QBO.selectAction);
		Thread.sleep(2000);
		JavaScriptclick(driver, QBO.estimateTax);
		Thread.sleep(5000);
		JavaScriptclick(driver, QBO.manageSalesforms);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, QBO.quickSearch, driver);
		waitforElement(180,
				"//table/tbody/tr/td[contains(.,'" + transno + "')]/../td[contains(text(),'Tax Estimated')]", driver);

		/*
		 * if (!VerifyObject(driver,
		 * "//table/tbody/tr[3]/td[contains(text(),'Tax Estimated')]")) {
		 * assertTrue(VerifyObject(driver,
		 * "//table/tbody/tr[3]/td[contains(text(),'Tax Estimated')]")); }
		 */
		// Verifying Address Validation
		JavaScriptclick(driver, QBO.addressValidation);
		waitforElement(180, QBO.synccustomerRecords, driver);
		assertTrue(gettitle(driver).equalsIgnoreCase("Customer Address Validation | Vertex Cloud"));
		JavaScriptclick(driver, QBO.validate);
		Thread.sleep(5000);
		click(QBO.suggestaddress);
		waitforElement(180, QBO.save, driver);
		click(QBO.save);
		waitforElement(120, QBO.validationStatus, driver);
		assertTrue(VerifyObject(driver, QBO.validationStatus));
		JavaScriptclick(driver, QBO.settings);
		waitforElement(60, QBS.addcompanyMapping, driver);
		assertTrue(gettitle(driver).equalsIgnoreCase("Settings | Vertex Cloud"));
		assertTrue(VerifyObject(driver, QBS.qboCompany1));
		assertTrue(VerifyObject(driver, QBS.toCompany1));
		assertTrue(VerifyObject(driver, QBS.addcompanyMapping));
		driver.close();
		driver.switchTo().window(parentwindow);
		waitforElement(60, QBO.LaunchSite, driver);
	}

	/*
	 * public ArrayList<String> Smoke_ReadNewInvoiceIds() throws IOException { try {
	 * invoiceList.clear(); File file = new File(".//"); String mainfolder =
	 * file.getCanonicalPath(); String invoiceIds = mainfolder +
	 * "\\resources\\qboimporter\\TransInvoiceNumbers.txt"; List<String> lines =
	 * Files.readAllLines(Paths.get(invoiceIds), Charset.defaultCharset()); for
	 * (String line : lines) { if (line.contains("Doc Number")) { String[] invoiceid
	 * = line.split("="); invoiceList.add(invoiceid[1].trim()); } } } catch
	 * (Exception e) { e.getMessage(); } return invoiceList; }
	 */

	public void ConnectorTest(String TestPath, String value) throws Exception {
		String ClntName = "";
		String CName, folderName, xmlFlie, Postdate, transType, LoopCount;
		String XMLFile = null, URL = null;
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		int i;
		CloudData.dataload(TestPath, value);
		for (i = CloudData.start_row; i <= CloudData.end_row; i++) {
			CloudData.setrowdata(i);
			xmlFlie = CloudData.get_data("XMLFileName");
			folderName = CloudData.get_data("Folder_Name");
			Postdate = CloudData.get_data("postfilingdate/documentdate");
			transType = CloudData.get_data("Transaction_Type");
			// Companycode = CloudData.get_data("Compcode");
			// ***Calling Interface Trusted ID from application
			if (CloudData.get_data("Trustedid_Admin").equalsIgnoreCase("Yes")) {
				if (CloudData.get_data("ClientName") != "") {
					ClntName = CloudData.get_data("ClientName");
					waitforElement(180, PUL.DASHBOARDLINK, driver);
					click(VSC.MANAGESMBCUSTLINK);
					Thread.sleep(2000);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, VSC.EXPORT, driver);
					// Getting Trusted ID from Vertex customersScreen
					type(VSC.SMBCUSTNAMESB, ClntName);
					Thread.sleep(5000);
					waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClntName + "')]", driver);
					CName = driver.findElement(By.xpath("//table/tbody/tr/td[3]")).getText();
					if ((ClntName).equalsIgnoreCase(CName)) {
						JavaScriptclick(driver, VSC.ACTION);
						Thread.sleep(5000);
						waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
						waitforElement(180, VSC.EXPORT, driver);
						TrsID = driver
								.findElement(
										By.xpath("//span[text()='Trusted Id:']/../../div[@class='span3 ng-binding']"))
								.getText().split(":")[1].trim();
					} else {
						assertTrue((ClntName).equalsIgnoreCase(CName));
					}
				}
			}
			if (CloudData.get_data("Trustedid_Client").equalsIgnoreCase("Yes")) {
				Smoke_InterfacesTrustedID();
			}
			// ***Calling xUpdateXML method from Utility class*******
			if ((TrsID != null) || (Postdate != "")) {
				UtilityScript us = new UtilityScript();
				us.xUpdateConnectorXML(folderName, xmlFlie, TrsID, Postdate, transType);
			}
			// ***Calling SoapTest method from DBScripts class*******

			XMLFile = CloudData.get_data("XMLFileName");
			URL = CloudData.get_data("Soap_WebServiceURL");
			System.out.println(URL);
			if (CloudData.get_data("Loop") != "") {
				LoopCount = CloudData.get_data("Loop");
				int Count = Integer.parseInt(LoopCount);
				for (int j = 0; j < Count; j++) {
					Smoke_RESTSoap(folderName, XMLFile, URL);
				}
			} else {
				Smoke_RESTSoap(folderName, XMLFile, URL);
			}
			Thread.sleep(5000);

		}
	}

	public String Smoke_RESTSoap(String foldrname, String fileName, String WSDLurl) throws Exception {
		String status = "";
		String xmlrep = null;
		try {
			File directory = new File(".//");
			String filePath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\upload" + "\\"
					+ "ConnectorTransactions" + "\\" + foldrname + "\\" + fileName;
			System.out.println("File path " + filePath);
			xDownloadFiles("ConnectorTransactions");
			String responsefile = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
					+ "ConnectorTransactions" + "\\" + fileName;
			Client client = Client.create();
			// Response Time Out set to 60 sec
			client.setReadTimeout(60000);
			client.setConnectTimeout(60000);
			WebResource webResource = client.resource(WSDLurl);
			ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).type(MediaType.TEXT_XML)
					.entity(new File(filePath)).post(ClientResponse.class);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				xmlrep = response.getEntity(String.class);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			xmlrep = response.getEntity(String.class);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(xmlrep)));
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(responsefile));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			status = status + e.getMessage();
		}
		return status;
	}

	public String Smoke_InterfacesTrustedID() throws Exception {
		String status = "";
		try {
			new Robot();
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
			MyInterfacePage MI = PageFactory.initElements(driver, MyInterfacePage.class);
			// Getting ClientName
			JavaScriptclick(driver, PUL.SETTINGSLINK);
			Thread.sleep(6000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			SettingsClntName = getText(MI.SETTINGSCLNTNAME);
			// Getting Trusted id
			JavaScriptMousehover(driver, PUL.SettingsHover);
			JavaScriptclick(driver, PUL.ADAPTERSLINK);
			Thread.sleep(6000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			TrsID = getText(MI.TRUSTEDID);
			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
			return AnErrorHasOccurredException() + e.getMessage() + status;
		}
		return status;
	}

	public String AnErrorHasOccurredException() throws Exception {
		String statuss = "";
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		if (VerifyObject(driver, PUL.OKERROR)) {
			statuss = "Fail,An error has occured warning Pop Up displayed;";
			click(PUL.OKERROR);
			waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
			Thread.sleep(3000);
			click(PUL.DASHBOARDLINK);
			waitforElement(180, PUL.UPDATEANALYSIS, driver);
		}
		return statuss;
	}

	public void QuickBooksLaunchSite(String Subscription, String Map_Company, String StartDate, String QBO_UserName,
			String QBO_Password, String invoiceno) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		QBOFirstTimeMap QBO = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		QBOSettings QBS = PageFactory.initElements(driver, QBOSettings.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(1800, PUL.SETTINGSLINK, driver);
		JavaScriptMousehover(driver, PUL.SETTINGSLINK);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN1, driver);
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, QBO.LaunchSite, driver);
		assertTrue(VerifyObject(driver, QBO.QuickBookOnline));
		assertTrue(VerifyObject(driver, QBO.QuickBookOnlineSection));
		if ((Subscription).equalsIgnoreCase("Standard")) {
			assertTrue(VerifyObject(driver, QBO.launchsitestandarddisable));
		}
		if ((Subscription).equalsIgnoreCase("Professional") || (Subscription).equalsIgnoreCase("Premium")) {
			// Handling View All connectors Window (Parent Window)
			String Parentwindow = driver.getWindowHandle();
			if (VerifyObject(driver, QBO.launchsitestandarddisable)) {
				driver.navigate().refresh();
				waitforElement(60, QBO.launchsiteenable, driver);
			}
			if (Browser.equalsIgnoreCase("IE")) {
				JavaScriptclick(driver, QBO.LaunchSite);
			} else {
				click(QBO.LaunchSite);
			}
			Thread.sleep(8000);
			Set<String> Window = driver.getWindowHandles();
			Window.remove(Parentwindow);
			driver.switchTo().window(Window.iterator().next());
			// QBO Page Getting Run time Error after clicking on Launch site buttton
			waitforElement(180, QBO.ApiKey, driver);
			if (VerifyObject(driver, QBO.ApiKey)) {
				type(QBO.ApiKey, APIkey);
				type(QBO.ApiPassword, APIPassword);
				JavaScriptclick(driver, QBO.Continue);
				waitforElement(180, QBO.ConnectQuickBooks, driver);
				// Handling Connect to Quick Books Window
				String Childwindow = driver.getWindowHandle();
				JavaScriptclick(driver, QBO.ConnectQuickBooks);
				Thread.sleep(10000);
				Set<String> Window1 = driver.getWindowHandles();
				System.out.println("1=" + Window1.size());
				Window1.remove(Childwindow);
				System.out.println("2=" + Window1.size());
				// Switching to Quick Books Companies Window (Child Window2)
				driver.switchTo().window(Window1.iterator().next());
				for (String winHandle : Window1) {
					driver.switchTo().window(winHandle);
				}
				Thread.sleep(5000);
				waitforElement(180, QBO.QBOUserid, driver);
				if (VerifyObject(driver, QBO.QBOUserid)) {
					type(QBO.QBOUserid, QBO_UserName);
					type(QBO.QBOPassword, QBO_Password);
					JavaScriptclick(driver, QBO.QBOSignIn);
					Thread.sleep(10000);
					waitforElement(60, QBO.ROQBOCOMPLIST, driver);
					// if Company List Page Not loaded it will close the Childwindow1 and
					// Childwindow2 and Switch to Parent Window
					// if(!(QBO.ROQBOCOMPLIST.isDisplayed())||QBO.QBOCONFIRMMYACCOUNT.isDisplayed())
					if (!(VerifyObject(driver, QBO.ROQBOCOMPLIST)) || VerifyObject(driver, QBO.QBOCONFIRMMYACCOUNT)) {
						driver.close();
						Thread.sleep(3000);
						driver.switchTo().window(Childwindow);
						driver.close();
						Thread.sleep(3000);
						driver.switchTo().window(Parentwindow);
						Thread.sleep(5000);
					}
					click(QBO.ROQBOCOMPLISTDROPDOWN);
					Thread.sleep(5000);
					driver.findElement(
							By.xpath("//div[@class='company-list-item']/div[contains(.,'" + ("Company") + "')]"))
							.click();
					Thread.sleep(10000);
					waitforElement(180, QBO.NEXT, driver);
					JavaScriptclick(driver, QBO.NEXT);
					waitforElement(180, QBO.CONNECT, driver);
					if (VerifyObject(driver, QBO.authorizeBtn)) {
						JavaScriptclick(driver, QBO.authorizeBtn);
					}
					if (VerifyObject(driver, QBO.CONNECTAUTHORIZE)) {
						JavaScriptclick(driver, QBO.CONNECTAUTHORIZE);
					}
					Thread.sleep(10000);
					driver.switchTo().window(Childwindow);
					waitforElement(180, QBO.taxCentralName, driver);
					if (VerifyObject(driver, QBO.taxCentralName)) {
						selectByValue(QBO.taxCentralName, Map_Company);
						JavaScriptclick(driver, QBO.submit);
						waitforElement(60, QBO.manageSalesforms, driver);
					}
				}
			}
			waitforElement(60, QBO.manageSalesforms, driver);
			JavaScriptclick(driver, QBO.manageSalesforms);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, QBO.syncsalesForms, driver);
			waitforElement(180, QBO.STARRTDATE, driver);
			click(QBO.STARRTDATE);
			QBO.STARRTDATE.sendKeys(Keys.CONTROL + "a");
			QBO.STARRTDATE.sendKeys(Keys.BACK_SPACE);
			JavaScripttype(driver, QBO.STARRTDATE, StartDate);
			Thread.sleep(2000);
			sendkeysTab(QBO.STARRTDATE);
			// assertTrue(gettitle(driver).equalsIgnoreCase("Manage Sales Form | Vertex
			// Cloud"));
			JavaScriptclick(driver, QBO.syncsalesForms);
			Thread.sleep(8000);
			waitUntilElementInvisibe(360, "//h3[contains(text(),'SYNC IN PROGRESS')]", driver);
			waitforElement(180, QBO.quickSearch, driver);
			// Searching for New Transaction Number
			JavaScriptclick(driver, QBO.quickSearch);
			clear(QBO.quickSearch);
			Thread.sleep(5000);
			type(QBO.quickSearch, invoiceno);
			Thread.sleep(5000);
			type(QBO.quickSearch, "0");
			Thread.sleep(5000);
			sendkeybackSpace(QBO.quickSearch);
			Thread.sleep(5000);
			if (QBO.NOSALESFORMS.isDisplayed()) {
				clear(QBO.quickSearch);
				Thread.sleep(5000);
				type(QBO.quickSearch, invoiceno);
				Thread.sleep(5000);
				type(QBO.quickSearch, "0");
				Thread.sleep(5000);
				sendkeybackSpace(QBO.quickSearch);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//table/tbody/tr[3]/td[contains(.,'" + invoiceno + "')]/../td/input[2]"))
						.click();
			}
			// JavaScriptclick(driver, QBO.formDatecheck);
			else {
				driver.findElement(By.xpath("//table/tbody/tr[3]/td[contains(.,'" + invoiceno + "')]/../td/input[2]"))
						.click();
			}
			Thread.sleep(5000);
			if (Browser.equalsIgnoreCase("IE")) {
				click(QBO.selectAction);
			} else {
				JavaScriptclick(driver, QBO.selectAction);
			}
			Thread.sleep(2000);
			JavaScriptclick(driver, QBO.estimateTax);
			Thread.sleep(5000);
			waitforText(360, "//table/tbody/tr[3]/td[contains(text(),'Tax Estimated')]", driver);
			JavaScriptclick(driver, QBO.addressValidation);
			waitforElement(180, QBO.synccustomerRecords, driver);
			JavaScriptclick(driver, QBO.synccustomerRecords);
			Thread.sleep(8000);
			waitUntilElementInvisibe(360, "//h3[contains(text(),'SYNC IN PROGRESS')]", driver);
			JavaScriptclick(driver, QBO.validate);
			waitforElement(180, QBO.suggestaddress, driver);
			JavaScriptclick(driver, QBO.suggestaddress);
			JavaScriptclick(driver, QBO.save);
			waitforText(120, QBO.validationStatus, driver);
			assertTrue(VerifyObject(driver, QBO.validationStatus));
			JavaScriptclick(driver, QBO.settings);
			waitforElement(120, QBS.addcompanyMapping, driver);
			driver.close();
			driver.switchTo().window(Parentwindow);
			waitforElement(60, QBO.LaunchSite, driver);
		}
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

	public void QBOLogout() throws Exception {

		QBOManageForms QBO = PageFactory.initElements(driver, QBOManageForms.class);
		waitforElement(180, QBO.SETTINGS, driver);
		JavaScriptclick(driver, QBO.SETTINGS);
		waitforElement(180, QBO.QBOSIGNOUT, driver);
		JavaScriptclick(driver, QBO.QBOSIGNOUT);
		waitforElement(180, QBO.QBOSignIn, driver);
	}

	public void QBOSandboxCompany(String Subscription, String QBOSandboxCompany) throws Exception {

		QBOManageForms QBO = PageFactory.initElements(driver, QBOManageForms.class);
		// WebElement iframe =
		// driver.findElement(By.xpath("//iframe[@id='qbo-auth-login-handler-iframe-view']"));
		// driver.switchTo().frame(iframe);
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

}
