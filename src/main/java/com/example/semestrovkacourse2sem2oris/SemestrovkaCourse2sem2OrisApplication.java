package com.example.semestrovkacourse2sem2oris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SemestrovkaCourse2sem2OrisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SemestrovkaCourse2sem2OrisApplication.class, args);
	}

}
