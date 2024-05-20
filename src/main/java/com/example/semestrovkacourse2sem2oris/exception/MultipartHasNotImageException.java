package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class MultipartHasNotImageException extends ServiceException {

    public MultipartHasNotImageException() {
        super("Multipart received with file not image", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}
