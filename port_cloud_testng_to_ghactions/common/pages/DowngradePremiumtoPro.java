package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DowngradePremiumtoPro {

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement SETTINGSLINK;
	
	@FindBy(xpath = "//li/a[contains(text(),'Edit My Subscription')]")
	public WebElement EDITSUBSCRIPTION;
	
	@FindBy(xpath = "//button[contains(text(),'SAVE')]")
	public WebElement SAVEBUTTON;
	
	@FindBy(xpath = "//h4[contains(text(),'Professional')]")
	public WebElement PROFESSIONAL;
	
	@FindBy(xpath = "//button[contains(text(), 'ACCEPT')]")
	public WebElement ACCEPTALERT;
	
	@FindBy(xpath = "//h3[contains(text(),'ATTENTION')]")
	public WebElement ATTENTIONALERT;
	
	@FindBy(xpath = "//p[contains(text(),'You have selected to modify your subscription, which may affect your monthly subscription fee.  By clicking ACCEPT, you agree to any subscription fee changes.')]")
	public WebElement SUBSCRIPTIONCHANGEMES;
	
	@FindBy(xpath = "//div/ul/li[text()='The provided password is incorrect']")
	public WebElement ERRORMSG;
	
	@FindBy(xpath = "//a[@id='cancel-go-back']")
	public WebElement CANCEL;

}
