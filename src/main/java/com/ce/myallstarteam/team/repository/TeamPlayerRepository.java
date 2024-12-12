package com.ce.myallstarteam.team.repository;

import com.ce.myallstarteam.team.entity.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, Integer> {
    List<TeamPlayer> findByTeamId(int teamId);
}