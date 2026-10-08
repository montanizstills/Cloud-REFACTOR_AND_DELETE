package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	public final String EMAIL_XPATH = "//input[@id='Email']";

	@FindBy(id = "username")
	public WebElement USERNAME;

	@FindBy(id = "password")
	public WebElement PASSWORD;

	@FindBy(xpath = "//i[@class='icon-info icon-small']")
	public WebElement USERLOGINI;

	@FindBy(xpath = "//h1[@class='text-center']")
	public WebElement USERLOGINTEXT;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement LOGIN;

	@FindBy(linkText = "account")
	public WebElement ACCOUNTICON;

	@FindBy(xpath = "//a[contains(.,'Forgot password?')]")
	public WebElement FORGETPWD;

	@FindBy(xpath = "//button[contains(text(),'Email Reset Link')]")
	public WebElement RESETLINK;

	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	public WebElement CONTINUE;

	@FindBy(xpath = "//a[contains(text(),'Back to Login')]")
	public WebElement BACKTOLOGIN;

	@FindBy(xpath = "//h1[contains(text(),'Check Your Email')]")
	public WebElement SUCCESSMSG;

	@FindBy(id = "phHeaderLogoImage")
	public WebElement SALESFORCEICON;

	@FindBy(xpath = "//span[@data-content='Please provide your user name and password to enter TaxCentral.  The supported browsers for TaxCentral include IE 10 and higher, Chrome, Firefox, and Safari for IOS 7.']")
	public WebElement POPUPTEXT;

	@FindBy(xpath = "//span[text()='Dashboard']")
	public WebElement DASHBOARD;

	@FindBy(xpath = "//span[text()='Settings']")
	public WebElement SETTINGS;

	@FindBy(id = "username")
	public WebElement CPA_USERNAME;

	@FindBy(id = "password")
	public WebElement CPA_PASSWORD;

	@FindBy(xpath = "//i[@class='icon-info icon-small']")
	public WebElement CPA_USERLOGINI;

	@FindBy(xpath = "//h1[contains(text(),'User Login')]")
	public WebElement CPA_USERLOGINTEXT;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement CPA_LOGIN;

	@FindBy(xpath = "//div[@class='validation-summary-errors alert alert-error']/ul/li[text()='Incorrect Username or Password!']")
	public WebElement CPA_INVALIDLOGIN;

	/*
	 * @FindBy(linkText="account")
	 * 
	 * public WebElement VTMSACCOUNT;
	 */
	@FindBy(xpath = "//a[@href='#account']/i")
	public WebElement VTMSACCOUNT;

	@FindBy(xpath = "//div[@class='alert alert-danger ng-binding']")
	public WebElement DELETEDVAR;

	@FindBy(xpath = "//a[contains(.,'User Settings')]")
	public WebElement USERSETTINGS;

	@FindBy(id = "firstName")
	public WebElement FIRSTNAME;

	/*
	 * @FindBy(id="logout")
	 * 
	 * public WebElement VTMSLOGOUT;
	 */
	@FindBy(xpath = "//a[@id='logout']")
	public WebElement VTMSLOGOUT;

	@FindBy(xpath = "//span[@id='error-element-password']")
	public WebElement LOGINLOCKERROR;
	/*
	 * @FindBy(xpath =
	 * "//div[@class='alert alert-danger ng-binding'][contains(.,'Your account has been locked for security reasons. Please reset your password using the link below.')]"
	 * )
	 * 
	 * public WebElement LOGINLOCKBLOCKERROR;
	 */

	@FindBy(id = "Email")
	public WebElement EMAIL;

	@FindBy(id = "UserName")
	public WebElement FORGOTUSERID;

	@FindBy(id = "requestUsername")
	public WebElement CPAFORGOTUSERID;

	@FindBy(xpath = "//img[@alt='TaxCentral']")
	public WebElement CPALOGINPAGELOGO;

	@FindBy(xpath = "//a[contains(text(),'Enroll today')]")
	public WebElement ENROLLTODAYLINK;

	@FindBy(xpath = "//img[@alt='TaxCentral']")
	public WebElement CPAIMG;

	@FindBy(xpath = "//div[contains(.,'Invalid username or password.')]/../div/strong[contains(.,'Error:')]")
	public WebElement INVALIDUSER;

	@FindBy(xpath = "//img[@alt='Vertex Cloud']")
	public WebElement VERTEXCLOUD;

	@FindBy(xpath = "//img[@alt='CPA Console']")
	public WebElement CPACONSOLE;

	@FindBy(xpath = "//img[@alt='Salesforce']")
	public WebElement SALESFORCE;

	@FindBy(xpath = "//a[contains(.,'please signout and login again')]")
	public WebElement FORBIDDEN_LOGOUT;

	@FindBy(id = "username")
	public WebElement UserName;

	@FindBy(xpath = "//fieldset/div/div/a[contains(.,'Cancel')]")
	public WebElement CANCELUSERSETTINGS;

	@FindBy(xpath = "//h4[contains(.,'Exemption Certificate Portal')]")
	public WebElement ECPortalText;

	@FindBy(xpath = "//button[@id='log-out']")
	public WebElement ECLogout;

	@FindBy(xpath = "//button[contains(.,'Login')]")
	public WebElement ECLogin;

	@FindBy(id = "emailAddress")
	public WebElement EmailAddress;

	@FindBy(id = "EmailAddress")
	public WebElement Fusername;

	@FindBy(xpath = "//a[contains(.,'Go to Dashboard')]")
	public WebElement GotoDashboard;

	@FindBy(xpath = "//a[contains(.,'Username')]")
	public WebElement Forgotusername;

	@FindBy(xpath = "//a[contains(.,'Password')]")
	public WebElement ForgotPassword;

	@FindBy(xpath = "//p[contains(.,'Forgot username')]")
	public WebElement Forgotusernameheader;

	@FindBy(xpath = "//input[@type='email']")
	public WebElement ForgotUseremail;

	@FindBy(xpath = "//button[contains(.,'Email username')]")
	public WebElement Emailuserbutton;

	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement Cancel;

	@FindBy(xpath = "//p[contains(.,'Reset your password')]")
	public WebElement Forgotpasswordheader;

	@FindBy(xpath = "//input[@id='Username']")
	public WebElement ForgotPWDUsername;

	@FindBy(xpath = "//label[contains(.,'Username')]")
	public WebElement ForgotPWDUsernamelabel;

	@FindBy(xpath = "//label[contains(.,'Email address')]")
	public WebElement ForgotPWDemaillabel;

	@FindBy(xpath = "//button[contains(.,'Email password reset link')]")
	public WebElement EmailPwdresetbutton;

	@FindBy(xpath = "//h1[contains(.,'Success!')]")
	public WebElement forgotPWDsuccess;

	@FindBy(xpath = "//label[contains(.,'Username')]")
	public WebElement Usernamelabel;

	@FindBy(xpath = "//label[contains(.,'Password')]")
	public WebElement PWDlabel;

	@FindBy(xpath = "//small[contains(.,'© 2021 Vertex, Inc. All rights reserved.')]")
	public WebElement Copyrightext;

	@FindBy(xpath = "//a[contains(.,'Back to login screen')]")
	public WebElement Backtologinscreen;

	@FindBy(xpath = "//p[contains(.,'Enter your Vertex Cloud email address and we will email you your username.')]")
	public WebElement Forgotusernameheadertext;

	@FindBy(xpath = "//p[contains(.,'Your Username has been emailed to your Vertex Cloud')]")
	public WebElement Forgotusernamesuccestext;

	@FindBy(xpath = "//p[contains(.,'Email Sent!')]")
	public WebElement Emailsent;

	@FindBy(xpath = "//p[contains(.,'Enter your Vertex Cloud username and email address')]")
	public WebElement ForgotPWDheadertext;

	@FindBy(xpath = "//p[contains(.,'Please check your email for instructions on how to reset your password.')]")
	public WebElement Forgotpasswordsuccestext;

	@FindBy(xpath = "//span[contains(.,'Please enter a valid email address')]")
	public WebElement ValidEmail;

	@FindBy(xpath = "//span[contains(.,'Please enter a valid username')]")
	public WebElement ValidUsername;

	@FindBy(xpath = "//span[contains(.,'Please check your password and try again')]")
	public WebElement ValidPassword;

	@FindBy(xpath = "//label[contains(.,'Email address')]")
	public WebElement Emaillabel;

	@FindBy(xpath = "//span[@id='Email-error']/../span[contains(.,'Please enter a valid email address')]")
	public WebElement invalidemail;

	@FindBy(xpath = "//input[@id='username']")
	public WebElement SALESFORCEUSERNAME;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement SALESFORCEPASSWORD;

	@FindBy(xpath = "//input[@id='Login']")
	public WebElement SALESFORCELOGIN;

	@FindBy(xpath = "//a[@title='Contacts']/span[contains(.,'Contacts')]")
	public WebElement SALESFORCECONTACTS;

	@FindBy(xpath = "//input[@placeholder='Search Contacts and more...']")
	public WebElement SFSEARCHCONTACTS;

	@FindBy(xpath = "//label/span[contains(.,'Active Portal User')]/../../div/span/..")
	public WebElement ACTIVEPORTALUSER;

	@FindBy(xpath = "//button/span[contains(.,'Edit Active Portal User')]")
	public WebElement EDITACTIVEPORTALUSER;

	@FindBy(xpath = "//span/div/span[@class='uiImage']")
	public WebElement SALESFORCELOGOUTICON;

	@FindBy(xpath = "//a[contains(.,'Log Out')]")
	public WebElement SALESFORCELOGOUT;

	@FindBy(xpath = "//button[@title='Edit Active Portal User']")
	public WebElement EDIT;

	@FindBy(xpath = "//button[@id='utilityLinkLogout_button']")
	public WebElement Oseriesloout;

	@FindBy(xpath = "//button[contains(.,'Login')]")
	public WebElement Oserieslogin;

	@FindBy(xpath = "//input[@id='username']")
	public WebElement Oseriesusername;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement Oseriespassword;

	@FindBy(xpath = "//div[contains(.,'Your password has expired. You must change your password to continue.')]")
	public WebElement pwdexpirymsg;

	@FindBy(xpath = "//input[@id='old-password']")
	public WebElement oldpassword;

	@FindBy(xpath = "//input[@id='new-password']")
	public WebElement newpassword;

	@FindBy(xpath = "//input[@id='confirm-password']")
	public WebElement confirmnewpassword;

	@FindBy(xpath = "//select[@id='SelectedTenantId']")
	public WebElement SELECTAVAILABLECLIENTS;

	@FindBy(xpath = "//h2[contains(.,'Test Instance Environment')]")
	public WebElement TESTINSTANEENVTEXT;

	@FindBy(xpath = "//a[contains(.,'password')]")
	public WebElement FORGOTPWD;

	@FindBy(xpath = "//p[@class='text-justify']")
	public WebElement EMAILLINKINFORGOTWD;

	@FindBy(xpath = "//input[@id='email']")
	public WebElement EMAILADDRESSIN;

	@FindBy(xpath = "//button[normalize-space()='Email Reset Link']")
	public WebElement EMAILRESETLINK;

	@FindBy(xpath = "//a[normalize-space()='Cancel']")
	public WebElement CANCELLINK;

	@FindBy(xpath = "//div[@class='ant-alert-content']")
	public WebElement USERDONTHAVEACCESS;

}
