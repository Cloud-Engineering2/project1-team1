package com.ce.myallstarteam.global.security;

import java.io.IOException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        org.springframework.security.core.Authentication authentication) throws IOException, ServletException {

        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        int userId = userDetails.getId(); // 사용자 ID 가져오기

        // 동적인 URL로 리디렉션
        response.sendRedirect("/api/v1/team/" + userId);

    }

}

