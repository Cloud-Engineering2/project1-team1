package com.ce.myallstarteam.global.config;

import com.ce.myallstarteam.global.security.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/v1/user/login", "/api/v1/user/sign-up")
                                                    .permitAll()
                                                .anyRequest().authenticated()
                                        )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                                response.sendRedirect("/api/v1/user/login");
                        })
                )

                .formLogin(form ->
                        form
                                .loginPage("/api/v1/user/login")
                                .loginProcessingUrl("/api/v1/user/login") // login.html post 요청 시 낚아채서 요청 처리
                                .successHandler(customAuthenticationSuccessHandler())
                )

                .logout(logout -> logout.logoutSuccessUrl("/api/v1/user/login"));

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호를 안전하게 저장하기 위해 해싱 알고리즘을 사용하여 인코딩 후 저장
        // 로그인 시 입력된 비밀번호를 동일한 방식으로 해싱한 후, 데이터베이스의 해시값과 일치하는지 확인
        return new BCryptPasswordEncoder();
    }

}
