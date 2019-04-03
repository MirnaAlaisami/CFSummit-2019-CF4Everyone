package org.cloudfoundry.training;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudAppInfoConfig extends AbstractCloudConfig {
	
	
	public final String CF_API_KEY = "cf_api";
	public final String APP_NAME_KEY = "application_name";
	public final String INSTANCE_INDEX_KEY = "instance_index";
    public final String SPACE_NAME_KEY = "space_name";
	
	@Bean
	public AppInfo appInfo() throws SQLException {
		Map<String,Object> properties = cloud().getApplicationInstanceInfo().getProperties();
		

		String cfAPI = (String)properties.get(CF_API_KEY);
		String providerName = (String)getProviderName(cfAPI);
		String appName = (String)properties.get(APP_NAME_KEY);
		int instanceIndex = (Integer)properties.get(INSTANCE_INDEX_KEY);
		String spaceName = (String)properties.get(SPACE_NAME_KEY);
        String database = getDatabase();
		return new AppInfo(providerName, appName, instanceIndex, spaceName, database);
	}
	
    private String getDatabase() throws SQLException {
        DataSource dataSource = null;
        try {
        	dataSource = cloud().getSingletonServiceConnector(DataSource.class, null);
        } catch (Exception e) {}
        if (dataSource == null) {
        	return AppInfo.H2;
        } else if ( isMySQL(dataSource)) {
            return AppInfo.MYSQL;
        } else {
        	 return dataSource.getConnection().getMetaData().getDriverName();
        }
    }

	private String getProviderName(String cfAPI){
		
		String result;
		switch (cfAPI) {
			case "https://api.de.a9s.eu":
                result = "anynines ("+cfAPI+")";
                break;
            case "https://api.run.pivotal.io":
                result = "Pivotal Web Services (" + cfAPI + ")";
                break;
            case "https://api.local.pcfdev.io":
                result = "PCF Dev (" + cfAPI + ")";
                break;
            case "https://api.lyra-836.appcloud.swisscom.com":
                result = "Swisscom ("+cfAPI+")";
                break;
            case "https://api.console.bluemix.net":
                result = "IBM Bluemix (" + cfAPI + ")";
                break;
            case "https://api.cf.us30.hana.ondemand.com":
                result = "SAP Cloud (" + cfAPI + ")";
            break;
            default:
                result = cfAPI;
                break;
		}
		return result;

    }
    
    
    private boolean isMySQL(DataSource dataSource) throws SQLException {
      return dataSource.getConnection().getMetaData().getDriverName().toLowerCase().contains("mysql");
    }
    
}
