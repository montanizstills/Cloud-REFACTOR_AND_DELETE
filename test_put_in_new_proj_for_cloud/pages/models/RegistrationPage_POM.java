package com.vertexinc.cloud.test.pages.models;

import org.openqa.selenium.By;

public interface RegistrationPage_POM {
    default By getCompanyNameField() {
        return By.cssSelector("input[name='clientName']");
    }

    default By getFirstNameField() {
        return By.cssSelector("input[name='firstName']");
    }

    default By getLastNameField() {
        return By.cssSelector("input[name='lastName']");
    }

    default By getPhoneNumberField() {
        return By.cssSelector("input[name='userPhone']");
    }

    default By getPasswordField() {
        return By.cssSelector("input[name='password']");
    }

    default By getConfirmPasswordField() {
        return By.cssSelector("input[name='passwordConfirm']");
    }

    default By getCountryField() {
        return By.cssSelector("select[name='country']");
    }

    default By getAddressField() {
        return By.cssSelector("input[name='address']");
    }

    default By getAddress2Field() {
        return By.cssSelector("input[name='address2']");
    }

    default By getCityField() {
        return By.cssSelector("input[name='city']");
    }

    default By getStateField() {
        return By.cssSelector("select[name='state']");
    }

    default By getZipField() {
        return By.cssSelector("input[name='postal']");
    }
    default By getFinishAndSaveButton(){
        return By.cssSelector("button[type='submit']");
    }
}
