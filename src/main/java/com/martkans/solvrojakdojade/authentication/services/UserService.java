package com.martkans.solvrojakdojade.authentication.services;

import com.martkans.solvrojakdojade.authentication.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User save(User user);
}
