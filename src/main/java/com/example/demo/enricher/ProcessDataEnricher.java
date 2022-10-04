package com.example.demo.enricher;

import com.example.demo.data.BookDetails;
import com.example.demo.data.ProcessData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProcessDataEnricher {

    private final RestTemplate restTemplate = new RestTemplate();

    public ProcessData enrichProcessDataWithBookDetails(ProcessData processData) {
        try {
            BookDetails bookDetails = restTemplate.getForObject("http://localhost:3000/9780132350884", BookDetails.class);
            processData.setBookDetails(bookDetails);
            return processData;
        } catch (HttpClientErrorException e) {
            processData.setBookDetails(new BookDetails());
            return processData;
        }
    }
}
