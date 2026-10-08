package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QBOSettings {

	@FindBy(xpath = "//span[contains(text(),'Settings')]")		
	public WebElement settings;	
		
	@FindBy(xpath = "//label[text()='TaxCentral Company']")	
	public WebElement removeMappinglink1;	
		
	@FindBy(id = "cancel-ok")	
	public WebElement removeCompanyMap;	
		
	@FindBy(xpath = "//h1[contains(text(),'CONFIGURE COMPANY MAPPING')]")	
	public WebElement configureCompanyMap;	
		
	@FindBy(xpath = "//input[@name='qboCompany'][1]")	
	public WebElement qboCompany1;	
		
	@FindBy(xpath = "//input[@name='qboCompany'][2]")	
	public WebElement qboCompany2;	
		
	@FindBy(xpath = "//input[@name='qboCompany'][3]")	
	public WebElement qboCompany3;	
		
	@FindBy(xpath = "//input[@name='tcCompany'][1]")	
	public WebElement toCompany1;	
		
	@FindBy(xpath = "//input[@name='tcCompany'][2]")	
	public WebElement toCompany2;	
		
	@FindBy(xpath = "//input[@name='tcCompany'][3]")	
	public WebElement toCompany3;	
		
	@FindBy(xpath = "//a[contains(text(),'Remove Mapping')][2]")	
	public WebElement removeMappinglink2;	
		
	@FindBy(xpath = "//a[contains(text(),'Remove Mapping')][3]")	
	public WebElement removeMappinglink3;	
		
	@FindBy(xpath = "//h2[contains(text(),'SECURITY CREDENTIALS')]")	
	public WebElement securityDetails;	
		
	@FindBy(xpath = "//label[contains(text(),'API Key')]/../input")	
	public WebElement apiKeyinput;	
		
	@FindBy(xpath = "//label[contains(text(),'API Password')]/../input")	
	public WebElement apiPasswordinput;	
		
	@FindBy(linkText = "Edit Security Credentials")	
	public WebElement editSecuritycredentials;	
		
	@FindBy(name = "key")	
	public WebElement apiKeyUpdate;	
		
	@FindBy(name = "Secretkey")	
	public WebElement apiPasswordUpdate;	
		
	@FindBy(xpath = "//button[contains(text(),'SAVE')]")	
	public WebElement save;	
		
	@FindBy(xpath = "//a[contains(text(),'RETURN TO SETTINGS')]")	
	public WebElement returntosettings;	
		
	@FindBy(xpath = "//button[contains(text(),'Remove Company Mapping')]")	
	public WebElement removecompanyMapping;	
		
	@FindBy(id = "cancel-go-back")	
	public WebElement cancel;	
		
	@FindBy(xpath = "//a[contains(text(),'Add Company Mapping')]")	
	public WebElement addcompanyMapping;	

}
