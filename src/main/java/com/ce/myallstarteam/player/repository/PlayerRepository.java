package com.ce.myallstarteam.player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ce.myallstarteam.player.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{

}
