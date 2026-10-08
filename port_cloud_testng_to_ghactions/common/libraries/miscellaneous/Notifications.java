package common.libraries.miscellaneous;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.NotificationMessages;
import common.utilities.CloudData;

public class Notifications extends CommonFunctions {

	private WebDriver driver;

	public Notifications(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void NotificationMessages(String TestPath, String value) throws Exception {
		if (configstatus == "ConfigFail") {
			// status="ConfigFail";
		} else {
			NotificationMessages NTM = PageFactory.initElements(driver, NotificationMessages.class);
			int i;
			JavaScriptclick(driver, NTM.MESSAGEICON);
			waitforElement(180, NTM.MESSAGESTEXT, driver);
			Thread.sleep(5000);
			CloudData.dataload(TestPath, value);
			for (i = CloudData.start_row; i <= CloudData.end_row; i++) {
				CloudData.setrowdata(i);
				if (CloudData.get_data("Messages").equalsIgnoreCase("Vertex Cloud Configuration Update")) {
					if (VerifyObject(driver, NTM.SMBCONFIGURATION)) {
						if (CloudData.get_data("Delete").equalsIgnoreCase("Yes")) {
							while (driver.findElements(By.xpath(
									"//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Configuration Update']"))
									.size() != 0) {
								JavaScriptclick(driver, NTM.SMBCONFIGURATION);
								waitforElement(180, NTM.CONFIGURATIONHEADING, driver);
								assertTrue(VerifyObject(driver, NTM.CONFIGURATIONHEADING));
								click(NTM.DELETEICON);
								switchToAlertAccept(driver);
							}
							assertTrue(VerifyObject(driver, NTM.SMBCONFIGURATION));
						}
					} else {
						assertTrue(VerifyObject(driver, NTM.SMBCONFIGURATION));
					}
				}

				if (CloudData.get_data("Messages").equalsIgnoreCase("Vertex Cloud Taxability Report")) {
					if (VerifyObject(driver, NTM.SMBTAXABILITYREPORT)) {
						if (CloudData.get_data("Delete").equalsIgnoreCase("Yes")) {
							while (driver.findElements(By.xpath(
									"//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Taxability Report']"))
									.size() != 0) {
								JavaScriptclick(driver, NTM.SMBTAXABILITYREPORT);
								waitforElement(180, NTM.TAXABILITYREPORTHEADING, driver);
								assertTrue(VerifyObject(driver, NTM.TAXABILITYREPORTHEADING));
								click(NTM.DELETEICON);
								switchToAlertAccept(driver);
							}
							assertTrue(VerifyObject(driver, NTM.SMBTAXABILITYREPORT));
						}
					} else {
						assertTrue(VerifyObject(driver, NTM.SMBTAXABILITYREPORT));
					}
				}
				if (CloudData.get_data("Messages").equalsIgnoreCase("Vertex Cloud My Rate File is available")) {
					if (VerifyObject(driver, NTM.SMBRATEFILE)) {
						if (CloudData.get_data("Delete").equalsIgnoreCase("Yes")) {
							while (driver.findElements(By.xpath(
									"//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Rate File Available']"))
									.size() != 0) {
								JavaScriptclick(driver, NTM.SMBRATEFILE);
								waitforElement(180, NTM.RATEFILEHEADING, driver);

								click(NTM.DELETEICON);
								switchToAlertAccept(driver);
							}
						}
					}
				}
				if (CloudData.get_data("Messages").equalsIgnoreCase("Vertex Cloud Report Generated")) {
					if (VerifyObject(driver, NTM.SMBREPORTS)) {
						if (CloudData.get_data("Delete").equalsIgnoreCase("Yes")) {
							while (driver.findElements(By.xpath(
									"//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Report Generated']"))
									.size() != 0) {
								JavaScriptclick(driver, NTM.SMBREPORTS);
								waitforElement(180, NTM.REPORTSHEADING, driver);
								click(NTM.DELETEICON);
								switchToAlertAccept(driver);
							}
						}
					}
				}
				if (CloudData.get_data("Messages").equalsIgnoreCase("Vertex Cloud Returns Generated")) {
					if (VerifyObject(driver, NTM.RETURNS)) {
						if (CloudData.get_data("Delete").equalsIgnoreCase("Yes")) {
							while (driver.findElements(By.xpath(
									"//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Returns Generated']"))
									.size() != 0) {
								JavaScriptclick(driver, NTM.RETURNS);
								waitforElement(180, NTM.RETURNSHEADING, driver);

								click(NTM.DELETEICON);
								switchToAlertAccept(driver);
							}
						}
					}
				}

			}
		}
	}

	public void notificationMessages(String messages, String delete) throws Exception {
		NotificationMessages NTM = PageFactory.initElements(driver, NotificationMessages.class);
		JavaScriptclick(driver, NTM.MESSAGEICON);
		waitforElement(180, NTM.MESSAGESTEXT, driver);
		Thread.sleep(5000);
		if (messages.equals("Vertex Cloud Configuration Update")) {
			if (VerifyObject(driver, NTM.SMBCONFIGURATION)) {
				if (delete.equalsIgnoreCase("Yes")) {
					while (driver
							.findElements(By.xpath(
									"//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Configuration Update']"))
							.size() != 0) {
						JavaScriptclick(driver, NTM.SMBCONFIGURATION);
						waitforElement(180, NTM.CONFIGURATIONHEADING, driver);
						assertTrue(VerifyObject(driver, NTM.CONFIGURATIONHEADING));
						click(NTM.DELETEICON);
						switchToAlertAccept(driver);
					}
					assertTrue(VerifyObject(driver, NTM.SMBCONFIGURATION));
				}
			} else {
				assertTrue(VerifyObject(driver, NTM.SMBCONFIGURATION));
			}
		}

		if (messages.equals("Vertex Cloud Taxability Report")) {
			if (VerifyObject(driver, NTM.SMBTAXABILITYREPORT)) {
				if (delete.equalsIgnoreCase("Yes")) {
					while (driver
							.findElements(By.xpath(
									"//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Taxability Report']"))
							.size() != 0) {
						JavaScriptclick(driver, NTM.SMBTAXABILITYREPORT);
						waitforElement(180, NTM.TAXABILITYREPORTHEADING, driver);
						assertTrue(VerifyObject(driver, NTM.TAXABILITYREPORTHEADING));
						click(NTM.DELETEICON);
						switchToAlertAccept(driver);
					}
					assertTrue(VerifyObject(driver, NTM.SMBTAXABILITYREPORT));
				}
			} else {
				assertTrue(VerifyObject(driver, NTM.SMBTAXABILITYREPORT));
			}
		}
		if (messages.equals("Vertex Cloud My Rate File is available")) {
			if (VerifyObject(driver, NTM.SMBRATEFILE)) {
				if (delete.equalsIgnoreCase("Yes")) {
					while (driver
							.findElements(By.xpath(
									"//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Rate File Available']"))
							.size() != 0) {
						JavaScriptclick(driver, NTM.SMBRATEFILE);
						waitforElement(180, NTM.RATEFILEHEADING, driver);

						click(NTM.DELETEICON);
						switchToAlertAccept(driver);
					}
				}
			}
		}
		if (messages.equals("Vertex Cloud Report Generated")) {
			if (VerifyObject(driver, NTM.SMBREPORTS)) {
				if (delete.equalsIgnoreCase("Yes")) {
					while (driver
							.findElements(By.xpath(
									"//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Report Generated']"))
							.size() != 0) {
						JavaScriptclick(driver, NTM.SMBREPORTS);
						waitforElement(180, NTM.REPORTSHEADING, driver);
						click(NTM.DELETEICON);
						switchToAlertAccept(driver);
					}
				}
			}
		}
		if (messages.equals("Vertex Cloud Returns Generated")) {
			if (VerifyObject(driver, NTM.RETURNS)) {
				if (delete.equalsIgnoreCase("Yes")) {
					while (driver
							.findElements(By.xpath(
									"//div[@class='col-content']/ul/li/a[text()='Vertex Cloud Returns Generated']"))
							.size() != 0) {
						JavaScriptclick(driver, NTM.RETURNS);
						waitforElement(180, NTM.RETURNSHEADING, driver);

						click(NTM.DELETEICON);
						switchToAlertAccept(driver);
					}
				}
			}
		}
	}

}
