package com.vertexinc.cloud.test.pages;

import com.vertexinc.cloud.test.pages.models.CloudApplicationConfigureCompanyPage_POM;
import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.Page;

public class CloudApplicationConfigureCompanyValidationErrorPage extends Page implements CloudApplicationConfigureCompanyPage_POM {
    public CloudApplicationConfigureCompanyValidationErrorPage(DriverHandler driverHandler) {
        super(driverHandler);
    }

    public void clickNoEditAddressButton() {

    }

    public void clickYesConfirmAddressButton() {
        getDriverHandler().click(getYesConfirmAddressButton());
    }
}
