package com.vertexinc.cloud.test.regression;

import com.vertexinc.cloud.test.util.TestParameterInput;
import io.github.nezonium.annotations.ScreenShotOnFail;
import io.github.nezonium.annotations.UseDriver;
import io.github.nezonium.util.SeleniumJupiterProvider;
import io.github.nezonium.util.WebBrowser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

public class TestLoginPage {

//    @UseDriver(browser = {WebBrowser.CHROME, WebBrowser.EDGE, WebBrowser.FIREFOX})
//    public void Test_SendStringsToUsernameField(WebBrowser webBrowser, @TestParameterInput(string = {"testParameterInput[0]", "testParameterInput[1]"}) List<String> stringsToEnterIntoUsernameField) {
//        SeleniumJupiterProvider.setUp(webBrowser);
//        test_sendStringsToUsernameField(stringsToEnterIntoUsernameField);
//    }
//
//        @UseDriver(browser = {WebBrowser.CHROME, WebBrowser.EDGE, WebBrowser.FIREFOX})
//    public void Test_SendStringsToPasswordField(WebBrowser webBrowser, @TestParameterInput List<String> stringsToEnterIntoPasswordField) {
//        SeleniumJupiterProvider.setUp(webBrowser);
//        test_sendStringsToPasswordField(stringsToEnterIntoPasswordField);
//    }
//
//    @UseDriver(browser = {WebBrowser.CHROME, WebBrowser.EDGE, WebBrowser.FIREFOX})
//    public void Test_LoginWithDifferentActors(WebBrowser webBrowser, @TestParameterInput Map<String, String> actors) {
//        SeleniumJupiterProvider.setUp(webBrowser);
//        //Map of username/password pairs. Some should be valid users. actors.put("vertuser2@vertex.local","******")
//        test_Login(actors);
//    }
    @UseDriver(browser = {WebBrowser.CHROME,WebBrowser.FIREFOX})
    public void test(@TestParameterInput(strings = {"hello","its me"}) String myStringsList){
        test_printStrings(myStringsList);
    }
    private void test_printStrings(String stringsToPrint){
        System.out.println(stringsToPrint);
//                stringsToPrint.forEach(System.out::println);
    }

//    private void test_sendStringsToUsernameField(List<String> stringsToEnterIntoUsernameField) {
//        stringsToEnterIntoUsernameField.forEach(string -> {
//            CloudApplicationWelcomeScreen loginPage = new CloudApplicationWelcomeScreen();
//            loginPage.enterUsernameField(string);
//            Assertions.assertEquals(loginPage.getUsernameField(), string);
//        });
//    }
//
//    private void test_sendStringsToPasswordField(List<String> stringsToEnterIntoPasswordField) {
//        stringsToEnterIntoPasswordField.forEach(string -> {
//            CloudLoginPage loginPage = new CloudLoginPage();
//            loginPage.enterPasswordField(string);
//            Assertions.assertEquals(loginPage.getPasswordField(), string);
//        });
//    }
//
//    private void test_Login(Map<String, String> actors) {
//        actors.forEach((user,password)->{
//            CloudLoginPage loginPage = new CloudLoginPage();
//            loginPage.enterUsernameField(user);
//            loginPage.enterPasswordField(password);
//            Page pageRightAfterLogin =  loginPage.clickLoginButton();
//            //Assert url is '' or item exists on page.
//        });
//    }
}
