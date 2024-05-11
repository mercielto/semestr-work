package com.example.semestrovkacourse2sem2oris.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LinkGeneratorImpl implements LinkGenerator {

    @Override
    public String generateLink() {
        return UUID.randomUUID().toString();
    }
}
