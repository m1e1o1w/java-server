package org.echocat.kata.java.part1.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerListerThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListerThread.class);
    private final ServerSocket serverSocket;

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public ServerListerThread(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Connection accepted");
                HttpServerListenerThread httpServerListenerThread = new HttpServerListenerThread(socket);
                executorService.execute(httpServerListenerThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
