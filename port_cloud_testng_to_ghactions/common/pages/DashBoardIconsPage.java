package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoardIconsPage {

	@FindBy(xpath = "//label/span/i[@class='icon-info icon-small section-heading']")
	public WebElement FILLINGPERIDICON;

	@FindBy(xpath = "//h3[contains(text(),'Filing Period')]")
	public WebElement FILINGPERIODLABEL;

	@FindBy(xpath = "//div[1]/div/h2/span/i[@class='icon-info icon-small section-heading']")
	public WebElement STATESTATITICSICON;

	@FindBy(xpath = "//h3[contains(text(),'State Statistics')]")
	public WebElement STATESTATITICSLABEL;

	@FindBy(xpath = "//div[3]/h2/span/i[@class='icon-info icon-small section-heading']")
	public WebElement COMPARATIVEANALYSISICON;

	@FindBy(xpath = "//h3[contains(text(),'Comparative Analysis')]")
	public WebElement COMPARATIVEANALYSISLABEL;

}
