package com.upgrade.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.upgrade.qa.utill.TestUtil;

/*This is a java class which contains the initialization of config .properties file and also a Initialization method which initializes the driver , set the property of the driver and does below 

1. Maximizes the window
2. Delete All cookies
3. Page load timeout
4. Implicitly wait for all elements to load in a page
5. Go to the URL/landing Page/Start Page*/

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static Logger logger;

	public TestBase() {

		try {

			prop = new Properties();
			FileInputStream src = new FileInputStream(
					"/Users/alekhya/eclipse-workspace/UpgradeLoanUiTestClient/src/main/java/com/upgrade/qa/config/config.properties");
			prop.load(src);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException io) {

			io.printStackTrace();
		}

	}

	public static void initialization() {

		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");

		if (browser.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver", "/Users/alekhya/downloads/chromedriver");
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(url);

	}

}
