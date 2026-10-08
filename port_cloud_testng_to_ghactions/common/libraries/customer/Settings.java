package common.libraries.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.libraries.usercreation.UserRegistration;
import common.pages.ConfigurePage;
import common.pages.FreeUserLinks;
import common.pages.ProfessionalUserLinks;

public class Settings extends CommonFunctions {

	private WebDriver driver;

	public Settings(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	UserRegistration userregistration = PageFactory.initElements(driver, UserRegistration.class);

	public void outhCreation(String payrollcalc, String Payrolladdcleansing, String ClientIDval, String Clientdes,
			String Clientname) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, FUL.MyOAuthCredentials, driver);
		JavaScriptscrolltoView(driver, FUL.settingsviewallcredentialslink);
		verifyOuthscreen();
		waitforElement(180, FUL.settingsviewallcredentialslink, driver);
		String beforecount[] = driver.findElement(By.xpath("//strong[contains(.,'Total Credentials:')]/../../p[2]"))
				.getText().split(":");
		System.out.println(beforecount[1]);
		JavaScriptclick(driver, FUL.settingsviewallcredentialslink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		click(PUL.CreatNewCredential);
		waitforElement(180, PUL.AddCredential, driver);
		if (!payrollcalc.isEmpty()) {
			selectByValue(PUL.selectcredentialusage, payrollcalc);
		}
		if (!Payrolladdcleansing.isEmpty()) {
			selectByValue(PUL.selectcredentialusage, Payrolladdcleansing);
		}
		type(PUL.ClientIdinput, ClientIDval);
		type(PUL.CredentialDescription, Clientdes);
		click(PUL.savebutton);
		waitforText(180, PUL.Successouthcreation, driver);
		Thread.sleep(5000);
		assertTrue(driver.findElement(By.xpath("//tr/td[1][contains(.,'" + payrollcalc + "')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[2][contains(.,'" + ClientIDval + "')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[3][contains(.,'" + Clientdes + "')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[4][contains(.,'" + Clientname + "')]")).isDisplayed());
		click(PUL.RIGHTReturntoSettings);
		waitforElement(180, FUL.MyOAuthCredentials, driver);
		JavaScriptscrolltoView(driver, FUL.settingsviewallcredentialslink);
		String[] aftercount = driver.findElement(By.xpath("//strong[contains(.,'Total Credentials:')]/../../p[2]"))
				.getText().split(":");
		String a = aftercount[1].toString();
		String b = beforecount[1].toString();
		assertTrue(a != b);

	}

	public void editOuthCreation(String Clientdes, String Clientname, String newclientdes) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, FUL.MyOAuthCredentials, driver);
		JavaScriptscrolltoView(driver, FUL.settingsviewallcredentialslink);
		waitforElement(180, FUL.settingsviewallcredentialslink, driver);
		JavaScriptclick(driver, FUL.settingsviewallcredentialslink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		WebElement actclick = driver.findElement(By.xpath("//td[3][contains(.,'" + Clientdes + "')]/../td[5]/div/a"));
		JavaScriptclick(driver, actclick);
		Thread.sleep(2000);
		driver.findElement(By.linkText("Edit")).click();
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		waitforElement(180, PUL.CredentialDescription, driver);
		Thread.sleep(2000);
		clear(PUL.CredentialDescription);
		type(PUL.CredentialDescription, newclientdes);
		click(PUL.savebutton);
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		Thread.sleep(5000);
		driver.navigate().refresh();
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		assertTrue(driver.findElement(By.xpath("//tr/td[3][contains(.,'" + newclientdes + "')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[4][contains(.,'" + Clientname + "')]")).isDisplayed());

	}

	public void deleteOuthCreation(String Clientname, String ClientID) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, FUL.MyOAuthCredentials, driver);
		JavaScriptscrolltoView(driver, FUL.settingsviewallcredentialslink);
		waitforElement(180, FUL.settingsviewallcredentialslink, driver);
		JavaScriptclick(driver, FUL.settingsviewallcredentialslink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		WebElement actclick = driver.findElement(By.xpath("//td[2][contains(.,'" + ClientID + "')]/../td[5]/div/a"));
		JavaScriptclick(driver, actclick);
		Thread.sleep(2000);
		driver.findElement(By.linkText("Delete")).click();
		waitforElement(180, PUL.deletewarning, driver);
		assertTrue(PUL.deletewarning.isDisplayed());
		assertTrue(PUL.deleteouthtext.isDisplayed());
		assertTrue(PUL.deleteouthlink.isDisplayed());
		assertTrue(PUL.cancellink.isDisplayed());
		click(PUL.cancellink);
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		click(PUL.actionauth);
		Thread.sleep(2000);
		waitforElement(180, PUL.actionauthDelete, driver);
		click(PUL.actionauthDelete);
		waitforElement(180, PUL.deletewarning, driver);
		click(PUL.deleteouthlink);
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		// assertTrue(!(driver.findElement(By.xpath("//td[2][contains(.,'"+ClientID+"')]")).isDisplayed()));

	}

	public void verifyOuthscreen() throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, FUL.MyOAuthCredentials, driver);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		JavaScriptscrolltoView(driver, FUL.settingsviewallcredentialslink);
		waitforElement(180, FUL.settingsviewallcredentialslink, driver);
		assertTrue(FUL.MyOAuthCredentials.isDisplayed());
		assertTrue(PUL.outhcredentialstext.isDisplayed());
		assertTrue(PUL.TotalCredentialstext.isDisplayed());
	}

	public void Managesecrets(String ClientID, String addsecret, String deletesecret) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, FUL.MyOAuthCredentials, driver);
		JavaScriptscrolltoView(driver, FUL.settingsviewallcredentialslink);
		waitforElement(180, FUL.settingsviewallcredentialslink, driver);
		JavaScriptclick(driver, FUL.settingsviewallcredentialslink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		waitforElement(180, PUL.CreatNewCredential, driver);
		Thread.sleep(10000);
		WebElement actclick = driver.findElement(By.xpath("//td[2][contains(.,'" + ClientID + "')]/../td[5]/div/a"));
		JavaScriptclick(driver, actclick);
		Thread.sleep(2000);
		driver.findElement(By.linkText("Manage Secrets")).click();
		waitforElement(180, PUL.managesecretsheader, driver);
		assertTrue(PUL.minusicon.isDisplayed());
		assertTrue(PUL.secretcreatedtext.isDisplayed());
		assertTrue(PUL.minusicon.isDisplayed());
		assertTrue(driver.findElement(By.xpath("//span[contains(.,'" + ClientID + "')]")).isDisplayed());
		assertTrue(PUL.deletesecreticon.isDisplayed());
		assertTrue(PUL.AddNewSecrettext.isDisplayed());
		assertTrue(PUL.Closebuttn.isDisplayed());
		if (!addsecret.isEmpty()) {
			click(PUL.AddNewSecrettext);
			waitforText(180, PUL.Successouthcreation, driver);
			JavaScriptclick(driver, actclick);
			Thread.sleep(2000);
			driver.findElement(By.linkText("Manage Secrets")).click();
			waitforElement(180, PUL.managesecretsheader, driver);
		}
		if (!deletesecret.isEmpty()) {
			if (VerifyObject(driver, PUL.deletefirstsecret)) {
				click(PUL.deletefirstsecret);
				assertTrue(PUL.warningtextmanagesecrets.isDisplayed());
				assertTrue(PUL.deletemanagesecret.isDisplayed());
				assertTrue(PUL.closemanagesecret.isDisplayed());
				click(PUL.closemanagesecret);
				click(PUL.deletefirstsecret);
				waitforElement(180, PUL.deletemanagesecret, driver);
				click(PUL.deletemanagesecret);
			}
			if (VerifyObject(driver, PUL.deletesecreticon)) {
				click(PUL.deletesecreticon);
			}
			click(PUL.Closebuttn);
		}
	}

	//Adding OAuth for ITI
	public void outhCreation(String payrollcalc, String Payrolladdcleansing, String oAuthITI, String clientIDITI, String clientDescITI, String ClientIDval, String Clientdes,
			String Clientname) throws Exception {
		ProfessionalUserLinks PUL = PageFactory.initElements(driver, ProfessionalUserLinks.class);
		ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
		FreeUserLinks FUL = PageFactory.initElements(driver, FreeUserLinks.class);
		JavaScriptclick(driver, PUL.SETTINGSLINK);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, FUL.MyOAuthCredentials, driver);
		JavaScriptscrolltoView(driver, FUL.settingsviewallcredentialslink);
		verifyOuthscreen();
		waitforElement(180, FUL.settingsviewallcredentialslink, driver);
		String beforecount[] = driver.findElement(By.xpath("//strong[contains(.,'Total Credentials:')]/../../p[2]"))
				.getText().split(":");
		System.out.println(beforecount[1]);
		JavaScriptclick(driver, FUL.settingsviewallcredentialslink);
		waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
		waitforElement(180, PUL.OAuthCredentialsHEADER, driver);
		click(PUL.CreatNewCredential);
		waitforElement(180, PUL.AddCredential, driver);
		if (!payrollcalc.isEmpty()) {
			selectByValue(PUL.selectcredentialusage, payrollcalc);
			type(PUL.ClientIdinput, ClientIDval);
			type(PUL.CredentialDescription, Clientdes);
		}
		if (!Payrolladdcleansing.isEmpty()) {
			selectByValue(PUL.selectcredentialusage, Payrolladdcleansing);
			type(PUL.ClientIdinput, ClientIDval);
			type(PUL.CredentialDescription, Clientdes);
		}
		if (!oAuthITI.isEmpty()) {
			selectByValue(PUL.selectcredentialusage, oAuthITI);
			type(PUL.ClientIdinput, clientIDITI);
			type(PUL.CredentialDescription, clientDescITI);
		}
		
		
		click(PUL.savebutton);
		waitforText(180, PUL.Successouthcreation, driver);
		Thread.sleep(5000);
		if (!payrollcalc.isEmpty()) {
		assertTrue(driver.findElement(By.xpath("//tr/td[1][contains(.,'" + payrollcalc + "')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[2][contains(.,'" + ClientIDval + "')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[3][contains(.,'" + Clientdes + "')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[4][contains(.,'" + Clientname + "')]")).isDisplayed());
		}
		if (!oAuthITI.isEmpty()) {
		assertTrue(driver.findElement(By.xpath("//tr/td[1][contains(.,'" + oAuthITI + "')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[2][contains(.,'" + clientIDITI + "')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[3][contains(.,'" + clientDescITI + "')]")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//tr/td[4][contains(.,'" + Clientname + "')]")).isDisplayed());
		}
		String[] Secret = driver.findElement(By.xpath("//div/strong[contains(text(),'New Secret:')]/.."))
				.getText().split(":");
		System.out.println("New Secret:"+Secret[1]);
		System.out.println("Client ID:"+clientIDITI);
		ClientSecret = Secret[1];
		ClientID = clientIDITI;
		click(PUL.RIGHTReturntoSettings);
		waitforElement(180, FUL.MyOAuthCredentials, driver);
		JavaScriptscrolltoView(driver, FUL.settingsviewallcredentialslink);
		String[] aftercount = driver.findElement(By.xpath("//strong[contains(.,'Total Credentials:')]/../../p[2]"))
				.getText().split(":");
		String a = aftercount[1].toString();
		String b = beforecount[1].toString();
		assertTrue(a != b);

	}
}