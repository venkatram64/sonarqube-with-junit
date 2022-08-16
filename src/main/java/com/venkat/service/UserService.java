package com.venkat.service;

import com.venkat.model.User;
import com.venkat.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Long save(User user){
        User newUser = this.userRepository.save(user);
        return newUser.getId();
    }
}
