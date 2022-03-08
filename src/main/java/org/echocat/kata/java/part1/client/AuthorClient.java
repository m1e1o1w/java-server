package org.echocat.kata.java.part1.client;

import org.echocat.kata.java.part1.http.JsonBodyHandler;
import org.echocat.kata.java.part1.http.Request;
import org.echocat.kata.java.part1.model.Author;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.List;

public class AuthorClient {
    private final static URI authorsUri = URI.create("https://jsonplaceholder.typicode.com/albums");

    private final HttpClient httpClient;
    public AuthorClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private List<Author> getAuthors(){
        HttpRequest httpRequest = getRequest();
         return httpClient.sendAsync(httpRequest, new JsonBodyHandler<List<Author>>())
                .thenApply((supplierHttpResponse) -> supplierHttpResponse.body().get())
                 .join();
    }

    private HttpRequest getRequest(){
        return Request.buildGetHttpRequest(authorsUri);
    }

    public void printAuthors(){
        System.out.println(getAuthors());
    }
}
