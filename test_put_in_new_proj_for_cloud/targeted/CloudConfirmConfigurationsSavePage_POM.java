package com.vertexinc.cloud.test.targeted;

import org.openqa.selenium.By;

public interface CloudConfirmConfigurationsSavePage_POM {
    default By getYesConfirmSaveConfigurationChangesButton(){
        return By.cssSelector("button[ng-click='ok()']");
    }
    default By getNoCancelLink(){
        return By.cssSelector("a[ng-click='cancel()']");
    }
}
