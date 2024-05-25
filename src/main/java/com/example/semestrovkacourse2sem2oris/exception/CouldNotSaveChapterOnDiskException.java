package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class CouldNotSaveChapterOnDiskException extends ServiceException {

    public CouldNotSaveChapterOnDiskException(String path) {
        super("Can not save file by path '%s'".formatted(path), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
