package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VerifyReportScreen {

	@FindBy(xpath = "//h1")

	public WebElement REPORTLABEL;
	@FindBy(xpath = "//div[2]/div/div[1]/div[1]/h2")

	public WebElement TRANSACTIONREPORTLABEL;
	@FindBy(xpath = "//div[2]/div[2]/div/div[1]/div[3]/h2")

	public WebElement TAXABILITYREPORTLABEL;
	@FindBy(xpath = "//div[2]/div[2]/div/div[2]/h2")

	public WebElement CONFIGURATIONREPORTLABEL;
	@FindBy(xpath = "//div[2]/div/div[2]/div/table")

	public WebElement CONFIGURATIONRETABLE;
	@FindBy(xpath = "//span[text()='Reports']")

	public WebElement REPORTSLINK;
	@FindBy(xpath = "//div[2]/div/div[3]/h2")

	public WebElement FILINGCALREPORTLABEL;
	@FindBy(xpath = "//a[contains(text(),'View complete configuration report')]")

	public WebElement VIEWCOMPCONFREP;
	@FindBy(xpath = "//div[3]/div/table")

	public WebElement FILINGCALTAB;

}
