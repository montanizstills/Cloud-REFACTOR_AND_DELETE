/*###################################################################################################
Name: BillingPage.java 
Description: Billing page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BillingPage {

	@FindBy(xpath = "//input[@name='bill_to_address_line1']")
	public WebElement BILLINGADDRESS1;

	@FindBy(xpath = "//input[@name='bill_to_address_city']")
	public WebElement BILLINGCITY;

	@FindBy(xpath = "//select[@id='bill_to_address_state']")
	public WebElement BILLINGSTATE;

	@FindBy(xpath = "//input[@name='card_number']")
	public WebElement CREDITCARD;
	
	@FindBy(xpath = "//select[@id='expire-month']")
	public WebElement EXPMONTH;

	@FindBy(xpath = "//select[@id='expire-year']")
	public WebElement EXPYEAR;

	@FindBy(xpath = "//input[@name='card_cvn']")
	public WebElement CVN;

	@FindBy(xpath = "//input[@name='bill_to_address_postal_code']")
	public WebElement BILLINGZIP;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement SAVE;
}
