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
import com.upgrade.qa.pageobjects.OfferPage;
import com.upgrade.qa.pageobjects.StartPage;
import com.upgrade.qa.utill.TestUtil;

public class InformationPageTest extends TestBase {

	StartPage startPageTObj;
	OfferPage offerPagePbj;
	InformationPage inforPageObj;
	Logger log = Logger.getLogger(InformationPageTest.class);

	public InformationPageTest() {

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

	// This a testcase to fill in all details of the candidate in "Check Your
	// Rate|Personal Loans|Upgrade" page and click on Check your Rate Button

	@Test(priority = 1, dataProvider = "getExcelTestData")
	public void clickOnCheckYourRateBtn(String firstname, String lastname, String address, String city, String state,
			String zipcode, String dob, String annualincome, String additional, String emailid, String password)
			throws Exception {

		log.debug(" Entering into clickOnCheckYourRateBtn() ");

		offerPagePbj = inforPageObj.fillInformation(firstname, lastname, address, city, state, zipcode, dob,
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

		log.debug(" Exiting into clickOnCheckYourRateBtn() ");

	}

	@AfterMethod
	public void teardown() {

		driver.quit();

		log.info("Browser Closed");

		log.info("Test_case_2 Executed Successfully");
	}

}
