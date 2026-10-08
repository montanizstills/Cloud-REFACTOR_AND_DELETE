/*###################################################################################################
Name: UtilityScript.java 
Description: UtilityScript having all reussable common methods 
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.utilities;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SubjectTerm;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.base.Joiner;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import common.libraries.admin.AdminFunctions;
import common.libraries.admin.Changesubscription;
import common.libraries.admin.ClientDelete;
import common.libraries.admin.DocumentCenter;
import common.libraries.admin.ManageCustomers;
import common.libraries.admin.PremiumCustomers;
import common.libraries.connectors.Connectors;
import common.libraries.cpa.CPAManaged;
import common.libraries.cpa.CPAPortal;
import common.libraries.customer.Calcparity;
import common.libraries.customer.Configuration;
import common.libraries.customer.ManageTaxreturns;
import common.libraries.customer.PortalFunctions;
import common.libraries.customer.Prepayments;
import common.libraries.customer.RateFile;
import common.libraries.customer.RatesJurisdictions;
import common.libraries.customer.Reports;
import common.libraries.customer.Settings;
import common.libraries.customer.TransactionReport;
import common.libraries.customer.Transactions;
import common.libraries.database.DataBase;
import common.libraries.ecw.ExemptionCertificateWizard;
import common.libraries.frachise.Franchise;
import common.libraries.generic.CommonFunctions;
import common.libraries.miscellaneous.Features;
import common.libraries.miscellaneous.HVR;
import common.libraries.miscellaneous.LinksNavigation;
import common.libraries.miscellaneous.Notifications;
import common.libraries.miscellaneous.OSeries9;
import common.libraries.qbo.QboApi;
import common.libraries.qbo.QuickBooks;
import common.libraries.qbo.QuickBooksUI;
import common.libraries.restapi.RestAPI;
import common.libraries.rte.RTE;
import common.libraries.salesforce.SalesForce;
import common.libraries.testinstance.TestInstanceCreation;
import common.libraries.usercreation.UserRegistration;
import common.libraries.webdriver.DriverManager;
import common.pages.Addseller;
import common.pages.AdminUndoAdjustmentFile;
import common.pages.AdminUserLinks;
import common.pages.CPA;
import common.pages.ConfigurePage;
import common.pages.Cpa_ManageUsers;
import common.pages.IntegrationAdmin;
import common.pages.LoginPage;
import common.pages.ProfessionalUserLinks;
import common.pages.QBOFirstTimeMap;
import common.pages.RegistrationPage;
import common.pages.SalesForcePage;
import common.pages.VertexSMBCustomers;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.Pattern;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class UtilityScript extends DriverManager {

	protected static WebDriver driver;
	public static String UniqueITIurl = null;
	public static String uniqueITIid = null;
	public static String VertexUserName;
	public static String cloudelementsclient;
	public static String NewFileNamePath;
	public static String Cell_Value;
	public static String returnvalue;
	public static String Filingfreq;
	public static String Compstat;
	public static String Subscription = "";
	public static String DNSname;
	public static String msgtype;
	public static String transcount;
	public static String Cell_Header;
	public static String ClientNme;
	public static String CategoryType;
	public static String Col_Row_Content;
	private static SecureRandom random = new SecureRandom();
	public static String DownloadedFileName;
	public static String Filepath;
	public static String DownloadFiles;
	public static String actualfile;
	public static String BillAmount;
	public static Date TestEndDate;
	Calendar cal = Calendar.getInstance();
	public static String listoffiles = "";
	public static CSVReader reader;
	public static CSVWriter csvOutput;
	public static String FileName;
	public static Sheet sheet;
	public static Workbook WorkBook;
	public static String testinstanceId;
	public static String MerchantResentURL;
	public static String PendingResentURL;
	public static String TestInstanceSetPwdURL;
	public static String TestInstanceUserName;
	public static String TestRailurl = null;
	public static String BearerToken = null;
	public static String APIuploadID = null;
	public static String APIadjustmentfileStatus = null;
	public static String APIRteStatus = null;
	public static String QBOSubscription = null;
	public static String QBOAddress = null;
	public static String Sub_scription;
	public static String Taxability_Category_Name;
	public static String ErrorScreenShot;
	public static String password = null;
	protected static WritableWorkbook resultWritebook;
	protected static WritableSheet resultWriteSheet;
	public static int RowNo = 1;
	public static int ScenarioNo = 1;
	public static String StartTime;
	public AdminFunctions adminPortal = null;
	public Changesubscription changeSubscription = null;
	public ClientDelete clientDelete = null;
	public Configuration configuration = null;
	public Connectors connectors = null;
	public CPAManaged cpaManaged = null;
	public CPAPortal cpaPortal = null;
	public DataBase database = null;
	public DocumentCenter documentCenter = null;
	public ExemptionCertificateWizard ecWizard = null;
	public Features features = null;
	public Franchise franchise = null;
	public HVR hvr = null;
	public LinksNavigation linksNavigation = null;
	public ManageCustomers manageCustomers = null;
	public ManageTaxreturns manageTaxreturns = null;
	public Notifications notifications = null;
	public OSeries9 osereis9 = null;
	public PortalFunctions clientPortal = null;
	public PremiumCustomers premiumCustomers = null;
	public Prepayments prepayments = null;
	public QboApi qboAPI = null;
	public QuickBooks quickbooks = null;
	public QuickBooksUI quickbooksUI = null;
	public RateFile ratefiles = null;
	public RatesJurisdictions ratesJurisdictions = null;
	public Reports reports = null;
	public RestAPI restAPI = null;
	public RTE rte = null;
	public SalesForce salesforce = null;
	public TestInstanceCreation testInstance = null;
	public TransactionReport transactionReport = null;
	public Transactions transactions = null;
	public UserRegistration userRegistration = null;
	public Settings settings = null;
	public Calcparity calcparity = null;
	public Connectors connector = null;

	public LoginPage loginPage = null;
	public QBOFirstTimeMap qbo = null;
	public Addseller addsellerpage = null;
	public RegistrationPage registrationPage = null;
	public ConfigurePage configurePage = null;
	public SalesForcePage salesforcePage = null;
	public IntegrationAdmin integrationadminPage = null;
	public AdminUserLinks adminlinksPage = null;
	public VertexSMBCustomers vertexsmbcustomersPage = null;
	public AdminUndoAdjustmentFile adminundoadjustmentfilePage = null;
	public ProfessionalUserLinks professionalLinks = null;
	public CPA cpaPage = null;
	public Cpa_ManageUsers cpamanagersPage = null;
	public static String transactionid;

	public void setDriver(WebDriver driver) {
		UtilityScript.driver = driver;
		adminPortal = PageFactory.initElements(driver, AdminFunctions.class);
		changeSubscription = PageFactory.initElements(driver, Changesubscription.class);
		clientDelete = PageFactory.initElements(driver, ClientDelete.class);
		configuration = PageFactory.initElements(driver, Configuration.class);
		connectors = PageFactory.initElements(driver, Connectors.class);
		cpaManaged = PageFactory.initElements(driver, CPAManaged.class);
		cpaPortal = PageFactory.initElements(driver, CPAPortal.class);
		database = PageFactory.initElements(driver, DataBase.class);
		documentCenter = PageFactory.initElements(driver, DocumentCenter.class);
		ecWizard = PageFactory.initElements(driver, ExemptionCertificateWizard.class);
		features = PageFactory.initElements(driver, Features.class);
		franchise = PageFactory.initElements(driver, Franchise.class);
		hvr = PageFactory.initElements(driver, HVR.class);
		linksNavigation = PageFactory.initElements(driver, LinksNavigation.class);
		manageCustomers = PageFactory.initElements(driver, ManageCustomers.class);
		manageTaxreturns = PageFactory.initElements(driver, ManageTaxreturns.class);
		notifications = PageFactory.initElements(driver, Notifications.class);
		osereis9 = PageFactory.initElements(driver, OSeries9.class);
		clientPortal = PageFactory.initElements(driver, PortalFunctions.class);
		premiumCustomers = PageFactory.initElements(driver, PremiumCustomers.class);
		prepayments = PageFactory.initElements(driver, Prepayments.class);
		qboAPI = PageFactory.initElements(driver, QboApi.class);
		quickbooks = PageFactory.initElements(driver, QuickBooks.class);
		quickbooksUI = PageFactory.initElements(driver, QuickBooksUI.class);
		ratefiles = PageFactory.initElements(driver, RateFile.class);
		ratesJurisdictions = PageFactory.initElements(driver, RatesJurisdictions.class);
		reports = PageFactory.initElements(driver, Reports.class);
		restAPI = PageFactory.initElements(driver, RestAPI.class);
		rte = PageFactory.initElements(driver, RTE.class);
		salesforce = PageFactory.initElements(driver, SalesForce.class);
		testInstance = PageFactory.initElements(driver, TestInstanceCreation.class);
		transactionReport = PageFactory.initElements(driver, TransactionReport.class);
		transactions = PageFactory.initElements(driver, Transactions.class);
		userRegistration = PageFactory.initElements(driver, UserRegistration.class);
		connector = PageFactory.initElements(driver, Connectors.class);
		settings = PageFactory.initElements(driver, Settings.class);
		calcparity = PageFactory.initElements(driver, Calcparity.class);

	}

	public void pageDriver(WebDriver driver) {

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		qbo = PageFactory.initElements(driver, QBOFirstTimeMap.class);
		registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
		configurePage = PageFactory.initElements(driver, ConfigurePage.class);
		salesforcePage = PageFactory.initElements(driver, SalesForcePage.class);
		integrationadminPage = PageFactory.initElements(driver, IntegrationAdmin.class);
		adminlinksPage = PageFactory.initElements(driver, AdminUserLinks.class);
		vertexsmbcustomersPage = PageFactory.initElements(driver, VertexSMBCustomers.class);
		adminundoadjustmentfilePage = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		professionalLinks = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		cpaPage = PageFactory.initElements(driver, CPA.class);
		cpamanagersPage = PageFactory.initElements(driver, Cpa_ManageUsers.class);

	}

	// To kill the process in Task Manager
	public void kill() throws Exception {
		try {
			processName = "iexplore.exe";
			processName1 = "IEDriverServer.exe";
			processName2 = "chromedriver.exe";
			processName3 = "EXCEL.EXE";
			processName4 = "geckodriver64.exe";
			if (isRunning()) {
				getRuntime().exec("taskkill /F /IM " + processName);
				getRuntime().exec("taskkill /F /IM " + processName1);
				getRuntime().exec("taskkill /F /IM " + processName2);
				getRuntime().exec("taskkill /F /IM " + processName3);
				getRuntime().exec("taskkill /F /IM " + processName4);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private boolean isRunning() throws Exception {
		try {
			Process listTasksProcess = getRuntime().exec("tasklist");
			BufferedReader tasksListReader = new BufferedReader(
					new InputStreamReader(listTasksProcess.getInputStream()));

			String tasksLine;

			while ((tasksLine = tasksListReader.readLine()) != null) {
				if (tasksLine.contains(processName3) || tasksLine.contains(processName)
						|| tasksLine.contains(processName1) || tasksLine.contains(processName2)
						|| tasksLine.contains(processName4)) // matches(processName))
				{
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	private Runtime getRuntime() {
		return Runtime.getRuntime();
	}

	// Get date time
	public static java.lang.String xGetDateTime() throws Exception {
		// get current date time with Date() to create unique file name
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
		// get current date time with Date()
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// DateFormat = "MM/dd/yyyy '-' h:mm a";
	public java.lang.String xGetDTAP() throws Exception {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy '-' h:mm a");
		// System.out.println("Current Date: " + ft.format(dNow));
		return (ft.format(dNow));
	}

	// DateFormat = "MMM dd, yyyy";
	public java.lang.String xGetDate(String DateFormat) throws Exception {
		// get current date time with Date() to create unique file name
		DateFormat dateFormat = new SimpleDateFormat(DateFormat);
		// get current date time with Date()
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// Get date time with SelText
	public static java.lang.String xGetDateTimeSel() throws Exception {
		// get current date time with Date() to create unique file name
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaadd_MMM_yyyy");
		// get current date time with Date()
		Date date = new Date();
		return ("S_" + dateFormat.format(date));
	}

	public java.lang.String xPreviousDate(String DateFormat) throws Exception {
		/*
		 * // get current date time with Date() to create unique file name DateFormat
		 * dateFormat = new SimpleDateFormat(DateFormat); // get current date time with
		 * Date() cal.clear(); cal.add(Calendar.DATE, -1);
		 * System.out.println("date value is" +dateFormat.format(cal.getTime())); return
		 * (dateFormat.format(cal.getTime()));
		 */
		DateFormat dateFormat = new SimpleDateFormat(DateFormat);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}

	// Get date time with System IP
	public java.lang.String xGetDateTimeIP() throws Exception {
		// get current date time with Date() to create unique file name
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ssaa_dd_MMM_yyyy");
		// get current date time with Date()
		Date date = new Date();
		// To identify the system
		InetAddress ownIP = InetAddress.getLocalHost();
		return (dateFormat.format(date) + "_IP" + ownIP.getHostAddress());
	}

	// Get date time sec stamp
	public static String RND() throws Exception {
		@SuppressWarnings("deprecation")

		String DTStamp = (((new Date()).getMonth()) + 1) + ((new Date()).getDate()) + ((new Date()).getYear()) + ""
				+ ((new Date()).getHours()) + ((new Date()).getMinutes()) + ((new Date()).getSeconds());
		return (DTStamp);
	}

	public static String GetCurrentMonthYear() throws Exception {
		SimpleDateFormat ft = new SimpleDateFormat("MM/yyyy");
		String s = ft.format(new Date());
		return (s);
	}

	public static String RND1() throws Exception {
		@SuppressWarnings("deprecation")
		String DTStamp = (((new Date()).getMonth()) + 1) + ((new Date()).getYear()) + "" + ((new Date()).getSeconds());
		return (DTStamp);
	}

	public static Workbook xGetWorkBook(String TestPath, String value) throws Exception {
		WorkBook = Workbook.getWorkbook(new File(TestPath));
		return WorkBook;
	}

	public static Sheet xGetdtSheet(String TestPath, String value) throws Exception {
		String[] Data;
		Data = value.split(";");
		WorkBook = Workbook.getWorkbook(new File(TestPath));
		sheet = WorkBook.getSheet(Data[1]);
		return sheet;
	}

	public static String xDownloadFiles(String FolderName) throws Exception {
		DownloadFiles = null;
		File directory = new File(".//");
		String DownloadLoc = directory.getCanonicalPath();
		DownloadFiles = DownloadLoc + "\\" + "resources\\testdata\\actual" + "\\" + FolderName;
		File CreateFolder = new File(DownloadFiles);
		if (!CreateFolder.exists()) {
			CreateFolder.mkdirs();
		}

		return (DownloadFiles);
	}

	public static String xUploadAdjustmentFile(String FolderName) throws Exception {
		String AdjustmentFiles = null;
		File directory = new File(".//");
		String AdjstLoc = directory.getCanonicalPath();
		AdjustmentFiles = AdjstLoc + "\\" + "resources\\testdata\\upload" + "\\" + FolderName;
		return (AdjustmentFiles);
	}

	public static String[] StoreCountriesinArray() throws Exception {

		String countriesList[] = { "United States", "Canada", "Afghanistan", "Aland Islands", "Albania", "Algeria",
				"Andorra", "Angola", "Anguilla", "Antarctica", "Antigua And Barbuda", "Argentina", "Armenia", "Aruba",
				"Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus",
				"Belgium", "Belize", "Benin", "Bermuda", "Bes Islands", "Bhutan", "Bolivia", "Bosnia And Herzegovina",
				"Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands",
				"Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Campione D'italia",
				"Canary Islands", "Cape Verde", "Cayman Islands", "Central African Republic", "Ceuta", "Chad", "Chile",
				"China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
				"Congo, The Democratic Republic Of The", "Cook Islands", "Costa Rica", "Cote D'ivoire", "Croatia",
				"Cuba", "Curacao", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
				"Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Eswatini", "Ethiopia",
				"Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "French Guiana",
				"French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana",
				"Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guatemala", "Guernsey", "Guinea",
				"Guinea-Bissau", "Guyana", "Haiti", "Heard Island And Mcdonald Islands", "Heligoland",
				"Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia",
				"Iran", "Iraq", "Ireland", "Israel", "Italian Waters Of Lake Lugano", "Italy", "Jamaica", "Japan",
				"Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos",
				"Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Lithuania", "Livigno", "Luxembourg", "Macao",
				"Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Martinique", "Mauritania",
				"Mauritius", "Mayotte", "Melilla", "Mexico", "Moldova", "Mongolia", "Montenegro", "Montserrat",
				"Morocco", "Mount Athos", "Mozambique", "Myanmar", "Namibia", "National Caribbean Netherlands", "Nauru",
				"Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger",
				"Nigeria", "Niue", "Norfolk Island", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan",
				"Palau", "Palestinian Territory", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
				"Pitcairn", "Poland", "Portugal", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda",
				"Saint Barthelemy", "Saint Helena", "Saint Kitts And Nevis", "Saint Lucia", "Saint Martin",
				"Saint Pierre And Miquelon", "Saint Vincent And The Grenadines", "Samoa", "San Marino",
				"Sao Tome And Principe", "Saudi Arabia", "Senegal", "Serbia", "Serbia And Montenegro", "Seychelles",
				"Sierra Leone", "Singapore", "Sint Maarten", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
				"South Africa", "South Georgia And The South Sandwich Islands", "South Korea", "South Sudan", "Spain",
				"Sri Lanka", "Sudan", "Suriname", "Svalbard And Jan Mayen", "Sweden", "Switzerland", "Syria", "Taiwan",
				"Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo", "Tokelau", "Tonga", "Trinidad And Tobago",
				"Tunisia", "Turkey", "Turkmenistan", "Turks And Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
				"United Arab Emirates", "United Kingdom", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam",
				"Wallis And Futuna", "Western Sahara", "Yemen", "Zambia", "Zimbabwe" };

		return (countriesList);
	}

	public static String[] xrStoreCountriesinArray(String TestPath, String countriessheet) throws Exception {
		Workbook workbook = Workbook.getWorkbook(new File(TestPath));
		Sheet sheet = workbook.getSheet(countriessheet);
		int row = sheet.getRows();
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 1; i < row; i++) {
			String data = sheet.getCell(0, i).getContents();
			list.add(data);
		}

		String countriesList[] = list.toArray(new String[list.size()]);
		return (countriesList);
	}

	public static String xAutoIT() throws Exception {
		File directory = new File(".//");
		String AutoIT = directory.getCanonicalPath() + "\\resources\\autoit";
		return (AutoIT);
	}

	public static void Wait(int MilliSec) throws InterruptedException {
		Thread.sleep(MilliSec);
	}

	public void Print(String Text) {
		System.out.println(Text);
		Reporter.log(Text);
		String Temp = Text;
		sMessages = sMessages + Temp.replaceAll(" ", "_") + "#";
	}

	public java.lang.String xAddMinutesToTheDateTime(String Date_TimeFormat, int NumberOfMinutes)
			throws InterruptedException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFormat);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(Date_TimeFormat));
		c.add(Calendar.MINUTE, NumberOfMinutes); // number of minutes
		String str = sdf.format(c.getTime());
		String delimiter = "_";
		String[] temp;
		temp = str.split(delimiter);
		for (int i = 0; i < temp.length - 1; i++) {
			NewDate = temp[i];
			NewTime = temp[i + 1];
		}
		// Print(NewDate);
		// Print(NewTime);

		return (str); // dt is now the new date
	}

	public java.lang.String xAddDaysToTheDateTime(String CurrentDate, int NumberOfDays, String DateFormat)
			throws InterruptedException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(CurrentDate));
		c.add(Calendar.DATE, NumberOfDays); // number of days
		String str = sdf.format(c.getTime());
		return (str); // dt is now the new date
	}

	// printing first date of the month
	public static java.lang.String xMonthsFirstDate(String CurrentDate, int NumberOfMonths, String DateFormat)
			throws InterruptedException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(CurrentDate));
		c.add(Calendar.MONTH, NumberOfMonths); // number of days
		String str = sdf.format(c.getTime());
		return (str); // dt is now the new date
	}

	public java.lang.String xGetCurrentDateEST(String DateFormat) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat);
		dateFormat.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
		NewDate = dateFormat.format(new Date());
		return (NewDate);
	}

	public java.lang.String xGetCurrentTimeEST() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		dateFormat.setTimeZone(TimeZone.getTimeZone("EST5EDT"));
		NewTime = dateFormat.format(new Date());
		return (NewTime);
	}

	public static java.lang.String xGetCurrentDateUTC(String DateFormat) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		NewDate = dateFormat.format(new Date());
		return (NewDate);
	}

	public static java.lang.String xGetCurrentTime() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		NewTime = dateFormat.format(new Date());
		return (NewTime);
	}

	public String xGetCurrentDate(String DateFormat) {

		DateFormat dateFormat = new SimpleDateFormat(DateFormat);
		String date = dateFormat.format(new Date());
		return (date);
	}

	public String xGetTomorrowDate(String DateFormat) {
		String s;
		Date date;
		Format formatter;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		date = calendar.getTime();
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		s = formatter.format(date);
		return (s);
	}

	public String xGetPrevoiusDate(String DateFormat) {
		String s;
		Date date;
		Format formatter;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		date = calendar.getTime();
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		s = formatter.format(date);
		return (s);
	}

	public String xGetCurrentDate(String DateFormat, int Days) {

		DateFormat dateFormat = new SimpleDateFormat(DateFormat);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, Days);
		String date = dateFormat.format(c.getTime());
		return (date);
	}

	public void Update_AdjAPIConfigXML(String AuthURI, String RootURI, String ClientID) throws Exception {
		String ElemnetName = null;
		try {
			File directory = new File(".//");
			String XMLLoc = directory.getCanonicalPath();
			String filePath = XMLLoc + "\\resources\\adjfilesdk\\AdjFileMgr.exe.config";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);

			NodeList list = doc.getElementsByTagName("add");

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				Element fstElmnt = (Element) node;
				ElemnetName = fstElmnt.getAttribute("key");
				if (ElemnetName.equals("auth_uri")) {
					fstElmnt.setAttribute("value", AuthURI);
				}
				if (ElemnetName.equals("root_uri")) {
					fstElmnt.setAttribute("value", RootURI);
				}
				if (ElemnetName.equals("client_id")) {
					fstElmnt.setAttribute("value", ClientID);
				}
			}
			// write the updated document to file or console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	// For updating importer config
	public void Update_ImporterConfigXML(String AcesTkn, String AcesTknScrt, String CnsumerKey, String CnsumerScret,
			String AppTkn, String RalmId) throws Exception {
		String ElemnetName = null;
		try {
			File directory = new File(".//");
			String filePath = directory.getCanonicalPath()
					+ "\\resources\\qboimporter\\bin\\Release\\QBOImporter.exe.config";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			NodeList list = doc.getElementsByTagName("add");
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				Element fstElmnt = (Element) node;
				ElemnetName = fstElmnt.getAttribute("key");
				if (ElemnetName.equals("AccessToken")) {
					fstElmnt.setAttribute("value", AcesTkn);
				}
				if (ElemnetName.equals("AccessTokenSecret")) {
					fstElmnt.setAttribute("value", AcesTknScrt);
				}
				if (ElemnetName.equals("ConsumerKey")) {
					fstElmnt.setAttribute("value", CnsumerKey);
				}
				if (ElemnetName.equals("ConsumerSecret")) {
					fstElmnt.setAttribute("value", CnsumerScret);
				}
				if (ElemnetName.equals("AppToken")) {
					fstElmnt.setAttribute("value", AppTkn);
				}
				if (ElemnetName.equals("RealmId")) {
					fstElmnt.setAttribute("value", RalmId);
				}

			}
			// write the updated document to file or console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public static String xRND(int Day_argument, int Month_argument, int Year_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, Day_argument);

		cal.add(Calendar.MONTH, Month_argument);

		cal.add(Calendar.YEAR, Year_argument);

		String transactionid = dateFormat.format(cal.getTime());

		return (transactionid);
	}

	// Updating datachecks related xml file
	public void xUpdateXML(String fileName, String TruID, String Postingdate, String TaxpayerCode) throws Exception {
		try {
			File directory = new File(".//");
			String XMLLoc = directory.getCanonicalPath();
			String filePath = XMLLoc + "\\" + "resources\\testdata\\upload" + "\\" + "ConnectorTransactions" + "\\"
					+ fileName;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			Node Login = doc.getElementsByTagName("Login").item(0);
			NodeList list = Login.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if ("TrustedId".equals(node.getNodeName())) {
					node.setTextContent(TruID);
				}
				if ("documentDate".equals(node.getNodeName())) {
					node.setTextContent(Postingdate);
				} 
				if ("postingDate".equals(node.getNodeName())) {
					if (!fileName.equals("CutInvoiceVerification_Myusage.xml")) {
					node.setTextContent(Postingdate);
					}
				} 
			}

			/*Node req = doc.getElementsByTagName("VertexEnvelope").item(0).getChildNodes().item(3);
			//Node invoicereq = doc.getElementsByTagName("InvoiceRequest").item(0)
			NamedNodeMap attr = req.getAttributes();
			//attr.getNamedItem("documentDate").setNodeValue(Postingdate);
			if (!fileName.equals("CutInvoiceVerification_Myusage.xml")) {
				Node req = doc.getElementsByTagName("VertexEnvelope").item(0).getChildNodes().item(3);
				NamedNodeMap attr = req.getAttributes();
				if (attr.getNamedItem("postingDate") != null) {
					attr.getNamedItem("postingDate").setNodeValue(Postingdate);
				}

			}*/
			if (TaxpayerCode != "") {
				Node compcode = doc.getElementsByTagName("Seller").item(0);
				NodeList list1 = compcode.getChildNodes();
				for (int j = 0; j < list1.getLength(); j++) {
					Node node1 = list1.item(j);
					if ("Company".equals(node1.getNodeName())) {
						node1.setTextContent(TaxpayerCode);
					}
				}
			}
			// write the updated document to file or console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
			Thread.sleep(10000);// To update Trusted ID it will take time
			System.out.println("DONE");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	// Updating datachecks related xml file
	public void xUpdateXML(String fileName, String TruID, String Postingdate, String TaxpayerCode, String transactionid)
			throws Exception {
		try {
			File directory = new File(".//");
			String XMLLoc = directory.getCanonicalPath();
			String filePath = XMLLoc + "\\" + "resources\\testdata\\upload" + "\\" + "ConnectorTransactions" + "\\"
					+ fileName;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			Node Login = doc.getElementsByTagName("Login").item(0);
			NodeList list = Login.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if ("TrustedId".equals(node.getNodeName())) {
					node.setTextContent(TruID);
				}
			}

			Node req = doc.getElementsByTagName("VertexEnvelope").item(0).getChildNodes().item(3);
			// Node invoicereq = doc.getElementsByTagName("InvoiceRequest").item(0)
			NamedNodeMap attr = req.getAttributes();

			if (fileName.equalsIgnoreCase("ARBillingSyncRequest.xml")) {

				attr.getNamedItem("documentNumber").setNodeValue(Postingdate);
			}

			if (fileName.equalsIgnoreCase("ReversalRequest.xml")) {

				attr.getNamedItem("originalTransactionId").setNodeValue(transactionid);

				attr.getNamedItem("transactionId").setNodeValue(transactionid + "_REV");

				attr.getNamedItem("postingDate").setNodeValue(Postingdate);

			} else {
				attr.getNamedItem("transactionId").setNodeValue(transactionid);
			}

			if (TaxpayerCode != "") {
				Node compcode = doc.getElementsByTagName("Seller").item(0);
				NodeList list1 = compcode.getChildNodes();
				for (int j = 0; j < list1.getLength(); j++) {
					Node node1 = list1.item(j);
					if ("Company".equals(node1.getNodeName())) {
						node1.setTextContent(TaxpayerCode);
					}
				}
			}
			// write the updated document to file or console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
			Thread.sleep(10000);// To update Trusted ID it will take time
			System.out.println("DONE");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public void xUpdateXML1(String fileName, String TruID, String DocumentDate, String TaxpayerCode) throws Exception {
		try {
			File directory = new File(".//");
			String XMLLoc = directory.getCanonicalPath();
			String filePath = XMLLoc + "\\" + "resources\\testdata\\upload" + "\\" + "ConnectorTransactions" + "\\"
					+ fileName;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			Node Login = doc.getElementsByTagName("Login").item(0);
			NodeList list = Login.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if ("TrustedId".equals(node.getNodeName())) {
					node.setTextContent(TruID);
				}
			}

			Node req = doc.getElementsByTagName("VertexEnvelope").item(0).getChildNodes().item(3);
			// Node invoicereq = doc.getElementsByTagName("InvoiceRequest").item(0)
			NamedNodeMap attr = req.getAttributes();
			attr.getNamedItem("documentDate").setNodeValue(DocumentDate);

			attr.getNamedItem("postingDate").setNodeValue(DocumentDate);
			if (TaxpayerCode != "") {
				Node compcode = doc.getElementsByTagName("Seller").item(0);
				NodeList list1 = compcode.getChildNodes();
				for (int j = 0; j < list1.getLength(); j++) {
					Node node1 = list1.item(j);
					if ("Company".equals(node1.getNodeName())) {
						node1.setTextContent(TaxpayerCode);
					}
				}
			}
			// write the updated document to file or console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
			Thread.sleep(10000);// To update Trusted ID it will take time
			System.out.println("DONE");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	// Updating Address Cleansing related xml file
	public void AddressCleansingxUpdateXML(String fileName, String TruID, String Postingdate) throws Exception {
		try {
			File directory = new File(".//");
			String XMLLoc = directory.getCanonicalPath();
			String filePath = XMLLoc + "\\" + "resources\\testdata\\upload" + "\\" + "ConnectorTransactions" + "\\"
					+ fileName;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			Node Login = doc.getElementsByTagName("Login").item(0);
			NodeList list = Login.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if ("TrustedId".equals(node.getNodeName())) {
					node.setTextContent(TruID);
				}
			}
			Thread.sleep(10000);// To update Trusted ID it will take time
			// write the updated document to file or console

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
			Thread.sleep(10000);// To update Trusted ID it will take time
			System.out.println("DONE");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	// Updating connector related xml file
	public void xUpdateConnectorXML(String folderName, String xmlFlie, String TruID, String Postingdate,
			String transType, String Companycode) throws Exception {
		try {
			File directory = new File(".//");
			String XMLLoc = directory.getCanonicalPath();
			String filePath = XMLLoc + "\\" + "resources\\testdata\\upload" + "\\" + "ConnectorTransactions" + "\\"
					+ folderName + "\\" + xmlFlie;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			NamedNodeMap attr = null;
			Node documentDate = null;
			if (TruID != null) {
				Node Login = doc.getElementsByTagName("Login").item(0);
				NodeList list = Login.getChildNodes();

				for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					if ("TrustedId".equals(node.getNodeName())) {
						node.setTextContent(TruID);
					}
				}
			}
			if (Postingdate != "") {
				transType = transType.toUpperCase();
				switch (transType) {
				case "INVOICE":
					Node InvoiceRequest = doc.getElementsByTagName("InvoiceRequest").item(0);
					attr = InvoiceRequest.getAttributes();
					documentDate = attr.getNamedItem("documentDate");
					Node postingDate = attr.getNamedItem("postingDate");
					documentDate.setTextContent(Postingdate);
					postingDate.setTextContent(Postingdate);
					break;
				case "PURCHASEORDER":
					Node PurchaseOrderRequest = doc.getElementsByTagName("PurchaseOrderRequest").item(0);
					attr = PurchaseOrderRequest.getAttributes();
					documentDate = attr.getNamedItem("documentDate");
					documentDate.setTextContent(Postingdate);
					break;
				case "QUOTE":
					Node QuotationRequest = doc.getElementsByTagName("QuotationRequest").item(0);
					attr = QuotationRequest.getAttributes();
					documentDate = attr.getNamedItem("documentDate");
					documentDate.setTextContent(Postingdate);
					break;
				}
			}
			if (!Companycode.isEmpty()) {
				Node compcode = doc.getElementsByTagName("Seller").item(0);
				NodeList list = compcode.getChildNodes();

				for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					if ("Company".equals(node.getNodeName())) {
						node.setTextContent(Companycode);
					}
				}
			}
			// write the updated document to file or console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);

			System.out.println("DONE");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public void xUpdateConnectorXML(String folderName, String xmlFlie, String TruID, String Postingdate,
			String transType) throws Exception {
		try {
			File directory = new File(".//");
			String XMLLoc = directory.getCanonicalPath();
			String filePath = XMLLoc + "\\" + "resources\\testdata\\upload" + "\\" + "ConnectorTransactions" + "\\"
					+ folderName + "\\" + xmlFlie;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			NamedNodeMap attr = null;
			Node documentDate = null;
			if (TruID != null) {
				Node Login = doc.getElementsByTagName("Login").item(0);
				NodeList list = Login.getChildNodes();

				for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					if ("TrustedId".equals(node.getNodeName())) {
						node.setTextContent(TruID);
					}
				}
			}
			if (Postingdate != "") {
				transType = transType.toUpperCase();
				switch (transType) {
				case "INVOICE":
					Node InvoiceRequest = doc.getElementsByTagName("InvoiceRequest").item(0);
					attr = InvoiceRequest.getAttributes();
					documentDate = attr.getNamedItem("documentDate");
					Node postingDate = attr.getNamedItem("postingDate");
					documentDate.setTextContent(Postingdate);
					postingDate.setTextContent(Postingdate);
					break;
				case "PURCHASEORDER":
					Node PurchaseOrderRequest = doc.getElementsByTagName("PurchaseOrderRequest").item(0);
					attr = PurchaseOrderRequest.getAttributes();
					documentDate = attr.getNamedItem("documentDate");
					documentDate.setTextContent(Postingdate);
					break;
				case "QUOTE":
					Node QuotationRequest = doc.getElementsByTagName("QuotationRequest").item(0);
					attr = QuotationRequest.getAttributes();
					documentDate = attr.getNamedItem("documentDate");
					documentDate.setTextContent(Postingdate);
					break;
				}
			}

			// write the updated document to file or console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);

			System.out.println("DONE");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	/* updating date in the xml response */
	public void xResponsexmldateUpdate(String fileName, String Postingdate, String transType, String Companycode)
			throws Exception {
		try {
			NamedNodeMap attr = null;
			Node documentDate = null;
			File directory = new File(".//");
			String XMLLoc = directory.getCanonicalPath();
			String filePath = XMLLoc + "\\" + "resources\\testdata\\expected" + "\\" + "ConnectorResponse" + "\\"
					+ fileName;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			transType = transType.toUpperCase();
			switch (transType) {
			case "INVOICE":
				Node InvoiceResponse = doc.getElementsByTagName("InvoiceResponse").item(0);
				attr = InvoiceResponse.getAttributes();
				documentDate = attr.getNamedItem("documentDate");
				Node postingDate = attr.getNamedItem("postingDate");
				documentDate.setTextContent(Postingdate);
				postingDate.setTextContent(Postingdate);
				break;
			case "PURCHASEORDER":
				Node PurchaseOrderResponse = doc.getElementsByTagName("PurchaseOrderResponse").item(0);
				attr = PurchaseOrderResponse.getAttributes();
				documentDate = attr.getNamedItem("documentDate");
				documentDate.setTextContent(Postingdate);
				break;
			case "QUOTE":
				Node QuotationResponse = doc.getElementsByTagName("QuotationResponse").item(0);
				attr = QuotationResponse.getAttributes();
				documentDate = attr.getNamedItem("documentDate");
				documentDate.setTextContent(Postingdate);
				break;
			case "DISTRIBUTETAXRESPONSE":
				Node DistributeTaxResponse = doc.getElementsByTagName("DistributeTaxResponse").item(0);
				attr = DistributeTaxResponse.getAttributes();
				documentDate = attr.getNamedItem("documentDate");
				documentDate.setTextContent(Postingdate);
				break;
			}
			if (!Companycode.isEmpty()) {
				Node compcode = doc.getElementsByTagName("Seller").item(0);
				NodeList list = compcode.getChildNodes();

				for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					if ("Company".equals(node.getNodeName())) {
						node.setTextContent(Companycode);
					}
				}
			}
			// write the updated document to file or console
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
			System.out.println("DONE");
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
		// return FileName;
	}

	public static String xTotalExecutionTime(String StartTime, String EndTime) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = format.parse(StartTime);
		Date date2 = format.parse(EndTime);
		String strDate1 = format.format(date1);
		String strDate2 = format.format(date2);
		if (strDate2.split(":")[0].contains("00") && (!strDate1.split(":")[0].contains("00"))) {
			String endtime24 = strDate2.split(":")[0].replace("00", "24");
			String formatdatenew = endtime24 + ":" + strDate2.split(":")[1] + ":" + strDate2.split(":")[2];
			TestEndDate = format.parse(formatdatenew);
		} else {
			TestEndDate = format.parse(strDate2);
		}
		long difference = TestEndDate.getTime() - date1.getTime();
		long diffSeconds = difference / 1000 % 60;
		long diffMin = difference / (60 * 1000) % 60;
		long diffHours = difference / (60 * 60 * 1000) % 24;
		// long diffDays = diff / (24 * 60 * 60 * 1000);
		String TotalTime = diffHours + ":" + diffMin + ":" + diffSeconds;
		return (TotalTime);
	}

	public void xKillIEs() throws Exception {
		Wait(3000);
		File directory = new File(".//");
		try {
			Runtime.getRuntime().exec("wscript.exe " + directory.getCanonicalPath() + "\\KillIEs.vbs");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Wait(5000);
	}

	public void xKillExcel() throws Exception {
		Wait(3000);
		File directory = new File(".//");
		try {
			Runtime.getRuntime().exec("wscript.exe " + directory.getCanonicalPath() + "\\KillExcel.vbs");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Wait(5000);
	}

	public void xTestScriptUpdate() throws Exception {
		Wait(3000);
		File directory = new File(".//");
		try {
			Runtime.getRuntime().exec("wscript.exe " + directory.getCanonicalPath() + "\\TestScriptUpdate.vbs");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Wait(5000);
	}

	public boolean xFileExist(String FileNameWithPath) throws Exception {
		java.io.File myDir = new java.io.File(FileNameWithPath);
		if (myDir.exists()) {
			Print("file exist");
			return true;
		} else {
			Print("file does not exist");
			assertTrue(false);
			return false;
		}
	}

	public void xMakeFileCopy(String NewFileNameWithPath, String FileNameWithPath) throws Exception {
		java.io.File base = new java.io.File(FileNameWithPath);
		java.io.File newfile = new java.io.File(NewFileNameWithPath);
		if (xFileExist(FileNameWithPath)) {
			FileUtils.copyFile(base, newfile);
		} else {
			Print("file does not existcould not copy");
			assertTrue(false);
		}
		if (xFileExist(NewFileNameWithPath)) {
			Print("file copied sucessfully");
		}

	}

	public void xDeleteFile(String FileNameWithPath) throws Exception {
		java.io.File file = new java.io.File(FileNameWithPath);
		if (xFileExist(FileNameWithPath)) {
			FileUtils.cleanDirectory(file);
			Print("Files Deleted Successfully");
		} else {
			Print("files does not exist.Could not Delete");
			// assertTrue(false);
		}
	}

	public static void xScreenShot() throws AWTException {
		try {

			String NewFileNamePath, FileName = null;
			// java.awt.Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
			// Rectangle rectangle = new Rectangle(resolution);

			// Get the dir path
			File directory = new File(".//");

			// get current date time with Date() to create unique file name
			DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy__hh_mm_ssaa");
			// get current date time with Date()
			Date date = new Date();

			// To identify the system
			InetAddress ownIP = InetAddress.getLocalHost();
			FileName = Method + "___" + dateFormat.format(date) + "_" + ownIP.getHostAddress() + ".png";
			NewFileNamePath = directory.getCanonicalPath() + "\\ScreenShots\\" + FileName;

			// Capture the screen shot using driver
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(NewFileNamePath));

			// Capture the screen shot of the area of the screen defined by the
			// rectangle
			// Robot robot = new Robot();
			// BufferedImage bi = new BufferedImage();
			// BufferedImage bi = robot.createScreenCapture(new Rectangle(rectangle));
			// ImageIO.write(bi, "png", new File(NewFileNamePath));
			// NewFileNamePath = "<a href=" + NewFileNamePath + ">ScreenShot" + "</a>";
			NewFileNamePath = "<a href=" + NewFileNamePath + "> <img src=" + "cid:" + FileName
					+ " height='200' width='200'/> </a>";
			// Place the reference in TestNG web report
			Reporter.log(NewFileNamePath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void xErrorScreenShot(int i) {
		try {

			String NewFileNamePath;
			java.awt.Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle rectangle = new Rectangle(resolution);

			// Get the dir path
			File directory = new File(".//");
			// System.out.println(directory.getCanonicalPath());

			// get current date time with Date() to create unique file name
			DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy__hh_mm_ssaa");
			// get current date time with Date()
			Date date = new Date();
			// System.out.println(dateFormat.format(date));

			NewFileNamePath = directory.getCanonicalPath() + "\\ErrorScreenShots\\" + Method + i + "___"
					+ dateFormat.format(date) + ".png";
			System.out.println(NewFileNamePath);

			// Capture the screen shot of the area of the screen defined by the
			// rectangle
			Robot robot = new Robot();
			// BufferedImage bi = new BufferedImage();
			BufferedImage bi = robot.createScreenCapture(new Rectangle(rectangle));
			ImageIO.write(bi, "png", new File(NewFileNamePath));
			NewFileNamePath = "<a href=" + NewFileNamePath + ">ScreenShot" + "</a>";
			// Place the reference in TestNG web report
			Reporter.log(NewFileNamePath);

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void xUpdateTestDetails(String Status) throws Exception { File
	 * directory = new File(".//"); String Temp = Method + "_" + Status; if (Method
	 * != ""){ try { Runtime.getRuntime().exec( "wscript.exe " +
	 * directory.getCanonicalPath() + "\\UpdateTestDetails.vbs "+ Temp + " " +
	 * sMessages); Method = ""; sMessages = ""; } catch (Exception e) {
	 * e.printStackTrace(); } } Wait(5000); // Allow OS to kill the process }
	 */
	public static WritableCellFormat xFillCell(Colour colour) throws WriteException {
		WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 10);
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
		cellFormat.setBackground(colour);
		return cellFormat;
	}

	public WritableCellFormat xFormatCell(Pattern pattern) throws WriteException {
		WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 10);
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
		cellFormat.setBackground(Colour.BLACK, pattern);
		return cellFormat;
	}

	public static String getRandomString(int length) {
		String result = new BigInteger(Long.SIZE * length, random).toString(32);
		return result.substring(0, length);
	}

	public static String xGetCustomizedDate(int Day_argument, int Month_argument, int Year_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, Day_argument);

		cal.add(Calendar.MONTH, Month_argument);

		cal.add(Calendar.YEAR, Year_argument);

		String CustomizedDate = dateFormat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static String xGetCustomizedDate1(int Day_argument, int Month_argument, int Year_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, Day_argument);

		cal.add(Calendar.MONTH, Month_argument);

		cal.add(Calendar.YEAR, Year_argument);

		String CustomizedDate = dateFormat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static String xGetCustomizedDate(int Month_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, 0);

		cal.add(Calendar.MONTH, Month_argument);

		cal.add(Calendar.YEAR, 0);

		String CustomizedDate = dateFormat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static String xGetCustomizedMon(int Month_argument, int Year_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMMMMM yyyy");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MONTH, Month_argument);

		cal.add(Calendar.YEAR, Year_argument);

		String CustomizedDate = dateFormat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static String xGetCustomizeddate(int Month_argument, int Year_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMMMMM - yyyy");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MONTH, Month_argument);

		cal.add(Calendar.YEAR, Year_argument);

		String CustomizedDate = dateFormat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static String xGetCustomizedMonth(int Month_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMMMMM yyyy");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MONTH, Month_argument);

		String CustomizedDate = dateFormat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static String xGetCustomizedMon(int Month_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMMMMM");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MONTH, Month_argument);

		String CustomizedDate = dateFormat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static String xGetCustomizedYear(int Year_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.YEAR, Year_argument);

		String CustomizedDate = dateFormat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static String xGetCustomizedPreviousMon(int Month_argument, int Year_argument) throws Exception {

		SimpleDateFormat simpleformat = new SimpleDateFormat("MM/dd/yyyy");

		Calendar cal = Calendar.getInstance();
		// Date date=new Date();
		String strDate = simpleformat.format(new Date());

		// System.out.println(date);
		cal.add(Calendar.DATE, -Integer.parseInt(strDate.split("/")[1]) - 1);
		cal.add(Calendar.MONTH, Month_argument);
		cal.add(Calendar.YEAR, Year_argument);

		String CustomizedDate = simpleformat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static String xGetCustomizedMonNumber(int Month_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("M");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MONTH, Month_argument);

		String CustomizedDate = dateFormat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static String xLabelRND(String[] DataValue) throws Exception {

		String VertexRNDName = "";
		if (DataValue.length > 1) {
			if (DataValue[1].equalsIgnoreCase("RND")) {
				String RandomValue = RND();
				VertexRNDName = DataValue.length == 3 ? DataValue[0] + RandomValue + DataValue[2]
						: DataValue[0] + RandomValue;
			} else if (DataValue[1].contains("RND")) {
				String RandomValue = RND();
				DataValue[1] = DataValue[1].replaceAll("RND", "");
				VertexRNDName = DataValue.length == 2 ? DataValue[0] + RandomValue + DataValue[1]
						: DataValue[0] + RandomValue;
			}
		}

		else {
			VertexRNDName = DataValue[0];
		}
		return (VertexRNDName);
	}

	public static String xLabelRND1(String[] DataValue) throws Exception {

		String VertexRNDName = "";
		if (DataValue.length > 1) {
			if (DataValue[1].equalsIgnoreCase("RND")) {
				String RandomValue = RND1();
				VertexRNDName = DataValue.length == 3 ? DataValue[0] + RandomValue + DataValue[2]
						: DataValue[0] + RandomValue;
			} else if (DataValue[1].contains("RND")) {
				String RandomValue = RND1();
				DataValue[1] = DataValue[1].replaceAll("RND", "");
				VertexRNDName = DataValue.length == 2 ? DataValue[0] + RandomValue + DataValue[1]
						: DataValue[0] + RandomValue;
			}
		}

		else {
			VertexRNDName = DataValue[0];
		}
		return (VertexRNDName);
	}

	public String xGetPDFLabel(String Filepath, String Text) throws Exception {

		String PDFText = "";
		PdfReader reader = new PdfReader(Filepath);
		System.out.println("This PDF has " + reader.getNumberOfPages() + " pages.");
		for (int PageCnt = 1; PageCnt <= reader.getNumberOfPages(); PageCnt++) {
			String page = PdfTextExtractor.getTextFromPage(reader, PageCnt);
			java.util.List<String> Line = Arrays.asList(page.split("\n"));
			for (int i = 0; i < Line.size(); i++) {
				String sVal = Line.get(i);
				if (sVal.matches(Text)) {
					PDFText = Line.get(i);
				}
			}
		}
		return (PDFText);
	}

	public String xGetCSVCellValue(String Filepath, String Text) throws Exception {
		String status = "";
		try {
			CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);

			}
			// Nested loop to display results
			end: for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y < array.get(x).size(); y++) {
					String Cell_Name = array.get(x).get(y);
					System.out.println(Cell_Name);
					Cell_Header = array.get(x).get(y);
					if (Cell_Name.equalsIgnoreCase(Text)) {
						// System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(Text)) == true ? status
								: status + "Fail, Column Value doesnot match;";
						// Cell_Value=array.get(x+1).get(y);
						System.out.println(array.size());
						try {
							if (array.size() > 1 && array.get(x + 1).get(y) != null) {
								Cell_Value = array.get(x + 1).get(y);
								// System.out.println("cell value is "+Cell_Value);
							}
						} catch (Exception e) {
							System.out.println(e);
						}
						break end;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return status;

	}

	public String xGetCSVCellData(String Filepath, String Text1) throws Exception {
		try {
			CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			// Nested loop to display results
			end: for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y < array.get(x).size(); y++) {
					// String Cell_Name=array.get(x).get(y);
					// System.out.println(Cell_Name);
					Cell_Header = array.get(x).get(y);
					if (Cell_Header.equalsIgnoreCase(Text1)) {
						Cell_Value = array.get(x).get(y + 2);
						break end;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return Cell_Value;

	}

	public String xGetCSVCell(String Filepath, String Text, String Taxpayer, String returnname, String filingfreqvalue)
			throws Exception {
		String status = "";
		try {
			// CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			// String content = Joiner.on(",").join(array);
			// Nested loop to display results
			// end:
			for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y < array.get(x).size(); y++) {
					String Cell_Name = array.get(x).get(y);
					System.out.println(Cell_Name);
					Cell_Header = array.get(x).get(y);
					if (Cell_Name.equalsIgnoreCase(Text)) {
						System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(Text)) == true ? status
								: status + "Fail, Column Value doesnot match;";
						for (int c = 1; c < array.size(); c++) {
							Cell_Value = array.get(x + c).get(y);
							System.out.println("cell value is " + Cell_Value);
							if (Cell_Value.equalsIgnoreCase(Taxpayer)) {
								returnvalue = array.get(x + c).get(y + 3);
								Filingfreq = array.get(x + c).get(y + 7);
								// if(returnvalue.equalsIgnoreCase(returnname)){
								// System.out.println("returnname="+returnname);
								// break;
								// }
								// if(Filingfreq.equalsIgnoreCase(filingfreqvalue))
								// {
								// System.out.println("filingfreqvalue="+filingfreqvalue);
								// break;
								// }
								// break taxpayersearch;
								// break end;
							}
							if (Cell_Value.equalsIgnoreCase(Taxpayer) && (returnvalue.equalsIgnoreCase(returnname))
									&& (Filingfreq.equalsIgnoreCase(filingfreqvalue))) {
								System.out.println("filingfreqvalue=" + filingfreqvalue);
								break;
							}

						}
					}
				}
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}

	@SuppressWarnings("unused")
	public String xGetCSVCellVal(String Filepath, String Clientname, String Taxpayer, String Taxpayerstatus)
			throws Exception {
		String status = "";
		try {
			// CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			Joiner.on(",").join(array);
			// Nested loop to display results
			END: for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y < array.get(x).size(); y++) {
					array.get(x).get(y);
					Cell_Header = array.get(x).get(y);

					if (Cell_Header.equalsIgnoreCase(Clientname)) {
						status = (Cell_Header.equalsIgnoreCase(Clientname)) == true ? status
								: status + "Fail, Client name not match;";
						Cell_Value = array.get(x).get(y + 1);
						if (Cell_Value.equalsIgnoreCase(Taxpayerstatus)) {
							status = (Cell_Value.equalsIgnoreCase(Taxpayerstatus)) == true ? status
									: status + "Fail, Taxpayerstatus not match;";
						}
						Cell_Value = array.get(x).get(y + 2);
						if (Cell_Value.equalsIgnoreCase(Taxpayer)) {
							status = (Cell_Value.equalsIgnoreCase(Taxpayer)) == true ? status
									: status + "Fail, Taxpayer not match;";
							System.out.println(Clientname + " and " + Taxpayerstatus + " and " + Taxpayer);
						}
					}

				}
			}

		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}

	public String xGetCSVCelVal(String Filepath, String Date, String uniqueusers) throws Exception {
		String status = "";
		int uniqueuser;
		try {
			// CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			// String content = Joiner.on(",").join(array);
			// Nested loop to display results
			// END:
			for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y < array.get(x).size(); y++) {
					// String Cell_Name=array.get(x).get(y);
					// Cell_Header=array.get(x).get(y);
					Cell_Value = array.get(x + 1).get(y);
					System.out.println(Cell_Value);
					String[] Value = Cell_Value.split(" ");
					Value[0].equalsIgnoreCase(xGetDate("MM/dd/yyyy"));
					System.out.println(Value[0]);
					Cell_Value = array.get(x + 1).get(y + 2);
					uniqueuser = Integer.parseInt(Cell_Value);
					if (uniqueuser > 0) {

					} else {
						status = "Value not matched";
					}

				}
			}

		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}

	@SuppressWarnings("unused")
	public String xGetCSVCellVAl(String Filepath, String Clientname, String RateFiletype, String JurisdictionName,
			String Count) throws Exception {
		String status = "";
		try {
			// CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			Joiner.on(",").join(array);
			// Nested loop to display results
			for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y < array.get(x).size(); y++) {
					array.get(x).get(y);
					Cell_Header = array.get(x).get(y);
					if (Cell_Header.equalsIgnoreCase(Clientname)) {
						status = (Cell_Header.equalsIgnoreCase(Clientname)) == true ? status
								: status + "Fail, Client name not match;";
						Cell_Value = array.get(x).get(y + 2);
						if (Cell_Value.equalsIgnoreCase(RateFiletype)) {
							status = (Cell_Value.equalsIgnoreCase(RateFiletype)) == true ? status
									: status + "Fail, RateFiletype not match;";
						}
						Cell_Value = array.get(x).get(y + 4);
						if (Cell_Value.equalsIgnoreCase(JurisdictionName)) {
							status = (Cell_Value.equalsIgnoreCase(JurisdictionName)) == true ? status
									: status + "Fail, JurisdictionName not match;";
						}
						Cell_Value = array.get(x).get(y + 7);
						if (Cell_Value.equalsIgnoreCase(Count)) {
							status = (Cell_Value.equalsIgnoreCase(Count)) == true ? status
									: status + "Fail, Count not match;";
							System.out.println(
									Clientname + " and " + RateFiletype + " and " + JurisdictionName + "and" + Count);
						}
					}

				}
			}

		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}
	// To verify the transaction metric data

	@SuppressWarnings("unused")
	public String xGetCSVValue(String Filepath, String Clientname, String Compname, String Compstatus,
			String Subscription, String Dnsname, String Msgtype, String Transcount) throws Exception {
		String status = "";
		try {
			// @SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			Joiner.on(",").join(array);
			// Nested loop to display results
			end: for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y < array.get(x).size(); y++) {
					array.get(x).get(y);
					Cell_Header = array.get(x).get(y);
					if (Cell_Header.equalsIgnoreCase(Clientname)) {
						status = (Cell_Header.equalsIgnoreCase(Clientname)) == true ? status
								: status + "Fail, Column Value doesnot match;";
						Cell_Value = array.get(x).get(y + 1);
						if (Cell_Value.equalsIgnoreCase(Compname)) {
							Compstat = array.get(x).get(y + 2);
							Subscription = array.get(x).get(y + 3);
							DNSname = array.get(x).get(y + 5);
							msgtype = array.get(x).get(y + 6);
							transcount = array.get(x).get(y + 7);
							System.out.println(transcount + " and " + msgtype);
						}
						if (Cell_Value.equalsIgnoreCase(Compname) && (Compstat.equalsIgnoreCase(Compstatus))
								&& (DNSname.equalsIgnoreCase(Dnsname)) && (Subscription.equalsIgnoreCase(Subscription))
								&& (transcount.equalsIgnoreCase(Transcount)) && (msgtype.equalsIgnoreCase(Msgtype))) {
							break;
						}
					}
					break end;
				}
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}

	// To verify the transaction metric data from Parameter value.
	@SuppressWarnings("unused")
	public String xGetCSVValueForTrxMetric(String Filepath, String Clientname, String Compname, String Compstatus,
			String Subscription, String Dnsname, String Msgtype, String Transcount) throws Exception {
		String status = "";
		try {

			// @SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			Joiner.on(",").join(array);
			// Nested loop to display results
			end: for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y < array.get(x).size(); y++) {
					array.get(x).get(y);
					Cell_Header = array.get(x).get(y);

					if (Cell_Header.equalsIgnoreCase(Clientname)) {

						status = (Cell_Header.equalsIgnoreCase(Clientname)) == true ? status
								: status + "Fail, Column Value doesnot match;";
						Cell_Value = array.get(x).get(y + 2);
						if (Cell_Value.equalsIgnoreCase(Compname)) {
							Compstat = array.get(x).get(y + 1);
							Subscription = array.get(x).get(y + 5);
							DNSname = array.get(x).get(y + 7);
							msgtype = array.get(x).get(y + 8);
							transcount = array.get(x).get(y + 9);
						}

						if (Cell_Value.equalsIgnoreCase(Compname) && (Compstat.equalsIgnoreCase(Compstatus))
								&& (DNSname.equalsIgnoreCase(Dnsname)) && (Subscription.equalsIgnoreCase(Subscription))
								&& (msgtype.equalsIgnoreCase(Msgtype))) {
							assertTrue((transcount).equalsIgnoreCase(Transcount));
							break end;
						}

					}

				}
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}

	// To verify the Returns generation metric data

	@SuppressWarnings("unused")
	public String xGetCSVValue(String Filepath, String ClientSt, String Compname, String Clientname, String Taxpsts,
			String rcount) throws Exception {
		String status = "";
		try {
			String dclientst = null, dtaxpsts = null, srcount = null;
			// CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			// Nested loop to display results
			end: for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y <= array.get(x).size(); y++) {
					array.get(x).get(y);
					Cell_Header = array.get(x).get(y + 1);
					if (Cell_Header.equalsIgnoreCase(Clientname)) {
						status = (Cell_Header.equalsIgnoreCase(Clientname)) == true ? status
								: status + "Fail, Column Value doesnot match;";
						Cell_Value = array.get(x).get(y + 3);
						if (Cell_Value.equalsIgnoreCase(Compname)) {
							dclientst = array.get(x).get(y + 2);
							dtaxpsts = array.get(x).get(y + 4);
							srcount = array.get(x).get(y + 5);

						}
						if (Cell_Value.equalsIgnoreCase(Compname) && (dclientst.equalsIgnoreCase(ClientSt))
								&& (dtaxpsts.equalsIgnoreCase(Taxpsts)) && (srcount.equalsIgnoreCase(rcount))) {
							break;
						} else {
							status = "values are not matched";
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}
	// To verify the taxbility category metric data

	@SuppressWarnings("unused")
	public String xGetCSVValue(String Filepath, String taxhir, String totalcusmapp, String Clientname, String testccus,
			String Liveccus, String vertexprov) throws Exception {
		String status = "";
		try {
			String totalcusm = null, tcount = null, livecount = null, vertexprovided = null;
			@SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			Joiner.on(",").join(array);
			// Nested loop to display results
			for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y <= array.get(x).size(); y++) {
					// String Cell_Name=array.get(x).get(y);
					Cell_Header = array.get(x).get(y);
					if (Cell_Header.equalsIgnoreCase(taxhir)) {
						status = (Cell_Header.equalsIgnoreCase(taxhir)) == true ? status
								: status + "Fail, Column Value doesnot match;";
						totalcusm = array.get(x).get(y + 1);
						tcount = array.get(x).get(y + 2);
						livecount = array.get(x).get(y + 3);
						vertexprovided = array.get(x).get(y + 4);
						if ((totalcusm.equalsIgnoreCase(totalcusmapp)) && (tcount.equalsIgnoreCase(testccus))
								&& (vertexprovided.equalsIgnoreCase(vertexprov))
								&& (livecount.equalsIgnoreCase(Liveccus))) {
							break;
						} else {
							status = "values are not matched";
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}
	// To verify the configure metric data

	@SuppressWarnings("unused")
	public String xGetCSVValue(String Filepath, String subname, String taxpayerna, String Statusinfo, String Clientname,
			String StateCountinfo, String CountryCountinfo, String ReturnCountinfo, String prodcount,
			String prodmapcount, String custcount, String certicount) throws Exception {
		String status = "";
		try {
			String subscrbname = null, taxname = null, Stusinfo = null, StatCountinfo = null, CntryCountinfo = null,
					RetnCountinfo = null, productscount = null, prodmappount = null, custoount = null,
					certifcount = null;
			CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			// String content = Joiner.on(",").join(array);
			// Nested loop to display results
			for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y <= array.get(x).size(); y++) {
					// String Cell_Name=array.get(x).get(y);
					Cell_Header = array.get(x).get(y);
					if (Cell_Header.equalsIgnoreCase(Clientname)) {
						status = (Cell_Header.equalsIgnoreCase(Clientname)) == true ? status
								: status + "Fail, Column Value doesnot match;";
						subscrbname = array.get(x).get(y + 1);
						taxname = array.get(x).get(y + 2);
						Stusinfo = array.get(x).get(y + 3);
						StatCountinfo = array.get(x).get(y + 5);
						CntryCountinfo = array.get(x).get(y + 6);
						RetnCountinfo = array.get(x).get(y + 7);
						productscount = array.get(x).get(y + 8);
						prodmappount = array.get(x).get(y + 9);
						custoount = array.get(x).get(y + 10);
						certifcount = array.get(x).get(y + 11);
						if ((subscrbname.equalsIgnoreCase(subname)) && (taxname.equalsIgnoreCase(taxpayerna))
								&& (Stusinfo.equalsIgnoreCase(Statusinfo))
								&& (StatCountinfo.equalsIgnoreCase(StateCountinfo))
								&& (CntryCountinfo.equalsIgnoreCase(CountryCountinfo))
								&& (RetnCountinfo.equalsIgnoreCase(ReturnCountinfo))
								&& (productscount.equalsIgnoreCase(prodcount))
								&& (prodmappount.equalsIgnoreCase(prodmapcount))
								&& (custoount.equalsIgnoreCase(custcount))
								&& (certifcount.equalsIgnoreCase(certicount))) {
							break;
						} else {
							status = "values are not matched";
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}
	// To verify the return filling frequency data

	@SuppressWarnings("unused")
	public String xGetCSVValue(String Filepath, String sts, String compname, String Clientname, String Subscr,
			String reg, String month, String bimonth, String quarter, String Semianu, String annually,
			String fistmonthsofqrtr, String occa, String occaquarter, String occasemanuly, String occaanuly)
			throws Exception {
		String status = "";
		try {
			String Status = null, TaxpayerEntity = null, Subscription = null, Registartions = null, Monthly = null,
					BiMonthly = null, Quarterly = null, SemiAnnually = null, Annually = null,
					FirstTwoMonthsOfQuarter = null, Occasional = null, OccasionalQuarterly = null,
					OccasionalSemiAnnually = null, OccasionalAnnually = null;
			@SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			// String content = Joiner.on(",").join(array);
			// Nested loop to display results
			for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y <= array.get(x).size(); y++) {
					// String Cell_Name=array.get(x).get(y);
					Cell_Header = array.get(x).get(y);
					if (Cell_Header.equalsIgnoreCase(Clientname)) {
						status = (Cell_Header.equalsIgnoreCase(Clientname)) == true ? status
								: status + "Fail, Column Value doesnot match;";
						Status = array.get(x).get(y + 1);
						TaxpayerEntity = array.get(x).get(y + 2);
						Subscription = array.get(x).get(y + 3);
						Registartions = array.get(x).get(y + 4);
						Monthly = array.get(x).get(y + 5);
						BiMonthly = array.get(x).get(y + 6);
						Quarterly = array.get(x).get(y + 7);
						SemiAnnually = array.get(x).get(y + 8);
						Annually = array.get(x).get(y + 9);
						FirstTwoMonthsOfQuarter = array.get(x).get(y + 10);
						Occasional = array.get(x).get(y + 11);
						OccasionalQuarterly = array.get(x).get(y + 12);
						OccasionalSemiAnnually = array.get(x).get(y + 13);
						OccasionalAnnually = array.get(x).get(y + 14);
						if ((Status.equalsIgnoreCase(sts)) && (TaxpayerEntity.equalsIgnoreCase(compname))
								&& (Subscription.equalsIgnoreCase(Subscr)) && (Registartions.equalsIgnoreCase(reg))
								&& (Monthly.equalsIgnoreCase(month)) && (BiMonthly.equalsIgnoreCase(bimonth))
								&& (Quarterly.equalsIgnoreCase(quarter)) && (SemiAnnually.equalsIgnoreCase(Semianu))
								&& (Annually.equalsIgnoreCase(annually))
								&& (FirstTwoMonthsOfQuarter.equalsIgnoreCase(fistmonthsofqrtr))
								&& (Occasional.equalsIgnoreCase(occa))
								&& (OccasionalQuarterly.equalsIgnoreCase((occaquarter))
										&& (OccasionalSemiAnnually.equalsIgnoreCase(occasemanuly))
										&& (OccasionalAnnually.equalsIgnoreCase(occaanuly)))) {
							break;
						} else {
							status = "values are not matched";
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}

	public String xExtractZiReadContents(String ZipFilepath, String UnZipFilepath, String FileName, String Text)
			throws Exception {

		// Extract the zip file
		FileInputStream fis = null;
		ZipInputStream zis = null;
		ZipEntry zEntry = null;
		String ExtractFileName = "";
		String FiletobeVerified = "";
		try {
			fis = new FileInputStream(ZipFilepath);
			zis = new ZipInputStream(new BufferedInputStream(fis));
			while ((zEntry = zis.getNextEntry()) != null) {
				try {
					byte[] tmp = new byte[4 * 1024];
					FileOutputStream fos = null;
					FiletobeVerified = zEntry.getName();
					ExtractFileName = UnZipFilepath + zEntry.getName();
					System.out.println("Extracting file to " + ExtractFileName);
					fos = new FileOutputStream(ExtractFileName);
					int size = 0;
					while ((size = zis.read(tmp)) != -1) {
						fos.write(tmp, 0, size);
					}
					fos.flush();
					fos.close();
				} catch (Exception ex) {

				}
				if (FiletobeVerified.split("\\.")[0].equalsIgnoreCase(FileName))
					System.out.println("Yes.");
				else
					System.out.println("NO.");
				// Read Extracted File and their Content
				BufferedReader Breader = new BufferedReader(new FileReader(ExtractFileName));
				try {
					StringBuilder Strbuilder = new StringBuilder();
					String line = Breader.readLine();

					while (line != null) {
						Strbuilder.append(line);
						Strbuilder.append("\n");
						line = Breader.readLine();
					}
					String fileContent = Strbuilder.toString();

					boolean hasState = fileContent.toUpperCase().contains(Text);
					if (hasState) {
						System.out.println("Yes. Text File Contains " + Text);
					} else {
						System.out.println("NO. Text File doesnt Contain " + Text);
					}
				} finally {
					Breader.close();
				}
			}

			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Text;
	}

	public String xExtractZiReadContents(String ZipFilepath, String UnZipFilepath) throws Exception {

		// Extract the zip file
		FileInputStream fis = null;
		ZipInputStream zis = null;
		ZipEntry zEntry = null;
		String ExtractFileName = "";
		try {
			fis = new FileInputStream(ZipFilepath);
			zis = new ZipInputStream(new BufferedInputStream(fis));
			while ((zEntry = zis.getNextEntry()) != null) {
				try {
					byte[] tmp = new byte[4 * 1024];
					FileOutputStream fos = null;
					zEntry.getName();
					ExtractFileName = UnZipFilepath + zEntry.getName();
					fos = new FileOutputStream(ExtractFileName);
					int size = 0;
					while ((size = zis.read(tmp)) != -1) {
						fos.write(tmp, 0, size);
					}
					fos.flush();
					fos.close();
				} catch (Exception ex) {

				}

			}

			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		actualfile = ExtractFileName;
		return ExtractFileName;
	}
	
	public String xExtractZiReadContents(String ZipFilepath) throws Exception {

		// Extract the zip file
		FileInputStream fis = null;
		ZipInputStream zis = null;
		ZipEntry zEntry = null;
		String ExtractFileName = "";
		try {
			fis = new FileInputStream(ZipFilepath);
			zis = new ZipInputStream(new BufferedInputStream(fis));
			while ((zEntry = zis.getNextEntry()) != null) {
				try {
					byte[] tmp = new byte[4 * 1024];
					FileOutputStream fos = null;
					zEntry.getName();
					ExtractFileName = zEntry.getName();
					fos = new FileOutputStream(ExtractFileName);
					int size = 0;
					while ((size = zis.read(tmp)) != -1) {
						fos.write(tmp, 0, size);
					}
					fos.flush();
					fos.close();
				} catch (Exception ex) {

				}

			}

			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		actualfile = ExtractFileName;

		return ExtractFileName;
	}

	public String xGetXLSXCellValue(String Filepath, String Text) throws Exception {

		FileInputStream fis = new FileInputStream(Filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int Num_rows = sheet.getLastRowNum();
		System.out.println("Number of rows in the sheet :" + Num_rows);
		XSSFRow HeaderRow = sheet.getRow(0); // 3rd row is my header row
		int columnsCount = HeaderRow.getLastCellNum();
		System.out.println("Number of columns in the sheet :" + columnsCount);
		end: for (int i = 0; i <= Num_rows; i++) {
			XSSFRow Rows = sheet.getRow(i);
			for (int col = 0; col < columnsCount; col++) {
				XSSFCell atColumn = HeaderRow.getCell(col);
				String Column_Value = atColumn.getStringCellValue();
				XSSFRow HeaderRow1 = sheet.getRow(1);
				XSSFCell atColumn1 = HeaderRow1.getCell(col);
				// String Column_Value=atColumn.getStringCellValue();

				if (Column_Value.equalsIgnoreCase(Text)) {
					switch (Rows.getCell(col).getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						int Numeric_Value = (int) Rows.getCell(col).getNumericCellValue();
						System.out.println(Numeric_Value);

						break;
					case Cell.CELL_TYPE_STRING:
						String Str_Value = Rows.getCell(col).getStringCellValue();
						System.out.println(Str_Value);
						if (Column_Value.equalsIgnoreCase(Text)) {

							double billingamount = atColumn1.getNumericCellValue();

							System.out.println(billingamount);

							BillAmount = Double.toString(billingamount);

							System.out.println(BillAmount);
						}
						break;
					default:
						Date date_Value = Rows.getCell(col).getDateCellValue();
						System.out.println(date_Value);
						break;
					}
					break end;
				}
			}
		}
		fis.close();
		return (BillAmount);
	}

	public String xGetXLSXCellValue2(String Filepath, String Text) throws Exception {

		FileInputStream fis = new FileInputStream(Filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int Num_rows = sheet.getLastRowNum();
		System.out.println("Number of rows in the sheet :" + Num_rows);
		XSSFRow HeaderRow = sheet.getRow(0); // 3rd row is my header row
		int columnsCount = HeaderRow.getLastCellNum();
		System.out.println("Number of columns in the sheet :" + columnsCount);
		end: for (int i = 0; i <= Num_rows; i++) {
			XSSFRow Rows = sheet.getRow(i);
			for (int col = 0; col < columnsCount; col++) {
				XSSFCell atColumn = HeaderRow.getCell(col);
				String Column_Value = atColumn.getStringCellValue();
				XSSFRow HeaderRow1 = sheet.getRow(1);
				XSSFCell atColumn1 = HeaderRow1.getCell(col);
				// String Column_Value=atColumn.getStringCellValue();

				if (Column_Value.equalsIgnoreCase(Text)) {
					switch (Rows.getCell(col).getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						int Numeric_Value = (int) Rows.getCell(col).getNumericCellValue();
						System.out.println(Numeric_Value);

						break;
					case Cell.CELL_TYPE_STRING:
						String Str_Value = Rows.getCell(col).getStringCellValue();
						System.out.println(Str_Value);
						if (Column_Value.equalsIgnoreCase(Text)) {

							String billingamount = atColumn1.getStringCellValue();

							System.out.println(billingamount);

							Cell_Value = billingamount;

							System.out.println(Cell_Value);
						}
						break;
					default:
						Date date_Value = Rows.getCell(col).getDateCellValue();
						System.out.println(date_Value);
						break;
					}
					break end;
				}
			}
		}
		fis.close();
		return (Cell_Value);
	}

	public void xGetXLSXHeaderValue(String Filepath, String Text) throws Exception {

		FileInputStream fis = new FileInputStream(Filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int Num_rows = sheet.getLastRowNum();
		XSSFRow HeaderRow = sheet.getRow(0); // 3rd row is my header row
		int columnsCount = HeaderRow.getLastCellNum();
		end: for (int i = 0; i <= Num_rows; i++) {
			@SuppressWarnings("unused")
			XSSFRow Rows = sheet.getRow(i);
			for (int col = 0; col < columnsCount; col++) {
				XSSFCell atColumn = HeaderRow.getCell(col);
				String Column_Value = atColumn.getStringCellValue();
				XSSFRow HeaderRow1 = sheet.getRow(1);
				@SuppressWarnings("unused")
				XSSFCell atColumn1 = HeaderRow1.getCell(col);

				if (Column_Value.equalsIgnoreCase(Text)) {
					System.out.println("Header columns matched " + Column_Value);
					break end;
				}
			}
		}
		fis.close();
	}

	public String xGetXLSXCellValue1(String Filepath, String Text, String Sheet) throws Exception {

		FileInputStream fis = new FileInputStream(Filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(Sheet);
		int Num_rows = sheet.getLastRowNum();
		System.out.println("Number of rows in the sheet :" + Num_rows);
		XSSFRow HeaderRow = sheet.getRow(0); // 3rd row is my header row
		int columnsCount = HeaderRow.getLastCellNum();
		System.out.println("Number of columns in the sheet :" + columnsCount);
		end: for (int i = 0; i <= Num_rows; i++) {
			XSSFRow Rows = sheet.getRow(i);
			for (int col = 0; col < columnsCount; col++) {
				XSSFCell atColumn = HeaderRow.getCell(col);
				String Column_Value = atColumn.getStringCellValue();
				XSSFRow HeaderRow1 = sheet.getRow(1);
				XSSFCell atColumn1 = HeaderRow1.getCell(col);

				// String Column_Value=atColumn.getStringCellValue();

				if (Column_Value.equalsIgnoreCase(Text)) {
					switch (Rows.getCell(col).getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						int Numeric_Value = (int) Rows.getCell(col).getNumericCellValue();
						System.out.println(Numeric_Value);

						break;
					case Cell.CELL_TYPE_STRING:
						String Str_Value = Rows.getCell(col).getStringCellValue();
						System.out.println(Str_Value);
						if (!Column_Value.equalsIgnoreCase("TaxArea ID")) {

							String billingamount = atColumn1.getStringCellValue();

							System.out.println(billingamount);

							BillAmount = billingamount;

							System.out.println(BillAmount);
						} else {
							switch (atColumn1.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								double billingamount = atColumn1.getNumericCellValue();

								System.out.println(billingamount);

								BillAmount = Double.toString(billingamount);
								break;

							case Cell.CELL_TYPE_STRING:
								String billingamount1 = atColumn1.getStringCellValue();

								// System.out.println(billingamount);

								// BillAmount=Double.toString(billingamount);

								BillAmount = billingamount1;

								System.out.println(BillAmount);
							}

						}
						break;
					default:
						Date date_Value = Rows.getCell(col).getDateCellValue();
						System.out.println(date_Value);
						break;
					}
					break end;
				}
			}
		}
		fis.close();
		return (BillAmount);
	}

	public String xGetXLSXCellvalueHeaderBase(String Filepath, String CoulmnHeader, String ColumnValue)
			throws Exception {
		String status = "";
		FileInputStream fis = new FileInputStream(Filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int Num_rows = sheet.getLastRowNum();
		XSSFRow HeaderRow = sheet.getRow(0);
		int columnsCount = HeaderRow.getLastCellNum();
		end: for (int i = 0; i <= Num_rows; i++) {
			XSSFRow Rows = sheet.getRow(i);
			for (int col = 0; col < columnsCount; col++) {
				XSSFCell atColumn = HeaderRow.getCell(col);
				String Column_Value = atColumn.getStringCellValue();
				if (Column_Value.equalsIgnoreCase(CoulmnHeader)) {
					switch (Rows.getCell(col).getCellType()) {
					case Cell.CELL_TYPE_STRING:
						String Str_Value = Rows.getCell(col).getStringCellValue();
						if (Str_Value.equalsIgnoreCase(ColumnValue)) {
							status = status + "Fail, Values are not match " + Str_Value + " and " + ColumnValue + ";";
						}
						break;
					}
					break end;
				}
			}
		}
		fis.close();
		return (BillAmount);
	}

	public String xGetXLSXCellvalueClientBase(String Filepath, String ClientName, String TaxCategoryName,
			String SubScriptionn, String REnDate) throws Exception {
		String status = "";
		try {
			int Clinet_NameColNo = 0;
			int SubscriptionColNo = 0;
			int TaxaCategoryNamecolno = 0;
			int ReturnEndDatecolno = 0;
			FileInputStream fis = new FileInputStream(Filepath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow HeaderRow = sheet.getRow(0);
			int columnsCount = HeaderRow.getLastCellNum();
			int ROWS = sheet.getFirstRowNum();
			int LastRow = sheet.getLastRowNum();
			String Client_Name_Col, Get_Client_Names = null;
			for (int j = 0; j < columnsCount; j++) {
				Client_Name_Col = sheet.getRow(0).getCell(j).getStringCellValue();
				if (Client_Name_Col.equalsIgnoreCase("Client Name")) {
					System.out.println(Client_Name_Col);
					Clinet_NameColNo = j;
					System.out.println(Clinet_NameColNo);
				}
				if (!SubScriptionn.isEmpty()) {
					if (Client_Name_Col.equalsIgnoreCase("Subscription")) {
						System.out.println(Client_Name_Col);
						SubscriptionColNo = j;
						System.out.println(SubscriptionColNo);
					}
				}
				if (!TaxCategoryName.isEmpty()) {
					if (Client_Name_Col.equalsIgnoreCase("Taxability Category Name")) {
						System.out.println(Client_Name_Col);
						TaxaCategoryNamecolno = j;
						System.out.println(TaxaCategoryNamecolno);
					}
				}
				if (!REnDate.isEmpty()) {
					if (Client_Name_Col.equalsIgnoreCase("Return End Date")) {
						System.out.println(Client_Name_Col);
						ReturnEndDatecolno = j;
						System.out.println(ReturnEndDatecolno);
					}
				}
			}
			for (int i = ROWS; i < LastRow; i++) {
				Get_Client_Names = sheet.getRow(i + 1).getCell(Clinet_NameColNo).getStringCellValue();
				if (Get_Client_Names.equalsIgnoreCase(ClientName)) {
					if (TaxCategoryName.isEmpty()) {
						Sub_scription = sheet.getRow(i + 1).getCell(SubscriptionColNo).getStringCellValue();
						if (!Sub_scription.equalsIgnoreCase(SubScriptionn)) {
							status = status + "Subscription Not matched";
						}
						XSSFCell REDate = sheet.getRow(i + 1).getCell(ReturnEndDatecolno);
						String REndDate = REDate.toString();
						if (REndDate.isEmpty()) {
							status = status + "Return Form has no End Date";
						}
					} else {
						Taxability_Category_Name = sheet.getRow(i + 1).getCell(TaxaCategoryNamecolno)
								.getStringCellValue();
						if (!Taxability_Category_Name.equalsIgnoreCase(TaxCategoryName)) {
							status = status + "Category Not Matched";
						}
						System.out.println(Get_Client_Names + "\n" + Sub_scription + "\n" + Taxability_Category_Name);
					}
					break;
				}
			}
			if (!ClientName.equalsIgnoreCase(Get_Client_Names)) {
				status = status + "Client Name not Not found";
			}
			fis.close();
			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public void xr_xGetXLSXCellvalueClientBase(String Filepath, String ClientName, String TaxCategoryName,
			String SubScriptionn, String ReturnEnddate) throws Exception {
		int Clinet_NameColNo = 0;
		int SubscriptionColNo = 0;
		int TaxaCategoryNamecolno = 0;
		int ReturnEndDatecolno = 0;
		FileInputStream fis = new FileInputStream(Filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow HeaderRow = sheet.getRow(0);
		int columnsCount = HeaderRow.getLastCellNum();
		int ROWS = sheet.getFirstRowNum();
		int LastRow = sheet.getLastRowNum();
		String Client_Name_Col, Get_Client_Names = null;
		for (int j = 0; j < columnsCount; j++) {
			Client_Name_Col = sheet.getRow(0).getCell(j).getStringCellValue();
			if (Client_Name_Col.equalsIgnoreCase("Client Name")) {
				System.out.println(Client_Name_Col);
				Clinet_NameColNo = j;
				System.out.println(Clinet_NameColNo);
			}
			if (!SubScriptionn.isEmpty()) {
				if (Client_Name_Col.equalsIgnoreCase("Subscription")) {
					System.out.println(Client_Name_Col);
					SubscriptionColNo = j;
					System.out.println(SubscriptionColNo);
				}
			}
			if (!TaxCategoryName.isEmpty()) {
				if (Client_Name_Col.equalsIgnoreCase("Taxability Category Name")) {
					System.out.println(Client_Name_Col);
					TaxaCategoryNamecolno = j;
					System.out.println(TaxaCategoryNamecolno);
				}
			}
			if (!ReturnEnddate.isEmpty()) {
				if (Client_Name_Col.equalsIgnoreCase("Return End Date")) {
					System.out.println(Client_Name_Col);
					ReturnEndDatecolno = j;
					System.out.println(ReturnEndDatecolno);
				}
			}
		}
		for (int i = ROWS; i < LastRow; i++) {
			Get_Client_Names = sheet.getRow(i + 1).getCell(Clinet_NameColNo).getStringCellValue();
			if (Get_Client_Names.equalsIgnoreCase(ClientName)) {
				if (TaxCategoryName.isEmpty()) {
					Sub_scription = sheet.getRow(i + 1).getCell(SubscriptionColNo).getStringCellValue();
					if (!Sub_scription.equalsIgnoreCase(SubScriptionn)) {
					}
					XSSFCell REDate = sheet.getRow(i + 1).getCell(ReturnEndDatecolno);
					String REndDate = REDate.toString();
					if (REndDate.isEmpty()) {
					}
				} else {
					Taxability_Category_Name = sheet.getRow(i + 1).getCell(TaxaCategoryNamecolno).getStringCellValue();
					if (!Taxability_Category_Name.equalsIgnoreCase(TaxCategoryName)) {
					}
					System.out.println(Get_Client_Names + "\n" + Sub_scription + "\n" + Taxability_Category_Name);
				}
				break;
			}
		}
	}

	public static String xGetXLSCellvaluesClientBase(String Filepath, String ClientName, String Paymentmethod,
			String Startdate, String Terms, String address, String street, String city, String state, String zip,
			String country, String approved) throws Exception {
		String status = "";
		try {
			int clientrow = 0;
			String client = null;
			Workbook workbook = Workbook.getWorkbook(new File(Filepath));
			System.out.println(Filepath);
			Sheet sheet = workbook.getSheet(0);
			int rows = sheet.getRows();
			int columns = sheet.getColumns();
			for (int i = 1; i <= rows; i++) {
				for (int j = 1; j <= columns; j++) {
					client = sheet.getCell(j, i).getContents();
					if (client.equals(ClientName)) {
						clientrow = i;
					}
				}

			}
			for (int k = 1; k <= columns; k++) {
				String cheadervalue = sheet.getCell(k, 0).getContents();
				switch (cheadervalue) {

				case "Invoice Payment Method":
					String Paymenttype = sheet.getCell(k, clientrow).getContents();
					if (!Paymenttype.equalsIgnoreCase(Paymentmethod)) {
						status = "Invoice payment not matched";
					}
					break;
				case "Invoice Start date":
					String date = sheet.getCell(k, clientrow).getContents();
					if (!date.equalsIgnoreCase(Startdate)) {
						status = "Start Date not matched";
					}
					break;
				case "Terms of Service":
					String service = sheet.getCell(k, clientrow).getContents();
					if (!service.equalsIgnoreCase(service)) {
						status = "Terms of Service not matched";
					}
					break;
				case "Billing Address":
					String Baddress = sheet.getCell(k, clientrow).getContents();
					if (!Baddress.equalsIgnoreCase(address)) {
						status = "Billing Address not matched";
					}
					break;
				case "Billing Street":
					String Bstreet = sheet.getCell(k, clientrow).getContents();
					if (!Bstreet.equalsIgnoreCase(street)) {
						status = "Billing Street not matched";
					}
					break;
				case "Billing City":
					String Bcity = sheet.getCell(k, clientrow).getContents();
					if (!Bcity.equalsIgnoreCase(city)) {
						status = "Billing City not matched";
					}
					break;
				case "Billing State/Province":
					String Bstate = sheet.getCell(k, clientrow).getContents();
					if (!Bstate.equalsIgnoreCase(state)) {
						status = "Billing State not matched";
					}
					break;
				case "Billing Zip/Postal Code":
					String Bzip = sheet.getCell(k, clientrow).getContents();
					if (!Bzip.equalsIgnoreCase(zip)) {
						status = "Billing Zip/Postal Code not matched";
					}
					break;
				case "Billing Country":
					String Bcountry = sheet.getCell(k, clientrow).getContents();
					if (!Bcountry.equalsIgnoreCase(country)) {
						status = "Billing Country not matched";
					}
					break;
				case "Approved By":
					String approvedby = sheet.getCell(k, clientrow).getContents();
					if (!approvedby.equalsIgnoreCase(approved)) {
						status = "Approved by not matched";
					}
					break;
				}
			}

			if (!ClientName.equalsIgnoreCase(client)) {
				status = "Client Name not Not found in the Cloud Customer Billing invoice sheet";
			}

			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public String xGetCSVCellvaluesClientBase(String Filepath, String ClientName, String Paymentmethod,
			String Startdate, String Terms, String address, String street, String city, String state, String zip,
			String country, String approved) throws Exception {
		String status = "";
		try {
			@SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			int clientrow = 0;
			String client = null;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			// Nested loop to display results
			for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y < array.get(x).size(); y++) {
					client = array.get(x).get(y);
					if (client.equalsIgnoreCase(ClientName)) {
						clientrow = x;
						for (int z = 1; z < array.get(x).size(); z++) {
							String cheadervalue = array.get(0).get(z);
							switch (cheadervalue) {

							case "Invoice Payment Method":
								String Paymenttype = array.get(clientrow).get(z);
								if (!Paymenttype.equalsIgnoreCase(Paymentmethod)) {
									status = "Invoice payment not matched";
								}
								break;
							case "Invoice Start date":
								String date = array.get(clientrow).get(z);
								if (!date.equalsIgnoreCase(Startdate)) {
									status = "Start Date not matched";
								}
								break;
							case "Terms of Service":
								String service = array.get(clientrow).get(z);
								if (!service.equalsIgnoreCase(service)) {
									status = "Terms of Service not matched";
								}
								break;
							case "Billing Address":
								String Baddress = array.get(clientrow).get(z);
								if (!Baddress.equalsIgnoreCase(address)) {
									status = "Billing Address not matched";
								}
								break;
							case "Billing Street":
								String Bstreet = array.get(clientrow).get(z);
								if (!Bstreet.equalsIgnoreCase(street)) {
									status = "Billing Street not matched";
								}
								break;
							case "Billing City":
								String Bcity = array.get(clientrow).get(z);
								if (!Bcity.equalsIgnoreCase(city)) {
									status = "Billing City not matched";
								}
								break;
							case "Billing State/Province":
								String Bstate = array.get(clientrow).get(z);
								if (!Bstate.equalsIgnoreCase(state)) {
									status = "Billing State not matched";
								}
								break;
							case "Billing Zip/Postal Code":
								String Bzip = array.get(clientrow).get(z);
								if (!Bzip.equalsIgnoreCase(zip)) {
									status = "Billing Zip/Postal Code not matched";
								}
								break;
							case "Billing Country":
								String Bcountry = array.get(clientrow).get(z);
								if (!Bcountry.equalsIgnoreCase(country)) {
									status = "Billing Country not matched";
								}
								break;
							case "Approved By":
								String approvedby = array.get(clientrow).get(z);
								if (!approvedby.equalsIgnoreCase(approved)) {
									status = "Approved by not matched";
								}
								break;
							}
						}
						break;
					}
				}
			}
			if (status.isEmpty()) {
				status = "Pass";
			}

		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}

	public String xGetCustomizedXLSXCellValue(String Filepath, String Sheet, String State, String Categoty)
			throws Exception {

		FileInputStream fis = new FileInputStream(Filepath);
		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet(Sheet);
		int Num_rows = sheet.getLastRowNum();
		int temp = 0;
		String CategoryType = "";
		for (int i = 0; i < Num_rows; i++) {
			org.apache.poi.ss.usermodel.Row Row = sheet.getRow(i);
			int columnsCount = Row.getLastCellNum();
			for (int col = 0; col < columnsCount; col++) {
				org.apache.poi.ss.usermodel.Cell atColumn = Row.getCell(col);
				String Column_Value = (!(atColumn == null)) ? atColumn.toString().trim() : "";
				if (Column_Value.equalsIgnoreCase(State)) {
					temp = col;
					break;
				}
				if (col == temp && Row.getCell(0) != null
						&& Row.getCell(0).toString().trim().equalsIgnoreCase(Categoty)) {
					org.apache.poi.ss.usermodel.Row sample = sheet.getRow(i);
					System.out.println(Row.getCell(0).toString().trim() + "==>" + sheet.getRow(1).getCell(temp) + "==>"
							+ sample.getCell(temp));
					CategoryType = sample.getCell(temp).toString();
					System.out.println(CategoryType);
				}
			}
			fis.close();
		}
		return (CategoryType);
	}

	public String xGetCustomizedXLSXCellValue(String Filepath, String Sheet, String State, String Categoty,
			String SubCategory, String QCStatus) throws Exception {

		FileInputStream fis = new FileInputStream(Filepath);
		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet(Sheet);
		int Num_rows = sheet.getLastRowNum();
		int temp = 0;
		String CategoryType = "";
		String QCSts = "";
		for (int i = 0; i < Num_rows; i++) {
			org.apache.poi.ss.usermodel.Row Row = sheet.getRow(i);
			int columnsCount = Row.getLastCellNum();
			temp = 0;
			for (int col = 0; col < columnsCount; col++) {
				org.apache.poi.ss.usermodel.Cell atColumn = Row.getCell(col);
				String Column_Value = (!(atColumn == null)) ? atColumn.toString().trim() : "";
				if (!Column_Value.equalsIgnoreCase("CN")) {
					if (Column_Value.equalsIgnoreCase(State)) {
						temp = col;
						break;
					}
					if (col == temp && Row.getCell(0) != null
							&& Row.getCell(0).toString().trim().equalsIgnoreCase(Categoty)) {
						org.apache.poi.ss.usermodel.Row sample = sheet.getRow(i);
						System.out.println(Row.getCell(0).toString().trim() + "==>" + sheet.getRow(1).getCell(temp)
								+ "==>" + sample.getCell(temp));
						CategoryType = sample.getCell(temp).toString();
						System.out.println(CategoryType);
					}
					temp = 0;
					if (Column_Value.equalsIgnoreCase(QCStatus)) {
						temp = col;
						QCSts = "Yes";
						break;
					}

					if (col == temp && Row.getCell(0) != null
							&& Row.getCell(0).toString().trim().equalsIgnoreCase(SubCategory)) {
						org.apache.poi.ss.usermodel.Row sample = sheet.getRow(i);
						System.out.println(Row.getCell(0).toString().trim() + "==>" + sheet.getRow(1).getCell(temp)
								+ "==>" + sample.getCell(temp).toString().trim());
						// System.out.println(Row.getCell(0).toString().trim()+"==>"+sheet.getRow(2).getCell(1)+"==>"+sample.getCell(1).toString().trim());
						System.out.println(Row.getCell(0).toString().trim() + "==>" + Row.getCell(1).toString().trim()
								+ "==>" + sample.getCell(1).toString().trim());
						if (Row.getCell(1).toString().isEmpty()) {
							QCSts = "NO";
						}
						SubCategory = sample.getCell(temp).toString();
						System.out.println(SubCategory);
					}
				} else {
					if (Column_Value.equalsIgnoreCase(State)) {
						temp = col;
						break;
					}
					if (col == temp && Row.getCell(0) != null
							&& Row.getCell(0).toString().trim().equalsIgnoreCase(Categoty)) {
						org.apache.poi.ss.usermodel.Row sample = sheet.getRow(i);
						System.out.println(Row.getCell(0).toString().trim() + "==>" + sheet.getRow(1).getCell(temp)
								+ "==>" + sample.getCell(temp));
						CategoryType = sample.getCell(temp).toString();
						System.out.println(CategoryType);
					}

					if (col == temp && Row.getCell(0) != null
							&& Row.getCell(0).toString().trim().equalsIgnoreCase(SubCategory)) {
						org.apache.poi.ss.usermodel.Row sample = sheet.getRow(i);
						System.out.println(Row.getCell(0).toString().trim() + "==>" + sheet.getRow(1).getCell(temp)
								+ "==>" + sample.getCell(temp).toString().trim());
						SubCategory = sample.getCell(temp).toString();
						System.out.println(SubCategory);
						if (sample.getCell(temp).toString().equalsIgnoreCase(QCStatus))
							QCSts = "Yes";
						break;
					}
				}
			}
			fis.close();
		}
		return (QCSts);
	}

	public String XGetXLSValue(String Filepath, String Text) throws Exception {

		Workbook workbook = Workbook.getWorkbook(new File(Filepath));
		Sheet sheet = workbook.getSheet(0);
		int Rows = sheet.getRows();
		int Columns = sheet.getColumns();
		for (int i = 0; i < Rows; i++) {
			for (int j = 0; j < Columns; j++) {
				String C_Header = sheet.getCell(j, i).getContents();
				if (C_Header.equalsIgnoreCase(Text)) {
					Col_Row_Content = sheet.getCell(j, i + 1).getContents();
					System.out.println(Col_Row_Content);
				}
			}
		}
		return Text;
	}

	public static String xRandomTimeStampName(String MethodName) throws Exception {

		try {
			// Get the dir path
			File directory = new File(".//");

			// get current date time with Date() to create unique file name
			DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy__hh_mm_ssaa");

			// get current date time with Date()
			Date date = new Date();

			// To identify the system
			InetAddress ownIP = InetAddress.getLocalHost();
			if (MethodName.contains(":")) {
				MethodName = MethodName.replace(":", "-");
			}
			NewFileNamePath = directory.getCanonicalPath() + "\\ScreenShots\\" + MethodName + "_"
					+ dateFormat.format(date) + "_" + ownIP.getHostAddress() + ".png";
		} catch (Exception e) {
			e.getMessage();
		}
		return NewFileNamePath;
	}

	public String PDFConversion(String FilePath, String FileExt) throws Exception {

		PDDocument pd;
		BufferedWriter wr;
		try {
			File input = new File(FilePath); // The PDF file from where you would like to extract
			String[] OutPutFilePath = FilePath.split("\\.");
			File output = new File(OutPutFilePath[0] + FileExt); // The file where you are going to store the extracted
			// data in txt, csv,xls, doc etc..
			pd = PDDocument.load(input);
			System.out.println(pd.getNumberOfPages());
			PDFTextStripper stripper = new PDFTextStripper();
			wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
			stripper.writeText(pd, wr);
			if (pd != null) {
				pd.close();
			}
			wr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FileExt;
	}

	public String CleanupfilesinFolder() throws Exception {
		File directory = new File(".//resources//testdata//actual");
		// String
		// strDirectory=directory.getCanonicalPath()+"\\"+"resources\\testdata\\actual";
		try {
			/*
			 * for(File f: directory.listFiles()) { f.delete();}
			 */
			// FileUtils.cleanDirectory(directory);
			// Runtime.getRuntime().exec("wscript.exe
			// "+directory.getCanonicalPath()+"\\CleanupFiles.vbs " +strDirectory);
			if (directory.isDirectory()) {
				for (File Folders : directory.listFiles()) {
					if (!Folders.getName().contains(".zip")) {
						if (Folders.isFile()) {
							Folders.delete();
						} else {
							for (File subFolders : Folders.listFiles()) {
								subFolders.delete();
							}
							Folders.delete();
						}
					}
				}
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Pass";
	}

	public String CleanupinSystemDownFolder() throws Exception {
		String DownloadFilePath = System.getProperty("user.home") + "\\Downloads";
		File directory = new File(DownloadFilePath);
		try {
			for (File f : directory.listFiles()) {
				f.delete();
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Pass";
	}

	public String MoveLatestDownloadFileFromOnedirtoAnother(String Targetpath) throws Exception {
		String DownloadFilePath;
		if (System.getProperty("os.name").contains("Windows XP")) {
			DownloadFilePath = System.getProperty("user.home") + "\\My Documents" + "\\Downloads";
		} else {
			DownloadFilePath = System.getProperty("user.home") + "\\Downloads";
		}

		File dir = new File(DownloadFilePath);
		File[] files = dir.listFiles();
		Arrays.sort(files);
		Arrays.sort(files, new Comparator<Object>() {
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
		System.out.println("Download Path :" + DownloadPath);
		String[] arrFileName = LatestDownloadedFile.split("\\\\");
		String FileName = arrFileName[arrFileName.length - 1];
		String[] arrFileNames = FileName.split("]");
		String arrFileReName = arrFileNames[0].replaceAll(" \\(.*\\)", "");
		System.out.println("Downloaded FileName is " + arrFileReName);
		Path RSource = Paths.get(DownloadPath);
		Path RTarget = Paths.get(Targetpath + "\\" + arrFileReName);
		Files.copy(RSource, RTarget, REPLACE_EXISTING);
		return Targetpath;
	}

	public String MoveLatestDownloadFileFromOnedirtoAnother(String SourcePath, String Targetpath) throws Exception {
		try {

			File dir = new File(SourcePath);
			File[] files = dir.listFiles();
			Arrays.sort(files);
			Arrays.sort(files, new Comparator<Object>() {
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
			System.out.println("Download Path :" + DownloadPath);
			String[] arrFileName = LatestDownloadedFile.split("\\\\");
			String FileName = arrFileName[arrFileName.length - 1];
			String[] arrFileNames = FileName.split("]");
			String arrFileReName = arrFileNames[0].replaceAll(" \\(.*\\)", "");
			System.out.println("Downloaded FileName is " + arrFileReName);
			Path RSource = Paths.get(DownloadPath);
			Path RTarget = Paths.get(Targetpath + "\\" + arrFileReName);
			Files.move(RSource, RTarget, REPLACE_EXISTING);

		} catch (Exception e) {

			e.getMessage();
		}
		return SourcePath;

	}

	public String PDFComparison(String ExpectedFile, String ActualFile) throws Exception {
		String status = "";
		try {
			PdfReader Dreader = new PdfReader(ExpectedFile); // to get the file
			PdfReader Oreader = new PdfReader(ActualFile); // to get the file
			int DPageCnt = Dreader.getNumberOfPages();
			int OPageCnt = Oreader.getNumberOfPages();
			if (DPageCnt == OPageCnt) {
				String Dpage = PdfTextExtractor.getTextFromPage(Dreader, DPageCnt);
				String Opage = PdfTextExtractor.getTextFromPage(Oreader, OPageCnt);
				List<String> DLine = Arrays.asList(Dpage.split("\n"));
				List<String> OLine = Arrays.asList(Opage.split("\n"));
				if (DLine.size() == OLine.size()) {
					for (int i = 0; i < DLine.size() - 1; i++) {
						String DVal = DLine.get(i);
						String OVal = OLine.get(i);
						status = (DVal.equalsIgnoreCase(OVal)) == true ? status
								: status + "Fail, Expected and Actual Pdf files Lines content match and mismatched contents in both files are "
										+ DVal + " and " + OVal + ";";
					}
				} else {
					status = status + "Fail, Expected and Actual Pdf file lines Size does not match;";
				}
			} else {
				status = status + "Fail, Expected and Actual Pdf file Page Count does not match;";
			}

		} catch (Exception e) {
			throw e;
		}
		return status;
	}

	/**
	 * Method used to Compare the Billing PDF File Line Item Contents Dynamically
	 * 
	 * @author venkat kandula
	 * @param String ExpectedFile
	 * @param String ActualFile
	 * @throws Exception
	 */

	public String BillingPDFFileLineItemsCompare(String ExpectedFile, String ActualFile, String Gstcompare,
			String Returnsvalue) throws Exception {
		String status = "";
		try {
			PdfReader Dreader = new PdfReader(ExpectedFile); // to get the file
			PdfReader Oreader = new PdfReader(ActualFile); // to get the file
			int DPageCnt = Dreader.getNumberOfPages();
			int OPageCnt = Oreader.getNumberOfPages();
			int j = 0, k = 0;
			if (DPageCnt == OPageCnt) {
				String Dpage = PdfTextExtractor.getTextFromPage(Dreader, DPageCnt);
				String Opage = PdfTextExtractor.getTextFromPage(Oreader, OPageCnt);
				List<String> DLine = Arrays.asList(Dpage.split("\n"));
				List<String> OLine = Arrays.asList(Opage.split("\n"));
				if (DLine.size() == OLine.size()) {
					for (int i = 0; i < DLine.size() - 1; i++) {
						String DVal = DLine.get(i);
						if (DVal.equalsIgnoreCase("DESCRIPTION TAX QUANTITY AMOUNT")) {
							j = i;
						}
						if (DVal.equalsIgnoreCase(
								"Please do not remit payment for this quote. Your payment will be completed")) {
							k = i;
						}
						if (DVal.equalsIgnoreCase(
								"The request for funds has been initiated for the total amount above.")) {
							k = i;
						}
					}

					for (int p = j + 1; p < k; p++) {

						String DVal = DLine.get(p);
						String OVal = OLine.get(p);
						if (Gstcompare.equalsIgnoreCase("YES")) {
							if (DVal.contains("GST/HST")) {
								String value1 = DLine.get(p + 2);
								System.out.println(value1);
								String[] Val = value1.split(" ");
								String GstHstVal = Val[2];
								status = (!GstHstVal.equalsIgnoreCase("$0.00")) == true ? status
										: status + "Fail, GST/HST tax amount not displaying in Quote/Receipt;";
							}
						}
						if (Returnsvalue != null) {
							if (DVal.contains("Premium Returns")) {
								String value1 = DLine.get(j + 3);
								System.out.println(value1);
								String[] Val = value1.split(" ");
								String RetVal = Val[2];
								status = (RetVal.equalsIgnoreCase(Returnsvalue)) == true ? status
										: status + "Fail, GST/HST tax amount not displaying in Quote/Receipt;";
							}
						}
						status = (DVal.equalsIgnoreCase(OVal)) == true ? status
								: status + "Fail, Expected and Actual Pdf files Lines content match and mismatched contents in both files are "
										+ DVal + " and " + OVal + ";";
					}

				} else {
					status = status + "Fail, Expected and Actual Pdf file lines Size does not match;";
				}
			} else {
				status = status + "Fail, Expected and Actual Pdf file Page Count does not match;";
			}

		} catch (Exception e) {
			throw e;
		}
		return status;
	}

	/*
	 * pdf comparing using a text file declare in function with and as
	 * LatestDownloadedFile(); PDFCompare(Filepath);
	 */
	/*
	 * public String PDFCompare(String ActualFile,String ExpectedFile) throws
	 * Exception { try{ //String File=LatestDownloadedFile()+"//"+ActualFile;
	 * //LatestDownloadedFile(); FileInputStream fileToParse = new
	 * FileInputStream(ActualFile); PDFParser parser = new PDFParser(fileToParse);
	 * parser.parse();
	 * 
	 * // Store the pdf text into string String output = new
	 * PDFTextStripper().getText(parser.getPDDocument());
	 * 
	 * // Remove all spaces, tab, new lines output = output.replaceAll("\\s", "");
	 * output = output.substring(0,output.length()-20);//21 is for Generated Date
	 * EX:04/17/201510:01:54AM
	 * 
	 * parser.getPDDocument().close();
	 * 
	 * // Convert the sample file to string for comparison. Also Remove all spaces,
	 * tab, new lines FileInputStream fileToParse1 = new
	 * FileInputStream(ExpectedFile); PDFParser parser1 = new
	 * PDFParser(fileToParse1); parser1.parse(); //File file = new
	 * File(ExpectedFile); String fileContent =new
	 * PDFTextStripper().getText(parser1.getPDDocument()); fileContent =
	 * fileContent.replaceAll("\\s", ""); fileContent =
	 * fileContent.substring(0,fileContent.length()-20);
	 * parser1.getPDDocument().close(); // Compare the sample and resultant file
	 * assertEquals((output.compareTo(fileContent)), 0);
	 * System.out.println("Content Matched"); } catch (AssertionError e) { return
	 * e.getMessage(); } return "pass"; }
	 */
	public String AnyfileCompare(File ActualFile, File ExpectedFile) throws Exception {
		try {
			@SuppressWarnings("unchecked")
			HashSet<String> f1 = new HashSet<String>(FileUtils.readLines(ActualFile));
			@SuppressWarnings("unchecked")
			HashSet<String> f2 = new HashSet<String>(FileUtils.readLines(ExpectedFile));
			f1.removeAll(f2);
			System.out.println("f1 size is " + f1.size());
			assertTrue(f1.size() == 0);
			if (f1.size() == 0) {
				System.out.println("comparision for success files is equal");
			} else {
				System.out.println("fail, comparision success files not equal");
			}
		} catch (AssertionError e) {
			return e.getMessage();
		}
		return "pass";
	}

	public String compareFiles(Scanner file1, Scanner file2) {
		String status = "";
		try {
			String lineA;
			String lineB;
			int x = 1;
			while (file1.hasNextLine() && file2.hasNextLine()) {
				lineA = file1.nextLine();
				lineB = file2.nextLine();
				if (!lineA.contains("ResponseTimeMS")) {
					status = (lineA.equals(lineB)) == true ? status
							: status + "Fail, Expected and Actual xml Lines not match -" + x + " " + lineA + " and "
									+ lineB + ";";
				}
				/*
				 * if(!lineA.contains("ResponseTimeMS")){ if (!lineA.equals(lineB)) {
				 * System.out.print("Diff " + x++ + "\r\n"); System.out.print("< " + lineA +
				 * "\r\n"); System.out.print("> " + lineB + "\r\n"); } }
				 */
			}
		} catch (AssertionError e) {
			return e.getMessage();
		}
		return status;
	}

	@SuppressWarnings("resource")
	public String ZipfilesCompare(String filepath1, String filepath2) throws Exception {
		ZipFile file1;
		try {
			file1 = new ZipFile(filepath1);
		} catch (IOException e) {
			System.out.println("Could not open zip file : " + e);
			return "fail";
			// return;
		}

		ZipFile file2;
		try {
			file2 = new ZipFile(filepath2);
		} catch (IOException e) {
			System.out.println("Could not open zip file : " + e);
			return "fail";
			// return;
		}

		Set<String> set1 = new LinkedHashSet<String>();
		for (Enumeration<?> e = file1.entries(); e.hasMoreElements();)
			set1.add(((ZipEntry) e.nextElement()).getName());

		Set<String> set2 = new LinkedHashSet<String>();
		for (Enumeration<?> e = file2.entries(); e.hasMoreElements();)
			set2.add(((ZipEntry) e.nextElement()).getName());

		int errcount = 0;
		int filecount = 0;
		String name = null;
		for (Iterator<String> i = set1.iterator(); i.hasNext();) {
			name = (String) i.next();
			if (!set2.contains(name)) {
				name = name + " not found in " + filepath2;
				System.out.println(name);
				errcount += 1;
				continue;
			}
			try {
				set2.remove(name);
				if (!streamsEqual(file1.getInputStream(file1.getEntry(name)),
						file2.getInputStream(file2.getEntry(name)))) {
					name = name + " does not match";
					System.out.println(name);
					errcount += 1;
					continue;
				}
			} catch (Exception e) {
				name = name + ": IO Error " + e;
				System.out.println(name);
				e.printStackTrace();
				errcount += 1;
				continue;
			}
			filecount += 1;
		}
		for (Iterator<String> i = set2.iterator(); i.hasNext();) {
			name = (String) i.next();
			name = name + " not found in " + filepath1;
			System.out.println(name);
			errcount += 1;
		}
		System.out.println(filecount + " entries matched");
		if (errcount > 0) {
			System.out.println(errcount + " entries did not match");
			return "fail";
		}
		if (name == "") {
			return "Pass" + filecount + " entries matched" + errcount + " entries did not match";
		} else {
			return name + "does not match/not found" + filecount + " entries matched" + errcount
					+ " entries did not match";
		}

	}

	static boolean streamsEqual(InputStream stream1, InputStream stream2) throws IOException {
		byte[] buf1 = new byte[4096];
		byte[] buf2 = new byte[4096];
		boolean done1 = false;
		boolean done2 = false;

		try {
			while (!done1) {
				int off1 = 0;
				int off2 = 0;

				while (off1 < buf1.length) {
					int count = stream1.read(buf1, off1, buf1.length - off1);
					if (count < 0) {
						done1 = true;
						break;
					}
					off1 += count;
				}
				while (off2 < buf2.length) {
					int count = stream2.read(buf2, off2, buf2.length - off2);
					if (count < 0) {
						done2 = true;
						break;
					}
					off2 += count;
				}
				if (off1 != off2 || done1 != done2)
					return false;
				for (int i = 0; i < off1; i++) {
					if (buf1[i] != buf2[i])
						return false;
				}
			}
			return true;
		} finally {
			stream1.close();
			stream2.close();
		}
	}

	/**
	 * Get the List of File Names in a Directory venkat kandula param directory
	 * 
	 * @return
	 */
	public ArrayList<String> GetListofFileNames(String directory) {

		File dir = new File(directory);
		File[] listOfFiles = dir.listFiles();
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				list.add(listOfFiles[i].getName());
			}
		}
		return list;

	}

	/**
	 * Compare the CSV and XLS files row and column based venkat kandula param
	 * ActualFile,sheet
	 * 
	 * @return
	 */
	@SuppressWarnings("resource")
	public String CSVXLSFilesCompare(String ActualFile, Sheet sheet) throws Exception {
		String status = "";
		try {
			CSVReader reader = new CSVReader(new FileReader(ActualFile));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int j = 0; j < nextLine.length; j++) {
					list.add(nextLine[j]);
				}
				array.add(list);
			}
			int oRCount = sheet.getRows();
			int DRowCount = array.size();
			if (oRCount == DRowCount) {
				int oColCount = sheet.getColumns();
				int DcolCount = array.get(0).size();
				for (int r = 0; r < DRowCount; r++) {
					if (oColCount == DcolCount) {

						for (int c = 0; c < DcolCount; c++) {
							String dContent = array.get(r).get(c);
							String oContent = sheet.getCell(DcolCount, c).getContents();
							if (dContent == oContent) {

								status = status
										+ "Fail, NO. Expected WorkSheets Report Content Does not match with Actual; ";

							}
						}
					} else {
						status = status + "Fail, NO. Expected WorkSheets Column Numbers Does not match with Actual; ";
					}

				}
			} else {

				status = status + "Fail, NO. Expected WorkSheets Row Numbers Does not match with Actual; ";

			}
			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}

		return status;
	}

	/**
	 * Compare the XLSX and XLSX files row and column based venkat kandula param
	 * ExpectedFile,ActualFile
	 * 
	 * @return
	 */
	public String XLSXtoXLSXFilesCompare(String ExpectedFile, String ActualFile) throws Exception {
		String status = "";
		try {
			FileInputStream Ex_fis = new FileInputStream(ExpectedFile);
			XSSFWorkbook Ex_workbook = new XSSFWorkbook(Ex_fis);
			XSSFSheet Ex_sheet = Ex_workbook.getSheetAt(0);
			int Num_rows = Ex_sheet.getLastRowNum();
			XSSFRow HeaderRow = Ex_sheet.getRow(0); // 1st row is my header row
			FileInputStream Ac_fis = new FileInputStream(ActualFile);
			XSSFWorkbook Ac_workbook = new XSSFWorkbook(Ac_fis);
			XSSFSheet Ac_sheet = Ac_workbook.getSheetAt(0);
			int Ac_Num_rows = Ac_sheet.getLastRowNum();
			XSSFRow Ac_HeaderRow = Ac_sheet.getRow(0); // 1st row is Ac_Num_rows header row
			if (Ac_Num_rows == Num_rows) {
				int columnsCount = HeaderRow.getLastCellNum();
				int Ac_columnsCount = Ac_HeaderRow.getLastCellNum();
				if (columnsCount == Ac_columnsCount) {
					for (int i = 0; i <= Num_rows; i++) {
						for (int col = 0; col < columnsCount; col++) {
							XSSFCell Ex_Content = HeaderRow.getCell(col);
							XSSFCell Ac_Content = Ac_HeaderRow.getCell(col);
							String Ex_Content_Value = Ex_Content.getStringCellValue().trim();
							String Ac_Content_Value = Ac_Content.getStringCellValue().trim();
							status = (Ex_Content_Value.equalsIgnoreCase(Ac_Content_Value)) == true ? status
									: status + "Fail, File Content does not match and mismatched contents in both files are "
											+ Ex_Content_Value + " and " + Ac_Content_Value + ";";
						}
					}

				} else {
					status = status + "Fail, Expected and Actual XLSX files Columns Count does not match;";
				}
			} else {
				status = status + "Fail, Expected and Actual XLSX files Row Count does not match;";
			}

		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	@SuppressWarnings("unused")
	public String CSVValueCompare(String Filepath, String Clientname, String taxpayern, String ErrorTy, String Errormsg)
			throws Exception {
		String status = "";
		try {
			String downerrtyp = null, downerrmsg = null;
			CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			// Nested loop to display results
			for (int x = 1; x < array.size(); x++) {
				for (int y = 0; y <= array.get(x).size(); y++) {
					// String Cell_Name=array.get(x).get(y);
					ClientNme = array.get(x).get(y + 1);
					if (ClientNme.equalsIgnoreCase(Clientname)) {
						status = (ClientNme.equalsIgnoreCase(Clientname)) == true ? status
								: status + "Fail, Column Value doesnot match;";
						Cell_Value = array.get(x).get(y + 6);
						if (Cell_Value.equalsIgnoreCase(taxpayern)) {
							downerrtyp = array.get(x).get(y + 9);
							downerrmsg = array.get(x).get(y + 10);
							System.out.println(downerrtyp + " and " + downerrmsg);
						}
					}
					break;
				}
				if (Cell_Value.equalsIgnoreCase(taxpayern) && (downerrtyp.equalsIgnoreCase(ErrorTy))
						&& (downerrmsg.equalsIgnoreCase(Errormsg))) {
				} else {
					status = "values are not matched";
				}
				break;
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	/**
	 * Compare the XLSX and XLSX files row and column based venkat kandula param
	 * ExpectedFile,ActualFile
	 * 
	 * @return
	 */
	public String XLSXtoXLSXFilesCompare(String ExpectedFile, String ActualFile, String sheetname) throws Exception {
		String status = "";
		try {
			FileInputStream Ex_fis = new FileInputStream(ExpectedFile);
			XSSFWorkbook Ex_workbook = new XSSFWorkbook(Ex_fis);
			XSSFSheet Ex_sheet = Ex_workbook.getSheet(sheetname);
			int Num_rows = Ex_sheet.getLastRowNum();
			XSSFRow HeaderRow = Ex_sheet.getRow(0); // 1st row is my header row
			FileInputStream Ac_fis = new FileInputStream(ActualFile);
			XSSFWorkbook Ac_workbook = new XSSFWorkbook(Ac_fis);
			XSSFSheet Ac_sheet = Ac_workbook.getSheet(sheetname);
			int Ac_Num_rows = Ac_sheet.getLastRowNum();
			XSSFRow Ac_HeaderRow = Ac_sheet.getRow(0); // 1st row is Ac_Num_rows header row
			if (Ac_Num_rows == Num_rows) {
				int columnsCount = HeaderRow.getLastCellNum();
				int Ac_columnsCount = Ac_HeaderRow.getLastCellNum();
				if (columnsCount == Ac_columnsCount) {
					for (int i = 0; i <= Num_rows; i++) {
						for (int col = 0; col < columnsCount; col++) {
							XSSFCell Ex_Content = HeaderRow.getCell(col);
							XSSFCell Ac_Content = Ac_HeaderRow.getCell(col);
							String Ex_Content_Value = Ex_Content.getStringCellValue().trim();
							String Ac_Content_Value = Ac_Content.getStringCellValue().trim();
							status = (Ex_Content_Value.equalsIgnoreCase(Ac_Content_Value)) == true ? status
									: status + "Fail, File Content does not match and mismatched contents in both files are "
											+ Ex_Content_Value + " and " + Ac_Content_Value + ";";
						}
					}

				} else {
					status = status + "Fail, Expected and Actual XLSX files Columns Count does not match;";
				}
			} else {
				status = status + "Fail, Expected and Actual XLSX files Row Count does not match;";
			}

		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	/**
	 * Compare the XLSX and XLSX files only column based sowmya param
	 * ExpectedFile,ActualFile
	 * 
	 * @return
	 */
	public String XLSXtoXLSXFilesColCompare(String ExpectedFile, String ActualFile, String sheetname) throws Exception {
		String status = "";
		try {
			FileInputStream Ex_fis = new FileInputStream(ExpectedFile);
			XSSFWorkbook Ex_workbook = new XSSFWorkbook(Ex_fis);
			XSSFSheet Ex_sheet = Ex_workbook.getSheet(sheetname);
			XSSFRow HeaderRow = Ex_sheet.getRow(0); // 1st row is my header row
			FileInputStream Ac_fis = new FileInputStream(ActualFile);
			XSSFWorkbook Ac_workbook = new XSSFWorkbook(Ac_fis);
			XSSFSheet Ac_sheet = Ac_workbook.getSheet(sheetname);
			XSSFRow Ac_HeaderRow = Ac_sheet.getRow(0); // 1st row is Ac_Num_rows header row
			int columnsCount = HeaderRow.getLastCellNum();
			int Ac_columnsCount = Ac_HeaderRow.getLastCellNum();
			if (columnsCount == Ac_columnsCount) {
				for (int col = 0; col < columnsCount; col++) {
					XSSFCell Ex_Content = HeaderRow.getCell(col);
					XSSFCell Ac_Content = Ac_HeaderRow.getCell(col);
					String Ex_Content_Value = Ex_Content.getStringCellValue().trim();
					String Ac_Content_Value = Ac_Content.getStringCellValue().trim();
					status = (Ex_Content_Value.equalsIgnoreCase(Ac_Content_Value)) == true ? status
							: status + "Fail, File Content does not match and mismatched contents in both files are "
									+ Ex_Content_Value + " and " + Ac_Content_Value + ";";
				}
			} else {
				status = status + "Fail, Expected and Actual XLSX files Columns Count does not match;";
			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	/**
	 * Rename the file name in the Downloading Files folder venkat kandula param
	 * OldFileName,NewFileName
	 * 
	 * @return
	 */
	public String RenameFileName(String OldFileName, String NewFileName) {
		try {
			File oldName = new File(OldFileName);
			File newName = new File(NewFileName);

			if (oldName.renameTo(newName)) {
				System.out.println("renamed");
			} else {
				System.out.println("Error");
			}

		} catch (Exception e) {
			return e.getMessage();
		}
		return NewFileName;

	}

	/*
	 * public void xResult(final WebDriver driver, WritableSheet RSheet, String
	 * value) throws Exception {
	 * 
	 * File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	 * String FilePath = xRandomTimeStampName(value); FileUtils.copyFile(scrFile,
	 * new File(FilePath)); WritableHyperlink hyperlink = new WritableHyperlink(12,
	 * VertexCloud.RowNo - 1, new File(FilePath), "Error Snapshot Link");
	 * RSheet.addHyperlink(hyperlink); }
	 */

	public void xCatchexception(WritableSheet RSheet, int Rownumber, String error)
			throws RowsExceededException, WriteException {

		RSheet.addCell(new Label(11, Rownumber - 1, error));
		RSheet.addCell(new Label(10, Rownumber - 1, "Fail", xFillCell(Colour.RED)));

	}

	/**
	 * Unzip it venkat kandula
	 * 
	 * @param zipFile input zip file
	 * @param output  zip file output folder
	 */
	public void unZipIt(String zipFile, String outputFolder) {

		byte[] buffer = new byte[1024];

		try {
			// create output directory is not exists
			File folder = new File(outputFolder);
			File directory = new File(".//");
			String output = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "RTE" + "\\";
			if (!folder.exists()) {
				folder.mkdir();
			}
			System.out.println("folder: " + folder);
			System.out.println("zipFile: " + zipFile);
			FileInputStream fis = null;
			ZipInputStream zis = null;
			ZipInputStream zis1 = null;
			ZipEntry zEntry = null;
			String ExtractFileName = "";
			fis = new FileInputStream(zipFile);
			zis = new ZipInputStream(new BufferedInputStream(fis));
			while ((zEntry = zis.getNextEntry()) != null) {
				try {
					byte[] tmp = new byte[4 * 1024];
					FileOutputStream fos = null;
					zEntry.getName();
					ExtractFileName = output + zEntry.getName();
					System.out.println("Extracting file to " + ExtractFileName);
					fos = new FileOutputStream(ExtractFileName);
					int size = 0;
					while ((size = zis.read(tmp)) != -1) {
						fos.write(tmp, 0, size);
					}
					fos.flush();
					fos.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

			// get the zip file content
			zis1 = new ZipInputStream(new FileInputStream(zipFile.trim()));
			// get the zipped file list entry
			ZipEntry ze = zis1.getNextEntry();
			System.out.println("ze: " + ze);
			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(output + File.separator + fileName);
				System.out.println("newFile: " + newFile);
				String filename = newFile.getAbsoluteFile().getName();
				actualfile=newFile.toString();
				listoffiles = filename + ";" + listoffiles;
				System.out.println("List of file names in the folder are :" + listoffiles);

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Unzip it Sowmya Damera
	 * 
	 * @param output zip file output folder
	 */
	public void unZipRTE(String outputFolder, String output) {

		byte[] buffer = new byte[1024];

		try {
			// create output directory is not exists
			File folder = new File(outputFolder);
			// File directory = new File(".//");
			// String output = directory.getCanonicalPath() + "\\" +
			// "resources\\testdata\\actual" +
			// "\\" + "RTE" + "\\";
			output = output + "\\";
			File folder2 = new File(output);
			if (!folder.exists()) {
				folder.mkdir();
			}
			if (!folder2.exists()) {
				folder2.mkdir();
			}
			System.out.println("folder: " + folder);
			System.out.println("folder: " + folder2);
			// System.out.println("zipFile: " + zipFile);
			FileInputStream fis = null;
			ZipInputStream zis = null;
			ZipInputStream zis1 = null;
			ZipEntry zEntry = null;
			String ExtractFileName = "";
			fis = new FileInputStream(outputFolder);
			zis = new ZipInputStream(new BufferedInputStream(fis));
			while ((zEntry = zis.getNextEntry()) != null) {
				try {
					byte[] tmp = new byte[4 * 1024];
					FileOutputStream fos = null;
					zEntry.getName();
					ExtractFileName = output + zEntry.getName();
					System.out.println("Extracting file to " + ExtractFileName);
					fos = new FileOutputStream(ExtractFileName);
					int size = 0;
					while ((size = zis.read(tmp)) != -1) {
						fos.write(tmp, 0, size);
					}
					fos.flush();
					fos.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

			// get the zip file content
			zis1 = new ZipInputStream(new FileInputStream(outputFolder.trim()));
			// get the zipped file list entry
			ZipEntry ze = zis1.getNextEntry();
			System.out.println("ze: " + ze);
			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(output + File.separator + fileName);
				System.out.println("newFile: " + newFile);
				String filename = newFile.getAbsoluteFile().getName();
				listoffiles = filename + ";" + listoffiles;
				System.out.println("List of file names in the folder are :" + listoffiles);

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// comparing files with File Format.
	public String Comparecsvfiles(File File1, File File2) throws Exception {
		String status = "";
		try {
			CSVReader reader1 = new CSVReader(new FileReader(File1));
			CSVReader reader2 = new CSVReader(new FileReader(File2));
			ArrayList<ArrayList<String>> array1 = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> array2 = new ArrayList<ArrayList<String>>();
			String[] nextLine1;
			while ((nextLine1 = reader1.readNext()) != null) {
				ArrayList<String> list1 = new ArrayList<String>();
				for (int i = 0; i < nextLine1.length; i++) {
					list1.add(nextLine1[i]);
				}
				array1.add(list1);
			}
			String[] nextLine2;
			while ((nextLine2 = reader2.readNext()) != null) {
				ArrayList<String> list2 = new ArrayList<String>();
				for (int i = 0; i < nextLine2.length; i++) {
					list2.add(nextLine2[i]);
				}
				array2.add(list2);
			}
			// Nested loop to display results
			int arraysize = 0;
			int arraycolumnsize = 0;
			int filesize1 = array1.size();
			int filesize2 = array2.size();
			if (filesize1 > filesize2) {
				arraysize = filesize1;
			} else if (filesize1 < filesize2) {
				arraysize = filesize2;
			} else {
				arraysize = filesize1;
			}

			for (int rf1 = 0; rf1 < arraysize; rf1++) {

				int filesizec1 = array1.get(rf1).size();
				int filesizec2 = array2.get(rf1).size();
				if (filesizec1 > filesizec2) {
					arraycolumnsize = filesizec1;
				} else if (filesizec1 < filesizec2) {
					arraycolumnsize = filesizec2;
				} else {

					arraycolumnsize = filesizec1;
				}

				for (int cf1 = 0; cf1 < arraycolumnsize; cf1++) {
					String file1content = null;
					String file2content = null;
					try {
						file1content = array1.get(rf1).get(cf1);
						file2content = array2.get(rf1).get(cf1);
						status = (file1content.equalsIgnoreCase(file2content)) == true ? status
								: status + "Fail, File Content does not match and mismatched contents in both files are in row "
										+ rf1 + " and column " + cf1 + ";";
					} catch (Exception e) {
						status = status
								+ "Fail, File Content does not match and mismatched contents in both files are in row "
								+ rf1 + " and column " + cf1 + ";";
					}

				}
			}

		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}

	public String csvFilesCompare(String File1, String File2) throws Exception {
		String status = "";
		try {
			CSVReader reader1 = new CSVReader(new FileReader(File1));
			CSVReader reader2 = new CSVReader(new FileReader(File2));
			ArrayList<ArrayList<String>> array1 = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> array2 = new ArrayList<ArrayList<String>>();
			String[] nextLine1;
			while ((nextLine1 = reader1.readNext()) != null) {
				ArrayList<String> list1 = new ArrayList<String>();
				for (int i = 0; i < nextLine1.length; i++) {
					list1.add(nextLine1[i]);
				}
				array1.add(list1);
			}
			String[] nextLine2;
			while ((nextLine2 = reader2.readNext()) != null) {
				ArrayList<String> list2 = new ArrayList<String>();
				for (int i = 0; i < nextLine2.length; i++) {
					list2.add(nextLine2[i]);
				}
				array2.add(list2);
			}
			// Nested loop to display results
			int arraysize = 0;
			int arraycolumnsize = 0;
			int filesize1 = array1.size();
			int filesize2 = array2.size();
			if (filesize1 > filesize2) {
				arraysize = filesize1;
			} else if (filesize1 < filesize2) {
				arraysize = filesize2;
			} else {
				arraysize = filesize1;
			}

			for (int rf1 = 0; rf1 < arraysize; rf1++) {

				int filesizec1 = array1.get(rf1).size();
				int filesizec2 = array2.get(rf1).size();
				if (filesizec1 > filesizec2) {
					arraycolumnsize = filesizec1;
				} else if (filesizec1 < filesizec2) {
					arraycolumnsize = filesizec2;
				} else {

					arraycolumnsize = filesizec1;
				}

				for (int cf1 = 0; cf1 < arraycolumnsize; cf1++) {
					String file1content = null;
					String file2content = null;
					try {
						file1content = array1.get(rf1).get(cf1);
						file2content = array2.get(rf1).get(cf1);
						status = (file1content.equalsIgnoreCase(file2content)) == true ? status
								: status + "Fail, File Content does not match and mismatched contents in both files are in row "
										+ rf1 + " and column " + cf1 + ";";
					} catch (Exception e) {
						status = status
								+ "Fail, File Content does not match and mismatched contents in both files are in row "
								+ rf1 + " and column " + cf1 + ";";
					}

				}
			}

		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}

	/**
	 * merge the multiple csv files to the new csv file venkat kandula param
	 * File1,File2
	 * 
	 * @return
	 */
	public String mergecsvfilestonewcsv(String filenames, String outputFile) throws Exception {

		try {
			String path = filenames.split(";")[0];
			String[] tfile = filenames.split(";");
			int len = tfile.length;
			for (int i = 1; i < len; i++) {
				if (i == 1) {
					reader = new CSVReader(new FileReader(path + "\\" + tfile[i]));
					List<String[]> allRows = reader.readAll();
					csvOutput = new CSVWriter(new FileWriter(outputFile, true));
					csvOutput.writeAll(allRows);
					csvOutput.close();
					reader.close();

				} else {
					reader = new CSVReader(new FileReader(path + "\\" + tfile[i]), ',', '\'', 1);
					List<String[]> allRows = reader.readAll();
					csvOutput = new CSVWriter(new FileWriter(outputFile, true));
					csvOutput.writeAll(allRows);
					csvOutput.close();
					reader.close();
				}
			}

		} catch (Exception e) {

			e.getMessage();
		}
		return filenames;
	}

	public String xReadJSON(String JSONfilepath, String ObjectName, String dwnldfileid) throws Exception {
		JSONParser parser = new JSONParser();
		String Value = null;

		try {
			Object obj = parser.parse(new FileReader(JSONfilepath));
			JSONObject jsonObject = (JSONObject) obj;

			switch (ObjectName) {
			/*
			 * case "id": JSONObject data = (JSONObject) jsonObject.get("data"); Value =
			 * data.get(ObjectName).toString(); System.out.println(Value); break;
			 */
			case "id":
				JSONObject data = (JSONObject) jsonObject.get("_data");
				if (data == null) {
					data = (JSONObject) jsonObject.get("data");
				}
				Value = data.get(ObjectName).toString();
				System.out.println(Value);
				break;
			case "url":
				JSONObject dataurl = (JSONObject) jsonObject.get("_data");
				if (dataurl == null) {
					dataurl = (JSONObject) jsonObject.get("data");
				}
				Value = dataurl.get(ObjectName).toString();
				System.out.println(Value);
				break;
			case "message":
				JSONObject data3 = (JSONObject) jsonObject.get("data");
				JSONObject status3 = (JSONObject) data3.get("status");
				Value = status3.get(ObjectName).toString();
				System.out.println(Value);
				break;
			case "status":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "fileGuid":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "reports":
				JSONObject data2 = (JSONObject) jsonObject.get("data");
				JSONObject status2 = (JSONObject) data2.get(ObjectName);
				if (dwnldfileid.equalsIgnoreCase("yes")) {
					dwnldfileid = "successFileId";
				} else if (dwnldfileid.equalsIgnoreCase("No")) {
					dwnldfileid = ("errorFileId");
				} else {
					dwnldfileid = "";
				}
				Value = status2.get(dwnldfileid).toString();
				System.out.println(Value);
				break;
			case "access_token":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "isValid":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "name":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "data":
				JSONObject Jtax = (JSONObject) jsonObject.get(ObjectName);
				if (dwnldfileid.equalsIgnoreCase("id")) {
					Value = Jtax.get("id").toString();
				}
				break;
			case "customerCode":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "flexField1":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "code":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "uuid":
				Value = jsonObject.get(ObjectName).toString();
				break;

			}
		} catch (Exception e) {
			Value = e.getMessage();
		}
		return Value;
	}

	@SuppressWarnings("unused")
	public String xReadjson(String JSONfilepath, String ObjectName) throws Exception {
		JSONParser parser = new JSONParser();
		String Value = null;

		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader(JSONfilepath));
			JSONObject locs = (JSONObject) obj.get("data");
			JSONArray array = (JSONArray) locs.get("responseDetails");

			switch (ObjectName) {
			case "city":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					System.out.println(array.get(i));
					JSONObject obj1 = (JSONObject) array.get(i);
					JSONArray locs1 = (JSONArray) obj1.get("addresses");
					System.out.println(locs1.get(i));
					JSONObject obj2 = (JSONObject) locs1.get(i);
					Value = obj2.get(ObjectName).toString();
					System.out.println(Value);
					break;
				}
				break;
			case "state":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get(ObjectName).toString();
					System.out.println(Value);
					break;
				}
			}
		} catch (Exception e) {
			Value = e.getMessage();
		}
		return Value;

	}

	public String xReadJSONPOSTCertificateApi(String JSONfilepath, String ObjectName) throws Exception {
		JSONParser parser = new JSONParser();
		String Value = null;
		try {
			Object obj = parser.parse(new FileReader(JSONfilepath));
			JSONObject jsonObject = (JSONObject) obj;
			switch (ObjectName) {
			case "code":
				JSONObject data = (JSONObject) jsonObject.get("error");
				Value = data.get(ObjectName).toString();
				break;
			case "message":
				JSONObject data1 = (JSONObject) jsonObject.get("error");
				Value = data1.get(ObjectName).toString();
				break;
			}
		} catch (Exception e) {
			Value = e.getMessage();
		}
		return Value;
	}

	@SuppressWarnings("unused")
	public String xReadJSONGETCertificateApi(String JSONfilepath, String ObjectName, String Name) throws Exception {
		JSONParser parser = new JSONParser();
		String Value = null;
		try {
			Object obj = parser.parse(new FileReader(JSONfilepath));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray array = (JSONArray) jsonObject.get("coverages");
			switch (ObjectName) {
			case "id":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "uuid":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "syncId":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "source":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customerName":
				JSONObject data = (JSONObject) jsonObject.get("customer");
				Value = data.get(ObjectName).toString();
				break;
			case "customeruuid":
				JSONObject data1 = (JSONObject) jsonObject.get("customer");
				Value = data1.get("uuid").toString();
				break;
			case "certificateScope":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "effectiveDate":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "endDate":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "code":
				JSONObject dat1 = (JSONObject) jsonObject.get("customer");
				Value = dat1.get(ObjectName).toString();
				break;
			case "sellercode":
				JSONObject data11 = (JSONObject) jsonObject.get("seller");
				Value = data11.get("code").toString();
				break;
			case "classId":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "className":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField1":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField2":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField3":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField4":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField5":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField6":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField7":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField8":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField9":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField10":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "notes":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "sellername":
				JSONObject data111 = (JSONObject) jsonObject.get("seller");
				Value = data111.get("name").toString();
				break;
			case "name":
				JSONObject data12 = (JSONObject) jsonObject.get("contact");
				Value = data12.get(ObjectName).toString();
				break;
			case "city":
				JSONObject data14 = (JSONObject) jsonObject.get("contact");
				Value = data14.get(ObjectName).toString();
				break;
			case "email":
				JSONObject data15 = (JSONObject) jsonObject.get("contact");
				Value = data15.get(ObjectName).toString();
				break;
			case "state":
				JSONObject data16 = (JSONObject) jsonObject.get("contact");
				Value = data16.get(ObjectName).toString();
				break;
			case "country":
				JSONObject data17 = (JSONObject) jsonObject.get("contact");
				Value = data17.get(ObjectName).toString();
				break;
			case "postalCode":
				JSONObject data18 = (JSONObject) jsonObject.get("contact");
				Value = data18.get(ObjectName).toString();
				break;
			case "phone":
				JSONObject data19 = (JSONObject) jsonObject.get("contact");
				Value = data19.get(ObjectName).toString();
				break;
			case "address":
				JSONObject data20 = (JSONObject) jsonObject.get("contact");
				Value = data20.get(ObjectName).toString();
				break;
			case "address2":
				JSONObject data13 = (JSONObject) jsonObject.get("contact");
				Value = data13.get(ObjectName).toString();
				break;
			case "certificateNumber":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get(ObjectName).toString();
					break;
				}
				break;
			case "issueDate":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get(ObjectName).toString();
					break;
				}
				break;
			case "expirationDate":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get(ObjectName).toString();
					break;
				}
				break;
			case "reviewDate":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get(ObjectName).toString();
					break;
				}
				break;
			case "jurisdictionid":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("jurisdiction").toString();
					if (Value.contains(Name)) {
						JSONObject obj2 = (JSONObject) parser.parse(Value);
						Value = obj2.get("id").toString();
						break;
					}
				}
				break;
			case "jurisdictionname":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("jurisdiction").toString();
					if (Value.contains(Name)) {
						JSONObject obj2 = (JSONObject) parser.parse(Value);
						Value = obj2.get("name").toString();
						break;
					}
				}
				break;
			case "jurisdictionabbreviation":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("jurisdiction").toString();
					if (Value.contains(Name)) {
						JSONObject obj2 = (JSONObject) parser.parse(Value);
						Value = obj2.get("abbreviation").toString();
						break;
					}
				}
				break;
			case "jurisdictionType":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("jurisdiction").toString();
					if (Value.contains(Name)) {
						JSONObject obj2 = (JSONObject) parser.parse(Value);
						Value = obj2.get("jurisdictionType").toString();
						break;
					}
				}
				break;
			case "reasonid":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("reason").toString();
					if (Value.contains(Name)) {
						JSONObject obj2 = (JSONObject) parser.parse(Value);
						Value = obj2.get("id").toString();
						break;
					}
				}
				break;
			case "reasontype":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("reason").toString();
					if (Value.contains(Name)) {
						JSONObject obj2 = (JSONObject) parser.parse(Value);
						Value = obj2.get("type").toString();
						break;
					}
				}
				break;
			}
		} catch (Exception e) {
			Value = e.getMessage();
		}
		return Value;
	}

	@SuppressWarnings("unused")
	public String xReadJSONCertificateApiarray(String JSONfilepath, String ObjectName, String Name) throws Exception {
		JSONParser parser = new JSONParser();
		String Value = null;
		String Valuee = null;

		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader(JSONfilepath));
			JSONArray array = (JSONArray) obj.get("coverages");
			JSONArray array1 = (JSONArray) obj.get("jurisdictionReasonTypes");
			switch (ObjectName) {
			case "certificateNumber":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Valuee = obj1.get(ObjectName).toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "issueDate":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Valuee = obj1.get(ObjectName).toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "excludeLowerJurisdictions":
				for (int i = 0; i < array1.size(); i++) {
					array1.get(i);
					JSONObject obj1 = (JSONObject) array1.get(i);
					Valuee = obj1.get(ObjectName).toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "expirationDate":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Valuee = obj1.get(ObjectName).toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "reviewDate":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Valuee = obj1.get(ObjectName).toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "jurisdiction":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get("jurisdiction").toString();
					if (Value.contains(Name)) {
						JSONObject obj1 = (JSONObject) parser.parse(Value);
						Valuee = obj1.get("id").toString();
						break;
					}
				}
				// case "jurisdiction":
				// for (int i = 0; i < array.size(); i++) {
				// array.get(i);
				// JSONObject obj3 = (JSONObject) array.get(i);
				// Value = obj3.get("jurisdiction").toString();
				// if (Value.contains(Name)) {
				// Object obj1 = parser.parse(Value);
				// JSONObject jsonObject = (JSONObject) obj1;
				// JSONObject data = (JSONObject) jsonObject.get("id");
				// Valuee = data.get(ObjectName).toString();
				// break;
				// }
				// }
			}

			// JSONObject status3 = (JSONObject) data3.get("jurisdiction");
			// Value = status3.get(ObjectName).toString();
			// System.out.println(Value);
			// }

		} catch (Exception e) {
			Value = e.getMessage();
		}
		return Valuee;
	}

	public String xReadJSONV3CustomerApi(String JSONfilepath, String ObjectName) throws Exception {
		JSONParser parser = new JSONParser();
		String Value = null;
		try {
			Object obj = parser.parse(new FileReader(JSONfilepath));
			JSONObject jsonObject = (JSONObject) obj;
			switch (ObjectName) {
			case "code":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "status":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "uuid":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "syncId":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "effectiveDate":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "endDate":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField1":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField2":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField3":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField4":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField5":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField6":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField7":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField8":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField9":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customField10":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "customerName":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "sellerCode":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "deleted":
				Value = jsonObject.get("isDeleted").toString();
				break;
			case "effDate":
				Value = jsonObject.get(ObjectName).toString();
				break;
			case "firstName":
				JSONArray array = (JSONArray) jsonObject.get("contacts");
				for (int i = 0; i < array.size(); i++) {
					JSONObject address = (JSONObject) array.get(i);
					Value = (String) address.get("firstName");
				}
				break;
			case "lastName":
				JSONArray a1 = (JSONArray) jsonObject.get("contacts");
				for (int i = 0; i < a1.size(); i++) {
					JSONObject address = (JSONObject) a1.get(i);
					Value = (String) address.get("lastName");
				}
				break;
			case "customerStreetAddress":
				JSONArray a2 = (JSONArray) jsonObject.get("contacts");
				for (int i = 0; i < a2.size(); i++) {
					JSONObject address = (JSONObject) a2.get(i);
					Value = (String) address.get("customerStreetAddress");
				}
				break;
			case "customerCity":
				JSONArray a3 = (JSONArray) jsonObject.get("contacts");
				for (int i = 0; i < a3.size(); i++) {
					JSONObject address = (JSONObject) a3.get(i);
					Value = (String) address.get("customerCity");
				}
				break;
			case "customerPostalCode":
				JSONArray a4 = (JSONArray) jsonObject.get("contacts");
				for (int i = 0; i < a4.size(); i++) {
					JSONObject address = (JSONObject) a4.get(i);
					Value = (String) address.get("customerPostalCode");
				}
				break;
			case "customerCountry":
				JSONArray a5 = (JSONArray) jsonObject.get("contacts");
				for (int i = 0; i < a5.size(); i++) {
					JSONObject address = (JSONObject) a5.get(i);
					Value = (String) address.get("customerCountry");
				}
				break;
			case "customerEmail":
				JSONArray a6 = (JSONArray) jsonObject.get("contacts");
				for (int i = 0; i < a6.size(); i++) {
					JSONObject address = (JSONObject) a6.get(i);
					Value = (String) address.get("customerEmail");
				}
				break;

			case "custcompanyCode":
				JSONArray a7 = (JSONArray) jsonObject.get("sellers");
				for (int i = 0; i < a7.size(); i++) {
					JSONObject address = (JSONObject) a7.get(i);
					Value = (String) address.get("companyCode");
				}
				break;
			case "custdivisionCode":
				JSONArray a8 = (JSONArray) jsonObject.get("sellers");
				for (int i = 0; i < a8.size(); i++) {
					JSONObject address = (JSONObject) a8.get(i);
					Value = (String) address.get("divisionCode");
				}
				break;
			case "custdepartmentCode":
				JSONArray a9 = (JSONArray) jsonObject.get("sellers");
				for (int i = 0; i < a9.size(); i++) {
					JSONObject address = (JSONObject) a9.get(i);
					Value = (String) address.get("departmentCode");
				}
				break;
			case "custpartitionName":
				JSONArray a10 = (JSONArray) jsonObject.get("sellers");
				for (int i = 0; i < a10.size(); i++) {
					JSONObject address = (JSONObject) a10.get(i);
					Value = (String) address.get("partitionName");
				}
				break;
			case "companyCode":
				JSONObject data = (JSONObject) jsonObject.get("taxpayerDefinition");
				Value = data.get(ObjectName).toString();
				System.out.println(Value);
				break;
			case "divisionCode":
				JSONObject data1 = (JSONObject) jsonObject.get("taxpayerDefinition");
				Value = data1.get(ObjectName).toString();
				System.out.println(Value);
				break;
			case "departmentCode":
				JSONObject data2 = (JSONObject) jsonObject.get("taxpayerDefinition");
				Value = data2.get(ObjectName).toString();
				System.out.println(Value);
				break;
			case "partitionName":
				JSONObject data3 = (JSONObject) jsonObject.get("taxpayerDefinition");
				Value = data3.get(ObjectName).toString();
				System.out.println(Value);
				break;
			case "name":
				JSONArray a11 = (JSONArray) jsonObject.get("contacts");
				for (int i = 0; i < a11.size(); i++) {
					JSONObject address = (JSONObject) a11.get(i);
					Value = (String) address.get("name");
				}
				break;
			case "Scode":
				JSONArray a12 = (JSONArray) jsonObject.get("sellers");
				for (int i = 0; i < a12.size(); i++) {
					JSONObject address = (JSONObject) a12.get(i);
					Value = (String) address.get("code");
				}
				break;
			}
		} catch (Exception e) {
			Value = e.getMessage();
		}
		return Value;
	}

	@SuppressWarnings("unused")
	public String xReadJSONArray(String JSONfilepath, String ObjectName, String Name) throws Exception {
		JSONParser parser = new JSONParser();
		String Value = null;
		String Valuee = null;

		try {
			Object obj = parser.parse(new FileReader(JSONfilepath));
			JSONArray array = (JSONArray) obj;
			switch (ObjectName) {

			case "addresses":
				JSONArray aa = (JSONArray) obj;
				for (int i = 0; i < aa.size(); i++) {
					aa.get(i);
					JSONObject obj1 = (JSONObject) aa.get(i);
					Value = obj1.get("city").toString();
					/*
					 * JSONObject obj2 = (JSONObject) parser.parse(Value); Valuee =
					 * obj2.get("companyCode").toString(); System.out.println(Valuee);
					 */
					break;
				}
				break;

			case "companyCode":
				JSONArray aa0 = (JSONArray) obj;
				for (int i = 0; i < aa0.size(); i++) {
					aa0.get(i);
					JSONObject obj1 = (JSONObject) aa0.get(i);
					Value = obj1.get("taxpayerDefinition").toString();
					JSONObject obj2 = (JSONObject) parser.parse(Value);
					Valuee = obj2.get("companyCode").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "divisionCode":
				JSONArray aa1 = (JSONArray) obj;
				for (int i = 0; i < aa1.size(); i++) {
					aa1.get(i);
					JSONObject obj1 = (JSONObject) aa1.get(i);
					Value = obj1.get("taxpayerDefinition").toString();
					JSONObject obj2 = (JSONObject) parser.parse(Value);
					Valuee = obj2.get("divisionCode").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "departmentCode":
				JSONArray aa2 = (JSONArray) obj;
				for (int i = 0; i < aa2.size(); i++) {
					aa2.get(i);
					JSONObject obj1 = (JSONObject) aa2.get(i);
					Value = obj1.get("taxpayerDefinition").toString();
					JSONObject obj2 = (JSONObject) parser.parse(Value);
					Valuee = obj2.get("departmentCode").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "partitionName":
				JSONArray aa3 = (JSONArray) obj;
				for (int i = 0; i < aa3.size(); i++) {
					aa3.get(i);
					JSONObject obj1 = (JSONObject) aa3.get(i);
					Value = obj1.get("taxpayerDefinition").toString();
					JSONObject obj2 = (JSONObject) parser.parse(Value);
					Valuee = obj2.get("partitionName").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "Taxpayeruuid":
				JSONArray aa4 = (JSONArray) obj;
				for (int i = 0; i < aa4.size(); i++) {
					aa4.get(i);
					JSONObject obj1 = (JSONObject) aa4.get(i);
					Value = obj1.get("taxpayerDefinition").toString();
					JSONObject obj2 = (JSONObject) parser.parse(Value);
					Valuee = obj2.get("uuid").toString();
					System.out.println(Valuee);
					break;
				}
				break;

			case "uuid":
				JSONArray a22 = (JSONArray) obj;
				for (int i = 0; i < a22.size(); i++) {
					a22.get(i);
					JSONObject obj1 = (JSONObject) a22.get(i);
					Valuee = obj1.get("uuid").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "status":
				JSONArray a2 = (JSONArray) obj;
				for (int i = 0; i < a2.size(); i++) {
					a2.get(i);
					JSONObject obj1 = (JSONObject) a2.get(i);
					Valuee = obj1.get("status").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customerName":
				JSONArray a31 = (JSONArray) obj;
				for (int i = 0; i < a31.size(); i++) {
					a31.get(i);
					JSONObject obj1 = (JSONObject) a31.get(i);
					Valuee = obj1.get("customerName").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "sellerCode":
				JSONArray a32 = (JSONArray) obj;
				for (int i = 0; i < a32.size(); i++) {
					a32.get(i);
					JSONObject obj1 = (JSONObject) a32.get(i);
					Valuee = obj1.get("sellerCode").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "effectiveDate":
				JSONArray a33 = (JSONArray) obj;
				for (int i = 0; i < a33.size(); i++) {
					a33.get(i);
					JSONObject obj1 = (JSONObject) a33.get(i);
					Valuee = obj1.get("effectiveDate").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "endDate":
				JSONArray a34 = (JSONArray) obj;
				for (int i = 0; i < a34.size(); i++) {
					a34.get(i);
					JSONObject obj1 = (JSONObject) a34.get(i);
					Valuee = obj1.get("endDate").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField1":
				JSONArray a35 = (JSONArray) obj;
				for (int i = 0; i < a35.size(); i++) {
					a35.get(i);
					JSONObject obj1 = (JSONObject) a35.get(i);
					Valuee = obj1.get("customField1").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField2":
				JSONArray a36 = (JSONArray) obj;
				for (int i = 0; i < a36.size(); i++) {
					a36.get(i);
					JSONObject obj1 = (JSONObject) a36.get(i);
					Valuee = obj1.get("customField2").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField3":
				JSONArray a37 = (JSONArray) obj;
				for (int i = 0; i < a37.size(); i++) {
					a37.get(i);
					JSONObject obj1 = (JSONObject) a37.get(i);
					Valuee = obj1.get("customField3").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField4":
				JSONArray a38 = (JSONArray) obj;
				for (int i = 0; i < a38.size(); i++) {
					a38.get(i);
					JSONObject obj1 = (JSONObject) a38.get(i);
					Valuee = obj1.get("customField4").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField5":
				JSONArray a39 = (JSONArray) obj;
				for (int i = 0; i < a39.size(); i++) {
					a39.get(i);
					JSONObject obj1 = (JSONObject) a39.get(i);
					Valuee = obj1.get("customField5").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField6":
				JSONArray a40 = (JSONArray) obj;
				for (int i = 0; i < a40.size(); i++) {
					a40.get(i);
					JSONObject obj1 = (JSONObject) a40.get(i);
					Valuee = obj1.get("customField6").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField7":
				JSONArray a41 = (JSONArray) obj;
				for (int i = 0; i < a41.size(); i++) {
					a41.get(i);
					JSONObject obj1 = (JSONObject) a41.get(i);
					Valuee = obj1.get("customField7").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField8":
				JSONArray a42 = (JSONArray) obj;
				for (int i = 0; i < a42.size(); i++) {
					a42.get(i);
					JSONObject obj1 = (JSONObject) a42.get(i);
					Valuee = obj1.get("customField8").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField9":
				JSONArray a43 = (JSONArray) obj;
				for (int i = 0; i < a43.size(); i++) {
					a43.get(i);
					JSONObject obj1 = (JSONObject) a43.get(i);
					Valuee = obj1.get("customField9").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField10":
				JSONArray a44 = (JSONArray) obj;
				for (int i = 0; i < a44.size(); i++) {
					a44.get(i);
					JSONObject obj1 = (JSONObject) a44.get(i);
					Valuee = obj1.get("customField10").toString();
					System.out.println(Valuee);
					break;
				}
				break;

			case "code":
				JSONArray a21 = (JSONArray) obj;
				for (int i = 0; i < a21.size(); i++) {
					a21.get(i);
					JSONObject obj1 = (JSONObject) a21.get(i);
					Valuee = obj1.get("code").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "id":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Valuee = obj1.get(ObjectName).toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "accountName":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Valuee = obj3.get(ObjectName).toString();
				}
				break;
			case "accountId":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Valuee = obj3.get(ObjectName).toString();
				}
				break;
			case "name":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get(ObjectName).toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("id").toString();
						break;
					}
				}
				break;
			case "productName":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get(ObjectName).toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("productId").toString();
						break;
					}
				}
				break;

			case "filter":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get(ObjectName).toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("id").toString();
						break;
					}
				}
				break;
			case "modelId":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get(ObjectName).toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("id").toString();
						break;
					}
				}
				break;
			case "instanceId":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get(ObjectName).toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get(ObjectName).toString();
						break;
					}
				}
				break;
			case "statusLabel":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get(ObjectName).toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("status").toString();
						break;
					}
				}
				break;
			case "description":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get(ObjectName).toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("id").toString();
						break;
					}
				}
				break;
			case "users":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get(ObjectName).toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("id").toString();
						break;
					}
				}
				break;
			case "roleType":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get(ObjectName).toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("roleId").toString();
						break;
					}
				}
				break;
			case "roletypeid":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get("roleType").toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("id").toString();
						break;
					}
				}
				break;
			case "displayName":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get("displayName").toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("id").toString();
						break;
					}
				}
				break;
			case "documentName":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj3 = (JSONObject) array.get(i);
					Value = obj3.get(ObjectName).toString();
					if (Value.equals(Name)) {
						JSONObject obj11 = (JSONObject) array.get(i);
						Valuee = obj11.get("id").toString();
						break;
					}
				}
				break;
			}
		} catch (Exception e) {
			Value = e.getMessage();
		}
		return Valuee;
	}

	@SuppressWarnings("unused")
	public String xReadJSONArraygetcustomefield3(String JSONfilepath, String ObjectName, String Name) throws Exception {
		JSONParser parser = new JSONParser();
		String Valuee = null;

		try {
			Object obj = parser.parse(new FileReader(JSONfilepath));
			switch (ObjectName) {
			case "status":
				JSONArray a2 = (JSONArray) obj;
				for (int i = 0; i < a2.size(); i++) {
					a2.get(i);
					JSONObject obj1 = (JSONObject) a2.get(i);
					Valuee = obj1.get("status").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "certificateId":
				JSONArray a3 = (JSONArray) obj;
				for (int i = 0; i < a3.size(); i++) {
					a3.get(i);
					JSONObject obj1 = (JSONObject) a3.get(i);
					Valuee = obj1.get("certificateId").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "uuid":
				JSONArray a4 = (JSONArray) obj;
				for (int i = 0; i < a4.size(); i++) {
					a4.get(i);
					JSONObject obj1 = (JSONObject) a4.get(i);
					Valuee = obj1.get("uuid").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "seller":
				JSONArray a5 = (JSONArray) obj;
				for (int i = 0; i < a5.size(); i++) {
					a5.get(i);
					JSONObject obj1 = (JSONObject) a5.get(i);
					Valuee = obj1.get("seller").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "certificateType":
				JSONArray a6 = (JSONArray) obj;
				for (int i = 0; i < a6.size(); i++) {
					a6.get(i);
					JSONObject obj1 = (JSONObject) a6.get(i);
					Valuee = obj1.get("certificateType").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customerName":
				JSONArray a7 = (JSONArray) obj;
				for (int i = 0; i < a7.size(); i++) {
					a7.get(i);
					JSONObject obj1 = (JSONObject) a7.get(i);
					Valuee = obj1.get("customerName").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "jurisdictions":
				JSONArray a8 = (JSONArray) obj;
				for (int i = 0; i < a8.size(); i++) {
					a8.get(i);
					JSONObject obj1 = (JSONObject) a8.get(i);
					Valuee = obj1.get("jurisdictions").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "expirationDate":
				JSONArray a9 = (JSONArray) obj;
				for (int i = 0; i < a9.size(); i++) {
					a9.get(i);
					JSONObject obj1 = (JSONObject) a9.get(i);
					Valuee = obj1.get("expirationDate").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "clientCode":
				JSONArray a10 = (JSONArray) obj;
				for (int i = 0; i < a10.size(); i++) {
					a10.get(i);
					JSONObject obj1 = (JSONObject) a10.get(i);
					Valuee = obj1.get("clientCode").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customerCode":
				JSONArray a11 = (JSONArray) obj;
				for (int i = 0; i < a11.size(); i++) {
					a11.get(i);
					JSONObject obj1 = (JSONObject) a11.get(i);
					Valuee = obj1.get("customerCode").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "certificateScope":
				JSONArray a12 = (JSONArray) obj;
				for (int i = 0; i < a12.size(); i++) {
					a12.get(i);
					JSONObject obj1 = (JSONObject) a12.get(i);
					Valuee = obj1.get("certificateScope").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "certificateEffDate":
				JSONArray a13 = (JSONArray) obj;
				for (int i = 0; i < a13.size(); i++) {
					a13.get(i);
					JSONObject obj1 = (JSONObject) a13.get(i);
					Valuee = obj1.get("certificateEffDate").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "certificateEndDate":
				JSONArray a14 = (JSONArray) obj;
				for (int i = 0; i < a14.size(); i++) {
					a14.get(i);
					JSONObject obj1 = (JSONObject) a14.get(i);
					Valuee = obj1.get("certificateEndDate").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField1Value":
				JSONArray a15 = (JSONArray) obj;
				for (int i = 0; i < a15.size(); i++) {
					a15.get(i);
					JSONObject obj1 = (JSONObject) a15.get(i);
					Valuee = obj1.get("customField1Value").toString();
					System.out.println(Valuee);
					break;
				}
				break;

			case "customField2Value":
				JSONArray a16 = (JSONArray) obj;
				for (int i = 0; i < a16.size(); i++) {
					a16.get(i);
					JSONObject obj1 = (JSONObject) a16.get(i);
					Valuee = obj1.get("customField2Value").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField3Value":
				JSONArray a17 = (JSONArray) obj;
				for (int i = 0; i < a17.size(); i++) {
					a17.get(i);
					JSONObject obj1 = (JSONObject) a17.get(i);
					Valuee = obj1.get("customField3Value").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField4Value":
				JSONArray a18 = (JSONArray) obj;
				for (int i = 0; i < a18.size(); i++) {
					a18.get(i);
					JSONObject obj1 = (JSONObject) a18.get(i);
					Valuee = obj1.get("customField4Value").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField5Value":
				JSONArray a19 = (JSONArray) obj;
				for (int i = 0; i < a19.size(); i++) {
					a19.get(i);
					JSONObject obj1 = (JSONObject) a19.get(i);
					Valuee = obj1.get("customField5Value").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField6Value":
				JSONArray a20 = (JSONArray) obj;
				for (int i = 0; i < a20.size(); i++) {
					a20.get(i);
					JSONObject obj1 = (JSONObject) a20.get(i);
					Valuee = obj1.get("customField6Value").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField7Value":
				JSONArray a21 = (JSONArray) obj;
				for (int i = 0; i < a21.size(); i++) {
					a21.get(i);
					JSONObject obj1 = (JSONObject) a21.get(i);
					Valuee = obj1.get("customField7Value").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField8Value":
				JSONArray a22 = (JSONArray) obj;
				for (int i = 0; i < a22.size(); i++) {
					a22.get(i);
					JSONObject obj1 = (JSONObject) a22.get(i);
					Valuee = obj1.get("customField8Value").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField9Value":
				JSONArray a23 = (JSONArray) obj;
				for (int i = 0; i < a23.size(); i++) {
					a23.get(i);
					JSONObject obj1 = (JSONObject) a23.get(i);
					Valuee = obj1.get("customField9Value").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "customField10Value":
				JSONArray a24 = (JSONArray) obj;
				for (int i = 0; i < a24.size(); i++) {
					a24.get(i);
					JSONObject obj1 = (JSONObject) a24.get(i);
					Valuee = obj1.get("customField10Value").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "partyDetailEffDate":
				JSONArray a25 = (JSONArray) obj;
				for (int i = 0; i < a25.size(); i++) {
					a25.get(i);
					JSONObject obj1 = (JSONObject) a25.get(i);
					Valuee = obj1.get("partyDetailEffDate").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "partyDetailEndDate":
				JSONArray a26 = (JSONArray) obj;
				for (int i = 0; i < a26.size(); i++) {
					a26.get(i);
					JSONObject obj1 = (JSONObject) a26.get(i);
					Valuee = obj1.get("partyDetailEndDate").toString();
					System.out.println(Valuee);
					break;
				}
				break;
			case "reason":
				JSONArray a27 = (JSONArray) obj;
				for (int i = 0; i < a27.size(); i++) {
					a27.get(i);
					JSONObject obj1 = (JSONObject) a27.get(i);
					Valuee = obj1.get("reason").toString();
					System.out.println(Valuee);
					break;
				}
				break;

			}

		} catch (Exception e) {
			e.getMessage();
		}
		return Valuee;
	}

	public String xReadJSONTaxpayerArray(String JSONfilepath, String ObjectName, String compcode) throws Exception {
		JSONParser parser = new JSONParser();
		String Value = null;
		String Valuee = null;
		try {
			Object obj = parser.parse(new FileReader(JSONfilepath));
			JSONArray array = (JSONArray) obj;
			switch (ObjectName) {
			case "companyCode":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("companyCode").toString();
					if (Value.contains(compcode)) {
						Valuee = obj1.get("companyCode").toString();
					}
				}
				break;
			case "divisionCode":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("companyCode").toString();
					if (Value.contains(compcode)) {
						Valuee = obj1.get("divisionCode").toString();
					}
				}
				break;
			case "departmentCode":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("companyCode").toString();
					if (Value.contains(compcode)) {
						Valuee = obj1.get("departmentCode").toString();
					}
				}
				break;
			case "partitionName":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("companyCode").toString();
					if (Value.contains(compcode)) {
						Valuee = obj1.get("partitionName").toString();
					}
				}
				break;
			case "Taxpayeruuid":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("companyCode").toString();
					if (Value.contains(compcode)) {
						Valuee = obj1.get("uuid").toString();
					}
				}
				break;
			case "syncId":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("syncId").toString();
					if (Value.contains(compcode)) {
						Valuee = obj1.get("syncId").toString();
					}
				}
				break;
			case "uuid":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("syncId").toString();
					if (Value.contains(compcode)) {
						Valuee = obj1.get("uuid").toString();
					}
				}
				break;
			case "type":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("syncId").toString();
					if (Value.contains(compcode)) {
						Valuee = obj1.get("type").toString();
					}
				}
				break;
			case "customeruuid":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Value = obj1.get("uuid").toString();
					if (Value.contains(compcode)) {
						Valuee = obj1.get("uuid").toString();
					}
				}
				break;
			case "customersyncid":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj2 = (JSONObject) array.get(i);
					Value = obj2.get("uuid").toString();
					if (Value.contains(compcode)) {
						Valuee = obj2.get("syncId").toString();
					}
				}
				break;
			case "syncidtype":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj2 = (JSONObject) array.get(i);
					Value = obj2.get("uuid").toString();
					if (Value.contains(compcode)) {
						Valuee = obj2.get("type").toString();
					}
				}
				break;
			}
		} catch (Exception e) {
			Value = e.getMessage();
		}
		return Valuee;
	}

	public String xReadJSONArrays(String JSONfilepath, String ObjectName, String Name, String sourcetaxPayerId)
			throws Exception {
		JSONParser parser = new JSONParser();
		String Valuee = null;
		try {
			Object obj = parser.parse(new FileReader(JSONfilepath));
			JSONArray array = (JSONArray) obj;
			switch (ObjectName) {
			case "id":
				for (int i = 0; i < array.size(); i++) {
					array.get(i);
					JSONObject obj1 = (JSONObject) array.get(i);
					Valuee = obj1.get(ObjectName).toString();
					if (!sourcetaxPayerId.equalsIgnoreCase(Valuee)) {
						testinstanceId = Valuee;
					}
				}
				break;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return testinstanceId;
	}

	public String xReadTaxpayerID(String JSONfilepath, String ObjectName1, String ObjectName2, String Companyname)
			throws Exception {
		JSONParser parser = new JSONParser();
		String Cvalue = null;
		String IdValuee = null;
		try {
			Object obj = parser.parse(new FileReader(JSONfilepath));
			JSONArray array = (JSONArray) obj;
			for (int j = 0; j < array.size(); j++) {
				switch (ObjectName1) {
				case "id":
					for (int i = 0; i < array.size(); i++) {
						array.get(i);
						JSONObject obj1 = (JSONObject) array.get(i);
						IdValuee = obj1.get(ObjectName1).toString();
					}
					break;
				}
				switch (ObjectName2) {
				case "name":
					for (int i = 0; i < array.size(); i++) {
						array.get(i);
						JSONObject obj1 = (JSONObject) array.get(i);
						Cvalue = obj1.get(ObjectName2).toString();
					}
					break;

				}
				if (Cvalue.equalsIgnoreCase(Companyname)) {
					testinstanceId = IdValuee;
					System.out.println("Test ID is =" + testinstanceId);
				}

			}

		} catch (Exception e) {
			testinstanceId = e.getMessage();
		}
		return testinstanceId;

	}

	public String LatestDownloadedFile(String FolderName, boolean isFromChrome) throws Exception {
		try {

			if (!isFromChrome) {
				File directory = new File(".//");
				String DownloadFilePath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
				File dir = new File(DownloadFilePath);
				File[] files = dir.listFiles();
				Arrays.sort(files);
				Arrays.sort(files, new Comparator<Object>() {
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
				Arrays.sort(files, new Comparator<Object>() {
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

	public static String xCSVtoTXT(String FileName) {
		try {
			// FileWriter writer=null;
			String csv = null;
			String Temp = "";
			File file = new File(FileName);
			System.out.println(file);
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				csv = scan.nextLine().replace("|", " ");
				if (csv.contains(".00")) {
					csv = csv.replace(".00", ".0");

				}
				if (csv.contains(".0")) {
					csv = csv.replace(".0", "");

				}
				if (csv.contains("([ ]*+[1-9]++[ ]*+)+"))

					Temp = Temp + csv;
			}
			FileName = Temp;
			System.out.println(FileName);
		} catch (Exception e) {
			return e.getMessage();
		}
		return FileName;

	}

	public static String xCSVUDateInputUpdation(String FileName) throws Exception {
		try {
			CSVReader csvReader = new CSVReader(new FileReader(FileName));
			String csv = "";
			String[] row = null;
			String ModifiedDate = null;
			List<String[]> content = csvReader.readAll();
			for (Object object : content) {
				row = (String[]) object;
				for (int i = 0; i < row.length; i++) {
					FileName = row[i] + ",";
					@SuppressWarnings("resource")
					Scanner scan = new Scanner(FileName);
					while (scan.hasNext()) {
						String Temp = scan.nextLine().replace("|", " ");
						Temp = Temp.substring(Temp.length() - Temp.length(), Temp.length() - 1);
						if (Temp.contains("TESTMONTH") || Temp.contains("CURRENTMONTH") || Temp.contains("LIVEMONTH")
								|| Temp.contains("COMPSTDATE") || Temp.contains("COMPSTDATESPL")
								|| Temp.contains("YRSTDAY") || Temp.contains("COMPSTDAY") || Temp.contains("WEEKDAY")
								|| Temp.contains("ENDCERTPTMONTH")) {
							ModifiedDate = xModified(ModifiedDate, Temp);
							Temp = Temp.replace(Temp, ModifiedDate);
						}
						csv = csv + Temp + ",";

					}
				}

			}
			FileName = csv;
			System.out.println(FileName);
			csvReader.close();

		}

		catch (Exception e) {
			return e.getMessage();
		}
		return FileName;
	}

	/**
	 * it will copy the original file and save into the tempfile, update the temp
	 * file dates based keyword provided in the sheet venkat kandula param
	 * Originalfilepath param tempfilepath
	 * 
	 * @return
	 */
	public String AdjustmentFiledateupdate(String Originalfilepath, String tempfilepath) throws Exception {
		File directory = new File(".//");
		try {
			Runtime.getRuntime().exec("wscript.exe " + directory.getCanonicalPath() + "\\UpdateAdjustmentfileDates.vbs "
					+ Originalfilepath + " " + tempfilepath);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Pass";
	}

	public void writeJsonFile(String jsonRespose, String folderName, String filename) {
		try {

			File directory = new File(folderName);
			File file = new File(directory.getCanonicalPath() + "\\" + filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(jsonRespose);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeCSVFile(String csvResponse, String folderName, String filename) {
		try {

			File directory = new File(folderName);
			File file = new File(directory.getCanonicalPath() + "\\" + filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(csvResponse);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Compare the two csv files row and column based and write the mismatch content
	 * into the new csv file venkat kandula param File1,File2, csvstatusfile
	 * 
	 * @return
	 */
	@SuppressWarnings({ "resource" })
	public String CreatecsvComparisonfile(String File1, String File2, String csvstatusfile) throws Exception {
		String status = "";
		PrintWriter pw = null;
		StringBuilder sb = null;
		try {
			pw = new PrintWriter(new File(csvstatusfile));
			sb = new StringBuilder();
			sb.append("Linenumber");
			sb.append(",");
			sb.append("Fieldname");
			sb.append(",");
			sb.append("Actual_value");
			sb.append(",");
			sb.append("Expected_value");
			sb.append(",");
			CSVReader reader1 = new CSVReader(new FileReader(File1));
			CSVReader reader2 = new CSVReader(new FileReader(File2));
			ArrayList<ArrayList<String>> array1 = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> array2 = new ArrayList<ArrayList<String>>();
			String[] nextLine1;
			while ((nextLine1 = reader1.readNext()) != null) {
				ArrayList<String> list1 = new ArrayList<String>();
				for (int i = 0; i < nextLine1.length; i++) {
					list1.add(nextLine1[i]);
				}
				array1.add(list1);
			}
			String[] nextLine2;
			while ((nextLine2 = reader2.readNext()) != null) {
				ArrayList<String> list2 = new ArrayList<String>();
				for (int i = 0; i < nextLine2.length; i++) {
					list2.add(nextLine2[i]);
				}
				array2.add(list2);
			}
			// Nested loop to display results
			int arraysize = 0;
			int arraycolumnsize = 0;
			int filesize1 = array1.size();
			int filesize2 = array2.size();
			if (filesize1 > filesize2) {
				arraysize = filesize1;
			} else if (filesize1 < filesize2) {
				arraysize = filesize2;
			} else {
				arraysize = filesize1;
			}
			for (int rf1 = 0; rf1 < arraysize; rf1++) {

				int filesizec1 = array1.get(rf1).size();
				int filesizec2 = array2.get(rf1).size();
				if (filesizec1 > filesizec2) {
					arraycolumnsize = filesizec1;
				} else if (filesizec1 < filesizec2) {
					arraycolumnsize = filesizec2;
				} else {

					arraycolumnsize = filesizec1;
				}
				for (int cf1 = 0; cf1 < arraycolumnsize; cf1++) {
					String file1content = null;
					String file2content = null;
					file1content = array1.get(rf1).get(cf1);
					file2content = array2.get(rf1).get(cf1);
					if (!file1content.equalsIgnoreCase(file2content)) {
						sb.append("\n");
						String fieldname = array1.get(0).get(cf1);
						String file1 = file1content;
						String file2 = file2content;
						sb.append(rf1 + 1);
						sb.append(",");
						sb.append(fieldname);
						sb.append(",");
						sb.append(file1);
						sb.append(",");
						sb.append(file2);
						status = "";
						status = (file1content.equalsIgnoreCase(file2content)) == true ? status
								: status + "Fail, Check the difference in the CSV file:" + csvstatusfile + ";";
					}
				}
			}
			pw.write(sb.toString());
			pw.close();
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;
	}

	public static class CreateZipFolder {

		public static List<String> fileList = new ArrayList<>();

		public static void compressDirectory(String dir, String zipFile) {
			File directory = new File(dir);
			getFileList(directory);

			try {
				File[] files = directory.listFiles();
				if (files != null && files.length > 0) {
					if (null != fileList && !fileList.isEmpty()) {
						FileOutputStream fos = new FileOutputStream(zipFile);
						ZipOutputStream zos = new ZipOutputStream(fos);

						for (String filePath : fileList) {
							System.out.println("Compressing: " + filePath);
							// Creates a zip entry.
							String name = filePath.substring(directory.getAbsolutePath().length() + 1,
									filePath.length());
							ZipEntry zipEntry = new ZipEntry(name);

							zos.putNextEntry(zipEntry);
							// Read file content and write to zip output stream.
							FileInputStream fis = new FileInputStream(filePath);
							byte[] buffer = new byte[1024];
							int length;
							while ((length = fis.read(buffer)) > 0) {
								zos.write(buffer, 0, length);
							}
							// Close the zip entry and the file input stream.
							zos.closeEntry();
							fis.close();
						}
						zos.close();
						fos.close();
					}

					// Close zip output stream and file output stream. This will
					// complete the compression process.

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Get files list from the directory recursive to the sub directory.
		 */

		public static void getFileList(File directory) {
			try {
				File[] files = directory.listFiles();
				if (files != null && files.length > 0) {
					for (File Folder : files) {
						if (Folder.isDirectory() && !Folder.getName().contains(".zip")) {
							if (null != Folder.listFiles() && Folder.listFiles().length > 0) {
								for (File File : Folder.listFiles()) {
									if (File.isDirectory()) {
										for (File SF : File.listFiles()) {
											if (SF.isDirectory()) {
												for (File SFF : SF.listFiles()) {
													if (SFF.isDirectory()) {
														for (File SFFF : SFF.listFiles()) {
															if (SFFF.isDirectory()) {
																for (File SFFFF : SFFF.listFiles()) {
																	fileList.add(SFFFF.getAbsolutePath());
																}
															} else {
																fileList.add(SFFF.getAbsolutePath());
															}
														}
													} else {
														fileList.add(SFF.getAbsolutePath());
													}
												}
											} else {
												fileList.add(SF.getAbsolutePath());
											}
										}
									} else {
										fileList.add(File.getAbsolutePath());
									}
								}
							}
							// else{fileList.add(Folder.getAbsolutePath());}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static class DeleteFolder {

		public static void deleteFile(File Dir) {

			if (Dir.isDirectory()) {
				for (File Folders : Dir.listFiles()) {
					if (!Folders.getName().contains(".zip")) {
						if (Folders.isFile()) {
							Folders.delete();
							System.out.println("Deleting:" + Folders);
						} else {
							for (File subFolders : Folders.listFiles()) {
								if (subFolders.isDirectory()) {
									for (File SF : subFolders.listFiles()) {
										if (SF.isDirectory()) {
											for (File SFF : SF.listFiles()) {
												if (SFF.isDirectory()) {
													for (File SFFF : SFF.listFiles()) {
														SFFF.delete();
														System.out.println("Deleting:" + SFFF);
													}
												}
												SFF.delete();
												System.out.println("Deleting:" + SFF);
											}
										}
										SF.delete();
										System.out.println("Deleting:" + SF);
									}
								}
								subFolders.delete();
								System.out.println("Deleting:" + subFolders);
							}
							Folders.delete();
							System.out.println("Deleting:" + Folders);
						}
					}
				}
			}
		}
	}

	public static String discrepancy(String ActualPath, String Expectedpath) throws IOException, InterruptedException {
		String status = "";
		try {
			File file = new File(".//");
			String Nf = file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\";
			File Actual = new File(ActualPath);
			File Expected = new File(Expectedpath);
			int numberOfSubfoldersAc = 0;
			File listActual[] = Actual.listFiles();
			List<String> ListAc = new ArrayList<String>();
			// String[] ListAc={"abc"};
			for (int i = 0; i < listActual.length; i++) {
				if (listActual[i].isDirectory()) {
					ListAc.add(listActual[i].getName());
					// System.out.println(listActual[i].getName());
					numberOfSubfoldersAc++;
				}
			}
			// System.out.println("No of dir " + numberOfSubfoldersAc);
			int numberOfSubfoldersEX = 0;
			File listExpected[] = Expected.listFiles();
			List<String> ListEx = new ArrayList<String>();
			// String[] ListEx={"abc"};
			for (int i = 0; i < listExpected.length; i++) {
				if (listExpected[i].isDirectory()) {
					ListEx.add(listExpected[i].getName());
					// ListEx[i]=listExpected[i].getName();
					numberOfSubfoldersEX++;
				}
			}
			// System.out.println("No of dir " + numberOfSubfoldersEX);
			PrintWriter Fr;
			PrintWriter Fr1;
			// Create discrepancy report in write mode
			Fr1 = new PrintWriter(new FileOutputStream((Nf + "Discrepancy Report" + ".csv"), true));
			// int mid=0;
			StringBuilder Freport = new StringBuilder();
			// below are the headings of discrepancy report
			Freport.append("Filename");
			Freport.append(",");
			Freport.append("Document number");
			Freport.append(",");
			Freport.append("Columns");
			Freport.append(",");
			Freport.append("Actual_value");
			Freport.append(",");
			Freport.append("Expected_value");
			Freport.append("\n");
			Fr1.write(Freport.toString());
			Fr1.close();// mentioned heading and closed it
			// int ac=0;int ex=0;int mn=0;
			int g = 0;
			int en = 0;
			String name_ORG1 = null;
			String name_ORG2 = null;
			String name_SUCC1 = null;
			String name_SUCC2 = null;
			String name_ERR1 = null;
			String name_ERR2 = null;
			int i = 0;
			// cntr=0;
			int st = 0;
			String cs = null;
			String ce = null;
			for (st = 0; st < numberOfSubfoldersAc; st++) {
				int f = 0;
				g = 0;

				for (en = 0; en < numberOfSubfoldersEX; en++) {
					if (ListAc.get(st).equalsIgnoreCase(ListEx.get(en))) {
						// System.out.println(ListAc.get(st));
						// System.out.println(ListEx.get(en));
						f++;
						List<String> list_org = listlength(ActualPath + ListAc.get(st) + "\\OriginalFiles\\");
						List<String> list_orgEX = listlength(Expectedpath + ListEx.get(en) + "\\OriginalFiles\\");
						i = list_org.size();
						int k = list_orgEX.size();
						String str2 = null;
						String str1 = null;
						String c = null;
						String Expected_file = null;
						int cnt = 0;
						String ext = ".csv";
						String actual_file = null;
						for (int cntr1 = 0; cntr1 < i; cntr1++) {
							cnt = 0;
							name_ORG1 = list_org.get(cntr1);
							String t = (name_ORG1.substring(name_ORG1.length() - 4));
							for (int j = 0; j < k; j++) {
								if (t.equalsIgnoreCase(ext)) {
									str1 = name_ORG1.replaceAll("[\\-\\_\\s+]", "");
									name_ORG2 = list_orgEX.get(j);
									String t1 = (name_ORG2.substring(name_ORG2.length() - 4));
									// String t1=(name_ORG2.substring(name_ORG2.length()-4));
									str2 = name_ORG2.replaceAll("[\\-\\_\\s+]", "");

									if (t1.equalsIgnoreCase(ext)) {
										if (str1.equalsIgnoreCase(str2)) {
											cnt = 1;
											String ex_f = Expectedpath + ListEx.get(en) + "\\OriginalFiles\\"
													+ name_ORG2;
											Expected_file = xCSVUDateUpdation(ex_f);
											// System.out.println(Expected_file);
											String a_f = ActualPath + ListAc.get(st) + "\\OriginalFiles\\" + name_ORG1;
											actual_file = xCSVUDateUpdation(a_f);
											c = csvFilesCompare(actual_file, Expected_file, name_ORG1, 0);
											/*
											 * if(c=="Matched") { CallMNM(name_ORG1,c,"Matched"); }
											 */
											if (c != "Matched") {
												// String c1=csvFilesCompare(actual_file,Expected_file,name_ORG1,0);
												// CallMNM(name_ORG1,c1,"NotMatched");
												Fr = new PrintWriter(new FileOutputStream(
														(Nf + "Discrepancy Report" + ".csv"), true));
												StringBuilder Freport1 = new StringBuilder();
												Freport1.append(name_ORG1 + "," + "," + ","
														+ "Original Files not Matched" + "," + "\n");
												Fr.write(Freport1.toString());
												Fr.close();
												CallMNM(name_ORG1, c, "NotMatched");
											}

											Expected_file = null;
											actual_file = null;
											// ******************success
											// starts*****************************************/
											String succ1 = name_ORG1.substring(0, (name_ORG1.length() - 4))
													+ "_suc.csv";
											name_SUCC1 = ActualPath + ListAc.get(st) + "\\" + "SuccessFiles\\"
													+ name_ORG1.substring(0, (name_ORG1.length() - 4)) + "_suc.csv";
											File f6 = new File(ActualPath + ListAc.get(st) + "\\" + "SuccessFiles\\"
													+ name_ORG1.substring(0, (name_ORG1.length() - 4)) + "_suc.csv");
											if (f6.exists()) {
												String succ2 = name_ORG2.substring(0, (name_ORG2.length() - 4))
														+ "_suc.csv";
												name_SUCC2 = Expectedpath + ListEx.get(en) + "\\SuccessFiles\\"
														+ name_ORG2.substring(0, (name_ORG2.length() - 4)) + "_suc.csv";
												File f3 = new File(name_SUCC2);
												if (f3.exists()) {
													// Wfile="Success";
													// Expected_file=null;
													Expected_file = xCSVUDateUpdation(name_SUCC2);

													actual_file = xCSVUDateUpdation(name_SUCC1);
													cs = csvFilesCompare(actual_file, Expected_file, succ1, 1);

													// call method for actual success and expected success
													/*
													 * if(cs=="Matched") { CallMNM(succ1,cs,"Matched"); }
													 */
													if (cs != "Matched") {
														CallMNM(succ1, cs, "NotMatched");
													}
												} else if (!f3.exists())// if file is not available in Expected success
												{
													Fr = new PrintWriter(new FileOutputStream(
															(Nf + "Discrepancy Report" + ".csv"), true));
													StringBuilder Freport1 = new StringBuilder();
													Freport1.append(succ2 + "," + "," + ","
															+ "File missing in Expected Success folder" + "," + "\n");
													Fr.write(Freport1.toString());
													Fr.close();
												}
											}

											// Expected_file=null;
											// **********success ends***********************//

											// ******************error starts*****************************************/
											String err1 = name_ORG1.substring(0, (name_ORG1.length() - 4)) + "_err.csv";
											name_ERR1 = ActualPath + ListAc.get(st) + "\\" + "ErrorFiles\\"
													+ name_ORG1.substring(0, (name_ORG1.length() - 4)) + "_err.csv";
											File f1 = new File(ActualPath + ListAc.get(st) + "\\" + "ErrorFiles\\"
													+ name_ORG1.substring(0, (name_ORG1.length() - 4)) + "_err.csv");

											if (f1.exists()) {
												String err2 = name_ORG2.substring(0, (name_ORG2.length() - 4))
														+ "_err.csv";
												name_ERR2 = Expectedpath + ListEx.get(en) + "\\ErrorFiles\\"
														+ name_ORG2.substring(0, (name_ORG2.length() - 4)) + "_err.csv";
												File f2 = new File(name_ERR2);
												if (f2.exists()) {
													// Wfile="Error";
													Expected_file = xCSVUDateUpdation(name_ERR2);
													actual_file = xCSVUDateUpdation(name_ERR1);
													// System.out.println(name_ERR1+"and"+Expected_file+"and"+Expectedpath);
													ce = csvFilesCompare(actual_file, Expected_file, err1, 1);
													/*
													 * if(ce=="Matched") { CallMNM(err1,ce,"Matched"); }
													 */
													if (ce != "Matched") {
														CallMNM(err1, ce, "NotMatched");
													}
												}

												else if (!f2.exists())// if file is not available in Expected error
												// folder
												{
													Fr = new PrintWriter(new FileOutputStream(
															(Nf + "Discrepancy Report" + ".csv"), true));
													StringBuilder Freport1 = new StringBuilder();
													Freport1.append(err2 + "," + "," + ","
															+ "File missing in Expected Error folder" + "," + "\n");
													Fr.write(Freport1.toString());
													Fr.close();
												}
											}

										}
									}
								}
							}
							ext = ".csv";
							if (cnt == 0)// if file is not available in expected original folder after searching each
							// file name in original
							{
								if ((name_ORG1.substring(name_ORG1.length() - 4)).equalsIgnoreCase(ext)) {
									Fr = new PrintWriter(
											new FileOutputStream((Nf + "Discrepancy Report" + ".csv"), true));
									StringBuilder Freport1 = new StringBuilder();
									Freport1.append(name_ORG1 + "," + "," + ","
											+ "File missing in Expected original folder " + ",");
									Freport1.append("\n");
									Fr.write(Freport1.toString());
									Fr.close();

								}
							}
						}

					}

					// en=numberOfSubfoldersEX;

					else {
						g = st;
					}

				}
				// en=0;
				if ((f == 0) && (g == st)) {
					Fr = new PrintWriter(new FileOutputStream((Nf + "Discrepancy Report" + ".csv"), true));
					StringBuilder Freport1 = new StringBuilder();
					Freport1.append(ListAc.get(st) + "," + "," + "," + "Folder missing in Expected Folder" + ",");
					Freport1.append("\n");
					Fr.write(Freport1.toString());
					Fr.close();
				}
			}

			// *********************************************************************************************************************************************
			String ext = ".csv";
			// System.out.println(st+"and"+en);
			int f = 0;
			st = 0;
			en = 0;
			for (st = 0; st < numberOfSubfoldersEX; st++) {
				for (en = 0; en < numberOfSubfoldersAc; en++) {
					if (ListEx.get(st).equalsIgnoreCase(ListAc.get(en))) {
						f++;
						List<String> list_orgEX = listlength(Expectedpath + ListEx.get(st) + "\\OriginalFiles\\");
						List<String> list_org = listlength(ActualPath + ListAc.get(en) + "\\OriginalFiles\\");

						int k = list_orgEX.size();
						i = list_org.size();
						int cnt = 0;
						for (int cntr2 = 0; cntr2 < k; cntr2++)// k=number of files in expected original
						{
							cnt = 0;
							name_ORG2 = list_orgEX.get(cntr2);
							String t1 = (name_ORG2.substring(name_ORG2.length() - 4));
							String str2 = name_ORG2.replaceAll("[\\-\\_\\s+]", "");
							for (int j1 = 0; j1 < i; j1++)// i=number of files in Actual original
							{
								name_ORG1 = list_org.get(j1);
								String t = (name_ORG1.substring(name_ORG1.length() - 4));
								String str1 = name_ORG1.replaceAll("[\\-\\_\\s+]", "");
								if ((str2.equalsIgnoreCase(str1)) && (t.equalsIgnoreCase(t1))
										&& (t.equalsIgnoreCase(ext)) && (t1.equalsIgnoreCase(ext))) {
									cnt = 1;
								}
							}
							if ((cnt == 0) && (t1.equalsIgnoreCase(ext) == true))// if file is not available in Actual
							// original folder
							{
								Fr = new PrintWriter(new FileOutputStream((Nf + "Discrepancy Report" + ".csv"), true));
								StringBuilder Freport1 = new StringBuilder();
								Freport1.append(name_ORG2 + "," + "," + "," + "File missing in Actual original folder"
										+ "," + "\n");
								Fr.write(Freport1.toString());
								Fr.close();
							}
						}
						ext = "_suc.csv";
						// find missing files in actual success
						List<String> list_succ = listlength(ActualPath + ListAc.get(en) + "\\SuccessFiles\\");
						List<String> list_succEX = listlength(Expectedpath + ListEx.get(st) + "\\SuccessFiles\\");
						// System.out.println(st+"and"+en);
						i = list_succ.size();
						k = list_succEX.size();

						for (int cntr3 = 0; cntr3 < k; cntr3++)// k=number of files in expected success
						{
							cnt = 0;
							String name_succ2 = list_succEX.get(cntr3);
							String t1 = (name_succ2.substring(name_succ2.length() - 8));
							String str2 = name_succ2.replaceAll("[\\-\\_\\s+]", "");
							for (int j2 = 0; j2 < i; j2++)// i=number of files in Actual success
							{
								String name_succ1 = list_succ.get(j2);
								String t = (name_succ1.substring(name_succ1.length() - 8));
								String str1 = name_succ1.replaceAll("[\\-\\_\\s+]", "");
								if ((str1.equalsIgnoreCase(str2)) && (t.equalsIgnoreCase(t1))
										&& (t.equalsIgnoreCase(ext)) && (t1.equalsIgnoreCase(ext))) {
									cnt = 1;
								}
							}
							if ((cnt == 0) && (t1.equalsIgnoreCase(ext) == true))// if file is not available in Actual
							// original folder
							{
								Fr = new PrintWriter(new FileOutputStream((Nf + "Discrepancy Report" + ".csv"), true));
								StringBuilder Freport1 = new StringBuilder();
								Freport1.append(name_succ2 + "," + "," + "," + "File missing in Actual Success folder"
										+ "," + "\n");
								Fr.write(Freport1.toString());
								Fr.close();
							}
						}
						ext = "_err.csv";
						List<String> list_err = listlength(ActualPath + ListAc.get(en) + "\\ErrorFiles\\");
						List<String> list_errEX = listlength(Expectedpath + ListEx.get(st) + "\\ErrorFiles\\");
						// System.out.println(st+"and"+en);
						i = list_err.size();
						k = list_errEX.size();
						// cntr=0;
						for (int cntr4 = 0; cntr4 < k; cntr4++)// k=number of files in expected success
						{
							cnt = 0;
							String name_err2 = list_errEX.get(cntr4);
							String t1 = (name_err2.substring(name_err2.length() - 8));
							String str2 = name_err2.replaceAll("[\\-\\_\\s+]", "");
							for (int j6 = 0; j6 < i; j6++)// i=number of files in Actual success
							{
								String name_err1 = list_err.get(j6);
								String t = (name_err1.substring(name_err1.length() - 8));
								String str1 = name_err1.replaceAll("[\\-\\_\\s+]", "");
								if ((str1.equalsIgnoreCase(str2)) && (t.equalsIgnoreCase(t1))
										&& (t.equalsIgnoreCase(ext)) && (t1.equalsIgnoreCase(ext))) {
									cnt = 1;
								}
							}
							if ((cnt == 0) && (t1.equalsIgnoreCase(ext) == true))// if file is not available in Actual
							// original folder
							{
								Fr = new PrintWriter(new FileOutputStream((Nf + "Discrepancy Report" + ".csv"), true));
								StringBuilder Freport1 = new StringBuilder();
								Freport1.append(name_err2 + "," + "," + "," + "File missing in Actual Error folder"
										+ "," + "\n");
								Fr.write(Freport1.toString());
								Fr.close();
							}
						}
					} else {
						g = st;
					}

				}
				// en=0;
				if ((f == 0) && (g == st)) {
					Fr = new PrintWriter(new FileOutputStream((Nf + "Discrepancy Report" + ".csv"), true));
					StringBuilder Freport1 = new StringBuilder();
					Freport1.append(
							ListEx.get(st) + "," + "," + "," + "Folder missing in Expected Error folder" + "," + "\n");
					Fr.write(Freport1.toString());
					Fr.close();

				}
				g = 0;
				f = 0;
			}

		} catch (Exception e) {
			return e.getMessage();
		}
		return status;

	}

	public static String csvFilesCompare(String actualFile, String expectedFile, String Wfile, int w)
			throws IOException, InterruptedException {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int cntr = 0;
		// int min=0;
		CSVReader reader1 = new CSVReader(new FileReader(actualFile));
		CSVReader reader2 = new CSVReader(new FileReader(expectedFile));
		ArrayList<ArrayList<String>> array1 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> array2 = new ArrayList<ArrayList<String>>();
		String[] nextLine1;
		// String line=null;
		String fieldname = null;
		String file1 = null;
		String file2 = null;
		int r = 0;
		int r1 = 0;
		// ************read actual file*************
		while ((nextLine1 = reader1.readNext()) != null) {
			if (nextLine1.length > 0) {
				ArrayList<String> list1 = new ArrayList<String>();
				for (i = 0; i < nextLine1.length; i++) {
					list1.add(nextLine1[i].replaceAll("[\\,\\,]", ""));// adding each row of csv to array
				}
				array1.add(list1);// ******write to an array
				r++;
			} // System.out.println(i);
		}
		Thread.sleep(20);
		String[] nextLine2;
		// ************read Expected file*************

		while ((nextLine2 = reader2.readNext()) != null) {
			if (nextLine2.length > 0) {
				ArrayList<String> list2 = new ArrayList<String>();
				for (i = 0; i < nextLine2.length; i++) {
					list2.add(nextLine2[i].replaceAll("[\\,\\,]", ""));// adding each row of csv to collection array

				}
				r1++;
				array2.add(list2);// ******write to an array
			}
		}
		Thread.sleep(20);
		reader1.close();// closed csv actual
		reader2.close();// closed csv expected
		// int filesize1=array1.size();
		// System.out.println(filesize1);//size of actual array
		// int filesize2=array2.size();
		Thread.sleep(20);
		int filesizec1 = 0;
		int filesizec2 = 0;
		filesizec1 = array1.get(0).size();
		// System.out.println(filesizec1);//check for col size actaul file
		filesizec2 = array2.get(0).size();
		// System.out.println(filesizec2);//check for col size expected file
		Thread.sleep(20);
		int c2 = -1;
		int j = 0;
		if (array1.get(0).equals(array2.get(0)) != true)// check for 1st row of heading of both files equal or not
		{
			j = 0;
			int k = 0;
			for (i = 0; i < filesizec1;) {
				k = -1;
				c2 = 0;
				for (j = 0; j < filesizec2; j++) {
					if (array1.get(0).get(i).equalsIgnoreCase(array2.get(0).get(j)) != true) {
						k = i;
						// System.out.println(array1.get(0).get(i)+"and"+array2.get(0).get(j));
					} else {
						c2 = c2 + 1;
					}
				}
				if ((k == i) && (c2 == 0) && (!array1.get(0).get(k).isEmpty()))// if any column missing in expected
				{
					sb.append(Wfile);
					sb.append(",");
					sb.append("1");
					sb.append(",");
					sb.append(array1.get(0).get(i));
					// System.out.println(sb.append(array1.get(0).get(k)));
					sb.append(",");
					sb.append("Column Exists in Actual");
					sb.append(",");
					sb.append("Column Missing in Expected");
					sb.append("\n");
				}
				i++;
			}
			Thread.sleep(20);

			j = 0;
			c2 = -1;
			i = 0;
			for (i = 0; i < filesizec2;)// expected array columns
			{
				k = -1;
				c2 = 0;
				for (j = 0; j < filesizec1; j++)//// actual array columns
				{
					// System.out.println(array2.get(0).get(i));
					if (array2.get(0).get(i).equalsIgnoreCase(array1.get(0).get(j)) != true)// if any column missing in
					// expected
					{
						k = i;
					} else {
						c2 = c2 + 1;
						;
					}
				}
				if ((k == i) && (c2 == 0) && (!array2.get(0).get(k).isEmpty()))// if any column missing in Actual
				{
					sb.append(Wfile);
					sb.append(",");
					sb.append("1");
					sb.append(",");
					sb.append(array2.get(0).get(k));
					sb.append(",");
					sb.append("Column Exists in Expected");
					sb.append(",");
					sb.append("Column Missing in Actual ");
					sb.append("\n");
				}
				i++;
			}
		}
		int cf2 = 0;
		String file1content = null;
		String file2content = null;
		String file1colValue = null;
		String file2colValue = null;
		int k = 1;
		int rf1 = 1;
		// int rt=r;
		// int r1t=r1;
		int st = 0;
		// System.out.println(min+" "+arraysize);
		Thread.sleep(20);
		// System.out.println(r+"and"+r1);
		if ((array1.size() > 1) && (rf1 > 0)) {
			while ((r >= 1) && (rf1 < r) && (array1.size() > 1)) {
				file1content = null;
				file1content = array1.get(rf1).get(w);
				k = 1;
				st = 0;
				while ((r1 >= 1) && (k < r1) && (array2.size() > 1) && (r >= 1) && (st == 0))// starting from row1
				// ...row 0 already
				// verified for headings
				{
					file2content = null;
					file2content = array2.get(k).get(w);
					System.out.println(file2content);
					if ((file1content.equalsIgnoreCase(file2content) == true))// if document number /any col at that
					// place value in actual=value in
					// expected
					{

						st = 1;
						cf2 = 0;
						file1colValue = null;
						file2colValue = null;
						for (int cf1 = 0; cf1 < array1.get(rf1).size(); cf1++) // check for each field value
						{
							if (cf2 < array2.get(k).size()) // check for each field value
							{
								// System.out.println(array1.size()+"and"+rf1+"and"+cf1+"and"+Wfile);
								file1colValue = array1.get(rf1).get(cf1);
								file2colValue = array2.get(k).get(cf2);
								if (file1colValue.equalsIgnoreCase(file2colValue) != true) {
									cntr++;
									fieldname = array1.get(0).get(cf1);
									file1 = file1colValue;
									file2 = file2colValue;
									sb.append(Wfile);
									sb.append(",");
									sb.append(file1content);
									sb.append(",");
									sb.append(fieldname);
									sb.append(",");
									sb.append(file1);
									sb.append(",");
									sb.append(file2);
									sb.append("\n");
								}
							}
							cf2 = cf2 + 1;
						}
						// **********remove one row from both array1 and array2 if doc number/other val
						// matched***********//
						if (cf2 < filesizec2) {
							while (cf2 < filesizec2) {
								fieldname = array2.get(0).get(cf2);

								sb.append(Wfile);
								sb.append(",");
								sb.append(file2content);
								sb.append(",");
								sb.append(fieldname);
								sb.append(",");
								sb.append("Column value Missing in Actual file");
								sb.append(",");
								sb.append("Column value Exists in Expected file");
								sb.append("\n");
								cf2++;
							}
						}
					}
					if ((file1content.equalsIgnoreCase(file2content) != true)) {
						k++;
					}
				}
				if (st == 1) {
					array2.remove(k);
					r1 = array2.size();
					array1.remove(rf1);
					r = array1.size();
					rf1 = 1;
					k = 1;
				} else {
					rf1++;
				}
			}
		}
		int sz1 = 1;

		// **********if any value still left in array1*************************
		sz1 = 1;
		if (array1.size() > 1) {
			int sz = array1.size();
			// System.out.println(sz);
			for (sz1 = 1; sz1 < sz; sz1++) {
				cntr++;
				// cntr2++;
				sb.append(Wfile);
				sb.append(",");
				sb.append(array1.get(sz1).get(w));
				sb.append(",");
				sb.append(array1.get(0).get(w));
				sb.append(",");
				sb.append("Exists in Actual");
				sb.append(",");
				sb.append("Missing in Expected");
				sb.append("\n");
			}
		}
		// **********if any value still left in array2*************************

		sz1 = 1;
		int sz3 = array2.size();

		if (array2.size() > 1) {
			for (sz1 = 1; sz1 < sz3; sz1++) {
				cntr++;
				// cntr2++;
				sb.append(Wfile);
				sb.append(",");
				sb.append(array2.get(sz1).get(w));
				sb.append(",");
				sb.append(array2.get(0).get(w));
				sb.append(",");
				sb.append("Exists in Expected");
				sb.append(",");
				sb.append("Missing in Actual");
				sb.append("\n");
			}
		}
		Thread.sleep(20);
		String fs = null;
		if (cntr == 0)// if no mismatch cntr=0
		{
			fs = "Matched";
			// sb.append("Matched");
		} else if (cntr > 0) {
			fs = sb.toString();
			// s=fs;
		}
		return fs;
	}

	public static List<String> listlength(String folder) throws IOException {

		List<String> list = new ArrayList<String>();
		// System.out.println(folder);
		try {
			File file = new File(folder);
			String Nf = file.getCanonicalPath() + "\\";
			File[] listOfFiles = null;
			listOfFiles = new File(Nf).listFiles();
			// System.out.println("folder name"+folder);
			// List<String> list = new ArrayList(listOfFiles.length);
			// System.out.println(listOfFiles.length);
			for (File file1 : listOfFiles) {
				if ((file1.isFile()) && (file1.getName() != null)) {
					list.add(file1.getName());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void CallMNM(String Filename, String Contents, String sts) {
		// path p1=new path();
		File file = new File(".//");
		try {

			String Nf = file.getCanonicalPath() + "\\" + "Result_discrepancy" + "\\";
			PrintWriter Fr;
			Fr = new PrintWriter(new FileOutputStream((Nf + "Discrepancy Report" + ".csv"), true));
			StringBuilder Freport = new StringBuilder();

			if (sts == "NotMatched")// add value in discrepancy report as well as create new compared file report
			// for each set of actual and expected files
			{
				PrintWriter Fr2;
				Fr2 = new PrintWriter(new FileOutputStream((Nf + "Discrepancy Report" + ".csv"), true));
				StringBuilder Freport2 = new StringBuilder();
				PrintWriter pw;
				pw = new PrintWriter(new File(Nf + "Result_" + Filename));
				StringBuilder sb = new StringBuilder();

				Freport2.append(Contents);
				// Freport2.append("\n");
				sb.append(Contents);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pw.write(sb.toString());// Writing content to descripancy report
				pw.close();
				Fr2.write(Freport2.toString());
				Fr2.close();
			}
			Fr.write(Freport.toString());
			Fr.close();
			/*
			 * if (sts=="Matched") {
			 * 
			 * Freport.append(Filename); Freport.append(","); Freport.append("All");
			 * Freport.append(","); Freport.append("All"); Freport.append(",");
			 * Freport.append("Matched"); Freport.append(","); Freport.append("Matched");
			 * Freport.append("\n"); try { Thread.sleep(20); } catch (InterruptedException
			 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
			 * Fr.write(Freport.toString()); Fr.close(); }
			 */ // Fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String xCSVUDateUpdation(String FileName) throws Exception {
		try {
			CSVReader csvReader = new CSVReader(new FileReader(FileName));
			String[] row = null;
			String Cellvalue = null;
			String ModifiedDate = null;
			String Modified = null;
			int PZIP = 0, DZIP = 0;
			List<String[]> lst = new ArrayList<String[]>();
			int j = 0;
			while ((row = csvReader.readNext()) != null) {
				if (j == 0) {
					for (int rowval = 1; rowval < row.length; rowval++) {
						Modified = row[rowval];
						if (Modified.equalsIgnoreCase(" Phys Origin Zip")
								|| Modified.equalsIgnoreCase("Phys Origin Zip")) {
							PZIP = rowval;
						}
						if (Modified.equalsIgnoreCase("Destination Zip")) {
							DZIP = rowval;
						}
					}
				}

				if (j != 0) {
					for (int rowval = 0; rowval < row.length; rowval++) {
						Modified = row[rowval];
						if (Modified.contains("TESTMONTH") || Modified.contains("CURRENTMONTH")
								|| Modified.contains("LIVEMONTH") || Modified.contains("COMPSTDATE")
								|| Modified.contains("COMPSTDATESPL") || Modified.contains("YRSTDAY")
								|| Modified.contains("COMPSTDAY") || Modified.contains("WEEKDAY")
								|| Modified.contains("ENDCERTPTMONTH") || Modified.contains("NOYEAR")
								|| Modified.contains("YESTERDAY") || Modified.contains("BEFOREYESTERDAY")
								|| Modified.contains("BEFOREBEFOREYESTERDAY")) {
							ModifiedDate = xModified(ModifiedDate, Modified);
							if (Modified.isEmpty() != true) {
								Cellvalue = Modified.replace(Modified, ModifiedDate);
							}
							for (int i = 0; i < row.length; i++) {
								if (Modified.contains("TESTMONTH") || Modified.contains("LIVEMONTH")
										|| Modified.contains("CURRENTMONTH") || Modified.contains("COMPSTDATE")
										|| Modified.contains("COMPSTDATESPL") || Modified.contains("YRSTDAY")
										|| Modified.contains("COMPSTDAY") || Modified.contains("WEEKDAY")
										|| Modified.contains("ENDCERTPTMONTH") || Modified.contains("NOYEAR")
										|| Modified.contains("YESTERDAY") || Modified.contains("BEFOREYESTERDAY")
										|| Modified.contains("BEFOREBEFOREYESTERDAY")) {
									row[rowval] = Cellvalue;
									break;
								}
							}
						}
						if (Modified.isEmpty() != true) {
							if (Modified.matches("\\d+")) {
								if (PZIP == rowval || DZIP == rowval) {
									if (Modified.length() == 3) {
										// Modified = Modified.format("\"00"+Modified+"\"", Modified);
										Modified = String.format("00" + Modified, Modified);
										row[rowval] = Modified;
										System.out.println(row[rowval]);
										continue;
									}
									if (Modified.length() == 4) {
										// Modified = Modified.format("\"0"+Modified+"\"", Modified);
										Modified = String.format("0" + Modified, Modified);
										row[rowval] = Modified;
										System.out.println(row[rowval]);
										continue;
									}
								}
							}
						}
					}

				}
				lst.add(row);
				j++;
			}
			csvReader.close();
			CSVWriter writer = new CSVWriter(new FileWriter(FileName), ',', CSVWriter.NO_QUOTE_CHARACTER);
			for (int i = 0; i < lst.size(); i++) {
				writer.writeNext(lst.get(i));
			}
			writer.close();
		} catch (Exception e) {
			return e.getMessage();
		}
		return FileName;
	}

	public static String xModified(String ModifiedDate, String Modified) throws Exception {

		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
		DateFormat dateFormat1 = new SimpleDateFormat("MM/dd/YYYY");
		String postingDate = dateFormat.format(date);
		int month = Integer.parseInt(postingDate.split("/")[0]);
		switch (Modified.toUpperCase()) {
		// Currentmonth-2
		case "TESTMONTH":
			switch (month) {
			case 1:
				ModifiedDate = "11" + "/" + "01" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 2:
				ModifiedDate = "12" + "/" + "01" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 12:
				ModifiedDate = month - 2 + "/" + "01" + "/" + (cal.get(Calendar.YEAR));
				break;
			default:
				ModifiedDate = "0" + (month - 2) + "/" + "01" + "/" + (cal.get(Calendar.YEAR));
				break;

			}
			break;
		// Currentmonth
		case "CURRENTMONTH":
			if (month > 9) {
				ModifiedDate = (month) + "/" + "01" + "/" + (cal.get(Calendar.YEAR));
			} else {
				ModifiedDate = "0" + month + "/" + "01" + "/" + (cal.get(Calendar.YEAR));
			}
			break;
		// Currentmonth-1
		case "LIVEMONTH":
			switch (month) {
			case 1:
				ModifiedDate = "12" + "/" + "01" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 11:
				ModifiedDate = (month - 1) + "/" + "01" + "/" + (cal.get(Calendar.YEAR));
				break;
			case 12:
				ModifiedDate = (month - 1) + "/" + "01" + "/" + (cal.get(Calendar.YEAR));
				break;
			default:
				ModifiedDate = "0" + (month - 1) + "/" + "01" + "/" + (cal.get(Calendar.YEAR));
				break;
			}
			break;
		// company start date 1/1/2015
		case "COMPSTDATE":
			ModifiedDate = "01/01/2015";
			break;
		// company start date 1/1/2015@
		case "COMPSTDATESPL":
			ModifiedDate = "01/01/2015@";
			break;
		// StartDay of the Year
		case "YRSTDAY":
			ModifiedDate = "01/01" + (cal.get(Calendar.YEAR));
			break;
		// Date in 1-Jan-15
		case "COMPSTDAY":
			Integer Year = cal.get(Calendar.YEAR) - 1;
			date = new SimpleDateFormat("yy").parse(Year.toString());
			ModifiedDate = "01" + "-" + new SimpleDateFormat("MMM").format(Calendar.JANUARY) + "-"
					+ new SimpleDateFormat("yy").format(date);
			break;
		// Date like "Thursday, January 1, 2015"
		case "WEEKDAY":
			ModifiedDate = "Thursday, January 1, 2015";
			break;
		// No Year like 15-Jan
		case "NOYEAR":
			ModifiedDate = "15" + "-" + new SimpleDateFormat("MMM").format(Calendar.JANUARY);
			break;
		// CertificateEnd Date 1/2/2017
		case "ENDCERTPTMONTH":
			ModifiedDate = "02/01/" + (cal.get(Calendar.YEAR) + 1);
			break;

		case "YESTERDAY":
			cal.add(Calendar.DATE, -1);
			cal.add(Calendar.MONTH, 0);
			cal.add(Calendar.YEAR, 0);
			ModifiedDate = dateFormat1.format(cal.getTime());
			break;
		case "BEFOREYESTERDAY":
			cal.add(Calendar.DATE, -2);
			cal.add(Calendar.MONTH, 0);
			cal.add(Calendar.YEAR, 0);
			ModifiedDate = dateFormat1.format(cal.getTime());
			break;
		case "BEFOREBEFOREYESTERDAY":
			cal.add(Calendar.DATE, -3);
			cal.add(Calendar.MONTH, 0);
			cal.add(Calendar.YEAR, 0);
			ModifiedDate = dateFormat1.format(cal.getTime());
			break;
		}
		return ModifiedDate;
	}

	public static int[] xDatarowno(String value) throws Exception {
		int count = 0, Sdno = 0, edno = 0, Dtar = 0;
		String[] Dataset = null;
		int[] Rdts = null;
		String[] Data;
		String Dtno;
		if (value.contains("#")) {
			String Data1[] = value.split("#");
			Data = Data1[0].split(";");
		} else {
			Data = value.split(";");
		}
		Dtno = Data[2];
		if (!Dtno.isEmpty()) {
			if (Dtno.contains(",")) {
				Dataset = Dtno.split(",");
				int Datacount = Dataset.length;
				for (int Dset = 0; Dset < Datacount; Dset++) {
					if (Dataset[Dset].contains("-")) {
						String[] stdata = Dataset[Dset].split("-");
						Sdno = Integer.parseInt(stdata[0]);
						edno = Integer.parseInt(stdata[1]);
						for (int i = Sdno; i <= edno; i++) {
							count++;
						}
					} else {
						count++;
					}
				}
				Rdts = new int[count];
				for (int Dset = 0; Dset < Datacount; Dset++) {
					if (Dataset[Dset].contains("-")) {
						String[] stdata = Dataset[Dset].split("-");
						Sdno = Integer.parseInt(stdata[0]);
						edno = Integer.parseInt(stdata[1]);
						for (int i = Sdno; i <= edno; i++) {
							Rdts[Dtar] = i;
							Dtar++;
						}
					} else {
						int Val = Integer.parseInt(Dataset[Dset]);
						Rdts[Dtar] = Val;
						Dtar++;
					}
				}
			} else if (Dtno.contains("-")) {
				String[] stdata = Dtno.split("-");
				Sdno = Integer.parseInt(stdata[0]);
				edno = Integer.parseInt(stdata[1]);
				for (int i = Sdno; i <= edno; i++) {
					count++;
				}
				Rdts = new int[count];
				for (int i = Sdno; i <= edno; i++) {
					Rdts[Dtar] = i;
					Dtar++;
				}

			} else {
				Rdts = new int[1];
				int dtval = Integer.parseInt(Dtno);
				Rdts[0] = dtval;
			}
			return (Rdts);
		} else
			return null;
	}

	public static String xGetreportlinkfromGmail() throws Exception {
		String registrationURL;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			Folder folder = store.getFolder("Personal Labels/SOC Report");
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject
			messages = folder.search(new SubjectTerm("Vertex SOC Request"), folder.getMessages());
			// Wait for 10 seconds
			if (messages.length == 0) {
				Thread.sleep(10000);
			}
			// Search for Recent mail from Inbox
			// This is to avoid using the mail for which
			for (Message mail : messages) {
				if (!mail.isSet(Flags.Flag.RECENT)) {
					mailFromGod = mail;
					isMailFound = true;
				}
			}

			// Test fails if no unread mail was found from Inbox Folder
			if (!isMailFound) {
				throw new Exception("Could not find new mail from Inbox Folder :(");
				// Read the content of mail and launch registration URL
			} else {
				String line;
				StringBuffer buffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}

				// Your logic to split the message and get the SOC Download URL goes here

				registrationURL = buffer.toString().split("//")[1];
				registrationURL = "https://" + registrationURL.split("This")[0];
				System.out.println(registrationURL);

			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return registrationURL;
	}

	public static String xGetECWRejectmaillink() throws Exception {
		String RejectUrl;
		String StrEnvironment = null;
		try {
			switch (Environment) {
			case "DEV":
				StrEnvironment = Environment.substring(0, 1).toUpperCase() + Environment.substring(1).toLowerCase();
				break;
			case "QA":
				StrEnvironment = Environment.substring(0, 1).toUpperCase() + Environment.substring(1);
				break;
			case "STAGE":
				StrEnvironment = Environment.substring(0, 1).toUpperCase() + Environment.substring(1).toLowerCase();
				break;
			}
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			Folder folder = store.getFolder("Personal Labels/" + StrEnvironment + " Env");
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject
			messages = folder.search(new SubjectTerm("ECWCMP, Inc Certificate Update Required"), folder.getMessages());
			// Wait for 10 seconds
			if (messages.length == 0) {
				Thread.sleep(10000);
			}
			// Search for Recent mail from Inbox
			// This is to avoid using the mail for which
			for (Message mail : messages) {
				if (!mail.isSet(Flags.Flag.RECENT)) {
					mailFromGod = mail;
					isMailFound = true;
				}
			}

			// Test fails if no unread mail was found from Inbox Folder
			if (!isMailFound) {
				throw new Exception("Could not find new mail from Inbox Folder :(");
				// Read the content of mail and launch registration URL
			} else {
				String line;
				StringBuffer buffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}

				// Your logic to split the message and get the SOC Download URL goes here

				RejectUrl = buffer.toString().split("//")[1];
				RejectUrl = "https://" + RejectUrl.split("Please feel free to contact me with any questions at")[0];
				System.out.println(RejectUrl);
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return RejectUrl;
	}

	public String xGetreportlinkfromGmail2(String Obj1, String Obj2, String Environment) throws Exception {
		String MsgBody, Sub, clientname, rname;
		Folder folder;
		String isMatched;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			if (Environment.equalsIgnoreCase("AWSPROD") || Environment.equalsIgnoreCase("AWSSTAGE")
					|| Environment.equalsIgnoreCase("STAGE")) {
				folder = store.getFolder("Personal Labels/Stage Env");
			} else if (Environment.equalsIgnoreCase("AWSQA")) {
				folder = store.getFolder("Personal Labels/QA Env");
			} else {
				folder = store.getFolder("Personal Labels/" + Environment + " Env");
			}
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject
			Sub = Obj1 + ":" + Obj2 + " Added";
			messages = folder.search(new SubjectTerm(Sub), folder.getMessages());
			// Wait for 10 seconds
			if (messages.length == 0) {
				Thread.sleep(10000);
			}
			// Search for Recent mail from Inbox
			// This is to avoid using the mail for which
			for (Message mail : messages) {
				if (!mail.isSet(Flags.Flag.RECENT)) {
					mailFromGod = mail;
					isMailFound = true;
					break;
				}
			}
			// Test fails if no unread mail was found from Inbox Folder
			if (!isMailFound) {
				throw new Exception("Could not find new mail from Inbox Folder :(");
				// Read the content of mail and launch registration URL
			} else {
				String line;
				StringBuffer buffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				// Your logic to split the message and get the SOC Download URL goes here
				MsgBody = buffer.toString();
				clientname = MsgBody.split(":")[0];
				rname = MsgBody.split(clientname + ":")[1];
				rname = rname.split("AddedCopyright")[0];
				rname = rname.trim();
				if (clientname.equalsIgnoreCase(Obj1) || (rname.equalsIgnoreCase(Obj2))) {
					isMatched = "Pass";
				} else {
					isMatched = "fail ,Sub is not matched";
				}
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return isMatched;
	}

	public String GetAddressCleansingNotification(String Subject, String Environment, String Username)
			throws Exception {
		String MsgBody, Sub, rname = null;
		Folder folder;
		String isMatched;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			if (Environment.equalsIgnoreCase("PROD") || Environment.equalsIgnoreCase("STAGE")
					|| Environment.equalsIgnoreCase("STAGE")) {
				folder = store.getFolder("Personal Labels/Stage Env");
			} else if (Environment.equalsIgnoreCase("QA")) {
				folder = store.getFolder("Personal Labels/QA Env");
			} else {
				folder = store.getFolder("Personal Labels/Dev Env");
			}
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject
			Sub = Subject + " " + Username;
			messages = folder.search(new SubjectTerm(Sub), folder.getMessages());
			// Wait for 10 seconds
			if (messages.length == 0) {
				Thread.sleep(10000);
			}
			// Search for Recent mail from Inbox
			// This is to avoid using the mail for which
			for (Message mail : messages) {
				if (!mail.isSet(Flags.Flag.RECENT)) {
					mailFromGod = mail;
					isMailFound = true;
					break;
				}
			}
			// Test fails if no unread mail was found from Inbox Folder
			if (!isMailFound) {
				throw new Exception("Could not find new mail from Inbox Folder :(");
				// Read the content of mail and launch registration URL
			} else {
				String line;
				StringBuffer buffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				// Your logic to split the message and get the SOC Download URL goes here
				MsgBody = buffer.toString();
				rname = MsgBody.split("Copyright")[0];
				rname = rname.trim();
				if (rname.equalsIgnoreCase(
						"This cloud customer has begun using Address Cleansing. Please add the appropriate usage line to enable invoicing.")) {
					isMatched = "Pass";
				} else {
					isMatched = "fail ,Sub is not matched";
				}
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return isMatched;
	}

	public String xGetreportlinkfromGmail3(String Obj1, String Obj2, String Environment) throws Exception {
		String MsgBody, Sub, clientname, rname;
		Folder folder;
		String isMatched;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			if (Environment.equalsIgnoreCase("AWSPROD") || Environment.equalsIgnoreCase("AWSSTAGE")
					|| Environment.equalsIgnoreCase("STAGE")) {
				folder = store.getFolder("Personal Labels/Stage Env");
			} else if (Environment.equalsIgnoreCase("AWSQA")) {
				folder = store.getFolder("Personal Labels/QA Env");
			} else {
				folder = store.getFolder("Personal Labels/" + Environment + " Env");
			}
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject
			Sub = Obj1 + ":" + Obj2 + " Updated";
			messages = folder.search(new SubjectTerm(Sub), folder.getMessages());
			// Wait for 10 seconds
			if (messages.length == 0) {
				Thread.sleep(10000);
			}
			// Search for Recent mail from Inbox
			// This is to avoid using the mail for which
			for (Message mail : messages) {
				if (!mail.isSet(Flags.Flag.RECENT)) {
					mailFromGod = mail;
					isMailFound = true;
					break;
				}
			}
			// Test fails if no unread mail was found from Inbox Folder
			if (!isMailFound) {
				throw new Exception("Could not find new mail from Inbox Folder :(");
				// Read the content of mail and launch registration URL
			} else {
				String line;
				StringBuffer buffer = new StringBuffer();
				BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				// Your logic to split the message and get the SOC Download URL goes here
				MsgBody = buffer.toString();
				clientname = MsgBody.split(":")[0];
				rname = MsgBody.split(clientname + ":")[1];
				rname = rname.split("AddedCopyright")[0];
				rname = rname.trim();
				if (clientname.equalsIgnoreCase(Obj1) || (rname.equalsIgnoreCase(Obj2))) {
					isMatched = "Pass";
				} else {
					isMatched = "fail ,Sub is not matched";
				}
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return isMatched;
	}

	public static String xCSVUDateUpdate(String FileName) throws Exception {
		try {
			CSVReader csvReader = new CSVReader(new FileReader(FileName));
			String[] row = null;
			String Cellvalue = null;
			String ModifiedDate = null;
			String Modified = null;
			int PZIP = 0, DZIP = 0;
			List<String[]> lst = new ArrayList<String[]>();
			int j = 0;
			while ((row = csvReader.readNext()) != null) {
				if (j == 0) {
					for (int rowval = 1; rowval < row.length; rowval++) {
						Modified = row[rowval];
						if (Modified.equalsIgnoreCase("Filing Period")) {
							PZIP = rowval;
						}
					}
				}

				if (j != 0) {
					for (int rowval = 0; rowval < row.length; rowval++) {
						Modified = row[rowval];
						if (Modified.contains("TESTMONTH") || Modified.contains("CURRENTMONTH")
								|| Modified.contains("LIVEMONTH") || Modified.contains("COMPSTDATE")
								|| Modified.contains("COMPSTDATESPL") || Modified.contains("YRSTDAY")
								|| Modified.contains("COMPSTDAY") || Modified.contains("WEEKDAY")
								|| Modified.contains("ENDCERTPTMONTH") || Modified.contains("NOYEAR")) {
							ModifiedDate = xModifiedDate(ModifiedDate, Modified);
							if (Modified.isEmpty() != true) {
								Cellvalue = Modified.replace(Modified, ModifiedDate);
							}
							for (int i = 0; i < row.length; i++) {
								if (Modified.contains("TESTMONTH") || Modified.contains("LIVEMONTH")
										|| Modified.contains("CURRENTMONTH") || Modified.contains("COMPSTDATE")
										|| Modified.contains("COMPSTDATESPL") || Modified.contains("YRSTDAY")
										|| Modified.contains("COMPSTDAY") || Modified.contains("WEEKDAY")
										|| Modified.contains("ENDCERTPTMONTH") || Modified.contains("NOYEAR")) {
									row[rowval] = Cellvalue;
									break;
								}
							}
						}
						if (Modified.isEmpty() != true) {
							if (Modified.matches("\\d+")) {
								if (PZIP == rowval || DZIP == rowval) {
									if (Modified.length() == 3) {
										// Modified = Modified.format("\"00"+Modified+"\"", Modified);
										Modified = String.format("00" + Modified, Modified);
										row[rowval] = Modified;
										System.out.println(row[rowval]);
										continue;
									}
									if (Modified.length() == 4) {
										// Modified = Modified.format("\"0"+Modified+"\"", Modified);
										Modified = String.format("0" + Modified, Modified);
										row[rowval] = Modified;
										System.out.println(row[rowval]);
										continue;
									}
								}
							}
						}
					}

				}
				lst.add(row);
				j++;
			}
			csvReader.close();
			CSVWriter writer = new CSVWriter(new FileWriter(FileName), ',', CSVWriter.NO_QUOTE_CHARACTER);
			for (int i = 0; i < lst.size(); i++) {
				writer.writeNext(lst.get(i));
			}
			writer.close();
		} catch (Exception e) {
			return e.getMessage();
		}
		return FileName;
	}

	public static String xModifiedDate(String ModifiedDate, String Modified) throws Exception {

		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
		String postingDate = dateFormat.format(date);
		int month = Integer.parseInt(postingDate.split("/")[0]);
		switch (Modified.toUpperCase()) {
		// Currentmonth-2
		case "TESTMONTH":
			switch (month) {
			case 1:
				ModifiedDate = "11" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 2:
				ModifiedDate = "12" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 12:
				ModifiedDate = month - 2 + "/" + (cal.get(Calendar.YEAR));
				break;
			default:
				ModifiedDate = "0" + (month - 2) + "/" + (cal.get(Calendar.YEAR));
				break;
			}
			break;
		// Currentmonth
		case "CURRENTMONTH":
			if (month > 9) {
				ModifiedDate = (month) + "/" + (cal.get(Calendar.YEAR));
			} else {
				ModifiedDate = "0" + month + "/" + (cal.get(Calendar.YEAR));
			}
			break;
		// Currentmonth-1
		case "LIVEMONTH":
			switch (month) {
			case 1:
				ModifiedDate = "12" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 11:
				ModifiedDate = (month - 1) + "/" + (cal.get(Calendar.YEAR));
				break;
			case 12:
				ModifiedDate = (month - 1) + "/" + (cal.get(Calendar.YEAR));
				break;
			default:
				ModifiedDate = "0" + (month - 1) + "/" + (cal.get(Calendar.YEAR));
				break;
			}
			break;
		case " TESTMONTH":
			switch (month) {
			case 1:
				ModifiedDate = "11" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 2:
				ModifiedDate = "12" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 12:
				ModifiedDate = month - 2 + "/" + (cal.get(Calendar.YEAR));
				break;
			default:
				ModifiedDate = "0" + (month - 2) + "/" + (cal.get(Calendar.YEAR));
				break;

			}
			break;
		// Currentmonth
		case " CURRENTMONTH":
			if (month > 9) {
				ModifiedDate = (month) + "/" + (cal.get(Calendar.YEAR));
			} else {
				ModifiedDate = "0" + month + "/" + (cal.get(Calendar.YEAR));
			}
			break;
		// Currentmonth-1
		case " LIVEMONTH":
			switch (month) {
			case 1:
				ModifiedDate = "12" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 11:
				ModifiedDate = (month - 1) + "/" + (cal.get(Calendar.YEAR));
				break;
			case 12:
				ModifiedDate = (month - 1) + "/" + (cal.get(Calendar.YEAR));
				break;
			default:
				ModifiedDate = "0" + (month - 1) + "/" + (cal.get(Calendar.YEAR));
				break;
			}
			break;
		}
		System.out.println(ModifiedDate);
		ModifiedDate = " " + ModifiedDate;
		System.out.println(ModifiedDate);
		return ModifiedDate;
	}

	public static String xCSVDateUpdation(String FileName) throws Exception {
		try {
			CSVReader csvReader = new CSVReader(new FileReader(FileName));
			String[] row = null;
			String Cellvalue = null;
			String ModifiedDate = null;
			String Modified = null;
			List<String[]> lst = new ArrayList<String[]>();
			int j = 0;
			while ((row = csvReader.readNext()) != null) {
				/*
				 * if(j==0){ for(int rowval=1;rowval<row.length;rowval++){ Modified=row[rowval];
				 * if(Modified.equalsIgnoreCase("Filing Period")){ Modified=row[rowval]; } } }
				 */

				if (j != 0) {
					for (int rowval = 0; rowval < row.length; rowval++) {
						Modified = row[rowval];
						if (Modified.contains("TESTMONTH") || Modified.contains("CURRENTMONTH")
								|| Modified.contains("LIVEMONTH")) {
							ModifiedDate = xdateModified(ModifiedDate, Modified);
							if (Modified.isEmpty() != true) {
								Cellvalue = Modified.replace(Modified, ModifiedDate);
							}
							for (int i = 0; i < row.length; i++) {
								if (Modified.contains("TESTMONTH") || Modified.contains("LIVEMONTH")
										|| Modified.contains("CURRENTMONTH")) {
									row[rowval] = Cellvalue;
									break;
								}
							}
						}

					}

				}
				lst.add(row);
				j++;
			}
			csvReader.close();
			CSVWriter writer = new CSVWriter(new FileWriter(FileName), ',', CSVWriter.NO_QUOTE_CHARACTER);
			for (int i = 0; i < lst.size(); i++) {
				writer.writeNext(lst.get(i));
			}
			writer.close();
		} catch (Exception e) {
			return e.getMessage();
		}
		return FileName;
	}

	public static String xdateModified(String ModifiedDate, String Modified) throws Exception {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
		String postingDate = dateFormat.format(date);
		int month = Integer.parseInt(postingDate.split("/")[0]);
		switch (Modified.toUpperCase()) {
		// Currentmonth-2
		case "TESTMONTH":
			switch (month) {
			case 1:
				ModifiedDate = "11" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 2:
				ModifiedDate = "12" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 12:
				ModifiedDate = month - 2 + "/" + (cal.get(Calendar.YEAR));
				break;
			default:
				ModifiedDate = "0" + (month - 2) + "/" + (cal.get(Calendar.YEAR));
				break;

			}
			break;
		// Currentmonth
		case "CURRENTMONTH":
			if (month > 9) {
				ModifiedDate = (month) + "/" + (cal.get(Calendar.YEAR));
			} else {
				ModifiedDate = "0" + month + "/" + (cal.get(Calendar.YEAR));
			}
			break;
		// Currentmonth-1
		case "LIVEMONTH":
			switch (month) {
			case 1:
				ModifiedDate = "12" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 11:
				ModifiedDate = (month - 1) + "/" + (cal.get(Calendar.YEAR));
				break;
			case 12:
				ModifiedDate = (month - 1) + "/" + (cal.get(Calendar.YEAR));
				break;
			default:
				ModifiedDate = "0" + (month - 1) + "01" + "/" + (cal.get(Calendar.YEAR));
				break;
			}
		case " TESTMONTH":
			switch (month) {
			case 1:
				ModifiedDate = " 11" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 2:
				ModifiedDate = " 12" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 12:
				ModifiedDate = " " + (month - 2) + "/" + (cal.get(Calendar.YEAR));
				break;
			default:
				ModifiedDate = " 0" + (month - 2) + "/" + (cal.get(Calendar.YEAR));
				break;

			}
			break;
		// Currentmonth
		case " CURRENTMONTH":
			if (month > 9) {
				ModifiedDate = " " + (month) + "/" + (cal.get(Calendar.YEAR));
			} else {
				ModifiedDate = " 0" + month + "/" + (cal.get(Calendar.YEAR));
			}
			break;
		// Currentmonth-1
		case " LIVEMONTH":
			switch (month) {
			case 1:
				ModifiedDate = " 12" + "/" + (cal.get(Calendar.YEAR) - 1);
				break;
			case 11:
				ModifiedDate = " " + (month - 1) + "/" + (cal.get(Calendar.YEAR));
				break;
			case 12:
				ModifiedDate = " " + (month - 1) + "/" + (cal.get(Calendar.YEAR));
				break;
			default:
				ModifiedDate = " 0" + (month - 1) + "01" + "/" + (cal.get(Calendar.YEAR));
				break;
			}

		}
		return ModifiedDate;
	}

	public static String xGetTestInstanceSetPasswordURL(String LoginName, String Environment) throws Exception {
		String registrationURL;
		Folder folder;
		String tUserName = "", pUserName;
		StringBuffer buffer = null;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			Thread.sleep(50000);
			if (Environment.equalsIgnoreCase("STAGE")) {
				folder = store.getFolder("Personal Labels/Stage Env");
			} else if (Environment.equalsIgnoreCase("QA")) {
				folder = store.getFolder("Personal Labels/QA Env");
			} else if (Environment.equalsIgnoreCase("DEV")) {
				folder = store.getFolder("Personal Labels/Dev Env");
			} else {
				folder = store.getFolder("Personal Labels/" + Environment + " Env");
			}
			// Folder folder = store.getFolder("Personal Labels/"+Environment+" Env");
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject
			messages = folder.search(new SubjectTerm("Vertex Cloud Specify Password"), folder.getMessages());
			System.out.println(messages.length);
			// Wait for 10 seconds
			int i;
			int n = messages.length;
			System.out.println(n);
			boolean report = false;
			do {
				if (messages.length == 0) {
					Thread.sleep(10000);
				}
				// Search for Recent mail from Inbox
				// This is to avoid using the mail for which
				// for (Message mail : messages) {
				for (i = n - 1; i >= n - i; i--) {
					Message mail = messages[i];
					if (!mail.isSet(Flags.Flag.SEEN) || mail.isSet(Flags.Flag.SEEN)) {
						mailFromGod = mail;
						isMailFound = true;
					}
					// Test fails if no unread mail was found from Inbox Folder
					if (!isMailFound) {
						throw new Exception("Could not find new mail from Inbox Folder :(");
						// Read the content of mail and launch registration URL
					} else {
						String line;
						buffer = new StringBuffer();
						BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
						// count++;
						while ((line = reader.readLine()) != null) {
							buffer.append(line);
						}
						tUserName = buffer.toString().split(":")[1].trim();
						pUserName = LoginName + ".TIhttps";
						if (tUserName.equalsIgnoreCase(pUserName)) {
							report = true;
							break;
						}
					}
				}
			} while (report == false);
			TestInstanceUserName = tUserName.substring(0, tUserName.length() - 5).trim();
			System.out.println(TestInstanceUserName);
			// Your logic to split the message and get the SOC Download URL goes here
			registrationURL = buffer.toString().split("//")[1];
			System.out.println(registrationURL);
			registrationURL = "https://" + registrationURL.split("This")[0];
			System.out.println(registrationURL);
			TestInstanceSetPwdURL = registrationURL.split("Copyright")[0];
		} catch (Exception e) {
			return e.getMessage();
		}
		return TestInstanceSetPwdURL;
	}

	public static void NotificationEmail(String Clientname, String Subject) throws Exception {

		Folder folder;
		StringBuffer buffer = null;
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
		Thread.sleep(50000);
		if (Environment.equalsIgnoreCase("AWSPROD") || Environment.equalsIgnoreCase("AWSSTAGE")
				|| Environment.equalsIgnoreCase("STAGE")) {
			folder = store.getFolder("Personal Labels/" + Environment.replace("TAGE", "tage") + " Env");
		} else if (Environment.equalsIgnoreCase("AWSQA")) {
			folder = store.getFolder("Personal Labels/" + Environment + " Env");
		} else if (Environment.equalsIgnoreCase("DEV")) {
			folder = store.getFolder("Personal Labels/" + Environment.replace("EV", "ev") + " Env");
		} else {
			folder = store.getFolder("Personal Labels/" + Environment + " Env");
		}
		folder.open(Folder.READ_WRITE);

		Message[] messages = folder.getMessages();

		for (int a = 0; a < messages.length; a++) {

			Multipart multipart = (Multipart) messages[a].getContent();
			for (int j = 0; j < multipart.getCount(); j++) {
				BodyPart bodyPart = multipart.getBodyPart(j);
				InputStream stream = bodyPart.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(stream));
				while (br.ready()) {
					System.out.println(br.readLine());
				}
			}
		}

		// Message[] messages = null;
		boolean isMailFound = false;
		Message mailFromGod = null;
		// Search for mail from Inbox Folder based on Subject
		// Here have to pass subject
		messages = folder.search(new SubjectTerm("" + Clientname + "" + Subject + ""), messages);
		System.out.println(messages.length);
		// Wait for 10 seconds
		int i;
		int n = messages.length;
		System.out.println(n);
		boolean report = false;
		do {
			if (messages.length == 0) {
				Thread.sleep(10000);
			}
			// Search for Recent mail from Inbox
			// This is to avoid using the mail for which
			// for (Message mail : messages) {
			for (i = n - 1; i >= n - i; i--) {
				Message mail = messages[i];
				if (!mail.isSet(Flags.Flag.SEEN) || mail.isSet(Flags.Flag.SEEN)) {
					mailFromGod = mail;
					isMailFound = true;
				}
				// Test fails if no unread mail was found from Inbox Folder
				if (!isMailFound) {
					throw new Exception("Could not find new mail from Inbox Folder :(");
					// Read the content of mail and launch registration URL
				} else {
					String line;
					buffer = new StringBuffer();
					BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
					// count++;
					while ((line = reader.readLine()) != null) {
						buffer.append(line);
					}
					if (buffer.toString().contains("uploaded a document to the Document Center")) {
						report = true;
						assertTrue(true);
						break;
					} else {
						assertTrue(false);
					}
				}
			}
		} while (report == false);

		folder.close(true);
		store.close();
		folder.close(true);
		store.close();
	}

	public static String xGetQBOVerificationCode(String Environment) throws Exception {
		Folder folder;
		StringBuffer buffer = null;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			Thread.sleep(50000);
			if (Environment.equalsIgnoreCase("AWSPROD") || Environment.equalsIgnoreCase("AWSSTAGE")
					|| Environment.equalsIgnoreCase("STAGE")) {
				folder = store.getFolder("Personal Labels/Stage Env");
			} else if (Environment.equalsIgnoreCase("AWSQA")) {
				folder = store.getFolder("Personal Labels/QA Env");
			} else if (Environment.equalsIgnoreCase("DEV")) {
				folder = store.getFolder("Personal Labels/Dev Env");
			} else {
				folder = store.getFolder("Personal Labels/" + Environment + " Env");
			}
			// Folder folder = store.getFolder("Personal Labels/"+Environment+" Env");
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject
			messages = folder.search(new SubjectTerm("Your Intuit Account"), folder.getMessages());
			System.out.println(messages.length);
			// Wait for 10 seconds
			int i;
			int n = messages.length;
			System.out.println(n);
			boolean report = false;
			do {
				if (messages.length == 0) {
					Thread.sleep(10000);
				}
				// Search for Recent mail from Inbox
				// This is to avoid using the mail for which
				// for (Message mail : messages) {
				for (i = n - 1; i >= n - i; i--) {
					Message mail = messages[i];
					if (!mail.isSet(Flags.Flag.SEEN) || mail.isSet(Flags.Flag.SEEN)) {
						mailFromGod = mail;
						isMailFound = true;
					}
					// Test fails if no unread mail was found from Inbox Folder
					if (!isMailFound) {
						throw new Exception("Could not find new mail from Inbox Folder :(");
						// Read the content of mail and launch registration URL
					} else {
						String line;
						buffer = new StringBuffer();
						BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
						// count++;
						while ((line = reader.readLine()) != null) {
							buffer.append(line);
						}
						System.out.println(buffer.toString());
					}
				}
			} while (report == false);
		} catch (Exception e) {
			return e.getMessage();
		}
		return TestInstanceSetPwdURL;
	}

	public static String xGetMerchantResentURL(String LoginName, String Environment, String MerchantClientName)
			throws Exception {
		String registrationURL;
		Folder folder;
		// String tUserName="",pUserName;
		StringBuffer buffer = null;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			Thread.sleep(10000);
			if (Environment.equalsIgnoreCase("AWSPROD") || Environment.equalsIgnoreCase("AWSSTAGE")
					|| Environment.equalsIgnoreCase("STAGE")) {
				folder = store.getFolder("Personal Labels/Stage Env");
			} else if (Environment.equalsIgnoreCase("AWSQA")) {
				folder = store.getFolder("Personal Labels/QA Env");
			} else if (Environment.equalsIgnoreCase("Platform_DEV")) {
				folder = store.getFolder("Personal Labels/Dev Env");
			} else {
				folder = store.getFolder("Personal Labels/" + Environment + " Env");
			}
			// Folder folder = store.getFolder("Personal Labels/"+Environment+" Env");
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject
			messages = folder.search(new SubjectTerm("Vertex Cloud Invitation"), folder.getMessages());
			System.out.println(messages.length);
			// Wait for 10 seconds
			int i;
			int n = messages.length;
			System.out.println(n);
			// boolean report = false;
			// do{
			if (messages.length == 0) {
				Thread.sleep(10000);
			}
			// Search for Recent mail from Inbox
			// This is to avoid using the mail for which
			// for (Message mail : messages) {
			for (i = n - 1; i >= n - i; i--) {
				Message mail = messages[i];
				if (!mail.isSet(Flags.Flag.SEEN) || mail.isSet(Flags.Flag.SEEN)) {
					mailFromGod = mail;
					isMailFound = true;
				}
				// Test fails if no unread mail was found from Inbox Folder
				if (!isMailFound) {
					throw new Exception("Could not find new mail from Inbox Folder :(");
					// Read the content of mail and launch registration URL
				} else {
					String line;
					buffer = new StringBuffer();
					BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
					// count++;
					while ((line = reader.readLine()) != null) {
						buffer.append(line);
					}
					String Clientname = buffer.toString();
					if (Clientname.contains(MerchantClientName)) {
						break;
					}
				}
			}
			// while(report == false);
			registrationURL = buffer.toString().split("//")[1];
			System.out.println(registrationURL);
			registrationURL = "https://" + registrationURL.split("This")[0];
			System.out.println(registrationURL);
			MerchantResentURL = registrationURL.split("Copyright")[0];
			System.out.println(MerchantResentURL);
		} catch (Exception e) {
			return e.getMessage();
		}
		return MerchantResentURL;
	}

	public static String xGetPendingResentURL(String LoginName, String Environment, String Pending) throws Exception {
		String registrationURL;
		Folder folder;
		StringBuffer buffer = null;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			Thread.sleep(10000);
			if (Environment.equalsIgnoreCase("AWSPROD") || Environment.equalsIgnoreCase("AWSSTAGE")
					|| Environment.equalsIgnoreCase("STAGE")) {
				folder = store.getFolder("Personal Labels/Stage Env");
			} else if (Environment.equalsIgnoreCase("AWSQA")) {
				folder = store.getFolder("Personal Labels/QA Env");
			} else if (Environment.equalsIgnoreCase("Platform_DEV")) {
				folder = store.getFolder("Personal Labels/Dev Env");
			} else {
				folder = store.getFolder("Personal Labels/" + Environment + " Env");
			}
			// Folder folder = store.getFolder("Personal Labels/"+Environment+" Env");
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject
			messages = folder.search(new SubjectTerm("Vertex Cloud Invitation"), folder.getMessages());
			System.out.println(messages.length);
			// Wait for 10 seconds
			int i;
			int n = messages.length;
			System.out.println(n);
			// boolean report = false;
			// do{
			if (messages.length == 0) {
				Thread.sleep(10000);
			}
			// Search for Recent mail from Inbox
			// This is to avoid using the mail for which
			// for (Message mail : messages) {
			for (i = n - 1; i >= n - i; i--) {
				Message mail = messages[i];
				if (!mail.isSet(Flags.Flag.SEEN) || mail.isSet(Flags.Flag.SEEN)) {
					mailFromGod = mail;
					isMailFound = true;
				}
				// Test fails if no unread mail was found from Inbox Folder
				if (!isMailFound) {
					throw new Exception("Could not find new mail from Inbox Folder :(");
					// Read the content of mail and launch registration URL
				} else {
					String line;
					buffer = new StringBuffer();
					BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
					while ((line = reader.readLine()) != null) {
						buffer.append(line);
					}
					String Clientname = buffer.toString();
					if (Clientname.contains(Pending)) {
						break;
					}
				}
			}
			// while(report == false);
			registrationURL = buffer.toString().split("//")[1];
			System.out.println(registrationURL);
			registrationURL = "https://" + registrationURL.split("This")[0];
			System.out.println(registrationURL);
			PendingResentURL = registrationURL.split("Copyright")[0];
			System.out.println(PendingResentURL);
		} catch (Exception e) {
			return e.getMessage();
		}
		return PendingResentURL;
	}

	public static String xGetCPAIntendedRecipients(String Environment, String Username) throws Exception {
		String IntendedRecipient = null;
		Folder folder;
		int count = 0;
		StringBuffer buffer = null;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			Thread.sleep(10000);
			if (Environment.equalsIgnoreCase("AWSPROD") || Environment.equalsIgnoreCase("AWSSTAGE")
					|| Environment.equalsIgnoreCase("STAGE")) {
				folder = store.getFolder("Personal Labels/Stage Env");
			} else if (Environment.equalsIgnoreCase("AWSQA")) {
				folder = store.getFolder("Personal Labels/QA Env");
			} else if (Environment.equalsIgnoreCase("Platform_DEV") || Environment.equalsIgnoreCase("DEV")) {
				folder = store.getFolder("Personal Labels/Dev Env");
			} else {
				folder = store.getFolder("Personal Labels/" + Environment + " Env");
			}
			// Folder folder = store.getFolder("Personal Labels/"+Environment+" Env");
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject

			messages = folder.search(new SubjectTerm("Customer Registration Complete"), folder.getMessages());
			System.out.println(messages.length);
			// Wait for 10 seconds
			int i;
			int n = messages.length;
			System.out.println(n);
			// boolean report = false;
			// do{
			if (messages.length == 0) {
				Thread.sleep(10000);
			}
			// Search for Recent mail from Inbox
			// This is to avoid using the mail for which
			// for (Message mail : messages) {
			for (i = n - 1; i >= n - i; i--) {
				Message mail = messages[i];
				if (!mail.isSet(Flags.Flag.SEEN) || mail.isSet(Flags.Flag.SEEN)) {
					mailFromGod = mail;
					isMailFound = true;
				}
				// Test fails if no unread mail was found from Inbox Folder
				if (!isMailFound) {
					throw new Exception("Could not find new mail from Inbox Folder :(");
					// Read the content of mail and launch registration URL
				} else {
					String line;
					buffer = new StringBuffer();
					BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
					while ((line = reader.readLine()) != null) {
						buffer.append(line);
					}
					String Clientname = buffer.toString();
					if (Clientname.contains(Username)) {
						count = count + 1;
						// while(report == false);
						IntendedRecipient = buffer.toString().split("INFO:")[1];
						IntendedRecipient = IntendedRecipient.split("Alert")[0];
						System.out.println(IntendedRecipient + " for " + Username);
						if (count == 8) {
							break;
						}

					}
				}
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return IntendedRecipient;
	}

	public String xGetNotificationMailECW(String Obj1, String Obj2, String Environment, String MSG, String Username)
			throws Exception {
		String MsgBody, MessageBody = "", Text, rname = "", uname = "";
		Folder folder;
		String isMatched;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "vtmsdev@gmail.com", "4a8!5Ri&");
			Thread.sleep(10000);
			if (Environment.equalsIgnoreCase("AWSPROD") || Environment.equalsIgnoreCase("AWSSTAGE")
					|| Environment.equalsIgnoreCase("STAGE")) {
				folder = store.getFolder("Personal Labels/Stage Env");
			} else if (Environment.equalsIgnoreCase("AWSQA")) {
				folder = store.getFolder("Personal Labels/QA Env");
			} else if (Environment.equalsIgnoreCase("DEV")) {
				folder = store.getFolder("Personal Labels/Dev Env");
			} else {
				folder = store.getFolder("Personal Labels/" + Environment + " Env");
			}
			folder.open(Folder.READ_WRITE);
			Message[] messages = null;
			boolean isMailFound = false;
			Message mailFromGod = null;
			// Search for mail from Inbox Folder based on Subject
			// Here have to pass subject
			// Sub=Obj1.trim();
			messages = folder.search(new SubjectTerm(Obj2), folder.getMessages());

			int i;
			int n = messages.length;
			System.out.println(n);
			// Wait for 10 seconds
			boolean report = false;
			do {
				if (messages.length == 0) {
					Thread.sleep(10000);
				}
				// Search for Recent mail from Inbox
				// This is to avoid using the mail for which
				for (i = n - 1; i >= n - i; i--) {
					Message mail = messages[i];
					if (!mail.isSet(Flags.Flag.SEEN) || mail.isSet(Flag.SEEN)) {
						mailFromGod = mail;
						isMailFound = true;
					}
					// Test fails if no unread mail was found from Inbox Folder
					if (!isMailFound) {
						throw new Exception("Could not find new mail from Inbox Folder :(");
						// Read the content of mail and launch registration URL
					} else {
						String line;
						StringBuffer buffer = new StringBuffer();
						BufferedReader reader = new BufferedReader(new InputStreamReader(mailFromGod.getInputStream()));
						while ((line = reader.readLine()) != null) {
							buffer.append(line);
						}
						// Your logic to split the message and get the SOC Download URL goes here
						MsgBody = buffer.toString();
						Text = MsgBody.split("-")[1];
						MessageBody = Text.split("Copyright")[0].trim();

						rname = MsgBody.split("Copyright")[1];
						rname = rname.split("Intended Recipient:")[1];
						rname = rname.split("Alert Event Type:")[0];
						rname = rname.trim();

						uname = MsgBody.split("Alert Event Type:")[1];
						uname = uname.split("<<Username>> - ")[1];
						uname = uname.split("<<Clientname>> - ")[0];
						uname = uname.trim();
						Thread.sleep(500);
						if (rname.equalsIgnoreCase(Obj1) && uname.equalsIgnoreCase(Username)) {
							report = true;
							break;
						}
					}
				}
			} while (report == false);
			if (rname.equalsIgnoreCase(Obj1) && (uname.equalsIgnoreCase(Username))
					&& (MessageBody.equalsIgnoreCase(MSG.trim()))) {
				isMatched = "Pass";
			} else {
				isMatched = "fail ,Sub is not matched";
			}
		}

		catch (Exception e) {
			return e.getMessage();
		}
		return isMatched;
	}

	/*
	 * public static void xCompareExcelFiles(String SourceFile, String
	 * DestinstionFile, WritableSheet RSheet, BusinessFunctions bussFunction) throws
	 * InvalidFormatException, IOException, BiffException, ParseException { String
	 * Verification, status = null, value = null; int NoOfVerify = 0;
	 * FileInputStream fis = new FileInputStream(SourceFile);
	 * org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
	 * int Num_Sheets = workbook.getNumberOfSheets();
	 * 
	 * Workbook CustInfoWorkBook = Workbook.getWorkbook(new
	 * FileInputStream(DestinstionFile)); // int
	 * num_workbooksheets=CustInfoWorkBook.getNumberOfSheets(); for (int j = 0; j <
	 * Num_Sheets; j++) { String sheetname = workbook.getSheetName(j); Sheet
	 * custsheetname = CustInfoWorkBook.getSheet(j); Verification = ""; Verification
	 * = (sheetname.equalsIgnoreCase(custsheetname.getName())) == true ?
	 * Verification : Verification + "Fail, NO. WorkSheet Names Does not match;";
	 * status = status + Verification; VertexCloud.RowNo =
	 * bussFunction.xResult(Verification, RSheet, sheetname, "", value,
	 * NoOfVerify++); if (sheetname.equalsIgnoreCase(custsheetname.getName())) {
	 * org.apache.poi.ss.usermodel.Sheet downloadedSheet =
	 * workbook.getSheet(sheetname); Sheet originalSheet; originalSheet =
	 * CustInfoWorkBook.getSheet(custsheetname.getName()); int dRCount =
	 * downloadedSheet.getLastRowNum(); int oRCount = originalSheet.getRows(); if
	 * (oRCount == 1) { dRCount = 0; } if ((dRCount) + 1 == oRCount) { int oColCount
	 * = 0; int dColCount = 0; if
	 * (sheetname.equalsIgnoreCase(" Qualifying Conditions")) { oColCount = 5; }
	 * else { oColCount = originalSheet.getColumns(); } for (int k = 0; k <=
	 * dRCount; k++) { org.apache.poi.ss.usermodel.Row dRow =
	 * downloadedSheet.getRow(k); if
	 * (sheetname.equalsIgnoreCase(" Qualifying Conditions")) { dColCount = 5; }
	 * else { dColCount = dRow.getLastCellNum(); } if (oColCount == dColCount) { for
	 * (int dcols = 0; dcols < dColCount; dcols++) { String oContent =
	 * originalSheet.getCell(dcols, k).getContents().trim(); String dContent = "";
	 * switch (dRow.getCell(dcols).getCellType()) { case Cell.CELL_TYPE_STRING:
	 * dContent = dRow.getCell(dcols).getStringCellValue().trim(); break; case
	 * Cell.CELL_TYPE_NUMERIC: if
	 * (DateUtil.isCellDateFormatted(dRow.getCell(dcols))) { Date date =
	 * dRow.getCell(dcols).getDateCellValue(); String dContentdate =
	 * String.valueOf(date); // System.out.println("Dowload file date format is"
	 * +dContentdate); DateFormat formatter = new
	 * SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy"); Date Oridate = (Date)
	 * formatter.parse(dContentdate); // System.out.println(Oridate); Calendar cal =
	 * Calendar.getInstance(); cal.setTime(Oridate); // String CalendatDate =
	 * cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) // + 1) + "/" +
	 * cal.get(Calendar.YEAR); String CalendatDate = cal.get(Calendar.MONTH) + 1 +
	 * "/" + (cal.get(Calendar.DATE)) + "/" + cal.get(Calendar.YEAR); //
	 * System.out.println("formatedDate : " + CalendatDate); String[]
	 * splitCalendatDate = CalendatDate.split("/"); dContent = splitCalendatDate[0]
	 * + "/" + splitCalendatDate[1] + "/" + splitCalendatDate[2].substring(2);
	 * System.out.println(dContent); System.out.println(oContent); } else { double d
	 * = dRow.getCell(dcols).getNumericCellValue(); dContent =
	 * String.valueOf(d).trim(); } break; default: boolean b1 =
	 * dRow.getCell(dcols).getBooleanCellValue(); dContent = String.valueOf(b1);
	 * break; } if (oContent.isEmpty() && dContent.isEmpty()) { } else {
	 * Verification = ""; Verification = (oContent.equalsIgnoreCase(dContent)) ==
	 * true ? Verification : Verification +
	 * "Fail, NO. Expected Reports Contents Does not match with Actual;"; status =
	 * status + Verification; VertexCloud.RowNo = bussFunction.xResult(Verification,
	 * RSheet, oContent, dContent, value, NoOfVerify++); } if (status.isEmpty()) {
	 * System.out.println(dContent); System.out.println(oContent); } } } else {
	 * Verification = ""; Verification =
	 * "Fail, NO. Expected WorkSheets Column Numbers Does not match with Actual;";
	 * status = status + Verification; VertexCloud.RowNo =
	 * bussFunction.xResult(Verification, RSheet,
	 * "Expected WorkSheets Column Numbers Does not match with Actual", "", value,
	 * NoOfVerify++); } } } else { Verification = ""; Verification =
	 * "Fail, NO. Expected WorkSheets Row Numbers Does not match with Actual;";
	 * status = status + Verification; VertexCloud.RowNo =
	 * bussFunction.xResult(Verification, RSheet,
	 * "Expected WorkSheets Row Numbers Does not match with Actual", "", value,
	 * NoOfVerify++); } } } }
	 */

	public static void xr_xCompareExcelFiles(String SourceFile, String DestinstionFile)
			throws InvalidFormatException, IOException, BiffException, ParseException {
		FileInputStream fis = new FileInputStream(SourceFile);
		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
		int Num_Sheets = workbook.getNumberOfSheets();
		Workbook CustInfoWorkBook = Workbook.getWorkbook(new FileInputStream(DestinstionFile));
		// int num_workbooksheets=CustInfoWorkBook.getNumberOfSheets();
		for (int j = 0; j < Num_Sheets; j++) {
			String sheetname = workbook.getSheetName(j);
			Sheet custsheetname = CustInfoWorkBook.getSheet(j);
			assertTrue(sheetname.equalsIgnoreCase(custsheetname.getName()));
			if (sheetname.equalsIgnoreCase(custsheetname.getName())) {
				org.apache.poi.ss.usermodel.Sheet downloadedSheet = workbook.getSheet(sheetname);
				Sheet originalSheet;
				originalSheet = CustInfoWorkBook.getSheet(custsheetname.getName());
				int dRCount = downloadedSheet.getLastRowNum();
				int oRCount = originalSheet.getRows();
				if (oRCount == 1) {
					dRCount = 0;
				}
				if ((dRCount) + 1 == oRCount) {
					int oColCount = 0;
					int dColCount = 0;
					if (sheetname.equalsIgnoreCase(" Qualifying Conditions")) {
						oColCount = 5;
					} else {
						oColCount = originalSheet.getColumns();
					}
					for (int k = 0; k <= dRCount; k++) {
						org.apache.poi.ss.usermodel.Row dRow = downloadedSheet.getRow(k);
						if (sheetname.equalsIgnoreCase(" Qualifying Conditions")) {
							dColCount = 5;
						} else {
							dColCount = dRow.getLastCellNum();
						}
						if (oColCount == dColCount) {
							for (int dcols = 0; dcols < dColCount; dcols++) {
								String oContent = originalSheet.getCell(dcols, k).getContents().trim();
								String dContent = "";
								if (dRow.getCell(dcols) != null) {
									switch (dRow.getCell(dcols).getCellType()) {
									case Cell.CELL_TYPE_STRING:
										dContent = dRow.getCell(dcols).getStringCellValue().trim();
										break;
									case Cell.CELL_TYPE_NUMERIC:
										if (DateUtil.isCellDateFormatted(dRow.getCell(dcols))) {
											Date date = dRow.getCell(dcols).getDateCellValue();
											String dContentdate = String.valueOf(date);
											// System.out.println("Dowload file date format is" +dContentdate);
											DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
											Date Oridate = (Date) formatter.parse(dContentdate);
											// System.out.println(Oridate);
											Calendar cal = Calendar.getInstance();
											cal.setTime(Oridate);
											// String CalendatDate = cal.get(Calendar.DATE) + "/" +
											// (cal.get(Calendar.MONTH)
											// + 1) + "/" + cal.get(Calendar.YEAR);
											String CalendatDate = cal.get(Calendar.MONTH) + 1 + "/"
													+ (cal.get(Calendar.DATE)) + "/" + cal.get(Calendar.YEAR);
											// System.out.println("formatedDate : " + CalendatDate);
											String[] splitCalendatDate = CalendatDate.split("/");
											dContent = splitCalendatDate[0] + "/" + splitCalendatDate[1] + "/"
													+ splitCalendatDate[2].substring(2);
											System.out.println(dContent);
											System.out.println(oContent);
										} else {
											double d = dRow.getCell(dcols).getNumericCellValue();
											dContent = String.valueOf(d).trim();
										}
										break;
									default:
										boolean b1 = dRow.getCell(dcols).getBooleanCellValue();
										dContent = String.valueOf(b1);
										break;
									}
									if (oContent.isEmpty() && dContent.isEmpty()) {
									} else {
										assertTrue(oContent.equalsIgnoreCase(dContent));
									}
									// if (status.isEmpty()) {
									System.out.println(dContent);
									System.out.println(oContent);
									// }
								}
							}
						} else {
						}
					}
				} else {
				}
			}
		}
	}

	public static String xInBetweenDates(String StartDate, String EndDate) throws Exception {
		String currentDate = xGetCurrentDateUTC("dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		Date startdate = sdf.parse(StartDate);
		Date Currentdate = sdf.parse(currentDate);
		Date enddate = sdf.parse(EndDate);
		if (Currentdate.compareTo(startdate) >= 0 && Currentdate.compareTo(enddate) <= 0) {
			return "True";
		} else {
			return "False";
		}
	}

	public static int[] xsubDatarowno(String value) throws Exception {
		int count = 0, Sdno = 0, edno = 0, Dtar = 0;
		String[] Dataset = null;
		int[] Rdts = null;
		if (!value.isEmpty()) {
			if (value.contains(";")) {
				Dataset = value.split(";");
				int Datacount = Dataset.length;
				for (int Dset = 0; Dset < Datacount; Dset++) {
					if (Dataset[Dset].contains("-")) {
						String[] stdata = Dataset[Dset].split("-");
						Sdno = Integer.parseInt(stdata[0]);
						edno = Integer.parseInt(stdata[1]);
						for (int i = Sdno; i <= edno; i++) {
							count++;
						}
					} else {
						count++;
					}
				}
				Rdts = new int[count];
				for (int Dset = 0; Dset < Datacount; Dset++) {
					if (Dataset[Dset].contains("-")) {
						String[] stdata = Dataset[Dset].split("-");
						Sdno = Integer.parseInt(stdata[0]);
						edno = Integer.parseInt(stdata[1]);
						for (int i = Sdno; i <= edno; i++) {
							Rdts[Dtar] = i;
							Dtar++;
						}
					} else {
						int Val = Integer.parseInt(Dataset[Dset]);
						Rdts[Dtar] = Val;
						Dtar++;
					}
				}
			} else if (value.contains("-")) {
				String[] stdata = value.split("-");
				Sdno = Integer.parseInt(stdata[0]);
				edno = Integer.parseInt(stdata[1]);
				for (int i = Sdno; i <= edno; i++) {
					count++;
				}
				Rdts = new int[count];
				for (int i = Sdno; i <= edno; i++) {
					Rdts[Dtar] = i;
					Dtar++;
				}

			} else {
				Rdts = new int[1];
				int dtval = Integer.parseInt(value);
				Rdts[0] = dtval;
			}
			return (Rdts);
		} else
			return null;
	}

	public static void HVRONOFF(Date instant, Date instant1) throws Exception {
		long diffMs = instant1.getTime() - instant.getTime();
		long diffSec = diffMs / 1000;
		long min = diffSec / 60;
		if (min < 10) {
			Thread.sleep(10 - min);
		}
	}

	public static void resultwriteScenario(String ScenarioName, String StepName) throws Exception {
		RowNo = RowNo + 1;
		// ScenarioNo=ScenarioNo+1;
		// resultWriteSheet.addCell(new Label(0,RowNo-1,String.valueOf(ScenarioNo-1)));
		resultWriteSheet.addCell(new Label(0, RowNo - 1, ScenarioName));
		resultWriteSheet.addCell(new Label(1, RowNo, StepName));
		resultWriteSheet.addCell(new Label(4, RowNo, "Pass", xFillCell(Colour.GREEN)));
		RowNo = RowNo + 1;
	}

	public static void resultwriteScenario(String ScenarioName, String StepName, String status, WebDriver aDriver,
			String MethodName) throws Exception {
		RowNo = RowNo + 1;
		// ScenarioNo=ScenarioNo+1;
		// resultWriteSheet.addCell(new Label(0,RowNo-1,String.valueOf(ScenarioNo-1)));
		resultWriteSheet.addCell(new Label(0, RowNo - 1, ScenarioName));
		resultWriteSheet.addCell(new Label(1, RowNo, StepName));
		resultWriteSheet.addCell(new Label(4, RowNo, "Fail", xFillCell(Colour.RED)));
		resultWriteSheet.addCell(new Label(5, RowNo, status));
		File scrFile = ((TakesScreenshot) aDriver).getScreenshotAs(OutputType.FILE);
		String FilePath = xRandomTimeStampName(MethodName);
		FileUtils.copyFile(scrFile, new File(FilePath));
		WritableHyperlink hyperlink = new WritableHyperlink(6, RowNo, new File(FilePath), MethodName);
		resultWriteSheet.addHyperlink(hyperlink);
	}

	public void resultwriteSteps(String StepName) throws RowsExceededException, WriteException {
		RowNo = RowNo + 1;
		resultWriteSheet.addCell(new Label(1, RowNo, StepName));
		resultWriteSheet.addCell(new Label(4, RowNo, "Pass", xFillCell(Colour.GREEN)));
	}

	public void resultwriteSteps(String StepName, WebDriver aDriver, String status, String MethodName)
			throws Exception {
		if (status.isEmpty()) {
			resultWriteSheet.addCell(new Label(1, RowNo, StepName));
			resultWriteSheet.addCell(new Label(4, RowNo, "Pass", xFillCell(Colour.GREEN)));
		} else {
			resultWriteSheet.addCell(new Label(1, RowNo, StepName));
			resultWriteSheet.addCell(new Label(4, RowNo, "Fail", xFillCell(Colour.RED)));
			resultWriteSheet.addCell(new Label(5, RowNo, status));
			File scrFile = ((TakesScreenshot) aDriver).getScreenshotAs(OutputType.FILE);
			String FilePath = xRandomTimeStampName(MethodName);
			FileUtils.copyFile(scrFile, new File(FilePath));
			WritableHyperlink hyperlink = new WritableHyperlink(6, RowNo, new File(FilePath), MethodName);
			resultWriteSheet.addHyperlink(hyperlink);
		}
		RowNo = RowNo + 1;
	}

	public void resultwritestepException(String StepName, WebDriver aDriver, String status, String MethodName)
			throws Exception {
		if (status.contains("Unable to locate element")) {
			StepName = "Web element not found and update your page object";
			resultWriteSheet.addCell(new Label(1, RowNo, StepName));
			resultWriteSheet.addCell(new Label(4, RowNo, "Fail", xFillCell(Colour.RED)));
			resultWriteSheet.addCell(new Label(5, RowNo, status));
			File scrFile = ((TakesScreenshot) aDriver).getScreenshotAs(OutputType.FILE);
			String FilePath = xRandomTimeStampName(MethodName);
			FileUtils.copyFile(scrFile, new File(FilePath));
			WritableHyperlink hyperlink = new WritableHyperlink(6, RowNo, new File(FilePath), MethodName);
			resultWriteSheet.addHyperlink(hyperlink);
		}
		RowNo = RowNo + 1;
	}

	public void xresultstarttime() throws Exception {
		StartTime = xGetCurrentTime();
		resultWriteSheet.addCell(new Label(7, 1, StartTime));
	}

	public static void xresultwrite() throws Exception {
		String EndTime = xGetCurrentTime();
		String TotalTime = xTotalExecutionTime(StartTime, EndTime);
		resultWriteSheet.addCell(new Label(8, 1, EndTime));
		resultWriteSheet.addCell(new Label(9, 1, TotalTime));
		autoResizeColumns();
		resultWritebook.write();
		resultWritebook.close();
		// sendMail();
	}

	public static void autoResizeColumns() {
		int columns = resultWriteSheet.getColumns();
		for (int i = 0; i < columns; i++) {
			if (i != 5) {
				CellView cell = resultWriteSheet.getColumnView(i);
				cell.setAutosize(true);
				resultWriteSheet.setColumnView(i, cell);
			}
		}
	}

	public void readXML(String fileName, String eOraclePartyNum, String eOracleAccountNumber, String eParentPartyNumber,
			String eParentAccountNumber, String eImageCount, String eReturnsCntProf, String eReturnsCntPrem,
			String RTRSCount, String eVolumeUsageCount, String eAddClensCount, String eRateFile,
			String eTaxabilityReportCoutnt, String eTestInstanceCount) throws Exception {
		File inputFile = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();

		// Verifying OraclePartyNum
		if (eOraclePartyNum != "") {
			if (!eOraclePartyNum
					.equalsIgnoreCase(doc.getElementsByTagName("OraclePartyNum").item(0).getTextContent())) {
				// eOraclePartyNumResult = "PASS";
				System.out.println("FAIL - OraclePartyNumber-Expected: " + eOraclePartyNum + ", Actual: "
						+ doc.getElementsByTagName("OraclePartyNum").item(0).getTextContent() + fileName);
			}
		}

		// Verifying OracleAccountNumber
		if (eOracleAccountNumber != "") {
			if (!eOracleAccountNumber
					.equalsIgnoreCase(doc.getElementsByTagName("OracleAccountNumber").item(0).getTextContent())) {
				System.out.println("FAIL - OracleAccountNumber-Expected: " + eOracleAccountNumber + ", Actual: "
						+ doc.getElementsByTagName("OracleAccountNumber").item(0).getTextContent() + fileName);
			}
		}

		// Verifying ParentPartyNumber
		if (eParentPartyNumber != "") {
			if (!eParentPartyNumber
					.equalsIgnoreCase(doc.getElementsByTagName("ParentPartyNumber").item(0).getTextContent())) {
				System.out.println("FAIL - ParentPartyNumber-Expected: " + eParentPartyNumber + ", Actual: "
						+ doc.getElementsByTagName("ParentPartyNumber").item(0).getTextContent() + fileName);
			}
		}
		// Verifying ParentAccountNumber
		if (eParentAccountNumber != "") {
			if (!eParentAccountNumber
					.equalsIgnoreCase(doc.getElementsByTagName("ParentAccountNumber").item(0).getTextContent())) {
				System.out.println("FAIL - ParentAccountNumber-ExpectedValue: " + eParentAccountNumber + ", Actual: "
						+ doc.getElementsByTagName("ParentAccountNumber").item(0).getTextContent() + fileName);
			}
		}

		NodeList nList = doc.getElementsByTagName("BeginGenerateReceiptLineItem");
		for (int b = 0; b < nList.getLength(); b++) {

			String eBillingProductName = nList.item(b).getChildNodes().item(1).getTextContent();
			String eRateCodeValue = nList.item(b).getChildNodes().item(3).getTextContent();
			String eEventCodeValue = nList.item(b).getChildNodes().item(5).getTextContent();

			switch (eBillingProductName) {
			case "Certificate Image Storage":
				// Verifying the Certificate Image Rate code
				if (!(eRateCodeValue).equalsIgnoreCase("CERTI")) {
					System.out.println("FAIL - CertificateImageStorageRateCode-Expected: CERTI" + " , Actual: "
							+ eRateCodeValue + fileName);
				}
				// Verifying the Certificate Image Count
				if (!(eEventCodeValue).equalsIgnoreCase(eImageCount)) {
					System.out.println("FAIL - CertificateImageStorageCount-Expected: " + eImageCount + ", Actual: "
							+ eEventCodeValue + fileName);
				}
				continue;

			case "Professional Returns":

				if (!(eRateCodeValue).equalsIgnoreCase("RETPR")) {
					System.out.println("FAIL - PreofessionalReturnsRateCode-Expected: RETPR" + ", Actual: "
							+ eRateCodeValue + fileName);
				}

				if (!(eEventCodeValue).equalsIgnoreCase(eReturnsCntProf)) {
					System.out.println("FAIL - ProfessionalReturnsCount-Expected: " + eReturnsCntProf + ", Actual: "
							+ eEventCodeValue + fileName);
				}
				continue;

			case "Premium Returns":

				if (!(eRateCodeValue).equalsIgnoreCase("PAYP ")) {
					System.out.println(
							"FAIL - PremiumReturnsRateCode-Expected: PAYP" + ", Actual: " + eRateCodeValue + fileName);
				}

				if (!(eEventCodeValue).equalsIgnoreCase(eReturnsCntPrem)) {
					System.out.println("FAIL-PremiumReturnsCount-Expected: " + eReturnsCntPrem + ", Actual: "
							+ eEventCodeValue + fileName);
				}
				continue;

			case "Real-time Rate Service":

				if (!(eRateCodeValue).equalsIgnoreCase("TAXQ ")) {
					System.out
							.println("FAIL - RTRS-RateCode-Expected: TAXQ" + " ,Actual: " + eRateCodeValue + fileName);
				}

				if (!(eEventCodeValue).equalsIgnoreCase(RTRSCount)) {
					System.out.println(
							"FAIL - RTRS-Count-Expected: " + RTRSCount + " ,Actual: " + eEventCodeValue + fileName);
				}
				continue;

			case "Volume Usage":

				if (!(eRateCodeValue).equalsIgnoreCase("TAXI ")) {
					System.out.println(
							"FAIL - TransactionsRateCode-Expected: TAXI" + " ,Actual: " + eRateCodeValue + fileName);
				}

				if (!(eEventCodeValue).equalsIgnoreCase(eVolumeUsageCount)) {
					System.out.println("FAIL - TransactionsCount-Expected: " + eVolumeUsageCount + " ,Actual: "
							+ eEventCodeValue + fileName);
				}
				continue;

			case "Address Cleansing":

				if (!(eRateCodeValue).equalsIgnoreCase("ADDRC")) {
					System.out.println("FAIL - AddressCleansingRateCode-Expected: ADDRC" + " ,Actual value: "
							+ eRateCodeValue + fileName);
				}

				if (!(eEventCodeValue).equalsIgnoreCase(eAddClensCount)) {
					System.out.println("FAIL - AddressCleansingCount-Expected: " + eAddClensCount + " ,Actual: "
							+ eEventCodeValue + fileName);
				}
				continue;

			case "Rate File ":

				if (!(eRateCodeValue).equalsIgnoreCase("RATED")) {
					System.out.println(
							"FAIL - RateFileRateCode-Expected: RATED" + " ,Actual: " + eRateCodeValue + fileName);
				}

				if (!(eEventCodeValue).equalsIgnoreCase(eRateFile)) {
					System.out.println(
							"FAIL - RateFileCount-Expected: " + eRateFile + " ,Actual: " + eEventCodeValue + fileName);
				}
				continue;

			case "Advanced Taxability Impact Report":

				if (!(eRateCodeValue).equalsIgnoreCase("ATIR ")) {
					System.out.println("FAIL - AdvancedTaxabilityImpactReportRateCode-Expected: ATIR" + " ,Actual: "
							+ eRateCodeValue + fileName);
				}

				if (!(eEventCodeValue).equalsIgnoreCase(eTaxabilityReportCoutnt)) {
					System.out.println("FAIL - AdvancedTaxabilityImpactReport-Expected:" + eTaxabilityReportCoutnt
							+ " ,Actual: " + eEventCodeValue + fileName);
				}
				continue;

			case "Test Instance":

				if (!(eRateCodeValue).equalsIgnoreCase("TINST")) {
					System.out.println(
							"FAIL - TestInstanceRateCode-Expected: TINST" + " ,Actual: " + eRateCodeValue + fileName);
				}

				if (!(eEventCodeValue).equalsIgnoreCase(eTestInstanceCount)) {
					System.out.println("FAIL - TestInstanceCount-Expected: " + eTestInstanceCount + " ,Actual: "
							+ eEventCodeValue + fileName);
				}
			}

			break;

		}

	}

	public static String xGetCustomizedMonthyear(int Month_argument, int Year_argument) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MONTH, Month_argument);

		cal.add(Calendar.YEAR, Year_argument);

		String CustomizedDate = dateFormat.format(cal.getTime());

		return (CustomizedDate);
	}

	public static void compareCSVRTE(String expectedFolder, String actualFolder, String resultsFile) throws Exception {
		File folder = new File(resultsFile);
		if (!folder.exists()) {
			folder.mkdir();
		}

		XSSFWorkbook workbook = new XSSFWorkbook();
		FileOutputStream fis = new FileOutputStream(resultsFile + "\\" + "results.xlsx");
		XSSFSheet sheet = workbook.createSheet("RTEOutPut");

		int r = 1;

		File file = new File(expectedFolder);
		String[] fileList = file.list();
		for (String name : fileList) {
			String expectedFile = expectedFolder + "\\" + name;
			String actualFile = actualFolder + "\\" + name;
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(expectedFile));
			@SuppressWarnings("resource")
			BufferedReader reader2 = new BufferedReader(new FileReader(actualFile));

			// Reading the file1
			List<String> lines = new ArrayList<>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

			// Reading the file2
			List<String> lines2 = new ArrayList<>();
			String line2 = null;
			while ((line2 = reader2.readLine()) != null) {
				lines2.add(line2);
			}

			// Verifying the row count
			if (lines.size() != lines2.size()) {
				System.out.println("FAIL: Num of lines from file1 " + name + "= " + lines.size()
						+ "Num of lines from file2 " + name + "= " + lines2.size());

			} else {

				for (int i = 0; i < lines.size(); i++) {
					String file1Line = lines.get(i);
					String file2Line = lines2.get(i);

					int firstFL1 = file1Line.indexOf("~");
					int secondFL1 = file1Line.indexOf("~", firstFL1 + 1);
					int thirdFL1 = file1Line.indexOf("~", secondFL1 + 1);

					int firstFL2 = file2Line.indexOf("~");
					int secondFL2 = file2Line.indexOf("~", firstFL2 + 1);
					int thirdFL2 = file2Line.indexOf("~", secondFL2 + 1);

					// Getting the substring and replacing the values in file1
					// Replacing the 2nd value
					// if the file name is rtebusinesslocation, rtejurisdiction, rteproduct,
					// rtetaxareajur, rtetaxpayer, rtetaxrate and rtetaxrule
					String Value2ndFrmFile1 = file1Line.substring(firstFL1 + 1, secondFL1);
					String partitionValueFrmFile1 = file1Line.substring(secondFL1 + 1, thirdFL1);
					String updated1LineValueFrmFile1 = file1Line.replaceFirst(Value2ndFrmFile1, "Value2");
					String updated2LineValueFrmFile1 = updated1LineValueFrmFile1.replaceFirst(partitionValueFrmFile1,
							"PartitonValue");

					// Getting the substring and replacing the values in file2
					String Value2ndFrmFile2 = file2Line.substring(firstFL2 + 1, secondFL2);
					String partitionValueFrmFile2 = file2Line.substring(secondFL2 + 1, thirdFL2);
					String updated1LineValueFrmFile2 = file2Line.replaceFirst(Value2ndFrmFile2, "Value2");
					String updated2LineValueFrmFile2 = updated1LineValueFrmFile2.replaceFirst(partitionValueFrmFile2,
							"PartitonValue");

					// If the line1 from actual does not match with line1 from expected, then
					// splitting each value and comparing.
					if (!updated2LineValueFrmFile1.equals(updated2LineValueFrmFile2)) {
						int tildaCount = 0;

						for (int c = 0; c < updated1LineValueFrmFile2.length(); c++) {
							if (updated1LineValueFrmFile2.charAt(c) == '~') {
								tildaCount++;
							}
						}

						int FL1tildaValue = 0;
						int FL1tildaS = 0;

						int FL2tildaValue = 0;
						int FL2tildaS = 0;

						for (int a = 0; a <= tildaCount; a++) {
							if (a == tildaCount) {
								updated2LineValueFrmFile1.substring(FL1tildaS, updated2LineValueFrmFile1.length());
								updated2LineValueFrmFile2.substring(FL2tildaS, updated2LineValueFrmFile2.length());
							} else {
								int FL1tilda = updated2LineValueFrmFile1.indexOf("~", FL1tildaValue);
								int FL2tilda = updated2LineValueFrmFile2.indexOf("~", FL2tildaValue);
								String fL1Value = updated2LineValueFrmFile1.substring(FL1tildaS, FL1tilda);
								String fL2Value = updated2LineValueFrmFile2.substring(FL2tildaS, FL2tilda);

								FL1tildaS = FL1tilda;
								FL1tildaValue = FL1tilda + 1;

								FL2tildaS = FL2tilda;
								FL2tildaValue = FL2tilda + 1;

								// Writing the file name, row number, expected value and actual value in Excel
								// sheet.
								if (!fL1Value.equals(fL2Value)) {
									Row row = sheet.createRow(r);
									Cell cell1 = row.createCell(1);
									Cell cell2 = row.createCell(2);
									Cell cell3 = row.createCell(3);
									Cell cell4 = row.createCell(4);
									cell1.setCellType(Cell.CELL_TYPE_STRING);
									cell2.setCellType(Cell.CELL_TYPE_STRING);
									cell3.setCellType(Cell.CELL_TYPE_STRING);
									cell4.setCellType(Cell.CELL_TYPE_STRING);

									cell1.setCellValue(name);
									cell2.setCellValue(i);
									cell3.setCellValue(fL1Value);
									cell4.setCellValue(fL2Value);
									r = r + 1;
								}
							}
						}
					}

					/*
					 * else { // System.out.println("PASS: line Number-" + i);
					 * //System.out.println("-Expected value= " + updated2LineValueFrmFile1); //
					 * System.out.println("-Actual   value= "+ updated2LineValueFrmFile2); }
					 */
				}

			}

		}
		// FileOutputStream fos = new FileOutputStream(fis);
		workbook.write(fis);
		fis.close();
	}

	public static void compareCSVRTEfiles(String expectedFolder, String actualFolder) throws Exception {

		File file = new File(expectedFolder);
		String[] fileList = file.list();
		for (String name : fileList) {
			String expectedFile = expectedFolder + "\\" + name;
			String actualFile = actualFolder + "\\" + name;
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(expectedFile));
			@SuppressWarnings("resource")
			BufferedReader reader2 = new BufferedReader(new FileReader(actualFile));

			// Reading the file1
			List<String> lines = new ArrayList<>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

			// Reading the file2
			List<String> lines2 = new ArrayList<>();
			String line2 = null;
			while ((line2 = reader2.readLine()) != null) {
				lines2.add(line2);
			}

			// Verifying the row count
			if (lines.size() != lines2.size()) {
				System.out.println("FAIL: Num of lines from file1 " + name + "= " + lines.size()
						+ "Num of lines from file2 " + name + "= " + lines2.size());

			} else {

				for (int i = 0; i < lines.size(); i++) {
					String file1Line = lines.get(i);
					String file2Line = lines2.get(i);

					assertTrue(file1Line.equalsIgnoreCase(file2Line));

				}

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

	public String Browser(WebDriver driver, String browser, String AutoITpath, String AdjustmentFilePath,
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

	public String returnpdflineitem(String filename, String lineItem) throws Exception {

		String Val = "";
		PdfReader reader = new PdfReader(filename); // to get the file
		int pageCnt = reader.getNumberOfPages();
		String page = PdfTextExtractor.getTextFromPage(reader, pageCnt);
		List<String> line = Arrays.asList(page.split("\n"));
		for (int i = 0; i < line.size() - 1; i++) {
			Val = line.get(i);
			if (Val.contains(lineItem)) {
				break;
			}
		}
		return Val;
	}

	public String xGetCSVCell(String Filepath, String FirstName, String LastName, String Email, String Username,
			String Phone, String CreatedDate, String LastLogin, String Status, String Role) throws Exception {
		String status = "";
		try {
			CSVReader reader = new CSVReader(new FileReader(Filepath));
			ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < nextLine.length; i++) {
					list.add(nextLine[i]);
				}
				array.add(list);
			}
			for (int x = 0; x < array.size(); x++) {
				for (int y = 0; y < array.get(x).size(); y++) {
					String Cell_Name = array.get(x).get(y);
					System.out.println(Cell_Name);
					Cell_Header = array.get(x).get(y);
					if (Cell_Name.equalsIgnoreCase(FirstName)) {
						System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(FirstName)) == true ? status
								: status + "Fail, Column Value doesnot match;";

					}
					if (Cell_Name.equalsIgnoreCase(LastName)) {
						System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(LastName)) == true ? status
								: status + "Fail, Column Value doesnot match;";

					}
					if (Cell_Name.equalsIgnoreCase(Email)) {
						System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(Email)) == true ? status
								: status + "Fail, Column Value doesnot match;";
					}
					if (Cell_Name.equalsIgnoreCase(Username)) {
						System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(Username)) == true ? status
								: status + "Fail, Column Value doesnot match;";
					}
					if (Cell_Name.equalsIgnoreCase(Phone)) {
						System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(Phone)) == true ? status
								: status + "Fail, Column Value doesnot match;";
					}
					if (Cell_Name.equalsIgnoreCase(CreatedDate)) {
						System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(CreatedDate)) == true ? status
								: status + "Fail, Column Value doesnot match;";
					}
					if (Cell_Name.equalsIgnoreCase(LastLogin)) {
						System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(LastLogin)) == true ? status
								: status + "Fail, Column Value doesnot match;";
					}
					if (Cell_Name.equalsIgnoreCase(Status)) {
						System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(Status)) == true ? status
								: status + "Fail, Column Value doesnot match;";
					}
					if (Cell_Name.equalsIgnoreCase(Role)) {
						System.out.println(Cell_Header);
						status = (Cell_Header.equalsIgnoreCase(Role)) == true ? status
								: status + "Fail, Column Value doesnot match;";
					}
				}

			}
		} catch (Exception e) {
			return e.getMessage() + status;
		}
		return status;

	}

	public void verifyCloudOSeriesIsNotDisplayed() throws Exception {
		CommonFunctions CF = new CommonFunctions();
		CF.VerifyObjectIsNotDisplayed("//label[contains(.,'Cloud O Series')]");
	}

	public void checkCloudOSeries() throws Exception {
		CommonFunctions CF = new CommonFunctions();
		CF.click(registrationPage.CloudOSeriestext);

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

	public void compareCustomerDetails(String actualFile, String actualSheetName, String companyCode)
			throws IOException {
		FileInputStream fis = new FileInputStream(actualFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(actualSheetName);

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int i = 0; i < rowCount + 1; i++) {

			Row row = sheet.getRow(i);
			assertFalse(row.getCell(0).getStringCellValue().equals(companyCode));
		}

	}
}
