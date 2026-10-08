package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DataChecks {

	@FindBy(xpath = "//div[@style='display: block)']/div/div/div/table/tbody/tr/td/a[text()='View Errors']")
	public WebElement VIEWERRORS;
	
	@FindBy(xpath = "//span[@class='badge badge-important badge-large']")
	public WebElement NOOFERRORS;
	
	@FindBy(xpath = "//a[@data-dismiss='modal']")
	public WebElement EXITLINK;
	
	@FindBy(xpath = "//a[@id='ValidationErrorLink']")
	public WebElement VALIDATIONLINK;
	
	@FindBy(xpath = "//span[@id='MessageCount']")
	public WebElement MESSAGEICON;
	
	@FindBy(xpath = "//h1[contains(text(),'Validation Errors')]")
	public WebElement VALIDATIONHEAD;
	
	@FindBy(xpath = "//button[@class='btn btn-primary pull-right']/../a[contains(text(),'Manage Validation Mappings')]")
	public WebElement MANAGEMAPPINGS;
	
	@FindBy(xpath = "//div[3]/div[1]/button[contains(.,'Process Data')]")
	public WebElement PROCESSDATA;
	
	@FindBy(xpath = "//h2[contains(text(),'Company Code Errors')]")
	public WebElement CMPNYERRORSHEAD;
	
	@FindBy(xpath = "//h2[contains(text(),'Location Code Errors')]")
	public WebElement LOCERRORSHEAD;
	
	@FindBy(xpath = "//h1[contains(text(),'Exemption Reason Errors')]")
	public WebElement EXCEPERRORSHEAD;
	
	@FindBy(xpath = "//table[2]/tbody/tr[1]/td[2]/../td[contains(text(),'Company Code')]")
	public WebElement CMPNYCODE;
	
	@FindBy(xpath = "//tr/td[3 and contains(text(),'PrimeTestbad1')]/../td/span/a/span[text()='View']")
	public WebElement CMPNYVIEWLINK;
	
	@FindBy(xpath = "//p/i[@class='icon icon-error']")
	public WebElement ERRORICON;
	
	@FindBy(xpath = "//select[@name='companyCode']")
	public WebElement CMPNYDROP;
	
	@FindBy(xpath = "//button[contains(text(),'VALIDATE ERROR')]")
	public WebElement VALIDATEERROR;
	
	@FindBy(xpath = "//tr/td[3 and contains(text(),'PrimeTestbad1')]/../td/i[@class='icon icon-ok ir']")
	public WebElement OKICON;
	
	@FindBy(xpath = "//p[1]/button[@disabled='disabled']")
	public WebElement PROCESSCHANGESENABLE;
	
	@FindBy(xpath = "//table[2]/tbody/tr[1]/td[1]")
	public WebElement NEEDBYACTION;
	
	@FindBy(xpath = "//body[contains(text(),'Company Code')]")
	public WebElement JSONRESPONSE;
	
	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	public WebElement REPORTS;
	
	@FindBy(xpath = "//a[contains(text(),'Validation Errors')]")
	public WebElement VALIDATIONERRORREPORTS;
	
	@FindBy(xpath = "//div/p/a[contains(.,'View Report')]")
	public WebElement EMPTYCPNYCODEVIEWREPORT;
	
	@FindBy(xpath = "//div/a[contains(text(),'Return to Configuration Overview')]")
	public WebElement RETUNRCONFIGOVERVIEWTOP;
	
	@FindBy(xpath = "//div/p/a[contains(text(),'Return to Configuration Overview')]")
	public WebElement RETUNRCONFIGOVERVIEWBOTTOM;
	
	@FindBy(xpath = "//span[@id='ValidationErrorCount']")
	public WebElement VALIDATIONERRORCOUNT;
	
	@FindBy(xpath = "//span/a[contains(text(),'View')]")
	public WebElement VIEWLINK;
	
	@FindBy(xpath = "//select[@class='input-block-level ng-pristine ng-untouched ng-valid']")
	public WebElement VALIDATIONDROPDOWN;
	
	@FindBy(xpath = "//div/div[1]/div/div/div/div/div/div[1]/span")
	public WebElement REGISTRATEDSTATESCOUNT;
	
	@FindBy(xpath = "//div/a[@class='btn btn-primary input-block-level'][contains(.,'Download Printable Report')]")
	public WebElement DWNLDPRINTRPRT;
	
	@FindBy(xpath = "//select[@id='company']")
	public WebElement COMPANYDD;
	
	@FindBy(xpath = "//button[contains(.,'Resolve Error')]")
	public WebElement RESOLVEERROR;
	
	@FindBy(xpath = "//span[contains(text(),'Configure')]")
	public WebElement CONFIGURELINK;
}
