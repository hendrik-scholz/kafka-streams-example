package com.example.demo.mapper;

import com.example.demo.data.Book;
import com.example.demo.data.ProcessData;
import com.example.demo.data.SimpleBook;
import org.springframework.stereotype.Service;

@Service
public class ProcessDataMapper {

    public ProcessData mapSimpleBookToProcessData(SimpleBook simpleBook) {
        ProcessData processData = new ProcessData();
        processData.setSimpleBook(simpleBook);
        return processData;
    }

    public Book mapProcessDataToBook(ProcessData processData) {
        Book book = new Book();
        book.setTitle(processData.getSimpleBook().getTitle());
        book.setAuthor(processData.getSimpleBook().getAuthor());
        book.setIsbn10(processData.getSimpleBook().getIsbn10());
        book.setPublisher(processData.getBookDetails().getPublisher());
        book.setLanguage(processData.getBookDetails().getLanguage());
        book.setNumberOfPages(processData.getBookDetails().getNumberOfPages());
        book.setPrice(processData.getBookDetails().getPrice());
        return book;
    }
}
