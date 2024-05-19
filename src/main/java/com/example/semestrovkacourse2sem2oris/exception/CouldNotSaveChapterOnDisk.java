package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class CouldNotSaveChapterOnDisk extends ServiceException {

    public CouldNotSaveChapterOnDisk(String path) {
        super("Can not save file by path '%s'".formatted(path), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
