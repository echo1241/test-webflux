package com.sparta.thredtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication
public class ThredtestApplication {

    public static void main(String[] args){

        SpringApplication.run(ThredtestApplication.class, args);
    }

}
