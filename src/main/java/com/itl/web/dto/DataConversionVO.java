package com.itl.web.dto;

public class DataConversionVO {

	public int apiNumber;
	public int tenantId;
	public String apiName;		
	public String incomingColumnName;
	public String outgoingColumnName;
	public String direction;
	public int getApiNumber() {
		return apiNumber;
	}
	public void setApiNumber(int apiNumber) {
		this.apiNumber = apiNumber;
	}
	public int getTenantId() {
		return tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getIncomingColumnName() {
		return incomingColumnName;
	}
	public void setIncomingColumnName(String incomingColumnName) {
		this.incomingColumnName = incomingColumnName;
	}
	public String getOutgoingColumnName() {
		return outgoingColumnName;
	}
	public void setOutgoingColumnName(String outgoingColumnName) {
		this.outgoingColumnName = outgoingColumnName;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
	
	
	
}
