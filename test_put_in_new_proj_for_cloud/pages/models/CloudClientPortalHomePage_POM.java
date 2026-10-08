package com.vertexinc.cloud.test.pages.models;

import org.openqa.selenium.By;

public interface CloudClientPortalHomePage_POM {
    default By getHomeConfigureButton() {
        return By.cssSelector("a[href='/config']");
    }
    default By getSaveConfigurationButton(){
        return By.cssSelector("a[ng-click='confirmSaveConfig($event, company.companyId)']");
    }
    default By getGoLiveButton(){
        return By.cssSelector("a[ng-click='goLive(container)']");
    }
    default By getEditRegistrationButton(){
        return By.cssSelector("a[data-action='edit-registration']");
    }
}
