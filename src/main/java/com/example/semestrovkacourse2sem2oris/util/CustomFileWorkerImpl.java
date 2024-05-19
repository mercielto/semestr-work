package com.example.semestrovkacourse2sem2oris.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Slf4j
public class CustomFileWorkerImpl implements CustomFileWorker {

    @Value("${path.post}")
    private String diskPath;

    @Override
    public StringBuilder read(String path) {
        StringBuilder builder = new StringBuilder();
        File file = new File("%s\\%s".formatted(diskPath, path));

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            try {
                save(path, "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        return builder;
    }

    @Override
    public void save(String path, String text) throws IOException {
        File file = new File("%s\\%s".formatted(diskPath, path));

        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            if (parentDir.mkdirs()) {
                log.info("Директории успешно созданы: " + parentDir.getPath());
            } else {
                log.error("Не удалось создать директории: " + parentDir.getPath());
                return;
            }
        }

        // Проверяем, существует ли файл, и создаем его, если он не существует
        if (!file.exists()) {
            if (file.createNewFile()) {
                log.info("Файл создан:" + file.getPath());
            } else {
                log.error("Не удалось создать файл: " + file.getPath());
                return;
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
        }
    }
}
