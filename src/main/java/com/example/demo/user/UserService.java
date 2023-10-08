package com.example.demo.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
