package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class CanNotSaveImageOnDiskException extends ServiceException {

    public CanNotSaveImageOnDiskException() {
        super("Can not save file on disk", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
