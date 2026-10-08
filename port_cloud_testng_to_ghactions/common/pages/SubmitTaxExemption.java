package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubmitTaxExemption {

	public final String CloudIcon = "//div[@class='vtx-loader vtx-loader-on']";

	@FindBy(xpath = "//input[@id='clientId']")

	public WebElement Client;

	@FindBy(id = "sellerCd")

	public WebElement Seller;

	@FindBy(id = "buyCd")

	public WebElement BuyerCode;

	@FindBy(xpath = "//button[contains(.,'Update wizard Parameters')]")

	public WebElement UpdateWizardPar;

	@FindBy(xpath = "//button[@id='wizard-btn']")

	public WebElement SUBMITTAXEXEMPTION;

	@FindBy(xpath = "//button[@id='tax-exemption-button']")

	public WebElement SUBMITTAXEXEMPTIONNEW;

	@FindBy(tagName = "iframe")

	public WebElement iFrame;

	@FindBy(xpath = "//button[contains(.,'Next')]")
	public WebElement NextButon;

	@FindBy(xpath = "//div[@class='form-control uploadFile']/p")
	public WebElement UploadForms;

	@FindBy(xpath = "//label[contains(.,'Select the tax type for which you are claiming an exemption')]")
	public WebElement SelectTaxType;

	@FindBy(xpath = "//div[@class='form-group form-group-sm']/input")
	public WebElement EnterTaxAmount;

	@FindBy(xpath = "//label[contains(.,'Federal Employer Identification Number')]")
	public WebElement FEIN;

	@FindBy(xpath = "//input[@type='text']")
	public WebElement FEInText;

	@FindBy(xpath = "//label[contains(.,'Are you requesting a blanket exemption or single use exemption?')]")

	public WebElement CertificateScopeScreen;

	@FindBy(xpath = "//h4[contains(.,'Property or Services being purchased')]")

	public WebElement ItemsThatQualify;

	@FindBy(xpath = "//label[contains(.,'Company Name')]/../input")

	public WebElement CompanyName;

	@FindBy(xpath = "//label[contains(.,'Street Address')]/../input")

	public WebElement StreetAddress;

	@FindBy(xpath = "//label[contains(.,'City')]/../input")

	public WebElement City;

	@FindBy(xpath = "//label[contains(.,'State')]/../select")

	public WebElement SelectState;

	@FindBy(xpath = "//label[contains(.,'Postal Code')]/../input")

	public WebElement PostalCode;

	@FindBy(xpath = "//label[contains(.,'Country')]/../select")

	public WebElement Country;

	@FindBy(xpath = "//label[contains(.,'Phone Number')]/../input")

	public WebElement PhoneNumber;

	@FindBy(xpath = "//label[contains(.,'Your company email')]/../input")

	public WebElement Email;

	@FindBy(xpath = "//label[contains(.,'Name')]/../input")

	public WebElement Name;

	@FindBy(xpath = "//label[contains(.,'Title')]/../input")

	public WebElement Title;

	@FindBy(xpath = "//label[contains(.,'Effective Date')]/../div/div/div/input")

	public WebElement Date;

	@FindBy(xpath = "//button[contains(.,'Upload')]")

	public WebElement UploadSignature;

	@FindBy(xpath = "//p[contains(.,'Drop an image or click to select a file to upload.')]")

	public WebElement DropImage;

	@FindBy(xpath = "//button[contains(.,'Preview Form')]")

	public WebElement PreviewForm;

	@FindBy(xpath = "//button[contains(.,'Submit')]")

	public WebElement Submit;

	@FindBy(xpath = "//div[@class='react-datepicker-wrapper']/div/input")

	public WebElement EffectiveDate;

	@FindBy(xpath = "//h4[contains(.,'Form Identification')]")

	public WebElement FormIdentification;

	@FindBy(xpath = "h4[contains(.,'SS UTA Cert of Exemption - Multi State')]")

	public WebElement SSUTA;

	@FindBy(xpath = "//label[contains(.,'Please enter the document number')]/../input[@type='text']")

	public WebElement DocumentNumber;

	@FindBy(xpath = "//h4[contains(.,'SS UTA Cert of Exemption - Multi State')]")

	public WebElement CertofExemption;

	@FindBy(xpath = "//label[contains(.,'Provide two-letter abbreviation of state for Tax ID number provided')]/../input")

	public WebElement TwoletterAbb;

	@FindBy(xpath = "//label[contains(.,'Provide purchaser's State Tax ID Number')]/../input")

	public WebElement StateTaxID;

	@FindBy(xpath = "//label[contains(.,'Select your type of business')]/../select")

	public WebElement SelectBusinessType;

	@FindBy(xpath = "//label[contains(.,'Arkansas exempt identification number')]/../input")

	public WebElement IDNum1;

	@FindBy(xpath = "//label[contains(.,'Indiana exempt identification number')]/../input")

	public WebElement IDNum2;

	@FindBy(xpath = "//label[contains(.,'Iowa exempt identification number')]/../input")

	public WebElement IDNum3;

	@FindBy(xpath = "//label[contains(.,'Please Select any Local Jurisdictions for exemption')]")

	public WebElement LocalJurisdictions;

	@FindBy(xpath = "//label[contains(.,'Please choose one of these Resale options')]")

	public WebElement ResaleOptions;

	@FindBy(xpath = "//h4[contains(.,'Uniform Sales&Use Tax Exmpt/Resale Cert Multijurisdiction')]")

	public WebElement UnifromSalesandUse;

	@FindBy(xpath = "//div[@class='form-group form-group-sm']/descendant::input[@type='text'][1]")

	public WebElement PurchasersBusiness;

	@FindBy(xpath = "//div[@class='form-group form-group-sm']/descendant::input[@type='text'][2]")

	public WebElement QualifyExample;

	@FindBy(xpath = "//input[@type='radio'][1]")

	public WebElement RadioButton;

	@FindBy(xpath = "//div[@class='form-group form-group-sm']/descendant::input[@type='text'][1]")

	public WebElement TextBox;

	@FindBy(xpath = "//div[@class='checkbox']/descendant::input[@type='checkbox'][1]")

	public WebElement CheckBoxes;

	@FindBy(xpath = "//select[1]")

	public WebElement SelectValues;

	@FindBy(xpath = "//button[contains(.,'Done')]")

	public WebElement DoneButton;

	@FindBy(xpath = "//div[contains(.,'Exemption  Certificate')]/a[contains(.,'Edit')]")

	public WebElement EditExemptCertf;

	@FindBy(id = "authClientId")

	public WebElement CLIENTID;

	@FindBy(id = "authClientSecret")

	public WebElement CLIENTSECRET;

	@FindBy(xpath = "//label[contains(.,'As the buyer, are you a registered New Mexico vendor for Sales and Use Tax?')]")

	public WebElement NEWMEXICOQUESTION2;

	@FindBy(xpath = "//span[contains(.,'Activity Log')]")

	public WebElement ACTIVITYLOG;

	@FindBy(xpath = "//button[@data-testid='button']/div/span[contains(.,'Search')]")

	public WebElement SEARCHLOG;

	@FindBy(xpath = "//span[contains(.,'Version')]")

	public WebElement VERSIONTAB;

	@FindBy(xpath = "//h3[contains(.,'Version')]/../../div[2]/div")

	public WebElement VERSIONS;

	@FindBy(xpath = "div.modal-body > div.form > form > div.form-group > div > div.input-group > div#signature-pad > div.m-signature-pad--body")

	public WebElement SIGNATURE;

	@FindBy(xpath = "//span[contains(.,'Clear')]")

	public WebElement CLEAR;

	@FindBy(xpath = "//div/div[@class='DayPickerInput']/input")

	public WebElement DATEPICKER;

	@FindBy(xpath = "//td/div[1]/a[contains(.,'Edit')]")

	public WebElement EDITCERTIFICATE;

	@FindBy(xpath = "//td/div[1]/a[contains(.,'Renew')]")

	public WebElement RENEWCERTIFICATE;

	@FindBy(xpath = "//span[contains(.,'Please enter FEIN in the following format ##-#######.')]")

	public WebElement FEINVERBIAGE;

	@FindBy(xpath = "//div[@class='modal-header vertexBlue']/button[@class='close']")

	public WebElement CLOSEBUTTON;

	@FindBy(xpath = "//span[contains(.,'Settings')]")

	public WebElement SETTINGS;

	@FindBy(xpath = "//a[contains(.,'All Certificate Settings')]")

	public WebElement ALLCERTSETTINGS;

	@FindBy(xpath = "//input[@id='ecw-rejection-letter-url']")

	public WebElement REJECTURL;

	@FindBy(xpath = "//button[contains(.,'Save')]")

	public WebElement SAVE;

	@FindBy(xpath = "//button[contains(.,'Exit')]")

	public WebElement CONFIRMBUTTON;

	@FindBy(xpath = "//span[contains(.,'Enter a date in MM/DD/YYYY format.')]")

	public WebElement INVALIDDATEFORMATE;

	@FindBy(xpath = "//span[contains(.,'Date must be greater than or equal to Today')]")

	public WebElement DATEGREATER;

	@FindBy(xpath = "//select[@id='username']")

	public WebElement SIGNINAS;

	@FindBy(xpath = "//input[@id='inputPassword']")

	public WebElement PASSWORD;

	@FindBy(xpath = "//button[@type='submit']")

	public WebElement SIGNIN;

	@FindBy(xpath = "(//span[contains(.,'Edit Link:')]/../a)[2]")

	public WebElement EDITLINK;

	@FindBy(xpath = "(//span[contains(.,'Renew Link:')]/../a)[2]")

	public WebElement RENEWLINK;

	@FindBy(xpath = "//div[@class='modal-body scrollDiv vertexBackground alert-danger']")

	public WebElement ERRORPAGE;

	@FindBy(id = "logout-link")

	public WebElement LOGOUT;

	@FindBy(id = "additional-documents-dropzone")

	public WebElement ADDITIONALDOCUMENTS;

	@FindBy(xpath = "//h4[text()='Upload Additional Documentation']")

	public WebElement ADDITIONALDOCUMENTTITLE;

	@FindBy(xpath = "//h4[text()='Additional Documentation']")

	public WebElement ADDITIONALDOCUMENTTITLEMULTI;

	@FindBy(xpath = "//button[contains(.,'Skip')]")

	public WebElement SKIP;

	@FindBy(xpath = "//div[@class='vtx-confirm modal-dialog']/descendant::button[contains(.,'Skip')]")

	public WebElement SKIPINDIALOUGE;

	@FindBy(xpath = "//p[contains(.,'The maximum number of files that can be included is 3')]")

	public WebElement MAXIMUMLIMIT;

	@FindBy(xpath = "//span[contains(.,'Remove')]")

	public WebElement REMOVE;

	@FindBy(xpath = "//div[@class='additional-document-links']/ul/li")

	public WebElement ADDITIONALUPLOADLIST;
	
	@FindBy(xpath = "//div[@class='modal-body scrollDiv vertexBackground certificateForm']//div[@class='alert alert-danger']")
	
	public WebElement LASTDEPLOYEMENTERROR;

}
