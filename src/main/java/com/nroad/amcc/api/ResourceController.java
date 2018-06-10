package com.nroad.amcc.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	
	@RequestMapping(value = "/public", method = RequestMethod.GET)
	public Object getPublic() {
		return "public resource.";
	}

	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	public Object getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@RequestMapping(value = "/principal", method = RequestMethod.GET)
	public Object getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal;
	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public Object getRoles() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Object helloUser() {
		return "hello user.";
	}
	
	@PreAuthorize("hasRole('TENANT_ADMIN')")
	@RequestMapping(value = "/tenantAdmin", method = RequestMethod.GET)
	public Object helloTenantAdmin() {
		return "hello tenant admin.";
	}
	
	@PreAuthorize("hasRole('TENANT_ADMIN') and hasAuthority(#gid)")
	@RequestMapping(value = "/tenantAdminGid", method = RequestMethod.GET)
	public Object helloTenantAdminGid(String gid) {
		return "hello tenant admin gid.";
	}
	
	@PreAuthorize("hasRole('AMCC_LEADER')")
	@RequestMapping(value = "/amccLeader", method = RequestMethod.GET)
	public Object helloAmccLeader() {
		return "hello amcc leader.";
	}

	
	@PreAuthorize("hasRole('AMCC_LEADER') or hasAuthority(#gid)")
	@RequestMapping(value = "/amccLeaderGid", method = RequestMethod.GET)
	public Object helloAmccLeaderGid(String gid) {
		return "hello amcc leader gid.";
	}
}
