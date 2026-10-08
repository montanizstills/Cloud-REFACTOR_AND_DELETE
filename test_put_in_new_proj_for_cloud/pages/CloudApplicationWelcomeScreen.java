package com.vertexinc.cloud.test.pages;

import com.vertexinc.cloud.test.pages.models.CloudApplicationWelcomeScreen_POM;
import io.github.nezonium.util.DriverHandler;
import io.github.nezonium.util.Page;

public class CloudApplicationWelcomeScreen extends Page implements CloudApplicationWelcomeScreen_POM {
    public CloudApplicationWelcomeScreen(){}

    public CloudApplicationWelcomeScreen(DriverHandler driverHandler){
        super(driverHandler);
    }

    public void enterEmailField(String email) {
        getDriverHandler().sendKeys(getEmailField(),email);
    }

    public void clickContinueButton(){
        getDriverHandler().click(getContinueButton());
    }

}
