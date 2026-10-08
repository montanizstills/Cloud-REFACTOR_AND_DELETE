package com.vertexinc.cloud.test.pages.models;

import org.openqa.selenium.By;

public interface CloudApplicationConfigureCompanyPage_POM {
    default By getCountryInputField(){
        return By.cssSelector("select[id='country']");
    }

    default By getCompanyCodeInputField(){
        return By.cssSelector("input[id='company-code']");
    }

    default By getFederalIdNo(){
        return By.cssSelector("input[name='fedid']");
    }

    default By getCompanyNameFilingEntity(){
        return By.cssSelector("input[id='company-name']");
    }

    default By getFirstNameInputField(){
        return By.cssSelector("input[id='statetempfirstName']");
    }

    default By getLastNameInputField(){
        return By.cssSelector("input[id='statetemplastName']");
    }

    default By getJobTitleInputField(){
        return By.cssSelector("input[id='title']");
    }

    default By getEmailInputField(){
        return By.cssSelector("input[id='email']");
    }

    default By getPhoneInputField(){
        return By.cssSelector("input[id='phone']");
    }

    default By getStreetAddressField(){
        return By.cssSelector("input[id='address1']");
    }

    default By getCityInputField(){
        return By.cssSelector("input[id='city']");
    }

    default By getStateFirstTimeConfigureCompanySelect_Object(){
        return By.cssSelector("select[id='state']");
    }

    default By getZipPostalInputField(){
        return By.cssSelector("input[name='zip']");
    }

    default By getDiscountsLink(){
        return By.cssSelector("a[ng-click='data.showDiscountCodes = true']");
    }

    default By getAddCompanyButtonFirstTimeSetup(){
        return By.cssSelector("button[type='submit']");
    }

    default By getYesConfirmAddressButton(){
        return By.cssSelector("a[ng-click='ok()']");
    }

//Post-First-time-configure
    default By getStateSelectorSwitchObject(String state){
        return By.cssSelector("label[for=\'"+state+"-checkbox']");
    }
    default By getEditConfigurationsButton() {
        return By.cssSelector("a[data-action='edit-registration']");
    }

    default By getStateLinkForConfigureCompany(String state) {
        return By.cssSelector("a[id=\'" + state + "-returns\']");
    }
    default By getReturnEffectiveDateButton() {
        return By.cssSelector("a[title='Click to change effective date']");
    }

//Return Info
    default By getSpecificReturnButton(String returnId,String state){
        return By.cssSelector("a[href*=\'"+returnId+"\\?stateId="+state+"']");
    }
    default By getRegistrationNumber() {
        return By.cssSelector("input[name='regNumber']");
    }

    default By getFilingMethod() {
        return By.cssSelector("select[id='filing-method']");
    }

    default By getFilingFrequency() {
        return By.cssSelector("select[id='filing-frequency']");
    }

    default By getFiscalYearStart() {
        return By.cssSelector("select[id='fiscal-year-override']");
    }

    default By getHowIsLocationDataReported() {
        return By.cssSelector("select[id='locationfield0']");
    }

    default By getHowIsNonLocationDataReported() {
        return By.cssSelector("select[id='locationfield1']");
    }

    default By getPrepaymentType() {
        return By.cssSelector("select[id='prepayment-type']");
    }

    default By getPrepaymentAmountInputBox(){
        return By.cssSelector("input[id='prepayment-amount']");
    }

    default By getSaveButton() {
        return By.cssSelector("button[type='submit']");
    }

    default By getReturnToCompanyOverviewButton(){
        return By.cssSelector("a.pull-right[href*='/companies']");
    }
}
