package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlexField {

	@FindBy(xpath = "//thead/tr/th[3]/../../../tbody/tr/td/a[contains(.,'All Flex Fields')]")
	public WebElement AllFlexFields;

	@FindBy(xpath = "//h1[contains(.,'Configure Flex Fields')]")
	public WebElement ConfigureFlexFieldsLabel;

	@FindBy(xpath = "//table/thead/tr[@class='tablesorter-headerRow']/th[contains(.,'Data Type')]/../th[contains(.,'Name')]/../th[contains(.,'Taxability Category')]/../th[contains(.,'Actions')]")
	public WebElement TableColHeaders;

	@FindBy(xpath = "//table/tbody//tr[2]//td[4]//div/a")
	public WebElement ActionViewBox;

	@FindBy(xpath = "//tbody//tr[2]//td[4]//div[1]//ul[1]//li[1]//a[1]")
	public WebElement EditLnk;

	@FindBy(xpath = "//i[contains(@class,'icon-button icon-product-download')]")
	public WebElement DownloadBtn;

	@FindBy(xpath = "//a[@class='btn pull-right' and contains(.,'Return to Configuration Overview')]")
	public WebElement ReturnConfigRight;

	@FindBy(xpath = "//a[@class='btn pull-left' and contains(.,'Return to Configuration Overview')]")
	public WebElement ReturnConfigLeft;

	@FindBy(xpath = "//p[contains(.,'Edit Flex Field')]")
	public WebElement EditFlexFieldLabels;

	@FindBy(xpath = "//button[@class='close drawer__btn-close']")
	public WebElement CloseIcon;

	@FindBy(xpath = "//input[@id='flexfield-datatype' and @disabled='disabled']")
	public WebElement FlexDataType;

	@FindBy(xpath = "//input[@id='flexfield-name' and @disabled='disabled']")
	public WebElement FlexName;

	@FindBy(xpath = "//textarea[@id='flexfield-description']")
	public WebElement FlexDescription;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	public WebElement CancelBtn;

	@FindBy(xpath = "//button[contains(.,'Save')]")
	public WebElement SaveBtn;

	@FindBy(xpath = "//input[@id='quick-search-taxability-category']")
	public WebElement QuickSearchTaxabilityCategory;

}
