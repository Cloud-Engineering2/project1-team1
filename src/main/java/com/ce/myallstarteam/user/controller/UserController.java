package com.ce.myallstarteam.user.controller;

import com.ce.myallstarteam.user.repository.UserRepository;
import com.ce.myallstarteam.user.request.UserRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ce.myallstarteam.user.dto.UserDto;
import com.ce.myallstarteam.user.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "signUp";
    }

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String signUpWithJson(@RequestBody UserRequest userRequest){

        if (userRequest != null) {
            userService.registerUser(userRequest);
        }

        return "redirect:/api/v1/user/login";
    }
    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String signUpWithForm(UserRequest userRequest){

        if (userRequest != null) {
            userService.registerUser(userRequest);
        }

        return "redirect:/api/v1/user/login";

    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/check-username")
    public ResponseEntity<Map<String, Object>> checkUsername(@RequestParam String username) {
        Map<String, Object> response = new HashMap<>();
        boolean available = userRepository.findByUsername(username) == null;
        response.put("available", available); // true: 사용 가능, false: 중복
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }


}
