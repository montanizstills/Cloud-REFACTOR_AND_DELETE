package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class TaxpayerPartitionsused {

	@FindBy(xpath = "//span[contains(text(),'Manage Customers')]")
	public WebElement SMBCUSTOMERSTAB;

	@FindBy(xpath = "//label[contains(text(),'Cloud Customer Name')]/../input")
	public WebElement SMBCUSTOMERNAME;

	@FindBy(xpath = "//td/a[contains(.,'Select')]")
	public WebElement ACTIONSELECT;

	@FindBy(linkText = "Delete")
	public WebElement SMBCUSTOMERUSERSDELETE;
	
	@FindBy(xpath="//div[@class='span3 ng-binding'][contains(.,'Trusted Id: ')]")
	public WebElement TRUSTEDID;
	
	@FindBy(xpath = "//a[contains(.,'Admin Details')]")
	public WebElement ADMINDETAILS;
	
	@FindBy(xpath = "(//dt[contains(.,'Partition Name:')])[1]/../dd[3]")
	public WebElement PARTITIONID;
	
	@FindBy(xpath = "//span[text()='Taxpayer Partitions Used:']")
	public WebElement PARTITIONSUSEDCOLUMN;

	@FindBy(xpath = "//span[text()='Taxpayer Partitions Used:']/..")
	public WebElement TAXPAYERPARTITIONSCOUNT;

	@FindBy(xpath = "//span[text()='Taxpayer Partition Configuration: ']/..")
	public WebElement TAXPAYERCONFIGURATION;

	@FindBy(xpath = "//select[@class='ng-pristine ng-untouched ng-valid' and @disabled='disabled']")
	public WebElement DefaultMTTP;

	@FindBy(xpath = "//button[contains(.,'Edit Company Configuration')]")
	public WebElement EDITTAXPAYERCAP;

	@FindBy(xpath = "//div[contains(text(),'Companies Allowance*')]/../div/select")
	public WebElement PARTITIONSPOPUPSELECT;

	@FindBy(xpath = "//h5[contains(.,'Modify this customer')]")
	public WebElement EDITTAXPAYERMODELPOPUPTEXT;

	@FindBy(xpath = "//span[contains(.,'Companies Used:')]")
	public WebElement COMPANIESUSED;

	@FindBy(id = "save-ok")
	public WebElement SAVE;

	@FindBy(id = "save-cancel")
	public WebElement CANCEL;

	@FindBy(xpath = "//span[text()='Metrics']")
	public WebElement METRICSTAB;

	@FindBy(id = "btnPartitionsMetrics")
	public WebElement PARTITIONMETRICSBTN;

	@FindBys({

			@FindBy(xpath = "//table/tbody/tr"), })
	public List<WebElement> PARTITIONROWS;

	@FindBys({

			@FindBy(xpath = "//div[contains(text(),'Taxpayer Partition Allowance*')]/../div/select/option"), })
	public List<WebElement> PARTITIONALLOWANCE;

	@FindBy(id = "btnClse")
	public WebElement CLOSEBTN;

	@FindBy(xpath = "//div[contains(.,'How many taxpayers per partition?')]/../div/select")
	public WebElement TaxpayerperPartition;

	@FindBy(xpath = "//div[4]/div/div")
	public WebElement TaxpayerPartitionConfigurationStatus;

	@FindBy(xpath = "//div/span[contains(.,'Taxpayer Partition Configuration: ')]/..")
	public WebElement TAXPAYERPARTITIONCONFIGURATION;
	
	
	//
	
	@FindBy(xpath = "//h2[@class='h1'][contains(.,'Add Partition')]")
	public WebElement ADD_PARTITION_Text;
	
	@FindBy(xpath = "//button[contains(.,'Add Partition')]")
	public WebElement BUT_ADD_PARTITION;
	
	@FindBy(xpath = "//h3[@id='AddPartitionHeader']")
	public WebElement AddPartitionHeader;
	
	@FindBy(xpath = "//strong[contains(.,'Cancel')]")
	public WebElement BUT_CANCEL;
	
	@FindBy(xpath = "//input[contains(@id,'displayName')]")
	public WebElement DisplayName;
	
	@FindBy(xpath = "//a[@id='confirm-add-partition']")
	public WebElement But_Add_Partition;
	
	@FindBy(xpath = "//h3[@id='removeConfirmHeader1']")
	public WebElement RemoveConfirmHeader1;
	
	@FindBy(xpath = "//a[@id='confirm-remove']")
	public WebElement But_Confirm_remove;
}
