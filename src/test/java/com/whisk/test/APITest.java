package com.whisk.test;


import org.testng.Assert;
import org.testng.annotations.*;

import com.whisk.utils.ApiUtils;

/**
 * Created by Vipin Alias Neo De Van
 */
public class APITest extends BaseTest{

  String id="";

    @Test
    public void verifyCreateEmptyShoppingList() {
        res = ApiUtils.createShoppingList("Shopping_List_API");
        testUtils.checkStatusIs200(res);
        id = testUtils.getResposeBodyAsString(res,"list.id");
        res=null;
        res = ApiUtils.getShoppingList(id);
        Assert.assertEquals(testUtils.getResposeBodyAsString(res,"list.content"),"");
    }


    @Test(dependsOnMethods = "verifyCreateEmptyShoppingList")
    public void verifyDeletedShoppingList() {
        res = ApiUtils.deleteShoppingList(id);
        testUtils.checkStatusIs200(res);
        String id = testUtils.getResposeBodyAsString(res,"list.id");
        res=null;
        res = ApiUtils.getShoppingList(id);
        Assert.assertEquals(res.getBody().asString().contains("shoppingList.notFound"),true);
    }
}
