package com.itl.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConversionTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	private int id;
	private String apiName;	
	private int apiNumber;
	private int tenantId;
	private String incomingColumnName;
	private String outgoingColumnName;
	private String direction;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
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
