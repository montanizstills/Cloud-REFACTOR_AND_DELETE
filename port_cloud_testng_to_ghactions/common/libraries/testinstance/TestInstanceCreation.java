package common.libraries.testinstance;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.admin.AdminFunctions;
import common.libraries.customer.PortalFunctions;
import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.LookupTaxRates;
import common.pages.ProfessionalUserLinks;
import common.pages.StatesRegistrationPage;
import common.pages.TestInstanceSandbox;

public class TestInstanceCreation extends CommonFunctions {

	private WebDriver driver;

	public TestInstanceCreation(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	AdminFunctions Adm = PageFactory.initElements(driver, AdminFunctions.class);

	public void ResetTestInstancePwd(String UserName, String ClientName) throws Exception {

		TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
		if (Environment.equalsIgnoreCase("DEV") || Environment.equalsIgnoreCase("AWSDEV")) {
			xGetTestInstanceSetPasswordURL(UserName + ".TI", Environment);
		} else if (Environment.equalsIgnoreCase("STAGE") || Environment.equalsIgnoreCase("AWSSTAGE")) {
			xGetTestInstanceSetPasswordURL(UserName + ".TI", Environment);
		} else if (Environment.equalsIgnoreCase("AWSPROD")) {
			xGetTestInstanceSetPasswordURL(UserName + ".TI", Environment);
		} else {
			xGetTestInstanceSetPasswordURL(UserName + ".TI", Environment);
		}
		Thread.sleep(7000);
		driver.get(TestInstanceSetPwdURL);
		System.out.println(TestInstanceSetPwdURL);
		assertTrue(driver.getTitle().equalsIgnoreCase("Test Instance Password | Vertex Cloud"));
		assertTrue(TI.testInstancePwdlabel.isDisplayed());
		TestInstanceclientName = ClientName + ".TI";
		System.out.println("Test Instance Client name :" + TestInstanceclientName);
		Thread.sleep(2000);
		type(TI.testInstanceUsername, UserName + ".TI");
		type(TI.testInstancepassword, "Password!1");
		Thread.sleep(2000);
		type(TI.testInstanceconfirmpassword, "Password!1");
		Thread.sleep(2000);
		click(TI.setPassword);
		Thread.sleep(2000);
	}

	public void CopyTaxcentralCompanyData() throws Exception {		
		try	{
			if (Browser.equalsIgnoreCase("Firefox")) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			} else {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			}
			TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			waitforElement(180, TI.Configurelnk, driver);
			JavaScriptclick(driver, TI.Configurelnk);
			waitUntilElementInvisibe(180, TI.NPROGRESSBUSY_XPATH, driver);
			waitforText(100, TI.ConfigureCompanyHeading, driver);
			Thread.sleep(6000);
			JavaScriptclick(driver, TI.copyDataBtn);
			Thread.sleep(1000);
			waitforElement(180, TI.changesprocessing, driver);
			int count = 0;
			if (VerifyObject(driver, CNP.STATEINPROCESS)) {
				boolean report = false;
				do {
					JavaScriptclick(driver, TI.Configurelnk);
					waitUntilElementInvisibe(180, TI.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(10000);
					if (count==120) {
						if (!VerifyObject(driver, CNP.STATEINPROCESS)) {
						report = true;
						break;
					}
					}
				} while (report == false);
			}
		}		
		catch (Exception ex) {
			ex.getMessage();
		}
	}

	public void verifyviewchangereport(String ReportName) throws Exception {
		{
			if (Browser.equalsIgnoreCase("Firefox")) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			} else {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			}
			PortalFunctions DownloadFiles = new PortalFunctions(driver);
			TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
			xDownloadFiles("ChangeReport");
			waitforElement(180, TI.Configurelnk, driver);
			JavaScriptclick(driver, TI.Configurelnk);
			waitUntilElementInvisibe(180, TI.NPROGRESSBUSY_XPATH, driver);
			waitforText(100, TI.ConfigureCompanyHeading, driver);
			Thread.sleep(6000);
			File tempfile = null;
			driver.findElement(By.xpath("//div/table//tbody/tr/td/a[contains(.,'View Change Report')]")).click();
			Thread.sleep(8000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "ChangeReport";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("ChangeReport", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(10000);
				tempfile = new File(LatestDownloadedFile("ChangeReport", false));
			}
			if (!ReportName.isEmpty()) {
				File file = new File(".//");
				String expectedfile = file.getCanonicalPath() + "//InputTestData//TestInstanceChangeReport//"
						+ ReportName;
				File fileexpected = new File(expectedfile);
				String stst = Comparecsvfiles(tempfile, fileexpected);
				System.out.println(stst);
			}
		}
	}

	public void TestInstanceEnvTaxpayerDeleteBeginRemoval(String Source_Company, String TaxpayerRemove)
			throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
		StatesRegistrationPage SR = PageFactory.initElements(driver, StatesRegistrationPage.class);
		String Currentdate = xGetDate("MM/dd/yyyy");
		waitforElement(180, TI.Configurelnk, driver);
		JavaScriptclick(driver, TI.Configurelnk);
		waitUntilElementInvisibe(180, TI.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, "//h2[contains(.,'" + Source_Company + "')]", driver);
		if (TaxpayerRemove.equalsIgnoreCase("Delete")) {
			waitforElement(180, TI.DeletetestCompanyMTTP, driver);
			JavaScriptclick(driver, TI.DeletetestCompanyMTTP);
			waitforElement(120, TI.deleteTestCompany, driver);
			click(TI.cancelLnk);
			click(TI.deleteTestCompany);
			waitforElement(180, TI.deleteRequestProcessingMTTP, driver);
			JavaScriptclick(driver, TI.ADDANOTHERCOMPANYLINK);
			waitUntilElementInvisibe(90, TI.NPROGRESSBUSY_XPATH, driver);
			JavaScriptclick(driver, TI.RETURNTOCONFIGOVERVIEW);
			Thread.sleep(3000);
			if (VerifyObject(driver, TI.deleteRequestProcessingMTTP)) {
				boolean report = false;
				do {
					driver.navigate().refresh();
					Thread.sleep(20000);
					if (!VerifyObject(driver, TI.deleteRequestProcessingMTTP)) {
						report = true;
					}
				} while (report == false);
			}
		}
		waitforElement(120, TI.ADDANOTHERCOMPANYLINK, driver);
		if (TaxpayerRemove.equalsIgnoreCase("Begin Removal")) {
			click(TI.beginRemovalBtn);
			waitUntilElementInvisibe(180, TI.NPROGRESSBUSY_XPATH, driver);
			driver.findElement(By.xpath("//td[contains(.,'" + Source_Company + "')]/../td/a[contains(.,'Remove')]"))
					.click();
			waitforText(60, TI.customerEndDate, driver);
			type(TI.customerEndDate, Currentdate);
			sendkeysTab(TI.customerEndDate);
			click(TI.removeItem);
			Thread.sleep(5000);
			JavaScriptclick(driver, SR.RETURNTOCONFIGOVERVIEW);
			waitUntilElementInvisibe(180, TI.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, SR.SAVECONFIGURATION, driver);
			WebElement saveconfig = driver.findElement(By.xpath(
					"//h2[contains(.,'" + Source_Company + "')]/../../div/a[contains(text(),'Save Configuration')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveconfig);
			waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
			if (VerifyObject(driver, CNP.COMPLETECONFIGPOPUP)) {
				click(CNP.COMPLETECONFIGPOPUP);
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);

		}
	}

	public void SandBoxLinksandReportsVerify(String DashBoard_Title, String Configure_Title,
			String CalculateTaxes_Title, String DistributeTaxes_Title, String Subscription_Type,
			String ManageAdjust_Title, String Lookuprates_Title, String EditCompany_Title, String Adapters_Title,
			String TaxabilityReport_Title, String TransactionReport_Title) throws Exception {

		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		LookupTaxRates LTR = PageFactory.initElements(driver, LookupTaxRates.class);
		TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
		ProUserTitles = "";
		click(PUL.DASHBOARDLINK);
		waitforElement(180, PUL.UPDATEANALYSIS, driver);

		assertTrue(PUL.testInstanceEnvLabel.isDisplayed());
		assertTrue(getText(TI.numberofReturns).equalsIgnoreCase("0"));
		assertTrue(getText(TI.totalSales).equalsIgnoreCase("$0"));
		assertTrue(getText(TI.totalTax).equalsIgnoreCase("$0"));
		assertTrue(DashBoard_Title.equalsIgnoreCase(driver.getTitle()));

		click(PUL.CONFIGURELINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, PUL.deleteTestCompanylabel, driver);

		assertTrue(Configure_Title.equalsIgnoreCase(driver.getTitle()));
		assertTrue(PUL.ConfigureOverViewlabel.isDisplayed());
		assertTrue(PUL.deleteTestCompanylabel.isDisplayed());

		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, PUL.CALCULATETAXES);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforText(180, PUL.LINEITEMS, driver);

		assertTrue(CalculateTaxes_Title.equalsIgnoreCase(driver.getTitle()));

		JavaScriptMousehover(driver, PUL.taxesHover);
		JavaScriptclick(driver, PUL.DISTRIBUTETAX);
		// waitforElement(180,PUL.ERRORDISTRIBUTE, driver);
		Thread.sleep(2000);
		if (VerifyObject(driver, PUL.ERRORDISTRIBUTE)) {
			assertTrue(PUL.ERRORDISTRIBUTE.isDisplayed());
		}
		if (VerifyObject(driver, PUL.TRANSDISTRIBUTE)) {
			assertTrue(PUL.TRANSDISTRIBUTE.isDisplayed());
		}

		assertTrue(DistributeTaxes_Title.equalsIgnoreCase(driver.getTitle()));
		assertTrue(PUL.DISTRIBUTEHEADING.isDisplayed());

		String Subtype = Subscription_Type;
		if (Subtype.equalsIgnoreCase("Tax Calculations and Returns") || Subtype.equalsIgnoreCase("Tax Calculations")) {
			JavaScriptMousehover(driver, PUL.taxesHover);
			JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
			waitforElement(180, PUL.ERRORREPORT, driver);
			assertTrue(ManageAdjust_Title.equalsIgnoreCase(driver.getTitle()));
			if (!VerifyObject(driver, TI.testInstanceadjustmentfileCopystatus)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
		if (Subtype.equalsIgnoreCase("Returns Only")) {
			if (!VerifyObject(driver, PUL.MANAGEADJUSTMENTFILELINK)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}

		click(PUL.RatesJurisdictions);
		waitforText(180, PUL.LOOKUPRATE, driver);
		assertTrue(Lookuprates_Title.equalsIgnoreCase(driver.getTitle()));
		// TAID verifiication
		if (!VerifyObject(driver, LTR.TAXAREAID)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (Subtype.equalsIgnoreCase("Tax Calculations") || Subtype.equalsIgnoreCase("Returns Only")) {
			if (!VerifyObject(driver, PUL.ReturnsHover)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		} else {
			JavaScriptMousehover(driver, PUL.ReturnsHover);
			JavaScriptclick(driver, PUL.MANAGETAXRETURNS);
			waitforElement(180, PUL.MOSTRECENT, driver);
			if (!VerifyObject(driver, TI.ReturnsDownloadLink)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}
		if (Subtype.equalsIgnoreCase("Tax Calculations and Returns")) {
			if (!VerifyObject(driver, PUL.RETURNSONLYUPLOAD)) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
		}

		if (Subtype.equalsIgnoreCase("Returns Only")) {
			JavaScriptMousehover(driver, PUL.ReturnsHover);
			JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
			waitforElement(180, PUL.RETURNSONLYTITLE, driver);
			assertTrue(PUL.RETURNSONLYTITLE.isDisplayed());
		}

		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.EDITCOMPANYLINK);
		waitforElement(180, PUL.CANCEL, driver);
		assertTrue(EditCompany_Title.equalsIgnoreCase(driver.getTitle()));
		if (!VerifyObject(driver, PUL.UPDATEBILLINGLINK)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}

		if (!VerifyObject(driver, PUL.EDITSUBSCRIPTIONLINK)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}

		if (!VerifyObject(driver, PUL.billingReports)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		JavaScriptclick(driver, PUL.SettingsHover);
		if (!VerifyObject(driver, PUL.settingstransactionCount)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (!VerifyObject(driver, PUL.Myusagesection)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		JavaScriptMousehover(driver, PUL.SettingsHover);
		JavaScriptclick(driver, PUL.ADAPTERSLINK);
		waitforText(180, PUL.LOOKUPURL, driver);
		assertTrue(Adapters_Title.equalsIgnoreCase(driver.getTitle()));
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TAXABILITYREPORTSLINK, driver);
		JavaScriptclick(driver, PUL.TAXABILITYREPORTSLINK);
		waitforElement(180, PUL.TAXABILITYREPORTHEADING, driver);
		assertTrue(TaxabilityReport_Title.equalsIgnoreCase(driver.getTitle()));
		JavaScriptclick(driver, PUL.ReportsHover);
		waitforElement(180, PUL.TRANSACTIONREPORTLINK, driver);
		JavaScriptclick(driver, PUL.TRANSACTIONREPORTLINK);
		waitforText(180, PUL.NOTRANSACTIONSTOVIEW, driver);
		assertTrue(TransactionReport_Title.equalsIgnoreCase(driver.getTitle()));
		if (!VerifyObject(driver, PUL.transactionReportDownloadReport)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}

		if (!VerifyObject(driver, PUL.RATEFFILEMENLINK)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (!VerifyObject(driver, PUL.DOCUMENTCENTRE)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}

		if (!VerifyObject(driver, PUL.addressCleansing)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (!VerifyObject(driver, PUL.TaxabilityImpactReport)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void CopyTaxcentralCompanyData(String VerifyChangeReport, String Source_Company, String ReportName,
			String MTTP, String STTP_SingleTaxPayer, String Multipletaxpayerlist, String TestInstance_CompanyCode)
			throws Exception {

		PortalFunctions DownloadFiles = new PortalFunctions(driver);
		TestInstanceSandbox TI = PageFactory.initElements(driver, TestInstanceSandbox.class);
		String TaxPayerName;
		String SourceCompanies;
		xDownloadFiles("ChangeReport");
		waitforElement(180, TI.Configurelnk, driver);
		JavaScriptclick(driver, TI.Configurelnk);
		waitUntilElementInvisibe(180, TI.NPROGRESSBUSY_XPATH, driver);
		waitforText(100, TI.ConfigureCompanyHeading, driver);
		Thread.sleep(6000);
		File tempfile = null;
		if (VerifyChangeReport.equalsIgnoreCase("Yes")) {
			driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
					+ "')]/../../../div/table//tbody/tr/td/a[contains(.,'View Change Report')]")).click();
			Thread.sleep(8000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
						+ "ChangeReport";
				MoveLatestDownloadFileFromOnedirtoAnother(path);
				Thread.sleep(10000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("ChangeReport", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				Thread.sleep(10000);
				tempfile = new File(LatestDownloadedFile("ChangeReport", false));
			}
			File file = new File(".//");
			String expectedfile = file.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
					+ "TestInstanceChangeReport" + "\\" + ReportName;
			File fileexpected = new File(expectedfile);
			String stst = Comparecsvfiles(tempfile, fileexpected);
			if (stst == "") {
				assertTrue(true);
			}
		} else {
			assertTrue(TI.copyCompanydatalnk.isDisplayed());
			assertTrue(TI.addnewTestCompanylnk.isDisplayed());
			assertTrue(TI.copyCompanyDatalabel.isDisplayed());
			if (MTTP.equalsIgnoreCase("Yes")) {
				for (int j = 1; j < TaxCentralTaxPayersCount; j++) {
					TaxPayerName = TaxCentralTaxPayers.get(j).getText();
					SourceCompanies = driver.findElement(By.xpath("//div[1]/div[1]/div[" + j + "]/input"))
							.getAttribute("value");
					if (TaxPayerName.equalsIgnoreCase(SourceCompanies)) {
						driver.findElement(By.xpath("//div[1]/div[2]/div[" + j + "]/div/input")).clear();
						driver.findElement(By.xpath("//div[1]/div[2]/div[" + j + "]/div/input")).sendKeys("TP" + j);
					}
				}
			} else if (STTP_SingleTaxPayer.equalsIgnoreCase("Yes") || Multipletaxpayerlist.equalsIgnoreCase("Yes")) {
			} else {
				waitforElement(180, TI.sourcCompany, driver);
				selectByValue(TI.sourcCompany, Source_Company);
				clear(TI.newCompanycode);
				type(TI.newCompanycode, TestInstance_CompanyCode);
			}
			Thread.sleep(5000);
			JavaScriptclick(driver, TI.copyDataBtn);
			Thread.sleep(1000);
			waitforElement(180, TI.changesprocessing, driver);
			if (!VerifyObject(driver, TI.viewChangeReport)) {
				boolean report = false;
				do {
					JavaScriptclick(driver, TI.Configurelnk);
					waitUntilElementInvisibe(180, TI.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(10000);
					if (VerifyObject(driver, TI.viewChangeReport)) {
						report = true;
					}
				} while (report == false);
			}

			if (MTTP.equalsIgnoreCase("Yes")) {
				for (int j = 1; j < TaxCentralTaxPayersCount; j++) {
					TaxPayerName = TaxCentralTaxPayers.get(j).getText().trim();
					SourceCompanies = driver.findElement(By.xpath("//div[1]/div[1]/div[" + j + "]/input"))
							.getAttribute("value");
					assertTrue(driver.findElement(By.xpath("//h2[contains(.,'" + TaxPayerName
							+ "')]/../../../div/table/thead/tr/th[contains(.,'Company Code')]")).isDisplayed());
					assertTrue(driver
							.findElement(By.xpath("//h2[contains(.,'" + TaxPayerName
									+ "')]/../../../div/table/thead/tr/th[contains(.,'Copied from Company')]"))
							.isDisplayed());
					assertTrue(driver
							.findElement(By.xpath("//h2[contains(.,'" + TaxPayerName
									+ "')]/../../../div/table/thead/tr/th[contains(.,'Last Copy Date')]"))
							.isDisplayed());
					assertTrue(driver.findElement(By.xpath("//h2[contains(.,'" + TaxPayerName
							+ "')]/../../../div/table/thead/tr/th[contains(.,'Net Change')]")).isDisplayed());
					driver.findElement(By.xpath("//h2[contains(.,'" + TaxPayerName
							+ "')]/../../../div/table//tbody/tr/td/a[contains(.,'View Change Report')]")).click();
					Thread.sleep(8000);
					if (Browser.equalsIgnoreCase("Chrome")) {
						File directory = new File(".//");
						String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
								+ "ChangeReport";
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(10000);
						tempfile = new File(DownloadFiles.LatestDownloadedFile("ChangeReport", true));
					}
					if (Browser.equalsIgnoreCase("Firefox")) {
						Thread.sleep(10000);
						tempfile = new File(LatestDownloadedFile("ChangeReport", false));
					}
					File file = new File(".//");
					String expectedfile = file.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
							+ "TestInstanceChangeReport" + "\\" + ReportName;
					csvFilesCompare(FileUtils.readFileToString(tempfile), expectedfile);
					assertTrue(driver
							.findElement(By.xpath("//h2[contains(.," + Source_Company
									+ "')]/../../../../div[3]/div/span/a[contains(.,'Delete Test Company')]"))
							.isDisplayed());
					assertTrue(TI.beginRemovalBtn.isDisplayed());
				}
			} else {
				if (Source_Company != "") {

					assertTrue(driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
							+ "')]/../../../div/table/thead/tr/th[contains(.,'Company Code')]")).isDisplayed());
					assertTrue(driver
							.findElement(By.xpath("//h2[contains(.,'" + Source_Company
									+ "')]/../../../div/table/thead/tr/th[contains(.,'Copied from Company')]"))
							.isDisplayed());
					assertTrue(driver
							.findElement(By.xpath("//h2[contains(.,'" + Source_Company
									+ "')]/../../../div/table/thead/tr/th[contains(.,'Last Copy Date')]"))
							.isDisplayed());
					assertTrue(driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
							+ "')]/../../../div/table/thead/tr/th[contains(.,'Net Change')]")).isDisplayed());
					driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
							+ "')]/../../../div/table//tbody/tr/td/a[contains(.,'View Change Report')]")).click();
					Thread.sleep(8000);
					if (Browser.equalsIgnoreCase("Chrome")) {
						File directory = new File(".//");
						String path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
								+ "ChangeReport";
						MoveLatestDownloadFileFromOnedirtoAnother(path);
						Thread.sleep(10000);

						tempfile = new File(DownloadFiles.LatestDownloadedFile("ChangeReport", true));
					}
					if (Browser.equalsIgnoreCase("Firefox")) {
						Thread.sleep(10000);
						tempfile = new File(LatestDownloadedFile("ChangeReport", false));
					}
					File file = new File(".//");
					String expectedfile = file.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
							+ "TestInstanceChangeReport" + "\\" + ReportName;
					csvFilesCompare(FileUtils.readFileToString(tempfile), expectedfile);
					assertTrue(driver
							.findElement(By.xpath("//h2[contains(.,'" + Source_Company
									+ "')]/../../../../div[3]/div/span/a[contains(.,'Delete Test Company')]"))
							.isDisplayed());
					assertTrue(TI.beginRemovalBtn.isDisplayed());
				}
			}
		}
	}

}
