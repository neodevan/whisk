package com.whisk.utils;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class ObjectRepoUtility {

	WebDriver driver;
	Wait<WebDriver> wait;
	long timeout= 10;
	long polling= 2;

	public ObjectRepoUtility(WebDriver driver) {
		this.driver = driver;
		wait = new FluentWait<WebDriver>(this.driver).withTimeout(timeout, TimeUnit.SECONDS).pollingEvery(polling,TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class).ignoring(UnknownError.class);
	}

	public void getURL(String baseURL) {
		driver.get(baseURL);
	}

	public WebElement constructXpathAttributeValue(String attr, String attrValue) {
		WebElement element = driver.findElement(By.xpath(".//*[@" + attr + "='" + attrValue
				+ "']"));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement constructXpathTextElement(String attrValue) {
		WebElement element = driver.findElement(By.xpath(".//*[contains(text(),'"+attrValue+"')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement constructXpathTagContainsTextElement(String tagName, String attrValue) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath((".//"+tagName+"[contains(text(),'" + attrValue
				+ "')]"))));
		WebElement element = driver.findElement(By.xpath(".//"+tagName+"[contains(text(),'" + attrValue
				+ "')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement constructXpathForTheSecondTopParentTagContainsTextElement(String secondTopParentTagName, String firstParentTagName,String childtagName, String attrValue) {
		WebElement element = driver.findElement(By.xpath(".//"+childtagName+"[contains(text(),'" + attrValue
				+ "')]/parent::"+firstParentTagName+"/parent::"+secondTopParentTagName));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement constructXpathForParentFollowingSiblingButtonElement(String parentTagName,String childtagName, String attrValue, String followingSiblingTagName) {
		WebElement element = driver.findElement(By.xpath(".//"+childtagName+"[contains(text(),'" + attrValue
				+ "')]/parent::"+parentTagName+"/following-sibling::"+followingSiblingTagName+"//button"));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement constructXpathButtonContainsTextElement(String attrValue) {
		WebElement element = driver.findElement(By.xpath(".//button[contains(.,'" + attrValue
				+ "')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement constructXpathLinkContainsTextElement(String attrValue) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[contains(.,'" + attrValue
				+ "')]")));
		WebElement element = driver.findElement(By.xpath(".//a[contains(.,'" + attrValue
				+ "')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement constructXpathTagFollowedChildElement(String tag,
			String attrValue) {
		if (attrValue == "Shopping") {
			WebElement element = driver.findElement(By.xpath(".//" + tag
					+ "[contains(.,'" + attrValue + "')]"));
			Actions action = new Actions(driver);
			action.moveToElement(element).click().build().perform();

			return null;
		}
		return driver.findElement(By.xpath(".//" + tag + "[contains(.,'"
				+ attrValue + "')]"));
	}

	public WebElement constructXpathParentChildTagFollowChildElement(
			String parentTag, String childTag, String parentNonTextAttr,
			String parentNonTextAttrValue, String parentTextAttrValue,
			String childTextAttrValue) {
		return driver.findElement(By.xpath(".//" + parentTag + "[@"
				+ parentNonTextAttr + "='" + parentNonTextAttrValue
				+ "' and contains(.,'" + parentTextAttrValue + "')]//"
				+ childTag + "[contains(.,'" + childTextAttrValue + "')]"));
	}

	public WebElement constructXpathParentTagFollowChildElement(
			String parentTag, String parentAttr, String parentAttrValue,
			String childTextAttrValue) {
		return driver.findElement(By.xpath(".//" + parentTag + "[contains(@"
				+ parentAttr + ",'" + parentAttrValue + "') and contains(.,'"
				+ childTextAttrValue + "')]"));
	}

	public WebElement constructXpathParentTagFollowChildElementInput(
			String parentTag, String parentAttr, String parentAttrValue,
			String childTextAttrValue) {
		WebElement element = driver.findElement(By.xpath(".//" + parentTag + "[contains(@"
				+ parentAttr + ",'" + parentAttrValue + "') and contains(.,'"
				+ childTextAttrValue + "')]"));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement ConstructElementByCssTagAttributeValue(String tag,
			String atribute, String value) {
		String selector = tag + "[" + atribute + "="
				+ value + "]";
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
		WebElement element = driver.findElement(By.cssSelector(selector));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement ConstructElementByCssTagAttributeContainsValue(
			String tag, String atribute, String value) {
		String selector = tag + "[" + atribute + "*='"
				+ value + "']";
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
		WebElement element = driver.findElement(By.cssSelector(selector));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public WebElement ConstructElementByCss(String selector) {
		return driver.findElement(By.cssSelector(selector));
	}

	public By constructXpathByTagContainsText(String tagName, String attrValue) {
		try {
			String xpathString = ".//" + tagName + "[contains(text(),'" + attrValue
					+ "')]";
			//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(xpathString)));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathString)));
			return By.xpath(xpathString);
		}catch (Exception e){
			return null;
		}

	}

	public List<WebElement> ConstructElementListByCss(String selector) {
		return driver.findElements(By.cssSelector(selector));
	}

	public void enterTextPlaceHolderTextbox(String elementAttributeorBy,
			String input) {
		WebElement textBoxIdentified = ConstructElementByCss(elementAttributeorBy);
		Actions actions = new Actions(driver);
		actions.moveToElement(textBoxIdentified);
		actions.click();
		actions.sendKeys(input);
		actions.build().perform();
	}
	
	public void enterTextValue(WebElement element,
			String input) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		element.clear();
		actions.sendKeys(input);
		actions.build().perform();
	}
	
	public String getAttributeValue(String selector, String attribute) {
		WebElement textBoxIdentified = ConstructElementByCss(selector);
		return textBoxIdentified.getAttribute(attribute);
	}
	

	public WebElement constructButton(String attr, String attrValue) {
		return driver.findElement(By.cssSelector("[" + attr + "='" + attrValue
				+ "']"));
	}

	public WebElement constructFrameByxpath(String att1, String attVal1,
			String att2, String attVal2) {
		return driver.findElement(By.xpath("//iframe[contains(@" + att1 + ", '"
				+ attVal1 + "') and contains(@" + att2 + ", '" + attVal2
				+ "')]"));
	}

	public Select dropDownSelect(String attrValue) {
		WebElement element = ConstructElementByCss("#" + attrValue);
		Select objSelect = new Select(element);
		return objSelect;
	}

	public void enterTextJS(String selector, String input) {
		String Query = "document.querySelector('" + selector + "').value='"
				+ input + "'";
		((JavascriptExecutor) driver).executeScript(Query);
	}
	
	public void enterTextArgumentJS(WebElement element, String input){
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].value="+input, element);	
		} catch(Exception e){
			
		}
		
	}
	

	public void attachAfile(String fileName) {
		String baseDirectory = System.getProperty("user.dir");
		String filepath = baseDirectory + "\\" + fileName;
		driver.findElement(By.name("file")).sendKeys(filepath);

	}

	public String getUrlText() {
		return driver.getCurrentUrl();
	}

	public boolean IsElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean IsAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

}
