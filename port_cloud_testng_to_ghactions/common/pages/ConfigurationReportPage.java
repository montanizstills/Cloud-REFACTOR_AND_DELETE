/*###################################################################################################
Name: BillingPage.java 
Description: Configuration Report page objects with all xpaths
Author: Subrahmanyam L
Date Created:01/28/2014
Date Modified:
Updated Comments:
#####################################################################################################*/
package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfigurationReportPage {
	public final String CLIENTNAME_XPATH = "//select[@id='taxpayersList']";
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Company Name')]/../../../tbody/tr/td[2]")
	public WebElement TAXINFO_TAXPAYERNAME_XPATH;
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Company Name')]/../../../tbody/tr/td[1]")
	public WebElement TAXINFO_CODE_XPATH;
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Company Name')]/../../../tbody/tr/td[4]")
	public WebElement TAXINFO_FEDERALIDVALUE_XPATH;
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Company Name')]/../../../tbody/tr/td[6]")
	public WebElement TAXINFO_DATE_XPATH;
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Company Name')]/../../../tbody/tr/td[8]")
	public WebElement TAXINFO_FILINGQUANTITY_XPATH;
	
	@FindBy(xpath = "//a[@title='Configuration Report']")
	public WebElement CONFIGREPORTTAB;
	
	@FindBy(xpath = "//div/h1")
	public WebElement CONFIGLABEL;
	
	@FindBy(xpath = "//span[@class='help-popover']/span")
	public WebElement HELPICON;
	
	@FindBy(xpath = "//h3[@class='popover-title']")
	public WebElement HELPTITLE;
	
	@FindBy(xpath = "//div[@class='popover-content']")
	public WebElement HELPCONTENT;
	
	@FindBy(xpath = "//select[@id='taxpayersList']")
	public WebElement TAXDROPDOWN;
	
	@FindBy(xpath = "//div[7]/div[2]/div/pagination-extension/span/select")
	public WebElement JURINFODROPDOWN;
	
	@FindBy(xpath = "//p[Contains(text(),'Taxpayer Information')]")
	public WebElement TAXINFO_HEADING;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Company Code')]")
	public WebElement TAXINFO_COMPANYCODE;
	
	@FindBy(xpath = "//a[contains(.,'EXPORT')]")
	public WebElement EXPORTBUTON;

	@FindBy(xpath = "//a[contains(.,'Export')]")
	public WebElement ADMINEXPORTBUTON;

	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Company Name')]/../../../tbody/tr/td[2]")
	public WebElement TAXINFO_TAXPAYERNAME;
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Company Name')]/../../../tbody/tr/td[1]")
	public WebElement TAXINFO_CODE;
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Company Name')]/../../../tbody/tr/td[4]")
	public WebElement TAXINFO_FEDERALIDVALUE;
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Company Name')]/../../../tbody/tr/td[6]")
	public WebElement TAXINFO_DATE;
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Company Name')]/../../../tbody/tr/td[8]")
	public WebElement TAXINFO_FILINGQUANTITY;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Company Name')]")
	public WebElement TAXINFO_TAXNAME;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Federal ID Number')]")
	public WebElement TAXINFO_FEDERALID;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Business Number')]")
	public WebElement TAXINFO_BUSSINESSNUMBER;
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'Start Date')]")
	public WebElement TAXINFO_STARTDATE;
	
	@FindBy(xpath = "//table[@id='tblTaxpayers']/thead/tr/th[contains(.,'End Date')]")
	public WebElement TAXINFO_ENDDATE;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Is Filing Entity')]")
	public WebElement TAXINFO_FILINGENTITY;
	
	@FindBy(xpath = "//p[Contains(text(),'Taxpayer Address')]")
	public WebElement TAXADDRESS_HEADING;
	
	@FindBy(xpath = "//table/thead/tr/th[1][contains(.,'Street Address')]")
	public WebElement TAXADDRESS_STREETADDRESS;
	
	@FindBy(xpath = "//table/thead/tr/th[2][contains(.,'City')]")
	public WebElement TAXADDRESS_CITY;
	
	@FindBy(xpath = "//table/thead/tr/th[2][contains(.,'Municipality')]")
	public WebElement TAXADDRESS_MUNICIPALITY;
	
	@FindBy(xpath = "//table[@id='tblTxpAddr']/thead/tr/th[contains(.,'State')]")
	public WebElement TAXADDRESS_STATE;
	
	@FindBy(xpath = "//table[@id='tblTxpAddr']/thead/tr/th[contains(.,'Province/Territory')]")
	public WebElement TAXADDRESS_PROVINCETERRITORY;
	
	@FindBy(xpath = "//table/thead/tr/th[6][contains(.,'Zip')]")
	public WebElement TAXADDRESS_ZIP;
	
	@FindBy(xpath = "//span[contains(text(),'Configuration Report')]")
	public WebElement CONFIGURATIONREPORT;
	
	@FindBy(xpath = "//div[@class='span3']/select[@id='clientSelect']")
	public WebElement ADMINCLIENTNAME;
	
	@FindBy(xpath = "//select[@id='taxpayersList']")
	public WebElement ADMINTAXPAYER;
	
	@FindBy(xpath = "//input[@value='Export']")
	public WebElement EXPORTBTN;
	
	@FindBy(xpath = "//p[contains(text(),'Company Locations')]")
	public WebElement TAXLOC_HEADING;
	
	@FindBy(xpath = "//th[text()='Location Code']")
	public WebElement TAXLOC_LOCATIONCODE;
	
	@FindBy(xpath = "//th[text()='Location Name']")
	public WebElement TAXLOC_LOCATIONNAME;
	
	@FindBy(xpath = "//table/thead/tr/th[3][contains(text(),'Street Address')]")
	public WebElement TAXLOC_STREETADDRESS;
	
	@FindBy(xpath = "//table/thead/tr/th[4][contains(text(),'City')]")
	public WebElement TAXLOC_CITY;
	
	@FindBy(xpath = "//table/thead/tr/th[5][contains(text(),'Jurisdiction')]")
	public WebElement TAXLOC_STATE;
	
	@FindBy(xpath = "//table/thead/tr/th[6][contains(text(),'Zip')]")
	public WebElement TAXLOC_ZIP;
	
	@FindBy(xpath = "//table/thead/tr/th[7][contains(text(),'Start Date')]")
	public WebElement TAXLOC_STARTDATE;
	
	@FindBy(xpath = "//table/thead/tr/th[8][contains(text(),'End Date')]")
	public WebElement TAXLOC_ENDDATE;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Registration Code')]")
	public WebElement TAXLOC_REGCODE;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'In City Limits')]")
	public WebElement INCITYLIMITS;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Physical Location')]")
	public WebElement PHYSICALLOCATION;
	
//	@FindBy(xpath= "//p[Contains(.,'Taxpayer Returns Information')]")
	
	@FindBy(xpath = "//p[@class='h4' and Contains(.,'Taxpayer Returns Information')]")
	public WebElement TAXRETURNS_HEADING;
	
	@FindBy(xpath = "//div/p[contains(.,'Company Returns Information')]/../../table/thead/tr/th[contains(.,'Registration Number')]")
	public WebElement TAXRETURNS_REGNUMBER;
	
	@FindBy(xpath = "//div/p[contains(.,'Company Returns Information')]/../../table/thead/tr/th[contains(.,'Return Name')]")
	public WebElement TAXRETURNS_RETURNNAME;
	
	@FindBy(xpath = "//div/p[contains(.,'Company Returns Information')]/../../table/thead/tr/th[contains(.,'Jurisdiction Name')]")
	public WebElement TAXRETURNS_JURINAME;
	
	@FindBy(xpath = "//div/p[contains(.,'Company Returns Information')]/../../table/thead/tr/th[contains(.,'Jurisidiction Type')]")
	public WebElement TAXRETURNS_JURITYPE;
	
	@FindBy(xpath = "//div/p[contains(.,'Company Returns Information')]/../../table/thead/tr/th[contains(.,'Filing Method')]")
	public WebElement TAXRETURNS_FILINGMETHOD;
	
	@FindBy(xpath = "//div/p[contains(.,'Company Returns Information')]/../../table/thead/tr/th[contains(.,'Filing Frequency')]")
	public WebElement TAXRETURNS_FILINGFREQ;
	
	@FindBy(xpath = "//div/p[contains(.,'Company Returns Information')]/../../table/thead/tr/th[contains(.,'Return Level')]")
	public WebElement RETURNLEVEL;
	
	@FindBy(xpath = "//div/p[contains(.,'Company Returns Information')]/../../table/thead/tr/th[contains(.,'Return Type')]")
	public WebElement RETURNTYPE;
	
	@FindBy(xpath = "//p[Contains(text(),'Customer Information')]")
	public WebElement TAXCUSTOMERINFO_HEADING;
	
	@FindBy(xpath = "//div/p[contains(.,'Customer Information')]/../../table/thead/tr/th[contains(.,'Customer Code')]")
	public WebElement TAXCUSTOMERINFO_CUSTOMERCODE;
	
	@FindBy(xpath = "//div/p[contains(.,'Customer Information')]/../../table/thead/tr/th[contains(.,'Customer Name')]")
	public WebElement TAXCUSTOMERINFO_CUSTOMERNAME;
	
	@FindBy(xpath = "//div/p[contains(.,'Customer Information')]/../../table/thead/tr/th[contains(.,'Customer Type')]")
	public WebElement TAXCUSTOMERINFO_CUSTOMERTYPE;
	
	@FindBy(xpath = "//div/p[contains(.,'Customer Information')]/../../table/thead/tr/th[contains(.,'Tax Result')]")
	public WebElement TAXCUSTOMERINFO_TAXRESULT;
	
	@FindBy(xpath = "//div/p[contains(.,'Customer Information')]/../../table/thead/tr/th[contains(.,'Deduction Category')]")
	public WebElement TAXCUSTOMERINFO_DEDUCTIONCATEGORY;
	
	@FindBy(xpath = "//div/p[contains(.,'Customer Information')]/../../table/thead/tr/th[contains(.,'Start Date')]")
	public WebElement TAXCUSTOMERINFO_STARTDATE;
	
	@FindBy(xpath = "//div/p[contains(.,'Customer Information')]/../../table/thead/tr/th[contains(.,'End Date')]")
	public WebElement TAXCUSTOMERINFO_ENDDATE;
	
	@FindBy(xpath = "//div/p[contains(.,'Jurisdiction Information')]/../../table/thead/tr/th[contains(.,'Name')]")
	public WebElement TAXPAYER_JURINAME;
	
	@FindBy(xpath = "//div/p[contains(.,'Jurisdiction Information')]/../../table/thead/tr/th[contains(.,'Jurisdiction Type')]")
	public WebElement TAXPAYER_JURITYPE;
	
	@FindBy(xpath = "//p[Contains(text(),'Discount Codes')]")
	public WebElement DISCOUNTCODES_HEADING;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/thead/tr/th[text()='Type']")
	public WebElement DISCOUNTCODESTYPE;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/thead/tr/th[text()='Code']")
	public WebElement DISCOUNTCODESCODE;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[contains(.,'General Discount')]")
	public WebElement GENERALDISCOUNT;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[contains(.,'General Discount')]/../td[contains(text(),'1')]")
	public WebElement GENERALDISCOUNTVALUE;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[contains(.,'Cash Discount')]")
	public WebElement CASHDISCOUNT;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[contains(.,'Cash Discount')]/../td[contains(text(),'2')]")
	public WebElement CASHDISCOUNTVALUE;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[contains(.,'Manufacturer')]")
	public WebElement MANUFACTURERCOUPONS;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[contains(.,'Manufacturer')]/../td[contains(text(),'3')]")
	public WebElement MANUFACTURERCOUPONSVALUE;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[contains(.,'Retailer')]")
	public WebElement RETAILERCOUPONS;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[contains(.,'Retailer')]/../td[contains(text(),'4')]")
	public WebElement RETAILERVALUE;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[text()='Trade Discount']")
	public WebElement TRADEDISCOUNT;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[text()='Trade Discount']/../td[contains(text(),'5')]")
	public WebElement TRADEDISCOUNTVALUE;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[text()='Quantity Discount']")
	public WebElement QUANTITYDISCOUNT;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[text()='Quantity Discount']/../td[contains(text(),'6')]")
	public WebElement QUANTITYDISCOUNTVALUE;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[text()='Early Payment Discount']")
	public WebElement EARLYPAYMENTDISCOUNT;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[text()='Early Payment Discount']/../td[contains(text(),'7')]")
	public WebElement EARLYPAYMENTDISCOUNTVALUE;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[text()='Flexible Discount']")
	public WebElement FLEXIBLEDISCOUNT;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../table/tbody/tr/td[text()='Flexible Discount']/../td[contains(text(),'8')]")
	public WebElement FLEXIBLEDISCOUNTVALUE;
	
	@FindBy(xpath = "//p[contains(.,'Customer Information')]/../../table/tbody/tr[@class='ng-scope']/td/div/div/a[contains(text(),'Certificates')]")
	public WebElement TAXCUSTOMERINFO_CERTIFICATE;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Certificate Class')]")
	public WebElement TAXCUSTOMERINFO_CERTIFICATECLASS;
	
	@FindBy(xpath = "//table/thead/tr/th[2][contains(text(),'Jurisdiction')]")
	public WebElement TAXCUSTOMERINFO_JURISDICTION;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Jurisdiction Type')]")
	public WebElement TAXCUSTOMERINFO_JURISDICTIONTYPE;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Certificate Reason')]")
	public WebElement TAXCUSTOMERINFO_CERTIFICATEREASON;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'Start Date')]")
	public WebElement TAXCUSTOMERINFO_CERTIFICATESTARTDATE;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(text(),'End Date')]")
	public WebElement TAXCUSTOMERINFO_CERTIFICATEENDDATE;
	
	@FindBy(xpath = "//table/tbody/tr[1]/td[8]/a/i")
	public WebElement TAXCUSTOMERINFO_CERTIFICATEPLUSICON;
	
	@FindBy(xpath = "//table/tbody/tr[2]/td/div[1]/table/thead/tr/th[contains(text(),'Name')]")
	public WebElement TAXCUSTOMERINFO_PRODUCTNAME;
	
	@FindBy(xpath = "//table/tbody/tr[2]/td/div/table/thead/tr/th[2]")
	public WebElement TAXCUSTOMERINFO_PRODUCTCODE;
	
	@FindBy(xpath = "//div/table/tbody/tr[2]/td/div[1]/table/thead/tr/th[contains(text(),'Type')]")
	public WebElement TAXCUSTOMERINFO_PRODUCTTYPE;
	
	@FindBy(xpath = "//p[Contains(text(),'Mappings')]")
	public WebElement TAXPRODUCTINFO_HEADING;
	
	@FindBy(xpath = "//div/p[contains(.,'Mappings')]/../../table/thead/tr/th[contains(.,'Code / Class')]")
	public WebElement TAXPRODUCTINFO_PRODUCTCODE;
	
	@FindBy(xpath = "//div/p[contains(.,'Mappings')]/../../table/thead/tr/th[contains(.,'Name')]")
	public WebElement TAXPRODUCTINFO_PRODUCTNAME;
	
	@FindBy(xpath = "//div/p[contains(.,'Mappings')]/../../table/thead/tr/th[contains(.,'Type')]")
	public WebElement TAXPRODUCTINFO_PRODUCTTYPE;
	
	@FindBy(xpath = "//div/p[contains(.,'Mappings')]/../../table/thead/tr/th[contains(.,'Start Date')]")
	public WebElement TAXPRODUCTINFO_STARTDATE;
	
	@FindBy(xpath = "//div/p[contains(.,'Mappings')]/../../table/thead/tr/th[contains(.,'End Date')]")
	public WebElement TAXPRODUCTINFO_ENDDATE;
	
	@FindBy(xpath = "//div/p[contains(.,'Mappings')]/../../table/thead/tr/th[contains(.,'Taxability Category')]")
	public WebElement TAXPRODUCTINFO_TAXABILITYCATEGEORY;
	
	@FindBy(xpath = "//select[@id='taxpayersList']")
	public WebElement CLIENTNAME;
	
	@FindBy(xpath = "//table/thead/tr/th[text()='Certificate Class']")
	public WebElement CERTIFICATECLASS;
	
	@FindBy(xpath = "//table/thead/tr/th[text()='Jurisdiction']")
	public WebElement JURISDICTION;
	
	@FindBy(xpath = "//table/thead/tr/th[text()='Jurisdiction Type']")
	public WebElement JURISDICTIONTYPE;
	
	@FindBy(xpath = "//table/thead/tr/th[text()='Exemption Reason']")
	public WebElement CERTIFICATEREASSON;
	
	@FindBy(xpath = "//table/thead/tr/th[text()='Certificate Number']")
	public WebElement CERTIFICATENUMBER;
	
	@FindBy(xpath = "//table/thead/tr/th[text()='Start Date']")
	public WebElement STARTDATE;
	
	@FindBy(xpath = "//table/thead/tr/th[text()='End Date']")
	public WebElement ENDDATE;
	
	// Admin Configuration Report
	@FindBy(xpath = "//span[contains(text(),'Configuration Report')]")
	public WebElement ADMINCONFIGURATIONREPORT;
	
	@FindBy(xpath = "//select[@id='clientSelect']")
	public WebElement ADMINCLIENTSELECT;
	
	@FindBy(xpath = "//a[contains(.,'EXPORT')]")
	public WebElement ADMINEXPORT;
	
	@FindBy(xpath = "//div/div/p[contains(.,'Discount Code')]/../../div/div/pagination-extension/span/select")
	public WebElement SELECTDISOCUNTCODE;
	
	@FindBy(xpath = "//a[text()='DOWNLOAD PRINTABLE REPORT']")
	public WebElement DWNLDPRINTREPRT;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Customer Date Added')]")
	public WebElement DATEADDED_CUSTOMER;
	
	@FindBy(xpath = "//div/p[contains(.,'Mappings')]/../../table/thead/tr/th[contains(.,'Date Added')]")
	public WebElement DATEADDED_PRODUCT;
	
	@FindBy(xpath = "//div[2]/div/table/thead/tr/th[1][text()='Date Added']")
	public WebElement CERTIFICATESTABLE;
	
	@FindBy(xpath = "//a[text()='Certificates ']")
	public WebElement CERTIFICATES;
	
	// 21038 Enable the Import Reverse Charges function on VAT Registration Screens
	@FindBy(xpath = "//div/p[contains(.,'International Registrations')]")
	public WebElement INTERNATIONALREGISTRATIONSSECTION;
	
	@FindBy(xpath = "//table/thead/tr/th[contains(.,'Name')]/../th[contains(.,'VAT Registration Number')]/../th[contains(.,'Physical Presence')]/../th[contains(.,'Import Reverse Charge')]")
	public WebElement INTERNATIONALREGISTRATIONSCOLUMNS;
	
	@FindBy(xpath = "//span/i[@class='icon-section icon-reports']/..")
	public WebElement Reports_Overview;
	
	@FindBy(xpath = "//span[@class='span12 h6']")
	public WebElement Reports_Overview_Text;
	
	@FindBy(xpath = "//a[contains(.,'Configuration')]")
	public WebElement ConfigurationLink;
	
	@FindBy(xpath = "//span[contains(.,'Find configuration information for a specific company.')]")
	public WebElement ConfigurationText;

	@FindBy(xpath = "//span[contains(.,'Identify tax exceptions that are relevant to your business. View the taxability of product categories in the configured jurisdictions.')]")
	public WebElement TaxabilityReportText;

	@FindBy(xpath = "//div/p[contains(.,'International Registrations')]/../../table/thead/tr/th[contains(.,'Name')]/../../../tbody/tr/td[contains(.,'No records found.')]")
	public WebElement NORECORDSINTERNATIOALJURIS;

	@FindBy(xpath = "//h2[contains(.,'SUMMARY OF CHANGES REPORT')]")
	public WebElement SUMMARYCHANGESHEADING;

	@FindBy(xpath = "//a[contains(.,'Summary of Changes')]")
	public WebElement SUMMARYOFCHANGESREPORT;

	@FindBy(xpath = "//p[contains(.,'Download a PDF file that outlines all recent tax content changes.This file is updated before the 1st each month')]")
	public WebElement SUMMARYCHANGESTEXT;

	@FindBy(xpath = "//pagination-extension[@current-page='data.taxpayerLocationTable.currentPage']/span/select")
	public WebElement LOCATIONSPAGINATION;
	
	@FindBy(xpath = "//p[contains(.,'Customer Information')]")
	public WebElement Customerinfo;
	
	@FindBy(xpath = "//pagination-extension[@current-page='data.certificateTable.currentPage']/span/select")
	public WebElement CertificatesPAGINATION;
	
	@FindBy(xpath = "//a[contains(.,'EXPORT')]")
	public WebElement EXPORT;
	
	
}
