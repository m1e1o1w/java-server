package org.echocat.kata.java.part1.client;

import java.io.*;
import java.net.Socket;
import java.net.URL;

public class AuthorClientSocket {

    private final Socket socket;
    private final URL url;

    public AuthorClientSocket(Socket socket, URL urL) {
        this.socket = socket;
        this.url = urL;
    }

    public void getAuthors(){
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            writer.println("GET " + url.getPath() + " HTTP/1.1");
            writer.println("Host: " + url.getHost());
            writer.println("User-Agent: Simple Http Client");
            writer.println("Accept: text/html");
            writer.println("Accept-Language: en-US");
            writer.println("Connection: keep-alive");
            writer.println();

            InputStream input = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
