package com.example.semestrovkacourse2sem2oris.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocOpenApiConfig {

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .packagesToScan("com.example.semestrovkacourse2sem2oris")
                .build();
    }
}
