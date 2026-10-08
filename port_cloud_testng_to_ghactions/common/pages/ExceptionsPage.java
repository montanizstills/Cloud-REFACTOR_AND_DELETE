/*###################################################################################################
Name: ExceptionsPage.java 
Description: Professional User - Configure Exceptions Page objects with all xpaths
Author: Razia Sultana
Date Created:01/20/2014
Date Modified:04/16/2014
Updated Comments:
#####################################################################################################*/
package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class ExceptionsPage {

	public final String THISFIELDISREQ_XPATH = "//span[contains(.,'This field is required.')]";
	public final String RETRURNTOCONFIGUREOVERVIEW_XPATH = "//p/a[contains(.,'Return to Configuration Overview')]";
	@FindBy(xpath = "//a[text()='Add an Exemption']")
	public WebElement ADDEXCEPTIONSLINK;
	
	@FindBy(xpath = "//select[@id='ddlCompany']")
	public WebElement COMPANYNAME;
	
	@FindBy(xpath = "//select[@id='customer-type']")
	public WebElement CUSTOMERTYPE;
	
	@FindBy(id = "customer-code")
	public WebElement CUSTOMERCODE;
	
	@FindBy(xpath = "button contain")
	public WebElement ADDEXCEPTIONBTNDISABLE;
	
	@FindBy(id = "customer-name")
	public WebElement CUSTOMERNAME;
	
	@FindBy(id = "quick-search-customers")
	public WebElement SEARCHCUSTOMERNAME;
	
	@FindBy(id = "start-date")
	public WebElement STARTDATE_CUST;

	@FindBy(id = "companyenddate")
	public WebElement ENDDATE_CUST;

	@FindBy(id = "tax-result")
	public WebElement TAXRESULTS;
	
	@FindBy(xpath = "//select[@id='certification-class']")
	public WebElement EXCEPTIONTYPE;
	
	@FindBy(xpath = "//input[@id='certificate-start-date']")
	public WebElement STARTDATE_CERT;

	@FindBy(xpath = "//input[@id='certificate-end-date']")
	public WebElement ENDDATE_CERT;
	
	@FindBy(xpath = "//select[@id='certificate-state-select']")
	public WebElement STATE;
	
	@FindBy(xpath = "//input[@data-bind='value: certificateNumber']")
	public WebElement EXCEPTIONNUMBER;
	
	@FindBy(xpath = "//select[@id='cert-reason1']")
	public WebElement EXCEPTIONREASON;
	
	@FindBy(xpath = "//select[@id='cert-reason1']/option")
	public WebElement EXCEPTIONVALUE;
	
	@FindBy(xpath = "//label[contains(.,'Resale')]/input")
	public WebElement RESALE;
	
	@FindBy(xpath = "//select[@id='prodcodeselect']")
	public WebElement PRODUCTCODE;
	
	@FindBy(xpath = "//label[contains(.,'Government')]/input")
	public WebElement GOVERNMENT;
	
	@FindBy(xpath = "//label[contains(.,'Exempt Organization')]/input")
	public WebElement EXEMPT;
	
	// @FindBy(xpath="//input[@id='customer-details-end-date']")
	public WebElement ENDDATE;
	
	@FindBy(xpath = "//a[contains(.,'Add Another Certificate')]")
	public WebElement ADDANOTHEREXCEPTION;
	
	@FindBy(xpath = "//button[contains(text(),'Save & Close')]")
	public WebElement ADDEXCEPTION;
	
	@FindBy(xpath = "//button[contains(text(),'Save & Close')]")
	public WebElement SAVEANDCLOSE;
	
	@FindBy(xpath = "//button[contains(.,'Save & Add Another')]")
	public WebElement SAVEANDADDANOTHER;
	
	@FindBy(xpath = "//div[@class='alert alert-error']/span[text()='Configuration save failed']")
	public WebElement ERRORTEXT;
	
	@FindBy(xpath = "//a[@class='btn btn-primary pull-right']")
	public WebElement READYTOSAVE;
	
	@FindBy(xpath = "//a[contains(text(),'Save Configuration')]")
	public WebElement SAVECONFIGURATION;
	
	@FindBy(xpath = "//button[text()='YES TO COMPLETE  CONFIGURATION']")
	public WebElement COMPLETECONFIGPOPUP;

	@FindBy(xpath = "//div[1]/div/span[@class='badge badge-large badge-error ng-binding']")
	public WebElement EXCEPTIONCOUNT;

	@FindBy(xpath = "//div/label[contains(.,'Default tax result for this customer or customer class*')]")
	public WebElement TAXRESULTTEXT;
	
	////// 6.2//////
	@FindBy(xpath = "//button[contains(.,'Validate Information')]")
	public WebElement ValidateBtn;
	
	@FindBy(xpath = "//h3[contains(.,'Certificate Validation Format')]")
	public WebElement ExNumFormtLabel;
	
	@FindBy(xpath = "//div[contains(text(),'Validation:')]")
	public WebElement ExNumVldLabel;
	
	@FindBy(xpath = "//div[contains(text(),'The following exception number formats are valid for')]")
	public WebElement ExRsnJuriVldLabel;
	
	@FindBy(xpath = "//div/h3[contains(.,'Exemption Number Format')]/../span/button[@class='close']")
	public WebElement closepopup;
	

	// **********Added objects for EPIC9-AddingFirstCustomerExcep**********

	@FindBy(xpath = "//div[@class='row-fluid']/div[2][@class='span6']/div[2][@class='well']/div[@class='registration-block']/h3[contains(text(),'Configuration Not Complete')]")
	public WebElement CONFIGNOTCOMPLETE;
	
	@FindBy(xpath = "//div/span[@class='badge badge-success badge-large' and @data-bind='text: $root.config().activeCustomersCount()']")
	public WebElement CONFIGCOMPLETED;
	
	@FindBy(xpath = "//a[@ng-click='dupCert(certificate)']")
	public WebElement DUPLICATECERTLINK;

	// **********Added objects for
	// Epic11-EditCustEx-AfterPush-OS******************************

	@FindBy(xpath = "//div/a[contains(text(),'Total Exemptions')]")
	public WebElement TOTALEXCEPTIONSLINK;

	@FindBy(xpath = "//input[@id='customer-name']")
	public WebElement EDITCUSTOMERNAME;

	@FindBy(xpath = "//input[@type='checkbox' and @disabled='disabled']")
	public WebElement MAPPEDCOMPANY;

	@FindBy(xpath = "//a[@class='ng-scope' and contains(text(),'Add Another Certificate')]")
	public WebElement ADDANOTHERCERTIFICATELINK;

	@FindBy(xpath = "//button[contains(text(),'Save Exemption')]")
	public WebElement SAVEEXCEPTION;

	// New Changes 4.1
	@FindBy(xpath = "//a[contains(.,'ADD A SINGLE EXEMPTION')]")
	public WebElement ADDSINGLEXCEP;

	@FindBy(xpath = "//div/span[contains(.,'Certificate Information')]")
	public WebElement CERTIINFO;

	@FindBy(xpath = "//div[@class='ng-hide']/div/a[contains(.,'ADD A NEW EXEMPTION')]")
	public WebElement ADDNEWEXCEPHIDE;

	@FindBy(xpath = "//a[contains(.,'Add A New EXEMPTION')]")
	public WebElement ADDNEWEXCEP;

	@FindBy(id = "ddlExceptionType")
	public WebElement EXCEPTYPE;

	@FindBy(id = "cert-start-date")
	public WebElement EXCEPSTRATDATE;

	@FindBy(id = "cert-end-date")
	public WebElement EXCEPENDDATE;

	@FindBy(xpath = "//tbody/tr[2]/td[6][@class='day disabled']")
	public WebElement PREVIOUSDATE;

	@FindBy(xpath = "//input[@id='exception-number']")
	public WebElement EXCEPNUM;
	
	@FindBy(xpath = "//input[@id='cert-issue-date']")
	public WebElement ISSUEDATE;
	
	@FindBy(xpath = "//input[@id='cert-review-date']")
	public WebElement REVIEWDATE;
	
	@FindBy(xpath = "//input[@id='cert-expiration-date']")
	public WebElement EXPIRATIONDATE;
	
	@FindBy(xpath = "//div/label[text()='Product Name']")
	public WebElement PRODUCTNAMELBL;

	@FindBy(xpath = "//a[contains(.,'ADD A SINGLE CERTIFICATE')]")
	public WebElement SINGLECERTILINK;
	
	@FindBy(xpath = "//a[contains(.,'ADD A NEW CERTIFICATE')]")
	public WebElement NEWCERTIBUTTON;
	
	@FindBy(xpath = "//div/a[contains(.,'Return to Configuration Overview')]")
	public WebElement RETRNCONFIGOVER;
	
	@FindBy(xpath = "//a[text()='+ Additional Jurisdiction']")
	public WebElement ADDIJURIS;
	
	@FindBy(xpath = "//a[text()='+ Additional Product Code / Class']")
	public WebElement ADDIPROD;
	
	@FindBy(id = "downloadFileFormat")
	public WebElement DNLDFILEFRMT;

	@FindBy(xpath = "//h1[contains(.,'Manage Exemption Files')]")
	public WebElement MANAGECERTLABEL;

	@FindBy(xpath = "//button[text()='Upload File']")
	public WebElement UPLOADBTN;
	
	@FindBy(xpath = "//select[@ng-model='companyId']")
	public WebElement UPLOADCMPNY;

	@FindBy(xpath = "//form/button[@class='btn btn-primary']")
	public WebElement SAVECERT;

	@FindBy(xpath = "//a[@class='btn pull-right ng-scope'][text()='Return to Customer Listing']")
	public WebElement CUSTOMERLISTING;
	
	@FindBy(xpath = "//div[@class='alert alert-success']")
	public WebElement SUCCESS;
	
	@FindBy(xpath = "//div[@class='alert alert-error alert-dismissible ng-binding ng-scope']")
	public WebElement ERROR;

	@FindBy(xpath = "//div/div/h2[contains(.,'ADD A SINGLE EXEMPTION')]/../../div/a[text()='EXPAND']")
	public WebElement EXPANDADDEXCEP;
	
	@FindBy(xpath = "//a[contains(.,'ADD A SINGLE CERTIFICATE')]")
	public WebElement ADDCERLINK;
	
	@FindBy(xpath = "//a[contains(.,'ADD A NEW CERTIFICATE')]")
	public WebElement ADDNEWCERBTN;
	
	@FindBy(xpath = "//div/div/h2[contains(.,'ADD A SINGLE CERTIFICATE')]/../../div/a[text()='EXPAND']")
	public WebElement EXPANDADDCERT;

	@FindBy(xpath = "//input[@id='remove-customer-end-date' and @disabled='disabled']")
	public WebElement ENDDATEDISABLE;
	
	@FindBy(xpath = "//a[@id='confirm-remove-customer']")
	public WebElement REMOVEBTN;
	
	@FindBy(xpath = "//p[contains(.,'You are about to remove an item.')]")
	public WebElement REMOVEANITEMPUOPUP;

	@FindBy(xpath = "//certificate-upload[@class='ng-isolate-scope']/div/div/div[2]/a[not(@class='ng-hide') and contains(.,'EXPAND')]")
	public WebElement EXPAND;
	
	@FindBy(xpath = "//certificate-upload[@class='ng-isolate-scope']/div/div/div[2]/a[contains(.,'COLLAPSE')]")
	public WebElement COLLAPSE;

	// Adding invalid Exception File Error message XPATH as per POR-4951 in 4.4.3

	@FindBy(xpath = "//div[@class='alert alert-error ng-binding'][contains(.,'not supported')]")
	public WebElement ERRORMSG;

	// Added as per POR-5414
	@FindBy(xpath = "//pagination-extension/span/select")
	public WebElement PAGINATION;
	
	@FindBy(xpath = "//div[@class='pagination pagination-right ng-scope']/ul/li/a[contains(.,'ï¿½')]")
	public WebElement PAGINATIONLASTPAGE;
	
	@FindBy(xpath = "//div[@class='pagination pagination-right ng-scope']/ul/li/a[contains(.,'13')]")
	public WebElement PAGINATIONNUMBER;

	// Added as per POR 5956

	@FindBy(xpath = "//div[@class='registration-row']/a[contains(.,'Customers')]")
	public WebElement CONFIGURECUSTOMERSLINK;
	
	@FindBy(xpath = "//a[contains(text(),'Expired')]")
	public WebElement EXPIREDLINK;
	
	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td[1]")
	public WebElement EXPIREDCUSTOMERNAME;
	
	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td[3]")
	public WebElement EXPIREDCUSTOMERCODE;

	// 5.0 Changes
	@FindBy(xpath = "//a[contains(.,'MANAGE UPLOADED FILES')]")
	public WebElement MANAGEUPLOADFILES;
	
	@FindBy(xpath = "//table/thead/tr/th/span[contains(.,'Customer Certificate File Name')]")
	public WebElement CUSTOMEREXCEPTIONFILENAME;
	
	@FindBy(xpath = "//span[contains(.,'This field is required.')]")
	public WebElement THISFIELDISREQ;

	@FindBy(xpath = "//td[contains(.,'_exp')]")
	public WebElement AFTERREMOVE;

	// 5.1 Changes

	@FindBy(xpath = "//button[contains(.,'Save & Add Another')]")
	public WebElement SAVEADDANOTHER;
	
	@FindBy(xpath = "//div[@class='alert alert-success'][contains(.,' has been saved successfully!')]")
	public WebElement SUCCESSCERTIFICAT;
	
	@FindBy(xpath = "//a[@class='btn pull-right'][contains(.,'View All Certificates')]")
	public WebElement RETURNTOCERTIFICATELIST;
	
	// Uploading after Exceptions
	@FindBy(xpath = "//div/a[contains(.,'Return to Configuration Overview')]")
	public WebElement RETRURNTOCONFIGUREOVERVIEW;
	
	@FindBy(xpath = "//div/a[contains(.,'Return to Customer Listing')]")
	public WebElement RETRURNTOCUSTOMERS;
	
	@FindBy(xpath = "//tbody/tr/td/a[contains(.,'Report')]")
	public WebElement REPORTS;
	
	@FindBy(xpath = "//tbody/tr/td/a/i[@class='icon icon-blue icon-add']")
	public WebElement BLUEADD;
	
	@FindBy(xpath = "//tbody/tr/td/a/i[@class='icon icon-blue icon-minus']")
	public WebElement BLUEMINUS;
	
	@FindBy(xpath = "//button[contains(.,'Begin Removal')]")
	public WebElement BEGINREMOVAL;
	
	@FindBy(xpath = "//select[@class='ng-pristine ng-untouched ng-valid']")
	public WebElement SELECT;
	
	@FindBy(xpath = "//a[contains(.,'View (2)')]") // need to edit depends upon certificates adding
	public WebElement CERTVIEW;
	
	@FindBy(xpath = "//a[text()='Edit']")
	public WebElement CERTEDIT;
	
	@FindBy(xpath = "//tr[2]/td/div/ul/li[2]/a[text()='Remove']")
	public WebElement CERT2REMOV;
	
	@FindBy(xpath = "//a[text()='Remove']")
	public WebElement CERTREMOV;
	
	@FindBy(xpath = "//tr[2]/td/div/a[@class='dropdown-toggle']")
	public WebElement ACTIONITEM2;
	
	@FindBy(xpath = "//tr[1]/td/div/a[@class='dropdown-toggle']")
	public WebElement ACTIONITEM1;
	
	@FindBy(xpath = "//input[@id='remove-customer-end-date']")
	public WebElement CERT2ENDDATE;
	
	@FindBy(xpath = "//tr[2]/td/span/a[contains(.,'Add')]")
	public WebElement ADDCERT;
	
	@FindBy(xpath = "//tr[1]/td/div/ul/li[2]/a[text()='Remove']")
	public WebElement REMVCERT1;

	@FindBy(xpath = "//a[contains(.,'Customers')]")
	public WebElement CUSTOMERS;

	@FindBy(xpath = "//input[@id='companyenddate']")
	public WebElement CUSTENDDATE;

	@FindBys({
			@FindBy(xpath = "//div/table/tbody/tr"), })
	public List<WebElement> ADDEDCUSTOMERSGRID;

	@FindBy(xpath = "//a[contains(.,'View Image')]")
	public WebElement VIEWIMAGE;

	public final String SAVEANDCLOSE_XPATH = "//button[contains(text(),'Save & Close')]";

	@FindBy(xpath = "//a[contains(.,'Add Customer Exemption')]")
	public WebElement ADDCUSTOMEREXCEPTION;

	@FindBy(xpath = "//a[@class='btn pull-right'][contains(.,'View All Exemptions')]")
	public WebElement VIEWALLEXCEPTIONS;

	@FindBy(xpath = "//a[contains(.,'Add Single Exemption')]")
	public WebElement ADDSINGLEEXCEPTION;

	@FindBy(xpath = "//a[contains(.,'Upload Multiple')]")
	public WebElement UPLOADEXCEPTION;

	@FindBy(css = "button.btn.btn-primary.ng-scope")
	public WebElement UPLOADBUTTON;

	@FindBy(linkText = "+ Upload New File")
	public WebElement UPLOADNEWFILE;

	@FindBy(xpath = "//a[@href='/VisitHelpCenter/UploadMultipleCustomerExceptions']")
	public WebElement HelpCenterLink;

	@FindBy(xpath = "//h1[contains(text(),'Configure Overview')]")
	public WebElement CONFIGUREOVERVIEW;

	@FindBy(xpath = "//label[contains(.,'Exemption State')]/../select")
	public WebElement EXCEPTIONSTATELABEL;

	@FindBy(xpath = "//certificate-upload/div/div/div/div/div/label[contains(.,'Company')]")
	public WebElement COMPANYCUSTOMERUPLOAD;
	
	@FindBys({ @FindBy(xpath = "//table/tbody/tr[@class='ng-scope']") })
	public List<WebElement> ManageExceptionFilestable;
	
	@FindBy(xpath = "//h1[contains(.,'Manage Exemption Files')]")
	public WebElement MANAGEEXCEPTIONTITLE;
	
	@FindBy(xpath = "//a[contains(text(),'Add Exemption')]")
	public WebElement ADDEXPBTN;
	
	@FindBy(xpath = "//div/span[contains(.,'Date Added')]")
	public WebElement DATEADDED;
	
	@FindBy(xpath = "//th[text()='Status']")
	public WebElement STATUS;
	
	@FindBy(xpath = "//div/span[contains(.,'Exemption File Name')]")
	public WebElement UPLOADFILENAME;

	@FindBy(xpath = "//th[contains(.,'Original File')]")
	public WebElement OrgFile;
	
	@FindBy(xpath = "//th[contains(.,'Success Report')]")
	public WebElement SucReport;

	@FindBy(xpath = "//th[contains(.,'Error Report')]")
	public WebElement ErrReport;

	@FindBy(xpath = "//a[contains(.,'View All Certificates')]")
	public WebElement VIEWALLCERTIFICATE;

	@FindBy(xpath = "//fileupload/div/div/label[contains(.,'Browse')]")
	public WebElement CERTIFICATEIMAGEBROWSE;

	@FindBy(xpath = "//div[@class='drawer__inner']/descendant::button[@class='btn btn-primary ng-scope']")
	public WebElement EXCEPTIONUPLOAD;

	@FindBy(xpath = "//h3[contains(.,'Certificate Image')]/../div/div/div/fileupload/div/div/label[contains(.,'Browse')]")
	public WebElement AFTERCERTIFICATEIMAGEBROWSE;

	@FindBy(xpath = "//label[contains(.,'Browse')]")
	public WebElement BROWSEBTN;

	@FindBy(xpath = "//label[contains(.,'Browse')]")
	public WebElement BROWSEBTNTaxable;

	@FindBy(xpath = "//table/tbody/tr/td[text()='Exception_QA']/../td/div/a[text()='Action Required']")
	public WebElement ACTIONREQUIRED;

	@FindBy(xpath = "//label[text()='Add Images']")
	public WebElement ADDIMAGES;

	@FindBy(xpath = "//a[text()='Upload']")
	public WebElement UPLOAD;

	@FindBy(xpath = "//label[text()='Done']")
	public WebElement DONE;

	@FindBy(xpath = "//label[contains(.,'Browse')]")
	public WebElement BROWSE;

	@FindBy(xpath = "//a[text()='Complete Upload']")
	public WebElement COMPLETEUPLOAD;

	@FindBy(xpath = "//a[text()='Success Rows']")
	public WebElement SUCCESSROWSLINK;

	@FindBy(xpath = "//li/a[contains(.,'Active Certificates')]")
	public WebElement Active_Certificates;

	@FindBy(xpath = "//a[contains(.,'Upload Multiple')]")
	public WebElement UploadMultiple;

	@FindBy(xpath = "//li/a[contains(.,'Active Certificates')]")
	public WebElement AddCertificate;

	@FindBy(xpath = "//button[contains(.,'Add Exemption')]")
	public WebElement ADDEXEMPTION;

	@FindBy(xpath = "//div/span[contains(.,'Error Rows*')]")
	public WebElement ERRORROWSLINK;

	@FindBy(xpath = "//div/a[contains(.,'Images Expected')]")
	public WebElement IMAGESEXPECTED;

	@FindBy(xpath = "//div/a[contains(.,'Images Missing')]")
	public WebElement IMAGESMISSING;

	@FindBy(xpath = "//div/a[contains(.,'Images Uploaded')]")
	public WebElement IMAGESUPLOADED;

	@FindBy(xpath = "//button[contains(.,'Add Exemption')]")
	public WebElement ADDEXCEPT;

	@FindBy(xpath = "//a[text()='Delete File']")
	public WebElement DELETEFILE;

	@FindBy(xpath = "//a/strong[text()='Cancel']")
	public WebElement CANCEL;

	@FindBy(xpath = "//div/a[contains(.,'Manage Files')]")
	public WebElement MANAGEFILES;

	@FindBy(xpath = "//a[text()='Return to File Listing']")
	public WebElement RETURNTOFILELISTING;

	@FindBy(xpath = "//a[text()='Remove item']")
	public WebElement REMOVEITEM;

	@FindBy(xpath = "//input[@id='tbQuickSearchByName']")
	public WebElement SEARCH;

	@FindBy(xpath = "//div[@class='dropdown']/a[@class='dropdown-toggle']")
	public WebElement ACTIONSEDIT;

	@FindBy(xpath = "//a[text()='Edit']")
	public WebElement EDIT;

	@FindBy(xpath = "//fileupload/div[@class='alert alert-error ng-binding'][contains(.,'One or more columns ExtraColumn you have uploaded are not recognized by Vertex Cloud.')]")
	public WebElement EXTRACOLUMNERROR;

	@FindBy(xpath = "//fileupload/div[@class='alert alert-error ng-binding'][contains(.,'Duplicate column names encountered, Please correct and re-upload.')]")
	public WebElement DUPLICATECOLUMNERROR;

	@FindBy(xpath = "//fileupload/div[@class='alert alert-error ng-binding'][contains(.,'One or more columns TaxResultType are missing and must be supplied.')]")
	public WebElement LESSCOLUMNERROR;

	public final String JURSDTYPE = "//select[@id='ddlJurisdictionType']";

	public final String ISSUEJURSDTYPE = "//select[@id='ddlIssuingJurisdiction']";

	@FindBy(xpath = "//label[contains(.,'Certificate Scope')]")
	public WebElement CertificateScopelabelText;

	@FindBy(xpath = "//select[@id='ddlCertificateScope']")
	public WebElement CertificateScopeValue;

	@FindBy(xpath = "//label[contains(.,'Code/Class')]")
	public WebElement Code_classText;

	@FindBy(xpath = "//input[@class='chosen-search-input default']")
	public WebElement ProductCodeVal;

	@FindBy(xpath = "//label[contains(.,'Document Number')]")
	public WebElement DocumentNumberText;
	
	@FindBy(xpath = "//input[@id='invoice-number']")
	public WebElement InvoiceNumber;

	@FindBy(xpath = "//div[contains(text(),'Entered Issue Date will be used to calculate expiration date.')]")
	public WebElement No_Issue_Date;

	@FindBy(xpath = "//label/input[@value='code']")
	public WebElement Codeclassradiobtn;
	
	@FindBy(xpath = "//label/input[@value='name']")
	public WebElement jurisdictionRadiobtn;

	@FindBy(xpath = "//label/input[@value='number']")
	public WebElement Exceptionnumberradiobtn;

	@FindBy(xpath = "//label/input[@value='name']")
	public WebElement JurisdictionNameRadiobtn;

	@FindBy(xpath = "//th/div/span[contains(.,'Date Added')]")
	public WebElement DateAddedHeader;

	@FindBy(xpath = "//th/div/span[contains(.,'Exemption Number')]")
	public WebElement ExceptionNumberheader;

	@FindBy(xpath = "//th/div/span[contains(.,'Exemption State')]")
	public WebElement ExceptionStateHeader;

	@FindBy(xpath = "//th/div/span[contains(.,'Jurisdiction Type')]")
	public WebElement JurisdictionTypeHeader;

	@FindBy(xpath = "//th/div/span[contains(.,'Issuing Jurisdiction')]")
	public WebElement IssuingJurisdictionHeader;

	@FindBy(xpath = "//th/div/span[contains(.,'Certificate Scope')]")
	public WebElement CertificateScopeHeader;

	@FindBy(xpath = "//th/div/span[contains(.,'Exemption Reason')]")
	public WebElement ExceptionReasonHeader;

	@FindBy(xpath = "//th/div/span[contains(.,'Start Date')]")
	public WebElement StartDateHeader;

	@FindBy(xpath = "//th/div/span[contains(.,'End Date')]")
	public WebElement EndDateHeader;

	@FindBy(xpath = "//tr[@class='tablesorter-headerRow']/th[contains(.,'Action')]")
	public WebElement ActionHeader;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td[2]")
	public WebElement ExpNovalue;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td[3]")
	public WebElement ExpStatevalue;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td[4]")
	public WebElement JusTypeValue;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td[6]")
	public WebElement Certificatescopevalue;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope']/tbody/tr/td[7]")
	public WebElement ExpResvalue;

	@FindBy(xpath = "//tr[1]/td/div/a[@class='dropdown-toggle']")
	public WebElement Actionitem;

	@FindBy(xpath = "//tr[1]/td/div[@class='dropdown open']")
	public WebElement Actionitemlink;

	@FindBy(xpath = "//p[contains(.,'Edit Certificate')]")
	public WebElement EDITCERTIFICATETEXT;

	@FindBy(xpath = "//p[contains(.,'Edit Customer')]")
	public WebElement EDITCUSTOMERTEXT;

	@FindBy(xpath = "//p[contains(.,'Edit Jurisdiction')]")
	public WebElement EDITJURISDICTIONTEXT;

	@FindBy(xpath = "//tr[1]/td/div[@class='dropdown open']/ul/li/a[contains(.,'Edit')]")
	public WebElement Editcertificatelink;

	@FindBy(xpath = "//tr[1]/td/div[@class='dropdown open']/ul/li/a[contains(.,'Remove')]")
	public WebElement Removecertificatelink;

	@FindBy(xpath = "//tr/td/div[@class='dropdown open']/ul/li/a[contains(.,'Upload Image')]")
	public WebElement UploadImagelink;

	@FindBy(xpath = "//tr[1]/td/div[@class='dropdown open']/ul/li/a[contains(.,'View Image')]")
	public WebElement ViewImagelink;

	@FindBy(xpath = "//tr[1]/td/div[@class='dropdown open']/ul/li/a[contains(.,'Delete Image')]")
	public WebElement DeleteImagelink;

	@FindBy(xpath = "//p[contains(.,'Edit Certificate')]")
	public WebElement EditcertificateHeader;

	@FindBy(xpath = "//td/span/a[contains(.,'Add')]")
	public WebElement CustomerAdd;

	@FindBy(xpath = "//pagination-extension/span/select")
	public WebElement pagination;

	@FindBy(xpath = "//select[@id='ddlExceptionJurisdiction']")
	public WebElement ExceptionType;

	@FindBy(xpath = "//select[@id='ddlExceptionReason']")
	public WebElement ExceptionReason;

	@FindBy(xpath = "	//div[contains(text(),'Expiration date could not be calculated.')]")
	public WebElement NoExpirationdate;

	@FindBy(xpath = "	//div[contains(text(),'The expiration date will be calculated using the issue date, if provided')]")
	public WebElement NoissueDate;

	@FindBy(xpath = "//a[contains(.,'Customers')]")
	public WebElement CustomersLink;

	@FindBy(xpath = "//button[contains(.,'Add Certificate')]")
	public WebElement ADDCERTIFICATE;

	@FindBy(xpath = "//span[contains(.,'Customer Information')]")
	public WebElement CUSTOMERINFOLINK;

	@FindBy(xpath = "//span[contains(.,'Certificate Information')]")
	public WebElement CertificateInfoLink;

	@FindBy(xpath = "//a[contains(.,'Add Customer')]")
	public WebElement ADDCUSTOMERLINK;

	@FindBy(id = "quick-search-customers")
	public WebElement SEARCHCUSTOMER;

	@FindBy(xpath = "//span[contains(.,'Jurisdiction Information')]")
	public WebElement JURISDICTIONLINK;

	@FindBy(xpath = "//button[contains(.,'Add Jurisdiction')]")
	public WebElement ADDJURISDICTION;

	@FindBy(xpath = "//button[contains(.,'Back to Add Certificate')]")
	public WebElement BackToAddCertificate;

	@FindBy(xpath = "//button[contains(.,'Save Jurisdiction')]")
	public WebElement SAVEJURISDICTION;

	@FindBy(xpath = "//span[contains(.,'Certificate Image')]")
	public WebElement CertificateImageLink;

	@FindBy(xpath = "//button[contains(.,'Save Certificate')]")
	public WebElement SAVECERTIFICATE;

	@FindBy(xpath = "//div[@class='ng-isolate-scope']//input[@id='quick-search-customers']")
	public WebElement SEARCHCUSTOMERADDCERTFSCREEN;

	@FindBy(xpath = "//button[@class='close drawer__btn-close']")
	public WebElement CLOSEICON;

	@FindBy(xpath = "//button[contains(.,'CANCEL')]")
	public WebElement CANCELBUTTON;

	@FindBy(xpath = "//button[contains(.,'Add Exemption')]")
	public WebElement ADDEXEMPTIONMUTILUPLOAD;

	@FindBy(xpath = "//button[contains(.,'Save & Add Certificate')]")
	public WebElement SAVEANDADDCERTFICATE;

	@FindBy(xpath = "//button[contains(.,'Add Certificate')]")
	public WebElement ADDCERTIFICATEBUTTON;

	@FindBy(xpath = "//span[contains(.,'Customer Information')]/../../descendant::i[@class='icon-add icon-blue']")
	public WebElement VERIFYPLUSICONCUSTOMERINFO;

	@FindBy(xpath = "//li[@class='accordion-unification__panel']/descendant::span[contains(.,'Customer Information')]/../../descendant::i[@class='icon-minus icon-blue']")
	public WebElement VERIFYMINUSICONCUSTOMERINFO;

	@FindBy(xpath = "//li[@class='accordion-unification__panel']/descendant::span[contains(.,'Certificate Information')]/../../descendant::i[@class='icon-add icon-blue']")
	public WebElement VERIFYPLUSICONCERTIFICATEINFO;

	@FindBy(xpath = "//li[@class='accordion-unification__panel']/descendant::span[contains(.,'Jurisdiction Information')]/../../descendant::i[@class='icon-add icon-blue']")
	public WebElement VERIFYPLUSICONJURISINFO;

	@FindBy(xpath = "//li[@class='accordion-unification__panel']/descendant::span[contains(.,'Certificate Image')]/../../descendant::i[@class='icon-add icon-blue']")
	public WebElement VERIFYPLUSICONCERTIFICATEIMAGE;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered ng-isolate-scope'][2]/descendant::td/div/a")
	public WebElement ACTIONSJURIS;

	@FindBy(xpath = "//span[text()='Issuing Jurisdiction']/../../../../../tbody/tr[1]/td[6]/span")
	public WebElement ISSUEJURIS;

	@FindBy(xpath = "//strong[contains(.,'You have reached your limit for a Configuration item.  Please contact Customer Support to discuss.  Thank you.')]")
	public WebElement CONFIGLIMITPOPUP;

	@FindBy(xpath = "//strong[contains(.,'You have reached your limit for a Configuration item.  Please contact Customer Support to discuss.  Thank you.')]/../../p/a[contains(.,'Ok')]")
	public WebElement CONFIGLIMITOKBUTTON;

	@FindBy(id = "quick-search-taxability-category")
	public WebElement SPTaxabilityCategory;

	@FindBy(xpath = "//a[contains(.,'Active Certificates')]/../span[1]")
	public WebElement ACTIVECERTIFICATESCOUNT;

	@FindBy(xpath = "//a[contains(.,'Customers')]/../span[1]")
	public WebElement CUSTOMERSCOUNT;

	@FindBy(xpath = "//input[@name='serachby' and @value='code']")
	public WebElement SearchByCodeRadioBtn;

	@FindBy(xpath = "//input[@id='tbQuickSearchByName']")
	public WebElement SearchByName;

	@FindBy(xpath = "//table/tbody/tr/td/div/a[@class='dropdown-toggle']")
	public WebElement ACTIONMENU;

	// VAT Registration for customers
	@FindBy(xpath = "//div/button[contains(.,'Add VAT Registration')]")
	public WebElement VATREGISTRATION;

	@FindBy(xpath = "//td[contains(.,'Click Add VAT Registration above to add customer VAT registration information')]")
	public WebElement VATREGISTRATIONTEXT;

	@FindBy(xpath = "//tr/th[contains(.,'Jurisdiction')]/../th[contains(.,'Registration ID')]/../th[contains(.,'Physical Presence')]/../th[contains(.,'Actions')]")
	public WebElement VATREGISTRATIONTABLE;

	@FindBy(xpath = "//button[contains(.,'Back to Add Customer')]")
	public WebElement BACKTOADDCUSTOMER;

	@FindBy(id = "ddlVATCountry")
	public WebElement VATCOUNTRIES;

	@FindBy(id = "registrationCodeId")
	public WebElement VATREGISTRATIONCODE;

	@FindBy(id = "physical-presence-indicator")
	public WebElement PHYSICALPRESENCEINDICATOR;

	@FindBy(xpath = "//button[contains(.,'Save VAT Registration')]")
	public WebElement SAVEVAT;

	@FindBy(xpath = "//button[contains(.,'Cancel')]")
	public WebElement CANCELVAT;

	@FindBy(xpath = "//p[contains(.,'Edit Jurisdiction')]")
	public WebElement EDITJURISDICTION;

	@FindBy(xpath = "//label/input[@value='code']")
	public WebElement SEARCHBYCODE;

	@FindBy(id = "confirm-remove-customer")
	public WebElement OKREMOVECUSTOMER;

	@FindBy(xpath = "//strong[contains(.,'The end date of the customer must be greater than all related certificates')]")
	public WebElement ERRORMESSAGE;

	@FindBy(xpath = "//input[@name='serachby' and @value='name']")
	public WebElement SearchBynameRadioBtn;
	
	@FindBy(xpath = "//h3[contains(.,'CERTIFICATES UPDATED')]")
	public WebElement CERTIFICATESUPDATEDtext;
	
	//@FindBy(xpath = "//strong[contains(., \"We've automatically updated the start dates of this customer's certificates for you to avoid errors when pushing your configuration.\")]")
	@FindBy(xpath="//div[2]/p/strong")
	public WebElement warningtext;
	
	//@FindBy(xpath = "//p[contains(., \"Keep in mind, we have only updated the start dates for certificates that had started before this customer's new start date. Other certificates were not affected.\"]")
	@FindBy(xpath="//div[4]/div[2]/p")
	public WebElement startdatetex;

	@FindBy(xpath = "//a[contains(.,'GOT IT')]")
	public WebElement gotittext;
	
}
