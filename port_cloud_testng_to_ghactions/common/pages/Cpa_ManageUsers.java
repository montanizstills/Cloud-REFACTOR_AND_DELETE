package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cpa_ManageUsers {

	public final String SELECTALLCLINETS_XPATH = "//div/a[contains(.,'Select All')]/../../div/table/tbody";
	
	public final String REMOVEALLCLINETS_XPATH = "//div/a[contains(.,'Remove All')]/../../div/table/tbody";
	
	@FindBy(xpath = "//div[@class='container-nav']/ul/li/a/span[text()='Settings']")
	public WebElement SETTINGS;
	
	@FindBy(xpath = "//div[@class='section-action']/a[text()='Create New User']")
	public WebElement CREATENEWUSER;
	
	@FindBy(xpath = "//input[@id='FirstName']")
	public WebElement FIRSTNAME;
	
	@FindBy(xpath = "//input[@id='LastName']")
	public WebElement LASTNAME;
	
	@FindBy(xpath = "//input[@id='email']")
	public WebElement EMAIL;
	
	@FindBy(xpath = "//input[@id='UserPhone']")
	public WebElement PHONENUMBER;
	
	@FindBy(xpath = "//select[@id='userRoleId']")
	public WebElement USERROLE;
	
	@FindBy(xpath = "//div[@class='row-fluid']/button[text()='SEND INVITE']")
	public WebElement SENDINVITE;
	
	@FindBy(xpath = "//div[@class='ng-scope']/p/a[text()='Complete Registration']")
	public WebElement COMPLETEREGISTRATION;
	
	@FindBy(xpath = "//input[@id='Username']")
	public WebElement USERNAME;
	
	@FindBy(xpath = "//input[@id='Password']")
	public WebElement PASSWORD;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	public WebElement CONFIRMPASSWORD;
	
	@FindBy(xpath = "//input[@id='agreedToSA']")
	public WebElement IAGREE;
	
	@FindBy(xpath = "//button[contains(.,'Complete Registration')]")
	public WebElement PROFILECOMP_REG;
	
	@FindBy(xpath = "//div[@class='section-action']/a[text()='Manage Subscription Settings']")
	public WebElement MANAGESUBSCRIPTIONSETTINGS;
	
	@FindBy(xpath = "//div[@class='section ng-scope']/h1/a[text()='RETURN TO SETTINGS']")
	public WebElement RETURNTOSETTINGS;
	
	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/thead/tr/th[contains(.,'Created')]")
	public WebElement SORT_CREATED;
	
	@FindBy(xpath = "//div[@class='alert alert-success']/strong[text()='Success!']")
	public WebElement SUCCESSMSG;
	
	// For View All Users
	@FindBy(xpath = "//div[@class='section-action']/a[text()='View All Users']")
	public WebElement VIEWALLUSERS;
	
	@FindBy(xpath = "//h2[text()='Company Information']/../div/a[text()='Edit']")
	public WebElement COMPINFOEDIT;
	
	@FindBy(xpath = "//input[@id='address1']")
	public WebElement COMPANYADDRESS;
	
	@FindBy(xpath = "//input[@id='phone']")
	public WebElement PHONE;
	
	@FindBy(xpath = "//select[@id='state']")
	public WebElement STATESELECT;
	
	@FindBy(xpath = "//input[@id='zip']")
	public WebElement ZIPCODE;
	
	@FindBy(xpath = "//select[@id='country']")
	public WebElement COUTRY;
	
	@FindBy(xpath = "//label[text()='Municipality']")
	public WebElement MUNICIPALITYCHANGE;
	
	@FindBy(xpath = "//label[text()='Province / Territory']")
	public WebElement PROVINCE;
	
	@FindBy(xpath = "//label[text()='Postal Code']")
	public WebElement POSTALCODE;
	
	@FindBy(xpath = "//label[text()='City']")
	public WebElement CITY;
	
	@FindBy(xpath = "//label[text()='State']")
	public WebElement STATE;
	
	@FindBy(xpath = "//label[text()='Zip / Postal Code']")
	public WebElement ZIP;
	
	@FindBy(xpath = "//a[text()='DELETE USER']")
	public WebElement DELETEUSER;
	
	@FindBy(xpath = "//p/a/u/strong[text()='Cancel']")
	public WebElement CANCEL;
	
	@FindBy(xpath = "//button[text()='Save']")
	public WebElement EDITSAVE;
	
	@FindBy(xpath = "//div[@class='modal-body ng-pristine ng-valid ng-scope']/p/a[text()='DELETE USER']")
	public WebElement DELETEUSER_POPUP;
	
	@FindBy(xpath = "//div[@class='section ng-scope']/h1/a[text()='RETURN TO USER LISTING']")
	public WebElement RETURNTOUSERLISTING;
	
	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement LASTNAME_EDIT;
	
	@FindBy(xpath = "//button[contains(.,'SAVE')]")
	public WebElement SAVE;
	
	@FindBy(xpath = "//input[@class='search input-append ng-pristine ng-valid ng-touched']")
	public WebElement QUICKSEARCHCLICK;
	
	@FindBy(xpath = "//input[@class='search input-append ng-pristine ng-untouched ng-valid ng-empty']")
	public WebElement QUICKSEARCH;
	
	@FindBy(xpath = "//input[@id='userName']")
	public WebElement VIEWALLUSERNAME;
	
	@FindBy(xpath = "//div[@class='registration-block ng-scope' and @ng-repeat='company in data.companies']/div/h3[@class='h5 heading-accent has-warning ng-binding']")
	public WebElement INPROCESSCOMPANYCONFIG;
	
	@FindBy(xpath = "html[@class='nprogress-busy']")
	public WebElement NPROGRESSBUSY;
	
	// Deleting users
	@FindBy(xpath = "//a/u[text()='Deleted Users']")
	public WebElement DELETEDUSERS;
	
	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr[2]/td[7][text()='Deleted']/../td[1]/a")
	public WebElement DELETEDUSERFIRSTROW;
	
	@FindBy(xpath = "//div[@class='row-fluid']/a[text()='REACTIVATE USER']")
	public WebElement REACTIVATEUSER;
	
	@FindBy(xpath = "//p/a[text()='Reactivate USER']")
	public WebElement POPUPREACTIUSER;
	
	@FindBy(xpath = "//button[text()='RESEND INVITE']")
	public WebElement RESENDINVITE;
	
	@FindBy(xpath = "//h2[contains(.,'Client Account Access')]")
	public WebElement CLIENTACCOUNTACCESS;
	
	@FindBy(xpath = "//p[contains(.,'Users with CPA Staff role are only able to view and manage client information if granted access below. Access can be changed at any time.')]")
	public WebElement CLIENTAATITLESTAFF;
	
	@FindBy(xpath = "//p[contains(.,'Users with the CPA Admin role will have access to all client accounts by default. If you wish to limit account access for this user, simply change the User Role listed above to CPA Staff.')]")
	public WebElement CLIENTAATITLEADMIN;
	
	@FindBy(xpath = "//multiselect/div/div/div[contains(.,'Select client(s) to grant access for this user')]")
	public WebElement GRANTACCESS;
	
	@FindBy(xpath = "//multiselect/div/div/div[contains(.,'client accounts selected for this user')]")
	public WebElement SELECTEDCLIENTS;
	
	@FindBy(xpath = "//a[contains(.,'Select All')]")
	public WebElement SELECTALL;
	
	@FindBy(xpath = "//a[contains(.,'Remove All')]")
	public WebElement REMVOEALL;
	
	@FindBy(xpath = "//div/a[contains(.,'Select All')]/../../div/table/tbody")
	public WebElement SELECTALLCLINETS;
	
	@FindBy(xpath = "//div/a[contains(.,'Remove All')]/../../div/table/tbody")
	public WebElement REMOVEALLCLINETS;
	
	@FindBy(xpath = "//select[@class='ng-pristine ng-untouched ng-valid ng-not-empty']")
	public WebElement DROPDOWN;
	
	@FindBy(xpath = "//a[contains(.,'Return to Settings')]")
	public WebElement CONRETURNTOSETTINGS;
	
	@FindBy(xpath = "//div/h1[contains(.,'Settings')]")
	public WebElement SETTINGSHEADER;
	
	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr[2]/td[7][text()='Active']/../td[1]/a")
	public WebElement ACTIVELINk;
	
	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody")
	public WebElement TABLELOAD;
	
	@FindBy(xpath = "//a[contains(.,'Return to Settings')]")
	public WebElement ReturntoSettings;
	
	@FindBy(xpath = "//h3[@id='removeConfirmHeader1']")
	public WebElement EmailResent;
	
	@FindBy(xpath = "//a[@id='confirm-remove']")
	public WebElement ConfirmResent;
	
	@FindBy(xpath = "//a[contains(.,'Login')]")
	public WebElement LOGIN;
	
	@FindBy(xpath = "//div/a[contains(.,'View All Users')]")
	public WebElement VIEWALLUAERS;
	
	@FindBy(xpath = "//div/a[contains(.,'Export')]")
	public WebElement EXPORTBUTTON;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	public WebElement Search;
	
	@FindBy(xpath = "//h1[contains(.,'USER DETAILS')]")
	public WebElement UserDetails;
	
	@FindBy(xpath = "//button[contains(.,'RESEND INVITE')]")
	public WebElement Resendlink;
	
	@FindBy(xpath = "//a[contains(.,'DELETE USER')]")
	public WebElement Deleteuser;
	
	@FindBy(xpath = "  //div[contains(.,'You have successfully resent an invitation to the user.')]/strong[contains(.,'Success!')]")
	public WebElement Successresend;
	
	@FindBy(xpath = "//h3[contains(.,' Delete User')]")
	public WebElement Deleteuserheaderpopup;
	
	@FindBy(xpath = " //a[@id='confirm-remove-user']")
	public WebElement Deleteuserbutton;
	
	@FindBy(xpath = "//a[contains(.,'REACTIVATE USER')]")
	public WebElement reactivateuser;
	
	@FindBy(xpath = "//a[contains(.,'RETURN TO USER LISTING')]/../a[@class='btn pull-right']")
	public WebElement ReturntoUserListing;
	
	@FindBy(xpath = "//a[contains(.,'RETURN TO SETTINGS')]/../a[@class='btn pull-right']")
	public WebElement Returntosettings;
	
	@FindBy(xpath = "//select[@id='roles0']")
	public WebElement ACCOUNTMANAGEMENTROLE;
	
	@FindBy(xpath = "//select[@id='roles1']")
	public WebElement VERTEXCLOUDINDIRECTTAXROLE;
	
	@FindBy(xpath = "//select[@id='roles2']")
	public WebElement EXEMPTIONCMROLE;
	
	@FindBy(xpath = "//select[@id='roles3']")
	public WebElement GLOBALCOMPLAINCEROLE;
	
	@FindBy(xpath = "//select[@id='roles4']")
	public WebElement DATAINTEGRITYROLE;
	
	@FindBy(xpath = "//select[@id='roles5']")
	public WebElement INDIRECTTAXINTELLIGENCE;
	
	@FindBy(xpath = "//a[contains(.,'Add New User')]")
	public WebElement ADDNEWUSER;
	
	@FindBy(xpath = "//input[@id='LastName']")
	public WebElement LASTNAME1;
	
	@FindBy(xpath = "//input[@id='Email']")
	public WebElement USEREMAIL;
	
	@FindBy(xpath = "//input[@value='Send Invite']")
	public WebElement SENDINVITEUSER;
	
	@FindBy(xpath = "//div/p[contains(.,'a direct link to continue registration')]/a")
	public WebElement COMPLETEREGISTRATIONLINK;
	
	@FindBy(xpath = "//p/a[contains(.,'Delete')]")
	public WebElement DELETEUSERBUTTON;

	@FindBy(xpath = "//input[@value='Select User Roles']")
	public WebElement GLOBALCOMPLIANCE;
	
	@FindBy(xpath = "//a[contains(.,'Return to Users')]")
	public WebElement RETURNTOUSERS;

}
