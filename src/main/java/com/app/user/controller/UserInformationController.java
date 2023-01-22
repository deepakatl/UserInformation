package com.app.user.controller;

import com.app.user.service.UserService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("essential-user")
public class UserInformationController {

	private final UserService userService;
	public UserInformationController(UserService userService){
		this.userService = userService;
	}
	@Get("/test")
	public String getRefreshToken(){
		return "OK";
	}

	@Get("/{context}/tenants")
	public List<String> getAllTenants(String context){
		return userService.getTenants();
	}
	@Get("/{context}/tenants/{tenantId}/repository")
	public List<String> getAllRepositoriesForTenant(String context, String tenantId){
		return userService.getAllRepositoriesForTenant(tenantId);
	}
}
