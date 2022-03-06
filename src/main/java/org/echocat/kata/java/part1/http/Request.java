package org.echocat.kata.java.part1.http;

import java.net.URI;

public class Request {

    public static java.net.http.HttpRequest buildGetHttpRequest(URI uri){
        return java.net.http.HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .header("Content-Type", "application/json")
                .build();
    }

}
