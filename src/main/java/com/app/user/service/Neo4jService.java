package com.app.user.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.TransactionConfig;
import org.neo4j.driver.summary.ResultSummary;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class Neo4jService {
	private final String GET_ALL_TENANTS = "MATCH (p:Platform)-[:HAS_TENANT]->(t:Tenant) RETURN t.uuid as tenantId";
	private final String GET_ALL_TENANT_REPO = "MATCH (p:Platform)-[:HAS_TENANT]->(t:Tenant)-[:HAS_REPOSITORY]->(r:Repository) WHERE t.uuid={tenantId} RETURN r.uuid as repoId";
	@Inject
	private Driver  driver;
	public List<String> getTenants(){
		Session session = driver.session();
		Result result = session.run(GET_ALL_TENANTS, TransactionConfig.empty());
		return result.stream().map(record -> record.get("tenantId").asString())
				.collect(Collectors.toList());
	}

	public List<String> getAllRepositoriesForTenant(){
		Session session = driver.session();
		Result result = session.run(GET_ALL_TENANT_REPO, TransactionConfig.empty());
		return result.stream().map(record -> record.get("repoId").asString())
				.collect(Collectors.toList());
	}
}
