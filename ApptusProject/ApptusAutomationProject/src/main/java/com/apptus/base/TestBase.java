package com.apptus.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	private static String browserName, pageLoadTimeout, implicitWait, chromeKey, chromeValue, firefoxKey, firefoxValue;
	private String CONFIG_PATH= "/ApptusProject/ApptusAutomationProject/src/main/java/com/Apptus/config/config.properties";

	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(CONFIG_PATH);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {

		browserName = prop.getProperty("browser");
		pageLoadTimeout = prop.getProperty("pageLoadTimeout");
		implicitWait = prop.getProperty("implicitWait");
		chromeKey = prop.getProperty("ChromeKey");
		chromeValue = prop.getProperty("ChromeValue");
		firefoxKey = prop.getProperty("firefoxKey");
		firefoxValue = prop.getProperty("firefoxValue");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty(chromeKey, chromeValue);
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty(firefoxKey, firefoxValue);
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(pageLoadTimeout), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(implicitWait), TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
}
