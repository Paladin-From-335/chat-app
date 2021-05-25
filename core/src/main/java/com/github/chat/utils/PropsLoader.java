package com.github.chat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PropsLoader {


    private static final Logger log = LoggerFactory.getLogger(PropsLoader.class);

    private Map<String, String> properties = new HashMap<>();

    public void loadProperties(String profile) {
        String pattern = String.format("application-%s.properties", profile);
        try {
            InputStream in = PropsLoader.class.getClassLoader().getResourceAsStream(pattern);
            Properties prop = new Properties();
            if (in == null) {
                System.out.println("Unable to find config.properties");
                return;
            }

            prop.load(in);
            this.properties = prop.stringPropertyNames().stream()
                    .collect(Collectors.toMap(Function.identity(), prop::getProperty));
            log.info("Enter: {}", this.properties.toString());
        } catch (IOException e) {
            log.error("Enter: Can't find properties. {}", e.getMessage());
        }
    }

    public Map<String , String> getProperties() {
        return properties;
    }
}