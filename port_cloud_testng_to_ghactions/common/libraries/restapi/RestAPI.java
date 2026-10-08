
package common.libraries.restapi;

import static org.testng.Assert.assertNotNull;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import common.libraries.admin.AdminFunctions;
import common.libraries.generic.CommonFunctions;
import common.pages.ConfigurePage;
import common.utilities.CloudData;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;

public class RestAPI extends CommonFunctions {

	private WebDriver driver;
	@SuppressWarnings("unused")
	private static String token;

	public RestAPI(WebDriver driver) throws Exception {
		this.driver = driver;
	}

	AdminFunctions aa = PageFactory.initElements(driver, AdminFunctions.class);

	public void RESTconfigureCompany(String Company_Code, String Company_Name, String Fereral_ID, String Start_Date,
			String Address1, String City, String State, String Zip) throws Exception {
		TaxpayerID = null;
		String RESTFilePath = xDownloadFiles("RESTAPI");
		BearerTokenPageSource();
		String restUrl = url + "/config/api/v1/";
		client = Client.create();
		webResource = client.resource(restUrl + "companies");
		String configState = "";
		String fID, sAddress, city, state, zip, company, serviceresponse;
		String[] ccData, cnData;
		ccData = Company_Code.split(";");
		TaxpayerCode = xLabelRND(ccData);
		cnData = Company_Name.split(";");
		TaxpayerCompanyName = xLabelRND(cnData);
		fID = Fereral_ID;
		sDate = Start_Date;
		sAddress = Address1;
		city = City;
		state = State;
		configState = Restconvertstateshort(state);
		zip = Zip;
		company = "{\"name\": \"" + TaxpayerCompanyName + "\",\"companyCode\": \"" + TaxpayerCode
				+ "\",\"federalId\": \"" + fID + "\",\"startDate\": \"" + sDate
				+ "\",\"IsFilingEntity\": \"true\",\"contact\": {\"streetAddress\": \"" + sAddress + "\",\"city\": \""
				+ city + "\",\"state\": \"" + configState + "\",\"zip\": \"" + zip + "\",},\"discountCategories\": []}";
		System.out.println(company);
		response = webResource.accept("application/json").type("application/json")
				.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, company);
		if (response.getStatus() == 401) {
			BearerTokenPageSource();
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, company);
		}
		if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
				|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
			assertTrue(false);
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		serviceresponse = response.getEntity(String.class);
		writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
		// File directory = new File(".//"+"DownloadingFiles");
		TaxpayerID = xReadJSON(RESTFilePath + "\\TaxpayerId.json", "id", "");

	}

	public void RESTreturns(String TaxpayerName, String Jurisdiction1, String Reg_Number, String Filing_Method,
			String Filing_Frequency, String ReturnsID, String FYearStart, String JurisdictionID, String StartDate,
			String State, String Business_No, String Three_Position, String CNSubID, String Quebec_No)
			throws Exception {

		String ReturnRL = url + "/config/api/v1/";
		client = Client.create();
		BearerTokenPageSource();
		String serviceresponse;
		String RESTFilePath = xDownloadFiles("RESTAPI");
		String jurisid = null, StDate = null;
		String fmethod = null, ffrequency = null, returns = null, cnreturns = null, returnid = null,
				finyearstart = null, rname = null, rnumber = null, fillingmethod = null, filingfrequency = null;
		String CurState = null;
		String businessno, quebecno, thirdpos;
		webResource = client.resource(ReturnRL + "companies");
		System.out.println(BearerToken);
		response = webResource.type("application/json").header(HttpHeaders.AUTHORIZATION, BearerToken)
				.get(ClientResponse.class);
		if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
				|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		serviceresponse = response.getEntity(String.class);
		writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");

		TaxpayerID = xReadTaxpayerID(RESTFilePath + "\\TaxpayerId.json", "id", "name", TaxpayerName);

		rname = Jurisdiction1;
		rnumber = Reg_Number;
		fillingmethod = Filing_Method;
		switch (fillingmethod) {
		case "Mail a paper return":
			fmethod = "1";
			break;
		case "EDI return":
			fmethod = "2";
			break;
		case "Web File":
			fmethod = "3";
			break;
		}
		filingfrequency = Filing_Frequency;
		switch (filingfrequency) {
		case "Monthly":
			ffrequency = "1";
			break;
		case "Quarterly":
			ffrequency = "4";
			break;
		case "Semi-annually":
			ffrequency = "8";
			break;
		case "Annually":
			ffrequency = "16";
			break;
		case "First Two Months of Quarter":
			ffrequency = "32";
			break;
		}
		returnid = ReturnsID;
		finyearstart = FYearStart;
		jurisid = JurisdictionID;
		StDate = StartDate;

		CurState = State;

		if (!(CurState.equalsIgnoreCase("CN") || CurState.equalsIgnoreCase("CANADA"))) {
			webResource = client.resource(url + "/config/api/v1/companies" + "/" + TaxpayerID + "/maindivisions/");
			returns = "{\"CompanyId\": \"" + TaxpayerID + "\",\"JurisdictionId\": \"" + jurisid + "\",\"start\": \""
					+ StDate + "\"}";
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, returns);
		} else {
			webResource = client.resource(url + "/config/api/v1/companies" + "/" + TaxpayerID + "/countries/");
			returns = "{\"companyId\": \"" + TaxpayerID + "\",\"jurisdictionId\": \"" + jurisid + "\"}";
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, returns);
			if (CNSubID.equalsIgnoreCase("Yes")) {
				int CStates[] = { 43423, 43604, 43786, 43911, 44067, 44130, 44152, 45667, 44315, 44879, 44906, 45454,
						45656 };
				for (int jID = 0; jID <= CStates.length - 1; jID++) {
					webResource = null;
					cnreturns = null;
					response = null;
					webResource = client
							.resource(url + "/config/api/v1/companies" + "/" + TaxpayerID + "/maindivisions/");
					cnreturns = "{\"companyId\": \"" + TaxpayerID + "\",\"jurisdictionId\": \"" + CStates[jID]
							+ "\",\"startDate\": \"" + StDate + "\"}";
					response = webResource.accept("application/json").type("application/json")
							.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, cnreturns);
				}
			}
		}
		System.out.println(response.getStatus());
		webResource = null;
		response = null;
		returns = null;
		webResource = client.resource(ReturnRL + "/returns" + "/" + returnid + "?taxpayerId=" + TaxpayerID + "");
		returns = "{\"id\": \"" + returnid + "\",\"name\": \"" + rname + "\",\"registrationNumber\": \"" + rnumber
				+ "\",\"filingMethod\": \"" + fmethod + "\",\"filingFrequency\": \"" + ffrequency
				+ "\",\"fiscalYearStartMonthOverride\": \"" + finyearstart + "\",\"schedules\":[],\"fields\":[]}";
		switch (rname) {
		case "CN-BRITISH COLUMBIA Provincial Sales Tax":
			businessno = Business_No;
			returns = "{\"id\": \"" + returnid + "\",\"name\": \"" + rname + "\",\"registrationNumber\": \"" + rnumber
					+ "\",\"filingMethod\": \"" + fmethod + "\",\"filingFrequency\": \"" + ffrequency
					+ "\",\"fiscalYearStartMonthOverride\": \"" + finyearstart
					+ "\",\"schedules\":[],\"fields\":[{\"id\": \"2632\",\"label\": \"Business Number\",\"value\": \""
					+ businessno + "\"},]}";
			break;
		case "CN-QUEBEC QST Return":
			quebecno = Quebec_No;
			if (url.equalsIgnoreCase("QA")) {
				returns = "{\"id\": \"" + returnid + "\",\"name\": \"" + rname + "\",\"registrationNumber\": \""
						+ rnumber + "\",\"filingMethod\": \"" + fmethod + "\",\"filingFrequency\": \"" + ffrequency
						+ "\",\"fiscalYearStartMonthOverride\": \"" + finyearstart
						+ "\",\"schedules\":[],\"fields\":[{\"id\": \"2542\",\"label\": \"Quebec Enterprise Number (NEQ)\",\"value\": \""
						+ quebecno + "\"}]}";
			} else {
				returns = "{\"id\": \"" + returnid + "\",\"name\": \"" + rname + "\",\"registrationNumber\": \""
						+ rnumber + "\",\"filingMethod\": \"" + fmethod + "\",\"filingFrequency\": \"" + ffrequency
						+ "\",\"fiscalYearStartMonthOverride\": \"" + finyearstart
						+ "\",\"schedules\":[],\"fields\":[]}";
			}
			break;
		case "IN Sales and Use Tax ST-103 Pymnt Coupon":
			thirdpos = Three_Position;
			returns = "{\"id\": \"" + returnid + "\",\"name\": \"" + rname + "\",\"registrationNumber\": \"" + rnumber
					+ "\",\"filingMethod\": \"" + fmethod + "\",\"filingFrequency\": \"" + ffrequency
					+ "\",\"fiscalYearStartMonthOverride\": \"" + finyearstart
					+ "\",\"schedules\":[],\"fields\":[{\"id\": \"2526\",\"label\": \"Three position #\",\"value\": \""
					+ thirdpos + "\"}]}";
			break;
		default:
			break;
		}
		response = webResource.accept("application/json").type("application/json")
				.header(HttpHeaders.AUTHORIZATION, BearerToken).put(ClientResponse.class, returns);
		System.out.println(rname + " " + response.getStatus());
		System.out.println(returns);
		if (response.getStatus() == 401) {
			BearerTokenPageSource();
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).put(ClientResponse.class, returns);
		}
		if (response.getStatus() == 409 || response.getStatus() == 404 || response.getStatus() == 403
				|| response.getStatus() == 401 || response.getStatus() == 400) {
			assertTrue(false);
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
	}

	public void RESTlocations(String TaxpayerName, String Location_Code, String Location_Name, String Loc_Reg_Code,
			String Loc_Start_Date, String Loc_Address, String Loc_City, String Loc_State, String Loc_Zip,
			String taxareaId, String JurisdictionID, String StartDate) throws Exception {

		String location = null;
		String lCode, lName, rCode, sDate, sAddress, city, state, zip, taxareaID, jurisid, StDate;
		String restUrl = url + "/config/api/v1/";
		client = Client.create();
		BearerTokenPageSource();
		String serviceresponse;
		String RESTFilePath = xDownloadFiles("RESTAPI");
		webResource = client.resource(restUrl + "companies");
		System.out.println(BearerToken);
		response = webResource.type("application/json").header(HttpHeaders.AUTHORIZATION, BearerToken)
				.get(ClientResponse.class);
		if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
				|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		serviceresponse = response.getEntity(String.class);
		writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
		TaxpayerID = xReadTaxpayerID(RESTFilePath + "\\TaxpayerId.json", "id", "name", TaxpayerName);

		lCode = Location_Code;
		lName = Location_Name;
		rCode = Loc_Reg_Code;
		sDate = Loc_Start_Date;
		sAddress = Loc_Address;
		city = Loc_City;
		state = Loc_State;
		zip = Loc_Zip;
		taxareaID = taxareaId;
		jurisid = JurisdictionID;
		StDate = StartDate;

		String CurState = Loc_State;

		if (!(CurState.equalsIgnoreCase("CN") || CurState.equalsIgnoreCase("CANADA"))) {
			webResource = client.resource(url + "/config/api/v1/companies" + "/" + TaxpayerID + "/maindivisions/");
			location = "{\"CompanyId\": \"" + TaxpayerID + "\",\"JurisdictionId\": \"" + jurisid + "\",\"start\": \""
					+ StDate + "\"}";
		} else {
			webResource = client.resource(url + "/config/api/v1/companies" + "/" + TaxpayerID + "/countries/");
			location = "{\"companyId\": \"" + TaxpayerID + "\",\"jurisdictionId\": \"" + jurisid + "\"}";
		}
		System.out.println(location);
		response = webResource.accept("application/json").type("application/json")
				.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, location);
		System.out.println(response.getStatus());
		webResource = null;
		location = null;
		response = null;

		webResource = client.resource(restUrl + "locations");

		switch (state) {
		case "CO":
			location = "{\"companyId\": \"" + TaxpayerID + "\",\"name\": \"" + lName + "\",\"code\": \"" + lCode
					+ "\",\"registrationCode\": \"" + rCode + "\",\"startDate\": \"" + sDate + "\",\"address1\": \""
					+ sAddress + "\",\"city\": \"" + city + "\",\"state\": \"" + state + "\",\"zip\": \"" + zip
					+ "\",\"taxAreaId\": \"" + taxareaID
					+ "\",\"countryId\":\"1\",\"fields\":[{\"id\":80,\"label\": \"Is this a Physical Location?\",\"optionValue\":22,\"options\": [{ \"id\": 22,\"label\": \"No - Utilities Location(207)\", \"order\": 30}]}]}";
			break;
		case "TX":
			location = "{\"companyId\": \"" + TaxpayerID + "\",\"name\": \"" + lName + "\",\"code\": \"" + lCode
					+ "\",\"registrationCode\": \"" + rCode + "\",\"startDate\": \"" + sDate + "\",\"address1\": \""
					+ sAddress + "\",\"city\": \"" + city + "\",\"state\": \"" + state + "\",\"zip\": \"" + zip
					+ "\",\"taxAreaId\": \"" + taxareaID
					+ "\",\"countryId\":\"1\",\"fields\":[{\"id\": 3,\"label\": \"Local reporting requirements\",\"optionValue\": 2,\"options\": [{\"id\": 2,\"label\": \"Outlet Number\",\"order\": 3}]},{\"id\": 73,\"label\": \"EDI: Principle Type of Business\",\"optionValue\": 13,\"options\": [{\"id\": 13,\"label\": \"Retail\",\"order\": 14 }]}]}";
			break;
		case "MO":
			location = "{\"companyId\": \"" + TaxpayerID + "\",\"name\": \"" + lName + "\",\"code\": \"" + lCode
					+ "\",\"registrationCode\": \"" + rCode + "\",\"startDate\": \"" + sDate + "\",\"address1\": \""
					+ sAddress + "\",\"city\": \"" + city + "\",\"state\": \"" + state + "\",\"zip\": \"" + zip
					+ "\",\"taxAreaId\": \"" + taxareaID
					+ "\",\"countryId\":\"1\",\"fields\":[{\"id\": 46,\"label\": \"Always Report Sales?\",\"optionValue\": 4,\"options\": [{\"id\": 4,\"label\": \"No\",\"order\": 5}]},{\"id\": 47,\"label\": \"Always Report Leasing?\",\"optionValue\": 6,\"options\": [{\"id\": 6,\"label\": \"No\",\"order\": 7}]},{\"id\": 48,\"label\": \"Always Report Food Tax?\",\"optionValue\": 8,\"options\": [{\"id\": 8,\"label\": \"No\",\"order\": 9}]},{\"id\": 61,\"label\": \"Always Report Consumers Use?\",\"optionValue\": 10,\"options\": [{\"id\": 10,\"label\": \"No\",\"order\": 11}]},{\"id\": 63,\"label\": \"Is this a Physical Location?\",\"optionValue\": 12,\"options\": [{\"id\": 12,\"label\": \"No\",\"order\": 13}]},{\"id\": 93,\"label\": \"Always Report Food Consumers Use?\",\"optionValue\": 27,\"options\": [{\"id\": 27,\"label\": \"Yes\",\n \"order\": 27}]},{\"id\": 100,\"label\": \"Always Report Manufacturing Exe?\",\"optionValue\": 29,\"options\": [{\"id\": 29,\"label\": \"No\",\"order\": 29}]}]}";
			break;
		default:
			location = "{\"companyId\": \"" + TaxpayerID + "\",\"name\": \"" + lName + "\",\"code\": \"" + lCode
					+ "\",\"registrationCode\": \"" + rCode + "\",\"startDate\": \"" + sDate + "\",\"address1\": \""
					+ sAddress + "\",\"city\": \"" + city + "\",\"state\": \"" + state + "\",\"zip\": \"" + zip
					+ "\",\"taxAreaId\": \"" + taxareaID + "\",\"countryId\":\"1\",\"fields\":[]}";
		}
		System.out.println(location);
		response = webResource.accept("application/json").type("application/json")
				.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, location);
		if (response.getStatus() == 401) {
			BearerTokenPageSource();
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, location);
		}
		if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
				|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
	}

	public void RESTConfigureProducts(String TaxpayerName, String Product_Type, String Product_Code,
			String Product_Name, String Start_Date, String Buysell_Prdct, String Taxability_CategoryId,
			String TaxabilityCategory_name) throws Exception {

		String restUrl = url + "/config/api/v1/";
		client = Client.create();
		BearerTokenPageSource();
		String serviceresponse;
		String RESTFilePath = xDownloadFiles("RESTAPI");
		String configuratorid = "";
		String productType = "";
		String ptype, pcode, pname, psdate, tid, ConfigId, products;

		webResource = client.resource(restUrl + "companies");
		System.out.println(BearerToken);
		response = webResource.type("application/json").header(HttpHeaders.AUTHORIZATION, BearerToken)
				.get(ClientResponse.class);
		if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
				|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
			assertTrue(false);
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		serviceresponse = response.getEntity(String.class);
		Thread.sleep(3000);
		writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
		TaxpayerID = xReadTaxpayerID(RESTFilePath + "\\TaxpayerId.json", "id", "name", TaxpayerName);

		ptype = Product_Type;
		pcode = Product_Code;
		pname = Product_Name;
		psdate = Start_Date;
		tid = Taxability_CategoryId;
		ConfigId = Buysell_Prdct;
		if (ConfigId.equalsIgnoreCase("Seller")) {
			configuratorid = "1";
			switch (ptype) {
			case "Product Code":
				productType = "0";
				break;
			case "Product Class":
				productType = "1";
				break;
			case "FlexCode03":
				productType = "6";
				break;
			case "FlexCode04":
				productType = "7";
				break;
			case "FlexCode05":
				productType = "8";
				break;
			case "FlexCode06":
				productType = "9";
				break;
			case "FlexCode07":
				productType = "10";
				break;
			}
		} else if (ConfigId.equalsIgnoreCase("Buyer")) {
			configuratorid = "2";
			switch (ptype) {
			case "Usage Code":
				productType = "2";
				break;
			case "Usage Class":
				productType = "3";
				break;
			case "FlexCode03":
				productType = "21";
				break;
			case "FlexCode04":
				productType = "22";
				break;
			case "FlexCode05":
				productType = "23";
				break;
			case "FlexCode06":
				productType = "24";
				break;
			case "FlexCode07":
				productType = "25";
				break;
			}
		}
		webResource = client.resource(restUrl + "new-product-mapping");
		products = "{\"mappingOwnerTaxpayerId\": \"" + TaxpayerID + "\",\"taxCategoryId\": \"" + configuratorid
				+ "\",\"productName\": \"" + pname + "\",\"productTypeId\": \"" + productType + "\",\"productCode\": \""
				+ pcode + "\",\"taxabilityCategoryId\": \"" + tid + "\",\"startDate\": \"" + psdate
				+ "\",\"endDate\": \"null\"}";
		System.out.println(products);
		response = webResource.accept("application/json").type("application/json")
				.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, products);
		if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
				|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
			assertTrue(false);
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
	}

	public void RESTexception(String Company_Name, String Customer_Type, String Customer_Code, String Customer_Name,
			String Exempt_CertInfo, String Cus_Start_Date, String Tax_Result, String Cert_Exception_Type,
			String Cert_Exception_No, String Cert_Exception_StartDate, String Exception_stateshortname,
			String Exception_State, String Exception_Reason, String Product_Code, String Exception_JurisID,
			String Exception_JurisLLEID) throws Exception {
		String RESTFilePath = xDownloadFiles("RESTAPI");
		String certificateIndex = "";
		String CerNumber = "";
		String cert_sdate = "";
		String exceptionid = "";
		String exceptionstate = "";
		String restUrl = url + "/config/api/v1/";
		client = Client.create();
		BearerTokenPageSource();
		String cTypeindex = "";
		String taxresulttype = "";
		String certificateindex = "";
		String certificateType = "";
		String ExceptionReason = "";
		String ExceptionReasonId = "";
		String ctypename, ccode, cname, jurisdictiontypeID, lowerLevelJurisdictionId, ecertificatename, esdate,
				taxresult, exceptions, serviceresponse, CustomerId, certificate, exstatename;
		webResource = client.resource(restUrl + "companies");
		System.out.println(BearerToken);
		response = webResource.type("application/json").header(HttpHeaders.AUTHORIZATION, BearerToken)
				.get(ClientResponse.class);
		if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
				|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
			assertTrue(false);
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		serviceresponse = response.getEntity(String.class);
		writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
		TaxpayerCompanyName = Company_Name;
		TaxpayerID = xReadTaxpayerID(RESTFilePath + "\\TaxpayerId.json", "id", "name", TaxpayerCompanyName);
		ctypename = Customer_Type;
		if (ctypename.equalsIgnoreCase("Code")) {
			cTypeindex = "0";
		} else if (ctypename.equalsIgnoreCase("Class")) {
			cTypeindex = "1";
		}
		ccode = Customer_Code;
		cname = Customer_Name;
		ecertificatename = Exempt_CertInfo;
		esdate = Cus_Start_Date;
		taxresult = Tax_Result;
		if (taxresult.equalsIgnoreCase("Exempt")) {
			taxresulttype = "3";
		} else if (taxresult.equalsIgnoreCase("Taxable")) {
			taxresulttype = "1";
		}
		if (ecertificatename.equalsIgnoreCase("Resale")) {
			certificateindex = "1";
		} else if (ecertificatename.equalsIgnoreCase("Government")) {
			certificateindex = "3";
		} else if (ecertificatename.equalsIgnoreCase("Exempt Organization")) {
			certificateindex = "9";
		}
		webResource = client.resource(restUrl + "exceptions");
		exceptions = "{\"customerType\": \"" + cTypeindex + "\",\"customerTypeName\": \"" + ctypename
				+ "\",\"customerCode\": \"" + ccode + "\",\"customerName\": \"" + cname
				+ "\",\"deductionCategory\":{\"name\": \"" + ecertificatename + "\",\"value\": \"" + certificateindex
				+ "\"},\"taxResult\":{\"name\": \"" + taxresult + "\",\"value\": \"" + taxresulttype
				+ "\"},\"startDate\": \"" + esdate + "\",\"companyId\": \"" + TaxpayerID + "\"}";
		System.out.println(exceptions);
		response = webResource.accept("application/json").type("application/json")
				.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, exceptions);
		if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
				|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
			assertTrue(false);
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		serviceresponse = response.getEntity(String.class);
		writeJsonFile(serviceresponse, RESTFilePath, "CustomerId.json");
		CustomerId = xReadJSON(RESTFilePath + "\\CustomerId.json", "id", "");
		System.out.println("Customer id is :" + CustomerId);
		if (Tax_Result.equalsIgnoreCase("Taxable")) {
			certificateType = Cert_Exception_Type;
			if (Cert_Exception_Type.equalsIgnoreCase("Exemption Certificate")) {
				certificateIndex = "1";
			} else if (Cert_Exception_Type.equalsIgnoreCase("Direct Pay Permit")) {
				certificateIndex = "2";
			}
			CerNumber = Cert_Exception_No;
			cert_sdate = Cert_Exception_StartDate;
			exstatename = Exception_stateshortname;
			exceptionstate = Exception_State;
			exceptionid = getReturnandStateIds(exceptionstate, "config\\jurisdictionids.properties");
			ExceptionReason = Exception_Reason;
			ExceptionReasonId = getReturnandStateIds(ExceptionReason, "config\\reasonexceptionid.properties");
			jurisdictiontypeID = Exception_JurisID;
			lowerLevelJurisdictionId = Exception_JurisLLEID;
			webResource = client.resource(restUrl + "certificates");
			certificate = "{\"customerId\": \"" + CustomerId + "\",\"startDate\": \"" + cert_sdate
					+ "\",\"exceptionType\": \"" + certificateIndex + "\",\"exceptionClass\": \"" + certificateType
					+ "\",\"exceptionNumber\": \"" + CerNumber + "\",\"jurisdictions\":[{\"jurisdictionId\": \""
					+ exceptionid + "\",\"jurisdictionType\": \"STATE\",\"jurisdictionName\": \"" + exstatename
					+ "\",\"exceptionReasonId\": \"" + ExceptionReasonId + "\", \"jurisdictionTypeTypeId\":  \""
					+ jurisdictiontypeID + "\", \"lowerLevelJurisdictionId\":  \"" + lowerLevelJurisdictionId
					+ "\",\"exceptionReason\": \"" + ExceptionReason + "\"}],\"productIds\": [],\"products\": []}";
			System.out.println(certificate);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, certificate);
			if (response.getStatus() == 401) {
				BearerTokenPageSource();
				response = webResource.accept("application/json").type("application/json")
						.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, certificate);
			}
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		}
	}

	public String Restconvertstateshort(String Statename) {
		String stateshortname = "";
		try {
			switch (Statename.toUpperCase()) {
			case "ALABAMA":
				stateshortname = "AL";
				break;
			case "ALASKA":
				stateshortname = "AK";
				break;
			case "ARIZONA":
				stateshortname = "AZ";
				break;
			case "ARKANSAS":
				stateshortname = "AR";
				break;
			case "CALIFORNIA":
				stateshortname = "CA";
				break;
			case "COLORADO":
				stateshortname = "CO";
				break;
			case "CONNECTICUT":
				stateshortname = "CT";
				break;
			case "DELAWARE":
				stateshortname = "DE";
				break;
			case "DISTRICT OF COLUMBIA":
				stateshortname = "DC";
				break;
			case "FLORIDA":
				stateshortname = "FL";
				break;
			case "GEORGIA":
				stateshortname = "GA";
				break;
			case "HAWAII":
				stateshortname = "HI";
				break;
			case "IDAHO":
				stateshortname = "ID";
				break;
			case "ILLINOIS":
				stateshortname = "IL";
				break;
			case "INDIANA":
				stateshortname = "IN";
				break;
			case "IOWA":
				stateshortname = "IA";
				break;
			case "KANSAS":
				stateshortname = "KS";
				break;
			case "KENTUCKY":
				stateshortname = "KY";
				break;
			case "LOUISIANA":
				stateshortname = "LA";
				break;
			case "MAINE":
				stateshortname = "ME";
				break;
			case "MARYLAND":
				stateshortname = "MD";
				break;
			case "MASSACHUSETTS":
				stateshortname = "MA";
				break;
			case "MICHIGAN":
				stateshortname = "MI";
				break;
			case "MINNESOTA":
				stateshortname = "MN";
				break;
			case "MISSISSIPPI":
				stateshortname = "MS";
				break;
			case "MISSOURI":
				stateshortname = "MO";
				break;
			case "MONTANA":
				stateshortname = "MT";
				break;
			case "NEBRASKA":
				stateshortname = "NE";
				break;
			case "NEVADA":
				stateshortname = "NV";
				break;
			case "NEW HAMPSHIRE":
				stateshortname = "NH";
				break;
			case "NEW JERSEY":
				stateshortname = "NJ";
				break;
			case "NEW MEXICO":
				stateshortname = "NM";
				break;
			case "NEW YORK":
				stateshortname = "NY";
				break;
			case "NORTH CAROLINA":
				stateshortname = "NC";
				break;
			case "NORTH DAKOTA":
				stateshortname = "ND";
				break;
			case "OHIO":
				stateshortname = "OH";
				break;
			case "OKLAHOMA":
				stateshortname = "OK";
				break;
			case "OREGON":
				stateshortname = "OR";
				break;
			case "PENNSYLVANIA":
				stateshortname = "PA";
				break;
			case "PUERTO RICO":
				stateshortname = "PR";
				break;
			case "RHODE ISLAND":
				stateshortname = "RI";
				break;
			case "SOUTH CAROLINA":
				stateshortname = "SC";
				break;
			case "SOUTH DAKOTA":
				stateshortname = "SD";
				break;
			case "TENNESSEE":
				stateshortname = "TN";
				break;
			case "TEXAS":
				stateshortname = "TX";
				break;
			case "UTAH":
				stateshortname = "UT";
				break;
			case "VERMONT":
				stateshortname = "VT";
				break;
			case "VIRGINIA":
				stateshortname = "VA";
				break;
			case "WASHINGTON":
				stateshortname = "WA";
				break;
			case "WEST VIRGINIA":
				stateshortname = "WV";
				break;
			case "WISCONSIN":
				stateshortname = "WI";
				break;
			case "WYOMING":
				stateshortname = "WY";
				break;
			default:
				stateshortname = Statename;
				break;
			}

		} catch (Exception e) {

			return e.getMessage();
		}
		return stateshortname;

	}

	@SuppressWarnings("rawtypes")
	public String getReturnandStateIds(String eval, String configpath) {
		String id = "";
		String key = "";
		String value;
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\" + configpath);
			prop.load(fis);
			Enumeration enuKeys = prop.keys();
			while (enuKeys.hasMoreElements()) {
				key = (String) enuKeys.nextElement();
				value = prop.getProperty(key);
				if (key.contains("_")) {
					key = key.replace("_", " ");
				}
				if (eval.equalsIgnoreCase(key)) {
					id = value;
					break;
				}
			}
		} catch (Exception e) {

			e.getMessage();
		}
		return id;
	}

	public String BearerTokenPageSource() {
		try {
			ConfigurePage CNP = PageFactory.initElements(driver, ConfigurePage.class);
			waitUntilElementInvisibe(180, CNP.NPROGRESSBUSY_XPATH, driver);
			Thread.sleep(5000);
			BearerToken = null;
			String strpagesource = driver.getPageSource();
			String subString = "accessToken";
			String s2 = "accessTokenExpiryUtc";
			String[] Bearertoken;
			String SubStr;
			if (strpagesource.indexOf(subString) != -1) {
				SubStr = strpagesource.substring(strpagesource.indexOf(subString), strpagesource.indexOf(s2));
				Bearertoken = SubStr.split("'");
				BearerToken = Bearertoken[1];
				BearerToken = "Bearer" + " " + BearerToken;
			  
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public void RESTrequest(String Url, String Afterlogout, String InvalidBarerToken, String Auth_Req, String Response)
			throws Exception {
		try {
			client = Client.create();
			BearerTokenPageSource();
			String URL = Url.replace("env", Environment);
			webResource = client.resource(URL);
			if (Afterlogout.equalsIgnoreCase("Yes")) {
				userRegistration.Logout();
				BearerTokenPageSource();
			}
			if (InvalidBarerToken.equalsIgnoreCase("Yes")) {
				response = webResource.accept("application/json").type("application/json")
						.header(HttpHeaders.AUTHORIZATION, "AZUUJHJ").get(ClientResponse.class);
			}
			if (Auth_Req.equalsIgnoreCase("Yes")) {
				response = webResource.accept("application/json").type("application/json")
						.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			}
			if (Auth_Req == "") {
				response = webResource.accept("application/json").get(ClientResponse.class);// .header(HttpHeaders.AUTHORIZATION,BearerToken).put(ClientResponse.class,returns);
			}
			String resvalue = response.getEntity(String.class).replace("\r\n", "").replace("\n", "");
			String respval = Response;
			assertTrue(resvalue.equalsIgnoreCase(respval));
			System.out.println("response is " + resvalue);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(CloudData.get_data("expectedfail").equalsIgnoreCase("Yes"));
			} else {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void RESTCustomCategories(String Category_Name, String ParentCategoryId, String JurisdictionId,
			String IsExempt, String reasonCategoryId) throws Exception {
		String cname, parenthierarcy, ReturnstateId, taxableType, reasonId, Categories;
		String restUrl = url + "/config/api/v1/";
		client = Client.create();
		BearerTokenPageSource();
		cname = Category_Name;
		parenthierarcy = ParentCategoryId;
		ReturnstateId = JurisdictionId;
		taxableType = IsExempt;
		reasonId = reasonCategoryId;
		webResource = client.resource(restUrl + "/" + TaxpayerID + "/customcategories");
		if (taxableType.equalsIgnoreCase("true")) {
			Categories = "{\"name\": \"" + cname + "\",\"parentCategoryId\":" + parenthierarcy + ",\"companyId\": \""
					+ TaxpayerID + "\",\"rules\":[{\"jurisdictionId\":" + ReturnstateId + ",\"isExempt\":" + taxableType
					+ ",\"reasonCategoryId\":" + reasonId + "}]}";
		} else {
			Categories = "{\"name\": \"" + cname + "\",\"parentCategoryId\":" + parenthierarcy + ",\"companyId\": \""
					+ TaxpayerID + "\",\"rules\":[{\"jurisdictionId\":" + ReturnstateId + ",\"isExempt\":" + taxableType
					+ "}]}";
		}
		System.out.println(Categories);
		response = webResource.accept("application/json").type("application/json")
				.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, Categories);

		if (response.getStatus() == 401) {
			BearerTokenPageSource();
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, Categories);
		}
		if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
				|| response.getStatus() == 403 || response.getStatus() == 400) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
	}

	public void RESTpostTransactions(String endpointUrl, String transactionType, String TaxpayerCode) throws Exception {
		String Transaction = null, serviceresponse;

		switch (transactionType.toUpperCase()) {

		case "QUOTATION":
			Transaction = "{\"saleMessageType\": \"QUOTATION\",\"seller\": {\"company\": \"" + TaxpayerCode
					+ "\"},\"lineItems\": [{\"seller\": {\"physicalOrigin\": {\"taxAreaId\": 391013000}},\"customer\": {\"customerCode\": {\"classCode\": \"custclass\",\"value\": \"cust\"},\"destination\": {\"streetAddress1\": \"2301 Renaissance Blvd\",\"streetAddress2\": \"Suite 100\",\"city\": \"King of Prussia\",\"mainDivision\": \"PA\",\"postalCode\": \"19406\",\"country\": \"UNITED STATES\"}},\"product\": {\"productClass\": \"PRODCLASS\",\"value\": \"PRODCODE\"},\"quantity\":{\"value\": 10},\"unitPrice\": 10,\"flexibleFields\": {\"flexibleCodeFields\": [{\"fieldId\": 1,\"value\": \"FLEXCodeField1\"}],\"flexibleNumericFields\": [{\"fieldId\": 1,\"value\": 111}],\"flexibleDateFields\": [{\"fieldId\": 1,\"value\": \"2017-12-18\"}]},\"lineItemNumber\": 1,\"deliveryTerm\": \"FOB\"},{\"seller\": {\"physicalOrigin\": {\"taxAreaId\": 391013000}},\"customer\": {\"customerCode\": {\"classCode\": \"custclass\",\"value\": \"cust\"},\"destination\": {\"streetAddress1\": \"2301 Renaissance Blvd\",\"streetAddress2\": \"Suite 100\",\"city\": \"King of Prussia\",\"mainDivision\": \"PA\",\"postalCode\": \"19406\",\"country\": \"UNITED STATES\"}},\"product\": {\"productClass\": \"SHIPPINGCLASS\",\"value\": \"SHIPPING\"},\"quantity\": {\"value\": 1},\"extendedPrice\": 20,\"lineItemNumber\": 2}],\"documentNumber\": \"12345\",\"documentDate\": \"2017-12-18\",\"transactionType\": \"SALE\"}";
			break;

		case "INVOICE":
			Transaction = "{\"saleMessageType\": \"INVOICE\",\"seller\": {\"company\": \"" + TaxpayerCode
					+ "\"},\"lineItems\": [{\"seller\": {\"physicalOrigin\": {\"taxAreaId\": 391013000}},\"customer\": {\"customerCode\": {\"classCode\": \"custclass\",\"value\": \"cust\"},\"destination\": {\"streetAddress1\": \"2301 Renaissance Blvd\",\"streetAddress2\": \"Suite 100\",\"city\": \"King of Prussia\",\"mainDivision\": \"PA\",\"postalCode\": \"19406\",\"country\": \"UNITED STATES\"}},\"product\": {\"productClass\": \"PRODCLASS\",\"value\": \"PRODCODE\"},\"quantity\":{\"value\": 10},\"unitPrice\": 10,\"flexibleFields\": {\"flexibleCodeFields\": [{\"fieldId\": 1,\"value\": \"FLEXCodeField1\"}],\"flexibleNumericFields\": [{\"fieldId\": 1,\"value\": 111}],\"flexibleDateFields\": [{\"fieldId\": 1,\"value\": \"2017-12-18\"}]},\"lineItemNumber\": 1,\"deliveryTerm\": \"FOB\"},{\"seller\": {\"physicalOrigin\": {\"taxAreaId\": 391013000}},\"customer\": {\"customerCode\": {\"classCode\": \"custclass\",\"value\": \"cust\"},\"destination\": {\"streetAddress1\": \"2301 Renaissance Blvd\",\"streetAddress2\": \"Suite 100\",\"city\": \"King of Prussia\",\"mainDivision\": \"PA\",\"postalCode\": \"19406\",\"country\": \"UNITED STATES\"}},\"product\": {\"productClass\": \"SHIPPINGCLASS\",\"value\": \"SHIPPING\"},\"quantity\": {\"value\": 1},\"extendedPrice\": 20,\"lineItemNumber\": 2}],\"documentNumber\": \"12345\",\"documentDate\": \"2017-12-18\",\"transactionType\": \"SALE\"}";
			break;

		}
		WebResource webResource = client.resource(endpointUrl);

		ClientResponse response = webResource.accept("application/json").type("application/json")
				.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, Transaction);
		if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
				|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
			response.getEntity(String.class);
			assertTrue(false);
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		serviceresponse = response.getEntity(String.class);
		File directory = new File(".\\");
		String DownloadLoc = directory.getCanonicalPath();
		writeJsonFile(serviceresponse, DownloadLoc, "HRRestTransactions_Status.json");
	}

	public void GetReturns(String reURL) throws Exception {
		try {
			TaxpayerID = null;
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				System.out.println(response.getStatus());
			}
			System.out.println(response.getEntity(String.class));
			response.getEntity(String.class);

			// String serviceresponse = response.getEntity(String.class);
			// System.out.println(serviceresponse);
			// writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
			// File directory = new File(".//"+"DownloadingFiles");
			// TaxpayerID = xReadJSON(RESTFilePath + "\\TaxpayerId.json", "id", "");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTGetFillingEntittes(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			String serviceresponse = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			serviceresponse = response.getEntity(String.class);
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
				PublicTaxpayerID = xReadTaxpayerID(RESTFilePath + "\\TaxpayerId.json", "id", "", "");
				assertNotNull(jsonPathEvaluator.get("id"));
				assertNotNull(jsonPathEvaluator.get("name"));
			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTGetBearerToken() throws Exception {
		try {
			TaxpayerID = null;
			driver.get(
					"https://devauth.vertexsmb.com/identity/connect/authorize?response_type=token&scope=vcd-adjustment-file-api%20vtms-internal-api&client_id=tax-central&state=State&redirect_uri=https://devportal.vertexsmb.com/");
			String URlvalue = driver.getCurrentUrl();
			String arryval[] = URlvalue.split("access_token=");
			System.out.println(URlvalue);
			String arr2val[] = arryval[1].split("&token_type");
			BearerToken = arr2val[0];
			BearerToken = "Bearer" + " " + BearerToken;
			System.out.println(BearerToken);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTGetBearerToken(String Scope) throws Exception {
		try {
			Scope = URLEncoder.encode(Scope, "utf-8");
			driver.get("https://devauth.vertexsmb.com/identity/connect/authorize?response_type=token&scope=" + Scope
					+ "&client_id=tax-central&state=State&redirect_uri=https://devportal.vertexsmb.com/");
			String URlvalue = driver.getCurrentUrl();
			String arryval[] = URlvalue.split("access_token=");
			System.out.println(URlvalue);
			String arr2val[] = arryval[1].split("&token_type");
			BearerToken = arr2val[0];
			BearerToken = "Bearer" + " " + BearerToken;
			System.out.println(BearerToken);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTGetBearerToken1(String Scope, String Url, String redirectURL) throws Exception {
		try {
			Scope = URLEncoder.encode(Scope, "utf-8");
			String URL = Url;
			String Redirecturl = redirectURL;

			// https://devportal.vertexsmb.com/
			// "https://devauth.vertexsmb.com/identity/connect/authorize?response_type=token&scope="

			driver.get(URL + Scope + "&client_id=tax-central&state=State&redirect_uri=" + Redirecturl);
			String URlvalue = driver.getCurrentUrl();
			String arryval[] = URlvalue.split("access_token=");
			System.out.println(URlvalue);
			String arr2val[] = arryval[1].split("&token_type");
			BearerToken = arr2val[0];
			BearerToken = "Bearer" + " " + BearerToken;
			System.out.println(BearerToken);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void oauth2accessToken(String scope, String clientid, String redirecturi, String partitonUUID,
			String username, String password) throws Exception {
		try {
			String AuthUrl = null, serviceresponse = null;
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaauth.vertexsmb.com/identity/connect/authorize";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devauth.vertexsmb.com/identity/connect/authorize";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageauth.vertexsmb.com/identity/connect/authorize";
			}
			client = Client.create();
			webResource = client.resource(AuthUrl);
			String payload = "grant_type=password&scope=" + scope + "&client_id=" + clientid + "&redirect_uri="
					+ redirecturi;
			response = webResource.accept("application/json").header("Vertex-Context", partitonUUID)
					.post(ClientResponse.class, payload);
			serviceresponse = response.getEntity(String.class);
			System.out.println(serviceresponse);
			BearerToken = "Bearer" + " " + BearerToken;
			System.out.println(BearerToken);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String BearerTokenGenerator() {

		String username = "MaldambaCo";
		String password = "MaldambaCo1!";
		String accessToken = "63e5bc8af3fec602798ebd753644ca4a";
		Response response = RestAssured.given().contentType("application/x-www-form-urlencoded")
				.header("Authorization", "Bearer " + accessToken)
				.body("grant_type=password" + "&username=" + username + "&password=" + password
						+ "&scope=openid%20profile%20vtms-internal-api%20help-center%20email%20vcd-filing-api"
						+ "&client_id=test-data-tool-qa" + "&client_secret=240fcc2d4d6e4dcb95d3543083019450")
				.when().post("https://devauth.vertexsmb.com/identity/connect/token");

		JsonPath jsonPathEvaluator = response.jsonPath();
		String token = jsonPathEvaluator.get("access_token");

		return token;
	}

	public static void setToken() {
		token = BearerTokenGenerator();
	}

	public static Response getResponse(String url) {
		Response resp = RestAssured.given().auth().oauth2(BearerToken).get(url);
		return resp;
	}

	public void postFillingEntittes(String reURL, String payload, int status) throws Exception {
		try {
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, payload);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			assertTrue(status == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateFillingEntittes(String reURL, String payload, int status) throws Exception {
		try {
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			System.out.println(payload);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).put(ClientResponse.class, payload);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			assertTrue(status == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTGetreturns(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			if (statuscode == 401) {
				BearerToken = BearerToken + "212121";
			}
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTGetassignedcodes(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				assertNotNull(jsonPathEvaluator.get("id"));
			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTGetassignedcodesettings(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				assertNotNull(jsonPathEvaluator.get("id"));
				assertNotNull(jsonPathEvaluator.get("filingJurisdictionId"));
				assertNotNull(jsonPathEvaluator.get("label"));
			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTGetassignedcodearea(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				assertNotNull(jsonPathEvaluator.get("id"));
				assertNotNull(jsonPathEvaluator.get("filingJurisdictionId"));
				assertNotNull(jsonPathEvaluator.get("name"));
			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void PostPhysicalLocations(String reURL, String payload, int status) throws Exception {
		try {
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, payload);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			assertTrue(status == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void UpdatePhysicalLocations(String reURL, String payload, int status) throws Exception {
		try {
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			System.out.println(payload);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).put(ClientResponse.class, payload);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			assertTrue(status == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTGetstateassignedcodes(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				assertNotNull(jsonPathEvaluator.get("id"));
			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTReturnRegistrations(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				assertNotNull(jsonPathEvaluator.get("id"));
			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void postReturns(String reURL, String payload, int status) throws Exception {
		try {
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			System.out.println(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, payload);
			System.out.println(response.getStatus());
			assertTrue(status == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateReturns(String reURL, String payload, int status) throws Exception {
		try {
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			System.out.println(payload);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).put(ClientResponse.class, payload);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			assertTrue(status == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void postassignedcodes(String reURL, String payload, int status) throws Exception {
		try {
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, payload);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			assertTrue(status == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void GetAllJurisdiction(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				assertNotNull(jsonPathEvaluator.get("id"));
				assertEquals(response.getStatus(), statuscode);
				assertNotNull(jsonPathEvaluator.get("id"));
				assertNotNull(jsonPathEvaluator.get("name"));
				assertNotNull(jsonPathEvaluator.get("code"));
				assertNotNull(jsonPathEvaluator.get("jurisdictionType"));
				assertNotNull(jsonPathEvaluator.get("jurisdictionType.name"));
				assertNotNull(jsonPathEvaluator.get("jurisdictionType.id"));
			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void JurisdictionGetById(String reURL, int statuscode, String state, String Country, String UsTerritory)
			throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				assertNotNull(jsonPathEvaluator.get("id"));
				assertEquals(response.getStatus(), 200);
				assertNotNull(jsonPathEvaluator.get("id"));
				assertNotNull(jsonPathEvaluator.get("name"));
				assertNotNull(jsonPathEvaluator.get("code"));
				if (!state.isEmpty()) {
					assertEquals(jsonPathEvaluator.get("jurisdictionType.name"), "State");
					assertEquals((int) jsonPathEvaluator.get("jurisdictionType.id"), 1);
				}
				if (!Country.isEmpty()) {
					assertEquals(jsonPathEvaluator.get("jurisdictionType.name"), "Country");
					assertEquals((int) jsonPathEvaluator.get("jurisdictionType.id"), 3);
				}
				if (!UsTerritory.isEmpty()) {
					assertEquals(jsonPathEvaluator.get("jurisdictionType.name"), "UsTerritory");
					assertEquals((int) jsonPathEvaluator.get("jurisdictionType.id"), 2);
				}

			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RESTGetjurisdictions(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				assertNotNull(jsonPathEvaluator.get("id"));
			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("resource")
	public String APIUploadAdjustmentFile(String transactionType, String filename) {
		String statusID = null, serviceresponse;
		try {
			String AuthUrl = "";
			String filePath = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
			filePath = xCSVUDateUpdation(AdjustmentFilePath + "\\" + filename + ".csv");
			transactionType = transactionType.toLowerCase();
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/adjustmentfiles/" + transactionType + "/upload";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/adjustmentfiles/" + transactionType + "/upload";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/adjustmentfiles/" + transactionType + "/upload";
			}

			WebResource resource = Client.create().resource(AuthUrl);
			FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
			FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
			FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("foo", "bar").bodyPart(filePart);
			response = resource.type(MediaType.MULTIPART_FORM_DATA).header(HttpHeaders.AUTHORIZATION, BearerToken)
					.post(ClientResponse.class, multipart);
			serviceresponse = response.getEntity(String.class);

			writeJsonFile(serviceresponse, RESTFilePath, "APIstatus.json");
			APIuploadID = xReadJSON(RESTFilePath + "\\APIstatus.json", "id", "");

			if (APIuploadID == null) {
				APIuploadID = statusID + "Fail;Status ID is NULL";
				assertTrue(false);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return APIuploadID;
	}

	public String APIUploadAdjustmentFileStatus() {
		String AuthUrl = null, serviceresponse, uploadStatus = null;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/adjustmentfiles/";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/adjustmentfiles/";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/adjustmentfiles/";
			}
			AuthUrl = AuthUrl + APIuploadID + "/status";
			client = Client.create();
			webResource = client.resource(AuthUrl);
			do {
				Thread.sleep(15000);
				response = webResource.accept("application/json").type("application/json")
						.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);

				serviceresponse = response.getEntity(String.class);
				writeJsonFile(serviceresponse, RESTFilePath, "UploadStatus.json");
				uploadStatus = xReadJSON(RESTFilePath + "\\UploadStatus.json", "message", "");
			} while (!(uploadStatus.equals("Complete with Error") || uploadStatus.equals("Complete")));
			APIadjustmentfileStatus = xReadJSON(RESTFilePath + "\\UploadStatus.json", "reports", "yes");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		}
		return APIadjustmentfileStatus;
	}

	public void APIDownloadAdjustmentFile() {
		String AuthUrl = null, serviceresponse;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/files/";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/files/";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/files/";
			}
			AuthUrl = AuthUrl + APIadjustmentfileStatus;
			client = Client.create();
			webResource = client.resource(AuthUrl);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);

			serviceresponse = response.getEntity(String.class);

			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			writeCSVFile(serviceresponse, RESTFilePath, "doenloadAdjutmentFile.csv");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void APIRTEStatus(String RteFiltername) {
		String AuthUrl = null, serviceresponse;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/rtefiles/" + RteFiltername + "/Status";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/rtefiles/" + RteFiltername + "/Status";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/rtefiles/" + RteFiltername + "/Status";
			}
			url = AuthUrl;
			client = Client.create();
			webResource = client.resource(url);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);

			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "RTEStatus.json");
			xReadJSON(RESTFilePath + "\\RTEStatus.json", "status", "");
			APIRteStatus = xReadJSON(RESTFilePath + "\\RTEStatus.json", "fileGuid", "yes");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		}
	}

	public void APIDownloadRTE(String fileGuid) throws Exception {
		String AuthUrl = null, serviceresponse;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/files/" + fileGuid;
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/files/" + fileGuid;
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/files/" + fileGuid;
			}
			url = AuthUrl;
			client = Client.create();
			webResource = client.resource(url);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);

			serviceresponse = response.getEntity(String.class);

			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			writeCSVFile(serviceresponse, RESTFilePath, "downloadRTEFile.csv");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void QBOAPITogetsubscription(String SubscriptionID) throws Exception {
		String AuthUrl = null, serviceresponse;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/subscription/level";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/subscription/level";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/subscription/level";
			}
			url = AuthUrl;
			client = Client.create();
			webResource = client.resource(url);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);

			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "QBOSubscription.json");
			QBOSubscription = xReadJSON(RESTFilePath + "\\QBOSubscription.json", "data", "id");
			assertTrue(QBOSubscription.equalsIgnoreCase(SubscriptionID));
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void QBOAPIsubscriptionFeatures(String SubscriptionID) throws Exception {
		String AuthUrl = null, serviceresponse;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/subscription/features";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/subscription/features";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/subscription/features";
			}
			url = AuthUrl;
			client = Client.create();
			webResource = client.resource(url);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);

			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "QBOSubscriptionFeature.json");
			// QBOSubscription = xReadJSON(RESTFilePath + "\\QBOSubscriptionFeature.json",
			// "data", "");
			// assertTrue(QBOSubscription.equalsIgnoreCase(SubscriptionID));
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void QBOAPIAddressValidation(String Address1, String Address2, String City, String State, String Zip)
			throws Exception {
		String AuthUrl = null, serviceresponse = null, address = null;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/addressvalidation";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/addressvalidation";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/addressvalidation";
			}
			url = AuthUrl;
			client = Client.create();
			webResource = client.resource(url);
			address = "{\"address1\":\"" + Address1 + "\",\"address2\":\"" + Address2 + "\",\"city\":\"" + City
					+ "\",\"state\":\"" + State + "\",\"zip\":\"" + Zip + "\"}";
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, address);
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "QBOAddress.json");
			QBOAddress = xReadjson(RESTFilePath + "\\QBOAddress.json", "city");
			assertTrue(QBOAddress.equalsIgnoreCase(City));
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void QBOAPIQuotation(String ClientId, String State, String Zip, String ExtendedPrice, String Quantity,
			String TransactionDate, String Documentnumber, String CompanyCode) throws Exception {
		String AuthUrl = null, serviceresponse = null, quotation = null;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/sale";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/sale";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/sale";
			}
			url = AuthUrl;
			client = Client.create();
			webResource = client.resource(url);
			quotation = "{\"clientId\":\"" + ClientId
					+ "\", \"customer\":{\"classCode\":\"\",\"code\":\"\",\"destination\":{\"address1\":\"\",\"address2\":\"\",\"city\":\"\",\"state\":\""
					+ State + "\",\"country\":\"\",\"zip\":\"" + Zip
					+ "\",\"locationCode\":\"\"}},\"lineItems\":[{\"extendedPrice\":\"" + ExtendedPrice
					+ "\",\"quantity\":\"" + Quantity + "\",\"unitPrice\":\"" + ExtendedPrice
					+ "\",\"linenumber\":\"1\",\"productCode\":\"\",\"productClass\":\"\"}],\"messageType\":\"1\",\"transactionDate\":\""
					+ TransactionDate + "\",\"documentNumber\":\"" + Documentnumber
					+ "\",\"discount\":{\"code\":\"GEN\",\"type\":\"1\",\"amount\":\"\"},\"shippingterms\":\"1\",\"seller\":{\"company\":\""
					+ CompanyCode + "\",\"origin\":{\"address1\":\"\",\"address2\":\"\",\"city\":\"\",\"state\":\""
					+ State + "\",\"country\":\"\",\"zip\":\"" + Zip + "\",\"locationCode\":\"\"}}}";
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, quotation);
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "Quotation.json");
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void QBOAPIInvoice(String ClientId, String State, String Zip, String ExtendedPrice, String Quantity,
			String TransactionDate, String Documentnumber, String CompanyCode) throws Exception {
		String AuthUrl = null, serviceresponse = null, Invoice = null;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/sale";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/sale";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/sale";
			}
			url = AuthUrl;
			client = Client.create();
			webResource = client.resource(url);
			Invoice = "{\"clientId\":\"" + ClientId
					+ "\", \"customer\":{\"classCode\":\"\",\"code\":\"\",\"destination\":{\"address1\":\"\",\"address2\":\"\",\"city\":\"\",\"state\":\""
					+ State + "\",\"country\":\"\",\"zip\":\"" + Zip
					+ "\",\"locationCode\":\"\"}},\"lineItems\":[{\"extendedPrice\":\"" + ExtendedPrice
					+ "\",\"quantity\":\"" + Quantity + "\",\"unitPrice\":\"" + ExtendedPrice
					+ "\",\"linenumber\":\"1\",\"productCode\":\"\",\"productClass\":\"\"}],\"messageType\":\"2\",\"transactionDate\":\""
					+ TransactionDate + "\",\"documentNumber\":\"" + Documentnumber + "\",\"postingDate\":\""
					+ TransactionDate
					+ "\",\"discount\":{\"code\":\"GEN\",\"type\":\"1\",\"amount\":\"\"},\"shippingterms\":\"1\",\"seller\":{\"company\":\""
					+ CompanyCode + "\",\"origin\":{\"address1\":\"\",\"address2\":\"\",\"city\":\"\",\"state\":\""
					+ State + "\",\"country\":\"\",\"zip\":\"" + Zip + "\",\"locationCode\":\"\"}}}";
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, Invoice);
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "Invoice.json");
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void APIOracleARAll(String CompanyCode) throws Exception {
		try {
			String AuthUrl = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String Body, serviceresponse;
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/reports/AR_ALL";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/reports/AR_ALL";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/reports/AR_ALL";
			}
			client = Client.create();
			webResource = client.resource(AuthUrl);
			Body = "{\"CompanyCode\":\"" + CompanyCode
					+ "\",\"ExtractName\":\"ORACLE_RECON_AR_ALL\",\"FilterValue\":[\"EBSDB\",\"01/01/2019\",\"01/01/2019\",\"2\",\"6|7|8\",\"0\"]}";
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, Body);
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "ReportsAR_All.json");
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void APITogetARStatus(String ARStatus) throws Exception {
		String AuthUrl = null;
		try {
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/files/" + ARStatus + "/statusAR";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/files/" + ARStatus + "/statusAR";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/files/" + ARStatus + "/statusAR";
			}
			url = AuthUrl;
			client = Client.create();
			webResource = client.resource(url);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);

			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void APITogetAPStatus(String APStatus) throws Exception {
		String AuthUrl = null;
		try {
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/files/" + APStatus + "/statusAP";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/files/" + APStatus + "/statusAP";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/files/" + APStatus + "/statusAP";
			}
			url = AuthUrl;
			client = Client.create();
			webResource = client.resource(url);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);

			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void APIOracleAPAll(String CompanyCode) throws Exception {
		try {
			String AuthUrl = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String Body, serviceresponse;
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/reports/AP_ALL";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/reports/AP_ALL";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/reports/AP_ALL";
			}
			client = Client.create();
			webResource = client.resource(AuthUrl);
			Body = "{\"CompanyCode\":\"" + CompanyCode
					+ "\",\"ExtractName\":\"ORACLE_RECON_AP_ALL\",\"FilterValue\":[\"EBSDB\",\"01/01/2019\",\"01/01/2019\",\"1\",\"1|7\",\"0\"]}";
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, Body);
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "ReportsAP_All.json");
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void APIOracleARDoc(String CompanyCode) throws Exception {
		try {
			String AuthUrl = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String Body, serviceresponse;
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaapi.vertexsmb.com/public/1/reports/AR_DOC";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devapi.vertexsmb.com/public/1/reports/AR_DOC";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageapi.vertexsmb.com/public/1/reports/AR_DOC";
			}
			client = Client.create();
			webResource = client.resource(AuthUrl);
			Body = "{\"CompanyCode\":\"" + CompanyCode
					+ "\",\"ExtractName\":\"ORACLE_RECON_AR_DOC\",\"FilterValue\":[\"EBSDB\",\"01/01/2019\",\"01/01/2019\",\"EBSDB_Sale_0921269531\",\"EBSDB_Sale_0921269531\",\"2\",\"6|7|8\",\"0\"]}";
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, Body);
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "ReportsAR_Doc.json");
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String publicAPItoken(String clientID, String clientSecret, String API, String Pwd) throws Exception {
		String status = "";
		try {
			String AuthUrl = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String Body, serviceresponse, Token;
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaauth.vertexsmb.com/identity/connect/token";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devauth.vertexsmb.com/identity/connect/token";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageauth.vertexsmb.com/identity/connect/token";
			}
			Pwd = URLEncoder.encode(Pwd, "utf-8");
			client = Client.create();
			webResource = client.resource(AuthUrl);
			Body = "grant_type=password&scope=vtms-public-api openid profile&client_id=" + clientID + "&client_secret="
					+ clientSecret + "&username=" + API + "&password=" + Pwd + "";
			response = webResource.accept("application/x-www-form-urlencoded").type("application/x-www-form-urlencoded")
					.post(ClientResponse.class, Body);
			System.out.println("AccessTokenResponse=" + response.getStatus());
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "AccessToken.json");
			Token = xReadJSON(RESTFilePath + "\\AccessToken.json", "access_token", "");
			if (Token == null) {
				status = status + "Fail;BearerToken is NULL";
			}
			BearerToken = "Bearer " + Token;
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public String accessToken(String grantType, String scope, String clientID, String clientSecret, String API,
			String Pwd) throws Exception {
		String status = "";
		try {
			String AuthUrl = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String Body, serviceresponse, Token;
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaauth.vertexsmb.com/identity/connect/token";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devauth.vertexsmb.com/identity/connect/token";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageauth.vertexsmb.com/identity/connect/token";
			}
			Pwd = URLEncoder.encode(Pwd, "utf-8");
			client = Client.create();
			webResource = client.resource(AuthUrl);
			Body = "grant_type=" + grantType + "&scope=" + scope + "&client_id=" + clientID + "&client_secret="
					+ clientSecret + "&username=" + API + "&password=" + Pwd + "";
			response = webResource.accept("application/x-www-form-urlencoded").type("application/x-www-form-urlencoded")
					.post(ClientResponse.class, Body);
			System.out.println("AccessTokenResponse=" + response.getStatus());
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "AccessToken.json");
			Token = xReadJSON(RESTFilePath + "\\AccessToken.json", "access_token", "");
			if (Token == null) {
				status = status + "Fail;BearerToken is NULL";
			}
			BearerToken = "Bearer " + Token;
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public String ITItoken(String clientID, String clientSecret) throws Exception {
		String status = "";
		try {
			String AuthUrl = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String Body, serviceresponse, Token;
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaauth.vertexsmb.com/identity/connect/token";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devauth.vertexsmb.com/identity/connect/token";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageauth.vertexsmb.com/identity/connect/token";
			}
			client = Client.create();
			webResource = client.resource(AuthUrl);
			Body = "grant_type=client_credentials&scope=doc-repo-api vcd-platform-api&client_id=" + clientID
					+ "&client_secret=" + clientSecret;
			response = webResource.accept("application/x-www-form-urlencoded").type("application/x-www-form-urlencoded")
					.post(ClientResponse.class, Body);
			System.out.println("AccessTokenResponse=" + response.getStatus());
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "AccessToken.json");
			Token = xReadJSON(RESTFilePath + "\\AccessToken.json", "access_token", "");
			if (Token == null) {
				status = status + "Fail;BearerToken is NULL";
			}
			BearerToken = "Bearer " + Token;
			// Thread.sleep(15000);
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public void ITIpost(String URL, String fileName) throws Exception {
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String payload = null, serviceresponse;
			client = Client.create();
			webResource = client.resource(URL);

			// payload="{​​​ \"name\": \""+fileName+"\", \"domain\":
			// \"indirect-tax-intelligence\", \"description\": \"test\", \"visibility\":
			// \"internal\", \"mediaType\": \"text\", \"companyName\": \"Frank Test
			// Company\", \"country\": \"USA\", \"version\": 0 }​​​";

			payload = "{\r\n" + "    \"name\": \"" + fileName + "\",\r\n"
					+ "    \"domain\": \"indirect-tax-intelligence\",\r\n" + "    \"description\": \"test\",\r\n"
					+ "    \"visibility\": \"internal\",\r\n" + "    \"mediaType\": \"text\",\r\n"
					+ "    \"companyName\": \"Frank Test Company\",\r\n" + "    \"country\": \"USA\",\r\n"
					+ "    \"version\": 0}";
			// System.out.println(payload);

			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).post(ClientResponse.class, payload);
			System.out.println(response.getStatus());
			// System.out.println(response.getEntity(String.class).toString());
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			serviceresponse = response.getEntity(String.class);
			System.out.println(serviceresponse);

			writeJsonFile(serviceresponse, RESTFilePath, "ITIResponse.json");
			uniqueITIid = xReadJSON(RESTFilePath + "\\ITIResponse.json", "id", "");
			UniqueITIurl = xReadJSON(RESTFilePath + "\\ITIResponse.json", "url", "");
			System.out.println(uniqueITIid);
			System.out.println(UniqueITIurl);
			// Thread.sleep(15000);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String uploadbinary(String AuthUrl, String filename) {
		try {
			String filePath = "";
			String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
			filename = AdjustmentFilePath + "\\" + filename + ".txt";
			// transactionType = transactionType.toLowerCase();

			javax.ws.rs.client.Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
			// org.glassfish.jersey.client.JerseyClient client=
			// JerseyClientBuilder.createClient().register(MultiPartFeature.class)
			javax.ws.rs.client.WebTarget target = client.target(AuthUrl);

			org.glassfish.jersey.media.multipart.MultiPart multiPart = new org.glassfish.jersey.media.multipart.MultiPart();
			multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

			org.glassfish.jersey.media.multipart.file.FileDataBodyPart filePart = new org.glassfish.jersey.media.multipart.file.FileDataBodyPart(
					"file", new File(filePath), MediaType.APPLICATION_OCTET_STREAM_TYPE);
			multiPart.bodyPart(filePart);
			javax.ws.rs.core.Response response = target.request().header(HttpHeaders.AUTHORIZATION, BearerToken)
					.put(Entity.entity(multiPart, multiPart.getMediaType()));

			System.out.println(response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return APIuploadID;
	}

	public String patchfile(String clientID, String clientSecret) throws Exception {
		String status = "";
		try {
			String AuthUrl = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String Body, serviceresponse, Token;
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaauth.vertexsmb.com/identity/connect/token";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devauth.vertexsmb.com/identity/connect/token";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageauth.vertexsmb.com/identity/connect/token";
			}
			client = Client.create();
			webResource = client.resource(AuthUrl);
			Body = "";
			response = webResource.accept("*/*").type("*/*").header(HttpHeaders.AUTHORIZATION, BearerToken)
					.put(ClientResponse.class, Body);
			System.out.println("AccessTokenResponse=" + response.getStatus());
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "AccessToken.json");
			Token = xReadJSON(RESTFilePath + "\\AccessToken.json", "access_token", "");
			if (Token == null) {
				status = status + "Fail;BearerToken is NULL";
			}
			BearerToken = "Bearer " + Token;
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	public void ITIPut(String url, String filename) throws Exception {
		try {
			xDownloadFiles("RESTAPI");
			Thread.sleep(10000);
			String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
			File txtfile = new File(AdjustmentFilePath + "\\" + filename + ".txt");

			WebResource resource = Client.create().resource(url);

			response = resource.accept("Content-Type", "text").put(ClientResponse.class, txtfile);
			String serviceresponse = response.getEntity(String.class);
			System.out.println(serviceresponse);

			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				assertTrue(false);
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void ITIPatch(String URL) throws Exception {

		try {

			String url = URL + uniqueITIid + "/complete";
			Thread.sleep(20000);
			System.out.println(url);

			if (!url.contains(uniqueITIid)) {
				Response response = RestAssured.given().accept("application/json").contentType("application/json")
						.header(HttpHeaders.AUTHORIZATION, BearerToken).patch(url).thenReturn();

				System.out.println(response.getStatusCode());
				System.out.println(response.getStatusLine().toString());

			} else {

				System.out.println("Patch file response is:" + 200);
			}

		} catch (Exception e) {

			e.getMessage();
		}

	}

	public void REStFillingperiod(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				assertNotNull(jsonPathEvaluator.get("id"));
			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void RestTransactionreporttypes(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			RESTGetBearerToken();
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String serviceresponse = "";
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions) response).jsonPath();
			if (statuscode == 200) {
				writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
				PublicTaxpayerID = xReadTaxpayerID(RESTFilePath + "\\TaxpayerId.json", "reportGroupName", "", "");
				assertNotNull(jsonPathEvaluator.get("id"));
				assertNotNull(jsonPathEvaluator.get("name"));
				assertNotNull(jsonPathEvaluator.get("reportGroupName"));
			}
			assertTrue(statuscode == response.getStatus());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * public void GetAllConfigReportTypes(String reURL, int statuscode) throws
	 * Exception { try { System.out.println(reURL); //RESTGetBearerToken(); client =
	 * Client.create(); webResource = client.resource(reURL); response =
	 * webResource.accept("application/json").type("application/json")
	 * .header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
	 * System.out.println(response.getStatus());
	 * System.out.println(response.getEntity(String.class).toString()); // JsonPath
	 * jsonPathEvaluator = ((ResponseBodyExtractionOptions) // response).jsonPath();
	 * 
	 * if (statuscode == 200) { System.out.println("verify the response"); //
	 * assertNotNull(jsonPathEvaluator.get("id"));
	 * assertEquals(response.getStatus(), 200);
	 * assertNotNull(response.getStatusInfo(), "OK");
	 * 
	 * }
	 * 
	 * if (statuscode == 400) { assertEquals(response.getStatus(), 404);
	 * assertNotNull(response.getStatusInfo(), "NOTFOUND"); }
	 * 
	 * if (statuscode == 403) { assertEquals(response.getStatus(), 403);
	 * assertNotNull(response.getStatusInfo(), "FORBIDDEN"); } assertTrue(statuscode
	 * == response.getStatus());
	 * 
	 * } catch (Exception e) { System.out.println(e.getMessage()); } }
	 */

	public void GetAllConfigReportTypes(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);

			if (statuscode == 401) {
				BearerToken = "";
			} else {
				RESTGetBearerToken();
			}
			Thread.sleep(50000);
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());

			if (statuscode == 200) {
				System.out.println("verify the response");
				// assertNotNull(jsonPathEvaluator.get("id"));
				assertEquals(response.getStatus(), 200);
				assertNotNull(response.getStatusInfo(), "OK");

			}

			if (statuscode == 400) {
				assertEquals(response.getStatus(), 404);
				assertNotNull(response.getStatusInfo(), "NOTFOUND");
			}

			if (statuscode == 403) {
				assertEquals(response.getStatus(), 403);
				assertNotNull(response.getStatusInfo(), "FORBIDDEN");
			}
			assertTrue(statuscode == response.getStatus());

			if (statuscode == 401) {
				assertEquals(response.getStatus(), 401);
				assertNotNull(response.getStatusInfo(), "Unauthorized");
			}
			assertTrue(statuscode == response.getStatus());

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void postconfigreportTypesstatus(String reURL, int reportTypeId, int statuscode, String PartitionUUID,
			String TaxpayerCode, String startdate, String enddate, String Jurisdiction, String customer)
			throws Exception {
		try {
			String company, serviceresponse;
			String RESTFilePath = xDownloadFiles("RESTAPI");
			System.out.println(reURL);
			// RESTGetBearerToken();
			BearerTokenPageSource();
			client = Client.create();
			webResource = client.resource(reURL);
			company = "{\"reportTypeId\": \"" + reportTypeId + "\",\"companyCode\": \"" + TaxpayerCode
					+ "\",\"customer\": \"" + customer + "\",\"jurisdiction\": \"" + Jurisdiction
					+ "\",\"startDate\": \"" + startdate + "\",\"endDate\":\"" + enddate + "\" }";

			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).header("Vertex-Context", PartitionUUID)
					.post(ClientResponse.class, company);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			// String z=response.getEntity(String.class).toString();
			/*
			 * String[] z1=z.split(":"); System.out.println(z1[0]);
			 * System.out.println(z1[1].split(":")); String[] z2=z1[1].split( ":");
			 * QueueserviceID=z1[1].split( "i")
			 */
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
			QueueserviceID = xReadJSON(RESTFilePath + "\\TaxpayerId.json", "id", "");
			System.out.println(QueueserviceID);
			if (response.getStatus() == 401) {
				System.out.println("Token Expired");
				BearerTokenPageSource();
			}
			if (statuscode == 201) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 201);
				assertNotNull(response.getStatusInfo(), "Created");

			}

			if (statuscode == 400) {
				assertEquals(response.getStatus(), 400);
				assertNotNull(response.getStatusInfo(), "Bad Request");
			}

			assertTrue(statuscode == response.getStatus());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public ClientResponse getallreportTypesstatus(String reURL, int statuscode) throws Exception {
		try {
			System.out.println(reURL);
			BearerTokenPageSource();
			Thread.sleep(50000);
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).header("Vertex-Context", PartitionUUID).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			if (statuscode == 200) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 200);
				assertNotNull(response.getStatusInfo(), "OK");
			}
			if (statuscode == 400) {
				assertEquals(response.getStatus(), 400);
				assertNotNull(response.getStatusInfo(), "Bad Request");
			}
			if (statuscode == 500) {
				assertEquals(response.getStatus(), 500);
				assertNotNull(response.getStatusInfo(), "Internal Server Error");
			}
			if (statuscode == 401) {
				assertEquals(response.getStatus(), 401);
				assertNotNull(response.getStatusInfo(), "Unauthorized");
				BearerTokenPageSource();
			}
			assertTrue(statuscode == response.getStatus());
			return response;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return response;
		}
	}

	public void getsinglereportTypesstatus(String reURL, int statuscode, String PublicFileId) throws Exception {
		try {
			System.out.println(reURL);
			BearerTokenPageSource();
			client = Client.create();
			webResource = client.resource(reURL + "/" + PublicFileId);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			if (statuscode == 200) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 200);
				assertNotNull(response.getStatusInfo(), "OK");
			}
			assertTrue(statuscode == response.getStatus());

			if (statuscode == 400) {
				assertEquals(response.getStatus(), 400);
				assertNotNull(response.getStatusInfo(), "Bad Request");
			}
			if (statuscode == 500) {
				assertEquals(response.getStatus(), 500);
				assertNotNull(response.getStatusInfo(), "Internal Server Error");
			}
			if (statuscode == 401) {
				assertEquals(response.getStatus(), 401);
				assertNotNull(response.getStatusInfo(), "Unauthorized");
				BearerTokenPageSource();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deletereportstatus(String reURL, int statuscode, String PublicFileId) throws Exception {
		try {
			System.out.println(reURL);
			// RESTGetBearerToken();
			BearerTokenPageSource();
			client = Client.create();
			webResource = client.resource(reURL + "/" + PublicFileId);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).delete(ClientResponse.class);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			if (statuscode == 204) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 204);
				assertNotNull(response.getStatusInfo(), "No Content");
			}
			if (statuscode == 400) {
				assertEquals(response.getStatus(), 400);
				assertNotNull(response.getStatusInfo(), "Bad Request");
			}
			if (statuscode == 404) {
				assertEquals(response.getStatus(), 404);
				assertNotNull(response.getStatusInfo(), "Not Found");
			}
			if (statuscode == 401) {
				assertEquals(response.getStatus(), 401);
				assertNotNull(response.getStatusInfo(), "Unauthorized");
				BearerTokenPageSource();
			}
			assertTrue(statuscode == response.getStatus());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void PostTransAndCompReportTypesStatus(String reURL, int reportTypeId, int statuscode, String PartitionUUID,
			String TaxpayerCode, String jurisdiction, String startdate, String endDate) throws Exception {
		try {
			String company, serviceresponse;
			String RESTFilePath = xDownloadFiles("RESTAPI");
			System.out.println(reURL);
			// RESTGetBearerToken();
			BearerTokenPageSource();
			client = Client.create();
			webResource = client.resource(reURL);
			company = "{\"reportTypeId\": \"" + reportTypeId + "\",\"companyCode\": \"" + TaxpayerCode
					+ "\",\"customer\": \"\",\"jurisdiction\": \"" + jurisdiction + "\",\"startDate\": \"" + startdate
					+ "\",\"endDate\": \"" + endDate + "\" }";

			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).header("Vertex-Context", PartitionUUID)
					.post(ClientResponse.class, company);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
			QueueserviceID = xReadJSON(RESTFilePath + "\\TaxpayerId.json", "id", "");
			System.out.println(QueueserviceID);
			if (response.getStatus() == 401) {
				System.out.println("Token Expired");
				BearerTokenPageSource();
			}
			if (statuscode == 201) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 201);
				assertNotNull(response.getStatusInfo(), "Created");

			}

			if (statuscode == 400) {
				assertEquals(response.getStatus(), 400);
				assertNotNull(response.getStatusInfo(), "Bad Request");
			}

			assertTrue(statuscode == response.getStatus());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void PostTxabilityReportStatus(String reURL, int statuscode, String PartitionUUID, String TaxpayerCode,
			String jurisdictionID, String startdate) throws Exception {
		try {
			String company, serviceresponse;
			String RESTFilePath = xDownloadFiles("RESTAPI");
			System.out.println(reURL);

			if (statuscode == 401) {
				BearerToken = "";
			} else {
				RESTGetBearerToken();
			}
			client = Client.create();
			webResource = client.resource(reURL);
			company = "{\"companyCode\": \"" + TaxpayerCode + "\",\"jurisdiction\": \"[" + jurisdictionID
					+ "]\",\"startDate\": \"" + startdate + "\"}";
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).header("Vertex-Context", PartitionUUID)
					.post(ClientResponse.class, company);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
			QueueserviceID = xReadJSON(RESTFilePath + "\\TaxpayerId.json", "id", "");
			System.out.println(QueueserviceID);
			if (statuscode == 201) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 201);
				assertNotNull(response.getStatusInfo(), "Created");
			}

			if (statuscode == 400) {
				assertEquals(response.getStatus(), 400);
				assertNotNull(response.getStatusInfo(), "Bad Request");
			}
			if (statuscode == 500) {
				assertEquals(response.getStatus(), 500);
				assertNotNull(response.getStatusInfo(), "Internal Server Error");
			}
			if (statuscode == 401) {
				assertEquals(response.getStatus(), 401);
				assertNotNull(response.getStatusInfo(), "Unauthorized");
			}

			assertTrue(statuscode == response.getStatus());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void GetAdjustmentFileformat(String reURL, int statuscode) throws Exception {
		try {

			System.out.println(reURL);
			// RESTGetBearerToken();
			BearerTokenPageSource();
			client = Client.create();
			webResource = client.resource(reURL);
			response = webResource.accept("application/json").type("application/json")
					.header("Vertex-Context", PartitionUUID).header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			System.out.println(BearerToken);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			// JsonPath jsonPathEvaluator = ((ResponseBodyExtractionOptions)
			// response).jsonPath();

			if (statuscode == 200) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 200);
				assertNotNull(response.getStatusInfo(), "OK");
			}

			if (statuscode == 400) {
				assertEquals(response.getStatus(), 404);
				assertNotNull(response.getStatusInfo(), "NOTFOUND");
			}

			if (statuscode == 403) {
				assertEquals(response.getStatus(), 403);
				assertNotNull(response.getStatusInfo(), "FORBIDDEN");
			}

			if (statuscode == 401) {
				assertEquals(response.getStatus(), 401);
				assertNotNull(response.getStatusInfo(), "Unauthorized");
				BearerTokenPageSource();
			}
			assertTrue(statuscode == response.getStatus());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void EUOSSData(String reURL, String reportTypeId, String statuscode, String PartitionUUID,
			String TaxpayerCode, String startdate, String endDate, String exchangeratedate, String registrationScheme,
			String filingCurrency, String filename) throws Exception {
		try {
			String serviceresponse;
			String filePath = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
			filePath = xCSVUDateUpdation(AdjustmentFilePath + "\\" + filename);
			// RESTGetBearerToken();
			client = Client.create();
			WebResource resource = Client.create().resource(reURL);
			FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
			@SuppressWarnings("resource")
			FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
			FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("reportTypeId", reportTypeId)
					.field("companyCode", TaxpayerCode).field("startDate", startdate).field("endDate", endDate)
					.field("exchangeRateDate", exchangeratedate).field("registrationScheme", registrationScheme)
					.field("filingCurrency", filingCurrency).bodyPart(filePart);
			response = resource.type(MediaType.MULTIPART_FORM_DATA).header(HttpHeaders.AUTHORIZATION, BearerToken)
					.header("Vertex-Context", PartitionUUID).post(ClientResponse.class, multipart);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
			QueueserviceID = xReadJSON(RESTFilePath + "\\TaxpayerId.json", "id", "");
			System.out.println(QueueserviceID);
			if (response.getStatus() == 201) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 201);
				assertNotNull(response.getStatusInfo(), "Created");

			}

			if (response.getStatus() == 400) {
				assertEquals(response.getStatus(), 400);
				assertNotNull(response.getStatusInfo(), "Bad Request");
			}

			assertTrue(200 == response.getStatus());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("resource")
	public void PostAdjustmentFile(String reURL, String messageType, String fileFormat, String fileName, int statuscode,
			String PartitionUUID) throws Exception {
		try {
			String filePath = "";
			String serviceresponse;
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
			filePath = xCSVUDateUpdation(AdjustmentFilePath + "\\" + fileName + ".csv");
			System.out.println(reURL);
			// RESTGetBearerToken();
			client = Client.create();
			webResource = client.resource(reURL);
			WebResource resource = Client.create().resource(reURL);
			FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
			FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
			FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("foo", "bar").bodyPart(filePart);
			multipart = formDataMultiPart.field("fileFormat", fileFormat, MediaType.MULTIPART_FORM_DATA_TYPE);
			multipart = formDataMultiPart.field("messageType", messageType, MediaType.MULTIPART_FORM_DATA_TYPE);

			response = resource.type(MediaType.MULTIPART_FORM_DATA).header(HttpHeaders.AUTHORIZATION, BearerToken)
					.post(ClientResponse.class, multipart);

			serviceresponse = response.getEntity(String.class);
			System.out.println(response.getEntity(String.class).toString());
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "AdjFileUploadStatus.json");
			QueueserviceID = xReadJSON(RESTFilePath + "\\AdjFileUploadStatus.json", "id", "");
			System.out.println(QueueserviceID);
			if (statuscode == 201) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 201);
				assertNotNull(response.getStatusInfo(), "Created");

			}

			if (statuscode == 400) {
				assertEquals(response.getStatus(), 400);
				assertNotNull(response.getStatusInfo(), "Bad Request");
			}

			assertTrue(statuscode == response.getStatus());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void AdjustmentFileAPI(String reURL, String messageType, String fileFormat, String fileName, int statuscode,
			String PartitionUUID) throws Exception {
		try {
			String serviceresponse;
			String filePath = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
			filePath = xCSVUDateUpdation(AdjustmentFilePath + "\\" + fileName + ".csv");
			// RESTGetBearerToken();
			client = Client.create();
			WebResource resource = Client.create().resource(reURL);
			FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
			@SuppressWarnings("resource")
			FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
			FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);
			response = resource.type(MediaType.MULTIPART_FORM_DATA).header(HttpHeaders.AUTHORIZATION, BearerToken)
					.header("Vertex-Context", PartitionUUID).post(ClientResponse.class, multipart);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
			QueueserviceID = xReadJSON(RESTFilePath + "\\TaxpayerId.json", "id", "");
			System.out.println(QueueserviceID);
			if (response.getStatus() == 201) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 201);
				assertNotNull(response.getStatusInfo(), "Created");

			}

			if (response.getStatus() == 400) {
				assertEquals(response.getStatus(), 400);
				assertNotNull(response.getStatusInfo(), "Bad Request");
			}

			assertTrue(200 == response.getStatus());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void EUOSSData(String reURL, String fileFormat, String messageType, String statuscode, String PartitionUUID,
			String filename) throws Exception {
		try {
			String serviceresponse;
			String filePath = "";
			String RESTFilePath = xDownloadFiles("RESTAPI");
			String AdjustmentFilePath = xUploadAdjustmentFile("AdjustmentFiles");
			filePath = xCSVUDateUpdation(AdjustmentFilePath + "\\" + filename + ".csv");
			// RESTGetBearerToken();
			BearerTokenPageSource();
			client = Client.create();
			WebResource resource = Client.create().resource(reURL);
			FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
			@SuppressWarnings("resource")
			FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
			FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("fileFormat", fileFormat)
					.field("messageType", messageType).bodyPart(filePart);
			response = resource.type(MediaType.MULTIPART_FORM_DATA).header(HttpHeaders.AUTHORIZATION, BearerToken)
					.header("Vertex-Context", PartitionUUID).post(ClientResponse.class, multipart);
			System.out.println(response.getStatus());
			System.out.println(response.getEntity(String.class).toString());
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, RESTFilePath, "TaxpayerId.json");
			QueueserviceID = xReadJSON(RESTFilePath + "\\TaxpayerId.json", "id", "");
			System.out.println(QueueserviceID);
			if (response.getStatus() == 401) {
				System.out.println("Token Expired");
				BearerTokenPageSource();
			}
			if (response.getStatus() == 201) {
				System.out.println("verify the response");
				assertEquals(response.getStatus(), 201);
				assertNotNull(response.getStatusInfo(), "Created");

			}

			if (response.getStatus() == 400) {
				assertEquals(response.getStatus(), 400);
				assertNotNull(response.getStatusInfo(), "Bad Request");
			}

			assertTrue(200 == response.getStatus());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void APIAdjustmentFileFormat(String FileformatId) {
		String AuthUrl = null, serviceresponse;
		try {
			String RESTFilePath = xDownloadFiles("RESTAPI");
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://adjustment-file-qa.platqa.vtxqa.net/adjustmentfiletemplate/" + FileformatId + "";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://adjustment-file-dev.platdev.vtxdev.net/adjustmentfiletemplate/" + FileformatId + "";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://adjustment-file-stage.platstage.vtxstage.net/adjustmentfiletemplate/" + FileformatId
						+ "";
			}
			BearerTokenPageSource();
			client = Client.create();
			webResource = client.resource(AuthUrl);
			response = webResource.accept("application/json").type("application/json")
					.header(HttpHeaders.AUTHORIZATION, BearerToken).get(ClientResponse.class);
			serviceresponse = response.getEntity(String.class);
			System.out.println(BearerToken);
			System.out.println(response.getStatus());

			writeCSVFile(serviceresponse, RESTFilePath, "downloadAdjustmentFileTemplate.csv");
			assertTrue(response.getStatus() == 200);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
