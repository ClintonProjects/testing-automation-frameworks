package AutomationTestingFrameworks.AutomationTestingWebsite;

import io.cucumber.java.en.Then;

public class AppTest {

	@Then("Start Browser")
	public void startBrowser() {
		System.out.println("Start test");
	}

}
