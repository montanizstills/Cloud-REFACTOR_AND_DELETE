package com.vertexinc.cloud.test.pages;

import com.vertexinc.cloud.test.pages.models.CloudApplicationHomePage_POM;
import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.Page;

public class CloudAdminPortalHomeScreen extends Page implements CloudApplicationHomePage_POM {

    public CloudAdminPortalHomeScreen(DriverHandler driverHandler) {
        super(driverHandler);
    }

    public void clickInviteUsers() {
        getDriverHandler().click(getInviteUsersButton());
    }

    public void logOut() {
        getDriverHandler().click(getLogoutButton());
    }
}
