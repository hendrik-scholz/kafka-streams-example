package com.example.demo.filter;

import com.example.demo.data.SimpleBook;
import org.springframework.stereotype.Service;

@Service
public class SimpleBookFilter {

    public boolean filterNullValues(SimpleBook simpleBook) {
        return simpleBook != null;
    }
}
