package com.whisk.test;

import com.whisk.pages.WhiskHomePage;
import com.whisk.pages.WhiskShoppingPage;
import com.whisk.testutil.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


public class WebUITest extends BaseTest {

	String shoppingListName = "";

    @Test()
    public void verifyUserIsAbleToAddItemsToShoppingList() {
           whiskSignUpPage.textBoxUsername("username").sendKeys(TestUtils.generategmailId());
		   whiskSignUpPage.buttonContinue("Continue").click();
		   whiskHomePage = new WhiskHomePage(driver);
		   Assert.assertEquals(whiskHomePage.spanText("Welcome"),true);
		   whiskHomePage.tabsLink("Shopping").click();
		   whiskShoppingPage = new WhiskShoppingPage(driver);
		   whiskShoppingPage.tabsLink("Create new list").click();
		   whiskShoppingPage.textBoxShppingList("name").click();
		   shoppingListName = whiskShoppingPage.textBoxShppingList("name").getAttribute("value");
		   whiskShoppingPage.shoppingPageButton("Create").click();
		   whiskShoppingPage.divNameContainsText(shoppingListName).click();
		   whiskShoppingPage.addItemAutoPopulate("Add item").click();
		   whiskShoppingPage.divNameContainsText("Popular").click();
		   whiskShoppingPage.autoCompleteItem("Milk").click();
		   whiskShoppingPage.autoCompleteItem("Eggs").click();
		   whiskShoppingPage.autoCompleteItem("Bread").click();
		   whiskShoppingPage.autoCompleteItem("Butter").click();
		   whiskShoppingPage.autoCompleteItem("Cheese").click();
		   whiskShoppingPage.divNameContainsText(shoppingListName).click();

    }

	 @Test(dataProvider="TestData",dependsOnMethods = "verifyUserIsAbleToAddItemsToShoppingList", priority = 1)
	    public void verifyItemsAddedToShoppingList(String item) {
		 Assert.assertEquals(whiskShoppingPage.shoppingItemSelectCheckbox(item).isDisplayed(),true);
	 }

	@Test(dependsOnMethods = "verifyItemsAddedToShoppingList", priority = 2)
	public void verifyShoppingListCanBeDeleted() {
		whiskShoppingPage.shoppingListOptionButton(shoppingListName).click();
		whiskShoppingPage.shoppingPageButton("Delete list").click();
		whiskShoppingPage.divNameContainsText("Confirm delete").click();
		Assert.assertEquals(whiskShoppingPage.shoppingListExists(shoppingListName),false);
	}

}
