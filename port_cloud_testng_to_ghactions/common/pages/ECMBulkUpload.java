package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ECMBulkUpload {

	@FindBy(xpath = "//div[@class='ecm-cert-content-header-bottom']//a[contains(.,'BULK UPLOAD')]")
	public WebElement BulkUploadButton;

	@FindBy(xpath = "//span[contains(text(),'Bulk Upload')]")
	public WebElement BulkUploadtittle;

	@FindBy(xpath = "//div[@class='buHeaderBackToCertificates']//button[contains(.,'Back to Certificates')]")
	public WebElement BackToCertificates;

	@FindBy(xpath = "//a[contains(text(),'Download the .CSV Template')]")
	public WebElement downloadCsvTemplate;

	@FindBy(xpath = "//div[@class='buContent']/div[contains(.,'Tabular Data File (.CSV)')]/../descendant::input/../div[1]")
	public WebElement UploadCsvFile;

	@FindBy(xpath = "//div[contains(text(),'Tabular Data File (.CSV)')]")
	public WebElement UploadCsvFileTittle;

	@FindBy(xpath = "//div[@class='buContent']/div[contains(.,'Certificate Images Folder (.ZIP)')]/../descendant::input/../div[1]")
	public WebElement UploadZipFiles;

	@FindBy(xpath = "//div[contains(text(),'Certificate Images Folder (.ZIP)')]")
	public WebElement UploadZipFileTittle;

	@FindBy(xpath = "//div[@class='buHeaderMain']//a[contains(.,'BULK UPLOAD HISTORY')]")
	public WebElement BulkUploadHistoryButton;

	@FindBy(xpath = "//div[@class='buFooter']//button[contains(.,'CLEAR ALL')]")
	public WebElement ClearAllButton;

	@FindBy(xpath = "//div[@class='buFooter']//button[contains(.,'SUBMIT')]")
	public WebElement SubmitButton;
	
	@FindBy(xpath = "//div[@class='buContent']/div[contains(.,'Tabular Data File (.CSV)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title]/parent::div//parent::div//div[@class='buContentFileDelete']")
	public WebElement CSVMAX5000CERTFERRORDELETE;
	
	@FindBy(xpath = "//div[@class='buContent']/div[contains(.,'Tabular Data File (.CSV)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title='The file is the incorrect type. Allowed types: CSV, CSV']/parent::div//parent::div//div[@class='buContentFileDelete']")
	public WebElement CSVINCORRECTFILETYPEDELETE;
	
	@FindBy(xpath = "//div[@class='buContent']/div[contains(.,'Tabular Data File (.CSV)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title='The maximum number of files are reached. Only 1 files are allowed.']/parent::div//parent::div//div[@class='buContentFileDelete']")
	public WebElement CSVMAX1REACHEDFILEDELETE;
	
	@FindBy(xpath = "//div[@class='buContent']/div[contains(.,'Tabular Data File (.CSV)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title='The file is too big. Only files smaller than 30 MB are allowed.']/parent::div//parent::div//div[@class='buContentFileDelete']")
	public WebElement CSVERRORFILESIZETOBIGDELETE;
	
	@FindBy(xpath = "//div[@class='buContent']/div[contains(.,'Tabular Data File (.CSV)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title='Success']/parent::div//parent::div//div[@class='buContentFileDelete']")
	public WebElement CSVSUCCESSFILEDELETE;
	
	@FindBy(xpath = "//div[@class='buContent']/div[contains(.,'Certificate Images Folder (.ZIP)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title='The file is too big. Only files smaller than 30 MB are allowed.']/parent::div//parent::div//div[@class='buContentFileDelete']")
	public WebElement ZIPERRORFILESIZETOBIGDELETE;
	
	@FindBy(xpath = "//div[@class='buContent']/div[contains(.,'Certificate Images Folder (.ZIP)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title='Success']/parent::div//parent::div//div[@class='buContentFileDelete']")
	public WebElement ZIPSUCCESSFILEDELETE;
	
	@FindBy(xpath = "//div[@class='buContent']/div[contains(.,'Certificate Images Folder (.ZIP)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title='The file is the incorrect type. Allowed types: ZIP, PDF, PNG, GIF, JPG, WEBP']/parent::div//parent::div//div[@class='buContentFileDelete']")
	public WebElement ZIPINCORRECTFILETYPEDELETE;

}
