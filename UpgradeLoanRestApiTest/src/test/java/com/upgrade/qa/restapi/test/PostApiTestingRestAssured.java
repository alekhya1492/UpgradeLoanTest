package com.upgrade.qa.restapi.test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrade.qa.restapi.base.TestBase;
import com.upgrade.qa.restapi.data.UserDetails;
import com.upgrade.qa.restapi.data.Users;
import com.upgrade.qa.restapi.util.TestUtil;

import io.restassured.http.ContentType;

public class PostApiTestingRestAssured extends TestBase {

	TestBase testBase;
	String serviceUrl;

	Logger log = Logger.getLogger(PostApiTestingRestAssured.class);

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		baseURI = prop.getProperty("postserviceurl");

	}

	@Test
	public void postApiTest() throws Exception {

		log.info("Entering the postApiTest Method");

		// Placing headers in HashMap

		HashMap<String, String> mapHeaders = new HashMap<String, String>();
		mapHeaders.put("x-cf-source-id", TestUtil.SOURCE_HEADER);
		mapHeaders.put("x-cf-corr-id", TestUtil.generateRandomUuid());
		mapHeaders.put("Content-Type", TestUtil.CONTENT_TYPE);

		// Use Jackson Api and convert java object to json object

		ObjectMapper mapper = new ObjectMapper();

		Users usersObj = new Users(prop.getProperty("valid.username"), prop.getProperty("valid.password"));

		Users invalidUsersObj = new Users(prop.getProperty("invalid.username"), prop.getProperty("valid.password"));

		// Object to JsonString

		String usersJsonString = mapper.writeValueAsString(usersObj);
		String invalidUsersString = mapper.writeValueAsString(invalidUsersObj);

		log.debug("printing the json String" + usersJsonString);

		System.out.println(usersJsonString);

		try {
			// Static imported RestAssure and Matcher to directly use its static methods.

			String response = given().contentType(ContentType.JSON).headers(mapHeaders).body(usersJsonString).when()
					.post("/login").then().assertThat().statusCode(RESPONSE_STATUS_CODE_200).extract().asString();

			// Response is mapped to UserDetails class, we can validate each variable of
			// class like done for product type
			UserDetails ud = mapper.readValue(response, UserDetails.class);

			// Checking the product type field

			Assert.assertEquals("PERSONAL_LOAN", ud.getLoansInReview().get(0).getProductType(),
					"ERROR!! Wrong product type in response. Recieved :: "
							+ ud.getLoansInReview().get(0).getProductType());

			log.info("Printing the response after validating" + response);

			// Checking for invalid credentials
			given().contentType(ContentType.JSON).headers(mapHeaders).body(invalidUsersString).when().post("/login")
					.then().assertThat().statusCode(RESPONSE_STATUS_CODE_401).extract().asString();

		} catch (Exception e) {

			e.printStackTrace();

		}

		log.info("Exiting from the postApiTest Method");
	}

}
