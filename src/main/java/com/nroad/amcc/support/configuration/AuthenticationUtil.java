package com.nroad.amcc.support.configuration;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AuthenticationUtil {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public static OAuth2AuthenticationDetails getOAuth2AuthenticationDetails() {
		try {
			return (OAuth2AuthenticationDetails) getAuthentication().getDetails();
		} catch (ClassCastException e) {
			// ...
		} catch (Exception e) {
			// ...
		}
		return null;
	}

	public static DecodedDetails getDecodedDetails() {
		OAuth2AuthenticationDetails oAuth2AuthenticationDetails = getOAuth2AuthenticationDetails();
		if (oAuth2AuthenticationDetails == null)
			return new DecodedDetails();
		return mapper.convertValue(oAuth2AuthenticationDetails.getDecodedDetails(), DecodedDetails.class);
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
