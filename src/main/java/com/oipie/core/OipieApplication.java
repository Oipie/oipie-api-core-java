package com.oipie.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class OipieApplication {

    public static void main(String[] args) {
        SpringApplication.run(OipieApplication.class, args);

    }

}
