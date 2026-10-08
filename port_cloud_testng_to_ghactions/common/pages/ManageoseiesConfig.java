package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageoseiesConfig {

	@FindBy(xpath = "//a[@title='Manage O-Series Config']")
	public WebElement Manageoseriesconfiglik;

	@FindBy(xpath = "//h1[contains(.,'Manage Lower Level Exception Support')]")
	public WebElement headerText;

	@FindBy(xpath = "//div/input/../../div/label[contains(text(),'Country')]/../input")
	public WebElement countryinput;

	@FindBy(xpath = "//div/input/../../div/label[contains(text(),'Jurisdiction')]/../input")
	public WebElement Jurisdictioninput;

	@FindBy(xpath = "//div/input/../../div/label[contains(text(),'Enable Lower Level Exceptions')]/../input")
	public WebElement enablelljtext;

	@FindBy(xpath = "//a[contains(.,'Filter')]")
	public WebElement filtertext;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Country')]")
	public WebElement countrycloumn;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Jurisdiction')]")
	public WebElement jurisdictioncolumn;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Enable Lower Level Exceptions')]")
	public WebElement enablelljcolumn;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Comment')]")
	public WebElement commentcolumn;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Actions')]")
	public WebElement Actionscolumn;

	@FindBy(xpath = "//input[@ng-model='config.lowerLevelExceptionsEnabled']")
	public WebElement EnablelowlevelException;

	@FindBy(xpath = "//input[@ng-model='config.currentComment']")
	public WebElement COMMENT;

	@FindBy(xpath = "//button[contains(.,'Update')]")
	public WebElement UPDATE;

	@FindBy(xpath = "//table/tbody/tr[2]/td/input[@disabled='disabled']")
	public WebElement Checkboxdisabled;

}
