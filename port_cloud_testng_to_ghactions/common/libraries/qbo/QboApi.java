package common.libraries.qbo;

import org.openqa.selenium.WebDriver;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import common.libraries.generic.CommonFunctions;

public class QboApi extends CommonFunctions {

	public QboApi(WebDriver driver) throws Exception {
	}

	public String AccessToken(String Usr, String Pwd, String FolderName) throws Exception {
		String status = "";
		try {
			String AuthUrl = "";
			String url, Body, serviceresponse, Token;
			if (Environment.equalsIgnoreCase("QA")) {
				AuthUrl = "https://qaauth.vertexsmb.com";
			} else if (Environment.equalsIgnoreCase("DEV")) {
				AuthUrl = "https://devauth.vertexsmb.com";
			} else if (Environment.equalsIgnoreCase("STAGE")) {
				AuthUrl = "https://stageauth.vertexsmb.com";
			}
			url = AuthUrl + "/identity/connect/token";
			client = Client.create();
			webResource = client.resource(url);
			Body = "grant_type=password&scope=vtms-public-api openid profile&client_id=pub-api-qboc&client_secret=132cd21c9b3e4af38cf540707aabf09f&username="
					+ Usr + "&password=" + Pwd + "";
			response = webResource.accept("application/x-www-form-urlencoded").type("application/x-www-form-urlencoded")
					.post(ClientResponse.class, Body);
			System.out.println("AccessTokenResponse=" + response.getStatus());
			if (response.getStatus() == 500 || response.getStatus() == 409 || response.getStatus() == 404
					|| response.getStatus() == 403 || response.getStatus() == 401 || response.getStatus() == 400) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			serviceresponse = response.getEntity(String.class);
			writeJsonFile(serviceresponse, FolderName, "AccessToken.json");
			Token = xReadJSON(FolderName + "\\AccessToken.json", "access_token", "");
			if (Token == null) {
				status = status + "Fail;BearerToken is NULL";
			}
			BearerToken = "Bearer " + Token;
		} catch (Exception e) {
			return e.getMessage();
		}
		return BearerToken;
	}

	/*
	 * public String QBOAPICalTax(String value) throws Exception { String status =
	 * ""; try { String[] Data = value.split(";"); int stRow, endRow, Data_Row;
	 * Sheet QBOAPI = xGetdtSheet(value); Workbook testscriptWorkbook =
	 * xGetWorkBook(value); Sheet CalTax =
	 * testscriptWorkbook.getSheet("CalculateTaxesProUser"); ConfigurePage CNP =
	 * PageFactory.initElements(driver, ConfigurePage.class); String QBOFilePath =
	 * xDownloadFiles("QBOAPI"); int Client_Id = 1, Secret = 2, APIUserName = 3,
	 * Password = 4, TaxCalculation = 5, CalTaxSheet = 6, AddressValidation = 7,
	 * Subscriptionlevel = 8, Subscription_Type = 9, Companies = 10, Company_Name =
	 * 11; int Trans_Date = 1, Trans_No = 2, Taxpayers = 3, Customers = 4, Locations
	 * = 5, S_State = 6, S_ZIP = 7, Add_Products_YN = 8, Products_Row = 9, Products
	 * = 10, Prod_Price = 11, Prod_Qty = 12, Total_Tax = 13, Date_Tomorrow = 14,
	 * Total = 15, AO_City = 16, AOVA_CITY = 17, AOError = 18, To_Street_Add = 19,
	 * To_City = 20, From_Street_Add = 21, From_City = 22, From_State = 23, From_ZIP
	 * = 24, Unpublished_Refresh = 25, To_Country = 26, From_Country = 27,
	 * Discount_Codes = 28, Discount_Value = 29, Dollar_Amt = 30, Shipping_Term =
	 * 31, Add_Additional = 32, Physicalorigin = 33, AdminDestination = 34,
	 * orderreceivedtaken = 35, Rmve_Custname = 36; String Usr, Pwd, BToken,
	 * Company, DocumentNumber, TransactionDate, PostingDate, State, ZipCode,
	 * ProdPrice, ProdQty, TaxPayer, CalculateTax, serviceresponse, Token,
	 * EnvironType, AddValidation; String url = ""; if (Data[2].contains(":")) {
	 * String[] stdata = Data[2].split(":"); stRow = Integer.parseInt(stdata[0]);
	 * endRow = Integer.parseInt(stdata[1]); } else { stRow = 2; endRow =
	 * Data.length - 1; } for (int i = stRow; i <= endRow; i++) { if
	 * (Data[2].contains(":")) { Data_Row = i; } else { Data_Row =
	 * Integer.parseInt(Data[i]); } Usr = QBOAPI.getCell(APIUserName,
	 * Data_Row).getContents(); Pwd = QBOAPI.getCell(Password,
	 * Data_Row).getContents(); // Getting Bearer Token BToken = AccessToken(Usr,
	 * Pwd, QBOFilePath); System.out.println("BearerToken=" + BToken); //
	 * CalculateTax if (QBOAPI.getCell(TaxCalculation,
	 * Data_Row).getContents().equalsIgnoreCase("Yes")) { EnvironType = Environment;
	 * switch (EnvironType) { case "QA": url =
	 * "https://qaapi.vertexsmb.com/public/v1/Transaction/taxCalculate"; break; case
	 * "DEV": url =
	 * "https://devapi.vertexsmb.com/public/v1/Transaction/taxCalculate"; break;
	 * case "STAGE": url =
	 * "https://stageapi.vertexsmb.com/public/v1/Transaction/taxCalculate"; break; }
	 * client = Client.create(); webResource = client.resource(url); int CalTaxPro =
	 * Integer.parseInt(QBOAPI.getCell(CalTaxSheet, Data_Row).getContents());
	 * Company = CalTax.getCell(Taxpayers, CalTaxPro).getContents(); DocumentNumber
	 * = CalTax.getCell(Trans_No, CalTaxPro).getContents(); TransactionDate =
	 * CalTax.getCell(Trans_Date, CalTaxPro).getContents(); PostingDate =
	 * CalTax.getCell(Trans_Date, CalTaxPro).getContents(); State =
	 * CalTax.getCell(S_State, CalTaxPro).getContents(); ZipCode =
	 * CalTax.getCell(S_ZIP, CalTaxPro).getContents(); ProdPrice =
	 * CalTax.getCell(Prod_Price, CalTaxPro).getContents(); ProdQty =
	 * CalTax.getCell(Prod_Qty, CalTaxPro).getContents(); TaxPayer =
	 * CalTax.getCell(Taxpayers, CalTaxPro).getContents(); CalculateTax =
	 * "{\"messageType\":2,\"documentNumber\":\"" + DocumentNumber +
	 * "\",\"transactionDate\":\"" + TransactionDate + "\",\"postingDate\":\"" +
	 * PostingDate + "\"," +
	 * "\"customer\":{\"destination\":{\"address1\":null,\"address2\":null,\"city\":null,\"state\":\""
	 * + State + "\",\"zip\":\"" + ZipCode + "\"},\"adminDestination\":null}," +
	 * "\"seller\":{\"company\":\"" + TaxPayer +
	 * "\",\"origin\":{\"city\":\"West Chester\",\"state\":\"PA\",\"zip\":\"19380\"}},"
	 * + "\"lineItems\":[{\"linenumber\":1,\"unitPrice\":0.0,\"extendedPrice\":\"" +
	 * ProdPrice + "\"," + "\"quantity\":\"" + ProdQty +
	 * "\",},{\"linenumber\":2,\"unitPrice\":0.0,\"extendedPrice\":null,\"quantity\":0.0,}],}";
	 * System.out.println("CalTaxJson=" + CalculateTax); response =
	 * webResource.accept("application/json").type("application/json")
	 * .header(HttpHeaders.AUTHORIZATION, BToken).post(ClientResponse.class,
	 * CalculateTax); System.out.println("CalTaxResponse=" + response.getStatus());
	 * if (response.getStatus() == 500 || response.getStatus() == 409 ||
	 * response.getStatus() == 404 || response.getStatus() == 403 ||
	 * response.getStatus() == 401 || response.getStatus() == 400 ||
	 * response.getStatus() == 405) { status = "Fail;CalTaxResponse"; throw new
	 * RuntimeException("Failed : HTTP error code : " + response.getStatus()); } //
	 * JSon File serviceresponse = response.getEntity(String.class);
	 * writeJsonFile(serviceresponse, QBOFilePath, "CalTax.json"); // File directory
	 * = new File(".//"+"DownloadingFiles"); Token = xReadJSON(QBOFilePath +
	 * "\\CalTax.json", "message", ""); System.out.println("CalToken=" + Token); if
	 * (Token.equalsIgnoreCase("Success")) { } else { status = "Fail;CalToken"; } }
	 * // Address Validation if (QBOAPI.getCell(AddressValidation,
	 * Data_Row).getContents().equalsIgnoreCase("Yes")) { EnvironType = Environment;
	 * switch (EnvironType) { case "QA": url =
	 * "https://qaapi.vertexsmb.com/public/v1/Address/Validate"; break; case "DEV":
	 * url = "https://devapi.vertexsmb.com/public/v1/Address/Validate"; break; case
	 * "STAGE": url = "https://stageapi.vertexsmb.com/public/v1/Address/Validate";
	 * break; } client = Client.create(); webResource = client.resource(url);
	 * AddValidation =
	 * "{\"Address1\":\"112 West Chester Pike\",\"Address2\":null,\"City\":\"West Chester\",\"State\":\"PA\",\"Zip\":\"19380\",\"Country\":null,\"Date\":null,\"ClientId\":0}"
	 * ; System.out.println("AddValidationJson=" + AddValidation); response =
	 * webResource.accept("application/json").type("application/json")
	 * .header(HttpHeaders.AUTHORIZATION, BToken).post(ClientResponse.class,
	 * AddValidation); System.out.println("AddValidationResponse=" +
	 * response.getStatus()); if (response.getStatus() == 500 ||
	 * response.getStatus() == 409 || response.getStatus() == 404 ||
	 * response.getStatus() == 403 || response.getStatus() == 401 ||
	 * response.getStatus() == 400) { status = "Fail;AddValidationResponse"; throw
	 * new RuntimeException("Failed : HTTP error code : " + response.getStatus()); }
	 * // JSon File serviceresponse = response.getEntity(String.class);
	 * writeJsonFile(serviceresponse, QBOFilePath, "AddressValidation.json"); //
	 * File directory = new File(".//"+"DownloadingFiles"); Token =
	 * xReadJSON(QBOFilePath + "\\AddressValidation.json", "isValid", "");
	 * System.out.println("AddValidationToken=" + Token); if
	 * (Token.equalsIgnoreCase("true")) { } else { status =
	 * "Fail;AddValidationToken"; } } // SubscriptionLevel if
	 * (QBOAPI.getCell(Subscriptionlevel,
	 * Data_Row).getContents().equalsIgnoreCase("Yes")) { EnvironType = Environment;
	 * switch (EnvironType) { case "QA": url =
	 * "https://qaapi.vertexsmb.com/public/v1/client/SubscriptionLevel"; break; case
	 * "DEV": url =
	 * "https://devapi.vertexsmb.com/public/v1/client/SubscriptionLevel"; break;
	 * case "STAGE": url =
	 * "https://stageapi.vertexsmb.com/public/v1/client/SubscriptionLevel"; break; }
	 * client = Client.create(); webResource = client.resource(url); response =
	 * webResource.accept("application/x-www-form-urlencoded")
	 * .type("application/x-www-form-urlencoded").header(HttpHeaders.AUTHORIZATION,
	 * BToken) .get(ClientResponse.class); System.out.println("SubLevelResponse=" +
	 * response.getStatus()); if (response.getStatus() == 500 ||
	 * response.getStatus() == 409 || response.getStatus() == 404 ||
	 * response.getStatus() == 403 || response.getStatus() == 401 ||
	 * response.getStatus() == 400) { status = "Fail;SubLevelResponse"; throw new
	 * RuntimeException("Failed : HTTP error code : " + response.getStatus()); } //
	 * JSon File serviceresponse = response.getEntity(String.class);
	 * writeJsonFile(serviceresponse, QBOFilePath, "SubscriptionLevel.json"); //
	 * File directory = new File(".//"+"DownloadingFiles"); Token =
	 * xReadJSON(QBOFilePath + "\\SubscriptionLevel.json", "name", "");
	 * System.out.println("SubLevelToken=" + Token); if
	 * (Token.equalsIgnoreCase(QBOAPI.getCell(Subscription_Type,
	 * Data_Row).getContents())) { } else { status = "Fail;SubLevelToken"; } } //
	 * Companies if (QBOAPI.getCell(Companies,
	 * Data_Row).getContents().equalsIgnoreCase("Yes")) { EnvironType = Environment;
	 * switch (EnvironType) { case "QA": url =
	 * "https://qaapi.vertexsmb.com/public/v1/client/companies"; break; case "DEV":
	 * url = "https://devapi.vertexsmb.com/public/v1/client/companies"; break; case
	 * "STAGE": url = "https://stageapi.vertexsmb.com/public/v1/client/companies";
	 * break; } client = Client.create(); webResource = client.resource(url);
	 * response = webResource.accept("application/x-www-form-urlencoded")
	 * .type("application/x-www-form-urlencoded").header(HttpHeaders.AUTHORIZATION,
	 * BToken) .get(ClientResponse.class); System.out.println("CompaniesResponse=" +
	 * response.getStatus()); if (response.getStatus() == 500 ||
	 * response.getStatus() == 409 || response.getStatus() == 404 ||
	 * response.getStatus() == 403 || response.getStatus() == 401 ||
	 * response.getStatus() == 400) { status = "Fail;CompaniesResponse"; throw new
	 * RuntimeException("Failed : HTTP error code : " + response.getStatus()); } //
	 * JSon File serviceresponse = response.getEntity(String.class);
	 * writeJsonFile(serviceresponse, QBOFilePath, "Companies.json"); // File
	 * directory = new File(".//"+"DownloadingFiles"); Token = xReadJSON(QBOFilePath
	 * + "\\Companies.json", "name", ""); System.out.println("CompanyToken=" +
	 * Token); if (Token.equalsIgnoreCase(QBOAPI.getCell(Company_Name,
	 * Data_Row).getContents())) { } else { status = "Fail;CompanyToken"; } }
	 * 
	 * if (status == "") { status = "Pass"; } } } catch (Exception e) { return
	 * e.getMessage() + status; }
	 * 
	 * return status; }
	 */
}
