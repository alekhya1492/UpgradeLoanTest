package com.upgrade.qa.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.upgrade.qa.base.TestBase;

public class StartPage extends TestBase {

	Logger log = Logger.getLogger(StartPage.class);

	@FindBy(name = "desiredAmount")
	WebElement loanAmount;

	@FindBy(xpath = "//select[@class = 'sc-dVhcbM iHtznt' and @data-auto = 'dropLoanPurpose']")
	WebElement loanPurpose;

	@FindBy(xpath = "//button[@type='submit' and @class = 'sc-jhAzac FTzFC sc-brqgnP elTcgq']")
	WebElement chechUrRateBtn;

	public StartPage() {

		PageFactory.initElements(driver, this);
	}

	public InformationPage clickOnCheckBtn() {

		loanAmount.clear();
		loanAmount.sendKeys(prop.getProperty("loanamount"));
		Select dropDown = new Select(loanPurpose);
		dropDown.selectByVisibleText(prop.getProperty("loanpurpose"));
		chechUrRateBtn.click();
		log.debug("Check your Rate button in Start page is clicked!! ");
		return new InformationPage();

	}

}
