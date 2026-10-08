package com.vertexinc.cloud.test.pages.models;

import org.openqa.selenium.By;

public interface CloudApplicationEmailConfAndPasswordPage_POM {

    default By getPasswordField(){
        return By.cssSelector("input[name='password']");
    }
    default By getContinueButton(){
        return By.cssSelector("button[name='action']");
    }
}
