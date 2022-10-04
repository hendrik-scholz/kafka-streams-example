package com.example.demo.data;

import lombok.Data;

@Data
public class Book {
    private String title;
    private String author;
    private String isbn10;
    private String publisher;
    private String language;
    private String numberOfPages;
    private String price;
}
