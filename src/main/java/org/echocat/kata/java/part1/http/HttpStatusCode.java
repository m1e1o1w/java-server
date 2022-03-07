package org.echocat.kata.java.part1.http;

public enum HttpStatusCode {
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request"),
    CLIENT_ERROR_405_METHOD_NOT_ALLOWED(405, "Method not allowed"),
    CLIENT_ERROR_414_BAD_REQUEST(414, "URI too long"),
    CLIENT_ERROR_500_INTERNAL_SERVER(500, "Internal Server"),
    CLIENT_ERROR_501_NOT_IMPLEMENTED(501, "Not implemented");

    public final int STATUS_CODE;
    public final String MESSAGE;

    HttpStatusCode(int STATUS_CODE, String MESSAGE) {
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
    }
}
