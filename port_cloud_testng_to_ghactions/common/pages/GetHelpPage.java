package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GetHelpPage {

	@FindBy(xpath = "//a/span[contains(text(),'Visit Help Center')]")
	public WebElement GETHELPLINK;
	
	@FindBy(xpath = "//a[contains(text(),'About')]")
	public WebElement ABOUTLINK;
	
	@FindBy(xpath = "//h1[contains(text(),'THE TRUSTED LEADER IN SALES AND USE TAX SOFTWARE EXCELLENCE ')]")
	public WebElement ABOUTHEADERLABEL;
	
	@FindBy(xpath = "//a[contains(text(),'Features')]")
	public WebElement FEATURESLINK;
	
	@FindBy(xpath = "//h2[contains(text(),'Bringing a Virtual Tax Office to Small to Medium-Sized Businesses with Vertex SMB Sales Tax Software')]")
	public WebElement FEATURESTITLE;
	
	@FindBy(xpath = "//a[contains(text(),'Pricing')]")
	public WebElement PRICINGLINK;
	
	@FindBy(xpath = "h1.h2")
	public WebElement PRICINGLABEL;
	
	@FindBy(xpath = "//a[contains(text(),'Partners')]")
	public WebElement PARTNERSLINK;
	
	@FindBy(xpath = "h2.title")
	public WebElement PARTNERSTITLE;
	
	@FindBy(xpath = "//a[contains(text(),'Resources')]")
	public WebElement RESOURCESLINK;
	
	@FindBy(xpath = "//a[contains(text(),'Webinar: Revolutionary Approach to Sales & Use Tax Automation')]")
	public WebElement RESOURCELABEL;
	
	@FindBy(xpath = "//a[contains(text(),'Contact Us')]")
	public WebElement CONTACTUSLINK;
	
	@FindBy(xpath = "h1.h2")
	public WebElement CONTACTUSLABEL;
	
	@FindBy(xpath = "//input[@id='input_3_8']")
	public WebElement FIRSTNAME;
	
	@FindBy(xpath = "//input[@id='input_3_9']")
	public WebElement LASTNAME;
	
	@FindBy(xpath = "//input[@id='input_3_1']")
	public WebElement COMPANY;
	
	@FindBy(xpath = "//input[@id='input_3_3']")
	public WebElement POSITION;
	
	@FindBy(xpath = "//input[@id='input_3_7']")
	public WebElement EMAIL;
	
	@FindBy(xpath = "//select[@id='input_3_5']")
	public WebElement STATE;
	
	@FindBy(xpath = "//textarea[@id='input_3_6']")
	public WebElement ADDITIONALMESSAGE;
	
	@FindBy(xpath = "//input[@id='gform_submit_button_3']")
	public WebElement SUBMITBUTTON;

}
