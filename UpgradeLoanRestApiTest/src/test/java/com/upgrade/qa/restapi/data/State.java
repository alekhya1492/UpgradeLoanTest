package com.upgrade.qa.restapi.data;

/*This class declares below properties of all states

1.label  
2.Abbreviation  
3.minimum loan amount 
4.minimum age*/

public class State {

	String label;
	String abbreviation;
	Float minLoanAmount;
	Integer minAge;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Float getMinLoanAmount() {
		return minLoanAmount;
	}

	public void setMinLoanAmount(Float minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

}
