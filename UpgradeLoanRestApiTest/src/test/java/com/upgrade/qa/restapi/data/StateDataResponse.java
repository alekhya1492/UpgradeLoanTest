package com.upgrade.qa.restapi.data;

import java.util.ArrayList;
import java.util.List;

//This class contains an Array List with getter and setter methods.

public class StateDataResponse {

	List<State> states = new ArrayList<State>();

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

}
