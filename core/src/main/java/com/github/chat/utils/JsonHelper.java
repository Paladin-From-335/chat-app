package com.github.chat.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class JsonHelper {

    private static final Logger log = LoggerFactory.getLogger(JsonHelper.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Optional<String> toJson(Object obj) {
        try {
            return Optional.of(MAPPER.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            System.out.printf("Enter %s \n", e.getMessage());
        }
        return Optional.empty();

    }

    public static <T> Optional<T> fromJson(String str, Class<T> cls) {
        try {
            return Optional.of(MAPPER.readValue(str, cls));
        } catch (JsonProcessingException e) {
            System.out.printf("Enter %s \n", e.getMessage());
        }
        return Optional.empty();
    }
}