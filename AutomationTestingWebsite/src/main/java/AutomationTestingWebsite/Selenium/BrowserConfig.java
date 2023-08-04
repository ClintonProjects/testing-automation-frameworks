package AutomationTestingWebsite.Selenium;

import java.io.*;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationTestingWebsite.Browser.Browser;
import AutomationTestingWebsite.Browser.Firefox;
import AutomationTestingWebsite.Browser.GoogleChrome;
import AutomationTestingWebsite.Browser.Edge;
import Scripts.DreambotSelenium;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserConfig extends Browser implements GoogleChrome, Firefox, Edge {

	/*
	 * Handle by WebElement
	 */

	public static void sendKeyByWebElement(WebElement element, String keys) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(keys);
		} catch (Exception e) {
		}
	}

	/*
	 * Handle by Xpath
	 */

	public static void clickByXpath(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		element.click();
	}

	public static void clickByXpathByAction(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		Actions actions = new Actions(driver);
		actions.click(driver.findElement(By.xpath(xpath))).build().perform();
	}

	public static void sendKeyByXpath(String xpath, String keys) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		element.sendKeys(keys);
	}

	public static void sendKeyByXpathByAction(String xpath, String keys) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		new Actions(driver).click(driver.findElement(By.xpath(xpath))).build().perform();
		new Actions(driver).sendKeys(keys).build().perform();
	}

	public String getTextByXpath(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			return element.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isElementExistsByXpath(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return element.isDisplayed();
	}

	/*
	 * Handle by JS
	 */

	public static void jsExcute(String script, WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		((JavascriptExecutor) driver).executeScript(script, webElement);
	}

	public static void sendScrollDown(String xpath) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,350)",
				ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	/*
	 * Cookies
	 */

	public static Set<Cookie> getCookies() {
		return driver.manage().getCookies();
	}

	public static ArrayList<String> cookiesAsStringArray() {
		// could do this with map but think this will be neater overall.
		ArrayList<String> ListOfCookies = new ArrayList<String>();
		for (Object i : driver.manage().getCookies().toArray())
			ListOfCookies.add(i.toString());
		return ListOfCookies;
	}

	/*
	 * Browser commands
	 */

	public static void openLink(String link) {
		driver.get(link);
	}

	public void navigateTo(String link) {
		driver.navigate().to(link);
	}

	// closes a single tab
	public static void driverClose() {
		driver.close();
	}

	public static void closeSpecificTab(int tabNum) throws InterruptedException {
		changeTab(tabNum);
		driver.close();
	}

	public static void openItemInNewTab(String xpath) {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.COMMAND).click(driver.findElement(By.xpath(xpath))).keyUp(Keys.COMMAND).build().perform();
	}

	// changes tab on browser
	public static void changeTab(int tabNum) throws InterruptedException {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		Thread.sleep(1000 * 1);
		driver.switchTo().window(tabs2.get(tabNum));
	}

	public String getPageTextToString() {
		return driver.getPageSource();
	}

	/*
	 * Browser commands
	 */

	// This is just better then having try catches all over a test.
	public void delay(int seconds) {
		try {
			Thread.sleep(1000 * seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}