package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestInstanceSandbox {

	public final String NPROGRESSBUSY_XPATH = "html[@class='nprogress-busy']";

	@FindBy(xpath = "//h1[contains(.,'Test Instance Password')]")

	public WebElement testInstancePwdlabel;

	@FindBy(id = "UserName")

	public WebElement testInstanceUsername;

	@FindBy(id = "NewPassword")

	public WebElement testInstancepassword;

	@FindBy(id = "ConfirmNewPassword")
	public WebElement testInstanceconfirmpassword;

	@FindBy(xpath = "//button[contains(.,'Set Password')]")

	public WebElement setPassword;

	@FindBy(xpath = "//span[contains(.,'Configure')]")

	public WebElement Configurelnk;

	@FindBy(xpath = "//a[contains(.,'Copy Company Data')]")

	public WebElement copyCompanydatalnk;

	@FindBy(xpath = "//ul[@class='nav nav-tabs tabs']/li/a[contains(.,'Add New Test Company')]")

	public WebElement addnewTestCompanylnk;

	@FindBy(xpath = "//h2[contains(.,'Copy Production Data')]")

	public WebElement copyCompanyDatalabel;

	@FindBy(xpath = "//select[@id='copy-company-source']")

	public WebElement sourcCompany;

	@FindBy(id = "new-company-code")

	public WebElement newCompanycode;

	@FindBy(xpath = "//div/button[@class='btn btn-primary'][text()='Copy Data']")

	public WebElement copyDataBtn;

	@FindBy(xpath = "//Strong[contains(.,'Request processing!')]")

	public WebElement changesprocessing;

	@FindBy(xpath = "//a[contains(.,'View Change Report')]")

	public WebElement viewChangeReport;

	@FindBy(xpath = "//h3[contains(.,'Company Code')]")

	public WebElement companycodelabel;

	@FindBy(xpath = "//h3[contains(.,'Copied from Company')]")

	public WebElement copiedfromCompanylabel;

	@FindBy(xpath = "//h3[contains(.,'Last Copy Date')]")

	public WebElement lastcopyDatelabel;

	@FindBy(xpath = "//h3[contains(.,'Net Change')]")

	public WebElement netChangelabel;

	@FindBy(xpath = "//a[contains(.,'Delete Test Company')]")

	public WebElement DeleteTestCompany;

	@FindBy(xpath = "//button[contains(.,'Begin Removal')]")

	public WebElement beginRemovalBtn;

	@FindBy(xpath = "//a[contains(.,'Add Another Company')]")

	public WebElement AddanotherCompany;

	@FindBy(id = "number_of_returns_main")

	public WebElement numberofReturns;

	@FindBy(id = "total_sales_main")

	public WebElement totalSales;

	@FindBy(id = "total_tax_main")

	public WebElement totalTax;

	@FindBy(xpath = "//div[4]/div/div/table/tbody/tr")

	public WebElement testInstanceadjustmentfileCopystatus;

	@FindBy(xpath = "//table/tbody[2]/tr[1]/td[4]/div/div/a[contains(.,'Download')]")

	public WebElement ReturnsDownloadLink;

	@FindBy(xpath = "//a[contains(.,'DELETE TEST COMPANY')]")

	public WebElement deleteTestCompany;

	@FindBy(xpath = "//p[contains(.,'You are about to delete one or more test companies.')]")

	public WebElement aboutdeletetestCompany;

	@FindBy(xpath = "//strong[contains(.,'Are you sure you want to permanently delete the selected company(s)?')]")

	public WebElement surewantdeletetestCompany;

	@FindBy(xpath = "//p[contains(.,'Please note, if you do not wish to permanently delete the selected test company(s), you can end-date a company by clicking the Begin Removal button on the bottom of the Configure Overview Page')]")

	public WebElement plznotedeletetestcompany;

	@FindBy(xpath = "//strong[contains(.,'Cancel')]")

	public WebElement cancelLnk;

	@FindBy(id = "remove-customer-end-date")

	public WebElement customerEndDate;

	@FindBy(xpath = "//a[contains(.,'Remove item')]")

	public WebElement removeItem;
	@FindBy(xpath = "//a[contains(.,'Delete Test Company')]")

	public WebElement DeletetestCompanyMTTP;
	@FindBy(xpath = "//span[contains(.,'Your delete request is processing.')]")
	public WebElement deleteRequestProcessingMTTP;
	@FindBy(xpath = "//h1[text()='Configure Company']")
	public WebElement ConfigureCompanyHeading;
	@FindBy(xpath = "//a[contains(.,'Add New Test Company')]")
	public WebElement ADDNEWTESTCOMPANY;
	@FindBy(id = "add-company")
	public WebElement ADDANOTHERCOMPANYLINK;
	@FindBy(xpath = "//div/span/../../div[contains(.,'Your test instance is unavailable for adding taxpayers at this time. Please check back later.')]")
	public WebElement UNAVIALFORADDINGTAXPAYERS;
	@FindBy(xpath = "//a[@class='btn pull-right' and contains(text(),'Return to Configuration Overview')]")
	public WebElement RETURNTOCONFIGOVERVIEW;

}
