/*###################################################################################################
Name: CalculateTaxesPage.java 
Description: Calculate Taxes page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class CalculateTaxesPage {

	@FindBy(id = "form-date")
	public WebElement TRANSDATE;

	@FindBy(id = "transaction-number")
	public WebElement TRANSNO;

	@FindBy(xpath = "//div[contains(text(),'You have unpublished configuration changes.')]")
	public WebElement UNPUBLISHED_CONFIGURATION;

	@FindBy(xpath = "//*[contains(text(),'Your configuration is not available for posting transactions to your journal.')]")
	public WebElement UNPUBLISHEDPUSH_CONFIGURATION;

	@FindBy(xpath = "//select[@id='tax-payer']")
	public WebElement TAXPAYER;

	@FindBy(xpath = "//button[contains(.,'Cancel Transaction')]")
	public WebElement CANCELTRANSACTION;

	@FindBy(id = "customer")
	public WebElement CUSTOMER;

	@FindBy(id = "location")
	public WebElement LOCATION;

	@FindBy(xpath = "//label[text()='Code/Class']/../select")
	public WebElement PRODUCT;

	@FindBy(xpath = "//div[@id='transactionDateContainer']/label/span")
	public WebElement TRANSACTIONDATESTAR;

	// Shipped To Xpaths
	@FindBy(xpath = "//select[@id='contact-1-country']")
	public WebElement SHIPCOUNTRY;

	@FindBy(xpath = "//select[@id='contact-2-country']")
	public WebElement SHIPCOUNTRYBUYER;

	@FindBy(xpath = "//select[@id='contact-2-state']")
	public WebElement SHIPSTATEBUYER;

	@FindBy(id = "contact-2-zip")
	public WebElement SHIPZIPBUYER;

	//// PHYSICAL ORIGIN SHIPPED FROM COUNTRY
	@FindBy(xpath = "//div/div/h4[contains(.,'Physical Origin (Shipped From)')]/../international-contact-address/div[2]/div/div/div/label[contains(.,'Country')]/../select")
	public WebElement PHYSICALCOUNTRY;

	@FindBy(id = "contact-1-street1")
	public WebElement SHIPSTREETADDRESS;

	@FindBy(id = "contact-1-city")
	public WebElement SHIPCITY;

	@FindBy(id = "contact-1-county")
	public WebElement SHIPCOUNTY;

	@FindBy(xpath = "//select[@id='contact-1-state']")
	public WebElement SHIPSTATE;

	@FindBy(id = "contact-1-zip")
	public WebElement SHIPZIP;

	@FindBy(xpath = "//div[@class='span6 shipping-options']/div/ul/li[1]/a")
	public WebElement PHYSICALORIGIN;

	@FindBy(xpath = "//div/div/h4[contains(.,'Physical Origin (Shipped From)')]/../international-contact-address/div[2]/div/div[3]/div[3]/div/label[contains(.,'State')]/../select")
	public WebElement PHYSICALORIGINSTATE;

	@FindBy(xpath = "//p[contains(.,'Enter Physical Destination By')]/../international-contact-address/div[2]/div/div[3]/div[4]/div/div/input")
	public WebElement PHYSICALORIGINZIP;

	@FindBy(xpath = "//div[@class='span6 shipping-options']/div/ul/li[1]/a")
	public WebElement ADMINDESTINATION;

	@FindBy(xpath = "//div/div/h4[contains(.,'Administrative Destination (Benefit Received For Services)')]/../international-contact-address/div[2]/div/div[3]/div[3]/div/label[contains(.,'State')]/../select")
	public WebElement ADMINDESTINATIONSTATE;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../international-contact-address/div[2]/div/div[3]/div[4]/div/div/input")
	public WebElement ADMINDESTINATIONZIP;

	@FindBy(xpath = "//div[@class='span6 shipping-options']/div/ul/li[1]/a")
	public WebElement ORDERRECEIVEDTAKEN;

	@FindBy(xpath = "//div/div/h4[contains(.,'Administrative Origin (Order Received Or Order Taken)')]/../international-contact-address/div[2]/div/div[3]/div[3]/div/label[contains(.,'State')]/../select")
	public WebElement ORDERRECEIVEDSTATE;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../international-contact-address/div[2]/div/div[3]/div[4]/div/div/input")
	public WebElement ORDERRECEIVEDZIP;

	// Shipped From Xpaths
	@FindBy(xpath = "//div/div/h4[contains(.,'Physical Origin (Shipped From)')]/../international-contact-address/div[2]/div/div/div/label[contains(.,'Country')]/../select")
	public WebElement POCOUNTRY;

	@FindBy(id = "contact-2-street1")
	public WebElement POSTREETADDRESS;

	@FindBy(id = "contact-2-city")
	public WebElement POCITY;

	@FindBy(xpath = "//input[@id='contact-2-county']")
	public WebElement POCOUNTY;

	@FindBy(xpath = "//div/div/h4[contains(.,'Physical Origin (Shipped From)')]/../international-contact-address/div[2]/div/div[3]/div[3]/div/label[contains(.,'State')]/../select")
	public WebElement POSTATE;

	@FindBy(xpath = "//div/div/h4[contains(.,'Physical Origin (Shipped From)')]/../international-contact-address/div[2]/div/div[3]/div[3]/div/label[contains(.,'State')]/../select")
	public WebElement POCSTATE;

	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../international-contact-address/div[2]/div/div[3]/div[4]/div/div/input")
	public WebElement POZIP;

	@FindBy(xpath = "//div/div/h4[contains(.,'Physical Origin (Shipped From)')]/../international-contact-address/div[2]/div/div/div/label[contains(.,'Country')]/../select")
	public WebElement FROMCOUNTRY;

	@FindBy(xpath = "Add Another Line Item")
	public WebElement PRODUCTADDLINEITEM;

	@FindBy(xpath = "//label[text()='Product']/../select")
	public WebElement PRODUCTNAME;

	@FindBy(xpath = "//div[3]/input")
	public WebElement PRODUCTPRICE;

	@FindBy(xpath = "//div[4]/input")
	public WebElement PRODUCTQTY;

	@FindBy(xpath = "//a[contains(text(),'View Additional Options')]")
	public WebElement VIEWADDITIONALOPTIONLINK;

	@FindBy(xpath = "//button[contains(text(),'Close & Return to Top')]")
	public WebElement CLOSEANDRETURN;

	@FindBy(xpath = "//span[contains(text(),'Calculate Tax')]")
	public WebElement CALTAX;

	@FindBy(xpath = "//a[@id='calculate-taxes']")
	public WebElement CALCULATETAXES;

	@FindBy(xpath = "//a[@disabled='disabled']")
	public WebElement DiableCalButton;

	@FindBy(xpath = "//div[@id='transaction-preview-container']/div/div/table/tbody/tr/td[6]/span")
	public WebElement TOTALTAX;

	public WebElement JURISDICTION;

	@FindBy(xpath = "//a[@id='post-transaction']")
	public WebElement POSTTRANSACTION;

	@FindBy(xpath = "//a[@title='Calculate Taxes']")
	public WebElement CALTAXES;

	@FindBy(xpath = "//div[@class='blockUI blockMsg blockPage']")
	public WebElement PLEASEWAIT;

	@FindBy(xpath = "//div[@class='ng-binding ng-scope']")
	public WebElement ERROR_CALCULATE;

	@FindBy(xpath = "//div[@class='ng-binding ng-scope']/span[contains(text(),'Transaction Posting Rejected: You are not registered to file in this jurisdiction')]")
	public WebElement POSTING_REJECTED;

	@FindBy(xpath = "//div[@class='alert alert-error ng-scope']/div[contains(text(),'Transaction Posting Rejected:')]")
	public WebElement COMPANYREMOVED;

	// For Dashboard verification
	@FindBy(xpath = "//th[text()='Total']/../td")
	public WebElement TOTALAMOUNT;

	@FindBy(xpath = "//th[text()='Total Tax']/../td")
	public WebElement TOTALTAXAMOUNT;

	// Administrative origin
	@FindBy(xpath = "//div/div[2][@class='span6 shipping-options']/div[2]/ul/li[3]/a[contains(text(),'Administrative Origin (order received or order taken)')]")
	public WebElement AOLINK;

	@FindBy(xpath = "//a[contains(.,'Physical Origin (Shipped From)')]")
	public WebElement POLINK;

	@FindBy(xpath = "//div/div[2][@class='span6 shipping-options']/div[2]/h4")
	public WebElement AOTEXT;

	@FindBy(xpath = "//div/div[2][@class='span6']/div/ul/li[4]/a[contains(text(),'Administrative Origin (order received or order taken)')]")
	public WebElement ADOAOLINK;

	@FindBy(xpath = "//div/div[2][@class='span6']/div/h4[contains(text(),'Administrative Origin (order received or order taken)')]")
	public WebElement ADOAOTEXT;

	@FindBy(id = "contact-2-city")
	public WebElement AOCITY;

	@FindBy(id = "contact-3-city")
	public WebElement AOVACITY;

	@FindBy(xpath = "//div[@class='ng-binding ng-scope' and contains(text(),'Some fields are invalid. Correct them and resubmit.')]")
	public WebElement ERRORPCODE;

	@FindBy(xpath = "//span[text()='Line Item #1:  Administrative Origin (order received or order taken) Postal Code is required when other address elements have been provided (Street Address, City, County, State)']")
	public WebElement ERRORPCODE1;

//	@FindBy(xpath = "//img[@alt='Tax Central by Vertex SMB']")
	public WebElement VERTEXLOGO;

	@FindBy(xpath = "//select[@id='discount']")
	public WebElement DISCOUNTCODESDROPDOWN;

	@FindBy(xpath = "//input[@id='discount-amount']")
	public WebElement DISCOUNTVALUE;

	@FindBy(xpath = "//input[@id='line-item-0-discount-amount']")
	public WebElement LINEITEMDISCOUNTVALUE;

	@FindBy(xpath = "//select[@class='input-block-level ng-valid ng-touched ng-dirty ng-valid-parse']")
	public WebElement DISCOUNTLINEITEMSDD;

	@FindBy(xpath = "//select[@id='discount']")
	public WebElement DISCODEDRPDN;

	@FindBy(xpath = "//input[@id='discount-amount']")
	public WebElement DISVALUE;

	@FindBy(xpath = "//div[@class='span6' and @ng-show='data.discount']/div/label[contains(.,'% - Percentage')]")
	public WebElement DISCPER;

	@FindBy(xpath = "//div[@class='span6']/div/label[contains(.,' $ - Dollar Amount')]")
	public WebElement DOLLARAMT;

	@FindBy(xpath = "//select[@id='shippingTerms']")
	public WebElement SHIPPINGTERM;

	@FindBy(xpath = "//select[@id='shippingTerms']/option[text()='None']/../option[text()='Supplier']")
	public WebElement VRFYSHIPPINGTRM;

	@FindBy(xpath = "//select[@ng-model='ngModel.country']")
	public WebElement TOCOUNTRY;

	@FindBy(xpath = "//div[@class='span6' and @ng-show='line.discount']/div/label[contains(.,'% - Percentage')]")
	public WebElement DISCPERPRO;

	@FindBy(xpath = "//button[contains(text(),'Update Results')]")
	public WebElement UPDATERESULTS;

	@FindBy(xpath = "//span[contains(text(),'Calculate Tax')]")
	public WebElement CALTAX_LINKK;

	@FindBy(id = "form-date")
	public WebElement TRANSDATE_IDD;

	@FindBy(id = "transaction-number")
	public WebElement TRANSNO_IDD;

	@FindBy(xpath = "//div/p/a[contains(.,'Add Another Line Item')]")
	public WebElement ANOTHERLINEITEM;

	@FindBy(xpath = "//a[contains(.,'Physical Destination (Shipped To)')]")
	public WebElement PHYDESTSHIPPEDTO;

	@FindBy(xpath = "//div[contains(text(),'Your transaction has been posted successfully.')]")
	public WebElement TRANSPOSTSUCCESS;

	@FindBy(xpath = "//label[contains(.,'Message Type')]")
	public WebElement MessageTypelabel;

	@FindBy(xpath = "//label[contains(.,'Code/Class')]")
	public WebElement CodeClass;

	@FindBy(xpath = "//label[contains(.,' Charged Tax')]")
	public WebElement ChargedTaxlabelOptional;

	@FindBy(xpath = "//input[@required='required']/../label[contains(.,'Charged Tax')]")
	public WebElement ChargedTaxlabelMandatory;

	@FindBy(xpath = "//a[contains(.,'More Options')]")
	public WebElement MoreOptionslink;

	@FindBy(xpath = "//select[@id='messageType']/option[contains(.,'Accrual')]")
	public WebElement Accrual;

	@FindBy(xpath = "//select[@id='messageType']/option[contains(.,'Asset/Goods Movement')]")
	public WebElement AssetGoodsMovement;

	@FindBy(xpath = "//select[@id='messageType']/option[contains(.,'Inventory Removal')]")
	public WebElement InventoryRemoval;

	@FindBy(xpath = "//select[@id='messageType']/option[contains(.,'Invoice Verification')]")
	public WebElement InvoiceVerification;

	@FindBy(xpath = "//select[@id='messageType']/option[contains(.,'Purchase Order')]")
	public WebElement PurchaseOrder;

	@FindBy(xpath = "//select[@id='messageType']")
	public WebElement MessageType;

	@FindBy(xpath = "//label[contains(.,'Movement Method')]")
	public WebElement Movementmethodlabel;

	@FindBy(xpath = "//select[@id='movementMethod']/option[contains(.,'Choose one')]")
	public WebElement Movementmethoddefaultvalue;

	@FindBy(xpath = "//select[@id='movementMethod']/option[contains(.,'Call_Off')]")
	public WebElement MovementmethodCallOff;

	@FindBy(xpath = "//select[@id='movementMethod']/option[contains(.,'Consignment')]")
	public WebElement MovementmethodConsignment;

	@FindBy(xpath = "//select[@id='movementMethod']/option[contains(.,'Regular')]")
	public WebElement MovementmethodRegular;

	@FindBy(xpath = "//input[@id='line-item-0-chargedTax']")
	public WebElement chargedtaxfield;

	@FindBy(xpath = "//tr/th[2][contains(.,'Code/Class')]")
	public WebElement CodeClassresultcolumn;

	@FindBy(xpath = "//tr/th[5][contains(.,'Variance')]")
	public WebElement Varianceresultcolumn;

	@FindBy(xpath = "//td[2][@class='text-center ng-binding']")
	public WebElement CodeClassvalue;

	@FindBy(xpath = "//td[5][@class='text-center ng-binding ng-scope']")
	public WebElement Variancevalue;

	@FindBy(xpath = "//label[contains(.,'Document Number')]")
	public WebElement Transactionnumberlabel;

	@FindBys({ @FindBy(xpath = "//select[@id='location']/option"), })
	public List<WebElement> LocationsList;

	public final String AOLINK_LINK = "//div/div[2][@class='span6 shipping-options']/div[2]/ul/li[3]/a[contains(text(),'Administrative Origin (order received or order taken)')]";
	public final String TRANSDATE_ID = "form-date";
	public final String POSTTRANSACTION_XPATH = "//a[@id='post-transaction']";
	public final String CLOSEANDRETURN_XPATH = "//button[contains(text(),'Close & Return to Top')]";
	public final String PRODUCT_XPATH = "//label[text()='Product']/../select";
	// CD-781 On the Calculate Tax Screen allow a User to calculate tax by the TAID
	// on the Ship to Address.

	@FindBy(xpath = "//p[contains(.,'Enter Physical Destination By')]")
	public WebElement DestinationType;

	@FindBy(xpath = "//label[@class='radio' and @for='lbl-1-Addr']/input")
	public WebElement ADDRADIO1;

	@FindBy(xpath = "//label[@class='radio' and @for='lbl-1-Taid']/input")
	public WebElement TAIDRADIO1;

	@FindBy(xpath = "//label[@class='radio' and @for='lbl-2-Addr']/input")
	public WebElement ADDRADIO2;

	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../contact-address/div[1]/label[2]/input")
	public WebElement POTAID;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../contact-address/div[1]/label[2]/input")
	public WebElement ADTAID;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../contact-address/div[1]/label[2]/input")
	public WebElement AOTAID;

	@FindBy(xpath = "//label[@class='radio' and @for='lbl-2-Taid']/input")
	public WebElement TAIDRADIO2;

	@FindBy(xpath = "//a[contains(.,'Physical Origin (Shipped From)')]")
	public WebElement PhysicalorginShippedform;

	@FindBy(xpath = "//a[contains(.,'Administrative Destination (Benefit Received For Services)')]")
	public WebElement AdminDestinationBRFS;

	@FindBy(xpath = "//a[contains(.,'Administrative Origin (Order Received Or Order Taken)')]")
	public WebElement AdminorginOROT;
	
	// 781- PhysicalorginShippedform
	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../international-contact-address[1]/div[2]/div[1]/div[1]/div[1]/select")
	public WebElement POFROMCOUNTRY;

	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../international-contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/select")
	public WebElement POFROMSTATE;

	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../international-contact-address[1]/div[2]/div[1]/div[3]/div[4]/div[1]/div[1]/input")
	public WebElement POFROMZIP;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../international-contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/select")
	public WebElement ADSTATE;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../international-contact-address[1]/div[2]/div[1]/div[3]/div[4]/div[1]/div[1]/input")
	public WebElement ADZIP;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../international-contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/select")
	public WebElement AOSTATE;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../international-contact-address[1]/div[2]/div[1]/div[3]/div[4]/div[1]/div[1]/input")
	public WebElement AOZIP;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../international-contact-address[1]/div[2]/div[1]/div[3]/div[2]/div[1]/select")
	public WebElement AOPROVINCE;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../international-contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/div[1]/input")
	public WebElement AOCANADAZIP;

	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../international-contact-address/div/label[2]/input")
	public WebElement POtaxareaid;

	@FindBy(id = "contact-1-taxareaid")
	public WebElement TOtaxareaid;

	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../international-contact-address/div/div/div/div/div/select")
	public WebElement POPROVINCE;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../international-contact-address/div/div/div/div/div/select")
	public WebElement ADPROVINCE;

	@FindBy(xpath = "//p[contains(.,'Enter Physical Origin By')]/../international-contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/div[1]/input[1]")
	public WebElement POCANADAZIP;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../international-contact-address[1]/div[2]/div[1]/div[3]/div[3]/div[1]/div[1]/input[1]")
	public WebElement ADCANADAZIP;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../international-contact-address/div/label[2]/input")
	public WebElement ADtaxareaid;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../international-contact-address/div/label[2]/input")
	public WebElement AOtaxareaid;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Destination By')]/../international-contact-address/div/div/div/div/select")
	public WebElement ADCOUNTRY;

	@FindBy(xpath = "//p[contains(.,'Enter Administrative Origin By')]/../international-contact-address/div/div/div/div/select")
	public WebElement AOCOUNTRY;
	
	// CD-364 Cloud Portal - Calculate Taxes - Display Tax Assist Rules applied
	// during tax calculation
	@FindBy(xpath = "//tr/td/a/i[@class='icon-add icon-blue']")
	public WebElement Morelink;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Type')]/../th[contains(.,'Jurisdiction')]/../th[contains(.,'Tax Type')]/../th[contains(.,'Taxable')]/../th[contains(.,'Nontaxable')]/../th[contains(.,'Exempt')]/../th[contains(.,'Tax')]/../th[contains(.,'Tax Rate')]")
	public WebElement AdditionalInformationcolumns;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Code/Class')]/../th[contains(.,'Qty')]/../th[contains(.,'Subtotal')]/../th[contains(.,'Total Tax')]/../th[contains(.,'Total Tax Rate')]/../th[contains(.,'Jurisdiction Count')]")
	public WebElement LINEITEMTOTALSCOLUMNS;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Assisted Parameter Name')]/../th[contains(.,'Assisted Parameter Value')]/../th[contains(.,'Assisted Phase')]/../th[contains(.,'Rule Name')]")
	public WebElement AppliedTaxAssistRulescolumns;

	@FindBy(xpath = "//table/tbody/tr[2]/td/div/table/tbody/tr[1]/td[1]")
	public WebElement PARAMETERNAME;

	@FindBy(xpath = "//table/tbody/tr[2]/td/div/table/tbody/tr[1]/td[2]")
	public WebElement PARAMETEVALUE;

	@FindBy(xpath = "//table/tbody/tr[2]/td/div/table/tbody/tr[1]/td[3]")
	public WebElement PHASE;

	@FindBy(xpath = "//table/tbody/tr[2]/td/div/table/tbody/tr[1]/td[4]")
	public WebElement RULENAME;

	@FindBy(xpath = "//h4[contains(text(),'Additional Information')]")
	public WebElement ADDITIONALINFORMATIONTEXT;

	@FindBy(xpath = "//h4[contains(text(),'Applied Tax Assist Rules')]")
	public WebElement APPLIEDTAXASSISTRULESTEXT;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Tax Level')]/../th[contains(.,'Jurisdiction')]/../th[contains(.,'Tax Type ')]/../th[contains(.,'Imposition Type')]/../th[contains(.,'Taxable')]/../th[contains(.,'Nontaxable')]/../th[contains(.,'Exempt')]/../th[8][contains(.,'Tax')]")
	public WebElement TOTALTABLECOLUMNS;

	@FindBy(xpath = "//table/tbody/tr[2]/td/table/tbody/tr/td[3]")
	public WebElement TAXTYPE;

	@FindBy(id = "vat-registration-number")
	public WebElement VATREGNO;

	@FindBy(id = "vat-physical-presence-indicator")
	public WebElement VATPHYSICALPRESENCE;

	@FindBy(xpath = "//input[@id='company-status']")
	public WebElement COMPANYSTATUS;

	@FindBy(xpath = "//a[contains(.,'Add Another Line Item')]")
	public WebElement AddAnotherLineItem;

}
