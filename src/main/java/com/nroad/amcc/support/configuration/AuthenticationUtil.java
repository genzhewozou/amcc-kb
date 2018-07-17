package com.nroad.amcc.support.configuration;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AuthenticationUtil {

	private static final ObjectMapper mapper=new ObjectMapper();
	
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static OAuth2AuthenticationDetails getOAuth2AuthenticationDetails() {
		return (OAuth2AuthenticationDetails)getAuthentication().getDetails();
	}
	
	public static DecodedDetails getDecodedDetails() {
		return mapper.convertValue(getOAuth2AuthenticationDetails().getDecodedDetails(), DecodedDetails.class);
	}
	
	public static String getTenantId() {
		return getDecodedDetails().getTenant_id();
	}
	
	public static String getUserId() {
		return getDecodedDetails().getUser_id();
	}
	
	public static String getUserName() {
		return getDecodedDetails().getUser_name();
	}
	
}
