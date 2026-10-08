/*###################################################################################################
Name: TaxabilityReports.java 
Description: Taxability Reports page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/28/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaxabilityReportsPage {
	@FindBy(xpath = "//a[@title='Taxability Reports']")

	public WebElement TAXABILITYREPORTTAB;
	@FindBy(xpath = "//div/h1")

	public WebElement TAXABILITYREPORTLABEL;
	@FindBy(xpath = "//span/a/i")

	public WebElement PLUSICON;
	@FindBy(xpath = "//span[@class='help-popover']/span")

	public WebElement HELPICON;
	@FindBy(xpath = "//h3[@class='popover-title']")

	public WebElement HELPTITLE;
	@FindBy(xpath = "//div[@class='popover-content']")

	public WebElement HELPCONTENT;
	@FindBy(xpath = "//div[@style='display: block)']/div[@class='tree-folder-header']/i[@class='glyphicon-folder-close']")

	public WebElement DOWNLOADLABELLINK;
	@FindBy(xpath = "//div[@style='display: block)']/div[@class='tree-folder-content']/div[@class='tree-item']/div[@class='tree-item-name']")

	public WebElement DOWNLOADLINK;

}
