package com.vertexinc.cloud.test.targeted;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vertexinc.cloud.test.pages.*;
import io.github.nezonium.annotations.UseDriver;
import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.SeleniumJupiterProvider;
import io.github.nezonium.util.WebBrowser;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RETUI1114 {
    

    @UseDriver(browser = {WebBrowser.CHROME})
    public void myTestExample(WebBrowser browser)  {
        //Given
        SeleniumJupiterProvider.setUp(browser);
        DriverHandler.setTimeOutWaitTimeInMillis(90000);
        SeleniumJupiterProvider.getInstance().getConfig().getManager().clearDriverCache();

        //Given:
        String email = RETUI1114_TEST_DATA.get("cloud-login-username-admin").toString();
        String password = RETUI1114_TEST_DATA.get("cloud-login-password-admin").toString();
        String invitedUserEmail = RETUI1114_TEST_DATA.get("invited_user_email").toString();
        String invitedUserPassword = RETUI1114_TEST_DATA.get("registration_password").toString();

        //When:
        //I {Navigate to Cloud Admin Portal}
        CloudApplicationWelcomeScreen applicationWelcomeScreen = new CloudApplicationWelcomeScreen();

        //Maximize window -- TODO tech debt concerning testing windows in different view sizes: mobile, minimized, maximized, etc.
        applicationWelcomeScreen.getDriverHandler().getDriver().manage().window().maximize();

        applicationWelcomeScreen.navigateTo(RETUI1114_TEST_DATA.get("test_environment_url").toString());
        applicationWelcomeScreen.enterEmailField(email);
        applicationWelcomeScreen.clickContinueButton();
        CloudApplicationEmailConfAndPasswordPage emailConfAndPasswordPage = new CloudApplicationEmailConfAndPasswordPage(applicationWelcomeScreen.getDriverHandler());
        emailConfAndPasswordPage.enterPassword(password);
        emailConfAndPasswordPage.clickContinueButton();

        //And I {Navigate to Invite new Client portal User}
        CloudAdminPortalHomeScreen cloudAdminPortalHomeScreen = new CloudAdminPortalHomeScreen(emailConfAndPasswordPage.getDriverHandler());
        cloudAdminPortalHomeScreen.clickInviteUsers();
        CloudApplicationAdminPortalAdminPortalInviteUsersPage cloudApplicationAdminPortalInviteUsersPage = new CloudApplicationAdminPortalAdminPortalInviteUsersPage(cloudAdminPortalHomeScreen.getDriverHandler());
        cloudApplicationAdminPortalInviteUsersPage.clickInviteUserButton();

        //And I {Fill in the form to invite a new Cloud Client Portal User}
        cloudApplicationAdminPortalInviteUsersPage.setRoleValue(RETUI1114_TEST_DATA.get("invited_user_role").toString());
        cloudApplicationAdminPortalInviteUsersPage.setAccountType(RETUI1114_TEST_DATA.get("invited_user_account_type").toString());
        cloudApplicationAdminPortalInviteUsersPage.setVarCPAFranchzName(RETUI1114_TEST_DATA.get("invited_user_var_cpa_franchz_name").toString());
        cloudApplicationAdminPortalInviteUsersPage.setOraclePartyNumber(RETUI1114_TEST_DATA.get("invited_user_oracle_party_number").toString());
        cloudApplicationAdminPortalInviteUsersPage.setClientName(RETUI1114_TEST_DATA.get("invited_user_client_name").toString());
        cloudApplicationAdminPortalInviteUsersPage.setEmail(RETUI1114_TEST_DATA.get("invited_user_email").toString());
        cloudApplicationAdminPortalInviteUsersPage.setCountry(RETUI1114_TEST_DATA.get("invited_user_country").toString());
        cloudApplicationAdminPortalInviteUsersPage.setAddress(RETUI1114_TEST_DATA.get("invited_user_street_address").toString());
        cloudApplicationAdminPortalInviteUsersPage.setAddress2(RETUI1114_TEST_DATA.get("invited_user_street_address2").toString());
        cloudApplicationAdminPortalInviteUsersPage.setCity(RETUI1114_TEST_DATA.get("invited_user_city").toString());
        cloudApplicationAdminPortalInviteUsersPage.setState(RETUI1114_TEST_DATA.get("invited_user_state").toString());
        cloudApplicationAdminPortalInviteUsersPage.setZip(RETUI1114_TEST_DATA.get("invited_user_zip").toString());
        cloudApplicationAdminPortalInviteUsersPage.setSubscription(RETUI1114_TEST_DATA.get("invited_user_subscription").toString());
        cloudApplicationAdminPortalInviteUsersPage.setReservedPodCheckBox(RETUI1114_TEST_DATA.get("invited_user_reserved_pod").toString());
        cloudApplicationAdminPortalInviteUsersPage.clickInviteNewUserButton();

        //And I {Register the invited user}
        cloudApplicationAdminPortalInviteUsersPage.clickRegistrationLink();
        RegistrationPage registrationPage = new RegistrationPage(cloudAdminPortalHomeScreen.getDriverHandler());
        registrationPage.setFirstName(RETUI1114_TEST_DATA.get("registration_first_name").toString());
        registrationPage.setLastName(RETUI1114_TEST_DATA.get("registration_last_name").toString());
        registrationPage.setPhoneNumber(RETUI1114_TEST_DATA.get("registration_phone_number").toString());
        registrationPage.setPassword(RETUI1114_TEST_DATA.get("registration_password").toString());
        registrationPage.setConfirmPassword(RETUI1114_TEST_DATA.get("registration_confirm_password").toString());
        registrationPage.setCountry(RETUI1114_TEST_DATA.get("registration_country").toString());
        registrationPage.setState(RETUI1114_TEST_DATA.get("registration_state").toString());
        registrationPage.clickFinishAndSave();

        //And I {log into the Cloud Client portal as the invited user}
        applicationWelcomeScreen.enterEmailField(invitedUserEmail);
        applicationWelcomeScreen.clickContinueButton();
        emailConfAndPasswordPage.enterPassword(invitedUserPassword);
        emailConfAndPasswordPage.clickContinueButton();

        //And I {navigate to configure a First time company}
        CloudClientPortalPortalHomePage cloudClientPortalHomePage = new CloudClientPortalPortalHomePage(registrationPage.getDriverHandler());
        cloudClientPortalHomePage.navigateTo("https://devportal.vertexsmb.com/config/#/companies/new/true");


        //And I {fill the form to register a first time company}
        CloudApplicationConfigureCompanyPage configureCompanyPage = new CloudApplicationConfigureCompanyPage(cloudClientPortalHomePage.getDriverHandler());
        configureCompanyPage.setCountry(RETUI1114_TEST_DATA.get("first_time_configure_company_country").toString());
        configureCompanyPage.setCompanyCode(RETUI1114_TEST_DATA.get("first_time_configure_company_code").toString());
        configureCompanyPage.setFederalIdNo(RETUI1114_TEST_DATA.get("first_time_configure_company_federal_id_number").toString());
        configureCompanyPage.setCompanyName_FilingEntity(RETUI1114_TEST_DATA.get("first_time_configure_company_name_filing_entity").toString());
        configureCompanyPage.setFirstName(RETUI1114_TEST_DATA.get("first_time_configure_company_first_name").toString());
        configureCompanyPage.setLastName(RETUI1114_TEST_DATA.get("first_time_configure_company_last_name").toString());
        configureCompanyPage.setJobTitle(RETUI1114_TEST_DATA.get("first_time_configure_company_job_title").toString());
        configureCompanyPage.setEmail(RETUI1114_TEST_DATA.get("first_time_configure_company_email_address").toString());
        configureCompanyPage.setPhone(RETUI1114_TEST_DATA.get("first_time_configure_company_phone").toString());
        configureCompanyPage.setAddress(RETUI1114_TEST_DATA.get("first_time_configure_company_address").toString());
        configureCompanyPage.setCity(RETUI1114_TEST_DATA.get("first_time_configure_company_city").toString());
        configureCompanyPage.setState(RETUI1114_TEST_DATA.get("first_time_configure_company_state").toString());
        configureCompanyPage.setZipPostalCode(RETUI1114_TEST_DATA.get("first_time_configure_company_zip").toString());
        configureCompanyPage.clickDiscountsLink();
        configureCompanyPage.clickAddCompanyButton();

        //Validate First Time Company Address If necessary
        CloudApplicationConfigureCompanyValidationErrorPage configureCompanyValidationErrorPage = new CloudApplicationConfigureCompanyValidationErrorPage(configureCompanyPage.getDriverHandler());
        configureCompanyValidationErrorPage.clickYesConfirmAddressButton();
        //Then

    }

    public static Map<?, ?> RETUI1114_TEST_DATA = new HashMap<>();

    @BeforeAll
    public static void setupTestData() {
        try {
            JsonParser parser = new JsonParser();
            JsonObject raw_test_data = (JsonObject) parser.parse(new FileReader(String.valueOf(Paths.get("src/test/resources/RETUI1114_TEST_DATA.json"))));
            JsonObject credential_data = (JsonObject) parser.parse(new FileReader(String.valueOf(Paths.get("src/test/resources/.test-properties.json"))));
            RETUI1114_TEST_DATA.putAll(new ObjectMapper().readValue(credential_data.toString(), Map.class));
            RETUI1114_TEST_DATA.putAll(new ObjectMapper().readValue(raw_test_data.toString(), Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
