package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailPage {
	public String url = "https://gmail.com";
	@FindBy(xpath = "//*[@id='Email']")

	public WebElement EMAILID;
	@FindBy(xpath = "//*[@id='next']")

	public WebElement NEXTBtn;
	@FindBy(xpath = "//*[@id='Passwd']")

	public WebElement Password;
	@FindBy(xpath = "//*[@id='signIn']")

	public WebElement SIGNin;
	@FindBy(xpath = "//class[@href='https://mail.google.com/mail/u/0/#label/Personal+Labels/SOC+Report']")

	public WebElement SOCLabel;
	@FindBy(xpath = "//*[@id=':ba']")

	public WebElement Move;
	@FindBy(xpath = "//*[@class='J-N-Jz']")

	public WebElement MarkRead;
}
