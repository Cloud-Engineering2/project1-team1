package com.ce.myallstarteam.player.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ce.myallstarteam.player.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
    @Query("SELECT p FROM Player p WHERE p.dPosition = :position AND p.name = :name")
    Page<Player> findByNameAndPosition(@Param("position") String position, @Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Player p WHERE p.dPosition = :position")
    Page<Player> findByPosition(@Param("position") String position, Pageable pageable);

    @Query("SELECT p FROM Player p WHERE p.name = :name")
    Page<Player> findByName(@Param("name") String name, Pageable pageable);

    Page<Player> findAll(Pageable pageable);
}