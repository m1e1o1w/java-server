package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.service.AuthorService;

import java.net.http.HttpClient;

public class MainApp {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        AuthorService authorService = new AuthorService(httpClient);
        authorService.printAuthors();
    }
}