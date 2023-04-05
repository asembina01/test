package com.example.eotinish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EotinishApplication {

	public static void main(String[] args) {
		SpringApplication.run(EotinishApplication.class, args);
	}

}
