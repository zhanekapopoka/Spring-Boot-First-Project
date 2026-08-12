package com.example.springBootfirstapp.Service;

import com.example.springBootfirstapp.Repositories.UserRepository;
import com.example.springBootfirstapp.Entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Userservice {
   private final UserRepository userRepository;

    public Userservice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}