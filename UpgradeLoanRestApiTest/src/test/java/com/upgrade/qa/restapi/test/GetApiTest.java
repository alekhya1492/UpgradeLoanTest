package com.upgrade.qa.restapi.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrade.qa.restapi.base.RestClient;
import com.upgrade.qa.restapi.base.TestBase;
import com.upgrade.qa.restapi.data.State;
import com.upgrade.qa.restapi.data.StateDataResponse;
import com.upgrade.qa.restapi.data.UnitedStateCodes;
import com.upgrade.qa.restapi.util.TestUtil;

/*This class contains the actual test case scenario execution - stores the JSON Response in an object and validates below 3 conditions

1. Validates the status code of the response 
2. Validates all the state names returned are valid US states or not, and total state count is 48 or not
        	3. Validates the state label and abbreviation are mapped correctly or not
4. Validates only one state has a min age of 19, and output the name of that state
5. Validates that Georgia is the only state with min loan amount requirement of $3,005*/

public class GetApiTest extends TestBase {

	TestBase testBase;
	String serviceUrl;
	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;

	Logger log = Logger.getLogger(GetApiTest.class);

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("serviceurl");

	}

	@Test(priority = 1)
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closebaleHttpResponse = restClient.get(serviceUrl);

		// a. Getting Status Code: and validating it
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		log.debug("Status Code--->" + statusCode);

		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "ERROR!! Status code is not 200");

		// b. Getting Json String:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");

		// Create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// Convert Json string to object
		StateDataResponse sd = objectMapper.readValue(responseString, StateDataResponse.class);

		// Printing a sample state and its properties

		System.out.println("Response JSON from API---> " + sd.getStates().get(0).getLabel()
				+ sd.getStates().get(0).getAbbreviation() + sd.getStates().get(0).getMinAge()
				+ sd.getStates().get(0).getMinLoanAmount());

		// Creating object to United State Codes

		UnitedStateCodes usStateCodeMapping = new UnitedStateCodes();

		List<State> minAgeStateList = new ArrayList<State>();

		List<State> minLoanAmtStateList = new ArrayList<State>();

		List<State> responseStates = sd.getStates();

		// Validating whether total count is 48 or not

		Assert.assertEquals(responseStates.size(), TestUtil.STATE_COUNT, "ERROR!! Response doesn't match to 48 states");

		for (State s : responseStates) {

			// Validating whether states are valid US states or not
			Assert.assertEquals(usStateCodeMapping.validateStateName(s.getLabel()), Boolean.TRUE,
					"ERROR!! Invalid State Recieved ::" + s.getLabel());

			// validating the mapping of state label and state abbrevation

			Assert.assertEquals(usStateCodeMapping.getStateCode(s.getLabel()), s.getAbbreviation(),
					"ERROR!! Invalid State Code Recieved");

			if (s.getMinAge() == TestUtil.MIN_AGE) {
				minAgeStateList.add(s);
			}

			if (s.getMinLoanAmount() == TestUtil.MIN_LOAN_AMOUNT) {
				minLoanAmtStateList.add(s);

			}
		}

		// Validating whether there is only 1 state with min age as 19 or not

		Assert.assertEquals(minAgeStateList.size(), 1, "ERROR!! There should one state with minimum age of 19");

		log.debug("State with minAge = 19  :: " + minAgeStateList.get(0).getLabel());

		// Validating whether more than 1 state has min loan amount of 3005 or not

		Assert.assertEquals(minLoanAmtStateList.size(), 1,
				"ERROR!! More than one state has minimum loan amount of 3005");

		// validating whether Gerogia is the only state that has min loan amount as 3005
		// or not.

		Assert.assertEquals(minLoanAmtStateList.get(0).getLabel(), TestUtil.MIN_LOAN_AMOUNT_STATE,
				"ERROR!! This is not Georgia. Only Georgia should have a minimum loan amount of 3005. Recieved :: "
						+ minLoanAmtStateList.get(0).getLabel());

	}

}
