package AutomationTestingWebsite.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import AutomationTestingWebsite.PropertyFileReader.PropertyFileReader;
import AutomationTestingWebsite.Selenium.BrowserConfig;

public class Main {

	//This class is just for testing new features quickly, 
	//please ignore it as it shouldn't be used in testing
	//it will be removed upon compeletion of this project.
	public static void main(String args[]) throws Exception {
		BrowserConfig browser = new BrowserConfig();
		browser.startFireFox();
		browser.navigateTo("https://www.google.com/");
		browser.screenshot("test");
		browser.driverClose();
		
	}
}
