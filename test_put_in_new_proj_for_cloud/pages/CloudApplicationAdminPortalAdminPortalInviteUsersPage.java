package com.vertexinc.cloud.test.pages;

import com.vertexinc.cloud.test.pages.models.CloudApplicationAdminPortalInviteUsersPage_POM;
import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.Page;
import org.openqa.selenium.By;

public class CloudApplicationAdminPortalAdminPortalInviteUsersPage extends Page implements CloudApplicationAdminPortalInviteUsersPage_POM {
    public CloudApplicationAdminPortalAdminPortalInviteUsersPage(DriverHandler driverHandler) {
        super(driverHandler);
    }

    public void clickInviteNewUserButton() {
        getDriverHandler().click(getInviteNewUsersButton());
    }

    public void setRoleValue(String inputValue) {
        getDriverHandler().toSelect(getRole_SelectElement()).selectByVisibleText(inputValue);
    }

    public void getRoleValue() {
        getDriverHandler().toSelect(getRole_SelectElement()).getFirstSelectedOption().getText();
    }

    public void setAccountType(String account_type) {
        getDriverHandler().toSelect(getAccountType_HTMLObject()).selectByVisibleText(account_type);
    }

    public void setVarCPAFranchzName(String var_cpa_franchzName) {
        getDriverHandler().toSelect(getVarCPAFranchiseName_HTMLObject()).selectByVisibleText(var_cpa_franchzName);
    }

    public void setOraclePartyNumber(String oracle_party_number) {
        getDriverHandler().sendKeys(getOraclePartyNumberField(), oracle_party_number);
    }

    public void setClientName(String client_name) {
        getDriverHandler().sendKeys(getClientName_Object(), client_name);
    }


    public void setEmail(String email) {
        getDriverHandler().sendKeys(getEmailField(), email);
    }

    public void setCountry(String country) {
        getDriverHandler().toSelect(getCountry_HTMLObject()).selectByVisibleText(country);
    }

    public void setReservedPodCheckBox(String reserved_pod) {
        if (Boolean.parseBoolean(reserved_pod)) {
            getDriverHandler().click(getReservedPod_CheckBox());
        }
    }

    public void setAddress(String address) {
        getDriverHandler().sendKeys(getStreetAddressField(), address);
    }

    public void setAddress2(String address2) {
        getDriverHandler().sendKeys(getStreetAddress2Field(), address2);
    }

    public void setCity(String city) {
        getDriverHandler().sendKeys(getCityField(), city);
    }

    public void setState(String state) {
        getDriverHandler().toSelect(getState_HTMLObject()).selectByVisibleText(state);
    }

    public void setZip(String zip) {
        getDriverHandler().sendKeys(getZipPostalCodeField(), zip);
    }

    public void setSubscription(String subscription) {
        getDriverHandler().toSelect(getSubscription_HTMLObject()).selectByVisibleText(subscription);
    }

    public void setInvoiceCheckbox(Boolean value) {
        if (value)
            getDriverHandler().click(getInvoiceCheckBox());
    }

    public void setInvoicePaymentMethod(String invoicePaymentMethod) {
        getDriverHandler().sendKeys(getInvoicePaymentMethod(), invoicePaymentMethod);
    }

    public void toggleInvoiceBillingAddressSameCompanyAsAddressCheckbox() {
    getDriverHandler().click(getInvoiceBillingAddressSameCompanyAsAddressCheckbox());
    }

    public void setInvoiceCountry(String country) {
        getDriverHandler().toSelect(getInvoiceCountry()).selectByVisibleText(country);
    }

    public void setInvoiceAddress1(String invoiceAddress1) {
        getDriverHandler().sendKeys(getInvoiceStreeAddress1(),invoiceAddress1);
    }

    public void setInvoiceAddress2(String invoiceAddress2) {
        getDriverHandler().sendKeys(getInvoiceStreetAddress2(),invoiceAddress2);
    }

    public void setInvoiceCity(String city) {
        getDriverHandler().sendKeys(getInvoiceCity(),city);
    }

    public void setInvoiceState(String state){
        getDriverHandler().toSelect(getInvoiceState()).selectByVisibleText(state);
    }

    public void clickAddDiscountCodesButton() {
        getDriverHandler().click(null);
    }

    public void clickInviteUserButton() {
        getDriverHandler().click(getInviteUsersButton());
    }

    public void clickRegistrationLink() {
        getDriverHandler().click(getRegistrationLink());
    }

}
