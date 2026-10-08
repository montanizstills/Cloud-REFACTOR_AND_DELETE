/*###################################################################################################
Name: ConfigureRegistration.java 
Description: Professional User - Configure Page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/
package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfigureRegistration {

	@FindBy(xpath = "//div[3]/ul/li[4]/a[@title='Registrations']")
	public WebElement REGISTRATIONLINK;
	
	@FindBy(xpath = "//div[@id='tab-9786cb7a-bc4b-49e7-8d4a-9db749cb2feb']/div/select")
	public WebElement ADDSTATES;
	
	@FindBy(xpath = "//div[3]/div[3]/div/button")
	public WebElement ADDSTATESADDBUTTON;
	
	@FindBy(xpath = "//div[3]/div[3]/ul/li/a/span")
	public WebElement ADDEDSTATELINK;
	
	@FindBy(xpath = "//div[4]/div[3]/ul/li/a/span")
	public WebElement JURIDICTIONSPAYMENTLINK;
	
	@FindBy(xpath = "//div[5]/div[3]/div/button")
	public WebElement REGISTERBUTTON;
	
	@FindBy(xpath = "//div/ul/li/input[@type='text']")
	public WebElement REGISTRATIONNUMBER;
	
	@FindBy(xpath = "//div/ul/li[2]/select")
	public WebElement FILLINGMETHOD;
	
	@FindBy(xpath = "//div/ul/li[3]/select")
	public WebElement FILLINGFREQUENCY;
	
	@FindBy(xpath = "//div[4]/div[3]/ul/li[2]/a/span")
	public WebElement JURIDICTIONSALESLINK;
	
	@FindBy(xpath = "//div/ul/li[4]/select")
	public WebElement CREDITTYPE;
	
	@FindBy(xpath = "//div/ul/li[5]/input[@type='text']")
	public WebElement EDIACCESSKEY;
	
	@FindBy(xpath = "//div/ul/li[6]/input")
	public WebElement PAYMENTFUNDSTRANSFER;
	
	@FindBy(xpath = "//div/ul/li[7]/input")
	public WebElement AMENDEDRETURN;
	
	@FindBy(xpath = "//div/ul/li[8]/select")
	public WebElement EDIENTITYTYPE;

}
