/*###################################################################################################
Name: BillingReport.java 
Description: Billing Report page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/28/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BillingReport {
	@FindBy(xpath = "//div/h1")
	public WebElement BILLINGREPORTLABEL;
	
	@FindBy(xpath = "//span[@class='help-popover']/span")
	public WebElement HELPICON;
	
	@FindBy(xpath = "//h3[@class='popover-title']")
	public WebElement HELPTITLE;
	
	@FindBy(xpath = "//div[@class='popover-content']")
	public WebElement HELPCONTENT;

}
