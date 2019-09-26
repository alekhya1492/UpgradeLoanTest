package com.upgrade.qa.restapi.util;

import java.util.UUID;

//This  java class contains all utility methods declared in static so that the test cases can easily access the most commonly used methods/variables/constants

public class TestUtil {

	public static final int MIN_AGE = 19;
	public static final int MIN_LOAN_AMOUNT = 3005;
	public static final String MIN_LOAN_AMOUNT_STATE = "Georgia";
	public static final int STATE_COUNT = 48;
	public static final String CONTENT_TYPE = "application/json";
	public static final String SOURCE_HEADER = "coding-challenge";

	public static String generateRandomUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
