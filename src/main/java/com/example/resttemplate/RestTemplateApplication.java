package com.example.resttemplate;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.boot.SpringApplication;

import java.util.Objects;


@SpringBootApplication
public class RestTemplateApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RestTemplateApplication.class, args);
        Communication comm = context.getBean("communication", Communication.class);
        System.out.print("Code: ");
        System.out.printf(Objects.requireNonNull(comm.insertUser().getBody()));
        System.out.printf(Objects.requireNonNull(comm.updateUser().getBody()));
        System.out.printf(Objects.requireNonNull(comm.deleteUser().getBody()));
    }
}
