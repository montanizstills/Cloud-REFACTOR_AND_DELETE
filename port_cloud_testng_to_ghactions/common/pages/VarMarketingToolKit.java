package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VarMarketingToolKit {

	@FindBy(xpath = "//span[contains(text(),'Marketing ToolKit')]")

	public WebElement MARKETINGTOOLKITLINK;
	@FindBy(xpath = "//h3[contains(text(),'Pricing Calculator')]")

	public WebElement PRICINGCALCULATORSECTION;
	@FindBy(xpath = "//p[contains(text(),'For Vertex SMB and our partners use only.')]")

	public WebElement PRICINGCALCULATORSUBTITLE;
	@FindBy(xpath = "//div[2]/ul/li//a[contains(text(),'Demo Movie')]")

	public WebElement DEMOMOVIELINK;
	@FindBy(xpath = "//div[2]/ul/li//a[contains(text(),'New Bitmap Image')]")

	public WebElement NEWBITMAPIMAGELINK;
	@FindBy(xpath = "//div[2]/ul/li//a[contains(text(),'New Rich Text Document')]")

	public WebElement RICHDOCUMENTLINK;
	@FindBy(xpath = "//div[2]/ul/li//a[contains(text(),'New Text Document')]")

	public WebElement TEXTDOCUMENTLINK;
}
