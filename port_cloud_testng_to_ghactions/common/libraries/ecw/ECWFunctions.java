package common.libraries.ecw;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.Addseller;
import common.pages.ConfigurePage;
import common.pages.ExceptionsPage;
import common.pages.LoginPage;
import common.pages.ProfessionalUserLinks;
import common.pages.SubmitTaxExemption;
import common.pages.WizardSetting;
import common.utilities.CloudData;

public class ECWFunctions extends CommonFunctions {

	private WebDriver driver;

	public static String SELLERCODE;
	public String Roles;
	public static String ClientCode;
	public static String parentwindow;
	public static String rejectemail;
	public static String EditLink;
	public static String RenewLink;

	public ECWFunctions(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public ECWFunctions AddSeller(String SellerCode, String SELLERNAME, String CompanyCode, String PartitionName,
			String StrettAddress, String DivisionCode, String DepartmentCode, String State, String PostalCode,
			String Country) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		Thread.sleep(5000);
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
		return this;

	}

	public ECWFunctions EditSeller(String SellerCode, String SELLERNAME, String CompanyCode, String PartitionName,
			String StrettAddress, String DivisionCode, String DepartmentCode, String State, String PostalCode,
			String Country, String CreatedSeller) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		String sellername = "";
		try {
			Thread.sleep(5000);
			click(AS.SELLER);
			waitforElement(180, AS.ADDSELLER, driver);
			if (SellerName == null && CreatedSeller.equalsIgnoreCase("Test")) {
				sellername = CreatedSeller;
			} else {
				sellername = SellerName;
			}
			click(driver.findElement(By.xpath("//div[@class='rt-td']/div[contains(.,'" + sellername
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
			throw new RuntimeException("EditSeller Method : " + e);
		}
		return this;
	}

	public ECWFunctions DeleteSeller(String CreatedSeller) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		String sellername = "";
		try {
			Thread.sleep(5000);
			click(AS.SELLER);
			waitforElement(180, AS.ADDSELLER, driver);
			if (SellerName == null && !CreatedSeller.isEmpty()) {
				sellername = CreatedSeller;
			} else {
				sellername = SellerName;
			}
			click(driver.findElement(By.xpath("//div[@class='rt-td']/div[contains(.,'" + sellername
					+ "')]/../../div/div/span/div[contains(@data-testid,'delete-button')]")));
			waitforElement(180, AS.CONFIRMDELETE, driver);
			click(AS.CONFIRMDELETE);

			assertTrue(VerifyObject(driver,
					"//div[@class='rt-td']/div[contains(.,'\"+SellerName+\"')]/../../div/div/span/div[contains(@data-testid,'delete-button')]"),
					"Fail,TaxabilityDriverCode should not be Displayed in Grid;");

		} catch (Exception e) {
			throw new RuntimeException("DeleteSeller Method : " + e);
		}
		return this;
	}

	public ECWFunctions VerifySeller(String SellerCode, String SELLERNAME, String CompanyCode, String PartitionName,
			String DivisionCode, String DepartmentCode, String CreatedSellerName) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		String seller = "";
		try {
			click(AS.SELLER);
			waitforElement(180, AS.ADDSELLER, driver);

			assertTrue(VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'\" + SellerCode + \"')]"),
					"Fail, Seller Code not displyed in Grid;");

			if (SellerName == null && !CreatedSellerName.isEmpty()) {
				seller = CreatedSellerName;
			} else {
				seller = SellerName;
			}

			assertTrue(VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + seller + "')]"),
					"Fail, Seller Name not displyed in Grid;");

			assertTrue(VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + CompanyCode + "')]"),
					"Fail, Company Code not displyed in Grid;");

			assertTrue(VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + PartitionName + "')]"),
					"Fail, Partition Name not displyed in Grid;");

			assertTrue(VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + DivisionCode + "')]"),
					"Fail, DivisionCode not displyed in Grid;");

			assertTrue(VerifyObject(driver, "//div[@class='rt-td']/div[contains(.,'" + DepartmentCode + "')]"),
					"Fail, DepartmentCode not displyed in Grid;");

		} catch (Exception e) {
			throw new RuntimeException("VerifySeller Method : " + e);
		}
		return this;
	}

	public ECWFunctions AddProducts(String ProductName, String TaxabilityDriverCode, String ProductClass,
			String ExemptionType) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			Thread.sleep(5000);
			waitforElement(60, AS.PRODUCT, driver);
			JavaScriptclick(driver, AS.PRODUCT);
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
		} catch (Exception e) {
			throw new RuntimeException("AddProducts Method : " + e);
		}
		return this;
	}

	public ECWFunctions EditProducts(String ProductName, String TaxabilityDriverCode, String ProductClass,
			String ExemptionType, String EditProductCode) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			Thread.sleep(5000);
			click(AS.PRODUCT);
			Thread.sleep(5000);
			waitforElement(180, AS.ADDPRODUCT, driver);
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
			throw new RuntimeException("EditProducts Method : " + e);
		}
		return this;
	}

	public ECWFunctions VerifyProducts(String ProductName, String TaxabilityDriverCode, String ProductClass,
			String ExemptionType) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			Thread.sleep(5000);
			click(AS.PRODUCT);
			Thread.sleep(5000);
			waitforElement(180, AS.ADDPRODUCT, driver);
			assertTrue(VerifyObject(driver, "//span[contains(.,'" + ProductName + "')]"),
					"Fail, Product Name  not Displayed in Grid;");
			assertTrue(VerifyObject(driver, "//span[contains(.,'" + TaxabilityDriverCode + "')]"),
					"Fail,TaxabilityDriverCode not Displayed in Grid;");
		} catch (Exception e) {
			throw new RuntimeException("VerifyProducts Method : " + e);
		}
		return this;
	}

	public ECWFunctions DeleteProduct(String ProductName, String TaxabilityDriverCode, String ProductClass,
			String ExemptionType) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(180, AS.SETTINGSTAB, driver);
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			Thread.sleep(5000);
			click(AS.PRODUCT);
			waitforElement(180, AS.ADDPRODUCT, driver);
			click(driver.findElement(By.xpath("//div[@class='rt-td']/div[contains(.,'" + TaxabilityDriverCode
					+ "')]/../../div/div/span/div[contains(@data-testid,'delete-button')]")));
			waitforElement(180, AS.CONFIRMDELETE, driver);
			click(AS.CONFIRMDELETE);
			Thread.sleep(5000);
			assertTrue(VerifyObject(driver, "//span[contains(.,'" + TaxabilityDriverCode + "')]"),
					"Fail,TaxabilityDriverCode should not be Displayed in Grid;");
		} catch (Exception e) {
			throw new RuntimeException("DeleteProduct Method : " + e);
		}
		return this;
	}

	public ECWFunctions CreateOAuth(String SelectCredentailsUsage, String ClientID, String Description,
			String Withspaces) throws Exception {
		String Secret = "";
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(180, AS.SETTINGSTAB, driver);
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			waitforElement(180, AS.CLIENTCODE, driver);
			ClientCode = getText(AS.CLIENTCODE);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			click(AS.VIEWALLCREDENTIALS);
			waitforElement(180, AS.CREATENEWCREDENTIALS, driver);
			click(AS.CREATENEWCREDENTIALS);
			waitforElement(180, AS.CLIENTID, driver);
			selectByValue(AS.CREDENTIALSUSAGE, SelectCredentailsUsage);
			if (SelectCredentailsUsage.equalsIgnoreCase("O Series Connector")) {
				ECWOSeriesClientID = xLabelRND(ClientID.split(";"));
				type(AS.CLIENTID, ECWOSeriesClientID);
			} else {
				ECWClientID = xLabelRND(ClientID.split(";"));
				type(AS.CLIENTID, ECWClientID);
			}
			sendkeysTab(AS.CLIENTID);
			if (Withspaces.equalsIgnoreCase("YES")) {
				assertTrue(VerifyObject(driver, AS.CLIENTIDERROR), "Fail, Client Id should not have spaces in between");
				click(AS.CANCELBUTTON);
			} else {
				type(AS.CREDENTIALDESCRIPTION, Description);
				click(AS.SAVE);
				waitforElement(180, AS.NEWSECRET, driver);
				Secret = getText(AS.NEWSECRET);
				Secret = Secret.replaceAll(
						"Success! A new secret was created. Please keep this secret for your records as it will no longer be accessible after closing this notification.",
						"").trim();
				Secret = Secret.replace("New Secret:", "").trim();
				if (SelectCredentailsUsage.equalsIgnoreCase("O Series Connector")) {
					ECWOSeriesClientSecret = Secret;
				} else {
					ECWClientSecret = Secret;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("CreateOAuth Method : " + e);
		}
		return this;
	}

	public ECWFunctions ViewAllUsers(String FirstName, String LastName, String Email, String Phone, String Role)
			throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(180, AS.SETTINGSTAB, driver);
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			waitforElement(180, AS.VIEWALLUSERS, driver);
			click(AS.VIEWALLUSERS);
			waitforElement(180, AS.CREATENEWUSERBUTTON, driver);
			assertTrue(VerifyObject(driver, "//td[contains(.,'" + FirstName + "')]"),
					"Fail, FirstName not displayed in grid");
			assertTrue(VerifyObject(driver, "//td[contains(.,'" + LastName + "')]"),
					"Fail, LastName not displayed in grid");
			assertTrue(VerifyObject(driver, "//td[contains(.,'" + Email + "')]"), "Fail, Email not displayed in grid");
			assertTrue(VerifyObject(driver, "//td[contains(.,'" + Phone + "')]"), "Fail, Phone not displayed in grid");
			assertTrue(VerifyObject(driver, "//td[contains(.,'" + Role + "')]"), "Fail, Role not displayed in grid");
		} catch (Exception e) {
			throw new RuntimeException("ViewAllUsers Method : " + e);
		}
		return this;

	}

	public ECWFunctions VerifyExemptionCertificateWizard(String url, String authurl) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(180, AS.SETTINGSTAB, driver);
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			String URL = getText(AS.URL);
			String AUTHORIZATIONURL = getText(AS.AUTHORIZATIONURL);
			assertTrue(URL.equalsIgnoreCase(url),
					"Fail, URl not matched in EXEMPTION CERTIFICATE WIZARD section_Settings Page");
			assertTrue(AUTHORIZATIONURL.equalsIgnoreCase(authurl),
					"Fail, Authorizationurl not matched in EXEMPTION CERTIFICATE WIZARD section_Settings Page");
		} catch (Exception e) {
			throw new RuntimeException("VerifyExemptionCertificateWizard Method: " + e);
		}
		return this;
	}

	public ECWFunctions EditOAuth(String ClientID, String Description) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		String clinetid = "";
		try {
			waitforElement(180, AS.SETTINGSTAB, driver);
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			click(AS.VIEWALLCREDENTIALS);
			waitforElement(180, AS.CREATENEWCREDENTIALS, driver);
			if (ClientID.equalsIgnoreCase("Test") || ClientID.isEmpty()) {
				clinetid = ECWClientID;
			} else {
				clinetid = ClientID;
			}
			waitforElement(180, "//td[contains(.,'" + clinetid + "')]/../td/div/a", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + clinetid + "')]/../td/div/a")).click();
			waitforElement(180, "//td[contains(.,'" + clinetid + "')]/../td/div/ul/li/a[contains(.,'Edit')]", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + clinetid + "')]/../td/div/ul/li/a[contains(.,'Edit')]"))
					.click();
			waitforElement(180, AS.CREDENTIALDESCRIPTION, driver);
			type(AS.CREDENTIALDESCRIPTION, Description);
			click(AS.SAVE);

		} catch (Exception e) {
			throw new RuntimeException("EditOAuth Method: " + e);
		}
		return this;
	}

	public ECWFunctions DeleteOAuth(String ClientID) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		String clientid = "";
		try {
			waitforElement(180, AS.SETTINGSTAB, driver);
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			click(AS.VIEWALLCREDENTIALS);
			waitforElement(180, AS.CREATENEWCREDENTIALS, driver);
			if (ClientID.equalsIgnoreCase("Test") || ClientID.isEmpty()) {
				clientid = ECWClientID;
			} else {
				clientid = ClientID;
			}
			waitforElement(180, "//td[contains(.,'" + clientid + "')]/../td/div/a", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + clientid + "')]/../td/div/a")).click();
			waitforElement(180, "//td[contains(.,'" + clientid + "')]/../td/div/ul/li/a[contains(.,'Delete')]", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + clientid + "')]/../td/div/ul/li/a[contains(.,'Delete')]"))
					.click();
			waitforElement(180, AS.DELETECREDENTIALSANDSECRETS, driver);
			click(AS.DELETECREDENTIALSANDSECRETS);
			Thread.sleep(5000);
			assertTrue(VerifyObject(driver, "//td[contains(.,'" + clientid + "')]"),
					"Fail, OAuth Credentails are displaying in Grid after delete action");
		} catch (Exception e) {
			throw new RuntimeException("DeleteOAuth Method: " + e);
		}
		return this;
	}

	public ECWFunctions ManageOAuth(String ClientID, String ManageSecretOptions) throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		String clientid = "";
		try {
			waitforElement(180, AS.SETTINGSTAB, driver);
			click(AS.SETTINGSTAB);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			waitforElement(180, AS.VIEWALLCREDENTIALS, driver);
			click(AS.VIEWALLCREDENTIALS);
			waitforElement(180, AS.CREATENEWCREDENTIALS, driver);
			waitforElement(180, "//td[contains(.,'" + ECWClientID + "')]/../td/div/a", driver);
			driver.findElement(By.xpath("//td[contains(.,'" + ECWClientID + "')]/../td/div/a")).click();
			if (ClientID.equalsIgnoreCase("Test") || ClientID.isEmpty()) {
				clientid = ECWClientID;
			} else {
				clientid = ClientID;
			}
			waitforElement(180, "//td[contains(.,'" + clientid + "')]/../td/div/ul/li/a[contains(.,'Manage Secrets')]",
					driver);
			driver.findElement(
					By.xpath("//td[contains(.,'" + clientid + "')]/../td/div/ul/li/a[contains(.,'Manage Secrets')]"))
					.click();
			switch (ManageSecretOptions) {
			case "Add throw new Secret":
				waitforElement(180, AS.ADDNEWSECRET, driver);
				click(AS.ADDNEWSECRET);
				waitforElement(60, AS.NEWSECRET, driver);
				ECWClientSecret = getText(AS.NEWSECRET);
				ECWClientSecret = ECWClientSecret.replaceAll(
						"Success! A throw new secret was created. Please keep this secret for your records as it will no longer be accessible after closing this notification.",
						"").trim();
				ECWClientSecret = ECWClientSecret.replace("throw new Secret:", "").trim();
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
			throw new RuntimeException("ManageOAuth Method: " + e);
		}
		return this;
	}

	public ECWFunctions LaunchSite() throws Exception {
		Addseller AS = PageFactory.initElements(driver, Addseller.class);
		try {
			waitforElement(180, AS.ECWIZARDTAB, driver);
			click(AS.ECWIZARDTAB);
			parentwindow = driver.getWindowHandle();
			waitforElement(180, AS.LAUNCHSITE, driver);
			click(AS.LAUNCHSITE);
			Thread.sleep(5000);
			for (String winHandle : driver.getWindowHandles()) {
				if (!winHandle.equals(parentwindow)) {
					driver.switchTo().window(winHandle);
				}
			}
			waitforElement(180, AS.SELLER, driver);
			driver.close();
			driver.switchTo().window(parentwindow);

		} catch (Exception e) {
			throw new RuntimeException("LaunchSite Method: " + e);
		}
		return this;
	}

	public ECWFunctions WizardSettings(String FormPrecedence, String MultiJurisdictionFormPrecedence,
			String Form_Upload, String Certificate_Scope, String Product_Question, String Products_listing,
			String AdditionalDocumentation) throws Exception {
		WizardSetting wizardsettings = PageFactory.initElements(driver, WizardSetting.class);
		try {
			// Set the "Wizard" checkbox to checked and press the "Save User" button.
			waitforElement(180, wizardsettings.WIZARDSETTINGSTAB, driver);
			click(wizardsettings.WIZARDSETTINGSTAB);
			waitforElement(180, wizardsettings.SAVE, driver);
			click(driver.findElement(
					By.xpath("//div[@id='multiJurFormOpts']/descendant::label[contains(.,'" + FormPrecedence + "')]")));

			if (!VerifyObject(driver, "//h5[contains(.,'Multi-Jurisdiction Form Precedence')]/../div[1][contains(.,'"
					+ MultiJurisdictionFormPrecedence + "')]")) {
				click(driver.findElement(
						By.xpath("//h5[contains(.,'Multi-Jurisdiction Form Precedence')]/../div[contains(.,'"
								+ MultiJurisdictionFormPrecedence + "')]/i[@class='fa fa-chevron-up']")));
			}

			switch (Form_Upload.toUpperCase()) {
			case "CHECK":
				if (!(driver.findElement(By.xpath("//label[contains(.,'Allow forms to be uploaded')]/input"))
						.isSelected())) {
					click(driver.findElement(By.xpath("//label[contains(.,'Allow forms to be uploaded')]/input")));
				}
				break;
			case "UNCHECK":
				if (driver.findElement(By.xpath("//label[contains(.,'Allow forms to be uploaded')]/input"))
						.isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'Allow forms to be uploaded')]/input")));
				}
				break;
			}
			switch (Certificate_Scope) {
			case "CHECK":
				if (!driver.findElement(By.xpath("//label[contains(.,'Always create blanket certificates')]/input"))
						.isSelected()) {
					click(driver
							.findElement(By.xpath("//label[contains(.,'Always create blanket certificates')]/input")));
				}
				break;
			case "UNCHECK":
				if (driver.findElement(By.xpath("//label[contains(.,'Always create blanket certificates')]/input"))
						.isSelected()) {
					click(driver
							.findElement(By.xpath("//label[contains(.,'Always create blanket certificates')]/input")));
				}
				break;
			}
			switch (Product_Question) {
			case "CHECK":
				if (!driver.findElement(By.xpath("//label[contains(.,'Always ask product question')]/input"))
						.isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'Always ask product question')]/input")));
				}
				break;
			case "UNCHECK":
				if (driver.findElement(By.xpath("//label[contains(.,'Always ask product question')]/input"))
						.isSelected()) {
					click(driver.findElement(By.xpath("//label[contains(.,'Always ask product question')]/input")));
				}
				break;
			}
			switch (Products_listing) {
			case "CHECK":
				if (!driver.findElement(By.xpath(
						"//label[contains(.,'Use separate page(s) to display exempt products on a certificate')]/input"))
						.isSelected()) {
					click(driver.findElement(By.xpath(
							"//label[contains(.,'Use separate page(s) to display exempt products on a certificate')]/input")));
				}
				break;
			case "UNCHECK":
				if (driver.findElement(By.xpath(
						"//label[contains(.,'Use separate page(s) to display exempt products on a certificate')]/input"))
						.isSelected()) {
					click(driver.findElement(By.xpath(
							"//label[contains(.,'Use separate page(s) to display exempt products on a certificate')]/input")));
				}
				break;
			}
			switch (AdditionalDocumentation) {
			case "CHECK":
				if (!driver.findElement(By.xpath(
						"//label[contains(.,'Allow user to upload additional documentation when creating or uploading a certificate')]/input"))
						.isSelected()) {
					click(driver.findElement(By.xpath(
							"//label[contains(.,'Allow user to upload additional documentation when creating or uploading a certificate')]/input")));
				}
				break;
			case "UNCHECK":
				if (driver.findElement(By.xpath(
						"//label[contains(.,'Allow user to upload additional documentation when creating or uploading a certificate')]/input"))
						.isSelected()) {
					click(driver.findElement(By.xpath(
							"//label[contains(.,'Allow user to upload additional documentation when creating or uploading a certificate')]/input")));
				}
				break;
			}
			click(wizardsettings.SAVE);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return this;
	}

	public ECWFunctions EnterClietIDSecrets(String ClientID, String ClinentSecret, String BuyerCode,
			String SellerCode) {
		try {
			driver.navigate().refresh();
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			String Clientid = "", clientsecret = "";
			clear(SubmitTaxExpt.Seller);
			type(SubmitTaxExpt.Seller, SellerCode);
			clear(SubmitTaxExpt.BuyerCode);
			type(SubmitTaxExpt.BuyerCode, BuyerCode);
			if (ClientID.isEmpty() || ClinentSecret.isEmpty()) {
				Clientid = ECWClientID;
				clientsecret = ECWClientSecret;
			} else {
				Clientid = ClientID;
				clientsecret = ClinentSecret;
			}
			clear(SubmitTaxExpt.CLIENTID);
			type(SubmitTaxExpt.CLIENTID, Clientid);
			clear(SubmitTaxExpt.CLIENTSECRET);
			type(SubmitTaxExpt.CLIENTSECRET, clientsecret);
			click(SubmitTaxExpt.UpdateWizardPar);
			Thread.sleep(15000);
			waitforElement(180, SubmitTaxExpt.SUBMITTAXEXEMPTION, driver);
			click(SubmitTaxExpt.SUBMITTAXEXEMPTION);
			Thread.sleep(10000);
		} catch (Exception e) {
			throw new RuntimeException("EnterClietIDSecrets Method : " + e);
		}
		return this;
	}

	public ECWFunctions selectJurisidictions(String Jurisdictions) {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			String[] jur = Jurisdictions.split(",");
			waitandswitchToFrame(SubmitTaxExpt.iFrame, driver);
			waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);
			for (int i = 0; i < jur.length; i++) {
				waitforElement(180, "//label[contains(.,'" + jur[i] + "')]/input", driver);
				if (!driver.findElement(By.xpath("//label[contains(.,'" + jur[i] + "')]/input")).isSelected()) {
					JavaScriptclick(driver,
							driver.findElement(By.xpath("//label[contains(.,'" + jur[i] + "')]/input")));
				}
			}
			waitforElement(180, SubmitTaxExpt.NextButon, driver);
			JavaScriptclick(driver, SubmitTaxExpt.NextButon);
		} catch (Exception e) {
			throw new RuntimeException("selectJurisidictions Method : " + e);
		}
		return this;
	}

	public ECWFunctions selectLowerJurisidictions(String LowerJurisdictions) {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			String[] jur = LowerJurisdictions.split(",");
			for (int i = 0; i < jur.length; i++) {
				waitforElement(180, "//label[contains(.,'" + jur[i] + "')]/input", driver);
				if (!driver.findElement(By.xpath("//label[contains(.,'" + jur[i] + "')]/input")).isSelected()) {
					JavaScriptclick(driver,
							driver.findElement(By.xpath("//label[contains(.,'" + jur[i] + "')]/input")));
				}
			}
			waitforElement(180, SubmitTaxExpt.NextButon, driver);
			JavaScriptclick(driver, SubmitTaxExpt.NextButon);
		} catch (Exception e) {
			throw new RuntimeException("selectLowerJurisidictions Method : " + e);
		}
		return this;
	}

	public ECWFunctions selectReasonType(String ReasonType) {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			waitforElement(180, "(//label[contains(.,'" + ReasonType + "')]/input)", driver);
			JavaScriptclick(driver, driver.findElement(By.xpath("(//label[contains(.,'" + ReasonType + "')]/input)")));
			waitforElement(180, SubmitTaxExpt.NextButon, driver);
			JavaScriptclick(driver, SubmitTaxExpt.NextButon);
		} catch (Exception e) {
			throw new RuntimeException("selectReasonType Method : " + e);
		}
		return this;
	}

	public ECWFunctions EnterFormValues(String FormCheckBoxes, String FormRadioButton, String FormTextBoxes,
			String FormUploadFilePath, String SelectDropDown, String Datepick) throws Exception {
		int dta = 0, checkboxcount = 0, textboxcount = 0, radiocout = 0, Uploadcount = 0, selectboxcount = 0,
				datepickcount = 0;
		SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String[] Checkboxes = FormCheckBoxes.split(";");
		String[] RadioButtons = FormRadioButton.split(";");
		String[] Text = FormTextBoxes.split(";");
		String[] UploadForms = FormUploadFilePath.split(",");
		String datepick[] = Datepick.split(";");
		String[] Select = SelectDropDown.split(",");
		waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);
		try {
			waitforElement(180, SubmitTaxExpt.NextButon, driver);
			if (VerifyObject(driver, SubmitTaxExpt.RadioButton)) {
				String unifrom[] = RadioButtons[radiocout].split(",");
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
				// String select = Select[selectboxcount];
				String s[] = Select[selectboxcount].split(",");
				scrollDown(driver);
				for (int j1 = 0; j1 < s.length; j1++) {
					try {
						if (s[j1].equalsIgnoreCase("Default") || s[j1].isEmpty()) {

						} else {
							scrollDown(driver);
							JavaScriptclick(driver, driver.findElement(
									By.xpath("(//div[@class='form-group form-group-sm']/select)[" + (j1 + 1) + "]")));
							JavaScriptscrolltoView(driver, driver.findElement(
									By.xpath("(//div[@class='form-group form-group-sm']/select)[" + (j1 + 1) + "]")));
							selectByValue(
									driver.findElement(By.xpath(
											"(//div[@class='form-group form-group-sm']/select)[" + (j1 + 1) + "]")),
									s[j1].trim());
							waitforElement(180, SubmitTaxExpt.NextButon, driver);
						}
					} catch (Exception e) {
						selectByValue(driver.findElement(By.xpath("//tr[" + (j1 + 1) + "]/td/select")), s[j1].trim());
						waitforElement(180, SubmitTaxExpt.NextButon, driver);
					}
				}
				selectboxcount++;
			}
			if (VerifyObject(driver, SubmitTaxExpt.CheckBoxes)) {
				if (!Checkboxes[checkboxcount].trim().isEmpty()) {
					String justis[] = Checkboxes[checkboxcount].split(",");
					for (int j = 0; j < justis.length; j++) {
						waitforElement(10, "//label[contains(.,'" + justis[j] + "')]/input", driver);
						if (!driver.findElement(By.xpath("//label[contains(.,'" + justis[j] + "')]/input"))
								.isSelected()) {
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
				}
			}

			if (VerifyObject(driver, SubmitTaxExpt.TextBox)) {
				if (!Text[textboxcount].trim().isEmpty()) {
					String text[] = Text[textboxcount].split(",");
					for (int j = 0; j < text.length; j++) {
						if (VerifyObject(driver,
								"(//div[@class='form-group form-group-sm']/descendant::input[@type='text'])[" + (j + 1)
										+ "]")) {
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
						JavaScriptclick(driver, driver
								.findElement(By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
						clear(driver
								.findElement(By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
						type(driver.findElement(
								By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")), date[dta]);
						sendkeysTab(driver
								.findElement(By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
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
						JavaScriptclick(driver, driver
								.findElement(By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
						clear(driver
								.findElement(By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
						String curdate = xGetCurrentDate("MM/dd/yyyy");
						type(driver.findElement(
								By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")), curdate);
						sendkeysTab(driver
								.findElement(By.xpath("(//div/div[@class='DayPickerInput']/input)[" + (j + 1) + "]")));
						dta++;
					}
				}
				dta = 0;
			}
		} catch (Exception e) {
			throw new Exception("EnterFormValues  :" + e);
		}
		return this;
	}

	public ECWFunctions EnterBuyerInfo(String CompanyName, String StreetAddress, String City, String State,
			String PostalCode, String Country, String PhoneNumber, String CompanyEmail) {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			clear(SubmitTaxExpt.CompanyName);
			type(SubmitTaxExpt.CompanyName, CompanyName);
			getAttribute(SubmitTaxExpt.CompanyName, "value");

			clear(SubmitTaxExpt.StreetAddress);
			type(SubmitTaxExpt.StreetAddress, StreetAddress);
			getAttribute(SubmitTaxExpt.StreetAddress, "value");

			clear(SubmitTaxExpt.City);
			type(SubmitTaxExpt.City, City);
			getAttribute(SubmitTaxExpt.City, "value");

			selectByValue(SubmitTaxExpt.SelectState, State);
			clear(SubmitTaxExpt.PostalCode);
			type(SubmitTaxExpt.PostalCode, PostalCode);
			getAttribute(SubmitTaxExpt.PostalCode, "value");

			selectByValue(SubmitTaxExpt.Country, Country);
			if (!PhoneNumber.isEmpty()) {
				clear(SubmitTaxExpt.PhoneNumber);
				type(SubmitTaxExpt.PhoneNumber, PhoneNumber);
				getAttribute(SubmitTaxExpt.PhoneNumber, "value");

			}
			if (!CompanyEmail.isEmpty()) {
				clear(SubmitTaxExpt.Email);
				type(SubmitTaxExpt.Email, CompanyEmail);

				getAttribute(SubmitTaxExpt.Email, "value");

			}
			waitforElement(180, SubmitTaxExpt.NextButon, driver);
			JavaScriptclick(driver, SubmitTaxExpt.NextButon);
		} catch (Exception e) {
			throw new RuntimeException("EnterClietIDSecrets Method : " + e);
		}
		return this;

	}

	public ECWFunctions UploadAdditionalDocuments(String NumberOfDocumnets, String MultiJurisdictions,
			String FileName) {
		try {
			String AutoITpath = xAutoIT();
			String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			if (!MultiJurisdictions.isEmpty()) {
				String[] mj = MultiJurisdictions.split(",");
				if (VerifyObject(driver, SubmitTaxExpt.ADDITIONALDOCUMENTTITLEMULTI)) {
					for (int j = 0; j < mj.length; j++) {
						String[] sub = mj[j].split(":");
						selectByValue(driver.findElement(
								By.xpath("//table[@class='table table-striped']/descendant::div[contains(.,'" + sub[0]
										+ "')]/../../td/select")),
								sub[1]);
					}
					waitforElement(180, SubmitTaxExpt.NextButon, driver);
					JavaScriptclick(driver, SubmitTaxExpt.NextButon);
					for (int j1 = 0; j1 < mj.length; j1++) {
						String[] sub = mj[j1].split(":");
						String[] files = sub[3].split(",");
						for (int i = 0; i < Integer.parseInt(sub[2]); i++) {
							JavaScriptclick(driver, SubmitTaxExpt.ADDITIONALDOCUMENTS);
							Thread.sleep(7000);
							String additional1[] = files[i].split("\\.");
							transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, additional1[0],
									"." + additional1[1]);
							Thread.sleep(10000);
						}
						waitforElement(180, SubmitTaxExpt.NextButon, driver);
						JavaScriptclick(driver, SubmitTaxExpt.NextButon);
					}
				}
			} else {
				for (int j1 = 0; j1 < Integer.parseInt(NumberOfDocumnets); j1++) {
					JavaScriptclick(driver, SubmitTaxExpt.ADDITIONALDOCUMENTS);
					Thread.sleep(7000);
					String additional1[] = FileName.split("\\.");
					transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, additional1[0],
							"." + additional1[1]);
					Thread.sleep(10000);

					waitforElement(180, SubmitTaxExpt.NextButon, driver);
					JavaScriptclick(driver, SubmitTaxExpt.NextButon);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("UploadAdditionalDocuments Method : " + e);
		}
		return this;
	}

	public ECWFunctions EditCertifcate() {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			List<WebElement> edit = driver.findElements(By.xpath("//a[contains(.,'Edit')]"));
			waitforElement(60, SubmitTaxExpt.EDITCERTIFICATE, driver);
			driver.findElement(By.xpath("//td/div[" + (edit.size()) + "]/a[contains(.,'Edit')]")).click();
			waitandswitchToFrame(SubmitTaxExpt.iFrame, driver);
		} catch (Exception e) {
			throw new RuntimeException("EditCertifcate Method : " + e);
		}
		return this;
	}

	public ECWFunctions RenewCertifcate() {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			List<WebElement> renew = driver.findElements(By.xpath("//a[contains(.,'Renew')]"));
			waitforElement(60, SubmitTaxExpt.RENEWCERTIFICATE, driver);
			driver.findElement(By.xpath("//td/div[" + (renew.size()) + "]/a[contains(.,'Renew')]")).click();
			waitandswitchToFrame(SubmitTaxExpt.iFrame, driver);
		} catch (Exception e) {
			throw new RuntimeException("RenewCertifcate Method : " + e);
		}
		return this;
	}

	public ECWFunctions MutlipleEditCertifcate(String formname) throws InterruptedException {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			driver.findElement(By.xpath("//div[contains(.,'" + formname + "')]/a[contains(.,'Edit')]")).click();
			waitandswitchToFrame(SubmitTaxExpt.iFrame, driver);
		} catch (Exception e) {
			throw new RuntimeException("MutlipleEditCertifcate : " + e);
		}
		return this;
	}

	public ECWFunctions MutlipleRenewCertifcate(String formname) {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			driver.findElement(By.xpath("//div[contains(.,'" + formname + "')]/a[contains(.,'Renew')]")).click();
			waitandswitchToFrame(SubmitTaxExpt.iFrame, driver);
		} catch (Exception e) {
			throw new RuntimeException("MutlipleRenewCertifcate : " + e);
		}
		return this;
	}

	public ECWFunctions EditCertifcateNew() {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			List<WebElement> edit = driver.findElements(By.xpath("//button[@name='edit-link']"));
			waitforElement(60, SubmitTaxExpt.EDITCERTIFICATE, driver);
			EditLink = getText(SubmitTaxExpt.EDITLINK);
			driver.findElement(By.xpath("(//button[@name='edit-link'])[" + edit.size() + "]")).click();
		} catch (Exception e) {
			throw new RuntimeException("EditCertifcateNew Method : " + e);
		}
		return this;
	}

	public ECWFunctions RenewCertifcateNew() {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			List<WebElement> renew = driver.findElements(By.xpath("//button[@name='renew-link']"));
			waitforElement(60, SubmitTaxExpt.RENEWCERTIFICATE, driver);
			RenewLink = getText(SubmitTaxExpt.RENEWLINK);
			driver.findElement(By.xpath("(//button[@name='renew-link'])[" + renew.size() + "]")).click();
		} catch (Exception e) {
			throw new RuntimeException("RenewCertifcateNew Method : " + e);
		}
		return this;
	}

	public ECWFunctions PreviewForm() {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
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
			JavaScriptclick(driver, SubmitTaxExpt.Submit);
		} catch (Exception e) {
			throw new RuntimeException("PreviewForm Method : " + e);
		}
		return this;
	}

	public ECWFunctions SignatureInfo(String Name, String Title, String Date, String SignatureFileName) {
		try {
			String curdate = "";
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
			waitforElement(180, SubmitTaxExpt.Name, driver);
			String AutoITpath = xAutoIT();
			type(SubmitTaxExpt.Name, Name);
			type(SubmitTaxExpt.Title, Title);
			String date[] = Date.split("+");
			switch (date[0].toUpperCase()) {
			case "TODAY":
				if (date[1].isEmpty()) {
					curdate = xGetCurrentDate("MM/dd/yyyy");
				} else {
					curdate = xGetCurrentDate("MM/dd/yyyy", Integer.parseInt(date[1]));
				}
			}
			JavaScriptclick(driver, SubmitTaxExpt.DATEPICKER);
			clear(SubmitTaxExpt.DATEPICKER);
			type(SubmitTaxExpt.DATEPICKER, curdate);
			sendkeysTab(SubmitTaxExpt.DATEPICKER);
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
			String image[] = SignatureFileName.split("\\.");
			transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, image[0], "." + image[1]);
			Thread.sleep(10000);
			waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);
			if (VerifyObject(driver, SubmitTaxExpt.Submit)) {
				JavaScriptclick(driver, SubmitTaxExpt.Submit);
			}

			if (VerifyObject(driver, SubmitTaxExpt.INVALIDDATEFORMATE)
					|| VerifyObject(driver, SubmitTaxExpt.DATEGREATER)) {
				JavaScriptclick(driver, SubmitTaxExpt.DATEPICKER);
				clear(SubmitTaxExpt.DATEPICKER);
				curdate = xGetCurrentDate("MM/dd/yyyy");
				type(SubmitTaxExpt.DATEPICKER, curdate);
				sendkeysTab(SubmitTaxExpt.DATEPICKER);
				JavaScriptclick(driver, SubmitTaxExpt.Submit);
			}
			waitUntilElementInvisibe(180, SubmitTaxExpt.CloudIcon, driver);

			List<WebElement> formlist = driver.findElements(By.xpath("//li/a"));
			for (int j = 1; j < formlist.size(); j++) {
				if (VerifyObject(driver, driver.findElement(By.xpath("(//li/a)[" + j + "]"))))
					JavaScriptclick(driver, driver.findElement(By.xpath("(//li/a)[" + j + "]")));
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
			waitforElement(180, SubmitTaxExpt.DoneButton, driver);
			JavaScriptclick(driver, SubmitTaxExpt.DoneButton);
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			throw new RuntimeException("SignatureInfo Method : " + e);
		}
		return this;
	}

	public ECWFunctions VersionCheck(String DataUpdateVersion, String ECWVersion) throws Exception {
		SubmitTaxExemption AS = PageFactory.initElements(driver, SubmitTaxExemption.class);
		try {
			waitforElement(180, AS.VERSIONTAB, driver);
			click(AS.VERSIONTAB);
			waitforElement(180, AS.VERSIONS, driver);
			assertTrue(
					VerifyObject(driver,
							"//h3[contains(.,'Version')]/../../div[2]/div[contains(.,'" + ECWVersion + "')]"),
					"Fail, ECWVersion mismacth");
			assertTrue(
					VerifyObject(driver,
							"//h3[contains(.,'Version')]/../../div[2]/div[contains(.,'" + DataUpdateVersion + "')]"),
					"Fail, DataUpdateVersion mistach");
		} catch (Exception e) {
			throw new RuntimeException("VersionCheck Method: " + e);
		}
		return this;
	}

	public ECWFunctions ApproveCertificates(String Exemptionstate) throws Exception {
		try {
			String states[] = Exemptionstate.split(",");
			driver.findElement(By.xpath("//td[4]/span[contains(.,'" + states[0].toUpperCase()
					+ "')]/../../td[@ng-show='data.isActive']/div/a")).click();
			JurisdictionType = driver
					.findElement(By.xpath("//td[4]/span[contains(.,'" + states[0].toUpperCase() + "')]/../../td[5]"))
					.getText();
			JavaScriptclick(driver, driver.findElement(By.xpath("//span[contains(.,'" + states[0].toUpperCase()
					+ "')]/../../descendant::ul/li/a[contains(.,'Approve')]")));
			if (VerifyObject(driver, "//td[4]/span[contains(.,'" + states[0].toUpperCase()
					+ "')]/../../td[contains(.,'Pending With Error')]")) {
				RevalidateandApprove(Exemptionstate);
			}
		} catch (Exception e) {
			throw new RuntimeException("ApproveCertificates Method: " + e);
		}
		return this;
	}

	public ECWFunctions RejectCertificates(String Exemptionstate, String Reasonforreject) throws Exception {
		try {
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			String states[] = Exemptionstate.split(",");
			driver.findElement(By.xpath("//td[4]/span[contains(.,'" + states[0].toUpperCase()
					+ "')]/../../td[@ng-show='data.isActive']/div/a")).click();
			waitforElement(180, "//span[contains(.,'" + states[0].toUpperCase()
					+ "')]/../../descendant::ul/li/a[contains(.,'Reject')]", driver);
			JavaScriptclick(driver, driver.findElement(By.xpath("//span[contains(.,'" + states[0].toUpperCase()
					+ "')]/../../descendant::ul/li/a[contains(.,'Reject')]")));
			waitforElement(180, CNP.REJECTREASONTEXTBOX, driver);
			type(CNP.REJECTREASONTEXTBOX, Reasonforreject);
			JavaScriptclick(driver, CNP.SENDREJECTION);
			if (rejectemail.isEmpty()) {
				assertTrue(rejectemail.isEmpty() == false, "Fail, Approve status not displayed");
			}
		} catch (Exception e) {
			throw new RuntimeException("RejectCertificates Method: " + e);
		}
		return this;

	}

	public ECWFunctions ViewCretificatesDetails(String Exemptionstate, String Customername, String Customertype,
			String Customercodeclass, String Certificatescope, String ExemptionType, String Startdate, String enddate,
			String exemptionstatesverify) throws Exception {
		try {
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			String states[] = Exemptionstate.split(",");
			driver.findElement(By.xpath("//td[4]/span[contains(.,'" + states[0].toUpperCase()
					+ "')]/../../td[@ng-show='data.isActive']/div/a")).click();
			waitforElement(180, "//span[contains(.,'" + states[0].toUpperCase()
					+ "')]/../../descendant::ul/li/a[contains(.,'View Details')]", driver);
			JavaScriptclick(driver, driver.findElement(By.xpath("//span[contains(.,'" + states[0].toUpperCase()
					+ "')]/../../descendant::ul/li/a[contains(.,'View Details')]")));
			waitforElement(180, CNP.CUSTOMERNAMEVALUE, driver);
			assertTrue(CNP.CUSTOMERNAMEVALUE.getText().trim().equalsIgnoreCase(Customername),
					"Fail, Customername not matched");
			assertTrue(CNP.CUSTOMERTYPE.getText().trim().equalsIgnoreCase(Customertype),
					"Fail, Customertype not matched");
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
		} catch (Exception e) {
			throw new RuntimeException("ViewCretificatesDetails Method: " + e);
		}
		return this;
	}

	public ECWFunctions VerifyApprovedCertificatesincustomers(String Exemptionstate, String Customernames,
			String CertificateScope, String Reason, String StartDate, String EndDate) {
		try {
			ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			click(PUL.CONFIGURELINK);
			waitforElement(180, CNP.CUSTOMERS, driver);
			click(CNP.CUSTOMERS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			driver.findElement(
					By.xpath("//td[contains(.,'" + Customernames + "')]/../td[6][contains(.,'View')]/span/a")).click();
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			String states[] = Exemptionstate.split(",");
			Thread.sleep(2000);
			String ApprovedJurisdictionType = driver
					.findElement(By.xpath("//td[4]/span[contains(.,'" + states[0].toUpperCase() + "')]/../../td[5]"))
					.getText();
			assertTrue((JurisdictionType.equalsIgnoreCase(ApprovedJurisdictionType)),
					"Fail, Jurisdiction Type in Pending and Active certificates mismatched");
			assertTrue(
					VerifyObject(driver,
							"//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[contains(.,'Approved')]"),
					"Fail, Approve status not displayed");
			String CertScope = driver
					.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[7]")).getText();
			assertTrue(CertScope.equalsIgnoreCase(CertificateScope), "Fail, Certificate Scope not matched with ECW");
			String reason = driver
					.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[8]")).getText();
			assertTrue(reason.equalsIgnoreCase(Reason), "Fail, Reason not matched with ECW");
			String startdate = driver
					.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[9]")).getText();
			assertTrue(startdate.equalsIgnoreCase(StartDate), "Fail, startdate not matched with ECW");
			String enddate = driver
					.findElement(By.xpath("//tr[1]/td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[10]"))
					.getText();
			assertTrue(enddate.equalsIgnoreCase(EndDate), "Fail, enddate not matched with ECW");

		} catch (Exception e) {
			throw new RuntimeException("VerifyApprovedCertificatesincustomers Method: " + e);
		}
		return this;
	}

	public ECWFunctions EditCertificates(String Exemptionstate, String Uploadimage) throws Exception {
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String AutoITpath = xAutoIT();
		try {
			ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			JavaScriptclick(driver, PUL.CONFIGURELINK);
			waitforElement(180, CNP.ActiveCertificates, driver);
			JavaScriptclick(driver, CNP.ActiveCertificates);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			String states[] = Exemptionstate.split(",");
			JavaScriptclick(driver, driver
					.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/a")));
			waitforElement(180,
					"//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/ul/li/a[contains(.,'Edit')]",
					driver);
			JavaScriptclick(driver, driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase()
					+ "')]/../td[12]/div/ul/li/a[contains(.,'Edit')]")));
			JavaScriptclick(driver, CNP.BROWSE);
			transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, Uploadimage, ".jpg");
			waitforElement(180, "CNP.SAVEJRISDICTION", driver);
			JavaScriptclick(driver, CNP.SAVEJRISDICTION);
		} catch (Exception e) {
			throw new RuntimeException("EditCertificates Method: " + e);
		}
		return this;
	}

	public ECWFunctions RemoveCertificates(String Exemptionstate, String Certifcateenddate) throws Exception {
		try {
			ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			JavaScriptclick(driver, PUL.CONFIGURELINK);
			waitforElement(180, CNP.ActiveCertificates, driver);
			JavaScriptclick(driver, CNP.ActiveCertificates);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			String states[] = Exemptionstate.split(",");
			JavaScriptclick(driver, driver
					.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/a")));
			waitforElement(180, "//td[4][contains(.,'" + states[0].toUpperCase()
					+ "')]/../td[12]/div/ul/li/a[contains(.,'Remove')]", driver);
			JavaScriptclick(driver, driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase()
					+ "')]/../td[12]/div/ul/li/a[contains(.,'Remove')]")));
			waitforElement(180, CNP.REMOVEITEM, driver);
			clear(CNP.CERTIFCATEENDDATE);
			type(CNP.CERTIFCATEENDDATE, Certifcateenddate);
			JavaScriptclick(driver, CNP.REMOVEITEM);
			String str = driver
					.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[10]")).getText()
					.trim();
			assertTrue(str.equalsIgnoreCase(Certifcateenddate), "Fail, Certifcate End Date not displayed");
		} catch (Exception e) {
			throw new RuntimeException("RemoveCertificates Method: " + e);
		}
		return this;
	}

	public ECWFunctions Viewcertificateimage(String Exemptionstate) throws Exception {
		try {
			ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			JavaScriptclick(driver, PUL.CONFIGURELINK);
			waitforElement(180, CNP.ActiveCertificates, driver);
			JavaScriptclick(driver, CNP.ActiveCertificates);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			String states[] = Exemptionstate.split(",");
			JavaScriptclick(driver, driver
					.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/a")));
			waitforElement(180, "//td[4][contains(.,'" + states[0].toUpperCase()
					+ "')]/../td[12]/div/ul/li/a[contains(.,'View Image')]", driver);
			JavaScriptclick(driver, driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase()
					+ "')]/../td[12]/div/ul/li/a[contains(.,'View Image')]")));
		} catch (Exception e) {
			throw new RuntimeException("Viewcertificateimage Method: " + e);
		}
		return this;
	}

	public ECWFunctions Deletecertificateimage(String Exemptionstate) throws Exception {
		try {
			ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			JavaScriptclick(driver, PUL.CONFIGURELINK);
			waitforElement(180, CNP.ActiveCertificates, driver);
			JavaScriptclick(driver, CNP.ActiveCertificates);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			String states[] = Exemptionstate.split(",");
			JavaScriptclick(driver, driver
					.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/a")));
			waitforElement(180, "//td[4][contains(.,'" + states[0].toUpperCase()
					+ "')]/../td[12]/div/ul/li/a[contains(.,'Delete Image')]", driver);
			JavaScriptclick(driver, driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase()
					+ "')]/../td[12]/div/ul/li/a[contains(.,'Delete Image')]")));
		} catch (Exception e) {
			throw new RuntimeException("Deletecertificateimage Method: " + e);
		}
		return this;
	}

	public ECWFunctions RevalidateandApprove(String Exemptionstate) throws Exception {
		try {
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			String states[] = Exemptionstate.split(",");
			JavaScriptclick(driver, driver
					.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase() + "')]/../td[12]/div/a")));
			waitforElement(180, "//td[4][contains(.,'" + states[0].toUpperCase()
					+ "')]/../td[12]/div/ul/li/a[contains(.,'View Details')]", driver);
			JavaScriptclick(driver, driver.findElement(By.xpath("//td[4][contains(.,'" + states[0].toUpperCase()
					+ "')]/../td[12]/div/ul/li/a[contains(.,'View Details')]")));
			waitforElement(180, CNP.REVALIDATE, driver);
			JavaScriptclick(driver, CNP.REVALIDATE);
			waitforElement(180, CNP.APPROVEBUTTON, driver);
			JavaScriptclick(driver, CNP.APPROVEBUTTON);

		} catch (Exception e) {
			throw new RuntimeException("RevalidateandApprove Method: " + e);
		}
		return this;
	}

	public int getpendingcertificatescount() throws Exception {
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

	public ECWFunctions EditandVerifyBuyerCode(String BuyerCode, String EditBuyerCode) throws Exception {
		try {
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
			ExceptionsPage CE = PageFactory.initElements(driver, ExceptionsPage.class);
			JavaScriptclick(driver, PUL.CONFIGURELINK);
			waitforElement(180, CNP.CUSTOMERS, driver);
			click(CNP.CUSTOMERS);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			driver.findElement(By.xpath("//td[contains(.,'" + BuyerCode + "')]/../td[9]/div/a")).click();
			driver.findElement(
					By.xpath("//td[contains(.,'" + BuyerCode + "')]/../td[9]/div/ul/li/a[contains(.,'Edit')]")).click();
			waitforElement(180, "//input[@id='customer-code']", driver);
			clear(driver.findElement(By.xpath("//input[@id='customer-code']")));
			type(driver.findElement(By.xpath("//input[@id='customer-code']")), EditBuyerCode);
			click(CE.SAVEANDCLOSE);
			assertTrue(VerifyObject(driver, "//span[contains(.,'" + EditBuyerCode + "')]"),
					"Fail, Buyer Code not Found with Edited Buyer Code from Cloud");
		} catch (Exception e) {
			throw new RuntimeException("EditandVerifyBuyerCode Method: " + e);
		}
		return this;
	}

	public ECWFunctions NewBobsLogin(String UserName, String Password) throws Exception {
		try {
			SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
			driver.get("http://localhost:5001/");
			if (VerifyObject(driver, SubmitTaxExpt.SIGNINAS)) {
				waitforElement(180, SubmitTaxExpt.SIGNINAS, driver);
				selectByValue(SubmitTaxExpt.SIGNINAS, UserName);
				clear(SubmitTaxExpt.PASSWORD);
				type(SubmitTaxExpt.PASSWORD, Password);
				click(SubmitTaxExpt.SIGNIN);
			}
		} catch (Exception e) {
			throw new RuntimeException("NewBobsLogin Method: " + e);
		}
		return this;
	}

	public static void SetEnvVariables(String Environment) throws Exception {
		try {
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

			Runtime.getRuntime().exec("cmd /c start setx BDB_AUTH_ENDPOINT https://" + Environment
					+ "auth.vertexsmb.com/identity/connect/token");

			Thread.sleep(5000);

			Runtime.getRuntime().exec(
					"cmd /c start setx BDB_WIZARD_BASE_ADDRESS https://" + Environment + "ecwportal.vertexsmb.com");

			Thread.sleep(5000);
		} catch (Exception e) {
			throw new RuntimeException("SetEnvVariables Method: " + e);
		}

	}

	public ECWFunctions SetEnvVariables(String Environment, String ECWClientID1, String ECWClientSecret1,
			String SELLERCODE1, String ClientCode1) throws Exception {
		try {
			Runtime.getRuntime().exec("cmd /c start setx BDB_PORT 5001");

			Thread.sleep(5000);

			Runtime.getRuntime().exec("cmd /c start setx BDB_SHOW_TOKEN_TIMEOUT False");

			Thread.sleep(5000);

			if (ECWClientID1 == "") {
				Runtime.getRuntime().exec("cmd /c start setx BDB_CLIENT_ID " + ECWClientID + "");
			} else {
				Runtime.getRuntime().exec("cmd /c start setx BDB_CLIENT_ID " + ECWClientID1 + "");
			}

			Thread.sleep(5000);

			if (ECWClientSecret1 == "") {
				Runtime.getRuntime().exec("cmd /c start setx BDB_CLIENT_SECRET " + ECWClientSecret + "");
			} else {
				Runtime.getRuntime().exec("cmd /c start setx BDB_CLIENT_SECRET " + ECWClientSecret1 + "");
			}

			Thread.sleep(5000);

			if (SELLERCODE1 == "") {
				Runtime.getRuntime().exec("cmd /c start setx BDB_SELLER_CODE " + SELLERCODE + "");
			} else {
				Runtime.getRuntime().exec("cmd /c start setx BDB_SELLER_CODE " + SELLERCODE1 + "");
			}

			Thread.sleep(5000);

			if (ClientCode1 == "") {
				Runtime.getRuntime().exec("cmd /c start setx BDB_CLIENT_CODE " + ClientCode + "");
			} else {
				Runtime.getRuntime().exec("cmd /c start setx BDB_CLIENT_CODE " + ClientCode1 + "");
			}

			Thread.sleep(5000);

			Runtime.getRuntime().exec("cmd /c start setx BDB_AUTH_ENDPOINT https://" + Environment
					+ "auth.vertexsmb.com/identity/connect/token");

			Thread.sleep(5000);

			Runtime.getRuntime().exec(
					"cmd /c start setx BDB_WIZARD_BASE_ADDRESS https://" + Environment + "ecwportal.vertexsmb.com");

			Thread.sleep(5000);
		} catch (Exception e) {
			throw new RuntimeException("SetEnvVariables Method: " + e);
		}
		return this;

	}

	public ECWFunctions StartNPM() throws IOException, InterruptedException {

		File directory = new File(".//");

		String MainFolder = directory.getCanonicalPath() + "\\";

		Runtime.getRuntime().exec("wscript.exe " + MainFolder + "\\ECW.vbs ");

		Thread.sleep(50000);
		return this;

	}

	public void BobsLogout() throws Exception {
		SubmitTaxExemption SubmitTaxExpt = PageFactory.initElements(driver, SubmitTaxExemption.class);
		if (VerifyObject(driver, SubmitTaxExpt.LOGOUT)) {
			click(SubmitTaxExpt.LOGOUT);
		}
	}

	public void ECWLogin(String TestPath, String value) throws Exception {
		LoginPage LP = PageFactory.initElements(driver, LoginPage.class);
		CloudData.dataload(TestPath, value);
		Thread.sleep(5000);
		if (VerifyObject(driver, LP.USERNAME)) {
			if (VertexUserName != null) {
				type(LP.USERNAME, VertexUserName);
			} else {
				type(LP.USERNAME, CloudData.get_data("UserName"));
			}
			click(LP.PASSWORD);
			clear(LP.PASSWORD);
			type(LP.PASSWORD, CloudData.get_data("Password"));
			click(LP.ECLogin);
		}
	}

}
