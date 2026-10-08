package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SystemConfiguration {

	@FindBy(xpath = "//span[contains(.,'System Configuration')]")

	public WebElement SystemConfigurationLink;

	@FindBy(xpath = "//span[contains(.,'Name')]")

	public WebElement Namecolumn;

	@FindBy(xpath = "//span[contains(.,'Value')]")

	public WebElement Valuecolumn;

	@FindBy(xpath = "//th[contains(.,'Action')]")

	public WebElement Actioncolumn;

	@FindBy(xpath = "//input[@type='text']")

	public WebElement ValueDate;

	@FindBy(xpath = "//button[contains(.,'Update')]")

	public WebElement Updatebutton;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")

	public WebElement Cancelbutton;

}
