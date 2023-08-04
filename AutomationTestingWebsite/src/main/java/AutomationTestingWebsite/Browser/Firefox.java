package AutomationTestingWebsite.Browser;

import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public interface Firefox {

	Browser browser = new Browser();

	default void startFireFox() {
		WebDriverManager.firefoxdriver().setup();
		browser.setDriver(new FirefoxDriver());
	}

}
