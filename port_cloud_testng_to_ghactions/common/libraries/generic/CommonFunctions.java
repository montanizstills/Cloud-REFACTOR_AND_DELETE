/*###################################################################################################
Name: CommonFunctions.java 
Description: Common used function accross the project
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.libraries.generic;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.net.ssl.internal.www.protocol.https.BASE64Encoder;

import common.utilities.UtilityScript;
import jxl.Sheet;
import jxl.Workbook;

public class CommonFunctions extends UtilityScript {

	/**
	 * it will wait until element invisible on the web page within specified time
	 * 
	 * @author venkat kandula
	 * @param int    Timeouty
	 * @param String objxpath
	 * @param final  WebDriver aDriver
	 * @throws Exception
	 */
	public static Connection conn = null;
	public WebResource webResource;
	public Client client;
	public ClientResponse response;
	public static String ECWClientID = "";
	public static String ECWClientSecret = "";
	public static String APIkey;
	public static String APIPassword;
	public static String ConnectorAPIKey;
	public static String ConnectorAPIPassword;
	public static String VertexClientName;
	public static String BPOCPAClientName;
	public String Events;
	public String VertexAdminTitles;
	public String currDate1;
	public static String TrsID;
	public static String DPID;
	public static String NPID;
	public static String ResponseTime;
	public static String SettingsClntName;
	public String strTaxpayerCompanyName;
	public String ApproveTitles;
	public static String VertexFirstName;
	public static String VertexLastName;
	public String VertexEmail;
	public static String AdminLoginUserName = "";
	public String VarUserTitles;
	public static String SMBCustomerUserName;
	public static String DIRegion;
	public static String CNTID;
	public static String UpdatedDIRegion;
	public static String TestInstanceclientName;
	public static String MultipleVertexUsers;
	public String TransactionSuccess;
	public String TransactionFile;
	public static String MultipleStates;
	public static String MerchantClientName;
	public int transactioncount = 0;
	public static String VertexProUserName;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	public static String CustomerCodelength;
	public static String UserN;
	public static String ClientName;
	public static String ClientID;
	public static String PartitionUUID;
	public static String PublicFileId;
	public static String ClientSecret;
	public static String OseriesUsername;
	public static String configstatus;
	public static String Description;
	public static String MultipleBPOClients;
	public static String MultipleMerchantClients;
	public static String BPOCPAFirstName;
	public static String BPOCPASClientName;
	public static String MultipleStaffClients;
	public static String CPAFirstName;
	public static String CPALastName;
	public static String CPAAdminName;
	public static String CPAClientName;
	public static String MultipleCPAAdmins;
	public String ProUserTitles;
	public static String loginlockedusername;
	public static String CPAStaffName;
	public static String FranchiseFN;
	public static String FranchiseLN;
	public static String FranchiseUser;
	public static int Initiatedi = 0;
	public static int Livei = 0;
	public static int rowcount2 = 0;
	public static int OverallReturnscount = 0;
	public static int OverallLocationscount = 0;
	public static int Overalljurisdictionscount = 0;
	public String strCatgName;
	public static String JurLink1;
	public static String Companynamesplit = null;
	public static String endDate;
	public String strLocationName;
	public String CustomerCode;
	public String CustomerName;
	public String strCustName;
	public String ProductCode;
	public String ProductName;
	public String strProdName;
	public String LocationCode;
	public String LocationName;
	public static String Currentdate;
	public static String Staterow;
	public static String NewStatecount;
	public static String stateName;
	public String TaxabilityDownloadlink;
	public String PrepaymentReturn;
	public static String JurisdictionType;
	public static String InstanceName;
	public static String ECWOSeriesClientID = "";
	public static String SellerName;
	public static String ECWOSeriesClientSecret = "";
	public static String ECWRoleUserName;
	public static boolean ECWFlag;
	public static String VertexECWUserName;
	public static String ECWUserName;
	public static String TaxpayerID = null;
	public static String QueueserviceID = null;
	public static String PublicTaxpayerID = null;
	public static String TaxpayerCode;
	public static String TaxpayerCompanyName;
	public static String sDate = null;
	public static String Filepath;
	
	public static String RTEFilepath;
	public static String RTEfileName;
	public static String FilterNamepath;
	public static String RTEPath;
	public static String GoliveDate;
	public static int TaxCentralTaxPayersCount;
	public static List<WebElement> TaxCentralTaxPayers;
	public static String ECWCode;
	public static String oraclenumber;
	public static String PortalUserName;
	public static String Activecount;
	public static String Pendingcount;
	public static String Initiatedcount;
	public static String Testcount;
	public static String Livecount;
	public static String LoginName;
	public String PasswordName;
	public static String firstname;
	public static String lastname;
	public static String PendfingFN;
	public static String PendfingLN;
	public String LockedUsername;
	public String VertexTreasEmail;
	public String VertexAppEmail;
	public String VertexFinanceEmail;
	public String VertexStaffEmail;
	public static String VertexProEmail;
	public String VertexVarEmail;
	public String VertexSysEmail;
	public String DashBoardLebel;
	public static String FirstName;
	public static String InvoiceNO;

	public void waitUntilElementInvisibe(int Timeout, String objxpath, final WebDriver aDriver) throws Exception {
		try {
			new FluentWait<WebDriver>(aDriver).withTimeout(Duration.ofSeconds(Timeout))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(objxpath)));
		} catch (Exception e) {
		}
	}

	public void waitUntilElementInvisibe(int Timeout, final WebElement key, final WebDriver aDriver) throws Exception {
		try {
			new FluentWait<WebDriver>(aDriver).withTimeout(Duration.ofSeconds(Timeout))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.invisibilityOf(key));
		} catch (Exception e) {
		}
	}

	/**
	 * it will wait until element visible on the web page within specified time
	 * 
	 * @author venkat kandula
	 * @updted Rajesh
	 * @param int    Timeouty
	 * @param String objxpath
	 * @param final  WebDriver aDriver
	 * @throws Exception
	 */

	public void waitforElement(int Timeout, String objxpath, final WebDriver aDriver) throws Exception {
		try {
			new FluentWait<WebDriver>(aDriver).withTimeout(Duration.ofSeconds(Timeout))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(objxpath)));
		} catch (Exception e) {
		}
	}

	/**
	 * it will wait until element visible on the web page within specified time
	 * 
	 * @author venkat kandula
	 * @updted Rajesh
	 * @param int   Timeouty
	 * @param final WebElement key
	 * @param final WebDriver aDriver
	 * @throws Exception
	 */

	public void waitforElement(int Timeout, final WebElement key, final WebDriver aDriver) throws Exception {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(aDriver).withTimeout(Duration.ofSeconds(Timeout))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.elementToBeClickable(key));
		} catch (Exception e) {
		}
	}

	/**
	 * @author Rajesh
	 * @param Timeout
	 * @param key
	 * @param aDriver
	 * @throws Exception
	 */
	public void waitforText(int Timeout, final WebElement key, final WebDriver aDriver) throws Exception {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(aDriver).withTimeout(Duration.ofSeconds(Timeout))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOf(key));
		} catch (Exception e) {
		}
	}

	/**
	 * @author rajeshc
	 * @param Timeout
	 * @param objxpath
	 * @param aDriver
	 * @throws Exception
	 */
	public void waitforText(int Timeout, String objxpath, final WebDriver aDriver) throws Exception {
		try {
			new FluentWait<WebDriver>(aDriver).withTimeout(Duration.ofSeconds(Timeout))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objxpath)));
		} catch (Exception e) {
		}
	}

	/**
	 * Type value in given webelement field
	 * 
	 * @author venkat kandula
	 * @param webelement key
	 * @param String     value
	 * @throws Exception
	 */

	public void type(WebElement key, String value) throws Exception {
		if (key.isDisplayed()) {
			key.sendKeys(value);
		} else {
			throw new Exception(key + "is not found");
		}
	}
	/*
	 * public void type(By locator, String value) throws Exception { try{
	 * //locator.sendKeys(value); driver.findele }catch(Exception e){ throw new
	 * Exception(locator+ "is not found"); } }
	 */

	/**
	 * it will perform the tab action on given webelement field
	 * 
	 * @author venkat kandula
	 * @param webelement
	 * @throws Exception
	 */

	public void sendkeysTab(WebElement key) throws Exception {
		if (key.isDisplayed()) {
			key.sendKeys(Keys.TAB);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * it will perform the Shift tab action on given webelement field
	 * 
	 * @author venkat kandula
	 * @param webelement
	 * @throws Exception
	 */

	public void sendkeysShiftTab(WebElement key) throws Exception {
		if (key.isDisplayed()) {
			String press = Keys.chord(Keys.SHIFT, Keys.TAB);
			key.sendKeys(press);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * it will perform the Back Space action on given webelement field
	 * 
	 * @author venkat kandula
	 * @param webelement key
	 * @throws Exception
	 */

	public void sendkeybackSpace(WebElement key) throws Exception {
		if (key.isDisplayed()) {
			key.sendKeys(Keys.BACK_SPACE);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * it will perform the Enter action on given webelement field
	 * 
	 * @author venkat kandula
	 * @param webelement key
	 * @throws Exception
	 */

	public void sendkeysEnter(WebElement key) throws Exception {
		if (key.isDisplayed()) {
			key.sendKeys(Keys.ENTER);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * it will perform the Return action on given webelement field
	 * 
	 * @author venkat kandula
	 * @param webelement key
	 * @throws Exception
	 */

	public void sendkeysReturn(WebElement key) throws Exception {
		if (key.isDisplayed()) {
			key.sendKeys(Keys.RETURN);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * Type value in given webelement field using JavaScript Executor
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 * @param webelement
	 * @param value
	 * @throws Exception
	 */

	public void JavaScripttype(final WebDriver aDriver, WebElement key, String value) throws Exception {
		if (key.isDisplayed()) {
			JavascriptExecutor jse = ((JavascriptExecutor) aDriver);
			jse.executeScript("arguments[0].value='" + value + "';", key);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * it will perform the Back Space action on given webelement field using
	 * Javascript executor
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 * @param webelement
	 * @param value
	 * @throws Exception
	 */

	public void JavaScripttypespace(final WebDriver aDriver, WebElement key, String value) throws Exception {
		if (key.isDisplayed()) {
			JavascriptExecutor jse = ((JavascriptExecutor) aDriver);
			jse.executeScript("arguments[0].value='" + value + " " + "';", key);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * Click on the given webelement field
	 * 
	 * @author venkat kandula
	 * @param webelement key
	 * @throws Exception
	 * @throws InterruptedException
	 */

	public void click(WebElement key) throws Exception {

		if (key.isDisplayed()) {
			key.click();
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * Click on the given webelement field using JavaScript Executor
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 * @param webelement
	 * @throws Exception
	 * @throws InterruptedException
	 */

	public void JavaScriptclick(final WebDriver aDriver, WebElement key) throws Exception {

		if (key.isDisplayed()) {
			JavascriptExecutor jse = (JavascriptExecutor) aDriver;
			jse.executeScript("arguments[0].click();", key);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * it will perform the Mouse Hover action on given webelement field using
	 * Javascript executor
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 * @param webelement
	 * @throws Exception
	 * @throws InterruptedException
	 */

	public void JavaScriptMousehover(final WebDriver aDriver, WebElement key) throws Exception {
		if (key.isDisplayed()) {
			String mouseOverScript = "if(document.createEvent){ " + "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initEvent('mouseover',true, false); " + "arguments[0].dispatchEvent(evObj);} "
					+ "else if(document.createEventObject) { " + "arguments[0].fireEvent('onmouseover');}";
			((JavascriptExecutor) aDriver).executeScript(mouseOverScript, key);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * Click on the given maskedit textboxes webelement field and send the data into
	 * the WebElement Field
	 * 
	 * @author venkat kandula maskedit textboxes (Phone Number, Zip etc......)
	 * @param webelement
	 * @param String
	 * @throws Exception
	 * @throws InterruptedException
	 */

	public void clickandtype(WebElement key, String value) throws Exception {

		if (key.isDisplayed()) {
			key.click();
			key.sendKeys(value);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * Get innerText of given webelement from html doc
	 * 
	 * @author venkat kandula
	 * @param key
	 * @return visible inner html text
	 * @throws Exception
	 */

	public String getText(WebElement key) throws Exception {

		if (key.isDisplayed()) {
			key.getText();
		} else {
			throw new Exception(key + "is not found");
		}
		return key.isDisplayed() ? key.getText() : null;
	}

	/**
	 * Get Attribute name based on id, name, class, value of given webelement from
	 * html doc
	 * 
	 * @author venkat kandula
	 * @param key
	 * @param String
	 * @return visible inner html text
	 * @throws Exception
	 */

	public String getAttribute(WebElement key, String AttributeName) throws Exception {

		if (key.isDisplayed()) {
			key.getAttribute(AttributeName);
		} else {
			throw new Exception(key + "is not found");
		}
		return key.isDisplayed() ? key.getAttribute(AttributeName) : null;
	}

	/**
	 * Verifying the webelement field using isDisplayed method
	 * 
	 * @author venkat kandula
	 * @param WebDriver  aDriver
	 * @param webelement element
	 * @throws InterruptedException
	 */

	public boolean VerifyObject(final WebDriver aDriver, WebElement element) throws Exception {
		try {
			aDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			element.isDisplayed();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Verifying the webelement field using isDisplayed method
	 * 
	 * @author venkat kandula
	 * @param WebDriver aDriver
	 * @param String    objxpath
	 * @throws InterruptedException
	 */

	public boolean VerifyObject(final WebDriver aDriver, String objxpath) throws Exception {
		try {
			aDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			aDriver.findElement(By.xpath(objxpath)).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Clear the input value from webelement field
	 * 
	 * @author venkat kandula
	 * @param webelement
	 * @throws Exception
	 * @throws InterruptedException
	 */

	public void clear(final WebElement key) throws Exception {
		if (key.isDisplayed()) {
			key.clear();
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * Select value in given webelement field
	 * 
	 * @author venkat kandula
	 * @param webelement
	 * @param value
	 * @throws Exception
	 */

	public void selectByValue(final WebElement key, final String value) throws Exception {
		if (key.isDisplayed()) {
			new Select(key).selectByVisibleText(value);
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * Select value in given webelement field based on Index
	 * 
	 * @author venkat kandula
	 * @param webelement
	 * @param int        index
	 * @throws Exception
	 */

	public void selectByIndex(final WebElement key, final int value) throws Exception {
		if (key.isDisplayed()) {
			new Select(key).selectByIndex(value);
			;
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/*
	 * it is used to down the page based on WebElement
	 * 
	 * @author venkat kandula
	 * 
	 * @param webelement
	 * 
	 * @throws Exception
	 */

	public void sendKeysPagedown(final WebElement key) throws Exception {
		if (key.isDisplayed()) {
			key.sendKeys(Keys.PAGE_DOWN);
			;
		} else {
			throw new Exception(key + "is not found");
		}
	}

	/**
	 * Switch to the Frame based on the id or name
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 * @param String
	 */

	public void switchToFrame(final WebDriver aDriver, String frameName) {
		aDriver.switchTo().frame(frameName);
	}

	/**
	 * Switch to the Frame based on the frame index
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 * @param int
	 */

	public void switchToFrame(final WebDriver aDriver, int frameindex) {
		aDriver.switchTo().frame(frameindex);
	}

	/**
	 * Switch to the Frame based on the WebElement field
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 * @param WebElement
	 */

	public void switchToFrame(final WebDriver aDriver, WebElement frameName) {
		aDriver.switchTo().frame(frameName);
	}

	/**
	 * Switch to the default Content
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 */

	public void switchToMain(final WebDriver aDriver) {
		aDriver.switchTo().defaultContent();
	}

	/**
	 * Switch to the Alert window and then Accept
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 */

	public void switchToAlertAccept(final WebDriver aDriver) {
		Alert alert = aDriver.switchTo().alert();
		alert.accept();
	}

	/**
	 * Switch to the Alert window and then Dismiss
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 */

	public void switchToAlertDismiss(final WebDriver aDriver) {
		Alert alert = aDriver.switchTo().alert();
		alert.dismiss();
	}

	/**
	 * Switch to the Alert Prompt box and then type value and Accept
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 * @param String
	 */

	public void switchToAlertPrompttypeandAccept(final WebDriver aDriver, String value) {
		Alert alert = aDriver.switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
	}

	/**
	 * Return true if alert present else return false
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 * @throws Exception
	 */

	public boolean isAlertPresent(final WebDriver aDriver) throws Exception {

		try {
			aDriver.switchTo().alert();
			return true;
		} catch (Exception e) {
			throw new Exception("alert is not found");
		}
	}

	/**
	 * if condition to compare expected and actual
	 * 
	 * @author venkat kandula
	 * @param String expected
	 * @param String actual
	 */

	public void ifcondition(String expected, String actual) {

		if (expected.equals(actual)) {

			System.out.println("expected matches with actual");
		}
	}

	/**
	 * Switch to the child window from parent window
	 * 
	 * @author venkat kandula
	 * @param String
	 * @param WebDriver
	 */

	public void switchToChildWindow(String parentHandler, final WebDriver aDriver) {

		for (String winHandle : aDriver.getWindowHandles()) {
			if (!winHandle.equals(parentHandler)) {
				aDriver.switchTo().window(winHandle);
			}
		}
	}

	/**
	 * Close the Child Window and Switch to the Parent Window
	 * 
	 * @author venkat kandula
	 * @param String
	 * @param WebDriver
	 */

	public void switchTomainWindow(String parentHandler, final WebDriver aDriver) {

		aDriver.close();
		aDriver.switchTo().window(parentHandler);
	}

	/**
	 * get the title of the page
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 */

	public String gettitle(final WebDriver aDriver) {

		return aDriver.getTitle();
	}

	/**
	 * Scroll down page using the Java script
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 */

	public void scrollDown(final WebDriver aDriver) {

		JavascriptExecutor js = (JavascriptExecutor) aDriver;
		js.executeScript("scroll(0,400)");
	}

	/**
	 * Scroll Up page using the Java script
	 * 
	 * @author venkat kandula
	 * @param WebDriver
	 */

	public void scrollUp(final WebDriver aDriver) {

		JavascriptExecutor js = (JavascriptExecutor) aDriver;
		js.executeScript("scroll(300,0)");
	}

	public void JavaScriptscrolltoView(final WebDriver aDriver, WebElement key) throws Exception {
		if (VerifyObject(aDriver, key)) {
			JavascriptExecutor jse = ((JavascriptExecutor) aDriver);
			jse.executeScript("arguments[0].scrollIntoView();", key);
		} else {
			throw new Exception(key + "does not exist");
		}
	}

	public boolean VerifyText(WebDriver aDriver, String Object, String text) throws Exception {

		try {
			WebElement element = aDriver.findElement(By.xpath(Object));
			String elementText = element.getText();
			System.out.println(elementText);
			if (elementText.equalsIgnoreCase(text)) {
				return true;
			}
		} catch (NoSuchElementException e) {
			throw new Exception(Object + "is not found");

		}
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String LatestDownloadedFile(String FolderName, boolean isFromChrome) throws Exception {
		try {

			if (!isFromChrome) {
				File directory = new File(".//");
				String DownloadFilePath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
				File dir = new File(DownloadFilePath);
				File[] files = dir.listFiles();
				Arrays.sort(files);
				Arrays.sort(files, new Comparator() {
					public int compare(Object o1, Object o2) {
						return compare((File) o1, (File) o2);
					}

					private int compare(File f1, File f2) {
						long result = f2.lastModified() - f1.lastModified();
						if (result > 0) {
							return 1;
						} else if (result < 0) {
							return -1;
						} else {
							return 0;
						}
					}
				});
				String LatestDownloadedFile = Arrays.asList(files[0]).toString();
				String regex = "\\[|\\]";
				String DownloadPath = LatestDownloadedFile.replaceAll(regex, "");

				String[] arrFileName = LatestDownloadedFile.split("\\\\");
				FileName = arrFileName[arrFileName.length - 1];
				if (FileName.contains("(")) {
					String[] splitfilename = FileName.split("\\(");
					FileName = splitfilename[0];
					System.out.println("split filename " + splitfilename[0]);
				}
				String[] arrFileNames = FileName.split("\\.");
				String FileNames = arrFileNames[0];
				DownloadedFileName = FileNames;
				String s = xDownloadFiles(FolderName);
				Filepath = s + "\\" + FileName.replace("]", "");
				Path RSource = Paths.get(DownloadPath);
				Path RTarget = Paths.get(Filepath);
				Files.move(RSource, RTarget, REPLACE_EXISTING);
			} else {
				String DownloadFilePath = xDownloadFiles(FolderName);

				File dir = new File(DownloadFilePath);
				File[] files = dir.listFiles();
				Arrays.sort(files);
				Arrays.sort(files, new Comparator() {
					public int compare(Object o1, Object o2) {
						return compare((File) o1, (File) o2);
					}

					private int compare(File f1, File f2) {
						long result = f2.lastModified() - f1.lastModified();
						if (result > 0) {
							return 1;
						} else if (result < 0) {
							return -1;
						} else {
							return 0;
						}
					}
				});
				String LatestDownloadedFile = Arrays.asList(files[0]).toString();
				String regex = "\\[|\\]";
				String DownloadPath = LatestDownloadedFile.replaceAll(regex, "");
				Filepath = DownloadPath;
				System.out.println(DownloadPath);
				String[] arrFileName = LatestDownloadedFile.split("\\\\");
				FileName = arrFileName[arrFileName.length - 1];
				if (FileName.contains("(")) {
					String[] splitfilename = FileName.split("\\(");
					FileName = splitfilename[0];
					System.out.println("split filename " + splitfilename[0]);
				}
				String[] arrFileNames = FileName.split("\\.");
				String FileNames = arrFileNames[0];
				DownloadedFileName = FileNames;
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return Filepath;
	}

	public ResultSet DBQuery(String queryvalue) throws Exception {
		ResultSet rs = null;
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
			new File(".//");
			// create object of Driver
			Class.forName(driver).newInstance();
			// connection will be established
			conn = DriverManager.getConnection(url + dbName, userName, password);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(queryvalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void waitandswitchToFrame(WebElement iframe, final WebDriver aDriver) {
		WebDriverWait wait = new WebDriverWait(aDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
	}

	public String AccessToken() {
		try {
			File file = new File(".//");
			String mainfolder = file.getCanonicalPath();
			Workbook DriverscriptWorkbook = Workbook.getWorkbook(new File(mainfolder + "//TestSetup//TestSetup.xls"));
			Sheet TestRailSheet = DriverscriptWorkbook.getSheet("TestRail");
			int TestRail_API_Url = 1, LoginID = 2, Password = 3;
			String name = TestRailSheet.getCell(LoginID, 1).getContents();
			String password = TestRailSheet.getCell(Password, 1).getContents();
			TestRailurl = TestRailSheet.getCell(TestRail_API_Url, 1).getContents();
			String authString = name + ":" + password;
			String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
			BearerToken = authStringEnc;
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public String xRayAccessToken() {

		try {
			String url = null, clientid = null, clientsecret = null, Payload = null;
			url = "https://xray.cloud.xpand-it.com/api/v1/authenticate";
			clientid = " ";
			clientsecret = " ";
			Payload = "client_id=" + clientid + "&client_secret=" + clientsecret + "&grant_type=client_credentials";
			client = Client.create();
			webResource = client.resource(url);
			response = webResource.accept("application/json").type("application/x-www-form-urlencoded")
					.post(ClientResponse.class, Payload);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			BearerToken = "Bearer" + " " + response.getEntity(String.class);
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public void VerifyObjectIsNotDisplayed(String eXpath) throws Exception {
		List<WebElement> eobject = driver.findElements(By.xpath(eXpath));
		assertFalse(eobject.size() > 0);
	}

	public void VerifyObjectIsDisplayed(String eXpath) throws Exception {
		List<WebElement> eobject = driver.findElements(By.xpath(eXpath));
		assertTrue(eobject.size() > 0);
	}

	public void verifyObjectIsDisabled(String eXpath) {
		boolean eObject = driver.findElement(By.xpath(eXpath)).isEnabled();
		assertFalse(eObject);
	}
}
