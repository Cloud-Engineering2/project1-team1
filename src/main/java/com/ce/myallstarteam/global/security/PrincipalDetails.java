package com.ce.myallstarteam.global.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ce.myallstarteam.user.entity.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails{

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.emptyList();
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }

    public int getId() {

        return user.getId();
    }

}

