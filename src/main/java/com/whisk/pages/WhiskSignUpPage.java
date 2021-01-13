package com.whisk.pages;

import com.whisk.utils.ObjectRepoUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author vipin
 * @since 10/01/2021
 */
public class WhiskSignUpPage {

    ObjectRepoUtility objRepo;

    public WhiskSignUpPage(WebDriver driver) {
        objRepo = new ObjectRepoUtility(driver);
    }

    public WebElement textBoxUsername(String texboxVal) {
        return objRepo.ConstructElementByCssTagAttributeValue("input", "name",
                texboxVal);
    }

    public WebElement buttonContinue(String containsText) {
        return objRepo.constructXpathButtonContainsTextElement(containsText);
    }

}
