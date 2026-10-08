package common.libraries.connectors;

import java.awt.Robot;
import java.io.File;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.InputSource;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import common.libraries.database.DataBase;
import common.libraries.generic.CommonFunctions;
import common.pages.AdminUndoAdjustmentFile;
import common.pages.AdminUserLinks;
import common.pages.ConfigurePage;
import common.pages.MyInterfacePage;
import common.pages.ProfessionalUserLinks;
import common.pages.QBOFirstTimeMap;
import common.pages.QBOManageForms;
import common.pages.TaxpayerPartitionsused;
import common.pages.Treasurybillingscreens;
import common.pages.UploadAdjustmentFile;
import common.pages.VertexSMBCustomers;
import common.utilities.UtilityScript;

public class Connectors extends CommonFunctions {

	private WebDriver driver;

	public Connectors(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	DataBase db = PageFactory.initElements(driver, DataBase.class);

	public String RESTSoap(String foldrname, String fileName, String WSDLurl) throws Exception {
		String status = "";
		String xmlrep = null;
		try {
			File directory = new File(".//");
			String filePath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\upload" + "\\"
					+ "ConnectorTransactions" + "\\" + foldrname + "\\" + fileName;
			System.out.println("File path " + filePath);
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
			return e.getMessage() + status;
		}
		return status;
	}

	public void ReturnsOnlyIntegration(String Subscription, String Action, String Available_ERPs,
			String ERP_Description, String QBO_Username, String QBO_Password, String Map_Company, String Company,
			String Verify_Duplicatename, String Statusvalue) throws Exception {
		String parentwindow;
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		QBOFirstTimeMap QBO = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		waitforElement(60, PUL.SETTINGSLINK, driver);
		click(PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN1, driver);
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		if (Subscription.equalsIgnoreCase("Professional - Tax calc and returns")
				|| Subscription.equalsIgnoreCase("Premium - Tax calc and returns")) {
			if (VerifyObject(driver, PUL.ReturnsOnlySetup)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		} else {
			assertTrue(PUL.ReturnsOnlyIntegration.isDisplayed());
			assertTrue(PUL.ReturnsOnlyText.isDisplayed());
			assertTrue(PUL.RETURNSONLYVERBAGE.isDisplayed());
			click(PUL.ReturnsOnlySetup);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(2000);
			assertTrue(PUL.ROIntegrationInstances.isDisplayed());
			assertTrue(PUL.DateAdded.isDisplayed());
			assertTrue(PUL.ACTION.isDisplayed());
			assertTrue(PUL.Statuscolumn.isDisplayed());
			assertTrue(PUL.RETURNSINSTANCENAME.isDisplayed());
			assertTrue(PUL.ReturnToConnectors.isDisplayed());
			assertTrue(QBO.STATUSLABEL.isDisplayed());
			Select defaultvalue = new Select(QBO.STATUS);
			assertTrue(defaultvalue.getFirstSelectedOption().getText().trim().equalsIgnoreCase("Show All"));
			click(PUL.ReturnToConnectors);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			click(PUL.ReturnsOnlySetup);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			driver.getTitle().equalsIgnoreCase("View All Connectors | Vertex Cloud");
			switch (Action) {
			case "Create":
				click(PUL.ADDCONNECTION);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				assertTrue(PUL.ERPCONFIGURATION.isDisplayed());
				assertTrue(PUL.AVAILABLEAPITEXT.isDisplayed());
				assertTrue(PUL.APIDESCRIPTION.isDisplayed());

				click(PUL.RETURNTORETURNSINSTACES);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				click(PUL.ADDCONNECTION);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.RETURNTORETURNSINSTACESLEFT, driver);
				click(PUL.RETURNTORETURNSINSTACESLEFT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.ADDCONNECTION, driver);
				click(PUL.ADDCONNECTION);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.RETURNTORETURNSINSTACESLEFT, driver);
				selectByValue(PUL.AVAILABLEERPS, Available_ERPs);
				Thread.sleep(2000);
				String[] regcData1 = ERP_Description.split(";");
				Description = xLabelRND(regcData1);
				type(PUL.ERPDESCRIPTION, Description);
				parentwindow = driver.getWindowHandle();
				Thread.sleep(2000);
				click(PUL.SUBMITERP);
				waitforElement(180, PUL.CANCELLINK, driver);
				click(PUL.CANCELLINK);
				waitforElement(180, PUL.SUBMITERP, driver);
				click(PUL.SUBMITERP);
				Thread.sleep(10000);
				for (String winHandle : driver.getWindowHandles()) {
					if (!winHandle.equals(parentwindow)) {
						driver.switchTo().window(winHandle);
					}
				}
				Thread.sleep(10000);
				if (VerifyObject(driver, QBO.QBOUserid)) {
					waitforElement(180, QBO.QBOUserid, driver);
					type(QBO.QBOUserid, QBO_Username);
					type(QBO.QBOPassword, QBO_Password);
					click(QBO.QBOSignIn);
				}
				Thread.sleep(10000);

				if (Map_Company.equalsIgnoreCase("YES")) {
					waitforElement(180, QBO.ROQBOCOMPLIST, driver);
					if (VerifyObject(driver, QBO.ROQBOCOMPLIST)) {
						System.out.println(QBO.quickbookcompanies.size());
						click(QBO.ROQBOCOMPLIST);
						Thread.sleep(2000);
						driver.findElement(
								By.xpath("//div[@class='company-list-item']/div[contains(.,'" + Company + "')]"))
								.click();
						waitforElement(180, QBO.NEXT, driver);
						click(QBO.NEXT);
						waitforElement(180, QBO.CONNECT, driver);
						if (VerifyObject(driver, QBO.CONNECT)) {
							click(QBO.CONNECT);
						}
						Thread.sleep(5000);
						driver.switchTo().window(parentwindow);
						if (Verify_Duplicatename.equalsIgnoreCase("YES")) {
							JavaScriptclick(driver, PUL.ADDCONNECTION);
							waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
							selectByValue(PUL.AVAILABLEERPS, Available_ERPs);
							type(PUL.ERPDESCRIPTION, Description);
							assertTrue(QBO.ERPDESCRIPTIONERROR.isDisplayed());
							JavaScriptclick(driver, PUL.RETURNTORETURNSINSTACES);
						}
						waitforElement(180, PUL.ReturnsOIntegration, driver);
						selectByValue(QBO.STATUS, Statusvalue);
						assertTrue(VerifyObject(driver, "//table/tbody/tr/td[contains(.,'Quickbooks Online-"
								+ Description + "')]/../td[contains(.,'Active')]"));
					} else {
						driver.close();
						Thread.sleep(2000);
						driver.switchTo().window(parentwindow);
						Thread.sleep(2000);
					}
				}
				break;

			case "Delete":
				selectByValue(CNP.PAGESIZE, "25 per page");
				selectByValue(CNP.PAGESIZE, "50 per page");
				selectByValue(CNP.PAGESIZE, "100 per page");
				waitforElement(180, "//table/tbody/tr/td[contains(.,'Quickbooks Online-" + Description
						+ "')]/../td/a[contains(.,'Delete')]", driver);
				driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'Quickbooks Online-" + Description
						+ "')]/../td/a[contains(.,'Delete')]")).click();
				waitforElement(180, PUL.DELETERETURNSINTEGRATION, driver);
				JavaScriptclick(driver, PUL.DELETERETURNSINTEGRATION);
				Thread.sleep(2000);
				waitUntilElementInvisibe(180, QBO.REQUESTPROCESSING, driver);
				Calendar.getInstance();
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				System.out.println(dateFormat);
				Date date = new Date();
				String date1 = dateFormat.format(date);
				System.out.println(date1);
				assertTrue(VerifyObject(driver, "//table/tbody/tr/td[contains(.,'Quickbooks Online-" + Description
						+ "')]/../td[contains(.,'Deleted " + date1 + "')]"));
				selectByValue(QBO.STATUS, Statusvalue);
				assertTrue(VerifyObject(driver, "//table/tbody/tr/td[contains(.,'Quickbooks Online-" + Description
						+ "')]/../td[contains(.,'Inactive')]"));
				if (VerifyObject(driver, QBO.DELETEDLINK)) {
					assertTrue(true);
				} else {
					assertFalse(false);
				}
				break;
			}
			Thread.sleep(5000);
			if (VerifyObject(driver, PUL.ReturntoOracleinstances)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
			if (VerifyObject(driver, PUL.ReturnsOnlyAPIInstances)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
	}

	public void ReturnOnlySyncConnector(String Without_Taxpayer, String Taxpayer_Name, String From_date, String To_Date,
			String No_Transactions) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		UploadAdjustmentFile UAF = PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		int count = 0;
		waitforElement(90, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, UAF.EXTENDEDHELP, driver);

		if (Without_Taxpayer.equalsIgnoreCase("Yes")) {
			assertTrue(UAF.UNPUBLISHED.isDisplayed());
		} else {
			End: if (VerifyObject(driver, UAF.UNPUBLISHED)) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(10000);
					count = count + 1;
					if (count == 80) {
						break End;
					}
					if (!VerifyObject(driver, UAF.UNPUBLISHED)) {
						report = true;
					}
				} while (report == false);
			}
			JavaScriptclick(driver, UAF.UPLOADDATA);
			waitforElement(180, UAF.SYNCCONNECTOR, driver);
			JavaScriptclick(driver, UAF.SYNCCONNECTOR);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, UAF.RETURNTORETURNSONLYADJUSTMENT, driver);
			click(UAF.RETURNTORETURNSONLYADJUSTMENT);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			assertTrue(UAF.UPLOADDATA.isDisplayed());
			JavaScriptclick(driver, UAF.UPLOADDATA);
			waitforElement(180, UAF.SYNCCONNECTOR, driver);
			JavaScriptclick(driver, UAF.SYNCCONNECTOR);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, UAF.RETURNTORETURNSONLYADJUSTMENT, driver);
			assertTrue(UAF.SELECTRETURNSINSTANCE.isDisplayed());
			assertTrue(UAF.SELECTCOMPANY.isDisplayed());
			assertTrue(UAF.SELECTTIMEPERIOD.isDisplayed());
			selectByValue(UAF.SELECTTAXPAYERS, Taxpayer_Name);
			Thread.sleep(2000);
			clear(UAF.STARTDATE);
			Thread.sleep(3000);
			click(UAF.STARTDATE);
			type(UAF.STARTDATE, From_date);
			click(UAF.ENDDATE);
			clear(UAF.ENDDATE);
			Thread.sleep(2000);
			click(UAF.ENDDATE);
			type(UAF.ENDDATE, To_Date);
			click(UAF.SYNC);
			waitforElement(180, UAF.FILEGENERATIONTEXT, driver);
			waitUntilElementInvisibe(360, UAF.FILEGENERATIONTEXT, driver);
			if (No_Transactions.equalsIgnoreCase("YES")) {
				assertTrue(PUL.NOTRANSACTIONSERROR.isDisplayed());
			} else {
				if(VerifyObject(driver,UAF.ERRORSFOUND)) {
				assertTrue(UAF.ERRORSFOUND.isDisplayed());}
			}
		}
	}

	public void QBOCDisconnect(String QuickBooks_company, String QBO_Action, String Sandbox_Verify, String QBO_UserName,
			String QBO_Password, String Map_Company) throws Exception {
		String Company, QBOAction, Sandboxverify, Childwindow;
		QBOFirstTimeMap QBO = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		QBOManageForms QBOM = PageFactory.initElements(driver, QBOManageForms.class);
		Company = QuickBooks_company;
		QBOAction = QBO_Action;
		Sandboxverify = Sandbox_Verify;
		/*
		 * if (!VerifyObject(driver, PUL.SETTINGSLINK)) { switch (xEnvironmentType()) {
		 * case "DEV": case "CLOUDELEMENTS":
		 * driver.get("https://devportal.vertexsmb.com"); break;
		 * 
		 * case "QA": driver.get("https://qaportal.vertexsmb.com"); break;
		 * 
		 * case "STAGE": driver.get("https://stage.vertexsmb.com"); break; }
		 * bussFunction.Login("Login;Login;3"); }
		 */
		waitforElement(180, PUL.SETTINGSLINK, driver);
		JavaScriptMousehover(driver, PUL.SETTINGSLINK);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN1, driver);
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, QBO.LaunchSite, driver);
		String Parentwindow = driver.getWindowHandle();
		if (Browser.equalsIgnoreCase("IE")) {
			JavaScriptclick(driver, QBO.LaunchSite);
		} else {
			click(QBO.LaunchSite);
		}
		Thread.sleep(8000);
		Set<String> Window = driver.getWindowHandles();
		Window.remove(Parentwindow);
		driver.switchTo().window(Window.iterator().next());
		waitforElement(30, QBO.APITEXT, driver);
		if (VerifyObject(driver, QBO.ApiKey)) {
			type(QBO.ApiKey, APIkey);
			type(QBO.ApiPassword1, APIPassword);
			JavaScriptclick(driver, QBO.Continue1);
			waitforElement(60, QBO.ConnectQuickBooks, driver);
		}
		if (VerifyObject(driver, QBO.ConnectQuickBooks)) {
			waitforElement(180, QBO.ConnectQuickBooks, driver);
			// Handling Connect to Quick Books Window
			Childwindow = driver.getWindowHandle();
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
			waitforElement(30, QBO.QBOUserid, driver);
			if (VerifyObject(driver, QBO.QBOUserid)) {
				type(QBO.QBOUserid, QBO_UserName);
				type(QBO.QBOPassword, QBO_Password);
				JavaScriptclick(driver, QBO.QBOSignIn);
				Thread.sleep(10000);
				waitforElement(300, QBO.ROQBOCOMPLIST, driver);
			}
			// if Company List Page Not loaded it will close the Childwindow1 and
			// Childwindow2 and Switch to Parent Window
			if (!QBO.ROQBOCOMPLIST.isDisplayed()) {
				driver.close();
				Thread.sleep(3000);
				driver.switchTo().window(Childwindow);
				driver.close();
				Thread.sleep(3000);
				driver.switchTo().window(Parentwindow);
				Thread.sleep(5000);
			}
			click(QBO.ROQBOCOMPLIST);
			Thread.sleep(2000);
			driver.findElement(
					By.xpath("//div[@class='company-list-item']/div[contains(.,'" + QuickBooks_company + "')]"))
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
			selectByValue(QBO.taxCentralName, Map_Company);
			JavaScriptclick(driver, QBO.submit);
		}
		waitforElement(60, QBO.manageSalesforms, driver);
		switch (QBOAction) {

		case "Connect":
			click(QBO.manageSalesforms);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			break;

		case "Disconnect":
			click(QBO.settings);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, QBO.DISCONNECT, driver);
			click(QBO.DISCONNECT);
			waitforElement(180, QBO.DISCONNECTCONFIRM, driver);
			click(QBO.DISCONNECTCONFIRM);
			waitforElement(180, QBO.ConnectQuickBooks, driver);
		}
		driver.close();
		Thread.sleep(1000);
		driver.switchTo().window(Parentwindow);
		driver.get("https://sandbox.qbo.intuit.com");
		waitforElement(30, QBOM.QBOUserid, driver);
		if (VerifyObject(driver, QBOM.QBOUserid)) {
			clear(QBOM.QBOUserid);
			type(QBOM.QBOUserid, QBO_UserName);
			clear(QBOM.QBOPassword);
			type(QBOM.QBOPassword, QBO_Password);
			Thread.sleep(2000);
			JavaScriptclick(driver, QBOM.QBOSignIn);
		}
		waitforElement(180, QBOM.PLEASESELECTCOMPANYTEXT, driver);
		switch (Company) {

		case "QBO_Automation_Smoke":
			driver.findElement(By.xpath("//div/ul/li[2]/a")).click();
			break;

		case "QBO_Automation_Professional":
			driver.findElement(By.xpath("//div/ul/li[5]/a")).click();
			break;

		case "QBO_Automation_Premium":
			driver.findElement(By.xpath("//div/ul/li[4]/a")).click();
			break;
		}
		waitforElement(30, QBOM.APPS, driver);
		driver.get("https://app.sandbox.qbo.intuit.com/app/appcenter/myapps");
		//JavaScriptclick(driver, QBOM.APPS);
		Thread.sleep(3000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, QBOM.VERTEXCLOUDRETURNSCONNECTOR, driver);
		switch (Sandboxverify) {

		case "Added":
			assertTrue(QBOM.VERTEXCLOUDRETURNSCONNECTOR.isDisplayed());
			break;

		case "Deleted":
			if (!VerifyObject(driver, QBOM.VERTEXCLOUDRETURNSCONNECTOR)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
		waitforElement(180, QBOM.QICON, driver);
		JavaScriptclick(driver, QBOM.QICON);
		waitforElement(180, QBOM.SIGNOUT, driver);
		JavaScriptclick(driver, QBOM.SIGNOUT);
		waitforElement(60, QBOM.QBOUserid, driver);

	}

	public void AdminReturnsOnlyIntegration(String ClientName) throws Exception {
		int count = 0;
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		click(AUA.manageSMBCustomers);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		type(AUA.smbCustomerName, ClientName);
		Thread.sleep(5000);
		if (!VerifyObject(driver, "//table/tbody/tr/td/div/a[contains(.,'" + ClientName + "')]")) {
			boolean report = false;
			count = 0;
			do {
				driver.navigate().refresh();
				Thread.sleep(20000);
				type(VSC.SMBCUSTNAMESB, ClientName);
				Thread.sleep(5000);
				count = count + 1;
				if (count == 26) {
					break;
				}
				if (VerifyObject(driver, "//table/tbody/tr/td/a[contains(.,'" + ClientName + "')]")) {
					report = true;
				}
			} while (report == false);
		}

		if (VerifyObject(driver, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
				+ "')]/../../../td[contains(text(),'Registered')]")) {
			WebElement registered = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Registered')]/../td[1]/div/a"));
			JavaScriptclick(driver, registered);
		} else {
			WebElement authorized = driver
					.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
							+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
			JavaScriptclick(driver, authorized);
		}
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		click(AUA.CONNECTORS);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, VSC.RETURNSONLYINTEGRATIONINSTANCE, driver);
		selectByValue(VSC.paginationDD, "25 per page");
		selectByValue(VSC.paginationDD, "50 per page");
		selectByValue(VSC.paginationDD, "100 per page");
		assertTrue(VSC.RETURNSONLYINTEGRATIONINSTANCE.isDisplayed());
		assertTrue(VSC.RODATEADDED.isDisplayed());
		assertTrue(VSC.RETURNSONLYINSTANCENAMECOL.isDisplayed());
		assertTrue(VerifyObject(driver, "//table/tbody/tr/td[contains(.,'Quickbooks Online-" + Description + "')]"));
	}

	public void TreasuryBillingUI(String Browser) throws Exception {
		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, AdminUserLinks.class);
		JavaScriptclick(driver, TBS.ORACLEBILLING);
		waitforElement(180, TBS.ORACLEBILLINGHEADING, driver);
		assertTrue(TBS.ORACLEBILLINGHEADING.isDisplayed());
		Select Year = new Select(TBS.YEAR);
		String DefaultYear = Year.getFirstSelectedOption().getText();
		assertTrue(DefaultYear.equalsIgnoreCase("2020"));
		Select month = new Select(TBS.BILLINGMONTH);
		String Defaultmonth = month.getFirstSelectedOption().getText();
		assertTrue(Defaultmonth.equalsIgnoreCase("April"));
		assertTrue(TBS.CLOUDCUSTOMERNAME.isDisplayed());
		assertTrue(TBS.CLOUDCUSTOMERNUMBER.isDisplayed());
		assertTrue(TBS.EXPORTDATA.isDisplayed());
		click(TBS.EXPORTDATA);
		Thread.sleep(20000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Reports";
			xDownloadFiles("Reports");
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(10000);
			LatestDownloadedFile("Reports", true);
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			Thread.sleep(5000);
			LatestDownloadedFile("Reports", false);
		}
		click(TBS.TESTACCOUNTS);
		Thread.sleep(3000);
		click(TBS.ACTIVEACCOUNTS);
		waitforElement(180, TBS.EDITUSERSETTINGS, driver);
		click(TBS.EDITUSERSETTINGS);
		waitforElement(180, TBS.RETURNTOORACLEBILLING, driver);
		JavaScriptclick(driver, TBS.RETURNTOORACLEBILLING);
		waitforElement(180, TBS.CLOUDCUSTOMERNAMECOL, driver);
		assertTrue(TBS.CLOUDCUSTOMERNAMECOL.isDisplayed());
		assertTrue(TBS.ORACLEACCOUNTNUMBERCOL.isDisplayed());
		assertTrue(TBS.ORACLEPARTYNUMBERCOL.isDisplayed());
		assertTrue(TBS.CLOUDCUSTOMERNUMBERCOL.isDisplayed());
		assertTrue(TBS.SUBSCRIPTIONCOL.isDisplayed());
		assertTrue(TBS.CLOUDCUSTOMERSTATUS.isDisplayed());
		assertTrue(TBS.VARCOL.isDisplayed());
		assertTrue(TBS.DOCUMENTSCOL.isDisplayed());
		assertTrue(TBS.PAYMENTSTATUSCOL.isDisplayed());
		selectByValue(TBS.PAGINATION, "25 per page");
		selectByValue(TBS.PAGINATION, "50 per page");
		selectByValue(TBS.PAGINATION, "100 per page");
		type(TBS.CLOUDCUSTOMERNAME, VertexClientName);
		Thread.sleep(6000);
		// assertTrue(VerifyObject(driver,
		// "//table/thead/tr/th/div/span[contains(.,'Oracle Party
		// Number')]/../../../../../tbody/tr/td[contains(.,'"+oraclenumber+"')]"));
		WebElement viewdetails = driver.findElement(By.xpath("//table/tbody/tr/td/a[contains(.,'" + VertexClientName
				+ "')]/../../td/a[contains(.,'+ View Details')]"));
		JavaScriptclick(driver, viewdetails);
		waitforElement(180, TBS.TRANSACTIONDETAILSHEADING, driver);
		assertTrue(TBS.TRANSACTIONDETAILSHEADING.isDisplayed());
		assertTrue(TBS.TRANSACTIONTYPECOL.isDisplayed());
		assertTrue(TBS.TRANSACTIONDATECOL.isDisplayed());
		assertTrue(TBS.RECEIPTNUMBERCOL.isDisplayed());
		assertTrue(TBS.SUBTOTALCOL.isDisplayed());
		assertTrue(TBS.TAXCOL.isDisplayed());
		assertTrue(TBS.GRANDTOTALCOL.isDisplayed());
		assertTrue(TBS.CURRENCYCODECOL.isDisplayed());
		JavaScriptclick(driver, TBS.HIDEDETAILS);
		waitforElement(180, TBS.RUNBILLINGBTN, driver);
		JavaScriptclick(driver, TBS.RUNBILLINGBTN);
		waitforElement(180, TBS.INITIATEORACLEHEADING, driver);
		assertTrue(TBS.CUSTOMERNAMERB.isDisplayed());
		assertTrue(TBS.CUSTOMERNUMBER.isDisplayed());
		Select Yearbilling = new Select(TBS.YEAR);
		String yearvalue = Yearbilling.getFirstSelectedOption().getText();
		assertTrue(yearvalue.equalsIgnoreCase("2020"));
		Select billingmonth = new Select(TBS.BILLINGMONTH);
		String Defaultbmonth = billingmonth.getFirstSelectedOption().getText();
		assertTrue(Defaultbmonth.equalsIgnoreCase("April"));
		click(TBS.TESTACCOUNTS);
		Thread.sleep(3000);
		click(TBS.ACTIVEACCOUNTS);
		waitforElement(180, TBS.CLOUDCUSTOMERNAMECOL, driver);
		assertTrue(TBS.CLOUDCUSTOMERNAMECOL.isDisplayed());
		assertTrue(TBS.CLOUDCUSTOMERNUMBERCOL.isDisplayed());
		assertTrue(TBS.SUBSCRIPTIONCOL.isDisplayed());
		assertTrue(TBS.CLOUDCUSTOMERSTATUS.isDisplayed());
		assertTrue(TBS.VARCOL.isDisplayed());
		assertTrue(TBS.ORACLESTATUS.isDisplayed());
		selectByValue(TBS.PAGINATION, "25 per page");
		selectByValue(TBS.PAGINATION, "50 per page");
		selectByValue(TBS.PAGINATION, "100 per page");
		type(TBS.CUSTOMERNAMERB, VertexClientName);
		Thread.sleep(5000);
		WebElement checkbox = driver.findElement(By.xpath("//td[text()='" + VertexClientName + "']/../td/input"));
		JavaScriptclick(driver, checkbox);
		assertTrue(TBS.INITIATEBILLRUN.isDisplayed());
		JavaScriptclick(driver, TBS.ORACLEBILLINGHISTORY);
		waitforElement(180, TBS.ORACLEBILLINGHEADING, driver);
		assertTrue(TBS.ORACLEBILLINGHEADING.isDisplayed());
		type(TBS.CLOUDCUSTOMERNAME, VertexClientName);
		Thread.sleep(6000);
		WebElement customer = driver
				.findElement(By.xpath("//table/tbody/tr/td/a[contains(.,'" + VertexClientName + "')]"));
		JavaScriptclick(driver, customer);
		waitforElement(180, TBS.RETURNORACLEBILLINGHISTORY, driver);
		if (VerifyObject(driver, TBS.NORECORDS)) {
			assertTrue(true);
		}
		selectByValue(TBS.PAGINATION, "25 per page");
		selectByValue(TBS.PAGINATION, "50 per page");
		selectByValue(TBS.PAGINATION, "100 per page");
		JavaScriptclick(driver, TBS.RETURNORACLEBILLINGHISTORY);
		waitforElement(180, TBS.ORACLEBILLINGHEADING, driver);
		assertTrue(TBS.ORACLEBILLINGHEADING.isDisplayed());

	}

	public void VerifySyncConnectorNoConfig(String Company_Name) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		UploadAdjustmentFile UAF = PageFactory.initElements(driver, UploadAdjustmentFile.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(90, PUL.CONFIGURELINK, driver);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		Thread.sleep(5000);
		waitforElement(180,
				"//h2[contains(.,'" + Company_Name + "')]/../../div/a[contains(text(),'Save Configuration')]", driver);
		WebElement saveconfig = driver.findElement(By
				.xpath("//h2[contains(.,'" + Company_Name + "')]/../../div/a[contains(text(),'Save Configuration')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveconfig);
		waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
		JavaScriptclick(driver, CNP.COMPLETECONFIGPOPUP);
		Thread.sleep(2000);
		waitforText(180, CNP.BEGINREMOVAL, driver);
		waitforElement(90, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(90, UAF.EXTENDEDHELP, driver);
		assertTrue(VerifyObject(driver, PUL.NOCONFIGWARNING));
		JavaScriptclick(driver, UAF.UPLOADDATA);
		waitforElement(180, UAF.SYNCCONNECTOR, driver);
		JavaScriptclick(driver, UAF.SYNCCONNECTOR);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, UAF.RETURNTORETURNSONLYADJUSTMENT, driver);
		assertTrue(!UAF.SYNC.isEnabled());
	}

	public void addNetSuite(String apiDescription, String consumerKey, String consumerSecret, String accessTokenID,
			String accessTokenSecret, String accountID, String dateAdded, String integrationStatus) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		QBOFirstTimeMap QBO = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		waitforElement(60, PUL.SETTINGSLINK, driver);
		click(PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN, driver);
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		click(PUL.ReturnsOnlySetup);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(QBO.ADDCONNECTION);
		// Select aPI = new Select(QBO.AVAILABLEAPIS);
		// aPI.selectByValue("NetSuite");
		selectByValue(QBO.AVAILABLEAPIS, "NetSuite");
		type(QBO.APIDESCRIPTION, apiDescription);
		type(QBO.CONSUMERKEY, consumerKey);
		type(QBO.CONSUMERSECRET, consumerSecret);
		type(QBO.ACCESSTOKENID, accessTokenID);
		type(QBO.ACCESSTOKENSECRET, accessTokenSecret);
		type(QBO.ACCOUNTID, accountID);
		click(QBO.SUBMIT);
		Thread.sleep(3000);
		WebElement eDateAdded = driver
				.findElement((By.xpath("//td[contains(text(), '" + apiDescription + "')]/preceding-sibling::td[1]")));
		WebElement eStatusValue = driver
				.findElement((By.xpath("//td[contains(text(), '" + apiDescription + "')]/following-sibling::td[1]")));

		assertTrue(dateAdded.equals(eDateAdded.getText()));
		assertTrue(integrationStatus.equals(eStatusValue.getText()));

	}

	public void NetSuite_VerifyError(String apiDescription, String consumerKey, String consumerSecret,
			String accessTokenID, String accessTokenSecret, String accountID) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		QBOFirstTimeMap QBO = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		waitforElement(60, PUL.SETTINGSLINK, driver);
		click(PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN, driver);
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		click(PUL.ReturnsOnlySetup);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(QBO.ADDCONNECTION);
		// Select aPI = new Select(QBO.AVAILABLEAPIS);
		// aPI.selectByValue("NetSuite");
		selectByValue(QBO.AVAILABLEAPIS, "NetSuite");
		type(QBO.APIDESCRIPTION, apiDescription);
		type(QBO.CONSUMERKEY, consumerKey);
		type(QBO.CONSUMERSECRET, consumerSecret);
		type(QBO.ACCESSTOKENID, accessTokenID);
		type(QBO.ACCESSTOKENSECRET, accessTokenSecret);
		type(QBO.ACCOUNTID, accountID);
		click(QBO.SUBMIT);
		Thread.sleep(20000);
		assertTrue(QBO.NETSUITEERROR.isDisplayed());

	}

	public void ReturnsOnlyIntegration(String Available_ERPs, String Descriptiontext, String ClientId)
			throws Exception {
		String parentwindow;
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		QBOFirstTimeMap QBO = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		PageFactory.initElements(driver, VertexSMBCustomers.class);
		waitforElement(60, PUL.SETTINGSLINK, driver);
		click(PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.VIEWALLCONNECTORSBTN1, driver);
		JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		assertTrue(PUL.ReturnsOnlyIntegration.isDisplayed());
		assertTrue(PUL.ReturnsOnlyText.isDisplayed());
		assertTrue(PUL.RETURNSONLYVERBAGE.isDisplayed());
		click(PUL.ReturnsOnlySetup);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(PUL.ROIntegrationInstances.isDisplayed());
		assertTrue(PUL.DateAdded.isDisplayed());
		assertTrue(PUL.RETURNSINTEGRATIONACTION.isDisplayed());
		assertTrue(PUL.Statuscolumn.isDisplayed());
		assertTrue(PUL.RETURNSINSTANCENAME.isDisplayed());
		assertTrue(PUL.ReturnToConnectors.isDisplayed());
		assertTrue(QBO.STATUSLABEL.isDisplayed());
		Select defaultvalue = new Select(QBO.STATUS);
		assertTrue(defaultvalue.getFirstSelectedOption().getText().trim().equalsIgnoreCase("Show All"));
		click(PUL.ReturnToConnectors);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(PUL.ReturnsOnlySetup);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		driver.getTitle().equalsIgnoreCase("View All Connectors | Vertex Cloud");
		click(PUL.ADDCONNECTION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		assertTrue(PUL.ERPCONFIGURATION.isDisplayed());
		assertTrue(PUL.AVAILABLEAPITEXT.isDisplayed());
		assertTrue(PUL.APIDESCRIPTION.isDisplayed());
		click(PUL.RETURNTORETURNSINSTACES);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		click(PUL.ADDCONNECTION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.RETURNTORETURNSINSTACESLEFT, driver);
		click(PUL.RETURNTORETURNSINSTACESLEFT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.ADDCONNECTION, driver);
		click(PUL.ADDCONNECTION);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.RETURNTORETURNSINSTACESLEFT, driver);
		selectByValue(PUL.AVAILABLEERPS, Available_ERPs);
		Thread.sleep(2000);
		Description = Descriptiontext;
		type(PUL.ERPDESCRIPTION, Description);
		if (Available_ERPs.equalsIgnoreCase("Quickbooks Enterprise")) {
			assertTrue(PUL.QBDTEXT1.isDisplayed());
			assertTrue(PUL.QBDTEXT2.isDisplayed());
			assertTrue(PUL.QBDCONTACTSUPPORTLINK.isDisplayed());
		}
		parentwindow = driver.getWindowHandle();
		Thread.sleep(2000);
		click(PUL.QBDCLICKHERE);
		Thread.sleep(10000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		waitforElement(60, PUL.VERTEXACCOUNTUSERNAME, driver);
		assertTrue(PUL.VERTEXACCOUNTUSERNAME.isDisplayed());
		driver.close();
		Thread.sleep(2000);
		driver.switchTo().window(parentwindow);
		Thread.sleep(2000);
		waitforElement(180, PUL.QBECLIENTID, driver);
		// type(PUL.QBECLIENTID, ClientId);

	}

	public void ViewAllConnectors(String Connector_Name, String ERP_Connector_URL, String Address_Lookup_URL,
			String Environment) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		PageFactory.initElements(driver, MyInterfacePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		new Robot();
		String CName, ERPConnectURl, AddLookupURL;
		waitforElement(180, PUL.SettingsHover, driver);
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.ADAPTERSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(1000);
		waitforElement(180, PUL.TRUSTEDIDLABEL, driver);
		assertTrue(PUL.TRUSTEDIDLABEL.isDisplayed());
		assertTrue(PUL.CONNECTORNAME.isDisplayed());
		assertTrue(PUL.ERPURL.isDisplayed());
		assertTrue(PUL.LOOKUPURL.isDisplayed());
		assertTrue(PUL.OSERIESSOAPTEXT.isDisplayed());
		assertTrue(PUL.RESTAPICONNECTORS.isDisplayed());
		assertTrue(PUL.PUBLICINTERFACENAME.isDisplayed());
		assertTrue(PUL.ENDPOINTURL.isDisplayed());
		CName = Connector_Name;
		ERPConnectURl = ERP_Connector_URL.replace("env", Environment);
		AddLookupURL = Address_Lookup_URL.replace("env", Environment);
		assertTrue(driver.findElement(By.xpath("//tr/td[contains(.,'" + CName + "')]/../td[contains(.,'" + ERPConnectURl
				+ "')]/../td[contains(.,'" + AddLookupURL + "')]")).isDisplayed());

		/*
		 * assertTrue(driver .findElement(By .xpath(
		 * "//tr/td[contains(.,'Authentication')]/../td[contains(.,'https://auth.vertexsmb.com')]"
		 * )) .isDisplayed()); assertTrue(driver .findElement(By
		 * .xpath("//tr/td[contains(.,'Adjustment File')]/../td[contains(.,'https://api.vertexsmb.com')]"
		 * )) .isDisplayed());
		 */
	}

	public void ConnectorTransactions(String XMLFileName, String postfilingdate, String Company_code,
			String Soap_WebServiceURL, String update_xml, String update_transactionid, String Update_AddressClansing,
			String No_Myusage_Verify, String Loop, String transctionType) throws Exception {
		String Soapstatus = "";
		String URL = null, Postdate = null, LoopCount = null, Taxpayercode = null;
		InterfacesTrustedID();

		Postdate = postfilingdate;
		Taxpayercode = Company_code;
		URL = Soap_WebServiceURL.replace("env", Environment);

		if (update_xml.equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			us.xUpdateXML(XMLFileName, TrsID, Postdate, Taxpayercode);
		}

		if (update_transactionid.equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			transactionid = RND();
			System.out.println(transactionid);
			us.xUpdateXML(XMLFileName, TrsID, Postdate, Taxpayercode, transactionid);
		}

		if (Update_AddressClansing.equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			us.AddressCleansingxUpdateXML(XMLFileName, TrsID, Postdate);
		}
		if (!(No_Myusage_Verify).equalsIgnoreCase("Yes")) {
			URL = Soap_WebServiceURL.replace("env", Environment.toLowerCase());
			System.out.println(URL);
			if (Loop != "") {
				LoopCount = Loop;
				int Count = Integer.parseInt(LoopCount);
				for (int j = 0; j < Count; j++) {
					Soapstatus = SoapPost(XMLFileName, URL, transctionType);
					System.out.println(Soapstatus);
					if (Soapstatus.equalsIgnoreCase("Posted")) {
						assertTrue(true);
					} else {
						assertTrue(false);
					}
				}
			} else {
				Soapstatus = SoapPost(XMLFileName, URL, transctionType);
				System.out.println("Soap Response :" + Soapstatus);
				if (Soapstatus.equalsIgnoreCase("Posted")) {
					assertTrue(true);
				} else if (XMLFileName.equalsIgnoreCase("Quote_Myusage_Invalid.xml")
						|| XMLFileName.equalsIgnoreCase("Purchaseorder_Myusage_Invalid.xml")
						|| XMLFileName.equalsIgnoreCase("Quote_Myusage_Error.xml")
						|| XMLFileName.equalsIgnoreCase("Purchaseorder_Myusage_Error.xml")) {
					if (Soapstatus.contains("No locality and bad postal code")) {
						assertTrue(true);
					}
				} else {
					assertTrue(false);
				}
			}
		}
	}

	public void DeleteRollbackReversalARBillingSyncTransactions(String XMLFileName, String postfilingdate,
			String Company_code, String Soap_WebServiceURL, String update_xml, String update_transactionid,
			String Update_AddressClansing, String No_Myusage_Verify, String Loop, String transctionType)
			throws Exception {
		String Soapstatus = "";
		String URL = null, Postdate = null, LoopCount = null, Taxpayercode = null;
		InterfacesTrustedID();

		Postdate = postfilingdate;
		Taxpayercode = Company_code;
		URL = Soap_WebServiceURL.replace("env", Environment);

		if (update_xml.equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			us.xUpdateXML(XMLFileName, TrsID, Postdate, Taxpayercode);
		}

		if (update_transactionid.equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			us.xUpdateXML(XMLFileName, TrsID, Postdate, Taxpayercode, transactionid);
		}

		if (Update_AddressClansing.equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			us.AddressCleansingxUpdateXML(XMLFileName, TrsID, Postdate);
		}
		if (!(No_Myusage_Verify).equalsIgnoreCase("Yes")) {
			URL = Soap_WebServiceURL.replace("env", Environment.toLowerCase());
			System.out.println(URL);
			if (Loop != "") {
				LoopCount = Loop;
				int Count = Integer.parseInt(LoopCount);
				for (int j = 0; j < Count; j++) {
					Soapstatus = SoapPost(XMLFileName, URL, transctionType);
					System.out.println(Soapstatus);
					if (Soapstatus.equalsIgnoreCase("Posted")) {
						assertTrue(true);
					} else {
						assertTrue(false);
					}
				}
			} else {
				Soapstatus = SoapPost(XMLFileName, URL, transctionType);
				System.out.println("Soap Response :" + Soapstatus);
				if (Soapstatus.equalsIgnoreCase("Posted")) {
					assertTrue(true);
				} else if (XMLFileName.equalsIgnoreCase("Quote_Myusage_Invalid.xml")
						|| XMLFileName.equalsIgnoreCase("Purchaseorder_Myusage_Invalid.xml")
						|| XMLFileName.equalsIgnoreCase("Quote_Myusage_Error.xml")
						|| XMLFileName.equalsIgnoreCase("Purchaseorder_Myusage_Error.xml")) {
					if (Soapstatus.contains("No locality and bad postal code")) {
						assertTrue(true);
					}
				} else {
					assertTrue(false);
				}
			}
		}
	}

	public String SoapPost(String fileName, String WSDLurl, String traansactionType) throws Exception {
		String status = "";
		String xmlrep = "";
		File directory = new File(".//");
		try {
			String filePath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\upload" + "\\"
					+ "ConnectorTransactions" + "\\" + fileName;
			Client client = Client.create();
			// Response Time Out set to 60 sec
			client.setReadTimeout(180000);
			client.setConnectTimeout(180000);
			WebResource webResource = client.resource(WSDLurl);
			ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).type(MediaType.TEXT_XML)
					.entity(new File(filePath)).post(ClientResponse.class);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				status = "Fail";
				xmlrep = response.getEntity(String.class);
				System.out.println(response.getStatus());
				throw new RuntimeException("HTTP error code : " + response.getStatus());

			}
			String output = response.getEntity(String.class);

			if (traansactionType.equalsIgnoreCase("AddressCleansing")
					|| traansactionType.equalsIgnoreCase("DeleteRequest")
					|| traansactionType.equalsIgnoreCase("RollbackRequest")
					|| traansactionType.equalsIgnoreCase("ARBillingSync")
					|| traansactionType.equalsIgnoreCase("ReversalRequest")
					|| traansactionType.equalsIgnoreCase("TransactionExistsRequest")) {

				if (output.contains("ResponseTimeMS")) {
					String time[] = output.split("ResponseTimeMS>");
					String Time1[] = time[1].split("</");
					ResponseTime = Time1[0];
					System.out.println(ResponseTime);
					status = "Posted";
				}
			} else if (output.contains("TotalTax")) {
				String time[] = output.split("TotalTax>");
				String Time1[] = time[1].split("</");
				ResponseTime = Time1[0];
				status = "Posted";
			} else {
				status = "Fail";
			}

		} catch (Exception e) {
			return status + e.getMessage() + "-- " + xmlrep;
		}
		return status;
	}

	public void InterfacesTrustedID() throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		MyInterfacePage MI = PageFactory.initElements(driver, MyInterfacePage.class);
		// Getting username
		waitforElement(60, PUL.SETTINGSLINK, driver);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		Thread.sleep(6000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, MI.SETTINGSCLNTNAME, driver);
		SettingsClntName = getText(MI.SETTINGSCLNTNAME);

		if (VerifyObject(driver, PUL.MyConnectors)) {

			JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
		} else {
			JavaScriptMousehover(driver, PUL.SETTINGSLINK);
			Thread.sleep(2000);
			JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.RETURNTOSETTINGS, driver);
		waitforText(180, MI.TRUSTEDID, driver);
		TrsID = getText(MI.TRUSTEDID);
	}
	
	public void AdminPortalTrustedID(String clientname) throws Exception {
		TaxpayerPartitionsused TPU = PageFactory.initElements(driver, TaxpayerPartitionsused.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		waitforElement(120, TPU.SMBCUSTOMERSTAB, driver);
		click(TPU.SMBCUSTOMERSTAB);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, TPU.SMBCUSTOMERNAME, driver);
		type(TPU.SMBCUSTOMERNAME, clientname);
		Thread.sleep(4000);
		waitforElement(180, "//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname + "')]",
				driver);
		driver.findElement(
				By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + clientname + "')]"))
				.click();
		Thread.sleep(5000);
		waitforText(180, TPU.TRUSTEDID, driver);
		String TrsID123 = getText(TPU.TRUSTEDID);
		TrsID=TrsID123.split(": ")[1];
		System.out.println(TrsID);

	}

	public void CalcParityTransactions(String XMLFileName, String postfilingdate, String Company_code,
			String Soap_WebServiceURL, String update_xml, String update_transactionid, String Update_AddressClansing,
			String No_Myusage_Verify, String Loop, String transctionType, String clientname) throws Exception {
		String Soapstatus = "";
		String URL = null, Postdate = null, LoopCount = null, Taxpayercode = null;
		AdminPortalTrustedID(clientname);

		Postdate = postfilingdate;
		Taxpayercode = Company_code;
		URL = Soap_WebServiceURL.replace("env", Environment);

		if (update_xml.equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			us.xUpdateXML(XMLFileName, TrsID, Postdate, Taxpayercode);
		}

		if (update_transactionid.equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			transactionid = RND();
			System.out.println(transactionid);
			us.xUpdateXML(XMLFileName, TrsID, Postdate, Taxpayercode, transactionid);
		}

		if (Update_AddressClansing.equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			us.AddressCleansingxUpdateXML(XMLFileName, TrsID, Postdate);
		}
		if (!(No_Myusage_Verify).equalsIgnoreCase("Yes")) {
			URL = Soap_WebServiceURL.replace("env", Environment.toLowerCase());
			System.out.println(URL);
			if (Loop != "") {
				LoopCount = Loop;
				int Count = Integer.parseInt(LoopCount);
				for (int j = 0; j < Count; j++) {
					Soapstatus = SoapPost(XMLFileName, URL, transctionType);
					System.out.println(Soapstatus);
					if (Soapstatus.equalsIgnoreCase("Posted")) {
						assertTrue(true);
					} else {
						assertTrue(false);
					}
				}
			} else {
				Soapstatus = SoapPost(XMLFileName, URL, transctionType);
				System.out.println("Soap Response :" + Soapstatus);
				if (Soapstatus.equalsIgnoreCase("Posted")) {
					assertTrue(true);
				} else if (XMLFileName.equalsIgnoreCase("Quote_Myusage_Invalid.xml")
						|| XMLFileName.equalsIgnoreCase("Purchaseorder_Myusage_Invalid.xml")
						|| XMLFileName.equalsIgnoreCase("Quote_Myusage_Error.xml")
						|| XMLFileName.equalsIgnoreCase("Purchaseorder_Myusage_Error.xml")) {
					if (Soapstatus.contains("No locality and bad postal code")) {
						assertTrue(true);
					}
				} else {
					assertTrue(false);
				}
			}
		}
	}

}
