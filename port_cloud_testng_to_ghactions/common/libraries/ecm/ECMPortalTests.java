package common.libraries.ecm;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import common.libraries.generic.CommonFunctions;
import common.libraries.miscellaneous.Features;
import common.pages.ECMBulkUpload;
import common.pages.ECMPortalPages;
import common.utilities.CloudData;

public class ECMPortalTests extends CommonFunctions {

	private WebDriver driver;

	public ECMPortalTests(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	public static String CustomerCode;
	public static String v3CertificateResponceuuid;

	Features features = PageFactory.initElements(driver, Features.class);

	ECMSteps ecmstp = PageFactory.initElements(driver, ECMSteps.class);

	public boolean verificationstatus(String Verification) throws Exception {
		if (Verification.isEmpty()) {
			return true;
		}
		return false;
	}

	public void select_table_settings(String[] columns) {
		try {
			ecmstp.click_table_settings();
			for (int i = 0; i < columns.length; i++) {
				ecmstp.check_column(columns[i]);
			}
			ecmstp.click_apply_button();
			Thread.sleep(10000);
		} catch (Exception e) {
		}
	}

	public void default_table_settings() {
		try {
			ecmstp.click_table_settings();
			String[] coulmnname = { "Cert. ID", "Created", "Customer", "Jurisdiction(s)", "Reason", "Scope" };
			int col = coulmnname.length;
			while (col < 0) {
				assertTrue(ecmstp.isSelected(coulmnname[col]), "Fail, Table Default Settings");
				col--;
			}
			Thread.sleep(10000);
		} catch (Exception e) {
		}
	}

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

	public void AND(boolean SearchStatus, boolean FilterSearchStatus) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		assertTrue((SearchStatus && FilterSearchStatus), "Fail, Certificate Filter Functionality;");
	}

	public void OR(boolean SearchStatus, boolean FilterSearchStatus) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		assertTrue((SearchStatus || FilterSearchStatus), "Fail, Certificate Filter Functionality;");
	}

	public String AccessToken() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			String serviceresponse;
			String Payload = null;
			String RESTFilePath = xDownloadFiles("RESTAPI");
			Payload = "client_id=" + "" + ECWClientID + "" + "&client_secret=" + "" + ECWClientSecret + ""
					+ "&grant_type=client_credentials&scope=ecw-connector-api vtms-internal-api";
			client = Client.create();
			webResource = client.resource("https://qaauth.vertexsmb.com/identity/connect/token");
			response = webResource.accept("application/x-www-form-urlencoded").type("application/x-www-form-urlencoded")
					.post(ClientResponse.class, Payload);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "Bulk_Data_Load.json");
			BearerToken = xReadJSON(RESTFilePath + "\\Bulk_Data_Load.json", "access_token", "");
			BearerToken = "Bearer" + " " + BearerToken;
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public String create_customers_api() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			CustomerCode = "New_customer21";
			String customers = "{\r\n" + "    \"newCustomer\" : {\r\n"
					+ "       \"customerCode\": \"New_customer21\",\r\n" + "        \"parentCustomerCode\": \"\",\r\n"
					+ "        \"flexField1\": \"Sold To Number\",\r\n" + "        \"flexField2\": \"test\",\r\n"
					+ "        \"flexField3\": \"Av Customer ID\",\r\n" + "        \"flexField4\": \"DEP ORG ID\",\r\n"
					+ "        \"effDate\": 20200205,\r\n"
					+ "        \"customerName\": \"Vertex Cloud Development\",\r\n" + "        \"sellers\": [{\r\n"
					+ "					\"sellerCode\": \"Vertex123\"\r\n" + "				}\r\n" + "            \r\n"
					+ "        ]\r\n" + "    }\r\n" + "}";

			client = Client.create();
			webResource = client
					.resource("https://qaecmservices.vertexsmb.com/ecm-service/connector/v2/customer/hierarchy");
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, customers);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue((response.getStatus() == 200),
						"Fail, create_customers_api returns status Code " + response.getStatus() + "");
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public String get_customers_api() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String serviceresponse;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			client = Client.create();
			MultivaluedMap<String, String> params = new MultivaluedMapImpl();
			params.add("customerCode", CustomerCode);
			webResource = client.resource("https://qaecmservices.vertexsmb.com/ecm-service/connector");
			response = webResource.path("/v2/customer").queryParams(params)
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue((response.getStatus() == 200),
						"Fail, get_customer API returns status Code " + response.getStatus() + "");
			}
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "Bulk_Data_Load.json");
			String CustomerCode = xReadJSON(RESTFilePath + "\\Bulk_Data_Load.json", "customerCode", "");
			assertTrue((CustomerCode.equalsIgnoreCase(CustomerCode)),
					"Fail, get_customer API ereturns wrong CustomerCode");
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public String get_customers_api_flexfield() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String serviceresponse;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			client = Client.create();
			MultivaluedMap<String, String> params = new MultivaluedMapImpl();
			params.add("flexField1", "Sold To Number");
			webResource = client.resource("https://qaecmservices.vertexsmb.com/ecm-service/connector");
			response = webResource.path("/v2/customer").queryParams(params)
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue((response.getStatus() == 200),
						"Fail, get_customer API returns status Code " + response.getStatus() + "");
			}
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "Bulk_Data_Load.json");
			String flexfield1 = xReadJSON(RESTFilePath + "\\Bulk_Data_Load.json", "customerCode", "");
			assertTrue((flexfield1.equalsIgnoreCase("Sold To Number")),
					"Fail, get_customer API ereturns wrong flexField1");
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public void create_customer_with_parent_api() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			CustomerCode = "New_customer21";
			String customerswithparent = "{\r\n" + "    \"customerParentSearchCriteria\": {\r\n"
					+ "        \"customerParentType\": \"hierarchical_customer_root\",\r\n"
					+ "        \"customer\": {\r\n" + "            \"customerCode\": \"New_customer21\"\r\n"
					+ "        }\r\n" + "    },\r\n" + "    \"newCustomer\": {\r\n"
					+ "        \"customerCode\": \"CustomerCodeTest\",\r\n"
					+ "        \"parentCustomerCode\": \" \",\r\n" + "        \"flexField1\": \"Sold To Number\",\r\n"
					+ "        \"flexField2\": \"test\",\r\n" + "        \"flexField3\": \"Av Customer ID\",\r\n"
					+ "        \"flexField4\": \"DEP ORG ID\",\r\n" + "        \"effDate\": 20200205,\r\n"
					+ "        \"customerName\": \"Vertex Cloud Development\",\r\n" + "        \"sellers\": [\r\n"
					+ "            {\r\n" + "                \"sellerCode\": \"Vertex123\"\r\n" + "            }\r\n"
					+ "        ],\r\n" + "        \"contacts\": [\r\n" + "            {\r\n"
					+ "                \"firstName\": \"Testing_first\",\r\n"
					+ "                \"lastName\": \"Testing_first\",\r\n"
					+ "                \"customerStreetAddress\": \"Testing_first\",\r\n"
					+ "                \"customerCity\": \"Testing_first\",\r\n"
					+ "                \"customerPostalCode\": \"Testing_first\",\r\n"
					+ "                \"customerCountry\": \"Testing_first\",\r\n"
					+ "                \"customerEmail\": \"Testing_first\"\r\n" + "            }\r\n" + "        ]\r\n"
					+ "    }\r\n" + "}";

			client = Client.create();
			webResource = client
					.resource("https://qaecmservices.vertexsmb.com/ecm-service/connector/v2/customer/hierarchy");
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, customerswithparent);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue((response.getStatus() == 200),
						"Fail, create_customer_with_parent_api returns status Code " + response.getStatus() + "");
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			return;
		}
	}

	public void verify_column_sorting(List<WebElement> elements, String ColumnName) throws Exception {
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
	}

	public ECMPortalTests ColumnDragANDDrop(String DragColumn, String DropColumn) throws Exception {
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
					"Fail, \"+DragColumn+\"Column not Moved to Column \"+DropColumn+\" ;");
		} catch (Exception e) {
			new RuntimeException("ColumnDragANDDrop Method: " + e);
		}
		return this;
	}

	public ECMPortalTests verify_Column_SearchBox(String ColumnName, String SearchBoxValue) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			switch (ColumnName) {
			case "Exemp. ID":
				SearchBoxValue = ecmstp.get_certificateid_text();
				ecmstp.searchtextbox(ColumnName, SearchBoxValue);
				break;
			case "Created":
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

	public ECMPortalTests morefiltersearchtextbox(String ColumnName, String SelectFilterOption, String SearchBoxValue,
			String ANDORFILTERTxt) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		ecmstp.click_table_settings();
		ecmstp.click_clearAll();
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
		type(searchbox, SearchBoxValue);
		driver.findElement(By.xpath("//button[contains(.,'APPLY')]")).click();
		Thread.sleep(1000);
		grid_Verifications_based_on_Cloumn(ColumnName, SearchBoxValue);
		ecmstp.click_table_settings();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[contains(.,'" + ColumnName + "')]")).click();
		Thread.sleep(1000);
		ecmstp.click_apply_button();
		return this;
	}

	public ECMPortalTests verify_Coulmn_Filters(String ColumnName, String SelectFilterOption, String FilterWith,
			String Condition, String SelectANDORFilterOption, String ANDORFILTERTxt, String FromDate, String ToDate)
			throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			switch (ColumnName) {
			case "Exemp. ID":
				FilterWith = ecmstp.get_certificateid_text();
				if (!Condition.isEmpty()) {
					ANDORFILTERTxt = ecmstp.get_seccertificateid_text();
				}
				break;
			}
			ecmstp.click_column_filter(ColumnName).select_filter_options(SelectFilterOption)
					.type_filter_text(FilterWith).click_and_or(Condition)
					.select_andOR_filter_options(SelectANDORFilterOption)
					.type_andOR_filter_text(ANDORFILTERTxt, ColumnName);
			Thread.sleep(5000);
			grid_Verifications_based_on_Cloumn(ColumnName, SelectFilterOption, FilterWith, Condition,
					SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
		} catch (Exception e) {
			new RuntimeException("verify_Coulmn_Filters Method: " + e);
		}
		return this;
	}

	public ECMPortalTests click_more_filter(String ColumnName, String SelectFilterOption, String FilterWith,
			String Condition, String SelectANDORFilterOption, String ANDORFILTERTxt, String FromDate, String ToDate)
			throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		ecmstp.click_table_settings();
		ecmstp.click_clearAll();
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
		Thread.sleep(1000);
		ecmstp.click_table_settings();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[contains(.,'" + ColumnName + "')]")).click();
		Thread.sleep(1000);
		ecmstp.click_apply_button();
		Thread.sleep(1000);
		grid_Verifications_based_on_Cloumn(ColumnName, SelectFilterOption, FilterWith, Condition,
				SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
		Thread.sleep(1000);
		ecmstp.click_table_settings();
		Thread.sleep(1000);
		ecmstp.click_selectAll();
		Thread.sleep(1000);
		ecmstp.click_apply_button();
		return this;
	}

	public ECMPortalTests verify_Coulmns_Data_ECM(String customername, String jurisdiction, String reason, String scope,
			String startdate, String customernumber, String Sellercode) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(4000);
			ecmstp.searchtextbox("Customer", customername);
			assertTrue(ecmstp.get_seccertificecustomer_text().equalsIgnoreCase(customername));
			assertTrue(ecmstp.get_seccertificatejurisdiction_text().equalsIgnoreCase(jurisdiction));
			assertTrue(ecmstp.get_seccertificatereason_text().equalsIgnoreCase(reason));
			assertTrue(ecmstp.get_seccertificatescope_text().equalsIgnoreCase(scope));
			assertTrue(ecmstp.get_seccertificatestartedate_text().equalsIgnoreCase(startdate));
			assertTrue(ecmstp.get_seccertificatecustomernumber_text().equalsIgnoreCase(customernumber));
			assertTrue(ecmstp.get_seccertificateseller_text().equalsIgnoreCase(Sellercode));
		} catch (Exception e) {
			new RuntimeException("verify_Coulmns_Data_ECM Method: " + e);
		}
		return this;
	}

	public ECMPortalTests ECMCertificateView(String customername, String Customernamecode, String ECMCertificateType,
			String ECMJurisdictions, String ECMReason, String ECMScope, String Date, String ECMExpirationdate,
			String ECMIssuedate, String ECMEnddate, String ECMReviewdate, String ECMSellernamecode, String ECMSource,
			String ECMForm, String ECMProducts, String ECMNotes, String ECMCustomfiled1) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(4000);
			ecmstp.searchtextbox("Customer", customername);
			ecmstp.get_certificateid_click();
			Thread.sleep(2000);
			assertTrue(ecmstp.ECMCustomername().equalsIgnoreCase(Customernamecode));
			assertTrue(ecmstp.ECMCerttype().equalsIgnoreCase(ECMCertificateType));
			assertTrue(ecmstp.ECMJurisdictions().equalsIgnoreCase(ECMJurisdictions));
			assertTrue(ecmstp.ECMReason().equalsIgnoreCase(ECMReason));
			assertTrue(ecmstp.ECMScope().equalsIgnoreCase(ECMScope));
			assertTrue(ecmstp.ECMStartdate().equalsIgnoreCase(Date));
			assertTrue(ecmstp.ECMIssuedate().equalsIgnoreCase(ECMIssuedate));
			assertTrue(ecmstp.ECMEnddate().equalsIgnoreCase(ECMEnddate));
			assertTrue(ecmstp.ECMReviewdate().equalsIgnoreCase(ECMReviewdate));
			assertTrue(ecmstp.ECMSellernamecode().equalsIgnoreCase(ECMSellernamecode));
			assertTrue(ecmstp.ECMSource().equalsIgnoreCase(ECMSource));
			assertTrue(ecmstp.ECMForm().equalsIgnoreCase(ECMForm));
			assertTrue(ecmstp.ECMProducts().equalsIgnoreCase(ECMProducts));
			assertTrue(ecmstp.ECMNotes().equalsIgnoreCase(ECMNotes));
			assertTrue(ecmstp.ECMCustomfiled1().equalsIgnoreCase(ECMCustomfiled1));

		} catch (Exception e) {
			new RuntimeException("ECMCertificateView Method: " + e);
		}
		return this;
	}

	public ECMPortalTests API_ECMCertificateViewInformation(String Customernamecode, String ECMCertificateType,
			String ECMJurisdictions, String ECMReason, String ECMScope, String StartDate, String ECMExpirationdate,
			String ECMIssuedate, String ECMEnddate, String ECMReviewdate, String ECMSellernamecode, String ECMSource,
			String ECMForm, String ECMProducts, String ECMNotes, String ECMCustomfiled1, String ECMCustomfiled2,
			String ECMCustomfiled3, String ECMCustomfiled4, String ECMCustomfiled5, String ECMCustomfiled6,
			String ECMCustomfiled7, String ECMCustomfiled8, String ECMCustomfiled9, String ECMCustomfiled10)
			throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		ecmstp.get_certificateid_click();
		Thread.sleep(5000);
		assertTrue(ecmstp.ECMCustomername().equalsIgnoreCase(Customernamecode));
		assertTrue(ecmstp.ECMCerttype().equalsIgnoreCase(ECMCertificateType));
		assertTrue(ecmstp.ECMJurisdictions().equalsIgnoreCase(ECMJurisdictions));
		assertTrue(ecmstp.ECMReason().equalsIgnoreCase(ECMReason));
		assertTrue(ecmstp.ECMScope().equalsIgnoreCase(ECMScope));
		assertTrue(ecmstp.ECMStartdate().equalsIgnoreCase(StartDate));
		assertTrue(ecmstp.ECMExpirationdate().equalsIgnoreCase(ECMExpirationdate));
		assertTrue(ecmstp.ECMIssuedate().equalsIgnoreCase(ECMIssuedate));
		assertTrue(ecmstp.ECMEnddate().equalsIgnoreCase(ECMEnddate));
		assertTrue(ecmstp.ECMSellernamecode().equalsIgnoreCase(ECMSellernamecode));
		assertTrue(ecmstp.ECMProducts().equalsIgnoreCase(ECMProducts));
		assertTrue(ecmstp.ECMNotes().equalsIgnoreCase(ECMNotes));
		assertTrue(ecmstp.ECMCustomfiled1().equalsIgnoreCase(ECMCustomfiled1));
		assertTrue(ecmstp.ECMCustomfiled2().equalsIgnoreCase(ECMCustomfiled2));
		assertTrue(ecmstp.ECMCustomfiled3().equalsIgnoreCase(ECMCustomfiled3));
		assertTrue(ecmstp.ECMCustomfiled4().equalsIgnoreCase(ECMCustomfiled4));
		assertTrue(ecmstp.ECMCustomfiled5().equalsIgnoreCase(ECMCustomfiled5));
		assertTrue(ecmstp.ECMCustomfiled6().equalsIgnoreCase(ECMCustomfiled6));
		assertTrue(ecmstp.ECMCustomfiled7().equalsIgnoreCase(ECMCustomfiled7));
		assertTrue(ecmstp.ECMCustomfiled8().equalsIgnoreCase(ECMCustomfiled8));
		assertTrue(ecmstp.ECMCustomfiled9().equalsIgnoreCase(ECMCustomfiled9));
		assertTrue(ecmstp.ECMCustomfiled10().equalsIgnoreCase(ECMCustomfiled10));
		// assertTrue(ECMReviewdate().equalsIgnoreCase(ECMReviewdate));
		// assertTrue(ECMSource1().equalsIgnoreCase(ECMSource));
		// assertTrue(ECMForm().equalsIgnoreCase(ECMForm));
		System.out.println("side by side certificateinfo verified in ui:- succcess");
		return this;
	}

	public ECMPortalTests ECMCertcontactInformation(String customername, String contactname, String ECMContactaddress,
			String ECMContactcity, String ECMContactState, String ECMContactPostalzip, String ECMCOntactCountry,
			String ECMContactphone, String ECMContactEmail) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(4000);
			ecmstp.searchtextbox("Customer", customername);
			ecmstp.get_certificateid_click();
			Thread.sleep(2000);
			ecmstp.ECMContact_info_click();
			Thread.sleep(2000);
			assertTrue(ecmstp.ECMContactfirstfirstlastname().equalsIgnoreCase(contactname));
			assertTrue(ecmstp.ECMContactaddress().equalsIgnoreCase(ECMContactaddress));
			assertTrue(ecmstp.ECMContactcity().equalsIgnoreCase(ECMContactcity));
			assertTrue(ecmstp.ECMContactState().equalsIgnoreCase(ECMContactState));
			assertTrue(ecmstp.ECMContactPostalzip().equalsIgnoreCase(ECMContactPostalzip));
			assertTrue(ecmstp.ECMCOntactCountry().equalsIgnoreCase(ECMCOntactCountry));
			assertTrue(ecmstp.ECMContactphone().equalsIgnoreCase(ECMContactphone));
			assertTrue(ecmstp.ECMContactEmail().equalsIgnoreCase(ECMContactEmail));
		} catch (Exception e) {
			new RuntimeException("ECMCertcontactInformation Method: " + e);
		}
		return this;
	}

	public ECMPortalTests API_ECMCertcontactInformation(String contactname, String ECMContactaddress,
			String ECMContactaddress2, String ECMContactcity, String ECMContactState, String ECMContactPostalzip,
			String ECMCOntactCountry, String ECMContactphone, String ECMContactEmail) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		ecmstp.ECMContact_info_click();
		Thread.sleep(2000);
		assertTrue(ecmstp.ECMContactfirstfirstlastname().equalsIgnoreCase(contactname));
		assertTrue(ecmstp.ECMContactaddress().equalsIgnoreCase(ECMContactaddress));
		assertTrue(ecmstp.ECMContactcity().equalsIgnoreCase(ECMContactcity));
		assertTrue(ecmstp.ECMContactState().equalsIgnoreCase(ECMContactState));
		assertTrue(ecmstp.ECMContactPostalzip().equalsIgnoreCase(ECMContactPostalzip));
		assertTrue(ecmstp.ECMCOntactCountry().equalsIgnoreCase(ECMCOntactCountry));
		assertTrue(ecmstp.ECMContactphone().equalsIgnoreCase(ECMContactphone));
		assertTrue(ecmstp.ECMContactEmail().equalsIgnoreCase(ECMContactEmail));
		System.out.println("side by side Certcontactinfo verified in ui:- succcess");
		return this;
	}

	public ECMPortalTests ECM_Certificate_Reject(String ColumnName, String customername, String reasonindex,
			String Additiobalinfo, String RejectCancel) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(4000);
			ecmstp.searchtextbox("Customer", customername);
			Thread.sleep(3000);
			String Certid = ecmstp.get_certificateid_text();
			System.out.println("Rejected Certificate ID: " + Certid);
			Thread.sleep(5000);
			ecmstp.click_Ellipse();
			Thread.sleep(5000);
			ecmstp.click_ECMCertRejectlink();
			Thread.sleep(5000);
			WebElement element = driver.findElement(By.xpath("//div[@class='crViewerButtons']/button"));
			assertTrue(!element.isEnabled());
			System.out.println("Verifying Reject button is disabled");
			driver.findElement(By.xpath("//button[contains(.,'CANCEL')]")).isEnabled();
			System.out.println("Verifying Cancel buton is enabled");
			Thread.sleep(5000);
			if (reasonindex == "All") {
				driver.findElement(By.xpath("//input[@id='1']/../label/span")).click();
				driver.findElement(By.xpath("//input[@id='2']/../label/span")).click();
				driver.findElement(By.xpath("//input[@id='3']/../label/span")).click();
				driver.findElement(By.xpath("//input[@id='4']/../label/span")).click();
				driver.findElement(By.xpath("//input[@id='5']/../label/span")).click();
			} else {
				driver.findElement(By.xpath("//input[@id='" + reasonindex + "']/../label/span")).click();
			}
			Thread.sleep(5000);
			driver.findElement(By.xpath("//textarea[@id='additionalInfo']")).sendKeys("" + Additiobalinfo + "");
			assertTrue(driver.findElement(By.xpath("//input[@id='sendEmail']")).isSelected());
			if (RejectCancel == "Yes") {
				driver.findElement(By.xpath("//button[contains(.,'CANCEL')]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath(
						"//div[@class='sbsHeaderLineInformationBase sbsHeaderLineInformationStatus sbsStatusPending']"))
						.isDisplayed();
				Thread.sleep(2000);
				ecmstp.click_closeicon();
			} else {
				ecmstp.click_ECMCertRejectbutton();
				Thread.sleep(5000);
				ecmstp.select_cert_rejectstatus_drop_down();
				Thread.sleep(5000);
				ecmstp.searchtextbox(ColumnName, Certid);
				Thread.sleep(5000);
				ecmstp.click_Ellipse();
				Thread.sleep(5000);
				assertTrue(driver.findElement(By.xpath("//button[contains(.,'View')]")).isDisplayed());
				WebElement element1 = driver.findElement(By.xpath("//button[@data-id='approve']"));
				assertTrue(!element1.isEnabled());
				Thread.sleep(2000);
				WebElement element2 = driver.findElement(By.xpath("//button[@data-id='reject']"));
				assertTrue(!element2.isEnabled());
				Thread.sleep(5000);
				System.out.println("Certifcate Rejected successfully");
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[@row-index='0']/div[1]/div/span/span[2]")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//span[contains(.,'Show Dropdown')]/..")).click();
				Thread.sleep(5000);
				WebElement element3 = driver.findElement(By.xpath("//button[contains(.,'Approve Selected')]"));
				assertTrue(!element3.isEnabled());

			}
		} catch (Exception e) {
			new RuntimeException("ECM_Certificate_Reject Method: " + e);
		}
		return this;
	}

	public ECMPortalTests ECM_Certificate_Approve(String ColumnName, String customername) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(4000);
			ecmstp.searchtextbox("Customer", customername);
			Thread.sleep(3000);
			String Certid = ecmstp.get_certificateid_text();
			System.out.println("Approved Certificate ID: " + Certid);
			Thread.sleep(5000);
			ecmstp.click_Ellipse();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[@data-id='approve']")).click();
			Thread.sleep(5000);
			ecmstp.select_cert_approvestatus_drop_down();
			Thread.sleep(5000);
			ecmstp.searchtextbox(ColumnName, Certid);
			Thread.sleep(2000);
			ecmstp.click_Ellipse();
			Thread.sleep(5000);
			assertTrue(driver.findElement(By.xpath("//button[contains(.,'View')]")).isDisplayed());
			WebElement element = driver.findElement(By.xpath("//button[@data-id='approve']"));
			assertTrue(!element.isEnabled());
			Thread.sleep(5000);
			WebElement element1 = driver.findElement(By.xpath("//button[@data-id='reject']"));
			assertTrue(!element1.isEnabled());
			Thread.sleep(5000);
			System.out.println("Certificate Approved successfully");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[@row-index='0']/div[1]/div/span/span[2]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//span[contains(.,'Show Dropdown')]/..")).click();
			Thread.sleep(5000);
			WebElement element3 = driver.findElement(By.xpath("//button[contains(.,'Approve Selected')]"));
			assertTrue(!element3.isEnabled());
		} catch (Exception e) {
			new RuntimeException("ECM_Certificate_Approve Method: " + e);
		}
		return this;
	}

	public ECMPortalTests ECM_Certificate_Reject_sidebyside(String ColumnName, String customername, String reasonindex,
			String Additiobalinfo, String RejectCancel) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(4000);
			ecmstp.searchtextbox("Customer", customername);
			String Certid = ecmstp.get_certificateid_text();
			System.out.println(Certid);
			ecmstp.get_certificateid_click();
			Thread.sleep(3000);
			ecmstp.click_ECMCertRejectbutton();
			Thread.sleep(2000);
			WebElement element = driver.findElement(By.xpath("//div[@class='crViewerButtons']/button"));
			assertTrue(!element.isEnabled());
			driver.findElement(By.xpath("//button[contains(.,'CANCEL')]")).isEnabled();
			if (reasonindex == "All") {
				driver.findElement(By.xpath("//input[@id='1']/../label/span")).click();
				driver.findElement(By.xpath("//input[@id='2']/../label/span")).click();
				driver.findElement(By.xpath("//input[@id='3']/../label/span")).click();
				driver.findElement(By.xpath("//input[@id='4']/../label/span")).click();
				driver.findElement(By.xpath("//input[@id='5']/../label/span")).click();
			} else {
				driver.findElement(By.xpath("//input[@id='" + reasonindex + "']/../label/span")).click();
			}
			driver.findElement(By.xpath("//textarea[@id='additionalInfo']")).sendKeys("" + Additiobalinfo + "");
			Thread.sleep(2000);
			assertTrue(driver.findElement(By.xpath("//input[@id='sendEmail']")).isSelected());
			if (RejectCancel == "Yes") {
				driver.findElement(By.xpath("//button[contains(.,'CANCEL')]")).click();
				Thread.sleep(2000);
				ecmstp.click_closeicon();
				Thread.sleep(2000);
				ecmstp.searchtextbox(ColumnName, Certid);
				Thread.sleep(2000);
				assertTrue(ecmstp.get_certificateid_text().equals(Certid));
			} else {
				Thread.sleep(2000);
				ecmstp.click_ECMCertRejectbuttonpopup();
				/*
				 * Thread.sleep(2000); String str=get_certificatestatus_text();
				 * assertTrue(str.equals("REJECTED")); Thread.sleep(2000); click_closeicon();
				 */
				Thread.sleep(2000);
				ecmstp.select_cert_rejectstatus_drop_down();
				Thread.sleep(2000);
				ecmstp.searchtextbox(ColumnName, Certid);
				Thread.sleep(2000);
				ecmstp.click_Ellipse();
				Thread.sleep(2000);
				assertTrue(driver.findElement(By.xpath("//button[contains(.,'View')]")).isDisplayed());
				WebElement element1 = driver.findElement(By.xpath("//button[@data-id='approve']"));
				assertTrue(!element1.isEnabled());
				WebElement element2 = driver.findElement(By.xpath("//button[@data-id='reject']"));
				assertTrue(!element2.isEnabled());
				ecmstp.get_certificateid_click();
				String str1 = ecmstp.get_certificatestatus_text();
				assertTrue(str1.equals("REJECTED"));
				WebElement element3 = driver.findElement(By.xpath("//button[contains(.,'REJECT')]"));
				assertTrue(!element3.isDisplayed());
				WebElement element4 = driver.findElement(By.xpath("//button[contains(.,'APPROVE')]"));
				assertTrue(!element4.isDisplayed());
			}

		} catch (Exception e) {
			new RuntimeException("ECM_Certificate_Reject_sidebyside Method: " + e);
		}
		return this;
	}

	public ECMPortalTests ECM_Certificate_Approve_sidebyside(String ColumnName, String customername) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(4000);
			ecmstp.searchtextbox("Customer", customername);
			String Certid = ecmstp.get_certificateid_text();
			System.out.println(Certid);
			ecmstp.get_certificateid_click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(.,'APPROVE')]")).click();
			/*
			 * Thread.sleep(2000); String str=get_certificatearrovestatus_text();
			 * assertTrue(str.equals("APPROVED")); Thread.sleep(2000); click_closeicon();
			 */
			Thread.sleep(2000);
			ecmstp.select_cert_approvestatus_drop_down();
			Thread.sleep(2000);
			ecmstp.searchtextbox(ColumnName, Certid);
			Thread.sleep(2000);
			ecmstp.click_Ellipse();
			Thread.sleep(2000);
			assertTrue(driver.findElement(By.xpath("//button[contains(.,'View')]")).isDisplayed());
			WebElement element = driver.findElement(By.xpath("//button[@data-id='approve']"));
			assertTrue(!element.isEnabled());
			Thread.sleep(2000);
			WebElement element1 = driver.findElement(By.xpath("//button[@data-id='reject']"));
			assertTrue(!element1.isEnabled());
			Thread.sleep(2000);
			ecmstp.get_certificateid_click();
			String str1 = ecmstp.get_certificatearrovestatus_text();
			Thread.sleep(3000);
			assertTrue(str1.equals("APPROVED"));
			/*
			 * Thread.sleep(2000); WebElement
			 * element2=driver.findElement(By.xpath("//button[contains(.,'REJECT')]"));
			 * assertTrue(!element2.isDisplayed()); Thread.sleep(2000); WebElement
			 * element3=driver.findElement(By.xpath("//button[contains(.,'APPROVE')]"));
			 * assertTrue(!element3.isDisplayed());
			 */
			Thread.sleep(2000);
			ecmstp.click_closeicon();

		} catch (Exception e) {
			new RuntimeException("ECM_Certificate_Approve_sidebyside Method: " + e);
		}
		return this;
	}

	public ECMPortalTests ECM_Certificate_BulkApprove(String ColumnName) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(4000);
			driver.findElement(By.xpath("//input[@id='react-select-2-input']")).sendKeys("100");
			sendkeysTab(driver.findElement(By.xpath("//input[@id='react-select-2-input']")));
			Thread.sleep(2000);
			String Certid = ecmstp.get_certificateid_text();
			System.out.println(Certid);
			Thread.sleep(2000);
			String[] str1 = ecmstp.get_records_count().split(" ");
			String str2 = str1[2];
			System.out.println(str2);
			int str3 = Integer.parseInt(str2);
			int j = 0;
			for (int i = 0; i <= str3; i++) {
				if (j == 15) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += 250",
							driver.findElement(By.xpath("//div[@ref='eBodyViewport']")));
					j = 0;
				}
				j++;
				driver.findElement(By.xpath("//div[@row-index='" + i + "']/div[1]/div/span/span[2]")).click();
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[contains(.,'Show Dropdown')]/..")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(.,'Approve Selected')]")).click();
			Thread.sleep(2000);
			ecmstp.select_cert_approvestatus_drop_down();
			Thread.sleep(2000);
			ecmstp.searchtextbox(ColumnName, Certid);
			Thread.sleep(2000);
			ecmstp.click_Ellipse();
			Thread.sleep(2000);
			assertTrue(driver.findElement(By.xpath("//button[contains(.,'View')]")).isDisplayed());
			WebElement element = driver.findElement(By.xpath("//button[@data-id='approve']"));
			assertTrue(!element.isEnabled());
			WebElement element1 = driver.findElement(By.xpath("//button[@data-id='reject']"));
			assertTrue(!element1.isEnabled());

		} catch (Exception e) {
			new RuntimeException("ECM_Certificate_Reject_sidebyside Method: " + e);
		}
		return this;
	}

	public ECMPortalTests ECM_Certificate_singleApprove(String ColumnName, String customername) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(4000);
			ecmstp.searchtextbox("Customer", customername);
			String Certid = ecmstp.get_certificateid_text();
			System.out.println(Certid);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@row-index='0']/div[1]/div/span/span[2]")).click();
			driver.findElement(By.xpath("//span[contains(.,'Show Dropdown')]/..")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[contains(.,'Approve Selected')]")).click();
			Thread.sleep(2000);
			ecmstp.select_cert_approvestatus_drop_down();
			Thread.sleep(2000);
			ecmstp.searchtextbox(ColumnName, Certid);
			Thread.sleep(2000);
			ecmstp.click_Ellipse();
			Thread.sleep(2000);
			assertTrue(driver.findElement(By.xpath("//button[contains(.,'View')]")).isDisplayed());
			WebElement element = driver.findElement(By.xpath("//button[@data-id='approve']"));
			assertTrue(!element.isEnabled());
			WebElement element1 = driver.findElement(By.xpath("//button[@data-id='reject']"));
			assertTrue(!element1.isEnabled());
		} catch (Exception e) {
			new RuntimeException("ECM_Certificate_Reject_sidebyside Method: " + e);
		}
		return this;
	}

	public ECMPortalTests Verify_ECMAdditionalDocumentation(String customername, String DocumentNames)
			throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
			Thread.sleep(4000);
			ecmstp.searchtextbox("Customer", customername);
			Thread.sleep(3000);
			if (ecmportal.VERIFYPAPERCLIP.isDisplayed()) {
				ecmstp.get_certificateid_click();
			} else {
				assertTrue(false, "Paper clip is not displayed");
			}
			ecmstp.get_ECMadditionalDocumentation_click();
			Thread.sleep(1000);
			String[] docs = DocumentNames.split(",");
			if (docs.length == ecmportal.ADDITITONALLINKSLIST.size()) {
				for (int i = 0; i < ecmportal.ADDITITONALLINKSLIST.size(); i++) {
					driver.findElement(By.xpath("//p[contains(.,'" + docs[i] + "')]")).click();
					Thread.sleep(12000);
					driver.findElement(By.xpath("//div[@class='sbsAdditionalDocumentHeading']//button//img")).click();
				}
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[@class='sbsHeaderLineClose']//button//*[local-name()='svg']"))
						.click();
			} else {
				Assert.assertTrue(false, "Total Number of Documnets Not matched with posted");
			}
		} catch (Exception e) {
			new RuntimeException("verify_ECMAdditionalDocumentation Method: " + e);
		}
		return this;
	}

	public void grid_Verifications_based_on_Cloumn(String ColumnName, String SelectFilterOption, String FilterWith,
			String Condition, String SelectANDORFilterOption, String ANDORFILTERTxt, String FromDate, String ToDate)
			throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		switch (ColumnName.toUpperCase()) {
		case "EXEMP. ID":
			verify_filter_gridResults(ecmstp.get_list_of_certid_elements(), SelectFilterOption, FilterWith, Condition,
					SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "CUSTOMER":
			verify_filter_gridResults(ecmstp.get_list_of_customers_elements(), SelectFilterOption, FilterWith,
					Condition, SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "CREATED":
			verify_filter_gridResults(ecmstp.get_list_of_created_elements(), SelectFilterOption, FilterWith, Condition,
					SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "SCOPE":
			verify_filter_gridResults(ecmstp.get_list_of_scope_elements(), SelectFilterOption, FilterWith, Condition,
					SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "REASON":
			verify_filter_gridResults(ecmstp.get_list_of_reason_elements(), SelectFilterOption, FilterWith, Condition,
					SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "JURISDICTION(S)":
			verify_filter_gridResults(ecmstp.get_list_of_Jurisdictions_elements(), SelectFilterOption, FilterWith,
					Condition, SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "START DATE":
			verify_filter_gridResults(ecmstp.get_list_of_startdate_elements(), SelectFilterOption, FilterWith,
					Condition, SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "CUSTOMER NUMBER":
			verify_filter_gridResults(ecmstp.get_list_of_customernumber_elements(), SelectFilterOption, FilterWith,
					Condition, SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		case "SELLER":
			verify_filter_gridResults(ecmstp.get_list_of_seller_elements(), SelectFilterOption, FilterWith, Condition,
					SelectANDORFilterOption, ANDORFILTERTxt, FromDate, ToDate);
			break;
		}
	}

	public void grid_Verifications_based_on_Cloumn(String ColumnName, String Serachwith) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		switch (ColumnName.toUpperCase()) {
		case "EXEMP. ID":
			verify_search_gridResults(ecmstp.get_list_of_certid_elements(), Serachwith);
			break;
		case "CUSTOMER":
			verify_search_gridResults(ecmstp.get_list_of_customers_elements(), Serachwith);
			break;
		case "CREATED":
			verify_search_gridResults(ecmstp.get_list_of_created_elements(), Serachwith);
			break;
		case "SCOPE":
			verify_search_gridResults(ecmstp.get_list_of_scope_elements(), Serachwith);
			break;
		case "REASON":
			verify_search_gridResults(ecmstp.get_list_of_reason_elements(), Serachwith);
			break;
		case "JURISDICTION(S)":
			verify_search_gridResults(ecmstp.get_list_of_Jurisdictions_elements(), Serachwith);
			break;
		case "START DATE":
			verify_search_gridResults(ecmstp.get_list_of_startdate_elements(), Serachwith);
			break;
		case "CUSTOMER NUMBER":
			verify_search_gridResults(ecmstp.get_list_of_customernumber_elements(), Serachwith);
			break;
		case "SELLER":
			verify_search_gridResults(ecmstp.get_list_of_seller_elements(), Serachwith);
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

	public void verify_filter_gridResults(List<WebElement> elements, String SelectFilterOption, String SearchWith,
			String Condition, String SelectANDORFilterOption, String ANDORFILTERTxt, String FromDate, String ToDate)
			throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		boolean SelectFilterOptioN = false, SelectANDORFilterOptioN = false;
		for (WebElement element : elements) {
			switch (SelectFilterOption) {
			case "Contains":
				SelectFilterOptioN = contains(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Equals":
				SelectFilterOptioN = equals(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Not contains":
				SelectFilterOptioN = notcontains(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Not Contains":
				SelectFilterOptioN = notcontains(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Not equal":
				SelectFilterOptioN = notequals(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Not Equal":
				SelectFilterOptioN = notequals(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Starts with":
				SelectFilterOptioN = startswith(SearchWith, element);
				assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
						+ " or wrong data is displying in grid :" + SearchWith);
				break;
			case "Starts With":
				SelectFilterOptioN = startswith(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Ends with":
				SelectFilterOptioN = endswith(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Ends With":
				SelectFilterOptioN = endswith(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Greater than":
				SelectFilterOptioN = greaterthan(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Greater Than":
				SelectFilterOptioN = greaterthan(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "Less than":
				SelectFilterOptioN = lessthan(SearchWith, element);
				assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
						+ " or wrong data is displying in grid :" + SearchWith);
				break;
			case "Less Than":
				SelectFilterOptioN = lessthan(SearchWith, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "In range":
				SelectFilterOptioN = inRange(FromDate, ToDate, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
				break;
			case "In Range":
				SelectFilterOptioN = inRange(FromDate, ToDate, element);
				if (Condition.equalsIgnoreCase("OR") || Condition.equalsIgnoreCase("AND")) {

				} else {
					assertTrue(SelectFilterOptioN, "Failed to Filter with +" + SelectFilterOption
							+ " or wrong data is displying in grid :" + SearchWith);
				}
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
				OR(SelectFilterOptioN, SelectANDORFilterOptioN, SelectFilterOption, SelectANDORFilterOption, SearchWith,
						ANDORFILTERTxt);
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

	public boolean contains(String SearchWith, WebElement element) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String text = getText(element);
		return text.contains(SearchWith);
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

	public ECMPortalTests Verify_ECM_Logout() throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			assertTrue(ecmstp.verify_logout_icon(), "Fail,Logout Icon Not displayed;");
			ecmstp.click_logout();
			/*
			 * assertTrue(gettitle(driver).equalsIgnoreCase("Login | Vertex Cloud") &&
			 * verify_login_page(),
			 * "Fail,LoginPage Title and User Login Text Not displayed;");
			 * VertexCloud.RowNo--;
			 */
		} catch (Exception e) {
			new RuntimeException("Verify_ECM_Help Method: " + e);
		}
		return this;
	}

	public ECMPortalTests Verify_ECM_Help() throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			assertTrue(ecmstp.verify_help_icon(), "Fail,HELP Icon Not displayed;");
			ecmstp.click_help_icon().verify_exemption_help();
			/*
			 * Verification = (verify_exemption_help()) == true? Verification: Verification
			 * + "Fail,Not Navigated to Exemption Certificate Wizard Portal Help Title;";
			 * status = status + Verification; VertexSMB.RowNo =
			 * bussFunction.xResult(Verification,
			 * "Verify_Exemption Certificate Wizard Portal Help_Page", "", "",NoOfVerify++);
			 */
			ecmstp.close_child_switch_to_main_window();
		} catch (Exception e) {
			new RuntimeException("Verify_ECM_Help Method: " + e);
		}
		return this;
	}

	public ECMPortalTests verify_user_info(String UserFirstName) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(15000);
			assertTrue(ecmstp.get_user_info().equalsIgnoreCase("Welcome, " + UserFirstName + "!"),
					"Fail, FirstName Not displaying");
			assertTrue(ecmstp.verify_copy_right(), "Fail, CopyRight Text is Not displaying");
			assertTrue(gettitle(driver).equalsIgnoreCase("Exemption Certificate Manager"),
					"Fail, Page Title displaying as wrong");
		} catch (Exception e) {
			new RuntimeException("verify_user_info Method: " + e);
		}
		return this;
	}

	public ECMPortalTests get_user_info_api(String Environment) {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			AccessToken(Environment);
			client = Client.create();
			webResource = client.resource("https://" + Environment.toLowerCase()
					+ "ecmservices.vertexsmb.com/ecm-service/portal/v2/userInfo");
			response = webResource.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			assertTrue(response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400,
					"Failed : HTTP error code : " + response.getStatus() + "");
		} catch (Exception e) {
			new RuntimeException("get_user_info_api Method: " + e);
		}
		return this;
	}

	public ECMPortalTests select_all_table_settings() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			Thread.sleep(2000);
			ecmstp.click_table_settings().click_selectAll().click_apply_button();
			String[] coulmnname = { "Cert. ID", "Created", "Customer", "Jurisdiction(s)", "Reason", "Scope",
					"Custom Field 1", "Custom Field 2", "Custom Field 3", "Custom Field 4", "Custom Field 5",
					"Custom Field 6", "Custom Field 7", "Custom Field 8", "Custom Field 9" };
			int col = coulmnname.length;
			while (col < 0) {
				assertTrue(VerifyObject(driver, "//span[text()='" + coulmnname[col] + "']"),
						"Fail, Columns Name are not displaying after selecting All Table settings");
				col--;
			}
		} catch (Exception e) {
			new RuntimeException("select_all_table_settings Method: " + e);
		}
		return this;
	}

	public ECMPortalTests clear_all_table_settings() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			ecmstp.click_table_settings().click_clearAll().click_apply_button();
			String[] coulmnname = { "Cert. ID", "Created", "Customer", "Jurisdiction(s)", "Reason", "Scope",
					"Custom Field 1", "Custom Field 2", "Custom Field 3", "Custom Field 4", "Custom Field 5",
					"Custom Field 6", "Custom Field 7", "Custom Field 8", "Custom Field 9" };
			int col = coulmnname.length;
			while (col < 0) {
				if (coulmnname[col].equalsIgnoreCase("Cert. ID")) {
					assertTrue(VerifyObject(driver, "//span[text()='" + coulmnname[col] + "']"),
							"Fail, Cert. ID Columns Name are not displaying after clearing All Table settings");
				} else {
					assertTrue(VerifyObject(driver, "//span[text()='" + coulmnname[col] + "']"),
							"Fail, Columns Name are displaying after clearing All Table settings");
				}
				col--;
			}
		} catch (Exception e) {
			new RuntimeException("clear_all_table_settings Method: " + e);
		}
		return this;
	}

	public ECMPortalTests select_table_settings(String columns) {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		String[] col = columns.split(",");
		try {
			ecmstp.click_table_settings();
			for (int i = 0; i < col.length; i++) {
				ecmstp.check_column(col[i]);
			}
			ecmstp.click_apply_button();
			Thread.sleep(10000);
		} catch (Exception e) {
			new RuntimeException("select_table_settings Method: " + e);
		}
		return this;
	}

	public ECMPortalTests verify_default_table_settings() {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			ecmstp.click_table_settings();
			String[] coulmnname = { "Cert. ID", "Created", "Customer", "Jurisdiction(s)", "Reason", "Scope" };
			int col = coulmnname.length;
			while (col < 0) {
				assertTrue(ecmstp.isSelected(coulmnname[col]), "Fail, Table Default Settings");
				col--;
			}
			Thread.sleep(10000);
		} catch (Exception e) {
			new RuntimeException("verify_default_table_settings Method: " + e);
		}
		return this;
	}

	public ECMPortalTests Verify_column_Sorting(String CoulmNameToSort) {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			switch (CoulmNameToSort.toUpperCase()) {
			case "CERT. ID":
				sorting(ecmstp.get_list_of_certid_elements(), CloudData.get_data("ColumnName"));
				break;
			case "CUSTOMER":
				sorting(ecmstp.get_list_of_customers_elements(), CloudData.get_data("ColumnName"));
				break;
			case "CREATED":
				sorting(ecmstp.get_list_of_created_elements(), CloudData.get_data("ColumnName"));
				break;
			case "SCOPE":
				sorting(ecmstp.get_list_of_scope_elements(), CloudData.get_data("ColumnName"));
				break;
			case "REASON":
				sorting(ecmstp.get_list_of_reason_elements(), CloudData.get_data("ColumnName"));
				break;
			case "JURISDICTION(S)":
				sorting(ecmstp.get_list_of_Jurisdictions_elements(), CloudData.get_data("ColumnName"));
				break;
			case "START DATE":
				sorting(ecmstp.get_list_of_startdate_elements(), CloudData.get_data("ColumnName"));
				break;
			case "CUSTOMER NUMBER":
				sorting(ecmstp.get_list_of_customernumber_elements(), CloudData.get_data("ColumnName"));
				break;
			case "SELLER":
				sorting(ecmstp.get_list_of_seller_elements(), CloudData.get_data("ColumnName"));
				break;
			}
		} catch (Exception e) {
			new RuntimeException("Verify_column_Sorting Method: " + e);
		}
		return this;
	}

	public ECMPortalTests sorting(List<WebElement> elements, String ColumnName) throws Exception {
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

	public ECMPortalTests Pagination(String NumberOfPages) {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			String[] str1 = ecmstp.get_records_count().split(" ");
			String str2 = str1[4];
			double str3 = Double.parseDouble(str2);
			for (int i = CloudData.start_row; i <= CloudData.end_row; i++) {
				CloudData.set_data_row(i);
				double d = Double.parseDouble(NumberOfPages);
				double str4 = (str3 / d);
				String str5 = new BigDecimal(str4).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
				assertTrue(ecmstp.get_last_page().equals(str5), "Number Pages not displaying as per Number of Records");
			}
		} catch (Exception e) {
			new RuntimeException("Pagination Method: " + e);
		}
		return this;
	}

	public String AccessToken(String env) {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		try {
			String serviceresponse;
			String Payload = null;
			String RESTFilePath = xDownloadFiles("RESTAPI");
			Payload = "client_id=" + "" + ECWClientID + "" + "&client_secret=" + "" + ECWClientSecret + ""
					+ "&grant_type=client_credentials&scope=ecw-connector-api vtms-internal-api";
			client = Client.create();
			webResource = client.resource("https://" + env.toLowerCase() + "auth.vertexsmb.com/identity/connect/token");
			response = webResource.accept("application/x-www-form-urlencoded").type("application/x-www-form-urlencoded")
					.post(ClientResponse.class, Payload);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				throw new RuntimeException("Failed : HTTP error code AccessToken : " + response.getStatus());
			}
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "Bulk_Data_Load.json");
			BearerToken = xReadJSON(RESTFilePath + "\\Bulk_Data_Load.json", "access_token", "");
			BearerToken = "Bearer" + " " + BearerToken;
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public ECMPortalTests Verify_BulkUploadLandingPage() throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		waitforElement(180, ecmBulkUpload.BulkUploadButton, driver);
		assertTrue(ecmBulkUpload.BulkUploadButton.isDisplayed());
		ecmstp.click_bulkupload_button();
		waitforElement(180, ecmBulkUpload.downloadCsvTemplate, driver);
		assertTrue(ecmBulkUpload.downloadCsvTemplate.isDisplayed());
		ecmstp.click_downloadcsvtemplate_link();
		return this;
	}

	public ECMPortalTests uploadcsvfiles(String Filename) throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		waitforElement(180, ecmBulkUpload.UploadCsvFile, driver);
		assertTrue(ecmBulkUpload.UploadCsvFile.isDisplayed());
		Thread.sleep(2000);
		ecmstp.click_uploadcsvfiles();
		Thread.sleep(2000);
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String additional1[] = Filename.split("\\.");
		transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, additional1[0], "." + additional1[1]);
		Thread.sleep(3000);
		waitforElement(180, driver.findElement(By.xpath(
				"//div[@class='buContent']/div[contains(.,'Tabular Data File (.CSV)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div/span")),
				driver);
		String result = driver.findElement(By.xpath("//div[@class='buContentFileName']/span")).getText();
		String actual = result.substring(0, result.indexOf(" "));
		System.out.println(Filename);
		System.out.println(actual);
		if (Filename.contains(actual)) {
			assertTrue(true, "uploading CSV file");
		} else {
			assertTrue(false, "uploading csv file fail");
		}
		String alerttext = driver.findElement(By.xpath(
				"//div[@class='buContent']/div[contains(.,'Tabular Data File (.CSV)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title]"))
				.getAttribute("title");
		System.out.println(alerttext);
		System.out.println(alerttext.contains("Maximum of 5,000 certifications are allowed."));
		System.out.println(alerttext.contains("The file is the incorrect type. Allowed types: CSV, CSV"));
		System.out.println(alerttext.contains("The maximum number of files are reached. Only 1 files are allowed."));
		System.out.println(alerttext.contains("The file is too big. Only files smaller than 30 MB are allowed."));
		System.out.println(alerttext.contains("Success"));
		WebElement findfile = driver.findElement(By.xpath(
				"//div[@class='buContent']/div[contains(.,'Tabular Data File (.CSV)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title='"
						+ alerttext + "']/parent::div//parent::div//div[@class='buContentFileDelete']"));
		if (alerttext.contains("Maximum of 5,000 certifications are allowed.")) {
			assertTrue(findfile.isDisplayed(), "Maximum 5000 Certificates");
			click(findfile);
		}
		if (alerttext.contains("The file is the incorrect type. Allowed type: CSV")) {
			assertTrue(findfile.isDisplayed(), "Uploading in CSV With Different FileTypes");
			click(findfile);
		}
		if (alerttext.contains("The maximum number of files are reached. Only 1 file is allowed.")) {
			assertTrue(findfile.isDisplayed(), "More Than 1 CSV File is Uploaded");
			click(findfile);
		}
		if (alerttext.contains("The file is too big. Only files smaller than 30 MB are allowed.")) {
			assertTrue(findfile.isDisplayed(), "File That Contains more than 30mb");
			click(findfile);
		}
		if (alerttext.contains("Success")) {
			assertTrue(findfile.isDisplayed(), "csv file is success");
		}
		System.out.println(alerttext);
		return this;
	}

	public ECMPortalTests uploadzipfiles(String Filename) throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		waitforElement(180, ecmBulkUpload.UploadZipFiles, driver);
		ecmstp.click_uploadzipfiles();
		Thread.sleep(2000);
		String AutoITpath = xAutoIT();
		String AdjustmentFilePath = xUploadAdjustmentFile("ExceptionFiles");
		String additional1[] = Filename.split("\\.");
		transactions.Browser(driver, Browser, AutoITpath, AdjustmentFilePath, additional1[0], "." + additional1[1]);
		Thread.sleep(3000);
		waitforElement(180, driver.findElement(By.xpath("//div[@class='buContentFileName']/span")), driver);
		String result = driver.findElement(By.xpath(
				"//div[@class='buContent']/div[contains(.,'Certificate Images Folder (.ZIP)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div/span"))
				.getText();
		String actual = result.substring(0, result.indexOf(" "));
		System.out.println(Filename);
		System.out.println(actual);
		if (Filename.contains(actual)) {
			Assert.assertTrue(true, "uploading ZIP file's pass");
		} else {
			assertTrue(false, "uploading ZIP file fail");
		}
		String alerttext = driver.findElement(By.xpath(
				"//div[@class='buContent']/div[contains(.,'Certificate Images Folder (.ZIP)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title]"))
				.getAttribute("title");
		System.out.println(alerttext.contains("Success"));
		System.out.println(alerttext.contains("The file is too big. Only files smaller than 30 MB are allowed."));
		System.out.println(
				alerttext.contains("The file is the incorrect type. Allowed types: ZIP, PDF, PNG, GIF, JPG, WEBP"));
		WebElement findfile = driver.findElement(By.xpath(
				"//div[@class='buContent']/div[contains(.,'Certificate Images Folder (.ZIP)')]/../descendant::input/../div[1]/../div[@class='buContentFiles']//div[@class='buContentFile']//div[@class='buContentFileStatus']//div[@title='"
						+ alerttext + "']/parent::div//parent::div//div[@class='buContentFileDelete']"));
		if (alerttext.contains("Success")) {
			assertTrue(findfile.isDisplayed(), "ZIP file is success");
		}
		if (alerttext.contains("The file is too big. Only files smaller than 30 MB are allowed.")) {
			assertTrue(findfile.isDisplayed(), "File That Contains more than 30mb");
			click(findfile);
		}
		if (alerttext.contains("The file is the incorrect type. Allowed types: ZIP, PDF, PNG, GIF, JPG, WEBP")) {
			assertTrue(findfile.isDisplayed(), "Uploading in ZIP With Different FileTypes");
			click(findfile);
		}
		return this;
	}

	public ECMPortalTests verifybuttons() throws Exception {
		ECMBulkUpload ecmBulkUpload = PageFactory.initElements(driver, ECMBulkUpload.class);
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		assertTrue(ecmBulkUpload.SubmitButton.isDisplayed(), "Submit Button");
		ecmstp.click_submit_button();
		Thread.sleep(2000);
		if (ecmBulkUpload.UploadCsvFileTittle.getText().equalsIgnoreCase("Tabular Data File (.CSV)")) {
			Assert.assertTrue(true, "Submit Button is not working");
		}
		assertTrue(ecmBulkUpload.ClearAllButton.isDisplayed(), "Clear All Button");
		ecmstp.click_clearall_button();
		Thread.sleep(2000);
		if (ecmBulkUpload.UploadCsvFile.getText()
				.equalsIgnoreCase("Drag the .CSV file here, or click to select file.")) {
			Assert.assertTrue(true, "Clear Button is  clicked");
		}
		assertTrue(ecmBulkUpload.BulkUploadHistoryButton.isDisplayed(), "BulkUpload History Button");
		ecmstp.click_bulkuploadhistory_button();
		assertTrue(ecmBulkUpload.BackToCertificates.isDisplayed(), "BackToCertificates Link");
		ecmstp.click_backtocertificates_link();
		return this;
	}

	public ECMPortalTests api_verify_Coulmns_Data_ECM(String certid, String certpriority, String customername,
			String jurisdiction, String reason, String scope, String certsource, String startdate,
			String certexpirationdate, String certenddate, String customernumber, String certowner, String sellercode,
			String certtype, String certcustomerfield1, String certcustomerfield2, String certcustomerfield3,
			String certcustomerfield4, String certcustomerfield5, String certcustomerfield6, String certcustomerfield7,
			String certcustomerfield8, String certcustomerfield9, String certcustomerfield10) throws Exception {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		ecmstp.searchtextbox("Cert. ID", certid);
		ECMPortalPages ecmportal = PageFactory.initElements(driver, ECMPortalPages.class);
		Thread.sleep(5000);
		assertTrue(ecmportal.VERIFYPAPERCLIP.isDisplayed(), "Paper clip is not displayed");
		assertTrue(ecmstp.get_certificateid_text().equalsIgnoreCase(certid), "ui page certificateid not matched");
		// assertTrue(get_seccertificatepriority_text().equalsIgnoreCase(certpriority),"ui
		// page certificatepriority not matched");
		assertTrue(ecmstp.get_seccertificecustomer_text().equalsIgnoreCase(customername),
				"ui page certificatecustomername not matched");
		assertTrue(ecmstp.get_seccertificatejurisdiction_text().equalsIgnoreCase(jurisdiction),
				"ui page certificatejurisdiction not matched");
		assertTrue(ecmstp.get_seccertificatereason_text().equalsIgnoreCase(reason),
				"ui page certificatereason not matched");
		assertTrue(ecmstp.get_seccertificatescope_text().equalsIgnoreCase(scope),
				"ui page certificatescope not matched");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500", ecmportal.HORIZONTALSCROLLBAR);
		// assertTrue(get_seccertificatesource_text().equalsIgnoreCase(certsource),"ui
		// page certificatesource not matched");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500", ecmportal.HORIZONTALSCROLLBAR);
		String pagestartdate = conversiondates("m/dd/yyyy", "yyyy-mm-dd", ecmstp.get_seccertificatestartedate_text());
		assertTrue(pagestartdate.equalsIgnoreCase(startdate), "ui page certificatestartdate not matched");
		// String pageexpirationdate = conversiondates("m/dd/yyyy", "yyyy-mm-dd",
		// get_seccertificateexpirationdate_text());
		// assertTrue(pageexpirationdate.equalsIgnoreCase(certexpirationdate),"ui page
		// certificateexpirationdate not matched");
		// String pageenddate = conversiondates("m/dd/yyyy", "yyyy-mm-dd",
		// get_seccertificateenddate_text());
		// assertTrue(pageenddate.equalsIgnoreCase(certenddate),"ui page
		// certificateenddate not matched");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500", ecmportal.HORIZONTALSCROLLBAR);
		assertTrue(ecmstp.get_seccertificatecustomernumber_text().equalsIgnoreCase(customernumber),
				"ui page certificatecustomernumber not matched");
		// assertTrue(get_seccertificateowner_text().equalsIgnoreCase(certowner),"ui
		// page certificateowner not matched");
		assertTrue(ecmstp.get_seccertificateseller_text().equalsIgnoreCase(sellercode),
				"ui page certificateSellercode not matched");
		assertTrue(ecmstp.get_seccertificatetype_text().equalsIgnoreCase(certtype),
				"ui page certificatetype not matched");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500", ecmportal.HORIZONTALSCROLLBAR);
		// assertTrue(get_seccertificatecustomerfield1_text().equalsIgnoreCase(certcustomerfield1),"ui
		// page certificatecustomerfield1 not matched");
		// assertTrue(get_seccertificatecustomerfield2_text().equalsIgnoreCase(certcustomerfield2),"ui
		// page certificatecustomerfield2 not matched");
		// assertTrue(get_seccertificatecustomerfield3_text().equalsIgnoreCase(certcustomerfield3),"ui
		// page certificatecustomerfield3 not matched");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500", ecmportal.HORIZONTALSCROLLBAR);
		// assertTrue(get_seccertificatecustomerfield4_text().equalsIgnoreCase(certcustomerfield4),"ui
		// page certificatecustomerfield4 not matched");
		// assertTrue(get_seccertificatecustomerfield5_text().equalsIgnoreCase(certcustomerfield5),"ui
		// page certificatecustomerfield5 not matched");
		// assertTrue(get_seccertificatecustomerfield6_text().equalsIgnoreCase(certcustomerfield6),"ui
		// page certificatecustomerfield6 not matched");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500", ecmportal.HORIZONTALSCROLLBAR);
		// assertTrue(get_seccertificatecustomerfield7_text().equalsIgnoreCase(certcustomerfield7),"ui
		// page certificatecustomerfield7 not matched");
		// assertTrue(get_seccertificatecustomerfield8_text().equalsIgnoreCase(certcustomerfield8),"ui
		// page certificatecustomerfield8 not matched");
		// assertTrue(get_seccertificatecustomerfield9_text().equalsIgnoreCase(certcustomerfield9),"ui
		// page certificatecustomerfield9 not matched");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollLeft += 500", ecmportal.HORIZONTALSCROLLBAR);
		// assertTrue(get_seccertificatecustomerfield10_text().equalsIgnoreCase(certcustomerfield10),"ui
		// page certificatecustomerfield10 not matched");
		System.out.println("verified in ui succcess");
		return this;
	}

	public static String conversiondates(String inputdate, String outputdate, String givendate) throws Exception {
		SimpleDateFormat inputFormat = new SimpleDateFormat(inputdate);
		SimpleDateFormat outpuDateFormat = new SimpleDateFormat(outputdate);
		Date date = null;
		String requireddate = null;
		date = inputFormat.parse(givendate);
		requireddate = outpuDateFormat.format(date);
		return requireddate;
	}

	public ECMPortalTests ECMUserlogin(String Username, String Password) {
		ecmstp = PageFactory.initElements(driver, ECMSteps.class);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("" + Username + "");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("" + Password + "");
		driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
		return null;
	}

}
