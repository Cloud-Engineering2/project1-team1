package com.ce.myallstarteam.team.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.ce.myallstarteam.user.entity.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(value = { AuditingEntityListener.class })
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "u_id")
    private User user;

    @Column(name="team_name", length = 100)
    private String name;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
    
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<TeamPlayer> teamPlayers = new ArrayList<>();

    private Team(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public static Team of(User user, String name) {
        return new Team(user, name);
    }

    public void updateTeamName(String teamName) {
        this.name = teamName;
    }

    public void updateTeamPlayers(List<TeamPlayer> teamPlayers) {
        this.teamPlayers.clear();
        this.teamPlayers.addAll(teamPlayers);
    }
}