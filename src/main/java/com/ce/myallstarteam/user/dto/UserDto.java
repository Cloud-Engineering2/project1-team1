package com.ce.myallstarteam.user.dto;

import com.ce.myallstarteam.user.entity.User;

import lombok.Getter;

@Getter
public class UserDto {

    private int id;
    private String username;
    private String password;
    private String email;


    public User toEntity(){
        return User.of(this.id, this.username, this.password, this.email);
    }


}

