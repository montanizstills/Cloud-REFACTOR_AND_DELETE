package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Oseries9 {

	@FindBy(id = "username")
	public WebElement USERNAME;

	@FindBy(id = "password")
	public WebElement PASSWORD;

	@FindBy(id = "Login_button")
	public WebElement LOGINBUTTON;

	@FindBy(id = "my-enterprise")
	public WebElement MYENTERPRISE;

	@FindBy(id = "my-enterprise/certificates")
	public WebElement CERTIFICATES;

	@FindBy(id = "search_button")
	public WebElement SEARCHBUTTON;

	@FindBy(id = "utilityLinkLogout_button")
	public WebElement LOGOUT;

}
