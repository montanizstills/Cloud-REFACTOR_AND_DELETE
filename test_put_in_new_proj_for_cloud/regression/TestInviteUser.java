package com.vertexinc.cloud.test.regression;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vertexinc.cloud.test.pages.*;
import io.github.nezonium.annotations.UseDriver;
import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.SeleniumJupiterProvider;
import io.github.nezonium.util.WebBrowser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TestInviteUser {

    public static Map<?,?> INVITE_USER_TEST_DATA = new HashMap<>();

    @BeforeAll
    public static void setupTestData() {
        try {
            JsonParser parser = new JsonParser();
            JsonObject raw_test_data = (JsonObject) parser.parse(new FileReader(String.valueOf(Paths.get("src/test/resources/INVITE_USER_TEST_DATA.json"))));
            JsonObject credential_data = (JsonObject) parser.parse(new FileReader(String.valueOf(Paths.get("src/test/resources/.test-properties.json"))));
            INVITE_USER_TEST_DATA.putAll(new ObjectMapper().readValue(credential_data.toString(), Map.class));
            INVITE_USER_TEST_DATA.putAll(new ObjectMapper().readValue(raw_test_data.toString(), Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @UseDriver(browser= {WebBrowser.CHROME, Webdriver.Fifrefox})
    public void TestInviteUser(WebBrowser browser){
        //Given
        SeleniumJupiterProvider.setUp(browser);
        DriverHandler.setTimeOutWaitTimeInMillis(90000);
        SeleniumJupiterProvider.getInstance().getConfig().getManager().clearDriverCache();

        //Given:
        String email = INVITE_USER_TEST_DATA.get("cloud-login-username-admin").toString();
        String password = INVITE_USER_TEST_DATA.get("cloud-login-password-admin").toString();
        String invitedUserEmail = INVITE_USER_TEST_DATA.get("invited_user_email").toString();
        String invitedUserPassword = INVITE_USER_TEST_DATA.get("registration_password").toString();

        //When:
        //I {Navigate to Cloud Admin Portal}
        CloudApplicationWelcomeScreen applicationWelcomeScreen = new CloudApplicationWelcomeScreen();

        //Maximize window -- TODO tech debt concerning testing windows in different view sizes: mobile, minimized, maximized, etc.
        applicationWelcomeScreen.getDriverHandler().getDriver().manage().window().maximize();

        applicationWelcomeScreen.navigateTo(INVITE_USER_TEST_DATA.get("test_environment_url").toString());
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
        cloudApplicationAdminPortalInviteUsersPage.setRoleValue(INVITE_USER_TEST_DATA.get("invited_user_role").toString());
        cloudApplicationAdminPortalInviteUsersPage.setAccountType(INVITE_USER_TEST_DATA.get("invited_user_account_type").toString());
        cloudApplicationAdminPortalInviteUsersPage.setVarCPAFranchzName(INVITE_USER_TEST_DATA.get("invited_user_var_cpa_franchz_name").toString());
        cloudApplicationAdminPortalInviteUsersPage.setOraclePartyNumber(INVITE_USER_TEST_DATA.get("invited_user_oracle_party_number").toString());
        cloudApplicationAdminPortalInviteUsersPage.setClientName(INVITE_USER_TEST_DATA.get("invited_user_client_name").toString());
        cloudApplicationAdminPortalInviteUsersPage.setEmail(INVITE_USER_TEST_DATA.get("invited_user_email").toString());
        cloudApplicationAdminPortalInviteUsersPage.setCountry(INVITE_USER_TEST_DATA.get("invited_user_country").toString());
        cloudApplicationAdminPortalInviteUsersPage.setAddress(INVITE_USER_TEST_DATA.get("invited_user_street_address").toString());
        cloudApplicationAdminPortalInviteUsersPage.setAddress2(INVITE_USER_TEST_DATA.get("invited_user_street_address2").toString());
        cloudApplicationAdminPortalInviteUsersPage.setCity(INVITE_USER_TEST_DATA.get("invited_user_city").toString());
        cloudApplicationAdminPortalInviteUsersPage.setState(INVITE_USER_TEST_DATA.get("invited_user_state").toString());
        cloudApplicationAdminPortalInviteUsersPage.setZip(INVITE_USER_TEST_DATA.get("invited_user_zip").toString());
        cloudApplicationAdminPortalInviteUsersPage.setSubscription(INVITE_USER_TEST_DATA.get("invited_user_subscription").toString());
        cloudApplicationAdminPortalInviteUsersPage.setReservedPodCheckBox(INVITE_USER_TEST_DATA.get("invited_user_reserved_pod").toString());
        cloudApplicationAdminPortalInviteUsersPage.clickInviteNewUserButton();

        //And I {Register the invited user}
        cloudApplicationAdminPortalInviteUsersPage.clickRegistrationLink();
        RegistrationPage registrationPage = new RegistrationPage(cloudAdminPortalHomeScreen.getDriverHandler());
        registrationPage.setFirstName(INVITE_USER_TEST_DATA.get("registration_first_name").toString());
        registrationPage.setLastName(INVITE_USER_TEST_DATA.get("registration_last_name").toString());
        registrationPage.setPhoneNumber(INVITE_USER_TEST_DATA.get("registration_phone_number").toString());
        registrationPage.setPassword(INVITE_USER_TEST_DATA.get("registration_password").toString());
        registrationPage.setConfirmPassword(INVITE_USER_TEST_DATA.get("registration_confirm_password").toString());
        registrationPage.setCountry(INVITE_USER_TEST_DATA.get("registration_country").toString());
        registrationPage.setState(INVITE_USER_TEST_DATA.get("registration_state").toString());
        registrationPage.clickFinishAndSave();

        //And I {log into the Cloud Client portal as the invited user}
        applicationWelcomeScreen.enterEmailField(invitedUserEmail);
        applicationWelcomeScreen.clickContinueButton();
        emailConfAndPasswordPage.enterPassword(invitedUserPassword);
        emailConfAndPasswordPage.clickContinueButton();

        CloudClientPortalPortalHomePage cloudClientPortalPortalHomePage = new CloudClientPortalPortalHomePage(emailConfAndPasswordPage.getDriverHandler());

        Assertions.assertEquals(cloudClientPortalPortalHomePage.getCurrentURL(),INVITE_USER_TEST_DATA.get("test_environment_url").toString());
    }

}
