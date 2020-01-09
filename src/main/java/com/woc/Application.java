package com.woc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.woc")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
