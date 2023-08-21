package com.parley.parley.repositories;

import com.parley.parley.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAllByEmail(String email);
    List<User> findAllByUsernameContainingIgnoreCase(String username);
}
