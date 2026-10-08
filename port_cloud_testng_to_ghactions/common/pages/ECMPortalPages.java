package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class ECMPortalPages {

	@FindBy(xpath = "//span[contains(.,'Certificates')]")
	public WebElement CERTIFICATESTAB;

	@FindBy(xpath = "//span[contains(.,'Sellers')]")
	public WebElement SELLERTAB;

	@FindBy(xpath = "//span[contains(.,'Customers')]")
	public WebElement CUSTOMERSTAB;

	@FindBy(xpath = "//span[contains(.,'Settings')]")
	public WebElement SETTINGSTAB;

	@FindBy(xpath = "//div[@class='ag-body-horizontal-scroll-viewport']")
	public WebElement HORIZONTALSCROLLBAR;

	@FindBy(xpath = "//select[@ref='eOptions1']")
	public WebElement FILTERSELECTOPTIONS;

	@FindBy(xpath = "//div[@ref='ePanelFrom1']/div/input")
	public WebElement FILTERTEXT;

	@FindBy(xpath = "//div[@ref='eInputWrapper1']/input")
	public WebElement FILTERTEXT1;

	@FindBy(xpath = "//div[@ref='ePanelFrom2']/div/input")
	public WebElement ANDORFILTERTEXT;

	@FindBy(xpath = "//div[@ref='eInputWrapper2']/input")
	public WebElement ANDORFILTERTEXT1;

	@FindBy(xpath = "//select[@ref='eOptions2']")
	public WebElement SELECTANDOROPTIONS;

	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='certificateId']")
	public WebElement FIRTSCERTIFICATE;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='code']")
	public WebElement FIRTSCUSTOMERCODE;

	@FindBy(xpath = "//div[@class='sbsHeaderLineInformationBase sbsHeaderLineInformationStatus sbsStatusRejected']")
	public WebElement CERTFICATESTATUS;
	
	@FindBy(xpath = "//div[@class='sbsHeaderLineInformationBase sbsHeaderLineInformationStatus sbsStatusApproved']")
	public WebElement CERTFICATEAPPROVESTATUS;

	@FindBy(xpath = "//button[@id='tab-contact-information']")
	public WebElement CONTACTINFORMATION;

	@FindBy(xpath = "//div[@row-id='1']/div[@col-id='certificateId']")
	public WebElement SECONDCERTIFICATE;
	
	@FindBy(xpath = "//div[@row-id='1']/div[@col-id='code']")
	public WebElement SECONDCUSTOMER;

	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='customerName']")
	public WebElement FIRSTCUSTOMERNAME;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='priority']")
	public WebElement FIRSTPRIORITY;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='source']")
	public WebElement FIRSTSOURCE;

	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='expirationDate']")
	public WebElement FIRSTEXPIRATIONDATE;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='endDate']")
	public WebElement FIRSTENDDATE;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='owner']")
	public WebElement FIRSTOWNER;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='flexField1']")
	public WebElement FIRSTCUSTOMERFIELD1;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='flexField2']")
	public WebElement FIRSTCUSTOMERFIELD2;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='flexField3']")
	public WebElement FIRSTCUSTOMERFIELD3;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='flexField4']")
	public WebElement FIRSTCUSTOMERFIELD4;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='flexField5']")
	public WebElement FIRSTCUSTOMERFIELD5;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='flexField6']")
	public WebElement FIRSTCUSTOMERFIELD6;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='flexField7']")
	public WebElement FIRSTCUSTOMERFIELD7;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='flexField8']")
	public WebElement FIRSTCUSTOMERFIELD8;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='flexField9']")
	public WebElement FIRSTCUSTOMERFIELD9;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='flexField10']")
	public WebElement FIRSTCUSTOMERFIELD10;
	
	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='jurisdiction']")
	public WebElement FIRSTJURISDICTION;

	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='reason']")
	public WebElement FIRSTREASON;

	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='certificateScope']")
	public WebElement FIRSTCERTICATESCOPE;

	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='effDate']")
	public WebElement FIRSTSTARTDATE;

	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='customerCode']")
	public WebElement FIRSTCUSTOMERNUMBER;

	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='sellerCode']")
	public WebElement FIRSTSELLERCODE;

	@FindBy(xpath = "//div[@row-id='0']/div[@col-id='certificateClassName']")
	public WebElement FIRSTCERTIFICATETYPE;
	
	@FindBy(xpath = "//input[@id='customer-name-code']")
	public WebElement ECMCUSTOMERNAME;

	@FindBy(xpath = "//select[@id='type']")
	public WebElement ECMCERTTYPE;

	@FindBy(xpath = "//input[@id='jurisdictions']")
	public WebElement ECMJURISDICTIONS;

	@FindBy(xpath = "//input[@id='reason']")
	public WebElement ECMREASON;

	@FindBy(xpath = "//label[contains(.,'SCOPE')]/../select")
	public WebElement ECMSCOPE;

	@FindBy(xpath = "//input[@id='start-date']")
	public WebElement ECMSTARTDATE;

	@FindBy(xpath = "//input[@id='expiration-dates']")
	public WebElement ECMEXPIRATIONDATES;

	@FindBy(xpath = "//input[@id='issue-date']")
	public WebElement ECMISSUEDATE;

	@FindBy(xpath = "//input[@id='end-date']")
	public WebElement ECMENDDATE;

	@FindBy(xpath = "//input[@id='review-date']")
	public WebElement ECMREVIEWDATE;

	@FindBy(xpath = "//input[@id='seller']")
	public WebElement ECMSELLERNAMECODE;

	@FindBy(xpath = "//input[@id='source']")
	public WebElement ECMSOURCE;
	
	@FindBy(xpath = "//select[@id='source']")
	public WebElement ECMSOURCE1;

	@FindBy(xpath = "//input[@id='form']")
	public WebElement ECMFORM;

	@FindBy(xpath = "//input[@id='products']")
	public WebElement ECMPRODUCTS;

	@FindBy(xpath = "//input[@id='notes']")
	public WebElement ECMNOTES;

	@FindBy(xpath = "//input[@id='flexfield-1']")
	public WebElement CUSTOMFIELD1;
	
	@FindBy(xpath = "//input[@id='flexfield-2']")
	public WebElement CUSTOMFIELD2;

	@FindBy(xpath = "//input[@id='flexfield-3']")
	public WebElement CUSTOMFIELD3;
	
	@FindBy(xpath = "//input[@id='flexfield-4']")
	public WebElement CUSTOMFIELD4;
	
	@FindBy(xpath = "//input[@id='flexfield-5']")
	public WebElement CUSTOMFIELD5;
	
	@FindBy(xpath = "//input[@id='flexfield-6']")
	public WebElement CUSTOMFIELD6;
	
	@FindBy(xpath = "//input[@id='flexfield-7']")
	public WebElement CUSTOMFIELD7;
	
	@FindBy(xpath = "//input[@id='flexfield-8']")
	public WebElement CUSTOMFIELD8;
	
	@FindBy(xpath = "//input[@id='flexfield-9']")
	public WebElement CUSTOMFIELD9;
	
	@FindBy(xpath = "//input[@id='flexfield-10']")
	public WebElement CUSTOMFIELD10;

	@FindBy(xpath = "//input[@id='contact-first-last-name']")
	public WebElement CONTACTFIRSTLASTNAME;
	
	@FindBy(xpath = "//input[@id='contact-country']")
	public WebElement CONTACTCOUNTRY;

	@FindBy(xpath = "//input[@id='contact-name']")
	public WebElement CONTACTNAME;
	
	@FindBy(xpath = "//input[@id='contact-address']")
	public WebElement CONTACTADDRESS;

	@FindBy(xpath = "//input[@id='contact-address-2']")
	public WebElement CONTACTADDRESS2;
	
	@FindBy(xpath = "//input[@id='contact-city']")
	public WebElement CONTACTCITY;

	@FindBy(xpath = "//input[@id='contact-province-state']")
	public WebElement CONTACTSTATE;

	@FindBy(xpath = "//input[@id='contact-postal-zip']")
	public WebElement CONTACTPOSTALZIP;
	
	@FindBy(xpath = "//input[@id='customer-country']")
	public WebElement CUSTOMERCOUNTRY;

	@FindBy(xpath = "//input[@id='contact-phone']")
	public WebElement CONTACTPHONE;

	@FindBy(xpath = "//input[@id='contact-email']")
	public WebElement CONTACTEMAIL;

	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='certificateId']"), })
	public List<WebElement> CERTIDGRIDLIST;

	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='created']"), })
	public List<WebElement> CREATEDGRIDLIST;

	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='priority']"), })
	public List<WebElement> PRIORITYGRIDLIST;

	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='customerName']"), })
	public List<WebElement> CUSTOMERNAMEGRIDLIST;
	
	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='jurisdiction']"), })
	public List<WebElement> JURISDICTIONGRIDLIST;

	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='reason']"), })
	public List<WebElement> REASONGRIDLIST;

	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='scope']"), })
	public List<WebElement> SCOPEGRIDLIST;

	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='effDate']"), })
	public List<WebElement> STARTDATEGRIDLIST;

	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='customerCode']"), })
	public List<WebElement> CUSTOMERNUMBERGRIDLIST;

	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='sellerCode']"), })
	public List<WebElement> SELLERCODEGRIDLIST;

	@FindBy(xpath = "//div[@class='ag-filter-date-to']/div/input")
	public WebElement TORANGE;

	@FindBy(xpath = "//div[@class='ag-filter-date-from' and @ref='ePanelFrom1']/div/input")
	public WebElement FROMRANGE;

	@FindBy(xpath = "(//button[@class='utility-control__btn']//*[local-name() = 'svg'])[2]")
	public WebElement LOGOUTICON;

	@FindBy(xpath = "(//button[@class='utility-control__btn']//*[local-name() = 'svg'])[1]")
	public WebElement HELPICON;

	@FindBy(xpath = "//div[@ref='eRightContainer']/div[@row-id='0']/div/div/div/div/button")
	public WebElement ELLIPSE;

	@FindBy(xpath = "//button[contains(.,'Reject')]")
	public WebElement ECMCERTREJECT;

	@FindBy(xpath = "//button[contains(.,'REJECT')]")
	public WebElement ECMCERTREJECTBUTTON;

	@FindBy(xpath = "//div[@class='crViewerButtons']/button[contains(.,'REJECT')]")
	public WebElement ECMCERTREJECTBUTTONPOPUP;

	@FindBy(xpath = "//div[@class='sbsHeaderLineClose']/button")
	public WebElement CLOSEICON;

	@FindBy(xpath = "//h1[contains(.,'User Login')]")
	public WebElement USERLOGINPAGE;

	@FindBy(xpath = "//h1[contains(.,'Welcome to the Vertex® Exemption Certificate Wizard Portal')]")
	public WebElement WELCOMETOHELPCENTRE;

	@FindBy(xpath = "//dd")
	public WebElement USERINFO;

	@FindBy(xpath = "//li[contains(.,'©2020 Vertex, Inc. All rights reserved.')]")
	public WebElement CopyRightText;

	@FindBy(xpath = "//u[contains(.,'Table Settings')]")
	public WebElement TABLESETTINGSICON;

	@FindBy(id = "select-all")
	public WebElement SELECTALL;

	@FindBy(id = "clear-all")
	public WebElement CLEARALL;

	@FindBy(xpath = "//button[contains(.,'APPLY')]")
	public WebElement APPLYBUTTON;

	@FindBy(xpath = "//button[contains(.,'CANCEL')]")
	public WebElement CANCELBUTTON;

	@FindBy(xpath = "//div[@class='vtx-select-btn__pill flex flex--justify-space-between bullet-pending']")
	public WebElement CERTIFICATEDROPDOWN;

	@FindBy(xpath = "//button[contains(.,'All')]")
	public WebElement ALL;

	@FindBy(xpath = "//button[contains(.,'Rejected')]")
	public WebElement REJECTEDSTATUS;

	@FindBy(xpath = "//button[contains(.,'Approved')]")
	public WebElement APPROVEDSTATUS;

	@FindBys({ @FindBy(xpath = "//span[@class='pagination__numbered-link']"), })
	public List<WebElement> PAGELIST;

	@FindBy(xpath = "//div[@class='flex--align-items-center']")
	public WebElement RECORDSCOUNT;

	@FindBy(xpath = "//button[@id='tab-additional-documentation']")
	public WebElement ADDITIONALDOCUMENTATION;

	@FindBys({
		@FindBy(xpath = "(//div[@class='sbsContentLeftTabContent'])[2]//div[@class='sbsField100']"), })
	public List<WebElement> ADDITITONALLINKSLIST;

	@FindBy(xpath = "//span[@class='custom-cell-link']//*[local-name()='svg']")
	public WebElement VERIFYPAPERCLIP;
	
	// Customer Queue
	
	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='code']"), })
	public List<WebElement> CUSTOMERCODEGRIDLIST;
	
	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='effectiveDate']"), })
	public List<WebElement> CUSTOMERSTARTDATEGRIDLIST;
	
	@FindBys({
		@FindBy(xpath = "//div[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']/descendant::div[@col-id='endDate']"), })
	public List<WebElement> CUSTOMERENDDATEGRIDLIST;
	
}
