package com.example.demo.serdes;

import com.example.demo.data.Book;
import com.example.demo.data.SimpleBook;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JsonSerdes {

    @Bean
    public Serde<SimpleBook> getSimpleBookSerde() {
        JsonSerializer<SimpleBook> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<SimpleBook> jsonDeserializer = new JsonDeserializer<>(SimpleBook.class);
        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }

    @Bean
    public Serde<Book> getBookSerde() {
        JsonSerializer<Book> jsonSerializer = new JsonSerializer<>();
        JsonDeserializer<Book> jsonDeserializer = new JsonDeserializer<>(Book.class);
        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }
}
