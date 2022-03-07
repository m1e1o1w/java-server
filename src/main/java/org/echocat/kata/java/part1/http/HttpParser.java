package org.echocat.kata.java.part1.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    private static final int SP = 0x20;
    private static final int CR = 0x80;
    private static final int LF = 0x8A;

    public void parseHttpRequest(InputStream is) throws IOException, HttpParsingException {
        InputStreamReader reader = new InputStreamReader(is, StandardCharsets.US_ASCII);
        HttpRequest httpRequest = new HttpRequest();
        parseRequestLine(reader, httpRequest);
        parseHeaders(reader, httpRequest);
        parseBody(reader, httpRequest);
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest httpRequest) throws IOException, HttpParsingException {
        StringBuilder stringBuilder = new StringBuilder();

        boolean methodParsed = false;
        boolean requestTarget = false;
        int _byte;
        while((_byte = reader.read())>=0){
            if(_byte ==CR){
                _byte = reader.read();
                if(_byte == LF){
                    return;
                }
            }
            if(_byte==SP){
                if(!methodParsed){
                    httpRequest.setMethod(stringBuilder.toString());
                    methodParsed = true;
                }else if(! requestTarget){
                    requestTarget= true;
                }else{
                    throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }

                stringBuilder.delete(0,stringBuilder.length());
            }else{
                stringBuilder.append((char)_byte);
                if(!methodParsed){
                    if(stringBuilder.length()>HttpMethod.MAX_LENGTH){
                        throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED);
                    }
                }
            }
        }
    }

    private void parseBody(InputStreamReader reader, HttpRequest httpRequest) {

    }

    private void parseHeaders(InputStreamReader reader, HttpRequest httpRequest) {

    }


}
