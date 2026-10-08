package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserSettingsPage {

	@FindBy(xpath = "//li/a/i[contains(text(),'account')]")

	public WebElement ACCOUNT;
	@FindBy(xpath = "//a[contains(text(),'User Settings')]")

	public WebElement USERSETTINGS;
	@FindBy(xpath = "//input[@id='lastName']")

	public WebElement LASTNAME;
	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")

	public WebElement DASHBOARD;
	@FindBy(xpath = "//div[@class='banner-content']/p[1][contains(text(),'You are currently using our free trial. Some features are hidden or not accessible to free trial users.')]")

	public WebElement BANNERCONTENT1;
	@FindBy(xpath = "//div[@class='banner-content']/p[2][contains(text(),'and harness the superior quality of our sales tax software calculation system.')]")

	public WebElement BANNERCONTENT2;
	@FindBy(xpath = "//div/p[2]/a[text()='Upgrade your plan']")

	public WebElement UPGRADEPLAN;
	// @FindBy(xpath="//select[@id='tax-payer' and @disabled='disabled']")
	//
	// public WebElement DASHBOARDTAXPAYER;
	// @FindBy(xpath="//select[@id='tax-payer-filing-period' and
	// @disabled='disabled']")
	//
	// public WebElement DASHBOARDFILINGPERIOD;
	@FindBy(xpath = "//select[@id='tax-payer']")

	public WebElement DASHBOARDTAXPAYER;
	@FindBy(xpath = "//select[@id='tax-payer-filing-period']")

	public WebElement DASHBOARDFILINGPERIOD;
	@FindBy(xpath = "//b[@id='number_of_trans_main' and text()='0']")

	public WebElement TRANSACTIONS;
	@FindBy(xpath = "//b[@id='number_of_returns_main' and text()='0']")

	public WebElement RETURNS;
	@FindBy(xpath = "//b[@id='total_sales_main' and text()='$0']")

	public WebElement SALES;
	@FindBy(xpath = "//b[@id='total_tax_main' and text()='$0']")

	public WebElement TAXES;
	@FindBy(xpath = "//div/dl/dd/a[@href='/tos/client.pdf']")

	public WebElement SUBSCRIPTIONPDF;
	@FindBy(xpath = "//div[contains(text(),'Your free trial will expire in 29 days')]")

	public WebElement TRIALEXPIRED;
	@FindBy(xpath = "//div[contains(text(),'Update your billing information to modify your subscription')]")

	public WebElement BILLINGINFO;
	@FindBy(xpath = "//div/p/strong[contains(text(),'no billing reports')]")

	public WebElement NOBILLINGREPORTS;
	@FindBy(xpath = "//input[@id='userName']")

	public WebElement USERNAME;

}
