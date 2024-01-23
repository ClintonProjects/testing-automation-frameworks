package AutomationTestingFrameworks.AutomationTestingWebsite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import AutomationTestingWebsite.PropertyFileReader.PropertyFileReader;
import AutomationTestingWebsite.Selenium.BrowserConfig;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;

public class AppTest {

	BrowserConfig browser = new BrowserConfig();
	private static final Logger logger = LogManager.getLogger(AppTest.class);
	
	@Given("^initializing the \"(.*)\" browser$")
	public void chooseBrowser(String s) {
		switch (s.toLowerCase()) {
		case "chrome":
			browser.startChromeDriver();
			break;
		case "firefox":
			browser.startFireFox();
			break;
		case "edge":
			browser.startEdgeDriver();
			break;
		default:
			logger.error("Browser start failed for reason " + new IllegalArgumentException("Unsupported browser: " + s));
			throw new IllegalArgumentException("Unsupported browser: " + s);
		}
	}

	@After
	private void closeBrowser() {
		browser.driverQuit();
	}
}
