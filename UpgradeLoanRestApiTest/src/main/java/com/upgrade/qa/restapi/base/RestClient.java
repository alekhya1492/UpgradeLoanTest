package com.upgrade.qa.restapi.base;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/*This is a java class which contains generic Http-client methods like (GET,POST,PUT and DELETE)(Get in this Use-case) which takes service URL as input and      
returns closebaleHttpResponse.*/

public class RestClient {

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();// Creates a client connection and returns a
																		// CloseableHttpcCient object
		HttpGet httpget = new HttpGet(url); // http get request
		CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httpget); // hit the GET URL

		return closebaleHttpResponse;

	}
}