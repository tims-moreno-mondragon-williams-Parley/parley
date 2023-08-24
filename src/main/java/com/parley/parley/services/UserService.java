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

// Commented out code is for followers feature if wanting to add to project.
//    public User followUser(Long followerId, Long followedId) {
//        User follower = userRepository.findById(followerId).orElse(null);
//        User followed = userRepository.findById(followedId).orElse(null);
//
//        if (follower != null && followed != null) {
//            follower.getFollowing().add(followed);
//            userRepository.save(follower);
//        }
//
//        return follower;
//    }
//
//    public User unfollowUser(Long followerId, Long followedId) {
//        User follower = userRepository.findById(followerId).orElse(null);
//        User followed = userRepository.findById(followedId).orElse(null);
//
//        if (follower != null && followed != null) {
//            followed.getFollowing().remove(follower);
//            userRepository.save(followed);
//        }
//
//        return followed;
//    }
}
