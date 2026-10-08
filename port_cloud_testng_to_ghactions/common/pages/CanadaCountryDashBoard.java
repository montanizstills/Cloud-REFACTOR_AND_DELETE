package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CanadaCountryDashBoard {

	@FindBy(xpath = "//label[@id='canadaRadioButtonLabel']")
	public WebElement CANADACOUNTRY;

	@FindBy(xpath = "//a/span[contains(text(),'Dashboard')]")
	public WebElement DASHBOARD;

	@FindBy(xpath = "//h2[@id='canadaStatsHeader']/span/i")
	public WebElement CANADASTATEHEADERICON;

	@FindBy(xpath = "//h3[contains(text(),'Canada')]")
	public WebElement CANADALABEL;

	@FindBy(xpath = "//p[contains(text(),'You are not currently registered in ')]")
	public WebElement NOTCURRENTLYREGISTERED;

	@FindBy(xpath = "//div[2]/p[contains(text(),'You can')]")
	public WebElement JURISDICTIONSINCONFIG;

	@FindBy(xpath = "//p[@id='regLineCAN']")
	public WebElement REGISTRATIONSCOUNT;

	@FindBy(xpath = "//label[@id='usRadioButtonLabel']")
	public WebElement USCOUNTRY;

	@FindBy(xpath = "//ul/li[1]/span[@class='map-legend-registered']")
	public WebElement STATESURREGISTEDIN;

	@FindBy(xpath = "//ul/li[2]/span[@class='map-legend-selected']")
	public WebElement CURRENTLYSELECTEDSTATE;
}
