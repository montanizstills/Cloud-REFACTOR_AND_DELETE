package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Addseller {

	@FindBy(xpath = "//span[contains(.,'Sellers')]")
	public WebElement SELLER;

	@FindBy(xpath = "//span[contains(.,'Add Seller')]")
	public WebElement ADDSELLER;

	@FindBy(id = "code")
	public WebElement SELLERCODE;

	@FindBy(id = "name")
	public WebElement SELLERNAME;

	@FindBy(id = "companyCode")
	public WebElement COMPANYCODE;

	@FindBy(xpath = "//span[contains(.,'Save Seller')]")
	public WebElement SAVESELLER;

	@FindBy(xpath = "//span[contains(.,'Users')]")
	public WebElement USERS;

	@FindBy(xpath = "//div[@data-testid='datatable-tablerow-1-edit-button']")
	public WebElement EditUSERBUTTON;

	@FindBy(xpath = "//label[contains(.,'Wizard')]")
	public WebElement Wizardcheckbox;

	@FindBy(xpath = "//span[contains(.,'Save User')]")
	public WebElement SAVEUSERBUTTON;

	@FindBy(id = "sellerCd")
	public WebElement SELLERCD;

	@FindBy(id = "buyCd")
	public WebElement BUYERCD;

	@FindBy(xpath = "//input[@value='Update wizard Parameters']")
	public WebElement UPDATEWIZARDPARAMETERS;

	@FindBy(id = "wizard-btn")
	public WebElement SUBMITTAX;

	@FindBy(id = "vertex-username")
	public WebElement Username;

	@FindBy(xpath = "//span[contains(.,'Products')]")
	public WebElement PRODUCT;

	@FindBy(xpath = "//a[@class='btn pull-right']")
	public WebElement Viewallmappings;

	@FindBy(xpath = "//span[contains(.,'Add Product')]")
	public WebElement ADDPRODUCT;

	@FindBy(id = "productName")
	public WebElement PRODUCTNAME;

	@FindBy(id = "taxabilityDriverCode")
	public WebElement TAXABILITYDRIVERCODE;

	@FindBy(xpath = "//label[contains(.,'Product Class')]/../span")
	public WebElement PRODUCTCLASS;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement SAVEPRODUCT;

	@FindBy(id = "partitionName")
	public WebElement PARTITIONNAME;

	@FindBy(id = "streetAddress")
	public WebElement STREETADDRESS;

	@FindBy(id = "divisionCode")
	public WebElement DIVISIONCODE;

	@FindBy(id = "city")
	public WebElement CITY;

	@FindBy(id = "departmentCode")
	public WebElement DEPARTMENTCODE;

	@FindBy(xpath = "//div[@class='Select-placeholder']")
	public WebElement STATE;

	@FindBy(id = "postalCode")
	public WebElement POSTALCODE;

	@FindBy(xpath = "//span[@class='Select-value-label']")
	public WebElement COUNTRY;

	@FindBy(xpath = "//span[contains(.,'Confirm')]")
	public WebElement CONFIRMDELETE;

	@FindBy(xpath = "//span[contains(.,'Select All')]")
	public WebElement SELECTALLEXEMPTIONTYPE;

	@FindBy(xpath = "//span[contains(.,'Clear All')]")
	public WebElement CLEARALLEXEMPTIONTYPE;

	@FindBy(xpath = "//span[contains(.,'Exemption Type')]")
	public WebElement EXEMTIONTYPETAB;

	@FindBy(xpath = "//span[contains(.,'Save')]")
	public WebElement SAVEBUTTON;

	@FindBy(xpath = "//span[contains(.,'Jurisdictions')]")
	public WebElement JURISDICTIONSTAB;

	@FindBy(xpath = "//a[@title='Dashboard']")
	public WebElement DASHBOARD;

	@FindBy(xpath = "//a[@title='Exemption Certificate Wizard']")
	public WebElement ECWIZARDTAB;;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement SETTINGSTAB;

	@FindBy(xpath = "//a[contains(.,'Launch Site')]")
	public WebElement LAUNCHSITE;

	@FindBy(xpath = "//a[contains(.,'Add New User')]")
	public WebElement ADDNEWUSER;

	@FindBy(xpath = "//h1[contains(.,'NEW USER ')]")
	public WebElement NEWUSERHEADER;

	@FindBy(id = "firstName")
	public WebElement FIRSTANAME;

	@FindBy(id = "LastName")
	public WebElement LASTNAME;

	@FindBy(id = "Email")
	public WebElement EMAIL;

	@FindBy(id = "phone")
	public WebElement PHONE;

	@FindBy(id = "NewUser_Role")
	public WebElement ROLE;

	@FindBy(xpath = "//input[@value='Send Invite']")
	public WebElement SENDINVITEBUTTON;

	@FindBy(xpath = "//p[3]/a")
	public WebElement REGISTRATIONLINK;

	@FindBy(id = "Username")
	public WebElement USERNAME;

	@FindBy(id = "Password")
	public WebElement PASSWORD;

	@FindBy(id = "ConfirmPassword")
	public WebElement CONFIRMPASSWORD;

	@FindBy(xpath = "//button[contains(.,'Complete')]")
	public WebElement COMPLETE;

	@FindBy(xpath = "//a[contains(.,'Login')]")
	public WebElement LOGIN;

	@FindBy(xpath = "//div[@class='section-action']/a[contains(.,'View All Users')]")
	public WebElement VIEWALLUSERS;

	@FindBy(xpath = "//div[@class='section-action']/a[contains(.,'View All Credentials')]")
	public WebElement VIEWALLCREDENTIALS;

	@FindBy(xpath = "//a[contains(.,'Create New Credential')]")
	public WebElement CREATENEWCREDENTIALS;

	@FindBy(id = "credential-usage")
	public WebElement CREDENTIALSUSAGE;

	@FindBy(id = "oauth-client-id")
	public WebElement CLIENTID;

	@FindBy(id = "credential-description")
	public WebElement CREDENTIALDESCRIPTION;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement SAVE;

	@FindBy(xpath = "//div[@class='ng-binding']")
	public WebElement NEWSECRET;

	@FindBy(xpath = "//div[2]/div/strong[contains(.,'URL:')]/../../div[2]")
	public WebElement URL;

	@FindBy(xpath = "//div[3]/div/strong[contains(.,'Authorization URL:')]/../../div[2]")
	public WebElement AUTHORIZATIONURL;

	@FindBy(xpath = "//span[contains(.,'Delete Credential and its Secrets')]")
	public WebElement DELETECREDENTIALSANDSECRETS;

	@FindBy(xpath = "//button[contains(.,'Add New Secret')]")
	public WebElement ADDNEWSECRET;

	@FindBy(xpath = "//i[@class='icon-section icon-section-light icon-delete pull-right'][1]")
	public WebElement DELETESECRETICON;

	@FindBy(xpath = "//ul[@class='unstyled ng-scope'][1]/li/div[contains(.,' You are about to delete a secret.  This secret will no longer be valid for use.  Are you sure you want to delete?')]/p/button[contains(.,'Delete')]")
	public WebElement DELETESECRETBUTTON;

	@FindBy(xpath = "//button[contains(.,'Close')]")
	public WebElement CLOSEBUTTON;

	@FindBy(xpath = "//a[contains(.,'Create New User')]")
	public WebElement CREATENEWUSERBUTTON;

	@FindBy(xpath = "//div[@class='section ng-scope']/descendant::div[contains(.,'No Credentials exist.')]")
	public WebElement NOCREDENTIALSEXIST;

	@FindBy(xpath = "//p/a[contains(.,'Delete')]")
	public WebElement DELETEUSER;

	@FindBy(xpath = "//strong[contains(.,'Deleted')]/../span")
	public WebElement DELETEDUSERCOUNT;

	@FindBy(xpath = "//strong[contains(.,'Success')]")
	public WebElement SUCCESS;

	@FindBy(xpath = "//label[contains(.,' I agree to the ')]")
	public WebElement IAGREETERMS;

	@FindBy(xpath = "//div/input[@id='oauth-client-id']/../div/span[contains(.,'Special characters are not allowed.')]")
	public WebElement CLIENTIDERROR;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	public WebElement CANCELBUTTON;

	@FindBy(xpath = "//span[contains(.,'Data Integrity')]")
	public WebElement DATAINTEGRITY;

	@FindBy(xpath = "//a[contains(.,'Launch Site')]")
	public WebElement DILAUNSITE;

	@FindBy(xpath = "//span[contains(.,'Global Compliance')]")
	public WebElement GLOBALCOMPLIANCE;

	@FindBy(xpath = "//a[contains(.,'Launch Site')]")
	public WebElement GCLAUNSITE;

	@FindBy(xpath = "//label[contains(.,'Canada')]/../div/div/span")
	public WebElement CANDANPLUS;

	@FindBy(xpath = "//label[contains(.,'United States')]/../div/div/span")
	public WebElement USPLUS;

	@FindBy(xpath = "//label[contains(.,'Alaska')]/../div/div/span")
	public WebElement ALASKAPLUS;

	@FindBy(xpath = "//label[contains(.,'Colorado')]/../div/div/span")
	public WebElement COLARADOPLUS;

	@FindBy(xpath = "//label[contains(.,'Louisiana')]/../div/div/span")
	public WebElement LOUISIANAPLUS;

	@FindBy(xpath = "//label[contains(.,'Exemption Certificate Manager')]/../div/select")
	public WebElement EXEMPTIONCERTIFICATEMANAGER;

	@FindBy(xpath = "//label[contains(.,'Vertex Cloud Indirect Tax')]/../div/select")
	public WebElement VERTEXCLOUDINDIRECTTAX;

	@FindBy(xpath = "//label[contains(.,'Account Management')]/../div/select")
	public WebElement ACCOUNTMANAGEMENT;

	@FindBy(xpath = "//h2[contains(.,'Exemption certificate wizard')]/../descendant::strong[contains(.,'Client Code:')]/../../div[2]")
	public WebElement CLIENTCODE;

}
