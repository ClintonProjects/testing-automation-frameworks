package AutomationTestingFrameworks.AutomationTestingWebsite;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/resources/Feature", 
	glue = {"AutomationTestingFrameworks.AutomationTestingWebsite" }, 
	plugin = { "pretty", "html:target/HtmlReports","json:target/JSONReports" }
	)

public class TestRunner {
	
}
