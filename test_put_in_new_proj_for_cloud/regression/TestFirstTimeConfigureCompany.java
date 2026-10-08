package com.vertexinc.cloud.test.regression;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vertexinc.cloud.test.pages.*;
import io.github.nezonium.annotations.UseDriver;
import io.github.nezonium.util.WebBrowser;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TestFirstTimeConfigureCompany {

    public static Map<?,?> FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA = new HashMap<>();

    @BeforeAll
    public static void setupTestData() {
        try {
            JsonParser parser = new JsonParser();
            JsonObject raw_test_data = (JsonObject) parser.parse(new FileReader(String.valueOf(Paths.get("src/test/resources/FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.json.json"))));
            JsonObject credential_data = (JsonObject) parser.parse(new FileReader(String.valueOf(Paths.get("src/test/resources/.test-properties.json"))));
            FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.putAll(new ObjectMapper().readValue(credential_data.toString(), Map.class));
            FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.putAll(new ObjectMapper().readValue(raw_test_data.toString(), Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @UseDriver(browser = {WebBrowser.CHROME})
    public void TestFirstTimeConfigureCompany(WebBrowser browser){
        //Given:
        String userEmail = FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("invited_user_email").toString();
        String userPassword = FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("registration_password").toString();
        CloudApplicationWelcomeScreen applicationWelcomeScreen = new CloudApplicationWelcomeScreen();

        //And I {log into the Cloud Client portal as the invited user}
        applicationWelcomeScreen.enterEmailField(userEmail);
        applicationWelcomeScreen.clickContinueButton();
        CloudApplicationEmailConfAndPasswordPage emailConfAndPasswordPage = new CloudApplicationEmailConfAndPasswordPage(applicationWelcomeScreen.getDriverHandler());
        emailConfAndPasswordPage.enterPassword(userPassword);
        emailConfAndPasswordPage.clickContinueButton();

        //And I {navigate to configure a First time company}
        CloudClientPortalPortalHomePage cloudClientPortalHomePage = new CloudClientPortalPortalHomePage(emailConfAndPasswordPage.getDriverHandler());
        cloudClientPortalHomePage.navigateTo("https://devportal.vertexsmb.com/config/#/companies/new/true");

        //And I {fill the form to register a first time company}
        CloudApplicationConfigureCompanyPage configureCompanyPage = new CloudApplicationConfigureCompanyPage(cloudClientPortalHomePage.getDriverHandler());
        configureCompanyPage.setCountry(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_country").toString());
        configureCompanyPage.setCompanyCode(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_code").toString());
        configureCompanyPage.setFederalIdNo(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_federal_id_number").toString());
        configureCompanyPage.setCompanyName_FilingEntity(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_name_filing_entity").toString());
        configureCompanyPage.setFirstName(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_first_name").toString());
        configureCompanyPage.setLastName(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_last_name").toString());
        configureCompanyPage.setJobTitle(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_job_title").toString());
        configureCompanyPage.setEmail(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_email_address").toString());
        configureCompanyPage.setPhone(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_phone").toString());
        configureCompanyPage.setAddress(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_address").toString());
        configureCompanyPage.setCity(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_city").toString());
        configureCompanyPage.setState(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_state").toString());
        configureCompanyPage.setZipPostalCode(FIRST_TIME_CONFIGURE_COMPANY_TEST_DATA.get("first_time_configure_company_zip").toString());
        configureCompanyPage.clickDiscountsLink();
        configureCompanyPage.clickAddCompanyButton();

        //Validate First Time Company Address (If necessary)
        CloudApplicationConfigureCompanyValidationErrorPage configureCompanyValidationErrorPage = new CloudApplicationConfigureCompanyValidationErrorPage(configureCompanyPage.getDriverHandler());
        configureCompanyValidationErrorPage.clickYesConfirmAddressButton();
    }
}
