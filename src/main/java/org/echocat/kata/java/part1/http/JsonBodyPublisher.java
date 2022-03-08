package org.echocat.kata.java.part1.http;

import java.nio.ByteBuffer;
import java.util.concurrent.Flow;

public class JsonBodyPublisher implements Flow.Publisher<ByteBuffer> {
    @Override
    public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {

    }
}
