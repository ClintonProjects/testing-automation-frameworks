package AutomationTestingWebsite.PropertyFileReader;

/* I'm not a fan of the normal approach of filling a class with xpaths. I believe a much
 * better approach is to have a file to read from, following a naming convention. For 
 * example, google.search_page.searchbox. I'm aware this approach forces you 
 * to make an object in each test case, but I think it's better than having a lot of 
 * classes for xpaths and such
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import AutomationTestingWebsite.Selenium.BrowserConfig;

public class PropertyFileReader {

	private final String resourceFolderPath = "src/main/resources/";
	private String filePath;
	private static final Logger logger = LogManager.getLogger(PropertyFileReader.class);

	public PropertyFileReader(String fileName) {
		filePath = resourceFolderPath + fileName + ".properties";
	}

	public String getProperty(String propertyName) throws IOException, Exception {
		Properties properties = new Properties();

		try (FileInputStream fileInput = new FileInputStream(filePath)) {
			properties.load(fileInput);

			String propertyValue = properties.getProperty(propertyName);

			if (propertyValue == null || propertyValue.isEmpty()) {
				logger.error("Property not found", new Exception("Property not found"));
				throw new Exception("Error reading properties file");
			}

			return propertyValue;

		} catch (IOException e) {
			logger.error("Property not found", new Exception("Error reading properties file"));
			throw new Exception("Error reading properties file");
		}
	}

}