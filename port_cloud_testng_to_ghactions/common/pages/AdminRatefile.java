package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminRatefile {

	@FindBy(xpath = "//span[contains(.,'Dashboard')]")
	public WebElement DASHBORD;

	@FindBy(xpath = "//h2[contains(.,'United States Rate File')]")
	public WebElement UNITEDSTATERATEFILETEXT;

	@FindBy(xpath = "//h2[contains(.,'United States Rate File')]/../div/div[contains(.,'Select the month for which you would like to download rate file.')]")
	public WebElement RATEFILEHELPTXT;

	@FindBy(xpath = "//h2[contains(.,'United States Rate File')]/../div/div/div/select")
	public WebElement RATEDROPDOWN;

	@FindBy(xpath = "//button[contains(.,'Download')]")
	public WebElement DNDLOADBUTTON;

}
