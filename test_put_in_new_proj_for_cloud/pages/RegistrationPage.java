package com.vertexinc.cloud.test.pages;

import com.vertexinc.cloud.test.pages.models.RegistrationPage_POM;
import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.Page;

public class RegistrationPage extends Page implements RegistrationPage_POM {
    public RegistrationPage(DriverHandler driverHandler) {
        super(driverHandler);
    }

    public void setFirstName(String firstName) {
        getDriverHandler().sendKeys(getFirstNameField(), firstName);
    }

    public void setLastName(String lastName) {
        getDriverHandler().sendKeys(getLastNameField(), lastName);
    }

    public void setPhoneNumber(String phoneNumber) {
        getDriverHandler().sendKeys(getPhoneNumberField(), phoneNumber);
    }

    public void setPassword(String password) {
        getDriverHandler().sendKeys(getPasswordField(), password);
    }

    public void setConfirmPassword(String confirmPassword) {
        getDriverHandler().sendKeys(getConfirmPasswordField(),confirmPassword);
    }

    public void setCompanyName(String companyName) {
        getDriverHandler().sendKeys(getCompanyNameField(), companyName);
    }

    public void setZip(String zip) {
        getDriverHandler().sendKeys(getZipField(),zip);
    }

    public void setState(String state) {
        getDriverHandler().sendKeys(getStateField(),state);
    }

    public void setCity(String city) {
        getDriverHandler().sendKeys(getCityField(),city);
    }

    public void setAddress_2(String address2) {
        getDriverHandler().sendKeys(getAddress2Field(),address2 );
    }

    public void setAddress_1(String address) {
        getDriverHandler().sendKeys(getAddressField(),address);
    }

    public void setCountry(String country) {
        getDriverHandler().toSelect(getCountryField()).selectByVisibleText(country);
    }

    public void clickFinishAndSave() {
        getDriverHandler().click(getFinishAndSaveButton());
    }
}
