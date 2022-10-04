package com.example.demo.processor;

import com.example.demo.data.Book;
import com.example.demo.data.SimpleBook;
import com.example.demo.enricher.ProcessDataEnricher;
import com.example.demo.filter.SimpleBookFilter;
import com.example.demo.mapper.ProcessDataMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookProcessor {

    private final Serde<SimpleBook> simpleBookSerde;
    private final Serde<Book> bookSerde;
    private final SimpleBookFilter simpleBookFilter;
    private final ProcessDataMapper processDataMapper;
    private final ProcessDataEnricher processDataEnricher;

    @Autowired
    void processBooks(StreamsBuilder streamsBuilder) {
        Consumed<String, SimpleBook> consumed = Consumed.with(Serdes.String(), simpleBookSerde);
        Produced<String, Book> produced = Produced.with(Serdes.String(), bookSerde);

        KStream<String, SimpleBook> messageStream = streamsBuilder
            .stream("input-topic", consumed);

        messageStream
            .filter(((key, simpleBook) -> simpleBookFilter.filterNullValues(simpleBook)))
            .mapValues((key, simpleBook) -> processDataMapper.mapSimpleBookToProcessData(simpleBook))
            .mapValues((key, processData) -> processDataEnricher.enrichProcessDataWithBookDetails(processData))
            .mapValues((key, processData) -> processDataMapper.mapProcessDataToBook(processData))
            .to("output-topic", produced);
    }
}
