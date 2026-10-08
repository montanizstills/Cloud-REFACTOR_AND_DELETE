package common.libraries.miscellaneous;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import au.com.bytecode.opencsv.CSVReader;
import common.libraries.customer.PortalFunctions;
import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.libraries.webdriver.DriverManager;
import common.pages.Addseller;
import common.pages.AdminUndoAdjustmentFile;
import common.pages.ConfigurePage;
import common.pages.ECWWizwardLogin;
import common.pages.ExceptionsPage;
import common.pages.FreeUserLinks;
import common.pages.LoginPage;
import common.pages.LookupTaxRates;
import common.pages.MyInterfacePage;
import common.pages.ProductsPage;
import common.pages.ProfessionalUserLinks;
import common.pages.RegistrationPage;
import common.pages.SubmitTaxExemption;
import common.pages.VertexSMBCustomers;
import common.pages.WizardSetting;
import common.utilities.CloudData;
import jxl.Sheet;

public class Features extends CommonFunctions {

	private WebDriver driver;

	public Features(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);
	public static Statement stmt = null;
	public static ResultSet rs = null;
	public static String SellerName;
	public static ArrayList<String> ONStates = new ArrayList<String>();
	public static ArrayList<String> OFFStates = new ArrayList<String>();
	public static ArrayList<String> ONExemptionType = new ArrayList<String>();
	public static ArrayList<String> OFFExemptionType = new ArrayList<String>();
	public static String Roles;
	public static String parentwindow;
	public static String SELLERCODE;
	public static String rejectemail;
	public static String ExptID = null;
	public static String ClientCode;
	private static Logger logger = Logger.getLogger("BusinessFunctions");

	public void EnterFormValues(String Numberofscreens, String FormCheckBoxes, String FormRadioButton,
			String FormTextBoxes, String FormUploadForms, String SelectFormAction, String Datepick, String Sign,
			String FEIN) throws Exception {
		int dta = 0, checkboxcount = 0, textboxcount = 0, radiocout = 0, Uploadcount = 0, selectboxcount = 0,
				datepickcount = 0;
		SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		int numberofscreens = Integer.parseInt(Numberofscreens);
		String[] Checkboxes = FormCheckBoxes.split(";");
		String[] RadioButtons = FormRadioButton.split(";");
		String[] Text = FormTextBoxes.split(";");
		String[] UploadForms = FormUploadForms.split(";");
		String datepick[] = Datepick.split(";");
		String[] Select = SelectFormAction.split(";");
		waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);
		for (int i = 0; i <= numberofscreens; i++) {
			try {
				waitforElement(180, SubmitTaxExpt.NextButon, driver);
				if (VerifyObject(driver, SubmitTaxExpt.RadioButton)) {
					String unifrom[] = RadioButtons[radiocout].split(",");
					driver.findElements(By.xpath("//input[@type='radio']"));
					int r = 0;
					for (int j1 = 0; j1 < unifrom.length; j1++) {
						try {
							r++;
							JavaScriptclick(driver, driver.findElement(
									By.xpath("(//label[contains(.,'" + unifrom[j1] + "')]/input)[" + (r) + "]")));

						} catch (Exception e) {
							JavaScriptclick(driver,
									driver.findElement(By.xpath("(//label[contains(.,'" + unifrom[j1] + "')]/input)")));
							r--;
						}
					}
					radiocout++;
				}
				if (VerifyObject(driver, SubmitTaxExpt.SelectValues)) {
					String s[] = Select[selectboxcount].split(",");
					scrollDown(driver);
					for (int j1 = 0; j1 < s.length; j1++) {
						try {
							if (s[j1].equalsIgnoreCase("Default") || s[j1].isEmpty()) {

							} else {
								scrollDown(driver);
								JavaScriptclick(driver, driver.findElement(By
										.xpath("(//div[@class='form-group form-group-sm']/select)[" + (j1 + 1) + "]")));
								JavaScriptscrolltoView(driver, driver.findElement(By
										.xpath("(//div[@class='form-group form-group-sm']/select)[" + (j1 + 1) + "]")));
								selectByValue(
										driver.findElement(By.xpath(
												"(//div[@class='form-group form-group-sm']/select)[" + (j1 + 1) + "]")),
										s[j1].trim());
								waitforElement(180, SubmitTaxExpt.NextButon, driver);
							}
						} catch (Exception e) {
							selectByValue(driver.findElement(By.xpath("//tr[" + (j1 + 1) + "]/td/select")),
									s[j1].trim());
							waitforElement(180, SubmitTaxExpt.NextButon, driver);
						}
					}
					selectboxcount++;
				}
				if (VerifyObject(driver, SubmitTaxExpt.CheckBoxes)) {
					if (!Checkboxes[checkboxcount].trim().isEmpty()) {
						String justis[] = Checkboxes[checkboxcount].split(",");
						for (int j = 0; j < justis.length; j++) {
							if (!driver.findElement(By.xpath("//label[contains(.,'" + justis[j] + "')]/input"))
									.isSelected()) {
								Thread.sleep(1000);
								JavaScriptclick(driver,
										driver.findElement(By.xpath("//label[contains(.,'" + justis[j] + "')]/input")));
							}
						}
					}
					checkboxcount++;
				}
				if (VerifyObject(driver, SubmitTaxExpt.UploadForms)) {
					try {
						String UploadForm = UploadForms[Uploadcount];
						switch (Browser.toUpperCase()) {
						case "FIREFOX":
							JavaScriptclick(driver, SubmitTaxExpt.UploadForms);
							break;
						case "CHROME":
						case "IE":
							click(SubmitTaxExpt.UploadForms);
							break;
						}
						Thread.sleep(15000);
						String image[] = UploadForm.split("\\.");
						transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, image[0], "." + image[1]);
						Thread.sleep(10000);
						waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);
						waitforElement(180, SubmitTaxExpt.NextButon, driver);
						Uploadcount++;
					}

					catch (Exception e) {
						if (VerifyObject(driver, SubmitTaxExpt.UploadSignature)) {
							String signature[] = Sign.split(":");
							switch (signature[0]) {
							case "Upload":
								JavaScriptclick(driver, SubmitTaxExpt.UploadSignature);
								Thread.sleep(7000);
								switch (Browser) {
								case "Firefox":
									JavaScriptclick(driver, SubmitTaxExpt.DropImage);
									break;
								case "Chrome":
									click(SubmitTaxExpt.DropImage);
									break;
								}
								String image[] = signature[1].split("\\.");
								Thread.sleep(7000);
								transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, image[0],
										"." + image[1]);
							}
						}
					}
				}

				if (VerifyObject(driver, SubmitTaxExpt.TextBox)) {
					if (!Text[textboxcount].trim().isEmpty()) {
						new Actions(driver);
						String text[] = Text[textboxcount].split(",");
						for (int j = 0; j < text.length; j++) {
							if (VerifyObject(driver,
									"(//div[@class='form-group form-group-sm']/descendant::input[@type='text'])["
											+ (j + 1) + "]")) {
								if (!text[dta].trim().isEmpty()) {
									JavaScriptclick(driver, driver.findElement(By.xpath(
											"(//div[@class='form-group form-group-sm']/descendant::input[@type='text'])["
													+ (j + 1) + "]")));
									clear(driver.findElement(By.xpath(
											"(//div[@class='form-group form-group-sm']/descendant::input[@type='text'])["
													+ (j + 1) + "]")));
									type(driver.findElement(By.xpath(
											"(//div[@class='form-group form-group-sm']/descendant::input[@type='text'])["
													+ (j + 1) + "]")),
											text[dta]);
									sendkeysTab(driver.findElement(By.xpath(
											"(//div[@class='form-group form-group-sm']/descendant::input[@type='text'])["
													+ (j + 1) + "]")));
									if (!FEIN.isEmpty() && dta >= 1) {
										JavaScriptclick(driver, SubmitTaxExpt.NextButon);
										if (VerifyObject(driver, SubmitTaxExpt.FEINVERBIAGE)) {
											clear(driver.findElement(By.xpath(
													"(//div[@class='form-group form-group-sm']/descendant::input[@type='text'])["
															+ (j + 1) + "]")));
											type(driver.findElement(By.xpath(
													"(//div[@class='form-group form-group-sm']/descendant::input[@type='text'])["
															+ (j + 1) + "]")),
													FEIN);
											sendkeysTab(driver.findElement(By.xpath(
													"(//div[@class='form-group form-group-sm']/descendant::input[@type='text'])["
															+ (j + 1) + "]")));
										}
									}
									dta++;
								}
							}
						}
						dta = 0;
					}
					textboxcount++;
				}
				if (VerifyObject(driver, SubmitTaxExpt.DATEPICKER)) {
					String date[] = datepick[datepickcount].split(",");
					for (int j = 0; j < date.length; j++) {
						if (VerifyObject(driver, "(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")) {
							JavaScriptclick(driver, driver.findElement(
									By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
							clear(driver.findElement(
									By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
							type(driver.findElement(
									By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")),
									date[dta]);
							sendkeysTab(driver.findElement(
									By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
							dta++;
						}
					}
					dta = 0;
					datepickcount++;
				}

				waitforElement(180, SubmitTaxExpt.NextButon, driver);
				JavaScriptclick(driver, SubmitTaxExpt.NextButon);
				waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);
				if (VerifyObject(driver, SubmitTaxExpt.INVALIDDATEFORMATE)
						|| VerifyObject(driver, SubmitTaxExpt.DATEGREATER)) {
					String date[] = datepick[datepickcount].split(",");
					for (int j = 0; j < date.length; j++) {
						if (VerifyObject(driver, "(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")) {
							JavaScriptclick(driver, driver.findElement(
									By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
							clear(driver.findElement(
									By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
							String curdate = xGetCurrentDate("MM/dd/yyyy");
							type(driver.findElement(
									By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")), curdate);
							sendkeysTab(driver.findElement(
									By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
							dta++;
						}
					}
					dta = 0;

				}

			} catch (Exception e) {
				throw new Exception("Unable to Post Certicates for  :" + Checkboxes[0] + e);
			}
		}
	}

	public int getpendingcertificatescount() throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		String PendingCertificatescount = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(7000);
		if (VerifyObject(driver, CNP.PENDINGCOUNTGRFEEN)) {
			PendingCertificatescount = getText(CNP.PENDINGCOUNTGRFEEN);
		} else {
			PendingCertificatescount = getText(CNP.PENDINGCOUNT);
		}

		Integer pendingcount = Integer.parseInt(PendingCertificatescount);
		return pendingcount;
	}

	public int getactivecertificatescount() throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		Thread.sleep(5000);
		String ActiveCertificatescount = "";
		if (VerifyObject(driver, CNP.ACTIVECERTIFICATECOUNTGREEN)) {
			ActiveCertificatescount = getText(CNP.ACTIVECERTIFICATECOUNTGREEN);
		} else {
			ActiveCertificatescount = getText(CNP.ACTIVECERTIFICATECOUNT);
		}
		Integer activecount = Integer.parseInt(ActiveCertificatescount);
		return activecount;
	}

	public static String SetEnvVariables() throws Exception {
		String env = "";
		Runtime.getRuntime().exec("cmd /c start setx BDB_PORT 5001");

		Thread.sleep(5000);

		Runtime.getRuntime().exec("cmd /c start setx BDB_SHOW_TOKEN_TIMEOUT False");

		Thread.sleep(5000);

		Runtime.getRuntime().exec("cmd /c start setx BDB_CLIENT_ID " + ECWClientID + "");

		Thread.sleep(5000);

		Runtime.getRuntime().exec("cmd /c start setx BDB_CLIENT_SECRET " + ECWClientSecret + "");

		Thread.sleep(5000);

		Runtime.getRuntime().exec("cmd /c start setx BDB_SELLER_CODE " + SELLERCODE + "");

		Thread.sleep(5000);

		Runtime.getRuntime().exec("cmd /c start setx BDB_CLIENT_CODE " + ClientCode + "");

		Thread.sleep(5000);

		if (Environment.contains("dev")) {
			env = "dev";
		} else if (Environment.contains("qa")) {
			env = "qa";
		} else if (Environment.contains("stage")) {
			env = "stage";
		}

		Runtime.getRuntime().exec(
				"cmd /c start setx BDB_AUTH_ENDPOINT https://" + env + "auth.vertexsmb.com/identity/connect/token");

		Thread.sleep(5000);

		Runtime.getRuntime().exec(
				"cmd /c start setx BDB_WIZARD_BASE_ADDRESS https://" + env.toLowerCase() + "ecmwizard.vertexsmb.com");

		Thread.sleep(5000);

		Runtime.getRuntime().exec("cmd /c start setx BDB_SERVICES_BASE_ADDRESS https://" + env.toLowerCase()
				+ "ecmservices.vertexsmb.com");

		Thread.sleep(5000);

		return "Pass";

	}

	public static String StartNPM() throws IOException, InterruptedException {

		File directory = new File(".//");

		String MainFolder = directory.getCanonicalPath() + "\\";

		Runtime.getRuntime().exec("wscript.exe " + MainFolder + "\\ECW.vbs ");

		Thread.sleep(50000);

		return "Pass";
	}

	public String BobsLogout() throws Exception {
		String status = "";
		SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
		if (VerifyObject(driver, SubmitTaxExpt.LOGOUT)) {
			click(SubmitTaxExpt.LOGOUT);
		}
		return status;
	}

	public void SAPConcur() throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(180, PUL.VERTEXSAPHEADER, driver);
		assertTrue(PUL.VERTEXSAPHEADER.isDisplayed());
		assertTrue(PUL.SAPCONCURHEADING.isDisplayed());
		assertTrue(PUL.CONCURCLICKHERE.isDisplayed());
		assertTrue(PUL.ALREADYENROLLED.isDisplayed());
		assertTrue(PUL.CONCURCONNECTORURL.isDisplayed());
		assertTrue(PUL.CONNECTORURLTEXT.isDisplayed());
		assertTrue(PUL.CONNECTORURLINPUT.isDisplayed());
		assertTrue(PUL.CONCURTRUSTEDID.isDisplayed());
		assertTrue(PUL.TRUSTEDIDTEXT.isDisplayed());
		assertTrue(PUL.TRUSTEDIDINPUT.isDisplayed());
		assertTrue(PUL.CONCURCONNECTBUTTON.isDisplayed());
		assertTrue(PUL.VERTEXSAPCONCURTEXT.isDisplayed());
	}

	public void EditProducts(String ProductName, String TaxabilityDriverCode, String ProductClass, String ExemptionType,
			String EditProductCode) throws Exception {
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			Thread.sleep(5000);
			click(PP.MAPPEDPRODUCTS);
			Thread.sleep(5000);
			waitforElement(180, PP.ADDPRODINPRODLIST, driver);
			click(driver.findElement(By.xpath("//div[@class='rt-td']/div[contains(.,'" + EditProductCode
					+ "')]/../../div/div/span/div[contains(@data-testid,'edit-button')]")));
			waitforElement(180, AS.TAXABILITYDRIVERCODE, driver);
			clear(AS.PRODUCTNAME);
			type(AS.PRODUCTNAME, ProductName);
			clear(AS.TAXABILITYDRIVERCODE);
			type(AS.TAXABILITYDRIVERCODE, TaxabilityDriverCode);
			if (ProductClass.equalsIgnoreCase("Yes")) {
				waitforElement(180, AS.PRODUCTCLASS, driver);
				click(AS.PRODUCTCLASS);
			}
			if (!ExemptionType.isEmpty()) {
				if (ExemptionType.equalsIgnoreCase("SELECT ALL")) {
					click(AS.SELECTALLEXEMPTIONTYPE);
				} else {
					click(AS.CLEARALLEXEMPTIONTYPE);
					Thread.sleep(5000);
					String[] exmptiontypes = ExemptionType.split(";");
					for (int i = 0; i < exmptiontypes.length; i++) {
						click(driver.findElement(By.xpath("//label[contains(.,'" + exmptiontypes[i] + "')]")));
					}
				}
			}
			click(AS.SAVEPRODUCT);
		} catch (Exception e) {
			throw new RuntimeException("EditProducts Method:" + e);
		}
	}

	public void CreateOAuth(String CredentailsUsage, String ClientID, String Description, String Withspaces)
			throws Exception {
		String Secret = "";
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		try {
			waitforElement(180, PUL.SETTINGSLINK, driver);
			click(PUL.SETTINGSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, AS.CLIENTCODE, driver);
			ClientCode = getText(AS.CLIENTCODE);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			click(AS.VIEWALLCREDENTIALS);
			waitforElement(180, AS.CREATENEWCREDENTIALS, driver);
			click(AS.CREATENEWCREDENTIALS);
			waitforElement(180, AS.CLIENTID, driver);
			selectByValue(AS.CREDENTIALSUSAGE, CredentailsUsage);
			if (CredentailsUsage.equalsIgnoreCase("O Series Connector")) {
				ECWOSeriesClientID = xLabelRND(ClientID.split(";"));
				type(AS.CLIENTID, ECWOSeriesClientID);
			} else {
				ECWClientID = xLabelRND(ClientID.split(";"));
				type(AS.CLIENTID, ECWClientID);
			}

			sendkeysTab(AS.CLIENTID);
			if (Withspaces.equalsIgnoreCase("YES")) {
				assertTrue((VerifyObject(driver, AS.CLIENTIDERROR)),
						"Fail, Client Id should not have spaces in between");
				click(AS.CANCELBUTTON);
			} else {
				type(AS.CREDENTIALDESCRIPTION, Description);
				click(AS.SAVE);
				waitforElement(180, AS.NEWSECRET, driver);
				getText(AS.NEWSECRET);
				Secret = getText(AS.NEWSECRET);
				Secret = Secret.replaceAll(
						"Success! A new secret was created. Please keep this secret for your records as it will no longer be accessible after closing this notification.",
						"").trim();
				Secret = Secret.replace("New Secret:", "").trim();

				if (CredentailsUsage.equalsIgnoreCase("O Series Connector")) {
					ECWOSeriesClientSecret = Secret;
				} else {
					ECWClientSecret = Secret;
				}
				System.out.println(ECWClientID);
				System.out.println(ECWClientSecret);
				logger.debug(ECWClientID);
				logger.debug(ECWClientSecret);
			}
		} catch (Exception e) {
			throw new RuntimeException("CreateOAuth Method:" + e);
		}
	}

	public void LaunchSite() throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		waitforElement(180, AS.ECWIZARDTAB, driver);
		click(AS.ECWIZARDTAB);
		parentwindow = driver.getWindowHandle();
		waitforElement(180, AS.LAUNCHSITE, driver);
		switch (DriverManager.Browser.toUpperCase()) {
		case "FIREFOX":
			click(AS.LAUNCHSITE);
			break;
		case "CHROME":
			JavaScriptclick(driver, AS.LAUNCHSITE);
			break;
		case "IE":
			click(AS.LAUNCHSITE);
			break;
		}
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		waitforElement(180, AS.SELLER, driver);
		driver.close();
		driver.switchTo().window(parentwindow);
	}

	public void DILaunchSite(String TestPath, String value) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		waitforElement(180, AS.DATAINTEGRITY, driver);
		parentwindow = driver.getWindowHandle();
		waitforElement(180, AS.DILAUNSITE, driver);
		click(AS.DILAUNSITE);
		Thread.sleep(15000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
				String DIUrl = driver.getCurrentUrl();
				System.out.println(DIUrl);
				String evn = Environment.toLowerCase();
				assertTrue(DIUrl.equalsIgnoreCase("https://" + evn + "di.vertexsmb.com/dm-integrity-ui/"));
				driver.close();
				driver.switchTo().window(parentwindow);
			}
		}
	}

	public void Customers(String Exemptionstates) throws Exception {
		driver.getWindowHandle();
		PageFactory.initElements(driver, SubmitTaxExemption.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		waitforElement(180, PUL.CONFIGURELINK, driver);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitforElement(60, CNP.PENDINGCERTIFICATES, driver);
		assertTrue((VerifyObject(driver, CNP.PENDINGCERTIFICATES)), "Fail,PENDINGCERTIFICATES text is not displaying");
		int pendcountbeforeapprove = getpendingcertificatescount();
		int activecountbeforeapprove = getactivecertificatescount();
		switch (DriverManager.Browser.toUpperCase()) {
		case "FIREFOX":
			click(CNP.PENDINGCERTIFICATES);
			break;
		case "CHROME":
			JavaScriptclick(driver, CNP.PENDINGCERTIFICATES);
			break;
		case "IE":
			JavaScriptclick(driver, CNP.PENDINGCERTIFICATES);
			break;
		}
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		ApproveCertificates(Exemptionstates);
		Thread.sleep(2000);
		waitforElement(180, PUL.CONFIGURELINK, driver);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitforElement(180, CNP.SAVECONFIGURATION, driver);
		int pendcountafterapprove = getpendingcertificatescount();
		assertTrue((pendcountafterapprove == (pendcountbeforeapprove - 1)), "Fail, Customername not matched");
		int activecountafterapprove = getactivecertificatescount();
		assertTrue((activecountafterapprove == (activecountbeforeapprove + 1)), "Fail, Customername not matched");

	}

	public void ApproveCertificates(String Exemptionstate) throws Exception {
		String states[] = Exemptionstate.split(",");
		driver.findElement(By.xpath(
				"//td[4]/span[contains(.,'" + states[0].toUpperCase() + "')]/../../td[@ng-show='data.isActive']/div/a"))
				.click();
		JurisdictionType = driver
				.findElement(By.xpath("//td[4]/span[contains(.,'" + states[0].toUpperCase() + "')]/../../td[5]"))
				.getText();
		JavaScriptclick(driver, driver.findElement(By.xpath("//span[contains(.,'" + states[0].toUpperCase()
				+ "')]/../../descendant::ul/li/a[contains(.,'Approve')]")));
		if (VerifyObject(driver, "//td[4]/span[contains(.,'" + states[0].toUpperCase()
				+ "')]/../../td[contains(.,'Pending With Error')]")) {
			RevalidateandApprove(Exemptionstate);
		}
	}

	public void RejectCertificates(String Exemptionstate, String Reasonforreject) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String states[] = Exemptionstate.split(",");
		driver.findElement(By.xpath(
				"//td[4]/span[contains(.,'" + states[0].toUpperCase() + "')]/../../td[@ng-show='data.isActive']/div/a"))
				.click();
		waitforElement(180,
				"//span[contains(.,'" + states[0].toUpperCase() + "')]/../../descendant::ul/li/a[contains(.,'Reject')]",
				driver);
		JavaScriptclick(driver, driver.findElement(By.xpath("//span[contains(.,'" + states[0].toUpperCase()
				+ "')]/../../descendant::ul/li/a[contains(.,'Reject')]")));
		waitforElement(180, CNP.REJECTREASONTEXTBOX, driver);
		type(CNP.REJECTREASONTEXTBOX, Reasonforreject);
		JavaScriptclick(driver, CNP.SENDREJECTION);
		rejectemail = xGetECWRejectmaillink();
		if (rejectemail.isEmpty()) {
			assertTrue(rejectemail.isEmpty(), "Fail, Approve status not displayed");
		}
	}

	public void ViewCretificatesDetails(String Exemptionstate, String Customername, String Customertype,
			String Customercodeclass, String Certificatescope, String ExemptionType, String Startdate, String enddate,
			String exemptionstatesverify) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String states[] = Exemptionstate.split(",");
		driver.findElement(By.xpath(
				"//td[4]/span[contains(.,'" + states[0].toUpperCase() + "')]/../../td[@ng-show='data.isActive']/div/a"))
				.click();
		waitforElement(180, "//span[contains(.,'" + states[0].toUpperCase()
				+ "')]/../../descendant::ul/li/a[contains(.,'View Details')]", driver);
		JavaScriptclick(driver, driver.findElement(By.xpath("//span[contains(.,'" + states[0].toUpperCase()
				+ "')]/../../descendant::ul/li/a[contains(.,'View Details')]")));
		waitforElement(180, CNP.CUSTOMERNAMEVALUE, driver);
		assertTrue(CNP.CUSTOMERNAMEVALUE.getText().trim().equalsIgnoreCase(Customername),
				"Fail, Customername not matched");
		assertTrue(CNP.CUSTOMERTYPE.getText().trim().equalsIgnoreCase(Customertype), "Fail, Customertype not matched");
		assertTrue(CNP.CUSTOMERCODECLASS.getText().trim().equalsIgnoreCase(Customercodeclass),
				"Fail, Customercodeclass not matched");
		assertTrue(CNP.CERTIFICATESCOPE.getText().trim().equalsIgnoreCase(Certificatescope),
				"Fail, Certificatescope not matched");
		assertTrue(CNP.CERTIFICATESTARTDATE.getText().trim().equalsIgnoreCase(Startdate),
				"Fail, Startdate not matched");
		assertTrue(CNP.CERTIFICATEENDDATE.getText().trim().equalsIgnoreCase(enddate), "Fail, enddate not matched");
		assertTrue(CNP.EXEMTIONSTATEVERIFY.getText().trim().equalsIgnoreCase(exemptionstatesverify),
				"Fail, Exemtionstateverify not matched");
		waitforElement(180, CNP.IMAGEFILE, driver);
		JavaScriptclick(driver, CNP.IMAGEFILE);
	}

	public void VerifyApprovedCertificatesincustomers(String Exemptionstate, String Customernames,
			String CertificateScope, String Reason, String StartDate, String EndDate) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		click(PUL.CONFIGURELINK);
		waitforElement(180, CNP.CUSTOMERS, driver);
		click(CNP.CUSTOMERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		driver.findElement(By.xpath("//td[contains(.,'" + Customernames + "')]/../td[6][contains(.,'View')]/span/a"))
				.click();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String states[] = Exemptionstate.split(",");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[4]/span[contains(.,'" + states[0].toUpperCase() + "')]/../../td[5]"))
				.getText();
		// assertTrue((JurisdictionType.equalsIgnoreCase(ApprovedJurisdictionType)),
		// "Fail, Jurisdiction Type in Pending and Active certificates mismatched");
		assertTrue(
				(VerifyObject(driver,
						"//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[contains(.,'Approved')]")),
				"Fail, Approve status not displayed");
		String CertScope = driver
				.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[7]")).getText();
		assertTrue((CertScope.equalsIgnoreCase(CertificateScope)), "Fail, Certificate Scope not matched with ECW");
		String reason = driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[8]"))
				.getText();
		assertTrue((reason.equalsIgnoreCase(Reason)), "Fail, Reason not matched with ECW");
		String startdate = driver
				.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[9]")).getText();
		assertTrue((startdate.equalsIgnoreCase(StartDate)), "Fail, startdate not matched with ECW");
		String enddate = driver
				.findElement(By.xpath("//tr[1]/td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[10]"))
				.getText();
		assertTrue((enddate.equalsIgnoreCase(EndDate)), "Fail, enddate not matched with ECW");
	}

	public void EditCertificates(String Exemptionstate, String Uploadimage) throws Exception {
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String AutoITpath = xAutoIT();
		String browser = Browser;
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitforElement(180, CNP.ActiveCertificates, driver);
		JavaScriptclick(driver, CNP.ActiveCertificates);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String states[] = Exemptionstate.split(",");
		JavaScriptclick(driver,
				driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/a")));
		waitforElement(180,
				"//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/ul/li/a[contains(.,'Edit')]",
				driver);
		JavaScriptclick(driver, driver.findElement(By.xpath(
				"//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/ul/li/a[contains(.,'Edit')]")));
		JavaScriptclick(driver, CNP.BROWSE);
		transactions.AutoItBrowser(driver, browser, AutoITpath, AdjustmentFilePath, Uploadimage, ".jpg");
		waitforElement(180, "CNP.SAVEJRISDICTION", driver);
		JavaScriptclick(driver, CNP.SAVEJRISDICTION);
	}

	public void RemoveCertificates(String Exemptionstate, String Certifcateenddate) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitforElement(180, CNP.ActiveCertificates, driver);
		JavaScriptclick(driver, CNP.ActiveCertificates);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String states[] = Exemptionstate.split(",");
		JavaScriptclick(driver,
				driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/a")));
		waitforElement(180,
				"//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/ul/li/a[contains(.,'Remove')]",
				driver);
		JavaScriptclick(driver, driver.findElement(By.xpath(
				"//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/ul/li/a[contains(.,'Remove')]")));
		waitforElement(180, CNP.REMOVEITEM, driver);
		clear(CNP.CERTIFCATEENDDATE);
		type(CNP.CERTIFCATEENDDATE, Certifcateenddate);
		JavaScriptclick(driver, CNP.REMOVEITEM);
		String str = driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[10]"))
				.getText().trim();
		assertTrue(str.equalsIgnoreCase(Certifcateenddate), "Fail, Certifcate End Date not displayed");
	}

	public void Viewcertificateimage(String Exemptionstate) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitforElement(180, CNP.ActiveCertificates, driver);
		JavaScriptclick(driver, CNP.ActiveCertificates);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String states[] = Exemptionstate.split(",");
		JavaScriptclick(driver,
				driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/a")));
		waitforElement(180, "//td[4][contains(.,'" + states[0].toUpperCase()
				+ "')]/../td[12]/div/ul/li/a[contains(.,'View Image')]", driver);
		JavaScriptclick(driver, driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase()
				+ "')]/../td[12]/div/ul/li/a[contains(.,'View Image')]")));
	}

	public void Deletecertificateimage(String Exemptionstate) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitforElement(180, CNP.ActiveCertificates, driver);
		JavaScriptclick(driver, CNP.ActiveCertificates);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		String states[] = Exemptionstate.split(",");
		JavaScriptclick(driver,
				driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/a")));
		waitforElement(180, "//td[4][contains(.,'" + states[0].toUpperCase()
				+ "')]/../td[12]/div/ul/li/a[contains(.,'Delete Image')]", driver);
		JavaScriptclick(driver, driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase()
				+ "')]/../td[12]/div/ul/li/a[contains(.,'Delete Image')]")));
	}

	public void RevalidateandApprove(String Exemptionstate) throws Exception {
		PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		String states[] = Exemptionstate.split(",");
		JavaScriptclick(driver,
				driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/a")));
		waitforElement(180, "//td[4][contains(.,'" + states[0].toUpperCase()
				+ "')]/../td[12]/div/ul/li/a[contains(.,'View Details')]", driver);
		JavaScriptclick(driver, driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase()
				+ "')]/../td[12]/div/ul/li/a[contains(.,'View Details')]")));
		waitforElement(180, CNP.REVALIDATE, driver);
		JavaScriptclick(driver, CNP.REVALIDATE);
		waitforElement(180, CNP.APPROVEBUTTON, driver);
		JavaScriptclick(driver, CNP.APPROVEBUTTON);
	}

	public void CopyConfigProducts(String Source_Company, String File_Name, String Destination_Company)
			throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		xDownloadFiles("Products");
		xAutoIT();
		xUploadAdjustmentFile("Products");
		PortalFunctions DownloadFiles = new PortalFunctions(driver);
		File tempfile = null;
		File tempfile2 = null;
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// End is writing to break the loop if there is a error while uploading the
		// invalid product sample
		if (VerifyObject(driver, "//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Source_Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		String sproductsCount = driver
				.findElement(By.xpath("//h2[contains(.,'" + Source_Company
						+ "')]/../../../div/div[contains(.,'Mappings')]/div/a[contains(.,'Mapped')]/../span"))
				.getText();
		WebElement MappedProductsGREEN = driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div[contains(.,'Mappings')]/div/a[contains(.,'Mapped')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductsGREEN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.FILTERSELECT, driver);
		click(PP.DownloadProducts);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			String dpath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Products";
			System.out.println("Path" + dpath);
			MoveLatestDownloadFileFromOnedirtoAnother(dpath);
			Thread.sleep(15000);
			tempfile = new File(DownloadFiles.LatestDownloadedFile("Products", true));
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			tempfile = new File(LatestDownloadedFile("Products", false));
		}
		File directory = new File(".//");
		String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Products";
		if (File_Name != "") {
			FileName = File_Name;
		} else {
			if (FileName != null && FileName.length() > 0 && FileName.charAt(FileName.length() - 1) == ']') {
				FileName = FileName.substring(0, FileName.length() - 1);
			}
		}
		tempfile2 = new File(DirName + "\\" + FileName);
		Thread.sleep(5000);
		@SuppressWarnings("unchecked")
		ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
		@SuppressWarnings("unchecked")
		ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
		f1.removeAll(f2);
		// assertTrue(f1.size() == 0);
		// click(PP.CopyProducts);
		JavaScriptclick(driver, PP.CopyProducts);
		waitforElement(60, PP.CopyDatalabel, driver);
		assertTrue(PP.CopyDatalabel.isDisplayed());
		assertTrue(PP.selectTaxpayerslabel.isDisplayed());
		assertTrue(PP.SourceCompany.isDisplayed());
		selectByValue(PP.DestinationCompany, Destination_Company);
		click(PP.Cancellnk);
		waitforElement(180, PP.FILTERSELECT, driver);
		click(PP.CopyProducts);
		waitforElement(60, PP.CopyDatalabel, driver);
		selectByValue(PP.DestinationCompany, Destination_Company);
		click(PP.CopyDataBtn);
		waitforText(180, PP.RequestProductCopyProcess, driver);
		if (VerifyObject(driver, PP.RequestProductCopyProcess)) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				Thread.sleep(1000);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, CNP.BEGINREMOVAL, driver);
				WebElement MappedProducts = driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
						+ "')]/../../../div/div[contains(.,'Mappings')]/div/a[contains(.,'Mapped')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProducts);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PP.FILTERSELECT, driver);
				if (!VerifyObject(driver, PP.RequestProductCopyProcess)) {
					report = true;
				}
			} while (report == false);
		}
		JavaScriptclick(driver, CNP.CONFIGURE);
		Thread.sleep(1000);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		waitforElement(180,
				"//h2[contains(.,'" + Destination_Company + "')]/../../div/a[contains(text(),'Save Configuration')]",
				driver);
		assertTrue(driver.findElement(By.xpath(
				"//h2[contains(.,'" + Destination_Company + "')]/../../div/a[contains(text(),'Save Configuration')]"))
				.isDisplayed());
		String dproductsCount = driver.findElement(By.xpath("//h2[contains(.,'" + Destination_Company
				+ "')]/../../../../div/div/div/div/a[contains(.,'Mapped')]/../span")).getText();
		assertTrue(sproductsCount.equalsIgnoreCase(dproductsCount));
		WebElement Productscopyed = driver.findElement(By.xpath("//h2[contains(.,'" + Destination_Company
				+ "')]/../../../div/div[contains(.,'Mappings')]/h3/span/a[contains(.,'+ Add')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Productscopyed);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ViewAllProducts, driver);
		click(PP.UploadMulProducts);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ManageFiles, driver);
		click(PP.ManageFiles);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ManageOriginalFiles, driver);
		assertTrue(PP.ManageOriginalFiles.isDisplayed());
		assertTrue(PP.ManageErrorFiles.isDisplayed());
		assertTrue(PP.ManageSuccessFiles.isDisplayed());
		int productscopiedcolumssize = PP.ProductsCopiedsuccess.size();
		assertTrue(productscopiedcolumssize == 5);
	}

	public void CopyConfigProductsSingleTaxpayer(String Source_Company, String File_Name) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		xAutoIT();
		xUploadAdjustmentFile("Products");
		PortalFunctions DownloadFiles = new PortalFunctions(driver);
		File tempfile = null;
		File tempfile2 = null;
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// End is writing to break the loop if there is a error while uploading the
		// invalid product sample
		if (VerifyObject(driver, "//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Source_Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		/*
		 * driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company +
		 * "')]/../../../div/div[contains(.,'Mappings')]/div/a[contains(.,'Mapped')]/../span"
		 * )).getText();
		 */
		WebElement MappedProductsGREEN = driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div[contains(.,'Mappings')]/div/a[contains(.,'Mapped')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductsGREEN);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.FILTERSELECT, driver);
		click(PP.DownloadProducts);
		Thread.sleep(10000);
		if (Browser.equalsIgnoreCase("Chrome")) {
			File directory = new File(".//");
			String dpath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Products";
			xDownloadFiles("Products");
			MoveLatestDownloadFileFromOnedirtoAnother(dpath);
			Thread.sleep(15000);
			tempfile = new File(DownloadFiles.LatestDownloadedFile("Products", true));
		}
		if (Browser.equalsIgnoreCase("Firefox")) {
			tempfile = new File(LatestDownloadedFile("Products", false));
		}
		File directory = new File(".//");
		String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Products";
		if (File_Name != "") {
			FileName = File_Name;
		} else {
			if (FileName != null && FileName.length() > 0 && FileName.charAt(FileName.length() - 1) == ']') {
				FileName = FileName.substring(0, FileName.length() - 1);
			}
		}
		tempfile2 = new File(DirName + "\\" + FileName);
		Thread.sleep(5000);
		@SuppressWarnings("unchecked")
		ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
		@SuppressWarnings("unchecked")
		ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
		f1.removeAll(f2);
		// assertTrue(f1.size() == 0);
		if (!VerifyObject(driver, PP.CopyProducts)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void CopyConfigNoProductsSingleTaxpayer(String Source_Company) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		xDownloadFiles("Products");
		xAutoIT();
		xUploadAdjustmentFile("Products");
		new PortalFunctions(driver);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// End is writing to break the loop if there is a error while uploading the
		// invalid product sample
		if (VerifyObject(driver, "//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
			boolean report = false;
			do {
				JavaScriptclick(driver, CNP.CONFIGURE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(20000);
				if (!VerifyObject(driver, "//h2[contains(.,'" + Source_Company
						+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
					report = true;
				}
			} while (report == false);
		}
		WebElement NoProducts = driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", NoProducts);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PP.ViewAllProducts, driver);
		click(PP.ViewAllProducts);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (!VerifyObject(driver, PP.DownloadProducts)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (!VerifyObject(driver, PP.CopyProducts)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void CopyConfigNoCustomersSingleTaxpayer(String Source_Company) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		xDownloadFiles("ExceptionFiles");
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// End is writing to break the loop if there is a error while uploading the
		// invalid product sample
		WebElement NoCustomers = driver.findElement(By.xpath("//h2[contains(.,'" + Source_Company
				+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", NoCustomers);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CE.CLOSEICON, driver);
		click(CE.CLOSEICON);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		if (!VerifyObject(driver, PP.DownloadProducts)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
		if (!VerifyObject(driver, PP.CopyProducts)) {
			assertTrue(true);
		} else {
			assertFalse(false);
		}
	}

	public void CopyConfigProducts(String TestPath, String value) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		xDownloadFiles("Products");
		xAutoIT();
		xUploadAdjustmentFile("Products");
		PortalFunctions DownloadFiles = new PortalFunctions(driver);
		File tempfile = null;
		File tempfile2 = null;
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		CloudData.dataload(TestPath, value);
		// End is writing to break the loop if there is a error while uploading the
		// invalid product sample
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			if (VerifyObject(driver, "//h2[contains(.,'" + CloudData.get_data("Source_Company")
					+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
				boolean report = false;
				do {
					JavaScriptclick(driver, CNP.CONFIGURE);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(20000);
					if (!VerifyObject(driver, "//h2[contains(.,'" + CloudData.get_data("Source_Company")
							+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
						report = true;
					}
				} while (report == false);
			}
			String sproductsCount = driver
					.findElement(By.xpath("//h2[contains(.,'" + CloudData.get_data("Source_Company")
							+ "')]/../../../div/div[contains(.,'Mappings')]/div/a[contains(.,'Mapped')]/../span"))
					.getText();
			WebElement MappedProductsGREEN = driver
					.findElement(By.xpath("//h2[contains(.,'" + CloudData.get_data("Source_Company")
							+ "')]/../../../div/div[contains(.,'Mappings')]/div/a[contains(.,'Mapped')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProductsGREEN);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.FILTERSELECT, driver);
			click(PP.DownloadProducts);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("Chrome")) {
				File directory = new File(".//");
				String dpath = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\" + "Products";
				System.out.println("Path" + dpath);
				MoveLatestDownloadFileFromOnedirtoAnother(dpath);
				Thread.sleep(15000);
				tempfile = new File(DownloadFiles.LatestDownloadedFile("Products", true));
			}
			if (Browser.equalsIgnoreCase("Firefox")) {
				tempfile = new File(LatestDownloadedFile("Products", false));
			}
			File directory = new File(".//");
			String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\" + "Products";
			if (CloudData.get_data("File_Name") != "") {
				FileName = CloudData.get_data("File_Name");
			} else {
				if (FileName != null && FileName.length() > 0 && FileName.charAt(FileName.length() - 1) == ']') {
					FileName = FileName.substring(0, FileName.length() - 1);
				}
			}
			tempfile2 = new File(DirName + "\\" + FileName);
			Thread.sleep(5000);
			@SuppressWarnings("unchecked")
			ArrayList<String> f1 = new ArrayList<String>(FileUtils.readLines(tempfile));
			@SuppressWarnings("unchecked")
			ArrayList<String> f2 = new ArrayList<String>(FileUtils.readLines(tempfile2));
			f1.removeAll(f2);
			// assertTrue(f1.size() == 0);
			// click(PP.CopyProducts);
			JavaScriptclick(driver, PP.CopyProducts);
			waitforElement(60, PP.CopyDatalabel, driver);
			assertTrue(PP.CopyDatalabel.isDisplayed());
			assertTrue(PP.selectTaxpayerslabel.isDisplayed());
			assertTrue(PP.SourceCompany.isDisplayed());
			selectByValue(PP.DestinationCompany, CloudData.get_data("Destination_Company"));
			click(PP.Cancellnk);
			waitforElement(180, PP.FILTERSELECT, driver);
			click(PP.CopyProducts);
			waitforElement(60, PP.CopyDatalabel, driver);
			selectByValue(PP.DestinationCompany, CloudData.get_data("Destination_Company"));
			click(PP.CopyDataBtn);
			waitforText(180, PP.RequestProductCopyProcess, driver);
			if (VerifyObject(driver, PP.RequestProductCopyProcess)) {
				boolean report = false;
				do {
					JavaScriptclick(driver, CNP.CONFIGURE);
					Thread.sleep(1000);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, CNP.BEGINREMOVAL, driver);
					WebElement MappedProducts = driver
							.findElement(By.xpath("//h2[contains(.,'" + CloudData.get_data("Source_Company")
									+ "')]/../../../div/div[contains(.,'Mappings')]/div/a[contains(.,'Mapped')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", MappedProducts);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, PP.FILTERSELECT, driver);
					if (!VerifyObject(driver, PP.RequestProductCopyProcess)) {
						report = true;
					}
				} while (report == false);
			}
			JavaScriptclick(driver, CNP.CONFIGURE);
			Thread.sleep(1000);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, CNP.BEGINREMOVAL, driver);
			waitforElement(180, "//h2[contains(.,'" + CloudData.get_data("Destination_Company")
					+ "')]/../../div/a[contains(text(),'Save Configuration')]", driver);
			assertTrue(driver.findElement(By.xpath("//h2[contains(.,'" + CloudData.get_data("Destination_Company")
					+ "')]/../../div/a[contains(text(),'Save Configuration')]")).isDisplayed());
			String dproductsCount = driver
					.findElement(By.xpath("//h2[contains(.,'" + CloudData.get_data("Destination_Company")
							+ "')]/../../../../div/div/div/div/a[contains(.,'Mapped')]/../span"))
					.getText();
			assertTrue(sproductsCount.equalsIgnoreCase(dproductsCount));
			WebElement Productscopyed = driver
					.findElement(By.xpath("//h2[contains(.,'" + CloudData.get_data("Destination_Company")
							+ "')]/../../../div/div[contains(.,'Mappings')]/h3/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Productscopyed);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.ViewAllProducts, driver);
			click(PP.UploadMulProducts);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.ManageFiles, driver);
			click(PP.ManageFiles);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.ManageOriginalFiles, driver);
			assertTrue(PP.ManageOriginalFiles.isDisplayed());
			assertTrue(PP.ManageErrorFiles.isDisplayed());
			assertTrue(PP.ManageSuccessFiles.isDisplayed());
			int productscopiedcolumssize = PP.ProductsCopiedsuccess.size();
			assertTrue(productscopiedcolumssize == 5);
		}
	}

	public void BulkUploadProducts(String TestPath, String value) throws Exception {
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		strProdName = "";
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		String DownloadFilepath = xDownloadFiles("Products");
		String browser = Browser;
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("Products");
		PortalFunctions DownloadFiles = new PortalFunctions(driver);
		String Filename;
		Robot robot = new Robot();
		int count;
		File tempfile = null;
		File tempfile2 = null;
		String[] nextLine;
		String path, Cell_Name;
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		CloudData.dataload(TestPath, value);
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			CloudData.setrowdata(i);
			// Verifying the Product state, and refreshing if it is in Yellow
			if (VerifyObject(driver, "//h2[contains(.,'" + CloudData.get_data("Company")
					+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
				boolean report = false;
				do {
					JavaScriptclick(driver, CNP.CONFIGURE);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(20000);
					if (!VerifyObject(driver, "//h2[contains(.,'" + CloudData.get_data("Company")
							+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
						report = true;
					}
				} while (report == false);
			}

			if (VerifyObject(driver, "//h2[contains(.,'" + CloudData.get_data("Company")
					+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
				WebElement AddProductsRed = driver.findElement(By.xpath("//h2[contains(.,'"
						+ CloudData.get_data("Company")
						+ "')]/../../../../div/div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsRed);
			} else if (VerifyObject(driver, "//h2[contains(.,'" + CloudData.get_data("Company")
					+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")) {
				WebElement AddProductsGreen = driver
						.findElement(By.xpath("//h2[contains(.,'" + CloudData.get_data("Company")
								+ "')]/../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", AddProductsGreen);
			}
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);

			// ************* Upload Product ***************
			if (CloudData.get_data("Upload_Product_Code") != "") {
				click(PP.UploadMulProducts);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PP.UPLOADFILE, driver);
				assertTrue(VerifyObject(driver, PP.ViewAllProducts));
				if (CloudData.get_data("Upload_Product_Code").equalsIgnoreCase("YES")) {
					if (CloudData.get_data("Download_Template").equalsIgnoreCase("YES")) {
						if (Browser.equalsIgnoreCase("IE")) {
							JavaScriptclick(driver, PP.DWNLD_FILE_FRMT_TEMP);
						} else {
							click(PP.DWNLD_FILE_FRMT_TEMP);
							// Verifying the downloaded template
							Thread.sleep(5000);
							if (Browser.equalsIgnoreCase("Chrome")) {
								File directory = new File(".//");
								path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
										+ "Products";
								System.out.println("Path" + path);
								MoveLatestDownloadFileFromOnedirtoAnother(path);
								Thread.sleep(10000);
								LatestDownloadedFile("Products", true);
							}
							if (Browser.equalsIgnoreCase("Firefox")) {
								LatestDownloadedFile("Products", false);
							}
							CSVReader reader = new CSVReader(new FileReader(Filepath));
							ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
							while ((nextLine = reader.readNext()) != null) {
								ArrayList<String> list = new ArrayList<String>();
								for (int t = 0; t < nextLine.length; t++) {
									list.add(nextLine[t]);
								}
								array.add(list);
							}
							Thread.sleep(5000);
							Cell_Name = array.get(0).get(0).trim();
							assertTrue(Cell_Name.equalsIgnoreCase("Results"));
							Cell_Name = array.get(0).get(1).trim();
							assertTrue(Cell_Name.equalsIgnoreCase("Code Or Class"));
							Cell_Name = array.get(0).get(2).trim();
							assertTrue(Cell_Name.equalsIgnoreCase("Name"));
							Cell_Name = array.get(0).get(3).trim();
							assertTrue(Cell_Name.equalsIgnoreCase("Tax Type"));
							Cell_Name = array.get(0).get(4).trim();
							assertTrue(Cell_Name.equalsIgnoreCase("Code Or Class Type"));
							Cell_Name = array.get(0).get(5).trim();
							assertTrue(Cell_Name.equalsIgnoreCase("Start Date"));
							Cell_Name = array.get(0).get(6).trim();
							assertTrue(Cell_Name.equalsIgnoreCase("End Date"));
							Cell_Name = array.get(0).get(7).trim();
							assertTrue(Cell_Name.equalsIgnoreCase("Vertex Tax Code"));
						}
						if (Browser.equalsIgnoreCase("IE")) {
							Thread.sleep(10000);
							robot.keyPress(KeyEvent.VK_TAB);
							robot.keyRelease(KeyEvent.VK_TAB);
							robot.keyPress(KeyEvent.VK_TAB);
							robot.keyRelease(KeyEvent.VK_TAB);
							robot.keyPress(KeyEvent.VK_DOWN);
							robot.keyRelease(KeyEvent.VK_DOWN);
							robot.keyPress(KeyEvent.VK_DOWN);
							robot.keyRelease(KeyEvent.VK_DOWN);
							robot.keyPress(KeyEvent.VK_ENTER);
							robot.keyRelease(KeyEvent.VK_ENTER);
							Thread.sleep(2000);
							robot.keyPress(KeyEvent.VK_HOME);
							robot.keyRelease(KeyEvent.VK_HOME);
							Thread.sleep(5000);
							StringSelection stringSelection = new StringSelection(DownloadFilepath + "\\");
							Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
							Thread.sleep(5000);
							robot.keyPress(KeyEvent.VK_CONTROL);
							robot.keyPress(KeyEvent.VK_V);
							robot.keyRelease(KeyEvent.VK_V);
							robot.keyRelease(KeyEvent.VK_CONTROL);
							robot.delay(5000);
							robot.keyPress(KeyEvent.VK_ENTER);
							robot.delay(1000);
							Thread.sleep(5000);
						}
						if (Browser.equalsIgnoreCase("Chrome")) {
							File directory = new File(".//");
							path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
									+ "Products";
							System.out.println("Path" + path);
							MoveLatestDownloadFileFromOnedirtoAnother(path);
							Thread.sleep(10000);
							DownloadFiles.LatestDownloadedFile("Products", false);

						}
						if (Browser.equalsIgnoreCase("Firefox")) {
							Thread.sleep(5000);
							LatestDownloadedFile("Products", false);
						}
					}

					Thread.sleep(1000);
					if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
						click(PP.BROWSE);
					} else {
						JavaScriptclick(driver, PP.BROWSE);
					}
					Thread.sleep(3000);
					Filename = CloudData.get_data("File_Name");
					transactions.AutoItBrowser(driver, browser, AutoITpath, AdjustmentFilePath, Filename, ".csv");
					Thread.sleep(4000);
					click(PP.UPLOADFILE);
					Thread.sleep(4000);
					waitforElement(180, PP.UPLOADADDPRODUCT, driver);
					waitforElement(180, "//tbody/tr[1]/td[text()='" + CloudData.get_data("File_Name")
							+ "']/../td/a[text()='Original File']", driver);

					String SuccessFile = "//table/tbody/tr/td[contains(.,'" + CloudData.get_data("File_Name")
							+ "')]/../td/a[not(@class='ng-hide') and contains(.,'Success File')]";
					String ErrorFile = "//table/tbody/tr/td[contains(.,'" + CloudData.get_data("File_Name")
							+ "')]/../td/a[contains(.,'Error File')]";

					if ((CloudData.get_data("Verify_Successfile").equalsIgnoreCase("YES"))) {

						FIRST: if (!VerifyObject(driver, SuccessFile)) {
							boolean report = false;
							count = 0;
							do {
								Thread.sleep(5000);
								if (VerifyObject(driver, ErrorFile)) {

									break FIRST;
								}
								count = count + 1;
								if (count == 20) {
									break;
								}
								if (VerifyObject(driver, SuccessFile)) {
									report = true;
								}
							} while (report == false);
						}

						driver.findElement(By.xpath("//td[contains(.,'" + CloudData.get_data("File_Name")
								+ "')]/../td/a[text()='Success File']")).click();
						Thread.sleep(15000);
						if (Browser.equalsIgnoreCase("Chrome")) {
							File directory = new File(".//");
							path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
									+ "Products";
							MoveLatestDownloadFileFromOnedirtoAnother(path);
							Thread.sleep(10000);
							tempfile = new File(LatestDownloadedFile("Products", true));

						}
						if (Browser.equalsIgnoreCase("Firefox")) {
							tempfile = new File(LatestDownloadedFile("Products", false));
						}

						if (CloudData.get_data("Validate") != "") {

							File directory = new File(".//");
							String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected"
									+ "\\" + "Products";
							String name1 = CloudData.get_data("Compare_Filename");
							tempfile2 = new File(DirName1 + "\\" + name1 + "_suc.csv");
							Thread.sleep(5000);
							Comparecsvfiles(tempfile, tempfile2);
						}
					}

					if (CloudData.get_data("Verify_Errorfile").equalsIgnoreCase("YES")) {
						FIRST: if (!VerifyObject(driver, ErrorFile)) {
							boolean report = false;
							count = 0;
							do {
								Thread.sleep(30000);
								if (VerifyObject(driver, SuccessFile)) {
									break FIRST;
								}
								count = count + 1;
								if (count == 8) {
									break;
								}
								if (VerifyObject(driver, ErrorFile)) {
									report = true;
								}
							} while (report == false);
						}

						WebElement Errfile = driver.findElement(By.xpath("//td[contains(.,'"
								+ CloudData.get_data("File_Name") + "')]/../td/a[text()='Error File']"));
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", Errfile);
						Thread.sleep(20000);

						if (Browser.equalsIgnoreCase("Chrome")) {
							File directory = new File(".//");
							path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
									+ "Products";
							MoveLatestDownloadFileFromOnedirtoAnother(path);
							Thread.sleep(5000);
							tempfile = new File(LatestDownloadedFile("Products", true));

						}
						if (Browser.equalsIgnoreCase("Firefox")) {
							tempfile = new File(LatestDownloadedFile("Products", false));
						}

						if (CloudData.get_data("Validate") != "") {

							File directory = new File(".//");
							String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected"
									+ "\\" + "Products";
							String name1 = CloudData.get_data("Compare_Filename");
							tempfile2 = new File(DirName1 + "\\" + name1 + "_err.csv");
							Thread.sleep(5000);
							Comparecsvfiles(tempfile, tempfile2);
						}

					}
				}
				if (VerifyObject(driver, PP.ADDPRODINPRODLIST)) {
					if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
						click(PP.ADDPRODINPRODLIST);
					} else {
						JavaScriptclick(driver, PP.ADDPRODINPRODLIST);
					}
					waitforElement(180, PP.UploadMulProducts, driver);
				}
				if (CloudData.get_data("Verify_Count") != "") {
					if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
						click(PP.ViewAllProducts);
					} else {
						JavaScriptclick(driver, PP.ViewAllProducts);
					}

					waitforElement(180, PP.ADDPRODMAPPING, driver);
					waitforText(180, PP.FILTERSELECT, driver);
					if (CloudData.get_data("ExpiredCount") != "") {
						waitforElement(180, PP.EXPIRED, driver);

						if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
							click(PP.EXPIRED);
						} else {
							JavaScriptclick(driver, PP.EXPIRED);
						}
						List<WebElement> tablerows = driver
								.findElements(By.xpath("//table[@id='tblProducts']/tbody/tr"));
						int Appcount = tablerows.size();
						String Excount = CloudData.get_data("ExpiredCount");
						int Ecount = Integer.parseInt(Excount);
						if (Ecount == Appcount) {
							assertTrue(Ecount == Appcount);
						}
					}
					if (CloudData.get_data("MappedCount") != "") {
						selectByValue(PP.FILTERSELECT, "Mapping Ready to Save");
						List<WebElement> tablerows = driver
								.findElements(By.xpath("//table[@id='tblProducts']/tbody/tr"));
						int Appcount = tablerows.size();
						String Excount = CloudData.get_data("MappedCount");
						int Ecount = Integer.parseInt(Excount);
						if (Ecount == Appcount) {
							assertTrue(Ecount == Appcount);
						}
					}
					if (CloudData.get_data("UnmappedCount") != "") {
						selectByValue(PP.FILTERSELECT, "Unmapped Products");
						List<WebElement> tablerows = driver
								.findElements(By.xpath("//table[@id='tblProducts']/tbody/tr"));
						int Appcount = tablerows.size();
						String Excount = CloudData.get_data("UnmappedCount");
						int Ecount = Integer.parseInt(Excount);
						if (Ecount == Appcount) {
							assertTrue(Ecount == Appcount);
						}
					}
					waitforElement(180, PP.ADDPRODMAPPING, driver);
					if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
						click(PP.ADDPRODMAPPING);
					} else {
						JavaScriptclick(driver, PP.ADDPRODMAPPING);
					}

					waitforElement(180, PP.UploadMulProducts, driver);
				}
			}
			if (VerifyObject(driver, CNP.SAVECONFIGURATION)) {
				if (!(CloudData.get_data("SaveConfiguration").equalsIgnoreCase("NO"))) {
					waitforElement(180, CNP.SAVECONFIGURATION, driver);
					if (Browser.equalsIgnoreCase("IE") || Browser.equalsIgnoreCase("Chrome")) {
						click(CNP.SAVECONFIGURATION);
					} else {
						JavaScriptclick(driver, CNP.SAVECONFIGURATION);
					}
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, CNP.COMPLETECONFIGPOPUP, driver);
					click(CNP.COMPLETECONFIGPOPUP);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, CNP.STATEINPROCESS, driver);
				}
			}

			if (VerifyObject(driver, "//h2[contains(.,'" + CloudData.get_data("Company")
					+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
				boolean report = false;
				do {
					JavaScriptclick(driver, CNP.CONFIGURE);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(20000);
					if (!VerifyObject(driver, "//h2[contains(.,'" + CloudData.get_data("Company")
							+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
						report = true;
					}
				} while (report == false);
			}
		}
	}

	public void CopyConfigNoProductsSingleTaxpayer(String TestPath, String value) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		xDownloadFiles("Products");
		xAutoIT();
		xUploadAdjustmentFile("Products");
		new PortalFunctions(driver);
		click(CNP.CONFIGURE);
		CloudData.dataload(TestPath, value);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// End is writing to break the loop if there is a error while uploading the
		// invalid product sample
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			CloudData.setrowdata(i);
			if (VerifyObject(driver, "//h2[contains(.,'" + CloudData.get_data("Source_Company")
					+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
				boolean report = false;
				do {
					JavaScriptclick(driver, CNP.CONFIGURE);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					Thread.sleep(20000);
					if (!VerifyObject(driver, "//h2[contains(.,'" + CloudData.get_data("Source_Company")
							+ "')]/../../../div/div/h3[@class='h5 heading-accent has-warning']")) {
						report = true;
					}
				} while (report == false);
			}
			WebElement NoProducts = driver
					.findElement(By.xpath("//h2[contains(.,'" + CloudData.get_data("Source_Company")
							+ "')]/../../../div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", NoProducts);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PP.ViewAllProducts, driver);
			click(PP.ViewAllProducts);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			if (!VerifyObject(driver, PP.DownloadProducts)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			if (!VerifyObject(driver, PP.CopyProducts)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		}

	}

	public void CopyConfigNoCustomersSingleTaxpayer(String TestPath, String value) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProductsPage PP = PageFactory.initElements(driver, ProductsPage.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		xDownloadFiles("ExceptionFiles");
		xAutoIT();
		xUploadAdjustmentFile("ExceptionFiles");
		new PortalFunctions(driver);
		CloudData.dataload(TestPath, value);
		click(CNP.CONFIGURE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, CNP.BEGINREMOVAL, driver);
		// End is writing to break the loop if there is a error while uploading the
		// invalid product sample
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			CloudData.setrowdata(i);
			WebElement NoCustomers = driver.findElement(By.xpath("//h2[contains(.,'"
					+ CloudData.get_data("Source_Company")
					+ "')]/../../../div/div/h3[contains(.,'Customer Exemptions')]/span/a[contains(.,'+ Add')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", NoCustomers);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			/*
			 * waitforElement(180, CE.VIEWALLEXCEPTIONS, driver);
			 * click(CE.VIEWALLEXCEPTIONS);
			 */
			waitforElement(180, CE.CLOSEICON, driver);
			click(CE.CLOSEICON);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			if (!VerifyObject(driver, PP.DownloadProducts)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
			if (!VerifyObject(driver, PP.CopyProducts)) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		}
	}

	public void BigCommerceIntegration(String TestPath, String value) throws Exception {

		MyInterfacePage MI = PageFactory.initElements(driver, MyInterfacePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		CloudData.dataload(TestPath, value);
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			CloudData.set_data_row(i);
			waitforElement(180, PUL.SettingsHover, driver);
			JavaScriptMousehover(driver, PUL.SettingsHover);
			JavaScriptclick(driver, PUL.ADAPTERSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, MI.BIGCOMMERCEBUTTON, driver);
			assertTrue(MI.BIGCOMMERCEHEADING.isDisplayed());
			assertTrue(MI.BIGCOMMERCETEXT.isDisplayed());
			if (!CloudData.get_data("Without_Taxpayer").equalsIgnoreCase("YES")) {
				click(MI.BIGCOMMERCEBUTTON);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, MI.BIGCOMMINSTACESTEXT, driver);
				assertTrue(MI.DATEADDED.isDisplayed());
				assertTrue(MI.VERTEXCLOUDCOMPANY.isDisplayed());
				assertTrue(MI.BIGCOMMSTORENAME.isDisplayed());
				assertTrue(MI.ACTION.isDisplayed());
				click(MI.RETURNTOCONNECTORS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, MI.BIGCOMMERCEBUTTON, driver);
				/*
				 * click(MI.BIGCOMMERCEBUTTON); waitUntilElementInvisibe(180,
				 * CNP.NPROGRESSBUSY_XPATH,driver);
				 */
				/*
				 * click(MI.ADDCONNECTION); waitUntilElementInvisibe(180,
				 * CNP.NPROGRESSBUSY_XPATH,driver); Verification="";
				 * Verification=(VerifyObject(driver,MI.BIGCOMMERCECONFIG))==true? Verification:
				 * Verification+
				 * "Fail,Big Commerce configuration heading not displayed in Big Commerce Integration setup;"
				 * ; status=status+ Verification; VertexSMB.RowNo =
				 * bussFunction.xResult(Verification,RSheet,"Big Commerce Configuration","",
				 * value,NoOfVerify++); Verification="";
				 * Verification=(VerifyObject(driver,MI.STOREINFORMATION))==true? Verification:
				 * Verification+
				 * "Fail,Store Information heading not displayed in Big Commerce Integration setup;"
				 * ; status=status+ Verification; VertexSMB.RowNo =
				 * bussFunction.xResult(Verification,RSheet,"Store Information","",value,
				 * NoOfVerify++); click(MI.RETURNTOINSTANCES); waitUntilElementInvisibe(180,
				 * CNP.NPROGRESSBUSY_XPATH,driver);
				 */
				/*
				 * click(MI.ADDCONNECTION); waitUntilElementInvisibe(180,
				 * CNP.NPROGRESSBUSY_XPATH,driver); click(MI.CANCEL);
				 * waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH,driver);
				 * click(MI.ADDCONNECTION); waitUntilElementInvisibe(180,
				 * CNP.NPROGRESSBUSY_XPATH,driver); selectByValue(MI.VERTEXCOMPANY,
				 * BigCommerceintegration.getCell(VertexCompany, Data_Row).getContents());
				 * waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH,driver); String
				 * storename= BigCommerceintegration.getCell(StoreName, Data_Row).getContents();
				 * type(MI.STORENAME,storename); String Client_Id =
				 * BigCommerceintegration.getCell(StoreID,Data_Row).getContents();
				 * type(MI.STOREID,Client_Id); Thread.sleep(2000);
				 * type(MI.STOREAPIURL,BigCommerceintegration.getCell(StoreAPIURL,
				 * Data_Row).getContents());
				 * type(MI.STORECLIENTID,BigCommerceintegration.getCell(Client_id,
				 * Data_Row).getContents()); type(MI.TOKEN,BigCommerceintegration.getCell(Token,
				 * Data_Row).getContents());
				 * type(MI.SECRET,BigCommerceintegration.getCell(Secret,
				 * Data_Row).getContents()); click(MI.SUBMIT); waitUntilElementInvisibe(180,
				 * CNP.NPROGRESSBUSY_XPATH,driver);
				 * if(BigCommerceintegration.getCell(VerifyError,
				 * Data_Row).getContents().equalsIgnoreCase("YES")){ Verification="";
				 * Verification=(VerifyObject(driver,MI.ERRORMESSAGE))==true? Verification:
				 * Verification+
				 * "Fail,Error message not displayed in Big Commerce Integration Setup page;";
				 * status=status+ Verification; VertexSMB.RowNo =
				 * bussFunction.xResult(Verification,RSheet,"Error","",value,NoOfVerify++);
				 * }else{ waitforElement(180, MI.SUCCESSMESSAGE, driver);}
				 * click(MI.RETURNTOINSTANCES); waitUntilElementInvisibe(180,
				 * CNP.NPROGRESSBUSY_XPATH,driver);
				 */

				/*
				 * if(BigCommerceintegration.getCell(Edit,
				 * Data_Row).getContents().equalsIgnoreCase("YES")){ WebElement edit =
				 * driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'"+
				 * storename+" / "+Client_Id+"')]/../td/div[2]/a[contains(.,'Edit')]"));
				 * JavaScriptclick(driver, edit); waitUntilElementInvisibe(180,
				 * CNP.NPROGRESSBUSY_XPATH,driver); click(MI.CANCEL);
				 * waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH,driver); }
				 * if(BigCommerceintegration.getCell(Delete,
				 * Data_Row).getContents().equalsIgnoreCase("YES")){ WebElement delete =
				 * driver.findElement(By.xpath("//table/tbody/tr/td[contains(.,'"+
				 * storename+" / "+Client_Id+"')]/../td/div[2]/a[contains(.,'Delete')]"));
				 * JavaScriptclick(driver, delete); waitforElement(180, MI.DeletePopup, driver);
				 * click(MI.DeletePopup); Thread.sleep(5000); Verification="";
				 * Verification=(!VerifyObject(driver,"//table/tbody/tr/td[contains(.,'"+
				 * storename+" / "+Client_Id+"')]"))==true? Verification: Verification+
				 * "Fail,Instance should delete successfully;"; status=status+ Verification;
				 * VertexSMB.RowNo =
				 * bussFunction.xResult(Verification,RSheet,"Deleted","",value,NoOfVerify++); }
				 */
			} else {
				assertTrue(MI.BIGCOMMERCEBUTTON.isDisplayed());
			}
		}
	}

	public void DIAdminScreens(String TestPath, String value) throws Exception {
		value.split(";");
		driver.getWindowHandle();
		PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		click(PUL.DATAINTEGRITY);
		Thread.sleep(2000);
		assertTrue("Data Integrity | Vertex Cloud".equalsIgnoreCase(driver.getTitle()));
		// DASHBOARD Page Verification
		assertTrue(RP.DATAINTEGRTYTEXT.isDisplayed());
		assertTrue(RP.DATAINTEGRTYWIDGETTEXT.isDisplayed());
		assertTrue(RP.LaunchSite.isDisplayed());
		DILaunchSite(TestPath, value);
		// Settings should not display
		assertTrue(PUL.ECWSettingslink.isDisplayed());
		// Rates and Jurisdictions should not display
		if (!VerifyObject(driver, PUL.RatesJurisdictions)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		// Visit Help Center should display
		assertTrue(PUL.GETHELPLINK.isDisplayed());
		// What's New section should display
		assertTrue(PUL.WHATSNEWLINKTAXCENTRAL.isDisplayed());
		assertTrue(LP.Copyrightext.isDisplayed());
	}

	public void ECWClientUserlinks(String TestPath, String value) throws Exception {
		value.split(";");
		driver.getWindowHandle();
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		click(PUL.ECWIZARD);
		Thread.sleep(3000);
		assertTrue("Exemption Certificate Wizard | Vertex Cloud".equalsIgnoreCase(driver.getTitle()));
		// DASHBOARD Page Verification
		assertTrue(RP.ECWheaderText.isDisplayed());
		assertTrue(RP.ECWWidgetText.isDisplayed());
		assertTrue(RP.LaunchSite.isDisplayed());
		if (VerifyObject(driver, PUL.ECWSettingslink)) {
			waitforElement(180, PUL.ECWSettingslink, driver);
			click(PUL.ECWSettingslink);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			assertTrue(PUL.ECWmycompany.isDisplayed());
			assertTrue(PUL.ECWmyComName.isDisplayed());
			assertTrue(PUL.ECWmyComaddress.isDisplayed());
			assertTrue(PUL.ECWmyComPhone.isDisplayed());
			assertTrue(PUL.ECWHeaderText.isDisplayed());
			assertTrue(PUL.ECWURL.isDisplayed());
			assertTrue(PUL.AUTHORIZATIONURL.isDisplayed());
			String EcwUrl = driver.findElement(By.xpath(
					"//div[@class='eqheight-well-col has-btn-block ng-scope']//div[2]//div[1]//strong[contains(.,'URL:')]/../../div[@class='span9 ng-binding']"))
					.getText();
			System.out.println(EcwUrl);
			String evn = Environment.toLowerCase();
			assertTrue(EcwUrl.equalsIgnoreCase("https://" + evn + "ecwportal.vertexsmb.com"));
			assertTrue(PUL.ECWClientCode.isDisplayed());
			// My OAuth Credentials section
			assertTrue(PUL.MYOAUTHCREDENTIALSHEADERTEXT.isDisplayed());
			assertTrue(PUL.MYOAUTHCREDENTIALSTEXT.isDisplayed());
			assertTrue(PUL.TOTALCREDENTIALS.isDisplayed());
			assertTrue(PUL.VIEWALLCREDENTIALS.isDisplayed());
			// My Users section
			assertTrue(PUL.MYUSERS.isDisplayed());
			assertTrue(PUL.ACTIVEUSER.isDisplayed());
			assertTrue(PUL.PENDINGUSERS.isDisplayed());
			assertTrue(PUL.DELETEDUSERS.isDisplayed());
			assertTrue(PUL.VIEWALLUSERSBUTTON.isDisplayed());
			assertTrue(PUL.ADDNEWUSERBUTTON.isDisplayed());
		}
		// contactsupport
		assertTrue(PUL.contactsupport.isDisplayed());
		// Visit Help Center should not display
		assertTrue(PUL.GETHELPLINK.isDisplayed());
		// What's New section should not display
		assertTrue(PUL.WHATSNEWLINKTC.isDisplayed());
		Thread.sleep(5000);
		click(PUL.ECWIZARD);
		Thread.sleep(2000);
		click(PUL.LAUNCHSITE);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(120, PUL.ECWSettingslink, driver);
		click(PUL.ECWLOGOUT);
	}

	public void Seller(String TestPath, String value) throws Exception {
		value.split(";");
		driver.getWindowHandle();
		xGetdtSheet(TestPath, value);
		PageFactory.initElements(driver, Addseller.class);
		CloudData.dataload(TestPath, value);
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			CloudData.setrowdata(i);
			switch (CloudData.get_data("SellerOptions")) {
			case "Add Seller":
				AddSeller(CloudData.get_data("Sellercode"), CloudData.get_data("Sellername"),
						CloudData.get_data("Companycode"), CloudData.get_data("PartitionName"),
						CloudData.get_data("StreetAddress"), CloudData.get_data("DivisionCode"),
						CloudData.get_data("DepartmentCode"), CloudData.get_data("State"),
						CloudData.get_data("PostalCode"), CloudData.get_data("Country"));
				break;
			case "Edit Seller":
				EditSeller(CloudData.get_data("Sellercode"), CloudData.get_data("Sellername"),
						CloudData.get_data("Companycode"), CloudData.get_data("PartitionName"),
						CloudData.get_data("StreetAddress"), CloudData.get_data("DivisionCode"),
						CloudData.get_data("DepartmentCode"), CloudData.get_data("State"),
						CloudData.get_data("PostalCode"), CloudData.get_data("Country"),
						CloudData.get_data("CreatedSellerName"));
				break;
			case "Delete Seller":
				DeleteSeller(CloudData.get_data("CreatedSellerName"));
				break;
			case "Verify Seller":
				VerifySeller(CloudData.get_data("Sellercode"), CloudData.get_data("Sellername"),
						CloudData.get_data("Companycode"), CloudData.get_data("PartitionName"),
						CloudData.get_data("DivisionCode"), CloudData.get_data("DepartmentCode"),
						CloudData.get_data("CreatedSellerName"));

				break;
			}
		}
	}

	public void AddSeller(String SellerCode, String SELLERNAME, String CompanyCode, String PartitionName,
			String StrettAddress, String DivisionCode, String DepartmentCode, String State, String PostalCode,
			String Country) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(60, AS.SELLER, driver);
			click(AS.SELLER);
			waitforElement(180, AS.ADDSELLER, driver);
			click(AS.ADDSELLER);
			waitforElement(180, AS.SELLERCODE, driver);
			type(AS.SELLERCODE, SellerCode);
			SELLERCODE = SellerCode;
			SellerName = xLabelRND(SELLERNAME.split(";"));
			type(AS.SELLERNAME, SellerName);
			type(AS.COMPANYCODE, CompanyCode);
			type(AS.PARTITIONNAME, PartitionName);
			type(AS.STREETADDRESS, StrettAddress);
			type(AS.DIVISIONCODE, DivisionCode);
			type(AS.DEPARTMENTCODE, DepartmentCode);
			if (!State.isEmpty()) {
				click(AS.STATE);
			}
			type(AS.POSTALCODE, PostalCode);
			if (!Country.isEmpty()) {
				click(AS.COUNTRY);
			}
			click(AS.SAVESELLER);
			Thread.sleep(1000);
			System.out.println(SELLERCODE);
			logger.debug(SELLERCODE);
		} catch (Exception e) {
			throw new RuntimeException("AddNewUser Method:" + e);
		}
	}

	public void EditSeller(String SellerCode, String SELLERNAME, String CompanyCode, String PartitionName,
			String StrettAddress, String DivisionCode, String DepartmentCode, String State, String PostalCode,
			String Country, String CreatedSeller) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			Thread.sleep(5000);
			click(AS.SELLER);
			waitforElement(180, AS.ADDSELLER, driver);
			if (SellerName == null && CreatedSeller.equalsIgnoreCase("Test")) {
				SellerName = CreatedSeller;
			}
			click(driver.findElement(By.xpath("//div[@class='rt-td']/div[contains(.,'" + SellerName
					+ "')]/../../div/div/span/div[contains(@data-testid,'edit-button')]")));
			waitforElement(180, AS.SELLERCODE, driver);
			/*
			 * clear(AS.SELLERCODE); type(AS.SELLERCODE,SellerCode);
			 */
			SellerName = xLabelRND(SellerName.split(";"));
			clear(AS.SELLERNAME);
			type(AS.SELLERNAME, SellerName);
			clear(AS.COMPANYCODE);
			type(AS.COMPANYCODE, CompanyCode);
			clear(AS.PARTITIONNAME);
			type(AS.PARTITIONNAME, PartitionName);
			clear(AS.STREETADDRESS);
			type(AS.STREETADDRESS, StrettAddress);
			clear(AS.DIVISIONCODE);
			type(AS.DIVISIONCODE, DivisionCode);
			clear(AS.DEPARTMENTCODE);
			type(AS.DEPARTMENTCODE, DepartmentCode);
			if (!State.isEmpty()) {
				click(AS.STATE);
			}
			type(AS.POSTALCODE, PostalCode);
			if (!Country.isEmpty()) {
				click(AS.COUNTRY);
			}
			click(AS.SAVESELLER);

		} catch (Exception e) {
			throw new RuntimeException("EditSeller Method:" + e);
		}
	}

	public void DeleteSeller(String CreatedSeller) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			Thread.sleep(5000);
			click(AS.SELLER);
			waitforElement(180, AS.ADDSELLER, driver);
			if (SellerName == null && CreatedSeller.equalsIgnoreCase("Test")) {
				SellerName = CreatedSeller;
			}
			click(driver.findElement(By.xpath("//div[@class='rt-td']/div[contains(.,'" + SellerName
					+ "')]/../../div/div/span/div[contains(@data-testid,'delete-button')]")));
			waitforElement(180, AS.CONFIRMDELETE, driver);
			click(AS.CONFIRMDELETE);
			assertTrue((VerifyObject(driver,
					"//div[@class='rt-td']/div[contains(.,'\"+SellerName+\"')]/../../div/div/span/div[contains(@data-testid,'delete-button')]")),
					"Fail,TaxabilityDriverCode should not be Displayed in Grid;");
		} catch (Exception e) {
			throw new RuntimeException("DeleteSeller Method:" + e);
		}
	}

	public void VerifySeller(String SellerCode, String SELLERNAME, String CompanyCode, String PartitionName,
			String DivisionCode, String DepartmentCode, String CreatedSellerName) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			click(AS.SELLER);
			waitforElement(180, AS.ADDSELLER, driver);
			assertTrue((VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + SellerCode + "')]")),
					"Fail, Seller Code not displyed in Grid;");
			if (SellerName == null && CreatedSellerName.equalsIgnoreCase("Test")) {
				SellerName = CreatedSellerName;
			}
			assertTrue((VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + SellerName + "')]")),
					"Fail, Seller Name not displyed in Grid;");
			assertTrue((VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + CompanyCode + "')]")),
					"Fail, Company Code not displyed in Grid;");
			assertTrue((VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + PartitionName + "')]")),
					"Fail, Partition Name not displyed in Grid;");
			assertTrue((VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + DivisionCode + "')]")),
					"Fail, DivisionCode not displyed in Grid;");
			assertTrue((VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + DepartmentCode + "')]")),
					"Fail, DepartmentCode not displyed in Grid;");

		} catch (Exception e) {
			throw new RuntimeException("VerifySeller Method:" + e);
		}
	}

	public void Products(String TestPath, String value) throws Exception {
		value.split(";");
		driver.getWindowHandle();
		xGetdtSheet(TestPath, value);
		PageFactory.initElements(driver, Addseller.class);
		try {
			CloudData.dataload(TestPath, value);
			for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
				CloudData.setrowdata(i);
				switch (CloudData.get_data("ProductOptions")) {
				case "Add Product":
					AddProducts(CloudData.get_data("Productname"), CloudData.get_data("Taxabilitydrivercode"),
							CloudData.get_data("Productclass"), CloudData.get_data("ExemptionType"));
					break;
				case "EditProduct":
					EditProducts(CloudData.get_data("Productname"), CloudData.get_data("Taxabilitydrivercode"),
							CloudData.get_data("Productclass"), CloudData.get_data("ExemptionType"),
							CloudData.get_data("EditProductCode"));
					break;
				case "VerifyProduct":
					VerifyProducts(CloudData.get_data("Productname"), CloudData.get_data("Taxabilitydrivercode"),
							CloudData.get_data("Productclass"), CloudData.get_data("ExemptionType"));
					break;
				case "DeleteProduct":
					DeleteProduct(CloudData.get_data("Productname"), CloudData.get_data("Taxabilitydrivercode"),
							CloudData.get_data("Productclass"), CloudData.get_data("ExemptionType"));
					break;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Products Method:" + e);
		}
	}

	public void ExemptionType(String TestPath, String value) throws Exception {
		int clearcount = 0;
		value.split(";");
		driver.getWindowHandle();
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			CloudData.dataload(TestPath, value);
			Thread.sleep(5000);
			click(AS.EXEMTIONTYPETAB);
			for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
				CloudData.setrowdata(i);
				waitforElement(180, AS.SELECTALLEXEMPTIONTYPE, driver);
				if (CloudData.get_data("VerifySelectALLByDefault").equalsIgnoreCase("Yes")) {
					List<WebElement> DefaultSelectON = driver.findElements(
							By.xpath("//div[@class='checkbox']/label[@class='checkbox vtxMultiSelect-on']"));
					for (int j = 1; j <= DefaultSelectON.size(); j++) {
						getText(driver.findElement(By.xpath(
								"//div[@class='checkbox']/label[@class='checkbox vtxMultiSelect-on'][" + j + "]")));
						assertTrue((VerifyObject(driver,
								"//div[@class='checkbox']/label[@class='checkbox vtxMultiSelect-on'][" + j + "]")),
								"Fail,Exemption Type Check is not Checked by default;");
					}
				}

				if (CloudData.get_data("ExemptionTypes").equalsIgnoreCase("Select All")) {
					click(AS.SELECTALLEXEMPTIONTYPE);
				} else {
					if (clearcount == 0) {
						click(AS.CLEARALLEXEMPTIONTYPE);
						clearcount++;
					}
					click(driver.findElement(
							By.xpath("//label[contains(.,'" + CloudData.get_data("ExemptionTypes") + "')]")));
				}
			}
			click(AS.SAVEBUTTON);
		} catch (Exception e) {
			throw new RuntimeException("ExemptionType Method:" + e);
		}
	}

	public void Jurisdictions(String TestPath, String value) throws Exception {
		int clearcount = 0;
		value.split(";");
		driver.getWindowHandle();
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		CloudData.dataload(TestPath, value);
		Thread.sleep(5000);
		waitforElement(180, AS.JURISDICTIONSTAB, driver);
		JavaScriptclick(driver, AS.JURISDICTIONSTAB);
		Thread.sleep(10000);
		String OOSTATES[] = {
				"British Columbia,Manitoba,New Brunswick,Newfoundland and Labrador,Nova Scotia,Ontario,Prince Edward Island,Quebec,Saskatchewan,Alabama,Alaska,Arizona,Arkansas,Colorado,Connecticut,District of Columbia,Florida,Georgia,Hawaii,Idaho,Illinois,Indiana,Iowa,Kansas,Kentucky,Louisiana,Maine,Maryland,Massachusetts,Michigan,Minnesota,Mississippi,Missouri,Nebraska,Nevada,New Jersey,New Mexico,New York,North Carolina,North Dakota,Ohio,Oklahoma,Pennsylvania,Puerto Rico,Rhode Island,South Carolina,South Dakota,Tennessee,Texas,Utah,Vermont,Virginia,Washington,West Virginia,Wisconsin,Wyoming" };
		waitforElement(180, AS.CANDANPLUS, driver);
		JavaScriptclick(driver, AS.CANDANPLUS);
		JavaScriptclick(driver, AS.USPLUS);
		waitforElement(180, AS.ALASKAPLUS, driver);
		JavaScriptclick(driver, AS.ALASKAPLUS);
		JavaScriptclick(driver, AS.COLARADOPLUS);
		JavaScriptclick(driver, AS.LOUISIANAPLUS);
		List<WebElement> USStates = driver
				.findElements(By.xpath("(//div[@class='checkbox'])[2]/label[@class='checkbox vtxMultiSelect-on']"));
		List<WebElement> CanadaStates = driver
				.findElements(By.xpath("(//div[@class='checkbox'])[1]/label[@class='checkbox vtxMultiSelect-on']"));
		for (int i = 1; i <= CanadaStates.size(); i++) {
			Features.ONStates.add(getText(driver.findElement(
					By.xpath("(//div[@class='checkbox'])[1]/label[@class='checkbox vtxMultiSelect-on'][" + i + "]"))));
		}
		for (int j = 1; j <= USStates.size(); j++) {
			Features.ONStates.add(getText(driver.findElement(
					By.xpath("(//div[@class='checkbox'])[2]/label[@class='checkbox vtxMultiSelect-on'][" + j + "]"))));
		}
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			if (CloudData.get_data("VerifyDefaultSelection").equalsIgnoreCase("Yes")) {
				for (int j = 0; j < OOSTATES.length; j++) {
					assertTrue((OOSTATES[j].equalsIgnoreCase(Features.ONStates.get(j + 1))),
							"Fail,By Default States are not selected");
				}
				Features.ONStates.clear();
			}
			if (CloudData.get_data("JURISDICTIONS").equalsIgnoreCase("Select All")) {
				click(AS.SELECTALLEXEMPTIONTYPE);
			} else {
				if (clearcount == 0) {
					click(AS.CLEARALLEXEMPTIONTYPE);
					clearcount++;
				}
				driver.findElement(By.xpath("//label[contains(.,'" + CloudData.get_data("JURISDICTIONS") + "')]"))
						.click();
			}
		}
		List<WebElement> USStates1 = driver
				.findElements(By.xpath("(//div[@class='checkbox'])[2]/label[@class='checkbox vtxMultiSelect-on']"));
		List<WebElement> CanadaStates1 = driver
				.findElements(By.xpath("(//div[@class='checkbox'])[1]/label[@class='checkbox vtxMultiSelect-on']"));
		for (int i = 1; i <= CanadaStates1.size(); i++) {
			Features.ONStates.add(getText(driver.findElement(
					By.xpath("(//div[@class='checkbox'])[1]/label[@class='checkbox vtxMultiSelect-on'][" + i + "]"))));
		}
		for (int j = 1; j <= USStates1.size() - 2; j++) {
			Features.ONStates.add(getText(driver.findElement(
					By.xpath("(//div[@class='checkbox'])[2]/label[@class='checkbox vtxMultiSelect-on'][" + j + "]"))));
		}
		List<WebElement> OFFState = driver.findElements(By.xpath("//div[@class='checkbox']/label[@class='checkbox ']"));
		for (int j = 1; j <= OFFState.size(); j++) {
			Features.OFFStates.add(getText(
					driver.findElement(By.xpath("//div[@class='checkbox']/label[@class='checkbox '][" + j + "]"))));
		}
		click(AS.SAVEBUTTON);
	}

	public void AddProducts(String ProductName, String TaxabilityDriverCode, String ProductClass, String ExemptionType)
			throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(60, AS.PRODUCT, driver);
			click(AS.PRODUCT);
			waitforElement(180, AS.ADDPRODUCT, driver);
			click(AS.ADDPRODUCT);
			waitforElement(180, AS.TAXABILITYDRIVERCODE, driver);
			type(AS.PRODUCTNAME, ProductName);
			type(AS.TAXABILITYDRIVERCODE, TaxabilityDriverCode);
			if (ProductClass.equalsIgnoreCase("Yes")) {
				waitforElement(180, AS.PRODUCTCLASS, driver);
				click(AS.PRODUCTCLASS);
			}
			if (!ExemptionType.isEmpty()) {
				if (ExemptionType.equalsIgnoreCase("SELECT ALL")) {
					click(AS.SELECTALLEXEMPTIONTYPE);
				} else {
					click(AS.CLEARALLEXEMPTIONTYPE);
					String[] exmptiontypes = ExemptionType.split(";");
					for (int i = 0; i < exmptiontypes.length; i++) {
						click(driver.findElement(By.xpath("//label[contains(.,'" + exmptiontypes[i] + "')]")));
					}
				}
			}
			click(AS.SAVEPRODUCT);
			System.out.println(ProductName);
		} catch (Exception e) {
			throw new RuntimeException("AddProducts Method:" + e);
		}
	}

	public void VerifyProducts(String ProductName, String TaxabilityDriverCode, String ProductClass,
			String ExemptionType) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			Thread.sleep(5000);
			click(AS.PRODUCT);
			Thread.sleep(5000);
			waitforElement(180, AS.ADDPRODUCT, driver);
			assertTrue((VerifyObject(driver, "//span[contains(.,'" + ProductName + "')]")),
					"Fail, Product Name  not Displayed in Grid;");
			assertTrue((VerifyObject(driver, "//span[contains(.,'" + TaxabilityDriverCode + "')]")),
					"Fail,TaxabilityDriverCode not Displayed in Grid;");
		} catch (Exception e) {
			throw new RuntimeException("VerifyProducts Method:" + e);
		}
	}

	public void DeleteProduct(String ProductName, String TaxabilityDriverCode, String ProductClass,
			String ExemptionType) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			Thread.sleep(5000);
			click(AS.PRODUCT);
			waitforElement(180, AS.ADDPRODUCT, driver);
			click(driver.findElement(By.xpath("//div[@class='rt-td']/div[contains(.,'" + TaxabilityDriverCode
					+ "')]/../../div/div/span/div[contains(@data-testid,'delete-button')]")));
			waitforElement(180, AS.CONFIRMDELETE, driver);
			click(AS.CONFIRMDELETE);
			Thread.sleep(5000);
			assertTrue((VerifyObject(driver, "//span[contains(.,'" + TaxabilityDriverCode + "')]")),
					"Fail,TaxabilityDriverCode should not be Displayed in Grid;");
		} catch (Exception e) {
			throw new RuntimeException("DeleteProduct Method:" + e);
		}
	}

	public void ECWSettings(String TestPath, String value) throws Exception {
		driver.getWindowHandle();
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		CloudData.dataload(TestPath, value);
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			CloudData.setrowdata(i);
			waitforElement(180, AS.SETTINGSTAB, driver);
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			switch (CloudData.get_data("SettingsSections")) {
			case "Add New User":
				click(AS.ADDNEWUSER);
				waitforElement(180, AS.NEWUSERHEADER, driver);
				AddNewUser(CloudData.get_data("FirstName"), CloudData.get_data("LastName"), CloudData.get_data("Email"),
						CloudData.get_data("Phone"), CloudData.get_data("Account_Management"),
						CloudData.get_data("Vertex_Cloud_Indirect_Tax"),
						CloudData.get_data("Exemption_Certificate_Manager"), CloudData.get_data("Global_Compliance"),
						CloudData.get_data("Data_Integrity"), CloudData.get_data("UserName"),
						CloudData.get_data("Password"));
				break;
			case "View All Users":
				ViewAllUsers(CloudData.get_data("FirstName"), CloudData.get_data("LastName"),
						CloudData.get_data("Email"), CloudData.get_data("Phone"),
						CloudData.get_data("Roles_Verfication"));
				break;
			case "Create Oauth Credentials":
				CreateOAuth(CloudData.get_data("CredentailsUsage"), CloudData.get_data("ClientID"),
						CloudData.get_data("Description"), CloudData.get_data("Verify_NoSpaces"));
				break;
			case "Verify Exemption Certificate Wizard":
				VerifyExemptionCertificateWizard(CloudData.get_data("Url"), CloudData.get_data("AuthorizationUrl"));
				break;
			case "Edit Oauth":
				EditOAuth(CloudData.get_data("ClientID"), CloudData.get_data("Description"));
				break;
			case "Mange Secrets":
				ManageOAuth(CloudData.get_data("ClientID"), CloudData.get_data("ManageSecretsOption"));
				break;
			case "Delete Oauth":
				DeleteOAuth(CloudData.get_data("ClientID"));
				break;
			case "Delete User":
				DeleteUser(CloudData.get_data("UserName"));
				break;
			case "Change Role":
				ChangeRole(CloudData.get_data("UserName"), CloudData.get_data("Account_Management"),
						CloudData.get_data("Vertex_Cloud_Indirect_Tax"),
						CloudData.get_data("Exemption_Certificate_Manager"));
				break;
			}
		}
	}

	public void AddNewUser(String FirstName, String LastName, String Email, String Phone, String Account_Management,
			String Vertex_Cloud_Indirect_Tax, String Exemption_Certificate_Manager, String Global_Compliance,
			String Data_Integrity, String UserName, String Password) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLUSERS, driver);
			click(AS.ADDNEWUSER);
			// VertexFirstName = FirstName;
			type(AS.FIRSTANAME, FirstName);
			type(AS.LASTNAME, LastName);
			type(AS.EMAIL, Email);
			type(AS.PHONE, Phone);
			sendkeysTab(AS.PHONE);
			// selectByValue(AS.ROLE, Role);.
			if (!Account_Management.isEmpty()) {
				selectByValue(AS.ACCOUNTMANAGEMENT, Account_Management);
				System.out.println("");
				Roles = Roles + Account_Management;
			}
			if (!Vertex_Cloud_Indirect_Tax.isEmpty()) {
				selectByValue(AS.VERTEXCLOUDINDIRECTTAX, Vertex_Cloud_Indirect_Tax);
				Roles = Roles + Vertex_Cloud_Indirect_Tax;
			}
			if (!Exemption_Certificate_Manager.isEmpty()) {
				selectByValue(AS.EXEMPTIONCERTIFICATEMANAGER, Exemption_Certificate_Manager);
				Roles = Roles + "," + "ECW Portal " + Exemption_Certificate_Manager;
			}
			if (!Global_Compliance.isEmpty()) {
				selectByValue(AS.GLOBALCOMPLIANCE, Global_Compliance);
				Roles = Roles + Global_Compliance;
			}
			if (!Data_Integrity.isEmpty()) {
				selectByValue(AS.DATAINTEGRITY, Data_Integrity);
				Roles = Roles + Data_Integrity;
			}
			click(AS.SENDINVITEBUTTON);
			waitforElement(180, AS.REGISTRATIONLINK, driver);
			click(AS.REGISTRATIONLINK);
			waitforElement(180, AS.USERNAME, driver);
			ECWRoleUserName = xLabelRND(UserName.split(";"));
			type(AS.USERNAME, ECWRoleUserName);
			type(AS.PASSWORD, Password);
			type(AS.CONFIRMPASSWORD, Password);
			// click(AS.IAGREETERMS);
			click(AS.COMPLETE);
			Thread.sleep(5000);
			click(AS.LOGIN);
			ECWFlag = true;
		} catch (Exception e) {
			throw new RuntimeException("AddNewUser Method:" + e);
		}
	}

	public void AddNewPendingUser(String FirstName, String LastName, String Email, String Phone,
			String Account_Management, String Vertex_Cloud_Indirect_Tax, String Exemption_Certificate_Manager,
			String Global_Compliance, String Data_Integrity) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLUSERS, driver);
			click(AS.ADDNEWUSER);
			// VertexFirstName = FirstName;
			type(AS.FIRSTANAME, FirstName);
			type(AS.LASTNAME, LastName);
			type(AS.EMAIL, Email);
			type(AS.PHONE, Phone);
			sendkeysTab(AS.PHONE);
			// selectByValue(AS.ROLE, Role);.
			if (!Account_Management.isEmpty()) {
				selectByValue(AS.ACCOUNTMANAGEMENT, Account_Management);
				System.out.println("");
				Roles = Roles + Account_Management;
			}
			if (!Vertex_Cloud_Indirect_Tax.isEmpty()) {
				selectByValue(AS.VERTEXCLOUDINDIRECTTAX, Vertex_Cloud_Indirect_Tax);
				Roles = Roles + Vertex_Cloud_Indirect_Tax;
			}
			if (!Exemption_Certificate_Manager.isEmpty()) {
				selectByValue(AS.EXEMPTIONCERTIFICATEMANAGER, Exemption_Certificate_Manager);
				Roles = Roles + "," + "ECW Portal " + Exemption_Certificate_Manager;
			}
			if (!Global_Compliance.isEmpty()) {
				selectByValue(AS.GLOBALCOMPLIANCE, Global_Compliance);
				Roles = Roles + Global_Compliance;
			}
			if (!Data_Integrity.isEmpty()) {
				selectByValue(AS.DATAINTEGRITY, Data_Integrity);
				Roles = Roles + Data_Integrity;
			}
			click(AS.SENDINVITEBUTTON);
			waitforElement(180, AS.REGISTRATIONLINK, driver);
			click(AS.DASHBOARD);
		} catch (Exception e) {
			throw new RuntimeException("AddNewUser Method:" + e);
		}
	}

	public void ViewAllUsers(String FirstName, String LastName, String Email, String Phone, String Role)
			throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(180, AS.VIEWALLUSERS, driver);
			click(AS.VIEWALLUSERS);
			waitforElement(180, AS.CREATENEWUSERBUTTON, driver);
			assertTrue((VerifyObject(driver, "//td[contains(.,'" + FirstName + "')]")),
					"Fail, FirstName not displayed in grid");
			assertTrue((VerifyObject(driver, "//td[contains(.,'" + LastName + "')]")),
					"Fail, LastName not displayed in grid");
			assertTrue((VerifyObject(driver, "//td[contains(.,'" + Email + "')]")),
					"Fail, Email not displayed in grid");
			assertTrue((VerifyObject(driver, "//td[contains(.,'" + Phone + "')]")),
					"Fail, Phone not displayed in grid");
			assertTrue((VerifyObject(driver, "//td[contains(.,'" + Role + "')]")), "Fail, Role not displayed in grid");
		} catch (Exception e) {
			throw new RuntimeException("ViewAllUsers Method:" + e);
		}
	}

	public void DeleteUser(String UserName) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLUSERS, driver);
			click(AS.VIEWALLUSERS);
			waitforElement(180, AS.CREATENEWUSERBUTTON, driver);
			if (UserName.equalsIgnoreCase("Test")) {
				UserName = ECWRoleUserName;
			}
			driver.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td/a[contains(.,'Delete')]")).click();
			waitforElement(180, AS.DELETEUSER, driver);
			click(AS.DELETEUSER);
			waitforElement(180, AS.DELETEDUSERCOUNT, driver);
			assertTrue((VerifyObject(driver, AS.DELETEDUSERCOUNT)), "Fail, Deleted User Count not displaying");
		} catch (Exception e) {
			throw new RuntimeException("DeleteUser method : " + e);
		}
	}

	public void DeletePendingUser(String email) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLUSERS, driver);
			click(AS.VIEWALLUSERS);
			waitforElement(180, AS.CREATENEWUSERBUTTON, driver);
			driver.findElement(By.xpath("//td[contains(.,'" + email + "')]/../td/a[contains(.,'Delete')]")).click();
			waitforElement(180, AS.DELETEUSER, driver);
			click(AS.DELETEUSER);
			waitforElement(180, AS.DELETEDUSERCOUNT, driver);
			assertTrue((VerifyObject(driver, AS.DELETEDUSERCOUNT)), "Fail, Deleted User Count not displaying");
		} catch (Exception e) {
			throw new RuntimeException("DeleteUser method : " + e);
		}
	}

	public void ChangeRole(String UserName, String Account_Management, String Vertex_Cloud_Indirect_Tax,
			String Exemption_Certificate_Manager) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLUSERS, driver);
			click(AS.VIEWALLUSERS);
			waitforElement(180, AS.CREATENEWUSERBUTTON, driver);
			if (UserName.equalsIgnoreCase("Test")) {
				UserName = ECWRoleUserName;
			}
			driver.findElement(By.xpath("//td[contains(.,'" + UserName + "')]/../td/div/a[contains(.,'Change Role')]"))
					.click();
			if (!Account_Management.isEmpty()) {
				selectByValue(AS.ACCOUNTMANAGEMENT, Account_Management);
			}
			if (!Vertex_Cloud_Indirect_Tax.isEmpty()) {
				selectByValue(AS.VERTEXCLOUDINDIRECTTAX, Vertex_Cloud_Indirect_Tax);
			}
			if (!Exemption_Certificate_Manager.isEmpty()) {
				selectByValue(AS.EXEMPTIONCERTIFICATEMANAGER, Exemption_Certificate_Manager);
			}
			click(AS.SAVE);
			waitforElement(180, AS.SUCCESS, driver);
		} catch (Exception e) {
			throw new RuntimeException("ChangeRole method :" + e);
		}
	}

	public void VerifyExemptionCertificateWizard(String url, String authurl) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			String URL = getText(AS.URL);
			String AUTHORIZATIONURL = getText(AS.AUTHORIZATIONURL);
			assertTrue(URL.equalsIgnoreCase(url),
					"Fail, URl not matched in EXEMPTION CERTIFICATE WIZARD section_Settings Page");
			assertTrue(AUTHORIZATIONURL.equalsIgnoreCase(authurl),
					"Fail, Authorizationurl not matched in EXEMPTION CERTIFICATE WIZARD section_Settings Page");
		} catch (Exception e) {
			throw new RuntimeException("VerifyExemptionCertificateWizard Method:" + e);
		}
	}

	public void EditOAuth(String ClientID, String Description) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			click(AS.VIEWALLCREDENTIALS);
			waitforElement(180, AS.CREATENEWCREDENTIALS, driver);
			if (ClientID.equalsIgnoreCase("Test") || ClientID.isEmpty()) {
				ClientID = ECWClientID;
			}
			waitforElement(180, "//td[contains(.,'" + ClientID + "')]/../td/div/a", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + ClientID + "')]/../td/div/a")).click();
			waitforElement(180, "//td[contains(.,'" + ClientID + "')]/../td/div/ul/li/a[contains(.,'Edit')]", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + ClientID + "')]/../td/div/ul/li/a[contains(.,'Edit')]"))
					.click();
			waitforElement(180, AS.CREDENTIALDESCRIPTION, driver);
			type(AS.CREDENTIALDESCRIPTION, Description);
			click(AS.SAVE);

		} catch (Exception e) {
			throw new RuntimeException("EditOAuth Method:" + e);
		}
	}

	public void DeleteOAuth(String ClientID) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			click(AS.VIEWALLCREDENTIALS);
			waitforElement(180, AS.CREATENEWCREDENTIALS, driver);
			if (ClientID.equalsIgnoreCase("Test") || ClientID.isEmpty()) {
				ClientID = ECWClientID;
			}
			waitforElement(180, "//td[contains(.,'" + ClientID + "')]/../td/div/a", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + ClientID + "')]/../td/div/a")).click();
			waitforElement(180, "//td[contains(.,'" + ClientID + "')]/../td/div/ul/li/a[contains(.,'Delete')]", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + ClientID + "')]/../td/div/ul/li/a[contains(.,'Delete')]"))
					.click();
			waitforElement(180, AS.DELETECREDENTIALSANDSECRETS, driver);
			click(AS.DELETECREDENTIALSANDSECRETS);
			Thread.sleep(5000);
			assertTrue((VerifyObject(driver, "//td[contains(.,'" + ClientID + "')]")),
					"Fail, OAuth Credentails are displaying in Grid after delete action");
		} catch (Exception e) {
			throw new RuntimeException("DeleteOAuth Method:" + e);
		}
	}

	public void ManageOAuth(String ClientID, String ManageSecretOptions) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			click(AS.VIEWALLCREDENTIALS);
			waitforElement(180, AS.CREATENEWCREDENTIALS, driver);
			waitforElement(180, "//td[contains(.,'" + ECWClientID + "')]/../td/div/a", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + ECWClientID + "')]/../td/div/a")).click();
			waitforElement(180,
					"//td[contains(.,'" + ECWClientID + "')]/../td/div/ul/li/a[contains(.,'Manage Secrets')]", driver);
			driver.findElement(
					By.xpath("//td[contains(.,'" + ECWClientID + "')]/../td/div/ul/li/a[contains(.,'Manage Secrets')]"))
					.click();

			if (ClientID.equalsIgnoreCase("Test") || ClientID.isEmpty()) {
				ClientID = ECWClientID;
			}
			switch (ManageSecretOptions) {
			case "Add New Secret":
				waitforElement(180, AS.ADDNEWSECRET, driver);
				click(AS.ADDNEWSECRET);
				waitforElement(60, AS.NEWSECRET, driver);
				ECWClientSecret = getText(AS.NEWSECRET);
				ECWClientSecret = ECWClientSecret.replaceAll(
						"Success! A new secret was created. Please keep this secret for your records as it will no longer be accessible after closing this notification.",
						"").trim();
				ECWClientSecret = ECWClientSecret.replace("New Secret:", "").trim();
				break;
			case "Delete Secret":
				waitforElement(180, AS.DELETESECRETICON, driver);
				click(AS.DELETESECRETICON);
				waitforElement(180, AS.DELETESECRETBUTTON, driver);
				click(AS.DELETESECRETBUTTON);
				waitforElement(180, AS.CLOSEBUTTON, driver);
				click(AS.CLOSEBUTTON);
				break;
			}
		} catch (Exception e) {
			throw new RuntimeException("ManageOAuth Method:" + e);
		}
	}

	public void WaitUntilAllCertificatesGetsSync(String TestPath, String value) throws Exception {
		PageFactory.initElements(driver, Addseller.class);
		try {
			Thread.sleep(300000);
			driver.navigate().refresh();

		} catch (Exception e) {
		}
	}

	public void GCLaunchSite(String TestPath, String value) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		waitforElement(180, AS.GLOBALCOMPLIANCE, driver);
		click(AS.GLOBALCOMPLIANCE);
		parentwindow = driver.getWindowHandle();
		waitforElement(180, AS.GCLAUNSITE, driver);
		click(AS.GCLAUNSITE);
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		driver.close();
		driver.switchTo().window(parentwindow);
	}

	public void ActivityLog(String TestPath, String value) throws Exception {
		SubmitTaxExemption AS = PageFactory.initElements(driver, SubmitTaxExemption.class);
		waitforElement(180, AS.ACTIVITYLOG, driver);
		click(AS.ACTIVITYLOG);
		waitforElement(180, AS.SEARCHLOG, driver);
		click(AS.SEARCHLOG);
		Thread.sleep(5000);
		assertTrue((VerifyObject(driver, "//span[contains(.,'" + ECWUserName + "')]")),
				"Fail, No Activity Logs displaying in Grid ");
	}

	public void ECWNotificationVerify(String TestPath, String value) throws Exception {
		String UserName;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		String[] Data = value.split(";");
		Sheet ECWNotifications = xGetdtSheet(TestPath, value);
		int SystemAdmin = 1, Email_title = 2, Message = 3;

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
			String Admin = ECWNotifications.getCell(SystemAdmin, Data_Row).getContents();
			String Title = ECWNotifications.getCell(Email_title, Data_Row).getContents();
			String message = ECWNotifications.getCell(Message, Data_Row).getContents();

			if (VertexProUserName != null) {
				UserName = VertexProUserName;
			} else {
				UserName = UserN;
			}
			xGetNotificationMailECW(Admin, Title, Environment, message, UserName);
		}
	}

	public void ECWWizardLogin(String TestPath, String value) throws Exception {
		String UserName;
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ECWWizwardLogin ECWizardLogin = PageFactory.initElements(driver, ECWWizwardLogin.class);
		String[] Data = value.split(";");
		Sheet Loginsheet = xGetdtSheet(TestPath, value);
		int UserNAME = 1, Password = 2;
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
			if (VerifyObject(driver, ECWizardLogin.ChangeUserButton)) {
				click(ECWizardLogin.ChangeUserButton);
			}
			if (VerifyObject(driver, ECWizardLogin.UserName)) {
				if (ECWUserName != null) {
					UserName = ECWUserName;
				} else {
					UserName = Loginsheet.getCell(UserNAME, Data_Row).getContents();
				}
				waitforElement(180, ECWizardLogin.UserName, driver);
				type(ECWizardLogin.UserName, UserName);
				waitforElement(180, ECWizardLogin.Password, driver);
				type(ECWizardLogin.Password, Loginsheet.getCell(Password, Data_Row).getContents());
				waitforElement(180, ECWizardLogin.Login, driver);
				click(ECWizardLogin.Login);
			}
		}
	}

	public void ECWWizardSubmitTaxExemption(String TestPath, String value) throws Exception {
		String Clientid = "", clientsecret = "";
		new ArrayList<String>();
		SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
		CloudData.dataload(TestPath, value);
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			try {
				CloudData.setrowdata(i);
				String AutoITpath = xAutoIT();
				CloudData.get_data("FormCheckBoxes").split(";");
				switch (CloudData.get_data("CertificateOptions")) {
				case "Create Certificate":
					if (url.contains("stage")) {
						driver.findElement(By.xpath("//button[@id='tax-exemption-button']")).click();
					} else {
						clear(SubmitTaxExpt.Seller);
						type(SubmitTaxExpt.Seller, CloudData.get_data("Seller"));
						clear(SubmitTaxExpt.BuyerCode);
						type(SubmitTaxExpt.BuyerCode, CloudData.get_data("BuyerCode"));
						logger.debug(CloudData.get_data("BuyerCode"));
						if (CloudData.get_data("ClientId").isEmpty() && CloudData.get_data("ClientSecret").isEmpty()) {
							Clientid = ECWClientID;
							clientsecret = ECWClientSecret;
						} else {
							Clientid = CloudData.get_data("ClientId");
							clientsecret = CloudData.get_data("ClientSecret");
						}
						clear(SubmitTaxExpt.CLIENTID);
						type(SubmitTaxExpt.CLIENTID, Clientid);
						clear(SubmitTaxExpt.CLIENTSECRET);
						type(SubmitTaxExpt.CLIENTSECRET, clientsecret);
						click(SubmitTaxExpt.UpdateWizardPar);
						Thread.sleep(10000);
						waitforElement(180, SubmitTaxExpt.SUBMITTAXEXEMPTION, driver);
						click(SubmitTaxExpt.SUBMITTAXEXEMPTION);
					}
					break;
				case "Edit Certificate":
					List<WebElement> edit = driver.findElements(By.xpath("//a[contains(.,'Edit')]"));
					waitforElement(60, SubmitTaxExpt.EDITCERTIFICATE, driver);
					driver.findElement(By.xpath("//td/div[" + (edit.size()) + "]/a[contains(.,'Edit')]")).click();
					break;
				case "Renew Certificate":
					List<WebElement> renew = driver.findElements(By.xpath("//a[contains(.,'Renew')]"));
					waitforElement(60, SubmitTaxExpt.RENEWCERTIFICATE, driver);
					driver.findElement(By.xpath("//td/div[" + (renew.size()) + "]/a[contains(.,'Renew')]")).click();
					break;
				case "Reject Certificate":
					Thread.sleep(15000);
					break;
				}
				waitandswitchToFrame(SubmitTaxExpt.iFrame, driver);
				EnterFormValues(CloudData.get_data("NumberofScreens"), CloudData.get_data("FormCheckBoxes"),
						CloudData.get_data("FormRadioButton"), CloudData.get_data("FormTextBoxes"),
						CloudData.get_data("FormUploadForms"), CloudData.get_data("SelectFormAction"),
						CloudData.get_data("DatePickers"), CloudData.get_data("Signature"), CloudData.get_data("FEIN"));
				String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
				clear(SubmitTaxExpt.CompanyName);
				type(SubmitTaxExpt.CompanyName, CloudData.get_data("CompanyName"));
				String CompanyNamee = getAttribute(SubmitTaxExpt.CompanyName, "value");
				if (CompanyNamee.length() > 60) {
					assertTrue(CompanyNamee.length() > 60, "Fail; Company Name Boundary Value");
				}
				clear(SubmitTaxExpt.StreetAddress);
				type(SubmitTaxExpt.StreetAddress, CloudData.get_data("StreetAddress"));
				String streetadd = getAttribute(SubmitTaxExpt.StreetAddress, "value");
				if (streetadd.length() > 60) {
					assertTrue(streetadd.length() > 60, "Fail; Company Name Street Address");
				}
				clear(SubmitTaxExpt.City);
				type(SubmitTaxExpt.City, CloudData.get_data("City"));
				String citty = getAttribute(SubmitTaxExpt.City, "value");
				if (citty.length() > 60) {
					assertTrue(citty.length() > 60, "Fail; City Boundary Value");
				}
				selectByValue(SubmitTaxExpt.SelectState, CloudData.get_data("State"));
				clear(SubmitTaxExpt.PostalCode);
				type(SubmitTaxExpt.PostalCode, CloudData.get_data("PostalCode"));
				String postalCode = getAttribute(SubmitTaxExpt.PostalCode, "value");
				if (postalCode.length() > 10) {
					assertTrue(postalCode.length() > 10, "Fail; Postal Code Boundary Value");
				}
				selectByValue(SubmitTaxExpt.Country, CloudData.get_data("Country"));
				if (!CloudData.get_data("PhoneNumber").isEmpty()) {
					type(SubmitTaxExpt.PhoneNumber, CloudData.get_data("Country"));
					String phnu = getAttribute(SubmitTaxExpt.PhoneNumber, "value");
					if (phnu.length() > 20) {
						assertTrue(phnu.length() > 20, "Fail; Phone Number Boundary Value");
					}
				}
				if (!CloudData.get_data("CompanyEmail").isEmpty()) {
					clear(SubmitTaxExpt.Email);
					type(SubmitTaxExpt.Email, CloudData.get_data("CompanyEmail"));
					String email = getAttribute(SubmitTaxExpt.Email, "value");
					if (email.length() > 100) {
						assertTrue(email.length() > 100, "Fail;  Email Boundary Value");
					}
				}
				waitforElement(180, SubmitTaxExpt.NextButon, driver);
				JavaScriptclick(driver, SubmitTaxExpt.NextButon);
				// Additional Upload
				String[] multi = CloudData.get_data("MultiJurisdictions").split(",");
				if (VerifyObject(driver, SubmitTaxExpt.ADDITIONALDOCUMENTTITLEMULTI)) {
					driver.findElements(By.xpath("//select"));
					for (int j = 0; j < multi.length; j++) {
						selectByValue(driver.findElement(By.xpath("(//select)[" + (j + 1) + "]")), multi[j]);
					}
					waitforElement(180, SubmitTaxExpt.NextButon, driver);
					JavaScriptclick(driver, SubmitTaxExpt.NextButon);
				}

				if (VerifyObject(driver, SubmitTaxExpt.ADDITIONALDOCUMENTTITLE)) {
					String[] addupload = CloudData.get_data("AdditionalUpload").split(":");
					String upload = addupload[1];
					switch (addupload[0]) {
					case "SKIP":
						JavaScriptclick(driver, SubmitTaxExpt.ADDITIONALDOCUMENTS);
						Thread.sleep(7000);
						System.out.println(upload);
						String additional[] = upload.split("\\.");
						transactions.AutoItBrowser(driver, Browser, AutoITpath, AdjustmentFilePath, additional[0],
								"." + additional[1]);
						Thread.sleep(10000);
						JavaScriptclick(driver, SubmitTaxExpt.SKIP);
						waitforElement(180, SubmitTaxExpt.SKIPINDIALOUGE, driver);
						JavaScriptclick(driver, SubmitTaxExpt.SKIPINDIALOUGE);
						waitforElement(180, SubmitTaxExpt.NextButon, driver);
						JavaScriptclick(driver, SubmitTaxExpt.NextButon);
						break;
					case "4":
						int uploadcount = Integer.parseInt(addupload[0]);
						for (int j = 0; j < multi.length; j++) {
							if (multi[j].equalsIgnoreCase("Upload") || multi[j].isEmpty()) {
								for (int j1 = 0; j1 < uploadcount - 1; j1++) {
									JavaScriptclick(driver, SubmitTaxExpt.ADDITIONALDOCUMENTS);
									Thread.sleep(7000);
									System.out.println(upload);
									String additional1[] = upload.split("\\.");
									transactions.AutoItBrowser(driver, Browser, AutoITpath, AdjustmentFilePath,
											additional1[0], "." + additional1[1]);
									Thread.sleep(10000);
								}
								waitforElement(180, SubmitTaxExpt.NextButon, driver);
								JavaScriptclick(driver, SubmitTaxExpt.NextButon);
							}
						}
						waitforElement(180, SubmitTaxExpt.MAXIMUMLIMIT, driver);
						Thread.sleep(1000);
						if (VerifyObject(driver, SubmitTaxExpt.MAXIMUMLIMIT)) {
							assertTrue(SubmitTaxExpt.MAXIMUMLIMIT.isDisplayed(),
									"Fail,Maximum Limit failed for Additional Upload;");
						}
						break;
					case "1":
					case "2":
					case "3":
						int uploadcount1 = Integer.parseInt(addupload[0]);
						for (int j = 0; j < multi.length; j++) {
							if (multi[j].equalsIgnoreCase("Upload") || multi[j].isEmpty()) {
								for (int j1 = 0; j1 < uploadcount1; j1++) {
									JavaScriptclick(driver, SubmitTaxExpt.ADDITIONALDOCUMENTS);
									Thread.sleep(7000);
									System.out.println(upload);
									String additional1[] = upload.split("\\.");
									transactions.AutoItBrowser(driver, Browser, AutoITpath, AdjustmentFilePath,
											additional1[0], "." + additional1[1]);
									Thread.sleep(10000);
								}
								waitforElement(180, SubmitTaxExpt.NextButon, driver);
								JavaScriptclick(driver, SubmitTaxExpt.NextButon);
							}
						}
						break;
					}
				}
				waitforElement(180, SubmitTaxExpt.Name, driver);
				type(SubmitTaxExpt.Name, CloudData.get_data("Name"));
				type(SubmitTaxExpt.Title, CloudData.get_data("Title"));
				CloudData.get_data("Date").split("/");
				if (VerifyObject(driver, SubmitTaxExpt.DATEPICKER) && !CloudData.get_data("Date").isEmpty()) {
					JavaScriptclick(driver, SubmitTaxExpt.DATEPICKER);
					clear(SubmitTaxExpt.DATEPICKER);
					type(SubmitTaxExpt.DATEPICKER, CloudData.get_data("Date"));
					sendkeysTab(SubmitTaxExpt.DATEPICKER);
				}
				String signature[] = CloudData.get_data("Signature").split(":");
				switch (signature[0]) {
				case "Upload":
					JavaScriptclick(driver, SubmitTaxExpt.UploadSignature);
					Thread.sleep(7000);
					switch (Browser.toUpperCase()) {
					case "FIREFOX":
						JavaScriptclick(driver, SubmitTaxExpt.DropImage);
						break;
					case "CHROME":
					case "IE":
						click(SubmitTaxExpt.DropImage);
						break;
					}
					Thread.sleep(7000);
					System.out.println(signature[1]);
					String image[] = signature[1].split("\\.");
					transactions.AutoItBrowser(driver, Browser, AutoITpath, AdjustmentFilePath, image[0],
							"." + image[1]);
					Thread.sleep(10000);
					break;
				}
				waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);
				if (!CloudData.get_data("SelectFormAction").equalsIgnoreCase("Upload")) {
					JavaScriptclick(driver, SubmitTaxExpt.PreviewForm);
					switch (Browser) {
					case "Chrome":
						Set<String> window = driver.getWindowHandles();
						driver.switchTo().window(window.iterator().next());
						Thread.sleep(5000);
						driver.close();
						driver.switchTo().window(parentwindow);
						break;
					}
				}
				JavaScriptclick(driver, SubmitTaxExpt.Submit);
				if (VerifyObject(driver, SubmitTaxExpt.INVALIDDATEFORMATE)
						|| VerifyObject(driver, SubmitTaxExpt.DATEGREATER)) {
					JavaScriptclick(driver, SubmitTaxExpt.DATEPICKER);
					clear(SubmitTaxExpt.DATEPICKER);
					String curdate = xGetCurrentDate("MM/dd/yyyy");
					type(SubmitTaxExpt.DATEPICKER, curdate);
					sendkeysTab(SubmitTaxExpt.DATEPICKER);
					JavaScriptclick(driver, SubmitTaxExpt.Submit);
				}
				waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);
				/*
				 * ExptID=driver.findElement(By.
				 * xpath("//table[@class='table table-bordered']/tbody[1]/tr[1]/td[3]")).getText
				 * (); System.out.println(ExptID); Thread.sleep(2000); List<WebElement> formlist
				 * = driver.findElements(By.
				 * xpath("//table[@class='table table-bordered']/tbody[1]/tr")); List<String>
				 * texts =
				 * formlist.stream().map(WebElement::getText).collect(Collectors.toList()); for
				 * (int j =1; j <=texts.size(); j++) { Thread.sleep(2000);
				 * JavaScriptclick(driver, driver.findElement(By.
				 * xpath("//table[@class='table table-bordered']/tbody[1]/tr["+j+"]/td[1]/a")));
				 * }
				 */
				/*
				 * List<WebElement> formlist = driver.findElements(By.xpath("//li/a")); for (int
				 * j = 1; j < formlist.size(); j++) { if (VerifyObject(driver,
				 * driver.findElement(By.xpath("(//li/a)[" + j + "]")))) JavaScriptclick(driver,
				 * driver.findElement(By.xpath("(//li/a)[" + j + "]"))); }
				 */
				/*
				 * if (VerifyObject(driver, driver.findElement(By.xpath("(//li/button)[1]")))) {
				 * List<WebElement>addupload=driver.findElements(By.xpath("//li/button")); for
				 * (int j = 1; j < formlist.size(); j++) { JavaScriptclick(driver,
				 * driver.findElement(By.xpath("(//li/button)["+j+"]")));
				 * 
				 * Thread.sleep(3000); Set<String> window=driver.getWindowHandles();
				 * driver.switchTo().window(window.iterator().next()); Thread.sleep(5000);
				 * driver.close(); driver.switchTo().window(parentwindow);
				 * 
				 * } }
				 */
				waitforElement(180, SubmitTaxExpt.DoneButton, driver);
				if (VerifyObject(driver, SubmitTaxExpt.DoneButton)) {
					assertTrue(SubmitTaxExpt.DoneButton.isDisplayed(), "Done Button is Displayed");
				}
				JavaScriptclick(driver, SubmitTaxExpt.DoneButton);
				// waitforElement(180, SubmitTaxExpt.EditExemptCertf, driver);
				driver.switchTo().defaultContent();
			} catch (Exception e) {
				if (VerifyObject(driver, SubmitTaxExpt.LASTDEPLOYEMENTERROR)) {
					System.out.println("Certificate NO:- " + CloudData.Data_Row + ", "
							+ CloudData.get_data("CertificateOptions") + " for locations:-"
							+ CloudData.get_data("FormCheckBoxes") + " is not Posted DUE TO DEPLOYMENT.");
					logger.debug("Certificate NO:- " + CloudData.Data_Row + ", "
							+ CloudData.get_data("CertificateOptions") + " for locations:-"
							+ CloudData.get_data("FormCheckBoxes") + " is not Posted DUE TO DEPLOYMENT.");
					Reporter.log("Certificate NO:- " + CloudData.Data_Row + ", "
							+ CloudData.get_data("CertificateOptions") + " for locations:-"
							+ CloudData.get_data("FormCheckBoxes") + " is not Posted DUE TO DEPLOYMENT.");
				} else {
					System.out.println(
							"Certificate NO:- " + CloudData.Data_Row + ", " + CloudData.get_data("CertificateOptions")
									+ " for locations:-" + CloudData.get_data("FormCheckBoxes")
									+ " is not posted due to either new rule or form number.");
					logger.debug(
							"Certificate NO:- " + CloudData.Data_Row + ", " + CloudData.get_data("CertificateOptions")
									+ " for locations:-" + CloudData.get_data("FormCheckBoxes")
									+ " is not posted due to either new rule or form number.");
					Reporter.log(
							"Certificate NO:- " + CloudData.Data_Row + ", " + CloudData.get_data("CertificateOptions")
									+ " for locations:-" + CloudData.get_data("FormCheckBoxes")
									+ " is not posted due to either new rule or form number.");
				}
				JavaScriptclick(driver, SubmitTaxExpt.CLOSEBUTTON);
				waitforElement(300, SubmitTaxExpt.CONFIRMBUTTON, driver);
				JavaScriptclick(driver, SubmitTaxExpt.CONFIRMBUTTON);
				driver.switchTo().defaultContent();
			}
		}
	}

	public void ECWWizardSubmitTaxExemptionNew(String TestPath, String value) throws Exception {
		String EditLink = "", RenewLink = "";
		new ArrayList<String>();
		SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
		CloudData.dataload(TestPath, value);
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			try {
				CloudData.setrowdata(i);
				String AutoITpath = xAutoIT();
				// clear(SubmitTaxExpt.Client);
				// type(SubmitTaxExpt.Client, WizardExemptCertificates.getCell(Client,
				// Data_Row).getContents());
				Thread.sleep(10000);

				CloudData.get_data("FormCheckBoxes").split(";");
				switch (CloudData.get_data("CertificateOptions")) {
				case "Create Certificate":
					waitforElement(180, SubmitTaxExpt.SUBMITTAXEXEMPTIONNEW, driver);
					click(SubmitTaxExpt.SUBMITTAXEXEMPTIONNEW);
					break;
				case "Edit Certificate":
					List<WebElement> edit = driver.findElements(By.xpath("//button[@name='edit-link']"));
					waitforElement(60, SubmitTaxExpt.EDITCERTIFICATE, driver);
					EditLink = getText(SubmitTaxExpt.EDITLINK);
					driver.findElement(By.xpath("(//button[@name='edit-link'])[" + edit.size() + "]")).click();

					break;
				case "Renew Certificate":
					List<WebElement> renew = driver.findElements(By.xpath("//button[@name='renew-link']"));
					waitforElement(60, SubmitTaxExpt.RENEWCERTIFICATE, driver);
					RenewLink = getText(SubmitTaxExpt.RENEWLINK);
					driver.findElement(By.xpath("(//button[@name='renew-link'])[" + renew.size() + "]")).click();
					break;
				case "Reject Certificate":
					Thread.sleep(15000);
					break;
				}
				waitandswitchToFrame(SubmitTaxExpt.iFrame, driver);
				if (CloudData.get_data("CertificateOptions").equalsIgnoreCase("Verify Edit Link Other User")) {
					driver.get(EditLink);
					String errormessage = getText(SubmitTaxExpt.ERRORPAGE);
					assertTrue((errormessage.equalsIgnoreCase(
							"The application has encountered an error. Please try again. If the problem persists contact the person responsible for your deployment.")),
							"Fail, Able to access Edit/Renew Link for orther users");

				} else if (CloudData.get_data("CertificateOptions").equalsIgnoreCase("Verify Renew Link Other User")) {
					driver.get(RenewLink);
					String errormessage = getText(SubmitTaxExpt.ERRORPAGE);
					assertTrue((errormessage.equalsIgnoreCase(
							"The application has encountered an error. Please try again. If the problem persists contact the person responsible for your deployment.")),
							"Fail, Able to access Edit/Renew Link for orther users");
				} else {
					waitandswitchToFrame(SubmitTaxExpt.iFrame, driver);
					EnterFormValues(CloudData.get_data("NumberofScreens"), CloudData.get_data("FormCheckBoxes"),
							CloudData.get_data("FormRadioButton"), CloudData.get_data("FormTextBoxes"),
							CloudData.get_data("FormUploadForms"), CloudData.get_data("SelectFormAction"),
							CloudData.get_data("DatePickers"), CloudData.get_data("Signature"),
							CloudData.get_data("FEIN"));
					String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
					clear(SubmitTaxExpt.CompanyName);
					type(SubmitTaxExpt.CompanyName, CloudData.get_data("CompanyName"));
					String CompanyNamee = getAttribute(SubmitTaxExpt.CompanyName, "value");
					if (CompanyNamee.length() > 60) {
						assertTrue(CompanyNamee.length() > 60, "Fail; Company Name Boundary Value");
					}
					clear(SubmitTaxExpt.StreetAddress);
					type(SubmitTaxExpt.StreetAddress, CloudData.get_data("StreetAddress"));
					String streetadd = getAttribute(SubmitTaxExpt.StreetAddress, "value");
					if (streetadd.length() > 60) {
						assertTrue(streetadd.length() > 60, "Fail; Company Name Street Address");
					}
					clear(SubmitTaxExpt.City);
					type(SubmitTaxExpt.City, CloudData.get_data("City"));
					String citty = getAttribute(SubmitTaxExpt.City, "value");
					if (citty.length() > 60) {
						assertTrue(citty.length() > 60, "Fail; City Boundary Value");
					}
					selectByValue(SubmitTaxExpt.SelectState, CloudData.get_data("State"));
					clear(SubmitTaxExpt.PostalCode);
					type(SubmitTaxExpt.PostalCode, CloudData.get_data("PostalCode"));

					String postalCode = getAttribute(SubmitTaxExpt.PostalCode, "value");
					if (postalCode.length() > 10) {
						assertTrue(postalCode.length() > 10, "Fail; Postal Code Boundary Value");
					}
					selectByValue(SubmitTaxExpt.Country, CloudData.get_data("Country"));
					if (!CloudData.get_data("PhoneNumber").isEmpty()) {
						type(SubmitTaxExpt.PhoneNumber, CloudData.get_data("Country"));
						String phnu = getAttribute(SubmitTaxExpt.PhoneNumber, "value");
						if (phnu.length() > 20) {
							assertTrue(phnu.length() > 20, "Fail; Phone Number Boundary Value");
						}
					}
					if (!CloudData.get_data("CompanyEmail").isEmpty()) {
						clear(SubmitTaxExpt.Email);
						type(SubmitTaxExpt.Email, CloudData.get_data("CompanyEmail"));

						String email = getAttribute(SubmitTaxExpt.Email, "value");
						if (email.length() > 100) {
							assertTrue(email.length() > 100, "Fail;  Email Boundary Value");
						}
					}
					waitforElement(180, SubmitTaxExpt.NextButon, driver);
					JavaScriptclick(driver, SubmitTaxExpt.NextButon);

					// Additional Upload
					String[] multi = CloudData.get_data("MultiJurisdictions").split(",");
					if (VerifyObject(driver, SubmitTaxExpt.ADDITIONALDOCUMENTTITLEMULTI)) {
						driver.findElements(By.xpath("//select"));
						for (int j = 0; j < multi.length; j++) {
							selectByValue(driver.findElement(By.xpath("(//select)[" + (j + 1) + "]")), multi[j]);
						}
						waitforElement(180, SubmitTaxExpt.NextButon, driver);
						JavaScriptclick(driver, SubmitTaxExpt.NextButon);
					}

					if (VerifyObject(driver, SubmitTaxExpt.ADDITIONALDOCUMENTTITLE)) {
						String[] addupload = CloudData.get_data("AdditionalUpload").split(":");
						String upload = addupload[1];
						switch (addupload[0]) {
						case "SKIP":
							JavaScriptclick(driver, SubmitTaxExpt.ADDITIONALDOCUMENTS);
							Thread.sleep(7000);
							System.out.println(upload);
							String additional[] = upload.split("\\.");
							transactions.AutoItBrowser(driver, Browser, AutoITpath, AdjustmentFilePath, additional[0],
									"." + additional[1]);
							Thread.sleep(10000);
							JavaScriptclick(driver, SubmitTaxExpt.SKIP);
							waitforElement(180, SubmitTaxExpt.SKIPINDIALOUGE, driver);
							JavaScriptclick(driver, SubmitTaxExpt.SKIPINDIALOUGE);
							waitforElement(180, SubmitTaxExpt.NextButon, driver);
							JavaScriptclick(driver, SubmitTaxExpt.NextButon);
							break;
						case "4":
							int uploadcount = Integer.parseInt(addupload[0]);
							for (int j = 0; j < multi.length; j++) {
								if (multi[j].equalsIgnoreCase("Upload") || multi[j].isEmpty()) {
									for (int j1 = 0; j1 < uploadcount - 1; j1++) {
										JavaScriptclick(driver, SubmitTaxExpt.ADDITIONALDOCUMENTS);
										Thread.sleep(7000);
										System.out.println(upload);
										String additional1[] = upload.split("\\.");
										transactions.AutoItBrowser(driver, Browser, AutoITpath, AdjustmentFilePath,
												additional1[0], "." + additional1[1]);
										Thread.sleep(10000);
									}
									waitforElement(180, SubmitTaxExpt.NextButon, driver);
									JavaScriptclick(driver, SubmitTaxExpt.NextButon);
								}

							}
							assertTrue((VerifyObject(driver, SubmitTaxExpt.MAXIMUMLIMIT)),
									"Fail,Maximum Limit failed for Additional Upload;");
							break;
						case "1":
						case "2":
						case "3":
							int uploadcount1 = Integer.parseInt(addupload[0]);
							for (int j = 0; j < multi.length; j++) {
								if (multi[j].equalsIgnoreCase("Upload") || multi[j].isEmpty()) {
									for (int j1 = 0; j1 < uploadcount1; j1++) {
										JavaScriptclick(driver, SubmitTaxExpt.ADDITIONALDOCUMENTS);
										Thread.sleep(7000);
										System.out.println(upload);
										String additional1[] = upload.split("\\.");
										transactions.AutoItBrowser(driver, Browser, AutoITpath, AdjustmentFilePath,
												additional1[0], "." + additional1[1]);
										Thread.sleep(10000);
									}
									waitforElement(180, SubmitTaxExpt.NextButon, driver);
									JavaScriptclick(driver, SubmitTaxExpt.NextButon);
								}
							}
							break;
						}
					}
					waitforElement(180, SubmitTaxExpt.Name, driver);
					type(SubmitTaxExpt.Name, CloudData.get_data("Name"));
					type(SubmitTaxExpt.Title, CloudData.get_data("Title"));
					CloudData.get_data("Date").split("/");
					if (VerifyObject(driver, SubmitTaxExpt.DATEPICKER) && !CloudData.get_data("Date").isEmpty()) {
						JavaScriptclick(driver, SubmitTaxExpt.DATEPICKER);
						clear(SubmitTaxExpt.DATEPICKER);
						type(SubmitTaxExpt.DATEPICKER, CloudData.get_data("Date"));
						sendkeysTab(SubmitTaxExpt.DATEPICKER);
					}
					String signature[] = CloudData.get_data("Signature").split(":");
					switch (signature[0]) {
					case "Upload":
						JavaScriptclick(driver, SubmitTaxExpt.UploadSignature);
						Thread.sleep(7000);
						switch (Browser.toUpperCase()) {
						case "FIREFOX":
							JavaScriptclick(driver, SubmitTaxExpt.DropImage);
							break;
						case "CHROME":
						case "IE":
							click(SubmitTaxExpt.DropImage);
							break;
						}
						Thread.sleep(7000);
						System.out.println(signature[1]);
						String image[] = signature[1].split("\\.");
						transactions.AutoItBrowser(driver, Browser, AutoITpath, AdjustmentFilePath, image[0],
								"." + image[1]);
						Thread.sleep(10000);
						break;
					}
					waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);
					if (!CloudData.get_data("SelectFormAction").equalsIgnoreCase("Upload")) {
						JavaScriptclick(driver, SubmitTaxExpt.PreviewForm);
						switch (Browser) {
						case "Chrome":
							parentwindow = driver.getWindowHandle();
							for (String winHandle : driver.getWindowHandles()) {
								if (!winHandle.equals(parentwindow)) {
									driver.switchTo().window(winHandle);
								}
							}
							driver.close();
							driver.switchTo().window(parentwindow);
							waitandswitchToFrame(SubmitTaxExpt.iFrame, driver);
							break;
						}
					}
					JavaScriptclick(driver, SubmitTaxExpt.Submit);
					if (VerifyObject(driver, SubmitTaxExpt.INVALIDDATEFORMATE)
							|| VerifyObject(driver, SubmitTaxExpt.DATEGREATER)) {
						JavaScriptclick(driver, SubmitTaxExpt.DATEPICKER);
						clear(SubmitTaxExpt.DATEPICKER);
						String curdate = xGetCurrentDate("MM/dd/yyyy");
						type(SubmitTaxExpt.DATEPICKER, curdate);
						sendkeysTab(SubmitTaxExpt.DATEPICKER);
						JavaScriptclick(driver, SubmitTaxExpt.Submit);
					}
					waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);
					List<WebElement> formlist = driver.findElements(By.xpath("//li/a"));
					for (int j = 1; j < formlist.size(); j++) {
						if (VerifyObject(driver, driver.findElement(By.xpath("(//li/a)[" + j + "]"))))
							JavaScriptclick(driver, driver.findElement(By.xpath("(//li/a)[" + j + "]")));
					}
					if (VerifyObject(driver, driver.findElement(By.xpath("(//li/button)[1]")))) {
						driver.findElements(By.xpath("//li/button"));
						for (int j = 1; j < formlist.size(); j++) {
							JavaScriptclick(driver, driver.findElement(By.xpath("(//li/button)[" + j + "]")));
							/*
							 * Thread.sleep(3000); Set<String> window=driver.getWindowHandles();
							 * driver.switchTo().window(window.iterator().next()); Thread.sleep(5000);
							 * driver.close(); driver.switchTo().window(parentwindow);
							 */
						}
					}
					waitforElement(180, SubmitTaxExpt.DoneButton, driver);
					JavaScriptclick(driver, SubmitTaxExpt.DoneButton);
					// waitforElement(180, SubmitTaxExpt.EditExemptCertf, driver);
					driver.switchTo().defaultContent();
				}
			} catch (Exception e) {
				JavaScriptclick(driver, SubmitTaxExpt.CLOSEBUTTON);
				waitforElement(60, SubmitTaxExpt.CONFIRMBUTTON, driver);
				JavaScriptclick(driver, SubmitTaxExpt.CONFIRMBUTTON);
				driver.switchTo().defaultContent();
			}
		}
	}

	public void WizardSettings(String TestPath, String value) throws Exception {
		WizardSetting wizardsettings = PageFactory.initElements(driver, WizardSetting.class);
		CloudData.dataload(TestPath, value);
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			CloudData.setrowdata(i);
			// Set the "Wizard" checkbox to checked and press the "Save User" button.
			waitforElement(180, wizardsettings.WIZARDSETTINGSTAB, driver);
			click(wizardsettings.WIZARDSETTINGSTAB);
			Thread.sleep(2000);
			waitforElement(180, wizardsettings.SAVE, driver);
			click(driver.findElement(By.xpath("//div[@id='multiJurFormOpts']/descendant::label[contains(.,'"
					+ CloudData.get_data("FormPrecedence") + "')]")));

			String MJFP[] = CloudData.get_data("Multi-Jurisdiction Form Precedence").split(":");
			if (!VerifyObject(driver, "//h5[contains(.,'Multi-Jurisdiction Form Precedence')]/../div[1][contains(.,'"
					+ MJFP[0] + "')]")) {
				click(driver.findElement(
						By.xpath("//h5[contains(.,'Multi-Jurisdiction Form Precedence')]/../div[contains(.,'" + MJFP[0]
								+ "')]/i[@class='fa fa-chevron-up']")));
			}

			String FU[] = CloudData.get_data("Form_Upload").split(":");

			switch (FU[1].trim()) {
			case "Check":
				if (!(driver.findElement(By.xpath("//label[contains(.,'" + FU[0] + "')]/input")).isSelected())) {
					click(driver.findElement(By.xpath("//label[contains(.,'" + FU[0] + "')]/input")));
				}
				break;
			case "UnCheck":
				if (driver.findElement(By.xpath("//label[contains(.,'" + FU[0] + "')]/input")).isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'" + FU[0] + "')]/input")));
				}
				break;
			}

			String CS[] = CloudData.get_data("Certificate_Scope").split(":");

			switch (CS[1].trim()) {
			case "Check":
				if (!driver.findElement(By.xpath("//label[contains(.,'" + CS[0] + "')]/input")).isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'" + CS[0] + "')]/input")));
				}
				break;
			case "UnCheck":
				if (driver.findElement(By.xpath("//label[contains(.,'" + CS[0] + "')]/input")).isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'" + CS[0] + "')]/input")));
				}
				break;
			}
			String PQ[] = CloudData.get_data("Product_Question").split(":");
			switch (PQ[1].trim()) {
			case "Check":
				if (!driver.findElement(By.xpath("//label[contains(.,'" + PQ[0] + "')]/input")).isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'" + PQ[0] + "')]/input")));
				}
				break;
			case "UnCheck":
				if (driver.findElement(By.xpath("//label[contains(.,'" + PQ[0] + "')]/input")).isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'" + PQ[0] + "')]/input")));
				}
				break;
			}

			String PL[] = CloudData.get_data("Products_listing").split(":");
			switch (PL[1].trim()) {
			case "Check":
				if (!driver.findElement(By.xpath("//label[contains(.,'" + PL[0] + "')]/input")).isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'" + PL[0] + "')]/input")));
				}
				break;
			case "UnCheck":
				if (driver.findElement(By.xpath("//label[contains(.,'" + PL[0] + "')]/input")).isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'" + PL[0] + "')]/input")));
				}
				break;
			}
			String AU[] = CloudData.get_data("AdditionalDocumentation").split(":");
			switch (AU[1].trim()) {
			case "Check":
				if (!driver.findElement(By.xpath("//label[contains(.,'" + AU[0] + "')]/input")).isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'" + AU[0] + "')]/input")));
				}
				break;
			case "UnCheck":
				if (driver.findElement(By.xpath("//label[contains(.,'" + AU[0] + "')]/input")).isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'" + AU[0] + "')]/input")));
				}
				break;
			}
			click(wizardsettings.SAVE);
		}
	}

	public void VerifyCertificatesinECPortal(String TestPath, String value) throws Exception {
		WizardSetting wizard = PageFactory.initElements(driver, WizardSetting.class);
		String path;
		File directory = new File(".//");
		CloudData.dataload(TestPath, value);
		xDownloadFiles("ECWCertificateReports");
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			driver.getWindowHandle();
			CloudData.setrowdata(i);
			waitforElement(300, wizard.CERTIFICATESEARCHL, driver);
			click(wizard.CERTIFICATESEARCHL);
			waitforElement(180, wizard.SEARCHBUTTON, driver);
			click(wizard.SEARCHBUTTON);
			Thread.sleep(5000);
			selectByValue(wizard.PAGNATION100ROWS, "100 rows");
			Thread.sleep(3000);
			waitforElement(180, "//span[contains(.,'" + CloudData.get_data("State")
					+ "')]/../../../descendant::button[@class='btn btn-link']", driver);
			assertTrue(
					(VerifyObject(driver,
							"//span[contains(.,'" + CloudData.get_data("State")
									+ "')]/../../../descendant::button[@class='btn btn-link']")),
					"Fail, " + CloudData.get_data("State") + "Certificate not synched in ECPortal;");
			if (VerifyObject(driver, "//span[contains(.,'" + CloudData.get_data("State")
					+ "')]/../../../descendant::button[@class='btn btn-link']")) {
				click(driver.findElement(By.xpath("//span[contains(.,'" + CloudData.get_data("State")
						+ "')]/../../../descendant::button[@class='btn btn-link']")));
				Thread.sleep(5000);
				switch (Browser) {
				case "Chrome":
					path = directory.getCanonicalPath() + "\\" + "resources\\testdata\\actual" + "\\"
							+ "ECWCertificateReports";
					MoveLatestDownloadFileFromOnedirtoAnother(path);
					Thread.sleep(10000);
					new File(LatestDownloadedFile("ECWCertificateReports", true));
					String DirName = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
							+ "ECWExemptCertificates";
					PDFComparison(Filepath, DirName + "\\" + FileName);
					break;
				case "Firefox":
				case "IE":
					LatestDownloadedFile("ECWCertificateReports", false);
					Thread.sleep(5000);
					String DirName1 = directory.getCanonicalPath() + "\\" + "resources\\testdata\\expected" + "\\"
							+ "ECWExemptCertificates\\" + CloudData.get_data("FileName");
					PDFComparison(Filepath, DirName1);
					break;
				}
			}
			if (!CloudData.get_data("VerifySupportDoc").isEmpty()) {
				if (VerifyObject(driver, "//span[contains(.,'" + CloudData.get_data("State")
						+ "')]/../../../descendant::span[@class='glyphicon glyphicon-chevron-down']")) {
					click(driver.findElement(By.xpath("//span[contains(.,'" + CloudData.get_data("State")
							+ "')]/../../../descendant::span[@class='glyphicon glyphicon-chevron-down']")));
					String support[] = CloudData.get_data("VerifySupportDoc").split(",");
					for (int j = 0; j < support.length; j++) {
						driver.findElement(By.xpath(
								"//div[@class='ceritifice-search__additional-documentation-links']/ol/li/button[contains(.,'"
										+ support[j] + "')]"))
								.click();
						Thread.sleep(2000);
					}
				}
			}
		}
	}

	public void VersionCheck(String TestPath, String value) throws Exception {
		SubmitTaxExemption AS = PageFactory.initElements(driver, SubmitTaxExemption.class);
		CloudData.dataload(TestPath, value);
		waitforElement(180, AS.VERSIONTAB, driver);
		click(AS.VERSIONTAB);
		waitforElement(180, AS.VERSIONS, driver);
		assertTrue((VerifyObject(driver, "//h3[contains(.,'Version')]/../../div[2]/div[contains(.,'"
				+ CloudData.get_data("ECWVersion") + "')]")), "Fail, ECWVersion mismacth ");
		assertTrue(
				(VerifyObject(driver,
						"//h3[contains(.,'Version')]/../../div[2]/div[contains(.,'"
								+ CloudData.get_data("DataUpdateVersion") + "')]")),
				"Fail, DataUpdateVersion mistach ");
	}

	public void ECPortalTabsVerification() throws Exception {
		driver.getWindowHandle();
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		PageFactory.initElements(driver, ConfigurePage.class);
		RegistrationPage RP = PageFactory.initElements(driver, RegistrationPage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		if (Roles.equalsIgnoreCase("Admin")) {
			click(PUL.DASHBOARDLINK);
		} else {
			click(PUL.ECWIZARD);
		}

		// Settings Verification
		if ((Roles.equalsIgnoreCase("Admin,ECW Portal Admin,ECW Portal User,"))) {
			assertTrue(("Exemption Certificate Wizard | Vertex Cloud".equalsIgnoreCase(driver.getTitle())),
					"Fail, Exemption Certificate Wizad Title not match with Expected Title;");
			// DASHBOARD Page Verification
			assertTrue((VerifyObject(driver, RP.ECWheaderText)), "Fail,ECWheaderText not displayed in Dashboard;");
			assertTrue((VerifyObject(driver, RP.ECWWidgetText)), "Fail,ECWWidgetText not displayed in Dashboard;");
			assertTrue((VerifyObject(driver, RP.LaunchSite)), "Fail,LaunchSite not displayed in Dashboard;");
			// click(RP.LaunchSite);
			click(PUL.ECWSettingslink);
			Thread.sleep(5000);
			assertTrue((VerifyObject(driver, PUL.ECWmycompany)), "Fail,My company text not displayed in Settings;");
			assertTrue((VerifyObject(driver, PUL.ECWmyComName)), "Fail,My Company name not displayed in Settings;");
			assertTrue((VerifyObject(driver, PUL.ECWmyComaddress)),
					"Fail,My Companty address not displayed in Settings;");
			assertTrue((VerifyObject(driver, PUL.ECWmyComPhone)), "Fail,phonenumber not displayed in Settings;");
			assertTrue((VerifyObject(driver, PUL.EditofMycompany)), "Fail,Edit link not displayed in Settings;s");
			assertTrue((VerifyObject(driver, PUL.ECWHeaderText)), "Fail,ECWheaderText not displayed in Settings;");
			assertTrue((VerifyObject(driver, PUL.ECWURL)), "Fail,ECW Url not displayed in Settings;");
			String EcwUrl = driver
					.findElement(By.xpath("//div/strong[contains(.,'URL:')]/../../div[@class='span9 ng-binding']"))
					.getText();
			System.out.println(EcwUrl);
			String evn = Environment.toLowerCase();
			assertTrue(EcwUrl.equalsIgnoreCase("https://" + evn + "ecwportal.vertexsmb.com"),
					"Fail,ECW URL is correct;");
			assertTrue((VerifyObject(driver, PUL.ECWClientCode)), "Fail,ECW Client code not displayed in Settings;");
		}
		if (Roles.equalsIgnoreCase("ECW Portal Admin,ECW Portal User") || Roles.equalsIgnoreCase("ECW Portal Admin")
				|| Roles.equalsIgnoreCase("ECW Portal User")) {
			assertTrue((VerifyObject(driver, PUL.CONFIGURELINK)),
					"Fail,Configure Tab displayed for ECW Portal Admin or User;");
			assertTrue((VerifyObject(driver, PUL.CALCULATETAXES)),
					"Fail,CalTaxes Tab displayed for ECW Portal Admin or User;");
			assertTrue((VerifyObject(driver, PUL.MANAGETAXRETURNS)),
					"Fail,Manage Tax Returns Tab displayed for ECW Portal Admin or User;");
			assertTrue((VerifyObject(driver, PUL.REPORTSLINK)),
					"Fail,REPORTSLINK Tab displayed for ECW Portal Admin or User;");
		}
		if (Roles.equalsIgnoreCase("ECW Portal User")) {
			assertTrue((VerifyObject(driver, PUL.ECWSettingslink)),
					"Fail,Settings Tab is displaying for ECW Portal User;");
		}

		if (Roles.equalsIgnoreCase("Admin")) {
			assertTrue((VerifyObject(driver, PUL.ECWIZARD)), "Fail,EC Wizard Tab displaying for Admin User;");
		}

		// contactsupport
		assertTrue((VerifyObject(driver, PUL.contactsupport)), "Fail,contactsupport link is not displayed;");
		// Visit Help Center should not display
		assertTrue((VerifyObject(driver, PUL.GETHELPLINK)), "Fail,Visit help center link displayed;");
		// What's New section should not display
		assertTrue((VerifyObject(driver, PUL.WHATSNEWLINKTAXCENTRAL)), "Fail,What's New link displayed;");
	}

	public void ECWizardTabsVerification(String TestPath, String value) throws Exception {
		String[] Data = value.split(";");
		if (Browser.equalsIgnoreCase("Firefox")) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		} else {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		PageFactory.initElements(driver, FreeUserLinks.class);
		LookupTaxRates LTR = PageFactory.initElements(driver, LookupTaxRates.class);
		Robot rob = new Robot();
		xDownloadFiles("SERAgreementTERMServicePDF");
		Sheet Prousertitlesheet = xGetdtSheet(TestPath, value);
		ProUserTitles = "";
		String parentwindow = driver.getWindowHandle();
		int DashBoard_Title = 1, Configure_Title = 2, CalculateTaxes_Title = 3, DistributeTaxes_Title = 4,
				ManageAdjust_Title = 5, Lookuprates_Title = 6, Managetaxreturns_Title = 7, EditCompany_Title = 14,
				ViewAllUsers_Title = 15, UpdateBilling_Title = 16, BillingReports_Title = 18, Adapters_Title = 19,
				DocumentCenter_Title = 22, Help_Center_Url = 23, RateFile = 24, ViewAllUsers_Verify = 25,
				CPA_Topics = 26, Data_Integrity = 35, Global_Compliance = 36;
		for (int i = 2; i < Data.length; i++) {
			int Data_Row = Integer.parseInt(Data[i]);
			click(PUL.DASHBOARDLINK);
			waitforElement(180, PUL.UPDATEANALYSIS, driver);
			// POR-630 verifyuing Inline help link not present
			assertTrue((!VerifyObject(driver, PUL.INLINEHELP)), "Fail,Inline help exists;");
			assertTrue(
					(Prousertitlesheet.getCell(DashBoard_Title, Data_Row).getContents()
							.equalsIgnoreCase(driver.getTitle())),
					"Fail, Dash Board Actual Title not match with Expected Title;");
			// DASHBOARD Page Verification
			assertTrue((VerifyObject(driver, PUL.COMPANYDD)), "Fail,Company Dropdown not displayed;");
			assertTrue((VerifyObject(driver, PUL.FILINGPERIODDD)), "Fail,FilingPeriod Dropdown not displayed;");
			assertTrue((VerifyObject(driver, PUL.UPDATERESULTS)), "Fail,Update Results not displayed;");
			assertTrue((VerifyObject(driver, PUL.UNITEDSTATES)), "Fail,UnitedStates Button not displayed;");
			assertTrue((VerifyObject(driver, PUL.CANADA)), "Fail,Canada Button not displayed;");
			assertTrue((VerifyObject(driver, PUL.MAP)), "Fail,Map not displayed;");
			assertTrue((VerifyObject(driver, PUL.OVERALLNUMBERSSECTION)), "Fail,OverallNumbers Section not displayed;");
			assertTrue((VerifyObject(driver, PUL.STATESTATSTICS)), "Fail,STATESTATSTICS Section not displayed;");
			assertTrue((VerifyObject(driver, PUL.CURRENTLYSHOWINGDD)), "Fail,CURRENTLYSHOWINGDD not displayed;");
			assertTrue((VerifyObject(driver, PUL.SORTBYDD)), "Fail,SORTBYDD_XPATH not displayed;");
			click(PUL.CONFIGURELINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforText(180, PUL.CONFIGUREOVERVIEW, driver);
			assertTrue(
					(Prousertitlesheet.getCell(Configure_Title, Data_Row).getContents()
							.equalsIgnoreCase(driver.getTitle())),
					"Fail, Configure Actual Title not match with Expected Title;");
			JavaScriptMousehover(driver, PUL.taxesHover);
			JavaScriptclick(driver, PUL.CALCULATETAXES);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforText(180, PUL.LINEITEMS, driver);
			assertTrue(
					(Prousertitlesheet.getCell(CalculateTaxes_Title, Data_Row).getContents()
							.equalsIgnoreCase(driver.getTitle())),
					"Fail, Calculate Taxes Actual Title not match with Expected Title;");
			// Calculate Taxes Page Verificaiton
			assertTrue((VerifyObject(driver, PUL.INVOICEDEATAILS)), "Fail,INVOICEDEATAILS not displayed;");
			assertTrue((VerifyObject(driver, PUL.SHIPPINGINFORMATION)), "Fail,SHIPPINGINFORMATION not displayed;");
			assertTrue((VerifyObject(driver, PUL.LINEITEMS)), "Fail,LINEITEMS not displayed;");
			JavaScriptMousehover(driver, PUL.taxesHover);
			JavaScriptclick(driver, PUL.DISTRIBUTETAX);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.DISTRIBUTETAXESTEXT, driver);
			assertTrue(VerifyObject(driver, PUL.ERRORDISTRIBUTE) || VerifyObject(driver, PUL.TRANSDISTRIBUTE),
					"Fail,Error Message is not displayed without configure;");
			assertTrue(
					(Prousertitlesheet.getCell(DistributeTaxes_Title, Data_Row).getContents()
							.equalsIgnoreCase(driver.getTitle())),
					"Fail, Distribute Taxes Actual Title not match with Expected Title;");
			// Verifying Heading text as per POR_5536 in 4.6
			assertTrue((VerifyObject(driver, PUL.DISTRIBUTEHEADING)), "Fail, Distribute Heading Text not Correct;");
			// Verifying TAID as per 6.7.1
			click(PUL.RatesJurisdictions);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforText(180, PUL.LOOKUPRATE, driver);
			assertTrue(
					(Prousertitlesheet.getCell(Lookuprates_Title, Data_Row).getContents()
							.equalsIgnoreCase(driver.getTitle())),
					"Fail, LookUp Tax Rates Actual Title not match with Expected Title;");
			assertTrue((VerifyObject(driver, LTR.TAXAREAID)), "Fail, Tax area id not displayed;");
			if ((Prousertitlesheet.getCell(Data_Integrity, Data_Row).getContents() != "")
					&& (Environment.equalsIgnoreCase("DEV"))) {
				click(PUL.DATAINTEGRITY);
				assertTrue(
						(Prousertitlesheet.getCell(Data_Integrity, Data_Row).getContents()
								.equalsIgnoreCase(driver.getTitle())),
						"Fail, Data Integrity Actual Title not match with Expected Title;");
			}
			if ((Prousertitlesheet.getCell(Global_Compliance, Data_Row).getContents() != "")
					&& (Environment.equalsIgnoreCase("DEV"))) {
				click(PUL.GLOBALCOMPLIANCE);
				assertTrue(
						(Prousertitlesheet.getCell(Global_Compliance, Data_Row).getContents()
								.equalsIgnoreCase(driver.getTitle())),
						"Fail, Global Compliance Actual Title not match with Expected Title;");
			}
			JavaScriptclick(driver, PUL.SettingsHover);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.subscriptionType, driver);
			String Subtype = getText(PUL.subscriptionType);
			if (Subtype.contains("Tax Calculations and Returns")) {
				JavaScriptMousehover(driver, PUL.taxesHover);
				JavaScriptclick(driver, PUL.MANAGEADJUSTMENTFILELINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.ERRORREPORT, driver);
				assertTrue(
						(Prousertitlesheet.getCell(ManageAdjust_Title, Data_Row).getContents()
								.equalsIgnoreCase(driver.getTitle())),
						"Fail, Managa Adjustment Actual Title not match with Expected Title;");
				// Adjsutment File Page Verification
				assertTrue((VerifyObject(driver, PUL.UPLOADNEWFILE)), "Fail,UPLOADNEWFILE not displayed;");
				assertTrue((VerifyObject(driver, PUL.FILEUPLOADDATE)), "Fail,FILEUPLOADDATE not displayed;");
				assertTrue((VerifyObject(driver, PUL.ADJUSTMENTFILENAME)), "Fail,ADJUSTMENTFILENAME not displayed;");
				assertTrue((VerifyObject(driver, PUL.ORIGINALFILE)), "Fail,ORIGINALFILE not displayed;");
				assertTrue((VerifyObject(driver, PUL.SUCCESSREPORT)), "Fail,SUCCESSREPORT not displayed;");
				assertTrue((VerifyObject(driver, PUL.ERRORREPORT)), "Fail,ERRORREPORT not displayed;");
			}
			if (Subtype.contains("Returns Only")) {
				assertTrue((!VerifyObject(driver, PUL.MANAGEADJUSTMENTFILELINK)),
						"Fail, Manage adjustment file link displaying for the Returns only client;");
			}
			/*
			 * click(PUL.LOOKUPTAXRATESLINK); waitforText(180, PUL.LOOKUPRATE, driver);
			 * Verification=""; Verification=(Prousertitlesheet.getCell(Lookuprates_Title,
			 * Data_Row).getContents().equalsIgnoreCase(driver.getTitle()))==true?
			 * Verification: Verification+
			 * "Fail, LookUp Tax Rates Actual Title not match with Expected Title;";
			 * status=status+ Verification; VertexSMB.RowNo =
			 * bussFunction.xResult(Verification,RSheet,Prousertitlesheet.getCell(
			 * Lookuprates_Title,
			 * Data_Row).getContents(),driver.getTitle(),value,NoOfVerify++);
			 */
			// Verifying Manage Tax Returns Tab is not displaying for Tax Calc Only User
			if (Prousertitlesheet.getCell(Managetaxreturns_Title, Data_Row).getContents().equalsIgnoreCase("No")) {
				assertTrue((!VerifyObject(driver, PUL.ReturnsHover)),
						"Fail,Manage Tax Returns Tab is displayed for Tax Calc Only User;");
			} else {
				JavaScriptMousehover(driver, PUL.ReturnsHover);
				JavaScriptclick(driver, PUL.MANAGETAXRETURNS);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.MOSTRECENT, driver);
				assertTrue(
						(Prousertitlesheet.getCell(Managetaxreturns_Title, Data_Row).getContents()
								.equalsIgnoreCase(driver.getTitle())),
						"Fail, Manage Tax Returns Actual Title not match with Expected Title;");
				// Manage Tax Returns Page Verification
				assertTrue((VerifyObject(driver, PUL.FILINGPERIOD)), "Fail,FILINGPERIOD not displayed;");
				assertTrue((VerifyObject(driver, PUL.RETURN)), "Fail,RETURN not displayed;");
				assertTrue((VerifyObject(driver, PUL.DUEDATE)), "Fail,DUEDATE not displayed;");
				assertTrue((VerifyObject(driver, PUL.PREPAYMENTS)), "Fail,PREPAYMENTS not displayed;");
				assertTrue((VerifyObject(driver, PUL.MOSTRECENT)), "Fail,MOSTRECENT not displayed;");
				rob.mouseMove(0, 0);
				if (Subtype.contains("Tax Calculations and Returns")) {
					assertTrue((!VerifyObject(driver, PUL.RETURNSONLYUPLOAD)),
							"Fail,Returns only adjustment file link displaying for the Tax Calculations and Returns Client;");
				}
				if (Subtype.contains("Returns Only")) {
					JavaScriptMousehover(driver, PUL.ReturnsHover);
					JavaScriptclick(driver, PUL.RETURNSONLYUPLOAD);
					waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
					waitforElement(180, PUL.RETURNSONLYTITLE, driver);
					assertTrue((VerifyObject(driver, PUL.RETURNSONLYTITLE)),
							"Fail, Returns Only Tab page title changed;");
				}
			}
			// Verify Customer ID Report link not exists
			assertTrue((!VerifyObject(driver, PUL.CUSTOMERIDREPORT)),
					"Customer ID Report link displayed left side for Client Admin User;");
			// calling reports
			// statusin=Reports(RSheet, driver,Data_Row,Prousertitlesheet,rob, value);
			JavaScriptMousehover(driver, PUL.SettingsHover);
			JavaScriptclick(driver, PUL.EDITCOMPANYLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.CANCEL, driver);
			assertTrue(
					(Prousertitlesheet.getCell(EditCompany_Title, Data_Row).getContents()
							.equalsIgnoreCase(driver.getTitle())),
					"Fail, Edit My Company Actual Title not match with Expected Title;");
			// Edit My Company Page Verification
			assertTrue((VerifyObject(driver, PUL.NAME)), "Fail,NAME Not Displayed;");
			assertTrue((VerifyObject(driver, PUL.PHONE)), "Fail,PHONE Not Displayed;");
			assertTrue((VerifyObject(driver, PUL.COUNTRY)), "Fail,COUNTRY_XPATH Not Displayed;");
			assertTrue((VerifyObject(driver, PUL.ADD1)), "Fail,ADD1 Not Displayed;");
			assertTrue((VerifyObject(driver, PUL.ADD2)), "Fail,ADD2 Not Displayed;");
			Thread.sleep(2000);
			assertTrue((VerifyObject(driver, PUL.CITY)), "Fail,CITY Not Displayed;");
			assertTrue((VerifyObject(driver, PUL.STATE)), "Fail,STATE Not Displayed;");
			assertTrue((VerifyObject(driver, PUL.ZIP)), "Fail,ZIP Not Displayed;");
			assertTrue((VerifyObject(driver, PUL.SAVE)), "Fail,SAVE Not Displayed;");
			assertTrue((VerifyObject(driver, PUL.CANCEL)), "Fail,CANCEL Not Displayed;");
			// BPO Client View All Users Link not to Verify
			if (Prousertitlesheet.getCell(ViewAllUsers_Verify, Data_Row).getContents().equalsIgnoreCase("YES")) {
				JavaScriptMousehover(driver, PUL.SettingsHover);
				JavaScriptclick(driver, PUL.VIEWALLUSERSLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.CREATENEWUSER, driver);
				assertTrue(
						(Prousertitlesheet.getCell(ViewAllUsers_Title, Data_Row).getContents()
								.equalsIgnoreCase(driver.getTitle())),
						"Fail, View All Users Actual Title not match with Expected Title;");
				// Verifying Create New User Button as per POR-5537 in 4.6
				assertTrue((VerifyObject(driver, PUL.CREATENEWUSER)), "Fail, Create New User Button not Displayed;");
			}
			JavaScriptMousehover(driver, PUL.SettingsHover);
			JavaScriptclick(driver, PUL.UPDATEBILLINGLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.SUBMITBILINFO, driver);
			assertTrue(
					(Prousertitlesheet.getCell(UpdateBilling_Title, Data_Row).getContents()
							.equalsIgnoreCase(driver.getTitle())),
					"Fail, Update Billing Actual Title not match with Expected Title;");
			JavaScriptMousehover(driver, PUL.SettingsHover);
			JavaScriptclick(driver, PUL.BILLINGREPORTSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforText(180, PUL.BILLINGREPORTHEADING, driver);
			assertTrue(
					(Prousertitlesheet.getCell(BillingReports_Title, Data_Row).getContents()
							.equalsIgnoreCase(driver.getTitle())),
					"Fail, Billing Reports Actual Title not match with Expected Title;");
			// Verifying Billing Report Heading Text as per POR-5536 in 4.6
			assertTrue((VerifyObject(driver, PUL.BILLINGREPORTHEADING)),
					"Fail, Billing Report Heading Text not Correct;");
			JavaScriptMousehover(driver, PUL.SettingsHover);
			JavaScriptclick(driver, PUL.ADAPTERSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforText(180, PUL.LOOKUPURL, driver);
			assertTrue(
					(Prousertitlesheet.getCell(Adapters_Title, Data_Row).getContents()
							.equalsIgnoreCase(driver.getTitle())),
					"Fail, My Connectors Actual Title not match with Expected Title;");
			// View All Connectors Page Verificaiton
			assertTrue((VerifyObject(driver, PUL.TRUSTEDIDLABEL)), "Fail,Trusted Id Label is not present;");
			assertTrue((VerifyObject(driver, PUL.CONNECTORNAME)), "Fail,ConnectoreName is not present in table;");
			assertTrue((VerifyObject(driver, PUL.ERPURL)), "Fail,ERP Connector URL is not present in table;");
			assertTrue((VerifyObject(driver, PUL.LOOKUPURL)), "Fail,Address Lookup URL is not present in table;");
			JavaScriptclick(driver, PUL.SettingsHover);
			if (Prousertitlesheet.getCell(DocumentCenter_Title, Data_Row).getContents() != "") {
				waitforElement(180, PUL.SERVICEAGREEMENTVIEWPDFPRE, driver);
			} else {
				waitforElement(180, PUL.SERVICEAGREEMENTVIEWPDFPRO, driver);
			}
			if (!(Browser.equalsIgnoreCase("IE"))) {
				if (Prousertitlesheet.getCell(DocumentCenter_Title, Data_Row).getContents() != "") {
					JavaScriptclick(driver, PUL.SERVICEAGREEMENTVIEWPDFPRE);
				} else {
					JavaScriptclick(driver, PUL.SERVICEAGREEMENTVIEWPDFPRO);
				}
				Thread.sleep(5000);
				if (Browser.equalsIgnoreCase("FireFox")) {
					LatestDownloadedFile("SERAgreementTERMServicePDF", false);
				}
				if (Browser.equalsIgnoreCase("Chrome")) {
					for (String winHandle : driver.getWindowHandles()) {
						if (!winHandle.equals(parentwindow)) {
							driver.switchTo().window(winHandle);
						}
					}
					driver.close();
					driver.switchTo().window(parentwindow);
					if (Prousertitlesheet.getCell(DocumentCenter_Title, Data_Row).getContents() != "") {
						waitforElement(180, PUL.SERVICEAGREEMENTVIEWPDFPRE, driver);
					} else {
						waitforElement(180, PUL.SERVICEAGREEMENTVIEWPDFPRO, driver);
					}
				}
				JavaScriptclick(driver, PUL.TERMSSERVICEVIEWPDF);
				Thread.sleep(5000);
				if (Browser.equalsIgnoreCase("FireFox")) {
					LatestDownloadedFile("SERAgreementTERMServicePDF", false);
				}
				if (Browser.equalsIgnoreCase("Chrome")) {
					for (String winHandle : driver.getWindowHandles()) {
						if (!winHandle.equals(parentwindow)) {
							driver.switchTo().window(winHandle);
						}
					}
					driver.close();
					driver.switchTo().window(parentwindow);
					waitforElement(180, PUL.TERMSSERVICEVIEWPDF, driver);
				}
			}
			waitforElement(180, PUL.VIEWALLCONNECTORSBTN, driver);
			JavaScriptclick(driver, PUL.VIEWALLCONNECTORSBTN);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.TRUSTEDID, driver);
			Thread.sleep(5000);
			assertTrue((VerifyObject(driver, PUL.TRUSTEDID)),
					"Fail, System should not navigate to the View All Connectors Page ;");
			assertTrue((VerifyObject(driver, PUL.CONTACTSUPPORTHREF)),
					"Fail, Contact Support Actual Title not match with Expected Title;");
			// Verifying RateFile Link
			if (Prousertitlesheet.getCell(RateFile, Data_Row).getContents() != "") {
				assertTrue((VerifyObject(driver, PUL.RATEFFILEMENLINK)), "Fail, RateFile Menu Link not Displayed;");
				JavaScriptMousehover(driver, PUL.RateFileHover);
				JavaScriptclick(driver, PUL.DWLDRATEFILE_LINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.MONTHYEAR, driver);
				assertTrue((VerifyObject(driver, PUL.RATEFILETEMPLATELINK)),
						"Fail,RateFileTemplate Link not Displayed;");
				assertTrue((VerifyObject(driver, PUL.MONTHYEAR)), "Fail, Month Drop Down not Displayed;");
				JavaScriptMousehover(driver, PUL.RateFileHover);
				JavaScriptclick(driver, PUL.MYRATEFILE_LINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.UPDNEWFILELINK, driver);
				assertTrue((VerifyObject(driver, PUL.EXTENDEDHELPRATEFILE)), "Fail, Extended Help Link not Displayed;");
				assertTrue((VerifyObject(driver, PUL.UPDNEWFILELINK)), "Fail, Upload New File Link not Displayed;");
			}
			if (Prousertitlesheet.getCell(DocumentCenter_Title, Data_Row).getContents() != "") {
				JavaScriptclick(driver, PUL.DOCUMENTCENTRE);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				waitforElement(180, PUL.DOCUMENTCENTERHEADING, driver);
				assertTrue(
						(Prousertitlesheet.getCell(DocumentCenter_Title, Data_Row).getContents()
								.equalsIgnoreCase(driver.getTitle())),
						"Fail, Document Center Actual Title not match with Expected Title;");
				// Verifying Document Center Heading Text as per POR-5536 in 4.6
				assertTrue((!VerifyObject(driver, PUL.DOCUMENTCENTRE)), "Fail,Document Centre Link is Present;");
			} else {
				assertTrue((VerifyObject(driver, PUL.DOCUMENTCENTERHEADING)),
						"Fail, Document Center Heading Text not Correct;");
			}
			// Help Center Verification
			if (Browser.equalsIgnoreCase("IE")) {
				scrollDown(driver);
				click(PUL.GETHELPLINK);
			} else {
				JavaScriptclick(driver, PUL.GETHELPLINK);
			}
			Thread.sleep(3000);
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(parentwindow)) {
					driver.switchTo().window(winHandle);
				}
			}
			// To Handle Additional encryption popup displaying
			if (Browser.equalsIgnoreCase("Firefox")) {
				try {
					Alert javascriptAlert = driver.switchTo().alert();
					javascriptAlert.accept();
				} catch (Exception e) {
				}
			}
			waitforText(180, PUL.VISITHELPCENTERWHATSNEW, driver);
			if (Prousertitlesheet.getCell(CPA_Topics, Data_Row).getContents().equalsIgnoreCase("YES")) {
				waitforElement(180, PUL.CUSTOMERTOPICS, driver);
				assertTrue((VerifyObject(driver, PUL.PARTNERTOPICS)),
						"Fail,Partner Topics Panel not displayed for CPA Console;");
				assertTrue((VerifyObject(driver, PUL.CUSTOMERTOPICS)),
						"Fail,Customer Topics Panel not displayed for CPA Console;");
			}
			if (Prousertitlesheet.getCell(Help_Center_Url, Data_Row).getContents() != "") {
				assertTrue(
						(Prousertitlesheet.getCell(Help_Center_Url, Data_Row).getContents()
								.equalsIgnoreCase(driver.getTitle())),
						"Fail, System should not redirect the user to VERTEX Help center Page without any additional authentication;");
				driver.close();
				driver.switchTo().window(parentwindow);
				waitforElement(180, PUL.GETHELPLINK, driver);
				if (Browser.equalsIgnoreCase("IE")) {
					scrollUp(driver);
				}
				JavaScriptclick(driver, PUL.DASHBOARDLINK);
				waitforElement(180, PUL.UPDATEANALYSIS, driver);
			} else {
				driver.close();
				driver.switchTo().window(parentwindow);
				waitforElement(180, PUL.GETHELPLINK, driver);
				if (Browser.equalsIgnoreCase("IE")) {
					scrollUp(driver);
				}
				JavaScriptclick(driver, PUL.DASHBOARDLINK);
				waitforElement(180, PUL.UPDATEANALYSIS, driver);
			}
			// What's New Verification
			if (Browser.equalsIgnoreCase("IE")) {
				scrollDown(driver);
				click(PUL.WHATSNEWLINKTAXCENTRAL);
			} else {
				JavaScriptclick(driver, PUL.WHATSNEWLINKTAXCENTRAL);
			}
			Thread.sleep(3000);
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(parentwindow)) {
					driver.switchTo().window(winHandle);
				}
			}
			// To Handle Additional encryption popup displaying
			if (Browser.equalsIgnoreCase("Firefox")) {
				try {
					Alert javascriptAlert = driver.switchTo().alert();
					javascriptAlert.accept();
				} catch (Exception e) {
				}
			}
			Thread.sleep(5000);
			waitforElement(180, PUL.SEARCHWHATSNEW, driver);
			assertTrue((VerifyObject(driver, PUL.SEARCHWHATSNEW)), "Fail, SEARCH Button in What's NEW;");
			driver.close();
			driver.switchTo().window(parentwindow);
			if (Browser.equalsIgnoreCase("IE")) {
				scrollUp(driver);
			}
			JavaScriptclick(driver, PUL.DASHBOARDLINK);
			waitforElement(180, PUL.UPDATEANALYSIS, driver);
		}
	}

	public void Customers(String TestPath, String value) throws Exception {
		xGetdtSheet(TestPath, value);
		driver.getWindowHandle();
		SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		CloudData.dataload(TestPath, value);
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			CloudData.setrowdata(i);
			waitforElement(180, PUL.CONFIGURELINK, driver);
			JavaScriptclick(driver, PUL.CONFIGURELINK);
			waitforElement(60, CNP.PENDINGCERTIFICATES, driver);
			assertTrue((VerifyObject(driver, CNP.PENDINGCERTIFICATES)),
					"Fail,PENDINGCERTIFICATES text is not displaying");
			String certificatestatus = CloudData.get_data("Status");
			switch (certificatestatus) {
			case "RejectCertificates":
				waitforElement(180, SubmitTaxExpt.SETTINGS, driver);
				switch (DriverManager.Browser.toUpperCase()) {
				case "FIREFOX":
					click(SubmitTaxExpt.SETTINGS);
					break;
				case "CHROME":
					JavaScriptclick(driver, SubmitTaxExpt.SETTINGS);
					break;
				case "IE":
					JavaScriptclick(driver, SubmitTaxExpt.SETTINGS);
					break;
				}
				waitforElement(180, SubmitTaxExpt.ALLCERTSETTINGS, driver);
				switch (DriverManager.Browser.toUpperCase()) {
				case "FIREFOX":
					click(SubmitTaxExpt.ALLCERTSETTINGS);
					break;
				case "CHROME":
					JavaScriptclick(driver, SubmitTaxExpt.ALLCERTSETTINGS);
					break;
				case "IE":
					JavaScriptclick(driver, SubmitTaxExpt.ALLCERTSETTINGS);
					break;
				}
				waitforElement(180, SubmitTaxExpt.REJECTURL, driver);
				String URL = "https://" + Environment.toLowerCase()
						+ "ecwportal.vertexsmb.com/demo/reject.html?cert=<<CERTUUID>>&token=<<EDITTOKEN>>";
				clear(SubmitTaxExpt.REJECTURL);
				type(SubmitTaxExpt.REJECTURL, URL);
				click(SubmitTaxExpt.SAVE);
				waitforElement(180, PUL.CONFIGURELINK, driver);
				JavaScriptclick(driver, PUL.CONFIGURELINK);
				int pendcountbeforereject = getpendingcertificatescount();
				switch (DriverManager.Browser.toUpperCase()) {
				case "FIREFOX":
					click(CNP.PENDINGCERTIFICATES);
					break;
				case "CHROME":
					JavaScriptclick(driver, CNP.PENDINGCERTIFICATES);
					break;
				case "IE":
					JavaScriptclick(driver, CNP.PENDINGCERTIFICATES);
					break;
				}
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				RejectCertificates(CloudData.get_data("Exemptionstates"), CloudData.get_data("Reasonforreject"));
				waitforElement(180, PUL.CONFIGURELINK, driver);
				JavaScriptclick(driver, PUL.CONFIGURELINK);
				int pendcountafterreject = getpendingcertificatescount();
				assertTrue((pendcountafterreject == (pendcountbeforereject - 1)), "Fail, Customername not matched");
				Thread.sleep(3000);
				driver.get(rejectemail);
				break;
			case "ViewCretificatesDetails":
				switch (DriverManager.Browser.toUpperCase()) {
				case "FIREFOX":
					click(CNP.PENDINGCERTIFICATES);
					break;
				case "CHROME":
					JavaScriptclick(driver, CNP.PENDINGCERTIFICATES);
					break;
				case "IE":
					JavaScriptclick(driver, CNP.PENDINGCERTIFICATES);
					break;
				}
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				ViewCretificatesDetails(CloudData.get_data("Exemptionstates"), CloudData.get_data("Customernames"),
						CloudData.get_data("Customertype"), CloudData.get_data("Customercodeclass"),
						CloudData.get_data("Certificatescope"), CloudData.get_data("ExemptionType"),
						CloudData.get_data("Startdate"), CloudData.get_data("enddate"),
						CloudData.get_data("Exemptionstatesverify"));
				waitforElement(180, PUL.CONFIGURELINK, driver);
				JavaScriptclick(driver, PUL.CONFIGURELINK);
				break;
			case "ApproveCertificates":
				int pendcountbeforeapprove = getpendingcertificatescount();
				int activecountbeforeapprove = getactivecertificatescount();
				switch (DriverManager.Browser.toUpperCase()) {
				case "FIREFOX":
					click(CNP.PENDINGCERTIFICATES);
					break;
				case "CHROME":
					JavaScriptclick(driver, CNP.PENDINGCERTIFICATES);
					break;
				case "IE":
					JavaScriptclick(driver, CNP.PENDINGCERTIFICATES);
					break;
				}
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				ApproveCertificates(CloudData.get_data("Exemptionstates"));
				Thread.sleep(2000);
				waitforElement(180, PUL.CONFIGURELINK, driver);
				JavaScriptclick(driver, PUL.CONFIGURELINK);
				waitforElement(180, CNP.PENDINGCERTIFICATES, driver);
				int pendcountafterapprove = getpendingcertificatescount();
				assertTrue((pendcountafterapprove == (pendcountbeforeapprove - 1)), "Fail, Customername not matched");
				int activecountafterapprove = getactivecertificatescount();
				assertTrue((activecountafterapprove == (activecountbeforeapprove + 1)),
						"Fail, Customername not matched");
				break;
			case "VerifyApprovedCertificatesincustomers":
				VerifyApprovedCertificatesincustomers(CloudData.get_data("Exemptionstates"),
						CloudData.get_data("Customernames"), CloudData.get_data("Certificatescope"),
						CloudData.get_data("ExemptionReason"), CloudData.get_data("Startdate"),
						CloudData.get_data("enddate"));
				break;
			case "EditCertificates":
				EditCertificates(CloudData.get_data("Exemptionstates"), CloudData.get_data("Uploadimage"));
				break;
			case "RemoveCertificates":
				RemoveCertificates(CloudData.get_data("Exemptionstates"), CloudData.get_data("Certifcateenddate"));
				break;
			case "Viewcertificateimage":
				Viewcertificateimage(CloudData.get_data("Exemptionstates"));
				break;
			case "Deletecertificateimage":
				Deletecertificateimage(CloudData.get_data("Exemptionstates"));
				break;
			}
		}
	}

	public void EditandVerifyBuyerCode(String TestPath, String value) throws Exception {
		driver.getWindowHandle();
		CloudData.load_data(TestPath, value);
		PageFactory.initElements(driver, SubmitTaxExemption.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
		JavaScriptclick(driver, PUL.CONFIGURELINK);
		waitforElement(180, CNP.CUSTOMERS, driver);
		click(CNP.CUSTOMERS);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		driver.findElement(By.xpath("//td[contains(.,'SV04')]/../td[9]/div/a")).click();
		driver.findElement(By.xpath("//td[contains(.,'SV04')]/../td[9]/div/ul/li/a[contains(.,'Edit')]")).click();
		waitforElement(180, "//input[@id='customer-code']", driver);
		clear(driver.findElement(By.xpath("//input[@id='customer-code']")));
		type(driver.findElement(By.xpath("//input[@id='customer-code']")), "SV05");
		click(CE.SAVEANDCLOSE);
		for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
			driver.findElement(
					By.xpath("//span[contains(.,'Pennsylvania')]/../../../div/descendant::span[contains(.,'SV03')]"))
					.getText();
			assertTrue((VerifyObject(driver, "//span[contains(.,'" + CloudData.get_data("Jurisdictions")
					+ "')]/../../../div/descendant::span[contains(.,'" + CloudData.get_data("BuyerCode") + "')]")),
					"Fail, Buyer Code not Found with Edited Buyer Code from Cloud");
		}
	}

	public void BobsLogin(String TestPath, String value) throws Exception {
		SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
		CloudData.dataload(TestPath, value);
		PageFactory.initElements(driver, LoginPage.class);
		driver.get("http://localhost:4290/");
		if (VerifyObject(driver, SubmitTaxExpt.SIGNINAS)) {
			waitforElement(180, SubmitTaxExpt.SIGNINAS, driver);
			selectByValue(SubmitTaxExpt.SIGNINAS, CloudData.get_data("UserName"));
			clear(SubmitTaxExpt.PASSWORD);
			type(SubmitTaxExpt.PASSWORD, CloudData.get_data("Password"));
			click(SubmitTaxExpt.SIGNIN);
		}
	}

	public void OracleCloudIntegration(String CloudInstanceName, String OracleDataCenter, String ERPCloudUserName,
			String ERPCloudPassword, String SchedulerEnabled, String AccountsReceivable, String SourceofProductCodes,
			String SourceofProductClasses, String AccountsPayable, String SourceofPurchaseCodes,
			String SourceofPurchaseClasses, String BusinessUnitName, String BusinessUnitID, String CountriesofOperation,
			String AddBusinessUnit, String Adminverify, String InstanceType, String End_Date) throws Exception {
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		VertexSMBCustomers VSC = PageFactory.initElements(driver, VertexSMBCustomers.class);
		AdminUndoAdjustmentFile AUA = PageFactory.initElements(driver, AdminUndoAdjustmentFile.class);
		Robot rob = new Robot();

		if (Adminverify.equalsIgnoreCase("Yes")) {
			waitforElement(180, VSC.MANAGESMBCUSTLINK, driver);
			click(VSC.MANAGESMBCUSTLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, VSC.SMBCUSTNAMESB, driver);
			type(VSC.SMBCUSTNAMESB, VertexClientName);
			waitforElement(180, "//table/tbody/tr/td[contains(.,'" + VertexClientName + "')]", driver);
			driver.findElement(By
					.xpath("//table/tbody/tr[@class='ng-scope']/td/div/a[contains(text(),'" + VertexClientName + "')]"))
					.click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(60, AUA.CONNECTORS, driver);
			JavaScriptclick(driver, AUA.CONNECTORS);
			waitUntilElementInvisibe(180, "html[@class='nprogress-busy']", driver);
			waitforElement(60, PUL.ORACLECLOUD, driver);
		} else {
			waitforElement(180, PUL.SettingsHover, driver);
			JavaScriptMousehover(driver, PUL.SettingsHover);
			JavaScriptclick(driver, PUL.ADAPTERSLINK);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.TRUSTEDIDLABEL, driver);
			waitforElement(180, PUL.INTEGRATIONSETUP, driver);
			// An error has occured POP After clicking on View all connectors
			if (VerifyObject(driver, PUL.ANERRORHASOCCURED)) {
				click(PUL.OKERROR);
				waitUntilElementInvisibe(60, PUL.OKERROR_XPATH, driver);
				Thread.sleep(3000);
				click(PUL.DASHBOARDLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			}

			JavaScriptclick(driver, PUL.INTEGRATIONSETUP);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.ORACLEERPCLOUDINTEGRATIONTESXT, driver);
			// 20189 Capability to on-board more than one Oracle ERP cloud instance on a
			// single Vertex cloud instance

			JavaScriptclick(driver, PUL.ADDCONNECTIONSBUTTON);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			waitforElement(180, PUL.RETURNTOORACLEINSTANCES, driver);

		}
		if (!Adminverify.equalsIgnoreCase("Yes")) {

			// if oracle cloud integration Page not loaded Properly
			if ((!VerifyObject(driver, PUL.SUBMIT))) {

				click(PUL.DASHBOARDLINK);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(5000);
			}

			// Creating Oracle Cloud User
			// CloudInstanceName
			click(PUL.CLOUDINSTANCENAME);
			type(PUL.CLOUDINSTANCENAME, CloudInstanceName);

			// OracleDataCenter
			selectByValue(PUL.ORACLEDATACENTERSELECT, OracleDataCenter);
			Thread.sleep(2000);
			// InstanceType
			selectByValue(PUL.INSTANCETYPE, InstanceType);
			// ERPUserName
			type(PUL.CLOUDUSERNAMEINPUT, ERPCloudUserName);

			// ERPPassword
			type(PUL.CLOUDPASSWORDINPUT, ERPCloudPassword);

			// SchedulerEnabled
			if (SchedulerEnabled.equalsIgnoreCase("Yes")) {
				click(PUL.YES);
			}
			// Account Receivable
			if (AccountsReceivable.equalsIgnoreCase("Yes")) {
				click(PUL.ACCOUNTSRECEIVABLE);
			}
			// Account Payable
			if (AccountsReceivable.equalsIgnoreCase("Yes")) {
				click(PUL.ACCOUNTSPAYABLE);
			}
			// Business Unit Name
			type(PUL.BUSINESSUNITNAME1, BusinessUnitName);

			// Business Unit ID
			type(PUL.BUSINESSUNITID1, BusinessUnitID);
			// Countries of Operation
			if (!CountriesofOperation.isEmpty()) {
				if (CountriesofOperation == "US") {
					click(PUL.UNITEDSTATESINPUT);
				} else {
					click(PUL.CANADAINPUT);
				}
			}
			click(PUL.SUBMIT);
			waitforElement(60, PUL.REMINDER, driver);
			click(PUL.REMINDERSUBMIT);
			waitforElement(180, PUL.SUCCESSERP, driver);
			WebElement Downloaded = driver.findElement(By.xpath(
					"//a[contains(.,'Vertex_ERP_Cloud_Taxlink_" + CountriesofOperation + "_R2R_Configuration')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", Downloaded);
			Thread.sleep(10000);
			if (Browser.equalsIgnoreCase("IE")) {
				Thread.sleep(10000);
				rob.keyPress(KeyEvent.VK_ALT);
				Thread.sleep(1000);
				rob.keyPress(KeyEvent.VK_S);
				rob.keyRelease(KeyEvent.VK_ALT);
				rob.keyRelease(KeyEvent.VK_S);
			}
			/*
			 * if (Browser.equalsIgnoreCase("Chrome") || Browser.equalsIgnoreCase("IE")) {
			 * String path = directory.getCanonicalPath() +
			 * "\\" + "resources\\testdata\\actual" + "\\" + "RateFiles";
			 * MoveLatestDownloadFileFromOnedirtoAnother(path); Thread.sleep(10000);
			 * tempfile = new File(LatestDownloadedFile("RateFiles", true)); } if
			 * (Browser.equalsIgnoreCase("Firefox") || (Browser.equalsIgnoreCase("IE"))) {
			 * tempfile = new File(LatestDownloadedFile("RateFiles", false)); }
			 */
			if (AddBusinessUnit.equalsIgnoreCase("Yes")) {
				waitforElement(180, PUL.RETURNTOORACLEINSTANCES, driver);
				JavaScriptclick(driver, PUL.RETURNTOORACLEINSTANCES);
				waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
				Thread.sleep(5000);
				String CreatedInstanceName = "//table/tbody/tr/td/a[contains(.,'" + InstanceName + "')]";
				WebElement OrgFile = driver.findElement(By.xpath(CreatedInstanceName));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", OrgFile);
				Thread.sleep(5000);
				// waitforElement(180,PUL.ADDBUSINESSUNIT, driver);
				waitforElement(180, PUL.Remove, driver);
				JavaScriptclick(driver, PUL.Remove);
				waitforElement(120, PUL.WarningPopup, driver);
				type(PUL.ENDDATE, End_Date);
				sendkeysTab(PUL.ENDDATE);
				JavaScriptclick(driver, PUL.RemoveItem);
				Thread.sleep(3000);
				click(PUL.SUBMIT);
				Thread.sleep(2000);
				waitforElement(60, PUL.REMINDER, driver);
				click(PUL.REMINDERSUBMIT);
				waitforElement(180, PUL.SUCCESSERP, driver);
				Thread.sleep(5000);
				JavaScriptclick(driver, PUL.Expired);
				Thread.sleep(2000);
				String ExpiredBusinessunit = driver.findElement(By.xpath("//label[@for='business-unit-name']/../input"))
						.getAttribute("value");
				System.out.println(ExpiredBusinessunit);
				Thread.sleep(5000);
				JavaScriptclick(driver, PUL.Active);
				Thread.sleep(5000);
				JavaScriptclick(driver, PUL.ADDBUSINESSUNIT);
				type(PUL.BUSINESSUNITNAME1, BusinessUnitName);
				// Business Unit ID
				type(PUL.BUSINESSUNITID1, BusinessUnitID);

				// Countries of Operation
				if (!CountriesofOperation.isEmpty()) {
					if (CloudData.get_data("CountriesofOperation").equalsIgnoreCase("US")) {
						click(PUL.UNITEDSTATESINPUT);
					} else {
						click(PUL.CANADAINPUT);
					}
				}
				click(PUL.SUBMIT);
				waitforElement(60, PUL.REMINDER, driver);
				click(PUL.REMINDERSUBMIT);
				waitforElement(180, PUL.SUCCESSERP, driver);

			}
		}
	}

}
