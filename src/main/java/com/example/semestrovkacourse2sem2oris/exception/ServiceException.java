package com.example.semestrovkacourse2sem2oris.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ServiceException extends RuntimeException {

    private final HttpStatus status;
    public ServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

