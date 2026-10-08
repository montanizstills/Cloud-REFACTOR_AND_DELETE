package common.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class ProductsPage {
	@FindBy(xpath = "//a[text()='Map a Product']")
	public WebElement MAP_A_PRODUCTLINK;

	@FindBy(xpath = "//h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")
	public WebElement ADD_A_PRODUCTLINK;

	@FindBy(xpath = "//button[contains(text(),'Upload') and @disabled='disabled']")
	public WebElement UPLOADBTNDISABLE;

	@FindBy(xpath = "//a[contains(.,'ADD A NEW PRODUCT')]")
	public WebElement ADD_A_NEW_PRODUCTLINK;

	@FindBy(xpath = "//table[@id='tblMappings']/tbody/tr[1]/td/a[2][text()='Edit']")
	public WebElement EDITPROD;

	@FindBy(xpath = "//table[@id='tblMappings']/tbody/tr[1]/td/a[text()='Remove']")
	public WebElement RMVEFIRSTPROD;

	@FindBy(xpath = "//div[@class='modal-body ng-scope']/p/strong[text()='Are you sure you want to remove this item?']")
	public WebElement WRNGTXT;

	@FindBy(xpath = "//a[contains(.,'Add Single Mapping')]")
	public WebElement ADD_A_NEW_SINGLPRODUCT;

	@FindBy(xpath = "//div/select[@id='product-type']")
	public WebElement PRODUCTTYPE;

	@FindBy(xpath = "//div/label/a[text()='Manage Product Codes']")
	public WebElement UPLOADPRODUCTCODE;

	@FindBy(xpath = "//a[@id='ih-ManageProductCodesPopupLink00P']/i")
	public WebElement PRODUCTCODEICON;

	@FindBy(xpath = "//inline-help[@id='ih-ManageProductCodes']/div/div[2]")
	public WebElement PRODUCTCODEICONTEXT;

	@FindBy(xpath = "//label[contains(.,'1.')]/a[contains(.,'Download File Format Template')]")
	public WebElement DWNLD_FILE_FRMT_TEMP;

	@FindBy(xpath = "//div/label[contains(.,'Browse')]")
	public WebElement BROWSE;

	@FindBy(xpath = "//button[contains(.,'Upload File')]")
	public WebElement UPLOADFILE;

	@FindBy(xpath = "//div[@class='alert alert-error ng-binding']")
	public WebElement UPLOADERROR;

	@FindBy(xpath = "//div[@class='modal-footer']/center/button[text()='OK']")
	public WebElement UPLOADSUCCESSOK;

	@FindBy(xpath = "//div[@class='row-fluid alert alert-error']/ul/li[contains(text(),'An exception was thrown. Warning: There are no products to save.')]")
	public WebElement EMPTYUPLOADERROR;

	@FindBy(xpath = "//a[@id='addNewProd']")
	public WebElement ADDNEW;

	@FindBy(xpath = "//input[@id='product-end-date']")
	public WebElement PRODUCTENDDATE;

	@FindBy(xpath = "//table[@class=' table-condensed']/thead/tr/th[@class='next']")
	public WebElement NEXTMONTH;

	@FindBy(xpath = "//table/tbody/tr[3]/td[4]")
	public WebElement FUTUREDATE;

	@FindBy(xpath = "//table/tbody/tr/td[@class='day active']")
	public WebElement ENDDATEASCURRENT;

	@FindBy(xpath = "//a[@ng-show='product.productCounts.countUnmapped > 0 && !data.isCommitting']")
	public WebElement PRODUCTREADYTOBESAVE;

	@FindBy(xpath = "//div[@class='col-container']/div/a[1][contains(.,'Products Ready To Be Mapped')]")
	public WebElement PRODUCTREADYTOBEMPD;

	@FindBy(xpath = "//select[@name='dlCompany']")
	public WebElement CMPNYNAME;

	@FindBy(id = "prodcode")
	public WebElement PRODCODE;

	@FindBy(xpath = "//select[@id='product-type']")
	public WebElement PRODCUTCODETYPE;

	@FindBy(id = "tbProductName")
	public WebElement PRODNAME;

	@FindBy(xpath = "//a[@id='prodReadyToSave']")
	public WebElement SAVE;

	@FindBy(id = "productcode")
	public WebElement PRODUCTCODE;

	@FindBy(xpath = "//td[@class='text-center']/input[@class='ng-pristine ng-untouched ng-valid']")
	public WebElement SELECTCHKBOX;

	@FindBy(xpath = "//tr[@class='exception-detailLable']/td[2]/label")
	public WebElement PDTCODE;

	@FindBy(xpath = "//tr[@class='exception-detailLable']/td[3]/label")
	public WebElement PDTNAME;

	@FindBy(xpath = "//div/a[text()='Remove Selected']")
	public WebElement REMOVESELECTED;

	@FindBy(xpath = "//button[contains(text(),'Add Product') and @disabled='disabled']")
	public WebElement ADDPRODUCTDISABLEBTN;

	@FindBy(id = "start-date")
	public WebElement STARTDATE;

	@FindBy(xpath = "//a[@id='Inline-help1PopupLink00Q']/i")
	public WebElement STARTDATEICON;

	@FindBy(xpath = " //inline-help[@id='Inline-help1']/div/h3")
	public WebElement STARTDATEICONTEXT;

	@FindBy(id = "product-end-date")
	public WebElement ENDDATE;

	@FindBy(id = "remove-customer-end-date")
	public WebElement PRODENDDATE;

	@FindBy(xpath = "//div/form/h3[contains(.,'Product Mapping')]")
	public WebElement PRODUCTMAPPING;

	@FindBy(xpath = "//div/span/input[@class='tt-hint']")
	public WebElement PRODUCTQUICKSEARCH;

	@FindBy(xpath = "//select[@id='tax-category']")
	public WebElement BUYSELLPRODT;

	@FindBy(xpath = "//div/a[contains(text(),'View Errors')]")
	public WebElement VIEWERRORS;

	@FindBy(xpath = "//p/a[text()='Return to upload product codes']")
	public WebElement RETURNTOUPLOADPRODUCTCODES;

	@FindBy(xpath = "//div[2]/div[1]/div[@class='span2 pull-right text-right']/a[text()='COLLAPSE']")
	public WebElement SINGLEPRODCOLLAPSE;

	@FindBy(xpath = "//div[2]/div[1]/div[@class='span2 pull-right text-right']/a[@class='ng-hide'][text()='COLLAPSE']")
	public WebElement SINGLEPRODCTCOLLAPSE;

	@FindBy(xpath = "//div[2]/div[1]/div[@class='span2 pull-right text-right']/a[text()='EXPAND']")
	public WebElement SINGLEPRODEXPAND;

	@FindBy(xpath = "//div[2]/div[1]/div[@class='span2 pull-right text-right']/a[@class='ng-hide'][text()='EXPAND']")
	public WebElement SINGLEPRODEXPANDHIDE;

	@FindBy(xpath = "//div[1]/div[1]/div[@class='span2 pull-right text-right']/a[text()='COLLAPSE']")
	public WebElement MULTPRODCOLLAPSE;

	@FindBy(xpath = "//div[1]/div[1]/div[@class='span2 pull-right text-right']/a[text()='EXPAND']")
	public WebElement MULTPRODEXPAND;

	@FindBy(xpath = "//a[contains(text(),'ADD A NEW PRODUCT')]")
	public WebElement ADDNEWPRODUCT;

	@FindBy(xpath = "//div/a[contains(text(),'Return To Product Listing')]")
	public WebElement RETURNTOPRODUCTLISTING;

	@FindBy(xpath = "(//a[contains(text(),'Return to Configuration Overview')])[1]")
	public WebElement RETURNTOCONFIGURATIONOVERVIEW;

	@FindBy(xpath = "//div[@class='control-group']/label/inline-help/a/i")
	public WebElement UPLOADURTEMPI;

	@FindBy(xpath = "//div[@class='popover fade right in']/div[text()='Templates may be uploaded using a CSV format.']")
	public WebElement POPUPTEXT;

	@FindBy(xpath = "//inline-help[@id='product-type-help']/a/i")
	public WebElement PRODTYPEI;

	@FindBy(xpath = "//div[@class='popover fade right in']/div[text()='A Usage Code identifies a general way in which the product, material, or service is to be used. A Product Code is a unique identifier associated with the transaction.']")
	public WebElement POPUPTYPEX;

	@FindBy(xpath = "//div[@class='popover fade right in']/div[text()='A Product Code is a unique identifier associated with the transaction. A Taxpayer is the current taxpayer. A Usage Code identifies a general way in which the product, material, or service is to be used.']")
	public WebElement POPUPTYPE;

	@FindBy(xpath = "//inline-help[@id='ih-ManageProductCodes']/a/i")
	public WebElement CODECLASSI;

	@FindBy(xpath = "//div[@class='popover fade right in']/div[text()='The Code/Class is a unique identifier associated with the transaction.  This must match your ERP exactly.']")
	public WebElement POPUPCODECLASS;

	@FindBy(xpath = "//div[@class='popover fade right in']/div[text()='The Start Date is the day on which the mapping you are setting up becomes effective in Vertex Cloud.']")
	public WebElement startdatetext;

	@FindBy(xpath = "//a[contains(.,'Add Conditional Rule')]")
	public WebElement ADDQUALICOND;

	@FindBy(xpath = "//div/label/strong[text()='Category Description']")
	public WebElement CategoryDesc;

	@FindBy(xpath = "//select[@id='condition-condition-0-jurisdiction-0']")
	public WebElement STATE;
	
	@FindBy(xpath = "//select[@id='condition-condition-1-jurisdiction-0']")
	public WebElement STATE1;

	@FindBy(xpath = "//select[@id='ddlJurisdictionTypeType0']")
	public WebElement JURISDICTIONTYPE;

	@FindBy(xpath = "//select[@id='qualcondLj0']")
	public WebElement LOWERJURISDICTION;

	@FindBy(xpath = "//select[@id='condition-condition-0-jurisdiction-0']/option[2]")
	public WebElement JURSELECTVALUES;

	@FindBy(xpath = "//select[@id='condition-condition-1-jurisdiction-0']")
	public WebElement EDITJURSELECT;

	@FindBy(xpath = "//select[@id='condition-condition-1-jurisdiction-0']/../div[@class='help-block help-block-error ng-scope ng-active']/span[text()='Error:  You have already selected this jurisdiction']")
	public WebElement ERROR;

	@FindBy(xpath = "//div[@class='control-group']/label/input")
	public WebElement QUALCOND;

	@FindBy(xpath = "//div[@class='accordion-heading']/a[contains(.,'Imposition Type')]")
	public WebElement IMPOSITIONTYPE;

	@FindBy(xpath = "//div/a[contains(.,'Conditional Rule')]/../../div[contains(.,'No Conditional Rules found.')]")
	public WebElement NOCONDITIONALRULE;

	@FindBy(xpath = "//div[@id='qualifying-condition-1']/div/div[@class='accordion-heading']/a[contains(.,'Imposition Type')]")
	public WebElement EDITIMPOSITIONTYPE;

	@FindBy(xpath = "//div[@id='qualifying-condition-1']/div//div/div/div[@class='control-group']/label/input")
	public WebElement EDITQUALCOND;

	@FindBy(xpath = "//div[1]/ng-form/div/p/a")
	public WebElement FIRSTREMOVE;

	@FindBy(xpath = "//div[3]/ng-form/div/p/a")
	public WebElement THIRDREMOVE;

	@FindBy(xpath = "//div[@class='span7']/p/a[text()='Return to Add New Product']")
	public WebElement RETURN_TO_ADD_NEW_PRODUCT;

	@FindBy(xpath = "//a[contains(text(),'ADD A NEW PRODUCT')]")
	public WebElement ADD_NEW_PRODUCT;

	@FindBy(xpath = "//span[text()='Code / Class already exists!']")
	public WebElement ERRORTEXT;

	@FindBy(xpath = "//button[contains(text(),'Add Product')]")
	public WebElement ADDPRODUCT;

	@FindBy(xpath = "//button[@class='btn btn-primary' and contains(text(),'Add Product')]")
	public WebElement ADDPROD;

	@FindBy(xpath = "//button[contains(text(),'Save and Return to View Products')]")
	public WebElement SAVEANDRETURNVIEWPROD;

	@FindBy(xpath = "//a[contains(text(),'Ready to Save')]")
	public WebElement READYTOSAVEEDITPRODS;

	@FindBy(xpath = "//button[contains(text(),'Save and Continue to Map Products')]")
	public WebElement SAVEANDCONTINUEMAPPROD;

	@FindBy(xpath = "//div[@id='condition-0-jurisdiction']/div/div/div/div/span[text()='You have already selected this Jurisdiction / Imposition combination.']")
	public WebElement QUALIFYINGERROR;

	@FindBy(xpath = "//button[@class='btn btn-primary'][contains(text(),'Add Product')]")
	public WebElement QUICKSEARCHADDPROD;

	@FindBy(xpath = "//div[@class='banner-callout']/a[text()='Ready to Save']")
	public WebElement READYTOSAVE;

	@FindBy(xpath = "//a[text()='ADD A NEW PRODUCT']")
	public WebElement ADDPRODUCT1;

	@FindBy(xpath = "//input[@id='product-codes']")
	public WebElement DUPLICATEPRODBROWSE;

	@FindBy(xpath = "//button[@class='btn' and contains(text(),'Upload File')]")
	public WebElement DUPLICATEPRODUPLOADFILE;

	@FindBy(xpath = "//div[1]/a[contains(text(),'Return To Product Listing')]")
	public WebElement RETURNSOFLISTING;

	@FindBy(xpath = "//div[1]/h2/a[contains(text(),'ADD A SINGLE PRODUCT')]")
	public WebElement ADDASINGLEPROD;

	@FindBy(xpath = "//div/select[@id='product-type']")
	public WebElement PRODTYPESELECT;

	@FindBy(xpath = "//table/tbody/tr[3]/td[8]/a[2]")
	public WebElement REMOVEDUMMYPROD;

	@FindBy(xpath = "//a[contains(text(),'Remove item')]")
	public WebElement REMOVEITEM;

	@FindBy(xpath = "//div/ul/li[contains(text(),'was duplicated')]")
	public WebElement DUPLICATEPRODUCTERRMSG;

	@FindBy(xpath = "//div/a[contains(text(),'View Errors')]")
	public WebElement DUPLICATEPRODUCTERRMSGBUTTON;

	@FindBy(xpath = "//a[contains(text(),'Export to CSV')]")
	public WebElement EXPORTCSV;

	@FindBy(xpath = "//a[@class='export btn btn-primary' and contains(text(),'Export to CSV')]")
	public WebElement DUPLICATEPRODMSGBTNPOPUPEXPORT;

	@FindBy(xpath = "//a[contains(text(),'Return to upload product codes')]")
	public WebElement DUPLICATEPRODMSGBTNPOPUPRETURN;

	@FindBy(xpath = "//input[@id='productDefinitionsSelectAll']")
	public WebElement SELECTALLPRODCHECKBOX;

	@FindBy(xpath = "//table/tbody[3]/tr[1]/td[1]/input")
	public WebElement SELECTFIRSTPROD;

	@FindBy(xpath = "//table/tbody/tr[1]/td/a[2][text()='Edit']")
	public WebElement EDITFIRSTPROD;

	@FindBy(xpath = "//table/tbody[1]/tr[2]/td/a[2][text()='Edit']")
	public WebElement EDITSECPROD;

	@FindBy(xpath = "//table/tbody[3]/tr[1]/td[8]/span/a[contains(text(),'Remove')]")
	public WebElement REMOVEFIRSTPROD;

	@FindBy(xpath = "//table/tbody[3]/tr[2]/td[2]/input")
	public WebElement EDITPRODCODE;

	@FindBy(xpath = "//table/tbody[3]/tr[2]/td[8]/a[contains(text(),'Save')]")
	public WebElement EDITPRODSAVE;

	@FindBy(xpath = "//div[@class='section header-block ng-scope']/a[text()='Return to Edit Product']")
	public WebElement RETRNTOEDITPROD;

	@FindBy(xpath = "//li[@class='add-category']/a[contains(text(),'Add Custom Tax Category')]")
	public WebElement ADDCUSTOMTAXCATEGORY;

	@FindBy(xpath = "//input[@id='catergory-name']")
	public WebElement CUSTOMTAXCATNAME;

	@FindBy(xpath = "//div[@class='tax-category-panel choices-block']/ul/li[@class='ng-scope']/a/span[text()='Alabama']")
	public WebElement SELECTSTATE;

	@FindBy(xpath = "//div[@class='accordion-heading']/a/i[@class='icon-plus-large ng-scope']")
	public WebElement ADDSYMBOLONSELECTEDSTATE;

	@FindBy(xpath = "//div[@class='control-group exemption-category']/select")
	public WebElement SELECTCATEGORY;

	@FindBy(xpath = "//div[@class='control-group exemption-specification']/select")
	public WebElement EXEMPTIONTYPE;

	@FindBy(xpath = "//select[@id='filterSelect']")
	public WebElement FILTERSELECT;

	@FindBy(xpath = "//button[@id='btn-add-category']")
	public WebElement ADDCUSTTAXCATBUTTON;

	@FindBy(xpath = "//a[@class='btn'][contains(text(),'Return to Edit Product')]")
	public WebElement RETURNTOEDITPRO;

	@FindBy(xpath = "//a[@class='btn pull-right' and text()='Return to Add New Product']")
	public WebElement RETURNTOADDPRODPAGE;

	@FindBy(xpath = "Save")
	public WebElement EDITPRODSAVE_LINK;

	@FindBy(xpath = "//table[@id='tblProducts']/tbody")
	public WebElement PRODUBTTABLE;

	@FindBy(xpath = "//button[contains(text(),'Save and Return to View Products')]")
	public WebElement SAVEANDRETURN;

	@FindBy(xpath = "//div/div[3]/div[2]/div[2]/div/div/div/div[1]/div[2]/a")
	public WebElement TOTALPRODUCTS;

	@FindBy(xpath = "//table/tbody/tr/td[8]/a[1]")
	public WebElement PRODUCTEDITLINK;

	@FindBy(xpath = "//button[contains(text(),'Save Product')]")
	public WebElement SAVEPRODUCT;

	@FindBy(xpath = "//table/tbody[1]/tr[1]/td[8]/a")
	public WebElement EDITPRODNAMELINK;

	@FindBy(xpath = "//table/tbody[1]/tr[2]/td[3]/input")
	public WebElement EDITPRODNAMEINPUT;

	@FindBy(xpath = "//input[@id='product-name1']")
	public WebElement PRODNAMEINPUT;

	@FindBy(xpath = "//a[@class='btn pull-right' and contains(text(),'Return to Configuration Overview')]")
	public WebElement RETURNTOCONFIGOVERVIEW;

	@FindBy(xpath = "//table[@class='table table-bordered']/tbody/tr/td[3]")
	public WebElement UPDATEDPRODNAME;

	@FindBy(xpath = "//tr[1]/td/a[contains(.,'Map Product')]")
	public WebElement MAPPRODUCT;

	@FindBy(xpath = "//tr[1]/td/a[text()='Edit']")
	public WebElement EDITPRODUCT;

	@FindBy(xpath = "//input[@id='company-name']")
	public WebElement SELECTCOMP;

	@FindBy(xpath = "//input[@id='tbQuickSearch']")
	public WebElement QUICKSEARCH;

	@FindBy(xpath = "//select[@id='filterSelect']/option[1][text()='All Products']/../option[text()='Unmapped']/../option[text()='Mapping Ready to Save']/../option[text()='Mapped']")
	public WebElement FILTER;

	@FindBy(xpath = "//h2[contains(text(),'Upload Multiple Product Codes')]")
	public WebElement UPLOADMULTIPLEPRODUCTCODES;

	@FindBy(xpath = "//h2[contains(text(),'Add A Single Product')]")
	public WebElement ADDASINGLEPRODUCT;

	@FindBy(xpath = "//button[contains(text(),'Yes to exit Configure Mapping')]")
	public WebElement YESEXITPRODUCT;

	@FindBy(xpath = "//div[@class='row-fluid']/div[2][@class='span6']/div[1][@class='well']/div[@class='registration-block']/h3[contains(text(),'Configuration Not Complete')]")
	public WebElement CONFIGNOTCOMPLETE;

	@FindBy(xpath = "//li/a[contains(text(),'Add Custom Tax Category')]")
	public WebElement CUSTOMTAX_CATEGORY;

	@FindBy(xpath = "//a[@data-action='add-custom-category']")
	public WebElement AddNewCategory;

	@FindBy(xpath = "//input[@id='category-name']")
	public WebElement CATERGORY_NAME;

	@FindBy(xpath = "//button[contains(text(),'Save & Close')]")
	public WebElement ADD_CUSTOMTAX_CATEGORY;

	@FindBy(xpath = "//button[contains(text(),'Save Custom Tax Category')]")
	public WebElement SAVECUSTOMTAX_CATEGORY;

	@FindBy(xpath = "//button[@disabled='disabled']")
	public WebElement DISABLED_CUSTOMTAX_CATEGORY;

	@FindBy(xpath = "//i[@class='icon-plus-large']")
	public WebElement STATE_PLUS;

	@FindBy(xpath = "//i[@class='icon-minus-large']")
	public WebElement STATE_MINUS;

	@FindBy(xpath = "//select[@id='-category']")
	public WebElement CATEGORY;

	@FindBy(xpath = "//select[@id='-reason']")
	public WebElement EXEMPTION;

	@FindBy(xpath = "//a[contains(.,'Remove')]")
	public WebElement REMOVEEXEMPTION;

	@FindBy(xpath = "//a[@id='confirm-remove-customer']")
	public WebElement DUPLICATECATNAME;

	@FindBy(xpath = "//input[@id='quick-search']")
	public WebElement PRODUCTS_QUICK_SEARCH;

	@FindBy(xpath = "//div[@id='new-mapping']/div/ul/li/a/p[1]")
	public WebElement SEARCHLINK;

	@FindBy(xpath = "//div[@class='ng-scope']/label/span")
	public WebElement CATG_DESCRIPTION;

	@FindBy(xpath = "//div[@class='col']/a[text()='Total Products']")
	public WebElement TOTAL_PRODUCTS;

	@FindBy(xpath = "//div[@class='col']/a[text()='Code/Class Mapped']")
	public WebElement CODE_CLASSMAPPED;

	@FindBy(xpath = "//a[contains(text(),'Mapped')]")
	public WebElement MAPPEDPRODUCTS;

	@FindBy(xpath = "//tr[2]/td/a[contains(text(),'Remove')]")
	public WebElement REMOVESECONDPRODUCT;

	@FindBy(xpath = "//a[@id='confirm-remove-customer']")
	public WebElement REMOVESECONDPRODUCTPOPUP;

	@FindBy(xpath = "(//a[contains(text(),'Ready to Save')])[2]")
	public WebElement PRODUCTREADYTOSAVE;

	@FindBy(xpath = "//div/ul[@class='typeahead dropdown-menu ng-isolate-scope']")
	public WebElement SEARCHGRIDFORA;

	@FindBy(xpath = "//button[@class='btn btn-primary' and @disabled='disabled']")
	public WebElement DISABLEADDPROD;

	@FindBy(xpath = "//p[contains(text(),'Landline Fees')]")
	public WebElement SEARCHPRODUC;

	@FindBy(xpath = "//ul[@class='typeahead dropdown-menu ng-isolate-scope']/li")
	public WebElement QSPRODRESULT;

	@FindBy(xpath = "//p[contains(text(),'Billiards')]")
	public WebElement BILLIARDS;

	@FindBy(xpath = "//strong[contains(text(),'Cancel')]")
	public WebElement REMOVECANCEL;

	@FindBy(xpath = "//a[contains(.,'Expired')]")
	public WebElement EXPIRED;

	@FindBy(xpath = "//strong[contains(text(),'You will lose entered information if you exit this page without saving.')]")
	public WebElement POPUPALERTMSG;

	@FindBy(xpath = "//button[contains(text(),'Yes to exit Product')]")
	public WebElement YESTOEXITPRODUCT;

	@FindBy(xpath = "//div/label[contains(text(),'Consumer Use Tax')]")
	public WebElement PRODUCTCONSUMERTEXT;

	@FindBy(xpath = "//button[contains(text(),'Add Product')][@disabled='disabled']")
	public WebElement ADDPRODDISABLE;

	@FindBy(xpath = "//table/thead/tr/th/div/span[contains(text(),'Code / Class')]")
	public WebElement CODECLASSHEADER;

	@FindBy(xpath = "//div/div/h3[contains(.,'Mappings')]/span/a[contains(.,'+ Add')]")
	public WebElement ADDPRODUCTLNK;

	@FindBys({ @FindBy(xpath = "//table[@id='prodDefinitions']/tbody"), })
	public List<WebElement> Rows;

	@FindBy(id = "tbProductName")
	public WebElement PRODUCTNAME;

	@FindBy(id = "categoryitem5286")
	public WebElement CATEGORYTWOLIST;

	@FindBy(xpath = "//label[contains(.,'United States')]/input")
	public WebElement USAlabel;

	@FindBy(xpath = "//label[contains(.,'Canada')]/input")
	public WebElement Canadalabel;

	@FindBy(id = "quick-search")
	public WebElement quickSearch;

	@FindBy(xpath = "//ul/li/a/p[1]")
	public WebElement categoryname;

	@FindBy(xpath = "//ul/li/a/p[2]")
	public WebElement categoryselection;

	@FindBy(xpath = "//a[contains(text(),'Remove Category')]")
	public WebElement removeCategory;

	@FindBys({ @FindBy(xpath = "//taxability-category-tree/div/div[2]/div/ul/li/a/p[1]"), })
	public List<WebElement> treeStructuresize;

	@FindBy(xpath = "//pagination-extension/span/select")
	public WebElement paginationExtention;

	@FindBys({ @FindBy(xpath = "//h2[contains(text(),'Products')]/../table/tbody/tr") })
	public List<WebElement> productsbeginremovalsize;

	public final String ADDPRODDISABLE_XPATH = "//button[contains(text(),'Add Product')][@disabled='disabled']";

	public final String SAVECUSTOMTAX_CATEGORY_XPATH = "//button[contains(text(),'Save Custom Tax Category')]";

	// public final String PRODUCTS_QUICK_SEARCH_XPATH
	// ="input[@id='products-quick-search']";

	public final String SEARCHGRIDFORA_XPATH = "//div/ul[@class='typeahead dropdown-menu ng-isolate-scope']";

	public final String ADD_A_NEW_PRODUCTLINK_XPATH = "//a[contains(.,'ADD A NEW PRODUCT')]";

	public final String REMOVESECONDPRODUCTPOPUP_XPATH = "//a[@id='confirm-remove-customer']";

	public final String READYTOSAVEEDITPRODS_XPATH = "//a[contains(text(),'Ready to Save')]";

	public final String RETURNTOUPLOADPRODUCTCODES_XPATH = "//p/a[text()='Return to upload product codes']";

	public final String UPLOADSUCCESSOK_XPATH = "//div[@class='modal-footer']/center/button[text()='OK']";

	public final String DUPLICATEPRODMSGBTNPOPUPRETURN_XPATH = "//a[contains(text(),'Return to upload product codes')]";

	public final String REMOVEITEM_XPATH = "//p[2]/a[contains(text(),'Remove item')]";

	public final String POPUPTYPE_XPATH = "//div[@class='popover-content'][text()='A Usage Code identifies a general way in which the product, material, or service is to be used. A Product Code is a unique identifier associated with the transaction.']";

	@FindBy(xpath = "//button[contains(text(),'Save & Close')]")
	public WebElement SAVEANDCLOSE;

	@FindBy(xpath = "//button[contains(text(),'Save & Close') and @disabled='disabled']")
	public WebElement SAVEANDCLOSEDISABLEBTN;

	@FindBy(xpath = "//a[contains(text(),'Upload Multiple Mappings')]")
	public WebElement UploadMulProducts;

	@FindBy(xpath = "//form[@id='product-code-upload']/div/div/div/label[contains(.,'Company')]")
	public WebElement Companylabel;

	@FindBy(xpath = "//div[@class='section header-block ng-scope']/a[contains(.,'View All Mappings')]")
	public WebElement ViewAllProducts;

	@FindBy(xpath = "//div/p/a[contains(.,'View All Mappings')]")
	public WebElement Viewallmappinglowerheader;

	@FindBy(xpath = "//select[@id='tax-category']")
	public WebElement TAXTYPE;

	@FindBy(xpath = "//button[contains(.,'Save & Close')]")
	public WebElement SAVECLOSEBUTTON;

	@FindBy(xpath = "//button[contains(text(),'Save and Continue to Map')]")
	public WebElement SAVEANDCONTINUETOMAP;

	@FindBy(xpath = "//div[@class='row-fluid']/descendant::div/a")
	public WebElement ADDPRODUCTLIST;

	@FindBy(xpath = "//h3[contains(.,'Mapping')][2]")
	public WebElement mappings;

	@FindBy(xpath = "//h3[contains(.,'Mapping Information')]")
	public WebElement Mappingheader;

	@FindBy(xpath = "//h2[contains(.,'Create New Mapping')]")
	public WebElement Createnewmappingtext;

	@FindBy(xpath = "//h1[contains(.,'Configure Mapping')]")
	public WebElement Configureheader;

	@FindBy(xpath = "//div/label[contains(text(),'Tax Type')]")
	public WebElement TAXTYPETEXT;

	@FindBy(xpath = "//label[@for='product-type'][contains(.,'Type')]")
	public WebElement CODECLASSTYPE;

	@FindBy(xpath = "//label[contains(text(),'Code / Class')]")
	public WebElement code_class;

	@FindBy(xpath = "//label[contains(.,'Name')]")
	public WebElement Name;

	@FindBy(xpath = "//a[text()='Add Mapping']")
	public WebElement ADDPRODINPRODLIST;

	@FindBy(xpath = "//div/br/../a[contains(text(),'Add Mapping')]")
	public WebElement ADDMAPPING;

	@FindBy(xpath = "//div[@class='mapping-category-overlay']/ul/li/label[text()='General']")
	public WebElement GENERAL;

	@FindBy(xpath = "//a[text()='Add Mapping']")
	public WebElement UPLOADADDPRODUCT;

	@FindBy(xpath = "//tr[1]/td/span[text()='Original File']")
	public WebElement ORIGINALFILELNK;

//	@FindBy(xpath="//tbody/tr/td[text()='ProductsSample']/../td/a[text()='Error File']")
//	public WebElement ERRORFILELNK;

	@FindBy(xpath = "//tbody/tr/td[text()='Smoke_ProductsSample']/../td/a[text()='Success File']")
	public WebElement SUCCESSFILELNK;

	@FindBy(xpath = "//input[@id='company-name']")
	public WebElement COMPANYNAMERONLY;

	@FindBy(xpath = "//div/a[contains(.,'View All Mappings')]")
	public WebElement VIEWPRODUCTS;

	@FindBy(xpath = "//a[text()='Active']")
	public WebElement ACTIVELINK;

	@FindBy(xpath = "//div[@class='product-mapping-create']/ul/li[1]/a")
	public WebElement MAPPEDCATEGORY;

	@FindBy(xpath = "//span/a/i[@class='icon-button icon-product-copy']")
	public WebElement CopyProducts;

	@FindBy(xpath = "//span/a/i[@class='icon-button icon-product-download']")
	public WebElement DownloadProducts;

	@FindBy(xpath = "//h3[contains(.,'Copy Data')]")
	public WebElement CopyDatalabel;

	@FindBy(xpath = "//p[contains(.,'Select the taxpayer to which you would like to copy the selected configuration data.')]")
	public WebElement selectTaxpayerslabel;

	@FindBy(xpath = "//input[@id='source-company' and @disabled='disabled']")
	public WebElement SourceCompany;

	@FindBy(id = "destination-company")
	public WebElement DestinationCompany;

	@FindBy(linkText = "Cancel")
	public WebElement Cancellnk;

	@FindBy(xpath = "//button[contains(.,'Copy Data')]")
	public WebElement CopyDataBtn;

	@FindBy(xpath = "//div/strong[contains(.,'Request Processing!')]")
	public WebElement RequestProductCopyProcess;

	@FindBy(xpath = "//a[contains(.,'Manage Files')]")
	public WebElement ManageFiles;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Original File')]")
	public WebElement ManageOriginalFiles;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Error Report')]")
	public WebElement ManageErrorFiles;

	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Success Report')]")
	public WebElement ManageSuccessFiles;

	@FindBys({ @FindBy(xpath = "//div[3]/table/thead/tr/th") })
	public List<WebElement> ProductsCopiedsuccess;

	@FindBy(xpath = "//a[contains(text(),'Custom Categories')]")
	public WebElement EditCustomCategories;

	@FindBy(xpath = "//a[@id='add-new-category']")
	public WebElement AddCategory;

	@FindBy(xpath = "//a[contains(.,'Custom Categories')]/../span")
	public WebElement CcategoriesCountMTTP;

	@FindBy(xpath = "//input[@value='code']")
	public WebElement Codearadiobutton;

	@FindBy(xpath = "//input[@id='tbQuickSearchByName']")
	public WebElement tablequicksearch;

	public final String QUICSEARCH = "//input[@id='tbQuickSearchByName']";

	@FindBy(xpath = "//div/h2[contains(.,'Upload Multiple Mappings')]")
	public WebElement PRODUCTHADERTEXT;

	@FindBy(xpath = "//a[text()='Remove item']")
	public WebElement REMOVEPRODUCT;

	@FindBy(xpath = "//div[@class='well']//div[2]//p[1]/../p[contains(.,'Download the file format template below to enter mapping information. In order to')]/../p[contains(.,' assign high-level taxability categories in your upload file refer to the Vertex Tax')]/../p[contains(.,' Codes file included in the download. Please visit the Help Center for more information ')]/../p[contains(.,'about uploading multiple')]/../p[contains(.,'mappings')]")
	public WebElement MultipleProductsDescription;

	@FindBy(xpath = "//a[@id='downloadFileFormat']")
	public WebElement TemplateandVertexTaxCodesLink;

	@FindBy(xpath = "//div[@class='row-fluid']/div[@class='span6 control-btn text-right']/a")
	public WebElement ADDPRODMAPPING;

	@FindBy(xpath = "//div[contains(text(),'Taxpayer mapping categories cannot be edited.')]")
	public WebElement Taxpayermappingcantedit;

	@FindBy(xpath = "//label/span[@class='ng-binding']")
	public WebElement CategoryDescription;

	@FindBy(xpath = "//div/label[contains(.,'Country/Province/State')]")
	public WebElement STATELABEL;

	@FindBy(xpath = "//div/h3[contains(.,'Duplicate Company Mapping')]")
	public WebElement DuplicateCompanyMappingPopup;

	@FindBy(xpath = "//a[@id='confirm-remove-customer']")
	public WebElement Removecustomer;

	@FindBy(xpath = "//strong[contains(.,'Vertex Cloud has established Configuration thresholds to improve performance and scalability. Please contact cloudsupport@vertexinc.com to increase these thresholds.')]")
	public WebElement CONFIGLIMITPOPUP;

	@FindBy(xpath = "//strong[contains(.,'Vertex Cloud has established Configuration thresholds to improve performance and scalability. Please contact cloudsupport@vertexinc.com to increase these thresholds.')]/../../p/a[contains(.,'Ok')]")
	public WebElement CONFIGLIMITOKBUTTON;

	@FindBy(xpath = "//a[contains(.,'Mapped')]/../span")
	public WebElement CONFIGLIMITCOUNT;

	@FindBy(xpath = "(//a[@class='btn btn-primary pull-right'])[2]")
	public WebElement ADDMAPPINGBUTTON;

	@FindBy(xpath = "//p/strong[contains(.,'Invalid date format. Please use the mm/dd/yyyy format.')]")
	public WebElement InvalidDateFormat;

	@FindBy(xpath = "//p/a[contains(.,'Ok')]")
	public WebElement OK;

	@FindBy(xpath = "//div/label[contains(.,'Rule Effective Date')]")
	public WebElement RULEEFFECTIVEDATE;

	@FindBy(xpath = "//label/inline-help[@id='effective-date-help']")
	public WebElement EFFECTIVEDATETOOLTIP;

	@FindBy(xpath = "//label/input[@value='true']")
	public WebElement TODAYRADIO;

	@FindBy(xpath = "//label/input[@value='false']")
	public WebElement MAPPINGSTART;

	@FindBy(xpath = "//label[contains(.,'Mapping Start (01/01/2017)')]")
	public WebElement MAPPINGSTARTVALUE;

	@FindBy(xpath = "//div/label[contains(.,'Rule Description')]")
	public WebElement RULEDESCRIPTION;

	@FindBy(xpath = "//label/input[@ng-model='cr.taxRuleId']")
	public WebElement RULEDESCRIPTIONRADIO;

	@FindBy(xpath = "//li[contains(.,'For Use in a Trade or Business')]")
	public WebElement RULE1;

	@FindBy(xpath = "//ol/li[2]")
	public WebElement RULE2;

	@FindBy(xpath = "//h3[contains(.,'Duplicate Mapping')]")
	public WebElement DUPLICATE;

	@FindBy(xpath = "//a[contains(.,'Imposition Type')]")
	public WebElement ImpositionType;

	@FindBy(xpath = "//select[@name='qualcondImp']")
	public WebElement QualcondImp;

	@FindBy(xpath = "//strong[contains(.,'An error has occurred.')]")
	public WebElement erroroccurredpopup;

}