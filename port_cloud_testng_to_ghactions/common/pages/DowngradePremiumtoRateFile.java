package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DowngradePremiumtoRateFile {
	
	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement SETTINGSLINK;
	
	@FindBy(xpath = "//li/a[contains(text(),'Edit My Subscription')]")
	public WebElement EDITSUBSCRIPTION;
	
	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement SAVEBUTTON;
	
	@FindBy(xpath = "//h4[contains(text(),'Rate File')]")
	public WebElement RATEFILE;
	
	@FindBy(xpath = "//input[@name='Subscription.HasRateFile']")
	public WebElement RATEFILECHECKBOX;
	
	@FindBy(xpath = "//div[2]/button")
	public WebElement SAVEBTNCHNSCRIPTION;
	
}
