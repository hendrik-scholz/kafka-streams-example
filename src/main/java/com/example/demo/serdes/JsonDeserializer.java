package com.example.demo.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

@Slf4j
class JsonDeserializer<T> implements Deserializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Class<T> destinationClass;

    public JsonDeserializer(Class<T> destinationClass) {
        this.destinationClass = destinationClass;
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }

        try {
            return objectMapper.readValue(data, destinationClass);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
