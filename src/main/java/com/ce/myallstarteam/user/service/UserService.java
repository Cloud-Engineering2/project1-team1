package com.ce.myallstarteam.user.service;

import com.ce.myallstarteam.user.request.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ce.myallstarteam.user.dto.UserDto;
import com.ce.myallstarteam.user.repository.UserRepository;
import com.ce.myallstarteam.user.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void registerUser(UserRequest userRequest) {

        if (userRequest != null) {
            String encryptedPassword = passwordEncoder.encode(userRequest.getPassword());

            User user = User.makeEntityWithUsernameAndPasswordAndEmail(userRequest.getUsername(), encryptedPassword, userRequest.getEmail());
            userRepository.save(user);

        }

    }

}

