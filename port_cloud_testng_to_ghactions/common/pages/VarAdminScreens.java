package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class VarAdminScreens {

	@FindBy(xpath = "//div[@class='left-col nav-col']/div/ul/li/a/span[contains(text(),'My Customers')]")

	public WebElement MYCUSTOMERSLINK;
	@FindBy(xpath = "//div[@class='pull-right ']/div/input[@type='text']")

	public WebElement QUICKSEARCH;
	@FindBy(xpath = "//input[@value='Export']")

	public WebElement EXPORT;
	@FindBy(xpath = "//table[@id='var-customers']/thead/tr/th[1]/div[contains(text(),'Name')]")

	public WebElement GRIDNAME;
	@FindBy(xpath = "//table[@id='var-customers']/thead/tr/th[2]/div[contains(text(),'Address')]")

	public WebElement GRIDADDRESS;
	@FindBy(xpath = "//table[@id='var-customers']/thead/tr/th[3]/div[contains(text(),'City')]")

	public WebElement GRIDCITY;
	@FindBy(xpath = "//table[@id='var-customers']/thead/tr/th[4]/div[contains(text(),'State')]")

	public WebElement GRIDSTATE;
	@FindBy(xpath = "//table[@id='var-customers']/thead/tr/th[5]/div[contains(text(),'Phone')]")

	public WebElement GRIDPHONE;
	@FindBy(xpath = "//table[@id='var-customers']/thead/tr/th[6]/div[contains(text(),'Status')]")

	public WebElement GRIDSTATUS;
	@FindBy(xpath = "//table[@id='var-customers']/thead/tr/th[7]/div[contains(text(),'Subscription')]")

	public WebElement GRIDSUBSCRIPTION;
	@FindBy(xpath = "//table[@id='var-customers']/thead/tr/th[8]/div[contains(text(),'Transactions')]")

	public WebElement GRIDTRANSACTIONS;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr[1]/td[8]/a")

	public WebElement GRIDTRANSACTIONSHOW;
	@FindBy(xpath = "//table[@id='var-customers']/thead/tr/th[9]/div[contains(text(),'Actions')]")

	public WebElement GRIDACTIONS;
	@FindBy(xpath = "//table[@id='var-customers']/thead/tr/th[10]/div[contains(text(),'User(s)')]")

	public WebElement GRIDUSERS;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr[1]/td[10]/a/i")

	public WebElement GRIDPLUSICON;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr[2]/td/table/thead/tr/th[contains(text(),'First')]")

	public WebElement SUBGRIDFIRST;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr[2]/td/table/thead/tr/th[2][contains(text(),'Last')]")

	public WebElement SUBGRIDLAST;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr[2]/td/table/thead/tr/th[contains(text(),'Email')]")

	public WebElement SUBGRIDEMAIL;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr[2]/td/table/thead/tr/th[contains(text(),'Phone')]")

	public WebElement SUBGRIDPHONE;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr[2]/td/table/thead/tr/th[contains(text(),'Created')]")

	public WebElement SUBGRIDCREATED;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr[2]/td/table/thead/tr/th[6][contains(text(),'Last')]")

	public WebElement SUBGRIDLASTLOGIN;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr[2]/td/table/thead/tr/th[contains(text(),'Status')]")

	public WebElement SUBGRIDSTATUS;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr[2]/td/table/thead/tr/th[contains(text(),'Role')]")

	public WebElement SUBGRIDROLE;
	@FindBy(xpath = "//table[@id='var-customers']/tbody/tr/td[10]/a/i[@class='icon icon-minus icon-blue']")

	public WebElement MINUSSYMBOL;
	@FindBy(xpath = "//div[@class='left-col nav-col']/div/ul/li/a/span[contains(text(),'Lookup Tax Rates')]")

	public WebElement LOOKUPTAXRATESLINK;
	@FindBy(xpath = "//div[@class='left-col nav-col']/div/ul/li/a/span[contains(text(),'Marketing ToolKit')]")

	public WebElement MARKETINGTOOLKITLINK;
	@FindBy(xpath = "//span[contains(text(),'Settings')]")

	public WebElement SETTINGSLINK;
	@FindBy(xpath = "//a[contains(text(),'Add New User')]")

	public WebElement ADDNEWUSER;
	@FindBy(xpath = "//h1")

	public WebElement SETTINGSANDCUSTOMERSTITLE;
	@FindBy(xpath = "//a[contains(text(),'Nunc elementum quam vitae felis dictum')]")

	public WebElement TEXTWITHLINK;
	@FindBy(xpath = "//div[@class='row-fluid']/h2[1]")

	public WebElement MYCOMPANYLABLE;
	@FindBy(xpath = "//div[@class='span12']/h2[1]")

	public WebElement MYUSERSLABLE;
	@FindBy(xpath = "//div[2]/div[@class='span3']/p[1]")

	public WebElement NAMEFIELD;
	@FindBy(xpath = "//div[3]/div[@class='span3']/p[1]")

	public WebElement ADDRESS;
	@FindBy(xpath = "//div[4]/div[@class='span3']/p[1]")

	public WebElement PHONE;
	@FindBy(xpath = "//div/a[contains(text(),'View All Users')]")

	public WebElement SETVIEWALLUSERS;
	@FindBy(xpath = "//div/a[contains(text(),'View All Users')]")

	public WebElement VIEWALLUSERS;
	@FindBy(xpath = "(//a[contains(text(),'View All Users')])[2]")

	public WebElement VIEWALLUSERSBUTTON;
	@FindBy(xpath = "//div[@class='section']/h1[contains(text(),'My Users')]")

	public WebElement MYUSERSTITLE;
	@FindBy(xpath = "//li[@data-bind='if: numActive']/span[@class='badge badge-large']")

	public WebElement ACTIVEUSERSCOUNT;
	@FindBy(xpath = "//a[contains(text(),'Back')]")

	public WebElement BACKBUTTON;
	@FindBy(xpath = "//input[@value='Export']")

	public WebElement EXPORTBUTTON;
	@FindBy(xpath = "//li[@data-bind='if: numPending']/span[@class='badge badge-large']")

	public WebElement PENDINGUSERSCOUNT;
	@FindBy(xpath = "//li[@data-bind='if: numDelete']/span[@class='badge badge-large']")

	public WebElement DELETEDUSERSCOUNT;
	@FindBy(xpath = "//a[@title='Invite Users']")

	public WebElement INVITEUSER;
	@FindBy(xpath = "//a[contains(text(),'Invite User')]")

	public WebElement ADMININVITE;
	@FindBy(xpath = "//button[@type='submit']")

	public WebElement CONTINUE;
	@FindBy(xpath = "//input[@id='NewUser_FirstName']")

	public WebElement NEWUSERFIRSTNAME;
	@FindBy(xpath = "//input[@id='NewUser_LastName']")

	public WebElement NEWUSERLASTNAME;
	@FindBy(xpath = "//input[@id='NewUser_Email']")

	public WebElement NEWUSEREMAIL;
	@FindBy(xpath = "//input[@id='NewUser_Phone']")

	public WebElement NEWUSERPHONE;
	@FindBy(xpath = "//select[@id='NewUser_Role']")

	public WebElement NEWUSERROLE;
	@FindBy(xpath = "//a[contains(text(),'Back  »')]")

	public WebElement NEWUSERBACK;
	@FindBy(xpath = "//p[3]/a")

	public WebElement EMAILVERIFYLINK;
	@FindBy(xpath = "//input[@id='UserName']")

	public WebElement USERNAME;
	@FindBy(xpath = "//input[@id='Password']")

	public WebElement NEWPASSWORD;
	@FindBy(xpath = "//input[@id='PasswordConfirm']")

	public WebElement NEWPASSWORDCONFIRM;
	@FindBy(xpath = "//input[@id='AcceptedTerms']")

	public WebElement ACCEPTEDTERMS;
	@FindBy(xpath = "//button[contains(text(),Complete)]")

	public WebElement NEWUSERCOMPLETE;
	@FindBy(xpath = "//a[contains(@href, '#configurationNotice1')]")

	public WebElement DELETEBUTTON;
	@FindBy(xpath = "//a[contains(text(),'Edit')]")

	public WebElement EDITLINK;
	@FindBy(xpath = "//a[contains(text(),'Back to Settings')]")

	public WebElement BACKTOSETTINGS;
	@FindBy(xpath = "//a[contains(text(),'Cancel')]")

	public WebElement CANCELBUTTON;
	@FindBy(xpath = "//input[@id='LastName']")

	public WebElement ADMINLASTNAME;
	@FindBy(xpath = "//input[@id='Email']")

	public WebElement ADMINEMAIL;
	@FindBy(xpath = "//input[@id='firstName']")

	public WebElement ADMINFIRSTNAME;
	@FindBy(xpath = "//input[@id='phone']")

	public WebElement ADMINPHONE;
	@FindBy(xpath = "//select[@id='role']")

	public WebElement ROLE;
	@FindBy(xpath = "//form[@id='createForm']/div/div/ul/li[1]")

	public WebElement FIRSTNAMEFIELDLABLE;
	@FindBy(xpath = "//form[@id='createForm']/div/div/ul/li[2]")

	public WebElement LASTNAMEFIELDLABLE;
	@FindBy(xpath = "//form[@id='createForm']/div/div/ul/li[3]")

	public WebElement PHONEFIELDLABLE;
	@FindBy(xpath = "//form[@id='createForm']/div/div/ul/li[4]")

	public WebElement EMAILFIELDLABLE;
	@FindBy(xpath = "//div/a[contains(text(),'Return to Settings')]")

	public WebElement RETURNTOSETTINGS;
	@FindBy(xpath = "//a/span[contains(text(),'Marketing ToolKit')]")

	public WebElement MARKETINGTOOLKIT;
	@FindBy(xpath = "//h1[contains(text(),'Marketing Toolkit')]")

	public WebElement MARKETINGTOOLKITTITLE;
	@FindBy(xpath = "//p[contains(text(),'Marketing Toolkit Description')]")

	public WebElement MARKETINGTOOLKITDESCRIPTION;
	@FindBy(xpath = "//h2[contains(text(),'Select Marketing Materials To Download')]")

	public WebElement MARKETINGMATERIALSDOWNLOAD;
	@FindBy(xpath = "//select[@class='input-block-level']")

	public WebElement FILTERMATERIALSTYPE;
	@FindBy(xpath = "//h3[contains(text(),'Pricing Calculator')]")

	public WebElement PRICINGCALCULATORTITLE;
	@FindBy(xpath = "//P[contains(text(),'The pricing calculator will help you determine the cost of Vertex SMB for your customers.')]")

	public WebElement PRICINGCALCULATOR;
	@FindBy(xpath = "//a[contains(text(),'View the Pricing Calculator')]")

	public WebElement VIEWPRICINGCALCULATORLINK;
	@FindBy(xpath = "//img[@src='/Content/images/icon-calculator.png']")

	public WebElement CALCULATORSYMBOL;
	@FindBy(xpath = "//h3[contains(text(),'Marketing tools just for you')]")

	public WebElement MARKETINGTOOLSJUSTFORYOU;
	@FindBy(xpath = "//p[contains(text(),'We have selected materials that you might find most useful.')]")

	public WebElement SELECTMATERIALSTEXT;
	@FindBy(xpath = "//div[contains(text(),'There are no files to display.')]")

	public WebElement NOFILESTODISPLAY;
	@FindBy(xpath = "//a[contains(text(),'New Microsoft Office Word Document')]")

	public WebElement BROCHURESLINK;
	@FindBy(xpath = "//a[contains(text(),'New Microsoft Office PowerPoint Presentation')]")

	public WebElement POWERPOINTLINK;
	@FindBy(xpath = "//a[contains(text(),'New Microsoft Office Excel Worksheet')]")

	public WebElement SPREADSHEETLINK1;
	@FindBy(xpath = "//a[contains(text(),'Vertex O Series v6 Disk Space Estimator_0')]")

	public WebElement SPREADSHEETLINK2;
	@FindBy(xpath = "//a[contains(text(),'O Series V6 Configuration')]")

	public WebElement PDF1;
	@FindBy(xpath = "//a[contains(text(),'OSeries v6 Installation')]")

	public WebElement PDF2;
	@FindBy(xpath = "//a[contains(text(),'Vertex O Series v6 Batch Client Interface')]")

	public WebElement PDF3;
	@FindBy(xpath = "//a[contains(text(),'Vertex OSeries v6 Administration_0')]")

	public WebElement PDF4;
	@FindBy(xpath = "//a[contains(text(),'Vertex OSeries v6 Planning_0')]")

	public WebElement PDF5;
	@FindBy(xpath = "//a[contains(text(),'Demo Movie')]")

	public WebElement VIDEOFILE;
	@FindBy(xpath = "//h4")

	public WebElement USERREACTIVATED;
	@FindBy(xpath = "//a[@class='ng-scope'][contains(text(),'Edit My Company')]")

	public WebElement EDITMYCOMPNAYLINK;
	
	@FindBy(xpath = "//div[1]/a[contains(text(),'Edit')]")
	public WebElement EDITLINKINSETTINGS;
	
	@FindBy(xpath = "//input[@id='name']")
	public WebElement NAME;
	
	@FindBy(xpath = "//input[@id='txtPhone']")
	public WebElement COMPANYPHONE;
	
	@FindBy(xpath = "//h2[@class='h1'][contains(.,'Company Information')]")
	public WebElement Company_Information;
	
	@FindBy(xpath = "//strong[contains(.,'Description:')]")
	public WebElement DESCRIPTION;
	
	@FindBy(xpath = "(//div[@class='span7 ng-binding'])[2]")
	public WebElement DESCRIPTIONTEXT;
	
	@FindBy(xpath = "//label[@for='description']")
	public WebElement LabelDESCRIPTION;
	
	@FindBy(xpath = "//input[contains(@id,'description')]")
	public WebElement DESCRIPTIONTEXTBOX;
	
	@FindBy(xpath = "//select[contains(@id,'SelectedTenantId')]")
	public WebElement Available_Clients;
	
	
	@FindBy(xpath = "//input[@name='phone']")

	public WebElement ForiegnPhone;
	@FindBy(xpath = "//input[@id='address1']")

	public WebElement ADDRESS1;
	@FindBy(xpath = "//input[@id='address2']")

	public WebElement ADDRESS2;
	@FindBy(xpath = "//input[@id='txtCity']")

	public WebElement CITY;
	@FindBy(xpath = "//select[@name='statecode']")

	public WebElement STATE;
	@FindBy(xpath = "//input[@id='txtZip']")

	public WebElement ZIP_CODE;
	@FindBy(xpath = "//button[contains(text(),'Save')]")

	public WebElement SAVE;
	@FindBy(xpath = "//a[contains(text(),'Cancel')]")

	public WebElement CANCEL;
	@FindBy(xpath = "//a[contains(text(),'Return to Settings')]")

	public WebElement COMPANYRETURNTOSETTINGS;
	@FindBy(xpath = "//div[@class='right-col dashboard-col']/div[contains(.,'Company information has been updated.')]")

	public WebElement EDITCOMPANYUPDATEDMESSAGE;
	@FindBy(xpath = "//span[contains(text(),'An email has been sent to the Customer.')]")

	public WebElement RESENDINVITATIONMESSAGE;
	@FindBy(xpath = "//a[contains(text(),'INVITE CUSTOMER')]")

	public WebElement INVITECUSTOMERS;
	@FindBy(xpath = "//input[@id='FirstName']")

	public WebElement CUSTOMERFIRSTNAME;
	@FindBy(xpath = "//input[@id='LastName']")

	public WebElement CUSTOMERLASTNAME;
	@FindBy(xpath = "//input[@id='Email']")

	public WebElement CUSTOMEREMAIL;
	@FindBy(xpath = "//input[@value='Send Invite']")

	public WebElement CUSTOMERSENDINVITE;
	@FindBy(xpath = "//a[contains(text(),'Cancel')]")

	public WebElement CUSTOMERCANCEL;
	@FindBy(xpath = "//h2")

	public WebElement INVITECUSTOMERS_TITLE;
	@FindBy(xpath = "//div[@class='section']/a[contains(text(),'Return to My Customers')]")

	public WebElement CUSTOMERSRETURNBOTTOM;
	@FindBy(xpath = "//div[@class='section header-block']/a[contains(text(),'Return to My Customers')]")

	public WebElement CUSTOMERSRETURNTOP;
	@FindBy(xpath = "//ul/li[contains(text(),'The Customer Name field is required.')]")

	public WebElement CLIENTNAME;
	@FindBy(xpath = "//ul/li[contains(text(),'The First Name field is required.')]")

	public WebElement FIRSTNAME;
	@FindBy(xpath = "//ul/li[contains(text(),'The Last Name field is required.')]")

	public WebElement LASTNAME;
	@FindBy(xpath = "//ul/li[contains(text(),'The Email field is required.')]")

	public WebElement EMAIL;
	@FindBy(xpath = "//ul/li[contains(text(),'The Phone Number field is required.')]")

	public WebElement PHONETEXT;
	@FindBy(xpath = "//ul/li[contains(text(),'The Street Address 1 field is required.')]")

	public WebElement ADDRESS1TEXT;
	@FindBy(xpath = "//ul/li[contains(text(),'City/Municipality is required')]")

	public WebElement CITY_MUN;
	@FindBy(xpath = "//ul/li[contains(text(),'State/Province is required')]")

	public WebElement STATETEXT;
	@FindBy(xpath = "//ul/li[contains(text(),'Postal Code is required')]")

	public WebElement ZIPTEXT;
	@FindBy(xpath = "//h2[contains(text(),'Customer created successfully!')]")

	public WebElement CUSTOMERSCREATED;
	@FindBy(xpath = "//p[contains(text(),'A new customer has been created successfully.  An email has been sent to  ')]")

	public WebElement EMAILLINKVERIFY;
	@FindBy(xpath = "//a[contains(text(),'Back to Customer »')]")

	public WebElement BACKTOCUSTOMERS;
	@FindBy(xpath = "//li/a[contains(text(),'Edit My Company')]")

	public WebElement EDITCOMPANYSUBLINK;
	@FindBy(xpath = "//table/thead/tr/th/a[contains(text(),'Name')]")

	public WebElement GRIDNAMEFIELD;
	@FindBy(xpath = "//a[contains(text(),'Create')]")

	public WebElement CREATEBUTTON;
	@FindBy(xpath = "//span/i[@class='icon-info icon-small']")

	public WebElement MYUSERSICON;
	@FindBy(xpath = "//div[@class='popover-content']")

	public WebElement MYUSERSICONTEXT;
	@FindBy(xpath = "//a[@class='icon-info icon-small']")

	public WebElement STATUSICON;
	@FindBy(xpath = "//div[6]/div[@class='popover-content']")

	public WebElement STATUSICONTEXT;
	@FindBy(xpath = "//a[contains(text(),'Edit My Subscription')]")

	public WebElement EDITSUBSCRIPTION;
	@FindBy(xpath = "//a[@title='Transaction Report']")

	public WebElement TRANSACTIONREPORTLINK;
	@FindBy(xpath = "//h1[text()='Settings']")

	public WebElement SETTINGSLABEL;
	@FindBy(xpath = "//div[@class='span12']/a[contains(text(),'Return to Settings')]")

	public WebElement RETRNSETNGSBTM;
	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")

	public WebElement DASHBOARD;
	@FindBy(xpath = "//th[contains(.,'Transactions')]")

	public WebElement TRANSCTION;
	@FindBy(xpath = "//div[2]/table/tbody/tr/td/strong")

	public WebElement TRANSCOUNT;
	@FindBy(xpath = "//label[contains(.,'Zip/Postal Code*')]/../div/div/span[contains(.,'This field is required.')]")

	public WebElement ZIPFIELDISREQUIRED;

	// R4.1 xpaths

	@FindBy(xpath = "//input[@id='tbPhone']")

	public WebElement ICPHONE;
	@FindBy(xpath = "//input[@id='tbAddress1']")

	public WebElement ICADDRESS1;
	@FindBy(xpath = "//input[@id='tbAddress2']")

	public WebElement ICADDRESS2;
	@FindBy(xpath = "//input[@id='tbCity']")

	public WebElement ICCITY;
	@FindBy(xpath = "//select[@id='ddlState']")

	public WebElement ICSTATE;
	@FindBy(xpath = "//input[@id='tbZipCpde']")

	public WebElement ICZIPCODE;
	@FindBy(xpath = "//select[@id='ddlSubscription']")

	public WebElement ICSUBSCRIPTION;
	@FindBy(xpath = "//select[@id='ddlHasRataeFile']")

	public WebElement ICRATEFILE;
	@FindBy(xpath = "//div[@class='span6']/input[@name='__RequestVerificationToken']")

	public WebElement REQUESTVERIFICATIONTOKEN;
	@FindBy(xpath = "pan[contains(text(),'An error has occurred.')]")

	public WebElement ANTIFOREIGNERROR;

	@FindBy(xpath = "//a[@class='Video-close']")

	public WebElement VIDEOCLOSE;
	@FindBy(xpath = "//div/a[contains(.,'View All Connectors')]")

	public WebElement VIWALLCONNECTORS;
	@FindBy(xpath = "//a[contains(.,'Update Analysis')]")

	public WebElement UPDATEANALYSIS;

	@FindBy(name = "statecode")

	public WebElement ForeignStateName;

	@FindBy(id = "country")

	public WebElement CountryDropDown;

	@FindBy(xpath = "//a[contains(.,'Export')]")

	public WebElement EXPORTLINK;

	@FindBys({ @FindBy(xpath = "//select[@id='country']/option"), })
	public List<WebElement> EditCompanyCountries;

	@FindBy(xpath = "//div/input[@id='name']/../div[contains(.,'Special characters are not allowed as a first letter.')]")

	public WebElement InvaidClientName;

	@FindBy(xpath = "//select[@id='user-role']")

	public WebElement USERROLE;

	@FindBy(xpath = "//div/input[@id='phone']/../div/span[contains(.,'This field is required')]")

	public WebElement PHONEERROR;

	@FindBy(xpath = "//div/input[@id='txtZip']/../div/span[contains(.,'This field is required.')]")

	public WebElement ERRORZIPCODE;

}
