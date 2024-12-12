package com.ce.myallstarteam.user.dto;

import com.ce.myallstarteam.team.dto.TeamPlayerDto;
import com.ce.myallstarteam.user.entity.User;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UserDto {

    private int id;
    private String username;
    private String password;
    private String email;


    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public User toEntity(){
        return User.of(this.id, this.username, this.password, this.email);
    }


}

