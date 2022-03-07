package org.echocat.kata.java.part1.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuration {

    private int port;

    public Configuration() {
    }

    public int getPort() {
        return port;
    }
}
