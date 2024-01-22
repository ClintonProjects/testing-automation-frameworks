package AutomationTestingWebsite.Selenium;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import AutomationTestingWebsite.Browser.Browser;
import AutomationTestingWebsite.Browser.Firefox;
import AutomationTestingWebsite.Browser.GoogleChrome;
import AutomationTestingWebsite.Browser.Edge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrowserConfig extends Browser implements GoogleChrome, Firefox, Edge {

	// logging

	private static final Logger logger = LogManager.getLogger(BrowserConfig.class);
	private final int WAIT_TIME = 60;

	/*
	 * Handle by WebElement
	 */

	public void sendKeysByWebElement(WebElement element, String keys) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(keys);
			logger.info("Successfully sent keys '{}' to element", keys);
		} catch (Exception e) {
			logger.error("Failed to send keys '{}' to element", keys, e);
		}
	}

	public void clicksByWebElement(WebElement element) throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
			logger.info("Clicked on element by element: {}", element);
		} catch (Exception e) {
			logger.error("Failed to clicked on element by element: {}", e);
		}
	}

	/*
	 * Handle by Xpath
	 */

	public void clickByXpath(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			element.click();
			logger.info("Clicked on element with xpath: {}", xpath);
		} catch (Exception e) {
			logger.error("Failed to click on element with xpath: {}", xpath, e);
		}
	}

	public void clickByXpathByAction(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			Actions actions = new Actions(driver);
			actions.click(driver.findElement(By.xpath(xpath))).build().perform();
			logger.info("Clicked on element with xpath: {}", xpath);
		} catch (Exception e) {
			logger.error("Failed to click on element with action by xpath: {}", xpath, e);
		}
	}

	public void sendKeysByXpath(String xpath, String keys) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			element.sendKeys(keys);
			logger.info("sent keys to element with xpath: {}", xpath);
		} catch (Exception e) {
			logger.error("Failed to send keys to element by xpath: {}", xpath, e);
		}
	}

	public void sendKeysByXpathByAction(String xpath, String keys) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			new Actions(driver).click(element).build().perform();
			new Actions(driver).sendKeys(keys).build().perform();
			logger.info("sent keys with action to element with xpath: {}", xpath);
		} catch (Exception e) {
			logger.error("Failed to send keys with action to element by xpath: {}", xpath, e);
		}
	}

	public String getTextByXpath(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			logger.info("Found text by xpath. TagName: {}, XPath: {}", element.getTagName(), xpath);
			return element.getText();
		} catch (Exception e) {
			logger.error("Failed to get text by xpath: {}", xpath, e);
			return null;
		}
	}

	public boolean isElementExistsByXpath(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			logger.info("Element exists by xpath: {}", xpath);
			return element.isDisplayed();
		} catch (Exception e) {
			logger.error("Element does not exist by xpath: {}", xpath, e);
			return false;
		}
	}

	/*
	 * Handle by JS
	 */

	public void executeJavaScript(String script, WebElement webElement) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(script, webElement);
			logger.info("Executed JavaScript: '{}' on element", script);
		} catch (Exception e) {
			logger.error("Failed to execute JavaScript: '{}' on element", script, e);
		}
	}

	public void scrollDown(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)");
			logger.info("Scrolled down using JavaScript for element with xpath: {}", xpath);
		} catch (Exception e) {
			logger.error("Failed to scroll down using JavaScript for element with xpath: {}", xpath, e);
		}
	}

	/*
	 * Cookies
	 */

	public Set<Cookie> getCookies() {
		try {
			Set<Cookie> cookies = driver.manage().getCookies();
			logger.info("Retrieved cookies: {}", cookies);
			return cookies;
		} catch (Exception e) {
			logger.error("Failed to retrieve cookies", e);
			return Collections.emptySet();
		}
	}

	public ArrayList<String> cookiesAsStringArray() {
		try {
			ArrayList<String> listOfCookies = new ArrayList<>();
			for (Object i : driver.manage().getCookies().toArray())
				listOfCookies.add(i.toString());
			logger.info("Converted cookies to String array: {}", listOfCookies);

			return listOfCookies;
		} catch (Exception e) {
			logger.error("Failed to convert cookies to String array", e);
			return new ArrayList<>();
		}
	}

	/*
	 * Browser commands
	 */

	public void openLink(String link) {
		try {
			driver.get(link);
			logger.info("Opened link: {}", link);
		} catch (Exception e) {
			logger.error("Failed to open link: {}", link, e);
		}
	}

	public void navigateTo(String link) {
		try {
			driver.navigate().to(link);
			logger.info("Navigated to link: {}", link);
		} catch (Exception e) {
			logger.error("Failed to navigate to link: {}", link, e);
		}
	}

	public void driverQuit() {
		try {
			driver.quit();
			logger.info("Quit from the browser");
		} catch (Exception e) {
			logger.error("Failed to shutdown browser", e);
		}
	}

	public void driverClose() {
		try {
			driver.close();
			logger.info("Closed the current tab");
		} catch (Exception e) {
			logger.error("Failed to close the current tab", e);
		}
	}

	public void closeSpecificTab(int tabNum) throws InterruptedException {
		try {
			changeTab(tabNum);
			driver.close();
			logger.info("Closed tab number: {}", tabNum);
		} catch (Exception e) {
			logger.error("Failed to close tab number: {}", tabNum, e);
		}
	}

	public void openItemInNewTab(String xpath) {
		try {
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.COMMAND).click(driver.findElement(By.xpath(xpath))).keyUp(Keys.COMMAND).build()
					.perform();
			logger.info("Opened item in a new tab with xpath: {}", xpath);
		} catch (Exception e) {
			logger.error("Failed to open item in a new tab with xpath: {}", xpath, e);
		}
	}

	// changes tab on browser
	public void changeTab(int tabNum) throws InterruptedException {
		try {
			ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
			Thread.sleep(1000 * 1);
			driver.switchTo().window(tabs2.get(tabNum));
			logger.info("Changed to tab number: {}", tabNum);
		} catch (Exception e) {
			logger.error("Failed to change to tab number: {}", tabNum, e);
		}
	}

	public String getPageTextToString() {
		try {
			String pageText = driver.getPageSource();
			logger.info("Retrieved page text as a string");
			return pageText;
		} catch (Exception e) {
			logger.error("Failed to retrieve page text as a string", e);
			return null;
		}
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