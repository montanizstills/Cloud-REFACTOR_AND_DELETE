package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class CalculateTaxesNewTerritories {

	@FindBys({ @FindBy(xpath = "//select[@id='contact-1-state']/option"), })
	public List<WebElement> PhysicalDestinationTerritories;

	@FindBys({ @FindBy(xpath = "//select[@id='contact-2-state']/option"), })
	public List<WebElement> PhysicalOrigonTerritories;

	@FindBys({ @FindBy(xpath = "//select[@id='contact-3-state']/option"), })
	public List<WebElement> AdminDestinationTerritories;

	@FindBys({ @FindBy(xpath = "//select[@id='contact-4-state']/option"), })
	public List<WebElement> AdminOriginTerritories;
}
