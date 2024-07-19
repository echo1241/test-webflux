package com.sparta.thredtest;

import com.sparta.thredtest.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.LazyInitializationExcludeFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/test")
public class WebfluxController {

    @Autowired
    private UserService userService;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final String jwtSecret = "7Iqk7YyM66W07YOA7L2U65Sp7YG065+9U3ByaW5n6rCV7J2Y7Yqc7YSw7LWc7JuQ67mI7J6F64uI64ukLg==";

    private static final Logger log = LoggerFactory.getLogger(WebfluxController.class);
    private static final String FILE_PATH = "multiplication_problems.txt";


    @GetMapping("/webflux")
    public Mono<String> webflux() {
        log.info("Webflux started");
        long startTime = System.currentTimeMillis();
        return Mono.fromCallable(() -> {List<String> problems = Files.readAllLines(Paths.get(FILE_PATH));
            List<Integer> results = problems.stream()
                    .map(this::solveProblem)
                    .toList();
            long endTime = System.currentTimeMillis();
            log.info("Webflux finished in " + (endTime - startTime) + " ms");
            return "Solved " + results.size() + " problems with WebFlux!";
        });
    }





    @GetMapping("/virtual")
    public String virtual() throws ExecutionException, InterruptedException {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var future = executor.submit(() -> {
            try {
                List<String> problems = Files.readAllLines(Paths.get(FILE_PATH));
                List<Integer> results = problems.stream()
                        .map(this::solveProblem)
                        .collect(Collectors.toList());
                return "Solved " + results.size() + " problems with Virtual Threads!";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return future.get();
    }

    private int solveProblem(String problem) {
        String[] parts = problem.split(" x ");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1].split(" =")[0]);
        return a * b;
    }


}
