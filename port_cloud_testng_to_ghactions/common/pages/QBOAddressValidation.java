package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class QBOAddressValidation {

	@FindBy(xpath = "//span[contains(text(),'Address Validation')]")

	public WebElement ADDRESSVALIDATION;

	public final String SYNCPROGRESS_XPATH = "//div/h3[text()='SYNC IN PROGRESS']";

	@FindBys({ @FindBy(xpath = "//select[@id='Address-Validation-Status']/option"), })
	public List<WebElement> ADDVALSTATUS_VALUES;

	@FindBy(xpath = "//button[text()='Sync Customer Records']")

	public WebElement SYNCCUSTOMERREC;

	@FindBy(xpath = "//table/tbody/tr[3]/td[1]")

	public WebElement FIRSTROW;

	@FindBy(xpath = "//div[@class='radio ng-scope']/input")

	public WebElement VALIDATEADDRESS;

	@FindBy(xpath = "//div[@class='radio']/input")

	public WebElement ORIGINALADDRESS;

	@FindBy(xpath = "//input[@id='tbQuickSearch']")

	public WebElement QUICKSEARCH;

	@FindBy(xpath = "//a[contains(text(),'Save')]")

	public WebElement SAVE;

	@FindBys({

			@FindBy(xpath = "//table/tbody/tr[@class='ng-scope']"), })

	public List<WebElement> tablerows;

	@FindBys({

			@FindBy(xpath = "//bootstrap-pagination/div/ul/li"), })
	public List<WebElement> Pagination;

}
