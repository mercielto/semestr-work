package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class MultipartHasNoMimeTypeException extends ServiceException {

    public MultipartHasNoMimeTypeException(String originalFilename) {
        super("Multipart has empty mime type (%s)".formatted(originalFilename), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}