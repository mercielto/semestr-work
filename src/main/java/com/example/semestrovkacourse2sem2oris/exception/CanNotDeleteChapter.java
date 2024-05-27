package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class CanNotDeleteChapter extends ServiceException {
    public CanNotDeleteChapter(String reason) {
        super("Can not delete chapter from repository. Reason: %s".formatted(reason),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
