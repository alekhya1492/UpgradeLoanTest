package com.upgrade.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.upgrade.qa.base.TestBase;
import com.upgrade.qa.pageobjects.InformationPage;
import com.upgrade.qa.pageobjects.StartPage;

public class StartPageTest extends TestBase {

	StartPage startPageTObj;
	InformationPage inforPageObj;
	Logger log = Logger.getLogger(StartPageTest.class);

	public StartPageTest() {

		super();
	}

	@BeforeMethod
	public void setup() {

		initialization();

		startPageTObj = new StartPage();

	}

	// This a test case to input loan amount and loan purpose in Start page and
	// click on Check you Rate Button

	@Test
	public void checkYouRate() {

		log.debug(" Entering into checkYouRate() ");

		inforPageObj = startPageTObj.clickOnCheckBtn();

		log.debug(" Exiting from checkYouRate() ");
	}

	@AfterMethod
	public void teardown() {

		driver.quit();

		log.info("Browser Closed");

		log.info("Test_case_1 Executed Successfully");

	}

}
