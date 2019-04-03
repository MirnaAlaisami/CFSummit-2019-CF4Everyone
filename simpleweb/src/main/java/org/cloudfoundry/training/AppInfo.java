package org.cloudfoundry.training;

import org.springframework.stereotype.Repository;

@Repository
public class AppInfo {

	public static final String MYSQL = "MySQL";
	public static final String H2 = "H2";
	
	private String providerName;
	private String appName;
	private int instanceIndex;
	private String spaceName;
	private String database;
	private String appURIs;
	
	public AppInfo() {}

	public AppInfo(String providerName, String appName, int instanceIndex, String spaceName, String database) {
		this.providerName = providerName;
		this.appName = appName;
		this.instanceIndex = instanceIndex;
		this.spaceName = spaceName;
		this.database = database;
	}

	public String getProviderName() {
		return providerName;
	}

	public String getAppName() {
		return appName;
	}

	public int getInstanceIndex() {
		return instanceIndex;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public String getDatabase() {
		return database;
	}

	
	
	
}
