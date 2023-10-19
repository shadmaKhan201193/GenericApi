package com.itl.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Resource_urls {

	@Id
	private int apiId;
	private String apiName;
	private String serviceUrl;
	
	public int getApiId() {
		return apiId;
	}
	public void setApiId(int apiId) {
		this.apiId = apiId;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
	
	
	
	
}
