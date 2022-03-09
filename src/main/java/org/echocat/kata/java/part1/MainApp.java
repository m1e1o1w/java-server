package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.client.AuthorClientSocket;
import org.echocat.kata.java.part1.config.Configuration;
import org.echocat.kata.java.part1.config.ConfigurationManager;
import org.echocat.kata.java.part1.core.ServerListerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;

public class MainApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        LOGGER.info("Starting server");

        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        configurationManager.loadConfig("src/main/resources/http.json");
        Configuration configuration = configurationManager.getConfiguration();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket((configuration.getPort()));
            ServerListerThread serverListerThread = new ServerListerThread(serverSocket);
            serverListerThread.start();
        } catch (IOException e) {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

//        HttpClient httpClient = HttpClient.newHttpClient();
//        AuthorClient authorService = new AuthorClient(httpClient);
//        authorService.printAuthors();
//

        try {
            URL clientUrl = URI.create("https://jsonplaceholder.typicode.com/albums").toURL();
            Socket socket = new Socket(clientUrl.getHost(), 80);
            AuthorClientSocket authorClientSocket = new AuthorClientSocket(socket, clientUrl);
            authorClientSocket.getAuthors();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}