package AutomationTestingWebsite.Browser;

import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;

public interface GoogleChrome {

	Logger logger = LogManager.getLogger(GoogleChrome.class);
	Browser browser = new Browser();

	default void startChromeDriver() {
		try {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			browser.setDriver(driver);
			logger.info("Started ChromeDriver");
		} catch (Exception e) {
			logger.error("Failed to start ChromeDriver", e);
		}
	}

	default void startChromeDriver(ChromeOptions opts) {
		try {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver(opts);
			browser.setDriver(driver);
			logger.info("Started ChromeDriver with options");
		} catch (Exception e) {
			logger.error("Failed to start ChromeDriver with options", e);
		}
	}

	// this is example of options that could be used.
	default ChromeOptions optionsSetA() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", null);
		options.addArguments("window-size=1920,1080");
		options.addArguments(
				"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
		options.addArguments("disable-infobars");
		options.addArguments("user-data-dir=" + "C:\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
		return options;
	}
}
