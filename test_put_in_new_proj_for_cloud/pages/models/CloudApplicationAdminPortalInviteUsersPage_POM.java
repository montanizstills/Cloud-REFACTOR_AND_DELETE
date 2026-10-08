package com.vertexinc.cloud.test.pages.models;

import org.openqa.selenium.By;

public interface CloudApplicationAdminPortalInviteUsersPage_POM {

    default By getInviteUsersButton() {
        return By.cssSelector("a[href='#/inviteNew']");
    }

    default By getInviteNewUsersButton(){
        return By.cssSelector("button[type='submit']");
    }


    default By getRole_SelectElement() {
        return By.cssSelector("select[name='Role']");
    }

    default By getEmailField() {
        return By.id("Email");
    }


    //Customer Admin
    default By getAccountType_HTMLObject() {
        return By.cssSelector("select[name='clientSubTypeName']");
    }

    default By getVarCPAFranchiseName_HTMLObject() {
        return By.cssSelector("select[name='resellerName']");
    }

    default By getOraclePartyNumberField() {
        return By.cssSelector("input[name='OraclePartyNumber']");
    }

    default By getClientName_Object() {
        return By.cssSelector("input[name='ClientName']");
    }

    default By getCountry_HTMLObject() {
        return By.cssSelector("select[name='Country']");
    }

    default By getStreetAddressField() {
        return By.cssSelector("input[name='Address1']");
    }

    default By getStreetAddress2Field() {
        return By.cssSelector("input[name='Address2']");
    }

    default By getCityField() {
        return By.cssSelector("input[name='City']");
    }

    default By getState_HTMLObject() {
        return By.cssSelector("select[name='StateCode']");
    }

    default By getZipPostalCodeField() {
        return By.cssSelector("input[name='PostalCode']");
    }

    default By getSubscription_HTMLObject() {

        return By.cssSelector("select[name='subscriptionId']");
    }

    default By getReservedPod_CheckBox() {
        return By.cssSelector("input[name='isReservedPodChecked']");
    }

    default By getInvoiceCheckBox() {
        return By.cssSelector("input[name='isInvoiceChecked']");
    }

    default By getInvoicePaymentMethod() {
        return By.cssSelector("select[id='manual-invoice-payment-method']");
    }

    default By getInvoiceBillingAddressSameCompanyAsAddressCheckbox() {
        return By.cssSelector("input[name='billingAddressChecked']");
    }

    default By getInvoiceCountry() {
        return By.cssSelector("select[id='manual-invoice-country']");
    }

    default By getInvoiceStreeAddress1() {
        return By.cssSelector("input[name='billingAddress1']");
    }

    default By getInvoiceStreetAddress2() {
        return By.cssSelector("input[name='billingAddress2']");
    }

    default By getInvoiceCity(){
        return By.cssSelector("input[name='manual-invoice-city']");
    }

    default By getInvoiceState(){
        return By.cssSelector("select[id='manual-invoice-state']");
    }

    default By getInvoiceZipPostalField(){
        return By.cssSelector("input[name='manual-invoice-zip']");
    }

    default By getRegistrationLink() {
        return By.cssSelector("p[ng-if='vm.bypassemail']>a");
    }

}
