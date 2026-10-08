package com.vertexinc.cloud.test.pages;

import com.vertexinc.cloud.test.pages.models.CloudApplicationEmailConfAndPasswordPage_POM;
import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.Page;

public class CloudApplicationEmailConfAndPasswordPage extends Page implements CloudApplicationEmailConfAndPasswordPage_POM {

    public CloudApplicationEmailConfAndPasswordPage(DriverHandler driverHandler) {
        super(driverHandler);
    }

    public void enterPassword(String password) {
        getDriverHandler().sendKeys(getPasswordField(),password);
    }
    public void clickContinueButton(){
        getDriverHandler().click(getContinueButton());
    }
}
