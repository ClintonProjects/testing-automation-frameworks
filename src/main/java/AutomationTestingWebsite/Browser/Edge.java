package AutomationTestingWebsite.Browser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public interface Edge {

	Logger logger = LogManager.getLogger(Edge.class);
	Browser browser = new Browser();

	default void startEdgeDriver() {
		try {
			WebDriverManager.edgedriver().setup();
			EdgeDriver edgeDriver = new EdgeDriver();
			browser.setDriver(edgeDriver);
			logger.info("Started EdgeDriver");
		} catch (Exception e) {
			logger.error("Failed to start EdgeDriver", e);
		}
	}
}
