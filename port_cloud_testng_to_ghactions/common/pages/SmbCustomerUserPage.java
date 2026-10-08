package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class SmbCustomerUserPage {

	@FindBy(xpath = "//input[@id='NewUser_Phone']")

	public WebElement PHONE_XPATH;
	@FindBy(xpath = "//a[contains(text(),'Add New User')]")
	public WebElement CREATENEWUSER;
	@FindBy(xpath = "//input[@id='NewUser_FirstName']")

	public WebElement FIRSTNAME;
	@FindBy(xpath = "//input[@id='NewUser_LastName']")

	public WebElement LASTNAME;
	@FindBy(xpath = "//input[@id='Email']")

	public WebElement EMAIL;
	@FindBy(xpath = "//input[@id='phone']")

	public WebElement PHONE;
	@FindBy(xpath = "//input[@id='Role1']")

	public WebElement ROLE1;
	@FindBy(xpath = "//input[@id='Role2']")

	public WebElement ROLE2;
	@FindBy(xpath = "//input[@id='Role30']")

	public WebElement ROLE3;
	@FindBy(xpath = "//input[@value='Send Invite']")

	public WebElement SENDINVITE;
	@FindBy(xpath = "//p[3]/a")

	public WebElement CONFIRMREGLINK;
	@FindBy(xpath = "//input[@id='UserName']") // input[@id='userName']

	public WebElement USERNAME;
	@FindBy(id = "Password")

	public WebElement password;
	@FindBy(xpath = "//input[@id='PasswordConfirm']")

	public WebElement PASSWORDCONFIRM;
	@FindBy(xpath = "//input[@id='ConfirmPassword']")

	public WebElement CONFIRMPASSWORD;
	@FindBy(xpath = "//input[@id='AcceptedTerms']")

	public WebElement SERVICETYPE;
	@FindBy(xpath = "//button[contains(text(),'Complete')]")

	public WebElement COMPLETE;
	// Franchise create new user
	@FindBy(xpath = "//a[contains(.,('Return to Customer Detail'))]")
	public WebElement RETURNTOCUSTOMERDETAIL;
	@FindBy(xpath = "//a[contains(.,('Create New User'))]")

	public WebElement ADMINCREATENEWUSER;
	@FindBy(xpath = "//a[contains(text(),' Create New User')]")

	public WebElement FRANCHISECREATENEWUSER;
	@FindBy(xpath = "//input[@id='firstName']")
	public WebElement FRANCHISEFIRSTNAME;
	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement FRANCHISELASTNAME;
	@FindBy(xpath = "//input[@id='LastName']")
	public WebElement LASTNAME1;
	@FindBy(xpath = "//input[@id='email']")

	public WebElement FRANCHISEEMAIL;
	@FindBy(xpath = "//input[@id='phoneNumber']")

	public WebElement FRANCHISEPHONE;
	@FindBy(xpath = "//option[contains(.,'Admin')]")

	public WebElement DefaultRole;
	@FindBy(xpath = "//button[contains(text(),'SEND INVITE')]")

	public WebElement SEND_INVITE;
	@FindBy(xpath = "//a[contains(.,'Complete Registration')]")

	public WebElement CompleteRegistrationLink;
	@FindBy(xpath = "//div[@class='ant-card-head-title text-center']/img")
	public WebElement VertexTitle;
	@FindBy(xpath = "//input[@id='Username']")

	public WebElement FRANCHISEUSERNAME;
	@FindBy(xpath = "//input[@id='Password']")

	public WebElement PASSWORD;
	@FindBy(xpath = "//input[@id='agreedToSA']")

	public WebElement SERVICEAgreement;
	@FindBy(xpath = "//button[contains(.,'Complete Registration')]")

	public WebElement COMPLETERegistration;
	@FindBy(xpath = "//h1[contains(.,'Manage Users')]")

	public WebElement Manage_Users_Title;
	@FindBy(xpath = "//input[@type='text']")

	public WebElement QuickSearch;
	@FindBy(xpath = "//span[@class='pagination-index ng-scope']")

	public WebElement Pagination;
	@FindBy(xpath = "//h2/strong[contains(.,'User Actions')]")

	public WebElement UserActionsTitle;
	@FindBy(xpath = "//a[contains(.,'DELETE USER')]")

	public WebElement DELETEUSER;
	@FindBy(xpath = "//a[@id='confirm-remove-user']")

	public WebElement confirmremove;
	@FindBy(xpath = "//strong[contains(.,'Success!')]")

	public WebElement Success;
	@FindBy(xpath = "//h4[contains(.,'Reactivate User')]")

	public WebElement Reactivate;
	@FindBy(xpath = "//h4[contains(.,'Reactivate User')]/../p")

	public WebElement ReactivateDescription;
	@FindBy(xpath = "//a[contains(.,'REACTIVATE USER')]")

	public WebElement REACTIVATEUSER;
	@FindBy(xpath = "//a[@id='confirm-remove-user']")

	public WebElement ConfirmReactivate;
	@FindBy(xpath = "//a[contains(.,'RETURN TO USER LISTING')]")

	public WebElement RETURNTOUSERLISTING;
	@FindBy(xpath = "//a[contains(.,'Deleted Users')]")

	public WebElement DeletedUsers;
	@FindBy(xpath = "//a[contains(.,'Return to Settings')]")

	public WebElement ReturntoSettings;
	@FindBy(xpath = "//a/strong[contains(.,'Pending')]")

	public WebElement Pending;
	@FindBy(xpath = "//h4[contains(.,'Resend Invitation')]/../p")

	public WebElement ResendInvitationInfo;
	@FindBy(xpath = "//button[contains(.,'RESEND INVITE')]")

	public WebElement RESENDINVITE;
	@FindBy(xpath = "//strong[contains(.,'Active')]/..")

	public WebElement ActiveUsers;
	@FindBy(xpath = "//input[@value='Export']")

	public WebElement ExportButton;
	@FindBy(xpath = "//div/a[contains(.,'View All Users')]")

	public WebElement VIEWALLUSERS;
	@FindBy(xpath = "//a[contains(.,'Return to Users')]")

	public WebElement RETURNTOUSERS;
	@FindBy(xpath = "//strong[contains(.,'Deleted')]")

	public WebElement DELETEDUSERSLNK;
	@FindBy(xpath = "//li/a/strong[contains(.,'Deleted')]")
	

	public WebElement DeletedUsersPage;
	
   @FindBy(xpath = "//td[contains(.,'Deleted')]")
	

	public WebElement DeletedUsersPage1;

	@FindBy(xpath = "//a[contains(.,'RETURN TO SETTINGS') and @class='btn pull-right']")
	public WebElement ReturntoSettingsRightBtn;

	@FindBy(xpath = "//select[@id='roles0']")
	public WebElement ACCOUNTMANAGEMENT;

	@FindBy(xpath = "//select[@id='roles1']")
	public WebElement VERTEXCLOUDINDIRECTTAX;

	@FindBy(xpath = "//select[@id='roles2']")
	public WebElement ECMANAGER;

	@FindBy(xpath = "//select[@id='roles3']")
	public WebElement GLOBALCOMPLIANCE;

	@FindBy(xpath = "//select[@id='roles4']")
	public WebElement DATAINTEGRITY;
	
	@FindBy(xpath="//select[@id='roles5']")
	public WebElement INDIRECTTAXINTELLIGENCE;

	@FindBy(xpath = "//a[contains(.,'Designated Billing Contact')]")
	public WebElement DESIGNATEDBILLINGCONTACT;

	@FindBy(xpath = "//a[contains(.,'Designated ECM Contact')]")
	public WebElement DESIGNATEDECMCONTACT;

	@FindBy(id = "ConfirmPassword")
	public WebElement CONFIRMPASSWORDNEW;

	@FindBy(xpath = "//p[contains(text(),'Your registration has been completed. Please click the link below to return to the login screen.')]")
	public WebElement SuccessMessage;

	@FindBy(xpath = "//div/a[contains(.,'Login')]")
	public WebElement LOGIN;
	
	@FindBys({ @FindBy(xpath = "//select[@id='roles0']/option"), })
	public List<WebElement> accountotions;
	
	@FindBy(xpath = "//input[@id='FirstName']")
	public WebElement ADFIRSTNAME;
	
	@FindBy(xpath = "//input[@id='LastName']")
	public WebElement ADLASTNAME;
	
	@FindBy(xpath = "//input[@id='UserPhone']")
	public WebElement ADPHONE;
	
	@FindBy(xpath = "//input[@id='Password']")
	public WebElement ADPASSWORD;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	public WebElement ADCONFORMPASSWORD;
	
	@FindBy(xpath = "//button[@type='submit'][contains(.,'Complete Registration')]")
	public WebElement Complete_Registration;

}
