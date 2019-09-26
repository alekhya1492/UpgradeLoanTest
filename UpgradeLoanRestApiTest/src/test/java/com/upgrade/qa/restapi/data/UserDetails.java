package com.upgrade.qa.restapi.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 Mapping JSON response
 
"firstName" : "Ian",
"userId" : 9114917,
"userUuid" : "34c16f53-38c4-461a-bd14-11fa748d2663",
"loanApplications" : [ ],
"loansInReview" : [ {
"id" : 9545966,
"uuid" : "230ea84a-7199-41c9-bf38-fff27e35970d",
"status" : "PENDING",
"productType" : "PERSONAL_LOAN",
"sourceSystem" : "BORROWER_FUNNEL_V2",
"hasOpenBackendCounter" : false,
"purpose" : "CREDIT_CARD",
"createDate" : "2019-08-21T18:18:59.959Z",
"postIssuanceStatus" : null
}]
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {

	private String firstName;
	private Integer userId;
	private String userUuid;
	private List<LoanDetails> loansInReview;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public List<LoanDetails> getLoansInReview() {
		return loansInReview;
	}

	public void setLoansInReview(List<LoanDetails> loansInReview) {
		this.loansInReview = loansInReview;
	}

}
