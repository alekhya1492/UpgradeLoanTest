package com.upgrade.qa.restapi.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//This is a java class which contains the initialization of config .properties file

public class TestBase {

	protected Properties prop;

	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_201 = 401;

	public TestBase() {

		try {

			prop = new Properties();
			FileInputStream src = new FileInputStream(
					"/Users/alekhya/eclipse-workspace/UpgradeLoanRestApiTest/src/main/java/com/upgrade/qa/restapi/config/config.properties");
			prop.load(src);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException io) {

			io.printStackTrace();
		}

	}

}
