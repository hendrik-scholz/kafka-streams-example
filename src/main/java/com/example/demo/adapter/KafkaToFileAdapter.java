package com.example.demo.adapter;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class KafkaToFileAdapter {

    @KafkaListener(topics = "output-topic", groupId = "fileAdapter")
    public void listen(String message) {
        String time = LocalDateTime.now().toString();

        try (OutputStream outputStream = new FileOutputStream("testOutput_" + time)) {
            outputStream.write(message.getBytes(UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
