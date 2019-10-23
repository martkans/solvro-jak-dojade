package com.martkans.solvrojakdojade.authentication.DTOs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserDTO {
    @ApiModelProperty(value = "username", example = "testUser")
    private String username;
    @ApiModelProperty(value = "password", example = "mySecretPassword")
    private String password;
    @ApiModelProperty(value = "email", example = "test_user@mail.com")
    private String email;
}
