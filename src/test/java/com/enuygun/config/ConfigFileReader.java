package com.enuygun.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private final Properties properties;

    public ConfigFileReader() {
        BufferedReader reader;
        String propertyFilePath = "src/test/resources/config.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("configs.properties not found at " + propertyFilePath);
        }
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("baseUrl");
        if (baseUrl != null) return baseUrl;
        else throw new RuntimeException("BaseUrl not specified in the configs.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("ImplicitlyWait not specified in the configs.properties file.");
    }

    public long getPageLoadTimeout() {
        String pageLoadTimeout = properties.getProperty("pageLoadTimeout");
        if (pageLoadTimeout != null) return Long.parseLong(pageLoadTimeout);
        else throw new RuntimeException("PageLoadTimeout not specified in the configs.properties file.");
    }

    public long getWebDriverWait() {
        String webDriverWait = properties.getProperty("webDriverWait");
        if (webDriverWait != null) return Long.parseLong(webDriverWait);
        else throw new RuntimeException("PageLoadTimeout not specified in the configs.properties file.");
    }

    public String getDepartureCity() {
        String departureCity = properties.getProperty("departureCity");
        if (departureCity != null) return departureCity;
        else throw new RuntimeException("DepartureCity not specified in the configs.properties file.");
    }

    public String getDestinationCity() {
        String destinationCity = properties.getProperty("destinationCity");
        if (destinationCity != null) return destinationCity;
        else throw new RuntimeException("DestinationCity not specified in the configs.properties file.");
    }

    public String getDepartureDate() {
        String departureDate = properties.getProperty("departureDate");
        if (departureDate != null) return departureDate;
        else throw new RuntimeException("DepartureDate not specified in the configs.properties file.");

    }
}