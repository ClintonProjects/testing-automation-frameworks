package AutomationTestingWebsite.Browser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public interface Firefox {

	Logger logger = LogManager.getLogger(Firefox.class);
	Browser browser = new Browser();

	default void startFireFox() {
		try {
			WebDriverManager.firefoxdriver().setup();
			FirefoxDriver firefoxDriver = new FirefoxDriver();
			browser.setDriver(firefoxDriver);
			logger.info("Started FirefoxDriver");
		} catch (Exception e) {
			logger.error("Failed to start FirefoxDriver", e);
		}
	}
}
