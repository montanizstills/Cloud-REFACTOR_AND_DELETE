package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VerifyEditFilingcompanyDetails {

	@FindBy(xpath = "//h3/span[@class='pull-right']/a[contains(text(),'Edit')]")

	public WebElement EDITCOMPANYLINK;
	@FindBy(xpath = "//a[contains(text(),'Edit Company (Filing Entity) Information')]")

	public WebElement EDITCOMPANYINFOLINK;
	@FindBy(xpath = "//input[@id='company-code']")

	public WebElement COMPANYCODE;
	@FindBy(xpath = "//input[@id='company-name']")

	public WebElement COMAPNYNAME;
	@FindBy(xpath = "//input[@id='federal-id']")

	public WebElement FEDERALCODE;
	@FindBy(xpath = "html[@class='nprogress-busy']")

	public WebElement NPROGRESSBUSY;
	@FindBy(xpath = "//button[contains(text(),'Update')]")

	public WebElement UPDATEBUTTON;

}
