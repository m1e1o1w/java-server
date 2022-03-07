package org.echocat.kata.java.part1.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListerThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListerThread.class);
    private final int port;
    private final ServerSocket serverSocket;

    public ServerListerThread(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();

                LOGGER.info("Connection accepted");
                HttpServerListenerThread httpServerListenerThread = new HttpServerListenerThread(socket);
                httpServerListenerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
