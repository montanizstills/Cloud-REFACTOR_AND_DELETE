/*###################################################################################################
Name: ConfigurePage.java 
Description: Professional User - Configure Page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoLiveVerify {

	@FindBy(css = "a.test-mode-button.btn.btn-primary")
	public WebElement GOLIVEBUTTON;

	@FindBy(xpath = "//button[contains(text(),'Yes, Go Live')]")
	public WebElement YESGOLIVEBUTTON;

	@FindBy(xpath = "//div[@class='modal-body golive-warning ng-scope']/p/button[contains(.,'Yes, Go Live')]")
	public WebElement SMOKE_YESGOLIVEBUTTON;

	@FindBy(xpath = "//a/strong[contains(text(),'No, return to test mode')]")
	public WebElement RETRUNFROMGOLIVE;

	@FindBy(xpath = "//a[contains(text(),'close')]")
	public WebElement CLOSE_INPROGRESS;

	@FindBy(xpath = "//a[contains(text(),'Check Status')]")
	public WebElement CHECKSTATUS;

	@FindBy(xpath = "//a[contains(text(),'Go to the Dashboard')]")
	public WebElement GOTODASHBOARD;

	@FindBy(xpath = "//li[contains(.,'Update Client Status')]/i[@class='icon icon-ok']")
	public WebElement OKICON_INPROGRESS;

	/*
	 * @FindBy(xpath=
	 * "//li[contains(.,'Applicable Discounts Already Taken Sent')]/i[@class='icon icon-ok']"
	 * )
	 * 
	 * public WebElement OKICON_INPROGRESS;
	 */

	@FindBy(xpath = "//div[@class='tree-folder-header']/div")
	public WebElement RATEFILETREE;

	@FindBy(xpath = "//div[@class='tree-folder-header']/div")
	public WebElement RATEFILELINK;

	@FindBy(xpath = "//tbody/tr/td/ul/li/a")
	public WebElement TAXABILITYLINK;

	@FindBy(id = "messageCount")
	public WebElement MESSAGEICON;

	@FindBy(xpath = "//p/strong[text()='no transaction reports']")
	public WebElement NOTRANSACTIONMSG;

	@FindBy(id = "btnGenerateReturns")
	public WebElement GENERATRETURN;

	@FindBy(xpath = "//button[contains(text(),'Reconciliation Report')]")
	public WebElement RECONCILIATION;

	@FindBy(xpath = "//button[contains(.,'Download All Returns')]")
	public WebElement DownloadAllReturns;

	@FindBy(xpath = "//table[@id='adjustmentFilesTable']/tbody/tr[1]")
	public WebElement UPLADJROW;

	@FindBy(xpath = "//ul/li[1]/span[@class='map-legend-registered']")
	public WebElement STATESURREGISTEDIN;

	@FindBy(xpath = "//ul/li[2]/span[@class='map-legend-selected']")
	public WebElement CURRENTLYSELECTEDSTATE;

	@FindBy(xpath = "//a[@title='Dashboard']")
	public WebElement DASHBOARD;

	@FindBy(xpath = "//li[contains(.,'Update Transactions Filter')]")
	public WebElement UPDATEFILTER;

	@FindBy(xpath = "//li[contains(.,'Purge Transactions')]")
	public WebElement PURGETRANS;

	@FindBy(xpath = "//li[contains(.,'Update Reports Filter')]")
	public WebElement UPDATEREPORTS;

	@FindBy(xpath = "//li[contains(.,'Purge Reports')]")
	public WebElement PURGEREPORTS;

	@FindBy(xpath = "//li[contains(.,'Artifact Zip and Purge')]")
	public WebElement ARTIFACTZIP;

	@FindBy(xpath = "//li[contains(.,'Returns Database Drop')]")
	public WebElement RETURNSDBDROP;

	@FindBy(xpath = "//li[contains(.,'Returns Database Restore From Gold Copy')]")
	public WebElement RETURNSDBGOLD;

	@FindBy(xpath = "//li[contains(.,'Returns Configuration Push')]")
	public WebElement RETUNRSCONFIGPUSH;

	@FindBy(xpath = "//li[contains(.,'Update Client Status')]")
	public WebElement UPDATECLIENTSTATUS;

	@FindBy(xpath = "//td/a[text()='Go Live']")
	public WebElement GOLIVEINDMIN;

	@FindBy(xpath = "//div[@class='modal hide fade warning in']/div/h4[contains(.,'Go Live Error')]")
	public WebElement GOLIVEERROR;

	@FindBy(xpath = "//div[@class='modal hide fade warning in']/div/div/a/strong[text()='Close']")
	public WebElement GOLIVERETURNSERROR;

	@FindBy(xpath = "//h3[contains(.,'In Progress')]/../button[@class='close']")
	public WebElement CLOSE;

	public final String YESGOLIVEBUTTON_XPATH = "//div[@class='modal hide fade warning in']/div/p/button[contains(text(),'Yes, Go Live')]";

	public final String CLOSE_XPATH = "//strong[contains(.,'Close')]";

	public final String UPLADJROW_XPATH = "//table[@id='adjustmentFilesTable']/tbody/tr[1]";

	@FindBy(xpath = "//strong[contains(.,'There are currently processes running that need to be completed before you can go live.')]")
	public WebElement GOLIVEERRORPROCESS;

	@FindBy(xpath = "//a[contains(.,'Ok')]")
	public WebElement GOLIVEOK;

	@FindBy(xpath = "//label[contains(.,'Company Status')]/../input")
	public WebElement Companystatus;

	@FindBy(xpath = "//label[contains(.,'Company')]/../div/select")
	public WebElement Companytext;

	@FindBy(xpath = "//tr[2]/td/a[text()='Delete']")
	public WebElement DELETE;

}