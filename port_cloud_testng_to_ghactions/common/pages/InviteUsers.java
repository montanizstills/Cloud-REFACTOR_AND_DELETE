/*###################################################################################################
Name: BillingPage.java 
Description: Configuration Report page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/28/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InviteUsers {

	public String DELETEPOPUP_XPATH = "//p/a[contains(.,'Delete')]";

	@FindBy(xpath = "//span[contains(text(),'Invite Users')]")
	public WebElement INVITEUSERS;

	@FindBy(xpath = "//a[contains(text(),'Invite User')]")
	public WebElement INVITEUSERBTN;

	@FindBy(xpath = "//div/input[@class='search input-append ng-pristine ng-untouched ng-valid']")
	public WebElement SEARCHTXTBOX;

	@FindBy(xpath = "//li[@ng-if='data.numActive']/span[@class='badge badge-large ng-binding']")
	public WebElement AUSERSCOUNT;

	@FindBy(xpath = "//li[@ng-if='data.numPending']/span[@class='badge badge-large ng-binding']")
	public WebElement PUSERSCOUNT;

	@FindBy(xpath = "//li[@ng-if=' data.numDelete']/span[@class='badge badge-large ng-binding']")
	public WebElement DUSERSCOUNT;

	@FindBy(xpath = "//div/bootstrap-pagination")
	public WebElement PAGINATION;

	@FindBy(xpath = "//span[text()='First Name']")
	public WebElement FNCLMN;

	@FindBy(xpath = "//span[text()='Last Name']")
	public WebElement LNCLMN;

	@FindBy(xpath = "//span[text()='Email']")
	public WebElement EMAILCLMN;

	@FindBy(xpath = "//span[text()='Phone']")
	public WebElement PHONECLMN;

	@FindBy(xpath = "//span[text()='Created']")
	public WebElement CREATEDCLMN;

	@FindBy(xpath = "//span[text()='Last Login']")
	public WebElement LASTLGNCLMN;

	@FindBy(xpath = "//span[text()='Status']")
	public WebElement STATUSCLMN;

	@FindBy(xpath = "//span[text()='Role']")
	public WebElement ROLECLMN;

	@FindBy(xpath = "//th[contains(.,'Action')]")
	public WebElement ACTIONSCLMN;

	@FindBy(xpath = "//a[contains(text(),'Invite User')]")
	public WebElement INVITENEWUSER;

	@FindBy(xpath = "//a[contains(.,'Return to Users')]")
	public WebElement RETURNTOUSERS;

	@FindBy(xpath = "//div/button[contains(.,'Send Invite')]")
	public WebElement RSENDINVITEBTN;

	// Error Messages
	@FindBy(xpath = "//select[@name='Role']/../div/span[text()='This field is required.']")
	public WebElement ROLEERROR;

	@FindBy(xpath = "//input[@name='FirstName']/../div/span[text()='This field is required.']")
	public WebElement FNERROR;

	@FindBy(xpath = "//input[@name='LastName']/../div/span[text()='This field is required.']")
	public WebElement LNERROR;

	@FindBy(xpath = "//input[@name='Email']/../div/span[text()='This field is required.']")
	public WebElement EMAILERROR;

	@FindBy(xpath = "//input[@id='txtPhone']/../div/span[text()='This field is required.']")
	public WebElement PHNERROR;

	// Roles verification
	@FindBy(xpath = "//select[@name='Role']/option[text()='Billing Approval Admin']/../option[text()='Customer Admin']/../option[text()='System Admin']/../option[text()='System Admin Read Only']/../option[text()='System Approval Admin']/../option[text()='Treasury Admin']/../option[text()='Vertex Data Integrity Admin']")
	public WebElement ALLROLES;

	@FindBy(xpath = "//input[@class='search input-append ng-pristine ng-untouched ng-valid']")
	public WebElement SEARCHTXT;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td/a[text()='Delete']")
	public WebElement DELETE;

	@FindBy(xpath = "//p/a[contains(.,'Delete')]")
	public WebElement DELETEPOPUP;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td/a[text()='Reactivate']")
	public WebElement REACTIVATE;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td[contains(.,'Deleted')]")
	public WebElement DELETED;

	@FindBy(xpath = "//h3[contains(.,'Are you sure you want to delete this user?')]")
	public WebElement WARNINGPOPUP;

	@FindBy(xpath = "//a[contains(.,'Export')]")
	public WebElement INVITEEXPORT;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Client Name')]")
	public WebElement CLIENTNAME;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Client Number')]")
	public WebElement CLIENTNUMBER;

	@FindBy(xpath = "//tbody/tr[2]/td/a[text()='Unlock']")
	public WebElement Unlocklink;

	@FindBy(xpath = "//div[@class='alert alert-success ng-scope']/../div[contains(.,'User has been unlocked successfully')]")
	public WebElement Unlocksuccess;

}
