package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class InventaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventaireApplication.class, args);
    }
}
