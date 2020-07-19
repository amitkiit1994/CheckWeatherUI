package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static Logger log=LogManager.getLogger(TestBase.class.getName());
	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		String propertiesFilePath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\config.properties";
		FileInputStream fis = new FileInputStream(propertiesFilePath);

		prop.load(fis);
		String browserName = prop.getProperty("BROWSER");
		String chromeDriverPath = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe";
		// System.out.println(browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("internetexplorer")) {
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public void getScreenshot(String result) throws IOException {
	}
}
