package com.vertexinc.cloud.test.targeted;

import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.Page;

public class CloudConfirmConfigurationSavePage extends Page implements CloudConfirmConfigurationsSavePage_POM {
    public CloudConfirmConfigurationSavePage(){}
    public CloudConfirmConfigurationSavePage(DriverHandler driverHandler){
        super(driverHandler);
    }

    public void clickYesConfirmButton() {
         getDriverHandler().click(getYesConfirmSaveConfigurationChangesButton());
    }
}
