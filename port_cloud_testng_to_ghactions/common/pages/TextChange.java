package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextChange {

	@FindBy(xpath = "//h2/../div/div/h3/span/a[contains(.,'Edit')]")

	public WebElement EDITCOMPANY;
	@FindBy(xpath = "//strong[contains(text(),'Alabama')]")

	public WebElement ALABAMA;
	@FindBy(xpath = "//strong[contains(text(),'Missouri')]")

	public WebElement MISSOURI;
	@FindBy(xpath = "//strong[contains(text(),'Oklahoma')]")

	public WebElement OKLAHOMA;
	@FindBy(xpath = "//a[contains(text(),'Return to Configuration Overview')]")

	public WebElement RETURNCOMPANY;
	@FindBy(xpath = "//a[@id='alabama-advanced-settings']/../a[@class='ng-hide']")

	public WebElement ALABAMAADVSETT;
	@FindBy(xpath = "//a[@id='missouri-advanced-settings']/../a[@class='ng-hide']")

	public WebElement MISSOURIADVSETT;
	@FindBy(xpath = "//a[@id='oklahoma-advanced-settings']/../a[@class='ng-hide']")

	public WebElement OKLAHOMADVSETT;
	@FindBy(xpath = "//a[@id='alabama-returns']")

	public WebElement ALABAMAREGISTER;
	@FindBy(xpath = "//a[@id='missouri-returns']")

	public WebElement MISSOURIREGISTER;
	@FindBy(xpath = "//a[@id='oklahoma-returns']")

	public WebElement OKLAHOMAREGISTER;

}
