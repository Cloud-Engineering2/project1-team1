package com.ce.myallstarteam.team.entity;

import com.ce.myallstarteam.player.entity.Player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class TeamPlayer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tp_id")
	private int id;
	
	@Column(length = 30, nullable = false)
	private String position;
	
	@ManyToOne
	@JoinColumn(name = "team_id", nullable = false)
	private Team team;
	
	@ManyToOne
	@JoinColumn(name = "p_id", nullable = false)
	private Player player;
	
	
}