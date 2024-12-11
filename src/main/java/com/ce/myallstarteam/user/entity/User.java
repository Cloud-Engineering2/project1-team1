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

    @Column(name = "u_pw", length = 50, nullable = false)
    private String password;

    @Column(name = "u_email", length = 50, nullable = false)
    private String email;
}
