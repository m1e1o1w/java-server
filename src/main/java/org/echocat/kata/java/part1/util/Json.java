package org.echocat.kata.java.part1.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.echocat.kata.java.part1.config.Configuration;

public class Json {
    private static ObjectMapper objectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    public static JsonNode parse(String tree) throws JsonProcessingException {
        return objectMapper.readTree(tree);
    }

    public static <T> T stringToObject(String string, TypeReference<T> type) throws JsonProcessingException {
        return objectMapper.readValue(string, type);
    }

    public static <A> A fromJson(JsonNode node, Class<A> aClass) throws JsonProcessingException {
        return objectMapper.treeToValue(node, aClass);
    }

    public static JsonNode toJson(Object object){
        return objectMapper.valueToTree(object);
    }

    public static String stringfy(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public static String stringfyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    private static String generateJson(Object object, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if(pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(object);
    }
}
