package com.nroad.amcc.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nroad.event.ApplicationEvent;

public class AuditEvent implements ApplicationEvent{

	public static final String EVENT_NAME = "audit.created";
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String uri;
	@JsonProperty
	private String method;
	@JsonProperty
	private String remoteAddr;
	@JsonProperty
	private String principal;
	@JsonProperty
	private Long createdDate;
	@JsonProperty
	private String serviceSource;
	@JsonProperty
	private String username;
	@JsonProperty
	private String tenantId;
	
	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return EVENT_NAME;
	}

	@Override
	public String getEventSourceId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public String getServiceSource() {
		return serviceSource;
	}

	public void setServiceSource(String serviceSource) {
		this.serviceSource = serviceSource;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	

}
