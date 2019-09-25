package com.upgrade.qa.testcases;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.upgrade.qa.base.TestBase;
import com.upgrade.qa.pageobjects.InformationPage;
import com.upgrade.qa.pageobjects.LogOutPage;
import com.upgrade.qa.pageobjects.OfferPage;
import com.upgrade.qa.pageobjects.StartPage;
import com.upgrade.qa.utill.TestUtil;

public class OfferPageTest extends TestBase {

	StartPage startPageTObj;
	OfferPage offerPageObj;
	InformationPage inforPageObj;
	LogOutPage logOutPageObj;
	Logger log = Logger.getLogger(OfferPageTest.class);

	InformationPageTest io;

	public OfferPageTest() {

		super();
	}

	@BeforeMethod
	public void setup() {

		initialization();

		startPageTObj = new StartPage();
		startPageTObj.clickOnCheckBtn();
		inforPageObj = new InformationPage();

	}

	@DataProvider
	public Object[][] getExcelTestData() {

		Object data[][] = TestUtil.getTestData("Data");
		return data;

	}

	// This is a test case to navigate to offer page check the loan details , save
	// them and then Signout from the page
	// This also navigates to Login page URL

	@Test(priority = 1, dataProvider = "getExcelTestData")
	public void clickSignOut(String firstname, String lastname, String address, String city, String state,
			String zipcode, String dob, String annualincome, String additional, String emailid, String password)
			throws Exception {

		log.debug(" Entering into clickSignOut() ");

		offerPageObj = inforPageObj.fillInformation(firstname, lastname, address, city, state, zipcode, dob,
				annualincome, additional, emailid, password);

		log.debug("check your rate in information paage is clicked");

		// Below code validates the dob input whether it is in valid range(date before
		// 01/01/2000 and after 01/01/1930>) or not and assets the error message

		Boolean dobCheck = TestUtil.isWithinDateRange(dob);

		if (dobCheck == false) {

			String dobError = driver.findElement(By.xpath("//div[@class=\"sc-jKJlTe eTLJZq\" and @data-error ='true']"))
					.getText();

			log.debug("DOB Validation :: " + dobError);

			Assert.assertEquals(dobError, TestUtil.DOB_ERROR_MESSAGE, "Error validation message is not as expected!!");

			throw new Exception("DOB entered is not within valid range!!");

		}

		logOutPageObj = offerPageObj.clickOnSignOut();

		log.debug("Entering Login URL!!!");

		driver.navigate().to(prop.getProperty("loginurl"));

		log.debug(" Exiting from clickSignOut() ");

	}

	@AfterMethod
	public void teardown() {

		driver.quit();

		log.info("Browser Closed");

		log.info("Test_case_3 Executed Successfully");
	}

}
