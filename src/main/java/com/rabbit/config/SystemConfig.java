package com.rabbit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by qinxy on 2018/11/20.
 */
@Configuration
@PropertySource("file:${server.config.home}")
public class SystemConfig {

    @Autowired
    private Environment environment;

    public Environment getEnvironment(){
        return environment;
    }

    public String getProperty(String key){
        return environment.getProperty(key);
    }
}
