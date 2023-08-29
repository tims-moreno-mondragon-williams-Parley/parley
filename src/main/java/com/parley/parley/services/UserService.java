package com.parley.parley.services;

import com.parley.parley.models.User;
import com.parley.parley.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUserProfile(User updatedUser) {
        return userRepository.save(updatedUser);
    }

}
