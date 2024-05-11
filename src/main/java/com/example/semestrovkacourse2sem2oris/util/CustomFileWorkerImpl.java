package com.example.semestrovkacourse2sem2oris.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class CustomFileWorkerImpl implements CustomFileWorker {

    @Value("path.post")
    private String diskPath;

    @Override
    public StringBuilder read(String path) {
        StringBuilder builder = new StringBuilder();
        File file = new File("%s/%s".formatted(diskPath, path));

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder;
    }

    @Override
    public void save(String path, String text) throws IOException {
        FileWriter writer = new FileWriter("%s/%s".formatted(diskPath, path));
        writer.write(text);
        writer.close();
    }
}
