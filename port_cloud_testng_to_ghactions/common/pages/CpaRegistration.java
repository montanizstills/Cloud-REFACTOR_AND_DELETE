package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CpaRegistration {

	@FindBy(xpath = "//input[@id='firstName']")
	public WebElement CPA_FIRSTNAME;
	
	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement CPA_LASTNAME;
	
	@FindBy(xpath = "//input[@id='email']")
	public WebElement CPA_EMAIL;
	
	@FindBy(xpath = "//input[@id='phoneNumber']")
	public WebElement CPA_PHONE;
	
	@FindBy(xpath = "//input[@id='userName']")
	public WebElement CPA_USERNAME;
	
	@FindBy(xpath = "//input[@id='password']")
	public WebElement CPA_PASSWORD;
	
	@FindBy(xpath = "//input[@id='passwordConfirm']")
	public WebElement CPA_CONFIRMPASSWORD;
	
	@FindBy(xpath = "//input[@id='companyName']")
	public WebElement CPA_ACCOUNTFIRMNAME;
	
	@FindBy(xpath = "//select[@id='companyCountry']")
	public WebElement CPA_COUNTRY;
	
	@FindBy(xpath = "//input[@id='companyAddress1']")
	public WebElement CPA_ADDRESS1;
	
	@FindBy(xpath = "//input[@id='companyCity']")
	public WebElement CPA_CITY;
	
	@FindBy(xpath = "//select[@id='companyState']")
	public WebElement CPA_STATE;
	
	@FindBy(xpath = "//input[@id='companyPostalCode']")
	public WebElement CPA_ZIP;
	
	@FindBy(xpath = "//input[@id='agreedToSA']")
	public WebElement CPA_AGREEDTOSA;
	
	@FindBy(xpath = "//div[@class='recaptcha-checkbox-checkmark']")
	public WebElement CPA_SECURITY;
	
	@FindBy(xpath = "//input[@id='agreedToToS']")
	public WebElement CPA_AGREEDTOS;
	
	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	public WebElement CPA_CONTINUE;
	
	@FindBy(xpath = "//a[contains(text(),'Continue')]")
	public WebElement CPA_CONTINUELINK;
	
	@FindBy(xpath = "//button[contains(text(),'BPO as Active')]")
	public WebElement CPA_BYPASSBPO;
	
	@FindBy(xpath = "//a[@class='btn btn-primary']")
	public WebElement CPA_CONTINUEPOPUP;
	
	@FindBy(xpath = "//input[@id='Text1']")
	public WebElement CPA_USERNAMELOGON;
	
	@FindBy(xpath = "//input[@id='password1']")
	public WebElement CPA_PASSWORDLOGON;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement CPA_LOGIN;
	
	@FindBy(xpath = "//button[contains(text(),'Referral Only as Active')]")
	public WebElement CPA_REFERALBPO;
	
	@FindBy(xpath = "//h1[contains(.,'Testing Bypass Billing')]")
	public WebElement TESTINGBYPASSBILLING;
	
	@FindBy(xpath = "//div[text()='Your password must be 8-128 characters in length and must contain at least one uppercase and lowercase letter, number, and special character (!@#$%^&*<>?).']")
	public WebElement PSWDVERIFYTXT_XPATH;
	
	@FindBy(xpath = "//h2[contains(.,'Shared Services')]")
	public WebElement SHAREDSERVICESHEADING;
	
	@FindBy(xpath = "//P[contains(.,'Shared Services clients are registered for and managed in Vertex Cloud internally')]")
	public WebElement SHAREDSERVICESTEXT;
	
	@FindBy(xpath = "//button[contains(.,'Enroll Shared Services')]")
	public WebElement SHAREDSERVICESBUTTON;
	
	@FindBy(xpath = "//label[contains(.,'I agree to the')]")
	public WebElement AGREETOTERMSTEXT;
	
	@FindBy(xpath = "//a[contains(.,'terms of service')]")
	public WebElement TERMSOFSERVICE;
	
	@FindBy(xpath = "//div[@ng-messages='form.agreedToToS.$error']/span[contains(.,'This field is required.')]")
	public WebElement TERMSOFSERVICEERROR;
	
	@FindBy(xpath="//a[contains(.,'Agreement')]")
	public WebElement AGREEMENTLINK;
	
	@FindBy(xpath="//button[contains(.,'I Agree')]")
	public WebElement IAGREE;
}
