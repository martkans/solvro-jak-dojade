package com.martkans.solvrojakdojade.authentication.services;

import com.martkans.solvrojakdojade.authentication.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    void save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
