package AutomationTestingWebsite.Browser;

import org.openqa.selenium.WebDriver;

public class Browser {
	
	protected static WebDriver driver;
	protected static String browser;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		Browser.driver = driver;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		Browser.browser = browser;
	}
	
	public Browser config() {
		return new Browser();
	}
	
}
