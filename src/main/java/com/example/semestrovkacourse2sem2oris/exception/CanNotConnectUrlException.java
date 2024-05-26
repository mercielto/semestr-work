package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class CanNotConnectUrlException extends ServiceException {

    public CanNotConnectUrlException() {
        super("Can not connect to image download url", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
