package com.att.dao.configurations;

import com.att.data.configurations.ConfigValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigurationDao {
    private class IdProvider {
        private int currentId;

        public IdProvider() {
            currentId = 0;
        }

        public int getNextId() {
            return this.currentId++;
        }
    }

    /**
     * No DB, so store the configs in a map.
     */
    private Map<String, List<ConfigValue>> currentConfigurations;
    private IdProvider idProvider;

    public ConfigurationDao() {
        idProvider = new IdProvider();
        currentConfigurations = new HashMap<>();
    }

    public List<ConfigValue> getConfigurationsForYearMonth(String yearMonth) {
    	List<ConfigValue> configValues = null;
    	// if multithreaded then need to synchronize all these to avoid concurrent modification exception
    	synchronized(currentConfigurations) {
    		configValues = currentConfigurations.get(yearMonth);
    	}
    	if(configValues!=null) {
    		// return copy to avoid concurrent modification exception potential or need to synchronize when used in calling method
    		configValues = new ArrayList<ConfigValue>(configValues);
    	} else {
    		configValues = new ArrayList<ConfigValue>();
    	}
    	return configValues;
    }

    public void addConfiguration(String yearMonth, ConfigValue value) {
        // assumed multithreaded access possible
        synchronized(currentConfigurations) {
        	int newId = idProvider.getNextId();
            value.setConfigId(newId); // assume new IDs are intended to be used this way, TODO verify
        	List<ConfigValue> configValue = currentConfigurations.get(yearMonth);
        	if(configValue==null) {
        		configValue = new ArrayList<ConfigValue>();
            	currentConfigurations.put(yearMonth, configValue);
        	}
        	configValue.add(value);
        }
    }

    public void removeAllConfigurationsForYearMonth(String yearMonth) {
    	synchronized(currentConfigurations) {
    		currentConfigurations.remove(yearMonth);
    	}
    }
    
    public void replaceConfigurationsForYearMonth(String yearMonth, Collection<ConfigValue> configValues) {
        synchronized(currentConfigurations) {
        	//  set all ID's
        	for(ConfigValue cv : configValues) {
        		if(cv.getConfigId()==-1) cv.setConfigId(idProvider.getNextId());
        	}
        	currentConfigurations.put(yearMonth, new ArrayList<ConfigValue>(configValues));
        }
    }
}
