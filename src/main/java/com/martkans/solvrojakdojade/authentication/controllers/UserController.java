package com.martkans.solvrojakdojade.authentication.controllers;

import com.martkans.solvrojakdojade.authentication.DTOs.UserDTO;
import com.martkans.solvrojakdojade.authentication.domain.User;
import com.martkans.solvrojakdojade.authentication.mappers.UserMapper;
import com.martkans.solvrojakdojade.authentication.services.UserService;
import com.martkans.solvrojakdojade.exceptions.BadRequestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class UserController {

    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 32;
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Saves new user to database")
    @ApiResponses({
            @ApiResponse(code = 201, message = "User created!"),
            @ApiResponse(code = 400, message = "Invalid data")
    })
    public String registration(@RequestBody UserDTO user){

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

        User userToRegister = userMapper.userDtoTOUser(user);
        userService.save(userToRegister);

        return "User created";
    }
}
