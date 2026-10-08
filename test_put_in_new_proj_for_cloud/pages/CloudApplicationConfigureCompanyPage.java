package com.vertexinc.cloud.test.pages;

import com.vertexinc.cloud.test.pages.models.CloudApplicationConfigureCompanyPage_POM;
import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class CloudApplicationConfigureCompanyPage extends Page implements CloudApplicationConfigureCompanyPage_POM {
    public CloudApplicationConfigureCompanyPage(DriverHandler driverHandler) {
        super(driverHandler);
    }

    public void clickEditRegistrationsButtonForCompanyOverview() {
        getDriverHandler().click(getEditConfigurationsButton());
    }

    //    First time user client portal Company/User Registration
    public void setCountry(String country) {
        getDriverHandler().toSelect(getCountryInputField()).selectByVisibleText(country);
    }

    public void setCompanyCode(String companyCode) {
        getDriverHandler().sendKeys(getCompanyCodeInputField(), companyCode);

    }

    public void setFederalIdNo(String federalIdNo) {
        getDriverHandler().sendKeys(getFederalIdNo(), federalIdNo);
    }

    public void setCompanyName_FilingEntity(String companyName_FilingEntity) {
        getDriverHandler().sendKeys(getCompanyNameFilingEntity(), companyName_FilingEntity);
    }

    public void setFirstName(String firstName) {
        getDriverHandler().sendKeys(getFirstNameInputField(), firstName);
    }

    public void setLastName(String lastName) {
        getDriverHandler().sendKeys(getLastNameInputField(), lastName);
    }

    public void setJobTitle(String jobTitle) {
        getDriverHandler().sendKeys(getJobTitleInputField(), jobTitle);
    }

    public void setEmail(String email) {
        getDriverHandler().sendKeys(getEmailInputField(), email);
    }

    public void setPhone(String phone) {
        getDriverHandler().sendKeys(getPhoneInputField(), phone);
    }

    public void setAddress(String address) {
        getDriverHandler().sendKeys(getStreetAddressField(), address);
    }

    public void setCity(String city) {
        getDriverHandler().sendKeys(getCityInputField(), city);
    }

    public void setState(String state) {
        getDriverHandler().toSelect(getStateFirstTimeConfigureCompanySelect_Object()).selectByVisibleText(state);
    }

    public void clickAddCompanyButton() {
        getDriverHandler().click(getAddCompanyButtonFirstTimeSetup());
    }

    //////////////////////Register For State///////////////////////////////////////
//    public void setReturnEffectiveDateForCompanyReturns(String MM_DD_YYYY) {
//        clickEditButtonForState("state");
//        CompanyReturnsSetEffectiveDatePage companyReturnsSetEffectiveDatePage = new CompanyReturnsSetEffectiveDatePage();
//        companyReturnsSetEffectiveDatePage.setEffectiveDateInputBox(MM_DD_YYYY);
//    }
    public void enableStateForRegistration(String state) {
        getDriverHandler().click(getStateSelectorSwitchObject(state));
    }

    public void clickStateReturnEditLink(String state) {
        getDriverHandler().click(getStateLinkForConfigureCompany(state));
    }

    public void clickReturnByName(String returnId, String state_shortname) {
        getDriverHandler().click(getSpecificReturnButton(returnId, state_shortname));
    }

    public void setRegistrationNumber(String registrationNumber) {
        getDriverHandler().sendKeys(getRegistrationNumber(), registrationNumber);
    }

    public void setFilingFrequency(String filingFrequency) {
        getDriverHandler().toSelect(getFilingFrequency()).selectByVisibleText(filingFrequency);
    }

    public void setFilingMethod(String method) {
        getDriverHandler().toSelect(getFilingMethod()).selectByVisibleText(method);
    }

    public void setFiscalYearStart(String fiscalYearStart) {
        getDriverHandler().toSelect(getFiscalYearStart()).selectByVisibleText(fiscalYearStart);
    }

    public void setHowIsLocationDataReported(String howIsLocationDataReported) {
        getDriverHandler().toSelect(getHowIsLocationDataReported()).selectByVisibleText(howIsLocationDataReported);
    }

    public void setHowIsNonLocationDataReported(String howIsNonLocationDataReported) {
        getDriverHandler().toSelect(getHowIsNonLocationDataReported()).selectByVisibleText(howIsNonLocationDataReported);
    }

    public void setPrepaymentAmount(String prepaymentType, String prepaymentAmount) {
        if (prepaymentAmount == null ){
            getDriverHandler().toSelect(getPrepaymentType()).selectByVisibleText(prepaymentType);
        }
        getDriverHandler().toSelect(getPrepaymentType()).selectByVisibleText(prepaymentType);
        getDriverHandler().sendKeys(getPrepaymentAmountInputBox(),prepaymentAmount);
    }

    public void clickSave() {
        getDriverHandler().click(getSaveButton());
    }

    public void setZipPostalCode(String zipPostal) {
        getDriverHandler().sendKeys(getZipPostalInputField(), zipPostal);
    }

    public void clickDiscountsLink() {
        getDriverHandler().click(getDiscountsLink());
    }

    public void clickReturnToCompanyOverviewButton() {
        getDriverHandler().click(getReturnToCompanyOverviewButton());
    }
///////////////////End State Registration Step///////////////////////////
}
