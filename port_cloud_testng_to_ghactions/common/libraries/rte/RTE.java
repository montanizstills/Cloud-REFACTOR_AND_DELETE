package common.libraries.rte;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.pages.ConfigurePage;
import common.pages.ManageBrackets;
import common.pages.RtePage;
import common.pages.VertexSMBCustomers;
import common.utilities.CloudData;

public class RTE extends CommonFunctions {

	private WebDriver driver;

	public RTE(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void createRTE(String Regenerate, String Title, String Name, String Description, String Date_Range,
			String Start_Date, String End_Date, String Daysprior_Validation, String DaysAfter_Validation,
			String DaysPrior, String DaysAfter, String Company, String Locations, String QuickSearch,
			String Product_Classes, String Sendsales, String Jurisdictions, String IncludeAllTaxAreas,
			String GenerateBrackets, String ImpositionType, String Include_Customer, String DuplicateName,
			String Schedule, String Schedule_Startdate, String Schedule_Starttime, String Frequency, String Weekday,
			String Manage, String Regen_Locremove, String Regen_ProdRemove, String Regen_LocAdd, String Delete,
			String Generate, String Smoke_RTE_Download, String View_Warning, String RTE_Download,
			String Last_Generationdate, String File_Name, String ReportsType, String Year, String Month)
			throws Exception {
		RtePage RTE = PageFactory.initElements(driver, RtePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
		Robot robot = new Robot();
		String DownloadFilepath = xDownloadFiles("RTE");
		String Adminpath = xDownloadFiles("AdminRTE");
		JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
		JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, RTE.QUICKSEARCH, driver);
		if (VerifyObject(driver,
				"//div[@class='alert alert-error ng-scope'][contains(.,'Recent configuration changes are currently being saved. All actions will become available once this is complete.')]")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver,
						"//div[@class='alert alert-error ng-scope'][contains(.,'Recent configuration changes are currently being saved. All actions will become available once this is complete.')]")) {
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180, RTE.CREATEEXTRACT, driver);
		if (!Regenerate.equalsIgnoreCase("Yes")) {
			// Clicking on Create Extract
			click(RTE.CREATEEXTRACT);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, RTE.NAME, driver);
			if (Title != "") {
				String title = RTE.TITLE.getText();
				assertTrue(title.equalsIgnoreCase(Title));
			}
			type(RTE.NAME, Name);
			type(RTE.DESCRIPTION, Description);
			String Daterange = Date_Range;
			switch (Daterange) {
			case "Start":
				type(RTE.STARTDATE, Start_Date);
				type(RTE.ENDDATE, End_Date);
				break;
			case "DaysPrior":
				click(RTE.DAYSPRIOR);
				// validating Error messages to Prior date
				if (Daysprior_Validation != "") {
					type(RTE.DAYSPRIORCDATE, Daysprior_Validation);
					assertTrue(RTE.DAYSPRIORERROR.isDisplayed());
					clear(RTE.DAYSPRIORCDATE);
					type(RTE.DAYSAFTERCDATE, DaysAfter_Validation);
					assertTrue(RTE.DAYSAFTERERROR.isDisplayed());
					clear(RTE.DAYSAFTERCDATE);
				}
				click(RTE.DAYSPRIORCDATE);
				type(RTE.DAYSPRIORCDATE, DaysPrior);
				click(RTE.DAYSAFTERCDATE);
				type(RTE.DAYSAFTERCDATE, DaysAfter);
				break;
			case "Nextmonth":
				click(RTE.NEXTMONTH);
				break;
			default:
				break;
			}
			if (Company.contains(";")) {
				click(RTE.SELECTCOMPLINK);
				waitforElement(60, RTE.COMPANYSELECTBUTTON, driver);
				String Comp[] = Company.split(";");
				for (int j = 0; j < Comp.length; j++) {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Comp[j] + "']/../../td[1]/input")).click();
				}
				click(RTE.COMPANYSELECTBUTTON);
			} else {
				selectByValue(RTE.SELECTCOMPANY, Company);
			}
			if (Locations != "") {
				click(RTE.SELECTLOCATIONS);
				waitforElement(60, RTE.LOCATIONSSELECTBUTTON, driver);
				if (Locations.contains(",")) {
					String locations[] = Locations.split(",");
					for (int j = 0; j < locations.length; j++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + locations[j] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.LOCATIONSQUICKSEARCH, Locations);
					Thread.sleep(200);
					driver.findElement(By.xpath("//tr[1]/td[2]/label[text()='" + Locations + "']/../../td[1]/input"))
							.click();
				} else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Locations + "']/../../td[1]/input")).click();
				}
				click(RTE.LOCATIONSSELECTBUTTON);
			}
			if (Product_Classes != "") {
				click(RTE.SELECTPRODUCTCLASSES);
				waitforElement(60, RTE.PRODUCTCLASSESSELECTBUTTON, driver);
				if (Product_Classes.contains(",")) {
					String ProdClasses[] = Product_Classes.split(",");
					for (int y = 0; y < ProdClasses.length; y++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + ProdClasses[y] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.PRODUCTCLASSESSQUICKSEARCH, Product_Classes);
					Thread.sleep(200);
					driver.findElement(
							By.xpath("//tr[1]/td[2]/label[text()='" + Product_Classes + "']/../../td[1]/input"))
							.click();
				} else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Product_Classes + "']/../../td[1]/input"))
							.click();
				}
				click(RTE.PRODUCTCLASSESSELECTBUTTON);
			}
			if (Sendsales != "") {
				click(RTE.SENDSALES);
				waitforElement(60, RTE.SELECTREGISTEREDJURISDICTIONS, driver);
				click(RTE.SELECTREGISTEREDJURISDICTIONS);
				waitforElement(60, RTE.REGISTEREDJURSELECTBUTTON, driver);
				if (Jurisdictions.contains(",")) {
					String Jurs[] = Jurisdictions.split(",");
					for (int a = 0; a < Jurs.length; a++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + Jurs[a] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.JURISDICTIONSQUICKSEARCH, Jurisdictions);
					Thread.sleep(200);
					driver.findElement(
							By.xpath("//tr[1]/td[2]/label[text()='" + Jurisdictions + "']/../../td[1]/input")).click();
				} else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Jurisdictions + "']/../../td[1]/input"))
							.click();
				}
				click(RTE.REGISTEREDJURSELECTBUTTON);
				if (IncludeAllTaxAreas.equalsIgnoreCase("Yes")) {
					click(RTE.INCLUDEALLTAXAREAS);
				}
			}
			if (GenerateBrackets != "") {
				click(RTE.GENERATEBRACKETSCHEDULE);
				waitforElement(60, RTE.SELECTIMPOSITIONTYPES, driver);
				click(RTE.SELECTIMPOSITIONTYPES);
				waitforElement(60, RTE.IMPOSITIONTYPESSELECTBUTTON, driver);
				if (ImpositionType.contains(",")) {
					String Jurs[] = ImpositionType.split(",");
					for (int b = 0; b < Jurs.length; b++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + Jurs[b] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					if(!ImpositionType.isEmpty()) {
					type(RTE.IMPOSITIONQUICKSEARCH, ImpositionType);
					Thread.sleep(200);
					driver.findElement(
							By.xpath("//tr[1]/td[2]/label[text()='" + ImpositionType + "']/../../td[1]/input")).click();
					}
				} else {
					if(!ImpositionType.isEmpty()) {
					driver.findElement(By.xpath("//td[2]/label[text()='" + ImpositionType + "']/../../td[1]/input"))
							.click();
					}
				}
				click(RTE.IMPOSITIONTYPESSELECTBUTTON);
			}
			if (Include_Customer.equalsIgnoreCase("Codes")) {
				click(RTE.CUSTOMERCLASSES);
			}
			if (Include_Customer.equalsIgnoreCase("Classes")) {
				click(RTE.CUSTOMERCODES);
			}

			// type(RTE.EXPORTFILENAME,Rtesheet.getCell(ExportfName,Data_Row).getContents());
			click(RTE.SAVE);
			if (DuplicateName != "") {
				waitforElement(180, RTE.OK, driver);
				assertTrue(RTE.DUPLICATEERRORMSG.isDisplayed());
				click(RTE.OK);
				Thread.sleep(2000);
				click(RTE.NAME);
				clear(RTE.NAME);
				type(RTE.NAME, DuplicateName);
				click(RTE.SAVE);
			}
			// Verifying Extract save success message
			if(VerifyObject(driver,RTE.SUCCESSEXTRACT)) {
			assertTrue(RTE.SUCCESSEXTRACT.isDisplayed());
			}
			// waitforElement(180,RTE.CREATEEXTRACT, driver);
			if (Schedule.equalsIgnoreCase("On")) {
				click(RTE.SCHEDULEON);
				waitforElement(60, RTE.SCHEDULESTARTDATE, driver);
				clear(RTE.SCHEDULESTARTDATE);
				click(RTE.SCHEDULESTARTDATE);
				type(RTE.SCHEDULESTARTDATE, Schedule_Startdate);
				clear(RTE.SCHEDULESTARTTIME);
				type(RTE.SCHEDULESTARTTIME, Schedule_Starttime);
				selectByValue(RTE.FREQUENCY, Frequency);
				if (Frequency.equalsIgnoreCase("Weekly")) {
					driver.findElement(By.xpath("//input[@id='" + Weekday + "']")).click();
				}
				if (Frequency.equalsIgnoreCase("Monthly")) {
					type(RTE.RUNONVALUE, Weekday);
				}
				click(RTE.SCHEDULESAVE);
				Thread.sleep(2000);
				// Verifying Schedule save success message
				//assertTrue(RTE.SUCCESSSCHEDULE.isDisplayed());
				click(RTE.RETURNTOALEXTRACTS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, RTE.CREATEEXTRACT, driver);
				// Verifying the scehdule updated correctly
				/*assertTrue(driver
						.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td[text()='" + Frequency + "']"))
						.isDisplayed());*/
			}
			if (VerifyObject(driver, RTE.RETURNTOALEXTRACTS)) {
				click(RTE.RETURNTOALEXTRACTS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, RTE.CREATEEXTRACT, driver);
			}

			// Verifying Table Column names and Quick search
			assertTrue(RTE.QUICKSEARCH.isDisplayed());
			assertTrue(RTE.COLNAME.isDisplayed());
			assertTrue(RTE.COLFREQUENCY.isDisplayed());
			assertTrue(RTE.COLNEXTGENERATETIME.isDisplayed());
			assertTrue(RTE.STATUS.isDisplayed());
			assertTrue(RTE.COLLASTGENERATED.isDisplayed());
			assertTrue(RTE.COLACTION.isDisplayed());

			// Verifying No download link before Generation
			WebElement downloadlink = driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name
					+ "']/../td/span[contains(.,'Complete')]/../../td/span/a[contains(.,'Download')]"));
			if (!VerifyObject(driver, downloadlink)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
			if (Manage != "") {
				driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Manage']"))
						.click();
				waitforElement(90, RTE.NAME, driver);
				click(RTE.NAME);
				clear(RTE.NAME);
				type(RTE.NAME, Manage);
				RTE.NAME.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				if (Regen_Locremove != "") {
					waitforElement(180, RTE.SELECTLOCATIONS, driver);
					click(RTE.SELECTLOCATIONS);
					waitforElement(60, RTE.LOCATIONSSELECTBUTTON, driver);
					driver.findElement(By.xpath("//td[2]/label[text()='" + Regen_Locremove + "']/../../td[1]/input"))
							.click();
					click(RTE.LOCATIONSSELECTBUTTON);
				}
				if (Regen_ProdRemove != "") {
					click(RTE.SELECTPRODUCTCLASSES);
					waitforElement(60, RTE.PRODUCTCLASSESSELECTBUTTON, driver);
					driver.findElement(By.xpath("//td[2]/label[text()='" + Regen_ProdRemove + "']/../../td[1]/input"))
							.click();
					click(RTE.PRODUCTCLASSESSELECTBUTTON);
				}
				if (Regen_LocAdd != "") {
					waitforElement(180, RTE.SELECTLOCATIONS, driver);
					click(RTE.SELECTLOCATIONS);
					waitforElement(60, RTE.LOCATIONSSELECTBUTTON, driver);
					driver.findElement(By.xpath("//td[2]/label[text()='" + Regen_LocAdd + "']/../../td[1]/input"))
							.click();
					click(RTE.LOCATIONSSELECTBUTTON);
				}
				// validating error message of dates with Edit
				if (Daysprior_Validation != "") {
					click(RTE.DAYSPRIOR);
					// validating Error messages to Prior date
					type(RTE.DAYSPRIORCDATE, Daysprior_Validation);
					assertTrue(RTE.DAYSPRIORERROR.isDisplayed());
					clear(RTE.DAYSPRIORCDATE);
					type(RTE.DAYSAFTERCDATE, DaysAfter_Validation);
					assertTrue(RTE.DAYSAFTERERROR.isDisplayed());
					clear(RTE.DAYSAFTERCDATE);
					click(RTE.DAYSPRIORCDATE);
					type(RTE.DAYSPRIORCDATE, DaysPrior);
					click(RTE.DAYSAFTERCDATE);
					type(RTE.DAYSAFTERCDATE, DaysAfter);
				}
				click(RTE.SAVE);
			}
			if (Delete != "") {
				waitforElement(180, RTE.CREATEEXTRACT, driver);
				driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Delete']"))
						.click();
				waitforElement(180, RTE.CANCEL, driver);
				Thread.sleep(4000);
				click(RTE.CANCEL);
				Thread.sleep(2000);
				waitUntilElementInvisibe(60, "//a/strong[text()='Cancel']", driver);
				driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Delete']"))
						.click();
				waitforElement(180, RTE.DELETE, driver);
				Thread.sleep(4000);
				click(RTE.DELETE);
			}
		}
		if ((Generate != "") || (Regenerate.equalsIgnoreCase("Yes"))) {
			if (Smoke_RTE_Download == "") {
				waitforElement(180, RTE.CREATEEXTRACT, driver);
				if (VerifyObject(driver, "//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Generate']")) {
					driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Generate']"))
							.click();
				} else {
					driver.findElement(
							By.xpath("//tbody/tr/td[text()='" + Manage + "']/../td/span/a[text()='Generate']")).click();
				}
				Thread.sleep(2000);
				waitforElement(40, RTE.PROCESSING, driver);
				JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
				JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				int c = 1;
				if (VerifyObject(driver,
						"//tbody/tr/td[text()='" + Name + "']/../td/span/i[text()='Generation in progress...']")) {
					boolean report = false;
					do {
						JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
						JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
						waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
						Thread.sleep(10000);
						c++;
						if (c == 2) {
							break;
						}
						if (!VerifyObject(driver, "//tbody/tr/td[text()='" + Name
								+ "']/../td/span/i[text()='Generation in progress...']")) {
							report = true;
						}
					} while (report == false);
				}
				waitforElement(180, RTE.PROCESSING, driver);
			}
		}
		if (View_Warning.equalsIgnoreCase("YES")) {
			if (VerifyObject(driver,
					"//tbody/tr/td[text()='" + Name + "']/../td[4]/span/i[text()='Generation in progress...']")) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(10000);
					if (!VerifyObject(driver,
							"//tbody/tr/td[text()='" + Name + "']/../td/span/i[text()='Generation in progress...']")) {
						report = true;
					}
				} while (report == false);
			}
			waitforElement(90, RTE.VIEWWARNING, driver);
			click(RTE.VIEWWARNING);
			assertTrue(RTE.WARNINGMESSAGE.isDisplayed());
			assertTrue(RTE.WARNINGTEXT.isDisplayed());
		}
		if ((RTE_Download.equalsIgnoreCase("YES")) && (Smoke_RTE_Download.equalsIgnoreCase("Yes"))) {
			if (VerifyObject(driver,
					"//tbody/tr/td[text()='" + Name + "']/../td/span/i[text()='Generation in progress...']")) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(10000);
					if (VerifyObject(driver,
							"//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]")) {
						report = true;
					}
				} while (report == false);
			}
			waitforElement(90, "//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]",
					driver);
			// Verifying last generated column value
			assertTrue(driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + Name
					+ "')]/../td/span[contains(.,'" + Last_Generationdate + "')]")).isDisplayed());
			driver.findElement(
					By.xpath("//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]"))
					.click();
			Thread.sleep(5000);
			File directory = new File(".//");
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				driver.navigate();
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_S);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "ZipRTE";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				new File(LatestDownloadedFile("resources\\testdata\\actual" + "\\" + "ZipRTE", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(5000);
				LatestDownloadedFile("ZipRTE", false);
			}
			DownloadFilepath = xExtractZiReadContents(Filepath, DownloadFilepath + "\\");
			System.out.println(DownloadFilepath);
			String filename = File_Name;
			String CSVFileclient = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "RTE"
					+ "\\" + filename + ".csv";
			System.out.println(CSVFileclient);
			userregistration.Logout();
			driver.get(url);
			Thread.sleep(5000);
			userregistration.UserLogin(adminusername, adminpassword);
			waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, VertexClientName);
			Thread.sleep(5000);
			waitforElement(180, VSC.SELECT, driver);
			waitforText(180, "//table/tbody/tr[2]/td/a[contains(.,'" + VertexClientName + "')]", driver);
			driver.findElement(By.xpath("//table/tbody/tr[2]/td/a[contains(.,'" + VertexClientName + "')]")).click();
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, VSC.SMBREPORTS, driver);
			JavaScriptclick(driver, VSC.SMBREPORTS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.FILETYPE, driver);
			selectByValue(VSC.FILETYPE, ReportsType);
			waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			waitforElement(180, VSC.YEAR, driver);
			selectByValue(VSC.YEAR, Year);
			waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			waitforElement(180, VSC.MONTH, driver);
			selectByValue(VSC.MONTH, Month);
			waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			// Verifying admin RTE See detials section
			driver.findElement(By.xpath("//a[contains(.,'" + Name + "')]/../../span/a/span[text()='See Details']"))
					.click();
			// Validating Locations
			assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + Locations + "')]")).isDisplayed());
			// Validating Product classes
			assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + Product_Classes + "')]")).isDisplayed());
			// Validating Jurisdictions
			assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + Jurisdictions + "')]")).isDisplayed());
			WebElement RteAdminDownload = driver.findElement(By.xpath("//a[contains(.,'" + Name + "')]"));
			JavaScriptclick(driver, RteAdminDownload);
			Thread.sleep(5000);
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				driver.navigate();
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_S);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {

				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "AdminZIPRTE";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				new File(LatestDownloadedFile("resources\\testdata\\actual" + "\\" + "AdminZIPRTE", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(5000);
				LatestDownloadedFile("AdminZIPRTE", false);
			}
			Adminpath = xExtractZiReadContents(Filepath, Adminpath + "\\");
			System.out.println(Adminpath);
			String CSVFileAdmin = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
					+ "AdminRTE" + "\\" + filename + ".csv";
			csvFilesCompare(CSVFileclient, CSVFileAdmin);
			userregistration.Logout();
			driver.get(url);
			Thread.sleep(5000);
			userregistration.UserLogin(VertexUserName, "Password!1");
			waitforElement(180, RTE.RETAILTAXEXTRACT, driver);
		}
	}

	public void createRTE(String Name, String description, String dateRange, String startDate, String endDate,
			String daysPrior, String daysAfter, String Company, String Locations, String locQuickSearch,
			String productClass, String productQuickSearch, String sendSales, String Jurisdictions,
			String jurisQuickSearch, String includeAllTaxAreas, String GenerateBrackets, String ImpositionType,
			String impositionQuickSearch, String includeCustomerCode, String includeCustomerClass) throws Exception {
		RtePage RTE = PageFactory.initElements(driver, RtePage.class);
		PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		new Robot();
		JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
		JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, RTE.QUICKSEARCH, driver);

		// Refreshing the page until the config is successful.
		if (VerifyObject(driver,
				"//div[@class='alert alert-error ng-scope'][contains(.,'Recent configuration changes are currently being saved. All actions will become available once this is complete.')]")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver,
						"//div[@class='alert alert-error ng-scope'][contains(.,'Recent configuration changes are currently being saved. All actions will become available once this is complete.')]")) {
					report = true;
				}
			} while (report == false);
		}

		click(RTE.CREATEEXTRACT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, RTE.NAME, driver);
		type(RTE.NAME, Name);
		type(RTE.DESCRIPTION, description);

		// Selecting Date Range
		String Daterange = dateRange;
		switch (Daterange) {
		case "Start":
			type(RTE.STARTDATE, startDate);
			type(RTE.ENDDATE, endDate);
			break;
		case "DaysPrior":
			click(RTE.DAYSPRIOR);
			click(RTE.DAYSPRIORCDATE);
			type(RTE.DAYSPRIORCDATE, daysPrior);
			click(RTE.DAYSAFTERCDATE);
			type(RTE.DAYSAFTERCDATE, daysAfter);
			break;
		case "Nextmonth":
			click(RTE.NEXTMONTH);
			break;
		default:
			break;
		}

		// Selecting the Company
		if (Company.contains(";")) {
			click(RTE.SELECTCOMPLINK);
			waitforElement(60, RTE.COMPANYSELECTBUTTON, driver);
			String Comp[] = Company.split(";");
			for (int j = 0; j < Comp.length; j++) {
				driver.findElement(By.xpath("//td[2]/label[text()='" + Comp[j] + "']/../../td[1]/input")).click();
			}
			click(RTE.COMPANYSELECTBUTTON);
		} else {
			selectByValue(RTE.SELECTCOMPANY, Company);
		}

		// Selecting the locations
		if (Locations != "") {
			click(RTE.SELECTLOCATIONS);
			waitforElement(60, RTE.LOCATIONSSELECTBUTTON, driver);
			if (Locations.contains(",")) {
				String locations[] = Locations.split(",");
				for (int j = 0; j < locations.length; j++) {
					driver.findElement(By.xpath("//td[2]/label[text()='" + locations[j] + "']/../../td[1]/input"))
							.click();
				}
			} else if (locQuickSearch.equalsIgnoreCase("Yes")) {
				type(RTE.LOCATIONSQUICKSEARCH, Locations);
				Thread.sleep(200);
				driver.findElement(By.xpath("//tr[1]/td[2]/label[text()='" + Locations + "']/../../td[1]/input"))
						.click();
			} else if (Locations.equalsIgnoreCase("All")) {
				driver.findElement(
						By.xpath("//div[@options='vm.locationSelector']/div/div/div/table/thead/tr/th/input")).click();
			}

			else {
				driver.findElement(By.xpath("//td[2]/label[text()='" + Locations + "']/../../td[1]/input")).click();
			}
			click(RTE.LOCATIONSSELECTBUTTON);
		}

		// Selecting Products
		if (productClass != "") {
			click(RTE.SELECTPRODUCTCLASSES);
			waitforElement(60, RTE.PRODUCTCLASSESSELECTBUTTON, driver);
			if (productClass.contains(",")) {
				String ProdClasses[] = productClass.split(",");
				for (int y = 0; y < ProdClasses.length; y++) {
					driver.findElement(By.xpath("//td[2]/label[text()='" + ProdClasses[y] + "']/../../td[1]/input"))
							.click();
				}
			} else if (productQuickSearch.equalsIgnoreCase("Yes")) {
				type(RTE.PRODUCTCLASSESSQUICKSEARCH, productClass);
				Thread.sleep(200);
				driver.findElement(By.xpath("//tr[1]/td[2]/label[text()='" + productClass + "']/../../td[1]/input"))
						.click();
			} else if (productClass.equalsIgnoreCase("All")) {
				driver.findElement(
						By.xpath("//div[@options='vm.productClassSelector']/div/div/div/table/thead/tr/th/input"))
						.click();
			}

			else {
				driver.findElement(By.xpath("//td[2]/label[text()='" + productClass + "']/../../td[1]/input")).click();
			}
			click(RTE.PRODUCTCLASSESSELECTBUTTON);
		}

		// Selecting Send Sales
		if (sendSales != "") {
			click(RTE.SENDSALES);
			waitforElement(60, RTE.SELECTREGISTEREDJURISDICTIONS, driver);
			click(RTE.SELECTREGISTEREDJURISDICTIONS);
			waitforElement(60, RTE.REGISTEREDJURSELECTBUTTON, driver);
			if (Jurisdictions.contains(",")) {
				String Jurs[] = Jurisdictions.split(",");
				for (int a = 0; a < Jurs.length; a++) {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Jurs[a] + "']/../../td[1]/input")).click();
				}
			} else if (jurisQuickSearch.equalsIgnoreCase("Yes")) {
				type(RTE.JURISDICTIONSQUICKSEARCH, Jurisdictions);
				Thread.sleep(200);
				driver.findElement(By.xpath("//tr[1]/td[2]/label[text()='" + Jurisdictions + "']/../../td[1]/input"))
						.click();
			} else if (Jurisdictions.equalsIgnoreCase("All")) {
				driver.findElement(
						By.xpath("//div[@options='vm.jurisdictionSelector']/div/div/div/table/thead/tr/th/input"))
						.click();

			}
			click(RTE.REGISTEREDJURSELECTBUTTON);
			if (includeAllTaxAreas.equalsIgnoreCase("Yes")) {
				click(RTE.INCLUDEALLTAXAREAS);
			}
		}

		// Selecting Bracket Schedules
		if (GenerateBrackets != "") {
			click(RTE.GENERATEBRACKETSCHEDULE);
			waitforElement(60, RTE.SELECTIMPOSITIONTYPES, driver);
			click(RTE.SELECTIMPOSITIONTYPES);
			waitforElement(60, RTE.IMPOSITIONTYPESSELECTBUTTON, driver);
			if (ImpositionType.contains(",")) {
				String Jurs[] = ImpositionType.split(",");
				for (int b = 0; b < Jurs.length; b++) {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Jurs[b] + "']/../../td[1]/input")).click();
				}
			} else if (impositionQuickSearch.equalsIgnoreCase("Yes")) {
				type(RTE.IMPOSITIONQUICKSEARCH, ImpositionType);
				Thread.sleep(200);
				driver.findElement(By.xpath("//tr[1]/td[2]/label[text()='" + ImpositionType + "']/../../td[1]/input"))
						.click();
			} else if (ImpositionType.equalsIgnoreCase("All")) {
				driver.findElement(
						By.xpath("//div[@options='vm.impositionSelector']/div/div/div/table/thead/tr/th/input"))
						.click();
			}
			click(RTE.IMPOSITIONTYPESSELECTBUTTON);
		}

		// Selecting Customer Code
		if (includeCustomerCode.equalsIgnoreCase("NO")) {
			click(RTE.CUSTOMERCODES);
		}

		// Selecting Customer Class
		if (includeCustomerClass.equalsIgnoreCase("NO")) {
			click(RTE.CUSTOMERCLASSES);
		}
		// Saving RTE filter
		click(RTE.SAVE);
		waitforElement(180, RTE.SUCCESSEXTRACT, driver);

	}

	public void generateRTE(String Name) throws Exception {
		RtePage RTE = PageFactory.initElements(driver, RtePage.class);
		PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
		JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, RTE.QUICKSEARCH, driver);
		driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Generate']")).click();
	}

	public void downloadRTE(String Name) throws Exception {
		RtePage RTE = PageFactory.initElements(driver, RtePage.class);
		PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		File directory = new File(".//");
		File rtepath = null;
		JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
		JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, RTE.QUICKSEARCH, driver);

		if (VerifyObject(driver,
				"//tbody/tr/td[text()='" + Name + "']/../td/span/i[text()='Generation in progress...']")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (VerifyObject(driver,
						"//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]")) {
					report = true;
				}
			} while (report == false);
		}

		String lastgenerated = driver
				.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + Name + "')]/../td[4]/span")).getText()
				.trim();
		String generated1 = lastgenerated.replaceAll("/", "");
		String generated2 = generated1.replaceAll(":", "");
		String generateddate = generated2.replaceAll(" ", "");
		FilterNamepath = Name + generateddate;
		waitforElement(90, "//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]",
				driver);
		WebElement download = driver.findElement(
				By.xpath("//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]"));
		JavaScriptclick(driver, download);
		Thread.sleep(30000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + FilterNamepath;
			MoveLatestDownloadFileFromOnedirtoAnother(path);
			Thread.sleep(6000);
			rtepath = new File(LatestDownloadedFile(FilterNamepath, true));
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			rtepath = new File(LatestDownloadedFile(FilterNamepath, false));
		}
		RTEFilepath = rtepath.toString();
		Path p = Paths.get(RTEFilepath);
		RTEfileName = p.getFileName().toString();
		RTEPath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + FilterNamepath + "\\"
				+ FilterNamepath;

	}

	public void createRTE(String Regenerate, String Title, String Name, String Description, String Date_Range,
			String Start_Date, String End_Date, String Daysprior_Validation, String DaysAfter_Validation,
			String DaysPrior, String DaysAfter, String Company, String Locations, String Sendsales, String QuickSearch,
			String Product_Classes, String Jurisdictions, String IncludeAllTaxAreas, String GenerateBrackets,
			String ImpositionType, String Include_Customer, String DuplicateName, String Smoke_RTE_Download,
			String Schedule, String Schedule_Startdate, String Schedule_time, String Frequency, String Weekday,
			String Manage, String Regen_Locremove, String Regen_ProdRemove, String Regen_LocAdd, String Delete,
			String Generate, String ReportsType, String Year, String Month, String View_Warning,
			String Last_Generationdate, String RTE_Download) throws Exception {
		RtePage RTE = PageFactory.initElements(driver, RtePage.class);
		PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		new Robot();
		xDownloadFiles("RTE");
		xDownloadFiles("AdminRTE");
		JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
		JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, RTE.QUICKSEARCH, driver);
		if (VerifyObject(driver,
				"//div[@class='alert alert-error ng-scope'][contains(.,'Recent configuration changes are currently being saved. All actions will become available once this is complete.')]")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver,
						"//div[@class='alert alert-error ng-scope'][contains(.,'Recent configuration changes are currently being saved. All actions will become available once this is complete.')]")) {
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180, RTE.CREATEEXTRACT, driver);
		if (!Regenerate.equalsIgnoreCase("Yes")) {
			// Clicking on Create Extract
			click(RTE.CREATEEXTRACT);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, RTE.NAME, driver);
			if (Title != "") {
				String title = RTE.TITLE.getText();
				assertTrue(title.equalsIgnoreCase(Title));
			}
			type(RTE.NAME, Name);
			type(RTE.DESCRIPTION, Description);
			String Daterange = Date_Range;
			switch (Daterange) {
			case "Start":
				type(RTE.STARTDATE, Start_Date);
				type(RTE.ENDDATE, End_Date);
				break;
			case "DaysPrior":
				click(RTE.DAYSPRIOR);
				// validating Error messages to Prior date
				if (Daysprior_Validation != "") {
					type(RTE.DAYSPRIORCDATE, Daysprior_Validation);
					assertTrue(RTE.DAYSPRIORERROR.isDisplayed());
					clear(RTE.DAYSPRIORCDATE);
					type(RTE.DAYSAFTERCDATE, DaysAfter_Validation);
					assertTrue(RTE.DAYSAFTERERROR.isDisplayed());
					clear(RTE.DAYSAFTERCDATE);
				}
				click(RTE.DAYSPRIORCDATE);
				type(RTE.DAYSPRIORCDATE, DaysPrior);
				click(RTE.DAYSAFTERCDATE);
				type(RTE.DAYSAFTERCDATE, DaysAfter);
				break;
			case "Nextmonth":
				click(RTE.NEXTMONTH);
				break;
			default:
				break;
			}
			if (Company.contains(";")) {
				click(RTE.SELECTCOMPLINK);
				waitforElement(60, RTE.COMPANYSELECTBUTTON, driver);
				String Comp[] = Company.split(";");
				for (int j = 0; j < Comp.length; j++) {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Comp[j] + "']/../../td[1]/input")).click();
				}
				click(RTE.COMPANYSELECTBUTTON);
			} else {
				selectByValue(RTE.SELECTCOMPANY, Company);
			}
			if (Locations != "") {
				click(RTE.SELECTLOCATIONS);
				waitforElement(60, RTE.LOCATIONSSELECTBUTTON, driver);
				if (Locations.contains(",")) {
					String locations[] = Locations.split(",");
					for (int j = 0; j < locations.length; j++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + locations[j] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.LOCATIONSQUICKSEARCH, Locations);
					Thread.sleep(200);
					driver.findElement(By.xpath("//tr[1]/td[2]/label[text()='" + Locations + "']/../../td[1]/input"))
							.click();
				} else if (Locations.equalsIgnoreCase("All")) {
					driver.findElement(
							By.xpath("//div[@options='vm.locationSelector']/div/div/div/table/thead/tr/th/input"))
							.click();
				}

				else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Locations + "']/../../td[1]/input")).click();
				}
				click(RTE.LOCATIONSSELECTBUTTON);
			}
			if (Product_Classes != "") {
				click(RTE.SELECTPRODUCTCLASSES);
				waitforElement(60, RTE.PRODUCTCLASSESSELECTBUTTON, driver);
				if (Product_Classes.contains(",")) {
					String ProdClasses[] = Product_Classes.split(",");
					for (int y = 0; y < ProdClasses.length; y++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + ProdClasses[y] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.PRODUCTCLASSESSQUICKSEARCH, Product_Classes);
					Thread.sleep(200);
					driver.findElement(
							By.xpath("//tr[1]/td[2]/label[text()='" + Product_Classes + "']/../../td[1]/input"))
							.click();
				} else if (Product_Classes.equalsIgnoreCase("All")) {
					driver.findElement(
							By.xpath("//div[@options='vm.productClassSelector']/div/div/div/table/thead/tr/th/input"))
							.click();
				}

				else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Product_Classes + "']/../../td[1]/input"))
							.click();
				}
				click(RTE.PRODUCTCLASSESSELECTBUTTON);
			}
			if (Sendsales != "") {
				click(RTE.SENDSALES);
				waitforElement(60, RTE.SELECTREGISTEREDJURISDICTIONS, driver);
				click(RTE.SELECTREGISTEREDJURISDICTIONS);
				waitforElement(60, RTE.REGISTEREDJURSELECTBUTTON, driver);
				if (Jurisdictions.contains(",")) {
					String Jurs[] = Jurisdictions.split(",");
					for (int a = 0; a < Jurs.length; a++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + Jurs[a] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.JURISDICTIONSQUICKSEARCH, Jurisdictions);
					Thread.sleep(200);
					driver.findElement(
							By.xpath("//tr[1]/td[2]/label[text()='" + Jurisdictions + "']/../../td[1]/input")).click();
				} else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Jurisdictions + "']/../../td[1]/input"))
							.click();
				}
				click(RTE.REGISTEREDJURSELECTBUTTON);
				if (IncludeAllTaxAreas.equalsIgnoreCase("Yes")) {
					click(RTE.INCLUDEALLTAXAREAS);
				}
			}
			if (GenerateBrackets != "") {
				click(RTE.GENERATEBRACKETSCHEDULE);
				waitforElement(60, RTE.SELECTIMPOSITIONTYPES, driver);
				click(RTE.SELECTIMPOSITIONTYPES);
				waitforElement(60, RTE.IMPOSITIONTYPESSELECTBUTTON, driver);
				if (ImpositionType.contains(",")) {
					String Jurs[] = ImpositionType.split(",");
					for (int b = 0; b < Jurs.length; b++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + Jurs[b] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.IMPOSITIONQUICKSEARCH, ImpositionType);
					Thread.sleep(200);
					driver.findElement(
							By.xpath("//tr[1]/td[2]/label[text()='" + ImpositionType + "']/../../td[1]/input")).click();
				} else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + ImpositionType + "']/../../td[1]/input"))
							.click();
				}
				click(RTE.IMPOSITIONTYPESSELECTBUTTON);
			}
			if (Include_Customer.equalsIgnoreCase("Codes")) {
				click(RTE.CUSTOMERCLASSES);
			}
			if (Include_Customer.equalsIgnoreCase("Classes")) {
				click(RTE.CUSTOMERCODES);
			}

			// type(RTE.EXPORTFILENAME,Rtesheet.getCell(ExportfName,Data_Row).getContents());
			click(RTE.SAVE);
			waitforElement(180, RTE.SUCCESSEXTRACT, driver);
			if (DuplicateName != "") {
				waitforElement(180, RTE.OK, driver);
				assertTrue(RTE.DUPLICATEERRORMSG.isDisplayed());
				click(RTE.OK);
				Thread.sleep(2000);
				click(RTE.NAME);
				clear(RTE.NAME);
				type(RTE.NAME, DuplicateName);
				click(RTE.SAVE);
			}
			// Verifying Extract save success message
			assertTrue(RTE.SUCCESSEXTRACT.isDisplayed());
			// waitforElement(180,RTE.CREATEEXTRACT, driver);
			if (Schedule.equalsIgnoreCase("On")) {
				// driver.findElement(By.xpath("//tbody/tr/td[text()='"+Rtesheet.getCell(Name,Data_Row).getContents()+"']/../td/span/a[text()='Manage']")).click();
				// waitforElement(90, RTE.NAME, driver);
				click(RTE.SCHEDULEON);
				waitforElement(60, RTE.SCHEDULESTARTDATE, driver);
				if (Schedule_Startdate != "") {
					clear(RTE.SCHEDULESTARTDATE);
					click(RTE.SCHEDULESTARTDATE);
					type(RTE.SCHEDULESTARTDATE, Schedule_Startdate);
				}
				clear(RTE.SCHEDULESTARTTIME);
				type(RTE.SCHEDULESTARTTIME, Schedule_time);
				selectByValue(RTE.FREQUENCY, Frequency);
				if (Frequency.equalsIgnoreCase("Weekly")) {
					driver.findElement(By.xpath("//input[@id='" + Weekday + "']")).click();
				}
				if (Frequency.equalsIgnoreCase("Monthly")) {
					type(RTE.RUNONVALUE, Weekday);
				}
				click(RTE.SCHEDULESAVE);
				Thread.sleep(2000);
				// Verifying Schedule save success message
				assertTrue(RTE.SUCCESSEXTRACT.isDisplayed());
				// click(RTE.RTECANCEL);
				click(RTE.RETURNTOALEXTRACTS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, RTE.CREATEEXTRACT, driver);
				// Verifying the scehdule updated correctly
				/*
				 * assertTrue(driver .findElement(By.xpath("//tbody/tr/td[text()='" + Name +
				 * "']/../td[text()='" + Frequency + "']")) .isDisplayed());
				 */
			}
			if (VerifyObject(driver, RTE.RETURNTOALEXTRACTS)) {
				click(RTE.RETURNTOALEXTRACTS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, RTE.CREATEEXTRACT, driver);
			}

			// Verifying Table Column names and Quick search
			assertTrue(RTE.QUICKSEARCH.isDisplayed());
			assertTrue(RTE.COLNAME.isDisplayed());
			assertTrue(RTE.COLFREQUENCY.isDisplayed());
			assertTrue(RTE.COLNEXTGENERATETIME.isDisplayed());
			assertTrue(RTE.STATUS.isDisplayed());
			assertTrue(RTE.COLLASTGENERATED.isDisplayed());
			assertTrue(RTE.COLACTION.isDisplayed());

			// Verifying No download link before Generation
			WebElement downloadlink = driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name
					+ "']/../td/span[contains(.,'Complete')]/../../td/span/a[contains(.,'Download')]"));
			if (!VerifyObject(driver, downloadlink)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
			if (Manage != "") {
				driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Manage']"))
						.click();
				waitforElement(90, RTE.NAME, driver);
				click(RTE.NAME);
				clear(RTE.NAME);
				type(RTE.NAME, Manage);
				RTE.NAME.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				if (Regen_Locremove != "") {
					waitforElement(180, RTE.SELECTLOCATIONS, driver);
					click(RTE.SELECTLOCATIONS);
					waitforElement(60, RTE.LOCATIONSSELECTBUTTON, driver);
					driver.findElement(By.xpath("//td[2]/label[text()='" + Regen_Locremove + "']/../../td[1]/input"))
							.click();
					click(RTE.LOCATIONSSELECTBUTTON);
				}
				if (Regen_ProdRemove != "") {
					click(RTE.SELECTPRODUCTCLASSES);
					waitforElement(60, RTE.PRODUCTCLASSESSELECTBUTTON, driver);
					driver.findElement(By.xpath("//td[2]/label[text()='" + Regen_ProdRemove + "']/../../td[1]/input"))
							.click();
					click(RTE.PRODUCTCLASSESSELECTBUTTON);
				}
				if (Regen_LocAdd != "") {
					waitforElement(180, RTE.SELECTLOCATIONS, driver);
					click(RTE.SELECTLOCATIONS);
					waitforElement(60, RTE.LOCATIONSSELECTBUTTON, driver);
					driver.findElement(By.xpath("//td[2]/label[text()='" + Regen_LocAdd + "']/../../td[1]/input"))
							.click();
					click(RTE.LOCATIONSSELECTBUTTON);
				}
				// validating error message of dates with Edit
				if (Daysprior_Validation != "") {
					click(RTE.DAYSPRIOR);
					// validating Error messages to Prior date
					type(RTE.DAYSPRIORCDATE, Daysprior_Validation);
					assertTrue(RTE.DAYSPRIORERROR.isDisplayed());
					clear(RTE.DAYSPRIORCDATE);
					type(RTE.DAYSAFTERCDATE, DaysAfter_Validation);
					assertTrue(RTE.DAYSAFTERERROR.isDisplayed());
					clear(RTE.DAYSAFTERCDATE);
					click(RTE.DAYSPRIORCDATE);
					type(RTE.DAYSPRIORCDATE, DaysPrior);
					click(RTE.DAYSAFTERCDATE);
					type(RTE.DAYSAFTERCDATE, DaysAfter);
				}
				click(RTE.SAVE);
			}
			if (Delete != "") {
				waitforElement(180, RTE.CREATEEXTRACT, driver);
				driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Delete']"))
						.click();
				waitforElement(180, RTE.CANCEL, driver);
				Thread.sleep(4000);
				click(RTE.CANCEL);
				Thread.sleep(2000);
				waitUntilElementInvisibe(60, "//a/strong[text()='Cancel']", driver);
				driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Delete']"))
						.click();
				waitforElement(180, RTE.DELETE, driver);
				Thread.sleep(4000);
				click(RTE.DELETE);
			}
		}
		if ((Generate != "") || (Regenerate.equalsIgnoreCase("Yes"))) {
			if (Smoke_RTE_Download == "") {
				waitforElement(180, RTE.CREATEEXTRACT, driver);
				if (VerifyObject(driver, "//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Generate']")) {
					driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Generate']"))
							.click();
				} else {
					driver.findElement(
							By.xpath("//tbody/tr/td[text()='" + Manage + "']/../td/span/a[text()='Generate']")).click();
				}
				Thread.sleep(2000);
				JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
				JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(40, RTE.PROCESSING, driver);
				JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
				JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				int c = 1;
				if (VerifyObject(driver,
						"//tbody/tr/td[text()='" + Name + "']/../td/span/i[text()='Generation in progress...']")) {
					boolean report = false;
					do {
						c = c + 1;
						JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
						JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
						waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
						Thread.sleep(10000);
						if (c == 2) {
							break;
						}
						if (!VerifyObject(driver, "//tbody/tr/td[text()='" + Name
								+ "']/../td/span/i[text()='Generation in progress...']")) {
							report = true;
						}
					} while (report == false);
				}
				waitforElement(180, RTE.PROCESSING, driver);
			}
		}
		if (View_Warning.equalsIgnoreCase("YES")) {
			if (VerifyObject(driver,
					"//tbody/tr/td[text()='" + Name + "']/../td[4]/span/i[text()='Generation in progress...']")) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(10000);
					if (!VerifyObject(driver,
							"//tbody/tr/td[text()='" + Name + "']/../td/span/i[text()='Generation in progress...']")) {
						report = true;
					}
				} while (report == false);
			}
			waitforElement(90, RTE.VIEWWARNING, driver);
			click(RTE.VIEWWARNING);
			assertTrue(RTE.WARNINGMESSAGE.isDisplayed());
			assertTrue(RTE.WARNINGTEXT.isDisplayed());
		}
		if ((RTE_Download.equalsIgnoreCase("YES")) || (Smoke_RTE_Download.equalsIgnoreCase("Yes"))) {
			int c = 1;
			if (VerifyObject(driver,
					"//tbody/tr/td[text()='" + Name + "']/../td/span/i[text()='Generation in progress...']")) {
				boolean report = false;
				do {
					c = c + 1;
					driver.navigate().refresh();
					Thread.sleep(10000);
					if (c == 50) {
						break;
					}
					if (VerifyObject(driver,
							"//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]")) {
						report = true;
					}
				} while (report == false);
			}
			waitforElement(90, "//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]",
					driver);
			// Verifying last generated column value
			if (VerifyObject(driver, "driver.findElement(By.xpath("// table/tbody/tr/td[contains(.,'" + Name
					+ "')]/../td/span[contains(.,'" + Last_Generationdate + "')]")) {
				assertTrue(driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + Name
						+ "')]/../td/span[contains(.,'" + Last_Generationdate + "')]")).isDisplayed());
			}
			/*
			 * driver.findElement( By.xpath("//table/tbody/tr/td[contains(.,'" + Name +
			 * "')]/../td/span/a[contains(.,'Download')]")) .click(); Thread.sleep(5000);
			 * File directory = new File(".//"); if (Browser.equalsIgnoreCase("IE")) {
			 * Thread.sleep(2000); robot.keyPress(KeyEvent.VK_ENTER);
			 * robot.keyRelease(KeyEvent.VK_ENTER); Thread.sleep(2000); driver.navigate();
			 * Thread.sleep(2000); robot.keyPress(KeyEvent.VK_ALT);
			 * robot.keyPress(KeyEvent.VK_S); robot.keyRelease(KeyEvent.VK_ALT);
			 * robot.keyRelease(KeyEvent.VK_S); Thread.sleep(5000); } if
			 * (Browser.equalsIgnoreCase("Chrome")) { String path =
			 * directory.getCanonicalPath() +
			 * "\\" + "resources\\testdata\\actual" + "\\" + "ZipRTE";
			 * System.out.println("Path" + path);
			 * MoveLatestDownloadFileFromOnedirtoAnother(path); Thread.sleep(10000); new
			 * File(LatestDownloadedFile("resources\\testdata\\actual" + "\\" + "ZipRTE",
			 * true)); } if (Browser.equalsIgnoreCase("Firefox")) { Thread.sleep(5000);
			 * LatestDownloadedFile("ZipRTE", false); } DownloadFilepath =
			 * xExtractZiReadContents(Filepath, DownloadFilepath + "\\");
			 * System.out.println(DownloadFilepath); String filename =
			 * CloudData.get_data("File_Name"); String CSVFileclient =
			 * directory.getCanonicalPath() +
			 * "\\" + "resources\\testdata\\actual" + "\\" + "RTE" + "\\" + filename +
			 * ".csv"; System.out.println(CSVFileclient);
			 */
			/*
			 * if (ReportsType != "") { userRegistration.Logout(); driver.get(url);
			 * Thread.sleep(5000); userRegistration.UserLogin(adminusername, adminpassword);
			 * waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
			 * click(VSC.MANAGESMBCUSTLINK); waitUntilElementInvisibe(180,
			 * "html[@class='nprogress-busy']", driver); waitforElement(180,
			 * VSC.SMBCUSTNAMESB, driver); type(VSC.SMBCUSTNAMESB, VertexClientName);
			 * Thread.sleep(5000); waitforElement(180, VSC.SELECT, driver); waitforText(180,
			 * "//table/tbody/tr[2]/td/a[contains(.,'" + VertexClientName + "')]", driver);
			 * driver.findElement(By.xpath("//table/tbody/tr[2]/td/a[contains(.,'" +
			 * VertexClientName + "')]")) .click(); waitUntilElementInvisibe(180,
			 * "html[@class='nprogress-busy']", driver); waitforElement(180, VSC.SMBREPORTS,
			 * driver); JavaScriptclick(driver, VSC.SMBREPORTS);
			 * waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			 * waitforElement(180, VSC.FILETYPE, driver); selectByValue(VSC.FILETYPE,
			 * ReportsType); waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			 * waitforElement(180, VSC.YEAR, driver); selectByValue(VSC.YEAR, Year);
			 * waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			 * waitforElement(180, VSC.MONTH, driver); selectByValue(VSC.MONTH, Month);
			 * waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver); // Verifying
			 * admin RTE See detials section driver.findElement(By.xpath("//a[contains(.,'"
			 * + Name + "')]/../../span/a/span[text()='See Details']")) .click(); //
			 * Validating Locations
			 * assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + Locations +
			 * "')]")).isDisplayed()); // Validating Product classes assertTrue(
			 * driver.findElement(By.xpath("//label[contains(.,'" + Product_Classes +
			 * "')]")).isDisplayed()); // Validating Jurisdictions
			 * assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + Jurisdictions
			 * + "')]")).isDisplayed()); WebElement RteAdminDownload =
			 * driver.findElement(By.xpath("//a[contains(.,'" + Name + "')]"));
			 * JavaScriptclick(driver, RteAdminDownload); Thread.sleep(5000); if
			 * (Browser.equalsIgnoreCase("IE")) { Thread.sleep(2000);
			 * robot.keyPress(KeyEvent.VK_ENTER); robot.keyRelease(KeyEvent.VK_ENTER);
			 * Thread.sleep(2000); driver.navigate(); Thread.sleep(2000);
			 * robot.keyPress(KeyEvent.VK_ALT); robot.keyPress(KeyEvent.VK_S);
			 * robot.keyRelease(KeyEvent.VK_ALT); robot.keyRelease(KeyEvent.VK_S);
			 * Thread.sleep(5000); } if (Browser.equalsIgnoreCase("Chrome")) {
			 * 
			 * String path = directory.getCanonicalPath() +
			 * "\\" + "resources\\testdata\\actual" + "\\" + "AdminZIPRTE";
			 * System.out.println("Path" + path);
			 * MoveLatestDownloadFileFromOnedirtoAnother(path); Thread.sleep(10000); new
			 * File(LatestDownloadedFile("resources\\testdata\\actual" +
			 * "\\" + "AdminZIPRTE", true)); } if (Browser.equalsIgnoreCase("Firefox")) {
			 * Thread.sleep(5000); LatestDownloadedFile("AdminZIPRTE", false); } Adminpath =
			 * xExtractZiReadContents(Filepath, Adminpath + "\\");
			 * System.out.println(Adminpath); String CSVFileAdmin =
			 * directory.getCanonicalPath() +
			 * "\\" + "resources\\testdata\\actual" + "\\" + "AdminRTE" +
			 * "\\" + filename + ".csv"; csvFilesCompare(CSVFileclient, CSVFileAdmin);
			 * userRegistration.Logout(); driver.get(url); Thread.sleep(5000);
			 * userRegistration.UserLogin(VertexUserName, "Password!1"); waitforElement(180,
			 * RTE.RETAILTAXEXTRACT, driver); }
			 */ }
	}

	public void RTE_Regenerate(String Regenerate, String Name, String Company, String Locations, String Sendsales,
			String QuickSearch, String Product_Classes, String Jurisdictions, String IncludeAllTaxAreas,
			String GenerateBrackets, String ImpositionType, String Include_Customer, String Smoke_RTE_Download,
			String Schedule, String Schedule_Startdate, String Schedule_time, String Frequency, String Weekday,
			String Manage, String Delete, String Generate, String ReportsType, String Year, String Month,
			String View_Warning, String Last_Generationdate, String RTE_Download) throws Exception {

		RtePage RTE = PageFactory.initElements(driver, RtePage.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);

		Robot robot = new Robot();
		String DownloadFilepath = xDownloadFiles("RTE");
		String Adminpath = xDownloadFiles("AdminRTE");
		JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
		JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, RTE.QUICKSEARCH, driver);
		if (VerifyObject(driver,
				"//div[@class='alert alert-error ng-scope'][contains(.,'Recent configuration changes are currently being saved. All actions will become available once this is complete.')]")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver,
						"//div[@class='alert alert-error ng-scope'][contains(.,'Recent configuration changes are currently being saved. All actions will become available once this is complete.')]")) {
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180, RTE.CREATEEXTRACT, driver);
		if ((Generate != "") || (Regenerate.equalsIgnoreCase("Yes"))) {
			driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Manage']")).click();
			waitforElement(90, RTE.NAME, driver);
			if (Locations != "") {
				click(RTE.SELECTLOCATIONS);
				waitforElement(60, RTE.LOCATIONSSELECTBUTTON, driver);
				if (Locations.contains(",")) {
					String locations[] = Locations.split(",");
					for (int j = 0; j < locations.length; j++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + locations[j] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.LOCATIONSQUICKSEARCH, Locations);
					Thread.sleep(200);
					driver.findElement(By.xpath("//tr[1]/td[2]/label[text()='" + Locations + "']/../../td[1]/input"))
							.click();
				} else if (Locations.equalsIgnoreCase("All")) {
					driver.findElement(
							By.xpath("//div[@options='vm.locationSelector']/div/div/div/table/thead/tr/th/input"))
							.click();
				}

				else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Locations + "']/../../td[1]/input")).click();
				}
				click(RTE.LOCATIONSSELECTBUTTON);
			}

			if (Product_Classes != "") {
				click(RTE.SELECTPRODUCTCLASSES);
				waitforElement(60, RTE.PRODUCTCLASSESSELECTBUTTON, driver);
				if (Product_Classes.contains(",")) {
					String ProdClasses[] = Product_Classes.split(",");
					for (int y = 0; y < ProdClasses.length; y++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + ProdClasses[y] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.PRODUCTCLASSESSQUICKSEARCH, Product_Classes);
					Thread.sleep(200);
					driver.findElement(
							By.xpath("//tr[1]/td[2]/label[text()='" + Product_Classes + "']/../../td[1]/input"))
							.click();
				} else if (Product_Classes.equalsIgnoreCase("All")) {
					driver.findElement(
							By.xpath("//div[@options='vm.productClassSelector']/div/div/div/table/thead/tr/th/input"))
							.click();
				}

				else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Product_Classes + "']/../../td[1]/input"))
							.click();
				}
				click(RTE.PRODUCTCLASSESSELECTBUTTON);
			}

			if (Sendsales != "") {
				click(RTE.SENDSALES);
				waitforElement(60, RTE.SELECTREGISTEREDJURISDICTIONS, driver);
				click(RTE.SELECTREGISTEREDJURISDICTIONS);
				waitforElement(60, RTE.REGISTEREDJURSELECTBUTTON, driver);
				if (Jurisdictions.contains(",")) {
					String Jurs[] = Jurisdictions.split(",");
					for (int a = 0; a < Jurs.length; a++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + Jurs[a] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.JURISDICTIONSQUICKSEARCH, Jurisdictions);
					Thread.sleep(200);
					driver.findElement(
							By.xpath("//tr[1]/td[2]/label[text()='" + Jurisdictions + "']/../../td[1]/input")).click();
				} else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + Jurisdictions + "']/../../td[1]/input"))
							.click();
				}
				click(RTE.REGISTEREDJURSELECTBUTTON);
				if (IncludeAllTaxAreas.equalsIgnoreCase("Yes")) {
					click(RTE.INCLUDEALLTAXAREAS);
				}
			}
			if (GenerateBrackets != "") {
				click(RTE.GENERATEBRACKETSCHEDULE);
				waitforElement(60, RTE.SELECTIMPOSITIONTYPES, driver);
				click(RTE.SELECTIMPOSITIONTYPES);
				waitforElement(60, RTE.IMPOSITIONTYPESSELECTBUTTON, driver);
				if (ImpositionType.contains(",")) {
					String Jurs[] = ImpositionType.split(",");
					for (int b = 0; b < Jurs.length; b++) {
						driver.findElement(By.xpath("//td[2]/label[text()='" + Jurs[b] + "']/../../td[1]/input"))
								.click();
					}
				} else if (QuickSearch.equalsIgnoreCase("Yes")) {
					type(RTE.IMPOSITIONQUICKSEARCH, ImpositionType);
					Thread.sleep(200);
					driver.findElement(
							By.xpath("//tr[1]/td[2]/label[text()='" + ImpositionType + "']/../../td[1]/input")).click();
				} else {
					driver.findElement(By.xpath("//td[2]/label[text()='" + ImpositionType + "']/../../td[1]/input"))
							.click();
				}
				click(RTE.IMPOSITIONTYPESSELECTBUTTON);
			}
			if (Include_Customer.equalsIgnoreCase("Codes")) {
				click(RTE.CUSTOMERCLASSES);
			}
			if (Include_Customer.equalsIgnoreCase("Classes")) {
				click(RTE.CUSTOMERCODES);
			}

			// type(RTE.EXPORTFILENAME,Rtesheet.getCell(ExportfName,Data_Row).getContents());
			click(RTE.SAVE);
			// Verifying Extract save success message
			assertTrue(RTE.SUCCESSEXTRACT.isDisplayed());
			// waitforElement(180,RTE.CREATEEXTRACT, driver);
			if (Schedule.equalsIgnoreCase("On")) {
				// driver.findElement(By.xpath("//tbody/tr/td[text()='"+Rtesheet.getCell(Name,Data_Row).getContents()+"']/../td/span/a[text()='Manage']")).click();
				// waitforElement(90, RTE.NAME, driver);
				click(RTE.SCHEDULEON);
				waitforElement(60, RTE.SCHEDULESTARTDATE, driver);
				clear(RTE.SCHEDULESTARTDATE);
				click(RTE.SCHEDULESTARTDATE);
				type(RTE.SCHEDULESTARTDATE, Schedule_Startdate);
				clear(RTE.SCHEDULESTARTTIME);
				type(RTE.SCHEDULESTARTTIME, Schedule_time);
				selectByValue(RTE.FREQUENCY, Frequency);
				if (Frequency.equalsIgnoreCase("Weekly")) {
					driver.findElement(By.xpath("//input[@id='" + Weekday + "']")).click();
				}
				if (Frequency.equalsIgnoreCase("Monthly")) {
					type(RTE.RUNONVALUE, Weekday);
				}
				click(RTE.SCHEDULESAVE);
				Thread.sleep(2000);
				// Verifying Schedule save success message
				assertTrue(RTE.SUCCESSSCHEDULE.isDisplayed());
				// click(RTE.RTECANCEL);
				click(RTE.RETURNTOALEXTRACTS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, RTE.CREATEEXTRACT, driver);
				// Verifying the scehdule updated correctly
				assertTrue(driver
						.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td[text()='" + Frequency + "']"))
						.isDisplayed());
			}
			if (VerifyObject(driver, RTE.RETURNTOALEXTRACTS)) {
				click(RTE.RETURNTOALEXTRACTS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, RTE.CREATEEXTRACT, driver);
			}

			if (Smoke_RTE_Download == "") {
				waitforElement(180, RTE.CREATEEXTRACT, driver);
				if (VerifyObject(driver, "//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Generate']")) {
					driver.findElement(By.xpath("//tbody/tr/td[text()='" + Name + "']/../td/span/a[text()='Generate']"))
							.click();
				} else {
					driver.findElement(
							By.xpath("//tbody/tr/td[text()='" + Manage + "']/../td/span/a[text()='Generate']")).click();
				}
				Thread.sleep(2000);
				driver.navigate().refresh();
				waitforElement(40, RTE.PROCESSING, driver);
				JavaScriptMousehover(driver, RTE.RETAILTAXEXTRACT);
				JavaScriptclick(driver, RTE.MANAGERETAILTAXEXTRACT);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				int c = 1;
				if (VerifyObject(driver,
						"//tbody/tr/td[text()='" + Name + "']/../td/span/i[text()='Generation in progress...']")) {
					boolean report = false;
					do {
						driver.navigate().refresh();
						Thread.sleep(10000);
						if (c == 2) {
							break;
						}
						if (!VerifyObject(driver, "//tbody/tr/td[text()='" + Name
								+ "']/../td/span/i[text()='Generation in progress...']")) {
							report = true;
						}
					} while (report == false);
				}
				waitforElement(180, RTE.PROCESSING, driver);
			}
		}

		if ((RTE_Download.equalsIgnoreCase("YES")) || (Smoke_RTE_Download.equalsIgnoreCase("Yes"))) {
			if (VerifyObject(driver,
					"//tbody/tr/td[text()='" + Name + "']/../td/span/i[text()='Generation in progress...']")) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(10000);
					if (VerifyObject(driver,
							"//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]")) {
						report = true;
					}
				} while (report == false);
			}
			waitforElement(90, "//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]",
					driver);
			// Verifying last generated column value
			assertTrue(driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'" + Name
					+ "')]/../td/span[contains(.,'" + Last_Generationdate + "')]")).isDisplayed());
			driver.findElement(
					By.xpath("//table/tbody/tr/td[contains(.,'" + Name + "')]/../td/span/a[contains(.,'Download')]"))
					.click();
			Thread.sleep(5000);
			File directory = new File(".//");
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				driver.navigate();
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_S);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "ZipRTE";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				new File(LatestDownloadedFile("resources\\testdata\\actual" + "\\" + "ZipRTE", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(5000);
				LatestDownloadedFile("ZipRTE", false);
			}
			DownloadFilepath = xExtractZiReadContents(Filepath, DownloadFilepath + "\\");
			System.out.println(DownloadFilepath);
			String filename = CloudData.get_data("File_Name");
			String CSVFileclient = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "RTE"
					+ "\\" + filename + ".csv";
			System.out.println(CSVFileclient);
			userRegistration.Logout();
			driver.get(url);
			Thread.sleep(5000);
			userRegistration.UserLogin(adminusername, adminpassword);
			waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, VertexClientName);
			Thread.sleep(5000);
			waitforElement(180, VSC.SELECT, driver);
			waitforText(180, "//table/tbody/tr[2]/td/a[contains(.,'" + VertexClientName + "')]", driver);
			driver.findElement(By.xpath("//table/tbody/tr[2]/td/a[contains(.,'" + VertexClientName + "')]")).click();
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(180, VSC.SMBREPORTS, driver);
			JavaScriptclick(driver, VSC.SMBREPORTS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.FILETYPE, driver);
			selectByValue(VSC.FILETYPE, ReportsType);
			waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			waitforElement(180, VSC.YEAR, driver);
			selectByValue(VSC.YEAR, Year);
			waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			waitforElement(180, VSC.MONTH, driver);
			selectByValue(VSC.MONTH, Month);
			waitUntilElementInvisibe(180, CNP.PLEASEWAIT_XPATH, driver);
			// Verifying admin RTE See detials section
			driver.findElement(By.xpath("//a[contains(.,'" + Name + "')]/../../span/a/span[text()='See Details']"))
					.click();
			// Validating Locations
			assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + Locations + "')]")).isDisplayed());
			// Validating Product classes
			assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + Product_Classes + "')]")).isDisplayed());
			// Validating Jurisdictions
			assertTrue(driver.findElement(By.xpath("//label[contains(.,'" + Jurisdictions + "')]")).isDisplayed());
			WebElement RteAdminDownload = driver.findElement(By.xpath("//a[contains(.,'" + Name + "')]"));
			JavaScriptclick(driver, RteAdminDownload);
			Thread.sleep(5000);
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				driver.navigate();
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_S);
				Thread.sleep(5000);
			}
			if (Browser.equalsIgnoreCase("Chrome")) {

				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "AdminZIPRTE";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				new File(LatestDownloadedFile("resources\\testdata\\actual" + "\\" + "AdminZIPRTE", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(5000);
				LatestDownloadedFile("AdminZIPRTE", false);
			}
			Adminpath = xExtractZiReadContents(Filepath, Adminpath + "\\");
			System.out.println(Adminpath);
			String CSVFileAdmin = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
					+ "AdminRTE" + "\\" + filename + ".csv";
			csvFilesCompare(CSVFileclient, CSVFileAdmin);
			userRegistration.Logout();
			driver.get(url);
			Thread.sleep(5000);
			userRegistration.UserLogin(VertexUserName, "Password!1");
			waitforElement(180, RTE.RETAILTAXEXTRACT, driver);
		}
	}

	public void ManageBracket(String Company, String Jurisdiction, String Tax_Rate, String Imposition_Type,
			String MaxBasisRow, String MaxBasisAmount, String ImpositionType) throws Exception {
		ManageBrackets MBS = PageFactory.initElements(driver, ManageBrackets.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);

		xAutoIT();
		xUploadAdjustmentFile("ManageBrackets");
		JavaScriptMousehover(driver, MBS.RETAILTAXEXTRACT);
		JavaScriptclick(driver, MBS.MANAGEBRACKETSCHEDULE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(60, MBS.PRODUCTSSEARCH, driver);
		new Robot();
		if (VerifyObject(driver,
				"//div[@class='alert alert-warning ng-scope'][contains(.,'An extract is currently running. You will be notified when it is complete.')]")) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				Thread.sleep(10000);
				if (!VerifyObject(driver,
						"//div[@class='alert alert-warning ng-scope'][contains(.,'An extract is currently running. You will be notified when it is complete.')]")) {
					report = true;
				}
			} while (report == false);
		}
		// selecting company for stpp user
		if (Company != "") {
			selectByValue(MBS.COMPANY, Company);
			Thread.sleep(5000);
		}
		selectByValue(MBS.JURISDICTION, Jurisdiction);
		waitforElement(90, MBS.SHOW, driver);
		// Verifying Imposition Type and Tax Rate
		if (Tax_Rate != "") {
			assertTrue(driver.findElement(By.xpath("//tbody/tr[3]/td[1][text()='" + Imposition_Type + "']"))
					.isDisplayed());
			assertTrue(driver.findElement(By.xpath("//tbody/tr[3]/td[2][text()='" + Tax_Rate + "']")).isDisplayed());
		} else {
			// Verifying No tax Rate
			assertTrue(MBS.NOBRACKETSCHEDULES.isDisplayed());
		}

		// clicking on show and verifying the values
		click(MBS.SHOW);
		waitforElement(90, MBS.HIDE, driver);
		click(MBS.HIDE);
		waitforElement(90, MBS.SHOW, driver);
		// Clicking on Edit
		driver.findElement(By.xpath("//td[text()='" + ImpositionType + "']/../td[5]/div/a")).click();
		Thread.sleep(3000);
		driver.findElement(
				By.xpath("//td[text()='" + ImpositionType + "']/../td[5]/div/a/../ul/li/a[contains(text(),'Edit')]"))
				.click();
		waitforElement(90, MBS.CANCEL, driver);

		// Max basis Row change [Getting row number from sheet]
		driver.findElement(By.xpath("//div[" + MaxBasisRow + "]/div/div[3]/input")).sendKeys(Keys.CONTROL + "a");
		driver.findElement(By.xpath("//div[" + MaxBasisRow + "]/div/div[3]/input")).sendKeys(Keys.CONTROL + "x");
		driver.findElement(By.xpath("//div[" + MaxBasisRow + "]/div/div[3]/input")).sendKeys(MaxBasisAmount);
		Thread.sleep(3000);
		click(MBS.MAXBASISROW2);
		Thread.sleep(3000);
		click(MBS.SAVE);
		waitforElement(180, MBS.JURISDICTION, driver);

	}

	public void Editbrackettable(WebDriver driver, String Minvalue, String Maxvalue, String TaxAmount,
			int Editbracketrows, String value) throws Exception {
		String Minvalues[] = Minvalue.split(",");
		String Maxvalues[] = Maxvalue.split(",");
		String Taxamountvalues[] = TaxAmount.split(",");
		for (int l = 2; l < Editbracketrows - 3; l++) {

			driver.findElement(By.xpath("//div[" + l + "]/div/div[2]/input")).getAttribute("value");
			assertTrue(driver.findElement(By.xpath("//div[" + l + "]/div/div[2]/input")).getAttribute("value")
					.equalsIgnoreCase(Minvalues[l - 2]));

			driver.findElement(By.xpath("//div[" + l + "]/div/div[3]/input")).getAttribute("value");
			assertTrue(driver.findElement(By.xpath("//div[" + l + "]/div/div[3]/input")).getAttribute("value")
					.equalsIgnoreCase(Maxvalues[l - 2]));

			driver.findElement(By.xpath("//div[" + l + "]/div/div[4]/input")).getAttribute("value");
			assertTrue(driver.findElement(By.xpath("//div[" + l + "]/div/div[4]/input")).getAttribute("value")
					.equalsIgnoreCase(Taxamountvalues[l - 2]));

		}
	}

}
