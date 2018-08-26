package com.att.web.configuarations;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.att.dao.configurations.ConfigurationDao;
import com.att.data.configurations.ConfigValue;

@RestController
@RequestMapping(value="/configuration")
public class ConfigurationController {

    private ConfigurationDao dao;

    @Autowired
    public ConfigurationController(ConfigurationDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.GET)
    @ResponseBody
    public List<ConfigValue> getConfigurationsForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth) {
    	return dao.getConfigurationsForYearMonth(yearMonth);
    }

    @RequestMapping(value="/{yearMonthNumber}", method=RequestMethod.DELETE)
    public void deleteConfigurationsForYearMonth(@PathVariable("yearMonthNumber") String yearMonth) {
        try {
        	dao.removeAllConfigurationsForYearMonth(yearMonth);
        } catch (Exception ex) {
        	// No configuration? TODO verify error cases, log?
        }
    }

    @RequestMapping(value="/{yearMonthNumber}", method={ RequestMethod.POST, RequestMethod.PUT })
    public void addConfigurationForYearMonth(
            @PathVariable("yearMonthNumber") String yearMonth,
            @RequestBody ConfigValue value) {
    	dao.addConfiguration(yearMonth, value);
    }
    
    @RequestMapping(value="/saveConfigurations/{yearMonthNumber}", method={ RequestMethod.POST })
    @ResponseBody
    public int saveConfigurations(
    		@PathVariable("yearMonthNumber") String yearMonth,
    		@RequestBody Collection<ConfigValue> configValues) {
    	dao.replaceConfigurationsForYearMonth(yearMonth, configValues);
    	return 0;  //  returning a value in the response prevents response from being sent before datastore is updated
    }
}
