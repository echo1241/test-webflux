package com.sparta.thredtest;

import com.sparta.thredtest.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.LazyInitializationExcludeFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/test")
public class WebfluxController {

    @Autowired
    private UserService userService;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final String jwtSecret = "7Iqk7YyM66W07YOA7L2U65Sp7YG065+9U3ByaW5n6rCV7J2Y7Yqc7YSw7LWc7JuQ67mI7J6F64uI64ukLg==";


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> getUserById(@RequestParam String authHdr) {
//        if (authHdr == null || !authHdr.startsWith("Bearer ")) {
//            return Mono.error(new IllegalArgumentException("Invalid Authorization header"));
//        }
//
//        String jwtString = authHdr.substring(7); // Remove "Bearer "

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(JwtService.key)
                    .build()
                    .parseClaimsJws(authHdr).getBody();

            String email = (String) claims.get("email");

            return userService.findById(email)
                    .doOnNext(d-> System.out.println(Thread.currentThread()))
                    .doOnError(e -> System.err.println("Error fetching user: " + e.getMessage()))
                    .onErrorResume(e -> Mono.empty()); // Handle error and return empty Mono

        } catch (Exception e) {
            return Mono.error(new IllegalArgumentException("Invalid JWT token"));
        }
    }
}
