package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class CouldNotSaveImageException extends ServiceException {

    public CouldNotSaveImageException(String fileName) {
        super("Can not save file in %s".formatted(fileName), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
