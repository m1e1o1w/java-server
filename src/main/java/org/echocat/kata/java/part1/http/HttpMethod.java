package org.echocat.kata.java.part1.http;

import java.util.Arrays;

public enum HttpMethod {
    GET,
    HEAD;
    public static final int MAX_LENGTH;

    static {
        MAX_LENGTH = Arrays.stream(HttpMethod.values()).mapToInt(it -> it.toString().length()).max().getAsInt();
    }
}
