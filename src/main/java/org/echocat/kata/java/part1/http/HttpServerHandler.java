package org.echocat.kata.java.part1.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;

import static java.lang.Thread.sleep;

public class HttpServerHandler implements HttpHandler, Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerHandler.class);

    public HttpServerHandler() {
        LOGGER.info("Handler initiated");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LOGGER.info("Running on thread:"+Thread.currentThread());
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var method = exchange.getRequestMethod();
        var s = "blablabala";
        exchange.sendResponseHeaders(200,s.length());
        var body = exchange.getResponseBody();
        body.write(s.getBytes(StandardCharsets.UTF_8));

        LOGGER.info("Finishing request");
        body.close();
    }

    @Override
    public void run() {

    }
}
