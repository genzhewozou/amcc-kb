package com.nroad.amcc.support.configuration;

import java.util.List;


public class DecodedDetails {

//    "tenant_id": "d13e938b-ca9f-4c19-88a7-8aa83bc2bf84",
//    "user_id": "de3961e4-d95e-4445-ad25-0016f7a90d67",
//    "user_name": "luoy",
//    "authorities": [
//        "ROLE_CRM_USER",
//        "ae5c8c5a-83e7-4303-aea1-a0f34bf9e452",
//        "fe89a22f-bc37-4956-bf7e-4153e128ebb1",
//        "ROLE_USER",
//        "d13e938b-ca9f-4c19-88a7-8aa83bc2bf84",
//        "de3961e4-d95e-4445-ad25-0016f7a90d67",
//        "ROLE_CRM_LEADER",
//        "ROLE_CALL_LEAD"
//    ],
//    "jti": "8790aac2-b3ea-4a7b-a084-f305e43e1930"
	
	
	private String tenant_id;
	
	private String user_id;

	private String user_name;

	private List<String> authorities;

	private String jti;

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}
	
	
}


