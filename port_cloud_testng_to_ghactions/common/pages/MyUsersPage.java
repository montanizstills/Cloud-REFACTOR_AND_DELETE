package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyUsersPage {

	@FindBy(xpath = "//a[@title='My Users']")
	public WebElement MYUSERSLINK;
	
	@FindBy(xpath = "//h1")
	public WebElement MYUSERSLABLE;
	
	@FindBy(xpath = "//a[contains(text(),'Create')]")
	public WebElement CREATEBUTTON;
	
	@FindBy(xpath = "//input[@id='NewUser_FirstName']")
	public WebElement FIRSTNAME;
	
	@FindBy(xpath = "//input[@id='NewUser_LastName']")
	public WebElement LASTNAME;
	
	@FindBy(xpath = "//input[@id='NewUser_Email']")
	public WebElement EMAIL;
	
	@FindBy(xpath = "//input[@id='NewUser_Phone']")
	public WebElement PHONE;
	
	@FindBy(xpath = "//select[@id='NewUser_Role']")
	public WebElement ROLE;
	
	@FindBy(xpath = "//button[contains(text(),'Send Invite')]")
	public WebElement SENDINVITEBUTTON;
	
	@FindBy(xpath = "//input[@id='Password']")
	public WebElement PASSWORD;
	
	@FindBy(xpath = "//input[@id='PasswordConfirm']")
	public WebElement PASSWORDCONFIRM;
	
	@FindBy(xpath = "//input[@id='AcceptedTerms']")
	public WebElement IAGREETC1;
	
	@FindBy(xpath = "//button[contains(text(),'Complete')]")
	public WebElement COMPLETEBUTTON;
	
	
	@FindBy(xpath = "//a[contains(@href,'/Users')]")
	public WebElement ViewAllUsers;

}
