/*###################################################################################################
Name: SubscriptionPage.java 
Description: Edit User subscirption Page objects with all xpaths
Author: Sudheer Bandi
Date Created:01/17/2014
Date Modified:
Updated Comments:
#####################################################################################################*/

package common.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscriptionPage {

	@FindBy(xpath = "//div[@id='subscription']/div/fieldset/ul/li/label/img")

	public WebElement FREESUBSCRIPTION;
	@FindBy(xpath = "//div[@id='subscription']/div/fieldset/ul/li[2]/label/img")

	public WebElement PROFESSIONALSUBSCRIPTION;
	@FindBy(xpath = "//input[@type='checkbox']")

	public WebElement IAGREETC;
	@FindBy(xpath = "//button[@type='submit']")

	public WebElement SUBMIT;
	@FindBy(xpath = "//div[@id='additional-information']/div/button")

	public WebElement NEXTSTEP2;
	@FindBy(xpath = "//input[contains(@id, 'Subscription_HasRateFile') and contains(@value, 'True')]")

	public WebElement RATEFILEYES;
	@FindBy(xpath = "//input[contains(@id, 'Subscription_HasRateFile') and contains(@value, 'False')]")

	public WebElement RATEFILENO;
	@FindBy(xpath = "//input[@id='s2id_autogen1']")

	public WebElement STATE;
	@FindBy(xpath = "//button[@type='submit']")

	public WebElement SAVE;
}
