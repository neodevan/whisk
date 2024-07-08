package com.whisk.utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class BrowserFactory {
    


     public static WebDriver InitBrowser(String browserName) {
         WebDriver driver = null;
         try {
             switch (browserName) {

                 case "Firefox":

                     WebDriverManager.firefoxdriver().setup();
                     FirefoxOptions Firefoxoptions = new FirefoxOptions();
                     Firefoxoptions.addArguments("--start-maximized");
                     driver = new FirefoxDriver(Firefoxoptions);
                     return driver;

                 case "Chrome":

                     WebDriverManager.chromedriver().clearDriverCache().setup();
                     driver = new ChromeDriver();
                     return driver;

             }
             return null;
         } catch (Exception e) {

             return null;
         }
     }
         


     
}
