package com.upgrade.qa.restapi.data;

public class LoanDetails {

	private Integer id;
	private String uuid;
	private String status;
	private String productType;
	private String sourceSystem;
	private Boolean hasOpenBackendCounter;
	private String purpose;
	private String createDate;
	private String postIssuanceStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public Boolean getHasOpenBackendCounter() {
		return hasOpenBackendCounter;
	}

	public void setHasOpenBackendCounter(Boolean hasOpenBackendCounter) {
		this.hasOpenBackendCounter = hasOpenBackendCounter;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPostIssuanceStatus() {
		return postIssuanceStatus;
	}

	public void setPostIssuanceStatus(String postIssuanceStatus) {
		this.postIssuanceStatus = postIssuanceStatus;
	}

}
