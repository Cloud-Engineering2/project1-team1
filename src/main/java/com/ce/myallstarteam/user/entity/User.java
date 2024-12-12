package com.ce.myallstarteam.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int id;

    @Column(name = "u_name", length = 50, nullable = false)
    private String username;

    @Column(name = "u_pw", length = 60, nullable = false)
    private String password;

    @Column(name = "u_email", length = 50, nullable = false)
    private String email;

    public static User of(int userId, String username, String password, String email) {
        return new User(userId, username, password, email);
    }

    public static User makeEntityWithUsernameAndPasswordAndEmail(String username, String password, String email) {

        User user = new User();
        user.username = username;
        user.password = password;
        user.email = email;

        return user;
    }
}

