package com.ce.myallstarteam.team.repository;

import com.ce.myallstarteam.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findByUserId(int userId);
    List<Team> findByUserIdAndNameContaining(int userId, String name);
}