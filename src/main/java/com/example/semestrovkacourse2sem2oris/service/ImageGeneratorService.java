package com.example.semestrovkacourse2sem2oris.service;

import java.io.IOException;

public interface ImageGeneratorService {

    byte[] generate(String text) throws IOException;
}
