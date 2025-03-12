package com.example.seconddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SecondDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondDemoApplication.class, args);
    }

}
