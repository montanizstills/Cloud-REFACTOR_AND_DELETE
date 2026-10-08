package common.libraries.customer;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.ManagetaxReturns;
import common.pages.ProfessionalUserLinks;

public class Prepayments extends CommonFunctions {

	private WebDriver driver;

	public Prepayments(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void EnterPrepayments(String TaxPayer, String Fillingperiod, String State, String Prepaymentform,
			String Amount, String PrepaymentType) throws Exception {
		
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		
		if (VerifyObject(driver,
				"//td/strong[contains(.,'" + State + "')]/../../td/p/a[contains(.,'Manage Prepayments')]")) {
		} else {
		waitforElement(60, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		Thread.sleep(2000);
		waitforElement(60, MTR.MANAGEPREPAYMENTSLINK, driver);
		JavaScriptclick(driver, MTR.MANAGEPREPAYMENTSLINK);
		waitUntilElementInvisibe(60, "html[@class='nprogress-busy']", driver);
		int count=0;
		if (!VerifyObject(driver, MTR.GENRATE)) {
			boolean report = false;
			do {
				JavaScriptMousehover(driver, PUL.ReturnsHover);
				Thread.sleep(2000);
				waitforElement(60, MTR.MANAGEPREPAYMENTSLINK, driver);
				JavaScriptclick(driver, MTR.MANAGEPREPAYMENTSLINK);
				waitUntilElementInvisibe(60, "html[@class='nprogress-busy']", driver);
				count = count + 1;
				selectByValue(MTR.TAXPAYER, TaxPayer);
				Thread.sleep(20000);
				if (count == 15) {
					break;
				}
				if (VerifyObject(driver, MTR.GENRATE)) {
					report = true;
				}
			} while (report == false);
		}
		
		waitforElement(60, MTR.PAGESIZE, driver);
		selectByValue(MTR.PREPAYMENTSTAXPAYER, TaxPayer);
		Thread.sleep(5000);
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(10000);
		}
		if (VerifyObject(driver,
				"//td/strong[contains(.,'" + State + "')]/../../td/p/a[contains(.,'Manage Prepayments')]")) {
			driver.findElement(
					By.xpath("//td/strong[contains(.,'" + State + "')]/../../td/p/a[contains(.,'Manage Prepayments')]"))
					.click();
			Thread.sleep(3000);

			if (PrepaymentType.equalsIgnoreCase("Prepayment State")) {

				driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'Prepayment State')]/../td/form/div/input")).sendKeys(Amount);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'Prepayment State')]/../td/form/div/button")).click();
			} else if (PrepaymentType.equalsIgnoreCase("Prepayment Rule")) {
				
				driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'Prepayment Rule')]/../td/form/div/input")).sendKeys(Amount);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'Prepayment Rule')]/../td/form/div/button")).click();
			
			} else if (PrepaymentType.equalsIgnoreCase("Prepayment Week 1")) {
				
				driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'Prepayment Week 1')]/../td/form/div/input")).sendKeys(Amount);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'Prepayment Week 1')]/../td/form/div/button")).click();
			} else if (PrepaymentType.equalsIgnoreCase("PrompTax")) {
				
				driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'PrompTax')]/../td/form/div/input")).sendKeys(Amount);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'PrompTax')]/../td/form/div/button")).click();
			} else if (PrepaymentType.equalsIgnoreCase("Use Prepayment")) {
				
				driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'Use Prepayment')]/../td/form/div/input")).sendKeys(Amount);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'Use Prepayment')]/../td/form/div/button")).click();
			} else {

				if (VerifyObject(driver, "//td/p[contains(.,'" + Prepaymentform
						+ "')]/../../td[contains(.,'Prepayment Local')]/../td/form/div/input")) {
					driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
							+ "')]/../../td[contains(.,'Prepayment Local')]/../td/form/div/input")).sendKeys(Amount);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
							+ "')]/../../td[contains(.,'Prepayment Local')]/../td/form/div/button")).click();

				} else {
					driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
							+ "')]/../../td[contains(.,'Non-location Data')]/../td/form/div/input")).sendKeys(Amount);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//td/p[contains(.,'" + Prepaymentform
							+ "')]/../../td[contains(.,'Non-location Data')]/../td/form/div/button")).click();

				}
			}
			Thread.sleep(2000);
		}
		
		driver.findElement(By.xpath("//td/strong[contains(.,'"+State+"')]/../../td/p/a[contains(.,'Close Prepayments')]")).click();
		Thread.sleep(2000);
	}

	public void GeneratePrepayment(String TaxPayer, String Fillingperiod) throws Exception {

		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(60, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		Thread.sleep(2000);
		waitforElement(60, MTR.MANAGEPREPAYMENTSLINK, driver);
		JavaScriptclick(driver, MTR.MANAGEPREPAYMENTSLINK);
		waitUntilElementInvisibe(60, "html[@class='nprogress-busy']", driver);
		waitforElement(60, MTR.PAGESIZE, driver);
		selectByValue(MTR.PREPAYMENTSTAXPAYER, TaxPayer);
		Thread.sleep(10000);
		waitforElement(60, MTR.FILLINGPERID, driver); // Wait for the Filing period list values
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		waitforElement(60, MTR.PAGESIZE, driver); // Wait for the Pagination list values
		selectByValue(MTR.PAGESIZE, "100 per page");
		Thread.sleep(5000); // to load the page after selecting the pagination list
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(300, 0)");
		Thread.sleep(1000);
		click(MTR.GENRATE);
		Thread.sleep(2000);
		waitforText(60, MTR.PrepaymentProcessing, driver);
	}

	public void DownloadPrepayment(String TaxPayer, String Fillingperiod) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(60, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		Thread.sleep(2000);
		waitforElement(60, MTR.MANAGEPREPAYMENTSLINK, driver);
		JavaScriptclick(driver, MTR.MANAGEPREPAYMENTSLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(60, MTR.PAGESIZE, driver);
		selectByValue(MTR.PREPAYMENTSTAXPAYER, TaxPayer);
		Thread.sleep(10000);
		waitforElement(60, MTR.FILLINGPERID, driver); // Wait for the Filing period list values
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		waitforElement(60, MTR.PAGESIZE, driver); // Wait for the Pagination list values
		selectByValue(MTR.PAGESIZE, "100 per page");
		Thread.sleep(5000); // to load the page after selecting the pagination list
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(300, 0)");
		Thread.sleep(1000);
		int count = 0;
		if (!VerifyObject(driver, "//button[contains(.,'Download All Most Recent')]")) {

			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
				Thread.sleep(20000); // For O Series sync refreshing the page
				count = count + 1;
				selectByValue(MTR.PREPAYMENTSTAXPAYER, TaxPayer);
				Thread.sleep(10000);
				if (count == 20) {
					assertTrue(false);
					break;
				}
				if (VerifyObject(driver, "//button[contains(.,'Download All Most Recent')]")) {
					report = true;
				}
			} while (report == false);
		}
		if (VerifyObject(driver, MTR.DWNDALL)) {
			waitforElement(180, MTR.DWNDALL, driver);
			JavaScriptclick(driver, MTR.DWNDALL);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
				System.out.println("Path" + path);
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				LatestDownloadedFile("Returns", true);
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				LatestDownloadedFile("Returns", false);
			}
		}

	}

	public void DownloadSeparatePayments(String TaxPayer, String Fillingperiod, String DwdState, String State)
			throws Exception {
		
		Robot rob = new Robot();
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String DownloadFilepath = xDownloadFiles("PrepaymentsPDFs");
		String nPath = DownloadFilepath;
		int count = 0;
		waitforElement(180, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		Thread.sleep(2000);
		waitforElement(180, MTR.MANAGEPREPAYMENTSLINK, driver);
		JavaScriptclick(driver, MTR.MANAGEPREPAYMENTSLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, MTR.DOWNLOADPDF, driver);
		Thread.sleep(5000);
		selectByValue(MTR.PREPAYMENTSTAXPAYER, TaxPayer);
		Thread.sleep(2000);
		if (VerifyObject(driver, MTR.NOPrepaymentRegistrations)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(10000);
				count = count + 1;
				selectByValue(MTR.PREPAYMENTSTAXPAYER, TaxPayer);
				Thread.sleep(10000);
				if (count == 5) {
					break;
				}
				if (!VerifyObject(driver, MTR.NOPrepaymentRegistrations)) {
					report = true;
				}
			} while (report == false);
		} else if (VerifyObject(driver, MTR.Warningmsg)) {
			boolean report = false;
			do {
				driver.navigate().refresh();
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(10000);
				count = count + 1;
				selectByValue(MTR.PREPAYMENTSTAXPAYER, TaxPayer);
				Thread.sleep(10000);
				if (count == 20) {
					break;
				}
				if (!VerifyObject(driver, MTR.Warningmsg)) {
					report = true;
				}
			} while (report == false);
		} else {
			if (!(VerifyObject(driver, MTR.DWNDALL))) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(10000);
					selectByValue(MTR.PREPAYMENTSTAXPAYER, TaxPayer);
					Thread.sleep(5000);
					selectByValue(MTR.FILLINGPERID, Fillingperiod);
					Thread.sleep(5000);
					selectByValue(MTR.PAGESIZE, "100 per page");
					Thread.sleep(5000);
					if ((VerifyObject(driver, MTR.DWNDALL))) {
						report = true;
					}
				} while (report == false);
			}
			assertTrue(MTR.DWNDALL.isDisplayed());
		}
		selectByValue(MTR.PREPAYMENTSTAXPAYER, TaxPayer);
		waitforElement(180, MTR.FILLINGPERID, driver);
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		waitUntilElementInvisibe(180, "//div[2]/p/strong[contains(.,'no prepayments')]", driver);
		waitforElement(180, MTR.PAGESIZE, driver);
		Thread.sleep(5000);
		List<WebElement> rowscount = driver.findElements(By.xpath("//table[@id='tblJurisdictions']/tbody"));
		if (DwdState != "") {
			if (DwdState.contains(";")) {
				String[] ExData = DwdState.split(";");
				for (int j = 0; j < ExData.length; j++) {
					Integer.parseInt(ExData[j]);
					for (int k = 1; k <= rowscount.size(); k++) {
						if (driver
								.findElement(
										By.xpath("//table[@id='tblJurisdictions']/tbody[" + k + "]/tr[1]/td[1]/strong"))
								.getText().equalsIgnoreCase(State)) {
							WebElement dwnldclick = driver.findElement(By.xpath("//table[@id='tblJurisdictions']/tbody["
									+ k + "]/tr[1]/td/strong[contains(.,'" + State + "')]/../../td[3]/div/div/a"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", dwnldclick);
							Thread.sleep(30000);
							rob.keyPress(KeyEvent.VK_ESCAPE);
							if (Browser.equalsIgnoreCase("Chrome")) {
								File directory = new File(".//");
								String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
								System.out.println("Path" + path);
								MoveLatestDownloadFileFromOnedirtoAnother(path);
								Thread.sleep(10000);
								LatestDownloadedFile("Returns", true);
							}
							if (Browser.equalsIgnoreCase("Firefox")) {
								LatestDownloadedFile("Returns", false);
							}
							Thread.sleep(5000);
							if (State.equalsIgnoreCase("Oklahoma")) {
								DownloadFilepath = xExtractZiReadContents(Filepath, DownloadFilepath + "\\");
								String oknames[] = { "OK Consumers Use Prepayment Return",
										"OK Sales Tax Prepayment Return", "OK Vendor's Use Prepayment Return" };
								for (int df = 0; df <= 2; df++) {
									Filepath = nPath + "\\" + oknames[df];
									DownloadedFileName = oknames[df];
									// DownloadPDFVerification(i);
								}
							} else {
								// DownloadPDFVerification(i);
							}
							k = k + 1;
						}
					}
				}
			} else if (DwdState.contains(":")) {
				String[] ExData = DwdState.split(":");
				int stRow1 = Integer.parseInt(ExData[0]);
				int endRow1 = Integer.parseInt(ExData[1]);
				for (int j = stRow1; j <= endRow1; j++) {
					for (int k = 1; k <= rowscount.size(); k++) {
						if (driver
								.findElement(
										By.xpath("//table[@id='tblJurisdictions']/tbody[" + k + "]/tr[1]/td[1]/strong"))
								.getText().equalsIgnoreCase(State)) {
							WebElement dwnldclick = driver.findElement(By.xpath("//table[@id='tblJurisdictions']/tbody["
									+ k + "]/tr[1]/td/strong[contains(.,'" + State + "')]/../../td[3]/div/div/a"));
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", dwnldclick);
							Thread.sleep(30000);
							if (Browser.equalsIgnoreCase("Chrome")) {
								File directory = new File(".//");
								String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
								System.out.println("Path" + path);
								MoveLatestDownloadFileFromOnedirtoAnother(path);
								Thread.sleep(10000);
								LatestDownloadedFile("Returns", true);
							}
							if (Browser.equalsIgnoreCase("Firefox")) {
								LatestDownloadedFile("Returns", false);
							}
							Thread.sleep(5000);
							if (State.equalsIgnoreCase("Oklahoma")) {
								DownloadFilepath = xExtractZiReadContents(Filepath, DownloadFilepath + "\\");
								String oknames[] = { "OK Consumers Use Prepayment Return.pdf",
										"OK Sales Tax Prepayment Return.pdf", "OK Vendor's Use Prepayment Return.pdf" };
								for (int df = 0; df <= 2; df++) {
									Filepath = nPath + "\\" + oknames[df];
									DownloadedFileName = oknames[df];
									// DownloadPDFVerification(i);
								}
							} else {
								// DownloadPDFVerification(i);
							}
							k = k + 1;
						}
					}
				}
			} else {
				//Integer.parseInt(DwdState);
				for (int k = 1; k <= rowscount.size(); k++) {
					if (driver
							.findElement(
									By.xpath("//table[@id='tblJurisdictions']/tbody[" + k + "]/tr[1]/td[1]/strong"))
							.getText().equalsIgnoreCase(State)) {
						WebElement dwnldclick = driver.findElement(By.xpath("//table[@id='tblJurisdictions']/tbody[" + k
								+ "]/tr[1]/td/strong[contains(.,'" + State + "')]/../../td[3]/div/div/a"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", dwnldclick);
						Thread.sleep(15000);
						if (Browser.equalsIgnoreCase("Chrome")) {
							File directory = new File(".//");
							String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
							System.out.println("Path" + path);
							MoveLatestDownloadFileFromOnedirtoAnother(path);
							Thread.sleep(10000);
							LatestDownloadedFile("Returns", true);
						}
						if (Browser.equalsIgnoreCase("Firefox")) {
							LatestDownloadedFile("Returns", false);
						}
						Thread.sleep(5000);
						if (State.equalsIgnoreCase("Oklahoma")) {
							DownloadFilepath = xExtractZiReadContents(Filepath, DownloadFilepath + "\\");
							String oknames[] = { "OK Consumers Use Prepayment Return", "OK Sales Tax Prepayment Return",
									"OK Vendor's Use Prepayment Return" };
							for (int df = 0; df <= 2; df++) {
								Filepath = nPath + "\\" + oknames[df];
								DownloadedFileName = oknames[df];
								// DownloadPDFVerification(i);
							}
						} else {
							// DownloadPDFVerification(i);
						}
						k = k + 1;
					}
				}
			}
		}
	}

	public void DownloadPayments(String TaxPayer, String Fillingperiod, String Return) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		Robot rob = new Robot();
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		click(MTR.MANAGETAXRETURNSLINK);
		waitUntilElementInvisibe(180, CNP.LOADER_XPATH, driver);
		Thread.sleep(5000);
		selectByValue(MTR.TAXPAYER, TaxPayer);
		selectByValue(MTR.FILLINGPERID, Fillingperiod);
		waitUntilElementInvisibe(180, CNP.LOADER_XPATH, driver);
		Thread.sleep(8000);
		waitforElement(180, MTR.PAGESIZE, driver);
		Select pg1 = new Select(driver.findElement(By.xpath("//span/select[@ng-model='data.pageSize']")));
		pg1.selectByVisibleText("100 per page");
		Thread.sleep(5000);
		List<WebElement> rowscount = driver.findElements(By.xpath("//table[@id='tblManageTaxReturns']/tbody"));
		for (int k = 1; k <= rowscount.size(); k++) {
			if (driver.findElement(By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td[1]"))
					.getText().equalsIgnoreCase(Return)) {
				if (!(VerifyObject(driver, CNP.DOWNLOADALLRTRNS))) {
					boolean report = false;
					do {
						driver.navigate().refresh();
						Thread.sleep(10000);
						selectByValue(MTR.TAXPAYER, TaxPayer);
						Thread.sleep(5000);
						selectByValue(MTR.FILLINGPERID, Fillingperiod);
						Thread.sleep(5000);
						Select pg2 = new Select(
								driver.findElement(By.xpath("//span/select[@ng-model='data.pageSize']")));
						pg2.selectByVisibleText("100 per page");
						Thread.sleep(5000);
						if ((VerifyObject(driver, CNP.DOWNLOADALLRTRNS))) {
							report = true;
						}
					} while (report == false);
				}
				assertTrue(CNP.DOWNLOADALLRTRNS.isDisplayed());

				WebElement dwnldclick = driver.findElement(
						By.xpath("//table[@id='tblManageTaxReturns']/tbody[" + k + "]/tr[1]/td/../td[4]/div/div/a"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", dwnldclick);
				Thread.sleep(30000);
				rob.keyPress(KeyEvent.VK_ESCAPE);
				if (Browser.equalsIgnoreCase("Chrome")) {
					File directory = new File(".//");
					String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual";
					System.out.println("Path" + path);
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					LatestDownloadedFile("Returns", true);
				}
				if (Browser.equalsIgnoreCase("Firefox")) {
					LatestDownloadedFile("Returns", false);
				}
				Thread.sleep(5000);
				k = k + 1;
			}
		}
	}

	public void DoNotPayPrepayments(String ReturnName, String FilingPeriod, String Taxpayer, String State)
			throws Exception {
		ManagetaxReturns MTR = PageFactory.initElements(driver, ManagetaxReturns.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(180, PUL.ReturnsHover, driver);
		JavaScriptMousehover(driver, PUL.ReturnsHover);
		Thread.sleep(2000);
		waitforElement(180, MTR.MANAGEPREPAYMENTSLINK, driver);
		JavaScriptclick(driver, MTR.MANAGEPREPAYMENTSLINK);
		waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
		waitforElement(180, MTR.DOWNLOADPDF, driver);
		Thread.sleep(5000);
		selectByValue(MTR.PREPAYMENTSTAXPAYER, Taxpayer);
		waitforElement(180, MTR.FILINGPERIOD, driver);
		selectByValue(MTR.FILINGPERIOD, FilingPeriod);
		Thread.sleep(2000);

		if (xInBetweenDates("01", "09").equalsIgnoreCase("TRUE")) {
			waitforElement(180,
					"//table/tbody/tr/td[contains(.,'" + State + "')]/../td/p/a[contains(.,'Manage Prepayments')]",
					driver);
			WebElement manageprepayments = driver.findElement(By.xpath(
					"//table/tbody/tr/td[contains(.,'" + State + "')]/../td/p/a[contains(.,'Manage Prepayments')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageprepayments);

			String returnname = ReturnName;
			WebElement element = driver.findElement(By.xpath(
					"//table/tbody/tr/td/table/tbody/tr/td/p[contains(.,\"" + returnname + "\")]/../../td/input"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			Thread.sleep(2000);
			click(MTR.Save);
		}
	}

}
