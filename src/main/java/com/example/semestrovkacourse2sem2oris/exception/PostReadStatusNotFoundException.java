package com.example.semestrovkacourse2sem2oris.exception;

import com.example.semestrovkacourse2sem2oris.model.PostReadStatus;

public class PostReadStatusNotFoundException extends NotFoundServiceException {

    public PostReadStatusNotFoundException(PostReadStatus status) {
        super("Status with name '%s' not found".formatted(status.name()));
    }
}
