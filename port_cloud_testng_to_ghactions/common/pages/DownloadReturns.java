package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DownloadReturns {

	@FindBy(xpath = "//span[contains(text(),'Manage Tax Returns')]")
	public WebElement MANAGETAXRETURNSLINK;
	
	@FindBy(xpath = "//h1")
	public WebElement MANAGETAXRETURNSLABEL;
	
	@FindBy(xpath = "//select[@id='tax-payer']")
	public WebElement TAXPAYER;
	
	@FindBy(xpath = "//select[@id='filing-period']")
	public WebElement FILLINGPERID;
	
	@FindBy(xpath = "//button[contains(text(),'Generate Returns')]")
	public WebElement GENERATRETURN;
	
	@FindBy(xpath = "//table/tbody//tr[1]/td[1]")
	public WebElement RETURN;
	
	@FindBy(xpath = "//table/tbody//tr[1]/td[3]")
	public WebElement STATUS;
	
	@FindBy(xpath = "//table/tbody//tr[1]/td[4]")
	public WebElement UPCOMING;
	
	@FindBy(xpath = "//button[contains(text(),'Reconciliation Report')]")
	public WebElement RECONCILIATION;
	
	@FindBy(xpath = "//button[contains(text(),'Unassigned Data Report')]")
	public WebElement UNASSIGNREPORT;

}
