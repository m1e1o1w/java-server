package org.echocat.kata.java.part1.http;

public class HttpParsingException extends Exception{
    private HttpStatusCode httpStatusCode;

    public HttpParsingException(HttpStatusCode statusCode){
        super(statusCode.MESSAGE);
        httpStatusCode = statusCode;
    }

}
