package com.upgrade.qa.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upgrade.qa.base.TestBase;

public class InformationPage extends TestBase {

	Logger log = Logger.getLogger(InformationPage.class);

	@FindBy(name = "borrowerFirstName")
	WebElement fName;

	@FindBy(name = "borrowerLastName")
	WebElement lName;

	@FindBy(name = "borrowerStreet")
	WebElement street;

	@FindBy(name = "borrowerCity")
	WebElement cityName;

	@FindBy(name = "borrowerState")
	WebElement stateName;

	@FindBy(name = "borrowerZipCode")
	WebElement postCode;

	@FindBy(name = "borrowerDateOfBirth")
	WebElement dateOfBirth;

	@FindBy(name = "borrowerIncome")
	WebElement annualIncome;

	@FindBy(name = "borrowerAdditionalIncome")
	WebElement additionalIncome;

	@FindBy(name = "username")
	WebElement userName;

	@FindBy(name = "password")
	WebElement pwd;

	@FindBy(css = ".sc-kPVwWT.sc-kfGgVZ.ghCrQD")
	WebElement tick;

	@FindBy(css = ".section.sc-brqgnP.iKoMvw")
	WebElement checkBtn;

	public InformationPage() {

		PageFactory.initElements(driver, this);
	}

	public OfferPage fillInformation(String firstname, String lastname, String address, String city, String state,
			String zipcode, String dob, String annualincome, String additional, String emailid, String password) {

		fName.clear();
		fName.sendKeys(firstname);
		lName.clear();
		lName.sendKeys(lastname);
		street.clear();
		street.sendKeys(address);
		cityName.clear();
		cityName.sendKeys(city);
		stateName.clear();
		stateName.sendKeys(state);
		postCode.clear();
		postCode.sendKeys(zipcode);

		dateOfBirth.clear();
		dateOfBirth.sendKeys(dob);

		annualIncome.clear();

		annualIncome.sendKeys(annualincome);

		additionalIncome.clear();

		additionalIncome.sendKeys(additional);

		userName.clear();
		userName.sendKeys(emailid);
		pwd.clear();
		pwd.sendKeys(password);
		tick.click();
		checkBtn.click();
		log.debug("Check you Rate button in Information page is clicked!!");

		return new OfferPage();

	}

}
