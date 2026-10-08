package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InlineHelpEditor {

	@FindBy(xpath = "//span[contains(.,'Help Editor')]")
	public WebElement InlineHelpEditorlink;

	@FindBy(xpath = "//label[contains(.,'Filing Period')]/span")
	public WebElement FILINGPERIOD;

	@FindBy(xpath = "//a[contains(.,'Cancel')]")
	public WebElement Cancelbutton;

	@FindBy(xpath = "//label[text()='Help Section']/../div/select")
	public WebElement HelpSection;

	@FindBy(xpath = "//textarea[@name='description']")
	public WebElement Discription;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement Save;

	@FindBy(xpath = "//span[contains(.,'Error Monitor')]")
	public WebElement ErrorMonitor;

	@FindBy(xpath = "//div[@class='popover-content'][text()='Testing']")
	public WebElement POPUPTYPE;

	@FindBy(xpath = "//a[contains(.,'Dashboard')]")
	public WebElement Dashboard;

	@FindBy(xpath = "//div[@class='popover-content'][contains(.,'Testing')]")
	public WebElement POPUPTYPE1;

	@FindBy(xpath = "//label[contains(.,'Title')]")
	public WebElement titlelable;

	@FindBy(xpath = "//label[contains(.,'Title')]")
	public WebElement Discriptionlable;

	@FindBy(xpath = "//input[@id='isVisible']")
	public WebElement isVisible;

	@FindBy(xpath = "//a[text()='Update Analysis']")
	public WebElement UPDATEANALYSIS;

	@FindBy(xpath = "//span[contains(.,'Saved!')]")
	public WebElement Savedtext;

	@FindBy(xpath = "//select[@id='inlinehelp-names']")
	public WebElement HelpSectionDropdown;

}
