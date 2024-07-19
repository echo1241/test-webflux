package com.sparta.thredtest;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRepository extends R2dbcRepository<User, String> {
}
