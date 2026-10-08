package common.libraries.ecm;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import common.libraries.generic.CommonFunctions;
import common.libraries.miscellaneous.Features;
import common.pages.ECMBulkUpload;
import common.pages.ECMPortalPages;

public class ECMCustomers extends CommonFunctions {
	private WebDriver driver;

	public ECMCustomers(WebDriver driver) throws Exception {
		this.driver=driver;
	}
	
	Features features=PageFactory.initElements(driver, Features.class);
	ECMBulkUpload ecmBulkUpload = null;
	ECMSteps ecmstp = null;

	public void Customer_AGGrid(String TestsName, String DragColumn, String DropColumn, String ColumnName, String SearchBoxValue, String SelectFilterOption, String FilterWith,
			String Condition, String SelectANDORFilterOption, String ANDORTextFilter, String FromDate, String Todate) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		Thread.sleep(7000);
		ecmstp.select_cert_status_drop_down();
		switch (TestsName) {
		case "ColumnDragandDrop":
			columndraganddrop(DragColumn,DropColumn);
			break;
		case "VerifyColumnSearchBox":
			verify_Column_SearchBox(ColumnName, SearchBoxValue);
			break;
		case "VerifyCoulmnFilters":
			verify_Coulmn_Filters(ColumnName, SelectFilterOption,
					FilterWith, Condition, SelectANDORFilterOption, ANDORTextFilter,
					FromDate, Todate);
			break;
		}
		driver.navigate().refresh();
	}

	//Drag and drop functionality
	public void columndraganddrop(String DragColumn, String DropColumn) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			WebElement dragelement = driver.findElement(
					By.xpath("//div[@class='ag-header-cell-label']/span[contains(.,'" + DragColumn + "')]"));
			WebElement dropelement = driver.findElement(
					By.xpath("//div[@class='ag-header-cell-label']/span[contains(.,'" + DropColumn + "')]"));
			Thread.sleep(3000);
			Point dragpointer = dragelement.getLocation();
			JavaScriptscrolltoView(driver, dragelement);
			Actions act = new Actions(driver);
			act.dragAndDrop(dragelement, dropelement).build().perform();
			WebElement elementafterdrag = driver.findElement(
					By.xpath("//div[@class='ag-header-cell-label']/span[contains(.,'" + DragColumn + "')]"));
			Point positionofelementafterdrag = elementafterdrag.getLocation();
			assertTrue(dragpointer.getX() != positionofelementafterdrag.getX(),
					"Fail, " + DragColumn + "Column not Moved to Column " + DropColumn + " ;");
		} catch (Exception e) {
		}
	}

	//Search functionality
	public ECMCustomers verify_Column_SearchBox(String ColumnName, String SearchBoxValue) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			switch (ColumnName) {
			case "Customer Code":
				SearchBoxValue = ecmstp.get_customerid_text();
				ecmstp.searchtextbox(ColumnName, SearchBoxValue);
				break;
			case "Start Date":
				ecmstp.created_searchtextbox(ColumnName, SearchBoxValue);
				break;
			default:
				ecmstp.searchtextbox(ColumnName, SearchBoxValue);
				break;
			}
			Thread.sleep(5000);
			grid_Verifications_based_on_Cloumn(ColumnName, SearchBoxValue);
		} catch (Exception e) {
			new RuntimeException("verify_Column_SearchBox Method: " + e);
		}
		return this;
	}

	public void grid_Verifications_based_on_Cloumn(String ColumnName, String Serachwith) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		switch (ColumnName.toUpperCase()) {
		case "CUSTOMER CODE":
			verify_search_gridResults(ecmstp.get_list_of_customercode_elements(), Serachwith);
			break;
		case "CUSTOMER NAME":
			verify_search_gridResults(ecmstp.get_list_of_customers_elements(), Serachwith);
			break;
		case "START DATE":
			verify_search_gridResults(ecmstp.get_list_of_customerstartdate_elements(), Serachwith);
			break;
		case "END DATE":
			verify_search_gridResults(ecmstp.get_list_of_customerenddate_elements(), Serachwith);
			break;
		case "TAXPAYER":
			verify_search_gridResults(ecmstp.get_list_of_Jurisdictions_elements(), Serachwith);
			break;
		}
	}

	public void verify_search_gridResults(List<WebElement> elements, String SearchWith) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		int RowNumber = 0;
		for (WebElement element : elements) {
			assertTrue(contains(SearchWith, element),
					"Fail Coulmn Search Box at RowNumber" + RowNumber + 1 + "with text" + SearchWith);
		}
		;
	}

	public boolean contains(String SearchWith, WebElement element) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String text = getText(element);
		return text.contains(SearchWith);
	}

	//Filter functionality
	public ECMCustomers verify_Coulmn_Filters(String ColumnName, String SelectFilterOption, String FilterWith,
			String Condition, String SelectANDORFilterOption, String ANDORFILTERTxt, String FromDate, String ToDate)
					throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			switch (ColumnName) {
			case "Customer Code":
				FilterWith = ecmstp.get_customerid_text();
				if (!Condition.isEmpty()) {
					ANDORFILTERTxt = ecmstp.get_seccustomerid_text();
				}
				break;
			}
			ecmstp.click_column_filter(ColumnName).select_filter_options(SelectFilterOption)
			.type_filter_text(FilterWith).click_and_or(Condition)
			.select_andOR_filter_options(SelectANDORFilterOption).type_andOR_filter_text(ANDORFILTERTxt,ColumnName);
			Thread.sleep(5000);
			grid_Verifications_based_on_Cloumn(ColumnName, SelectFilterOption, FilterWith, Condition,
					SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
		} catch (Exception e) {
			new RuntimeException("verify_Coulmn_Filters Method: " + e);
		}
		return this;
	}

	public void grid_Verifications_based_on_Cloumn(String ColumnName, String SelectFilterOption, String FilterWith,
			String Condition, String SelectANDORFilterOption, String ANDORFILTERTxt, String FromDate, String ToDate)
					throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		switch (ColumnName.toUpperCase()) {
		case "CUSTOMER CODE":
			verify_filter_gridResults(ecmstp.get_list_of_customercode_elements(), SelectFilterOption, FilterWith,
					Condition, SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "CUSTOMER NAME":
			verify_filter_gridResults(ecmstp.get_list_of_customers_elements(), SelectFilterOption, FilterWith,
					Condition, SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "START DATE":
			verify_filter_gridResults(ecmstp.get_list_of_customerstartdate_elements(), SelectFilterOption, FilterWith,
					Condition, SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "END DATE":
			verify_filter_gridResults(ecmstp.get_list_of_customerenddate_elements(), SelectFilterOption, FilterWith, Condition,
					SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "TAXPAYER":
			verify_filter_gridResults(ecmstp.get_list_of_reason_elements(), SelectFilterOption, FilterWith,
					Condition, SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		}
	}

	public void verify_filter_gridResults(List<WebElement> elements, String SelectFilterOption, String SearchWith,
			String Condition, String SelectANDORFilterOption, String ANDORFILTERTxt, String FromDate, String ToDate)
					throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		boolean SelectFilterOptioN = false, SelectANDORFilterOptioN = false;
		for (WebElement element : elements) {
			switch (SelectFilterOption) {
			case "Contains":
				SelectFilterOptioN = contains(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Equals":
				SelectFilterOptioN = equals(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Not contains":
				SelectFilterOptioN = notcontains(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Not Contains":
				SelectFilterOptioN = notcontains(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Not equal":
				SelectFilterOptioN = notequals(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Not Equal":
				SelectFilterOptioN = notequals(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Starts with":
				SelectFilterOptioN = startswith(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Starts With":
				SelectFilterOptioN = startswith(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Ends with":
				SelectFilterOptioN = endswith(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Ends With":
				SelectFilterOptioN = endswith(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Greater than":
				SelectFilterOptioN = greaterthan(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Greater Than":
				SelectFilterOptioN = greaterthan(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Less than":
				SelectFilterOptioN = lessthan(SearchWith, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "Less Than":
				SelectFilterOptioN = lessthan(SearchWith, element);
				assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
						+ " or wrong data is displying in grid :" + SearchWith);
				break;
			case "In range":
				SelectFilterOptioN = inRange(FromDate, ToDate, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			case "In Range":
				SelectFilterOptioN = inRange(FromDate, ToDate, element);
				if(Condition.equalsIgnoreCase("OR")||Condition.equalsIgnoreCase("AND")) {

				}else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);}
				break;
			}
			switch (SelectANDORFilterOption) {
			case "Contains":
				SelectANDORFilterOptioN = contains(ANDORFILTERTxt, element);
				break;
			case "Equals":
				SelectANDORFilterOptioN = equals(ANDORFILTERTxt, element);
				break;
			case "Not contains":
				SelectANDORFilterOptioN = notcontains(ANDORFILTERTxt, element);
				break;
			case "Not Contains":
				SelectANDORFilterOptioN = notcontains(ANDORFILTERTxt, element);
				break;
			case "Not equal":
				SelectANDORFilterOptioN = notequals(ANDORFILTERTxt, element);
				break;
			case "Not Equal":
				SelectANDORFilterOptioN = notequals(ANDORFILTERTxt, element);
				break;
			case "Starts with":
				SelectANDORFilterOptioN = startswith(ANDORFILTERTxt, element);
				break;
			case "Starts With":
				SelectANDORFilterOptioN = startswith(ANDORFILTERTxt, element);
				break;
			case "Ends with":
				SelectANDORFilterOptioN = endswith(ANDORFILTERTxt, element);
				break;
			case "Ends With":
				SelectANDORFilterOptioN = endswith(ANDORFILTERTxt, element);
				break;
			case "Greater than":
				SelectANDORFilterOptioN = greaterthan(ANDORFILTERTxt, element);
				break;
			case "Greater Than":
				SelectANDORFilterOptioN = greaterthan(ANDORFILTERTxt, element);
				break;
			case "Less than":
				SelectANDORFilterOptioN = lessthan(ANDORFILTERTxt, element);
				break;
			case "Less Than":
				SelectANDORFilterOptioN = lessthan(ANDORFILTERTxt, element);
				break;
			case "In range":
				SelectANDORFilterOptioN = inRange(FromDate, ToDate, element);
				break;
			case "In Range":
				SelectANDORFilterOptioN = inRange(FromDate, ToDate, element);
				break;
			}
			if (Condition.equalsIgnoreCase("AND")) {
				AND(SelectFilterOptioN, SelectANDORFilterOptioN, SelectFilterOption, SelectANDORFilterOption,
						SearchWith, ANDORFILTERTxt);
			}
			if (Condition.equalsIgnoreCase("OR")) {
				OR(SelectFilterOptioN, SelectANDORFilterOptioN, SelectFilterOption, SelectANDORFilterOption,
						SearchWith, ANDORFILTERTxt);
			}

		}
		;
	}

	public void AND(boolean SearchStatus, boolean FilterSearchStatus, String FisrtFilter, String SecondFilter,
			String FilterWith, String SFilterWith) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		assertTrue(SearchStatus && FilterSearchStatus, "Fail, Certificate Filter with AND combination " + FisrtFilter
				+ SecondFilter + " With Data " + FilterWith + " " + SFilterWith);
	}

	public void OR(boolean SearchStatus, boolean FilterSearchStatus, String FisrtFilter, String SecondFilter,
			String FilterWith, String SFilterWith) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		assertTrue(SearchStatus || FilterSearchStatus, "Fail, Certificate Filter with OR combination " + FisrtFilter
				+ SecondFilter + " With Data " + FilterWith + " " + SFilterWith);
	}

	public boolean equals(String SearchWith, WebElement element) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String text = getText(element);
		return text.equals(SearchWith);
	}

	public boolean notequals(String SearchWith, WebElement element) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String text = getText(element);
		return !text.equals(SearchWith);
	}

	public boolean startswith(String SearchWith, WebElement element) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String text = getText(element);
		return text.startsWith(SearchWith);
	}

	public boolean endswith(String SearchWith, WebElement element) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String text = getText(element);
		return text.endsWith(SearchWith);
	}

	public boolean notcontains(String SearchWith, WebElement element) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String text = getText(element);
		return !text.contains(SearchWith);
	}

	public boolean greaterthan(String SearchWith, WebElement element) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String text = getText(element);
		Date date1 = new SimpleDateFormat("mm/dd/yyyy").parse(text);
		Date date2 = new SimpleDateFormat("mm/dd/yyyy").parse(SearchWith);
		return date1.after(date2);
	}

	public boolean lessthan(String SearchWith, WebElement element) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String text = getText(element);
		Date date1 = new SimpleDateFormat("mm/dd/yyyy").parse(text);
		Date date2 = new SimpleDateFormat("mm/dd/yyyy").parse(SearchWith);
		return date1.before(date2);
	}

	public boolean inRange(String FromDate, String ToDate, WebElement element) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String text = getText(element);
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		Date fromdate = sdf.parse(FromDate);
		Date todate = sdf.parse(ToDate);
		Date date1 = new SimpleDateFormat("mm/dd/yyyy").parse(text);
		return date1.compareTo(fromdate) >= 0 && date1.compareTo(todate) <= 0;
	}

	//Sorting functionality

	public ECMCustomers Verify_column_Sorting(String CoulmNameToSort, String ColumnName) {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			switch (CoulmNameToSort.toUpperCase()) {
			case "EXEMP. ID":
				sorting(ecmstp.get_list_of_certid_elements(), ColumnName);
				break;
			case "CUSTOMER NAME":
				sorting(ecmstp.get_list_of_customers_elements(), ColumnName);
				break;
			case "START DATE":
				sorting(ecmstp.get_list_of_created_elements(), ColumnName);
				break;
			case "END DATE":
				sorting(ecmstp.get_list_of_scope_elements(), ColumnName);
				break;
			case "TAXPAYER":
				sorting(ecmstp.get_list_of_reason_elements(), ColumnName);
				break;
			case "EXEMPTIONS":
				sorting(ecmstp.get_list_of_Jurisdictions_elements(), ColumnName);
				break;
			}
		} catch (Exception e) {
			new RuntimeException("Verify_column_Sorting Method: " + e);
		}
		return this;
	}

	public ECMCustomers sorting(List<WebElement> elements, String ColumnName) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		Thread.sleep(6000);
		List<String> list = new ArrayList<String>();
		for (WebElement element : elements) {
			list.add(element.getText());
		}
		Thread.sleep(3000);
		Collections.sort(list);
		ecmstp.click_column(ColumnName);
		Thread.sleep(15000);
		List<String> list1 = new ArrayList<String>();
		for (WebElement element : elements) {
			list1.add(element.getText());
		}
		Assert.assertTrue(list.equals(list1), "column is sorted successfully");
		return this;
	}

	//Table Settings

	public void Table_Settings_Tests(String TestsName, String[] Columns) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		switch (TestsName) {
		case "SelectAll":
			select_all_table_settings();
			break;
		case "Default Settings":
			default_table_settings();
			break;
		case "Select Specific Columns":
			select_table_settings(Columns);
			break;
		case "ClearAll":
			clear_all_table_settings();
			break;
		}
	}

	public ECMCustomers select_all_table_settings() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		try {
			Thread.sleep(2000);
			ecmstp.click_table_settings();
			//click_selectAll().
			driver.findElement(By.xpath("//button[contains(.,'Select All')]")).click();
			ecmstp.click_apply_button();
			String[] coulmnname = { "Customer Name", "Start Date", "End Date", "TaxPayer", "Exemptions",
					"Custom Field 1", "Custom Field 2", "Custom Field 3", "Custom Field 4", "Custom Field 5",
					"Custom Field 6", "Custom Field 7", "Custom Field 8", "Custom Field 9", "Custom Field 10" };
			int col = coulmnname.length-1;
			while(col>=0) {
				switch(coulmnname[col]) {
				case "Custom Field 10":
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 2000",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 10']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Custom Field 9":
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 1500",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 9']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Custom Field 8":
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 500",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 8']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Custom Field 7":
					Thread.sleep(1000);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 7']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Custom Field 6":
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 50",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 6']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Custom Field 5":
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 400",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 5']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Custom Field 4":
					Thread.sleep(1000);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 4']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Custom Field 3":
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 200",
							ecmportal.HORIZONTALSCROLLBAR);
					Thread.sleep(1000);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 3']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Custom Field 2":
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 300",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 2']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Custom Field 1":
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 100",
							ecmportal.HORIZONTALSCROLLBAR);
					Thread.sleep(1000);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 1']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Exemptions":
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 250",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='Exemptions']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				/*case "TaxPayer":
					Thread.sleep(1000);
					assertTrue(driver.findElement(By.xpath("//span[contains(.,'Taxpayer')]")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;*/
				case "End Date":
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 250",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='End Date']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Start Date":
					Thread.sleep(1000);
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft -= 500",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='Start Date']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					break;
				case "Customer Name":
					Thread.sleep(1000);
					assertTrue(driver.findElement(By.xpath("//span[text()='Customer Name']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
					System.out.println("Customer name verified");
					break;
				}
				col--;
			}
			System.out.println("PASS");
		} catch (Exception e) {
			new RuntimeException("select_all_table_settings Method: " + e);
		}
		return this;
	}

	public ECMCustomers clear_all_table_settings() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			ecmstp.click_table_settings();
			driver.findElement(By.xpath("//button[contains(.,'Clear All')]")).click();
			//click_clearAll().
			ecmstp.click_apply_button();
			String[] coulmnname = { "Customer Name", "Start Date", "End Date", "TaxPayer", "Exemptions",
					"Custom Field 1", "Custom Field 2", "Custom Field 3", "Custom Field 4", "Custom Field 5",
					"Custom Field 6", "Custom Field 7", "Custom Field 8", "Custom Field 9", "Custom Field 10"  };
			int col = coulmnname.length;
			if(col>0) {
				assertTrue(!driver.findElement(By.xpath("//span[text()='Customer Name']")).isDisplayed(),"Fail, Custom Field 10 columns is not displaying after selecting Custom Field 10 Table settings");
			}
		} catch (Exception e) {
			new RuntimeException("clear_all_table_settings Method: " + e);
		}
		return this;
	}

	public void select_table_settings(String[] columns) {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		try {
			ecmstp.click_table_settings();
			System.out.println(columns.length);
			for (int i = 0; i < columns.length; i++) {
				ecmstp.check_column(columns[i]);
			}
			ecmstp.click_apply_button();
			Thread.sleep(10000);
			for(int i=0;i < columns.length; i++) {
				switch(columns[i]) {
				case "Custom Field 1":
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 1']")).isDisplayed(),"Fail, Custom Field 1 columns is not displaying after selecting Custom Field 1 Table settings");
					break;
				case "Custom Field 2":
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500",
							ecmportal.HORIZONTALSCROLLBAR);
					assertTrue(driver.findElement(By.xpath("//span[text()='Custom Field 2']")).isDisplayed(),"Fail, Custom Field 2 columns is not displaying after selecting Custom Field 2 Table settings");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void default_table_settings() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			ecmstp.click_table_settings();
			String[] coulmnname = { "Customer Code", "customerName", "effectiveDate", "endDate", "exemptions" };
			int col = coulmnname.length-1;
			while (col > 0) {
				assertTrue(ecmstp.isSelected(coulmnname[col]), "Fail, Table Default Settings");
				col--;
			}
			Thread.sleep(10000);
			driver.findElement(By.xpath("//button[contains(.,'CANCEL')]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Pagination
	public ECMCustomers Pagination(String NumberOfPages) {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			String[] str1 = ecmstp.get_records_count().split(" ");
			String str2 = str1[4];
			double str3 = Double.parseDouble(str2);
			double d = Double.parseDouble(NumberOfPages);
			double str4 = (str3 / d);
			String str5 = new BigDecimal(str4).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
			System.out.println(ecmstp.get_last_page());
			assertTrue(ecmstp.get_last_page().equals(str5), "Number Pages not displaying as per Number of Records");
		} catch (Exception e) {
			new RuntimeException("Pagination Method: " + e);
		}
		return this;
	}

	//More Filter

	public void Morefilter_AGgrid(String TestsName, String ColumnName, String SelectFilterOption, String FilterWith, String Condition, String SelectANDORFilterOption, String ANDORTextFilter, String FromDate, String ToDate) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		Thread.sleep(7000);
		ecmstp.select_cert_status_drop_down();
		switch (TestsName) {
		case "VerifyCoulmnFilters":
			click_more_filter(ColumnName,SelectFilterOption,FilterWith,Condition,SelectANDORFilterOption,ANDORTextFilter,FromDate,ToDate);
			break;
		}
		driver.navigate().refresh();
	}

	public ECMCustomers click_more_filter(String ColumnName, String SelectFilterOption, String FilterWith,
			String Condition, String SelectANDORFilterOption, String ANDORFILTERTxt, String FromDate, String ToDate)
					throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		ecmstp.click_table_settings();
		driver.findElement(By.xpath("//button[contains(.,'Clear All')]")).click();
		//ecmstp.click_clearAll();
		ecmstp.click_apply_button();
		driver.findElement(By.xpath("//button[@id='btn-more-filters']")).click();
		Thread.sleep(2000);
		WebElement columnvalue = driver.findElement(By.xpath("//span[contains(.,'" + ColumnName + "')]/../div/div"));
		click(columnvalue);
		driver.findElement(By.xpath("//span[contains(.,'" + ColumnName + "')]/../div/div/descendant::input"))
		.sendKeys("" + SelectFilterOption + "");
		sendkeysTab(
				driver.findElement(By.xpath("//span[contains(.,'" + ColumnName + "')]/../div/div/descendant::input")));
		WebElement searchbox = driver
				.findElement(By.xpath("//span[contains(.,'" + ColumnName + "')]/../../div/div/input"));
		waitforElement(30, searchbox, driver);
		click(searchbox);
		type(searchbox, FilterWith);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(.,'" + ColumnName + "')]/../../div/div[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(.,'" + ColumnName + "')]/../../div[2]/div[1]/descendant::input"))
		.sendKeys("" + Condition + "");
		Thread.sleep(1000);
		sendkeysTab(driver.findElement(
				By.xpath("//span[contains(.,'" + ColumnName + "')]/../../div[2]/div[1]/descendant::input")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(.,'" + ColumnName + "')]/../../div[2]/div[2]/descendant::input"))
		.sendKeys("" + SelectANDORFilterOption + "");
		Thread.sleep(1000);
		sendkeysTab(driver.findElement(
				By.xpath("//span[contains(.,'" + ColumnName + "')]/../../div[2]/div[2]/descendant::input")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(.,'" + ColumnName + "')]/../../div[2]/div/input"))
		.sendKeys("" + ANDORFILTERTxt + "");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(.,'APPLY')]")).click();
		/*Thread.sleep(1000);
		ecmstp.click_table_settings();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[contains(.,'" + ColumnName + "')]")).click();
		Thread.sleep(1000);
		ecmstp.click_apply_button();*/
		Thread.sleep(1000);
		grid_Verifications_based_on_Cloumn(ColumnName, SelectFilterOption, FilterWith, Condition,
				SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='vtx-icon-button']")).click();
		Thread.sleep(2000);
		ecmstp.click_table_settings();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(.,'Select All')]")).click();
		//ecmstp.click_selectAll();
		Thread.sleep(1000);
		ecmstp.click_apply_button();
		return this;
	}
	
	public ECMCustomers ECMUserlogin(String Username, String Password) {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("" + Username + "");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("" + Password + "");
		driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
		return null;
	}
}
