package com.upgrade.qa.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.upgrade.qa.base.TestBase;

public class LoginPage extends TestBase {

	Logger log = Logger.getLogger(LoginPage.class);

	@FindBy(name = "username")
	WebElement uname;

	@FindBy(name = "password")
	WebElement pwd;

	@FindBy(xpath = "//button[@type = 'submit']")
	WebElement signInBtn;

	public LoginPage() {

		PageFactory.initElements(driver, this);
	}

	public OfferPage logingIn(String username, String password) {

		uname.sendKeys(username);
		pwd.sendKeys(password);
		signInBtn.click();
		log.debug("SignIn Button is clicked!!!");

		return new OfferPage();
	}

}
