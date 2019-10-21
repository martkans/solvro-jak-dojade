package com.martkans.solvrojakdojade.authentication.controllers;

import com.martkans.solvrojakdojade.authentication.domain.User;
import com.martkans.solvrojakdojade.authentication.services.UserService;
import com.martkans.solvrojakdojade.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 32;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public String registration(@RequestBody User user){

        if (userService.findByUsername(user.getUsername()).isPresent())
            throw new BadRequestException("Username exists: " + user.getUsername());

        if (user.getUsername().length() < MIN_LENGTH || user.getUsername().length() > MAX_LENGTH)
            throw new BadRequestException("Username must have between " + MIN_LENGTH + " and " + MAX_LENGTH + "characters: "
                    + user.getUsername());

        if (user.getPassword().length() < MIN_LENGTH || user.getPassword().length() > MAX_LENGTH)
            throw new BadRequestException("Password must have between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters");

        if (userService.findByEmail(user.getEmail()).isPresent())
            throw new BadRequestException("Email used: " + user.getEmail());

        if (!user.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
                throw new BadRequestException("Invalid email: " + user.getEmail());

        userService.save(user);

        return "User created";
    }
}
