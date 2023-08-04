package AutomationTestingWebsite.Browser;

import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public interface Edge {

	Browser browser = new Browser();

	default void startEdgeDriver() {
		WebDriverManager.edgedriver();
		browser.setDriver(new EdgeDriver());
	}
}
