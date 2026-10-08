package com.vertexinc.cloud.test.pages.models;

import org.openqa.selenium.By;

public interface CloudApplicationWelcomeScreen_POM {

    default By getEmailField() {
        return By.cssSelector("input[name='username']");
    }

    default By getContinueButton() {
        return By.cssSelector("button[name='action']");
    }

}
