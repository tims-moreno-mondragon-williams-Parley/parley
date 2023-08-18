package com.parley.parley.repositories;

import com.parley.parley.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findByUsername(String username);
}
