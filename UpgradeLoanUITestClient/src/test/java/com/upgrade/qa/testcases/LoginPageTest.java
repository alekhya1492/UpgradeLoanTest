package com.upgrade.qa.testcases;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.upgrade.qa.base.TestBase;
import com.upgrade.qa.pageobjects.InformationPage;
import com.upgrade.qa.pageobjects.LoginPage;
import com.upgrade.qa.pageobjects.OfferPage;
import com.upgrade.qa.pageobjects.StartPage;
import com.upgrade.qa.utill.TestUtil;

public class LoginPageTest extends TestBase {

	OfferPage offerPageObjAfterSignUp;
	OfferPage offerPageObjAfterLogin;
	LoginPage loginPageObj;
	StartPage startPageTObj;
	InformationPage inforPageObj;
	Logger log = Logger.getLogger(LoginPageTest.class);

	public LoginPageTest() {

		super();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {

		initialization();
		startPageTObj = new StartPage();
		startPageTObj.clickOnCheckBtn();
		inforPageObj = new InformationPage();
		loginPageObj = new LoginPage();
		offerPageObjAfterSignUp = new OfferPage();
		offerPageObjAfterLogin = new OfferPage();

	}

	@DataProvider
	public Object[][] getExcelTestData() {

		Object data[][] = TestUtil.getTestData("Data");
		return data;

	}

	// This is a testcase to log in to Upgrade loan Portal and verify that Loan
	// details matched with previously saved offer page values or not

	@Test(priority = 1, dataProvider = "getExcelTestData")
	public void clickSignIn(String firstname, String lastname, String address, String city, String state,
			String zipcode, String dob, String annualincome, String additional, String emailid, String password)
			throws Exception {

		log.debug(" Entering into clickSignIn() ");

		offerPageObjAfterSignUp = inforPageObj.fillInformation(firstname, lastname, address, city, state, zipcode, dob,
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

		Map<String, String> signUpValues = offerPageObjAfterSignUp.getOfferPageValueMap();

		offerPageObjAfterSignUp.clickOnSignOut();

		log.debug(" Navigating to login URL !!! ");

		driver.navigate().to(prop.getProperty("loginurl"));

		offerPageObjAfterLogin = loginPageObj.logingIn(emailid, password);

		String titleName = offerPageObjAfterLogin.pageTitle();

		// To verify that we are on /offer-page

		Assert.assertEquals(driver.getTitle(), titleName, "ERROR!! Did not land in Offer page.");

		Map<String, String> loginValues = offerPageObjAfterLogin.getOfferPageValueMap();

		/*
		 * To validate that Loan Amount, APR, Loan Term and Monthly Payment matches with
		 * the info stored previously
		 */

		Assert.assertEquals(signUpValues.get(TestUtil.LOAN_AMOUNT), loginValues.get(TestUtil.LOAN_AMOUNT),
				"ERROR!! Loan Amount not matched");

		log.debug(" Exiting from clickSignIn() ");

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();

		log.info("Browser Closed");

		log.info("Test_case_4 Executed Successfully");
	}

}
