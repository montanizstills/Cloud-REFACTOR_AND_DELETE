/*###################################################################################################
Name: LocalDriverFactory.java 
Description: Having Driver related functions
Author: Venkat Reddy Kandula
Date Created:02/06/2020
Date Modified:04/10/20202
Updated Comments:
#####################################################################################################*/

package common.libraries.webdriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;

import common.properties.CommonDataProperties;
import common.properties.ReadProperties;

public class DriverManager extends common.listener.TestNGAssertionsCustom {

	private WebDriver driver;
	public static String processName;
	public static String processName1;
	public static String processName2;
	public static String processName3;
	public static String processName4;
	public static String processName5;
	public static String Browser;
	private static String IBROWSER = null;
	private static String IBROWSER64 = null;
	private static String FBROWSER = null;
	private static String FBROWSER64 = null;
	private static String CBROWSER = null;
	private static String EBROWSER = null;
	private static String EBROWSER64 = null;
	public static FirefoxProfile Prof;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static String url = null;
	public static String sapurl = null;
	public static String VARName = null;
	public static String POD = null;
	public static String PODID = null;
	public static String adminusername = null;
	public static String adminpassword = null;
	public static String Oseriespassword = null;
	public static String franchiseurl = null;
	public static String cpaurl = null;
	public static String ECMurl = null;
	public static String ECWurl = null;
	public static String DEMOURL = null;
	public static String DEMOURL2x = null;
	public static String SANDBOXURL = null;
	public static String Environment = null;
	public static String cpaenrollurl = null;
	public static String mduportal = null;
	public static String mdudemo = null;
	public static String CommanUIURL = null;
	public static String EnvironmentType;
	public static final String Url = "Test";
	public static final int ImplicitWait = 5;
	public static final int TimeOut10 = 10000;
	public static final int TimeOut20 = 20000;
	public static final String DateTimeFormat = "MM/dd/yyyy_hh:mm a";
	public static final String DateFormat = "MM/dd/yyyy";
	public static final String DateFormat1 = "MMM dd, yyyy";
	public static String NewDate, NewTime, Script, MethodName = "", Method = "";
	public static String Temp, Temp1, sMessages = "";
	public static String csemailid = null;
	public static String cssmokeuser = null;
	public static String cstiuser = null;
	public static String cscpauser = null;
	public static String csecwuser = null;
	public static String cssmokepassword = null;
	public static String prodemailid = null;
	public static String prodsmokeuser = null;
	public static String prodcalcuser = null;
	public static String prodtiuser = null;
	public static String prodcpauser = null;
	public static String prodecwuser = null;
	public static String prodsmokepassword = null;
	public static String prodcalcpassword = null;
	public static String smokecpauser = null;
	public static String smokefranchiseuser = null;

	ReadProperties DRIVER_PROPERTIES = new ReadProperties(CommonDataProperties.CONFIG_DETAILS_FILE_PATH);
	ReadProperties URL_PROPERTIES = new ReadProperties(CommonDataProperties.ENV_PROP_FILE_PATH);
	ReadProperties USER_PROPERTIES = new ReadProperties(CommonDataProperties.USER_CREDENTIALS_FILE_PATH);
	ReadProperties ASSIGNEDPOD_PROPERTIES = new ReadProperties(CommonDataProperties.ASSIGNEDPOD_FILE_PATH);

	protected WebDriver getDriver(String Env, String browser) throws Exception {
		Browser = browser;
		adminusername = USER_PROPERTIES.getProperty("USERNAME");
		// kill();
		CleanupfilesinFolder();
		Environment = Env;
		VARName = USER_PROPERTIES.getProperty("VARNAME");
		mduportal = URL_PROPERTIES.getProperty("MDUPORTAL");
		mdudemo = URL_PROPERTIES.getProperty("MDUDEMO");
		smokecpauser = USER_PROPERTIES.getProperty("SMOKECPAUSER");
		smokefranchiseuser = USER_PROPERTIES.getProperty("SMOKEFRANCHISEUSER");
		Oseriespassword = USER_PROPERTIES.getProperty("Oseriespassword");
		POD = ASSIGNEDPOD_PROPERTIES.getProperty("PODNAME");
		switch (Env) {
		case "DEV":
		case "cpSmoke":
		case "cpSmoke44":
			EnvironmentType = "DEV";
			url = URL_PROPERTIES.getProperty("DEV");
			sapurl = URL_PROPERTIES.getProperty("DEVSAP");
			adminpassword = USER_PROPERTIES.getProperty("DEVPASSWORD");
			franchiseurl = URL_PROPERTIES.getProperty("DEVFRANCHISE");
			cpaurl = URL_PROPERTIES.getProperty("DEVCPA");
			ECMurl = URL_PROPERTIES.getProperty("ECMDEV");
			ECWurl = URL_PROPERTIES.getProperty("ECWDEV");
			DEMOURL = URL_PROPERTIES.getProperty("DEMODEV");
			DEMOURL2x = URL_PROPERTIES.getProperty("DEMODEV2x");
			DEMOURL2x = URL_PROPERTIES.getProperty("DEMODEV2x");
			SANDBOXURL = URL_PROPERTIES.getProperty("SANDBOX");
			cpaenrollurl = URL_PROPERTIES.getProperty("DEVCPAENROLL");
			CommanUIURL = URL_PROPERTIES.getProperty("DEVCOMMANUI");
			break;
		case "QA":
			EnvironmentType = "QA";
			url = URL_PROPERTIES.getProperty("QA");
			sapurl = URL_PROPERTIES.getProperty("QASAP");
			adminpassword = USER_PROPERTIES.getProperty("QAPASSWORD");
			franchiseurl = URL_PROPERTIES.getProperty("QAFRANCHISE");
			cpaurl = URL_PROPERTIES.getProperty("QACPA");
			ECMurl = URL_PROPERTIES.getProperty("ECMQA");
			ECWurl = URL_PROPERTIES.getProperty("ECWQA");
			DEMOURL = URL_PROPERTIES.getProperty("DEMOQA");
			DEMOURL2x = URL_PROPERTIES.getProperty("DEMOQA2x");
			SANDBOXURL = URL_PROPERTIES.getProperty("SANDBOX");
			cpaenrollurl = URL_PROPERTIES.getProperty("QACPAENROLL");
			CommanUIURL = URL_PROPERTIES.getProperty("QACOMMANUI");
			break;
		case "STAGE":
			EnvironmentType = "STAGE";
			VARName = "Vertex Cloud - Internal";
			url = URL_PROPERTIES.getProperty("STAGE");
			sapurl = URL_PROPERTIES.getProperty("STAGESAP");
			adminpassword = USER_PROPERTIES.getProperty("STAGEPASSWORD");
			franchiseurl = URL_PROPERTIES.getProperty("STAGEFRANCHISE");
			cpaurl = URL_PROPERTIES.getProperty("STAGECPA");
			ECMurl = URL_PROPERTIES.getProperty("ECMSTAGE");
			ECWurl = URL_PROPERTIES.getProperty("ECWSTAGE");
			DEMOURL = URL_PROPERTIES.getProperty("DEMOSTAGE");
			DEMOURL2x = URL_PROPERTIES.getProperty("DEMOSTAGE2x");
			SANDBOXURL = URL_PROPERTIES.getProperty("SANDBOX");
			cpaenrollurl = URL_PROPERTIES.getProperty("STAGECPAENROLL");
			CommanUIURL = URL_PROPERTIES.getProperty("STAGECOMMANUI");
			break;
		case "CS":
		case "CSHealthCheck":
			EnvironmentType = "CS";
			url = URL_PROPERTIES.getProperty("CS");
			cpaurl = URL_PROPERTIES.getProperty("CPACS");
			ECWurl = URL_PROPERTIES.getProperty("ECWCS");
			csemailid = USER_PROPERTIES.getProperty("CSEMAILID");
			cssmokeuser = USER_PROPERTIES.getProperty("CSSMOKEUSER");
			cstiuser = USER_PROPERTIES.getProperty("CSTIUSER");
			cscpauser = USER_PROPERTIES.getProperty("CSCPAUSER");
			csecwuser = USER_PROPERTIES.getProperty("CSECWUSER");
			cssmokepassword = USER_PROPERTIES.getProperty("CSSMOKEPASSWORD");
			break;
		case "PROD":
		case "PRODHealthCheck":
			EnvironmentType = "PROD";
			url = URL_PROPERTIES.getProperty("PROD");
			cpaurl = URL_PROPERTIES.getProperty("CPAPROD");
			ECMurl = URL_PROPERTIES.getProperty("ECMPROD");
			ECWurl = URL_PROPERTIES.getProperty("ECWPROD");
			prodemailid = USER_PROPERTIES.getProperty("PRODEMAILID");
			prodsmokeuser = USER_PROPERTIES.getProperty("PRODSMOKEUSER");
			prodtiuser = USER_PROPERTIES.getProperty("PRODTIUSER");
			prodcpauser = USER_PROPERTIES.getProperty("PRODCPAUSER");
			prodecwuser = USER_PROPERTIES.getProperty("PRODECWUSER");
			prodsmokepassword = USER_PROPERTIES.getProperty("PRODSMOKEPASSWORD");
			CommanUIURL = URL_PROPERTIES.getProperty("PRODCOMMANUI");
			prodcalcuser = USER_PROPERTIES.getProperty("PRODCALCUSER");
			prodcalcpassword = USER_PROPERTIES.getProperty("PRODCALCPASSWORD");
			break;
		case "DR":
			EnvironmentType = "DR";
			url = URL_PROPERTIES.getProperty("DR");
			prodsmokeuser = USER_PROPERTIES.getProperty("PRODSMOKEUSER");
			prodecwuser = USER_PROPERTIES.getProperty("PRODECWUSER");
			prodsmokepassword = USER_PROPERTIES.getProperty("PRODSMOKEPASSWORD");
			break;
		}

		if (driver == null) {

			File directory = new File(".//");
			IBROWSER = DRIVER_PROPERTIES.getProperty("IEBOWSERDRIVER");
			IBROWSER64 = DRIVER_PROPERTIES.getProperty("IEBOWSERDRIVER64");
			CBROWSER = DRIVER_PROPERTIES.getProperty("CHROMEDRIVER");
			FBROWSER = DRIVER_PROPERTIES.getProperty("FIREFOXDRIVER");
			FBROWSER64 = DRIVER_PROPERTIES.getProperty("FIREFOXDRIVER64");
			EBROWSER = DRIVER_PROPERTIES.getProperty("IEEDGEDRIVER");
			EBROWSER64 = DRIVER_PROPERTIES.getProperty("IEEDGEDRIVER64");
			if (browser.equalsIgnoreCase("Firefox")) {
				if (System.getProperty("sun.arch.data.model").equals("64")) {
					System.setProperty("webdriver.gecko.driver", FBROWSER64);
				} else {
					System.setProperty("webdriver.gecko.driver", FBROWSER);
				}
				Prof = new FirefoxProfile();
				// DesiredCapabilities dc = DesiredCapabilities.firefox();
				// dc.setJavascriptEnabled(true);
				Prof.setPreference("browser.download.folderList", 2);
				String strConsltFilePath = directory.getCanonicalPath() + "\\resources\\testdata\\actual";
				Prof.setPreference("browser.download.dir", strConsltFilePath);
				Prof.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"text/csv,application/x-zip-compressed,application/octet-stream,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.fdf, application/x-msdos-program, application/x-unknown-application-octet-stream, application/vnd.ms-powerpoint, application/excel, application/vnd.ms-publisher, application/x-unknown-message-rfc822, application/vnd.ms-excel, application/msword, application/x-mspublisher, application/x-tar, application/zip, application/x-gzip,application/x-stuffit,application/vnd.ms-works, application/powerpoint, application/rtf, application/postscript, application/x-gtar, video/quicktime, video/x-msvideo, video/mpeg, video/mp4, audio/x-wav, audio/x-midi, audio/x-aiff, image/png, image/jpeg, image/pjpeg, application/vnd.oasis.opendocument.text, text/plain");
				Prof.setPreference("pdfjs.disabled", true);
				Prof.setPreference("plugin.scan.Acrobat", "99.0");
				Prof.setPreference("plugin.scan.plid.all", false);
				// dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
				// UnexpectedAlertBehaviour.IGNORE);
				// dc.setCapability("marionette", true);
				Prof.setPreference("plugin.disable_full_page_plugin_for_types",
						"application/pdf,application/vnd.adobe.xfdf,application/vnd.fdf,application/vnd.adobe.xdp+xml");
				FirefoxOptions options = new FirefoxOptions();
				options.setProfile(Prof);
				// options.merge(dc);
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver = new FirefoxDriver(options);
			}
			if (browser.equalsIgnoreCase("IE")) {
				if (System.getProperty("sun.arch.data.model").equals("64")) {
					System.setProperty("webdriver.ie.driver", IBROWSER64);
				} else {
					System.setProperty("webdriver.ie.driver", IBROWSER);
				}
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
				options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				options.setCapability("EnableNativeEvents", false);
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver = new InternetExplorerDriver(options);
			}
			if (browser.equalsIgnoreCase("EDGE")) {
				if (System.getProperty("sun.arch.data.model").equals("64")) {
					System.setProperty("webdriver.edge.driver", EBROWSER64);
				} else {
					System.setProperty("webdriver.edge.driver", EBROWSER);
				}
				driver = new EdgeDriver();
			}
			if (browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", CBROWSER);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				Map<String, Object> prefs = new HashMap<String, Object>();
				// To Turns off multiple download warning
				prefs.put("profile.default_content_settings.popups", 0);
				prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				// Turns off download prompt
				prefs.put("download.prompt_for_download", false);
				prefs.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });
				options.setExperimentalOption("prefs", prefs);
				// DesiredCapabilities dc = DesiredCapabilities.chrome();
				// dc.setJavascriptEnabled(true);
				options.addArguments("test-type");
				options.addArguments("disable-popup-blocking");
				options.addArguments("disable-infobars");
				options.addArguments("start-maximized");
				options.addArguments("enable-automation");
				options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type"));
				// dc.setCapability("chrome.binary", CBROWSER);
				// dc.setCapability(ChromeOptions.CAPABILITY, options);
				options.setPageLoadStrategy(PageLoadStrategy.NONE);
				driver = new ChromeDriver(options);
			}
			if (browser.equalsIgnoreCase("Safari")) {
				driver = new SafariDriver();
			}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		return driver;
	}

	protected WebDriver getDriver(String Env, String browser, String podid) throws Exception {
		Browser = browser;
		adminusername = USER_PROPERTIES.getProperty("USERNAME");
		// kill();
		CleanupfilesinFolder();
		Environment = Env;
		VARName = USER_PROPERTIES.getProperty("VARNAME");
		mduportal = URL_PROPERTIES.getProperty("MDUPORTAL");
		mdudemo = URL_PROPERTIES.getProperty("MDUDEMO");
		smokecpauser = USER_PROPERTIES.getProperty("SMOKECPAUSER");
		smokefranchiseuser = USER_PROPERTIES.getProperty("SMOKEFRANCHISEUSER");
		Oseriespassword = USER_PROPERTIES.getProperty("Oseriespassword");
		POD = ASSIGNEDPOD_PROPERTIES.getProperty("PODNAME");
		PODID = podid;
		switch (Env) {
		case "DEV":
		case "cpSmoke":
		case "cpSmoke44":
		case "PODSmoke":
			EnvironmentType = "DEV";
			url = URL_PROPERTIES.getProperty("DEV");
			sapurl = URL_PROPERTIES.getProperty("DEVSAP");
			adminpassword = USER_PROPERTIES.getProperty("DEVPASSWORD");
			franchiseurl = URL_PROPERTIES.getProperty("DEVFRANCHISE");
			cpaurl = URL_PROPERTIES.getProperty("DEVCPA");
			ECMurl = URL_PROPERTIES.getProperty("ECMDEV");
			ECWurl = URL_PROPERTIES.getProperty("ECWDEV");
			DEMOURL = URL_PROPERTIES.getProperty("DEMODEV");
			DEMOURL2x = URL_PROPERTIES.getProperty("DEMODEV2x");
			DEMOURL2x = URL_PROPERTIES.getProperty("DEMODEV2x");
			SANDBOXURL = URL_PROPERTIES.getProperty("SANDBOX");
			cpaenrollurl = URL_PROPERTIES.getProperty("DEVCPAENROLL");
			CommanUIURL = URL_PROPERTIES.getProperty("DEVCOMMANUI");
			break;
		case "QA":
			EnvironmentType = "QA";
			url = URL_PROPERTIES.getProperty("QA");
			sapurl = URL_PROPERTIES.getProperty("QASAP");
			adminpassword = USER_PROPERTIES.getProperty("QAPASSWORD");
			franchiseurl = URL_PROPERTIES.getProperty("QAFRANCHISE");
			cpaurl = URL_PROPERTIES.getProperty("QACPA");
			ECMurl = URL_PROPERTIES.getProperty("ECMQA");
			ECWurl = URL_PROPERTIES.getProperty("ECWQA");
			DEMOURL = URL_PROPERTIES.getProperty("DEMOQA");
			DEMOURL2x = URL_PROPERTIES.getProperty("DEMOQA2x");
			SANDBOXURL = URL_PROPERTIES.getProperty("SANDBOX");
			cpaenrollurl = URL_PROPERTIES.getProperty("QACPAENROLL");
			CommanUIURL = URL_PROPERTIES.getProperty("QACOMMANUI");
			break;
		case "STAGE":
			EnvironmentType = "STAGE";
			VARName = "Vertex Cloud - Internal";
			url = URL_PROPERTIES.getProperty("STAGE");
			sapurl = URL_PROPERTIES.getProperty("STAGESAP");
			adminpassword = USER_PROPERTIES.getProperty("STAGEPASSWORD");
			franchiseurl = URL_PROPERTIES.getProperty("STAGEFRANCHISE");
			cpaurl = URL_PROPERTIES.getProperty("STAGECPA");
			ECMurl = URL_PROPERTIES.getProperty("ECMSTAGE");
			ECWurl = URL_PROPERTIES.getProperty("ECWSTAGE");
			DEMOURL = URL_PROPERTIES.getProperty("DEMOSTAGE");
			DEMOURL2x = URL_PROPERTIES.getProperty("DEMOSTAGE2x");
			SANDBOXURL = URL_PROPERTIES.getProperty("SANDBOX");
			cpaenrollurl = URL_PROPERTIES.getProperty("STAGECPAENROLL");
			CommanUIURL = URL_PROPERTIES.getProperty("STAGECOMMANUI");
			break;
		case "CS":
		case "CSHealthCheck":
			EnvironmentType = "CS";
			url = URL_PROPERTIES.getProperty("CS");
			cpaurl = URL_PROPERTIES.getProperty("CPACS");
			ECWurl = URL_PROPERTIES.getProperty("ECWCS");
			csemailid = USER_PROPERTIES.getProperty("CSEMAILID");
			cssmokeuser = USER_PROPERTIES.getProperty("CSSMOKEUSER");
			cstiuser = USER_PROPERTIES.getProperty("CSTIUSER");
			cscpauser = USER_PROPERTIES.getProperty("CSCPAUSER");
			csecwuser = USER_PROPERTIES.getProperty("CSECWUSER");
			cssmokepassword = USER_PROPERTIES.getProperty("CSSMOKEPASSWORD");
			break;
		case "PROD":
		case "PRODHealthCheck":
			EnvironmentType = "PROD";
			url = URL_PROPERTIES.getProperty("PROD");
			cpaurl = URL_PROPERTIES.getProperty("CPAPROD");
			ECMurl = URL_PROPERTIES.getProperty("ECMPROD");
			ECWurl = URL_PROPERTIES.getProperty("ECWPROD");
			prodemailid = USER_PROPERTIES.getProperty("PRODEMAILID");
			prodsmokeuser = USER_PROPERTIES.getProperty("PRODSMOKEUSER");
			prodtiuser = USER_PROPERTIES.getProperty("PRODTIUSER");
			prodcpauser = USER_PROPERTIES.getProperty("PRODCPAUSER");
			prodecwuser = USER_PROPERTIES.getProperty("PRODECWUSER");
			prodsmokepassword = USER_PROPERTIES.getProperty("PRODSMOKEPASSWORD");
			CommanUIURL = URL_PROPERTIES.getProperty("PRODCOMMANUI");
			prodcalcuser = USER_PROPERTIES.getProperty("PRODCALCUSER");
			prodcalcpassword = USER_PROPERTIES.getProperty("PRODCALCPASSWORD");
			break;
		case "DR":
			EnvironmentType = "DR";
			url = URL_PROPERTIES.getProperty("DR");
			prodsmokeuser = USER_PROPERTIES.getProperty("PRODSMOKEUSER");
			prodecwuser = USER_PROPERTIES.getProperty("PRODECWUSER");
			prodsmokepassword = USER_PROPERTIES.getProperty("PRODSMOKEPASSWORD");
			break;
		}

		if (driver == null) {

			File directory = new File(".//");
			IBROWSER = DRIVER_PROPERTIES.getProperty("IEBOWSERDRIVER");
			IBROWSER64 = DRIVER_PROPERTIES.getProperty("IEBOWSERDRIVER64");
			CBROWSER = DRIVER_PROPERTIES.getProperty("CHROMEDRIVER");
			FBROWSER = DRIVER_PROPERTIES.getProperty("FIREFOXDRIVER");
			FBROWSER64 = DRIVER_PROPERTIES.getProperty("FIREFOXDRIVER64");
			EBROWSER = DRIVER_PROPERTIES.getProperty("IEEDGEDRIVER");
			EBROWSER64 = DRIVER_PROPERTIES.getProperty("IEEDGEDRIVER64");
			if (browser.equalsIgnoreCase("Firefox")) {
				if (System.getProperty("sun.arch.data.model").equals("64")) {
					System.setProperty("webdriver.gecko.driver", FBROWSER64);
				} else {
					System.setProperty("webdriver.gecko.driver", FBROWSER);
				}
				Prof = new FirefoxProfile();
				// DesiredCapabilities dc = DesiredCapabilities.firefox();
				// dc.setJavascriptEnabled(true);
				Prof.setPreference("browser.download.folderList", 2);
				String strConsltFilePath = directory.getCanonicalPath() + "\\resources\\testdata\\actual";
				Prof.setPreference("browser.download.dir", strConsltFilePath);
				Prof.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"text/csv,application/x-zip-compressed,application/octet-stream,application/pdf,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.fdf, application/x-msdos-program, application/x-unknown-application-octet-stream, application/vnd.ms-powerpoint, application/excel, application/vnd.ms-publisher, application/x-unknown-message-rfc822, application/vnd.ms-excel, application/msword, application/x-mspublisher, application/x-tar, application/zip, application/x-gzip,application/x-stuffit,application/vnd.ms-works, application/powerpoint, application/rtf, application/postscript, application/x-gtar, video/quicktime, video/x-msvideo, video/mpeg, video/mp4, audio/x-wav, audio/x-midi, audio/x-aiff, image/png, image/jpeg, image/pjpeg, application/vnd.oasis.opendocument.text, text/plain");
				Prof.setPreference("pdfjs.disabled", true);
				Prof.setPreference("plugin.scan.Acrobat", "99.0");
				Prof.setPreference("plugin.scan.plid.all", false);
				Prof.setPreference("browser.autofocus",true);
				// dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
				// UnexpectedAlertBehaviour.IGNORE);
				// dc.setCapability("marionette", true);
				Prof.setPreference("plugin.disable_full_page_plugin_for_types",
						"application/pdf,application/vnd.adobe.xfdf,application/vnd.fdf,application/vnd.adobe.xdp+xml");
				FirefoxOptions options = new FirefoxOptions();
				options.setProfile(Prof);
				// options.setHeadless(true);
				// options.merge(dc);
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver = new FirefoxDriver(options);
			}
			if (browser.equalsIgnoreCase("IE")) {
				if (System.getProperty("sun.arch.data.model").equals("64")) {
					System.setProperty("webdriver.ie.driver", IBROWSER64);
				} else {
					System.setProperty("webdriver.ie.driver", IBROWSER);
				}
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
				options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
				options.setCapability("EnableNativeEvents", false);
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver = new InternetExplorerDriver(options);
			}
			if (browser.equalsIgnoreCase("EDGE")) {
				if (System.getProperty("sun.arch.data.model").equals("64")) {
					System.setProperty("webdriver.edge.driver", EBROWSER64);
				} else {
					System.setProperty("webdriver.edge.driver", EBROWSER);
				}
				driver = new EdgeDriver();
			}
			if (browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", CBROWSER);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				Map<String, Object> prefs = new HashMap<String, Object>();
				// To Turns off multiple download warning
				prefs.put("profile.default_content_settings.popups", 0);
				prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				// Turns off download prompt
				prefs.put("download.prompt_for_download", false);
				prefs.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });
				options.setExperimentalOption("prefs", prefs);
				// DesiredCapabilities dc = DesiredCapabilities.chrome();
				// dc.setJavascriptEnabled(true);
				options.addArguments("test-type");
				options.addArguments("disable-popup-blocking");
				options.addArguments("disable-infobars");
				options.addArguments("start-maximized");
				options.addArguments("enable-automation");
				options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type"));
				// dc.setCapability("chrome.binary", CBROWSER);
				// dc.setCapability(ChromeOptions.CAPABILITY, options);
				options.setPageLoadStrategy(PageLoadStrategy.NONE);
				driver = new ChromeDriver(options);
			}
			if (browser.equalsIgnoreCase("Safari")) {
				driver = new SafariDriver();
			}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		return driver;
	}

	public static void quitDriver(WebDriver driver) {
		driver.quit();
		driver = null;
	}

	public static void closebrowser(WebDriver driver) throws Exception {

		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static void Open(WebDriver driver, String URL) throws Exception {
		try {
			driver.get(URL);
			log.debug("user navigates to " + URL + "");
		} catch (Exception e) {
			log.debug("user not navigates to " + URL + "");
			throw e;
		}
	}

	public void kill() throws Exception {
		try {
			processName = "iexplore.exe";
			processName1 = "IEDriverServer.exe";
			processName2 = "chromedriver.exe";
			processName3 = "geckodriver64.exe";
			processName4 = "geckodriver.exe";
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

		} catch (Exception e) {
			throw e;
		}

		return false;
	}

	private Runtime getRuntime() {
		return Runtime.getRuntime();
	}

	public String CleanupfilesinFolder() throws Exception {
		File directory = new File(".//resources//testdata//actual");
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

}
