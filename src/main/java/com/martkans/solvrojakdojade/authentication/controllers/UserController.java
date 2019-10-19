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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public String registration(@RequestBody User user){

        if (userService.findByUsername(user.getUsername()).isPresent())
            throw new BadRequestException("Username exists: " + user.getUsername());

        if (user.getUsername().length() < 6 || user.getUsername().length() > 32)
            throw new BadRequestException("Username must have between 6 and 32 characters: "
                    + user.getUsername());

        if (user.getPassword().length() < 6 || user.getPassword().length() > 32)
            throw new BadRequestException("Password must have between 6 and 32 characters");

        if (userService.findByEmail(user.getEmail()).isPresent())
            throw new BadRequestException("Email used: " + user.getEmail());

        if (!user.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
                throw new BadRequestException("Invalid email: " + user.getEmail());

        userService.save(user);

        return "User created";
    }
}
