package com.vertexinc.cloud.test.pages;

import com.vertexinc.cloud.test.pages.models.CloudClientPortalHomePage_POM;
import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.Page;

public class CloudClientPortalPortalHomePage extends Page implements CloudClientPortalHomePage_POM {
    public CloudClientPortalPortalHomePage(DriverHandler driverHandler) {
        super(driverHandler);
    }

    public void clickHomeConfigureButton() {
        getDriverHandler().click(getHomeConfigureButton());
    }

    public void clickEditRegistrationsButtonForCompanyOverview() {
//        getDriverHandler().click(getEditConfigurationsButton()); --TODO
    }

    public void clickSaveConfigurationButton() {
        getDriverHandler().click(getSaveConfigurationButton());
    }

    public void clickGoLiveButton() {
        getDriverHandler().click(getGoLiveButton());
    }
}
