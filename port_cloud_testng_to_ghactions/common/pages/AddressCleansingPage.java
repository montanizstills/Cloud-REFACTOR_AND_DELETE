/*###################################################################################################
Name: BillingPage.java 
Description: Configuration Report page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/28/2014
Date Modified:
Updated Comments:
#####################################################################################################*/
package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressCleansingPage {

	@FindBy(xpath = "//a[contains(.,'Address Cleansing')]")
	public WebElement ADDRESSCLEANSINGLINKFROMLEFTNAV;

	@FindBy(xpath = "//h1[contains(.,'Address Cleansing')]")
	public WebElement ADDRESSCLEANSINGPAGETITLE;

	@FindBy(xpath = "//p")
	public WebElement ADDRESSCLEANSINGPAGEHELPCONTENTTEXT;

	@FindBy(xpath = "//h2")
	public WebElement CLEANSEDFILESTABLETITLE;

	@FindBy(xpath = "//button[contains(.,'Upload File')]")
	public WebElement UPLOADFILEBUTTONINADDRESSCLEANSINGPAGE;

	@FindBy(xpath = "//div[@class='ant-notification-notice-description']")
	public WebElement FILEUPLOAD;

}
