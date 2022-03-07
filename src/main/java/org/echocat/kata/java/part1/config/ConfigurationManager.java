package org.echocat.kata.java.part1.config;

import com.fasterxml.jackson.core.type.TypeReference;
import org.echocat.kata.java.part1.util.Json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigurationManager {
    private static Configuration configuration;
    private static ConfigurationManager configurationManager;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (configurationManager == null) {
            configurationManager = new ConfigurationManager();
        }
        return configurationManager;
    }

    public void loadConfig(String path) {
        try {
            String readString = Files.readString(Path.of(path));
            configuration = Json.stringToObject(readString, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getConfiguration() {
        if (configuration == null) {
            throw new HttpConfigurationException();
        }
        return configuration;
    }
}
