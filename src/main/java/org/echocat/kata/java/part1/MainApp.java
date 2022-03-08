package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.config.Configuration;
import org.echocat.kata.java.part1.config.ConfigurationManager;
import org.echocat.kata.java.part1.core.ServerListerThread;
import org.echocat.kata.java.part1.client.AuthorClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.http.HttpClient;

public class MainApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        LOGGER.info("Starting server");

        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        configurationManager.loadConfig("src/main/resources/http.json");
        Configuration configuration = configurationManager.getConfiguration();

        try {
            ServerListerThread serverListerThread = new ServerListerThread(configuration.getPort());
            serverListerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpClient httpClient = HttpClient.newHttpClient();
        AuthorClient authorService = new AuthorClient(httpClient);
        authorService.printAuthors();
    }
}