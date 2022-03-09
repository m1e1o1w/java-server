package org.echocat.kata.java.part1.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServerListenerThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerListenerThread.class);
    private final Socket socket;

    public HttpServerListenerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            String response = "<html>\n" +
                    "  <head>\n" +
                    "    <title>Href Attribute Example</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <h1>Href Attribute Example</h1>\n" +
                    "  </body>\n" +
                    "</html>";
            final String CRFL = "\f\n";
            String response1 = "HTTP/1.1 200 OK" + CRFL;
//                    "Content-Length: " + response.getBytes().length + CRFL
//                    + CRFL
//                    + response + CRFL + CRFL;

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            outputStream.write(response1.getBytes());
            outputStream.close();
            LOGGER.info("Wrote response");
        } catch (IOException e) {
            LOGGER.error("Problem with connection");
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                LOGGER.error("Problem with socket");
            }
        }
    }
}
