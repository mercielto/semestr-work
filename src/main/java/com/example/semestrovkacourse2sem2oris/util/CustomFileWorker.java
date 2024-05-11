package com.example.semestrovkacourse2sem2oris.util;

import java.io.IOException;

public interface CustomFileWorker {
    StringBuilder read(String path);

    void save(String path, String text) throws IOException;
}
