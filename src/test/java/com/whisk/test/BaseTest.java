package com.whisk.test;

import java.lang.reflect.Method;

import com.whisk.pages.WhiskHomePage;
import com.whisk.pages.WhiskShoppingPage;
import com.whisk.pages.WhiskSignUpPage;
import org.apache.log4j.Logger;

import com.whisk.testutil.TestUtils;
import com.whisk.utils.ApiUtils;
import com.whisk.utils.BrowserFactory;
import com.whisk.utils.TestDataReader;
import com.jayway.restassured.response.Response;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

/**
 * Created by Vipin Alias Neo De Van
 */
public class BaseTest {

	protected Logger log=Logger.getLogger(BaseTest.class);
    public Response res = null; //Response
    String BaseWebURL = "https://my.whisk-dev.com/";
    TestUtils testUtils = new TestUtils();
    WebDriver driver;
    WhiskSignUpPage whiskSignUpPage;
    WhiskShoppingPage whiskShoppingPage;
    WhiskHomePage whiskHomePage;

    @BeforeTest
    public void setup (){
        ApiUtils.setBaseURI();
        driver = BrowserFactory.InitBrowser("Chrome");
        driver.get(BaseWebURL);
        whiskSignUpPage = new WhiskSignUpPage(driver);
    }


    @DataProvider(name="TestData") 
    public Object[][] getData(Method method)
    {
        log.info("Reading test data for : " +method.getName());
        String currentTestClass=method.getDeclaringClass().toString();
        currentTestClass=currentTestClass.substring(currentTestClass.lastIndexOf(".")+1, currentTestClass.length());
        return TestDataReader.getDataFromExcelFile(currentTestClass,method.getName());
    }
    

}
