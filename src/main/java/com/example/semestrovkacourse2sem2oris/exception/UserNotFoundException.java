package com.example.semestrovkacourse2sem2oris.exception;

public class UserNotFoundException extends NotFoundServiceException {

    public UserNotFoundException(String attribute) {
        super("User with attribute %s not found".formatted(attribute));
    }
}
