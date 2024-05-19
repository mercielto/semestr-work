package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class ChapterNotFoundException extends ServiceException {

    public ChapterNotFoundException(String link) {
        super("Chapter by link = '%s' not found".formatted(link), HttpStatus.NOT_FOUND);
    }
}
