package common.libraries.ecm;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import common.libraries.generic.CommonFunctions;
import common.pages.ECMBulkUpload;
import common.pages.ECMPortalPages;

public class ECMSteps extends CommonFunctions {

	private WebDriver driver;

	public ECMSteps(WebDriver driver) throws Exception {
		this.driver = driver;
	}
	
	ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
	public static String parentwindow;

	public ECMSteps searchtextbox(String ColumnName, String SearchText) throws Exception {
		WebElement ColumnsearchBox = driver
				.findElement(By.xpath("//div[@class='ag-header-cell-label']/span[contains(.,'" + ColumnName + "')]"));
		JavaScriptscrolltoView(driver, ColumnsearchBox);
		WebElement searchbox = driver.findElement(
				By.xpath("//div[@aria-colindex='" + ColumnsearchBox.getAttribute("aria-colindex") + "']/div/input"));
		waitforElement(180, searchbox, driver);
		click(searchbox);
		type(searchbox, SearchText);
		return this;
	}

	public ECMSteps created_searchtextbox(String ColumnName, String SearchText) throws Exception {
		WebElement ColumnsearchBox = driver
				.findElement(By.xpath("//div[@class='ag-header-cell-label']/span[contains(.,'" + ColumnName + "')]"));
		JavaScriptscrolltoView(driver, ColumnsearchBox);
		Actions aact = new Actions(driver);
		aact.moveToElement(driver.findElement(By.xpath(
				"//div[@aria-colindex='" + ColumnsearchBox.getAttribute("aria-colindex") + "']/div/div/div/input")))
		.build().perform();
		WebElement searchbox = driver
				.findElement(By.xpath("//div[@class='ag-header-cell ag-column-hover']/div[@aria-colindex='"
						+ ColumnsearchBox.getAttribute("aria-colindex") + "']/div/div/div/input"));
		type(searchbox, SearchText);
		return this;
	}

	public ECMSteps click_column_filter(String ColumnName) throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		WebElement Columnfilter = driver
				.findElement(By.xpath("//div[@class='ag-header-cell-label']/span[contains(.,'" + ColumnName + "')]"));
		WebElement filter = driver.findElement(
				By.xpath("//div[@aria-colindex='" + Columnfilter.getAttribute("aria-colindex") + "']/../div/button"));
		try {
			if((ColumnName.equalsIgnoreCase("Reason"))||ColumnName.equalsIgnoreCase("Scope")||ColumnName.equalsIgnoreCase("Created")||ColumnName.equalsIgnoreCase("Customer Number")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 250",
						ecmportal.HORIZONTALSCROLLBAR);
				click(filter);
			}else {
				click(filter);}
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 250",
					ecmportal.HORIZONTALSCROLLBAR);
			click(filter);
		}
		return this;
	}

	public ECMSteps click_column(String ColumnName) throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		WebElement Column = driver
				.findElement(By.xpath("//div[@class='ag-header-cell-label']/span[contains(.,'" + ColumnName + "')]"));
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 250",
					ecmportal.HORIZONTALSCROLLBAR);
			click(Column);
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 250",
					ecmportal.HORIZONTALSCROLLBAR);
			click(Column);
		}
		return this;
	}

	public ECMSteps select_filter_options(String SelectFilterOption) throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		if (!SelectFilterOption.isEmpty()) {
			waitforElement(60, ecmportal.FILTERSELECTOPTIONS, driver);
			selectByValue(ecmportal.FILTERSELECTOPTIONS, SelectFilterOption);
		}
		return this;
	}

	public ECMSteps click_and_or(String Condition) throws Exception {
		if (!Condition.isEmpty()) {
			waitforElement(180, driver.findElement(By.xpath("//input[@value='" + Condition + "']")), driver);
			WebElement andOr = driver.findElement(By.xpath("//input[@value='" + Condition + "']"));
			click(andOr);
		}
		return this;
	}

	public ECMSteps select_andOR_filter_options(String ANDORFilterOPTIONS) throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		if (!ANDORFilterOPTIONS.isEmpty()) {
			waitforElement(60, ecmportal.SELECTANDOROPTIONS, driver);
			selectByValue(ecmportal.SELECTANDOROPTIONS, ANDORFilterOPTIONS);
		}
		return this;
	}

	public ECMSteps type_filter_text(String FilterText) throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		if (!FilterText.isEmpty()) {
			if (VerifyObject(driver, ecmportal.FILTERTEXT)) {
				click(ecmportal.FILTERTEXT);
				type(ecmportal.FILTERTEXT, FilterText);
			} else {
				click(ecmportal.FILTERTEXT1);
				type(ecmportal.FILTERTEXT1, FilterText);
			}
		}
		return this;
	}

	public ECMSteps type_andOR_filter_text(String ANDORFilterText, String ColumnName) throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		if (!ANDORFilterText.isEmpty()) {
			if (VerifyObject(driver, ecmportal.ANDORFILTERTEXT)) {
				click(ecmportal.ANDORFILTERTEXT);
				type(ecmportal.ANDORFILTERTEXT, ANDORFilterText);
			} else {
				click_column_filter(ColumnName);
				Thread.sleep(1000);
				click(ecmportal.ANDORFILTERTEXT1);
				type(ecmportal.ANDORFILTERTEXT1, ANDORFilterText);
			}

		}
		return this;
	}

	public String get_certificateid_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRTSCERTIFICATE, driver);
		return getText(ecmportal.FIRTSCERTIFICATE);
	}
	
	public String get_customerid_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRTSCUSTOMERCODE, driver);
		return getText(ecmportal.FIRTSCUSTOMERCODE);
	}

	public String get_certificatestatus_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.CERTFICATESTATUS, driver);
		return getText(ecmportal.CERTFICATESTATUS);
	}

	public String get_certificatearrovestatus_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.CERTFICATEAPPROVESTATUS, driver);
		return getText(ecmportal.CERTFICATEAPPROVESTATUS);
	}

	public String get_seccertificateid_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.SECONDCERTIFICATE, driver);
		return getText(ecmportal.SECONDCERTIFICATE);
	}
	
	public String get_seccustomerid_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.SECONDCUSTOMER, driver);
		return getText(ecmportal.SECONDCUSTOMER);
	}

	public String get_seccertificecustomer_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERNAME, driver);
		return getText(ecmportal.FIRSTCUSTOMERNAME);
	}

	public ECMSteps get_certificateid_click() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRTSCERTIFICATE, driver);
		click(ecmportal.FIRTSCERTIFICATE);
		return this;
	}

	public ECMSteps get_ECMadditionalDocumentation_click() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.ADDITIONALDOCUMENTATION, driver);
		click(ecmportal.ADDITIONALDOCUMENTATION);
		return this;
	}

	public List<WebElement> get_list_of_Additional_Documentaion_Links() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.ADDITITONALLINKSLIST;
	}

	public ECMSteps ECMContact_info_click() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.CONTACTINFORMATION, driver);
		click(ecmportal.CONTACTINFORMATION);
		return this;
	}

	public String get_seccertificatepriority_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTPRIORITY, driver);
		return getText(ecmportal.FIRSTPRIORITY);
	}

	public String get_seccertificatesource_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTSOURCE, driver);
		return getText(ecmportal.FIRSTSOURCE);
	}

	public String get_seccertificateexpirationdate_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTEXPIRATIONDATE, driver);
		return getText(ecmportal.FIRSTEXPIRATIONDATE);
	}

	public String get_seccertificateenddate_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTENDDATE, driver);
		return getText(ecmportal.FIRSTENDDATE);
	}

	public String get_seccertificateowner_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTOWNER, driver);
		return getText(ecmportal.FIRSTOWNER);
	}

	public String get_seccertificatejurisdiction_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTJURISDICTION, driver);
		return getText(ecmportal.FIRSTJURISDICTION);
	}

	public String get_seccertificatereason_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTREASON, driver);
		return getText(ecmportal.FIRSTREASON);
	}

	public String get_seccertificatescope_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCERTICATESCOPE, driver);
		return getText(ecmportal.FIRSTCERTICATESCOPE);
	}

	public String get_seccertificatestartedate_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTSTARTDATE, driver);
		return getText(ecmportal.FIRSTSTARTDATE);
	}

	public String get_seccertificatecustomernumber_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERNUMBER, driver);
		return getText(ecmportal.FIRSTCUSTOMERNUMBER);
	}

	public String get_seccertificateseller_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTSELLERCODE, driver);
		return getText(ecmportal.FIRSTSELLERCODE);
	}

	public String get_seccertificatetype_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCERTIFICATETYPE, driver);
		return getText(ecmportal.FIRSTCERTIFICATETYPE);
	}

	public String get_seccertificatecustomerfield1_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERFIELD1, driver);
		return getText(ecmportal.FIRSTCUSTOMERFIELD1);
	}

	public String get_seccertificatecustomerfield2_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERFIELD2, driver);
		return getText(ecmportal.FIRSTCUSTOMERFIELD2);
	}

	public String get_seccertificatecustomerfield3_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERFIELD3, driver);
		return getText(ecmportal.FIRSTCUSTOMERFIELD3);
	}

	public String get_seccertificatecustomerfield4_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERFIELD4, driver);
		return getText(ecmportal.FIRSTCUSTOMERFIELD4);
	}

	public String get_seccertificatecustomerfield5_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERFIELD5, driver);
		return getText(ecmportal.FIRSTCUSTOMERFIELD5);
	}

	public String get_seccertificatecustomerfield6_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERFIELD6, driver);
		return getText(ecmportal.FIRSTCUSTOMERFIELD6);
	}

	public String get_seccertificatecustomerfield7_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERFIELD7, driver);
		return getText(ecmportal.FIRSTCUSTOMERFIELD7);
	}

	public String get_seccertificatecustomerfield8_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERFIELD8, driver);
		return getText(ecmportal.FIRSTCUSTOMERFIELD8);
	}

	public String get_seccertificatecustomerfield9_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERFIELD9, driver);
		return getText(ecmportal.FIRSTCUSTOMERFIELD9);
	}

	public String get_seccertificatecustomerfield10_text() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.FIRSTCUSTOMERFIELD10, driver);
		return getText(ecmportal.FIRSTCUSTOMERFIELD10);
	}

	public String ECMCustomername() throws Exception {
		//waitforElement(30, ecmportal.ECMCUSTOMERNAME, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMCUSTOMERNAME.getAttribute("value");
		return getvalue;
	}

	public String ECMCerttype() throws Exception {
		//waitforElement(30, ecmportal.ECMCERTTYPE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMCERTTYPE.getAttribute("value");
		return getvalue;
	}

	public String ECMJurisdictions() throws Exception {
		//waitforElement(30, ecmportal.ECMJURISDICTIONS, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMJURISDICTIONS.getAttribute("value");
		return getvalue;
	}

	public String ECMReason() throws Exception {
		//waitforElement(30, ecmportal.ECMREASON, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMREASON.getAttribute("value");
		return getvalue;
	}

	public String ECMScope() throws Exception {
		//waitforElement(30, ecmportal.ECMSCOPE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMSCOPE.getAttribute("value");
		return getvalue;
	}

	public String ECMStartdate() throws Exception {
		//waitforElement(30, ecmportal.ECMSTARTDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMSTARTDATE.getAttribute("value");
		return getvalue;
	}

	public String ECMExpirationdate() throws Exception {
		//waitforElement(30, ecmportal.ECMEXPIRATIONDATES, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMEXPIRATIONDATES.getAttribute("value");
		return getvalue;
	}

	public String ECMIssuedate() throws Exception {
		//waitforElement(30, ecmportal.ECMISSUEDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMISSUEDATE.getAttribute("value");
		return getvalue;
	}

	public String ECMEnddate() throws Exception {
		//waitforElement(30, ecmportal.ECMENDDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMENDDATE.getAttribute("value");
		return getvalue;
	}

	public String ECMReviewdate() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMREVIEWDATE.getAttribute("value");
		return getvalue;
	}

	public String ECMSellernamecode() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMSELLERNAMECODE.getAttribute("value");
		return getvalue;
	}

	public String ECMSource() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(30, ecmportal.ECMSOURCE, driver);
		return getText(ecmportal.ECMSOURCE);
	}

	public String ECMSource1() throws Exception {
		//waitforElement(30, ecmportal.ECMSOURCE1, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		selectByValue(ecmportal.ECMSOURCE1, "Manual");
		return getText(ecmportal.ECMSOURCE1);
	}

	public String ECMForm() throws Exception {
		//waitforElement(30, ecmportal.ECMFORM, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return getText(ecmportal.ECMFORM);
	}

	public String ECMProducts() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMPRODUCTS.getAttribute("value");
		return getvalue;
	}

	public String ECMNotes() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.ECMNOTES.getAttribute("value");
		return getvalue;
	}

	public String ECMCustomfiled1() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMFIELD1.getAttribute("value");
		return getvalue;
	}
	public String ECMCustomfiled2() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMFIELD2.getAttribute("value");
		return getvalue;
	}
	public String ECMCustomfiled3() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMFIELD3.getAttribute("value");
		return getvalue;
	}
	public String ECMCustomfiled4() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMFIELD4.getAttribute("value");
		return getvalue;
	}
	public String ECMCustomfiled5() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMFIELD5.getAttribute("value");
		return getvalue;
	}
	public String ECMCustomfiled6() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMFIELD6.getAttribute("value");
		return getvalue;
	}
	public String ECMCustomfiled7() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMFIELD7.getAttribute("value");
		return getvalue;
	}
	public String ECMCustomfiled8() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMFIELD8.getAttribute("value");
		return getvalue;
	}
	public String ECMCustomfiled9() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMFIELD9.getAttribute("value");
		return getvalue;
	}
	public String ECMCustomfiled10() throws Exception {
		//waitforElement(30, ecmportal.ECMREVIEWDATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMFIELD10.getAttribute("value");
		return getvalue;
	}

	public String ECMContactfirstfirstlastname() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.CONTACTFIRSTLASTNAME, driver);
		String getvalue=ecmportal.CONTACTFIRSTLASTNAME.getAttribute("value");
		return getvalue;
	}

	public String ECMContactname() throws Exception {
		//waitforElement(180, ecmportal.CONTACTNAME, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CONTACTNAME.getAttribute("value");
		return getvalue;
	}

	public String ECMContactaddress() throws Exception {
		//waitforElement(180, ecmportal.CONTACTADDRESS, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CONTACTADDRESS.getAttribute("value");
		return getvalue;
	}

	public String ECMContactaddress2() throws Exception {
		//waitforElement(180, ecmportal.CONTACTADDRESS2, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CONTACTADDRESS2.getAttribute("value");
		return getvalue;
	}

	public String ECMContactcity() throws Exception {
		//waitforElement(180, ecmportal.CONTACTCITY, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CONTACTCITY.getAttribute("value");
		return getvalue;
	}

	public String ECMContactState() throws Exception {
		//waitforElement(180, ecmportal.CONTACTSTATE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CONTACTSTATE.getAttribute("value");
		return getvalue;
	}

	public String ECMContactPostalzip() throws Exception {
		//waitforElement(180, ecmportal.CONTACTPOSTALZIP, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CONTACTPOSTALZIP.getAttribute("value");
		return getvalue;
	}

	public String ECMCOntactCountry() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.CONTACTCOUNTRY, driver);
		String getvalue=ecmportal.CONTACTCOUNTRY.getAttribute("value");
		return getvalue;
	}

	public String ECMContactCustomerCountry() throws Exception {
		//waitforElement(180, ecmportal.CUSTOMERCOUNTRY, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CUSTOMERCOUNTRY.getAttribute("value");
		return getvalue;
	}

	public String ECMContactphone() throws Exception {
		//waitforElement(180, ecmportal.CONTACTPHONE, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CONTACTPHONE.getAttribute("value");
		return getvalue;
	}

	public String ECMContactEmail() throws Exception {
		//waitforElement(180, ecmportal.CONTACTEMAIL, driver);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		String getvalue=ecmportal.CONTACTEMAIL.getAttribute("value");
		return getvalue;
	}

	public List<WebElement> get_list_of_certid_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.CERTIDGRIDLIST;
	}

	public List<WebElement> get_list_of_created_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.CREATEDGRIDLIST;
	}

	public List<WebElement> get_list_of_customers_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.CUSTOMERNAMEGRIDLIST;
	}

	public List<WebElement> get_list_of_Jurisdictions_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.JURISDICTIONGRIDLIST;
	}

	public List<WebElement> get_list_of_startdate_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.STARTDATEGRIDLIST;
	}

	public List<WebElement> get_list_of_customernumber_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.CUSTOMERNUMBERGRIDLIST;
	}

	public List<WebElement> get_list_of_seller_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.SELLERCODEGRIDLIST;
	}

	public List<WebElement> get_list_of_reason_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.REASONGRIDLIST;
	}

	public List<WebElement> get_list_of_scope_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.SCOPEGRIDLIST;
	}

	public ECMSteps type_Created_From_Range(String FromDate) throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		type(ecmportal.FROMRANGE, FromDate);
		return this;
	}

	public ECMSteps type_Created_To_Range(String FromDate) throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		type(ecmportal.FROMRANGE, FromDate);
		return this;
	}

	public ECMSteps click_logout() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.LOGOUTICON);
		return this;
	}

	public boolean verify_logout_icon() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return VerifyObject(driver, ecmportal.LOGOUTICON);
	}

	public boolean verify_login_page() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.USERLOGINPAGE, driver);
		return VerifyObject(driver, ecmportal.USERLOGINPAGE);
	}

	public boolean verify_help_icon() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return VerifyObject(driver, ecmportal.HELPICON);
	}

	public ECMSteps click_help_icon() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.HELPICON);
		return this;
	}

	public ECMSteps click_Ellipse() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.ELLIPSE);
		return this;
	}

	public ECMSteps click_ECMCertRejectlink() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.ECMCERTREJECT);
		return this;
	}

	public ECMSteps click_ECMCertRejectbutton() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.ECMCERTREJECTBUTTON);
		return this;
	}

	public ECMSteps click_ECMCertRejectbuttonpopup() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.ECMCERTREJECTBUTTONPOPUP);
		return this;
	}

	public ECMSteps click_closeicon() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.CLOSEICON);
		return this;
	}

	public boolean verify_exemption_help() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		parentwindow = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(parentwindow)) {
				driver.switchTo().window(winHandle);
			}
		}
		return VerifyObject(driver, ecmportal.WELCOMETOHELPCENTRE);
	}

	public void close_child_switch_to_main_window() {
		driver.close();
		driver.switchTo().window(parentwindow);
	}

	public String get_user_info() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.USERINFO, driver);
		return getText(ecmportal.USERINFO);

	}

	public boolean verify_copy_right() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.CopyRightText, driver);
		return VerifyObject(driver, ecmportal.CopyRightText);

	}

	public void select_table_settings_options(String CoulmnName) throws Exception {
		waitforElement(180, "//label[contains(.,'" + CoulmnName + "')]/../input", driver);
		driver.findElement(By.xpath("//label[contains(.,'" + CoulmnName + "')]/../input")).click();
	}

	public ECMSteps click_selectAll() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.SELECTALL, driver);
		click(ecmportal.SELECTALL);
		return this;
	}

	public ECMSteps click_clearAll() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		waitforElement(180, ecmportal.SELECTALL, driver);
		click(ecmportal.CLEARALL);
		return this;
	}

	public ECMSteps click_apply_button() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.APPLYBUTTON);
		return this;
	}

	public ECMSteps select_cert_status_drop_down() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.CERTIFICATEDROPDOWN);
		click(ecmportal.ALL);
		return this;
	}

	public ECMSteps select_cert_rejectstatus_drop_down() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.CERTIFICATEDROPDOWN);
		click(ecmportal.REJECTEDSTATUS);
		return this;
	}

	public ECMSteps select_cert_approvestatus_drop_down() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.CERTIFICATEDROPDOWN);
		click(ecmportal.APPROVEDSTATUS);
		return this;
	}

	public ECMSteps click_table_settings() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.TABLESETTINGSICON);
		return this;
	}

	public ECMSteps click_cancel_button() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		click(ecmportal.CANCELBUTTON);
		return this;
	}

	public boolean isSelected(String CoulmnName) throws Exception {
		@SuppressWarnings("unused")
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return driver.findElement(By.xpath("//input[@id='" + CoulmnName + "']")).isSelected();
	}

	public ECMSteps check_column(String CoulmnName) throws Exception {
		driver.findElement(By.xpath("//label[contains(.,'" + CoulmnName + "')]")).click();
		return this;
	}

	public int get_page_list_count() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.PAGELIST.size();
	}

	public String get_last_page() throws Exception {
		return driver
				.findElement(By.xpath("(//span[@class='pagination__numbered-link'])[" + get_page_list_count() + "]"))
				.getText();
	}

	public String get_records_count() throws Exception {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.RECORDSCOUNT.getText();
	}

	public ECMSteps click_bulkupload_button() throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		waitforElement(180, ecmBulkUpload.BulkUploadButton, driver);
		click(ecmBulkUpload.BulkUploadButton);
		return this;
	}

	public ECMSteps click_downloadcsvtemplate_link() throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		waitforElement(180, ecmBulkUpload.downloadCsvTemplate, driver);
		JavaScriptclick(driver, ecmBulkUpload.downloadCsvTemplate);
		return this;
	}

	public ECMSteps click_uploadcsvfiles() throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		waitforElement(180, ecmBulkUpload.UploadCsvFile, driver);
		click(ecmBulkUpload.UploadCsvFile);
		return this;
	}

	public ECMSteps click_uploadzipfiles() throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		waitforElement(180, ecmBulkUpload.UploadZipFiles, driver);
		click(ecmBulkUpload.UploadZipFiles);
		return this;
	}

	public ECMSteps click_submit_button() throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		waitforElement(180, ecmBulkUpload.SubmitButton, driver);
		JavaScriptclick(driver, ecmBulkUpload.SubmitButton);
		waitforElement(180, ecmBulkUpload.UploadCsvFileTittle, driver);
		return this;
	}

	public ECMSteps click_clearall_button() throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		waitforElement(180, ecmBulkUpload.ClearAllButton, driver);
		click(ecmBulkUpload.ClearAllButton);
		return this;
	}

	public ECMSteps click_bulkuploadhistory_button() throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		waitforElement(180, ecmBulkUpload.BulkUploadHistoryButton, driver);
		click(ecmBulkUpload.BulkUploadHistoryButton);
		return this;
	}

	public ECMSteps click_backtocertificates_link() throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		waitforElement(180, ecmBulkUpload.BackToCertificates, driver);
		click(ecmBulkUpload.BackToCertificates);
		return this;
	}
	
	// Customer Queue
	public List<WebElement> get_list_of_customercode_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.CUSTOMERCODEGRIDLIST;
	}
	
	public List<WebElement> get_list_of_customerstartdate_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.CUSTOMERSTARTDATEGRIDLIST;
	}
	
	public List<WebElement> get_list_of_customerenddate_elements() {
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		return ecmportal.CUSTOMERENDDATEGRIDLIST;
	}

}
