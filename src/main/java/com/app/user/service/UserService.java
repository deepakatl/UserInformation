package com.app.user.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;

@Singleton
public class UserService {
	@Inject
	private Neo4jService neo4jService;
	public List<String> getTenants() {
		return neo4jService.getTenants();
	}
	public List<String> getAllRepositoriesForTenant(String tenantId) {
		return neo4jService.getTenants();
	}
}
