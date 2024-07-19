package com.sparta.thredtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> findById(String email) {

        return userRepository.findById(email);
    }
}
