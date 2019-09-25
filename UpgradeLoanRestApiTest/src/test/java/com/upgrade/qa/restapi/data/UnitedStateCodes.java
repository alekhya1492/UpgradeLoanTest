package com.upgrade.qa.restapi.data;

import java.util.Map;
import java.util.TreeMap;

//This class contains a Hash Map which stores all valid US States and their Abbreviations in Key value pair(Key - State name , Value - 						Abbreviation ) 

public class UnitedStateCodes {

	final Map<String, String> stateToCodeMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

	public UnitedStateCodes() {
		stateToCodeMap.put("Alabama", "AL");
		stateToCodeMap.put("Montana", "MT");
		stateToCodeMap.put("Alaska", "AK");
		stateToCodeMap.put("Nebraska", "NE");
		stateToCodeMap.put("Arizona", "AZ");
		stateToCodeMap.put("Nevada", "NV");
		stateToCodeMap.put("Arkansas", "AR");
		stateToCodeMap.put("New Hampshire", "NH");
		stateToCodeMap.put("California", "CA");
		stateToCodeMap.put("New Jersey", "NJ");
		stateToCodeMap.put("District of Columbia", "DC");
		stateToCodeMap.put("New Mexico", "NM");
		stateToCodeMap.put("Connecticut", "CT");
		stateToCodeMap.put("New York", "NY");
		stateToCodeMap.put("Delaware", "DE");
		stateToCodeMap.put("North Carolina", "NC");
		stateToCodeMap.put("Florida", "FL");
		stateToCodeMap.put("North Dakota", "ND");
		stateToCodeMap.put("Georgia", "GA");
		stateToCodeMap.put("Ohio", "OH");
		stateToCodeMap.put("Hawaii", "HI");
		stateToCodeMap.put("Oklahoma", "OK");
		stateToCodeMap.put("Idaho", "ID");
		stateToCodeMap.put("Oregon", "OR");
		stateToCodeMap.put("Illinois", "IL");
		stateToCodeMap.put("Pennsylvania", "PA");
		stateToCodeMap.put("Indiana", "IN");
		stateToCodeMap.put("Rhode Island", "RI");
		stateToCodeMap.put("Iowa", "IA");
		stateToCodeMap.put("South Carolina", "SC");
		stateToCodeMap.put("Kansas", "KS");
		stateToCodeMap.put("South Dakota", "SD");
		stateToCodeMap.put("Kentucky", "KY");
		stateToCodeMap.put("Tennessee", "TN");
		stateToCodeMap.put("Louisiana", "LA");
		stateToCodeMap.put("Texas", "TX");
		stateToCodeMap.put("Maine", "ME");
		stateToCodeMap.put("Utah", "UT");
		stateToCodeMap.put("Maryland", "MD");
		stateToCodeMap.put("Vermont", "VT");
		stateToCodeMap.put("Massachusetts", "MA");
		stateToCodeMap.put("Virginia", "VA");
		stateToCodeMap.put("Michigan", "MI");
		stateToCodeMap.put("Washington", "WA");
		stateToCodeMap.put("Minnesota", "MN");
		stateToCodeMap.put("Mississippi", "MS");
		stateToCodeMap.put("Wisconsin", "WI");
		stateToCodeMap.put("Missouri", "MO");
		stateToCodeMap.put("Wyoming", "WY");
	}

	public Boolean validateStateName(String stateName) {
		return stateToCodeMap.containsKey(stateName);
	}

	public String getStateCode(String stateName) {
		return stateToCodeMap.get(stateName);
	}
}
