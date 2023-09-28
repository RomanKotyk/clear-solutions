package com.example.userapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static byte[] convertObjectToJsonBytes(Object object) throws Exception {
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsBytes(object);
    }
}
