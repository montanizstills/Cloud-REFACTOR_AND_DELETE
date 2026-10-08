package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WizardSetting {

	@FindBy(xpath = "//span[contains(.,'Wizard Settings')]")
	public WebElement WIZARDSETTINGS;

	@FindBy(xpath = "//span[contains(.,'Wizard Settings')]")
	public WebElement ALWAYS;

	@FindBy(xpath = "//span[contains(.,'Save')]")
	public WebElement SAVE;

	@FindBy(xpath = "//span[contains(.,'Wizard Settings')]")
	public WebElement WIZARDSETTINGSTAB;

	@FindBy(xpath = "//span[contains(.,'Certificate Search')]")
	public WebElement CERTIFICATESEARCHL;

	@FindBy(xpath = "//button[@type='submit']/div/span[contains(.,'Search')]")
	public WebElement SEARCHBUTTON;

	@FindBy(xpath = "//h3[contains(.,'Exemption Certificate Wizard Integration')]/../div//strong[contains(.,'Client ID:')]/../../div[2]")
	public WebElement GETCLIENTID;

	@FindBy(xpath = "//h3[contains(.,'Exemption Certificate Wizard Integration')]/../div//strong[contains(.,'Client Secret:')]/../../div[2]")
	public WebElement GETCLIENTSECRET;

	@FindBy(xpath = "//span[contains(.,'Settings')]")
	public WebElement SETTINGS;

	@FindBy(xpath = "//select")
	public WebElement PAGNATION100ROWS;
}
