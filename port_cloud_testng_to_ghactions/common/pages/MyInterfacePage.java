
package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyInterfacePage {

	@FindBy(xpath = "//a[@title='My Interfaces']")
	public WebElement MYINTERFACELINK;

	@FindBy(xpath = "//h1")
	public WebElement MYINTERFACESLABEL;

	@FindBy(xpath = "//input[@id='OSeriesUrl']")
	public WebElement ERPADAPTERURL;

	@FindBy(xpath = "//span[contains(.,'Trusted ID:')]/../span[2]")
	public WebElement TRUSTEDID;

	@FindBy(xpath = "//div[2]/div[1]/div[1]/div[1]/div[2]/div[2]")
	public WebElement SETTINGSCLNTNAME;

	@FindBy(xpath = "//tr/td/div[3]/a")
	public WebElement DOWNLOADLINK1;

	@FindBy(xpath = "//tr/td/div[4]/a")
	public WebElement DOWNLOADLINK2;

	@FindBy(xpath = "//tr/td/div[6]/a")
	public WebElement DOWNLOADLINK3;

	@FindBy(xpath = "//tr/td/div[7]/a")
	public WebElement DOWNLOADLINK4;

	@FindBy(xpath = "//span[contains(.,'Trusted ID:')]")
	public WebElement TRUSTEDIDLABEL;

	@FindBy(xpath = "//th[contains(.,'Connector Name')]")
	public WebElement CONNECTORNAME;

	@FindBy(xpath = "//th[contains(.,'ERP Connector URL')]")
	public WebElement ERPURL;

	@FindBy(xpath = "//th[contains(.,'Address Lookup URL')]")
	public WebElement LOOKUPURL;

	@FindBy(xpath = "//h2[contains(.,'Big Commerce Integration')]")
	public WebElement BIGCOMMERCEHEADING;

	@FindBy(xpath = "//p[contains(.,'The Vertex Cloud integration to Big Commerce provides a connection to Big Commerce Stores. Click the Integration Setup button below to continue.')]")
	public WebElement BIGCOMMERCETEXT;

	@FindBy(xpath = "//button[@ng-if='canSetupBigCommerce']")
	public WebElement BIGCOMMERCEBUTTON;

	@FindBy(xpath = "//h4[contains(.,'Big Commerce Integration Instances')]")
	public WebElement BIGCOMMINSTACESTEXT;

	@FindBy(xpath = "//div/span[contains(.,'Date Added')]")
	public WebElement DATEADDED;

	@FindBy(xpath = "//div/span[contains(.,'Vertex Cloud Company')]")
	public WebElement VERTEXCLOUDCOMPANY;

	@FindBy(xpath = "//div/span[contains(.,'Big Commerce Store Name / ID')]")
	public WebElement BIGCOMMSTORENAME;

	@FindBy(xpath = "//div/../../th[contains(.,'Action')]")
	public WebElement ACTION;

	@FindBy(xpath = "//a[contains(.,'RETURN TO CONNECTORS')]")
	public WebElement RETURNTOCONNECTORS;

	@FindBy(xpath = "//a[contains(.,'ADD CONNECTION')]")
	public WebElement ADDCONNECTION;

	@FindBy(xpath = "//h2[contains(.,'Big Commerce Configuration')]")
	public WebElement BIGCOMMERCECONFIG;

	@FindBy(xpath = "//h2[contains(.,'Store Information')]")
	public WebElement STOREINFORMATION;

	@FindBy(id = "vertex-company")
	public WebElement VERTEXCOMPANY;

	@FindBy(xpath = "//div[@ng-app='adapters']/div/div/div/a[contains(.,'Return to Instances')]")
	public WebElement RETURNTOINSTANCES;

	@FindBy(id = "store-name")
	public WebElement STORENAME;

	@FindBy(id = "store-id")
	public WebElement STOREID;

	@FindBy(id = "store-api-url")
	public WebElement STOREAPIURL;

	@FindBy(id = "client-id")
	public WebElement STORECLIENTID;

	@FindBy(id = "token")
	public WebElement TOKEN;

	@FindBy(id = "secret")
	public WebElement SECRET;

	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement CANCEL;

	@FindBy(xpath = "//button[contains(.,'Submit')]")
	public WebElement SUBMIT;

	@FindBy(xpath = "//strong[contains(.,'Error!')]/../../div[contains(.,'The following error was reported with this Big Commerce integration.')]")
	public WebElement ERRORMESSAGE;

	@FindBy(xpath = "//strong[contains(.,'Success!')]/../../div[contains(.,'Big Commerce integration instance saved successfully.')]")
	public WebElement SUCCESSMESSAGE;

	@FindBy(xpath = "//span[contains(.,'Delete Big Commerce connection')]")
	public WebElement DeletePopup;
}
