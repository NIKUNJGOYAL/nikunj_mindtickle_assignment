package com.org.automation.utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnvManager {
    public static Properties properties = new Properties();
    public static Map<String,String> fileMap = new HashMap<>();

    public static String fetchEnvProperties(String key) {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/env.properties");
            properties.load(fileInputStream);
            fileMap.put(key,properties.getProperty(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileMap.get(key);
    }
}
