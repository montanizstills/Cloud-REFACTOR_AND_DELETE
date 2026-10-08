package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminConfigLimit {

	@FindBy(xpath = "//button[contains(.,'View Config Limits')]")
	public WebElement VIEWLIMITS;

	@FindBy(xpath = "//td[contains(.,'Product')]/../td[2]")
	public WebElement PRODUCTSLIMIT;

	@FindBy(xpath = "//td[contains(.,'Mapping')]/../td[2]")
	public WebElement MAPPINGSLIMIT;

	@FindBy(xpath = "//td[contains(.,'Customer')]/../td[2]")
	public WebElement CUSTOMERLIMIT;

	@FindBy(xpath = "//td[contains(.,'Certificate')]/../td[2]")
	public WebElement CERTIFICATELIMIT;

	@FindBy(xpath = "//td[contains(.,'Mapping')]/../td/a[contains(.,'Edit')]")
	public WebElement MAPPINGSEDIT;

	@FindBy(xpath = "//td[contains(.,'Product')]/../td/a[contains(.,'Edit')]")
	public WebElement PRODUCTEDIT;

	@FindBy(xpath = "//td[contains(.,'Customer')]/../td/a[contains(.,'Edit')]")
	public WebElement CUSTOMEREDIT;

	@FindBy(xpath = "//td[contains(.,'Certificate')]/../td/a[contains(.,'Edit')]")
	public WebElement CERTIFICATEEDIT;

	@FindBy(xpath = "//td[contains(.,'Mapping')]/../td/input")
	public WebElement MAPPINGSTEXT;

	@FindBy(xpath = "//td[contains(.,'Product')]/../td/input")
	public WebElement PRODUCTTEXT;

	@FindBy(xpath = "//td[contains(.,'Customer')]/../td/input")
	public WebElement CUSTOMERTEXT;

	@FindBy(xpath = "//td[contains(.,'Certificate')]/../td/input")
	public WebElement CERTIFICATETEXT;

	@FindBy(xpath = "//a[@class='btn btn-danger']")
	public WebElement SVAEBUTTON;

	@FindBy(xpath = "//a[@class='btn btn-default']")
	public WebElement CANCELBUTTON;

	@FindBy(xpath = "//p/a")
	public WebElement CLOSEBUTTON;

	@FindBy(xpath = "//h3[contains(.,'Configuration Limits')]")
	public WebElement CONFIGURATIONLIMITSHEADER;

	@FindBy(xpath = "//td[contains(.,'Mapping')]/../td/a[contains(.,'Edit')][@class='btn btn-default']")
	public WebElement MAPPINGEDITHIDE;

	@FindBy(xpath = "//td[contains(.,'Product')]/../td/a[contains(.,'Edit')][@class='btn btn-default']")
	public WebElement PRODUCTEDITHIDE;

	@FindBy(xpath = "//td[contains(.,'Customer')]/../td/a[contains(.,'Edit')][@class='btn btn-default']")
	public WebElement CUSTOMEREDITHIDE;

	@FindBy(xpath = "//td[contains(.,'Certificate')]/../td/a[contains(.,'Edit')][@class='btn btn-default']")
	public WebElement CERTIFICATEEDITHIDE;

	@FindBy(xpath = "//div[2]/div[contains(.,'New limit was not saved. Value must be between 0 and 2147483642.')]")
	public WebElement VALIDATIONMESSAGE;

}
