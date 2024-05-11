package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends ServiceException {
    public PostNotFoundException(String link) {
        super("Post by link (%s) not found".formatted(link), HttpStatus.NOT_FOUND);
    }
}
