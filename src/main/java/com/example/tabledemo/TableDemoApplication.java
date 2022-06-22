package com.example.tabledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.tabledemo")
public class TableDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableDemoApplication.class, args);
    }

}
