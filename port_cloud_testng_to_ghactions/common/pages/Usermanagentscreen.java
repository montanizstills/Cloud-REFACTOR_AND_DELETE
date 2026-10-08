package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Usermanagentscreen {

	//All users link
	@FindBy(xpath = "//h1[contains(.,'All Users')]")
	public WebElement Allusersheader;
	
	@FindBy(xpath = "//a[contains(.,'Return to Settings')]/../a[@class='btn pull-right ng-scope']")
	public WebElement ReturntoSETTINGSLABEL;
	
	@FindBy(xpath = "//span[contains(.,'First Name')]")
	public WebElement FirstNamelabel;
	
	@FindBy(xpath = "//span[contains(.,'Last Name')]")
	public WebElement LastNamelabel;
	
	@FindBy(xpath = "//th/div/span[contains(.,'Email')]")
	public WebElement Emaillabel;
	
	@FindBy(xpath = "//th/div/span[contains(.,'User Name')]")
	public WebElement UserNamelabel;
	
	@FindBy(xpath = "//th[contains(.,'Phone')]")
	public WebElement Phonelabel;
	
	@FindBy(xpath = "//th/div/span[contains(.,'Created')]")
	public WebElement Createdlabel;
	
	@FindBy(xpath = "//th/div/span[contains(.,'Last Login')]")
	public WebElement Lastloginlabel;
	
	@FindBy(xpath = "//th/div/span[contains(.,'Status')]")
	public WebElement StatusLABEL;
	
	@FindBy(xpath = "//th/div/span[contains(.,'Role')]")
	public WebElement Roleslabel;
	
	@FindBy(xpath = "//th[contains(.,'Action')]")
	public WebElement Actionslabel;
	
	@FindBy(xpath = "//a[contains(text(),'Export User CSV File')]")
	public WebElement Exportbutton;
	
	@FindBy(xpath = "//a[contains(text(),'Export')]")
	public WebElement Exportlink;
	
	@FindBy(xpath = "//div/pagination-extension/span/select")
	public WebElement pagination;
	
	@FindBy(xpath = "//a[contains(.,'Designated Billing Contact')]")
	public WebElement DesignatedBillingContactlink;
	
	@FindBy(xpath = "//a[contains(.,'Designated ECM Contact')]")
	public WebElement DesignatedECMContactlink;
	
	@FindBy(xpath = "//a[contains(.,'Create New User')]")
	public WebElement CreateNewUserlink;
	
	@FindBy(xpath = "//a[contains(.,'CREATE NEW USER')]")
	public WebElement CREATENEWUSER;
	
	@FindBy(xpath = "//a[contains(.,'Delete')]")
	public WebElement Deletelink;
	
	@FindBy(xpath = "//a[contains(.,'Change Role')]")
	public WebElement ChangeRolelink;
	
	
	//end of all users
	
	//Create new Screen	 
	
	@FindBy(xpath = "//h1[contains(.,'Create New User')]")
	public WebElement NEWUSERheader;
	
	@FindBy(xpath = "//label[contains(.,'Client Name')]/../div/input")
	public WebElement ClientNameinputfield;
	
	@FindBy(xpath = "//label[contains(.,'First Name')]/../div/input")
	public WebElement FirstNameinputfield;
	
	@FindBy(xpath = "//label[contains(.,'Last Name')]/../div/input")
	public WebElement LastNameinputfield;
	
	@FindBy(xpath = "//label[contains(.,'Email')]/../div/input")
	public WebElement Emailinputfield;
	
	@FindBy(xpath = "//label[contains(.,'Phone')]/../div/input")
	public WebElement Phoneinputfield;
	
	@FindBy(xpath = "//h2[contains(.,'Role Assignment')]")
	public WebElement RoleAssignmenttext;
	
	@FindBy(xpath = "//label[contains(.,'Account Management')]/../div/select")
	public WebElement AccountManagementdropdown;
	
	@FindBy(xpath = "//label[contains(.,'Vertex Cloud Indirect Tax')]/../div/select")
	public WebElement VertexCloudIndirectTaxdropdown;
	
	@FindBy(xpath = "//label[contains(.,'Exemption Certificate Manager')]/../div/select")
	public WebElement ExemptionCertificateManagerdropdown;
	
	@FindBy(xpath = "//label[contains(.,'Global Compliance')]/../div/select")
	public WebElement GlobalCompliancedropdown;
	
	@FindBy(xpath = "//label[contains(.,'Data Integrity')]/../div/select")
	public WebElement DataIntegritydropdown;
	
	@FindBy(xpath = "//div/input[@value='Send Invite']")
	public WebElement SendInvitelink;
	
	@FindBy(xpath = "//a[contains(.,'Back to')]")
	public WebElement ReturntoUserslink;
	
	//user link screen
	
	@FindBy(xpath = "//a[contains(.,'Back »')]")
	public WebElement Backlink;
	
	@FindBy(xpath = "//p[3]/a")
	public WebElement Emaillink;	
	
	//Complete Registration screen
	
	@FindBy(xpath = "//h1[contains(.,'Complete Your Registration')]")
	public WebElement CompleteYourRegistrationtext;
	
	@FindBy(xpath = "//input[@id='Username']")
	public WebElement Usernamefield;
	
	@FindBy(xpath = "//input[@id='Password']")
	public WebElement Passwordfield;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	public WebElement ConfirmPasswordfield;
	
	@FindBy(xpath = "//button[contains(.,'Complete Registration')]")
	public WebElement CompleteRegistrationbutton;
	
	
	//Success screen 
	
	@FindBy(xpath = "//h1[contains(.,'Success!')]")
	public WebElement successtext;
		
	@FindBy(xpath = "//p[contains(.,'Your registration has been completed. Please click the link below to return to the login screen.')]")
	public WebElement Successlink;
	
	@FindBy(xpath = "//a[contains(.,'Login')]")
	public WebElement Loginlink;	
	
	@FindBy(xpath = "//div[@class='ant-select-selector']")
	public WebElement logoutlink;	
		
	@FindBy(xpath = "//div/span[contains(.,'Log Out')]")
	public WebElement logout;	
	
	//Delete screen 
	
	@FindBy(xpath = "//h3[contains(.,'Are you sure, you want to delete this user?')]")
	public WebElement DeleteheaderText;	
	
	@FindBy(xpath = "//a[contains(.,'Delete')]/../a[@id='confirm-remove-customer']")
	public WebElement removecustomerlink;	
	
	@FindBy(xpath = "//strong[contains(.,'Cancel')]")
	public WebElement cancellink;	
	
	//Change role screen
	
	@FindBy(xpath = "//h1[contains(.,'User Settings')]")
	public WebElement UserSettingstext;	
	
	@FindBy(xpath = "//label[contains(.,'First Name')]/../div/input[@disabled='disabled']")
	public WebElement FirstNamedisabled;
	
	@FindBy(xpath = "//label[contains(.,'Last Name')]/../div/input[@disabled='disabled']")
	public WebElement LastNamedisabled;
	
	@FindBy(xpath = "//label[contains(.,'Email Address')]/../div/input[@disabled='disabled']")
	public WebElement Emaildisabled;
	
	@FindBy(xpath = "//label[contains(.,'Phone')]/../div/input[@disabled='disabled']")
	public WebElement Phonedisabled;
	
	@FindBy(xpath = "//label[contains(.,'User Name')]/../div/input[@disabled='disabled']")
	public WebElement UserNamedisabled;
		
	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement Changerolesavebutton;	
	
	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement Changerolecancelbutton;
	
	@FindBy(xpath = "//div[contains(.,'User settings updated!')]/strong[contains(.,'Success!')]")
	public WebElement usersettingssuccess;
	
	@FindBy(xpath = "//li/strong[contains(.,'Active')]/../span[1]")
	public WebElement Activecount;
	
	@FindBy(xpath = "//li/strong[contains(.,'Pending')]/../span[1]")
	public WebElement PendingCount;
	
	@FindBy(xpath = "//li/strong[contains(.,'Deactivated')]/../span[1]")
	public WebElement DeletedCount;
	
	@FindBy(xpath = "//div[contains(text(),'This user is currently the designated billing cont')]")
	public WebElement designatedbillingtext;
		
	@FindBy(xpath = "//tr[@class='inner-table']/td/div[contains(.,'Select Billing Contact')]")
	public WebElement SelectBillingContacttext;
	
	@FindBy(xpath = "//tr[@class='inner-table']/td/div/div/select")
	public WebElement SelectBillingContactdrodown;
	
	@FindBy(xpath = "//tr[@class='inner-table']/td/div/div/a[contains(.,'Update')]")
	public WebElement designatedbillingUpdate;	
	
	@FindBy(xpath = "//h3[contains(.,'Warning!')]")
	public WebElement Warningtext;
	
	@FindBy(xpath = "//p[contains(.,'You are about to update designated billing contact.')]")
	public WebElement designatedbillingcontact;
	
	@FindBy(xpath = "//strong[contains(.,'Are you sure you want to update this designated billing contact?')]")
	public WebElement designatedbillingcontactcofirmtet;
	
	@FindBy(xpath = "//a[contains(.,'Update')]/../a[@id='confirm-remove-customer']")
	public WebElement Updatebutton;
	
	@FindBy(xpath = "//a/strong[contains(.,'Cancel')]")
	public WebElement Cancelbutton;
			
	@FindBy(xpath = "//div[contains(text(),'This user is currently the designated Exemption Ce')]")
	public WebElement designatedECMtext;
		
	@FindBy(xpath = "//tr[@class='inner-table']/td/div[contains(.,'Select ECM Contact')]")
	public WebElement SelectECMContacttext;
	
	@FindBy(xpath = "//tr[@class='inner-table']/td/div/select")
	public WebElement SelectECMContactdrodown;
		
	@FindBy(xpath = "//div[contains(text(),'Recieve monthly notifications of Expiration Certif')]")
	public WebElement ExpirationECMdays;
		
	@FindBy(xpath = "//tr[@class='inner-table']//div[@class='row-fluid']//div[1]")
	public WebElement EnableReportECM ;	
	
	@FindBy(xpath = "//tr[@class='inner-table']//div[@class='row-fluid']//div[2]")
	public WebElement disableReportECM;	
	
	@FindBy(xpath = "//tr[@class='inner-table']/td/div/a[contains(.,'Update')]")
	public WebElement designatedECMUpdate;		
	
	@FindBy(xpath = "//p[contains(.,'You are about to update designated ECM contact.')]")
	public WebElement designatedEMcontact;
	
	@FindBy(xpath = "//strong[contains(.,'Are you sure you want to update this designated ECM contact?')]")
	public WebElement designatedECMcontactcofirmtet;
		
	@FindBy(xpath = "//span[contains(.,'Log Back In')]")
	public WebElement UMLoginlink;	
	//Account Management Admin, Exemption Certificate Manager Admin, Vertex Cloud Indirect Tax Admin, Global Compliance Admin, Data Integrity Admin 

	@FindBy(xpath = "//input[@id='firstName']/../div/span[contains(.,'This field is required.')]")
	public WebElement FirstNameerrorinputfield;
	
	@FindBy(xpath = "//input[@id='LastName']/../div/span[contains(.,'This field is required.')]")
	public WebElement LastNameerrorinputfield;
	
	@FindBy(xpath = "//input[@id='Email']/../div/span[contains(.,'This field is required.')]")
	public WebElement Emailerrorinputfield;
	
	@FindBy(xpath = "//input[@id='phone']/../div/span[contains(.,'This field is required.')]")
	public WebElement Phoneerrorinputfield;
	
	@FindBy(xpath = "//div/h2[normalize-space()='Role Assignment']/../../div/div[@class='help-block']")
	public WebElement roleassignmenterror;
	
	
}
