package common.libraries.database;

import java.awt.Robot;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import common.libraries.admin.AdminFunctions;
import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.DashboardPage;
import common.pages.LoginPage;
import common.pages.MyInterfacePage;
import common.pages.ProfessionalUserLinks;
import common.pages.Treasurybillingscreens;
import common.utilities.UtilityScript;

public class DataBase extends CommonFunctions {
	private WebDriver driver;
	public static String Client_Internal_Trusted_ID;
	public static String Client_Pod_ID;

	public DataBase(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	AdminFunctions Adm = PageFactory.initElements(driver, AdminFunctions.class);

	public String SoapPost(String fileName, String WSDLurl) throws Exception {
		String status = "";
		String xmlrep = "";
		try {
			File directory = new File(".//");
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
				status = "Fail;";
				xmlrep = response.getEntity(String.class);
				throw new RuntimeException("HTTP error code : " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			if (output.contains("ResponseTimeMS")) {
				String time[] = output.split("ResponseTimeMS>");
				String Time1[] = time[1].split("</");
				ResponseTime = Time1[0];
			}
			/**************************
			 * commented as there is no verification( just using print statements)
			 **********************************/
			/*
			 * if(fileName.equalsIgnoreCase("Quote_Transaction_TaxAssistRule.xml")){
			 * if(output.contains("TestRule_Taxable_State")){ System.out.println("Present");
			 * } else{System.out.println(" not Present");}}
			 */
			if (status.isEmpty()) {// output= response.getEntity(String.class);
				status = "Posted";
			} else {
				status = "Fail, transaction not posted for" + WSDLurl;
			}
		} catch (Exception e) {
			/*
			 * e.printStackTrace(); System.out.println(e.getMessage());
			 */
			return status + e.getMessage() + "-- " + xmlrep;
		}
		return status;
	}

	public void ConnectorTransactions(String xmlfname, String Postdate, String Taxpayercode, String URL, String TrSID)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		}
		String Soapstatus = "";
		InterfacesTrustedID();
		UtilityScript us = new UtilityScript();
		us.xUpdateXML1(xmlfname, TrsID, Postdate, Taxpayercode);

		System.out.println(URL);
		Soapstatus = SoapPost(xmlfname, URL);
		System.out.println(Soapstatus);
		Thread.sleep(5000);
	}

	public void ConnectorTransactions(String xmlfname, String Postdate, String Taxpayercode, String URL)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		}
		String Soapstatus = "";
		InterfacesTrustedID();
		UtilityScript us = new UtilityScript();
		us.xUpdateXML1(xmlfname, TrsID, Postdate, Taxpayercode);

		System.out.println(URL);
		Soapstatus = SoapPost(xmlfname, URL);
		System.out.println(Soapstatus);
		Thread.sleep(5000);
	}

	public void addrCleansingThroughConnectors(String xmlfname, String URL) throws Exception {
		String Soapstatus = "";
		InterfacesTrustedID();
		System.out.println(URL);
		Soapstatus = SoapPost(xmlfname, URL);
		if (Soapstatus.equalsIgnoreCase("Posted") || xmlfname.contains("Error") || xmlfname.contains("Invalid")) {
		}
		Thread.sleep(5000);
	}

	public String gettrustedandPodId() throws Exception {

		String url = null, dbName = null, driver = null, userName = null, password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		}
		try {
			new File(".//");
			// Runtime.getRuntime().exec("wscript.exe
			// "+directory.getCanonicalPath()+"\\VPNConnect.vbs" );
			Thread.sleep(50000);
			// create object of Driver
			Class.forName(driver).newInstance();
			// connection will be established
			conn = DriverManager.getConnection(url + dbName, userName, password);
			// *******************Statement******************
			Statement stmt = conn.createStatement();
			S_Query = "SELECT cp.TrustedId AS 'Client / Internal Trusted Id' ,p.OSeriesPodId AS 'Taxpayer Pod Id',cp.OSeriesPodId AS 'Client Pod Id' FROM VC.VtmsUser INNER JOIN VC.Client c ON c.ClientId = u.ClientId LEFT JOIN PR.IntegrationClientRegistry i ON i.IntegrationClientId = u.ClientId LEFT JOIN cw.ConfigurationContainer cc ON cc.IntegrationClientRegistryId = i.IntegrationClientRegistryId LEFT JOIN CW.Taxpayer t ON t.ConfigurationContainerId = cc.ConfigurationContainerId LEFT JOIN PR.OSeriesPodPartition p ON p.ConfigurationContainerId  = cc.ConfigurationContainerId  LEFT JOIN PR.OSeriesPodPartition cp ON cp.OSeriesPodPartitionId = i.OSeriesPodPartitionId LEFT JOIN PR.ReturnsDatabase rd ON rd.OSeriesPodPartitionId = p.OSeriesPodPartitionId where u.username like '"
					+ VertexProUserName + "'";
			ResultSet rs = stmt.executeQuery(S_Query);
			Client_Internal_Trusted_ID = rs.getString("Client / Internal Trusted Id");
			Client_Pod_ID = rs.getString("Client Pod Id");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			conn.close();
			// Runtime.getRuntime().exec("wscript.exe
			// "+directory.getCanonicalPath()+"\\VPNDisconnect.vbs" );
			Thread.sleep(10000);
		}
		return Client_Internal_Trusted_ID;
	}

	public String InterfacesTrustedID() throws Exception {
		String status = "";
		try {
			new Robot();
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
			MyInterfacePage MI = PageFactory.initElements(driver, MyInterfacePage.class);
			// Getting username
			JavaScriptclick(driver, PUL.SETTINGSLINK);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforText(180, MI.SETTINGSCLNTNAME, driver);
			SettingsClntName = getText(MI.SETTINGSCLNTNAME);
			// Getting Trusted id
			JavaScriptclick(driver, PUL.SETTINGSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.VIEWALLCONNECTORSBTN1, driver);
			JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN1);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.RETURNTOSETTINGS, driver);
			waitforText(180, MI.TRUSTEDID, driver);
			TrsID = MI.TRUSTEDID.getText();
			if (status.isEmpty()) {
				status = "Pass";
			}

		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public String DB_FetchCustomerCode(String CustomerName) throws Exception {
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		}
		try {
			File directory = new File(".//");
			Runtime.getRuntime().exec("wscript.exe " + directory.getCanonicalPath() + "\\VPNConnect.vbs");
			Thread.sleep(20000);
			// create object of Driver
			Class.forName(driver).newInstance();
			// connection will be established
			conn = DriverManager.getConnection(url + dbName, userName, password);

			// *******************Statement******************
			Statement stmt = conn.createStatement();
			S_Query = "select LEN(CustomerCode) AS Length,CustomerCode from CW.Customer  where   CustomerName = '"
					+ CustomerName + "'";
			ResultSet rs = stmt.executeQuery(S_Query);
			while (rs.next()) {
				CustomerCodelength = rs.getString("Length");
				System.out.println("Length of the Customer code in the DB is:" + CustomerCodelength);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			File directory = new File(".//");
			Runtime.getRuntime().exec("wscript.exe " + directory.getCanonicalPath() + "\\VPNDisconnect.vbs");
			Thread.sleep(10000);
		}
		return CustomerCodelength;
	}

	public String UpdateCustomerstatus(String ClientName) throws Exception {
		Connection conn = null;
		String status = "";
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			ClientName = VertexClientName;
			S_Query = "UPDATE VC.Client SET ClientStatusID=7 WHERE ClientName ='" + ClientName + "'";
			Thread.sleep(2000);
			S_Query = "UPDATE VC.Client SET ClientStatusID=8 WHERE ClientName ='" + ClientName + "'";
			stmt.execute(S_Query);
			if (status.isEmpty())
				status = "Pass";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return status;
	}

	public String VerifyCustomerstatus(String Username) throws Exception {
		Connection conn = null;
		String status = "";
		String url = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null, password = null,
				S_Query = null, dbName1 = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName1 = "databaseName=VTMSAuth;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName1 = "databaseName=VTMSAuth;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName1, userName, password);
			// *******************Statement******************
			Statement stmt = conn.createStatement();
			Username = VertexProUserName;
			S_Query = "SELECT subjectId FROM Auth.Subject WHERE UserName ='" + Username + "'";
			Boolean trueval = stmt.execute(S_Query);
			if (trueval) {
				System.out.println(trueval);
				status = "Pass";
			} else {
				status = "Fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return status;
	}

	public String CloudElementsRecord(String Username) throws Exception {
		Connection conn = null;
		String status = "";
		String url = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null, password = null,
				dbName1 = null, UserId = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CLOUDELEMENTS":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName1 = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName1 = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName1, userName, password);
			// *******************Statement******************
			Statement stmt = conn.createStatement();
			if (VertexUserName != null) {
				Username = VertexUserName;
			} else {
				Username = UserN;
			}
			ResultSet rs = stmt.executeQuery("SELECT * FROM VC.VtmsUser WHERE UserName ='" + Username + "'");
			while (rs.next()) {
				UserId = rs.getString("VtmsUserId");
				System.out.println(UserId);
			}
			// rs.close();
			ResultSet rs1 = stmt
					.executeQuery("SELECT * FROM CE.CloudElementsCompanyExecution WHERE VtmsUserId ='" + UserId + "'");
			List<String> ids = new ArrayList<String>();
			while (rs1.next()) {
				ids.add(rs1.getString(("CloudElementsFormulaExecutionId")));
				System.out.println(ids);
			}
			String value1 = ids.get(0);
			String value2 = ids.get(1);
			if (!value1.equalsIgnoreCase(value2)) {
				status = "";
			} else {
				status = "Fail, Cloud Elements Formula Execution Ids should be unique;";
			}
			rs1.close();
			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return status;

	}

	public String VerifyDIStatus(String Username) throws Exception {
		Connection conn = null;
		String status = "";
		String url = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null, password = null,
				dbName1 = null, diValue = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CLOUDELEMENTS":
		case "DATAINTEGRITY":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName1 = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName1 = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName1, userName, password);
			// *******************Statement******************
			Statement stmt = conn.createStatement();
			if (VertexUserName != null) {
				Username = VertexUserName;
			} else {
				Username = UserN;
			}
			ResultSet rs1 = stmt
					.executeQuery("SELECT * FROM VC.ClientRegistrationMetadata WHERE ClientId ='" + CNTID + "'");
			while (rs1.next()) {
				diValue = rs1.getString("RegistrationValue");
				System.out.println(diValue);
			}
			if (diValue.equalsIgnoreCase(DIRegion)) {
				status = "";
			} else {
				status = "Fail,Data Integrity Region value is not same in DB;";
			}
			rs1.close();
			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return status;
	}

	public void ConnectorTransactions(String XMLFileName, String postfilingdate, String Company_code,
			String Soap_WebServiceURL, String update_xml, String update_transactionid, String Update_AddressClansing,
			String No_Myusage_Verify, String Loop) throws Exception {

		String Soapstatus = "";
		String XMLFile = null, URL = null, xmlfname = null, Postdate = null, LoopCount = null, Taxpayercode = null;
		InterfacesTrustedID();

		xmlfname = XMLFileName;
		Postdate = postfilingdate;
		Taxpayercode = Company_code;
		URL = Soap_WebServiceURL.replace("env", Environment);

		if (!(update_xml).equalsIgnoreCase("No")) {
			UtilityScript us = new UtilityScript();
			us.xUpdateXML(xmlfname, TrsID, Postdate, Taxpayercode);
		}

		if (update_transactionid.equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			us.xUpdateXML(xmlfname, TrsID, Postdate, Taxpayercode, xRND(0, 0, 0));
		}

		if ((Update_AddressClansing).equalsIgnoreCase("Yes")) {
			UtilityScript us = new UtilityScript();
			us.AddressCleansingxUpdateXML(xmlfname, TrsID, Postdate);
		}
		if (!(No_Myusage_Verify).equalsIgnoreCase("Yes")) {
			XMLFile = XMLFileName;
			URL = Soap_WebServiceURL.replace("env", Environment.toLowerCase());
			System.out.println(URL);
			if (Loop != "") {
				LoopCount = Loop;
				int Count = Integer.parseInt(LoopCount);
				for (int j = 0; j < Count; j++) {
					Soapstatus = SoapPost(XMLFile, URL);
					System.out.println(Soapstatus);
					if (Soapstatus.equalsIgnoreCase("Posted")) {
						assertTrue(true);
					} else {
						assertTrue(false);
					}
				}
			} else {
				Soapstatus = SoapPost(XMLFile, URL);
				if (Soapstatus.equalsIgnoreCase("Posted")) {
					assertTrue(true);
				} else {
					assertTrue(false);
				}
			}
		}
	}

	public void Myusageverify(String Myusage_Verify, String Myusage_Update) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		}
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		// Verifying Myusage section
		if (Myusage_Verify.equalsIgnoreCase("Yes")) {
			click(PUL.SETTINGSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforText(60, "//h2[contains(.,'My Usage')]", driver);
			if (Myusage_Verify != "") {
				if (!VerifyObject(driver,
						"//table[@class='table table-bordered table-striped']/tbody/tr/td[contains(.,'Real-Time Rate Service Transaction Count')]/../td[2][contains(.,'"
								+ Myusage_Update + "')]")) {
					boolean report = false;
					int count = 0;
					do {
						driver.navigate().refresh();
						Thread.sleep(30000);
						count = count + 1;
						if (count == 40) {
							assertTrue(false);
							break;
						}
						if (VerifyObject(driver,
								"//table[@class='table table-bordered table-striped']/tbody/tr/td[contains(.,'Real-Time Rate Service Transaction Count')]/../td[2][contains(.,'"
										+ Myusage_Update + "')]")) {
							report = true;
						}
					} while (report == false);
				}
				if (VerifyObject(driver,
						"//table[@class='table table-bordered table-striped']/tbody/tr/td[contains(.,'Real-Time Rate Service Transaction Count')]/../td[2][contains(.,'"
								+ Myusage_Update + "')]")) {
					assertTrue(true);
				}
			} else {
				assertTrue(VerifyObject(driver, PUL.CURRMONTH) && (VerifyObject(driver, PUL.PREVMONTH))
						&& (VerifyObject(driver, PUL.PREVMONTH1)));
			}
		}

	}

	public void BillingEditFilterSettings(String Users, String ClientName, String VarType, String AccountType)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Treasurybillingscreens TBS = PageFactory.initElements(driver, Treasurybillingscreens.class);
		PageFactory.initElements(driver, DashboardPage.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		xDownloadFiles("Billing");
		String clientname = null;
		int count = 0;
		waitforElement(60, LP.USERNAME, driver);
		Thread.sleep(5000);
		/*
		 * if (Users != "") { if (Users.equalsIgnoreCase("REGTAXCENTRALLOGIN")) {
		 * clientname = VertexClientName; } else if
		 * (Users.equalsIgnoreCase("REGREFTAXCENTRALLOGIN")) { clientname =
		 * VertexClientName; } else if (Users.equalsIgnoreCase("REGTESTINSTANCELOGIN"))
		 * { clientname = TestInstanceclientName; } } else { clientname =
		 * BPOCPAClientName; }
		 */
		if (ClientName != "") {
			clientname = ClientName;
		}
		waitforElement(60, TBS.MANAGEBILLINGHOVER, driver);
		JavaScriptMousehover(driver, TBS.MANAGEBILLINGHOVER);
		JavaScriptclick(driver, TBS.RUNHISTORY);
		Thread.sleep(5000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String var = VarType;
		if (AccountType.equalsIgnoreCase("Active")) {
			waitforElement(180, TBS.EDITUSERSETTINGS, driver);
			click(TBS.EDITUSERSETTINGS);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			assertTrue(driver
					.findElement(By.xpath(
							"//div[contains(.,'Active Accounts')]/div/table/tbody/tr/td[contains(.,'" + var + "')]"))
					.isDisplayed());
			driver.findElement(
					By.xpath("//div[contains(.,'Active Accounts')]/div/table/tbody/tr/td[contains(.,'" + var + "')]"))
					.click();
			click(TBS.SAVECHANGES);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, TBS.EDITUSERSETTINGS, driver);
			waitforElement(180, TBS.TESTACCOUNTS, driver);
			click(TBS.TESTACCOUNTS);
			Thread.sleep(2000);
			if (!VerifyObject(driver,
					"//table[@id='billingHistoryTable']/thead/tr/th[contains(.,'Cloud Customer Name')]/../../../tbody/tr/td[contains(.,'"
							+ clientname + "')]")) {
				boolean report = false;
				count = 0;
				do {
					driver.navigate().refresh();
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, TBS.TESTACCOUNTS, driver);
					click(TBS.TESTACCOUNTS);
					Thread.sleep(2000);
					click(TBS.RHCUSTOMERNAME);
					clear(TBS.RHCUSTOMERNAME);
					type(TBS.RHCUSTOMERNAME, clientname);
					Thread.sleep(5000);
					count = count + 1;
					if (count == 25) {
						assertTrue(driver.findElement(By.xpath(
								"//table[@id='billingHistoryTable']/thead/tr/th[contains(.,'Cloud Customer Name')]/../../../tbody/tr/td[contains(.,'"
										+ clientname + "')]"))
								.isDisplayed());
						break;
					}
					if (VerifyObject(driver,
							"//table[@id='billingHistoryTable']/thead/tr/th[contains(.,'Cloud Customer Name')]/../../../tbody/tr/td[contains(.,'"
									+ clientname + "')]")) {
						report = true;
					}
				} while (report == false);
			}

			click(TBS.RHCUSTOMERNAME);
			clear(TBS.RHCUSTOMERNAME);
			type(TBS.RHCUSTOMERNAME, clientname);
			waitforElement(180,
					"//table[@id='billingHistoryTable']/thead/tr/th[contains(.,'Cloud Customer Name')]/../../../tbody/tr/td[contains(.,'"
							+ clientname + "')]",
					driver);
			// Verifying Client not Displaying in Billing Active Account Section
			click(TBS.ACTIVEACCOUNTS);
			Thread.sleep(2000);
			click(TBS.RHCUSTOMERNAME);
			clear(TBS.RHCUSTOMERNAME);
			type(TBS.RHCUSTOMERNAME, clientname);
			Thread.sleep(2000);
			if (VerifyObject(driver,
					"//table[@id='billingRunsTable']/thead/tr/th[contains(.,'Cloud Customer Name')]/../../../tbody/tr/td[contains(.,'"
							+ clientname + "')]")) {
				assertTrue(driver.findElement(By.xpath(
						"//table[@id='billingRunsTable']/thead/tr/th[contains(.,'Cloud Customer Name')]/../../../tbody/tr/td[contains(.,'\"+clientname+\"')]"))
						.isDisplayed());
			}
		} else {
			click(TBS.EDITUSERSETTINGS);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, "//div[contains(.,'Test Accounts')]/div/table/tbody/tr/td[contains(.,'" + var + "')]",
					driver);
			assertTrue(driver
					.findElement(By.xpath(
							"//div[contains(.,'Test Accounts')]/div/table/tbody/tr/td[contains(.,'" + var + "')]"))
					.isDisplayed());
			driver.findElement(
					By.xpath("//div[contains(.,'Test Accounts')]/div/table/tbody/tr/td[contains(.,'" + var + "')]"))
					.click();
			Thread.sleep(2000);
			click(TBS.SAVECHANGES);
			Thread.sleep(5000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, TBS.EDITUSERSETTINGS, driver);
		}
	}

	public void executeQuery(String Query) throws Exception {

		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null;
		switch (Environment) {
		case "DEV":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		}
		try {
			Thread.sleep(20000);
			// create object of Driver
			Class.forName(driver).newInstance();
			// connection will be established
			conn = DriverManager.getConnection(url + dbName, userName, password);

			// *******************Statement******************
			Statement stmt = conn.createStatement();
			// String Query = "Declare @fileMetadataId int,@QueueServiceId Int select top 1
			// @fileMetadataId = fmt.FileMetaId from vc.[File] f join vc.filemetadata fmt on
			// f.fileid=fmt.fileid where f.filetypeid=41 and fmt.[key]='04/2020' order by
			// f.fileid desc update vc.filemetadata set value=9999 where
			// FileMetaId=@fileMetadataId select top 1 @QueueServiceId=queueserviceId from
			// qs.queueservice where queuetypeid=504 order by 1 desc Update QS.QueueService
			// SET QueueStatusID = 1, ExceptionLogId=NULL WHERE QueueServiceID =
			// @QueueServiceId";
			ResultSet rs = stmt.executeQuery(Query);
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			Thread.sleep(10000);
		}
	}

	public String archieveclientfromDB(String startDate, String endDate, String varName) throws Exception {
		Connection conn = null;
		String status = "";
		String url = null;
		String dbName = null;
		String sqldriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String userName = null;
		String password = null;
		String sQuery = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(sqldriver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			ClientName = VertexClientName;
			sQuery = "UPDATE C SET C.ClientStatusID = 7 FROM VC.Client C INNER JOIN VC.Client PC ON PC.ClientId = C.ParentClientId WHERE PC.ClientName = ' "
					+ varName + "' AND C.ClientStatusID = 2 AND C.CreatedUTC <= '" + startDate
					+ "' AND C.CreatedUTC <= '" + endDate + "'";
			Thread.sleep(2000);
			stmt.execute(sQuery);
			Thread.sleep(5000);
			sQuery = "UPDATE C SET C.ClientStatusID = 8 FROM VC.Client C INNER JOIN VC.Client PC ON PC.ClientId = C.ParentClientId WHERE PC.ClientName = ' "
					+ varName + "' AND C.ClientStatusID = 7 AND C.CreatedUTC <= '" + startDate
					+ "' AND C.CreatedUTC <= '" + endDate + "'";
			Thread.sleep(2000);
			stmt.execute(sQuery);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return status;
	}

	public String archieveclientfromDB(String clientnames) throws Exception {
		Connection conn = null;
		String status = "";
		String url = null;
		String dbName = null;
		String sqldriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String userName = null;
		String password = null;
		String sQuery = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(sqldriver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			sQuery = "UPDATE VC.Client SET ClientStatusID = 7 WHERE clientname in (" + clientnames + ")";
			Thread.sleep(2000);
			stmt.execute(sQuery);
			Thread.sleep(5000);
			sQuery = "UPDATE VC.Client SET ClientStatusID = 8 WHERE clientname in (" + clientnames + ")";
			Thread.sleep(2000);
			stmt.execute(sQuery);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return status;
	}

	public String getPublicTaxpayerID(String Taxpayername, String tID) throws Exception {
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "SELECT PublicId from CW.Taxpayer where TaxpayerName='" + Taxpayername
					+ "'  ORDER BY  TaxpayerID DESC";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			if (rs.next()) {
				tID = rs.getString("PUBLICID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return tID;
	}

	public String getTaxpayerID(String Taxpayername, String tID) throws Exception {
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "SELECT TaxpayerID from CW.Taxpayer where TaxpayerName='" + Taxpayername
					+ "'  ORDER BY  TaxpayerID DESC";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			if (rs.next()) {
				tID = rs.getString("TaxpayerID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return tID;
	}

	public String verifyProductcodestatus(String productcode, String ostatus) throws Exception {
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "SELECT OSeriesDeleteStatusId FROM CW.DeletedProduct where ProductCode='" + productcode + "'";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			if (rs.next()) {
				ostatus = rs.getString("OSeriesDeleteStatusId");
				System.out.println(ostatus);
				assertTrue(ostatus.matches("3"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return ostatus;
	}

	public String getPublicLocationId(String taxpayerId, String locationName) throws Exception {
		String PublicLocationId = "";
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "Select * From CW.Location where TaxpayerID='" + taxpayerId + "' AND LocationName ='"
					+ locationName + "'";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			if (rs.next()) {
				PublicLocationId = rs.getString("PublicId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return PublicLocationId;
	}

	public void verifynewcalcfeature() throws Exception {
		@SuppressWarnings("unused")
		String FeatureName = "";
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "Select FeatureName from VC.Feature";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			List<String> ids = new ArrayList<String>();
			while (rs.next()) {
				ids.add(rs.getString(("FeatureName")));
			}
			for (int i = 0; i < ids.size(); i++) {
				System.out.println(ids.get(i));
				if (ids.get(i).equalsIgnoreCase("Cloud O Series – Tax Calc.")) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void verifynewcalcuserinDB(String Clientname) throws Exception {
		String ClientID = "";
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "SELECT TOP 1 ClientId  FROM VC.Client WHERE ClientName = '" + Clientname + "'";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			if (rs.next()) {
				ClientID = rs.getString("ClientId");
			}
			S_Query = "Select * from VC.ClientSubscribedFeature WHERE FeatureId='120' and ClientId='" + ClientID + "'";
			Thread.sleep(2000);
			ResultSet rs1 = stmt.executeQuery(S_Query);
			if (rs1.next()) {
				ClientID = rs1.getString("ClientId");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void CreateuserrecordinOseries(String Clientname, String PodId) throws Exception {
		@SuppressWarnings("unused")
		String url = null, dbName = null, driver = null, userName = null, password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		case "STAGE":
			url = "jdbc:sqlserver://sporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com,1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "StageAdmin";
			password = "Vertex2016";
			break;
		}
		try {
			@SuppressWarnings("unused")
			File directory = new File(".//");
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "Select ClientId from VC.Client where ClientName='" + Clientname + "'";
			ResultSet rs = stmt.executeQuery(S_Query);
			while (rs.next()) {
				ClientID = rs.getString("ClientId");
				System.out.println(ClientID);
			}
			S_Query = "Insert into pr.OSeriesPodClient (OSeriesPodId, ClientId) values (" + PodId + ", " + ClientID
					+ ")";
			stmt.execute(S_Query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyComplianceReportinDB() throws Exception {
		String ClientID = "";
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "Select * from [VC].[FileType] where FileTypeId in ('36','51')";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			while (rs.next()) {
				ClientID = rs.getString("Name");
				System.out.println(ClientID);
				// assertTrue(ClientID.equalsIgnoreCase("OseriesComplianceFile"));
				// System.out.println(ClientID);
				// assertTrue(ClientID.equalsIgnoreCase("InternationalReturns"));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void verifyTransactionReportinDB() throws Exception {
		String ClientID = "";
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "Select * from [VC].[FileSubType] where FileSubTypeId in ('14','29','24','23')";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			while (rs.next()) {
				ClientID = rs.getString("Name");
				System.out.println(ClientID);
				// assertTrue(ClientID.equalsIgnoreCase("Summary"));
				// assertTrue(ClientID.equalsIgnoreCase("DocumentSummaryByJurisdiction"));
				// assertTrue(ClientID.equalsIgnoreCase("JurisdictionSummary"));
				// assertTrue(ClientID.equalsIgnoreCase("LargeVolumeTransactionDetail"));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void Getvertexcontent(String UserName) throws Exception {
		String TrustedID = "";
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "SELECT u.UserName, vuc.ClientId, icr.IntegrationClientId, icr.IntegrationClientRegistryId, cc.ConfigurationContainerId, opp.OSeriesPodId, opp.OSeriesPodPartitionId, icr.IsActive, opp.PartitionUUID, TrustedId\r\n"
					+ "FROM vc.VtmsUser u\r\n" + "JOIN vc.VtmsUserClient vuc on u.VtmsUserId = vuc.VtmsUserId\r\n"
					+ "JOIN pr.IntegrationClientRegistry icr ON vuc.ClientId = icr.IntegrationClientId\r\n"
					+ "JOIN cw.ConfigurationContainer cc on icr.IntegrationClientRegistryId = cc.IntegrationClientRegistryId\r\n"
					+ "JOIN PR.OSeriesPodPartition opp on cc.ConfigurationContainerId = opp.ConfigurationContainerId\r\n"
					+ "JOIN PR.OSeriesPod op on opp.OSeriesPodId = op.OSeriesPodId\r\n" + "WHERE u.EmailAddress = '"
					+ UserName + "'";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			if (rs.next()) {
				TrustedID = rs.getString("TrustedId");
				System.out.println(TrustedID);
				PartitionUUID = rs.getString("PartitionUUID");
				System.out.println(PartitionUUID);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void GetpublicfileID(String EmailID) throws Exception {
		String configcontainerID = "";
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "SELECT cc.ConfigurationContainerId FROM vc.VtmsUser u JOIN vc.VtmsUserClient vuc on u.VtmsUserId = vuc.VtmsUserId JOIN pr.IntegrationClientRegistry icr ON vuc.ClientId = icr.IntegrationClientId JOIN cw.ConfigurationContainer cc on icr.IntegrationClientRegistryId = cc.IntegrationClientRegistryId JOIN PR.OSeriesPodPartition opp on cc.ConfigurationContainerId = opp.ConfigurationContainerId WHERE u.EmailAddress = '"
					+ EmailID + "'";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			if (rs.next()) {
				configcontainerID = rs.getString("ConfigurationContainerId");
				System.out.println(configcontainerID);
			}
			S_Query = "SELECT * FROM [VTMSPortal].[VC].[File] where [VC].[File].ConfigurationContainerId='"
					+ configcontainerID + "'";
			Thread.sleep(2000);
			ResultSet rs1 = stmt.executeQuery(S_Query);
			if (rs1.next()) {
				PublicFileId = rs1.getString("PublicFileId");
				System.out.println(PublicFileId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void verifyeventlog() throws Exception {
		String EventData = "";
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "select * from Audit.AuditEventLog where AuditEventTypeId='10100'";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			if (rs.next()) {
				EventData = rs.getString("EventData");
				System.out.println(EventData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void verifydeleteeventlog() throws Exception {
		String EventData = "";
		Connection conn = null;
		String url = null, dbName = null, driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver", userName = null,
				password = null, S_Query = null;
		switch (Environment) {
		case "DEV":
		case "Platform_DEV":
		case "CONFIG":
			url = "jdbc:sqlserver://dporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "DevAdmin";
			password = "Vertex2016";
			break;
		case "QA":
			url = "jdbc:sqlserver://qporsql01.c3tqqpbclssk.us-east-1.rds.amazonaws.com:1433;";
			dbName = "databaseName=VTMSPortal;";
			userName = "QAAdmin";
			password = "Vertex2016";
			break;
		default:
		}
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			S_Query = "select * from Audit.AuditEventLog where AuditEventTypeId='10101'";
			Thread.sleep(2000);
			ResultSet rs = stmt.executeQuery(S_Query);
			if (rs.next()) {
				EventData = rs.getString("EventData");
				System.out.println(EventData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

}
