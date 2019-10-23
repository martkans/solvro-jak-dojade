package com.martkans.solvrojakdojade.authentication.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private String email;

    public User(){}

    User(User user) {
        this.email = user.email;
        this.username = user.username;
        this.password = user.password;
        this.id = user.id;
    }
}
