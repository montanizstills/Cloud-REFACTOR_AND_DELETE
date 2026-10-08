package common.libraries.admin;

import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.database.DataBase;
import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.VertexSMBCustomers;

public class ClientDelete extends CommonFunctions {
	private WebDriver driver;

	public ClientDelete(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void DeleteCustomer(String Clientname) throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		DataBase DataBase = PageFactory.initElements(driver, DataBase.class);
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
			waitforElement(90, VSC.EXPORT, driver);
			Thread.sleep(1000);
			driver.navigate().refresh();
			DataBase.UpdateCustomerstatus(Clientname);
			Thread.sleep(20000);
			driver.navigate().refresh();
			Thread.sleep(5000);
			waitforElement(180, VSC.PermanentDelete, driver);
			JavaScriptclick(driver, VSC.PermanentDelete);
			waitforElement(180, VSC.PermanentDeleteYes, driver);
			click(VSC.PermanentDeleteYes);
			Thread.sleep(1000);
			driver.navigate().refresh();
			waitforText(180, VSC.ManageCloudCustomerheader, driver);
		}
	}

	public void CreaterecordinOseries(String Clientname) throws Exception {
		String url = null, dbName = null, userName = null, password = null, S_Query = null;
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
			Thread.sleep(50000);
			// create object of Driver
			// Class.forName(driver).newInstance();
			// connection will be established
			conn = DriverManager.getConnection(url + dbName, userName, password);
			// *******************Statement******************
			Statement stmt = conn.createStatement();
			S_Query = "Select ClientId from VC.Client where ClientName='" + Clientname + "'";
			ResultSet rs = stmt.executeQuery(S_Query);
			while (rs.next()) {
				ClientID = rs.getString("ClientId");
				System.out.println(ClientID);
			}
			S_Query = "Insert into pr.OSeriesPodClient (OSeriesPodId, ClientId) values (42, " + ClientID + ")";
			stmt.execute(S_Query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			// Runtime.getRuntime().exec("wscript.exe
			// "+directory.getCanonicalPath()+"\\VPNDisconnect.vbs" );
			Thread.sleep(10000);
		}

	}

	public void GetOseriesUsername(String Clientname) throws Exception {
		String url = null, dbName = null, userName = null, password = null, S_Query = null;
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
			Thread.sleep(50000);
			conn = DriverManager.getConnection(url + dbName, userName, password);
			// *******************Statement******************
			Statement stmt = conn.createStatement();
			S_Query = "SELECT p.UserName AS 'Taxpayer O Series User Name' FROM VC.VtmsUser u INNER JOIN VC.Client c ON c.ClientId = u.ClientId LEFT JOIN PR.IntegrationClientRegistry i ON i.IntegrationClientId = u.ClientId LEFT JOIN CW.ConfigurationContainer cc ON i.IntegrationClientRegistryId = cc.IntegrationClientRegistryId LEFT JOIN CW.Taxpayer t ON t.ConfigurationContainerId = cc.ConfigurationContainerId LEFT JOIN PR.OSeriesPodPartition p ON p.ConfigurationContainerId = cc.ConfigurationContainerId WHERE c.ClientName = '"
					+ Clientname + "'";
			ResultSet rs1 = stmt.executeQuery(S_Query);
			while (rs1.next()) {
				OseriesUsername = rs1.getString("Taxpayer O Series User Name");
				System.out.println(OseriesUsername);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
			// Runtime.getRuntime().exec("wscript.exe
			// "+directory.getCanonicalPath()+"\\VPNDisconnect.vbs" );
			Thread.sleep(10000);
		}
	}

	// To delete clients based on VAR and creation dates
	public void automationclientdelete(String varName, String startDate, String endDate) throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		DataBase DB = PageFactory.initElements(driver, DataBase.class);		
		waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
		click(VSC.MANAGESMBCUSTLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(360, VSC.SMBCUSTNAMESB, driver);
		DB.archieveclientfromDB(startDate,endDate,varName);
		Thread.sleep(5000);
		type(VSC.Varname, varName);
		selectByValue(VSC.clientStatus, "Archived");
		if (VerifyObject(driver, "//table/tbody/tr[2]/td/div/a/../../../td[contains(.,'Archived')]")) {
			waitforText(180,"//tr[2]/td[contains(.,'Archived')]", driver);		
			Thread.sleep(2000);		
			List <WebElement > a=driver.findElements(By.xpath("//table/tbody/tr"));
			for(int i=0;i<=a.size();i++) {
			WebElement clientclick = driver.findElement(
					By.xpath("//table/tbody/tr[2]/td/div/a/../../../td/div/a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", clientclick);
			Thread.sleep(2000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.PermanentDelete, driver);
			JavaScriptclick(driver, VSC.PermanentDelete);
			waitforElement(180, VSC.PermanentDeleteYes, driver);
			click(VSC.PermanentDeleteYes);
			Thread.sleep(1000);
			click(VSC.CustomerListinglink);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(360, VSC.SMBCUSTNAMESB, driver);
			}
		}
		
	}

	// To delete clients using VAR and list of clientnames 
	public void automationclientdelete(String varName, String clientnames) throws Exception {
			VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			DataBase DB = PageFactory.initElements(driver, DataBase.class);		
			waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(360, VSC.SMBCUSTNAMESB, driver);
			DB.archieveclientfromDB(clientnames);
			Thread.sleep(5000);
			//type(VSC.SMBCUSTNAMESB,"ECW");
			type(VSC.Varname, varName);
			Thread.sleep(5000);
			selectByValue(VSC.clientStatus, "Archived");
			if (VerifyObject(driver, "//table/tbody/tr[2]/td/div/a/../../../td[contains(.,'Archived')]")) {
				waitforText(180,"//tr[2]/td[contains(.,'Archived')]", driver);		
				Thread.sleep(2000);		
				List <WebElement > a=driver.findElements(By.xpath("//table/tbody/tr"));
				for(int i=0;i<=a.size();i++) {
				WebElement clientclick = driver.findElement(
						By.xpath("//table/tbody/tr[2]/td/div/a/../../../td/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", clientclick);
				Thread.sleep(2000);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, VSC.PermanentDelete, driver);
				JavaScriptclick(driver, VSC.PermanentDelete);
				waitforElement(180, VSC.PermanentDeleteYes, driver);
				click(VSC.PermanentDeleteYes);
				Thread.sleep(1000);
				click(VSC.CustomerListinglink);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(360, VSC.SMBCUSTNAMESB, driver);
				}
			}
			
		}

}
