package com.whisk.pages;

import com.whisk.utils.ObjectRepoUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author vipin
 * @since 10/01/2021
 */
public class WhiskShoppingPage {

    ObjectRepoUtility objRepo;

    public WhiskShoppingPage(WebDriver driver) {
        objRepo = new ObjectRepoUtility(driver);
    }

    public WebElement tabsLink(String containsText) {
        return objRepo.constructXpathLinkContainsTextElement(containsText);
    }

    public WebElement textBoxShppingList(String texboxVal) {
        return objRepo.ConstructElementByCssTagAttributeValue("input", "name",
                texboxVal);
    }

    public WebElement shoppingPageButton(String containsText) {
        return objRepo.constructXpathButtonContainsTextElement(containsText);
    }

    public WebElement divNameContainsText(String textValue){
        return  objRepo.constructXpathTagContainsTextElement("div",textValue);
    }

    public WebElement addItemAutoPopulate(String textBoxValue){
        return objRepo.ConstructElementByCssTagAttributeContainsValue("input","placeholder", textBoxValue);
    }

    public WebElement autoCompleteItem(String itemName){
        return  objRepo.constructXpathForTheSecondTopParentTagContainsTextElement("div","div","span", itemName);
    }

    public WebElement shoppingItemSelectCheckbox(String itemName){
        return  objRepo.constructXpathParentTagFollowChildElementInput("div","data-testid","shopping-list-item", itemName);
    }

    public WebElement shoppingListOptionButton(String shoppingListName){
        return  objRepo.constructXpathForParentFollowingSiblingButtonElement("div","div", shoppingListName, "div");
    }

    public boolean shoppingListExists(String shoppingListName){
       if(objRepo.constructXpathByTagContainsText("div", shoppingListName)==null){
           return false;
       }
       return true;
    }

}
