package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpgradeProToPremium {

	@FindBy(xpath = "//span[contains(text(),'Settings')]")

	public WebElement SETTINGSLINK;
	@FindBy(xpath = "//li/a[contains(text(),'Edit My Subscription')]")

	public WebElement EDITSUBSCRIPTION;
	@FindBy(xpath = "//button[contains(text(),'SAVE')]")

	public WebElement SAVEBUTTON;
	@FindBy(xpath = "//h4[contains(text(),'Premium')]")

	public WebElement PREMIUMSUB;
	@FindBy(xpath = "//button[contains(text(), 'Accept')]")

	public WebElement UPGRADEBUTTON;
	@FindBy(xpath = "//span[contains(text(),'Document Center')]")

	public WebElement DOCUMENTCENTRE;
	@FindBy(xpath = "//div[@class='alert alert-success ng-binding ng-scope']")

	public WebElement SUCCESSMSG;
	@FindBy(xpath = "//div/ul/li[text()='The provided password is incorrect']")

	public WebElement ERRORMSG;
	@FindBy(xpath = "//h3[contains(text(),'ATTENTION')]")

	public WebElement ATTENTIONALERT;
	@FindBy(xpath = "//p/strong[contains(text(),'You have selected to modify customer subscription, which may affect customer monthly subscription fee.')]/../strong[contains(text(),' By clicking ACCEPT, you agree to any subscription fee changes.')]")

	public WebElement SUBSCRIPTIONCHANGEMES;
	@FindBy(xpath = "//p/strong[contains(text(),'You have selected to modify customer subscription, which may affect customer monthly subscription fee.')]/../../p/strong[contains(.,' By clicking ACCEPT, you agree to any subscription fee changes, and understand customer need to add')]")

	public WebElement STNDRDSUBSCRIPTIONCHANGEMSG;
	@FindBy(xpath = "//strong[text()='Returns Only.']/../input")

	public WebElement RETURNSONLYRADIOBTN;
	@FindBy(id = "include-rate-file")

	public WebElement RATEFILERADIOBTN;
	@FindBy(xpath = "//strong[text()='Tax Calculations and Returns.']/../input")

	public WebElement CALRETURNSRADIOBTN;
	@FindBy(xpath = "//strong[text()='Taxability Impact Report.']/../input")

	public WebElement TAXIMPACTREPORT;

	@FindBy(xpath = "//strong[contains(.,'Premium: Tax Calculations and Returns')]")

	public WebElement UPGRADEPRETCR;

	@FindBy(xpath = "//strong[contains(.,'Premium: Tax Calculations and Returns')]")

	public WebElement DWNGRADEPRETPRORO;

	@FindBy(xpath = "//strong[contains(.,'( onboarding pending )')]")

	public WebElement ONBOARDPENDING;

	@FindBy(xpath = "//div[1]/div[2]/div/div/text[1]")

	public WebElement SUBSCRIPTIONCHANGEDOWNGRADEMSG;

	@FindBy(xpath = "//div[1]/div[2]/div/div/text[2]")

	public WebElement SUBSCRIPTIONCHANGEDOWNGRADERTRSMSG;

	@FindBy(xpath = "//strong[contains(.,'Premium: Returns Only')]")

	public WebElement UPGRADEPREROnly;

	@FindBy(xpath = "//div[1]/a[contains(text(),'Edit My Company')]")

	public WebElement EDITMYCOMPNAYLINK;
}
