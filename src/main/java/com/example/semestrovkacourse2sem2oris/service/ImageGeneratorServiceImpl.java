package com.example.semestrovkacourse2sem2oris.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class ImageGeneratorServiceImpl implements ImageGeneratorService {

    @Value("${api.image.token}")
    private String token;

    @Override
    public byte[] generate(String text) throws IOException {
        String url = "https://api-inference.huggingface.co/models/runwayml/stable-diffusion-v1-5";
        String requestBody = "{\"inputs\": \"%s\"}".formatted(text);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");


        con.setRequestProperty("Authorization", "Bearer " + token);
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);

        // отправка
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()))) {
            writer.write(requestBody);
        }

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream inputStream = con.getInputStream();
                 ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                log.info("Image downloaded successfully");

                return outputStream.toByteArray();
            }
        }
        return new byte[0];
    }
}
