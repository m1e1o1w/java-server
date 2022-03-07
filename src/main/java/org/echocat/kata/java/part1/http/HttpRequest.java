package org.echocat.kata.java.part1.http;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.echocat.kata.java.part1.http.HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED;

public class HttpRequest extends HttpMessage{
    HttpMethod method;
    String requestTarget;
    String httpVersion;

    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(String method) throws HttpParsingException {
        if(Arrays.stream(HttpMethod.values()).collect(Collectors.toList()).contains(method)){
            this.method = HttpMethod.valueOf(method);
        }else{
            throw new HttpParsingException(CLIENT_ERROR_501_NOT_IMPLEMENTED);
        }

    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public void setRequestTarget(String requestTarget) {
        this.requestTarget = requestTarget;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    HttpRequest() {
    }
}
