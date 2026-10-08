/*###################################################################################################
Name: FilingCalendarReport.java 
Description: Filing Calendar Report Report page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/28/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilingCalendarReportPage {

	public final String TAXPAYERDROPDOWN_XPATH = "//label[contains(.,'Company: ')]/../select[@id='taxpayersList']";
	
	@FindBy(xpath = "//a[@title='Filing Calendar Report']")
	public WebElement FILINGCALENDARREPORTTAB;
	
	@FindBy(xpath = "//div/h1")
	public WebElement FILINGCALENDARREPORTLABEL;
	
	@FindBy(xpath = "//span[@class='help-popover']/span")
	public WebElement HELPICON;
	
	@FindBy(xpath = "//h3[@class='popover-title']")
	public WebElement HELPTITLE;
	
	@FindBy(xpath = "//div[@class='popover-content']")
	public WebElement HELPCONTENT;
	
	@FindBy(xpath = "//select[@id='taxpayersList']")
	public WebElement TAXPAYERDROPDOWN;
	
	@FindBy(xpath = "//table[@id='field-table']/tbody[1]/tr[1]/td[1]")
	public WebElement RETURNPREPAYMENT;
	
	@FindBy(xpath = "//tr/td[2]")
	public WebElement SOFTDATE;
	
	@FindBy(xpath = "//tr/td[3]")
	public WebElement HARDDATE;
	
	@FindBy(xpath = "//tr/td[4]")
	public WebElement DUEDATE;
	
	@FindBy(xpath = "//th/div/span[contains(.,'Return/Pre-Payment')]")
	public WebElement RPREPAYMENTLBL;
	
	@FindBy(xpath = "//th[text()='Soft Date']")
	public WebElement SOFTDATELBL;
	
	@FindBy(xpath = "//th[text()='Hard Date']")
	public WebElement HARDDATELBL;
	
	@FindBy(xpath = "//th/div/span[text()='Due Date']")
	public WebElement DUEDATELBL;
	
	@FindBy(xpath = "//div[@class='span12']/div[contains(.,'There are no upcoming filing dates for this company.')]")
	public WebElement MESSAGENOUPCOMING;
	
	@FindBy(xpath = "//a[text()='DOWNLOAD PRINTABLE REPORT']")
	public WebElement DWNLDPRINTREPRT;
	
	@FindBy(xpath = "//a[contains(.,'Filing Calendar')]")
	public WebElement FilingCalendarLink;
	
	@FindBy(xpath = "//span[contains(.,'Find the due date, by jurisdiction for each return and prepayment that are configured in Vertex Cloud for your company.')]")
	public WebElement FilingCalendarText;
	
}
