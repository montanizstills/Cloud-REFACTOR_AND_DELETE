package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ECWWizwardLogin {

	@FindBy(id = "username")
	public WebElement UserName;

	@FindBy(id = "password")
	public WebElement Password;

	@FindBy(xpath = "//button[contains(.,'Login')]")
	public WebElement Login;

	@FindBy(xpath = "//span[contains(.,'Setting')]")
	public WebElement Settings;

	@FindBy(xpath = "//button[contains(.,'Change User')]")
	public WebElement ChangeUserButton;

}
