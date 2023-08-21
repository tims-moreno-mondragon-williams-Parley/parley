package com.parley.parley.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Mock user data. In a real-world scenario, fetch from a database.
        if ("admin".equals(username)) {
            return User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("password")
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
