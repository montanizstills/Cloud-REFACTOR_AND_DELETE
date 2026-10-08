package com.vertexinc.cloud.test.pages.models;

import org.openqa.selenium.By;

public interface CompanyReturnsEffectiveDatePage_POM {
    default By getCompanyStartDateRadioButton(){
        return By.cssSelector("input[id='enable-pick-company-start']");
    }
    default By getCompanyStartDateInputField(){
        return By.cssSelector("input[id='enable-today-future']");
    }
    default By getSaveButtonForConfigureCompanyReturns(){
        return By.cssSelector("input[type='submit']");
    }

}
