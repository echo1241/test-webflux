package com.sparta.thredtest.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtService {

    public static byte[] bytes;
    public static SecretKey key;
    Long expireTime = 86400000L; // 1일

    @PostConstruct
    private void init() {
        bytes = Base64.getDecoder().decode("7Iqk7YyM66W07YOA7L2U65Sp7YG065+9U3ByaW5n6rCV7J2Y7Yqc7YSw7LWc7JuQ67mI7J6F64uI64ukLg==");
        key = Keys.hmacShaKeyFor(bytes);
    }

    public static String createToken(String email) {
        Date date = new Date();

        return Jwts.builder()
                .setSubject(email)
                .claim("email", email)
//                .setExpiration(new Date(date.getTime() + expireTime))
                .signWith(key)
                .compact();

    }
}

