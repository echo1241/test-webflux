package com.sparta.thredtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.io.IOException;


@SpringBootApplication
public class ThredtestApplication{

    public static void main(String[] args) throws IOException {

        String filePath = "multiplication_problems.txt";
        MultiplicationProblemGenerator.generateProblems(filePath, 10000);
        SpringApplication.run(ThredtestApplication.class, args);
    }

}
