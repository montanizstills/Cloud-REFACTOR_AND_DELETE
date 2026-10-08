package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CPAClientBillingReport {

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	public WebElement SETTINGS;
	
	@FindBy(xpath = "//a[contains(text(),'View All Billing Reports')]")
	public WebElement VIEWALLBILLINGREPORTS;
	
	@FindBy(xpath = "//div[2]/div/div/div[2]/div/div[1]//select[@class='input-block-level ng-pristine ng-untouched ng-valid ng-empty']")
	public WebElement CLIENT;
	
	@FindBy(xpath = "//div[2]/div[2]/div/div/div[2]/div/div[3]/select")
	public WebElement BILLINGPERIOD;
	
	@FindBy(xpath = "//div[2]/div[2]/div/div/div[2]/div/div[2]/select")
	public WebElement BILLINGYEAR;
	
	@FindBy(xpath = "//div[2]/div[2]/div/div/div[2]/div/div[4]/select")
	public WebElement BILLINGTYPE;
	
	@FindBy(xpath = "//table/tbody/tr[2]/td[5]/a[contains(text(),'Download')]")
	public WebElement DOWNLOADBILLINGREPORT;

}
