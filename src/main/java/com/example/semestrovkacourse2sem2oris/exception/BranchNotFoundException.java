package com.example.semestrovkacourse2sem2oris.exception;

import org.springframework.http.HttpStatus;

public class BranchNotFoundException extends ServiceException {
    public BranchNotFoundException(String branchLink) {
        super("Branch by link '%s' not found".formatted(branchLink), HttpStatus.NOT_FOUND);
    }
}
