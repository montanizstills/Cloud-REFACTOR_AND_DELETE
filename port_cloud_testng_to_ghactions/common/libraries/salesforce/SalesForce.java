package common.libraries.salesforce;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.pages.Cpa_ManageUsers;
import common.pages.LoginPage;
import common.pages.ProfessionalUserLinks;
import common.pages.SalesForcePage;
import common.pages.VertexSMBCustomers;
import jxl.Sheet;

public class SalesForce extends CommonFunctions {
	private WebDriver driver;

	public SalesForce(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public void SalesforceActiveStatusVerify(String Firstname, String Lastname, String UserStatus) throws Exception {
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		waitforElement(180, LP.SALESFORCECONTACTS, driver);
		JavaScriptclick(driver, LP.SALESFORCECONTACTS);
		Thread.sleep(5000);
		waitforElement(180, LP.SFSEARCHCONTACTS, driver);
		type(LP.SFSEARCHCONTACTS, Firstname);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(4000);
		if (!VerifyObject(driver,
				"//div[@class='autoHeight col-0-wrap col-1-wrap col-2-wrap col-3-wrap col-4-wrap col-5-wrap hideRowNumberColumn hideSelection forceListViewManagerGrid']/div/div/div/div/table/tbody/tr/th/span/a")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, LP.SALESFORCECONTACTS);
				Thread.sleep(5000);
				waitforElement(180, LP.SFSEARCHCONTACTS, driver);
				type(LP.SFSEARCHCONTACTS, Firstname);
				Actions action1 = new Actions(driver);
				action1.sendKeys(Keys.ENTER).build().perform();
				Thread.sleep(4000);
				if (VerifyObject(driver,
						"//div[@class='autoHeight col-0-wrap col-1-wrap col-2-wrap col-3-wrap col-4-wrap col-5-wrap hideRowNumberColumn hideSelection forceListViewManagerGrid']/div/div/div/div/table/tbody/tr/th/span/a")) {
					report = true;
				}
			} while (report == false);
		}
		waitforElement(180,
				"//div[@class='autoHeight col-0-wrap col-1-wrap col-2-wrap col-3-wrap col-4-wrap col-5-wrap hideRowNumberColumn hideSelection forceListViewManagerGrid']/div/div/div/div/table/tbody/tr/th/span/a",
				driver);
		WebElement contact = driver.findElement(By.xpath(
				"//div[@class='autoHeight col-0-wrap col-1-wrap col-2-wrap col-3-wrap col-4-wrap col-5-wrap hideRowNumberColumn hideSelection forceListViewManagerGrid']/div/div/div/div/table/tbody/tr/th/span/a"));
		click(contact);
		Thread.sleep(11000);
		waitforElement(180, "//a[contains(.,'Details')]", driver);
		((JavascriptExecutor) driver).executeScript("scroll(0,500);");
		waitforElement(180, LP.EDITACTIVEPORTALUSER, driver);
		JavaScriptclick(driver, LP.EDITACTIVEPORTALUSER);
		waitforElement(60, LP.ACTIVEPORTALUSER, driver);
		switch (UserStatus) {
		case "Active":
			assertTrue(LP.ACTIVEPORTALUSER.isEnabled());
			break;

		case "Deleted":
			if (!LP.ACTIVEPORTALUSER.isSelected()) {
				assertTrue(true);
			} else {
				assertFalse(false);
			}
			break;

		}
	}

	public void SalesforceLogout() throws Exception {
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		waitforElement(180, LP.SALESFORCELOGOUTICON, driver);
		JavaScriptclick(driver, LP.SALESFORCELOGOUTICON);
		waitforElement(180, LP.SALESFORCELOGOUT, driver);
		JavaScriptclick(driver, LP.SALESFORCELOGOUT);
		waitforElement(180, LP.SALESFORCEUSERNAME, driver);
	}

	public void DeleteUser(String Username) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Cpa_ManageUsers NU = PageFactory.initElements(driver, Cpa_ManageUsers.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, NU.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, NU.VIEWALLUAERS, driver);
		JavaScriptclick(driver, NU.VIEWALLUAERS);
		waitforElement(180, NU.EXPORTBUTTON, driver);
		WebElement delete = driver.findElement(
				By.xpath("//table/tbody/tr/td[contains(.,'" + Username + "')]/../td/a[contains(.,'Delete')]"));
		JavaScriptclick(driver, delete);
		waitforElement(180, NU.DELETEUSERBUTTON, driver);
		JavaScriptclick(driver, NU.DELETEUSERBUTTON);
	}

	public void ReactivateUser(String Username) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Cpa_ManageUsers NU = PageFactory.initElements(driver, Cpa_ManageUsers.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(180, PUL.DASHBOARDLINK, driver);
		JavaScriptclick(driver, PUL.DASHBOARDLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptclick(driver, NU.SETTINGS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, NU.VIEWALLUAERS, driver);
		JavaScriptclick(driver, NU.VIEWALLUAERS);
		waitforElement(180, NU.EXPORTBUTTON, driver);
		WebElement Reactivate = driver.findElement(
				By.xpath("//table/tbody/tr/td[contains(.,'" + Username + "')]/../td/a[contains(.,'Reactivate')]"));
		JavaScriptclick(driver, Reactivate);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + Username + "')]/../td/a[contains(.,'Delete')]",
				driver);
	}

	public void SalesforceInactiveReason(String TestPath, String value) throws Exception {
		String status = "";
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			String[] Data = value.split(";");
			String Verification = "";
			SalesForcePage SF = PageFactory.initElements(driver, SalesForcePage.class);
			PageFactory.initElements(driver, ConfigurePage.class);
			Sheet SalesForceSheet = xGetdtSheet(TestPath, value);
			int count = 0;
			int View = 3, Verify_Status = 4, Customer_Status = 10;
			int stRow, endRow, Data_Row;
			if (Data[2].contains(":")) {
				String[] stdata = Data[2].split(":");
				stRow = Integer.parseInt(stdata[0]);
				endRow = Integer.parseInt(stdata[1]);
			} else {
				stRow = 2;
				endRow = Data.length - 1;
			}
			for (int i = stRow; i <= endRow; i++) {
				if (Data[2].contains(":")) {
					Data_Row = i;
				} else {
					Data_Row = Integer.parseInt(Data[i]);
				}
				// VertexClientName="PQPClient124161917";
				type(SF.SEARCHINPUT, VertexClientName);
				waitforElement(180, SF.SEARCHBUTTON, driver);
				click(SF.SEARCHBUTTON);
				waitforElement(180, SF.SEARCHAGAIN, driver);
				if (VerifyObject(driver, "//div[@id='searchResultsWarningMessageBox']")) {
					boolean report = false;
					do {
						driver.navigate().refresh();
						Thread.sleep(30000);
						count = count + 1;
						if (count == 20) {// status=status+"Fail;There is no user in salesforce";
							System.out.println("There is no user in salesforce");
							break;
						}
						if (!VerifyObject(driver, "//div[@id='searchResultsWarningMessageBox']")) {
							report = true;
						}
					} while (report == false);
				}
				if (VerifyObject(driver, "//div[@id='searchResultsWarningMessageBox']")) {
					Verification = "";
					Verification = "Fail,Due to Sync Issue,Customer is not displayed in Salesforce;";
					status = status + Verification;
				} else {
					// Verfying Deleted Customer Status in Sales Force
					if ((SalesForceSheet.getCell(View, Data_Row)).getContents() != "") {
						driver.findElement(By.xpath("//th/a[contains(.,'" + VertexClientName + "')]")).click();
						waitforElement(180,
								"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Name')]/../td/div[contains(.,'"
										+ VertexClientName + "')]",
								driver);
						Thread.sleep(4000);
						waitforElement(180,
								"//table[@class='list']/tbody/tr/th[contains(.,'Contact Status')]/../../tr/td[contains(.,'Active')]",
								driver);
						if (SalesForceSheet.getCell(Verify_Status, Data_Row).getContents().equalsIgnoreCase("Yes")) {
							waitforElement(180,
									"//table[@class='list']/tbody/tr/th[contains(.,'Contact Status')]/../../tr/td[contains(.,'Inactive')]",
									driver);
							Verification = "";
							Verification = VerifyObject(driver,
									"//table[@class='list']/tbody/tr/th[contains(.,'Contact Status')]/../../tr/td[contains(.,'Inactive')]") == true
											? Verification
											: Verification + "Fail,Customer Deleted is not  displayed;";
							status = status + Verification;
						} else {
							Verification = "";
							Verification = VerifyObject(driver,
									"//table[@class='list']/tbody/tr/th[contains(.,'Contact Status')]/../../tr/td[contains(.,'Active')]") == true
											? Verification
											: Verification + "Fail,Customer is not active after Delete;";
							status = status + Verification;
						}
					}
					// Verifying Pending Billing/Registered/Authorized Status in Sales Force as per
					// POR-4048 in 4.3.1
					if (SalesForceSheet.getCell(Customer_Status, Data_Row).getContents() != "") {
						driver.findElement(By.xpath("//th/a[contains(.,'" + VertexClientName + "')]")).click();
						waitforElement(180,
								"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Name')]/../td/div[contains(.,'"
										+ VertexClientName + "')]",
								driver);
						if (SalesForceSheet.getCell(Customer_Status, Data_Row).getContents()
								.equalsIgnoreCase("Pending Billing")) {
							waitforElement(180,
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Status Description')]/../../tr/td/div[contains(.,'Pending Billing')]",
									driver);
							Verification = "";
							Verification = (VerifyObject(driver,
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Status Description')]/../../tr/td/div[contains(.,'Pending Billing')]")) == true
											? Verification
											: Verification + "Fail, Pending Billing User Status Not displayed";
							status = status + Verification;
						}
						if (SalesForceSheet.getCell(Customer_Status, Data_Row).getContents()
								.equalsIgnoreCase("Registered")) {
							waitforElement(180,
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Status Description')]/../../tr/td/div[contains(.,'Registered')]",
									driver);
							Verification = "";
							Verification = (VerifyObject(driver,
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Status Description')]/../../tr/td/div[contains(.,'Registered')]")) == true
											? Verification
											: Verification + "Fail, Registered User Status Not displayed";
							status = status + Verification;
						}
						if (SalesForceSheet.getCell(Customer_Status, Data_Row).getContents()
								.equalsIgnoreCase("Authorized")) {
							waitforElement(180,
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Status Description')]/../../tr/td/div[contains(.,'Authorized')]",
									driver);
							Verification = "";
							Verification = (VerifyObject(driver,
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Status Description')]/../../tr/td/div[contains(.,'Authorized')]")) == true
											? Verification
											: Verification + "Fail, Authorized User Status Not displayed";
							status = status + Verification;
						}
						if (SalesForceSheet.getCell(Customer_Status, Data_Row).getContents()
								.equalsIgnoreCase("Initialized")) {
							waitforElement(180,
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Status Description')]/../../tr/td/div[contains(.,'Initialized')]",
									driver);
							Verification = "";
							Verification = (VerifyObject(driver,
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Status Description')]/../../tr/td/div[contains(.,'Initialized')]")) == true
											? Verification
											: Verification + "Fail, Initialized User Status Not displayed";
							status = status + Verification;
							String LivevsTesting = driver.findElement(By.xpath(
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Live vs. Testing')]/../td[2]/div"))
									.getText().trim();
							Verification = "";
							Verification = (LivevsTesting.isEmpty()) == true ? Verification
									: Verification + "Fail, Live vs. Testing status should be blank";
							status = status + Verification;
						}
						if (SalesForceSheet.getCell(Customer_Status, Data_Row).getContents()
								.equalsIgnoreCase("Pending Billing")) {
							waitforElement(180,
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Status Description')]/../../tr/td/div[contains(.,'Pending Billing')]",
									driver);
							Verification = "";
							Verification = (VerifyObject(driver,
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Account Status Description')]/../../tr/td/div[contains(.,'Pending Billing')]")) == true
											? Verification
											: Verification + "Fail, Pending Billing User Status Not displayed";
							status = status + Verification;
							String LivevsTesting = driver.findElement(By.xpath(
									"//table[@class='detailList']/tbody/tr/td[contains(.,'Live vs. Testing')]/../td[2]/div"))
									.getText().trim();
							Verification = "";
							Verification = (LivevsTesting.isEmpty()) == true ? Verification
									: Verification + "Fail, Live vs. Testing status should be blank";
							status = status + Verification;
						}
					}
				}
			}
			if (status.isEmpty()) {
				status = "Pass";
			}
		} catch (Exception e) {
		}
	}

	public void SalesForceLogin(String Username, String Password) throws Exception {
		SalesForcePage SFP = PageFactory.initElements(driver, SalesForcePage.class);
		waitforElement(180, SFP.SFUSERNAME, driver);
		type(SFP.SFUSERNAME, Username);
		waitforElement(180, SFP.SFPASSWORD, driver);
		type(SFP.SFPASSWORD, Password);
		Thread.sleep(3000);
		click(SFP.SFLOGIN);
		waitforElement(180, SFP.ACCOUNTS, driver);
	}

	public void SalesforceMasterpartner(String Accountname) throws Exception {
		SalesForcePage SFP = PageFactory.initElements(driver, SalesForcePage.class);
		waitforElement(180, SFP.ACCOUNTS, driver);
		click(SFP.ACCOUNTS);
		Thread.sleep(3000);
		waitforElement(180, SFP.SEARCHINPUT, driver);
		type(SFP.SEARCHINPUT, Accountname);
		Thread.sleep(2000);
		click(SFP.SEARCHBUTTON);
		waitforElement(180, "//table/tbody/tr/th/a[contains(.,'" + Accountname + "')]", driver);
		WebElement accountname = driver
				.findElement(By.xpath("//table/tbody/tr/th/a[contains(.,'" + Accountname + "')]"));
		JavaScriptclick(driver, accountname);
		waitforElement(180, SFP.MASTERPARTNER, driver);
		String MasterPartner = SFP.MASTERPARTNER.getText().trim();
		System.out.println(MasterPartner);
		String Var = SFP.VAR.getText().trim();
		assertTrue(MasterPartner.equalsIgnoreCase(""));
		assertTrue(Var.isEmpty());
	}

	public void Logout() throws Exception {
		SalesForcePage SFP = PageFactory.initElements(driver, SalesForcePage.class);
		waitforElement(180, SFP.LOGOUTDROPDOWN, driver);
		JavaScriptclick(driver, SFP.LOGOUTDROPDOWN);
		waitforElement(180, SFP.LOGOUT, driver);
		JavaScriptclick(driver, SFP.LOGOUT);
		waitforElement(180, SFP.SFUSERNAME, driver);
	}

	public void VerifyGoliveDateInAdmin(String ClientName) throws Exception {
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		// ProfessionalUserLinks PUL = PageFactory.initElements(driver,
		// ProfessionalUserLinks.class);
		if (Browser.equalsIgnoreCase("Firefox")) {
			click(VSC.MANAGESMBCUSTLINK);
		} else {
			JavaScriptclick(driver, VSC.MANAGESMBCUSTLINK);
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, VSC.SMBCUSTNAMESB, driver);
		type(VSC.SMBCUSTNAMESB, ClientName);
		Thread.sleep(5000);
		waitforElement(180, "//table/tbody/tr/td[contains(.,'" + ClientName + "')]", driver);
		WebElement clientname = driver
				.findElement(By.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + ClientName
						+ "')]/../../../td[contains(text(),'Authorized')]/../td[1]/div/a"));
		JavaScriptclick(driver, clientname);
		Thread.sleep(2000);
		waitforElement(180, VSC.SMBCUSTOMERREPORT, driver);
		JavaScriptclick(driver, VSC.SMBCUSTOMERREPORT);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		Thread.sleep(3000);
		String GLDate = driver.findElement(By.xpath("//div/span[contains(.,'Go Live Date:')]/../span[2]")).getText();
		String[] Date = GLDate.split(" ");
		GoliveDate = Date[0];
		System.out.println("Golive date is " + GoliveDate);
	}

	public void VerifyGoliveDateInSalesforce(String OraclePartynumber, String Accountname, String GoliveDate,
			String Taxpayername) throws Exception {
		SalesForcePage SFP = PageFactory.initElements(driver, SalesForcePage.class);
		waitforElement(180, SFP.SFACCOUNTS, driver);
		click(SFP.SFACCOUNTS);
		Thread.sleep(3000);
		waitforElement(180, SFP.SFSEARCH, driver);
		click(SFP.SFSEARCH);
		waitforElement(180, SFP.SFSEARCHFIELD, driver);
		type(SFP.SFSEARCHFIELD, OraclePartynumber);
		Thread.sleep(2000);
		SFP.SFSEARCHFIELD.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		waitforElement(180, "//h2/a[contains(.,'" + Accountname + "')]", driver);
		WebElement accountname = driver.findElement(By.xpath("//h2/a[contains(.,'" + Accountname + "')]"));
		JavaScriptclick(driver, accountname);
		waitforElement(180, SFP.SHOWALL, driver);
		Thread.sleep(7000);
		click(SFP.SHOWALL);
		waitforElement(180, SFP.TAXPAYERCOMPANYINFORMATION, driver);
		JavaScriptclick(driver, SFP.TAXPAYERCOMPANYINFORMATION);
		waitforElement(180, "//a[contains(.,'" + Taxpayername + "')]", driver);
		WebElement taxpayer = driver.findElement(By.xpath("//a[contains(.,'" + Taxpayername + "')]"));
		JavaScriptclick(driver, taxpayer);
		waitforElement(60,
				"//div/div/div/span[contains(.,'Company Go-Live Date')]/../../div/span/slot/slot/lightning-formatted-text[contains(.,'"
						+ GoliveDate + "')]",
				driver);
		WebElement TaxpayerGolivedate = driver.findElement(By.xpath(
				"//div/div/div/span[contains(.,'Company Go-Live Date')]/../../div/span/slot/slot/lightning-formatted-text[contains(.,'"
						+ GoliveDate + "')]"));
		assertTrue(TaxpayerGolivedate.isDisplayed());

	}

	public void VerifyTaxpayerDeatilsInSalesforce(String OraclePartynumber, String Accountname, String Taxpayername,
			String TaxpayerCode) throws Exception {
		SalesForcePage SFP = PageFactory.initElements(driver, SalesForcePage.class);
		waitforElement(180, SFP.SFACCOUNTS, driver);
		click(SFP.SFACCOUNTS);
		Thread.sleep(3000);
		waitforElement(180, SFP.SFSEARCH, driver);
		click(SFP.SFSEARCH);
		waitforElement(180, SFP.SFSEARCHFIELD, driver);
		type(SFP.SFSEARCHFIELD, OraclePartynumber);
		Thread.sleep(2000);
		SFP.SFSEARCHFIELD.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		waitforElement(180, "//h2/a[contains(.,'" + Accountname + "')]", driver);
		WebElement accountname = driver.findElement(By.xpath("//h2/a[contains(.,'" + Accountname + "')]"));
		JavaScriptclick(driver, accountname);
		waitforElement(180, SFP.SHOWALL, driver);
		Thread.sleep(7000);
		click(SFP.SHOWALL);
		waitforElement(180, SFP.TAXPAYERCOMPANYINFORMATION, driver);
		JavaScriptclick(driver, SFP.TAXPAYERCOMPANYINFORMATION);
		waitforElement(180, "//a[contains(.,'" + Taxpayername + "')]", driver);
		WebElement taxpayer = driver.findElement(By.xpath("//a[contains(.,'" + Taxpayername + "')]"));
		JavaScriptclick(driver, taxpayer);
		waitforElement(60,
				"//div/div/div/span[contains(.,'Company Code')]/../../div/span/slot/slot/lightning-formatted-text[contains(.,'"
						+ TaxpayerCode + "')]",
				driver);
		WebElement Taxpayercode = driver.findElement(By.xpath(
				"//div/div/div/span[contains(.,'Company Code')]/../../div/span/slot/slot/lightning-formatted-text[contains(.,'"
						+ TaxpayerCode + "')]"));
		assertTrue(Taxpayercode.isDisplayed());
		WebElement Taxpayerstartdate = driver.findElement(By.xpath(
				"//div/div/div/span[contains(.,'Company Start Date')]/../../div/span/slot/slot/lightning-formatted-text[contains(.,'1/1/2017')]"));
		assertTrue(Taxpayerstartdate.isDisplayed());

	}

}
