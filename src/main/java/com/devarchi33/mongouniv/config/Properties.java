package com.devarchi33.mongouniv.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by donghoon on 2016. 6. 6..
 */
@ConfigurationProperties(locations = {"classpath:/application.yml"})
@Component
@Data
public class Properties {
    private Map<String, Map<String, String>> servers = new HashMap<>();
    private String env;
}
