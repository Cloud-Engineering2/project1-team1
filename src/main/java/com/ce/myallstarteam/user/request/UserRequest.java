package com.ce.myallstarteam.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UserRequest {
    private String username;
    private String password;
    private String email;

}
