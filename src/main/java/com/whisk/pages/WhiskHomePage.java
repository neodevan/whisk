package com.whisk.pages;

import com.whisk.utils.ObjectRepoUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author vipin
 * @since 10/01/2021
 */
public class WhiskHomePage {

    ObjectRepoUtility objRepo;

    public WhiskHomePage(WebDriver driver) {
        objRepo = new ObjectRepoUtility(driver);
    }

    public WebElement tabsLink(String containsText) {
        return objRepo.constructXpathLinkContainsTextElement(containsText);
    }

    public boolean spanText(String containsText) {
        return objRepo.IsElementPresent(objRepo.constructXpathByTagContainsText("span", containsText));
    }
}
