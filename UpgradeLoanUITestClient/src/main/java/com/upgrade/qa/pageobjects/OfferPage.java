package com.upgrade.qa.pageobjects;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upgrade.qa.base.TestBase;
import com.upgrade.qa.utill.TestUtil;

public class OfferPage extends TestBase {

	Logger log = Logger.getLogger(OfferPage.class);

	@FindBy(css = ".sc-chPdSV.VlhWk")
	WebElement loanAmount;

	@FindBy(xpath = "//div[@class = 'sc-iQNlJl euLQKM']/span")
	WebElement monthlyPayment;

	@FindBy(xpath = "//div[@class='section--xs' and @data-auto = 'defaultLoanTerm']")
	WebElement monthlyTerm;

	@FindBy(xpath = "//div[@class='section--xs' and @data-auto = 'defaultLoanInterestRate']")
	WebElement interestRate;

	@FindBy(xpath = "//div[@tabindex='0' and @class = 'sc-hZSUBg dMzhYO']/div")
	WebElement apr;

	// Used Absolute xpath for below 2 webElements as Relative Xpath is not working

	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div/header/div/label")
	WebElement menuBtn;

	@FindBy(xpath = "//*[@id=\"root\"]/div/main/div/header/div/nav/ul/li[2]/a")
	WebElement signOut;

	public OfferPage() {

		PageFactory.initElements(driver, this);
	}

//If in case individual values are to be validated, use the below method. But here, in the offer page 
	// the web elements are same so, by just getting the texts it solves the problem

//	public Map<String, Double> getOfferPageValueMap() {
//
//		Map<String, Double> valueMap = new HashMap<String, Double>();
//		valueMap.put("loan", Double.parseDouble(loanAmount.getText().replaceAll(",", "").trim()));
//		valueMap.put("payment", Double.parseDouble(monthlyPayment.getText().replaceAll("$", "").trim()));
//		valueMap.put("term", Double.parseDouble(monthlyTerm.getText().split(" ")[0].trim()));
//		valueMap.put("rate", Double.parseDouble(interestRate.getText().split(" ")[0].replaceAll("%", "").trim()));
//		valueMap.put("aprValue", Double.parseDouble(apr.getText().split(" ")[0].replaceAll("%", "").trim()));
//		System.out.println("Initial Offer Page values array ::s " + valueMap.values().toArray());
//		return valueMap;
//	}

	public Map<String, String> getOfferPageValueMap() {

		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put(TestUtil.LOAN_AMOUNT, loanAmount.getText());
		valueMap.put("payment", monthlyPayment.getText());
		valueMap.put("term", monthlyTerm.getText());
		valueMap.put("rate", interestRate.getText());
		valueMap.put("aprValue", apr.getText());
		log.debug("Offer Page values array :: " + valueMap.values().toString());
		return valueMap;
	}

	public LogOutPage clickOnSignOut() throws InterruptedException {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", menuBtn);

		log.debug("Menu Button is clicked!!");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		executor.executeScript("arguments[0].click()", signOut);

		log.debug("SignOut Button is clicked!!");

		return new LogOutPage();

	}

	public String pageTitle() {

		return driver.getTitle();
	}

}
