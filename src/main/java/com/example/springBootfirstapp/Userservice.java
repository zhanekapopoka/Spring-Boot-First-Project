package com.example.springBootfirstapp;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Userservice {
   private final UserRepository userRepository;

    public Userservice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}